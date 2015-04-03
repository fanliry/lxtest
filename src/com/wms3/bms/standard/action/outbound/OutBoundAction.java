package com.wms3.bms.standard.action.outbound;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.business.competenceMgr.UserMgr;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.ricosoft.common.tools.CommonTools;
import com.ricosoft.common.tools.StrTools;
import com.ricosoft.entity.competenceMgr.UserInfo;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.base.ICargoSpaceBus;
import com.wms3.bms.standard.business.base.IWhAreaBus;
import com.wms3.bms.standard.business.base.impl.CargoSpaceBusImpl;
import com.wms3.bms.standard.business.base.impl.WhAreaBusImpl;
import com.wms3.bms.standard.business.inventory.IInventoryBus;
import com.wms3.bms.standard.business.inventory.impl.InventoryBusImpl;
import com.wms3.bms.standard.business.job.IJobBus;
import com.wms3.bms.standard.business.job.impl.JobBusImpl;
import com.wms3.bms.standard.business.outbound.IOutBoundBus;
import com.wms3.bms.standard.business.outbound.IOutBoundJobBus;
import com.wms3.bms.standard.business.outbound.IOutboundSoBus;
import com.wms3.bms.standard.business.outbound.impl.OutboundSoBusImpl;
import com.wms3.bms.standard.business.outbound.impl.OutBoundBusImpl;
import com.wms3.bms.standard.business.outbound.impl.OutBoundJobBusImpl;
import com.wms3.bms.standard.business.scheduletasl.IScheduleTask;
import com.wms3.bms.standard.constant.StandardConstant;
import com.wms3.bms.standard.dao.base.IBaseCargoSpaceDao;
import com.wms3.bms.standard.dao.base.impl.BaseCargoSpaceDaoImpl;
import com.wms3.bms.standard.dao.base.impl.BaseSeqDaoImpl;
import com.wms3.bms.standard.dao.inventory.IInventoryDao;
import com.wms3.bms.standard.dao.inventory.impl.InventoryDaoImpl;
import com.wms3.bms.standard.dao.job.impl.JobDaoImpl;
import com.wms3.bms.standard.dao.job.impl.TaskDaoImpl;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BaseLotSet;
import com.wms3.bms.standard.entity.base.BaseSeq;
import com.wms3.bms.standard.entity.base.BaseWharea;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundPoDetail;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundPoHeader;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.outerInterface.SaleForm;
import com.wms3.bms.standard.entity.outerInterface.SaleFormDetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

/** 
 * 描述: 出库单管理
 * @author yao 2015-3-8
 * @since WMS 3.0
 */
