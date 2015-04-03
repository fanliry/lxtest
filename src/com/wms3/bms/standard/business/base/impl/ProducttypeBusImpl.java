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
 * ����: ��Ʒ������ҵ����
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
	 * ����:����������ѯ����
	 * @param ptname	�����
	 * @return 
	 * @throws Exception
	 */
	public List getProducttypeQuery(String ptname) throws Exception {
		
		return baseProducttypeDAO.getProducttypeQuery(ptname);
	}

	/**
	 * ����:������Ʒ���ID��ÿ���
	 * @param id	��Ʒ���ID
	 * @return 
	 * @throws Exception
	 */
	public BaseProducttype getProducttypeById(String id) throws Exception {
		
		return baseProducttypeDAO.getProducttypeById(id);
	}

	/**
	 * ����:������Ʒ���
	 * @param producttype	��Ʒ���
	 * @throws Exception
	 */
	public void addProducttype(BaseProducttype producttype) throws Exception {
		
		//���ͬһ�������������һ����Ʒ������
		String parentid = producttype.getParentid();
		String id = baseProducttypeDAO.getMaxProducttypeNo(parentid);
		
		int plength = parentid.length();
		int length = id.length();
		if(length > 3){
			id = id.substring(plength, length);		//ȡ��λ
		}
        id = SeqTool.getCode(Integer.parseInt(id) + 1, 3);
        
        producttype.setPtid(parentid + id);
        producttype.setPtno(parentid + id);
        baseProducttypeDAO.addProducttype(producttype);
	}
	
	/**
	 * ����:��ȡ������Ʒ����б�
	 * @return 
	 * @throws Exception
	 */
	public List getProducttypeList() throws Exception {
		
		return baseProducttypeDAO.getProducttypeList();
	}

	/**
	 * ����:�޸���Ʒ���
	 * @param producttype	��Ʒ���
	 * @throws Exception
	 */
	public void updateProducttype(BaseProducttype producttype) throws Exception {
		
		baseProducttypeDAO.updateProducttype(producttype);
		
	}

	/**
	 * ����:ɾ����Ʒ���
	 * @param id	��Ʒ���ID
	 * @throws Exception
	 */
	public void deleteProducttype(String id) throws Exception {
		
		baseProducttypeDAO.deleteProducttype(id);
	}
	
}