package com.wms3.bms.standard.action.inbound;

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

import com.wms3.bms.standard.business.inbound.IInBoundJobBus;
import com.wms3.bms.standard.business.inbound.impl.InBoundJobBusImpl;
import com.wms3.bms.standard.entity.base.BaseLotSet;
//import com.wms3.bms.standard.entity.job.InoutJob;
//import com.wms3.bms.standard.entity.job.InoutJobdetail;

/**
 * ����:���ά��
 * @author gui
 *
 */
public class InBoundRKWHAction extends AbstractAction
{
	protected IInBoundJobBus inBoundJobBus;
	protected List<BaseLotSet> lsLot;
	protected int maxLine = 6;
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//����
		String alleyId = CommonTools.getStrToGbk(request.getParameter("alleyId"));			//���
		String indate = CommonTools.getStrToGbk(request.getParameter("indate"));			//��ҵ����
		String jobid = CommonTools.getStrToGbk(request.getParameter("jobid"));				//��ҵ��
		String invoicetype = CommonTools.getStrToGbk(request.getParameter("invoicetype"));	//��ҵ��Դ
		String shiftid = CommonTools.getStrToGbk(request.getParameter("shift_id"));			//��� 
		String package_id = CommonTools.getStrToGbk(request.getParameter("package_id"));	//Ʒ��
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));		//��������
		String isback = CommonTools.getStrToGbk(request.getParameter("isback"));			//�Ƿ����
		String jobdetailid = CommonTools.getStrToGbk(request.getParameter("jobdetailid"));	//��ҵ��ϸ��
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));				//��ʶ
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		
        inBoundJobBus = new InBoundJobBusImpl(dao);
		try{
		
			if(flag != null && flag.equals("1")){ 		//���ά������ ��ѯ��ҵ�б�
			
				strUrl = "/standard/jsp/inbound/rkwh/inbound_rkwh_list.jsp";
				
				String sortflg = "asc";
				PagingTool pt = inBoundJobBus.getInboundJobs(warehouseid, whAreaId, alleyId, indate, jobid, invoicetype, "", shiftid, 
						package_id, "", traycode, isback, sortflg, strUrl, maxLine);
				
				List ls = pt.getLsResult();
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
					
			}else if(flag != null && flag.equals("2")){	//���ά������ ��ѯ��ҵ��ϸ�б�

				strUrl = "/standard/jsp/inbound/rkwh/inbound_rkwh_detail.jsp";
				
				List ls = inBoundJobBus.getJobDetails(jobid);
				request.setAttribute("exResultList", ls);
				
			}else if(flag != null && flag.equals("3")){	//���ά������ �޸���ҵʱ���ѯ��ҵ
			
//				InoutJob job = inBoundJobBus.getJobByJobid(jobid);
				strUrl = "/standard/jsp/inbound/rkwh/inbound_rkwh_update.jsp";
				
				//request.setAttribute("job", job);
				
			}else if(flag != null && flag.equals("4")){	//���ά������ �޸���ҵ��ϸʱ���ѯ��ҵ��ϸ
			
				//InoutJobdetail jobdetail = inBoundJobBus.getJobDetailBydetailid(jobdetailid);
				strUrl = "/standard/jsp/inbound/rkwh/inbound_rkwh_updatedetail.jsp";
				
				//request.setAttribute("jobdetail", jobdetail);
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("������==>���ά��==>�����ҵ��ѯʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�����޵��������ҵ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdd(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//����
		String alleyId = CommonTools.getStrToGbk(request.getParameter("alleyId"));			//���
		String cargospaceid = CommonTools.getStrToGbk(request.getParameter("cargospaceid"));//��λID
		String intype = CommonTools.getStrToGbk(request.getParameter("intype"));			//�������
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));		//��������
		String indate = CommonTools.getStrToGbk(request.getParameter("indate"));			//��ҵ����
		String shiftid = CommonTools.getStrToGbk(request.getParameter("shift_id"));			//��� 
		String jobdetails = CommonTools.getStrToGbk(request.getParameter("jobdetails"));	//��ҵ��ϸ
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		
		inBoundJobBus = new InBoundJobBusImpl(dao);
		try{
			
			strUrl = "/standard/jsp/inbound/rkwh/inbound_rkwh_list.jsp";
			
			String strBackMsg = inBoundJobBus.addRKWHJob(warehouseid, whAreaId, alleyId, cargospaceid, intype, traycode, indate, 
					shiftid, jobdetails, strUserCode);
			
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
					
		}catch(Exception er){
		
			Logger.error("�û�["+strUserCode+"]��������==>���ά��==>�����ҵʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�޸������ҵ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecUpdate(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String jobid = CommonTools.getStrToGbk(request.getParameter("jobid"));		//��ҵID
		String jobtype = CommonTools.getStrToGbk(request.getParameter("jobtype"));	//��ҵ��
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));//��������
		String strUserCode = (String)httpsession.getAttribute("userCode");
		
		inBoundJobBus = new InBoundJobBusImpl(dao);	
		try{
		
			String strBackMsg = inBoundJobBus.updateRKWHJob(jobid, jobtype, traycode);
			if(strBackMsg.equals("Y")){
				Logger.info("�û���" + strUserCode + "�����޸������ҵ��" + jobid);
			}
			
			strUrl = "/standard/jsp/inbound/rkwh/inbound_rkwh_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
			
			Logger.error("�û�["+strUserCode+"]��������==>���ά��==>�޸���ҵ["+jobid+"]ʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}
	
	/**
	 * ����:�޸������ҵ��ϸkey
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecUpdateDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String jobdetailid = CommonTools.getStrToGbk(request.getParameter("jobdetailid"));	//��ҵ��ϸ��
		String key = CommonTools.getStrToGbk(request.getParameter("key"));					//��ҵ��ϸ����
		String strUserCode = (String)httpsession.getAttribute("userCode");
		
		inBoundJobBus = new InBoundJobBusImpl(dao);	
		try{
		
			String strBackMsg = inBoundJobBus.updateRKWHJobDetail(jobdetailid, key);
			if(strBackMsg.equals("Y")){
				Logger.info("�û���" + strUserCode + "�����޸������ϸ��ҵ��" + jobdetailid);
			}
			
			strUrl = "/standard/jsp/inbound/rkwh/inbound_rkwh_detail.jsp";
			
			//InoutJobdetail jobdetail = inBoundJobBus.getJobDetailBydetailid(jobdetailid);
			//List ls = inBoundJobBus.getJobDetails(jobdetail.getJobid());
			
		//	request.setAttribute("exResultList", ls);
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
			
			Logger.error("�û�["+strUserCode+"]��������==>���ά��==>�޸���ҵ��ϸ["+jobdetailid+"]ʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}
	
	/**
	 * ����:���������ҵ
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
		
		String jobid = CommonTools.getStrToGbk(request.getParameter("jobid"));		//��ҵID
		String strUserCode = (String)httpsession.getAttribute("userCode");
		
		inBoundJobBus = new InBoundJobBusImpl(dao);	
		try{
		
			String strBackMsg = inBoundJobBus.cancelRKWHJob(jobid);
			if(strBackMsg.equals("Y")){
				Logger.info("�û���" + strUserCode + "�������������ҵ(���ά��)��" + jobid);
			}
			
			strUrl = "/standard/jsp/inbound/rkwh/inbound_rkwh_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
			
			Logger.error("�û�["+strUserCode+"]��������==>���ά��==>������ҵ["+jobid+"]ʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}
	
}
