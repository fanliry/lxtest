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
 * ����: ��װ����ҵ����
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
	 * ����:����������ѯ��װ
	 * @param pkgdesc	��װ����
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
			throw new Exception("����������ѯ��װ����:" + er.getMessage());
		}
				
		return pt;
	}

	/**
	 * ����:���ݰ�װID��ÿ���
	 * @param id	��װID
	 * @return 
	 * @throws Exception
	 */
	public BasePackage getPackageById(String id) throws Exception {
		
		return basePackageDAO.getPackageById(id);
	}

	/**
	 * ����:���Ӱ�װ
	 * @param pk	��װ
	 * @param pkmaster1, pkmaster2, pkmaster3, pkmaster4, pkmaster5 ��װ����Ϣ
	 * @throws Exception
	 */
	public void addPackage(BasePackage pk, BasePackageMastermesg pkmaster1, BasePackageMastermesg pkmaster2, BasePackageMastermesg pkmaster3, 
			BasePackageMastermesg pkmaster4, BasePackageMastermesg pkmaster5) throws Exception {
		
		basePackageDAO.addPackage(pk, pkmaster1, pkmaster2, pkmaster3, pkmaster4, pkmaster5);
	}
	
	/**
	 * ����:��ȡ���а�װ�б�
	 * @return 
	 * @throws Exception
	 */
	public List getPackageList() throws Exception {
		
		return basePackageDAO.getPackageList();
	}

	/**
	 * ����:�޸İ�װ
	 * @param pk	��װ
	 * @throws Exception
	 */
	public void updatePackage(BasePackage pk) throws Exception {
		
		basePackageDAO.updatePackage(pk);
		
	}

	/**
	 * ����:ɾ����װ
	 * @param id	��װID
	 * @throws Exception
	 */
	public void deletePackage(String id) throws Exception {
		
		basePackageDAO.deletePackage(id);
	}

	/**
	 * ����:�޸İ�װ
	 * @param pk	��װ
	 * @param pkmaster1, pkmaster2, pkmaster3, pkmaster4, pkmaster5 ��װ����Ϣ
	 * @throws Exception
	 */
	public void updatePackage(BasePackage pk, BasePackageMastermesg pkmaster1, BasePackageMastermesg pkmaster2, BasePackageMastermesg pkmaster3, 
			BasePackageMastermesg pkmaster4, BasePackageMastermesg pkmaster5) throws Exception {
		
		basePackageDAO.updatePackage(pk, pkmaster1, pkmaster2, pkmaster3, pkmaster4, pkmaster5);
	}
	
}