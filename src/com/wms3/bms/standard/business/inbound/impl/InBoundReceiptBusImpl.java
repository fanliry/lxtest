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
 * ����: �ջ�������ҵ����
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public class InBoundReceiptBusImpl implements IInBoundReceiptBus {
    /** �ջ���DAO��  */
    protected IInboundReceiptDao inReceiptDao;
    /** DAO�� */
    protected EntityDAO daoE;
    
    public InBoundReceiptBusImpl(EntityDAO dao){
        inReceiptDao = new InboundReceiptDaoImpl(dao);
        daoE = dao;
    }
    /**
     * �����ջ���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IInBoundReceiptBus#addInBoundReceipt(com.wms3.bms.standard.entity.inbound.InboundReceiptHeader)
     */
    public String addInBoundReceipt(InboundReceiptHeader receiptInvoice) throws Exception {
        //����ջ�����ID
    	BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(daoE);
        BaseSeq  seqEn = seqDao.getSeqByType("SHD");
        String strID = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), inReceiptDao.getEntityDAO());    

        receiptInvoice.setReinvoiceid(strID);
        //��������ջ���
        inReceiptDao.addInBoundReceipt(receiptInvoice);
        return strID;
    }
    /**
     * �޸��ջ���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IInBoundReceiptBus#updateInBoundReceipt(com.wms3.bms.standard.entity.inbound.InboundReceiptHeader)
     */
    public String updateInBoundReceipt(InboundReceiptHeader receiptInvoice) throws Exception {

        String strMsg = "�����ɹ�!";
        //ͬ��  �ջ�����
        synchronized (receiptInvoice.getReinvoiceid()) {
            if(receiptInvoice.getInstatus().equals("0")){
                inReceiptDao.updateInBoundReceipt(receiptInvoice);
            }else{
                 strMsg = "�ջ���[" + receiptInvoice.getReinvoiceid()  + "]״̬��" + receiptInvoice.getInstatus() + "����Ϊ����״̬0���޷��޸�!";
            }         
        }
        return strMsg;  
    }
    /**
     * �����ջ�����ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IInBoundReceiptBus#addInBoundReceiptDetail(com.wms3.bms.standard.entity.inbound.InboundReceiptDetail, java.lang.String)
     */
    public String addInBoundReceiptDetail(InboundReceiptDetail invoiceDetail, String strInReceiptId) throws Exception {
        String strMsg = "�����ɹ�!";
        // ͬ��  ���ݺ�
        synchronized (strInReceiptId) {
            //�ջ���
            InboundReceiptHeader invoice = getInBoundReceiptInvoiceById(strInReceiptId);
            if(invoice != null){
                if(invoice.getInstatus().equals("0")){     
                    inReceiptDao.addInBoundReceiptDetail(invoiceDetail);
                }else{
                        strMsg = "�ջ���[" + strInReceiptId  + "]״̬��" + invoice.getInstatus() + "����Ϊ����״̬0���޷������ջ�����ϸ!";
                }    
            }  
        }
        return strMsg;
    }
    /**
     * �޸��ջ�����ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IInBoundReceiptBus#updateInBoundReceiptDetail(com.wms3.bms.standard.entity.inbound.InboundReceiptDetail)
     */
    public String updateInBoundReceiptDetail(InboundReceiptDetail receiptDetail) throws Exception {
        String strMsg = "�����ɹ�!";
        // ͬ��  ���ݺ�
        synchronized (receiptDetail.getReinvoiceid()) {
            InboundReceiptHeader invoice = getInBoundReceiptInvoiceById(receiptDetail.getReinvoiceid());
            if(invoice != null){
                //����״̬    0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�
                //������ϸ״̬0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�
                if(invoice.getInstatus().equals("0") && receiptDetail.getLinestatus().equals("0")){
                    
                    inReceiptDao.updateInBoundReceiptDetail(receiptDetail);
                }else{
                        strMsg = "�ջ���[" + receiptDetail.getReinvoiceid()  + "]״̬��" + invoice.getInstatus() + "�����ջ�����ϸ״̬(" + receiptDetail.getLinestatus() + ")��Ϊ����״̬0���޷��޸��ջ�����ϸ!";
                }    
            }  
        }
        return strMsg; 
    }
    /**
     * �����ջ���ID����ջ���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IInBoundReceiptBus#getInBoundReceiptInvoiceById(java.lang.String)
     */
    public InboundReceiptHeader getInBoundReceiptInvoiceById(String strReceiptId) throws Exception {    
        return inReceiptDao.getInBoundReceiptInvoiceById(strReceiptId);
    }
    /**
     * �����ջ�����ϸID����ջ�����ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IInBoundReceiptBus#getInBoundReceiptDetailByDetailId(java.lang.String)
     */
    public InboundReceiptDetail getInBoundReceiptDetailByDetailId(String strDetailId) throws Exception {
        return inReceiptDao.getInBoundReceiptDetailByDetailId(strDetailId);
    }
    /**
     * �����ջ���ID����ջ�����ϸ�б�
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IInBoundReceiptBus#getInBoundReceiptDetailsById(java.lang.String)
     */
    public List<InboundReceiptDetail> getInBoundReceiptDetailsById(String strReceiptId) throws Exception {
        return inReceiptDao.getInBoundReceiptDetailsById(strReceiptId);
    }
    /**
     * ɾ���ջ������ջ�����ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IInBoundReceiptBus#deleteReceiptInvoice(java.lang.String, java.lang.String, java.lang.String)
     */
    public String deleteReceiptInvoice(String strReceiptId, String strReceiptDetailId, String strFlag) throws Exception {

        String strMsg = "�����ɹ�!";
        //ͬ��  ���ݺ�
        synchronized (strReceiptId) { 
            InboundReceiptHeader invoice = getInBoundReceiptInvoiceById(strReceiptId);
            if(invoice != null){
                if(strFlag != null && strFlag.trim().equals("1")){ //1��ɾ������
                    if(invoice.getInstatus().equals("0")){//����״̬Ϊ����״̬ 0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�
                        inReceiptDao.deleteInBoundReceipt(strReceiptId);   
                    }else{
                        strMsg = "����[" + strReceiptId  + "]״̬��" + invoice.getInstatus() + "����Ϊ����״̬0������ɾ���ջ�����";
                    }
                }else if(strFlag != null && strFlag.trim().equals("2")){ //2��ɾ��������ϸ
                    //�ջ�����ϸ��״̬  0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�
                    InboundReceiptDetail receiptDetail = getInBoundReceiptDetailByDetailId(strReceiptDetailId);
                    if(receiptDetail != null){
                        if(receiptDetail.getLinestatus().equals("0")){//Ϊ����״̬ʱ����ɾ��
                            inReceiptDao.deleteInBoundReceiptDetail(strReceiptDetailId);  
                        }else{
                            strMsg = "�ջ���[" + strReceiptId  + "]���ջ���ϸ[" + strReceiptDetailId + "]״̬��" + receiptDetail.getLinestatus() + "����Ϊ����״̬0������ɾ���ջ�����ϸ��";
                        }     
                    }else{
                        strMsg = "�ջ���[" + strReceiptId  + "]���ջ�����ϸ[" + strReceiptDetailId + "]������,�޷���ɾ��������";
                    }
                }
            }else{
                strMsg = "�ջ���[" + strReceiptId  + "]������,�޷���ɾ��������";
            }
        } 
        return strMsg;
    }
    /**
     * ��ѯ�ջ����Ĳ�ѯ���
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
     * ��ѯ�ջ����Ĳ�ѯ�ܼ�¼���
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
