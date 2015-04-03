package com.wms3.bms.standard.business.outbound;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.base.BaseLotSet;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;
import com.wms3.bms.standard.entity.outerInterface.SaleFormDetail;

/**
 * 描述: 出库单管理接口类
 * @author hug 2012-9-13
 * @since WMS 3.0
 */
public interface IOutBoundBus {
	//出库单生成任务锁(不能同时生成出库单据，每次只能一个进程生成出库单据)
    public static byte[] newout_lock = new byte[0]; 

	/**
     * 功能：增加出库单
     * @author hug 2012-5-16 
     * @param invoice
     * @return
     * @throws Exception
     */
    public String addOutBound(OutboundInvoiceHeader invoice) throws Exception;
    /**
     * 功能:增加出库单详细
     * @author hug 2012-9-17 
     * @param outBoundDetail    出库单详细
     * @param strInvoiceId      出库单ID
     * @return
     * @throws Exception
     */
    public String addOutBoundDetail(OutboundInvoiceDetail outBoundDetail, String strInvoiceId) throws Exception;
    /**
     * 功能:修改出库单详细
     * @author hug 2012-9-17 
     * @param outBoundDetail   出库单详细
     * @param strInvoiceId     出库单ID
     * @return
     * @throws Exception
     */
    public String updateOutBoundDetail(OutboundInvoiceDetail outBoundDetail, String strInvoiceId) throws Exception;
    
    /**
     * 功能：修改出库单
     * @author hug 2012-5-16 
     * @param invoice
     * @return
     * @throws Exception
     */
    public String updateOutBound(OutboundInvoiceHeader invoice) throws Exception;
    
    /**
     * 功能: 删除出库单或出库单详细
     * @author hug 2012-9-17 
     * @param strOutBoundId         出库单ID
     * @param strOutBoundDetailId   出库单详细ID
     * @param strFlag               1：删除出库单；2：删除出库单详细
     * @return
     * @throws Exception
     */
    public String deleteOutBoundInvoice(String strOutBoundId, String strOutBoundDetailId, String strFlag) throws Exception;
    
    /**
     * 功能：根据出库单ID获得出库单
     * @author hug 2012-5-16 
     * @param strOutBoundId 出库单ID
     * @return
     * @throws Exception
     */
    public OutboundInvoiceHeader getOutBoundById(String strOutBoundId) throws Exception;
    /**
     * 功能:根据出库单详细ID获得出库单详细
     * @author hug 2012-9-17 
     * @param strInvoiceDetailId    出库单详细ID
     * @return
     * @throws Exception
     */
    public OutboundInvoiceDetail getOutBoundDetailById(String strInvoiceDetailId) throws Exception; 
    /**
     * 功能:获取没有完全分配的出库产品信息
     * @author yao 2015-3-11
     * @return
     * @throws Exception
     */
    public List getOutBoundProudctInfo() throws Exception; 
    
    /**
     * 功能：根据出库单ID获得出库单详细
     * @author hug 2012-5-16 
     * @param strInvoiceId  出库单ID
     * @return
     * @throws Exception
     */
    public List<OutboundInvoiceDetail> getOutBoundDetailsById(String strInvoiceId) throws Exception;
    /**
     * 功能:根据仓库ID获得需要发货确认的出库单
     * @author hug 2012-9-18 
     * @param warehouseid   仓库ID
     * @return
     * @throws Exception
     */
    public List<OutboundInvoiceHeader> getSendOutInvoice(String warehouseid) throws Exception;
    
    /**
     * 功能:根据仓库ID获得需要发货确认的B客户的出库单
     * @author hug 2012-9-18 
     * @param warehouseid   仓库ID
     * @param aoutid        A客户单据ID
     * @return
     * @throws Exception
     */
    public List<OutboundInvoiceHeader> getSendOutInvoice(String warehouseid, String aoutid) throws Exception;
    
    /**
     * 功能：获得出库单查询的SQL语句
     * @author hug 2012-3-19 
     * @param warehouseid   仓库ID
     * @param outstatus     单据状态
     * @param outtype       出库类型
     * @param shiftid       作业班次
     * @param screatedate   单据开始生成时间
     * @param ecreatedate   单据结束生成时间
     * @param customerid    客户
     * @param outno         出库单号
     * @return
     */
    public String getOutBoundQuerySQL(String warehouseid, String outstatus, String outtype, String shiftid, String screatedate, String ecreatedate, String customerid, String outno);
    public String getOutBoundCountSQL(String warehouseid, String outstatus, String outtype, String shiftid, String screatedate, String ecreatedate, String customerid, String outno);
   
