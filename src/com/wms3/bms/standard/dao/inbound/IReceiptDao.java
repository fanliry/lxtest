package com.wms3.bms.standard.dao.inbound;

import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction;

/**
 * 
 * 描述: 收货数据库操作DAO类接口
 * @author hug 2012-8-27
 * @since WMS 3.0
 */
public interface IReceiptDao extends IDao {
    /**
     * 
     * 功能:增加收货交易记录
     * @author hug 2012-8-28 
     * @param trans     收货交易记录
     * @param strSql    修改收货单详细的收货数量或拒收数量或冻结数量
     * @throws Exception
     */
    public void addReceiptTrans(InboundReceiptTransaction trans, String strSql) throws Exception;
     
    /**
     * 功能:根据收货单ID获得收货任务列表
     * @author hug 2012-8-28 
     * @param strInReceiptId    收货单ID
     * @return
     * @throws Exception
     */
    public List<InboundReceiptTransaction> getReceiptTransactionToIvoiceId(String strInReceiptId) throws Exception;
    
    /**
     * 功能:绑定托盘条码
     * @author hug 2012-9-4 
     * @param jobid     作业ID
     * @param traycode  托盘条码
     * @return
     * @throws Exception
     */
    public void bindTray(String jobid, String traycode) throws Exception;
    /**
     * 功能: 取消收货的SQL
     * @author hug 2012-9-11 
     * @param strDetailSql  1:修改收货单详细的收货数量，状态，改为部分收货状态
     * @param strInvoiceSql 2:修改收货单的状态 ,改为部分收货状态1
     * @param strTransSql   3:修改收货记录的状态，改为取消收货状态5
     * @throws Exception
     */
    public void cancelReceiptSQL(String strDetailSql, String strInvoiceSql, String strTransSql) throws Exception;
    
}
