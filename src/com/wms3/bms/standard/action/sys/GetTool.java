package com.wms3.bms.standard.action.sys;

import java.util.ArrayList;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.dwr.TreeBean;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.business.base.IBaseInoutControlBus;
import com.wms3.bms.standard.business.base.IProductBus;
import com.wms3.bms.standard.business.base.IShiftconfigBus;
import com.wms3.bms.standard.business.base.impl.BaseInoutControlBusImpl;
import com.wms3.bms.standard.business.base.impl.ProductBusImpl;
import com.wms3.bms.standard.business.base.impl.ShiftconfigBusImpl;
import com.wms3.bms.standard.business.inventory.IInventoryCheckBus;
import com.wms3.bms.standard.business.inventory.impl.InventoryCheckBusImpl;
import com.wms3.bms.standard.business.job.IJobBus;
import com.wms3.bms.standard.business.job.impl.JobBusImpl;
import com.wms3.bms.standard.dao.base.IBaseWhAreaVirtualDao;
import com.wms3.bms.standard.dao.base.impl.BaseWhAreaVirtualDaoImpl;
import com.wms3.bms.standard.dao.inbound.IInboundReceiptDao;
import com.wms3.bms.standard.dao.inbound.impl.InboundReceiptDaoImpl;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BaseInoutControl;
import com.wms3.bms.standard.entity.base.BaseShiftconfig;
import com.wms3.bms.standard.entity.base.BaseWhareaVirtual;
import com.wms3.bms.standard.entity.inbound.InboundReceiptHeader;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundRequestInvoiceDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundRequestInvoiceHeader;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckTask;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.service.BMSService;

/**
 * 
 * 描述: DWR验证或获得信息
 * @author yao 2015-3-8
 * @since WMS 3.0
 */
public class GetTool {
    private EntityDAO dao = null;   //数据库操作DAO类
    public GetTool(){
        dao = BMSService.getm_EntityDAO();
    }
    /**
     * 功能：根据物品条码获得物品
     * @author hug 2012-3-14 
     * @param strCode
     * @return
     */
    public String getProductName(String strCode)
    {
        String strBackMsg = "";
        IProductBus productBus = new ProductBusImpl(dao);
        try{
            List ls = productBus.getProductByCode(strCode);
            if(ls != null){
                Object[] obj = null;
                for(int i = 0; i < ls.size(); i++){
                    obj = (Object[])ls.get(i);
                    strBackMsg += obj[0].toString() + "|" + obj[1].toString() + ",";
                }
            } 
        }catch(Exception er){
            er.printStackTrace();
            Logger.error("根据物品条码("+strCode+")获得物品出错：" + er.getMessage());
        }
        return strBackMsg.length()>0 ? strBackMsg.substring(0, strBackMsg.length()-1):strBackMsg;
    }
    
    /**
     * 功能：根据产品编码获得物品
     * @author fanll 2012-3-14 
     * @param strCode
     * @return
     */
    public String getProductInfo(String strCode)
    {
        String strBackMsg = "";
        IProductBus productBus = new ProductBusImpl(dao);
        try{
            List ls = productBus.getProductByCodeInfo(strCode);
            if(ls != null){
                Object[] obj = null;
                for(int i = 0; i < ls.size(); i++){
                    obj = (Object[])ls.get(0);
                    strBackMsg += obj[0].toString() + "|" + obj[1].toString()  + "|" + obj[2].toString() 
                    		   + "|" + obj[3].toString()  + "|" + obj[4].toString() + ",";
                }
            } 
        }catch(Exception er){
            er.printStackTrace();
            Logger.error("根据物品条码("+strCode+")获得物品出错：" + er.getMessage());
        }
        return strBackMsg.length()>0 ? strBackMsg.substring(0, strBackMsg.length()-1):strBackMsg;
    }
    
    /**
     * 功能：根据逻辑库区id获得仓库id和库区id
     * @author fanll 2012-3-14 
     * @param strCode
     * @return
     */
    public String getWhInfo(String Virtualwhid){
        String strBackMsg = "";
        IBaseWhAreaVirtualDao VirtualDao = new BaseWhAreaVirtualDaoImpl(dao);
        try{
        	BaseWhareaVirtual basewh = VirtualDao.getWhareaById(Virtualwhid);
            if(basewh != null){
            	strBackMsg = basewh.getWarehouseid() + "|" + basewh.getBelongto();
            }
        }catch(Exception er){
            er.printStackTrace();
            Logger.error("根据逻辑库区id("+Virtualwhid+")获得仓库id和库区id出错：" + er.getMessage());
        }
        return strBackMsg;
    }
    
