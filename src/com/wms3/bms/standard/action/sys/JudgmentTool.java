package com.wms3.bms.standard.action.sys;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.business.base.impl.BatchBusImpl;
import com.wms3.bms.standard.business.inventory.impl.InventoryBusImpl;
import com.wms3.bms.standard.business.job.IJobBus;
import com.wms3.bms.standard.business.job.impl.JobBusImpl;
import com.wms3.bms.standard.dao.base.IBaseCargoSpaceDao;
import com.wms3.bms.standard.dao.base.IBaseProductDao;
import com.wms3.bms.standard.dao.base.impl.BaseCargoSpaceDaoImpl;
import com.wms3.bms.standard.dao.base.impl.BaseProductDaoImpl;
import com.wms3.bms.standard.dao.job.IJobDao;
import com.wms3.bms.standard.dao.job.impl.JobDaoImpl;
import com.wms3.bms.standard.dao.outbound.IOutboundDao;
import com.wms3.bms.standard.dao.outbound.IReviewDao;
import com.wms3.bms.standard.dao.outbound.impl.OutboundDaoImpl;
import com.wms3.bms.standard.dao.outbound.impl.ReviewDaoImpl;
import com.wms3.bms.standard.entity.base.BaseBatch;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BaseProduct;
import com.wms3.bms.standard.entity.inbound.lxyy.BindingRecord;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundRequestInvoiceDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundRequestInvoiceHeader;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;
import com.wms3.bms.standard.service.BMSService;

/**
 * 
 * ����: DWR��֤�ж�
 * @author yao 2015-5-15
 * @since WMS 3.0
 */
public class JudgmentTool {
    
    /**
     * ���ܣ�����������֤
     * @param strTrayCode   ��������
     * @param session
     * @return
     */
    public String isTrayCodeUse(String strTrayCode, HttpSession session)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
            String strSql = "select count(job.jobid) from InoutJob as job where job.traycode='" + strTrayCode + "' and job.status in('1','2','3') and job.inOutType in('0','1')";
            int iCount = dao.searchEntitieCount(strSql);
            if(iCount > 0)
            {
                strMsg = "δ�����ҵ����ʹ�ø��������룬�޷��󶨣�";
                return strMsg;
            }
            
