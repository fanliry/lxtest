package com.wms3.bms.standard.business.quality;

import java.util.List;

import com.wms3.bms.standard.entity.inventory.InventoryQualityResult;
import com.wms3.bms.standard.entity.inventory.InventoryQualityResultDetail;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
/**
 * ����:����������
 * @author yao
 *
 */
public interface InventoryQualityBus 
{
	/**
	 * ����:���״̬ת������ѯ��SQL���
	 * @param strLotNumber	����
	 * @param strFormDate	����
	 * @param strToDate 	����
	 * @return
	 */
	public String getQualityHeaderSQL(String strLotNumber, String strFormDate, String strToDate)throws Exception;
	/**
	 * @param strLotNumber	����
	 * @param strFormDate	����
	 * @param strToDate 	����
	 * @return
	 */
	public String getQualityHeaderCountSQL(String strLotNumber, String strFormDate, String strToDate) throws Exception;
	/**
	 * ���ܣ����¿�棬�������м�¼������ϸ
	 *@author liuxh 2013-3-8
	 *@param lsajustDetail ������ϸ����
	 *@param lsinventory   ��漯��
	 *@param adjust        ���е�
	 *@throws Exception
	 */
	public void createqualityInvoice(List<InventoryQualityResultDetail> lsajustDetail, List<InventoryStorage> lsinventory, InventoryQualityResult adjust) throws Exception;

	/**
	 * ���ܣ�����id��ȡ���е�����ϸ����Ϣ
	 * @param strId
	 * @return
	 * @throws Exception
	 */
	public InventoryQualityResult getAdjustListToId(String strId) throws Exception;
}
