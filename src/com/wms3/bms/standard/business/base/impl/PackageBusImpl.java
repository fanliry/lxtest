package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.business.base.IPackageBus;
import com.wms3.bms.standard.dao.base.IBasePackageDao;
import com.wms3.bms.standard.dao.base.impl.BasePackageDaoImpl;
import com.wms3.bms.standard.entity.base.BasePackage;
import com.wms3.bms.standard.entity.base.BasePackageMastermesg;

/**
 * 
 * 描述: 包装管理业务类
 * @author fangjie
 * @since WMS 3.0
 */
public class PackageBusImpl implements IPackageBus {
	
	protected IBasePackageDao basePackageDAO = null;
	protected EntityDAO m_dao = null;

	/**
	 * @param dao
	 */
	public PackageBusImpl(EntityDAO dao) {
		this.basePackageDAO = new BasePackageDaoImpl(dao);
		this.m_dao = dao;
	}


	/**
	 * 功能:根据条件查询包装
	 * @param pkgdesc	包装描述
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getPackageQuery(String pkgdesc, String strUrl, int maxLine) throws Exception {
		
		PagingTool pt = null;
		
		try {
			String sql = "From BasePackage as t where 1=1" ;
			
			if(pkgdesc != null && pkgdesc.trim().length() > 0){
				sql += " and t.pkgdesc like '%" + pkgdesc + "%'";
			}
			
			String strHQL = sql + " order by t.packageid";
			String strCountHQL = "select count(*)" + sql ;
			
			pt = CommonPagination.getPagingTool(m_dao, strCountHQL ,strHQL, strUrl, 1, maxLine);
			
		} catch (Exception er) {
			throw new Exception("根据条件查询包装出错:" + er.getMessage());
		}
				
		return pt;
	}

	/**
	 * 功能:根据包装ID获得库区
	 * @param id	包装ID
	 * @return 
	 * @throws Exception
	 */
	public BasePackage getPackageById(String id) throws Exception {
		
		return basePackageDAO.getPackageById(id);
	}

	/**
	 * 功能:增加包装
	 * @param pk	包装
	 * @param pkmaster1, pkmaster2, pkmaster3, pkmaster4, pkmaster5 包装主信息
	 * @throws Exception
	 */
	public void addPackage(BasePackage pk, BasePackageMastermesg pkmaster1, BasePackageMastermesg pkmaster2, BasePackageMastermesg pkmaster3, 
			BasePackageMastermesg pkmaster4, BasePackageMastermesg pkmaster5) throws Exception {
		
		basePackageDAO.addPackage(pk, pkmaster1, pkmaster2, pkmaster3, pkmaster4, pkmaster5);
	}
	
	/**
	 * 功能:获取所有包装列表
	 * @return 
	 * @throws Exception
	 */
	public List getPackageList() throws Exception {
		
		return basePackageDAO.getPackageList();
	}

	/**
	 * 功能:修改包装
	 * @param pk	包装
	 * @throws Exception
	 */
	public void updatePackage(BasePackage pk) throws Exception {
		
		basePackageDAO.updatePackage(pk);
		
	}

	/**
	 * 功能:删除包装
	 * @param id	包装ID
	 * @throws Exception
	 */
	public void deletePackage(String id) throws Exception {
		
		basePackageDAO.deletePackage(id);
	}

	/**
	 * 功能:修改包装
	 * @param pk	包装
	 * @param pkmaster1, pkmaster2, pkmaster3, pkmaster4, pkmaster5 包装主信息
	 * @throws Exception
	 */
	public void updatePackage(BasePackage pk, BasePackageMastermesg pkmaster1, BasePackageMastermesg pkmaster2, BasePackageMastermesg pkmaster3, 
			BasePackageMastermesg pkmaster4, BasePackageMastermesg pkmaster5) throws Exception {
		
		basePackageDAO.updatePackage(pk, pkmaster1, pkmaster2, pkmaster3, pkmaster4, pkmaster5);
	}
	
}