package com.wms3.bms.standard.action.sys;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.dao.base.impl.BaseProductDaoImpl;
import com.wms3.bms.standard.entity.base.BaseProduct;
import com.wms3.bms.standard.service.BMSService;

public class SequenceMgr 
{
	/**
	 * 根据产品id获取该产品相应的信息
	 * @param strId
	 * @return
	 */
	public BaseProduct getSkuId(String strId)
	{
		try
		{
			EntityDAO dao =  BMSService.getm_EntityDAO();
			BaseProductDaoImpl skuMgr = new BaseProductDaoImpl(dao);
			BaseProduct sku = skuMgr.getProductById(strId);
			return sku;
		}catch(Exception er)
		{
			Logger.error("DWR获取产品信息失败:" + er.getMessage());
		}
		return null;
	}
}
