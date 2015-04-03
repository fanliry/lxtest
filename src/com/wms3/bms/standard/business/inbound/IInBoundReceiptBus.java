package com.wms3.bms.standard.business.inbound;

import java.util.List;

import com.wms3.bms.standard.entity.inbound.InboundReceiptDetail;
import com.wms3.bms.standard.entity.inbound.InboundReceiptHeader;

/**
 * 
 * 描述: 收货单管理接口类
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public interface IInBoundReceiptBus {
    
    /**
     * 功能：增加收货单
     * @author hug 2012-8-16 
     * @param receiptInvoice    收货单
     * @return
     * @throws Exception
     */
    public String addInBoundReceipt(InboundReceiptHeader receiptInvoice) throws Exception;
    
    /**
     * 功能：修改收货单
     * @author hug 2012-8-17 
     * @param receiptInvoice    收货单
     * @return
     * @throws Exception
     */
    public String updateInBoundReceipt(InboundReceiptHeader receiptInvoice) throws Exception;
    
    /**
     * 功能：增加收货单详细
     * @author hug 2012-8-16 
     * @param invoiceDetail     收货单详细
     * @param strInReceiptId    收货单ID
     * @return
     * @throws Exception
     */
    public String addInBoundReceiptDetail(InboundReceiptDetail invoiceDetail, String strInReceiptId) throws Exception;
    
    /**
     * 功能：修改收货单详细
     * @author hug 2012-8-17 
     * @param receiptDetail     收货单详细
     * @return
     * @throws Exception
     */
    public String updateInBoundReceiptDetail(InboundReceiptDetail receiptDetail) throws Exception;
    
    /**
     * 功能：根据收货单ID获得收货单
     * @author hug 2012-8-16 
     * @param strReceiptId      收货单ID
     * @return
     * @throws Exception
     */
    public InboundReceiptHeader getInBoundReceiptInvoiceById(String strReceiptId) throws Exception;
    
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
     * 功能：删除收货单或收货单详细
     * @author hug 2012-8-17 
     * @param strReceiptId          收货单ID
     * @param strReceiptDetailId    收货单详细ID
     * @param strFlag     1：删除收货单；2：删除收货单详细
     * @return
     * @throws Exception
     */
    public String deleteReceiptInvoice(String strReceiptId, String strReceiptDetailId, String strFlag) throws Exception;
    /**
     * 功能：查询收货单的查询语句
     * @author hug 2012-8-17 
     * @param warehouseid   仓库ID
     * @param ownerid       货主
     * @param instatus      状态
     * @param intype        类型
     * @param screatedate   开始日期
     * @param ecreatedate   结束日期
     * @return
     */
    public String getInboundReceiptQuerySQL(String warehouseid, String ownerid, String instatus, String intype, String screatedate, String ecreatedate);
    
    /**
     * 功能：查询收货单的查询总记录语句
     * @author hug 2012-8-17 
     * @param warehouseid   仓库ID
     * @param ownerid       货主
     * @param instatus      状态
     * @param intype        类型
     * @param screatedate   开始日期
     * @param ecreatedate   结束日期
     * @return
     */
    public String getInboundReceiptCountSQL(String warehouseid, String ownerid, String instatus, String intype, String screatedate, String ecreatedate);
    
   
}
