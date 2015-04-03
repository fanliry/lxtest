package com.wms3.bms.standard.dao.outbound;


import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

/**
 * 描述: 出库分配DAO接口类
 * @author hug 2012-9-21
 * @since WMS 3.0
 */
public interface IAssginDao extends IDao {
    /**
     * 功能: 根据库存ID获得库存
     * @author hug 2012-9-26 
     * @param strInventoryId    库存ID
     * @return
     * @throws Exception
     */
    public InventoryStorage getInventoryStorageById(String strInventoryId) throws Exception;
    /**
     * 功能: 根据货位ID和托盘条码查询库存
     * @author hug 2012-9-28 
     * @param strSpaceId    货位ID
     * @param strTrayCode   托盘条码
     * @return
     * @throws Exception
     */
    public List<InventoryStorage> getInventoryStorageBySpaceId(String strSpaceId, String strTrayCode) throws Exception;
    
    public void addAssginJob(List<InoutJob> lsJob, List<ScheduleTask> lsTask, List<InoutJobdetail> lsJobDetail, List<String> lsStorageSQL, List<String> lsInvoiceSQL, String strSpaceSQL,OutboundInvoiceHeader outboundInvoice) throws Exception;
    
}
