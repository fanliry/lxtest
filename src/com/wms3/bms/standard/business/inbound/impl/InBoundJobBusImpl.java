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
 * ����: �����ҵ����ҵ����
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public class InBoundJobBusImpl implements IInBoundJobBus {
	
    /** �ջ���DAO��  */
    protected IInboundJobDao inboundJobDao;
    
    
    public InBoundJobBusImpl(EntityDAO dao) {
    	inboundJobDao = new InboundJobDaoImpl(dao);
    	
	}


	/**
	 * ����:����������ѯ�����ҵ
	 * @param warehouseid	�ֿ�
	 * @param whAreaId		����
	 * @param alleyId		���
	 * @param indate		��ҵ����
	 * @param jobid			��ҵ��
	 * @param invoicetype	��ҵ��Դ
	 * @param status		��ҵ״̬
	 * @param shiftid		���
	 * @param productid		Ʒ��
	 * @param ownerid		����
	 * @param tray_code		��������
	 * @param isback		�Ƿ����
	 * @param sortflg		�����־
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
			
			if(warehouseid != null && warehouseid.trim().length() > 0){		//�ֿ�
				strBud.append(" and job.warehouseid='").append(warehouseid).append("'");
			}
			
			if(whAreaId != null && whAreaId.trim().length() > 0){		//����
				strBud.append(" and job.tcargoWhareaId='").append(whAreaId).append("'");
			}
			
			if(alleyId != null && alleyId.trim().length() > 0){		//���
				strBud.append(" and job.tcargoAlleyId='").append(alleyId).append("'");
			}
			
			if(indate != null && indate.trim().length() > 0){		//��ҵ����
				strBud.append(" and substring(job.createtime,1,10)='").append(indate).append("'");
			}
			
			if(jobid != null && jobid.trim().length() > 0){			//��ҵ��
				strBud.append(" and job.jobid='").append(jobid).append("'");
			}
			
			if(invoicetype != null && invoicetype.trim().length() > 0){	//��ҵ��Դ
				strBud.append(" and job.invoicetype='").append(invoicetype).append("'");
			}
			
			if(status != null && status.trim().length() > 0){		//��ҵ״̬
				strBud.append(" and job.status='").append(status).append("'");
			}

			if(shiftid != null && shiftid.trim().length() > 0){		//���
				strBud.append(" and job.shiftid='").append(shiftid).append("'");
			}
			
			if(traycode != null && traycode.trim().length() > 0){		//��������
				strBud.append(" and job.traycode='").append(traycode).append("'");
			}
			
			if(isback != null && isback.equals("Y")){			//�Ƿ����( ���� 2.�����  1�ϼ����  N.��)
				strBud.append(" and job.jobpos='2' and job.isaccount='N'");
				
			}else if(isback != null && isback.equals("N")){		//�Ƿ����( ���  1.����  1�ϼ���� Y.��)
				strBud.append(" and job.jobpos!='2' and job.isaccount='Y'");
			}
			
			if(productid != null && productid.trim().length() > 0){		//Ʒ��
				strBud.append(" and job.jobid in " +
					"(select jobdetail.jobid from InoutJobdetail as jobdetail " +
					" where job.jobid=jobdetail.jobid and jobdetail.productid='").append(productid).append("')");
			}
			
			if(ownerid != null && ownerid.trim().length() > 0){		//����
				strBud.append(" and job.jobid in " +
					"(select jobdetail.jobid from InoutJobdetail as jobdetail " +
					" where job.jobid=jobdetail.jobid and jobdetail.ownerId='").append(ownerid).append("')");
			}
			
			//����ʽ
			String strHQL = strBud.toString() + " order by job.jobid";
			if(sortflg.equals("desc")){
				strHQL = strHQL + " desc";
			}
			
			String strCountHQL = "select count(*)" + strBud.toString();
			
			pt = CommonPagination.getPagingTool(inboundJobDao.getEntityDAO(), strCountHQL ,strHQL, strUrl, 1, maxLine);
			
		} catch (Exception er) {
			throw new Exception("����������ѯ��ѯ�����ҵ�б����:" + er.getMessage());
		}
				
		return pt;
	}

	/**
	 * ����:������ҵid��ѯ��ҵ��ϸ
	 * @param jobid			��ҵ��
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
			throw new Exception("������->��ѯ��ҵ��ϸ�б�ʱ�����" + er.getMessage());
		}
			
		return list;
	}

	/**
	 * ����:�����ѻ���ҵ
	 * @param warehouseid	�ֿ�
	 * @param whAreaId		����
	 * @param alleyId		���
	 * @param cargospaceid	��λID
	 * @param intype		�������
	 * @param tray_code		��������
	 * @param indate		��ҵ����
	 * @param shiftid		���
	 * @param jobdetails	��ҵ��ϸ���������÷ָ����ָ�����
	 * @param strUserCode	�û�id
	 * @return 
	 * @throws Exception
	 */
	public String addRKWHJob(String warehouseid, String whAreaId, String alleyId, String cargospaceid, String intype, String traycode, 
			String indate, String shiftid, String jobdetails, String strUserCode) throws Exception {
		
		String strBackMsg = "Y";
		
		try {
			synchronized (cargospaceid) { //��Ʒ��������ֹ���˲���ͬһ������
				
				String strTime = StrTools.getCurrDateTime(2);	//���ʱ��(��֤һ������ÿ������ʱ��һ��)
				
				//�������������
                InAllotRequestBean inRequest = new InAllotRequestBean();
                inRequest.setSpaceid(cargospaceid);	
                
				//������ҵ��Ϣ
				InoutJob job = new InoutJob();
				job.setJobtype(intype);    	//��ҵ���� 
				job.setOnLineType("2"); 	//����ģʽ 1.���� 2.�ѻ�
				job.setIsaccount("Y");  	//�Ƿ���� Y.�� N.��
				job.setStatus("4");     	//��ҵ״̬ 1.δִ�� 2.��ִ�� 3.��ִ�� 4.��� 5.���� 6.�쳣��ҵ 8.��ͣ��ҵ
				job.setPriority(10);  		//���ȼ�
				job.setCreatetime(indate);	//ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
			    job.setFinishtime(strTime); //���ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
			    job.setJobpos("1");      	//��ҵ���� 1.���� 2.�����
			    job.setInOutType("1");  	//������� 1�ϼ���� 2.����
			    job.setShiftid(shiftid);   	//��ҵ���
			    job.setWarehouseid(warehouseid);//�ֿ�ID
			    job.setTraycode(traycode);  //��������
			    job.setRoute("1");      	//ִ��·��1-ֱ��/ֱ��2-����
			    job.setOpManId(strUserCode);//������
			    job.setInvoicetype("1");	//��ҵ��Դ 1��ֱ�Ӳɼ����ɵ���ҵ��2�����ջ��������ɵ���ҵ��3������ⵥ�����ɵ���ҵ 4:��ERP�����������ɵ���ҵ

			    job.setTcargoWhareaId(whAreaId); 		//Ŀ�����ID
			    job.setTcargoSpaceId(cargospaceid);  	//Ŀ���λID
			    job.setTcargoAlleyId(alleyId);  		//Ŀ�����ID
			    
			    
			    BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(inboundJobDao.getEntityDAO());
                BaseSeq  seqEn = seqDao.getSeqByType("RKZY");//�����ҵ
                String jobid = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), inboundJobDao.getEntityDAO()); //��ҵID  
                
				job.setJobid(jobid);		//��ҵ���

				
				//������ҵ��ϸ��Ϣ
				List lsJobDetail = new ArrayList();
				List lsStorage = new ArrayList();
				
				String productid = "";		//Ʒ��
				String customerid = "";		//����
				String packid = "";			//��װ
				String punit = "";			//��λ
				double jobnum = 0;			//����
				double volume = 0;			//���
				double weight = 0;			//ë��
				double netweight = 0;		//����
				String lotatt1 = "";		//��������1
				String lotatt2 = "";		//��������2
				String lotatt3 = "";		//��������3
				String lotatt4 = "";		//��������4
				String lotatt5 = "";		//��������5
				String lotatt6 = "";		//��������6
				String lotatt7 = "";		//��������7
				String lotatt8 = "";		//��������8
				String lotatt9 = "";		//��������9
				String lotatt10 = "";		//��������10
				String lotatt11 = "";		//��������11
				String lotatt12 = "";		//��������12
				String lotid = "";			//��������ID
				
				String[] aem = jobdetails.split(",");
				String[] bem;
				
				for(int i=0; i<aem.length; i++){
					
					bem = aem[i].split("\\|");
					
					productid = bem[0];		//Ʒ��
					customerid = bem[1];	//����
					packid = bem[2];		//��װ
					punit = bem[3];			//��λ
					jobnum = Double.parseDouble(bem[4]==""?"0":bem[4]);	//����
					volume = Double.parseDouble(bem[5]==""?"0":bem[5]);	//���
					weight = Double.parseDouble(bem[6]==""?"0":bem[6]);	//ë��
					netweight = Double.parseDouble(bem[7]==""?"0":bem[7]);	//����
					lotatt1 = bem[8];		//��������1
					lotatt2 = bem[9];		//��������2
					lotatt3 = bem[10];		//��������3
					lotatt4 = bem[11];		//��������4
					lotatt5 = bem[12];		//��������5
					lotatt6 = bem[13];		//��������6
					lotatt7 = bem[14];		//��������7
					lotatt8 = bem[15];		//��������8
					lotatt9 = bem[16];		//��������9
					lotatt10 = bem[17];		//��������10
					lotatt11 = bem[18];		//��������11
					lotatt12 = bem[19];		//��������12
					lotid = bem[20];		//��������ID
				
					InoutJobdetail jobdetail = new InoutJobdetail();
	                //��ҵ��ϸID
	                String strJobDetailId = SeqTool.getDetailId(jobid, "1");
	                jobdetail.setJobdetailid(strJobDetailId);   //��ҵ��ϸID
	                jobdetail.setJobid(jobid);                  //��ҵID
	                jobdetail.setLinestatus("2");               //״̬0:�½�2���
	                jobdetail.setProductid(productid);			//Ʒ��ID
	                jobdetail.setPackid(packid); 				//��װID
	                jobdetail.setPunit(punit);   				//��λ
	                jobdetail.setLotid(lotid);   				//��������ID
	                jobdetail.setJobnum(jobnum);           		//����
	                jobdetail.setNetweight(netweight);  		//����
	                jobdetail.setWeight(weight);        		//ë��
	                jobdetail.setVolume(volume);        		//���
	                jobdetail.setIsinvoice("N");                //�Ƿ������ɵ���
	              
	                jobdetail.setOwnerId(customerid);   //����
	                lsJobDetail.add(jobdetail);
				
	                //�������������
	                InAllotRequestBean.ProductBean productBean = inRequest.getNewProductBean();
	                productBean.setProductid(jobdetail.getProductid()); //��Ʒ
                    productBean.setPackid(jobdetail.getPackid());       //��װ
                    productBean.setEunit(jobdetail.getPunit());         //��λ
                    productBean.setPutnum(jobdetail.getJobnum());          //�ϼ�����
                    productBean.setPutweight(jobdetail.getWeight());       //�ϼ�����
                    productBean.setPutnetweight(jobdetail.getNetweight()); //�ϼܾ���
                    productBean.setPutvolume(jobdetail.getVolume());       //�ϼ���� 
                    productBean.setLotid(jobdetail.getLotid());		//��������ID
                   
                    productBean.setOwnerid(jobdetail.getOwnerId());	//����  
                    inRequest.addProductBean(productBean);    
                    
                    //���ӿ����Ϣ
                    InventoryStorage inventoryStorage = new InventoryStorage();
                  
                    inventoryStorage.setCargoSpaceId(job.getTcargoSpaceId()); //��λID
                    inventoryStorage.setCargoAlleyId(job.getTcargoAlleyId()); //���ID
                    //inventoryStorage.setCargoAreaId(space.getCargoAreaId());//����ID
                    inventoryStorage.setWhAreaId(job.getTcargoWhareaId()); //����
                    inventoryStorage.setWarehouseid(job.getWarehouseid()); //�ֿ�ID
                    
                    inventoryStorage.setOwnerId(jobdetail.getOwnerId());    //����ID
                    inventoryStorage.setProductid(jobdetail.getProductid());//��ƷID
                    inventoryStorage.setPackid(jobdetail.getPackid());      //��װID
                    inventoryStorage.setPunit(jobdetail.getPunit());        //������λ
                    inventoryStorage.setIndate(strTime);				//���ʱ��
                    inventoryStorage.setLotid(jobdetail.getLotid());	//����ID
                    
                    inventoryStorage.setIntype("2");                	//��ⷽʽ 1.���� 2.�ѻ� 
                    inventoryStorage.setPnum(jobdetail.getJobnum());         //�������
                    inventoryStorage.setPvolume(jobdetail.getVolume());      //������
                    inventoryStorage.setPweight(jobdetail.getWeight());      //�������
                    inventoryStorage.setPnetweight(jobdetail.getNetweight());//��澻��
                    
                    inventoryStorage.setInjobid(jobdetail.getJobid());              //��ҵID
                    inventoryStorage.setInjobetailid(jobdetail.getJobdetailid());   //��ҵ��ϸID
                    
                    inventoryStorage.setTraycode(job.getTraycode());        //��������
                   
                    inventoryStorage.setReserve3(jobdetail.getReserve3());  //Ԥ��1
                    inventoryStorage.setReserve4(jobdetail.getReserve4());  //Ԥ��1
                   
                    lsStorage.add(inventoryStorage);
                }     
				
				// //��֤�»�λ********************************************************
				//TODO
                BaseCargospace cargospace = null;//Ϊ�����ʾʧ�ܣ�Ϊ��λ�������ʾ�ɹ�
                if(cargospace != null){  //��֤�ɹ�
                	
                	cargospace.setCsstatus("2");	// ��λ״̬ 1���ջ�λ��2������λ��3�������䣻4��������䣻5��������̵㣻6���������̵㣻7������ȡ����8���ѳ���
                    
                    //���浽���ݿ�,������ҵ����ҵ��ϸ��Ϣ���������,�޸Ļ�λ״̬
                    inboundJobDao.addRKWHJob(cargospace, job, lsJobDetail, lsStorage); 
                    Logger.info("�û���" + strUserCode + "���������ѻ���ҵ��" + jobid);
                    
                }else{//��֤ʧ��
                	strBackMsg = "ָ����λ["+ cargospaceid +"]��֤ʧ�ܣ��û�λ�޷��ϼ�����ӵ���ҵ��";
                }   
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			strBackMsg = "�����ѻ���ҵʱ��������";
			return strBackMsg;
		}
		
		return strBackMsg;
	}

	/**
	 * ����:������ҵid��ѯ��ҵ
	 * @param jobid			��ҵ��
	 * @return 
	 * @throws Exception
	 */
	public InoutJob getJobByJobid(String jobid) throws Exception {
		
		return inboundJobDao.getJobByJobid(jobid);
	}


	/**
	 * ����:�޸���ҵ�����ά����
	 * @param jobid			��ҵID
	 * @param jobtype		��ҵ����
	 * @param traycode		��������
	 * @return 
	 * @throws Exception
	 */
	public String updateRKWHJob(String jobid, String jobtype, String traycode) throws Exception {
		
		String strBackMsg = "Y";
		
		try {
			
			InoutJob job = inboundJobDao.getJobByJobid(jobid);
			if(job != null){
				
				//�ж���ҵ��Դ 1��ֱ�Ӳɼ����ɵ���ҵ��2�����ջ��������ɵ���ҵ��3������ⵥ�����ɵ���ҵ 4:��ERP�����������ɵ���ҵ 12:�˹����ɵ���ҵ
				if(job.getInvoicetype().startsWith("1") ){	
					
					//�ж���ҵ�Ƿ������ɵ�����
					List ls = getJobDetails(jobid);
					if(ls != null && ls.size() > 0){
						
						InoutJobdetail jobdetail = (InoutJobdetail)ls.get(0);
						if(jobdetail.getIsinvoice().equals("Y")){
							
							strBackMsg = "��ҵ:" + jobid + "�����ɵ��ݣ��޷��޸���ҵ��";
						}else{
							
							//�ж���ҵ״̬ 1.δִ�� 2.��ִ�� 3.��ִ�� 4.��� 5.���� 6.�쳣��ҵ 8.��ͣ��ҵ
							if(!job.getStatus().equals("4")){
								job.setTraycode(traycode);	//����ɵ�ֻ�ܸ���ҵ����,���ܸ���ϸ(���漰����޸�)
							}
							job.setJobtype(jobtype);
							inboundJobDao.updateJob(job);
						}
					}
				}else{
					
					strBackMsg = " ֻ���޸���Դ�ǡ�ֱ�Ӳɼ����ɵ���ҵ�����ߡ��˹����ɵ���ҵ������ҵ��";
				}
			}else{
				
				strBackMsg = "��ҵ�����ڣ�";
			}
		}catch (Exception e) {
		
			e.printStackTrace();
			strBackMsg = "�޸���ҵʱ��������";
			return strBackMsg;
		}
		
		return strBackMsg;
	}

	/**
	 * ����:������ҵ��ϸ�Ų�ѯ��ҵ��ϸ
	 * @param jobdetailid			��ҵ��ϸ��
	 * @return 
	 * @throws Exception
	 */
	public InoutJobdetail getJobDetailBydetailid(String jobdetailid) throws Exception {
		
		return inboundJobDao.getJobDetailBydetailid(jobdetailid);
	}

	/**
	 * ����:�޸���ҵ��ϸ�����ά����
	 * @param jobdetailid	��ҵ��ϸ��
	 * @param key			��ҵ��ϸ����
	 * @return 
	 * @throws Exception
	 */
	public String updateRKWHJobDetail(String jobdetailid, String key) throws Exception {
		
		String strBackMsg = "Y";
		
		try {
			InoutJobdetail jobdetail = getJobDetailBydetailid(jobdetailid);
			InoutJob job = getJobByJobid(jobdetail.getJobid());
			
			if(jobdetail != null){
				
				//�ж���ҵ��Դ 1��ֱ�Ӳɼ����ɵ���ҵ��2�����ջ��������ɵ���ҵ��3������ⵥ�����ɵ���ҵ 4:��ERP�����������ɵ���ҵ 12:�˹����ɵ���ҵ
				if(job.getInvoicetype().startsWith("1") ){	
					
					//�ж���ҵ�Ƿ������ɵ�����
					if(jobdetail.getIsinvoice().equals("Y")){
							
						strBackMsg = "����ҵ��ϸ�����ɵ��ݣ��޷��޸ģ�";
					}else{
						
						//�ж�״̬ 0:�½� 2:���
						if(jobdetail.getLinestatus().equals("2")){
							
							strBackMsg = "����ҵ��ϸ�Ѿ���ɣ��޷��޸ģ�";
						}else{
						
							String[] bem = key.split("\\|");
							String productid = bem[0];		//Ʒ��
							String customerid = bem[1];		//����
							String packid = bem[2];			//��װ
							String punit = bem[3];			//��λ
							double jobnum = Double.parseDouble(bem[4]==""?"0":bem[4]);		//����
							double volume = Double.parseDouble(bem[5]==""?"0":bem[5]);		//���
							double weight = Double.parseDouble(bem[6]==""?"0":bem[6]);		//ë��
							double netweight = Double.parseDouble(bem[7]==""?"0":bem[7]);	//����
							String lotatt1 = bem[8];		//��������1
							String lotatt2 = bem[9];		//��������2
							String lotatt3 = bem[10];		//��������3
							String lotatt4 = bem[11];		//��������4
							String lotatt5 = bem[12];		//��������5
							String lotatt6 = bem[13];		//��������6
							String lotatt7 = bem[14];		//��������7
							String lotatt8 = bem[15];		//��������8
							String lotatt9 = bem[16];		//��������9
							String lotatt10 = bem[17];		//��������10
							String lotatt11 = bem[18];		//��������11
							String lotatt12 = bem[19];		//��������12
							String lotid = bem[20];			//��������ID
							
			                jobdetail.setProductid(productid);	//Ʒ��ID
			                jobdetail.setPackid(packid); 		//��װID
			                jobdetail.setPunit(punit);   		//��λ
			                jobdetail.setLotid(lotid);   		//��������ID
			                jobdetail.setJobnum(jobnum);        //����
			                jobdetail.setNetweight(netweight);  //����
			                jobdetail.setWeight(weight);        //ë��
			                jobdetail.setVolume(volume);        //���
			                
			                jobdetail.setOwnerId(customerid);   //����
							
							inboundJobDao.updateJobDetail(jobdetail);
						}
					}
				}else{
					
					strBackMsg = " ֻ���޸���Դ�ǡ�ֱ�Ӳɼ����ɵ���ҵ�����ߡ��˹����ɵ���ҵ������ҵ��";
				}
			}else{
				
				strBackMsg = "��ҵ��ϸ�����ڣ�";
			}
		}catch (Exception e) {
		
			e.printStackTrace();
			strBackMsg = "�޸���ҵ��ϸʱ��������";
			return strBackMsg;
		}
		
		return strBackMsg;
	}

	/**
	 * ����:������ҵ�����ά����
	 * @param jobid			��ҵID
	 * @return 
	 * @throws Exception
	 */
	public String cancelRKWHJob(String jobid) throws Exception {
		
		String strBackMsg = "Y";
		
		try {
			InoutJob job = inboundJobDao.getJobByJobid(jobid);
			if(job != null){
				
				//��ȡ��ҵ��Ŀ���λ
				IBaseCargoSpaceDao baseCargoSpaceDAO = new BaseCargoSpaceDaoImpl(inboundJobDao.getEntityDAO());
				BaseCargospace cargospace = baseCargoSpaceDAO.getCargoSpaceById(job.getTcargoSpaceId());
				
				//�ж���ҵ��Դ 1��ֱ�Ӳɼ����ɵ���ҵ��2�����ջ��������ɵ���ҵ��3������ⵥ�����ɵ���ҵ 4:��ERP�����������ɵ���ҵ 12:�˹����ɵ���ҵ
				if(job.getInvoicetype().startsWith("1") ){	
				
					if(job.getInvoicetype().equals("1")){	//��ҵ��Դ 1��ֱ�Ӳɼ����ɵ���ҵ
								
						//�ж���ҵ״̬ 1.δִ�� 2.��ִ�� 3.��ִ�� 4.��� 5.���� 6.�쳣��ҵ 8.��ͣ��ҵ
						if(job.getStatus().equals("1") || job.getStatus().equals("2")){
							
							job.setStatus("5");
							cargospace.setCsstatus("1");
							//���浽���ݿ�,������ҵ��ɾ�����,�޸Ļ�λ״̬Ϊ�ջ�λ
							inboundJobDao.cancelJob(job, cargospace, null); 
							
						}else{
							strBackMsg = "ֻ������״̬��δִ�л��ߴ�ִ�е���ҵ��";
						}
					}
					
					if(job.getInvoicetype().equals("12")){	//��ҵ��Դ 12:�˹����ɵ���ҵ
						
						IInventoryDao inventoryDAO = new InventoryDaoImpl(inboundJobDao.getEntityDAO());
						
						// �жϻ�λ״̬ 1���ջ�λ��2������λ��3�������䣻4��������䣻5��������̵㣻6���������̵㣻7������ȡ����8���ѳ���
						if(cargospace.getCsstatus().equals("2")){
							
							List lsStorage = inventoryDAO.getInventoryByCsId(job.getTcargoSpaceId());	//����б�
							
							job.setStatus("5");
							cargospace.setCsstatus("1");
							//���浽���ݿ�,������ҵ��ɾ�����,�޸Ļ�λ״̬Ϊ�ջ�λ
							inboundJobDao.cancelJob(job, cargospace, lsStorage); 
							
						}else{
							strBackMsg = "��ҵ:" + jobid + "��Ӧ�Ļ�λ״̬��������λ���û�λ�����Ѿ������䣬�޷�������ҵ��";
						}
					}
				}else{
					strBackMsg = "ֻ��������Դ�ǡ�ֱ�Ӳɼ����ɵ���ҵ�����ߡ��˹����ɵ���ҵ������ҵ��";
				}
				
			}else{
				strBackMsg = "��ҵ�����ڣ�";
			}
			
		}catch (Exception e) {
		
			e.printStackTrace();
			strBackMsg = "������ҵ�����ά����ʱ��������";
			return strBackMsg;
		}
		
		return strBackMsg;
	}

	/**
	 * ����:����������ѯ�����ҵ��ϸ
	 * @param warehouseid	�ֿ�
	 * @param whAreaId		����
	 * @param alleyId		���
	 * @param cargospaceid	��λ
	 * @param onlinetype	���ģʽ
	 * @param indate_from	��ҵ����
	 * @param indate_to		��ҵ����
	 * @param shiftid		���
	 * @param isback		�Ƿ����
	 * @param invoicetype	��ҵ��Դ
	 * @param productid		Ʒ��
	 * @param ownerid		����
	 * @param tray_code		��������
	 * @param lotid			����id
     * @param lotatt1, lotatt2, lotatt3, lotatt4, lotatt5, lotatt6      ��������
     * @param lotatt7, lotatt8, lotatt9, lotatt10, lotatt11, lotatt12   ��������
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
			
			if(warehouseid != null && warehouseid.trim().length() > 0){		//�ֿ�
				strBud.append(" and job.warehouseid='").append(warehouseid).append("'");
			}
			
			if(whAreaId != null && whAreaId.trim().length() > 0){		//����
				strBud.append(" and job.tcargoWhareaId='").append(whAreaId).append("'");
			}
			
			if(alleyId != null && alleyId.trim().length() > 0){		//���
				strBud.append(" and job.tcargoAlleyId='").append(alleyId).append("'");
			}
			
	        if(cargospaceid != null && cargospaceid.trim().length() >0){	//��λ
	            strBud.append(" and job.tcargoSpaceId='").append(cargospaceid).append("'");
	        }
	        
	        if(onlinetype != null && onlinetype.trim().length() > 0){			//���ģʽ
				strBud.append(" and job.onLineType='").append(onlinetype).append("'");
			}
			
			if(indate_from != null && indate_from.trim().length() > 0){		//��ҵ����
				strBud.append(" and job.createtime>='").append(indate_from).append("'");
			}
			if(indate_to != null && indate_to.trim().length() > 0){		//��ҵ����
				strBud.append(" and job.createtime<='").append(indate_to).append(" 99:99:99'");
			}
			
			if(shiftid != null && shiftid.trim().length() > 0){		//���
				strBud.append(" and job.shiftid='").append(shiftid).append("'");
			}
			
			if(isback != null && isback.equals("Y")){			//�Ƿ����( ���� 2.�����  1�ϼ����  N.��)
				strBud.append(" and job.jobpos='2' and job.isaccount='N'");
				
			}else if(isback != null && isback.equals("N")){		//�Ƿ����( ���  1.����  1�ϼ���� Y.��)
				strBud.append(" and job.jobpos!='2' and job.isaccount='Y'");
			}
			
			if(invoicetype != null && invoicetype.trim().length() > 0){	//��ҵ��Դ
				strBud.append(" and job.invoicetype='").append(invoicetype).append("'");
			}

			if(productid != null && productid.trim().length() > 0){		//Ʒ��
				strBud.append(" and jobdetail.productid='").append(productid).append("'");
			}
			
			if(ownerid != null && ownerid.trim().length() > 0){		//����
				strBud.append(" and jobdetail.ownerId='").append(ownerid).append("'");
			}
			
			if(traycode != null && traycode.trim().length() > 0){		//��������
				strBud.append(" and job.traycode='").append(traycode).append("'");
			}
			
			//��������ID�������
			ILotBus lotBus = new LotBusImpl(inboundJobDao.getEntityDAO());
            HashMap<String, BaseLotDetail> hsLot = lotBus.getHashMapByLotId(lotid);
            
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
			
			String strHQL = strBud.toString() + " order by job.jobid";
			String strCountHQL = "select count(*)" + strBud.toString();
			
			pt = CommonPagination.getPagingTool(inboundJobDao.getEntityDAO(), strCountHQL ,strHQL, strUrl, 1, maxLine);
			
		} catch (Exception er) {
			throw new Exception("����������ѯ��ѯ�����ҵ�б����:" + er.getMessage());
		}
				
		return pt;
	}
/**
 * �����ˮ��ҵ��ѯ
 * @param warehouseid�ֿ�
 * @param whAreaId   ����
 * @param alleyId    �����
 * @param indate_from��ʼ����
 * @param indate_to  ��������
 * @param invoicetype��ҵ����
 * @param productid  ��Ʒid
 * @param ownerid    ����
 * @param traycode   ��������
 * @param lotid      ����id
 * @param lotatt1    ����1
 * @param lotatt2    ����2
 * @param lotatt3    ����3
 * @param lotatt4    ����4
 * @param lotatt5    ����5
 * @param lotatt6    ����6
 * @param lotatt7    ����7
 * @param lotatt8    ����8
 * @param lotatt9    ����9
 * @param lotatt10   ����10
 * @param lotatt11   ����10
 * @param lotatt12   ����12
 * @param strUrl     ת���ַ
 * @param maxLine    �����ʾ����
 * @param invoiceid  ���ݺ�
 * @param groupinfo  ������Ϣ
 * @param customer_id�ͻ�
 * @param intype     ��������
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
			
			if(groupinfo != null && groupinfo.trim().length()>0){ //��ȡ�ַ��ֶ�
				
				String aem[] = groupinfo.split(",");
				String bem[] = aem[0].split("\\|");// ����id
				
				for(int x=0;x<bem.length;x++){//����
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
			}else{ // �޷���ʱ����ʾ����������Ʒ�������ڣ��ݻ������ȡ�
				head = " select jobdetail.productcode, jobdetail.m_strProductName,jobdetail.lotinfo,jobdetail.printdate,job.boundstockid,job.jobtype,substring(job.createtime,1,10),sum(jobdetail.jobnum),sum(jobdetail.volume),sum(jobdetail.weight),sum(jobdetail.netweight) ";
			}
			
			strBud.append(" from InoutJob as job, InoutJobdetail as jobdetail where job.jobid=jobdetail.jobid and job.inOutType='1' and job.status='4' ");
			
			if(warehouseid != null && warehouseid.trim().length() > 0){		//�ֿ�
				strBud.append(" and job.warehouseid='").append(warehouseid).append("'");
			}
			
			if(whAreaId != null && whAreaId.trim().length() > 0){		//����
				strBud.append(" and job.tcargoWhareaId='").append(whAreaId).append("'");
			}
			
			if(alleyId != null && alleyId.trim().length() > 0){		//���
				strBud.append(" and job.tcargoAlleyId='").append(alleyId).append("'");
			}
			
			if(indate_from != null && indate_from.trim().length() > 0){		//��ҵ����
				strBud.append(" and job.createtime>='").append(indate_from).append("'");
			}
			if(indate_to != null && indate_to.trim().length() > 0){		//��ҵ����
				strBud.append(" and job.createtime<='").append(indate_to).append(" 99:99:99'");
			}
			
			if(invoicetype != null && invoicetype.trim().length() > 0){	//��ҵ����
				strBud.append(" and job.invoicetype='").append(invoicetype).append("'");
			}

			if(productid != null && productid.trim().length() > 0){		//Ʒ��
				strBud.append(" and jobdetail.productid='").append(productid).append("'");
			}
			
			if(ownerid != null && ownerid.trim().length() > 0){		//����
				strBud.append(" and jobdetail.ownerId='").append(ownerid).append("'");
			}
			
			if(traycode != null && traycode.trim().length() > 0){		//��������
				strBud.append(" and job.traycode='").append(traycode).append("'");
			}
			
			if(customer_id != null && customer_id.trim().length() > 0){	//�ͻ�
				strBud.append(" and jobdetail.customerid='").append(customer_id).append("'");
			}
			
			if(boundstockid != null && boundstockid.trim().length() > 0){	//����
				strBud.append(" and job.boundstockid='").append(boundstockid).append("'");
			}
			
			if(intype != null && intype.trim().length() > 0){	//��ҵ����
				strBud.append(" and job.jobtype='").append(intype).append("'");
			}
			
	      
			// --customerid|ownerId,1|2,�ͻ�|����--
	        String strHQL = "";
	        if(groupinfo != null && groupinfo.trim().length()>0){ //��������
	        	String aem[] = groupinfo.split(",");
				String bem[] = aem[0].split("\\|");// ����id
				strBud.append(" group by ");
				for(int x=0;x<bem.length;x++){//����
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
				for(int x=0;x<bem.length;x++){//����
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
			throw new Exception("����������ѯ��ѯ�����ˮ�б����:" + er.getMessage());
		}
				
		return pt;
	}
	/**
	 * ������ˮ��ҵ��ѯ
	 * @param warehouseid�ֿ�
	 * @param whAreaId   ����
	 * @param alleyId    �����
	 * @param indate_from��ʼ����
	 * @param indate_to  ��������
	 * @param invoicetype��ҵ����
	 * @param productid  ��Ʒid
	 * @param ownerid    ����
	 * @param traycode   ��������
	 * @param lotid      ����id
	 * @param lotatt1    ����1
	 * @param lotatt2    ����2
	 * @param lotatt3    ����3
	 * @param lotatt4    ����4
	 * @param lotatt5    ����5
	 * @param lotatt6    ����6
	 * @param lotatt7    ����7
	 * @param lotatt8    ����8
	 * @param lotatt9    ����9
	 * @param lotatt10   ����10
	 * @param lotatt11   ����10
	 * @param lotatt12   ����12
	 * @param strUrl     ת���ַ
	 * @param maxLine    �����ʾ����
	 * @param invoiceid  ���ݺ�
	 * @param groupinfo  ������Ϣ
	 * @param customer_id�ͻ�
	 * @param intype     ��������
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
				
				if(groupinfo != null && groupinfo.trim().length()>0){ //��ȡ�ַ��ֶ�
					
					String aem[] = groupinfo.split(",");
					String bem[] = aem[0].split("\\|");// ����id
					
					for(int x=0;x<bem.length;x++){//����
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
				}else{ // �޷���ʱ����ʾ����������Ʒ�������ڣ��ݻ������ȡ�
					head = " select jobdetail.productcode, jobdetail.m_strProductName,jobdetail.lotinfo,jobdetail.printdate,jobdetail.m_strCustomerName,job.boundstockid,job.jobtype,substring(job.createtime,1,10),sum(jobdetail.jobnum),sum(jobdetail.volume),sum(jobdetail.weight),sum(jobdetail.netweight) ";
				}
				
				strBud.append(" from InoutJob as job, InoutJobdetail as jobdetail where job.jobid=jobdetail.jobid and job.inOutType='2' and job.status='4' ");
				
				if(warehouseid != null && warehouseid.trim().length() > 0){		//�ֿ�
					strBud.append(" and job.warehouseid='").append(warehouseid).append("'");
				}
				
				if(whAreaId != null && whAreaId.trim().length() > 0){		//����
					strBud.append(" and job.tcargoWhareaId='").append(whAreaId).append("'");
				}
				
				if(alleyId != null && alleyId.trim().length() > 0){		//���
					strBud.append(" and job.tcargoAlleyId='").append(alleyId).append("'");
				}
				
				
				if(indate_from != null && indate_from.trim().length() > 0){		//��ҵ����
					strBud.append(" and job.createtime>='").append(indate_from).append("'");
				}
				if(indate_to != null && indate_to.trim().length() > 0){		//��ҵ����
					strBud.append(" and job.createtime<='").append(indate_to).append(" 99:99:99'");
				}
				
				if(invoicetype != null && invoicetype.trim().length() > 0){	//��ҵ����
					strBud.append(" and job.invoicetype='").append(invoicetype).append("'");
				}

				if(productid != null && productid.trim().length() > 0){		//Ʒ��
					strBud.append(" and jobdetail.productid='").append(productid).append("'");
				}
				
				if(ownerid != null && ownerid.trim().length() > 0){		//����
					strBud.append(" and jobdetail.ownerId='").append(ownerid).append("'");
				}
				
				if(traycode != null && traycode.trim().length() > 0){		//��������
					strBud.append(" and job.traycode='").append(traycode).append("'");
				}
				
				if(customer_id != null && customer_id.trim().length() > 0){	//�ͻ�
					strBud.append(" and jobdetail.customerid='").append(customer_id).append("'");
				}
				
				if(boundstockid != null && boundstockid.trim().length() > 0){	//����
					strBud.append(" and job.boundstockid='").append(boundstockid).append("'");
				}
				
				if(outtype != null && outtype.trim().length() > 0){	//��ҵ����
					strBud.append(" and job.outtype='").append(outtype).append("'");
				}
				
				
				
				

		        
				// --customerid|ownerId,1|2,�ͻ�|����--
		        String strHQL = "";
		        if(groupinfo != null && groupinfo.trim().length()>0){ //��������
		        	String aem[] = groupinfo.split(",");
					String bem[] = aem[0].split("\\|");// ����id
					strBud.append(" group by ");
					for(int x=0;x<bem.length;x++){//����
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
					for(int x=0;x<bem.length;x++){//����
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
				throw new Exception("����������ѯ��ѯ�����ˮ�б����:" + er.getMessage());
			}
					
			return pt;
		}
    /**
     * ����:����������Ե�SQL
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
                strBud.append(" and jobdetail.lotatt").append(flg).append(" ='").append(lotatt).append("'");   	
		    }else if(strLotsearch != null && strLotsearch.equals("2")){  //2-ģ����ѯ
                strBud.append(" and jobdetail.lotatt").append(flg).append(" like '%").append(lotatt).append("%'");        
		    }else if(strLotsearch != null && strLotsearch.equals("3")){  //3-��Χ��ѯ(���ڸ�ʽ)    	
	    		String[] lotatts =  lotatt.split("\\|");
		        if(lotatts.length>0 && lotatts[0] !=null && lotatts[0].trim().length() > 0){
                    strBud.append(" and jobdetail.lotatt").append(flg).append(" >='").append(lotatts[0]).append("'");
		        }
		        if(lotatts.length>1 && lotatts[1] !=null && lotatts[1].trim().length() > 0){
                    strBud.append(" and jobdetail.lotatt").append(flg).append(" <='").append(lotatts[1]).append("'");
		        }
		        
		    }else{		//û��ѡ���ѯ��ʽ��ʱ�򣬰��վ�ȷ��ѯ������
                strBud.append(" and jobdetail.lotatt").append(flg).append(" ='").append(lotatt).append("'");
		    }
		}
		return strBud.toString();
	}

	/**
	 * ����:������ҵ����ҵ����
	 * @param jobids		��ҵID(�ɸ�����)
	 * @param strUserCode	�û�
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
					
					//�ж���ҵ��Դ 1��ֱ�Ӳɼ����ɵ���ҵ��2�����ջ��������ɵ���ҵ��3������ⵥ�����ɵ���ҵ 4:��ERP�����������ɵ���ҵ 12:�˹����ɵ���ҵ
					if(job.getInvoicetype().endsWith("1") ){	
									
						//�ж���ҵ״̬ 1.δִ�� 2.��ִ�� 3.��ִ�� 4.��� 5.���� 6.�쳣��ҵ 8.��ͣ��ҵ
						if(job.getStatus().equals("4") || job.getStatus().equals("5")){
							
							if(strBackMsg.equals("Y")){
								strBackMsg = "���������Ѿ���ɻ����Ѿ����ϵ�����ҵ��";
								
				 			}else {
				 				strBackMsg += "\r���������Ѿ���ɻ����Ѿ����ϵ�����ҵ��";
				 			}
							
						}else{
							
							//��ȡ��ҵ��Ŀ���λ
							cargospace = baseCargoSpaceDAO.getCargoSpaceById(job.getTcargoSpaceId());
							
							job.setStatus("5");
							cargospace.setCsstatus("1");
							//���浽���ݿ�,������ҵ��ɾ�����,�޸Ļ�λ״̬Ϊ�ջ�λ
							inboundJobDao.cancelJob(job, cargospace, null); 
							Logger.info("�û���" + strUserCode + "������ҵ����ģ���������������ҵ��" + jobid[i]);
						}
					}else{
						
						if(strBackMsg.equals("Y")){
							strBackMsg = "ֻ��������Դ�ǡ�ֱ�Ӳɼ����ɵ���ҵ������ҵ��";
							
			 			}else {
			 				strBackMsg += "\rֻ��������Դ�ǡ�ֱ�Ӳɼ����ɵ���ҵ������ҵ��";
			 			}
					}
					
				}else{
					
					if(strBackMsg.equals("Y")){
						strBackMsg = "��ҵ�����ڣ�";
						
		 			}else {
		 				strBackMsg += "\r��ҵ�����ڣ�";
		 			}
				}
			}
		}catch (Exception e) {
		
			e.printStackTrace();
			strBackMsg = "������ҵ����ҵ����ʱ��������";
			return strBackMsg;
		}
		
		return strBackMsg;
	}

	/**
	 * ����:�ֶ������ҵ����ҵ����
	 * @param jobids		��ҵID(�ɸ�����)
	 * @param strUserCode	�û�
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
					
					//�ж���ҵ��Դ 1��ֱ�Ӳɼ����ɵ���ҵ��2�����ջ��������ɵ���ҵ��3������ⵥ�����ɵ���ҵ 4:��ERP�����������ɵ���ҵ 12:�˹����ɵ���ҵ
					    //if(job.getInvoicetype().endsWith("1") ){
									
						//�ж���ҵ״̬ 1.δִ�� 2.��ִ�� 3.��ִ�� 4.��� 5.���� 6.�쳣��ҵ 8.��ͣ��ҵ
						if(job.getStatus().equals("4") || job.getStatus().equals("5")){
							
							if(strBackMsg.equals("Y")){
								strBackMsg = "�����ֶ�����Ѿ���ɻ����Ѿ����ϵ�����ҵ��";
								
				 			}else {
				 				strBackMsg += "\r�����ֶ�����Ѿ���ɻ����Ѿ����ϵ�����ҵ��";
				 			}
							
						}else{
							
							String strTime = StrTools.getCurrDateTime(2);	//���ʱ��(��֤һ������ÿ������ʱ��һ��)
							
							//��ȡ��ҵ��Ŀ���λ 
							cargospace = baseCargoSpaceDAO.getCargoSpaceById(job.getTcargoSpaceId());
							if(cargospace != null){
							
								job.setStatus("4");
								job.setFinishtime(strTime);
								job.setOnLineType("2");		//�ѻ�
								
								cargospace.setCsstatus("2");//��λ״̬ 1���ջ�λ��2������λ��3�������䣻4��������䣻5��������̵㣻6���������̵㣻7������ȡ����8���ѳ���
								
								List lsjobdetail = getJobDetails(jobid[i]);		//��ҵ��ϸ�б�
								List lsStorage = new ArrayList();							//����б�
								InventoryStorage inventoryStorage = null;
								InoutJobdetail jobdetail = null;
								if(lsjobdetail!=null && lsjobdetail.size()>0){
									
									jobdetail = (InoutJobdetail)lsjobdetail.get(i);
									//���ӿ����Ϣ
				                    inventoryStorage = new InventoryStorage();
				                    
				                    inventoryStorage.setCargoSpaceId(job.getTcargoSpaceId()); 	//��λID
				                    inventoryStorage.setCargoAlleyId(job.getTcargoAlleyId()); 	//���ID
				                    //inventoryStorage.setCargoAreaId(space.getCargoAreaId());	//����ID
				                    inventoryStorage.setWhAreaId(job.getTcargoWhareaId());  	//����
				                    inventoryStorage.setWarehouseid(job.getWarehouseid());  	//�ֿ�ID
				                    
				                    inventoryStorage.setOwnerId(jobdetail.getOwnerId());     	//����ID
				                    inventoryStorage.setProductid(jobdetail.getProductid()); 	//��ƷID
				                    inventoryStorage.setPackid(jobdetail.getPackid());       	//��װID
				                    inventoryStorage.setPunit(jobdetail.getPunit());         	//������λ
				                    inventoryStorage.setIndate(strTime);					 	//���ʱ��
				                    inventoryStorage.setLotid(jobdetail.getLotid());		 	//����ID
				                    
				                    inventoryStorage.setIntype("2");                		 	//��ⷽʽ 1.���� 2.�ѻ� 
				                    inventoryStorage.setPnum(jobdetail.getJobnum());         	//�������
				                    inventoryStorage.setPvolume(jobdetail.getVolume());      	//������
				                    inventoryStorage.setPweight(jobdetail.getWeight());      	//�������
				                    inventoryStorage.setPnetweight(jobdetail.getNetweight());	//��澻��
				                    
				                    inventoryStorage.setInjobid(jobdetail.getJobid());              //��ҵID
				                    inventoryStorage.setInjobetailid(jobdetail.getJobdetailid());   //��ҵ��ϸID
				                    
				                    inventoryStorage.setTraycode(job.getTraycode());        //��������
				                    
				                    inventoryStorage.setReserve3(jobdetail.getReserve3());  //Ԥ��1
				                    inventoryStorage.setReserve4(jobdetail.getReserve4());  //Ԥ��1
				                   
				                    lsStorage.add(inventoryStorage);
								}
								
								//���
								
								//���浽���ݿ�,�ֶ������ҵ����ӿ��,�޸Ļ�λ״̬���ջ�λ
								inboundJobDao.finishJob(job, cargospace, lsjobdetail, lsStorage); 
								Logger.info("�û���" + strUserCode + "������ҵ����ģ�����ֶ�����������ҵ��" + jobid[i]);
								
							}else{
								if(strBackMsg.equals("Y")){
									strBackMsg = "��ҵ��" + jobid[i] + "���ֶ����ʧ�ܣ�������λ��";
									
					 			}else {
					 				strBackMsg += "\r��ҵ��" + jobid[i] + "���ֶ����ʧ�ܣ�������λ��";
					 			}
							}
						}
					/*}else{
						
						if(strBackMsg.equals("Y")){
							strBackMsg = "ֻ���ֶ������Դ�ǡ�ֱ�Ӳɼ����ɵ���ҵ������ҵ��";
							
			 			}else {
			 				strBackMsg += "\rֻ���ֶ������Դ�ǡ�ֱ�Ӳɼ����ɵ���ҵ������ҵ��";
			 			}
					}*/
					
				}else{
					
					if(strBackMsg.equals("Y")){
						strBackMsg = "��ҵ�����ڣ�";
						
		 			}else {
		 				strBackMsg += "\r��ҵ�����ڣ�";
		 			}
				}
			}
		}catch (Exception e) {
		
			e.printStackTrace();
			strBackMsg = "�ֶ������ҵ����ҵ����ʱ��������";
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
			
			if(groupinfo != null && groupinfo.trim().length()>0){ //��ȡ�ַ��ֶ�
				
				String aem[] = groupinfo.split(",");
				String bem[] = aem[0].split("\\|");// ����id
				
				for(int x=0;x<bem.length;x++){//����
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
			}else{ // �޷���ʱ����ʾ����������Ʒ�������ڣ��ݻ������ȡ�
				head = " select jobdetail.productcode, jobdetail.m_strProductName,jobdetail.lotinfo,jobdetail.printdate,job.boundstockid,job.jobtype,substring(job.createtime,1,10),sum(jobdetail.jobnum),sum(jobdetail.volume),sum(jobdetail.weight),sum(jobdetail.netweight) ";
			}
			
			strBud.append(" from InoutJob as job, InoutJobdetail as jobdetail where job.jobid=jobdetail.jobid and job.inOutType='1' and job.status='4' ");
			
			if(warehouseid != null && warehouseid.trim().length() > 0){		//�ֿ�
				strBud.append(" and job.warehouseid='").append(warehouseid).append("'");
			}
			
			if(whAreaId != null && whAreaId.trim().length() > 0){		//����
				strBud.append(" and job.tcargoWhareaId='").append(whAreaId).append("'");
			}
			
			if(alleyId != null && alleyId.trim().length() > 0){		//���
				strBud.append(" and job.tcargoAlleyId='").append(alleyId).append("'");
			}
			
			if(indate_from != null && indate_from.trim().length() > 0){		//��ҵ����
				strBud.append(" and job.createtime>='").append(indate_from).append("'");
			}
			if(indate_to != null && indate_to.trim().length() > 0){		//��ҵ����
				strBud.append(" and job.createtime<='").append(indate_to).append(" 99:99:99'");
			}
			
			if(invoicetype != null && invoicetype.trim().length() > 0){	//��ҵ����
				strBud.append(" and job.invoicetype='").append(invoicetype).append("'");
			}

			if(productid != null && productid.trim().length() > 0){		//Ʒ��
				strBud.append(" and jobdetail.productid='").append(productid).append("'");
			}
			
			if(ownerid != null && ownerid.trim().length() > 0){		//����
				strBud.append(" and jobdetail.ownerId='").append(ownerid).append("'");
			}
			
			if(traycode != null && traycode.trim().length() > 0){		//��������
				strBud.append(" and job.traycode='").append(traycode).append("'");
			}
			
			if(customer_id != null && customer_id.trim().length() > 0){	//�ͻ�
				strBud.append(" and jobdetail.customerid='").append(customer_id).append("'");
			}
			
			if(boundstockid != null && boundstockid.trim().length() > 0){	//����
				strBud.append(" and job.boundstockid='").append(boundstockid).append("'");
			}
			
			if(intype != null && intype.trim().length() > 0){	//��ҵ����
				strBud.append(" and job.jobtype='").append(intype).append("'");
			}
			
	      
			// --customerid|ownerId,1|2,�ͻ�|����--
	        String strHQL = "";
	        if(groupinfo != null && groupinfo.trim().length()>0){ //��������
	        	String aem[] = groupinfo.split(",");
				String bem[] = aem[0].split("\\|");// ����id
				strBud.append(" group by ");
				for(int x=0;x<bem.length;x++){//����
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
				for(int x=0;x<bem.length;x++){//����
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
			throw new Exception("����������ѯ��ѯ�����ˮ�б����:" + er.getMessage());
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
			
			if(groupinfo != null && groupinfo.trim().length()>0){ //��ȡ�ַ��ֶ�
				
				String aem[] = groupinfo.split(",");
				String bem[] = aem[0].split("\\|");// ����id
				
				for(int x=0;x<bem.length;x++){//����
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
			}else{ // �޷���ʱ����ʾ����������Ʒ�������ڣ��ݻ������ȡ�
				head = " select jobdetail.productcode, jobdetail.m_strProductName,jobdetail.lotinfo,jobdetail.printdate,jobdetail.m_strCustomerName,job.boundstockid,job.jobtype,substring(job.createtime,1,10),sum(jobdetail.jobnum),sum(jobdetail.volume),sum(jobdetail.weight),sum(jobdetail.netweight) ";
			}
			
			strBud.append(" from InoutJob as job, InoutJobdetail as jobdetail where job.jobid=jobdetail.jobid and job.inOutType='2' and job.status='4' ");
			
			if(warehouseid != null && warehouseid.trim().length() > 0){		//�ֿ�
				strBud.append(" and job.warehouseid='").append(warehouseid).append("'");
			}
			
			if(whAreaId != null && whAreaId.trim().length() > 0){		//����
				strBud.append(" and job.tcargoWhareaId='").append(whAreaId).append("'");
			}
			
			if(alleyId != null && alleyId.trim().length() > 0){		//���
				strBud.append(" and job.tcargoAlleyId='").append(alleyId).append("'");
			}
			
			
			if(indate_from != null && indate_from.trim().length() > 0){		//��ҵ����
				strBud.append(" and job.createtime>='").append(indate_from).append("'");
			}
			if(indate_to != null && indate_to.trim().length() > 0){		//��ҵ����
				strBud.append(" and job.createtime<='").append(indate_to).append(" 99:99:99'");
			}
			
			if(invoicetype != null && invoicetype.trim().length() > 0){	//��ҵ����
				strBud.append(" and job.invoicetype='").append(invoicetype).append("'");
			}

			if(productid != null && productid.trim().length() > 0){		//Ʒ��
				strBud.append(" and jobdetail.productid='").append(productid).append("'");
			}
			
			if(ownerid != null && ownerid.trim().length() > 0){		//����
				strBud.append(" and jobdetail.ownerId='").append(ownerid).append("'");
			}
			
			if(traycode != null && traycode.trim().length() > 0){		//��������
				strBud.append(" and job.traycode='").append(traycode).append("'");
			}
			
			if(customer_id != null && customer_id.trim().length() > 0){	//�ͻ�
				strBud.append(" and jobdetail.customerid='").append(customer_id).append("'");
			}
			
			if(boundstockid != null && boundstockid.trim().length() > 0){	//����
				strBud.append(" and job.boundstockid='").append(boundstockid).append("'");
			}
			
			if(outtype != null && outtype.trim().length() > 0){	//��ҵ����
				strBud.append(" and job.outtype='").append(outtype).append("'");
			}
			
			
			
			

	        
			// --customerid|ownerId,1|2,�ͻ�|����--
	        String strHQL = "";
	        if(groupinfo != null && groupinfo.trim().length()>0){ //��������
	        	String aem[] = groupinfo.split(",");
				String bem[] = aem[0].split("\\|");// ����id
				strBud.append(" group by ");
				for(int x=0;x<bem.length;x++){//����
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
				for(int x=0;x<bem.length;x++){//����
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
			throw new Exception("����������ѯ��ѯ�����ˮ�б����:" + er.getMessage());
		}
				
		return list;
	}
}
