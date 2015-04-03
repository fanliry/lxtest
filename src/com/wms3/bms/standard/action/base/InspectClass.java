package com.wms3.bms.standard.action.base;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.base.IWarehouseBus;
import com.wms3.bms.standard.business.base.impl.WarehouseBusImpl;
import com.wms3.bms.standard.entity.base.Meter;
import com.wms3.bms.standard.service.BMSService;

/**
 * ����:DWR��֤
 * @author gui
 *
 */
public class InspectClass 
{
	/**
	 * ����:���ֿ����Ƿ���ڵ�ǰ�ֿ�
	 */
	public String checkHaveIscurrent() throws Exception
	{
		List ls=null;
		try
		{
			EntityDAO dao  = BMSService.getm_EntityDAO();
			IWarehouseBus warehouseBus = new WarehouseBusImpl(dao);
			
			//��ò�ѯ���
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
	 * ˵����Dwr ��鵥λ�����Ƿ�Ψһ
	 * @author xiaotao
	 *
	 */
		public String checkMeterName(String meterName) throws Exception
		{
			EntityDAO dao  = BMSService.getm_EntityDAO();
			//��ò�ѯ���
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
