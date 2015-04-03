package com.wms3.bms.standard.business.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseAlley;

/**
 * 描述: 巷道管理业务接口
 * @author fangjie
 * 
 */
public interface IAlleyBus {

	/**
	 * 功能:根据条件查询巷道
	 * @param warehouseid		仓库ID
	 * @param cargoAlleyId		巷道ID	
	 * @param whAreaId			库区ID
	 * @return
	 * @throws Exception
	 */
	public List getAlleyQuery(String warehouseid, String cargoAlleyId, String whAreaId) throws Exception;

	/**
	 * 功能:根据巷道ID获得巷道
	 * @param cargoAlleyId	巷道ID
	 * @return
	 * @throws Exception
	 */
	public BaseAlley getAlleyById(String cargoAlleyId) throws Exception;
	
	/**
	 * 功能:增加巷道
	 * @param alley	巷道
	 * @throws Exception
	 */
	public void addAlley(BaseAlley alley)  throws Exception;

	/**
	 * 功能:获取所有巷道列表
	 * @return
	 * @throws Exception
	 */
	public List getAlleyList() throws Exception;

	/**
	 * 功能:修改巷道
	 * @param alley	巷道
	 * @throws Exception
	 */
	public void updateAlley(BaseAlley alley) throws Exception;

	/**
	 * 功能:删除巷道
	 * @param id	巷道ID
	 * @throws Exception
	 */
	public void deleteAlley(String id) throws Exception;

	/**
	 * 功能:判断库区下是否有巷道
	 * @param id	库区ID
	 * @throws Exception
	 */
	public boolean isWhAreaHasChildNode(String id) throws Exception;


}
