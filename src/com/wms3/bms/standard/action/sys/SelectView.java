package com.wms3.bms.standard.action.sys;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.dwr.TreeBean;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.business.base.IAlleyBus;
import com.wms3.bms.standard.business.base.ICargoAreaBus;
import com.wms3.bms.standard.business.base.IPackageMastermesgBus;
import com.wms3.bms.standard.business.base.IProducttypeBus;
import com.wms3.bms.standard.business.base.ITypeBus;
import com.wms3.bms.standard.business.base.IWarehouseBus;
import com.wms3.bms.standard.business.base.IWhAreaBus;
import com.wms3.bms.standard.business.base.IWhAreaVirtualBus;
import com.wms3.bms.standard.business.base.impl.AlleyBusImpl;
import com.wms3.bms.standard.business.base.impl.CargoAreaBusImpl;
import com.wms3.bms.standard.business.base.impl.PackageMastermesgBusImpl;
import com.wms3.bms.standard.business.base.impl.ProducttypeBusImpl;
import com.wms3.bms.standard.business.base.impl.TypeBusImpl;
import com.wms3.bms.standard.business.base.impl.WarehouseBusImpl;
import com.wms3.bms.standard.business.base.impl.WhAreaBusImpl;
import com.wms3.bms.standard.business.base.impl.WhAreaVirtualBusImpl;
import com.wms3.bms.standard.business.outbound.IOutBoundBus;
import com.wms3.bms.standard.business.outbound.impl.OutBoundBusImpl;
import com.wms3.bms.standard.business.rule.IAllocationRuleBus;
import com.wms3.bms.standard.business.rule.IPutawayRuleBus;
import com.wms3.bms.standard.business.rule.IReplenishRuleBus;
import com.wms3.bms.standard.business.rule.IRuleConfigBus;
import com.wms3.bms.standard.business.rule.impl.AllocationRuleImpl;
import com.wms3.bms.standard.business.rule.impl.PutawayRuleImpl;
import com.wms3.bms.standard.business.rule.impl.ReplenishRuleImpl;
import com.wms3.bms.standard.business.rule.impl.RuleConfigImpl;
import com.wms3.bms.standard.dao.base.impl.BaseBatchDaoImpl;
import com.wms3.bms.standard.dao.base.impl.BaseLotDetailDaoImpl;
import com.wms3.bms.standard.entity.base.BaseAlley;
import com.wms3.bms.standard.entity.base.BaseBatch;
import com.wms3.bms.standard.entity.base.BaseCargoarea;
import com.wms3.bms.standard.entity.base.BaseLot;
import com.wms3.bms.standard.entity.base.BaseLotDetail;
import com.wms3.bms.standard.entity.base.BasePackageMastermesg;
import com.wms3.bms.standard.entity.base.BaseProducttype;
import com.wms3.bms.standard.entity.base.BaseType;
import com.wms3.bms.standard.entity.base.BaseWarehouse;
import com.wms3.bms.standard.entity.base.BaseWharea;
import com.wms3.bms.standard.entity.base.BaseWhareaVirtual;
import com.wms3.bms.standard.entity.base.BranchMeans;
import com.wms3.bms.standard.entity.base.Workshop;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;
import com.wms3.bms.standard.entity.rule.RuleAllocation;
import com.wms3.bms.standard.entity.rule.RuleConfig;
import com.wms3.bms.standard.entity.rule.RulePutaway;
import com.wms3.bms.standard.entity.rule.RuleReplenish;
import com.wms3.bms.standard.service.BMSService;


/**
 * 描述:显示下拉框的值
 * @author hugui
 *
 */
public class SelectView 
{
	/**
	 * 功能:获得所有仓库列表
	 * @param id
	 * @return
	 */
	public List getWarehouseList(String id)
	{
		List lsResult = new ArrayList();
		EntityDAO dao = BMSService.getm_EntityDAO();
		IWarehouseBus warehouseBus = new WarehouseBusImpl(dao);
		List ls = null;
		try
		{
			ls = warehouseBus.getWarehouseList();
		}catch(Exception er)
		{
			Logger.error("获得仓库信息失败:" + er.getMessage());
		}
		if(ls != null)
		{
			for(int i = 0; i < ls.size(); i++)
			{
				BaseWarehouse house = (BaseWarehouse)ls.get(i);
				String strFlag = "0";
				if(id != null && id.trim().length() > 0)
				{
					if(house.getWarehouseid().equals(id))
					{
						strFlag = "1";
					}
					
				}else{//显示当前仓库
					
					if(house.getIscurrent() != null  && house.getIscurrent().equals("Y"))
					{
						strFlag = "1";
					}
				}
				lsResult.add(new TreeBean(house.getWarehouseid(), house.getWarehousename(), strFlag));
			}
		}
		return lsResult;
	}

