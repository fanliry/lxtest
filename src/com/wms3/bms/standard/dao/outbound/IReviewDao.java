package com.wms3.bms.standard.dao.outbound;

import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.outbound.OutboundCrosscheck;

/**
 * ����: ���⸴��DAO��ӿ�
 * @author hug 2012-10-12
 * @since WMS 3.0
 */
public interface IReviewDao extends IDao {
    /**
     * ����:�ж϶����Ƿ񸴺ˣ�1:�����ݸ���; 2:���������ҵ���ˣ�
     * @author hug 2012-10-12 
     * @param orderId   ����ID
     * @param flag      1:�����ݸ���; 2:���������ҵ���� 
     * @return
     * @throws Exception
     */
    public boolean isCheckedByHeader(String orderId, String flag) throws Exception;
    /**
     * ����: ���ӵ��ݸ���
     * @author hug 2012-10-19 
     * @param check
     * @throws Exception
     */
    public void addReview(OutboundCrosscheck check) throws Exception;
    /**
     * ����: �޸ĸ�������
     * @author hug 2012-10-19 
     * @param check
     * @throws Exception
     */
    public void updateReview(OutboundCrosscheck check) throws Exception;
    
    /**
     * ����:ȡ�����ݸ���
     * @author hug 2012-10-22 
     * @param invoicedetailid
     * @throws Exception
     */
    public void deleteInvoiceReview(String invoicedetailid) throws Exception;
    /**
     * ����:ȡ������
     * @author hug 2012-10-19 
     * @param strCheckId    ���⸴��ID
     * @param crosstype     �������� 1:�����ݸ���; 2:���������ҵ����
     * @throws Exception
     */
    public void deleteReview(String strCheckId, String crosstype) throws Exception;
    /**
     * ����:������ҵID����ҵ��ϸID��ø�����Ϣ
     * @author hug 2012-10-19 
     * @param jobid         ��ҵID
     * @param jobdetailid   ��ҵ��ϸID
     * @return
     * @throws Exception
     */
    public OutboundCrosscheck getOutboundCrosscheck(String jobid, String jobdetailid) throws Exception;
    /**
     * ���ܣ����ݳ��ⵥ��ϸid��ø�����Ϣ
     * @param id
     * @return
     * @throws Exception
     */
    public List<OutboundCrosscheck > getOutboundCrossById(String id)throws Exception;
}
