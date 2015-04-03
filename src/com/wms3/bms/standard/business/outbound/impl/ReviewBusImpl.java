package com.wms3.bms.standard.business.outbound.impl;

import java.util.List;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.outbound.IReviewBus;
import com.wms3.bms.standard.dao.job.IJobDao;
import com.wms3.bms.standard.dao.job.impl.JobDaoImpl;
import com.wms3.bms.standard.dao.outbound.IOutboundDao;
import com.wms3.bms.standard.dao.outbound.IReviewDao;
import com.wms3.bms.standard.dao.outbound.impl.OutboundDaoImpl;
import com.wms3.bms.standard.dao.outbound.impl.ReviewDaoImpl;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.outbound.OutboundCrosscheck;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;

/**
 * ����: ����ҵ����
 * @author hug 2012-10-19
 * @since WMS 3.0
 */
public class ReviewBusImpl implements IReviewBus {
    /** ���ⵥDAO��  */
    protected IOutboundDao outBoundDAO;
    /** ����DAO�� */
    protected IReviewDao reviewDAO;
    /** ��ҵDAO�� */
    protected IJobDao jobDAO;
    public ReviewBusImpl(EntityDAO dao){
        outBoundDAO = new OutboundDaoImpl(dao);
        reviewDAO = new ReviewDaoImpl(dao);
        jobDAO = new JobDaoImpl(dao);
    }
    /**
     * �����ݸ���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IReviewBus#invoiceReview(java.lang.String, java.lang.String, double, java.lang.String)
     */
    public String invoiceReview(String invoiceid, String invoicedetailid, double renum, String usercode) throws Exception {
        String strMsg = "�����ɹ�!";
        //ͬ�� 
        synchronized (invoicedetailid) {
            OutboundInvoiceDetail outDetail = outBoundDAO.getOutBoundDetailById(invoicedetailid);
            if(outDetail != null){
                double qtyUnChecked = 0;//outDetail.getAssignnum() - Double.parseDouble(outDetail.getReviewnum() == null ? "0":outDetail.getReviewnum());         //δ��������
                if(renum <= qtyUnChecked){//<=δ��������
                    //����
                    OutboundCrosscheck check = new OutboundCrosscheck();
                    check.setCrosstype("1");            //�������� 1:�����ݸ���; 2:���������ҵ����
                    check.setOutstockid(invoiceid);     //���ⵥID
                    check.setOutstockdetailid(invoicedetailid);//���ⵥ��ϸID
                    //������ҵID
                    //������ҵ��ϸID
                    //�ͻ�ID
                    //��ƷID
                    check.setQtyscan(renum);       //ɨ������
                    check.setChecktime(StrTools.getCurrDateTime(2));//����ʱ��
                    check.setCheckwho(usercode);   //������
                    
                    reviewDAO.addReview(check);
                }else{
                    strMsg = "����������(" + renum + ")�����ܴ���δ����������(" + qtyUnChecked + ")����";
                }
            }else{
                strMsg = "����[" + invoiceid + "]�ĵ�����ϸ[" + invoicedetailid + "]�����ڣ�";
            }    
        }
        return strMsg;
    }
    /**
     * ����ϸ����
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IReviewBus#detailReview(java.lang.String, java.lang.String, java.lang.String, java.lang.String, double, java.lang.String)
     */
    public String detailReview(String invoiceid, String invoicedetailid, String jobid, String jobdetailid, double renum, String usercode) throws Exception {
        String strMsg = "�����ɹ�!";
        synchronized (jobdetailid) {
            InoutJobdetail jobDetail = jobDAO.getJobDetailByDetailId(jobdetailid);
            if(jobDetail != null){
                //�Ѹ��˵�����
                OutboundCrosscheck check = reviewDAO.getOutboundCrosscheck(jobid, jobdetailid);
                if(check!= null){
                    double qtyUnChecked = jobDetail.getJobnum() - check.getQtyscan();
                    if( renum <= qtyUnChecked){
                        check.setQtyscan(check.getQtyscan()+renum);//����
                        check.setCheckwho(usercode);//������
                        check.setChecktime(StrTools.getCurrDateTime(2));//����ʱ��
                        reviewDAO.updateReview(check);//�޸ĸ�������
                    }else{
                        strMsg = "��ҵ��ϸ��"+jobdetailid+"���ġ���������(" + renum + ")�����ܴ���δ����������(" + qtyUnChecked + ")����";
                    }
                }else{
                    if(renum <= jobDetail.getJobnum()){
                        //����
                        check = new OutboundCrosscheck();
                        check.setCrosstype("2");            //�������� 1:�����ݸ���; 2:���������ҵ����
                        check.setOutstockid(invoiceid);     //���ⵥID
                        check.setOutstockdetailid(invoicedetailid);//���ⵥ��ϸID
                        check.setJobid(jobid);              //������ҵID
                        check.setJobdetailid(jobdetailid);  //������ҵ��ϸID
                        //�ͻ�ID
                        check.setProductid(jobDetail.getProductid());//��ƷID
                        check.setQtyscan(renum);       //ɨ������
                        check.setChecktime(StrTools.getCurrDateTime(2));//����ʱ��
                        check.setCheckwho(usercode);   //������
                        
                        reviewDAO.addReview(check);      
                    }else{
                        strMsg = "��ҵ��ϸ��"+jobdetailid+"���ġ���������(" + renum + ")�����ܴ���δ����������(" + jobDetail.getJobnum() + ")����";
                    }    
                }
            }else{
                strMsg = "��ҵ[" + jobid + "]����ҵ��ϸ[" + jobdetailid + "]�����ڣ�";
            } 
        }
        return strMsg;
    }
    /**
     * ȡ�����ݸ���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IReviewBus#deleteInvoiceReview(java.lang.String, java.lang.String)
     */
    public String deleteInvoiceReview(String invoicedetailid, String usercode) throws Exception {
        String strMsg = "�����ɹ�!";
        //ɾ�����˵�
        reviewDAO.deleteInvoiceReview(invoicedetailid);
        return strMsg;
    }
    /**
     * ȡ����ϸ����
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IReviewBus#deleteDetailReview(java.lang.String, java.lang.String)
     */
    public String deleteDetailReview(String strId, String usercode) throws Exception {
        String strMsg = "�����ɹ�!";
        //ɾ�����˵�
        reviewDAO.deleteReview(strId, "2");
        return strMsg;
    }
    /**
     * ���ݳ��ⵥID�����ҵ��ϸ�Ĳ�ѯ��SQL
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IReviewBus#getJobDetailQuerySQL(java.lang.String)
     */
    public String getJobDetailQuerySQL(String strInvoiceId) {
        StringBuilder strBud = new StringBuilder();
        strBud.append("select detail, (select chec.qtyscan from OutboundCrosscheck chec where chec.crosstype='2' and chec.jobdetailid=detail.jobdetailid and chec.jobid=detail.jobid) as checknum from InoutJobdetail as detail where detail.linestatus='4' and detail.invoiceid='")
        .append(strInvoiceId).append("' and (detail.assignnum - detail.reviewnum)!=0");
        //δ��������=��ҵ��ϸ�ķ�������-�Ѹ��˵�����  δ������������Ϊ0
        return strBud.toString();
    }
    /**
     * ���ݳ��ⵥID�����ҵ��ϸ�Ĳ�ѯ�ܼ�¼��SQL
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.outbound.IReviewBus#getJobDetailCountSQL(java.lang.String)
     */
    public String getJobDetailCountSQL(String strInvoiceId) {
        StringBuilder strBud = new StringBuilder();
        strBud.append("select count(detail.jobdetailid) from InoutJobdetail as detail where detail.linestatus='4' and detail.invoiceid='")
        .append(strInvoiceId).append("' and (detail.assignnum - detail.reviewnum)!=0");
        return strBud.toString();
    }
    
    public String getReviewQuerySQL(String strInvoiceId) {
        StringBuilder strBud = new StringBuilder();
        strBud.append("from OutboundCrosscheck chec where chec.crosstype='2' and chec.outstockid='")
        .append(strInvoiceId).append("'");
        return strBud.toString();
    }
    
    public String getReviewCountSQL(String strInvoiceId) {
        StringBuilder strBud = new StringBuilder();
        strBud.append("select count(chec.crosscheckid) from OutboundCrosscheck chec where chec.crosstype='2' and chec.outstockid='")
        .append(strInvoiceId).append("'");
        return strBud.toString();
    }
    /**
     * ���ܣ����ݳ��ⵥ��ϸID�жϸ�����Ϣ�Ƿ����
     * @param strId ���ⵥ��ϸID
     */
	public boolean getOutBoundCroByOutBoundDelId(String strId)
			throws Exception {
		List<OutboundCrosscheck> ls = null;
		ls = reviewDAO.getOutboundCrossById(strId);
		if (ls!=null && ls.size()>0) {
             return true;				
		}
		return false;
	}
	public boolean isCheckedByHeader(String orderId, String flag) throws Exception{
		return reviewDAO.isCheckedByHeader(orderId, flag);
	}
}
