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
	 * ���ܣ�����id������̵��¼
	 * @param needCheckId ���̵�ID
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
			 throw new Exception("����id������̵��¼����InventoryNeedCheckDaoImpl.getINeedcheckInfo��" + er.getMessage());
		}
		
		return iNeedcheck;
	}

	/**
	  * ���ܣ����ݿ�λID��ȡ����쳣��
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
				 throw new Exception("���ݿ�λID��ȡ����쳣����¼����InventoryNeedCheckDaoImpl.getINeedcheckInfoByCargospaceId��" + er.getMessage());
			}
			
			return iNeedcheck;
	 }

}
