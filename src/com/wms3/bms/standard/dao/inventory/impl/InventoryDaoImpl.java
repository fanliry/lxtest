package com.wms3.bms.standard.dao.inventory.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.AbstractDao;
import com.wms3.bms.standard.dao.inventory.IInventoryDao;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;

/**
 * 
 * 描述: 库存DAO类
 * @author hug 2012-4-25
 * @since WMS 3.0
 */
public class InventoryDaoImpl extends AbstractDao implements IInventoryDao {

    public InventoryDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    /**
     * 功能：根据库存ID获得库存
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inventory.IInventoryDao#getInventoryById(java.lang.String)
     */
    public InventoryStorage getInventoryById(String strDetailId) throws Exception {
        InventoryStorage inventory = null;
        try{
            if(strDetailId != null){
            	
                String strSql = "from InventoryStorage as detail where detail.inventoryid='" + strDetailId + "'";
                List ls = m_dao.searchEntities(strSql);
                if(ls != null && ls.size() > 0){
                    inventory = (InventoryStorage)ls.get(0);
                }
            }
        }catch(Exception er){
            throw new Exception("根据库存ID获得库存出错：" + er.getMessage());
        }
        return inventory;
    }
    
    /**
     * 判断货位和该货位的巷道是否出库锁了 true：锁定；false：没锁
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inventory.IInventoryDao#isSpaceAlleyOutLock(java.lang.String)
     */
    public boolean isSpaceAlleyOutLock(String strSpace) throws Exception {
        try{
            if(strSpace != null && strSpace.trim().length() > 1){
                String strSql = "select spa.cargoSpaceId from BaseCargospace as spa, BaseAlley as al where spa.cargoAlleyId=al.cargoAlleyId and al.outlock='N' and spa.outlock='N' spa.cargoSpaceId='" + strSpace + "'";
                List ls = m_dao.searchEntities(strSql);
                if(ls != null && ls.size()>0){ //没有锁
                    return false;
                }else{  //锁了
                    return true;
                }
            }
        }catch(Exception er){
            throw new Exception(" 判断货位和该货位的巷道是否出库锁了出错：" + er.getMessage());
        }
        return false;
    }
    /**
     * 获得作业详细行数
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inventory.IInventoryDao#getCountJobDetailRow(java.lang.String)
     */
    public int getCountJobDetailRow(String strJobId) throws Exception {
        try{
            if(strJobId != null){
                
                String strSql = "select count(jobd.jobdetailid) from InoutJobdetail as jobd where jobd.jobid='" + strJobId + "'";
                return m_dao.searchEntitieCount(strSql);
            } 
        }catch(Exception er){
            throw new Exception("获得作业详细行数出错：" + er.getMessage());
        }
        return 0;
    }
    public List<InventoryStorage> searchInventory(String strProductId, String strBatch, String strPrintdate, String strStype, String strMstatus, String strCustomerId) throws Exception {
        List<InventoryStorage> ls = null;
        try{
            String strSql = "from InventoryStorage as ins where ins.status != '2'";
            if(strProductId != null && strProductId.trim().length() > 0){
                strSql = strSql + " and ins.productid='"+ strProductId +"'";
            }
            if(strBatch != null && strBatch.trim().length() > 0){
                strSql = strSql + " and ins.batch='"+ strBatch +"'";            
            }
            if(strPrintdate != null && strPrintdate.trim().length() > 0){
                strSql = strSql + " and ins.printdate='"+ strPrintdate +"'";
            }
            if(strStype != null && strStype.trim().length() > 0){
                strSql = strSql + " and ins.stype='"+ strStype +"'"; 
            }
            if(strMstatus != null && strMstatus.trim().length() > 0){
                strSql = strSql + " and ins.mstatus='"+ strMstatus +"'";
            }
            if(strCustomerId != null && strCustomerId.trim().length() > 0){
                strSql = strSql + " and ins.ownerId='"+ strCustomerId +"'";
            }
            
            strSql = strSql + " order by ins.indate";
            
            ls = m_dao.searchEntities(strSql);
            
        }catch(Exception er){
            throw new Exception("查询库存出错：" + er.getMessage());
        }
        return ls;
    }
    /**
     * 查询的每一箱
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inventory.IInventoryDao#queryInventory(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public List<InventoryStorage> queryInventory(String warehouseid, String whareaid, String strProductId, String strBatchId, String strBatch, String strPrintdate, String strStype, String strMstatus, String ownerid) throws Exception {
        List<InventoryStorage> ls = null;
        try{
            String strSql = "from InventoryStorage as ins where ins.status != '2'";
            if(warehouseid != null && warehouseid.trim().length() > 0){
                strSql = strSql + " and ins.warehouseid='"+ warehouseid +"'";
            }
            if(whareaid != null && whareaid.trim().length() > 0){
                strSql = strSql + " and ins.whAreaId='"+ whareaid +"'";
            }
            if(strProductId != null && strProductId.trim().length() > 0){
                strSql = strSql + " and ins.productid='"+ strProductId +"'";
            }
            if(strBatchId != null && strBatchId.trim().length() > 0){
                strSql = strSql + " and ins.batchid='"+ strBatchId +"'";            
            }
            if(strBatch != null && strBatch.trim().length() > 0){
                strSql = strSql + " and ins.batch='"+ strBatch +"'";            
            }
            if(strPrintdate != null && strPrintdate.trim().length() > 0){
                strSql = strSql + " and ins.printdate='"+ strPrintdate +"'";
            }
            if(strStype != null && strStype.trim().length() > 0){
                strSql = strSql + " and ins.stype='"+ strStype +"'"; 
            }
            if(strMstatus != null && strMstatus.trim().length() > 0){
                strSql = strSql + " and ins.mstatus='"+ strMstatus +"'";
            }
            if(ownerid != null && ownerid.trim().length() > 0){
                strSql = strSql + " and ins.ownerId='"+ ownerid +"'";
            }
            strSql = strSql + " order by ins.indate";
            
            ls = m_dao.searchEntities(strSql);
            
        }catch(Exception er){
            throw new Exception("查询库存出错：" + er.getMessage());
        }
        return ls;
    }
    public List<InventoryStorage> groupInventory(String warehouseid, String whareaid, String strProductId, String strBatchId, String strBatch, String strPrintdate, String strStype, String strMstatus, String ownerid) throws Exception {
        List<InventoryStorage> ls = null;
        try{
            String strSql = "select ins.warehouseid,ins.whAreaId,ins.cargoSpaceId,ins.traycode,ins.productid,ins.batchid,ins.batch,ins.printdate,ins.stype,ins.mstatus,ins.punit,";
            strSql = strSql + " (ins.pnum-ins.freezenum-ins.assignnum-ins.holdnum) as num,";
            strSql = strSql + " (ins.pweight-ins.freezepweight-ins.assignweight-ins.holdweight) as weight,";
            strSql = strSql + " (ins.pnetweight-ins.freezenetweight-ins.assignnetweight-ins.holdnetweight) as netweight,";
            strSql = strSql + " (ins.pvolume-ins.freezevolume-ins.assignvolume-ins.holdvolume) as volume";
            strSql = strSql + " from InventoryStorage as ins where ins.status != '2'";
            if(warehouseid != null && warehouseid.trim().length() > 0){
                strSql = strSql + " and ins.warehouseid='"+ warehouseid +"'";
            }
            if(whareaid != null && whareaid.trim().length() > 0){
                strSql = strSql + " and ins.whAreaId='"+ whareaid +"'";
            }
            if(strProductId != null && strProductId.trim().length() > 0){
                strSql = strSql + " and ins.productid='"+ strProductId +"'";
            }
            if(strBatchId != null && strBatchId.trim().length() > 0){
                strSql = strSql + " and ins.batchid='"+ strBatchId +"'";            
            }
            if(strBatch != null && strBatch.trim().length() > 0){
                strSql = strSql + " and ins.batch='"+ strBatch +"'";            
            }
            if(strPrintdate != null && strPrintdate.trim().length() > 0){
                strSql = strSql + " and ins.printdate='"+ strPrintdate +"'";
            }
            if(strStype != null && strStype.trim().length() > 0){
                strSql = strSql + " and ins.stype='"+ strStype +"'"; 
            }
            if(strMstatus != null && strMstatus.trim().length() > 0){
                strSql = strSql + " and ins.mstatus='"+ strMstatus +"'";
            }
            if(ownerid != null && ownerid.trim().length() > 0){
                strSql = strSql + " and ins.ownerId='"+ ownerid +"'";
            }
            strSql = strSql + " order by ins.cargoSpaceId ";
            
            strSql = strSql + " group by ins.warehouseid,ins.whAreaId,ins.cargoSpaceId,ins.traycode,ins.productid,ins.batchid,ins.batch,ins.printdate,ins.stype,ins.mstatus,ins.punit";
            
            ls = m_dao.searchEntities(strSql);
            
        }catch(Exception er){
            throw new Exception("统计查询库存出错：" + er.getMessage());
        }
        return ls;
    }
    
	/**
	 * 功能:根据货位ID获得库存列表
	 * @param strCargoSpaceId	货位ID
	 * @return
	 * @throws Exception
	 */
	public List<InventoryStorage> getInventoryByCsId(String strCargoSpaceId) throws Exception
	{
		List ls = null;
		try
		{
			if(strCargoSpaceId != null)
			{
				String strHql = "from InventoryStorage as ins where ins.cargoSpaceId='" + strCargoSpaceId + "'";
				strHql = strHql + " order by ins.indate";
				ls = m_dao.searchEntities(strHql); 		
			}
		}catch(Exception er)
		{
			throw new Exception("根据货位ID获得库存列表失败:" + er.getMessage());
		}
		return ls;
	}
	/**
	 * 功能:根据库存获得库存列表
	 * @param inventoryids	库存id
	 * @return
	 * @throws Exception
	 */
	public List<InventoryStorage> getInventoryByCsIds(String inventoryids) throws Exception
	{
		List ls = null;
		try
		{
			if(inventoryids != null)
			{
				String strHql = "from InventoryStorage as ins where ins.cargoSpaceId in (" + inventoryids + ")";
				strHql = strHql + " order by ins.indate";
				ls = m_dao.searchEntities(strHql); 		
			}
		}catch(Exception er)
		{
			throw new Exception("根据货位ID获得库存列表失败:" + er.getMessage());
		}
		return ls;
	}
	/**
	 * 功能：根据作业id，托盘条码获得库存
	 * @param jobid 作业ID
	 * @param trayCode 托盘条码
	 * @return
	 * @throws Exception
	 */
	public List<InventoryStorage> getInventoryByJobIdAndTrayCode(String jobid,
			String trayCode) throws Exception {
		List<InventoryStorage> ls = null;
		try {
			  String strSql = "from InventoryStorage where injobid='"+jobid+"'"+" and traycode='"+trayCode+"'";
			  ls = (List<InventoryStorage>) querySQL(strSql);	
		} catch (Exception er) {
			throw new Exception("根据作业ID，托盘条码获得库存列表失败:" + er.getMessage());
		}
      
		return ls;
	}
	/**
	 * 功能：根据作业产品Id，托盘条码，货位货物库存信息
	 * @param proId 产品ID
	 * @param trayCode 托盘条码
	 * @param cargoSpaceId 货位
	 * @return
	 * @throws Exception
	 */
	public InventoryStorage getInventoryByProIdAndTrayCodeAndCSId(String proId,
			String trayCode, String cargoSpaceId) throws Exception {
		InventoryStorage iStorage = null;
		List<InventoryStorage> ls = null;
		try {
			String strSql = "from InventoryStorage where productid='"+proId+
			"' and traycode='"+ trayCode +"' and cargoSpaceId='"+cargoSpaceId+"'";
			ls = (List<InventoryStorage>) querySQL(strSql);
			if(ls!=null && ls.size()>0)
			iStorage = ls.get(0);	
		} catch (Exception er) {
			throw new Exception("根据作业产品Id，托盘条码，货位ID货区库存信息出差（InventoryDaoImpl.getInventoryByProIdAndTrayCodeAndCSId）:" + er.getMessage());
		}
		
		return iStorage;
	}
	public InventoryStorage getInventoryByIdAndInventoryType(String tracode) throws Exception{
		InventoryStorage iStorage = null;
		List<InventoryStorage> ls = null;
		try {
			String strSql = "from InventoryStorage as inventory where inventory.traycode='"+tracode +"' and inventory.whAreaId in (select bw.whAreaId from BaseWharea as bw where bw.warehouseid=inventory.warehouseid and bw.whAreaType='9')";
			ls = (List<InventoryStorage>) querySQL(strSql);
			if(ls!=null && ls.size()>0)
			iStorage = ls.get(0);	
		} catch (Exception er) {
			throw new Exception("根据作业产品Id获取暂存区的库存出错:" + er.getMessage());
		}
		
		return iStorage;
	}
}
