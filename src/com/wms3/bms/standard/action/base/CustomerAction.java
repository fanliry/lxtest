package com.wms3.bms.standard.action.base;

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
import com.wms3.bms.standard.business.base.ICustomerBus;
import com.wms3.bms.standard.business.base.impl.CustomerBusImpl;
import com.wms3.bms.standard.entity.base.BaseCustomer;

/**
 * 描述:客户管理
 * @author gui
 *
 */
public class CustomerAction extends AbstractAction
{
	protected ICustomerBus customerBus;
	protected int maxLine = 20;		//分页显示的行数；
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String customername = CommonTools.getStrToGbk(request.getParameter("customername"));	//客户名称
		String customertype = CommonTools.getStrToGbk(request.getParameter("customertype"));	//客户分类
		String id = CommonTools.getStrToGbk(request.getParameter("id"));						//客户Id
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
			
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));				//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		customerBus = new CustomerBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //客户管理 查询列表
			{
				strUrl = "/standard/jsp/base/customer/base_customer_list.jsp";
				
				PagingTool pt = customerBus.getCustomerQuery(customername, customertype, strUrl, maxLine);
				List ls = pt.getLsResult();
				
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
					
			}else if(flag != null && flag.equals("2"))//客户管理 修改时获取信息
			{
				strUrl = "/standard/jsp/base/customer/base_customer_update.jsp";
				
				BaseCustomer customer = customerBus.getCustomerById(id);
				request.setAttribute("customer", customer); 
				
			}else if(flag != null && flag.equals("3")) //选择客户 查询列表
			{
				strUrl = "/standard/jsp/common/common_customer_list.jsp";
				
				PagingTool pt = customerBus.getCustomerQuery(customername, customertype, strUrl, maxLine);
				List ls = pt.getLsResult();
				
				request.setAttribute("exResultList", ls);
				request.setAttribute("pagingTool", pt);
				httpsession.setAttribute("commpaging", pt);
					
			}else if(flag != null && flag.equals("4")) //选择rf供应商查询列表
			{
				strUrl = "/standard/jsp/common/common_customer_rf_list.jsp";
				List<BaseCustomer> ls = customerBus.getCustomerQueryRF(customername, customertype);
				request.setAttribute("exResultList", ls);
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>客户管理==>客户查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加客户
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
		
		String shortname = CommonTools.getStrToGbk(request.getParameter("shortname"));		//客户简称
		String customername = CommonTools.getStrToGbk(request.getParameter("customername"));//客户全称
		String customertype = CommonTools.getStrToGbk(request.getParameter("customertype"));//客户分类
		String contact = CommonTools.getStrToGbk(request.getParameter("contact"));			//联系人
		String phone = CommonTools.getStrToGbk(request.getParameter("phone"));				//联系电话
		String fax = CommonTools.getStrToGbk(request.getParameter("fax"));					//传真
		String address = CommonTools.getStrToGbk(request.getParameter("address"));			//地址
		String bank = CommonTools.getStrToGbk(request.getParameter("bank"));				//开户银行
	    String putawayid = CommonTools.getStrToGbk(request.getParameter("putawayid"));		//上架规则ID
	    String allocationid = CommonTools.getStrToGbk(request.getParameter("allocationid"));//分配规则ID
	    String replenishid = CommonTools.getStrToGbk(request.getParameter("replenishid"));	//补货规则ID
		String linenumber = CommonTools.getStrToGbk(request.getParameter("linenumber"));	//线路号
		String pakageid = CommonTools.getStrToGbk(request.getParameter("pakageid"));		//包装ID
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		customerBus = new CustomerBusImpl(dao);
		try
		{
        	//新增客户信息
			BaseCustomer customer = new BaseCustomer();
			customer.setShortname(shortname);		// 客户简称
			customer.setCustomername(customername);	// 客户全称
			customer.setCustomertype(customertype);	// 客户分类
			customer.setContact(contact);			// 联系人
			customer.setPhone(phone);				// 联系电话
			customer.setFax(fax);					// 传真
			customer.setAddress(address);			// 地址
			customer.setBank(bank);					// 开户银行
			customer.setCreatetime(currentTime);	// 创建时间
			customer.setCreatemanid(strUserCode);	// 创建人
			customer.setUpdatetime(currentTime);	// 最后修改时间
			customer.setUpdatemanid(strUserCode);	// 最后修改人
			customer.setUseflag("Y");				// 是否启用
			customer.setPutawayid(putawayid);		// 上架规则ID
			customer.setAllocationid(allocationid);	// 分配规则ID
			customer.setReplenishid(replenishid);	// 补货规则ID
			customer.setLinenumber(linenumber);		// 线路号
			customer.setPakageid(pakageid);			// 包装ID
			customerBus.addCustomer(customer);
			
			Logger.info("用户" + strUserName + "添加了客户" + customername);

	        strUrl = "/standard/jsp/base/customer/base_customer_list.jsp";
	        PagingTool pt = customerBus.getCustomerQuery("", "", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>客户管理==>增加客户失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改客户
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
		
		String id = CommonTools.getStrToGbk(request.getParameter("id"));					//客户Id
		String shortname = CommonTools.getStrToGbk(request.getParameter("shortname"));		//客户简称
		String customername = CommonTools.getStrToGbk(request.getParameter("customername"));//客户全称
		String customertype = CommonTools.getStrToGbk(request.getParameter("customertype"));//客户分类
		String contact = CommonTools.getStrToGbk(request.getParameter("contact"));			//联系人
		String phone = CommonTools.getStrToGbk(request.getParameter("phone"));				//联系电话
		String fax = CommonTools.getStrToGbk(request.getParameter("fax"));					//传真
		String address = CommonTools.getStrToGbk(request.getParameter("address"));			//地址
		String bank = CommonTools.getStrToGbk(request.getParameter("bank"));				//开户银行
	    String putawayid = CommonTools.getStrToGbk(request.getParameter("putawayid"));		//上架规则ID
	    String allocationid = CommonTools.getStrToGbk(request.getParameter("allocationid"));//分配规则ID
	    String replenishid = CommonTools.getStrToGbk(request.getParameter("replenishid"));	//补货规则ID
		String linenumber = CommonTools.getStrToGbk(request.getParameter("linenumber"));	//线路号
		String pakageid = CommonTools.getStrToGbk(request.getParameter("pakageid"));		//包装ID
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		customerBus = new CustomerBusImpl(dao);
		try
		{
			if(id != null && id.length()>0)
			{
				BaseCustomer customer = customerBus.getCustomerById(id);
				customer.setShortname(shortname);		//客户简称
				customer.setCustomername(customername);	//客户全称
				customer.setCustomertype(customertype);	//客户分类
				customer.setContact(contact);			//联系人
				customer.setPhone(phone);				//联系电话
				customer.setFax(fax);					//传真
				customer.setAddress(address);			//地址
				customer.setBank(bank);					//开户银行
				customer.setPutawayid(putawayid);		// 上架规则ID
				customer.setAllocationid(allocationid);	// 分配规则ID
				customer.setReplenishid(replenishid);	// 补货规则ID
				customer.setUpdatetime(currentTime);	//最后修改时间
				customer.setUpdatemanid(strUserCode);	//最后修改人
				customer.setLinenumber(linenumber);		//线路号
				customer.setPakageid(pakageid);			// 包装ID
				customerBus.updateCustomer(customer);
				Logger.info("用户" + strUserName + "修改了客户" + id);
			}
			
			strUrl = "/standard/jsp/base/customer/base_customer_list.jsp";
			PagingTool pt = customerBus.getCustomerQuery("", "", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>客户管理==>修改客户失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除客户
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
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		customerBus = new CustomerBusImpl(dao);
		try
		{
			if(ids != null && !ids.equals(""))
			{
				String[] id = ids.split(",");
				for(int i=0; i<id.length; i++)
				{
					//删除
					customerBus.deleteCustomer(id[i]);	
					Logger.info("用户" + strUserName + "删除了客户" + id[i]);
				}
			}
			
			strUrl = "/standard/jsp/base/customer/base_customer_list.jsp";
			PagingTool pt = customerBus.getCustomerQuery("", "", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("基本信息==>客户管理==>客户删除失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
}