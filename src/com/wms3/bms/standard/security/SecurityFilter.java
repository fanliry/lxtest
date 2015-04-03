package com.wms3.bms.standard.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 描述:物流管理系统
 * @author yao
 *
 */
public class SecurityFilter implements Filter 
{
	private FilterConfig filterConfig = null;
	public void init(FilterConfig config) throws ServletException {
	   this.filterConfig = config;
	}
	public void doFilter(ServletRequest request,ServletResponse response, FilterChain chain)throws IOException, ServletException {
	    HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;
	    HttpSession session = req.getSession();
	    String url = req.getServletPath();
	    if (session.getAttribute("userName") != null || url.contains("/login.jsp") || url.contains("/mesg.jsp")) {
	    	chain.doFilter(request, response);
	    } else {
	    	if((url.contains("/rf/"))){
	    		res.sendRedirect(req.getContextPath() + "/rf/mesg.jsp");
	    	}else{
	    		res.sendRedirect(req.getContextPath() + "/mesg.jsp");
	    	}
	    }
	    return;
	}
	public void destroy() {
	}
}
