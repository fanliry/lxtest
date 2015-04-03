package com.wms3.bms.standard.dao.inventory;

import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;

/**
 * 
 * ����: ���DAO��ӿ�
 * @author hug 2012-4-25
 * @since WMS 3.0
 */
public interface IInventoryDao  extends IDao{
    /**
     * ���ܣ����ݿ��ID��ÿ��
     * @author hug 2012-4-25 
     * @param strDetailId
     * @return
     * @throws Exception
     */
    public InventoryStorage getInventoryById(String strDetailId) throws Exception;
    
    /**
     * ����:�жϻ�λ�͸û�λ������Ƿ�������� true��������false��û��
     * @author hug 2012-5-18 
     * @param strSpace
     * @return
     * @throws Exception
     */
    public boolean isSpaceAlleyOutLock(String strSpace) throws Exception;
    
    /**
     * ���ܣ������ҵ��ϸ����
     * @author hug 2012-5-18 
     * @param strJobId  ��ҵID
     * @return
     * @throws Exception
     */
    public int getCountJobDetailRow(String strJobId) throws Exception;
    
    public List<InventoryStorage> searchInventory(String strProductId, String strBatch, String strPrintdate, String strStype, String strMstatus, String strCustomerId) throws Exception;
    
    public List<InventoryStorage> queryInventory(String warehouseid, String whareaid, String strProductId, String strBatchId, String strBatch, String strPrintdate, String strStype, String strMstatus, String ownerid) throws Exception;
    public List<InventoryStorage> groupInventory(String warehouseid, String whareaid, String strProductId, String strBatchId, String strBatch, String strPrintdate, String strStype, String strMstatus, String ownerid) throws Exception;

	/**
	 * ����:���ݻ�λID��ÿ���б�
	 * @param strCargoSpaceId	��λID
	 * @return
	 * @throws Exception
	 */
	public List<InventoryStorage> getInventoryByCsId(String strCargoSpaceId) throws Exception;
	/**
	 * ���ܣ�������ҵid�����������ÿ��
	 * @param jobid ��ҵID
	 * @param trayCode ��������
	 * @return
	 * @throws Exception
	 */
	public List<InventoryStorage> getInventoryByJobIdAndTrayCode(String jobid,String trayCode)throws Exception;
	/**
	 * ���ܣ�������ҵ��ƷId���������룬��λ��������Ϣ
	 * @param proId ��ƷID
	 * @param trayCode ��������
	 * @param cargoSpaceId ��λ
	 * @return
	 * @throws Exception
	 */
	public InventoryStorage getInventoryByProIdAndTrayCodeAndCSId(String proId,String trayCode,String cargoSpaceId)throws Exception;
	
	public InventoryStorage getInventoryByIdAndInventoryType(String inventoryId) throws Exception;
  
}