	/**
	 * 功能:获得指定仓库的库区
	 * @param id
	 * @param warehouseid
	 * @return
	 */
	public List getWhAreaList(String id, String warehouseid)
	{
		List lsResult = new ArrayList();
		EntityDAO dao = BMSService.getm_EntityDAO();
		IWhAreaBus whAreaBus = new WhAreaBusImpl(dao);
		
		List ls = null;
		try
		{
			ls = whAreaBus.getWhAreaByWhid(warehouseid);
		}catch(Exception er)
		{
			Logger.error("获得库区信息失败:" + er.getMessage());
		}
		if(ls != null)
		{
			for(int i = 0; i < ls.size(); i++)
			{
				BaseWharea wharea = (BaseWharea)ls.get(i);
				String strFlag = "0";
				if(wharea.getwhAreaId().equals(id))
				{
					strFlag = "1";
				}
				lsResult.add(new TreeBean(wharea.getwhAreaId(), wharea.getwhAreaName(), strFlag));
			}
		}
		return lsResult;
	}
	
	/**
	 * 功能:获得指定仓库的虚拟库区
	 * @param id
	 * @param warehouseid
	 * @return
	 */
	public List getWhAreaVirtualList(String id, String warehouseid)
	{
		List lsResult = new ArrayList();
		EntityDAO dao = BMSService.getm_EntityDAO();
		IWhAreaVirtualBus whAreaVirtualBus = new WhAreaVirtualBusImpl(dao);
		
		List ls = null;
		try
		{
			ls = whAreaVirtualBus.getWhAreaByWhid(warehouseid);
		}catch(Exception er)
		{
			Logger.error("获得库区信息失败:" + er.getMessage());
		}
		if(ls != null)
		{
			for(int i = 0; i < ls.size(); i++)
			{
				BaseWhareaVirtual wharea = (BaseWhareaVirtual)ls.get(i);
				String strFlag = "0";
				if(wharea.getwhAreaId().equals(id))
				{
					strFlag = "1";
				}
				lsResult.add(new TreeBean(wharea.getwhAreaId(), wharea.getwhAreaName(), strFlag));
			}
		}
		return lsResult;
	}
	
	/**
	 * 功能:获得指定仓库的库区
	 * @param id
	 * @param warehouseid
	 * @return
	 */
	public List getWhAreaNotTemList(String id, String warehouseid)
	{
		List lsResult = new ArrayList();
		EntityDAO dao = BMSService.getm_EntityDAO();
		IWhAreaBus whAreaBus = new WhAreaBusImpl(dao);
		
		List ls = null;
		try
		{
			ls = whAreaBus.getWhAreaNotTemByWhid(warehouseid);
		}catch(Exception er)
		{
			Logger.error("获得库区信息失败:" + er.getMessage());
		}
		if(ls != null)
		{
			for(int i = 0; i < ls.size(); i++)
			{
				BaseWharea wharea = (BaseWharea)ls.get(i);
				String strFlag = "0";
				if(wharea.getwhAreaId().equals(id))
				{
					strFlag = "1";
				}
				lsResult.add(new TreeBean(wharea.getwhAreaId(), wharea.getwhAreaName(), strFlag));
			}
		}
		return lsResult;
	}
    /**
     * 功能:获得指定仓库的暂存库区
     * @param warehouseid
     * @return
     */
    public List getZCWhAreaList(String warehouseid,String whAreaId)
    {
        List lsResult = new ArrayList();
        EntityDAO dao = BMSService.getm_EntityDAO();
        IWhAreaBus whAreaBus = new WhAreaBusImpl(dao);  
        BaseWharea zc = null;
        try
        {
            zc = whAreaBus.getZCgetWhAreaByWhid(warehouseid,whAreaId);
        }catch(Exception er)
        {
            Logger.error("获得暂存库区信息失败:" + er.getMessage());
        }
        if(zc != null)
        {
            lsResult.add(new TreeBean(zc.getwhAreaId(), zc.getwhAreaName(), "1"));
        }
        return lsResult;
    }
	
	/**
	 * 功能:判断库区类型是否是立体库区
	 * @param whAreaId
	 * @return
	 */
	public boolean isWhAreaTypeRight(String whAreaId)
	{
		EntityDAO dao = BMSService.getm_EntityDAO();
		IWhAreaBus whAreaBus = new WhAreaBusImpl(dao);
		
		try
		{
			BaseWharea wharea = whAreaBus.getWhareaById(whAreaId);
			if(wharea.getwhAreaType().equals("2")){		//2.立体库区
				return true;
			}
		}catch(Exception er)
		{
			Logger.error("判断库区类型是否是立体库区失败:" + er.getMessage());
		}
		return false;
	}
	
