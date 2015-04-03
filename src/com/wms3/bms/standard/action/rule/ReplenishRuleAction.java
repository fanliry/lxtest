package com.wms3.bms.standard.action.rule;

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
import com.wms3.bms.standard.business.rule.IReplenishRuleBus;
import com.wms3.bms.standard.business.rule.impl.ReplenishRuleImpl;
import com.wms3.bms.standard.entity.rule.RuleReplenish;

/**
 * 描述:补货规则管理
 * @author 
 *
 */
public class ReplenishRuleAction extends AbstractAction
{
	protected IReplenishRuleBus replenishRuleBus;
	protected int maxLine = 20;		//分页显示的行数；
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String replenishid = CommonTools.getStrToGbk(request.getParameter("replenishid"));	//补货规则Id
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//所属仓库ID
		String descr = CommonTools.getStrToGbk(request.getParameter("descr"));				//补货规则描述
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        replenishRuleBus = new ReplenishRuleImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //补货规则管理 查询列表
			{
				strUrl = "/standard/jsp/rule/replenish/rule_replenish_list.jsp";
				
				PagingTool pt = replenishRuleBus.getReplenishRuleQuery(warehouseid, descr, strUrl, maxLine);
				List ls = pt.getLsResult();
				
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
					
			}else if(flag != null && flag.equals("2"))//补货规则管理 修改时获取信息
			{
				strUrl = "/standard/jsp/rule/replenish/rule_replenish_update.jsp";
				
				RuleReplenish replenishRule = replenishRuleBus.getReplenishRuleById(replenishid);
				request.setAttribute("replenishRule", replenishRule); 
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("规则管理==>补货规则==>补货规则查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加补货规则
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
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));		//所属仓库ID
		String ruleconfigid = CommonTools.getStrToGb2312(request.getParameter("ruleconfigid"));	//规则配置ID
		
		String ea_lowerlimit = CommonTools.getStrToGb2312(request.getParameter("ea_lowerlimit"));	//件拣下限
		String ea_uplimit = CommonTools.getStrToGb2312(request.getParameter("ea_uplimit"));			//件拣上限
		String ea_minreplenishqty = CommonTools.getStrToGb2312(request.getParameter("ea_minreplenishqty"));	//件拣最小补货数
		
		String cs_lowerlimit = CommonTools.getStrToGb2312(request.getParameter("cs_lowerlimit"));	//件拣下限
		String cs_uplimit = CommonTools.getStrToGb2312(request.getParameter("cs_uplimit"));			//件拣上限
		String cs_minreplenishqty = CommonTools.getStrToGb2312(request.getParameter("cs_minreplenishqty"));	//件拣最小补货数
		
		String ot_lowerlimit = CommonTools.getStrToGb2312(request.getParameter("ot_lowerlimit"));	//箱件拣下限
		String ot_uplimit = CommonTools.getStrToGb2312(request.getParameter("ot_uplimit"));			//箱件拣上限
		String ot_minreplenishqty = CommonTools.getStrToGb2312(request.getParameter("ot_minreplenishqty"));	//箱件拣最小补货数
		
		String remark = CommonTools.getStrToGb2312(request.getParameter("remark"));	//备注
		
