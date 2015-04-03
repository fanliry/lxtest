package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.base.IProducttypeBus;
import com.wms3.bms.standard.dao.base.IBaseProducttypeDao;
import com.wms3.bms.standard.dao.base.impl.BaseProducttypeDaoImpl;
import com.wms3.bms.standard.entity.base.BaseProducttype;

/**
 * 
 * 描述: 物品类别管理业务类
 * @author fangjie
 * @since WMS 3.0
 */
public class ProducttypeBusImpl implements IProducttypeBus {
	
	protected IBaseProducttypeDao baseProducttypeDAO = null;

	/**
	 * @param dao
	 */
	public ProducttypeBusImpl(EntityDAO dao) {
		this.baseProducttypeDAO = new BaseProducttypeDaoImpl(dao);
	}


	/**
	 * 功能:根据条件查询库区
	 * @param ptname	类别名
	 * @return 
	 * @throws Exception
	 */
	public List getProducttypeQuery(String ptname) throws Exception {
		
		return baseProducttypeDAO.getProducttypeQuery(ptname);
	}

	/**
	 * 功能:根据物品类别ID获得库区
	 * @param id	物品类别ID
	 * @return 
	 * @throws Exception
	 */
	public BaseProducttype getProducttypeById(String id) throws Exception {
		
		return baseProducttypeDAO.getProducttypeById(id);
	}

	/**
	 * 功能:增加物品类别
	 * @param producttype	物品类别
	 * @throws Exception
	 */
	public void addProducttype(BaseProducttype producttype) throws Exception {
		
		//获得同一个父类别下最大的一个物品类别编码
		String parentid = producttype.getParentid();
		String id = baseProducttypeDAO.getMaxProducttypeNo(parentid);
		
		int plength = parentid.length();
		int length = id.length();
		if(length > 3){
			id = id.substring(plength, length);		//取三位
		}
        id = SeqTool.getCode(Integer.parseInt(id) + 1, 3);
        
        producttype.setPtid(parentid + id);
        producttype.setPtno(parentid + id);
        baseProducttypeDAO.addProducttype(producttype);
	}
	
	/**
	 * 功能:获取所有物品类别列表
	 * @return 
	 * @throws Exception
	 */
	public List getProducttypeList() throws Exception {
		
		return baseProducttypeDAO.getProducttypeList();
	}

	/**
	 * 功能:修改物品类别
	 * @param producttype	物品类别
	 * @throws Exception
	 */
	public void updateProducttype(BaseProducttype producttype) throws Exception {
		
		baseProducttypeDAO.updateProducttype(producttype);
		
	}

	/**
	 * 功能:删除物品类别
	 * @param id	物品类别ID
	 * @throws Exception
	 */
	public void deleteProducttype(String id) throws Exception {
		
		baseProducttypeDAO.deleteProducttype(id);
	}
	
}