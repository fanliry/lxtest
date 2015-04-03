package com.wms3.bms.standard.dao.outbound;


import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

/**
 * ����: �������DAO�ӿ���
 * @author hug 2012-9-21
 * @since WMS 3.0
 */
public interface IAssginDao extends IDao {
    /**
     * ����: ���ݿ��ID��ÿ��
     * @author hug 2012-9-26 
     * @param strInventoryId    ���ID
     * @return
     * @throws Exception
     */
    public InventoryStorage getInventoryStorageById(String strInventoryId) throws Exception;
    /**
     * ����: ���ݻ�λID�����������ѯ���
     * @author hug 2012-9-28 
     * @param strSpaceId    ��λID
     * @param strTrayCode   ��������
     * @return
     * @throws Exception
     */
    public List<InventoryStorage> getInventoryStorageBySpaceId(String strSpaceId, String strTrayCode) throws Exception;
    
    public void addAssginJob(List<InoutJob> lsJob, List<ScheduleTask> lsTask, List<InoutJobdetail> lsJobDetail, List<String> lsStorageSQL, List<String> lsInvoiceSQL, String strSpaceSQL,OutboundInvoiceHeader outboundInvoice) throws Exception;
    
}
