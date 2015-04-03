package com.wms3.bms.standard.dao.outbound;

import com.wms3.bms.standard.dao.IDao;

/**
 * ����: ���DAO�ӿ�
 * @author hug 2012-10-24
 * @since WMS 3.0
 */
public interface IPickDao extends IDao {
    /**
     * ����:
     * @author hug 2012-10-24 
     * @param strJobDSQL        �޸���ҵ��ϸSQL
     * @param strInvoiceDSQL    �޸ĵ�����ϸSQL
     * @param strStorageSQL     �޸Ŀ���SQL
     * @throws Exception
     */
    public void executePick(String strJobDSQL, String strInvoiceDSQL, String strStorageSQL) throws Exception;
    /**
     * ����: �����ҵ�Ƿ�����ȫ������       true:��ȫ��� false:û�м�����
     * @author hug 2012-10-24 
     * @param strJobId  ��ҵID
     * @return
     * @throws Exception
     */
    public boolean isPickJobFinish(String strJobId) throws Exception;
    /**
     * ����: �����ⵥ��ϸ�Ƿ�����ȫ������  true:��ȫ��� false:û�м�����
     * @author hug 2012-10-24 
     * @param strInvoiceDetailId    ���ⵥ��ϸID
     * @return
     * @throws Exception
     */
    public boolean isPickInvoiceDetailFinish(String strInvoiceDetailId) throws Exception;
    
    /**
     * ����: �����ⵥ�Ƿ�����ȫ�ܻ����    true:��ȫ��� false:û�м�����
     * @author hug 2012-10-24 
     * @param strInvoiceId      ���ⵥID
     * @return
     * @throws Exception
     */
    public boolean isPickInvoiceFinish(String strInvoiceId) throws Exception;

}
