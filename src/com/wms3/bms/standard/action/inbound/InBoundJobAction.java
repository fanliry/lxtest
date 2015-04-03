package com.wms3.bms.standard.action.inbound;

import java.util.ArrayList;
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
import com.wms3.bms.standard.business.allot.IAllotBus;
import com.wms3.bms.standard.business.allot.impl.AllotBusImpl;
import com.wms3.bms.standard.business.inbound.IInBoundJobBus;
import com.wms3.bms.standard.business.inbound.impl.InBoundJobBusImpl;
import com.wms3.bms.standard.dao.base.IBaseCargoSpaceDao;
import com.wms3.bms.standard.dao.base.impl.BaseCargoSpaceDaoImpl;
import com.wms3.bms.standard.dao.base.impl.BaseSeqDaoImpl;
import com.wms3.bms.standard.dao.inbound.IInboundPoDao;
import com.wms3.bms.standard.dao.inbound.impl.InboundPoDaoImpl;
import com.wms3.bms.standard.dao.job.IJobDao;
import com.wms3.bms.standard.dao.job.impl.JobDaoImpl;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BaseLotSet;
import com.wms3.bms.standard.entity.base.BaseSeq;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundPoDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundPoHeader;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

/**
 * ����:�����ҵ
 * 
 * @author yao
 * 
 */
