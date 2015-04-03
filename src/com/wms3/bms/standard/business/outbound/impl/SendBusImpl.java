package com.wms3.bms.standard.business.outbound.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.outbound.IReviewBus;
import com.wms3.bms.standard.business.outbound.ISendBus;
import com.wms3.bms.standard.dao.base.IBaseCargoSpaceDao;
import com.wms3.bms.standard.dao.base.IBaseWhAreaDao;
import com.wms3.bms.standard.dao.base.impl.BaseCargoSpaceDaoImpl;
import com.wms3.bms.standard.dao.base.impl.BaseSeqDaoImpl;
import com.wms3.bms.standard.dao.base.impl.BaseWhAreaDaoImpl;
import com.wms3.bms.standard.dao.inventory.IInventoryDao;
import com.wms3.bms.standard.dao.inventory.impl.InventoryDaoImpl;
import com.wms3.bms.standard.dao.job.IJobDao;
import com.wms3.bms.standard.dao.job.impl.JobDaoImpl;
import com.wms3.bms.standard.dao.outbound.IOutboundDao;
import com.wms3.bms.standard.dao.outbound.ISendDao;
import com.wms3.bms.standard.dao.outbound.impl.OutboundDaoImpl;
import com.wms3.bms.standard.dao.outbound.impl.SendDaoImpl;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BaseSeq;
import com.wms3.bms.standard.entity.base.BaseWharea;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;

/**
 * 描述: 发货确认业务类
 * @author hug 2012-10-9
 * @since WMS 3.0
 */
public class SendBusImpl implements ISendBus{
    /** 作业DAO类 */
    protected IJobDao jobDAO;
    /** 出库单DAO类  */
    protected IOutboundDao outBoundDAO;
    /** 发货确认DAO类*/
    protected ISendDao sendDAO;
    /**单据复核DAO类    */
    protected IReviewBus reviewBus;
    /** 库存DAO类  */
    protected IInventoryDao inventoryDAO;
    /** 货位DAO类接口 */
    protected IBaseCargoSpaceDao spaceDao;
    
    public SendBusImpl(EntityDAO dao){
        jobDAO = new JobDaoImpl(dao);
        sendDAO = new SendDaoImpl(dao);
        outBoundDAO = new OutboundDaoImpl(dao);
        inventoryDAO = new InventoryDaoImpl(dao);
        reviewBus = new ReviewBusImpl(dao);
        spaceDao = new BaseCargoSpaceDaoImpl(dao);
    }

