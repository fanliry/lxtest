package com.wms3.bms.standard.dao.inventory;

import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.inventory.InventoryTransferDetail;
import com.wms3.bms.standard.entity.inventory.InventoryTransferHeader;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;

/**
 * 描述: 货品调整DAO类接口
 * @author yao 2012-9-26
 * @since WMS 3.0
 */
public interface ProductAdjustDao extends IDao{   
    /**
     * 功能：增加物品调整单
     * @author yao 2012-5-16 
     * @param inventorytransfer
     * @throws Exception
     */
    public void addProductAdjust(InventoryTransferHeader inventorytransfer) throws Exception;
    /**
     * 功能：更新物品调整单
     * @author yao 2012-9-26 
     * @param inventorytransfer
     * @throws Exception
     */
    public void updateProductAdjust(InventoryTransferHeader inventorytransfer) throws Exception;
    /**
     * 功能: 增加物品调整单详细
     * @author yao 2012-9-26
     * @param inventorytransferDetail    物品调整单详细
     * @throws Exception
     */
    public void addProductAdjustDetail(InventoryTransferDetail inventorytransferDetail) throws Exception;
    
    /**
     * 功能: 修改物品调整单详细
     * @author yao 2012-9-26
     * @param inventorytransferDetail    物品调整单详细
     * @throws Exception
     */
    public void updateProductAdjustDetail(InventoryTransferDetail inventorytransferDetail) throws Exception;
    
    /**
     * 功能：删除物品调整单
     * @author yao 2012-9-26 
     * @param inventorytransferId      物品调整单ID
     * @throws Exception
     */
    public void deleteProductAdjust(String inventorytransferId) throws Exception;
    
    /**
     * 功能：删除物品调整单详细
     * @author yao 2012-9-26 
     * @param inventorytransferdetailId       物品调整单详细ID
     * @throws Exception
     */
    public void deleteProductAdjustDetail(String inventorytransferdetailId) throws Exception;
    
    /**
     * 功能：根据出库单ID获得物品调整单
     * @author yao 2012-9-26 
     * @param inventorytransferId 物品调整单ID
     * @return
     * @throws Exception
     */
    public InventoryTransferHeader getProductAdjustById(String inventorytransferId) throws Exception;
    /**
     * 功能:根据物品调整单详细ID获得物品调整单详细
     * @author yao 2012-9-26
     * @param inventorytransferdetailId    物品调整单详细ID
     * @return
     * @throws Exception
     */
    public InventoryTransferDetail getProductAdjustDetailById(String inventorytransferdetailId) throws Exception;
    /**
     * 功能：根据物品调整单ID获得物品调整单详细
     * @author yao 2012-9-26 
     * @param inventorytransferId  物品调整单ID
     * @return
     * @throws Exception
     */
    public List<InventoryTransferDetail> getProductAdjustDetailsById(String inventorytransferId) throws Exception;
    
    /**
     * 功能:根据仓库ID获得需要发货确认的物品调整单
     * @author yao 2012-9-26
     * @param warehouseid   仓库ID
     * @return
     * @throws Exception
     */
    public List<InventoryTransferHeader> getSendProductAdjust(String warehouseid) throws Exception;
    
    

}
