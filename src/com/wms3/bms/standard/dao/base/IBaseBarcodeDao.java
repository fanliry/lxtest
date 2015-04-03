package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseBarcode;

/**
 * 描述: 条形码DAO类接口
 * @author fangjie
 * 
 */
public interface IBaseBarcodeDao {

	/**
	 * 功能:根据条形码ID获得条形码
	 * @param id	条形码ID
	 * @return
	 * @throws Exception
	 */
	public BaseBarcode getBarcodeById(String id) throws Exception;
	
	/**
	 * 功能:获取所有条形码列表
	 * @return
	 * @throws Exception
	 */
	public List getBarcodeList() throws Exception;

	/**
	 * 功能:增加条形码
	 * @param barcode	条形码
	 * @throws Exception
	 */
	public void addBarcode(BaseBarcode barcode) throws Exception;

	/**
	 * 功能:修改条形码
	 * @param barcode	条形码
	 * @throws Exception
	 */
	public void updateBarcode(BaseBarcode barcode) throws Exception;

	/**
	 * 功能:删除条形码
	 * @param id	条形码ID
	 * @throws Exception
	 */
	public void deleteBarcode(String id) throws Exception;

}
