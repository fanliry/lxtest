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
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.inbound.IInBoundReceiptBus;
import com.wms3.bms.standard.business.inbound.impl.InBoundReceiptBusImpl;
import com.wms3.bms.standard.entity.inbound.InboundReceiptDetail;
import com.wms3.bms.standard.entity.inbound.InboundReceiptHeader;


/**
 * 
 * ����: �ջ�������
 * @author hug 2012-3-8
 * @since WMS 3.0
 */
public class InBoundReceiptAction extends AbstractAction{
    
    private IInBoundReceiptBus inReceiptBus;

    @Override
    public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        
        String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        
        String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));// �ֿ�ID
        String ownerid = CommonTools.getStrToGbk(request.getParameter("ownerid"));        //����
        String instatus = CommonTools.getStrToGbk(request.getParameter("instatus"));      //����״̬
        String intype = CommonTools.getStrToGbk(request.getParameter("intype"));          //�������
        String screatedate = CommonTools.getStrToGbk(request.getParameter("start_time")); //���ݿ�ʼ����ʱ��
        String ecreatedate = CommonTools.getStrToGbk(request.getParameter("end_time"));   //���ݽ�������ʱ��

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
        inReceiptBus = new InBoundReceiptBusImpl(dao);
        try
        {
             if(flag != null && flag.trim().equals("1")){//�½��ջ���->�ջ�����ѯ
                 
                 String strQueryHQL = inReceiptBus.getInboundReceiptQuerySQL(warehouseid, ownerid, instatus, intype, screatedate, ecreatedate);
                 String strCountHQL = inReceiptBus.getInboundReceiptCountSQL(warehouseid, ownerid, instatus, intype, screatedate, ecreatedate);   
                 
                 //��תҳ��
                 strUrl = "/standard/jsp/inbound/newreceipt/inbound_newshd_list.jsp";    
                 PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                 List ls = pt.getLsResult();//��ѯ���
                 request.setAttribute("exResultList", ls);
                 request.setAttribute("pagingTool", pt);
                 httpsession.setAttribute("paging", pt);

             }else if(flag != null && flag.trim().equals("re1")){//�ջ�->�ջ�����ѯ
                 
                 String strQueryHQL = inReceiptBus.getInboundReceiptQuerySQL(warehouseid, ownerid, instatus, intype, screatedate, ecreatedate);
                 String strCountHQL = inReceiptBus.getInboundReceiptCountSQL(warehouseid, ownerid, instatus, intype, screatedate, ecreatedate);   
                 
                 //��תҳ��
                 strUrl = "/standard/jsp/inbound/receipt/inbound_receipt_list.jsp";    
                 PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                 List ls = pt.getLsResult();//��ѯ���
                 request.setAttribute("exResultList", ls);
                 request.setAttribute("pagingTool", pt);
                 httpsession.setAttribute("paging", pt);

             }else if(flag != null && flag.trim().equals("put1")){//�ϼ�->�ջ�����ѯ
                 
                 String strQueryHQL = inReceiptBus.getInboundReceiptQuerySQL(warehouseid, ownerid, instatus, intype, screatedate, ecreatedate);
                 String strCountHQL = inReceiptBus.getInboundReceiptCountSQL(warehouseid, ownerid, instatus, intype, screatedate, ecreatedate);   
                 
                 //��תҳ��
                 strUrl = "/standard/jsp/inbound/putaway/inbound_putaway_list.jsp";    
                 PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                 List ls = pt.getLsResult();//��ѯ���
                 request.setAttribute("exResultList", ls);
                 request.setAttribute("pagingTool", pt);
                 httpsession.setAttribute("paging", pt);

             }else if(flag != null && flag.trim().equals("2")){//�ջ�����ӡ
                 //�ջ���
                 InboundReceiptHeader inBound = inReceiptBus.getInBoundReceiptInvoiceById(invoiceid);
                 //�ջ�����ϸ
                 List<InboundReceiptDetail> lsDetail = inReceiptBus.getInBoundReceiptDetailsById(invoiceid);
                 
                 strUrl = "/standard/jsp/inbound/receipt/inbound_receipt_print.jsp";
                 
                 request.setAttribute("invoice", inBound);
                 request.setAttribute("invoicedetail", lsDetail); 
                 
             }else if(flag != null && flag.trim().equals("3")){ //�½��ջ���->�����ջ���ID����ջ�����ϸ�б�
                 //�ջ�����ϸ
                 List<InboundReceiptDetail> lsDetail = inReceiptBus.getInBoundReceiptDetailsById(invoiceid);
                 
                 strUrl = "/standard/jsp/inbound/newreceipt/inbound_newshd_dlist.jsp";
                 request.setAttribute("invoicedetail", lsDetail);
                 
             }else if(flag != null && flag.trim().equals("re3")){ //�ջ�->�����ջ���ID����ջ�����ϸ�б�
                 //�ջ�����ϸ
                 List<InboundReceiptDetail> lsDetail = inReceiptBus.getInBoundReceiptDetailsById(invoiceid);
                 
                 strUrl = "/standard/jsp/inbound/receipt/inbound_receipt_dlist.jsp";
                 request.setAttribute("invoicedetail", lsDetail);
                 
             }else if(flag != null && flag.trim().equals("4")){//�����ջ�����ID����ջ�����
                 //�ջ���
                 InboundReceiptHeader inBound = inReceiptBus.getInBoundReceiptInvoiceById(invoiceid);
                 strUrl = "/standard/jsp/inbound/newreceipt/inbound_newshd_update.jsp";
                 request.setAttribute("invoice", inBound);     
             }else if(flag != null && flag.trim().equals("6")){//�½��ջ���->��ϸ->��ѯ�ջ�����ϸ
                 //�ջ�����ϸ
                 List<InboundReceiptDetail> lsDetail = inReceiptBus.getInBoundReceiptDetailsById(invoiceid);
                 strUrl = "/standard/jsp/inbound/newreceipt/inbound_newshd_detail_list.jsp";
                 request.setAttribute("invoicedetail", lsDetail);
             }   
             
             request.getRequestDispatcher(strUrl).forward(request, response);
        }catch(Exception er)
        {
            Logger.error("������==>����ջ�������:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * ���ܣ���������ջ���
     * @author hug 2012-3-8 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String addRicoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        
        String warehouseid = CommonTools.getStrToGb2312(request.getParameter("warehouseid")); // �ֿ�ID
        String strUserCode = (String)request.getSession().getAttribute("userCode");           //��ǰ�û�
        
        String strInType = CommonTools.getStrToGbk(request.getParameter("intype"));             //�������
        
        String strCustomerId = CommonTools.getStrToGbk(request.getParameter("customer_id"));    //���� 
        String strArrivestarttime = CommonTools.getStrToGbk(request.getParameter("arrivestarttime"));//Ԥ�ڵ���ʱ���
        String strArriveendtime = CommonTools.getStrToGbk(request.getParameter("arriveendtime"));    //Ԥ�ڵ���ʱ�䵽
        
        String strShipperid = CommonTools.getStrToGbk(request.getParameter("shipperid"));        //������   
        String strSupplierid = CommonTools.getStrToGbk(request.getParameter("supplierid"));      //��Ӧ��
        String strReceiveloc = CommonTools.getStrToGbk(request.getParameter("receiveloc"));      //�ջ���̨
        
        String strCustomsno = CommonTools.getStrToGbk(request.getParameter("customsno"));       //���ر�����
        String strReserve1 = CommonTools.getStrToGbk(request.getParameter("reserve1"));         //�û��Զ���1
        String strReserve2 = CommonTools.getStrToGbk(request.getParameter("reserve2"));         //�û��Զ���2
        
        String strRemark = CommonTools.getStrToGbk(request.getParameter("remark"));        //��ע
          
        inReceiptBus = new InBoundReceiptBusImpl(dao);
        String strMsg = "Y";
        try
        {
            //����ջ���
            InboundReceiptHeader inBoundReceipt = new InboundReceiptHeader();
            inBoundReceipt.setWarehouseid(warehouseid);    //�ֿ���
            inBoundReceipt.setInvoicedate(StrTools.getCurrDateTime(8));//Ĭ��Ϊ��������
            inBoundReceipt.setCreatetime(StrTools.getCurrDateTime(2));//��������ʱ��
            inBoundReceipt.setCreatemanid(strUserCode);//����������Ա���
            inBoundReceipt.setInstatus("0");//����״̬
            inBoundReceipt.setIntype(strInType);//�������
            inBoundReceipt.setOwnerid(strCustomerId);//����
            
            inBoundReceipt.setArrivestarttime(strArrivestarttime);//Ԥ�ڵ���ʱ���
            inBoundReceipt.setArriveendtime(strArriveendtime);//Ԥ�ڵ���ʱ�䵽
            inBoundReceipt.setShipperid(strShipperid);//������
            inBoundReceipt.setSupplierid(strSupplierid);//��Ӧ��
            inBoundReceipt.setReceiveloc(strReceiveloc);//�ջ���̨
            
            inBoundReceipt.setCustomsno(strCustomsno);//���ر�����
            inBoundReceipt.setReserve1(strReserve1);//�û��Զ���1
            inBoundReceipt.setReserve2(strReserve2);//�û��Զ���2
            inBoundReceipt.setRemark(strRemark);     //��ע
            String strID = inReceiptBus.addInBoundReceipt(inBoundReceipt);
               
   
            //��תҳ��
            strUrl = "/standard/jsp/inbound/newreceipt/inbound_newshd_list.jsp";
            //����ֵ
            request.setAttribute("back_msg", strMsg);
            request.setAttribute("InvoicedId", strID);
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("������==>����ջ����ݳ���:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    
    /**
     * ���ܣ��޸�����ջ���
     * @author hug 2012-3-13 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String updateRicoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        
        String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // �ֿ�ID
        String strUserCode = (String)request.getSession().getAttribute("userCode");           //��ǰ�û�
        
        //�ջ���ID
        String strInvoiceId = CommonTools.getStrToGbk(request.getParameter("invoiceid")); 
        
        String strInType = CommonTools.getStrToGbk(request.getParameter("intype"));             //�������
        String strCustomerId = CommonTools.getStrToGbk(request.getParameter("customer_id"));    //���� 
        String strArrivestarttime = CommonTools.getStrToGbk(request.getParameter("arrivestarttime"));//Ԥ�ڵ���ʱ���
        String strArriveendtime = CommonTools.getStrToGbk(request.getParameter("arriveendtime"));    //Ԥ�ڵ���ʱ�䵽
        
        String strShipperid = CommonTools.getStrToGbk(request.getParameter("shipperid"));        //������   
        String strSupplierid = CommonTools.getStrToGbk(request.getParameter("supplierid"));      //��Ӧ��
        String strReceiveloc = CommonTools.getStrToGbk(request.getParameter("receiveloc"));      //�ջ���̨
        
        String strCustomsno = CommonTools.getStrToGbk(request.getParameter("customsno"));       //���ر�����
        String strReserve1 = CommonTools.getStrToGbk(request.getParameter("reserve1"));         //�û��Զ���1
        String strReserve2 = CommonTools.getStrToGbk(request.getParameter("reserve2"));         //�û��Զ���2
        
        String strRemark = CommonTools.getStrToGbk(request.getParameter("remark"));        //��ע
        inReceiptBus = new InBoundReceiptBusImpl(dao);
        String strMsg = "";
        try
        {
            //Ҫ�޸�����ջ���������
            InboundReceiptHeader inBoundReceipt = inReceiptBus.getInBoundReceiptInvoiceById(strInvoiceId);
            inBoundReceipt.setWarehouseid(warehouseid);    //�ֿ���
            inBoundReceipt.setUpdatemanid(strUserCode);    //�����޸���Ա���
            inBoundReceipt.setIntype(strInType);//�������
            inBoundReceipt.setOwnerid(strCustomerId);//����         
            inBoundReceipt.setArrivestarttime(strArrivestarttime);//Ԥ�ڵ���ʱ���
            inBoundReceipt.setArriveendtime(strArriveendtime);//Ԥ�ڵ���ʱ�䵽
            inBoundReceipt.setShipperid(strShipperid);//������
            inBoundReceipt.setSupplierid(strSupplierid);//��Ӧ��
            inBoundReceipt.setReceiveloc(strReceiveloc);//�ջ���̨
            
            inBoundReceipt.setCustomsno(strCustomsno);//���ر�����
            inBoundReceipt.setReserve1(strReserve1);//�û��Զ���1
            inBoundReceipt.setReserve2(strReserve2);//�û��Զ���2
            inBoundReceipt.setRemark(strRemark);     //��ע
            strMsg = inReceiptBus.updateInBoundReceipt(inBoundReceipt);
            
            //ˢ�� 
            PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
            List ls = null;
            String strUrl = null;
            if(pt != null)
            {
                ls = CommonPagination.getPagingList(dao, pt);
                
                strUrl = pt.getM_strUrl();
            }
            if(strUrl == null)
            {
                //��תҳ��
                strUrl = "/standard/jsp/inbound/newreceipt/inbound_newshd_list.jsp";
            }
            request.setAttribute("exResultList", ls);
            request.setAttribute("pagingTool", pt);
            httpsession.setAttribute("paging", pt);
           
            //����ֵ
            request.setAttribute("back_msg", strMsg);//���ز������
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("������==>�޸�����ջ����ݳ���:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * ���ܣ���������ջ�����ϸ
     * @author hug 2012-3-13 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String addDetailRicoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        
        //String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // �ֿ�ID
        //String strUserCode = (String)request.getSession().getAttribute("userCode");           //��ǰ�û�
        //�ջ�����ID
        String strInvoiceId = CommonTools.getStrToGbk(request.getParameter("invoiceid")); 
        
        String strProductId = CommonTools.getStrToGbk(request.getParameter("productid"));    //Ʒ�����
        String strPackId = CommonTools.getStrToGbk(request.getParameter("packid"));          //��װ   
        String strEunit = CommonTools.getStrToGbk(request.getParameter("eunit"));            //��λ
        String strInvoiceNum = CommonTools.getStrToGbk(request.getParameter("invoicenum"));  //��С��λ����
        String strVolume = CommonTools.getStrToGbk(request.getParameter("volume"));          //���
        String strWeight = CommonTools.getStrToGbk(request.getParameter("weight"));          //����
        String strNetweight = CommonTools.getStrToGbk(request.getParameter("netweight"));    //����
        String strPrice = CommonTools.getStrToGbk(request.getParameter("price"));            //�۸�
        String strReclocation = CommonTools.getStrToGbk(request.getParameter("reclocation"));//�ջ���λ
        String strSkustatus = CommonTools.getStrToGbk(request.getParameter("skustatus"));  //��Ʒ״̬����
        String strSkustatus_descr = CommonTools.getStrToGbk(request.getParameter("skustatus_descr"));//��Ʒ״̬����

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
        
        inReceiptBus = new InBoundReceiptBusImpl(dao);
        String strMsg = "";
        try
        {
            //����ջ�����ϸ
            InboundReceiptDetail receiptDetail = new InboundReceiptDetail();
            receiptDetail.setReinvoiceid(strInvoiceId); //�ջ������
            receiptDetail.setProductid(strProductId);   //Ʒ��
            receiptDetail.setPackid(strPackId);         //��װ
            receiptDetail.setEunit(strEunit);
            receiptDetail.setInvoicenum(Double.parseDouble(strInvoiceNum)); //��С��λ����
            receiptDetail.setVolume(Double.parseDouble(strVolume));         //���
            receiptDetail.setWeight(Double.parseDouble(strWeight));         //����
            receiptDetail.setNetweight(Double.parseDouble(strNetweight));   //����
            receiptDetail.setPrice(Double.parseDouble(strPrice));//����
            receiptDetail.setReclocation(strReclocation);        //�ջ�����
            receiptDetail.setSkustatus(strSkustatus);            //��Ʒ״̬����
            receiptDetail.setSkustatus_descr(strSkustatus_descr);//��Ʒ״̬����
            receiptDetail.setLinestatus("0");   //������״̬0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�
            
            receiptDetail.setLotid(strLotid);    //��������ID
            receiptDetail.setLotatt1(strLotatt1);//��������1
            receiptDetail.setLotatt2(strLotatt2);//��������2
            receiptDetail.setLotatt3(strLotatt3);//��������3
            receiptDetail.setLotatt4(strLotatt4);//��������4
            receiptDetail.setLotatt5(strLotatt5);//��������5
            receiptDetail.setLotatt6(strLotatt6);//��������6
            receiptDetail.setLotatt7(strLotatt7);//��������7
            receiptDetail.setLotatt8(strLotatt8);//��������8
            receiptDetail.setLotatt9(strLotatt9);//��������9
            receiptDetail.setLotatt10(strLotatt10);//��������10
            receiptDetail.setLotatt11(strLotatt11);//��������11
            receiptDetail.setLotatt12(strLotatt12);//��������12
            
            
            strMsg = inReceiptBus.addInBoundReceiptDetail(receiptDetail, strInvoiceId);
            
            List<InboundReceiptDetail> lsDetail = inReceiptBus.getInBoundReceiptDetailsById(strInvoiceId);
            
            //��תҳ��
            strUrl = "/standard/jsp/inbound/newreceipt/inbound_newshd_detail_list.jsp";
            //����ֵ
            request.setAttribute("back_msg", strMsg);//���ز������
            request.setAttribute("invoicedetail", lsDetail);
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("������==>��������ջ�����ϸ����:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * ���ܣ��޸�����ջ�����ϸ
     * @author hug 2012-3-13 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String updateDetailRicoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        
        //String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // �ֿ�ID
        //String strUserCode = (String)request.getSession().getAttribute("userCode");           //��ǰ�û�
        
        //�ջ�����ϸID
        String strInvoiceDetailId = CommonTools.getStrToGbk(request.getParameter("detailid")); 
        
        String strProductId = CommonTools.getStrToGbk(request.getParameter("productid"));    //Ʒ�����
        String strPackId = CommonTools.getStrToGbk(request.getParameter("packid"));          //��װ
        String strEunit = CommonTools.getStrToGbk(request.getParameter("eunit"));           //��λ
        String strInvoiceNum = CommonTools.getStrToGbk(request.getParameter("invoicenum"));  //��С��λ����
        String strVolume = CommonTools.getStrToGbk(request.getParameter("volume"));          //���
        String strWeight = CommonTools.getStrToGbk(request.getParameter("weight"));          //����
        String strNetweight = CommonTools.getStrToGbk(request.getParameter("netweight"));    //����
        String strPrice = CommonTools.getStrToGbk(request.getParameter("price"));            //�۸�
        String strReclocation = CommonTools.getStrToGbk(request.getParameter("reclocation"));  //�ջ���λ
        String strSkustatus = CommonTools.getStrToGbk(request.getParameter("skustatus"));  //��Ʒ״̬����
        String strSkustatus_descr = CommonTools.getStrToGbk(request.getParameter("skustatus_descr"));//��Ʒ״̬����

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
            
        inReceiptBus = new InBoundReceiptBusImpl(dao);
        String strMsg = "";
        try
        {
            //�ջ�����ϸ
            InboundReceiptDetail receiptDetail = inReceiptBus.getInBoundReceiptDetailByDetailId(strInvoiceDetailId); 
            receiptDetail.setProductid(strProductId);//Ʒ�����
            receiptDetail.setPackid(strPackId);      //��װ
            receiptDetail.setEunit(strEunit);
            receiptDetail.setInvoicenum(Double.parseDouble(strInvoiceNum)); //��С��λ����
            receiptDetail.setVolume(Double.parseDouble(strVolume));         //���
            receiptDetail.setWeight(Double.parseDouble(strWeight));         //����
            receiptDetail.setNetweight(Double.parseDouble(strNetweight));   //����
            receiptDetail.setPrice(Double.parseDouble(strPrice));//����
            receiptDetail.setReclocation(strReclocation);//�ջ���λ
            receiptDetail.setSkustatus(strSkustatus);            //��Ʒ״̬����
            receiptDetail.setSkustatus_descr(strSkustatus_descr);//��Ʒ״̬����
            
            receiptDetail.setLotid(strLotid);    //��������ID
            receiptDetail.setLotatt1(strLotatt1);//��������1
            receiptDetail.setLotatt2(strLotatt2);//��������2
            receiptDetail.setLotatt3(strLotatt3);//��������3
            receiptDetail.setLotatt4(strLotatt4);//��������4
            receiptDetail.setLotatt5(strLotatt5);//��������5
            receiptDetail.setLotatt6(strLotatt6);//��������6
            receiptDetail.setLotatt7(strLotatt7);//��������7
            receiptDetail.setLotatt8(strLotatt8);//��������8
            receiptDetail.setLotatt9(strLotatt9);//��������9
            receiptDetail.setLotatt10(strLotatt10);//��������10
            receiptDetail.setLotatt11(strLotatt11);//��������11
            receiptDetail.setLotatt12(strLotatt12);//��������12
            
            strMsg = inReceiptBus.updateInBoundReceiptDetail(receiptDetail);
            
            List<InboundReceiptDetail> lsDetail = inReceiptBus.getInBoundReceiptDetailsById(receiptDetail.getReinvoiceid());
            
            //��תҳ��
            strUrl = "/standard/jsp/inbound/newreceipt/inbound_newshd_detail_list.jsp";
            //����ֵ
            request.setAttribute("back_msg", strMsg);//���ز������
            request.setAttribute("invoicedetail", lsDetail);
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("������==>�޸�����ջ�����ϸ����:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * ���ܣ�ɾ�����ݻ򵥾���ϸ
     * @author hug 2012-3-13 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String deleteRicoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        
        //String strUserCode = (String)request.getSession().getAttribute("userCode"); //��ǰ�û�
        //�������� 1��ɾ������ 2��ɾ��������ϸ
        String strFlag = CommonTools.getStrToGbk(request.getParameter("flag"));
        //����ID�б�
        String strInvoiceIds = CommonTools.getStrToGbk(request.getParameter("invoiceids")); 
        
        //����ID
        String strInvoiceId = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
        //������ϸID (ɾ������ʱ����Ҫ)�б�
        String strInvoiceDetailIds = CommonTools.getStrToGbk(request.getParameter("detailids"));

        inReceiptBus = new InBoundReceiptBusImpl(dao);
        String strMsg = "";
        try
        {
            List<InboundReceiptDetail> lsDetail = null;
            if(strFlag != null && strFlag.trim().equals("1")){ //1��ɾ������)
                if(strInvoiceIds != null)
                {   
                    String strIds[] = strInvoiceIds.split(",");    
                    for(int i = 0; i < strIds.length; i++)
                    {
                        //����ID
                        String strInvoice = strIds[i];
                        strMsg = strMsg + inReceiptBus.deleteReceiptInvoice(strInvoice, null, strFlag);
                    }
                }
                //��תҳ��
                strUrl = "/standard/jsp/inbound/newreceipt/inbound_newshd_list.jsp";
                
            }else if(strFlag != null && strFlag.trim().equals("2")){ //2��ɾ��������ϸ
                if(strInvoiceDetailIds != null)
                {   
                    String strIds[] = strInvoiceDetailIds.split(",");    
                    for(int i = 0; i < strIds.length; i++)
                    {
                        //������ϸ
                        String strInvoiceDetailId = strIds[i];
                        strMsg = strMsg + inReceiptBus.deleteReceiptInvoice(strInvoiceId, strInvoiceDetailId, strFlag);
                    }
                }
                lsDetail = inReceiptBus.getInBoundReceiptDetailsById(strInvoiceId);
                //��תҳ��
                strUrl = "/standard/jsp/inbound/newreceipt/inbound_newshd_detail_list.jsp";
            }

            //����ֵ
            request.setAttribute("back_msg", strMsg);//���ز������
            request.setAttribute("invoicedetail", lsDetail);
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("������==>ɾ������ջ������ջ�����ϸ(" + strFlag + ")����:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }

    
    

}
