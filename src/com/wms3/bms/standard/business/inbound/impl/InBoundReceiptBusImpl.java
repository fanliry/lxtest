package com.wms3.bms.standard.business.inbound.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.inbound.IInBoundReceiptBus;
import com.wms3.bms.standard.dao.base.impl.BaseSeqDaoImpl;
import com.wms3.bms.standard.dao.inbound.IInboundReceiptDao;
import com.wms3.bms.standard.dao.inbound.impl.InboundReceiptDaoImpl;
import com.wms3.bms.standard.entity.base.BaseSeq;
import com.wms3.bms.standard.entity.inbound.InboundReceiptDetail;
import com.wms3.bms.standard.entity.inbound.InboundReceiptHeader;

/**
 * 
 * 描述: 收货单管理业务类
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public class InBoundReceiptBusImpl implements IInBoundReceiptBus {
    /** 收货单DAO类  */
    protected IInboundReceiptDao inReceiptDao;
    /** DAO类 */
    protected EntityDAO daoE;
    
    public InBoundReceiptBusImpl(EntityDAO dao){
        inReceiptDao = new InboundReceiptDaoImpl(dao);
        daoE = dao;
    }
    /**
     * 增加收货单
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IInBoundReceiptBus#addInBoundReceipt(com.wms3.bms.standard.entity.inbound.InboundReceiptHeader)
     */
    public String addInBoundReceipt(InboundReceiptHeader receiptInvoice) throws Exception {
        //获得收货单据ID
    	BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(daoE);
        BaseSeq  seqEn = seqDao.getSeqByType("SHD");
        String strID = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), inReceiptDao.getEntityDAO());    

        receiptInvoice.setReinvoiceid(strID);
        //增加入库收货单
        inReceiptDao.addInBoundReceipt(receiptInvoice);
        return strID;
    }
    /**
     * 修改收货单
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IInBoundReceiptBus#updateInBoundReceipt(com.wms3.bms.standard.entity.inbound.InboundReceiptHeader)
     */
    public String updateInBoundReceipt(InboundReceiptHeader receiptInvoice) throws Exception {

        String strMsg = "操作成功!";
        //同步  收货单号
        synchronized (receiptInvoice.getReinvoiceid()) {
            if(receiptInvoice.getInstatus().equals("0")){
                inReceiptDao.updateInBoundReceipt(receiptInvoice);
            }else{
                 strMsg = "收货单[" + receiptInvoice.getReinvoiceid()  + "]状态（" + receiptInvoice.getInstatus() + "）不为开单状态0！无法修改!";
            }         
        }
        return strMsg;  
    }
    /**
     * 增加收货单详细
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IInBoundReceiptBus#addInBoundReceiptDetail(com.wms3.bms.standard.entity.inbound.InboundReceiptDetail, java.lang.String)
     */
    public String addInBoundReceiptDetail(InboundReceiptDetail invoiceDetail, String strInReceiptId) throws Exception {
        String strMsg = "操作成功!";
        // 同步  单据号
        synchronized (strInReceiptId) {
            //收货单
            InboundReceiptHeader invoice = getInBoundReceiptInvoiceById(strInReceiptId);
            if(invoice != null){
                if(invoice.getInstatus().equals("0")){     
                    inReceiptDao.addInBoundReceiptDetail(invoiceDetail);
                }else{
                        strMsg = "收货单[" + strInReceiptId  + "]状态（" + invoice.getInstatus() + "）不为开单状态0！无法新增收货单详细!";
                }    
            }  
        }
        return strMsg;
    }
    /**
     * 修改收货单详细
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IInBoundReceiptBus#updateInBoundReceiptDetail(com.wms3.bms.standard.entity.inbound.InboundReceiptDetail)
     */
    public String updateInBoundReceiptDetail(InboundReceiptDetail receiptDetail) throws Exception {
        String strMsg = "操作成功!";
        // 同步  单据号
        synchronized (receiptDetail.getReinvoiceid()) {
            InboundReceiptHeader invoice = getInBoundReceiptInvoiceById(receiptDetail.getReinvoiceid());
            if(invoice != null){
                //单据状态    0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架
                //单据详细状态0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架
                if(invoice.getInstatus().equals("0") && receiptDetail.getLinestatus().equals("0")){
                    
                    inReceiptDao.updateInBoundReceiptDetail(receiptDetail);
                }else{
                        strMsg = "收货单[" + receiptDetail.getReinvoiceid()  + "]状态（" + invoice.getInstatus() + "）或收货单详细状态(" + receiptDetail.getLinestatus() + ")不为开单状态0！无法修改收货单详细!";
                }    
            }  
        }
        return strMsg; 
    }
    /**
     * 根据收货单ID获得收货单
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IInBoundReceiptBus#getInBoundReceiptInvoiceById(java.lang.String)
     */
    public InboundReceiptHeader getInBoundReceiptInvoiceById(String strReceiptId) throws Exception {    
        return inReceiptDao.getInBoundReceiptInvoiceById(strReceiptId);
    }
    /**
     * 根据收货单详细ID获得收货单详细
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IInBoundReceiptBus#getInBoundReceiptDetailByDetailId(java.lang.String)
     */
    public InboundReceiptDetail getInBoundReceiptDetailByDetailId(String strDetailId) throws Exception {
        return inReceiptDao.getInBoundReceiptDetailByDetailId(strDetailId);
    }
    /**
     * 根据收货单ID获得收货单详细列表
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IInBoundReceiptBus#getInBoundReceiptDetailsById(java.lang.String)
     */
    public List<InboundReceiptDetail> getInBoundReceiptDetailsById(String strReceiptId) throws Exception {
        return inReceiptDao.getInBoundReceiptDetailsById(strReceiptId);
    }
    /**
     * 删除收货单或收货单详细
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IInBoundReceiptBus#deleteReceiptInvoice(java.lang.String, java.lang.String, java.lang.String)
     */
    public String deleteReceiptInvoice(String strReceiptId, String strReceiptDetailId, String strFlag) throws Exception {

        String strMsg = "操作成功!";
        //同步  单据号
        synchronized (strReceiptId) { 
            InboundReceiptHeader invoice = getInBoundReceiptInvoiceById(strReceiptId);
            if(invoice != null){
                if(strFlag != null && strFlag.trim().equals("1")){ //1：删除单据
                    if(invoice.getInstatus().equals("0")){//单据状态为开单状态 0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架
                        inReceiptDao.deleteInBoundReceipt(strReceiptId);   
                    }else{
                        strMsg = "单据[" + strReceiptId  + "]状态（" + invoice.getInstatus() + "）不为开单状态0，不能删除收货单！";
                    }
                }else if(strFlag != null && strFlag.trim().equals("2")){ //2：删除单据详细
                    //收货单详细的状态  0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架
                    InboundReceiptDetail receiptDetail = getInBoundReceiptDetailByDetailId(strReceiptDetailId);
                    if(receiptDetail != null){
                        if(receiptDetail.getLinestatus().equals("0")){//为开单状态时才能删除
                            inReceiptDao.deleteInBoundReceiptDetail(strReceiptDetailId);  
                        }else{
                            strMsg = "收货单[" + strReceiptId  + "]的收货详细[" + strReceiptDetailId + "]状态（" + receiptDetail.getLinestatus() + "）不为开单状态0，不能删除收货单详细！";
                        }     
                    }else{
                        strMsg = "收货单[" + strReceiptId  + "]的收货单详细[" + strReceiptDetailId + "]不存在,无法做删除操作！";
                    }
                }
            }else{
                strMsg = "收货单[" + strReceiptId  + "]不存在,无法做删除操作！";
            }
        } 
        return strMsg;
    }
    /**
     * 查询收货单的查询语句
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IInBoundReceiptBus#getInboundReceiptQuerySQL(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public String getInboundReceiptQuerySQL(String warehouseid, String ownerid, String instatus, String intype, String screatedate, String ecreatedate) {
        StringBuilder strBud = new StringBuilder();
        strBud.append("from InboundReceiptHeader as inv where 1=1 ");
        if(warehouseid != null && warehouseid.trim().length() >0){     
            strBud.append(" and inv.warehouseid='").append(warehouseid).append("'");
        }
        if(ownerid != null && ownerid.trim().length() >0){
            strBud.append(" and inv.ownerid='").append(ownerid).append("'");
        }
        if(instatus != null && instatus.trim().length() >0){
            strBud.append(" and inv.instatus='").append(instatus).append("'");
        }
        if(intype != null && intype.trim().length() >0){
            strBud.append(" and inv.intype='").append(intype).append("'");
        }
        if(screatedate != null && screatedate.trim().length() >0){
            strBud.append(" and inv.invoicedate >='").append(screatedate).append("'");
        }
        if(ecreatedate != null && ecreatedate.trim().length() >0){
            strBud.append(" and inv.invoicedate <='").append(ecreatedate).append("'");
        }
        return strBud.toString();
    }
    /**
     * 查询收货单的查询总记录语句
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IInBoundReceiptBus#getInboundReceiptCountSQL(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public String getInboundReceiptCountSQL(String warehouseid, String ownerid, String instatus, String intype, String screatedate, String ecreatedate) {
        StringBuilder strBud = new StringBuilder();
        strBud.append("select  count(inv.reinvoiceid) from InboundReceiptHeader as inv where 1=1 ");
        if(warehouseid != null && warehouseid.trim().length() >0){     
            strBud.append(" and inv.warehouseid='").append(warehouseid).append("'");
        }
        if(ownerid != null && ownerid.trim().length() > 0){
            strBud.append(" and inv.ownerid='").append(ownerid).append("'");
        }
        if(instatus != null && instatus.trim().length() > 0){
            strBud.append(" and inv.instatus='").append(instatus).append("'");
        }
        if(intype != null && intype.trim().length() > 0){
            strBud.append(" and inv.intype='").append(intype).append("'");
        }
        if(screatedate != null && screatedate.trim().length() > 0){
            strBud.append(" and inv.invoicedate >='").append(screatedate).append("'");
        }
        if(ecreatedate != null && ecreatedate.trim().length() > 0){
            strBud.append(" and inv.invoicedate <='").append(ecreatedate).append("'");
        }
        return strBud.toString();
    }
    

}
