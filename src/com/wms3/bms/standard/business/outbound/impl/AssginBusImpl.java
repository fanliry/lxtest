package com.wms3.bms.standard.business.outbound.impl;

import java.util.ArrayList;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.allot.impl.OutAllotRequestBean;
import com.wms3.bms.standard.business.allot.impl.OutAllotResponseBean;
import com.wms3.bms.standard.business.allot.impl.OutAllotVerifyRequestBean;
import com.wms3.bms.standard.business.base.IBaseInoutControlBus;
import com.wms3.bms.standard.business.base.impl.BaseInoutControlBusImpl;
import com.wms3.bms.standard.business.outbound.IAssginBus;
import com.wms3.bms.standard.constant.BMSConstant;
import com.wms3.bms.standard.dao.base.IBaseCargoSpaceDao;
import com.wms3.bms.standard.dao.base.impl.BaseCargoSpaceDaoImpl;
import com.wms3.bms.standard.dao.base.impl.BaseSeqDaoImpl;
import com.wms3.bms.standard.dao.inventory.IInventoryDao;
import com.wms3.bms.standard.dao.outbound.IAssginDao;
import com.wms3.bms.standard.dao.outbound.IOutboundDao;
import com.wms3.bms.standard.dao.outbound.impl.AssginDaoImpl;
import com.wms3.bms.standard.dao.outbound.impl.OutboundDaoImpl;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BaseInoutControl;
import com.wms3.bms.standard.entity.base.BaseLotSet;
import com.wms3.bms.standard.entity.base.BaseSeq;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

/**
 * 描述: 出库分配业务
 * @author hug 2012-9-20
 * @since WMS 3.0
 */
public class AssginBusImpl implements IAssginBus {
    /** 分配DAO类 */
    protected IAssginDao assginDao;
    /** 货位DAO类接口 */
    protected IBaseCargoSpaceDao spaceDao;

    /** 出库单DAO类  */
    protected IOutboundDao outBoundDAO;
    //库存
    protected IInventoryDao inventoryDao;
    protected EntityDAO m_dao;

    
    public AssginBusImpl(EntityDAO dao){
        assginDao = new AssginDaoImpl(dao);
        spaceDao = new BaseCargoSpaceDaoImpl(dao);
        outBoundDAO = new OutboundDaoImpl(dao);
        m_dao = dao;
    }
    public List<?> getAssginQueryStorage(List<BaseLotSet> lsLot, String ownerid, String warehouseId, String strProductId, String strPackId, String strLotatt1, String strLotatt2, String strLotatt3, String strLotatt4, String strLotatt5, String strLotatt6, String strLotatt7, String strLotatt8, String strLotatt9, String strLotatt10, String strLotatt11, String strLotatt12, String whAreaId, String alleyId, String strTrayCode) throws Exception {
        StringBuilder strBud = new StringBuilder();
        //StringBuilder strGrpBud = new StringBuilder(); //Groupby语句
        List lsStorage = null;
        try{
              strBud.append("select sto.whAreaId, sto.whAreaName, sto.cargoSpaceId, sto.cargoSpaceName, sto.traycode, ") 
                    .append("sto.productid, sto.productName, sto.punit, sto.punitname, ") 
                    .append("sto.pnum, ")
                    .append("sto.assignnum,")
                    .append("sto.pweight,") 
                    .append("sto.pvolume, ")
                    .append("sto.inventoryid, ");
             //strGrpBud.append(" group by sto.whAreaId, sto.cargoSpaceId, sto.traycode, sto.productid, sto.punit,sto.inventoryid, ");
                                
            //批次属性动态部分
            BaseLotSet lotSet = null;
            if(lsLot != null){
                for(int k = 0; k < lsLot.size(); k++){
                    lotSet = (BaseLotSet)lsLot.get(k);  
                    strBud.append("sto.").append(lotSet.getLotid()).append(", ");
                    //strGrpBud.append("sto.").append(lotSet.getLotid()).append(", ");
                }
            }
            strBud.append("sto.ownerId, sto.ownerName, sto.packid ");
            //strGrpBud.append("sto.ownerId, sto.packid  ");
            
            //表名*********************
            strBud.append(" from InventoryStorage sto where 1=1 ");
            
            //查询条件*where条件****************************************
            //仓库ID
            if(warehouseId != null && warehouseId.trim().length() > 0){
                strBud.append(" and sto.warehouseid='").append(warehouseId).append("'");
            }
            //库区ID
            if(whAreaId != null && whAreaId.trim().length() > 0){ 
                strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
            }
            //货主
            if(ownerid != null && ownerid.trim().length()>0){
                strBud.append(" and sto.ownerId='").append(ownerid).append("'");
            } 
            //品名ID
            if(strProductId != null && strProductId.trim().length() > 0){
                strBud.append(" and sto.productid='").append(strProductId).append("'");
            }
            //包装
            if(strPackId != null && strPackId.trim().length() > 0){
                strBud.append(" and sto.packid='").append(strPackId).append("'");
            }
            //批次属性1
            if(strLotatt1 != null && strLotatt1.trim().length() > 0){
                strBud.append(" and sto.lotatt1='").append(strLotatt1).append("'");
            }
            //批次属性2
            if(strLotatt2 != null && strLotatt2.trim().length() > 0){
                strBud.append(" and sto.lotatt2='").append(strLotatt2).append("'");
            }
            //批次属性3
            if(strLotatt3 != null && strLotatt3.trim().length() > 0){
                strBud.append(" and sto.lotatt3='").append(strLotatt3).append("'");
            }
            //批次属性4
            if(strLotatt4 != null && strLotatt4.trim().length() > 0){
                strBud.append(" and sto.lotatt4='").append(strLotatt4).append("'");
            }
            //批次属性5
            if(strLotatt5 != null && strLotatt5.trim().length() > 0){
                strBud.append(" and sto.lotatt5='").append(strLotatt5).append("'");
            }
            //批次属性6
            if(strLotatt6 != null && strLotatt6.trim().length() > 0){
                strBud.append(" and sto.lotatt6='").append(strLotatt6).append("'");
            }
            //批次属性7
            if(strLotatt7 != null && strLotatt7.trim().length() > 0){
                strBud.append(" and sto.lotatt7='").append(strLotatt7).append("'");
            }
            //批次属性8
            if(strLotatt8 != null && strLotatt8.trim().length() > 0){
                strBud.append(" and sto.lotatt8='").append(strLotatt8).append("'");
            }
            //批次属性9
            if(strLotatt9 != null && strLotatt9.trim().length() > 0){
                strBud.append(" and sto.lotatt9='").append(strLotatt9).append("'");
            }
            //批次属性10
            if(strLotatt10 != null && strLotatt10.trim().length() > 0){
                strBud.append(" and sto.lotatt10='").append(strLotatt10).append("'");
            }
            //批次属性11
            if(strLotatt11 != null && strLotatt11.trim().length() > 0){
                strBud.append(" and sto.lotatt11='").append(strLotatt11).append("'");
            }
            //批次属性12
            if(strLotatt12 != null && strLotatt12.trim().length() > 0){
                strBud.append(" and sto.lotatt12='").append(strLotatt12).append("'");
            }
            //巷道ID
            if(alleyId != null && alleyId.trim().length() > 0){
                strBud.append(" and sto.cargoAlleyId='").append(alleyId).append("'");
            }
            //托盘条码
            if(strTrayCode != null && strTrayCode.trim().length() > 0){
                strBud.append(" and sto.traycode='").append(strTrayCode).append("'");
            }//
            //条件
            strBud.append(" and (sto.pnum-sto.assignnum-sto.holdnum)>0");
            //分组
           // strBud.append(strGrpBud.toString());
            //排序
            strBud.append(" order by sto.cargoSpaceId ");
            
            lsStorage = assginDao.querySQL(strBud.toString());
            
        }catch(Exception er){
            throw new Exception("出库分配查询库存出错：" + er.getMessage());
        }
        return lsStorage;
    }
    
