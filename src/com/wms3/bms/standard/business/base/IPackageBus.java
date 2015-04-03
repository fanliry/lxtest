package com.wms3.bms.standard.business.base;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.base.BasePackage;
import com.wms3.bms.standard.entity.base.BasePackageMastermesg;

/**
 * 
 * 描述: 包装管理业务接口
 * @author fangjie
 * @since WMS 3.0
 */
public interface IPackageBus {
	
	/**
	 * 功能:根据条件查询包装
	 * @param pkgdesc	包装描述
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getPackageQuery(String pkgdesc, String strUrl, int maxLine) throws Exception;

	/**
	 * 功能:根据包装ID获得包装
	 * @param id	包装ID
	 * @return 
	 * @throws Exception
	 */
	public BasePackage getPackageById(String id) throws Exception;

	/**
	 * 功能:增加包装
	 * @param pk	包装
	 * @param pkmaster1, pkmaster2, pkmaster3, pkmaster4, pkmaster5 包装主信息
	 * @throws Exception
	 */
	public void addPackage(BasePackage pk, BasePackageMastermesg pkmaster1, BasePackageMastermesg pkmaster2, BasePackageMastermesg pkmaster3, 
			BasePackageMastermesg pkmaster4, BasePackageMastermesg pkmaster5) throws Exception;

	/**
	 * 功能:获取所有包装列表
	 * @return 
	 * @throws Exception
	 */
	public List getPackageList() throws Exception;

	/**
	 * 功能:修改包装
	 * @param pk	包装
	 * @throws Exception
	 */
	public void updatePackage(BasePackage pk) throws Exception;

	/**
	 * 功能:删除包装
	 * @param id	包装ID
	 * @throws Exception
	 */
	public void deletePackage(String id) throws Exception;

	/**
	 * 功能:修改包装
	 * @param pk	包装
	 * @param pkmaster1, pkmaster2, pkmaster3, pkmaster4, pkmaster5 包装主信息
	 * @throws Exception
	 */
	public void updatePackage(BasePackage pk, BasePackageMastermesg pkmaster1, BasePackageMastermesg pkmaster2, BasePackageMastermesg pkmaster3, 
			BasePackageMastermesg pkmaster4, BasePackageMastermesg pkmaster5) throws Exception;
	
}