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
import com.wms3.bms.standard.business.outbound.IPickBus;
import com.wms3.bms.standard.business.outbound.impl.OutBoundBusImpl;
import com.wms3.bms.standard.business.outbound.impl.PickBusImpl;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;

/**
 * ����: �������
 * @author hug 2012-9-18
 * @since WMS 3.0
 */
public class PickAction extends AbstractAction{
    protected IOutBoundBus outboundBus;
    protected IPickBus pickBus;
    public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
    {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        
        outboundBus = new OutBoundBusImpl(dao);
        pickBus = new PickBusImpl(dao);
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
            
            if(flag != null && flag.trim().equals("1")){//���->���ⵥ��ѯ
                
                String strQueryHQL = outboundBus.getOutBoundQuerySQL(warehouseid, outstatus, outtype, shiftid, screatedate, ecreatedate, customerid, outno);
                String strCountHQL = outboundBus.getOutBoundCountSQL(warehouseid, outstatus, outtype, shiftid, screatedate, ecreatedate, customerid, outno);
                
                //��תҳ��
                strUrl = "/standard/jsp/outbound/pick/outbound_pick_list.jsp";    
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//��ѯ���
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("paging", pt);
                
            }else if(flag != null && flag.trim().equals("2")){ //���->���ݳ��ⵥID��ó��ⵥ����ϸ�б�
                //���ⵥ��ϸ
                List<OutboundInvoiceDetail> lsDetail = outboundBus.getOutBoundDetailsById(invoiceid);
                
                strUrl = "/standard/jsp/outbound/pick/outbound_pick_dlist.jsp";
                request.setAttribute("invoicedetail", lsDetail);    
            }else if(flag != null && flag.trim().equals("3")){//���->ִ�м��->���ݳ��ⵥID��ü����ҵ�б�
                String strQueryHQL = pickBus.getPickJobQuerySQL(invoiceid);
                String strCountHQL = pickBus.getPickJobCountSQL(invoiceid);    
                //��תҳ��
                strUrl = "/standard/jsp/outbound/pick/outbound_pick_execute_job.jsp";   
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 15);
                List ls = pt.getLsResult();//��ѯ���
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("inboundPagejob", pt); 
                
            }else if(flag != null && flag.trim().equals("4")){ 
                
                
            }else if(flag != null && flag.trim().equals("5")){  //���->��ӡ�������  
                //��תҳ��
                strUrl = "/standard/jsp/outbound/pick/outbound_pick_print.jsp"; 
                List lsJob = pickBus.getPickJob(invoiceid, invoicedetailid);
                request.setAttribute("exResultList", lsJob);
            }else if(flag != null && flag.trim().equals("6")){  //���->��ӡ���������ϸ
                //��תҳ��
                strUrl = "/standard/jsp/outbound/pick/outbound_pick_print_detail.jsp"; 
                List lsJobDetail = pickBus.getPickJobDetail(invoiceid, invoicedetailid);
                request.setAttribute("exResultList", lsJobDetail);
            }
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("�������==>���==>�����ѯʧ��:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * ����: ִ�м��
     * @author hug 2012-10-23 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String executePick(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
    {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        HttpSession httpsession = request.getSession(false);
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        
        //��ǰ�û�
        String strUserCode = (String)httpsession.getAttribute("userCode");
        //���ⵥID
        String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
        //��ҵ��ϸID
        String jobdetailid = CommonTools.getStrToGbk(request.getParameter("jobdetailid"));

        String strBackMsg = "Y";
        outboundBus = new OutBoundBusImpl(dao);
        pickBus = new PickBusImpl(dao);
        try{
            
            strBackMsg = pickBus.executePick(invoiceid, jobdetailid, strUserCode);
            
            String strQueryHQL = pickBus.getPickJobQuerySQL(invoiceid);
            String strCountHQL = pickBus.getPickJobCountSQL(invoiceid);    
            //��תҳ��
            strUrl = "/standard/jsp/outbound/pick/outbound_pick_execute_job.jsp";   
            PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, 15);
            List ls = pt.getLsResult();//��ѯ���
            request.setAttribute("exResultList", ls);
            request.setAttribute("pagingTool", pt);
            httpsession.setAttribute("inboundPagejob", pt);   
            //����ֵ
            request.setAttribute("back_msg", strBackMsg);   
            request.getRequestDispatcher(strUrl).forward(request, response);

        }catch(Exception er)
        {
            Logger.error("�û�["+strUserCode+"]���������==>���ʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "�û�["+strUserCode+"]��������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    
  
 
}
