package com.wms3.bms.standard.dao.inbound.impl;

import java.util.ArrayList;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.AbstractDao;
import com.wms3.bms.standard.dao.inbound.IInboundReceiptDao;
import com.wms3.bms.standard.entity.inbound.InboundReceiptDetail;
import com.wms3.bms.standard.entity.inbound.InboundReceiptHeader;

/**
 * 
 * ����: �ջ������ݿ����DAO��
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public class InboundReceiptDaoImpl extends AbstractDao implements IInboundReceiptDao{
    
    public InboundReceiptDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    /**
     * �����ջ���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IInboundReceiptDao#addInBoundReceipt(com.wms3.bms.standard.entity.inbound.InboundReceiptHeader)
     */
    public void addInBoundReceipt(InboundReceiptHeader receiptInvoice) throws Exception {
        m_dao.save("InboundReceiptHeader", receiptInvoice);
    }
    /**
     * �޸��ջ���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IInboundReceiptDao#updateInBoundReceipt(com.wms3.bms.standard.entity.inbound.InboundReceiptHeader)
     */
    public void updateInBoundReceipt(InboundReceiptHeader receiptInvoice) throws Exception {
        m_dao.update("InboundReceiptHeader", receiptInvoice);    
    }
    /**
     * �����ջ�����ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IInboundReceiptDao#addInBoundReceiptDetail(com.wms3.bms.standard.entity.inbound.InboundReceiptDetail)
     */
    public void addInBoundReceiptDetail(InboundReceiptDetail receiptDetail) throws Exception {
        m_dao.save("InboundReceiptDetail", receiptDetail); 
    }
    
    /**
     * �޸��ջ�����ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IInboundReceiptDao#updateInBoundReceiptDetail(com.wms3.bms.standard.entity.inbound.InboundReceiptDetail)
     */
    public void updateInBoundReceiptDetail(InboundReceiptDetail receiptDetail) throws Exception {
        m_dao.update("InboundReceiptDetail", receiptDetail);     
    }
    
    /**
     * �����ջ���ID����ջ���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IInboundReceiptDao#getInBoundReceiptInvoiceById(java.lang.String)
     */
    public InboundReceiptHeader getInBoundReceiptInvoiceById(String strReceiptId) throws Exception {
        try{
            if(strReceiptId != null){
                String strSql = "from InboundReceiptHeader as invo where invo.reinvoiceid='" + strReceiptId + "'";
                List lsInBound = m_dao.searchEntities(strSql, 0, 1);
                if(lsInBound != null && lsInBound.size() > 0){
                    return (InboundReceiptHeader)lsInBound.get(0);
                }
            }
        }catch(Exception er){
            throw new Exception("��������ջ���ID["+strReceiptId+"]�������ջ�������" + er.getMessage());
        }
        return null;
    }
    /**
     * �����ջ�����ϸID����ջ�����ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IInboundReceiptDao#getInBoundReceiptDetailByDetailId(java.lang.String)
     */
    public InboundReceiptDetail getInBoundReceiptDetailByDetailId(String strDetailId) throws Exception {
        try{
            if(strDetailId != null){
                String strSql = "from InboundReceiptDetail as invo where invo.reincoicedetailid='" + strDetailId + "'";
                List lsInBound = m_dao.searchEntities(strSql, 0, 1);
                if(lsInBound != null && lsInBound.size() > 0){
                    return (InboundReceiptDetail)lsInBound.get(0);
                }
            }
        }catch(Exception er){
            throw new Exception("��������ջ�����ϸID["+strDetailId+"]�������ջ�����ϸ����" + er.getMessage());
        }
        return null;
    }
    /**
     * �����ջ���ID����ջ�����ϸ�б�
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IInboundReceiptDao#getInBoundReceiptDetailsById(java.lang.String)
     */
    public List<InboundReceiptDetail> getInBoundReceiptDetailsById(String strReceiptId) throws Exception {
        try{
            if(strReceiptId != null){
                String strSql = "from InboundReceiptDetail as inde where inde.reinvoiceid='" + strReceiptId + "'";
                List<InboundReceiptDetail> lsDetail = m_dao.searchEntities(strSql); 
                return lsDetail;
            }
        }catch(Exception er){
            throw new Exception("��������ջ���ID["+strReceiptId+"]�������ջ�����ϸ�б����" + er.getMessage());
        }
        return null;
    }
    /**
     * �����ջ���ID���δ�ϼܵ��ջ�����ϸ�б�
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IInboundReceiptDao#getNoPutawayInBoundReceiptDetailsById(java.lang.String)
     */
    public List<InboundReceiptDetail> getNoPutawayInBoundReceiptDetailsById(String strReceiptId) throws Exception {
        try{
            if(strReceiptId != null){
                String strSql = "from InboundReceiptDetail as inde where inde.reinvoiceid='" + strReceiptId + "' and inde.linestatus !='5'";
                List<InboundReceiptDetail> lsDetail = m_dao.searchEntities(strSql); 
                return lsDetail;
            }
        }catch(Exception er){
            throw new Exception("��������ջ���ID["+strReceiptId+"]���δ�ϼܵ�����ջ�����ϸ�б����" + er.getMessage());
        }
        return null;
    }
    /**
     * ɾ���ջ���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IInboundReceiptDao#deleteInBoundReceipt(java.lang.String)
     */
    public void deleteInBoundReceipt(String strReceiptId) throws Exception {
        try{
            if(strReceiptId != null){
                List<String> lsSQL = new ArrayList<String>();
                String strSql = "delete from InboundReceiptHeader as invo where invo.reinvoiceid='" + strReceiptId + "'";
                lsSQL.add(strSql);
                String strSql1 = "delete from InboundReceiptDetail as inde where inde.reinvoiceid='" + strReceiptId + "'";
                lsSQL.add(strSql1);
                m_dao.deleteSqlList(lsSQL);
            }
        }catch(Exception er){
            throw new Exception("ɾ������ջ�������" + er.getMessage());
        }  
    }
    /**
     * ɾ���ջ�����ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IInboundReceiptDao#deleteInBoundReceiptDetail(java.lang.String)
     */
    public void deleteInBoundReceiptDetail(String strDetailId) throws Exception {
        try{
            if(strDetailId != null){
                String strSql = "delete from InboundReceiptDetail as inde where inde.reincoicedetailid='" + strDetailId + "'";
                m_dao.deleteSql(strSql);
            }
        }catch(Exception er){
            throw new Exception("ɾ������ջ�����ϸ����" + er.getMessage());
        }  
    }
    
    /**
     * �ж��Ƿ�ȫ���ջ����   ȫ���ջ����true,�����ջ�Ϊfalse
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IInboundReceiptDao#ifReceiptFinish(java.lang.String)
     */
    public boolean ifReceiptFinish(String strInvoiceId) throws Exception {
        try{
            if(strInvoiceId != null){
                String strSql = "select indetail.reincoicedetailid from InboundReceiptDetail as indetail where indetail.reinvoiceid='" + strInvoiceId + "' and indetail.linestatus in('0', '1')";
                List lsResult = m_dao.searchEntities(strSql);
                if(lsResult != null && lsResult.size()>0){
                    return false;
                }
            }  
        }catch(Exception er){
            throw new Exception("�ж��Ƿ�ȫ���ջ���ɳ���" + er.getMessage());
        }
        return true;
    }
    /**
     * �ж��Ƿ�ȫ���ϼ����   ȫ���ϼ����true,�����ϼ�Ϊfalse
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IInboundReceiptDao#ifPutawayFinish(java.lang.String)
     */
    public boolean ifPutawayFinish(String strInvoiceId) throws Exception {
        try{
            //������״̬0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�
            if(strInvoiceId != null){
                String strSql = "select indetail.reincoicedetailid from InboundReceiptDetail as indetail where indetail.reinvoiceid='" + strInvoiceId + "' and indetail.linestatus !='5'";
                List lsResult = m_dao.searchEntities(strSql);
                if(lsResult != null && lsResult.size()>0){
                    return false;
                }
            }  
        }catch(Exception er){
            throw new Exception("�ж��Ƿ�ȫ���ϼ���ɳ���" + er.getMessage());
        }
        return true;
    }
    /**
     * �ж��ջ�����ϸ���ϼܼ�¼�Ƿ�ȫ���ϼ����   ȫ���ϼ����true,�����ϼ�Ϊfalse
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IInboundReceiptDao#ifPutawayReceiptDetailFinish(java.lang.String)
     */
    public boolean ifPutawayReceiptDetailFinish(String strDetailId) throws Exception {
        try{
            //�ջ���¼״̬ 1:�ջ���ɣ�2:������; 3:�����ϼ�; 4����ȫ�ϼ� 5��ȡ���ջ�
            if(strDetailId != null){
                String strSql = "select retra.transreceiptid from InboundReceiptTransaction as retra where retra.reinvoicedetailid='" + strDetailId + "' and retra.transstatus not in('4','5')";
                List lsResult = m_dao.searchEntities(strSql);
                if(lsResult != null && lsResult.size()>0){
                    return false;
                }
            }  
        }catch(Exception er){
            throw new Exception("�ж��ջ�����ϸ���ϼܼ�¼�Ƿ�ȫ���ϼ���ɳ���" + er.getMessage());
        }
        return true;
    }
    /**
     * ���δ�ջ���ɵ��ջ���(״̬0-����1-�����ջ�)
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IInboundReceiptDao#getNoReceiptFinish(java.lang.String)
     */
    public List<InboundReceiptHeader> getNoReceiptFinish(String strWarehouseId) throws Exception {
        try{
            if(strWarehouseId != null){
                String strSql = "from InboundReceiptHeader as inre where inre.instatus in('0','1') and inre.warehouseid='" + strWarehouseId + "'";
                List<InboundReceiptHeader> ls = m_dao.searchEntities(strSql); 
                return ls;
            }
        }catch(Exception er){
            throw new Exception("���ݲֿ�["+strWarehouseId+"]���δ�ջ���ɵ��ջ����б����" + er.getMessage());
        }
        return null;
    }
    /**
     * �����Ҫ�ϼܵ��ջ��� (״̬��Ϊ0-���� 5-��ȫ�ϼ�)
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IInboundReceiptDao#getNeedPutawayInvoice(java.lang.String)
     */
    public List<InboundReceiptHeader> getNeedPutawayInvoice(String strWarehouseId) throws Exception {
        try{
            if(strWarehouseId != null){
                String strSql = "from InboundReceiptHeader as inre where inre.instatus not in('0','5') and inre.warehouseid='" + strWarehouseId + "'";
                List<InboundReceiptHeader> ls = m_dao.searchEntities(strSql); 
                return ls;
            }
        }catch(Exception er){
            throw new Exception("���ݲֿ�["+strWarehouseId+"]���δ�ջ���ɵ��ջ����б����" + er.getMessage());
        }
        return null;
    }

}
