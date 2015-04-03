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
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.inventory.IInventoryBus;
import com.wms3.bms.standard.business.inventory.IInventoryCheckBus;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckRequest;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult;
import com.wms3.bms.standard.business.inventory.impl.InventoryCheckBusImpl;
import com.wms3.bms.standard.constant.CommonReturn;

/**
 * 描述:库存盘点
 * @author gui
 *
 */
public class InventoryCheckAction extends AbstractAction {

	protected IInventoryBus inventoryBus;
	protected IInventoryCheckBus inventoryCheckBus;
	protected int maxLine = 20;			//分页显示的行数；
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//仓库ID
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//库区ID
		String type = CommonTools.getStrToGbk(request.getParameter("type"));		//类型
		String status = CommonTools.getStrToGbk(request.getParameter("status"));	//状态
		String lotnumber = CommonTools.getStrToGbk(request.getParameter("lotnumber"));		//类型
		String productid = CommonTools.getStrToGbk(request.getParameter("productid "));	//状态
		String requestid = CommonTools.getStrToGbk(request.getParameter("requestid"));	//盘点申请ID
		String taskid = CommonTools.getStrToGbk(request.getParameter("taskid"));	//盘点任务ID
		
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//每页显示行数
     
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        
        inventoryCheckBus = new InventoryCheckBusImpl(dao);
        
