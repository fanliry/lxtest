package com.wms3.bms.standard.business.outbound;

import java.util.List;

import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * 描述: 拣货业务接口
 * @author hug 2012-9-19
 * @since WMS 3.0
 */
public interface IPickBus {
    /**
     * 功能:根据出库单ID获得未拣货作业的查询SQL语句
     * @author hug 2012-9-19    linestatus='0'
     * @param strInvoiceId  出库单ID
     * @return
     */
    public String getPickJobQuerySQL(String strInvoiceId);
    /**
     * 功能:根据出库单ID获得未拣货作业的查询总记录的SQL语句
     * @author hug 2012-9-19   linestatus='0'
     * @param strInvoiceId  出库单ID
     * @return
     */
    public String getPickJobCountSQL(String strInvoiceId);
    /**
     * 功能:根据作业ID获得作业详细列表
     * @author hug 2012-9-19 
     * @param strJobId
     * @return
     * @throws Exception
     */
    public List<InoutJobdetail> getJobDetailsById(String strJobId) throws Exception;
    
    /**
     * 功能: 根据出库单和出库单详细ID查询出库作业
     * @author hug 2012-9-27 
     * @param invoiceid         出库单ID 
     * @param invoicedetailid   出库单详细ID
     * @return
     * @throws Exception
     */
    public List getPickJob(String invoiceid, String invoicedetailid) throws Exception;
    /**
     * 功能: 根据出库单和出库单详细ID查询出库作业详细
     * @author hug 2012-9-27 
     * @param invoiceid         出库单ID
     * @param invoicedetailid   出库单详细ID
     * @return
     * @throws Exception
     */
    public List getPickJobDetail(String invoiceid, String invoicedetailid) throws Exception;
    
    /**
     * 功能: 执行拣货
     * @author hug 2012-10-24 
     * @param invoiceid     出库单ID
     * @param jobdetailid   作业详细ID
     * @param strUserCode
     * @return
     * @throws Exception
     */
    public String executePick(String invoiceid, String jobdetailid, String strUserCode) throws Exception;

}
