package com.wms3.bms.standard.action.outbound;

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
import com.wms3.bms.standard.business.outbound.IOutBoundBus;
import com.wms3.bms.standard.business.outbound.IReviewBus;
import com.wms3.bms.standard.business.outbound.impl.OutBoundBusImpl;
import com.wms3.bms.standard.business.outbound.impl.ReviewBusImpl;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;

/**
 * ����: ���˲���
 * @author hug 2012-9-18
 * @since WMS 3.0
 */
public class ReviewAction extends AbstractAction{
    protected IOutBoundBus outboundBus;
    protected IReviewBus reviewBus;
    public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
    {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        reviewBus = new ReviewBusImpl(dao);
        outboundBus = new OutBoundBusImpl(dao);
        try
        {
            String flag = CommonTools.getStrToGb2312(request.getParameter("flag"));
            
            String outstatus = CommonTools.getStrToGbk(request.getParameter("outstatus"));      //����״̬
            String outtype = CommonTools.getStrToGbk(request.getParameter("outtype"));          //��������
            String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));  //�ֿ�ID
            String shiftid = CommonTools.getStrToGbk(request.getParameter("shiftid"));          //��ҵ���
            String screatedate = CommonTools.getStrToGbk(request.getParameter("start_time"));   //���ݿ�ʼ����ʱ��
            String ecreatedate = CommonTools.getStrToGbk(request.getParameter("end_time"));     //���ݽ�������ʱ��
            String customerid = CommonTools.getStrToGbk(request.getParameter("customerid"));    //�ͻ�
            String outno = CommonTools.getStrToGbk(request.getParameter("outno"));              //���ⵥ��
            
