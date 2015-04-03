/**
 * 描述：采购订单dao操作
 * @authar liuxh 2014-1-20
 */
package com.wms3.bms.standard.dao.inbound.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.constant.CommonReturn;
import com.wms3.bms.standard.dao.AbstractDao;
import com.wms3.bms.standard.dao.inbound.IInboundPoDao;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundPoDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundPoHeader;

/**
 * 描述:
 * @author liuxh 2014-1-20
 * @since wmsBMS3.0
 */
public class InboundPoDaoImpl extends AbstractDao implements IInboundPoDao {
	
	/** 数据库基本的DAO操作 */
    public InboundPoDaoImpl(EntityDAO dao){
        this.m_dao = dao;
    }
	/* (non-Javadoc)
	 * @see com.wms3.bms.standard.dao.inbound.IInboundPoDao#getInboundPoHeaders(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getInboundPoHeaders(String poid,
			String potype, String postatus, String createtime,
			String createdate, String createmanid, String warehouseid,
			String customerid,String poflag, String arrivestarttime, String arriveendtime,
			String shipperid, String supplierid, String remark,
			String reserve1, String reserve2, String reserve3, String reserve4)
			throws Exception {	
		
		StringBuilder strBur = new StringBuilder();
		try {
	
			strBur.append("from InboundPoHeader as header where 1=1");
			if(poid!=null&&poid.length()>0){
				strBur.append(" and header.poid='").append(poid).append("'");
			}
			if(potype!=null&&potype.length()>0){
				strBur.append(" and header.potype='").append(potype).append("'");
			}
			if(postatus!=null&&postatus.length()>0){
				strBur.append(" and header.postatus='").append(postatus).append("'");
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
			if(poflag!=null&&poflag.length()>0){
				strBur.append(" and header.poflag='").append(poflag).append("'");
			}
			if(arrivestarttime!=null&&arrivestarttime.length()>0){
				strBur.append(" and header.arrivestarttime>='").append(arrivestarttime).append("'");
			}
			if(arriveendtime!=null&&arriveendtime.length()>0){
				strBur.append(" and header.arriveendtime<='").append(arriveendtime).append("'");
			}
			if(shipperid!=null&&shipperid.length()>0){
				strBur.append(" and header.shipperid='").append(shipperid).append("'");
			}
			if(remark!=null&&remark.length()>0){
				strBur.append(" and header.remark='").append(remark).append("'");
			}
			if(reserve1!=null&&reserve1.length()>0){
				strBur.append(" and header.reserve1='").append(reserve1).append("'");
			}
			if(reserve2!=null&&reserve2.length()>0){
				strBur.append(" and header.reserve2='").append(reserve2).append("'");
			}
			if(reserve3!=null&&reserve3.length()>0){
				strBur.append(" and header.reserve3='").append(reserve3).append("'");
			}
			if(reserve4!=null&&reserve4.length()>0){
				strBur.append(" and header.reserve4='").append(reserve4).append("'");
			}
			return strBur.toString();
				
		} catch (Exception er) {
			Logger.info("根据条件查询订单记出错："+er.getMessage());
			throw new Exception("根据条件查询订单记出错："+er.getMessage());
	    }
	}
	
	public String getInboundPoHeader(String poid,
			String potype, String postatus, String start_time,
			String end_time, String createmanid, String warehouseid,
			String customerid,String poflag, String arrivestarttime, String arriveendtime,
			String shipperid, String supplierid, String remark,
			String reserve1, String reserve2, String reserve3, String reserve4)
			throws Exception {	
		
		StringBuilder strBur = new StringBuilder();
		try {
	
			strBur.append("from InboundPoHeader as header where 1=1");
			if(poid!=null&&poid.length()>0){
				strBur.append(" and header.poid='").append(poid).append("'");
			}
			if(potype!=null&&potype.length()>0){
				strBur.append(" and header.potype='").append(potype).append("'");
			}
			if(postatus!=null&&postatus.length()>0){
				strBur.append(" and header.postatus='").append(postatus).append("'");
			}
			if(start_time!=null&&start_time.length()>0){
				strBur.append(" and header.createtime>='").append(start_time).append("'");
			}
			if(end_time!=null&&end_time.length()>0){
				strBur.append(" and header.createdate<='").append(end_time).append("'");
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
			if(poflag!=null&&poflag.length()>0){
				strBur.append(" and header.poflag='").append(poflag).append("'");
			}
			if(arrivestarttime!=null&&arrivestarttime.length()>0){
				strBur.append(" and header.arrivestarttime>='").append(arrivestarttime).append("'");
			}
			if(arriveendtime!=null&&arriveendtime.length()>0){
				strBur.append(" and header.arriveendtime<='").append(arriveendtime).append("'");
			}
			if(shipperid!=null&&shipperid.length()>0){
				strBur.append(" and header.shipperid='").append(shipperid).append("'");
			}
			if(remark!=null&&remark.length()>0){
				strBur.append(" and header.remark='").append(remark).append("'");
			}
			if(reserve1!=null&&reserve1.length()>0){
				strBur.append(" and header.reserve1='").append(reserve1).append("'");
			}
			if(reserve2!=null&&reserve2.length()>0){
				strBur.append(" and header.reserve2='").append(reserve2).append("'");
			}
			if(reserve3!=null&&reserve3.length()>0){
				strBur.append(" and header.reserve3='").append(reserve3).append("'");
			}
			if(reserve4!=null&&reserve4.length()>0){
				strBur.append(" and header.reserve4='").append(reserve4).append("'");
			}
			return strBur.toString();
				
		} catch (Exception er) {
			Logger.info("根据条件查询订单记出错："+er.getMessage());
			throw new Exception("根据条件查询订单记出错："+er.getMessage());
	    }
	}

	/* (non-Javadoc)
	 * @see com.wms3.bms.standard.dao.inbound.IInboundPoDao#getInboundPoHeaderById(java.lang.String)
	 */
	public InboundPoHeader getInboundPoHeaderById(String poid) throws Exception {
		
		if (poid != null && poid.length() > 0) {
			String strSQL = "from InboundPoHeader where poid='" + poid + "'";
			List<InboundPoHeader> ls = m_dao.searchEntities(strSQL);
			if (ls != null && ls.size() > 0) {
				return ls.get(0);
			}
		}
		return null;
	}
	/**
	 * 功能：根据订单详细id获得订单
	 * @author yao 2015-3-10
	 * @param poid
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> getJobInfoByPodetailid(String podetailid) throws Exception{
		List<Object[]> ls = null;
		if (podetailid != null && podetailid.length() > 0) {
			String hql = " from InoutJob ta,InoutJobdetail tb where ta.jobid=tb.jobid  and ta.boundstockdetailid='"+podetailid+"' ";
			ls = m_dao.searchEntities(hql);
		}
		return ls;
	}

	/* (non-Javadoc)
	 * @see com.wms3.bms.standard.dao.inbound.IInboundPoDao#updateInboundPoHeader(com.wms3.bms.standard.entity.inbound.InboundPoHeader)
	 */
	public void updateInboundPoHeader(InboundPoHeader inboundPoHeader)
			throws Exception {
		try {
			if(inboundPoHeader!=null){
				m_dao.update(inboundPoHeader);
			}
		} catch (Exception er) {
			Logger.info("修改订单信息出错："+er.getMessage());
			throw new Exception("修改订单信息出错："+er.getMessage());
		}

	}

