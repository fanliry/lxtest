package com.wms3.bms.standard.business.base;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.base.BasePackage;
import com.wms3.bms.standard.entity.base.BasePackageMastermesg;

/**
 * 
 * ����: ��װ����ҵ��ӿ�
 * @author fangjie
 * @since WMS 3.0
 */
public interface IPackageBus {
	
	/**
	 * ����:����������ѯ��װ
	 * @param pkgdesc	��װ����
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getPackageQuery(String pkgdesc, String strUrl, int maxLine) throws Exception;

	/**
	 * ����:���ݰ�װID��ð�װ
	 * @param id	��װID
	 * @return 
	 * @throws Exception
	 */
	public BasePackage getPackageById(String id) throws Exception;

	/**
	 * ����:���Ӱ�װ
	 * @param pk	��װ
	 * @param pkmaster1, pkmaster2, pkmaster3, pkmaster4, pkmaster5 ��װ����Ϣ
	 * @throws Exception
	 */
	public void addPackage(BasePackage pk, BasePackageMastermesg pkmaster1, BasePackageMastermesg pkmaster2, BasePackageMastermesg pkmaster3, 
			BasePackageMastermesg pkmaster4, BasePackageMastermesg pkmaster5) throws Exception;

	/**
	 * ����:��ȡ���а�װ�б�
	 * @return 
	 * @throws Exception
	 */
	public List getPackageList() throws Exception;

	/**
	 * ����:�޸İ�װ
	 * @param pk	��װ
	 * @throws Exception
	 */
	public void updatePackage(BasePackage pk) throws Exception;

	/**
	 * ����:ɾ����װ
	 * @param id	��װID
	 * @throws Exception
	 */
	public void deletePackage(String id) throws Exception;

	/**
	 * ����:�޸İ�װ
	 * @param pk	��װ
	 * @param pkmaster1, pkmaster2, pkmaster3, pkmaster4, pkmaster5 ��װ����Ϣ
	 * @throws Exception
	 */
	public void updatePackage(BasePackage pk, BasePackageMastermesg pkmaster1, BasePackageMastermesg pkmaster2, BasePackageMastermesg pkmaster3, 
			BasePackageMastermesg pkmaster4, BasePackageMastermesg pkmaster5) throws Exception;
	
}