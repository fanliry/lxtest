package com.wms3.bms.standard.dao.inbound;

import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

/**
 * 描述: 上架数据库操作DAO类接口
 * @author hug 2012-8-30
 * @since WMS 3.0
 */
public interface IPutawayDao extends IDao {

    /**
     * 功能: 根据收货记录ID获得收货记录
     * @author hug 2012-8-30 
     * @param strTransId    收货记录ID
     * @return
     * @throws Exception
     */
    public InboundReceiptTransaction getTransReceiptById(String strTransId) throws Exception;
    
    /** 
     * 功能:增加库存
     * @author hug 2012-9-4 
     * @param lsStorage         库存列表
     * @param strJobId          作业ID
     * @param strJobStatus      作业状态
     * @param strSpaceId        货位ID
     * @param strSpaceStatus    货位状态
     * @throws Exception
     */
    public void addStorage(List<InventoryStorage> lsStorage, String strJobId, String strJobStatus, String strSpaceId, String strSpaceStatus) throws Exception;
    
    public void addStorage(BaseCargospace space, List<InventoryStorage> lsStorage, String strJobId, String strJobStatus, String strSpaceId, String strSpaceStatus, String strOldSpaceId, String strOldSpaceStatus) throws Exception;
    
    
    /**
     * 功能:根据收货单ID获得上架的作业信息
     * @author hug 2012-9-5 
     * @param strInvoiceId  收货单ID
     * @return
     * @throws Exception
     */
    public List getPutawayJob(String strInvoiceId) throws Exception;
    
    
    /**
     * 功能:生成上架作业
     * @author hug 2012-9-6 
     * @param lsObj
     * @throws Exception
     */
    public void addPutawayJob(List<Object[]> lsObj) throws Exception;
    /**
     * 功能:码盘生成上架作业
     * @author hug 2012-9-7 
     * @param lsObj
     * @param strSql    
     * @throws Exception
     */
    public void addPutawayJob(List<Object[]> lsObj, String strSql) throws Exception;
    /**
     * 功能:增加上架作业
     * @author hug 2012-8-31 
     * @param job           上架作业
     * @param lsJobDetail   上架作业详细列表
     * @param lsTransSql    修改收货记录上架数量的SQL语句
     * @param task          调度任务
     * @throws Exception
     */
    public void addPutawayJob(InoutJob job, List<InoutJobdetail> lsJobDetail, List<String> lsTransSql, ScheduleTask task )throws Exception;
    
    /**
     * 功能:获得该包装一托的数量
     * @author hug 2012-9-7 
     * @param packid        包装ID
     * @return
     * @throws Exception
     */
    public int getPackageTrayNum(String packid) throws Exception;
    
    public void cancelPutaway(String strJobSql, List<String> lsTrasnsSQL, List<String> lsInvoiceSQL, List<String> lsDetailSQL, String strSpaceSQL) throws Exception;
}
