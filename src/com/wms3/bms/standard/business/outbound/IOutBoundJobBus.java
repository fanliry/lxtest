package com.wms3.bms.standard.business.outbound;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;

/**
 * 
 * ����: ������ҵ����ӿ���
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public interface IOutBoundJobBus {

	/**
	 * ����:����������ѯ������ҵ
	 * @param warehouseid	�ֿ�
	 * @param whAreaId		����
	 * @param productid		Ʒ��
	 * @param customerid	�ͻ�
	 * @param indate		��ҵ����
	 * @param shiftid		���
	 * @param jobid			��ҵ��
	 * @param status		��ҵ״̬
	 * @param tray_code		��������
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getOutboundJobs(String warehouseid, String Virtualwhid, String whAreaId, String productid, String customerid, String indate, String shiftid, 
			String jobid, String status, String taskid, String traycode,  String strUrl, int maxLine) throws Exception;

	/**
	 * ����:������ҵid��ѯ��ҵ��ϸ
	 * @param jobid			��ҵ��
	 * @return 
	 * @throws Exception
	 */
	public List getJobDetails(String jobid) throws Exception;
	
	/**
	 * ����:������ҵ����ҵ����
	 * @param jobids		��ҵID(�ɸ�����)
	 * @param strUserCode	�û�
	 * @return 
	 * @throws Exception
	 */
	public String cancelJobs(String jobids, String strUserCode) throws Exception;

	/**
	 * ����:�ֶ������ҵ����ҵ����
	 * @param jobids		��ҵID(�ɸ�����)
	 * @param strUserCode	�û�
	 * @return 
	 * @throws Exception
	 */
	public String finishJobs(String jobids, String strUserCode) throws Exception;

	/**
	 * ����:�趨���ȼ���
	 * @param jobids		��ҵID(�ɸ�����)
	 * @param priority		���ȼ���
	 * @param strUserCode	�û�
	 * @return 
	 * @throws Exception
	 */
	public String updJobStatus(String jobids, String priority, String strUserCode) throws Exception;

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
	 * @param productid		Ʒ��
	 * @param customerid	�ͻ�
	 * @param tray_code		��������
	 * @param lotid			����id
     * @param lotatt1, lotatt2, lotatt3, lotatt4, lotatt5, lotatt6      ��������
     * @param lotatt7, lotatt8, lotatt9, lotatt10, lotatt11, lotatt12   ��������
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getOutboundJobDetails(String warehouseid, String whAreaId, String alleyId, String cargospaceid, String onlinetype, 
			String indate_from, String indate_to, String shiftid, String productid, String customerid, String traycode, 
			String lotid, String lotatt1, String lotatt2, String lotatt3, String lotatt4, String lotatt5, String lotatt6, String lotatt7, 
			String lotatt8, String lotatt9, String lotatt10, String lotatt11, String lotatt12, String strUrl, int maxLine) throws Exception;
	
	/**
	 * ����:���ݵ�����ϸid��ѯ��ҵ��ϸ
	 * @param outstockdetailid			������ϸid
	 * @return 
	 * @throws Exception
	 */
	public List getJobDetailsAndJobByOutboundDid(String outstockdetailid) throws Exception;
	
}
