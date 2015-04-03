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
 * �������ʼ�����ҵ��ʵ����
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
	  * ���ܣ����¿���쵥״̬�Ϳ��
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
			//��쵥��
			outBoundHeader = outBoundBus.getOutBoundById(id[i]);
			//��쵥��ϸ
			outBoundDetails = outBoundBus.getOutBoundDetailsById(id[i]);
			for (int j = 0; j < outBoundDetails.size(); j++) {
				//�����ҵ��ϸ
				List jobLs = jobDao.getPickJobDetail(id[i], outBoundDetails.get(j).getOutstockdetailid());
				if (jobLs!=null&&jobLs.size()>0) {					
					jobDel = (InoutJobdetail) jobLs.get(0);
					//��ÿ��
					iStorage = getInventById(jobDel.getInventoryid());
					if (iStorage!=null) {
					//����������Ϊ0��ɾ�����
					if (iStorage.getPnum()==0.0) {
						dao.delete(iStorage);
					}else {
						iStorage.setStatus("2");//���¿��״̬�Ѿ����
						dao.update(iStorage);
					}
					//���³�쵥��ϸ״̬
					outBoundDetails.get(j).setLinestatus("7");
					dao.update("OutboundInvoiceDetail",outBoundDetails.get(j));	
				}else {
					strMeg = "��ȡ�����Ϣʧ�ܣ�";
					Logger.info("�û���" + userCode + "�����ʼ����ģ��������˳�쵥�ݣ�" + id[i]+"��״̬��ȡ���ʧ��");
					break;
				}
			 }else {
				 strMeg = "�õ���û�ж�Ӧ����ҵ��";
				 Logger.info("�û���" + userCode + "�����ʼ����ģ��������˳�쵥�ݣ�" + id[i]+"��״̬���ݳ�쵥ID��ȡ��ҵʧ��");
				 return strMeg;
			}
			}
			outBoundHeader.setOutstatus("7");
			dao.update("OutboundInvoiceHeader",outBoundHeader);	
			strMeg = "���³ɹ���";
			Logger.info("�û���" + userCode + "�����ʼ����ģ��������˳�쵥�ݣ�" + id[i]+"��״̬");
		}
		
		} catch (Exception e) {
		throw new Exception("���³�쵥״̬����,QualityMgrBusImpl.updateCheckBoundUpdateInvent��" + e.getMessage());
		}
		return strMeg;
	}
	/**
	  * ���ܣ����Ͽ���쵥״̬�Ϳ��
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
			//��쵥��
			outBoundHeader = outBoundBus.getOutBoundById(id[i]);
			if (outBoundHeader!=null&&outBoundHeader.getOutstatus().equals("7")) {
				strMeg = "����ȷ�ϵĵ��ݲ������ϣ�";
				return strMeg;
			} else {
			//��쵥��ϸ
			outBoundDetails = outBoundBus.getOutBoundDetailsById(id[i]);
			for (int j = 0; j < outBoundDetails.size(); j++) {
				//�����ҵ��ϸ
				List jobLs = jobDao.getPickJobDetail(id[i], outBoundDetails.get(j).getOutstockdetailid());
				if (jobLs!=null&&jobLs.size()>0) {					
					jobDel = (InoutJobdetail) jobLs.get(0);
					//��ÿ��
					iStorage = getInventById(jobDel.getInventoryid());
					if (iStorage!=null) {
					iStorage.setPnum(jobDel.getAssignnum());
					iStorage.setStatus("0");//��ԭ���״̬��δ����
				    dao.update(iStorage);
					
				}else {
					strMeg = "��ȡ�����Ϣʧ�ܣ�";
					Logger.info("�û���" + userCode + "�����ʼ����ģ�������ϳ�쵥�ݣ�" + id[i]+"��״̬��ȡ���ʧ��");
					return strMeg;
				}
			 }else {
				 strMeg = "�õ���û�ж�Ӧ����ҵ��";
				 Logger.info("�û���" + userCode + "�����ʼ����ģ�������ϳ�쵥�ݣ�" + id[i]+"��״̬���ݳ�쵥ID��ȡ��ҵʧ��");
				 return strMeg;
			}
				//���³�쵥��ϸ״̬
				outBoundDetails.get(j).setLinestatus("8");
				dao.update("OutboundInvoiceDetail",outBoundDetails.get(j));	
			}
			outBoundHeader.setOutstatus("8");
			dao.update("OutboundInvoiceHeader",outBoundHeader);	
			strMeg = "���ϳɹ���";
			Logger.info("�û���" + userCode + "�����ʼ����ģ���������˳�쵥�ݣ�" + id[i]);
		}
		}
		
		} catch (Exception e) {
		throw new Exception("���³�쵥״̬����,QualityMgrBusImpl.updateCheckBoundUpdateInvent��" + e.getMessage());
		}
		return strMeg;
	}
	/**
	 * ���ܣ����ݿ��ID��ÿ��
	 * 
	 */
	public InventoryStorage getInventById(String id) throws Exception {
		
		return inventDao.getInventoryById(id);
	}
	
}
