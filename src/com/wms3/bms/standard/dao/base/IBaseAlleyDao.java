package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseAlley;

/**
 * ����: ���DAO��ӿ�
 * @author fangjie
 * 
 */
public interface IBaseAlleyDao {
	
	/**
	 * ����:����������ѯ���
	 * @param warehouseid		�ֿ�ID
	 * @param cargoAlleyId		���ID	
	 * @param whAreaId 			����ID
	 * @return
	 * @throws Exception
	 */
	public List getAlleyQuery(String warehouseid, String cargoAlleyId, String whAreaId) throws Exception;

	/**
	 * ����:�������ID������
	 * @param cargoAlleyId	���ID
	 * @return
	 * @throws Exception
	 */
	public BaseAlley getAlleyById(String cargoAlleyId) throws Exception;

	/**
	 * ����:���ͬ�ֿ�ͬ����������һ���������
	 * @param whAreaId 
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	public String getMaxAlleyId(String whAreaId) throws Exception;

	/**
	 * ����:�������
	 * @param alley	���
	 * @throws Exception
	 */
	public void addAlley(BaseAlley alley) throws Exception;

	/**
	 * ����:��ȡ��������б�
	 * @return
	 * @throws Exception
	 */
	public List getAlleyList() throws Exception;

	/**
	 * ����:�޸����
	 * @param alley	���
	 * @throws Exception
	 */
	public void updateAlley(BaseAlley alley) throws Exception;

	/**
	 * ����:ɾ�����
	 * @param id	���ID
	 * @throws Exception
	 */
	public void deleteAlley(String id) throws Exception;

	/**
	 * ����:�жϿ������Ƿ������
	 * @param id	����ID
	 * @throws Exception
	 */
	public boolean isWhAreaHasChildNode(String id) throws Exception;

}
