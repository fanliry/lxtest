package com.wms3.bms.standard.business.allot.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.dao.base.IBaseCustomerDao;
import com.wms3.bms.standard.dao.base.IBaseProductDao;
import com.wms3.bms.standard.dao.base.impl.BaseCustomerDaoImpl;
import com.wms3.bms.standard.dao.base.impl.BaseProductDaoImpl;
import com.wms3.bms.standard.dao.rule.IAllocationDetailRuleDao;
import com.wms3.bms.standard.dao.rule.IAllocationRuleDao;
import com.wms3.bms.standard.dao.rule.IPutawayDetailRuleDao;
import com.wms3.bms.standard.dao.rule.IPutawayRuleDao;
import com.wms3.bms.standard.dao.rule.IRuleConfigDao;
import com.wms3.bms.standard.dao.rule.impl.AllocationDetailRuleDaoImpl;
import com.wms3.bms.standard.dao.rule.impl.AllocationRuleDaoImpl;
import com.wms3.bms.standard.dao.rule.impl.PutawayDetailRuleDaoImpl;
import com.wms3.bms.standard.dao.rule.impl.PutawayRuleDaoImpl;
import com.wms3.bms.standard.dao.rule.impl.RuleConfigDaoImpl;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BaseCustomer;
import com.wms3.bms.standard.entity.base.BaseProduct;
import com.wms3.bms.standard.entity.rule.RuleAllocation;
import com.wms3.bms.standard.entity.rule.RuleAllocationDetail;
import com.wms3.bms.standard.entity.rule.RuleConfig;
import com.wms3.bms.standard.entity.rule.RulePutaway;
import com.wms3.bms.standard.entity.rule.RulePutawayDetail;

public class AllotServiceBusImpl {
	protected EntityDAO m_dao;
	/** 产品DAO类 */
	protected IBaseProductDao prtDAO;
	/** 客户DAO类 */
	protected IBaseCustomerDao custDAO;
	/**上架规则*/
	protected IPutawayRuleDao putRuleDAO;
	/** 上架规则详细DAO类 */
	protected IPutawayDetailRuleDao putRuleDetailDAO;
	
	/** 分配规则DAO类 */
	protected IAllocationRuleDao allotRuleDAO;
	/** 分配规则详细DAO类 */
	protected IAllocationDetailRuleDao allotRuleDetailDAO;
	/** 规则配置DAO类 */
	protected IRuleConfigDao configDAO;

	public AllotServiceBusImpl(EntityDAO dao) {
		m_dao = dao;
		prtDAO = new BaseProductDaoImpl(dao);
		custDAO = new BaseCustomerDaoImpl(dao);
		putRuleDAO = new PutawayRuleDaoImpl(dao);
		putRuleDetailDAO = new PutawayDetailRuleDaoImpl(dao);
		allotRuleDAO = new AllocationRuleDaoImpl(dao);
		allotRuleDetailDAO = new AllocationDetailRuleDaoImpl(dao);
		configDAO = new RuleConfigDaoImpl(dao);
	}
	
