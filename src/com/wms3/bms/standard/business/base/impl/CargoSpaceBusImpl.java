package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.base.ICargoSpaceBus;
import com.wms3.bms.standard.dao.base.IBaseCargoSpaceDao;
import com.wms3.bms.standard.dao.base.IBasePackageMastermesgDao;
import com.wms3.bms.standard.dao.base.impl.BaseCargoSpaceDaoImpl;
import com.wms3.bms.standard.dao.base.impl.BasePackageMastermesgDaoImpl;
import com.wms3.bms.standard.dao.inventory.IInventoryDao;
import com.wms3.bms.standard.dao.inventory.impl.InventoryDaoImpl;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BasePackageMastermesg;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;

/**
 * ����: ��λ����ҵ����
 * @author fangjie
 * 
 */
public class CargoSpaceBusImpl implements ICargoSpaceBus {
	
	protected IBaseCargoSpaceDao baseCargoSpaceDAO = null;
	protected IInventoryDao inventoryDAO = null;
	protected EntityDAO m_dao = null;
    protected IBasePackageMastermesgDao packageMasterDao = null;

	public CargoSpaceBusImpl(EntityDAO dao) {
		this.baseCargoSpaceDAO = new BaseCargoSpaceDaoImpl(dao);
		this.inventoryDAO = new InventoryDaoImpl(dao);
		this.m_dao = dao;
        packageMasterDao = new BasePackageMastermesgDaoImpl(dao);
	}

	/**
	 * ����:���ݻ�λID��û�λ
	 * @param cargoSpaceId	��λID
	 * @return
	 * @throws Exception
	 */
	public BaseCargospace getCargoSpaceById(String cargoSpaceId) throws Exception {
		
		return baseCargoSpaceDAO.getCargoSpaceById(cargoSpaceId);
	}

	/**
	 * ����:���ӻ�λ
	 * @param cargoSpace	��λ
	 * @throws Exception
	 */
	public void addCargospace(BaseCargospace cargoSpace) throws Exception {
		
		baseCargoSpaceDAO.addCargospace(cargoSpace);
		
	}

	/**
	 * ����:�޸Ļ�λ
	 * @param cargoSpace	��λ
	 * @throws Exception
	 */
	public void updateCargospace(BaseCargospace cargoSpace) throws Exception {
		
		baseCargoSpaceDAO.updateCargospace(cargoSpace);
	}

	/**
	 * ����:ɾ����λ
	 * @param id	��λID
	 * @throws Exception
	 */
	public void deletCargospaceById(String id) throws Exception {

		baseCargoSpaceDAO.deletCargospaceById(id);
	}
	
	/**
	 * ����:ɾ����������л�λ
	 * @param id	���ID
	 * @throws Exception
	 */
	public void deletCargospaceByAlleyId(String id) throws Exception {

		baseCargoSpaceDAO.deletCargospaceByAlleyId(id);
	}

	/**
	 * ����:����������ѯ��λ
	 * @param warehouseid		�ֿ�ID
	 * @param cargoAreaId		����ID
	 * @param cargoAlleyId		���ID	
	 * @param whAreaId			����ID
	 * @return
	 * @throws Exception
	 */
	public List getCargoSpaceQuery(String warehouseid, String cargoAreaId, String cargoAlleyId, String whAreaId) throws Exception {

		return baseCargoSpaceDAO.getCargoSpaceQuery(warehouseid, cargoAreaId, cargoAlleyId, whAreaId);
	}
	
