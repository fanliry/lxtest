package com.wms3.bms.standard.action.inventory;

import java.util.HashMap;
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
import com.wms3.bms.standard.business.base.IProductBus;
import com.wms3.bms.standard.business.base.impl.ProductBusImpl;
import com.wms3.bms.standard.business.inventory.IInventoryBus;
import com.wms3.bms.standard.business.inventory.impl.InventoryBusImpl;
import com.wms3.bms.standard.entity.base.BaseLotSet;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;

/**
 * 描述:物品查询
 * @author fanll
 *
 */
public class InventoryAction extends AbstractAction
{
	protected IInventoryBus inventoryBus;
	protected int maxLine = 20;			//分页显示的行数；
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//仓库ID
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//库区ID
		String Virtualwhid = CommonTools.getStrToGbk(request.getParameter("Virtualwhid"));		//逻辑库区id
		String cargoAlleyId = CommonTools.getStrToGbk(request.getParameter("cargoAlleyId"));//巷道ID
		String platoon = CommonTools.getStrToGbk(request.getParameter("platoon"));			//排
		String column = CommonTools.getStrToGbk(request.getParameter("column"));			//列
		String floor = CommonTools.getStrToGbk(request.getParameter("floor"));				//层
		
		String customer_id = CommonTools.getStrToGbk(request.getParameter("customer_id"));	//货主ID
		String package_id = CommonTools.getStrToGbk(request.getParameter("package_id"));	//品名ID
		String tray_code = CommonTools.getStrToGbk(request.getParameter("tray_code"));		//托盘条码
		
		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from"));	//入库日期from
		String indate_to = CommonTools.getStrToGbk(request.getParameter("indate_to"));		//入库日期to
		
