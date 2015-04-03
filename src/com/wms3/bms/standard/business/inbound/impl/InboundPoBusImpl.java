package com.wms3.bms.standard.business.inbound.impl;

import java.util.ArrayList;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.base.IProductBus;
import com.wms3.bms.standard.business.base.impl.ProductBusImpl;
import com.wms3.bms.standard.business.inbound.IInBoundPoBus;
import com.wms3.bms.standard.constant.CommonReturn;
import com.wms3.bms.standard.constant.StandardConstant;
import com.wms3.bms.standard.dao.base.impl.BaseSeqDaoImpl;
import com.wms3.bms.standard.dao.inbound.IInboundPoDao;
import com.wms3.bms.standard.dao.inbound.impl.InboundPoDaoImpl;
import com.wms3.bms.standard.entity.base.BaseProduct;
import com.wms3.bms.standard.entity.base.BaseSeq;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundPoDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundPoHeader;

/**
 * 描述:
 * @author liuxh 2014-1-20
 * @since wmsBMS3.0
 */
public class InboundPoBusImpl implements IInBoundPoBus {
   
	protected IInboundPoDao inboundPoDao;
	protected EntityDAO m_dao = null;
	public InboundPoBusImpl(EntityDAO dao) {
		inboundPoDao = new InboundPoDaoImpl(dao);
		m_dao = dao;
	}

	/* (non-Javadoc)
	 * @see com.wms3.bms.standard.business.inbound.IInBoundPoBus#getInboundPoHeaders(java.lang.String, java.lang.String, java.lang.String)
	 */
	public PagingTool getInboundPoHeaders(String warehouseid,
			String customerid, String createdate,String poflag,String poid, String potype, String strUrl,int maxrow) throws Exception {
		PagingTool pt = null;
		try {
			//查询sql
			String strSQL = inboundPoDao.getInboundPoHeaders(poid, potype, null, null, createdate, null, warehouseid, customerid, poflag, null,
					null, null, null, null, null, null, null, null);
			if(strSQL!=null&&strSQL.length()>0){
				//查询总的记录
				String strCountHQL =" select count(*) "+strSQL;
				pt = CommonPagination.getPagingTool(inboundPoDao.getEntityDAO(), strCountHQL, strSQL, strUrl, 1, maxrow);	
			}
		} catch (Exception er) {
			Logger.info("根据条件查询采购订单出错(InboundPoBusImpl.getInboundPoHeaders)："+er.getMessage());
			throw new Exception("根据条件查询采购订单出错："+er.getMessage());
		}
		return pt;				
	}
	
