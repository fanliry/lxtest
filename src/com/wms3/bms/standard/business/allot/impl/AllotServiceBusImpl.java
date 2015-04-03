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
	/** ��ƷDAO�� */
	protected IBaseProductDao prtDAO;
	/** �ͻ�DAO�� */
	protected IBaseCustomerDao custDAO;
	/**�ϼܹ���*/
	protected IPutawayRuleDao putRuleDAO;
	/** �ϼܹ�����ϸDAO�� */
	protected IPutawayDetailRuleDao putRuleDetailDAO;
	
	/** �������DAO�� */
	protected IAllocationRuleDao allotRuleDAO;
	/** ���������ϸDAO�� */
	protected IAllocationDetailRuleDao allotRuleDetailDAO;
	/** ��������DAO�� */
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
	 * ���ָ����λ��֤���
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public BaseCargospace inVerifyEntry(InAllotRequestBean req) throws Exception {

		InAllotRequestBean.ProductBean prtBean = (InAllotRequestBean.ProductBean) req.lsProductBean
		.get(0);		
		BaseProduct prdt = prtDAO.getProductById(prtBean.getProductid());// ��ȡ��Ʒ

		Hashtable<String, Object> hsMethodParam = new Hashtable<String, Object>();
		hsMethodParam.put("BaseProduct", prdt);
		hsMethodParam.put("reqBean", req);
		hsMethodParam.put("prtBean", prtBean);
		hsMethodParam.put("target.cargoSpaceId", req.spaceid);// Ŀ���λ
		
		Object obj = performAllot("com.wms3.bms.standard.dao.allot.impl.InboundAllotDaoImpl", "getCsFromTargetCs",
				 hsMethodParam);

		return (BaseCargospace)obj;
	}

	/**
	 * ������ִ�����
	 * 
	 * @param invoiceId
	 * @return
	 */
	public List<InAllotResponseBean> inAllotEntry(InAllotRequestBean req) throws Exception {
		// ���ݲ�Ʒ->�ͻ����������з���
		// (1)���ݲ�Ʒ��ѯ���������
		// (2)���ݿͻ���ѯ���������
		// (3)�����������������з���
		// (3-1)���������������Ĺ���������performAllot����ִ�з���
		// (3-2)�������ɹ����ҷ�������ǻ����ȣ��򷵻ػ�λ�б��������ִ����һ��������

		List<InAllotResponseBean> retLs = new ArrayList<InAllotResponseBean>();

		if (req == null)
			return null;

		// ��Ʒ�������
		int pAssignNum = 1;

		// �����ϼܷ�ʽ
		if (!req.putmode.equals("PL"))
			pAssignNum = req.getProductBeanSize();

		for (int i = 0; i < pAssignNum; i++) {
			InAllotRequestBean.ProductBean prtBean = (InAllotRequestBean.ProductBean) req.lsProductBean
					.get(i);

			// ��ȡ��Ʒ
			BaseProduct prdt = prtDAO.getProductById(prtBean.getProductid());

			// ��ȡ��Ʒ������Id
			String ruleId = prdt.getPutawayid();
			List<InAllotResponseBean> tmpLs= null;
			
			if(prtBean.putnum > prtBean.realputnum)
				tmpLs = inAllotExecPrt(prdt, ruleId, req, prtBean);
			else
				continue;
			
			if(tmpLs != null)
				retLs.addAll(tmpLs);
			
			if (tmpLs == null || ((prtBean.putnum > prtBean.realputnum)&&(req.putmode.equals("CS")||req.putmode.equals("EA")))||(req.iTrays>req.iRealTrays&&req.putmode.equals("PL"))) {
				// ��ȡ�ͻ�
				BaseCustomer cust = custDAO.getCustomerById(prtBean.getOwnerid());

				// ��ȡ�ͻ�������
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

		// �ӹ�������л�ȡ��Ӧ����
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
			if (retObj != null) {// ���������һ��������䵽��λ���򷵻�
				return (List<InAllotResponseBean>)retObj;
			}
		}

		return null;
	}

	/**
	 * ����ָ����λ��֤���
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public BaseCargospace  outVerifyEntry(OutAllotVerifyRequestBean verifyReq) throws Exception {
		
		
		return null;
	}
	
	public List<OutAllotResponseBean> outAllotEntry(OutAllotRequestBean req)
			throws Exception {
		// ���ݲ�Ʒ->�ͻ����������з���
		// (1)���ݲ�Ʒ��ѯ����������
		// (2)���ݿͻ���ѯ����������
		// (3)���ݳ�������������з���
		// (3-1)���ݳ�����������Ĺ���������performAllot����ִ�з���
		// (3-2)�������ɹ����ҷ�������ǻ����ȣ��򷵻ػ�λ�б��������ִ����һ��������

		List<OutAllotResponseBean> retLs = null;

		if (req == null)
			return null;
		
		//�����Ʒ��ϢprtBean
		OutAllotRequestBean.ProductBean prtBean = req.productBean;
		
		//��ȡ��Ʒ����
		BaseProduct prdt = prtDAO.getProductById(prtBean.getProductid());

		// ��ȡ��Ʒ�������Id
		String ruleId = prdt.getAllocationid();
		retLs = outAllotExecPrt(prdt, ruleId, req, prtBean);
		if (retLs == null) {
			// ��ȡ�ͻ�
			BaseCustomer cust = custDAO.getCustomerById(req.ownerid);

			// ��ȡ�ͻ��������
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

		// �ӹ�������л�ȡ��Ӧ����
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
			if (retObj != null) {// ���������һ��������䵽��λ���򷵻�
				return (List) retObj;
			}
		}

		return null;

	}

	/**
	 * ����ִ�з���,�ڹ������ϸ����������Ҫ��ִ���༰�������ý�ȥ
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
			Logger.info("�����λʱ��������,��������" + strClass + "������������"  + strMethod + "��" + e.getMessage());
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
