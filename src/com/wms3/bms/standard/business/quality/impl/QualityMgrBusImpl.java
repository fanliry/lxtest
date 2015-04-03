package com.wms3.bms.standard.business.quality.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.business.base.ILotBus;
import com.wms3.bms.standard.business.base.impl.LotBusImpl;
import com.wms3.bms.standard.business.outbound.IOutBoundBus;
import com.wms3.bms.standard.business.outbound.impl.OutBoundBusImpl;
import com.wms3.bms.standard.business.quality.IQualityMgrBus;
import com.wms3.bms.standard.dao.inventory.IInventoryDao;
import com.wms3.bms.standard.dao.inventory.impl.InventoryDaoImpl;
import com.wms3.bms.standard.dao.job.IJobDao;
import com.wms3.bms.standard.dao.job.impl.JobDaoImpl;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;
/**
 * 描述：质检管理的业务实现类
 * @author liuxh
 * @since 2012-11-18
 *
 */
public class QualityMgrBusImpl implements IQualityMgrBus {
	protected ILotBus lotBus;
	protected EntityDAO dao;
	protected IOutBoundBus outBoundBus;
	protected IJobDao jobDao;
	protected IInventoryDao inventDao;
	public QualityMgrBusImpl(EntityDAO dao) {
		this.dao = dao;
		this.lotBus = new LotBusImpl(dao);	
		this.outBoundBus = new OutBoundBusImpl(dao);
		this.jobDao = new JobDaoImpl(dao);
		this.inventDao = new InventoryDaoImpl(dao);
	}
	/**
	  * 功能：更新库存抽检单状态和库存
	  * @param ids
	  * @param userCode
	  * @return
	  * @throws Exception
	  */
	public String updateCheckBoundUpdateInvent(String ids, String userCode)
			throws Exception {
		String strMeg="Y";
		OutboundInvoiceHeader outBoundHeader = null;
		List<OutboundInvoiceDetail> outBoundDetails = null;
		InoutJobdetail jobDel = null;
		InventoryStorage iStorage = null;
		
		try {	
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			//抽检单据
			outBoundHeader = outBoundBus.getOutBoundById(id[i]);
			//抽检单明细
			outBoundDetails = outBoundBus.getOutBoundDetailsById(id[i]);
			for (int j = 0; j < outBoundDetails.size(); j++) {
				//获得作业明细
				List jobLs = jobDao.getPickJobDetail(id[i], outBoundDetails.get(j).getOutstockdetailid());
				if (jobLs!=null&&jobLs.size()>0) {					
					jobDel = (InoutJobdetail) jobLs.get(0);
					//获得库存
					iStorage = getInventById(jobDel.getInventoryid());
					if (iStorage!=null) {
					//如果库存数量为0，删除库存
					if (iStorage.getPnum()==0.0) {
						dao.delete(iStorage);
					}else {
						iStorage.setStatus("2");//更新库存状态已经完成
						dao.update(iStorage);
					}
					//更新抽检单明细状态
					outBoundDetails.get(j).setLinestatus("7");
					dao.update("OutboundInvoiceDetail",outBoundDetails.get(j));	
				}else {
					strMeg = "获取库存信息失败！";
					Logger.info("用户【" + userCode + "】在质检管理模块里更新了抽检单据：" + id[i]+"的状态获取库存失败");
					break;
				}
			 }else {
				 strMeg = "该单据没有对应的作业！";
				 Logger.info("用户【" + userCode + "】在质检管理模块里更新了抽检单据：" + id[i]+"的状态根据抽检单ID获取作业失败");
				 return strMeg;
			}
			}
			outBoundHeader.setOutstatus("7");
			dao.update("OutboundInvoiceHeader",outBoundHeader);	
			strMeg = "更新成功！";
			Logger.info("用户【" + userCode + "】在质检管理模块里更新了抽检单据：" + id[i]+"的状态");
		}
		
		} catch (Exception e) {
		throw new Exception("更新抽检单状态出错,QualityMgrBusImpl.updateCheckBoundUpdateInvent：" + e.getMessage());
		}
		return strMeg;
	}
	/**
	  * 功能：作废库存抽检单状态和库存
	  * @param ids
	  * @param userCode
	  * @return
	  * @throws Exception
	  */
	public String deleteCheckBoundUpdateInvent(String ids, String userCode)throws Exception {
		
		String strMeg="Y";
		OutboundInvoiceHeader outBoundHeader = null;
		List<OutboundInvoiceDetail> outBoundDetails = null;
		InoutJobdetail jobDel = null;
		InventoryStorage iStorage = null;
		
		try {	
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			//抽检单据
			outBoundHeader = outBoundBus.getOutBoundById(id[i]);
			if (outBoundHeader!=null&&outBoundHeader.getOutstatus().equals("7")) {
				strMeg = "发货确认的单据不能作废！";
				return strMeg;
			} else {
			//抽检单明细
			outBoundDetails = outBoundBus.getOutBoundDetailsById(id[i]);
			for (int j = 0; j < outBoundDetails.size(); j++) {
				//获得作业明细
				List jobLs = jobDao.getPickJobDetail(id[i], outBoundDetails.get(j).getOutstockdetailid());
				if (jobLs!=null&&jobLs.size()>0) {					
					jobDel = (InoutJobdetail) jobLs.get(0);
					//获得库存
					iStorage = getInventById(jobDel.getInventoryid());
					if (iStorage!=null) {
					iStorage.setPnum(jobDel.getAssignnum());
					iStorage.setStatus("0");//还原库存状态：未分配
				    dao.update(iStorage);
					
				}else {
					strMeg = "获取库存信息失败！";
					Logger.info("用户【" + userCode + "】在质检管理模块里作废抽检单据：" + id[i]+"的状态获取库存失败");
					return strMeg;
				}
			 }else {
				 strMeg = "该单据没有对应的作业！";
				 Logger.info("用户【" + userCode + "】在质检管理模块里作废抽检单据：" + id[i]+"的状态根据抽检单ID获取作业失败");
				 return strMeg;
			}
				//更新抽检单明细状态
				outBoundDetails.get(j).setLinestatus("8");
				dao.update("OutboundInvoiceDetail",outBoundDetails.get(j));	
			}
			outBoundHeader.setOutstatus("8");
			dao.update("OutboundInvoiceHeader",outBoundHeader);	
			strMeg = "作废成功！";
			Logger.info("用户【" + userCode + "】在质检管理模块里作废了抽检单据：" + id[i]);
		}
		}
		
		} catch (Exception e) {
		throw new Exception("更新抽检单状态出错,QualityMgrBusImpl.updateCheckBoundUpdateInvent：" + e.getMessage());
		}
		return strMeg;
	}
	/**
	 * 功能：根据库存ID获得库存
	 * 
	 */
	public InventoryStorage getInventById(String id) throws Exception {
		
		return inventDao.getInventoryById(id);
	}
	
}
