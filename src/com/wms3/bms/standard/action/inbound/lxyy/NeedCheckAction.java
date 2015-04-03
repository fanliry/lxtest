package com.wms3.bms.standard.action.inbound.lxyy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.ricosoft.common.tools.CommonTools;
import com.ricosoft.common.tools.StrTools;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.inventory.InventoryNeedcheck;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
/**
 * 描述：需盘点管理
 * @author yao
 *
 */
public class NeedCheckAction extends AbstractAction {
	
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession();
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String strUserCode = (String)request.getSession().getAttribute("userCode");
		String strUserName = (String)request.getSession().getAttribute("userName");
		String errortextString ="";
		try{
			String method = CommonTools.getStrToGb2312(request.getParameter("method"));
			String flag = CommonTools.getStrToGb2312(request.getParameter("flag"));
			String ids = CommonTools.getStrToGb2312(request.getParameter("ids"));
			String warehouseid = CommonTools.getStrToGb2312(request.getParameter("warehouseid"));
			String start_time = CommonTools.getStrToGb2312(request.getParameter("indate_from"));
			String end_time = CommonTools.getStrToGb2312(request.getParameter("indate_to"));
			String is_do = CommonTools.getStrToGb2312(request.getParameter("is_do"));
			
			InventoryNeedcheck ta = new InventoryNeedcheck();
			String strUrl="";
            /*需盘点管理 查询*/
			//每页显示行数
            int iline = 20;
            String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));
            if(linerow != null && linerow.length()>0){
                iline = Integer.parseInt(linerow);
            }
		    if(flag.equals("1")){
		    	errortextString = "入库管理==>生产入库管理==>异常管理==>查询盘点记录:";
				strUrl = "/standard/jsp/inbound/exceptionMgr/inbound_excepton_list.jsp";
				/*查询*/
				String strQueryHQL = ta.getQueryHQL(warehouseid,start_time,end_time,is_do,"1");
                String strCountHQL = ta.getQueryHQLCount(warehouseid,start_time,end_time,is_do,"1");
				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//查询结果
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				Logger.info("用户["+strUserCode+"]，入库管理==>生产入库管理==>异常管理==>查询");
					
			}else if(flag.equals("2")){
				errortextString = "入库管理==>生产入库管理==>异常管理==>查询盘点记录:";
				strUrl = "/standard/jsp/inbound/exceptionMgr/inbound_excepton_list.jsp";
				if(ids!=null && !ids.equals("")){
					String id[] = 	ids.split(",");
					for(int i=0;i<id.length;i++){
						String strQueryHQL = ta.getQueryHQLByid(id[i]);
						List ls =  dao.searchEntities(strQueryHQL,0,1);
						if(ls!=null && ls.size()>0){
							ta = (InventoryNeedcheck)ls.get(0);
							ta.setHandleflag("Y");
							ta.setHandleman(strUserCode);
							ta.setHandletime(StrTools.getCurrDateTime(2));
							ta.setHandlecontent("堆垛机虚报 清空该货位");
							dao.update(ta);
						}
					}
				}
				/*查询*/
				String strQueryHQL = ta.getQueryHQL(warehouseid,start_time,end_time,is_do,"1");
                String strCountHQL = ta.getQueryHQLCount(warehouseid,start_time,end_time,is_do,"1");
				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 0, iline);
                List ls = pt.getLsResult();//查询结果
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				Logger.info("用户["+strUserCode+"]，入库管理==>生产入库管理==>异常管理==>清空货位");
			}else if(flag.equals("3")){
		    	errortextString = "出库管理==>异常管理==>查询盘点记录:";
				strUrl = "/standard/jsp/outbound/exception/outbound_exception_list.jsp";
				/*查询*/
				String strQueryHQL = ta.getQueryHQL(warehouseid,start_time,end_time,is_do,"2");
                String strCountHQL = ta.getQueryHQLCount(warehouseid,start_time,end_time,is_do,"2");
				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//查询结果
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				Logger.info("用户["+strUserCode+"]，出库管理==>异常管理==>查询");
			}else if(flag.equals("4")){
				errortextString = "出库管理==>异常管理==>设置为满货位:";
				strUrl = "/standard/jsp/outbound/exception/outbound_exception_list.jsp";
				if(ids!=null && !ids.equals("")){
					String id[] = 	ids.split(",");
					for(int i=0;i<id.length;i++){
						String strQueryHQL = ta.getQueryHQLByid(id[i]);
						List ls =  dao.searchEntities(strQueryHQL,0,1);
						if(ls!=null && ls.size()>0){
							ta = (InventoryNeedcheck)ls.get(0);
							ta.setHandleflag("Y");
							ta.setHandleman(strUserCode);
							ta.setHandletime(StrTools.getCurrDateTime(2));
							ta.setHandlecontent("堆垛机虚报 设置为满货位");
							BaseCargospace basespace  = new BaseCargospace();
							String str = basespace.getQueryHQLByspaceid(ta.getCargoSpaceId());
							List lsList = dao.searchEntities(str);
							if(lsList!=null && lsList.size()>0){
								basespace  = (BaseCargospace)lsList.get(0);
								basespace.setCsstatus("2");
								dao.update(basespace);
							}
							dao.update(ta);
						}
					}
				}
				/*查询*/
				String strQueryHQL = ta.getQueryHQL(warehouseid,start_time,end_time,is_do,"2");
                String strCountHQL = ta.getQueryHQLCount(warehouseid,start_time,end_time,is_do,"2");
				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 0, iline);
                List ls = pt.getLsResult();//查询结果
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				Logger.info("用户["+strUserCode+"]，出库管理==>异常管理==>设置为满货位");
			}else if(flag.equals("5")){
				errortextString = "库存管理==>出库异常调整==>查询盘点记录失败:";
				strUrl = "/standard/jsp/inventory/adjust/kc_outbound_exception_list.jsp";
				/*查询*/
				String strQueryHQL = ta.getQueryHQL(warehouseid,start_time,end_time,is_do,"2");
                String strCountHQL = ta.getQueryHQLCount(warehouseid,start_time,end_time,is_do,"2");
				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//查询结果
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				Logger.info("用户["+strUserCode+"]，库存管理==>出库异常调整==>查询");
			}else if(flag.equals("6")){
				errortextString = "库存管理==>出库异常调整==>查询盘点记录失败:";
				strUrl = "/standard/jsp/inventory/adjust/kc_inbound_excepton_list.jsp";
				/*查询*/
				String strQueryHQL = ta.getQueryHQL(warehouseid,start_time,end_time,is_do,"1");
                String strCountHQL = ta.getQueryHQLCount(warehouseid,start_time,end_time,is_do,"1");
				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//查询结果
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				Logger.info("用户["+strUserCode+"]，库存管理==>出库异常调整==>查询");
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
		}
		catch(Exception er){
			Logger.error(errortextString+er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}