package com.wms3.bms.standard.dao.outbound;

import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * 描述: 货确认DAO类接口
 * @author hug 2012-10-9
 * @since WMS 3.0
 */
public interface ISendDao extends IDao{
    
    /**
     * 功能：A客户移动到暂存区
     * @author hug 2012-5-21 
     * @param inventoryStorage  暂存区  
     * @param strJobSQL         修改作业详细数量
     * @param strAInvoiceSQL    修改A单据详细的分配数量
     * @param strAInvoiceSQL    删除复核信息的SQL
     * @throws Exception
     */
    public void sendAmoveZ(InventoryStorage inventoryStorage, String strJobSQL, String strAInvoiceSQL,String strCrossSQL) throws Exception;
    /**
     * 功能：A客户移动到暂存区
     * @author hug 2012-5-21 
     * @param inventoryStorage  暂存区  
     * @param strJobSQL         删除作业及作业明细
     * @param strAInvoiceSQL    修改A单据详细的分配数量
     * @throws Exception
     */
    public void sendAmoveZD(InventoryStorage inventoryStorage, InoutJob job,InoutJobdetail detail, String strAInvoiceSQL) throws Exception;
    /**
     * 功能：A客户移动到暂存区
     * @author hug 2012-5-21 
     * @param inventoryStorage  暂存区  
     * @param strJobSQL         修改作业详细数量
     * @param strAInvoiceSQL    修改A单据详细的分配数量
     * @throws Exception
     */
    public void sendAmoveZ(InventoryStorage inventoryStorage, String strJobSQL, String strAInvoiceSQL) throws Exception;
    /**
     * 功能：A客户移动到B客户
     * @author hug 2012-5-23 
     * @param ajobDetail    修改A客户作业详细数量的SQL
     * @param bjob          增加B客户作业
     * @param bjobDetail    增加B客户作业详细
     * @param strAInvoiceSQL    修改A客户单据详细的分配数量SQL
     * @param strBInvoiceSQL    修改B客户单据详细的分配数量SQL
     * @throws Exception
     */
    public void sendAmoveB(String ajobDetail, InoutJob bjob, InoutJobdetail bjobDetail, String strAInvoiceSQL, String strBInvoiceSQL) throws Exception;
    
    /**
     * 功能：暂存区移动到A客户
     * @author hug 2012-5-23 
     * @param strInventorySql   修改或删除暂存区的SQL
     * @param ajob              A客户作业
     * @param ajobDetail        A客户作业详细
     * @param strInvoiceSQL     修改A客户单据详细的分配数量SQL
     * @throws Exception
     */
    public void sendZmoveA(String strInventorySql, InoutJob ajob, InoutJobdetail ajobDetail, String  strInvoiceSQL) throws Exception;
    
    /**
     * 功能：出库单发货确认操作
     * @author hug 2012-5-24 
     * @param strInvoiceSQL         出库单SQL
     * @param lsInvoiceDetailSQL    出库单详细SQL
     * @throws Exception
     */
    public void outInvoiceFHQR(String strInvoiceSQL, List<String> lsInvoiceDetailSQL) throws Exception;
    
    /**
     * 功能：出库单详细发货确认操作
     * @author hug 2012-5-24 
     * @param strSQL
     * @throws Exception
     */
    public void outInvoiceDetailFHQR(String strSQL) throws Exception;
    
    /**
     * 功能：根据出库单查询没发货确认的单据详细信息
     * @author hug 2012-5-24 
     * @param strOutId  出库单ID
     * @return
     * @throws Exception
     */
    public List getNoFHQRDetail(String strOutId) throws Exception;
    
    /**
     * 功能: 根据仓库ID获得暂存区
     * @author hug 2012-10-29 
     * @param warehouseid   仓库ID
     * @return
     * @throws Exception
     */
    public List getZCInventory(String warehouseid) throws Exception;
    /**
     * 功能: 根据仓库ID获得暂存区
     * @author hug 2012-10-29 
     * @param warehouseid   仓库ID
     * @return
     * @throws Exception
     */
    public List getZCInventorybyTray(String warehouseid,String zcwhareaid,String traycode) throws Exception;
    
    /**
     * 功能: 根据托盘条码或箱条码获得单据已出库的作业及作业详细(已完成作业)
     * @author hug 2012-10-30 
     * @param invoiceid     单据ID
     * @param traycode      托盘条码
     * @param boxcode       箱条码
     * @return
     * @throws Exception
     */
    public List getJobAndJobDetailByCode(String invoiceid, String traycode, String boxcode) throws Exception;
}
