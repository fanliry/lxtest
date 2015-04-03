package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseCargospace;

/**
 * ����: ��λDAO��ӿ�
 * @author fangjie
 * 
 */
public interface IBaseCargoSpaceDao {

	/**
	 * ����:���ݻ�λID��û�λ
	 * @param cargoSpaceId	��λID
	 * @return
	 * @throws Exception
	 */
	public BaseCargospace getCargoSpaceById(String cargoSpaceId) throws Exception;

	/**
	 * ����:���ӻ�λ
	 * @param cargoSpace	��λ
	 * @throws Exception
	 */
	public void addCargospace(BaseCargospace cargoSpace) throws Exception;

	/**
	 * ����:�޸Ļ�λ
	 * @param cargoSpace	��λ
	 * @throws Exception
	 */
	public void updateCargospace(BaseCargospace cargoSpace) throws Exception;

	/**
	 * ����:ɾ����λ
	 * @param id	��λID
	 * @throws Exception
	 */
	public void deletCargospaceById(String id) throws Exception;
	
	/**
	 * ����:����������ѯ��λ
	 * @param warehouseid		�ֿ�ID
	 * @param cargoAreaId		����ID
	 * @param cargoAlleyId		���ID	
	 * @param whAreaId			����ID
	 * @return
	 * @throws Exception
	 */
	public List getCargoSpaceQuery(String warehouseid, String cargoAreaId, String cargoAlleyId, String whAreaId) throws Exception;

	/**
	 * ����:ɾ����������л�λ
	 * @param id	���ID
	 * @throws Exception
	 */
	public void deletCargospaceByAlleyId(String id) throws Exception;
	
    /**
     * ���ܣ��޸Ļ�λ״̬
     * @author hug 2012-4-27 
     * @param strSpaceId    ��λID
     * @param strStatus     ��λ״̬
     * @throws Exception
     */
    public void updateCargospaceStatus(String strSpaceId, String strStatus) throws Exception;

	/**
	 * ����:���һ����λ�Ŀ��
	 * @param strCargoSpaceId ��λID
	 * @throws Exception
	 */
	public void clearOneCS(String cargoSpaceId) throws Exception;

	/**
	 * ����:������л�λ�Ŀ��
	 * @throws Exception
	 */
	public void clearAllCS() throws Exception;

	/**
	 * ����:�޸Ļ�λ���������
	 * @param strIds		��λID
	 * @param strFlag		1:������;2:�������;3:������;4:�������
	 * @param strFlagValue
	 * @throws Exception
	 */
	public void lockCS(String strIds, String flag, String strFlag) throws Exception;

	/**
	 * ����:��ջ�λ,�����λ
	 * @param strIds		��λID
	 * @param strFlag		1:��ջ�λ;2:�����λ
	 * @throws Exception
	 */
	public void saveCS(String ids, String flag) throws Exception;
	
	/**
	 * ����:���ָ����λ���ڵĻ�λ
	 * @param cargoSpaceId	��λID
	 * @param 1-�� 2-�� 3-�� 4-�� 
	 * @return
	 * @throws Exception
	 */
	public BaseCargospace getNearCargospace(String cargoSpaceId, String position) throws Exception;


	/**
     * ����:���ݲֿ�id�Ϳ�λid���Ψһ�Ŀ���id
     * @param warehouseid
     * @return
     * @throws Exception
     */
    public String getWhAreaIdByWarehouseidAndCargospaceId(String warehouseid,String cargospaceId)throws Exception;
    /**
     * ����:���ݲֿ�id�Ϳ�λid���Ψһ�����id
     * @param warehouseid
     * @return
     * @throws Exception
     */
    public String getAlleyIdByWarehouseidAndCargospaceId(String warehouseid,String cargospaceId)throws Exception;
    
    /**
     * ���ܣ����ݻ�λ��ȡ��λ��Ϣ
     * @return
     * @throws Exception 
     */
    public BaseCargospace GetCargospace(String platoon, String column,String floor,String warehouseid) throws Exception;
    
	/**
	 * ���ܣ���ȡһ���ջ�λ
	 * @param warehouseid
	 * @param WhAreaId
	 * @return
	 */
    public BaseCargospace getNullSpaceId(String warehouseid, String WhAreaId);
    
    /**
     * ���ܣ����ݻ�λ��ȡ��λ��Ϣ
     * @return
     * @throws Exception 
     */
    public BaseCargospace GetWhCargospace(String platoon, String column,String floor,String warehouseid,String whAreaId);
    
	/**
	 * ����:����whAreaId��û�λ
	 * @param whAreaId
	 * @return
	 * @throws Exception
	 */
	public BaseCargospace getCargoSpaceBywhAreaId(String whAreaId) throws Exception;
}
