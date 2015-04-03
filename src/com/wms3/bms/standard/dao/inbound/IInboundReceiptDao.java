package com.wms3.bms.standard.dao.inbound;

import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.inbound.InboundReceiptDetail;
import com.wms3.bms.standard.entity.inbound.InboundReceiptHeader;

/**
 * 
 * ����: �ջ������ݿ����DAO��ӿ�
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public interface IInboundReceiptDao extends IDao {
    
    /**
     * ���ܣ������ջ���
     * @author hug 2012-8-16 
     * @param receiptInvoice    �ջ���
     * @return
     * @throws Exception
     */
    public void addInBoundReceipt(InboundReceiptHeader receiptInvoice) throws Exception;
    
    /**
     * ���ܣ������ջ���ID����ջ���
     * @author hug 2012-8-16 
     * @param strReceiptId      �ջ���ID
     * @return
     * @throws Exception
     */
    public InboundReceiptHeader getInBoundReceiptInvoiceById(String strReceiptId) throws Exception;
    
    /**
     * ���ܣ��޸��ջ���
     * @author hug 2012-8-17 
     * @param receiptInvoice    �ջ���
     * @throws Exception
     */
    public void updateInBoundReceipt(InboundReceiptHeader receiptInvoice) throws Exception;
    
    /**
     * ���ܣ������ջ�����ϸ
     * @author hug 2012-8-17 
     * @param receiptDetail     �ջ�����ϸ
     * @throws Exception
     */
    public void addInBoundReceiptDetail(InboundReceiptDetail receiptDetail) throws Exception;
    
    /**
     * ���ܣ��޸��ջ�����ϸ
     * @author hug 2012-8-17 
     * @param receiptDetail     �ջ�����ϸ
     * @throws Exception
     */
    public void updateInBoundReceiptDetail(InboundReceiptDetail receiptDetail) throws Exception;
    
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
     * ���ܣ�ɾ���ջ���
     * @author hug 2012-8-17 
     * @param strReceiptId      �ջ���ID
     * @throws Exception
     */
    public void deleteInBoundReceipt(String strReceiptId) throws Exception;
    
    /**
     * ���ܣ�ɾ���ջ�����ϸ
     * @author hug 2012-8-17 
     * @param strDetailId       �ջ�����ϸID
     * @throws Exception
     */
    public void deleteInBoundReceiptDetail(String strDetailId) throws Exception;
    
    /**
     * 
     * ����:�ж��Ƿ�ȫ���ջ����   ȫ���ջ����true,�����ջ�Ϊfalse
     * @author hug 2012-8-27 
     * @param strInvoiceId  �ջ���ID
     * @return
     * @throws Exception
     */
    public boolean ifReceiptFinish(String strInvoiceId) throws Exception;
    /**
     * 
     * ����:�ж��Ƿ�ȫ���ϼ����   ȫ���ϼ����true,�����ϼ�Ϊfalse
     * @author hug 2012-9-10 
     * @param strInvoiceId  �ջ���ID
     * @return
     * @throws Exception
     */
    public boolean ifPutawayFinish(String strInvoiceId) throws Exception;
    
    /**
     * ����:�ж��ջ�����ϸ���ϼܼ�¼�Ƿ�ȫ���ϼ����   ȫ���ϼ����true,�����ϼ�Ϊfalse
     * @author hug 2012-9-10 
     * @param strDetailId   �ջ�����ϸID
     * @return
     * @throws Exception
     */
    public boolean ifPutawayReceiptDetailFinish(String strDetailId) throws Exception;
    
    /**
     * ����: ���δ�ջ���ɵ��ջ���(״̬0-����1-�����ջ�)
     * @author hug 2012-9-3 
     * @param strWarehouseId    �ֿ�ID
     * @return
     * @throws Exception
     */
    public List<InboundReceiptHeader> getNoReceiptFinish(String strWarehouseId) throws Exception;
    
    /**
     * ����:�����Ҫ�ϼܵ��ջ��� (״̬��Ϊ0-���� 5-��ȫ�ϼ�)
     * @author hug 2012-9-3 
     * @param strWarehouseId
     * @return
     * @throws Exception
     */
    public List<InboundReceiptHeader> getNeedPutawayInvoice(String strWarehouseId) throws Exception;
    
    /**
     * ���ܣ������ջ���ID���δ�ϼܵ��ջ�����ϸ�б�
     * @author hug 2012-8-17 
     * @param strReceiptId      �ջ���ID
     * @return
     * @throws Exception
     */
    public List<InboundReceiptDetail> getNoPutawayInBoundReceiptDetailsById(String strReceiptId) throws Exception;

}
