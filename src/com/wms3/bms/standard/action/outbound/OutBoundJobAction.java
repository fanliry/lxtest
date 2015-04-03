package com.wms3.bms.standard.action.outbound;

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
import com.wms3.bms.standard.business.outbound.IOutBoundBus;
import com.wms3.bms.standard.business.outbound.IOutBoundJobBus;
import com.wms3.bms.standard.business.outbound.impl.OutBoundBusImpl;
import com.wms3.bms.standard.business.outbound.impl.OutBoundJobBusImpl;
import com.wms3.bms.standard.dao.job.IJobDao;
import com.wms3.bms.standard.dao.job.impl.JobDaoImpl;
import com.wms3.bms.standard.entity.base.BaseLotSet;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * 描述: 出库作业
 * @author yao
 *
 */
public class OutBoundJobAction extends AbstractAction
{
	protected IOutBoundJobBus outBoundJobBus;
	protected List<BaseLotSet> lsLot;
	protected int maxLine = 6;
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//仓库
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//库区
		String Virtualwhid = CommonTools.getStrToGbk(request.getParameter("Virtualwhid"));  //逻辑库区id
		String productid = CommonTools.getStrToGbk(request.getParameter("productid"));		//品名
		String customerid = CommonTools.getStrToGbk(request.getParameter("customerid"));	//客户
		String jobid = CommonTools.getStrToGbk(request.getParameter("jobid"));				//作业号
		String indate = CommonTools.getStrToGbk(request.getParameter("indate"));			//作业日期
		String shiftid = CommonTools.getStrToGbk(request.getParameter("shift_id"));			//班次 
		String status = CommonTools.getStrToGbk(request.getParameter("status"));			//作业状态
		String taskid = CommonTools.getStrToGbk(request.getParameter("taskid")); 			//调度任务ID
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));		//托盘条码
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));				//标识
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		
        outBoundJobBus = new OutBoundJobBusImpl(dao);
		try{
		
			if(flag != null && flag.equals("1")){ 		//出库作业管理 查询作业列表
			
				strUrl = "/standard/jsp/outbound/job/outbound_job_list.jsp";
				
				PagingTool pt = outBoundJobBus.getOutboundJobs(warehouseid, Virtualwhid, whAreaId, productid, customerid, indate, shiftid, jobid, status, 
						taskid, traycode, strUrl, maxLine);
				
				List ls = pt.getLsResult();
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
					
			}else if(flag != null && flag.equals("2")){	//出库作业管理 查询作业明细列表

				strUrl = "/standard/jsp/outbound/job/outbound_job_detail.jsp";
				
				List ls = outBoundJobBus.getJobDetails(jobid);
				request.setAttribute("exResultList", ls);
				
			}else if(flag != null && flag.equals("3")){ 		//出货查询列表
			
				strUrl = "/standard/jsp/outbound/query/outbound_search_list.jsp";
				
				PagingTool pt = outBoundJobBus.getOutboundJobs(warehouseid, Virtualwhid, whAreaId, productid, customerid, indate, shiftid, jobid, status, 
						taskid, traycode, strUrl, maxLine);
				
				List ls = pt.getLsResult();
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
					
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("出库管理==>作业管理==>出库作业查询失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:手动完成出库作业
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecFinish(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String jobids = CommonTools.getStrToGbk(request.getParameter("jobids"));		//作业ID
		String strUserCode = (String)httpsession.getAttribute("userCode");
		
		outBoundJobBus = new OutBoundJobBusImpl(dao);	
		try{
			
			String strBackMsg = outBoundJobBus.finishJobs(jobids, strUserCode);
			
			strUrl = "/standard/jsp/outbound/job/outbound_job_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
			
			Logger.error("用户["+strUserCode+"]，出库管理==>作业管理==>手动完成出库作业失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}
	/**
	 * 功能:手动完成出库暂存作业）
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecFinishToTem(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String jobid = CommonTools.getStrToGbk(request.getParameter("jobids"));		//作业ID
		String strUserCode = (String)httpsession.getAttribute("userCode");
		
		IOutBoundBus outBoundBus = new OutBoundBusImpl(dao);
		IJobDao jobDao = new JobDaoImpl(dao);
		try{
			List<InoutJobdetail> jobDetail = jobDao.getJobDetailByJobId(jobid);
			InoutJobdetail jobMX = null;
		    if(jobDetail!=null && jobDetail.size()>0){
		    	jobMX = (InoutJobdetail)jobDetail.get(0);
		    }
			String strBackMsg = outBoundBus.updateInventoryAndJob(jobid, jobMX.getJobdetailid(), jobMX.getInventoryid());
			strUrl = "/standard/jsp/outbound/job/outbound_job_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
			
			Logger.error("用户["+strUserCode+"]，出库管理==>作业管理==>手动完成出库作业失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}
	
	/**
	 * 功能:作废出库作业
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
		
		String jobids = CommonTools.getStrToGbk(request.getParameter("jobids"));		//作业ID
		String strUserCode = (String)httpsession.getAttribute("userCode");
		
		outBoundJobBus = new OutBoundJobBusImpl(dao);	
		try{
			
			String strBackMsg = outBoundJobBus.cancelJobs(jobids, strUserCode);
			
			strUrl = "/standard/jsp/outbound/job/outbound_job_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
			
			Logger.error("用户["+strUserCode+"]，出库管理==>作业管理==>作废出库作业失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}
	
	/**
	 * 功能:设定优先级别
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecUpdPriority(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String jobids = CommonTools.getStrToGbk(request.getParameter("jobids"));		//作业IDS
		String priority = CommonTools.getStrToGb2312(request.getParameter("priority"));	//优先级
		String strUserCode = (String)httpsession.getAttribute("userCode");
		
		outBoundJobBus = new OutBoundJobBusImpl(dao);	
		try{
		
			String strBackMsg = outBoundJobBus.updJobStatus(jobids, priority, strUserCode);
			
			strUrl = "/standard/jsp/outbound/job/outbound_job_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
			
		}catch(Exception er){
		
			Logger.error("用户["+strUserCode+"]，出库管理==>作业管理==>设定优先级别失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:出库查询
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecSearchJob(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//仓库
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//库区
		String alleyId = CommonTools.getStrToGbk(request.getParameter("alleyId"));			//巷道
		String cargospaceid = CommonTools.getStrToGbk(request.getParameter("cargospaceid"));//货位

		String onlinetype = CommonTools.getStrToGbk(request.getParameter("onlinetype"));	//出库模式
		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from"));	//作业日期
		String indate_to = CommonTools.getStrToGbk(request.getParameter("indate_to"));		//作业日期
		String shiftid = CommonTools.getStrToGbk(request.getParameter("shift_id"));			//班次 
		
		String productid = CommonTools.getStrToGbk(request.getParameter("productid"));		//品名
		String customerid = CommonTools.getStrToGbk(request.getParameter("customerid"));	//货主
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));		//托盘条码
		
/*		String lotid = CommonTools.getStrToGbk(request.getParameter("lotid"));              //批次ID
		String lotatt1 = CommonTools.getStrToGbk(request.getParameter("lotatt1"));			//批次属性1
		String lotatt2 = CommonTools.getStrToGbk(request.getParameter("lotatt2"));			//批次属性2
		String lotatt3 = CommonTools.getStrToGbk(request.getParameter("lotatt3"));			//批次属性3
		String lotatt4 = CommonTools.getStrToGbk(request.getParameter("lotatt4"));			//批次属性4
		String lotatt5 = CommonTools.getStrToGbk(request.getParameter("lotatt5"));			//批次属性5
		String lotatt6 = CommonTools.getStrToGbk(request.getParameter("lotatt6"));			//批次属性6
		String lotatt7 = CommonTools.getStrToGbk(request.getParameter("lotatt7"));			//批次属性7
		String lotatt8 = CommonTools.getStrToGbk(request.getParameter("lotatt8"));			//批次属性8
		String lotatt9 = CommonTools.getStrToGbk(request.getParameter("lotatt9"));			//批次属性9
		String lotatt10 = CommonTools.getStrToGbk(request.getParameter("lotatt10"));		//批次属性10
		String lotatt11 = CommonTools.getStrToGbk(request.getParameter("lotatt11"));		//批次属性11
		String lotatt12 = CommonTools.getStrToGbk(request.getParameter("lotatt12"));		//批次属性12
		*/
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		
        outBoundJobBus = new OutBoundJobBusImpl(dao);
		try{
			
			strUrl = "/standard/jsp/outbound/query/outbound_search_list.jsp";
			
			PagingTool pt = outBoundJobBus.getOutboundJobDetails(warehouseid, whAreaId, alleyId, cargospaceid, 
					onlinetype, indate_from, indate_to, shiftid, productid, customerid, traycode, 
					"", "", "", "", "", "", "", "", "", "", "", "", "", strUrl, maxLine);
			
/*			PagingTool pt = outBoundJobBus.getOutboundJobDetails(warehouseid, whAreaId, alleyId, cargospaceid, 
					onlinetype, indate_from, indate_to, shiftid, productid, customerid, traycode, 
					lotid, lotatt1, lotatt2, lotatt3, lotatt4, lotatt5, lotatt6, lotatt7, lotatt8, lotatt9, lotatt10, 
					lotatt11, lotatt12, strUrl, maxLine);*/
			
			List ls = pt.getLsResult();
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);

			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("出库管理==>出库查询==>出库作业查询失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
