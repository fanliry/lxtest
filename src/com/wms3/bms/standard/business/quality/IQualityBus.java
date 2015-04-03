package com.wms3.bms.standard.business.quality;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.constant.CommonReturn;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;
/**
 * �������ʼ����ҵ��ӿ�
 * @author liuxh
 *
 */
public interface IQualityBus {
	/**
	 * ���ܣ��ʼ������У����飩
	 *@author liuxh 2013-3-7
	 *@param warehouseid �ֿ�
	 *@param whareaid  ����
	 *@param lotnumber ����
	 *@param requestid ���뵥��
	 *@param productid ��Ʒid
	 *@param productstatus ��Ʒ״̬
	 *@param isgroup �Ƿ����η���
	 *@return
	 *@throws Exception
	 */
	public String searchInventoryForLotnumber(String warehouseid,String whareaid,String lotnumber,String requestid,String productid,String productstatus,String productCode)throws Exception;
	/**
	 * ���ܣ���ѯĳ�������µ���ⵥ
	 *@author liuxh 2013-3-7
	 *@param warehouseid  �ֿ�id
	 *@param lotnumber    ����
	 *@param requestid    ���뵥id
	 *@param productid    ��Ʒid
	 *@param productstatus     ��Ʒ״̬
	 *@return
	 *@throws Exception
	 */
	public String searchInventroyForInstock(String warehouseid,String lotnumber,String requestid,String productid,String productstatus,String whareaid)throws Exception;
	/**
	 * ���ܣ���ѯ��ⵥ��ϸ
	 *@author liuxh 2013-3-7
	 *@param warehouseid
	 *@param instockid
	 *@param lotnumber
	 *@param productid
	 *@return
	 *@throws Exception
	 */
	public List<InboundDetail> searchInStockDel(String instockid,String productid)throws Exception;
    /**
     *  ���ܣ�����״̬���ӷ��м�¼
     *@author liuxh 2013-3-8
     *@param usercode   �û�id
     *@param warehouseid  �ֿ�id
     *@param whareaid     ����id
     *@param lotnumber    ����
     *@param requestid    ���뵥id
     *@param productid    ��Ʒid
     *@param productstatus  ��Ʒ״̬
     *@param strwpzt       �µĲ�Ʒ״̬
     *@return
     *@throws Exception
     */
	public CommonReturn updateStatusAddRecord(String usercode,String warehouseid,String whareaid,String lotnumber,String requestid,String productid,String productstatus,
			String strwpzt)throws Exception;
	/**
	 * ����:�ʼ����->����ѯ����SQL
	 * @param warehouseid	�ֿ�ID
	 * @param cargoAreaId	����ID
	 * @param whAreaId		����ID
	 * @param platoon		��
	 * @param column		��
	 * @param floor			��
	 * @param customerId    ����
	 * @param indate_from   �������from
	 * @param indate_to     �������to
	 * @param productid		��Ʒid
	 * @param traycode		��������
	 * @param lotid		          ����ID
	 * @param lotatt1-lotatt12 ��������
	 * @param strUrl 		��ַ
	 * @param maxLine 		��ҳ��ʾ����
	 * @return
	 * @throws Exception
	 */
    public PagingTool getKcSelect(String warehouseId, String whAreaId, String cargoAreaId, String platoon, String column, String floor, 
    		String customerId,String indate_from ,String indate_to,String productid, String traycode, String lotid, String lotatt1, String lotatt2, String lotatt3,String lotatt4, String lotatt5, 
			String lotatt6,String lotatt7, String lotatt8, String lotatt9,String lotatt10, String lotatt11, String lotatt12, String strUrl,int maxLine) throws Exception;
    /**
     * ����:���ݿ��ID��ȡ�����Ϣ
     * @param inventId ���ID
     * @return
     * @throws Exception
     */
    public InventoryStorage getInventById(String inventId)throws Exception;
    /**
     * ���ܣ����ӳ����ⵥ
     * @param departId ����id
     * @param formdate ��������
     * @param createmanid ����Ա
     * @param customerId �ͻ�
     * @param ownerId ����
     * @param warehouseManId �ֹ�Ա 
     */
    public OutboundInvoiceHeader addpOutboundInvoiceHeader(String warehouseId,String departId,String formdate,String createmanid,String customerId,String ownerId,String warehouseManId)throws Exception;
    /**
     * ���ܣ����ӳ����ⵥ��ϸ������ҵ��������ҵ��ϸ
     * @param OpManId ����Ա
     * @param inventId ���ID
     * @param outInvoiceId ���ⵥ��ID
     * @param outJob  ������ҵID
     * @param Num    �������
     * @param CheckNum  �������
     * @param customerId �ͻ�ID
     * @return
     * @throws Exception
     */
    public Object[] addOutInvoiceDelAddOutJob(String OpManId,String inventId,String outInvoiceId,String outJob,double Num,double CheckNum,String customerId,String i)throws Exception;
    /**
     * ���ܣ����� �����ⵥ�ݣ������ⵥ��ϸ����������ҵ����������ҵ��ϸ���¿��
     * @param outInvoiceHeader �����ⵥ
     * @param outJob ������ҵ
     * @param lObjects 
     * @return
     * @throws Exception
     */
    public String addBoundAddBoundDelAddJobAddJobDel(OutboundInvoiceHeader outInvoiceHeader,List<Object[]> delObj)throws Exception;
    
    public PagingTool getLsRelease(String Productid, String lotinfo, String Qualityid, String createtime, String strUrl, int maxLine) throws Exception;
    
}

