package com.wms3.bms.standard.business.outbound;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseLotSet;

/**
 * 描述: 出库分配业务接口
 * @author hug 2012-9-20
 * @since WMS 3.0
 */
public interface IAssginBus {
    //出库分配生成任务锁(多人同时分配时，只能排队进行，一个分配完了，下一个才能分配)
    public static byte[] assgin_lock = new byte[0];
    public List<?> getAssginQueryStorage(List<BaseLotSet> lsLot, String ownerid, String warehouseId, String strProductId, String strPackId, String strLotatt1, String strLotatt2, String strLotatt3, String strLotatt4, String strLotatt5, String strLotatt6, String strLotatt7, String strLotatt8, String strLotatt9, String strLotatt10, String strLotatt11, String strLotatt12, String whAreaId, String alleyId, String strTrayCode) throws Exception;
    /**
     * 功能: 手动分配
     * @author hug 2012-11-15 
     * @param invoiceid         出库单ID
     * @param invoicedetailid   出库单详细ID
     * @param strStoMsg         货位、库存列表
     * @param strUserCode
     * @return
     * @throws Exception
     */
    public String assginStorage(String invoiceid, String invoicedetailid, String strStoMsg, String floor, String tsjh, String strUserCode) throws Exception;
    /**
     * 功能: 手动分配
     * @author hug 2012-11-15 
     * @param invoiceid         出库单ID
     * @param invoicedetailid   出库单详细ID
     * @param strStoMsg         货位、库存列表
     * @param strUserCode
     * @return
     * @throws Exception
     */
    public String assginStorageNew(String invoiceid, String invoicedetailid, String strStoMsg, String strUserCode,String fpnumresult) throws Exception;
    /**
     * 功能: 自动分配
     * @author hug 2012-11-15 
     * @param invoiceid
     * @param invoicedetailid
     * @param ownerid
     * @param customerid
     * @param warehouseId
     * @param whAreaId
     * @param strUserCode
     * @return
     * @throws Exception
     */
	public String autoAssginStorage(String invoiceid, String invoicedetailid,
			String ownerid, String customerid, String warehouseId,
			String whAreaId, String strUserCode) throws Exception;
    
    
}
