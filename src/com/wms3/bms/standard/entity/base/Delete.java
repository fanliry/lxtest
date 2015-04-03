package com.wms3.bms.standard.entity.base;


import java.util.ArrayList;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;

/**
 * 描述:删除信息
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
			throw new Exception("根据ID删除信息失败:" + er.getMessage());
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
			throw new Exception("删除信息失败:" + er.getMessage());
		}
	}
}