    /**
     * 功能：获得班次设置
     * @return
     * @throws Exception 
     */
    public String getShiftconfig()
    {
    	String strBackMsg = "";
        IShiftconfigBus shiftconfigBus = new ShiftconfigBusImpl(dao);
    	BaseShiftconfig shiftconfig = shiftconfigBus.getShiftconfig();
        if(shiftconfig != null){

            strBackMsg = shiftconfig.getTimespace() + "|" + shiftconfig.getType() + "|" + shiftconfig.getInout()
            	 + "|" + shiftconfig.getOpman1() + "|" + shiftconfig.getOnduty1() + "|" + shiftconfig.getOpman2() + "|" + shiftconfig.getOnduty2()
            	 + "|" + shiftconfig.getOpman3() + "|" + shiftconfig.getOnduty3() + "|" + shiftconfig.getOpman4() + "|" + shiftconfig.getOnduty4();
        }
        return strBackMsg;
    }
    /**
     * 功能：获得未收货完成的收货单
     * @author hug 2012-5-10 
     * @param strWarehouseId    仓库ID
     * @return
     */
    public List getInBoundInvoiceId(String strWarehouseId){
        List<TreeBean> lsResult = new ArrayList<TreeBean>();
        EntityDAO dao = BMSService.getm_EntityDAO();
        IInboundReceiptDao inReceiptDao = new InboundReceiptDaoImpl(dao);
        List<InboundReceiptHeader> ls = null;
        try
        {
            ls = inReceiptDao.getNoReceiptFinish(strWarehouseId);
        }catch(Exception er)
        {
            Logger.error("获得未收货完成的收货单失败:" + er.getMessage());
        }
        if(ls != null)
        {
            for(int i = 0; i < ls.size(); i++)
            {
                InboundReceiptHeader invoice = ls.get(i);
                String strFlag = "0";
                
                lsResult.add(new TreeBean(invoice.getReinvoiceid(), invoice.getReinvoiceid(), strFlag));
            }
        }
        return lsResult;
    }
      
