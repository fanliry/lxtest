package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.business.base.IBarcodeBus;
import com.wms3.bms.standard.dao.base.IBaseBarcodeDao;
import com.wms3.bms.standard.dao.base.impl.BaseBarcodeDaoImpl;
import com.wms3.bms.standard.entity.base.BaseBarcode;

/**
 * 描述: 条形码管理业务类
 * @author fangjie
 * 
 */
public class BarcodeBusImpl implements IBarcodeBus {
	
	protected IBaseBarcodeDao baseBarcodeDAO = null;
	protected EntityDAO m_dao = null;

	public BarcodeBusImpl(EntityDAO dao) {
		this.baseBarcodeDAO = new BaseBarcodeDaoImpl(dao);
		this.m_dao = dao;
	}
	
	/**
	 * 功能:根据条件查询条形码
	 * @param isused	是否使用
	 * @param maxLine 	分页显示行数
	 * @param strUrl 	地址
	 * @return
	 * @throws Exception
	 */
	public PagingTool getBarcodeQuery(String isused, String strUrl, int maxLine) throws Exception {
		
		PagingTool pt = null;
		try
		{
			String sql = "From BaseBarcode as t where 1=1" ;
			
			if(isused != null && isused.trim().length() > 0){
				sql += " and t.isused = '" + isused + "'";
			}

			String strHQL = sql + " order by t.barcode";
			String strCountHQL = "select count(*)" + sql ;
			
			pt = CommonPagination.getPagingTool(m_dao, strCountHQL ,strHQL, strUrl, 1, maxLine);

				
		}catch(Exception er)
		{
			throw new Exception("根据条件查询条形码出错:" + er.getMessage());
		}
		return pt;
	}

	/**
	 * 功能:根据条形码ID获得条形码
	 * @param id	条形码ID
	 * @return
	 * @throws Exception
	 */
	public BaseBarcode getBarcodeById(String id) throws Exception {
		
		return baseBarcodeDAO.getBarcodeById(id);
	}

	/**
	 * 功能:增加条形码
	 * @param barcode	条形码
	 * @throws Exception
	 */
	public void addBarcode(BaseBarcode barcode) throws Exception {
		
		baseBarcodeDAO.addBarcode(barcode);
		
	}

	/**
	 * 功能:获取所有条形码列表
	 * @return
	 * @throws Exception
	 */
	public List getBarcodeList() throws Exception {

		return baseBarcodeDAO.getBarcodeList();
	}

	/**
	 * 功能:修改条形码
	 * @param barcode	条形码
	 * @throws Exception
	 */
	public void updateBarcode(BaseBarcode barcode) throws Exception {
		
		baseBarcodeDAO.updateBarcode(barcode);
	}

	/**
	 * 功能:删除条形码
	 * @param id	条形码ID
	 * @throws Exception
	 */
	public void deleteBarcode(String id) throws Exception {
		
		baseBarcodeDAO.deleteBarcode(id);
	}
	
	/**
	 * 获取条形码
	 * @param i
	 * @return
	 */
	public String getBarCode(int i) {
		
		String code = null;
		if (i < 10) {
		  code = "00000" + i;
		}
		else if (i < 100) {
		  code = "0000" + i;
		}
		else if (i < 1000) {
		  code = "000" + i;
		}
		else if (i < 10000) {
		  code = "00" + i;
		}
		else if (i < 100000) {
		  code = "0" + i;
		}
		else
		  code = i+"";
		
		return code;
	}

}