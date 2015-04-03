package com.wms3.bms.standard.business.job;

import java.util.List;

import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * 描述: 作业业务类操作接口
 * @author hug 2012-9-4
 * @since WMS 3.0
 */
public interface IJobBus {
    //  RF生成作业锁(多人同时操作RF时，只能排队进行，一个完了，下一个才能保存)
    public static byte[] job_lock = new byte[0]; 
    /**
     * 功能:增加作业（只有一条作业详细）
     * @author hug 2012-9-5 
     * @param job       作业
     * @param jobDetail 作业详细
     * @return
     * @throws Exception
     */
    public String addOneJob(InoutJob job, InoutJobdetail jobDetail) throws Exception;
    
   
    public String getJobDetailByTraycode(String traycode,String warehouseID) throws Exception; 
    
    
    public String GetZCHLInventoryByTraycode(String traycode,String warehouseID) throws Exception;
    
    public String GetJobInfoByTraycode(String traycode) throws Exception;
    
    public List getJobDetailByInvoiceid(String invoiceid) throws Exception;
    
    public InoutJob createInoutJob(String strNo1,String strUserCode,String strTime,String strtoWhAreaId,String strtoCargospaceId,String warehouseid,InventoryStorage inStroStorage) throws Exception;
    
    public InoutJobdetail createInoutJobDetail(String strNo1,String strUserCode,String strTime,String strtoWhAreaId,String strtoCargospaceId,String warehouseid,InventoryStorage inStroStorage) throws Exception;
    
}
