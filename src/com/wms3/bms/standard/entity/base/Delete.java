package com.wms3.bms.standard.entity.base;


import java.util.ArrayList;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;

/**
 * ����:ɾ����Ϣ
 * @author xiaotao
 *
 */
public class Delete
{
	public void deleteToId(String object ,String strUserId, EntityDAO dao,String id) throws Exception
	{
		try
		{
			if(strUserId != null)
			{
				List ls = new ArrayList();
				String strSql = "delete "+object+" as m where m."+id+"='"+strUserId+"'";
				ls.add(strSql);
				dao.deleteSqlList(ls);
			}
		}catch(Exception er)
		{
			throw new Exception("����IDɾ����Ϣʧ��:" + er.getMessage());
		}
	}
	public void deleteAll(String object,EntityDAO dao) throws Exception
	{
		try
		{
			if(object != null)
			{
				List ls = new ArrayList();
				String strSql = "delete "+object+"";
				ls.add(strSql);
				dao.deleteSqlList(ls);
			}
		}catch(Exception er)
		{
			throw new Exception("ɾ����Ϣʧ��:" + er.getMessage());
		}
	}
}