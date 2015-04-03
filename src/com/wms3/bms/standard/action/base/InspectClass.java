package com.wms3.bms.standard.action.base;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.base.IWarehouseBus;
import com.wms3.bms.standard.business.base.impl.WarehouseBusImpl;
import com.wms3.bms.standard.entity.base.Meter;
import com.wms3.bms.standard.service.BMSService;

/**
 * 描述:DWR验证
 * @author gui
 *
 */
public class InspectClass 
{
	/**
	 * 功能:检查仓库中是否存在当前仓库
	 */
	public String checkHaveIscurrent() throws Exception
	{
		List ls=null;
		try
		{
			EntityDAO dao  = BMSService.getm_EntityDAO();
			IWarehouseBus warehouseBus = new WarehouseBusImpl(dao);
			
			//获得查询语句
			ls = warehouseBus.getHaveIscurrent();
		}catch(Exception ex)
		{
			ex.toString();
		}
		if(ls != null && ls.size()!=0)
		{
			return "1";
		}else
		{
			return "0";
		}
	}

	/**
	 * 说明：Dwr 检查单位名称是否唯一
	 * @author xiaotao
	 *
	 */
		public String checkMeterName(String meterName) throws Exception
		{
			EntityDAO dao  = BMSService.getm_EntityDAO();
			//获得查询语句
			String strSql=Meter.getSql(meterName);
			List ls=dao.searchEntities(strSql);
			if(ls.size()!=0)
			{
				return "0";
			}else
			{
				return "1";
			}
		}
}
