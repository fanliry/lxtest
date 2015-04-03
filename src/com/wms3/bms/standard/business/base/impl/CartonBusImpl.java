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
 * 描述: 周转箱管理业务类
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
	 * 功能:根据条件查询周转箱
	 * @param cartonid	装箱代码
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
			throw new Exception("根据条件查询周转箱出错:" + er.getMessage());
		}
				
		return pt;
	}

	/**
	 * 功能:根据周转箱ID获得库区
	 * @param id	周转箱ID
	 * @return 
	 * @throws Exception
	 */
	public BaseCarton getCartonById(String id) throws Exception {
		
		return baseCartonDAO.getCartonById(id);
	}

	/**
	 * 功能:增加周转箱
	 * @param carton	周转箱
	 * @throws Exception
	 */
	public void addCarton(BaseCarton carton) throws Exception {
		
		//获得编码
        String id = baseCartonDAO.getMaxCartonNo();
        id = SeqTool.getCode(Integer.parseInt(id) + 1, 4);
        carton.setCartonid(id);
        baseCartonDAO.addCarton(carton);
	}
	
	/**
	 * 功能:获取所有周转箱列表
	 * @return 
	 * @throws Exception
	 */
	public List getCartonList() throws Exception {
		
		return baseCartonDAO.getCartonList();
	}

	/**
	 * 功能:修改周转箱
	 * @param carton	周转箱
	 * @throws Exception
	 */
	public void updateCarton(BaseCarton carton) throws Exception {
		
		baseCartonDAO.updateCarton(carton);
		
	}

	/**
	 * 功能:删除周转箱
	 * @param id	周转箱ID
	 * @throws Exception
	 */
	public void deleteCarton(String id) throws Exception {
		
		baseCartonDAO.deleteCarton(id);
	}
	
}