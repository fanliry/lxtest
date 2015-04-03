package com.wms3.bms.standard.business.base.impl;

import java.util.ArrayList;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.base.ICustomerBus;
import com.wms3.bms.standard.dao.base.IBaseCustomerDao;
import com.wms3.bms.standard.dao.base.impl.BaseCustomerDaoImpl;
import com.wms3.bms.standard.entity.base.BaseCustomer;

/**
 * 
 * ����: �ͻ�����ҵ����
 * @author fangjie
 * @since WMS 3.0
 */
public class CustomerBusImpl implements ICustomerBus {
	
	protected IBaseCustomerDao baseCustomerDAO = null;
	protected EntityDAO m_dao;

	/**
	 * @param dao
	 */
	public CustomerBusImpl(EntityDAO dao) {
		this.baseCustomerDAO = new BaseCustomerDaoImpl(dao);
		this.m_dao = dao;
	}


	/**
	 * ����:����������ѯ�ͻ�
	 * @param customername	�ͻ�����
	 * @param customertype	�ͻ�����
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getCustomerQuery(String customername, String customertype, String strUrl, int maxLine) throws Exception {
		
		PagingTool pt = null;
		
		try {
			String sql = "From BaseCustomer as t where 1=1" ;
			
			if(customertype != null && customertype.trim().length() > 0){
				sql += " and t.customertype='" + customertype + "'";
			}
			if(customername != null && customername.trim().length() > 0){
				sql += " and t.customername like '%" + customername + "%'";
			}
			
			String strHQL = sql + " order by t.customerid";
			String strCountHQL = "select count(*)" + sql;
			
			pt = CommonPagination.getPagingTool(m_dao, strCountHQL ,strHQL, strUrl, 1, maxLine);
			
		} catch (Exception er) {
			throw new Exception("����������ѯ�ͻ�����:" + er.getMessage());
		}
				
		return pt;
	}

	/**
	 * ����:����������ѯ�ͻ�
	 * @param customername	�ͻ�����
	 * @param customertype	�ͻ�����
	 * @return 
	 * @throws Exception
	 */
	public List<BaseCustomer> getCustomerQueryRF(String customername, String customertype) throws Exception {
		
		List<BaseCustomer> list = new ArrayList<BaseCustomer>();
		
		try {
			String sql = "From BaseCustomer as t where 1=1" ;
			
			if(customertype != null && customertype.trim().length() > 0){
				sql += " and t.customertype='" + customertype + "'";
			}
			if(customername != null && customername.trim().length() > 0){
				sql += " and t.customername like '%" + customername + "%'";
			}
			
			String strHQL = sql + " order by t.customerid";
			
			list = m_dao.searchEntities(strHQL);
			
		} catch (Exception er) {
			throw new Exception("����������ѯ�ͻ�����:" + er.getMessage());
		}
				
		return list;
	}
	
	
	/**
	 * ����:���ݿͻ�ID��ÿ���
	 * @param id	�ͻ�ID
	 * @return 
	 * @throws Exception
	 */
	public BaseCustomer getCustomerById(String id) throws Exception {
		
		return baseCustomerDAO.getCustomerById(id);
	}

	/**
	 * ����:���ӿͻ�
	 * @param customer	�ͻ�
	 * @throws Exception
	 */
	public void addCustomer(BaseCustomer customer) throws Exception {
		
		//��ñ���
        String id = baseCustomerDAO.getMaxCustomerNo();
        id = SeqTool.getCode(Integer.parseInt(id) + 1, 3);
        customer.setCustomerid(id);
        baseCustomerDAO.addCustomer(customer);
	}
	
	/**
	 * ����:��ȡ���пͻ��б�
	 * @return 
	 * @throws Exception
	 */
	public List getCustomerList() throws Exception {
		
		return baseCustomerDAO.getCustomerList();
	}

	/**
	 * ����:�޸Ŀͻ�
	 * @param customer	�ͻ�
	 * @throws Exception
	 */
	public void updateCustomer(BaseCustomer customer) throws Exception {
		
		baseCustomerDAO.updateCustomer(customer);
		
	}

	/**
	 * ����:ɾ���ͻ�
	 * @param id	�ͻ�ID
	 * @throws Exception
	 */
	public void deleteCustomer(String id) throws Exception {
		
		baseCustomerDAO.deleteCustomer(id);
	}
	
}