package com.wms3.bms.standard.entity.outerInterface;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;

/**
 * ˵�������۵�
 * @author yao
 */
public class SaleForm 
{
	private String m_strSaleFormId ;	//���۶�����
	private String m_strSaleFormType ;	//�������� �������ͺ�ΪC01��C03��C08��C21��C27   ���۳��⣬�ⲿ�������⣬�ڲ��������⣬���۳��⣬�����˻�
	private String m_strCustomerId;		//�ͻ�ID
	private String m_strDownTime;		//��������ʱ��
	private String m_strIsOut;			//�Ƿ������
	private String m_strIsFinish;		//�Ƿ���ɳ���
	private String m_strIsInvalid;		//�Ƿ���Ч
	
	/*��������*/
	private String m_strCustomerName;		//�ͻ�����
	private String m_strSaleFormTypeName ;	//������������
	/**
	 * ����:Ĭ�Ϲ�����
	 */
	public SaleForm() {}

	
	public String getM_strDownTime() {
		return m_strDownTime;
	}

	public void setM_strDownTime(String downTime) {
		m_strDownTime = downTime;
	}

	public String getM_strCustomerId() {
		return m_strCustomerId;
	}

	public void setM_strCustomerId(String customerId) {
		m_strCustomerId = customerId;
	}

	public String getM_strCustomerName() {
		return m_strCustomerName;
	}

	public void setM_strCustomerName(String customerName) {
		m_strCustomerName = customerName;
	}

	public String getM_strIsInvalid() {
		return m_strIsInvalid;
	}

	public void setM_strIsInvalid(String isInvalid) {
		m_strIsInvalid = isInvalid;
	}

	public String getM_strIsFinish() {
		return m_strIsFinish;
	}

	public void setM_strIsFinish(String isFinish) {
		m_strIsFinish = isFinish;
	}

	public String getM_strIsOut() {
		return m_strIsOut;
	}

	public void setM_strIsOut(String isOut) {
		m_strIsOut = isOut;
	}

	public String getM_strSaleFormId() {
		return m_strSaleFormId;
	}

	public void setM_strSaleFormId(String saleFormId) {
		m_strSaleFormId = saleFormId;
	}
	
	public String getM_strSaleFormTypeName() {
		return m_strSaleFormTypeName;
	}


	public void setM_strSaleFormTypeName(String m_strSaleFormTypeName) {
		this.m_strSaleFormTypeName = m_strSaleFormTypeName;
	}


	/**
	 * ����:����δ������δ���������۵����б�
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	public List getNotOverList(EntityDAO dao) throws Exception {
		String hql = getQueryHQL(null, null, null, null, "N", "N", "N");
		List ls = dao.searchEntities(hql);
		return ls;
	}
	/**
	 * ��ȡ��ѯ��HQL���
	 * @param sale_form_id
	 * @param customer_id
	 * @param start_time
	 * @param end_time
	 * @param is_finish
	 * @return
	 */
	public String getQueryHQL(String sale_form_id, String customer_id, String start_time, String end_time,String m_strIsOut,String is_finish, String is_invalid) {
		String hql = "from SaleForm where 1=1";
		if(sale_form_id != null && sale_form_id.trim().length() > 0) {
			hql += " and m_strSaleFormId='" + sale_form_id + "'";
		}
		if(customer_id != null && customer_id.trim().length() > 0) {
			hql += " and m_strCustomerId='" + customer_id + "'";
		}
		if(start_time != null && start_time.trim().length() > 0) {
			hql += " and substring(m_strDownTime, 0, 11)>='" + start_time + "'";
		}
		if(end_time != null && end_time.trim().length() > 0) {
			hql += " and substring(m_strDownTime, 0, 11)<='" + end_time + "'";
		}
		if(m_strIsOut != null && m_strIsOut.trim().length() > 0) {
			hql += " and m_strIsOut='" + m_strIsOut + "'";
		}
		if(is_finish != null && is_finish.trim().length() > 0) {
			hql += " and m_strIsFinish='" + is_finish + "'";
		}
		if(is_invalid != null && is_invalid.trim().length() > 0) {
			hql += " and m_strIsInvalid='" + is_invalid + "'";
		}
		hql += " order by m_strSaleFormId";
		return hql;
	}
	/**
	 * ����ID��ȡ��Ϣ
	 * @param dao
	 * @param id
	 * @return
	 */
	public SaleForm getInfoById(EntityDAO dao, String id){
		SaleForm ta = null;
		String hql = "from SaleForm where m_strSaleFormId='" + id + "'";
		List ls = dao.searchEntities(hql);
		if(ls != null && ls.size() == 1){
			ta = (SaleForm)ls.get(0);
		}
		return ta;
	}


	public String getM_strSaleFormType() {
		return m_strSaleFormType;
	}


	public void setM_strSaleFormType(String saleFormType) {
		m_strSaleFormType = saleFormType;
	}
}
