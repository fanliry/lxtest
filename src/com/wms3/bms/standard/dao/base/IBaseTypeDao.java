package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseType;

/**
 * 
 * ����: ����DAO��ӿ�
 * @author fangjie
 * @since WMS 3.0
 */
public interface IBaseTypeDao {

	/**
	 * ���ܣ�����������ѯ���͵��б�
	 * @param typevalue		����ֵ
	 * @param typename		������
	 * @return 
	 */
	List getType(String typename, String typevalue) throws Exception;
	
	/**
	 * ���ܣ��������ʹ����ѯ�������ݵ��б�
	 * @param typevalue		����ֵ
	 * @param typename		������
	 * @return
	 */
	List getTypeList(String typevalue) throws Exception;
	
	/**
	 * ����:��������
	 * @param type	����
	 * @throws Exception
	 */
	void addType(BaseType type) throws Exception;
	
	/**
	 * ����:��ø�����ֵ
	 * @return 
	 * @throws Exception
	 */
	List getTypeParentList() throws Exception;
	
	/**
	 * ����:��������ID�������
	 * @param typeId	����ID
	 * @return 
	 * @throws Exception
	 */
	BaseType getTypeById(String id) throws Exception;
	
	/**
	 * ���ܣ�����������ѯ����
	 * @param typevalue		����ֵ
	 * @param selectvalue	�����б�ֵ
	 * @return
	 */
	List getTypeList(String typevalue, String selectvalue) throws Exception;
	
	/**
	 * ����:��������ֵ�޸�����
	 * @param typevalue		����ֵ
	 * @param typename		������
	 * @throws Exception
	 */
	void updateType(String typevalue, String typename) throws Exception;
	
	/**
	 * ����:��������Ϊϵͳ����
	 * @param typevalue		����ֵ
	 * @throws Exception
	 */
	void setTypeLevel(String typevalue) throws Exception;
	
	/**
	 * ����:��������ֵɾ������
	 * @param typevalue		����ֵ
	 * @throws Exception
	 */
	void deleteType(String typevalue) throws Exception;
	
	/**
	 * ����:�޸�����
	 * @param type	����
	 * @throws Exception
	 */
	void updateType(BaseType type) throws Exception;
	
	/**
	 * ����:ɾ������
	 * @param type	����
	 * @throws Exception
	 */
	void deleteType(BaseType type) throws Exception;
    /**
     * ���ܣ���ȡ�ֵ���������
     * @throws Exception
     */
	List<BaseType> getType()throws Exception;
}
