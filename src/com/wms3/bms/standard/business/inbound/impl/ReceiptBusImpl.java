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
 * 描述: 收货业务类
 * @author hug 2012-8-23
 * @since WMS 3.0
 */
public class ReceiptBusImpl  implements IReceiptBus{
    /** 收货单据操作DAO类  */
    protected IInboundReceiptDao inReceiptDao;
    /** 收货操作DAO类  */
    protected IReceiptDao receiptDao;
    /** 上架操作DAO类 */
    protected IPutawayDao putawayDao;
    public ReceiptBusImpl (EntityDAO dao){
        inReceiptDao = new InboundReceiptDaoImpl(dao);
        receiptDao = new ReceiptDaoImpl(dao);
        putawayDao = new PutawayDaoImpl(dao);
    }
    
    /**
     * 根据收货单ID获得收货单详细查询的SQL语句
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IReceiptBus#getInReceiptDetailQuerySQL(java.lang.String)
     */
    public String getInReceiptDetailQuerySQL(String strInReceiptId) {
        String strSQL =  "from InboundReceiptDetail as inde where inde.reinvoiceid='" + strInReceiptId + "' ";//and inde.linestatus in('0','1')
        return strSQL;
    }
    /**
     * 根据收货单ID获得收货单详细查询总记录的SQL语句
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IReceiptBus#getInReceiptDetailCountSQL(java.lang.String)
     */
    public String getInReceiptDetailCountSQL(String strInReceiptId) {
        String strSQL =  "select count(inde.reincoicedetailid) from InboundReceiptDetail as inde where inde.reinvoiceid='" + strInReceiptId + "'  ";//and inde.linestatus in('0','1')
        return strSQL;
    }
    /**
     * 根据收货单详细ID获得收货单详细的收货任务的查询SQL语句
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IReceiptBus#getReceiptTaskQuerySQL(java.lang.String)
     */
    public String getReceiptTaskQuerySQL(String strReceiptDetailId) {
        String strSQL = "from InboundReceiptTransaction as retr where retr.reinvoicedetailid='" + strReceiptDetailId + "' order by retr.transreceiptid";
        return strSQL;
    }
    /**
     * 根据收货单详细ID获得收货单详细的收货任务的查询总记录的SQL语句
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IReceiptBus#getReceiptTaskCountSQL(java.lang.String)
     */
    public String getReceiptTaskCountSQL(String strReceiptDetailId) {
        String strSQL = "select count(retr.transreceiptid) from InboundReceiptTransaction as retr where retr.reinvoicedetailid='" + strReceiptDetailId + "'";
        return strSQL;
    }
    /**
     * 根据收货单详细ID获得收货单详细的收货任务的查询总记录的SQL语句（状态不为5）
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IReceiptBus#getReceiptTransCountSQL(java.lang.String)
     */
    public String getReceiptTransCountSQL(String strInvoiceId) {
        String strSQL = "select count(retr.transreceiptid) from InboundReceiptTransaction as retr where retr.reinvoiceid='" + strInvoiceId + "' and retr.transstatus !='5'";
        return strSQL;
    }
    /**
     * 根据收货单ID获得收货记录的查询SQL语句（状态不为5）
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IReceiptBus#getReceiptTransQuerySQL(java.lang.String)
     */
    public String getReceiptTransQuerySQL(String strInvoiceId) {
        String strSQL = "from InboundReceiptTransaction as retr where retr.reinvoiceid='" + strInvoiceId + "' and retr.transstatus != '5'  order by retr.transreceiptid";
        return strSQL;
    }
    
    
    /**
     * 根据收货单ID获得收货任务列表
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IReceiptBus#getReceiptTransactionToIvoiceId(java.lang.String)
     */
    public List<InboundReceiptTransaction> getReceiptTransactionToIvoiceId(String strInReceiptId) throws Exception {  
        return receiptDao.getReceiptTransactionToIvoiceId(strInReceiptId);
    }
    
