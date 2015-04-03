package com.wms3.bms.standard.dao.outbound;

import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.outbound.OutboundCrosscheck;

/**
 * 描述: 出库复核DAO类接口
 * @author hug 2012-10-12
 * @since WMS 3.0
 */
public interface IReviewDao extends IDao {
    /**
     * 功能:判断订单是否复核（1:按单据复核; 2:按分配的作业复核）
     * @author hug 2012-10-12 
     * @param orderId   单据ID
     * @param flag      1:按单据复核; 2:按分配的作业复核 
     * @return
     * @throws Exception
     */
    public boolean isCheckedByHeader(String orderId, String flag) throws Exception;
    /**
     * 功能: 增加单据复核
     * @author hug 2012-10-19 
     * @param check
     * @throws Exception
     */
    public void addReview(OutboundCrosscheck check) throws Exception;
    /**
     * 功能: 修改复核数量
     * @author hug 2012-10-19 
     * @param check
     * @throws Exception
     */
    public void updateReview(OutboundCrosscheck check) throws Exception;
    
    /**
     * 功能:取消单据复核
     * @author hug 2012-10-22 
     * @param invoicedetailid
     * @throws Exception
     */
    public void deleteInvoiceReview(String invoicedetailid) throws Exception;
    /**
     * 功能:取消复核
     * @author hug 2012-10-19 
     * @param strCheckId    出库复核ID
     * @param crosstype     复核类型 1:按单据复核; 2:按分配的作业复核
     * @throws Exception
     */
    public void deleteReview(String strCheckId, String crosstype) throws Exception;
    /**
     * 功能:根据作业ID和作业详细ID获得复核信息
     * @author hug 2012-10-19 
     * @param jobid         作业ID
     * @param jobdetailid   作业详细ID
     * @return
     * @throws Exception
     */
    public OutboundCrosscheck getOutboundCrosscheck(String jobid, String jobdetailid) throws Exception;
    /**
     * 功能：根据出库单详细id获得复核信息
     * @param id
     * @return
     * @throws Exception
     */
    public List<OutboundCrosscheck > getOutboundCrossById(String id)throws Exception;
}
