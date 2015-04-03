package com.wms3.bms.standard.business.job;

import java.util.List;

import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * ����: ��ҵҵ��������ӿ�
 * @author hug 2012-9-4
 * @since WMS 3.0
 */
public interface IJobBus {
    //  RF������ҵ��(����ͬʱ����RFʱ��ֻ���Ŷӽ��У�һ�����ˣ���һ�����ܱ���)
    public static byte[] job_lock = new byte[0]; 
    /**
     * ����:������ҵ��ֻ��һ����ҵ��ϸ��
     * @author hug 2012-9-5 
     * @param job       ��ҵ
     * @param jobDetail ��ҵ��ϸ
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
