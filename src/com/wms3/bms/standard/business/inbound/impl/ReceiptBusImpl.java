package com.wms3.bms.standard.business.inbound.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.inbound.IReceiptBus;
import com.wms3.bms.standard.dao.base.impl.BaseSeqDaoImpl;
import com.wms3.bms.standard.dao.inbound.IInboundReceiptDao;
import com.wms3.bms.standard.dao.inbound.IPutawayDao;
import com.wms3.bms.standard.dao.inbound.IReceiptDao;
import com.wms3.bms.standard.dao.inbound.impl.InboundReceiptDaoImpl;
import com.wms3.bms.standard.dao.inbound.impl.PutawayDaoImpl;
import com.wms3.bms.standard.dao.inbound.impl.ReceiptDaoImpl;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BaseSeq;
import com.wms3.bms.standard.entity.inbound.InboundReceiptDetail;
import com.wms3.bms.standard.entity.inbound.InboundReceiptHeader;
import com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction;
/**
 * 
 * ����: �ջ�ҵ����
 * @author hug 2012-8-23
 * @since WMS 3.0
 */
public class ReceiptBusImpl  implements IReceiptBus{
    /** �ջ����ݲ���DAO��  */
    protected IInboundReceiptDao inReceiptDao;
    /** �ջ�����DAO��  */
    protected IReceiptDao receiptDao;
    /** �ϼܲ���DAO�� */
    protected IPutawayDao putawayDao;
    public ReceiptBusImpl (EntityDAO dao){
        inReceiptDao = new InboundReceiptDaoImpl(dao);
        receiptDao = new ReceiptDaoImpl(dao);
        putawayDao = new PutawayDaoImpl(dao);
    }
    
    /**
     * �����ջ���ID����ջ�����ϸ��ѯ��SQL���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IReceiptBus#getInReceiptDetailQuerySQL(java.lang.String)
     */
    public String getInReceiptDetailQuerySQL(String strInReceiptId) {
        String strSQL =  "from InboundReceiptDetail as inde where inde.reinvoiceid='" + strInReceiptId + "' ";//and inde.linestatus in('0','1')
        return strSQL;
    }
    /**
     * �����ջ���ID����ջ�����ϸ��ѯ�ܼ�¼��SQL���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IReceiptBus#getInReceiptDetailCountSQL(java.lang.String)
     */
    public String getInReceiptDetailCountSQL(String strInReceiptId) {
        String strSQL =  "select count(inde.reincoicedetailid) from InboundReceiptDetail as inde where inde.reinvoiceid='" + strInReceiptId + "'  ";//and inde.linestatus in('0','1')
        return strSQL;
    }
    /**
     * �����ջ�����ϸID����ջ�����ϸ���ջ�����Ĳ�ѯSQL���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IReceiptBus#getReceiptTaskQuerySQL(java.lang.String)
     */
    public String getReceiptTaskQuerySQL(String strReceiptDetailId) {
        String strSQL = "from InboundReceiptTransaction as retr where retr.reinvoicedetailid='" + strReceiptDetailId + "' order by retr.transreceiptid";
        return strSQL;
    }
    /**
     * �����ջ�����ϸID����ջ�����ϸ���ջ�����Ĳ�ѯ�ܼ�¼��SQL���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IReceiptBus#getReceiptTaskCountSQL(java.lang.String)
     */
    public String getReceiptTaskCountSQL(String strReceiptDetailId) {
        String strSQL = "select count(retr.transreceiptid) from InboundReceiptTransaction as retr where retr.reinvoicedetailid='" + strReceiptDetailId + "'";
        return strSQL;
    }
    /**
     * �����ջ�����ϸID����ջ�����ϸ���ջ�����Ĳ�ѯ�ܼ�¼��SQL��䣨״̬��Ϊ5��
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IReceiptBus#getReceiptTransCountSQL(java.lang.String)
     */
    public String getReceiptTransCountSQL(String strInvoiceId) {
        String strSQL = "select count(retr.transreceiptid) from InboundReceiptTransaction as retr where retr.reinvoiceid='" + strInvoiceId + "' and retr.transstatus !='5'";
        return strSQL;
    }
    /**
     * �����ջ���ID����ջ���¼�Ĳ�ѯSQL��䣨״̬��Ϊ5��
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IReceiptBus#getReceiptTransQuerySQL(java.lang.String)
     */
    public String getReceiptTransQuerySQL(String strInvoiceId) {
        String strSQL = "from InboundReceiptTransaction as retr where retr.reinvoiceid='" + strInvoiceId + "' and retr.transstatus != '5'  order by retr.transreceiptid";
        return strSQL;
    }
    
    
    /**
     * �����ջ���ID����ջ������б�
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IReceiptBus#getReceiptTransactionToIvoiceId(java.lang.String)
     */
    public List<InboundReceiptTransaction> getReceiptTransactionToIvoiceId(String strInReceiptId) throws Exception {  
        return receiptDao.getReceiptTransactionToIvoiceId(strInReceiptId);
    }
    