	/**
	 * 功能:获得指定仓库的货区
	 * @param id
	 * @param warehouseid
	 * @return
	 */
	public List getCargoAreaList(String id, String warehouseid)
	{
		List lsResult = new ArrayList();
		EntityDAO dao = BMSService.getm_EntityDAO();
		ICargoAreaBus cargoAreaBus = new CargoAreaBusImpl(dao);
		
		List ls = null;
		try
		{
			ls = cargoAreaBus.getCargoAreaQuery(warehouseid, "");
		}catch(Exception er)
		{
			Logger.error("获得货区信息失败:" + er.getMessage());
		}
		if(ls != null)
		{
			for(int i = 0; i < ls.size(); i++)
			{
				BaseCargoarea cargoarea = (BaseCargoarea)ls.get(i);
				String strFlag = "0";
				if(cargoarea.getCargoAreaId().equals(id))
				{
					strFlag = "1";
				}
				lsResult.add(new TreeBean(cargoarea.getCargoAreaId(), cargoarea.getCargoAreaName(), strFlag));
			}
		}
		return lsResult;
	}
	
	/**
	 * 功能:获得指定库区的巷道
	 * @param id
	 * @param whAreaId
	 * @return
	 */
	public List getAlleyList(String id, String whAreaId)
	{
		List lsResult = new ArrayList();
		EntityDAO dao = BMSService.getm_EntityDAO();
		IAlleyBus alleyBus = new AlleyBusImpl(dao);
		
		List ls = null;
		try
		{
			if(whAreaId!=null && whAreaId.length()>0){
				ls = alleyBus.getAlleyQuery("", "", whAreaId);
			}
			
		}catch(Exception er)
		{
			Logger.error("获得指定库区的巷道信息失败:" + er.getMessage());
		}
		if(ls != null)
		{
			for(int i = 0; i < ls.size(); i++)
			{
				BaseAlley alley = (BaseAlley)ls.get(i);
				String strFlag = "0";
				if(alley.getCargoAlleyId().equals(id))
				{
					strFlag = "1";
				}
				lsResult.add(new TreeBean(alley.getCargoAlleyId(), alley.getCargoAlleyName(), strFlag));
			}
		}
		return lsResult;
	}
	
