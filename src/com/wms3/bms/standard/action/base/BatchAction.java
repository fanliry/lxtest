package com.wms3.bms.standard.action.base;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.standard.business.base.IBatchBus;
import com.wms3.bms.standard.business.base.IBatchmeanBus;
import com.wms3.bms.standard.business.base.IBatchruleBus;
import com.wms3.bms.standard.business.base.impl.BatchBusImpl;
import com.wms3.bms.standard.business.base.impl.BatchmeanBusImpl;
import com.wms3.bms.standard.business.base.impl.BatchruleBusImpl;
import com.wms3.bms.standard.entity.base.BaseBatch;
import com.wms3.bms.standard.entity.base.BaseBatchmean;
import com.wms3.bms.standard.entity.base.BaseBatchrule;

/**
 * 描述:批次管理
 * @author fangjie
 *
 */
public class BatchAction extends AbstractAction
{
	protected IBatchBus batchBus;
	protected IBatchmeanBus batchBusmean;
	protected IBatchruleBus batchBusrule;
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String id = CommonTools.getStrToGbk(request.getParameter("id"));		//ID
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		batchBus = new BatchBusImpl(dao);
		batchBusmean = new BatchmeanBusImpl(dao);
		batchBusrule = new BatchruleBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //批次管理 查询批次列表
			{
				strUrl = "/standard/jsp/base/batch/base_batch_left.jsp";
				List ls = batchBus.getBatchList();
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2")) //批次管理 修改时获取批次信息
			{
				strUrl = "/standard/jsp/base/batch/base_batch_update.jsp";
				BaseBatch batch = batchBus.getBatchById(id);
				request.setAttribute("batch", batch);
					
			}else if(flag != null && flag.equals("3")) //批次管理 查询批次意义列表
			{
				strUrl = "/standard/jsp/base/batch/base_batch_mean.jsp";
				List ls = batchBusmean.getBatchmeanByBatchId(id);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("4")) //批次管理 修改时获取批次意义信息
			{
				strUrl = "/standard/jsp/base/batch/base_batch_mean_update.jsp";
				BaseBatchmean batchmean = batchBusmean.getBatchmeanById(id);
				request.setAttribute("batchmean", batchmean);
					
			}else if(flag != null && flag.equals("5")) //批次管理 查询批次规则列表
			{
				strUrl = "/standard/jsp/base/batch/base_batch_rule.jsp";
				List ls = batchBusrule.getBatchruleByBatchId(id);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("6")) //批次管理 修改时获取批次规则信息
			{
				strUrl = "/standard/jsp/base/batch/base_batch_rule_update.jsp";
				BaseBatchrule batchrule = batchBusrule.getBatchruleById(id);
				request.setAttribute("batchrule", batchrule);
					
			}		
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>批次管理==>批次查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加批次
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAddBatch(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String batchname = CommonTools.getStrToGbk(request.getParameter("batch_name"));	//批次名称
		String batchlength = CommonTools.getStrToGbk(request.getParameter("length"));	//批次名
		String useflag = CommonTools.getStrToGbk(request.getParameter("use_flag"));		//是否启用 Y:是 N.否
		
		String strUserName = (String)httpsession.getAttribute("userName");
		
		batchBus = new BatchBusImpl(dao);
		try
		{
        	//新增批次信息
			BaseBatch batch = new BaseBatch(batchname, new Integer(batchlength), useflag);		 		
			batchBus.addBatch(batch);
			
			Logger.info("用户" + strUserName + "添加了批次" + batchname);

			strUrl = "/standard/jsp/base/batch/base_batch_left.jsp";
			List ls = batchBus.getBatchList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>批次管理==>增加批次失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加批次意义
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAddBatchmean(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String batchid = CommonTools.getStrToGbk(request.getParameter("batch_id"));	//批次ID
		String mean = CommonTools.getStrToGbk(request.getParameter("mean"));			//意义
		String startpos = CommonTools.getStrToGbk(request.getParameter("start_pos"));//开始位置
		String endpos = CommonTools.getStrToGbk(request.getParameter("end_pos"));	//结束位置
		
		String strUserName = (String)httpsession.getAttribute("userName");
		
		batchBusmean = new BatchmeanBusImpl(dao);
		try
		{
        	//新增批次意义信息
			BaseBatchmean batchmean = new BaseBatchmean(batchid, mean, new Integer(startpos), new Integer(endpos));		 		
			batchBusmean.addBatchmean(batchmean);
			
			Logger.info("用户" + strUserName + "添加了批次:" + batchid + "的意义：" + mean);

			strUrl = "/standard/jsp/base/batch/base_batch_mean.jsp";
			List ls = batchBusmean.getBatchmeanByBatchId(batchid);
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>批次管理==>增加批次意义失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加批次规则
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAddBatchrule(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String batchid = CommonTools.getStrToGbk(request.getParameter("batch_id"));		//批次ID
		String rule_name = CommonTools.getStrToGbk(request.getParameter("rule_name"));	//规则名称
		String startpos = CommonTools.getStrToGbk(request.getParameter("start_pos"));	//开始位置
		String endpos = CommonTools.getStrToGbk(request.getParameter("end_pos"));		//结束位置
		String ruleinfo = CommonTools.getStrToGbk(request.getParameter("rule_info"));	//规则
		
		String strUserName = (String)httpsession.getAttribute("userName");
		
		batchBusrule = new BatchruleBusImpl(dao);
		try
		{
        	//新增批次规则信息
			BaseBatchrule batchrule = new BaseBatchrule(batchid, rule_name, ruleinfo, new Integer(startpos), new Integer(endpos));		 		
			batchBusrule.addBatchrule(batchrule);
			
			Logger.info("用户" + strUserName + "添加了批次:" + batchid + "的规则：" + rule_name);

			strUrl = "/standard/jsp/base/batch/base_batch_rule.jsp";
			List ls = batchBusrule.getBatchruleByBatchId(batchid);
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>批次管理==>增加批次规则失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改批次
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecEditBatch(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String batchid = CommonTools.getStrToGbk(request.getParameter("batch_id"));		//批次ID
		String batch_name = CommonTools.getStrToGbk(request.getParameter("batch_name"));//批次名字
		String length = CommonTools.getStrToGbk(request.getParameter("length"));		//长度
		String useflag = CommonTools.getStrToGbk(request.getParameter("use_flag"));		//是否启用 Y:是 N.否

		String strUserName = (String)httpsession.getAttribute("userName");
		
		batchBus = new BatchBusImpl(dao);
		try
		{
			if(batchid!=null && !batchid.equals(""))
			{
				BaseBatch batch = batchBus.getBatchById(batchid);
				batch.setUseflag(useflag);	// 是否启用 Y:是 N.否
				batch.setBatchname(batch_name);
				if(length!=null){
						try {
					        Integer.parseInt(length);
						} catch (NumberFormatException ex) {
							length="0";
						}
				}
				batch.setBatchlength(Integer.parseInt(length));
				batchBus.updateBatch(batch);
				Logger.info("用户" + strUserName + "修改了批次" + batchid);
			}
			
			strUrl = "/standard/jsp/base/batch/base_batch_left.jsp";
			List ls = batchBus.getBatchList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>批次管理==>修改批次失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除批次
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecDeleteBatch(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String id = CommonTools.getStrToGbk(request.getParameter("id"));
		String strUserName = (String)httpsession.getAttribute("userName");
		
		batchBus = new BatchBusImpl(dao);
		try
		{
			//删除
			batchBus.deleteBatch(id);	
			Logger.info("用户" + strUserName + "删除了批次" + id);
			
			strUrl = "/standard/jsp/base/batch/base_batch_left.jsp";
			List ls = batchBus.getBatchList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>批次管理==>批次删除失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除批次意义
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecDeleteBatchmean(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String ids = CommonTools.getStrToGbk(request.getParameter("ids"));
		String batchid = CommonTools.getStrToGbk(request.getParameter("batchid"));		//批次id
		String strUserName = (String)httpsession.getAttribute("userName");
		
		batchBusmean = new BatchmeanBusImpl(dao);
		try
		{
			if(ids != null && !ids.equals(""))
			{
				String[] id = ids.split(",");
				for(int i=0; i<id.length; i++)
				{
					//删除
					batchBusmean.deleteBatchmean(id[i]);	
					Logger.info("用户" + strUserName + "删除了批次意义" + id[i]);
				}
			}
			
			strUrl = "/standard/jsp/base/batch/base_batch_mean.jsp";
			List ls = batchBusmean.getBatchmeanByBatchId(batchid);
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>批次管理==>批次意义删除失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除批次规则
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecDeleteBatchrule(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String ids = CommonTools.getStrToGbk(request.getParameter("ids"));
		String batchid = CommonTools.getStrToGbk(request.getParameter("batchid"));		//批次id
		String strUserName = (String)httpsession.getAttribute("userName");
		
		batchBusrule = new BatchruleBusImpl(dao);
		try
		{
			if(ids != null && !ids.equals(""))
			{
				String[] id = ids.split(",");
				for(int i=0; i<id.length; i++)
				{
					//删除
					batchBusrule.deleteBatchrule(id[i]);	
					Logger.info("用户" + strUserName + "删除了批次规则" + id[i]);
				}
			}
			
			strUrl = "/standard/jsp/base/batch/base_batch_rule.jsp";
			List ls = batchBusrule.getBatchruleByBatchId(batchid);
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>批次管理==>批次规则删除失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
