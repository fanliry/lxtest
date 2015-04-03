package com.wms3.bms.standard.entity.base;


import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;

/**
 * ˵������������ʵ��
 * @author xiaotao
 *
 */

public class BranchMeans{
	
	
	private String m_branchMeansId;
	private String m_branchNumber;	 //���ű��
	private String m_branchName;     //��������
	private String m_describe;       //��ע
	
	
	public BranchMeans(){}
	
	public BranchMeans(String branchNumber,String branchName)
	{
		this.m_branchNumber=branchNumber;
		this.m_branchName  =branchName;
	}
	

	public String getM_branchMeansId() {
		return m_branchMeansId;
	}

	public void setM_branchMeansId(String meansId) {
		m_branchMeansId = meansId;
	}

	public String getM_branchName() {
		return m_branchName;
	}

	public void setM_branchName(String name) {
		m_branchName = name;
	}

	public String getM_branchNumber() {
		return m_branchNumber;
	}

	public void setM_branchNumber(String number) {
		m_branchNumber = number;
	}

	public String getM_describe() {
		return m_describe;
	}

	public void setM_describe(String m_describe) {
		this.m_describe = m_describe;
	}
	
	

	/**
	 * ���ܣ�����Id��ȡģ�����
	 * @param strId
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	public BranchMeans getBranchInfoFromId(String strId, EntityDAO dao) throws Exception{
		if(strId != null){
			strId = strId.trim();
			
			String strSql = "From BranchMeans as m where m.m_branchMeansId='"+ strId +"'";
			List ls = dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BranchMeans)ls.get(0);
			}
		}
		
		return null;
	}
	
	/**
	 * ����:����
	 * @param dao
	 * @throws Exception
	 */
	public void addBranchInfo(EntityDAO dao) throws Exception
	{
		dao.save(this);
	}
	/**
	 * ����:����
	 * @param dao
	 * @throws Exception
	 */
	public void updateBranchInfo(EntityDAO dao) throws Exception
	{
		dao.update(this);
	}
	/**
	 * ����:���в����б�
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	public static List getDepartmentList(EntityDAO dao) throws Exception
	{
		List ls = null;
		try
		{
			String strHql = "From BranchMeans as bm where 1=1";
			ls = dao.searchEntities(strHql);
		}catch(Exception er)
		{
			throw new Exception("��ѯ���в����б����:" + er.getMessage());
		}
		return ls;
	}
	
	/**
	 * ����:��ò�ѯ��SQL���
	 * @param branchNumber
	 * @param branchName
	 * @return
	 */
	public static String getBranchSQL(String branchNumber, String branchName)
	{
		  String strSql="From BranchMeans as m";
		  
		  if (branchNumber!=null && branchNumber.trim().length()>0)
		  {
			  strSql=strSql+" where m.m_branchNumber like '%"+branchNumber+"%'";	
		  }
		  if (branchName!=null && branchName.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_branchName like '%"+branchName+"%'";	
		  }
		  return strSql;
	}
	/**
	 * ����:��ò�ѯ��SQL���
	 * @param branchName
	 * @param branchNumber
	 * @return strSql
	 * 
	 */
	public static String getBranchCountSQL(String branchNumber, String branchName)
	{
		  String strSql="select count(m) From BranchMeans as m  where 1=1";
		  
		  if (branchNumber!=null && branchNumber.trim().length()>0)
		  {
			  strSql=strSql+"and m.m_branchNumber like '%"+branchNumber+"%'";	
		  }
		  if (branchName!=null && branchName.trim().length()>0)
		  {
			  strSql=strSql+" and m.m_branchName like '%"+branchName+"%'";	
		  }
		  return strSql;
	}
	
	/**
	 * ����:������Ϣ���ɱ��
	 * @param object
	 * @param strField
	 * @param dao
	 * @return
	 */
	public List getNumber(String object,String strField,EntityDAO dao)
	{
		List ls= dao.searchEntities("select max(m."+strField+") From "+object+" m");
		return ls;
	}

	/**
	 * ���ܣ�����ID��ȡ����
	 * @param dao
	 * @param strDepartId
	 * @author yangxi
	 * @return
	 */
	public static String getDepartNameById(EntityDAO dao, String strDepartId)
	{		
		String strDepart = "";
		try
		{
			if (strDepartId!=null && strDepartId.trim().length()>0)
			{
				
				String strSql = "select m_branchName from BranchMeans where m_branchMeansId = '"+strDepartId+"'";
				List ls = dao.searchEntities(strSql);
				if(ls != null && ls.size() > 0)
				{
					strDepart = (String)ls.get(0);
				}
			}
		}catch(Exception er)
		{
			er.printStackTrace();
		}
		return strDepart;
	}

}
