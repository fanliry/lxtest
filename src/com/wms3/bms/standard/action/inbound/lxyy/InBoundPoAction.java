package com.wms3.bms.standard.action.inbound.lxyy;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.pagination.PagingTool;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.standard.business.inbound.IInBoundPoBus;
import com.wms3.bms.standard.business.inbound.impl.InBoundBusImpl;
import com.wms3.bms.standard.business.inbound.impl.InboundPoBusImpl;
import com.wms3.bms.standard.constant.CommonReturn;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundPoHeader;

/**
 * ����:
 * @author yaohb 2014-3-2
 * @since wmsBMS3.0
 */
public class InBoundPoAction extends AbstractAction {
	
	protected IInBoundPoBus inBoundPoBus;
	static final String V_RKDDETAIL = "V-rkddetail";
	protected int maxLine = 6;
	/* (non-Javadoc)
	 * @see com.ricosoft.action.AbstractAction#ricoExec(java.util.Hashtable, java.util.Hashtable)
	 */
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		String warehouseid = (String) hsSysParam.get("c_warhouse_id");	//�ֿ�
		String customerid = CommonTools.getStrToGbk(request.getParameter("customerid"));	//�ͻ�id
		String createdate = CommonTools.getStrToGbk(request.getParameter("createdate"));	//��������
		String poid = CommonTools.getStrToGbk(request.getParameter("poid"));	//�ɹ���id
		String potype = CommonTools.getStrToGbk(request.getParameter("potype"));
		