	/* (non-Javadoc)
	 * @see com.wms3.bms.standard.dao.inbound.IInboundPoDao#getInboundPoDelsByPoId(java.lang.String)
	 */
	public CommonReturn getInboundPoDelsByPoId(String poid)
			throws Exception {
		CommonReturn cReturn = new CommonReturn();
		try {
			if(poid!=null&&poid.length()>0){
				String strSQL ="from InboundPoDetail where poid='"+poid+"'";
				List<InboundPoDetail> ls = m_dao.searchEntities(strSQL);
				cReturn.setLsReturn(ls);
				cReturn.setStrCountHQL("select count(*) "+strSQL);
				cReturn.setStrQueryHQL(strSQL);
			}
		} catch (Exception er) {
			Logger.info("根据订单查询订单详细出错："+er.getMessage());
			throw new Exception("根据订单查询订单详细出错："+er.getMessage());
		}
		return cReturn;
	}

	/* (non-Javadoc)
	 * @see com.wms3.bms.standard.dao.inbound.IInboundPoDao#getInboundPoDelById(java.lang.String)
	 */
	public InboundPoDetail getInboundPoDelById(String podelid) throws Exception {
		try {
			if(podelid!=null&&podelid.length()>0){
				String strSQL = "from InboundPoDetail where podetailid='"+podelid+"'";
				List<InboundPoDetail> ls = m_dao.searchEntities(strSQL);
				if(ls!=null&&ls.size()>0){
					return ls.get(0);
				}
			}
		} catch (Exception er) {
			Logger.info("根据采购单详细id获得采购单详细出错："+er.getMessage());
			throw new Exception("根据采购单详细id获得采购单详细出错："+er.getMessage());
		}
		return null;
	}
	
	public List<InboundPoDetail> getInboundPoDetailsByPoId(String poid) throws Exception {
		List<InboundPoDetail> details=null;
		try {
			if(poid!=null&&poid.length()>0){
				String strSQL = "from InboundPoDetail where poid='"+poid+"'";
				details = m_dao.searchEntities(strSQL);
			}
		} catch (Exception er) {
			Logger.info("根据采购单详细id获得采购单详细出错："+er.getMessage());
			throw new Exception("根据采购单详细id获得采购单详细出错："+er.getMessage());
		}
		return details;
	}
	/**
	 * 功能：根据采购单详细id及采购单id获得采购单详细
	 * @author yao 2014-2-28
	 * @param poid
	 * @param podelid
	 * @return
	 * @throws Exception
	 */
	public InboundPoDetail getInboundPoDelById(String poid,String podelid) throws Exception{
		try {
			if(podelid!=null&&podelid.length()>0){
				String strSQL = "from InboundPoDetail where podetailid='"+podelid+"' and poid='"+poid+"'";
				List<InboundPoDetail> ls = m_dao.searchEntities(strSQL);
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
	

}