    /**
     * 功能：获得要上架的收货单
     * @author hug 2012-5-10 
     * @param strWarehouseId    仓库ID
     * @return
     */
    public List getPutawayInBoundInvoiceId(String strWarehouseId){
        List<TreeBean> lsResult = new ArrayList<TreeBean>();
        EntityDAO dao = BMSService.getm_EntityDAO();
        IInboundReceiptDao inReceiptDao = new InboundReceiptDaoImpl(dao);
        List<InboundReceiptHeader> ls = null;
        try
        {
            ls = inReceiptDao.getNeedPutawayInvoice(strWarehouseId);
        }catch(Exception er)
        {
            Logger.error("获得需要上架的收货单失败:" + er.getMessage());
        }
        if(ls != null)
        {
            for(int i = 0; i < ls.size(); i++)
            {
                InboundReceiptHeader invoice = ls.get(i);
                String strFlag = "0";
                
                lsResult.add(new TreeBean(invoice.getReinvoiceid(), invoice.getReinvoiceid(), strFlag));
            }
        }
        return lsResult;
    }
    /**
     * 功能：获取没有关闭且没有作废的审核单
     * @return
     * @throws Exception 
     */
    public String Getrequestvoiceid(String strWarehouseID)
    {
    	String strBackMsg = "";
    	InboundRequestInvoiceHeader header = new InboundRequestInvoiceHeader();
    	EntityDAO dao = BMSService.getm_EntityDAO();
    	List<InboundRequestInvoiceHeader> ls = header.getInboundRIHList(strWarehouseID,dao);
    	if(ls!=null && ls.size()>0){
    		for(int i =0;i<ls.size();i++){
    			header = ls.get(i);
    			if(header!=null){
    				// 申请单号,申请单类型,申请单状态,|申请单号,申请单类型,申请单状态,|
    				String Instockid = header.getInstockid()!=null?header.getInstockid():"";
    				String Invoicetypename = header.getInvoicetypename()!=null?header.getInvoicetypename():"";
    				String Instatusname = header.getInstatusname()!=null?header.getInstatusname():"";
    				strBackMsg = strBackMsg + Instockid+","+Invoicetypename+","+Instatusname+",|";
    			}
    		}
    		if(!strBackMsg.equals("")){
    			strBackMsg = strBackMsg.substring(0, strBackMsg.length()-1);
			}
    	}
        return strBackMsg;
    }
    /**
     * 功能：获取没有确定及没有作废的入库单
     * @return
     * @throws Exception 
     */
    public String Getinvoiceid(String strWarehouseID)
    {
    	String strBackMsg = "";
    	InboundHeader header = new InboundHeader();
    	EntityDAO dao = BMSService.getm_EntityDAO();
    	List<InboundHeader> ls = header.getInboundRIHList(strWarehouseID,dao);
    	if(ls!=null && ls.size()>0){
    		for(int i =0;i<ls.size();i++){
    			header = ls.get(i);
    			if(header!=null){
    				// 入库单号,入库单类型,入库单状态,|入库单号,入库单类型,入库单状态,|
    				String Instockid = header.getInstockid()!=null?header.getInstockid():"";
    				String Invoicetypename = header.getInvoicetypename()!=null?header.getInvoicetypename():"";
    				String Instatusname = header.getInstatusname()!=null?header.getInstatusname():"";
    				strBackMsg = strBackMsg + Instockid+","+Invoicetypename+","+Instatusname+",|";
    			}
    		}
    		if(!strBackMsg.equals("")){
    			strBackMsg = strBackMsg.substring(0, strBackMsg.length()-1);
			}
    	}
        return strBackMsg;
    }
    /**
     * 根据托盘条码获取入库信息
     * @param traycode
     * @return
     */
    public String GetJobInfoByTraycode(String traycode){
    	String strBackMsg = "";
    	EntityDAO dao = BMSService.getm_EntityDAO();
    	IJobBus jobBus = new JobBusImpl(dao);
    	try {
			strBackMsg = jobBus.GetJobInfoByTraycode(traycode);
		} catch (Exception e) {
			Logger.error("根据托盘条码["+traycode+"]获取相应作业信息出错:" + e.getMessage());
			e.printStackTrace();
		}
    	return strBackMsg;
    }
    /**
     * 根据托盘条码获取暂存区直接出货作业
     * @param traycode
     * @return
     */
    public String GetZCJobDetailByTraycode(String traycode, String warehouseID){
    	String strBackMsg = "";
    	EntityDAO dao = BMSService.getm_EntityDAO();
    	IJobBus jobBus = new JobBusImpl(dao);
    	try {
			strBackMsg = jobBus.getJobDetailByTraycode(traycode,warehouseID);
		} catch (Exception e) {
			Logger.error("根据托盘条码["+traycode+"]获取暂存区作业出错:" + e.getMessage());
			e.printStackTrace();
		}
    	return strBackMsg;
    }
    
