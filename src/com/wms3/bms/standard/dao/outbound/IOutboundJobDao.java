package com.wms3.bms.standard.dao.outbound;


import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;


/**
 * 
 * ����: ������ҵ���ݿ����DAO��ӿ�
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public interface IOutboundJobDao extends IDao {

	/**
	 * ����:������ҵid��ѯ��ҵ
	 * @param jobid			��ҵ��
	 * @return 
	 * @throws Exception
	 */
	public InoutJob getJobByJobid(String jobid) throws Exception;
	
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
	 * ����:������ҵ
	 * @param job			��ҵ
	 * @param cargospace	��λ
	 * @param lsStorage		����б�
	 * @return 
	 * @throws Exception
	 */
	public void ZFJob(InoutJob job, BaseCargospace cargospace, List lsStorage,StringBuilder detailBuilder) throws Exception;
	/**
	 * ����:�����ݴ���ҵ
	 * @param job			��ҵ
	 * @param cargospace	��λ
	 * @param lsStorage		����б�
	 * @return 
	 * @throws Exception
	 */
	public void ZCJob(InoutJob job, BaseCargospace cargospace, InventoryStorage lsStorage,StringBuilder detailBuilder) throws Exception;

	/**
	 * ����:�ֶ������ҵ����ӿ��,�޸Ļ�λ״̬���ջ�λ
	 * @param job			��ҵ
	 * @param cargospace	��λ
	 * @param lsJobDetail	��ҵ��ϸ���б�
	 * @param lsStorage		�����б�
	 * @return 
	 * @throws Exception
	 */
	public void finishJob(InoutJob job,ScheduleTask task, BaseCargospace cargospace, List lsjobdetail, List lsStorage,List<OutboundInvoiceDetail> outBoundls) throws Exception;
	/**
	 * ����:�ֶ������ҵ����ӿ��,�޸Ļ�λ״̬���ջ�λ
	 * @param job			��ҵ
	 * @param cargospace	��λ
	 * @param lsJobDetail	��ҵ��ϸ���б�
	 * @param lsStorage		�����б�
	 * @return 
	 * @throws Exception
	 */
	public void finishJobupdate(InoutJob job, BaseCargospace cargospace, List lsjobdetail, List lsStorage,List<OutboundInvoiceDetail> outBoundls) throws Exception;
	/**
	 * ����:�ֶ������ҵ����ӿ��,�޸Ļ�λ״̬���ջ�λ
	 * @param job			��ҵ
	 * @param cargospace	��λ
	 * @param lsJobDetail	��ҵ��ϸ���б�
	 * @param lsStorage		�����б�
	 * @return 
	 * @throws Exception
	 */
	public void finishJobZC(InoutJob job, BaseCargospace cargospace, List lsjobdetail, List  lsStorage1,List lsStorage2,List<OutboundInvoiceDetail> outBoundls) throws Exception;
	/**
	 * ����:�ֶ������ҵ�����¿��,�޸Ļ�λ״̬Ϊ�ջ�λ,�޸ĵ���״̬
	 * @param job			��ҵ
	 * @param cargospace	��λ
	 * @param lsJobDetail	��ҵ��ϸ���б�
	 * @param lsStorage		�����б�
	 * @param lstask		���ȵ��б�
	 * @return 
	 * @throws Exception
	 */
	public void finishJobupdatez(InoutJob job, BaseCargospace cargospace, List lsjobdetail,ScheduleTask task, List lsStorage,List<OutboundInvoiceDetail> outBoundls) throws Exception;

}
