package com.wms3.bms.standard.dao.outbound.impl;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.dao.AbstractDao;
import com.wms3.bms.standard.dao.outbound.IOutboundSoDao;
import com.wms3.bms.standard.entity.outerInterface.SaleForm;
import com.wms3.bms.standard.entity.outerInterface.SaleFormDetail;

/**
 * 描述:
 * @author yao 2014-3-25
 * @since wmsBMS3.0
 */
public class OutboundSoDaoImpl extends AbstractDao implements IOutboundSoDao {
	
	/** 数据库基本的DAO操作 */
    public OutboundSoDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
    public String getOutboundSoHeaders(String m_strSaleFormId,String m_strSaleFormType,String m_strCustomerId,String m_strDownTime, String m_strIsOut,String m_strIsFinish,
    		String m_strIsInvalid)
			throws Exception {	
		StringBuilder strBur = new StringBuilder();
		try {
			strBur.append("From SaleForm header where 1=1");
			if(m_strSaleFormId!=null&&m_strSaleFormId.length()>0){
				strBur.append(" and header.m_strSaleFormId='").append(m_strSaleFormId).append("'");
			}
			if(m_strSaleFormType!=null&&m_strSaleFormType.length()>0){
				strBur.append(" and header.m_strSaleFormType='").append(m_strSaleFormType).append("'");
			}
			if(m_strCustomerId!=null&&m_strCustomerId.length()>0){
				strBur.append(" and header.m_strCustomerId='").append(m_strCustomerId).append("'");
			}
			if(m_strDownTime!=null&&m_strDownTime.length()>0){
				strBur.append(" and header.m_strDownTime like '").append(m_strDownTime).append("%'");
			}
			if(m_strIsOut!=null&&m_strIsOut.length()>0){
				strBur.append(" and header.m_strIsOut='").append(m_strIsOut).append("'");
			}
			if(m_strIsFinish!=null&&m_strIsFinish.length()>0){
				strBur.append(" and header.m_strIsFinish='").append(m_strIsFinish).append("'");
			}
			if(m_strIsInvalid!=null&&m_strIsInvalid.length()>0){
				strBur.append(" and header.m_strIsInvalid='").append(m_strIsInvalid).append("'");
			}
			
			return strBur.toString();
				
		} catch (Exception er) {
			Logger.info("根据条件查询订单记出错："+er.getMessage());
			throw new Exception("根据条件查询订单记出错："+er.getMessage());
	    }
	}
    public String getOutboundSoHeadersTime(String m_strSaleFormId,String m_strSaleFormType,String m_strCustomerId,String m_strDownTime, String m_strIsOut,String m_strIsFinish,
    		String m_strIsInvalid) throws Exception
	{
    	String sql = getOutboundSoHeaders(m_strSaleFormId, m_strSaleFormType, m_strCustomerId, m_strDownTime, m_strIsOut, m_strIsFinish, m_strIsInvalid);
    	StringBuilder strBur = new StringBuilder();
    	strBur.append(sql);
    	return strBur.toString();
	}
    
    public String getOutboundSoHeadersByGD(String soid,String sotype,String sostatus,String createtime, String createdate,String createmanid,
    		String warehouseid,String customerid,String releasestatus,String consigneeid, String erpno,String soflag)
			throws Exception {	
		StringBuilder strBur = new StringBuilder();
		try {
			strBur.append("from SaleForm as header where header.isfqc='N' and  sotype in('1','5','7','8') ");
			if(soid!=null&&soid.length()>0){
				strBur.append(" and header.soid='").append(soid).append("'");
			}
			if(sotype!=null&&sotype.length()>0){
				strBur.append(" and header.sotype='").append(sotype).append("'");
			}
			if(sostatus!=null&&sostatus.length()>0){
				strBur.append(" and header.sostatus='").append(sostatus).append("'");
			}
			if(createtime!=null&&createtime.length()>0){
				strBur.append(" and header.createtime='").append(createtime).append("'");
			}
			if(createdate!=null&&createdate.length()>0){
				strBur.append(" and header.createdate='").append(createdate).append("'");
			}
			if(createmanid!=null&&createmanid.length()>0){
				strBur.append(" and header.createmanid='").append(createmanid).append("'");
			}
			if(warehouseid!=null&&warehouseid.length()>0){
				strBur.append(" and header.warehouseid='").append(warehouseid).append("'");
			}
			if(customerid!=null&&customerid.length()>0){
				strBur.append(" and header.customerid='").append(customerid).append("'");
			}
			if(releasestatus!=null&&releasestatus.length()>0){
				strBur.append(" and header.releasestatus='").append(releasestatus).append("'");
			}
			if(consigneeid!=null&&consigneeid.length()>0){
				strBur.append(" and header.consigneeid>='").append(consigneeid).append("'");
			}
			if(erpno!=null&&erpno.length()>0){
				strBur.append(" and header.erpno<='").append(erpno).append("'");
			}
			if(soflag!=null&&soflag.length()>0){
				strBur.append(" and header.soflag='").append(soflag).append("'");
			}
			return strBur.toString();
				
		} catch (Exception er) {
			Logger.info("根据条件查询订单记出错："+er.getMessage());
			throw new Exception("根据条件查询订单记出错："+er.getMessage());
	    }
	}
	public SaleForm getOutboundSoHeaderById(String soid) throws Exception {
		
		if (soid != null && soid.length() > 0) {
			String strSQL = "From SaleForm sa where sa.m_strSaleFormId='"+soid+"'";
			List<SaleForm> ls = m_dao.searchEntities(strSQL);
			if (ls != null && ls.size() > 0) {
				return ls.get(0);
			}
		}
		return null;
	}

