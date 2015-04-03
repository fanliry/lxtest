package com.wms3.bms.standard.dao.outbound;

import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * ����: ��ȷ��DAO��ӿ�
 * @author hug 2012-10-9
 * @since WMS 3.0
 */
public interface ISendDao extends IDao{
    
    /**
     * ���ܣ�A�ͻ��ƶ����ݴ���
     * @author hug 2012-5-21 
     * @param inventoryStorage  �ݴ���  
     * @param strJobSQL         �޸���ҵ��ϸ����
     * @param strAInvoiceSQL    �޸�A������ϸ�ķ�������
     * @param strAInvoiceSQL    ɾ��������Ϣ��SQL
     * @throws Exception
     */
    public void sendAmoveZ(InventoryStorage inventoryStorage, String strJobSQL, String strAInvoiceSQL,String strCrossSQL) throws Exception;
    /**
     * ���ܣ�A�ͻ��ƶ����ݴ���
     * @author hug 2012-5-21 
     * @param inventoryStorage  �ݴ���  
     * @param strJobSQL         ɾ����ҵ����ҵ��ϸ
     * @param strAInvoiceSQL    �޸�A������ϸ�ķ�������
     * @throws Exception
     */
    public void sendAmoveZD(InventoryStorage inventoryStorage, InoutJob job,InoutJobdetail detail, String strAInvoiceSQL) throws Exception;
    /**
     * ���ܣ�A�ͻ��ƶ����ݴ���
     * @author hug 2012-5-21 
     * @param inventoryStorage  �ݴ���  
     * @param strJobSQL         �޸���ҵ��ϸ����
     * @param strAInvoiceSQL    �޸�A������ϸ�ķ�������
     * @throws Exception
     */
    public void sendAmoveZ(InventoryStorage inventoryStorage, String strJobSQL, String strAInvoiceSQL) throws Exception;
    /**
     * ���ܣ�A�ͻ��ƶ���B�ͻ�
     * @author hug 2012-5-23 
     * @param ajobDetail    �޸�A�ͻ���ҵ��ϸ������SQL
     * @param bjob          ����B�ͻ���ҵ
     * @param bjobDetail    ����B�ͻ���ҵ��ϸ
     * @param strAInvoiceSQL    �޸�A�ͻ�������ϸ�ķ�������SQL
     * @param strBInvoiceSQL    �޸�B�ͻ�������ϸ�ķ�������SQL
     * @throws Exception
     */
    public void sendAmoveB(String ajobDetail, InoutJob bjob, InoutJobdetail bjobDetail, String strAInvoiceSQL, String strBInvoiceSQL) throws Exception;
    
    /**
     * ���ܣ��ݴ����ƶ���A�ͻ�
     * @author hug 2012-5-23 
     * @param strInventorySql   �޸Ļ�ɾ���ݴ�����SQL
     * @param ajob              A�ͻ���ҵ
     * @param ajobDetail        A�ͻ���ҵ��ϸ
     * @param strInvoiceSQL     �޸�A�ͻ�������ϸ�ķ�������SQL
     * @throws Exception
     */
    public void sendZmoveA(String strInventorySql, InoutJob ajob, InoutJobdetail ajobDetail, String  strInvoiceSQL) throws Exception;
    
    /**
     * ���ܣ����ⵥ����ȷ�ϲ���
     * @author hug 2012-5-24 
     * @param strInvoiceSQL         ���ⵥSQL
     * @param lsInvoiceDetailSQL    ���ⵥ��ϸSQL
     * @throws Exception
     */
    public void outInvoiceFHQR(String strInvoiceSQL, List<String> lsInvoiceDetailSQL) throws Exception;
    
    /**
     * ���ܣ����ⵥ��ϸ����ȷ�ϲ���
     * @author hug 2012-5-24 
     * @param strSQL
     * @throws Exception
     */
    public void outInvoiceDetailFHQR(String strSQL) throws Exception;
    
    /**
     * ���ܣ����ݳ��ⵥ��ѯû����ȷ�ϵĵ�����ϸ��Ϣ
     * @author hug 2012-5-24 
     * @param strOutId  ���ⵥID
     * @return
     * @throws Exception
     */
    public List getNoFHQRDetail(String strOutId) throws Exception;
    
    /**
     * ����: ���ݲֿ�ID����ݴ���
     * @author hug 2012-10-29 
     * @param warehouseid   �ֿ�ID
     * @return
     * @throws Exception
     */
    public List getZCInventory(String warehouseid) throws Exception;
    /**
     * ����: ���ݲֿ�ID����ݴ���
     * @author hug 2012-10-29 
     * @param warehouseid   �ֿ�ID
     * @return
     * @throws Exception
     */
    public List getZCInventorybyTray(String warehouseid,String zcwhareaid,String traycode) throws Exception;
    
    /**
     * ����: ��������������������õ����ѳ������ҵ����ҵ��ϸ(�������ҵ)
     * @author hug 2012-10-30 
     * @param invoiceid     ����ID
     * @param traycode      ��������
     * @param boxcode       ������
     * @return
     * @throws Exception
     */
    public List getJobAndJobDetailByCode(String invoiceid, String traycode, String boxcode) throws Exception;
}
