package com.wms3.bms.standard.dao.outbound;

import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;
import com.wms3.bms.standard.entity.outerInterface.SaleFormDetail;

/**
 * 描述: 出库管理DAO类接口
 * @author hug 2012-9-13
 * @since WMS 3.0
 */
public interface IOutboundDao extends IDao{   
    /**
     * 功能：增加出库单
     * @author hug 2012-5-16 
     * @param invoice
     * @throws Exception
     */
    public void addOutBound(OutboundInvoiceHeader invoice) throws Exception;
    /**
     * 功能：更新出库单
     * @author hug 2012-5-16 
     * @param invoice
     * @throws Exception
     */
    public void updateOutBound(OutboundInvoiceHeader invoice) throws Exception;
    /**
     * 功能: 增加出库单详细
     * @author hug 2012-9-17 
     * @param outBoundDetail    出库单详细
     * @throws Exception
     */
    public void addOutBoundDetail(OutboundInvoiceDetail outBoundDetail) throws Exception;
    
    /**
     * 功能: 修改出库单详细
     * @author hug 2012-9-17 
     * @param outBoundDetail    出库单详细
     * @throws Exception
     */
    public void updateOutBoundDetail(OutboundInvoiceDetail outBoundDetail) throws Exception;
    
    /**
     * 功能：删除出库单
     * @author hug 2012-8-17 
     * @param strOutBoundId      出库单ID
     * @throws Exception
     */
    public void deleteOutBound(String strOutBoundId) throws Exception;
    
    /**
     * 功能：删除出库单详细
     * @author hug 2012-8-17 
     * @param strDetailId       出库单详细ID
     * @throws Exception
     */
    public void deleteOutBoundDetail(String strDetailId) throws Exception;
    
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
     * 功能：根据出库单据id获得出库已经完成作业
     * @return List<InoutJobdetail>
     * @throws Exception
     */
    public List<InoutJobdetail> getFinishJobByOutIdCid(String outId)throws Exception;
    /**
     * 功能：根据出库单据id获得出库已经完成作业
     * @return List<InoutJobdetail>
     * @throws Exception
     */
    public List<InoutJob> getFinishJobByIdCid(String outId)throws Exception;
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
    public String addOutBound(OutboundInvoiceHeader outInvoice,List<OutboundInvoiceDetail> outboundInvoiceDetails,List<SaleFormDetail> SaleFormDetaills) throws Exception;
    
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
