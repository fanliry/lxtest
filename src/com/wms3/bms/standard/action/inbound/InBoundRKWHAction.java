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
 * 描述:入库维护
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
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//仓库
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//库区
		String alleyId = CommonTools.getStrToGbk(request.getParameter("alleyId"));			//巷道
		String indate = CommonTools.getStrToGbk(request.getParameter("indate"));			//作业日期
		String jobid = CommonTools.getStrToGbk(request.getParameter("jobid"));				//作业号
		String invoicetype = CommonTools.getStrToGbk(request.getParameter("invoicetype"));	//作业来源
		String shiftid = CommonTools.getStrToGbk(request.getParameter("shift_id"));			//班次 
		String package_id = CommonTools.getStrToGbk(request.getParameter("package_id"));	//品名
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));		//托盘条码
		String isback = CommonTools.getStrToGbk(request.getParameter("isback"));			//是否回流
		String jobdetailid = CommonTools.getStrToGbk(request.getParameter("jobdetailid"));	//作业明细号
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));				//标识
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		
        inBoundJobBus = new InBoundJobBusImpl(dao);
		try{
		
			if(flag != null && flag.equals("1")){ 		//入库维护管理 查询作业列表
			
				strUrl = "/standard/jsp/inbound/rkwh/inbound_rkwh_list.jsp";
				
				String sortflg = "asc";
				PagingTool pt = inBoundJobBus.getInboundJobs(warehouseid, whAreaId, alleyId, indate, jobid, invoicetype, "", shiftid, 
						package_id, "", traycode, isback, sortflg, strUrl, maxLine);
				
				List ls = pt.getLsResult();
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
					
			}else if(flag != null && flag.equals("2")){	//入库维护管理 查询作业明细列表

				strUrl = "/standard/jsp/inbound/rkwh/inbound_rkwh_detail.jsp";
				
				List ls = inBoundJobBus.getJobDetails(jobid);
				request.setAttribute("exResultList", ls);
				
			}else if(flag != null && flag.equals("3")){	//入库维护管理 修改作业时候查询作业
			
//				InoutJob job = inBoundJobBus.getJobByJobid(jobid);
				strUrl = "/standard/jsp/inbound/rkwh/inbound_rkwh_update.jsp";
				
				//request.setAttribute("job", job);
				
			}else if(flag != null && flag.equals("4")){	//入库维护管理 修改作业详细时候查询作业详细
			
				//InoutJobdetail jobdetail = inBoundJobBus.getJobDetailBydetailid(jobdetailid);
				strUrl = "/standard/jsp/inbound/rkwh/inbound_rkwh_updatedetail.jsp";
				
				//request.setAttribute("jobdetail", jobdetail);
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("入库管理==>入库维护==>入库作业查询失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加无单据入库作业
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
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//仓库
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//库区
		String alleyId = CommonTools.getStrToGbk(request.getParameter("alleyId"));			//巷道
		String cargospaceid = CommonTools.getStrToGbk(request.getParameter("cargospaceid"));//货位ID
		String intype = CommonTools.getStrToGbk(request.getParameter("intype"));			//入库类型
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));		//托盘条码
		String indate = CommonTools.getStrToGbk(request.getParameter("indate"));			//作业日期
		String shiftid = CommonTools.getStrToGbk(request.getParameter("shift_id"));			//班次 
		String jobdetails = CommonTools.getStrToGbk(request.getParameter("jobdetails"));	//作业明细
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		
		inBoundJobBus = new InBoundJobBusImpl(dao);
		try{
			
			strUrl = "/standard/jsp/inbound/rkwh/inbound_rkwh_list.jsp";
			
			String strBackMsg = inBoundJobBus.addRKWHJob(warehouseid, whAreaId, alleyId, cargospaceid, intype, traycode, indate, 
					shiftid, jobdetails, strUserCode);
			
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
					
		}catch(Exception er){
		
			Logger.error("用户["+strUserCode+"]，入库管理==>入库维护==>添加作业失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改入库作业
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
		
		String jobid = CommonTools.getStrToGbk(request.getParameter("jobid"));		//作业ID
		String jobtype = CommonTools.getStrToGbk(request.getParameter("jobtype"));	//作业型
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));//托盘条码
		String strUserCode = (String)httpsession.getAttribute("userCode");
		
		inBoundJobBus = new InBoundJobBusImpl(dao);	
		try{
		
			String strBackMsg = inBoundJobBus.updateRKWHJob(jobid, jobtype, traycode);
			if(strBackMsg.equals("Y")){
				Logger.info("用户【" + strUserCode + "】了修改入库作业：" + jobid);
			}
			
			strUrl = "/standard/jsp/inbound/rkwh/inbound_rkwh_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
			
			Logger.error("用户["+strUserCode+"]，入库管理==>入库维护==>修改作业["+jobid+"]失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}
	
	/**
	 * 功能:修改入库作业明细key
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
		
		String jobdetailid = CommonTools.getStrToGbk(request.getParameter("jobdetailid"));	//作业明细号
		String key = CommonTools.getStrToGbk(request.getParameter("key"));					//作业明细内容
		String strUserCode = (String)httpsession.getAttribute("userCode");
		
		inBoundJobBus = new InBoundJobBusImpl(dao);	
		try{
		
			String strBackMsg = inBoundJobBus.updateRKWHJobDetail(jobdetailid, key);
			if(strBackMsg.equals("Y")){
				Logger.info("用户【" + strUserCode + "】了修改入库明细作业：" + jobdetailid);
			}
			
			strUrl = "/standard/jsp/inbound/rkwh/inbound_rkwh_detail.jsp";
			
			//InoutJobdetail jobdetail = inBoundJobBus.getJobDetailBydetailid(jobdetailid);
			//List ls = inBoundJobBus.getJobDetails(jobdetail.getJobid());
			
		//	request.setAttribute("exResultList", ls);
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
			
			Logger.error("用户["+strUserCode+"]，入库管理==>入库维护==>修改作业明细["+jobdetailid+"]失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}
	
	/**
	 * 功能:作废入库作业
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
		
		String jobid = CommonTools.getStrToGbk(request.getParameter("jobid"));		//作业ID
		String strUserCode = (String)httpsession.getAttribute("userCode");
		
		inBoundJobBus = new InBoundJobBusImpl(dao);	
		try{
		
			String strBackMsg = inBoundJobBus.cancelRKWHJob(jobid);
			if(strBackMsg.equals("Y")){
				Logger.info("用户【" + strUserCode + "】作废了入库作业(入库维护)：" + jobid);
			}
			
			strUrl = "/standard/jsp/inbound/rkwh/inbound_rkwh_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
			
			Logger.error("用户["+strUserCode+"]，入库管理==>入库维护==>作废作业["+jobid+"]失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}
	
}