    /**
     * 描述: 出库分配查询库存
     * @author fanll 2014-7-25
     * @since lxyy
     */
    public List<?> getAssginQueryStorage(String warehouseId, String whAreaId, String alleyId, String strProductId, String traycode,String status) throws Exception {
        StringBuilder strBud = new StringBuilder();
        List lsStorage = null;
        try{

            //表名*********************
            strBud.append(" from InventoryStorage sto,BaseCargospace space where 1=1 ");
            
            //查询条件*where条件****************************************
           
            //仓库ID
            if(warehouseId != null && warehouseId.trim().length() > 0){
                strBud.append(" and sto.warehouseid='").append(warehouseId).append("'");
            }
            //库区ID
            if(whAreaId != null && whAreaId.trim().length() > 0){ 
                strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
            }
            //巷道ID
            if(alleyId != null && alleyId.trim().length() > 0){
                strBud.append(" and sto.cargoAlleyId='").append(alleyId).append("'");
            }
            //品名ID
            if(strProductId != null && strProductId.trim().length() > 0){
                strBud.append(" and sto.productid='").append(strProductId).append("'");
            }
            //托盘条码
            if(traycode != null && traycode.trim().length() > 0){
                strBud.append(" and sto.traycode='").append(traycode).append("'");
            }
            //物品状态
            if(status != null && status.trim().length() > 0){
                strBud.append(" and sto.productstatus in (").append(status).append(")");
            }
            //条件
            strBud.append(" and space.outlock!='Y'"); //出库锁住的不能进行分配
            
            //判断是否暂存库区（暂存库区可以非满货位出库）
            if(whAreaId.equals("001003")){             //   暂存库区编号  001003 （手动设置）
                strBud.append(" and (sto.pnum-sto.assignnum)>0 and sto.cargoSpaceId = space.cargoSpaceId and sto.status in('0','1') "); //货位为满货位状态的库存
            }else{
            	strBud.append(" and (sto.pnum-sto.assignnum)>0 and sto.cargoSpaceId = space.cargoSpaceId and space.csstatus='2' and sto.status in('0','1') "); //货位为满货位状态的库存
            }
            //排序
            strBud.append(" order by sto.indate ");
            
            lsStorage = assginDao.querySQL(strBud.toString());
            
        }catch(Exception er){
            throw new Exception("出库分配查询库存出错：" + er.getMessage());
        }
        return lsStorage;
    }
	/**
	 * 功能: 手动分配
	 * @author hug 2012-11-15 
	 * @param invoiceid         出库单ID
	 * @param invoicedetailid   出库单详细ID
	 * @param strStoMsg         货位、库存列表
	 * @param strUserCode
	 * @return
	 * @throws Exception
	 */
	public String assginStorageNew(String invoiceid, String invoicedetailid, String strStoMsg, String strUserCode,String fpnumresult) throws Exception{

		String strMsg = "Y";
		synchronized (assgin_lock) {
			//出库单详细
			OutboundInvoiceDetail outDetail = outBoundDAO.getOutBoundDetailById(invoicedetailid);
			OutboundInvoiceHeader invoice = outBoundDAO.getOutBoundById(invoiceid);
			if(outDetail != null && !outDetail.getLinestatus().equals("7") && invoice!=null && !invoice.getOutstatus().equals("7")){//不为发货状态
				//格式化 客户人工分配的库存信息
				String strStorage = parseAssginStr(strStoMsg);
				Logger.info(strUserCode+"格式化后人工分配的信息:" + strStorage);
				if(strStorage != null && strStorage.length()>1){
					String[] spaces = strStorage.split("\\|");
					String flag = "";    //货位1:托盘条码a[库存ID1，数量，毛重，净重，体积;库存ID2，数量，毛重，净重，体积]托盘条码b[库存ID1，数量，毛重，净重，体积;库存ID2，数量，毛重，净重，体积]
					String spaceid = ""; //货位ID
					String strTrays = "";//托盘条码a[库存ID1，数量，毛重，净重，体积;库存ID2，数量，毛重，净重，体积]托盘条码b[库存ID1，数量，毛重，净重，体积;库存ID2，数量，毛重，净重，体积]
					String flag2 = "";   //托盘条码a[库存ID1，数量，毛重，净重，体积;库存ID2，数量，毛重，净重，体积
					String traycode = "";//托盘条码
					String strStorages = "";//库存ID1，数量，毛重，净重，体积;库存ID2，数量，毛重，净重，体积
					String flag3 = "";   //库存ID1，数量，毛重，净重，体积
					String strStorageId;//库存ID
					double num;         //数量
					double weight;      //毛重
					double netweight;   //净重
					double volume;      //体积
					for(int i=0; i<spaces.length; i++){
						flag = spaces[i];
						String[] space = flag.split(":");
						spaceid = space[0];//货位ID
						strTrays = space[1]; //托盘条码信息
						//验证**********************************************************************
						OutAllotVerifyRequestBean requestBean = new OutAllotVerifyRequestBean();
						requestBean.setCargoSpaceId(spaceid);
						String[] tray = strTrays.split("\\]"); 
						for(int j=0; j< tray.length; j++){
							flag2 = tray[j];         
							traycode = flag2.substring(0, flag2.indexOf("["));//托盘条码
							strStorages = flag2.substring(flag2.indexOf("[")+1, flag2.length());//库存ID列表

							OutAllotVerifyRequestBean.TrayStorageBean traystorageBean = requestBean.getNewTrayStorageBean();
							traystorageBean.setTrayCode(traycode);//托盘条码

							String[] storage = strStorages.split(";");
							for(int k = 0; k < storage.length; k++){
								flag3 = storage[k]; //库存ID1，数量，毛重，净重，体积
								String[] invetnroys = flag3.split(",");
								strStorageId = invetnroys[0];//库存ID
								num = Double.parseDouble((invetnroys[1]==null?"":invetnroys[1]));         //数量
								weight = Double.parseDouble((invetnroys[2]==null?"":invetnroys[2]));      //毛重
								netweight = Double.parseDouble((invetnroys[3]==null?"":invetnroys[3]));   //净重
								volume = Double.parseDouble((invetnroys[4]==null?"":invetnroys[4]));      //体积

								OutAllotVerifyRequestBean.TrayStorageBean.StorageBean storageBean = traystorageBean.getNewStorageBean();
								storageBean.setInventoryid(strStorageId);//库存ID
								storageBean.setNum(num);        //数量
								storageBean.setWeight(weight);  //毛重
								storageBean.setNetweight(netweight);//净重
								storageBean.setVolume(volume);  //体积     
								traystorageBean.addStorageBean(storageBean);
							} 
							requestBean.addTrayStorageBean(traystorageBean);
						}

						//验证并返回信息*************************************************************************
						//获得货位对象                        
						BaseCargospace baseSpace = spaceDao.getCargoSpaceById(spaceid);//货位             
						//根据库区ID判断是否需要生成调度任务
						boolean bTask = assginDao.isCreateTask(baseSpace.getWhAreaId());

						List<InoutJob> lsJob = new ArrayList<InoutJob>();//增加作业
						List<InoutJobdetail> lsJobDetail = new ArrayList<InoutJobdetail>();//增加的作业详细 
						List<String> lsStorageSQL = new ArrayList<String>();//修改库存的SQL
						List<String> lsInvoiceSQL = new ArrayList<String>();//修改出库单详细的SQL
						for(int m=0; m < requestBean.getTrayStorageBeanSize(); m++){
							OutAllotVerifyRequestBean.TrayStorageBean traystorageBean = requestBean.getListTrayStorageBean().get(m);
							String strTrayCode = traystorageBean.getTrayCode();//托盘条码

							//增加出库作业
							InoutJob job = new InoutJob();
							job.setJobtype("2");        //业类型1-入库2-出库3-移库
							if(bTask){ //生成调度任务
								job.setOnLineType("1");     //联机模式 1.联机 2.脱机
							}else{ 
								job.setOnLineType("2");     //联机模式 1.联机 2.脱机
							}

							job.setIsaccount("Y");      //是否记账 Y.是 N.否
							job.setStatus("1");         //作业状态 1.未执行 2.待执行 3.正执行 4.完成 5.作废 6.异常作业 8.暂停作业


							job.setPriority(10);        //优先级
							job.setCreatetime(StrTools.getCurrDateTime(2));//生成时间
							job.setJobpos("2");             //作业方向 1.入库端 2.出库端
							//job.setShiftid(strShiftId);   //作业班次
							job.setTraycode(strTrayCode);   //托盘条码
							job.setOpManId(strUserCode);    //操作人
							job.setInOutType("2");          //入出类型 1上架入库 2.出库
							job.setInvoicetype("2");        //1:先有作业后生成单据;2:先有单据后生成作业
							//获得作业ID   收货作业SJZY 的前缀 SJ

							BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(assginDao.getEntityDAO());
							BaseSeq  seqEn = seqDao.getSeqByType("CKZY");
							String strID = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), assginDao.getEntityDAO());

							String ss ="";
							job.setJobid(strID); //设置作业ID
							job.setWarehouseid(baseSpace.getWarehouseid());    //仓库ID
							job.setScargoSpaceId(baseSpace.getCargoSpaceId()); //源货位ID
							job.setScargoAlleyId(baseSpace.getCargoAlleyId()); //源巷道ID
							job.setScargoWhareaId(baseSpace.getWhAreaId());    //源库区ID
							job.setBoundstockdetailid(invoicedetailid);        //出库单详细ID      
							job.setBoundstockid(invoiceid); //出库单ID
							if(bTask){ //生成调度任务
								job.setJobcategory("1"); //自动化立体库作业
							}else{ 
								job.setJobcategory("2"); //暂存区作业
							}

							//根据货位ID和托盘条码获得该货位上的所有库存列表
							List<InventoryStorage> lsStorage = assginDao.getInventoryStorageBySpaceId(spaceid, strTrayCode);
							InventoryStorage storage = null;
							for(int ii=0; ii<lsStorage.size(); ii++){
								storage = lsStorage.get(ii);
								OutAllotVerifyRequestBean.TrayStorageBean.StorageBean storageBean = findStorageBean(storage.getInventoryid(), traystorageBean.getListStorageBean());

								if(storageBean == null){
									continue;
								}
								//增加出库作业详细  拣货任务
								InoutJobdetail jobDetail = new InoutJobdetail();
								//作业详细ID
								String strJobDetailId = SeqTool.getDetailId(strID, String.valueOf(i));
								jobDetail.setJobdetailid(strJobDetailId);   //作业详细ID
								jobDetail.setJobid(strID);                  //作业ID
								jobDetail.setLinestatus("0");               //状态0:新建2完成
								jobDetail.setInventoryid(storage.getInventoryid()); //库存ID
								jobDetail.setProductid(storage.getProductid());     //品名
								jobDetail.setPackid(storage.getPackid());   //包装ID
								jobDetail.setPunit(storage.getPunit());     //单位
								jobDetail.setLotid(storage.getLotid());     //批次类型ID
								jobDetail.setLotinfo(storage.getLotinfo()); //批号
								jobDetail.setLotinfo(storage.getProductdate());//生产日期
								// jobDetail.setJobnum(storage.getPnum() - storage.getAssignnum() - storage.getHoldnum());           //数量
								jobDetail.setJobnum(storage.getPnum());           //数量
								jobDetail.setNetweight(storage.getPnetweight());  //净重
								jobDetail.setWeight(storage.getPweight());        //毛量
								jobDetail.setVolume(storage.getPvolume());        //体积   
								/*if(storageBean != null){ //需要分配的数量
                                        jobDetail.setAssignnum(storageBean.getNum());             //分配数量
                                        jobDetail.setAssignnetweight(storageBean.getNetweight()); //分配净重
                                        jobDetail.setAssignweight(storageBean.getWeight());       //分配毛量
                                        jobDetail.setAssignvolume(storageBean.getVolume());       //分配体积
                                    }else{   //不需要分配的
                                        jobDetail.setAssignnum(0);          //分配数量
                                        jobDetail.setAssignnetweight(0);    //分配净重
                                        jobDetail.setAssignweight(0);       //分配毛量
                                        jobDetail.setAssignvolume(0);       //分配体积
                                    }*/
								jobDetail.setAssignnum(storageBean.getNum());             //分配数量
								jobDetail.setAssignnetweight(storageBean.getNetweight()); //分配净重
								jobDetail.setAssignweight(storageBean.getWeight());       //分配毛量
								jobDetail.setAssignvolume(storageBean.getVolume());       //分配体积

								jobDetail.setOwnerId(storage.getOwnerId());     //货主
								jobDetail.setIsinvoice("Y");                    //是否已生成单据
								jobDetail.setInjobdetailid(storage.getInjobetailid());//源作业详细（生成出库作业时的添加该库存的入库作业）
								//增加出库作业详细
								lsJobDetail.add(jobDetail);

								//修改库存的状态及库存的分配数量
								StringBuilder storageBudSQL= new StringBuilder();
								storageBudSQL.append("update InventoryStorage set ");
								/*if(storageBean != null){ //需要分配的数量
                                        storageBudSQL.append(" assignnum=assignnum+"+storageBean.getNum())
                                        .append(" , assignweight=assignweight+"+storageBean.getWeight())
                                        .append(" ,assignnetweight=assignnetweight+" + storageBean.getNetweight())
                                        .append(" , assignvolume=assignvolume+"+storageBean.getVolume());
                                    }*/
								storageBudSQL.append(" assignnum=assignnum+"+storageBean.getNum())
								.append(" , assignweight=assignweight+"+storageBean.getWeight())
								.append(" ,assignnetweight=assignnetweight+" + storageBean.getNetweight())
								.append(" , assignvolume=assignvolume+"+storageBean.getVolume());
								storageBudSQL.append("  where inventoryid='").append(storage.getInventoryid()).append("'");

								//增加修改库存的SQL
								lsStorageSQL.add(storageBudSQL.toString());
								//修改出库单详细的分配数量
								StringBuilder invoiceBudSQL= new StringBuilder();
								/*if(storageBean != null){ //需要分配的数量
                                    	invoiceBudSQL.append("update OutboundInvoiceDetail set linestatus='2', assignnum=assignnum+").append(storageBean.getNum())
                                        .append(" , assignweight=assignweight+").append(storageBean.getWeight())
                                        .append(" , assignnetweight=assignnetweight+").append(storageBean.getNetweight())
                                        .append(" , assignvolume=assignvolume+").append(storageBean.getVolume())
                                        .append(" where outstockdetailid='").append(invoicedetailid).append("'");
                                    }*/
								invoiceBudSQL.append("update OutboundInvoiceDetail set linestatus='2', assignnum=assignnum+").append(storageBean.getNum())
								.append(" , assignweight=assignweight+").append(storageBean.getWeight())
								.append(" , assignnetweight=assignnetweight+").append(storageBean.getNetweight())
								.append(" , assignvolume=assignvolume+").append(storageBean.getVolume())
								.append(" where outstockdetailid='").append(invoicedetailid).append("'");
								//增加修改出库单详细的SQL
								lsInvoiceSQL.add(invoiceBudSQL.toString());
							} 
							//增加作业
							lsJob.add(job);
						}
						//生成出库作业，是否需要生成出库调度作业
						//修改出库单详细的分配数量  
						//修改货位状态
						String strSpaceSQL = null;
						//立体库需要修改货位状态为出库分配状态
						if(bTask){ //生成调度任务
							strSpaceSQL = "update BaseCargospace set csstatus='4' where cargoSpaceId='" + spaceid + "'"; //自动化立体库作业
						}else{ 
							strSpaceSQL = "update BaseCargospace set csstatus='2' where cargoSpaceId='" + spaceid + "'";  //暂存区作业  暂存区一直为满货位
						}

						//生成出库作业，是否需要生成出库调度作业,修改出库单详细的分配数量************************************************************
						assginDao.addAssginJob(lsJob, null, lsJobDetail, lsStorageSQL, lsInvoiceSQL, strSpaceSQL,invoice); 
					}    
				}else{
					strMsg = "没有选中的货位信息，无法分配！"; 
				}

			}else{
				strMsg = "出库单["+invoiceid+"]的出库单详细["+invoicedetailid+"]不存或出库单详细已发货确认了!";
			}
		} 
		return strMsg;
	}	
	
	public String assginStorage(String invoiceid, String invoicedetailid, String strStoMsg, String floor, String tsjh, String strUserCode) throws Exception {
		String strMsg = "Y";
		// synchronized (assgin_lock) {
		// 出库单详细
		OutboundInvoiceDetail outDetail = outBoundDAO.getOutBoundDetailById(invoicedetailid);
		OutboundInvoiceHeader invoice = outBoundDAO.getOutBoundById(invoiceid);
		IBaseInoutControlBus ControlBus = new BaseInoutControlBusImpl(outBoundDAO.getEntityDAO());
		String inoutcontr = "";
		
		if (outDetail != null && !outDetail.getLinestatus().equals("7") && invoice != null && !invoice.getOutstatus().equals("7")) {// 不为发货状态
			BaseInoutControl InoutControl = ControlBus.getInoutControl(); //出入库控制
			if(InoutControl != null){
				inoutcontr = InoutControl.getCon_type();
			}
			boolean successflag = true;
			invoice.setOutstatus("2"); // 设置出库单为分配状态
			// 格式化 客户人工分配的库存信息
			String strStorage = parseAssginStr(strStoMsg);
			Logger.info(strUserCode + "格式化后人工分配的信息:" + strStorage);
			if (strStorage != null && strStorage.length() > 1) {
				String[] spaces = strStorage.split("\\|");
				String flag = ""; // 货位1:托盘条码a[库存ID1，数量，毛重，净重，体积;库存ID2，数量，毛重，净重，体积]托盘条码b[库存ID1，数量，毛重，净重，体积;库存ID2，数量，毛重，净重，体积]
				String spaceid = ""; // 货位ID
				String strTrays = "";// 托盘条码a[库存ID1，数量，毛重，净重，体积;库存ID2，数量，毛重，净重，体积]托盘条码b[库存ID1，数量，毛重，净重，体积;库存ID2，数量，毛重，净重，体积]
				String flag2 = ""; // 托盘条码a[库存ID1，数量，毛重，净重，体积;库存ID2，数量，毛重，净重，体积
				String traycode = "";// 托盘条码
				String strStorages = "";// 库存ID1，数量，毛重，净重，体积;库存ID2，数量，毛重，净重，体积
				String flag3 = ""; // 库存ID1，数量，毛重，净重，体积
				String strStorageId;// 库存ID
				double num; // 数量
				double fpnum; // 分配数量
				for (int i = 0; i < spaces.length; i++) {
					flag = spaces[i];
					String[] space = flag.split(":");
					spaceid = space[0];// 货位ID
					strTrays = space[1]; // 托盘条码信息
					// 验证**********************************************************************
					OutAllotVerifyRequestBean requestBean = new OutAllotVerifyRequestBean();
					requestBean.setCargoSpaceId(spaceid);
					String[] tray = strTrays.split("\\]");
					for (int j = 0; j < tray.length; j++) {
						flag2 = tray[j];
						traycode = flag2.substring(0, flag2.indexOf("["));// 托盘条码
						strStorages = flag2.substring(flag2.indexOf("[") + 1, flag2.length());// 库存ID列表

						OutAllotVerifyRequestBean.TrayStorageBean traystorageBean = requestBean.getNewTrayStorageBean();
						traystorageBean.setTrayCode(traycode);// 托盘条码

						String[] storage = strStorages.split(";");
						for (int k = 0; k < storage.length; k++) {
							flag3 = storage[k]; // 库存ID1，数量，分配数量
							String[] invetnroys = flag3.split(",");
							strStorageId = invetnroys[0];// 库存ID
							num = Double.parseDouble((invetnroys[1] == null ? "" : invetnroys[1])); // 数量
							fpnum = Double.parseDouble((invetnroys[2] == null ? "" : invetnroys[2])); // 分配数量

							OutAllotVerifyRequestBean.TrayStorageBean.StorageBean storageBean = traystorageBean.getNewStorageBean();
							storageBean.setInventoryid(strStorageId);// 库存ID
							storageBean.setNum(fpnum); // 分配数量
							traystorageBean.addStorageBean(storageBean);
						}
						requestBean.addTrayStorageBean(traystorageBean);
					}

					// 验证并返回信息*************************************************************************
					// 获得货位对象
					BaseCargospace baseSpace = spaceDao.getCargoSpaceById(spaceid);// 货位
					// 根据库区ID判断是否需要生成调度任务
					boolean bTask = assginDao.isCreateTask(baseSpace.getWhAreaId());

					List<InoutJob> lsJob = new ArrayList<InoutJob>();// 增加作业
					List<InoutJobdetail> lsJobDetail = new ArrayList<InoutJobdetail>();// 增加的作业详细
					List<String> lsStorageSQL = new ArrayList<String>();// 修改库存的SQL
					List<String> lsInvoiceSQL = new ArrayList<String>();// 修改出库单详细的SQL
					List<ScheduleTask> lsTask = null;
					if (bTask) { // 立体库的
						lsTask = new ArrayList<ScheduleTask>();
					}
					for (int m = 0; m < requestBean.getTrayStorageBeanSize(); m++) {
						OutAllotVerifyRequestBean.TrayStorageBean traystorageBean = requestBean.getListTrayStorageBean().get(m);
						String strTrayCode = traystorageBean.getTrayCode();// 托盘条码
						// 增加出库作业
						InoutJob job = new InoutJob();
						job.setJobtype(invoice.getOuttype()); // 作业类型
						if (bTask) { // 生成调度任务
							job.setOnLineType("1"); // 联机模式 1.联机 2.脱机
						} else {
							job.setOnLineType("2"); // 联机模式 1.联机 2.脱机
						}
						job.setIsaccount("Y"); // 是否记账 Y.是 N.否
						job.setStatus("1"); // 作业状态 1.未执行 2.待执行 3.正执行 4.完成 5.作废    6.异常作业 8.暂停作业
						job.setPriority(10); // 优先级
						job.setCreatetime(StrTools.getCurrDateTime(2));// 生成时间
						job.setJobpos("2"); // 作业方向 1.入库端 2.出库端
						job.setTraycode(strTrayCode); // 托盘条码
						job.setOpManId(strUserCode); // 操作人
						job.setInOutType("2"); // 入出类型 1上架入库 2.出库
						job.setInvoicetype("2"); // 1:先有作业后生成单据;2:先有单据后生成作业
						// 获得作业ID 收货作业SJZY 的前缀 SJ

						BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(assginDao.getEntityDAO());
						BaseSeq seqEn = seqDao.getSeqByType("CKZY");
						String strID = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(), seqEn.getIcodelength(), assginDao.getEntityDAO());

						String ss = "";
						job.setJobid(strID); // 设置作业ID
						job.setWarehouseid(baseSpace.getWarehouseid()); // 仓库ID
						job.setScargoSpaceId(baseSpace.getCargoSpaceId()); // 源货位ID
						job.setScargoAlleyId(baseSpace.getCargoAlleyId()); // 源巷道ID
						job.setScargoWhareaId(baseSpace.getWhAreaId()); // 源库区ID
						job.setBoundstockdetailid(invoicedetailid); // 出库单详细ID
						job.setBoundstockid(invoiceid); // 出库单ID

						if (bTask) { // 生成调度任务
							
							job.setJobcategory("1"); // 自动化立体库作业
							if(inoutcontr!=null && inoutcontr.equals("1")){ //可入库
								strMsg = "出入库已经卡控，现在只能入库！";
								successflag  = false;
								break;
							}
						} else {
							job.setJobcategory("2"); // 暂存区作业
						}

						// 根据货位ID和托盘条码获得该货位上的所有库存列表
						List<InventoryStorage> lsStorage = assginDao.getInventoryStorageBySpaceId(spaceid, strTrayCode);
						InventoryStorage storage = null;
						for (int ii = 0; ii < lsStorage.size(); ii++) {
							storage = lsStorage.get(ii);
							OutAllotVerifyRequestBean.TrayStorageBean.StorageBean storageBean = findStorageBean(storage.getInventoryid(), traystorageBean.getListStorageBean());
							if (storageBean == null) {
								continue;
							}
							// 增加出库作业详细 拣货任务
							InoutJobdetail jobDetail = new InoutJobdetail();
							// 作业详细ID
							String strJobDetailId = SeqTool.getDetailId(strID, String.valueOf(i));
							job.setBoundrequeststockid(storage.getRequestid()); // 添加申请单号

							jobDetail.setJobdetailid(strJobDetailId); // 作业详细ID
							jobDetail.setJobid(strID); // 作业ID
							jobDetail.setLinestatus("0"); // 状态0:新建2完成
							jobDetail.setInventoryid(storage.getInventoryid()); // 库存ID
							jobDetail.setProductid(storage.getProductid()); // 品名
							jobDetail.setPackid(storage.getPackid()); // 包装ID
							jobDetail.setPunit(storage.getPunit()); // 单位
							jobDetail.setLotid(storage.getLotid()); // 批次类型ID
							jobDetail.setLotinfo(storage.getLotinfo()); // 批号
							jobDetail.setPrintdate(storage.getProductdate());// 生产日期
							jobDetail.setProductStatus(storage.getProductstatus()); // 物品状态
							jobDetail.setProductcode(storage.getProductcode()); // 产品条码
							jobDetail.setLotinfo2(storage.getLotinfo2());

							if (bTask) { // 立体库
								jobDetail.setJobnum(storage.getPnum()); // 数量
							} else { // 暂存区
								jobDetail.setJobnum(storage.getPnum() - storage.getAssignnum() - storage.getHoldnum()); // 数量
							}

							jobDetail.setNetweight(storage.getPnetweight()); // 净重
							jobDetail.setWeight(storage.getPweight()); // 毛量
							jobDetail.setVolume(storage.getPvolume()); // 体积
							jobDetail.setAssignnum(storageBean.getNum()); // 分配数量
							jobDetail.setAssignnetweight(storageBean.getNetweight()); // 分配净重
							jobDetail.setAssignweight(storageBean.getWeight()); // 分配毛量
							jobDetail.setAssignvolume(storageBean.getVolume()); // 分配体积

							ScheduleTask task = null;

							if (bTask) { // 需要生成调度任务 整个货位需要分配出库的
								task = new ScheduleTask();

								String strTaskId = SeqTool.getID("RW", m_dao); // 作业ID
								task.setTaskid(strTaskId);// taskid; 调度任务编号
								job.setTaskid(strTaskId); // 调度任务ID
								task.setJobid(job.getJobid());
								task.setTasktype("2"); // 调度类型 1-入库 2-出库 3-移库
								task.setSplatoon(baseSpace.getCsplatoon()); // 源货位排
								task.setScolumn(baseSpace.getCscolumn()); // 源货位列
								task.setSfloor(baseSpace.getCsfloor()); // 源货位层
								task.setCargoSpaceId(baseSpace.getCargoSpaceId());// 货位ID
								task.setCargoAlleyId(baseSpace.getCargoAlleyId());// 巷道ID
								task.setWarehouseid(baseSpace.getWarehouseid()); // 仓库ID
								task.setWhAreaId(baseSpace.getWhAreaId()); // 库区ID
								task.setCargoSpaceId(baseSpace.getCargoSpaceId());
								task.setStatus("2"); // 任务状态
								task.setPriority(10); // 优先级
								task.setCreatetime(StrTools.getCurrDateTime(2)); // 时间
																					// 时间格式：yyyy-MM-dd
																					// hh:mm:ss
								task.setTraycode(strTrayCode); // 托盘条码
								task.setTaskpos("2"); // 任务方向 1.入库端 2.出库端
								task.setRoute("1"); // 执行路线 1-直入/直出 2-回流
								task.setFloor(floor);
								task.setPhase(1);
								task.setJobtype(job.getJobtype());
								task.setTSJH(tsjh);
								
								// 增加调度
								lsTask.add(task);
							}

							jobDetail.setOwnerId(storage.getOwnerId()); // 货主
							jobDetail.setIsinvoice("Y"); // 是否已生成单据
							jobDetail.setInjobdetailid(storage.getInjobetailid());// 源作业详细（生成出库作业时的添加该库存的入库作业）
							// 增加出库作业详细
							lsJobDetail.add(jobDetail);
							// 修改暂存库存的分配数量
							StringBuilder storageBudSQL = new StringBuilder();
							if (bTask) { // 立体库
								storageBudSQL.append("update InventoryStorage inventoryS set inventoryS.status='1' where inventoryS.inventoryid='" + storage.getInventoryid() + "'");
								// 增加修改库存的SQL
								lsStorageSQL.add(storageBudSQL.toString());
							} else { // 暂存区 需要修改分配数量
								storageBudSQL.append("update InventoryStorage inventoryS set ");
								storageBudSQL.append(" inventoryS.assignnum=inventoryS.assignnum+" + storageBean.getNum()).append(" , inventoryS.status='1' ")
										.append(" , inventoryS.assignweight=inventoryS.assignweight+" + storageBean.getWeight())
										.append(" ,inventoryS.assignnetweight=inventoryS.assignnetweight+" + storageBean.getNetweight())
										.append(" , inventoryS.assignvolume=inventoryS.assignvolume+" + storageBean.getVolume());
								storageBudSQL.append("  where inventoryS.inventoryid='").append(storage.getInventoryid()).append("'");

								// 增加修改库存的SQL
								lsStorageSQL.add(storageBudSQL.toString());
							}

						}
						// 增加作业
						lsJob.add(job);
					}
					if(successflag){
						// 生成出库作业，是否需要生成出库调度作业
						// 修改出库单详细的分配数量
						// 修改货位状态
						String strSpaceSQL = null;
						// 立体库需要修改货位状态为出库分配状态
						if (bTask) { // 生成调度任务
							strSpaceSQL = "update BaseCargospace set csstatus='4' where cargoSpaceId='" + spaceid + "'"; // 自动化立体库作业
						} else {
							strSpaceSQL = "update BaseCargospace set csstatus='2' where cargoSpaceId='" + spaceid + "'"; // 暂存区作业
																															// 暂存区一直为满货位
						}

						// 生成出库作业，是否需要生成出库调度作业,修改出库单详细的分配数量************************************************************
						assginDao.addAssginJob(lsJob, lsTask, lsJobDetail, lsStorageSQL, lsInvoiceSQL, strSpaceSQL, invoice);
					}else{
						break;
					}
					
				}
			} else {
				strMsg = "没有选中的货位信息，无法分配！";
			}

		} else {
			strMsg = "出库单[" + invoiceid + "]的出库单详细[" + invoicedetailid + "]不存或出库单详细已发货确认了!";
		}
		return strMsg;
	}
    /**
     * 功能: 查找库存
     * @author hug 2012-9-29 
     * @param strInventoryId    库存ID
     * @param lsStroageBean     库存ID列表
     * @return
     */
    public OutAllotVerifyRequestBean.TrayStorageBean.StorageBean findStorageBean(String strInventoryId, List<OutAllotVerifyRequestBean.TrayStorageBean.StorageBean> lsStroageBean){        
        OutAllotVerifyRequestBean.TrayStorageBean.StorageBean storageBean = null;
        if(lsStroageBean != null){
            for(int i=0; i<lsStroageBean.size(); i++){
                storageBean = lsStroageBean.get(i);
                if(strInventoryId.equals(storageBean.getInventoryid())){//有这个库存ID
                    break;
                }else{
                    storageBean = null;
                }
            }    
        }  
        return storageBean;
    }
    /** 
     * 功能: 解析人工分配货位的信息
     * @author hug 2012-9-25 
     * @param strMsg
     * @return
     */
    public String parseAssginStr(String strMsg)
    {
        //源*******************************
        //货位1:托盘条码a[库存ID1，数量，毛重，净重，体积;库存ID2，数量，毛重，净重，体积]托盘条码b[库存ID1，数量，毛重，净重，体积;库存ID2，数量，毛重，净重，体积]| 
        //货位2:托盘条码c[库存ID1，数量，毛重，净重，体积;库存ID2，数量，毛重，净重，体积] |
        //货位3:托盘条码d[库存ID1，数量，毛重，净重，体积;库存ID2，数量，毛重，净重，体积] |
        
        //测试*****************************
        //strMsg =  "001:Y-000008[库存1,数量1,毛重1,净重1,体积1;库存2,数量2,毛重2,净重2,体积2]|" + 
        //          "002:Y-000089[库存ID2,数量,毛重,净重,体积;库存ID3,数量,毛重,净重,体积]|" +
        //          "001:Y-000008[库存3,数量3,毛重3,净重3,体积3;库存2,数量,毛重,净重,体积]|" +
        //          "001:Y-000001[库存1,数量5,毛重5,净重5,体积5]|" +
    	//          "001:Y-000001[库存2,数量5,毛重5,净重5,体积5]|" +
    	//001:Y-000001[库存1,数量5,毛重5,净重5,体积5;库存2,数量5,毛重5,净重5,体积5]
        //          "001:Y-000008[库存8,数量8,毛重8,净重8,体积3;库存2,数量,毛重,净重,体积;库存9,数量9,毛重9,净重9,体积9;库存7,数量7,毛重7,净重7,体积7]";
        //最后顺序：货位:托盘[库存1;库存1
        String distinctStr = "";
        String flag = "";//逗号分隔的内容（货位|库存ID）
        String flag2 = "";
        String spaceid = "";//货位ID
        String[] newstr = strMsg.split("\\|");
        int strLen = newstr.length;//长度
        for(int i=0; i< strLen; i++)
        {
            //货位2:托盘条码c[库存ID1，数量，毛重，净重，体积;库存ID2，数量，毛重，净重，体积]
            flag = newstr[i];
            if(flag.length() > 0){
                String[] space = flag.split(":");
                spaceid = space[0];//货位ID
                for(int j=i+1; j < strLen; j++){
                    flag2 = newstr[j];
                    String[] space2 = flag2.split(":");
                    String spaceid2 = space2[0];             
                    if (spaceid2.equals(spaceid) && flag.length() != 0)
                    {
                        //相同货位的合在一起
                        newstr[i] = newstr[i] + space2[1];
                        newstr[j] = ""; 
                    }
                }       
                //合并货位中相同的托盘
                String[] newspace = newstr[i].split(":");
                newstr[i] = newspace[0] +":"+ parseTrayStr(newspace[1]);    
                if (newstr[i].length() != 0)
                {
                    if (distinctStr.length() != 0)
                    {
                        distinctStr = distinctStr + "|" + newstr[i];
                    }
                    else
                    {
                        distinctStr = newstr[i];
                    }
                }       
            }       
        }   
        return distinctStr;
    }
    /** 
     * 功能:相同托盘的合在一起
     * @author hug 2012-9-25 
     * @param strTrays  托盘字符串
     * @return
     */
    public String parseTrayStr(String strTrays){
        //格式  strMsg = "Y-000008[库存1,数量1,毛重1,净重1,体积1;库存2,数量2,毛重2,净重2,体积2]Y-000008[库存3,数量3,毛重3,净重3,体积3;库存2,数量,毛重,净重,体积]";
        //返回  strMsg = "Y-000008[库存1,数量1,毛重1,净重1,体积1;库存2,数量2,毛重2,净重2,体积2;库存3,数量3,毛重3,净重3,体积3;库存2,数量,毛重,净重,体积]";
        String strValue = "";
        String flag = "";
        String flag2 = "";
        String traycode = "";  //托盘条码
        String[] tray = strTrays.split("\\]");
        int strLen = tray.length;//长度
        for(int i=0; i< strLen; i++)
        {
            flag = tray[i];
            if(flag.length() >1){
                traycode = flag.substring(0, flag.indexOf("["));//托盘条码
                for(int j=i+1; j < strLen; j++){
                    flag2 = tray[j];
                    System.out.println(flag2);
                    if(flag2.length() >0){
                        String traycode2 = flag2.substring(0, flag2.indexOf("["));//托盘条码
                        String strStorage2 = flag2.substring(flag2.indexOf("[")+1, flag2.length());//库存ID列表
                        if (traycode2.equals(traycode) )
                        {
                            //相同托盘的合在一起 
                            tray[i] = tray[i] + ";" + strStorage2;  
                            tray[j] = "";   
                        }   
                    }
                }       
                tray[i] = traycode + "[" + distinctStorageId(tray[i].substring(tray[i].indexOf("[")+1, tray[i].length())); 
                if (tray[i].length() != 0)
                {
                    if (strValue.length() != 0)
                    {
                        strValue = strValue + "]" + tray[i];
                    }else
                    {
                        strValue = tray[i];
                    }
                }
            }   
        }
        return strValue;
    }
    /**
     * 功能: 取掉字符串相同的库存ID
     * @author hug 2012-9-25 
     * @param strStoMsg 字符串
     * @return
     */
    public String distinctStorageId(String strStoMsg){
        //格式  strStoMsg=库存1,数量1,毛重1,净重1,体积1;库存2,数量2,毛重2,净重2,体积2;库存3,数量3,毛重3,净重3,体积3;库存2,数量,毛重,净重,体积";
        //结果  return=   库存1,数量1,毛重1,净重1,体积1;库存2,数量2,毛重2,净重2,体积2;库存3,数量3,毛重3,净重3,体积3    
        String strValue = "";
        String flag = "";
        String[] storages = strStoMsg.split(";");
        int strLen = storages.length;//长度
        for(int i=0; i< strLen; i++){
            flag = storages[i];
            if(flag.length() > 0){
                //库存ID
                String spaceid = flag.split(",")[0];
                for(int j=i+1; j < strLen; j++){
                    String spaceid2 = (storages[j].split(","))[0];
                    if (spaceid2.equals(spaceid))
                    {
                        //相同库存ID就去掉
                        storages[j] = ""; 
                    }
                }
                if (storages[i].length() != 0)
                {
                    if (strValue.length() != 0)
                    {
                        strValue = strValue + ";" + storages[i];
                    }else
                    {
                        strValue = storages[i];
                    }
                }   
            }
        }
        return strValue;
    }
    
    public String autoAssginStorage(String invoiceid, String invoicedetailid, String ownerid, String customerid, String warehouseId, String whAreaId, String strUserCode) throws Exception {
        String strMsg = "Y";
        synchronized (assgin_lock) {
            //出库单详细
            OutboundInvoiceDetail outDetail = outBoundDAO.getOutBoundDetailById(invoicedetailid);
            OutboundInvoiceHeader invoice = outBoundDAO.getOutBoundById(invoiceid);
            if(outDetail != null && !outDetail.getLinestatus().equals("7") && invoice!=null && (!invoice.getOutstatus().equals("7")||!invoice.getOutstatus().equals("8"))){//不为发货状态
                if(outDetail.getInvoicenum() - outDetail.getAssignnum() > 0){
                    OutAllotRequestBean outRequest = new OutAllotRequestBean();
                    outRequest.setOutstockid(invoiceid);            //出库单据号
                    outRequest.setOutstockdetailid(invoicedetailid);//出库单详细ID
                    outRequest.setWarehouseid(warehouseId); //仓库ID
                    outRequest.setWhAreaId(whAreaId);       //库区ID
                    outRequest.setOwnerid(ownerid);         //货主
                    outRequest.setCustomerid(customerid);      //客户ID(收货人)
                    
                    OutAllotRequestBean.ProductBean productBean = outRequest.newProductBean();
                    productBean.setProductid(outDetail.getProductid()); //产品
                    productBean.setPackid(outDetail.getPackid());       //包装
                    productBean.setEunit(outDetail.getPkgunit());       //单位
                    productBean.setNum(outDetail.getInvoicenum() - outDetail.getAssignnum());           //需要分配的数量
                    productBean.setNetweight(outDetail.getNetweight() - outDetail.getAssignnetweight());//需要分配的净重
                    productBean.setWeight(outDetail.getWeight() - outDetail.getAssignweight());         //需要分配的毛重
                    productBean.setVolume(outDetail.getVolume() - outDetail.getAssignvolume());         //需要分配的净重
                    productBean.setLotid(outDetail.getLotid());      //批次类型ID
                    productBean.setLotatt1(outDetail.getLotatt1());  //批次属性1
                    productBean.setLotatt2(outDetail.getLotatt2());  //批次属性2
                    productBean.setLotatt3(outDetail.getLotatt3());  //批次属性3
                    productBean.setLotatt4(outDetail.getLotatt4());  //批次属性4
                    productBean.setLotatt5(outDetail.getLotatt5());  //批次属性5
                    productBean.setLotatt6(outDetail.getLotatt6());  //批次属性6
                    productBean.setLotatt7(outDetail.getLotatt7());  //批次属性7
                    productBean.setLotatt8(outDetail.getLotatt8());  //批次属性8
                    productBean.setLotatt9(outDetail.getLotatt9());  //批次属性9
                    productBean.setLotatt10(outDetail.getLotatt10());//批次属性10
                    productBean.setLotatt11(outDetail.getLotatt11());//批次属性11
                    productBean.setLotatt12(outDetail.getLotatt12());//批次属性12
                    outRequest.setProductBean(productBean);
                    
                    //根据库区ID判断是否需要生成调度任务
                    // boolean bTask = assginDao.isCreateTask(whAreaId);
                    List<InoutJob> lsJob = new ArrayList<InoutJob>();//增加作业
                    /*List<ScheduleTask> lsTask = null;
                    if(bTask){ //立体库的
                        lsTask = new ArrayList<ScheduleTask>();
                    }*/
                    List<InoutJobdetail> lsJobDetail = new ArrayList<InoutJobdetail>();//增加的作业详细 
                    List<String> lsStorageSQL = new ArrayList<String>();//修改库存的SQL
                    List<String> lsInvoiceSQL = new ArrayList<String>();//修改出库单详细的SQL
                    
                    //返回出库分配结果*******************************************************************************
                    List<OutAllotResponseBean> lsAllot = null;
                    if(lsAllot != null){
                        try{
                            OutAllotResponseBean outResponseBean = null; //出库分配响应对象
                            for(int i = 0; i < lsAllot.size(); i++){
                                outResponseBean =  lsAllot.get(i);   
                                //获得货位对象
                                BaseCargospace baseSpace = spaceDao.getCargoSpaceById(outResponseBean.getCargoSpaceId());//货位 
                                          
                                //托盘信息
                                List<OutAllotResponseBean.TrayStorageBean> lsTray = outResponseBean.getListTrayStorageBean();
                                if(lsTray != null){
                                    OutAllotResponseBean.TrayStorageBean trayBean = null;
                                    String strTrayCode = null;  //托盘条码
                                    for(int j = 0; j < lsTray.size(); j++){
                                        trayBean = lsTray.get(j);
                                        strTrayCode = trayBean.getTrayCode();
                                        
                                        //增加出库作业
                                        InoutJob job = new InoutJob();
                                        job.setJobtype("2");        //业类型1-入库2-出库3-移库
                                        job.setOnLineType("1");     //联机模式 1.联机 2.脱机
                                        job.setIsaccount("Y");      //是否记账 Y.是 N.否
                                        job.setStatus("1");         //作业状态 1.未执行 2.待执行 3.正执行 4.完成 5.作废 6.异常作业 8.暂停作业
                                        job.setPriority(10);        //优先级
                                        job.setCreatetime(StrTools.getCurrDateTime(2));//生成时间
                                        job.setJobpos("2");             //作业方向 1.入库端 2.出库端
                                        //job.setShiftid(strShiftId);   //作业班次
                                        job.setTraycode(strTrayCode);   //托盘条码
                                        job.setOpManId(strUserCode);    //操作人
                                        job.setInOutType("2");          //入出类型 1上架入库 2.出库
                                        job.setInvoicetype("2");        //1:先有作业后生成单据;2:先有单据后生成作业
                                        //获得作业ID   收货作业SJZY 的前缀 SJ
                                        BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(assginDao.getEntityDAO());
        				                BaseSeq  seqEn = seqDao.getSeqByType("CKZY");
        				                String strID = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), assginDao.getEntityDAO());

                                        job.setJobid(strID); //设置作业ID
                                        job.setWarehouseid(outResponseBean.getWarehouseid());    //仓库ID
                                        job.setScargoSpaceId(outResponseBean.getCargoSpaceId()); //源货位ID
                                        job.setScargoAlleyId(outResponseBean.getCargoAlleyId()); //源巷道ID
                                        job.setScargoWhareaId(outResponseBean.getWhAreaId());    //源库区ID
                                        job.setBoundstockid(invoiceid);//出库单ID
                                        job.setBoundstockdetailid(invoicedetailid);//出库单详细ID
                                        //job.setOldspacestatus(outResponseBean.getOldstatus());   //分配前货位状态
                                                   
                                            //返回库存信息  该货位上所有的库存ID
                                            List<OutAllotResponseBean.TrayStorageBean.StorageBean> lsStorage = trayBean.getListStorageBean();
                                            if(lsStorage != null){ 
                                                OutAllotResponseBean.TrayStorageBean.StorageBean storageBean = null;
                                                String strInventoryId = null;   //库存ID
                                                for(int k = 0; k < lsStorage.size(); k++){ 
                                                    storageBean = lsStorage.get(k);
                                                    strInventoryId = storageBean.getInventoryid(); //库存ID
                                                    
                                                    InventoryStorage storage = assginDao.getInventoryStorageById(strInventoryId);
                                                    //增加出库作业详细  拣货任务
                                                    InoutJobdetail jobDetail = new InoutJobdetail();
                                                    //作业详细ID
                                                    String strJobDetailId = SeqTool.getDetailId(strID, String.valueOf(i));
                                                    jobDetail.setJobdetailid(strJobDetailId);   //作业详细ID
                                                    jobDetail.setJobid(strID);                  //作业ID
                                                    jobDetail.setLinestatus("0");               //状态0:新建2完成
                                                    jobDetail.setInventoryid(storage.getInventoryid()); //库存ID
                                                    jobDetail.setProductid(storage.getProductid());     //品名
                                                    jobDetail.setPackid(storage.getPackid());   //包装ID
                                                    jobDetail.setPunit(storage.getPunit());     //单位
                                                    jobDetail.setLotid(storage.getLotid());     //批次类型ID
                                                    
                                                    jobDetail.setJobnum(storage.getPnum() - storage.getHoldnum());           //数量
                                                    jobDetail.setNetweight(storage.getPnetweight() - storage.getHoldnetweight());  //净重
                                                    jobDetail.setWeight(storage.getPweight() - storage.getHoldweight());        //毛量
                                                    jobDetail.setVolume(storage.getPvolume() - storage.getHoldvolume());        //体积
                                                    
                                                    jobDetail.setAssignnum(storageBean.getNum());             //分配数量
                                                    jobDetail.setAssignnetweight(storageBean.getNetweight()); //分配净重
                                                    jobDetail.setAssignweight(storageBean.getWeight());       //分配毛量
                                                    jobDetail.setAssignvolume(storageBean.getVolume());       //分配体积
                                                    
                                                    
                                                    jobDetail.setOwnerId(storage.getOwnerId());     //货主
                                                    jobDetail.setIsinvoice("Y");                    //是否已生成单据
                                                    jobDetail.setInjobdetailid(storage.getInjobetailid());//源作业详细（生成出库作业时的添加该库存的入库作业）
                                                    //增加出库作业详细
                                                    lsJobDetail.add(jobDetail); 
                                                    //修改库存的状态及库存的分配数量
                                                    StringBuilder storageBudSQL= new StringBuilder();
                                                    storageBudSQL.append("update InventoryStorage set status='1' ");
                                                    if(storageBean != null){ //需要分配的数量
                                                        storageBudSQL.append(", assignnum=assignnum+"+storageBean.getNum())
                                                        .append(" , assignweight=assignweight+"+storageBean.getWeight())
                                                        .append(" ,assignnetweight=assignnetweight+" + storageBean.getNetweight())
                                                        .append(" , assignvolume=assignvolume+"+storageBean.getVolume());
                                                    }
                                                    storageBudSQL.append("  where inventoryid='").append(storage.getInventoryid()).append("'");
                                                    //增加修改库存的SQL
                                                    lsStorageSQL.add(storageBudSQL.toString());
                                                    
                                                    //修改出库单详细的分配数量
                                                    StringBuilder invoiceBudSQL= new StringBuilder();
                                                    invoiceBudSQL.append("update OutboundInvoiceDetail set linestatus='2', assignnum=assignnum+").append(storageBean.getNum())
                                                    .append(" , assignweight=assignweight+").append(storageBean.getWeight())
                                                    .append(" , assignnetweight=assignnetweight+").append(storageBean.getNetweight())
                                                    .append(" , assignvolume=assignvolume+").append(storageBean.getVolume())
                                                    .append(" where outstockdetailid='").append(invoicedetailid).append("'");
                                                    //增加修改出库单详细的SQL
                                                    lsInvoiceSQL.add(invoiceBudSQL.toString());
                                                } 
                                            } 
                                        //增加作业
                                        lsJob.add(job);   
                                    }
                                }
                                //他改库存状态和货位状态；还需要判断该货位的调度是否已生成。
                                //剩下的库存不管，库存状态也不管。
                                //下架时,多条作业同时完成，库存要全部下架，剩余产品放到暂存区
                                //修改出库单详细的分配数量  
                                //修改货位状态
                                String strSpaceSQL = null;
                                //if(bTask){//立体库需要修改货位状态为出库分配状态
                                    strSpaceSQL = "update BaseCargospace set csstatus='4' where cargoSpaceId='" + outResponseBean.getCargoSpaceId() + "'";
                                //}   
                               //设置单据为出库分配状态
                               invoice.setOutstatus("2");
                                //生成出库作业，是否需要生成出库调度作业,修改出库单详细的分配数量************************************************************
                               //assginDao.addAssginJob(lsJob, lsTask, lsJobDetail, lsStorageSQL, lsInvoiceSQL, strSpaceSQL); 
                               assginDao.addAssginJob(lsJob, null, lsJobDetail, lsStorageSQL, lsInvoiceSQL, strSpaceSQL,invoice); 
                            }    
                        }catch(Exception er){
                            //生成出库作业失败，要还原货位分配前的状态,需要做哪些处理
                            throw new Exception("生成出库作业失败：" + er.getMessage());   
                        }     
                    }else{
                        strMsg = "出库单["+invoiceid+"]的出库单详细["+invoicedetailid+"]分配失败!";
                    }    
                }else{
                    strMsg = "出库单["+invoiceid+"]的出库单详细["+invoicedetailid+"][开单:"+outDetail.getInvoicenum()+"][分配:"+outDetail.getAssignnum()+"]已分配完成无需再分配!";
                }
            }else{
                strMsg = "出库单["+invoiceid+"]的出库单详细["+invoicedetailid+"]不存或出库单详细已发货确认了!";
            }
        }
        return strMsg;
    }

}

