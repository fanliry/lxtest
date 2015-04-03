package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseLotDetail;

/**
 * 
 * ����: ����������ϸ��ϢDAO��ӿ�
 * @author fangjie
 * @since WMS 3.0
 */
public interface IBaseLotDetailDao {

	/**
	 * ����:��������ID���������ϸ��Ϣ�б�
	 * @param id	����ID
	 * @return 
	 * @throws Exception
	 */
	List getListByLotId(String lotid) throws Exception;

	/**
	 * ����:����������ϸID���������ϸ
	 * @param id	������ϸID
	 * @return 
	 * @throws Exception
	 */
	BaseLotDetail getLotDetailByDetailId(String lotdetailid) throws Exception;
    
}
