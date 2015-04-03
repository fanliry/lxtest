package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseWhAreaVirtualDao;
import com.wms3.bms.standard.entity.base.BaseWhareaVirtual;

/**
 * 
 * ����: ����DAO��
 * @author fanll 
 * @since WMS 3.0
 */
public class BaseWhAreaVirtualDaoImpl implements IBaseWhAreaVirtualDao {
	
	/** ���ݿ������DAO���� */
    protected EntityDAO m_dao;

	public BaseWhAreaVirtualDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}
	
	/**
	 * ����:����������ѯ����
	 * @param warehouseid		�ֿ�ID
	 * @param whAreaName		������
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaQuery(String warehouseid, String whAreaName) throws Exception 
	{
		List ls = null;
		
		String strHql = "From BaseWhareaVirtual as t where 1=1" ;
		
		if(warehouseid != null && warehouseid.trim().length() > 0){
			strHql += " and t.warehouseid='" + warehouseid + "'";
		}
		if(whAreaName != null && whAreaName.trim().length() > 0){
			strHql += " and t.whAreaName like '%" + whAreaName + "%'";
		}
		strHql = strHql + " order by t.whAreaId";
		ls = m_dao.searchEntities(strHql);		
				
		return ls;
	}

	/**
	 * ����:���ݿ���ID��ÿ���
	 * @param whAreaId	����ID
	 * @return 
	 * @throws Exception
	 */
	public BaseWhareaVirtual getWhareaById(String whAreaId) throws Exception 
	{
		if(whAreaId != null)
		{
			String strSql = " from BaseWhareaVirtual as t where t.whAreaId='" + whAreaId+"'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseWhareaVirtual)ls.get(0);
			}
		}
		return null;
	}
	
	/**
	 * ����:�������һ����������
	 * @param warehouseid 
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	public String getMaxWhareaNo(String warehouseid) throws Exception 
	{
		String maxWhareaNo = warehouseid + "0000";
		try
		{
			if(warehouseid != null && !warehouseid.equals("")) {
				String strSql = "from BaseWhareaVirtual as t where t.warehouseid='" + warehouseid + "' order by t.whAreaId desc";
				List ls = m_dao.searchEntities(strSql);
				if(ls != null && !ls.isEmpty())
				{
					BaseWhareaVirtual wharea = (BaseWhareaVirtual)ls.get(0);
					maxWhareaNo = wharea.getwhAreaId();
				}
			}
		}catch(Exception er)
		{
			throw new Exception("�������һ����������ʧ��:"+er.getMessage());
		}
		return maxWhareaNo;
	}

	/**
	 * ����:���ӿ���
	 * @param dao
	 * @param wharea	����
	 * @throws Exception
	 */
	public void addWhArea(BaseWhareaVirtual wharea) throws Exception 
	{
		m_dao.save(wharea);
	}

	/**
	 * ����:��ȡ���п����б�
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	public List getWhAreaList() throws Exception 
	{
		List ls = null;
		try
		{
			String strHql = "From BaseWhareaVirtual as t where 1=1 order by t.whAreaId";
			ls = m_dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("��ѯ�����б����:" + er.getMessage());
		}
		return ls;
	}

	/**
	 * ����:�޸Ŀ���
	 * @param dao
	 * @param wharea	����
	 * @throws Exception
	 */
	public void updateWhArea(BaseWhareaVirtual wharea) throws Exception 
	{
		m_dao.update(wharea);
	}

	/**
	 * ����:ɾ������
	 * @param whAreaId	����ID
	 * @throws Exception
	 */
	public void deleteWhArea(String whAreaId) throws Exception 
	{
		if(whAreaId != null && !whAreaId.equals(""))
		{
			String strSql  = " delete BaseWhareaVirtual as t where t.whAreaId='" + whAreaId+"'";

			m_dao.deleteSql(strSql);
		}	
	}
	
	/**
	 * ����:�жϲֿ����Ƿ��п���
	 * @param id	�ֿ�ID
	 * @throws Exception
	 */
	public boolean isWhHasChildNode(String id) throws Exception {
		
		if(id != null && !id.equals(""))
		{
			String strSql = " from BaseWhareaVirtual as t where t.warehouseid='" + id + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return true;
			}
		}
		return false;
	}

	/**
	 * ����:ȡ��ָ���ֿ��µĿ���
	 * @param warehouseid	�ֿ�ID
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaByWhid(String warehouseid) throws Exception {
		
		List ls = null;
		String strHql = "";
		if(warehouseid!=null && warehouseid.length()>0){
			 strHql = "From BaseWhareaVirtual as t where 1=1 and t.warehouseid='" + warehouseid + "' order by t.whAreaId";
		}else{
			 strHql = "From BaseWhareaVirtual as t where 1=1 order by t.whAreaId";
		}
		
		ls = m_dao.searchEntities(strHql);
		
		return ls;
	}
	/**
	 * ����:ȡ��ָ���ֿ��µĿ���������⣩
	 * @param warehouseid	�ֿ�ID
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaByWhidNotTem(String warehouseid) throws Exception {
		
		List ls = null;
		
		String strHql = "From BaseWhareaVirtual as t where 1=1 and t.warehouseid='" + warehouseid + "' and t.istask='Y' order by t.whAreaId";
		ls = m_dao.searchEntities(strHql);
		
		return ls;
	}
    /**
     * ���ݲֿ����ݴ���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.base.IBaseWhAreaDao#getZCgetWhAreaByWhid(java.lang.String)
     */
    public BaseWhareaVirtual getZCgetWhAreaByWhid(String warehouseid) throws Exception {
        if(warehouseid != null)
        {
            String strSql = " from BaseWhareaVirtual as t where t.warehouseid='" + warehouseid+"' and t.whAreaType='9'";
            List ls = m_dao.searchEntities(strSql);
            if(ls != null && ls.size() > 0){
                return (BaseWhareaVirtual)ls.get(0);
            }
        }
        return null;
    }
}