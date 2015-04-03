package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.base.ICartonBus;
import com.wms3.bms.standard.dao.base.IBaseCartonDao;
import com.wms3.bms.standard.dao.base.impl.BaseCartonDaoImpl;
import com.wms3.bms.standard.entity.base.BaseCarton;

/**
 * 
 * ����: ��ת�����ҵ����
 * @author fangjie
 * @since WMS 3.0
 */
public class CartonBusImpl implements ICartonBus {
	
	protected IBaseCartonDao baseCartonDAO = null;
	protected EntityDAO m_dao;

	/**
	 * @param dao
	 */
	public CartonBusImpl(EntityDAO dao) {
		this.baseCartonDAO = new BaseCartonDaoImpl(dao);
		this.m_dao = dao;
	}


	/**
	 * ����:����������ѯ��ת��
	 * @param cartonid	װ�����
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getCartonQuery(String cartonid, String strUrl, int maxLine) throws Exception {
		
		PagingTool pt = null;
		
		try {
			String sql = " From BaseCarton as t where 1=1" ;
			
			if(cartonid != null && cartonid.trim().length() > 0){
				sql += " and t.cartonid='" + cartonid + "'";
			}

			String strHQL = sql + " order by t.cartonid";
			String strCountHQL = "select count(*)" + sql;
			
			pt = CommonPagination.getPagingTool(m_dao, strCountHQL ,strHQL, strUrl, 1, maxLine);
			
		} catch (Exception er) {
			throw new Exception("����������ѯ��ת�����:" + er.getMessage());
		}
				
		return pt;
	}

	/**
	 * ����:������ת��ID��ÿ���
	 * @param id	��ת��ID
	 * @return 
	 * @throws Exception
	 */
	public BaseCarton getCartonById(String id) throws Exception {
		
		return baseCartonDAO.getCartonById(id);
	}

	/**
	 * ����:������ת��
	 * @param carton	��ת��
	 * @throws Exception
	 */
	public void addCarton(BaseCarton carton) throws Exception {
		
		//��ñ���
        String id = baseCartonDAO.getMaxCartonNo();
        id = SeqTool.getCode(Integer.parseInt(id) + 1, 4);
        carton.setCartonid(id);
        baseCartonDAO.addCarton(carton);
	}
	
	/**
	 * ����:��ȡ������ת���б�
	 * @return 
	 * @throws Exception
	 */
	public List getCartonList() throws Exception {
		
		return baseCartonDAO.getCartonList();
	}

	/**
	 * ����:�޸���ת��
	 * @param carton	��ת��
	 * @throws Exception
	 */
	public void updateCarton(BaseCarton carton) throws Exception {
		
		baseCartonDAO.updateCarton(carton);
		
	}

	/**
	 * ����:ɾ����ת��
	 * @param id	��ת��ID
	 * @throws Exception
	 */
	public void deleteCarton(String id) throws Exception {
		
		baseCartonDAO.deleteCarton(id);
	}
	
}