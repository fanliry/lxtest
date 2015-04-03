package com.wms3.bms.standard.dao.inbound.impl;

import java.sql.Connection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.AbstractDao;
import com.wms3.bms.standard.dao.inbound.IPutawayDao;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BasePackageMastermesg;
import com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

/**
 * 描述: 上架数据库操作DAO类
 * @author hug 2012-8-30
 * @since WMS 3.0
 */
public class PutawayDaoImpl extends AbstractDao implements IPutawayDao{
    public PutawayDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    /**
     * 根据收货记录ID获得收货记录
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IPutawayDao#getTransReceiptById(java.lang.String)
     */
    public InboundReceiptTransaction getTransReceiptById(String strTransId) throws Exception {
        InboundReceiptTransaction trans = null;
        try{
            if(strTransId != null){
                String strSql = "from InboundReceiptTransaction as retrans where retrans.transreceiptid='" + strTransId + "'";
                List ls = m_dao.searchEntities(strSql);
                if(ls != null && ls.size() > 0){
                    trans = (InboundReceiptTransaction)ls.get(0);
                }
            }
        }catch(Exception er){
            throw new Exception("根据收货记录ID获得收货记录出错：" + er.getMessage());
        }
        return trans;
    }
 
    /**
     * 增加库存
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IPutawayDao#addStorage(java.util.List, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public void addStorage(List<InventoryStorage> lsStorage, String strJobId, String strJobStatus, String strSpaceId, String strSpaceStatus) throws Exception {
        Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            //增加库存
            if(lsStorage != null){
                InventoryStorage storage = null;//库存
                for(int i = 0; i < lsStorage.size(); i++){
                    storage = lsStorage.get(i);
                    session.save(storage);
                }
            }
            //修改作业状态
            String strJobSql = "update InoutJob as injob set injob.status='" + strJobStatus + "' where injob.jobid='" + strJobId + "'";
            session.createQuery(strJobSql).executeUpdate(); 
            //修改货位状态
            if(strSpaceId != null && strSpaceId.trim().length() > 0){
                String strSpaceSql = "update BaseCargospace as spc set spc.csstatus='" + strSpaceStatus + "' where spc.cargoSpaceId='" + strSpaceId + "'";
                session.createQuery(strSpaceSql).executeUpdate(); 
            }
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("上架到库存出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
    }
    public void addStorage(BaseCargospace space, List<InventoryStorage> lsStorage, String strJobId, String strJobStatus, String strSpaceId, String strSpaceStatus, String strOldSpaceId, String strOldSpaceStatus) throws Exception {
        Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();

            //增加库存
            if(lsStorage != null){
                InventoryStorage storage = null;//库存
                for(int i = 0; i < lsStorage.size(); i++){
                    storage = lsStorage.get(i);
                    storage.setCargoSpaceId(space.getCargoSpaceId()); //货位ID
                    storage.setCargoAlleyId(space.getCargoAlleyId()); //巷道ID
                    //storage(space.getCargoAreaId());//货区ID
                    storage.setWhAreaId(space.getWhAreaId());         //库区
                    storage.setWarehouseid(space.getWarehouseid());   //仓库ID
                    session.save(storage);
                }
            }
            //修改作业状态
            String strJobSql = "update InoutJob as injob set injob.status='" + strJobStatus + "'," +
                    " injob.tcargoWhareaId='"+ (space.getWhAreaId() == null ? "" : space.getWhAreaId()) +"',"+
                    "injob.tcargoSpaceId='"+ (space.getCargoSpaceId() == null ? "" : space.getCargoSpaceId()) +"'," +
                    "injob.tcargoAlleyId='"+ (space.getCargoAlleyId() == null ? "" : space.getCargoAlleyId()) +"'  where injob.jobid='" + strJobId + "'";
            session.createQuery(strJobSql).executeUpdate(); 
            //修改货位状态
            if(strSpaceId != null && strSpaceId.trim().length() > 0){
                String strSpaceSql = "update BaseCargospace as spc set spc.csstatus='" + strSpaceStatus + "' where spc.cargoSpaceId='" + strSpaceId + "'";
                session.createQuery(strSpaceSql).executeUpdate(); 
            }
            //修改原货位状态
            if(strOldSpaceId != null && strOldSpaceId.trim().length() > 0){
                String strOldSpaceSql = "update BaseCargospace as spc set spc.csstatus='" + strOldSpaceStatus + "', spc.haspalletnum=spc.haspalletnum-1  where spc.cargoSpaceId='" + strOldSpaceId + "'";
                session.createQuery(strOldSpaceSql).executeUpdate(); 
            }
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("上架到库存出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }
    }
    /**
     * 根据收货单ID获得上架的作业信息
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IPutawayDao#getPutawayJob(java.lang.String)
     */
    public List getPutawayJob(String strInvoiceId) throws Exception {
        List lsResult = null;
        try{
            if(strInvoiceId != null){
                String strSql = "from InoutJob as injob, InoutJobdetail as detail  where injob.jobid=detail.jobid and injob.status!='5' and injob.inOutType='1' and  detail.sinvoiceid='" + strInvoiceId + "'";     
                lsResult = m_dao.searchEntities(strSql);
            }
        }catch(Exception er){
            throw new Exception("根据收货单ID获得上架的作业信息出错：" + er.getMessage());
        }
        return lsResult;
    }
    