    /**
     * ִ���ջ�
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IReceiptBus#excuteReceipt(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, double, double, double, double, double, java.lang.String, java.lang.String, double, java.lang.String, java.lang.String, java.lang.String)
     */
   public String excuteReceipt(String invoiceid, String invoicedetailid, String strLotid, String strLotatt1, String strLotatt2, String strLotatt3, String strLotatt4, String strLotatt5, String strLotatt6, String strLotatt7, String strLotatt8, String strLotatt9, String strLotatt10, String strLotatt11, String strLotatt12, double num, double weight, double netweight, double volume, double rejectednum, String rejectcode, String rejectreason, double holdnum, String holdcode, String holdreason, String userCode) throws Exception {
       String strMsg = "Y";
       //ͬ��  �ջ�����ϸ
       synchronized (invoicedetailid) {
           //��ȡ�ջ�����ϸ
           InboundReceiptDetail receiptDetail = inReceiptDao.getInBoundReceiptDetailByDetailId(invoicedetailid);
           //�ж������Ƿ�����
           if(receiptDetail != null && (receiptDetail.getLinestatus().equals("0") || receiptDetail.getLinestatus().equals("1"))){
               //������ϸ��ʣ�ջ����� (��������-�ջ�����-��������-��������)
               double realNum = receiptDetail.getInvoicenum() - receiptDetail.getRecnum() - receiptDetail.getRejectednum() - receiptDetail.getHoldnum();
               if((num + rejectednum + holdnum) <= realNum) //С�ڻ�ʣ�ջ�����
               {
                   InboundReceiptTransaction trans = null;  //�ջ���¼
                   if(num != 0){ //�ջ�������Ϊ0 
                       //�ջ���
                       InboundReceiptHeader receiptInvoice = inReceiptDao.getInBoundReceiptInvoiceById(invoiceid);
                       
                       //�ջ���¼
                       trans = new InboundReceiptTransaction();
                       //�ջ���¼ID  ���ٺ�
                       BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(inReceiptDao.getEntityDAO());
                       BaseSeq  seqEn = seqDao.getSeqByType("SH");
                       String strID = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), inReceiptDao.getEntityDAO());
                       
                       trans.setTransreceiptid(strID);//ID
                       trans.setTransstatus("1");     //״̬      1:�ջ���ɣ�2��ȡ���ջ�
                       trans.setReinvoiceid(invoiceid);                 //�ջ����ݱ��
                       trans.setReinvoicedetailid(invoicedetailid);     //�ջ�����ϸID
                       trans.setWarehouseid(receiptInvoice.getWarehouseid()); //�ֿ���
                       trans.setReceipttime(StrTools.getCurrDateTime(2));     //�ջ�ʱ��
                       trans.setReceiptmanid(userCode);                       //�ջ���
                       //receiptrf;        //�ջ��豸RF
                       //traycode;         //��������
                       trans.setOwnerid(receiptInvoice.getOwnerid());      //����
                       trans.setProductid(receiptDetail.getProductid());   //��Ʒ
                       trans.setPackid(receiptDetail.getPackid());         //��װ
                       trans.setEunit(receiptDetail.getEunit());           //��λ
                       trans.setRecnum(num);            //�ջ�����
                       trans.setReweight(weight);       //�ջ�����
                       trans.setRenetweight(netweight); //�ջ�����
                       trans.setRevolume(volume);       //�ջ����
                       trans.setPucnum(0);              //�ϼ�����
                       trans.setPuweight(0);            //�ϼ�����
                       trans.setPunetweight(0);         //�ϼܾ���
                       trans.setPuvolume(0);            //�ϼ����
                       trans.setLotid(strLotid);        //��������ID
                       trans.setLotatt1(strLotatt1);    //��������1
                       trans.setLotatt2(strLotatt2);    //��������2
                       trans.setLotatt3(strLotatt3);;   //��������3
                       trans.setLotatt4(strLotatt4);    //��������4
                       trans.setLotatt5(strLotatt5);    //��������5
                       trans.setLotatt6(strLotatt6);    //��������6
                       trans.setLotatt7(strLotatt7);    //��������7
                       trans.setLotatt8(strLotatt8);    //��������8
                       trans.setLotatt9(strLotatt9);    //��������9
                       trans.setLotatt10(strLotatt10);  //��������10
                       trans.setLotatt11(strLotatt11);  //��������11
                       trans.setLotatt12(strLotatt12);  //��������12
                       
                   }
                   // //1:�޸ĵ�����ϸ���ջ�����,��������ϸ���ջ�״̬
                   String strStatus = "";
                   if((num + rejectednum + holdnum) == realNum){//��ȫ�ջ�
                       strStatus = "2";
                   }else{//�����ջ�
                       strStatus = "1";       
                   }
                   //�޸��ջ�����ϸ���ջ�����
                   String strSql = "update InboundReceiptDetail set linestatus='" + strStatus + "'";
                   if(num != 0){ //�ջ�����
                       strSql = strSql + ", recnum=" + (receiptDetail.getRecnum() + num);
                       strSql = strSql + ", reweight=" + (receiptDetail.getReweight() + weight);
                       strSql = strSql + ", renetweight=" + (receiptDetail.getRenetweight() + netweight);
                       strSql = strSql + ", revolume=" + (receiptDetail.getRevolume() + volume);        
                   }
                   //����������Ϊ0ʱ
                   if(rejectednum != 0){
                       strSql = strSql + ", rejectednum=" + (receiptDetail.getRejectednum() + rejectednum);
                       strSql = strSql + ", rejectcode='" + rejectcode + "', rejectreason='" + rejectreason + "'";
                   }
                   //����������Ϊ0ʱ
                   if(holdnum != 0){
                       strSql = strSql + ", holdnum=" + (receiptDetail.getHoldnum() + holdnum);
                       strSql = strSql + ", holdcode='" + holdcode + "', holdreason='" + holdreason + "'";
                   }
                   strSql = strSql + " where reincoicedetailid='" + invoicedetailid + "'";
                   
                   //�����ջ���¼���޸��ջ�����ϸ״̬
                   receiptDao.addReceiptTrans(trans, strSql);
                   
                   //����ջ���״̬ �ջ���״̬ ����״̬ 0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�
                   String strInvoiceStatus = "";
                   if(inReceiptDao.ifReceiptFinish(invoiceid)){//ȫ���ջ����
                       strInvoiceStatus = "2";
                   }else{//�����ջ�
                       strInvoiceStatus = "1";
                   }
                   String strInvoiceSql = "update InboundReceiptHeader set instatus='" + strInvoiceStatus + "' where reinvoiceid='" + invoiceid + "'";
                   //�޸��ջ�����״̬
                   receiptDao.excuteSQL(strInvoiceSql);      
               }else{
                   strMsg = "���ջ�����(" + num + ")��+ ����������(" + rejectednum + ")�� + ����������(" + holdnum + ")�����ܴ��ڡ�ʣ���ջ�����(" + realNum + ")����";
               }       
           }    
       }
       return strMsg;
   }
   /**
    * ִ��RF�ջ�
    * (non-Javadoc)
    * @see com.wms3.bms.standard.business.inbound.IReceiptBus#excuteRFReceipt(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, double, double, double, double, java.lang.String)
    */
   public String excuteRFReceipt(String invoiceid, String invoicedetailid, String strLotid, String strLotatt1, String strLotatt2, String strLotatt3, String strLotatt4, String strLotatt5, String strLotatt6, String strLotatt7, String strLotatt8, String strLotatt9, String strLotatt10, String strLotatt11, String strLotatt12, double num, double weight, double netweight, double volume, String userCode) throws Exception {
       String strMsg = "Y";
       //ͬ��  �ջ�����ϸ
       synchronized (invoicedetailid) {
           //��ȡ�ջ�����ϸ
           InboundReceiptDetail receiptDetail = inReceiptDao.getInBoundReceiptDetailByDetailId(invoicedetailid);
           //�ж������Ƿ�����
           if(receiptDetail != null && (receiptDetail.getLinestatus().equals("0") || receiptDetail.getLinestatus().equals("1"))){
               //������ϸ��ʣ�ջ����� (��������-�ջ�����-��������-��������)
               double realNum = receiptDetail.getInvoicenum() - receiptDetail.getRecnum() - receiptDetail.getRejectednum() - receiptDetail.getHoldnum();
               if(num <= realNum) //С�ڻ�ʣ�ջ�����
               {
                   InboundReceiptTransaction trans = null;  //�ջ���¼
                   if(num != 0){ //�ջ�������Ϊ0 
                       //�ջ���
                       InboundReceiptHeader receiptInvoice = inReceiptDao.getInBoundReceiptInvoiceById(invoiceid);
                       
                       //�ջ���¼
                       trans = new InboundReceiptTransaction();
                       //�ջ���¼ID  ���ٺ�
                       
                       BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(inReceiptDao.getEntityDAO());
                       BaseSeq  seqEn = seqDao.getSeqByType("SH");
                       String strID = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), inReceiptDao.getEntityDAO());
                       
                       trans.setTransreceiptid(strID);//ID
                       trans.setTransstatus("1");     //״̬      1:�ջ���ɣ�2��ȡ���ջ�
                       trans.setReinvoiceid(invoiceid);                 //�ջ����ݱ��
                       trans.setReinvoicedetailid(invoicedetailid);     //�ջ�����ϸID
                       trans.setWarehouseid(receiptInvoice.getWarehouseid()); //�ֿ���
                       trans.setReceipttime(StrTools.getCurrDateTime(2));     //�ջ�ʱ��
                       trans.setReceiptmanid(userCode);                       //�ջ���
                       //receiptrf;        //�ջ��豸RF
                       //traycode;         //��������
                       trans.setOwnerid(receiptInvoice.getOwnerid());      //����
                       trans.setProductid(receiptDetail.getProductid());   //��Ʒ
                       trans.setPackid(receiptDetail.getPackid());         //��װ
                       trans.setEunit(receiptDetail.getEunit());           //��λ
                       trans.setRecnum(num);            //�ջ�����
                       trans.setReweight(weight);       //�ջ�����
                       trans.setRenetweight(netweight); //�ջ�����
                       trans.setRevolume(volume);       //�ջ����
                       trans.setPucnum(0);              //�ϼ�����
                       trans.setPuweight(0);            //�ϼ�����
                       trans.setPunetweight(0);         //�ϼܾ���
                       trans.setPuvolume(0);            //�ϼ����
                       trans.setLotid(strLotid);        //��������ID
                       trans.setLotatt1(strLotatt1);    //��������1
                       trans.setLotatt2(strLotatt2);    //��������2
                       trans.setLotatt3(strLotatt3);;   //��������3
                       trans.setLotatt4(strLotatt4);    //��������4
                       trans.setLotatt5(strLotatt5);    //��������5
                       trans.setLotatt6(strLotatt6);    //��������6
                       trans.setLotatt7(strLotatt7);    //��������7
                       trans.setLotatt8(strLotatt8);    //��������8
                       trans.setLotatt9(strLotatt9);    //��������9
                       trans.setLotatt10(strLotatt10);  //��������10
                       trans.setLotatt11(strLotatt11);  //��������11
                       trans.setLotatt12(strLotatt12);  //��������12
                       
                       
                       
                   }
                   // //1:�޸ĵ�����ϸ���ջ�����,��������ϸ���ջ�״̬
                   String strStatus = "";
                   if(num == realNum){//��ȫ�ջ�
                       strStatus = "2";
                   }else{//�����ջ�
                       strStatus = "1";       
                   }
                   //�޸��ջ�����ϸ���ջ�����
                   String strSql = "update InboundReceiptDetail set linestatus='" + strStatus + "'";
                   if(num != 0){ //�ջ�����
                       strSql = strSql + ", recnum=" + (receiptDetail.getRecnum() + num);
                       strSql = strSql + ", reweight=" + (receiptDetail.getReweight() + weight);
                       strSql = strSql + ", renetweight=" + (receiptDetail.getRenetweight() + netweight);
                       strSql = strSql + ", revolume=" + (receiptDetail.getRevolume() + volume);        
                   }
                   strSql = strSql + " where reincoicedetailid='" + invoicedetailid + "'";
                   
                   //�����ջ���¼���޸��ջ�����ϸ״̬
                   receiptDao.addReceiptTrans(trans, strSql);
                   
                   //����ջ���״̬ �ջ���״̬ ����״̬ 0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�
                   String strInvoiceStatus = "";
                   if(inReceiptDao.ifReceiptFinish(invoiceid)){//ȫ���ջ����
                       strInvoiceStatus = "2";
                   }else{//�����ջ�
                       strInvoiceStatus = "1";
                   }
                   String strInvoiceSql = "update InboundReceiptHeader set instatus='" + strInvoiceStatus + "' where reinvoiceid='" + invoiceid + "'";
                   //�޸��ջ�����״̬
                   receiptDao.excuteSQL(strInvoiceSql);      
               }else{
                   strMsg = "���ջ�����(" + num + ")�� ���ܴ��ڡ�ʣ���ջ�����(" + realNum + ")����";
               }       
           }    
       }
       return strMsg;
   }
   /**
    * ����������
    * (non-Javadoc)
    * @see com.wms3.bms.standard.business.inbound.IReceiptBus#bindTray(java.lang.String, java.lang.String)
    */
   public String bindTray(String jobid, String traycode) throws Exception {
       String strMsg = "�󶨳ɹ���";
       if(jobid != null){
           synchronized (jobid) {
               //������
               receiptDao.bindTray(jobid, traycode); 
           }        
       }else{
           strMsg = "��ҵ��Ϊ�գ��޷��󶨣�";
       }
       return strMsg;
   }
   /**
    * ȡ���ջ�
    * (non-Javadoc)
    * @see com.wms3.bms.standard.business.inbound.IReceiptBus#cancelReceipt(java.lang.String, java.lang.String)
    */
   public String cancelReceipt(String invoiceid, String strTransId) throws Exception 
   {
       String strMsg = "ȡ���ջ�[" + strTransId+  "]�ɹ���";    
       synchronized (strTransId) {
           InboundReceiptTransaction trans = putawayDao.getTransReceiptById(strTransId);
           //״̬  (transstatus)    1:�ջ���ɣ�2:������; 3:�����ϼ�; 4����ȫ�ϼ� 5��ȡ���ջ�
           if(trans != null && trans.getTransstatus().equals("1")){ //�ջ����״̬����ȡ���ջ�
               if(trans.getPucnum() > 0){
                   strMsg = "[" + strTransId + "]���ϼܣ��޷�����ȡ���ջ�������";
               }else{
                   synchronized (trans.getReinvoicedetailid()) {
                       //1:�޸��ջ�����ϸ���ջ�������״̬����Ϊ�����ջ�״̬
                       String strDetailSql = "update InboundReceiptDetail set linestatus='1', recnum=recnum-" + trans.getRecnum() + ",  " +
                                                       " reweight=reweight-" + trans.getReweight() + ",  " +
                                                       " renetweight=renetweight-" + trans.getRenetweight() + ",  " +
                                                       " revolume=revolume-" + trans.getRevolume() + "   where reincoicedetailid='" + trans.getReinvoicedetailid() + "'";
                       //2:�޸��ջ�����״̬ ,��Ϊ�����ջ�״̬1
                       String strInvoiceSql = "update InboundReceiptHeader set instatus='1' where reinvoiceid='" + trans.getReinvoiceid() + "'";
                       //3:�޸��ջ���¼��״̬����Ϊȡ���ջ�״̬5
                       String strTransSql = "update InboundReceiptTransaction set transstatus='5' where transreceiptid='" + strTransId + "'";
                       
                       receiptDao.cancelReceiptSQL(strDetailSql, strInvoiceSql, strTransSql);
                   }
               }   
           }else{
               strMsg = "[" + strTransId + "]���ϼܻ���ȡ���ջ����޷�����ȡ���ջ�������";
           }
       }
       return strMsg;
   }

}
