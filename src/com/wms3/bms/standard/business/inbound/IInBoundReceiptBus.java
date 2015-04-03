package com.wms3.bms.standard.business.inbound;

import java.util.List;

import com.wms3.bms.standard.entity.inbound.InboundReceiptDetail;
import com.wms3.bms.standard.entity.inbound.InboundReceiptHeader;

/**
 * 
 * ����: �ջ�������ӿ���
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public interface IInBoundReceiptBus {
    
    /**
     * ���ܣ������ջ���
     * @author hug 2012-8-16 
     * @param receiptInvoice    �ջ���
     * @return
     * @throws Exception
     */
    public String addInBoundReceipt(InboundReceiptHeader receiptInvoice) throws Exception;
    
    /**
     * ���ܣ��޸��ջ���
     * @author hug 2012-8-17 
     * @param receiptInvoice    �ջ���
     * @return
     * @throws Exception
     */
    public String updateInBoundReceipt(InboundReceiptHeader receiptInvoice) throws Exception;
    
    /**
     * ���ܣ������ջ�����ϸ
     * @author hug 2012-8-16 
     * @param invoiceDetail     �ջ�����ϸ
     * @param strInReceiptId    �ջ���ID
     * @return
     * @throws Exception
     */
    public String addInBoundReceiptDetail(InboundReceiptDetail invoiceDetail, String strInReceiptId) throws Exception;
    
    /**
     * ���ܣ��޸��ջ�����ϸ
     * @author hug 2012-8-17 
     * @param receiptDetail     �ջ�����ϸ
     * @return
     * @throws Exception
     */
    public String updateInBoundReceiptDetail(InboundReceiptDetail receiptDetail) throws Exception;
    
    /**
     * ���ܣ������ջ���ID����ջ���
     * @author hug 2012-8-16 
     * @param strReceiptId      �ջ���ID
     * @return
     * @throws Exception
     */
    public InboundReceiptHeader getInBoundReceiptInvoiceById(String strReceiptId) throws Exception;
    
    /**
     * ���ܣ������ջ���ID����ջ�����ϸ�б�
     * @author hug 2012-8-17 
     * @param strReceiptId      �ջ���ID
     * @return
     * @throws Exception
     */
    public List<InboundReceiptDetail> getInBoundReceiptDetailsById(String strReceiptId) throws Exception;
    
    /**
     * ���ܣ������ջ�����ϸID����ջ�����ϸ
     * @author hug 2012-8-17 
     * @param strDetailId       �ջ�����ϸID
     * @return
     * @throws Exception
     */
    public InboundReceiptDetail getInBoundReceiptDetailByDetailId(String strDetailId) throws Exception;
    /**
     * ���ܣ�ɾ���ջ������ջ�����ϸ
     * @author hug 2012-8-17 
     * @param strReceiptId          �ջ���ID
     * @param strReceiptDetailId    �ջ�����ϸID
     * @param strFlag     1��ɾ���ջ�����2��ɾ���ջ�����ϸ
     * @return
     * @throws Exception
     */
    public String deleteReceiptInvoice(String strReceiptId, String strReceiptDetailId, String strFlag) throws Exception;
    /**
     * ���ܣ���ѯ�ջ����Ĳ�ѯ���
     * @author hug 2012-8-17 
     * @param warehouseid   �ֿ�ID
     * @param ownerid       ����
     * @param instatus      ״̬
     * @param intype        ����
     * @param screatedate   ��ʼ����
     * @param ecreatedate   ��������
     * @return
     */
    public String getInboundReceiptQuerySQL(String warehouseid, String ownerid, String instatus, String intype, String screatedate, String ecreatedate);
    
    /**
     * ���ܣ���ѯ�ջ����Ĳ�ѯ�ܼ�¼���
     * @author hug 2012-8-17 
     * @param warehouseid   �ֿ�ID
     * @param ownerid       ����
     * @param instatus      ״̬
     * @param intype        ����
     * @param screatedate   ��ʼ����
     * @param ecreatedate   ��������
     * @return
     */
    public String getInboundReceiptCountSQL(String warehouseid, String ownerid, String instatus, String intype, String screatedate, String ecreatedate);
    
   
}