            //��֤�ֿ����Ƿ��и���������
            String strSql1 = "select count(st.inventoryid) from InventoryStorage as st where st.traycode='" + strTrayCode + "'";
            iCount = dao.searchEntitieCount(strSql1);
            if(iCount > 0)
            {
            	strMsg = "���������ʹ�ø��������룬�޷��󶨣�";
                return strMsg;
            }
            
        }catch(Exception er)
        {
            strMsg = "����������֤ʧ��!��������������Ƿ����!";
            Logger.error("����������֤ʧ��:" + er.getMessage());
        }
        return strMsg;
    }
    /**
     * ���ܣ��������������ջ�
     * @param strTrayCode   ��������
     * @param invoiceid     ��ⵥ��
     * @return
     */
    public String isRecebyTrayCode(String invoiceid,String strTrayCode)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	//��ֹͬһ�����ظ��ջ�
            //String strSql0 = "select ta.instockid from InboundHeader as ta,InboundDetail as tb where tb.traycode='" + strTrayCode + "' and tb.status in('1') and ta.instockid=tb.instockid and ta.instockid='"+invoiceid+"' order by ta.instockid desc";
        	String strSql0 = "select ta.instockid from InboundHeader as ta,InboundDetail as tb where tb.traycode='" + strTrayCode + "' and tb.status in('1') and ta.instockid=tb.instockid and ta.instockid='"+invoiceid+"' order by ta.instockid desc";
        	List lsList = dao.searchEntities(strSql0);
            if(lsList!=null && lsList.size()==1){	
            }else{
            	 strMsg = "�����ظ��ջ�!";
            }
            
        }catch(Exception er)
        {
            strMsg = "�������������ջ�ʧ��!�������������!";
            Logger.error("����������֤ʧ��:" + er.getMessage());
        }
        return strMsg;
    }
    /**
     * ���ܣ��жϸõ����Ƿ����ڸòֿ�
     * @param invoiceid     ��ⵥ��
     * @return
     */
    public String isToWarehouseid(String invoiceid,String warehouseid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
            String strSql0 = "select * from OutboundInvoiceHeader as ta where ta.outstockid='" + invoiceid + "' and ta.warehouseid='"+warehouseid+"'  and ta.outstatus !='8' ";
            List lsList = dao.searchEntities(strSql0);
            if(lsList!=null && lsList.size()==1){	
            	
            }else{
            	 strMsg = "�õ��ݲ����ڸòֿ� �� �����Ѿ�����!";
            }
            
        }catch(Exception er)
        {
            strMsg = "�жϸõ����Ƿ����ڸòֿ�!";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * ���ܣ��ж�������Ϣ�Ƿ������������������õ����γ���һ��
     * @param invoiceid     ��ⵥ��
     * @return
     */
    public String isLotInfoLength(String lotid,String lotinfo)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        BatchBusImpl batchBus = new BatchBusImpl(dao);
        try
        {
        	BaseBatch batch = batchBus.getBatchById(lotid);
        	if(batch!=null && batch.getBatchlength()!=null){
        		if(batch.getBatchlength().intValue() == lotinfo.length()){
        			
        		}else{
        			strMsg = "����������Ϣ����Ӧ��Ϊ"+batch.getBatchlength().intValue()+"!";
        		}
        	}
            
        }catch(Exception er)
        {
            strMsg = "�жϸõ����Ƿ����ڸòֿ�!";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
	/**
	 * ������ҵ
	 * @param jobids
	 * @return
	 */
    public String cancelJobs(String jobid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        { 
        	InoutJob job = new InoutJob().getJobByJobid(jobid,dao);
        	if(job!=null){
        		 if(job.getStatus().equals("1") || job.getStatus().equals("2")){
        		 }else{
        			 strMsg = "ֻ��δִ�л��ߴ�ִ����ҵ�ſ�������";
        		 }
        	}else{
        		strMsg = "û���ҵ�����ҵ";
        	}
            
        }catch(Exception er)
        {
            strMsg = "������ҵ"+jobid+"��֤ʧ��!";
            Logger.error(strMsg+ er.getMessage());
        }
        return strMsg;
    }
	/**
	 * ������ҵ��֤
	 * @param jobids
	 * @return
	 */
    public String CheckJobs(String jobid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        { 
        	InoutJob job = new InoutJob().getJobByJobid(jobid,dao);
        	InoutJobdetail detail = new InoutJobdetail().getJobDetailByJobid(jobid,dao);
        	if(job!=null){
        		 if(!job.getStatus().equals("4")){
        			 strMsg = "ֻ�������ҵ�ſ��Ը���";
        		 }else if(job.getStatus().equals("4")){
        			 if(detail!=null && detail.getIsreview()!=null && detail.getIsreview().equals("Y")){
        				 strMsg = "����ҵ�Ѿ��ɹ����� �����ٴθ���";
        			 } 
        		 }
        	}else{
        		strMsg = "û���ҵ�����ҵ";
        	}
        	
        }catch(Exception er)
        {
            strMsg = "������ҵ"+jobid+"��֤ʧ��!";
            Logger.error(strMsg+ er.getMessage());
        }
        return strMsg;
    }
    /**
	 * ��ʼ�������ҵ
	 * @param jobids
	 * @return
	 */
    public String initializeJobs(String jobid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        { 
        	InoutJob job = new InoutJob().getJobByJobid(jobid,dao);
        	if(job!=null){
        		 if(!job.getStatus().equals("3")){
        			 strMsg = "ֻ����ִ�������ҵ�ſ��Գ�ʼ��";
        		 }
        	}else{
        		strMsg = "û���ҵ�����ҵ";
        	}
            
        }catch(Exception er)
        {
            strMsg = "��ʼ�������ҵ"+jobid+"��֤ʧ��!";
            Logger.error(strMsg+ er.getMessage());
        }
        return strMsg;
    }
	/**
	 * �ƿ⵽�ݴ���֤
	 * @param jobids
	 * @return
	 */
    public String WaitJobs(String jobid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        { 
        	InoutJob job = new InoutJob().getJobByJobid(jobid,dao);
        	if(job!=null){
        		 if(job.getStatus().equals("1") && job.getInvoicetype().equals("1")){
        		 }else{
        			 strMsg = "��δִ�������ҵ�����ƿ⵽�ݴ�";
        		 }
        	}else{
        		strMsg = "û���ҵ�����ҵ";
        	}
            
        }catch(Exception er)
        {
            strMsg = "��ҵ��Ϊ"+jobid+"�ƿ⵽�ݴ���֤ʧ��!";
            Logger.error(strMsg+ er.getMessage());
        }
        return strMsg;
    }
	/**
	 * �����ҵ
	 * @param jobids
	 * @return
	 */
    public String finishJobs(String jobid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        { 
        	InoutJob job = new InoutJob().getJobByJobid(jobid,dao);
        	if(job!=null){
//        		 if(!job.getStatus().equals("3")){
//        			 strMsg = "ֻ����ִ�е���ҵ�������";
//        		 }
	       		 if(job.getStatus().equals("4")){
	   			 strMsg = "��ѡ���������ɵ���ҵ��";
	       		 }
	       		 if(job.getStatus().equals("5")){
	   			 strMsg = "��ѡ��������ϵ���ҵ��";
	       		 }
	       		 if(job.getStatus().equals("6")){
	   			 strMsg = "��ѡ������쳣��ҵ!!";
	       		 }
        	}else{
        		strMsg = "û���ҵ�����ҵ";
        	}
            
        }catch(Exception er)
        {
            strMsg = "�����ҵ"+jobid+"��֤ʧ��!";
            Logger.error(strMsg+ er.getMessage());
        }
        return strMsg;
    }
    
    /**
     * ����:����ȷ�ϣ��������
     * @param strOutId      ���ⵥ�ݺ�
     * @return
     */
    public String sendCheck(String strOutId)
    {
        String strMsg = "";
        EntityDAO dao = BMSService.getm_EntityDAO();
        IJobDao jobDAO = new JobDaoImpl(dao);
        IOutboundDao outBoundDAO = new OutboundDaoImpl(dao);
        try
        {
            //���õ���δ��ɵ���ҵ��(��Ϊ4:��ɺ�5:����)
            int iJob = jobDAO.getUnfinishedJob(strOutId, null);
            int iJobFH = jobDAO.getUncheckUpJob(strOutId, null);
            if(iJob != 0)
            {
                strMsg += "�õ����С�" + iJob + "������ҵδ��ɣ�\r\r";
            }
            if(iJobFH != 0)
            {
                strMsg += "�õ����С�" + iJobFH + "������ҵδ���ˣ�\r\r";
            }
            //��ⵥ����ϸ�Ŀ��������Ƿ����
            List lsDetail = outBoundDAO.getOutBoundDetailsById(strOutId);
            if(lsDetail != null && lsDetail.size()>0)
            {
                OutboundInvoiceDetail outDetail = null;
                for(int i = 0; i < lsDetail.size(); i++)
                {
                    outDetail = (OutboundInvoiceDetail)lsDetail.get(i);
                    if(!outDetail.getLinestatus().trim().equals("7"))//��Ϊȷ��״̬
                    {
                        //�������� - �������
                        //double iNum = outDetail.getInvoicenum() - outDetail.getPicknum();
                    	double iNum = outDetail.getInvoicenum() - outDetail.getAssignnum();
                        //����������ȥ���������
                        if(iNum > 0)
                        {
                            strMsg += "��Ʒ��" + outDetail.getM_strProductName() + "���ȿ��������١�" + iNum + "����\r";
                        }else if(iNum < 0)
                        {
                            strMsg += "��Ʒ��" + outDetail.getM_strProductName() + "���ȿ��������ࡾ" + (-iNum) + "����\r";
                        }   
                        
                    }
                }
            }
            
            if(strMsg == "")
            {
                strMsg = "Y";
            }
            else
            {
                strMsg = strMsg.substring(0, strMsg.length()-1);
            }
            
        }catch(Exception er)
        {
            Logger.error("�������ʧ��:" + er.getMessage());
        }
        
        return strMsg;
    }
    /**
     * ����:����ȷ�ϣ��������
     * @param strOutId      ���ⵥ�ݺ�
     * @return
     */
    public String sendCheck1(String strOutId)
    {
        String strMsg = "";
        EntityDAO dao = BMSService.getm_EntityDAO();
        IJobDao jobDAO = new JobDaoImpl(dao);
        IOutboundDao outBoundDAO = new OutboundDaoImpl(dao);
        try
        {
            //���õ���δ��ɵ���ҵ��(��Ϊ4:��ɺ�5:����)
            int iJob = jobDAO.getUnfinishedJob(strOutId, null);
            int iJobFH = jobDAO.getUncheckUpJob(strOutId, null);
            if(iJob != 0)
            {
                strMsg += "�õ����С�" + iJob + "������ҵδ��ɣ�\r\r";
            }
            if(iJobFH != 0)
            {
                strMsg += "�õ����С�" + iJobFH + "������ҵδ���ˣ�\r\r";
            }
            if(strMsg == "")
            {
                strMsg = "Y";
            }
            else
            {
                strMsg = strMsg.substring(0, strMsg.length()-1);
            }
            
        }catch(Exception er)
        {
            Logger.error("�������ʧ��:" + er.getMessage());
        }
        
        return strMsg;
    }
    /**
     * ����:����ȷ�ϣ��������
     * @param strOutId      ���ⵥ�ݺ�
     * @return
     */
    public String sendCheck2(String strOutId)
    {
        String strMsg = "";
        EntityDAO dao = BMSService.getm_EntityDAO();
        IJobDao jobDAO = new JobDaoImpl(dao);
        IOutboundDao outBoundDAO = new OutboundDaoImpl(dao);
        try
        {
            //��ⵥ����ϸ�Ŀ��������Ƿ����
            List lsDetail = outBoundDAO.getOutBoundDetailsById(strOutId);
            if(lsDetail != null && lsDetail.size()>0)
            {
                OutboundInvoiceDetail outDetail = null;
                for(int i = 0; i < lsDetail.size(); i++)
                {
                    outDetail = (OutboundInvoiceDetail)lsDetail.get(i);
                    if(!outDetail.getLinestatus().trim().equals("7"))//��Ϊȷ��״̬
                    {
                        //�������� - �������
                        //double iNum = outDetail.getInvoicenum() - outDetail.getPicknum();
                    	double iNum = outDetail.getInvoicenum() - outDetail.getAssignnum();
                        //����������ȥ���������
                        if(iNum > 0)
                        {
                            strMsg += "��Ʒ��" + outDetail.getM_strProductName() + "���ȿ��������١�" + iNum + "����\r";
                        }else if(iNum < 0)
                        {
                            strMsg += "��Ʒ��" + outDetail.getM_strProductName() + "���ȿ��������ࡾ" + (-iNum) + "����\r";
                        }   
                        
                    }
                }
            }
            
            if(strMsg == "")
            {
                strMsg = "Y";
            }
            else
            {
                strMsg = strMsg.substring(0, strMsg.length()-1);
            }
            
        }catch(Exception er)
        {
            Logger.error("�������ʧ��:" + er.getMessage());
        }
        
        return strMsg;
    }
    /**
     * ����:����ȷ�ϣ��������
     * @param strOutId      ���ⵥ�ݺ�
     * @param strOutDetailId    ���ⵥ����ϸID
     * @return
     */
    public String sendOneCheck(String strOutId, String strOutDetailId)
    {
        String strMsg = "";
        EntityDAO dao = BMSService.getm_EntityDAO();
        IJobDao jobDAO = new JobDaoImpl(dao);
        IOutboundDao outBoundDAO = new OutboundDaoImpl(dao);
        try
        {
            //��ⵥ����ϸ�Ŀ��������Ƿ����
            OutboundInvoiceDetail outDetail = outBoundDAO.getOutBoundDetailById(strOutDetailId);
            if(outDetail != null && !outDetail.getLinestatus().trim().equals("7"))//��Ϊȷ��״̬
            {
                //���õ���δ��ɵ���ҵ��(��Ϊ4:��ɺ�5:����)
                int iJob = jobDAO.getUnfinishedJob(strOutId, strOutDetailId);
                int iJobFH = jobDAO.getUncheckUpJob(strOutId, null);
                if(iJob != 0)
                {
                    strMsg += "�õ����С�" + iJob + "������ҵδ��ɣ�\r\r";
                }
                if(iJobFH != 0)
                {
                    strMsg += "�õ����С�" + iJob + "������ҵδ���ˣ�\r\r";
                }
                //�������� - �������
               // double iNum = outDetail.getInvoicenum() - outDetail.getPicknum();
                //�������� - ��������
                double iNum = outDetail.getInvoicenum() - outDetail.getAssignnum();
                 //����������ȥ���������
                 if(iNum > 0)
                 {
                        strMsg += "��Ʒ��" + outDetail.getM_strProductName() + "���ȿ��������١�" + iNum + "����\r";
                 }else if(iNum < 0)
                 {
                         strMsg += "��Ʒ��" + outDetail.getM_strProductName() + "���ȿ��������ࡾ" + (-iNum) + "����\r";
                 }   
                        
            }

            if(strMsg == "")
            {
                strMsg = "Y";
            }
            else
            {
                strMsg = strMsg.substring(0, strMsg.length()-1);
            }
            
        }catch(Exception er)
        {
            Logger.error("�������ʧ��:" + er.getMessage());
        }
        
        return strMsg;
    }
    /**
     * ����:����ȷ�ϣ��������
     * @param strOutId      ���ⵥ�ݺ�
     * @param strOutDetailId    ���ⵥ����ϸID
     * @return
     */
    public String sendOneCheck1(String strOutId, String strOutDetailId)
    {
        String strMsg = "";
        EntityDAO dao = BMSService.getm_EntityDAO();
        IJobDao jobDAO = new JobDaoImpl(dao);
        IOutboundDao outBoundDAO = new OutboundDaoImpl(dao);
        try
        {
            //��ⵥ����ϸ�Ŀ��������Ƿ����
            OutboundInvoiceDetail outDetail = outBoundDAO.getOutBoundDetailById(strOutDetailId);
            if(outDetail != null && !outDetail.getLinestatus().trim().equals("7"))//��Ϊȷ��״̬
            {
                //���õ���δ��ɵ���ҵ��(��Ϊ4:��ɺ�5:����)
                int iJob = jobDAO.getUnfinishedJob(strOutId, strOutDetailId);
                int iJobFH = jobDAO.getUncheckUpJob(strOutId, null);
                if(iJob != 0)
                {
                    strMsg += "�õ����С�" + iJob + "������ҵδ��ɣ�\r\r";
                }
                if(iJobFH != 0)
                {
                    strMsg += "�õ����С�" + iJob + "������ҵδ���ˣ�\r\r";
                }             
            }

            if(strMsg == "")
            {
                strMsg = "Y";
            }
            else
            {
                strMsg = strMsg.substring(0, strMsg.length()-1);
            }  
        }catch(Exception er)
        {
            Logger.error("�������ʧ��:" + er.getMessage());
        }
        
        return strMsg;
    }
    /**
     * ����:����ȷ�ϣ��������
     * @param strOutId      ���ⵥ�ݺ�
     * @param strOutDetailId    ���ⵥ����ϸID
     * @return
     */
    public String sendOneCheck2(String strOutId, String strOutDetailId)
    {
        String strMsg = "";
        EntityDAO dao = BMSService.getm_EntityDAO();
        IJobDao jobDAO = new JobDaoImpl(dao);
        IOutboundDao outBoundDAO = new OutboundDaoImpl(dao);
        try
        {
            //��ⵥ����ϸ�Ŀ��������Ƿ����
            OutboundInvoiceDetail outDetail = outBoundDAO.getOutBoundDetailById(strOutDetailId);
            if(outDetail != null && !outDetail.getLinestatus().trim().equals("7"))//��Ϊȷ��״̬
            {
                //�������� - ��������
                double iNum = outDetail.getInvoicenum() - outDetail.getAssignnum();
                 //����������ȥ���������
                 if(iNum > 0)
                 {
                        strMsg += "��Ʒ��" + outDetail.getM_strProductName() + "���ȿ��������١�" + iNum + "����\r";
                 }else if(iNum < 0)
                 {
                         strMsg += "��Ʒ��" + outDetail.getM_strProductName() + "���ȿ��������ࡾ" + (-iNum) + "����\r";
                 }   
                        
            }

            if(strMsg == "")
            {
                strMsg = "Y";
            }
            else
            {
                strMsg = strMsg.substring(0, strMsg.length()-1);
            }
            
        }catch(Exception er)
        {
            Logger.error("�������ʧ��:" + er.getMessage());
        }
        
        return strMsg;
    }
    /**
     * ���ܣ��ж϶����Ƿ񸴺ˣ�1:�����ݸ���; 2:���������ҵ���ˣ�
     * @author hug 2012-10-12 
     * @param orderId
     * @param flag
     * @return
     */
    public int isOrderChecked(String orderId, String flag)
    {
        EntityDAO dao = BMSService.getm_EntityDAO();
        IReviewDao reviewDao = new ReviewDaoImpl(dao);
        boolean result = false;
        try{
            if(flag!=null && flag.equals("header"))
            {
                result = reviewDao.isCheckedByHeader(orderId, "1");
            }
            if(flag!=null && flag.equals("detail"))
            {
                result = reviewDao.isCheckedByHeader(orderId, "2");
            }
        }catch(Exception er)
        {
            Logger.error("�ж϶����Ƿ񸴺ˣ�1:�����ݸ���; 2:���������ҵ���ˣ�ʧ�ܣ�" + er.getMessage());
        }
        if(result)
        {
            return 1;
        }
        else
        {
            return 0;
        }   
    }
    /**
     * ���ܣ�������==>����������==>������뵥����==>�����֤��ֻ���½�״̬�ſ�����ˣ�
     * @param instockid   ���뵥��
     * @param strUserCode   �����
     * @return
     */
    public String isAudit(String instockid,String strUserCode)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	InboundRequestInvoiceHeader header = new InboundRequestInvoiceHeader();
        	header = header.getDaoQueryHql(instockid,dao);
        	if(header!=null && header.getInstatus()!=null && header.getInstatus().equals("1")){ //�½�
        		strMsg = "Y";
        	}else{
        		strMsg = "ֻ���½�״̬�ſ������";
        	}
        	if(strUserCode==null || strUserCode.equals("")){
        		strMsg = "��½�ѹ�ʱ �����µ�½�û�";
        	}else{
        		if(header!=null && header.getCreatemanid()!=null){
            		if(header.getCreatemanid().equals(strUserCode)&&!strUserCode.equals("root")){
            			strMsg = "�Ƶ���������˲���Ϊͬһ��";
            		}
            	}
        	}
        	
            
        }catch(Exception er)
        {
            strMsg = "������==>����������==>������뵥����==>�����֤ʧ��";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * ���ܣ�RF-�󶨣���Ʒ���ֻ����˼���״̬�ĵ��ݲſ����ٴΰ󶨣����ǳ�Ʒ���ֻ���½�����״̬�ĵ��ݲſ����ٴΰ󶨣�
     * @param instockid   ���뵥��
     * @return
     */
    public String isAuditAbind(String instockid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	InboundRequestInvoiceHeader header = new InboundRequestInvoiceHeader();
        	header = header.getDaoQueryHql(instockid,dao);
        	if(header!=null && header.getInstatus()!=null && (((header.getInvoicetypeid().equals("9") && header.getInstatus().equals("2")||header.getInstatus().equals("3")))||(!header.getInvoicetypeid().equals("9") && (header.getInstatus().equals("1")||header.getInstatus().equals("3"))))){ //����Ʒ���ֻ����˼���״̬�ĵ��ݲſ����ٴΰ󶨣����ǳ�Ʒ���ֻ���½�����״̬�ĵ��ݲſ����ٴΰ󶨣�
        		strMsg = "Y";
        	}else{
        		strMsg = "ֻ����˼���״̬�ĵ��ݲſ����ٴΰ�";
        	}
            
        }catch(Exception er)
        {
            strMsg = "RF-��ʧ��";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * ���� ȡ������֤
     * @param bangrecordid   �󶨼�¼id
     * @return
     */
    public String isCancelbind(String bangrecordid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	BindingRecord record = new BindingRecord();
        	record = record.getQueryHQLByRecordid(bangrecordid,dao);
        	if(record!=null && record.getStatus()!=null && record.getStatus().equals("1")){ // 1,δ���ɵ���
        		strMsg = "Y";
        	}else{
        		strMsg = "ֻ��δ���ɵ��ݵİ󶨼�¼�ſ���ȡ����";
        	}
            
        }catch(Exception er)
        {
            strMsg = "ȡ����ʧ��";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * ���� ��֤�Ƿ�Ϊ�����������ҵ
     * @param jobid   ��ҵid
     * @return
     */
    public String isTaskJob(String jobid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	if(jobid != null && !jobid.equals("")){
        			String strSql = " from InoutJob as t where t.jobid='" + jobid + "' and t.jobcategory='1'"; // 1���� �������ҵ
        			List ls = dao.searchEntities(strSql);
        			if(ls != null && ls.size() > 0){
        				InoutJob job = (InoutJob)ls.get(0);
        				/*if(job.getInvoicetype().equals("4")){ //���Ϊ�ƿ���ҵ �ж� �ݴ����Ƿ��и�ͬ�����������룬����� ����ʾ �Ҳ��ɹ������� �ɹ�	
        				}*/
        			}else{
        				strMsg = "����ҵ�����������ҵ";
        			}
    			
    		}else{
    			strMsg = "����ҵ��Ϊ��";
    		}
            
        }catch(Exception er)
        {
            strMsg = "��֤�Ƿ�Ϊ�����������ҵʧ��";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * ���� ��֤�Ƿ�Ϊ�ǵ����������ҵ������ �ݴ�����ҵ��
     * @param jobid   ��ҵid
     * @return
     */
    public String isNotTaskJob(String jobid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	if(jobid != null && !jobid.equals("")){
    			String strSql = " from InoutJob as t where t.jobid='" + jobid + "' and t.jobcategory='2'"; // 2���� �ݴ�����ҵ
    			List ls = dao.searchEntities(strSql);
    			if(ls != null && ls.size() > 0){	
    			}else{
    				strMsg = "����ҵ�����ݴ�����ҵ";
    			}
    		}else{
    			strMsg = "����ҵ��Ϊ��";
    		}
            
        }catch(Exception er)
        {
            strMsg = "��֤�Ƿ�Ϊ�ǵ����������ҵʧ��";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * ���ܣ�������==>����������==>������뵥����==>ȡ�������֤��ֻ�����״̬�ſ���ȡ����ˣ�
     * @param instockid   ���뵥��
     * @return
     */
    public String isCancelAudit(String instockid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	InboundRequestInvoiceHeader header = new InboundRequestInvoiceHeader();
        	header = header.getDaoQueryHql(instockid,dao);
        	if(header!=null && header.getInstatus()!=null && header.getInstatus().equals("2")){ //���
        		strMsg = "Y";
        	}else{
        		strMsg = "ֻ�����״̬�ſ���ȡ�����";
        	}
            
        }catch(Exception er)
        {
            strMsg = "������==>����������==>������뵥����==>ȡ�������֤ʧ��";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * ���ܣ�������==>����������==>������뵥����==>ȡ�������֤��ֻ�����״̬�ſ���ȡ����ˣ�
     * @param instockid   ���뵥��
     * @return
     */
    public String isCancelReceive(String instockid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	InboundRequestInvoiceHeader header = new InboundRequestInvoiceHeader();
        	header = header.getDaoQueryHql(instockid,dao);
        	if(header!=null && header.getInstatus()!=null && header.getInstatus().equals("2")){ //���
        		strMsg = "Y";
        	}else{
        		strMsg = "ֻ�����״̬�ſ���ȡ�����";
        	}
            
        }catch(Exception er)
        {
            strMsg = "������==>����������==>������뵥����==>ȡ�������֤ʧ��";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * ���ܣ�������==>����������==>������뵥����==>������֤��ֻ���½�״̬�ſ������ϣ�
     * @param instockid   ���뵥��
     * @return
     */
    public String isDeleteAudit(String instockid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	InboundRequestInvoiceHeader header = new InboundRequestInvoiceHeader();
        	header = header.getDaoQueryHql(instockid,dao);
        	if(header!=null && header.getInstatus()!=null && header.getInstatus().equals("1")){ //�½�
        		strMsg = "Y";
        	}else{
        		strMsg = "ֻ���½�״̬�ſ�������";
        	}
            
        }catch(Exception er)
        {
            strMsg = "������==>����������==>������뵥����==>������֤ʧ��";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * ���ܣ�������==>����������==>��ⵥ����==>������֤��ֻ���½�״̬�ſ������ϣ�
     * @param instockid   ���뵥��
     * @return
     */
    public String isDelete(String instockid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	InboundHeader header = new InboundHeader();//
        	header = header.getDaoQueryHql(instockid,dao);
        	if(header!=null && header.getInstatus()!=null && header.getInstatus().equals("1")){ //�½�
        		strMsg = "Y";
        	}else{
        		strMsg = "ֻ���½�״̬�ſ�������";
        	}
            
        }catch(Exception er)
        {
            strMsg = "������==>����������==>��ⵥ����==>������֤ʧ��";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * ���ܣ�������==>����������==>������뵥����==>�ر����뵥��֤��ֻ�а�״̬�ſ��Թر�ȷ�ϣ�
     * @param instockid   ���뵥��
     * @return
     */
    public String isColseAudit(String instockid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	InboundRequestInvoiceHeader header = new InboundRequestInvoiceHeader();
        	header = header.getDaoQueryHql(instockid,dao);
        	if(header!=null && header.getInstatus()!=null && header.getInstatus().equals("3")){ //��
        		strMsg = "Y";
        	}else{
        		strMsg = "ֻ�а�״̬�ſ��Թر�ȷ��";
        	}
            
        }catch(Exception er)
        {
            strMsg = "������==>����������==>������뵥����==>�ر����뵥��֤ʧ��";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * ���ܣ�������==>����������==>��ⵥ����==>ȷ����֤��������Ӧ����ʾ��
     * @param instockid   ���ݺ�
     * @return
     */
    public String isRKDYZ(String instockid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	InboundHeader header = new InboundHeader();
        	header = header.getDaoQueryHql(instockid,dao);
        	
        	if(header!=null&&header.getInstatus()!=null){
        		if(header.getInstatus().equals("3")){
        			strMsg = "�õ����Ѿ�ȷ�� �����ڽ���ȷ��";
        			return strMsg;
        		}else if(header.getInstatus().equals("4")){
        			strMsg = "�õ����Ѿ����� �����ٽ���ȷ��";
        			return strMsg;
        		}	
        	}
        	List ls = new InboundDetail().getQueryNotSJHQLByid(instockid, dao);
    		if(ls!=null && ls.size()>0){
    			strMsg = strMsg+" ����"+ls.size()+"����ҵû���ϼ���� ���ܽ���ȷ��";
    			return strMsg;
    		}	
            
        }catch(Exception er)
        {
            strMsg = "������==>����������==>��ⵥ����==>ȷ����֤��������Ӧ����ʾ��";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * ���ܣ�������==>����������==>��ⵥ����==>ȷ����֤��������Ӧ����ʾ��
     * @param instockid   ���ݺ�
     * @return
     */
    public String isRKDconfirm(String instockid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	InboundDetail detail = new InboundDetail();
        	List lsList = detail.getQueryHQLByid(instockid, dao);
        	int j=0;
        	if(lsList!=null && lsList.size()>0){
        		for(int i=0;i<lsList.size();i++){
        			detail = (InboundDetail)lsList.get(0);
        			if(detail!=null && detail.getInnum()!=detail.getGetnum() && detail.getStatus().equals("4")){ //�ϼ���� ���������ʵ��������һ�� ��ʾ����
        				strMsg = strMsg+"��idΪ"+detail.getBandrecordid()+"�ϼ������ҵ����Ʒ�� "+detail.getProductName()+" ������Ϊ"+detail.getInnum()+" �ϼ�����Ϊ"+detail.getGetnum()+"\r\r";
        				j++;
        			}
        		}
        		
        		if(j>0){
        			strMsg = strMsg+" �Ƿ�ȷ�ϸõ���";
        			strMsg = strMsg.substring(1, strMsg.length());
        		}
        	}  
        }catch(Exception er)
        {
            strMsg = "������==>����������==>��ⵥ����==>ȷ����֤��������Ӧ����ʾ��";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * ȡ���ջ���֤
     * @param instockdetailid
     * @return
     */
    public String isReceipt(String instockdetailid)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	InboundDetail detail = new InboundDetail();
        	detail = detail.getQueryHQLByRecordid(instockdetailid,dao);
        	if(detail!=null && detail.getStatus()!=null && detail.getStatus().equals("2")){ //�ջ�״̬
        	}else{
        		strMsg = "ֻ���ջ�״̬�ſ���ȡ���ջ�";
        	}
            
        }catch(Exception er)
        {
            strMsg = "ȡ���ջ���֤ʧ��";
            Logger.error(strMsg + er.getMessage());
        }
        return strMsg;
    }
    /**
     * ��֤�������Ƿ񸴺��޶���
     * @param invoiceid
     * @param requestvoicedetailid
     * @param num
     * @return
     */
    public String isNum(String invoiceid ,String requestvoicedetailid,String num){
    	String strBackMsg = "Y";
    	EntityDAO dao = BMSService.getm_EntityDAO();
    	try{
    		//InboundRequestInvoiceHeader inReqInvH = new InboundRequestInvoiceHeader();
    		InboundRequestInvoiceDetail inReqInvD = new InboundRequestInvoiceDetail();
    		IBaseProductDao baseProductDao = new BaseProductDaoImpl(dao);
    		InboundRequestInvoiceDetail inReqInvDt = inReqInvD.getDetailByid(invoiceid, requestvoicedetailid, dao);
    		String productId = inReqInvDt.getProductid();
    		BaseProduct baseProduct =baseProductDao.getProductById(productId);
    		double upperlimit = baseProduct.getUpperlimit();
    		double lowerlimit = baseProduct.getLowerlimit();
    		double num1 = 0;
    		if(num!=null){
    			num1 = Double.valueOf(num);
    		}
    		if(num1<lowerlimit){
    			strBackMsg ="��������������С�޶�����";
    		}else if(num1>upperlimit){
    			strBackMsg ="����������������޶�����";
    		}else if((num1==lowerlimit&&lowerlimit==0)||(num1==upperlimit&&upperlimit==0)){
    			strBackMsg ="����������Ϊ0��";
    		}
    	}catch(Exception er)
        {
    		strBackMsg = "������֤ʧ�ܣ�";
            Logger.error(strBackMsg + er.getMessage());
        }
        
    	return strBackMsg;
    }
    
    /**
     * ��֤��λ�Ƿ�ʹ��
     * @param invoiceid
     * @param requestvoicedetailid
     * @param num
     * @return
     */
    public String isCarspace(String warehouseid,String platoon, String column,String floor)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
            String strSql0 = "select bc.csstatus from BaseCargospace bc where bc.csplatoon='"+platoon+"' and bc.cscolumn='"+column+"' and bc.csfloor='"+floor+"'"+" and bc.warehouseid='"+warehouseid+"'";
            List lsList = dao.searchEntities(strSql0);
            if(lsList!=null && lsList.size()>0){
            	if(lsList.get(0)!=null){
            		String  status=  (String)lsList.get(0);
            		if(status!=null&&!status.equals("1")){//�ջ�λ
            			strMsg = "��λ��"+platoon+"��"+column+"��"+floor+"�㡿�����л����Ѿ������䣡";
            		}
            	}
            }
        }catch(Exception er)
        {
            strMsg = "��λ��֤ʧ��!";
            Logger.error("��λ��֤ʧ��:" + er.getMessage());
        }
        return strMsg;
    }
    
    /**
     * ���ܣ������ͻ��Ƿ�������ҵ
     * @param snumber   ���ͺ�
     * @param session
     * @return
     */
    public String isSnumberUse(String snumber, HttpSession session)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
            String strSql = "select count(job.jobid) from InoutJob as job where job.snumber='" + snumber + "' and job.status in('2') and job.inOutType in('1')";
            int iCount = dao.searchEntitieCount(strSql);
            if(iCount > 0)
            {
                strMsg = "�����ͻ����ڴ�ִ����ҵ��";
                return strMsg;
            }
            
        }catch(Exception er)
        {
            strMsg = "���ͻ���֤ʧ��!��������ͻ��Ƿ����!";
            Logger.error("���ͻ���֤ʧ��:" + er.getMessage());
        }
        return strMsg;
    }
    
	/**
	 * ���ܣ���ѯ���в��Ƿ�����ѡ����
	 * @param warehouseid
	 * @param whAreaId
	 * @param platoon
	 * @param column
	 * @param floor
	 * @return
	 */
    public String iswhAreaId(String warehouseid, String whAreaId, String platoon, String column, String floor)
    {
        String strMsg = "Y";
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	IBaseCargoSpaceDao idao = new BaseCargoSpaceDaoImpl(dao);
        	BaseCargospace space = idao.GetWhCargospace(platoon, column, floor, warehouseid,whAreaId);
            if(!space.getWhAreaId().equals(whAreaId))
            {
                strMsg = "���в㲻������ѡ������";
                return strMsg;
            }
            
        }catch(Exception er)
        {
            strMsg = "��ѯ���в��Ƿ�����ѡ����ʧ��!";
            Logger.error("��ѯ���в��Ƿ�����ѡ����ʧ��:" + er.getMessage());
        }
        return strMsg;
    }
    
}