	public void updateOutboundSoHeader(SaleForm outboundSoHeader)
			throws Exception {
		try {
			if(outboundSoHeader!=null){
				m_dao.update(outboundSoHeader);
			}
		} catch (Exception er) {
			Logger.info("修改订单信息出错："+er.getMessage());
			throw new Exception("修改订单信息出错："+er.getMessage());
		}

	}

	/* (non-Javadoc)
	 * @see com.wms3.bms.standard.dao.inbound.IInboundPoDao#getInboundPoDelById(java.lang.String)
	 */
	public SaleFormDetail getOutboundSoDelById(String sodelid) throws Exception {
		try {
			if(sodelid!=null&&sodelid.length()>0){
				String strSQL = "from SaleFormDetail saD where saD.m_strSaleFormId='"+sodelid+"'";
				List<SaleFormDetail> ls = m_dao.searchEntities(strSQL);
				if(ls!=null&&ls.size()>0){
					return ls.get(0);
				}
			}
		} catch (Exception er) {
			Logger.info("根据订单详细id获得订单详细出错："+er.getMessage());
			throw new Exception("根据订单详细id获得订单详细出错："+er.getMessage());
		}
		return null;
	}
	
	public SaleFormDetail getSoDeByDId(String sodid) throws Exception {
		try {
			if(sodid!=null&&sodid.length()>0){
				String strSQL = "from SaleFormDetail saD where saD.m_strSaleFormDetailId='"+sodid+"'";
				List<SaleFormDetail> ls = m_dao.searchEntities(strSQL);
				if(ls!=null&&ls.size()>0){
					return ls.get(0);
				}
			}
		} catch (Exception er) {
			Logger.info("根据订单详细id获得订单详细出错："+er.getMessage());
			throw new Exception("根据订单详细id获得订单详细出错："+er.getMessage());
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.wms3.bms.standard.dao.outbound.IOutboundSoDao#getOutboundSoDelById(java.lang.String, java.lang.String)
	 */
	public SaleFormDetail getOutboundSoDelById(String soid,String sodelid) throws Exception{
		try {
			
			String strSQL = "from SaleFormDetail saD where 1=1 ";
			if(soid != null && soid.length() > 0){
				strSQL += " and saD.m_strSaleFormId='"+soid+"' ";
			}
			if(sodelid != null && sodelid.length() > 0){
				strSQL += " and saD.m_strSaleFormDetailId='"+sodelid+"' ";
			}
			List<SaleFormDetail> ls = m_dao.searchEntities(strSQL);
			if(ls!=null&&ls.size()>0){
				return ls.get(0);
			}
			
		} catch (Exception er) {
			Logger.info("根据订单详细id获得订单详细出错："+er.getMessage());
			throw new Exception("根据订单详细id获得订单详细出错："+er.getMessage());
		}
		return null;
	}

	public List canBGByHeadId(String soid) throws Exception
	{
		List ls = null;
		String strSql = null;
		try
		{
			if(soid != null && soid.length() > 0)
			{
				strSql = "select sum(ponum),sum(assignnum) From SaleFormDetail where soid='"+soid+"'";
				ls = m_dao.searchEntities(strSql);
			}
		}catch(Exception er)
		{
			Logger.info("根据so单id获得订单详细出错："+er.getMessage());
			throw new Exception("根据so单id获得订单详细出错："+er.getMessage());
		}
		return ls;
	}

}
