package com.wms3.bms.standard.business.outbound.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.base.ILotBus;
import com.wms3.bms.standard.business.base.impl.LotBusImpl;
import com.wms3.bms.standard.business.outbound.IOutBoundBus;
import com.wms3.bms.standard.business.outbound.IOutboundSoBus;
import com.wms3.bms.standard.dao.base.impl.BaseSeqDaoImpl;
import com.wms3.bms.standard.dao.job.IJobDao;
import com.wms3.bms.standard.dao.job.impl.JobDaoImpl;
import com.wms3.bms.standard.dao.outbound.IOutboundDao;
import com.wms3.bms.standard.dao.outbound.impl.OutboundDaoImpl;
import com.wms3.bms.standard.entity.base.BaseLotDetail;
import com.wms3.bms.standard.entity.base.BaseLotSet;
import com.wms3.bms.standard.entity.base.BaseSeq;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;
import com.wms3.bms.standard.entity.outerInterface.SaleFormDetail;

/**
 * 描述: 出库单管理业务类
 * @author hug 2012-9-13
 * @since WMS 3.0
 */
public class OutBoundBusImpl implements IOutBoundBus{
    /** 出库单DAO类  */
    protected IOutboundDao outBoundDAO;
    /** 作业DAO类  */
    protected IJobDao jobDao;
    
