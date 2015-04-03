package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BasePackageMastermesg;

/**
 * 
 * ����: ��װ����ϢDAO��ӿ�
 * @author fangjie
 * @since WMS 3.0
 */
public interface IBasePackageMastermesgDao {

	/**
	 * ����:���ݰ�װID��ð�װ����Ϣ
	 * @param packageid	��װID
	 * @param type
	 * @return 
	 * @throws Exception
	 */
	public BasePackageMastermesg getPackageMastermesg(String packageid, String type) throws Exception;

	/**
	 * ����:���ݰ�װ����ϢID��ð�װ����Ϣ
	 * @param packmasterid	��װ����ϢID
	 * @return 
	 * @throws Exception
	 */
	public BasePackageMastermesg getPackageMastermesgById(String packmasterid) throws Exception;
    
    /**
     * ���ܣ����ݰ�װID����/�������ͻ�ð�װ��λ
     * @author hug 2012-8-20 
     * @param strPackageId  ��װID
     * @param inoutType     ��/��������  1:��⣻ 2������
     * @return
     * @throws Exception
     */
    public List<BasePackageMastermesg> getPackageMastermesgList(String strPackageId, String inoutType) throws Exception;
    
}
