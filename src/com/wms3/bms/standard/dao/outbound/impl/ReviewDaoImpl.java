package com.wms3.bms.standard.dao.outbound.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.AbstractDao;
import com.wms3.bms.standard.dao.outbound.IReviewDao;
import com.wms3.bms.standard.entity.outbound.OutboundCrosscheck;

/**
 * ����: ���⸴��DAO��
 * @author hug 2012-10-12
 * @since WMS 3.0
 */
public class ReviewDaoImpl extends AbstractDao implements IReviewDao {
    
    public ReviewDaoImpl(EntityDAO dao)
    {
        this.m_dao = dao;
    }
    /**
     * �ж϶����Ƿ񸴺ˣ�1:�����ݸ���; 2:���������ҵ���ˣ�
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.ICrosscheckDao#isCheckedByHeader(java.lang.String, java.lang.String)
     */
    public boolean isCheckedByHeader(String orderId, String flag) throws Exception {
        boolean result = false;
        try
        {
            if(orderId != null)
            {
                String strSql = "from OutboundCrosscheck as oc where oc.outstockid='"+orderId+"' and oc.crosstype='" + flag + "'";
                List ls = m_dao.searchEntities(strSql);
                if(ls!=null && ls.size()>0)
                {
                    result = true;
                }
            }
        }catch(Exception er)
        {
            throw new Exception("�ж��Ƿ��Ѱ��������˻򰴷������ҵ���˳���(ReviewDaoImpl.isCheckedByHeader):" + er.getMessage());
        }
        return result;
    }   
    /**
     * ���Ӹ���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.ICrosscheckDao#addReview(com.wms3.bms.standard.entity.outbound.OutboundCrosscheck)
     */
    public void addReview(OutboundCrosscheck check) throws Exception {
        m_dao.save("OutboundCrosscheck", check); 
    }
    /**
     * �޸ĸ�������
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IReviewDao#updateReview(com.wms3.bms.standard.entity.outbound.OutboundCrosscheck)
     */
    public void updateReview(OutboundCrosscheck check) throws Exception {
        m_dao.update("OutboundCrosscheck", check);
    }
    
    /**
     * ȡ�����ݸ���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IReviewDao#deleteInvoiceReview(java.lang.String)
     */
    public void deleteInvoiceReview(String invoicedetailid) throws Exception {
        try{
            String strSql = "delete from OutboundCrosscheck as oc where oc.outstockdetailid='" + invoicedetailid + "' and oc.crosstype='1'";
            m_dao.deleteSql(strSql);
        }catch(Exception er){
            throw new Exception("ɾ�����˳���(ReviewDaoImpl.deleteInvoiceReview):" + er.getMessage());
        } 
    }
    
    /**
     * ȡ������
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IReviewDao#deleteReview(java.lang.String, java.lang.String)
     */
    public void deleteReview(String strCheckId, String crosstype) throws Exception {
        try{
            String strSql = "delete from OutboundCrosscheck as oc where oc.crosscheckid='" + strCheckId + "' and oc.crosstype='" + crosstype + "'";
            m_dao.deleteSql(strSql);
        }catch(Exception er){
            throw new Exception("ɾ�����˳���(ReviewDaoImpl.addReview):" + er.getMessage());
        }   
    }
    /**
     * ������ҵID����ҵ��ϸID��ø�����Ϣ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.outbound.IReviewDao#getOutboundCrosscheck(java.lang.String, java.lang.String)
     */
    public OutboundCrosscheck getOutboundCrosscheck(String jobid, String jobdetailid) throws Exception {
        OutboundCrosscheck check = null;
        try{
            String strSql = " from OutboundCrosscheck as oc where oc.jobid='" + jobid + "' and oc.jobdetailid='" + jobdetailid + "' and oc.crosstype='2'";
            List ls = m_dao.searchEntities(strSql);
            if(ls != null && ls.size() > 0){
                check = (OutboundCrosscheck)ls.get(0);
            }
        }catch(Exception er){
            throw new Exception("������ҵID����ҵ��ϸID��ø�����Ϣ����(ReviewDaoImpl.getOutboundCrosscheck):" + er.getMessage());
        } 
        return check;
    }
	public List<OutboundCrosscheck> getOutboundCrossById(String id)
			throws Exception {
		List<OutboundCrosscheck> check = null;
        try{
            String strSql = " from OutboundCrosscheck as oc where oc.outstockdetailid='" + id + "'";
            check = m_dao.searchEntities(strSql);
            return check;
        }catch(Exception er){
            throw new Exception("���ݳ��ⵥ��ϸID��ø�����Ϣ����(ReviewDaoImpl.getOutboundCrossById):" + er.getMessage());
        }
	}

}
