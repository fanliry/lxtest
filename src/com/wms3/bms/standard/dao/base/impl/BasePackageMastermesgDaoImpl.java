package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBasePackageMastermesgDao;
import com.wms3.bms.standard.entity.base.BasePackageMastermesg;

/**
 * 
 * ����: ��װ����ϢDAO��
 * @author fangjie 
 * @since WMS 3.0
 */
public class BasePackageMastermesgDaoImpl implements IBasePackageMastermesgDao {
	
	/** ���ݿ������DAO���� */
    protected EntityDAO m_dao;

	public BasePackageMastermesgDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}

	/**
	 * ����:���ݰ�װID��ð�װ����Ϣ
	 * @param packageid	��װID
	 * @param type
	 * @return 
	 * @throws Exception
	 */
	public BasePackageMastermesg getPackageMastermesg(String packageid, String type) throws Exception {
		
		String strSql = " from BasePackageMastermesg as t where t.packid='" + packageid + "' and t.pkgunit='" + type + "'";
		List ls = m_dao.searchEntities(strSql);
		if(ls != null && ls.size() > 0){
			return (BasePackageMastermesg)ls.get(0);
		}
		return null;
	}

	/**
	 * ����:���ݰ�װ����ϢID��ð�װ����Ϣ
	 * @param packmasterid	��װ����ϢID
	 * @return 
	 * @throws Exception
	 */
	public BasePackageMastermesg getPackageMastermesgById(String packmasterid) throws Exception {
		
		String strSql = " from BasePackageMastermesg as t where t.packmasterid='" + packmasterid + "'";
		List ls = m_dao.searchEntities(strSql);
		if(ls != null && ls.size() > 0){
			return (BasePackageMastermesg)ls.get(0);
		}
		return null;
	}
    
    public List<BasePackageMastermesg> getPackageMastermesgList(String strPackageId, String inoutType) throws Exception {
        String strSql = " from BasePackageMastermesg as t where t.packid='" + strPackageId + "'";
        if(inoutType != null && inoutType.equals("1")){//���ʹ��
            strSql = strSql + " and t.inused='Y'";
        }else if(inoutType != null && inoutType.equals("2")){//����ʹ��
            strSql = strSql + " and t.outused='Y'";
        }
        
        List ls = m_dao.searchEntities(strSql);
        return ls;
    }

	
}