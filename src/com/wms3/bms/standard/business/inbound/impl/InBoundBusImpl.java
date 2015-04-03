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
 * ����: ��ⵥ����ҵ����
 * @author hug 2012-8-16
 * @since WMS 3.0
 */
public class InBoundBusImpl implements IInBoundBus {
	
    /** �ջ���DAO��  */
    protected IInboundDao inboundDao;
    
    public InBoundBusImpl(EntityDAO dao) {
    	inboundDao = new InboundDaoImpl(dao);
	}

    /**
	 * ����:��ѯ�½���ⵥ����ҵ��
	 * @param warehouseid	�ֿ�
	 * @param whAreaId		����
	 * @param indate		����
	 * @param shiftid		���
	 * @param owner_id		����
	 * @param isinvoice		�Ƿ񿪵�
	 * @param lsLot 		�½���ⵥ��Ӧ�������������ü�
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
			
			//�������Զ�̬����
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
			
			strSql += getJobCondition(warehouseid, whAreaId, indate, shiftid, owner_id, isinvoice);	//where����
			strSql += strSqlGrp + " order by job.jobtype, jobdetail.ownerId, jobdetail.sinvoiceid, jobdetail.productid ";
			list = inboundDao.querySQL(strSql);
			
		} catch(Exception er) {
			er.printStackTrace();
			throw new Exception("������->�½���ⵥ��ѯ��ҵ������" + er.getMessage());
		}
		return list;
	}

	/**
	 * ����:�½���ⵥ->��ѯ��ϸ
	 * @param warehouseid	�ֿ�
	 * @param whAreaId		����
	 * @param indate		����
	 * @param shiftid		���
	 * @param isinvoice		�Ƿ񿪵�
	 * @param strKey		��ϸkey
	 * @param lsLot 		�½���ⵥ��Ӧ�������������ü� 
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
			throw new Exception("������->�½���ⵥ->��ѯ��ϸ����" + er.getMessage());
		}
		return list;
	}

	/**
	 * ����:�½���ⵥ->��ѯ��ϸ��ҵsql��
	 * @param warehouseid	�ֿ�
	 * @param whAreaId		����
	 * @param indate		����
	 * @param shiftid		���
	 * @param isinvoice		�Ƿ񿪵�
	 * @param strKey		��ϸkey
	 * @param lsLot 		�½���ⵥ��Ӧ�������������ü� 
	 * @return 
	 * @throws Exception
	 */
	private String getJobCreateInvoice(String warehouseid, String whAreaId, String indate, String shiftid, String isinvoice, String strKey, 
			List<BaseLotSet> lsLot) {
		
		String tempstr = "";
		
		tempstr = getJobCondition(warehouseid, whAreaId, indate, "", shiftid, isinvoice);	//where����(������ϸ����)
		
		//�������Զ�̬����
		String[] aem = strKey.split("\\|");
		
		
		if(aem[0].length() == 0){		//Ʒ��
			tempstr += " and (jobdetail.productid='" + aem[0] + "' or jobdetail.productid is null)";
		}else{
			tempstr += " and jobdetail.productid='" + aem[0] + "'";
		}	
		
		if(aem[1].length() == 0){		//��ҵ����
			tempstr += " and (job.jobtype='" + aem[1] + "' or job.jobtype is null)";
		}else{
			tempstr += " and job.jobtype='" + aem[1] + "'";
		}

		if(aem[2].length() == 0){		//����
			tempstr += " and (jobdetail.ownerId='" + aem[2] + "' or jobdetail.ownerId is null)";
		}else{
			tempstr += " and jobdetail.ownerId='" + aem[2] + "'";
		}
		
		if(aem[3].length() == 0){		//��Դ���ݺ�
			tempstr += " and (jobdetail.sinvoiceid='" + aem[3] + "' or jobdetail.sinvoiceid is null)";
		}else{
			tempstr += " and jobdetail.sinvoiceid='" + aem[3] + "'";
		}
		
		if(aem[4].length() == 0){		//��װID
			tempstr += " and (jobdetail.packid='" + aem[4] + "' or jobdetail.packid is null)";
		}else{
			tempstr += " and jobdetail.packid='" + aem[4] + "'";
		}
		
		if(aem[5].length() == 0){		//��λ
			tempstr += " and (jobdetail.punit='" + aem[5] + "' or jobdetail.punit is null)";
		}else{
			tempstr += " and jobdetail.punit='" + aem[5] + "'";
		}
		
		if(aem[6].length() == 0){		//��������ID
			tempstr += " and (jobdetail.lotid='" + aem[6] + "' or jobdetail.lotid is null)";
		}else{
			tempstr += " and jobdetail.lotid='" + aem[6] + "'";
		}
		
		if(aem[7].length() == 0){		//��ҵ��Դ
			tempstr += " and (job.invoicetype='" + aem[7] + "' or job.invoicetype is null)";
		}else{
			tempstr += " and job.invoicetype='" + aem[7] + "'";
		}
		
		String lotvalue = "";
		BaseLotSet lotSet = null;
		if(lsLot != null){
			for(int k = 0; k < lsLot.size(); k++){
				lotSet = (BaseLotSet)lsLot.get(k);  
				lotvalue = aem[8+k];			//��̬��������ֵ
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
	 * ����:�����½���ⵥ��ѯ��ҵʱ��Ĳ�ѯ����
	 * @param warehouseid	�ֿ�
	 * @param whAreaId		����
	 * @param indate		����
	 * @param shiftid		���
	 * @param owner_id		����
	 * @param isinvoice		�Ƿ񿪵�
	 * @return 
	 * @throws Exception
	 */
	private String getJobCondition(String warehouseid, String whAreaId, String indate, String shiftid, String owner_id, String isinvoice) {
		
		String tmpstr = " from InoutJob as job, InoutJobdetail as jobdetail " +
				  " where job.jobid=jobdetail.jobid and job.inOutType='1' and job.isaccount='Y' and job.status!='5' ";
		
		//�ֿ�
		if(warehouseid != null && warehouseid.trim().length() > 0){
			tmpstr += " and job.warehouseid='" + warehouseid + "'";
		}
		
		//����
		if(whAreaId != null && whAreaId.trim().length() > 0){
			tmpstr += " and job.tcargoWhareaId='" + whAreaId + "'";
		}

		//����
		if(indate != null && indate.trim().length() > 0){
			tmpstr += " and substring(job.createtime,1,10)='" + indate + "'";
		}
		
		//���
		if(shiftid != null && shiftid.trim().length() > 0){
			tmpstr += " and job.shiftid='" + shiftid + "'";
		}
		
		//����
		if(owner_id != null && owner_id.trim().length() > 0){
			tmpstr += " and jobdetail.ownerId='" + owner_id + "'";
		}
		
		//�Ƿ񿪵�
		if(isinvoice != null && isinvoice.trim().length() > 0){
			tmpstr += " and jobdetail.isinvoice='" + isinvoice + "'";
		}
		
		return tmpstr;
	}
	/**
	 * ���ܣ����ݶ�����ϸid��ö���
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
	 * ����:�½���ⵥ
	 * @param warehouseid	�ֿ�
	 * @param whAreaId		����
	 * @param indate		����
	 * @param shiftid		���
	 * @param strKey		��ϸkey�ļ���
	 * @param lsLot 		�½���ⵥ��Ӧ�������������ü� 
	 * @param strUserCode 	�û�id  
	 * @return 
	 * @throws Exception
	 */
	public String addInboundInvoice(String warehouseid, String whAreaId, String indate, String shiftid, String strKey, 
			List<BaseLotSet> lsLot, String strUserCode) throws Exception {
		
		String strBackMsg = "Y";
		
		//��ⵥ����������(����ͬʱ������ⵥ�ݣ�ÿ��ֻ��һ������������ⵥ��)
		synchronized (newin_lock) {
			
			InboundHeader inInvoice = null;	//��ⵥ
			InboundDetail inDetail = null;	//��ⵥ��ϸ
			InoutJobdetail jobDetail = null; 		//�������ҵ��ϸ
			
			String strInvoiceNo = "";		//��ⵥ�ݺ�
			String strtempInvoiceNo = "";	//������һ����ⵥ�ݺ�
			String jobtype = "";      		//��ҵ����
			String ownerId = "";			//����
			String sinvoiceid = "";			//��Դ���ݺ�
	        String productid = "";        	//Ʒ��
			String strJobtype = "";			//��ҵ����(�����Ѿ����ɹ����ݵ���ҵ����)
			String strOwnerId = "";			//����(�����Ѿ����ɹ����ݵĻ���)
			String strSinvoiceid = "";		//��Դ���ݺ�(�����Ѿ����ɹ����ݵ���Դ���ݺ�)
			
	    	double fNum = 0;				//����
	        double fVolum = 0;				//���
	        double fWeight = 0;				//����
	        double fNetweight = 0;			//����
			
			int iline = 0;					//���浥����ϸ������
			String jobdetailids = "";		//������Ҫ���ɵ��ݵ�job��ϸid
			List<InboundDetail> lsinDetail = new ArrayList();	//������ϸ���б�
			
			String[] keys = strKey.split(",");
			String[] aem = null;
			List ls = null;
			String strSql = null;
			Object[] obj = null;
			
			
			try {
				for(int i = 0; i < keys.length; i++){
					aem = keys[i].split("\\|");
					
					productid = aem[0];        	//Ʒ��
				    jobtype = aem[1];      		//��ҵ����
					ownerId = aem[2];			//����
					sinvoiceid = aem[3];		//��Դ���ݺ�
		        	fNum = 0;					//����
		        	fVolum = 0;					//���
	            	fWeight = 0;				//����
	            	fNetweight = 0;				//����
				    
				    synchronized (productid) { //��Ʒ��������ֹ���˲���ͬһ������
				    	
				        //��ö�Ӧ����ҵ��ϸ
				    	strSql = getJobCreateInvoice(warehouseid, whAreaId, indate, shiftid, "", keys[i], lsLot);
				    	strSql += " and job.status ='4' and jobdetail.isinvoice='N'" +
				    			" order by job.jobid, jobdetail.jobdetailid ";
				    	ls = inboundDao.querySQL(strSql);
				    	
				        if(ls != null && ls.size() > 0) {
				        	
				        	//��֤�Ƿ������ɵ��ݹ�����ҵ,����ҵ���ͻ��������Դ���ݺŷ����仯ʱ�����µ���
				            if(i==0 || !strJobtype.equals(jobtype) || !strOwnerId.equals(ownerId) || !strSinvoiceid.equals(sinvoiceid)){
				            
				            	strJobtype = jobtype;
				            	strOwnerId = ownerId;
				            	strSinvoiceid = sinvoiceid;
				                
				            	iline = 0;
								jobdetailids = "";
				                lsinDetail = new ArrayList();
				            	
				                //��ⵥ��
				                BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(inboundDao.getEntityDAO());
				                BaseSeq  seqEn = seqDao.getSeqByType("RKD");
				                strInvoiceNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), inboundDao.getEntityDAO()); 
				                //��ⵥ
				                inInvoice = new InboundHeader();
				                inInvoice.setInstockid(strInvoiceNo);  		//��ⵥ���
				                inInvoice.setWarehouseid(warehouseid);		//�ֿ���
				                inInvoice.setInvoicedate(StrTools.getCurrDateTime(8));    //��������
				                inInvoice.setCreatetime(StrTools.getCurrDateTime(2));    //��������ʱ��
				                inInvoice.setCreatemanid(strUserCode);    	//����������Ա���
				                inInvoice.setInstatus("0");       	//����״̬ 0-�½���1-��ˣ�2-ȷ�� 5-���� 
				                inInvoice.setInvoicetypeid(jobtype);       //�������
				                inInvoice.setUploadflag("1");     	//�ϴ���־0-���ϴ���Ĭ�ϣ�1-δ�ϴ�
				                

				            }
	                        
				          	//���ɵ�����ϸ
				    		for(int j = 0; j < ls.size(); j++){
				    			
					        	obj = (Object[])ls.get(j);
								jobDetail = (InoutJobdetail)obj[1];
								jobdetailids += "'" + jobDetail.getJobdetailid() + "',";		//��ϸ�������Ӧ��jobdetailid
								
								//ͳ����ҵ��ϸ���������ͻ����ϵ������п��ܲ�һ�����ڻ���򿪵�ʱ�������µ���ҵ���ɣ�
								fNum += jobDetail.getJobnum();			//����
								fVolum += jobDetail.getVolume();		//���
	            				fWeight += jobDetail.getWeight();		//����
	            				fNetweight += jobDetail.getNetweight();	//����
							}
				    			
				            inDetail = new InboundDetail();
				            iline = iline + 1; //������ϸ����
				            
				            inDetail.setInstockdetailid(strInvoiceNo + SeqTool.getCode(iline, 2));	//��ⵥ��ϸID
				            inDetail.setInstockid(strInvoiceNo);	//��ⵥ�ݱ��
				            inDetail.setProductid(productid);		//Ʒ�����
				            inDetail.setPunit(aem[5]);				//��λ
				            inDetail.setInnum(fNum);				//�������ϼܣ�
				            inDetail.setLotid(aem[6]);				//��������ID
				            
				    		lsinDetail.add(inDetail);
				    		jobdetailids += "|";
				    		
				    		if(i==keys.length-1 || (!strtempInvoiceNo.equals("") && !strtempInvoiceNo.equals(strInvoiceNo))){

				    			strtempInvoiceNo = strInvoiceNo;
				    			
				            	//������һ����ⵥ����ϸ,�����޸���ҵΪ�����ɵ���
				            	if(inInvoice!=null ){
				            		
				            		inboundDao.createInvoice(inInvoice, lsinDetail, jobdetailids);
				            		Logger.info("�û���" + strUserCode + "����������ⵥ�����ݺţ�" + strInvoiceNo);
				            	}
				    		}
				         
				        }else{
				        
				            if(strBackMsg.equals("Y")){
				                strBackMsg = "ѡ�" + (i+1) + "�����������ݵ���ҵ���ݣ�";
				            }else{
				                strBackMsg += "\\rѡ�" + (i+1) + "�����������ݵ���ҵ���ݣ�";
				            }
				        }
				    }
				}
			} catch (Exception e) {
				e.printStackTrace();
				strBackMsg = "���ɵ���ʱ��������";
				return strBackMsg;
			}
		}
		
		return strBackMsg;
	}

	/**
	 * ����:��ⵥ����->��ѯ��ⵥ�б��sql��
	 * @param warehouseid	�ֿ�
	 * @param whAreaId		����
	 * @param indate_from	����
	 * @param indate_to		����
	 * @param shiftid		���
	 * @param owner_id		����
	 * @param instatus		����״̬
	 * @param intype		�������
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
			
			//�ֿ�
			if(warehouseid != null && warehouseid.trim().length() > 0){
				strSql += " and inbound.warehouseid='" + warehouseid + "'";
			}
			
			//����
			if(whAreaId != null && whAreaId.trim().length() > 0){
				strSql += " and inbound.tcargoWhareaId='" + whAreaId + "'";
			}
	
			//����
			if(indate_from != null && indate_from.trim().length() > 0){
				strSql += " and inbound.invoicedate>='" + indate_from + "'";
			}
			if(indate_to != null && indate_to.trim().length() > 0){
				strSql += " and inbound.invoicedate<='" + indate_to + "'";
			}
			
			//���
			if(shiftid != null && shiftid.trim().length() > 0){
				strSql += " and inbound.shiftid='" + shiftid + "'";
			}
			
			//����
			if(owner_id != null && owner_id.trim().length() > 0){
				strSql += " and inbound.ownerId='" + owner_id + "'";
			}
			
			//����״̬
			if(instatus != null && instatus.trim().length() > 0){
				strSql += " and inbound.instatus='" + instatus + "'";
			}
		
			//�������
			if(intype != null && intype.trim().length() > 0){
				strSql += " and inbound.intype='" + intype + "'";
			}
			
			String strHQL = strSql + " order by inbound.instockid";
			String strCountHQL = "select count(*)" + strSql;
			
			pt = CommonPagination.getPagingTool(inboundDao.getEntityDAO(), strCountHQL ,strHQL, strUrl, 1, maxLine);
			
		} catch (Exception er) {
			throw new Exception("����������ѯ��ѯ��ⵥ�б����:" + er.getMessage());
		}
				
		return pt;
		
	}

	/**
	 * ����:��ⵥ����->��ѯ��ⵥ��ϸ�б�
	 * @param instockid	��ⵥ�ݱ��
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
			throw new Exception("������->��ѯ��ⵥ��ϸ�б�ʱ�����" + er.getMessage());
		}
			
		return list;
	}

	/**
	 * ����:������ⵥ�ݱ�Ų�ѯ��ⵥ
	 * @param instockid	��ⵥ�ݱ��
	 * @return 
	 * @throws Exception
	 */
	public InboundHeader getInboundInvoice(String instockid) throws Exception {
		
		return inboundDao.getInboundHeader(instockid);
	}

	/**
	 * ����:������ⵥ
	 * @param instockid	��ⵥ�ݱ��
	 * @return 
	 * @throws Exception
	 */
	public void updateInboundInvoice(InboundHeader inbound) throws Exception {
		
		inboundDao.updateInboundInvoice(inbound);
	}

	/**
	 * ����:ɾ����ⵥ
	 * @param instockid	��ⵥ�ݱ��
	 * @return 
	 * @throws Exception
	 */
	public void deleteInboundInvoice(String instockid) throws Exception {
		
		inboundDao.deleteInboundInvoice(instockid);
	}

	/**
	 * ����:������ⵥ�ݱ�Ų�ѯ��ҵ��������
	 * @param instockdetailid	��ⵥ����ϸ���
	 * @return 
	 * @throws Exception
	 */
	public int getJobNumSum(String instockdetailid) throws Exception {
		
		return inboundDao.getJobNumSum(instockdetailid);
		
	}

	/**
	 * ����:���ϵ���
	 * @param instockid	��ⵥ�ݱ��
	 * @return 
	 * @throws Exception
	 */
	public void cancelInboundInvoice(String instockid) throws Exception {
		
		inboundDao.cancelInboundInvoice(instockid);
	}
	
	/**
	 * ����:������ϸ����
	 * @param instockdetailid	��ⵥ����ϸ���
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
