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
import com.wms3.bms.standard.business.inbound.IPutawayBus;
//import com.wms3.bms.standard.business.inbound.impl.PutawayBusImpl;
import com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;


/**
 * ����: �ϼ�
 * @author hug 2012-8-28
 * @since WMS 3.0
 */
public class PutawayAction  extends AbstractAction{
    protected IPutawayBus putawayBus;
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
            //�ջ����׼�¼ID
            String transid = CommonTools.getStrToGbk(request.getParameter("transid"));
            if(flag != null && flag.trim().equals("1")){ //�ϼ�->�������ϼ�->��ѯ�ջ���¼
                String strQueryHQL = putawayBus.getReceiptTransQuerySQL(invoiceid);
                String strCountHQL = putawayBus.getReceiptTransCountSQL(invoiceid);    
                //��תҳ��
                strUrl = "/standard/jsp/inbound/putaway/inbound_putaway_mgr_trans.jsp";   
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 1);
                List ls = pt.getLsResult();//��ѯ���
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("inboundPage4", pt); 
                
            }else if(flag != null && flag.trim().equals("2")){ //�ϼ�->����
                //��תҳ��
                strUrl = "/standard/jsp/inbound/putaway/inbound_putaway_tray.jsp";
                InboundReceiptTransaction trans = putawayBus.getTransReceiptById(transid);
                //����ð�װһ���̿ɷŶ�������
                int iOneTray = putawayBus.getOneTrayNumber(trans.getPackid());
                //���̵���������
                int iTrayNum = putawayBus.getTrayNum((trans.getRecnum()-trans.getPucnum()), trans.getPackid());
                request.setAttribute("trans", trans);  
                request.setAttribute("onetray", iOneTray);
                request.setAttribute("traynum", iTrayNum);
            }else if(flag != null && flag.trim().equals("3")){ //�ϼ�->ִ���ϼ�->��ѯ��ҵ
                String strQueryHQL = putawayBus.getPutawayJobQuerySQL(invoiceid);
                String strCountHQL = putawayBus.getPutawayJobCountSQL(invoiceid);    
                //��תҳ��
                strUrl = "/standard/jsp/inbound/putaway/inbound_putaway_execute_job.jsp";   
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//��ѯ���
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("inboundPagejob", pt); 
                
            }else if(flag != null && flag.trim().equals("4")){ //�ϼ�->ִ���ϼ�->��ѯ��ҵ��ϸ
                //��תҳ��
                strUrl = "/standard/jsp/inbound/putaway/inbound_putaway_execute_jobdetail.jsp";  
                //��ҵID
                String jobid = CommonTools.getStrToGbk(request.getParameter("jobid"));
    //            List<InoutJobdetail> lsJobDetail = putawayBus.getJobDetailsById(jobid);
     //           request.setAttribute("exResultList", lsJobDetail);
            }else if(flag != null && flag.trim().equals("label")){ //�ϼ�->��ӡ�ջ��ı�ǩ
                //��תҳ��
                strUrl = "/standard/jsp/inbound/putaway/inbound_putaway_label.jsp";
                InboundReceiptTransaction trans = putawayBus.getTransReceiptById(transid);
                request.setAttribute("trans", trans);  
            }else if(flag != null && flag.equals("print")){//�ϼ�->��ӡ�ϼ�����
                //��תҳ��
                strUrl = "/standard/jsp/inbound/putaway/inbound_putaway_printtask.jsp";
                List ls = putawayBus.getPutawayJob(invoiceid);

                request.setAttribute("invoiceid", invoiceid);
                request.setAttribute("exResultList", ls); 
            }
            
            request.getRequestDispatcher(strUrl).forward(request, response);   
        }catch(Exception er)
        {
            Logger.error("������==>�ϼܲ�ѯ����:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * ����:�����ϼ�����
     * @author hug 2012-9-4 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String createPutaway(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        //HttpSession httpsession = request.getSession(false);
        
        String strUserCode = (String)request.getSession().getAttribute("userCode");     //��ǰ�û�  
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));  //�ջ���ID 
        
        putawayBus = null;// new PutawayBusImpl(dao);
        String strMsg = "";
        
        //�ջ���¼������
        String strLineRows = CommonTools.getStrToGbk(request.getParameter("transrows"));
        //��������
        String strTrayCode = CommonTools.getStrToGbk(request.getParameter("traycode"));
        //�ֿ�ID
        String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));
        //����ID
        String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));
        //�ϼܷ�ʽ
        String putmode = CommonTools.getStrToGbk(request.getParameter("putmode"));
        
        try
        {
            //�����ϼ�����
            strMsg = putawayBus.createPutawayTask(invoiceid, strLineRows, strUserCode, strTrayCode, warehouseid, whAreaId, putmode, request);   
                
            //��תҳ��
            strUrl = "/standard/jsp/inbound/putaway/inbound_putaway_mgr_put.jsp";   
            request.setAttribute("invoiceid", invoiceid);
            request.setAttribute("back_msg", strMsg);
            request.getRequestDispatcher(strUrl).forward(request, response);      
        }catch(Exception er)
        {
            Logger.error("�û�["+strUserCode+"]��������==>�����ϼ��������:" + er.getMessage());
            request.setAttribute("errormsg", "�û�["+strUserCode+"]��������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * ����:����
     * @author hug 2012-9-4 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String codeTray(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        //HttpSession httpsession = request.getSession(false);
        
        String strUserCode = (String)request.getSession().getAttribute("userCode");     //��ǰ�û�  

        putawayBus = null;//new PutawayBusImpl(dao);
        String strMsg = "";
        
        //�ջ���¼Id
        String strTransId = CommonTools.getStrToGbk(request.getParameter("transid"));
        //�ϼܿ���ID
        String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));
        //String strLotid = CommonTools.getStrToGbk(request.getParameter("lotid"));       //��������ID
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
        
        try
        {
            strMsg = putawayBus.excuteCodeTray(strTransId, whAreaId, strLotatt1,strLotatt2,strLotatt3, strLotatt4, strLotatt5, strLotatt6, strLotatt7, strLotatt8, strLotatt9, strLotatt10, strLotatt11, strLotatt12, strUserCode);
            //��תҳ��
            strUrl = "/standard/jsp/inbound/putaway/inbound_putaway_tasklist.jsp";   
            request.setAttribute("back_msg", strMsg);
            request.getRequestDispatcher(strUrl).forward(request, response);      
        }catch(Exception er)
        {
            Logger.error("�û�["+strUserCode+"]��������==>���̳���:" + er.getMessage());
            request.setAttribute("errormsg", "�û�["+strUserCode+"]��������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * ����: �ϼ�
     * @author hug 2012-9-4 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String putaway(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        
        String strUserCode = (String)request.getSession().getAttribute("userCode");     //��ǰ�û�  
        putawayBus = null;//new PutawayBusImpl(dao);
        String strMsg = "";  
        //�ϼ���ҵID
        String strJobId = CommonTools.getStrToGbk(request.getParameter("jobid"));
        //�»�λID
        String strNewSpaceId = CommonTools.getStrToGbk(request.getParameter("newspaceid"));
        //�ջ���ID
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));

        try
        { 
            //ִ���ϼ�
            strMsg = putawayBus.excutePutaway(strJobId, strUserCode, strNewSpaceId); 
            
            String strQueryHQL = putawayBus.getPutawayJobQuerySQL(invoiceid);
            String strCountHQL = putawayBus.getPutawayJobCountSQL(invoiceid);    
            //��תҳ��
            strUrl = "/standard/jsp/inbound/putaway/inbound_putaway_execute_job.jsp";   
            PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 5);
            List ls = pt.getLsResult();//��ѯ���
            request.setAttribute("exResultList", ls);
            request.setAttribute("pagingTool", pt);
            httpsession.setAttribute("inboundPagejob", pt); 
            
            request.setAttribute("back_msg", strMsg);
            request.getRequestDispatcher(strUrl).forward(request, response);      
        }catch(Exception er)
        {
            Logger.error("�û�["+strUserCode+"]��������==>ִ���ϼܳ���:" + er.getMessage());
            request.setAttribute("errormsg", "�û�["+strUserCode+"]��������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * ����:ȡ���ϼ�
     * @author hug 2012-9-12 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String cancelPutaway(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        
        String strUserCode = (String)request.getSession().getAttribute("userCode");     //��ǰ�û�  

        putawayBus = null;//new PutawayBusImpl(dao);
        String strMsg = "";
        
        //�ϼ���ҵID
        String strJobId = CommonTools.getStrToGbk(request.getParameter("jobid"));
        //�ջ���ID
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));

        try
        { 
            //ȡ���ϼ�
            strMsg = putawayBus.cancelPutaway(strJobId);
   
            String strQueryHQL = putawayBus.getPutawayJobQuerySQL(invoiceid);
            String strCountHQL = putawayBus.getPutawayJobCountSQL(invoiceid);    
            //��תҳ��
            strUrl = "/standard/jsp/inbound/putaway/inbound_putaway_execute_job.jsp";   
            PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 5);
            List ls = pt.getLsResult();//��ѯ���
            request.setAttribute("exResultList", ls);
            request.setAttribute("pagingTool", pt);
            httpsession.setAttribute("inboundPagejob", pt);
            
            request.setAttribute("back_msg", strMsg);
            request.getRequestDispatcher(strUrl).forward(request, response);      
        }catch(Exception er)
        {
            Logger.error("�û�["+strUserCode+"]��������==>ȡ���ϼܳ���:" + er.getMessage());
            request.setAttribute("errormsg", "�û�["+strUserCode+"]��������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }

}
