package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseWhareaVirtual;

/**
 * 
 * ����: ����DAO��ӿ�
 * @author fanll
 * @since WMS 3.0
 */
public interface IBaseWhAreaVirtualDao {

	/**
	 * ����:����������ѯ����
	 * @param warehouseid		�ֿ�ID
	 * @param whAreaName		������
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaQuery(String warehouseid, String whAreaName) throws Exception;

	/**
	 * ����:���ݿ���ID��ÿ���
	 * @param whAreaId	����ID
	 * @return 
	 * @throws Exception
	 */
	public BaseWhareaVirtual getWhareaById(String whAreaId) throws Exception;

	/**
	 * ����:��øòֿ������һ����������
	 * @param warehouseid 
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	public String getMaxWhareaNo(String warehouseid) throws Exception;

	/**
	 * ����:���ӿ���
	 * @param wharea	����
	 * @throws Exception
	 */
	public void addWhArea(BaseWhareaVirtual wharea) throws Exception;

	/**
	 * ����:��ȡ���п����б�
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaList() throws Exception;

	/**
	 * ����:�޸Ŀ���
	 * @param wharea	����
	 * @throws Exception
	 */
	public void updateWhArea(BaseWhareaVirtual wharea) throws Exception;

	/**
	 * ����:ɾ������
	 * @param whAreaId	����ID
	 * @throws Exception
	 */
	public void deleteWhArea(String whAreaId) throws Exception;

	/**
	 * ����:�жϲֿ����Ƿ��п���
	 * @param id	�ֿ�ID
	 * @throws Exception
	 */
	public boolean isWhHasChildNode(String id) throws Exception;

	/**
	 * ����:ȡ��ָ���ֿ��µĿ���
	 * @param warehouseid	�ֿ�ID
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaByWhid(String warehouseid) throws Exception;
	/**
	 * ����:ȡ��ָ���ֿ��µĿ���������⣩
	 * @param warehouseid	�ֿ�ID
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaByWhidNotTem(String warehouseid) throws Exception;
    /**
     * ����:���ݲֿ����ݴ���
     * @param warehouseid
     * @return
     * @throws Exception
     */
    public BaseWhareaVirtual getZCgetWhAreaByWhid(String warehouseid)throws Exception;

}