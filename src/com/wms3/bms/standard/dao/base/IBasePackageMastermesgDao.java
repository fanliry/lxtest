package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BasePackageMastermesg;

/**
 * 
 * 描述: 包装主信息DAO类接口
 * @author fangjie
 * @since WMS 3.0
 */
public interface IBasePackageMastermesgDao {

	/**
	 * 功能:根据包装ID获得包装主信息
	 * @param packageid	包装ID
	 * @param type
	 * @return 
	 * @throws Exception
	 */
	public BasePackageMastermesg getPackageMastermesg(String packageid, String type) throws Exception;

	/**
	 * 功能:根据包装主信息ID获得包装主信息
	 * @param packmasterid	包装主信息ID
	 * @return 
	 * @throws Exception
	 */
	public BasePackageMastermesg getPackageMastermesgById(String packmasterid) throws Exception;
    
    /**
     * 功能：根据包装ID和入/出库类型获得包装单位
     * @author hug 2012-8-20 
     * @param strPackageId  包装ID
     * @param inoutType     入/出库类型  1:入库； 2：出库
     * @return
     * @throws Exception
     */
    public List<BasePackageMastermesg> getPackageMastermesgList(String strPackageId, String inoutType) throws Exception;
    
}