            //ÿҳ��ʾ����
            int iline = 5;
            String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));
            if(linerow != null && linerow.length()>0){
                iline = Integer.parseInt(linerow);
            }
            
            //���ⵥID
            String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
            //���ⵥ��ϸID
            String invoicedetailid = CommonTools.getStrToGbk(request.getParameter("invoicedetailid"));
            
            if(flag != null && flag.trim().equals("1")){//����->���ⵥ��ѯ
                
                String strQueryHQL = outboundBus.getOutBoundQuerySQL(warehouseid, outstatus, outtype, shiftid, screatedate, ecreatedate, customerid, outno);
                String strCountHQL = outboundBus.getOutBoundCountSQL(warehouseid, outstatus, outtype, shiftid, screatedate, ecreatedate, customerid, outno);
                
                //��תҳ��
                strUrl = "/standard/jsp/outbound/review/outbound_review_list.jsp";    
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//��ѯ���
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("paging", pt);
                
            }else if(flag != null && flag.trim().equals("2")){ //����->���ݳ��ⵥID��ó��ⵥ����ϸ�б�
                //���ⵥ��ϸ
                List<OutboundInvoiceDetail> lsDetail = outboundBus.getOutBoundDetailsById(invoiceid);
                
                strUrl = "/standard/jsp/outbound/review/outbound_review_dlist.jsp";
                request.setAttribute("invoicedetail", lsDetail);    
            }else if(flag != null && flag.trim().equals("3")){//����->�����ݸ���->���ݳ��ⵥID��ó��ⵥ��ϸ
                //���ⵥ��ϸ
                List<OutboundInvoiceDetail> lsDetail = outboundBus.getOutBoundDetailsById(invoiceid);
                
                strUrl = "/standard/jsp/outbound/review/outbound_review_invoice_list.jsp";
                request.setAttribute("invoicedetail", lsDetail);        
            }else if(flag != null && flag.trim().equals("4")){//����->����ϸ����->��ѯ����ɵ���ҵ
                
                String strQueryHQL = reviewBus.getJobDetailQuerySQL(invoiceid);
                String strCountHQL = reviewBus.getJobDetailCountSQL(invoiceid);
                
                //��תҳ��
                strUrl = "/standard/jsp/outbound/review/outbound_review_detail_job.jsp";    
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 10);
                List ls = pt.getLsResult();//��ѯ���
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("outboundPagejob", pt);
            }else if(flag != null && flag.trim().equals("5"))//����->����ϸ����->��ѯ�ѵļ�¼
            {
                String strQueryHQL = reviewBus.getReviewQuerySQL(invoiceid);
                String strCountHQL = reviewBus.getReviewCountSQL(invoiceid);
                
                //��תҳ��
                strUrl = "/standard/jsp/outbound/review/outbound_review_detail_crosscheck.jsp";
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 8);
                List ls = pt.getLsResult();//��ѯ���
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("outboundPagereview", pt);
            }
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("�������==>����==>�����ѯʧ��:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /** 
     * ����: �����ݸ���
     * @author hug 2012-10-19 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String invoiceReview(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
    {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        HttpSession httpsession = request.getSession(false);
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        
        //��ʶ 1:ȷ�ϸ��� 2��ȡ������
        String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        //��ǰ�û�
        String strUserCode = (String)httpsession.getAttribute("userCode");
        
        //���ⵥID
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
        //���ⵥ��ϸID
        String invoicedetailid = CommonTools.getStrToGbk(request.getParameter("invoicedetailid"));
        //���˵�����
        String renum = CommonTools.getStrToGbk(request.getParameter("renum"));
        
        String strBackMsg = "Y";
        reviewBus = new ReviewBusImpl(dao);
        outboundBus = new OutBoundBusImpl(dao);
        try{
            if(flag != null && flag.trim().equals("1")){//ȷ�ϸ���(�����ݸ���)
                strBackMsg = reviewBus.invoiceReview(invoiceid, invoicedetailid, Double.parseDouble(renum), strUserCode);     
            }else if(flag != null && flag.trim().equals("2")){//ȡ������
                strBackMsg = reviewBus.deleteInvoiceReview(invoicedetailid, strUserCode);
            }
            //��תҳ��
            strUrl = "/standard/jsp/outbound/review/outbound_review_invoice_list.jsp";
            List<OutboundInvoiceDetail> lsDetail = outboundBus.getOutBoundDetailsById(invoiceid);
            request.setAttribute("invoicedetail", lsDetail);    
            //����ֵ
            request.setAttribute("back_msg", strBackMsg); 
            
            request.getRequestDispatcher(strUrl).forward(request, response);

        }catch(Exception er)
        {
            Logger.error("�û�["+strUserCode+"]���������==>�����ݸ���ʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "�û�["+strUserCode+"]��������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /** 
     * ����: ����ϸ����
     * @author hug 2012-10-19 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String detailReview(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
    {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        HttpSession httpsession = request.getSession(false);
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        
        //��ʶ 1:ȷ�ϸ��� 2��ȡ������
        String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        //��ǰ�û�
        String strUserCode = (String)httpsession.getAttribute("userCode");
        //���ⵥID
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
        //���ⵥ��ϸID
        String invoicedetailid = CommonTools.getStrToGbk(request.getParameter("invoicedetailid"));
        //��ҵID
        String jobid = CommonTools.getStrToGbk(request.getParameter("jobid"));
        //��ҵ��ϸID
        String jobdetailid = CommonTools.getStrToGbk(request.getParameter("jobdetailid"));
        //���˵�����
        String renum = CommonTools.getStrToGbk(request.getParameter("renum"));
        
        //����ID
        String strCrosscheckID = CommonTools.getStrToGbk(request.getParameter("crossid"));
        String strBackMsg = "Y";
        reviewBus = new ReviewBusImpl(dao);
        try{
            if(flag != null && flag.trim().equals("1")){//ȷ�ϸ���(����ϸ����)
                strBackMsg = reviewBus.detailReview(invoiceid, invoicedetailid, jobid, jobdetailid, Double.parseDouble(renum), strUserCode); 
            }else if(flag != null && flag.trim().equals("2")){//ȡ������
                strBackMsg = reviewBus.deleteDetailReview(strCrosscheckID, strUserCode);
            }

            String strQueryHQL = reviewBus.getJobDetailQuerySQL(invoiceid);
            String strCountHQL = reviewBus.getJobDetailCountSQL(invoiceid);
            
            //��תҳ��
            strUrl = "/standard/jsp/outbound/review/outbound_review_detail_job.jsp";    
            PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 10);
            List ls = pt.getLsResult();//��ѯ���
            //����ֵ
            request.setAttribute("back_msg", strBackMsg);
            request.setAttribute("exResultList", ls);
            request.setAttribute("pagingTool", pt);
            httpsession.setAttribute("outboundPagejob", pt);
            
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("�û�["+strUserCode+"]���������==>����ϸ����ʧ��:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    
  
 
}
