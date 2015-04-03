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
 * ����: ��ι���ҵ����
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
	 * ����:����������ѯ���
	 * @param strOp_Man_Id		������
	 * @param strIn_Out_Type	�������
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
			throw new Exception("����������ѯ��γ���:" + er.getMessage());
		}
				
		return pt;
	}

	/**
	 * ����:���ݰ��ID��ð��
	 * @param id	���ID
	 * @return 
	 * @throws Exception
	 */
	public BaseShift getShiftById(String id) throws Exception {
		
		return baseShiftDAO.getShiftById(id);
	}
	
	/**
	 * ����:���ݰ��ID��ð��
	 * @param strIn_Out_Type	�������
	 * @return 
	 * @throws Exception
	 */
	public BaseShift getLastShift(String strIn_Out_Type) throws Exception {
		return baseShiftDAO.getLastShift(strIn_Out_Type);
		
	}

	/**
	 * ����:���Ӱ��
	 * @param shift	���
	 * @throws Exception
	 */
	public String addShift(BaseShift shift) throws Exception {
		
		String back_msg = "";
		
		//��֤�ð�����Ƿ��ظ�����ֹ�����ͻ���ͬʱ��Ӹð�Σ�
		if(baseShiftDAO.isShiftExist(shift.getM_strShiftName(), shift.getM_strIn_Out_Type())){
			
			back_msg = "�ð���Ѿ����ڣ�������ӣ�";
		}else{
			//���һ����εı�־��Ҫ���ã�ȡ����һ�����
			BaseShift lastshift = getLastShift(shift.getM_strIn_Out_Type());
	        baseShiftDAO.addShift(shift, lastshift);
		}
        return back_msg;
	}
	
	/**
	 * ����:��ȡ���а���б�
	 * @return 
	 * @throws Exception
	 */
	public List getShiftList() throws Exception {
		
		return baseShiftDAO.getShiftList();
	}

	/**
	 * ����:�޸İ��
	 * @param shift	���
	 * @throws Exception
	 */
	public void updateShift(BaseShift shift) throws Exception {
		
		baseShiftDAO.updateShift(shift);
		
	}

	/**
	 * ����:ɾ�����
	 * @param id	���ID
	 * @throws Exception
	 */
	public void deleteShift(String id) throws Exception {
		
		baseShiftDAO.deleteShift(id);
	}
}