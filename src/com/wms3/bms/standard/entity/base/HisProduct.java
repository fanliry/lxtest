package com.wms3.bms.standard.entity.base;


import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;

/**
 * ����������
 * @author zhi
 *
 */
public class HisProduct
{
	private String m_strHisProductId;				/*Id*/
	private String m_strOutType;					/*��������*/
	private String m_strFlag;						/*���� 1.��Ʒ��� 2.��Ʒ״̬*/
	private String m_strTypeVale;					/*���ͱ��� ��Ʒ������Ʒ״̬ID*/				
	
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
	 * ���ܣ����ݲ�Ʒ�����ȡ��װ�б�
	 * @param dao
	 * @param id ��Ʒ����
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
	 * ���ܣ���ȡ�ɳ�����Ʒ�����б�
	 * @param dao
	 * @param out_type ��������
	 * @param c yplb ��Ʒ��� wpzt ��Ʒ״̬
	 * @return
	 */
	public List getListByRId(EntityDAO dao, String out_type, String c) 
	{
		String hql = "from HisProduct where m_strOutType='"+out_type+"' and m_strFlag='"+ c+"'";
		List ls = dao.searchEntities(hql);
		return ls;
	}
	/**
	 * ���ܣ���ȡ�ɳ�����Ʒ�������
	 * @param dao
	 * @param out_type ��������
	 * @param c yplb ��Ʒ��� wpzt ��Ʒ״̬
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