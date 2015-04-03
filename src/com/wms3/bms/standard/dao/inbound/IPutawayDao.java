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
 * ����: �ϼ����ݿ����DAO��ӿ�
 * @author hug 2012-8-30
 * @since WMS 3.0
 */
public interface IPutawayDao extends IDao {

    /**
     * ����: �����ջ���¼ID����ջ���¼
     * @author hug 2012-8-30 
     * @param strTransId    �ջ���¼ID
     * @return
     * @throws Exception
     */
    public InboundReceiptTransaction getTransReceiptById(String strTransId) throws Exception;
    
    /** 
     * ����:���ӿ��
     * @author hug 2012-9-4 
     * @param lsStorage         ����б�
     * @param strJobId          ��ҵID
     * @param strJobStatus      ��ҵ״̬
     * @param strSpaceId        ��λID
     * @param strSpaceStatus    ��λ״̬
     * @throws Exception
     */
    public void addStorage(List<InventoryStorage> lsStorage, String strJobId, String strJobStatus, String strSpaceId, String strSpaceStatus) throws Exception;
    
    public void addStorage(BaseCargospace space, List<InventoryStorage> lsStorage, String strJobId, String strJobStatus, String strSpaceId, String strSpaceStatus, String strOldSpaceId, String strOldSpaceStatus) throws Exception;
    
    
    /**
     * ����:�����ջ���ID����ϼܵ���ҵ��Ϣ
     * @author hug 2012-9-5 
     * @param strInvoiceId  �ջ���ID
     * @return
     * @throws Exception
     */
    public List getPutawayJob(String strInvoiceId) throws Exception;
    
    
    /**
     * ����:�����ϼ���ҵ
     * @author hug 2012-9-6 
     * @param lsObj
     * @throws Exception
     */
    public void addPutawayJob(List<Object[]> lsObj) throws Exception;
    /**
     * ����:���������ϼ���ҵ
     * @author hug 2012-9-7 
     * @param lsObj
     * @param strSql    
     * @throws Exception
     */
    public void addPutawayJob(List<Object[]> lsObj, String strSql) throws Exception;
    /**
     * ����:�����ϼ���ҵ
     * @author hug 2012-8-31 
     * @param job           �ϼ���ҵ
     * @param lsJobDetail   �ϼ���ҵ��ϸ�б�
     * @param lsTransSql    �޸��ջ���¼�ϼ�������SQL���
     * @param task          ��������
     * @throws Exception
     */
    public void addPutawayJob(InoutJob job, List<InoutJobdetail> lsJobDetail, List<String> lsTransSql, ScheduleTask task )throws Exception;
    
    /**
     * ����:��øð�װһ�е�����
     * @author hug 2012-9-7 
     * @param packid        ��װID
     * @return
     * @throws Exception
     */
    public int getPackageTrayNum(String packid) throws Exception;
    
    public void cancelPutaway(String strJobSql, List<String> lsTrasnsSQL, List<String> lsInvoiceSQL, List<String> lsDetailSQL, String strSpaceSQL) throws Exception;
}