		try{
			
			if(flag != null && flag.equals("1")){ //盘点请求信息查询
				
				strUrl = "/standard/jsp/inventory/check/kc_check_list.jsp";
				
				/*PagingTool pt = inventoryCheckBus.getCheckRequests(warehouseid, whAreaId, type, status,lotnumber,productid, strUrl, maxLine);
				
				List ls = pt.getLsResult();
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);*/
				
			}else if(flag != null && flag.equals("2")){ //盘点任务信息查询
				
				strUrl = "/standard/jsp/inventory/check/kc_check_detail.jsp";
				
				//List ls = inventoryCheckBus.getCheckTasks(requestid);
				//request.setAttribute("exResultList", ls);
				
			}else if(flag != null && flag.equals("3")){ //修改盘点请求，查询盘点请求
				
				strUrl = "/standard/jsp/inventory/check/kc_check_request_update.jsp";
				
				InventoryCheckRequest checkreq = inventoryCheckBus.getCheckReqById(requestid);
				request.setAttribute("checkreq", checkreq); 
				
			}else if(flag != null && flag.equals("4")){ //生成盘点任务，查询盘点请求
				
				strUrl = "/standard/jsp/inventory/check/kc_check_task.jsp";
				
				InventoryCheckRequest checkreq = inventoryCheckBus.getCheckReqById(requestid);
				request.setAttribute("checkreq", checkreq); 
				
			}else if(flag != null && flag.equals("5")){ //生成盘点任务，查询库存列表
				
				strUrl = "/standard/jsp/inventory/check/kc_check_task_list.jsp";
				
				List ls = inventoryCheckBus.queryStorage(requestid);
				request.setAttribute("exResultList", ls);
				
			}else if(flag != null && flag.equals("6")){ //结果, 查询盘点任务
				
				strUrl = "/standard/jsp/inventory/check/kc_check_result_list.jsp";
				
				//List ls = inventoryCheckBus.getCheckTasks(requestid);
				//request.setAttribute("exResultList", ls);
				
			}else if(flag != null && flag.equals("7")){ //结果, 查询盘点结果
				
				strUrl = "/standard/jsp/inventory/check/kc_check_result_detail.jsp";
				
				List ls = inventoryCheckBus.getCheckResults(taskid);
				request.setAttribute("exResultList", ls);
				
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("库存管理==>库存盘点==>查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加盘点申请单
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
		
		String type =  CommonTools.getStrToGbk(request.getParameter("type"));					//类型
		//String priority = CommonTools.getStrToGbk(request.getParameter("priority"));			//优先级
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));		//仓库
		String wh_area_id = CommonTools.getStrToGbk(request.getParameter("wh_area_id"));		//库区
		String cargo_space_id = CommonTools.getStrToGbk(request.getParameter("cargo_space_id"));//货位
        String lotnumber = CommonTools.getStrToGbk(request.getParameter("lotnumber"));		    //批号
        String productid = CommonTools.getStrToGbk(request.getParameter("productid"));			//品名
        String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));			//托盘条码
       // String boxcode = CommonTools.getStrToGbk(request.getParameter("boxcode"));				//箱条码
        String productcode = CommonTools.getStrToGbk(request.getParameter("productcode"));		//产品条码
        //String starttime = CommonTools.getStrToGbk(request.getParameter("starttime"));			//开始时间
        //String endtime = CommonTools.getStrToGbk(request.getParameter("endtime"));				//结束时间
	    

		String strUserCode = (String)httpsession.getAttribute("userCode");
		inventoryCheckBus = new InventoryCheckBusImpl(dao);
		
		try{
			
			String strBackMsg = "Y";
			String strTime =  StrTools.getCurrDateTime(2); 
			
			InventoryCheckRequest checkReq = new InventoryCheckRequest();
			checkReq.setCounttype(type);		//类型
			checkReq.setStatus("1");			//状态
			//checkReq.setPriority(Integer.parseInt(priority));	//优先级
			checkReq.setWarehouseid(warehouseid);	//仓库
			checkReq.setWh_area_id(wh_area_id);		//库区
			checkReq.setCargo_space_id(cargo_space_id);	//货位
			checkReq.setLotinfo(lotnumber);		//批号
			checkReq.setProductid(productid);		//品名
			checkReq.setTraycode(traycode);			//托盘条码
			//checkReq.setBoxcode(boxcode);			//箱条码
			checkReq.setProductcode(productcode);	//产品条码
			checkReq.setRequesttime(strTime);		//申请时间
			checkReq.setCreatemanid(strUserCode);		//创建人
		//	checkReq.setStarttime(starttime);		//开始时间
			//checkReq.setEndtime(endtime);			//结束时间
	    	
	    	inventoryCheckBus.addCheckReq(checkReq);
	    	
	    	strUrl = "/standard/jsp/inventory/check/kc_check_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){

			Logger.error("用户["+strUserCode+"]，库存管理==>库存盘点==>增加盘点申请失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改盘点申请单
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecEdit(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String type =  CommonTools.getStrToGbk(request.getParameter("type"));					//类型
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));		//仓库
		String wh_area_id = CommonTools.getStrToGbk(request.getParameter("wh_area_id"));		//库区
		String cargo_space_id = CommonTools.getStrToGbk(request.getParameter("cargo_space_id"));//货位
        String lotnumber = CommonTools.getStrToGbk(request.getParameter("lotnumber"));		    //批号
        String productid = CommonTools.getStrToGbk(request.getParameter("productid"));			//品名
        String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));			//托盘条码
        String productcode = CommonTools.getStrToGbk(request.getParameter("productcode"));		//产品条码
        String requestid = CommonTools.getStrToGbk(request.getParameter("requestid"));		    //申请单id
        

		String strUserCode = (String)httpsession.getAttribute("userCode");
		inventoryCheckBus = new InventoryCheckBusImpl(dao);
		
		try{
			
			String strBackMsg = "Y";
			
			InventoryCheckRequest checkReq = inventoryCheckBus.getCheckReqById(requestid);
			if(checkReq.getStatus().equals("1")){	
				
				checkReq.setCounttype(type);		//类型
				checkReq.setStatus("1");			//状态
				//checkReq.setWarehouseid(warehouseid);	//仓库
				checkReq.setWh_area_id(wh_area_id);		//库区
				checkReq.setCargo_space_id(cargo_space_id);	//货位
				checkReq.setLotinfo(lotnumber);		//批号
				checkReq.setProductid(productid);		//品名
				checkReq.setTraycode(traycode);			//托盘条码
				checkReq.setProductcode(productcode);	//产品条码
				checkReq.setRequesttime(StrTools.getCurrDateTime(2));		//申请时间
				checkReq.setCreatemanid(strUserCode);		//创建人
		    	
		    	inventoryCheckBus.updateCheckReq(checkReq);
				Logger.info("用户["+strUserCode+"]，库存管理==>库存盘点==>修改了盘点申请单:" + requestid);
				
			}else{
				strBackMsg = "只能修改【新建】状态的申请单！";
			}
	    	
	    	strUrl = "/standard/jsp/inventory/check/kc_check_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){

			Logger.error("用户["+strUserCode+"]，库存管理==>库存盘点==>修改盘点申请单失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除盘点申请单
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecDelete(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String ids = CommonTools.getStrToGbk(request.getParameter("ids"));
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		inventoryCheckBus = new InventoryCheckBusImpl(dao);
		
		try{
			String strBackMsg = "Y";
			InventoryCheckRequest checkReq = null;
			if(ids != null && !ids.equals("")){
			
				String[] id = ids.split(",");
				for(int i=0; i<id.length; i++){
					
					checkReq = inventoryCheckBus.getCheckReqById(id[i]);
					if(checkReq.getStatus().equals("1")){
						
						inventoryCheckBus.deleteCheckReq(id[i]);	
						Logger.info("用户["+strUserCode+"]，删除了盘点申请单" + id[i]);
					}else{
						strBackMsg = "非【新建】状态的申请单无法删除！";
					}
				}
			}
			
			strUrl = "/standard/jsp/inventory/check/kc_check_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("用户["+strUserCode+"]，库存管理==>库存盘点==>删除盘点申请单失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:生成盘点任务
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAddTask(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");

		String requestid = CommonTools.getStrToGbk(request.getParameter("requestid"));	//盘点申请ID
		String ids = CommonTools.getStrToGbk(request.getParameter("ids"));	//库存ID

		String strUserCode = (String)httpsession.getAttribute("userCode");
		inventoryCheckBus = new InventoryCheckBusImpl(dao);
		
		try{
				
			String strBackMsg = inventoryCheckBus.addCheckTasks(requestid, ids);
			if(strBackMsg.equals("Y")){
				Logger.info("用户["+strUserCode+"]，对盘点申请单:" + requestid + "生成了盘点任务");
			}
	    	
	    	strUrl = "/standard/jsp/inventory/check/kc_check_task_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){

			Logger.error("用户["+strUserCode+"]，库存管理==>库存盘点==>对盘点申请单:" + requestid + "生成盘点任务失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:生成盘点结果
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAddResult(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");

		String taskid = CommonTools.getStrToGbk(request.getParameter("taskid"));		//盘点任务ID
		String checknum = CommonTools.getStrToGbk(request.getParameter("checknum"));	//盘点数量

		String strUserCode = (String)httpsession.getAttribute("userCode");
		inventoryCheckBus = new InventoryCheckBusImpl(dao);
		
		try{
				
			CommonReturn cReturn =  inventoryCheckBus.addCheckResult(taskid, checknum,strUserCode);		
			String strBackMsg = cReturn.getRetInfo();
			List lsResult =  cReturn.getLsReturn();
			if(strBackMsg.equals("Y")){
				Logger.info("用户["+strUserCode+"]，对盘点任务:" + taskid + "新增了盘点结果，盘点数量：" + checknum );
			}
	    	
	    	strUrl = "/standard/jsp/inventory/check/kc_check_result_detail.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.setAttribute("exResultList", lsResult);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){

			Logger.error("用户["+strUserCode+"]，库存管理==>库存盘点==>对盘点任务:" + taskid + "新增盘点结果失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改盘点结果
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecEditResult(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");

		String checkid = CommonTools.getStrToGbk(request.getParameter("checkid"));		//盘点结果ID
		String checknum = CommonTools.getStrToGbk(request.getParameter("checknum"));	//盘点数量
		//String checknetweight = CommonTools.getStrToGbk(request.getParameter("checknetweight"));//盘点重量
		String taskid = "";
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		inventoryCheckBus = new InventoryCheckBusImpl(dao);
		
		try{
			
			InventoryCheckResult result = inventoryCheckBus.getCheckResultById(checkid);
			taskid = result.getTaskid();
			result.setChecknum(Double.parseDouble(checknum));
			//result.setChecknetweight(Double.parseDouble(checknetweight));
			String strBackMsg = inventoryCheckBus.updateCheckResult(result);
			if(strBackMsg.equals("Y")){
				Logger.info("用户["+strUserCode+"]，对盘点任务:" + taskid + "修改了盘点结果，盘点数量：" + checknum);
			}
	    	
	    	strUrl = "/standard/jsp/inventory/check/kc_check_result_detail.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){

			Logger.error("用户["+strUserCode+"]，库存管理==>库存盘点==>对盘点任务:" + taskid + "修改盘点结果失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:关闭
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecClose(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");

		String requestid = CommonTools.getStrToGbk(request.getParameter("requestid"));	//盘点申请ID

		String strUserCode = (String)httpsession.getAttribute("userCode");
		inventoryCheckBus = new InventoryCheckBusImpl(dao);
		
		try{
				
			String strBackMsg = inventoryCheckBus.closeCheckTasks(requestid);
			if(strBackMsg.equals("Y")){
				Logger.info("用户["+strUserCode+"]，关闭了盘点申请单:" + requestid);
			}
	    	
	    	strUrl = "/standard/jsp/inventory/check/kc_check_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){

			Logger.error("用户["+strUserCode+"]，库存管理==>库存盘点==>关闭了盘点申请单:" + requestid + "。" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 打印盘点任务
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecPrintTasks(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {

		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");

		String requestid = CommonTools.getStrToGbk(request.getParameter("requestid"));	//盘点申请ID

		String strUserCode = (String)httpsession.getAttribute("userCode");
		inventoryCheckBus = new InventoryCheckBusImpl(dao);
		
		try{
				
			InventoryCheckRequest checkReq = inventoryCheckBus.getCheckReqById(requestid);
			if(checkReq.getCounttype().equals("1")){	//盲盘
				strUrl = "/standard/jsp/inventory/check/kc_check_report1.jsp";
			}else{
				strUrl = "/standard/jsp/inventory/check/kc_check_report.jsp";
			}
	    	
			//List ls = inventoryCheckBus.getCheckTasks(requestid);
			//request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){

			Logger.error("用户["+strUserCode+"]，库存管理==>库存盘点==>打印盘点任务失败(盘点申请单:" + requestid + ")" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