		String start_time = CommonTools.getStrToGbk(request.getParameter("start_time"));	
		String end_time = CommonTools.getStrToGbk(request.getParameter("end_time"));
		
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));	
		String RFflag = CommonTools.getStrToGbk(request.getParameter("RFflag"));	
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		inBoundPoBus = new InboundPoBusImpl(dao);
		try {
			if(flag!=null){
				if(flag.equals("1")){//��ö�����Ϣ	
					//��תҳ��
					//strUrl = "/standard/jsp/inbound/po/inbound_po_list.jsp";
					strUrl = "/standard/jsp/inbound/po/inbound_rkmgr_po_list.jsp";
					PagingTool pt = inBoundPoBus.getInboundPoHeaders("", customerid, createdate,"1",null, null, strUrl,maxLine);
					List ls = pt!=null?pt.getLsResult():null;
					request.setAttribute("exResultList", ls);
					httpsession.setAttribute("paging", pt);
				}else if(flag.equals("2")){
					//��תҳ��
					strUrl = "/standard/jsp/inbound/po/inbound_po_detail.jsp";
					maxLine = 50;
					PagingTool pt = inBoundPoBus.getInboundPoDelsByPoId(poid, strUrl, maxLine);
					List ls = pt.getLsResult();
					request.setAttribute("exResultList", ls);
					
				}else if(flag.equals("3")){//��ȡ��������̵Ķ���
					//��תҳ��
					strUrl = "/standard/jsp/inbound/po/inbound_rkmgr_po_list.jsp";
					PagingTool pt = inBoundPoBus.getInboundPoHeaders("", customerid, createdate,"2",null, null, strUrl,maxLine);
					List ls = pt!=null?pt.getLsResult():null;
					request.setAttribute("exResultList", ls);
					httpsession.setAttribute("paging", pt);
				}else if(flag.equals("3newin")){//��ȡ��������̵Ķ���
					//��תҳ��
					strUrl = "/standard/jsp/inbound/po/inbound_rkmgr_po_rk_list.jsp";
					PagingTool pt = inBoundPoBus.getInboundPoHeaders("", customerid, createdate,"2",null, null, strUrl,maxLine);
					List ls = pt!=null?pt.getLsResult():null;
					request.setAttribute("exResultList", ls);
					httpsession.setAttribute("paging", pt);
				}else if(flag.equals("4")){//��ȡ��������̵Ķ�����ϸ
					//��תҳ��
					strUrl = "/standard/jsp/inbound/po/inbound_rkmgr_po_detail.jsp";
					maxLine = 50;
					PagingTool pt = inBoundPoBus.getInboundPoDelsByPoId(poid, strUrl, maxLine);
					List ls = pt.getLsResult();
					request.setAttribute("exResultList", ls);
				}else if(flag.equals("5")){//��ȡ��������̵Ķ�����ϸ
					maxLine = 50;
					//��תҳ��
					strUrl = "/customs/yld/jsp/rf/inbound_po_detail.jsp";
					PagingTool pt = inBoundPoBus.getInboundPoDelsByPoId(poid, strUrl, maxLine);
					List ls=null;
					if(pt!=null){
						ls= pt.getLsResult();
					}
					request.setAttribute("exResultList", ls);
				}else if(flag.equals("6")){//��ö�����Ϣ	
					//��תҳ��
					//strUrl = "/standard/jsp/inbound/po/inbound_po_list.jsp";
					strUrl = "/standard/jsp/inbound/inothermgr/inbound_othermgr_list.jsp";
					if("rf".equals(RFflag)){
						strUrl = "/rf/lxyy/inother/inbound_othermgr_list.jsp";
						end_time = start_time;
					}
					PagingTool pt = inBoundPoBus.getInboundPoHeader("", customerid,  start_time,
							 end_time, null, poid, potype, strUrl, maxLine);
					List ls = pt!=null?pt.getLsResult():null;
					request.setAttribute("exResultList", ls);
					httpsession.setAttribute("paging", pt);
				}else if(flag.equals("7")){
					//��תҳ��
					strUrl = "/standard/jsp/inbound/inothermgr/inbound_othermgr_detail.jsp";
					if("rf".equals(RFflag)){
						strUrl = "/rf/lxyy/inother/inbound_othermgr_detail.jsp";
					}
					maxLine = 50;
					PagingTool pt = inBoundPoBus.getInboundPoDelsByPoId(poid, strUrl, maxLine);
					List ls = pt.getLsResult();
					request.setAttribute("exResultList", ls);
					
				}
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
		} catch (Exception er) {
			Logger.error("����������==>�ɹ�������ѯ����:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * ���ܣ����ݶ���������Ԥ�ڵ���֪ͨ��������ϸ
	 * @author liuxh 2014-1-21
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAddAsn(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
			
			HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
			HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
			EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
			HttpSession httpsession = request.getSession(false);
			
			String poid = CommonTools.getStrToGbk(request.getParameter("poid"));	//�ɹ���id
			String ids = CommonTools.getStrToGbk(request.getParameter("ids"));	    //������ϸ|����
			String asntype = CommonTools.getStrToGbk(request.getParameter("asntype"));	    //������ϸ|����
			String warehouseid = (String) hsSysParam.get("c_warhouse_id");	//�ֿ�
			String strUserCode = (String)request.getSession().getAttribute("userCode");           //��ǰ�û�
			String flag = CommonTools.getStrToGbk(request.getParameter("flag"));	//�ɹ���id
			String isReceipt = CommonTools.getStrToGbk(request.getParameter("isReceipt"));//�Ƿ�ֱ���ջ�
			inBoundPoBus = new InboundPoBusImpl(dao);
			//IInBoundAsnBus asnBus = new InBoundAsnBusImpl(dao);
			try {
				String 	strBackMsg="Y";	
				CommonReturn cReturn = inBoundPoBus.createAsnByPoId(poid,ids,asntype,warehouseid, strUserCode);
				strBackMsg = cReturn.getRetInfo();
				//��תҳ��
				if("rf".equals(flag)){
				strUrl = "/customs/yld/jsp/rf/RF_receipt_add.jsp";
				if(strBackMsg==null){
					strBackMsg="Y";
				}
				}else{
				strUrl = "/standard/jsp/inbound/asnmgr/inbound_newasn_list.jsp";
				}
				String invoiceid = cReturn.getStrCountHQL();//����id
				/*if("Y".equals(strBackMsg) && "1".equals(isReceipt))
				{
					List detail = asnBus.getInboundAsnDetailsByAsnId(invoiceid);
					for(int i = 0; i < detail.size(); i++)
					{
						IReceiptBus receiptBus = new ReceiptBusImpl(dao);
						String detailid = ((InboundAsnDetail)detail.get(i)).getAsndetailid();
						double num = ((InboundAsnDetail)detail.get(i)).getInvoicenum();
						String strMsg = "";
						strMsg = receiptBus.excuteReceipt(warehouseid, null, null, null, invoiceid, detailid, null,null, null, null, null, null, null, 
							null, null, null, null, null, null,num, 0, 0, 0, 0,  null, null, 0, null, null, strUserCode, null, null);	
						if(!"Y".equals(strMsg))
						{
							strBackMsg = strMsg;
							break;
						}
					  
					}
				}
				IInBoundAsnBus inBoundAsnBus = new InBoundAsnBusImpl(dao);*/
				/*PagingTool pt = inBoundAsnBus.getInboundAsnHeaders(warehouseid, "0", null, StrTools.getCurrDateTime(8) ,StrTools.getCurrDateTime(8), asntype, strUrl, maxLine);				
				List ls = pt!=null?pt.getLsResult():null; */
				//request.setAttribute("exResultList", ls);	
				request.setAttribute("InvoicedId", invoiceid);	
                //httpsession.setAttribute( "paging", pt);
				request.setAttribute("back_msg", strBackMsg);
				request.getRequestDispatcher(strUrl).forward(request, response);
			} catch (Exception er) {
				Logger.error("����������==>�ɹ�������ѯ����:" + er.getMessage());
	            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
	            request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
			return null;
		}
	/**
	 * ���ܣ����ݶ���������ⵥ����ϸ
	 * @author yao 2014-2-28
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAddInbound(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
			
			HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
			HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
			EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
			HttpSession httpsession = request.getSession(false);
			
			String warehouseid = (String) hsSysParam.get("c_warhouse_id");	//�ֿ�
			String poid = CommonTools.getStrToGbk(request.getParameter("poid"));	//��ⶩ��id
			String instockid = CommonTools.getStrToGbk(request.getParameter("instockid"));	//��ⵥ
			String ids = CommonTools.getStrToGbk(request.getParameter("ids"));	//��ⶩ����ϸ����
			String intype = CommonTools.getStrToGbk(request.getParameter("intype"));	//�������
			String departid = CommonTools.getStrToGbk(request.getParameter("departid"));  //����
			String strUserCode = (String)request.getSession().getAttribute("userCode");           //��ǰ�û�
	
			inBoundPoBus = new InboundPoBusImpl(dao);
			InBoundBusImpl inboundbusimpl = new InBoundBusImpl(dao);
			
			try {/*
				String 	strBackMsg="Y";	
				//��תҳ��
				strUrl = "/standard/jsp/inbound/rkdmgr/inbound_rkmgr_addbyorder_detail.jsp";
				List ls=null;
				if(ids!=null && ids.length()>0){
					String info[] = ids.split(",");
					if(info!=null){
						InboundPoHeader header = inBoundPoBus.getInboundPoHeaders(poid);
						if(header!=null ){
							synchronized (inboundbusimpl.newin_lock) {
								if(instockid!=null && !instockid.equals("")){//���Ѵ��ڵ���ⵥ��������Ϣ
									//InboundInvoiceHeader head = inboundbusimpl.getInboundInvoice(instockid);
									if(head!=null&&head.getInstatus()!=null&&head.getInstatus().equals("0")&&head.getInvoicesource()!=null&&head.getInvoicesource().equals("4")){
										List lsList = new ArrayList<InboundInvoiceDetail>();
						                List lsList2 = new ArrayList<InboundPoDetail>(); 
						                String sqlMax = "select max(detail.instockdetailid) from InboundInvoiceDetail as detail where detail.instockid='" + instockid + "'";
						                int sValue = SeqTool.getDetailMaxId(sqlMax, instockid.length(),dao);
										for(int i=0;i<info.length;i++){
											String msg[]=info[i].split("\\|"); // ������ϸid|����ֵ|����id
											InboundPoDetail podetail = inBoundPoBus.getInboundPoHeaders(poid,msg[0]);
											if(podetail!=null){
												InboundInvoiceDetail inDetail = new InboundInvoiceDetail();
								                inDetail.setInstockdetailid(instockid + SeqTool.getCode((sValue+i+1), StandardConstant.D_LEN));	//��ⵥ��ϸID
									            inDetail.setInstockid(instockid);	//��ⵥ�ݱ��
									            inDetail.setProductid(podetail.getProductid());		//Ʒ�����
									            inDetail.setPackid(podetail.getPackid());				//��װ
									            inDetail.setEunit(podetail.getEunit());				//��λ
									            inDetail.setInvoicenum(Double.parseDouble(msg[1]));		//������������
									            inDetail.setWhAreaId(msg[2]);
									            inDetail.setLinestatus("0");        	//������״̬0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�
									            inDetail.setReincoiceid(podetail.getPoid());//���Զ���id
									            inDetail.setReinvoicedetailid(podetail.getPodetailid());//���Զ�����ϸid
									            inDetail.setLotid(podetail.getLotid());
									            inDetail.setLotatt1(podetail.getLotatt1());
									            inDetail.setLotatt2(podetail.getLotatt2());
									            inDetail.setLotatt3(podetail.getLotatt3());
									            inDetail.setLotatt4(podetail.getLotatt4());
									            inDetail.setLotatt5(podetail.getLotatt5());
									            inDetail.setLotatt6(podetail.getLotatt6());
									            inDetail.setLotatt7(podetail.getLotatt7());
									            inDetail.setLotatt8(podetail.getLotatt8());
									            inDetail.setLotatt9(podetail.getLotatt9());
									            inDetail.setLotatt10(podetail.getLotatt10());
									            inDetail.setLotatt11(podetail.getLotatt11());
									            inDetail.setLotatt12(podetail.getLotatt12());
									            podetail.setUseponum(podetail.getUseponum()+Double.parseDouble(msg[1]));
									            
									            lsList.add(inDetail);
									            lsList2.add(podetail);
											} 
										}
										strBackMsg = inboundbusimpl.addInboundInvoice(null, lsList,lsList2);
									}else{
										strBackMsg = "����ⵥ���ǿ���״̬�������������ⵥ��ϸ";
									}
								}else{
									BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(dao);
					                BaseSeq  seqEn = seqDao.getSeqByType("RKD");
					                String strInvoiceNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), dao);
					                InboundInvoiceHeader inInvoice = new InboundInvoiceHeader();
									inInvoice.setInstockid(strInvoiceNo);  		//��ⵥ���
					                inInvoice.setWarehouseid(warehouseid);		//�ֿ���
					                inInvoice.setInvoicedate(StrTools.getCurrDateTime(8));    //��������
					                inInvoice.setCreatetime(StrTools.getCurrDateTime(2));    //��������ʱ��
					                inInvoice.setCreatemanid(strUserCode);    	//����������Ա���
					                inInvoice.setInstatus("0");       	//����״̬ 0-�½���1-��ˣ�2-ȷ�� 5-���� 
					                inInvoice.setIntype(intype);           //�������
					                inInvoice.setDepartid(departid);
					                inInvoice.setOpmode("1");         	//���ݴ���ʽ1-�������ݣ�Ĭ�ϣ�2-��Ŀ����
					                inInvoice.setUploadflag("1");     	//�ϴ���־0-���ϴ���Ĭ�ϣ�1-δ�ϴ�
					                inInvoice.setShiftid("");      //��ҵ���
					                inInvoice.setOnLineType("1");     	//����ģʽ 1.������Ĭ�ϣ�2.�ѻ�
					                inInvoice.setInpos("1");          	//����
					                inInvoice.setInvoicesource("4"); //������Դ
					                inInvoice.setCustomerid(header.getCustomerid());  	//�ͻ�
					                List lsList = new ArrayList<InboundInvoiceDetail>();
					                List lsList2 = new ArrayList<InboundPoDetail>(); 
					                String sqlMax = "select max(detail.instockdetailid) from InboundInvoiceDetail as detail where detail.instockid='" + strInvoiceNo + "'";
					                int sValue = SeqTool.getDetailMaxId(sqlMax, strInvoiceNo.length(),dao);
									for(int i=0;i<info.length;i++){
										String msg[]=info[i].split("\\|"); // ������ϸid|����ֵ|����id
										InboundPoDetail podetail = inBoundPoBus.getInboundPoHeaders(poid,msg[0]);
										if(podetail!=null){
											InboundInvoiceDetail inDetail = new InboundInvoiceDetail();
											inDetail.setInstockdetailid(SeqTool.getDetailId(strInvoiceNo,(sValue+i+1)+"",StandardConstant.D_LEN));	//��ⵥ��ϸID
							                //inDetail.setInstockdetailid(strInvoiceNo + SeqTool.getCode((sValue+i+1), StandardConstant.D_LEN));	//��ⵥ��ϸID
								            inDetail.setInstockid(strInvoiceNo);	//��ⵥ�ݱ��
								            inDetail.setProductid(podetail.getProductid());		//Ʒ�����
								            inDetail.setPackid(podetail.getPackid());				//��װ
								            inDetail.setEunit(podetail.getEunit());				//��λ
								            inDetail.setInvoicenum(Double.parseDouble(msg[1]));		//������������
								            inDetail.setWhAreaId(msg[2]);
								            inDetail.setLinestatus("0");        	//������״̬0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�
								            inDetail.setReincoiceid(podetail.getPoid());//���Զ���id
								            inDetail.setReinvoicedetailid(podetail.getPodetailid());//���Զ�����ϸid
								            inDetail.setLotid(podetail.getLotid());
								            inDetail.setLotatt1(podetail.getLotatt1());
								            inDetail.setLotatt2(podetail.getLotatt2());
								            inDetail.setLotatt3(podetail.getLotatt3());
								            inDetail.setLotatt4(podetail.getLotatt4());
								            inDetail.setLotatt5(podetail.getLotatt5());
								            inDetail.setLotatt6(podetail.getLotatt6());
								            inDetail.setLotatt7(podetail.getLotatt7());
								            inDetail.setLotatt8(podetail.getLotatt8());
								            inDetail.setLotatt9(podetail.getLotatt9());
								            inDetail.setLotatt10(podetail.getLotatt10());
								            inDetail.setLotatt11(podetail.getLotatt11());
								            inDetail.setLotatt12(podetail.getLotatt12());
								            podetail.setUseponum(podetail.getUseponum()+Double.parseDouble(msg[1]));
								            lsList.add(inDetail);
								            lsList2.add(podetail);
										} 
									}
									inboundbusimpl.addInboundInvoice(inInvoice, lsList,lsList2);
									instockid=strInvoiceNo;
								}
							}	
						}else{
							strBackMsg = "���ʧ��";
						}
					}
				}
				List<BaseLotViewsearch> lsLot = AbstractBus.getLotset(hsSysParam, V_RKDDETAIL);
				if(instockid!=null && instockid.length()>1){
					ls = inboundbusimpl.getInboundDetails(instockid,lsLot);
				}
				request.setAttribute("lsLot", lsLot);
				request.setAttribute("exResultList", ls);	
				request.setAttribute("instockid", instockid);
				request.setAttribute("back_msg", strBackMsg);
				request.getRequestDispatcher(strUrl).forward(request, response);*/
			} catch (Exception er) {
				Logger.error("��ⵥ����==>���������==>ѡ�񶩵�:" + er.getMessage());
	            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
	            request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
			return null;
		}
	public String ricoExecPrint(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String poid = CommonTools.getStrToGbk(request.getParameter("poid"));		//���ݱ��
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
		if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		maxLine = 50;
		inBoundPoBus = new InboundPoBusImpl(dao);
		try{

			InboundPoHeader inboundPoHeader=inBoundPoBus.getInboundPoHeaders(poid);
			PagingTool pl = inBoundPoBus.getInboundPoDelsByPoId(poid, strUrl, maxLine);
			List lsDetail =pl.getLsResult();
            strUrl = "/standard/jsp/inbound/inothermgr/inbound_othermgr_print.jsp";
            request.setAttribute("po", inboundPoHeader);
            request.setAttribute("podetail", lsDetail); 
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("�������==>����������==>��ⵥ��ӡʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * po��ҵ��Ϣ��ʾ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
     public String ricoExecJobDdis(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String podetailid = CommonTools.getStrToGbk(request.getParameter("podetailid"));	//������ϸ���
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
		if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		maxLine = 50;
		inBoundPoBus = new InboundPoBusImpl(dao);
		try{

			List<Object[]> ls=inBoundPoBus.getJobInfoByPodetailid(podetailid);
            strUrl = "/standard/jsp/inbound/job/inbound_jobinfo_display.jsp";
            request.setAttribute("jobinfo", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("�������==>����������==>��ⵥ��ӡʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

}