	/**
	 * 入库指定货位验证入口
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public BaseCargospace inVerifyEntry(InAllotRequestBean req) throws Exception {

		InAllotRequestBean.ProductBean prtBean = (InAllotRequestBean.ProductBean) req.lsProductBean
		.get(0);		
		BaseProduct prdt = prtDAO.getProductById(prtBean.getProductid());// 获取产品

		Hashtable<String, Object> hsMethodParam = new Hashtable<String, Object>();
		hsMethodParam.put("BaseProduct", prdt);
		hsMethodParam.put("reqBean", req);
		hsMethodParam.put("prtBean", prtBean);
		hsMethodParam.put("target.cargoSpaceId", req.spaceid);// 目标货位
		
		Object obj = performAllot("com.wms3.bms.standard.dao.allot.impl.InboundAllotDaoImpl", "getCsFromTargetCs",
				 hsMethodParam);

		return (BaseCargospace)obj;
	}

	/**
	 * 入库分配执行入口
	 * 
	 * @param invoiceId
	 * @return
	 */
	public List<InAllotResponseBean> inAllotEntry(InAllotRequestBean req) throws Exception {
		// 根据产品->客户分配规则进行分配
		// (1)根据产品查询入库分配规则
		// (2)根据客户查询入库分配规则
		// (3)根据入库分配规则项进行分配
		// (3-1)根据入库分配规则项的规则代码调用performAllot方法执行分配
		// (3-2)如果分配成功，且分配规则是或优先，则返回货位列表，否则继续执行下一条规则项

		List<InAllotResponseBean> retLs = new ArrayList<InAllotResponseBean>();

		if (req == null)
			return null;

		// 产品分配次数
		int pAssignNum = 1;

		// 分析上架方式
		if (!req.putmode.equals("PL"))
			pAssignNum = req.getProductBeanSize();

		for (int i = 0; i < pAssignNum; i++) {
			InAllotRequestBean.ProductBean prtBean = (InAllotRequestBean.ProductBean) req.lsProductBean
					.get(i);

			// 获取产品
			BaseProduct prdt = prtDAO.getProductById(prtBean.getProductid());

			// 获取产品入库规则Id
			String ruleId = prdt.getPutawayid();
			List<InAllotResponseBean> tmpLs= null;
			
			if(prtBean.putnum > prtBean.realputnum)
				tmpLs = inAllotExecPrt(prdt, ruleId, req, prtBean);
			else
				continue;
			
			if(tmpLs != null)
				retLs.addAll(tmpLs);
			
			if (tmpLs == null || ((prtBean.putnum > prtBean.realputnum)&&(req.putmode.equals("CS")||req.putmode.equals("EA")))||(req.iTrays>req.iRealTrays&&req.putmode.equals("PL"))) {
				// 获取客户
				BaseCustomer cust = custDAO.getCustomerById(prtBean.getOwnerid());

				// 获取客户入库规则
				if(cust != null){
					ruleId = cust.getPutawayid();				
					retLs = inAllotExecPrt(prdt, ruleId, req, prtBean);
				}
			}
			
			if(req.putmode =="PL"){
				if(req.iTrays == 1 && req.iRealTrays == 1)
					break;
				else if(req.iTrays > 1 && req.iTrays == req.iRealTrays)
					break;
			}
			else {
				int iReq = 0;
				int iReal = 0;
				
				for(int j=0; j < req.lsProductBean.size(); j++){
					InAllotRequestBean.ProductBean pBean = req.lsProductBean.get(j);
					iReq += pBean.putnum;
					iReal += pBean.realputnum;
				}
				
				if(iReq == iReal)
					break;
			}
		}

		return retLs;
	}

	protected List<InAllotResponseBean> inAllotExecPrt(BaseProduct prdt,
			String ruleId, InAllotRequestBean reqBean,
			InAllotRequestBean.ProductBean prtBean) throws Exception {
		if (ruleId == null || prdt == null)
			return null;

		RulePutaway putRule = null;
		List<RulePutawayDetail> lsRuleDetail = null;

		// 从规则对象中获取相应数据
		String strClass = null;
		String strMethod = null;

		lsRuleDetail = putRuleDetailDAO.getPutawayDetailRuleById(ruleId);

		Hashtable<String, Object> hsMethodParam = new Hashtable<String, Object>();
		hsMethodParam.put("BaseProduct", prdt);
		hsMethodParam.put("reqBean", reqBean);
		hsMethodParam.put("prtBean", prtBean);
		hsMethodParam.put("dao", m_dao);
		
		RulePutaway rPutaway = putRuleDAO.getPutawayRuleById(ruleId);
		String whId = rPutaway.getWarehouseid();
		hsMethodParam.put("target.warehouseid", whId);

		for (int i = 0; i < lsRuleDetail.size(); i++) {
			RulePutawayDetail rPutawayDetail = lsRuleDetail.get(i);
			hsMethodParam.put("rPutawayDetail", rPutawayDetail);

			String strConfigId = rPutawayDetail.getRuleconfigid();

			RuleConfig rconfig = configDAO.getRuleConfigById(strConfigId);			

			strClass = rconfig.getRuleclass();
			strMethod = rconfig.getRulemethod();

			Object retObj = performAllot(strClass, strMethod, hsMethodParam);
			if (retObj != null) {// 如果其中有一条规则分配到货位，则返回
				return (List<InAllotResponseBean>)retObj;
			}
		}

		return null;
	}

