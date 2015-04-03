package com.wms3.bms.standard.business.outbound;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.outerInterface.SaleForm;
import com.wms3.bms.standard.entity.outerInterface.SaleFormDetail;

/**
 * ����:���˶�����ҵ�������
 * @author yao 2014-3-25
 * @since wmsBMS3.0
 */
public interface IOutboundSoBus {

	public PagingTool getOutboundSoHeaders(String m_strSaleFormId,String m_strSaleFormType,String m_strCustomerId,String m_strDownTime, String m_strIsOut,String m_strIsFinish,
    		String m_strIsInvalid,String strUrl,int maxrow) throws Exception;
	
	public PagingTool getOutboundSoHeadersByGD(String warehouseid,String customerid, String createdate,String sotype,String sostatus,String soflag,String erpno,String strUrl,int maxrow) throws Exception;
	/**
	 * ���ܣ����ݶ���id
	 * @author yao 2014-2-28
	 * @param soid
	 * @return
	 * @throws Exception
	 */
	public SaleForm getOutboundSoHeaders(String soid) throws Exception;
	/**
	 * ���ܣ����ݶ���id
	 * @param soid
	 * @return
	 * @throws Exception
	 */
	public List<SaleForm> getOutboundSoHeaderById(String soid) throws Exception;
	/**
	 * ���ܣ����ݶ���id
	 * @param soid
	 * @return
	 * @throws Exception
	 */
	public List<SaleForm> getOutboundSoHeaderById2(String soid) throws Exception;
	/**
	 * ���ܣ����ݶ���id
	 * @author yao 2014-3-24
	 * @param soid
	 * @param detailid
	 * @return
	 * @throws Exception
	 */
	public SaleFormDetail getOutboundSoHeaders(String soid,String detailid) throws Exception;
	/**
	 * 
	 * ���ܣ����ݶ�����ϸid��ö�����ϸ
	 * @author yao 2014-3-24
	 * @param sodelid
	 * @return
	 * @throws Exception
	 */
	public SaleFormDetail getSoDetailByDelId(String sodelid)throws Exception;
	/**
	 * ���ܣ����ݶ���id ��ȡ��Ӧ�Ķ�����ϸ
	 * @param soid
	 * @return
	 * @throws Exception
	 */
	public List getOutboundSoDetails(String soid) throws Exception;
	public PagingTool getOutboundSoDetailsPage(String soid, String strUrl, int maxrow) throws Exception;
	//public List<ProcessTrack> getProcessTrackBySoId(String soid) throws Exception;

	//public List<ProcessReport> getProcessBySoId(String soid) throws Exception;
	public SaleFormDetail getSoDeByDId(String sodid) throws Exception;
	
	
}
