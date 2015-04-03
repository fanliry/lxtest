package com.wms3.bms.standard.dao.inbound;

import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction;

/**
 * 
 * ����: �ջ����ݿ����DAO��ӿ�
 * @author hug 2012-8-27
 * @since WMS 3.0
 */
public interface IReceiptDao extends IDao {
    /**
     * 
     * ����:�����ջ����׼�¼
     * @author hug 2012-8-28 
     * @param trans     �ջ����׼�¼
     * @param strSql    �޸��ջ�����ϸ���ջ���������������򶳽�����
     * @throws Exception
     */
    public void addReceiptTrans(InboundReceiptTransaction trans, String strSql) throws Exception;
     
    /**
     * ����:�����ջ���ID����ջ������б�
     * @author hug 2012-8-28 
     * @param strInReceiptId    �ջ���ID
     * @return
     * @throws Exception
     */
    public List<InboundReceiptTransaction> getReceiptTransactionToIvoiceId(String strInReceiptId) throws Exception;
    
    /**
     * ����:����������
     * @author hug 2012-9-4 
     * @param jobid     ��ҵID
     * @param traycode  ��������
     * @return
     * @throws Exception
     */
    public void bindTray(String jobid, String traycode) throws Exception;
    /**
     * ����: ȡ���ջ���SQL
     * @author hug 2012-9-11 
     * @param strDetailSql  1:�޸��ջ�����ϸ���ջ�������״̬����Ϊ�����ջ�״̬
     * @param strInvoiceSql 2:�޸��ջ�����״̬ ,��Ϊ�����ջ�״̬1
     * @param strTransSql   3:�޸��ջ���¼��״̬����Ϊȡ���ջ�״̬5
     * @throws Exception
     */
    public void cancelReceiptSQL(String strDetailSql, String strInvoiceSql, String strTransSql) throws Exception;
    
}
