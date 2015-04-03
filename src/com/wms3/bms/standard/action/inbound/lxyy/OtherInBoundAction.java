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
import com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundRequestInvoiceDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundRequestInvoiceHeader;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * ����:��ⵥ����
 * @author yao
 *
 */
public class OtherInBoundAction extends AbstractAction
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
		String inrequeststockid = CommonTools.getStrToGbk(request.getParameter("inrequeststockid"));		//���뵥��
		String invoicedate = CommonTools.getStrToGbk(request.getParameter("invoicedate"));	//��������
		String instatus = CommonTools.getStrToGb2312(request.getParameter("instatus"));		//����״̬
		String productName = CommonTools.getStrToGbk(request.getParameter("productName"));		//��Ʒ����
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));		//����
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));//��������
		String num = CommonTools.getStrToGbk(request.getParameter("num"));//����
		String requestvoicedetailid = CommonTools.getStrToGbk(request.getParameter("requestvoicedetailid"));//�굥id
		String receiverecordid = CommonTools.getStrToGbk(request.getParameter("receiverecordid"));//�ջ���¼��
		
		String starttime = CommonTools.getStrToGbk(request.getParameter("starttime"));			//��ʼ����
		String endtime = CommonTools.getStrToGbk(request.getParameter("endtime"));			//��������
		
		String isform = CommonTools.getStrToGbk(request.getParameter("isform"));			//�Ƿ񿪵�
		String invoicetype = CommonTools.getStrToGbk(request.getParameter("invoicetype"));		//���ͣ���Ʒ��⣬�����˿⣩
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String errortextString ="";
		try{
			//ÿҳ��ʾ����
            int iline = 6;
            String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));
            if(linerow != null && linerow.length()>0){
                iline = Integer.parseInt(linerow);
            }
			if(flag != null && flag.equals("1")){ //������ �½���ⵥ ��ѯ
			
				errortextString = "������==>�˻�������==>�½���ⵥ==>��ѯ�󶨼�¼ʧ��:";
				strUrl = "/standard/jsp/inbound/othernewin/fyhz/inbound_newin_list.jsp";
				
	            
				BindingRecord record = new BindingRecord();
				/*��ѯ*/
				String strQueryHQL = record.getQueryHQLOther(warehouseid,instockid,isform,invoicetype,lotinfo,starttime,endtime);
                String strCountHQL = record.getQueryHQLCountOther(warehouseid,instockid,isform,invoicetype,lotinfo,starttime,endtime);
                
				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List lsRecord = pt.getLsResult();//��ѯ���
                
				request.setAttribute("exResultList", lsRecord);
				httpsession.setAttribute("paging", pt);
				Logger.info("�û�["+strUserCode+"]��������==>�˻�������==>�½���ⵥ==>��ѯ");
					
			}else if(flag != null && flag.equals("2")){ //��ⵥ���� ��ѯ��ⵥ
			
				errortextString = "������==>�˻�������==>��ⵥ����==>��ѯ��ⵥ��ʧ��:";
				strUrl = "/standard/jsp/inbound/othernewin/fyhz/inbound_scnewrkdmgr_list.jsp";
				InboundHeader inHeader = new InboundHeader();
				/*��ѯ*/
				String strQueryHQL = inHeader.getQueryHQLOther(warehouseid,inrequeststockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                String strCountHQL = inHeader.getQueryHQLCountOther(warehouseid,inrequeststockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//��ѯ���
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				Logger.info("�û�["+strUserCode+"]��������==>�˻�������==>��ⵥ����==>��ѯ");
					
			}else if(flag != null && flag.equals("3")){//��ⵥ���� ��ѯ��ϸ
			

				errortextString = "������==>�˻�������==>��ⵥ����==>��ѯ��ϸʧ��:";
				strUrl = "/standard/jsp/inbound/othernewin/fyhz/inbound_scnewrkdmgrde_list.jsp";
				InboundDetail detail = new InboundDetail(); 
				/*��ѯ*/
				List ls = dao.searchEntities(detail.getQueryHQLByid(instockid));
				request.setAttribute("invoicedetail", ls);
				Logger.info("�û�["+strUserCode+"]��������==>�˻�������==>��ⵥ����==>��ⵥ��ϸ��ѯ");
			}else if(flag != null && flag.equals("4")){//��ⵥ���� ���ϵ���
			

				errortextString = "������==>�˻�������==>��ⵥ����==>���ϵ���ʧ��:";
				strUrl = "/standard/jsp/inbound/othernewin/fyhz/inbound_scnewrkdmgr_list.jsp";
				
				InboundHeader inHeader = new InboundHeader();
				BindingRecord detail = new BindingRecord();
				List lsBindingRecord = detail.getQueryHQLByid(instockid,dao);
				List<BindingRecord> lsListrecord =new ArrayList<BindingRecord>();
				/*��ѯ*/
				if(lsBindingRecord!=null && lsBindingRecord.size()>0){
					for(int i=0;i<lsBindingRecord.size();i++){
						BindingRecord record =new BindingRecord();
						record = (BindingRecord)lsBindingRecord.get(i);
						record.setStatus("1");
						lsListrecord.add(record);
					}
				}
				inHeader = inHeader.getDaoQueryHql(instockid,dao);
				if(inHeader!=null){
					inHeader.setInstatus("4");
					inHeader.updateInboundABR(inHeader,lsBindingRecord,dao);
				}
				String strQueryHQL = inHeader.getQueryHQLOther(warehouseid,inrequeststockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                String strCountHQL = inHeader.getQueryHQLCountOther(warehouseid,inrequeststockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//��ѯ���
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				Logger.info("�û�["+strUserCode+"]��������==>�˻�������==>��ⵥ����==>���ϵ�"+instockid);
			}else if(flag != null && flag.equals("5")){//��ⵥ���� ��ӡ��ⵥ
			

				errortextString = "������==>�˻�������==>��ⵥ����==>��ӡ��ⵥʧ��:";
				strUrl = "/standard/jsp/inbound/othernewin/fyhz/in_rkd_print.jsp";
				InboundHeader invoiceHeader = new InboundHeader();
				InboundDetail invoicedetail = new InboundDetail();
				/*��ѯ*/
				InboundHeader invoice = invoiceHeader.getDaoQueryHql(instockid,dao);
				List lsdetail = dao.searchEntities(invoicedetail.getQueryHQLByid(instockid));
				
				request.setAttribute("instockid", instockid);
				request.setAttribute("invoice", invoice);
				request.setAttribute("invoicedetail", lsdetail);
				Logger.info("�û�["+strUserCode+"]��������==>�˻�������==>��ⵥ����==>��ӡ��ⵥ"+instockid);
			}else if(flag != null && flag.equals("6")){//������뵥���� ��ӡ�ջ���
				

				errortextString = "������==>�˻�������==>��ⵥ����==>��ӡ�ջ�ʧ��:";
				strUrl = "/standard/jsp/inbound/othernewin/fyhz/in_rkdbr_print.jsp";
				InboundHeader invoiceHeader = new InboundHeader();
				InboundDetail invoicedetail = new InboundDetail();
				/*��ѯ*/
				InboundHeader invoice = invoiceHeader.getDaoQueryHql(instockid,dao);
				List lsinvoicedetail = dao.searchEntities(invoicedetail.getQuerySHByid(instockid));
				
				request.setAttribute("instockid", instockid);
				request.setAttribute("invoice", invoice);
				request.setAttribute("lsinvoicedetail", lsinvoicedetail);
				Logger.info("�û�["+strUserCode+"]��������==>�˻�������==>��ⵥ����==>��ӡ��ⵥ"+instockid+"�ջ���¼");
			}else if(flag != null && flag.equals("7")){//��ⵥ���� ��ⵥ�ջ���Ϣ��ʾ
				

				errortextString = "������==>�˻�������==>��ⵥ����==>��ⵥ�ջ���Ϣ��ʾʧ��:";
				strUrl = "/standard/jsp/inbound/othernewin/fyhz/inbound_receive_record_list.jsp";
				InboundHeader invoiceHeader = new InboundHeader();
				InboundDetail invoicedetail = new InboundDetail();
				/*��ѯ*/
				InboundHeader invoice = invoiceHeader.getDaoQueryHql(instockid,dao);
				List lsinvoicedetail = dao.searchEntities(invoicedetail.getQuerySHByid(instockid));
				
				request.setAttribute("invoice", invoice);
				request.setAttribute("lsinvoicedetail", lsinvoicedetail);
				Logger.info("�û�["+strUserCode+"]��������==>�˻�������==>��ⵥ����==>��ⵥ"+instockid+"�ջ���Ϣ��ʾ");
			}else if(flag != null && flag.equals("8")){//��ⵥ���� ��ⵥ�ջ���Ϣ��ʾ ȡ���ջ�
				

				errortextString = "������==>�˻�������==>��ⵥ����==>��ⵥ�ջ���Ϣ��ʾ==>ȡ���ջ�ʧ��:";
				strUrl = "/standard/jsp/inbound/othernewin/fyhz/inbound_receive_record_list.jsp";
				InboundHeader invoiceHeader = new InboundHeader();
				InboundDetail invoicedetail = new InboundDetail();
				InoutJob job = new InoutJob();
				List records = dao.searchEntities(invoicedetail.getQueryHQLByRecordid(receiverecordid));
				List lsinvoicedetailx = dao.searchEntities(invoicedetail.getQuerySHByid(instockid));
				List jobs = dao.searchEntities(job.getQueryHQLByboundid(instockid,receiverecordid));
				InboundHeader invoice = invoiceHeader.getDaoQueryHql(instockid,dao);
				if(records!=null && records.size()==1 && jobs!=null && jobs.size()==1){
					//������ҵ��ϸ
					InboundDetail invoicedetail1 = (InboundDetail)records.get(0);
					invoicedetail1.setStatus("1");
					invoicedetail1.setGetnum(0.0);//ʵ������
					invoicedetail1.setObtainmanid("");//�ջ���
					invoicedetail1.setObtaintime("");//�ջ�ʱ��
					
					//������ҵ
					InoutJob jobx = (InoutJob)jobs.get(0);
					jobx.setStatus("5");
					
					dao.update(invoicedetail1);
					dao.update(jobx);
					if(lsinvoicedetailx!=null && lsinvoicedetailx.size()==0){
						//�޸ĵ���״̬
						invoice.setInstatus("1"); //�޸�Ϊ�½�״̬
						dao.update(invoice);
					}
				}
				/*��ѯ*/
				List lsinvoicedetail = dao.searchEntities(invoicedetail.getQuerySHByid(instockid));
				
				request.setAttribute("invoice", invoice);
				request.setAttribute("lsrecord", lsinvoicedetail);
				Logger.info("�û�["+strUserCode+"]��������==>�˻�������==>������뵥����==>��ⵥ"+instockid+"�ջ���Ϣ��ʾ==>ȡ���ջ�");
			}else if(flag != null && flag.equals("9")){//��ⵥ���� ��ⵥȷ��
				

				errortextString = "������==>�˻�������==>��ⵥ����==>��ⵥȷ��:";
				strUrl = "/standard/jsp/inbound/othernewin/fyhz/inbound_scnewrkdmgr_list.jsp";
				InboundHeader inHeader = new InboundHeader();
				InboundHeader invoice = inHeader.getDaoQueryHql(instockid,dao);
				if(invoice!=null){
					invoice.setInstatus("3");
					dao.update(invoice);
				}
				/*��ѯ*/
				
				String strQueryHQL = inHeader.getQueryHQLOther(warehouseid,inrequeststockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                String strCountHQL = inHeader.getQueryHQLCountOther(warehouseid,inrequeststockid,instatus,productName,lotinfo,starttime,endtime,invoicetype);
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//��ѯ���
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				
				Logger.info("�û�["+strUserCode+"]��������==>�˻�������==>��ⵥ"+instockid+"ȷ��");
			}else if(flag != null && flag.equals("RF1")){//RF�ջ� ����
				errortextString = "RF==>�ջ�==>�ջ�ʧ��:";
				strUrl = "/rf/fyhz/receipt.jsp";
				InboundDetail invoicedetail = new InboundDetail();
				InboundHeader invoice = new InboundHeader().getDaoQueryHql(instockid,dao);
				invoicedetail = invoicedetail.getDetailByidtray(instockid,traycode,dao);
				invoicedetail.setGetnum(Double.parseDouble(num));//ʵ������
				invoicedetail.setStatus("2");
				invoice.setInstatus("2");
				
				//�����ҵ��
	            BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(dao);
	            BaseSeq  seqEn = seqDao.getSeqByType("RKZY");
	            String strNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(),dao); 
				InoutJob job = new InoutJob();
				InoutJobdetail jobdetail = new InoutJobdetail();
				job.setJobid(strNo);
				job.setJobtype(invoice.getInvoicetypeid()); 
				job.setOnLineType("1");//��������
				job.setIsaccount("Y");//����
				job.setStatus("2"); //����״̬
				job.setPriority(1);//���ȼ�
				job.setCreatetime(StrTools.getCurrDateTime(2));
				job.setInOutType("1");//�������
				job.setTraycode(invoicedetail.getTraycode());
				job.setRoute("1");//·�� ֱ��
				job.setOpManId(strUserCode);
				job.setInvoicetype("1");//������ջ��������ɵ���ҵ
				job.setWarehouseid(invoice.getWarehouseid());
				job.setBoundstockid(invoice.getInstockid());
				job.setBoundstockdetailid(invoicedetail.getInstockdetailid());
				job.setBoundrequeststockid(invoice.getInrequestid());
				jobdetail.setJobid(strNo);
				jobdetail.setProductid(invoicedetail.getProductid());
				jobdetail.setLotid(invoicedetail.getLotid());
				jobdetail.setLotinfo(invoicedetail.getLotinfo());
				jobdetail.setPunit(invoicedetail.getPunit());
				jobdetail.setJobnum(invoicedetail.getGetnum());
				jobdetail.setAssignnum(invoicedetail.getGetnum());
				jobdetail.setPrintdate(invoicedetail.getPrintdate());
				jobdetail.setIsplit("1");//����
				jobdetail.setIsinvoice("Y");//�����Ƿ������ɵ��� Y��  N��
				
				invoice.addInBoundJob(invoice,invoicedetail ,job,jobdetail,dao);
				request.setAttribute("instockid", instockid);
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
			Logger.error(errortextString + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

	
	/**
	 * ����:������ⵥ
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
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�ID
		String instockid = CommonTools.getStrToGbk(request.getParameter("instockid"));		//���ݺ�
		String invoicedate = CommonTools.getStrToGbk(request.getParameter("invoicedate"));	//��������
		String instatus = CommonTools.getStrToGb2312(request.getParameter("instatus"));		//����״̬
		String productName = CommonTools.getStrToGbk(request.getParameter("productName"));		//��Ʒ����
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));		//����
		String starttime = CommonTools.getStrToGbk(request.getParameter("starttime"));			//��ʼ����
		String endtime = CommonTools.getStrToGbk(request.getParameter("endtime"));			//��������
		String isform = CommonTools.getStrToGbk(request.getParameter("isform"));			//�Ƿ񿪵�
		String invoicetype = CommonTools.getStrToGbk(request.getParameter("invoicetype"));		//���ͣ���Ʒ��⣬�����˿⣩
		String condition = CommonTools.getStrToGbk(request.getParameter("condition"));		   //�󶨼�¼idϵ��
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		InboundHeader Header=null;
		InboundDetail  Detail=null;
		
		try{
			String strBackMsg = "Y";
			strUrl = "/standard/jsp/inbound/othernew/inbound_scnewsqd_list.jsp";
			int rowLength = StrTools.StrTimes(condition,",");
			 //������뵥��
            BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(dao);
            BaseSeq  seqEn = seqDao.getSeqByType("RKD");
            String strNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(),dao); 
            String inrequestid = "";
            int x=0,y=0;
            List<InboundDetail> lsInboundDetail = new ArrayList<InboundDetail>();
			List<BindingRecord> lsBindingRecord = new ArrayList<BindingRecord>();
			for(int i=0; i< rowLength; i++)
			{
				
				//�󶨼�¼id
				String bandrecordid = StrTools.GetStrFlag(condition,",",i);
				BindingRecord record = new BindingRecord();
				/*��ѯ*/
				List lsRecord = dao.searchEntities(record.getQueryHQLByRecordid(bandrecordid));
				if(lsRecord!=null&&lsRecord.size()==1){
					record = (BindingRecord)lsRecord.get(0);
					if(record!=null && record.getStatus().equals("1")){ //ֻ��δ�����İ󶨼�¼�ſ�Ӧ������ⵥ
						record.setStatus("2");
						inrequestid = record.getInstockid();
						Detail = new InboundDetail();
						Detail.setInstockid(strNo);
						Detail.setBandrecordid(record.getBandrecordid());
						Detail.setInnum(record.getNum());
						Detail.setTraycode(record.getTraycode());
						Detail.setLotid(record.getLotid());
						Detail.setLotinfo(record.getLotinfo());
						Detail.setProductStatus(record.getProductStatus());
						Detail.setStatus("1");
						Detail.setPunit(record.getPunit());
						Detail.setPrintdate(record.getPrintdate());
						Detail.setProductid(record.getProductid());
						lsInboundDetail.add(Detail);
						lsBindingRecord.add(record);
						y++;
					}else{
						x++;
					}
				}
			}
			if(x!=0){
				strBackMsg = "��"+x+"���󶨼�¼û��������Ӧ����ⵥ";
			}
			if(y!=0 && lsInboundDetail!=null && lsInboundDetail.size()>0  && lsBindingRecord!=null && lsBindingRecord.size()>0 ){ //�����ѡ�İ󶨼�¼��δ���ɵ��� ����Ҫ������� ���򲻽�����ⵥ
				//��ⵥ
				Header = new InboundHeader();
				Header.setInstockid(strNo);  		    //��ⵥid
				Header.setInrequestid(inrequestid);       //���뵥��
				Header.setInvoicetypeid(invoicetype);	//���뵥����id(��Ʒ��⣬�����˿�)
				Header.setInvoicedate(StrTools.getCurrDateTime(8));  	//����ʱ��
				Header.setCreatetime(StrTools.getCurrDateTime(2));	//��������ʱ��
				Header.setInstatus("1");  		//��ⵥ��״̬id 1-�½���2-ȷ���� 3-���� 
				Header.setCreatemanid(strUserCode);		//����������Ա���
				Header.setWarehouseid(warehouseid);  	//�ջ��ֿ�
	            Header.addInBound(lsInboundDetail, lsBindingRecord, Header, dao);
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
