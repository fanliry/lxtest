package com.wms3.bms.standard.action.inventory.lxyy;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Transaction;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.ricosoft.common.tools.CommonTools;
import com.ricosoft.common.tools.StrTools;
import com.ricosoft.entity.competenceMgr.SystemLogInfo;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.base.ICargoSpaceBus;
import com.wms3.bms.standard.business.base.IWhAreaBus;
import com.wms3.bms.standard.business.base.impl.CargoSpaceBusImpl;
import com.wms3.bms.standard.business.base.impl.WhAreaBusImpl;
import com.wms3.bms.standard.business.inventory.IInventoryCheckResultBus;
import com.wms3.bms.standard.business.inventory.IInventoryNeedCheckBus;
import com.wms3.bms.standard.business.inventory.InventoryAdjustBus;
import com.wms3.bms.standard.business.inventory.impl.InventoryAdjustBusImpl;
import com.wms3.bms.standard.business.inventory.impl.InventoryCheckResultBusImpl;
import com.wms3.bms.standard.business.inventory.impl.InventoryNeedCheckBusImp;
import com.wms3.bms.standard.constant.StandardConstant;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BaseWharea;
import com.wms3.bms.standard.entity.inventory.InventoryNeedcheck;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryAdjustDetail;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryAdjustHeader;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;



/**
 *  描述:库存调整单
 * @author yao
 *
 */