		String strUserName = (String)httpsession.getAttribute("userName");
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        replenishRuleBus = new ReplenishRuleImpl(dao);
		try
		{
        	//补货规则信息
			RuleReplenish replenishRule = new RuleReplenish();
			replenishRule.setDescr(descr);					//描述
			replenishRule.setWarehouseid(warehouseid);		//所属仓库ID
			replenishRule.setRuleconfigid(ruleconfigid);	//规则配置ID
			replenishRule.setEa_lowerlimit(Double.parseDouble(ea_lowerlimit));		//件拣下限
			replenishRule.setEa_uplimit(Double.parseDouble(ea_uplimit));			//件拣上限
			replenishRule.setEa_minreplenishqty(Double.parseDouble(ea_minreplenishqty));	//件拣最小补货数
			replenishRule.setCs_lowerlimit(Double.parseDouble(cs_lowerlimit));		//箱拣下限
			replenishRule.setCs_uplimit(Double.parseDouble(cs_uplimit));			//箱拣上限
			replenishRule.setCs_minreplenishqty(Double.parseDouble(cs_minreplenishqty));	//箱拣最小补货数
			replenishRule.setOt_lowerlimit(Double.parseDouble(ot_lowerlimit));		//箱件拣下限
			replenishRule.setOt_uplimit(Double.parseDouble(ot_uplimit));			//箱件拣上限
			replenishRule.setOt_minreplenishqty(Double.parseDouble(ot_minreplenishqty));	//箱件拣最小补货数
			replenishRule.setRemark(remark);				//备注

			replenishRuleBus.addReplenishRule(replenishRule);
			
			Logger.info("用户" + strUserName + "添加了补货规则" + descr);

			strUrl = "/standard/jsp/rule/replenish/rule_replenish_list.jsp";
			
			PagingTool pt = replenishRuleBus.getReplenishRuleQuery("", "", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("规则管理==>补货规则==>增加补货规则失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改补货规则
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
		
		String replenishid = CommonTools.getStrToGbk(request.getParameter("replenishid"));			//补货规则Id
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));			//所属仓库ID
		String descr = CommonTools.getStrToGb2312(request.getParameter("descr"));					//描述
		String ruleconfigid = CommonTools.getStrToGb2312(request.getParameter("ruleconfigid"));		//规则配置ID
		
		String ea_lowerlimit = CommonTools.getStrToGb2312(request.getParameter("ea_lowerlimit"));	//件拣下限
		String ea_uplimit = CommonTools.getStrToGb2312(request.getParameter("ea_uplimit"));			//件拣上限
		String ea_minreplenishqty = CommonTools.getStrToGb2312(request.getParameter("ea_minreplenishqty"));	//件拣最小补货数
		
		String cs_lowerlimit = CommonTools.getStrToGb2312(request.getParameter("cs_lowerlimit"));	//件拣下限
		String cs_uplimit = CommonTools.getStrToGb2312(request.getParameter("cs_uplimit"));			//件拣上限
		String cs_minreplenishqty = CommonTools.getStrToGb2312(request.getParameter("cs_minreplenishqty"));	//件拣最小补货数
		
		String ot_lowerlimit = CommonTools.getStrToGb2312(request.getParameter("ot_lowerlimit"));	//箱件拣下限
		String ot_uplimit = CommonTools.getStrToGb2312(request.getParameter("ot_uplimit"));			//箱件拣上限
		String ot_minreplenishqty = CommonTools.getStrToGb2312(request.getParameter("ot_minreplenishqty"));	//箱件拣最小补货数
		
		String remark = CommonTools.getStrToGb2312(request.getParameter("remark"));	//备注
		
		String strUserName = (String)httpsession.getAttribute("userName");
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        replenishRuleBus = new ReplenishRuleImpl(dao);
		try
		{
			if(replenishid != null && replenishid.length()>0)
			{
				RuleReplenish replenishRule = replenishRuleBus.getReplenishRuleById(replenishid);
				replenishRule.setDescr(descr);					//描述
				replenishRule.setWarehouseid(warehouseid);		//所属仓库ID
				replenishRule.setRuleconfigid(ruleconfigid);	//规则配置ID
				replenishRule.setEa_lowerlimit(Double.parseDouble(ea_lowerlimit));		//件拣下限
				replenishRule.setEa_uplimit(Double.parseDouble(ea_uplimit));			//件拣上限
				replenishRule.setEa_minreplenishqty(Double.parseDouble(ea_minreplenishqty));	//件拣最小补货数
				replenishRule.setCs_lowerlimit(Double.parseDouble(cs_lowerlimit));		//箱拣下限
				replenishRule.setCs_uplimit(Double.parseDouble(cs_uplimit));			//箱拣上限
				replenishRule.setCs_minreplenishqty(Double.parseDouble(cs_minreplenishqty));	//箱拣最小补货数
				replenishRule.setOt_lowerlimit(Double.parseDouble(ot_lowerlimit));		//箱件拣下限
				replenishRule.setOt_uplimit(Double.parseDouble(ot_uplimit));			//箱件拣上限
				replenishRule.setOt_minreplenishqty(Double.parseDouble(ot_minreplenishqty));	//箱件拣最小补货数
				replenishRule.setRemark(remark);				//备注
				
				replenishRuleBus.updateReplenishRule(replenishRule);
				Logger.info("用户" + strUserName + "修改了补货规则" + replenishid);
			}
			
			strUrl = "/standard/jsp/rule/replenish/rule_replenish_list.jsp";
			
			PagingTool pt = replenishRuleBus.getReplenishRuleQuery("", "", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("规则管理==>补货规则==>修改补货规则失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除补货规则
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
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));	//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        replenishRuleBus = new ReplenishRuleImpl(dao);
		try
		{
			if(ids != null && !ids.equals(""))
			{
				String[] id = ids.split(",");
				for(int i=0; i<id.length; i++)
				{
					//删除
					replenishRuleBus.deleteReplenishRule(id[i]);	
					Logger.info("用户" + strUserName + "删除了补货规则" + id[i]);
				}
			}
			
			strUrl = "/standard/jsp/rule/replenish/rule_replenish_list.jsp";
			
			PagingTool pt = replenishRuleBus.getReplenishRuleQuery("", "", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("规则管理==>补货规则==>删除补货规则失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}