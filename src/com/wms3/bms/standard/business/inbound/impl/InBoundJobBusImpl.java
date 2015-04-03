package com.wms3.bms.standard.business.inbound.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.allot.impl.InAllotRequestBean;
import com.wms3.bms.standard.business.base.ILotBus;
import com.wms3.bms.standard.business.base.impl.LotBusImpl;
import com.wms3.bms.standard.business.inbound.IInBoundJobBus;
import com.wms3.bms.standard.dao.base.IBaseCargoSpaceDao;
import com.wms3.bms.standard.dao.base.impl.BaseCargoSpaceDaoImpl;
import com.wms3.bms.standard.dao.base.impl.BaseSeqDaoImpl;
import com.wms3.bms.standard.dao.inbound.IInboundJobDao;
import com.wms3.bms.standard.dao.inbound.impl.InboundJobDaoImpl;
import com.wms3.bms.standard.dao.inventory.IInventoryDao;
import com.wms3.bms.standard.dao.inventory.impl.InventoryDaoImpl;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BaseLotDetail;
import com.wms3.bms.standard.entity.base.BaseSeq;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * 
 * 描述: 入库作业管理业务类
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public class InBoundJobBusImpl implements IInBoundJobBus {
	
    /** 收货单DAO类  */
    protected IInboundJobDao inboundJobDao;
    
    
    public InBoundJobBusImpl(EntityDAO dao) {
    	inboundJobDao = new InboundJobDaoImpl(dao);
    	
	}


	/**
	 * 功能:根据条件查询入库作业
	 * @param warehouseid	仓库
	 * @param whAreaId		库区
	 * @param alleyId		巷道
	 * @param indate		作业日期
	 * @param jobid			作业号
	 * @param invoicetype	作业来源
	 * @param status		作业状态
	 * @param shiftid		班次
	 * @param productid		品名
	 * @param ownerid		货主
	 * @param tray_code		托盘条码
	 * @param isback		是否回流
	 * @param sortflg		排序标志
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getInboundJobs(String warehouseid, String whAreaId, String alleyId, String indate, String jobid, String invoicetype, 
			String status, String shiftid, String productid, String ownerid, String traycode, String isback, String sortflg, 
			String strUrl, int maxLine) throws Exception {
		
		PagingTool pt = null;
		
		try {
			StringBuilder strBud = new StringBuilder();
			strBud.append(" from InoutJob as job where job.inOutType='1' ");
			
			if(warehouseid != null && warehouseid.trim().length() > 0){		//仓库
				strBud.append(" and job.warehouseid='").append(warehouseid).append("'");
			}
			
			if(whAreaId != null && whAreaId.trim().length() > 0){		//库区
				strBud.append(" and job.tcargoWhareaId='").append(whAreaId).append("'");
			}
			
			if(alleyId != null && alleyId.trim().length() > 0){		//巷道
				strBud.append(" and job.tcargoAlleyId='").append(alleyId).append("'");
			}
			
			if(indate != null && indate.trim().length() > 0){		//作业日期
				strBud.append(" and substring(job.createtime,1,10)='").append(indate).append("'");
			}
			
			if(jobid != null && jobid.trim().length() > 0){			//作业号
				strBud.append(" and job.jobid='").append(jobid).append("'");
			}
			
			if(invoicetype != null && invoicetype.trim().length() > 0){	//作业来源
				strBud.append(" and job.invoicetype='").append(invoicetype).append("'");
			}
			
			if(status != null && status.trim().length() > 0){		//作业状态
				strBud.append(" and job.status='").append(status).append("'");
			}

			if(shiftid != null && shiftid.trim().length() > 0){		//班次
				strBud.append(" and job.shiftid='").append(shiftid).append("'");
			}
			
			if(traycode != null && traycode.trim().length() > 0){		//托盘条码
				strBud.append(" and job.traycode='").append(traycode).append("'");
			}
			
			if(isback != null && isback.equals("Y")){			//是否回流( 回流 2.出库端  1上架入库  N.否)
				strBud.append(" and job.jobpos='2' and job.isaccount='N'");
				
			}else if(isback != null && isback.equals("N")){		//是否回流( 入库  1.入库端  1上架入库 Y.是)
				strBud.append(" and job.jobpos!='2' and job.isaccount='Y'");
			}
			
			if(productid != null && productid.trim().length() > 0){		//品名
				strBud.append(" and job.jobid in " +
					"(select jobdetail.jobid from InoutJobdetail as jobdetail " +
					" where job.jobid=jobdetail.jobid and jobdetail.productid='").append(productid).append("')");
			}
			
			if(ownerid != null && ownerid.trim().length() > 0){		//货主
				strBud.append(" and job.jobid in " +
					"(select jobdetail.jobid from InoutJobdetail as jobdetail " +
					" where job.jobid=jobdetail.jobid and jobdetail.ownerId='").append(ownerid).append("')");
			}
			
			//排序方式
			String strHQL = strBud.toString() + " order by job.jobid";
			if(sortflg.equals("desc")){
				strHQL = strHQL + " desc";
			}
			
			String strCountHQL = "select count(*)" + strBud.toString();
			
			pt = CommonPagination.getPagingTool(inboundJobDao.getEntityDAO(), strCountHQL ,strHQL, strUrl, 1, maxLine);
			
		} catch (Exception er) {
			throw new Exception("根据条件查询查询入库作业列表出错:" + er.getMessage());
		}
				
		return pt;
	}

	/**
	 * 功能:根据作业id查询作业明细
	 * @param jobid			作业号
	 * @return 
	 * @throws Exception
	 */
	public List getJobDetails(String jobid) throws Exception {
		
		String strSql = "";
		List list = null;
		
		try {
			strSql = " from InoutJobdetail as jobdetail where jobdetail.jobid='" + jobid + "'";
			
			list = inboundJobDao.querySQL(strSql);
			
		} catch(Exception er) {
			er.printStackTrace();
			throw new Exception("入库管理->查询作业详细列表时候出错：" + er.getMessage());
		}
			
		return list;
	}

	/**
	 * 功能:生成脱机作业
	 * @param warehouseid	仓库
	 * @param whAreaId		库区
	 * @param alleyId		巷道
	 * @param cargospaceid	货位ID
	 * @param intype		入库类型
	 * @param tray_code		托盘条码
	 * @param indate		作业日期
	 * @param shiftid		班次
	 * @param jobdetails	作业明细（多条，用分隔符分隔开）
	 * @param strUserCode	用户id
	 * @return 
	 * @throws Exception
	 */
	public String addRKWHJob(String warehouseid, String whAreaId, String alleyId, String cargospaceid, String intype, String traycode, 
			String indate, String shiftid, String jobdetails, String strUserCode) throws Exception {
		
		String strBackMsg = "Y";
		
		try {
			synchronized (cargospaceid) { //产品锁定，防止多人操作同一条数据
				
				String strTime = StrTools.getCurrDateTime(2);	//入库时间(保证一托盘上每箱的入库时间一致)
				
				//入库分配请求对象
                InAllotRequestBean inRequest = new InAllotRequestBean();
                inRequest.setSpaceid(cargospaceid);	
                
				//增加作业信息
				InoutJob job = new InoutJob();
				job.setJobtype(intype);    	//作业类型 
				job.setOnLineType("2"); 	//联机模式 1.联机 2.脱机
				job.setIsaccount("Y");  	//是否记账 Y.是 N.否
				job.setStatus("4");     	//作业状态 1.未执行 2.待执行 3.正执行 4.完成 5.作废 6.异常作业 8.暂停作业
				job.setPriority(10);  		//优先级
				job.setCreatetime(indate);	//时间 时间格式：yyyy-MM-dd hh:mm:ss
			    job.setFinishtime(strTime); //完成时间 时间格式：yyyy-MM-dd hh:mm:ss
			    job.setJobpos("1");      	//作业方向 1.入库端 2.出库端
			    job.setInOutType("1");  	//入出类型 1上架入库 2.出库
			    job.setShiftid(shiftid);   	//作业班次
			    job.setWarehouseid(warehouseid);//仓库ID
			    job.setTraycode(traycode);  //托盘条码
			    job.setRoute("1");      	//执行路线1-直入/直出2-回流
			    job.setOpManId(strUserCode);//操作人
			    job.setInvoicetype("1");	//作业来源 1：直接采集生成的作业，2：按收货单来生成的作业，3：按入库单来生成的作业 4:按ERP其它单来生成的作业

			    job.setTcargoWhareaId(whAreaId); 		//目标库区ID
			    job.setTcargoSpaceId(cargospaceid);  	//目标货位ID
			    job.setTcargoAlleyId(alleyId);  		//目标巷道ID
			    
			    
			    BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(inboundJobDao.getEntityDAO());
                BaseSeq  seqEn = seqDao.getSeqByType("RKZY");//入库作业
                String jobid = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), inboundJobDao.getEntityDAO()); //作业ID  
                
				job.setJobid(jobid);		//作业编号

				
				//增加作业详细信息
				List lsJobDetail = new ArrayList();
				List lsStorage = new ArrayList();
				
				String productid = "";		//品名
				String customerid = "";		//货主
				String packid = "";			//包装
				String punit = "";			//单位
				double jobnum = 0;			//数量
				double volume = 0;			//体积
				double weight = 0;			//毛重
				double netweight = 0;		//净重
				String lotatt1 = "";		//批次属性1
				String lotatt2 = "";		//批次属性2
				String lotatt3 = "";		//批次属性3
				String lotatt4 = "";		//批次属性4
				String lotatt5 = "";		//批次属性5
				String lotatt6 = "";		//批次属性6
				String lotatt7 = "";		//批次属性7
				String lotatt8 = "";		//批次属性8
				String lotatt9 = "";		//批次属性9
				String lotatt10 = "";		//批次属性10
				String lotatt11 = "";		//批次属性11
				String lotatt12 = "";		//批次属性12
				String lotid = "";			//批次类型ID
				
				String[] aem = jobdetails.split(",");
				String[] bem;
				
				for(int i=0; i<aem.length; i++){
					
					bem = aem[i].split("\\|");
					
					productid = bem[0];		//品名
					customerid = bem[1];	//货主
					packid = bem[2];		//包装
					punit = bem[3];			//单位
					jobnum = Double.parseDouble(bem[4]==""?"0":bem[4]);	//数量
					volume = Double.parseDouble(bem[5]==""?"0":bem[5]);	//体积
					weight = Double.parseDouble(bem[6]==""?"0":bem[6]);	//毛重
					netweight = Double.parseDouble(bem[7]==""?"0":bem[7]);	//净重
					lotatt1 = bem[8];		//批次属性1
					lotatt2 = bem[9];		//批次属性2
					lotatt3 = bem[10];		//批次属性3
					lotatt4 = bem[11];		//批次属性4
					lotatt5 = bem[12];		//批次属性5
					lotatt6 = bem[13];		//批次属性6
					lotatt7 = bem[14];		//批次属性7
					lotatt8 = bem[15];		//批次属性8
					lotatt9 = bem[16];		//批次属性9
					lotatt10 = bem[17];		//批次属性10
					lotatt11 = bem[18];		//批次属性11
					lotatt12 = bem[19];		//批次属性12
					lotid = bem[20];		//批次类型ID
				
					InoutJobdetail jobdetail = new InoutJobdetail();
	                //作业详细ID
	                String strJobDetailId = SeqTool.getDetailId(jobid, "1");
	                jobdetail.setJobdetailid(strJobDetailId);   //作业详细ID
	                jobdetail.setJobid(jobid);                  //作业ID
	                jobdetail.setLinestatus("2");               //状态0:新建2完成
	                jobdetail.setProductid(productid);			//品名ID
	                jobdetail.setPackid(packid); 				//包装ID
	                jobdetail.setPunit(punit);   				//单位
	                jobdetail.setLotid(lotid);   				//批次类型ID
	                jobdetail.setJobnum(jobnum);           		//数量
	                jobdetail.setNetweight(netweight);  		//净重
	                jobdetail.setWeight(weight);        		//毛量
	                jobdetail.setVolume(volume);        		//体积
	                jobdetail.setIsinvoice("N");                //是否已生成单据
	              
	                jobdetail.setOwnerId(customerid);   //货主
	                lsJobDetail.add(jobdetail);
				
	                //入库分配请求对象
	                InAllotRequestBean.ProductBean productBean = inRequest.getNewProductBean();
	                productBean.setProductid(jobdetail.getProductid()); //产品
                    productBean.setPackid(jobdetail.getPackid());       //包装
                    productBean.setEunit(jobdetail.getPunit());         //单位
                    productBean.setPutnum(jobdetail.getJobnum());          //上架数量
                    productBean.setPutweight(jobdetail.getWeight());       //上架重量
                    productBean.setPutnetweight(jobdetail.getNetweight()); //上架净重
                    productBean.setPutvolume(jobdetail.getVolume());       //上架体积 
                    productBean.setLotid(jobdetail.getLotid());		//批次类型ID
                   
                    productBean.setOwnerid(jobdetail.getOwnerId());	//货主  
                    inRequest.addProductBean(productBean);    
                    
                    //增加库存信息
                    InventoryStorage inventoryStorage = new InventoryStorage();
                  
                    inventoryStorage.setCargoSpaceId(job.getTcargoSpaceId()); //货位ID
                    inventoryStorage.setCargoAlleyId(job.getTcargoAlleyId()); //巷道ID
                    //inventoryStorage.setCargoAreaId(space.getCargoAreaId());//货区ID
                    inventoryStorage.setWhAreaId(job.getTcargoWhareaId()); //库区
                    inventoryStorage.setWarehouseid(job.getWarehouseid()); //仓库ID
                    
                    inventoryStorage.setOwnerId(jobdetail.getOwnerId());    //货主ID
                    inventoryStorage.setProductid(jobdetail.getProductid());//物品ID
                    inventoryStorage.setPackid(jobdetail.getPackid());      //包装ID
                    inventoryStorage.setPunit(jobdetail.getPunit());        //计量单位
                    inventoryStorage.setIndate(strTime);				//入库时间
                    inventoryStorage.setLotid(jobdetail.getLotid());	//批次ID
                    
                    inventoryStorage.setIntype("2");                	//入库方式 1.联机 2.脱机 
                    inventoryStorage.setPnum(jobdetail.getJobnum());         //库存数量
                    inventoryStorage.setPvolume(jobdetail.getVolume());      //库存体积
                    inventoryStorage.setPweight(jobdetail.getWeight());      //库存重量
                    inventoryStorage.setPnetweight(jobdetail.getNetweight());//库存净重
                    
                    inventoryStorage.setInjobid(jobdetail.getJobid());              //作业ID
                    inventoryStorage.setInjobetailid(jobdetail.getJobdetailid());   //作业详细ID
                    
                    inventoryStorage.setTraycode(job.getTraycode());        //托盘条码
                   
                    inventoryStorage.setReserve3(jobdetail.getReserve3());  //预留1
                    inventoryStorage.setReserve4(jobdetail.getReserve4());  //预留1
                   
                    lsStorage.add(inventoryStorage);
                }     
				
				// //验证新货位********************************************************
				//TODO
                BaseCargospace cargospace = null;//为空则表示失败，为货位对象则表示成功
                if(cargospace != null){  //验证成功
                	
                	cargospace.setCsstatus("2");	// 货位状态 1：空货位；2：满货位；3：入库分配；4：出库分配；5：入库需盘点；6：出库需盘点；7：出库取货；8：已出货
                    
                    //保存到数据库,新增作业和作业详细信息，新增库存,修改货位状态
                    inboundJobDao.addRKWHJob(cargospace, job, lsJobDetail, lsStorage); 
                    Logger.info("用户【" + strUserCode + "】生成了脱机作业：" + jobid);
                    
                }else{//验证失败
                	strBackMsg = "指定货位["+ cargospaceid +"]验证失败，该货位无法上架所添加的作业！";
                }   
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			strBackMsg = "生成脱机作业时候发生错误！";
			return strBackMsg;
		}
		
		return strBackMsg;
	}

	/**
	 * 功能:根据作业id查询作业
	 * @param jobid			作业号
	 * @return 
	 * @throws Exception
	 */
	public InoutJob getJobByJobid(String jobid) throws Exception {
		
		return inboundJobDao.getJobByJobid(jobid);
	}


	/**
	 * 功能:修改作业（入库维护）
	 * @param jobid			作业ID
	 * @param jobtype		作业类型
	 * @param traycode		托盘条码
	 * @return 
	 * @throws Exception
	 */
	public String updateRKWHJob(String jobid, String jobtype, String traycode) throws Exception {
		
		String strBackMsg = "Y";
		
		try {
			
			InoutJob job = inboundJobDao.getJobByJobid(jobid);
			if(job != null){
				
				//判断作业来源 1：直接采集生成的作业，2：按收货单来生成的作业，3：按入库单来生成的作业 4:按ERP其它单来生成的作业 12:人工生成的作业
				if(job.getInvoicetype().startsWith("1") ){	
					
					//判断作业是否已生成单据了
					List ls = getJobDetails(jobid);
					if(ls != null && ls.size() > 0){
						
						InoutJobdetail jobdetail = (InoutJobdetail)ls.get(0);
						if(jobdetail.getIsinvoice().equals("Y")){
							
							strBackMsg = "作业:" + jobid + "已生成单据，无法修改作业！";
						}else{
							
							//判断作业状态 1.未执行 2.待执行 3.正执行 4.完成 5.作废 6.异常作业 8.暂停作业
							if(!job.getStatus().equals("4")){
								job.setTraycode(traycode);	//已完成的只能改作业类型,不能改详细(不涉及库存修改)
							}
							job.setJobtype(jobtype);
							inboundJobDao.updateJob(job);
						}
					}
				}else{
					
					strBackMsg = " 只能修改来源是【直接采集生成的作业】或者【人工生成的作业】的作业！";
				}
			}else{
				
				strBackMsg = "作业不存在！";
			}
		}catch (Exception e) {
		
			e.printStackTrace();
			strBackMsg = "修改作业时候发生错误！";
			return strBackMsg;
		}
		
		return strBackMsg;
	}

	/**
	 * 功能:根据作业明细号查询作业明细
	 * @param jobdetailid			作业明细号
	 * @return 
	 * @throws Exception
	 */
	public InoutJobdetail getJobDetailBydetailid(String jobdetailid) throws Exception {
		
		return inboundJobDao.getJobDetailBydetailid(jobdetailid);
	}

	/**
	 * 功能:修改作业明细（入库维护）
	 * @param jobdetailid	作业明细号
	 * @param key			作业明细内容
	 * @return 
	 * @throws Exception
	 */
	public String updateRKWHJobDetail(String jobdetailid, String key) throws Exception {
		
		String strBackMsg = "Y";
		
		try {
			InoutJobdetail jobdetail = getJobDetailBydetailid(jobdetailid);
			InoutJob job = getJobByJobid(jobdetail.getJobid());
			
			if(jobdetail != null){
				
				//判断作业来源 1：直接采集生成的作业，2：按收货单来生成的作业，3：按入库单来生成的作业 4:按ERP其它单来生成的作业 12:人工生成的作业
				if(job.getInvoicetype().startsWith("1") ){	
					
					//判断作业是否已生成单据了
					if(jobdetail.getIsinvoice().equals("Y")){
							
						strBackMsg = "该作业明细已生成单据，无法修改！";
					}else{
						
						//判断状态 0:新建 2:完成
						if(jobdetail.getLinestatus().equals("2")){
							
							strBackMsg = "该作业明细已经完成，无法修改！";
						}else{
						
							String[] bem = key.split("\\|");
							String productid = bem[0];		//品名
							String customerid = bem[1];		//货主
							String packid = bem[2];			//包装
							String punit = bem[3];			//单位
							double jobnum = Double.parseDouble(bem[4]==""?"0":bem[4]);		//数量
							double volume = Double.parseDouble(bem[5]==""?"0":bem[5]);		//体积
							double weight = Double.parseDouble(bem[6]==""?"0":bem[6]);		//毛重
							double netweight = Double.parseDouble(bem[7]==""?"0":bem[7]);	//净重
							String lotatt1 = bem[8];		//批次属性1
							String lotatt2 = bem[9];		//批次属性2
							String lotatt3 = bem[10];		//批次属性3
							String lotatt4 = bem[11];		//批次属性4
							String lotatt5 = bem[12];		//批次属性5
							String lotatt6 = bem[13];		//批次属性6
							String lotatt7 = bem[14];		//批次属性7
							String lotatt8 = bem[15];		//批次属性8
							String lotatt9 = bem[16];		//批次属性9
							String lotatt10 = bem[17];		//批次属性10
							String lotatt11 = bem[18];		//批次属性11
							String lotatt12 = bem[19];		//批次属性12
							String lotid = bem[20];			//批次类型ID
							
			                jobdetail.setProductid(productid);	//品名ID
			                jobdetail.setPackid(packid); 		//包装ID
			                jobdetail.setPunit(punit);   		//单位
			                jobdetail.setLotid(lotid);   		//批次类型ID
			                jobdetail.setJobnum(jobnum);        //数量
			                jobdetail.setNetweight(netweight);  //净重
			                jobdetail.setWeight(weight);        //毛量
			                jobdetail.setVolume(volume);        //体积
			                
			                jobdetail.setOwnerId(customerid);   //货主
							
							inboundJobDao.updateJobDetail(jobdetail);
						}
					}
				}else{
					
					strBackMsg = " 只能修改来源是【直接采集生成的作业】或者【人工生成的作业】的作业！";
				}
			}else{
				
				strBackMsg = "作业明细不存在！";
			}
		}catch (Exception e) {
		
			e.printStackTrace();
			strBackMsg = "修改作业明细时候发生错误！";
			return strBackMsg;
		}
		
		return strBackMsg;
	}

	/**
	 * 功能:作废作业（入库维护）
	 * @param jobid			作业ID
	 * @return 
	 * @throws Exception
	 */
	public String cancelRKWHJob(String jobid) throws Exception {
		
		String strBackMsg = "Y";
		
		try {
			InoutJob job = inboundJobDao.getJobByJobid(jobid);
			if(job != null){
				
				//获取作业的目标货位
				IBaseCargoSpaceDao baseCargoSpaceDAO = new BaseCargoSpaceDaoImpl(inboundJobDao.getEntityDAO());
				BaseCargospace cargospace = baseCargoSpaceDAO.getCargoSpaceById(job.getTcargoSpaceId());
				
				//判断作业来源 1：直接采集生成的作业，2：按收货单来生成的作业，3：按入库单来生成的作业 4:按ERP其它单来生成的作业 12:人工生成的作业
				if(job.getInvoicetype().startsWith("1") ){	
				
					if(job.getInvoicetype().equals("1")){	//作业来源 1：直接采集生成的作业
								
						//判断作业状态 1.未执行 2.待执行 3.正执行 4.完成 5.作废 6.异常作业 8.暂停作业
						if(job.getStatus().equals("1") || job.getStatus().equals("2")){
							
							job.setStatus("5");
							cargospace.setCsstatus("1");
							//保存到数据库,作废作业，删除库存,修改货位状态为空货位
							inboundJobDao.cancelJob(job, cargospace, null); 
							
						}else{
							strBackMsg = "只能作废状态是未执行或者待执行的作业！";
						}
					}
					
					if(job.getInvoicetype().equals("12")){	//作业来源 12:人工生成的作业
						
						IInventoryDao inventoryDAO = new InventoryDaoImpl(inboundJobDao.getEntityDAO());
						
						// 判断货位状态 1：空货位；2：满货位；3：入库分配；4：出库分配；5：入库需盘点；6：出库需盘点；7：出库取货；8：已出货
						if(cargospace.getCsstatus().equals("2")){
							
							List lsStorage = inventoryDAO.getInventoryByCsId(job.getTcargoSpaceId());	//库存列表
							
							job.setStatus("5");
							cargospace.setCsstatus("1");
							//保存到数据库,作废作业，删除库存,修改货位状态为空货位
							inboundJobDao.cancelJob(job, cargospace, lsStorage); 
							
						}else{
							strBackMsg = "作业:" + jobid + "对应的货位状态不是满货位，该货位可能已经被分配，无法作废作业！";
						}
					}
				}else{
					strBackMsg = "只能作废来源是【直接采集生成的作业】或者【人工生成的作业】的作业！";
				}
				
			}else{
				strBackMsg = "作业不存在！";
			}
			
		}catch (Exception e) {
		
			e.printStackTrace();
			strBackMsg = "作废作业（入库维护）时候发生错误！";
			return strBackMsg;
		}
		
		return strBackMsg;
	}

	/**
	 * 功能:根据条件查询入库作业明细
	 * @param warehouseid	仓库
	 * @param whAreaId		库区
	 * @param alleyId		巷道
	 * @param cargospaceid	货位
	 * @param onlinetype	入库模式
	 * @param indate_from	作业日期
	 * @param indate_to		作业日期
	 * @param shiftid		班次
	 * @param isback		是否回流
	 * @param invoicetype	作业来源
	 * @param productid		品名
	 * @param ownerid		货主
	 * @param tray_code		托盘条码
	 * @param lotid			批次id
     * @param lotatt1, lotatt2, lotatt3, lotatt4, lotatt5, lotatt6      批次属性
     * @param lotatt7, lotatt8, lotatt9, lotatt10, lotatt11, lotatt12   批次属性
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getInboundJobDetails(String warehouseid, String whAreaId, String alleyId, String cargospaceid,  
			String onlinetype, String indate_from, String indate_to, String shiftid, String isback, String invoicetype, 
			String productid, String ownerid, String traycode, String lotid, String lotatt1, String lotatt2, String lotatt3,  
			String lotatt4, String lotatt5, String lotatt6, String lotatt7, String lotatt8, String lotatt9, String lotatt10, 
			String lotatt11, String lotatt12, String strUrl, int maxLine) throws Exception {
		
		PagingTool pt = null;
		
		try {
			StringBuilder strBud = new StringBuilder();
			strBud.append(" from InoutJob as job, InoutJobdetail as jobdetail where job.jobid=jobdetail.jobid and job.inOutType='1' ");
			
			if(warehouseid != null && warehouseid.trim().length() > 0){		//仓库
				strBud.append(" and job.warehouseid='").append(warehouseid).append("'");
			}
			
			if(whAreaId != null && whAreaId.trim().length() > 0){		//库区
				strBud.append(" and job.tcargoWhareaId='").append(whAreaId).append("'");
			}
			
			if(alleyId != null && alleyId.trim().length() > 0){		//巷道
				strBud.append(" and job.tcargoAlleyId='").append(alleyId).append("'");
			}
			
	        if(cargospaceid != null && cargospaceid.trim().length() >0){	//货位
	            strBud.append(" and job.tcargoSpaceId='").append(cargospaceid).append("'");
	        }
	        
	        if(onlinetype != null && onlinetype.trim().length() > 0){			//入库模式
				strBud.append(" and job.onLineType='").append(onlinetype).append("'");
			}
			
			if(indate_from != null && indate_from.trim().length() > 0){		//作业日期
				strBud.append(" and job.createtime>='").append(indate_from).append("'");
			}
			if(indate_to != null && indate_to.trim().length() > 0){		//作业日期
				strBud.append(" and job.createtime<='").append(indate_to).append(" 99:99:99'");
			}
			
			if(shiftid != null && shiftid.trim().length() > 0){		//班次
				strBud.append(" and job.shiftid='").append(shiftid).append("'");
			}
			
			if(isback != null && isback.equals("Y")){			//是否回流( 回流 2.出库端  1上架入库  N.否)
				strBud.append(" and job.jobpos='2' and job.isaccount='N'");
				
			}else if(isback != null && isback.equals("N")){		//是否回流( 入库  1.入库端  1上架入库 Y.是)
				strBud.append(" and job.jobpos!='2' and job.isaccount='Y'");
			}
			
			if(invoicetype != null && invoicetype.trim().length() > 0){	//作业来源
				strBud.append(" and job.invoicetype='").append(invoicetype).append("'");
			}

			if(productid != null && productid.trim().length() > 0){		//品名
				strBud.append(" and jobdetail.productid='").append(productid).append("'");
			}
			
			if(ownerid != null && ownerid.trim().length() > 0){		//货主
				strBud.append(" and jobdetail.ownerId='").append(ownerid).append("'");
			}
			
			if(traycode != null && traycode.trim().length() > 0){		//托盘条码
				strBud.append(" and job.traycode='").append(traycode).append("'");
			}
			
			//根据批次ID获得批次
			ILotBus lotBus = new LotBusImpl(inboundJobDao.getEntityDAO());
            HashMap<String, BaseLotDetail> hsLot = lotBus.getHashMapByLotId(lotid);
            
			BaseLotDetail lotDetail = null;	//批次明细

	        //批次属性
	        if(lotatt1 != null && lotatt1.trim().length()>0){
	        	
	        	lotDetail = hsLot.get("lotatt1");
	            strBud.append(getSqlLotatt("1", lotatt1, lotDetail));   
	        }
	        
	        if(lotatt2 != null && lotatt2.trim().length()>0){
	        	
	        	lotDetail = hsLot.get("lotatt2");
	            strBud.append(getSqlLotatt("2", lotatt2, lotDetail));   
	        }
	        
	        if(lotatt3 != null && lotatt3.trim().length()>0){
	        	
	            lotDetail = hsLot.get("lotatt3");
	            strBud.append(getSqlLotatt("3", lotatt3, lotDetail)); 
	        }
	        
	        if(lotatt4 != null && lotatt4.trim().length()>0){
	        	
	            lotDetail = hsLot.get("lotatt4");
	            strBud.append(getSqlLotatt("4", lotatt4, lotDetail));
	        }
	        
	        if(lotatt5 != null && lotatt5.trim().length()>0){
	        	
	            lotDetail = hsLot.get("lotatt5");
	            strBud.append(getSqlLotatt("5", lotatt5, lotDetail));
	        }
	        
	        if(lotatt6 != null && lotatt6.trim().length()>0){
	        	
	            lotDetail = hsLot.get("lotatt6");
	            strBud.append(getSqlLotatt("6", lotatt6, lotDetail));
	        }
	        
	        if(lotatt7 != null && lotatt7.trim().length()>0){
	            
	            lotDetail = hsLot.get("lotatt7");
	            strBud.append(getSqlLotatt("7", lotatt7, lotDetail));
	        }
	        
	        if(lotatt8 != null && lotatt8.trim().length()>0){
	            
	            lotDetail = hsLot.get("lotatt8");
	            strBud.append(getSqlLotatt("8", lotatt8, lotDetail));
	        }
	        
	        if(lotatt9 != null && lotatt9.trim().length()>0){
	            
	            lotDetail = hsLot.get("lotatt9");
	            strBud.append(getSqlLotatt("9", lotatt9, lotDetail));
	        }
	        
	        if(lotatt10 != null && lotatt10.trim().length()>0){
	            
	            lotDetail = hsLot.get("lotatt10");
	            strBud.append(getSqlLotatt("10", lotatt10, lotDetail));
	        }
	        
	        if(lotatt11 != null && lotatt11.trim().length()>0){
	        	
	            lotDetail = hsLot.get("lotatt11");
	            strBud.append(getSqlLotatt("11", lotatt11, lotDetail));
	        }
	        
	        if(lotatt12 != null && lotatt12.trim().length()>0){
	        	
	            lotDetail = hsLot.get("lotatt12");
	            strBud.append(getSqlLotatt("12", lotatt12, lotDetail));
	        }
			
			String strHQL = strBud.toString() + " order by job.jobid";
			String strCountHQL = "select count(*)" + strBud.toString();
			
			pt = CommonPagination.getPagingTool(inboundJobDao.getEntityDAO(), strCountHQL ,strHQL, strUrl, 1, maxLine);
			
		} catch (Exception er) {
			throw new Exception("根据条件查询查询入库作业列表出错:" + er.getMessage());
		}
				
		return pt;
	}
/**
 * 入库流水作业查询
 * @param warehouseid仓库
 * @param whAreaId   库区
 * @param alleyId    巷道号
 * @param indate_from初始日期
 * @param indate_to  结束日期
 * @param invoicetype作业类型
 * @param productid  产品id
 * @param ownerid    货主
 * @param traycode   托盘条码
 * @param lotid      批次id
 * @param lotatt1    属性1
 * @param lotatt2    属性2
 * @param lotatt3    属性3
 * @param lotatt4    属性4
 * @param lotatt5    属性5
 * @param lotatt6    属性6
 * @param lotatt7    属性7
 * @param lotatt8    属性8
 * @param lotatt9    属性9
 * @param lotatt10   属性10
 * @param lotatt11   属性10
 * @param lotatt12   属性12
 * @param strUrl     转向地址
 * @param maxLine    最大显示行数
 * @param invoiceid  单据号
 * @param groupinfo  分组信息
 * @param customer_id客户
 * @param intype     单据类型
 * @return
 * @throws Exception
 */
	public PagingTool getInboundJobDetailsGroupByIn(String warehouseid, String whAreaId, String alleyId,String indate_from, String indate_to,
			String invoicetype, String productid, String ownerid, String traycode, String lotid, String strUrl, int maxLine,String boundstockid,String groupinfo,String customer_id,String intype) throws Exception {
		
		PagingTool pt = null;
		List list = null;
		try {
			StringBuilder strBud = new StringBuilder();
			String head = "select ";
			String head1 = "select count(*) ";
			
			if(groupinfo != null && groupinfo.trim().length()>0){ //获取字符字段
				
				String aem[] = groupinfo.split(",");
				String bem[] = aem[0].split("\\|");// 属性id
				
				for(int x=0;x<bem.length;x++){//分组
					if(bem[x] != null && bem[x].trim().length()>0){
						if(bem[x].equals("productcode")){
							head += "jobdetail.productcode,";
						}else if(bem[x].equals("productid")){
							head += "jobdetail.m_strProductName,";
						}else if(bem[x].equals("lotinfo")){
							head += "jobdetail.lotinfo,";
						}
						else if(bem[x].equals("printdate")){
							head += "jobdetail.printdate,";
						}
						else if(bem[x].equals("boundstockid")){
							head += "job.boundstockid,";
						}
						else if(bem[x].equals("jobtype")){
							head += "job.jobtype,";
						}
						else if(bem[x].equals("createtime")){
							head += "substring(job.createtime,1,10),";
						}
						
					}	
				}
				head += "sum(jobdetail.jobnum),sum(jobdetail.volume),sum(jobdetail.weight),sum(jobdetail.netweight) ";
			}else{ // 无分组时，显示货主名，产品名，日期，容积数量等。
				head = " select jobdetail.productcode, jobdetail.m_strProductName,jobdetail.lotinfo,jobdetail.printdate,job.boundstockid,job.jobtype,substring(job.createtime,1,10),sum(jobdetail.jobnum),sum(jobdetail.volume),sum(jobdetail.weight),sum(jobdetail.netweight) ";
			}
			
			strBud.append(" from InoutJob as job, InoutJobdetail as jobdetail where job.jobid=jobdetail.jobid and job.inOutType='1' and job.status='4' ");
			
			if(warehouseid != null && warehouseid.trim().length() > 0){		//仓库
				strBud.append(" and job.warehouseid='").append(warehouseid).append("'");
			}
			
			if(whAreaId != null && whAreaId.trim().length() > 0){		//库区
				strBud.append(" and job.tcargoWhareaId='").append(whAreaId).append("'");
			}
			
			if(alleyId != null && alleyId.trim().length() > 0){		//巷道
				strBud.append(" and job.tcargoAlleyId='").append(alleyId).append("'");
			}
			
			if(indate_from != null && indate_from.trim().length() > 0){		//作业日期
				strBud.append(" and job.createtime>='").append(indate_from).append("'");
			}
			if(indate_to != null && indate_to.trim().length() > 0){		//作业日期
				strBud.append(" and job.createtime<='").append(indate_to).append(" 99:99:99'");
			}
			
			if(invoicetype != null && invoicetype.trim().length() > 0){	//作业类型
				strBud.append(" and job.invoicetype='").append(invoicetype).append("'");
			}

			if(productid != null && productid.trim().length() > 0){		//品名
				strBud.append(" and jobdetail.productid='").append(productid).append("'");
			}
			
			if(ownerid != null && ownerid.trim().length() > 0){		//货主
				strBud.append(" and jobdetail.ownerId='").append(ownerid).append("'");
			}
			
			if(traycode != null && traycode.trim().length() > 0){		//托盘条码
				strBud.append(" and job.traycode='").append(traycode).append("'");
			}
			
			if(customer_id != null && customer_id.trim().length() > 0){	//客户
				strBud.append(" and jobdetail.customerid='").append(customer_id).append("'");
			}
			
			if(boundstockid != null && boundstockid.trim().length() > 0){	//单号
				strBud.append(" and job.boundstockid='").append(boundstockid).append("'");
			}
			
			if(intype != null && intype.trim().length() > 0){	//作业类型
				strBud.append(" and job.jobtype='").append(intype).append("'");
			}
			
	      
			// --customerid|ownerId,1|2,客户|货主--
	        String strHQL = "";
	        if(groupinfo != null && groupinfo.trim().length()>0){ //分组排序
	        	String aem[] = groupinfo.split(",");
				String bem[] = aem[0].split("\\|");// 属性id
				strBud.append(" group by ");
				for(int x=0;x<bem.length;x++){//分组
					if(bem[x] != null && bem[x].trim().length()>0){
						if((x+1)==bem.length){
							if(bem[x].equals("productcode")||bem[x].equals("productid")){
								strBud.append("jobdetail.productid");
							}else if(bem[x].equals("createtime")){
								strBud.append("substring(job.createtime,1,10) ");
							}else if(bem[x].equals("boundstockid")||bem[x].equals("jobtype")){
								strBud.append("job."+bem[x]+" ");
							}else{
								strBud.append("jobdetail."+bem[x]+" ");
							}
						}else{
							if(bem[x].equals("productcode")||bem[x].equals("productid")){
								strBud.append("jobdetail.productid,");
							}else  if(bem[x].equals("boundstockid")||bem[x].equals("jobtype")){
								strBud.append("job."+bem[x]+",");
							}else{
								strBud.append("jobdetail."+bem[x]+",");
							}
						}	
					}
					
				}
				strBud.append(" order by ");
				for(int x=0;x<bem.length;x++){//排序
					if(bem[x] != null && bem[x].trim().length()>0){
						if((x+1)==bem.length){
							if(bem[x].equals("createtime")){
								strBud.append("substring(job.createtime,1,10) ");
							}else if(bem[x].equals("boundstockid")||bem[x].equals("jobtype")){
								strBud.append("job."+bem[x]+" ");
							}else{
								strBud.append("jobdetail."+bem[x]+" ");
							}
						}else{
							if(bem[x].equals("boundstockid")||bem[x].equals("jobtype")){
								strBud.append("job."+bem[x]+",");
							}else{
								strBud.append("jobdetail."+bem[x]+",");
							}
						}
					}	
				}
				strHQL = strBud.toString();
	        }else{
	        	strBud.append(" group by jobdetail.productid,jobdetail.lotinfo,jobdetail.printdate,job.boundstockid,job.jobtype,substring(job.createtime,1,10)");
	        	strBud.append(" order by jobdetail.productid,jobdetail.lotinfo,jobdetail.printdate,job.boundstockid,job.jobtype,substring(job.createtime,1,10)");
	        	strHQL = strBud.toString();
	        }
			
	        head  += strHQL;
	        head1 += strHQL;
//	        head1 += "select count(*) from ("+head1+") as ss";
	       list = inboundJobDao.getEntityDAO().searchEntities(head);
			
			pt = CommonPagination.getPagingTool(inboundJobDao.getEntityDAO(),list.size(), head , strUrl, 1, maxLine);
			
			
		} catch (Exception er) {
			throw new Exception("根据条件查询查询入库流水列表出错:" + er.getMessage());
		}
				
		return pt;
	}
	/**
	 * 出库流水作业查询
	 * @param warehouseid仓库
	 * @param whAreaId   库区
	 * @param alleyId    巷道号
	 * @param indate_from初始日期
	 * @param indate_to  结束日期
	 * @param invoicetype作业类型
	 * @param productid  产品id
	 * @param ownerid    货主
	 * @param traycode   托盘条码
	 * @param lotid      批次id
	 * @param lotatt1    属性1
	 * @param lotatt2    属性2
	 * @param lotatt3    属性3
	 * @param lotatt4    属性4
	 * @param lotatt5    属性5
	 * @param lotatt6    属性6
	 * @param lotatt7    属性7
	 * @param lotatt8    属性8
	 * @param lotatt9    属性9
	 * @param lotatt10   属性10
	 * @param lotatt11   属性10
	 * @param lotatt12   属性12
	 * @param strUrl     转向地址
	 * @param maxLine    最大显示行数
	 * @param invoiceid  单据号
	 * @param groupinfo  分组信息
	 * @param customer_id客户
	 * @param intype     单据类型
	 * @return
	 * @throws Exception
	 */
		public PagingTool getInboundJobDetailsGroupByOut(String warehouseid, String whAreaId, String alleyId,String indate_from, String indate_to,
				String invoicetype, String productid, String ownerid, String traycode, String lotid, String strUrl, int maxLine,String boundstockid,String groupinfo,String customer_id,String outtype) throws Exception {
			
			PagingTool pt = null;
			List list = null;
			
			try {
				StringBuilder strBud = new StringBuilder();
				String head = "select ";
				String head1 = "select count(*) ";
				
				if(groupinfo != null && groupinfo.trim().length()>0){ //获取字符字段
					
					String aem[] = groupinfo.split(",");
					String bem[] = aem[0].split("\\|");// 属性id
					
					for(int x=0;x<bem.length;x++){//分组
						if(bem[x].equals("productcode")){
							head += "jobdetail.productcode,";
						}else if(bem[x].equals("productid")){
							head += "jobdetail.m_strProductName,";
						}else if(bem[x].equals("lotinfo")){
							head += "jobdetail.lotinfo,";
						}
						else if(bem[x].equals("printdate")){
							head += "jobdetail.printdate,";
						}else if(bem[x].equals("customerid")){
							head += "jobdetail.m_strCustomerName,";
						}
						else if(bem[x].equals("boundstockid")){
							head += "job.boundstockid,";
						}
						else if(bem[x].equals("jobtype")){
							head += "job.jobtype,";
						}
						else if(bem[x].equals("createtime")){
							head += "substring(job.createtime,1,10),";
						}
					}
					head += "  sum(jobdetail.jobnum),sum(jobdetail.volume),sum(jobdetail.weight),sum(jobdetail.netweight) ";
				}else{ // 无分组时，显示货主名，产品名，日期，容积数量等。
					head = " select jobdetail.productcode, jobdetail.m_strProductName,jobdetail.lotinfo,jobdetail.printdate,jobdetail.m_strCustomerName,job.boundstockid,job.jobtype,substring(job.createtime,1,10),sum(jobdetail.jobnum),sum(jobdetail.volume),sum(jobdetail.weight),sum(jobdetail.netweight) ";
				}
				
				strBud.append(" from InoutJob as job, InoutJobdetail as jobdetail where job.jobid=jobdetail.jobid and job.inOutType='2' and job.status='4' ");
				
				if(warehouseid != null && warehouseid.trim().length() > 0){		//仓库
					strBud.append(" and job.warehouseid='").append(warehouseid).append("'");
				}
				
				if(whAreaId != null && whAreaId.trim().length() > 0){		//库区
					strBud.append(" and job.tcargoWhareaId='").append(whAreaId).append("'");
				}
				
				if(alleyId != null && alleyId.trim().length() > 0){		//巷道
					strBud.append(" and job.tcargoAlleyId='").append(alleyId).append("'");
				}
				
				
				if(indate_from != null && indate_from.trim().length() > 0){		//作业日期
					strBud.append(" and job.createtime>='").append(indate_from).append("'");
				}
				if(indate_to != null && indate_to.trim().length() > 0){		//作业日期
					strBud.append(" and job.createtime<='").append(indate_to).append(" 99:99:99'");
				}
				
				if(invoicetype != null && invoicetype.trim().length() > 0){	//作业类型
					strBud.append(" and job.invoicetype='").append(invoicetype).append("'");
				}

				if(productid != null && productid.trim().length() > 0){		//品名
					strBud.append(" and jobdetail.productid='").append(productid).append("'");
				}
				
				if(ownerid != null && ownerid.trim().length() > 0){		//货主
					strBud.append(" and jobdetail.ownerId='").append(ownerid).append("'");
				}
				
				if(traycode != null && traycode.trim().length() > 0){		//托盘条码
					strBud.append(" and job.traycode='").append(traycode).append("'");
				}
				
				if(customer_id != null && customer_id.trim().length() > 0){	//客户
					strBud.append(" and jobdetail.customerid='").append(customer_id).append("'");
				}
				
				if(boundstockid != null && boundstockid.trim().length() > 0){	//单号
					strBud.append(" and job.boundstockid='").append(boundstockid).append("'");
				}
				
				if(outtype != null && outtype.trim().length() > 0){	//作业类型
					strBud.append(" and job.outtype='").append(outtype).append("'");
				}
				
				
				
				

		        
				// --customerid|ownerId,1|2,客户|货主--
		        String strHQL = "";
		        if(groupinfo != null && groupinfo.trim().length()>0){ //分组排序
		        	String aem[] = groupinfo.split(",");
					String bem[] = aem[0].split("\\|");// 属性id
					strBud.append(" group by ");
					for(int x=0;x<bem.length;x++){//分组
						if(bem[x] != null && bem[x].trim().length()>0){
							if((x+1)==bem.length){
								if(bem[x].equals("productcode")||bem[x].equals("productid")){
									strBud.append("jobdetail.productid");
								}else if(bem[x].equals("createtime")){
									strBud.append("substring(job.createtime,1,10) ");
								}else if(bem[x].equals("boundstockid")||bem[x].equals("jobtype")){
									strBud.append("job."+bem[x]+" ");
								}else{
									strBud.append("jobdetail."+bem[x]+" ");
								}
							}else{
								if(bem[x].equals("productcode")||bem[x].equals("productid")){
									strBud.append("jobdetail.productid ,");
								}else if(bem[x].equals("boundstockid")||bem[x].equals("jobtype")){
									strBud.append("job."+bem[x]+",");
								}else{
									strBud.append("jobdetail."+bem[x]+",");
								}
							}	
						}
						
					}
					strBud.append(" order by ");
					for(int x=0;x<bem.length;x++){//排序
						if(bem[x] != null && bem[x].trim().length()>0){
							if((x+1)==bem.length){
								if(bem[x].equals("createtime")){
									strBud.append("substring(job.createtime,1,10) ");
								}else if(bem[x].equals("boundstockid")||bem[x].equals("jobtype")){
									strBud.append("job."+bem[x]+" ");
								}else{
									strBud.append("jobdetail."+bem[x]+" ");
								}
							}else{
								if(bem[x].equals("boundstockid")||bem[x].equals("jobtype")){
									strBud.append("job."+bem[x]+",");
								}else{
									strBud.append("jobdetail."+bem[x]+",");
								}
							}
						}	
					}
					strHQL = strBud.toString();
		        }else{
		        	strBud.append(" group by jobdetail.productid,jobdetail.lotinfo,jobdetail.printdate,jobdetail.customerid,job.boundstockid,job.jobtype,substring(job.createtime,1,10) ");
		        	strBud.append(" order by jobdetail.productid,jobdetail.lotinfo,jobdetail.printdate,jobdetail.customerid,job.boundstockid,job.jobtype,substring(job.createtime,1,10) ");
		        	strHQL = strBud.toString();
		        }
				
		        head  += strHQL;
		        head1 += strHQL;
		        list = inboundJobDao.getEntityDAO().searchEntities(head);
				
				pt = CommonPagination.getPagingTool(inboundJobDao.getEntityDAO(),list.size(), head , strUrl, 1, maxLine);
				
			} catch (Exception er) {
				throw new Exception("根据条件查询查询入库流水列表出错:" + er.getMessage());
			}
					
			return pt;
		}
    /**
     * 功能:获得批次属性的SQL
     * @param flg		第几个批次属性
     * @param lotatt	批次属性的值
     * @param lotDetail	批次属性
     * @return
     * @throws Exception
     */
	private String getSqlLotatt(String flg,	String lotatt, BaseLotDetail lotDetail) {
		
        StringBuilder strBud = new StringBuilder();	
		if(lotDetail != null){
			
			//页面查询时候的模式 1-精确查询,2-模糊查询(文本格式),3-范围查询(日期格式)
			String strLotsearch = lotDetail.getM_strLotsearch();	
		    
		    if(strLotsearch != null && strLotsearch.equals("1")){       //1-精确查询
                strBud.append(" and jobdetail.lotatt").append(flg).append(" ='").append(lotatt).append("'");   	
		    }else if(strLotsearch != null && strLotsearch.equals("2")){  //2-模糊查询
                strBud.append(" and jobdetail.lotatt").append(flg).append(" like '%").append(lotatt).append("%'");        
		    }else if(strLotsearch != null && strLotsearch.equals("3")){  //3-范围查询(日期格式)    	
	    		String[] lotatts =  lotatt.split("\\|");
		        if(lotatts.length>0 && lotatts[0] !=null && lotatts[0].trim().length() > 0){
                    strBud.append(" and jobdetail.lotatt").append(flg).append(" >='").append(lotatts[0]).append("'");
		        }
		        if(lotatts.length>1 && lotatts[1] !=null && lotatts[1].trim().length() > 0){
                    strBud.append(" and jobdetail.lotatt").append(flg).append(" <='").append(lotatts[1]).append("'");
		        }
		        
		    }else{		//没有选择查询方式的时候，按照精确查询来处理
                strBud.append(" and jobdetail.lotatt").append(flg).append(" ='").append(lotatt).append("'");
		    }
		}
		return strBud.toString();
	}

	/**
	 * 功能:作废作业（作业管理）
	 * @param jobids		作业ID(可复数个)
	 * @param strUserCode	用户
	 * @return 
	 * @throws Exception
	 */
	public String cancelJobs(String jobids, String strUserCode) throws Exception {
		
		String strBackMsg = "Y";
		
		try {
			
			IBaseCargoSpaceDao baseCargoSpaceDAO = new BaseCargoSpaceDaoImpl(inboundJobDao.getEntityDAO());
			BaseCargospace cargospace = null;
			
			String[] jobid = jobids.split(",");
			for(int i=0; i<jobid.length; i++){
				
				InoutJob job = inboundJobDao.getJobByJobid(jobid[i]);
				if(job != null){
					
					//判断作业来源 1：直接采集生成的作业，2：按收货单来生成的作业，3：按入库单来生成的作业 4:按ERP其它单来生成的作业 12:人工生成的作业
					if(job.getInvoicetype().endsWith("1") ){	
									
						//判断作业状态 1.未执行 2.待执行 3.正执行 4.完成 5.作废 6.异常作业 8.暂停作业
						if(job.getStatus().equals("4") || job.getStatus().equals("5")){
							
							if(strBackMsg.equals("Y")){
								strBackMsg = "不能作废已经完成或者已经作废掉的作业！";
								
				 			}else {
				 				strBackMsg += "\r不能作废已经完成或者已经作废掉的作业！";
				 			}
							
						}else{
							
							//获取作业的目标货位
							cargospace = baseCargoSpaceDAO.getCargoSpaceById(job.getTcargoSpaceId());
							
							job.setStatus("5");
							cargospace.setCsstatus("1");
							//保存到数据库,作废作业，删除库存,修改货位状态为空货位
							inboundJobDao.cancelJob(job, cargospace, null); 
							Logger.info("用户【" + strUserCode + "】在作业管理模块里作废了入库作业：" + jobid[i]);
						}
					}else{
						
						if(strBackMsg.equals("Y")){
							strBackMsg = "只能作废来源是【直接采集生成的作业】的作业！";
							
			 			}else {
			 				strBackMsg += "\r只能作废来源是【直接采集生成的作业】的作业！";
			 			}
					}
					
				}else{
					
					if(strBackMsg.equals("Y")){
						strBackMsg = "作业不存在！";
						
		 			}else {
		 				strBackMsg += "\r作业不存在！";
		 			}
				}
			}
		}catch (Exception e) {
		
			e.printStackTrace();
			strBackMsg = "作废作业（作业管理）时候发生错误！";
			return strBackMsg;
		}
		
		return strBackMsg;
	}

	/**
	 * 功能:手动完成作业（作业管理）
	 * @param jobids		作业ID(可复数个)
	 * @param strUserCode	用户
	 * @return 
	 * @throws Exception
	 */
	public String finishJobs(String jobids, String strUserCode) throws Exception {
		
		String strBackMsg = "Y";
		
		try {
			
			IBaseCargoSpaceDao baseCargoSpaceDAO = new BaseCargoSpaceDaoImpl(inboundJobDao.getEntityDAO());
			BaseCargospace cargospace = null;
			
			String[] jobid = jobids.split(",");
			for(int i=0; i<jobid.length; i++){
				
				InoutJob job = inboundJobDao.getJobByJobid(jobid[i]);
				if(job != null){
					
					//判断作业来源 1：直接采集生成的作业，2：按收货单来生成的作业，3：按入库单来生成的作业 4:按ERP其它单来生成的作业 12:人工生成的作业
					    //if(job.getInvoicetype().endsWith("1") ){
									
						//判断作业状态 1.未执行 2.待执行 3.正执行 4.完成 5.作废 6.异常作业 8.暂停作业
						if(job.getStatus().equals("4") || job.getStatus().equals("5")){
							
							if(strBackMsg.equals("Y")){
								strBackMsg = "不能手动完成已经完成或者已经作废掉的作业！";
								
				 			}else {
				 				strBackMsg += "\r不能手动完成已经完成或者已经作废掉的作业！";
				 			}
							
						}else{
							
							String strTime = StrTools.getCurrDateTime(2);	//入库时间(保证一托盘上每箱的入库时间一致)
							
							//获取作业的目标货位 
							cargospace = baseCargoSpaceDAO.getCargoSpaceById(job.getTcargoSpaceId());
							if(cargospace != null){
							
								job.setStatus("4");
								job.setFinishtime(strTime);
								job.setOnLineType("2");		//脱机
								
								cargospace.setCsstatus("2");//货位状态 1：空货位；2：满货位；3：入库分配；4：出库分配；5：入库需盘点；6：出库需盘点；7：出库取货；8：已出货
								
								List lsjobdetail = getJobDetails(jobid[i]);		//作业明细列表
								List lsStorage = new ArrayList();							//库存列表
								InventoryStorage inventoryStorage = null;
								InoutJobdetail jobdetail = null;
								if(lsjobdetail!=null && lsjobdetail.size()>0){
									
									jobdetail = (InoutJobdetail)lsjobdetail.get(i);
									//增加库存信息
				                    inventoryStorage = new InventoryStorage();
				                    
				                    inventoryStorage.setCargoSpaceId(job.getTcargoSpaceId()); 	//货位ID
				                    inventoryStorage.setCargoAlleyId(job.getTcargoAlleyId()); 	//巷道ID
				                    //inventoryStorage.setCargoAreaId(space.getCargoAreaId());	//货区ID
				                    inventoryStorage.setWhAreaId(job.getTcargoWhareaId());  	//库区
				                    inventoryStorage.setWarehouseid(job.getWarehouseid());  	//仓库ID
				                    
				                    inventoryStorage.setOwnerId(jobdetail.getOwnerId());     	//货主ID
				                    inventoryStorage.setProductid(jobdetail.getProductid()); 	//物品ID
				                    inventoryStorage.setPackid(jobdetail.getPackid());       	//包装ID
				                    inventoryStorage.setPunit(jobdetail.getPunit());         	//计量单位
				                    inventoryStorage.setIndate(strTime);					 	//入库时间
				                    inventoryStorage.setLotid(jobdetail.getLotid());		 	//批次ID
				                    
				                    inventoryStorage.setIntype("2");                		 	//入库方式 1.联机 2.脱机 
				                    inventoryStorage.setPnum(jobdetail.getJobnum());         	//库存数量
				                    inventoryStorage.setPvolume(jobdetail.getVolume());      	//库存体积
				                    inventoryStorage.setPweight(jobdetail.getWeight());      	//库存重量
				                    inventoryStorage.setPnetweight(jobdetail.getNetweight());	//库存净重
				                    
				                    inventoryStorage.setInjobid(jobdetail.getJobid());              //作业ID
				                    inventoryStorage.setInjobetailid(jobdetail.getJobdetailid());   //作业详细ID
				                    
				                    inventoryStorage.setTraycode(job.getTraycode());        //托盘条码
				                    
				                    inventoryStorage.setReserve3(jobdetail.getReserve3());  //预留1
				                    inventoryStorage.setReserve4(jobdetail.getReserve4());  //预留1
				                   
				                    lsStorage.add(inventoryStorage);
								}
								
								//库存
								
								//保存到数据库,手动完成作业，添加库存,修改货位状态满空货位
								inboundJobDao.finishJob(job, cargospace, lsjobdetail, lsStorage); 
								Logger.info("用户【" + strUserCode + "】在作业管理模块里手动完成了入库作业：" + jobid[i]);
								
							}else{
								if(strBackMsg.equals("Y")){
									strBackMsg = "作业【" + jobid[i] + "】手动完成失败，无入库货位！";
									
					 			}else {
					 				strBackMsg += "\r作业【" + jobid[i] + "】手动完成失败，无入库货位！";
					 			}
							}
						}
					/*}else{
						
						if(strBackMsg.equals("Y")){
							strBackMsg = "只能手动完成来源是【直接采集生成的作业】的作业！";
							
			 			}else {
			 				strBackMsg += "\r只能手动完成来源是【直接采集生成的作业】的作业！";
			 			}
					}*/
					
				}else{
					
					if(strBackMsg.equals("Y")){
						strBackMsg = "作业不存在！";
						
		 			}else {
		 				strBackMsg += "\r作业不存在！";
		 			}
				}
			}
		}catch (Exception e) {
		
			e.printStackTrace();
			strBackMsg = "手动完成作业（作业管理）时候发生错误！";
			return strBackMsg;
		}
		
		return strBackMsg;
	}
	
	
	public List getInboundJobDetailsGroupListByIn(String warehouseid, String whAreaId, String alleyId,String indate_from, String indate_to,
			String invoicetype, String productid, String ownerid, String traycode, String lotid, String strUrl,String boundstockid,String groupinfo,String customer_id,String intype) throws Exception{
		List list = null;
		try {
			StringBuilder strBud = new StringBuilder();
			String head = "select ";
			String head1 = "select count(*) ";
			
			if(groupinfo != null && groupinfo.trim().length()>0){ //获取字符字段
				
				String aem[] = groupinfo.split(",");
				String bem[] = aem[0].split("\\|");// 属性id
				
				for(int x=0;x<bem.length;x++){//分组
					if(bem[x] != null && bem[x].trim().length()>0){
						if(bem[x].equals("productcode")){
							head += "jobdetail.productcode,";
						}else if(bem[x].equals("productid")){
							head += "jobdetail.m_strProductName,";
						}else if(bem[x].equals("lotinfo")){
							head += "jobdetail.lotinfo,";
						}
						else if(bem[x].equals("printdate")){
							head += "jobdetail.printdate,";
						}
						else if(bem[x].equals("boundstockid")){
							head += "job.boundstockid,";
						}
						else if(bem[x].equals("jobtype")){
							head += "job.jobtype,";
						}
						else if(bem[x].equals("createtime")){
							head += "substring(job.createtime,1,10),";
						}
						
					}	
				}
				head += "sum(jobdetail.jobnum),sum(jobdetail.volume),sum(jobdetail.weight),sum(jobdetail.netweight) ";
			}else{ // 无分组时，显示货主名，产品名，日期，容积数量等。
				head = " select jobdetail.productcode, jobdetail.m_strProductName,jobdetail.lotinfo,jobdetail.printdate,job.boundstockid,job.jobtype,substring(job.createtime,1,10),sum(jobdetail.jobnum),sum(jobdetail.volume),sum(jobdetail.weight),sum(jobdetail.netweight) ";
			}
			
			strBud.append(" from InoutJob as job, InoutJobdetail as jobdetail where job.jobid=jobdetail.jobid and job.inOutType='1' and job.status='4' ");
			
			if(warehouseid != null && warehouseid.trim().length() > 0){		//仓库
				strBud.append(" and job.warehouseid='").append(warehouseid).append("'");
			}
			
			if(whAreaId != null && whAreaId.trim().length() > 0){		//库区
				strBud.append(" and job.tcargoWhareaId='").append(whAreaId).append("'");
			}
			
			if(alleyId != null && alleyId.trim().length() > 0){		//巷道
				strBud.append(" and job.tcargoAlleyId='").append(alleyId).append("'");
			}
			
			if(indate_from != null && indate_from.trim().length() > 0){		//作业日期
				strBud.append(" and job.createtime>='").append(indate_from).append("'");
			}
			if(indate_to != null && indate_to.trim().length() > 0){		//作业日期
				strBud.append(" and job.createtime<='").append(indate_to).append(" 99:99:99'");
			}
			
			if(invoicetype != null && invoicetype.trim().length() > 0){	//作业类型
				strBud.append(" and job.invoicetype='").append(invoicetype).append("'");
			}

			if(productid != null && productid.trim().length() > 0){		//品名
				strBud.append(" and jobdetail.productid='").append(productid).append("'");
			}
			
			if(ownerid != null && ownerid.trim().length() > 0){		//货主
				strBud.append(" and jobdetail.ownerId='").append(ownerid).append("'");
			}
			
			if(traycode != null && traycode.trim().length() > 0){		//托盘条码
				strBud.append(" and job.traycode='").append(traycode).append("'");
			}
			
			if(customer_id != null && customer_id.trim().length() > 0){	//客户
				strBud.append(" and jobdetail.customerid='").append(customer_id).append("'");
			}
			
			if(boundstockid != null && boundstockid.trim().length() > 0){	//单号
				strBud.append(" and job.boundstockid='").append(boundstockid).append("'");
			}
			
			if(intype != null && intype.trim().length() > 0){	//作业类型
				strBud.append(" and job.jobtype='").append(intype).append("'");
			}
			
	      
			// --customerid|ownerId,1|2,客户|货主--
	        String strHQL = "";
	        if(groupinfo != null && groupinfo.trim().length()>0){ //分组排序
	        	String aem[] = groupinfo.split(",");
				String bem[] = aem[0].split("\\|");// 属性id
				strBud.append(" group by ");
				for(int x=0;x<bem.length;x++){//分组
					if(bem[x] != null && bem[x].trim().length()>0){
						if((x+1)==bem.length){
							if(bem[x].equals("productcode")||bem[x].equals("productid")){
								strBud.append("jobdetail.productid");
							}else if(bem[x].equals("createtime")){
								strBud.append("substring(job.createtime,1,10) ");
							}else if(bem[x].equals("boundstockid")||bem[x].equals("jobtype")){
								strBud.append("job."+bem[x]+" ");
							}else{
								strBud.append("jobdetail."+bem[x]+" ");
							}
						}else{
							if(bem[x].equals("productcode")||bem[x].equals("productid")){
								strBud.append("jobdetail.productid,");
							}else  if(bem[x].equals("boundstockid")||bem[x].equals("jobtype")){
								strBud.append("job."+bem[x]+",");
							}else{
								strBud.append("jobdetail."+bem[x]+",");
							}
						}	
					}
					
				}
				strBud.append(" order by ");
				for(int x=0;x<bem.length;x++){//排序
					if(bem[x] != null && bem[x].trim().length()>0){
						if((x+1)==bem.length){
							if(bem[x].equals("createtime")){
								strBud.append("substring(job.createtime,1,10) ");
							}else if(bem[x].equals("boundstockid")||bem[x].equals("jobtype")){
								strBud.append("job."+bem[x]+" ");
							}else{
								strBud.append("jobdetail."+bem[x]+" ");
							}
						}else{
							if(bem[x].equals("boundstockid")||bem[x].equals("jobtype")){
								strBud.append("job."+bem[x]+",");
							}else{
								strBud.append("jobdetail."+bem[x]+",");
							}
						}
					}	
				}
				strHQL = strBud.toString();
	        }else{
	        	strBud.append(" group by jobdetail.productid,jobdetail.lotinfo,jobdetail.printdate,job.boundstockid,job.jobtype,substring(job.createtime,1,10)");
	        	strBud.append(" order by jobdetail.productid,jobdetail.lotinfo,jobdetail.printdate,job.boundstockid,job.jobtype,substring(job.createtime,1,10)");
	        	strHQL = strBud.toString();
	        }
			
	        head  += strHQL;
	        head1 += strHQL;
//	        head1 += "select count(*) from ("+head1+") as ss";
	       list = inboundJobDao.getEntityDAO().searchEntities(head);
			
			//pt = CommonPagination.getPagingTool(inboundJobDao.getEntityDAO(),list.size(), head , strUrl, 1, maxLine);
			
		} catch (Exception er) {
			throw new Exception("根据条件查询查询入库流水列表出错:" + er.getMessage());
		}
				
		return list;
	}
	
	public List getInboundJobDetailsGroupListByOut(String warehouseid, String whAreaId, String alleyId,String indate_from, String indate_to,
			String invoicetype, String productid, String ownerid, String traycode, String lotid, String strUrl, String boundstockid,String groupinfo,String customer_id,String outtype) throws Exception{
		//PagingTool pt = null;
		List list = null;
		
		try {
			StringBuilder strBud = new StringBuilder();
			String head = "select ";
			String head1 = "select count(*) ";
			
			if(groupinfo != null && groupinfo.trim().length()>0){ //获取字符字段
				
				String aem[] = groupinfo.split(",");
				String bem[] = aem[0].split("\\|");// 属性id
				
				for(int x=0;x<bem.length;x++){//分组
					if(bem[x].equals("productcode")){
						head += "jobdetail.productcode,";
					}else if(bem[x].equals("productid")){
						head += "jobdetail.m_strProductName,";
					}else if(bem[x].equals("lotinfo")){
						head += "jobdetail.lotinfo,";
					}
					else if(bem[x].equals("printdate")){
						head += "jobdetail.printdate,";
					}else if(bem[x].equals("customerid")){
						head += "jobdetail.m_strCustomerName,";
					}
					else if(bem[x].equals("boundstockid")){
						head += "job.boundstockid,";
					}
					else if(bem[x].equals("jobtype")){
						head += "job.jobtype,";
					}
					else if(bem[x].equals("createtime")){
						head += "substring(job.createtime,1,10),";
					}
				}
				head += "  sum(jobdetail.jobnum),sum(jobdetail.volume),sum(jobdetail.weight),sum(jobdetail.netweight) ";
			}else{ // 无分组时，显示货主名，产品名，日期，容积数量等。
				head = " select jobdetail.productcode, jobdetail.m_strProductName,jobdetail.lotinfo,jobdetail.printdate,jobdetail.m_strCustomerName,job.boundstockid,job.jobtype,substring(job.createtime,1,10),sum(jobdetail.jobnum),sum(jobdetail.volume),sum(jobdetail.weight),sum(jobdetail.netweight) ";
			}
			
			strBud.append(" from InoutJob as job, InoutJobdetail as jobdetail where job.jobid=jobdetail.jobid and job.inOutType='2' and job.status='4' ");
			
			if(warehouseid != null && warehouseid.trim().length() > 0){		//仓库
				strBud.append(" and job.warehouseid='").append(warehouseid).append("'");
			}
			
			if(whAreaId != null && whAreaId.trim().length() > 0){		//库区
				strBud.append(" and job.tcargoWhareaId='").append(whAreaId).append("'");
			}
			
			if(alleyId != null && alleyId.trim().length() > 0){		//巷道
				strBud.append(" and job.tcargoAlleyId='").append(alleyId).append("'");
			}
			
			
			if(indate_from != null && indate_from.trim().length() > 0){		//作业日期
				strBud.append(" and job.createtime>='").append(indate_from).append("'");
			}
			if(indate_to != null && indate_to.trim().length() > 0){		//作业日期
				strBud.append(" and job.createtime<='").append(indate_to).append(" 99:99:99'");
			}
			
			if(invoicetype != null && invoicetype.trim().length() > 0){	//作业类型
				strBud.append(" and job.invoicetype='").append(invoicetype).append("'");
			}

			if(productid != null && productid.trim().length() > 0){		//品名
				strBud.append(" and jobdetail.productid='").append(productid).append("'");
			}
			
			if(ownerid != null && ownerid.trim().length() > 0){		//货主
				strBud.append(" and jobdetail.ownerId='").append(ownerid).append("'");
			}
			
			if(traycode != null && traycode.trim().length() > 0){		//托盘条码
				strBud.append(" and job.traycode='").append(traycode).append("'");
			}
			
			if(customer_id != null && customer_id.trim().length() > 0){	//客户
				strBud.append(" and jobdetail.customerid='").append(customer_id).append("'");
			}
			
			if(boundstockid != null && boundstockid.trim().length() > 0){	//单号
				strBud.append(" and job.boundstockid='").append(boundstockid).append("'");
			}
			
			if(outtype != null && outtype.trim().length() > 0){	//作业类型
				strBud.append(" and job.outtype='").append(outtype).append("'");
			}
			
			
			
			

	        
			// --customerid|ownerId,1|2,客户|货主--
	        String strHQL = "";
	        if(groupinfo != null && groupinfo.trim().length()>0){ //分组排序
	        	String aem[] = groupinfo.split(",");
				String bem[] = aem[0].split("\\|");// 属性id
				strBud.append(" group by ");
				for(int x=0;x<bem.length;x++){//分组
					if(bem[x] != null && bem[x].trim().length()>0){
						if((x+1)==bem.length){
							if(bem[x].equals("productcode")||bem[x].equals("productid")){
								strBud.append("jobdetail.productid");
							}else if(bem[x].equals("createtime")){
								strBud.append("substring(job.createtime,1,10) ");
							}else if(bem[x].equals("boundstockid")||bem[x].equals("jobtype")){
								strBud.append("job."+bem[x]+" ");
							}else{
								strBud.append("jobdetail."+bem[x]+" ");
							}
						}else{
							if(bem[x].equals("productcode")||bem[x].equals("productid")){
								strBud.append("jobdetail.productid ,");
							}else if(bem[x].equals("boundstockid")||bem[x].equals("jobtype")){
								strBud.append("job."+bem[x]+",");
							}else{
								strBud.append("jobdetail."+bem[x]+",");
							}
						}	
					}
					
				}
				strBud.append(" order by ");
				for(int x=0;x<bem.length;x++){//排序
					if(bem[x] != null && bem[x].trim().length()>0){
						if((x+1)==bem.length){
							if(bem[x].equals("createtime")){
								strBud.append("substring(job.createtime,1,10) ");
							}else if(bem[x].equals("boundstockid")||bem[x].equals("jobtype")){
								strBud.append("job."+bem[x]+" ");
							}else{
								strBud.append("jobdetail."+bem[x]+" ");
							}
						}else{
							if(bem[x].equals("boundstockid")||bem[x].equals("jobtype")){
								strBud.append("job."+bem[x]+",");
							}else{
								strBud.append("jobdetail."+bem[x]+",");
							}
						}
					}	
				}
				strHQL = strBud.toString();
	        }else{
	        	strBud.append(" group by jobdetail.productid,jobdetail.lotinfo,jobdetail.printdate,jobdetail.customerid,job.boundstockid,job.jobtype,substring(job.createtime,1,10) ");
	        	strBud.append(" order by jobdetail.productid,jobdetail.lotinfo,jobdetail.printdate,jobdetail.customerid,job.boundstockid,job.jobtype,substring(job.createtime,1,10) ");
	        	strHQL = strBud.toString();
	        }
			
	        head  += strHQL;
	        head1 += strHQL;
	        list = inboundJobDao.getEntityDAO().searchEntities(head);
			
			//pt = CommonPagination.getPagingTool(inboundJobDao.getEntityDAO(),list.size(), head , strUrl, 1, maxLine);
			
		} catch (Exception er) {
			throw new Exception("根据条件查询查询入库流水列表出错:" + er.getMessage());
		}
				
		return list;
	}
}
