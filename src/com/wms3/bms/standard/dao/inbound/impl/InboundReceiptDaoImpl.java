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
 * 描述: 收货单数据库操作DAO类
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public class InboundReceiptDaoImpl extends AbstractDao implements IInboundReceiptDao{
    
    public InboundReceiptDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    /**
     * 增加收货单
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IInboundReceiptDao#addInBoundReceipt(com.wms3.bms.standard.entity.inbound.InboundReceiptHeader)
     */
    public void addInBoundReceipt(InboundReceiptHeader receiptInvoice) throws Exception {
        m_dao.save("InboundReceiptHeader", receiptInvoice);
    }
    /**
     * 修改收货单
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IInboundReceiptDao#updateInBoundReceipt(com.wms3.bms.standard.entity.inbound.InboundReceiptHeader)
     */
    public void updateInBoundReceipt(InboundReceiptHeader receiptInvoice) throws Exception {
        m_dao.update("InboundReceiptHeader", receiptInvoice);    
    }
    /**
     * 增加收货单详细
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IInboundReceiptDao#addInBoundReceiptDetail(com.wms3.bms.standard.entity.inbound.InboundReceiptDetail)
     */
    public void addInBoundReceiptDetail(InboundReceiptDetail receiptDetail) throws Exception {
        m_dao.save("InboundReceiptDetail", receiptDetail); 
    }
    
    /**
     * 修改收货单详细
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IInboundReceiptDao#updateInBoundReceiptDetail(com.wms3.bms.standard.entity.inbound.InboundReceiptDetail)
     */
    public void updateInBoundReceiptDetail(InboundReceiptDetail receiptDetail) throws Exception {
        m_dao.update("InboundReceiptDetail", receiptDetail);     
    }
    
    /**
     * 根据收货单ID获得收货单
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
            throw new Exception("根据入库收货单ID["+strReceiptId+"]获得入库收货单出错：" + er.getMessage());
        }
        return null;
    }
    /**
     * 根据收货单详细ID获得收货单详细
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
            throw new Exception("根据入库收货单详细ID["+strDetailId+"]获得入库收货单详细出错：" + er.getMessage());
        }
        return null;
    }
    /**
     * 根据收货单ID获得收货单详细列表
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
            throw new Exception("根据入库收货单ID["+strReceiptId+"]获得入库收货单详细列表出错：" + er.getMessage());
        }
        return null;
    }
    /**
     * 根据收货单ID获得未上架的收货单详细列表
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
            throw new Exception("根据入库收货单ID["+strReceiptId+"]获得未上架的入库收货单详细列表出错：" + er.getMessage());
        }
        return null;
    }
    /**
     * 删除收货单
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
            throw new Exception("删除入库收货单出错：" + er.getMessage());
        }  
    }
    /**
     * 删除收货单详细
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
            throw new Exception("删除入库收货单详细出错：" + er.getMessage());
        }  
    }
    
    /**
     * 判断是否全部收货完成   全部收货完成true,部分收货为false
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
            throw new Exception("判断是否全部收货完成出错：" + er.getMessage());
        }
        return true;
    }
    /**
     * 判断是否全部上架完成   全部上架完成true,部分上架为false
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IInboundReceiptDao#ifPutawayFinish(java.lang.String)
     */
    public boolean ifPutawayFinish(String strInvoiceId) throws Exception {
        try{
            //单据行状态0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架
            if(strInvoiceId != null){
                String strSql = "select indetail.reincoicedetailid from InboundReceiptDetail as indetail where indetail.reinvoiceid='" + strInvoiceId + "' and indetail.linestatus !='5'";
                List lsResult = m_dao.searchEntities(strSql);
                if(lsResult != null && lsResult.size()>0){
                    return false;
                }
            }  
        }catch(Exception er){
            throw new Exception("判断是否全部上架完成出错：" + er.getMessage());
        }
        return true;
    }
    /**
     * 判断收货单详细的上架记录是否全部上架完成   全部上架完成true,部分上架为false
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.inbound.IInboundReceiptDao#ifPutawayReceiptDetailFinish(java.lang.String)
     */
    public boolean ifPutawayReceiptDetailFinish(String strDetailId) throws Exception {
        try{
            //收货记录状态 1:收货完成；2:已码盘; 3:部分上架; 4：完全上架 5：取消收货
            if(strDetailId != null){
                String strSql = "select retra.transreceiptid from InboundReceiptTransaction as retra where retra.reinvoicedetailid='" + strDetailId + "' and retra.transstatus not in('4','5')";
                List lsResult = m_dao.searchEntities(strSql);
                if(lsResult != null && lsResult.size()>0){
                    return false;
                }
            }  
        }catch(Exception er){
            throw new Exception("判断收货单详细的上架记录是否全部上架完成出错：" + er.getMessage());
        }
        return true;
    }
    /**
     * 获得未收货完成的收货单(状态0-开单1-部分收货)
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
            throw new Exception("根据仓库["+strWarehouseId+"]获得未收货完成的收货单列表出错：" + er.getMessage());
        }
        return null;
    }
    /**
     * 获得需要上架的收货单 (状态不为0-开单 5-完全上架)
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
            throw new Exception("根据仓库["+strWarehouseId+"]获得未收货完成的收货单列表出错：" + er.getMessage());
        }
        return null;
    }

}
