package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.base.IPackageMastermesgBus;
import com.wms3.bms.standard.dao.base.IBasePackageMastermesgDao;
import com.wms3.bms.standard.dao.base.impl.BasePackageMastermesgDaoImpl;
import com.wms3.bms.standard.entity.base.BasePackageMastermesg;

/**
 * 
 * ����: ��װ����Ϣ����ҵ����
 * @author fangjie
 * @since WMS 3.0
 */
public class PackageMastermesgBusImpl implements IPackageMastermesgBus {
	
	protected IBasePackageMastermesgDao basePackageMastermesgDAO = null;

	/**
	 * @param dao
	 */
	public PackageMastermesgBusImpl(EntityDAO dao) {
		
		this.basePackageMastermesgDAO = new BasePackageMastermesgDaoImpl(dao);
	}


	/**
	 * ����:���ݰ�װID��ð�װ����Ϣ
	 * @param packageid	��װID
	 * @param type
	 * @return 
	 * @throws Exception
	 */
	public BasePackageMastermesg getPackageMastermesg(String packageid, String type) throws Exception{
		
		return basePackageMastermesgDAO.getPackageMastermesg(packageid, type);
	}

	/**
	 * ����:���ݰ�װ����ϢID��ð�װ����Ϣ
	 * @param packmasterid	��װ����ϢID
	 * @return 
	 * @throws Exception
	 */
	public BasePackageMastermesg getPackageMastermesgById(String packmasterid) throws Exception {
		
		return basePackageMastermesgDAO.getPackageMastermesgById(packmasterid);
	}
    
    public List<BasePackageMastermesg> getPackageMastermesgList(String strPackageId, String inoutType) throws Exception {
        
        return basePackageMastermesgDAO.getPackageMastermesgList(strPackageId, inoutType);
    }
	
}