	/**
	 * 根据条件查询PO集合
	 * @param warehouseid
	 * @param customerid
	 * @param createdate
	 * @param poflag
	 * @param poid
	 * @param potype
	 * @param strUrl
	 * @param maxrow
	 * @return
	 * @throws Exception
	 */
	public PagingTool getInboundPoHeader(String warehouseid,
			String customerid, String start_time,
			String end_time,String poflag,String poid, String potype, String strUrl,int maxrow) throws Exception {
		PagingTool pt = null;
		try {
			//查询sql
			String strSQL = inboundPoDao.getInboundPoHeader(poid, potype, null,  start_time,
					end_time, null, warehouseid, customerid, poflag, null,
					null, null, null, null, null, null, null, null);
			if(strSQL!=null&&strSQL.length()>0){
				//查询总的记录
				String strCountHQL =" select count(*) "+strSQL;
				pt = CommonPagination.getPagingTool(inboundPoDao.getEntityDAO(), strCountHQL, strSQL, strUrl, 1, maxrow);	
			}
		} catch (Exception er) {
			Logger.info("根据条件查询采购订单出错(InboundPoBusImpl.getInboundPoHeaders)："+er.getMessage());
			throw new Exception("根据条件查询采购订单出错："+er.getMessage());
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
	public InboundPoHeader getInboundPoHeaders(String poid) throws Exception{
		return inboundPoDao.getInboundPoHeaderById(poid);
	}
	
	/**
	 * 功能：根据订单id
	 * @author yao 2014-2-28
	 * @param poid
	 * @return
	 * @throws Exception
	 */
	public InboundPoHeader getInboundPoHeaderById(String poid) throws Exception{
		return inboundPoDao.getInboundPoHeaderById(poid);
	}
		
	/**
	 * 功能：根据订单详细id
	 * @author yao 2015-3-10
	 * @param podetailid
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> getJobInfoByPodetailid(String podetailid) throws Exception{
		return inboundPoDao.getJobInfoByPodetailid(podetailid);
	}
	/**
	 * 功能：根据订单id
	 * @author yao 2014-2-28
	 * @param poid
	 * @param detailid
	 * @return
	 * @throws Exception
	 */
	public InboundPoDetail getInboundPoHeaders(String poid,String detailid) throws Exception{
		return inboundPoDao.getInboundPoDelById(poid,detailid);
	}
	/**
	 * 功能：根据采购订单获得订单详细
	 * @author liuxh 2014-1-20
	 * @param poid  
	 * @param strUrl
	 * @param maxrow
	 * @return
	 * @throws Exception
	 */
	public PagingTool getInboundPoDelsByPoId(String poid, String strUrl, int maxrow)
			throws Exception {
		PagingTool pt = null;
		try {
			if(poid!=null&&poid.length()>0){
				CommonReturn cReturn = inboundPoDao.getInboundPoDelsByPoId(poid);
				if(cReturn!=null){
					String strHQL = cReturn.getStrQueryHQL();
					String strCountHql = cReturn.getStrCountHQL();
					pt = CommonPagination.getPagingTool(inboundPoDao.getEntityDAO(), strCountHql, strHQL, strUrl, 1, maxrow);
				}
			}
		} catch (Exception er) {
			Logger.info("根据采购单id查询明细出错（InboundPoBusImpl.getInboundPoDelsByPoId）："+er.getMessage());
			throw new Exception("根据采购单id查询明细出错（InboundPoBusImpl.getInboundPoDelsByPoId）："+er.getMessage());
		}

		return pt;
	}

	/* (non-Javadoc)
	 * @see com.wms3.bms.standard.business.inbound.IInBoundPoBus#createAsnByPoId(java.lang.String)
	 */
	public CommonReturn createAsnByPoId(String poid,String ids,String asntype,String warehouseid,String userCode) throws Exception {
		/*
		CommonReturn cReturn = new CommonReturn();
		IInboundAsnDao inboundAsnDao = new InboundAsnDaoImpl(inboundPoDao.getEntityDAO());
		IProductBus iproductbus  = new ProductBusImpl(inboundPoDao.getEntityDAO());
		//到货通知单
		InboundAsnHeader iAsnHeader = null;
		//到货通知单详细
		List<InboundAsnDetail> asnDetails = new ArrayList<InboundAsnDetail>();
		//修改采购订单数量
		List<InboundPoDetail> poDetails = new ArrayList<InboundPoDetail>();
		if (poid!=null) {
			InboundPoHeader iPoHeader = inboundPoDao.getInboundPoHeaderById(poid);
			if (iPoHeader!=null) {
				//创建预期到货单
				iAsnHeader = new InboundAsnHeader();
				BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(m_dao);
			    BaseSeq  seqEn = seqDao.getSeqByType("ASN");
			    //获得到货单id
	            String strInvoiceNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(),m_dao);	               
				iAsnHeader.setAsnid(strInvoiceNo);
				iAsnHeader.setArriveendtime(iPoHeader.getArriveendtime());
				iAsnHeader.setArrivestarttime(iPoHeader.getArrivestarttime());
				iAsnHeader.setAsnstatus("0");//状态
				iAsnHeader.setAsntype(iPoHeader.getPotype());//类型
				iAsnHeader.setCreatedate(StrTools.getCurrDateTime(8));
				iAsnHeader.setCreatetime(StrTools.getCurrDateTime(2));
				iAsnHeader.setCreatemanid(userCode);//创建人
				iAsnHeader.setCustomerid(iPoHeader.getCustomerid());//客户
				iAsnHeader.setPoid(iPoHeader.getPoid());//订单id
				iAsnHeader.setShipperid(iPoHeader.getShipperid());//承运人
				iAsnHeader.setSupplierid(iPoHeader.getSupplierid());//供应商
				iAsnHeader.setWarehouseid(warehouseid);//仓库id
				iAsnHeader.setIfstock(iPoHeader.getIfstock()==null?"N":iPoHeader.getIfstock());
				iAsnHeader.setReserve3(iPoHeader.getReserve3());//是否工艺否
				String[] delIdAndNum = ids.split(","); 
				String sqlMax = "select max(detail.asndetailid) from InboundAsnDetail as detail where detail.asnid='" + strInvoiceNo + "'";
                int sValue = SeqTool.getDetailMaxId(sqlMax, strInvoiceNo.length()+1,m_dao);			
				int i=1;
				InboundAsnDetail inaAsnDetail =null;
				for (String info : delIdAndNum) {
					String[] infos =  info.split("\\|");
					String num = infos[1]!=""?infos[1]:"0";
					InboundPoDetail poDetail = inboundPoDao.getInboundPoDelById(poid,infos[0]);
					if (poDetail!=null&&poDetail.getPonum()>=(poDetail.getUseponum()+Double.parseDouble(num))) {
						
						//创建预期到到货单明细
						inaAsnDetail = new InboundAsnDetail();
				        String strNextId = SeqTool.getCode(sValue+i, StandardConstant.D_LEN);
				        String strID = SeqTool.getDetailId(strInvoiceNo, strNextId);
						inaAsnDetail.setAsndetailid(strID);
						inaAsnDetail.setAsnid(iAsnHeader.getAsnid());
						inaAsnDetail.setAsnlinestatus("0");//状态
						inaAsnDetail.setEunit(poDetail.getEunit());//单位
						inaAsnDetail.setInvoicenum(Double.parseDouble(num));//开单数量
						//inaAsnDetail.setNetweight(poDetail.getNetweight());//净重
						inaAsnDetail.setPackid(poDetail.getPackid());//包装id
						inaAsnDetail.setPodetailid(poDetail.getPodetailid());
						inaAsnDetail.setPoid(iPoHeader.getPoid());
						inaAsnDetail.setPrice(poDetail.getPrice());//单价
						inaAsnDetail.setProductid(poDetail.getProductid());//产品id
						//inaAsnDetail.setVolume(poDetail.getVolume());//体积
						//inaAsnDetail.setWeight(poDetail.getWeight());//重量
						if(poDetail.getLotid()==null || poDetail.getLotid().equals("")){
							BaseProduct product = iproductbus.getProductById(poDetail.getProductid());
							if(product.getLotid()==null || product.getLotid().equals("")){
								cReturn.setRetInfo("请给相应的产品输入批次属性信息！");
								return cReturn;
							}
							inaAsnDetail.setLotid(product.getLotid());//属性id
						}else{
							inaAsnDetail.setLotid(poDetail.getLotid());//属性id
						}
						inaAsnDetail.setLotatt1(poDetail.getLotatt1());//属性1
						inaAsnDetail.setLotatt2(poDetail.getLotatt2());//属性2
						inaAsnDetail.setLotatt3(poDetail.getLotatt3());//属性3
						inaAsnDetail.setLotatt4(poDetail.getLotatt4());//属性4
						inaAsnDetail.setLotatt5(poDetail.getLotatt5());//属性5
						inaAsnDetail.setLotatt6(poDetail.getLotatt6());//属性6
						inaAsnDetail.setLotatt7(poDetail.getLotatt7());//属性7
						inaAsnDetail.setLotatt8(poDetail.getLotatt8());//属性8
						inaAsnDetail.setLotatt9(poDetail.getLotatt9());//属性9
						inaAsnDetail.setLotatt10(poDetail.getLotatt10());//属性10
						inaAsnDetail.setLotatt11(poDetail.getLotatt11());//属性11
						inaAsnDetail.setLotatt12(poDetail.getLotatt12());//属性12
						poDetail.setUseponum(poDetail.getUseponum()+Double.parseDouble(num));
						inaAsnDetail.setIfcheck(poDetail.getIfcheck());//是否需要质量校验
						asnDetails.add(inaAsnDetail);
						poDetails.add(poDetail);
					}else {
						cReturn.setRetInfo("输入数量["+Double.parseDouble(num)+"]大于单据数量！");
						return cReturn;
					}
					i++;
				}
				//保存到数据库
				inboundAsnDao.addAsnHeaderAndAsnDetails(iAsnHeader, asnDetails,poDetails);
				cReturn.setStrCountHQL(iAsnHeader.getAsnid());
			}
		}
		return cReturn;*/
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wms3.bms.standard.business.inbound.IInBoundPoBus#getPoDetailByDelId(java.lang.String)
	 */
	public InboundPoDetail getPoDetailByDelId(String podelid) throws Exception {
		// TODO Auto-generated method stub
		return inboundPoDao.getInboundPoDelById(podelid);
	}


	public List<InboundPoDetail> getInboundPoDelByPoId(String poid) throws Exception {
		// TODO Auto-generated method stub
		return inboundPoDao.getInboundPoDetailsByPoId(poid);
	}

}
