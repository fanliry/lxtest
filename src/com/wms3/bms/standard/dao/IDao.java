package com.wms3.bms.standard.dao;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;

/**
 * ����: DAO���ӿ�
 * @author hug 2012-9-21
 * @since WMS 3.0
 */
public interface IDao {
    /**
     * ���ܣ�������ݿ����DAO��
     * @author hug 2012-3-13 
     * @return
     */
    public EntityDAO getEntityDAO();
    
    /** 
     * ����: ִ�в�ѯ��䲢���ؽ��
     * @author hug 2012-9-21 
     * @return
     * @throws Exception
     */
    public List<?> querySQL(String strSQL) throws Exception;
    
    /**
     * 
     * ����:ִ��HQL��䣨�޸ġ�ɾ����
     * @author hug 2012-8-28 
     * @param strSQL
     * @throws Exception
     */
    public void excuteSQL(String strSQL) throws Exception;
    /**
     * ����:����ִ��HQL��䣨�޸ġ�ɾ����
     * @author hug 2012-9-27 
     * @param lsSQL
     * @throws Exception
     */
    public void excuteBatchSQL(List lsSQL) throws Exception;
    
    /**
     * ����:���ݿ����ж��Ƿ����ɵ�������
     * @author hug 2012-9-6 
     * @param whAreaId  ����ID
     * @return
     * @throws Exception
     */
    public boolean isCreateTask(String whAreaId) throws Exception;
    
    /**
     * ����:�����޸Ļ�λ״̬
     * @author hug 2012-9-6 
     * @param lsSQL
     * @throws Exception
     */
    public void updateSpaceStatus(List<String> lsSQL) throws Exception;
}
