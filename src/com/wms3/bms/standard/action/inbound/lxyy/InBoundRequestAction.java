package com.wms3.bms.standard.action.inbound.lxyy;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.inbound.IInBoundBus;
import com.wms3.bms.standard.business.inbound.impl.InBoundBusImpl;
import com.wms3.bms.standard.dao.base.impl.BaseSeqDaoImpl;
import com.wms3.bms.standard.entity.base.BaseLotSet;
import com.wms3.bms.standard.entity.base.BaseSeq;
import com.wms3.bms.standard.entity.inbound.InboundInvoiceDetail;
import com.wms3.bms.standard.entity.inbound.InboundInvoiceHeader;
import com.wms3.bms.standard.entity.inbound.lxyy.BindingRecord;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundRequestInvoiceDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundRequestInvoiceHeader;

/**
 * ����:������뵥����
 * @author yao
 *
 */
public class InBoundRequestAction extends AbstractAction
{
	protected IInBoundBus inBoundBus;
	protected List<BaseLotSet> lsLot;
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�ID
		String instockid = CommonTools.getStrToGbk(request.getParameter("instockid"));		//���ݺ�
		String invoicedate = CommonTools.getStrToGbk(request.getParameter("invoicedate"));			//��������
		String instatus = CommonTools.getStrToGb2312(request.getParameter("instatus"));		//����״̬
		String productName = CommonTools.getStrToGbk(request.getParameter("productName"));		//��Ʒ����
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));		//����
		
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));//��������
		String num = CommonTools.getStrToGbk(request.getParameter("num"));//����
		String requestvoicedetailid = CommonTools.getStrToGbk(request.getParameter("requestvoicedetailid"));//�굥id
		String bandrecordid = CommonTools.getStrToGbk(request.getParameter("bandrecordid"));//�󶨼�¼��
		
		String starttime = CommonTools.getStrToGbk(request.getParameter("starttime"));			//��ʼ����
		String endtime = CommonTools.getStrToGbk(request.getParameter("endtime"));			//��������
		String invoicetype = CommonTools.getStrToGbk(request.getParameter("invoicetype"));		//���ͣ���Ʒ��⣬�����˿⣩
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String errortextString ="";
		String back_msg = "Y";
		try{
			//ÿҳ��ʾ����
            int iline = 6;
            String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));
            if(linerow != null && linerow.length()>0){
                iline = Integer.parseInt(linerow);
            }
			if(flag != null && flag.equals("1")){ //������뵥���� ��ѯ������뵥
				
				back_msg = "";
				errortextString = "������==>����������==>������뵥����==>������뵥ʧ��:";
				strUrl = "/standard/jsp/inbound/scnew/inbound_scnewsqdmgr_list.jsp";
				InboundRequestInvoiceHeader invoiceHeader = new InboundRequestInvoiceHeader();
				/*��ѯ*/
				String strQueryHQL = invoiceHeader.getQueryHQL(warehouseid,instockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                String strCountHQL = invoiceHeader.getQueryHQLCount(warehouseid,instockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                
				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//��ѯ���
                
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				Logger.info("�û�["+strUserCode+"]��������==>����������==>������뵥����==>��ѯ");
					
			}else if(flag != null && flag.equals("2")){//������뵥���� ��ѯ��ϸ
			
				back_msg = "";
				errortextString = "������==>����������==>������뵥����==>��ѯ��ϸʧ��:";
				strUrl = "/standard/jsp/inbound/scnew/inbound_scnewsqdmgrde_list.jsp";
				InboundRequestInvoiceDetail invoicedetail = new InboundRequestInvoiceDetail();
				/*��ѯ*/
				List ls = dao.searchEntities(invoicedetail.getQueryHQLByid(instockid));
				request.setAttribute("invoicedetail", ls);
				Logger.info("�û�["+strUserCode+"]��������==>����������==>������뵥����==>������뵥��ϸ��ѯ");
			}else if(flag != null && flag.equals("3")){//������뵥���� ���
			
				errortextString = "������==>����������==>������뵥����==>���ʧ��:";
				strUrl = "/standard/jsp/inbound/scnew/inbound_scnewsqdmgr_list.jsp";
				InboundRequestInvoiceHeader invoiceHeader = new InboundRequestInvoiceHeader();
				/*��ѯ*/
				dao.updateSql(invoiceHeader.getUpdateAuditsql(instockid,strUserCode));
				
				String strQueryHQL = invoiceHeader.getQueryHQL(warehouseid,instockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                String strCountHQL = invoiceHeader.getQueryHQLCount(warehouseid,instockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                
				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//��ѯ���
                
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				Logger.info("�û�["+strUserCode+"]��������==>����������==>������뵥����==>��˵�"+instockid);
			}else if(flag != null && flag.equals("4")){//������뵥���� ȡ�����
			

				errortextString = "������==>����������==>������뵥����==>ȡ�����ʧ��:";
				strUrl = "/standard/jsp/inbound/scnew/inbound_scnewsqdmgr_list.jsp";
				InboundRequestInvoiceHeader invoiceHeader = new InboundRequestInvoiceHeader();
				/*��ѯ*/
				dao.updateSql(invoiceHeader.getUpdateCancelAuditsql(instockid));
				String strQueryHQL = invoiceHeader.getQueryHQL(warehouseid,instockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                String strCountHQL = invoiceHeader.getQueryHQLCount(warehouseid,instockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                
				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//��ѯ���
                
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				Logger.info("�û�["+strUserCode+"]��������==>����������==>������뵥����==>ȡ����˵�"+instockid);
			}else if(flag != null && flag.equals("5")){//������뵥���� ���ϵ���
			

				errortextString = "������==>����������==>������뵥����==>���ϵ���ʧ��:";
				strUrl = "/standard/jsp/inbound/scnew/inbound_scnewsqdmgr_list.jsp";
				InboundRequestInvoiceHeader invoiceHeader = new InboundRequestInvoiceHeader();
				/*��ѯ*/
				dao.updateSql(invoiceHeader.getUpdateDeleteAuditsql(instockid));
				String strQueryHQL = invoiceHeader.getQueryHQL(warehouseid,instockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                String strCountHQL = invoiceHeader.getQueryHQLCount(warehouseid,instockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                
				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//��ѯ���
                
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				Logger.info("�û�["+strUserCode+"]��������==>����������==>������뵥����==>���ϵ�"+instockid);
			}else if(flag != null && flag.equals("6")){//������뵥���� �ر����뵥
			
				
				errortextString = "������==>����������==>������뵥����==>�ر����뵥ʧ��:";
				strUrl = "/standard/jsp/inbound/scnew/inbound_scnewsqdmgr_list.jsp";
				InboundRequestInvoiceHeader invoiceHeader = new InboundRequestInvoiceHeader();
				/*��ѯ*/
				dao.updateSql(invoiceHeader.getUpdateCloseAuditsql(instockid));
				String strQueryHQL = invoiceHeader.getQueryHQL(warehouseid,instockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                String strCountHQL = invoiceHeader.getQueryHQLCount(warehouseid,instockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                
				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//��ѯ���
                
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				Logger.info("�û�["+strUserCode+"]��������==>����������==>������뵥����==>�ر����뵥"+instockid);
			}else if(flag != null && flag.equals("7")){//������뵥���� ��ӡ���뵥
				back_msg = "";

				errortextString = "������==>����������==>������뵥����==>��ӡ���뵥ʧ��:";
				strUrl = "/standard/jsp/inbound/scnew/inbound_rkd_print.jsp";
				InboundRequestInvoiceHeader invoiceHeader = new InboundRequestInvoiceHeader();
				InboundRequestInvoiceDetail invoicedetail = new InboundRequestInvoiceDetail();
				/*��ѯ*/
				InboundRequestInvoiceHeader invoice = invoiceHeader.getDaoQueryHql(instockid,dao);
				List lsdetail = dao.searchEntities(invoicedetail.getQueryHQLByid(instockid));
				
				request.setAttribute("instockid", instockid);
				request.setAttribute("invoice", invoice);
				request.setAttribute("invoicedetail", lsdetail);
				Logger.info("�û�["+strUserCode+"]��������==>����������==>������뵥����==>��ӡ���뵥"+instockid);
			}else if(flag != null && flag.equals("8")){//������뵥���� ��ӡ�󶨼�¼
				
				back_msg = "";
				errortextString = "������==>����������==>������뵥����==>��ӡ���뵥�󶨼�¼ʧ��:";
				strUrl = "/standard/jsp/inbound/scnew/inbound_rkdbr_print.jsp";
				InboundRequestInvoiceHeader invoiceHeader = new InboundRequestInvoiceHeader();
				BindingRecord record = new BindingRecord();
				/*��ѯ*/
				InboundRequestInvoiceHeader invoice = invoiceHeader.getDaoQueryHql(instockid,dao);
				List lsRecord = dao.searchEntities(record.getQueryHQLByid(instockid));
				
				request.setAttribute("instockid", instockid);
				request.setAttribute("invoice", invoice);
				request.setAttribute("lsrecord", lsRecord);
				Logger.info("�û�["+strUserCode+"]��������==>����������==>������뵥����==>��ӡ���뵥"+instockid+"�󶨼�¼");
			}else if(flag != null && flag.equals("9")){//������뵥���� ������뵥����Ϣ��ʾ
				back_msg = "";

				errortextString = "������==>����������==>������뵥����==>������뵥����Ϣ��ʾʧ��:";
				strUrl = "/standard/jsp/inbound/scnew/inbound_bindrecord_list.jsp";
				InboundRequestInvoiceHeader invoiceHeader = new InboundRequestInvoiceHeader();
				BindingRecord record = new BindingRecord();
				/*��ѯ*/
				InboundRequestInvoiceHeader invoice = invoiceHeader.getDaoQueryHql(instockid,dao);
				List lsRecord = dao.searchEntities(record.getQueryHQLByid(instockid));
				
				request.setAttribute("invoice", invoice);
				request.setAttribute("lsrecord", lsRecord);
				Logger.info("�û�["+strUserCode+"]��������==>����������==>������뵥����==>������뵥"+instockid+"����Ϣ��ʾ");
			}else if(flag != null && flag.equals("10")){//������뵥���� ������뵥����Ϣ��ʾ ȡ���󶨼�¼

				errortextString = "������==>����������==>������뵥����==>������뵥����Ϣ��ʾ==>ȡ���󶨼�¼ʧ��:";
				strUrl = "/standard/jsp/inbound/scnew/inbound_bindrecord_list.jsp";
				InboundRequestInvoiceHeader invoiceHeader = new InboundRequestInvoiceHeader();
				BindingRecord record = new BindingRecord();
				List records = dao.searchEntities(record.getQueryHQLByRecordid(bandrecordid));
				if(records!=null && records.size()==1){
					record = (BindingRecord)records.get(0);
					InboundRequestInvoiceDetail detail = new InboundRequestInvoiceDetail();
					detail = detail.getDetailByid(instockid,record.getInstockdetailid(),dao);
					if(detail!=null){
						double numx = detail.getBindingnum() - record.getNum();
						if(numx<=0){
							numx = 0;
						}
						detail.setBindingnum(numx);
						record.UpdateRequestDInBound(detail,record,dao);
					}else{
						back_msg = "û���ҵ���Ӧ�ĵ�����ϸ";
					}
					
				}
				/*��ѯ*/
				InboundRequestInvoiceHeader invoice = invoiceHeader.getDaoQueryHql(instockid,dao);
				List lsRecord = dao.searchEntities(record.getQueryHQLByid(instockid));
				
				request.setAttribute("invoice", invoice);
				request.setAttribute("lsrecord", lsRecord);
				Logger.info("�û�["+strUserCode+"]��������==>����������==>������뵥����==>������뵥"+instockid+"����Ϣ��ʾ==>ȡ���󶨼�¼");
			}else if(flag != null && flag.equals("RF1")){//RF������ ����
			
				errortextString = "RF������=>��ʧ��:";
				strUrl = "/rf/lxyy/bindtray.jsp";
				
				/*��ѯ*/
				InboundRequestInvoiceHeader invoice = new InboundRequestInvoiceHeader().getDaoQueryHql(instockid,dao);
				InboundRequestInvoiceDetail invoicedetail = new InboundRequestInvoiceDetail().getDetailByid(instockid, requestvoicedetailid, dao);
				if(invoice!=null){
					//��
		            BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(dao);
		            BaseSeq  seqEn = seqDao.getSeqByType("BDD");
		            String strRecordNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(),dao); 
		            
					BindingRecord record = new BindingRecord();
					double num1 = invoicedetail.getBindingnum();
					double num2 = Double.parseDouble(num);
					
					record.setBandrecordid(strRecordNo);
					record.setInstockid(instockid);
					record.setInstockdetailid(requestvoicedetailid);
					record.setProductid(invoicedetail.getProductid());
					record.setTraycode(traycode);
					record.setBandrecordtype(invoice.getInvoicetypeid());
					record.setPrintdate(invoicedetail.getPrintdate());
					record.setProductStatus(invoicedetail.getProductStatus());
					record.setLotid(invoicedetail.getLotid());
					record.setLotinfo(invoicedetail.getLotinfo());
					record.setNum(num2);
					record.setStatus("1");
					record.setPunit(invoicedetail.getPunit());
					record.setBandmanid(strUserCode);
					record.setCreatetime(StrTools.getCurrDateTime(2));
					
					invoicedetail.setBindingnum(num1+num2);
					invoice.setInstatus("3");
					
					dao.save(record);
					dao.update(invoicedetail);
					dao.update(invoice);
				}
				request.setAttribute("invoice", invoice);
				request.setAttribute("invoicedetail", invoicedetail);
				Logger.info("�û�["+strUserCode+"]��RF������=> ���뵥��Ϊ��"+instockid+"����������Ϊ��"+traycode);
			}
			request.setAttribute("back_msg", back_msg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
			Logger.error(errortextString + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

	
	/**
	 * ����:����������뵥
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecCreate(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String searchCondition = CommonTools.getStrToGbk(request.getParameter("searchCondition"));	//��ѯ����
		String warehouseid = CommonTools.getStrToGb2312(request.getParameter("warehouseid"));	//�ֿ�id
		String invoicetype = CommonTools.getStrToGb2312(request.getParameter("invoicetype"));	//�������
		String invoicedate = CommonTools.getStrToGb2312(request.getParameter("invoicedate"));	//��ӡ����
		String department = CommonTools.getStrToGb2312(request.getParameter("department"));	    //��������
		String remark = CommonTools.getStrToGb2312(request.getParameter("remark"));	        //��ע
		String detail = CommonTools.getStrToGb2312(request.getParameter("detail"));	//��ϸ
		String strUserCode = (String)httpsession.getAttribute("userCode");
		InboundRequestInvoiceHeader RequestHeader=null;
		InboundRequestInvoiceDetail  RequestDetail=null;
		
		try{
			String strBackMsg = "Y";
			strUrl = "/standard/jsp/inbound/scnew/inbound_scnewsqd_list.jsp";
			int rowLength = StrTools.StrTimes(detail,"|");
			 //������뵥��
            BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(dao);
            BaseSeq  seqEn = seqDao.getSeqByType("SQD");
            //String strRequestNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(),dao);
            String strRequestNo = SeqTool.getID(seqEn.getSeqType(), dao);
            
            List<InboundRequestInvoiceDetail> lsRequestDetail = new ArrayList<InboundRequestInvoiceDetail>();
			for(int i=0; i< rowLength; i++)
			{
				
				//��Ʒid   ��Ʒ��     ��λ     ��������    �ƻ�����   ��������  ������Ϣ
				String colums[] = StrTools.GetStrFlag(detail,"|",i).split(",");
				RequestDetail = new InboundRequestInvoiceDetail();
				RequestDetail.setInstockid(strRequestNo);  		//���뵥id
				RequestDetail.setProductid(colums[0]);  		//Ʒ��id
				RequestDetail.setPrintdate(colums[3]);  	    //��������
				RequestDetail.setPlannum(Double.parseDouble(colums[4]));  	//�ƻ�����
				RequestDetail.setLotid(colums[5]);  		   //��������
				RequestDetail.setLotinfo(colums[6]);  		   //��������
				RequestDetail.setProductStatus(colums[7]);		//��Ʒ״̬
				RequestDetail.setRemark("");  		           //��ע
				RequestDetail.setPunit(colums[2]); //��λ
				lsRequestDetail.add(RequestDetail);
				
			}
			if(lsRequestDetail!=null && lsRequestDetail.size()>0){
				//��ⵥ
				RequestHeader = new InboundRequestInvoiceHeader();
	            RequestHeader.setInstockid(strRequestNo);  		//���뵥id
	            RequestHeader.setInvoicetypeid(invoicetype);	//���뵥����id(��Ʒ��⣬�����˿�)
	            RequestHeader.setInvoicedate(invoicedate);  	    //����ʱ��
	            RequestHeader.setCreatetime(StrTools.getCurrDateTime(2));		//��������ʱ��
	            RequestHeader.setInstatus("1");  		//���뵥��״̬id 1-�½���2-��ˣ�3-���ְ󶨣�4-�ر� 5-���� 
	            RequestHeader.setCreatemanid(strUserCode);		//����������Ա���
	            RequestHeader.setDepartid(department);  		//��������
	            RequestHeader.setAuditmanid("");		//�����Id
	            RequestHeader.setWarehouseid(warehouseid);  	//�ջ��ֿ�
	            RequestHeader.setAuditdate("");		    //���ʱ��
	            RequestHeader.setConfirmdate("");  		//�ر�ȷ��ʱ��
	            RequestHeader.setRemark(remark);		//��ע
	            RequestHeader.addRequestInBound(lsRequestDetail, RequestHeader, dao);
			}else{
				strBackMsg ="û�����뵥��ϸ û�н�������뵥";
			}
            
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
					
		}catch(Exception er){
		
			Logger.error("�û�["+strUserCode+"]��������==>������ⵥ��==>������ⵥʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
