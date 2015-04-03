package com.wms3.bms.standard.business.inbound;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction;
//import com.wms3.bms.standard.entity.job.InoutJobdetail;

/**
 * ����: �ϼ�ҵ��ӿ�
 * @author hug 2012-8-29
 * @since WMS 3.0
 */
public interface IPutawayBus {
    //  �ϼ�����������(����ͬʱ�ϼ�ʱ��ֻ���Ŷӽ��У�һ���ϼ����ˣ���һ�������ϼ�)
    public static byte[] put_lock = new byte[0];
    /**
     * ����:�����ջ���ID����ϼ���ҵ�Ĳ�ѯSQL���
     * @author hug 2012-9-5 
     * @param strInvoiceId  �ջ���ID
     * @return
     */
    public String getPutawayJobQuerySQL(String strInvoiceId);
    /**
     * ����:�����ջ���ID����ϼ���ҵ�Ĳ�ѯ�ܼ�¼��SQL���
     * @author hug 2012-9-5 
     * @param strInvoiceId  �ջ���ID
     * @return
     */
    public String getPutawayJobCountSQL(String strInvoiceId);
    /**
     * ����:������ҵID�����ҵ��ϸ�б�
     * @author hug 2012-9-5 
     * @param strJobId  ��ҵID
     * @return
     * @throws Exception
     */
   // public List<InoutJobdetail> getJobDetailsById(String strJobId) throws Exception;
    
    /**
     * ���ܣ������ջ���ID����ϼ���ҵ�б�(������������ҵ)
     * @author hug 2012-6-5 
     * @param strInvoiceId  �ջ���ID
     * @return
     * @throws Exception
     */
    public List getPutawayJob(String strInvoiceId) throws Exception;
    
    /**
     * ����:�����ջ���¼ID����ջ���¼����
     * @author hug 2012-9-4 
     * @param strTransId    �ջ���¼ID
     * @return
     * @throws Exception
     */
    public InboundReceiptTransaction getTransReceiptById(String strTransId) throws Exception;
    /**
     * ���ܣ������ջ���ID����ջ���¼�Ĳ�ѯSQL���
     * @author hug 2012-8-29 
     * @param strInvoiceId  �ջ���ID
     * @return
     */
    public String getReceiptTransQuerySQL(String strInvoiceId);
    
    /**
     * ����:�����ջ���ID����ջ���¼�Ĳ�ѯ�ܼ�¼��SQL���
     * @author hug 2012-8-29 
     * @param strInvoiceId  �ջ���ID
     * @return
     */
    public String getReceiptTransCountSQL(String strInvoiceId);
    
    /**
     * ���ܣ������ջ���ID�����Ҫ�ϼܵ��ջ���¼�Ĳ�ѯSQL���(״̬��Ϊ 4����ȫ�ϼ� 5��ȡ���ջ�)
     * @author hug 2012-8-29 
     * @param strInvoiceId  �ջ���ID
     * @return
     */
    public String getNeedReceiptTransQuerySQL(String strInvoiceId);
    
    /**
     * ����:�����ջ���ID�����Ҫ�ϼܵ��ջ���¼�Ĳ�ѯ�ܼ�¼��SQL���(״̬��Ϊ 4����ȫ�ϼ� 5��ȡ���ջ�)
     * @author hug 2012-8-29 
     * @param strInvoiceId  �ջ���ID
     * @return
     */
    public String getNeedReceiptTransCountSQL(String strInvoiceId);
    
    
    
    /**
     * ���ܣ������ջ���ID����ջ�����ϸ�Ĳ�ѯSQL���(״̬0-����1-�����ջ�)
     * @author hug 2012-8-29 
     * @param strInvoiceId  �ջ���ID
     * @return
     */
    public String getReceiptDetailQuerySQL(String strInvoiceId);
    
    /**
     * ����:�����ջ���ID����ջ�����ϸ�Ĳ�ѯ�ܼ�¼��SQL���(״̬0-����1-�����ջ�)
     * @author hug 2012-8-29 
     * @param strInvoiceId  �ջ���ID
     * @return
     */
    public String getReceiptDetailCountSQL(String strInvoiceId);
    
    /**
     * ����:�����ϼ�����
     * @author hug 2012-9-7 
     * @param invoiceid     �ջ���ID
     * @param strLineRows   �ϼܵ���ϸ����
     * @param strUserCode   ������
     * @param strTrayCode   ��������
     * @param warehouseid   �ֿ�ID
     * @param whAreaId      ����ID
     * @param putmode       �ջ���ʽ
     * @param request
     * @return
     * @throws Exception
     */
    public String createPutawayTask(String invoiceid, String strLineRows, String strUserCode, String strTrayCode, String warehouseid, String whAreaId, String putmode, HttpServletRequest request) throws Exception;
    /** 
     * ����: ����,�������ϼ�����
     * @author hug 2012-9-10 
     * @param strTransId    �ջ���¼I
     * @param whAreaId      ����
     * @param strLotatt1    ��������1
     * @param strLotatt2    ��������2
     * @param strLotatt3    ��������3
     * @param strLotatt4    ��������4
     * @param strLotatt5    ��������5
     * @param strLotatt6    ��������6
     * @param strLotatt7    ��������7
     * @param strLotatt8    ��������8
     * @param strLotatt9    ��������9
     * @param strLotatt10   ��������10
     * @param strLotatt11   ��������11
     * @param strLotatt12   ��������12
     * @param strUserCode   ������
     * @return
     * @throws Exception
     */
    public String excuteCodeTray(String strTransId, String whAreaId, String strLotatt1, String strLotatt2, String strLotatt3, String strLotatt4, String strLotatt5, String strLotatt6, String strLotatt7, String strLotatt8, String strLotatt9, String strLotatt10, String strLotatt11, String strLotatt12, String strUserCode) throws Exception;
    
    /** 
     * ����:ִ���ϼ�
     * @author hug 2012-9-4 
     * @param strJobId      ��ҵID
     * @param strUserCode   ������
     * @param strNewSpaceId ָ���Ļ�λID       
     * @return
     * @throws Exception
     */
    public String excutePutaway(String strJobId, String strUserCode, String strNewSpaceId) throws Exception;
    /**
     * ����:ȡ���ϼ�
     * @author hug 2012-9-12 
     * @param strJobId  ��ҵID
     * @return
     * @throws Exception
     */
    public String cancelPutaway(String strJobId) throws Exception;
    /**
     * ����: ���ݰ�װ�������ɷŵ���������
     * @author hug 2012-9-10 
     * @param num       ����
     * @param packid    ��װ
     * @return
     * @throws Exception
     */
    public int getTrayNum(double num, String packid) throws Exception;
    /**
     * ����: �ð�װһ���̿ɷŶ�������
     * @author hug 2012-9-10 
     * @param packid    ��װID
     * @return
     * @throws Exception
     */
    public int getOneTrayNumber(String packid) throws Exception;
}
