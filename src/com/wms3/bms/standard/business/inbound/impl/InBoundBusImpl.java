package com.wms3.bms.standard.business.inbound.impl;

import java.util.ArrayList;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.inbound.IInBoundBus;
import com.wms3.bms.standard.dao.base.impl.BaseSeqDaoImpl;
import com.wms3.bms.standard.dao.inbound.IInboundDao;
import com.wms3.bms.standard.dao.inbound.impl.InboundDaoImpl;
import com.wms3.bms.standard.entity.base.BaseLotSet;
import com.wms3.bms.standard.entity.base.BaseSeq;
import com.wms3.bms.standard.entity.inbound.InboundInvoiceDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * 
 * 描述: 入库单管理业务类
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public class InBoundBusImpl implements IInBoundBus {
	
    /** 收货单DAO类  */
    protected IInboundDao inboundDao;
    
    public InBoundBusImpl(EntityDAO dao) {
    	inboundDao = new InboundDaoImpl(dao);
	}

    /**
	 * 功能:查询新建入库单的作业集
	 * @param warehouseid	仓库
	 * @param whAreaId		库区
	 * @param indate		日期
	 * @param shiftid		班次
	 * @param owner_id		货主
	 * @param isinvoice		是否开单
	 * @param lsLot 		新建入库单对应的批次属性设置集
	 * @return 
	 * @throws Exception
	 */
	public List getInboundJobs(String warehouseid, String whAreaId, String indate, String shiftid, String owner_id, String isinvoice, 
			List<BaseLotSet> lsLot) throws Exception {
		
		String strSql = "";
		String strSqlGrp = "";
		List list = null;
		
		try {
			strSql = "select jobdetail.productid, jobdetail.m_strProductName, job.jobtype, job.jobtypeName, " +
					"jobdetail.ownerId, jobdetail.m_strOwnerName, jobdetail.sinvoiceid, " +
					"sum(jobdetail.volume), sum(jobdetail.weight), sum(jobdetail.netweight), sum(jobdetail.jobnum), " +
					"sum(case when job.status ='4' then jobdetail.volume else 0 end), " +
					"sum(case when job.status ='4' then jobdetail.weight else 0 end), " +
					"sum(case when job.status ='4' then jobdetail.netweight else 0 end), " +
					"sum(case when job.status ='4' then jobdetail.jobnum else 0 end), " +
					"sum(case when jobdetail.isinvoice='N' then jobdetail.volume else 0 end), " +
					"sum(case when jobdetail.isinvoice='N' then jobdetail.weight else 0 end), " +
					"sum(case when jobdetail.isinvoice='N' then jobdetail.netweight else 0 end), " +
					"sum(case when jobdetail.isinvoice='N' then jobdetail.jobnum else 0 end)," +
					"jobdetail.packid, jobdetail.punit, jobdetail.lotid, job.invoicetype ";
			
			strSqlGrp = " group by jobdetail.productid, job.jobtype, jobdetail.ownerId, job.inOutType, jobdetail.sinvoiceid, " +
					"jobdetail.packid, jobdetail.punit, jobdetail.lotid, job.invoicetype ";
			
			//批次属性动态部分
			String strAttr = "";    
			BaseLotSet lotSet = null;
			if(lsLot != null){
				for(int k = 0; k < lsLot.size(); k++){
					lotSet = (BaseLotSet)lsLot.get(k);  
					strAttr += "jobdetail." + lotSet.getLotid() + ", ";
				}
			}
			if(strAttr.length() > 0){
				strSql += ", " + strAttr.substring(0, strAttr.length()-2);
				strSqlGrp += ", " + strAttr.substring(0, strAttr.length()-2);
			}
			
			strSql += getJobCondition(warehouseid, whAreaId, indate, shiftid, owner_id, isinvoice);	//where条件
			strSql += strSqlGrp + " order by job.jobtype, jobdetail.ownerId, jobdetail.sinvoiceid, jobdetail.productid ";
			list = inboundDao.querySQL(strSql);
			
		} catch(Exception er) {
			er.printStackTrace();
			throw new Exception("入库管理->新建入库单查询作业集出错：" + er.getMessage());
		}
		return list;
	}

	/**
	 * 功能:新建入库单->查询明细
	 * @param warehouseid	仓库
	 * @param whAreaId		库区
	 * @param indate		日期
	 * @param shiftid		班次
	 * @param isinvoice		是否开单
	 * @param strKey		明细key
	 * @param lsLot 		新建入库单对应的批次属性设置集 
	 * @return 
	 * @throws Exception
	 */
	public List getInboundJobDetails(String warehouseid, String whAreaId, String indate, String shiftid, String isinvoice, String strKey, 
			List<BaseLotSet> lsLot) throws Exception {
		
		List list = null;
		try {
			
			String strSql = getJobCreateInvoice(warehouseid, whAreaId, indate, shiftid, isinvoice, strKey, lsLot);
			strSql += " order by job.jobid, jobdetail.jobdetailid ";
			list = inboundDao.querySQL(strSql);
			
		} catch(Exception er) {
			er.printStackTrace();
			throw new Exception("入库管理->新建入库单->查询明细出错：" + er.getMessage());
		}
		return list;
	}

	/**
	 * 功能:新建入库单->查询明细作业sql文
	 * @param warehouseid	仓库
	 * @param whAreaId		库区
	 * @param indate		日期
	 * @param shiftid		班次
	 * @param isinvoice		是否开单
	 * @param strKey		明细key
	 * @param lsLot 		新建入库单对应的批次属性设置集 
	 * @return 
	 * @throws Exception
	 */
	private String getJobCreateInvoice(String warehouseid, String whAreaId, String indate, String shiftid, String isinvoice, String strKey, 
			List<BaseLotSet> lsLot) {
		
		String tempstr = "";
		
		tempstr = getJobCondition(warehouseid, whAreaId, indate, "", shiftid, isinvoice);	//where条件(货主明细里有)
		
		//批次属性动态部分
		String[] aem = strKey.split("\\|");
		
		
		if(aem[0].length() == 0){		//品名
			tempstr += " and (jobdetail.productid='" + aem[0] + "' or jobdetail.productid is null)";
		}else{
			tempstr += " and jobdetail.productid='" + aem[0] + "'";
		}	
		
		if(aem[1].length() == 0){		//作业类型
			tempstr += " and (job.jobtype='" + aem[1] + "' or job.jobtype is null)";
		}else{
			tempstr += " and job.jobtype='" + aem[1] + "'";
		}

		if(aem[2].length() == 0){		//货主
			tempstr += " and (jobdetail.ownerId='" + aem[2] + "' or jobdetail.ownerId is null)";
		}else{
			tempstr += " and jobdetail.ownerId='" + aem[2] + "'";
		}
		
		if(aem[3].length() == 0){		//来源单据号
			tempstr += " and (jobdetail.sinvoiceid='" + aem[3] + "' or jobdetail.sinvoiceid is null)";
		}else{
			tempstr += " and jobdetail.sinvoiceid='" + aem[3] + "'";
		}
		
		if(aem[4].length() == 0){		//包装ID
			tempstr += " and (jobdetail.packid='" + aem[4] + "' or jobdetail.packid is null)";
		}else{
			tempstr += " and jobdetail.packid='" + aem[4] + "'";
		}
		
		if(aem[5].length() == 0){		//单位
			tempstr += " and (jobdetail.punit='" + aem[5] + "' or jobdetail.punit is null)";
		}else{
			tempstr += " and jobdetail.punit='" + aem[5] + "'";
		}
		
		if(aem[6].length() == 0){		//批次类型ID
			tempstr += " and (jobdetail.lotid='" + aem[6] + "' or jobdetail.lotid is null)";
		}else{
			tempstr += " and jobdetail.lotid='" + aem[6] + "'";
		}
		
		if(aem[7].length() == 0){		//作业来源
			tempstr += " and (job.invoicetype='" + aem[7] + "' or job.invoicetype is null)";
		}else{
			tempstr += " and job.invoicetype='" + aem[7] + "'";
		}
		
		String lotvalue = "";
		BaseLotSet lotSet = null;
		if(lsLot != null){
			for(int k = 0; k < lsLot.size(); k++){
				lotSet = (BaseLotSet)lsLot.get(k);  
				lotvalue = aem[8+k];			//动态批次属性值
				if(lotvalue.length() == 0){
					tempstr += " and (jobdetail." + lotSet.getLotid() + "='" + lotvalue + "' or jobdetail." + lotSet.getLotid() + " is null)";
				}else{
					tempstr += " and jobdetail." + lotSet.getLotid() + "='" + lotvalue + "' ";
				}
			}
		}
		
		return tempstr;
	}
	
	/**
	 * 功能:返回新建入库单查询作业时候的查询条件
	 * @param warehouseid	仓库
	 * @param whAreaId		库区
	 * @param indate		日期
	 * @param shiftid		班次
	 * @param owner_id		货主
	 * @param isinvoice		是否开单
	 * @return 
	 * @throws Exception
	 */
	private String getJobCondition(String warehouseid, String whAreaId, String indate, String shiftid, String owner_id, String isinvoice) {
		
		String tmpstr = " from InoutJob as job, InoutJobdetail as jobdetail " +
				  " where job.jobid=jobdetail.jobid and job.inOutType='1' and job.isaccount='Y' and job.status!='5' ";
		
		//仓库
		if(warehouseid != null && warehouseid.trim().length() > 0){
			tmpstr += " and job.warehouseid='" + warehouseid + "'";
		}
		
		//库区
		if(whAreaId != null && whAreaId.trim().length() > 0){
			tmpstr += " and job.tcargoWhareaId='" + whAreaId + "'";
		}

		//日期
		if(indate != null && indate.trim().length() > 0){
			tmpstr += " and substring(job.createtime,1,10)='" + indate + "'";
		}
		
		//班次
		if(shiftid != null && shiftid.trim().length() > 0){
			tmpstr += " and job.shiftid='" + shiftid + "'";
		}
		
		//货主
		if(owner_id != null && owner_id.trim().length() > 0){
			tmpstr += " and jobdetail.ownerId='" + owner_id + "'";
		}
		
		//是否开单
		if(isinvoice != null && isinvoice.trim().length() > 0){
			tmpstr += " and jobdetail.isinvoice='" + isinvoice + "'";
		}
		
		return tmpstr;
	}
	/**
	 * 功能：根据订单详细id获得订单
	 * @author yao 2015-3-10
	 * @param poid
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> getJobInfoByPodetailid(String instockdetailid) throws Exception{
		List<Object[]> ls = null;
		if (instockdetailid != null && instockdetailid.length() > 0) {
			String hql = " from InoutJob ta,InoutJobdetail tb where ta.jobid=tb.jobid  and ta.boundstockdetailid='"+instockdetailid+"' ";
			ls = inboundDao.getEntityDAO().searchEntities(hql);
		}
		return ls;
	}
	/**
	 * 功能:新建入库单
	 * @param warehouseid	仓库
	 * @param whAreaId		库区
	 * @param indate		日期
	 * @param shiftid		班次
	 * @param strKey		明细key的集合
	 * @param lsLot 		新建入库单对应的批次属性设置集 
	 * @param strUserCode 	用户id  
	 * @return 
	 * @throws Exception
	 */
	public String addInboundInvoice(String warehouseid, String whAreaId, String indate, String shiftid, String strKey, 
			List<BaseLotSet> lsLot, String strUserCode) throws Exception {
		
		String strBackMsg = "Y";
		
		//入库单生成任务锁(不能同时生成入库单据，每次只能一个进程生成入库单据)
		synchronized (newin_lock) {
			
			InboundHeader inInvoice = null;	//入库单
			InboundDetail inDetail = null;	//入库单详细
			InoutJobdetail jobDetail = null; 		//出入库作业详细
			
			String strInvoiceNo = "";		//入库单据号
			String strtempInvoiceNo = "";	//保存上一张入库单据号
			String jobtype = "";      		//作业类型
			String ownerId = "";			//货主
			String sinvoiceid = "";			//来源单据号
	        String productid = "";        	//品名
			String strJobtype = "";			//作业类型(保存已经生成过单据的作业类型)
			String strOwnerId = "";			//货主(保存已经生成过单据的货主)
			String strSinvoiceid = "";		//来源单据号(保存已经生成过单据的来源单据号)
			
	    	double fNum = 0;				//数量
	        double fVolum = 0;				//体积
	        double fWeight = 0;				//重量
	        double fNetweight = 0;			//净重
			
			int iline = 0;					//保存单据详细的数量
			String jobdetailids = "";		//保存需要生成单据的job明细id
			List<InboundDetail> lsinDetail = new ArrayList();	//单据明细的列表
			
			String[] keys = strKey.split(",");
			String[] aem = null;
			List ls = null;
			String strSql = null;
			Object[] obj = null;
			
			
			try {
				for(int i = 0; i < keys.length; i++){
					aem = keys[i].split("\\|");
					
					productid = aem[0];        	//品名
				    jobtype = aem[1];      		//作业类型
					ownerId = aem[2];			//货主
					sinvoiceid = aem[3];		//来源单据号
		        	fNum = 0;					//数量
		        	fVolum = 0;					//体积
	            	fWeight = 0;				//重量
	            	fNetweight = 0;				//净重
				    
				    synchronized (productid) { //产品锁定，防止多人操作同一条数据
				    	
				        //获得对应的作业明细
				    	strSql = getJobCreateInvoice(warehouseid, whAreaId, indate, shiftid, "", keys[i], lsLot);
				    	strSql += " and job.status ='4' and jobdetail.isinvoice='N'" +
				    			" order by job.jobid, jobdetail.jobdetailid ";
				    	ls = inboundDao.querySQL(strSql);
				    	
				        if(ls != null && ls.size() > 0) {
				        	
				        	//验证是否已生成单据过的作业,当作业类型或货主或来源单据号发生变化时生成新单据
				            if(i==0 || !strJobtype.equals(jobtype) || !strOwnerId.equals(ownerId) || !strSinvoiceid.equals(sinvoiceid)){
				            
				            	strJobtype = jobtype;
				            	strOwnerId = ownerId;
				            	strSinvoiceid = sinvoiceid;
				                
				            	iline = 0;
								jobdetailids = "";
				                lsinDetail = new ArrayList();
				            	
				                //入库单号
				                BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(inboundDao.getEntityDAO());
				                BaseSeq  seqEn = seqDao.getSeqByType("RKD");
				                strInvoiceNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), inboundDao.getEntityDAO()); 
				                //入库单
				                inInvoice = new InboundHeader();
				                inInvoice.setInstockid(strInvoiceNo);  		//入库单编号
				                inInvoice.setWarehouseid(warehouseid);		//仓库编号
				                inInvoice.setInvoicedate(StrTools.getCurrDateTime(8));    //单据日期
				                inInvoice.setCreatetime(StrTools.getCurrDateTime(2));    //单据生成时间
				                inInvoice.setCreatemanid(strUserCode);    	//单据生成人员编号
				                inInvoice.setInstatus("0");       	//单据状态 0-新建，1-审核，2-确认 5-作废 
				                inInvoice.setInvoicetypeid(jobtype);       //入库类型
				                inInvoice.setUploadflag("1");     	//上传标志0-已上传（默认）1-未上传
				                

				            }
	                        
				          	//生成单据明细
				    		for(int j = 0; j < ls.size(); j++){
				    			
					        	obj = (Object[])ls.get(j);
								jobDetail = (InoutJobdetail)obj[1];
								jobdetailids += "'" + jobDetail.getJobdetailid() + "',";		//明细单据里对应的jobdetailid
								
								//统计作业明细的数量（和画面上的数量有可能不一样，在画面打开的时候，又有新的作业生成）
								fNum += jobDetail.getJobnum();			//数量
								fVolum += jobDetail.getVolume();		//体积
	            				fWeight += jobDetail.getWeight();		//重量
	            				fNetweight += jobDetail.getNetweight();	//净重
							}
				    			
				            inDetail = new InboundDetail();
				            iline = iline + 1; //生成详细次数
				            
				            inDetail.setInstockdetailid(strInvoiceNo + SeqTool.getCode(iline, 2));	//入库单详细ID
				            inDetail.setInstockid(strInvoiceNo);	//入库单据编号
				            inDetail.setProductid(productid);		//品名规格
				            inDetail.setPunit(aem[5]);				//单位
				            inDetail.setInnum(fNum);				//数量（上架）
				            inDetail.setLotid(aem[6]);				//批次类型ID
				            
				    		lsinDetail.add(inDetail);
				    		jobdetailids += "|";
				    		
				    		if(i==keys.length-1 || (!strtempInvoiceNo.equals("") && !strtempInvoiceNo.equals(strInvoiceNo))){

				    			strtempInvoiceNo = strInvoiceNo;
				    			
				            	//保存上一张入库单和明细,并且修改作业为已生成单据
				            	if(inInvoice!=null ){
				            		
				            		inboundDao.createInvoice(inInvoice, lsinDetail, jobdetailids);
				            		Logger.info("用户【" + strUserCode + "】生成了入库单，单据号：" + strInvoiceNo);
				            	}
				    		}
				         
				        }else{
				        
				            if(strBackMsg.equals("Y")){
				                strBackMsg = "选项【" + (i+1) + "】无生产单据的作业数据！";
				            }else{
				                strBackMsg += "\\r选项【" + (i+1) + "】无生产单据的作业数据！";
				            }
				        }
				    }
				}
			} catch (Exception e) {
				e.printStackTrace();
				strBackMsg = "生成单据时候发生错误！";
				return strBackMsg;
			}
		}
		
		return strBackMsg;
	}

	/**
	 * 功能:入库单管理->查询入库单列表的sql文
	 * @param warehouseid	仓库
	 * @param whAreaId		库区
	 * @param indate_from	日期
	 * @param indate_to		日期
	 * @param shiftid		班次
	 * @param owner_id		货主
	 * @param instatus		单据状态
	 * @param intype		入库类型
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getInbounds(String warehouseid, String whAreaId, String indate_from, String indate_to, String shiftid, String owner_id, 
			String instatus, String intype, String strUrl, int maxLine) throws Exception {
		
		PagingTool pt = null;
		
		try {
			String strSql = " from InboundHeader as inbound where 1=1 ";
			
			//仓库
			if(warehouseid != null && warehouseid.trim().length() > 0){
				strSql += " and inbound.warehouseid='" + warehouseid + "'";
			}
			
			//库区
			if(whAreaId != null && whAreaId.trim().length() > 0){
				strSql += " and inbound.tcargoWhareaId='" + whAreaId + "'";
			}
	
			//日期
			if(indate_from != null && indate_from.trim().length() > 0){
				strSql += " and inbound.invoicedate>='" + indate_from + "'";
			}
			if(indate_to != null && indate_to.trim().length() > 0){
				strSql += " and inbound.invoicedate<='" + indate_to + "'";
			}
			
			//班次
			if(shiftid != null && shiftid.trim().length() > 0){
				strSql += " and inbound.shiftid='" + shiftid + "'";
			}
			
			//货主
			if(owner_id != null && owner_id.trim().length() > 0){
				strSql += " and inbound.ownerId='" + owner_id + "'";
			}
			
			//单据状态
			if(instatus != null && instatus.trim().length() > 0){
				strSql += " and inbound.instatus='" + instatus + "'";
			}
		
			//入库类型
			if(intype != null && intype.trim().length() > 0){
				strSql += " and inbound.intype='" + intype + "'";
			}
			
			String strHQL = strSql + " order by inbound.instockid";
			String strCountHQL = "select count(*)" + strSql;
			
			pt = CommonPagination.getPagingTool(inboundDao.getEntityDAO(), strCountHQL ,strHQL, strUrl, 1, maxLine);
			
		} catch (Exception er) {
			throw new Exception("根据条件查询查询入库单列表出错:" + er.getMessage());
		}
				
		return pt;
		
	}

	/**
	 * 功能:入库单管理->查询入库单详细列表
	 * @param instockid	入库单据编号
	 * @return 
	 * @throws Exception
	 */
	public List getInboundDetails(String instockid) throws Exception {
		
		String strSql = "";
		List list = null;
		
		try {
			strSql = " from InboundDetail as inboundDetail where inboundDetail.instockid='" + instockid + "'";
			
			list = inboundDao.querySQL(strSql);
			
		} catch(Exception er) {
			er.printStackTrace();
			throw new Exception("入库管理->查询入库单详细列表时候出错：" + er.getMessage());
		}
			
		return list;
	}

	/**
	 * 功能:根据入库单据编号查询入库单
	 * @param instockid	入库单据编号
	 * @return 
	 * @throws Exception
	 */
	public InboundHeader getInboundInvoice(String instockid) throws Exception {
		
		return inboundDao.getInboundHeader(instockid);
	}

	/**
	 * 功能:更新入库单
	 * @param instockid	入库单据编号
	 * @return 
	 * @throws Exception
	 */
	public void updateInboundInvoice(InboundHeader inbound) throws Exception {
		
		inboundDao.updateInboundInvoice(inbound);
	}

	/**
	 * 功能:删除入库单
	 * @param instockid	入库单据编号
	 * @return 
	 * @throws Exception
	 */
	public void deleteInboundInvoice(String instockid) throws Exception {
		
		inboundDao.deleteInboundInvoice(instockid);
	}

	/**
	 * 功能:根据入库单据编号查询作业的总数量
	 * @param instockdetailid	入库单据详细编号
	 * @return 
	 * @throws Exception
	 */
	public int getJobNumSum(String instockdetailid) throws Exception {
		
		return inboundDao.getJobNumSum(instockdetailid);
		
	}

	/**
	 * 功能:作废单据
	 * @param instockid	入库单据编号
	 * @return 
	 * @throws Exception
	 */
	public void cancelInboundInvoice(String instockid) throws Exception {
		
		inboundDao.cancelInboundInvoice(instockid);
	}
	
	/**
	 * 功能:作废详细单据
	 * @param instockdetailid	入库单据详细编号
	 * @return 
	 * @throws Exception
	 */
	public void cancelInboundDetail(String instockdetailid) throws Exception {
		
		inboundDao.cancelInboundDetail(instockdetailid);
	}
	public String addHLRKJob(String inventoryId,String userCode,String getnum) throws Exception{
		return inboundDao.addHLRKJob(inventoryId,userCode,getnum);
	}
	public String addHLRKJobplus(String inventoryId,String Virtualwhid,String sjtraycode,String userCode,String getnum,String platoon,String column,String floor) throws Exception{
		return inboundDao.addHLRKJobplus(inventoryId,Virtualwhid,sjtraycode, userCode, getnum, platoon, column, floor);
	}
	
}
