package com.wms3.bms.standard.action.outbound;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.standard.business.outbound.ISendBus;
import com.wms3.bms.standard.business.outbound.impl.SendBusImpl;
import com.wms3.bms.standard.dao.outbound.IAssginDao;
import com.wms3.bms.standard.dao.outbound.impl.AssginDaoImpl;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * ����: ����ȷ��
 * @author hug 2012-10-9
 * @since WMS 3.0
 */
public class SendAction extends AbstractAction{
    protected ISendBus sendBus;
    public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));
        String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));//�ֿ�ID
        String zcwhareaid = CommonTools.getStrToGbk(request.getParameter("zcwhareaid"));//�ݴ�ID
        sendBus = new SendBusImpl(dao); 
        try
        {     
            //���ⵥID
            String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
            
            //ÿҳ��ʾ����
            int iline = 5;
            String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));
            if(linerow != null && linerow.length()>0){
                iline = Integer.parseInt(linerow);
            }
            if(flag != null && flag.trim().equals("1")){//����ȷ�� -> ���� -> ���ݲֿ����ݴ�
            	
                List ls = sendBus.getZCInventorybyTray(warehouseid,zcwhareaid,traycode);
                //��תҳ��
                strUrl = "/standard/jsp/outbound/fhqr/outbound_fhqr_adjust_zc_list.jsp";   
                request.setAttribute("exResultList", ls);
                 
             }else if(flag != null && flag.trim().equals("2")){//����ȷ�� ->��ѯ��ҵ
                 //���ⵥ��ϸID
                 String invoicedetailid = CommonTools.getStrToGbk(request.getParameter("invoicedetailid"));
                 List ls = sendBus.getJobAndJobDetail(invoiceid, invoicedetailid);
                 
                 //��תҳ��
                 strUrl = "/standard/jsp/outbound/fhqr/outbound_fhqr_job_list.jsp";   
                 request.setAttribute("exResultList", ls);
                 
             }else if(flag != null && flag.trim().equals("3")){//����ȷ�� ->���� ->��������������������ѯ�ѳ������ҵ
                 List ls = sendBus.getJobAndJobDetailByCode(invoiceid, traycode, "");    
                 //��תҳ��
                 strUrl = "/standard/jsp/outbound/fhqr/outbound_fhqr_adjust_jobdetail.jsp";   
                 request.setAttribute("exResultList", ls);
             }else if(flag != null && flag.trim().equals("4")){//����ȷ�� ->���� ->���� ->��ѯ��ҵ��ϸ
                 //��ҵ��ϸID
                String strJobDetailId = CommonTools.getStrToGbk(request.getParameter("jobdetailid")); 
                strUrl ="/standard/jsp/outbound/fhqr/outbound_fhqr_adjust_move.jsp";
                //��ҵ��ϸ
                InoutJobdetail jobDetail = sendBus.getJobDetailByDetailId(strJobDetailId);
                //��ҵ
                InoutJob job = sendBus.getJobById(jobDetail.getJobid());
                request.setAttribute("job", job);
                request.setAttribute("jobDetail", jobDetail);
             }else if(flag != null && flag.trim().equals("5")){//����ȷ�� ->���� ->���� ->��ѯ�ݴ�
                 //���ID
                 String strStoreid = CommonTools.getStrToGbk(request.getParameter("Storeid")); 
                 strUrl ="/standard/jsp/outbound/fhqr/outbound_fhqr_adjust_movezc.jsp";
                 IAssginDao assginDao = new AssginDaoImpl(dao);
                 InventoryStorage storage = assginDao.getInventoryStorageById(strStoreid);
                 request.setAttribute("storage", strStoreid);
             }
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("�������==>������ѯ����:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ"+er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * ����:�������� A�ͻ�->B�ͻ� A�ͻ�->�ݴ�
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String ricoExecMove(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
    {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        HttpSession httpsession = request.getSession(false);
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        
        //��ʶ  A�ͻ�->�ݴ���   A�ͻ�->B�ͻ�
        String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        
        //�����ĳ������ҵ��ϸ
        String strDetailId = CommonTools.getStrToGbk(request.getParameter("detailid"));
        //����������
        String strNum = CommonTools.getStrToGbk(request.getParameter("num"));
        //������ë��
        String strWeight = CommonTools.getStrToGbk(request.getParameter("weight"));
        //�����ľ���
        String strNetweight = CommonTools.getStrToGbk(request.getParameter("netweight"));
        //���������
        String strVolume = CommonTools.getStrToGbk(request.getParameter("volume"));
        
        //A�ͻ� -> �ݴ���****************************************************************
        //���ⵥID
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
        //�ֿ�ID
        String strWarehouseId = CommonTools.getStrToGbk(request.getParameter("warehouseid"));
        //����ID���ݴ���ID��
        String whAreaId =  CommonTools.getStrToGbk(request.getParameter("whAreaId")); 
        // �ݴ����Ļ�λID
       // String whAreaSpaceId = strWarehouseId + whAreaId + "001";   //�ݴ�����λ   
        String whAreaSpaceId = whAreaId + "010101";   //�ݴ�����λ 
        //��������
        String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));
        //������
        String boxcode = CommonTools.getStrToGbk(request.getParameter("boxcode")); 
        //��ǰ�ֿ�
        String strUserCode = (String)httpsession.getAttribute("userCode");            //��ǰ�û�
        String strShiftid =  CommonTools.getStrToGbk(request.getParameter("shiftid"));//���
        sendBus = new SendBusImpl(dao);
        String strMsg = "Y";
        try
        {
            double num = Double.parseDouble(strNum);       //����
            double volume = 0;    //���
            double weight = 0;    //����
            double netweight = 0; //����
           
            List ls = null;
            if(flag != null && flag.equals("1"))    //A�ͻ�->�ݴ���
            {
            	strMsg = sendBus.getAtoZ(strDetailId, num, weight, netweight, volume, strUserCode, strShiftid, strWarehouseId, whAreaId, whAreaSpaceId);
            }
            ls = sendBus.getJobAndJobDetailByCode(invoiceid, traycode, boxcode);    
            //��תҳ��
            strUrl = "/standard/jsp/outbound/fhqr/outbound_fhqr_adjust_jobdetail.jsp";   
            request.setAttribute("exResultList", ls);
            request.setAttribute("back_msg", strMsg);
            request.getRequestDispatcher(strUrl).forward(request, response);
        }catch(Exception er)
        {
            Logger.error("�û�["+strUserCode+"]���������==>����ȷ�ϵ���ʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "�û�["+strUserCode+"]��������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * ����: ��������
     * @author hug 2012-10-31 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String ricoExecBatchMove(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
    {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        HttpSession httpsession = request.getSession(false);
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        
        //��ʶ  A�ͻ�->�ݴ���   A�ͻ�->B�ͻ�
        String flag = CommonTools.getStrToGbk(request.getParameter("flag"));    
        //�������ҵ��ϸ�б�  �ö��ŷָ�
        String strDetailIds = CommonTools.getStrToGbk(request.getParameter("detailids"));
        
        //A�ͻ� -> B�ͻ�****************************************************************
        //B�ͻ�����ID
        String strBInvoiceId = CommonTools.getStrToGbk(request.getParameter("binvoiceid"));
        //B�ͻ�������ϸID
        String strBInvoiceDetailId = CommonTools.getStrToGbk(request.getParameter("binvoicedetailid"));
        
        //A�ͻ� -> �ݴ���****************************************************************
        //�ֿ�ID
        String strWarehouseId = CommonTools.getStrToGbk(request.getParameter("warehouseid"));
        // ����ID���ݴ���ID��
        String whAreaId =  CommonTools.getStrToGbk(request.getParameter("whAreaId")); //һ���ֿ�ֻ����һ���ݴ���
        // �ݴ����Ļ�λID
        String whAreaSpaceId  =  strWarehouseId + whAreaId + "001";   //�ݴ�����λ
        
        //��ǰ�û�
        String strUserCode = (String)httpsession.getAttribute("userCode");
        //���
        String strShiftid =  CommonTools.getStrToGbk(request.getParameter("shiftid")); 
        
        sendBus = new SendBusImpl(dao);
        String strMsg = "Y";
        try
        {
            List ls = null;
            
            if(flag != null && flag.equals("1"))    //A�ͻ�->�ݴ���
            {
                sendBus.getBatchAtoZ(strDetailIds, strUserCode, strShiftid, strWarehouseId, whAreaId, whAreaSpaceId);
            }else if(flag != null && flag.equals("2")) //A�ͻ� -> B�ͻ�
            {
                sendBus.getBatchAtoB(strBInvoiceId, strBInvoiceDetailId, strDetailIds, strUserCode, strShiftid);
            }

            request.setAttribute("back_msg", strMsg);
            request.setAttribute("exResultList", ls);
            request.getRequestDispatcher(strUrl).forward(request, response);
        }catch(Exception er)
        {
            Logger.error("�û�["+strUserCode+"]���������==>����ȷ����������ʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "�û�["+strUserCode+"]��������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    
    /**
     * ���ܣ�����ȷ��
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String ricoExecOkFHQR(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
    {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
         
        //���ⵥ�ݺ�
        String strOutBoundId = CommonTools.getStrToGbk(request.getParameter("out_id"));
        //���
        String strShiftid =  CommonTools.getStrToGbk(request.getParameter("shiftid"));
        sendBus = new SendBusImpl(dao);
        //��ǰ�û�
        String strUserCode = (String)httpsession.getAttribute("userCode");
        try
        {
            String strMsg = "Y";
            //�����ݷ���ȷ��
            strMsg = sendBus.outInvoiceFHQR(strOutBoundId, strUserCode, strShiftid);
            
           // strUrl = "/standard/jsp/outbound/outbound_fhqr.jsp";
            strUrl = "/standard/jsp/outbound/fhqr/outbound_fhqr_list.jsp";
            request.setAttribute("back_msg", strMsg);
            request.getRequestDispatcher(strUrl).forward(request, response);
        }catch(Exception er)
        {
            Logger.error("�û�["+strUserCode+"]���������==>����ȷ��ʧ��:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        
        return null;
    }
    /**
     * ���ܣ�������ϸ���з���ȷ��
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String ricoExecOneOkFHQR(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
    {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        
        //��ʶ 
        //String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        
        //���ⵥ�ݺ�
        //String invoiceid = CommonTools.getStrToGbk(request.getParameter("out_id"));
        //���ⵥ��ϸ
        String strOutDetailId = CommonTools.getStrToGbk(request.getParameter("out_detail_id"));
        //���
        String strShiftid =  CommonTools.getStrToGbk(request.getParameter("shiftid"));
        sendBus = new SendBusImpl(dao);
        //��ǰ�û�
        String strUserCode = (String)httpsession.getAttribute("userCode");
        try
        {
            String strMsg = "Y";
            //�����ݷ���ȷ��
            strMsg = sendBus.outInvoiceDetailFHQR(strOutDetailId, strUserCode, strShiftid);

            //��תҳ��
            strUrl = "/standard/jsp/outbound/fhqr/outbound_fhqr_list.jsp";
           // strUrl = "/standard/jsp/outbound/outbound_fhqr.jsp";
   
            request.setAttribute("back_msg", strMsg);
            request.getRequestDispatcher(strUrl).forward(request, response);
        }catch(Exception er)
        {
            Logger.error("�û�["+strUserCode+"]���������==>��������ϸ����ȷ��ʧ��:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }

}