    /**
     * 根据托盘条码获取暂存区回流作业
     * @param traycode
     * @return
     */
    public String GetZCHLInventoryByTraycode(String traycode, String warehouseID){
    	String strBackMsg = "";
    	EntityDAO dao = BMSService.getm_EntityDAO();
    	IJobBus jobBus = new JobBusImpl(dao);
    	try {
			strBackMsg = jobBus.GetZCHLInventoryByTraycode(traycode,warehouseID);
		} catch (Exception e) {
			Logger.error("根据托盘条码["+traycode+"]获取暂存区作业出错:" + e.getMessage());
			e.printStackTrace();
		}
    	return strBackMsg;
    }
    /**
     * 功能：根据托盘条码获取一条入库单据明细
     * @return
     * @throws Exception 
     */
    public String GetdetailByid(String invoiceid,String traycode)
    {
    	String strBackMsg = "";
    	InboundDetail detail = new InboundDetail();
    	EntityDAO dao = BMSService.getm_EntityDAO();
    	InboundDetail obj = detail.getDetailByidtray(invoiceid,traycode,dao);
    	if(obj!=null){
    		//产品名,生产日期,批号,产品id,应收数量
    		double innum =obj.getInnum();
    		String ProductName =obj.getProductName()!=null?obj.getProductName():"";
    		String Printdate =obj.getPrintdate()!=null?obj.getPrintdate():"";
    		String Lotinfo =obj.getLotinfo()!=null?obj.getLotinfo():"";
    		String productid =obj.getProductid()!=null?obj.getProductid():"";
    		String unit = obj.getPunit();
    		String unitName = obj.getM_strUnitName();
    		strBackMsg =ProductName+","+Printdate+","+Lotinfo+","+productid+","+innum+","+unit+","+unitName;
    	}
        return strBackMsg;
    }
    public double getSurplusnumByinvoiceid(String invoiceid){
    	InboundDetail detail = new InboundDetail();
    	EntityDAO dao = BMSService.getm_EntityDAO();
    	double surplusnum = detail.getSurplusnumByinvoiceid(invoiceid, dao);
    	return surplusnum;
    }
   
    
    /**
     * 功能：根据申请单号获取一条单据明细
     * @return
     * @throws Exception 
     */
    public String GetreqdetailByid(String invoiceid)
    {
    	String strBackMsg = "";
    	InboundRequestInvoiceDetail detail = new InboundRequestInvoiceDetail();
    	EntityDAO dao = BMSService.getm_EntityDAO();
    	InboundRequestInvoiceDetail obj = detail.getDetailByid(invoiceid,dao);
    	if(obj!=null){
    		// 申请单明细id,计划数量,绑定数量,产品名,生产日期,批号,
    		String Instockdetailid =obj.getInstockdetailid()!=null?obj.getInstockdetailid():"";
    		double Plannum =obj.getPlannum();
    		double Bindingnum =obj.getBindingnum();
    		String ProductName =obj.getProductName()!=null?obj.getProductName():"";
    		String Printdate =obj.getPrintdate()!=null?obj.getPrintdate():"";
    		String Lotinfo =obj.getLotinfo()!=null?obj.getLotinfo():"";
    		String productid =obj.getProductid()!=null?obj.getProductid():"";
    		String productStatus = obj.getProductStatus()!=null?obj.getProductStatus():"";
    		
    		strBackMsg =Instockdetailid+","+Plannum+","+Bindingnum+","+ProductName+","+Printdate+","+Lotinfo+","+productid+","+productStatus;
    	}
        return strBackMsg;
    }
    /**
     * 功能：根据申请单号获取一条单据明细(down)
     * @return
     * @throws Exception 
     */
    public String GetreqdetailByDownid(String invoiceid,String requestvoicedetailid)
    {
    	String strBackMsg = "";
    	InboundRequestInvoiceDetail detail = new InboundRequestInvoiceDetail();
    	EntityDAO dao = BMSService.getm_EntityDAO();
    	InboundRequestInvoiceDetail obj = detail.getDetailByDownid(invoiceid,requestvoicedetailid,dao);
    	if(obj!=null){
    		// 申请单明细id,计划数量,绑定数量,产品名,生产日期,批号,
    		String Instockdetailid =obj.getInstockdetailid()!=null?obj.getInstockdetailid():"";
    		double Plannum =obj.getPlannum();
    		double Bindingnum =obj.getBindingnum();
    		String ProductName =obj.getProductName()!=null?obj.getProductName():"";
    		String Printdate =obj.getPrintdate()!=null?obj.getPrintdate():"";
    		String Lotinfo =obj.getLotinfo()!=null?obj.getLotinfo():"";
    		String productid =obj.getProductid()!=null?obj.getProductid():"";
    		
    		strBackMsg =Instockdetailid+","+Plannum+","+Bindingnum+","+ProductName+","+Printdate+","+Lotinfo+","+productid;
    	}
        return strBackMsg;
    }
    /**
     * 功能：根据申请单号获取一条单据明细(down)
     * @return
     * @throws Exception 
     */
    public String GetreqdetailByUpid(String invoiceid,String requestvoicedetailid)
    {
    	String strBackMsg = "";
    	InboundRequestInvoiceDetail detail = new InboundRequestInvoiceDetail();
    	EntityDAO dao = BMSService.getm_EntityDAO();
    	InboundRequestInvoiceDetail obj = detail.getDetailByUpid(invoiceid,requestvoicedetailid,dao);
    	if(obj!=null){
    		// 申请单明细id,计划数量,绑定数量,产品名,生产日期,批号,
    		String Instockdetailid =obj.getInstockdetailid()!=null?obj.getInstockdetailid():"";
    		double Plannum =obj.getPlannum();
    		double Bindingnum =obj.getBindingnum();
    		String ProductName =obj.getProductName()!=null?obj.getProductName():"";
    		String Printdate =obj.getPrintdate()!=null?obj.getPrintdate():"";
    		String Lotinfo =obj.getLotinfo()!=null?obj.getLotinfo():"";
    		String productid =obj.getProductid()!=null?obj.getProductid():"";
    		
    		strBackMsg =Instockdetailid+","+Plannum+","+Bindingnum+","+ProductName+","+Printdate+","+Lotinfo+","+productid;
    	}
        return strBackMsg;
    }
    /**
     * 功能：根据盘点单和托盘条码获取盘点任务
     *@author liuxh 2013-3-13
     *@param checkid 盘点单
     *@param tray    托盘条码
     *@return
     */
    public String getCheckTaskByCheckIdAndTray(String checkid,String tray)
    {   
    	String strBackMsg = "Y";
    	IInventoryCheckBus iCheckBus = new InventoryCheckBusImpl(dao);
    	InventoryCheckTask iTask = null;
    	try {
			List<InventoryCheckTask> ls = iCheckBus.getCheckTasks(checkid, tray);
			if (ls!=null && ls.size()>0) {
					iTask = ls.get(0);
					String lotnumber = iTask.getLotinfo()!=null?iTask.getLotinfo():"";//批号
					String product = iTask.getProductName()!=null?iTask.getProductName():"";//产品
					double pnum = iTask.getNum();//库存数量
					String chectTaskid = iTask.getTaskid()!=null?iTask.getTaskid():"";;//任务id
					strBackMsg ="Y"+ "," + lotnumber + "," + product + "," + String.valueOf(pnum) + "," + chectTaskid ;
			}else {
				strBackMsg ="N" + "," + "根据托盘条码获取信息失败！";
			}
		} catch (Exception er) {
			strBackMsg = "根据盘点单("+checkid+")托盘条码（"+tray+"）获得盘点任务出错";
			er.printStackTrace();
            Logger.error("根据盘点单("+checkid+")托盘条码（"+tray+"）获得盘点任务出错：" + er.getMessage());
		}
    	return strBackMsg ;
    }
    
    
    /**
     * 功能：根据托盘条码获取一条出库已经完成的且单据没有发货确认的作业
     * @return
     * @throws Exception 
     */
    public String GetCheckUpByTray(String traycode,String warehouseid)
    {
    	String strBackMsg = "";
    	InoutJob job = new InoutJob();
    	EntityDAO dao = BMSService.getm_EntityDAO();
    	Object[] obj;
		try {
			obj = job.getJobByJobTraycode(traycode,warehouseid,dao);
			if(obj!=null){
	    		//收货人，产品名称，生产日期，批号，出库数量，分配数量，开单数量，作业id
	    		
	    		String ClientName =obj[0]!=null?obj[0].toString():"";
	    		String productName =obj[1]!=null?obj[1].toString():"";
	    		String printdate =obj[2]!=null?obj[2].toString():"";
	    		String lotinfo =obj[3]!=null?obj[3].toString():"";
	    		String outnum =obj[4]!=null?obj[4].toString():"";
	    		String fpnum =obj[5]!=null?obj[5].toString():"";
	    		String jobid =obj[6]!=null?obj[6].toString():"";
	    		
	    		strBackMsg =ClientName+","+productName+","+printdate+","+lotinfo+","+outnum+","+fpnum+","+jobid;
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
        return strBackMsg;
    }
    
    /**
     * 功能：根据货位获取货位信息
     * @return
     * @throws Exception 
     */
    public BaseCargospace GetCargospace(String platoon, String column,String floor)
    {
    	BaseCargospace space = null;
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
            String strSql0 = " from BaseCargospace bc where bc.csplatoon='"+platoon+"' and bc.cscolumn='"+column+"' and bc.csfloor='"+floor+"'";
            List lsList = dao.searchEntities(strSql0);
            if(lsList!=null && lsList.size()>0){
            	if(lsList.get(0)!=null){
            		space=(BaseCargospace)lsList.get(0);
            		return space;
            	}
            	
            }
            
        }catch(Exception er)
        {
            Logger.error("根据货位获取货位信息失败:" + er.getMessage());
        }
        return space;
    }
    
    /**
     * 功能：卡控
     * @return
     */
    public String GetInoutControl()
    {
    	String retuStr = "";
    	BaseInoutControl InoutControl = null;
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	IBaseInoutControlBus ControlBus = new BaseInoutControlBusImpl(dao);
			InoutControl = ControlBus.getInoutControl();
			if(InoutControl != null){
				retuStr = InoutControl.getCon_type();
				return retuStr;
			}
			
        }catch(Exception er)
        {
            Logger.error("获取卡控信息失败:" + er.getMessage());
        }
        return retuStr;
    }
}
