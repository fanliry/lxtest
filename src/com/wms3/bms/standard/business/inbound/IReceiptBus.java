package com.wms3.bms.standard.business.inbound;

import java.util.List;

import com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction;

/**
 * 
 * ����: �ջ�ҵ��ӿ���
 * @author hug 2012-8-23
 * @since WMS 3.0
 */
public interface IReceiptBus {
    /**
     * ���ܣ������ջ���ID����ջ�����ϸ��ѯ��SQL���(״̬Ϊ0-����1-�����ջ�)
     * @author hug 2012-8-23 
     * @param strInReceiptId    �ջ���ID
     * @return
     */
    public String getInReceiptDetailQuerySQL(String strInReceiptId);
    
    /**
     * ���ܣ������ջ���ID����ջ�����ϸ��ѯ�ܼ�¼��SQL���(״̬Ϊ0-����1-�����ջ�)
     * @author hug 2012-8-23 
     * @param strInReceiptId    �ջ���ID
     * @return
     */
    public String getInReceiptDetailCountSQL(String strInReceiptId);
    
    /**
     * ���ܣ������ջ�����ϸID����ջ�����ϸ���ջ�����Ĳ�ѯSQL���
     * @author hug 2012-8-23 
     * @param strReceiptDetailId    �ջ�����ϸID
     * @return
     */
    public String getReceiptTaskQuerySQL(String strReceiptDetailId);
    
    /**
     * ���ܣ������ջ�����ϸID����ջ�����ϸ���ջ�����Ĳ�ѯ�ܼ�¼��SQL���
     * @author hug 2012-8-23 
     * @param strReceiptDetailId    �ջ�����ϸID
     * @return
     */
    public String getReceiptTaskCountSQL(String strReceiptDetailId);
    /**
     * ����:�����ջ���ID����ջ���¼�Ĳ�ѯSQL���
     * @author hug 2012-9-11 
     * @param strInvoiceId  �ջ���ID
     * @return
     */
    public String getReceiptTransQuerySQL(String strInvoiceId);
    /**
     * ����:�����ջ�����ϸID����ջ�����ϸ���ջ�����Ĳ�ѯ�ܼ�¼��SQL���
     * @author hug 2012-9-11 
     * @param strInvoiceId  �ջ���ID
     * @return
     */
    public String getReceiptTransCountSQL(String strInvoiceId);
    
    /**
     * ����:�����ջ���ID����ջ������б�
     * @author hug 2012-8-28 
     * @param strInReceiptId    �ջ���ID
     * @return
     * @throws Exception
     */
    public List<InboundReceiptTransaction> getReceiptTransactionToIvoiceId(String strInReceiptId) throws Exception;
    
    /**
     * ����: ִ���ջ�
     * @author hug 2012-8-27 
     * @param invoiceid         �ջ���ID
     * @param invoicedetailid   �ջ�����ϸID
     * @param strLotid          ��������ID
     * @param strLotatt1        ��������1
     * @param strLotatt2        ��������2 
     * @param strLotatt3        ��������3
     * @param strLotatt4        ��������4
     * @param strLotatt5        ��������5
     * @param strLotatt6        ��������6
     * @param strLotatt7        ��������7
     * @param strLotatt8        ��������8
     * @param strLotatt9        ��������9
     * @param strLotatt10       ��������10
     * @param strLotatt11       ��������11
     * @param strLotatt12       ��������12
     * @param num               �ջ�����
     * @param weight            �ջ�ë��
     * @param netweight         �ջ�����
     * @param volume            �ջ����
     * @param rejectednum       ��������
     * @param rejectcode        ���մ���
     * @param rejectreason      ����ԭ��
     * @param holdnum           ��������
     * @param holdcode          �������
     * @param holdreason        ����ԭ��
     * @param userCode          ������
     * @return
     * @throws Exception
     */
    public String excuteReceipt(String invoiceid, String invoicedetailid, String strLotid, String strLotatt1, String strLotatt2, String strLotatt3, String strLotatt4, String strLotatt5, String strLotatt6, String strLotatt7, String strLotatt8, String strLotatt9, String strLotatt10, String strLotatt11, String strLotatt12, double num, double weight, double netweight, double volume, double rejectednum, String rejectcode, String rejectreason, double holdnum, String holdcode, String holdreason, String userCode) throws Exception;
    /** 
     * ����:ȡ���ջ�
     * @author hug 2012-9-11 
     * @param invoiceid     �ջ���ID
     * @param strTransId    �ջ���¼ID
     * @return
     * @throws Exception
     */
    public String cancelReceipt(String invoiceid, String strTransId) throws Exception;
    
    /**
     * ����: ִ��RF�ջ�
     * @author hug 2012-8-27 
     * @param invoiceid         �ջ���ID
     * @param invoicedetailid   �ջ�����ϸID
     * @param strLotid          ��������ID
     * @param strLotatt1        ��������1
     * @param strLotatt2        ��������2 
     * @param strLotatt3        ��������3
     * @param strLotatt4        ��������4
     * @param strLotatt5        ��������5
     * @param strLotatt6        ��������6
     * @param strLotatt7        ��������7
     * @param strLotatt8        ��������8
     * @param strLotatt9        ��������9
     * @param strLotatt10       ��������10
     * @param strLotatt11       ��������11
     * @param strLotatt12       ��������12
     * @param num               �ջ�����
     * @param weight            �ջ�ë��
     * @param netweight         �ջ�����
     * @param volume            �ջ����
     * @param userCode          ������
     * @return
     * @throws Exception
     */
    public String excuteRFReceipt(String invoiceid, String invoicedetailid, String strLotid, String strLotatt1, String strLotatt2, String strLotatt3, String strLotatt4, String strLotatt5, String strLotatt6, String strLotatt7, String strLotatt8, String strLotatt9, String strLotatt10, String strLotatt11, String strLotatt12, double num, double weight, double netweight, double volume, String userCode) throws Exception;
    
    /**
     * ����:����������
     * @author hug 2012-9-4 
     * @param jobid     ��ҵID
     * @param traycode  ��������
     * @return
     * @throws Exception
     */
    public String bindTray(String jobid, String traycode) throws Exception;
}
