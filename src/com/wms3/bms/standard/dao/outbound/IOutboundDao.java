package com.wms3.bms.standard.dao.outbound;

import java.util.List;

import com.wms3.bms.standard.dao.IDao;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;
import com.wms3.bms.standard.entity.outerInterface.SaleFormDetail;

/**
 * ����: �������DAO��ӿ�
 * @author hug 2012-9-13
 * @since WMS 3.0
 */
public interface IOutboundDao extends IDao{   
    /**
     * ���ܣ����ӳ��ⵥ
     * @author hug 2012-5-16 
     * @param invoice
     * @throws Exception
     */
    public void addOutBound(OutboundInvoiceHeader invoice) throws Exception;
    /**
     * ���ܣ����³��ⵥ
     * @author hug 2012-5-16 
     * @param invoice
     * @throws Exception
     */
    public void updateOutBound(OutboundInvoiceHeader invoice) throws Exception;
    /**
     * ����: ���ӳ��ⵥ��ϸ
     * @author hug 2012-9-17 
     * @param outBoundDetail    ���ⵥ��ϸ
     * @throws Exception
     */
    public void addOutBoundDetail(OutboundInvoiceDetail outBoundDetail) throws Exception;
    
    /**
     * ����: �޸ĳ��ⵥ��ϸ
     * @author hug 2012-9-17 
     * @param outBoundDetail    ���ⵥ��ϸ
     * @throws Exception
     */
    public void updateOutBoundDetail(OutboundInvoiceDetail outBoundDetail) throws Exception;
    
    /**
     * ���ܣ�ɾ�����ⵥ
     * @author hug 2012-8-17 
     * @param strOutBoundId      ���ⵥID
     * @throws Exception
     */
    public void deleteOutBound(String strOutBoundId) throws Exception;
    
    /**
     * ���ܣ�ɾ�����ⵥ��ϸ
     * @author hug 2012-8-17 
     * @param strDetailId       ���ⵥ��ϸID
     * @throws Exception
     */
    public void deleteOutBoundDetail(String strDetailId) throws Exception;
    
    /**
     * ���ܣ����ݳ��ⵥID��ó��ⵥ
     * @author hug 2012-5-16 
     * @param strOutBoundId ���ⵥID
     * @return
     * @throws Exception
     */
    public OutboundInvoiceHeader getOutBoundById(String strOutBoundId) throws Exception;
    /**
     * ����:���ݳ��ⵥ��ϸID��ó��ⵥ��ϸ
     * @author hug 2012-9-17 
     * @param strInvoiceDetailId    ���ⵥ��ϸID
     * @return
     * @throws Exception
     */
    public OutboundInvoiceDetail getOutBoundDetailById(String strInvoiceDetailId) throws Exception;
    /**
     * ����:��ȡû����ȫ����ĳ����Ʒ��Ϣ
     * @author yao 2015-3-11
     * @return
     * @throws Exception
     */
    public List getOutBoundProudctInfo() throws Exception; 
    /**
     * ���ܣ����ݳ��ⵥID��ó��ⵥ��ϸ
     * @author hug 2012-5-16 
     * @param strInvoiceId  ���ⵥID
     * @return
     * @throws Exception
     */
    public List<OutboundInvoiceDetail> getOutBoundDetailsById(String strInvoiceId) throws Exception;
    
    /**
     * ����:���ݲֿ�ID�����Ҫ����ȷ�ϵĳ��ⵥ
     * @author hug 2012-9-18 
     * @param warehouseid   �ֿ�ID
     * @return
     * @throws Exception
     */
    public List<OutboundInvoiceHeader> getSendOutInvoice(String warehouseid) throws Exception;
    /**
     * ����:���ݲֿ�ID�����Ҫ����ȷ�ϵ�B�ͻ��ĳ��ⵥ
     * @author hug 2012-9-18 
     * @param warehouseid   �ֿ�ID
     * @param aoutid        A�ͻ�����ID
     * @return
     * @throws Exception
     */
    public List<OutboundInvoiceHeader> getSendOutInvoice(String warehouseid, String aoutid) throws Exception;
    /**
     * ���ܣ����ݳ��ⵥ��id��ó����Ѿ������ҵ
     * @return List<InoutJobdetail>
     * @throws Exception
     */
    public List<InoutJobdetail> getFinishJobByOutIdCid(String outId)throws Exception;
    /**
     * ���ܣ����ݳ��ⵥ��id��ó����Ѿ������ҵ
     * @return List<InoutJobdetail>
     * @throws Exception
     */
    public List<InoutJob> getFinishJobByIdCid(String outId)throws Exception;
    /**
	 * ���ܣ��ݴ�ֱ�ӳ���ʱ���޸ĵ�����
	 * @param job
	 * @param jobDetail
	 * @param inventory
	 * @throws Exception
	 */
    public String updateInventoryAndJob(String jobID, String jobDetailID,String inventoryID) throws Exception;
    
    /**
     * ���ܣ����ӳ��ⵥ
     * @author fanll 2015-3-8
     * @param invoice
     * @param outboundInvoiceDetails
     * @return
     * @throws Exception
     */
    public String addOutBound(OutboundInvoiceHeader outInvoice,List<OutboundInvoiceDetail> outboundInvoiceDetails,List<SaleFormDetail> SaleFormDetaills) throws Exception;
    
    /**
     * ���ܣ�ȡ�����ⵥ
     * @author fanll 2015-3-8
     * @param outInvoice
     * @param SaleFormDetaills
     * @return
     * @throws Exception
     */
    public String CancelInvoice(OutboundInvoiceHeader outInvoice,List<SaleFormDetail> SaleFormDetaills) throws Exception;
    
}
