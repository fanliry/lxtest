package com.wms3.bms.standard.business.outbound;

import java.util.List;

import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * ����: ���ҵ��ӿ�
 * @author hug 2012-9-19
 * @since WMS 3.0
 */
public interface IPickBus {
    /**
     * ����:���ݳ��ⵥID���δ�����ҵ�Ĳ�ѯSQL���
     * @author hug 2012-9-19    linestatus='0'
     * @param strInvoiceId  ���ⵥID
     * @return
     */
    public String getPickJobQuerySQL(String strInvoiceId);
    /**
     * ����:���ݳ��ⵥID���δ�����ҵ�Ĳ�ѯ�ܼ�¼��SQL���
     * @author hug 2012-9-19   linestatus='0'
     * @param strInvoiceId  ���ⵥID
     * @return
     */
    public String getPickJobCountSQL(String strInvoiceId);
    /**
     * ����:������ҵID�����ҵ��ϸ�б�
     * @author hug 2012-9-19 
     * @param strJobId
     * @return
     * @throws Exception
     */
    public List<InoutJobdetail> getJobDetailsById(String strJobId) throws Exception;
    
    /**
     * ����: ���ݳ��ⵥ�ͳ��ⵥ��ϸID��ѯ������ҵ
     * @author hug 2012-9-27 
     * @param invoiceid         ���ⵥID 
     * @param invoicedetailid   ���ⵥ��ϸID
     * @return
     * @throws Exception
     */
    public List getPickJob(String invoiceid, String invoicedetailid) throws Exception;
    /**
     * ����: ���ݳ��ⵥ�ͳ��ⵥ��ϸID��ѯ������ҵ��ϸ
     * @author hug 2012-9-27 
     * @param invoiceid         ���ⵥID
     * @param invoicedetailid   ���ⵥ��ϸID
     * @return
     * @throws Exception
     */
    public List getPickJobDetail(String invoiceid, String invoicedetailid) throws Exception;
    
    /**
     * ����: ִ�м��
     * @author hug 2012-10-24 
     * @param invoiceid     ���ⵥID
     * @param jobdetailid   ��ҵ��ϸID
     * @param strUserCode
     * @return
     * @throws Exception
     */
    public String executePick(String invoiceid, String jobdetailid, String strUserCode) throws Exception;

}
