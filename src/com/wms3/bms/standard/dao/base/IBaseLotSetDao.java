package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseLotSet;



/**
 * 
 * ����: ��������������ӿ�
 * @author hug 2012-3-12
 * @since WMS 3.0
 */
public interface IBaseLotSetDao {

	/**
	 * ����:��ѯ�����������ò�ͬ����
	 * @param lottype 	����
	 * @return 
	 * @throws Exception
	 */
	List getLotsetType() throws Exception;
	
	/**
	 * ����:����������������
	 * @param lotset1, lotset2, lotset3, lotset4, lotset5, lotset6, 
	 * @param lotset7, lotset8, lotset9, lotset10, lotset11, lotset12
	 * @throws Exception
	 */
	void addLotset(BaseLotSet lotset1, BaseLotSet lotset2, BaseLotSet lotset3, BaseLotSet lotset4, BaseLotSet lotset5, BaseLotSet lotset6, 
			BaseLotSet lotset7, BaseLotSet lotset8, BaseLotSet lotset9, BaseLotSet lotset10, BaseLotSet lotset11, BaseLotSet lotset12) throws Exception;
	
	/**
	 * ����:�������Ͳ�ѯ�����������õ��б�
	 * @param lottype	����
	 * @return 
	 * @throws Exception
	 */
	List getLotsetByType(String lottype) throws Exception;
	
	/**
	 * ����:ɾ��������������
	 * @param lottype	����
	 * @return 
	 * @throws Exception
	 */
	void deleteLotset(String lottype) throws Exception;
	
	/**
	 * ����:����Id��ѯ������������
	 * @param lotattid	Id
	 * @return 
	 * @throws Exception
	 */
	BaseLotSet getLotsetById(String lotattid) throws Exception;
	
	/**
	 * ����:�޸�������������
	 * @param lotset1, lotset2, lotset3, lotset4, lotset5, lotset6, 
	 * @param lotset7, lotset8, lotset9, lotset10, lotset11, lotset12
	 * @throws Exception
	 */
	void updateLotset(BaseLotSet lotset1, BaseLotSet lotset2, BaseLotSet lotset3, BaseLotSet lotset4, BaseLotSet lotset5, BaseLotSet lotset6, 
			BaseLotSet lotset7, BaseLotSet lotset8, BaseLotSet lotset9, BaseLotSet lotset10, BaseLotSet lotset11, BaseLotSet lotset12) throws Exception;
	
	/**
	 * ����:�жϸ������Ƿ����
	 * @param lottype	����
	 * @throws Exception
	 */
	boolean isLotsetExist(String lottype) throws Exception;
    
    /**
     * ���ܣ������������ͻ�ÿ���ʾ���������ԣ��������õ�˳������
     * @author hug 2012-8-21 
     * @param strLotType    ����
     * @return
     * @throws Exception
     */
    public List<BaseLotSet> getViewLot(String strLotType) throws Exception;
    /**
     * ���ܣ�ͳ������Ҫ��ʾ����������
     * @author hug 2012-8-22 
     * @return
     * @throws Exception
     */
    public List<String> getGroupLotType() throws Exception;
}