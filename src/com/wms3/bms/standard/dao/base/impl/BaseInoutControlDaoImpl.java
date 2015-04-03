package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseInoutControlDao;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BaseInoutControl;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

public class BaseInoutControlDaoImpl implements IBaseInoutControlDao {

	/** ���ݿ������DAO���� */
    protected EntityDAO m_dao;
    
	public BaseInoutControlDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}
	
	@Override
	public void addBaseInoutControl(BaseInoutControl Control) throws Exception {
		m_dao.save(Control);
	}

    public List<BaseInoutControl> getlsInoutControl() throws Exception {
        List<BaseInoutControl> ls = null;
        try{
            String strSql = "from BaseInoutControl where 1=1";
                        
            ls = m_dao.searchEntities(strSql);
            
        }catch(Exception er){
            throw new Exception("��ѯ����⿨���б����" + er.getMessage());
        }
        return ls;
    }
	
	/**
	 * ����:����⿨�أ��������,��������һ������
	 * @param ls
	 * @param Control
	 * @throws Exception
	 */
	public void DelInoutControlAdd(List<BaseInoutControl> ls, BaseInoutControl Control) throws Exception {
		
		Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            
            if(ls != null){
            	BaseInoutControl cntol = null;
                
                for(int i = 0; i < ls.size(); i++){
                	
                	cntol = (BaseInoutControl)ls.get(i);
                    //ɾ��
                    session.delete(cntol);
                }
            }
            
            //����
            session.save(Control);	
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("����⿨�أ��������,��������һ�����ݳ���"+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
		
	}
	
}
