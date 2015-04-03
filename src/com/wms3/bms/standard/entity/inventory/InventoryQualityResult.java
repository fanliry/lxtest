package com.wms3.bms.standard.entity.inventory;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.tools.CommonBusiness;

/**
 * 描述：放行单
 * @author yao
 *
 */
public class InventoryQualityResult 
{
	private String m_strCheckResultId;			/*质检结果单ID*/	
	private String m_strLotNumber;  		    /*批号*/
	private String m_strOpManId;				/*操作人ID*/
	private String m_strCreateDate;   			/*单据日期*/
	private String m_strNum;					/*数量*/
	private String m_strProductid;			    /*产品id*/
	
	
	


	public InventoryQualityResult() {}
	
	
	
	public String getM_strCheckResultId() {
		return m_strCheckResultId;
	}

	public void setM_strCheckResultId(String checkResultId) {
		m_strCheckResultId = checkResultId;
	}

	public String getM_strCreateDate() {
		return m_strCreateDate;
	}

	public void setM_strCreateDate(String createDate) {
		m_strCreateDate = createDate;
	}
	public String getM_strOpManId() {
		return m_strOpManId;
	}

	public void setM_strOpManId(String opManId) {
		m_strOpManId = opManId;
	}

	public String makeId(EntityDAO dao){
		CommonBusiness cb = new CommonBusiness();
		return cb.getPPNo("CR", 4, "select max(m_strCheckResultId) from InventoryCheckResult", dao);
	}
	/**
	 * 功能：获取查询HQL语句
	 * @param end_time 
	 * @param start_time 
	 * @param is_up 
	 * @return
	 */
	public String getQueryHQL(String start_time, String end_time, String is_up)
	{
		String hql = "from InventoryQualityResult where 1=1";
		if(start_time != null && start_time.trim().length() > 0){
			hql += " and substring(m_strCreateDate, 1, 10)>='" + start_time + "'";
		}
		if(end_time != null && end_time.trim().length() > 0){
			hql += " and substring(m_strCreateDate, 1, 10)<='" + end_time + "'";
		}
		if(is_up != null && is_up.trim().length() > 0){
			hql += " and m_strIsUp='" + is_up + "'";
		}
		return hql;
	}
	/**
	 * 功能：根据ID获取信息
	 * @param dao
	 * @param id 单据ID
	 * @return
	 */
	public InventoryQualityResult getInfoById(EntityDAO dao, String id)
	{
		InventoryQualityResult ta = null;
		
		String hql = "From InventoryQualityResult where m_strCheckResultId='"+ id + "'";
		List ls = dao.searchEntities(hql);
		if(ls != null && ls.size() == 1)
		{
			ta = (InventoryQualityResult)ls.get(0);
		}
		return ta;
	}


	public String getM_strLotNumber() {
		return m_strLotNumber;
	}


	public void setM_strLotNumber(String mStrLotNumber) {
		m_strLotNumber = mStrLotNumber;
	}


	public String getM_strNum() {
		return m_strNum;
	}


	public void setM_strNum(String mStrNum) {
		m_strNum = mStrNum;
	}


	public String getM_strProductid() {
		return m_strProductid;
	}


	public void setM_strProductid(String mStrProductid) {
		m_strProductid = mStrProductid;
	}

}