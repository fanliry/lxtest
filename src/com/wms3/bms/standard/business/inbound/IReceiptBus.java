package com.wms3.bms.standard.business.inbound;

import java.util.List;

import com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction;

/**
 * 
 * 描述: 收货业务接口类
 * @author hug 2012-8-23
 * @since WMS 3.0
 */
public interface IReceiptBus {
    /**
     * 功能：根据收货单ID获得收货单详细查询的SQL语句(状态为0-开单1-部分收货)
     * @author hug 2012-8-23 
     * @param strInReceiptId    收货单ID
     * @return
     */
    public String getInReceiptDetailQuerySQL(String strInReceiptId);
    
    /**
     * 功能：根据收货单ID获得收货单详细查询总记录的SQL语句(状态为0-开单1-部分收货)
     * @author hug 2012-8-23 
     * @param strInReceiptId    收货单ID
     * @return
     */
    public String getInReceiptDetailCountSQL(String strInReceiptId);
    
    /**
     * 功能：根据收货单详细ID获得收货单详细的收货任务的查询SQL语句
     * @author hug 2012-8-23 
     * @param strReceiptDetailId    收货单详细ID
     * @return
     */
    public String getReceiptTaskQuerySQL(String strReceiptDetailId);
    
    /**
     * 功能：根据收货单详细ID获得收货单详细的收货任务的查询总记录的SQL语句
     * @author hug 2012-8-23 
     * @param strReceiptDetailId    收货单详细ID
     * @return
     */
    public String getReceiptTaskCountSQL(String strReceiptDetailId);
    /**
     * 功能:根据收货单ID获得收货记录的查询SQL语句
     * @author hug 2012-9-11 
     * @param strInvoiceId  收货单ID
     * @return
     */
    public String getReceiptTransQuerySQL(String strInvoiceId);
    /**
     * 功能:根据收货单详细ID获得收货单详细的收货任务的查询总记录的SQL语句
     * @author hug 2012-9-11 
     * @param strInvoiceId  收货单ID
     * @return
     */
    public String getReceiptTransCountSQL(String strInvoiceId);
    
    /**
     * 功能:根据收货单ID获得收货任务列表
     * @author hug 2012-8-28 
     * @param strInReceiptId    收货单ID
     * @return
     * @throws Exception
     */
    public List<InboundReceiptTransaction> getReceiptTransactionToIvoiceId(String strInReceiptId) throws Exception;
    
    /**
     * 功能: 执行收货
     * @author hug 2012-8-27 
     * @param invoiceid         收货单ID
     * @param invoicedetailid   收货单详细ID
     * @param strLotid          批次类型ID
     * @param strLotatt1        批次属性1
     * @param strLotatt2        批次属性2 
     * @param strLotatt3        批次属性3
     * @param strLotatt4        批次属性4
     * @param strLotatt5        批次属性5
     * @param strLotatt6        批次属性6
     * @param strLotatt7        批次属性7
     * @param strLotatt8        批次属性8
     * @param strLotatt9        批次属性9
     * @param strLotatt10       批次属性10
     * @param strLotatt11       批次属性11
     * @param strLotatt12       批次属性12
     * @param num               收货数量
     * @param weight            收货毛重
     * @param netweight         收货净重
     * @param volume            收货体积
     * @param rejectednum       拒收数量
     * @param rejectcode        拒收代码
     * @param rejectreason      拒收原因
     * @param holdnum           冻结数量
     * @param holdcode          冻结代码
     * @param holdreason        冻结原因
     * @param userCode          操作人
     * @return
     * @throws Exception
     */
    public String excuteReceipt(String invoiceid, String invoicedetailid, String strLotid, String strLotatt1, String strLotatt2, String strLotatt3, String strLotatt4, String strLotatt5, String strLotatt6, String strLotatt7, String strLotatt8, String strLotatt9, String strLotatt10, String strLotatt11, String strLotatt12, double num, double weight, double netweight, double volume, double rejectednum, String rejectcode, String rejectreason, double holdnum, String holdcode, String holdreason, String userCode) throws Exception;
    /** 
     * 功能:取消收货
     * @author hug 2012-9-11 
     * @param invoiceid     收货单ID
     * @param strTransId    收货记录ID
     * @return
     * @throws Exception
     */
    public String cancelReceipt(String invoiceid, String strTransId) throws Exception;
    
    /**
     * 功能: 执行RF收货
     * @author hug 2012-8-27 
     * @param invoiceid         收货单ID
     * @param invoicedetailid   收货单详细ID
     * @param strLotid          批次类型ID
     * @param strLotatt1        批次属性1
     * @param strLotatt2        批次属性2 
     * @param strLotatt3        批次属性3
     * @param strLotatt4        批次属性4
     * @param strLotatt5        批次属性5
     * @param strLotatt6        批次属性6
     * @param strLotatt7        批次属性7
     * @param strLotatt8        批次属性8
     * @param strLotatt9        批次属性9
     * @param strLotatt10       批次属性10
     * @param strLotatt11       批次属性11
     * @param strLotatt12       批次属性12
     * @param num               收货数量
     * @param weight            收货毛重
     * @param netweight         收货净重
     * @param volume            收货体积
     * @param userCode          操作人
     * @return
     * @throws Exception
     */
    public String excuteRFReceipt(String invoiceid, String invoicedetailid, String strLotid, String strLotatt1, String strLotatt2, String strLotatt3, String strLotatt4, String strLotatt5, String strLotatt6, String strLotatt7, String strLotatt8, String strLotatt9, String strLotatt10, String strLotatt11, String strLotatt12, double num, double weight, double netweight, double volume, String userCode) throws Exception;
    
    /**
     * 功能:绑定托盘条码
     * @author hug 2012-9-4 
     * @param jobid     作业ID
     * @param traycode  托盘条码
     * @return
     * @throws Exception
     */
    public String bindTray(String jobid, String traycode) throws Exception;
}