    /**
     * 执行收货
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.inbound.IReceiptBus#excuteReceipt(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, double, double, double, double, double, java.lang.String, java.lang.String, double, java.lang.String, java.lang.String, java.lang.String)
     */
   public String excuteReceipt(String invoiceid, String invoicedetailid, String strLotid, String strLotatt1, String strLotatt2, String strLotatt3, String strLotatt4, String strLotatt5, String strLotatt6, String strLotatt7, String strLotatt8, String strLotatt9, String strLotatt10, String strLotatt11, String strLotatt12, double num, double weight, double netweight, double volume, double rejectednum, String rejectcode, String rejectreason, double holdnum, String holdcode, String holdreason, String userCode) throws Exception {
       String strMsg = "Y";
       //同步  收货单详细
       synchronized (invoicedetailid) {
           //获取收货单详细
           InboundReceiptDetail receiptDetail = inReceiptDao.getInBoundReceiptDetailByDetailId(invoicedetailid);
           //判断数量是否满足
           if(receiptDetail != null && (receiptDetail.getLinestatus().equals("0") || receiptDetail.getLinestatus().equals("1"))){
               //单据详细还剩收货数量 (开单数量-收货数量-拒收数量-冻结数量)
               double realNum = receiptDetail.getInvoicenum() - receiptDetail.getRecnum() - receiptDetail.getRejectednum() - receiptDetail.getHoldnum();
               if((num + rejectednum + holdnum) <= realNum) //小于还剩收货数量
               {
                   InboundReceiptTransaction trans = null;  //收货记录
                   if(num != 0){ //收货数量不为0 
                       //收货单
                       InboundReceiptHeader receiptInvoice = inReceiptDao.getInBoundReceiptInvoiceById(invoiceid);
                       
                       //收货记录
                       trans = new InboundReceiptTransaction();
                       //收货记录ID  跟踪号
                       BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(inReceiptDao.getEntityDAO());
                       BaseSeq  seqEn = seqDao.getSeqByType("SH");
                       String strID = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), inReceiptDao.getEntityDAO());
                       
                       trans.setTransreceiptid(strID);//ID
                       trans.setTransstatus("1");     //状态      1:收货完成；2：取消收货
                       trans.setReinvoiceid(invoiceid);                 //收货单据编号
                       trans.setReinvoicedetailid(invoicedetailid);     //收货单详细ID
                       trans.setWarehouseid(receiptInvoice.getWarehouseid()); //仓库编号
                       trans.setReceipttime(StrTools.getCurrDateTime(2));     //收货时间
                       trans.setReceiptmanid(userCode);                       //收货人
                       //receiptrf;        //收货设备RF
                       //traycode;         //托盘条码
                       trans.setOwnerid(receiptInvoice.getOwnerid());      //货主
                       trans.setProductid(receiptDetail.getProductid());   //产品
                       trans.setPackid(receiptDetail.getPackid());         //包装
                       trans.setEunit(receiptDetail.getEunit());           //单位
                       trans.setRecnum(num);            //收货数量
                       trans.setReweight(weight);       //收货重量
                       trans.setRenetweight(netweight); //收货净重
                       trans.setRevolume(volume);       //收货体积
                       trans.setPucnum(0);              //上架数量
                       trans.setPuweight(0);            //上架重量
                       trans.setPunetweight(0);         //上架净重
                       trans.setPuvolume(0);            //上架体积
                       trans.setLotid(strLotid);        //批次类型ID
                       trans.setLotatt1(strLotatt1);    //批次属性1
                       trans.setLotatt2(strLotatt2);    //批次属性2
                       trans.setLotatt3(strLotatt3);;   //批次属性3
                       trans.setLotatt4(strLotatt4);    //批次属性4
                       trans.setLotatt5(strLotatt5);    //批次属性5
                       trans.setLotatt6(strLotatt6);    //批次属性6
                       trans.setLotatt7(strLotatt7);    //批次属性7
                       trans.setLotatt8(strLotatt8);    //批次属性8
                       trans.setLotatt9(strLotatt9);    //批次属性9
                       trans.setLotatt10(strLotatt10);  //批次属性10
                       trans.setLotatt11(strLotatt11);  //批次属性11
                       trans.setLotatt12(strLotatt12);  //批次属性12
                       
                   }
                   // //1:修改单据详细的收货数量,及单据详细的收货状态
                   String strStatus = "";
                   if((num + rejectednum + holdnum) == realNum){//完全收货
                       strStatus = "2";
                   }else{//部分收货
                       strStatus = "1";       
                   }
                   //修改收货单详细的收货数量
                   String strSql = "update InboundReceiptDetail set linestatus='" + strStatus + "'";
                   if(num != 0){ //收货数量
                       strSql = strSql + ", recnum=" + (receiptDetail.getRecnum() + num);
                       strSql = strSql + ", reweight=" + (receiptDetail.getReweight() + weight);
                       strSql = strSql + ", renetweight=" + (receiptDetail.getRenetweight() + netweight);
                       strSql = strSql + ", revolume=" + (receiptDetail.getRevolume() + volume);        
                   }
                   //拒收数量不为0时
                   if(rejectednum != 0){
                       strSql = strSql + ", rejectednum=" + (receiptDetail.getRejectednum() + rejectednum);
                       strSql = strSql + ", rejectcode='" + rejectcode + "', rejectreason='" + rejectreason + "'";
                   }
                   //冻结数量不为0时
                   if(holdnum != 0){
                       strSql = strSql + ", holdnum=" + (receiptDetail.getHoldnum() + holdnum);
                       strSql = strSql + ", holdcode='" + holdcode + "', holdreason='" + holdreason + "'";
                   }
                   strSql = strSql + " where reincoicedetailid='" + invoicedetailid + "'";
                   
                   //增加收货记录，修改收货单详细状态
                   receiptDao.addReceiptTrans(trans, strSql);
                   
                   //检测收货单状态 收货单状态 单据状态 0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架
                   String strInvoiceStatus = "";
                   if(inReceiptDao.ifReceiptFinish(invoiceid)){//全部收货完成
                       strInvoiceStatus = "2";
                   }else{//部分收货
                       strInvoiceStatus = "1";
                   }
                   String strInvoiceSql = "update InboundReceiptHeader set instatus='" + strInvoiceStatus + "' where reinvoiceid='" + invoiceid + "'";
                   //修改收货单的状态
                   receiptDao.excuteSQL(strInvoiceSql);      
               }else{
                   strMsg = "【收货数量(" + num + ")】+ 【拒收数量(" + rejectednum + ")】 + 【冻结数量(" + holdnum + ")】不能大于【剩余收货数量(" + realNum + ")】！";
               }       
           }    
       }
       return strMsg;
   }
   /**
    * 执行RF收货
    * (non-Javadoc)
    * @see com.wms3.bms.standard.business.inbound.IReceiptBus#excuteRFReceipt(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, double, double, double, double, java.lang.String)
    */
   public String excuteRFReceipt(String invoiceid, String invoicedetailid, String strLotid, String strLotatt1, String strLotatt2, String strLotatt3, String strLotatt4, String strLotatt5, String strLotatt6, String strLotatt7, String strLotatt8, String strLotatt9, String strLotatt10, String strLotatt11, String strLotatt12, double num, double weight, double netweight, double volume, String userCode) throws Exception {
       String strMsg = "Y";
       //同步  收货单详细
       synchronized (invoicedetailid) {
           //获取收货单详细
           InboundReceiptDetail receiptDetail = inReceiptDao.getInBoundReceiptDetailByDetailId(invoicedetailid);
           //判断数量是否满足
           if(receiptDetail != null && (receiptDetail.getLinestatus().equals("0") || receiptDetail.getLinestatus().equals("1"))){
               //单据详细还剩收货数量 (开单数量-收货数量-拒收数量-冻结数量)
               double realNum = receiptDetail.getInvoicenum() - receiptDetail.getRecnum() - receiptDetail.getRejectednum() - receiptDetail.getHoldnum();
               if(num <= realNum) //小于还剩收货数量
               {
                   InboundReceiptTransaction trans = null;  //收货记录
                   if(num != 0){ //收货数量不为0 
                       //收货单
                       InboundReceiptHeader receiptInvoice = inReceiptDao.getInBoundReceiptInvoiceById(invoiceid);
                       
                       //收货记录
                       trans = new InboundReceiptTransaction();
                       //收货记录ID  跟踪号
                       
                       BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(inReceiptDao.getEntityDAO());
                       BaseSeq  seqEn = seqDao.getSeqByType("SH");
                       String strID = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), inReceiptDao.getEntityDAO());
                       
                       trans.setTransreceiptid(strID);//ID
                       trans.setTransstatus("1");     //状态      1:收货完成；2：取消收货
                       trans.setReinvoiceid(invoiceid);                 //收货单据编号
                       trans.setReinvoicedetailid(invoicedetailid);     //收货单详细ID
                       trans.setWarehouseid(receiptInvoice.getWarehouseid()); //仓库编号
                       trans.setReceipttime(StrTools.getCurrDateTime(2));     //收货时间
                       trans.setReceiptmanid(userCode);                       //收货人
                       //receiptrf;        //收货设备RF
                       //traycode;         //托盘条码
                       trans.setOwnerid(receiptInvoice.getOwnerid());      //货主
                       trans.setProductid(receiptDetail.getProductid());   //产品
                       trans.setPackid(receiptDetail.getPackid());         //包装
                       trans.setEunit(receiptDetail.getEunit());           //单位
                       trans.setRecnum(num);            //收货数量
                       trans.setReweight(weight);       //收货重量
                       trans.setRenetweight(netweight); //收货净重
                       trans.setRevolume(volume);       //收货体积
                       trans.setPucnum(0);              //上架数量
                       trans.setPuweight(0);            //上架重量
                       trans.setPunetweight(0);         //上架净重
                       trans.setPuvolume(0);            //上架体积
                       trans.setLotid(strLotid);        //批次类型ID
                       trans.setLotatt1(strLotatt1);    //批次属性1
                       trans.setLotatt2(strLotatt2);    //批次属性2
                       trans.setLotatt3(strLotatt3);;   //批次属性3
                       trans.setLotatt4(strLotatt4);    //批次属性4
                       trans.setLotatt5(strLotatt5);    //批次属性5
                       trans.setLotatt6(strLotatt6);    //批次属性6
                       trans.setLotatt7(strLotatt7);    //批次属性7
                       trans.setLotatt8(strLotatt8);    //批次属性8
                       trans.setLotatt9(strLotatt9);    //批次属性9
                       trans.setLotatt10(strLotatt10);  //批次属性10
                       trans.setLotatt11(strLotatt11);  //批次属性11
                       trans.setLotatt12(strLotatt12);  //批次属性12
                       
                       
                       
                   }
                   // //1:修改单据详细的收货数量,及单据详细的收货状态
                   String strStatus = "";
                   if(num == realNum){//完全收货
                       strStatus = "2";
                   }else{//部分收货
                       strStatus = "1";       
                   }
                   //修改收货单详细的收货数量
                   String strSql = "update InboundReceiptDetail set linestatus='" + strStatus + "'";
                   if(num != 0){ //收货数量
                       strSql = strSql + ", recnum=" + (receiptDetail.getRecnum() + num);
                       strSql = strSql + ", reweight=" + (receiptDetail.getReweight() + weight);
                       strSql = strSql + ", renetweight=" + (receiptDetail.getRenetweight() + netweight);
                       strSql = strSql + ", revolume=" + (receiptDetail.getRevolume() + volume);        
                   }
                   strSql = strSql + " where reincoicedetailid='" + invoicedetailid + "'";
                   
                   //增加收货记录，修改收货单详细状态
                   receiptDao.addReceiptTrans(trans, strSql);
                   
                   //检测收货单状态 收货单状态 单据状态 0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架
                   String strInvoiceStatus = "";
                   if(inReceiptDao.ifReceiptFinish(invoiceid)){//全部收货完成
                       strInvoiceStatus = "2";
                   }else{//部分收货
                       strInvoiceStatus = "1";
                   }
                   String strInvoiceSql = "update InboundReceiptHeader set instatus='" + strInvoiceStatus + "' where reinvoiceid='" + invoiceid + "'";
                   //修改收货单的状态
                   receiptDao.excuteSQL(strInvoiceSql);      
               }else{
                   strMsg = "【收货数量(" + num + ")】 不能大于【剩余收货数量(" + realNum + ")】！";
               }       
           }    
       }
       return strMsg;
   }
   /**
    * 绑定托盘条码
    * (non-Javadoc)
    * @see com.wms3.bms.standard.business.inbound.IReceiptBus#bindTray(java.lang.String, java.lang.String)
    */
   public String bindTray(String jobid, String traycode) throws Exception {
       String strMsg = "绑定成功！";
       if(jobid != null){
           synchronized (jobid) {
               //绑定托盘
               receiptDao.bindTray(jobid, traycode); 
           }        
       }else{
           strMsg = "作业号为空，无法绑定！";
       }
       return strMsg;
   }
   /**
    * 取消收货
    * (non-Javadoc)
    * @see com.wms3.bms.standard.business.inbound.IReceiptBus#cancelReceipt(java.lang.String, java.lang.String)
    */
   public String cancelReceipt(String invoiceid, String strTransId) throws Exception 
   {
       String strMsg = "取消收货[" + strTransId+  "]成功！";    
       synchronized (strTransId) {
           InboundReceiptTransaction trans = putawayDao.getTransReceiptById(strTransId);
           //状态  (transstatus)    1:收货完成；2:已码盘; 3:部分上架; 4：完全上架 5：取消收货
           if(trans != null && trans.getTransstatus().equals("1")){ //收货完成状态才能取消收货
               if(trans.getPucnum() > 0){
                   strMsg = "[" + strTransId + "]已上架，无法进行取消收货操作！";
               }else{
                   synchronized (trans.getReinvoicedetailid()) {
                       //1:修改收货单详细的收货数量，状态，改为部分收货状态
                       String strDetailSql = "update InboundReceiptDetail set linestatus='1', recnum=recnum-" + trans.getRecnum() + ",  " +
                                                       " reweight=reweight-" + trans.getReweight() + ",  " +
                                                       " renetweight=renetweight-" + trans.getRenetweight() + ",  " +
                                                       " revolume=revolume-" + trans.getRevolume() + "   where reincoicedetailid='" + trans.getReinvoicedetailid() + "'";
                       //2:修改收货单的状态 ,改为部分收货状态1
                       String strInvoiceSql = "update InboundReceiptHeader set instatus='1' where reinvoiceid='" + trans.getReinvoiceid() + "'";
                       //3:修改收货记录的状态，改为取消收货状态5
                       String strTransSql = "update InboundReceiptTransaction set transstatus='5' where transreceiptid='" + strTransId + "'";
                       
                       receiptDao.cancelReceiptSQL(strDetailSql, strInvoiceSql, strTransSql);
                   }
               }   
           }else{
               strMsg = "[" + strTransId + "]已上架或已取消收货，无法进行取消收货操作！";
           }
       }
       return strMsg;
   }

}