		String total_items = CommonTools.getStrToGbk(request.getParameter("total_items"));  //统计项目
        String lotnumber = CommonTools.getStrToGbk(request.getParameter("lotnumber"));      //批号
        String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));      //进厂编号
        String productid = CommonTools.getStrToGbk(request.getParameter("productid"));      //产品id
        //, , , , 
        String status = CommonTools.getStrToGbk(request.getParameter("status"));            //货位状态
        String kcstatus = CommonTools.getStrToGbk(request.getParameter("kcstatus"));            //货位状态
        String requestid = CommonTools.getStrToGbk(request.getParameter("requestid"));      //申请单号
        String instockid = CommonTools.getStrToGbk(request.getParameter("instockid"));      //入库单号
        String productstatus = CommonTools.getStrToGbk(request.getParameter("productstatus")); //产品状态
        String productcode = CommonTools.getStrToGbk(request.getParameter("productcode"));     //产品编码
        String producttype = CommonTools.getStrToGbk(request.getParameter("producttype"));     //产品类别
       
		
		//String inventoryid = CommonTools.getStrToGb2312(request.getParameter("inventoryid"));   //库存ID
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//每页显示行数
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        
		inventoryBus = new InventoryBusImpl(dao);
		try
		{
			List ls = null;
			String strMsql = new String();
			String[] strSqls = new String[2];
			
			if(flag != null && (flag.equals("1") || flag.equals("2") || flag.equals("11") || flag.equals("12") || flag.equals("21"))){ //库存查询

				if(!flag.equals("12") && !flag.equals("21")){
					strSqls = inventoryBus.getInventoryQuerynew(warehouseid, whAreaId,Virtualwhid, lotinfo, producttype, cargoAlleyId, platoon, 
							column, floor, lotnumber, productid, indate_from, indate_to, tray_code, status, requestid, instockid, productstatus, productcode);
				}else{
					strMsql = inventoryBus.getInventoryWpQuerynew(warehouseid, whAreaId, Virtualwhid, lotinfo, productid, indate_from, indate_to, productstatus);
				}

				if(flag.equals("1")){	//库存查询 查询列表
					strUrl = "/standard/jsp/inventory/search/kc_search_list.jsp";
					PagingTool pt = CommonPagination.getPagingTool(dao, strSqls[1],strSqls[0] , strUrl, 1, maxLine);
					ls = pt.getLsResult();
					request.setAttribute("exResultList", ls);
					httpsession.setAttribute("paging", pt);
					
				}
				else if(flag.equals("12")){	//物品查询 查询列表
					strUrl = "/standard/jsp/inventory/search/wp_search_list.jsp";
					
/*						strSqls = inventoryBus.getInventoryWpQuerynew(warehouseid, whAreaId, Virtualwhid, lotinfo, productid, indate_from, indate_to, productstatus);
						
						PagingTool pt = CommonPagination.getPagingTool(dao, strSqls[1],strSqls[0] , strUrl, 1, maxLine);
						ls = pt.getLsResult();*/
					ls = dao.searchEntities(strMsql);
					request.setAttribute("exResultList", ls);
					//httpsession.setAttribute("paging", pt);
					
				}
				else if(flag.equals("11")){	//库存查询  产品过期预警
					strUrl = "/standard/jsp/inventory/productwarning/product_warning_list.jsp";
					List ls2 = null;
					IProductBus productBus = new ProductBusImpl(dao);
					ls2 = productBus.searchEntitieAll();
					request.setAttribute("exResultList2", ls2);
					
					PagingTool pt = CommonPagination.getPagingTool(dao, strSqls[1],strSqls[0] , strUrl, 1, maxLine);
					ls = pt.getLsResult();
					request.setAttribute("exResultList", ls);
					httpsession.setAttribute("paging", pt);
					
				}else if(flag.equals("2")){	//库存管理 库存查询 报表
					
					strUrl = "/standard/jsp/inventory/search/kc_search_report.jsp";
					
					//PagingTool pt = CommonPagination.getPagingTool(dao, strSqls[1],strSqls[0] , strUrl, 1, maxLine);
					ls = dao.searchEntities(strSqls[0]);
					request.setAttribute("exResultList", ls);
					//httpsession.setAttribute("paging", pt);
				}else if(flag.equals("21")){	//库存管理 物品查询 报表
					
					strUrl = "/standard/jsp/inventory/search/wp_search_report.jsp";
					
					//PagingTool pt = CommonPagination.getPagingTool(dao, strSqls[1],strSqls[0] , strUrl, 1, maxLine);
					ls = dao.searchEntities(strMsql);
					request.setAttribute("exResultList", ls);
					//httpsession.setAttribute("paging", pt);
				}
				
				}else if(flag != null && (flag.equals("3") || flag.equals("4"))){ //库存管理 库存统计
				
				strSqls = inventoryBus.getInventoryTotalQuery(warehouseid, whAreaId, customer_id, package_id, indate_from, indate_to,total_items);
				
				//取得页面列表要显示的项目名
				String list_items = "";
				if(total_items.indexOf("sto.productid") > 0){	//品名
					list_items += "品名,";
	            }
	            if(total_items.indexOf("sto.ownerId") > 0){		//货主
	            	list_items += "货主,";
	            }
	            
			/*	if(hsLot != null){
					lsLot = hsLot.get("kcstatistic");	//库存统计
				}
				*/
				BaseLotSet lotSet = null;
//				for(int i=0; i<lsLot.size(); i++){
//					lotSet = (BaseLotSet)lsLot.get(i);
//					if(total_items.indexOf(lotSet.getLotid()) > 0){
//						list_items += lotSet.getLotname() + ",";
//					}
//				}
				
				if(flag.equals("3")){	//库存管理 库存统计 查询列表
					
					strUrl = "/standard/jsp/inventory/statistic/kc_statistic_list.jsp";
					
					PagingTool pt = CommonPagination.getPagingTool(dao, strSqls[0], strSqls[1], strUrl, 1, maxLine);
					ls = pt.getLsResult();
					
					request.setAttribute("list_items", list_items);
					request.setAttribute("exResultList", ls);
					httpsession.setAttribute("paging", pt);
					
				}else if(flag.equals("4")){	//库存管理 库存查询 报表
					
					strUrl = "/standard/jsp/inventory/statistic/kc_statistic_report.jsp";
					
					ls = dao.searchEntities(strSqls[1]);
					request.setAttribute("list_items", list_items);
					request.setAttribute("exResultList", ls);
				}
			}else if (flag!=null && flag.equals("5")) {
				strUrl = "/standard/jsp/inventory/adjust/kc_search_list.jsp";
				strSqls = inventoryBus.getInventoryQuerynew(warehouseid, whAreaId, cargoAlleyId, platoon, 
						column, floor, lotnumber, productid, indate_from, indate_to, tray_code, status, requestid, instockid, productstatus, productcode,kcstatus);
				
				PagingTool pt = CommonPagination.getPagingTool(dao, strSqls[1],strSqls[0] , strUrl, 1, maxLine);
				ls = pt.getLsResult();
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>库存查询==>库存查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
