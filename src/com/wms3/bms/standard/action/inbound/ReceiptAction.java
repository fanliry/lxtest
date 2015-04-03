package com.wms3.bms.standard.action.inbound;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.standard.business.inbound.IInBoundReceiptBus;
import com.wms3.bms.standard.business.inbound.IReceiptBus;
import com.wms3.bms.standard.business.inbound.impl.InBoundReceiptBusImpl;
import com.wms3.bms.standard.business.inbound.impl.ReceiptBusImpl;
import com.wms3.bms.standard.entity.inbound.InboundReceiptHeader;
import com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction;

/**
 * 
 * ����: �ջ�
 * @author hug 2012-8-23
 * @since WMS 3.0
 */
public class ReceiptAction  extends AbstractAction{
    protected IReceiptBus receiptBus;       //�ջ�ҵ��
    private IInBoundReceiptBus inReceiptBus;//�ջ���ҵ��
    public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        
        String flag = CommonTools.getStrToGb2312(request.getParameter("flag"));
        
        //�ջ���ID
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
        //�ջ�����ϸID
        String invoicedetailid = CommonTools.getStrToGbk(request.getParameter("invoicedetailid"));
        
        //ÿҳ��ʾ����
        int iline = 5;
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));
        if(linerow != null && linerow.length()>0){
            iline = Integer.parseInt(linerow);
        }
        receiptBus = new ReceiptBusImpl(dao);
        inReceiptBus = new InBoundReceiptBusImpl(dao);
        try
        {
            if(flag != null && flag.trim().equals("1")){//�ջ�->��ѯ������ϸ
                
                String strQueryHQL = receiptBus.getInReceiptDetailQuerySQL(invoiceid);
                String strCountHQL = receiptBus.getInReceiptDetailCountSQL(invoiceid);   
                
                //��תҳ��
                strUrl = "/standard/jsp/inbound/receipt/inbound_receipt_mgr_detail.jsp";   
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//��ѯ���
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("inboundPage1", pt);
                    
            }else if(flag != null && flag.trim().equals("2")){//�ջ�->���ݵ�����ϸ��ѯ�ջ�������Ϣ
                
                String strQueryHQL = receiptBus.getReceiptTaskQuerySQL(invoicedetailid);
                String strCountHQL = receiptBus.getReceiptTaskCountSQL(invoicedetailid);   
                
                //��תҳ��
                strUrl = "/standard/jsp/inbound/receipt/inbound_receipt_mgr_task.jsp";   
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 5);
                List ls = pt.getLsResult();//��ѯ���
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("inboundPage2", pt); 
            }else if(flag != null && flag.trim().equals("3"))//�ջ�->��ӡ�ջ�����
            {
                //��תҳ��
                strUrl = "/standard/jsp/inbound/receipt/inbound_receipt_task_print.jsp";
                //�ջ���
                InboundReceiptHeader inBound = inReceiptBus.getInBoundReceiptInvoiceById(invoiceid);
                //�ջ�����
                List<InboundReceiptTransaction> lsTask = receiptBus.getReceiptTransactionToIvoiceId(invoiceid);
                
                request.setAttribute("invoice", inBound);
                request.setAttribute("tasks", lsTask);
            }else if(flag != null && flag.trim().equals("4"))//�ϼ�->�����ջ�����ѯ�ջ���¼
            {
                //��תҳ��
                strUrl = "/standard/jsp/inbound/putaway/inbound_putaway_tasklist.jsp";
                //�ջ������б�
                List<InboundReceiptTransaction> lsTask = receiptBus.getReceiptTransactionToIvoiceId(invoiceid);
                request.setAttribute("tasks", lsTask);
            }else if(flag != null && flag.trim().equals("5"))//�ջ�->ȡ���ջ�->�����ջ�����ѯ�ջ���¼
            {
                //��תҳ��
                strUrl = "/standard/jsp/inbound/receipt/inbound_receipt_cancel_list.jsp";
                
                String strQueryHQL = receiptBus.getReceiptTransQuerySQL(invoiceid);
                String strCountHQL = receiptBus.getReceiptTransCountSQL(invoiceid); 
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 10);
                List ls = pt.getLsResult();//��ѯ���
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("inboundPageCancel", pt);      
            }
            
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("������==>�ջ���ѯ����:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    
    }
    /**
     * ����:�ջ�
     * @author hug 2012-8-28 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String receipt(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        
        String strUserCode = (String)request.getSession().getAttribute("userCode");           //��ǰ�û�  
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));  //�ջ���ID   
        String invoicedetailid = CommonTools.getStrToGbk(request.getParameter("invoicedetailid"));//�ջ�����ϸID
        String strLotid = CommonTools.getStrToGbk(request.getParameter("lotid"));       //��������ID
        String strLotatt1 = CommonTools.getStrToGbk(request.getParameter("lotatt1"));   //��������1
        String strLotatt2 = CommonTools.getStrToGbk(request.getParameter("lotatt2"));   //��������2
        String strLotatt3 = CommonTools.getStrToGbk(request.getParameter("lotatt3"));   //��������3
        String strLotatt4 = CommonTools.getStrToGbk(request.getParameter("lotatt4"));   //��������4
        String strLotatt5 = CommonTools.getStrToGbk(request.getParameter("lotatt5"));   //��������5
        String strLotatt6 = CommonTools.getStrToGbk(request.getParameter("lotatt6"));   //��������6
        String strLotatt7 = CommonTools.getStrToGbk(request.getParameter("lotatt7"));   //��������7
        String strLotatt8 = CommonTools.getStrToGbk(request.getParameter("lotatt8"));   //��������8
        String strLotatt9 = CommonTools.getStrToGbk(request.getParameter("lotatt9"));   //��������9
        String strLotatt10 = CommonTools.getStrToGbk(request.getParameter("lotatt10")); //��������10
        String strLotatt11 = CommonTools.getStrToGbk(request.getParameter("lotatt11")); //��������11
        String strLotatt12 = CommonTools.getStrToGbk(request.getParameter("lotatt12")); //��������12
        
        String num = CommonTools.getStrToGbk(request.getParameter("num"));              //�ջ�����
        String weight = CommonTools.getStrToGbk(request.getParameter("weight"));        //�ջ�ë��
        String netweight = CommonTools.getStrToGbk(request.getParameter("netweight"));  //�ջ�����
        String volume = CommonTools.getStrToGbk(request.getParameter("volume"));        //�ջ����
        
        String rejectednum = CommonTools.getStrToGbk(request.getParameter("rejectednum"));  //��������
        String rejectcode = CommonTools.getStrToGbk(request.getParameter("rejectcode"));    //���մ���
        String rejectreason = CommonTools.getStrToGbk(request.getParameter("rejectreason"));//����ԭ��
        
        String holdnum = CommonTools.getStrToGbk(request.getParameter("holdnum"));      //��������
        String holdcode = CommonTools.getStrToGbk(request.getParameter("holdcode"));    //�������
        String holdreason = CommonTools.getStrToGbk(request.getParameter("holdreason"));//����ԭ��
        
        //ÿҳ��ʾ����
        int iline = 5;
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));
        if(linerow != null && linerow.length()>0){
            iline = Integer.parseInt(linerow);
        }
        receiptBus = new ReceiptBusImpl(dao);
        String strMsg = "";
        try
        {
                
            //ִ���ջ�
           strMsg = receiptBus.excuteReceipt(invoiceid, invoicedetailid, strLotid, 
                                strLotatt1, strLotatt2, strLotatt3, strLotatt4, strLotatt5, strLotatt6, 
                                strLotatt7, strLotatt8, strLotatt9, strLotatt10, strLotatt11, strLotatt12, 
                                Double.parseDouble(num), Double.parseDouble(weight), Double.parseDouble(netweight), Double.parseDouble(volume),
                                Double.parseDouble(rejectednum), rejectcode, rejectreason,
                                Double.parseDouble(holdnum), holdcode, holdreason, strUserCode);
                
          String strQueryHQL = receiptBus.getInReceiptDetailQuerySQL(invoiceid);
          String strCountHQL = receiptBus.getInReceiptDetailCountSQL(invoiceid);   
                
          //��תҳ��
          strUrl = "/standard/jsp/inbound/receipt/inbound_receipt_mgr_detail.jsp";   
          PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
          List ls = pt.getLsResult();//��ѯ���
          request.setAttribute("exResultList", ls);
          request.setAttribute("pagingTool", pt);
          httpsession.setAttribute("inboundPage2", pt);
          request.setAttribute("back_msg", strMsg);
          request.getRequestDispatcher(strUrl).forward(request, response);      
        }catch(Exception er)
        {
            Logger.error("������==>ִ���ջ�����:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * ����:ȡ���ջ�
     * @author hug 2012-8-28 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String cancelreceipt(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
           
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));  //�ջ���ID   
        //�ջ���¼
        String transid = CommonTools.getStrToGbk(request.getParameter("transid"));//�ջ���¼ID
       
        receiptBus = new ReceiptBusImpl(dao);
        String strMsg = "";
        try
        {             
            //ִ���ջ�
           strMsg = receiptBus.cancelReceipt(invoiceid, transid);             
          //��תҳ��
          strUrl = "/standard/jsp/inbound/receipt/inbound_receipt_cancel_list.jsp";
          
          String strQueryHQL = receiptBus.getReceiptTransQuerySQL(invoiceid);
          String strCountHQL = receiptBus.getReceiptTransCountSQL(invoiceid); 
          PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 10);
          List ls = pt.getLsResult();//��ѯ���
          request.setAttribute("exResultList", ls);
          request.setAttribute("pagingTool", pt);
          httpsession.setAttribute("inboundPageCancel", pt); 
          
          request.setAttribute("back_msg", strMsg);
          request.getRequestDispatcher(strUrl).forward(request, response);      
        }catch(Exception er)
        {
            Logger.error("������==>ȡ���ջ�����:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }

}
