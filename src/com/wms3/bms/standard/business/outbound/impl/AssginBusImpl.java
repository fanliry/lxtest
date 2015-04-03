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
 * ����: �������ҵ��
 * @author hug 2012-9-20
 * @since WMS 3.0
 */
public class AssginBusImpl implements IAssginBus {
    /** ����DAO�� */
    protected IAssginDao assginDao;
    /** ��λDAO��ӿ� */
    protected IBaseCargoSpaceDao spaceDao;

    /** ���ⵥDAO��  */
    protected IOutboundDao outBoundDAO;
    //���
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
        //StringBuilder strGrpBud = new StringBuilder(); //Groupby���
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
                                
            //�������Զ�̬����
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
            
            //����*********************
            strBud.append(" from InventoryStorage sto where 1=1 ");
            
            //��ѯ����*where����****************************************
            //�ֿ�ID
            if(warehouseId != null && warehouseId.trim().length() > 0){
                strBud.append(" and sto.warehouseid='").append(warehouseId).append("'");
            }
            //����ID
            if(whAreaId != null && whAreaId.trim().length() > 0){ 
                strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
            }
            //����
            if(ownerid != null && ownerid.trim().length()>0){
                strBud.append(" and sto.ownerId='").append(ownerid).append("'");
            } 
            //Ʒ��ID
            if(strProductId != null && strProductId.trim().length() > 0){
                strBud.append(" and sto.productid='").append(strProductId).append("'");
            }
            //��װ
            if(strPackId != null && strPackId.trim().length() > 0){
                strBud.append(" and sto.packid='").append(strPackId).append("'");
            }
            //��������1
            if(strLotatt1 != null && strLotatt1.trim().length() > 0){
                strBud.append(" and sto.lotatt1='").append(strLotatt1).append("'");
            }
            //��������2
            if(strLotatt2 != null && strLotatt2.trim().length() > 0){
                strBud.append(" and sto.lotatt2='").append(strLotatt2).append("'");
            }
            //��������3
            if(strLotatt3 != null && strLotatt3.trim().length() > 0){
                strBud.append(" and sto.lotatt3='").append(strLotatt3).append("'");
            }
            //��������4
            if(strLotatt4 != null && strLotatt4.trim().length() > 0){
                strBud.append(" and sto.lotatt4='").append(strLotatt4).append("'");
            }
            //��������5
            if(strLotatt5 != null && strLotatt5.trim().length() > 0){
                strBud.append(" and sto.lotatt5='").append(strLotatt5).append("'");
            }
            //��������6
            if(strLotatt6 != null && strLotatt6.trim().length() > 0){
                strBud.append(" and sto.lotatt6='").append(strLotatt6).append("'");
            }
            //��������7
            if(strLotatt7 != null && strLotatt7.trim().length() > 0){
                strBud.append(" and sto.lotatt7='").append(strLotatt7).append("'");
            }
            //��������8
            if(strLotatt8 != null && strLotatt8.trim().length() > 0){
                strBud.append(" and sto.lotatt8='").append(strLotatt8).append("'");
            }
            //��������9
            if(strLotatt9 != null && strLotatt9.trim().length() > 0){
                strBud.append(" and sto.lotatt9='").append(strLotatt9).append("'");
            }
            //��������10
            if(strLotatt10 != null && strLotatt10.trim().length() > 0){
                strBud.append(" and sto.lotatt10='").append(strLotatt10).append("'");
            }
            //��������11
            if(strLotatt11 != null && strLotatt11.trim().length() > 0){
                strBud.append(" and sto.lotatt11='").append(strLotatt11).append("'");
            }
            //��������12
            if(strLotatt12 != null && strLotatt12.trim().length() > 0){
                strBud.append(" and sto.lotatt12='").append(strLotatt12).append("'");
            }
            //���ID
            if(alleyId != null && alleyId.trim().length() > 0){
                strBud.append(" and sto.cargoAlleyId='").append(alleyId).append("'");
            }
            //��������
            if(strTrayCode != null && strTrayCode.trim().length() > 0){
                strBud.append(" and sto.traycode='").append(strTrayCode).append("'");
            }//
            //����
            strBud.append(" and (sto.pnum-sto.assignnum-sto.holdnum)>0");
            //����
           // strBud.append(strGrpBud.toString());
            //����
            strBud.append(" order by sto.cargoSpaceId ");
            
