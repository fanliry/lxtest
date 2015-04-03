package com.wms3.bms.standard.business.outbound;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.base.BaseLotSet;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;
import com.wms3.bms.standard.entity.outerInterface.SaleFormDetail;

/**
 * ����: ���ⵥ����ӿ���
 * @author hug 2012-9-13
 * @since WMS 3.0
 */
public interface IOutBoundBus {
	//���ⵥ����������(����ͬʱ���ɳ��ⵥ�ݣ�ÿ��ֻ��һ���������ɳ��ⵥ��)
    public static byte[] newout_lock = new byte[0]; 

	/**
     * ���ܣ����ӳ��ⵥ
     * @author hug 2012-5-16 
     * @param invoice
     * @return
     * @throws Exception
     */
    public String addOutBound(OutboundInvoiceHeader invoice) throws Exception;
    /**
     * ����:���ӳ��ⵥ��ϸ
     * @author hug 2012-9-17 
     * @param outBoundDetail    ���ⵥ��ϸ
     * @param strInvoiceId      ���ⵥID
     * @return
     * @throws Exception
     */
    public String addOutBoundDetail(OutboundInvoiceDetail outBoundDetail, String strInvoiceId) throws Exception;
    /**
     * ����:�޸ĳ��ⵥ��ϸ
     * @author hug 2012-9-17 
     * @param outBoundDetail   ���ⵥ��ϸ
     * @param strInvoiceId     ���ⵥID
     * @return
     * @throws Exception
     */
    public String updateOutBoundDetail(OutboundInvoiceDetail outBoundDetail, String strInvoiceId) throws Exception;
    
    /**
     * ���ܣ��޸ĳ��ⵥ
     * @author hug 2012-5-16 
     * @param invoice
     * @return
     * @throws Exception
     */
    public String updateOutBound(OutboundInvoiceHeader invoice) throws Exception;
    
    /**
     * ����: ɾ�����ⵥ����ⵥ��ϸ
     * @author hug 2012-9-17 
     * @param strOutBoundId         ���ⵥID
     * @param strOutBoundDetailId   ���ⵥ��ϸID
     * @param strFlag               1��ɾ�����ⵥ��2��ɾ�����ⵥ��ϸ
     * @return
     * @throws Exception
     */
    public String deleteOutBoundInvoice(String strOutBoundId, String strOutBoundDetailId, String strFlag) throws Exception;
    
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
     * ���ܣ���ó��ⵥ��ѯ��SQL���
     * @author hug 2012-3-19 
     * @param warehouseid   �ֿ�ID
     * @param outstatus     ����״̬
     * @param outtype       ��������
     * @param shiftid       ��ҵ���
     * @param screatedate   ���ݿ�ʼ����ʱ��
     * @param ecreatedate   ���ݽ�������ʱ��
     * @param customerid    �ͻ�
     * @param outno         ���ⵥ��
     * @return
     */
    public String getOutBoundQuerySQL(String warehouseid, String outstatus, String outtype, String shiftid, String screatedate, String ecreatedate, String customerid, String outno);
    public String getOutBoundCountSQL(String warehouseid, String outstatus, String outtype, String shiftid, String screatedate, String ecreatedate, String customerid, String outno);
   
    /**
     * ����: �������->ͳ��Ԥ�������
     * @author hug 2012-9-21 
     * @param warehouseid   �ֿ�ID
     * @param whAreaId      ����ID
     * @param ownerid   ����ID
     * @param productid Ʒ��ID
     * @param packid    ��װID
     * @param lotatt1   ��������1
     * @param lotatt2   ��������2
     * @param lotatt3   ��������3
     * @param lotatt4   ��������4
     * @param lotatt5   ��������5
     * @param lotatt6   ��������6
     * @param lotatt7   ��������7
     * @param lotatt8   ��������8
     * @param lotatt9   ��������9
     * @param lotatt10  ��������10
     * @param lotatt11  ��������11
     * @param lotatt12  ��������12
     * @return
     * @throws Exception
     */
    public Object[] getGroupSoftallocationNum(String warehouseid, String whAreaId, String ownerid, String productid, String packid, String lotatt1, String lotatt2, String lotatt3, String lotatt4, String lotatt5, String lotatt6, String lotatt7, String lotatt8, String lotatt9, 
            String lotatt10, String lotatt11, String lotatt12) throws Exception;
    
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
			String customerid, String traycode, String lotinfo) throws Exception;
	
	/**
     * ���ܣ����ϳ��ⵥ
     * @param ids ���ⵥID
     * @return
     * @throws Exception
     */
	public String cancelInvoices(String ids, String strUserCode) throws Exception;
	/**
	 * ���ܣ���ó��ⵥ��ϸ
	 * @param jobDelList  ��ҵ��ϸ����
	 * @return
	 * @throws Exception
	 */
	public List<OutboundInvoiceDetail> getOutBoundDelByJobDelList(List jobDelList)throws Exception;
    /**
     * ���ܣ����ݳ��ⵥID�ͻ�ID�ж��Ƿ����������ҵ��ɾ�����ⵥ��ʹ�ã�
     * @param id ���ⵥid
     * @return
     * @throws Exception
     */
	public boolean isFinishJobByOutIdAndCId(String id)throws Exception;
	
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
    public String addOutBoundls(OutboundInvoiceHeader outInvoice,List<OutboundInvoiceDetail> outboundInvoiceDetails,List<SaleFormDetail> SaleFormDetaills) throws Exception;
    
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
