package com.wms3.bms.standard.business.inbound;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.base.BaseLotSet;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader;

/**
 * 
 * ����: ��ⵥ����ӿ���
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public interface IInBoundBus {
	
	//��ⵥ����������(����ͬʱ������ⵥ�ݣ�ÿ��ֻ��һ������������ⵥ��)
    public static byte[] newin_lock = new byte[0]; 
    
	/**
	 * ����:��ѯ�½���ⵥ����ҵ��
	 * @param warehouseid	�ֿ�
	 * @param whAreaId		����
	 * @param indate		����
	 * @param shiftid		���
	 * @param owner_id		����
	 * @param isinvoice		�Ƿ񿪵�
	 * @param lsLot 		�½���ⵥ��Ӧ�������������ü�
	 * @return 
	 * @throws Exception
	 */
	public List getInboundJobs(String warehouseid, String whAreaId, String indate, String shiftid, String owner_id, String isinvoice, 
			List<BaseLotSet> lsLot) throws Exception;
	/**
	 * ���ܣ����ݶ�����ϸid��ö���
	 * @author yao 2015-3-10
	 * @param poid
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> getJobInfoByPodetailid(String instockdetailid) throws Exception;

	/**
	 * ����:�½���ⵥ->��ѯ��ϸ
	 * @param warehouseid	�ֿ�
	 * @param whAreaId		����
	 * @param indate		����
	 * @param shiftid		���
	 * @param isinvoice		�Ƿ񿪵�
	 * @param strKey		��ϸkey
	 * @param lsLot 		�½���ⵥ��Ӧ�������������ü� 
	 * @return 
	 * @throws Exception
	 */
	public List getInboundJobDetails(String warehouseid, String whAreaId, String indate, String shiftid, String isinvoice, String strKey, 
			List<BaseLotSet> lsLot) throws Exception;

	/**
	 * ����:�½���ⵥ
	 * @param warehouseid	�ֿ�
	 * @param whAreaId		����
	 * @param indate		����
	 * @param shiftid		���
	 * @param strKey		��ϸkey
	 * @param lsLot 		�½���ⵥ��Ӧ�������������ü� 
	 * @param strUserCode 	�û�id  
	 * @return 
	 * @throws Exception
	 */
	public String addInboundInvoice(String warehouseid, String whAreaId, String indate, String shiftid, String strKey, 
			List<BaseLotSet> lsLot, String strUserCode) throws Exception;

	/**
	 * ����:��ⵥ����->��ѯ��ⵥ�б�
	 * @param warehouseid	�ֿ�
	 * @param whAreaId		����
	 * @param indate_from	����
	 * @param indate_to		����
	 * @param shiftid		���
	 * @param owner_id		����
	 * @param instatus		����״̬
	 * @param intype		�������
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getInbounds(String warehouseid, String whAreaId, String indate_from, String indate_to, String shiftid, String owner_id, 
			String instatus, String intype, String strUrl, int maxLine) throws Exception;

	/**
	 * ����:������ⵥ�ݱ�Ų�ѯ��ⵥ��ϸ�б�
	 * @param instockid	��ⵥ�ݱ��
	 * @return 
	 * @throws Exception
	 */
	public List getInboundDetails(String instockid) throws Exception;
    
	/**
	 * ����:������ⵥ�ݱ�Ų�ѯ��ⵥ
	 * @param instockid	��ⵥ�ݱ��
	 * @return 
	 * @throws Exception
	 */
	public InboundHeader getInboundInvoice(String instockid) throws Exception;

	/**
	 * ����:������ⵥ
	 * @param instockid	��ⵥ�ݱ��
	 * @return 
	 * @throws Exception
	 */
	public void updateInboundInvoice(InboundHeader inbound) throws Exception;

	/**
	 * ����:ɾ����ⵥ
	 * @param instockid	��ⵥ�ݱ��
	 * @return 
	 * @throws Exception
	 */
	public void deleteInboundInvoice(String instockid) throws Exception;

	/**
	 * ����:������ⵥ�ݱ�Ų�ѯ��ҵ��������
	 * @param instockdetailid	��ⵥ����ϸ���
	 * @return 
	 * @throws Exception
	 */
	public int getJobNumSum(String instockdetailid) throws Exception;
	
	/**
	 * ����:���ϵ���
	 * @param instockid	��ⵥ�ݱ��
	 * @return 
	 * @throws Exception
	 */
	public void cancelInboundInvoice(String instockid) throws Exception;

	/**
	 * ����:������ϸ����
	 * @param instockdetailid	��ⵥ����ϸ���
	 * @return 
	 * @throws Exception
	 */
	public void cancelInboundDetail(String instockdetailid) throws Exception;
	
	/**
	 * ���ɻ��������ҵ
	 * @param inventoryId
	 * @throws Exception
	 */
	public String addHLRKJob(String inventoryId,String userCode,String getnum) throws Exception;
	/**
	 * ���ɻ��������ҵ(ָ����λ�����ɵ���)
	 * @param inventoryId
	 * @throws Exception
	 */
	public String addHLRKJobplus(String inventoryId,String Virtualwhid,String sjtraycode,String userCode,String getnum,String platoon,String column,String floor) throws Exception;
}
