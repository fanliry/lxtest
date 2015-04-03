package com.wms3.bms.standard.business.quality.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.base.ILotBus;
import com.wms3.bms.standard.business.base.impl.LotBusImpl;
import com.wms3.bms.standard.business.inventory.IInventoryBus;
import com.wms3.bms.standard.business.inventory.ProductAdjustBus;
import com.wms3.bms.standard.business.inventory.impl.InventoryBusImpl;
import com.wms3.bms.standard.business.inventory.impl.ProductAdjustBusImpl;
import com.wms3.bms.standard.business.quality.IQualityBus;
import com.wms3.bms.standard.business.quality.InventoryQualityBus;
import com.wms3.bms.standard.constant.CommonReturn;
import com.wms3.bms.standard.dao.base.impl.BaseSeqDaoImpl;
import com.wms3.bms.standard.dao.inventory.IInventoryDao;
import com.wms3.bms.standard.dao.inventory.impl.InventoryDaoImpl;
import com.wms3.bms.standard.entity.base.BaseLotDetail;
import com.wms3.bms.standard.entity.base.BaseSeq;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail;
import com.wms3.bms.standard.entity.inventory.InventoryQualityResult;
import com.wms3.bms.standard.entity.inventory.InventoryQualityResultDetail;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;
	/**
	 * 描述：质检管理业务接口实现类
	 * @author liuxh
	 *
	 */
	public class QualityBusImpl implements IQualityBus {
		protected EntityDAO m_dao = null;
		protected IInventoryBus iBus= null;
		protected IInventoryDao iDao= null;
		protected ProductAdjustBus pBus = null;
		protected ILotBus lotBus;
		public QualityBusImpl(EntityDAO dao) {
			this.m_dao = dao;
			this.iBus = new InventoryBusImpl(dao);
			this.lotBus = new LotBusImpl(dao);
			this.iDao = new InventoryDaoImpl(dao);
			this.pBus = new ProductAdjustBusImpl(m_dao);
		}

		@Override
		public String searchInventoryForLotnumber(String warehouseid,
				String whareaid, String lotnumber, String requestid,
				String productid, String productstatus,String productCode) throws Exception {
			
			StringBuilder strBud = new StringBuilder();
			try {
				strBud.append("select inven.warehouseid, inven.warehouseName,inven.whAreaId,inven.whAreaName,inven.requestid,inven.lotinfo,inven.productid,inven.productcode,inven.productName, inven.productstatus,inven.productStatusName,sum(inven.pnum)");
	        //分组
			StringBuilder strBudSqlGrp = new StringBuilder();
			strBudSqlGrp.append(" group by inven.warehouseid,inven.whAreaId,inven.requestid,inven.lotinfo,inven.productid,inven.productcode,inven.productstatus");
			
			strBud.append(" From InventoryStorage as inven where 1=1 and inven.status='0' ") ;
			if (warehouseid!=null && warehouseid.trim().length()>0) {
				strBud.append(" and inven.warehouseid='").append(warehouseid).append("'");
			}
			if (whareaid!=null && whareaid.trim().length()>0) {
				strBud.append(" and inven.whAreaId='").append(whareaid).append("'");
			}
			if (lotnumber!=null &&lotnumber.trim().length()>0) {
				strBud.append(" and inven.lotinfo='").append(lotnumber).append("'");
			}
			if (requestid!=null &&requestid.trim().length()>0) {
				strBud.append(" and inven.requestid='").append(requestid).append("'");
			}
			if (productid!=null &&productid.trim().length()>0) {
				strBud.append(" and inven.productid='").append(productid).append("'");
			}
			if (productstatus != null && productstatus.trim().length()>0) {
				strBud.append(" and inven.productstatus='").append(productstatus).append("'");
			}
			if (productCode != null && productCode.trim().length()>0) {
				strBud.append(" and inven.productcode='").append(productCode).append("'");
			}
			strBud.append(strBudSqlGrp);
			strBud.append(" order by inven.lotinfo");
			
			} catch (Exception er) {
				throw new Exception("质检管理->放行查询语句拼接出错：" + er.getMessage());
			}
			return strBud.toString();
		}

		@Override
		public String searchInventroyForInstock(String warehouseid,
				String lotnumber, String requestid, String productid, String productstatus,String whareaid)
				throws Exception {
			StringBuilder strBud = new StringBuilder();
			try {
			strBud.append("select inven.warehouseid,inven.whAreaName,inven.instockid,pro.productname,pro.spec,inven.productstatus,inven.pnum,inven.productid");
	        //分组
			//StringBuilder strBudSqlGrp = new StringBuilder();
			//strBudSqlGrp.append(" group by inven.warehouseid,inven.whAreaId,inven.instockid,pro.productname,pro.spec,inven.productstatus");
			strBud.append(" From InventoryStorage as inven,BaseProduct as pro where pro.productid = inven.productid") ;
			if (warehouseid!=null && warehouseid.trim().length()>0) {
				strBud.append(" and inven.warehouseid='").append(warehouseid).append("'");
			}
			if (lotnumber!=null &&lotnumber.trim().length()>0) {
				strBud.append(" and inven.lotinfo='").append(lotnumber).append("'");
			}
			if (requestid!=null &&requestid.trim().length()>0) {
				strBud.append(" and inven.requestid='").append(requestid).append("'");
			}
			if (productid!=null &&productid.trim().length()>0) {
				strBud.append(" and pro.productid='").append(productid).append("'");
			}
			if (productstatus != null && productstatus.trim().length()>0) {
				strBud.append(" and inven.productstatus='").append(productstatus).append("'");
			}
			if (whareaid!=null && whareaid.trim().length()>0) {
				strBud.append(" and inven.whAreaId='").append(whareaid).append("'");
			}
			//strBud.append(strBudSqlGrp);
			strBud.append(" order by inven.instockid");
		
			} catch (Exception er) {
				throw new Exception("质检管理->放行查询语句拼接出错：" + er.getMessage());
			}
			return strBud.toString();
		}
		/**
		 * 功能:质检管理->抽检查询库存的SQL
		 * @param warehouseid	仓库ID
		 * @param cargoAreaId	货区ID
		 * @param whAreaId		库区ID
		 * @param platoon		排
		 * @param column		列
		 * @param floor			层
		 * @param productid		产品id
		 * @param traycode		托盘条码
		 * @param lotid		          批次ID
		 * @param lotatt1-lotatt12 批次属性
		 * @param strUrl 		地址
		 * @param maxLine 		分页显示行数
		 * @return
		 * @throws Exception
		 */
		public PagingTool getKcSelect(String warehouseId, String whAreaId,
				String cargoAreaId, String platoon, String column, String floor,
				String customerId,String indate_from ,String indate_to,String productid, String traycode, String lotid, String lotatt1,
				String lotatt2, String lotatt3, String lotatt4, String lotatt5,
				String lotatt6, String lotatt7, String lotatt8, String lotatt9,
				String lotatt10, String lotatt11, String lotatt12, String strUrl,
				int maxLine) throws Exception {
				
			    PagingTool pt = null;
				String strCountHQL = "";
				String strHQL = "";
		        StringBuilder strBud = new StringBuilder(); 
		        try {
		        HashMap<String, BaseLotDetail> hsLot = lotBus.getHashMapByLotId(lotid);	       
		        if((platoon!=null && platoon.trim().length()>0) || (column!=null && column.trim().length()>0) || (floor!=null && floor.trim().length()>0)){
		            strBud.append(" From InventoryStorage as sto, BaseCargospace as space where sto.cargoSpaceId=space.cargoSpaceId");
		        }else{
		            strBud.append(" From InventoryStorage as sto where 1=1");
		        }
		        
		        //仓库ID
		        if(warehouseId != null && warehouseId.trim().length() > 0){
		        
		            strBud.append(" and sto.warehouseid='").append(warehouseId).append("'");
		        }
		        
		        //库区ID
		        if(whAreaId != null && whAreaId.trim().length() > 0){
		        
		            strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
		        }
		        
		        //巷道ID
		        if(cargoAreaId != null && cargoAreaId.trim().length()>0){
		        
		            strBud.append(" and sto.cargoAlleyId='").append(cargoAreaId).append("'");
		        }
		
		        //排
		        if(platoon != null && platoon.trim().length() >0){
		        
		            strBud.append(" and space.csplatoon=").append(Integer.parseInt(platoon));
		        }
		        
		        //列
		        if(column != null && column.trim().length() >0){
		        
		            strBud.append(" and space.cscolumn=").append(Integer.parseInt(column));
		        }
		        
		        //层
		        if(floor != null && floor.trim().length() > 0){
		        
		            strBud.append(" and space.csfloor=").append(Integer.parseInt(floor));
		        }
		        
		        //货主
		        if(customerId != null && customerId.trim().length()>0){
		        
		            strBud.append(" and sto.ownerId='").append(customerId).append("'");
		        }
		        
		        //品名ID
		        if(productid != null && productid.trim().length() > 0){
		        
		            strBud.append(" and sto.productid='").append(productid).append("'");
		        }
		        
		        //托盘条码
		        if(traycode != null && traycode.trim().length()>0){
		        
		            strBud.append(" and sto.traycode='").append(traycode).append("'");
		        }
		        
		        //入库日期
		        if(indate_from != null && indate_from.trim().length()>0){
		        
		            strBud.append(" and sto.indate>='").append(indate_from).append("'");
		        }
		        if(indate_to != null && indate_to.trim().length()>0){
		            
		            strBud.append(" and sto.indate<='").append(indate_to).append(" 99:99:99'");
		        }
		        
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
				strHQL = strBud.toString();
		        } catch (Exception er) {
		        	 throw new Exception("抽检->获取库存信息出错：" + er.getMessage());
				}
				strCountHQL= "select count(*)"+strHQL;
				pt = CommonPagination.getPagingTool(m_dao, strCountHQL ,"select sto" + strHQL, strUrl, 1, maxLine);
				return pt;
	
		}
	    /**
	     * 功能:库存管理->库存查询,获得查询条件的SQL,获得批次属性的SQL
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
	                strBud.append(" and sto.lotatt").append(flg).append(" ='").append(lotatt).append("'");   	
			    }else if(strLotsearch != null && strLotsearch.equals("2")){  //2-模糊查询
	                strBud.append(" and sto.lotatt").append(flg).append(" like '%").append(lotatt).append("%'");        
			    }else if(strLotsearch != null && strLotsearch.equals("3")){  //3-范围查询(日期格式)    	
		    		String[] lotatts =  lotatt.split("\\|");
			        if(lotatts.length>0 && lotatts[0] !=null && lotatts[0].trim().length() > 0){
	                    strBud.append(" and sto.lotatt").append(flg).append(" >='").append(lotatts[0]).append("'");
			        }
			        if(lotatts.length>1 && lotatts[1] !=null && lotatts[1].trim().length() > 0){
	                    strBud.append(" and sto.lotatt").append(flg).append(" <='").append(lotatts[1]).append("'");
			        }
			        
			    }else{		//没有选择查询方式的时候，按照精确查询来处理
	                strBud.append(" and sto.lotatt").append(flg).append(" ='").append(lotatt).append("'");
			    }
			}
			return strBud.toString();
		}
		/**
		 * 功能：根据库存ID获得库存
		 * @param inventID 库存iD
		 */
		public InventoryStorage getInventById(String inventId) throws Exception {
			InventoryStorage invent = null;
			
			try
			{
				invent = iDao.getInventoryById(inventId);
			}catch(Exception er)
			{
				throw new Exception("根据库存ID获得库存信息出错(QualityBusImpl.getInventById):" + er.getMessage());
			}
			return invent;
		}
		
		/**
	     * 功能：增加抽检出库单
	     * @param departId 部门id
	     * @param formdate 单据日期
	     * @param createmanid 操作员
	     * @param customerId 客户
	     * @param ownerId 货主
	     * @param warehouseManId 仓管员 
	     */
		public OutboundInvoiceHeader addpOutboundInvoiceHeader(String warehouseId,String departId,
				String formdate, String createmanid, String customerId,
				String ownerId,String warehouseManId) throws Exception {
			OutboundInvoiceHeader outInvoice = new OutboundInvoiceHeader();
			try {	
			outInvoice.setWarehouseid(warehouseId);                 //仓库ID
	        outInvoice.setOuttype("7");                             //出库类型
	        outInvoice.setDepartid(departId);                       //部门id
	        outInvoice.setCreatetime(StrTools.getCurrDateTime(2));  //出库单生成时间
	        outInvoice.setFormdate(formdate);                       //单据日期
	        outInvoice.setOutpos("2");                              //出库点
	        outInvoice.setOutstatus("0");                           //单据状态 0.出库开单 
	        outInvoice.setCreatemanid(createmanid);                 //创建人
	        outInvoice.setOpmanid(createmanid);                     //操作员
	        outInvoice.setIsupload("N");                            //是否上传成功 Y.是 N.否 默认为否
	        outInvoice.setOwnerid(ownerId);                         //货主
	        outInvoice.setCustomerid(customerId);                   //客户
	        outInvoice.setWarehousemanid(warehouseManId);           //仓库管理员     
	        
	        BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(m_dao);
            BaseSeq  seqEn = seqDao.getSeqByType("CKD");
            outInvoice.setOutstockid(SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), m_dao));
			} catch (Exception er) {
				throw new Exception("生成抽检出库单据出错:" + er.getMessage());
			}
			return outInvoice;
		}
		/**
	     * 功能：增加抽检出库单明细出库作业明细
	     * @param inventId 库存ID
	     * @param outInvoiceId 出库单据ID
	     * @param outJob  出库作业ID
	     * @param Num    库存数量
	     * @param CheckNum  抽检数量
	     * @param customerId 客户ID
	     * @return
	     * @throws Exception
	     */
		public Object[] addOutInvoiceDelAddOutJob(String OpManId,String inventId,String outInvoiceId, String outjob, double Num,
				double CheckNum, String customerId,String i) throws Exception {	
			Object[] obj= new Object[4];
			//根据库存ID获得库存
			InventoryStorage iStorage = getInventById(inventId);
			if (iStorage!=null) {
				 OutboundInvoiceDetail outInvoiceDel = new OutboundInvoiceDetail();
				 InoutJob outJob = new InoutJob();
				 InoutJobdetail outJobDel = new InoutJobdetail();
				 try {
			
				 //新建抽检出库单明细
				 outInvoiceDel.setAssignnum(CheckNum); //分配数量
				 outInvoiceDel.setCargoAlleyId(iStorage.getCargoAlleyId()); //巷道ID
				 outInvoiceDel.setCargoSpaceId(iStorage.getCargoSpaceId());//货位
				 outInvoiceDel.setCustomid(customerId);//客户id
				 outInvoiceDel.setLinestatus("0");//设置单据行状态
				 outInvoiceDel.setLotatt1(iStorage.getLotatt1());//批次属性1-属性12
				 outInvoiceDel.setLotatt2(iStorage.getLotatt2());
				 outInvoiceDel.setLotatt3(iStorage.getLotatt3());
				 outInvoiceDel.setLotatt4(iStorage.getLotatt4());
				 outInvoiceDel.setLotatt5(iStorage.getLotatt5());
				 outInvoiceDel.setLotatt6(iStorage.getLotatt6());
				 outInvoiceDel.setLotatt7(iStorage.getLotatt7());
				 outInvoiceDel.setLotatt8(iStorage.getLotatt8());
				 outInvoiceDel.setLotatt9(iStorage.getLotatt9());
				 outInvoiceDel.setLotatt10(iStorage.getLotatt10());
				 outInvoiceDel.setLotatt9(iStorage.getLotatt11());
				 outInvoiceDel.setLotatt10(iStorage.getLotatt12());
				 outInvoiceDel.setLotid(iStorage.getLotid());//批次ID
				 outInvoiceDel.setM_strPackName(iStorage.getPackagename());//包装名称
				 outInvoiceDel.setM_strProductName(iStorage.getProductName());//产品名称
				 outInvoiceDel.setM_strUnitName(iStorage.getPunitname());//单位名次
				 outInvoiceDel.setOutstockdetailid(SeqTool.getDetailId(outInvoiceId,i));//出库单据详细ID
				 outInvoiceDel.setOutstockid(outInvoiceId);//出库单据ID
				 outInvoiceDel.setPackid(iStorage.getPackid());//设置单据行状态
				 outInvoiceDel.setPkgunit(iStorage.getPunit());//包装单位
				 outInvoiceDel.setProductid(iStorage.getProductid());//包装单位
				 outInvoiceDel.setTraycode(iStorage.getTraycode());//托盘条码
				 outInvoiceDel.setWhAreaId(iStorage.getWhAreaId());//托盘条码   
				 //新增作业
				 
				 BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(m_dao);
	             BaseSeq  seqEn = seqDao.getSeqByType("CKZY"); 
				 outJob.setJobid(SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), m_dao));//作业编号
				 
		         outJob.setCreatetime(StrTools.getCurrDateTime(8));//  创建时间
		         outJob.setInOutType("2"); //出入库类型     
		         outJob.setIsaccount("N"); //是否记账
		         outJob.setJobpos("2");    //作业端
		         outJob.setJobtypeName("抽检出库");//作业类型名字
		         outJob.setOpMan(OpManId);//操作员
		         outJob.setOpManId(OpManId);//操作员ID
		         outJob.setStatus("0");	//作业状态
		         outJob.setOnLineType("2");
		         outJob.setJobtype("2");
		         outJob.setWarehouseid(iStorage.getWarehouseid());//仓库ID
		         outJob.setPriority(10);//优先级
		         outJob.setScargoAlleyId(iStorage.getCargoAlleyId());//巷道id
		         outJob.setScargoWhareaName(iStorage.getWhAreaName());//巷道名字
		         outJob.setScargoSpaceId(iStorage.getCargoSpaceId());//货位id
		         outJob.setScargoSpaceName(iStorage.getCargoSpaceName());//货位名次
		         outJob.setTraycode(iStorage.getTraycode());	         //托盘条码
		         outJob.setTcargoAlleyId(iStorage.getCargoAlleyId());//	目标巷道
		         outJob.setTcargoSpaceId(iStorage.getCargoSpaceId());//目标货位id
		         outJob.setTcargoSpaceName(iStorage.getCargoSpaceName());//货位名称
		         outJob.setTcargoWhareaId(iStorage.getWhAreaId());//库区
				 
				 //新建抽检出库作业明细
				 outJobDel.setAssignnum(CheckNum);//分配数量
				 outJobDel.setBoxcode(iStorage.getBoxcode());//条码
				 outJobDel.setCustomerid(customerId);//客户ID
				 outJobDel.setInventoryid(inventId);//库存ID
				
				 outJobDel.setJobdetailid(SeqTool.getDetailId(outJob.getJobid(),"9"));//作业详细ID
				 outJobDel.setJobid(outJob.getJobid());//作业ID
				 outJobDel.setJobnum(CheckNum);//作业数量
				 outJobDel.setLinestatus("0");//作业状态4完成
				 outJobDel.setM_strCustomerName(customerId);//客户名字
				 outJobDel.setM_strOwnerName(iStorage.getOwnerName());//货主
				 outJobDel.setM_strPackName(iStorage.getPackagename());//包装名称
				 outJobDel.setM_strProductName(iStorage.getPackagename());//设置产品名字
				 outJobDel.setM_strUnitName(iStorage.getPunit());//设置产品
				 outJobDel.setOwnerId(iStorage.getOwnerId());//货主ID
				 outJobDel.setProductcode(iStorage.getProductcode());//设置产品条码
				 outJobDel.setProductid(iStorage.getProductid());//设置物品规格
				 outJobDel.setPunit(iStorage.getPunit());//设置物品规格		 
				 
				
				 outJobDel.setLotid(iStorage.getLotid());//设置批次类型ID
				 iStorage.setStatus("1");//设置库存状态 1.已分配
				 iStorage.setPnum(Num-CheckNum);//更新库存数量
				 obj[0]=outInvoiceDel;
				 obj[1]=outJob;
				 obj[2]=outJobDel;
				 obj[3]=iStorage;				
				} catch (Exception e) {
		    	 Logger.error("抽检》增加抽检出库单明细和作业明细出差！QualityBusImpl.addBoundAddBoundDelAddJobAddJobDel："+e.getMessage());
				}
			}
			return obj;
		}
		/**
	     * 功能：保存 抽检出库单据，抽检出库单明细，抽检出库作业，抽检出库作业明细更新库存
	     * @param outInvoiceHeader 抽检出库单
	     * @param outJob 出库作业
	     * @param lObjects 
	     * @return
	     * @throws Exception
	     */
		public String addBoundAddBoundDelAddJobAddJobDel(OutboundInvoiceHeader outInvoiceHeader,List<Object[]> delObj)
				throws Exception {
			String strMeg = "保存成功";
			Session session = m_dao.getSession();
			try {			
			Transaction tx = session.beginTransaction();
			if (delObj!=null&&delObj.size()>0) {
				for (int i = 0; i < delObj.size(); i++) {
					OutboundInvoiceDetail outBoundDel = new OutboundInvoiceDetail();
					InoutJobdetail outJobDel = new InoutJobdetail();
					InoutJob outJob = new InoutJob();
					InventoryStorage iStorage = null;
					outBoundDel = (OutboundInvoiceDetail) delObj.get(i)[0];//抽检出库单明细
					outJob = (InoutJob) delObj.get(i)[1];//抽检出库作业
					outJobDel = (InoutJobdetail) delObj.get(i)[2]; //抽检作业明细					
					iStorage = (InventoryStorage)delObj.get(i)[3];//库存
					session.save("OutboundInvoiceDetail",outBoundDel);
					session.save("OutboundInvoiceDetail",outBoundDel);		
					session.save("InoutJob",outJob);
					
					session.save("InoutJobdetail",outJobDel);

					//更新库存，库存数量为0，删除。不为0更新
					///if (iStorage.getPnum()==0.0) {
						//session.delete("inventory_storage",iStorage);
					//}else {
						session.update("inventory_storage", iStorage);
					//}
				}		
			}
			session.save("OutboundInvoiceHeader",outInvoiceHeader);
			tx.commit();
			} catch (HibernateException  he) {
			 strMeg = "保存抽检单据出错！";
		     Logger.error("抽检抽检单保存出错！QualityBusImpl.addBoundAddBoundDelAddJobAddJobDel："+he.getMessage());
			}finally{
				m_dao.closeSession(session);				
			}
			return strMeg;
		}

		@Override
		public List<InboundDetail>searchInStockDel(String instockid, String productid)
				throws Exception {
			List<InboundDetail> lsDetails = null;
			try {
				StringBuilder strBud = new StringBuilder();
				strBud.append("select del.traycode From InboundDetail as del where 1=1");
				if (instockid!=null &&instockid.trim().length()>0) {
					strBud.append(" and del.instockid='").append(instockid).append("'");
				}
				if (productid!=null && productid.trim().length()>0) {
					strBud.append(" and del.productid='").append(instockid).append("'");
				}
				lsDetails = m_dao.searchEntities(strBud.toString());	
			} catch (Exception er) {
				throw new Exception("质检管理》根据入库单查询入库单明细出错:" + er.getMessage());
			}
			
			return lsDetails;
		}

		@Override
		public CommonReturn updateStatusAddRecord(String usercode,
				String warehouseid, String whareaid, String lotnumber,
				String requestid, String productid, String productstatus,
				String strwpzt) throws Exception {
			
			CommonReturn cReturn = new CommonReturn();
			try {
				InventoryQualityBus iQualityBus = new InventoryQualityBusImpl(m_dao);
				InventoryQualityResult inResult = new InventoryQualityResult();
				String strID = SeqTool.getID("FXD", m_dao);//生成id
				inResult.setM_strCheckResultId(strID); //放行记录id
				inResult.setM_strCreateDate(StrTools.getCurrDateTime(2));	//放行时间		
				inResult.setM_strOpManId(usercode);   //放行人
				inResult.setM_strLotNumber(lotnumber);//批号
				List<InventoryQualityResultDetail> lsDetails = new ArrayList<InventoryQualityResultDetail>();
				//创建新的list 和 质检结果一块提交
				List<InventoryStorage> lStorages = new ArrayList<InventoryStorage>();
				//获得库存的sql
				String strHql = "from InventoryStorage as inventoryS where 1=1";
				//String warehouseid, String whareaid, String lotnumber,String requestid, String productid, String productstatus,String strwpzt
				if(warehouseid!=null&warehouseid.trim().length()>0){
					strHql = strHql + " and inventoryS.warehouseid='" + warehouseid + "'";
				}
				
				if(whareaid!=null&whareaid.trim().length()>0){
					strHql = strHql + " and inventoryS.whAreaId='" + whareaid + "'";
				}
				
				if(lotnumber!=null&lotnumber.trim().length()>0){
					strHql = strHql + " and inventoryS.lotinfo='" + lotnumber + "'";
				}
				
				if(requestid!=null&requestid.trim().length()>0){
					strHql = strHql + " and inventoryS.requestid='" + requestid + "'";
				}
				
				if(productid!=null&productid.trim().length()>0){
					strHql = strHql + " and inventoryS.productid='" + productid + "'";
				}
				
				if(productstatus!=null&productstatus.trim().length()>0){
					strHql = strHql + " and inventoryS.productstatus='" + productstatus + "'";
				}
				
				
			    List<InventoryStorage> ls = m_dao.searchEntities(strHql);
			    InventoryStorage iStorage = null;
			    InventoryQualityResultDetail iQualityResultDetail = null;
			    double pnum = 0.0;
			    if (ls != null && ls.size()>0) {
					for (int i = 0; i < ls.size(); i++) {
						iStorage = ls.get(i);
						iStorage.setProductstatus(strwpzt);
						
						
						lStorages.add(iStorage);//增加一条记录
						iQualityResultDetail = new InventoryQualityResultDetail();
						iQualityResultDetail.setM_iProductNum(iStorage.getPnum());//数量
						iQualityResultDetail.setM_strCarspaceid(iStorage.getCargoSpaceId());//库位
						iQualityResultDetail.setM_strProductId(iStorage.getProductid());//产品id
						iQualityResultDetail.setM_strOldProductStatus(productstatus);
						iQualityResultDetail.setM_strNewProductStatus(strwpzt);
						iQualityResultDetail.setM_strPrintDate(StrTools.getCurrDateTime(2));
						iQualityResultDetail.setM_strInventoryid(iStorage.getInventoryid());
						iQualityResultDetail.setM_strCheckResultId(strID);
						iQualityResultDetail.setM_strCheckResultDetailId(SeqTool.getDetailId(strID, String.valueOf(i+1)));
						iQualityResultDetail.setM_strUnit(iStorage.getPunit());
						lsDetails.add(iQualityResultDetail);
						pnum += iStorage.getPnum();				
					}
				}
			    inResult.setM_strNum(String.valueOf(pnum));	
			    //提交到数据库
			    iQualityBus.createqualityInvoice(lsDetails, lStorages, inResult);
				cReturn.setRetInfo("放行成功！放行单为："+strID);
			}catch (Exception er) {
				Logger.error("质检管理==>状态转换:" + er.getMessage());
			}
			return cReturn;
		}
		
		public PagingTool getLsRelease(String Productid, String lotinfo, String Qualityid, String createtime, String strUrl, int maxLine) throws Exception {
				
			    PagingTool pt = null;
				String strCountHQL = "";
				String strHQL = "";
		        StringBuilder strBud = new StringBuilder(); 
		        try {
		        
			        strBud.append(" From Release as sto where 1=1");
			        
			        if(Productid != null && Productid.trim().length() > 0){
			        
			            strBud.append(" and sto.Productid='").append(Productid).append("'");
			        }
			        
			        if(lotinfo != null && lotinfo.trim().length() > 0){
			        
			            strBud.append(" and sto.lotinfo='").append(lotinfo).append("'");
			        }
			        
			        if(Qualityid != null && Qualityid.trim().length() > 0){
			        
			            strBud.append(" and sto.Qualityid='").append(Qualityid).append("'");
			        }
			        
			        if(createtime != null && createtime.trim().length()>0){
			        
			            strBud.append(" and sto.createtime like '").append(createtime).append("%' ");
			        }
					strHQL = strBud.toString();
					strCountHQL= "select count(*)"+strHQL;
					pt = CommonPagination.getPagingTool(m_dao, strCountHQL ,"select sto" + strHQL, strUrl, 1, maxLine);
					return pt;
					
		        } catch (Exception er) {
		        	 throw new Exception("质量管理->放行记录查询出错：" + er.getMessage());
				}
	
		}
		
	}