    public OutBoundBusImpl(EntityDAO dao){
        outBoundDAO = new OutboundDaoImpl(dao);
        jobDao = new JobDaoImpl(dao); 
    }
    /**
     * 增加出库单
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IOutBoundBus#addOutBound(com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader)
     */
    public String addOutBound(OutboundInvoiceHeader invoice) throws Exception {
        //获得单据ID
    	BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(outBoundDAO.getEntityDAO());
        BaseSeq  seqEn = seqDao.getSeqByType("CKD");
        String strID = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), outBoundDAO.getEntityDAO());
        invoice.setOutstockid(strID);
        //增加出库单
        outBoundDAO.addOutBound(invoice);
        return strID;
    }
    /**
     * 修改出库单
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IOutBoundBus#updateOutBound(com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader)
     */
    public String updateOutBound(OutboundInvoiceHeader invoice) throws Exception {
        String strMsg = "操作成功!";
        //同步  收货单号
        synchronized (invoice.getOutstockid()) {
            if(invoice.getOutstatus().equals("0")){
                outBoundDAO.updateOutBound(invoice);
            }else{
                 strMsg = "出库单[" + invoice.getOutstockid()  + "]状态（" + invoice.getOutstatus() + "）不为开单状态1！无法修改!";
            }         
        }
        return strMsg;  
    }
    /**
     * 增加出库单详细
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IOutBoundBus#addOutBoundDetail(com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail, java.lang.String)
     */
    public String addOutBoundDetail(OutboundInvoiceDetail outBoundDetail, String strInvoiceId) throws Exception {
        String strMsg = "操作成功!";
        //同步  出库单号
        synchronized (strInvoiceId) {
            //出库单
            OutboundInvoiceHeader invoice = getOutBoundById(strInvoiceId);
            if(invoice != null){
                if(invoice.getOutstatus().equals("0")){     
                    outBoundDAO.addOutBoundDetail(outBoundDetail);
                }else{
                        strMsg = "出库单[" + strInvoiceId  + "]状态（" + invoice.getOutstatus() + "）不为开单状态0！无法新增出库单详细!";
                }    
            }     
        }
        return strMsg;
    }
    /**
     * 修改出库单详细
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IOutBoundBus#updateOutBoundDetail(com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail, java.lang.String)
     */
    public String updateOutBoundDetail(OutboundInvoiceDetail outBoundDetail, String strInvoiceId) throws Exception {
        String strMsg = "操作成功!";
        //同步  出库单号
        synchronized (strInvoiceId) {
            //出库单
            OutboundInvoiceHeader invoice = getOutBoundById(strInvoiceId);
            if(invoice != null){
                //出库单的状态
                //出库单详细的状态
                if(invoice.getOutstatus().equals("0") && outBoundDetail.getLinestatus().equals("0")){     
                    outBoundDAO.updateOutBoundDetail(outBoundDetail);
                }else{
                    strMsg = "出库单[" + strInvoiceId  + "]状态（" + invoice.getOutstatus() + "）或出库单详细状态(" + outBoundDetail.getLinestatus() + ")不为开单状态0！无法修改出库单详细!";
                }    
            }     
        }
        return strMsg;
    }
    /**
     * 删除出库单或出库单详细
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IOutBoundBus#deleteOutBoundInvoice(java.lang.String, java.lang.String, java.lang.String)
     */
    public String deleteOutBoundInvoice(String strOutBoundId, String strOutBoundDetailId, String strFlag) throws Exception {

        String strMsg = "操作成功!";
        //同步  单据号
        synchronized (strOutBoundId) { 
            OutboundInvoiceHeader invoice = getOutBoundById(strOutBoundId);
            if(invoice != null){
                if(strFlag != null && strFlag.trim().equals("1")){ //1：删除单据
                    if(invoice.getOutstatus().equals("0")&& !isFinishJobByOutIdAndCId(strOutBoundId)){//单据状态为开单状态;是否存在已完成作业
                        outBoundDAO.deleteOutBound(strOutBoundId);   
                    }else{
                        strMsg = "单据[" + strOutBoundId  + "]状态（" + invoice.getOutstatus() + "）不为开单状态0，或者 存在已经完成的作业，不能删除出库单！";
                    }
                }else if(strFlag != null && strFlag.trim().equals("2")){ //2：删除单据详细
                    //出库单详细的状态  
                    OutboundInvoiceDetail outBoundDetail = getOutBoundDetailById(strOutBoundDetailId);
                    if(outBoundDetail != null){
                        if(outBoundDetail.getLinestatus().equals("0")){//为开单状态时才能删除
                            outBoundDAO.deleteOutBoundDetail(strOutBoundDetailId);  
                        }else{
                            strMsg = "出库单[" + strOutBoundId  + "]的出库详细[" + strOutBoundDetailId + "]状态（" + outBoundDetail.getLinestatus() + "）不为开单状态0，不能删除出库单详细！";
                        }     
                    }else{
                        strMsg = "出库单[" + strOutBoundId  + "]的出库单详细[" + strOutBoundDetailId + "]不存在,无法做删除操作！";
                    }
                }
            }else{
                strMsg = "出库单[" + strOutBoundId  + "]不存在,无法做删除操作！";
            }
        } 
        return strMsg;
    }
    /**
     * 根据出库单ID获得出库单
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IOutBoundBus#getOutBoundById(java.lang.String)
     */
    public OutboundInvoiceHeader getOutBoundById(String strOutBoundId) throws Exception {
        return outBoundDAO.getOutBoundById(strOutBoundId);
    }
    /**
     * 根据出库单详细ID获得出库单详细
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IOutBoundBus#getOutBoundDetailById(java.lang.String)
     */
    public OutboundInvoiceDetail getOutBoundDetailById(String strInvoiceDetailId) throws Exception {
        return outBoundDAO.getOutBoundDetailById(strInvoiceDetailId);
    }
    /**
     * 功能:获取没有完全分配的出库产品信息
     * @author yao 2015-3-11
     * @return
     * @throws Exception
     */
    public List getOutBoundProudctInfo() throws Exception{
    	return outBoundDAO.getOutBoundProudctInfo();
    }
    /**
     * 根据出库单ID获得出库单详细
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IOutBoundBus#getOutBoundDetailsById(java.lang.String)
     */
    public List<OutboundInvoiceDetail> getOutBoundDetailsById(String strInvoiceId) throws Exception {
        return outBoundDAO.getOutBoundDetailsById(strInvoiceId);
    }
    /**
     * 根据仓库ID获得需要发货确认的出库单
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IOutBoundBus#getSendOutInvoice(java.lang.String)
     */
    public List<OutboundInvoiceHeader> getSendOutInvoice(String warehouseid) throws Exception {
        return outBoundDAO.getSendOutInvoice(warehouseid);
    }
    /**
     * 根据仓库ID获得需要发货确认的B客户的出库单
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IOutBoundBus#getSendOutInvoice(java.lang.String, java.lang.String)
     */
    public List<OutboundInvoiceHeader> getSendOutInvoice(String warehouseid, String aoutid) throws Exception {
        return outBoundDAO.getSendOutInvoice(warehouseid, aoutid);
    }
    
    public String getOutBoundCountSQL(String warehouseid, String outstatus, String outtype, String shiftid, String screatedate, String ecreatedate, String customerid, String outno) {
        StringBuilder strBud = new StringBuilder();
        strBud.append("select count(inv.outstockid) from OutboundInvoiceHeader as inv where 1=1 ");
        if(warehouseid != null && warehouseid.trim().length() >0){     
            strBud.append(" and inv.warehouseid='").append(warehouseid).append("'");
        }
        if(outstatus != null && outstatus.trim().length() >0){
            strBud.append(" and inv.outstatus='").append(outstatus).append("'");
        }
        if(outtype != null && outtype.trim().length() >0){
            strBud.append(" and inv.outtype='").append(outtype).append("'");
        }
        if(shiftid != null && shiftid.trim().length() >0){
            strBud.append(" and inv.shiftid='").append(shiftid).append("'");
        }
        if(screatedate != null && screatedate.trim().length() >0){
            strBud.append(" and inv.formdate >='").append(screatedate).append("'");
        }
        if(ecreatedate != null && ecreatedate.trim().length() >0){
            strBud.append(" and inv.formdate <='").append(ecreatedate).append("'");
        }
        if(customerid != null && customerid.trim().length() >0){
            strBud.append(" and inv.customerid='").append(customerid).append("'");
        }
        if(outno != null && outno.trim().length() >0){
            strBud.append(" and inv.outstockid='").append(outno).append("'");
        }
        return strBud.toString();
    }
    public String getOutBoundQuerySQL(String warehouseid, String outstatus, String outtype, String shiftid, String screatedate, String ecreatedate, String customerid, String outno) {
        StringBuilder strBud = new StringBuilder();
        strBud.append("from OutboundInvoiceHeader as inv where 1=1 ");
        if(warehouseid != null && warehouseid.trim().length() >0){     
            strBud.append(" and inv.warehouseid='").append(warehouseid).append("'");
        }
        if(outstatus != null && outstatus.trim().length() >0){
            strBud.append(" and inv.outstatus='").append(outstatus).append("'");
        }
        if(outtype != null && outtype.trim().length() >0){
            strBud.append(" and inv.outtype='").append(outtype).append("'");
        }
        if(shiftid != null && shiftid.trim().length() >0){
            strBud.append(" and inv.shiftid='").append(shiftid).append("'");
        }
        if(screatedate != null && screatedate.trim().length() >0){
            strBud.append(" and inv.formdate >='").append(screatedate).append("'");
        }
        if(ecreatedate != null && ecreatedate.trim().length() >0){
            strBud.append(" and inv.formdate <='").append(ecreatedate).append("'");
        }
        if(customerid != null && customerid.trim().length() >0){
            strBud.append(" and inv.customerid='").append(customerid).append("'");
        }
        if(outno != null && outno.trim().length() >0){
            strBud.append(" and inv.outstockid='").append(outno).append("'");
        }
        return strBud.toString();
    }
    /**
     * 出库分配->统计预配的数量
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IOutBoundBus#getGroupSoftallocationNum(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public Object[] getGroupSoftallocationNum(String warehouseid, String whAreaId, String ownerid, String productid, String packid, String lotatt1, String lotatt2, String lotatt3, String lotatt4, String lotatt5, String lotatt6, String lotatt7, String lotatt8, String lotatt9, String lotatt10, String lotatt11, String lotatt12) throws Exception {
        Object[] obj = null;
        StringBuilder strBud = new StringBuilder();
        strBud.append("select sum(sto.assignnum), ")
        .append("sum(sto.weight), ")
        .append("sum(sto.netweight), ")
        .append("sum(sto.volume) ")
        .append(" from OutboundSoftallocation sto where sto.status='0' ");
        //仓库ID
        if(warehouseid != null && warehouseid.trim().length() > 0){
            strBud.append(" and sto.warehouseid='").append(warehouseid).append("'");
        }
        //库区ID
        if(whAreaId != null && whAreaId.trim().length() > 0){ 
            strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
        }
        //货主
        if(ownerid != null && ownerid.trim().length()>0){
            strBud.append(" and sto.ownerId='").append(ownerid).append("'");
        } 
        //品名ID
        if(productid != null && productid.trim().length() > 0){
            strBud.append(" and sto.productid='").append(productid).append("'");
        }
        //包装
        if(packid != null && packid.trim().length() > 0){
            strBud.append(" and sto.packid='").append(packid).append("'");
        }
        //批次属性1
        if(lotatt1 != null && lotatt1.trim().length() > 0){
            strBud.append(" and sto.lotatt1='").append(lotatt1).append("'");
        }
        //批次属性2
        if(lotatt2 != null && lotatt2.trim().length() > 0){
            strBud.append(" and sto.lotatt2='").append(lotatt2).append("'");
        }
        //批次属性3
        if(lotatt3 != null && lotatt3.trim().length() > 0){
            strBud.append(" and sto.lotatt3='").append(lotatt3).append("'");
        }
        //批次属性4
        if(lotatt4 != null && lotatt4.trim().length() > 0){
            strBud.append(" and sto.lotatt4='").append(lotatt4).append("'");
        }
        //批次属性5
        if(lotatt5 != null && lotatt5.trim().length() > 0){
            strBud.append(" and sto.lotatt5='").append(lotatt5).append("'");
        }
        //批次属性6
        if(lotatt6 != null && lotatt6.trim().length() > 0){
            strBud.append(" and sto.lotatt6='").append(lotatt6).append("'");
        }
        //批次属性7
        if(lotatt7 != null && lotatt7.trim().length() > 0){
            strBud.append(" and sto.lotatt7='").append(lotatt7).append("'");
        }
        //批次属性8
        if(lotatt8 != null && lotatt8.trim().length() > 0){
            strBud.append(" and sto.lotatt8='").append(lotatt8).append("'");
        }
        //批次属性9
        if(lotatt9 != null && lotatt9.trim().length() > 0){
            strBud.append(" and sto.lotatt9='").append(lotatt9).append("'");
        }
        //批次属性10
        if(lotatt10 != null && lotatt10.trim().length() > 0){
            strBud.append(" and sto.lotatt10='").append(lotatt10).append("'");
        }
        //批次属性11
        if(lotatt11 != null && lotatt11.trim().length() > 0){
            strBud.append(" and sto.lotatt11='").append(lotatt11).append("'");
        }
        //批次属性12
        if(lotatt12 != null && lotatt12.trim().length() > 0){
            strBud.append(" and sto.lotatt12='").append(lotatt12).append("'");
        }
        List ls = outBoundDAO.querySQL(strBud.toString());
        if(ls != null && ls.size() > 0){
            obj = (Object[])ls.get(0);
        }
        return obj;
    }
    
    /**
	 * 功能:发货查询
	 * @param warehouseid	仓库
	 * @param whAreaId		库区
	 * @param senddate_from	发货日期
	 * @param senddate_to	发货日期
	 * @param shiftid		班次
	 * @param productid		品名
	 * @param customerid	客户
	 * @param tray_code		托盘条码
	 * @param lotid			批次属性id
     * @param lotatt1   	批次属性1
     * @param lotatt2   	批次属性2
     * @param lotatt3   	批次属性3
     * @param lotatt4   	批次属性4
     * @param lotatt5   	批次属性5
     * @param lotatt6   	批次属性6
     * @param lotatt7   	批次属性7
     * @param lotatt8   	批次属性8
     * @param lotatt9   	批次属性9
     * @param lotatt10  	批次属性10
     * @param lotatt11  	批次属性11
     * @param lotatt12  	批次属性12
     * @param lsLot 
	 * @return 
	 * @throws Exception
	 */
	public String[] getOutboundSend(String warehouseid, String whAreaId, String senddate_from, String senddate_to, String shiftid, String productid, 
			String customerid, String traycode, String lotinfo) throws Exception {
		
		String[] strSqls = new String[2];
		
		try {
			StringBuilder strBud = new StringBuilder();
			StringBuilder strBudGrp = new StringBuilder();
			StringBuilder strBudCon = new StringBuilder();
			
			strBud.append("select invoice.outstockid, invoice.customername, invoice.vehicleno, invoice.vehiclepos, jobdetail.m_strProductName," +
					" job.traycode, job.scargoSpaceName, sum(jobdetail.assignnum), sum(jobdetail.assignvolume), sum(jobdetail.assignweight), sum(jobdetail.assignnetweight), jobdetail.lotinfo, jobdetail.printdate");
			
			strBudGrp.append(" group by invoice.outstockid, invoice.customerid, invoice.vehicleno, invoice.vehiclepos, jobdetail.productid,job.traycode, job.scargoSpaceId, jobdetail.lotinfo, jobdetail.printdate");
			
            strBudCon.append(" from OutboundInvoiceHeader invoice, InoutJob as job, InoutJobdetail as jobdetail" +
					" where job.jobid=jobdetail.jobid and job.boundstockid=invoice.outstockid " +
					" and invoice.outstatus='7' and job.status='4' and jobdetail.assignnum<>0");
			
			if(warehouseid != null && warehouseid.trim().length() > 0){		//仓库
				strBudCon.append(" and job.warehouseid='").append(warehouseid).append("'");
			}
			
			if(whAreaId != null && whAreaId.trim().length() > 0){		//库区
				strBudCon.append(" and job.tcargoWhareaId='").append(whAreaId).append("'");
			}
			
			if(productid != null && productid.trim().length() > 0){		//品名
				strBudCon.append(" and jobdetail.productid='").append(productid).append("'");
			}
			
			if(customerid != null && customerid.trim().length() > 0){	//客户
				strBudCon.append(" and invoice.customerid='").append(customerid).append("'");
			}

			if(shiftid != null && shiftid.trim().length() > 0){		//班次
				strBudCon.append(" and job.shiftid='").append(shiftid).append("'");
			}
			
			if(traycode != null && traycode.trim().length() > 0){		//托盘条码
				strBudCon.append(" and job.traycode='").append(traycode).append("'");
			}
			
			if(lotinfo != null && lotinfo.trim().length() > 0){		//托盘条码
				strBudCon.append(" and jobdetail.lotinfo='").append(lotinfo).append("'");
			}
			
			if(senddate_from != null && senddate_from.trim().length() > 0){		//发货日期
				strBudCon.append(" and invoice.formdate>='").append(senddate_from).append("'");
			}
			
			if(senddate_to != null && senddate_to.trim().length() > 0){		//发货日期
				strBudCon.append(" and invoice.formdate<='").append(senddate_to).append("'");
			}
	        
	        strBud.append(strBudCon).append(strBudGrp);
			
			strSqls[0] = "select count(*) " + strBudCon.toString();
            strSqls[1] = strBud.toString() + " order by invoice.outstockid";
			
		} catch (Exception er) {
			throw new Exception("拼接发货查询的查询语句时候出错:" + er.getMessage());
		}
				
		return strSqls;
	}
	
	/**
     * 功能:获得批次属性的SQL
     * @param flg		第几个批次属性
     * @param lotatt	批次属性的值
     * @param lotDetail	批次属性
     * @return
     * @throws Exception
     */
	private String getSqlLotatt(String flg,	String lotatt, BaseLotDetail lotDetail) {
		
        StringBuilder strBud = new StringBuilder();	
		if(lotDetail != null){
			
			//页面查询时候的模式 1-精确查询,2-模糊查询(文本格式),3-范围查询(日期格式)
			String strLotsearch = lotDetail.getM_strLotsearch();	
		    
		    if(strLotsearch != null && strLotsearch.equals("1")){       //1-精确查询
                strBud.append(" and jobdetail.lotatt").append(flg).append(" ='").append(lotatt).append("'");   	
		    }else if(strLotsearch != null && strLotsearch.equals("2")){  //2-模糊查询
                strBud.append(" and jobdetail.lotatt").append(flg).append(" like '%").append(lotatt).append("%'");        
		    }else if(strLotsearch != null && strLotsearch.equals("3")){  //3-范围查询(日期格式)    	
	    		String[] lotatts =  lotatt.split("\\|");
		        if(lotatts.length>0 && lotatts[0] !=null && lotatts[0].trim().length() > 0){
                    strBud.append(" and jobdetail.lotatt").append(flg).append(" >='").append(lotatts[0]).append("'");
		        }
		        if(lotatts.length>1 && lotatts[1] !=null && lotatts[1].trim().length() > 0){
                    strBud.append(" and jobdetail.lotatt").append(flg).append(" <='").append(lotatts[1]).append("'");
		        }
		        
		    }else{		//没有选择查询方式的时候，按照精确查询来处理
                strBud.append(" and jobdetail.lotatt").append(flg).append(" ='").append(lotatt).append("'");
		    }
		}
		return strBud.toString();
	}
	
	/**
     * 功能：作废出库单
     * @param ids 出库单ID
     * @return
     * @throws Exception
     */
	public String cancelInvoices(String ids, String strUserCode) throws Exception {
		
		String strBackMsg = "Y";
		try {
			
			OutboundInvoiceHeader outboundinvoice = null;
			OutboundInvoiceDetail detail = null;
			List<OutboundInvoiceDetail> lsDetail;
			
			String[] outstockid = ids.split(",");
			for(int i=0; i<outstockid.length; i++){
				
				outboundinvoice = getOutBoundById(outstockid[i]);	//出库单据
				lsDetail = getOutBoundDetailsById(outstockid[i]);	//出库单据详细
				boolean statusflg = false;
				
				//判断状态 0:开单；2-分配  7:发货确认 8:作废
				if(outboundinvoice.getOutstatus().equals("0")){
					statusflg = false;
					
				}else if(outboundinvoice.getOutstatus().equals("2")) //分配后可不可以作废，分配后作业都已作废
                {
                    //分配状态, 作业都已作废，是可以作废单据的。
                    int iCount = jobDao.noCancelJob(outboundinvoice.getOutstockid(), null);
                    if(iCount >0){ //不能作废
                        statusflg = true;
                    }else{ //可以作废
                        statusflg = false;
                    }
                }else{
					statusflg = true;
				}
				
				if(statusflg){
					
					if(strBackMsg.equals("Y")){
						strBackMsg = "不能作废非开单状态的单据！";
						
		 			}else {
		 				strBackMsg += "\r不能作废非开单状态的单据！";
		 			}
				}else{
					
					if(outboundinvoice.getSaleno() !=null || outboundinvoice.getSaleno() != ""){
						List<SaleFormDetail> SaleFormDetaills = new ArrayList<SaleFormDetail>();
						List<OutboundInvoiceDetail> lsinvoiceD = getOutBoundDetailsById(outboundinvoice.getOutstockid());
						OutboundInvoiceDetail invoiceD = null;
						IOutboundSoBus soBus = new OutboundSoBusImpl(outBoundDAO.getEntityDAO());
						if(lsinvoiceD != null && lsinvoiceD.size() > 0){
							for (int j = 0; j < lsinvoiceD.size(); j++) {
								invoiceD =  lsinvoiceD.get(j);
								SaleFormDetail saD = soBus.getSoDeByDId(invoiceD.getSodetailid());
								saD.setM_iOutNum(saD.getM_iOutNum()- invoiceD.getInvoicenum());
								SaleFormDetaills.add(saD);
							}
						}
						outboundinvoice.setOutstatus("8");
						outBoundDAO.CancelInvoice(outboundinvoice, SaleFormDetaills);
					}else{
						outboundinvoice.setOutstatus("8");
						outBoundDAO.updateOutBound(outboundinvoice);
					}
					
					
					Logger.info("用户【" + strUserCode + "】在单据管理模块里作废了出库单据：" + outstockid[i]);
				}
			}
		}catch (Exception e) {
		
			e.printStackTrace();
			throw new Exception("作废出库单据时候出错:" + e.getMessage());
		}
		
		return strBackMsg;
	}
