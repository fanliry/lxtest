package com.wms3.bms.standard.action.inventory;

import java.util.ArrayList;
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
import com.ricosoft.common.tools.StrTools;
import com.ricosoft.entity.competenceMgr.SystemLogInfo;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.inventory.InventoryAdjustBus;
import com.wms3.bms.standard.business.inventory.ProductAdjustBus;
import com.wms3.bms.standard.business.inventory.impl.InventoryAdjustBusImpl;
import com.wms3.bms.standard.business.inventory.impl.ProductAdjustBusImpl;
import com.wms3.bms.standard.dao.base.impl.BaseProductDaoImpl;
import com.wms3.bms.standard.dao.base.impl.BaseSeqDaoImpl;
import com.wms3.bms.standard.dao.inventory.impl.ProductAdjustDaoImpl;
import com.wms3.bms.standard.entity.base.BaseProduct;
import com.wms3.bms.standard.entity.base.BaseSeq;
import com.wms3.bms.standard.entity.inventory.InventoryAdjustDetail;
import com.wms3.bms.standard.entity.inventory.InventoryTransferDetail;
import com.wms3.bms.standard.entity.inventory.InventoryTransferHeader;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;



/**
 *  描述:物品调整单
 * @author yao
 *
 */
public class ProductAdjustAction extends AbstractAction
{
	protected InventoryAdjustBus inventoryadjustbus;
	
