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
 * ����: DWR��֤������Ϣ
 * @author yao 2015-3-8
 * @since WMS 3.0
 */
public class GetTool {
    private EntityDAO dao = null;   //���ݿ����DAO��
    public GetTool(){
        dao = BMSService.getm_EntityDAO();
    }
    /**
     * ���ܣ�������Ʒ��������Ʒ
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
            Logger.error("������Ʒ����("+strCode+")�����Ʒ����" + er.getMessage());
        }
        return strBackMsg.length()>0 ? strBackMsg.substring(0, strBackMsg.length()-1):strBackMsg;
    }
    
    /**
     * ���ܣ����ݲ�Ʒ��������Ʒ
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
            Logger.error("������Ʒ����("+strCode+")�����Ʒ����" + er.getMessage());
        }
        return strBackMsg.length()>0 ? strBackMsg.substring(0, strBackMsg.length()-1):strBackMsg;
    }
    
    /**
     * ���ܣ������߼�����id��òֿ�id�Ϳ���id
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
            Logger.error("�����߼�����id("+Virtualwhid+")��òֿ�id�Ϳ���id����" + er.getMessage());
        }
        return strBackMsg;
    }
    
    /**
     * ���ܣ���ð������
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
     * ���ܣ����δ�ջ���ɵ��ջ���
     * @author hug 2012-5-10 
     * @param strWarehouseId    �ֿ�ID
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
            Logger.error("���δ�ջ���ɵ��ջ���ʧ��:" + er.getMessage());
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
     * ���ܣ����Ҫ�ϼܵ��ջ���
     * @author hug 2012-5-10 
     * @param strWarehouseId    �ֿ�ID
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
            Logger.error("�����Ҫ�ϼܵ��ջ���ʧ��:" + er.getMessage());
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
     * ���ܣ���ȡû�йر���û�����ϵ���˵�
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
    				// ���뵥��,���뵥����,���뵥״̬,|���뵥��,���뵥����,���뵥״̬,|
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
     * ���ܣ���ȡû��ȷ����û�����ϵ���ⵥ
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
    				// ��ⵥ��,��ⵥ����,��ⵥ״̬,|��ⵥ��,��ⵥ����,��ⵥ״̬,|
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
     * �������������ȡ�����Ϣ
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
			Logger.error("������������["+traycode+"]��ȡ��Ӧ��ҵ��Ϣ����:" + e.getMessage());
			e.printStackTrace();
		}
    	return strBackMsg;
    }
    /**
     * �������������ȡ�ݴ���ֱ�ӳ�����ҵ
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
			Logger.error("������������["+traycode+"]��ȡ�ݴ�����ҵ����:" + e.getMessage());
			e.printStackTrace();
		}
    	return strBackMsg;
    }
    
    /**
     * �������������ȡ�ݴ���������ҵ
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
			Logger.error("������������["+traycode+"]��ȡ�ݴ�����ҵ����:" + e.getMessage());
			e.printStackTrace();
		}
    	return strBackMsg;
    }
    /**
     * ���ܣ��������������ȡһ����ⵥ����ϸ
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
    		//��Ʒ��,��������,����,��Ʒid,Ӧ������
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
     * ���ܣ��������뵥�Ż�ȡһ��������ϸ
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
    		// ���뵥��ϸid,�ƻ�����,������,��Ʒ��,��������,����,
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
     * ���ܣ��������뵥�Ż�ȡһ��������ϸ(down)
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
    		// ���뵥��ϸid,�ƻ�����,������,��Ʒ��,��������,����,
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
     * ���ܣ��������뵥�Ż�ȡһ��������ϸ(down)
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
    		// ���뵥��ϸid,�ƻ�����,������,��Ʒ��,��������,����,
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
     * ���ܣ������̵㵥�����������ȡ�̵�����
     *@author liuxh 2013-3-13
     *@param checkid �̵㵥
     *@param tray    ��������
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
					String lotnumber = iTask.getLotinfo()!=null?iTask.getLotinfo():"";//����
					String product = iTask.getProductName()!=null?iTask.getProductName():"";//��Ʒ
					double pnum = iTask.getNum();//�������
					String chectTaskid = iTask.getTaskid()!=null?iTask.getTaskid():"";;//����id
					strBackMsg ="Y"+ "," + lotnumber + "," + product + "," + String.valueOf(pnum) + "," + chectTaskid ;
			}else {
				strBackMsg ="N" + "," + "�������������ȡ��Ϣʧ�ܣ�";
			}
		} catch (Exception er) {
			strBackMsg = "�����̵㵥("+checkid+")�������루"+tray+"������̵��������";
			er.printStackTrace();
            Logger.error("�����̵㵥("+checkid+")�������루"+tray+"������̵��������" + er.getMessage());
		}
    	return strBackMsg ;
    }
    
    
    /**
     * ���ܣ��������������ȡһ�������Ѿ���ɵ��ҵ���û�з���ȷ�ϵ���ҵ
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
	    		//�ջ��ˣ���Ʒ���ƣ��������ڣ����ţ���������������������������������ҵid
	    		
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
     * ���ܣ����ݻ�λ��ȡ��λ��Ϣ
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
            Logger.error("���ݻ�λ��ȡ��λ��Ϣʧ��:" + er.getMessage());
        }
        return space;
    }
    
    /**
     * ���ܣ�����
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
            Logger.error("��ȡ������Ϣʧ��:" + er.getMessage());
        }
        return retuStr;
    }
}