/*	*//**
	 * 功能：获得出库单明细
	 * @param jobDelList  作业明细集合
	 * @return
	 * @throws Exception
	 *//*
	public List<OutboundInvoiceDetail> getOutBoundDelByJobDelList(
			List jobDelList) throws Exception {
		List<OutboundInvoiceDetail> outBoundLs = new ArrayList<OutboundInvoiceDetail>();
		String outBoundId = null;	//出库单ID
		try {
			if (jobDelList!=null && jobDelList.size()>0) {
				for (int i = 0; i < jobDelList.size(); i++) {
					outBoundId = ((InoutJobdetail) jobDelList.get(i)).getInvoiceid();
					outBoundLs  = outBoundDAO.getOutBoundDetailsById(outBoundId);
				}
				return outBoundLs;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("OutBoundBusImpl.getOutBoundDelByJobDelList:根据作业明细集合货区出库单详细出错:" + e.getMessage());
		}		
		return null;
	}*/
	 /**
     * 功能：根据出库单ID判断是否有已完成作业（删除出库单据使用）
     * @param id 出库单id
     * @return
     * @throws Exception
     */
	public boolean isFinishJobByOutIdAndCId(String id)throws Exception {
		List<InoutJob> ls = null;
		try {
			ls = outBoundDAO.getFinishJobByIdCid(id);
			if (ls!=null&&ls.size()>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("OutBoundBusImpl.isFinishJobByOutIdAndCId:根据出库单ID判断是否有已完成作业出错:" + e.getMessage());
		}
		return false;
	}
	public List<OutboundInvoiceDetail> getOutBoundDelByJobDelList(
			List jobDelList) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 功能：暂存直接出库时，修改的内容
	 * @param job
	 * @param jobDetail
	 * @param inventory
	 * @throws Exception
	 */
	public String updateInventoryAndJob(String jobID, String jobDetailID,String inventoryID) throws Exception{
		
		return outBoundDAO.updateInventoryAndJob(jobID, jobDetailID, inventoryID);
	}
	
    /**
     * 功能：增加出库单
     * @author fanll 2015-3-8
     * @param invoice
     * @param outboundInvoiceDetails
     * @return
     * @throws Exception
     */
    public String addOutBoundls(OutboundInvoiceHeader outInvoice,List<OutboundInvoiceDetail> outboundInvoiceDetails,List<SaleFormDetail> SaleFormDetaills) throws Exception{
    	return outBoundDAO.addOutBound(outInvoice, outboundInvoiceDetails, SaleFormDetaills);
    }
    
    /**
     * 功能：取消出库单
     * @author fanll 2015-3-8
     * @param outInvoice
     * @param SaleFormDetaills
     * @return
     * @throws Exception
     */
    public String CancelInvoice(OutboundInvoiceHeader outInvoice,List<SaleFormDetail> SaleFormDetaills) throws Exception{
    	return outBoundDAO.CancelInvoice(outInvoice, SaleFormDetaills);
    }
}
