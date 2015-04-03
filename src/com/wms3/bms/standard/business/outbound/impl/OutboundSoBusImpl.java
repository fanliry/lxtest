package com.wms3.bms.standard.business.outbound.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.business.outbound.IOutboundSoBus;
import com.wms3.bms.standard.dao.outbound.IOutboundSoDao;
import com.wms3.bms.standard.dao.outbound.impl.OutboundSoDaoImpl;
import com.wms3.bms.standard.entity.outerInterface.SaleForm;
import com.wms3.bms.standard.entity.outerInterface.SaleFormDetail;

/**
 * 描述:
 * @author yao 2014-3-25
 * @since wmsBMS3.0
 */
public class OutboundSoBusImpl implements IOutboundSoBus {
   
	protected IOutboundSoDao outboundSoDao;
	protected EntityDAO m_dao = null;
	public OutboundSoBusImpl(EntityDAO dao) {
		outboundSoDao = new OutboundSoDaoImpl(dao);
		m_dao = dao;
	}
	/* (non-Javadoc)
	 * @see com.wms3.bms.standard.business.inbound.IInBoundPoBus#getInboundPoHeaders(java.lang.String, java.lang.String, java.lang.String)
	 */
	public PagingTool getOutboundSoHeaders(String m_strSaleFormId,String m_strSaleFormType,String m_strCustomerId,String m_strDownTime, String m_strIsOut,String m_strIsFinish,
    		String m_strIsInvalid,String strUrl,int maxrow) throws Exception {
		PagingTool pt = null;
		try {
			//查询sql
			String strSQL = outboundSoDao.getOutboundSoHeadersTime(m_strSaleFormId, m_strSaleFormType, m_strCustomerId, m_strDownTime, m_strIsOut, m_strIsFinish, m_strIsInvalid);
			if(strSQL!=null&&strSQL.length()>0){
				//查询总的记录
				String strCountHQL =" select count(*) "+strSQL;
				pt = CommonPagination.getPagingTool(outboundSoDao.getEntityDAO(), strCountHQL, strSQL, strUrl, 1, maxrow);	
			}
		} catch (Exception er) {
			Logger.info("根据条件查询出库订单出错(OutboundSoBusImpl.getOutboundSoHeaders)："+er.getMessage());
			throw new Exception("根据条件查询出库订单出错："+er.getMessage());
		}
		return pt;				
	}
	
	public PagingTool getOutboundSoHeadersByGD(String warehouseid,
			String customerid, String createdate,String sotype,String sostatus,String soflag,String erpno,String strUrl,int maxrow) throws Exception {
		PagingTool pt = null;
		try {
			//查询sql
			String strSQL = outboundSoDao.getOutboundSoHeadersByGD(null, sotype, sostatus, null, createdate, null, warehouseid, customerid, null, null, erpno, soflag);
			if(strSQL!=null&&strSQL.length()>0){
				//查询总的记录
				String strCountHQL =" select count(*) "+strSQL;
				pt = CommonPagination.getPagingTool(outboundSoDao.getEntityDAO(), strCountHQL, strSQL, strUrl, 1, maxrow);	
			}
		} catch (Exception er) {
			Logger.info("根据条件查询出库订单出错(OutboundSoBusImpl.getOutboundSoHeaders)："+er.getMessage());
			throw new Exception("根据条件查询出库订单出错："+er.getMessage());
		}
		return pt;				
	}
	public List getOutboundSoDetails(String soid) throws Exception {
		List ls = null;
		try {
			//查询sql
			String hql= "from SaleFormDetail saD where saD.m_strSaleFormId='"+soid+"'";
			ls = m_dao.searchEntities(hql);
		} catch (Exception er) {
			Logger.info("根据条件查询出库订单出错(OutboundSoBusImpl.getOutboundSoDetails)："+er.getMessage());
			throw new Exception("根据条件查询出库订单出错："+er.getMessage());
		}
		return ls;				
	}

	public PagingTool getOutboundSoDetailsPage(String soid, String strUrl, int maxrow) throws Exception
	{
		PagingTool pt = null;
		
		try{
			String strSQL= "from SaleFormDetail saD where saD.m_strSaleFormId='"+soid+"'";
			if(strSQL!=null&&strSQL.length()>0){
				//查询总的记录
				String strCountHQL =" select count(*) "+strSQL;
				pt = CommonPagination.getPagingTool(outboundSoDao.getEntityDAO(), strCountHQL, strSQL, strUrl, 1, maxrow);	
			}
		}catch (Exception er) {
			Logger.info("根据条件查询外来单详细出错(OutboundSoBusImpl.getOutboundSoDetailsPage)："+er.getMessage());
			throw new Exception("根据条件查询外来单详细出错："+er.getMessage());
		}
		
		return pt;
	}
	/**
	 * 功能：根据订单id
	 * @author yao 2014-2-28
	 * @param poid
	 * @return
	 * @throws Exception
	 */
	public SaleForm getOutboundSoHeaders(String soid) throws Exception{
		return outboundSoDao.getOutboundSoHeaderById(soid);
	}
    public List<SaleForm> getOutboundSoHeaderById(String soid) throws Exception {
		
		if (soid != null && soid.length() > 0) {
			String strSQL = "from SaleForm sa where sa.m_strSaleFormId='"+soid+"'";
			List<SaleForm> ls = m_dao.searchEntities(strSQL);
			if (ls != null && ls.size() > 0) {
				return ls;
			}
		}
		return null;
	}
	/**
	 * 功能：根据订单id
	 * @author yao 2014-2-28
	 * @param poid
	 * @param detailid
	 * @return
	 * @throws Exception
	 */
	public SaleFormDetail getOutboundSoHeaders(String soid,String detailid) throws Exception{
		return outboundSoDao.getOutboundSoDelById(soid,detailid);
	}
	
	public SaleFormDetail getSoDeByDId(String sodid) throws Exception{
		return outboundSoDao.getSoDeByDId(sodid);
	}
	
	/* (non-Javadoc)
	 * @see com.wms3.bms.standard.business.inbound.IInBoundPoBus#getPoDetailByDelId(java.lang.String)
	 */
	public SaleFormDetail getSoDetailByDelId(String sodelid) throws Exception {
		return outboundSoDao.getOutboundSoDelById(sodelid);
	}
	@Override
	public List<SaleForm> getOutboundSoHeaderById2(String soid) throws Exception {
		if (soid != null && soid.length() > 0) {
			String strSQL = "from SaleForm sa where sa.m_strSaleFormId='"+soid+"' and sotype in('1','5','7','8')";
			List<SaleForm> ls = m_dao.searchEntities(strSQL);
			if (ls != null && ls.size() > 0) {
				return ls;
			}
		}
		return null;
	}
/*	public List<ProcessTrack> getProcessTrackBySoId(String soid) throws Exception {
		if (soid != null && soid.length() > 0) {
			String strSQL = "from ProcessTrack where ecm01='" + soid + "'";
			List<ProcessTrack> ls = m_dao.searchEntities(strSQL);
			if (ls != null && ls.size() > 0) {
				return ls;
			}
		}
		return null;
	}
	public List<ProcessReport> getProcessBySoId(String soid) throws Exception {
		if (soid != null && soid.length() > 0) {
			String strSQL = "from ProcessReport where gongid='" + soid + "'";
			List<ProcessReport> ls = m_dao.searchEntities(strSQL);
			if (ls != null && ls.size() > 0) {
				return ls;
			}
		}
		return null;
	}*/

}
