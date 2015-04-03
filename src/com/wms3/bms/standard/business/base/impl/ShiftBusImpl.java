package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.business.base.IShiftBus;
import com.wms3.bms.standard.dao.base.IBaseShiftDao;
import com.wms3.bms.standard.dao.base.impl.BaseShiftDaoImpl;
import com.wms3.bms.standard.entity.base.BaseShift;

/**
 * 
 * 描述: 班次管理业务类
 * @author fangjie
 * @since WMS 3.0
 */
public class ShiftBusImpl implements IShiftBus {
	
	protected IBaseShiftDao baseShiftDAO = null;
	protected EntityDAO m_dao = null;

	/**
	 * @param dao
	 */
	public ShiftBusImpl(EntityDAO dao) {
		this.baseShiftDAO = new BaseShiftDaoImpl(dao);
		this.m_dao = dao;
	}


	/**
	 * 功能:根据条件查询班次
	 * @param strOp_Man_Id		负责人
	 * @param strIn_Out_Type	入出类型
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getShiftQuery(String strOp_Man_Id, String strIn_Out_Type, String strUrl, int maxLine) throws Exception {
		
		PagingTool pt = null;
		
		try {
			String sql = " From BaseShift as t where 1=1" ;
			
			if(strOp_Man_Id != null && strOp_Man_Id.trim().length() > 0){
				sql += " and t.m_strOp_Man_Id='" + strOp_Man_Id + "'";
			}
			if(strIn_Out_Type != null && strIn_Out_Type.trim().length() > 0){
				sql += " and t.m_strIn_Out_Type='" + strIn_Out_Type + "'";
			}
			
			String strHQL = sql + " order by t.m_strShiftName, t.m_strIn_Out_Type";
			String strCountHQL = "select count(*)" + sql ;
			
			pt = CommonPagination.getPagingTool(m_dao, strCountHQL ,strHQL, strUrl, 1, maxLine);
			
		} catch (Exception er) {
			throw new Exception("根据条件查询班次出错:" + er.getMessage());
		}
				
		return pt;
	}

	/**
	 * 功能:根据班次ID获得班次
	 * @param id	班次ID
	 * @return 
	 * @throws Exception
	 */
	public BaseShift getShiftById(String id) throws Exception {
		
		return baseShiftDAO.getShiftById(id);
	}
	
	/**
	 * 功能:根据班次ID获得班次
	 * @param strIn_Out_Type	入出类型
	 * @return 
	 * @throws Exception
	 */
	public BaseShift getLastShift(String strIn_Out_Type) throws Exception {
		return baseShiftDAO.getLastShift(strIn_Out_Type);
		
	}

	/**
	 * 功能:增加班次
	 * @param shift	班次
	 * @throws Exception
	 */
	public String addShift(BaseShift shift) throws Exception {
		
		String back_msg = "";
		
		//验证该班次名是否重复（防止其它客户端同时添加该班次）
		if(baseShiftDAO.isShiftExist(shift.getM_strShiftName(), shift.getM_strIn_Out_Type())){
			
			back_msg = "该班次已经存在！不能添加！";
		}else{
			//最后一个班次的标志需要重置，取得上一个班次
			BaseShift lastshift = getLastShift(shift.getM_strIn_Out_Type());
	        baseShiftDAO.addShift(shift, lastshift);
		}
        return back_msg;
	}
	
	/**
	 * 功能:获取所有班次列表
	 * @return 
	 * @throws Exception
	 */
	public List getShiftList() throws Exception {
		
		return baseShiftDAO.getShiftList();
	}

	/**
	 * 功能:修改班次
	 * @param shift	班次
	 * @throws Exception
	 */
	public void updateShift(BaseShift shift) throws Exception {
		
		baseShiftDAO.updateShift(shift);
		
	}

	/**
	 * 功能:删除班次
	 * @param id	班次ID
	 * @throws Exception
	 */
	public void deleteShift(String id) throws Exception {
		
		baseShiftDAO.deleteShift(id);
	}
}