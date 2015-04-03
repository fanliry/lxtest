package com.wms3.bms.standard.dao.quality;

/**
 * �������ʼ������в�ѯ��dao
 * @author liuxh 2013-3-7
 *
 */
public interface IQualityQueryDao {
	/**
	 * ���ܣ��ʼ������У����飩
	 *@author liuxh 2013-3-7
	 *@param warehouseid �ֿ�
	 *@param whareaid  ����
	 *@param lotnumber ����
	 *@param requestid ���뵥��
	 *@param productid ��Ʒid
	 *@param productstatus ��Ʒ״̬
	 *@param isgroup �Ƿ����η���
	 *@return
	 *@throws Exception
	 */
	public String searchInventoryForLotnumber(String warehouseid,String whareaid,String lotnumber,String requestid,String productid,String productstatus)throws Exception;
	/**
	 * ���ܣ���ѯĳ�������µ���ⵥ
	 *@author liuxh 2013-3-7
	 *@param warehouseid  �ֿ�id
	 *@param lotnumber    ����
	 *@param requestid    ���뵥id
	 *@param productid    ��Ʒid
	 *@param inbound      ��ⵥ 
	 *@return
	 *@throws Exception
	 */
	public String searchInventroyForInstock(String warehouseid,String lotnumber,String requestid,String productid,String inbound)throws Exception;
}
