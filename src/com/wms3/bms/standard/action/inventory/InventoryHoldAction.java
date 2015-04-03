package com.wms3.bms.standard.action.inventory;

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
import com.wms3.bms.standard.business.inventory.IInventoryHoldBus;
import com.wms3.bms.standard.business.inventory.impl.InventoryHoldBusImpl;

/**
 * 描述:库存冻结/释放
 * @author gui
 *
 */
public class InventoryHoldAction extends AbstractAction {

	protected IInventoryHoldBus inventoryHoldBus;
	protected int maxLine = 20;			//分页显示的行数；
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String cargospaceid = CommonTools.getStrToGbk(request.getParameter("cargospaceid"));//货位ID
		
		String productid = CommonTools.getStrToGbk(request.getParameter("productid"));		//品名ID
		String holdcode = CommonTools.getStrToGbk(request.getParameter("holdcode"));		//冻结原因
		String holdby = CommonTools.getStrToGbk(request.getParameter("holdby"));			//冻结方法
		String dateon_from = CommonTools.getStrToGbk(request.getParameter("dateon_from"));	//冻结日期
		String dateon_to = CommonTools.getStrToGbk(request.getParameter("dateon_to"));		//冻结日期
		String dateoff_from = CommonTools.getStrToGbk(request.getParameter("dateoff_from"));//释放日期
		String dateoff_to = CommonTools.getStrToGbk(request.getParameter("dateoff_to"));	//释放日期
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));		//托盘条码
		String boxcode = CommonTools.getStrToGbk(request.getParameter("boxcode"));			//箱条码
		String productcode = CommonTools.getStrToGbk(request.getParameter("productcode"));	//产品条码
		
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//每页显示行数
     
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        
        inventoryHoldBus = new InventoryHoldBusImpl(dao);
		try{
			
			if(flag != null && flag.equals("1")){ //库存冻结信息查询
				
				strUrl = "/standard/jsp/inventory/hold/kc_hold_list.jsp";
				
				PagingTool pt = inventoryHoldBus.getHoldList(cargospaceid, productid, 
						holdcode, holdby, dateon_from, dateon_to, dateoff_from, dateoff_to, strUrl, maxLine);
				
				List ls = pt.getLsResult();
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				
			}else if(flag != null && flag.equals("2")){ //查询入库库存
				
				strUrl = "/standard/jsp/inventory/hold/kc_inhold_add_list.jsp";
				
				List ls = inventoryHoldBus.getStorageList(productid, cargospaceid, traycode, boxcode, productcode);
				request.setAttribute("exResultList", ls);
			}else if(flag != null && flag.equals("3")){ //查询出库库存
				
				strUrl = "/standard/jsp/inventory/hold/kc_outhold_add_list.jsp";
				
				List ls = inventoryHoldBus.getStorageList(productid, cargospaceid, traycode, boxcode, productcode);
				request.setAttribute("exResultList", ls);
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("库存管理==>冻结/释放==>查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			
		}
		return null;
	}
	
	/**
	 * 功能:生成冻结记录
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

		String holdcode = CommonTools.getStrToGbk(request.getParameter("holdcode"));		//冻结原因
		String holdby = CommonTools.getStrToGbk(request.getParameter("holdby"));			//冻结方法
		String dateoff = CommonTools.getStrToGbk(request.getParameter("dateoff"));			//释放日期
		String holdreason = CommonTools.getStrToGbk(request.getParameter("holdreason"));	//原因描述
		String ids = CommonTools.getStrToGbk(request.getParameter("ids"));					//库存IDS

		String strUserCode = (String)httpsession.getAttribute("userCode");
		inventoryHoldBus = new InventoryHoldBusImpl(dao);
		try{

			String strBackMsg = inventoryHoldBus.addHold(ids, holdcode, holdby, dateoff, holdreason, strUserCode);
	    	
	    	strUrl = "/standard/jsp/inventory/hold/kc_hold_add_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){

			Logger.error("用户["+strUserCode+"]，库存管理==>冻结/释放==>对库存:" + ids + "生成冻结记录失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:释放冻结记录
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

		String ids = CommonTools.getStrToGbk(request.getParameter("ids"));					//库存IDS

		String strUserCode = (String)httpsession.getAttribute("userCode");
		inventoryHoldBus = new InventoryHoldBusImpl(dao);
		try{
			
			String strBackMsg = inventoryHoldBus.updateHold(ids, strUserCode);
	    	
	    	strUrl = "/standard/jsp/inventory/hold/kc_hold_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){

			Logger.error("用户["+strUserCode+"]，库存管理==>冻结/释放==>对库存:" + ids + "生成冻结记录失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
}