	/**
	 * 出库指定货位验证入口
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public BaseCargospace  outVerifyEntry(OutAllotVerifyRequestBean verifyReq) throws Exception {
		
		
		return null;
	}
	
	public List<OutAllotResponseBean> outAllotEntry(OutAllotRequestBean req)
			throws Exception {
		// 根据产品->客户分配规则进行分配
		// (1)根据产品查询出库分配规则
		// (2)根据客户查询出库分配规则
		// (3)根据出库分配规则项进行分配
		// (3-1)根据出库分配规则项的规则代码调用performAllot方法执行分配
		// (3-2)如果分配成功，且分配规则是或优先，则返回货位列表，否则继续执行下一条规则项

		List<OutAllotResponseBean> retLs = null;

		if (req == null)
			return null;
		
		//请求产品信息prtBean
		OutAllotRequestBean.ProductBean prtBean = req.productBean;
		
		//获取产品对象
		BaseProduct prdt = prtDAO.getProductById(prtBean.getProductid());

		// 获取产品出库规则Id
		String ruleId = prdt.getAllocationid();
		retLs = outAllotExecPrt(prdt, ruleId, req, prtBean);
		if (retLs == null) {
			// 获取客户
			BaseCustomer cust = custDAO.getCustomerById(req.ownerid);

			// 获取客户出库规则
			ruleId = cust.getAllocationid();
			
			if(ruleId == null)
				return null;

			retLs = outAllotExecPrt(prdt, ruleId, req, prtBean);

		}

		return retLs;

	}

	protected List<OutAllotResponseBean> outAllotExecPrt(BaseProduct prdt, String ruleId, OutAllotRequestBean reqBean,
			OutAllotRequestBean.ProductBean prtBean)
			throws Exception {

		if (ruleId == null || prdt == null)
			return null;

		List<RuleAllocationDetail> lsRuleDetail = null;

		// 从规则对象中获取相应数据
		String strClass = null;
		String strMethod = null;

		lsRuleDetail = allotRuleDetailDAO.getAllocationDetailRuleById(ruleId);

		Hashtable<String, Object> hsMethodParam = new Hashtable<String, Object>();
		hsMethodParam.put("BaseProduct", prdt);
		hsMethodParam.put("reqBean", reqBean);
		hsMethodParam.put("prtBean", prtBean);
		hsMethodParam.put("dao", m_dao);
		
		RuleAllocation rAllocation = allotRuleDAO.getAllocationRuleById(ruleId);
		String whId = rAllocation.getWarehouseid();
		hsMethodParam.put("target.warehouseid", whId);

		for (int i = 0; i < lsRuleDetail.size(); i++) {
			RuleAllocationDetail rAllocationDetail = lsRuleDetail.get(i);
			hsMethodParam.put("rAllocationDetail", rAllocationDetail);
			
			String strConfigId = rAllocationDetail.getRuleconfigid();
			RuleConfig rconfig = configDAO.getRuleConfigById(strConfigId);
			strClass = rconfig.getRuleclass();
			strMethod = rconfig.getRulemethod();

			Object retObj = performAllot(strClass, strMethod, hsMethodParam);
			if (retObj != null) {// 如果其中有一条规则分配到货位，则返回
				return (List) retObj;
			}
		}

		return null;

	}

	/**
	 * 分析执行方法,在规则的详细代码项中需要把执行类及方法配置进去
	 * 
	 * @param strClass
	 * @param strMethod
	 * @param hsMethodParam
	 * @return
	 */
	protected Object performAllot(String strClass, String strMethod,
			Hashtable<String, Object> hsMethodParam) {
		try {
			Object obj = Class.forName(strClass).newInstance();
			Method method = obj.getClass().getMethod(strMethod,
					new Class[] { Hashtable.class });
			//InboundAllotDaoImpl.getCsFromStoragetype
			return method.invoke(obj, new Object[] { hsMethodParam });
		} catch (Exception e) {
			e.printStackTrace();
			Logger.info("分配货位时候发生错误,类名（：" + strClass + "），方法名（"  + strMethod + "）" + e.getMessage());
		}

		return null;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
}
