package com.wms3.bms.standard.dao.inbound;

import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.inbound.InboundReceiptDetail;
import com.wms3.bms.standard.entity.inbound.InboundReceiptHeader;

/**
 * 
 * 描述: 收货单数据库操作DAO类接口
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public interface IInboundReceiptDao extends IDao {
    
    /**
     * 功能：增加收货单
     * @author hug 2012-8-16 
     * @param receiptInvoice    收货单
     * @return
     * @throws Exception
     */
    public void addInBoundReceipt(InboundReceiptHeader receiptInvoice) throws Exception;
    
    /**
     * 功能：根据收货单ID获得收货单
     * @author hug 2012-8-16 
     * @param strReceiptId      收货单ID
     * @return
     * @throws Exception
     */
    public InboundReceiptHeader getInBoundReceiptInvoiceById(String strReceiptId) throws Exception;
    
    /**
     * 功能：修改收货单
     * @author hug 2012-8-17 
     * @param receiptInvoice    收货单
     * @throws Exception
     */
    public void updateInBoundReceipt(InboundReceiptHeader receiptInvoice) throws Exception;
    
    /**
     * 功能：增加收货单详细
     * @author hug 2012-8-17 
     * @param receiptDetail     收货单详细
     * @throws Exception
     */
    public void addInBoundReceiptDetail(InboundReceiptDetail receiptDetail) throws Exception;
    
    /**
     * 功能：修改收货单详细
     * @author hug 2012-8-17 
     * @param receiptDetail     收货单详细
     * @throws Exception
     */
    public void updateInBoundReceiptDetail(InboundReceiptDetail receiptDetail) throws Exception;
    
    /**
     * 功能：根据收货单ID获得收货单详细列表
     * @author hug 2012-8-17 
     * @param strReceiptId      收货单ID
     * @return
     * @throws Exception
     */
    public List<InboundReceiptDetail> getInBoundReceiptDetailsById(String strReceiptId) throws Exception;
    
    /**
     * 功能：根据收货单详细ID获得收货单详细
     * @author hug 2012-8-17 
     * @param strDetailId       收货单详细ID
     * @return
     * @throws Exception
     */
    public InboundReceiptDetail getInBoundReceiptDetailByDetailId(String strDetailId) throws Exception;
    
    /**
     * 功能：删除收货单
     * @author hug 2012-8-17 
     * @param strReceiptId      收货单ID
     * @throws Exception
     */
    public void deleteInBoundReceipt(String strReceiptId) throws Exception;
    
    /**
     * 功能：删除收货单详细
     * @author hug 2012-8-17 
     * @param strDetailId       收货单详细ID
     * @throws Exception
     */
    public void deleteInBoundReceiptDetail(String strDetailId) throws Exception;
    
    /**
     * 
     * 功能:判断是否全部收货完成   全部收货完成true,部分收货为false
     * @author hug 2012-8-27 
     * @param strInvoiceId  收货单ID
     * @return
     * @throws Exception
     */
    public boolean ifReceiptFinish(String strInvoiceId) throws Exception;
    /**
     * 
     * 功能:判断是否全部上架完成   全部上架完成true,部分上架为false
     * @author hug 2012-9-10 
     * @param strInvoiceId  收货单ID
     * @return
     * @throws Exception
     */
    public boolean ifPutawayFinish(String strInvoiceId) throws Exception;
    
    /**
     * 功能:判断收货单详细的上架记录是否全部上架完成   全部上架完成true,部分上架为false
     * @author hug 2012-9-10 
     * @param strDetailId   收货单详细ID
     * @return
     * @throws Exception
     */
    public boolean ifPutawayReceiptDetailFinish(String strDetailId) throws Exception;
    
    /**
     * 功能: 获得未收货完成的收货单(状态0-开单1-部分收货)
     * @author hug 2012-9-3 
     * @param strWarehouseId    仓库ID
     * @return
     * @throws Exception
     */
    public List<InboundReceiptHeader> getNoReceiptFinish(String strWarehouseId) throws Exception;
    
    /**
     * 功能:获得需要上架的收货单 (状态不为0-开单 5-完全上架)
     * @author hug 2012-9-3 
     * @param strWarehouseId
     * @return
     * @throws Exception
     */
    public List<InboundReceiptHeader> getNeedPutawayInvoice(String strWarehouseId) throws Exception;
    
    /**
     * 功能：根据收货单ID获得未上架的收货单详细列表
     * @author hug 2012-8-17 
     * @param strReceiptId      收货单ID
     * @return
     * @throws Exception
     */
    public List<InboundReceiptDetail> getNoPutawayInBoundReceiptDetailsById(String strReceiptId) throws Exception;

}