    /**
     * 生成上架作业
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IPutawayDao#addPutawayJob(java.util.List)
     */
    public void addPutawayJob(List<Object[]> lsObj) throws Exception {
        StatelessSession session = null; //大批量操作时使用
        Transaction tx = null;
        Connection conn = null;
        try{
            conn = m_dao.getConnection();//从连接池获得连接
            session = m_dao.getSessionFactory().openStatelessSession(conn);
            tx = session.beginTransaction();
            //增加作业
            Object[] obj = null;
            for(int i = 0; i < lsObj.size(); i++){
                obj = lsObj.get(i);
                
                //增加上架作业
                InoutJob job = (InoutJob)obj[0]; //作业
                session.insert("InoutJob", job);
                
                //增加调度任务
                ScheduleTask task = (ScheduleTask)obj[1];       //调度任务
                if(task != null){
                    session.insert(task);
                }
                
                //增加作业详细
                List<InoutJobdetail> lsJobDetail = (List<InoutJobdetail>)obj[2];//作业详细
                InoutJobdetail jobDetail = null;//上架作业详细
                for(int k = 0; k < lsJobDetail.size(); k++){
                    jobDetail = lsJobDetail.get(k);
                    session.insert("InoutJobdetail", jobDetail);
                }
                
                //增加要修改的收货记录
                List<String> lsTrans = (List<String>)obj[3];    //要修改的收货记录
                String strTransSql = null;
                for(int k = 0; k < lsTrans.size(); k++){
                    strTransSql = lsTrans.get(k);
                    session.createQuery(strTransSql).executeUpdate();
                }       
            } 
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("生成上架作业出错："+er.getMessage());
        }finally{
            if(session != null)
            {
                session.close();
            }
            if(conn != null){
                conn.close(); //关闭连接
            }
        }    
    }
    public void addPutawayJob(List<Object[]> lsObj, String strSql) throws Exception {
        StatelessSession session = null; //大批量操作时使用
        Transaction tx = null;
        Connection conn = null;
        try{
            conn = m_dao.getConnection();//从连接池获得连接
            session = m_dao.getSessionFactory().openStatelessSession(conn);
            tx = session.beginTransaction();
            //增加作业
            Object[] obj = null;
            for(int i = 0; i < lsObj.size(); i++){
                obj = lsObj.get(i);
                
                //增加上架作业
                InoutJob job = (InoutJob)obj[0]; //作业
                session.insert("InoutJob", job);
                
                //增加调度任务
                ScheduleTask task = (ScheduleTask)obj[1];       //调度任务
                if(task != null){
                    session.insert(task);
                }
                
                //增加作业详细
                List<InoutJobdetail> lsJobDetail = (List<InoutJobdetail>)obj[2];//作业详细
                InoutJobdetail jobDetail = null;//上架作业详细
                for(int k = 0; k < lsJobDetail.size(); k++){
                    jobDetail = lsJobDetail.get(k);
                    session.insert("InoutJobdetail", jobDetail);
                }    
            } 
            //修改收货记录
            session.createQuery(strSql).executeUpdate();
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("生成上架作业出错："+er.getMessage());
        }finally{
            if(session != null)
            {
                session.close();
            }
            if(conn != null){
                conn.close(); //关闭连接
            }
        }    
    }
    
