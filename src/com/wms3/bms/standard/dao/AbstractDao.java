package com.wms3.bms.standard.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;

/**
 * ����: DAO��������
 * @author hug 2012-9-21
 * @since WMS 3.0
 */
public abstract class AbstractDao{
    /** ���ݿ������DAO���� */
    public EntityDAO m_dao;
    /**
     * ����:����DAO
     * @author hug 2012-9-21 
     * @return
     */
    public EntityDAO getEntityDAO() {
        return m_dao;
    }
    /** 
     * ����: ִ�в�ѯ��䷵�ؽ��
     * @author hug 2012-9-21 
     * @return
     * @throws Exception
     */
    public List<?> querySQL(String strSQL) throws Exception {
        return m_dao.searchEntities(strSQL);
    }

    /**
     * ����:ִ��HQL��䣨�޸ġ�ɾ����
     * @author hug 2012-9-21 
     * @param strSQL
     * @throws Exception
     */
    public void excuteSQL(String strSQL) throws Exception {
        m_dao.updateSql(strSQL);     
    }
    /**
     * ����: ����ִ��HQL��䣨�޸ġ�ɾ����
     * @author hug 2012-9-27 
     * @param lsSQL
     * @throws Exception
     */
    public void excuteBatchSQL(List lsSQL) throws Exception{
        m_dao.deleteSqlList(lsSQL);
    }
    
    /**
     * ����:���ݿ����ж��Ƿ����ɵ�������
     * @author hug 2012-9-26 
     * @param whAreaId  ����ID
     * @return
     * @throws Exception
     */
    public boolean isCreateTask(String whAreaId) throws Exception {
        try{
            if(whAreaId != null){
                String strSql = "select wh.whAreaId from BaseWharea as wh where wh.whAreaId='" + whAreaId + "' and wh.istask='Y'";
                List lsResult = m_dao.searchEntities(strSql);
                if(lsResult != null && lsResult.size()>0){
                    return true; //���ɵ�������
                }
            }  
        }catch(Exception er){
            throw new Exception("���ݿ����ж��Ƿ����ɵ����������" + er.getMessage());
        }
        return false;
    }
    /**
     * ����:�����޸Ļ�λ״̬
     * @author hug 2012-9-27 
     * @param lsSQL
     * @throws Exception
     */
    public void updateSpaceStatus(List<String> lsSQL) throws Exception {
        Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            //�޸Ļ�λ״̬
            if(lsSQL != null){
                String strSql = null;//�޸Ļ�λ״̬��SQL���
                for(int i = 0; i < lsSQL.size(); i++){
                    strSql = lsSQL.get(i);
                    session.createQuery(strSql).executeUpdate(); 
                }
            }
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("�޸Ļ�λ״̬����"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }     
    }

}