public class InventoryAdjustAction extends AbstractAction
{
	protected InventoryAdjustBus inventoryadjustbus;
	protected int maxLine = 5;			//分页显示的行数；
	/**
	 * 查询库存调整单
	 */
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		//状态
		String strStatus = CommonTools.getStrToGb2312(request.getParameter("status"));
		//仓库id
		String strWarehouseid = CommonTools.getStrToGb2312(request.getParameter("warehouseid"));
		//调整类型
		String strAdjusttype = CommonTools.getStrToGb2312(request.getParameter("adjusttype"));
		//调整原因
		String strReasoncode = CommonTools.getStrToGb2312(request.getParameter("reasoncode"));
		inventoryadjustbus = new InventoryAdjustBusImpl(dao);
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
		if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }

		try
		{	
			List ls = null;
			//查询的SQL语句
			String strQuerySQL = inventoryadjustbus.getAdjustHeaderSQL(strWarehouseid,strStatus,strAdjusttype,strReasoncode);
			//查询角色总记录数的SQL语句
			String strCountSQL = "select count(ah) "+strQuerySQL;
			String strUrl = "/standard/jsp/inventory/adjust/kc_adjustheader_list.jsp";	
			PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, strUrl ,1, maxLine);
			//查询结果
			ls = pt.getLsResult();
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);	

			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>库存调整==>库存调整单查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * 功能:库存调整单详细查询
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		
		//id
		String strId = CommonTools.getStrToGb2312(request.getParameter("id"));
		InventoryAdjustBusImpl inventoryadjustbus1 = new InventoryAdjustBusImpl(dao);
		
		try
		{	
			List ls = null;
			//查询的SQL语句
			String strQuerySQL = inventoryadjustbus1.getAdjustDetailSQL(strId);
			//查询角色总记录数的SQL语句
			String strCountSQL = inventoryadjustbus1.getAdjustDetailCountSQL(strId);

			String strUrl = "/standard/jsp/inventory/adjust/kc_adjustdetail_list.jsp";
			
			PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, strUrl ,1, maxLine);
			//查询结果
			ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("inboundPage1", pt);	

			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>库存调整==>库存调整单详细查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * 功能:增加库存调整单
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdd(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		
		//状态
		String strStatus = CommonTools.getStrToGb2312(request.getParameter("status"));
		//仓库id
		String strWarehouseid = CommonTools.getStrToGb2312(request.getParameter("warehouseid"));
		//调整类型
		String strAdjusttype = CommonTools.getStrToGb2312(request.getParameter("adjusttype"));
		//调整原因
		String strReasoncode = CommonTools.getStrToGb2312(request.getParameter("reasoncode"));
		//原因
		String strReasondiscr = CommonTools.getStrToGb2312(request.getParameter("reasondiscr"));
		//用户
		String strUserCode = request.getSession().getAttribute("userCode").toString();
	
		try
		{
			
			InventoryAdjustHeader adjust = new InventoryAdjustHeader();
			//调整单号
            String strInvoiceNo = SeqTool.getID("TZ", dao);     
			adjust.setWarehouseid(strWarehouseid);
			adjust.setStatus(strStatus);
			adjust.setAdjusttype(strAdjusttype);
			adjust.setReasoncode(strReasoncode);
			adjust.setReasondiscr(strReasondiscr);
			adjust.setAdjustid(strInvoiceNo);
			adjust.setCreatemanid(strUserCode);
			adjust.setCreatetime(StrTools.getCurrDateTime(5));
			dao.save(adjust);
			
			//日志
			SystemLogInfo sysLog = new SystemLogInfo();
			String strUserName = request.getSession().getAttribute("userName").toString();
			sysLog.setM_strLogCode(strUserCode);
			sysLog.setM_strLogName(strUserName);
			sysLog.setM_strModuleName("库存管理=>库存调整");
			sysLog.setM_strContent("添加库存调整单成功");
			sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
			dao.save(sysLog);
		
			//刷新 
			PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
			List ls = null;
			String strUrl = null;
			if(pt != null)
			{
				//更新总记录数
				int rows = pt.getM_iCountRow()+1;
				pt.setPagingParameter(rows, 1, pt.getM_iMaxRow());
				ls = CommonPagination.getPagingList(dao, pt);
				strUrl = pt.getM_strUrl();
			}
			if(strUrl == null)
			{
				strUrl = "/standard/jsp/inventory/adjust/kc_adjustheader_list.jsp";
			}
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>库存调整==>库存调整单增加失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * 功能:删除库存调整单
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecDelete(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		try
		{

			//代码(多个用逗号分隔)
			String strDeleteStr = CommonTools.getStrToGb2312(request.getParameter("deleteStr"));
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			ICargoSpaceBus csBus = new CargoSpaceBusImpl(dao);
			PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
			List ls = null;
			String strUrl = null;
			if(strDeleteStr != null && strDeleteStr.length() > 0)
			{
				String [] roleIds = strDeleteStr.split(",");
				InventoryAdjustHeader adjust=null;
				for(int i=0; i<roleIds.length; i++)
				{
					String strTemp =  roleIds[i];
					//删除
					adjust=inventoryadjustbus2.getAdjustHeaderToId(strTemp);
					if(adjust!=null && adjust.getStatus().equals("0")){ // 库存调整单为创建状态
						List<InventoryAdjustDetail> lsDetail = inventoryadjustbus2.getAdjustDetailListToId(strTemp);
						List<InventoryStorage> lsinventory = new ArrayList<InventoryStorage>();
						List<BaseCargospace> lscargospace = new ArrayList<BaseCargospace>();
						if(lsDetail != null)
						{
							for(int j = 0; j < lsDetail.size(); j++)
							{
								    InventoryAdjustDetail detail = (InventoryAdjustDetail)lsDetail.get(j);
								    if (detail.getType().equals("2")) {
										BaseCargospace cargospace = csBus.getCargoSpaceById(detail.getCargo_space_id());
										cargospace.setCsstatus("1");
										lscargospace.add(cargospace);
										//dao.update(cargospace);
									}
									//库存
									InventoryStorage inventory = inventoryadjustbus2.getInventoryInfoToId(detail.getInventoryid());
									if(inventory != null)
									{
										inventory.setStatus("0");//未分配
									}
									lsinventory.add(inventory);
							}
						}
						
						inventoryadjustbus2.deletejustInvoice(lscargospace,lsDetail, lsinventory, adjust);
						//日志
						SystemLogInfo sysLog = new SystemLogInfo();
						String strUserCode = request.getSession().getAttribute("userCode").toString();
						String strUserName = request.getSession().getAttribute("userName").toString();
						sysLog.setM_strLogCode(strUserCode);
						sysLog.setM_strLogName(strUserName);
						sysLog.setM_strModuleName("库存管理=>库存调整单");
						sysLog.setM_strContent("删除库存调整单成功:单号为"+strTemp);
						sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
						dao.save(sysLog);				
						request.setAttribute("back_msg", "Y");
					}else{
						request.setAttribute("back_msg", "库存调整单非创建状态");
					}	
				}

				if(pt != null)
				{
					//更新总记录数  
					int rows = pt.getM_iCountRow()-roleIds.length;
					pt.setPagingParameter(rows, 1, pt.getM_iMaxRow());
					ls = CommonPagination.getPagingList(dao, pt);
					strUrl = pt.getM_strUrl();
				}
			}
			if(strUrl == null)
			{
				strUrl = "/standard/jsp/inventory/adjust/kc_adjustheader_list.jsp";
			}
			
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
	
		}catch(Exception er)
		{
			Logger.error("库存管理==>库存调整==>库存调整单删除失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * 功能:根据ID获得库存调整单
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecQuery(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String strId = CommonTools.getStrToGb2312(request.getParameter("id"));
		try
		{
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			InventoryAdjustHeader adjust = inventoryadjustbus2.getAdjustHeaderToId(strId);
			if (adjust.getStatus()!=null&&adjust.getStatus().trim().equals("0")) {
				request.setAttribute("adjust", adjust);
			}else {
				request.setAttribute("back_msg", "只有创建状态才能增加调整单明细");
			}
			request.setAttribute("adjust", adjust);
			request.getRequestDispatcher("/standard/jsp/inventory/adjust/kc_adjust_header_update.jsp").forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>库存调整==>根据ID获得库存调整单失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}	
		return null;
	}
	/**
	 * 功能:更新库存调整单
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecUpdate(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
  
		//ID
		String strId = CommonTools.getStrToGb2312(request.getParameter("id"));
		//原因代码
		String strReasoncode = CommonTools.getStrToGb2312(request.getParameter("reasoncode"));
		//原因
		String strReasondiscr = CommonTools.getStrToGb2312(request.getParameter("reasondiscr"));
		try
		{
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			InventoryAdjustHeader adjust= inventoryadjustbus2.getAdjustHeaderToId(strId);
			if(adjust != null)
			{
				adjust.setReasoncode(strReasoncode);
				adjust.setReasondiscr(strReasondiscr);
				adjust.setAdjusttime(StrTools.getCurrDateTime(5));
				inventoryadjustbus2.updateAdjustHeader(adjust);
			}
			
			//日志
			SystemLogInfo sysLog = new SystemLogInfo();
			String strUserCode = request.getSession().getAttribute("userCode").toString();
			String strUserName = request.getSession().getAttribute("userName").toString();
			sysLog.setM_strLogCode(strUserCode);
			sysLog.setM_strLogName(strUserName);
			sysLog.setM_strModuleName("库存管理=>库存调整单");
			sysLog.setM_strContent("修改库存调整单成功");
			sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
			dao.save(sysLog);
			
			//刷新 
			PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
			List ls = null;
			String strUrl = null;
			if(pt != null)
			{
				ls = CommonPagination.getPagingList(dao, pt);
				
				strUrl = pt.getM_strUrl();
			}
			if(strUrl == null)
			{
				strUrl = "/standard/jsp/inventory/adjust/kc_adjustheader_list.jsp";
			}
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>库存调整==>库存调整单修改失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

	/**
	 * 功能:库存查询
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecInventoryQuery(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		
		//客户
		String strCustomerId = CommonTools.getStrToGb2312(request.getParameter("customerid"));
		//产品
		String strPackageId = CommonTools.getStrToGb2312(request.getParameter("packageid"));
		//批次属性
		String strLotid = CommonTools.getStrToGb2312(request.getParameter("lotid"));
		//托盘条码
		String strTraycode = CommonTools.getStrToGb2312(request.getParameter("tray_code"));
		//货位
		String strLocid = CommonTools.getStrToGb2312(request.getParameter("locid"));
		InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
		if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		try
		{
			List ls = null;
			//查询的SQL语句
			String strQuerySQL = inventoryadjustbus2.getInventoryToSQL(strCustomerId,strPackageId,strLotid,strTraycode,strLocid);
			//查询角色总记录数的SQL语句
			String strCountSQL = inventoryadjustbus2.getInventoryToCountSQL(strCustomerId,strPackageId,strLotid,strTraycode,strLocid);

			String strUrl = "/standard/jsp/inventory/adjust/kc_list_detail.jsp";
			PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, strUrl ,1, maxLine);
			//查询结果
			ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);	

			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>冻结、释放==>库存查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}

		return null;
		
	}
	//****调整单详细*******************************************************
	
	
	/**
	 * 功能:增加盘点调整单详细
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAddCheckDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);  
		//调整单ID
		String strId = CommonTools.getStrToGb2312(request.getParameter("id"));
		
		//盘点结果
		String checkresult = CommonTools.getStrToGb2312(request.getParameter("checkresult"));
		
		//库存ID
		String strInventoryid = CommonTools.getStrToGb2312(request.getParameter("inventoryid"));
		
		//盘点结果ID
		String checkid = CommonTools.getStrToGb2312(request.getParameter("checkid"));
		
		//添加修改的标注
		String strflag = CommonTools.getStrToGb2312(request.getParameter("flag"));
		//状态
		//String strStatus = CommonTools.getStrToGb2312(request.getParameter("status"));
	
		try
		{
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			InventoryStorage inventory = inventoryadjustbus2.getInventoryInfoToId(strInventoryid);	//获得库存
			InventoryAdjustHeader adjustx= inventoryadjustbus2.getAdjustHeaderToId(strId);//获取调整单
			ICargoSpaceBus csBus = new CargoSpaceBusImpl(dao);
			IWhAreaBus whareaBus = new WhAreaBusImpl(dao);
			IInventoryCheckResultBus checkResultBus = new InventoryCheckResultBusImpl(dao);
			BaseWharea wharea = null;
			BaseCargospace cargospace = null;
			BaseCargospace updatecs = null; //更新货位的标识替换。
			InventoryCheckResult checkResult = null;//盘点结果单
			
			List<InventoryAdjustDetail > adjustDetail = null;
			if(adjustx!=null&&adjustx.getStatus().equals("0")){//调整单为创建状态ricoExecAddDetailOutboundExce
				if(inventory!=null&&inventory.getStatus().equals("2")){//库存为需调整状态
					adjustDetail = inventoryadjustbus2.getAdjustDetailListToId(adjustx.getAdjustid());
					if(!isCargoSpace(inventory.getCargoSpaceId(),adjustDetail)){
						cargospace = csBus.getCargoSpaceById(inventory.getCargoSpaceId());//获得货位		
						InventoryAdjustDetail adjustDel = new InventoryAdjustDetail();
						adjustDel.setAdjustid(strId);//调整单号
						adjustDel.setStatus("1");//调整单状态
						adjustDel.setWarehouseid(inventory.getWarehouseid());//仓库
						adjustDel.setWh_area_id(inventory.getWhAreaId());//库区
						adjustDel.setTlotinfo(inventory.getLotinfo());//批号
						adjustDel.setTproductid(inventory.getProductid());//产品id
						adjustDel.setTlotid(inventory.getLotid());//批号id
						adjustDel.setTtraycode(inventory.getTraycode());//托盘条码
						adjustDel.setSyspnum(inventory.getPnum());//库存数量
						adjustDel.setRealitypnum(Float.parseFloat(checkresult));//目标数量
						adjustDel.setCargo_space_id(inventory.getCargoSpaceId());//货位id
						adjustDel.setInventoryid(inventory.getInventoryid());//库存id
						adjustDel.setCreatetime(StrTools.getCurrDateTime(5));//创建时间
						adjustDel.setAdjustdetailid(SeqTool.getDetailId(strId, "2"));
						adjustDel.setWarehouseid(inventory.getWarehouseid());//仓库id
						adjustDel.setTprintdate(inventory.getProductdate());
						adjustDel.setType("1");//表示调整类型
						//dao.save(adjustDel);
						
						//更该库存
						inventory.setStatus("3"); //库存调整状态状态  0:未分配，1：已分配，2:需调整， 3:调整 4:货品调整；5：盘点       (暂存区状态)
						//dao.update(inventory);						
						
						//更改库位状态
						wharea = whareaBus.getWhareaById(cargospace.getWhAreaId());//获得库区
						if (wharea.getwhAreaType()!=null && !wharea.getwhAreaType().equals("9")) {
							cargospace.setCsstatus("2");//货位所属库区不是暂存区时，设置货位状态为满货位状态
							updatecs = cargospace;
						}
						//更改盘点结果单状态为调整
						
						checkResult = checkResultBus.getCheckResultById(checkid);
						checkResult.setStatus("2");// 1.新建，2.调整，3.完成
						
						
						
						//日志
						SystemLogInfo sysLog = new SystemLogInfo();
						String strUserCode = request.getSession().getAttribute("userCode").toString();
						String strUserName = request.getSession().getAttribute("userName").toString();
						sysLog.setM_strLogCode(strUserCode);
						sysLog.setM_strLogName(strUserName);
						sysLog.setM_strModuleName("库存管理=>库存调整");
						sysLog.setM_strContent("添加库存调整单详细成功");
						sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
						//提交到数据库 
						inventoryadjustbus2.addinvenUpdatecarsoAddAdjustDel(inventory, updatecs, adjustDel,checkResult,sysLog);
						
						request.setAttribute("back_msg", "Y");
					}else{
						request.setAttribute("back_msg", "明细中已经有该货位，无需再次添加！");
					}
				}else{
					request.setAttribute("back_msg", "库存为需调整状态才能添加明细！");
				}
			}else{
				request.setAttribute("back_msg", "调整单【"+adjustx.getAdjustid()+"】为创建状态才能添加明细！");
			}
			
			//查询的SQL语句
			String strQuerySQL = inventoryadjustbus2.getAdjustDetailSQL(strId);
			strUrl = "/standard/jsp/inventory/adjust/kc_adjustdetail_list.jsp";
			List ls=dao.searchEntities(strQuerySQL);
			//查询结果
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>库存调整==>盘点库存调整单详细增加失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
		
	/**
	 * 功能:增加库存调整单详细
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAddDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);  
		//调整单ID
		String strId = CommonTools.getStrToGb2312(request.getParameter("id"));
		//仓库
		String strWarehouseId= CommonTools.getStrToGb2312(request.getParameter("warehouseid"));
		//库区
		String strWhAreaid = CommonTools.getStrToGb2312(request.getParameter("whareaid"));
		//货位
		String strCargospaceid = CommonTools.getStrToGb2312(request.getParameter("cargospaceid"));
		//产品id
		String strPackage= CommonTools.getStrToGb2312(request.getParameter("packageid"));
	   //生产日期
		String strPrintdate = CommonTools.getStrToGb2312(request.getParameter("printdate"));
	    //单位
		String strPunt = CommonTools.getStrToGb2312(request.getParameter("punit"));
	   //批号类型
		String strLotid = CommonTools.getStrToGb2312(request.getParameter("lotid"));
	   //批号信息
		String strLotinfo = CommonTools.getStrToGb2312(request.getParameter("lotinfo"));
	   //托盘条码
		String strTraycode = CommonTools.getStrToGb2312(request.getParameter("traycode"));
	   //目标数量数量
		String pnum = CommonTools.getStrToGb2312(request.getParameter("pnum"));
		//库存ID
		String strInventoryid = CommonTools.getStrToGb2312(request.getParameter("inventoryid"));
		//添加修改的标注
		String strflag = CommonTools.getStrToGb2312(request.getParameter("flag"));
		//状态
		//String strStatus = CommonTools.getStrToGb2312(request.getParameter("status"));
	
		try
		{
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			InventoryStorage inventory = inventoryadjustbus2.getInventoryInfoToId(strInventoryid);	//获得库存
			InventoryAdjustHeader adjustx= inventoryadjustbus2.getAdjustHeaderToId(strId);
			ICargoSpaceBus csBus = new CargoSpaceBusImpl(dao);
			IWhAreaBus whareaBus = new WhAreaBusImpl(dao);
			BaseWharea wharea = null;
			BaseCargospace cargospace = null;
			BaseCargospace updatecs = null; //更新货位的标识替换。
			List<InventoryAdjustDetail >adjustDetail = null;
			if(inventory != null && inventory.getStatus().equals("0"))  //有库存，且库存为未分配状态
			{
		        adjustDetail = inventoryadjustbus2.getAdjustDetailListToId(adjustx.getAdjustid());				
			     if(!isCargoSpace(inventory.getCargoSpaceId(),adjustDetail) || inventory.getCargoSpaceId().substring(3, 6).equals(StandardConstant.zcidkzl)){
				    cargospace = csBus.getCargoSpaceById(inventory.getCargoSpaceId());//获得货位		
					if(adjustx.getStatus().equals("0")){ //创建状态。 1.添加明细2。更改库存状态（调整状态），3.货位状态：调整状态（暂存去除外）
						InventoryAdjustDetail adjustDel = new InventoryAdjustDetail();
						adjustDel.setAdjustid(strId);//调整单号
						adjustDel.setStatus("1");//调整单状态
						adjustDel.setWarehouseid(inventory.getWarehouseid());//仓库
						adjustDel.setWh_area_id(inventory.getWhAreaId());//库区
						adjustDel.setTlotinfo(strLotinfo);//批号
						adjustDel.setTproductid(strPackage);//产品id
						adjustDel.setTpunit(strPunt);//单位
						adjustDel.setTlotid(strLotid);//批号id
						adjustDel.setTtraycode(strTraycode);//托盘条码
						adjustDel.setSyspnum(inventory.getPnum());//库存数量
						adjustDel.setRealitypnum(Double.parseDouble(pnum));//目标数量
						adjustDel.setCargo_space_id(inventory.getCargoSpaceId());//货位id
						adjustDel.setInventoryid(inventory.getInventoryid());//库存id
						adjustDel.setCreatetime(StrTools.getCurrDateTime(5));//创建时间
						adjustDel.setAdjustdetailid(SeqTool.getDetailId(strId, "2"));
						adjustDel.setWarehouseid(inventory.getWarehouseid());//仓库id
						adjustDel.setTprintdate(strPrintdate);
						
						adjustDel.setType("1");//表示调整类型
						//dao.save(adjustDel);
						
						//更该库存
						inventory.setStatus("3"); //库存调整状态状态  0:未分配，1：已分配，2:需调整， 3:调整 4:货品调整；5：盘点       (暂存区状态)
						//dao.update(inventory);						
						
						//更改库位状态
						wharea = whareaBus.getWhareaById(cargospace.getWhAreaId());//获得库区
						/*if (wharea.getwhAreaType()!=null && !wharea.getwhAreaType().equals("9")) {
							cargospace.setCsstatus("2");//货位所属库区不是暂存区时，设置货位状态为调整状态
							updatecs = cargospace;
						}else{
							
						}*/
						
						//日志
						SystemLogInfo sysLog = new SystemLogInfo();
						String strUserCode = request.getSession().getAttribute("userCode").toString();
						String strUserName = request.getSession().getAttribute("userName").toString();
						sysLog.setM_strLogCode(strUserCode);
						sysLog.setM_strLogName(strUserName);
						sysLog.setM_strModuleName("库存管理=>库存调整");
						sysLog.setM_strContent("添加库存调整单详细成功");
						sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
						//提交到数据库 
						inventoryadjustbus2.addinvenUpdatecarsoAddAdjustDel1(inventory, updatecs, adjustDel,sysLog);
						
						request.setAttribute("back_msg", "Y");
					}else{
						request.setAttribute("back_msg", "只有创建状态才能增加调整单明细");
					}
				}else {
					request.setAttribute("back_msg", "明细中已经有该货位，无需再次添加！");
				}
			}else{//没有库存情况
				if (strflag!=null && strflag.equals("3")) {
							adjustDetail = inventoryadjustbus2.getAdjustDetailListToId(adjustx.getAdjustid());
							if (!isCargoSpace(strCargospaceid, adjustDetail)) {
							cargospace = csBus.getCargoSpaceById(strCargospaceid);//获得货位
							if (cargospace.getCsstatus()!=null && cargospace.getCsstatus().equals("1")) {
								InventoryAdjustDetail adjustDetail1 = new InventoryAdjustDetail();
								adjustDetail1.setAdjustdetailid(SeqTool.getDetailId(strId, "1"));
								adjustDetail1.setAdjustid(strId);//调整单id
								adjustDetail1.setTlotid(strLotid);//批号类型
								adjustDetail1.setTlotinfo(strLotinfo);//批号信息
								adjustDetail1.setTprintdate(strPrintdate);//生产日期
								adjustDetail1.setTproductid(strPackage);//产品
								adjustDetail1.setRealitypnum(Double.valueOf(pnum));
								adjustDetail1.setTpunit(strPunt);//单位
								adjustDetail1.setTtraycode(strTraycode);//托盘条码
								adjustDetail1.setWarehouseid(strWarehouseId);//仓库
								adjustDetail1.setWh_area_id(strWhAreaid);//货区
								adjustDetail1.setCargo_space_id(strCargospaceid);//货位
								adjustDetail1.setStatus("1");
								adjustDetail1.setType("2");//表示新增库存
								//dao.save(adjustDetail);
		
								//更改库位状态
								wharea = whareaBus.getWhareaById(cargospace.getWhAreaId());//获得库区
								if (wharea.getwhAreaType()!=null && !wharea.getwhAreaType().equals("9")) {
									cargospace.setCsstatus("2");//货位所属库区不是暂存区时，设置货位状态为调整状态
									updatecs = cargospace;
								}
								//dao.update(cargospace);
								
								//日志
								SystemLogInfo sysLog = new SystemLogInfo();
								String strUserCode = request.getSession().getAttribute("userCode").toString();
								String strUserName = request.getSession().getAttribute("userName").toString();
								sysLog.setM_strLogCode(strUserCode);
								sysLog.setM_strLogName(strUserName);
								sysLog.setM_strModuleName("库存管理=>库存调整");
								sysLog.setM_strContent("添加库存调整单详细成功");
								sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
								//dao.save(sysLog);
								//提交到数据库
								inventoryadjustbus2.addinvenUpdatecarsoAddAdjustDel1(inventory,updatecs, adjustDetail1,sysLog);		
								
								request.setAttribute("back_msg", "Y");					
							
							}else {
								request.setAttribute("back_msg", "只有未分配状态的货位才能添加信息！");
							}		
						}else {
							request.setAttribute("back_msg", "明细中已经添加了对该货位的调整，无需再次添加！");
						}
					}else {
					        request.setAttribute("back_msg", "只有库存状态为未分配状态时，方可进行添加调整！");
				    }
				
			}
		
			//查询的SQL语句
			String strQuerySQL = inventoryadjustbus2.getAdjustDetailSQL(strId);
			strUrl = "/standard/jsp/inventory/adjust/kc_adjustdetail_list.jsp";
			List ls=dao.searchEntities(strQuerySQL);
			//查询结果
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>库存调整==>库存调整单详细增加失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加出库异常调整单详细
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAddDetailOutboundExce(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);  
		//调整单ID
		String strId = CommonTools.getStrToGb2312(request.getParameter("id"));
		//异常单ID
		String strInventoryid = CommonTools.getStrToGb2312(request.getParameter("inventoryid"));
	
		try
		{
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			//获取出库异常单
			InventoryNeedcheck inventoryNC = inventoryadjustbus2.getInventoryNeedcheckInfoById(strInventoryid);
			
			//获取调整单头
			InventoryAdjustHeader adjustx= inventoryadjustbus2.getAdjustHeaderToId(strId);
			
			//获取库存，根据异常单的货位
			InventoryStorage inventoryStorage = inventoryadjustbus2.getInventoryStorageInfoByCargoSpaceId(inventoryNC.getCargoSpaceId());
			
			ICargoSpaceBus csBus = new CargoSpaceBusImpl(dao);
			IWhAreaBus whareaBus = new WhAreaBusImpl(dao);
			BaseWharea wharea = null;
			BaseCargospace cargospace = null;
			BaseCargospace updatecs = null; //更新货位的标识替换。
			List<InventoryAdjustDetail > adjustDetail = null;
			if(inventoryNC != null &&inventoryNC.getStatus().equals("0")&&inventoryNC.getInouttype().equals("2")){//只有新建状态并且是出库异常的出库异常才能增加调整详细
				if(adjustx.getStatus().equals("0")){// 只有创建状态的调整单才能添加明细 0：创建状态 1：审核状态 3：完成状态
					adjustDetail = inventoryadjustbus2.getAdjustDetailListToId(adjustx.getAdjustid());	//获取调整单详情
					if(inventoryStorage != null&&inventoryStorage.getStatus().equals("0")){//库存为未分配状态
					if(!isCargoSpace(inventoryStorage.getCargoSpaceId(),adjustDetail)){//判断该货位是否已经分配
						
							//修改出库异常单
							inventoryNC.setStatus("1");
							inventoryNC.setHandleflag("Y");
							//inventoryNC.setHandletime(StrTools.getCurrDateTime(5));
							
							//增加调整单详细
							InventoryAdjustDetail adjustDel = new InventoryAdjustDetail();
							adjustDel.setAdjustid(strId);//调整单号
							adjustDel.setStatus("1");//调整单详细状态 （1，未调整 2，已调整）
							adjustDel.setInventoryid(inventoryNC.getNeedcheckid());//出库异常单ID
							adjustDel.setWarehouseid(inventoryNC.getWarehouseid());//仓库
							adjustDel.setWh_area_id(inventoryStorage.getWhAreaId());//库区
							adjustDel.setTlotinfo(inventoryStorage.getLotinfo());//批号
							adjustDel.setTlotid(inventoryStorage.getLotid());//批号id
							adjustDel.setTproductid(inventoryStorage.getProductid());//产品id
							adjustDel.setCargo_space_id(inventoryNC.getCargoSpaceId());//货位
							adjustDel.setTtraycode(inventoryStorage.getTraycode());//托盘条码
							adjustDel.setTpunit(inventoryStorage.getPunit());//单位
							
							adjustDel.setSyspnum(inventoryStorage.getPnum());//库存数量
							adjustDel.setRealitypnum(0);//目标数量
							adjustDel.setCreatetime(StrTools.getCurrDateTime(5));//创建时间
							adjustDel.setAdjustdetailid(SeqTool.getDetailId(strId, "2"));
							adjustDel.setTprintdate(inventoryStorage.getProductdate());
							adjustDel.setType("1");//表示调整类型
							
							//修改库存状态为调整
							inventoryStorage.setStatus("3");//0:未分配，1：已分配，2:需调整， 3:调整 4:货品调整；5：盘点       (暂存区状态)
							
							
							
							//日志
							SystemLogInfo sysLog = new SystemLogInfo();
							String strUserCode = request.getSession().getAttribute("userCode").toString();
							String strUserName = request.getSession().getAttribute("userName").toString();
							sysLog.setM_strLogCode(strUserCode);
							sysLog.setM_strLogName(strUserName);
							sysLog.setM_strModuleName("库存管理=>库存调整");
							sysLog.setM_strContent("添加库存调整单详细成功");
							sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
							//提交到数据库 
							inventoryadjustbus2.addinvenUpdatecarsoAddAdjustDeloutboundExce(inventoryStorage,inventoryNC,adjustDel,sysLog);
							request.setAttribute("back_msg", "Y");
						
					}else{
						request.setAttribute("back_msg", "明细中已经有该货位，无需再次添加！");
					}
					}else{
						request.setAttribute("back_msg", "没有库位【"+inventoryNC.getCargoSpaceId()+"】对应的库存或者库存为未分配状态才可创建调整单明细");
					}
				}else{
						request.setAttribute("back_msg", "只有调整单在创建状态才能增加调整单明细");
				}
			}else{
				request.setAttribute("back_msg", "【出库异常调整】异常单只有在创建状态且为出库异常时才能增加调整单明细");
			}
			//查询的SQL语句
			String strQuerySQL = inventoryadjustbus2.getAdjustDetailSQL(strId);
			strUrl = "/standard/jsp/inventory/adjust/kc_adjustdetail_list.jsp";
			List ls=dao.searchEntities(strQuerySQL);
			//查询结果
			request.setAttribute("inventoryNCId", strInventoryid);
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>库存调整==>库存调整单详细增加失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:增加入库异常调整单详细
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAddDetailInboundExce(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);  
		//调整单ID
		String strId = CommonTools.getStrToGb2312(request.getParameter("id"));
		//异常单ID
		String strInventoryid = CommonTools.getStrToGb2312(request.getParameter("inventoryid"));
		//产品ID
		String productid = CommonTools.getStrToGb2312(request.getParameter("productid"));
		//生产日期
		String printDate = CommonTools.getStrToGb2312(request.getParameter("printDate"));
		//托盘条码
		String traycode = CommonTools.getStrToGb2312(request.getParameter("traycode"));
		//单位
		String punit = CommonTools.getStrToGb2312(request.getParameter("punit"));
		//库存数量
		String pnum = CommonTools.getStrToGb2312(request.getParameter("pnum"));
		//批号类型
		String lotid = CommonTools.getStrToGb2312(request.getParameter("lotid"));
		//批号信息
		String lotinfo = CommonTools.getStrToGb2312(request.getParameter("lotinfo"));
		//是否是整托
		String isSplit = CommonTools.getStrToGb2312(request.getParameter("isSplit"));
	
		try
		{
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			//获取入库异常单
			InventoryNeedcheck inventoryNC = inventoryadjustbus2.getInventoryNeedcheckInfoById(strInventoryid);
			//仓库ID
			String warehouseid = inventoryNC.getWarehouseid();
			//库位ID
			String cargospaceid = inventoryNC.getCargoSpaceId();
		
			//获取库位对象，根据其ID
			ICargoSpaceBus csBus = new CargoSpaceBusImpl(dao);
			BaseCargospace cargospace = csBus.getCargoSpaceById(cargospaceid);
			
			//获取库区对象，根据库位id和仓库id
			IWhAreaBus whareaBus = new WhAreaBusImpl(dao);
			String whareaid = csBus.getWhAreaIdByWarehouseidAndCargospaceId(warehouseid, cargospaceid);
//			BaseWharea wharea = whareaBus.getWhareaById(whareaid);
			
//			//获取巷道对象，根据
//			IAlleyBus alleybus = new AlleyBusImpl(dao);
//			String alleyid = csBus.getAlleyIdByWarehouseidAndCargospaceId(inventoryNC.getWarehouseid(), inventoryNC.getCargoSpaceId());
//			BaseAlley alley = alleybus.getAlleyById(alleyid);
			List<InventoryAdjustDetail > adjustDetail = null;

			//获取调整单头
			InventoryAdjustHeader adjustx= inventoryadjustbus2.getAdjustHeaderToId(strId);
			if(inventoryNC != null &&inventoryNC.getStatus().equals("0")&&inventoryNC.getInouttype().equals("1")){//只有新建状态并且是入库异常的入库异常才能增加调整详细
				if(adjustx.getStatus().equals("0")){// 只有创建状态的调整单才能添加明细 0：创建状态 1：审核状态 3：完成状态
					adjustDetail = inventoryadjustbus2.getAdjustDetailListToId(adjustx.getAdjustid());	//获取调整单详情
					if(!isCargoSpace(cargospaceid,adjustDetail)){//判断该货位是否已经分配了明细
						//修改出库异常单
						inventoryNC.setStatus("1");// 0--新建  1--正在调整 2--调整完成
						inventoryNC.setHandleflag("Y");//处理标识 Y,N
						
						//增加调整单详细
						InventoryAdjustDetail adjustDel = new InventoryAdjustDetail();
						adjustDel.setAdjustid(strId);//调整单号
						adjustDel.setStatus("1");//调整单详细状态 （1，未调整 2，已调整）
						adjustDel.setInventoryid(strInventoryid);//入库异常单ID
						adjustDel.setWarehouseid(warehouseid);//仓库
						adjustDel.setWh_area_id(whareaid);//库区
						adjustDel.setCargo_space_id(cargospaceid);//货位
						adjustDel.setTlotid(lotid);//批号id
						adjustDel.setTlotinfo(lotinfo);//批号
						adjustDel.setTpunit(punit);//单位
						adjustDel.setTproductid(productid);//产品id
						
						adjustDel.setTtraycode(traycode);//托盘条码
						adjustDel.setSyspnum(0);//库存数量
						adjustDel.setRealitypnum(Double.parseDouble(pnum));//目标数量
						adjustDel.setCreatetime(StrTools.getCurrDateTime(5));//创建时间
						adjustDel.setAdjustdetailid(SeqTool.getDetailId(strId, "2"));
						adjustDel.setTprintdate(printDate);//产品生产日期
						adjustDel.setType("1");//表示调整类型
						
						
						//日志
						SystemLogInfo sysLog = new SystemLogInfo();
						String strUserCode = request.getSession().getAttribute("userCode").toString();
						String strUserName = request.getSession().getAttribute("userName").toString();
						sysLog.setM_strLogCode(strUserCode);
						sysLog.setM_strLogName(strUserName);
						sysLog.setM_strModuleName("库存管理=>库存调整");
						sysLog.setM_strContent("添加库存调整单详细成功");
						sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
						
						//提交到数据库 
						inventoryadjustbus2.addinvenUpdatecarsoAddAdjustDeloutboundExce(inventoryNC,adjustDel,sysLog);
						request.setAttribute("back_msg", "Y");
						
					}else{
						request.setAttribute("back_msg", "明细中已经有该货位，无需再次添加！");
					}
					
				}else{
					request.setAttribute("back_msg", "只有调整单在创建状态才能增加调整单明细");
				}
			}else{
				request.setAttribute("back_msg", "【入库异常调整】异常单只有在创建状态且为入库异常时才能增加调整单明细");
			}

			//查询的SQL语句
			String strQuerySQL = inventoryadjustbus2.getAdjustDetailSQL(strId);
			strUrl = "/standard/jsp/inventory/adjust/kc_adjustdetail_list.jsp";
			List ls=dao.searchEntities(strQuerySQL);
			//查询结果
			request.setAttribute("inventoryNCId", strInventoryid);
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>库存调整==>库存调整单详细增加失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除盘点调整单详细
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecCheckDeleteDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		try
		{

			//调整单ID
			String strId = CommonTools.getStrToGb2312(request.getParameter("id"));
			//代码(多个用逗号分隔)
			String strDeleteStr = CommonTools.getStrToGb2312(request.getParameter("deleteStr"));
			
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			IInventoryCheckResultBus checkResultBus = new InventoryCheckResultBusImpl(dao);
			String strUrl = null;
			InventoryAdjustHeader adjust= inventoryadjustbus2.getAdjustHeaderToId(strId);
			if(strDeleteStr != null && strDeleteStr.length() > 0 && adjust.getStatus().equals("0")) //创建状态
			{
				String [] roleIds = strDeleteStr.split(",");
				for(int i=0; i<roleIds.length; i++)
				{
					//删除
					String strTemp =  roleIds[i];
					//inventoryadjustbus2.deleteAdjustDetail(strTemp);
					InventoryAdjustDetail detail = inventoryadjustbus2.getAdjustDetailToId(strTemp);
					if(detail.getType()!=null && detail.getType().equals("1")){
						InventoryStorage inventory = inventoryadjustbus2.getInventoryInfoToId(detail.getInventoryid());
						inventory.setStatus("2"); //还原未需调整状态 0:未分配，1：已分配，2:需调整， 3:调整 4:货品调整；5：盘点       (暂存区状态)
						InventoryCheckResult checkResult = checkResultBus.getCheckResultByInventoryId(detail.getInventoryid());
						checkResult.setStatus("1");// 1.新建，2.调整，3.完成
						
						
						if(inventory!=null){
							dao.update(inventory);
						}
						if(checkResult!=null){
							dao.update(checkResult);
						}
						if(detail!=null){
							dao.delete(detail);
						}
						
					}else {
						ICargoSpaceBus csBus = new CargoSpaceBusImpl(dao);
						BaseCargospace cargospace = csBus.getCargoSpaceById(detail.getCargo_space_id());
						cargospace.setCsstatus("1");
						dao.update(cargospace);
						dao.delete(detail);
					}

				}
				//日志
				SystemLogInfo sysLog = new SystemLogInfo();
				String strUserCode = request.getSession().getAttribute("userCode").toString();
				String strUserName = request.getSession().getAttribute("userName").toString();
				sysLog.setM_strLogCode(strUserCode);
				sysLog.setM_strLogName(strUserName);
				sysLog.setM_strModuleName("库存管理=>库存调整");
				sysLog.setM_strContent("删除库存调整单详细成功");
				sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
				dao.save(sysLog);
				request.setAttribute("back_msg", "Y");
				
			}else{
				request.setAttribute("back_msg", "只有创建状态才能删除");
			}
			//查询的SQL语句
			String strQuerySQL = inventoryadjustbus2.getAdjustDetailSQL(strId);
			strUrl = "/standard/jsp/inventory/adjust/kc_adjustdetail_list.jsp";
			List ls=dao.searchEntities(strQuerySQL);
			//查询结果
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
	
		}catch(Exception er)
		{
			Logger.error("库存管理==>库存调整==>库存调整单详细删除失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除库存调整单详细
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecDeleteDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		try
		{

			//调整单ID
			String strId = CommonTools.getStrToGb2312(request.getParameter("id"));
			//代码(多个用逗号分隔)
			String strDeleteStr = CommonTools.getStrToGb2312(request.getParameter("deleteStr"));
			
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			IInventoryCheckResultBus checkResultBus = new InventoryCheckResultBusImpl(dao);
			String strUrl = null;
			InventoryAdjustHeader adjust= inventoryadjustbus2.getAdjustHeaderToId(strId);
			if(strDeleteStr != null && strDeleteStr.length() > 0 && adjust.getStatus().equals("0")) //创建状态
			{
				String [] roleIds = strDeleteStr.split(",");
				for(int i=0; i<roleIds.length; i++)
				{
					//删除
					String strTemp =  roleIds[i];
					//inventoryadjustbus2.deleteAdjustDetail(strTemp);
					InventoryAdjustDetail detail = inventoryadjustbus2.getAdjustDetailToId(strTemp);
					if(detail.getType()!=null && detail.getType().equals("1")){
						//修改库存
						InventoryStorage inventory = inventoryadjustbus2.getInventoryInfoToId(detail.getInventoryid());
						inventory.setStatus("0"); //还原未需调整状态 0:未分配，1：已分配，2:需调整， 3:调整 4:货品调整；5：盘点       (暂存区状态)
						
						if(inventory!=null){
							dao.update(inventory);
						}
						
						if(detail!=null){
							dao.delete(detail);
						}
						
					}else {
						
						ICargoSpaceBus csBus = new CargoSpaceBusImpl(dao);
						BaseCargospace cargospace = csBus.getCargoSpaceById(detail.getCargo_space_id());
						cargospace.setCsstatus("1");
						dao.update(cargospace);
						dao.delete(detail);
					}

				}
				//日志
				SystemLogInfo sysLog = new SystemLogInfo();
				String strUserCode = request.getSession().getAttribute("userCode").toString();
				String strUserName = request.getSession().getAttribute("userName").toString();
				sysLog.setM_strLogCode(strUserCode);
				sysLog.setM_strLogName(strUserName);
				sysLog.setM_strModuleName("库存管理=>库存调整");
				sysLog.setM_strContent("删除库存调整单详细成功");
				sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
				dao.save(sysLog);
				request.setAttribute("back_msg", "Y");
				
			}else{
				request.setAttribute("back_msg", "只有创建状态才能删除");
			}
			//查询的SQL语句
			String strQuerySQL = inventoryadjustbus2.getAdjustDetailSQL(strId);
			strUrl = "/standard/jsp/inventory/adjust/kc_adjustdetail_list.jsp";
			List ls=dao.searchEntities(strQuerySQL);
			//查询结果
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
	
		}catch(Exception er)
		{
			Logger.error("库存管理==>库存调整==>库存调整单详细删除失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除入库调整单明细
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecInDeleteDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		IInventoryNeedCheckBus iinventoryNCBus = new InventoryNeedCheckBusImp(dao);
		try
		{

			//调整单ID
			String strId = CommonTools.getStrToGb2312(request.getParameter("id"));
			//代码(多个用逗号分隔)
			String strDeleteStr = CommonTools.getStrToGb2312(request.getParameter("deleteStr"));
			
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			
			String strUrl = null;
			InventoryAdjustHeader adjust= inventoryadjustbus2.getAdjustHeaderToId(strId);
			if(strDeleteStr != null && strDeleteStr.length() > 0 && adjust.getStatus().equals("0")) //创建状态
			{
				String [] roleIds = strDeleteStr.split(",");
				for(int i=0; i<roleIds.length; i++)
				{
					//删除
					String strTemp =  roleIds[i];
					//inventoryadjustbus2.deleteAdjustDetail(strTemp);
					InventoryAdjustDetail detail = inventoryadjustbus2.getAdjustDetailToId(strTemp);
					
					//获取库位
					String cargospaceid = detail.getCargo_space_id();
					//根据货位获取入库异常单
					InventoryNeedcheck inNeedcheck = iinventoryNCBus.getINeedcheckInfoByCargospaceId(cargospaceid);
					if(inNeedcheck!=null){//是否处理N,Y与
						inNeedcheck.setHandleflag("N");
						inNeedcheck.setStatus("0");//状态0--新建1---调整2--完成
					}
					
					
					if(detail!=null && detail.getStatus().equals("1")){
						//删除入库异常调整单明细
						if(detail!=null){
							dao.delete(detail);
						}
						//修改入库异常单的是否处理N与状态0
						if(inNeedcheck!=null){
							dao.update(inNeedcheck);
						}
						
						
					}else{
						request.setAttribute("back_msg", "只有入库异常调整单明细为未调整状态才能删除");
					}

				}
				//日志
				SystemLogInfo sysLog = new SystemLogInfo();
				String strUserCode = request.getSession().getAttribute("userCode").toString();
				String strUserName = request.getSession().getAttribute("userName").toString();
				sysLog.setM_strLogCode(strUserCode);
				sysLog.setM_strLogName(strUserName);
				sysLog.setM_strModuleName("库存管理=>库存调整");
				sysLog.setM_strContent("删除入库异常调整单明细成功");
				sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
				dao.save(sysLog);
				request.setAttribute("back_msg", "Y");
				
			}else{
				request.setAttribute("back_msg", "只有调整单为创建状态才能删除");
			}
			//查询的SQL语句
			String strQuerySQL = inventoryadjustbus2.getAdjustDetailSQL(strId);
			strUrl = "/standard/jsp/inventory/adjust/kc_adjustdetail_list.jsp";
			List ls=dao.searchEntities(strQuerySQL);
			//查询结果
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
	
		}catch(Exception er)
		{
			Logger.error("库存管理==>库存调整==>入库异常调整单明细删除失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:删除出库调整单详细
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecOutDeleteDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		IInventoryNeedCheckBus iinventoryNCBus = new InventoryNeedCheckBusImp(dao);
		try
		{

			//调整单ID
			String strId = CommonTools.getStrToGb2312(request.getParameter("id"));
			//代码(多个用逗号分隔)
			String strDeleteStr = CommonTools.getStrToGb2312(request.getParameter("deleteStr"));
			
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			
			String strUrl = null;
			InventoryAdjustHeader adjust= inventoryadjustbus2.getAdjustHeaderToId(strId);
			if(strDeleteStr != null && strDeleteStr.length() > 0 && adjust.getStatus().equals("0")) //创建状态
			{
				String [] roleIds = strDeleteStr.split(",");
				for(int i=0; i<roleIds.length; i++)
				{
					//删除
					String strTemp =  roleIds[i];
					//inventoryadjustbus2.deleteAdjustDetail(strTemp);
					InventoryAdjustDetail detail = inventoryadjustbus2.getAdjustDetailToId(strTemp);
					
						//根据库位找库存
						InventoryStorage inventory = inventoryadjustbus2.getInventoryStorageInfoByCargoSpaceId(detail.getCargo_space_id());
						inventory.setStatus("0"); //还原未需调整状态0:未分配，1：已分配，2:需调整， 3:调整 4:货品调整；5：盘点       (暂存区状态)
						
						//获取库位
						String cargospaceid = detail.getCargo_space_id();
						//根据货位获取入库异常单
						InventoryNeedcheck inNeedcheck = iinventoryNCBus.getINeedcheckInfoByCargospaceId(cargospaceid);
						if(inNeedcheck!=null){//是否处理N,Y与
							inNeedcheck.setHandleflag("N");
							inNeedcheck.setStatus("0");//状态0--新建1---调整2--完成
						}
						
						//修改出库异常单
						if(inNeedcheck!=null){
							dao.update(inNeedcheck);
						}
						//修改库存状态
						if(inventory!=null){
							dao.update(inventory);
						}
						//删除明细
						if(detail!=null){
							dao.delete(detail);
						}
						
					

				}
				//日志
				SystemLogInfo sysLog = new SystemLogInfo();
				String strUserCode = request.getSession().getAttribute("userCode").toString();
				String strUserName = request.getSession().getAttribute("userName").toString();
				sysLog.setM_strLogCode(strUserCode);
				sysLog.setM_strLogName(strUserName);
				sysLog.setM_strModuleName("库存管理=>库存调整");
				sysLog.setM_strContent("删除出库调整单详细成功");
				sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
				dao.save(sysLog);
				request.setAttribute("back_msg", "Y");
				
			}else{
				request.setAttribute("back_msg", "只有创建状态才能删除");
			}
			//查询的SQL语句
			String strQuerySQL = inventoryadjustbus2.getAdjustDetailSQL(strId);
			strUrl = "/standard/jsp/inventory/adjust/kc_adjustdetail_list.jsp";
			List ls=dao.searchEntities(strQuerySQL);
			//查询结果
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
	
		}catch(Exception er)
		{
			Logger.error("库存管理==>库存调整==>库存调整单详细删除失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	
	
	
	
	
	
	
	/**
	 * 功能:根据ID获得库存调整单详细
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecQueryDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String strId = CommonTools.getStrToGb2312(request.getParameter("id"));
		try
		{
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			//BaseProductDaoImpl skuMgr = new BaseProductDaoImpl(dao);
			InventoryAdjustDetail detail = inventoryadjustbus2.getAdjustDetailToId(strId);
			//BaseProduct sku = skuMgr.getProductById(detail.getTproductid());	
			request.setAttribute("adjustdetail", detail);
			//request.setAttribute("sku", sku);
			strUrl = "/standard/jsp/inventory/adjust/kc_adjust_detail_update.jsp";
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>库存调整单==>根据ID获得库存调整单详细失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}	
		return null;
	}
	/**
	 * 功能:修改库存调整单详细
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecUpdateDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		//调整单明细ID
		String strId = CommonTools.getStrToGb2312(request.getParameter("detailId"));

		//目标数量
		String strToQty = CommonTools.getStrToGb2312(request.getParameter("tonum"));
		try
		{
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			InventoryAdjustDetail detail = inventoryadjustbus2.getAdjustDetailToId(strId);
			InventoryAdjustHeader header = inventoryadjustbus2.getAdjustHeaderToId(detail.getAdjustid());
			if(header.getStatus() != null && header.getStatus().equals("0")) //单据详细状态为创建状态
			{   
				detail.setRealitypnum(Double.valueOf(strToQty));	     
				detail.setCreatetime(StrTools.getCurrDateTime(5));
				dao.update(detail);
				request.setAttribute("back_msg", "Y");
			}else{
				request.setAttribute("back_msg", "只有创建状态才能修改");
			}
			
			//日志
			SystemLogInfo sysLog = new SystemLogInfo();
			String strUserCode = request.getSession().getAttribute("userCode").toString();
			String strUserName = request.getSession().getAttribute("userName").toString();
			sysLog.setM_strLogCode(strUserCode);
			sysLog.setM_strLogName(strUserName);
			sysLog.setM_strModuleName("库存管理=>库存调整");
			sysLog.setM_strContent("修改库存调整单详细成功");
			sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
			dao.save(sysLog);
			
			//查询的SQL语句
			String strQuerySQL = inventoryadjustbus2.getAdjustDetailSQL(strId);
			strUrl = "/standard/jsp/inventory/adjust/kc_adjustdetail_list.jsp";
			List ls=dao.searchEntities(strQuerySQL);
			//查询结果
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>库存调整==>库存调整单详细修改失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * 批量调整
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdjust(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);	
		String strDeleteStr = CommonTools.getStrToGb2312(request.getParameter("deleteStr"));
		try
		{
			PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
			List ls = null;
			String strUrl = null;
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			String strUserCode = request.getSession().getAttribute("userCode").toString();
			String strUserName = request.getSession().getAttribute("userName").toString();
			String backmeg = "";//返回信息
			if(strDeleteStr != null && strDeleteStr.length() > 0)
			{
				String [] roleIds = strDeleteStr.split(",");
				for(int i=0; i<roleIds.length; i++)
				{
					List<InventoryAdjustDetail> lsajustDetail = new ArrayList<InventoryAdjustDetail>();
					List<InventoryStorage> lsinventory = new ArrayList<InventoryStorage>();//修改库存
					List<InventoryStorage> addkcls = new ArrayList<InventoryStorage>();//新增库存集合
					List<InoutJob> jobls = new ArrayList<InoutJob>();//作业集合（已完成）
					List<InoutJobdetail> jobdells = new ArrayList<InoutJobdetail>();//作业明细
					List<BaseCargospace> cargospacesls = new ArrayList<BaseCargospace>();	
					Object[] obj = null;
					//ID
					String strTemp =  roleIds[i];				
					InventoryAdjustHeader adjust= inventoryadjustbus2.getAdjustHeaderToId(strTemp);
					List lsDetail = inventoryadjustbus2.getAdjustDetailListToId(strTemp);
				if (adjust.getStatus()!=null && adjust.getStatus().trim().equals("1")) {//只有审核才能调整
					if(lsDetail != null && lsDetail.size()>0)
					{
						for(int j = 0; j < lsDetail.size(); j++)
						{
							InventoryAdjustDetail detail = (InventoryAdjustDetail)lsDetail.get(j);	
							String status = detail.getStatus();
							if(status != null && status.trim().equals("1"))//未完成
							{
								String type = detail.getType();
								InoutJob jobObj = null;
								if (type!=null && type.trim().equals("2")) {//新增库存（入库异常用）
									obj = inventoryadjustbus2.addinvenForAdjust(detail);
									jobObj = (InoutJob)obj[0];
									jobls.add(jobObj);//添加作业
									jobdells.add((InoutJobdetail)obj[1]);//添加作业明细
									addkcls.add((InventoryStorage)obj[2]);//添加库存
									cargospacesls.add((BaseCargospace)obj[3]);//添加库位
									//detail.setStatus("2");//完成
									//lsajustDetail.add(detail);
								}else {//调整库存数量（盘点常用）
									obj = inventoryadjustbus2.updateinvenForAdjust(detail);
									jobObj = (InoutJob)obj[0];
									jobls.add(jobObj);//添加作业
									jobdells.add((InoutJobdetail)obj[1]);//添加作业明细
									lsinventory.add((InventoryStorage)obj[2]);//添加库存
									cargospacesls.add((BaseCargospace)obj[3]);
								}
								//更新调整单的状态，调整时间
								detail.setJobid(jobObj.getJobid());
								detail.setStatus("2");//已调整
								detail.setAdjusttime(StrTools.getCurrDateTime(5));//调整时间
								lsajustDetail.add(detail);						
							}
						}
						//修改调整单状态等
						adjust.setAdjusttime(StrTools.getCurrDateTime(5));
						adjust.setStatus("3");//完成状态
						adjust.setAuditmanid(strUserCode);
						//日志
						SystemLogInfo sysLog = new SystemLogInfo();
						sysLog.setM_strLogCode(strUserCode);
						sysLog.setM_strLogName(strUserName);
						sysLog.setM_strModuleName("库存管理=>库存调整单");
						sysLog.setM_strContent("调整库存调整单成功");
						sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
						//执行调整，提交到数据库
						inventoryadjustbus2.createjustInvoice(adjust,lsajustDetail,lsinventory,addkcls,jobls,jobdells,cargospacesls,sysLog);
		        		Logger.info("用户【" + strUserCode + "】调整了库存调整单成功，单据号：" + adjust.getAdjustid());
						
					}else {
						request.setAttribute("back_msg", "调整单:"+adjust.getAdjustid()+"没有可调整的信息！");
					}
					
				  }else {
					backmeg+= strTemp+",";
					request.setAttribute("back_msg", "调整单:"+backmeg+"是不是审核状态，不能进行调整！");
				}

				    
			 }
								
				if(pt != null)
				{
					//更新总记录数  
					int rows = pt.getM_iCountRow();
					pt.setPagingParameter(rows, 1, pt.getM_iMaxRow());
					
					ls = CommonPagination.getPagingList(dao, pt);
					
					strUrl = pt.getM_strUrl();
				}
			}
			if(strUrl == null)
			{
				strUrl = "/jsp/inventory/inventoryAdjustBatch_list_header.jsp";
			}
			
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);	
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>库存调整==>批量调整失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	
	/**
	 * 批量调整出库异常调整单
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdjustOutboundExce(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);	
		String strDeleteStr = CommonTools.getStrToGb2312(request.getParameter("deleteStr"));
		try
		{
			PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
			List ls = null;
			String strUrl = null;
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			ICargoSpaceBus cargoSpaceBus = new CargoSpaceBusImpl(dao);
		
			String strUserCode = request.getSession().getAttribute("userCode").toString();
			String strUserName = request.getSession().getAttribute("userName").toString();
			String backmeg = "";//返回信息
			if(strDeleteStr != null && strDeleteStr.length() > 0)
			{
				String [] roleIds = strDeleteStr.split(",");
				for(int i=0; i<roleIds.length; i++)
				{
					List<InventoryAdjustDetail> lsajustDetail = new ArrayList<InventoryAdjustDetail>();
					List<InventoryStorage> lsinventory = new ArrayList<InventoryStorage>();//删除库存
//					List<InventoryStorage> addkcls = new ArrayList<InventoryStorage>();//新增库存集合
//					List<InoutJob> jobls = new ArrayList<InoutJob>();//作业集合（已完成）
//					List<InoutJobdetail> jobdells = new ArrayList<InoutJobdetail>();//作业明细
					List<BaseCargospace> cargospacesls = new ArrayList<BaseCargospace>();	//修改货位状态
					List<InventoryNeedcheck> lsinventoryNeedcheck = new ArrayList<InventoryNeedcheck>();	//修改异常单状态
					
					Object[] obj = null;
					//ID
					String strTemp =  roleIds[i];	
					
					InventoryAdjustHeader adjust= inventoryadjustbus2.getAdjustHeaderToId(strTemp);//获取调整单头
					List lsDetail = inventoryadjustbus2.getAdjustDetailListToId(strTemp);//获取调整单详细
				if (adjust.getStatus()!=null && adjust.getStatus().trim().equals("1")) {//只有审核才能调整
					if(lsDetail != null && lsDetail.size()>0)
					{
						for(int j = 0; j < lsDetail.size(); j++)
						{
							InventoryAdjustDetail detail = (InventoryAdjustDetail)lsDetail.get(j);	
							String status = detail.getStatus();
							
							if(status != null && status.trim().equals("1"))//未完成
							{
								//查找相应的存库
								InventoryStorage inventoryStorage = inventoryadjustbus2.getInventoryStorageInfoByCargoSpaceId(detail.getCargo_space_id());
								lsinventory.add(inventoryStorage);
								//查找相应的货位
								BaseCargospace baseCargospace = cargoSpaceBus.getCargoSpaceById(detail.getCargo_space_id());
								baseCargospace.setCsstatus("1");//修改为空货位
								cargospacesls.add(baseCargospace);
								
								//更新调整单的状态，调整时间
								//detail.setJobid(jobObj.getJobid());
								detail.setStatus("2");//已调整
								detail.setAdjusttime(StrTools.getCurrDateTime(5));//调整时间
								lsajustDetail.add(detail);		
								
								//更新异常单
								//获取异常单
								InventoryNeedcheck inventoryNeedcheck = inventoryadjustbus2.getInventoryNeedcheckInfoById(detail.getInventoryid());
								if(inventoryNeedcheck!=null){
									inventoryNeedcheck.setStatus("2");//更新为完成状态
									lsinventoryNeedcheck.add(inventoryNeedcheck);
								}
								
								
								
							}
						}
						//修改调整单状态等
						adjust.setAdjusttime(StrTools.getCurrDateTime(5));
						adjust.setStatus("3");//完成状态
						adjust.setAuditmanid(strUserCode);
						//日志
						SystemLogInfo sysLog = new SystemLogInfo();
						sysLog.setM_strLogCode(strUserCode);
						sysLog.setM_strLogName(strUserName);
						sysLog.setM_strModuleName("库存管理=>库存调整单");
						sysLog.setM_strContent("调整库存调整单成功");
						sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
						//执行调整，提交到数据库
						inventoryadjustbus2.createjustInvoice(adjust,lsajustDetail,lsinventory,cargospacesls,lsinventoryNeedcheck,sysLog);
						
		        		Logger.info("用户【" + strUserCode + "】调整了库存调整单成功，单据号：" + adjust.getAdjustid());
						
					}else {
						request.setAttribute("back_msg", "调整单:"+adjust.getAdjustid()+"没有可调整的信息！");
					}
					
				  }else {
					backmeg+= strTemp+",";
					request.setAttribute("back_msg", "调整单:"+backmeg+"是不是审核状态，不能进行调整！");
				}

				    
			 }
								
				if(pt != null)
				{
					//更新总记录数  
					int rows = pt.getM_iCountRow();
					pt.setPagingParameter(rows, 1, pt.getM_iMaxRow());
					
					ls = CommonPagination.getPagingList(dao, pt);
					
					strUrl = pt.getM_strUrl();
				}
			}
			if(strUrl == null)
			{
				strUrl = "/jsp/inventory/inventoryAdjustBatch_list_header.jsp";
			}
			
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);	
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>库存调整==>批量调整失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	
	
	
	/**
	 * 批量调整入库异常调整单
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdjustInboundExce(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);	
		String strDeleteStr = CommonTools.getStrToGb2312(request.getParameter("deleteStr"));
		try
		{
			PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
			List ls = null;
			String strUrl = null;
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			ICargoSpaceBus cargoSpaceBus = new CargoSpaceBusImpl(dao);
		
			String strUserCode = request.getSession().getAttribute("userCode").toString();
			String strUserName = request.getSession().getAttribute("userName").toString();
			String backmeg = "";//返回信息
			if(strDeleteStr != null && strDeleteStr.length() > 0)
			{
				String [] roleIds = strDeleteStr.split(",");
				for(int i=0; i<roleIds.length; i++)
				{
					List<InventoryAdjustDetail> lsajustDetail = new ArrayList<InventoryAdjustDetail>();
					List<InventoryStorage> lsinventory = new ArrayList<InventoryStorage>();//增加库存
//					List<InventoryStorage> addkcls = new ArrayList<InventoryStorage>();//新增库存集合
//					List<InoutJob> jobls = new ArrayList<InoutJob>();//作业集合（已完成）
//					List<InoutJobdetail> jobdells = new ArrayList<InoutJobdetail>();//作业明细
					List<BaseCargospace> cargospacesls = new ArrayList<BaseCargospace>();	//修改货位状态
					List<InventoryNeedcheck> lsinventoryNeedcheck = new ArrayList<InventoryNeedcheck>();	//修改异常单状态
					
					Object[] obj = null;
					//ID
					String strTemp =  roleIds[i];	
					
					InventoryAdjustHeader adjust= inventoryadjustbus2.getAdjustHeaderToId(strTemp);//获取调整单头
					List lsDetail = inventoryadjustbus2.getAdjustDetailListToId(strTemp);//获取调整单详细
				if (adjust.getStatus()!=null && adjust.getStatus().trim().equals("1")) {//只有审核才能调整
					if(lsDetail != null && lsDetail.size()>0)
					{
						for(int j = 0; j < lsDetail.size(); j++)
						{
							InventoryAdjustDetail detail = (InventoryAdjustDetail)lsDetail.get(j);	
							String status = detail.getStatus();
							
							if(status != null && status.trim().equals("1"))//未完成
							{
								//查找相应的存库
								InventoryStorage inventoryS = new InventoryStorage();
								inventoryS.setCargoSpaceId(detail.getCargo_space_id());
								//inventoryS.setCargoAlleyId(job.getTcargoAlleyId());
								inventoryS.setWhAreaId(detail.getWh_area_id());
								inventoryS.setWarehouseid(detail.getWarehouseid());
								inventoryS.setProductid(detail.getTproductid());
								inventoryS.setProductdate(detail.getTprintdate());
								inventoryS.setIndate(StrTools.getCurrDateTime(2));
								inventoryS.setLotid(detail.getTlotid());
								inventoryS.setLotinfo(detail.getTlotinfo());
								//inventoryS.setPackid(jobdetail.getPackid());
								inventoryS.setIntype("2"); //脱机
								inventoryS.setPunit(detail.getTpunit());
								inventoryS.setProductstatus("1");//物品状态
							//	inventoryS.setIsplit("1"); //整托
								inventoryS.setPnum(detail.getRealitypnum());//库存数量
//								inventoryS.setInjobid(job.getJobid());
								inventoryS.setTraycode(detail.getTtraycode());
								//inventoryS.setRequestid(job.getBoundrequeststockid()); //申请单
								//inventoryS.setInstockid(job.getBoundstockid());
								inventoryS.setProductcode(detail.getTproductcode());
								inventoryS.setStatus("0"); //未分配状态
								
								
								lsinventory.add(inventoryS);
								
								
								//查找相应的货位
								BaseCargospace baseCargospace = cargoSpaceBus.getCargoSpaceById(detail.getCargo_space_id());
								baseCargospace.setCsstatus("2");//修改为满货位
								cargospacesls.add(baseCargospace);
								
								//更新调整单详情的状态，调整时间
								//detail.setJobid(jobObj.getJobid());
								detail.setStatus("2");//已调整
								detail.setAdjusttime(StrTools.getCurrDateTime(5));//调整时间
								lsajustDetail.add(detail);		
								
								//更新异常单
								//获取异常单
								InventoryNeedcheck inventoryNeedcheck = inventoryadjustbus2.getInventoryNeedcheckInfoById(detail.getInventoryid());
								if(inventoryNeedcheck!=null){
									inventoryNeedcheck.setStatus("2");//更新为完成状态
									lsinventoryNeedcheck.add(inventoryNeedcheck);
								}
							}
						}
						//修改调整单状态等
						adjust.setAdjusttime(StrTools.getCurrDateTime(5));
						adjust.setStatus("3");//完成状态
						adjust.setAuditmanid(strUserCode);
						//日志
						SystemLogInfo sysLog = new SystemLogInfo();
						sysLog.setM_strLogCode(strUserCode);
						sysLog.setM_strLogName(strUserName);
						sysLog.setM_strModuleName("库存管理=>库存调整单");
						sysLog.setM_strContent("调整库存调整单成功");
						sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
						//执行调整，提交到数据库
						inventoryadjustbus2.createjustInvoice1(adjust,lsajustDetail,lsinventory,cargospacesls,lsinventoryNeedcheck,sysLog);
						
		        		Logger.info("用户【" + strUserCode + "】调整了库存调整单成功，单据号：" + adjust.getAdjustid());
						
					}else {
						request.setAttribute("back_msg", "调整单:"+adjust.getAdjustid()+"没有可调整的信息！");
					}
					
				  }else {
					backmeg+= strTemp+",";
					request.setAttribute("back_msg", "调整单:"+backmeg+"是不是审核状态，不能进行调整！");
				}

				    
			 }
								
				if(pt != null)
				{
					//更新总记录数  
					int rows = pt.getM_iCountRow();
					pt.setPagingParameter(rows, 1, pt.getM_iMaxRow());
					
					ls = CommonPagination.getPagingList(dao, pt);
					
					strUrl = pt.getM_strUrl();
				}
			}
			if(strUrl == null)
			{
				strUrl = "/jsp/inventory/inventoryAdjustBatch_list_header.jsp";
			}
			
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);	
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>库存调整==>批量调整失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 批量调整盘点调整单
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdjustCheckExce(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);	
		String strDeleteStr = CommonTools.getStrToGb2312(request.getParameter("deleteStr"));
		try
		{
			PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
			List ls = null;
			String strUrl = null;
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			ICargoSpaceBus cargoSpaceBus = new CargoSpaceBusImpl(dao);
			IInventoryCheckResultBus checkResultBus = new InventoryCheckResultBusImpl(dao);
		
			String strUserCode = request.getSession().getAttribute("userCode").toString();
			String strUserName = request.getSession().getAttribute("userName").toString();
			String backmeg = "";//返回信息
			if(strDeleteStr != null && strDeleteStr.length() > 0)
			{
				String [] roleIds = strDeleteStr.split(",");
				for(int i=0; i<roleIds.length; i++)
				{
					List<InventoryAdjustDetail> lsajustDetail = new ArrayList<InventoryAdjustDetail>();
					List<InventoryStorage> lsinventory = new ArrayList<InventoryStorage>();//修改库存
					List<BaseCargospace> cargospacesls = new ArrayList<BaseCargospace>();	//修改货位状态
					List<InventoryCheckResult> lscheckResult = new ArrayList<InventoryCheckResult>();	//修改盘点结果单状态
					
					Object[] obj = null;
					//ID
					String strTemp =  roleIds[i];	
					
					InventoryAdjustHeader adjust= inventoryadjustbus2.getAdjustHeaderToId(strTemp);//获取调整单头
					List lsDetail = inventoryadjustbus2.getAdjustDetailListToId(strTemp);//获取调整单详细
				if (adjust.getStatus()!=null && adjust.getStatus().trim().equals("1")) {//只有审核才能调整
					if(lsDetail != null && lsDetail.size()>0)
					{
						for(int j = 0; j < lsDetail.size(); j++)
						{
							InventoryAdjustDetail detail = (InventoryAdjustDetail)lsDetail.get(j);	
							String status = detail.getStatus();
							
							if(status != null && status.trim().equals("1"))//未完成
							{
								//查找相应的存库
								InventoryStorage inventoryS = inventoryadjustbus2.getInventoryInfoToId(detail.getInventoryid());
								inventoryS.setCargoSpaceId(detail.getCargo_space_id());
								//inventoryS.setCargoAlleyId(job.getTcargoAlleyId());
								inventoryS.setWhAreaId(detail.getWh_area_id());
								inventoryS.setWarehouseid(detail.getWarehouseid());
								inventoryS.setProductid(detail.getTproductid());
								inventoryS.setProductdate(detail.getTprintdate());
								inventoryS.setIndate(StrTools.getCurrDateTime(2));
								inventoryS.setLotid(detail.getTlotid());
								inventoryS.setLotinfo(detail.getTlotinfo());
								//inventoryS.setPackid(jobdetail.getPackid());
								inventoryS.setIntype("2"); //脱机
								inventoryS.setPunit(detail.getTpunit());
								inventoryS.setProductstatus("1");//物品状态
							//	inventoryS.setIsplit("1"); //整托
								inventoryS.setPnum(detail.getRealitypnum());//库存数量
//								inventoryS.setInjobid(job.getJobid());
								inventoryS.setTraycode(detail.getTtraycode());
								//inventoryS.setRequestid(job.getBoundrequeststockid()); //申请单
								//inventoryS.setInstockid(job.getBoundstockid());
								//inventoryS.setProductcode(detail.get);
								inventoryS.setStatus("0"); //未分配状态
								
								
								lsinventory.add(inventoryS);
								
								//盘点结果单
								InventoryCheckResult checkResult = checkResultBus.getCheckResultByInventoryId(detail.getInventoryid());
								checkResult.setStatus("3");// 1.新建，2.调整，3.完成
								lscheckResult.add(checkResult);
								
								//查找相应的货位
								BaseCargospace baseCargospace = cargoSpaceBus.getCargoSpaceById(detail.getCargo_space_id());
								baseCargospace.setCsstatus("2");//修改为满货位
								cargospacesls.add(baseCargospace);
								
								//更新调整单的状态，调整时间
								//detail.setJobid(jobObj.getJobid());
								detail.setStatus("2");//已调整
								detail.setAdjusttime(StrTools.getCurrDateTime(5));//调整时间
								lsajustDetail.add(detail);		
								
								//更新异常单
								//获取异常单
//								InventoryNeedcheck inventoryNeedcheck = inventoryadjustbus2.getInventoryNeedcheckInfoById(detail.getInventoryid());
//								if(inventoryNeedcheck!=null){
//									inventoryNeedcheck.setStatus("2");//更新为完成状态
//									lsinventoryNeedcheck.add(inventoryNeedcheck);
//								}
								
								
								
							}
						}
						//修改调整单状态等
						adjust.setAdjusttime(StrTools.getCurrDateTime(5));
						adjust.setStatus("3");//完成状态
						adjust.setAuditmanid(strUserCode);
						//日志
						SystemLogInfo sysLog = new SystemLogInfo();
						sysLog.setM_strLogCode(strUserCode);
						sysLog.setM_strLogName(strUserName);
						sysLog.setM_strModuleName("库存管理=>库存调整单");
						sysLog.setM_strContent("调整库存调整单成功");
						sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
						//执行调整，提交到数据库
						inventoryadjustbus2.createjustInvoice2(adjust,lsajustDetail,lsinventory,cargospacesls,lscheckResult,sysLog);
						
		        		Logger.info("用户【" + strUserCode + "】调整了库存调整单成功，单据号：" + adjust.getAdjustid());
						
					}else {
						request.setAttribute("back_msg", "调整单:"+adjust.getAdjustid()+"没有可调整的信息！");
					}
					
				  }else {
					backmeg+= strTemp+",";
					request.setAttribute("back_msg", "调整单:"+backmeg+"是不是审核状态，不能进行调整！");
				}

				    
			 }
								
				if(pt != null)
				{
					//更新总记录数  
					int rows = pt.getM_iCountRow();
					pt.setPagingParameter(rows, 1, pt.getM_iMaxRow());
					
					ls = CommonPagination.getPagingList(dao, pt);
					
					strUrl = pt.getM_strUrl();
				}
			}
			if(strUrl == null)
			{
				strUrl = "/jsp/inventory/inventoryAdjustBatch_list_header.jsp";
			}
			
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);	
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>库存调整==>批量调整失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 功能:库存调整单审核
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAuditAjustHeader(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		String strUserCode = request.getSession().getAttribute("userCode").toString();
		String strUserName = request.getSession().getAttribute("userName").toString();
		try{
		//代码(多个用逗号分隔)
		String strDeleteStr = CommonTools.getStrToGb2312(request.getParameter("deleteStr"));
		InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
		ICargoSpaceBus csBus = new CargoSpaceBusImpl(dao);
		PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
		List ls = null;
		String strUrl = null;
		if(strDeleteStr != null && strDeleteStr.length() > 0)
		{
			String [] roleIds = strDeleteStr.split(",");
			InventoryAdjustHeader adjust=null;
			List<InventoryAdjustDetail> adjustDetailLs = null;
			for(int i=0; i<roleIds.length; i++)
			{
				String strTemp =  roleIds[i];
				//获得调整单
				adjust=inventoryadjustbus2.getAdjustHeaderToId(strTemp);
			   //获得调整单明细
				adjustDetailLs = inventoryadjustbus2.getAdjustDetailListToId(adjust.getAdjustid());
				if(adjust!=null && adjust.getStatus().equals("0")){ // 库存调整单为创建状态
					if (adjustDetailLs!=null && adjustDetailLs.size()>0) {
						adjust.setAuditdate(StrTools.getCurrDateTime(5));
						adjust.setStatus("1");
						adjust.setAuditmanid(strUserCode);
						dao.update(adjust);
						
						//日志
						SystemLogInfo sysLog = new SystemLogInfo();
						sysLog.setM_strLogCode(strUserCode);
						sysLog.setM_strLogName(strUserName);
						sysLog.setM_strModuleName("库存管理=>库存调整单");
						sysLog.setM_strContent("审核库存调整单成功:单号为"+strTemp);
						sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
						dao.save(sysLog);				
						request.setAttribute("back_msg", "Y");
					}else {
						request.setAttribute("back_msg", "该库存调整单没有明细，不能审核！");
					}
					
				}else{
					request.setAttribute("back_msg", "库存调整单非创建状态");
				}	
			}

			if(pt != null)
			{
				//更新总记录数  
				int rows = pt.getM_iCountRow()-roleIds.length;
				pt.setPagingParameter(rows, 1, pt.getM_iMaxRow());
				ls = CommonPagination.getPagingList(dao, pt);
				strUrl = pt.getM_strUrl();
			}
		}
		if(strUrl == null)
		{
			strUrl = "/standard/jsp/inventory/adjust/kc_adjustheader_list.jsp";
		}
		
		request.setAttribute("exResultList", ls);
		request.setAttribute("pagingTool", pt);
		httpsession.setAttribute("paging", pt);
		
		request.getRequestDispatcher(strUrl).forward(request, response);

		}catch(Exception er)
		{
			Logger.error("库存管理==>库存调整==>库存调整单审核失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * 功能：盘点调整单明细中是否存在要调整的货位
	 * @param cs 要调整的货位
	 * @param csLs  调整明细集合
	 * @return
	 */
	boolean isCargoSpace(String cs,List<InventoryAdjustDetail> csLs) {
		boolean isHavaCs = false;
		if (csLs!=null && csLs.size()>0) {
			for (InventoryAdjustDetail del : csLs) {
				if (del.getCargo_space_id().equals(cs)) {
					isHavaCs = true;
					break;
				}
			}
		}
		return isHavaCs;
	}
	
	
	
	
	
	
	/**
	 * 批量调整库存信息
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdjustInventoryinfoExce(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);	
		String strDeleteStr = CommonTools.getStrToGb2312(request.getParameter("deleteStr"));
		try
		{
			PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
			List ls = null;
			String strUrl = null;
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			ICargoSpaceBus cargoSpaceBus = new CargoSpaceBusImpl(dao);
		
			String strUserCode = request.getSession().getAttribute("userCode").toString();
			String strUserName = request.getSession().getAttribute("userName").toString();
			String backmeg = "";//返回信息
			if(strDeleteStr != null && strDeleteStr.length() > 0)
			{
				String [] roleIds = strDeleteStr.split(",");
				for(int i=0; i<roleIds.length; i++)
				{
					List<InventoryAdjustDetail> lsajustDetail = new ArrayList<InventoryAdjustDetail>();
					List<InventoryStorage> lsinventory = new ArrayList<InventoryStorage>();//修改库存
					List<BaseCargospace> cargospacesls = new ArrayList<BaseCargospace>();	//修改货位状态
					//List<InventoryNeedcheck> lsinventoryNeedcheck = new ArrayList<InventoryNeedcheck>();	//修改异常单状态
					
					Object[] obj = null;
					//ID
					String strTemp =  roleIds[i];	
					
					InventoryAdjustHeader adjust= inventoryadjustbus2.getAdjustHeaderToId(strTemp);//获取调整单头
					List lsDetail = inventoryadjustbus2.getAdjustDetailListToId(strTemp);//获取调整单详细
				   if (adjust.getStatus()!=null && adjust.getStatus().trim().equals("1")) {//只有审核才能调整
					if(lsDetail != null && lsDetail.size()>0)
					{
						for(int j = 0; j < lsDetail.size(); j++)
						{
							InventoryAdjustDetail detail = (InventoryAdjustDetail)lsDetail.get(j);	
							String status = detail.getStatus();
							
							if(status != null && status.trim().equals("1"))//未完成
							{
								//查找相应的存库
								InventoryStorage inventoryS = inventoryadjustbus2.getInventoryInfoToId(detail.getInventoryid());
								if(inventoryS!=null){//修改库存
									inventoryS.setCargoSpaceId(detail.getCargo_space_id());
									//inventoryS.setCargoAlleyId(job.getTcargoAlleyId());
									inventoryS.setWhAreaId(detail.getWh_area_id());
									inventoryS.setWarehouseid(detail.getWarehouseid());
									inventoryS.setProductid(detail.getTproductid());
									inventoryS.setProductdate(detail.getTprintdate());
									inventoryS.setIndate(StrTools.getCurrDateTime(2));
									inventoryS.setLotid(detail.getTlotid());
									inventoryS.setLotinfo(detail.getTlotinfo());
									//inventoryS.setPackid(jobdetail.getPackid());
									inventoryS.setIntype("2"); //脱机
									inventoryS.setPunit(detail.getTpunit());
									inventoryS.setProductstatus("1");//物品状态
								//	inventoryS.setIsplit("1"); //整托
									inventoryS.setPnum(detail.getRealitypnum());//库存数量
//									inventoryS.setInjobid(job.getJobid());
									inventoryS.setTraycode(detail.getTtraycode());
									//inventoryS.setRequestid(job.getBoundrequeststockid()); //申请单
									//inventoryS.setInstockid(job.getBoundstockid());
									//inventoryS.setProductcode(detail.get);
									inventoryS.setStatus("0"); //未分配状态
									lsinventory.add(inventoryS);
									
								}else{//添加库存
									//---------------添加库存-----------------------
									InventoryStorage inventoryS1 = new InventoryStorage();
									inventoryS1.setCargoSpaceId(detail.getCargo_space_id());
									//inventoryS1.setCargoAlleyId(job.getTcargoAlleyId());
									inventoryS1.setWhAreaId(detail.getWh_area_id());
									inventoryS1.setWarehouseid(detail.getWarehouseid());
									inventoryS1.setProductid(detail.getTproductid());
									inventoryS1.setProductdate(detail.getTprintdate());
									inventoryS1.setIndate(StrTools.getCurrDateTime(2));
									inventoryS1.setLotid(detail.getTlotid());
									inventoryS1.setLotinfo(detail.getTlotinfo());
									//inventoryS1.setPackid(jobdetail.getPackid());
									inventoryS1.setIntype("2"); //脱机
									inventoryS1.setPunit(detail.getTpunit());
									inventoryS1.setProductcode(detail.getTproductcode());//产品编码
									inventoryS1.setProductstatus("1");//物品状态
									inventoryS1.setPnum(detail.getRealitypnum());//库存数量
									inventoryS1.setTraycode(detail.getTtraycode());
									inventoryS1.setStatus("0"); //未分配状态
									lsinventory.add(inventoryS1);
								}
								//查找相应的货位
								BaseCargospace baseCargospace = cargoSpaceBus.getCargoSpaceById(detail.getCargo_space_id());
								String ss="";
								if(adjust.getWarehouseid().equals("001")){
									ss = StandardConstant.zcidkzl;
								}
								if(baseCargospace.getWhAreaId().equals(adjust.getWarehouseid()+ss)){ //为暂存区 不需要更改货位状态
								}else{
									if(detail.getRealitypnum()<=0){
										baseCargospace.setCsstatus("1");
									}else{
										baseCargospace.setCsstatus("2");
									}
									cargospacesls.add(baseCargospace);
								}
								
								
								//更新调整单的状态，调整时间
								//detail.setJobid(jobObj.getJobid());
								detail.setStatus("2");//已调整
								detail.setAdjusttime(StrTools.getCurrDateTime(5));//调整时间
								lsajustDetail.add(detail);		
								
								//更新异常单
								//获取异常单
//								InventoryNeedcheck inventoryNeedcheck = inventoryadjustbus2.getInventoryNeedcheckInfoById(detail.getInventoryid());
//								if(inventoryNeedcheck!=null){
//									inventoryNeedcheck.setStatus("2");//更新为完成状态
//									lsinventoryNeedcheck.add(inventoryNeedcheck);
//								}
								
								
								
							}
						}
						//修改调整单状态等
						adjust.setAdjusttime(StrTools.getCurrDateTime(5));
						adjust.setStatus("3");//完成状态
						adjust.setAuditmanid(strUserCode);
						//日志
						SystemLogInfo sysLog = new SystemLogInfo();
						sysLog.setM_strLogCode(strUserCode);
						sysLog.setM_strLogName(strUserName);
						sysLog.setM_strModuleName("库存管理=>库存调整单");
						sysLog.setM_strContent("调整库存调整单成功");
						sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
						//执行调整，提交到数据库
						inventoryadjustbus2.createjustInvoice3(adjust,lsajustDetail,lsinventory,cargospacesls,sysLog);
						
		        		Logger.info("用户【" + strUserCode + "】调整了库存调整单成功，单据号：" + adjust.getAdjustid());
						
					}else {
						request.setAttribute("back_msg", "调整单:"+adjust.getAdjustid()+"没有可调整的信息！");
					}
					
				  }else {
					backmeg+= strTemp+",";
					request.setAttribute("back_msg", "调整单:"+backmeg+"是不是审核状态，不能进行调整！");
				}

				    
			 }
								
				if(pt != null)
				{
					//更新总记录数  
					int rows = pt.getM_iCountRow();
					pt.setPagingParameter(rows, 1, pt.getM_iMaxRow());
					
					ls = CommonPagination.getPagingList(dao, pt);
					
					strUrl = pt.getM_strUrl();
				}
			}
			if(strUrl == null)
			{
				strUrl = "/jsp/inventory/inventoryAdjustBatch_list_header.jsp";
			}
			
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);	
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>库存调整==>批量调整失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
}
