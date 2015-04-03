package com.wms3.bms.standard.business.base;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BaseWharea;

/**
 * ����: ��λ����ҵ��ӿ�
 * @author fangjie
 * 
 */
public interface ICargoSpaceBus {


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
	 * ����:�ж�������Ƿ��л�λ
	 * @param id	���ID
	 * @throws Exception
	 */
	public boolean isAlleyHasChildNode(String id) throws Exception;
	
	/**
	 * ����:�жϿ������Ƿ��л�λ
	 * @param id	����ID
	 * @throws Exception
	 */
	public boolean isWhAreaHasChildNode(String id) throws Exception;

	/**
	 * ����:ɾ����������л�λ
	 * @param id	���ID
	 * @throws Exception
	 */
	public void deletCargospaceByAlleyId(String strId) throws Exception;

	/**
	 * ����:����������ѯ��λ
	 * @param warehouseid	�ֿ�ID
	 * @param cargoAreaId	����ID
	 * @param whAreaId		����ID
	 * @param platoon		��
	 * @param column		��
	 * @param floor			��
	 * @param in_lock		�����
	 * @param out_lock		������
	 * @param csstatus		��λ״̬
	 * @param usage			��λʹ��
	 * @param handling		�洢����
	 * @param strUrl 		��ַ
	 * @param maxLine 		��ҳ��ʾ����
	 * @return
	 * @throws Exception
	 */
	public PagingTool getCsQuery(String warehouseId, String whAreaId, String cargoAreaId, String platoon, String column, String floor, 
			String in_lock, String out_lock, String csstatus, String usage, String handling, String strUrl, int maxLine) throws Exception;

	/**
	 * ����:���ݻ�λID��ÿ��
	 * @param cargoSpaceId	��λID
	 * @return
	 * @throws Exception
	 */
	public List getStorageBySpaceId(String cargoSpaceId) throws Exception;
	
	/**
	 * ����:���һ����λ�Ŀ��
	 * @param strCargoSpaceId ��λID
	 * @throws Exception
	 */
	public void clearOneCargoSpace(String cargoSpaceId) throws Exception;
	
	/**
	 * ����:������л�λ�Ŀ��
	 * @throws Exception
	 */
	public void clearAllCargoSpace() throws Exception;
	
	
	/**
	 * ����:�޸Ļ�λ״̬
	 * @param strSpaceId
	 * @param strStatus
	 * @throws Exception
	 */
	public void updateCargospaceStatus(String strSpaceId, String strStatus) throws Exception;

	/**
	 * ����:�޸Ļ�λ���������
	 * @param strIds		��λID
	 * @param strFlag		1:������;2:�������;3:������;4:�������
	 * @param strFlagValue
	 * @throws Exception
	 */
	public void updateCargoSpaceLock(String strIds, String flag, String strFlag) throws Exception;

	/**
	 * ����:��ջ�λ,�����λ
	 * @param strIds		��λID
	 * @param strFlag		1:��ջ�λ;2:�����λ
	 * @throws Exception
	 */
	public void saveCargoSpace(String ids, String string) throws Exception;
	
	/**
     * ���ܣ���ȡ��λ��ʹ�õ�����
     * @author hug 2012-6-26 
     * @param strSpaceId
     * @return
     * @throws Exception
     */
    public double getSpaceUseWeight(String strSpaceId) throws Exception;
    /**
     * ���ܣ���ȡ��λ��ʹ�õ����
     * @author hug 2012-6-26 
     * @param strSpaceId
     * @return
     * @throws Exception
     */
    public double getSpaceUseVolume(String strSpaceId) throws Exception;


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
	 * ����:����whAreaId��û�λ
	 * @param whAreaId
	 * @return
	 * @throws Exception
	 */
	public BaseCargospace getCargoSpaceBywhAreaId(String whAreaId) throws Exception;
}