    /**
     * 增加上架作业
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IPutawayDao#addPutawayJob(com.wms3.bms.standard.entity.job.InoutJob, java.util.List, java.util.List, com.wms3.bms.standard.entity.schedule.ScheduleTask)
     */
    public void addPutawayJob(InoutJob job, List<InoutJobdetail> lsJobDetail, List<String> lsTransSql, ScheduleTask task) throws Exception {
        Session session = null;
        Transaction tx = null;
        try{
            session = m_dao.getSession();
            tx = session.beginTransaction();
            //增加上架作业
            session.save("InoutJob", job);
            //增加上架作业详细
            if(lsJobDetail != null){
                InoutJobdetail jobDetail = null;//上架作业详细
                for(int i = 0; i < lsJobDetail.size(); i++){
                    jobDetail = lsJobDetail.get(i);
                    session.save("InoutJobdetail", jobDetail);
                }
            }
            //修改收货记录的上架数量
            if(lsTransSql != null){
                String strSql = null;//修改收货记录的SQL语句
                for(int i = 0; i < lsTransSql.size(); i++){
                    strSql = lsTransSql.get(i);
                    session.createQuery(strSql).executeUpdate(); 
                }
            }
            //增加调度任务
            if(task != null){
                session.save(task);           
            }
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("增加上架作业出错："+er.getMessage());
        }finally{
            m_dao.closeSession(session);
        }     
    }
    /**
     * 获得该包装一托的数量
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IPutawayDao#getPackageTrayNum(java.lang.String)
     */
    public int getPackageTrayNum(String packid) throws Exception {
        int iPL = 0;
        try{
            String strSql = "from BasePackageMastermesg as pm where pm.packid='" + packid + "' and pm.pkgunit='PL' and pm.inused='Y'";
            List ls = m_dao.searchEntities(strSql);
            if(ls != null && ls.size()>0){
                BasePackageMastermesg packMaster = (BasePackageMastermesg)ls.get(0);
                iPL = packMaster.getQty();
            }
        }catch(Exception er){
            throw new Exception("获得该包装一托的数量出错：" + er.getMessage());
        }
        return iPL;
    }
    /**
     * 取消上架
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IPutawayDao#cancelPutaway(java.lang.String, java.util.List, java.util.List, java.util.List, java.lang.String)
     */
    public void cancelPutaway(String strJobSql, List<String> lsTrasnsSQL, List<String> lsInvoiceSQL, List<String> lsDetailSQL, String strSpaceSQL) throws Exception 
    {   StatelessSession session = null; //大批量操作时使用
        Transaction tx = null;
        Connection conn = null;
        try{
            conn = m_dao.getConnection();//从连接池获得连接
            session = m_dao.getSessionFactory().openStatelessSession(conn);
            tx = session.beginTransaction();
            //1: 修改作业状态
            session.createQuery(strJobSql).executeUpdate();
            
            //2：修改收货记录的状态，及上架数量
            for(int i = 0; i < lsTrasnsSQL.size(); i++){
                String strTransSql = lsTrasnsSQL.get(i);   
                session.createQuery(strTransSql).executeUpdate();
            }
            //3：修改收货单的状态
            for(int i = 0; i < lsInvoiceSQL.size(); i++){
                String strInvoiceSql = lsInvoiceSQL.get(i); 
                session.createQuery(strInvoiceSql).executeUpdate();
            }
            //4：修改收货单详细的状态
            for(int i = 0; i < lsDetailSQL.size(); i++){
                String strDetailSql = lsDetailSQL.get(i); 
                session.createQuery(strDetailSql).executeUpdate();
            }        
            //5: 修改收货记录
            session.createQuery(strSpaceSQL).executeUpdate();
            
            tx.commit();    
        }catch(Exception er){
            if(tx != null){
                tx.rollback();
            }
            throw new  Exception("生成上架作业出错："+er.getMessage());
        }finally{
            if(session != null)
            {
                session.close();
            }
            if(conn != null){
                conn.close(); //关闭连接
            }
        }    
    }
    /**
     * 功能:根据库区判断是否生成调度任务
     * @author yao 2012-11-22
     * @param whAreaId  库区ID
     * @return
     * @throws Exception
     */
    public boolean isCreateTask(String whAreaId) throws Exception{
    	
    	List ls = null;
    	Boolean flag = false;
		try
		{
			if(whAreaId!=null && whAreaId.length()>0){
				String strHql = "From BaseWharea as t where t.istask='Y' and t.whAreaId='"+whAreaId+"'";
				ls = m_dao.searchEntities(strHql);
			}
			if(ls!=null&&ls.size()>=1){
				flag = true;
			}
		}catch(Exception er)
		{
			throw new Exception("查询库区列表出错:" + er.getMessage());
		}
		return flag;
    }

}