	/**
	 * A客户到暂存区 (non-Javadoc)
	 * 
	 * @see com.wms3.bms.standard.business.outbound.ISendBus#getAtoZ(java.lang.String,
	 *      double, double, double, double, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getAtoZ(String strJobDetailId, double num, double weight, double netweight, double volume, String strUserCode, String strShiftid, String warehouseId, String whAreaId,
			String whAreaSpaceId) throws Exception {
		String strMsg = "Y";	
		String loginfo = "";
		synchronized (strJobDetailId) {
			Session session = null;
			// 获得作业详细
			InoutJobdetail jobDetail = jobDAO.getJobDetailByDetailId(strJobDetailId);
			if (jobDetail != null) {
				// 获得作业
				InoutJob job = jobDAO.getJobById(jobDetail.getJobid());
				// 单据详细状态
				OutboundInvoiceDetail invoiceDetail = outBoundDAO.getOutBoundDetailById(job.getBoundstockdetailid());
				InventoryStorage inventoryStorage = inventoryDAO.getInventoryByIdAndInventoryType(job.getTraycode());
				IBaseWhAreaDao baseWharea = new BaseWhAreaDaoImpl(sendDAO.getEntityDAO());// 暂存区
				BaseWharea whareIda = baseWharea.getZCgetWhAreaByWhid(job.getWarehouseid(),job.getScargoWhareaId());
				String whAreaSpaceid = whareIda.getwhAreaId() + "010101";
				BaseCargospace baseSpace = spaceDao.getCargoSpaceById(whAreaSpaceid);// 货位
				try {
					session = sendDAO.getEntityDAO().getSession();
					Transaction tx = session.beginTransaction();
					// 单据详细不能是发货确认状态
					if (invoiceDetail != null && !invoiceDetail.getLinestatus().equals("7")) { // 不为发货确认状态
						if (job != null && job.getStatus().trim().equals("4")) {
							// 当调整数量小于等于作业数量
							if (num <= jobDetail.getJobnum()) {
								if (num <= jobDetail.getJobnum() - jobDetail.getAssignnum() && num > 0) {
									jobDetail.setJobnum(jobDetail.getJobnum() - num);
									session.update(jobDetail);
									// 库存表的修改
									if (inventoryStorage != null && inventoryStorage.getTraycode().equals(job.getTraycode())) {
										inventoryStorage.setPnum(inventoryStorage.getPnum() + num);
										loginfo = "作业id："+job.getJobid()+"托盘条码："+job.getTraycode()+" 与暂存中存在的产品进行合并,合并数量为"+inventoryStorage.getPnum();
										session.update(inventoryStorage);
									} else if (inventoryStorage == null) {
										InventoryStorage inventoryStorage1 = new InventoryStorage();
										inventoryStorage1.setCargoSpaceId(whAreaSpaceid); // 货位ID
										inventoryStorage1.setWhAreaId(whareIda.getwhAreaId()); // 库区ID（暂存区ID）
										inventoryStorage1.setWarehouseid(job.getWarehouseid()); // 仓库ID
										inventoryStorage1.setStatus("0"); // 状态 0:未分配，1：已分配  3:调整 4:货品调整；5：盘点
										inventoryStorage1.setPnum(num); // 库存数量
										inventoryStorage1.setPvolume(volume); // 库存体积
										inventoryStorage1.setPweight(weight); // 库存重量
										inventoryStorage1.setPnetweight(netweight);// 库存净重
										inventoryStorage1.setProductid(jobDetail.getProductid());
										inventoryStorage1.setProductdate(jobDetail.getPrintdate());
										inventoryStorage1.setIndate(StrTools.getCurrDateTime(2));
										inventoryStorage1.setLotid(jobDetail.getLotid());
										inventoryStorage1.setLotinfo(jobDetail.getLotinfo());
										inventoryStorage1.setLotinfo2(jobDetail.getLotinfo2());
										inventoryStorage1.setSupplier(jobDetail.getSupplier());
										inventoryStorage1.setPackid(jobDetail.getPackid());
										inventoryStorage1.setIntype("2"); // 脱机
										inventoryStorage1.setPunit(jobDetail.getPunit());
										inventoryStorage1.setInjobid(job.getJobid());
										inventoryStorage1.setTraycode(job.getTraycode());
										inventoryStorage1.setInstockid(job.getBoundstockid());
										inventoryStorage1.setProductcode(jobDetail.getProductcode()); // 产品编码
										inventoryStorage1.setProductstatus(jobDetail.getProductStatus()); // 物品状态
										inventoryStorage1.setReserve3(jobDetail.getReserve3());// 预留3
										inventoryStorage1.setReserve4(jobDetail.getReserve4());// 预留4
										session.save(inventoryStorage1);
										loginfo = "作业id："+job.getJobid()+"托盘条码："+job.getTraycode()+" 移库到暂存,移库数量为"+inventoryStorage1.getPnum();
									}
								} else if (num >= jobDetail.getJobnum() - jobDetail.getAssignnum() && jobDetail.getJobnum() - jobDetail.getAssignnum() >= 0) {

									Double assignum = jobDetail.getAssignnum(); // 这把作业的以前分配数量
									jobDetail.setJobnum(jobDetail.getJobnum() - num);
									jobDetail.setAssignnum(jobDetail.getJobnum());
									session.update(jobDetail);

									// 库存表的修改
									if (inventoryStorage != null && inventoryStorage.getTraycode().equals(job.getTraycode())) {
										inventoryStorage.setPnum(inventoryStorage.getPnum() + num);
										session.update(inventoryStorage);
										loginfo = "1作业id："+job.getJobid()+"托盘条码："+job.getTraycode()+" 与暂存中存在的产品进行合并,合并数量为"+inventoryStorage.getPnum();
									} else if (inventoryStorage == null) {
										InventoryStorage inventoryStorage1 = new InventoryStorage();
										inventoryStorage1.setCargoSpaceId(whAreaSpaceid); // 货位ID
										inventoryStorage1.setWhAreaId(whareIda.getwhAreaId()); // 库区ID（暂存区ID）
										inventoryStorage1.setWarehouseid(job.getWarehouseid()); // 仓库ID
										inventoryStorage1.setStatus("0"); // 状态0:未分配，1：已分配 3:调整 4:货品调整；5：盘点
										inventoryStorage1.setPnum(num); // 库存数量
										inventoryStorage1.setPvolume(volume); // 库存体积
										inventoryStorage1.setPweight(weight); // 库存重量
										inventoryStorage1.setPnetweight(netweight);// 库存净重
										inventoryStorage1.setProductid(jobDetail.getProductid());
										inventoryStorage1.setProductdate(jobDetail.getPrintdate());
										inventoryStorage1.setIndate(StrTools.getCurrDateTime(2));
										inventoryStorage1.setLotid(jobDetail.getLotid());
										inventoryStorage1.setLotinfo(jobDetail.getLotinfo());
										inventoryStorage1.setLotinfo2(jobDetail.getLotinfo2());
										inventoryStorage1.setSupplier(jobDetail.getSupplier());
										inventoryStorage1.setPackid(jobDetail.getPackid());
										inventoryStorage1.setIntype("2"); // 脱机
										inventoryStorage1.setPunit(jobDetail.getPunit());
										inventoryStorage1.setInjobid(job.getJobid());
										inventoryStorage1.setTraycode(job.getTraycode());
										inventoryStorage1.setInstockid(job.getBoundstockid());
										inventoryStorage1.setProductcode(jobDetail.getProductcode()); // 产品编码
										inventoryStorage1.setProductstatus(jobDetail.getProductStatus()); // 物品状态
										inventoryStorage1.setReserve3(jobDetail.getReserve3());// 预留3
										inventoryStorage1.setReserve4(jobDetail.getReserve4());// 预留4
										session.save(inventoryStorage1);
										loginfo = "1作业id："+job.getJobid()+"托盘条码："+job.getTraycode()+" 移库到暂存,移库数量为"+inventoryStorage1.getPnum();
									}
									// 出库单明细
									invoiceDetail.setAssignnum(invoiceDetail.getAssignnum() + (jobDetail.getAssignnum() - assignum)); // (jobDetail.getAssignnum()-assignum)																									// 现在分配数量减去以前分配数量
									session.update(invoiceDetail);
								} else {
									strMsg = "该作业[" + jobDetail.getJobid() + "]调整数量不对，不能操作！";
								}
							} else {
								strMsg = "该作业[" + jobDetail.getJobid() + "]调整的数量不能大于该作业的分配数量！";
							}

						} else {
							strMsg = "该作业[" + jobDetail.getJobid() + "]未完成，不能操作！";
						}
					} else {
						strMsg = "该作业[" + jobDetail.getJobid() + "]的单据，已发货确认了！";
					}
					if (strMsg.equals("Y")) {
						tx.commit();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					sendDAO.getEntityDAO().closeSession(session);
				}
			} else {
				strMsg = "该作业详细[" + strJobDetailId + "]不存在，无法操作！";
			}
            Logger.info("用户["+strUserCode+"]：出库管理==>发货确认调整信息:" +loginfo);
		}
		return strMsg;
	}
    /**
     * A客户批量移到暂存区
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.ISendBus#getBatchAtoZ(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public String getBatchAtoZ(String strJobDetailIds, String strUserCode, String strShiftid, String warehouseId, String whAreaId, String whAreaSpaceId) throws Exception {
        String strMsg = ""; 
        String[] details = strJobDetailIds.split(",");
        for(int i=0; i<details.length; i++)
        {
            String strJobDetailId = details[i]; //作业详细ID
            synchronized (strJobDetailId)       //同步作业详细
            {
                //获得作业详细
                InoutJobdetail jobDetail = jobDAO.getJobDetailByDetailId(strJobDetailId);
                if(jobDetail != null){
                	InoutJob job = jobDAO.getJobById(jobDetail.getJobid());
                    //单据详细状态
                    OutboundInvoiceDetail  invoiceDetail = outBoundDAO.getOutBoundDetailById(job.getBoundstockdetailid());
                    //单据详细不能是发货确认状态
                    if(invoiceDetail != null && !invoiceDetail.getLinestatus().equals("7")){ //不为发货确认状态
                        //获得作业
                        if(job != null && job.getStatus().trim().equals("4")){
                                //增加到暂存区中
                                InventoryStorage inventoryStorage = new InventoryStorage();
                                inventoryStorage.setCargoSpaceId(whAreaSpaceId);    //货位ID
                                inventoryStorage.setWhAreaId(whAreaId);             //库区ID（暂存区ID）
                                inventoryStorage.setWarehouseid(warehouseId);       //仓库ID 
                                inventoryStorage.setStatus("0"); //状态  0:未分配，1：已分配 3:调整 4:货品调整；5：盘点
                                
                                inventoryStorage.setOwnerId(jobDetail.getOwnerId());    //货主ID
                                inventoryStorage.setProductid(jobDetail.getProductid());//物品ID
                                inventoryStorage.setPackid(jobDetail.getPackid());      //包装ID
                                inventoryStorage.setIndate(StrTools.getCurrDateTime(2));//入库时间
                                         
                                inventoryStorage.setIntype("2");                        //入库方式 1.联机 2.脱机
                                inventoryStorage.setPunit(jobDetail.getPunit());        //计量单位
                                inventoryStorage.setLotid(jobDetail.getLotid());        //批次ID
                                
                                inventoryStorage.setPnum(jobDetail.getJobnum());         //库存数量
                                inventoryStorage.setPvolume(jobDetail.getVolume());      //库存体积
                                inventoryStorage.setPweight(jobDetail.getWeight());      //库存重量
                                inventoryStorage.setPnetweight(jobDetail.getNetweight());//库存净重
                                inventoryStorage.setInjobid(jobDetail.getJobid());    //作业ID
                                inventoryStorage.setInjobetailid(strJobDetailId);     //作业详细ID
                                inventoryStorage.setTraycode(job.getTraycode());      //托盘条码
                               
                                inventoryStorage.setReserve3(jobDetail.getReserve3());//预留3
                                inventoryStorage.setReserve4(jobDetail.getReserve4());//预留4
                                 
                                
                                //删除作业详细
                                String strJobSQL = "delete from InoutJobdetail where jobdetailid='" + strJobDetailId + "'";   
                                //修改A单据详细的分配数量
                                String strAInvoiceSQL = "update OutboundInvoiceDetail set " +
                                "assignnum=assignnum-" + jobDetail.getJobnum() + ", " +
                                "assignnetweight=assignnetweight-" + jobDetail.getNetweight() + ", " +
                                "assignweight=assignweight-" + jobDetail.getWeight() + ", " +
                                "assignvolume=assignvolume-" + jobDetail.getVolume() + 
                                "  where outstockdetailid='" + job.getBoundstockdetailid() + "'";   
                                //A客户->暂存区
                                //sendDAO.sendAmoveZ(inventoryStorage, strJobSQL, strAInvoiceSQL);  
                                //更改复核数量
                                String strRevSQL = "";
                                boolean result= reviewBus.isCheckedByHeader(job.getBoundstockid(), "1");
                                if (result) {
    								strRevSQL = "update OutboundCrosscheck set qtyscan=qtyscan- " + jobDetail.getJobnum()+", where outstockdetailid='"+ job.getBoundstockdetailid()+",";
    							}else {
    								strRevSQL = "delete from OutboundCrosscheck where outstockdetailid='"+job.getBoundstockdetailid()+"'";
    							}
                                sendDAO.sendAmoveZ(inventoryStorage, strJobSQL, strAInvoiceSQL, strRevSQL);
                        }else{
                            strMsg = "该作业[" + jobDetail.getJobid() + "]未完成，不能操作！";
                        }  
                    }else{
                        strMsg = "该作业[" + jobDetail.getJobid() + "]的单据，已发货确认了！";
                    }
                }else{
                    strMsg = "该作业详细[" + strJobDetailId + "]不存在，无法操作！";
                }   
            }
        }
        return strMsg;
    }
    /**
     * A客户移动到B客户
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.ISendBus#getAtoB(java.lang.String, java.lang.String, java.lang.String, double, double, double, double, java.lang.String, java.lang.String)
     */
    public String getAtoB(String strBInvoiceId, String strBInvoiceDetailId, String strJobDetailId, double num, double weight, double netweight, double volume, String strUserCode, String strShiftid) throws Exception {
        String strMsg = "";
        synchronized (strJobDetailId) 
        {  
            //获得作业详细
            InoutJobdetail jobDetail = jobDAO.getJobDetailByDetailId(strJobDetailId);
            
            if(jobDetail != null){ 
            	InoutJob aJob = jobDAO.getJobById(jobDetail.getJobid());
                //B客户单据详细状态
                OutboundInvoiceDetail  binvoiceDetail = outBoundDAO.getOutBoundDetailById(strBInvoiceDetailId);
                //B客户单据详细不能是发货确认状态
                if(binvoiceDetail != null && !binvoiceDetail.getLinestatus().equals("7")){ //不为发货确认状态
                    //A客户单据详细状态
                    OutboundInvoiceDetail  invoiceDetail = outBoundDAO.getOutBoundDetailById(aJob.getBoundstockdetailid());
                    //A客户单据详细不能是发货确认状态
                    if(invoiceDetail != null && !invoiceDetail.getLinestatus().equals("7")){ //不为发货确认状态
                        //获得作业
                        if(aJob != null && aJob.getStatus().trim().equals("4")){
                            //判断作业详细数量
                            if(jobDetail.getAssignnum() >= num){
                                //增加作业及作业详细  修改作业数量  
                                //增加作业及作业详细
                                InoutJob bJob = new InoutJob();
                                
                                BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(jobDAO.getEntityDAO());
				                BaseSeq  seqEn = seqDao.getSeqByType("CKZY");
				                String strID = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), jobDAO.getEntityDAO());
				                
                                bJob.setJobid(strID);//设置作业ID
                                bJob.setJobtype(aJob.getJobtype());  //作业类型1-入库2-出库3-移库
                                bJob.setOnLineType("2"); //联机模式 1.联机 2.脱机
                                bJob.setIsaccount("Y");  //是否记账 Y.是 N.否
                                bJob.setStatus("4");     //作业状态 1.未执行 2.待执行 3.正执行 4.完成 5.作废 6.异常作业 8.暂停作业
                                bJob.setPriority(10);    //优先级
                                bJob.setCreatetime(StrTools.getCurrDateTime(2)); //时间 时间格式：yyyy-MM-dd hh:mm:ss
                                bJob.setJobpos("2");     //作业方向 1.入库端 2.出库端
                                bJob.setInOutType("2");  //入出类型 0:收货1上架入库 2.出库
                                bJob.setShiftid(strShiftid);    //作业班次
                                bJob.setWarehouseid(aJob.getWarehouseid());//仓库ID
                                bJob.setTraycode(aJob.getTraycode());   //托盘条码
                                bJob.setOpManId(strUserCode);    //操作人
                                bJob.setInvoicetype("2");//1:先有作业后生成单据;2:先有单据后生成作业
                                
                                bJob.setScargoWhareaId(aJob.getScargoWhareaId());//源库区ID
                                bJob.setScargoSpaceId(aJob.getScargoSpaceId());  //源货位ID
                                bJob.setScargoAlleyId(aJob.getScargoAlleyId());  //源巷道ID 
                                bJob.setBoundstockid(strBInvoiceId);//B单据ID
                                bJob.setBoundstockdetailid(strBInvoiceDetailId);//B单据详细ID
                                
                                //增加作业详细
                                InoutJobdetail bjobdetail = new InoutJobdetail();
                                //作业详细ID
                                String strBJobDetailId = SeqTool.getDetailId(bJob.getJobid(), "1");
                                bjobdetail.setJobdetailid(strBJobDetailId);    //作业详细ID
                                bjobdetail.setJobid(bJob.getJobid());          //作业编号
                                bjobdetail.setLinestatus("4");  //状态0:新建 4:完成
                                bjobdetail.setInventoryid(jobDetail.getInventoryid());//库存ID
                                bjobdetail.setProductid(jobDetail.getProductid());  //物品ID
                                bjobdetail.setPackid(jobDetail.getPackid());//包装ID
                                bjobdetail.setPunit(jobDetail.getPunit());     //单位
                                bjobdetail.setLotid(jobDetail.getLotid());     //批次类型ID
                                bjobdetail.setJobnum(num);            //作业数量
                                bjobdetail.setAssignnum(num);         //分配数量
                                bjobdetail.setVolume(volume);         //体积
                                bjobdetail.setWeight(weight);         //重量
                                bjobdetail.setNetweight(netweight);   //净重
                                bjobdetail.setAssignvolume(volume);   //分配体积
                                bjobdetail.setAssignweight(weight);   //分配重量
                                bjobdetail.setAssignnetweight(netweight);//分配净重    
                                bjobdetail.setCustomerid(binvoiceDetail.getCustomid());//客户ID
                                bjobdetail.setOwnerId(jobDetail.getOwnerId());      //货主ID

                                
                                bjobdetail.setBoxcode(jobDetail.getBoxcode());      //条码
                                bjobdetail.setIsinvoice("Y");      //是否已生成单据 Y是  N否
                                bjobdetail.setInjobdetailid(jobDetail.getJobdetailid());  //源作业详细（生成出库作业时的添加该库存的入库作业）
                                bjobdetail.setReserve1(jobDetail.getReserve1()); //预留1
                                bjobdetail.setReserve2(jobDetail.getReserve2()); //预留2
                                bjobdetail.setReserve3(jobDetail.getReserve3()); //预留3
                                bjobdetail.setReserve4(jobDetail.getReserve4()); //预留4
                                 
                                //修改作业详细数量
                                String strJobSQL = "update InoutJobdetail set " +
                                                "jobnum=jobnum-" + num + ", " +
                                                "volume=volume-" + volume + ", " +
                                                "weight=weight-" + weight + ", " +
                                                "netweight=netweight-" + netweight + ", " +
                                                "assignnum=assignnum-" + num + ", " +
                                                "assignvolume=assignvolume-" + volume + ", " +
                                                "assignweight=assignweight-" + weight + ", " +
                                                "assignnetweight=assignnetweight-" + netweight + 
                                                " where jobdetailid='" + strJobDetailId + "'";
                                jobDetail.setJobnum(jobDetail.getJobnum() - num);       //作业数量
                                jobDetail.setWeight(jobDetail.getWeight() - weight);    //重量
                                jobDetail.setNetweight(jobDetail.getNetweight() - netweight);//净重
                                jobDetail.setVolume(jobDetail.getVolume() - volume);    //体积
                                jobDetail.setAssignnum(jobDetail.getAssignnum() - num);
                                jobDetail.setAssignnetweight(jobDetail.getAssignnetweight() - netweight);
                                jobDetail.setAssignweight(jobDetail.getAssignweight() - weight);
                                jobDetail.setAssignvolume(jobDetail.getAssignvolume() - volume);
                                
                                //修改A单据详细的分配数量
                                String strAInvoiceSQL = "update OutboundInvoiceDetail set " +
                                "assignnum=assignnum-" + num + ", " +
                                "assignnetweight=assignnetweight-" + netweight + ", " +
                                "assignweight=assignweight-" + weight + ", " +
                                "assignvolume=assignvolume-" + volume + 
                                " where outstockdetailid='" + bJob.getBoundstockdetailid() + "'";
                                invoiceDetail.setAssignnum(invoiceDetail.getAssignnum() - num);
                                invoiceDetail.setAssignnetweight(invoiceDetail.getAssignnetweight() - netweight);
                                invoiceDetail.setAssignweight(invoiceDetail.getAssignweight() - weight);
                                invoiceDetail.setAssignvolume(invoiceDetail.getAssignvolume() - volume);
                                //修改B单据详细的分配数量
                                String strBInvoiceSQL = "update OutboundInvoiceDetail set " +
                                        "assignnum=assignnum+" + num + ", " +
                                        "assignnetweight=assignnetweight+" + netweight + ", " +
                                        "assignweight=assignweight+" + weight + ", " +
                                        "assignvolume=assignvolume+" + volume + 
                                        " where outstockdetailid='" + strBInvoiceDetailId + "'";
                                binvoiceDetail.setAssignnum(binvoiceDetail.getAssignnum() + num);
                                binvoiceDetail.setAssignnetweight(binvoiceDetail.getAssignnetweight() + netweight);
                                binvoiceDetail.setAssignweight(binvoiceDetail.getAssignweight() + weight);
                                binvoiceDetail.setAssignvolume(binvoiceDetail.getAssignvolume() + volume);
                                
                                //A客户->B客户
                                sendDAO.sendAmoveB(strJobSQL, bJob, bjobdetail, strAInvoiceSQL, strBInvoiceSQL); 
                                
                            }else{
                                strMsg = "该作业[" + jobDetail.getJobid() + "]作业详细["+ strJobDetailId +"]的数量["+jobDetail.getAssignnum()+"]小于下移数量["+num+"]，不能操作！";
                            }    
                        }else{
                            strMsg = "该作业[" + jobDetail.getJobid() + "]未完成，不能操作！";
                        } 
                        
                    }else{
                        strMsg = "A客户的作业["+jobDetail.getJobid()+"]的单据详细，已发货确认了！";
                    }
                    
                }else{
                    strMsg = "B客户的单据详细["+strBInvoiceDetailId+"]，已发货确认了！";
                }     
            }else{
                strMsg = "该作业详细[" + strJobDetailId + "]不存在，无法操作！";
            }   
        }
        return strMsg;
    }
    /**
     * A客户批量到B客户
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.ISendBus#getBatchAtoB(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public String getBatchAtoB(String strBInvoiceId, String strBInvoiceDetailId, String strJobDetailIds, String strUserCode, String strShiftid) throws Exception {
        String strMsg = "";
        String[] details = strJobDetailIds.split(",");
        for(int i=0; i<details.length; i++)
        {
            String strJobDetailId = details[i]; //作业详细ID
            synchronized (strJobDetailId) 
            {  
                //获得作业详细
                InoutJobdetail jobDetail = jobDAO.getJobDetailByDetailId(strJobDetailId);
                if(jobDetail != null){ 
                	InoutJob job = jobDAO.getJobById(jobDetail.getJobid());
                    //B客户单据详细状态
                    OutboundInvoiceDetail  binvoiceDetail = outBoundDAO.getOutBoundDetailById(strBInvoiceDetailId);
                    //B客户单据详细不能是发货确认状态
                    if(binvoiceDetail != null && !binvoiceDetail.getLinestatus().equals("7")){ //不为发货确认状态
                        //A客户单据详细状态
                        OutboundInvoiceDetail  invoiceDetail = outBoundDAO.getOutBoundDetailById(job.getBoundstockdetailid());
                        //A客户单据详细不能是发货确认状态
                        if(invoiceDetail != null && !invoiceDetail.getLinestatus().equals("7")){ //不为发货确认状态
                            //获得作业
                            InoutJob aJob = jobDAO.getJobById(jobDetail.getJobid());
                            if(aJob != null && aJob.getStatus().trim().equals("4")){ 
                                    //增加作业及作业详细
                                    InoutJob bJob = new InoutJob();
                                    BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(jobDAO.getEntityDAO());
    				                BaseSeq  seqEn = seqDao.getSeqByType("CKZY");
    				                String strID = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), jobDAO.getEntityDAO());//作业ID
    				                
                                    bJob.setJobid(strID);    //设置作业ID
                                    bJob.setJobtype(aJob.getJobtype());  //作业类型1-入库2-出库3-移库
                                    bJob.setOnLineType("2"); //联机模式 1.联机 2.脱机
                                    bJob.setIsaccount("Y");  //是否记账 Y.是 N.否
                                    bJob.setStatus("4");     //作业状态 1.未执行 2.待执行 3.正执行 4.完成 5.作废 6.异常作业 8.暂停作业
                                    bJob.setPriority(10);    //优先级
                                    bJob.setCreatetime(StrTools.getCurrDateTime(2)); //时间 时间格式：yyyy-MM-dd hh:mm:ss
                                    bJob.setJobpos("2");     //作业方向 1.入库端 2.出库端
                                    bJob.setInOutType("2");  //入出类型 0:收货1上架入库 2.出库
                                    bJob.setShiftid(strShiftid);    //作业班次
                                    bJob.setWarehouseid(aJob.getWarehouseid()); //仓库ID
                                    bJob.setTraycode(aJob.getTraycode());       //托盘条码
                                    bJob.setOpManId(strUserCode);               //操作人
                                    bJob.setInvoicetype("2");   //1:先有作业后生成单据;2:先有单据后生成作业
                                    
                                    bJob.setScargoWhareaId(aJob.getScargoWhareaId());//源库区ID
                                    bJob.setScargoSpaceId(aJob.getScargoSpaceId());  //源货位ID
                                    bJob.setScargoAlleyId(aJob.getScargoAlleyId());  //源巷道ID 
                                    bJob.setBoundstockid(strBInvoiceId);//B单据ID
                                    bJob.setBoundstockdetailid(strBInvoiceDetailId);//B单据详细ID
                                    
                                    //增加作业详细
                                    InoutJobdetail bjobdetail = new InoutJobdetail();
                                    //作业详细ID
                                    String strBJobDetailId = SeqTool.getDetailId(bJob.getJobid(), "1");
                                    bjobdetail.setJobdetailid(strBJobDetailId);    //作业详细ID
                                    bjobdetail.setJobid(bJob.getJobid());          //作业编号
                                    bjobdetail.setLinestatus("4");                 //状态0:新建 4:完成
                                    bjobdetail.setInventoryid(jobDetail.getInventoryid());//库存ID
                                    bjobdetail.setProductid(jobDetail.getProductid());  //物品ID
                                    bjobdetail.setPackid(jobDetail.getPackid());        //包装ID
                                    bjobdetail.setPunit(jobDetail.getPunit());          //单位
                                    bjobdetail.setLotid(jobDetail.getLotid());          //批次类型ID
                                    bjobdetail.setJobnum(jobDetail.getJobnum());        //作业数量
                                    bjobdetail.setAssignnum(jobDetail.getJobnum());     //分配数量
                                    bjobdetail.setVolume(jobDetail.getVolume());        //体积
                                    bjobdetail.setWeight(jobDetail.getWeight());        //重量
                                    bjobdetail.setNetweight(jobDetail.getNetweight());  //净重
                                    bjobdetail.setAssignvolume(jobDetail.getVolume());  //分配体积
                                    bjobdetail.setAssignweight(jobDetail.getWeight());  //分配重量
                                    bjobdetail.setAssignnetweight(jobDetail.getNetweight());//分配净重    
                                    bjobdetail.setCustomerid(binvoiceDetail.getCustomid()); //客户ID
                                    bjobdetail.setOwnerId(jobDetail.getOwnerId());      //货主ID
  
                                    
                                    bjobdetail.setBoxcode(jobDetail.getBoxcode());      //条码
                                    bjobdetail.setIsinvoice("Y");      //是否已生成单据 Y是  N否
                                    bjobdetail.setInjobdetailid(jobDetail.getJobdetailid());  //源作业详细（生成出库作业时的添加该库存的入库作业）
                                    bjobdetail.setReserve1(jobDetail.getReserve1()); //预留1
                                    bjobdetail.setReserve2(jobDetail.getReserve2()); //预留2
                                    bjobdetail.setReserve3(jobDetail.getReserve3()); //预留3
                                    bjobdetail.setReserve4(jobDetail.getReserve4()); //预留4
                                     
                                    //修改作业详细数量
                                    String strJobSQL = "delete from InoutJobdetail  where jobdetailid='" + strJobDetailId + "'";
                                    
                                    //修改A单据详细的分配数量
                                    String strAInvoiceSQL = "update OutboundInvoiceDetail set " +
                                    "assignnum=assignnum-" + jobDetail.getJobnum() + ", " +
                                    "assignnetweight=assignnetweight-" + jobDetail.getNetweight() + ", " +
                                    "assignweight=assignweight-" + jobDetail.getWeight() + ", " +
                                    "assignvolume=assignvolume-" + jobDetail.getVolume() + 
                                    " where outstockdetailid='" + bJob.getBoundstockdetailid() + "'";
                                    invoiceDetail.setAssignnum(invoiceDetail.getAssignnum() - jobDetail.getJobnum());
                                    invoiceDetail.setAssignnetweight(invoiceDetail.getAssignnetweight() - jobDetail.getNetweight());
                                    invoiceDetail.setAssignweight(invoiceDetail.getAssignweight() - jobDetail.getWeight());
                                    invoiceDetail.setAssignvolume(invoiceDetail.getAssignvolume() - jobDetail.getVolume());
                                    //修改B单据详细的分配数量
                                    String strBInvoiceSQL = "update OutboundInvoiceDetail set " +
                                            "assignnum=assignnum+" + jobDetail.getJobnum() + ", " +
                                            "assignnetweight=assignnetweight+" + jobDetail.getNetweight() + ", " +
                                            "assignweight=assignweight+" + jobDetail.getWeight() + ", " +
                                            "assignvolume=assignvolume+" + jobDetail.getVolume() + 
                                            " where outstockdetailid='" + strBInvoiceDetailId + "'";
                                    binvoiceDetail.setAssignnum(binvoiceDetail.getAssignnum() + jobDetail.getJobnum());
                                    binvoiceDetail.setAssignnetweight(binvoiceDetail.getAssignnetweight() + jobDetail.getNetweight());
                                    binvoiceDetail.setAssignweight(binvoiceDetail.getAssignweight() + jobDetail.getWeight());
                                    binvoiceDetail.setAssignvolume(binvoiceDetail.getAssignvolume() + jobDetail.getVolume());
                                    
                                    //A客户->B客户
                                    sendDAO.sendAmoveB(strJobSQL, bJob, bjobdetail, strAInvoiceSQL, strBInvoiceSQL);   
                            }else{
                                strMsg = "该作业[" + jobDetail.getJobid() + "]未完成，不能操作！";
                            } 
                        }else{
                            strMsg = "A客户的作业["+jobDetail.getJobid()+"]的单据详细，已发货确认了！";
                        }  
                    }else{
                        strMsg = "B客户的单据详细["+strBInvoiceDetailId+"]，已发货确认了！";
                    }     
                }else{
                    strMsg = "该作业详细[" + strJobDetailId + "]不存在，无法操作！";
                }   
            }
        }
        return strMsg;
    }
    
    /**
     * 暂存区到A客户
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.ISendBus#getZtoA(java.lang.String, java.lang.String, double, double, double, double, java.lang.String, java.lang.String)
     */
    public String getZtoA(String strAInvoiceDetailId, String strInventoryId, double num, double weight, double netweight, double volume, String strUserCode, String strShiftid) throws Exception {
        String strMsg = "";
        synchronized (strInventoryId) 
        {  
            //获得库存
            InventoryStorage inventory = inventoryDAO.getInventoryById(strInventoryId);
            if(inventory != null){ 
                //A客户单据详细状态
                OutboundInvoiceDetail  ainvoiceDetail = outBoundDAO.getOutBoundDetailById(strAInvoiceDetailId);
                //A客户单据详细不能是发货确认状态
                if(ainvoiceDetail != null && !ainvoiceDetail.getLinestatus().equals("3")){ //不为发货确认状态
                    OutboundInvoiceHeader outInvoice = outBoundDAO.getOutBoundById(ainvoiceDetail.getOutstockid());//单据
                    //判断库存数量
                    //库存实际数量
                    double realnum =  inventory.getPnum() - inventory.getAssignnum() - inventory.getHoldnum(); 
                    if(realnum >= num){
                        //增加作业及作业详细
                        InoutJob bJob = new InoutJob();
                        
                        BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(jobDAO.getEntityDAO());
		                BaseSeq  seqEn = seqDao.getSeqByType("CKZY");
		                String strID = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), jobDAO.getEntityDAO());//作业ID
                        bJob.setJobid(strID);//设置作业ID
                        bJob.setJobtype(outInvoice.getOuttype());  //作业类型1-入库2-出库3-移库
                        bJob.setOnLineType("2"); //联机模式 1.联机 2.脱机
                        bJob.setIsaccount("Y");  //是否记账 Y.是 N.否
                        bJob.setStatus("4");     //作业状态 1.未执行 2.待执行 3.正执行 4.完成 5.作废 6.异常作业 8.暂停作业
                        bJob.setPriority(10);    //优先级
                        bJob.setCreatetime(StrTools.getCurrDateTime(2)); //时间 时间格式：yyyy-MM-dd hh:mm:ss
                        bJob.setJobpos("2");     //作业方向 1.入库端 2.出库端
                        bJob.setInOutType("2");  //入出类型 0:收货1上架入库 2.出库
                        bJob.setShiftid(strShiftid);    //作业班次
                        bJob.setWarehouseid(inventory.getWarehouseid());//仓库ID
                        bJob.setTraycode(inventory.getTraycode());   //托盘条码
                        bJob.setOpManId(strUserCode);    //操作人
                        bJob.setInvoicetype("2");//1:先有作业后生成单据;2:先有单据后生成作业
                        
                        bJob.setScargoWhareaId(inventory.getWhAreaId());//源库区ID
                        bJob.setScargoSpaceId(inventory.getCargoSpaceId());  //源货位ID
                        bJob.setScargoAlleyId(inventory.getCargoAlleyId());  //源巷道ID 
                        bJob.setBoundstockid(ainvoiceDetail.getOutstockid());//单据ID
                        bJob.setBoundstockdetailid(ainvoiceDetail.getOutstockdetailid());//单据详细ID
                        
                        //增加作业详细
                        InoutJobdetail bjobdetail = new InoutJobdetail();
                        //作业详细ID
                        String strBJobDetailId = SeqTool.getDetailId(bJob.getJobid(), "1");
                        bjobdetail.setJobdetailid(strBJobDetailId);    //作业详细ID
                        bjobdetail.setJobid(bJob.getJobid());          //作业编号
                        bjobdetail.setLinestatus("2");  //状态0:新建 2:完成
                        bjobdetail.setInventoryid(inventory.getInventoryid());//库存ID
                        bjobdetail.setProductid(inventory.getProductid());  //物品ID
                        
                        bjobdetail.setPackid(inventory.getPackid());//包装ID
                        bjobdetail.setPunit(inventory.getPunit());     //单位
                        bjobdetail.setLotid(inventory.getLotid());     //批次类型ID
                        
                        bjobdetail.setJobnum(num);        //作业数量
                        bjobdetail.setAssignnum(num);     //分配数量
                        bjobdetail.setVolume(volume);         //体积
                        bjobdetail.setWeight(weight);         //重量
                        bjobdetail.setNetweight(netweight);   //净重
                        bjobdetail.setAssignvolume(volume);   //分配体积
                        bjobdetail.setAssignweight(weight);   //分配重量
                        bjobdetail.setAssignnetweight(netweight);//分配净重 
                        
                        bjobdetail.setCustomerid(ainvoiceDetail.getCustomid());//客户ID
                        bjobdetail.setOwnerId(inventory.getOwnerId());//货主ID
                        
                        bjobdetail.setIsinvoice("Y");      //是否已生成单据 Y是  N否
                        bjobdetail.setInjobdetailid(inventory.getInjobetailid());  //源作业详细（生成出库作业时的添加该库存的入库作业）
                       
                        bjobdetail.setReserve3(inventory.getReserve3());       //预留3
                        bjobdetail.setReserve4(inventory.getReserve4());       //预留4 
                        
                        //修改库存详细数量或删除库存????????????????????????????????????????????????????
                        String strUpdateInventorySql = null;
                        if(num < realnum){ //小于实际库存数量
                            strUpdateInventorySql = "update InventoryStorage set " +
                            "pnum=pnum-"+num+"," +
                            "pvolume=pvolume-" + volume + "," +
                            "pweight=pweight-"+weight+"," +
                            "pnetweight=pnetweight-"+netweight+"  " +
                            "where inventoryid='" + strInventoryId + "'";   
                        }else{//等于实际库存数量
                            //冻结为0就删除库存
                            if(inventory.getHoldnum() == 0){//直接删除库存
                                strUpdateInventorySql = "delete from InventoryStorage where inventoryid='" + strInventoryId + "'";
                            }else{//修改库存
                                strUpdateInventorySql = "update InventoryStorage set " +
                                "pnum=pnum-"+num+"," +
                                "pvolume=pvolume-" + volume + "," +
                                "pweight=pweight-"+weight+"," +
                                "pnetweight=pnetweight-"+netweight+"  " +
                                "where inventoryid='" + strInventoryId + "'";
                            }
                        }   
                        
                        //修改A单据详细的分配数量
                        String strInvoiceSQL = "update OutboundInvoiceDetail set " +
                        "assignnum=assignnum+" + num + ", " +
                        "assignnetweight=assignnetweight+" + netweight + ", " +
                        "assignweight=assignweight+" + weight + ", " +
                        "assignvolume=assignvolume+" + volume + 
                        " where outstockdetailid='" + strAInvoiceDetailId + "'";
                        ainvoiceDetail.setAssignnum(ainvoiceDetail.getAssignnum() + num);
                        ainvoiceDetail.setAssignnetweight(ainvoiceDetail.getAssignnetweight() + netweight);
                        ainvoiceDetail.setAssignweight(ainvoiceDetail.getAssignweight() + weight);
                        ainvoiceDetail.setAssignvolume(ainvoiceDetail.getAssignvolume() + volume);
                 
                        //暂存区->A客户
                        sendDAO.sendZmoveA(strUpdateInventorySql, bJob, bjobdetail, strInvoiceSQL); 
                        
                    }else{
                        strMsg = "该库存[" + strInventoryId + "]的数量["+realnum+"]小于下移数量["+num+"]，不能操作！";
                    } 
                }else{
                    strMsg = "B客户的单据详细["+strAInvoiceDetailId+"]，已发货确认了！";
                }     
            }else{
                strMsg = "该库存[" + strInventoryId + "]不存在，无法操作！";
            }   
        }
        return strMsg;
    }
    
    /**
     * 按单据发货确认
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.ISendBus#outInvoiceFHQR(java.lang.String, java.lang.String, java.lang.String)
     */
    public String outInvoiceFHQR(String strInvoiceId, String strUserCode, String strShiftid) throws Exception {
        String strMsg = "Y";
        synchronized (strInvoiceId) 
        {
            OutboundInvoiceHeader outInvoice = outBoundDAO.getOutBoundById(strInvoiceId);//单据
            if(outInvoice != null && (!outInvoice.getOutstatus().equals("7") || !outInvoice.getOutstatus().equals("8"))){
            	//7:发货确认 8:作废
                //检测该单据未完成的作业数(不为4:完成和5:作废)
                int iJob = jobDAO.getUnfinishedJob(strInvoiceId, null);
                int iJobFH = jobDAO.getUncheckUpJob(strInvoiceId, null);
                
                if(iJob != 0)
                {
                    strMsg = "该单据["+strInvoiceId+"]有【" + iJob + "】条作业未完成！";
                }else if(iJobFH != 0){
                	strMsg = "该单据["+strInvoiceId+"]有【" + iJob + "】条作业未复核！";
                }else{
                    //根据单据号查询所要操作的作业详细，发货剩下的
                    List ls = jobDAO.getRemainJobDetail(strInvoiceId, null);
                    if(ls != null && ls.size()>0){
                        //返回
                        strMsg = "该单据["+strInvoiceId+"]有【" + ls.size() + "】条剩余产品未移到暂存区！";   
                        return strMsg;
                    }   
                    //检测单据详细的开单数量与发货数量
                    List<OutboundInvoiceDetail> lsDetail = outBoundDAO.getOutBoundDetailsById(strInvoiceId);
                    List<String> lsSQL = new ArrayList<String>();//要更新的单据详细SQL语句
                    if(lsDetail != null && lsDetail.size()>0)
                    {
                        OutboundInvoiceDetail outDetail = null;//出库单详细
                        for(int i = 0; i < lsDetail.size(); i++)
                        {
                            outDetail = lsDetail.get(i);
                            if(outDetail != null && !outDetail.getLinestatus().equals("7")){//单据详细没发货确认 
                                
                                //发货数量
                                Object[] numobj = jobDAO.getInvoiceDetailFinishNum(outDetail.getOutstockdetailid());
                                Double dNum = numobj[0] == null ? 0 : (Double)numobj[0];        //数量
                                Double dWeight = numobj[1] == null ? 0 : (Double)numobj[1];     //毛重
                                Double dNetWeight = numobj[2] == null ? 0 : (Double)numobj[2];  //净重
                                Double dVolume = numobj[3] == null ? 0 : (Double)numobj[3];     //体积
                                
                                //单据详细发货确认操作
                                outDetail.setSendnum(dNum != null ? dNum.doubleValue(): 0);
                                outDetail.setSweight(dWeight != null ? dWeight.doubleValue(): 0);
                                outDetail.setSnetweight(dNetWeight != null ? dNetWeight.doubleValue(): 0);
                                outDetail.setSvolume(dVolume != null ? dVolume.doubleValue() : 0);
                                outDetail.setLinestatus("7");
                                String strInvoiceDetailSQL = "update OutboundInvoiceDetail set linestatus='7', sendnum="+(dNum != null ? dNum.doubleValue(): 0)+", sweight="+(dWeight != null ? dWeight.doubleValue(): 0)+",snetweight="+(dNetWeight != null ? dNetWeight.doubleValue(): 0)+",svolume="+(dVolume != null ? dVolume.doubleValue() : 0)+"  where outstockdetailid='" + outDetail.getOutstockdetailid() + "'";
                                lsSQL.add(strInvoiceDetailSQL);    
                            }
                        }
                    }
                    //发货确认
                    outInvoice.setConfirmdate(StrTools.getCurrDateTime(1));
                    outInvoice.setConfirmanid(strUserCode);
                    outInvoice.setOutstatus("7");
                    String strInvoiceSQL = "update OutboundInvoiceHeader set outstatus='7',confirmanid='" + strUserCode + "', confirmdate='" + StrTools.getCurrDateTime(1) + "' where outstockid='" + strInvoiceId + "'";
                    
                    //发货确认操作
                    sendDAO.outInvoiceFHQR(strInvoiceSQL, lsSQL); 
                }  
            }else{
                strMsg = "该单据[" + strInvoiceId + "]不存在或单据状态不为审核状态，无法发货确认！";
            } 
        }
        return strMsg;
    }
    
    /**
     * 按单据详细发货确认
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.ISendBus#outInvoiceDetailFHQR(java.lang.String, java.lang.String, java.lang.String)
     */
    public String outInvoiceDetailFHQR(String strInvoiceDetailId, String strUserCode, String strShiftid) throws Exception {
        String strMsg = "Y";
        synchronized (strInvoiceDetailId) 
        {
            //出库单详细
            OutboundInvoiceDetail outDetail = outBoundDAO.getOutBoundDetailById(strInvoiceDetailId);
            if(outDetail != null && !outDetail.getLinestatus().equals("3")){//单据详细没发货确认 
                //出库单
                String strInvoiceId = outDetail.getOutstockid();
                OutboundInvoiceHeader outInvoice = outBoundDAO.getOutBoundById(strInvoiceId);
                if(outInvoice != null && (!outInvoice.getOutstatus().equals("7") || !outInvoice.getOutstatus().equals("8"))){  
                    //检测该单据的单据详细未完成的作业数(不为4:完成和5:作废)
                    int iJob = jobDAO.getUnfinishedJob(strInvoiceId, strInvoiceDetailId);
                    int iJobFH = jobDAO.getUncheckUpJob(strInvoiceId, strInvoiceDetailId);
                    if(iJob != 0)
                    {
                        strMsg = "该单据["+strInvoiceId+"]单据详细[" + strInvoiceDetailId + "]有【" + iJob + "】条作业未完成！";
                    }else if(iJobFH!=0){
                    	strMsg = "该单据["+strInvoiceId+"]单据详细[" + strInvoiceDetailId + "]有【" + iJob + "】条作业未复核！";
                    }else{
                        //根据单据号的单据详细查询所要操作的作业详细，发货剩下的
                        List ls = jobDAO.getRemainJobDetail(strInvoiceId, strInvoiceDetailId);
                        if(ls != null && ls.size()>0){
                            //返回
                            strMsg = "该单据["+strInvoiceId+"]单据详细[" + strInvoiceDetailId + "]有【" + ls.size() + "】条剩余产品未移到暂存区！";   
                            return strMsg;
                        }   
                        //检测单据详细的开单数量与发货数量
                        //发货数量
                        Object[] numobj = jobDAO.getInvoiceDetailFinishNum(outDetail.getOutstockdetailid());
                        Double dNum = numobj[0] == null ? 0 : (Double)numobj[0];        //数量
                        Double dWeight = numobj[1] == null ? 0 : (Double)numobj[1];     //毛重
                        Double dNetWeight = numobj[2] == null ? 0 : (Double)numobj[2];  //净重
                        Double dVolume = numobj[3] == null ? 0 : (Double)numobj[3];     //体积
                           
                        //单据详细发货确认操作
                        outDetail.setSendnum(dNum != null ? dNum.doubleValue(): 0);
                        outDetail.setSweight(dWeight != null ? dWeight.doubleValue(): 0);
                        outDetail.setSnetweight(dNetWeight != null ? dNetWeight.doubleValue(): 0);
                        outDetail.setSvolume(dVolume != null ? dVolume.doubleValue() : 0);
                        outDetail.setLinestatus("7");
                        String strInvoiceDetailSQL = "update OutboundInvoiceDetail set linestatus='7', sendnum="+(dNum != null ? dNum.doubleValue(): 0)+", sweight="+(dWeight != null ? dWeight.doubleValue(): 0)+",snetweight="+(dNetWeight != null ? dNetWeight.doubleValue(): 0)+",svolume="+(dVolume != null ? dVolume.doubleValue() : 0)+"  where outstockdetailid='" + outDetail.getOutstockdetailid() + "'";
                        sendDAO.outInvoiceDetailFHQR(strInvoiceDetailSQL);
       
                                
                        //发货确认 需判断
                        List lsDetail = sendDAO.getNoFHQRDetail(strInvoiceId);
                        if(lsDetail != null && lsDetail.size() == 0)
                        {
                            outInvoice.setConfirmdate(StrTools.getCurrDateTime(1));
                            outInvoice.setConfirmanid(strUserCode);
                            outInvoice.setOutstatus("7");
                            String strInvoiceSQL = "update OutboundInvoiceHeader set outstatus='7',confirmanid='" + strUserCode + "', confirmdate='" + StrTools.getCurrDateTime(1) + "' where outstockid='" + strInvoiceId + "'";
                            
                            //单据发货确认操作
                            sendDAO.outInvoiceDetailFHQR(strInvoiceSQL);   
                        }
                    }  
                }else{
                    strMsg = "该单据[" + outDetail.getOutstockid() + "]不存在或单据状态不为分配状态，无法发货确认！";
                } 
            }else{
                strMsg = "该单据详细[" + strInvoiceDetailId + "]不存在或已经发货确认了，无法进行发货确认！";
            }
        }
        return strMsg;
    }
    /**
     * 根据单据ID和单据详细ID获得作业及作业详细
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.ISendBus#getJobAndJobDetail(java.lang.String, java.lang.String)
     */
    public List getJobAndJobDetail(String invoiceid, String invoicedetailid) throws Exception {
        return jobDAO.getJobAndJobDetail(invoiceid, invoicedetailid);
    }
    /**
     * 根据仓库ID获得暂存区
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.ISendBus#getZCInventory(java.lang.String)
     */
    public List getZCInventory(String warehouseid) throws Exception {   
        return sendDAO.getZCInventory(warehouseid);
    }
    /**
     * 根据仓库ID获得暂存区
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.ISendBus#getZCInventory(java.lang.String)
     */
    public List getZCInventorybyTray(String warehouseid,String zcwhareaid,String traycode) throws Exception {   
        return sendDAO.getZCInventorybyTray(warehouseid,zcwhareaid,traycode);
    }
    
    /**
     * 根据托盘条码或箱条码获得单据已出库的作业及作业详细(已完成作业)
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.ISendBus#getJobAndJobDetailByCode(java.lang.String, java.lang.String, java.lang.String)
     */
    public List getJobAndJobDetailByCode(String invoiceid, String traycode, String boxcode) throws Exception {
        return sendDAO.getJobAndJobDetailByCode(invoiceid, traycode, boxcode);
    }
    /**
     * 根据作业ID获得作业
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.ISendBus#getJobById(java.lang.String)
     */
    public InoutJob getJobById(String strJobId) throws Exception {
        return jobDAO.getJobById(strJobId);
    }
    /**
     * 根据作业详细ID获得作业详细
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.ISendBus#getJobDetailByDetailId(java.lang.String)
     */
    public InoutJobdetail getJobDetailByDetailId(String strJobDetailId) throws Exception {
        return jobDAO.getJobDetailByDetailId(strJobDetailId);
    }


}
