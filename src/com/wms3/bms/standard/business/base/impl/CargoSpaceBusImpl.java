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
 * 描述: 货位管理业务类
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
	 * 功能:根据货位ID获得货位
	 * @param cargoSpaceId	货位ID
	 * @return
	 * @throws Exception
	 */
	public BaseCargospace getCargoSpaceById(String cargoSpaceId) throws Exception {
		
		return baseCargoSpaceDAO.getCargoSpaceById(cargoSpaceId);
	}

	/**
	 * 功能:增加货位
	 * @param cargoSpace	货位
	 * @throws Exception
	 */
	public void addCargospace(BaseCargospace cargoSpace) throws Exception {
		
		baseCargoSpaceDAO.addCargospace(cargoSpace);
		
	}

	/**
	 * 功能:修改货位
	 * @param cargoSpace	货位
	 * @throws Exception
	 */
	public void updateCargospace(BaseCargospace cargoSpace) throws Exception {
		
		baseCargoSpaceDAO.updateCargospace(cargoSpace);
	}

	/**
	 * 功能:删除货位
	 * @param id	货位ID
	 * @throws Exception
	 */
	public void deletCargospaceById(String id) throws Exception {

		baseCargoSpaceDAO.deletCargospaceById(id);
	}
	
	/**
	 * 功能:删除巷道下所有货位
	 * @param id	巷道ID
	 * @throws Exception
	 */
	public void deletCargospaceByAlleyId(String id) throws Exception {

		baseCargoSpaceDAO.deletCargospaceByAlleyId(id);
	}

	/**
	 * 功能:根据条件查询货位
	 * @param warehouseid		仓库ID
	 * @param cargoAreaId		货区ID
	 * @param cargoAlleyId		巷道ID	
	 * @param whAreaId			库区ID
	 * @return
	 * @throws Exception
	 */
	public List getCargoSpaceQuery(String warehouseid, String cargoAreaId, String cargoAlleyId, String whAreaId) throws Exception {

		return baseCargoSpaceDAO.getCargoSpaceQuery(warehouseid, cargoAreaId, cargoAlleyId, whAreaId);
	}
	
	/**
	 * 功能:判断巷道下是否有货位
	 * @param id	巷道ID
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
	 * 功能:判断库区下是否有货位
	 * @param id	库区ID
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
	 * 功能:根据条件查询货位
	 * @param warehouseid	仓库ID
	 * @param cargoAreaId	货区ID
	 * @param whAreaId		库区ID
	 * @param platoon		排
	 * @param column		列
	 * @param floor			层
	 * @param in_lock		入库锁
	 * @param out_lock		出库锁
	 * @param csstatus		货位状态
	 * @param usage			库位使用
	 * @param handling		存储类型
	 * @param strUrl 		地址
	 * @param maxLine 		分页显示行数
	 * @return
	 * @throws Exception
	 */
	public PagingTool getCsQuery(String warehouseId, String whAreaId, String cargoAreaId, String platoon, String column, String floor, 
			String in_lock, String out_lock, String csstatus, String usage, String handling, String strUrl, int maxLine) throws Exception {
		
		PagingTool pt = null;

		String sql = " From BaseCargospace as t where 1=1" ;
		
		
		if(warehouseId != null && warehouseId.trim().length() > 0){	//仓库
			sql += " and t.warehouseid = '" + warehouseId + "'";
		}
		
		if(whAreaId != null && whAreaId.trim().length() > 0){ //库区
			sql += " and t.whAreaId  = '" + whAreaId + "'";
		}
		
		if(cargoAreaId != null && cargoAreaId.trim().length() > 0){	//货区
			sql += " and t.cargoAreaId = '" + cargoAreaId + "'";
		}
		
		if(platoon != null && platoon.trim().length() > 0){	//排
			sql += " and t.csplatoon = '" + SeqTool.getCode(Integer.parseInt(platoon), 2) + "'";
		}
		
		if(column != null && column.trim().length() > 0){	//列
			sql += " and t.cscolumn = '" + SeqTool.getCode(Integer.parseInt(column), 2) + "'";
		}
		
		if(floor != null && floor.trim().length() > 0){	//层
			sql += " and t.csfloor = '" + SeqTool.getCode(Integer.parseInt(floor), 2) + "'";
		}
		
		if(in_lock != null && in_lock.trim().length() > 0){	//入库锁
			sql += " and t.inlock = '" + in_lock + "'";
		}
		
		if(out_lock != null && out_lock.trim().length() > 0){	//出库锁
			sql += " and t.outlock = '" + out_lock + "'";
		}
		
		if(csstatus != null && csstatus.trim().length() > 0){	//货位状态
			sql += " and t.csstatus = '" + csstatus + "'";
		}
		
		if(usage != null && usage.trim().length() > 0){	//库位使用
			sql += " and t.usagetype = '" + usage + "'";
		}
		
		if(handling != null && handling.trim().length() > 0){	//存储类型
			sql += " and t.storagetype = '" + handling + "'";
		}
		
		String strHQL = sql + " order by t.cargoSpaceId";
		String strCountHQL = "select count(*)" + sql ;
		
		pt = CommonPagination.getPagingTool(m_dao, strCountHQL ,strHQL, strUrl, 1, maxLine);

		return pt;
	}

	/**
	 * 功能:根据货位ID获得库存
	 * @param cargoSpaceId	货位ID
	 * @return
	 * @throws Exception
	 */
	public List getStorageBySpaceId(String cargoSpaceId) throws Exception {
		
		return inventoryDAO.getInventoryByCsId(cargoSpaceId);
	}
	
	/**
	 * 功能:清空一个货位的库存
	 * @param strCargoSpaceId 货位ID
	 * @throws Exception
	 */
	public void clearOneCargoSpace(String cargoSpaceId) throws Exception {
		
		baseCargoSpaceDAO.clearOneCS(cargoSpaceId);
	}
	
	/**
	 * 功能:清空所有货位的库存
	 * @throws Exception
	 */
	public void clearAllCargoSpace() throws Exception {
		
		baseCargoSpaceDAO.clearAllCS();
	}

	/**
	 * 功能:修改货位的入出库锁
	 * @param strIds		货位ID
	 * @param strFlag		1:入库加锁;2:出库加锁;3:入库解锁;4:出库解锁
	 * @param strFlagValue
	 * @throws Exception
	 */
	public void updateCargoSpaceLock(String strIds, String flag, String strFlag) throws Exception {

		baseCargoSpaceDAO.lockCS(strIds, flag, strFlag);
	}

	/**
	 * 功能:清空货位,保存货位
	 * @param strIds		货位ID
	 * @param strFlag		1:清空货位;2:保存货位
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
                //数量*这个包装的重量
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
                //数量*这个包装的体积
                volume = volume + storage.getPnum()* master.getVolume();
            }
        }
        return volume;
    }
    
    /**
	 * 功能:修改货位状态
	 * @param strSpaceId
	 * @param strStatus
	 * @throws Exception
	 */
	public void updateCargospaceStatus(String strSpaceId, String strStatus) throws Exception {
		
		baseCargoSpaceDAO.updateCargospaceStatus(strSpaceId, strStatus);
	}

	/**
     * 功能:根据仓库id和库位id获得唯一的库区id
     * @param warehouseid
     * @return
     * @throws Exception
     */
    public String getWhAreaIdByWarehouseidAndCargospaceId(String warehouseid,String cargospaceId)throws Exception{
    	return baseCargoSpaceDAO.getWhAreaIdByWarehouseidAndCargospaceId(warehouseid, cargospaceId);
    }
    /**
     * 功能:根据仓库id和库位id获得唯一的巷道id
     * @param warehouseid
     * @return
     * @throws Exception
     */
    public String getAlleyIdByWarehouseidAndCargospaceId(String warehouseid,String cargospaceId)throws Exception{
    	return baseCargoSpaceDAO.getAlleyIdByWarehouseidAndCargospaceId(warehouseid, cargospaceId);
    }
    
	/**
	 * 功能:根据whAreaId获得货位
	 * @param whAreaId
	 * @return
	 * @throws Exception
	 */
	public BaseCargospace getCargoSpaceBywhAreaId(String whAreaId) throws Exception {
		return baseCargoSpaceDAO.getCargoSpaceBywhAreaId(whAreaId);
	}
}