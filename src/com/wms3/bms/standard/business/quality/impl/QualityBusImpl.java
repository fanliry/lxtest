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
	 * �������ʼ����ҵ��ӿ�ʵ����
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
	        //����
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
				throw new Exception("�ʼ����->���в�ѯ���ƴ�ӳ���" + er.getMessage());
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
	        //����
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
				throw new Exception("�ʼ����->���в�ѯ���ƴ�ӳ���" + er.getMessage());
			}
			return strBud.toString();
		}
		/**
		 * ����:�ʼ����->����ѯ����SQL
		 * @param warehouseid	�ֿ�ID
		 * @param cargoAreaId	����ID
		 * @param whAreaId		����ID
		 * @param platoon		��
		 * @param column		��
		 * @param floor			��
		 * @param productid		��Ʒid
		 * @param traycode		��������
		 * @param lotid		          ����ID
		 * @param lotatt1-lotatt12 ��������
		 * @param strUrl 		��ַ
		 * @param maxLine 		��ҳ��ʾ����
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
		        
		        //�ֿ�ID
		        if(warehouseId != null && warehouseId.trim().length() > 0){
		        
		            strBud.append(" and sto.warehouseid='").append(warehouseId).append("'");
		        }
		        
		        //����ID
		        if(whAreaId != null && whAreaId.trim().length() > 0){
		        
		            strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
		        }
		        
		        //���ID
		        if(cargoAreaId != null && cargoAreaId.trim().length()>0){
		        
		            strBud.append(" and sto.cargoAlleyId='").append(cargoAreaId).append("'");
		        }
		
		        //��
		        if(platoon != null && platoon.trim().length() >0){
		        
		            strBud.append(" and space.csplatoon=").append(Integer.parseInt(platoon));
		        }
		        
		        //��
		        if(column != null && column.trim().length() >0){
		        
		            strBud.append(" and space.cscolumn=").append(Integer.parseInt(column));
		        }
		        
		        //��
		        if(floor != null && floor.trim().length() > 0){
		        
		            strBud.append(" and space.csfloor=").append(Integer.parseInt(floor));
		        }
		        
		        //����
		        if(customerId != null && customerId.trim().length()>0){
		        
		            strBud.append(" and sto.ownerId='").append(customerId).append("'");
		        }
		        
		        //Ʒ��ID
		        if(productid != null && productid.trim().length() > 0){
		        
		            strBud.append(" and sto.productid='").append(productid).append("'");
		        }
		        
		        //��������
		        if(traycode != null && traycode.trim().length()>0){
		        
		            strBud.append(" and sto.traycode='").append(traycode).append("'");
		        }
		        
		        //�������
		        if(indate_from != null && indate_from.trim().length()>0){
		        
		            strBud.append(" and sto.indate>='").append(indate_from).append("'");
		        }
		        if(indate_to != null && indate_to.trim().length()>0){
		            
		            strBud.append(" and sto.indate<='").append(indate_to).append(" 99:99:99'");
		        }
		        
		        BaseLotDetail lotDetail = null;	//������ϸ
		
		        //��������
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
		        	 throw new Exception("���->��ȡ�����Ϣ����" + er.getMessage());
				}
				strCountHQL= "select count(*)"+strHQL;
				pt = CommonPagination.getPagingTool(m_dao, strCountHQL ,"select sto" + strHQL, strUrl, 1, maxLine);
				return pt;
	
		}
	    /**
	     * ����:������->����ѯ,��ò�ѯ������SQL,����������Ե�SQL
	     * @param flg		�ڼ�����������
	     * @param lotatt	�������Ե�ֵ
	     * @param lotDetail	��������
	     * @return
	     * @throws Exception
	     */
		private String getSqlLotatt(String flg,	String lotatt, BaseLotDetail lotDetail) {
			
	        StringBuilder strBud = new StringBuilder();	
			if(lotDetail != null){
				
				//ҳ���ѯʱ���ģʽ 1-��ȷ��ѯ,2-ģ����ѯ(�ı���ʽ),3-��Χ��ѯ(���ڸ�ʽ)
				String strLotsearch = lotDetail.getM_strLotsearch();	
			    
			    if(strLotsearch != null && strLotsearch.equals("1")){       //1-��ȷ��ѯ
	                strBud.append(" and sto.lotatt").append(flg).append(" ='").append(lotatt).append("'");   	
			    }else if(strLotsearch != null && strLotsearch.equals("2")){  //2-ģ����ѯ
	                strBud.append(" and sto.lotatt").append(flg).append(" like '%").append(lotatt).append("%'");        
			    }else if(strLotsearch != null && strLotsearch.equals("3")){  //3-��Χ��ѯ(���ڸ�ʽ)    	
		    		String[] lotatts =  lotatt.split("\\|");
			        if(lotatts.length>0 && lotatts[0] !=null && lotatts[0].trim().length() > 0){
	                    strBud.append(" and sto.lotatt").append(flg).append(" >='").append(lotatts[0]).append("'");
			        }
			        if(lotatts.length>1 && lotatts[1] !=null && lotatts[1].trim().length() > 0){
	                    strBud.append(" and sto.lotatt").append(flg).append(" <='").append(lotatts[1]).append("'");
			        }
			        
			    }else{		//û��ѡ���ѯ��ʽ��ʱ�򣬰��վ�ȷ��ѯ������
	                strBud.append(" and sto.lotatt").append(flg).append(" ='").append(lotatt).append("'");
			    }
			}
			return strBud.toString();
		}
		/**
		 * ���ܣ����ݿ��ID��ÿ��
		 * @param inventID ���iD
		 */
		public InventoryStorage getInventById(String inventId) throws Exception {
			InventoryStorage invent = null;
			
			try
			{
				invent = iDao.getInventoryById(inventId);
			}catch(Exception er)
			{
				throw new Exception("���ݿ��ID��ÿ����Ϣ����(QualityBusImpl.getInventById):" + er.getMessage());
			}
			return invent;
		}
		
		/**
	     * ���ܣ����ӳ����ⵥ
	     * @param departId ����id
	     * @param formdate ��������
	     * @param createmanid ����Ա
	     * @param customerId �ͻ�
	     * @param ownerId ����
	     * @param warehouseManId �ֹ�Ա 
	     */
		public OutboundInvoiceHeader addpOutboundInvoiceHeader(String warehouseId,String departId,
				String formdate, String createmanid, String customerId,
				String ownerId,String warehouseManId) throws Exception {
			OutboundInvoiceHeader outInvoice = new OutboundInvoiceHeader();
			try {	
			outInvoice.setWarehouseid(warehouseId);                 //�ֿ�ID
	        outInvoice.setOuttype("7");                             //��������
	        outInvoice.setDepartid(departId);                       //����id
	        outInvoice.setCreatetime(StrTools.getCurrDateTime(2));  //���ⵥ����ʱ��
	        outInvoice.setFormdate(formdate);                       //��������
	        outInvoice.setOutpos("2");                              //�����
	        outInvoice.setOutstatus("0");                           //����״̬ 0.���⿪�� 
	        outInvoice.setCreatemanid(createmanid);                 //������
	        outInvoice.setOpmanid(createmanid);                     //����Ա
	        outInvoice.setIsupload("N");                            //�Ƿ��ϴ��ɹ� Y.�� N.�� Ĭ��Ϊ��
	        outInvoice.setOwnerid(ownerId);                         //����
	        outInvoice.setCustomerid(customerId);                   //�ͻ�
	        outInvoice.setWarehousemanid(warehouseManId);           //�ֿ����Ա     
	        
	        BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(m_dao);
            BaseSeq  seqEn = seqDao.getSeqByType("CKD");
            outInvoice.setOutstockid(SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), m_dao));
			} catch (Exception er) {
				throw new Exception("���ɳ����ⵥ�ݳ���:" + er.getMessage());
			}
			return outInvoice;
		}
		/**
	     * ���ܣ����ӳ����ⵥ��ϸ������ҵ��ϸ
	     * @param inventId ���ID
	     * @param outInvoiceId ���ⵥ��ID
	     * @param outJob  ������ҵID
	     * @param Num    �������
	     * @param CheckNum  �������
	     * @param customerId �ͻ�ID
	     * @return
	     * @throws Exception
	     */
		public Object[] addOutInvoiceDelAddOutJob(String OpManId,String inventId,String outInvoiceId, String outjob, double Num,
				double CheckNum, String customerId,String i) throws Exception {	
			Object[] obj= new Object[4];
			//���ݿ��ID��ÿ��
			InventoryStorage iStorage = getInventById(inventId);
			if (iStorage!=null) {
				 OutboundInvoiceDetail outInvoiceDel = new OutboundInvoiceDetail();
				 InoutJob outJob = new InoutJob();
				 InoutJobdetail outJobDel = new InoutJobdetail();
				 try {
			
				 //�½������ⵥ��ϸ
				 outInvoiceDel.setAssignnum(CheckNum); //��������
				 outInvoiceDel.setCargoAlleyId(iStorage.getCargoAlleyId()); //���ID
				 outInvoiceDel.setCargoSpaceId(iStorage.getCargoSpaceId());//��λ
				 outInvoiceDel.setCustomid(customerId);//�ͻ�id
				 outInvoiceDel.setLinestatus("0");//���õ�����״̬
				 outInvoiceDel.setLotatt1(iStorage.getLotatt1());//��������1-����12
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
				 outInvoiceDel.setLotid(iStorage.getLotid());//����ID
				 outInvoiceDel.setM_strPackName(iStorage.getPackagename());//��װ����
				 outInvoiceDel.setM_strProductName(iStorage.getProductName());//��Ʒ����
				 outInvoiceDel.setM_strUnitName(iStorage.getPunitname());//��λ����
				 outInvoiceDel.setOutstockdetailid(SeqTool.getDetailId(outInvoiceId,i));//���ⵥ����ϸID
				 outInvoiceDel.setOutstockid(outInvoiceId);//���ⵥ��ID
				 outInvoiceDel.setPackid(iStorage.getPackid());//���õ�����״̬
				 outInvoiceDel.setPkgunit(iStorage.getPunit());//��װ��λ
				 outInvoiceDel.setProductid(iStorage.getProductid());//��װ��λ
				 outInvoiceDel.setTraycode(iStorage.getTraycode());//��������
				 outInvoiceDel.setWhAreaId(iStorage.getWhAreaId());//��������   
				 //������ҵ
				 
				 BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(m_dao);
	             BaseSeq  seqEn = seqDao.getSeqByType("CKZY"); 
				 outJob.setJobid(SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), m_dao));//��ҵ���
				 
		         outJob.setCreatetime(StrTools.getCurrDateTime(8));//  ����ʱ��
		         outJob.setInOutType("2"); //���������     
		         outJob.setIsaccount("N"); //�Ƿ����
		         outJob.setJobpos("2");    //��ҵ��
		         outJob.setJobtypeName("������");//��ҵ��������
		         outJob.setOpMan(OpManId);//����Ա
		         outJob.setOpManId(OpManId);//����ԱID
		         outJob.setStatus("0");	//��ҵ״̬
		         outJob.setOnLineType("2");
		         outJob.setJobtype("2");
		         outJob.setWarehouseid(iStorage.getWarehouseid());//�ֿ�ID
		         outJob.setPriority(10);//���ȼ�
		         outJob.setScargoAlleyId(iStorage.getCargoAlleyId());//���id
		         outJob.setScargoWhareaName(iStorage.getWhAreaName());//�������
		         outJob.setScargoSpaceId(iStorage.getCargoSpaceId());//��λid
		         outJob.setScargoSpaceName(iStorage.getCargoSpaceName());//��λ����
		         outJob.setTraycode(iStorage.getTraycode());	         //��������
		         outJob.setTcargoAlleyId(iStorage.getCargoAlleyId());//	Ŀ�����
		         outJob.setTcargoSpaceId(iStorage.getCargoSpaceId());//Ŀ���λid
		         outJob.setTcargoSpaceName(iStorage.getCargoSpaceName());//��λ����
		         outJob.setTcargoWhareaId(iStorage.getWhAreaId());//����
				 
				 //�½���������ҵ��ϸ
				 outJobDel.setAssignnum(CheckNum);//��������
				 outJobDel.setBoxcode(iStorage.getBoxcode());//����
				 outJobDel.setCustomerid(customerId);//�ͻ�ID
				 outJobDel.setInventoryid(inventId);//���ID
				
				 outJobDel.setJobdetailid(SeqTool.getDetailId(outJob.getJobid(),"9"));//��ҵ��ϸID
				 outJobDel.setJobid(outJob.getJobid());//��ҵID
				 outJobDel.setJobnum(CheckNum);//��ҵ����
				 outJobDel.setLinestatus("0");//��ҵ״̬4���
				 outJobDel.setM_strCustomerName(customerId);//�ͻ�����
				 outJobDel.setM_strOwnerName(iStorage.getOwnerName());//����
				 outJobDel.setM_strPackName(iStorage.getPackagename());//��װ����
				 outJobDel.setM_strProductName(iStorage.getPackagename());//���ò�Ʒ����
				 outJobDel.setM_strUnitName(iStorage.getPunit());//���ò�Ʒ
				 outJobDel.setOwnerId(iStorage.getOwnerId());//����ID
				 outJobDel.setProductcode(iStorage.getProductcode());//���ò�Ʒ����
				 outJobDel.setProductid(iStorage.getProductid());//������Ʒ���
				 outJobDel.setPunit(iStorage.getPunit());//������Ʒ���		 
				 
				
				 outJobDel.setLotid(iStorage.getLotid());//������������ID
				 iStorage.setStatus("1");//���ÿ��״̬ 1.�ѷ���
				 iStorage.setPnum(Num-CheckNum);//���¿������
				 obj[0]=outInvoiceDel;
				 obj[1]=outJob;
				 obj[2]=outJobDel;
				 obj[3]=iStorage;				
				} catch (Exception e) {
		    	 Logger.error("��졷���ӳ����ⵥ��ϸ����ҵ��ϸ���QualityBusImpl.addBoundAddBoundDelAddJobAddJobDel��"+e.getMessage());
				}
			}
			return obj;
		}
		/**
	     * ���ܣ����� �����ⵥ�ݣ������ⵥ��ϸ����������ҵ����������ҵ��ϸ���¿��
	     * @param outInvoiceHeader �����ⵥ
	     * @param outJob ������ҵ
	     * @param lObjects 
	     * @return
	     * @throws Exception
	     */
		public String addBoundAddBoundDelAddJobAddJobDel(OutboundInvoiceHeader outInvoiceHeader,List<Object[]> delObj)
				throws Exception {
			String strMeg = "����ɹ�";
			Session session = m_dao.getSession();
			try {			
			Transaction tx = session.beginTransaction();
			if (delObj!=null&&delObj.size()>0) {
				for (int i = 0; i < delObj.size(); i++) {
					OutboundInvoiceDetail outBoundDel = new OutboundInvoiceDetail();
					InoutJobdetail outJobDel = new InoutJobdetail();
					InoutJob outJob = new InoutJob();
					InventoryStorage iStorage = null;
					outBoundDel = (OutboundInvoiceDetail) delObj.get(i)[0];//�����ⵥ��ϸ
					outJob = (InoutJob) delObj.get(i)[1];//��������ҵ
					outJobDel = (InoutJobdetail) delObj.get(i)[2]; //�����ҵ��ϸ					
					iStorage = (InventoryStorage)delObj.get(i)[3];//���
					session.save("OutboundInvoiceDetail",outBoundDel);
					session.save("OutboundInvoiceDetail",outBoundDel);		
					session.save("InoutJob",outJob);
					
					session.save("InoutJobdetail",outJobDel);

					//���¿�棬�������Ϊ0��ɾ������Ϊ0����
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
			 strMeg = "�����쵥�ݳ���";
		     Logger.error("����쵥�������QualityBusImpl.addBoundAddBoundDelAddJobAddJobDel��"+he.getMessage());
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
				throw new Exception("�ʼ����������ⵥ��ѯ��ⵥ��ϸ����:" + er.getMessage());
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
				String strID = SeqTool.getID("FXD", m_dao);//����id
				inResult.setM_strCheckResultId(strID); //���м�¼id
				inResult.setM_strCreateDate(StrTools.getCurrDateTime(2));	//����ʱ��		
				inResult.setM_strOpManId(usercode);   //������
				inResult.setM_strLotNumber(lotnumber);//����
				List<InventoryQualityResultDetail> lsDetails = new ArrayList<InventoryQualityResultDetail>();
				//�����µ�list �� �ʼ���һ���ύ
				List<InventoryStorage> lStorages = new ArrayList<InventoryStorage>();
				//��ÿ���sql
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
						
						
						lStorages.add(iStorage);//����һ����¼
						iQualityResultDetail = new InventoryQualityResultDetail();
						iQualityResultDetail.setM_iProductNum(iStorage.getPnum());//����
						iQualityResultDetail.setM_strCarspaceid(iStorage.getCargoSpaceId());//��λ
						iQualityResultDetail.setM_strProductId(iStorage.getProductid());//��Ʒid
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
			    //�ύ�����ݿ�
			    iQualityBus.createqualityInvoice(lsDetails, lStorages, inResult);
				cReturn.setRetInfo("���гɹ������е�Ϊ��"+strID);
			}catch (Exception er) {
				Logger.error("�ʼ����==>״̬ת��:" + er.getMessage());
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
		        	 throw new Exception("��������->���м�¼��ѯ����" + er.getMessage());
				}
	
		}
		
	}
