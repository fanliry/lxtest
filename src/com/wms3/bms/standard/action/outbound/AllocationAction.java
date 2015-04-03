package com.wms3.bms.standard.action.outbound;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.standard.business.outbound.IAssginBus;
import com.wms3.bms.standard.business.outbound.IOutBoundBus;
import com.wms3.bms.standard.business.outbound.impl.AssginBusImpl;
import com.wms3.bms.standard.business.outbound.impl.OutBoundBusImpl;
import com.wms3.bms.standard.entity.base.BaseLotSet;
import com.wms3.bms.standard.entity.base.HisProduct;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;

/**
 * ����: �������
 * @author hug 2012-9-20
 * @since WMS 3.0
 */
public class AllocationAction extends AbstractAction{
    protected IAssginBus assginBus;
    protected IOutBoundBus outboundBus;
    public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
    {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        //HttpSession httpsession = request.getSession(false);
        
        AssginBusImpl assginBus1 = new AssginBusImpl(dao);
        try
        {
            String flag = CommonTools.getStrToGbk(request.getParameter("flag"));        
            //���ⵥID
            String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
            //���ⵥ��ϸID
            String invoicedetailid = CommonTools.getStrToGbk(request.getParameter("invoicedetailid"));     
            //String customerid = CommonTools.getStrToGbk(request.getParameter("customerid"));     //�ͻ�
            String ownerid = CommonTools.getStrToGbk(request.getParameter("ownerid"));           //����   
            String warehouseId = CommonTools.getStrToGbk(request.getParameter("warehouseid"));   //�ֿ�ID
            String strProductId = CommonTools.getStrToGbk(request.getParameter("productid"));    //Ʒ��ID
            String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));      //����
            String alleyId = CommonTools.getStrToGbk(request.getParameter("alleyid"));        //���
            String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));        //����
            String printdate = CommonTools.getStrToGbk(request.getParameter("printdate"));    //��������
            String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));      //��������
            
            String unit = CommonTools.getStrToGbk(request.getParameter("unit"));      //��λ

            HisProduct tg = new HisProduct();
            if(flag != null && flag.trim().equals("1")){//�������->�ֹ������ѯ
                //��תҳ��
                strUrl = "/standard/jsp/outbound/assgin/outbound_assgin_list.jsp";
                outboundBus = new OutBoundBusImpl(dao);
                OutboundInvoiceHeader outBound = outboundBus.getOutBoundById(invoiceid);
                OutboundInvoiceDetail outInvoiceDetail = outboundBus.getOutBoundDetailById(invoicedetailid);
                String out_type = outBound.getOuttype();
                String ss = tg.getCAdd(dao, out_type, "wpzt");
                List lsStorage = assginBus1.getAssginQueryStorage(warehouseId,whAreaId, alleyId, strProductId,traycode,ss);

                request.setAttribute("exResultList", lsStorage);
                request.setAttribute("outInvoiceDetail", outInvoiceDetail);
            }
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("�������==>����==>��������ѯʧ��:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * ����: �˹��������
     * @author hug 2012-9-26 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String allotRicoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        //HttpSession httpsession = request.getSession(false);
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
 
        //��ǰ�û�
        String strUserCode = (String)request.getSession().getAttribute("userCode");
        
        //���ⵥID
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("outboundid"));
        //���ⵥ��ϸID
        String invoicedetailid = CommonTools.getStrToGbk(request.getParameter("detaiid"));
        
        //����Ŀ����Ϣ ��ʽ:    ��λ:��������c[���ID1������;���ID2������,��������] | ��λ3:��������d[���ID1������;���ID2������,��������] |
        String strStoMsg = CommonTools.getStrToGbk(request.getParameter("result"));
        
        String floor = CommonTools.getStrToGbk(request.getParameter("floor"));
        
        String tsjh = CommonTools.getStrToGbk(request.getParameter("tsjh"));
        
        Logger.info(strUserCode+"�����˹��������Ϣ:" + strStoMsg);
        
        assginBus = new AssginBusImpl(dao);
        outboundBus = new OutBoundBusImpl(dao);
        try
        {   
        	OutboundInvoiceHeader outBound = null;
             
			String strBackMsg = assginBus.assginStorage(invoiceid, invoicedetailid, strStoMsg, floor, tsjh, strUserCode);
            OutboundInvoiceDetail invoiceDetail = outboundBus.getOutBoundDetailById(invoicedetailid);
            
            if(invoiceDetail != null){ 
            	outBound = outboundBus.getOutBoundById(invoiceDetail.getOutstockid());
            }
            //��תҳ��
            strUrl = "/standard/jsp/outbound/assgin/outbound_assgin_list.jsp";
            //����ֵ
            request.setAttribute("back_msg", strBackMsg);   
            request.setAttribute("invoicedetail", invoiceDetail);
            request.setAttribute("invoice", outBound);
            request.getRequestDispatcher(strUrl).forward(request, response);   
            
        }catch(Exception er)
        {
            Logger.error("�û�["+strUserCode+"]���������==>�˹�����������:" + er.getMessage());
            request.setAttribute("errormsg", "�û�["+strUserCode+"]��������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }       
        return null;
    }
    /**
     * ����: �Զ�����
     * @author hug 2012-9-26 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String outoAllotRicoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        //HttpSession httpsession = request.getSession(false);
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
 
        //��ǰ�û�
        String strUserCode = (String)request.getSession().getAttribute("userCode");
        
        //���ⵥID
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
        //���ⵥ��ϸID
        String invoicedetailid = CommonTools.getStrToGbk(request.getParameter("invoicedetailid"));
        String warehouseId = CommonTools.getStrToGbk(request.getParameter("warehouseid"));   //�ֿ�ID
        String customerid = CommonTools.getStrToGbk(request.getParameter("customerid"));     //�ͻ�
        String ownerid = CommonTools.getStrToGbk(request.getParameter("ownerid"));           //����

        String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));    //����ID
        
        assginBus = new AssginBusImpl(dao);
        try
        {
            String strBackMsg = assginBus.autoAssginStorage(invoiceid, invoicedetailid, ownerid, customerid, warehouseId, whAreaId, strUserCode);
            
            //��תҳ��
            //strUrl = "/standard/jsp/outbound/newckd/outbound_newckd_list.jsp";
            //����ֵ
            request.setAttribute("back_msg", strBackMsg);                
            request.getRequestDispatcher(strUrl).forward(request, response);   
            
        }catch(Exception er)
        {
            Logger.error("�û�["+strUserCode+"]���������==>�����Զ��������:" + er.getMessage());
            request.setAttribute("errormsg", "�û�["+strUserCode+"]��������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }       
        return null;
    }
    
}
