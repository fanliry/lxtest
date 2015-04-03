package com.wms3.bms.standard.dao.inventory.impl;

import java.util.ArrayList;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.AbstractDao;
import com.wms3.bms.standard.dao.inventory.ProductAdjustDao;
import com.wms3.bms.standard.entity.inventory.InventoryTransferDetail;
import com.wms3.bms.standard.entity.inventory.InventoryTransferHeader;

/**
 * ����: �������DAO��
 * @author yao 2012-9-26
 * @since WMS 3.0
 */
public class ProductAdjustDaoImpl extends AbstractDao implements ProductAdjustDao{
    
    public ProductAdjustDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }  
    /**
     * ������Ʒ������
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inventory.ProductAdjustDao#addProductAdjust(com.wms3.bms.standard.entity.inventory.InventoryTransferHeader)
     */
    public void addProductAdjust(InventoryTransferHeader inventorytransfer) throws Exception {
        m_dao.save(inventorytransfer);  
    }
    /**
     * ������Ʒ������
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inventory.ProductAdjustDao#updateProductAdjust(com.wms3.bms.standard.entity.inventory.InventoryTransferHeader)
     */
    public void updateProductAdjust(InventoryTransferHeader inventorytransfer) throws Exception {
        m_dao.update(inventorytransfer);
    }
    /**
     * ������Ʒ��������ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inventory.ProductAdjustDao#addProductAdjustDetail(com.wms3.bms.standard.entity.inventory.InventoryTransferDetail)
     */
    public void addProductAdjustDetail(InventoryTransferDetail inventorytransferDetail) throws Exception {
        m_dao.save(inventorytransferDetail);
    }
    /**
     * �޸���Ʒ��������ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inventory.ProductAdjustDao#updateProductAdjustDetail(com.wms3.bms.standard.entity.inventory.InventoryTransferDetail)
     */
    public void updateProductAdjustDetail(InventoryTransferDetail inventorytransferDetail) throws Exception {
        m_dao.update(inventorytransferDetail);  
    }
    /**
     * ɾ����Ʒ������
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inventory.ProductAdjustDao#deleteProductAdjust(java.lang.String)
     */
    public void deleteProductAdjust(String inventorytransferId) throws Exception {
        try{
            if(inventorytransferId != null){
                String strSql = "delete from InventoryTransferHeader as outvo where outvo.transferid='" + inventorytransferId + "'";
                m_dao.deleteSql(strSql);
            }
        }catch(Exception er){
            throw new Exception("ɾ����Ʒ����������" + er.getMessage());
        }   
    }
    /**
     * ɾ����Ʒ��������ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inventory.ProductAdjustDao#deleteProductAdjustDetail(java.lang.String)
     */
    public void deleteProductAdjustDetail(String inventorytransferdetailId) throws Exception {
        try{
            if(inventorytransferdetailId != null){
                String strSql = "delete from InventoryTransferDetail as outde where outde.transferdetailid='" + inventorytransferdetailId + "'";
                m_dao.deleteSql(strSql);
            }
        }catch(Exception er){
            throw new Exception("ɾ����Ʒ��������ϸ����" + er.getMessage());
        } 
    }
    /**
     * ������Ʒ������ID�����Ʒ������
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inventory.ProductAdjustDao#getProductAdjustById(java.lang.String)
     */
    public InventoryTransferHeader getProductAdjustById(String inventorytransferId) throws Exception {
        try{
            if(inventorytransferId != null){
                String strSql = "from InventoryTransferHeader as invo where invo.transferid='" + inventorytransferId + "'";
                List lsOutBound = m_dao.searchEntities(strSql, 0, 1);
                if(lsOutBound != null && lsOutBound.size() > 0){
                    return (InventoryTransferHeader)lsOutBound.get(0);
                }
            }
        }catch(Exception er){
            throw new Exception("������Ʒ������ID["+inventorytransferId+"]�����Ʒ����������" + er.getMessage());
        }
        return null;
    }
    /**
     * ������Ʒ��������ϸID�����Ʒ��������ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inventory.ProductAdjustDao#getProductAdjustDetailById(java.lang.String)
     */
    public InventoryTransferDetail getProductAdjustDetailById(String inventorytransferdetailId) throws Exception {
        try{
            if(inventorytransferdetailId != null){
                String strSql = "from InventoryTransferDetail as invo where invo.transferdetailid='" + inventorytransferdetailId + "'";
                List lsOutBound = m_dao.searchEntities(strSql, 0, 1);
                if(lsOutBound != null && lsOutBound.size() > 0){
                    return (InventoryTransferDetail)lsOutBound.get(0);
                }
            }
        }catch(Exception er){
            throw new Exception("������Ʒ��������ϸID["+inventorytransferdetailId+"]�����Ʒ��������ϸ����" + er.getMessage());
        }
        return null;
    }
    /**
     * ������Ʒ������ID�����Ʒ��������ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inventory.ProductAdjustDao#getOutBoundDetailsById(java.lang.String)
     */
    public List<InventoryTransferDetail> getProductAdjustDetailsById(String inventorytransferId) throws Exception {
        try{
            if(inventorytransferId != null){
                String strSql = "from InventoryTransferDetail as invo where invo.transferid='" + inventorytransferId + "'";
                List<InventoryTransferDetail> lsOutBound = m_dao.searchEntities(strSql);
                return lsOutBound;
            }
        }catch(Exception er){
            throw new Exception("������Ʒ������ID["+inventorytransferId+"]�����Ʒ��������ϸ����" + er.getMessage());
        }
        return null;
    }
    /**
     * ���ݲֿ�ID�����Ҫ����ȷ�ϵ���Ʒ������
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inventory.ProductAdjustDao#getSendProductAdjust(java.lang.String)
     */
    public List<InventoryTransferHeader> getSendProductAdjust(String warehouseid) throws Exception {
        List<InventoryTransferHeader> lsResult = null;
        try{
            if(warehouseid != null){
                String strSql = "from InventoryTransferHeader as outinv  where outinv.status in('0','1') and outinv.warehouseid='" + warehouseid + "'";     
                lsResult = m_dao.searchEntities(strSql);
            }
        }catch(Exception er){
            throw new Exception("���ݲֿ�ID�����Ҫ����ȷ�ϵ���Ʒ����������" + er.getMessage());
        }
        return lsResult;
    }
    
}
