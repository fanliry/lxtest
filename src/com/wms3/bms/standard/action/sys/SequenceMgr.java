package com.wms3.bms.standard.action.sys;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.dao.base.impl.BaseProductDaoImpl;
import com.wms3.bms.standard.entity.base.BaseProduct;
import com.wms3.bms.standard.service.BMSService;

public class SequenceMgr 
{
	/**
	 * ���ݲ�Ʒid��ȡ�ò�Ʒ��Ӧ����Ϣ
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
			Logger.error("DWR��ȡ��Ʒ��Ϣʧ��:" + er.getMessage());
		}
		return null;
	}
}
