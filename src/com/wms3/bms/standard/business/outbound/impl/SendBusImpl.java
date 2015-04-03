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
 * ����: ����ȷ��ҵ����
 * @author hug 2012-10-9
 * @since WMS 3.0
 */
public class SendBusImpl implements ISendBus{
    /** ��ҵDAO�� */
    protected IJobDao jobDAO;
    /** ���ⵥDAO��  */
    protected IOutboundDao outBoundDAO;
    /** ����ȷ��DAO��*/
    protected ISendDao sendDAO;
    /**���ݸ���DAO��    */
    protected IReviewBus reviewBus;
    /** ���DAO��  */
    protected IInventoryDao inventoryDAO;
    /** ��λDAO��ӿ� */
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
	 * A�ͻ����ݴ��� (non-Javadoc)
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
			// �����ҵ��ϸ
			InoutJobdetail jobDetail = jobDAO.getJobDetailByDetailId(strJobDetailId);
			if (jobDetail != null) {
				// �����ҵ
				InoutJob job = jobDAO.getJobById(jobDetail.getJobid());
				// ������ϸ״̬
				OutboundInvoiceDetail invoiceDetail = outBoundDAO.getOutBoundDetailById(job.getBoundstockdetailid());
				InventoryStorage inventoryStorage = inventoryDAO.getInventoryByIdAndInventoryType(job.getTraycode());
				IBaseWhAreaDao baseWharea = new BaseWhAreaDaoImpl(sendDAO.getEntityDAO());// �ݴ���
				BaseWharea whareIda = baseWharea.getZCgetWhAreaByWhid(job.getWarehouseid(),job.getScargoWhareaId());
				String whAreaSpaceid = whareIda.getwhAreaId() + "010101";
				BaseCargospace baseSpace = spaceDao.getCargoSpaceById(whAreaSpaceid);// ��λ
				try {
					session = sendDAO.getEntityDAO().getSession();
					Transaction tx = session.beginTransaction();
					// ������ϸ�����Ƿ���ȷ��״̬
					if (invoiceDetail != null && !invoiceDetail.getLinestatus().equals("7")) { // ��Ϊ����ȷ��״̬
						if (job != null && job.getStatus().trim().equals("4")) {
							// ����������С�ڵ�����ҵ����
							if (num <= jobDetail.getJobnum()) {
								if (num <= jobDetail.getJobnum() - jobDetail.getAssignnum() && num > 0) {
									jobDetail.setJobnum(jobDetail.getJobnum() - num);
									session.update(jobDetail);
									// ������޸�
									if (inventoryStorage != null && inventoryStorage.getTraycode().equals(job.getTraycode())) {
										inventoryStorage.setPnum(inventoryStorage.getPnum() + num);
										loginfo = "��ҵid��"+job.getJobid()+"�������룺"+job.getTraycode()+" ���ݴ��д��ڵĲ�Ʒ���кϲ�,�ϲ�����Ϊ"+inventoryStorage.getPnum();
										session.update(inventoryStorage);
									} else if (inventoryStorage == null) {
										InventoryStorage inventoryStorage1 = new InventoryStorage();
										inventoryStorage1.setCargoSpaceId(whAreaSpaceid); // ��λID
										inventoryStorage1.setWhAreaId(whareIda.getwhAreaId()); // ����ID���ݴ���ID��
										inventoryStorage1.setWarehouseid(job.getWarehouseid()); // �ֿ�ID
										inventoryStorage1.setStatus("0"); // ״̬ 0:δ���䣬1���ѷ���  3:���� 4:��Ʒ������5���̵�
										inventoryStorage1.setPnum(num); // �������
										inventoryStorage1.setPvolume(volume); // ������
										inventoryStorage1.setPweight(weight); // �������
										inventoryStorage1.setPnetweight(netweight);// ��澻��
										inventoryStorage1.setProductid(jobDetail.getProductid());
										inventoryStorage1.setProductdate(jobDetail.getPrintdate());
										inventoryStorage1.setIndate(StrTools.getCurrDateTime(2));
										inventoryStorage1.setLotid(jobDetail.getLotid());
										inventoryStorage1.setLotinfo(jobDetail.getLotinfo());
										inventoryStorage1.setLotinfo2(jobDetail.getLotinfo2());
										inventoryStorage1.setSupplier(jobDetail.getSupplier());
										inventoryStorage1.setPackid(jobDetail.getPackid());
										inventoryStorage1.setIntype("2"); // �ѻ�
										inventoryStorage1.setPunit(jobDetail.getPunit());
										inventoryStorage1.setInjobid(job.getJobid());
										inventoryStorage1.setTraycode(job.getTraycode());
										inventoryStorage1.setInstockid(job.getBoundstockid());
										inventoryStorage1.setProductcode(jobDetail.getProductcode()); // ��Ʒ����
										inventoryStorage1.setProductstatus(jobDetail.getProductStatus()); // ��Ʒ״̬
										inventoryStorage1.setReserve3(jobDetail.getReserve3());// Ԥ��3
										inventoryStorage1.setReserve4(jobDetail.getReserve4());// Ԥ��4
										session.save(inventoryStorage1);
										loginfo = "��ҵid��"+job.getJobid()+"�������룺"+job.getTraycode()+" �ƿ⵽�ݴ�,�ƿ�����Ϊ"+inventoryStorage1.getPnum();
									}
								} else if (num >= jobDetail.getJobnum() - jobDetail.getAssignnum() && jobDetail.getJobnum() - jobDetail.getAssignnum() >= 0) {

									Double assignum = jobDetail.getAssignnum(); // �����ҵ����ǰ��������
									jobDetail.setJobnum(jobDetail.getJobnum() - num);
									jobDetail.setAssignnum(jobDetail.getJobnum());
									session.update(jobDetail);

									// ������޸�
									if (inventoryStorage != null && inventoryStorage.getTraycode().equals(job.getTraycode())) {
										inventoryStorage.setPnum(inventoryStorage.getPnum() + num);
										session.update(inventoryStorage);
										loginfo = "1��ҵid��"+job.getJobid()+"�������룺"+job.getTraycode()+" ���ݴ��д��ڵĲ�Ʒ���кϲ�,�ϲ�����Ϊ"+inventoryStorage.getPnum();
									} else if (inventoryStorage == null) {
										InventoryStorage inventoryStorage1 = new InventoryStorage();
										inventoryStorage1.setCargoSpaceId(whAreaSpaceid); // ��λID
										inventoryStorage1.setWhAreaId(whareIda.getwhAreaId()); // ����ID���ݴ���ID��
										inventoryStorage1.setWarehouseid(job.getWarehouseid()); // �ֿ�ID
										inventoryStorage1.setStatus("0"); // ״̬0:δ���䣬1���ѷ��� 3:���� 4:��Ʒ������5���̵�
										inventoryStorage1.setPnum(num); // �������
										inventoryStorage1.setPvolume(volume); // ������
										inventoryStorage1.setPweight(weight); // �������
										inventoryStorage1.setPnetweight(netweight);// ��澻��
										inventoryStorage1.setProductid(jobDetail.getProductid());
										inventoryStorage1.setProductdate(jobDetail.getPrintdate());
										inventoryStorage1.setIndate(StrTools.getCurrDateTime(2));
										inventoryStorage1.setLotid(jobDetail.getLotid());
										inventoryStorage1.setLotinfo(jobDetail.getLotinfo());
										inventoryStorage1.setLotinfo2(jobDetail.getLotinfo2());
										inventoryStorage1.setSupplier(jobDetail.getSupplier());
										inventoryStorage1.setPackid(jobDetail.getPackid());
										inventoryStorage1.setIntype("2"); // �ѻ�
										inventoryStorage1.setPunit(jobDetail.getPunit());
										inventoryStorage1.setInjobid(job.getJobid());
										inventoryStorage1.setTraycode(job.getTraycode());
										inventoryStorage1.setInstockid(job.getBoundstockid());
										inventoryStorage1.setProductcode(jobDetail.getProductcode()); // ��Ʒ����
										inventoryStorage1.setProductstatus(jobDetail.getProductStatus()); // ��Ʒ״̬
										inventoryStorage1.setReserve3(jobDetail.getReserve3());// Ԥ��3
										inventoryStorage1.setReserve4(jobDetail.getReserve4());// Ԥ��4
										session.save(inventoryStorage1);
										loginfo = "1��ҵid��"+job.getJobid()+"�������룺"+job.getTraycode()+" �ƿ⵽�ݴ�,�ƿ�����Ϊ"+inventoryStorage1.getPnum();
									}
									// ���ⵥ��ϸ
									invoiceDetail.setAssignnum(invoiceDetail.getAssignnum() + (jobDetail.getAssignnum() - assignum)); // (jobDetail.getAssignnum()-assignum)																									// ���ڷ���������ȥ��ǰ��������
									session.update(invoiceDetail);
								} else {
									strMsg = "����ҵ[" + jobDetail.getJobid() + "]�����������ԣ����ܲ�����";
								}
							} else {
								strMsg = "����ҵ[" + jobDetail.getJobid() + "]�������������ܴ��ڸ���ҵ�ķ���������";
							}

						} else {
							strMsg = "����ҵ[" + jobDetail.getJobid() + "]δ��ɣ����ܲ�����";
						}
					} else {
						strMsg = "����ҵ[" + jobDetail.getJobid() + "]�ĵ��ݣ��ѷ���ȷ���ˣ�";
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
				strMsg = "����ҵ��ϸ[" + strJobDetailId + "]�����ڣ��޷�������";
			}
            Logger.info("�û�["+strUserCode+"]���������==>����ȷ�ϵ�����Ϣ:" +loginfo);
		}
		return strMsg;
	}
    /**
     * A�ͻ������Ƶ��ݴ���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.ISendBus#getBatchAtoZ(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public String getBatchAtoZ(String strJobDetailIds, String strUserCode, String strShiftid, String warehouseId, String whAreaId, String whAreaSpaceId) throws Exception {
        String strMsg = ""; 
        String[] details = strJobDetailIds.split(",");
        for(int i=0; i<details.length; i++)
        {
            String strJobDetailId = details[i]; //��ҵ��ϸID
            synchronized (strJobDetailId)       //ͬ����ҵ��ϸ
            {
                //�����ҵ��ϸ
                InoutJobdetail jobDetail = jobDAO.getJobDetailByDetailId(strJobDetailId);
                if(jobDetail != null){
                	InoutJob job = jobDAO.getJobById(jobDetail.getJobid());
                    //������ϸ״̬
                    OutboundInvoiceDetail  invoiceDetail = outBoundDAO.getOutBoundDetailById(job.getBoundstockdetailid());
                    //������ϸ�����Ƿ���ȷ��״̬
                    if(invoiceDetail != null && !invoiceDetail.getLinestatus().equals("7")){ //��Ϊ����ȷ��״̬
                        //�����ҵ
                        if(job != null && job.getStatus().trim().equals("4")){
                                //���ӵ��ݴ�����
                                InventoryStorage inventoryStorage = new InventoryStorage();
                                inventoryStorage.setCargoSpaceId(whAreaSpaceId);    //��λID
                                inventoryStorage.setWhAreaId(whAreaId);             //����ID���ݴ���ID��
                                inventoryStorage.setWarehouseid(warehouseId);       //�ֿ�ID 
                                inventoryStorage.setStatus("0"); //״̬  0:δ���䣬1���ѷ��� 3:���� 4:��Ʒ������5���̵�
                                
                                inventoryStorage.setOwnerId(jobDetail.getOwnerId());    //����ID
                                inventoryStorage.setProductid(jobDetail.getProductid());//��ƷID
                                inventoryStorage.setPackid(jobDetail.getPackid());      //��װID
                                inventoryStorage.setIndate(StrTools.getCurrDateTime(2));//���ʱ��
                                         
                                inventoryStorage.setIntype("2");                        //��ⷽʽ 1.���� 2.�ѻ�
                                inventoryStorage.setPunit(jobDetail.getPunit());        //������λ
                                inventoryStorage.setLotid(jobDetail.getLotid());        //����ID
                                
                                inventoryStorage.setPnum(jobDetail.getJobnum());         //�������
                                inventoryStorage.setPvolume(jobDetail.getVolume());      //������
                                inventoryStorage.setPweight(jobDetail.getWeight());      //�������
                                inventoryStorage.setPnetweight(jobDetail.getNetweight());//��澻��
                                inventoryStorage.setInjobid(jobDetail.getJobid());    //��ҵID
                                inventoryStorage.setInjobetailid(strJobDetailId);     //��ҵ��ϸID
                                inventoryStorage.setTraycode(job.getTraycode());      //��������
                               
                                inventoryStorage.setReserve3(jobDetail.getReserve3());//Ԥ��3
                                inventoryStorage.setReserve4(jobDetail.getReserve4());//Ԥ��4
                                 
                                
                                //ɾ����ҵ��ϸ
                                String strJobSQL = "delete from InoutJobdetail where jobdetailid='" + strJobDetailId + "'";   
                                //�޸�A������ϸ�ķ�������
                                String strAInvoiceSQL = "update OutboundInvoiceDetail set " +
                                "assignnum=assignnum-" + jobDetail.getJobnum() + ", " +
                                "assignnetweight=assignnetweight-" + jobDetail.getNetweight() + ", " +
                                "assignweight=assignweight-" + jobDetail.getWeight() + ", " +
                                "assignvolume=assignvolume-" + jobDetail.getVolume() + 
                                "  where outstockdetailid='" + job.getBoundstockdetailid() + "'";   
                                //A�ͻ�->�ݴ���
                                //sendDAO.sendAmoveZ(inventoryStorage, strJobSQL, strAInvoiceSQL);  
                                //���ĸ�������
                                String strRevSQL = "";
                                boolean result= reviewBus.isCheckedByHeader(job.getBoundstockid(), "1");
                                if (result) {
    								strRevSQL = "update OutboundCrosscheck set qtyscan=qtyscan- " + jobDetail.getJobnum()+", where outstockdetailid='"+ job.getBoundstockdetailid()+",";
    							}else {
    								strRevSQL = "delete from OutboundCrosscheck where outstockdetailid='"+job.getBoundstockdetailid()+"'";
    							}
                                sendDAO.sendAmoveZ(inventoryStorage, strJobSQL, strAInvoiceSQL, strRevSQL);
                        }else{
                            strMsg = "����ҵ[" + jobDetail.getJobid() + "]δ��ɣ����ܲ�����";
                        }  
                    }else{
                        strMsg = "����ҵ[" + jobDetail.getJobid() + "]�ĵ��ݣ��ѷ���ȷ���ˣ�";
                    }
                }else{
                    strMsg = "����ҵ��ϸ[" + strJobDetailId + "]�����ڣ��޷�������";
                }   
            }
        }
        return strMsg;
    }
    /**
     * A�ͻ��ƶ���B�ͻ�
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.ISendBus#getAtoB(java.lang.String, java.lang.String, java.lang.String, double, double, double, double, java.lang.String, java.lang.String)
     */
    public String getAtoB(String strBInvoiceId, String strBInvoiceDetailId, String strJobDetailId, double num, double weight, double netweight, double volume, String strUserCode, String strShiftid) throws Exception {
        String strMsg = "";
        synchronized (strJobDetailId) 
        {  
            //�����ҵ��ϸ
            InoutJobdetail jobDetail = jobDAO.getJobDetailByDetailId(strJobDetailId);
            
            if(jobDetail != null){ 
            	InoutJob aJob = jobDAO.getJobById(jobDetail.getJobid());
                //B�ͻ�������ϸ״̬
                OutboundInvoiceDetail  binvoiceDetail = outBoundDAO.getOutBoundDetailById(strBInvoiceDetailId);
                //B�ͻ�������ϸ�����Ƿ���ȷ��״̬
                if(binvoiceDetail != null && !binvoiceDetail.getLinestatus().equals("7")){ //��Ϊ����ȷ��״̬
                    //A�ͻ�������ϸ״̬
                    OutboundInvoiceDetail  invoiceDetail = outBoundDAO.getOutBoundDetailById(aJob.getBoundstockdetailid());
                    //A�ͻ�������ϸ�����Ƿ���ȷ��״̬
                    if(invoiceDetail != null && !invoiceDetail.getLinestatus().equals("7")){ //��Ϊ����ȷ��״̬
                        //�����ҵ
                        if(aJob != null && aJob.getStatus().trim().equals("4")){
                            //�ж���ҵ��ϸ����
                            if(jobDetail.getAssignnum() >= num){
                                //������ҵ����ҵ��ϸ  �޸���ҵ����  
                                //������ҵ����ҵ��ϸ
                                InoutJob bJob = new InoutJob();
                                
                                BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(jobDAO.getEntityDAO());
				                BaseSeq  seqEn = seqDao.getSeqByType("CKZY");
				                String strID = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), jobDAO.getEntityDAO());
				                
                                bJob.setJobid(strID);//������ҵID
                                bJob.setJobtype(aJob.getJobtype());  //��ҵ����1-���2-����3-�ƿ�
                                bJob.setOnLineType("2"); //����ģʽ 1.���� 2.�ѻ�
                                bJob.setIsaccount("Y");  //�Ƿ���� Y.�� N.��
                                bJob.setStatus("4");     //��ҵ״̬ 1.δִ�� 2.��ִ�� 3.��ִ�� 4.��� 5.���� 6.�쳣��ҵ 8.��ͣ��ҵ
                                bJob.setPriority(10);    //���ȼ�
                                bJob.setCreatetime(StrTools.getCurrDateTime(2)); //ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
                                bJob.setJobpos("2");     //��ҵ���� 1.���� 2.�����
                                bJob.setInOutType("2");  //������� 0:�ջ�1�ϼ���� 2.����
                                bJob.setShiftid(strShiftid);    //��ҵ���
                                bJob.setWarehouseid(aJob.getWarehouseid());//�ֿ�ID
                                bJob.setTraycode(aJob.getTraycode());   //��������
                                bJob.setOpManId(strUserCode);    //������
                                bJob.setInvoicetype("2");//1:������ҵ�����ɵ���;2:���е��ݺ�������ҵ
                                
                                bJob.setScargoWhareaId(aJob.getScargoWhareaId());//Դ����ID
                                bJob.setScargoSpaceId(aJob.getScargoSpaceId());  //Դ��λID
                                bJob.setScargoAlleyId(aJob.getScargoAlleyId());  //Դ���ID 
                                bJob.setBoundstockid(strBInvoiceId);//B����ID
                                bJob.setBoundstockdetailid(strBInvoiceDetailId);//B������ϸID
                                
                                //������ҵ��ϸ
                                InoutJobdetail bjobdetail = new InoutJobdetail();
                                //��ҵ��ϸID
                                String strBJobDetailId = SeqTool.getDetailId(bJob.getJobid(), "1");
                                bjobdetail.setJobdetailid(strBJobDetailId);    //��ҵ��ϸID
                                bjobdetail.setJobid(bJob.getJobid());          //��ҵ���
                                bjobdetail.setLinestatus("4");  //״̬0:�½� 4:���
                                bjobdetail.setInventoryid(jobDetail.getInventoryid());//���ID
                                bjobdetail.setProductid(jobDetail.getProductid());  //��ƷID
                                bjobdetail.setPackid(jobDetail.getPackid());//��װID
                                bjobdetail.setPunit(jobDetail.getPunit());     //��λ
                                bjobdetail.setLotid(jobDetail.getLotid());     //��������ID
                                bjobdetail.setJobnum(num);            //��ҵ����
                                bjobdetail.setAssignnum(num);         //��������
                                bjobdetail.setVolume(volume);         //���
                                bjobdetail.setWeight(weight);         //����
                                bjobdetail.setNetweight(netweight);   //����
                                bjobdetail.setAssignvolume(volume);   //�������
                                bjobdetail.setAssignweight(weight);   //��������
                                bjobdetail.setAssignnetweight(netweight);//���侻��    
                                bjobdetail.setCustomerid(binvoiceDetail.getCustomid());//�ͻ�ID
                                bjobdetail.setOwnerId(jobDetail.getOwnerId());      //����ID

                                
                                bjobdetail.setBoxcode(jobDetail.getBoxcode());      //����
                                bjobdetail.setIsinvoice("Y");      //�Ƿ������ɵ��� Y��  N��
                                bjobdetail.setInjobdetailid(jobDetail.getJobdetailid());  //Դ��ҵ��ϸ�����ɳ�����ҵʱ����Ӹÿ��������ҵ��
                                bjobdetail.setReserve1(jobDetail.getReserve1()); //Ԥ��1
                                bjobdetail.setReserve2(jobDetail.getReserve2()); //Ԥ��2
                                bjobdetail.setReserve3(jobDetail.getReserve3()); //Ԥ��3
                                bjobdetail.setReserve4(jobDetail.getReserve4()); //Ԥ��4
                                 
                                //�޸���ҵ��ϸ����
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
                                jobDetail.setJobnum(jobDetail.getJobnum() - num);       //��ҵ����
                                jobDetail.setWeight(jobDetail.getWeight() - weight);    //����
                                jobDetail.setNetweight(jobDetail.getNetweight() - netweight);//����
                                jobDetail.setVolume(jobDetail.getVolume() - volume);    //���
                                jobDetail.setAssignnum(jobDetail.getAssignnum() - num);
                                jobDetail.setAssignnetweight(jobDetail.getAssignnetweight() - netweight);
                                jobDetail.setAssignweight(jobDetail.getAssignweight() - weight);
                                jobDetail.setAssignvolume(jobDetail.getAssignvolume() - volume);
                                
                                //�޸�A������ϸ�ķ�������
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
                                //�޸�B������ϸ�ķ�������
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
                                
                                //A�ͻ�->B�ͻ�
                                sendDAO.sendAmoveB(strJobSQL, bJob, bjobdetail, strAInvoiceSQL, strBInvoiceSQL); 
                                
                            }else{
                                strMsg = "����ҵ[" + jobDetail.getJobid() + "]��ҵ��ϸ["+ strJobDetailId +"]������["+jobDetail.getAssignnum()+"]С����������["+num+"]�����ܲ�����";
                            }    
                        }else{
                            strMsg = "����ҵ[" + jobDetail.getJobid() + "]δ��ɣ����ܲ�����";
                        } 
                        
                    }else{
                        strMsg = "A�ͻ�����ҵ["+jobDetail.getJobid()+"]�ĵ�����ϸ���ѷ���ȷ���ˣ�";
                    }
                    
                }else{
                    strMsg = "B�ͻ��ĵ�����ϸ["+strBInvoiceDetailId+"]���ѷ���ȷ���ˣ�";
                }     
            }else{
                strMsg = "����ҵ��ϸ[" + strJobDetailId + "]�����ڣ��޷�������";
            }   
        }
        return strMsg;
    }
    /**
     * A�ͻ�������B�ͻ�
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.ISendBus#getBatchAtoB(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public String getBatchAtoB(String strBInvoiceId, String strBInvoiceDetailId, String strJobDetailIds, String strUserCode, String strShiftid) throws Exception {
        String strMsg = "";
        String[] details = strJobDetailIds.split(",");
        for(int i=0; i<details.length; i++)
        {
            String strJobDetailId = details[i]; //��ҵ��ϸID
            synchronized (strJobDetailId) 
            {  
                //�����ҵ��ϸ
                InoutJobdetail jobDetail = jobDAO.getJobDetailByDetailId(strJobDetailId);
                if(jobDetail != null){ 
                	InoutJob job = jobDAO.getJobById(jobDetail.getJobid());
                    //B�ͻ�������ϸ״̬
                    OutboundInvoiceDetail  binvoiceDetail = outBoundDAO.getOutBoundDetailById(strBInvoiceDetailId);
                    //B�ͻ�������ϸ�����Ƿ���ȷ��״̬
                    if(binvoiceDetail != null && !binvoiceDetail.getLinestatus().equals("7")){ //��Ϊ����ȷ��״̬
                        //A�ͻ�������ϸ״̬
                        OutboundInvoiceDetail  invoiceDetail = outBoundDAO.getOutBoundDetailById(job.getBoundstockdetailid());
                        //A�ͻ�������ϸ�����Ƿ���ȷ��״̬
                        if(invoiceDetail != null && !invoiceDetail.getLinestatus().equals("7")){ //��Ϊ����ȷ��״̬
                            //�����ҵ
                            InoutJob aJob = jobDAO.getJobById(jobDetail.getJobid());
                            if(aJob != null && aJob.getStatus().trim().equals("4")){ 
                                    //������ҵ����ҵ��ϸ
                                    InoutJob bJob = new InoutJob();
                                    BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(jobDAO.getEntityDAO());
    				                BaseSeq  seqEn = seqDao.getSeqByType("CKZY");
    				                String strID = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), jobDAO.getEntityDAO());//��ҵID
    				                
                                    bJob.setJobid(strID);    //������ҵID
                                    bJob.setJobtype(aJob.getJobtype());  //��ҵ����1-���2-����3-�ƿ�
                                    bJob.setOnLineType("2"); //����ģʽ 1.���� 2.�ѻ�
                                    bJob.setIsaccount("Y");  //�Ƿ���� Y.�� N.��
                                    bJob.setStatus("4");     //��ҵ״̬ 1.δִ�� 2.��ִ�� 3.��ִ�� 4.��� 5.���� 6.�쳣��ҵ 8.��ͣ��ҵ
                                    bJob.setPriority(10);    //���ȼ�
                                    bJob.setCreatetime(StrTools.getCurrDateTime(2)); //ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
                                    bJob.setJobpos("2");     //��ҵ���� 1.���� 2.�����
                                    bJob.setInOutType("2");  //������� 0:�ջ�1�ϼ���� 2.����
                                    bJob.setShiftid(strShiftid);    //��ҵ���
                                    bJob.setWarehouseid(aJob.getWarehouseid()); //�ֿ�ID
                                    bJob.setTraycode(aJob.getTraycode());       //��������
                                    bJob.setOpManId(strUserCode);               //������
                                    bJob.setInvoicetype("2");   //1:������ҵ�����ɵ���;2:���е��ݺ�������ҵ
                                    
                                    bJob.setScargoWhareaId(aJob.getScargoWhareaId());//Դ����ID
                                    bJob.setScargoSpaceId(aJob.getScargoSpaceId());  //Դ��λID
                                    bJob.setScargoAlleyId(aJob.getScargoAlleyId());  //Դ���ID 
                                    bJob.setBoundstockid(strBInvoiceId);//B����ID
                                    bJob.setBoundstockdetailid(strBInvoiceDetailId);//B������ϸID
                                    
                                    //������ҵ��ϸ
                                    InoutJobdetail bjobdetail = new InoutJobdetail();
                                    //��ҵ��ϸID
                                    String strBJobDetailId = SeqTool.getDetailId(bJob.getJobid(), "1");
                                    bjobdetail.setJobdetailid(strBJobDetailId);    //��ҵ��ϸID
                                    bjobdetail.setJobid(bJob.getJobid());          //��ҵ���
                                    bjobdetail.setLinestatus("4");                 //״̬0:�½� 4:���
                                    bjobdetail.setInventoryid(jobDetail.getInventoryid());//���ID
                                    bjobdetail.setProductid(jobDetail.getProductid());  //��ƷID
                                    bjobdetail.setPackid(jobDetail.getPackid());        //��װID
                                    bjobdetail.setPunit(jobDetail.getPunit());          //��λ
                                    bjobdetail.setLotid(jobDetail.getLotid());          //��������ID
                                    bjobdetail.setJobnum(jobDetail.getJobnum());        //��ҵ����
                                    bjobdetail.setAssignnum(jobDetail.getJobnum());     //��������
                                    bjobdetail.setVolume(jobDetail.getVolume());        //���
                                    bjobdetail.setWeight(jobDetail.getWeight());        //����
                                    bjobdetail.setNetweight(jobDetail.getNetweight());  //����
                                    bjobdetail.setAssignvolume(jobDetail.getVolume());  //�������
                                    bjobdetail.setAssignweight(jobDetail.getWeight());  //��������
                                    bjobdetail.setAssignnetweight(jobDetail.getNetweight());//���侻��    
                                    bjobdetail.setCustomerid(binvoiceDetail.getCustomid()); //�ͻ�ID
                                    bjobdetail.setOwnerId(jobDetail.getOwnerId());      //����ID
  
                                    
                                    bjobdetail.setBoxcode(jobDetail.getBoxcode());      //����
                                    bjobdetail.setIsinvoice("Y");      //�Ƿ������ɵ��� Y��  N��
                                    bjobdetail.setInjobdetailid(jobDetail.getJobdetailid());  //Դ��ҵ��ϸ�����ɳ�����ҵʱ����Ӹÿ��������ҵ��
                                    bjobdetail.setReserve1(jobDetail.getReserve1()); //Ԥ��1
                                    bjobdetail.setReserve2(jobDetail.getReserve2()); //Ԥ��2
                                    bjobdetail.setReserve3(jobDetail.getReserve3()); //Ԥ��3
                                    bjobdetail.setReserve4(jobDetail.getReserve4()); //Ԥ��4
                                     
                                    //�޸���ҵ��ϸ����
                                    String strJobSQL = "delete from InoutJobdetail  where jobdetailid='" + strJobDetailId + "'";
                                    
                                    //�޸�A������ϸ�ķ�������
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
                                    //�޸�B������ϸ�ķ�������
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
                                    
                                    //A�ͻ�->B�ͻ�
                                    sendDAO.sendAmoveB(strJobSQL, bJob, bjobdetail, strAInvoiceSQL, strBInvoiceSQL);   
                            }else{
                                strMsg = "����ҵ[" + jobDetail.getJobid() + "]δ��ɣ����ܲ�����";
                            } 
                        }else{
                            strMsg = "A�ͻ�����ҵ["+jobDetail.getJobid()+"]�ĵ�����ϸ���ѷ���ȷ���ˣ�";
                        }  
                    }else{
                        strMsg = "B�ͻ��ĵ�����ϸ["+strBInvoiceDetailId+"]���ѷ���ȷ���ˣ�";
                    }     
                }else{
                    strMsg = "����ҵ��ϸ[" + strJobDetailId + "]�����ڣ��޷�������";
                }   
            }
        }
        return strMsg;
    }
    
    /**
     * �ݴ�����A�ͻ�
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.ISendBus#getZtoA(java.lang.String, java.lang.String, double, double, double, double, java.lang.String, java.lang.String)
     */
    public String getZtoA(String strAInvoiceDetailId, String strInventoryId, double num, double weight, double netweight, double volume, String strUserCode, String strShiftid) throws Exception {
        String strMsg = "";
        synchronized (strInventoryId) 
        {  
            //��ÿ��
            InventoryStorage inventory = inventoryDAO.getInventoryById(strInventoryId);
            if(inventory != null){ 
                //A�ͻ�������ϸ״̬
                OutboundInvoiceDetail  ainvoiceDetail = outBoundDAO.getOutBoundDetailById(strAInvoiceDetailId);
                //A�ͻ�������ϸ�����Ƿ���ȷ��״̬
                if(ainvoiceDetail != null && !ainvoiceDetail.getLinestatus().equals("3")){ //��Ϊ����ȷ��״̬
                    OutboundInvoiceHeader outInvoice = outBoundDAO.getOutBoundById(ainvoiceDetail.getOutstockid());//����
                    //�жϿ������
                    //���ʵ������
                    double realnum =  inventory.getPnum() - inventory.getAssignnum() - inventory.getHoldnum(); 
                    if(realnum >= num){
                        //������ҵ����ҵ��ϸ
                        InoutJob bJob = new InoutJob();
                        
                        BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(jobDAO.getEntityDAO());
		                BaseSeq  seqEn = seqDao.getSeqByType("CKZY");
		                String strID = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), jobDAO.getEntityDAO());//��ҵID
                        bJob.setJobid(strID);//������ҵID
                        bJob.setJobtype(outInvoice.getOuttype());  //��ҵ����1-���2-����3-�ƿ�
                        bJob.setOnLineType("2"); //����ģʽ 1.���� 2.�ѻ�
                        bJob.setIsaccount("Y");  //�Ƿ���� Y.�� N.��
                        bJob.setStatus("4");     //��ҵ״̬ 1.δִ�� 2.��ִ�� 3.��ִ�� 4.��� 5.���� 6.�쳣��ҵ 8.��ͣ��ҵ
                        bJob.setPriority(10);    //���ȼ�
                        bJob.setCreatetime(StrTools.getCurrDateTime(2)); //ʱ�� ʱ���ʽ��yyyy-MM-dd hh:mm:ss
                        bJob.setJobpos("2");     //��ҵ���� 1.���� 2.�����
                        bJob.setInOutType("2");  //������� 0:�ջ�1�ϼ���� 2.����
                        bJob.setShiftid(strShiftid);    //��ҵ���
                        bJob.setWarehouseid(inventory.getWarehouseid());//�ֿ�ID
                        bJob.setTraycode(inventory.getTraycode());   //��������
                        bJob.setOpManId(strUserCode);    //������
                        bJob.setInvoicetype("2");//1:������ҵ�����ɵ���;2:���е��ݺ�������ҵ
                        
                        bJob.setScargoWhareaId(inventory.getWhAreaId());//Դ����ID
                        bJob.setScargoSpaceId(inventory.getCargoSpaceId());  //Դ��λID
                        bJob.setScargoAlleyId(inventory.getCargoAlleyId());  //Դ���ID 
                        bJob.setBoundstockid(ainvoiceDetail.getOutstockid());//����ID
                        bJob.setBoundstockdetailid(ainvoiceDetail.getOutstockdetailid());//������ϸID
                        
                        //������ҵ��ϸ
                        InoutJobdetail bjobdetail = new InoutJobdetail();
                        //��ҵ��ϸID
                        String strBJobDetailId = SeqTool.getDetailId(bJob.getJobid(), "1");
                        bjobdetail.setJobdetailid(strBJobDetailId);    //��ҵ��ϸID
                        bjobdetail.setJobid(bJob.getJobid());          //��ҵ���
                        bjobdetail.setLinestatus("2");  //״̬0:�½� 2:���
                        bjobdetail.setInventoryid(inventory.getInventoryid());//���ID
                        bjobdetail.setProductid(inventory.getProductid());  //��ƷID
                        
                        bjobdetail.setPackid(inventory.getPackid());//��װID
                        bjobdetail.setPunit(inventory.getPunit());     //��λ
                        bjobdetail.setLotid(inventory.getLotid());     //��������ID
                        
                        bjobdetail.setJobnum(num);        //��ҵ����
                        bjobdetail.setAssignnum(num);     //��������
                        bjobdetail.setVolume(volume);         //���
                        bjobdetail.setWeight(weight);         //����
                        bjobdetail.setNetweight(netweight);   //����
                        bjobdetail.setAssignvolume(volume);   //�������
                        bjobdetail.setAssignweight(weight);   //��������
                        bjobdetail.setAssignnetweight(netweight);//���侻�� 
                        
                        bjobdetail.setCustomerid(ainvoiceDetail.getCustomid());//�ͻ�ID
                        bjobdetail.setOwnerId(inventory.getOwnerId());//����ID
                        
                        bjobdetail.setIsinvoice("Y");      //�Ƿ������ɵ��� Y��  N��
                        bjobdetail.setInjobdetailid(inventory.getInjobetailid());  //Դ��ҵ��ϸ�����ɳ�����ҵʱ����Ӹÿ��������ҵ��
                       
                        bjobdetail.setReserve3(inventory.getReserve3());       //Ԥ��3
                        bjobdetail.setReserve4(inventory.getReserve4());       //Ԥ��4 
                        
                        //�޸Ŀ����ϸ������ɾ�����????????????????????????????????????????????????????
                        String strUpdateInventorySql = null;
                        if(num < realnum){ //С��ʵ�ʿ������
                            strUpdateInventorySql = "update InventoryStorage set " +
                            "pnum=pnum-"+num+"," +
                            "pvolume=pvolume-" + volume + "," +
                            "pweight=pweight-"+weight+"," +
                            "pnetweight=pnetweight-"+netweight+"  " +
                            "where inventoryid='" + strInventoryId + "'";   
                        }else{//����ʵ�ʿ������
                            //����Ϊ0��ɾ�����
                            if(inventory.getHoldnum() == 0){//ֱ��ɾ�����
                                strUpdateInventorySql = "delete from InventoryStorage where inventoryid='" + strInventoryId + "'";
                            }else{//�޸Ŀ��
                                strUpdateInventorySql = "update InventoryStorage set " +
                                "pnum=pnum-"+num+"," +
                                "pvolume=pvolume-" + volume + "," +
                                "pweight=pweight-"+weight+"," +
                                "pnetweight=pnetweight-"+netweight+"  " +
                                "where inventoryid='" + strInventoryId + "'";
                            }
                        }   
                        
                        //�޸�A������ϸ�ķ�������
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
                 
                        //�ݴ���->A�ͻ�
                        sendDAO.sendZmoveA(strUpdateInventorySql, bJob, bjobdetail, strInvoiceSQL); 
                        
                    }else{
                        strMsg = "�ÿ��[" + strInventoryId + "]������["+realnum+"]С����������["+num+"]�����ܲ�����";
                    } 
                }else{
                    strMsg = "B�ͻ��ĵ�����ϸ["+strAInvoiceDetailId+"]���ѷ���ȷ���ˣ�";
                }     
            }else{
                strMsg = "�ÿ��[" + strInventoryId + "]�����ڣ��޷�������";
            }   
        }
        return strMsg;
    }
    
    /**
     * �����ݷ���ȷ��
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.ISendBus#outInvoiceFHQR(java.lang.String, java.lang.String, java.lang.String)
     */
    public String outInvoiceFHQR(String strInvoiceId, String strUserCode, String strShiftid) throws Exception {
        String strMsg = "Y";
        synchronized (strInvoiceId) 
        {
            OutboundInvoiceHeader outInvoice = outBoundDAO.getOutBoundById(strInvoiceId);//����
            if(outInvoice != null && (!outInvoice.getOutstatus().equals("7") || !outInvoice.getOutstatus().equals("8"))){
            	//7:����ȷ�� 8:����
                //���õ���δ��ɵ���ҵ��(��Ϊ4:��ɺ�5:����)
                int iJob = jobDAO.getUnfinishedJob(strInvoiceId, null);
                int iJobFH = jobDAO.getUncheckUpJob(strInvoiceId, null);
                
                if(iJob != 0)
                {
                    strMsg = "�õ���["+strInvoiceId+"]�С�" + iJob + "������ҵδ��ɣ�";
                }else if(iJobFH != 0){
                	strMsg = "�õ���["+strInvoiceId+"]�С�" + iJob + "������ҵδ���ˣ�";
                }else{
                    //���ݵ��ݺŲ�ѯ��Ҫ��������ҵ��ϸ������ʣ�µ�
                    List ls = jobDAO.getRemainJobDetail(strInvoiceId, null);
                    if(ls != null && ls.size()>0){
                        //����
                        strMsg = "�õ���["+strInvoiceId+"]�С�" + ls.size() + "����ʣ���Ʒδ�Ƶ��ݴ�����";   
                        return strMsg;
                    }   
                    //��ⵥ����ϸ�Ŀ��������뷢������
                    List<OutboundInvoiceDetail> lsDetail = outBoundDAO.getOutBoundDetailsById(strInvoiceId);
                    List<String> lsSQL = new ArrayList<String>();//Ҫ���µĵ�����ϸSQL���
                    if(lsDetail != null && lsDetail.size()>0)
                    {
                        OutboundInvoiceDetail outDetail = null;//���ⵥ��ϸ
                        for(int i = 0; i < lsDetail.size(); i++)
                        {
                            outDetail = lsDetail.get(i);
                            if(outDetail != null && !outDetail.getLinestatus().equals("7")){//������ϸû����ȷ�� 
                                
                                //��������
                                Object[] numobj = jobDAO.getInvoiceDetailFinishNum(outDetail.getOutstockdetailid());
                                Double dNum = numobj[0] == null ? 0 : (Double)numobj[0];        //����
                                Double dWeight = numobj[1] == null ? 0 : (Double)numobj[1];     //ë��
                                Double dNetWeight = numobj[2] == null ? 0 : (Double)numobj[2];  //����
                                Double dVolume = numobj[3] == null ? 0 : (Double)numobj[3];     //���
                                
                                //������ϸ����ȷ�ϲ���
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
                    //����ȷ��
                    outInvoice.setConfirmdate(StrTools.getCurrDateTime(1));
                    outInvoice.setConfirmanid(strUserCode);
                    outInvoice.setOutstatus("7");
                    String strInvoiceSQL = "update OutboundInvoiceHeader set outstatus='7',confirmanid='" + strUserCode + "', confirmdate='" + StrTools.getCurrDateTime(1) + "' where outstockid='" + strInvoiceId + "'";
                    
                    //����ȷ�ϲ���
                    sendDAO.outInvoiceFHQR(strInvoiceSQL, lsSQL); 
                }  
            }else{
                strMsg = "�õ���[" + strInvoiceId + "]�����ڻ򵥾�״̬��Ϊ���״̬���޷�����ȷ�ϣ�";
            } 
        }
        return strMsg;
    }
    
    /**
     * ��������ϸ����ȷ��
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.ISendBus#outInvoiceDetailFHQR(java.lang.String, java.lang.String, java.lang.String)
     */
    public String outInvoiceDetailFHQR(String strInvoiceDetailId, String strUserCode, String strShiftid) throws Exception {
        String strMsg = "Y";
        synchronized (strInvoiceDetailId) 
        {
            //���ⵥ��ϸ
            OutboundInvoiceDetail outDetail = outBoundDAO.getOutBoundDetailById(strInvoiceDetailId);
            if(outDetail != null && !outDetail.getLinestatus().equals("3")){//������ϸû����ȷ�� 
                //���ⵥ
                String strInvoiceId = outDetail.getOutstockid();
                OutboundInvoiceHeader outInvoice = outBoundDAO.getOutBoundById(strInvoiceId);
                if(outInvoice != null && (!outInvoice.getOutstatus().equals("7") || !outInvoice.getOutstatus().equals("8"))){  
                    //���õ��ݵĵ�����ϸδ��ɵ���ҵ��(��Ϊ4:��ɺ�5:����)
                    int iJob = jobDAO.getUnfinishedJob(strInvoiceId, strInvoiceDetailId);
                    int iJobFH = jobDAO.getUncheckUpJob(strInvoiceId, strInvoiceDetailId);
                    if(iJob != 0)
                    {
                        strMsg = "�õ���["+strInvoiceId+"]������ϸ[" + strInvoiceDetailId + "]�С�" + iJob + "������ҵδ��ɣ�";
                    }else if(iJobFH!=0){
                    	strMsg = "�õ���["+strInvoiceId+"]������ϸ[" + strInvoiceDetailId + "]�С�" + iJob + "������ҵδ���ˣ�";
                    }else{
                        //���ݵ��ݺŵĵ�����ϸ��ѯ��Ҫ��������ҵ��ϸ������ʣ�µ�
                        List ls = jobDAO.getRemainJobDetail(strInvoiceId, strInvoiceDetailId);
                        if(ls != null && ls.size()>0){
                            //����
                            strMsg = "�õ���["+strInvoiceId+"]������ϸ[" + strInvoiceDetailId + "]�С�" + ls.size() + "����ʣ���Ʒδ�Ƶ��ݴ�����";   
                            return strMsg;
                        }   
                        //��ⵥ����ϸ�Ŀ��������뷢������
                        //��������
                        Object[] numobj = jobDAO.getInvoiceDetailFinishNum(outDetail.getOutstockdetailid());
                        Double dNum = numobj[0] == null ? 0 : (Double)numobj[0];        //����
                        Double dWeight = numobj[1] == null ? 0 : (Double)numobj[1];     //ë��
                        Double dNetWeight = numobj[2] == null ? 0 : (Double)numobj[2];  //����
                        Double dVolume = numobj[3] == null ? 0 : (Double)numobj[3];     //���
                           
                        //������ϸ����ȷ�ϲ���
                        outDetail.setSendnum(dNum != null ? dNum.doubleValue(): 0);
                        outDetail.setSweight(dWeight != null ? dWeight.doubleValue(): 0);
                        outDetail.setSnetweight(dNetWeight != null ? dNetWeight.doubleValue(): 0);
                        outDetail.setSvolume(dVolume != null ? dVolume.doubleValue() : 0);
                        outDetail.setLinestatus("7");
                        String strInvoiceDetailSQL = "update OutboundInvoiceDetail set linestatus='7', sendnum="+(dNum != null ? dNum.doubleValue(): 0)+", sweight="+(dWeight != null ? dWeight.doubleValue(): 0)+",snetweight="+(dNetWeight != null ? dNetWeight.doubleValue(): 0)+",svolume="+(dVolume != null ? dVolume.doubleValue() : 0)+"  where outstockdetailid='" + outDetail.getOutstockdetailid() + "'";
                        sendDAO.outInvoiceDetailFHQR(strInvoiceDetailSQL);
       
                                
                        //����ȷ�� ���ж�
                        List lsDetail = sendDAO.getNoFHQRDetail(strInvoiceId);
                        if(lsDetail != null && lsDetail.size() == 0)
                        {
                            outInvoice.setConfirmdate(StrTools.getCurrDateTime(1));
                            outInvoice.setConfirmanid(strUserCode);
                            outInvoice.setOutstatus("7");
                            String strInvoiceSQL = "update OutboundInvoiceHeader set outstatus='7',confirmanid='" + strUserCode + "', confirmdate='" + StrTools.getCurrDateTime(1) + "' where outstockid='" + strInvoiceId + "'";
                            
                            //���ݷ���ȷ�ϲ���
                            sendDAO.outInvoiceDetailFHQR(strInvoiceSQL);   
                        }
                    }  
                }else{
                    strMsg = "�õ���[" + outDetail.getOutstockid() + "]�����ڻ򵥾�״̬��Ϊ����״̬���޷�����ȷ�ϣ�";
                } 
            }else{
                strMsg = "�õ�����ϸ[" + strInvoiceDetailId + "]�����ڻ��Ѿ�����ȷ���ˣ��޷����з���ȷ�ϣ�";
            }
        }
        return strMsg;
    }
    /**
     * ���ݵ���ID�͵�����ϸID�����ҵ����ҵ��ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.ISendBus#getJobAndJobDetail(java.lang.String, java.lang.String)
     */
    public List getJobAndJobDetail(String invoiceid, String invoicedetailid) throws Exception {
        return jobDAO.getJobAndJobDetail(invoiceid, invoicedetailid);
    }
    /**
     * ���ݲֿ�ID����ݴ���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.ISendBus#getZCInventory(java.lang.String)
     */
    public List getZCInventory(String warehouseid) throws Exception {   
        return sendDAO.getZCInventory(warehouseid);
    }
    /**
     * ���ݲֿ�ID����ݴ���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.ISendBus#getZCInventory(java.lang.String)
     */
    public List getZCInventorybyTray(String warehouseid,String zcwhareaid,String traycode) throws Exception {   
        return sendDAO.getZCInventorybyTray(warehouseid,zcwhareaid,traycode);
    }
    
    /**
     * ��������������������õ����ѳ������ҵ����ҵ��ϸ(�������ҵ)
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.ISendBus#getJobAndJobDetailByCode(java.lang.String, java.lang.String, java.lang.String)
     */
    public List getJobAndJobDetailByCode(String invoiceid, String traycode, String boxcode) throws Exception {
        return sendDAO.getJobAndJobDetailByCode(invoiceid, traycode, boxcode);
    }
    /**
     * ������ҵID�����ҵ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.ISendBus#getJobById(java.lang.String)
     */
    public InoutJob getJobById(String strJobId) throws Exception {
        return jobDAO.getJobById(strJobId);
    }
    /**
     * ������ҵ��ϸID�����ҵ��ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.ISendBus#getJobDetailByDetailId(java.lang.String)
     */
    public InoutJobdetail getJobDetailByDetailId(String strJobDetailId) throws Exception {
        return jobDAO.getJobDetailByDetailId(strJobDetailId);
    }


}
