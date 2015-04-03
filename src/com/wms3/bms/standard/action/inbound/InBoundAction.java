package com.wms3.bms.standard.action.inbound;

import java.net.URLDecoder;
import java.util.HashMap;
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
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.inbound.IInBoundBus;
import com.wms3.bms.standard.business.inbound.impl.InBoundBusImpl;
import com.wms3.bms.standard.business.inbound.impl.InboundPoBusImpl;
import com.wms3.bms.standard.entity.base.BaseLotSet;
import com.wms3.bms.standard.entity.inbound.InboundInvoiceDetail;
import com.wms3.bms.standard.entity.inbound.InboundInvoiceHeader;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader;

/**
 * ����:��ⵥ����
 * @author gui
 *
 */
public class InBoundAction extends AbstractAction
{
	protected IInBoundBus inBoundBus;
	protected List<BaseLotSet> lsLot;
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//����
		String indate = CommonTools.getStrToGbk(request.getParameter("indate"));			//����
		String shiftid = CommonTools.getStrToGb2312(request.getParameter("shift_id"));		//��� 
		String owner_id = CommonTools.getStrToGbk(request.getParameter("owner_id"));		//����
		String isinvoice = CommonTools.getStrToGbk(request.getParameter("isinvoice"));		//�Ƿ񿪵�
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		inBoundBus = new InBoundBusImpl(dao);
		try{
		
			lsLot = getLotset(hsSysParam);
			
			if(flag != null && flag.equals("1")){ //�½���ⵥ ��ѯ�б�
			
				strUrl = "/standard/jsp/inbound/newin/inbound_newin_list.jsp";
				
				List ls = inBoundBus.getInboundJobs(warehouseid, whAreaId, indate, shiftid, owner_id, isinvoice, lsLot);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2")){//�½���ⵥ ��ѯ�б� ��ѯ��ϸ
			

				String strKey = URLDecoder.decode(CommonTools.getStrToGb2312(request.getParameter("key")), "UTF-8");	//��ϸ(�����ĺ������ַ�)
				strUrl = "/standard/jsp/inbound/newin/inbound_newin_list_detail.jsp";
				
				List ls = inBoundBus.getInboundJobDetails(warehouseid, whAreaId, indate, shiftid, isinvoice, strKey, lsLot);
				request.setAttribute("exResultList", ls);
				request.setAttribute("lsLot", lsLot);
			}	
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("������==>�½���ⵥ==>��ҵ��ѯʧ��:" + er.getMessage());
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
		
		String searchCondition = CommonTools.getStrToGbk(request.getParameter("searchCondition"));	//��ѯ����
		String strKey = CommonTools.getStrToGb2312(request.getParameter("key"));	//��ϸ
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		
		inBoundBus = new InBoundBusImpl(dao);
		
		try{
			String strBackMsg = "Y";
			lsLot = getLotset(hsSysParam);
			
			strUrl = "/standard/jsp/inbound/newin/inbound_newin_list.jsp";
			String shiftid = "";		//��� (Ԥ��)
			
			String[] bem = searchCondition.split("\\|");
			String warehouseid = bem[0];	//�ֿ�
			String whAreaId = bem[1];		//����
			String indate = bem[2];			//����
			String owner_id = bem[3];		//����
			String isinvoice = bem[4];		//�Ƿ񿪵�
			
			strBackMsg = inBoundBus.addInboundInvoice(warehouseid, whAreaId, indate, shiftid, strKey, lsLot, strUserCode);
			
			List ls = inBoundBus.getInboundJobs(warehouseid, whAreaId, indate, shiftid, owner_id, isinvoice, lsLot);
			request.setAttribute("exResultList", ls);
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
					
		}catch(Exception er){
		
			Logger.error("�û�["+strUserCode+"]��������==>������ⵥ��==>������ⵥʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:��ⵥ����->��ѯ����
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecSearchRkd(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//����
		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from"));	//���ڿ�ʼ
		String indate_to = CommonTools.getStrToGb2312(request.getParameter("indate_to"));	//���ڽ���
		String shiftid = CommonTools.getStrToGb2312(request.getParameter("shift_id"));		//��� 
		String owner_id = CommonTools.getStrToGbk(request.getParameter("owner_id"));		//����
		String instatus = CommonTools.getStrToGbk(request.getParameter("instatus"));		//����״̬
		String intype = CommonTools.getStrToGbk(request.getParameter("intype"));			//�������
		String instockid = CommonTools.getStrToGbk(request.getParameter("instockid"));		//��ⵥ�ݱ��
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
		int maxLine = 6;
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		
		inBoundBus = new InBoundBusImpl(dao);
		try{
		
			if(flag != null && flag.equals("1")){ 		//��ⵥ���� ��ѯ��ⵥ�б�
			
				strUrl = "/standard/jsp/inbound/rkdmgr/inbound_rkmgr_list.jsp";
				
				PagingTool pt = inBoundBus.getInbounds(warehouseid, whAreaId, indate_from, indate_to, shiftid, owner_id, instatus, intype, strUrl, maxLine);
				List ls = pt.getLsResult();
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
					
			}else if(flag != null && flag.equals("2")){	//��ⵥ���� ��ѯ��ⵥ��ϸ�б�
			

				strUrl = "/standard/jsp/inbound/rkdmgr/inbound_rkmgr_detail.jsp";
				
				List ls = inBoundBus.getInboundDetails(instockid);
				request.setAttribute("exResultList", ls);
			}	
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("������==>��ⵥ����==>��ⵥ��ѯʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
/*	*//**
	 * ����:��ⵥ����->�������
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 *//*
	public String ricoExecAudit(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//����
		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from"));	//���ڿ�ʼ
		String indate_to = CommonTools.getStrToGb2312(request.getParameter("indate_to"));	//���ڽ���
		String shiftid = CommonTools.getStrToGb2312(request.getParameter("shift_id"));		//��� 
		String owner_id = CommonTools.getStrToGbk(request.getParameter("owner_id"));		//����
		String instatus = CommonTools.getStrToGbk(request.getParameter("instatus"));		//����״̬
		String intype = CommonTools.getStrToGbk(request.getParameter("intype"));			//�������
		String instockids = CommonTools.getStrToGbk(request.getParameter("instockids"));	//��ⵥ�ݱ��
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
		int maxLine = 6;
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strCurrDate = StrTools.getCurrDateTime(8);
		
		inBoundBus = new InBoundBusImpl(dao);
		
		try{
			String strBackMsg = "Y";
			
			String ids[] = instockids.split(",");
			InboundHeader inbound = null;
			for(int i=0 ; i < ids.length; i++){
			
				inbound = inBoundBus.getInboundInvoice(ids[i]);
				if(inbound!=null && inbound.getInstatus().equals("0")){	//����״̬ 0-�½���1-��ˣ�2-ȷ�� 5-���� 
				
					boolean bFlag = true; //��֤�ɹ���־
					List lsDetail = inBoundBus.getInboundDetails(ids[i]);	//��ⵥ��ϸ
					if(lsDetail != null){
					
						InboundInvoiceDetail inDetail = null;
						for(int j = 0; j < lsDetail.size(); j++){
						
							inDetail = (InboundInvoiceDetail)lsDetail.get(j);
							
							//��֤������ϸ����������ҵ�����Ƿ����
							int iNum = inBoundBus.getJobNumSum(inDetail.getInstockdetailid());
							if(iNum != (int)inDetail.getInvoicenum()){
								
								bFlag = false;
								break;
							}		
						}
					}
					if(bFlag){
						
						inbound.setInstatus("1");				//����״̬ 0-�½���1-��ˣ�2-ȷ�� 5-���� 
						inbound.setAuditmanid(strUserCode);		//�����Id
						inbound.setAuditdate(strCurrDate);		//�������
						inBoundBus.updateInboundInvoice(inbound);
						Logger.error("�û�["+strUserCode+"]��������ⵥ��˲���������id��" + ids[i]);
						
					}else{
						
						if(strBackMsg.equals("Y")){
							strBackMsg = "���ݡ�" + ids[i] + "����֤���󣬵��ݲ�Ʒ��������ϸ�������ȣ��޷���ˣ�";
						}else{
							strBackMsg += "\\r���ݡ�" + ids[i] + "����֤���󣬵��ݲ�Ʒ��������ϸ�������ȣ��޷���ˣ�";
						}
					}
				}else{
				
					if(strBackMsg.equals("Y")){
						strBackMsg = "���ݡ�" + ids[i] + "����״̬Ϊ�ǿ���״̬���޷���ˣ�";
					}else{
						strBackMsg += "\\r���ݡ�" + ids[i] + "����״̬Ϊ�ǿ���״̬���޷���ˣ�";
					}
				}
			}
			
			strUrl = "/standard/jsp/inbound/rkdmgr/inbound_rkmgr_list.jsp";
			PagingTool pt = inBoundBus.getInbounds(warehouseid, whAreaId, indate_from, indate_to, shiftid, owner_id, instatus, intype, strUrl, maxLine);
			List ls = pt.getLsResult();
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
					
		}catch(Exception er){
		
			Logger.error("�û�["+strUserCode+"]��������==>�������ʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}*/
	
	/**
	 * ����:��ⵥ����->���ݵ���ȷ��
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecConfirm(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//����
		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from"));	//���ڿ�ʼ
		String indate_to = CommonTools.getStrToGb2312(request.getParameter("indate_to"));	//���ڽ���
		String shiftid = CommonTools.getStrToGb2312(request.getParameter("shift_id"));		//��� 
		String owner_id = CommonTools.getStrToGbk(request.getParameter("owner_id"));		//����
		String instatus = CommonTools.getStrToGbk(request.getParameter("instatus"));		//����״̬
		String intype = CommonTools.getStrToGbk(request.getParameter("intype"));			//�������
		String instockids = CommonTools.getStrToGbk(request.getParameter("instockids"));	//��ⵥ�ݱ��
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
		int maxLine = 6;
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strCurrDate = StrTools.getCurrDateTime(8);
		
		inBoundBus = new InBoundBusImpl(dao);
		
		try{
			String strBackMsg = "Y";
			
			String ids[] = instockids.split(",");
			InboundHeader inbound = null;
			for(int i=0 ; i < ids.length; i++){
			
				inbound = inBoundBus.getInboundInvoice(ids[i]);
				if(inbound!=null && inbound.getInstatus().equals("1")){	//����״̬ 1-�½���3-ȷ��  5-���� 
				
					boolean bFlag = true; //��֤�ɹ���־
					
					List lsDetail = inBoundBus.getInboundDetails(ids[i]);	//��ⵥ��ϸ
					if(lsDetail != null){
					
						InboundDetail inDetail = null;
						for(int j = 0; j < lsDetail.size(); j++){
						
							inDetail = (InboundDetail)lsDetail.get(j);
							
							//��֤������ϸ����������ҵ�����Ƿ����
							double iNum = inBoundBus.getJobNumSum(inDetail.getInstockdetailid());
							if(iNum != inDetail.getInnum()){
								bFlag = false;
								break;
							}		
						}
					}
					if(bFlag){
						inbound.setInstatus("3");				//����״̬ 1-�½���3-ȷ�� 5-���� 
						inbound.setCreatemanid(strUserCode);	//ȷ����Id
						inbound.setCreatetime(strCurrDate);		//ȷ������
						inBoundBus.updateInboundInvoice(inbound);
						Logger.error("�û�["+strUserCode+"]��������ⵥȷ�ϲ���������id��" + ids[i]);
						
					}else{
						
						if(strBackMsg.equals("Y")){
							strBackMsg = "���ݡ�" + ids[i] + "����֤���󣬵��ݲ�Ʒ��������ϸ�������ȣ��޷�ȷ�ϣ�";
						}else{
							strBackMsg += "\\r���ݡ�" + ids[i] + "����֤���󣬵��ݲ�Ʒ��������ϸ�������ȣ��޷�ȷ�ϣ�";
						}
					}
				}else{
				
					if(strBackMsg.equals("Y")){
						strBackMsg = "���ݡ�" + ids[i] + "����״̬Ϊ�ǿ���״̬���޷�ȷ�ϣ�";
					}else{
						strBackMsg += "\\r���ݡ�" + ids[i] + "����״̬Ϊ�ǿ���״̬���޷�ȷ�ϣ�";
					}
				}
			}
			
			strUrl = "/standard/jsp/inbound/rkdmgr/inbound_rkmgr_list.jsp";
			PagingTool pt = inBoundBus.getInbounds(warehouseid, whAreaId, indate_from, indate_to, shiftid, owner_id, instatus, intype, strUrl, maxLine);
			List ls = pt.getLsResult();
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
					
		}catch(Exception er){
		
			Logger.error("�û�["+strUserCode+"]��������==>����ȷ��ʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:��ⵥ����->��������
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecCancel(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//����
		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from"));	//���ڿ�ʼ
		String indate_to = CommonTools.getStrToGb2312(request.getParameter("indate_to"));	//���ڽ���
		String shiftid = CommonTools.getStrToGb2312(request.getParameter("shift_id"));		//��� 
		String owner_id = CommonTools.getStrToGbk(request.getParameter("owner_id"));		//����
		String instatus = CommonTools.getStrToGbk(request.getParameter("instatus"));		//����״̬
		String intype = CommonTools.getStrToGbk(request.getParameter("intype"));			//�������
		String instockids = CommonTools.getStrToGbk(request.getParameter("instockids"));	//��ⵥ�ݱ��
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
		int maxLine = 6;
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		String strUserCode = (String)httpsession.getAttribute("userCode");
		
		inBoundBus = new InBoundBusImpl(dao);
		
		try{
			String strBackMsg = "Y";
			
			String ids[] = instockids.split(",");
			InboundHeader inbound = null;
			for(int i=0 ; i < ids.length; i++){
				
				inbound = inBoundBus.getInboundInvoice(ids[i]);
				if(inbound!=null && inbound.getInstatus().equals("1")){	//����״̬ 1-�½���2-ȷ�� 5-���� 
				
					//**���ݵ�����Դ����********************************************
					//1��ֱ�Ӳɼ����ɵ���ҵ��2�����ջ��������ɵ���ҵ��3������ⵥ�����ɵ���ҵ 4:��ERP�����������ɵ���ҵ
					//if(inbound.getInvoicesource().equals("1") || inbound.getInvoicesource().equals("2") ){ 	
					
						inBoundBus.cancelInboundInvoice(ids[i]);
						Logger.error("�û�["+strUserCode+"]��������ⵥ���ϲ���������id��" + ids[i]);
					//}
					
				}else{
				
					if(strBackMsg.equals("Y")){
						strBackMsg = "���ݡ�" + ids[i] + "����״̬Ϊ�ǿ���״̬���޷����ϣ�";
					}else{
						strBackMsg += "\\r���ݡ�" + ids[i] + "����״̬Ϊ�ǿ���״̬���޷����ϣ�";
					}
				}
			}
			
			strUrl = "/standard/jsp/inbound/rkdmgr/inbound_rkmgr_list.jsp";
			PagingTool pt = inBoundBus.getInbounds(warehouseid, whAreaId, indate_from, indate_to, shiftid, owner_id, instatus, intype, strUrl, maxLine);
			List ls = pt.getLsResult();
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
					
		}catch(Exception er){
		
			Logger.error("�û�["+strUserCode+"]��������==>��������ʧ��:" + er.getMessage());
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
		
		String instockdetailid = CommonTools.getStrToGbk(request.getParameter("instockdetailid"));	//������ϸ���
		inBoundBus = new InBoundBusImpl(dao);
		try{

			List<Object[]> ls=inBoundBus.getJobInfoByPodetailid(instockdetailid);
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
	
/*	*//**
	 * ����:��ⵥ����->��ϸ��������
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 *//*
	public String ricoExecCancelDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String instockdetailids = CommonTools.getStrToGbk(request.getParameter("instockdetailids"));	//��ⵥ�ݱ��
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		
		inBoundBus = new InBoundBusImpl(dao);
		
		try{
			String strBackMsg = "Y";
			
			String ids[] = instockdetailids.split(",");
			String instockid = ids[0].substring(0, ids[0].length()-2);
			InboundHeader inbound = inBoundBus.getInboundInvoice(instockid);
				
			if(inbound!=null && inbound.getInstatus().equals("0")){	//����״̬ 0-�½���1-��ˣ�2-ȷ�� 5-���� 
			
				//**���ݵ�����Դ����********************************************
				//1��ֱ�Ӳɼ����ɵ���ҵ��2�����ջ��������ɵ���ҵ��3������ⵥ�����ɵ���ҵ 4:��ERP�����������ɵ���ҵ
				if(inbound.getInvoicesource().equals("1") || inbound.getInvoicesource().equals("2") ){

					for(int i=0 ; i < ids.length; i++){
						inBoundBus.cancelInboundDetail(ids[i]);
						Logger.error("�û�["+strUserCode+"]��������ⵥ��ϸ���ϲ���������id��" + instockid + "��������ϸid��" + ids[i]);
					}
				}
				
			}else{
			
				if(strBackMsg.equals("Y")){
					strBackMsg = "���ݡ�" + instockid + "����״̬Ϊ�ǿ���״̬���޷����ϣ�";
				}else{
					strBackMsg += "\\r���ݡ�" + instockid + "����״̬Ϊ�ǿ���״̬���޷����ϣ�";
				}
			}
			
			strUrl = "/standard/jsp/inbound/rkdmgr/inbound_rkmgr_detail.jsp";
			List ls = inBoundBus.getInboundDetails(instockid);
			request.setAttribute("exResultList", ls);
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
					
		}catch(Exception er){
		
			Logger.error("�û�["+strUserCode+"]��������==>��ϸ��������ʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	*/
	/**
	 * ����:��ⵥ����->���ݴ�ӡ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecReportRKD(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String instockid = CommonTools.getStrToGbk(request.getParameter("instockid"));		//��ⵥ�ݱ��
		
		inBoundBus = new InBoundBusImpl(dao);
		try{
			
			lsLot = getLotset(hsSysParam);	//�������Լ�
			
			InboundHeader inbound = inBoundBus.getInboundInvoice(instockid);	//��ⵥ
			List lsDetail = inBoundBus.getInboundDetails(instockid);	//��ⵥ��ϸ
            
            strUrl = "/standard/jsp/inbound/rkdmgr/inbound_rkmgr_print.jsp";
            request.setAttribute("lsLot", lsLot);
            request.setAttribute("invoice", inbound);
            request.setAttribute("invoicedetail", lsDetail); 
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("������==>��ⵥ����==>��ⵥ��ӡʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�������������б�
	 * @param hsSysParam
	 * @return
	 * @throws Exception
	 */
	private List getLotset(Hashtable hsSysParam) {
		
		HashMap<String, List> hsLot = (HashMap<String, List>)hsSysParam.get("viewLot");	//����Ҫ��ʾ������
		List list = null;  	//��ʾ�����������б�
		if(hsLot != null){
			list = hsLot.get("newin");		//�½���ⵥ->��ⵥͳ�Ƶ���Ŀ
		}
		return list;
	}
	
	
}
