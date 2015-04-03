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
import com.wms3.bms.standard.business.rule.IPutawayRuleBus;
import com.wms3.bms.standard.business.rule.impl.PutawayRuleImpl;
import com.wms3.bms.standard.entity.rule.RulePutaway;
import com.wms3.bms.standard.entity.rule.RulePutawayDetail;

/**
 * 描述:上架规则管理
 * @author 
 *
 */
public class PutawayRuleAction extends AbstractAction
{
	protected IPutawayRuleBus putawayRuleBus;
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String putawayid = CommonTools.getStrToGbk(request.getParameter("putawayid"));		//上架规则Id
		String putawaydetailid = CommonTools.getStrToGbk(request.getParameter("putawaydetailid"));	//上架规则详细ID
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//所属仓库ID
		String descr = CommonTools.getStrToGbk(request.getParameter("descr"));				//上架规则描述
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));

        putawayRuleBus = new PutawayRuleImpl(dao);
        List ls = null;
		try
		{
			
			if(flag != null && flag.equals("1")) //上架规则管理 查询列表
			{
				strUrl = "/standard/jsp/rule/putaway/rule_putaway_list.jsp";
				
				ls = putawayRuleBus.getPutawayRuleQuery(warehouseid, descr);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2"))//上架规则管理 查询明细列表
			{
				strUrl = "/standard/jsp/rule/putaway/rule_putaway_detail.jsp";
				
				ls = putawayRuleBus.getPutawayDetailRuleById(putawayid);
				request.setAttribute("exResultList", ls);
				
			}else if(flag != null && flag.equals("3"))//上架规则管理 修改规则时获取信息
			{
				strUrl = "/standard/jsp/rule/putaway/rule_putaway_update.jsp";
				
				RulePutaway putawayRule = putawayRuleBus.getPutawayRuleById(putawayid);
				request.setAttribute("putawayRule", putawayRule); 
				
			}else if(flag != null && flag.equals("4"))//上架规则管理 修改明细时获取信息
			{
				strUrl = "/standard/jsp/rule/putaway/rule_putaway_detail_update.jsp";
				
				RulePutawayDetail putawayDetailRule = putawayRuleBus.getPutDetailByDetailId(putawaydetailid);
				request.setAttribute("putawayDetailRule", putawayDetailRule); 
				
				RulePutaway putawayRule = putawayRuleBus.getPutawayRuleById(putawayDetailRule.getPutawayid());
				request.setAttribute("warehouseid", putawayRule.getWarehouseid());
				
			}else if(flag != null && flag.equals("5"))//上架规则管理 修改优先顺位时获取信息
			{
				strUrl = "/standard/jsp/rule/putaway/rule_putaway_sort.jsp";
				
				ls = putawayRuleBus.getPutawayDetailRuleById(putawayid);
				request.setAttribute("exResultList", ls);
				
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("规则管理==>上架规则==>上架规则查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加上架规则
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
		putawayRuleBus = new PutawayRuleImpl(dao);
		try
		{
        	//上架规则
			RulePutaway putawayRule = new RulePutaway();
			putawayRule.setDescr(descr);					//描述
			putawayRule.setWarehouseid(warehouseid);		//所属仓库ID
			putawayRule.setRemark(remark);					//备注

			putawayRuleBus.addPutawayRule(putawayRule);
			
			Logger.info("用户:" + strUserName + "添加了上架规则:" + descr);

			strUrl = "/standard/jsp/rule/putaway/rule_putaway_list.jsp";
			
			List ls = putawayRuleBus.getPutawayRuleQuery("", "");
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("规则管理==>上架规则==>增加上架规则失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改上架规则
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
		
		String putawayid = CommonTools.getStrToGbk(request.getParameter("putawayid"));		//上架规则Id
		String descr = CommonTools.getStrToGb2312(request.getParameter("descr"));			//描述
		String remark = CommonTools.getStrToGb2312(request.getParameter("remark"));			//备注
		
		String strUserName = (String)httpsession.getAttribute("userName");
		putawayRuleBus = new PutawayRuleImpl(dao);
		try
		{
			if(putawayid != null && putawayid.length()>0)
			{
				RulePutaway putawayRule = putawayRuleBus.getPutawayRuleById(putawayid);
				putawayRule.setDescr(descr);				//描述
				putawayRule.setRemark(remark);				//备注
				
				putawayRuleBus.updatePutawayRule(putawayRule);
				Logger.info("用户:" + strUserName + "修改了上架规则:" + putawayid);
			}
			
			strUrl = "/standard/jsp/rule/putaway/rule_putaway_list.jsp";
			
			List ls = putawayRuleBus.getPutawayRuleQuery("", "");
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("规则管理==>上架规则==>修改上架规则失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除上架规则
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
		putawayRuleBus = new PutawayRuleImpl(dao);
		try
		{
			if(ids != null && !ids.equals(""))
			{
				String[] id = ids.split(",");
				for(int i=0; i<id.length; i++)
				{	
					putawayRuleBus.deletePutawayRule(id[i]);
					Logger.info("用户:" + strUserName + "删除了上架规则:" + id[i]);
				}
			}
			
			strUrl = "/standard/jsp/rule/putaway/rule_putaway_list.jsp";
			
			List ls = putawayRuleBus.getPutawayRuleQuery("", "");
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("规则管理==>上架规则==>删除上架规则失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加上架规则详细
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
		
	    String putawayid = CommonTools.getStrToGbk(request.getParameter("putawayid"));  		//上架规则ID
	    String sort = CommonTools.getStrToGbk(request.getParameter("sort"));					//优先顺位
		String ruleconfigid = CommonTools.getStrToGbk(request.getParameter("ruleconfigid"));	//规则配置ID
	    String enableflag = CommonTools.getStrToGbk(request.getParameter("enableflag"));		//是否生效
	    
	    String tozone = CommonTools.getStrToGbk(request.getParameter("tozone"));				//目标库区
	    String tolocationid = CommonTools.getStrToGbk(request.getParameter("tolocationid"));	//目标库位
	    
	    String loc_restrict = CommonTools.getStrToGbk(request.getParameter("loc_restrict"));	//库位限制
	    
	    String loc_usage1 = CommonTools.getStrToGbk(request.getParameter("loc_usage1"));   			//库位使用1
	    String loc_usage2 = CommonTools.getStrToGbk(request.getParameter("loc_usage2"));   			//库位使用2
	    String loc_usage3 = CommonTools.getStrToGbk(request.getParameter("loc_usage3"));   			//库位使用3
	    String loc_usage4 = CommonTools.getStrToGbk(request.getParameter("loc_usage4"));   			//库位使用4
	    String loc_usage5 = CommonTools.getStrToGbk(request.getParameter("loc_usage5"));   			//库位使用5
	    
	    String loc_handle1 = CommonTools.getStrToGbk(request.getParameter("loc_handle1"));   		//存储类型1
	    String loc_handle2 = CommonTools.getStrToGbk(request.getParameter("loc_handle2"));   		//存储类型2
	    String loc_handle3 = CommonTools.getStrToGbk(request.getParameter("loc_handle3"));   		//存储类型3
	    String loc_handle4 = CommonTools.getStrToGbk(request.getParameter("loc_handle4"));   		//存储类型4
	    String loc_handle5 = CommonTools.getStrToGbk(request.getParameter("loc_handle5"));   		//存储类型5
	    
	    String lotid = CommonTools.getStrToGbk(request.getParameter("lotid"));			//批次ID
	    String lotatt1 = CommonTools.getStrToGbk(request.getParameter("lotatt1"));		//批次属性值1
	    String lotatt2 = CommonTools.getStrToGbk(request.getParameter("lotatt2"));		//批次属性值2
	    String lotatt3 = CommonTools.getStrToGbk(request.getParameter("lotatt3"));		//批次属性值3
	    String lotatt4 = CommonTools.getStrToGbk(request.getParameter("lotatt4"));		//批次属性值4
	    String lotatt5 = CommonTools.getStrToGbk(request.getParameter("lotatt5"));		//批次属性值5
	    String lotatt6 = CommonTools.getStrToGbk(request.getParameter("lotatt6"));		//批次属性值6
	    String lotatt7 = CommonTools.getStrToGbk(request.getParameter("lotatt7"));		//批次属性值7
	    String lotatt8 = CommonTools.getStrToGbk(request.getParameter("lotatt8"));		//批次属性值8
	    String lotatt9 = CommonTools.getStrToGbk(request.getParameter("lotatt9"));		//批次属性值9
	    String lotatt10 = CommonTools.getStrToGbk(request.getParameter("lotatt10"));	//批次属性值10
	    String lotatt11 = CommonTools.getStrToGbk(request.getParameter("lotatt11"));	//批次属性值11
	    String lotatt12 = CommonTools.getStrToGbk(request.getParameter("lotatt12"));	//批次属性值12
	    
	    String remark = CommonTools.getStrToGbk(request.getParameter("remark"));    	//备注
		
		String strUserName = (String)httpsession.getAttribute("userName");
		putawayRuleBus = new PutawayRuleImpl(dao);
		try
		{
        	//上架规则详细
			RulePutawayDetail putawayDetailRule = new RulePutawayDetail();
			putawayDetailRule.setPutawayid(putawayid) ;  			//上架规则ID
			putawayDetailRule.setSort(Integer.parseInt(sort));		//优先顺位
			putawayDetailRule.setRuleconfigid(ruleconfigid);		//规则配置ID
			putawayDetailRule.setEnableflag(enableflag);			//是否生效
		    
			putawayDetailRule.setTozone(tozone);					//目标库区
			putawayDetailRule.setTolocationid(tolocationid);		//目标库位
		    
			putawayDetailRule.setLoc_restrict(loc_restrict);    	//库位限制
		    
			putawayDetailRule.setLoc_usage1(loc_usage1);   			//库位使用1
			putawayDetailRule.setLoc_usage2(loc_usage2);   			//库位使用2
			putawayDetailRule.setLoc_usage3(loc_usage3);   			//库位使用3
			putawayDetailRule.setLoc_usage4(loc_usage4);   			//库位使用4
			putawayDetailRule.setLoc_usage5(loc_usage5);   			//库位使用5
		    
			putawayDetailRule.setLoc_handle1(loc_handle1);   		//存储类型1
			putawayDetailRule.setLoc_handle2(loc_handle2);   		//存储类型2
			putawayDetailRule.setLoc_handle3(loc_handle3);   		//存储类型3
			putawayDetailRule.setLoc_handle4(loc_handle4);   		//存储类型4
			putawayDetailRule.setLoc_handle5(loc_handle5);   		//存储类型5

		    putawayDetailRule.setLotid(lotid);			//批次属性值1	
	    	putawayDetailRule.setLotatt1(lotatt1);		//批次属性值1
	    	putawayDetailRule.setLotatt2(lotatt2);		//批次属性值2
	    	putawayDetailRule.setLotatt3(lotatt3);		//批次属性值3
	    	putawayDetailRule.setLotatt4(lotatt4);		//批次属性值4
	    	putawayDetailRule.setLotatt5(lotatt5);		//批次属性值5
	    	putawayDetailRule.setLotatt6(lotatt6);		//批次属性值6
	    	putawayDetailRule.setLotatt7(lotatt7);		//批次属性值7
	    	putawayDetailRule.setLotatt8(lotatt8);		//批次属性值8
	    	putawayDetailRule.setLotatt9(lotatt9);		//批次属性值9
	    	putawayDetailRule.setLotatt10(lotatt10);	//批次属性值10
	    	putawayDetailRule.setLotatt11(lotatt11);	//批次属性值11
	    	putawayDetailRule.setLotatt12(lotatt12);	//批次属性值12
		    
		    putawayDetailRule.setRemark(remark);    	//备注

			putawayRuleBus.addPutawayDetailRule(putawayDetailRule);
			
			Logger.info("用户:" + strUserName + "添加了上架规则:" + putawayid + "的详细");

			strUrl = "/standard/jsp/rule/putaway/rule_putaway_detail.jsp";
			
			List ls = putawayRuleBus.getPutawayDetailRuleById(putawayid);
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("规则管理==>上架规则==>增加上架规则详细失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改上架规则详细
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
		
	    String putawayid = CommonTools.getStrToGbk(request.getParameter("putawayid"));  		//上架规则ID
	    String putawaydetailid = CommonTools.getStrToGbk(request.getParameter("putawaydetailid"));	//上架规则详细ID
		String ruleconfigid = CommonTools.getStrToGbk(request.getParameter("ruleconfigid"));	//规则配置ID
	    String enableflag = CommonTools.getStrToGbk(request.getParameter("enableflag"));		//是否生效
	    
	    String tozone = CommonTools.getStrToGbk(request.getParameter("tozone"));				//目标库区
	    String tolocationid = CommonTools.getStrToGbk(request.getParameter("tolocationid"));	//目标库位
	    
	    String loc_restrict = CommonTools.getStrToGbk(request.getParameter("loc_restrict"));	//库位限制
	    
	    String loc_usage1 = CommonTools.getStrToGbk(request.getParameter("loc_usage1"));   			//库位使用1
	    String loc_usage2 = CommonTools.getStrToGbk(request.getParameter("loc_usage2"));   			//库位使用2
	    String loc_usage3 = CommonTools.getStrToGbk(request.getParameter("loc_usage3"));   			//库位使用3
	    String loc_usage4 = CommonTools.getStrToGbk(request.getParameter("loc_usage4"));   			//库位使用4
	    String loc_usage5 = CommonTools.getStrToGbk(request.getParameter("loc_usage5"));   			//库位使用5
	    
	    String loc_handle1 = CommonTools.getStrToGbk(request.getParameter("loc_handle1"));   		//存储类型1
	    String loc_handle2 = CommonTools.getStrToGbk(request.getParameter("loc_handle2"));   		//存储类型2
	    String loc_handle3 = CommonTools.getStrToGbk(request.getParameter("loc_handle3"));   		//存储类型3
	    String loc_handle4 = CommonTools.getStrToGbk(request.getParameter("loc_handle4"));   		//存储类型4
	    String loc_handle5 = CommonTools.getStrToGbk(request.getParameter("loc_handle5"));   		//存储类型5
	    
	    String lotid = CommonTools.getStrToGbk(request.getParameter("lotid"));			//批次ID
	    String lotatt1 = CommonTools.getStrToGbk(request.getParameter("lotatt1"));		//批次属性值1
	    String lotatt2 = CommonTools.getStrToGbk(request.getParameter("lotatt2"));		//批次属性值2
	    String lotatt3 = CommonTools.getStrToGbk(request.getParameter("lotatt3"));		//批次属性值3
	    String lotatt4 = CommonTools.getStrToGbk(request.getParameter("lotatt4"));		//批次属性值4
	    String lotatt5 = CommonTools.getStrToGbk(request.getParameter("lotatt5"));		//批次属性值5
	    String lotatt6 = CommonTools.getStrToGbk(request.getParameter("lotatt6"));		//批次属性值6
	    String lotatt7 = CommonTools.getStrToGbk(request.getParameter("lotatt7"));		//批次属性值7
	    String lotatt8 = CommonTools.getStrToGbk(request.getParameter("lotatt8"));		//批次属性值8
	    String lotatt9 = CommonTools.getStrToGbk(request.getParameter("lotatt9"));		//批次属性值9
	    String lotatt10 = CommonTools.getStrToGbk(request.getParameter("lotatt10"));	//批次属性值10
	    String lotatt11 = CommonTools.getStrToGbk(request.getParameter("lotatt11"));	//批次属性值11
	    String lotatt12 = CommonTools.getStrToGbk(request.getParameter("lotatt12"));	//批次属性值12
	    
	    String remark = CommonTools.getStrToGbk(request.getParameter("remark"));    	//备注
		
		String strUserName = (String)httpsession.getAttribute("userName");
		putawayRuleBus = new PutawayRuleImpl(dao);
		try
		{
			RulePutawayDetail putawayDetailRule = putawayRuleBus.getPutDetailByDetailId(putawaydetailid);
			putawayDetailRule.setRuleconfigid(ruleconfigid);		//规则配置ID
			putawayDetailRule.setEnableflag(enableflag);			//是否生效
		    
			putawayDetailRule.setTozone(tozone);					//目标库区
			putawayDetailRule.setTolocationid(tolocationid);		//目标库位
		    
			putawayDetailRule.setLoc_restrict(loc_restrict);    	//库位限制
		    
			putawayDetailRule.setLoc_usage1(loc_usage1);   			//库位使用1
			putawayDetailRule.setLoc_usage2(loc_usage2);   			//库位使用2
			putawayDetailRule.setLoc_usage3(loc_usage3);   			//库位使用3
			putawayDetailRule.setLoc_usage4(loc_usage4);   			//库位使用4
			putawayDetailRule.setLoc_usage5(loc_usage5);   			//库位使用5
		    
			putawayDetailRule.setLoc_handle1(loc_handle1);   		//存储类型1
			putawayDetailRule.setLoc_handle2(loc_handle2);   		//存储类型2
			putawayDetailRule.setLoc_handle3(loc_handle3);   		//存储类型3
			putawayDetailRule.setLoc_handle4(loc_handle4);   		//存储类型4
			putawayDetailRule.setLoc_handle5(loc_handle5);   		//存储类型5
		    
		    putawayDetailRule.setLotid(lotid);			//批次属性值1	
	    	putawayDetailRule.setLotatt1(lotatt1);		//批次属性值1
	    	putawayDetailRule.setLotatt2(lotatt2);		//批次属性值2
	    	putawayDetailRule.setLotatt3(lotatt3);		//批次属性值3
	    	putawayDetailRule.setLotatt4(lotatt4);		//批次属性值4
	    	putawayDetailRule.setLotatt5(lotatt5);		//批次属性值5
	    	putawayDetailRule.setLotatt6(lotatt6);		//批次属性值6
	    	putawayDetailRule.setLotatt7(lotatt7);		//批次属性值7
	    	putawayDetailRule.setLotatt8(lotatt8);		//批次属性值8
	    	putawayDetailRule.setLotatt9(lotatt9);		//批次属性值9
	    	putawayDetailRule.setLotatt10(lotatt10);	//批次属性值10
	    	putawayDetailRule.setLotatt11(lotatt11);	//批次属性值11
	    	putawayDetailRule.setLotatt12(lotatt12);	//批次属性值12
			
		    putawayDetailRule.setRemark(remark);    	//备注
		    
		    putawayRuleBus.updatePutawayDetailRule(putawayDetailRule);
			
			Logger.info("用户:" + strUserName + "修改了上架规则详细:" + putawaydetailid);

			strUrl = "/standard/jsp/rule/putaway/rule_putaway_detail.jsp";
			
			List ls = putawayRuleBus.getPutawayDetailRuleById(putawayid);
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("规则管理==>上架规则==>修改上架规则详细失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除上架规则详细
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
		putawayRuleBus = new PutawayRuleImpl(dao);
		try
		{
			String putawayid = "";	 //上架规则ID
			if(ids != null && !ids.equals(""))
			{
				String[] id = ids.split(",");
				for(int i=0; i<id.length; i++){
				
					String[] arr = id[i].split("\\|");		//规则详细ID +"|" + 上架规则
					putawayid = arr[1];
					
					putawayRuleBus.deletePutawayDetailRule(arr[0]);
					Logger.info("用户:" + strUserName + "删除了上架规则详细:" + arr[0] + "和批次属性值:" + arr[1]);
				}
			}
			
			strUrl = "/standard/jsp/rule/putaway/rule_putaway_detail.jsp";
			
			List ls = putawayRuleBus.getPutawayDetailRuleById(putawayid);
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("规则管理==>上架规则==>删除上架规则详细失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改上架规则详细的优先顺位
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
		
		String putawayid = CommonTools.getStrToGbk(request.getParameter("putawayid"));  //上架规则ID
		String detailids = CommonTools.getStrToGbk(request.getParameter("detailids"));	//上架规则详细ID
		String sorts = CommonTools.getStrToGbk(request.getParameter("sorts"));			//优先顺位
		String strUserName = (String)httpsession.getAttribute("userName");
		putawayRuleBus = new PutawayRuleImpl(dao);
		try
		{
			putawayRuleBus.updatePutawayDetailRuleSorts(detailids, sorts);
			Logger.info("用户:" + strUserName + "修改上架规则" + putawayid +"的优先顺位");

			
			strUrl = "/standard/jsp/rule/putaway/rule_putaway_detail.jsp";
			
			List ls = putawayRuleBus.getPutawayDetailRuleById(putawayid);
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("规则管理==>上架规则==>修改上架规则详细的优先顺位失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}