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
 * ����: ���ⵥ����
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
            String outstatus = CommonTools.getStrToGbk(request.getParameter("outstatus"));      //����״̬
            String outtype = CommonTools.getStrToGbk(request.getParameter("outtype"));          //��������
            String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));  //�ֿ�ID
            String shiftid = CommonTools.getStrToGbk(request.getParameter("shiftid"));          //��ҵ���
            String screatedate = CommonTools.getStrToGbk(request.getParameter("start_time"));   //���ݿ�ʼ����ʱ��
            String ecreatedate = CommonTools.getStrToGbk(request.getParameter("end_time"));     //���ݽ�������ʱ��
            String customerid = CommonTools.getStrToGbk(request.getParameter("customerid"));    //�ͻ�
            String outno = CommonTools.getStrToGbk(request.getParameter("outno"));              //���ⵥ��
            String jobid = CommonTools.getStrToGbk(request.getParameter("jobid"));              //��ҵid
            
            
            String detailid = CommonTools.getStrToGbk(request.getParameter("detailid")); 		//���ⵥ��ϸid
            String Virtualwhid = CommonTools.getStrToGbk(request.getParameter("Virtualwhid"));  //�߼�����
            String productid = CommonTools.getStrToGbk(request.getParameter("productid"));    	//��Ʒid
            String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));    		//����
            String printdate = CommonTools.getStrToGbk(request.getParameter("printdate"));    	//��������
            String trayCode = CommonTools.getStrToGbk(request.getParameter("trayCode"));    	//����
            
           
            
            
            String strUserCode = (String)httpsession.getAttribute("userCode");
            String errortextString ="",back_msg="";
            
            //ÿҳ��ʾ����
            int iline = 5;
            String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));
            if(linerow != null && linerow.length()>0){
                iline = Integer.parseInt(linerow);
            }
            
            //���ⵥID
            String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
            //���ⵥ��ϸID
            String invoicedetailid = CommonTools.getStrToGbk(request.getParameter("invoicedetailid"));
            
            if(flag != null && flag.trim().equals("1")){//�½�����->���ⵥ��ѯ
                
                String strQueryHQL = outboundBus.getOutBoundQuerySQL(warehouseid, outstatus, outtype, shiftid, screatedate, ecreatedate, customerid, outno);
                String strCountHQL = outboundBus.getOutBoundCountSQL(warehouseid, outstatus, outtype, shiftid, screatedate, ecreatedate, customerid, outno);
                
                //��תҳ��
                strUrl = "/standard/jsp/outbound/newckd/outbound_newckd_list.jsp";    
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//��ѯ���
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("paging", pt);
                
            }else if(flag != null && flag.trim().equals("2")){ //�½�����->���ݵ���ID��õ�����ϸ�б�
                //���ⵥ��ϸ
                List<OutboundInvoiceDetail> lsDetail = outboundBus.getOutBoundDetailsById(invoiceid);
                
                strUrl = "/standard/jsp/outbound/newckd/outbound_newckd_dlist.jsp";
                request.setAttribute("invoicedetail", lsDetail);    
            }else if(flag != null && flag.trim().equals("3")){ //�½�����->�޸ĳ��ⵥ
                //
                strUrl = "/standard/jsp/outbound/newckd/outbound_newckd_update.jsp";
                OutboundInvoiceHeader outBound = outboundBus.getOutBoundById(invoiceid);
                request.setAttribute("invoice", outBound); 
            }else if(flag != null && flag.trim().equals("4")){ //�½�����->��ϸ->��ѯ���ⵥ��ϸ
                //���ⵥ��ϸ
                List<OutboundInvoiceDetail> lsDetail = outboundBus.getOutBoundDetailsById(invoiceid);
                
                strUrl = "/standard/jsp/outbound/newckd/outbound_newckd_detail_list.jsp";
                request.setAttribute("invoicedetail", lsDetail);    
            }else if(flag != null && flag.trim().equals("5")){//�½�����->����
                OutboundInvoiceDetail invoiceDetail = outboundBus.getOutBoundDetailById(invoicedetailid);
                OutboundInvoiceHeader outBound = null;
                if(invoiceDetail != null){ 
                    outBound = outboundBus.getOutBoundById(invoiceDetail.getOutstockid());
                }          
                strUrl = "/standard/jsp/outbound/assgin/outbound_assgin.jsp";
                request.setAttribute("invoicedetail", invoiceDetail);
                request.setAttribute("invoice", outBound);    
                
            }else if(flag != null && flag.trim().equals("lotinfopick")){//���ⱸ��->�����ż������
            	
                String isRF = CommonTools.getStrToGb2312(request.getParameter("isRF"));
                if("rf".equals(isRF)){
                	strUrl = "/rf/lxyy/RFlotinfopick/lotinfopick_assgin_list.jsp";
                }else{
                    strUrl = "/standard/jsp/outbound/lotinfopick/lotinfopick_assgin_list.jsp";
                }
                
            	List lsList = outboundBus.getOutBoundProudctInfo();        

                request.setAttribute("exResultList", lsList);    
                
            }else if(flag != null && flag.trim().equals("lotinfopickzc")){//���ⱸ��->�����ż������->�ݴ��б�
            	
                String isRF = CommonTools.getStrToGb2312(request.getParameter("isRF"));
                if("rf".equals(isRF)){
                	strUrl = "/rf/lxyy/RFlotinfopick/zc_kc_list.jsp";
                }else{
                    strUrl = "/standard/jsp/outbound/lotinfopick/zc_kc_list.jsp";
                }
            	
            	IInventoryBus inventoryBus = new InventoryBusImpl(dao);
            	//List lsList = inventoryBus.getZCInventoryByCKInfo(warehouseid, "", "", "", "", "", ""); //��ʱ�� �Ժ�����������
            	List lsList = inventoryBus.getZCInventoryByCKInfo(warehouseid, "", Virtualwhid, productid, lotinfo, printdate, trayCode);     

                request.setAttribute("exResultList", lsList);    
                        
            }else if(flag != null && flag.trim().equals("6")){//�������->���ⵥ��ѯ
            	
            	String strQueryHQL = outboundBus.getOutBoundQuerySQL(warehouseid, outstatus, outtype, shiftid, screatedate, ecreatedate, customerid, outno);
                String strCountHQL = outboundBus.getOutBoundCountSQL(warehouseid, outstatus, outtype, shiftid, screatedate, ecreatedate, customerid, outno);
                //��תҳ��
                strUrl = "/standard/jsp/outbound/ckdmgr/outbound_ckmgr_list.jsp";    
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
  
                List ls = pt.getLsResult();//��ѯ���
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("paging", pt);
                
            }else if(flag != null && flag.trim().equals("7")){ //�������->���ݵ���ID��õ�����ϸ�б�
                
                List<OutboundInvoiceDetail> lsDetail = outboundBus.getOutBoundDetailsById(invoiceid);	//���ⵥ��ϸ
                
                strUrl = "/standard/jsp/outbound/ckdmgr/outbound_ckmgr_detail.jsp";
                request.setAttribute("exResultList", lsDetail);    
                
            }else if(flag != null && flag.trim().equals("8")){
            	strUrl = "/standard/jsp/outbound/ckdmx/outbound_ckdDetail_list.jsp";
            	List lsDetail = JobBus.getJobDetailByInvoiceid(invoiceid);	//���ⵥ��ҵ����ϸ
            	
            	
                request.setAttribute("exResultList", lsDetail); 
            	
            	
            }else if(flag != null && flag.trim().equals("11")){ //����ȷ��->���ݵ��ݺŲ�ѯ������ϸ
                OutboundInvoiceHeader outBound = outboundBus.getOutBoundById(invoiceid);
                List<OutboundInvoiceDetail> lsDetail = outboundBus.getOutBoundDetailsById(invoiceid);
                //��תҳ��
                strUrl = "/standard/jsp/outbound/fhqr/outbound_fhqr_list.jsp";
                request.setAttribute("exResultList", lsDetail);
                request.setAttribute("outinvoice", outBound);
            }else if(flag != null && flag.trim().equals("12")){ //����ȷ��->B�ͻ����ݵ��ݺŲ�ѯ������ϸ
                OutboundInvoiceHeader outBound = outboundBus.getOutBoundById(invoiceid);
                List<OutboundInvoiceDetail> lsDetail = outboundBus.getOutBoundDetailsById(invoiceid);
                //��תҳ��
                strUrl = "/standard/jsp/outbound/fhqr/outbound_fhqr_adjust_B_list.jsp";
                request.setAttribute("exResultList", lsDetail);
                request.setAttribute("outinvoice", outBound);
            }else if(flag != null && flag.trim().equals("13")){//�ʼ����->��쵥��ѯ
            	
            	String strQueryHQL = outboundBus.getOutBoundQuerySQL(warehouseid, outstatus, outtype, shiftid, screatedate, ecreatedate, customerid, outno);
                String strCountHQL = outboundBus.getOutBoundCountSQL(warehouseid, outstatus, outtype, shiftid, screatedate, ecreatedate, customerid, outno);              
                //��תҳ��
                strUrl = "/standard/jsp/quality/qualitymgr/quality_qualitymgr_list.jsp";    
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
  
                List ls = pt.getLsResult();//��ѯ���
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("paging", pt);
                
            }else if(flag != null && flag.trim().equals("fh")){ //Rf ����
            	errortextString = "RF==>����==>����ʧ��:";
				strUrl = "/rf/lxyy/checkup.jsp"; //�������ҵ
				InoutJob job = new InoutJob().getJobByJobid(jobid, dao);
				IWhAreaBus whAreaBus = new WhAreaBusImpl(dao);  
				BaseWharea zc = whAreaBus.getZCgetWhAreaByWhid(warehouseid,job.getScargoWhareaId());
				InoutJobdetail jobdetail = new InoutJobdetail().getJobDetailByJobid(jobid,dao);
				if(job!=null && jobdetail!=null){
					String jobidinfo  = job.getJobid().intern();
					synchronized (jobidinfo) {
						if(zc != null){//�ݴ治��Ϊ��
							if(!"Y".equals(jobdetail.getIsreview())){
								jobdetail.setIsreview("Y");
								jobdetail.setReviewnum(jobdetail.getJobnum());
								jobdetail.setReviewTime(StrTools.getCurrDateTime(2));
								jobdetail.setReviewid(strUserCode);
								InventoryBusImpl inventbus = new InventoryBusImpl(dao);
								InventoryStorage Inventory = inventbus.getInventoryQueryZC(job.getWarehouseid(), zc.getwhAreaId(), jobdetail.getProductid(), job.getTraycode());
								if(Inventory!=null){ //�ۼ���ȥ
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
										//��ӿ��
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
										inventory.setProductstatus(jobdetail.getProductStatus());//��Ʒ״̬
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
								back_msg = "����ҵ�Ѿ����ϣ������ٴθ���";
							}
							
						}else{
							back_msg = "�ݴ治���ڣ�������ݴ���Ϣ�Ƿ����";
						}
					}
				}else{
					back_msg = "û���ҵ���Ӧ����ҵ����ҵ��ϸ";
				}
            }
            request.setAttribute("back_msg", back_msg);
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("�������==>�½����ⵥ==>�����ѯʧ��:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * ���ܣ������ż������
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
        
        String result = CommonTools.getStrToGbk(request.getParameter("result"));    	//�ݴ������ϸ�� ���id&��������,���id&��������
        String detailid = CommonTools.getStrToGbk(request.getParameter("detailid"));    //������ϸid
        
        //��ǰ�û�
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
            			 String[]  kcinfo = kcinfos[i].split("\\|"); //kcinfo[0]���id  kcinfo[1]��������
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
 							job.setStatus("4"); //���״̬
 							job.setVirtualwhid(outboundInvoiceDetail.getVirtualwhid());//�߼�����id
 							job.setPriority(1);//���ȼ�
 							job.setCreatetime(StrTools.getCurrDateTime(2));
 							job.setInOutType("2");//��������
 							job.setIsinvoice("Y");
 							job.setTraycode(storage.getTraycode());
 							job.setBoundstockid(outboundInvoiceDetail.getOutstockid());
 							job.setBoundstockdetailid(outboundInvoiceDetail.getOutstockdetailid());
 							job.setRoute("1");//·�� ֱ��
 							job.setOpManId(strUserCode);
 							job.setJobcategory("2"); // �ݴ�����ҵ
 							job.setInvoicetype("2"); // 1:������ҵ�����ɵ���;2:���е��ݺ�������ҵ
 							job.setWarehouseid(storage.getWarehouseid());
							job.setScargoSpaceId(storage.getCargoSpaceId());
							job.setScargoWhareaId(storage.getWhAreaId());
							job.setScargoAlleyId(storage.getCargoAlleyId());
				            
							jobdetail.setJobid(strNo);
							jobdetail.setProductid(storage.getProductid());
							jobdetail.setLotinfo(storage.getLotid());		//�������
							jobdetail.setLotinfo2(storage.getLotinfo2());	//ԭ�����
							jobdetail.setPunit(storage.getPunit());
							jobdetail.setJobnum(Double.parseDouble(kcinfo[1]));
							jobdetail.setAssignnum(Double.parseDouble(kcinfo[1]));
							jobdetail.setPrintdate(storage.getProductdate());
							jobdetail.setSupplier(storage.getSupplier());
							jobdetail.setIsinvoice("Y");
							jobdetail.setProductStatus(storage.getProductstatus());//��Ʒ״̬
							jobdetail.setProductcode(storage.getProductcode());
							jobdetail.setIsreview("Y");

							
							lsInoutJobs.add(job);
							lsInoutJobdetails.add(jobdetail);
							Logger.error("�û�[" + strUserCode + "]��������==>RF������ҵ�ɹ�:jobid=[" +job.getJobid() +"]");
            			 }else{
            				 strBackMsg = "�ݴ�����������ܴ����ݴ�����";
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
                    //������ҵ
                    job = null;
                    for(int i = 0; i < lsInoutJobs.size(); i++){
                        job = lsInoutJobs.get(i);
                        session.save(job);
                    } 
                    
                    //������ҵ��ϸ
                    jobdetail = null;
                    for(int i = 0; i < lsInoutJobdetails.size(); i++){
                    	jobdetail = lsInoutJobdetails.get(i);
                        session.save(jobdetail);
                    } 
                    
                    //������ҵ��ϸ
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
                    throw new  Exception("���ɳ�����ҵ����"+er.getMessage());
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
            //����ֵ
            request.setAttribute("back_msg", strBackMsg);  
            request.setAttribute("exResultList", lsList);  
            request.getRequestDispatcher(strUrl).forward(request, response);   
        }catch(Exception er)
        {
            Logger.error("�û�["+strUserCode+"]�����ⱸ��==>�����ż���������:" + er.getMessage());
            request.setAttribute("errormsg", "�û�["+strUserCode+"]��������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }       
        return null;
    }
    /**
     * ���ܣ����ӳ��ⵥ
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
        
        String strOutType = CommonTools.getStrToGbk(request.getParameter("out_type"));          //�������� 
        String strWarehouseId = CommonTools.getStrToGbk(request.getParameter("warehouseid"));   //�ֿ�
        String strVehiclepos = CommonTools.getStrToGbk(request.getParameter("vehiclepos"));     //��λ
        String strVehicleno = CommonTools.getStrToGbk(request.getParameter("vehicleno"));       //���ƺ�
        String strSendplatform = CommonTools.getStrToGbk(request.getParameter("sendplatform")); //������̨
        String strSetposition = CommonTools.getStrToGbk(request.getParameter("setposition"));   //����λ
        String strShiftid = CommonTools.getStrToGbk(request.getParameter("shiftid"));           //���
        
        String strOnLineType = CommonTools.getStrToGbk(request.getParameter("on_line_type")); //����ģʽ
        String strRequestdate = CommonTools.getStrToGbk(request.getParameter("requestdate")); //Ҫ�󷢻�ʱ��
        String strExpectdate = CommonTools.getStrToGbk(request.getParameter("expectdate"));   //Ԥ�ڷ���ʱ��
        String strSaleno = CommonTools.getStrToGbk(request.getParameter("saleno"));           //���۵���
            
        String strOutDate = CommonTools.getStrToGbk(request.getParameter("formdate"));        //��������
        String strDepartId = CommonTools.getStrToGbk(request.getParameter("departid"));       //����
        
        String ownerid = CommonTools.getStrToGbk(request.getParameter("owner_id"));         // ����
        String customer_id = CommonTools.getStrToGbk(request.getParameter("customer_id"));  // �ͻ�
        
        String strAddress = CommonTools.getStrToGbk(request.getParameter("address"));        //������ַ
        
        //��ǰ�û�
        String strUserCode = (String)request.getSession().getAttribute("userCode");
        
        outboundBus = new OutBoundBusImpl(dao);
        try
        {
            String strBackMsg = "Y";
    
            //���ⵥ
            OutboundInvoiceHeader outInvoice = new OutboundInvoiceHeader();
            outInvoice.setOuttype(strOutType);         // ��������
            outInvoice.setDepartid(strDepartId);       // ����id
            outInvoice.setWarehouseid(strWarehouseId); // �ֿ�id
            outInvoice.setCreatetime(StrTools.getCurrDateTime(2)); // ���ⵥ����ʱ��
            outInvoice.setFormdate(strOutDate);                     // ��������
            outInvoice.setOutpos("2");                 // �����
            outInvoice.setVehicleno(strVehicleno);     // ����
            outInvoice.setVehiclepos(strVehiclepos);   // ��λ
            outInvoice.setOutstatus("0");              // ����״̬ 0.���⿪�� 
            outInvoice.setCreatemanid(strUserCode);    // ������
            outInvoice.setOpmanid(strUserCode);        // ����Ա
            outInvoice.setShiftid(strShiftid);                 // ��ҵ���
            outInvoice.setIsupload("N");               // �Ƿ��ϴ��ɹ� Y.�� N.�� Ĭ��Ϊ��
            outInvoice.setOnLineType(strOnLineType);   // ����ģʽ 1.���� 2.�ѻ�
            outInvoice.setSaleno(strSaleno);           // ���۵���  
            outInvoice.setSendplatform(strSendplatform);  //������̨
            outInvoice.setSetposition(strSetposition);    //����λ
            outInvoice.setShipmentstarttime(strRequestdate);//Ҫ�󷢻�ʱ��
            outInvoice.setShipmentendtime(strExpectdate);   //Ԥ�ڷ���ʱ��
            outInvoice.setOwnerid(ownerid);         //����
            outInvoice.setCustomerid(customer_id);  //�ͻ�
            outInvoice.setAddress(strAddress); //�ͻ� �ջ��˵�ַ
            //���ӳ��ⵥ
            String strID = outboundBus.addOutBound(outInvoice);
            //��תҳ��
            strUrl = "/standard/jsp/outbound/newckd/outbound_newckd_list.jsp";
            
            //����ֵ
            request.setAttribute("back_msg", strBackMsg);                
            request.setAttribute("InvoicedId", strID);
            request.getRequestDispatcher(strUrl).forward(request, response);   
            
        }catch(Exception er)
        {
            Logger.error("�û�["+strUserCode+"]���������==>�½����ⵥ�ݳ���:" + er.getMessage());
            request.setAttribute("errormsg", "�û�["+strUserCode+"]��������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }       
        return null;
    }
    /**
     * ���ܣ��޸ĳ��ⵥ
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
        //String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid")); // �ֿ�ID
        String strUserCode = (String)request.getSession().getAttribute("userCode");           //��ǰ�û�
        
        //����ID
        String strInvoiceId = CommonTools.getStrToGbk(request.getParameter("invoiceid")); 
         
        String strOutType = CommonTools.getStrToGbk(request.getParameter("out_type"));       //��������
        //��ǰ�ֿ�
        //String strWarehouseId = CommonTools.getStrToGbk(request.getParameter("warehouseid"));
        
        String strVehiclepos = CommonTools.getStrToGbk(request.getParameter("vehiclepos"));  //��λ
        String strVehicleno = CommonTools.getStrToGbk(request.getParameter("vehicleno"));    //���ƺ�
        String strSendplatform = CommonTools.getStrToGbk(request.getParameter("sendplatform")); //������̨
        String strSetposition = CommonTools.getStrToGbk(request.getParameter("setposition"));   //����λ
        
        String strOnLineType = CommonTools.getStrToGbk(request.getParameter("on_line_type"));//����ģʽ
        String strRequestdate = CommonTools.getStrToGbk(request.getParameter("requestdate")); //Ҫ�󷢻�ʱ��
        String strExpectdate = CommonTools.getStrToGbk(request.getParameter("expectdate")); //Ԥ�ڷ���ʱ��
        String strSaleno = CommonTools.getStrToGbk(request.getParameter("saleno"));          //���۵���
            
        String strOutDate = CommonTools.getStrToGbk(request.getParameter("formdate"));       //��������
        String strDepartId = CommonTools.getStrToGbk(request.getParameter("departid")); //����

        String ownerid = CommonTools.getStrToGbk(request.getParameter("owner_id"));         // ����
        String customer_id = CommonTools.getStrToGbk(request.getParameter("customer_id"));  // �ͻ� ���ջ���
        String strAddress = CommonTools.getStrToGbk(request.getParameter("address"));       // �ջ��˵�ַ
        outboundBus = new OutBoundBusImpl(dao);
        String strMsg = "Y";
        try
        {
            //Ҫ�޸ĳ��ⵥ������ 
            OutboundInvoiceHeader outInvoice = outboundBus.getOutBoundById(strInvoiceId);
            outInvoice.setOuttype(strOutType);         // ��������
            outInvoice.setDepartid(strDepartId);       // ����id
            //outInvoice.setWarehouseid(strWarehouseId); // �ֿ�id
            outInvoice.setFormdate(strOutDate);        // ��������
            outInvoice.setVehicleno(strVehicleno);     // ����
            outInvoice.setVehiclepos(strVehiclepos);   // ��λ
            outInvoice.setOpmanid(strUserCode);        // ����Ա
            //outInvoice.setShiftid(strShiftid);       // ��ҵ��� 
            outInvoice.setOnLineType(strOnLineType);   // ����ģʽ 1.���� 2.�ѻ�
            outInvoice.setSaleno(strSaleno);           // ���۵���  
            outInvoice.setSendplatform(strSendplatform);    //������̨
            outInvoice.setSetposition(strSetposition);      //����λ
            outInvoice.setShipmentstarttime(strRequestdate);//Ҫ�󷢻�ʱ��
            outInvoice.setShipmentendtime(strExpectdate);   //Ԥ�ڷ���ʱ��
            outInvoice.setOwnerid(ownerid);         //����
            outInvoice.setCustomerid(customer_id);  //�ͻ�
            outInvoice.setAddress(strAddress);//�ջ��˵�ַ
            
            strMsg = outboundBus.updateOutBound(outInvoice); 

            
            //ˢ�� 
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
                //��תҳ��
                strUrl = "/standard/jsp/outbound/newckd/outbound_newckd_list.jsp";
            }
            request.setAttribute("exResultList", ls);
            request.setAttribute("pagingTool", pt);
            httpsession.setAttribute("paging", pt);
           
            
            
            //����ֵ
            request.setAttribute("back_msg", strMsg);//���ز������
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("�û�["+strUserCode+"]���������==>�޸ĳ��ⵥ�ݳ���:" + er.getMessage());
            request.setAttribute("errormsg", "�û�["+strUserCode+"]��������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    
    /**
     * ���ܣ����ӳ��ⵥ��ϸ
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
        
        String strUserCode = (String)request.getSession().getAttribute("userCode");           //��ǰ�û�
        //���ⵥ��ID
        String strInvoiceId = CommonTools.getStrToGbk(request.getParameter("invoiceid")); 
        String strProductId = CommonTools.getStrToGbk(request.getParameter("productid"));    //Ʒ�����
        String printdate = CommonTools.getStrToGbk(request.getParameter("printdate"));       //��������  
        
        
        String strEunit = CommonTools.getStrToGbk(request.getParameter("eunit"));            //��λ
        String strInvoiceNum = CommonTools.getStrToGbk(request.getParameter("invoicenum"));  //��������
        String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));            //����
        String Virtualwhid = CommonTools.getStrToGbk(request.getParameter("Virtualwhid"));    //�߼�����
        
        outboundBus = new OutBoundBusImpl(dao);
        String strMsg = "";
        try
        {
            //���ⵥ��ϸ
            OutboundInvoiceDetail outBoundDetail = new OutboundInvoiceDetail();
            outBoundDetail.setOutstockid(strInvoiceId); //�ջ������
            outBoundDetail.setLinestatus("0");   //������״̬0-����
            outBoundDetail.setProductid(strProductId);   //Ʒ��
            outBoundDetail.setPkgunit(strEunit);
            outBoundDetail.setInvoicenum(Double.parseDouble(strInvoiceNum)); //����
            outBoundDetail.setLotinfo(lotinfo);   //������Ϣ
            outBoundDetail.setPrintdate(printdate); //��������
            outBoundDetail.setVirtualwhid(Virtualwhid); //�߼�����
            strMsg = outboundBus.addOutBoundDetail(outBoundDetail, strInvoiceId);
            //���ؽ��
            //���ⵥ��ϸ
            List<OutboundInvoiceDetail> lsDetail = outboundBus.getOutBoundDetailsById(strInvoiceId); 
            strUrl = "/standard/jsp/outbound/newckd/outbound_newckd_detail_list.jsp";
            request.setAttribute("invoicedetail", lsDetail);  
            //����ֵ
            request.setAttribute("back_msg", strMsg);//���ز������
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("�û�["+strUserCode+"]���������==>�������ⵥ��ϸ����:" + er.getMessage());
            request.setAttribute("errormsg", "�û�["+strUserCode+"]��������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    /**
     * ���ܣ��޸ĳ��ⵥ��ϸ
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
        
        String strUserCode = (String)request.getSession().getAttribute("userCode");           //��ǰ�û�
        //���ⵥ��ϸID
        String strInvoiceDetailId = CommonTools.getStrToGbk(request.getParameter("detailid")); 
        
        String strProductId = CommonTools.getStrToGbk(request.getParameter("productid"));    //Ʒ�����
        
        String strEunit = CommonTools.getStrToGbk(request.getParameter("eunit"));            //��λ
        String strInvoiceNum = CommonTools.getStrToGbk(request.getParameter("invoicenum"));  //��������
        //String strVolume = CommonTools.getStrToGbk(request.getParameter("volume"));          //���
        
        String productcode = CommonTools.getStrToGbk(request.getParameter("productcode"));       //��Ʒ���� 
        String spec = CommonTools.getStrToGbk(request.getParameter("spec"));      				 //��� 
        
        String printdate = CommonTools.getStrToGbk(request.getParameter("printdate"));       //��������  
        String strWeight = CommonTools.getStrToGbk(request.getParameter("weight"));          //����
        
//        String strNetweight = CommonTools.getStrToGbk(request.getParameter("netweight"));    //����
//        String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));            //����
            
        outboundBus = new OutBoundBusImpl(dao);
        String strMsg = "";
        try
        {
            //���ⵥ��ϸ
            OutboundInvoiceDetail outBoundDetail = outboundBus.getOutBoundDetailById(strInvoiceDetailId); 
            outBoundDetail.setProductid(strProductId);   //Ʒ��
            outBoundDetail.setPkgunit(strEunit);
            outBoundDetail.setInvoicenum(Double.parseDouble(strInvoiceNum)); //����
            //outBoundDetail.setVolume(Double.parseDouble(strVolume));         //���
           // outBoundDetail.setWeight(Double.parseDouble(strWeight));         //����
//            outBoundDetail.setNetweight(Double.parseDouble(strNetweight));   //����
//            outBoundDetail.setLotinfo(lotinfo);   //������Ϣ
           // outBoundDetail.setPrintdate(printdate); //��������
            strMsg = outboundBus.updateOutBoundDetail(outBoundDetail, outBoundDetail.getOutstockid());
            
            //���ؽ��
            //���ⵥ��ϸ
            List<OutboundInvoiceDetail> lsDetail = outboundBus.getOutBoundDetailsById(outBoundDetail.getOutstockid()); 
            strUrl = "/standard/jsp/outbound/newckd/outbound_newckd_detail_list.jsp";
            request.setAttribute("invoicedetail", lsDetail); 
           
            //����ֵ
            request.setAttribute("back_msg", strMsg);//���ز������
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("�û�["+strUserCode+"]���������==>�޸ĳ��ⵥ��ϸ����:" + er.getMessage());
            request.setAttribute("errormsg", "�û�["+strUserCode+"]��������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    
    /**
     * ���ܣ�ɾ�����ⵥ�ݻ���ⵥ����ϸ
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
        
        String strUserCode = (String)request.getSession().getAttribute("userCode"); //��ǰ�û�
        //�������� 1��ɾ������ 2��ɾ��������ϸ
        String strFlag = CommonTools.getStrToGbk(request.getParameter("flag"));
        //����ID�б�
        String strInvoiceIds = CommonTools.getStrToGbk(request.getParameter("invoiceids")); 
        
        //����ID
        String strInvoiceId = CommonTools.getStrToGbk(request.getParameter("invoiceid"));
        //������ϸID (ɾ������ʱ����Ҫ)�б�
        String strInvoiceDetailIds = CommonTools.getStrToGbk(request.getParameter("detailids"));

        outboundBus = new OutBoundBusImpl(dao);
        String strMsg = "";
        try
        {
            List<OutboundInvoiceDetail> lsDetail = null;
            if(strFlag != null && strFlag.trim().equals("1")){ //1��ɾ������)
                if(strInvoiceIds != null)
                {   
                    String strIds[] = strInvoiceIds.split(",");    
                    for(int i = 0; i < strIds.length; i++)
                    {
                        //����ID
                        String strInvoice = strIds[i];
                        strMsg = strMsg + outboundBus.deleteOutBoundInvoice(strInvoice, null, strFlag);
                    }
                }
                //��תҳ��
                strUrl = "/standard/jsp/outbound/newckd/outbound_newckd_list.jsp";
                
            }else if(strFlag != null && strFlag.trim().equals("2")){ //2��ɾ��������ϸ
                if(strInvoiceDetailIds != null)
                {   
                    String strIds[] = strInvoiceDetailIds.split(",");    
                    for(int i = 0; i < strIds.length; i++)
                    {
                        //������ϸ
                        String strInvoiceDetailId = strIds[i];
                        strMsg = strMsg + outboundBus.deleteOutBoundInvoice(strInvoiceId, strInvoiceDetailId, strFlag);
                    }
                }
                lsDetail = outboundBus.getOutBoundDetailsById(strInvoiceId);
                //��תҳ��
                strUrl = "/standard/jsp/outbound/newckd/outbound_newckd_detail_list.jsp";
            }

            //����ֵ
            request.setAttribute("back_msg", strMsg);//���ز������
            request.setAttribute("invoicedetail", lsDetail);
            request.getRequestDispatcher(strUrl).forward(request, response);
            
        }catch(Exception er)
        {
            Logger.error("�û�["+strUserCode+"]���������==>ɾ�����ⵥ����ϸ(" + strFlag + ")����:" + er.getMessage());
            request.setAttribute("errormsg", "�û�["+strUserCode+"]��������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        return null;
    }
    
    
    /**
	 * ����:������ѯ
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
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//����
		String senddate_from = CommonTools.getStrToGbk(request.getParameter("senddate_from"));	//��������
		String senddate_to = CommonTools.getStrToGbk(request.getParameter("senddate_to"));		//��������
		String shiftid = CommonTools.getStrToGbk(request.getParameter("shift_id"));			//��� 
		
		String productid = CommonTools.getStrToGbk(request.getParameter("productid"));		//Ʒ��
		String customerid = CommonTools.getStrToGbk(request.getParameter("customerid"));	//�ͻ�
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));		//��������
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));              //����ID
		String jobid = CommonTools.getStrToGbk(request.getParameter("jobid"));				//��ҵ��
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
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
				
				if(flag.equals("1")){//������ѯ�б�
					strUrl = "/standard/jsp/outbound/send/outbound_send_search_list.jsp";
					
					PagingTool pt = CommonPagination.getPagingTool(dao, strSqls[0], strSqls[1], strUrl, 1, maxLine);
					
					List ls = pt.getLsResult();
					request.setAttribute("exResultList", ls);
					httpsession.setAttribute("paging", pt);
					
				}else if(flag.equals("2")){		//������ѯ ����
					
					strUrl = "/standard/jsp/outbound/send/outbound_send_search_report.jsp";
					
					List ls = dao.searchEntities(strSqls[1]);
					
					request.setAttribute("exResultList", ls);
					
				}
			}else if(flag!=null && flag.equals("3")){		//������ѯ ��ҵ��ϸ�б�
				
				strUrl = "/standard/jsp/outbound/send/outbound_send_search_detail.jsp";
				
				List ls = outboundjobBus.getJobDetails(jobid);
				request.setAttribute("exResultList", ls);
			}
			

			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("�������==>������ѯ==>������ѯʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:���ϳ��ⵥ��
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
		
		String ids = CommonTools.getStrToGbk(request.getParameter("ids"));		//����IDS
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		outboundBus = new OutBoundBusImpl(dao);
		try{
			
			String strBackMsg = outboundBus.cancelInvoices(ids, strUserCode);
		
			strUrl = "/standard/jsp/outbound/ckdmgr/outbound_ckmgr_list.jsp";	
			
			    
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
			
			Logger.error("�û�["+strUserCode+"]���������==>���ݹ���==>���ϳ��ⵥ��ʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}
	
	/**
	 * ����:���ⵥ����->���ݴ�ӡ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecReportCKD(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String outstockid = CommonTools.getStrToGbk(request.getParameter("outstockid"));	//���ⵥ�ݱ��
		
		outboundBus = new OutBoundBusImpl(dao);
		try{
			
			List<BaseLotSet> lsLot = null;	//�������Լ�
			HashMap<String, List> hsLot = (HashMap<String, List>)hsSysParam.get("viewLot");	//����Ҫ��ʾ������
			if(hsLot != null){
				lsLot = hsLot.get("newckd");		//�½����ⵥ->��ʾ���ⵥ��ϸ	
			}
			OutboundInvoiceHeader outbound = outboundBus.getOutBoundById(outstockid);	//���ⵥ
			List lsDetail = outboundBus.getOutBoundDetailsById(outstockid);				//���ⵥ��ϸ
            
            strUrl = "/standard/jsp/outbound/ckdmgr/outbound_ckmgr_cpprint.jsp";
            request.setAttribute("lsLot", lsLot);
            request.setAttribute("outstockid", outstockid);
            request.setAttribute("invoice", outbound);
            request.setAttribute("invoicedetail", lsDetail); 
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("�������==>���ݹ���==>���ⵥ��ӡʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:���ⵥ����->���ݴ�ӡ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecReportCKDMX(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String outstockid = CommonTools.getStrToGbk(request.getParameter("outstockid"));	//���ⵥ�ݱ��
		
		outboundBus = new OutBoundBusImpl(dao);
		JobBus = new JobBusImpl(dao);
		try{
			
			OutboundInvoiceHeader outbound = outboundBus.getOutBoundById(outstockid);	//���ⵥ
			//List lsDetail = outboundBus.getOutBoundDetailsById(outstockid);				//���ⵥ��ϸ
			List ls = JobBus.getJobDetailByInvoiceid(outstockid);
            
            strUrl = "/standard/jsp/outbound/ckdmgr/outbound_ckmgr_djmxprint.jsp";
            //request.setAttribute("invoice", outbound);
            request.setAttribute("invoice", outbound);
            request.setAttribute("invoicedetail", ls); 
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("�������==>���ݹ���==>���ⵥ��ӡʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:���ⵥ����->��ѯ
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
    		
            //ÿҳ��ʾ����
            int iline = 5;
            String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));
            if(linerow != null && linerow.length()>0){
                iline = Integer.parseInt(linerow);
            }
            String outstockdetailid = CommonTools.getStrToGbk(request.getParameter("outstockdetailid"));//���ⵥ��ϸ
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
            Logger.error("�������==>���ݹ���==>��������:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        
		return null;
	}
	
	/**
	 * ���ܣ����ӳ��ⵥ
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
        
        String warehouseid = (String) hsSysParam.get("c_warhouse_id");  //�ֿ� 
        String strFormType = CommonTools.getStrToGbk(request.getParameter("form_type"));        //�������� 
        String strownerid = CommonTools.getStrToGbk(request.getParameter("owner_id"));             // ����
        String strcustomerid = CommonTools.getStrToGbk(request.getParameter("customer_id"));  // �ͻ�
        String straddress = CommonTools.getStrToGbk(request.getParameter("address"));   //�ջ���ַ
        String strvehicleno = CommonTools.getStrToGbk(request.getParameter("vehicleno"));  //���ƺ�
        String strvehiclepos = CommonTools.getStrToGbk(request.getParameter("vehiclepos"));  //��λ
        String sign= CommonTools.getStrToGbk(request.getParameter("sign"));
        String strdepartid = CommonTools.getStrToGbk(request.getParameter("departid"));  //����
        String strsendplatform = CommonTools.getStrToGbk(request.getParameter("sendplatform")); //������̨
        String strsetposition = CommonTools.getStrToGbk(request.getParameter("setposition"));  //����λ
        String strsaleno = CommonTools.getStrToGbk(request.getParameter("saleno"));  //���۵���
        String strformdate = CommonTools.getStrToGbk(request.getParameter("formdate")); //��������
        String strrequestdate = CommonTools.getStrToGbk(request.getParameter("requestdate"));  //Ҫ�󷢻�ʱ��
        String strexpectdate = CommonTools.getStrToGbk(request.getParameter("expectdate"));  //Ԥ�ڷ���ʱ��
        String strdetail = CommonTools.getStrToGbk(request.getParameter("detail"));  
        String strUserCode = (String)request.getSession().getAttribute("userCode");//��ǰ�û�
        outboundBus = new OutBoundBusImpl(dao);
        String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        if(flag != null && flag.trim().equals("Storage")){
        	flag = "product";
        }
        try
        {
        	if(flag != null && flag.trim().equals("1")){//�½�����->���->��ϸ�б�
        		/*strUrl = "/standard/jsp/outbound/newckd/outbound_ckd_add_list.jsp";
				lsLot = AbstractBus.getLotset(hsSysParam, V_CKDDETAIL);
				request.setAttribute("lsLot", lsLot);*/
        	}else{//�½�����->���->���ݶ������ɳ��ⵥ
        		List<OutboundInvoiceDetail> outboundInvoiceDetails = new ArrayList<OutboundInvoiceDetail>();
        		List<SaleFormDetail> SaleFormDetaills = new ArrayList<SaleFormDetail>();
        		//SaleForm			//���۵�
				//SaleFormDetail	//���۵���ϸ
                String invoiceid="";
                String strBackMsg = "Y";
                HttpSession httpsession = request.getSession(false);
                int iline = 5;//ÿҳ��ʾ����
                String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));
                if(linerow != null && linerow.length()>0){
                    iline = Integer.parseInt(linerow);
                }

        		BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(dao);
        		IOutboundSoBus outBoundSoBus = new OutboundSoBusImpl(dao);
        		
                BaseSeq  seqEn = seqDao.getSeqByType("CKD");
                String strOutID = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), dao);
                
                OutboundInvoiceHeader outInvoice = new OutboundInvoiceHeader();//���ⵥ
                outInvoice.setOutstockid(strOutID);
                outInvoice.setOuttype(strFormType);        // ���ⵥ������
                outInvoice.setDepartid(strdepartid);       // ����id
                outInvoice.setWarehouseid("001");    // �ֿ�id
                outInvoice.setCreatetime(StrTools.getCurrDateTime(2)); // ���ⵥ����ʱ��
                outInvoice.setFormdate(strformdate);                    // ��������
                outInvoice.setOutpos("2");                 // �����
                outInvoice.setVehicleno(strvehicleno);     // ����
                outInvoice.setVehiclepos(strvehiclepos);   // ��λ
                outInvoice.setOutstatus("0");              // ����״̬ 0.���⿪�� 
                outInvoice.setCreatemanid(strUserCode);    // ������
                outInvoice.setOpmanid(strUserCode);        // ����Ա
                outInvoice.setShiftid("");                 // ��ҵ���
                outInvoice.setIsupload("N");               // �Ƿ��ϴ��ɹ� Y.�� N.�� Ĭ��Ϊ��
                outInvoice.setOnLineType("1");              // ����ģʽ 1.���� 2.�ѻ�
                outInvoice.setSaleno(strsaleno);           // ���۵���  
                outInvoice.setSendplatform(strsendplatform);  //������̨
                outInvoice.setSetposition(strsetposition);    //����λ
                outInvoice.setShipmentstarttime(strrequestdate);//Ҫ�󷢻�ʱ��
                outInvoice.setShipmentendtime(strexpectdate);   //Ԥ�ڷ���ʱ��
                outInvoice.setOwnerid(strownerid);         //����
                outInvoice.setCustomerid(strcustomerid);  //�ͻ�
        		if(flag != null && flag.trim().equals("order")){
        			   synchronized (IOutBoundBus.newout_lock){
		                    if(strdetail!=null && !strdetail.equals("")){ //strdetail ģʽdetailid,num|detailid,num
		                    	String[] strdetail1= strdetail.split("\\|");
		                    	if(strdetail1!=null && strdetail1.length>0){
		                    		for(int i=0;i<strdetail1.length;i++){
		                    			String[] strdetail12= strdetail1[i].split(",");
		                    			if(strdetail12!=null && strdetail12.length==3){//��ӵ�����ϸ  detailid,num,�߼�����
		                    				SaleFormDetail sodetail = null;
		                    				sodetail = outBoundSoBus.getOutboundSoHeaders(null,strdetail12[0]);
		                    				
		                    				if(sodetail!=null){//���ⶩ��Ϊ����״̬
		                    					OutboundInvoiceDetail outBoundDetail = new OutboundInvoiceDetail();
		                    					outBoundDetail.setOutstockdetailid(SeqTool.getDetailId(strOutID,(i+1)+"",StandardConstant.D_LEN));
		                        	            outBoundDetail.setOutstockid(strOutID); //���ⵥ���
		                        	            outBoundDetail.setLinestatus("0");   //������״̬0-����
		                        	            outBoundDetail.setProductid(sodetail.getM_strPackageId());   //Ʒ��
		                        	            outBoundDetail.setPkgunit(sodetail.getM_strUnit());
		                        	            outBoundDetail.setInvoicenum(Double.parseDouble(strdetail12[1])); //����
		                        	            outBoundDetail.setVolume(0);         //���
		                        	            outBoundDetail.setWeight(0);         //����
		                        	            outBoundDetail.setNetweight(0);   //����
		                        	            outBoundDetail.setPrice(0);//����
		                        	            outBoundDetail.setSodetailid(strdetail12[0]);	//so��ϸ
		                        	            outBoundDetail.setSoid(strsaleno);				//so
		                        	            
		                        	            outboundInvoiceDetails.add(outBoundDetail);
		                        	            double dnum = sodetail.getM_iOutNum()+Double.parseDouble(strdetail12[1]);
		                        	            sodetail.setM_iOutNum(dnum);
		                        	            SaleFormDetaills.add(sodetail);
		                        	            
		                    				}else{
		                    					strBackMsg = "�г��ⶩ����ϸ��Ϊ����״̬ ���ܽ����ٴο��� ���ɵ���ʧ��";
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
		                    			strBackMsg = "û�е�����ϸ��Ϣ ���ɵ���ʧ��";
		                    		}
		                    	}
		                    }
        			}
        		}else{
        			strBackMsg = "û�и����ɸõ�������   ���ɵ���ʧ��";
        		}
        		String strQueryHQL = outboundBus.getOutBoundQuerySQL(warehouseid, "", "", "", "", "", "", invoiceid);
                String strCountHQL = outboundBus.getOutBoundCountSQL(warehouseid, "", "", "", "", "", "", invoiceid);
                strUrl = "/standard/jsp/outbound/newckd/outbound_newckd_list.jsp";//��תҳ��  
                if(sign.equals("1")){
                	strUrl="/standard/jsp/outbound/newckd/outbound_ckd_add_list.jsp";
                }
                PagingTool pt = CommonPagination.getPagingTool(dao, strCountHQL, strQueryHQL, strUrl, 1, iline);
                List ls = pt.getLsResult();//��ѯ���
                request.setAttribute("back_msg", strBackMsg);   //����ֵ 
                request.setAttribute("InvoicedId", invoiceid);
                request.setAttribute("exResultList", ls);
                request.setAttribute("pagingTool", pt);
                httpsession.setAttribute("paging", pt);
        	}
            request.getRequestDispatcher(strUrl).forward(request, response);        
        }catch(Exception er)
        {
            Logger.error("�û�["+strUserCode+"]���������==>�½����ⵥ�ݳ���:" + er.getMessage());
            request.setAttribute("errormsg", "�û�["+strUserCode+"]��������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }       
        return null;
    }
	
}