            lsStorage = assginDao.querySQL(strBud.toString());
            
        }catch(Exception er){
            throw new Exception("��������ѯ������" + er.getMessage());
        }
        return lsStorage;
    }
    
    /**
     * ����: ��������ѯ���
     * @author fanll 2014-7-25
     * @since lxyy
     */
    public List<?> getAssginQueryStorage(String warehouseId, String whAreaId, String alleyId, String strProductId, String traycode,String status) throws Exception {
        StringBuilder strBud = new StringBuilder();
        List lsStorage = null;
        try{

            //����*********************
            strBud.append(" from InventoryStorage sto,BaseCargospace space where 1=1 ");
            
            //��ѯ����*where����****************************************
           
            //�ֿ�ID
            if(warehouseId != null && warehouseId.trim().length() > 0){
                strBud.append(" and sto.warehouseid='").append(warehouseId).append("'");
            }
            //����ID
            if(whAreaId != null && whAreaId.trim().length() > 0){ 
                strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
            }
            //���ID
            if(alleyId != null && alleyId.trim().length() > 0){
                strBud.append(" and sto.cargoAlleyId='").append(alleyId).append("'");
            }
            //Ʒ��ID
            if(strProductId != null && strProductId.trim().length() > 0){
                strBud.append(" and sto.productid='").append(strProductId).append("'");
            }
            //��������
            if(traycode != null && traycode.trim().length() > 0){
                strBud.append(" and sto.traycode='").append(traycode).append("'");
            }
            //��Ʒ״̬
            if(status != null && status.trim().length() > 0){
                strBud.append(" and sto.productstatus in (").append(status).append(")");
            }
            //����
            strBud.append(" and space.outlock!='Y'"); //������ס�Ĳ��ܽ��з���
            
            //�ж��Ƿ��ݴ�������ݴ�������Է�����λ���⣩
            if(whAreaId.equals("001003")){             //   �ݴ�������  001003 ���ֶ����ã�
                strBud.append(" and (sto.pnum-sto.assignnum)>0 and sto.cargoSpaceId = space.cargoSpaceId and sto.status in('0','1') "); //��λΪ����λ״̬�Ŀ��
            }else{
            	strBud.append(" and (sto.pnum-sto.assignnum)>0 and sto.cargoSpaceId = space.cargoSpaceId and space.csstatus='2' and sto.status in('0','1') "); //��λΪ����λ״̬�Ŀ��
            }
            //����
            strBud.append(" order by sto.indate ");
            
            lsStorage = assginDao.querySQL(strBud.toString());
            
        }catch(Exception er){
            throw new Exception("��������ѯ������" + er.getMessage());
        }
        return lsStorage;
    }
	/**
	 * ����: �ֶ�����
	 * @author hug 2012-11-15 
	 * @param invoiceid         ���ⵥID
	 * @param invoicedetailid   ���ⵥ��ϸID
	 * @param strStoMsg         ��λ������б�
	 * @param strUserCode
	 * @return
	 * @throws Exception
	 */
	public String assginStorageNew(String invoiceid, String invoicedetailid, String strStoMsg, String strUserCode,String fpnumresult) throws Exception{

		String strMsg = "Y";
		synchronized (assgin_lock) {
			//���ⵥ��ϸ
			OutboundInvoiceDetail outDetail = outBoundDAO.getOutBoundDetailById(invoicedetailid);
			OutboundInvoiceHeader invoice = outBoundDAO.getOutBoundById(invoiceid);
			if(outDetail != null && !outDetail.getLinestatus().equals("7") && invoice!=null && !invoice.getOutstatus().equals("7")){//��Ϊ����״̬
				//��ʽ�� �ͻ��˹�����Ŀ����Ϣ
				String strStorage = parseAssginStr(strStoMsg);
				Logger.info(strUserCode+"��ʽ�����˹��������Ϣ:" + strStorage);
				if(strStorage != null && strStorage.length()>1){
					String[] spaces = strStorage.split("\\|");
					String flag = "";    //��λ1:��������a[���ID1��������ë�أ����أ����;���ID2��������ë�أ����أ����]��������b[���ID1��������ë�أ����أ����;���ID2��������ë�أ����أ����]
					String spaceid = ""; //��λID
					String strTrays = "";//��������a[���ID1��������ë�أ����أ����;���ID2��������ë�أ����أ����]��������b[���ID1��������ë�أ����أ����;���ID2��������ë�أ����أ����]
					String flag2 = "";   //��������a[���ID1��������ë�أ����أ����;���ID2��������ë�أ����أ����
					String traycode = "";//��������
					String strStorages = "";//���ID1��������ë�أ����أ����;���ID2��������ë�أ����أ����
					String flag3 = "";   //���ID1��������ë�أ����أ����
					String strStorageId;//���ID
					double num;         //����
					double weight;      //ë��
					double netweight;   //����
					double volume;      //���
					for(int i=0; i<spaces.length; i++){
						flag = spaces[i];
						String[] space = flag.split(":");
						spaceid = space[0];//��λID
						strTrays = space[1]; //����������Ϣ
						//��֤**********************************************************************
						OutAllotVerifyRequestBean requestBean = new OutAllotVerifyRequestBean();
						requestBean.setCargoSpaceId(spaceid);
						String[] tray = strTrays.split("\\]"); 
						for(int j=0; j< tray.length; j++){
							flag2 = tray[j];         
							traycode = flag2.substring(0, flag2.indexOf("["));//��������
							strStorages = flag2.substring(flag2.indexOf("[")+1, flag2.length());//���ID�б�

							OutAllotVerifyRequestBean.TrayStorageBean traystorageBean = requestBean.getNewTrayStorageBean();
							traystorageBean.setTrayCode(traycode);//��������

							String[] storage = strStorages.split(";");
							for(int k = 0; k < storage.length; k++){
								flag3 = storage[k]; //���ID1��������ë�أ����أ����
								String[] invetnroys = flag3.split(",");
								strStorageId = invetnroys[0];//���ID
								num = Double.parseDouble((invetnroys[1]==null?"":invetnroys[1]));         //����
								weight = Double.parseDouble((invetnroys[2]==null?"":invetnroys[2]));      //ë��
								netweight = Double.parseDouble((invetnroys[3]==null?"":invetnroys[3]));   //����
								volume = Double.parseDouble((invetnroys[4]==null?"":invetnroys[4]));      //���

								OutAllotVerifyRequestBean.TrayStorageBean.StorageBean storageBean = traystorageBean.getNewStorageBean();
								storageBean.setInventoryid(strStorageId);//���ID
								storageBean.setNum(num);        //����
								storageBean.setWeight(weight);  //ë��
								storageBean.setNetweight(netweight);//����
								storageBean.setVolume(volume);  //���     
								traystorageBean.addStorageBean(storageBean);
							} 
							requestBean.addTrayStorageBean(traystorageBean);
						}

						//��֤��������Ϣ*************************************************************************
						//��û�λ����                        
						BaseCargospace baseSpace = spaceDao.getCargoSpaceById(spaceid);//��λ             
						//���ݿ���ID�ж��Ƿ���Ҫ���ɵ�������
						boolean bTask = assginDao.isCreateTask(baseSpace.getWhAreaId());

						List<InoutJob> lsJob = new ArrayList<InoutJob>();//������ҵ
						List<InoutJobdetail> lsJobDetail = new ArrayList<InoutJobdetail>();//���ӵ���ҵ��ϸ 
						List<String> lsStorageSQL = new ArrayList<String>();//�޸Ŀ���SQL
						List<String> lsInvoiceSQL = new ArrayList<String>();//�޸ĳ��ⵥ��ϸ��SQL
						for(int m=0; m < requestBean.getTrayStorageBeanSize(); m++){
							OutAllotVerifyRequestBean.TrayStorageBean traystorageBean = requestBean.getListTrayStorageBean().get(m);
							String strTrayCode = traystorageBean.getTrayCode();//��������

							//���ӳ�����ҵ
							InoutJob job = new InoutJob();
							job.setJobtype("2");        //ҵ����1-���2-����3-�ƿ�
							if(bTask){ //���ɵ�������
								job.setOnLineType("1");     //����ģʽ 1.���� 2.�ѻ�
							}else{ 
								job.setOnLineType("2");     //����ģʽ 1.���� 2.�ѻ�
							}

							job.setIsaccount("Y");      //�Ƿ���� Y.�� N.��
							job.setStatus("1");         //��ҵ״̬ 1.δִ�� 2.��ִ�� 3.��ִ�� 4.��� 5.���� 6.�쳣��ҵ 8.��ͣ��ҵ


							job.setPriority(10);        //���ȼ�
							job.setCreatetime(StrTools.getCurrDateTime(2));//����ʱ��
							job.setJobpos("2");             //��ҵ���� 1.���� 2.�����
							//job.setShiftid(strShiftId);   //��ҵ���
							job.setTraycode(strTrayCode);   //��������
							job.setOpManId(strUserCode);    //������
							job.setInOutType("2");          //������� 1�ϼ���� 2.����
							job.setInvoicetype("2");        //1:������ҵ�����ɵ���;2:���е��ݺ�������ҵ
							//�����ҵID   �ջ���ҵSJZY ��ǰ׺ SJ

							BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(assginDao.getEntityDAO());
							BaseSeq  seqEn = seqDao.getSeqByType("CKZY");
							String strID = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), assginDao.getEntityDAO());

							String ss ="";
							job.setJobid(strID); //������ҵID
							job.setWarehouseid(baseSpace.getWarehouseid());    //�ֿ�ID
							job.setScargoSpaceId(baseSpace.getCargoSpaceId()); //Դ��λID
							job.setScargoAlleyId(baseSpace.getCargoAlleyId()); //Դ���ID
							job.setScargoWhareaId(baseSpace.getWhAreaId());    //Դ����ID
							job.setBoundstockdetailid(invoicedetailid);        //���ⵥ��ϸID      
							job.setBoundstockid(invoiceid); //���ⵥID
							if(bTask){ //���ɵ�������
								job.setJobcategory("1"); //�Զ����������ҵ
							}else{ 
								job.setJobcategory("2"); //�ݴ�����ҵ
							}

							//���ݻ�λID�����������øû�λ�ϵ����п���б�
							List<InventoryStorage> lsStorage = assginDao.getInventoryStorageBySpaceId(spaceid, strTrayCode);
							InventoryStorage storage = null;
							for(int ii=0; ii<lsStorage.size(); ii++){
								storage = lsStorage.get(ii);
								OutAllotVerifyRequestBean.TrayStorageBean.StorageBean storageBean = findStorageBean(storage.getInventoryid(), traystorageBean.getListStorageBean());

								if(storageBean == null){
									continue;
								}
								//���ӳ�����ҵ��ϸ  �������
								InoutJobdetail jobDetail = new InoutJobdetail();
								//��ҵ��ϸID
								String strJobDetailId = SeqTool.getDetailId(strID, String.valueOf(i));
								jobDetail.setJobdetailid(strJobDetailId);   //��ҵ��ϸID
								jobDetail.setJobid(strID);                  //��ҵID
								jobDetail.setLinestatus("0");               //״̬0:�½�2���
								jobDetail.setInventoryid(storage.getInventoryid()); //���ID
								jobDetail.setProductid(storage.getProductid());     //Ʒ��
								jobDetail.setPackid(storage.getPackid());   //��װID
								jobDetail.setPunit(storage.getPunit());     //��λ
								jobDetail.setLotid(storage.getLotid());     //��������ID
								jobDetail.setLotinfo(storage.getLotinfo()); //����
								jobDetail.setLotinfo(storage.getProductdate());//��������
								// jobDetail.setJobnum(storage.getPnum() - storage.getAssignnum() - storage.getHoldnum());           //����
								jobDetail.setJobnum(storage.getPnum());           //����
								jobDetail.setNetweight(storage.getPnetweight());  //����
								jobDetail.setWeight(storage.getPweight());        //ë��
								jobDetail.setVolume(storage.getPvolume());        //���   
								/*if(storageBean != null){ //��Ҫ���������
                                        jobDetail.setAssignnum(storageBean.getNum());             //��������
                                        jobDetail.setAssignnetweight(storageBean.getNetweight()); //���侻��
                                        jobDetail.setAssignweight(storageBean.getWeight());       //����ë��
                                        jobDetail.setAssignvolume(storageBean.getVolume());       //�������
                                    }else{   //����Ҫ�����
                                        jobDetail.setAssignnum(0);          //��������
                                        jobDetail.setAssignnetweight(0);    //���侻��
                                        jobDetail.setAssignweight(0);       //����ë��
                                        jobDetail.setAssignvolume(0);       //�������
                                    }*/
								jobDetail.setAssignnum(storageBean.getNum());             //��������
								jobDetail.setAssignnetweight(storageBean.getNetweight()); //���侻��
								jobDetail.setAssignweight(storageBean.getWeight());       //����ë��
								jobDetail.setAssignvolume(storageBean.getVolume());       //�������

								jobDetail.setOwnerId(storage.getOwnerId());     //����
								jobDetail.setIsinvoice("Y");                    //�Ƿ������ɵ���
								jobDetail.setInjobdetailid(storage.getInjobetailid());//Դ��ҵ��ϸ�����ɳ�����ҵʱ����Ӹÿ��������ҵ��
								//���ӳ�����ҵ��ϸ
								lsJobDetail.add(jobDetail);

								//�޸Ŀ���״̬�����ķ�������
								StringBuilder storageBudSQL= new StringBuilder();
								storageBudSQL.append("update InventoryStorage set ");
								/*if(storageBean != null){ //��Ҫ���������
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

								//�����޸Ŀ���SQL
								lsStorageSQL.add(storageBudSQL.toString());
								//�޸ĳ��ⵥ��ϸ�ķ�������
								StringBuilder invoiceBudSQL= new StringBuilder();
								/*if(storageBean != null){ //��Ҫ���������
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
								//�����޸ĳ��ⵥ��ϸ��SQL
								lsInvoiceSQL.add(invoiceBudSQL.toString());
							} 
							//������ҵ
							lsJob.add(job);
						}
						//���ɳ�����ҵ���Ƿ���Ҫ���ɳ��������ҵ
						//�޸ĳ��ⵥ��ϸ�ķ�������  
						//�޸Ļ�λ״̬
						String strSpaceSQL = null;
						//�������Ҫ�޸Ļ�λ״̬Ϊ�������״̬
						if(bTask){ //���ɵ�������
							strSpaceSQL = "update BaseCargospace set csstatus='4' where cargoSpaceId='" + spaceid + "'"; //�Զ����������ҵ
						}else{ 
							strSpaceSQL = "update BaseCargospace set csstatus='2' where cargoSpaceId='" + spaceid + "'";  //�ݴ�����ҵ  �ݴ���һֱΪ����λ
						}

						//���ɳ�����ҵ���Ƿ���Ҫ���ɳ��������ҵ,�޸ĳ��ⵥ��ϸ�ķ�������************************************************************
						assginDao.addAssginJob(lsJob, null, lsJobDetail, lsStorageSQL, lsInvoiceSQL, strSpaceSQL,invoice); 
					}    
				}else{
					strMsg = "û��ѡ�еĻ�λ��Ϣ���޷����䣡"; 
				}

			}else{
				strMsg = "���ⵥ["+invoiceid+"]�ĳ��ⵥ��ϸ["+invoicedetailid+"]�������ⵥ��ϸ�ѷ���ȷ����!";
			}
		} 
		return strMsg;
	}	
	
	public String assginStorage(String invoiceid, String invoicedetailid, String strStoMsg, String floor, String tsjh, String strUserCode) throws Exception {
		String strMsg = "Y";
		// synchronized (assgin_lock) {
		// ���ⵥ��ϸ
		OutboundInvoiceDetail outDetail = outBoundDAO.getOutBoundDetailById(invoicedetailid);
		OutboundInvoiceHeader invoice = outBoundDAO.getOutBoundById(invoiceid);
		IBaseInoutControlBus ControlBus = new BaseInoutControlBusImpl(outBoundDAO.getEntityDAO());
		String inoutcontr = "";
		
		if (outDetail != null && !outDetail.getLinestatus().equals("7") && invoice != null && !invoice.getOutstatus().equals("7")) {// ��Ϊ����״̬
			BaseInoutControl InoutControl = ControlBus.getInoutControl(); //��������
			if(InoutControl != null){
				inoutcontr = InoutControl.getCon_type();
			}
			boolean successflag = true;
			invoice.setOutstatus("2"); // ���ó��ⵥΪ����״̬
			// ��ʽ�� �ͻ��˹�����Ŀ����Ϣ
			String strStorage = parseAssginStr(strStoMsg);
			Logger.info(strUserCode + "��ʽ�����˹��������Ϣ:" + strStorage);
			if (strStorage != null && strStorage.length() > 1) {
				String[] spaces = strStorage.split("\\|");
				String flag = ""; // ��λ1:��������a[���ID1��������ë�أ����أ����;���ID2��������ë�أ����أ����]��������b[���ID1��������ë�أ����أ����;���ID2��������ë�أ����أ����]
				String spaceid = ""; // ��λID
				String strTrays = "";// ��������a[���ID1��������ë�أ����أ����;���ID2��������ë�أ����أ����]��������b[���ID1��������ë�أ����أ����;���ID2��������ë�أ����أ����]
				String flag2 = ""; // ��������a[���ID1��������ë�أ����أ����;���ID2��������ë�أ����أ����
				String traycode = "";// ��������
				String strStorages = "";// ���ID1��������ë�أ����أ����;���ID2��������ë�أ����أ����
				String flag3 = ""; // ���ID1��������ë�أ����أ����
				String strStorageId;// ���ID
				double num; // ����
				double fpnum; // ��������
				for (int i = 0; i < spaces.length; i++) {
					flag = spaces[i];
					String[] space = flag.split(":");
					spaceid = space[0];// ��λID
					strTrays = space[1]; // ����������Ϣ
					// ��֤**********************************************************************
					OutAllotVerifyRequestBean requestBean = new OutAllotVerifyRequestBean();
					requestBean.setCargoSpaceId(spaceid);
					String[] tray = strTrays.split("\\]");
					for (int j = 0; j < tray.length; j++) {
						flag2 = tray[j];
						traycode = flag2.substring(0, flag2.indexOf("["));// ��������
						strStorages = flag2.substring(flag2.indexOf("[") + 1, flag2.length());// ���ID�б�

						OutAllotVerifyRequestBean.TrayStorageBean traystorageBean = requestBean.getNewTrayStorageBean();
						traystorageBean.setTrayCode(traycode);// ��������

						String[] storage = strStorages.split(";");
						for (int k = 0; k < storage.length; k++) {
							flag3 = storage[k]; // ���ID1����������������
							String[] invetnroys = flag3.split(",");
							strStorageId = invetnroys[0];// ���ID
							num = Double.parseDouble((invetnroys[1] == null ? "" : invetnroys[1])); // ����
							fpnum = Double.parseDouble((invetnroys[2] == null ? "" : invetnroys[2])); // ��������

							OutAllotVerifyRequestBean.TrayStorageBean.StorageBean storageBean = traystorageBean.getNewStorageBean();
							storageBean.setInventoryid(strStorageId);// ���ID
							storageBean.setNum(fpnum); // ��������
							traystorageBean.addStorageBean(storageBean);
						}
						requestBean.addTrayStorageBean(traystorageBean);
					}

					// ��֤��������Ϣ*************************************************************************
					// ��û�λ����
					BaseCargospace baseSpace = spaceDao.getCargoSpaceById(spaceid);// ��λ
					// ���ݿ���ID�ж��Ƿ���Ҫ���ɵ�������
					boolean bTask = assginDao.isCreateTask(baseSpace.getWhAreaId());

					List<InoutJob> lsJob = new ArrayList<InoutJob>();// ������ҵ
					List<InoutJobdetail> lsJobDetail = new ArrayList<InoutJobdetail>();// ���ӵ���ҵ��ϸ
					List<String> lsStorageSQL = new ArrayList<String>();// �޸Ŀ���SQL
					List<String> lsInvoiceSQL = new ArrayList<String>();// �޸ĳ��ⵥ��ϸ��SQL
					List<ScheduleTask> lsTask = null;
					if (bTask) { // ������
						lsTask = new ArrayList<ScheduleTask>();
					}
					for (int m = 0; m < requestBean.getTrayStorageBeanSize(); m++) {
						OutAllotVerifyRequestBean.TrayStorageBean traystorageBean = requestBean.getListTrayStorageBean().get(m);
						String strTrayCode = traystorageBean.getTrayCode();// ��������
						// ���ӳ�����ҵ
						InoutJob job = new InoutJob();
						job.setJobtype(invoice.getOuttype()); // ��ҵ����
						if (bTask) { // ���ɵ�������
							job.setOnLineType("1"); // ����ģʽ 1.���� 2.�ѻ�
						} else {
							job.setOnLineType("2"); // ����ģʽ 1.���� 2.�ѻ�
						}
						job.setIsaccount("Y"); // �Ƿ���� Y.�� N.��
						job.setStatus("1"); // ��ҵ״̬ 1.δִ�� 2.��ִ�� 3.��ִ�� 4.��� 5.����    6.�쳣��ҵ 8.��ͣ��ҵ
						job.setPriority(10); // ���ȼ�
						job.setCreatetime(StrTools.getCurrDateTime(2));// ����ʱ��
						job.setJobpos("2"); // ��ҵ���� 1.���� 2.�����
						job.setTraycode(strTrayCode); // ��������
						job.setOpManId(strUserCode); // ������
						job.setInOutType("2"); // ������� 1�ϼ���� 2.����
						job.setInvoicetype("2"); // 1:������ҵ�����ɵ���;2:���е��ݺ�������ҵ
						// �����ҵID �ջ���ҵSJZY ��ǰ׺ SJ

						BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(assginDao.getEntityDAO());
						BaseSeq seqEn = seqDao.getSeqByType("CKZY");
						String strID = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(), seqEn.getIcodelength(), assginDao.getEntityDAO());

						String ss = "";
						job.setJobid(strID); // ������ҵID
						job.setWarehouseid(baseSpace.getWarehouseid()); // �ֿ�ID
						job.setScargoSpaceId(baseSpace.getCargoSpaceId()); // Դ��λID
						job.setScargoAlleyId(baseSpace.getCargoAlleyId()); // Դ���ID
						job.setScargoWhareaId(baseSpace.getWhAreaId()); // Դ����ID
						job.setBoundstockdetailid(invoicedetailid); // ���ⵥ��ϸID
						job.setBoundstockid(invoiceid); // ���ⵥID

						if (bTask) { // ���ɵ�������
							
							job.setJobcategory("1"); // �Զ����������ҵ
							if(inoutcontr!=null && inoutcontr.equals("1")){ //�����
								strMsg = "������Ѿ����أ�����ֻ����⣡";
								successflag  = false;
								break;
							}
						} else {
							job.setJobcategory("2"); // �ݴ�����ҵ
						}

						// ���ݻ�λID�����������øû�λ�ϵ����п���б�
						List<InventoryStorage> lsStorage = assginDao.getInventoryStorageBySpaceId(spaceid, strTrayCode);
						InventoryStorage storage = null;
						for (int ii = 0; ii < lsStorage.size(); ii++) {
							storage = lsStorage.get(ii);
							OutAllotVerifyRequestBean.TrayStorageBean.StorageBean storageBean = findStorageBean(storage.getInventoryid(), traystorageBean.getListStorageBean());
							if (storageBean == null) {
								continue;
							}
							// ���ӳ�����ҵ��ϸ �������
							InoutJobdetail jobDetail = new InoutJobdetail();
							// ��ҵ��ϸID
							String strJobDetailId = SeqTool.getDetailId(strID, String.valueOf(i));
							job.setBoundrequeststockid(storage.getRequestid()); // ������뵥��

							jobDetail.setJobdetailid(strJobDetailId); // ��ҵ��ϸID
							jobDetail.setJobid(strID); // ��ҵID
							jobDetail.setLinestatus("0"); // ״̬0:�½�2���
							jobDetail.setInventoryid(storage.getInventoryid()); // ���ID
							jobDetail.setProductid(storage.getProductid()); // Ʒ��
							jobDetail.setPackid(storage.getPackid()); // ��װID
							jobDetail.setPunit(storage.getPunit()); // ��λ
							jobDetail.setLotid(storage.getLotid()); // ��������ID
							jobDetail.setLotinfo(storage.getLotinfo()); // ����
							jobDetail.setPrintdate(storage.getProductdate());// ��������
							jobDetail.setProductStatus(storage.getProductstatus()); // ��Ʒ״̬
							jobDetail.setProductcode(storage.getProductcode()); // ��Ʒ����
							jobDetail.setLotinfo2(storage.getLotinfo2());

							if (bTask) { // �����
								jobDetail.setJobnum(storage.getPnum()); // ����
							} else { // �ݴ���
								jobDetail.setJobnum(storage.getPnum() - storage.getAssignnum() - storage.getHoldnum()); // ����
							}

							jobDetail.setNetweight(storage.getPnetweight()); // ����
							jobDetail.setWeight(storage.getPweight()); // ë��
							jobDetail.setVolume(storage.getPvolume()); // ���
							jobDetail.setAssignnum(storageBean.getNum()); // ��������
							jobDetail.setAssignnetweight(storageBean.getNetweight()); // ���侻��
							jobDetail.setAssignweight(storageBean.getWeight()); // ����ë��
							jobDetail.setAssignvolume(storageBean.getVolume()); // �������

							ScheduleTask task = null;

							if (bTask) { // ��Ҫ���ɵ������� ������λ��Ҫ��������
								task = new ScheduleTask();

								String strTaskId = SeqTool.getID("RW", m_dao); // ��ҵID
								task.setTaskid(strTaskId);// taskid; ����������
								job.setTaskid(strTaskId); // ��������ID
								task.setJobid(job.getJobid());
								task.setTasktype("2"); // �������� 1-��� 2-���� 3-�ƿ�
								task.setSplatoon(baseSpace.getCsplatoon()); // Դ��λ��
								task.setScolumn(baseSpace.getCscolumn()); // Դ��λ��
								task.setSfloor(baseSpace.getCsfloor()); // Դ��λ��
								task.setCargoSpaceId(baseSpace.getCargoSpaceId());// ��λID
								task.setCargoAlleyId(baseSpace.getCargoAlleyId());// ���ID
								task.setWarehouseid(baseSpace.getWarehouseid()); // �ֿ�ID
								task.setWhAreaId(baseSpace.getWhAreaId()); // ����ID
								task.setCargoSpaceId(baseSpace.getCargoSpaceId());
								task.setStatus("2"); // ����״̬
								task.setPriority(10); // ���ȼ�
								task.setCreatetime(StrTools.getCurrDateTime(2)); // ʱ��
																					// ʱ���ʽ��yyyy-MM-dd
																					// hh:mm:ss
								task.setTraycode(strTrayCode); // ��������
								task.setTaskpos("2"); // ������ 1.���� 2.�����
								task.setRoute("1"); // ִ��·�� 1-ֱ��/ֱ�� 2-����
								task.setFloor(floor);
								task.setPhase(1);
								task.setJobtype(job.getJobtype());
								task.setTSJH(tsjh);
								
								// ���ӵ���
								lsTask.add(task);
							}

							jobDetail.setOwnerId(storage.getOwnerId()); // ����
							jobDetail.setIsinvoice("Y"); // �Ƿ������ɵ���
							jobDetail.setInjobdetailid(storage.getInjobetailid());// Դ��ҵ��ϸ�����ɳ�����ҵʱ����Ӹÿ��������ҵ��
							// ���ӳ�����ҵ��ϸ
							lsJobDetail.add(jobDetail);
							// �޸��ݴ���ķ�������
							StringBuilder storageBudSQL = new StringBuilder();
							if (bTask) { // �����
								storageBudSQL.append("update InventoryStorage inventoryS set inventoryS.status='1' where inventoryS.inventoryid='" + storage.getInventoryid() + "'");
								// �����޸Ŀ���SQL
								lsStorageSQL.add(storageBudSQL.toString());
							} else { // �ݴ��� ��Ҫ�޸ķ�������
								storageBudSQL.append("update InventoryStorage inventoryS set ");
								storageBudSQL.append(" inventoryS.assignnum=inventoryS.assignnum+" + storageBean.getNum()).append(" , inventoryS.status='1' ")
										.append(" , inventoryS.assignweight=inventoryS.assignweight+" + storageBean.getWeight())
										.append(" ,inventoryS.assignnetweight=inventoryS.assignnetweight+" + storageBean.getNetweight())
										.append(" , inventoryS.assignvolume=inventoryS.assignvolume+" + storageBean.getVolume());
								storageBudSQL.append("  where inventoryS.inventoryid='").append(storage.getInventoryid()).append("'");

								// �����޸Ŀ���SQL
								lsStorageSQL.add(storageBudSQL.toString());
							}

						}
						// ������ҵ
						lsJob.add(job);
					}
					if(successflag){
						// ���ɳ�����ҵ���Ƿ���Ҫ���ɳ��������ҵ
						// �޸ĳ��ⵥ��ϸ�ķ�������
						// �޸Ļ�λ״̬
						String strSpaceSQL = null;
						// �������Ҫ�޸Ļ�λ״̬Ϊ�������״̬
						if (bTask) { // ���ɵ�������
							strSpaceSQL = "update BaseCargospace set csstatus='4' where cargoSpaceId='" + spaceid + "'"; // �Զ����������ҵ
						} else {
							strSpaceSQL = "update BaseCargospace set csstatus='2' where cargoSpaceId='" + spaceid + "'"; // �ݴ�����ҵ
																															// �ݴ���һֱΪ����λ
						}

						// ���ɳ�����ҵ���Ƿ���Ҫ���ɳ��������ҵ,�޸ĳ��ⵥ��ϸ�ķ�������************************************************************
						assginDao.addAssginJob(lsJob, lsTask, lsJobDetail, lsStorageSQL, lsInvoiceSQL, strSpaceSQL, invoice);
					}else{
						break;
					}
					
				}
			} else {
				strMsg = "û��ѡ�еĻ�λ��Ϣ���޷����䣡";
			}

		} else {
			strMsg = "���ⵥ[" + invoiceid + "]�ĳ��ⵥ��ϸ[" + invoicedetailid + "]�������ⵥ��ϸ�ѷ���ȷ����!";
		}
		return strMsg;
	}
    /**
     * ����: ���ҿ��
     * @author hug 2012-9-29 
     * @param strInventoryId    ���ID
     * @param lsStroageBean     ���ID�б�
     * @return
     */
    public OutAllotVerifyRequestBean.TrayStorageBean.StorageBean findStorageBean(String strInventoryId, List<OutAllotVerifyRequestBean.TrayStorageBean.StorageBean> lsStroageBean){        
        OutAllotVerifyRequestBean.TrayStorageBean.StorageBean storageBean = null;
        if(lsStroageBean != null){
            for(int i=0; i<lsStroageBean.size(); i++){
                storageBean = lsStroageBean.get(i);
                if(strInventoryId.equals(storageBean.getInventoryid())){//��������ID
                    break;
                }else{
                    storageBean = null;
                }
            }    
        }  
        return storageBean;
    }
    /** 
     * ����: �����˹������λ����Ϣ
     * @author hug 2012-9-25 
     * @param strMsg
     * @return
     */
    public String parseAssginStr(String strMsg)
    {
        //Դ*******************************
        //��λ1:��������a[���ID1��������ë�أ����أ����;���ID2��������ë�أ����أ����]��������b[���ID1��������ë�أ����أ����;���ID2��������ë�أ����أ����]| 
        //��λ2:��������c[���ID1��������ë�أ����أ����;���ID2��������ë�أ����أ����] |
        //��λ3:��������d[���ID1��������ë�أ����أ����;���ID2��������ë�أ����أ����] |
        
        //����*****************************
        //strMsg =  "001:Y-000008[���1,����1,ë��1,����1,���1;���2,����2,ë��2,����2,���2]|" + 
        //          "002:Y-000089[���ID2,����,ë��,����,���;���ID3,����,ë��,����,���]|" +
        //          "001:Y-000008[���3,����3,ë��3,����3,���3;���2,����,ë��,����,���]|" +
        //          "001:Y-000001[���1,����5,ë��5,����5,���5]|" +
    	//          "001:Y-000001[���2,����5,ë��5,����5,���5]|" +
    	//001:Y-000001[���1,����5,ë��5,����5,���5;���2,����5,ë��5,����5,���5]
        //          "001:Y-000008[���8,����8,ë��8,����8,���3;���2,����,ë��,����,���;���9,����9,ë��9,����9,���9;���7,����7,ë��7,����7,���7]";
        //���˳�򣺻�λ:����[���1;���1
        String distinctStr = "";
        String flag = "";//���ŷָ������ݣ���λ|���ID��
        String flag2 = "";
        String spaceid = "";//��λID
        String[] newstr = strMsg.split("\\|");
        int strLen = newstr.length;//����
        for(int i=0; i< strLen; i++)
        {
            //��λ2:��������c[���ID1��������ë�أ����أ����;���ID2��������ë�أ����أ����]
            flag = newstr[i];
            if(flag.length() > 0){
                String[] space = flag.split(":");
                spaceid = space[0];//��λID
                for(int j=i+1; j < strLen; j++){
                    flag2 = newstr[j];
                    String[] space2 = flag2.split(":");
                    String spaceid2 = space2[0];             
                    if (spaceid2.equals(spaceid) && flag.length() != 0)
                    {
                        //��ͬ��λ�ĺ���һ��
                        newstr[i] = newstr[i] + space2[1];
                        newstr[j] = ""; 
                    }
                }       
                //�ϲ���λ����ͬ������
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
     * ����:��ͬ���̵ĺ���һ��
     * @author hug 2012-9-25 
     * @param strTrays  �����ַ���
     * @return
     */
    public String parseTrayStr(String strTrays){
        //��ʽ  strMsg = "Y-000008[���1,����1,ë��1,����1,���1;���2,����2,ë��2,����2,���2]Y-000008[���3,����3,ë��3,����3,���3;���2,����,ë��,����,���]";
        //����  strMsg = "Y-000008[���1,����1,ë��1,����1,���1;���2,����2,ë��2,����2,���2;���3,����3,ë��3,����3,���3;���2,����,ë��,����,���]";
        String strValue = "";
        String flag = "";
        String flag2 = "";
        String traycode = "";  //��������
        String[] tray = strTrays.split("\\]");
        int strLen = tray.length;//����
        for(int i=0; i< strLen; i++)
        {
            flag = tray[i];
            if(flag.length() >1){
                traycode = flag.substring(0, flag.indexOf("["));//��������
                for(int j=i+1; j < strLen; j++){
                    flag2 = tray[j];
                    System.out.println(flag2);
                    if(flag2.length() >0){
                        String traycode2 = flag2.substring(0, flag2.indexOf("["));//��������
                        String strStorage2 = flag2.substring(flag2.indexOf("[")+1, flag2.length());//���ID�б�
                        if (traycode2.equals(traycode) )
                        {
                            //��ͬ���̵ĺ���һ�� 
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
     * ����: ȡ���ַ�����ͬ�Ŀ��ID
     * @author hug 2012-9-25 
     * @param strStoMsg �ַ���
     * @return
     */
    public String distinctStorageId(String strStoMsg){
        //��ʽ  strStoMsg=���1,����1,ë��1,����1,���1;���2,����2,ë��2,����2,���2;���3,����3,ë��3,����3,���3;���2,����,ë��,����,���";
        //���  return=   ���1,����1,ë��1,����1,���1;���2,����2,ë��2,����2,���2;���3,����3,ë��3,����3,���3    
        String strValue = "";
        String flag = "";
        String[] storages = strStoMsg.split(";");
        int strLen = storages.length;//����
        for(int i=0; i< strLen; i++){
            flag = storages[i];
            if(flag.length() > 0){
                //���ID
                String spaceid = flag.split(",")[0];
                for(int j=i+1; j < strLen; j++){
                    String spaceid2 = (storages[j].split(","))[0];
                    if (spaceid2.equals(spaceid))
                    {
                        //��ͬ���ID��ȥ��
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
            //���ⵥ��ϸ
            OutboundInvoiceDetail outDetail = outBoundDAO.getOutBoundDetailById(invoicedetailid);
            OutboundInvoiceHeader invoice = outBoundDAO.getOutBoundById(invoiceid);
            if(outDetail != null && !outDetail.getLinestatus().equals("7") && invoice!=null && (!invoice.getOutstatus().equals("7")||!invoice.getOutstatus().equals("8"))){//��Ϊ����״̬
                if(outDetail.getInvoicenum() - outDetail.getAssignnum() > 0){
                    OutAllotRequestBean outRequest = new OutAllotRequestBean();
                    outRequest.setOutstockid(invoiceid);            //���ⵥ�ݺ�
                    outRequest.setOutstockdetailid(invoicedetailid);//���ⵥ��ϸID
                    outRequest.setWarehouseid(warehouseId); //�ֿ�ID
                    outRequest.setWhAreaId(whAreaId);       //����ID
                    outRequest.setOwnerid(ownerid);         //����
                    outRequest.setCustomerid(customerid);      //�ͻ�ID(�ջ���)
                    
                    OutAllotRequestBean.ProductBean productBean = outRequest.newProductBean();
                    productBean.setProductid(outDetail.getProductid()); //��Ʒ
                    productBean.setPackid(outDetail.getPackid());       //��װ
                    productBean.setEunit(outDetail.getPkgunit());       //��λ
                    productBean.setNum(outDetail.getInvoicenum() - outDetail.getAssignnum());           //��Ҫ���������
                    productBean.setNetweight(outDetail.getNetweight() - outDetail.getAssignnetweight());//��Ҫ����ľ���
                    productBean.setWeight(outDetail.getWeight() - outDetail.getAssignweight());         //��Ҫ�����ë��
                    productBean.setVolume(outDetail.getVolume() - outDetail.getAssignvolume());         //��Ҫ����ľ���
                    productBean.setLotid(outDetail.getLotid());      //��������ID
                    productBean.setLotatt1(outDetail.getLotatt1());  //��������1
                    productBean.setLotatt2(outDetail.getLotatt2());  //��������2
                    productBean.setLotatt3(outDetail.getLotatt3());  //��������3
                    productBean.setLotatt4(outDetail.getLotatt4());  //��������4
                    productBean.setLotatt5(outDetail.getLotatt5());  //��������5
                    productBean.setLotatt6(outDetail.getLotatt6());  //��������6
                    productBean.setLotatt7(outDetail.getLotatt7());  //��������7
                    productBean.setLotatt8(outDetail.getLotatt8());  //��������8
                    productBean.setLotatt9(outDetail.getLotatt9());  //��������9
                    productBean.setLotatt10(outDetail.getLotatt10());//��������10
                    productBean.setLotatt11(outDetail.getLotatt11());//��������11
                    productBean.setLotatt12(outDetail.getLotatt12());//��������12
                    outRequest.setProductBean(productBean);
                    
                    //���ݿ���ID�ж��Ƿ���Ҫ���ɵ�������
                    // boolean bTask = assginDao.isCreateTask(whAreaId);
                    List<InoutJob> lsJob = new ArrayList<InoutJob>();//������ҵ
                    /*List<ScheduleTask> lsTask = null;
                    if(bTask){ //������
                        lsTask = new ArrayList<ScheduleTask>();
                    }*/
                    List<InoutJobdetail> lsJobDetail = new ArrayList<InoutJobdetail>();//���ӵ���ҵ��ϸ 
                    List<String> lsStorageSQL = new ArrayList<String>();//�޸Ŀ���SQL
                    List<String> lsInvoiceSQL = new ArrayList<String>();//�޸ĳ��ⵥ��ϸ��SQL
                    
                    //���س��������*******************************************************************************
                    List<OutAllotResponseBean> lsAllot = null;
                    if(lsAllot != null){
                        try{
                            OutAllotResponseBean outResponseBean = null; //���������Ӧ����
                            for(int i = 0; i < lsAllot.size(); i++){
                                outResponseBean =  lsAllot.get(i);   
                                //��û�λ����
                                BaseCargospace baseSpace = spaceDao.getCargoSpaceById(outResponseBean.getCargoSpaceId());//��λ 
                                          
                                //������Ϣ
                                List<OutAllotResponseBean.TrayStorageBean> lsTray = outResponseBean.getListTrayStorageBean();
                                if(lsTray != null){
                                    OutAllotResponseBean.TrayStorageBean trayBean = null;
                                    String strTrayCode = null;  //��������
                                    for(int j = 0; j < lsTray.size(); j++){
                                        trayBean = lsTray.get(j);
                                        strTrayCode = trayBean.getTrayCode();
                                        
                                        //���ӳ�����ҵ
                                        InoutJob job = new InoutJob();
                                        job.setJobtype("2");        //ҵ����1-���2-����3-�ƿ�
                                        job.setOnLineType("1");     //����ģʽ 1.���� 2.�ѻ�
                                        job.setIsaccount("Y");      //�Ƿ���� Y.�� N.��
                                        job.setStatus("1");         //��ҵ״̬ 1.δִ�� 2.��ִ�� 3.��ִ�� 4.��� 5.���� 6.�쳣��ҵ 8.��ͣ��ҵ
                                        job.setPriority(10);        //���ȼ�
                                        job.setCreatetime(StrTools.getCurrDateTime(2));//����ʱ��
                                        job.setJobpos("2");             //��ҵ���� 1.���� 2.�����
                                        //job.setShiftid(strShiftId);   //��ҵ���
                                        job.setTraycode(strTrayCode);   //��������
                                        job.setOpManId(strUserCode);    //������
                                        job.setInOutType("2");          //������� 1�ϼ���� 2.����
                                        job.setInvoicetype("2");        //1:������ҵ�����ɵ���;2:���е��ݺ�������ҵ
                                        //�����ҵID   �ջ���ҵSJZY ��ǰ׺ SJ
                                        BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(assginDao.getEntityDAO());
        				                BaseSeq  seqEn = seqDao.getSeqByType("CKZY");
        				                String strID = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), assginDao.getEntityDAO());

                                        job.setJobid(strID); //������ҵID
                                        job.setWarehouseid(outResponseBean.getWarehouseid());    //�ֿ�ID
                                        job.setScargoSpaceId(outResponseBean.getCargoSpaceId()); //Դ��λID
                                        job.setScargoAlleyId(outResponseBean.getCargoAlleyId()); //Դ���ID
                                        job.setScargoWhareaId(outResponseBean.getWhAreaId());    //Դ����ID
                                        job.setBoundstockid(invoiceid);//���ⵥID
                                        job.setBoundstockdetailid(invoicedetailid);//���ⵥ��ϸID
                                        //job.setOldspacestatus(outResponseBean.getOldstatus());   //����ǰ��λ״̬
                                                   
                                            //���ؿ����Ϣ  �û�λ�����еĿ��ID
                                            List<OutAllotResponseBean.TrayStorageBean.StorageBean> lsStorage = trayBean.getListStorageBean();
                                            if(lsStorage != null){ 
                                                OutAllotResponseBean.TrayStorageBean.StorageBean storageBean = null;
                                                String strInventoryId = null;   //���ID
                                                for(int k = 0; k < lsStorage.size(); k++){ 
                                                    storageBean = lsStorage.get(k);
                                                    strInventoryId = storageBean.getInventoryid(); //���ID
                                                    
                                                    InventoryStorage storage = assginDao.getInventoryStorageById(strInventoryId);
                                                    //���ӳ�����ҵ��ϸ  �������
                                                    InoutJobdetail jobDetail = new InoutJobdetail();
                                                    //��ҵ��ϸID
                                                    String strJobDetailId = SeqTool.getDetailId(strID, String.valueOf(i));
                                                    jobDetail.setJobdetailid(strJobDetailId);   //��ҵ��ϸID
                                                    jobDetail.setJobid(strID);                  //��ҵID
                                                    jobDetail.setLinestatus("0");               //״̬0:�½�2���
                                                    jobDetail.setInventoryid(storage.getInventoryid()); //���ID
                                                    jobDetail.setProductid(storage.getProductid());     //Ʒ��
                                                    jobDetail.setPackid(storage.getPackid());   //��װID
                                                    jobDetail.setPunit(storage.getPunit());     //��λ
                                                    jobDetail.setLotid(storage.getLotid());     //��������ID
                                                    
                                                    jobDetail.setJobnum(storage.getPnum() - storage.getHoldnum());           //����
                                                    jobDetail.setNetweight(storage.getPnetweight() - storage.getHoldnetweight());  //����
                                                    jobDetail.setWeight(storage.getPweight() - storage.getHoldweight());        //ë��
                                                    jobDetail.setVolume(storage.getPvolume() - storage.getHoldvolume());        //���
                                                    
                                                    jobDetail.setAssignnum(storageBean.getNum());             //��������
                                                    jobDetail.setAssignnetweight(storageBean.getNetweight()); //���侻��
                                                    jobDetail.setAssignweight(storageBean.getWeight());       //����ë��
                                                    jobDetail.setAssignvolume(storageBean.getVolume());       //�������
                                                    
                                                    
                                                    jobDetail.setOwnerId(storage.getOwnerId());     //����
                                                    jobDetail.setIsinvoice("Y");                    //�Ƿ������ɵ���
                                                    jobDetail.setInjobdetailid(storage.getInjobetailid());//Դ��ҵ��ϸ�����ɳ�����ҵʱ����Ӹÿ��������ҵ��
                                                    //���ӳ�����ҵ��ϸ
                                                    lsJobDetail.add(jobDetail); 
                                                    //�޸Ŀ���״̬�����ķ�������
                                                    StringBuilder storageBudSQL= new StringBuilder();
                                                    storageBudSQL.append("update InventoryStorage set status='1' ");
                                                    if(storageBean != null){ //��Ҫ���������
                                                        storageBudSQL.append(", assignnum=assignnum+"+storageBean.getNum())
                                                        .append(" , assignweight=assignweight+"+storageBean.getWeight())
                                                        .append(" ,assignnetweight=assignnetweight+" + storageBean.getNetweight())
                                                        .append(" , assignvolume=assignvolume+"+storageBean.getVolume());
                                                    }
                                                    storageBudSQL.append("  where inventoryid='").append(storage.getInventoryid()).append("'");
                                                    //�����޸Ŀ���SQL
                                                    lsStorageSQL.add(storageBudSQL.toString());
                                                    
                                                    //�޸ĳ��ⵥ��ϸ�ķ�������
                                                    StringBuilder invoiceBudSQL= new StringBuilder();
                                                    invoiceBudSQL.append("update OutboundInvoiceDetail set linestatus='2', assignnum=assignnum+").append(storageBean.getNum())
                                                    .append(" , assignweight=assignweight+").append(storageBean.getWeight())
                                                    .append(" , assignnetweight=assignnetweight+").append(storageBean.getNetweight())
                                                    .append(" , assignvolume=assignvolume+").append(storageBean.getVolume())
                                                    .append(" where outstockdetailid='").append(invoicedetailid).append("'");
                                                    //�����޸ĳ��ⵥ��ϸ��SQL
                                                    lsInvoiceSQL.add(invoiceBudSQL.toString());
                                                } 
                                            } 
                                        //������ҵ
                                        lsJob.add(job);   
                                    }
                                }
                                //���Ŀ��״̬�ͻ�λ״̬������Ҫ�жϸû�λ�ĵ����Ƿ������ɡ�
                                //ʣ�µĿ�治�ܣ����״̬Ҳ���ܡ�
                                //�¼�ʱ,������ҵͬʱ��ɣ����Ҫȫ���¼ܣ�ʣ���Ʒ�ŵ��ݴ���
                                //�޸ĳ��ⵥ��ϸ�ķ�������  
                                //�޸Ļ�λ״̬
                                String strSpaceSQL = null;
                                //if(bTask){//�������Ҫ�޸Ļ�λ״̬Ϊ�������״̬
                                    strSpaceSQL = "update BaseCargospace set csstatus='4' where cargoSpaceId='" + outResponseBean.getCargoSpaceId() + "'";
                                //}   
                               //���õ���Ϊ�������״̬
                               invoice.setOutstatus("2");
                                //���ɳ�����ҵ���Ƿ���Ҫ���ɳ��������ҵ,�޸ĳ��ⵥ��ϸ�ķ�������************************************************************
                               //assginDao.addAssginJob(lsJob, lsTask, lsJobDetail, lsStorageSQL, lsInvoiceSQL, strSpaceSQL); 
                               assginDao.addAssginJob(lsJob, null, lsJobDetail, lsStorageSQL, lsInvoiceSQL, strSpaceSQL,invoice); 
                            }    
                        }catch(Exception er){
                            //���ɳ�����ҵʧ�ܣ�Ҫ��ԭ��λ����ǰ��״̬,��Ҫ����Щ����
                            throw new Exception("���ɳ�����ҵʧ�ܣ�" + er.getMessage());   
                        }     
                    }else{
                        strMsg = "���ⵥ["+invoiceid+"]�ĳ��ⵥ��ϸ["+invoicedetailid+"]����ʧ��!";
                    }    
                }else{
                    strMsg = "���ⵥ["+invoiceid+"]�ĳ��ⵥ��ϸ["+invoicedetailid+"][����:"+outDetail.getInvoicenum()+"][����:"+outDetail.getAssignnum()+"]�ѷ�����������ٷ���!";
                }
            }else{
                strMsg = "���ⵥ["+invoiceid+"]�ĳ��ⵥ��ϸ["+invoicedetailid+"]�������ⵥ��ϸ�ѷ���ȷ����!";
            }
        }
        return strMsg;
    }

}

