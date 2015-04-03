package com.wms3.bms.standard.action.rule;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.standard.business.rule.IAllocationRuleBus;
import com.wms3.bms.standard.business.rule.impl.AllocationRuleImpl;
import com.wms3.bms.standard.entity.rule.RuleAllocation;
import com.wms3.bms.standard.entity.rule.RuleAllocationDetail;

/**
 * 描述:分配规则管理
 * @author 
 *
 */
public class AllocationRuleAction extends AbstractAction
{
	protected IAllocationRuleBus allocationRuleBus;
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String allocationid = CommonTools.getStrToGbk(request.getParameter("allocationid"));		//分配规则Id
		String allocationdetailid = CommonTools.getStrToGbk(request.getParameter("allocationdetailid"));	//分配规则详细ID
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//所属仓库ID
		String descr = CommonTools.getStrToGbk(request.getParameter("descr"));				//分配规则描述
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));

        allocationRuleBus = new AllocationRuleImpl(dao);
        List ls = null;
		try
		{
			
			if(flag != null && flag.equals("1")) //分配规则管理 查询列表
			{
				strUrl = "/standard/jsp/rule/allocation/rule_allocation_list.jsp";
				
				ls = allocationRuleBus.getAllocationRuleQuery(warehouseid, descr);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2"))//分配规则管理 查询明细列表
			{
				strUrl = "/standard/jsp/rule/allocation/rule_allocation_detail.jsp";
				
				ls = allocationRuleBus.getAllocationDetailRuleById(allocationid);
				request.setAttribute("exResultList", ls);
				
			}else if(flag != null && flag.equals("3"))//分配规则管理 修改规则时获取信息
			{
				strUrl = "/standard/jsp/rule/allocation/rule_allocation_update.jsp";
				
				RuleAllocation allocationRule = allocationRuleBus.getAllocationRuleById(allocationid);
				request.setAttribute("allocationRule", allocationRule); 
				
			}else if(flag != null && flag.equals("4"))//分配规则管理 修改明细时获取信息
			{
				strUrl = "/standard/jsp/rule/allocation/rule_allocation_detail_update.jsp";
				
				RuleAllocationDetail allocationDetailRule = allocationRuleBus.getPutDetailByDetailId(allocationdetailid);
				request.setAttribute("allocationDetailRule", allocationDetailRule); 
				
				RuleAllocation allocationRule = allocationRuleBus.getAllocationRuleById(allocationid);
				request.setAttribute("warehouseid", allocationRule.getWarehouseid());
				
			}else if(flag != null && flag.equals("5"))//分配规则管理 修改优先顺位时获取信息
			{
				strUrl = "/standard/jsp/rule/allocation/rule_allocation_sort.jsp";
				
				ls = allocationRuleBus.getAllocationDetailRuleById(allocationid);
				request.setAttribute("exResultList", ls);
				
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("规则管理==>分配规则==>分配规则查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加分配规则
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdd(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String descr = CommonTools.getStrToGb2312(request.getParameter("descr"));	//描述
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//所属仓库ID
		String remark = CommonTools.getStrToGb2312(request.getParameter("remark"));	//备注
		
		String strUserName = (String)httpsession.getAttribute("userName");
		allocationRuleBus = new AllocationRuleImpl(dao);
		try
		{
        	//分配规则
			RuleAllocation allocationRule = new RuleAllocation();
			allocationRule.setDescr(descr);					//描述
			allocationRule.setWarehouseid(warehouseid);		//所属仓库ID
			allocationRule.setRemark(remark);				//备注

			allocationRuleBus.addAllocationRule(allocationRule);
			
			Logger.info("用户:" + strUserName + "添加了分配规则:" + descr);

			strUrl = "/standard/jsp/rule/allocation/rule_allocation_list.jsp";
			
			List ls = allocationRuleBus.getAllocationRuleQuery("", "");
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("规则管理==>分配规则==>增加分配规则失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改分配规则
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecEdit(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String allocationid = CommonTools.getStrToGbk(request.getParameter("allocationid"));		//分配规则Id
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//所属仓库ID
		String descr = CommonTools.getStrToGb2312(request.getParameter("descr"));			//描述
		String remark = CommonTools.getStrToGb2312(request.getParameter("remark"));			//备注
		
		String strUserName = (String)httpsession.getAttribute("userName");
		allocationRuleBus = new AllocationRuleImpl(dao);
		try
		{
			if(allocationid != null && allocationid.length()>0)
			{
				RuleAllocation allocationRule = allocationRuleBus.getAllocationRuleById(allocationid);
				allocationRule.setDescr(descr);				//描述
				allocationRule.setWarehouseid(warehouseid);	//所属仓库ID
				allocationRule.setRemark(remark);			//备注
				
				allocationRuleBus.updateAllocationRule(allocationRule);
				Logger.info("用户:" + strUserName + "修改了分配规则:" + allocationid);
			}
			
			strUrl = "/standard/jsp/rule/allocation/rule_allocation_list.jsp";
			
			List ls = allocationRuleBus.getAllocationRuleQuery("", "");
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("规则管理==>分配规则==>修改分配规则失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除分配规则
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecDelete(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String ids = CommonTools.getStrToGbk(request.getParameter("ids"));
		String strUserName = (String)httpsession.getAttribute("userName");
		allocationRuleBus = new AllocationRuleImpl(dao);
		try
		{
			if(ids != null && !ids.equals(""))
			{
				String[] id = ids.split(",");
				for(int i=0; i<id.length; i++)
				{
					allocationRuleBus.deleteAllocationRule(id[i]);
					Logger.info("用户:" + strUserName + "删除了分配规则:" + id[i] + "以及详细");
				}
			}
			
			strUrl = "/standard/jsp/rule/allocation/rule_allocation_list.jsp";
			
			List ls = allocationRuleBus.getAllocationRuleQuery("", "");
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("规则管理==>分配规则==>删除分配规则失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加分配规则详细
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAddDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
	    String allocationid = CommonTools.getStrToGbk(request.getParameter("allocationid"));  	//分配规则ID
	    String sort = CommonTools.getStrToGbk(request.getParameter("sort"));					//优先顺位
		String ruleconfigid = CommonTools.getStrToGbk(request.getParameter("ruleconfigid"));	//规则配置ID
	    String enableflag = CommonTools.getStrToGbk(request.getParameter("enableflag"));		//是否生效
	    
	    String clearloc_flag = CommonTools.getStrToGbk(request.getParameter("clearloc_flag"));  //是否清仓
	    String apart_flag = CommonTools.getStrToGbk(request.getParameter("apart_flag"));		//是否拆零
	    String over_flag = CommonTools.getStrToGbk(request.getParameter("over_flag"));    		//是否拣货位超量分配
	    String auto_flag = CommonTools.getStrToGbk(request.getParameter("auto_flag"));    		//是否自动产生补货任务
	    String bulkpick_flag = CommonTools.getStrToGbk(request.getParameter("bulkpick_flag"));  //是否存储位拣选
	    String part_flag = CommonTools.getStrToGbk(request.getParameter("part_flag"));    		//是否拆包
	    String unit_flag = CommonTools.getStrToGbk(request.getParameter("unit_flag"));    		//计量单位
	    
	    String tozone = CommonTools.getStrToGbk(request.getParameter("tozone"));				//目标库区
	    String tolocationid = CommonTools.getStrToGbk(request.getParameter("tolocationid"));	//目标库位
	    String toalleys = CommonTools.getStrToGbk(request.getParameter("toalleys"));			//目标巷道，可多条
	    String part_allocation_flag = CommonTools.getStrToGbk(request.getParameter("part_allocation_flag"));//是否允许部分分配 N-否,Y-是
	    
	    String remark = CommonTools.getStrToGbk(request.getParameter("remark"));    	//备注
		
		String strUserName = (String)httpsession.getAttribute("userName");
		allocationRuleBus = new AllocationRuleImpl(dao);
		try
		{
        	//分配规则详细
			RuleAllocationDetail allocationDetailRule = new RuleAllocationDetail();
			
			allocationDetailRule.setAllocationid(allocationid) ;  	//分配规则ID
			allocationDetailRule.setSort(Integer.parseInt(sort));	//优先顺位
			allocationDetailRule.setRuleconfigid(ruleconfigid);		//规则配置ID
			allocationDetailRule.setEnableflag(enableflag);			//是否生效
		    
			allocationDetailRule.setClearloc_flag(clearloc_flag);	//是否清仓
			allocationDetailRule.setApart_flag(apart_flag);			//是否拆零
			allocationDetailRule.setOver_flag(over_flag);			//是否拣货位超量分配
			allocationDetailRule.setAuto_flag(auto_flag);			//是否自动产生补货任务
			allocationDetailRule.setBulkpick_flag(bulkpick_flag);	//是否存储位拣选
			allocationDetailRule.setPart_flag(part_flag);			//是否拆包
			allocationDetailRule.setUnit_flag(unit_flag);			//计量单位
			
			allocationDetailRule.setTozone(tozone);					//目标库区
			allocationDetailRule.setTolocationid(tolocationid);		//目标库位
			allocationDetailRule.setToalleys(toalleys);				//目标巷道，可多条
		    allocationDetailRule.setPart_allocation_flag(part_allocation_flag);//是否允许部分分配 N-否,Y-是
		    
		    allocationDetailRule.setRemark(remark);    				//备注

			allocationRuleBus.addAllocationDetailRule(allocationDetailRule);
			
			Logger.info("用户:" + strUserName + "添加了分配规则:" + allocationid + "的详细");

			strUrl = "/standard/jsp/rule/allocation/rule_allocation_detail.jsp";
			
			List ls = allocationRuleBus.getAllocationDetailRuleById(allocationid);
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("规则管理==>分配规则==>增加分配规则详细失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改分配规则详细
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecEditDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
	    String allocationid = CommonTools.getStrToGbk(request.getParameter("allocationid"));  		//分配规则ID
	    String allocationdetailid = CommonTools.getStrToGbk(request.getParameter("allocationdetailid"));	//分配规则详细ID
		String ruleconfigid = CommonTools.getStrToGbk(request.getParameter("ruleconfigid"));	//规则配置ID
	    String enableflag = CommonTools.getStrToGbk(request.getParameter("enableflag"));		//是否生效
	    
	    String clearloc_flag = CommonTools.getStrToGbk(request.getParameter("clearloc_flag"));  //是否清仓
	    String apart_flag = CommonTools.getStrToGbk(request.getParameter("apart_flag"));		//是否拆零
	    String over_flag = CommonTools.getStrToGbk(request.getParameter("over_flag"));    		//是否拣货位超量分配
	    String auto_flag = CommonTools.getStrToGbk(request.getParameter("auto_flag"));    		//是否自动产生补货任务
	    String bulkpick_flag = CommonTools.getStrToGbk(request.getParameter("bulkpick_flag"));  //是否存储位拣选
	    String part_flag = CommonTools.getStrToGbk(request.getParameter("part_flag"));    		//是否拆包
	    String unit_flag = CommonTools.getStrToGbk(request.getParameter("unit_flag"));    		//计量单位
	    
	    String tozone = CommonTools.getStrToGbk(request.getParameter("tozone"));				//目标库区
	    String tolocationid = CommonTools.getStrToGbk(request.getParameter("tolocationid"));	//目标库位
	    String toalleys = CommonTools.getStrToGbk(request.getParameter("toalleys"));			//目标巷道，可多条
	    String part_allocation_flag = CommonTools.getStrToGbk(request.getParameter("part_allocation_flag"));//是否允许部分分配 N-否,Y-是
	    
	    String remark = CommonTools.getStrToGbk(request.getParameter("remark"));    	//备注
		
		String strUserName = (String)httpsession.getAttribute("userName");
		allocationRuleBus = new AllocationRuleImpl(dao);
		try
		{
			RuleAllocationDetail allocationDetailRule = allocationRuleBus.getPutDetailByDetailId(allocationdetailid);
			allocationDetailRule.setRuleconfigid(ruleconfigid);		//规则配置ID
			allocationDetailRule.setEnableflag(enableflag);			//是否生效
		    
			allocationDetailRule.setClearloc_flag(clearloc_flag);	//是否清仓
			allocationDetailRule.setApart_flag(apart_flag);			//是否拆零
			allocationDetailRule.setOver_flag(over_flag);			//是否拣货位超量分配
			allocationDetailRule.setAuto_flag(auto_flag);			//是否自动产生补货任务
			allocationDetailRule.setBulkpick_flag(bulkpick_flag);	//是否存储位拣选
			allocationDetailRule.setPart_flag(part_flag);			//是否拆包
			allocationDetailRule.setUnit_flag(unit_flag);			//计量单位
			
			allocationDetailRule.setTozone(tozone);					//目标库区
			allocationDetailRule.setTolocationid(tolocationid);		//目标库位
			allocationDetailRule.setToalleys(toalleys);				//目标巷道，可多条
		    allocationDetailRule.setPart_allocation_flag(part_allocation_flag);//是否允许部分分配 N-否,Y-是
			
		    allocationDetailRule.setRemark(remark);    				//备注
		    
		    allocationRuleBus.updateAllocationDetailRule(allocationDetailRule);
			
			Logger.info("用户:" + strUserName + "修改了分配规则详细:" + allocationdetailid);

			strUrl = "/standard/jsp/rule/allocation/rule_allocation_detail.jsp";
			
			List ls = allocationRuleBus.getAllocationDetailRuleById(allocationid);
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("规则管理==>分配规则==>修改分配规则详细失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除分配规则详细
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecDeleteDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String ids = CommonTools.getStrToGbk(request.getParameter("ids"));
		String strUserName = (String)httpsession.getAttribute("userName");
		allocationRuleBus = new AllocationRuleImpl(dao);
		try
		{
			String allocationid = "";	 //分配规则ID
			if(ids != null && !ids.equals(""))
			{
				String[] id = ids.split(",");
				for(int i=0; i<id.length; i++){
				
					String[] arr = id[i].split("\\|");		//规则详细ID +"|" + 分配规则ID 
					allocationid = arr[1];
					
					allocationRuleBus.deleteAllocationDetailRule(arr[0]);
					Logger.info("用户:" + strUserName + "删除了分配规则详细:" + arr[0]);
				}
			}
			
			strUrl = "/standard/jsp/rule/allocation/rule_allocation_detail.jsp";
			
			List ls = allocationRuleBus.getAllocationDetailRuleById(allocationid);
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("规则管理==>分配规则==>删除分配规则详细失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改分配规则详细的优先顺位
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecEditSorts(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String allocationid = CommonTools.getStrToGbk(request.getParameter("allocationid"));  //分配规则ID
		String detailids = CommonTools.getStrToGbk(request.getParameter("detailids"));	//分配规则详细ID
		String sorts = CommonTools.getStrToGbk(request.getParameter("sorts"));			//优先顺位
		String strUserName = (String)httpsession.getAttribute("userName");
		allocationRuleBus = new AllocationRuleImpl(dao);
		try
		{
			allocationRuleBus.updateAllocationDetailRuleSorts(detailids, sorts);
			Logger.info("用户:" + strUserName + "修改分配规则" + allocationid +"的优先顺位");

			
			strUrl = "/standard/jsp/rule/allocation/rule_allocation_detail.jsp";
			
			List ls = allocationRuleBus.getAllocationDetailRuleById(allocationid);
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("规则管理==>分配规则==>修改分配规则详细的优先顺位失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}