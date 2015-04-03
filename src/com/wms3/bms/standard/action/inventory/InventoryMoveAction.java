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
import com.wms3.bms.standard.business.base.ICargoSpaceBus;
import com.wms3.bms.standard.business.base.impl.CargoSpaceBusImpl;
import com.wms3.bms.standard.business.inventory.IInventoryBus;
import com.wms3.bms.standard.business.inventory.InventoryMoveBus;
import com.wms3.bms.standard.business.inventory.impl.InventoryBusImpl;
import com.wms3.bms.standard.business.inventory.impl.InventoryMoveBusImpl;
import com.wms3.bms.standard.business.job.IJobBus;
import com.wms3.bms.standard.business.job.impl.JobBusImpl;
import com.wms3.bms.standard.dao.base.impl.BaseSeqDaoImpl;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BaseSeq;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryMovementDetail;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryMovementHeader;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * 描述：库存移动
 * 
 * @author liuxh
 * 
 */
public class InventoryMoveAction extends AbstractAction {

	protected IInventoryBus inventoryBus;

	protected int maxLine = 20; // 分页显示的行数；

	@Override
	/**
	 * 移库
	 */
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam)
			throws Exception {

		HttpServletRequest request = (HttpServletRequest) hsCurrentParam
				.get("request");
		HttpServletResponse response = (HttpServletResponse) hsCurrentParam
				.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO) hsSysParam.get("dao");

		String strWarehouseId = CommonTools.getStrToGbk(request
				.getParameter("warehouseid"));// 仓库ID
		String strAreaId = CommonTools.getStrToGbk(request
				.getParameter("whAreaId"));// 货区
		String strCargoAlleyId = CommonTools.getStrToGbk(request
				.getParameter("cargoAlleyId"));// 巷道
		String strPlatoon = CommonTools.getStrToGbk(request
				.getParameter("platoon"));// 排
		String strColumn = CommonTools.getStrToGbk(request
				.getParameter("column"));// 列
		String strFloor = CommonTools
				.getStrToGbk(request.getParameter("floor"));// 层

		String strPackageId = CommonTools.getStrToGbk(request
				.getParameter("package_id"));// 客户ID
		String strTrayCode = CommonTools.getStrToGbk(request
				.getParameter("tray_code"));// 托盘条码
		String productcode = CommonTools.getStrToGbk(request
				.getParameter("productcode"));// 产品编码
		String strLineRow = CommonTools.getStrToGbk(request
				.getParameter("linerow"));// 显示行数
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));// 显示行数

		String strInventoryId = CommonTools.getStrToGb2312(request
				.getParameter("inventoryid")); // 库存ID

		if (strLineRow != null && strLineRow.length() > 0) {
			maxLine = Integer.parseInt(strLineRow);
		}
		inventoryBus = new InventoryBusImpl(dao);
		List ls = null;
		try {
			if (flag != null) {
				if (flag.equals("1")) {// 库存管理=》库存移动->查询库存

					strUrl = "/standard/jsp/inventory/move/kc_move_list.jsp";

					String strQueryHql = inventoryBus.getInventoryHQL(
							strWarehouseId, strAreaId, strCargoAlleyId,
							strPlatoon, strColumn, strFloor, strPackageId,
							strTrayCode, null, null, productcode);
					String strCountHql = "select count(*)" + strQueryHql;

					PagingTool pt = CommonPagination.getPagingTool(dao,
							strCountHql, "select sto" + strQueryHql, strUrl, 1,
							maxLine);
					ls = pt.getLsResult();
					request.setAttribute("exResultList", ls);
					httpsession.setAttribute("paging", pt);
				} else if (flag.equals("2")) {// 库存移动-》点击移动获取库存信息

					InventoryMoveBus iBus = new InventoryMoveBusImpl(dao);
					strUrl = "/standard/jsp/inventory/move/movement_view.jsp";
					String[] ids = strInventoryId.split("\\|");
					InventoryStorage iStorage = null;
					List<InventoryStorage> list = new ArrayList<InventoryStorage>();
					for (int i = 0; i < ids.length; i++) {
						iStorage = iBus.getInventoryStorage(ids[i]);
						list.add(iStorage);
					}

					request.setAttribute("ids", strInventoryId);
					request.setAttribute("strWarehouseId", strWarehouseId);
					request.setAttribute("inventory", list);
				}
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
		} catch (Exception er) {
			Logger.error("库存移动==>库存查询==>查询库存:" + er.getMessage());
			request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}
		return null;
	}

	/**
	 * 增加移动单据
	 * 
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdd(Hashtable hsSysParam, Hashtable hsCurrentParam)
			throws Exception {
		HttpServletRequest request = (HttpServletRequest) hsCurrentParam
				.get("request");
		HttpServletResponse response = (HttpServletResponse) hsCurrentParam
				.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO) hsSysParam.get("dao");

		String strUserCode = request.getSession().getAttribute("userCode")
				.toString();// 创单人
		String strUserName = request.getSession().getAttribute("userName")
				.toString();// 创单人
		String flag = CommonTools.getStrToGb2312(request.getParameter("flag"));// flag
		String strId = CommonTools.getStrToGb2312(request.getParameter("ids"));// 库存ID

		String strTime = CommonTools.getStrToGb2312(request
				.getParameter("strTime"));// 制单时间
		
		String warehouseid = CommonTools.getStrToGb2312(request
				.getParameter("warehouseid"));// 移入仓库ID
		
		String strtoWarehouseid = CommonTools.getStrToGb2312(request
				.getParameter("toWarehouseid"));// 移入仓库ID
		String strtoWhAreaId = CommonTools.getStrToGb2312(request
				.getParameter("toWhAreaId"));// 移入库区 ID

		String strtoCargospaceId = CommonTools.getStrToGb2312(request
				.getParameter("toCargospaceId"));// 移入库位ID
		String strtoCargospaceName = CommonTools.getStrToGb2312(request
				.getParameter("toCargospaceName"));// 移入库位名称

		String strreasoncode = CommonTools.getStrToGb2312(request
				.getParameter("reasoncode"));// 移库原因

		String strRemark = CommonTools.getStrToGb2312(request
				.getParameter("remark"));// 备注

		if (flag != null && flag.equals("4")) {

			InventoryMoveBus iMoveBus = new InventoryMoveBusImpl(dao);
			ICargoSpaceBus iCargo = new CargoSpaceBusImpl(dao);
			IJobBus iJobBus = new JobBusImpl(dao);

			int i = 0;
			int x=0,y=0;
			try {
				String strBackMsg = "Y";
				
				
				
				//移库单号
				BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(dao);
				BaseSeq seqEn = seqDao.getSeqByType("YKD");
				String strNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(), seqEn.getIcodelength(), dao);
				
				//创建集合
				List<InventoryStorage> lsInStroStorage = new ArrayList<InventoryStorage>();
				List<BaseCargospace> lsCargospace = new ArrayList<BaseCargospace>();
				List<InventoryMovementDetail> lsInMoverDetail = new ArrayList<InventoryMovementDetail>();
				List<InoutJob> lsInoutJob = new ArrayList<InoutJob>();
				List<InoutJobdetail> lsInoutJobDetail = new ArrayList<InoutJobdetail>();
				
				String id = null;
				String[] ids = strId.split("\\|");
				for (int j = 0; j < ids.length; j++) {
					id=ids[j];
					// 获得库存信息
					InventoryStorage inStroStorage = iMoveBus.getInventoryStorage(id);
					// 获取库位信息
					BaseCargospace cargospace = iCargo.getCargoSpaceById(inStroStorage.getCargoSpaceId());

					if (cargospace != null&& cargospace.getCsstatus().equals("2")&&inStroStorage.getStatus().equals("0")) {// 库位信息为满货位状态，库存为未分配状态
						
						//作业单号
						BaseSeq seqEn1 = seqDao.getSeqByType("CKZY");
						String strNo1 = SeqTool.getNewID(seqEn1.getSeqType(),seqEn1.getSeqPrefix(), seqEn1.getIcodelength(),dao);
						
						
						//创建移库作业实例
						InoutJob inoutJob = iJobBus.createInoutJob(strNo1, strUserCode, strTime,strtoWhAreaId, strtoCargospaceId,strtoWarehouseid, inStroStorage);
						
						//创建移库作业详细实例
						InoutJobdetail inoutJobDetail = iJobBus.createInoutJobDetail(strNo1, strUserCode, strTime,strtoWhAreaId, strtoCargospaceId,strtoWarehouseid, inStroStorage);
						
						//创建移库单详细
						InventoryMovementDetail inMoverDetail = iMoveBus.createMoveDetail(strNo, strUserCode, strTime,strtoWhAreaId, strtoCargospaceId,strtoCargospaceName,strtoWarehouseid, strreasoncode, strRemark, inStroStorage);
						
						//将各元素添加到集合里面
						lsInStroStorage.add(inStroStorage);
						lsCargospace.add(cargospace);
						lsInoutJob.add(inoutJob);
						lsInoutJobDetail.add(inoutJobDetail);
						lsInMoverDetail.add(inMoverDetail);
						
						SystemLogInfo sysLog = new SystemLogInfo();
						sysLog.setM_strLogCode(strUserCode);
						sysLog.setM_strLogName(strUserName);
						sysLog.setM_strModuleName("库存管理=>库存移动");
						sysLog.setM_strContent("移动库存成功");
						sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
						dao.save(sysLog);
						y++;
					} else {
						strBackMsg = "请确定库位或库存状态";
						x++;
					}

				}
				if(x!=0){
					strBackMsg = "有"+x+"个库存没有生成相应的移库详细单据和作业";
				}
				if(y!=0 && lsInMoverDetail!=null && lsInMoverDetail.size()>0  && lsInoutJob!=null && lsInoutJob.size()>0&& lsInoutJobDetail!=null && lsInoutJobDetail.size()>0 ){ 
					//移库单
					InventoryMovementHeader iMoveHeader = new InventoryMovementHeader();
					iMoveHeader.setMovementid(strNo);
					iMoveHeader.setWarehouseid(strtoWarehouseid);
					iMoveHeader.setCreateManid(strUserCode);
					iMoveHeader.setCreateTime(strTime);
					iMoveBus.addMoveHeaderAddMoveDetailUpdateInvent(iMoveHeader,lsInMoverDetail,lsInoutJob,lsInoutJobDetail,lsInStroStorage,lsCargospace);
				}
			
				// 刷新
				PagingTool pt = (PagingTool) httpsession.getAttribute("paging");
				List ls = null;
				String strUrl = null;
				if (pt != null) {
					// 更新总记录数
					int rows = pt.getM_iCountRow() + i;
					pt.setPagingParameter(rows, 1, pt.getM_iMaxRow());
					ls = CommonPagination.getPagingList(dao, pt);

					strUrl = pt.getM_strUrl();
				}
				if (strUrl == null) {
					strUrl = "/jsp/inventory/move/kc_move_list.jsp";
				}

				request.setAttribute("back_msg", strBackMsg);
				request.setAttribute("exResultList", ls);
				request.setAttribute("pagingTool", pt);
				httpsession.setAttribute("paging", pt);

				request.getRequestDispatcher(strUrl).forward(request, response);
			} catch (Exception e) {
			//	dao.update(inStroStorage);
				Logger.error("库存管理==>库存移动==>单个移动失败:" + e.getMessage());
				request.getRequestDispatcher("/error.jsp").forward(request,response);
			}

		}
		return null;
	}

	/**
	 * 移库单据查询
	 * 
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecQuery(Hashtable hsSysParam, Hashtable hsCurrentParam)
			throws Exception {
		HttpServletRequest request = (HttpServletRequest) hsCurrentParam
				.get("request");
		HttpServletResponse response = (HttpServletResponse) hsCurrentParam
				.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO) hsSysParam.get("dao");
		
		
		String moventid = CommonTools.getStrToGbk(request
				.getParameter("id")); // 仓库ID
		// from
		String fmwarehouseid = CommonTools.getStrToGbk(request
				.getParameter("fmwarehouseid")); // 仓库ID
		String fmwhAreaId = CommonTools.getStrToGbk(request
				.getParameter("fmwhAreaid")); // 库区ID
		String fmplatoon = CommonTools.getStrToGbk(request
				.getParameter("fmplatoon")); // 排
		String fmcolumn = CommonTools.getStrToGbk(request
				.getParameter("fmcolumn")); // 列
		String fmfloor = CommonTools.getStrToGbk(request
				.getParameter("fmfloor")); // 层

		String fmCustomerId = CommonTools.getStrToGbk(request
				.getParameter("fmcustomerid")); // 客户ID 多余
		String fmPackageId = CommonTools.getStrToGbk(request
				.getParameter("fmpackageId")); // 品名ID 多余
		String fmTrayCode = CommonTools.getStrToGbk(request
				.getParameter("fmtrayCode")); // 托盘条码 多余
		// to
		String towhAreaId = CommonTools.getStrToGbk(request
				.getParameter("towhAreaid")); // 库区ID
		String toplatoon = CommonTools.getStrToGbk(request
				.getParameter("toplatoon")); // 排
		String tocolumn = CommonTools.getStrToGbk(request
				.getParameter("tocolumn")); // 列
		String tofloor = CommonTools.getStrToGbk(request
				.getParameter("tofloor")); // 层

		String toCustomerId = CommonTools.getStrToGbk(request
				.getParameter("tocustomerId")); // 客户ID 多余
		String toPackageId = CommonTools.getStrToGbk(request
				.getParameter("topackageId")); // 品名ID 多余
		String toTrayCode = CommonTools.getStrToGbk(request
				.getParameter("totrayCode")); // 托盘条码 多余

		String date_from = CommonTools.getStrToGbk(request
				.getParameter("indate_from")); // 日期from
		String date_to = CommonTools.getStrToGbk(request
				.getParameter("indate_to")); // 日期to

		
		String flag = CommonTools.getStrToGbk(request
				.getParameter("flag")); 
		String linerow = CommonTools.getStrToGbk(request
				.getParameter("linerow")); // 每页显示行数
		if (linerow != null && linerow.length() > 0) {
			maxLine = Integer.parseInt(linerow);
		}
		
		
		
		if(flag!=null){
			if(flag.equals("1")){
				InventoryMoveBus iMoveBus = new InventoryMoveBusImpl(dao);
				List lsmoves = null;
				PagingTool pt = null;
				try {
					strUrl = "/standard/jsp/inventory/ykquery/yk_query_list.jsp";
					pt = iMoveBus.getMovesLs0(fmwarehouseid, fmwhAreaId, fmplatoon,
							fmcolumn, fmfloor, fmCustomerId, fmPackageId, fmTrayCode,
							towhAreaId, toplatoon, tocolumn, tofloor, toCustomerId,
							toPackageId, toTrayCode, date_from, date_to, strUrl, 1,
							maxLine);
					lsmoves = pt.getLsResult();
					request.setAttribute("exResultList", lsmoves);
					httpsession.setAttribute("paging", pt);
					request.getRequestDispatcher(strUrl).forward(request, response);

				} catch (Exception er) {
					Logger.error("库存管理==>库存交易==>查询失败:" + er.getMessage());
					request.getRequestDispatcher("/error.jsp").forward(request,
							response);
				}
			}else if(flag.equals("2")){
				InventoryMoveBus iMoveBus = new InventoryMoveBusImpl(dao);
				List lsmoves = null;
				PagingTool pt = null;
				try {
					strUrl = "/standard/jsp/inventory/ykquery/yk_query_list_detail.jsp";
					pt = iMoveBus.getMovesLs1(moventid, strUrl, 1,
							maxLine);
					lsmoves = pt.getLsResult();
					request.setAttribute("exResultList", lsmoves);
					httpsession.setAttribute("paging", pt);
					request.getRequestDispatcher(strUrl).forward(request, response);

				} catch (Exception er) {
					Logger.error("库存管理==>库存交易==>查询失败:" + er.getMessage());
					request.getRequestDispatcher("/error.jsp").forward(request,
							response);
				}
			}
		}
		

		return null;
	}

	/**
	 * 打印
	 * 
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecPrint(Hashtable hsSysParam, Hashtable hsCurrentParam)
			throws Exception {
		HttpServletRequest request = (HttpServletRequest) hsCurrentParam
				.get("request");
		HttpServletResponse response = (HttpServletResponse) hsCurrentParam
				.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO) hsSysParam.get("dao");
		
		String invoiceid = CommonTools.getStrToGbk(request
				.getParameter("invoiceid")); 
		
		// from
		String fmwarehouseid = CommonTools.getStrToGbk(request
				.getParameter("fmwarehouseid")); // 仓库ID
		String fmwhAreaId = CommonTools.getStrToGbk(request
				.getParameter("fmwhAreaid")); // 库区ID
		String fmplatoon = CommonTools.getStrToGbk(request
				.getParameter("fmplatoon")); // 排
		String fmcolumn = CommonTools.getStrToGbk(request
				.getParameter("fmcolumn")); // 列
		String fmfloor = CommonTools.getStrToGbk(request
				.getParameter("fmfloor")); // 层

		String fmCustomerId = CommonTools.getStrToGbk(request
				.getParameter("fmcustomerid")); // 客户ID 多余
		String fmPackageId = CommonTools.getStrToGbk(request
				.getParameter("fmpackageId")); // 品名ID 多余
		String fmTrayCode = CommonTools.getStrToGbk(request
				.getParameter("fmtrayCode")); // 托盘条码 多余
		// to
		String towhAreaId = CommonTools.getStrToGbk(request
				.getParameter("towhAreaid")); // 库区ID
		String toplatoon = CommonTools.getStrToGbk(request
				.getParameter("toplatoon")); // 排
		String tocolumn = CommonTools.getStrToGbk(request
				.getParameter("tocolumn")); // 列
		String tofloor = CommonTools.getStrToGbk(request
				.getParameter("tofloor")); // 层

		String toCustomerId = CommonTools.getStrToGbk(request
				.getParameter("tocustomerId")); // 客户ID 多余
		String toPackageId = CommonTools.getStrToGbk(request
				.getParameter("topackageId")); // 品名ID 多余
		String toTrayCode = CommonTools.getStrToGbk(request
				.getParameter("totrayCode")); // 托盘条码 多余

		String date_from = CommonTools.getStrToGbk(request
				.getParameter("indate_from")); // 日期from
		String date_to = CommonTools.getStrToGbk(request
				.getParameter("indate_to")); // 日期to

		String linerow = CommonTools.getStrToGbk(request
				.getParameter("linerow")); // 每页显示行数
		if (linerow != null && linerow.length() > 0) {
			maxLine = Integer.parseInt(linerow);
		}
		InventoryMoveBus iMoveBus = new InventoryMoveBusImpl(dao);
//		List lsmoves = null;
//		// PagingTool pt = null;
//		String strHql = null;
		try {
			strUrl = "/standard/jsp/inventory/ykquery/yk_query_print.jsp";
//			strHql = iMoveBus.getQueryHql(fmwarehouseid, fmwhAreaId, fmplatoon,
//					fmcolumn, fmfloor, fmCustomerId, fmPackageId, fmTrayCode,
//					towhAreaId, toplatoon, tocolumn, tofloor, toCustomerId,
//					toPackageId, toTrayCode, date_from, date_to);
//			lsmoves = dao.searchEntities(strHql);
			
			InventoryMovementHeader movementHeader = new InventoryMovementHeader();
			InventoryMovementDetail movementDetail = new InventoryMovementDetail();
			
			InventoryMovementHeader moveHeader = movementHeader.getIMoveHeader(invoiceid,dao);
			
			List lsmoveDetail = movementDetail.getMoveDetailListToId(invoiceid, dao);
			
			request.setAttribute("result", moveHeader);
			request.setAttribute("exResultList", lsmoveDetail);
			//request.setAttribute("exResultList", lsmoves);
			// httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {
			Logger.error("库存管理==>库存交易==>查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}

		return null;
	}

}
