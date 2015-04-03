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
 * 描述:入库作业
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

		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // 仓库
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId")); // 库区
		String Virtualwhid = CommonTools.getStrToGbk(request.getParameter("Virtualwhid")); // 逻辑库区id
		String jobid = CommonTools.getStrToGbk(request.getParameter("jobid")); // 作业号
		String indate = CommonTools.getStrToGbk(request.getParameter("indate")); // 作业日期
		String invoicetype = CommonTools.getStrToGbk(request.getParameter("invoicetype")); // 作业来源
		String instockid = CommonTools.getStrToGbk(request.getParameter("instockid")); // 入库单据id
		String status = CommonTools.getStrToGbk(request.getParameter("status")); // 作业状态
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode")); // 托盘条码
		String isback = CommonTools.getStrToGbk(request.getParameter("isback")); // 是否回流
		String productid = CommonTools.getStrToGbk(request.getParameter("productid")); // 品名
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo")); // 批次信息
		String taskid = CommonTools.getStrToGbk(request.getParameter("taskid")); // 调度任务ID
		String flag = CommonTools.getStrToGbk(request.getParameter("flag")); // 标识
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow")); // 每页显示行数

		String strUserCode = (String) httpsession.getAttribute("userCode");
		if (linerow != null && linerow.length() > 0) {
			maxLine = Integer.parseInt(linerow);
		}

		String errinfo = "";
		try {
			// 每页显示行数
			int iline = 6;
			if (linerow != null && linerow.length() > 0) {
				iline = Integer.parseInt(linerow);
			}
			inBoundJobBus = new InBoundJobBusImpl(dao);
			if (flag != null && flag.equals("1")) { // 入库作业管理 查询作业列表

				strUrl = "/standard/jsp/inbound/job/inbound_job_list.jsp";

				InoutJob job = new InoutJob();
				String inOutType = "1"; // 入库作业
				/* 查询 */
				String strQueryHQL = job.getQueryHQL(warehouseid,Virtualwhid, whAreaId, indate, jobid, instockid, status, traycode, isback, productid, lotinfo, taskid, inOutType);
				String strCountHQL = job.getQueryHQLCount(warehouseid,Virtualwhid, whAreaId, indate, jobid, instockid, status, traycode, isback, productid, lotinfo, taskid, inOutType);

				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
				List ls = pt.getLsResult();// 查询结果
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				errinfo = "入库管理==>作业管理==>入库作业查询失败:";

			} else if (flag != null && flag.equals("2")) { // 入库作业管理 查询作业明细列表

				strUrl = "/standard/jsp/inbound/job/inbound_job_detail.jsp";

				List ls = inBoundJobBus.getJobDetails(jobid);
				request.setAttribute("exResultList", ls);
				errinfo = "入库管理==>作业管理==>查询作业明细列表失败:";

			} else if (flag != null && flag.equals("3")) { // 未执行作业移库到暂存操作
				strUrl = "/standard/jsp/inbound/job/inbound_job_list.jsp";
				InoutJob job = new InoutJob();
				String back_msg = job.finishJobsToTem(jobid, strUserCode, dao);

				String inOutType = "1"; // 入库作业
				/* 查询 */
				String strQueryHQL = job.getQueryHQL(warehouseid,Virtualwhid, whAreaId, indate, jobid, instockid, status, traycode, isback, productid, lotinfo, taskid, inOutType);
				String strCountHQL = job.getQueryHQLCount(warehouseid,Virtualwhid, whAreaId, indate, jobid, instockid, status, traycode, isback, productid, lotinfo, taskid, inOutType);

				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
				List ls = pt.getLsResult();// 查询结果

				request.setAttribute("back_msg", back_msg);
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				errinfo = "入库管理==>作业管理==>待执行作业移库到暂存操作失败:";
			}else if (flag != null && flag.equals("4")) { // 入库作业管理 查询作业列表
				String intype = CommonTools.getStrToGbk(request.getParameter("intype")); // 作业类型
				strUrl = "/standard/jsp/inbound/newrkd/inbound_search_part_list.jsp";

				InoutJob job = new InoutJob();
				String inOutType = "1"; // 入库作业
				/* 查询 */
				String strQueryHQL = job.getQueryJobADetailHQLPart(warehouseid, indate, indate, productid, intype);
				String strCountHQL = job.getQueryJobADetailHQLCountPart(warehouseid, indate, indate, productid, intype);

				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, maxLine);

				List ls = pt.getLsResult();
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				errinfo = "入库管理==>作业管理==>入库作业查询失败:";

			} 
			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {

			Logger.error(errinfo + er.getMessage());
			request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

	/**
	 * 功能:入库查询
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

		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // 仓库
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId")); // 库区
		String Virtualwhid = CommonTools.getStrToGbk(request.getParameter("Virtualwhid")); // 逻辑库区
		String alleyId = CommonTools.getStrToGbk(request.getParameter("alleyId")); // 巷道
		String cargospaceid = CommonTools.getStrToGbk(request.getParameter("cargospaceid"));// 货位

//		String onlinetype = CommonTools.getStrToGbk(request.getParameter("onlinetype")); // 入库模式
//		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from")); // 作业日期
//		String indate_to = CommonTools.getStrToGbk(request.getParameter("indate_to")); // 作业日期
//		String shiftid = CommonTools.getStrToGbk(request.getParameter("shift_id")); // 班次
//		String isback = CommonTools.getStrToGbk(request.getParameter("isback")); // 是否回流
//		String invoicetype = CommonTools.getStrToGbk(request.getParameter("invoicetype")); // 作业来源

//		String instockid = CommonTools.getStrToGbk(request.getParameter("instockid")); // 入库单号
		String productid = CommonTools.getStrToGbk(request.getParameter("productid")); // 品名
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo")); // 批号信息
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode")); // 托盘条码
		//String productStatus = CommonTools.getStrToGbk(request.getParameter("productStatus"));//产品状态

		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow")); // 每页显示行数
		
		String startTime = CommonTools.getStrToGbk(request.getParameter("startTime")); // 作业日期
		String endTime = CommonTools.getStrToGbk(request.getParameter("endTime")); // 作业日期
		String productdate = CommonTools.getStrToGbk(request.getParameter("productdate")); // 产品生产日期
		
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

			Logger.error("入库管理==>入库查询==>入库作业查询失败:" + er.getMessage());
			request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:入库查询
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

		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // 仓库
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId")); // 库区
		String alleyId = CommonTools.getStrToGbk(request.getParameter("alleyId")); // 巷道
		String cargospaceid = CommonTools.getStrToGbk(request.getParameter("cargospaceid"));// 货位

		String onlinetype = CommonTools.getStrToGbk(request.getParameter("onlinetype")); // 入库模式
		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from")); // 作业日期
		String indate_to = CommonTools.getStrToGbk(request.getParameter("indate_to")); // 作业日期
		String shiftid = CommonTools.getStrToGbk(request.getParameter("shift_id")); // 班次
		String isback = CommonTools.getStrToGbk(request.getParameter("isback")); // 是否回流
		String invoicetype = CommonTools.getStrToGbk(request.getParameter("invoicetype")); // 作业来源

		String instockid = CommonTools.getStrToGbk(request.getParameter("instockid")); // 入库单号
		String productid = CommonTools.getStrToGbk(request.getParameter("productid")); // 品名
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo")); // 批号信息
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode")); // 托盘条码

		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow")); // 每页显示行数
		
//		String indate = CommonTools.getStrToGbk(request.getParameter("indate")); // 作业日期
		
//		String isopen = CommonTools.getStrToGbk(request.getParameter("isopen"));
		if (linerow != null && linerow.length() > 0) {
			maxLine = Integer.parseInt(linerow);
		} else {
			maxLine = 20;
		}
		try {

			strUrl = "/standard/jsp/inbound/query/inbound_search_list.jsp";

			InoutJob job = new InoutJob();
			String inOutType = "1"; // 入库作业
			/* 查询 */
			String strQueryHQL = job.getQueryJobADetailHQL(warehouseid, whAreaId, alleyId, cargospaceid, onlinetype, indate_from, indate_to, isback, invoicetype, instockid, productid, lotinfo, traycode, inOutType);
			String strCountHQL = job.getQueryJobADetailHQLCount(warehouseid, whAreaId, alleyId, cargospaceid, onlinetype, indate_from, indate_to, isback, invoicetype, instockid, productid, lotinfo, traycode, inOutType);

			PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, maxLine);

			List ls = pt.getLsResult();
			
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);

			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {

			Logger.error("入库管理==>入库查询==>入库作业查询失败:" + er.getMessage());
			request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

	/**
	 * 功能:作废作业
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

		String jobids = CommonTools.getStrToGbk(request.getParameter("jobids")); // 作业ID
		String strUserCode = (String) httpsession.getAttribute("userCode");
		try {
			InoutJob job = new InoutJob();
			String strBackMsg = job.cancelJobs(jobids, strUserCode, dao);
			strUrl = "/standard/jsp/inbound/job/inbound_job_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {

			Logger.error("用户[" + strUserCode + "]，入库管理==>作业管理==>作废入库作业失败:" + er.getMessage());
			request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}

		return null;
	}

	/**
	 * 功能:初始化入库作业
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

		String jobids = CommonTools.getStrToGbk(request.getParameter("jobids")); // 作业ID
		String strUserCode = (String) httpsession.getAttribute("userCode");
		try {
			InoutJob job = new InoutJob();
			String strBackMsg = job.initializeJobs(jobids, strUserCode, dao);
			strUrl = "/standard/jsp/inbound/job/inbound_job_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {

			Logger.error("用户[" + strUserCode + "]，入库管理==>作业管理==>作废入库作业失败:" + er.getMessage());
			request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}

		return null;
	}

	/**
	 * 功能:手动完成入库作业
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

		String jobids = CommonTools.getStrToGbk(request.getParameter("jobids")); // 作业ID
		String strUserCode = (String) httpsession.getAttribute("userCode");
		try {
			InoutJob job = new InoutJob();
			String strBackMsg = job.finishJobs(jobids, strUserCode, dao);

			strUrl = "/standard/jsp/inbound/job/inbound_job_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {

			Logger.error("用户[" + strUserCode + "]，入库管理==>作业管理==>手动完成入库作业失败:" + er.getMessage());
			request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}

		return null;
	}

	/**
	 * 打印
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

		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // 仓库
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId")); // 库区
		String alleyId = CommonTools.getStrToGbk(request.getParameter("alleyId")); // 巷道
		String cargospaceid = CommonTools.getStrToGbk(request.getParameter("cargospaceid"));// 货位

		String onlinetype = CommonTools.getStrToGbk(request.getParameter("onlinetype")); // 入库模式
		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from")); // 作业日期
		String indate_to = CommonTools.getStrToGbk(request.getParameter("indate_to")); // 作业日期
		String shiftid = CommonTools.getStrToGbk(request.getParameter("shift_id")); // 班次
		String isback = CommonTools.getStrToGbk(request.getParameter("isback")); // 是否回流
		String invoicetype = CommonTools.getStrToGbk(request.getParameter("invoicetype")); // 作业来源

		String instockid = CommonTools.getStrToGbk(request.getParameter("instockid")); // 入库单号
		String productid = CommonTools.getStrToGbk(request.getParameter("productid")); // 品名
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo")); // 批号信息
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode")); // 托盘条码

		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow")); // 每页显示行数
		if (linerow != null && linerow.length() > 0) {
			maxLine = Integer.parseInt(linerow);
		}

		try {
			strUrl = "/standard/jsp/inbound/query/in_rkcx_print.jsp";
			InoutJob job = new InoutJob();
			String inOutType = "1"; // 入库作业
			/* 查询 */
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
			Logger.error("库存管理==>库存交易==>查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}

		return null;
	}

	/**
	 * 功能:根据作业生成入库单
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
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // 仓库
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId")); // 库区
		String Virtualwhid = CommonTools.getStrToGbk(request.getParameter("Virtualwhid")); // 逻辑库区
		String alleyId = CommonTools.getStrToGbk(request.getParameter("alleyId")); // 巷道
		String cargospaceid = CommonTools.getStrToGbk(request.getParameter("cargospaceid"));// 货位
		String productid = CommonTools.getStrToGbk(request.getParameter("productid")); // 品名
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo")); // 批号信息
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode")); // 托盘条码
		String startTime = CommonTools.getStrToGbk(request.getParameter("startTime")); // 作业日期
		String endTime = CommonTools.getStrToGbk(request.getParameter("endTime")); // 作业日期
		String productdate = CommonTools.getStrToGbk(request.getParameter("productdate")); // 产品生产日期
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
			String strNo = null;	//入库单id
			
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
							// 入库单id
							BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(dao);
							BaseSeq seqEn = seqDao.getSeqByType("RKD");
							strNo = SeqTool.getID(seqEn.getSeqType(),"",dao);
							
							Header = new InboundHeader();
							Header.setInstockid(strNo);
							Header.setInvoicetypeid(str[3]);
							Header.setInvoicedate(StrTools.getCurrDateTime(8));
							Header.setCreatetime(StrTools.getCurrDateTime(2));	//单据生成时间
							Header.setInstatus("1");  		//入库单据状态id 1-新建，2-确定， 3-作废 
							Header.setCreatemanid(strUserCode);		//单据生成人员编号
							Header.setWarehouseid(str[0]);  	//收货仓库
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
								
								//查询条件中必须要有分组查询条件j.warehouseid, j.tcargoWhareaId,j.Virtualwhid,j.jobtype, jd.printdate, jd.productid, jd.punit, jd.productStatus"; 及  本身的查询条件 如果条件相同 只罗列一个即可
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
							strBackMsg = "未开单的完成数量为零,请先完成作业,再生产单据!!!";
						}
					}
				}
			}
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {

			Logger.error("用户[" + strUserCode + "]，入库管理==>生成入库单据==>生成入库单失败:" + er.getMessage());
			request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

	/**
	 * 功能:根据RF生成作业(仓管入库)
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

		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // 仓库
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId")); // 库区
		String Virtualwhid = CommonTools.getStrToGbk(request.getParameter("Virtualwhid")); // 逻辑库区
		String intype = CommonTools.getStrToGbk(request.getParameter("intype")); // 入库类型
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode")); // 托盘条码
		String productId = CommonTools.getStrToGbk(request.getParameter("productId")); // 产品id
		//String productName = CommonTools.getStrToGbk(request.getParameter("productName")); // 产品名称
		String productCode = CommonTools.getStrToGbk(request.getParameter("productCode")); // 产品编码
		String punit = CommonTools.getStrToGbk(request.getParameter("punit")); // 单位
		String productstatus = CommonTools.getStrToGbk(request.getParameter("productstatus")); // 物品状态
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo")); 		// 进厂编号
		String lotinfo2 = CommonTools.getStrToGbk(request.getParameter("lotinfo2")); 	// 原厂编号
		String printdate = CommonTools.getStrToGbk(request.getParameter("printdate")); 	// 生产日期
		String supplier = CommonTools.getStrToGbk(request.getParameter("supplier")); 	// 供应商
		String snumber = CommonTools.getStrToGbk(request.getParameter("snumber")); 	// 输送号
		double jobnum = Double.parseDouble(request.getParameter("jobnum")); 		// 数量
		
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		String strUserCode = (String) httpsession.getAttribute("userCode");
		IJobDao ijobdao = null;
		
		try {
			String strBackMsg = "Y";
			if(flag.equals("cprk")){//成品入库
				strUrl = "/rf/lxyy/receipt_cprkd.jsp";
			}else if(flag.equals("cgrk")){//仓管入库
				strUrl = "/rf/lxyy/receipt_cgrkd.jsp";
			}
			
			String traycodesString = traycode.intern(); //托盘条码锁
			IAllotBus allotBus = new AllotBusImpl(dao);
			//入库作业号
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
					job.setOnLineType("1");//联机操作
					job.setIsaccount("Y");//记账
					job.setStatus("2"); //待机状态
					job.setVirtualwhid(Virtualwhid);//逻辑库区id
					job.setPriority(1);//优先级
					job.setSnumber(snumber);	//输送号（入库输送号 为输送机的编码号 一般与巷道号相同）
					job.setCreatetime(StrTools.getCurrDateTime(2));
					job.setInOutType("1");//入库类型
					job.setIsinvoice("N");
					job.setTraycode(traycode);
					job.setRoute("1");//路线 直入
					job.setOpManId(strUserCode);
					
					ScheduleTask task = null;
					//获取一个空货位
					BaseCargospace space = allotBus.getNullSpaceId(warehouseid, whAreaId,"");
					if(space!=null){
						job.setTcargoSpaceId(space.getCargoSpaceId());
						job.setTcargoWhareaId(space.getWhAreaId());
						job.setTcargoAlleyId(space.getCargoAlleyId());
						space.setCsstatus("3");
						job.setWarehouseid(space.getWarehouseid());
			            task = new ScheduleTask();
			            
			            String strTaskId= SeqTool.getID("RW",dao); //作业ID  
			            task.setTaskid(strTaskId);    //taskid;          调度任务编号
			            task.setJobid(job.getJobid());
			            job.setTaskid(strTaskId); //调度任务ID 
			            task.setTasktype("1");          //调度类型 1-入库 2-出库 3-移库
			            task.setSplatoon(space.getCsplatoon());       //源货位排
			            task.setScolumn(space.getCscolumn());         //源货位列
			            task.setSfloor(space.getCsfloor());           //源货位层   
			            task.setCargoSpaceId(space.getCargoSpaceId());// 货位ID
			            task.setCargoAlleyId(space.getCargoAlleyId());// 巷道ID
			            task.setWarehouseid(space.getWarehouseid());  // 仓库ID
			            task.setWhAreaId(space.getWhAreaId());        // 库区ID  
			            task.setStatus("2");          //任务状态 待执行
			            task.setPhase(1);
			            task.setPriority(10);         //优先级	                                
			            task.setCreatetime(StrTools.getCurrDateTime(2));      //时间 时间格式：yyyy-MM-dd hh:mm:ss
			            task.setTraycode(traycode);   //托盘条码
			            task.setTaskpos("2");         //任务方向 1.入库端 2.出库端
			            task.setRoute("1");           //执行路线 1-直入/直出 2-回流
			            task.setSnumber(snumber);	  //输送号
						
						jobdetail.setJobid(strNo);
						jobdetail.setProductid(productId);
						jobdetail.setLotinfo(lotinfo);		//进厂编号
						jobdetail.setLotinfo2(lotinfo2);	//原厂编号
						jobdetail.setPunit(punit);
						jobdetail.setJobnum(jobnum);
						jobdetail.setAssignnum(0);
						jobdetail.setPrintdate(printdate);
						jobdetail.setSupplier(supplier);
						jobdetail.setIsinvoice("N");//设置是否已生成单据 Y是  N否
						jobdetail.setProductStatus(productstatus);//物品状态
						jobdetail.setProductcode(productCode);
						
						ijobdao = new JobDaoImpl(dao);
						ijobdao.addRFJobz(job, jobdetail, task, space, dao);
						Logger.error("用户[" + strUserCode + "]，入库管理==>RF生成作业[cg]成功:jobid=[" +job.getJobid() +"]");
					}else{
						strBackMsg = "获取货位失败";
						Logger.error("用户[" + strUserCode + "]，入库管理==>RF生成作业[cg]失败:jobid=[" +job.getJobid() +"]");
					}
			}
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {
			Logger.error("用户[" + strUserCode + "]，入库管理==>RF生成作业[cg]失败:" + er.getMessage());
			request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:根据RF生成作业(成品入库、退料、销退)(包材、原辅料)(其他)
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

		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // 仓库
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId")); // 库区
		String Virtualwhid = CommonTools.getStrToGbk(request.getParameter("Virtualwhid")); // 逻辑库区
		String intype = CommonTools.getStrToGbk(request.getParameter("intype")); // 入库类型
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode")); // 托盘条码
		String productId = CommonTools.getStrToGbk(request.getParameter("productId")); // 产品id
		//String productName = CommonTools.getStrToGbk(request.getParameter("productName")); // 产品名称
		String productCode = CommonTools.getStrToGbk(request.getParameter("productCode")); // 产品编码
		String punit = CommonTools.getStrToGbk(request.getParameter("punit")); // 单位
		String productstatus = CommonTools.getStrToGbk(request.getParameter("productstatus")); // 物品状态
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo")); 		// 进厂编号
		String lotinfo2 = CommonTools.getStrToGbk(request.getParameter("lotinfo2")); 	// 原厂编号
		String printdate = CommonTools.getStrToGbk(request.getParameter("printdate")); 	// 生产日期
		String supplier = ""; //CommonTools.getStrToGbk(request.getParameter("supplier")); 	// 供应商
		String snumber = CommonTools.getStrToGbk(request.getParameter("snumber")); 	// 输送号
		double jobnum = Double.parseDouble(request.getParameter("jobnum")); 		// 数量
		
		String poid = CommonTools.getStrToGbk(request.getParameter("poid")); 				// poid
		String podetailid = CommonTools.getStrToGbk(request.getParameter("podetailid")); 	// podetailid
		
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		String strUserCode = (String) httpsession.getAttribute("userCode");
		IJobDao ijobdao = null;
		
		try {
			String strBackMsg = "Y";
			if(flag.equals("tlrk")){//退料入库
				strUrl = "/rf/lxyy/receipt_tlrkd.jsp";
			}else if(flag.equals("xtrk")){//销退入库
				strUrl = "/rf/lxyy/receipt_xtrkd.jsp";
			}else if(flag.equals("bcrk")){//包材入库
				strUrl = "/rf/lxyy/receipt_bcrkd_1.jsp";
			}else if(flag.equals("yflrk")){//原辅料入库
				strUrl = "/rf/lxyy/receipt_yflrkd.jsp";
			}else if(flag.equals("qtrk")){//其他入库
				strUrl = "/rf/lxyy/receipt_qtrkd.jsp";
			}
			
			IInboundPoDao IPoDao = new InboundPoDaoImpl(dao);
			IAllotBus allotBus = new AllotBusImpl(dao);
			InboundPoHeader PoHeader = IPoDao.getInboundPoHeaderById(poid);
			InboundPoDetail PoDetail = IPoDao.getInboundPoDelById(podetailid);
			
			String traycodesString = traycode.intern(); //托盘条码锁
			//入库作业号
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
							job.setOnLineType("1");//联机操作
							job.setIsaccount("Y");//记账
							job.setStatus("2"); //待机状态
							job.setVirtualwhid(Virtualwhid);//逻辑库区id
							job.setPriority(1);//优先级
							job.setSnumber(snumber);	//输送号（入库输送号 为输送机的编码号 一般与巷道号相同）
							job.setCreatetime(StrTools.getCurrDateTime(2));
							job.setInOutType("1");//入库类型
							job.setIsinvoice("N");
							job.setTraycode(traycode);
							job.setRoute("1");//路线 直入
							job.setOpManId(strUserCode);
							
							ScheduleTask task = null;
							//获取一个空货位
							BaseCargospace space = allotBus.getNullSpaceId(warehouseid, whAreaId,"");
							if(space!=null){
								job.setTcargoSpaceId(space.getCargoSpaceId());
								job.setTcargoWhareaId(space.getWhAreaId());
								job.setTcargoAlleyId(space.getCargoAlleyId());
								space.setCsstatus("3");
								job.setWarehouseid(space.getWarehouseid());
					            task = new ScheduleTask();
					            
					            String strTaskId= SeqTool.getID("RW",dao); //作业ID  
					            task.setTaskid(strTaskId);    //taskid;          调度任务编号
					            task.setJobid(job.getJobid());
					            job.setTaskid(strTaskId); //调度任务ID 
					            task.setTasktype("1");          //调度类型 1-入库 2-出库 3-回流
					            task.setSplatoon(space.getCsplatoon());       //源货位排
					            task.setScolumn(space.getCscolumn());         //源货位列
					            task.setSfloor(space.getCsfloor());           //源货位层   
					            task.setCargoSpaceId(space.getCargoSpaceId());// 货位ID
					            task.setCargoAlleyId(space.getCargoAlleyId());// 巷道ID
					            task.setWarehouseid(space.getWarehouseid());  // 仓库ID
					            task.setWhAreaId(space.getWhAreaId());        // 库区ID  
					            task.setStatus("2");          //任务状态 待执行
					            task.setPhase(1);
					            task.setPriority(10);         //优先级	                                
					            task.setCreatetime(StrTools.getCurrDateTime(2));      //时间 时间格式：yyyy-MM-dd hh:mm:ss
					            task.setTraycode(traycode);   //托盘条码
					            task.setTaskpos("2");         //任务方向 1.入库端 2.出库端
					            task.setRoute("1");           //执行路线 1-直入/直出 2-回流
					            task.setSnumber(snumber);	  //输送号
								
								jobdetail.setJobid(strNo);
								jobdetail.setProductid(productId);
								jobdetail.setLotinfo(lotinfo);		//进厂编号
								jobdetail.setLotinfo2(lotinfo2);	//原厂编号
								jobdetail.setPunit(punit);
								jobdetail.setJobnum(jobnum);
								jobdetail.setAssignnum(0);
								jobdetail.setPrintdate(printdate);
								jobdetail.setSupplier(PoHeader.getSupplierid());
								jobdetail.setIsinvoice("Y");//设置是否已生成单据 Y是  N否
								jobdetail.setProductStatus(productstatus);//物品状态
								jobdetail.setProductcode(productCode);
								
								ijobdao = new JobDaoImpl(dao);
								ijobdao.addRFJobz(job, jobdetail, task, space, dao);
								Logger.error("用户[" + strUserCode + "]，入库管理==>RF生成作业["+flag+"]成功:jobid=[" +job.getJobid() +"]");
							}else{
								strBackMsg = "获取货位失败";
								Logger.error("用户[" + strUserCode + "]，入库管理==>RF生成作业["+flag+"]失败:jobid=[" +job.getJobid() +"]");
							}
					  }else{
						  strBackMsg = "作业数量超出开单数量";
					  }
		            
				}else{
					strBackMsg = "获取PO信息失败";
				}
	            
			}
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {
			Logger.error("用户[" + strUserCode + "]，入库管理==>RF生成作业["+flag+"]失败:" + er.getMessage());
			request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

	/**
	 * 功能:入库查询
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

		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // 仓库
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId")); // 库区
		String alleyId = CommonTools.getStrToGbk(request.getParameter("alleyId")); // 巷道
		String cargospaceid = CommonTools.getStrToGbk(request.getParameter("cargospaceid"));// 货位

		String onlinetype = CommonTools.getStrToGbk(request.getParameter("onlinetype")); // 入库模式
		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from")); // 作业日期
		String indate_to = CommonTools.getStrToGbk(request.getParameter("indate_to")); // 作业日期
		String shiftid = CommonTools.getStrToGbk(request.getParameter("shift_id")); // 班次
		String isback = CommonTools.getStrToGbk(request.getParameter("isback")); // 是否回流
		String invoicetype = CommonTools.getStrToGbk(request.getParameter("invoicetype")); // 作业来源

		String instockid = CommonTools.getStrToGbk(request.getParameter("instockid")); // 入库单号
		String productid = CommonTools.getStrToGbk(request.getParameter("productid")); // 品名
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo")); // 批号信息
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode")); // 托盘条码

		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow")); // 每页显示行数
		if (linerow != null && linerow.length() > 0) {
			maxLine = Integer.parseInt(linerow);
		} else {
			maxLine = 20;
		}
		try {

			strUrl = "/standard/jsp/inbound/query/inbound_search_list.jsp";

			InoutJob job = new InoutJob();
			String inOutType = "1"; // 入库作业
			/* 查询 */
			String strQueryHQL = job.getQueryJobADetailHQL(warehouseid, whAreaId, alleyId, cargospaceid, onlinetype, indate_from, indate_to, isback, invoicetype, instockid, productid, lotinfo, traycode, inOutType);
			String strCountHQL = job.getQueryJobADetailHQLCount(warehouseid, whAreaId, alleyId, cargospaceid, onlinetype, indate_from, indate_to, isback, invoicetype, instockid, productid, lotinfo, traycode, inOutType);

			PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, maxLine);

			List ls = pt.getLsResult();
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);

			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {

			Logger.error("入库管理==>入库查询==>入库作业查询失败:" + er.getMessage());
			request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:根据作业生成入库单(部分生成入库单)
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
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); 	// 仓库
		String jobtype = CommonTools.getStrToGbk(request.getParameter("intype")); // 作业类型

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
			String strNo = null;	//入库单id
			String strProductid = null;	//产品id	(标量)
			String strPrintdate = null;	//生产时间	 (标量)
			Boolean isus = null;	//判断是否属于同一入库单详细详细
			Boolean isaddD = false;	//是否加一条详细			
			// 入库单id
			BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(dao);
			BaseSeq seqEn = seqDao.getSeqByType("RKD");
			strNo = SeqTool.getID(seqEn.getSeqType(),"",dao);
			
			Header = new InboundHeader();
			Header.setInstockid(strNo);  		    	//入库单id
			//Header.setInrequestid("");       		
			//Header.setInvoicetypeid("");	//申请单类型id(成品入库，销售退库)
			Header.setInvoicedate(StrTools.getCurrDateTime(8));  	//建单时间
			Header.setCreatetime(StrTools.getCurrDateTime(2));	//单据生成时间
			Header.setInstatus("1");  		//入库单据状态id 1-新建，2-确定， 3-作废 
			Header.setCreatemanid(strUserCode);		//单据生成人员编号
			Header.setWarehouseid(warehouseid);  	//收货仓库
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
								
								//是否是相同产品ID，相同生产日期
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
									
									//入库详细
									Detail = new InboundDetail();
									Detail.setInstockid(strNo);		//入库单id
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

						//入库详细
						Detail = new InboundDetail();
						Detail.setInstockid(strNo);		//入库单id
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

			Logger.error("用户[" + strUserCode + "]，入库管理==>生成入库单据==>生成入库单失败:" + er.getMessage());
			request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * 功能:入库维护添加
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

		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // 仓库
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId")); // 库区
		String Virtualwhid = CommonTools.getStrToGbk(request.getParameter("Virtualwhid")); // 逻辑库区
		String intype = CommonTools.getStrToGbk(request.getParameter("intype")); // 入库类型
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode")); // 托盘条码
		String productId = CommonTools.getStrToGbk(request.getParameter("productId")); // 产品id
		String productCode = CommonTools.getStrToGbk(request.getParameter("productCode")); // 产品编码
		String punit = CommonTools.getStrToGbk(request.getParameter("punit")); // 单位
		String productstatus = CommonTools.getStrToGbk(request.getParameter("productstatus")); // 物品状态
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo")); 		// 进厂编号
		String lotinfo2 = CommonTools.getStrToGbk(request.getParameter("lotinfo2")); 	// 原厂编号
		String printdate = CommonTools.getStrToGbk(request.getParameter("printdate")); 	// 生产日期
		String supplier = CommonTools.getStrToGbk(request.getParameter("supplier")); 	// 供应商
		String snumber = CommonTools.getStrToGbk(request.getParameter("snumber")); 	// 输送号
		double jobnum = Double.parseDouble(request.getParameter("jobnum")); 		// 数量
		String platoon = request.getParameter("platoon"); 	// 排
		String column = request.getParameter("column"); 	// 列
		String floor = request.getParameter("floor"); 		// 层
		
		String strUserCode = (String) httpsession.getAttribute("userCode");
		IJobDao ijobdao = null;
		
		try {
			String strBackMsg = "Y";
			strUrl = "/standard/jsp/inbound/job/inbound_job_list.jsp";
			String traycodesString = traycode.intern(); //托盘条码锁
			//入库作业号
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
				job.setOnLineType("1");//联机操作
				job.setIsaccount("Y");//记账
				job.setStatus("4"); //完成状态
				job.setVirtualwhid(Virtualwhid);//逻辑库区id
				job.setPriority(1);//优先级
				job.setSnumber(snumber);	//输送号（入库输送号 为输送机的编码号 一般与巷道号相同）
				job.setCreatetime(StrTools.getCurrDateTime(2));
				job.setInOutType("1");//入库类型
				job.setIsinvoice("N");
				job.setTraycode(traycode);
				job.setRoute("1");//路线 直入
				job.setOpManId(strUserCode);
				
				ScheduleTask task = null;
				//获取一个空货位
				IBaseCargoSpaceDao bspacedao=new BaseCargoSpaceDaoImpl(dao);
				BaseCargospace space = bspacedao.GetCargospace(platoon, column, floor, warehouseid);
				if(space!=null){
					job.setTcargoSpaceId(space.getCargoSpaceId());
					job.setTcargoWhareaId(space.getWhAreaId());
					job.setTcargoAlleyId(space.getCargoAlleyId());
					space.setCsstatus("2"); //改成满货位状态
					job.setWarehouseid(space.getWarehouseid());
		           
					
					jobdetail.setJobid(strNo);
					jobdetail.setProductid(productId);
					jobdetail.setLotinfo(lotinfo);		//进厂编号
					jobdetail.setLotinfo2(lotinfo2);	//原厂编号
					jobdetail.setPunit(punit);
					jobdetail.setJobnum(jobnum);
					jobdetail.setAssignnum(0);
					jobdetail.setPrintdate(printdate);
					jobdetail.setSupplier(supplier);
					jobdetail.setIsinvoice("N");//设置是否已生成单据 Y是  N否
					jobdetail.setProductStatus(productstatus);//物品状态
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
					inventory.setIntype("2"); //脱机
					inventory.setPunit(jobdetail.getPunit());
					inventory.setProductstatus(jobdetail.getProductStatus());//物品状态
					inventory.setPnum(jobdetail.getJobnum());
					inventory.setInjobid(job.getJobid());
					inventory.setInjobetailid(jobdetail.getJobdetailid());
					inventory.setTraycode(job.getTraycode());
					inventory.setProductcode(jobdetail.getProductcode());
					inventory.setStatus("0"); //未分配状态
					
					String finishtime = StrTools.getCurrDateTime(2);
					job.setFinishtime(finishtime);//完成时间
					inventory.setIndate(finishtime);
					
					job.rkadd(space, job, jobdetail, inventory, task, dao);
					
					Logger.error("用户[" + strUserCode + "]，入库管理==>作业管理==>入库增加成功:jobid=[" +job.getJobid() +"]，csid=["+inventory.getInventoryid()+"]");
				}
			}
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {
			Logger.error("用户[" + strUserCode + "]，入库管理==>作业管理==>入库增加失败:" + er.getMessage());
			request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

	
}
