package com.wms3.bms.standard.dao.inventory.impl;
import java.util.List;

import com.ricosoft.common.dao.dataSource.DaoFactory;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.AbstractDao;
import com.wms3.bms.standard.dao.inventory.IInventoryNeedCheckDao;
import com.wms3.bms.standard.entity.inventory.InventoryNeedcheck;

public class InventoryNeedCheckDaoImpl extends AbstractDao implements IInventoryNeedCheckDao {
	  public InventoryNeedCheckDaoImpl(EntityDAO dao){
	        this.m_dao = dao;
	    }
	/**
	 * 功能：根据id获得需盘点记录
	 * @param needCheckId 需盘点ID
	 * @return
	 * @throws Exception
	 */
	public InventoryNeedcheck getINeedcheckInfo(String needCheckId)
			throws Exception {
		InventoryNeedcheck iNeedcheck = null;
		try {
			String strSql = "from InventoryNeedcheck where needcheckid='"+needCheckId+"'";
			List ls = querySQL(strSql);
			if (ls!=null && ls.size()>0) {
				iNeedcheck = (InventoryNeedcheck) ls.get(0);
			}
		} catch (Exception er) {
			 throw new Exception("根据id获得需盘点记录出错：InventoryNeedCheckDaoImpl.getINeedcheckInfo：" + er.getMessage());
		}
		
		return iNeedcheck;
	}

	/**
	  * 功能：根据库位ID获取入库异常单
	  * @param needCheckId
	  * @return
	  * @throws Exception
	  */
	 public InventoryNeedcheck getINeedcheckInfoByCargospaceId(String cargospaceId)
		throws Exception{
		 InventoryNeedcheck iNeedcheck = null;
			try {
				String strSql = "from InventoryNeedcheck where cargoSpaceId='"+cargospaceId+"' and inouttype in ('1','2')" ;
				List ls = querySQL(strSql);
				if (ls!=null && ls.size()>0) {
					iNeedcheck = (InventoryNeedcheck) ls.get(0);
				}
			} catch (Exception er) {
				 throw new Exception("根据库位ID获取入库异常单记录出错：InventoryNeedCheckDaoImpl.getINeedcheckInfoByCargospaceId：" + er.getMessage());
			}
			
			return iNeedcheck;
	 }

}