    /**
     * 功能: 出库分配->统计预配的数量
     * @author hug 2012-9-21 
     * @param warehouseid   仓库ID
     * @param whAreaId      库区ID
     * @param ownerid   货主ID
     * @param productid 品名ID
     * @param packid    包装ID
     * @param lotatt1   批次属性1
     * @param lotatt2   批次属性2
     * @param lotatt3   批次属性3
     * @param lotatt4   批次属性4
     * @param lotatt5   批次属性5
     * @param lotatt6   批次属性6
     * @param lotatt7   批次属性7
     * @param lotatt8   批次属性8
     * @param lotatt9   批次属性9
     * @param lotatt10  批次属性10
     * @param lotatt11  批次属性11
     * @param lotatt12  批次属性12
     * @return
     * @throws Exception
     */
    public Object[] getGroupSoftallocationNum(String warehouseid, String whAreaId, String ownerid, String productid, String packid, String lotatt1, String lotatt2, String lotatt3, String lotatt4, String lotatt5, String lotatt6, String lotatt7, String lotatt8, String lotatt9, 
            String lotatt10, String lotatt11, String lotatt12) throws Exception;
    
    /**
	 * 功能:发货查询
	 * @param warehouseid	仓库
	 * @param whAreaId		库区
	 * @param senddate_from	发货日期
	 * @param senddate_to	发货日期
	 * @param shiftid		班次
	 * @param productid		品名
	 * @param customerid	客户
	 * @param tray_code		托盘条码
	 * @param lotid			批次属性id
     * @param lotatt1   	批次属性1
     * @param lotatt2   	批次属性2
     * @param lotatt3   	批次属性3
     * @param lotatt4   	批次属性4
     * @param lotatt5   	批次属性5
     * @param lotatt6   	批次属性6
     * @param lotatt7   	批次属性7
     * @param lotatt8   	批次属性8
     * @param lotatt9   	批次属性9
     * @param lotatt10  	批次属性10
     * @param lotatt11  	批次属性11
     * @param lotatt12  	批次属性12
     * @param lsLot 
	 * @return 
	 * @throws Exception
	 */
	public String[] getOutboundSend(String warehouseid, String whAreaId, String senddate_from, String senddate_to, String shiftid, String productid, 
			String customerid, String traycode, String lotinfo) throws Exception;
	
	/**
     * 功能：作废出库单
     * @param ids 出库单ID
     * @return
     * @throws Exception
     */
	public String cancelInvoices(String ids, String strUserCode) throws Exception;
	/**
	 * 功能：获得出库单明细
	 * @param jobDelList  作业明细集合
	 * @return
	 * @throws Exception
	 */
	public List<OutboundInvoiceDetail> getOutBoundDelByJobDelList(List jobDelList)throws Exception;
    /**
     * 功能：根据出库单ID客户ID判断是否有已完成作业（删除出库单据使用）
     * @param id 出库单id
     * @return
     * @throws Exception
     */
	public boolean isFinishJobByOutIdAndCId(String id)throws Exception;
	
	/**
	 * 功能：暂存直接出库时，修改的内容
	 * @param job
	 * @param jobDetail
	 * @param inventory
	 * @throws Exception
	 */
	public String updateInventoryAndJob(String jobID, String jobDetailID,String inventoryID) throws Exception;
	
    /**
     * 功能：增加出库单
     * @author fanll 2015-3-8
     * @param invoice
     * @param outboundInvoiceDetails
     * @return
     * @throws Exception
     */
    public String addOutBoundls(OutboundInvoiceHeader outInvoice,List<OutboundInvoiceDetail> outboundInvoiceDetails,List<SaleFormDetail> SaleFormDetaills) throws Exception;
    
    /**
     * 功能：取消出库单
     * @author fanll 2015-3-8
     * @param outInvoice
     * @param SaleFormDetaills
     * @return
     * @throws Exception
     */
    public String CancelInvoice(OutboundInvoiceHeader outInvoice,List<SaleFormDetail> SaleFormDetaills) throws Exception;
    
}
