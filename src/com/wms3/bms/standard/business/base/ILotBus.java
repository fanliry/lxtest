package com.wms3.bms.standard.business.base;

import java.util.HashMap;
import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.base.BaseLot;
import com.wms3.bms.standard.entity.base.BaseLotDetail;

/**
 * 
 * ����: �������Թ���ҵ��ӿ�
 * @author fangjie
 * @since WMS 3.0
 */
public interface ILotBus {
	
	/**
	 * ����:����������ѯ��������
	 * @param descr	����
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getLotQuery(String descr, String strUrl, int maxLine) throws Exception;

	/**
	 * ����:�������Σ�������ϸ��Ϣ
	 * @param lot	����
	 * @param lotDetail1, lotDetail2, lotDetail3, lotDetail4, lotDetail5, lotDetail6, 
	 * @param lotDetail7, lotDetail8, lotDetail9, lotDetail10, lotDetail11, lotDetail12 ������ϸ��Ϣ
	 * @throws Exception
	 */
	public void addLot(BaseLot lot, BaseLotDetail lotDetail1, BaseLotDetail lotDetail2, BaseLotDetail lotDetail3, BaseLotDetail lotDetail4, 
			BaseLotDetail lotDetail5, BaseLotDetail lotDetail6, BaseLotDetail lotDetail7, BaseLotDetail lotDetail8, BaseLotDetail lotDetail9, 
			BaseLotDetail lotDetail10, BaseLotDetail lotDetail11, BaseLotDetail lotDetail12) throws Exception;

	/**
	 * ����:��������ID�������
	 * @param id	����ID
	 * @return 
	 * @throws Exception
	 */
	public BaseLot getLotById(String lotid) throws Exception;

	/**
	 * ����:��������ID���������ϸ��Ϣ�б�
	 * @param id	����ID
	 * @return 
	 * @throws Exception
	 */
	public List getListByLotId(String lotid) throws Exception;
    /**
     * 
     * ����: ����:��������ID���������ϸ��Ϣ�б�(HashMap)
     * @author hug 2012-8-30 
     * @param lotid   ����ID
     * @return
     * @throws Exception
     */
    public HashMap<String, BaseLotDetail> getHashMapByLotId(String lotid) throws Exception;

	/**
	 * ����:����������ϸID���������ϸ
	 * @param id	������ϸID
	 * @return 
	 * @throws Exception
	 */
	public BaseLotDetail getLotDetailByDetailId(String lotdetailid) throws Exception;

	/**
	 * ����:�޸����Σ�������ϸ��Ϣ
	 * @param lot	����
	 * @param lotDetail1, lotDetail2, lotDetail3, lotDetail4, lotDetail5, lotDetail6, 
	 * @param lotDetail7, lotDetail8, lotDetail9, lotDetail10, lotDetail11, lotDetail12 ������ϸ��Ϣ
	 * @throws Exception
	 */
	public void updateLot(BaseLot lot, BaseLotDetail lotDetail1, BaseLotDetail lotDetail2, BaseLotDetail lotDetail3, BaseLotDetail lotDetail4, 
			BaseLotDetail lotDetail5, BaseLotDetail lotDetail6, BaseLotDetail lotDetail7, BaseLotDetail lotDetail8, BaseLotDetail lotDetail9, 
			BaseLotDetail lotDetail10, BaseLotDetail lotDetail11, BaseLotDetail lotDetail12) throws Exception;

	/**
	 * ����:ɾ�����Σ�������ϸ��Ϣ
	 * @param id	����ID
	 * @return 
	 * @throws Exception
	 */
	public void deleteLot(String id) throws Exception;

	/**
	 * ����:���������������
	 * @return 
	 * @throws Exception
	 */
	public List getAllLots() throws Exception;


}