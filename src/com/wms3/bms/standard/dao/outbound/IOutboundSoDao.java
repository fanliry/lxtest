package com.wms3.bms.standard.dao.outbound;
import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.outerInterface.SaleForm;
import com.wms3.bms.standard.entity.outerInterface.SaleFormDetail;
/**
 * 描述:出库订单dao操作接口
 * @author yao 2014-3-25
 * @since wmsBMS3.0
 */
public interface IOutboundSoDao extends IDao {

    public String getOutboundSoHeaders(String m_strSaleFormId,String m_strSaleFormType,String m_strCustomerId,String m_strDownTime, String m_strIsOut,String m_strIsFinish,
    		String m_strIsInvalid)
			throws Exception;

    public String getOutboundSoHeadersTime(String m_strSaleFormId,String m_strSaleFormType,String m_strCustomerId,String m_strDownTime, String m_strIsOut,String m_strIsFinish,
    		String m_strIsInvalid) throws Exception;
	
	public String getOutboundSoHeadersByGD(String soid,String sotype,String sostatus,String createtime, String createdate,String createmanid,
    		String warehouseid,String customerid,String releasestatus,String consigneeid, String erpno,String soflag) throws Exception;
	/**
	 * 功能：根据订单id获得订单
	 * @author  yao 2014-3-25
	 * @param soid
	 * @return
	 * @throws Exception
	 */
	public SaleForm getOutboundSoHeaderById(String soid) throws Exception;
	/**
	 * 功能：修改订单
	 * @author  yao 2014-3-25
	 * @param outboundSoHeader
	 * @throws Exception
	 */
	public void updateOutboundSoHeader(SaleForm outboundSoHeader) throws Exception;
	/**
	 * 功能：根据订单详细id获得订单详细
	 * @author  yao 2014-3-25
	 * @param sodelid
	 * @return
	 * @throws Exception
	 */
	public SaleFormDetail getOutboundSoDelById(String sodelid) throws Exception;
	/**
	 * 功能：根据订单详细id及订单id获得订单详细
	 * @author  yao 2014-3-25
	 * @param soid
	 * @param sodelid
	 * @return
	 * @throws Exception
	 */
	public SaleFormDetail getOutboundSoDelById(String soid,String sodelid) throws Exception;
	/**
	 * 功能：根据订单详细id获得订单详细
	 * @author xz
	 * @param soid
	 * @return
	 * @throws Exception
	 */
	public List canBGByHeadId(String soid) throws Exception;
	
	public SaleFormDetail getSoDeByDId(String sodid) throws Exception;
}