	/**
	 * ����:�ж�������Ƿ��л�λ
	 * @param id	���ID
	 * @throws Exception
	 */
	public boolean isAlleyHasChildNode(String id) throws Exception {

		List ls = baseCargoSpaceDAO.getCargoSpaceQuery("", "", id, "");
		
		if(ls!=null && ls.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * ����:�жϿ������Ƿ��л�λ
	 * @param id	����ID
	 * @throws Exception
	 */
	public boolean isWhAreaHasChildNode(String id) throws Exception {
		
		List ls = baseCargoSpaceDAO.getCargoSpaceQuery("", "", "", id);
		
		if(ls!=null && ls.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
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
			String in_lock, String out_lock, String csstatus, String usage, String handling, String strUrl, int maxLine) throws Exception {
		
		PagingTool pt = null;

		String sql = " From BaseCargospace as t where 1=1" ;
		
		
		if(warehouseId != null && warehouseId.trim().length() > 0){	//�ֿ�
			sql += " and t.warehouseid = '" + warehouseId + "'";
		}
		
		if(whAreaId != null && whAreaId.trim().length() > 0){ //����
			sql += " and t.whAreaId  = '" + whAreaId + "'";
		}
		
		if(cargoAreaId != null && cargoAreaId.trim().length() > 0){	//����
			sql += " and t.cargoAreaId = '" + cargoAreaId + "'";
		}
		
		if(platoon != null && platoon.trim().length() > 0){	//��
			sql += " and t.csplatoon = '" + SeqTool.getCode(Integer.parseInt(platoon), 2) + "'";
		}
		
		if(column != null && column.trim().length() > 0){	//��
			sql += " and t.cscolumn = '" + SeqTool.getCode(Integer.parseInt(column), 2) + "'";
		}
		
		if(floor != null && floor.trim().length() > 0){	//��
			sql += " and t.csfloor = '" + SeqTool.getCode(Integer.parseInt(floor), 2) + "'";
		}
		
		if(in_lock != null && in_lock.trim().length() > 0){	//�����
			sql += " and t.inlock = '" + in_lock + "'";
		}
		
		if(out_lock != null && out_lock.trim().length() > 0){	//������
			sql += " and t.outlock = '" + out_lock + "'";
		}
		
		if(csstatus != null && csstatus.trim().length() > 0){	//��λ״̬
			sql += " and t.csstatus = '" + csstatus + "'";
		}
		
		if(usage != null && usage.trim().length() > 0){	//��λʹ��
			sql += " and t.usagetype = '" + usage + "'";
		}
		
		if(handling != null && handling.trim().length() > 0){	//�洢����
			sql += " and t.storagetype = '" + handling + "'";
		}
		
		String strHQL = sql + " order by t.cargoSpaceId";
		String strCountHQL = "select count(*)" + sql ;
		
		pt = CommonPagination.getPagingTool(m_dao, strCountHQL ,strHQL, strUrl, 1, maxLine);

		return pt;
	}

	/**
	 * ����:���ݻ�λID��ÿ��
	 * @param cargoSpaceId	��λID
	 * @return
	 * @throws Exception
	 */
	public List getStorageBySpaceId(String cargoSpaceId) throws Exception {
		
		return inventoryDAO.getInventoryByCsId(cargoSpaceId);
	}
	
	/**
	 * ����:���һ����λ�Ŀ��
	 * @param strCargoSpaceId ��λID
	 * @throws Exception
	 */
	public void clearOneCargoSpace(String cargoSpaceId) throws Exception {
		
		baseCargoSpaceDAO.clearOneCS(cargoSpaceId);
	}
	
	/**
	 * ����:������л�λ�Ŀ��
	 * @throws Exception
	 */
	public void clearAllCargoSpace() throws Exception {
		
		baseCargoSpaceDAO.clearAllCS();
	}

	/**
	 * ����:�޸Ļ�λ���������
	 * @param strIds		��λID
	 * @param strFlag		1:������;2:�������;3:������;4:�������
	 * @param strFlagValue
	 * @throws Exception
	 */
	public void updateCargoSpaceLock(String strIds, String flag, String strFlag) throws Exception {

		baseCargoSpaceDAO.lockCS(strIds, flag, strFlag);
	}

	/**
	 * ����:��ջ�λ,�����λ
	 * @param strIds		��λID
	 * @param strFlag		1:��ջ�λ;2:�����λ
	 * @throws Exception
	 */
	public void saveCargoSpace(String ids, String flag) throws Exception {

		baseCargoSpaceDAO.saveCS(ids, flag);
	}
    
    public double getSpaceUseWeight(String strSpaceId) throws Exception {
        double weight = 0;
        List<InventoryStorage> lsStorage = inventoryDAO.getInventoryByCsId(strSpaceId);
        if(lsStorage != null){
            InventoryStorage storage = null;
            BasePackageMastermesg master = null;
            for(int i = 0; i < lsStorage.size(); i++){
                storage = lsStorage.get(i);
                master = packageMasterDao.getPackageMastermesg(storage.getPackid(), storage.getPunit());
                //����*�����װ������
                weight = weight + storage.getPnum()* master.getWeight();
            }
        }
        return weight;
    }
    public double getSpaceUseVolume(String strSpaceId) throws Exception {
        double volume = 0;
        List<InventoryStorage> lsStorage = inventoryDAO.getInventoryByCsId(strSpaceId);
        if(lsStorage != null){
            InventoryStorage storage = null;
            BasePackageMastermesg master = null;
            for(int i = 0; i < lsStorage.size(); i++){
                storage = lsStorage.get(i);
                master = packageMasterDao.getPackageMastermesg(storage.getPackid(), storage.getPunit());
                //����*�����װ�����
                volume = volume + storage.getPnum()* master.getVolume();
            }
        }
        return volume;
    }
    
    /**
	 * ����:�޸Ļ�λ״̬
	 * @param strSpaceId
	 * @param strStatus
	 * @throws Exception
	 */
	public void updateCargospaceStatus(String strSpaceId, String strStatus) throws Exception {
		
		baseCargoSpaceDAO.updateCargospaceStatus(strSpaceId, strStatus);
	}

	/**
     * ����:���ݲֿ�id�Ϳ�λid���Ψһ�Ŀ���id
     * @param warehouseid
     * @return
     * @throws Exception
     */
    public String getWhAreaIdByWarehouseidAndCargospaceId(String warehouseid,String cargospaceId)throws Exception{
    	return baseCargoSpaceDAO.getWhAreaIdByWarehouseidAndCargospaceId(warehouseid, cargospaceId);
    }
    /**
     * ����:���ݲֿ�id�Ϳ�λid���Ψһ�����id
     * @param warehouseid
     * @return
     * @throws Exception
     */
    public String getAlleyIdByWarehouseidAndCargospaceId(String warehouseid,String cargospaceId)throws Exception{
    	return baseCargoSpaceDAO.getAlleyIdByWarehouseidAndCargospaceId(warehouseid, cargospaceId);
    }
    
	/**
	 * ����:����whAreaId��û�λ
	 * @param whAreaId
	 * @return
	 * @throws Exception
	 */
	public BaseCargospace getCargoSpaceBywhAreaId(String whAreaId) throws Exception {
		return baseCargoSpaceDAO.getCargoSpaceBywhAreaId(whAreaId);
	}
}