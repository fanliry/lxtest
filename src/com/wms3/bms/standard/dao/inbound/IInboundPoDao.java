package com.wms3.bms.standard.dao.inbound;

import java.util.List;

import com.wms3.bms.standard.constant.CommonReturn;
import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundPoDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundPoHeader;
/**
 * 描述:采购订单dao操作接口
 * @author liuxh 2014-1-20
 * @since wmsBMS3.0
 */
public interface IInboundPoDao extends IDao {
	/**
	 * 功能：根据条件获得采购订单sql
	 * @author liuxh 2014-1-20
	 * @param poid
	 * @param potype
	 * @param postatus
	 * @param createtime
	 * @param createdate
	 * @param createmanid
	 * @param warehouseid
	 * @param customerid
	 * @param arrivestarttime
	 * @param arriveendtime
	 * @param shipperid
	 * @param supplierid
	 * @param remark
	 * @param reserve1
	 * @param reserve2
	 * @param reserve3
	 * @param reserve4
	 * @return
	 * @throws Exception
	 */
	public String getInboundPoHeaders(String poid, String potype, String postatus,
			String createtime, String createdate, String createmanid,
			String warehouseid, String customerid,String poflag, String arrivestarttime,
			String arriveendtime, String shipperid, String supplierid,
			String remark, String reserve1, String reserve2, String reserve3,
			String reserve4)throws Exception;
	/**
	 * 根据条件获得PO
	 * @param poid
	 * @param potype
	 * @param postatus
	 * @param createtime
	 * @param createdate
	 * @param createmanid
	 * @param warehouseid
	 * @param customerid
	 * @param poflag
	 * @param arrivestarttime
	 * @param arriveendtime
	 * @param shipperid
	 * @param supplierid
	 * @param remark
	 * @param reserve1
	 * @param reserve2
	 * @param reserve3
	 * @param reserve4
	 * @return
	 * @throws Exception
	 */
	public String getInboundPoHeader(String poid,
			String potype, String postatus, String start_time,
			String end_time, String createmanid, String warehouseid,
			String customerid,String poflag, String arrivestarttime, String arriveendtime,
			String shipperid, String supplierid, String remark,
			String reserve1, String reserve2, String reserve3, String reserve4)
			throws Exception;
	/**
	 * 功能：根据订单id获得订单
	 * @author liuxh 2014-1-20
	 * @param poid
	 * @return
	 * @throws Exception
	 */
	public InboundPoHeader getInboundPoHeaderById(String poid) throws Exception;
	/**
	 * 功能：根据订单详细id获得订单
	 * @author yao 2015-3-10
	 * @param poid
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> getJobInfoByPodetailid(String podetailid) throws Exception;
	/**
	 * 功能：修改采购订单
	 * @author liuxh 2014-1-20
	 * @param inboundPoHeader
	 * @throws Exception
	 */
	public void updateInboundPoHeader(InboundPoHeader inboundPoHeader) throws Exception;
	/**
	 * 功能：根据采购单id获得详细
	 * @author liuxh 2014-1-20
	 * @param poid
	 * @return
	 * @throws Exception
	 */
	public CommonReturn getInboundPoDelsByPoId(String poid) throws Exception;
	/**
	 * 功能：根据采购单详细id获得采购单详细
	 * @author liuxh 2014-1-20
	 * @param podelid
	 * @return
	 * @throws Exception
	 */
	public InboundPoDetail getInboundPoDelById(String podelid) throws Exception;
	/**
	 * 功能：根据采购单详细id及采购单id获得采购单详细
	 * @author yao 2014-2-28
	 * @param poid
	 * @param podelid
	 * @return
	 * @throws Exception
	 */
	public InboundPoDetail getInboundPoDelById(String poid,String podelid) throws Exception;
	
	/**
	 * 根据采购单id获得采购单详细
	 * @param poid
	 * @return
	 * @throws Exception
	 */
	public List<InboundPoDetail> getInboundPoDetailsByPoId(String poid) throws Exception ;
}
