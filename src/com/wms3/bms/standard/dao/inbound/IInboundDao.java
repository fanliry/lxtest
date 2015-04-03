package com.wms3.bms.standard.dao.inbound;

import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.inbound.InboundInvoiceHeader;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader;

/**
 * 
 * ����: ��ⵥ���ݿ����DAO��ӿ�
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public interface IInboundDao extends IDao{

    /**
	 * ����:������ⵥ��
	 * @param inInvoice		��ⵥ
	 * @param lsinDetail	��ⵥ��ϸ�ļ���
	 * @param ids			��Ӧ��ҵ��ϸ��id
	 * @return 
	 * @throws Exception
	 */
	public void createInvoice(InboundHeader inInvoice, List<InboundDetail> lsinDetail, String ids) throws Exception;

	/**
	 * ����:������ⵥ�ݱ�Ų�ѯ��ⵥ
	 * @param instockid	��ⵥ�ݱ��
	 * @return 
	 * @throws Exception
	 */
	public InboundHeader getInboundHeader(String instockid) throws Exception;

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
    
	public String addHLRKJob(String inventoryId,String strUserCode,String getnum) throws Exception;
	/**
	 * rF�������(��ָ����λ)
	 * 
	 */
	public String addHLRKJobplus(String inventoryId,String Virtualwhid,String sjtraycode,String strUserCode,String getnum,String platoon,String column,String floor) throws Exception;
}
