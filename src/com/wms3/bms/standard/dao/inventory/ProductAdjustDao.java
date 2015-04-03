package com.wms3.bms.standard.dao.inventory;

import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.inventory.InventoryTransferDetail;
import com.wms3.bms.standard.entity.inventory.InventoryTransferHeader;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;

/**
 * ����: ��Ʒ����DAO��ӿ�
 * @author yao 2012-9-26
 * @since WMS 3.0
 */
public interface ProductAdjustDao extends IDao{   
    /**
     * ���ܣ�������Ʒ������
     * @author yao 2012-5-16 
     * @param inventorytransfer
     * @throws Exception
     */
    public void addProductAdjust(InventoryTransferHeader inventorytransfer) throws Exception;
    /**
     * ���ܣ�������Ʒ������
     * @author yao 2012-9-26 
     * @param inventorytransfer
     * @throws Exception
     */
    public void updateProductAdjust(InventoryTransferHeader inventorytransfer) throws Exception;
    /**
     * ����: ������Ʒ��������ϸ
     * @author yao 2012-9-26
     * @param inventorytransferDetail    ��Ʒ��������ϸ
     * @throws Exception
     */
    public void addProductAdjustDetail(InventoryTransferDetail inventorytransferDetail) throws Exception;
    
    /**
     * ����: �޸���Ʒ��������ϸ
     * @author yao 2012-9-26
     * @param inventorytransferDetail    ��Ʒ��������ϸ
     * @throws Exception
     */
    public void updateProductAdjustDetail(InventoryTransferDetail inventorytransferDetail) throws Exception;
    
    /**
     * ���ܣ�ɾ����Ʒ������
     * @author yao 2012-9-26 
     * @param inventorytransferId      ��Ʒ������ID
     * @throws Exception
     */
    public void deleteProductAdjust(String inventorytransferId) throws Exception;
    
    /**
     * ���ܣ�ɾ����Ʒ��������ϸ
     * @author yao 2012-9-26 
     * @param inventorytransferdetailId       ��Ʒ��������ϸID
     * @throws Exception
     */
    public void deleteProductAdjustDetail(String inventorytransferdetailId) throws Exception;
    
    /**
     * ���ܣ����ݳ��ⵥID�����Ʒ������
     * @author yao 2012-9-26 
     * @param inventorytransferId ��Ʒ������ID
     * @return
     * @throws Exception
     */
    public InventoryTransferHeader getProductAdjustById(String inventorytransferId) throws Exception;
    /**
     * ����:������Ʒ��������ϸID�����Ʒ��������ϸ
     * @author yao 2012-9-26
     * @param inventorytransferdetailId    ��Ʒ��������ϸID
     * @return
     * @throws Exception
     */
    public InventoryTransferDetail getProductAdjustDetailById(String inventorytransferdetailId) throws Exception;
    /**
     * ���ܣ�������Ʒ������ID�����Ʒ��������ϸ
     * @author yao 2012-9-26 
     * @param inventorytransferId  ��Ʒ������ID
     * @return
     * @throws Exception
     */
    public List<InventoryTransferDetail> getProductAdjustDetailsById(String inventorytransferId) throws Exception;
    
    /**
     * ����:���ݲֿ�ID�����Ҫ����ȷ�ϵ���Ʒ������
     * @author yao 2012-9-26
     * @param warehouseid   �ֿ�ID
     * @return
     * @throws Exception
     */
    public List<InventoryTransferHeader> getSendProductAdjust(String warehouseid) throws Exception;
    
    

}
