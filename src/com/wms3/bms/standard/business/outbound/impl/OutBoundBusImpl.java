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
 * ����: ���ⵥ����ҵ����
 * @author hug 2012-9-13
 * @since WMS 3.0
 */
public class OutBoundBusImpl implements IOutBoundBus{
    /** ���ⵥDAO��  */
    protected IOutboundDao outBoundDAO;
    /** ��ҵDAO��  */
    protected IJobDao jobDao;
    
    public OutBoundBusImpl(EntityDAO dao){
        outBoundDAO = new OutboundDaoImpl(dao);
        jobDao = new JobDaoImpl(dao); 
    }
    /**
     * ���ӳ��ⵥ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IOutBoundBus#addOutBound(com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader)
     */
    public String addOutBound(OutboundInvoiceHeader invoice) throws Exception {
        //��õ���ID
    	BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(outBoundDAO.getEntityDAO());
        BaseSeq  seqEn = seqDao.getSeqByType("CKD");
        String strID = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), outBoundDAO.getEntityDAO());
        invoice.setOutstockid(strID);
        //���ӳ��ⵥ
        outBoundDAO.addOutBound(invoice);
        return strID;
    }
    /**
     * �޸ĳ��ⵥ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IOutBoundBus#updateOutBound(com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader)
     */
    public String updateOutBound(OutboundInvoiceHeader invoice) throws Exception {
        String strMsg = "�����ɹ�!";
        //ͬ��  �ջ�����
        synchronized (invoice.getOutstockid()) {
            if(invoice.getOutstatus().equals("0")){
                outBoundDAO.updateOutBound(invoice);
            }else{
                 strMsg = "���ⵥ[" + invoice.getOutstockid()  + "]״̬��" + invoice.getOutstatus() + "����Ϊ����״̬1���޷��޸�!";
            }         
        }
        return strMsg;  
    }
    /**
     * ���ӳ��ⵥ��ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IOutBoundBus#addOutBoundDetail(com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail, java.lang.String)
     */
    public String addOutBoundDetail(OutboundInvoiceDetail outBoundDetail, String strInvoiceId) throws Exception {
        String strMsg = "�����ɹ�!";
        //ͬ��  ���ⵥ��
        synchronized (strInvoiceId) {
            //���ⵥ
            OutboundInvoiceHeader invoice = getOutBoundById(strInvoiceId);
            if(invoice != null){
                if(invoice.getOutstatus().equals("0")){     
                    outBoundDAO.addOutBoundDetail(outBoundDetail);
                }else{
                        strMsg = "���ⵥ[" + strInvoiceId  + "]״̬��" + invoice.getOutstatus() + "����Ϊ����״̬0���޷��������ⵥ��ϸ!";
                }    
            }     
        }
        return strMsg;
    }
    /**
     * �޸ĳ��ⵥ��ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IOutBoundBus#updateOutBoundDetail(com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail, java.lang.String)
     */
    public String updateOutBoundDetail(OutboundInvoiceDetail outBoundDetail, String strInvoiceId) throws Exception {
        String strMsg = "�����ɹ�!";
        //ͬ��  ���ⵥ��
        synchronized (strInvoiceId) {
            //���ⵥ
            OutboundInvoiceHeader invoice = getOutBoundById(strInvoiceId);
            if(invoice != null){
                //���ⵥ��״̬
                //���ⵥ��ϸ��״̬
                if(invoice.getOutstatus().equals("0") && outBoundDetail.getLinestatus().equals("0")){     
                    outBoundDAO.updateOutBoundDetail(outBoundDetail);
                }else{
                    strMsg = "���ⵥ[" + strInvoiceId  + "]״̬��" + invoice.getOutstatus() + "������ⵥ��ϸ״̬(" + outBoundDetail.getLinestatus() + ")��Ϊ����״̬0���޷��޸ĳ��ⵥ��ϸ!";
                }    
            }     
        }
        return strMsg;
    }
    /**
     * ɾ�����ⵥ����ⵥ��ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IOutBoundBus#deleteOutBoundInvoice(java.lang.String, java.lang.String, java.lang.String)
     */
    public String deleteOutBoundInvoice(String strOutBoundId, String strOutBoundDetailId, String strFlag) throws Exception {

        String strMsg = "�����ɹ�!";
        //ͬ��  ���ݺ�
        synchronized (strOutBoundId) { 
            OutboundInvoiceHeader invoice = getOutBoundById(strOutBoundId);
            if(invoice != null){
                if(strFlag != null && strFlag.trim().equals("1")){ //1��ɾ������
                    if(invoice.getOutstatus().equals("0")&& !isFinishJobByOutIdAndCId(strOutBoundId)){//����״̬Ϊ����״̬;�Ƿ�����������ҵ
                        outBoundDAO.deleteOutBound(strOutBoundId);   
                    }else{
                        strMsg = "����[" + strOutBoundId  + "]״̬��" + invoice.getOutstatus() + "����Ϊ����״̬0������ �����Ѿ���ɵ���ҵ������ɾ�����ⵥ��";
                    }
                }else if(strFlag != null && strFlag.trim().equals("2")){ //2��ɾ��������ϸ
                    //���ⵥ��ϸ��״̬  
                    OutboundInvoiceDetail outBoundDetail = getOutBoundDetailById(strOutBoundDetailId);
                    if(outBoundDetail != null){
                        if(outBoundDetail.getLinestatus().equals("0")){//Ϊ����״̬ʱ����ɾ��
                            outBoundDAO.deleteOutBoundDetail(strOutBoundDetailId);  
                        }else{
                            strMsg = "���ⵥ[" + strOutBoundId  + "]�ĳ�����ϸ[" + strOutBoundDetailId + "]״̬��" + outBoundDetail.getLinestatus() + "����Ϊ����״̬0������ɾ�����ⵥ��ϸ��";
                        }     
                    }else{
                        strMsg = "���ⵥ[" + strOutBoundId  + "]�ĳ��ⵥ��ϸ[" + strOutBoundDetailId + "]������,�޷���ɾ��������";
                    }
                }
            }else{
                strMsg = "���ⵥ[" + strOutBoundId  + "]������,�޷���ɾ��������";
            }
        } 
        return strMsg;
    }
    /**
     * ���ݳ��ⵥID��ó��ⵥ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IOutBoundBus#getOutBoundById(java.lang.String)
     */
    public OutboundInvoiceHeader getOutBoundById(String strOutBoundId) throws Exception {
        return outBoundDAO.getOutBoundById(strOutBoundId);
    }
    /**
     * ���ݳ��ⵥ��ϸID��ó��ⵥ��ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IOutBoundBus#getOutBoundDetailById(java.lang.String)
     */
    public OutboundInvoiceDetail getOutBoundDetailById(String strInvoiceDetailId) throws Exception {
        return outBoundDAO.getOutBoundDetailById(strInvoiceDetailId);
    }
    /**
     * ����:��ȡû����ȫ����ĳ����Ʒ��Ϣ
     * @author yao 2015-3-11
     * @return
     * @throws Exception
     */
    public List getOutBoundProudctInfo() throws Exception{
    	return outBoundDAO.getOutBoundProudctInfo();
    }
    /**
     * ���ݳ��ⵥID��ó��ⵥ��ϸ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IOutBoundBus#getOutBoundDetailsById(java.lang.String)
     */
    public List<OutboundInvoiceDetail> getOutBoundDetailsById(String strInvoiceId) throws Exception {
        return outBoundDAO.getOutBoundDetailsById(strInvoiceId);
    }
    /**
     * ���ݲֿ�ID�����Ҫ����ȷ�ϵĳ��ⵥ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IOutBoundBus#getSendOutInvoice(java.lang.String)
     */
    public List<OutboundInvoiceHeader> getSendOutInvoice(String warehouseid) throws Exception {
        return outBoundDAO.getSendOutInvoice(warehouseid);
    }
    /**
     * ���ݲֿ�ID�����Ҫ����ȷ�ϵ�B�ͻ��ĳ��ⵥ
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
     * �������->ͳ��Ԥ�������
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
        //�ֿ�ID
        if(warehouseid != null && warehouseid.trim().length() > 0){
            strBud.append(" and sto.warehouseid='").append(warehouseid).append("'");
        }
        //����ID
        if(whAreaId != null && whAreaId.trim().length() > 0){ 
            strBud.append(" and sto.whAreaId='").append(whAreaId).append("'");
        }
        //����
        if(ownerid != null && ownerid.trim().length()>0){
            strBud.append(" and sto.ownerId='").append(ownerid).append("'");
        } 
        //Ʒ��ID
        if(productid != null && productid.trim().length() > 0){
            strBud.append(" and sto.productid='").append(productid).append("'");
        }
        //��װ
        if(packid != null && packid.trim().length() > 0){
            strBud.append(" and sto.packid='").append(packid).append("'");
        }
        //��������1
        if(lotatt1 != null && lotatt1.trim().length() > 0){
            strBud.append(" and sto.lotatt1='").append(lotatt1).append("'");
        }
        //��������2
        if(lotatt2 != null && lotatt2.trim().length() > 0){
            strBud.append(" and sto.lotatt2='").append(lotatt2).append("'");
        }
        //��������3
        if(lotatt3 != null && lotatt3.trim().length() > 0){
            strBud.append(" and sto.lotatt3='").append(lotatt3).append("'");
        }
        //��������4
        if(lotatt4 != null && lotatt4.trim().length() > 0){
            strBud.append(" and sto.lotatt4='").append(lotatt4).append("'");
        }
        //��������5
        if(lotatt5 != null && lotatt5.trim().length() > 0){
            strBud.append(" and sto.lotatt5='").append(lotatt5).append("'");
        }
        //��������6
        if(lotatt6 != null && lotatt6.trim().length() > 0){
            strBud.append(" and sto.lotatt6='").append(lotatt6).append("'");
        }
        //��������7
        if(lotatt7 != null && lotatt7.trim().length() > 0){
            strBud.append(" and sto.lotatt7='").append(lotatt7).append("'");
        }
        //��������8
        if(lotatt8 != null && lotatt8.trim().length() > 0){
            strBud.append(" and sto.lotatt8='").append(lotatt8).append("'");
        }
        //��������9
        if(lotatt9 != null && lotatt9.trim().length() > 0){
            strBud.append(" and sto.lotatt9='").append(lotatt9).append("'");
        }
        //��������10
        if(lotatt10 != null && lotatt10.trim().length() > 0){
            strBud.append(" and sto.lotatt10='").append(lotatt10).append("'");
        }
        //��������11
        if(lotatt11 != null && lotatt11.trim().length() > 0){
            strBud.append(" and sto.lotatt11='").append(lotatt11).append("'");
        }
        //��������12
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
	 * ����:������ѯ
	 * @param warehouseid	�ֿ�
	 * @param whAreaId		����
	 * @param senddate_from	��������
	 * @param senddate_to	��������
	 * @param shiftid		���
	 * @param productid		Ʒ��
	 * @param customerid	�ͻ�
	 * @param tray_code		��������
	 * @param lotid			��������id
     * @param lotatt1   	��������1
     * @param lotatt2   	��������2
     * @param lotatt3   	��������3
     * @param lotatt4   	��������4
     * @param lotatt5   	��������5
     * @param lotatt6   	��������6
     * @param lotatt7   	��������7
     * @param lotatt8   	��������8
     * @param lotatt9   	��������9
     * @param lotatt10  	��������10
     * @param lotatt11  	��������11
     * @param lotatt12  	��������12
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
			
			if(warehouseid != null && warehouseid.trim().length() > 0){		//�ֿ�
				strBudCon.append(" and job.warehouseid='").append(warehouseid).append("'");
			}
			
			if(whAreaId != null && whAreaId.trim().length() > 0){		//����
				strBudCon.append(" and job.tcargoWhareaId='").append(whAreaId).append("'");
			}
			
			if(productid != null && productid.trim().length() > 0){		//Ʒ��
				strBudCon.append(" and jobdetail.productid='").append(productid).append("'");
			}
			
			if(customerid != null && customerid.trim().length() > 0){	//�ͻ�
				strBudCon.append(" and invoice.customerid='").append(customerid).append("'");
			}

			if(shiftid != null && shiftid.trim().length() > 0){		//���
				strBudCon.append(" and job.shiftid='").append(shiftid).append("'");
			}
			
			if(traycode != null && traycode.trim().length() > 0){		//��������
				strBudCon.append(" and job.traycode='").append(traycode).append("'");
			}
			
			if(lotinfo != null && lotinfo.trim().length() > 0){		//��������
				strBudCon.append(" and jobdetail.lotinfo='").append(lotinfo).append("'");
			}
			
			if(senddate_from != null && senddate_from.trim().length() > 0){		//��������
				strBudCon.append(" and invoice.formdate>='").append(senddate_from).append("'");
			}
			
			if(senddate_to != null && senddate_to.trim().length() > 0){		//��������
				strBudCon.append(" and invoice.formdate<='").append(senddate_to).append("'");
			}
	        
	        strBud.append(strBudCon).append(strBudGrp);
			
			strSqls[0] = "select count(*) " + strBudCon.toString();
            strSqls[1] = strBud.toString() + " order by invoice.outstockid";
			
		} catch (Exception er) {
			throw new Exception("ƴ�ӷ�����ѯ�Ĳ�ѯ���ʱ�����:" + er.getMessage());
		}
				
		return strSqls;
	}
	
	/**
     * ����:����������Ե�SQL
     * @param flg		�ڼ�����������
     * @param lotatt	�������Ե�ֵ
     * @param lotDetail	��������
     * @return
     * @throws Exception
     */
	private String getSqlLotatt(String flg,	String lotatt, BaseLotDetail lotDetail) {
		
        StringBuilder strBud = new StringBuilder();	
		if(lotDetail != null){
			
			//ҳ���ѯʱ���ģʽ 1-��ȷ��ѯ,2-ģ����ѯ(�ı���ʽ),3-��Χ��ѯ(���ڸ�ʽ)
			String strLotsearch = lotDetail.getM_strLotsearch();	
		    
		    if(strLotsearch != null && strLotsearch.equals("1")){       //1-��ȷ��ѯ
                strBud.append(" and jobdetail.lotatt").append(flg).append(" ='").append(lotatt).append("'");   	
		    }else if(strLotsearch != null && strLotsearch.equals("2")){  //2-ģ����ѯ
                strBud.append(" and jobdetail.lotatt").append(flg).append(" like '%").append(lotatt).append("%'");        
		    }else if(strLotsearch != null && strLotsearch.equals("3")){  //3-��Χ��ѯ(���ڸ�ʽ)    	
	    		String[] lotatts =  lotatt.split("\\|");
		        if(lotatts.length>0 && lotatts[0] !=null && lotatts[0].trim().length() > 0){
                    strBud.append(" and jobdetail.lotatt").append(flg).append(" >='").append(lotatts[0]).append("'");
		        }
		        if(lotatts.length>1 && lotatts[1] !=null && lotatts[1].trim().length() > 0){
                    strBud.append(" and jobdetail.lotatt").append(flg).append(" <='").append(lotatts[1]).append("'");
		        }
		        
		    }else{		//û��ѡ���ѯ��ʽ��ʱ�򣬰��վ�ȷ��ѯ������
                strBud.append(" and jobdetail.lotatt").append(flg).append(" ='").append(lotatt).append("'");
		    }
		}
		return strBud.toString();
	}
	
	/**
     * ���ܣ����ϳ��ⵥ
     * @param ids ���ⵥID
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
				
				outboundinvoice = getOutBoundById(outstockid[i]);	//���ⵥ��
				lsDetail = getOutBoundDetailsById(outstockid[i]);	//���ⵥ����ϸ
				boolean statusflg = false;
				
				//�ж�״̬ 0:������2-����  7:����ȷ�� 8:����
				if(outboundinvoice.getOutstatus().equals("0")){
					statusflg = false;
					
				}else if(outboundinvoice.getOutstatus().equals("2")) //�����ɲ��������ϣ��������ҵ��������
                {
                    //����״̬, ��ҵ�������ϣ��ǿ������ϵ��ݵġ�
                    int iCount = jobDao.noCancelJob(outboundinvoice.getOutstockid(), null);
                    if(iCount >0){ //��������
                        statusflg = true;
                    }else{ //��������
                        statusflg = false;
                    }
                }else{
					statusflg = true;
				}
				
				if(statusflg){
					
					if(strBackMsg.equals("Y")){
						strBackMsg = "�������Ϸǿ���״̬�ĵ��ݣ�";
						
		 			}else {
		 				strBackMsg += "\r�������Ϸǿ���״̬�ĵ��ݣ�";
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
					
					
					Logger.info("�û���" + strUserCode + "���ڵ��ݹ���ģ���������˳��ⵥ�ݣ�" + outstockid[i]);
				}
			}
		}catch (Exception e) {
		
			e.printStackTrace();
			throw new Exception("���ϳ��ⵥ��ʱ�����:" + e.getMessage());
		}
		
		return strBackMsg;
	}
/*	*//**
	 * ���ܣ���ó��ⵥ��ϸ
	 * @param jobDelList  ��ҵ��ϸ����
	 * @return
	 * @throws Exception
	 *//*
	public List<OutboundInvoiceDetail> getOutBoundDelByJobDelList(
			List jobDelList) throws Exception {
		List<OutboundInvoiceDetail> outBoundLs = new ArrayList<OutboundInvoiceDetail>();
		String outBoundId = null;	//���ⵥID
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
			throw new Exception("OutBoundBusImpl.getOutBoundDelByJobDelList:������ҵ��ϸ���ϻ������ⵥ��ϸ����:" + e.getMessage());
		}		
		return null;
	}*/
	 /**
     * ���ܣ����ݳ��ⵥID�ж��Ƿ����������ҵ��ɾ�����ⵥ��ʹ�ã�
     * @param id ���ⵥid
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
			throw new Exception("OutBoundBusImpl.isFinishJobByOutIdAndCId:���ݳ��ⵥID�ж��Ƿ����������ҵ����:" + e.getMessage());
		}
		return false;
	}
	public List<OutboundInvoiceDetail> getOutBoundDelByJobDelList(
			List jobDelList) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * ���ܣ��ݴ�ֱ�ӳ���ʱ���޸ĵ�����
	 * @param job
	 * @param jobDetail
	 * @param inventory
	 * @throws Exception
	 */
	public String updateInventoryAndJob(String jobID, String jobDetailID,String inventoryID) throws Exception{
		
		return outBoundDAO.updateInventoryAndJob(jobID, jobDetailID, inventoryID);
	}
	
    /**
     * ���ܣ����ӳ��ⵥ
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
     * ���ܣ�ȡ�����ⵥ
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
