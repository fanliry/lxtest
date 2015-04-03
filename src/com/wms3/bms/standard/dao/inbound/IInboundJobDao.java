package com.wms3.bms.standard.dao.inbound;

import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * 
 * ����: �����ҵ���ݿ����DAO��ӿ�
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public interface IInboundJobDao extends IDao {

	/**
	 * ����:������ҵ����ҵ��ϸ��Ϣ���������,�����޸Ļ�λ״̬Ϊ����λ2
	 * @param cargospace	��λ
	 * @param job			��ҵ
	 * @param lsJobDetail	��ҵ��ϸ���б�
	 * @param lsStorage		�����б�
	 * @return 
	 * @throws Exception
	 */
	public void addRKWHJob(BaseCargospace cargospace, InoutJob job, List lsJobDetail, List lsStorage) throws Exception;

	/**
	 * ����:������ҵid��ѯ��ҵ
	 * @param jobid			��ҵ��
	 * @return 
	 * @throws Exception
	 */
	public InoutJob getJobByJobid(String jobid) throws Exception;
	
	/**
	 * ����:�޸���ҵ
	 * @param job			��ҵ
	 * @return 
	 * @throws Exception
	 */
	public void updateJob(InoutJob job) throws Exception;

	/**
	 * ����:������ҵ��ϸ�Ų�ѯ��ҵ��ϸ
	 * @param jobdetailid			��ҵ��ϸ��
	 * @return 
	 * @throws Exception
	 */
	public InoutJobdetail getJobDetailBydetailid(String jobdetailid) throws Exception;
	
	/**
	 * ����:�޸���ҵ��ϸ
	 * @param jobdetail			��ҵ��ϸ
	 * @return 
	 * @throws Exception
	 */
	public void updateJobDetail(InoutJobdetail jobdetail) throws Exception;

	/**
	 * ����:�޸���ҵ��ϸ
	 * @param job			��ҵ
	 * @param cargospace	��λ
	 * @param lsStorage		����б�
	 * @return 
	 * @throws Exception
	 */
	public void cancelJob(InoutJob job, BaseCargospace cargospace, List lsStorage) throws Exception;

	/**
	 * ����:�ֶ������ҵ����ӿ��,�޸Ļ�λ״̬���ջ�λ
	 * @param job			��ҵ
	 * @param cargospace	��λ
	 * @param lsJobDetail	��ҵ��ϸ���б�
	 * @param lsStorage		�����б�
	 * @return 
	 * @throws Exception
	 */
	public void finishJob(InoutJob job, BaseCargospace cargospace, List lsjobdetail, List lsStorage) throws Exception;

}
