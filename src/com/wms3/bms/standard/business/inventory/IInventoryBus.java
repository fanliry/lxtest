package com.wms3.bms.standard.business.inventory;

import java.util.List;

import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;

/**
 * ����: ����ѯҵ��ӿ�
 * @author fangjie
 * 
 */
public interface IInventoryBus {
    
    /**
     * ����:������->����ƶ���SQL���
     * @param warehouseid       �ֿ�ID
     * @param whAreaId          ����ID
     * @param cargoAlleyId      ���ID
     * @param platoon           ��
     * @param column            ��
     * @param floor             ��
     * @param customer_id       ����
     * @param package_id        Ʒ�����
     * @param tray_code         ��������
     * @param lotatt1, lotatt2, lotatt3, lotatt4, lotatt5, lotatt6      ��������
     * @param lotatt7, lotatt8, lotatt9, lotatt10, lotatt11, lotatt12   �������� 
     * @param sttLotId          ����ID
     * @param tray_code         ��������
     * @param indate_from       �������from
     * @param indate_to         �������to
     * @return
     * @throws Exception
     */
    public String getInventoryHQL(String warehouseid, String whAreaId, String cargoAlleyId, String platoon, String column, String floor, 
            String package_id, String tray_code,String indate_from ,String indate_to,String productcode) throws Exception;
/*	 *//**
	  * ����:�ʼ����->״̬ת����SQL���
	  *@author liuxh 2013-3-8
	  *@param warehouseid �ֿ�id
	  *@param whareaid    ����
	  *@param lotnumber   ����
	  *@param requestid   ���뵥
	  *@param productid   ��Ʒid
	  *@param productstatus ��Ʒ״̬
	  *@return
	  *@throws Exception
	  *//*
    public String getInventoryStatusHQL(String warehouseid,String whareaid,String lotnumber,String requestid,String productid,String productstatus) throws Exception;*/
    /**
     * ����:������->���ͳ�Ƶ�SQL���
     * @param warehouseid       �ֿ�ID
     * @param whAreaId          ����ID
     * @param customer_id       ����
     * @param package_id        Ʒ��
     * @param indate_from, indate_to      �������
     * @param lotatt1, lotatt2, lotatt3, lotatt4, lotatt5, lotatt6      ��������
     * @param lotatt7, lotatt8, lotatt9, lotatt10, lotatt11, lotatt12   �������� 
     * @param sttLotId          ����ID
     * @param total_items       ͳ����Ŀ
     * @return
     * @throws Exception
	 */
	public String[] getInventoryTotalQuery(String warehouseid, String whAreaId, String customer_id, String package_id, String indate_from, String indate_to, 
			String total_items) throws Exception;
    
    /**
     * ����: �������->ͳ�ƿ�������
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
    public Object[] getGroupInventoryNum(String warehouseid, String whAreaId, String ownerid, String productid, String packid) throws Exception;
    /**
     * ���ܣ�����ѯ��sql
     *@author liuxh 2013-3-12
     *@param warehouseid �ֿ�
     *@param whAreaId    ����
     *@param cargoAlleyId ���
     *@param platoon      ��
     *@param column       ��
     *@param floor        ��
     *@param lotnumber    ����
     *@param productid  ��Ʒid
     *@param indate_from ��ʼʱ��
     *@param indate_to   ����ʱ��
     *@param tray_code   ��������
     *@param status      ״̬
     *@param requestid   ���뵥
     *@param instockid   ��ⵥ
     *@param productstatus   ��Ʒ״̬
     *@param productcode   ��Ʒ����
     *@param kcstatus   ���״̬
     *@return
     *@throws Exception
     */
	public String[] getInventoryQuerynew(String warehouseid, String whAreaId,
			String cargoAlleyId, String platoon, String column, String floor,
			String lotnumber, String productid, String indateFrom,
			String indateTo, String trayCode, String status, String requestid,
			String instockid, String productstatus, String productcode,
			String kcstatus) throws Exception;
	/**
	 * ���ⱸ��->�����ż������->�ݴ��б�
	 * @param warehouseid
	 * @param whAreaId
	 * @param Virtualwhid
	 * @param productid
	 * @param lotinfo
	 * @param printdate
	 * @param trayCode
	 * @return
	 * @throws Exception
	 */
	public List<InventoryStorage> getZCInventoryByCKInfo(String warehouseid, String whAreaId,String Virtualwhid,String productid,String lotinfo,String printdate, String trayCode) throws Exception;
    /**
     * ���ܣ�����ѯ��sql
     *@author liuxh 2013-3-12
     *@param warehouseid �ֿ�
     *@param whAreaId    ����
     *@param cargoAlleyId ���
     *@param platoon      ��
     *@param column       ��
     *@param floor        ��
     *@param lotnumber    ����
     *@param productid  ��Ʒid
     *@param indate_from ��ʼʱ��
     *@param indate_to   ����ʱ��
     *@param tray_code   ��������
     *@param status      ״̬
     *@param requestid   ���뵥
     *@param instockid   ��ⵥ
     *@param productstatus   ��Ʒ״̬
     *@param productcode   ��Ʒ����
     *@param kcstatus   ���״̬
     *@return
     *@throws Exception
     */
	public String[] getInventoryQuerynew(String warehouseid, String whAreaId,
			String Virtualwhid, String lotinfo, String producttype,
			String cargoAlleyId, String platoon, String column, String floor,
			String lotnumber, String productid, String indateFrom,
			String indateTo, String trayCode, String status, String requestid,
			String instockid, String productstatus, String productcode) throws Exception;
	
	/**
	 * ���ܣ�(������)��Ʒ��ѯ
	 * @param warehouseid
	 * @param whAreaId
	 * @param Virtualwhid
	 * @param lotinfo
	 * @param productid
	 * @param indateFrom
	 * @param indateTo
	 * @param productstatus
	 * @return
	 * @throws Exception
	 */
	public String getInventoryWpQuerynew(String warehouseid, String whAreaId, String Virtualwhid, String lotinfo, String productid, String indateFrom,String indateTo,String productstatus) throws Exception;
	
	/**
	 * ���ܣ�(������)��Ʒ��ѯ
	 * @param warehouseid
	 * @param whAreaId
	 * @param Virtualwhid
	 * @param lotinfo
	 * @param productid
	 * @param indateFrom
	 * @param indateTo
	 * @param productstatus
	 * @return
	 * @throws Exception
	 */
	public List<InventoryStorage> getInventoryWpQuery(String warehouseid, String whAreaId, String Virtualwhid, String lotinfo, String productid, String indateFrom,String indateTo,String productstatus) throws Exception;
	
}