public class OutBoundAction extends AbstractAction{
    protected IOutBoundBus outboundBus;
    private IJobBus JobBus;
    public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
    {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        outboundBus = new OutBoundBusImpl(dao);
        JobBus = new JobBusImpl(dao);
        try
        {
            String flag = CommonTools.getStrToGb2312(request.getParameter("flag"));
            String outstatus = CommonTools.getStrToGbk(request.getParameter("outstatus"));      //单据状态
            String outtype = CommonTools.getStrToGbk(request.getParameter("outtype"));          //出库类型
            String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));  //仓库ID
            String shiftid = CommonTools.getStrToGbk(request.getParameter("shiftid"));          //作业班次
            String screatedate = CommonTools.getStrToGbk(request.getParameter("start_time"));   //单据开始生成时间
            String ecreatedate = CommonTools.getStrToGbk(request.getParameter("end_time"));     //单据结束生成时间
            String customerid = CommonTools.getStrToGbk(request.getParameter("customerid"));    //客户
            String outno = CommonTools.getStrToGbk(request.getParameter("outno"));              //出库单号
            String jobid = CommonTools.getStrToGbk(request.getParameter("jobid"));              //作业id
            
            
            String detailid = CommonTools.getStrToGbk(request.getParameter("detailid")); 		//出库单详细id
            String Virtualwhid = CommonTools.getStrToGbk(request.getParameter("Virtualwhid"));  //逻辑库区
            String productid = CommonTools.getStrToGbk(request.getParameter("productid"));    	//产品id
            String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));    		//批号
            String printdate = CommonTools.getStrToGbk(request.getParameter("printdate"));    	//生产日期
            String trayCode = CommonTools.getStrToGbk(request.getParameter("trayCode"));    	//托盘
            
           
            
            
            String strUserCode = (String)httpsession.getAttribute("userCode");
            String errortextString ="",back_msg="";
            
            //每页显示行数
            int iline = 5;
            String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));
            if(linerow != null && linerow.length()>0){
                iline = Integer.parseInt(linerow);
            }
            
            //出库单ID
            String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
            //出库单详细ID
            String invoicedetailid = CommonTools.getStrToGbk(request.getParameter("invoicedetailid"));
            
            if(flag != null && flag.trim().equals("1")){//新建出库->出库单查询
                
                String strQueryHQL = outboundBus.getOutBoundQuerySQL(warehouseid, outstatus, outtype, shiftid, screatedate, ecreatedate, customerid, outno);
                String strCountHQL = outboundBus.getOutBoundCountSQL(warehouseid, outstatus, outtype, shiftid, screatedate, ecreatedate, customerid, outno);
                
                //跳转页面
                strUrl = "/standard/jsp/outbound/newckd/outbound_newckd_list.jsp";    
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//查询结果
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("paging", pt);
                
            }else if(flag != null && flag.trim().equals("2")){ //新建出库->根据单据ID获得单据详细列表
                //出库单详细
                List<OutboundInvoiceDetail> lsDetail = outboundBus.getOutBoundDetailsById(invoiceid);
                
                strUrl = "/standard/jsp/outbound/newckd/outbound_newckd_dlist.jsp";
                request.setAttribute("invoicedetail", lsDetail);    
            }else if(flag != null && flag.trim().equals("3")){ //新建出库->修改出库单
                //
                strUrl = "/standard/jsp/outbound/newckd/outbound_newckd_update.jsp";
                OutboundInvoiceHeader outBound = outboundBus.getOutBoundById(invoiceid);
                request.setAttribute("invoice", outBound); 
            }else if(flag != null && flag.trim().equals("4")){ //新建出库->明细->查询出库单详细
                //出库单详细
                List<OutboundInvoiceDetail> lsDetail = outboundBus.getOutBoundDetailsById(invoiceid);
                
                strUrl = "/standard/jsp/outbound/newckd/outbound_newckd_detail_list.jsp";
                request.setAttribute("invoicedetail", lsDetail);    
            }else if(flag != null && flag.trim().equals("5")){//新建出库->分配
                OutboundInvoiceDetail invoiceDetail = outboundBus.getOutBoundDetailById(invoicedetailid);
                OutboundInvoiceHeader outBound = null;
                if(invoiceDetail != null){ 
                    outBound = outboundBus.getOutBoundById(invoiceDetail.getOutstockid());
                }          
                strUrl = "/standard/jsp/outbound/assgin/outbound_assgin.jsp";
                request.setAttribute("invoicedetail", invoiceDetail);
                request.setAttribute("invoice", outBound);    
                
            }else if(flag != null && flag.trim().equals("lotinfopick")){//出库备货->按批号拣货出库
            	
                String isRF = CommonTools.getStrToGb2312(request.getParameter("isRF"));
                if("rf".equals(isRF)){
                	strUrl = "/rf/lxyy/RFlotinfopick/lotinfopick_assgin_list.jsp";
                }else{
                    strUrl = "/standard/jsp/outbound/lotinfopick/lotinfopick_assgin_list.jsp";
                }
                
            	List lsList = outboundBus.getOutBoundProudctInfo();        

                request.setAttribute("exResultList", lsList);    
                
            }else if(flag != null && flag.trim().equals("lotinfopickzc")){//出库备货->按批号拣货出库->暂存列表
            	
                String isRF = CommonTools.getStrToGb2312(request.getParameter("isRF"));
                if("rf".equals(isRF)){
                	strUrl = "/rf/lxyy/RFlotinfopick/zc_kc_list.jsp";
                }else{
                    strUrl = "/standard/jsp/outbound/lotinfopick/zc_kc_list.jsp";
                }
            	
            	IInventoryBus inventoryBus = new InventoryBusImpl(dao);
            	//List lsList = inventoryBus.getZCInventoryByCKInfo(warehouseid, "", "", "", "", "", ""); //暂时用 以后用下面的语句
            	List lsList = inventoryBus.getZCInventoryByCKInfo(warehouseid, "", Virtualwhid, productid, lotinfo, printdate, trayCode);     

                request.setAttribute("exResultList", lsList);    
                        
            }else if(flag != null && flag.trim().equals("6")){//出库管理->出库单查询
            	
            	String strQueryHQL = outboundBus.getOutBoundQuerySQL(warehouseid, outstatus, outtype, shiftid, screatedate, ecreatedate, customerid, outno);
                String strCountHQL = outboundBus.getOutBoundCountSQL(warehouseid, outstatus, outtype, shiftid, screatedate, ecreatedate, customerid, outno);
                //跳转页面
                strUrl = "/standard/jsp/outbound/ckdmgr/outbound_ckmgr_list.jsp";    
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
  
                List ls = pt.getLsResult();//查询结果
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("paging", pt);
                
            }else if(flag != null && flag.trim().equals("7")){ //出库管理->根据单据ID获得单据详细列表
                
                List<OutboundInvoiceDetail> lsDetail = outboundBus.getOutBoundDetailsById(invoiceid);	//出库单详细
                
                strUrl = "/standard/jsp/outbound/ckdmgr/outbound_ckmgr_detail.jsp";
                request.setAttribute("exResultList", lsDetail);    
                
            }else if(flag != null && flag.trim().equals("8")){
            	strUrl = "/standard/jsp/outbound/ckdmx/outbound_ckdDetail_list.jsp";
            	List lsDetail = JobBus.getJobDetailByInvoiceid(invoiceid);	//出库单作业及详细
            	
            	
                request.setAttribute("exResultList", lsDetail); 
            	
            	
            }else if(flag != null && flag.trim().equals("11")){ //发货确认->根据单据号查询单据详细
                OutboundInvoiceHeader outBound = outboundBus.getOutBoundById(invoiceid);
                List<OutboundInvoiceDetail> lsDetail = outboundBus.getOutBoundDetailsById(invoiceid);
                //跳转页面
                strUrl = "/standard/jsp/outbound/fhqr/outbound_fhqr_list.jsp";
                request.setAttribute("exResultList", lsDetail);
                request.setAttribute("outinvoice", outBound);
            }else if(flag != null && flag.trim().equals("12")){ //发货确认->B客户根据单据号查询单据详细
                OutboundInvoiceHeader outBound = outboundBus.getOutBoundById(invoiceid);
                List<OutboundInvoiceDetail> lsDetail = outboundBus.getOutBoundDetailsById(invoiceid);
                //跳转页面
                strUrl = "/standard/jsp/outbound/fhqr/outbound_fhqr_adjust_B_list.jsp";
                request.setAttribute("exResultList", lsDetail);
                request.setAttribute("outinvoice", outBound);
            }else if(flag != null && flag.trim().equals("13")){//质检管理->抽检单查询
            	
            	String strQueryHQL = outboundBus.getOutBoundQuerySQL(warehouseid, outstatus, outtype, shiftid, screatedate, ecreatedate, customerid, outno);
                String strCountHQL = outboundBus.getOutBoundCountSQL(warehouseid, outstatus, outtype, shiftid, screatedate, ecreatedate, customerid, outno);              
                //跳转页面
                strUrl = "/standard/jsp/quality/qualitymgr/quality_qualitymgr_list.jsp";    
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
  
                List ls = pt.getLsResult();//查询结果
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("paging", pt);
                
            }else if(flag != null && flag.trim().equals("fh")){ //Rf 复核
            	errortextString = "RF==>复核==>复核失败:";
				strUrl = "/rf/lxyy/checkup.jsp"; //立体库作业
				InoutJob job = new InoutJob().getJobByJobid(jobid, dao);
				IWhAreaBus whAreaBus = new WhAreaBusImpl(dao);  
				BaseWharea zc = whAreaBus.getZCgetWhAreaByWhid(warehouseid,job.getScargoWhareaId());
				InoutJobdetail jobdetail = new InoutJobdetail().getJobDetailByJobid(jobid,dao);
				if(job!=null && jobdetail!=null){
					String jobidinfo  = job.getJobid().intern();
					synchronized (jobidinfo) {
						if(zc != null){//暂存不能为空
							if(!"Y".equals(jobdetail.getIsreview())){
								jobdetail.setIsreview("Y");
								jobdetail.setReviewnum(jobdetail.getJobnum());
								jobdetail.setReviewTime(StrTools.getCurrDateTime(2));
								jobdetail.setReviewid(strUserCode);
								InventoryBusImpl inventbus = new InventoryBusImpl(dao);
								InventoryStorage Inventory = inventbus.getInventoryQueryZC(job.getWarehouseid(), zc.getwhAreaId(), jobdetail.getProductid(), job.getTraycode());
								if(Inventory!=null){ //累加上去
									Inventory.setPnum(Inventory.getPnum()+(jobdetail.getJobnum()-jobdetail.getAssignnum()));
									jobdetail.setJobnum(jobdetail.getAssignnum());
									job.fhupdateJob(jobdetail,Inventory,dao);
								}else{
									double jobnum= jobdetail.getJobnum();
									jobdetail.setJobnum(jobdetail.getAssignnum());
									InventoryStorage inventory = new InventoryStorage();
									if(jobnum-jobdetail.getAssignnum()>0){
										
										ICargoSpaceBus cargoSpaceBus = new CargoSpaceBusImpl(dao);
										BaseCargospace zcspace = cargoSpaceBus.getCargoSpaceBywhAreaId(zc.getwhAreaId());
										//添加库存
										inventory.setCargoSpaceId(zcspace.getCargoSpaceId());
										inventory.setWhAreaId(zcspace.getWhAreaId());
										inventory.setWarehouseid(zcspace.getWarehouseid());
										inventory.setProductid(jobdetail.getProductid());
										inventory.setProductdate(jobdetail.getPrintdate());
										inventory.setIndate(StrTools.getCurrDateTime(2));
										inventory.setLotid(jobdetail.getLotid());
										inventory.setLotinfo(jobdetail.getLotinfo());
										inventory.setLotinfo2(jobdetail.getLotinfo2());
										inventory.setSupplier(jobdetail.getSupplier());
										inventory.setPackid(jobdetail.getPackid());
										inventory.setProductstatus(jobdetail.getProductStatus());//物品状态
										inventory.setPunit(jobdetail.getPunit());
										inventory.setPnum(jobnum-jobdetail.getAssignnum());
										inventory.setAssignnum(0);
										inventory.setInjobid(job.getJobid());
										inventory.setTraycode(job.getTraycode());
										inventory.setInstockid(job.getBoundstockid());
										inventory.setProductcode(jobdetail.getProductcode());
										inventory.setStatus("0");
									}else{
										inventory = null;
									}
									job.fhJobz(jobdetail, inventory, null, dao);
								}
								back_msg = "Y";
							}else{
								back_msg = "该作业已经复合，不能再次复合";
							}
							
						}else{
							back_msg = "暂存不存在，请查阅暂存信息是否存在";
						}
					}
				}else{
					back_msg = "没有找到相应的作业及作业明细";
				}
            }
            request.setAttribute("back_msg", back_msg);
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("出库管理==>新建出库单==>出库查询失败:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * 功能：按批号拣货出库
     * @author yao 2015-3-12 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String pickZcProduct(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        
        String result = CommonTools.getStrToGbk(request.getParameter("result"));    	//暂存分配明细： 库存id&分配数量,库存id&分配数量
        String detailid = CommonTools.getStrToGbk(request.getParameter("detailid"));    //出库详细id
        
        //当前用户
        String strUserCode = (String)request.getSession().getAttribute("userCode");
        outboundBus = new OutBoundBusImpl(dao);
        IInventoryDao inventoryDao = new InventoryDaoImpl(dao);
        BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(dao);
        try
        {
            String strBackMsg = "Y";
            List<InventoryStorage> lsInventoryStorages = new ArrayList<InventoryStorage>(); 
            List<InoutJob> lsInoutJobs = new ArrayList<InoutJob>(); 
            List<InoutJobdetail> lsInoutJobdetails = new ArrayList<InoutJobdetail>(); 
            InventoryStorage storage = null;
            InoutJob job = null;
            InoutJobdetail jobdetail = null;
            OutboundInvoiceDetail outboundInvoiceDetail = null;
            OutboundInvoiceHeader outboundInvoiceHeader = null;
            boolean flag = true;
            if(result!=null){
            	 outboundInvoiceDetail = outboundBus.getOutBoundDetailById(detailid);
            	 outboundInvoiceHeader = outboundBus.getOutBoundById(outboundInvoiceDetail.getOutstockid());
            	 String[] kcinfos = result.split(",");
            	 if(kcinfos!=null && kcinfos.length>0){
            		 outboundInvoiceDetail.setLinestatus("2");
            		 for (int i = 0; i < kcinfos.length; i++) {
            			 String[]  kcinfo = kcinfos[i].split("\\|"); //kcinfo[0]库存id  kcinfo[1]分配数量
            			 storage = inventoryDao.getInventoryById(kcinfo[0]);
            			 if(storage.getPnum()-Double.parseDouble(kcinfo[1])>=0){
            				storage.setPnum(storage.getPnum()-Double.parseDouble(kcinfo[1]));
            				lsInventoryStorages.add(storage);

 				            BaseSeq  seqEn = seqDao.getSeqByType("RKZY");
 				            String strNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(),dao); 
            				job = new InoutJob();
            				jobdetail = new InoutJobdetail();
 							job.setJobid(strNo);
 							job.setJobtype(outboundInvoiceHeader.getOuttype());
 							job.setOnLineType("2");
 							job.setIsaccount("Y");
 							job.setStatus("4"); //完成状态
 							job.setVirtualwhid(outboundInvoiceDetail.getVirtualwhid());//逻辑库区id
 							job.setPriority(1);//优先级
 							job.setCreatetime(StrTools.getCurrDateTime(2));
 							job.setInOutType("2");//出库类型
 							job.setIsinvoice("Y");
 							job.setTraycode(storage.getTraycode());
 							job.setBoundstockid(outboundInvoiceDetail.getOutstockid());
 							job.setBoundstockdetailid(outboundInvoiceDetail.getOutstockdetailid());
 							job.setRoute("1");//路线 直入
 							job.setOpManId(strUserCode);
 							job.setJobcategory("2"); // 暂存区作业
 							job.setInvoicetype("2"); // 1:先有作业后生成单据;2:先有单据后生成作业
 							job.setWarehouseid(storage.getWarehouseid());
							job.setScargoSpaceId(storage.getCargoSpaceId());
							job.setScargoWhareaId(storage.getWhAreaId());
							job.setScargoAlleyId(storage.getCargoAlleyId());
				            
							jobdetail.setJobid(strNo);
							jobdetail.setProductid(storage.getProductid());
							jobdetail.setLotinfo(storage.getLotid());		//进厂编号
							jobdetail.setLotinfo2(storage.getLotinfo2());	//原厂编号
							jobdetail.setPunit(storage.getPunit());
							jobdetail.setJobnum(Double.parseDouble(kcinfo[1]));
							jobdetail.setAssignnum(Double.parseDouble(kcinfo[1]));
							jobdetail.setPrintdate(storage.getProductdate());
							jobdetail.setSupplier(storage.getSupplier());
							jobdetail.setIsinvoice("Y");
							jobdetail.setProductStatus(storage.getProductstatus());//物品状态
							jobdetail.setProductcode(storage.getProductcode());
							jobdetail.setIsreview("Y");

							
							lsInoutJobs.add(job);
							lsInoutJobdetails.add(jobdetail);
							Logger.error("用户[" + strUserCode + "]，入库管理==>RF生成作业成功:jobid=[" +job.getJobid() +"]");
            			 }else{
            				 strBackMsg = "暂存分配数量不能大于暂存数量";
            				 flag = false;
            				 break;
            			 }
					}
            	 }
            }
            if(flag){
            	Session session = null;
        		Transaction tx = null;
        		try {

        			session = dao.getSession();
        			tx = session.beginTransaction();
                    //增加作业
                    job = null;
                    for(int i = 0; i < lsInoutJobs.size(); i++){
                        job = lsInoutJobs.get(i);
                        session.save(job);
                    } 
                    
                    //增加作业详细
                    jobdetail = null;
                    for(int i = 0; i < lsInoutJobdetails.size(); i++){
                    	jobdetail = lsInoutJobdetails.get(i);
                        session.save(jobdetail);
                    } 
                    
                    //增加作业详细
                    storage = null;
                    for(int i = 0; i < lsInventoryStorages.size(); i++){
                    	storage = lsInventoryStorages.get(i);
                    	if(storage.getPnum()<=0){
                    		session.delete(storage);
                    	}else{
                    		session.update(storage);
                    	}
                    } 
                    if(outboundInvoiceDetail!=null){
                    	session.update(outboundInvoiceDetail);
                    }
                    tx.commit();    
                }catch(Exception er){
                    if(tx != null){
                        tx.rollback();
                    }
                    throw new  Exception("生成出库作业出错："+er.getMessage());
                }finally{
                    /*if(session != null)
                    {
                        session.close();
                    }*/
                    dao.closeSession(session);
                }  
            }
            List lsList = outboundBus.getOutBoundProudctInfo();        
            strUrl = "/standard/jsp/outbound/lotinfopick/lotinfopick_assgin_list.jsp"; 
            //返回值
            request.setAttribute("back_msg", strBackMsg);  
            request.setAttribute("exResultList", lsList);  
            request.getRequestDispatcher(strUrl).forward(request, response);   
        }catch(Exception er)
        {
            Logger.error("用户["+strUserCode+"]：出库备货==>按批号拣货出库出错:" + er.getMessage());
            request.setAttribute("errormsg", "用户["+strUserCode+"]：错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }       
        return null;
    }
    /**
     * 功能：增加出库单
     * @author hug 2012-3-8 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String addRicoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        
        String strOutType = CommonTools.getStrToGbk(request.getParameter("out_type"));          //出库类型 
        String strWarehouseId = CommonTools.getStrToGbk(request.getParameter("warehouseid"));   //仓库
        String strVehiclepos = CommonTools.getStrToGbk(request.getParameter("vehiclepos"));     //车位
        String strVehicleno = CommonTools.getStrToGbk(request.getParameter("vehicleno"));       //车牌号
        String strSendplatform = CommonTools.getStrToGbk(request.getParameter("sendplatform")); //发货月台
        String strSetposition = CommonTools.getStrToGbk(request.getParameter("setposition"));   //集货位
        String strShiftid = CommonTools.getStrToGbk(request.getParameter("shiftid"));           //班次
        
        String strOnLineType = CommonTools.getStrToGbk(request.getParameter("on_line_type")); //出库模式
        String strRequestdate = CommonTools.getStrToGbk(request.getParameter("requestdate")); //要求发货时间
        String strExpectdate = CommonTools.getStrToGbk(request.getParameter("expectdate"));   //预期发货时间
        String strSaleno = CommonTools.getStrToGbk(request.getParameter("saleno"));           //销售单号
            
        String strOutDate = CommonTools.getStrToGbk(request.getParameter("formdate"));        //单据日期
        String strDepartId = CommonTools.getStrToGbk(request.getParameter("departid"));       //部门
        
        String ownerid = CommonTools.getStrToGbk(request.getParameter("owner_id"));         // 货主
        String customer_id = CommonTools.getStrToGbk(request.getParameter("customer_id"));  // 客户
        
        String strAddress = CommonTools.getStrToGbk(request.getParameter("address"));        //发货地址
        
        //当前用户
        String strUserCode = (String)request.getSession().getAttribute("userCode");
        
        outboundBus = new OutBoundBusImpl(dao);
        try
        {
            String strBackMsg = "Y";
    
            //出库单
            OutboundInvoiceHeader outInvoice = new OutboundInvoiceHeader();
            outInvoice.setOuttype(strOutType);         // 出库类型
            outInvoice.setDepartid(strDepartId);       // 部门id
            outInvoice.setWarehouseid(strWarehouseId); // 仓库id
            outInvoice.setCreatetime(StrTools.getCurrDateTime(2)); // 出库单生成时间
            outInvoice.setFormdate(strOutDate);                     // 单据日期
            outInvoice.setOutpos("2");                 // 出库点
            outInvoice.setVehicleno(strVehicleno);     // 车牌
            outInvoice.setVehiclepos(strVehiclepos);   // 车位
            outInvoice.setOutstatus("0");              // 单据状态 0.出库开单 
            outInvoice.setCreatemanid(strUserCode);    // 创建人
            outInvoice.setOpmanid(strUserCode);        // 操作员
            outInvoice.setShiftid(strShiftid);                 // 作业班次
            outInvoice.setIsupload("N");               // 是否上传成功 Y.是 N.否 默认为否
            outInvoice.setOnLineType(strOnLineType);   // 联机模式 1.联机 2.脱机
            outInvoice.setSaleno(strSaleno);           // 销售单号  
            outInvoice.setSendplatform(strSendplatform);  //发货月台
            outInvoice.setSetposition(strSetposition);    //集货位
            outInvoice.setShipmentstarttime(strRequestdate);//要求发货时间
            outInvoice.setShipmentendtime(strExpectdate);   //预期发货时间
            outInvoice.setOwnerid(ownerid);         //货主
            outInvoice.setCustomerid(customer_id);  //客户
            outInvoice.setAddress(strAddress); //客户 收货人地址
            //增加出库单
            String strID = outboundBus.addOutBound(outInvoice);
            //跳转页面
            strUrl = "/standard/jsp/outbound/newckd/outbound_newckd_list.jsp";
            
            //返回值
            request.setAttribute("back_msg", strBackMsg);                
            request.setAttribute("InvoicedId", strID);
            request.getRequestDispatcher(strUrl).forward(request, response);   
            
        }catch(Exception er)
        {
            Logger.error("用户["+strUserCode+"]：出库管理==>新建出库单据出错:" + er.getMessage());
            request.setAttribute("errormsg", "用户["+strUserCode+"]：错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }       
        return null;
    }
    /**
     * 功能：修改出库单
     * @author hug 2012-3-13 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String updateRicoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        HttpSession httpsession = request.getSession(false);
        //String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // 仓库ID
        String strUserCode = (String)request.getSession().getAttribute("userCode");           //当前用户
        
        //单据ID
        String strInvoiceId = CommonTools.getStrToGbk(request.getParameter("invoiceid")); 
         
        String strOutType = CommonTools.getStrToGbk(request.getParameter("out_type"));       //出库类型
        //当前仓库
        //String strWarehouseId = CommonTools.getStrToGbk(request.getParameter("warehouseid"));
        
        String strVehiclepos = CommonTools.getStrToGbk(request.getParameter("vehiclepos"));  //车位
        String strVehicleno = CommonTools.getStrToGbk(request.getParameter("vehicleno"));    //车牌号
        String strSendplatform = CommonTools.getStrToGbk(request.getParameter("sendplatform")); //发货月台
        String strSetposition = CommonTools.getStrToGbk(request.getParameter("setposition"));   //集货位
        
        String strOnLineType = CommonTools.getStrToGbk(request.getParameter("on_line_type"));//出库模式
        String strRequestdate = CommonTools.getStrToGbk(request.getParameter("requestdate")); //要求发货时间
        String strExpectdate = CommonTools.getStrToGbk(request.getParameter("expectdate")); //预期发货时间
        String strSaleno = CommonTools.getStrToGbk(request.getParameter("saleno"));          //销售单号
            
        String strOutDate = CommonTools.getStrToGbk(request.getParameter("formdate"));       //单据日期
        String strDepartId = CommonTools.getStrToGbk(request.getParameter("departid")); //部门

        String ownerid = CommonTools.getStrToGbk(request.getParameter("owner_id"));         // 货主
        String customer_id = CommonTools.getStrToGbk(request.getParameter("customer_id"));  // 客户 即收货人
        String strAddress = CommonTools.getStrToGbk(request.getParameter("address"));       // 收货人地址
        outboundBus = new OutBoundBusImpl(dao);
        String strMsg = "Y";
        try
        {
            //要修改出库单的内容 
            OutboundInvoiceHeader outInvoice = outboundBus.getOutBoundById(strInvoiceId);
            outInvoice.setOuttype(strOutType);         // 出库类型
            outInvoice.setDepartid(strDepartId);       // 部门id
            //outInvoice.setWarehouseid(strWarehouseId); // 仓库id
            outInvoice.setFormdate(strOutDate);        // 单据日期
            outInvoice.setVehicleno(strVehicleno);     // 车牌
            outInvoice.setVehiclepos(strVehiclepos);   // 车位
            outInvoice.setOpmanid(strUserCode);        // 操作员
            //outInvoice.setShiftid(strShiftid);       // 作业班次 
            outInvoice.setOnLineType(strOnLineType);   // 联机模式 1.联机 2.脱机
            outInvoice.setSaleno(strSaleno);           // 销售单号  
            outInvoice.setSendplatform(strSendplatform);    //发货月台
            outInvoice.setSetposition(strSetposition);      //集货位
            outInvoice.setShipmentstarttime(strRequestdate);//要求发货时间
            outInvoice.setShipmentendtime(strExpectdate);   //预期发货时间
            outInvoice.setOwnerid(ownerid);         //货主
            outInvoice.setCustomerid(customer_id);  //客户
            outInvoice.setAddress(strAddress);//收货人地址
            
            strMsg = outboundBus.updateOutBound(outInvoice); 

            
            //刷新 
            PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
            List ls = null;
            String strUrl = null;
            if(pt != null)
            {
                ls = CommonPagination.getPagingList(dao, pt);
                
                strUrl = pt.getM_strUrl();
            }
            if(strUrl == null)
            {
                //跳转页面
                strUrl = "/standard/jsp/outbound/newckd/outbound_newckd_list.jsp";
            }
            request.setAttribute("exResultList", ls);
            request.setAttribute("pagingTool", pt);
            httpsession.setAttribute("paging", pt);
           
            
            
            //返回值
            request.setAttribute("back_msg", strMsg);//返回操作结果
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("用户["+strUserCode+"]：出库管理==>修改出库单据出错:" + er.getMessage());
            request.setAttribute("errormsg", "用户["+strUserCode+"]：错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    
    /**
     * 功能：增加出库单详细
     * @author hug 2012-3-13 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String addDetailRicoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        
        String strUserCode = (String)request.getSession().getAttribute("userCode");           //当前用户
        //出库单据ID
        String strInvoiceId = CommonTools.getStrToGbk(request.getParameter("invoiceid")); 
        String strProductId = CommonTools.getStrToGbk(request.getParameter("productid"));    //品名规格
        String printdate = CommonTools.getStrToGbk(request.getParameter("printdate"));       //生产日期  
        
        
        String strEunit = CommonTools.getStrToGbk(request.getParameter("eunit"));            //单位
        String strInvoiceNum = CommonTools.getStrToGbk(request.getParameter("invoicenum"));  //出库数量
        String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));            //批号
        String Virtualwhid = CommonTools.getStrToGbk(request.getParameter("Virtualwhid"));    //逻辑库区
        
        outboundBus = new OutBoundBusImpl(dao);
        String strMsg = "";
        try
        {
            //出库单详细
            OutboundInvoiceDetail outBoundDetail = new OutboundInvoiceDetail();
            outBoundDetail.setOutstockid(strInvoiceId); //收货单编号
            outBoundDetail.setLinestatus("0");   //单据行状态0-开单
            outBoundDetail.setProductid(strProductId);   //品名
            outBoundDetail.setPkgunit(strEunit);
            outBoundDetail.setInvoicenum(Double.parseDouble(strInvoiceNum)); //数量
            outBoundDetail.setLotinfo(lotinfo);   //批号信息
            outBoundDetail.setPrintdate(printdate); //生产日期
            outBoundDetail.setVirtualwhid(Virtualwhid); //逻辑库区
            strMsg = outboundBus.addOutBoundDetail(outBoundDetail, strInvoiceId);
            //返回结果
            //出库单详细
            List<OutboundInvoiceDetail> lsDetail = outboundBus.getOutBoundDetailsById(strInvoiceId); 
            strUrl = "/standard/jsp/outbound/newckd/outbound_newckd_detail_list.jsp";
            request.setAttribute("invoicedetail", lsDetail);  
            //返回值
            request.setAttribute("back_msg", strMsg);//返回操作结果
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("用户["+strUserCode+"]：出库管理==>新增出库单详细出错:" + er.getMessage());
            request.setAttribute("errormsg", "用户["+strUserCode+"]：错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * 功能：修改出库单详细
     * @author hug 2012-3-13 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String updateDetailRicoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        
        String strUserCode = (String)request.getSession().getAttribute("userCode");           //当前用户
        //出库单详细ID
        String strInvoiceDetailId = CommonTools.getStrToGbk(request.getParameter("detailid")); 
        
        String strProductId = CommonTools.getStrToGbk(request.getParameter("productid"));    //品名规格
        
        String strEunit = CommonTools.getStrToGbk(request.getParameter("eunit"));            //单位
        String strInvoiceNum = CommonTools.getStrToGbk(request.getParameter("invoicenum"));  //出库数量
        //String strVolume = CommonTools.getStrToGbk(request.getParameter("volume"));          //体积
        
        String productcode = CommonTools.getStrToGbk(request.getParameter("productcode"));       //产品编码 
        String spec = CommonTools.getStrToGbk(request.getParameter("spec"));      				 //规格 
        
        String printdate = CommonTools.getStrToGbk(request.getParameter("printdate"));       //生产日期  
        String strWeight = CommonTools.getStrToGbk(request.getParameter("weight"));          //重量
        
//        String strNetweight = CommonTools.getStrToGbk(request.getParameter("netweight"));    //净重
//        String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));            //批号
            
        outboundBus = new OutBoundBusImpl(dao);
        String strMsg = "";
        try
        {
            //出库单详细
            OutboundInvoiceDetail outBoundDetail = outboundBus.getOutBoundDetailById(strInvoiceDetailId); 
            outBoundDetail.setProductid(strProductId);   //品名
            outBoundDetail.setPkgunit(strEunit);
            outBoundDetail.setInvoicenum(Double.parseDouble(strInvoiceNum)); //数量
            //outBoundDetail.setVolume(Double.parseDouble(strVolume));         //体积
           // outBoundDetail.setWeight(Double.parseDouble(strWeight));         //重量
//            outBoundDetail.setNetweight(Double.parseDouble(strNetweight));   //净重
//            outBoundDetail.setLotinfo(lotinfo);   //批号信息
           // outBoundDetail.setPrintdate(printdate); //生产日期
            strMsg = outboundBus.updateOutBoundDetail(outBoundDetail, outBoundDetail.getOutstockid());
            
            //返回结果
            //出库单详细
            List<OutboundInvoiceDetail> lsDetail = outboundBus.getOutBoundDetailsById(outBoundDetail.getOutstockid()); 
            strUrl = "/standard/jsp/outbound/newckd/outbound_newckd_detail_list.jsp";
            request.setAttribute("invoicedetail", lsDetail); 
           
            //返回值
            request.setAttribute("back_msg", strMsg);//返回操作结果
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("用户["+strUserCode+"]：出库管理==>修改出库单详细出错:" + er.getMessage());
            request.setAttribute("errormsg", "用户["+strUserCode+"]：错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    
    /**
     * 功能：删除出库单据或出库单据详细
     * @author hug 2012-3-13 
     * @param hsSysParam
     * @param hsCurrentParam
     * @return
     * @throws Exception
     */
    public String deleteRicoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        
        String strUserCode = (String)request.getSession().getAttribute("userCode"); //当前用户
        //操作类型 1：删除单据 2：删除单据详细
        String strFlag = CommonTools.getStrToGbk(request.getParameter("flag"));
        //单据ID列表
        String strInvoiceIds = CommonTools.getStrToGbk(request.getParameter("invoiceids")); 
        
        //单据ID
        String strInvoiceId = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
        //单据详细ID (删除单据时不需要)列表
        String strInvoiceDetailIds = CommonTools.getStrToGbk(request.getParameter("detailids"));

        outboundBus = new OutBoundBusImpl(dao);
        String strMsg = "";
        try
        {
            List<OutboundInvoiceDetail> lsDetail = null;
            if(strFlag != null && strFlag.trim().equals("1")){ //1：删除单据)
                if(strInvoiceIds != null)
                {   
                    String strIds[] = strInvoiceIds.split(",");    
                    for(int i = 0; i < strIds.length; i++)
                    {
                        //单据ID
                        String strInvoice = strIds[i];
                        strMsg = strMsg + outboundBus.deleteOutBoundInvoice(strInvoice, null, strFlag);
                    }
                }
                //跳转页面
                strUrl = "/standard/jsp/outbound/newckd/outbound_newckd_list.jsp";
                
            }else if(strFlag != null && strFlag.trim().equals("2")){ //2：删除单据详细
                if(strInvoiceDetailIds != null)
                {   
                    String strIds[] = strInvoiceDetailIds.split(",");    
                    for(int i = 0; i < strIds.length; i++)
                    {
                        //单据详细
                        String strInvoiceDetailId = strIds[i];
                        strMsg = strMsg + outboundBus.deleteOutBoundInvoice(strInvoiceId, strInvoiceDetailId, strFlag);
                    }
                }
                lsDetail = outboundBus.getOutBoundDetailsById(strInvoiceId);
                //跳转页面
                strUrl = "/standard/jsp/outbound/newckd/outbound_newckd_detail_list.jsp";
            }

            //返回值
            request.setAttribute("back_msg", strMsg);//返回操作结果
            request.setAttribute("invoicedetail", lsDetail);
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("用户["+strUserCode+"]：出库管理==>删除出库单或单详细(" + strFlag + ")出错:" + er.getMessage());
            request.setAttribute("errormsg", "用户["+strUserCode+"]：错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    
    
    /**
	 * 功能:发货查询
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecSendSearch(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//仓库
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//库区
		String senddate_from = CommonTools.getStrToGbk(request.getParameter("senddate_from"));	//发货日期
		String senddate_to = CommonTools.getStrToGbk(request.getParameter("senddate_to"));		//发货日期
		String shiftid = CommonTools.getStrToGbk(request.getParameter("shift_id"));			//班次 
		
		String productid = CommonTools.getStrToGbk(request.getParameter("productid"));		//品名
		String customerid = CommonTools.getStrToGbk(request.getParameter("customerid"));	//客户
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));		//托盘条码
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));              //批次ID
		String jobid = CommonTools.getStrToGbk(request.getParameter("jobid"));				//作业号
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//每页显示行数
		int maxLine = 20;
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		
        outboundBus = new OutBoundBusImpl(dao);
        IOutBoundJobBus outboundjobBus = new OutBoundJobBusImpl(dao);
		try{
			
			if(flag!=null && flag.equals("1") || flag.equals("2")){		
				
				String[] strSqls = outboundBus.getOutboundSend(warehouseid, whAreaId, senddate_from, senddate_to, shiftid, productid, customerid, traycode, 
						lotinfo);
				
				if(flag.equals("1")){//发货查询列表
					strUrl = "/standard/jsp/outbound/send/outbound_send_search_list.jsp";
					
					PagingTool pt = CommonPagination.getPagingTool(dao, strSqls[0], strSqls[1], strUrl, 1, maxLine);
					
					List ls = pt.getLsResult();
					request.setAttribute("exResultList", ls);
					httpsession.setAttribute("paging", pt);
					
				}else if(flag.equals("2")){		//发货查询 报表
					
					strUrl = "/standard/jsp/outbound/send/outbound_send_search_report.jsp";
					
					List ls = dao.searchEntities(strSqls[1]);
					
					request.setAttribute("exResultList", ls);
					
				}
			}else if(flag!=null && flag.equals("3")){		//发货查询 作业明细列表
				
				strUrl = "/standard/jsp/outbound/send/outbound_send_search_detail.jsp";
				
				List ls = outboundjobBus.getJobDetails(jobid);
				request.setAttribute("exResultList", ls);
			}
			

			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("出库管理==>发货查询==>发货查询失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:作废出库单据
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecCancel(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String ids = CommonTools.getStrToGbk(request.getParameter("ids"));		//单据IDS
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		outboundBus = new OutBoundBusImpl(dao);
		try{
			
			String strBackMsg = outboundBus.cancelInvoices(ids, strUserCode);
		
			strUrl = "/standard/jsp/outbound/ckdmgr/outbound_ckmgr_list.jsp";	
			
			    
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
			
			Logger.error("用户["+strUserCode+"]，出库管理==>单据管理==>作废出库单据失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}
	
	/**
	 * 功能:出库单管理->单据打印
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecReportCKD(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String outstockid = CommonTools.getStrToGbk(request.getParameter("outstockid"));	//出库单据编号
		
		outboundBus = new OutBoundBusImpl(dao);
		try{
			
			List<BaseLotSet> lsLot = null;	//批次属性集
			HashMap<String, List> hsLot = (HashMap<String, List>)hsSysParam.get("viewLot");	//所有要显示的批次
			if(hsLot != null){
				lsLot = hsLot.get("newckd");		//新建出库单->显示出库单详细	
			}
			OutboundInvoiceHeader outbound = outboundBus.getOutBoundById(outstockid);	//出库单
			List lsDetail = outboundBus.getOutBoundDetailsById(outstockid);				//出库单详细
            
            strUrl = "/standard/jsp/outbound/ckdmgr/outbound_ckmgr_cpprint.jsp";
            request.setAttribute("lsLot", lsLot);
            request.setAttribute("outstockid", outstockid);
            request.setAttribute("invoice", outbound);
            request.setAttribute("invoicedetail", lsDetail); 
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("出库管理==>单据管理==>出库单打印失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:出库单管理->单据打印
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecReportCKDMX(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String outstockid = CommonTools.getStrToGbk(request.getParameter("outstockid"));	//出库单据编号
		
		outboundBus = new OutBoundBusImpl(dao);
		JobBus = new JobBusImpl(dao);
		try{
			
			OutboundInvoiceHeader outbound = outboundBus.getOutBoundById(outstockid);	//出库单
			//List lsDetail = outboundBus.getOutBoundDetailsById(outstockid);				//出库单详细
			List ls = JobBus.getJobDetailByInvoiceid(outstockid);
            
            strUrl = "/standard/jsp/outbound/ckdmgr/outbound_ckmgr_djmxprint.jsp";
            //request.setAttribute("invoice", outbound);
            request.setAttribute("invoice", outbound);
            request.setAttribute("invoicedetail", ls); 
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("出库管理==>单据管理==>出库单打印失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:出库单管理->查询
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecSearchRkd(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		try
        {
			outboundBus = new OutBoundBusImpl(dao);
            String flag = CommonTools.getStrToGb2312(request.getParameter("flag"));
    		String strUserCode = (String)httpsession.getAttribute("userCode");
    		
            //每页显示行数
            int iline = 5;
            String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));
            if(linerow != null && linerow.length()>0){
                iline = Integer.parseInt(linerow);
            }
            String outstockdetailid = CommonTools.getStrToGbk(request.getParameter("outstockdetailid"));//出库单明细
            if(flag != null && flag.trim().equals("1")){
    				strUrl = "/standard/jsp/outbound/ckdmgr/outbound_ckmgr_job_detail.jsp";
    				OutboundInvoiceDetail detil= null;
    				if(outstockdetailid!=null && outstockdetailid.length()>1){
    					detil = outboundBus.getOutBoundDetailById(outstockdetailid);
    				}
    				request.setAttribute("exdetil", detil);
            }
            
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("出库管理==>单据管理==>单据作废:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        
		return null;
	}
	
	/**
	 * 功能：增加出库单
	 * 
	 * @author yao 2012-3-29
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String addRkd(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
        HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
        //HttpSession httpsession = request.getSession(false);
        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
        
        String warehouseid = (String) hsSysParam.get("c_warhouse_id");  //仓库 
        String strFormType = CommonTools.getStrToGbk(request.getParameter("form_type"));        //单据类型 
        String strownerid = CommonTools.getStrToGbk(request.getParameter("owner_id"));             // 货主
        String strcustomerid = CommonTools.getStrToGbk(request.getParameter("customer_id"));  // 客户
        String straddress = CommonTools.getStrToGbk(request.getParameter("address"));   //收货地址
        String strvehicleno = CommonTools.getStrToGbk(request.getParameter("vehicleno"));  //车牌号
        String strvehiclepos = CommonTools.getStrToGbk(request.getParameter("vehiclepos"));  //车位
        String sign= CommonTools.getStrToGbk(request.getParameter("sign"));
        String strdepartid = CommonTools.getStrToGbk(request.getParameter("departid"));  //部门
        String strsendplatform = CommonTools.getStrToGbk(request.getParameter("sendplatform")); //发货月台
        String strsetposition = CommonTools.getStrToGbk(request.getParameter("setposition"));  //集货位
        String strsaleno = CommonTools.getStrToGbk(request.getParameter("saleno"));  //销售单号
        String strformdate = CommonTools.getStrToGbk(request.getParameter("formdate")); //单据日期
        String strrequestdate = CommonTools.getStrToGbk(request.getParameter("requestdate"));  //要求发货时间
        String strexpectdate = CommonTools.getStrToGbk(request.getParameter("expectdate"));  //预期发货时间
        String strdetail = CommonTools.getStrToGbk(request.getParameter("detail"));  
        String strUserCode = (String)request.getSession().getAttribute("userCode");//当前用户
        outboundBus = new OutBoundBusImpl(dao);
        String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        if(flag != null && flag.trim().equals("Storage")){
        	flag = "product";
        }
        try
        {
        	if(flag != null && flag.trim().equals("1")){//新建出库->添加->详细列表
        		/*strUrl = "/standard/jsp/outbound/newckd/outbound_ckd_add_list.jsp";
				lsLot = AbstractBus.getLotset(hsSysParam, V_CKDDETAIL);
				request.setAttribute("lsLot", lsLot);*/
        	}else{//新建出库->添加->根据订单生成出库单
        		List<OutboundInvoiceDetail> outboundInvoiceDetails = new ArrayList<OutboundInvoiceDetail>();
        		List<SaleFormDetail> SaleFormDetaills = new ArrayList<SaleFormDetail>();
        		//SaleForm			//销售单
				//SaleFormDetail	//销售单详细
                String invoiceid="";
                String strBackMsg = "Y";
                HttpSession httpsession = request.getSession(false);
                int iline = 5;//每页显示行数
                String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));
                if(linerow != null && linerow.length()>0){
                    iline = Integer.parseInt(linerow);
                }

        		BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(dao);
        		IOutboundSoBus outBoundSoBus = new OutboundSoBusImpl(dao);
        		
                BaseSeq  seqEn = seqDao.getSeqByType("CKD");
                String strOutID = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), dao);
                
                OutboundInvoiceHeader outInvoice = new OutboundInvoiceHeader();//出库单
                outInvoice.setOutstockid(strOutID);
                outInvoice.setOuttype(strFormType);        // 出库单据类型
                outInvoice.setDepartid(strdepartid);       // 部门id
                outInvoice.setWarehouseid("001");    // 仓库id
                outInvoice.setCreatetime(StrTools.getCurrDateTime(2)); // 出库单生成时间
                outInvoice.setFormdate(strformdate);                    // 单据日期
                outInvoice.setOutpos("2");                 // 出库点
                outInvoice.setVehicleno(strvehicleno);     // 车牌
                outInvoice.setVehiclepos(strvehiclepos);   // 车位
                outInvoice.setOutstatus("0");              // 单据状态 0.出库开单 
                outInvoice.setCreatemanid(strUserCode);    // 创建人
                outInvoice.setOpmanid(strUserCode);        // 操作员
                outInvoice.setShiftid("");                 // 作业班次
                outInvoice.setIsupload("N");               // 是否上传成功 Y.是 N.否 默认为否
                outInvoice.setOnLineType("1");              // 联机模式 1.联机 2.脱机
                outInvoice.setSaleno(strsaleno);           // 销售单号  
                outInvoice.setSendplatform(strsendplatform);  //发货月台
                outInvoice.setSetposition(strsetposition);    //集货位
                outInvoice.setShipmentstarttime(strrequestdate);//要求发货时间
                outInvoice.setShipmentendtime(strexpectdate);   //预期发货时间
                outInvoice.setOwnerid(strownerid);         //货主
                outInvoice.setCustomerid(strcustomerid);  //客户
        		if(flag != null && flag.trim().equals("order")){
        			   synchronized (IOutBoundBus.newout_lock){
		                    if(strdetail!=null && !strdetail.equals("")){ //strdetail 模式detailid,num|detailid,num
		                    	String[] strdetail1= strdetail.split("\\|");
		                    	if(strdetail1!=null && strdetail1.length>0){
		                    		for(int i=0;i<strdetail1.length;i++){
		                    			String[] strdetail12= strdetail1[i].split(",");
		                    			if(strdetail12!=null && strdetail12.length==3){//添加单据详细  detailid,num,逻辑库区
		                    				SaleFormDetail sodetail = null;
		                    				sodetail = outBoundSoBus.getOutboundSoHeaders(null,strdetail12[0]);
		                    				
		                    				if(sodetail!=null){//出库订单为开单状态
		                    					OutboundInvoiceDetail outBoundDetail = new OutboundInvoiceDetail();
		                    					outBoundDetail.setOutstockdetailid(SeqTool.getDetailId(strOutID,(i+1)+"",StandardConstant.D_LEN));
		                        	            outBoundDetail.setOutstockid(strOutID); //出库单编号
		                        	            outBoundDetail.setLinestatus("0");   //单据行状态0-开单
		                        	            outBoundDetail.setProductid(sodetail.getM_strPackageId());   //品名
		                        	            outBoundDetail.setPkgunit(sodetail.getM_strUnit());
		                        	            outBoundDetail.setInvoicenum(Double.parseDouble(strdetail12[1])); //数量
		                        	            outBoundDetail.setVolume(0);         //体积
		                        	            outBoundDetail.setWeight(0);         //重量
		                        	            outBoundDetail.setNetweight(0);   //净重
		                        	            outBoundDetail.setPrice(0);//单价
		                        	            outBoundDetail.setSodetailid(strdetail12[0]);	//so详细
		                        	            outBoundDetail.setSoid(strsaleno);				//so
		                        	            
		                        	            outboundInvoiceDetails.add(outBoundDetail);
		                        	            double dnum = sodetail.getM_iOutNum()+Double.parseDouble(strdetail12[1]);
		                        	            sodetail.setM_iOutNum(dnum);
		                        	            SaleFormDetaills.add(sodetail);
		                        	            
		                    				}else{
		                    					strBackMsg = "有出库订单详细不为开单状态 不能进行再次开单 生成单据失败";
		                    					break;
		                    				}
		                    			}
		                    		}
		                    		if(outboundInvoiceDetails!=null && outboundInvoiceDetails.size()>0){
		                    			if(strBackMsg.equals("Y")){
		                    				outboundBus.addOutBoundls(outInvoice, outboundInvoiceDetails, SaleFormDetaills);
		                    				invoiceid=strOutID;
		                    			}
		                    		}else{
		                    			strBackMsg = "没有单据明细信息 生成单据失败";
		                    		}
		                    	}
		                    }
        			}
        		}else{
        			strBackMsg = "没有该生成该单据类型   生成单据失败";
        		}
        		String strQueryHQL = outboundBus.getOutBoundQuerySQL(warehouseid, "", "", "", "", "", "", invoiceid);
                String strCountHQL = outboundBus.getOutBoundCountSQL(warehouseid, "", "", "", "", "", "", invoiceid);
                strUrl = "/standard/jsp/outbound/newckd/outbound_newckd_list.jsp";//跳转页面  
                if(sign.equals("1")){
                	strUrl="/standard/jsp/outbound/newckd/outbound_ckd_add_list.jsp";
                }
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//查询结果
                request.setAttribute("back_msg", strBackMsg);   //返回值 
                request.setAttribute("InvoicedId", invoiceid);
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("paging", pt);
        	}
            request.getRequestDispatcher(strUrl).forward(request, response);        
        }catch(Exception er)
        {
            Logger.error("用户["+strUserCode+"]：出库管理==>新建出库单据出错:" + er.getMessage());
            request.setAttribute("errormsg", "用户["+strUserCode+"]：错误信息:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }       
        return null;
    }
	
}
