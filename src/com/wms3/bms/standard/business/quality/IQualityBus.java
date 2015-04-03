package com.wms3.bms.standard.business.quality;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.constant.CommonReturn;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;
/**
 * 描述：质检管理业务接口
 * @author liuxh
 *
 */
public interface IQualityBus {
	/**
	 * 功能：质检管理放行（分组）
	 *@author liuxh 2013-3-7
	 *@param warehouseid 仓库
	 *@param whareaid  库区
	 *@param lotnumber 批号
	 *@param requestid 申请单号
	 *@param productid 产品id
	 *@param productstatus 产品状态
	 *@param isgroup 是否按批次分组
	 *@return
	 *@throws Exception
	 */
	public String searchInventoryForLotnumber(String warehouseid,String whareaid,String lotnumber,String requestid,String productid,String productstatus,String productCode)throws Exception;
	/**
	 * 功能：查询某个批号下的入库单
	 *@author liuxh 2013-3-7
	 *@param warehouseid  仓库id
	 *@param lotnumber    批号
	 *@param requestid    申请单id
	 *@param productid    产品id
	 *@param productstatus     产品状态
	 *@return
	 *@throws Exception
	 */
	public String searchInventroyForInstock(String warehouseid,String lotnumber,String requestid,String productid,String productstatus,String whareaid)throws Exception;
	/**
	 * 功能：查询入库单详细
	 *@author liuxh 2013-3-7
	 *@param warehouseid
	 *@param instockid
	 *@param lotnumber
	 *@param productid
	 *@return
	 *@throws Exception
	 */
	public List<InboundDetail> searchInStockDel(String instockid,String productid)throws Exception;
    /**
     *  功能：更改状态增加放行记录
     *@author liuxh 2013-3-8
     *@param usercode   用户id
     *@param warehouseid  仓库id
     *@param whareaid     库区id
     *@param lotnumber    批号
     *@param requestid    申请单id
     *@param productid    产品id
     *@param productstatus  产品状态
     *@param strwpzt       新的产品状态
     *@return
     *@throws Exception
     */
	public CommonReturn updateStatusAddRecord(String usercode,String warehouseid,String whareaid,String lotnumber,String requestid,String productid,String productstatus,
			String strwpzt)throws Exception;
	/**
	 * 功能:质检管理->抽检查询库存的SQL
	 * @param warehouseid	仓库ID
	 * @param cargoAreaId	货区ID
	 * @param whAreaId		库区ID
	 * @param platoon		排
	 * @param column		列
	 * @param floor			层
	 * @param customerId    货主
	 * @param indate_from   入库日期from
	 * @param indate_to     入库日期to
	 * @param productid		产品id
	 * @param traycode		托盘条码
	 * @param lotid		          批次ID
	 * @param lotatt1-lotatt12 批次属性
	 * @param strUrl 		地址
	 * @param maxLine 		分页显示行数
	 * @return
	 * @throws Exception
	 */
    public PagingTool getKcSelect(String warehouseId, String whAreaId, String cargoAreaId, String platoon, String column, String floor, 
    		String customerId,String indate_from ,String indate_to,String productid, String traycode, String lotid, String lotatt1, String lotatt2, String lotatt3,String lotatt4, String lotatt5, 
			String lotatt6,String lotatt7, String lotatt8, String lotatt9,String lotatt10, String lotatt11, String lotatt12, String strUrl,int maxLine) throws Exception;
    /**
     * 功能:根据库存ID获取库存信息
     * @param inventId 库存ID
     * @return
     * @throws Exception
     */
    public InventoryStorage getInventById(String inventId)throws Exception;
    /**
     * 功能：增加抽检出库单
     * @param departId 部门id
     * @param formdate 单据日期
     * @param createmanid 操作员
     * @param customerId 客户
     * @param ownerId 货主
     * @param warehouseManId 仓管员 
     */
    public OutboundInvoiceHeader addpOutboundInvoiceHeader(String warehouseId,String departId,String formdate,String createmanid,String customerId,String ownerId,String warehouseManId)throws Exception;
    /**
     * 功能：增加抽检出库单明细出库作业，出库作业明细
     * @param OpManId 操作员
     * @param inventId 库存ID
     * @param outInvoiceId 出库单据ID
     * @param outJob  出库作业ID
     * @param Num    库存数量
     * @param CheckNum  抽检数量
     * @param customerId 客户ID
     * @return
     * @throws Exception
     */
    public Object[] addOutInvoiceDelAddOutJob(String OpManId,String inventId,String outInvoiceId,String outJob,double Num,double CheckNum,String customerId,String i)throws Exception;
    /**
     * 功能：保存 抽检出库单据，抽检出库单明细，抽检出库作业，抽检出库作业明细更新库存
     * @param outInvoiceHeader 抽检出库单
     * @param outJob 出库作业
     * @param lObjects 
     * @return
     * @throws Exception
     */
    public String addBoundAddBoundDelAddJobAddJobDel(OutboundInvoiceHeader outInvoiceHeader,List<Object[]> delObj)throws Exception;
    
    public PagingTool getLsRelease(String Productid, String lotinfo, String Qualityid, String createtime, String strUrl, int maxLine) throws Exception;
    
}

