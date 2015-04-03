package com.wms3.bms.standard.business.outbound;

import java.util.List;

import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * 描述: 发货确认业务接口类
 * @author hug 2012-10-9
 * @since WMS 3.0
 */
public interface ISendBus {
    
    /**
     * 功能：A客户到暂存区
     * @author hug 2012-5-21 
     * @param strJobDetailId    作业详细
     * @param num               数量
     * @param weight            毛重
     * @param netweight         净重
     * @param volume            体积
     * @param strUserCode       用户
     * @param strShiftid        班次
     * @param warehouseId       仓库ID
     * @param whAreaId          库区ID（暂存区ID）
     * @param whAreaSpaceId     暂存区的货位ID
     * @return
     * @throws Exception
     */
    public String getAtoZ(String strJobDetailId, double num, double weight, double netweight, double volume, String strUserCode, String strShiftid, String warehouseId, String whAreaId, String whAreaSpaceId) throws Exception;
    
    /**
     * 功能:A客户到B客户
     * @author hug 2012-5-23 
     * @param strBInvoiceId         B客户单据
     * @param strBInvoiceDetailId   B客户单据详细
     * @param strJobDetailId        作业详细ID
     * @param num           数量
     * @param weight        毛重
     * @param netweight     净重
     * @param volume        体积
     * @param strUserCode   用户
     * @param strShiftid    班次
     * @return
     * @throws Exception
     */
    public String getAtoB(String strBInvoiceId, String strBInvoiceDetailId, String strJobDetailId, double num, double weight, double netweight, double volume, String strUserCode, String strShiftid) throws Exception;
    
    /**
     * 功能：A客户批量到暂存区
     * @author hug 2012-5-21 
     * @param strJobDetailIds   作业详细ID列表
     * @param strUserCode       用户
     * @param strShiftid        班次
     * @param warehouseId       仓库ID
     * @param whAreaId          库区ID（暂存区ID）
     * @param whAreaSpaceId     暂存区的货位ID
     * @return
     * @throws Exception
     */
    public String getBatchAtoZ(String strJobDetailIds, String strUserCode, String strShiftid, String warehouseId, String whAreaId, String whAreaSpaceId) throws Exception;
    
    /**
     * 功能:A客户批量到B客户
     * @author hug 2012-5-23 
     * @param strBInvoiceId         B客户单据
     * @param strBInvoiceDetailId   B客户单据详细
     * @param strJobDetailIds       作业详细ID列表
     * @param strUserCode   用户
     * @param strShiftid    班次
     * @return
     * @throws Exception
     */
    public String getBatchAtoB(String strBInvoiceId, String strBInvoiceDetailId, String strJobDetailIds, String strUserCode, String strShiftid) throws Exception;
    
    
    /**
     * 功能：暂存区到A客户
     * @author hug 2012-5-23 
     * @param strAInvoiceDetailId   A客户单据ID
     * @param strInventoryId        暂存区
     * @param num                   数量
     * @param weight                毛重
     * @param netweight             净重
     * @param volume                体积
     * @param strUserCode           用户
     * @param strShiftid            班次
     * @return
     * @throws Exception
     */
    public String getZtoA(String strAInvoiceDetailId, String strInventoryId, double num, double weight, double netweight, double volume, String strUserCode, String strShiftid) throws Exception;
    
    /**
     * 功能：按单据发货确认
     * @author hug 2012-5-24 
     * @param strInvoiceId  出库单ID
     * @param strUserCode   用户
     * @param strShiftid    班次
     * @return
     * @throws Exception
     */
    public String outInvoiceFHQR(String strInvoiceId, String strUserCode, String strShiftid) throws Exception;
    /**
     * 功能：按单据详细发货确认
     * @author hug 2012-5-24 
     * @param strInvoiceDetailId    出库单详细ID
     * @param strUserCode           用户
     * @param strShiftid            班次
     * @return
     * @throws Exception
     */
    public String outInvoiceDetailFHQR(String strInvoiceDetailId, String strUserCode, String strShiftid) throws Exception;
    
   
    
    /**
     * 功能:根据单据ID和单据详细ID获得作业及作业详细
     * @author hug 2012-10-10 
     * @param invoiceid         单据ID
     * @param invoicedetailid   单据详细ID
     * @return
     * @throws Exception
     */
    public List getJobAndJobDetail(String invoiceid, String invoicedetailid) throws Exception;
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
     * @param traycode   托盘条码
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
    
    /**
     * 功能：根据作业ID获得作业
     * @author hug 2012-3-19 
     * @param strJobId  作业ID
     * @return
     * @throws Exception
     */
    public InoutJob getJobById(String strJobId) throws Exception;
    /**
     * 功能:根据作业详细ID获得作业详细
     * @author hug 2012-10-9 
     * @param strJobDetailId    作业详细ID
     * @return
     * @throws Exception
     */
    public InoutJobdetail getJobDetailByDetailId(String strJobDetailId) throws Exception;
}
