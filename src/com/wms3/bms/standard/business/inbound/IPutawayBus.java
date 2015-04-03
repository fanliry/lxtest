package com.wms3.bms.standard.business.inbound;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction;
//import com.wms3.bms.standard.entity.job.InoutJobdetail;

/**
 * 描述: 上架业务接口
 * @author hug 2012-8-29
 * @since WMS 3.0
 */
public interface IPutawayBus {
    //  上架生成任务锁(多人同时上架时，只能排队进行，一个上架完了，下一个才能上架)
    public static byte[] put_lock = new byte[0];
    /**
     * 功能:根据收货单ID获得上架作业的查询SQL语句
     * @author hug 2012-9-5 
     * @param strInvoiceId  收货单ID
     * @return
     */
    public String getPutawayJobQuerySQL(String strInvoiceId);
    /**
     * 功能:根据收货单ID获得上架作业的查询总记录的SQL语句
     * @author hug 2012-9-5 
     * @param strInvoiceId  收货单ID
     * @return
     */
    public String getPutawayJobCountSQL(String strInvoiceId);
    /**
     * 功能:根据作业ID获得作业详细列表
     * @author hug 2012-9-5 
     * @param strJobId  作业ID
     * @return
     * @throws Exception
     */
   // public List<InoutJobdetail> getJobDetailsById(String strJobId) throws Exception;
    
    /**
     * 功能：根据收货单ID获得上架作业列表(不包括作废作业)
     * @author hug 2012-6-5 
     * @param strInvoiceId  收货单ID
     * @return
     * @throws Exception
     */
    public List getPutawayJob(String strInvoiceId) throws Exception;
    
    /**
     * 功能:根据收货记录ID获得收货记录对象
     * @author hug 2012-9-4 
     * @param strTransId    收货记录ID
     * @return
     * @throws Exception
     */
    public InboundReceiptTransaction getTransReceiptById(String strTransId) throws Exception;
    /**
     * 功能：根据收货单ID获得收货记录的查询SQL语句
     * @author hug 2012-8-29 
     * @param strInvoiceId  收货单ID
     * @return
     */
    public String getReceiptTransQuerySQL(String strInvoiceId);
    
    /**
     * 功能:根据收货单ID获得收货记录的查询总记录的SQL语句
     * @author hug 2012-8-29 
     * @param strInvoiceId  收货单ID
     * @return
     */
    public String getReceiptTransCountSQL(String strInvoiceId);
    
    /**
     * 功能：根据收货单ID获得需要上架的收货记录的查询SQL语句(状态不为 4：完全上架 5：取消收货)
     * @author hug 2012-8-29 
     * @param strInvoiceId  收货单ID
     * @return
     */
    public String getNeedReceiptTransQuerySQL(String strInvoiceId);
    
    /**
     * 功能:根据收货单ID获得需要上架的收货记录的查询总记录的SQL语句(状态不为 4：完全上架 5：取消收货)
     * @author hug 2012-8-29 
     * @param strInvoiceId  收货单ID
     * @return
     */
    public String getNeedReceiptTransCountSQL(String strInvoiceId);
    
    
    
    /**
     * 功能：根据收货单ID获得收货单详细的查询SQL语句(状态0-开单1-部分收货)
     * @author hug 2012-8-29 
     * @param strInvoiceId  收货单ID
     * @return
     */
    public String getReceiptDetailQuerySQL(String strInvoiceId);
    
    /**
     * 功能:根据收货单ID获得收货单详细的查询总记录的SQL语句(状态0-开单1-部分收货)
     * @author hug 2012-8-29 
     * @param strInvoiceId  收货单ID
     * @return
     */
    public String getReceiptDetailCountSQL(String strInvoiceId);
    
    /**
     * 功能:生成上架任务
     * @author hug 2012-9-7 
     * @param invoiceid     收货单ID
     * @param strLineRows   上架的明细行数
     * @param strUserCode   操作人
     * @param strTrayCode   托盘条码
     * @param warehouseid   仓库ID
     * @param whAreaId      库区ID
     * @param putmode       收货方式
     * @param request
     * @return
     * @throws Exception
     */
    public String createPutawayTask(String invoiceid, String strLineRows, String strUserCode, String strTrayCode, String warehouseid, String whAreaId, String putmode, HttpServletRequest request) throws Exception;
    /** 
     * 功能: 码盘,并生成上架任务
     * @author hug 2012-9-10 
     * @param strTransId    收货记录I
     * @param whAreaId      库区
     * @param strLotatt1    批次属性1
     * @param strLotatt2    批次属性2
     * @param strLotatt3    批次属性3
     * @param strLotatt4    批次属性4
     * @param strLotatt5    批次属性5
     * @param strLotatt6    批次属性6
     * @param strLotatt7    批次属性7
     * @param strLotatt8    批次属性8
     * @param strLotatt9    批次属性9
     * @param strLotatt10   批次属性10
     * @param strLotatt11   批次属性11
     * @param strLotatt12   批次属性12
     * @param strUserCode   操作人
     * @return
     * @throws Exception
     */
    public String excuteCodeTray(String strTransId, String whAreaId, String strLotatt1, String strLotatt2, String strLotatt3, String strLotatt4, String strLotatt5, String strLotatt6, String strLotatt7, String strLotatt8, String strLotatt9, String strLotatt10, String strLotatt11, String strLotatt12, String strUserCode) throws Exception;
    
    /** 
     * 功能:执行上架
     * @author hug 2012-9-4 
     * @param strJobId      作业ID
     * @param strUserCode   操作人
     * @param strNewSpaceId 指定的货位ID       
     * @return
     * @throws Exception
     */
    public String excutePutaway(String strJobId, String strUserCode, String strNewSpaceId) throws Exception;
    /**
     * 功能:取消上架
     * @author hug 2012-9-12 
     * @param strJobId  作业ID
     * @return
     * @throws Exception
     */
    public String cancelPutaway(String strJobId) throws Exception;
    /**
     * 功能: 根据包装和数量可放的托盘数量
     * @author hug 2012-9-10 
     * @param num       数量
     * @param packid    包装
     * @return
     * @throws Exception
     */
    public int getTrayNum(double num, String packid) throws Exception;
    /**
     * 功能: 该包装一托盘可放多少数量
     * @author hug 2012-9-10 
     * @param packid    包装ID
     * @return
     * @throws Exception
     */
    public int getOneTrayNumber(String packid) throws Exception;
}