	/**
	 * 功能：查看是否存在该类型id
	 * @param id
	 * @return
	 */
	public boolean getTypeIdStatus(String id){
		
		EntityDAO dao = BMSService.getm_EntityDAO();
		ITypeBus typeBus = new TypeBusImpl(dao);
		BaseType type = null;
		
		try {
			type = typeBus.getTypeById(id);
			
		} catch (Exception er) {
			Logger.error("根据类型ID获得类型信息失败:" + er.getMessage());
		}
		

		if(type != null ){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 功能：查看是否存在该类型列表值
	 * @param typevalue
	 * @param selectvalue
	 * @return
	 */
	public boolean getSelectValueStatus(String typevalue, String selectvalue){
		
		EntityDAO dao = BMSService.getm_EntityDAO();
		ITypeBus typeBus = new TypeBusImpl(dao);
		List ls = null;
		
		try {
			ls = typeBus.getTypeList(typevalue, selectvalue);

		} catch (Exception er) {
			Logger.error("根据类型列表值获得类型信息失败:" + er.getMessage());
		}
		
		if(ls != null && ls.size() > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 功能:获得父类型值
	 * @param id
	 * @return
	 */
	public List getTypeParentList(String id)
	{
		List lsResult = new ArrayList();
		EntityDAO dao = BMSService.getm_EntityDAO();
		ITypeBus typeBus = new TypeBusImpl(dao);
		List ls = null;
		
		try
		{
			ls = typeBus.getTypeParentList();
		}catch(Exception er)
		{
			Logger.error("获得父类型值信息失败:" + er.getMessage());
		}
		if(ls != null)
		{
			for(int i = 0; i < ls.size(); i++)
			{
				BaseType type = (BaseType)ls.get(i);
				String strFlag = "0";
				if(type.getTypeid().equals(id))
				{
					strFlag = "1";
				}
				lsResult.add(new TreeBean(type.getTypeid(), type.getTypename(), strFlag));
			}
		}
		return lsResult;
	}
	
	/**
	 * 功能:获得所有父物品类别值
	 * @param id
	 * @return
	 */
	public List getPtTypeParentList(String id)
	{
		List lsResult = new ArrayList();
		EntityDAO dao = BMSService.getm_EntityDAO();
		IProducttypeBus producttypeBus = new ProducttypeBusImpl(dao);
		List ls = null;
		
		try
		{
			ls = producttypeBus.getProducttypeList();
		}catch(Exception er)
		{
			Logger.error("获得所有父物品类别信息失败:" + er.getMessage());
		}
		if(ls != null)
		{
			for(int i = 0; i < ls.size(); i++)
			{
				BaseProducttype pttype = (BaseProducttype)ls.get(i);
				String strFlag = "0";
				if(pttype.getPtid().equals(id))
				{
					strFlag = "1";
				}
				lsResult.add(new TreeBean(pttype.getPtid(), pttype.getPtname(), strFlag));
			}
		}
		return lsResult;
	}
	
	/**
	 * 功能:类型列表下拉框
	 * @param id
	 * @param typevalue 	类型值
	 * @return
	 */
	public List getBaseTypeList(String id, String typevalue)
	{
		List lsResult = new ArrayList();
		EntityDAO dao = BMSService.getm_EntityDAO();
		ITypeBus typeBus = new TypeBusImpl(dao);
		List ls = null;
		try
		{
			ls = typeBus.getTypeList(typevalue, "");
			
		}catch(Exception er)
		{
			Logger.error("根据类型值获得类型列表失败:" + er.getMessage());
		}
		if(ls != null)
		{
			for(int i = 0; i < ls.size(); i++)
			{
				BaseType type = (BaseType)ls.get(i);
				String strFlag = "0";
				if(type.getSelectvalue()!= null && type.getSelectvalue().length()>0)
				{
					if(type.getSelectvalue().equals(id))
					{
						strFlag = "1";
					}
					lsResult.add(new TreeBean(type.getSelectvalue(), type.getSelecttext(), strFlag));
				}
			}
		}
		
		return lsResult;
	}
	/**
	 * 功能:类型列表下拉框
	 * @param id
	 * @param typevalue 	类型值
	 * @return
	 */
	public List getBaseTypeListother(String id, String typevalue)
	{
		List lsResult = new ArrayList();
		EntityDAO dao = BMSService.getm_EntityDAO();
		ITypeBus typeBus = new TypeBusImpl(dao);
		List ls = null;
		try
		{
			ls = typeBus.getTypeList(typevalue, "");
			
		}catch(Exception er)
		{
			Logger.error("根据类型值获得类型列表失败:" + er.getMessage());
		}
		if(ls != null)
		{
			for(int i = 0; i < ls.size(); i++)
			{
				BaseType type = (BaseType)ls.get(i);
				String strFlag = "0";
				if(type.getSelectvalue()!= null && type.getSelectvalue().length()>0)
				{
					if(type.getSelectvalue().equals(id))
					{
						strFlag = "1";
					}
					if(typevalue.equals("rkdj")){
						if(type.getSelectvalue().equals("9")){
						}else{
							lsResult.add(new TreeBean(type.getSelectvalue(), type.getSelecttext(), strFlag));
						}
						
					}else{
						
					}
					
				}
			}
		}
		
		return lsResult;
	}
    
    /**
     * 功能：查询需要发货确认的出库单
     * @author hug 2012-6-14 
     * @param warehouseid   仓库ID
     * @return
     */
	public List getOutInvoiceId(String warehouseid){
        List<TreeBean> lsResult = new ArrayList<TreeBean>();
        EntityDAO dao = BMSService.getm_EntityDAO();
        IOutBoundBus outboundBus = new OutBoundBusImpl(dao);
        List<OutboundInvoiceHeader> ls = null;
        try
        {
            ls = outboundBus.getSendOutInvoice(warehouseid);
            
        }catch(Exception er)
        {
            Logger.error("查询需要发货确认的出库单失败:" + er.getMessage());
        }
        if(ls != null)
        {
            OutboundInvoiceHeader outbound = null;
            for(int i = 0; i < ls.size(); i++)
            {
                outbound = (OutboundInvoiceHeader)ls.get(i);
                lsResult.add(new TreeBean(outbound.getOutstockid(), outbound.getOutstockid(), "0"));   
            }
        }
        return lsResult;
    }
    /**
     * 功能：查询需要发货确认的B客户的出库单
     * @author hug 2012-6-14 
     * @param warehouseid   仓库ID
     * @param aoutid        A客户单据ID
     * @return
     */
    public List getBOutInvoiceId(String warehouseid, String aoutid){
        List<TreeBean> lsResult = new ArrayList<TreeBean>();
        EntityDAO dao = BMSService.getm_EntityDAO();
        IOutBoundBus outboundBus = new OutBoundBusImpl(dao);
        List<OutboundInvoiceHeader> ls = null;
        try
        {
            ls = outboundBus.getSendOutInvoice(warehouseid, aoutid);
            
        }catch(Exception er)
        {
            Logger.error("查询需要发货确认的B客户的出库单失败:" + er.getMessage());
        }
        if(ls != null)
        {
            OutboundInvoiceHeader outbound = null;
            for(int i = 0; i < ls.size(); i++)
            {
                outbound = (OutboundInvoiceHeader)ls.get(i);
                lsResult.add(new TreeBean(outbound.getOutstockid(), outbound.getOutstockid(), "0"));   
            }
        }
        return lsResult;
    }
	
	/**
	 * 功能:获得指定规则
	 * @param id
	 * @param flag 1：上架；2：分配；3：补货
	 * @return
	 */
	public List getRules(String id, String warehouseid, String flag)
	{
		List lsResult = new ArrayList();
		EntityDAO dao = BMSService.getm_EntityDAO();
		IPutawayRuleBus putawayRuleBus = new PutawayRuleImpl(dao);
		IAllocationRuleBus allocationRuleBus = new AllocationRuleImpl(dao);
		IReplenishRuleBus replenishRuleBus = new ReplenishRuleImpl(dao);
		
		List ls = null;
		try{
		
			String strFlag = "0";
			if(flag.equals("1")){
				ls = putawayRuleBus.getPutawayRuleQuery(warehouseid, "");
				
				if(ls != null){
					for(int i = 0; i < ls.size(); i++){
						strFlag = "0";
						RulePutaway putwayrule = (RulePutaway)ls.get(i);
						if(putwayrule.getPutawayid().equals(id)){
							strFlag = "1";
						}
						lsResult.add(new TreeBean(putwayrule.getPutawayid(), putwayrule.getDescr(), strFlag));
					}
				}
				
			}else if(flag.equals("2")){
				ls = allocationRuleBus.getAllocationRuleQuery(warehouseid, "");
				
				if(ls != null){
					for(int i = 0; i < ls.size(); i++){
						strFlag = "0";
						RuleAllocation allocationrule = (RuleAllocation)ls.get(i);
						if(allocationrule.getAllocationid().equals(id)){
							strFlag = "1";
						}
						lsResult.add(new TreeBean(allocationrule.getAllocationid(), allocationrule.getDescr(), strFlag));
					}
				}
				
			}else if(flag.equals("3")){
				ls = replenishRuleBus.getReplenishRulesBywhid(warehouseid);
				
				if(ls != null){
					for(int i = 0; i < ls.size(); i++){
						strFlag = "0";
						RuleReplenish replenishrule = (RuleReplenish)ls.get(i);
						if(replenishrule.getReplenishid().equals(id)){
							strFlag = "1";
						}
						lsResult.add(new TreeBean(replenishrule.getReplenishid(), replenishrule.getDescr(), strFlag));
					}
				}
			}
			
		}catch(Exception er)
		{
			Logger.error("获得指定规则的规则方式失败:" + er.getMessage());
		}
		
		return lsResult;
	}
     
	/**
	 * 功能:获得指定规则的规则方式
	 * @param id
	 * @param flag 1：上架；2：分配；3：补货
	 * @return
	 */
	public List getRuleConfig(String id, String flag)
	{
		List lsResult = new ArrayList();
		EntityDAO dao = BMSService.getm_EntityDAO();
		IRuleConfigBus ruleConfigBus = new RuleConfigImpl(dao);
		
		List ls = null;
		try
		{
			ls = ruleConfigBus.getRuleConfigByRuletype(flag);
		}catch(Exception er)
		{
			Logger.error("获得指定规则的规则方式失败:" + er.getMessage());
		}
		if(ls != null)
		{
			for(int i = 0; i < ls.size(); i++)
			{
				RuleConfig ruleConfig = (RuleConfig)ls.get(i);
				String strFlag = "0";
				if(ruleConfig.getRuleconfigid().equals(id))
				{
					strFlag = "1";
				}
				lsResult.add(new TreeBean(ruleConfig.getRuleconfigid(), ruleConfig.getDescr(), strFlag));
			}
		}
		return lsResult;
	}
	
	/**
	 * 功能:获得指定批次属性
	 * @param id
	 * @param flag
	 * @return
	 */
	public List getBaseLotList(String id)
	{
		List lsResult = new ArrayList();
		EntityDAO dao = BMSService.getm_EntityDAO();
		BaseLotDetailDaoImpl baseLot = new BaseLotDetailDaoImpl(dao);
		
		List ls = null;
		try
		{
			ls = baseLot.getLotList();
		}catch(Exception er)
		{
			Logger.error("获得批次属性失败:" + er.getMessage());
		}
		if(ls != null)
		{
			for(int i = 0; i < ls.size(); i++)
			{
				BaseLot lot = (BaseLot)ls.get(i);
				String strFlag = "0";
				if(lot.getM_strLotid().equals(id))
				{
					strFlag = "1";
				}
				lsResult.add(new TreeBean(lot.getM_strLotid(), lot.getM_strDescr(), strFlag));
			}
		}
		return lsResult;
	}
	/**
	 * 功能:获得指定批号类型
	 * @param id
	 * @param flag
	 * @return
	 */
	public List getBatchList(String id)
	{
		List lsResult = new ArrayList();
		EntityDAO dao = BMSService.getm_EntityDAO();
		BaseBatchDaoImpl basebatch = new BaseBatchDaoImpl(dao);
		
		List ls = null;
		try
		{
			ls = basebatch.getBatchList();
		}catch(Exception er)
		{
			Logger.error("获得批号类型失败:" + er.getMessage());
		}
		if(ls != null)
		{
			for(int i = 0; i < ls.size(); i++)
			{
				BaseBatch lot = (BaseBatch)ls.get(i);
				String strFlag = "0";
				if(lot.getBatchid().equals(id))
				{
					strFlag = "1";
				}
				lsResult.add(new TreeBean(lot.getBatchid(), lot.getBatchname(), strFlag));
			}
		}
		return lsResult;
	}
	/**
	 * 功能:获得指定批次属性
	 * @param id
	 * @param flag
	 * @return
	 */
	public List getBaseLotDetailList(String id)
	{
		List lsLot = new ArrayList();
		EntityDAO dao = BMSService.getm_EntityDAO();
		BaseLotDetailDaoImpl baseLot = new BaseLotDetailDaoImpl(dao);
		List ls = null;
		try
		{
			ls = baseLot.getDetailListByLotId(id);
		}catch(Exception er)
		{
			Logger.error("下拉框==>获得批次详细列表失败:" + er.getMessage());
		}
		if(ls != null)
		{
			for(int i = 0; i < ls.size(); i++)
			{
				BaseLotDetail lot = (BaseLotDetail)ls.get(i);
				String strFlag = "0";
				lsLot.add(new TreeBean(lot.getM_strAttcode(), lot.getM_strAttname(), strFlag));
			}
		}
		return lsLot;
	}
    /**
     * 功能：获得指定规则的优先顺位
     * @param ruleid 规则id
     * @param flg 1：上架规则 2：分配规则
     * @return
     * @throws Exception 
     */
    public int getRuleNextSort(String ruleid, String flg) throws Exception
    {
    	int value = 0;
    	EntityDAO dao = BMSService.getm_EntityDAO();

    	if(flg.equals("1")){		//1：上架规则
    		
    		IPutawayRuleBus putawayRuleBus = new PutawayRuleImpl(dao);
			value = putawayRuleBus.getRuleNextSort(ruleid);

    	}else if(flg.equals("2")){	//2：分配规则
    		
    		IAllocationRuleBus allocationRuleBus = new AllocationRuleImpl(dao);
    		value = allocationRuleBus.getRuleNextSort(ruleid);
    	}

        return value;
    }
    
    /**
     * 功能：根据包装ID和入/出库类型获得包装单位
     * @author hug 2012-8-20 
     * @param id
     * @param packageid     包装ID
     * @param inouttype     入/出库类型  1:入库； 2：出库
     * @return
     */
    public List getInOutUnit(String id, String packageid, String inouttype)
    {
        List lsResult = new ArrayList();
        EntityDAO dao = BMSService.getm_EntityDAO();
        IPackageMastermesgBus packageBus = new PackageMastermesgBusImpl(dao);
        
        List<BasePackageMastermesg> ls = null;
        try
        {
            ls = packageBus.getPackageMastermesgList(packageid, inouttype);
        }catch(Exception er)
        {
            Logger.error("根据包装ID和入/出库类型获得包装单位失败:" + er.getMessage());
        }
        if(ls != null)
        {
            BasePackageMastermesg packeg;
            for(int i = 0; i < ls.size(); i++)
            {
                packeg = ls.get(i);
                String strFlag = "0";
                if(id == null || id.trim().length() <1)
                {
                    id = "EA";
                }
                if(packeg.getPkgunit().equals(id))
                {
                    strFlag = "1";
                }
                lsResult.add(new TreeBean(packeg.getPkgunit(), packeg.getPkgunitdesc(), strFlag));
            }
        }
        return lsResult;
    }

//	/**
//	 * 功能：获取班次下拉列表
//	 * @param type1 出入库类型
//	 * @param num 显示数量
//	 * @return
//	 */
//	public List getShiftList(String strType, int num, String id)
//	{
//		List lsResult = new ArrayList();
//		EntityDAO dao = BMSService.getm_EntityDAO();
//		ShiftMgr shiftMgr = new ShiftMgr(dao);
//		List ls = null;
//		try
//		{
//			ls = shiftMgr.getShitList(strType, num);
//		}catch(Exception er)
//		{
//			Logger.error("获得货区信息失败:" + er.getMessage());
//		}
//		if(ls != null)
//		{
//			Shift shift = null;
//			for(int i=0; i < ls.size(); i++)
//			{
//				shift = (Shift)ls.get(i);
//				if(shift.getM_strShiftId().equals(id))
//				{
//					lsResult.add(new TreeBean(shift.getM_strShiftId(), shift.getM_strShiftName(), "1"));
//				}
//				else if((id == null || id.length()<1) && shift.getM_strIscurrent()!=null && shift.getM_strIscurrent().equals("Y"))
//				{
//					lsResult.add(new TreeBean(shift.getM_strShiftId(), shift.getM_strShiftName(), "1"));
//				}
//				else
//				{
//					lsResult.add(new TreeBean(shift.getM_strShiftId(), shift.getM_strShiftName(), "0"));
//				}
//			}
//		}
//		return lsResult;
//	}

	/**
	 * 功能:获得批次列表
	 * @param id	批次ID
	 * @return
	 */
	public List getBaseBatchList(String id)
	{
		List lsResult = new ArrayList();
		EntityDAO dao = BMSService.getm_EntityDAO();
		BaseBatchDaoImpl batchMgr = new BaseBatchDaoImpl(dao);
		List ls = null;
		try
		{
			ls = batchMgr.getBatchList();
		}catch(Exception er)
		{
			Logger.error("获得批次列表信息失败:" + er.getMessage());
		}
		if(ls != null)
		{
			for(int i = 0; i < ls.size(); i++)
			{
				BaseBatch batch = (BaseBatch)ls.get(i);
				String strFlag = "0";
				if(batch.getBatchid().equals(id))
				{
					strFlag = "1";
				}
				lsResult.add(new TreeBean(batch.getBatchid(), batch.getBatchname(), strFlag));
			}
		}
		return lsResult;
		
	}
	/**
	 * 功能:获得生产车间列表
	 * @param id	车间ID
	 * @return
	 */
	public List getWorkShopList(String id)
	{
		List lsResult = new ArrayList();
		EntityDAO dao = BMSService.getm_EntityDAO();
		Workshop workshopMgr = new Workshop();
		
		List ls = null;
		try
		{
			ls = workshopMgr.getWorkshopList(dao);
		}catch(Exception er)
		{
			Logger.error("获得车间列表信息失败:" + er.getMessage());
		}
		if(ls != null)
		{
			for(int i = 0; i < ls.size(); i++)
			{
				Workshop work = (Workshop)ls.get(i);
				String strFlag = "0";
				if(work.getM_workshopCode().equals(id))
				{
					strFlag = "1";
				}
				//lsResult.add(new TreeBean(work.getM_workshopCode(), work.getM_workshopName(), strFlag));
				lsResult.add(new TreeBean(work.getM_workshopCode(), work.getM_workshopCode(), strFlag));
			}
		}
		return lsResult;
		
	}
	/**
	 * 功能:获得部门列表
	 * @param id	部门id
	 * @return
	 */
	public List getBranchMeansList(String id)
	{
		List lsResult = new ArrayList();
		EntityDAO dao = BMSService.getm_EntityDAO();
		BranchMeans workshopMgr = new BranchMeans();
		
		List ls = null;
		try
		{
			ls = workshopMgr.getDepartmentList(dao);
		}catch(Exception er)
		{
			Logger.error("获得车间列表信息失败:" + er.getMessage());
		}
		if(ls != null)
		{
			for(int i = 0; i < ls.size(); i++)
			{
				BranchMeans work = (BranchMeans)ls.get(i);
				String strFlag = "0";
				if(work.getM_branchNumber().equals(id))
				{
					strFlag = "1";
				}
				lsResult.add(new TreeBean(work.getM_branchNumber(), work.getM_branchName(), strFlag));
			}
		}
		return lsResult;
		
	}
	/**
	 * 功能:获得物品类别列表
	 * @param id	
	 * @return
	 */	
public List getProductStatus(String value){
	List lsResult = new ArrayList();
	EntityDAO dao = BMSService.getm_EntityDAO();
	BaseType baseType = new BaseType();
	String hql = "From BaseType as baseT where baseT.typevalue='wpzt' and baseT.selectvalue ='1'";//'1',待检，'2',合格,'3',不合格
	
	List ls = null;
	try
	{
		ls = dao.searchEntities(hql);
		if(ls != null)
		{
			for(int i = 0; i < ls.size(); i++)
			{
				BaseType bt = (BaseType)ls.get(i);
				String strFlag = "0";
				if(bt.getSelectvalue().equals(value)){
					strFlag = "1";
				}
				lsResult.add(new TreeBean(bt.getSelectvalue(), bt.getSelecttext(), strFlag));
			}
		}
	}catch(Exception er)
	{
		Logger.error("获得车间列表信息失败:" + er.getMessage());
	}
	
	return lsResult;
}

//	/**
//	 * 功能：查看是否存在该批次id
//	 * @param id
//	 * @return
//	 */
//	public boolean getm_strBatchIdStatus(String id){
//		EntityDAO dao = BMSService.getm_EntityDAO();
//		String hql= " from Batch as batch where batch.m_strBatchId='" + id+"'";
//		List ls = dao.searchEntities(hql);
//		if(ls != null && ls.size() > 0){
//			return true;
//		}
//		return false;
//	}
//	/**
//	 * 获取相应的班次
//	 * @param id
//	 * @return
//	 */
//	public String getSelectValueShift(String id){
//		EntityDAO dao = BMSService.getm_EntityDAO();
//	    Shift tb = null;
//	    String ls = null;
//	    ShiftMgr shiftMgr = new ShiftMgr(dao);
//	    tb = shiftMgr.getLastInfo(dao, id);
//	    if(tb == null){
//	    ls = "甲";	
//	    }else{
//	    ls =(tb.getM_strThreenum().equals("乙")) ? "丙" : (tb.getM_strThreenum().equals("甲")) ? "乙" : "甲";
//	    }
//		return ls;
//	}
//	/**
//	 * 获取白夜班
//	 * @param id
//	 * @return
//	 */
//	public String getDayNightShift(String id){
//		EntityDAO dao = BMSService.getm_EntityDAO();
//	    Shift tb = null;
//	    String ls = null;
//	    ShiftMgr shiftMgr = new ShiftMgr(dao);
//	    tb = shiftMgr.getLastInfo(dao, id);
//	    if(tb == null){
//	    ls = "白";
//	    }else{
//	    ls =(tb.getM_strDayNight().equals("白")) ? "夜" :  "白";
//	    }
//		return ls;
//	}
//	/**
//	 * 获取相应的班次日期
//	 * @param id
//	 * @return
//	 * @throws Exception
//	 */
//	public String getDateShift(String id) throws Exception {
//		EntityDAO dao = BMSService.getm_EntityDAO();
//	    Shift tb = null;
//	    String ls = null;
//	    ShiftMgr shiftMgr = new ShiftMgr(dao);
//	    SimpleDateFormat RetDateFm2 = new SimpleDateFormat("yyyy-MM-dd");
//	    tb = shiftMgr.getLastInfo(dao, id);
//	    if(tb != null){
//	    ls = (tb.getM_strDayNight().equals("白")) ? tb.getM_strShiftDate() : 
//	        RetDateFm2.format(new Date(RetDateFm2.parse(tb.getM_strShiftDate()).getTime() + 86400000L));
//	    }else{
//	    	ls = StrTools.getCurrDateTime(8);
//	    }
//		return ls;
//	}
/**
 * 功能：获得盘点申请单id
 * @param warehouseid
 * @return
 */
public List getcheckReqLs(String warehouseid){
	List reqLs = null;
	EntityDAO dao = BMSService.getm_EntityDAO();
	List ls = new ArrayList();
	try {
		String[] timewhid = warehouseid.split("\\|");
		String createTime = timewhid[0];
		String whid = timewhid[1];
		String strHql = "select req.requestid from InventoryCheckRequest req where req.requesttime like '"+createTime+"%' and req.status!=4 and req.warehouseid='"+whid+"' order by req.requesttime";
		reqLs = dao.searchEntities(strHql);
		if (reqLs!=null) {
			for (Object checkReqId : reqLs) {
				String reqId = checkReqId.toString();
				String strFlag = "0";
				ls.add(new TreeBean(reqId, reqId, strFlag));
			}
		}
	} catch (HibernateException er) {
		Logger.error("RF根据仓库id获取盘点单出错:" + er.getMessage());
	}
	return ls;
}
/**
 * 功能：RF获取货位的排出错
 * @return
 */
public List getPlatoon(){
	List reqLs = null;
	EntityDAO dao = BMSService.getm_EntityDAO();
	List ls = new ArrayList();
	try {
		String strHql = "select distinct cspace.csplatoon from BaseCargospace cspace order by cspace.csplatoon ";
		reqLs = dao.searchEntities(strHql);
		if (reqLs!=null) {
			for (Object platoon : reqLs) {
				String reqId = platoon.toString();
				String strFlag = "0";
				ls.add(new TreeBean(reqId, reqId, strFlag));
			}
		}
	} catch (HibernateException er) {
		Logger.error("RF获取货位的排出错:" + er.getMessage());
	}
	return ls;
}
/**
 * 功能：RF获取货位的列出错
 * @return
 */
public List getColumn(){
	List reqLs = null;
	EntityDAO dao = BMSService.getm_EntityDAO();
	List ls = new ArrayList();
	try {
		
		String strHql = "select distinct cspace.cscolumn from BaseCargospace cspace order by cspace.cscolumn";
		reqLs = dao.searchEntities(strHql);
		if (reqLs!=null) {
			for (Object column : reqLs) {
				String reqId = column.toString();
				String strFlag = "0";
				ls.add(new TreeBean(reqId, reqId, strFlag));
			}
		}
	} catch (HibernateException er) {
		Logger.error("RF获取货位的列出错:" + er.getMessage());
	}
	return ls;
}
/**
 * 功能：RF获取货位的层
 * @return
 */
public List getFloor(){
	List reqLs = null;
	EntityDAO dao = BMSService.getm_EntityDAO();
	List ls = new ArrayList();
	try {
		
		String strHql = "select distinct cspace.csfloor from BaseCargospace cspace order by cspace.csfloor";
		reqLs = dao.searchEntities(strHql);
		if (reqLs!=null) {
			for (Object csfloor : reqLs) {
				String reqId = csfloor.toString();
				String strFlag = "0";
				ls.add(new TreeBean(reqId, reqId, strFlag));
			}
		}
	} catch (HibernateException er) {
		Logger.error("RF获取货位的层出错:" + er.getMessage());
	}
	return ls;
}





}
