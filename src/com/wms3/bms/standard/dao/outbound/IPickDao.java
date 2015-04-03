package com.wms3.bms.standard.dao.outbound;

import com.wms3.bms.standard.dao.IDao;

/**
 * 描述: 拣货DAO接口
 * @author hug 2012-10-24
 * @since WMS 3.0
 */
public interface IPickDao extends IDao {
    /**
     * 功能:
     * @author hug 2012-10-24 
     * @param strJobDSQL        修改作业详细SQL
     * @param strInvoiceDSQL    修改单据详细SQL
     * @param strStorageSQL     修改库存的SQL
     * @throws Exception
     */
    public void executePick(String strJobDSQL, String strInvoiceDSQL, String strStorageSQL) throws Exception;
    /**
     * 功能: 检测作业是否已完全拣货完成       true:完全拣货 false:没有拣货完成
     * @author hug 2012-10-24 
     * @param strJobId  作业ID
     * @return
     * @throws Exception
     */
    public boolean isPickJobFinish(String strJobId) throws Exception;
    /**
     * 功能: 检测出库单详细是否已完全拣货完成  true:完全拣货 false:没有拣货完成
     * @author hug 2012-10-24 
     * @param strInvoiceDetailId    出库单详细ID
     * @return
     * @throws Exception
     */
    public boolean isPickInvoiceDetailFinish(String strInvoiceDetailId) throws Exception;
    
    /**
     * 功能: 检测出库单是否已完全拒货完成    true:完全拣货 false:没有拣货完成
     * @author hug 2012-10-24 
     * @param strInvoiceId      出库单ID
     * @return
     * @throws Exception
     */
    public boolean isPickInvoiceFinish(String strInvoiceId) throws Exception;

}
