package com.wms3.bms.standard.dao.inventory.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.AbstractDao;
import com.wms3.bms.standard.dao.inventory.IInventoryDao;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;

/**
 * 
 * ����: ���DAO��
 * @author hug 2012-4-25
 * @since WMS 3.0
 */
public class InventoryDaoImpl extends AbstractDao implements IInventoryDao {

    public InventoryDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    /**
     * ���ܣ����ݿ��ID��ÿ��
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
            throw new Exception("���ݿ��ID��ÿ�����" + er.getMessage());
        }
        return inventory;
    }
    
    /**
     * �жϻ�λ�͸û�λ������Ƿ�������� true��������false��û��
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inventory.IInventoryDao#isSpaceAlleyOutLock(java.lang.String)
     */
    public boolean isSpaceAlleyOutLock(String strSpace) throws Exception {
        try{
            if(strSpace != null && strSpace.trim().length() > 1){
                String strSql = "select spa.cargoSpaceId from BaseCargospace as spa, BaseAlley as al where spa.cargoAlleyId=al.cargoAlleyId and al.outlock='N' and spa.outlock='N' spa.cargoSpaceId='" + strSpace + "'";
                List ls = m_dao.searchEntities(strSql);
                if(ls != null && ls.size()>0){ //û����
                    return false;
                }else{  //����
                    return true;
                }
            }
        }catch(Exception er){
            throw new Exception(" �жϻ�λ�͸û�λ������Ƿ�������˳���" + er.getMessage());
        }
        return false;
    }
    /**
     * �����ҵ��ϸ����
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
            throw new Exception("�����ҵ��ϸ��������" + er.getMessage());
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
            throw new Exception("��ѯ������" + er.getMessage());
        }
        return ls;
    }
    /**
     * ��ѯ��ÿһ��
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
            throw new Exception("��ѯ������" + er.getMessage());
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
            throw new Exception("ͳ�Ʋ�ѯ������" + er.getMessage());
        }
        return ls;
    }
    
	/**
	 * ����:���ݻ�λID��ÿ���б�
	 * @param strCargoSpaceId	��λID
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
			throw new Exception("���ݻ�λID��ÿ���б�ʧ��:" + er.getMessage());
		}
		return ls;
	}
	/**
	 * ����:���ݿ���ÿ���б�
	 * @param inventoryids	���id
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
			throw new Exception("���ݻ�λID��ÿ���б�ʧ��:" + er.getMessage());
		}
		return ls;
	}
	/**
	 * ���ܣ�������ҵid�����������ÿ��
	 * @param jobid ��ҵID
	 * @param trayCode ��������
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
			throw new Exception("������ҵID�����������ÿ���б�ʧ��:" + er.getMessage());
		}
      
		return ls;
	}
	/**
	 * ���ܣ�������ҵ��ƷId���������룬��λ��������Ϣ
	 * @param proId ��ƷID
	 * @param trayCode ��������
	 * @param cargoSpaceId ��λ
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
			throw new Exception("������ҵ��ƷId���������룬��λID���������Ϣ���InventoryDaoImpl.getInventoryByProIdAndTrayCodeAndCSId��:" + er.getMessage());
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
			throw new Exception("������ҵ��ƷId��ȡ�ݴ����Ŀ�����:" + er.getMessage());
		}
		
		return iStorage;
	}
}
