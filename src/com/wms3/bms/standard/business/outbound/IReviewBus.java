package com.wms3.bms.standard.business.outbound;

/**
 * ����: ����ҵ����
 * @author hug 2012-10-19
 * @since WMS 3.0
 */
public interface IReviewBus {
    /** 
     * ����: �����ݸ���
     * @author hug 2012-10-19 
     * @param invoiceid         ���ⵥID
     * @param invoicedetailid   ���ⵥ��ϸID
     * @param renum             ��������
     * @param usercode          ������
     * @return
     * @throws Exception
     */
    public String invoiceReview(String invoiceid, String invoicedetailid, double renum, String usercode) throws Exception;
    /**
     * ����: ȡ�����ݸ���
     * @author hug 2012-10-19 
     * @param invoicedetailid     ���ⵥ��ϸID
     * @param usercode
     * @return
     * @throws Exception
     */
    public String deleteInvoiceReview(String invoicedetailid, String usercode) throws Exception;
    /**
     * ����: ȡ����ϸ����
     * @author hug 2012-10-19 
     * @param strId     ����ID
     * @param usercode
     * @return
     * @throws Exception
     */
    public String deleteDetailReview(String strId, String usercode) throws Exception;
    /**
     * ����: ����ϸ����
     * @author hug 2012-10-19 
     * @param invoiceid         ���ⵥID
     * @param invoicedetailid   ���ⵥ��ϸID
     * @param jobid             ��ҵID
     * @param jobdetailid       ��ҵ��ϸID
     * @param renum             ��������
     * @param usercode          ������
     * @return
     * @throws Exception
     */
    public String detailReview(String invoiceid, String invoicedetailid, String jobid, String jobdetailid, double renum, String usercode) throws Exception;
    /**
     * ����: ���ݳ��ⵥID�����ҵ��ϸ�Ĳ�ѯ��SQL
     * @author hug 2012-10-22 
     * @param strInvoiceId   ���ⵥID
     * @return
     */
    public String getJobDetailQuerySQL(String strInvoiceId);
    /**
     * ����:���ݳ��ⵥID�����ҵ��ϸ�Ĳ�ѯ�ܼ�¼��SQL
     * @author hug 2012-10-22 
     * @param strInvoiceId    ���ⵥID
     * @return
     */
    public String getJobDetailCountSQL(String strInvoiceId);
    
    public String getReviewQuerySQL(String strInvoiceId);
    public String getReviewCountSQL(String strInvoiceId);
    /**
     * ���ܣ����ݳ��ⵥ��ϸID�жϸ�����Ϣ�Ƿ����
     * @param strId ���ⵥ��ϸID
     */
    public boolean getOutBoundCroByOutBoundDelId(String strId) throws Exception;
    /**
     * ���ܣ��ж϶����Ƿ񸴺ˣ�1:�����ݸ���; 2:���������ҵ���ˣ�
     * @param orderId
     * @param flag
     * @return
     * @throws Exception
     */
    public boolean isCheckedByHeader(String orderId, String flag) throws Exception;
}
