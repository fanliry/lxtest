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
import com.wms3.bms.standard.business.inventory.impl.InventoryAdjustBusImpl;
import com.wms3.bms.standard.dao.base.impl.BaseProductDaoImpl;
import com.wms3.bms.standard.dao.base.impl.BaseSeqDaoImpl;
import com.wms3.bms.standard.entity.base.BaseProduct;
import com.wms3.bms.standard.entity.base.BaseSeq;
import com.wms3.bms.standard.entity.inventory.InventoryAdjustDetail;
import com.wms3.bms.standard.entity.inventory.InventoryAdjustHeader;
import com.wms3.bms.standard.entity.inventory.InventoryTransaction;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;



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
		//客户
		String strCustomer = CommonTools.getStrToGb2312(request.getParameter("customerid"));
		//状态
		String strStatus = CommonTools.getStrToGb2312(request.getParameter("status"));
		inventoryadjustbus = new InventoryAdjustBusImpl(dao);
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
		if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }

		try
		{	
			/*List ls = null;
			//查询的SQL语句
			String strQuerySQL = inventoryadjustbus.getAdjustHeaderSQL(strStatus, strCustomer);
			//查询角色总记录数的SQL语句
			String strCountSQL = inventoryadjustbus.getAdjustHeaderCountSQL(strStatus, strCustomer);
			String strUrl = "/standard/jsp/inventory/adjust/kc_adjustheader_list.jsp";	
			PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, strUrl ,1, maxLine);
			//查询结果
			ls = pt.getLsResult();
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);	*/

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
		//客户
		String strCustomer = CommonTools.getStrToGb2312(request.getParameter("customerid"));
		//原因代码
		String strReasoncode = CommonTools.getStrToGb2312(request.getParameter("reasoncode"));
		//原因
		String strReasondiscr = CommonTools.getStrToGb2312(request.getParameter("reasondiscr"));
	
		try
		{
			
			InventoryAdjustHeader adjust = new InventoryAdjustHeader();
			//调整单号
			BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(dao);
            BaseSeq  seqEn = seqDao.getSeqByType("ADD");
            String strInvoiceNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), dao);
            
			//adjust.setCustomerid(strCustomer);
			adjust.setStatus(strStatus);
			adjust.setReasoncode(strReasoncode);
			adjust.setReasondiscr(strReasondiscr);
			adjust.setAdjustid(strInvoiceNo);
			adjust.setCreatetime(StrTools.getCurrDateTime(5));
			dao.save(adjust);
			
			//日志
			SystemLogInfo sysLog = new SystemLogInfo();
			String strUserCode = request.getSession().getAttribute("userCode").toString();
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
					//adjust=inventoryadjustbus2.getAdjustHeaderToId(strTemp);
					if(adjust!=null && adjust.getStatus().equals("0")){ // 库存调整单为创建状态
						List lsDetail = inventoryadjustbus2.getAdjustDetailListToId(strTemp);
						List lsinventory = new ArrayList();
						if(lsDetail != null)
						{
							for(int j = 0; j < lsDetail.size(); j++)
							{
								    InventoryAdjustDetail detail = (InventoryAdjustDetail)lsDetail.get(j);
									//库存
									InventoryStorage inventory = inventoryadjustbus2.getInventoryInfoToId(detail.getInventoryid());
									if(inventory != null)
									{
										inventory.setStatus("0");//未分配
									}
									lsinventory.add(inventory);
							}
						}
						
						//inventoryadjustbus2.deletejustInvoice(lsDetail, lsinventory, adjust);
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
			//InventoryAdjustHeader adjust = inventoryadjustbus2.getAdjustHeaderToId(strId);
			
			//request.setAttribute("adjust", adjust);
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
		//客户
		String strCustomer = CommonTools.getStrToGb2312(request.getParameter("customerid"));
		//原因代码
		String strReasoncode = CommonTools.getStrToGb2312(request.getParameter("reasoncode"));
		//原因
		String strReasondiscr = CommonTools.getStrToGb2312(request.getParameter("reasondiscr"));
		try
		{
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			InventoryAdjustHeader adjust= null;//inventoryadjustbus2.getAdjustHeaderToId(strId);
			if(adjust != null)
			{
				//adjust.setCustomerid(strCustomer);
				adjust.setReasoncode(strReasoncode);
				adjust.setReasondiscr(strReasondiscr);
				adjust.setAdjusttime(StrTools.getCurrDateTime(5));
				//inventoryadjustbus2.updateAdjustHeader(adjust);
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
		//库存ID
		String strInventoryid = CommonTools.getStrToGb2312(request.getParameter("inventoryid"));
		//目标数量
		String strToQty = CommonTools.getStrToGb2312(request.getParameter("toqty"));
		//目标毛重
		String strToGrossweight = CommonTools.getStrToGb2312(request.getParameter("togrossweight"));
		//目标体积
		String strToCubic = CommonTools.getStrToGb2312(request.getParameter("tocubic"));
		//目标净重
		String strToNetweight = CommonTools.getStrToGb2312(request.getParameter("tonetweight"));
		//状态
		String strStatus = CommonTools.getStrToGb2312(request.getParameter("status"));
	
		try
		{
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			InventoryStorage inventory = inventoryadjustbus2.getInventoryInfoToId(strInventoryid);	
			InventoryAdjustHeader adjustx= null ; //inventoryadjustbus2.getAdjustHeaderToId(strId);
			
			if(inventory != null && inventory.getStatus().equals("0"))  //库存为未分配状态
			{
				if(adjustx.getStatus().equals("0")){ //创建状态
					InventoryAdjustDetail adjust = new InventoryAdjustDetail();
					adjust.setAdjustid(strId);
					adjust.setStatus(strStatus);
					//adjust.setCustomerid(inventory.getOwnerId());
					adjust.setProductid(inventory.getProductid());
					adjust.setLotid(inventory.getLotid());
					adjust.setTraycode(inventory.getTraycode());
					adjust.setPnum(inventory.getPnum());
					//adjust.setPvolume(inventory.getPvolume());
					adjust.setPweight(inventory.getPweight());
					adjust.setPnetweight(inventory.getPnetweight());
					adjust.setTonum(Float.parseFloat(strToQty));
					adjust.setCargo_space_id(inventory.getCargoSpaceId());
					adjust.setInventoryid(inventory.getInventoryid());
					
					adjust.setLotatt1(inventory.getLotatt1());
					adjust.setLotatt2(inventory.getLotatt2());
					adjust.setLotatt3(inventory.getLotatt3());
					adjust.setLotatt4(inventory.getLotatt4());
					adjust.setLotatt5(inventory.getLotatt5());
					adjust.setLotatt6(inventory.getLotatt6());
					adjust.setLotatt7(inventory.getLotatt7());
					adjust.setLotatt8(inventory.getLotatt8());
					adjust.setLotatt9(inventory.getLotatt9());
					adjust.setLotatt10(inventory.getLotatt10());
					adjust.setLotatt11(inventory.getLotatt11());
					adjust.setLotatt12(inventory.getLotatt12());
					
					if(strToCubic != null && strToCubic.trim().length() > 0)
					{
						adjust.setTovolume(Float.parseFloat(strToCubic));	
					}
					if(strToGrossweight != null && strToGrossweight.trim().length() > 0)
					{
						adjust.setToweight(Float.parseFloat(strToGrossweight));
					}
					if(strToNetweight != null && strToNetweight.trim().length() > 0)
					{
						adjust.setTonetweight(Float.parseFloat(strToNetweight));
					}
					adjust.setCreatetime(StrTools.getCurrDateTime(5));
					adjust.setAdjustdetailid(strInventoryid);
					dao.save(adjust);	
					
					//库存
					inventory.setStatus("3"); //库存调整状态
					dao.update(inventory);	
					request.setAttribute("back_msg", "Y");
					
					//日志
					SystemLogInfo sysLog = new SystemLogInfo();
					String strUserCode = request.getSession().getAttribute("userCode").toString();
					String strUserName = request.getSession().getAttribute("userName").toString();
					sysLog.setM_strLogCode(strUserCode);
					sysLog.setM_strLogName(strUserName);
					sysLog.setM_strModuleName("库存管理=>库存调整");
					sysLog.setM_strContent("添加库存调整单详细成功");
					sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
					dao.save(sysLog);
				}else{
					request.setAttribute("back_msg", "只有创建状态才能增加调整单明细");
				}
				
			}else{
				request.setAttribute("back_msg", "只有未分配状态才能库存调整");
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
			String strUrl = null;
			InventoryAdjustHeader adjust= null;// inventoryadjustbus2.getAdjustHeaderToId(strId);
			if(strDeleteStr != null && strDeleteStr.length() > 0 && adjust.getStatus().equals("0")) //创建状态
			{
				String [] roleIds = strDeleteStr.split(",");
				for(int i=0; i<roleIds.length; i++)
				{
					//删除
					String strTemp =  roleIds[i];
					//inventoryadjustbus2.deleteAdjustDetail(strTemp);
					/*InventoryAdjustDetail detail = inventoryadjustbus2.getAdjustDetailToId(strTemp);
					InventoryStorage inventory = inventoryadjustbus2.getInventoryInfoToId(detail.getInventoryid());	
					dao.delete(detail);
					inventory.setStatus("0"); //还原未分配状态
					dao.update(inventory);*/
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
			InventoryAdjustDetail detail = null;//inventoryadjustbus2.getAdjustDetailToId(strId);
			BaseProduct sku = skuMgr.getProductById(detail.getProductid());
			
			request.setAttribute("adjustdetail", detail);
			request.setAttribute("sku", sku);
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
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
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
					
					InventoryAdjustHeader adjust= null;//inventoryadjustbus2.getAdjustHeaderToId(strTemp);
					List lsDetail = inventoryadjustbus2.getAdjustDetailListToId(strTemp);
					if(lsDetail != null)
					{
						for(int j = 0; j < lsDetail.size(); j++)
						{
							InventoryAdjustDetail detail = (InventoryAdjustDetail)lsDetail.get(j);
							
							String status = detail.getStatus();
							if(status != null && !status.trim().equals("1"))
							{
								//库存ID
								String strId = detail.getInventoryid();
								//目标数量
								double toQty = detail.getTonum();
								//目标毛重
								double toGrossweight = detail.getToweight();
								//目标净重
								double toNetweight = detail.getTonetweight();
								//目标体积
								double toCubic = detail.getTovolume();
								//库存
								InventoryStorage inventory = inventoryadjustbus2.getInventoryInfoToId(strId);
								if(inventory != null)
								{
									//增加库存交易
									InventoryTransaction trans = new InventoryTransaction();
									trans.setTransactiontype("调整");//调整
									trans.setTransstatus("完成");//完成
									trans.setDoctype("调整单");//调整单
									
									trans.setFmcustomerid(inventory.getOwnerId());
									trans.setFmpackid(inventory.getPackid());
									trans.setFmproductid(inventory.getProductid());
									trans.setFmlotid(inventory.getLotid());
									trans.setFmcargo_space_id(inventory.getCargoSpaceId());
									trans.setFmpunit(inventory.getPunit());
									trans.setFmnum(inventory.getPnum());
									trans.setFmvolume(inventory.getPvolume());
									trans.setFmweight(inventory.getPweight());
									trans.setFmnetweight(inventory.getPnetweight());
									trans.setCreatemanid(strUserName);
									
									trans.setTocustomerid(inventory.getOwnerId());
									trans.setToproductid(inventory.getProductid());
									trans.setTolotid(inventory.getLotid());
									trans.setTocargo_space_id(inventory.getCargoSpaceId());
									trans.setTopunit(inventory.getPunit());
									trans.setTonum(toQty);
									trans.setTovolume(toCubic);
									trans.setToweight(toGrossweight);
									trans.setTonetweight(toNetweight);
									
									inventory.setPnum(toQty);
									inventory.setPvolume(toCubic);
									inventory.setPweight(toGrossweight);
									inventory.setPnetweight(toNetweight);
									inventory.setStatus("0");//未分配
						
									trans.setReasoncode(adjust.getReasoncode());
									trans.setReason(adjust.getReasondiscr());
									trans.setTransactiontime(StrTools.getCurrDateTime(5)); 
									
									lstrans.add(trans);
								}
								detail.setAdjusttime(StrTools.getCurrDateTime(5));
								detail.setStatus("完成");
								lsajustDetail.add(detail);
								lsinventory.add(inventory);
								
							}
						}
					}
					//修改调整单状态等
					adjust.setAdjusttime(StrTools.getCurrDateTime(5));
					adjust.setStatus("1");
					//inventoryadjustbus2.createjustInvoice(lsajustDetail, lsinventory, adjust,lstrans);
            		Logger.info("用户【" + strUserCode + "】生成了库存调整单，单据号：" + adjust.getAdjustid());
				}
				
				//日志
				SystemLogInfo sysLog = new SystemLogInfo();
				sysLog.setM_strLogCode(strUserCode);
				sysLog.setM_strLogName(strUserName);
				sysLog.setM_strModuleName("库存管理=>库存调整单");
				sysLog.setM_strContent("调整库存调整单成功");
				sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
				dao.save(sysLog);
				
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