public class InBoundJobAction extends AbstractAction {
	protected IInBoundJobBus inBoundJobBus;
	protected List<BaseLotSet> lsLot;
	protected int maxLine = 6;

	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {

		HttpServletRequest request = (HttpServletRequest) hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse) hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO) hsSysParam.get("dao");

		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // �ֿ�
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId")); // ����
		String Virtualwhid = CommonTools.getStrToGbk(request.getParameter("Virtualwhid")); // �߼�����id
		String jobid = CommonTools.getStrToGbk(request.getParameter("jobid")); // ��ҵ��
		String indate = CommonTools.getStrToGbk(request.getParameter("indate")); // ��ҵ����
		String invoicetype = CommonTools.getStrToGbk(request.getParameter("invoicetype")); // ��ҵ��Դ
		String instockid = CommonTools.getStrToGbk(request.getParameter("instockid")); // ��ⵥ��id
		String status = CommonTools.getStrToGbk(request.getParameter("status")); // ��ҵ״̬
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode")); // ��������
		String isback = CommonTools.getStrToGbk(request.getParameter("isback")); // �Ƿ����
		String productid = CommonTools.getStrToGbk(request.getParameter("productid")); // Ʒ��
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo")); // ������Ϣ
		String taskid = CommonTools.getStrToGbk(request.getParameter("taskid")); // ��������ID
		String flag = CommonTools.getStrToGbk(request.getParameter("flag")); // ��ʶ
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow")); // ÿҳ��ʾ����

		String strUserCode = (String) httpsession.getAttribute("userCode");
		if (linerow != null && linerow.length() > 0) {
			maxLine = Integer.parseInt(linerow);
		}

		String errinfo = "";
		try {
			// ÿҳ��ʾ����
			int iline = 6;
			if (linerow != null && linerow.length() > 0) {
				iline = Integer.parseInt(linerow);
			}
			inBoundJobBus = new InBoundJobBusImpl(dao);
			if (flag != null && flag.equals("1")) { // �����ҵ���� ��ѯ��ҵ�б�

				strUrl = "/standard/jsp/inbound/job/inbound_job_list.jsp";

				InoutJob job = new InoutJob();
				String inOutType = "1"; // �����ҵ
				/* ��ѯ */
				String strQueryHQL = job.getQueryHQL(warehouseid,Virtualwhid, whAreaId, indate, jobid, instockid, status, traycode, isback, productid, lotinfo, taskid, inOutType);
				String strCountHQL = job.getQueryHQLCount(warehouseid,Virtualwhid, whAreaId, indate, jobid, instockid, status, traycode, isback, productid, lotinfo, taskid, inOutType);

				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
				List ls = pt.getLsResult();// ��ѯ���
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				errinfo = "������==>��ҵ����==>�����ҵ��ѯʧ��:";

			} else if (flag != null && flag.equals("2")) { // �����ҵ���� ��ѯ��ҵ��ϸ�б�

				strUrl = "/standard/jsp/inbound/job/inbound_job_detail.jsp";

				List ls = inBoundJobBus.getJobDetails(jobid);
				request.setAttribute("exResultList", ls);
				errinfo = "������==>��ҵ����==>��ѯ��ҵ��ϸ�б�ʧ��:";

			} else if (flag != null && flag.equals("3")) { // δִ����ҵ�ƿ⵽�ݴ����
				strUrl = "/standard/jsp/inbound/job/inbound_job_list.jsp";
				InoutJob job = new InoutJob();
				String back_msg = job.finishJobsToTem(jobid, strUserCode, dao);

				String inOutType = "1"; // �����ҵ
				/* ��ѯ */
				String strQueryHQL = job.getQueryHQL(warehouseid,Virtualwhid, whAreaId, indate, jobid, instockid, status, traycode, isback, productid, lotinfo, taskid, inOutType);
				String strCountHQL = job.getQueryHQLCount(warehouseid,Virtualwhid, whAreaId, indate, jobid, instockid, status, traycode, isback, productid, lotinfo, taskid, inOutType);

				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
				List ls = pt.getLsResult();// ��ѯ���

				request.setAttribute("back_msg", back_msg);
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				errinfo = "������==>��ҵ����==>��ִ����ҵ�ƿ⵽�ݴ����ʧ��:";
			}else if (flag != null && flag.equals("4")) { // �����ҵ���� ��ѯ��ҵ�б�
				String intype = CommonTools.getStrToGbk(request.getParameter("intype")); // ��ҵ����
				strUrl = "/standard/jsp/inbound/newrkd/inbound_search_part_list.jsp";

				InoutJob job = new InoutJob();
				String inOutType = "1"; // �����ҵ
				/* ��ѯ */
				String strQueryHQL = job.getQueryJobADetailHQLPart(warehouseid, indate, indate, productid, intype);
				String strCountHQL = job.getQueryJobADetailHQLCountPart(warehouseid, indate, indate, productid, intype);

				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, maxLine);

				List ls = pt.getLsResult();
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				errinfo = "������==>��ҵ����==>�����ҵ��ѯʧ��:";

			} 
			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {

			Logger.error(errinfo + er.getMessage());
			request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

	/**
	 * ����:����ѯ
	 * 
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecSearchJobCollection(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {

		HttpServletRequest request = (HttpServletRequest) hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse) hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO) hsSysParam.get("dao");

		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // �ֿ�
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId")); // ����
		String Virtualwhid = CommonTools.getStrToGbk(request.getParameter("Virtualwhid")); // �߼�����
		String alleyId = CommonTools.getStrToGbk(request.getParameter("alleyId")); // ���
		String cargospaceid = CommonTools.getStrToGbk(request.getParameter("cargospaceid"));// ��λ

//		String onlinetype = CommonTools.getStrToGbk(request.getParameter("onlinetype")); // ���ģʽ
//		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from")); // ��ҵ����
//		String indate_to = CommonTools.getStrToGbk(request.getParameter("indate_to")); // ��ҵ����
//		String shiftid = CommonTools.getStrToGbk(request.getParameter("shift_id")); // ���
//		String isback = CommonTools.getStrToGbk(request.getParameter("isback")); // �Ƿ����
//		String invoicetype = CommonTools.getStrToGbk(request.getParameter("invoicetype")); // ��ҵ��Դ

//		String instockid = CommonTools.getStrToGbk(request.getParameter("instockid")); // ��ⵥ��
		String productid = CommonTools.getStrToGbk(request.getParameter("productid")); // Ʒ��
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo")); // ������Ϣ
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode")); // ��������
		//String productStatus = CommonTools.getStrToGbk(request.getParameter("productStatus"));//��Ʒ״̬

		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow")); // ÿҳ��ʾ����
		
		String startTime = CommonTools.getStrToGbk(request.getParameter("startTime")); // ��ҵ����
		String endTime = CommonTools.getStrToGbk(request.getParameter("endTime")); // ��ҵ����
		String productdate = CommonTools.getStrToGbk(request.getParameter("productdate")); // ��Ʒ��������
		
		String isopen = CommonTools.getStrToGbk(request.getParameter("isopen"));
		if (linerow != null && linerow.length() > 0) {
			maxLine = Integer.parseInt(linerow);
		} else {
			maxLine = 20;
		}
		try {

			strUrl = "/standard/jsp/inbound/newrkd/inbound_search_list.jsp";

			
			String hql = "select j.jobtype, j.jobtypeName, jd.productid, jd.m_strProductName, jd.printdate, jd.punit, jd.m_strUnitName, j.warehouseid, j.warehousename, j.tcargoWhareaId, j.tcargoWhareaName, "
					+ " sum(case when j.status!='5' and j.status!='6' and j.status!='8' then jd.jobnum else 0 end), "
					+ " sum(case when j.status in ('4') then jd.jobnum else 0 end), "
					+ " sum(case when j.status!='5' and j.status!='6' and j.status!='8' and (j.isinvoice='N' or j.isinvoice is null) then jd.jobnum else 0 end) "				
					+ " ,jd.lotinfo,j.Virtualwhid,j.Virtualwhname,jd.productStatus,jd.productStatusName from InoutJob j, InoutJobdetail jd "
					+ " where j.jobid=jd.jobid and j.inOutType='1' and j.isaccount='Y'";
					
					//" and j.traycode='' and j.isinvoice='' "
			if(warehouseid != null && warehouseid.length() > 0)
			{
				hql += " and j.warehouseid='" + warehouseid + "'";
			}
			if(whAreaId != null && whAreaId.length() > 0)
			{
				hql += " and j.tcargoWhareaId='" + whAreaId + "'";
			}
			if(Virtualwhid != null && Virtualwhid.length() > 0)
			{
				hql += " and j.Virtualwhid='" + Virtualwhid + "'";
			}
			
			if(alleyId != null && alleyId.length() > 0)
			{
				hql += " and j.tcargoAlleyId='" + alleyId + "'";
			}
			if(cargospaceid != null && cargospaceid.length() > 0)
			{
				hql += " and j.tcargoSpaceId='" + cargospaceid + "'";
			}
			if(productid != null && productid.length() > 0)
			{
				hql += " and jd.productid='" + productid + "'";
			}
			if(lotinfo != null && lotinfo.length() > 0)
			{
				hql += " and jd.lotinfo='" + lotinfo + "'";
			}
			if(traycode != null && traycode.length() > 0)
			{
				hql += " and j.traycode='" + traycode + "'";
			}
			if(startTime != null && startTime.length() > 0)
			{
				hql += " and j.createtime > '" + startTime + "'";
			}
			if(endTime != null && endTime.length() > 0)
			{
				hql += "  and j.createtime < '" + endTime + " 99:99:99'";
			}
			if(productdate != null && productdate.length() > 0)
			{
				hql += " and jd.printdate='" + productdate + "'";
			}
			
			if(isopen != null && isopen.length() > 0)
			{
				if(isopen.equals("N")){
					hql += " and (j.isinvoice is null or j.isinvoice='" + isopen + "')";
				}else{
					hql += " and j.isinvoice='" + isopen + "'";
				}
				
			}
			
			hql += " group by j.warehouseid, j.tcargoWhareaId,j.Virtualwhid,j.jobtype, jd.productid, jd.printdate,jd.lotinfo, jd.punit, j.inOutType, jd.productStatus";
			hql += " order by j.warehouseid, j.tcargoWhareaId,j.Virtualwhid,j.jobtype, jd.productid";
			
			List ls = dao.searchEntities(hql);
			
			request.setAttribute("exResultList", ls);

			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {

			Logger.error("������==>����ѯ==>�����ҵ��ѯʧ��:" + er.getMessage());
			request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:����ѯ
	 * 
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecSearchJob(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {

		HttpServletRequest request = (HttpServletRequest) hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse) hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO) hsSysParam.get("dao");

		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // �ֿ�
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId")); // ����
		String alleyId = CommonTools.getStrToGbk(request.getParameter("alleyId")); // ���
		String cargospaceid = CommonTools.getStrToGbk(request.getParameter("cargospaceid"));// ��λ

		String onlinetype = CommonTools.getStrToGbk(request.getParameter("onlinetype")); // ���ģʽ
		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from")); // ��ҵ����
		String indate_to = CommonTools.getStrToGbk(request.getParameter("indate_to")); // ��ҵ����
		String shiftid = CommonTools.getStrToGbk(request.getParameter("shift_id")); // ���
		String isback = CommonTools.getStrToGbk(request.getParameter("isback")); // �Ƿ����
		String invoicetype = CommonTools.getStrToGbk(request.getParameter("invoicetype")); // ��ҵ��Դ

		String instockid = CommonTools.getStrToGbk(request.getParameter("instockid")); // ��ⵥ��
		String productid = CommonTools.getStrToGbk(request.getParameter("productid")); // Ʒ��
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo")); // ������Ϣ
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode")); // ��������

		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow")); // ÿҳ��ʾ����
		
//		String indate = CommonTools.getStrToGbk(request.getParameter("indate")); // ��ҵ����
		
//		String isopen = CommonTools.getStrToGbk(request.getParameter("isopen"));
		if (linerow != null && linerow.length() > 0) {
			maxLine = Integer.parseInt(linerow);
		} else {
			maxLine = 20;
		}
		try {

			strUrl = "/standard/jsp/inbound/query/inbound_search_list.jsp";

			InoutJob job = new InoutJob();
			String inOutType = "1"; // �����ҵ
			/* ��ѯ */
			String strQueryHQL = job.getQueryJobADetailHQL(warehouseid, whAreaId, alleyId, cargospaceid, onlinetype, indate_from, indate_to, isback, invoicetype, instockid, productid, lotinfo, traycode, inOutType);
			String strCountHQL = job.getQueryJobADetailHQLCount(warehouseid, whAreaId, alleyId, cargospaceid, onlinetype, indate_from, indate_to, isback, invoicetype, instockid, productid, lotinfo, traycode, inOutType);

			PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, maxLine);

			List ls = pt.getLsResult();
			
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);

			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {

			Logger.error("������==>����ѯ==>�����ҵ��ѯʧ��:" + er.getMessage());
			request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

	/**
	 * ����:������ҵ
	 * 
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecCancel(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {

		HttpServletRequest request = (HttpServletRequest) hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse) hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO) hsSysParam.get("dao");

		String jobids = CommonTools.getStrToGbk(request.getParameter("jobids")); // ��ҵID
		String strUserCode = (String) httpsession.getAttribute("userCode");
		try {
			InoutJob job = new InoutJob();
			String strBackMsg = job.cancelJobs(jobids, strUserCode, dao);
			strUrl = "/standard/jsp/inbound/job/inbound_job_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {

			Logger.error("�û�[" + strUserCode + "]��������==>��ҵ����==>���������ҵʧ��:" + er.getMessage());
			request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}

		return null;
	}

	/**
	 * ����:��ʼ�������ҵ
	 * 
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecinitialize(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {

		HttpServletRequest request = (HttpServletRequest) hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse) hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO) hsSysParam.get("dao");

		String jobids = CommonTools.getStrToGbk(request.getParameter("jobids")); // ��ҵID
		String strUserCode = (String) httpsession.getAttribute("userCode");
		try {
			InoutJob job = new InoutJob();
			String strBackMsg = job.initializeJobs(jobids, strUserCode, dao);
			strUrl = "/standard/jsp/inbound/job/inbound_job_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {

			Logger.error("�û�[" + strUserCode + "]��������==>��ҵ����==>���������ҵʧ��:" + er.getMessage());
			request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}

		return null;
	}

	/**
	 * ����:�ֶ���������ҵ
	 * 
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecFinish(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {

		HttpServletRequest request = (HttpServletRequest) hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse) hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO) hsSysParam.get("dao");

		String jobids = CommonTools.getStrToGbk(request.getParameter("jobids")); // ��ҵID
		String strUserCode = (String) httpsession.getAttribute("userCode");
		try {
			InoutJob job = new InoutJob();
			String strBackMsg = job.finishJobs(jobids, strUserCode, dao);

			strUrl = "/standard/jsp/inbound/job/inbound_job_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {

			Logger.error("�û�[" + strUserCode + "]��������==>��ҵ����==>�ֶ���������ҵʧ��:" + er.getMessage());
			request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}

		return null;
	}

	/**
	 * ��ӡ
	 * 
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecPrint(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		HttpServletRequest request = (HttpServletRequest) hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse) hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO) hsSysParam.get("dao");

		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // �ֿ�
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId")); // ����
		String alleyId = CommonTools.getStrToGbk(request.getParameter("alleyId")); // ���
		String cargospaceid = CommonTools.getStrToGbk(request.getParameter("cargospaceid"));// ��λ

		String onlinetype = CommonTools.getStrToGbk(request.getParameter("onlinetype")); // ���ģʽ
		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from")); // ��ҵ����
		String indate_to = CommonTools.getStrToGbk(request.getParameter("indate_to")); // ��ҵ����
		String shiftid = CommonTools.getStrToGbk(request.getParameter("shift_id")); // ���
		String isback = CommonTools.getStrToGbk(request.getParameter("isback")); // �Ƿ����
		String invoicetype = CommonTools.getStrToGbk(request.getParameter("invoicetype")); // ��ҵ��Դ

		String instockid = CommonTools.getStrToGbk(request.getParameter("instockid")); // ��ⵥ��
		String productid = CommonTools.getStrToGbk(request.getParameter("productid")); // Ʒ��
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo")); // ������Ϣ
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode")); // ��������

		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow")); // ÿҳ��ʾ����
		if (linerow != null && linerow.length() > 0) {
			maxLine = Integer.parseInt(linerow);
		}

		try {
			strUrl = "/standard/jsp/inbound/query/in_rkcx_print.jsp";
			InoutJob job = new InoutJob();
			String inOutType = "1"; // �����ҵ
			/* ��ѯ */
			String strQueryHQL = job.getQueryJobADetailHQL(warehouseid, whAreaId, alleyId, cargospaceid, onlinetype, indate_from, indate_to, isback, invoicetype, instockid, productid, lotinfo, traycode, inOutType);
			// String strCountHQL =
			// job.getQueryJobADetailHQLCount(warehouseid,whAreaId,alleyId,cargospaceid,onlinetype,indate_from,indate_to,isback,invoicetype,instockid,productid,lotinfo,traycode,inOutType);

			// PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL,
			// strQueryHQL, strUrl, 1, maxLine);
			List ls = dao.searchEntities(strQueryHQL);

			request.setAttribute("exResultList", ls);
			// httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {
			Logger.error("������==>��潻��==>��ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}

		return null;
	}

	/**
	 * ����:������ҵ������ⵥ
	 * 
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecCreate(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {

		HttpServletRequest request = (HttpServletRequest) hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse) hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO) hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // �ֿ�
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId")); // ����
		String Virtualwhid = CommonTools.getStrToGbk(request.getParameter("Virtualwhid")); // �߼�����
		String alleyId = CommonTools.getStrToGbk(request.getParameter("alleyId")); // ���
		String cargospaceid = CommonTools.getStrToGbk(request.getParameter("cargospaceid"));// ��λ
		String productid = CommonTools.getStrToGbk(request.getParameter("productid")); // Ʒ��
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo")); // ������Ϣ
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode")); // ��������
		String startTime = CommonTools.getStrToGbk(request.getParameter("startTime")); // ��ҵ����
		String endTime = CommonTools.getStrToGbk(request.getParameter("endTime")); // ��ҵ����
		String productdate = CommonTools.getStrToGbk(request.getParameter("productdate")); // ��Ʒ��������
		String isopen = CommonTools.getStrToGbk(request.getParameter("isopen"));
		
		String jobids = CommonTools.getStrToGbk(request.getParameter("keys")); //
		String condition = CommonTools.getStrToGbk(request.getParameter("condition")); //
		
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));

		String strUserCode = (String) httpsession.getAttribute("userCode");
		List<InboundHeader> lsInbound = new ArrayList<InboundHeader>();
		List<InboundDetail> lsInboundDetail = new ArrayList<InboundDetail>();
		InboundHeader Header = null;
		InboundDetail Detail = null;

		try {
			String strBackMsg = "Y";
			strUrl = "/BMSService?actionCode=inBoundJobAction&method=ricoExecSearchJobCollection" + condition;// inbound/newin/lxyy/inbound_newin_list.jsp
			
			IJobDao ijobdao = new JobDaoImpl(dao);
			InoutJob job = null;
			InoutJobdetail jobD = null;
			List<InoutJob> jobResultLs = new ArrayList<InoutJob>();
			List<InoutJobdetail> jobDResultLs = new ArrayList<InoutJobdetail>();
			String strNo = null;	//��ⵥid
			
			if(flag.equals("1"))
			{
				String[] jobCollection = jobids.split(",");
				if(jobCollection.length > 0)
				{
					for(int i = 0; i < jobCollection.length; i++)
					{
						String[] str = jobCollection[i].split("\\|");
						String hql = "select  j.warehouseid, j.tcargoWhareaId,j.Virtualwhid,j.jobtype, jd.printdate, jd.productid, jd.punit, jd.productStatus, sum(jd.jobnum)"
								 + " from InoutJob j, InoutJobdetail jd  where j.jobid=jd.jobid and j.inOutType='1' and j.status='4' and (j.isinvoice is null or j.isinvoice!='Y') and j.isaccount='Y' "
								 + " and j.warehouseid='" + str[0] + "' and j.tcargoWhareaId='" + str[1] + "' and j.jobtype='" + str[3] + "' and jd.productid='" + str[4] + "' and jd.printdate='" + str[5] + "'and jd.lotinfo='" + str[6] + "'and j.Virtualwhid='" + str[2] + "'";
						hql += " group by j.warehouseid, j.tcargoWhareaId,j.Virtualwhid,j.jobtype, jd.printdate, jd.productid, jd.punit, jd.productStatus";
						List ls = dao.searchEntities(hql);
						if(ls != null && ls.size() > 0)
						{
							// ��ⵥid
							BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(dao);
							BaseSeq seqEn = seqDao.getSeqByType("RKD");
							strNo = SeqTool.getID(seqEn.getSeqType(),"",dao);
							
							Header = new InboundHeader();
							Header.setInstockid(strNo);
							Header.setInvoicetypeid(str[3]);
							Header.setInvoicedate(StrTools.getCurrDateTime(8));
							Header.setCreatetime(StrTools.getCurrDateTime(2));	//��������ʱ��
							Header.setInstatus("1");  		//��ⵥ��״̬id 1-�½���2-ȷ���� 3-���� 
							Header.setCreatemanid(strUserCode);		//����������Ա���
							Header.setWarehouseid(str[0]);  	//�ջ��ֿ�
							lsInbound.add(Header);
							
							for(int j = 0; j < ls.size(); j++)
							{
								Object[] obj = (Object[])ls.get(j);
								Detail = new InboundDetail();
								Detail.setInstockdetailid(SeqTool.getDetailId(strNo,(j+1)+"",3));
								Detail.setInstockid(Header.getInstockid());
								Detail.setInnum(Double.parseDouble(obj[8].toString()));
								Detail.setGetnum(Double.parseDouble(obj[8].toString()));
								Detail.setStatus("1");
								Detail.setPrintdate(obj[4].toString());
								Detail.setProductid(obj[5].toString());
								Detail.setPunit(obj[6].toString());
								Detail.setProductStatus(obj[7].toString());
								lsInboundDetail.add(Detail);
								
								//��ѯ�����б���Ҫ�з����ѯ����j.warehouseid, j.tcargoWhareaId,j.Virtualwhid,j.jobtype, jd.printdate, jd.productid, jd.punit, jd.productStatus"; ��  ����Ĳ�ѯ���� ���������ͬ ֻ����һ������
								String jobHql = "From InoutJob j, InoutJobdetail jd where  j.jobid=jd.jobid and j.inOutType='1' and j.status='4' and (j.isinvoice is null or j.isinvoice!='Y') and j.isaccount='Y'"
										 + " and j.warehouseid='" + str[0] + "' and j.tcargoWhareaId='" + str[1] + "' and j.jobtype='" + str[3] + "' and jd.productid='" + str[4] + "' and jd.printdate='" + str[5] + "'and jd.lotinfo='" + str[6] + "'and j.Virtualwhid='" + str[2] + "'";
								List jobls = dao.searchEntities(jobHql);
								Object[] objjob = null;
								for (int z = 0; z < jobls.size(); z++) {
									objjob = (Object[])jobls.get(z);
									job = (InoutJob)objjob[0];
									job.setBoundstockid(Header.getInstockid());
									job.setIsinvoice("Y");
									job.setBoundstockdetailid(Detail.getInstockdetailid());
									jobResultLs.add(job);
									
									String jobDHql = "From InoutJobdetail jd where  jd.jobid='"+job.getJobid()+"'";
									List jobDls = dao.searchEntities(jobDHql);
									for (int k = 0; k < jobDls.size(); k++) {
										jobD = (InoutJobdetail)jobls.get(k);
										jobD.setIsinvoice("Y");
										jobDResultLs.add(jobD);
									}
								}
								
							}
							ijobdao.addInBoundupdateJob(lsInbound, lsInboundDetail, jobResultLs, jobDResultLs, dao);
						}else{
							strBackMsg = "δ�������������Ϊ��,���������ҵ,����������!!!";
						}
					}
				}
			}
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {

			Logger.error("�û�[" + strUserCode + "]��������==>������ⵥ��==>������ⵥʧ��:" + er.getMessage());
			request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

	/**
	 * ����:����RF������ҵ(�ֹ����)
	 * 
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecCreateCGJob(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {

		HttpServletRequest request = (HttpServletRequest) hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse) hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO) hsSysParam.get("dao");

		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // �ֿ�
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId")); // ����
		String Virtualwhid = CommonTools.getStrToGbk(request.getParameter("Virtualwhid")); // �߼�����
		String intype = CommonTools.getStrToGbk(request.getParameter("intype")); // �������
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode")); // ��������
		String productId = CommonTools.getStrToGbk(request.getParameter("productId")); // ��Ʒid
		//String productName = CommonTools.getStrToGbk(request.getParameter("productName")); // ��Ʒ����
		String productCode = CommonTools.getStrToGbk(request.getParameter("productCode")); // ��Ʒ����
		String punit = CommonTools.getStrToGbk(request.getParameter("punit")); // ��λ
		String productstatus = CommonTools.getStrToGbk(request.getParameter("productstatus")); // ��Ʒ״̬
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo")); 		// �������
		String lotinfo2 = CommonTools.getStrToGbk(request.getParameter("lotinfo2")); 	// ԭ�����
		String printdate = CommonTools.getStrToGbk(request.getParameter("printdate")); 	// ��������
		String supplier = CommonTools.getStrToGbk(request.getParameter("supplier")); 	// ��Ӧ��
		String snumber = CommonTools.getStrToGbk(request.getParameter("snumber")); 	// ���ͺ�
		double jobnum = Double.parseDouble(request.getParameter("jobnum")); 		// ����
		
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		String strUserCode = (String) httpsession.getAttribute("userCode");
		IJobDao ijobdao = null;
		
		try {
			String strBackMsg = "Y";
			if(flag.equals("cprk")){//��Ʒ���
				strUrl = "/rf/lxyy/receipt_cprkd.jsp";
			}else if(flag.equals("cgrk")){//�ֹ����
				strUrl = "/rf/lxyy/receipt_cgrkd.jsp";
			}
			
			String traycodesString = traycode.intern(); //����������
			IAllotBus allotBus = new AllotBusImpl(dao);
			//�����ҵ��
			synchronized (traycodesString) {

				  BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(dao);
		            BaseSeq  seqEn = seqDao.getSeqByType("RKZY");
		            String strNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(),dao); 
					InoutJob job = new InoutJob();
					InoutJobdetail jobdetail = new InoutJobdetail();
					job.setJobid(strNo);
					job.setWarehouseid(warehouseid);
					job.setTcargoWhareaId(whAreaId);
					job.setJobtype(intype);
					job.setOnLineType("1");//��������
					job.setIsaccount("Y");//����
					job.setStatus("2"); //����״̬
					job.setVirtualwhid(Virtualwhid);//�߼�����id
					job.setPriority(1);//���ȼ�
					job.setSnumber(snumber);	//���ͺţ�������ͺ� Ϊ���ͻ��ı���� һ�����������ͬ��
					job.setCreatetime(StrTools.getCurrDateTime(2));
					job.setInOutType("1");//�������
					job.setIsinvoice("N");
					job.setTraycode(traycode);
					job.setRoute("1");//·�� ֱ��
					job.setOpManId(strUserCode);
					
					ScheduleTask task = null;
					//��ȡһ���ջ�λ
					BaseCargospace space = allotBus.getNullSpaceId(warehouseid, whAreaId,"");
					if(space!=null){
						job.setTcargoSpaceId(space.getCargoSpaceId());
						job.setTcargoWhareaId(space.getWhAreaId());
						job.setTcargoAlleyId(space.getCargoAlleyId());
						space.setCsstatus("3");
						job.setWarehouseid(space.getWarehouseid());
			            task = new ScheduleTask();
			            
			            String strTaskId= SeqTool.getID("RW",dao); //��ҵID  
			            task.setTaskid(strTaskId);    //taskid;          ����������
			            task.setJobid(job.getJobid());
			            job.setTaskid(strTaskId); //��������ID 
			            task.setTasktype("1");          //�������� 1-��� 2-���� 3-�ƿ�
			            task.setSplatoon(space.getCsplatoon());       //Դ��λ��
			            task.setScolumn(space.getCscolumn());         //Դ��λ��
			            task.setSfloor(space.getCsfloor());           //Դ��λ��   
			            task.setCargoSpaceId(space.getCargoSpaceId());// ��λID
			            task.setCargoAlleyId(space.getCargoAlleyId());// ���ID
			            task.setWarehouseid(space.getWarehouseid());  // �ֿ�ID
			            task.setWhAreaId(space.getWhAreaId());        // ����ID  
			            task.setStatus("2");          //����״̬ ��ִ��
			            task.setPhase(1);
			            task.setPriority(10);         //���ȼ�	                                
			            task.setCreatetime(StrTools.getCurrDateTime(2));      //ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
			            task.setTraycode(traycode);   //��������
			            task.setTaskpos("2");         //������ 1.���� 2.�����
			            task.setRoute("1");           //ִ��·�� 1-ֱ��/ֱ�� 2-����
			            task.setSnumber(snumber);	  //���ͺ�
						
						jobdetail.setJobid(strNo);
						jobdetail.setProductid(productId);
						jobdetail.setLotinfo(lotinfo);		//�������
						jobdetail.setLotinfo2(lotinfo2);	//ԭ�����
						jobdetail.setPunit(punit);
						jobdetail.setJobnum(jobnum);
						jobdetail.setAssignnum(0);
						jobdetail.setPrintdate(printdate);
						jobdetail.setSupplier(supplier);
						jobdetail.setIsinvoice("N");//�����Ƿ������ɵ��� Y��  N��
						jobdetail.setProductStatus(productstatus);//��Ʒ״̬
						jobdetail.setProductcode(productCode);
						
						ijobdao = new JobDaoImpl(dao);
						ijobdao.addRFJobz(job, jobdetail, task, space, dao);
						Logger.error("�û�[" + strUserCode + "]��������==>RF������ҵ[cg]�ɹ�:jobid=[" +job.getJobid() +"]");
					}else{
						strBackMsg = "��ȡ��λʧ��";
						Logger.error("�û�[" + strUserCode + "]��������==>RF������ҵ[cg]ʧ��:jobid=[" +job.getJobid() +"]");
					}
			}
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {
			Logger.error("�û�[" + strUserCode + "]��������==>RF������ҵ[cg]ʧ��:" + er.getMessage());
			request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:����RF������ҵ(��Ʒ��⡢���ϡ�����)(���ġ�ԭ����)(����)
	 * 
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecCreateRFJob(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {

		HttpServletRequest request = (HttpServletRequest) hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse) hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO) hsSysParam.get("dao");

		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // �ֿ�
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId")); // ����
		String Virtualwhid = CommonTools.getStrToGbk(request.getParameter("Virtualwhid")); // �߼�����
		String intype = CommonTools.getStrToGbk(request.getParameter("intype")); // �������
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode")); // ��������
		String productId = CommonTools.getStrToGbk(request.getParameter("productId")); // ��Ʒid
		//String productName = CommonTools.getStrToGbk(request.getParameter("productName")); // ��Ʒ����
		String productCode = CommonTools.getStrToGbk(request.getParameter("productCode")); // ��Ʒ����
		String punit = CommonTools.getStrToGbk(request.getParameter("punit")); // ��λ
		String productstatus = CommonTools.getStrToGbk(request.getParameter("productstatus")); // ��Ʒ״̬
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo")); 		// �������
		String lotinfo2 = CommonTools.getStrToGbk(request.getParameter("lotinfo2")); 	// ԭ�����
		String printdate = CommonTools.getStrToGbk(request.getParameter("printdate")); 	// ��������
		String supplier = ""; //CommonTools.getStrToGbk(request.getParameter("supplier")); 	// ��Ӧ��
		String snumber = CommonTools.getStrToGbk(request.getParameter("snumber")); 	// ���ͺ�
		double jobnum = Double.parseDouble(request.getParameter("jobnum")); 		// ����
		
		String poid = CommonTools.getStrToGbk(request.getParameter("poid")); 				// poid
		String podetailid = CommonTools.getStrToGbk(request.getParameter("podetailid")); 	// podetailid
		
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		String strUserCode = (String) httpsession.getAttribute("userCode");
		IJobDao ijobdao = null;
		
		try {
			String strBackMsg = "Y";
			if(flag.equals("tlrk")){//�������
				strUrl = "/rf/lxyy/receipt_tlrkd.jsp";
			}else if(flag.equals("xtrk")){//�������
				strUrl = "/rf/lxyy/receipt_xtrkd.jsp";
			}else if(flag.equals("bcrk")){//�������
				strUrl = "/rf/lxyy/receipt_bcrkd_1.jsp";
			}else if(flag.equals("yflrk")){//ԭ�������
				strUrl = "/rf/lxyy/receipt_yflrkd.jsp";
			}else if(flag.equals("qtrk")){//�������
				strUrl = "/rf/lxyy/receipt_qtrkd.jsp";
			}
			
			IInboundPoDao IPoDao = new InboundPoDaoImpl(dao);
			IAllotBus allotBus = new AllotBusImpl(dao);
			InboundPoHeader PoHeader = IPoDao.getInboundPoHeaderById(poid);
			InboundPoDetail PoDetail = IPoDao.getInboundPoDelById(podetailid);
			
			String traycodesString = traycode.intern(); //����������
			//�����ҵ��
			synchronized (traycodesString) {
				if(PoHeader != null && PoDetail != null){
					  if(jobnum <= PoDetail.getPonum() - PoDetail.getUseponum()){
						  BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(dao);
				            BaseSeq  seqEn = seqDao.getSeqByType("RKZY");
				            String strNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(),dao); 
							InoutJob job = new InoutJob();
							InoutJobdetail jobdetail = new InoutJobdetail();
							job.setJobid(strNo);
							job.setWarehouseid(warehouseid);
							job.setTcargoWhareaId(whAreaId);
							job.setJobtype(intype);
							job.setOnLineType("1");//��������
							job.setIsaccount("Y");//����
							job.setStatus("2"); //����״̬
							job.setVirtualwhid(Virtualwhid);//�߼�����id
							job.setPriority(1);//���ȼ�
							job.setSnumber(snumber);	//���ͺţ�������ͺ� Ϊ���ͻ��ı���� һ�����������ͬ��
							job.setCreatetime(StrTools.getCurrDateTime(2));
							job.setInOutType("1");//�������
							job.setIsinvoice("N");
							job.setTraycode(traycode);
							job.setRoute("1");//·�� ֱ��
							job.setOpManId(strUserCode);
							
							ScheduleTask task = null;
							//��ȡһ���ջ�λ
							BaseCargospace space = allotBus.getNullSpaceId(warehouseid, whAreaId,"");
							if(space!=null){
								job.setTcargoSpaceId(space.getCargoSpaceId());
								job.setTcargoWhareaId(space.getWhAreaId());
								job.setTcargoAlleyId(space.getCargoAlleyId());
								space.setCsstatus("3");
								job.setWarehouseid(space.getWarehouseid());
					            task = new ScheduleTask();
					            
					            String strTaskId= SeqTool.getID("RW",dao); //��ҵID  
					            task.setTaskid(strTaskId);    //taskid;          ����������
					            task.setJobid(job.getJobid());
					            job.setTaskid(strTaskId); //��������ID 
					            task.setTasktype("1");          //�������� 1-��� 2-���� 3-����
					            task.setSplatoon(space.getCsplatoon());       //Դ��λ��
					            task.setScolumn(space.getCscolumn());         //Դ��λ��
					            task.setSfloor(space.getCsfloor());           //Դ��λ��   
					            task.setCargoSpaceId(space.getCargoSpaceId());// ��λID
					            task.setCargoAlleyId(space.getCargoAlleyId());// ���ID
					            task.setWarehouseid(space.getWarehouseid());  // �ֿ�ID
					            task.setWhAreaId(space.getWhAreaId());        // ����ID  
					            task.setStatus("2");          //����״̬ ��ִ��
					            task.setPhase(1);
					            task.setPriority(10);         //���ȼ�	                                
					            task.setCreatetime(StrTools.getCurrDateTime(2));      //ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
					            task.setTraycode(traycode);   //��������
					            task.setTaskpos("2");         //������ 1.���� 2.�����
					            task.setRoute("1");           //ִ��·�� 1-ֱ��/ֱ�� 2-����
					            task.setSnumber(snumber);	  //���ͺ�
								
								jobdetail.setJobid(strNo);
								jobdetail.setProductid(productId);
								jobdetail.setLotinfo(lotinfo);		//�������
								jobdetail.setLotinfo2(lotinfo2);	//ԭ�����
								jobdetail.setPunit(punit);
								jobdetail.setJobnum(jobnum);
								jobdetail.setAssignnum(0);
								jobdetail.setPrintdate(printdate);
								jobdetail.setSupplier(PoHeader.getSupplierid());
								jobdetail.setIsinvoice("Y");//�����Ƿ������ɵ��� Y��  N��
								jobdetail.setProductStatus(productstatus);//��Ʒ״̬
								jobdetail.setProductcode(productCode);
								
								ijobdao = new JobDaoImpl(dao);
								ijobdao.addRFJobz(job, jobdetail, task, space, dao);
								Logger.error("�û�[" + strUserCode + "]��������==>RF������ҵ["+flag+"]�ɹ�:jobid=[" +job.getJobid() +"]");
							}else{
								strBackMsg = "��ȡ��λʧ��";
								Logger.error("�û�[" + strUserCode + "]��������==>RF������ҵ["+flag+"]ʧ��:jobid=[" +job.getJobid() +"]");
							}
					  }else{
						  strBackMsg = "��ҵ����������������";
					  }
		            
				}else{
					strBackMsg = "��ȡPO��Ϣʧ��";
				}
	            
			}
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {
			Logger.error("�û�[" + strUserCode + "]��������==>RF������ҵ["+flag+"]ʧ��:" + er.getMessage());
			request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

	/**
	 * ����:����ѯ
	 * 
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecSearchDlist(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {

		HttpServletRequest request = (HttpServletRequest) hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse) hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO) hsSysParam.get("dao");

		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // �ֿ�
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId")); // ����
		String alleyId = CommonTools.getStrToGbk(request.getParameter("alleyId")); // ���
		String cargospaceid = CommonTools.getStrToGbk(request.getParameter("cargospaceid"));// ��λ

		String onlinetype = CommonTools.getStrToGbk(request.getParameter("onlinetype")); // ���ģʽ
		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from")); // ��ҵ����
		String indate_to = CommonTools.getStrToGbk(request.getParameter("indate_to")); // ��ҵ����
		String shiftid = CommonTools.getStrToGbk(request.getParameter("shift_id")); // ���
		String isback = CommonTools.getStrToGbk(request.getParameter("isback")); // �Ƿ����
		String invoicetype = CommonTools.getStrToGbk(request.getParameter("invoicetype")); // ��ҵ��Դ

		String instockid = CommonTools.getStrToGbk(request.getParameter("instockid")); // ��ⵥ��
		String productid = CommonTools.getStrToGbk(request.getParameter("productid")); // Ʒ��
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo")); // ������Ϣ
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode")); // ��������

		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow")); // ÿҳ��ʾ����
		if (linerow != null && linerow.length() > 0) {
			maxLine = Integer.parseInt(linerow);
		} else {
			maxLine = 20;
		}
		try {

			strUrl = "/standard/jsp/inbound/query/inbound_search_list.jsp";

			InoutJob job = new InoutJob();
			String inOutType = "1"; // �����ҵ
			/* ��ѯ */
			String strQueryHQL = job.getQueryJobADetailHQL(warehouseid, whAreaId, alleyId, cargospaceid, onlinetype, indate_from, indate_to, isback, invoicetype, instockid, productid, lotinfo, traycode, inOutType);
			String strCountHQL = job.getQueryJobADetailHQLCount(warehouseid, whAreaId, alleyId, cargospaceid, onlinetype, indate_from, indate_to, isback, invoicetype, instockid, productid, lotinfo, traycode, inOutType);

			PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, maxLine);

			List ls = pt.getLsResult();
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);

			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {

			Logger.error("������==>����ѯ==>�����ҵ��ѯʧ��:" + er.getMessage());
			request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:������ҵ������ⵥ(����������ⵥ)
	 * 
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecCreatePart(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {

		HttpServletRequest request = (HttpServletRequest) hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse) hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO) hsSysParam.get("dao");

		String jobids = CommonTools.getStrToGbk(request.getParameter("keys")); 				// jobids
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); 	// �ֿ�
		String jobtype = CommonTools.getStrToGbk(request.getParameter("intype")); // ��ҵ����

		String strUserCode = (String) httpsession.getAttribute("userCode");
		List<InboundHeader> lsInboundHeader = new ArrayList<InboundHeader>();
		List<InboundDetail> lsInboundDetail = new ArrayList<InboundDetail>();
		List<InoutJob> lsInoutJob = new ArrayList<InoutJob>();
		List<InoutJobdetail> lsInoutJobD = new ArrayList<InoutJobdetail>();
		InboundHeader Header = null;
		InboundDetail Detail = null;

		try {
			String strBackMsg = "Y";
			strUrl = "/standard/jsp/inbound/newrkd/inbound_search_list.jsp";// inbound/newin/lxyy/inbound_newin_list.jsp
			
			IJobDao ijobdao = new JobDaoImpl(dao);
			InoutJob job = null;
			InoutJobdetail jobD = null;
			InoutJobdetail jobD2 = null;
			InoutJobdetail jobDend = null;
			String jobid = null;
			String strNo = null;	//��ⵥid
			String strProductid = null;	//��Ʒid	(����)
			String strPrintdate = null;	//����ʱ��	 (����)
			Boolean isus = null;	//�ж��Ƿ�����ͬһ��ⵥ��ϸ��ϸ
			Boolean isaddD = false;	//�Ƿ��һ����ϸ			
			// ��ⵥid
			BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(dao);
			BaseSeq seqEn = seqDao.getSeqByType("RKD");
			strNo = SeqTool.getID(seqEn.getSeqType(),"",dao);
			
			Header = new InboundHeader();
			Header.setInstockid(strNo);  		    	//��ⵥid
			//Header.setInrequestid("");       		
			//Header.setInvoicetypeid("");	//���뵥����id(��Ʒ��⣬�����˿�)
			Header.setInvoicedate(StrTools.getCurrDateTime(8));  	//����ʱ��
			Header.setCreatetime(StrTools.getCurrDateTime(2));	//��������ʱ��
			Header.setInstatus("1");  		//��ⵥ��״̬id 1-�½���2-ȷ���� 3-���� 
			Header.setCreatemanid(strUserCode);		//����������Ա���
			Header.setWarehouseid(warehouseid);  	//�ջ��ֿ�
			Header.setInvoicetypeid(jobtype);
			
			lsInboundHeader.add(Header);

			double jobnum = 0;
			String [] thejobid = jobids.split(",");
			if(thejobid.length > 0){
				for(int i = 0; i <= thejobid.length; i++){
					if(i<thejobid.length){
						jobid = thejobid[i];
						
						job = ijobdao.getJobById(jobid);
						List<InoutJobdetail> listJobD = ijobdao.getJobDetailByJobId(jobid);
						
						if(listJobD.size() > 0){
							for(int j=0; j < listJobD.size(); j++){
								jobD = listJobD.get(j);
								
								if(i==thejobid.length-1&&j==listJobD.size()-1){
									jobDend = jobD;
								}
								
								if(i==0){
									isus = true;
									strProductid = jobD.getProductid();
									strPrintdate = jobD.getPrintdate();
								}
								
								//�Ƿ�����ͬ��ƷID����ͬ��������
								if(strProductid.equals(jobD.getProductid())&&strPrintdate.equals(jobD.getPrintdate())){
									jobnum += jobD.getJobnum();
									jobD2 = jobD;
									
								}else{
									isus = false;
									isaddD = true;
									strProductid = jobD.getProductid();
									strPrintdate = jobD.getPrintdate();
								}
								
								if(isaddD||i==thejobid.length-1){
									
									//�����ϸ
									Detail = new InboundDetail();
									Detail.setInstockid(strNo);		//��ⵥid
									Detail.setBandrecordid(strNo);
									Detail.setInnum(jobnum);
									Detail.setGetnum(jobnum);
									Detail.setTraycode(job.getTraycode());
									Detail.setLotid("");
									Detail.setLotinfo("");
									Detail.setProductStatus(jobD2.getProductStatus());
									Detail.setStatus("1");
									Detail.setPunit(jobD2.getPunit());
									Detail.setPrintdate(jobD2.getPrintdate());
									Detail.setProductid(jobD2.getProductid());
									
									lsInboundDetail.add(Detail);
									
									job.setBoundstockid(Header.getInstockid());
									job.setBoundstockdetailid(Detail.getInstockdetailid());
									jobD2.setIsinvoice("Y");
									
									lsInoutJob.add(job);
									lsInoutJobD.add(jobD2);
									
									jobnum = jobD.getJobnum();
								}
								
								
							}
						}
						
					}
					
					if(i==thejobid.length&&i!=1){

						//�����ϸ
						Detail = new InboundDetail();
						Detail.setInstockid(strNo);		//��ⵥid
						Detail.setBandrecordid(strNo);
						Detail.setInnum(jobnum);
						Detail.setGetnum(jobnum);
						Detail.setTraycode(job.getTraycode());
						Detail.setLotid("");
						Detail.setLotinfo("");
						Detail.setProductStatus(jobDend.getProductStatus());
						Detail.setStatus("1");
						Detail.setPunit(jobDend.getPunit());
						Detail.setPrintdate(jobDend.getPrintdate());
						Detail.setProductid(jobDend.getProductid());
						
						lsInboundDetail.add(Detail);
						
						job.setBoundstockid(Header.getInstockid());
						job.setBoundstockdetailid(Detail.getInstockdetailid());
						jobDend.setIsinvoice("Y");
						
						lsInoutJob.add(job);
						lsInoutJobD.add(jobDend);
						
					}
				}
					ijobdao = new JobDaoImpl(dao);
					ijobdao.addInBoundupdateJob(lsInboundHeader, lsInboundDetail, lsInoutJob, lsInoutJobD, dao);
				
			}else{
				strBackMsg = "";
			}
			
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {

			Logger.error("�û�[" + strUserCode + "]��������==>������ⵥ��==>������ⵥʧ��:" + er.getMessage());
			request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * ����:���ά�����
	 * 
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecRKAdd(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {

		HttpServletRequest request = (HttpServletRequest) hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse) hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO) hsSysParam.get("dao");

		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // �ֿ�
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId")); // ����
		String Virtualwhid = CommonTools.getStrToGbk(request.getParameter("Virtualwhid")); // �߼�����
		String intype = CommonTools.getStrToGbk(request.getParameter("intype")); // �������
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode")); // ��������
		String productId = CommonTools.getStrToGbk(request.getParameter("productId")); // ��Ʒid
		String productCode = CommonTools.getStrToGbk(request.getParameter("productCode")); // ��Ʒ����
		String punit = CommonTools.getStrToGbk(request.getParameter("punit")); // ��λ
		String productstatus = CommonTools.getStrToGbk(request.getParameter("productstatus")); // ��Ʒ״̬
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo")); 		// �������
		String lotinfo2 = CommonTools.getStrToGbk(request.getParameter("lotinfo2")); 	// ԭ�����
		String printdate = CommonTools.getStrToGbk(request.getParameter("printdate")); 	// ��������
		String supplier = CommonTools.getStrToGbk(request.getParameter("supplier")); 	// ��Ӧ��
		String snumber = CommonTools.getStrToGbk(request.getParameter("snumber")); 	// ���ͺ�
		double jobnum = Double.parseDouble(request.getParameter("jobnum")); 		// ����
		String platoon = request.getParameter("platoon"); 	// ��
		String column = request.getParameter("column"); 	// ��
		String floor = request.getParameter("floor"); 		// ��
		
		String strUserCode = (String) httpsession.getAttribute("userCode");
		IJobDao ijobdao = null;
		
		try {
			String strBackMsg = "Y";
			strUrl = "/standard/jsp/inbound/job/inbound_job_list.jsp";
			String traycodesString = traycode.intern(); //����������
			//�����ҵ��
			synchronized (traycodesString) {
	            BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(dao);
	            BaseSeq  seqEn = seqDao.getSeqByType("RKZY");
	            String strNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(),dao); 
				InoutJob job = new InoutJob();
				InoutJobdetail jobdetail = new InoutJobdetail();
				job.setJobid(strNo);
				job.setWarehouseid(warehouseid);
				job.setTcargoWhareaId(whAreaId);
				job.setJobtype(intype);
				job.setOnLineType("1");//��������
				job.setIsaccount("Y");//����
				job.setStatus("4"); //���״̬
				job.setVirtualwhid(Virtualwhid);//�߼�����id
				job.setPriority(1);//���ȼ�
				job.setSnumber(snumber);	//���ͺţ�������ͺ� Ϊ���ͻ��ı���� һ�����������ͬ��
				job.setCreatetime(StrTools.getCurrDateTime(2));
				job.setInOutType("1");//�������
				job.setIsinvoice("N");
				job.setTraycode(traycode);
				job.setRoute("1");//·�� ֱ��
				job.setOpManId(strUserCode);
				
				ScheduleTask task = null;
				//��ȡһ���ջ�λ
				IBaseCargoSpaceDao bspacedao=new BaseCargoSpaceDaoImpl(dao);
				BaseCargospace space = bspacedao.GetCargospace(platoon, column, floor, warehouseid);
				if(space!=null){
					job.setTcargoSpaceId(space.getCargoSpaceId());
					job.setTcargoWhareaId(space.getWhAreaId());
					job.setTcargoAlleyId(space.getCargoAlleyId());
					space.setCsstatus("2"); //�ĳ�����λ״̬
					job.setWarehouseid(space.getWarehouseid());
		           
					
					jobdetail.setJobid(strNo);
					jobdetail.setProductid(productId);
					jobdetail.setLotinfo(lotinfo);		//�������
					jobdetail.setLotinfo2(lotinfo2);	//ԭ�����
					jobdetail.setPunit(punit);
					jobdetail.setJobnum(jobnum);
					jobdetail.setAssignnum(0);
					jobdetail.setPrintdate(printdate);
					jobdetail.setSupplier(supplier);
					jobdetail.setIsinvoice("N");//�����Ƿ������ɵ��� Y��  N��
					jobdetail.setProductStatus(productstatus);//��Ʒ״̬
					jobdetail.setProductcode(productCode);
					
					
					InventoryStorage inventory = new InventoryStorage();
					inventory.setCargoSpaceId(job.getTcargoSpaceId());
					inventory.setCargoAlleyId(job.getTcargoAlleyId());
					inventory.setWhAreaId(job.getTcargoWhareaId());
					inventory.setWarehouseid(job.getWarehouseid());
					inventory.setVirtualwhid(job.getVirtualwhid());
					inventory.setProductid(jobdetail.getProductid());
					inventory.setProductdate(jobdetail.getPrintdate());
					inventory.setIndate(StrTools.getCurrDateTime(2));
					inventory.setLotid(jobdetail.getLotid());
					inventory.setLotinfo(jobdetail.getLotinfo());
					inventory.setLotinfo2(jobdetail.getLotinfo2());
					inventory.setSupplier(jobdetail.getSupplier());
					inventory.setPackid(jobdetail.getPackid());
					inventory.setIntype("2"); //�ѻ�
					inventory.setPunit(jobdetail.getPunit());
					inventory.setProductstatus(jobdetail.getProductStatus());//��Ʒ״̬
					inventory.setPnum(jobdetail.getJobnum());
					inventory.setInjobid(job.getJobid());
					inventory.setInjobetailid(jobdetail.getJobdetailid());
					inventory.setTraycode(job.getTraycode());
					inventory.setProductcode(jobdetail.getProductcode());
					inventory.setStatus("0"); //δ����״̬
					
					String finishtime = StrTools.getCurrDateTime(2);
					job.setFinishtime(finishtime);//���ʱ��
					inventory.setIndate(finishtime);
					
					job.rkadd(space, job, jobdetail, inventory, task, dao);
					
					Logger.error("�û�[" + strUserCode + "]��������==>��ҵ����==>������ӳɹ�:jobid=[" +job.getJobid() +"]��csid=["+inventory.getInventoryid()+"]");
				}
			}
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {
			Logger.error("�û�[" + strUserCode + "]��������==>��ҵ����==>�������ʧ��:" + er.getMessage());
			request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

	
}
