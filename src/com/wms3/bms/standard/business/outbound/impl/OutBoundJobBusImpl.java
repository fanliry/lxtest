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
 * ����: ������ҵ����ҵ����
 * 
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public class OutBoundJobBusImpl implements IOutBoundJobBus {

	/** DAO�� */
	protected IOutboundJobDao outboundJobDao;
	/** ����DAO�� */
	protected IAssginDao assginDao;

	public OutBoundJobBusImpl(EntityDAO dao) {
		assginDao = new AssginDaoImpl(dao);
		outboundJobDao = new OutboundJobDaoImpl(dao);
	}

	/**
	 * ����:����������ѯ������ҵ
	 * 
	 * @param warehouseid
	 *            �ֿ�
	 * @param whAreaId
	 *            ����
	 * @param productid
	 *            Ʒ��
	 * @param customerid
	 *            �ͻ�
	 * @param indate
	 *            ��ҵ����
	 * @param shiftid
	 *            ���
	 * @param jobid
	 *            ��ҵ��
	 * @param status
	 *            ��ҵ״̬
	 * @param tray_code
	 *            ��������
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

			if (warehouseid != null && warehouseid.trim().length() > 0) { // �ֿ�
				strBud.append(" and job.warehouseid='").append(warehouseid).append("'");
			}
			
			if (Virtualwhid != null && Virtualwhid.trim().length() > 0) { // �߼�����id
				strBud.append(" and job.Virtualwhid='").append(Virtualwhid).append("'");
			}

			if (whAreaId != null && whAreaId.trim().length() > 0) { // ����
				strBud.append(" and job.tcargoWhareaId='").append(whAreaId).append("'");
			}

			if (productid != null && productid.trim().length() > 0) { // Ʒ��
				strBud.append(" and job.jobid in " + "(select jobdetail.jobid from InoutJobdetail as jobdetail " + " where job.jobid=jobdetail.jobid and jobdetail.productid='").append(productid)
						.append("')");
			}

			if (customerid != null && customerid.trim().length() > 0) { // ����
				strBud.append(" and job.jobid in " + "(select jobdetail.jobid from InoutJobdetail as jobdetail " + " where job.jobid=jobdetail.jobid and jobdetail.customerid='").append(customerid)
						.append("')");
			}

			if (indate != null && indate.trim().length() > 0) { // ��ҵ����
				strBud.append(" and substring(job.createtime,1,10)='").append(indate).append("'");
			}

			if (jobid != null && jobid.trim().length() > 0) { // ��ҵ��
				strBud.append(" and job.jobid='").append(jobid).append("'");
			}

			if (status != null && status.trim().length() > 0) { // ��ҵ״̬
				strBud.append(" and job.status='").append(status).append("'");
			}

			if (shiftid != null && shiftid.trim().length() > 0) { // ���
				strBud.append(" and job.shiftid='").append(shiftid).append("'");
			}

			if (taskid != null && taskid.trim().length() > 0) { // ��������ID
				strBud.append(" and job.taskid='").append(taskid).append("'");
			}
			
			if (traycode != null && traycode.trim().length() > 0) { // ��������
				strBud.append(" and job.traycode='").append(traycode).append("'");
			}

			// ����ʽ
			String strHQL = strBud.toString() + " order by job.jobid desc";

			String strCountHQL = "select count(*)" + strBud.toString();

			pt = CommonPagination.getPagingTool(outboundJobDao.getEntityDAO(), strCountHQL, strHQL, strUrl, 1, maxLine);

		} catch (Exception er) {
			throw new Exception("����������ѯ��ѯ������ҵ�б����:" + er.getMessage());
		}

		return pt;
	}

	/**
	 * ����:������ҵid��ѯ��ҵ��ϸ
	 * 
	 * @param jobid
	 *            ��ҵ��
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
			throw new Exception("�������->��ѯ��ҵ��ϸ�б�ʱ�����" + er.getMessage());
		}

		return list;
	}

	/**
	 * ����:������ҵ����ҵ����
	 * 
	 * @param jobids
	 *            ��ҵID(�ɸ�����)
	 * @param strUserCode
	 *            �û�
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
						if (job.getJobcategory() != null && job.getJobcategory().equals("1")) { // �������ҵ����
							// �ж���ҵ״̬ 1.δִ�� 2.��ִ�� 3.��ִ�� 4.��� 5.���� 6.�쳣��ҵ 8.��ͣ��ҵ
							if (job.getStatus().equals("4") || job.getStatus().equals("5")) {

								if (strBackMsg.equals("Y")) {
									strBackMsg = "���������Ѿ���ɻ����Ѿ����ϵ�����ҵ��";

								} else {
									strBackMsg += "\r���������Ѿ���ɻ����Ѿ����ϵ�����ҵ��";
								}

							} else {

								// ��ȡ��ҵ��Ŀ���λ �޸ģ�֮ǰ��job.getTcargoSpaceId()������
								// :Դλ��job.getScargoSpaceId()
								cargospace = baseCargoSpaceDAO.getCargoSpaceById(job.getScargoSpaceId());
								cargospace.setCsstatus("2"); // ����λ
								job.setStatus("5"); // ����
								List lsStorage = inventoryDAO.getInventoryByCsId(job.getScargoSpaceId()); // ����б�
								if (job.getInvoicetype().equals("4")) { // �ƿ���ҵ��������Ƶ��ݴ棩
									// �޸ĳ��ⵥ��ϸ�ķ�������
									StringBuilder invoiceBudSQL = new StringBuilder();
									// ���浽���ݿ�,������ҵ���ָ�������״̬,�޸Ļ�λ״̬Ϊ�ջ�λ
									outboundJobDao.ZFJob(job, cargospace, lsStorage, invoiceBudSQL);
									Logger.info("�û���" + strUserCode + "������ҵ����ģ���������˳�����ҵ��" + jobid[i]);
								} else {
									// �޸ĳ��ⵥ��ϸ�ķ�������
									StringBuilder invoiceBudSQL = new StringBuilder();
									// ��Ҫ���������
									invoiceBudSQL.append("update OutboundInvoiceDetail set assignnum=assignnum-").append(jobDetail.getAssignnum()).append(" , assignweight=assignweight-")
											.append(jobDetail.getAssignweight()).append(" , assignnetweight=assignnetweight-").append(jobDetail.getAssignnetweight())
											.append(" , assignvolume=assignvolume-").append(jobDetail.getAssignvolume()).append(" where outstockdetailid='").append(job.getBoundstockdetailid())
											.append("'");

									lsStorage = null;
									// ���浽���ݿ�,������ҵ���ָ�������״̬,�޸Ļ�λ״̬Ϊ�ջ�λ
									outboundJobDao.ZFJob(job, cargospace, lsStorage, invoiceBudSQL);
									Logger.info("�û���" + strUserCode + "������ҵ����ģ���������˳�����ҵ��" + jobid[i]);
								}

							}
						} else if (job.getJobcategory() != null && job.getJobcategory().equals("2")) { // �ݴ�����ҵ
							// �ж���ҵ״̬ 1.δִ�� 2.��ִ�� 3.��ִ�� 4.��� 5.���� 6.�쳣��ҵ 8.��ͣ��ҵ
							if (job.getStatus().equals("4") || job.getStatus().equals("5")) {

								if (strBackMsg.equals("Y")) {
									strBackMsg = "���������Ѿ���ɻ����Ѿ����ϵ�����ҵ��";

								} else {
									strBackMsg += "\r���������Ѿ���ɻ����Ѿ����ϵ�����ҵ��";
								}

							} else {
								// ��ȡ��ҵ��Ŀ���λ �޸ģ�֮ǰ��job.getTcargoSpaceId()������
								// :Դλ��job.getScargoSpaceId()
								// cargospace =
								// baseCargoSpaceDAO.getCargoSpaceById(job.getScargoSpaceId());
								// cargospace.setCsstatus("2"); //����λ
								cargospace = null;
								job.setStatus("5"); // ����
								InventoryStorage lsStorage = inventoryDAO.getInventoryById(jobDetail.getInventoryid()); // ����б�
								if (lsStorage != null) {
									double num = lsStorage.getAssignnum() - jobDetail.getAssignnum();
									if (num < 0) {
										lsStorage.setAssignnum(0);
									} else {
										lsStorage.setAssignnum(num);
									}
								}
								// �޸ĳ��ⵥ��ϸ�ķ�������
								StringBuilder invoiceBudSQL = new StringBuilder();
								// ��Ҫ���������
								invoiceBudSQL.append("update OutboundInvoiceDetail set assignnum=assignnum-").append(jobDetail.getAssignnum()).append(" , assignweight=assignweight-")
										.append(jobDetail.getAssignweight()).append(" , assignnetweight=assignnetweight-").append(jobDetail.getAssignnetweight()).append(" , assignvolume=assignvolume-")
										.append(jobDetail.getAssignvolume()).append(" where outstockdetailid='").append(job.getBoundstockdetailid()).append("'");
								// ���浽���ݿ�,������ҵ���ָ�������״̬,�޸Ļ�λ״̬Ϊ�ջ�λ
								outboundJobDao.ZCJob(job, cargospace, lsStorage, invoiceBudSQL);
								Logger.info("�û���" + strUserCode + "������ҵ����ģ���������˳�����ҵ��" + jobid[i]);
							}
						} else {
							strBackMsg = "��ҵ���û���ҵ���";
						}
					} else {

						if (strBackMsg.equals("Y")) {
							strBackMsg = "��ҵ�����ڣ�";

						} else {
							strBackMsg += "\r��ҵ�����ڣ�";
						}
					}
					
				}
				
			
				
			}
		} catch (Exception e) {

			e.printStackTrace();
			strBackMsg = "������ҵ����ҵ����ʱ��������";
		}

		return strBackMsg;
	}

	/**
	 * ����:�ֶ������ҵ����ҵ����
	 * 
	 * @param jobids
	 *            ��ҵID(�ɸ�����)
	 * @param strUserCode
	 *            �û�
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
						if (job.getJobcategory() != null && job.getJobcategory().equals("1")) { // �������ҵ
							// �ж���ҵ״̬ 1.δִ�� 2.��ִ�� 3.��ִ�� 4.��� 5.���� 6.�쳣��ҵ 8.��ͣ��ҵ
							if (job.getStatus().equals("4") || job.getStatus().equals("5")) {
								if (strBackMsg.equals("Y")) {
									strBackMsg = "�����ֶ�����Ѿ���ɻ����Ѿ����ϵ�����ҵ��";
								} else {
									strBackMsg += "\r�����ֶ�����Ѿ���ɻ����Ѿ����ϵ�����ҵ��";
								}
							} else {
								String strTime = StrTools.getCurrDateTime(2); // ����ʱ��(��֤һ������ÿ������ʱ��һ��)
								// ��ȡ��ҵ��Ŀ���λ
								cargospace = baseCargoSpaceDAO.getCargoSpaceById(job.getScargoSpaceId());
								if (cargospace != null) {
									job.setStatus("4");
									job.setFinishtime(strTime);
									job.setOnLineType("2"); // �ѻ�
									ScheduleTask task = taskimp.getScheduleTaskBytraycode(job.getTraycode(), outboundJobDao.getEntityDAO());
						            task.setStatus("6");		//�޸ĵ���״̬ :  2.��ִ��   3.��ִ�� 4.��� 5.����  6.ͬ�����  8.ͬ������
									List lsjobdetail = getJobDetails(jobid[i]); // ��ҵ��ϸ�б�
									List lsStorage = null;
									cargospace.setCsstatus("1");// ��λ״̬1���ջ�λ��2������λ��3�������䣻4��������䣻5��������̵㣻6���������̵㣻7������ȡ����8���ѳ���
									lsStorage = inventoryDAO.getInventoryByCsId(job.getScargoSpaceId()); // ����б�
									// ���浽���ݿ�,�ֶ������ҵ,�޸ĵ���״̬��ɾ�����,�޸Ļ�λ״̬Ϊ�ջ�λ
									outboundJobDao.finishJob(job,task, cargospace, lsjobdetail, lsStorage, null);
									Logger.info("�û���" + strUserCode + "������ҵ����ģ�����ֶ�����˳�����ҵ��" + jobid[i]);
								} else {
									if (strBackMsg.equals("Y")) {
										strBackMsg = "��ҵ��" + jobid[i] + "���ֶ����ʧ�ܣ��޳����λ��";
									} else {
										strBackMsg += "\r��ҵ��" + jobid[i] + "���ֶ����ʧ�ܣ��޳����λ��";
									}
								}
							}
						} else {
							strBackMsg = "�����������ҵ��";
						}
					} else {
						if (strBackMsg.equals("Y")) {
							strBackMsg = "��ҵ�����ڣ�";

						} else {
							strBackMsg += "\r��ҵ�����ڣ�";
						}
					}
					
				}
			
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			strBackMsg = "�ֶ������ҵ����ҵ����ʱ��������";
		}
		return strBackMsg;
	}

	/**
	 * ����:�趨���ȼ���
	 * 
	 * @param jobids
	 *            ��ҵID(�ɸ�����)
	 * @param priority
	 *            ���ȼ���
	 * @param strUserCode
	 *            �û�
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
				Logger.info("�û�[" + strUserCode + "]����ҵ��[" + jobid[i] + "]�趨�����ȼ���:" + priority);
			}

		} catch (Exception e) {

			e.printStackTrace();
			strBackMsg = "�趨���ȼ���ʱ��������";
		}

		return strBackMsg;
	}

	/**
	 * ����:����������ѯ�����ҵ��ϸ
	 * 
	 * @param warehouseid
	 *            �ֿ�
	 * @param whAreaId
	 *            ����
	 * @param alleyId
	 *            ���
	 * @param cargospaceid
	 *            ��λ
	 * @param onlinetype
	 *            ���ģʽ
	 * @param indate_from
	 *            ��ҵ����
	 * @param indate_to
	 *            ��ҵ����
	 * @param shiftid
	 *            ���
	 * @param productid
	 *            Ʒ��
	 * @param customerid
	 *            �ͻ�
	 * @param tray_code
	 *            ��������
	 * @param lotid
	 *            ����id
	 * @param lotatt1
	 *            , lotatt2, lotatt3, lotatt4, lotatt5, lotatt6 ��������
	 * @param lotatt7
	 *            , lotatt8, lotatt9, lotatt10, lotatt11, lotatt12 ��������
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

			if (warehouseid != null && warehouseid.trim().length() > 0) { // �ֿ�
				strBud.append(" and job.warehouseid='").append(warehouseid).append("'");
			}

			if (whAreaId != null && whAreaId.trim().length() > 0) { // ����
				strBud.append(" and job.tcargoWhareaId='").append(whAreaId).append("'");
			}

			if (alleyId != null && alleyId.trim().length() > 0) { // ���
				strBud.append(" and job.tcargoAlleyId='").append(alleyId).append("'");
			}

			if (cargospaceid != null && cargospaceid.trim().length() > 0) { // ��λ
				strBud.append(" and job.tcargoSpaceId='").append(cargospaceid).append("'");
			}

			if (onlinetype != null && onlinetype.trim().length() > 0) { // ���ģʽ
				strBud.append(" and job.onLineType='").append(onlinetype).append("'");
			}

			if (indate_from != null && indate_from.trim().length() > 0) { // ��ҵ����
				strBud.append(" and job.createtime>='").append(indate_from).append("'");
			}
			if (indate_to != null && indate_to.trim().length() > 0) { // ��ҵ����
				strBud.append(" and job.createtime<='").append(indate_to).append(" 99:99:99'");
			}

			if (shiftid != null && shiftid.trim().length() > 0) { // ���
				strBud.append(" and job.shiftid='").append(shiftid).append("'");
			}

			if (productid != null && productid.trim().length() > 0) { // Ʒ��
				strBud.append(" and jobdetail.productid='").append(productid).append("'");
			}

			if (customerid != null && customerid.trim().length() > 0) { // �ͻ�
				strBud.append(" and jobdetail.customerid='").append(customerid).append("'");
			}

			if (traycode != null && traycode.trim().length() > 0) { // ��������
				strBud.append(" and job.traycode='").append(traycode).append("'");
			}

			// ��������ID�������
			ILotBus lotBus = new LotBusImpl(outboundJobDao.getEntityDAO());
			HashMap<String, BaseLotDetail> hsLot = lotBus.getHashMapByLotId(lotid);

			BaseLotDetail lotDetail = null; // ������ϸ

			// ��������
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
			throw new Exception("����������ѯ��ѯ�����ҵ�б����:" + er.getMessage());
		}

		return pt;
	}

	/**
	 * ����:����������Ե�SQL
	 * 
	 * @param flg
	 *            �ڼ�����������
	 * @param lotatt
	 *            �������Ե�ֵ
	 * @param lotDetail
	 *            ��������
	 * @return
	 * @throws Exception
	 */
	private String getSqlLotatt(String flg, String lotatt, BaseLotDetail lotDetail) {

		StringBuilder strBud = new StringBuilder();
		if (lotDetail != null) {

			// ҳ���ѯʱ���ģʽ 1-��ȷ��ѯ,2-ģ����ѯ(�ı���ʽ),3-��Χ��ѯ(���ڸ�ʽ)
			String strLotsearch = lotDetail.getM_strLotsearch();

			if (strLotsearch != null && strLotsearch.equals("1")) { // 1-��ȷ��ѯ
				strBud.append(" and jobdetail.lotatt").append(flg).append(" ='").append(lotatt).append("'");
			} else if (strLotsearch != null && strLotsearch.equals("2")) { // 2-ģ����ѯ
				strBud.append(" and jobdetail.lotatt").append(flg).append(" like '%").append(lotatt).append("%'");
			} else if (strLotsearch != null && strLotsearch.equals("3")) { // 3-��Χ��ѯ(���ڸ�ʽ)
				String[] lotatts = lotatt.split("\\|");
				if (lotatts.length > 0 && lotatts[0] != null && lotatts[0].trim().length() > 0) {
					strBud.append(" and jobdetail.lotatt").append(flg).append(" >='").append(lotatts[0]).append("'");
				}
				if (lotatts.length > 1 && lotatts[1] != null && lotatts[1].trim().length() > 0) {
					strBud.append(" and jobdetail.lotatt").append(flg).append(" <='").append(lotatts[1]).append("'");
				}

			} else { // û��ѡ���ѯ��ʽ��ʱ�򣬰��վ�ȷ��ѯ������
				strBud.append(" and jobdetail.lotatt").append(flg).append(" ='").append(lotatt).append("'");
			}
		}
		return strBud.toString();
	}
	
	/**
	 * ����:���ݵ�����ϸid��ѯ��ҵ��ϸ
	 * @param outstockdetailid			������ϸid
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
			throw new Exception("����ⵥ����->��ѯ��ҵ��ϸ�б�ʱ�����" + er.getMessage());
		}
			
		return list;
	}

}
