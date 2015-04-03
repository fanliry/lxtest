package com.wms3.bms.standard.business.outbound.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.base.ILotBus;
import com.wms3.bms.standard.business.base.impl.LotBusImpl;
import com.wms3.bms.standard.business.outbound.IOutBoundBus;
import com.wms3.bms.standard.business.outbound.IOutBoundJobBus;
import com.wms3.bms.standard.dao.base.IBaseCargoSpaceDao;
import com.wms3.bms.standard.dao.base.impl.BaseCargoSpaceDaoImpl;
import com.wms3.bms.standard.dao.inventory.IInventoryDao;
import com.wms3.bms.standard.dao.inventory.impl.InventoryDaoImpl;
import com.wms3.bms.standard.dao.job.ITaskDao;
import com.wms3.bms.standard.dao.job.impl.TaskDaoImpl;
import com.wms3.bms.standard.dao.outbound.IAssginDao;
import com.wms3.bms.standard.dao.outbound.IOutboundJobDao;
import com.wms3.bms.standard.dao.outbound.impl.AssginDaoImpl;
import com.wms3.bms.standard.dao.outbound.impl.OutboundJobDaoImpl;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BaseLotDetail;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

/**
 * 
 * 描述: 出库作业管理业务类
 * 
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public class OutBoundJobBusImpl implements IOutBoundJobBus {

	/** DAO类 */
	protected IOutboundJobDao outboundJobDao;
	/** 分配DAO类 */
	protected IAssginDao assginDao;

	public OutBoundJobBusImpl(EntityDAO dao) {
		assginDao = new AssginDaoImpl(dao);
		outboundJobDao = new OutboundJobDaoImpl(dao);
	}

	/**
	 * 功能:根据条件查询出库作业
	 * 
	 * @param warehouseid
	 *            仓库
	 * @param whAreaId
	 *            库区
	 * @param productid
	 *            品名
	 * @param customerid
	 *            客户
	 * @param indate
	 *            作业日期
	 * @param shiftid
	 *            班次
	 * @param jobid
	 *            作业号
	 * @param status
	 *            作业状态
	 * @param tray_code
	 *            托盘条码
	 * @param maxLine
	 * @param strUrl
	 * @return
	 * @throws Exception
	 */
	public PagingTool getOutboundJobs(String warehouseid,String Virtualwhid, String whAreaId, String productid, String customerid, String indate, String shiftid, String jobid, String status,String taskid, String traycode,
			String strUrl, int maxLine) throws Exception {

		PagingTool pt = null;

		try {
			StringBuilder strBud = new StringBuilder();
			strBud.append(" from InoutJob as job where job.inOutType='2' ");

			if (warehouseid != null && warehouseid.trim().length() > 0) { // 仓库
				strBud.append(" and job.warehouseid='").append(warehouseid).append("'");
			}
			
			if (Virtualwhid != null && Virtualwhid.trim().length() > 0) { // 逻辑库区id
				strBud.append(" and job.Virtualwhid='").append(Virtualwhid).append("'");
			}

			if (whAreaId != null && whAreaId.trim().length() > 0) { // 库区
				strBud.append(" and job.tcargoWhareaId='").append(whAreaId).append("'");
			}

			if (productid != null && productid.trim().length() > 0) { // 品名
				strBud.append(" and job.jobid in " + "(select jobdetail.jobid from InoutJobdetail as jobdetail " + " where job.jobid=jobdetail.jobid and jobdetail.productid='").append(productid)
						.append("')");
			}

			if (customerid != null && customerid.trim().length() > 0) { // 货主
				strBud.append(" and job.jobid in " + "(select jobdetail.jobid from InoutJobdetail as jobdetail " + " where job.jobid=jobdetail.jobid and jobdetail.customerid='").append(customerid)
						.append("')");
			}

			if (indate != null && indate.trim().length() > 0) { // 作业日期
				strBud.append(" and substring(job.createtime,1,10)='").append(indate).append("'");
			}

			if (jobid != null && jobid.trim().length() > 0) { // 作业号
				strBud.append(" and job.jobid='").append(jobid).append("'");
			}

			if (status != null && status.trim().length() > 0) { // 作业状态
				strBud.append(" and job.status='").append(status).append("'");
			}

			if (shiftid != null && shiftid.trim().length() > 0) { // 班次
				strBud.append(" and job.shiftid='").append(shiftid).append("'");
			}

			if (taskid != null && taskid.trim().length() > 0) { // 调度任务ID
				strBud.append(" and job.taskid='").append(taskid).append("'");
			}
			
			if (traycode != null && traycode.trim().length() > 0) { // 托盘条码
				strBud.append(" and job.traycode='").append(traycode).append("'");
			}

			// 排序方式
			String strHQL = strBud.toString() + " order by job.jobid desc";

			String strCountHQL = "select count(*)" + strBud.toString();

			pt = CommonPagination.getPagingTool(outboundJobDao.getEntityDAO(), strCountHQL, strHQL, strUrl, 1, maxLine);

		} catch (Exception er) {
			throw new Exception("根据条件查询查询出库作业列表出错:" + er.getMessage());
		}

		return pt;
	}

	/**
	 * 功能:根据作业id查询作业明细
	 * 
	 * @param jobid
	 *            作业号
	 * @return
	 * @throws Exception
	 */
	public List getJobDetails(String jobid) throws Exception {

		String strSql = "";
		List list = null;

		try {
			strSql = " from InoutJobdetail as jobdetail where jobdetail.jobid='" + jobid + "'";

			list = outboundJobDao.querySQL(strSql);

		} catch (Exception er) {
			er.printStackTrace();
			throw new Exception("出库管理->查询作业详细列表时候出错：" + er.getMessage());
		}

		return list;
	}

	/**
	 * 功能:作废作业（作业管理）
	 * 
	 * @param jobids
	 *            作业ID(可复数个)
	 * @param strUserCode
	 *            用户
	 * @return
	 * @throws Exception
	 */
	public String cancelJobs(String jobids, String strUserCode) throws Exception {

		String strBackMsg = "Y";

		try {

			IBaseCargoSpaceDao baseCargoSpaceDAO = new BaseCargoSpaceDaoImpl(outboundJobDao.getEntityDAO());
			IInventoryDao inventoryDAO = new InventoryDaoImpl(outboundJobDao.getEntityDAO());
			BaseCargospace cargospace = null;
			String[] jobid = jobids.split(",");
			for (int i = 0; i < jobid.length; i++) {
				String jobidsString = jobid[i].intern();
				synchronized (jobidsString) {
					InoutJob job = outboundJobDao.getJobByJobid(jobid[i]);
					InoutJobdetail jobDetail = new InoutJobdetail().getJobDetailByJobid(jobid[i], outboundJobDao.getEntityDAO());
					if (job != null && jobDetail != null) {
						if (job.getJobcategory() != null && job.getJobcategory().equals("1")) { // 立体库作业作废
							// 判断作业状态 1.未执行 2.待执行 3.正执行 4.完成 5.作废 6.异常作业 8.暂停作业
							if (job.getStatus().equals("4") || job.getStatus().equals("5")) {

								if (strBackMsg.equals("Y")) {
									strBackMsg = "不能作废已经完成或者已经作废掉的作业！";

								} else {
									strBackMsg += "\r不能作废已经完成或者已经作废掉的作业！";
								}

							} else {

								// 获取作业的目标货位 修改：之前是job.getTcargoSpaceId()现在是
								// :源位置job.getScargoSpaceId()
								cargospace = baseCargoSpaceDAO.getCargoSpaceById(job.getScargoSpaceId());
								cargospace.setCsstatus("2"); // 满货位
								job.setStatus("5"); // 作废
								List lsStorage = inventoryDAO.getInventoryByCsId(job.getScargoSpaceId()); // 库存列表
								if (job.getInvoicetype().equals("4")) { // 移库作业（立体库移到暂存）
									// 修改出库单详细的分配数量
									StringBuilder invoiceBudSQL = new StringBuilder();
									// 保存到数据库,作废作业，恢复库存分配状态,修改货位状态为空货位
									outboundJobDao.ZFJob(job, cargospace, lsStorage, invoiceBudSQL);
									Logger.info("用户【" + strUserCode + "】在作业管理模块里作废了出库作业：" + jobid[i]);
								} else {
									// 修改出库单详细的分配数量
									StringBuilder invoiceBudSQL = new StringBuilder();
									// 需要分配的数量
									invoiceBudSQL.append("update OutboundInvoiceDetail set assignnum=assignnum-").append(jobDetail.getAssignnum()).append(" , assignweight=assignweight-")
											.append(jobDetail.getAssignweight()).append(" , assignnetweight=assignnetweight-").append(jobDetail.getAssignnetweight())
											.append(" , assignvolume=assignvolume-").append(jobDetail.getAssignvolume()).append(" where outstockdetailid='").append(job.getBoundstockdetailid())
											.append("'");

									lsStorage = null;
									// 保存到数据库,作废作业，恢复库存分配状态,修改货位状态为空货位
									outboundJobDao.ZFJob(job, cargospace, lsStorage, invoiceBudSQL);
									Logger.info("用户【" + strUserCode + "】在作业管理模块里作废了出库作业：" + jobid[i]);
								}

							}
						} else if (job.getJobcategory() != null && job.getJobcategory().equals("2")) { // 暂存区作业
							// 判断作业状态 1.未执行 2.待执行 3.正执行 4.完成 5.作废 6.异常作业 8.暂停作业
							if (job.getStatus().equals("4") || job.getStatus().equals("5")) {

								if (strBackMsg.equals("Y")) {
									strBackMsg = "不能作废已经完成或者已经作废掉的作业！";

								} else {
									strBackMsg += "\r不能作废已经完成或者已经作废掉的作业！";
								}

							} else {
								// 获取作业的目标货位 修改：之前是job.getTcargoSpaceId()现在是
								// :源位置job.getScargoSpaceId()
								// cargospace =
								// baseCargoSpaceDAO.getCargoSpaceById(job.getScargoSpaceId());
								// cargospace.setCsstatus("2"); //满货位
								cargospace = null;
								job.setStatus("5"); // 作废
								InventoryStorage lsStorage = inventoryDAO.getInventoryById(jobDetail.getInventoryid()); // 库存列表
								if (lsStorage != null) {
									double num = lsStorage.getAssignnum() - jobDetail.getAssignnum();
									if (num < 0) {
										lsStorage.setAssignnum(0);
									} else {
										lsStorage.setAssignnum(num);
									}
								}
								// 修改出库单详细的分配数量
								StringBuilder invoiceBudSQL = new StringBuilder();
								// 需要分配的数量
								invoiceBudSQL.append("update OutboundInvoiceDetail set assignnum=assignnum-").append(jobDetail.getAssignnum()).append(" , assignweight=assignweight-")
										.append(jobDetail.getAssignweight()).append(" , assignnetweight=assignnetweight-").append(jobDetail.getAssignnetweight()).append(" , assignvolume=assignvolume-")
										.append(jobDetail.getAssignvolume()).append(" where outstockdetailid='").append(job.getBoundstockdetailid()).append("'");
								// 保存到数据库,作废作业，恢复库存分配状态,修改货位状态为空货位
								outboundJobDao.ZCJob(job, cargospace, lsStorage, invoiceBudSQL);
								Logger.info("用户【" + strUserCode + "】在作业管理模块里作废了出库作业：" + jobid[i]);
							}
						} else {
							strBackMsg = "作业类别没有找到！";
						}
					} else {

						if (strBackMsg.equals("Y")) {
							strBackMsg = "作业不存在！";

						} else {
							strBackMsg += "\r作业不存在！";
						}
					}
					
				}
				
			
				
			}
		} catch (Exception e) {

			e.printStackTrace();
			strBackMsg = "作废作业（作业管理）时候发生错误！";
		}

		return strBackMsg;
	}

	/**
	 * 功能:手动完成作业（作业管理）
	 * 
	 * @param jobids
	 *            作业ID(可复数个)
	 * @param strUserCode
	 *            用户
	 * @return
	 * @throws Exception
	 */
	public String finishJobs(String jobids, String strUserCode) throws Exception {
		String strBackMsg = "Y";
		try {
			IBaseCargoSpaceDao baseCargoSpaceDAO = new BaseCargoSpaceDaoImpl(outboundJobDao.getEntityDAO());
			IInventoryDao inventoryDAO = new InventoryDaoImpl(outboundJobDao.getEntityDAO());
			TaskDaoImpl taskimp = new TaskDaoImpl(outboundJobDao.getEntityDAO());
			BaseCargospace cargospace = null;
			String[] jobid = jobids.split(",");
			for (int i = 0; i < jobid.length; i++) {
				String jobidsString = jobid[i].intern();
				synchronized (jobidsString) {
					InoutJob job = outboundJobDao.getJobByJobid(jobid[i]);
					if (job != null) {
						if (job.getJobcategory() != null && job.getJobcategory().equals("1")) { // 立体库作业
							// 判断作业状态 1.未执行 2.待执行 3.正执行 4.完成 5.作废 6.异常作业 8.暂停作业
							if (job.getStatus().equals("4") || job.getStatus().equals("5")) {
								if (strBackMsg.equals("Y")) {
									strBackMsg = "不能手动完成已经完成或者已经作废掉的作业！";
								} else {
									strBackMsg += "\r不能手动完成已经完成或者已经作废掉的作业！";
								}
							} else {
								String strTime = StrTools.getCurrDateTime(2); // 出库时间(保证一托盘上每箱的入库时间一致)
								// 获取作业的目标货位
								cargospace = baseCargoSpaceDAO.getCargoSpaceById(job.getScargoSpaceId());
								if (cargospace != null) {
									job.setStatus("4");
									job.setFinishtime(strTime);
									job.setOnLineType("2"); // 脱机
									ScheduleTask task = taskimp.getScheduleTaskBytraycode(job.getTraycode(), outboundJobDao.getEntityDAO());
						            task.setStatus("6");		//修改调度状态 :  2.待执行   3.正执行 4.完成 5.作废  6.同步完成  8.同步作废
									List lsjobdetail = getJobDetails(jobid[i]); // 作业明细列表
									List lsStorage = null;
									cargospace.setCsstatus("1");// 货位状态1：空货位；2：满货位；3：入库分配；4：出库分配；5：入库需盘点；6：出库需盘点；7：出库取货；8：已出货
									lsStorage = inventoryDAO.getInventoryByCsId(job.getScargoSpaceId()); // 库存列表
									// 保存到数据库,手动完成作业,修改调度状态，删除库存,修改货位状态为空货位
									outboundJobDao.finishJob(job,task, cargospace, lsjobdetail, lsStorage, null);
									Logger.info("用户【" + strUserCode + "】在作业管理模块里手动完成了出库作业：" + jobid[i]);
								} else {
									if (strBackMsg.equals("Y")) {
										strBackMsg = "作业【" + jobid[i] + "】手动完成失败，无出库货位！";
									} else {
										strBackMsg += "\r作业【" + jobid[i] + "】手动完成失败，无出库货位！";
									}
								}
							}
						} else {
							strBackMsg = "不是立体库作业！";
						}
					} else {
						if (strBackMsg.equals("Y")) {
							strBackMsg = "作业不存在！";

						} else {
							strBackMsg += "\r作业不存在！";
						}
					}
					
				}
			
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			strBackMsg = "手动完成作业（作业管理）时候发生错误！";
		}
		return strBackMsg;
	}

	/**
	 * 功能:设定优先级别
	 * 
	 * @param jobids
	 *            作业ID(可复数个)
	 * @param priority
	 *            优先级别
	 * @param strUserCode
	 *            用户
	 * @return
	 * @throws Exception
	 */
	public String updJobStatus(String jobids, String priority, String strUserCode) throws Exception {

		String strBackMsg = "Y";

		try {

			String sql = "";
			String[] jobid = jobids.split(",");
			for (int i = 0; i < jobid.length; i++) {

				sql = "update InoutJob as job set job.priority= " + Integer.parseInt(priority) + " where job.jobid='" + jobid[i] + "'";
				outboundJobDao.excuteSQL(sql);
				Logger.info("用户[" + strUserCode + "]对作业号[" + jobid[i] + "]设定了优先级别:" + priority);
			}

		} catch (Exception e) {

			e.printStackTrace();
			strBackMsg = "设定优先级别时候发生错误！";
		}

		return strBackMsg;
	}

	/**
	 * 功能:根据条件查询入库作业明细
	 * 
	 * @param warehouseid
	 *            仓库
	 * @param whAreaId
	 *            库区
	 * @param alleyId
	 *            巷道
	 * @param cargospaceid
	 *            货位
	 * @param onlinetype
	 *            入库模式
	 * @param indate_from
	 *            作业日期
	 * @param indate_to
	 *            作业日期
	 * @param shiftid
	 *            班次
	 * @param productid
	 *            品名
	 * @param customerid
	 *            客户
	 * @param tray_code
	 *            托盘条码
	 * @param lotid
	 *            批次id
	 * @param lotatt1
	 *            , lotatt2, lotatt3, lotatt4, lotatt5, lotatt6 批次属性
	 * @param lotatt7
	 *            , lotatt8, lotatt9, lotatt10, lotatt11, lotatt12 批次属性
	 * @param maxLine
	 * @param strUrl
	 * @return
	 * @throws Exception
	 */
	public PagingTool getOutboundJobDetails(String warehouseid, String whAreaId, String alleyId, String cargospaceid, String onlinetype, String indate_from, String indate_to, String shiftid,
			String productid, String customerid, String traycode, String lotid, String lotatt1, String lotatt2, String lotatt3, String lotatt4, String lotatt5, String lotatt6, String lotatt7,
			String lotatt8, String lotatt9, String lotatt10, String lotatt11, String lotatt12, String strUrl, int maxLine) throws Exception {

		PagingTool pt = null;

		try {
			StringBuilder strBud = new StringBuilder();
			strBud.append(" from InoutJob as job, InoutJobdetail as jobdetail where job.jobid=jobdetail.jobid and job.inOutType='2' ");

			if (warehouseid != null && warehouseid.trim().length() > 0) { // 仓库
				strBud.append(" and job.warehouseid='").append(warehouseid).append("'");
			}

			if (whAreaId != null && whAreaId.trim().length() > 0) { // 库区
				strBud.append(" and job.tcargoWhareaId='").append(whAreaId).append("'");
			}

			if (alleyId != null && alleyId.trim().length() > 0) { // 巷道
				strBud.append(" and job.tcargoAlleyId='").append(alleyId).append("'");
			}

			if (cargospaceid != null && cargospaceid.trim().length() > 0) { // 货位
				strBud.append(" and job.tcargoSpaceId='").append(cargospaceid).append("'");
			}

			if (onlinetype != null && onlinetype.trim().length() > 0) { // 入库模式
				strBud.append(" and job.onLineType='").append(onlinetype).append("'");
			}

			if (indate_from != null && indate_from.trim().length() > 0) { // 作业日期
				strBud.append(" and job.createtime>='").append(indate_from).append("'");
			}
			if (indate_to != null && indate_to.trim().length() > 0) { // 作业日期
				strBud.append(" and job.createtime<='").append(indate_to).append(" 99:99:99'");
			}

			if (shiftid != null && shiftid.trim().length() > 0) { // 班次
				strBud.append(" and job.shiftid='").append(shiftid).append("'");
			}

			if (productid != null && productid.trim().length() > 0) { // 品名
				strBud.append(" and jobdetail.productid='").append(productid).append("'");
			}

			if (customerid != null && customerid.trim().length() > 0) { // 客户
				strBud.append(" and jobdetail.customerid='").append(customerid).append("'");
			}

			if (traycode != null && traycode.trim().length() > 0) { // 托盘条码
				strBud.append(" and job.traycode='").append(traycode).append("'");
			}

			// 根据批次ID获得批次
			ILotBus lotBus = new LotBusImpl(outboundJobDao.getEntityDAO());
			HashMap<String, BaseLotDetail> hsLot = lotBus.getHashMapByLotId(lotid);

			BaseLotDetail lotDetail = null; // 批次明细

			// 批次属性
			if (lotatt1 != null && lotatt1.trim().length() > 0) {

				lotDetail = hsLot.get("lotatt1");
				strBud.append(getSqlLotatt("1", lotatt1, lotDetail));
			}

			if (lotatt2 != null && lotatt2.trim().length() > 0) {

				lotDetail = hsLot.get("lotatt2");
				strBud.append(getSqlLotatt("2", lotatt2, lotDetail));
			}

			if (lotatt3 != null && lotatt3.trim().length() > 0) {

				lotDetail = hsLot.get("lotatt3");
				strBud.append(getSqlLotatt("3", lotatt3, lotDetail));
			}

			if (lotatt4 != null && lotatt4.trim().length() > 0) {

				lotDetail = hsLot.get("lotatt4");
				strBud.append(getSqlLotatt("4", lotatt4, lotDetail));
			}

			if (lotatt5 != null && lotatt5.trim().length() > 0) {

				lotDetail = hsLot.get("lotatt5");
				strBud.append(getSqlLotatt("5", lotatt5, lotDetail));
			}

			if (lotatt6 != null && lotatt6.trim().length() > 0) {

				lotDetail = hsLot.get("lotatt6");
				strBud.append(getSqlLotatt("6", lotatt6, lotDetail));
			}

			if (lotatt7 != null && lotatt7.trim().length() > 0) {

				lotDetail = hsLot.get("lotatt7");
				strBud.append(getSqlLotatt("7", lotatt7, lotDetail));
			}

			if (lotatt8 != null && lotatt8.trim().length() > 0) {

				lotDetail = hsLot.get("lotatt8");
				strBud.append(getSqlLotatt("8", lotatt8, lotDetail));
			}

			if (lotatt9 != null && lotatt9.trim().length() > 0) {

				lotDetail = hsLot.get("lotatt9");
				strBud.append(getSqlLotatt("9", lotatt9, lotDetail));
			}

			if (lotatt10 != null && lotatt10.trim().length() > 0) {

				lotDetail = hsLot.get("lotatt10");
				strBud.append(getSqlLotatt("10", lotatt10, lotDetail));
			}

			if (lotatt11 != null && lotatt11.trim().length() > 0) {

				lotDetail = hsLot.get("lotatt11");
				strBud.append(getSqlLotatt("11", lotatt11, lotDetail));
			}

			if (lotatt12 != null && lotatt12.trim().length() > 0) {

				lotDetail = hsLot.get("lotatt12");
				strBud.append(getSqlLotatt("12", lotatt12, lotDetail));
			}

			String strHQL = strBud.toString() + " order by job.jobid";
			String strCountHQL = "select count(*)" + strBud.toString();

			pt = CommonPagination.getPagingTool(outboundJobDao.getEntityDAO(), strCountHQL, strHQL, strUrl, 1, maxLine);

		} catch (Exception er) {
			throw new Exception("根据条件查询查询入库作业列表出错:" + er.getMessage());
		}

		return pt;
	}

	/**
	 * 功能:获得批次属性的SQL
	 * 
	 * @param flg
	 *            第几个批次属性
	 * @param lotatt
	 *            批次属性的值
	 * @param lotDetail
	 *            批次属性
	 * @return
	 * @throws Exception
	 */
	private String getSqlLotatt(String flg, String lotatt, BaseLotDetail lotDetail) {

		StringBuilder strBud = new StringBuilder();
		if (lotDetail != null) {

			// 页面查询时候的模式 1-精确查询,2-模糊查询(文本格式),3-范围查询(日期格式)
			String strLotsearch = lotDetail.getM_strLotsearch();

			if (strLotsearch != null && strLotsearch.equals("1")) { // 1-精确查询
				strBud.append(" and jobdetail.lotatt").append(flg).append(" ='").append(lotatt).append("'");
			} else if (strLotsearch != null && strLotsearch.equals("2")) { // 2-模糊查询
				strBud.append(" and jobdetail.lotatt").append(flg).append(" like '%").append(lotatt).append("%'");
			} else if (strLotsearch != null && strLotsearch.equals("3")) { // 3-范围查询(日期格式)
				String[] lotatts = lotatt.split("\\|");
				if (lotatts.length > 0 && lotatts[0] != null && lotatts[0].trim().length() > 0) {
					strBud.append(" and jobdetail.lotatt").append(flg).append(" >='").append(lotatts[0]).append("'");
				}
				if (lotatts.length > 1 && lotatts[1] != null && lotatts[1].trim().length() > 0) {
					strBud.append(" and jobdetail.lotatt").append(flg).append(" <='").append(lotatts[1]).append("'");
				}

			} else { // 没有选择查询方式的时候，按照精确查询来处理
				strBud.append(" and jobdetail.lotatt").append(flg).append(" ='").append(lotatt).append("'");
			}
		}
		return strBud.toString();
	}
	
	/**
	 * 功能:根据单据明细id查询作业明细
	 * @param outstockdetailid			单据明细id
	 * @return 
	 * @throws Exception
	 */
	public List getJobDetailsAndJobByOutboundDid(String outstockdetailid) throws Exception{
		String strSql = "";
		List list = null;
		
		try {
			strSql = " from InoutJobdetail as jobdetail,InoutJob as job where jobdetail.outstockdetailid='" + outstockdetailid + "' and jobdetail.jobid=job.jobid ";
			list = outboundJobDao.querySQL(strSql);
		} catch(Exception er) {
			er.printStackTrace();
			throw new Exception("出库库单管理->查询作业详细列表时候出错：" + er.getMessage());
		}
			
		return list;
	}

}
