package com.wms3.bms.standard.action.rf;

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
import com.wms3.bms.standard.business.inbound.IPutawayBus;
import com.wms3.bms.standard.business.inbound.IReceiptBus;
//import com.wms3.bms.standard.business.inbound.impl.PutawayBusImpl;
import com.wms3.bms.standard.business.inbound.impl.ReceiptBusImpl;

/**
 * ����: RF�ջ�
 * @author hug 2012-9-3
 * @since WMS 3.0
 */
public class RFReceiptAction extends AbstractAction{
    protected IPutawayBus putawayBus;
    protected IReceiptBus receiptBus;       //�ջ�ҵ��
    @Override
    public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        
        String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        //�ջ���ID
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
        
        //ÿҳ��ʾ����
        int iline = 5;
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));
        if(linerow != null && linerow.length()>0){
            iline = Integer.parseInt(linerow);
        }
        putawayBus = null;//new PutawayBusImpl(dao);
        try{
            if(flag != null && flag.trim().equals("1")){ //RF�ջ�->��ѯ�ջ�����ϸ
                //(״̬0-����1-�����ջ�)
                String strQueryHQL = putawayBus.getReceiptDetailQuerySQL(invoiceid);
                String strCountHQL = putawayBus.getReceiptDetailCountSQL(invoiceid);    
                //��תҳ��
                strUrl = "/rf/receipt_list.jsp";   
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 1);
                List ls = pt.getLsResult();//��ѯ���
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("inboundPage5", pt);       
            }else if(flag != null && flag.trim().equals("2")){ //RF�ϼ�->��ѯ�ϼܼ�¼
                //(״̬��Ϊ 4����ȫ�ϼ� 5��ȡ���ջ�)
                String strQueryHQL = putawayBus.getNeedReceiptTransQuerySQL(invoiceid);
                String strCountHQL = putawayBus.getNeedReceiptTransCountSQL(invoiceid);    
                //��תҳ��
                strUrl = "/rf/putaway_list.jsp";   
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 1);
                List ls = pt.getLsResult();//��ѯ���
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("inboundPage6", pt);       
            }
            
            
            request.getRequestDispatcher(strUrl).forward(request, response);   
        }catch(Exception er)
        {
            Logger.error("RF==>RF�ջ���ѯ����:" + er.getMessage());
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
        
        receiptBus = new ReceiptBusImpl(dao);
        String strMsg = "";
        try
        {     
            //ִ���ջ�
           strMsg = receiptBus.excuteRFReceipt(invoiceid, invoicedetailid, strLotid, 
                                strLotatt1, strLotatt2, strLotatt3, strLotatt4, strLotatt5, strLotatt6, 
                                strLotatt7, strLotatt8, strLotatt9, strLotatt10, strLotatt11, strLotatt12, 
                                Double.parseDouble(num), Double.parseDouble(weight), Double.parseDouble(netweight), Double.parseDouble(volume),
                                strUserCode);
                
         
          //��תҳ��
          strUrl = "/rf/receipt.jsp";   

          request.setAttribute("back_msg", strMsg);
          request.getRequestDispatcher(strUrl).forward(request, response);      
        }catch(Exception er)
        {
            Logger.error("RF�ջ�����==>ִ��RF�ջ�����:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * ����:������
     * @author hug 2012-9-4 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String bindTray(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        //HttpSession httpsession = request.getSession(false);
        
        //��ҵ��
        String jobid = CommonTools.getStrToGbk(request.getParameter("jobid"));  //��ҵ�� 
        //��������
        String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));
        receiptBus = new ReceiptBusImpl(dao);
        String strMsg = "";
        try
        {    
            strMsg = receiptBus.bindTray(jobid, traycode);
            
            //��תҳ��
            strUrl = "/rf/bindtray.jsp";   

            request.setAttribute("back_msg", strMsg);
            request.getRequestDispatcher(strUrl).forward(request, response);      
          }catch(Exception er)
          {
              Logger.error("RF==>�����̳���:" + er.getMessage());
              request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
              request.getRequestDispatcher("/error.jsp").forward(request, response);
          }
        return null;
    }


}
