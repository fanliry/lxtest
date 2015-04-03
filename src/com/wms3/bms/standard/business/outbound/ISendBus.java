package com.wms3.bms.standard.business.outbound;

import java.util.List;

import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * ����: ����ȷ��ҵ��ӿ���
 * @author hug 2012-10-9
 * @since WMS 3.0
 */
public interface ISendBus {
    
    /**
     * ���ܣ�A�ͻ����ݴ���
     * @author hug 2012-5-21 
     * @param strJobDetailId    ��ҵ��ϸ
     * @param num               ����
     * @param weight            ë��
     * @param netweight         ����
     * @param volume            ���
     * @param strUserCode       �û�
     * @param strShiftid        ���
     * @param warehouseId       �ֿ�ID
     * @param whAreaId          ����ID���ݴ���ID��
     * @param whAreaSpaceId     �ݴ����Ļ�λID
     * @return
     * @throws Exception
     */
    public String getAtoZ(String strJobDetailId, double num, double weight, double netweight, double volume, String strUserCode, String strShiftid, String warehouseId, String whAreaId, String whAreaSpaceId) throws Exception;
    
    /**
     * ����:A�ͻ���B�ͻ�
     * @author hug 2012-5-23 
     * @param strBInvoiceId         B�ͻ�����
     * @param strBInvoiceDetailId   B�ͻ�������ϸ
     * @param strJobDetailId        ��ҵ��ϸID
     * @param num           ����
     * @param weight        ë��
     * @param netweight     ����
     * @param volume        ���
     * @param strUserCode   �û�
     * @param strShiftid    ���
     * @return
     * @throws Exception
     */
    public String getAtoB(String strBInvoiceId, String strBInvoiceDetailId, String strJobDetailId, double num, double weight, double netweight, double volume, String strUserCode, String strShiftid) throws Exception;
    
    /**
     * ���ܣ�A�ͻ��������ݴ���
     * @author hug 2012-5-21 
     * @param strJobDetailIds   ��ҵ��ϸID�б�
     * @param strUserCode       �û�
     * @param strShiftid        ���
     * @param warehouseId       �ֿ�ID
     * @param whAreaId          ����ID���ݴ���ID��
     * @param whAreaSpaceId     �ݴ����Ļ�λID
     * @return
     * @throws Exception
     */
    public String getBatchAtoZ(String strJobDetailIds, String strUserCode, String strShiftid, String warehouseId, String whAreaId, String whAreaSpaceId) throws Exception;
    
    /**
     * ����:A�ͻ�������B�ͻ�
     * @author hug 2012-5-23 
     * @param strBInvoiceId         B�ͻ�����
     * @param strBInvoiceDetailId   B�ͻ�������ϸ
     * @param strJobDetailIds       ��ҵ��ϸID�б�
     * @param strUserCode   �û�
     * @param strShiftid    ���
     * @return
     * @throws Exception
     */
    public String getBatchAtoB(String strBInvoiceId, String strBInvoiceDetailId, String strJobDetailIds, String strUserCode, String strShiftid) throws Exception;
    
    
    /**
     * ���ܣ��ݴ�����A�ͻ�
     * @author hug 2012-5-23 
     * @param strAInvoiceDetailId   A�ͻ�����ID
     * @param strInventoryId        �ݴ���
     * @param num                   ����
     * @param weight                ë��
     * @param netweight             ����
     * @param volume                ���
     * @param strUserCode           �û�
     * @param strShiftid            ���
     * @return
     * @throws Exception
     */
    public String getZtoA(String strAInvoiceDetailId, String strInventoryId, double num, double weight, double netweight, double volume, String strUserCode, String strShiftid) throws Exception;
    
    /**
     * ���ܣ������ݷ���ȷ��
     * @author hug 2012-5-24 
     * @param strInvoiceId  ���ⵥID
     * @param strUserCode   �û�
     * @param strShiftid    ���
     * @return
     * @throws Exception
     */
    public String outInvoiceFHQR(String strInvoiceId, String strUserCode, String strShiftid) throws Exception;
    /**
     * ���ܣ���������ϸ����ȷ��
     * @author hug 2012-5-24 
     * @param strInvoiceDetailId    ���ⵥ��ϸID
     * @param strUserCode           �û�
     * @param strShiftid            ���
     * @return
     * @throws Exception
     */
    public String outInvoiceDetailFHQR(String strInvoiceDetailId, String strUserCode, String strShiftid) throws Exception;
    
   
    
    /**
     * ����:���ݵ���ID�͵�����ϸID�����ҵ����ҵ��ϸ
     * @author hug 2012-10-10 
     * @param invoiceid         ����ID
     * @param invoicedetailid   ������ϸID
     * @return
     * @throws Exception
     */
    public List getJobAndJobDetail(String invoiceid, String invoicedetailid) throws Exception;
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
     * @param traycode   ��������
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
    
    /**
     * ���ܣ�������ҵID�����ҵ
     * @author hug 2012-3-19 
     * @param strJobId  ��ҵID
     * @return
     * @throws Exception
     */
    public InoutJob getJobById(String strJobId) throws Exception;
    /**
     * ����:������ҵ��ϸID�����ҵ��ϸ
     * @author hug 2012-10-9 
     * @param strJobDetailId    ��ҵ��ϸID
     * @return
     * @throws Exception
     */
    public InoutJobdetail getJobDetailByDetailId(String strJobDetailId) throws Exception;
}
