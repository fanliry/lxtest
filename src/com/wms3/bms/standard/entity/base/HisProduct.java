package com.wms3.bms.standard.entity.base;


import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;

/**
 * 描述：批次
 * @author zhi
 *
 */
public class HisProduct
{
	private String m_strHisProductId;				/*Id*/
	private String m_strOutType;					/*出库类型*/
	private String m_strFlag;						/*类型 1.样品类别 2.物品状态*/
	private String m_strTypeVale;					/*类型表中 样品类别和物品状态ID*/				
	
	private String m_strTypeValeName;
	

	public String getM_strHisProductId() {
		return m_strHisProductId;
	}

	public void setM_strHisProductId(String hisProductId) {
		m_strHisProductId = hisProductId;
	}

	public String getM_strTypeValeName() {
		return m_strTypeValeName;
	}

	public void setM_strTypeValeName(String typeValeName) {
		m_strTypeValeName = typeValeName;
	}

	public HisProduct() {}
	

	public String getM_strFlag() {
		return m_strFlag;
	}

	public void setM_strFlag(String flag) {
		m_strFlag = flag;
	}

	public String getM_strTypeVale() {
		return m_strTypeVale;
	}

	public void setM_strTypeVale(String typeVale) {
		m_strTypeVale = typeVale;
	}

	public String getM_strOutType() {
		return m_strOutType;
	}

	public void setM_strOutType(String outType) {
		m_strOutType = outType;
	}

	/**
	 * 功能：根据产品代码获取包装列表
	 * @param dao
	 * @param id 产品代码
	 * @return
	 */
	public HisProduct getInfoById(EntityDAO dao, String id)
	{
		HisProduct ta = null;
		String hql = "from HisProduct where m_strHisProductId='"+id+"'";
		List ls = dao.searchEntities(hql);
		if(ls != null && ls.size() == 1)
		{
			ta = (HisProduct)ls.get(0);
		}
		return ta;
	}
	/**
	 * 功能：获取可出库物品类型列表
	 * @param dao
	 * @param out_type 出库类型
	 * @param c yplb 样品类别 wpzt 物品状态
	 * @return
	 */
	public List getListByRId(EntityDAO dao, String out_type, String c) 
	{
		String hql = "from HisProduct where m_strOutType='"+out_type+"' and m_strFlag='"+ c+"'";
		List ls = dao.searchEntities(hql);
		return ls;
	}
	/**
	 * 功能：获取可出库物品类型组合
	 * @param dao
	 * @param out_type 出库类型
	 * @param c yplb 样品类别 wpzt 物品状态
	 * @return
	 */
	public String getCAdd(EntityDAO dao, String out_type, String c){
		String add = "";
		if(out_type.equals("11")){
			add = "'1'";
		}
		else{
			HisProduct ta = null;
			List ls = getListByRId(dao, out_type, c);
			for(int j=0; j<ls.size(); j++){
				ta = (HisProduct)ls.get(j);
				add += "'" + ta.getM_strTypeVale() + "',";
			}
			if(add == ""){
				add = "'1'";
			}
			else{
				add = add.substring(0, add.length()-1);
			}
		}
		return add;
	}
}