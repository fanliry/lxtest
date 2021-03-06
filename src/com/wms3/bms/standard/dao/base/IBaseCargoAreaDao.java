package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseCargoarea;

/**
 * 描述: 货区DAO类接口
 * @author fangjie
 * 
 */
public interface IBaseCargoAreaDao {
	
	/**
	 * 功能:根据条件查询库区
	 * @param warehouseid		仓库ID
	 * @param cargoAreaName		货区名	
	 * @return
	 * @throws Exception
	 */
	public List getCargoAreaQuery(String warehouseid, String cargoAreaName) throws Exception;

	/**
	 * 功能:根据货区ID获得货区
	 * @param cargoAreaId	货区ID
	 * @return
	 * @throws Exception
	 */
	public BaseCargoarea getCargoareaById(String cargoAreaId) throws Exception;

	/**
	 * 功能:获得该仓库的最大的一个货区编码
	 * @param warehouseid 
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	public String getMaxCargoAreaId(String warehouseid) throws Exception;

	/**
	 * 功能:增加货区
	 * @param cargoArea	货区
	 * @throws Exception
	 */
	public void addCargoArea(BaseCargoarea cargoarea) throws Exception;

	/**
	 * 功能:获取所有货区列表
	 * @return
	 * @throws Exception
	 */
	public List getCargoAreaList() throws Exception;

	/**
	 * 功能:修改货区
	 * @param cargoArea	货区
	 * @throws Exception
	 */
	public void updateCargoArea(BaseCargoarea cargoarea) throws Exception;

	/**
	 * 功能:删除货区
	 * @param cargoAreaId	货区ID
	 * @throws Exception
	 */
	public void deleteCargoArea(String id) throws Exception;

}
