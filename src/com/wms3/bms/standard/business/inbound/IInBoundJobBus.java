package com.wms3.bms.standard.business.inbound;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * 
 * ����: ���
 * ��ҵ����ӿ���
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public interface IInBoundJobBus {

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
			String strUrl, int maxLine) throws Exception;

	/**
	 * ����:������ҵid��ѯ��ҵ��ϸ
	 * @param jobid			��ҵ��
	 * @return 
	 * @throws Exception
	 */
	public List getJobDetails(String jobid) throws Exception;

	/**
	 * ����:������ҵ����ҵ��ϸ
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
			String indate, String shiftid, String jobdetails, String strUserCode) throws Exception;

	/**
	 * ����:������ҵid��ѯ��ҵ
	 * @param jobid			��ҵ��
	 * @return 
	 * @throws Exception
	 */
	public InoutJob getJobByJobid(String jobid) throws Exception;

	/**
	 * ����:�޸���ҵ�����ά����
	 * @param jobid			��ҵID
	 * @param jobtype		��ҵ����
	 * @param tray_code		��������
	 * @return 
	 * @throws Exception
	 */
	public String updateRKWHJob(String jobid, String jobtype, String traycode) throws Exception;

	/**
	 * ����:������ҵ��ϸ�Ų�ѯ��ҵ��ϸ
	 * @param jobdetailid			��ҵ��ϸ��
	 * @return 
	 * @throws Exception
	 */
	public InoutJobdetail getJobDetailBydetailid(String jobdetailid) throws Exception;

	/**
	 * ����:�޸���ҵ��ϸ�����ά����
	 * @param jobdetailid	��ҵ��ϸ��
	 * @param key			��ҵ��ϸ����
	 * @return 
	 * @throws Exception
	 */
	public String updateRKWHJobDetail(String jobdetailid, String key) throws Exception;

	/**
	 * ����:������ҵ�����ά����
	 * @param jobid			��ҵID
	 * @return 
	 * @throws Exception
	 */
	public String cancelRKWHJob(String jobid) throws Exception;

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
			String lotatt11, String lotatt12, String strUrl, int maxLine) throws Exception;

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
	 * ���ܣ���ȡ���������ȫ����ҵ
	 * @param warehouseid
	 * @param whAreaId
	 * @param alleyId
	 * @param indate_from
	 * @param indate_to
	 * @param invoicetype
	 * @param productid
	 * @param ownerid
	 * @param traycode
	 * @param lotid
	 * @param strUrl
	 * @param boundstockid
	 * @param groupinfo
	 * @param customer_id
	 * @param intype
	 * @return
	 * @throws Exception
	 */
	public List getInboundJobDetailsGroupListByIn(String warehouseid, String whAreaId, String alleyId,String indate_from, String indate_to,
			String invoicetype, String productid, String ownerid, String traycode, String lotid, String strUrl,String boundstockid,String groupinfo,String customer_id,String intype) throws Exception; 
	
	/**
	 *  ���ܣ���ȡ�����ĳ���ȫ����ҵ
	 * @param warehouseid
	 * @param whAreaId
	 * @param alleyId
	 * @param indate_from
	 * @param indate_to
	 * @param invoicetype
	 * @param productid
	 * @param ownerid
	 * @param traycode
	 * @param lotid
	 * @param strUrl
	 * @param boundstockid
	 * @param groupinfo
	 * @param customer_id
	 * @param outtype
	 * @return
	 * @throws Exception
	 */
	public List getInboundJobDetailsGroupListByOut(String warehouseid, String whAreaId, String alleyId,String indate_from, String indate_to,
			String invoicetype, String productid, String ownerid, String traycode, String lotid, String strUrl, String boundstockid,String groupinfo,String customer_id,String outtype) throws Exception ;
}