	protected  ProductAdjustBus productadjustbus;
	protected int maxLine = 5;			//分页显示的行数；
	/**
	 * 查询货品调整单
	 */
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		//客户
		String strCustomer = CommonTools.getStrToGb2312(request.getParameter("customerid"));
		//状态
		String strStatus = CommonTools.getStrToGb2312(request.getParameter("status"));
		productadjustbus = new ProductAdjustBusImpl(dao);
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
		if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }

		try
		{	
			List ls = null;
			//查询的SQL语句
			String strQuerySQL = productadjustbus.getProductAdjustHeaderSQL(strStatus, strCustomer);
			//查询角色总记录数的SQL语句
			String strCountSQL = productadjustbus.getProductAdjustHeaderCountSQL(strStatus, strCustomer);
			String strUrl = "/standard/jsp/inventory/productadjust/product_adjustheader_list.jsp";	
			PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, strUrl ,1, maxLine);
			//查询结果
			ls = pt.getLsResult();
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);	

			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>货品调整==>货品调整单查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * 功能:货物调整单详细查询
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
		ProductAdjustBusImpl productadjustbus1 = new ProductAdjustBusImpl(dao);
		try
		{	
			List ls = null;
			//查询的SQL语句
			String strQuerySQL = productadjustbus1.getProductAdjustDetailSQL(strId);
			//查询角色总记录数的SQL语句
			String strCountSQL = productadjustbus1.getProductAdjustDetailCountSQL(strId);
			String strUrl = "/standard/jsp/inventory/productadjust/product_adjustdetail_list.jsp";
			PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, strUrl ,1, maxLine);
			//查询结果
			ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);

			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>货物调整==>货物调整单详细查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * 功能:增加货品调整单
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
		//客户
		String strCustomer = CommonTools.getStrToGb2312(request.getParameter("customerid"));
		//原因代码
		String strReasoncode = CommonTools.getStrToGb2312(request.getParameter("reasoncode"));
		//原因
		String strReasondiscr = CommonTools.getStrToGb2312(request.getParameter("reasondiscr"));
		//单证类型
		String strDoctype = CommonTools.getStrToGb2312(request.getParameter("doctype"));
	    
		
		String strUserCode = request.getSession().getAttribute("userCode").toString();
		String strUserName = request.getSession().getAttribute("userName").toString();
		try
		{
			ProductAdjustDaoImpl DaoImpl = new ProductAdjustDaoImpl(dao);
			InventoryTransferHeader adjust = new InventoryTransferHeader();
			//货品调整单号
			BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(dao);
            BaseSeq  seqEn = seqDao.getSeqByType("PDD");
            String strInvoiceNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), dao);
           
			adjust.setCustomerid(strCustomer);
			adjust.setStatus(strStatus);
			adjust.setReasoncode(strReasoncode);
			adjust.setReasondiscr(strReasondiscr);
			adjust.setTransferid(strInvoiceNo);
			adjust.setDoctype(strDoctype);
			adjust.setCreatetime(StrTools.getCurrDateTime(5));
			adjust.setCreatemanid(strUserName);
			DaoImpl.addProductAdjust(adjust);
			
			//日志
			SystemLogInfo sysLog = new SystemLogInfo();
			
			sysLog.setM_strLogCode(strUserCode);
			sysLog.setM_strLogName(strUserName);
			sysLog.setM_strModuleName("库存管理=>货品调整");
			sysLog.setM_strContent("添加货品调整单成功");
			sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
			dao.save(sysLog);
		
			List ls = null;
			//查询的SQL语句
			productadjustbus = new ProductAdjustBusImpl(dao);
			String strQuerySQL = productadjustbus.getProductAdjustHeaderSQL(strStatus, strCustomer);
			//查询角色总记录数的SQL语句
			String strCountSQL = productadjustbus.getProductAdjustHeaderCountSQL(strStatus, strCustomer);
			String strUrl = "/standard/jsp/inventory/productadjust/product_adjustheader_list.jsp";	
			PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, strUrl ,1, maxLine);
			//查询结果
			ls = pt.getLsResult();
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);	
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>货品调整==>货品调整单增加失败:" + er.getMessage());
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
			ProductAdjustBusImpl productadjustbus1 = new ProductAdjustBusImpl(dao);
			ProductAdjustDaoImpl DaoImpl = new ProductAdjustDaoImpl(dao);
			if(strDeleteStr != null && strDeleteStr.length() > 0)
			{
				String [] roleIds = strDeleteStr.split(",");
				InventoryTransferHeader adjust=null;
				for(int i=0; i<roleIds.length; i++)
				{
					String strTemp =  roleIds[i];
					//删除
					adjust=productadjustbus1.getProductAdjustHeaderToId(strTemp);
					if(adjust!=null && adjust.getStatus().equals("0")){ // 库存调整单为创建状态
						List lsDetail = productadjustbus1.getProductAdjustDetailListToId(strTemp);
						List lsinventory = new ArrayList();
						if(lsDetail != null)
						{
							for(int j = 0; j < lsDetail.size(); j++)
							{
								    InventoryAdjustDetail detail = (InventoryAdjustDetail)lsDetail.get(j);
									//库存
									InventoryStorage inventory = productadjustbus1.getInventoryInfoToId(detail.getInventoryid());
									if(inventory != null)
									{
										inventory.setStatus("0");//未分配
									}
									lsinventory.add(inventory);
							}
						}
						
						productadjustbus1.deletejustInvoice(lsDetail, lsinventory, adjust);
						//日志
						SystemLogInfo sysLog = new SystemLogInfo();
						String strUserCode = request.getSession().getAttribute("userCode").toString();
						String strUserName = request.getSession().getAttribute("userName").toString();
						sysLog.setM_strLogCode(strUserCode);
						sysLog.setM_strLogName(strUserName);
						sysLog.setM_strModuleName("库存管理=>货品调整单");
						sysLog.setM_strContent("删除库存调整单成功:单号为"+strTemp);
						sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
						dao.save(sysLog);				
						request.setAttribute("back_msg", "Y");
					}else{
						request.setAttribute("back_msg", "货品调整单非创建状态");
					}	
				}
			}
			//查询的SQL语句
			productadjustbus = new ProductAdjustBusImpl(dao);
			String strQuerySQL = productadjustbus.getProductAdjustHeaderSQL("", "");
			//查询角色总记录数的SQL语句
			String strCountSQL = productadjustbus.getProductAdjustHeaderCountSQL("", "");
			String strUrl = "/standard/jsp/inventory/productadjust/product_adjustheader_list.jsp";	
			PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, strUrl ,1, maxLine);
			//查询结果
			List ls = pt.getLsResult();
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
			ProductAdjustBusImpl productadjustbus1 = new ProductAdjustBusImpl(dao);
			
			InventoryTransferHeader adjust = productadjustbus1.getProductAdjustHeaderToId(strId);
			
			request.setAttribute("adjust", adjust);
			request.getRequestDispatcher("/standard/jsp/inventory/productadjust/product_adjust_header_update.jsp").forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>货品调整==>根据ID获得货品调整单失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}	
		return null;
	}
	/**
	 * 功能:更新货品调整单
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
		//客户
		String strCustomer = CommonTools.getStrToGb2312(request.getParameter("customerid"));
		//原因代码
		String strReasoncode = CommonTools.getStrToGb2312(request.getParameter("reasoncode"));
		//原因
		String strReasondiscr = CommonTools.getStrToGb2312(request.getParameter("reasondiscr"));
		try
		{
			ProductAdjustBusImpl productadjustbus1 = new ProductAdjustBusImpl(dao);
			ProductAdjustDaoImpl DaoImpl = new ProductAdjustDaoImpl(dao);
			InventoryTransferHeader adjust= productadjustbus1.getProductAdjustHeaderToId(strId);
			if(adjust != null)
			{
				adjust.setCustomerid(strCustomer);
				adjust.setReasoncode(strReasoncode);
				adjust.setReasondiscr(strReasondiscr);
				adjust.setTransfertime(StrTools.getCurrDateTime(5));
				DaoImpl.updateProductAdjust(adjust);
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
			
			//查询的SQL语句
			productadjustbus = new ProductAdjustBusImpl(dao);
			String strQuerySQL = productadjustbus.getProductAdjustHeaderSQL("", "");
			//查询角色总记录数的SQL语句
			String strCountSQL = productadjustbus.getProductAdjustHeaderCountSQL("", "");
			String strUrl = "/standard/jsp/inventory/productadjust/product_adjustheader_list.jsp";	
			PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, strUrl ,1, maxLine);
			//查询结果
			List ls = pt.getLsResult();
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);	
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>货品调整==>货品调整单修改失败:" + er.getMessage());
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
		
		
		String strwarehouseid = CommonTools.getStrToGb2312(request.getParameter("warehouseid"));//仓库
		String strwhAreaId = CommonTools.getStrToGb2312(request.getParameter("whAreaId")); //库区
		String strcargoAreaId = CommonTools.getStrToGb2312(request.getParameter("cargoAreaId"));//货区
		String strCustomerId = CommonTools.getStrToGb2312(request.getParameter("customer_id"));//客户
		String strfmpackid = CommonTools.getStrToGb2312(request.getParameter("fmpackid"));//包装
		String strfmpunit = CommonTools.getStrToGb2312(request.getParameter("fmpunit"));//单位
		String strproductid = CommonTools.getStrToGb2312(request.getParameter("productid"));//产品
		String strLotid = CommonTools.getStrToGb2312(request.getParameter("lotid"));//批次属性
		String strfmtraycode = CommonTools.getStrToGb2312(request.getParameter("fmtraycode"));//托盘条码
		
		String lt1 = CommonTools.getStrToGb2312(request.getParameter("lt1"));  //批次属性段1
		String lt2 = CommonTools.getStrToGb2312(request.getParameter("lt2"));  //批次属性段2
		String lt3 = CommonTools.getStrToGb2312(request.getParameter("lt3"));  //批次属性段3
		String lt4 = CommonTools.getStrToGb2312(request.getParameter("lt4"));  //批次属性段4
		String lt5 = CommonTools.getStrToGb2312(request.getParameter("lt5"));  //批次属性段5
		String lt6 = CommonTools.getStrToGb2312(request.getParameter("lt6"));  //批次属性段6
		String lt7 = CommonTools.getStrToGb2312(request.getParameter("lt7"));  //批次属性段7
		String lt8 = CommonTools.getStrToGb2312(request.getParameter("lt8"));  //批次属性段8
		String lt9 = CommonTools.getStrToGb2312(request.getParameter("lt9"));  //批次属性段9
		String lt10 = CommonTools.getStrToGb2312(request.getParameter("lt10"));//批次属性段10
		String lt11 = CommonTools.getStrToGb2312(request.getParameter("lt11"));//批次属性段11
		String lt12 = CommonTools.getStrToGb2312(request.getParameter("lt12"));//批次属性段12
		ProductAdjustBusImpl productadjustbus1 = new ProductAdjustBusImpl(dao);
		ProductAdjustDaoImpl DaoImpl = new ProductAdjustDaoImpl(dao);
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
		if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		try
		{
			List ls = null;
			//查询的SQL语句
			String strQuerySQL = productadjustbus1.getInventoryToSQL(strwarehouseid,"0",strCustomerId,strwhAreaId,strcargoAreaId,strfmpackid,strfmpunit,strproductid,strLotid,strfmtraycode,lt1,lt2,lt3,lt4,lt5,lt6,lt7,lt8,lt9,lt10,lt11,lt12);
			//查询角色总记录数的SQL语句
			String strCountSQL = productadjustbus1.getInventoryToCountSQL(strwarehouseid,"0",strCustomerId,strwhAreaId,strcargoAreaId,strfmpackid,strfmpunit,strproductid,strLotid,strfmtraycode,lt1,lt2,lt3,lt4,lt5,lt6,lt7,lt8,lt9,lt10,lt11,lt12);

			String strUrl = "/standard/jsp/inventory/productadjust/product_list_detail.jsp";
			PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, strUrl ,1, maxLine);
			//查询结果
			ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);	
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>货品调整==>明细==>库存查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}

		return null;
		
	}
	//****调整单详细*******************************************************
	/**
	 * 功能:增加调整单详细
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
		//库存ID系列
		String strInventoryids = CommonTools.getStrToGb2312(request.getParameter("param"));
		String strTocustomerid = CommonTools.getStrToGb2312(request.getParameter("customer_id"));
		String strToproductid = CommonTools.getStrToGb2312(request.getParameter("productid"));
		String strTopackid = CommonTools.getStrToGb2312(request.getParameter("topackid"));
		String strTopunit = CommonTools.getStrToGb2312(request.getParameter("topunit"));
		String strTotraycode = CommonTools.getStrToGb2312(request.getParameter("totraycode"));
		
		String strLotatt1 = CommonTools.getStrToGb2312(request.getParameter("Lotatt1"));
		String strLotatt2 = CommonTools.getStrToGb2312(request.getParameter("Lotatt2"));
		String strLotatt3 = CommonTools.getStrToGb2312(request.getParameter("Lotatt3"));
		String strLotatt4 = CommonTools.getStrToGb2312(request.getParameter("Lotatt4"));
		String strLotatt5 = CommonTools.getStrToGb2312(request.getParameter("Lotatt5"));
		String strLotatt6 = CommonTools.getStrToGb2312(request.getParameter("Lotatt6"));
		String strLotatt7 = CommonTools.getStrToGb2312(request.getParameter("Lotatt7"));
		String strLotatt8 = CommonTools.getStrToGb2312(request.getParameter("Lotatt8"));
		String strLotatt9 = CommonTools.getStrToGb2312(request.getParameter("Lotatt9"));
		String strLotatt10 = CommonTools.getStrToGb2312(request.getParameter("Lotatt10"));
		String strLotatt11 = CommonTools.getStrToGb2312(request.getParameter("Lotatt11"));
		String strLotatt12 = CommonTools.getStrToGb2312(request.getParameter("Lotatt12"));
		ProductAdjustBusImpl productadjustbus1 = new ProductAdjustBusImpl(dao);
	
		try
		{
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			String[]  inventoryids= strInventoryids.split(",");
			for(int i=0;i<inventoryids.length;i++){
				InventoryStorage inventory = inventoryadjustbus2.getInventoryInfoToId(inventoryids[i]);
				if(inventory != null && inventory.getStatus().equals("0"))  //库存为未分配状态
				{
					InventoryTransferDetail adjust = new InventoryTransferDetail();
					adjust.setTransferid(strId);
					adjust.setStatus("0");
					adjust.setInventoryid(inventory.getInventoryid());
					adjust.setCargo_space_id(inventory.getCargoSpaceId());
					adjust.setWh_area_id(inventory.getCargoAreaId());
					adjust.setFmcustomerid(inventory.getOwnerId());
					adjust.setFmpackid(inventory.getPackid());
					adjust.setFmpunit(inventory.getPunit());
					adjust.setFmproductid(inventory.getProductid());
					adjust.setFmlotid(inventory.getLotid());
					adjust.setFmtraycode(inventory.getTraycode());
					adjust.setFmlotatt1(inventory.getLotatt1());
					adjust.setFmlotatt2(inventory.getLotatt2());
					adjust.setFmlotatt3(inventory.getLotatt3());
					adjust.setFmlotatt4(inventory.getLotatt4());
					adjust.setFmlotatt5(inventory.getLotatt5());
					adjust.setFmlotatt6(inventory.getLotatt6());
					adjust.setFmlotatt7(inventory.getLotatt7());
					adjust.setFmlotatt8(inventory.getLotatt8());
					adjust.setFmlotatt9(inventory.getLotatt9());
					adjust.setFmlotatt10(inventory.getLotatt10());
					adjust.setFmlotatt11(inventory.getLotatt11());
					adjust.setFmlotatt12(inventory.getLotatt12());
					adjust.setPnum(inventory.getPnum());
					adjust.setPvolume(inventory.getPvolume());
					adjust.setPnetweight(inventory.getPnetweight());
					adjust.setPweight(inventory.getPweight());
					
					adjust.setTocustomerid(strTocustomerid);
					adjust.setTopackid(strTopackid);
					adjust.setTopunit(strTopunit);
					adjust.setToproductid(strToproductid);
					adjust.setTolotid(inventory.getLotid());
					adjust.setTotraycode(strTotraycode);
					adjust.setTolotatt1(strLotatt1);
					adjust.setTolotatt2(strLotatt2);
					adjust.setTolotatt3(strLotatt3);
					adjust.setTolotatt4(strLotatt4);
					adjust.setTolotatt5(strLotatt5);
					adjust.setTolotatt6(strLotatt6);
					adjust.setTolotatt7(strLotatt7);
					adjust.setTolotatt8(strLotatt8);
					adjust.setTolotatt9(strLotatt9);
					adjust.setTolotatt10(strLotatt10);
					adjust.setTolotatt11(strLotatt11);
					adjust.setTolotatt12(strLotatt12);
				
					dao.save(adjust);	
					//更新库存，将库存状态调整为货品调整状态
					inventory.setStatus("4"); //货品调整状态
					dao.update(inventory);	
					request.setAttribute("back_msg", "Y");
					//日志
					SystemLogInfo sysLog = new SystemLogInfo();
					String strUserCode = request.getSession().getAttribute("userCode").toString();
					String strUserName = request.getSession().getAttribute("userName").toString();
					sysLog.setM_strLogCode(strUserCode);
					sysLog.setM_strLogName(strUserName);
					sysLog.setM_strModuleName("库存管理=>货品调整");
					sysLog.setM_strContent("货品调整单"+strId+":添加货品调整单详细成功,库存为"+inventory.getCargoSpaceId());
					sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
					dao.save(sysLog);
				}else{
					request.setAttribute("back_msg", "当中的某个库存不为未分配状态，不能进行货品调整");
				}	
			}
			
			
			//查询的SQL语句
			String strQuerySQL = productadjustbus1.getProductAdjustDetailSQL(strId);
			strUrl = "/standard/jsp/inventory/productadjust/product_adjustdetail_list.jsp";
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
			
			ProductAdjustBusImpl productadjustbus = new ProductAdjustBusImpl(dao);
			InventoryTransferHeader adjust = productadjustbus.getProductAdjustHeaderToId(strId);
			String strUrl = null;
			if(strDeleteStr != null && strDeleteStr.length() > 0 && adjust.getStatus().equals("0")) //创建状态
			{
				String [] roleIds = strDeleteStr.split(",");
				for(int i=0; i<roleIds.length; i++)
				{
					//删除
					String strTemp =  roleIds[i];
					//inventoryadjustbus2.deleteAdjustDetail(strTemp);
					InventoryTransferDetail detail = productadjustbus.getProductAdjustDetailToId(strTemp);
					InventoryStorage inventory = productadjustbus.getInventoryInfoToId(detail.getInventoryid());	
					dao.delete(detail);
					inventory.setStatus("0"); //还原未分配状态
					dao.update(inventory);
				}
				request.setAttribute("back_msg", "Y");
				
			}else{
				request.setAttribute("back_msg", "只有创建状态才能删除");
			}
			//查询的SQL语句
			List ls = productadjustbus.getProductAdjustDetailListToId(strId);
			strUrl = "/standard/jsp/inventory/productadjust/product_adjustdetail_list.jsp";
			//查询结果
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
	
		}catch(Exception er)
		{
			Logger.error("库存管理==>货品调整==>货品调整单详细删除失败:" + er.getMessage());
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
			BaseProductDaoImpl skuMgr = new BaseProductDaoImpl(dao);
			/*InventoryAdjustDetail detail = inventoryadjustbus2.getAdjustDetailToId(strId);
			BaseProduct sku = skuMgr.getProductById(detail.getProductid());
			
			request.setAttribute("adjustdetail", detail);
			request.setAttribute("sku", sku);*/
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
		String id="";
		//调整单明细ID
		String strId = CommonTools.getStrToGb2312(request.getParameter("detailId"));

		//目标数量
		String strToQty = CommonTools.getStrToGb2312(request.getParameter("toqty"));
		//目标毛重
		String strToGrossweight = CommonTools.getStrToGb2312(request.getParameter("togrossweight"));
		//目标体积
		String strToCubic = CommonTools.getStrToGb2312(request.getParameter("tocubic"));
		//目标净重
		String strToNetweight = CommonTools.getStrToGb2312(request.getParameter("tonetweight"));
		try
		{
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			InventoryAdjustDetail detail = null;//inventoryadjustbus2.getAdjustDetailToId(strId);
			id = detail.getAdjustid();
			if(detail != null && detail.getStatus().equals("0")) //单据详细状态为创建状态
			{
				detail.setTonum(Float.parseFloat(strToQty));
				if(strToCubic != null && strToCubic.trim().length() > 0)
				{
					detail.setTovolume(Float.parseFloat(strToCubic));	
				}
				if(strToGrossweight != null && strToGrossweight.trim().length() > 0)
				{
					detail.setToweight(Float.parseFloat(strToGrossweight));
				}
				if(strToNetweight != null && strToNetweight.trim().length() > 0)
				{
					detail.setTonetweight(Float.parseFloat(strToNetweight));
				}
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
			String strQuerySQL = inventoryadjustbus2.getAdjustDetailSQL(id);
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
			ProductAdjustBusImpl productadjustbus = new ProductAdjustBusImpl(dao);
			String strUserCode = request.getSession().getAttribute("userCode").toString();
			String strUserName = request.getSession().getAttribute("userName").toString();
			if(strDeleteStr != null && strDeleteStr.length() > 0)
			{
				String [] roleIds = strDeleteStr.split(",");
				for(int i=0; i<roleIds.length; i++)
				{
					List lsajustDetail = new ArrayList();
					List lsinventory = new ArrayList();
					List lstrans = new ArrayList();
					//ID
					String strTemp =  roleIds[i];
					
					InventoryTransferHeader adjust= productadjustbus.getProductAdjustHeaderToId(strTemp);
					List lsDetail = productadjustbus.getProductAdjustDetailListToId(strTemp);
					if(lsDetail != null)
					{
						for(int j = 0; j < lsDetail.size(); j++)
						{
							InventoryTransferDetail detail = (InventoryTransferDetail)lsDetail.get(j);
							
							String status = detail.getStatus();
							if(status != null && !status.trim().equals("1"))
							{
								//库存ID
								String strId = detail.getInventoryid();
								//库存
								InventoryStorage inventory = productadjustbus.getInventoryInfoToId(strId);
								if(inventory != null)
								{
									
									inventory.setOwnerId(detail.getTocustomerid());
									inventory.setPackid(detail.getTopackid());
									inventory.setPunit(detail.getTopunit());
									inventory.setProductid(detail.getToproductid());
									inventory.setLotid(detail.getTolotid());
									inventory.setTraycode(detail.getTotraycode());
									inventory.setLotatt1(detail.getTolotatt1());
									inventory.setLotatt2(detail.getTolotatt2());
									inventory.setLotatt3(detail.getTolotatt3());
									inventory.setLotatt4(detail.getTolotatt4());
									inventory.setLotatt5(detail.getTolotatt5());
									inventory.setLotatt6(detail.getTolotatt6());
									inventory.setLotatt7(detail.getTolotatt7());
									inventory.setLotatt8(detail.getTolotatt8());
									inventory.setLotatt9(detail.getTolotatt9());
									inventory.setLotatt10(detail.getTolotatt10());
									inventory.setLotatt11(detail.getTolotatt11());
									inventory.setLotatt12(detail.getTolotatt12());
									inventory.setStatus("0");//未分配
								}
								detail.setTransfertime(StrTools.getCurrDateTime(5));
								detail.setStatus("1");
								lsajustDetail.add(detail);
								lsinventory.add(inventory);
								
							}
						}
					}
					//修改货品调整单状态等
					adjust.setTransfertime(StrTools.getCurrDateTime(5));
					adjust.setStatus("1");
					productadjustbus.createjustInvoice(lsajustDetail, lsinventory, adjust);
            		Logger.info("用户【" + strUserCode + "】生成了货品调整单，单据号：" + adjust.getTransferid());
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
				strUrl = "/standard/jsp/inventory/productadjust/product_adjustheader_list.jsp";
			}
			
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);	
			
		}catch(Exception er)
		{
			Logger.error("库存管理==>货品调整==>货品批量调整失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

}
