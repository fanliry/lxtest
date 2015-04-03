package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.base.IProductBus;
import com.wms3.bms.standard.dao.base.IBaseProductDao;
import com.wms3.bms.standard.dao.base.impl.BaseProductDaoImpl;
import com.wms3.bms.standard.entity.base.BaseProduct;

/**
 * ����: ��Ʒ����ҵ����
 * @author fangjie
 * 
 */
public class ProductBusImpl implements IProductBus {
	
	protected IBaseProductDao baseProductyDAO = null;
	protected EntityDAO m_dao = null;

	public ProductBusImpl(EntityDAO dao) {
		this.baseProductyDAO = new BaseProductDaoImpl(dao);
		this.m_dao = dao;
	}

	/**
	 * ����:������ƷID�����Ʒ
	 * @param productid	��ƷID
	 * @return
	 * @throws Exception
	 */
	public BaseProduct getProductById(String productid) throws Exception {
		return baseProductyDAO.getProductById(productid);
	}

	/**
	 * ����:������Ʒ
	 * @param product	��Ʒ
	 * @throws Exception
	 */
	public void addProduct(BaseProduct product) throws Exception {
        //String typeid = product.getPttypeid();		//��Ʒ���ID
		
		// ���ͬ����µ������ƷID
		String no = baseProductyDAO.getMaxProductIdz();
		
		String productid = SeqTool.getCode(Integer.parseInt(no) + 1, 7);
		product.setProductid(productid);
		baseProductyDAO.addProduct(product);
	}

	/**
	 * ����:�޸���Ʒ
	 * @param product	��Ʒ
	 * @throws Exception
	 */
	public void updateProduct(BaseProduct product) throws Exception {
		baseProductyDAO.updateProduct(product);
	}

	/**
	 * ����:ɾ����Ʒ
	 * @param id	��ƷID
	 * @throws Exception
	 */
	public void deleteProduct(String id) throws Exception {
		baseProductyDAO.deleteProduct(id);
	}
	
    /**
     * ����:������Ʒ��������Ʒ�������ж����
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.base.IProductBus#getProductByCode(java.lang.String)
     */
    public List getProductByCode(String strCode) throws Exception {
        
        return baseProductyDAO.getProductByCode(strCode);
    }
    
    /**
     * ����:���ݲ�Ʒ��������Ʒ
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.base.IProductBus#getProductByCodeInfo(String)
     */
    public List getProductByCodeInfo(String strCode) throws Exception {
        
        return baseProductyDAO.getProductByCodeInfo(strCode);
    }
    
    /**
     * ����:������Ʒ��������Ʒ�������ж����
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.base.IProductBus#getProductByCode(java.lang.String)
     */
    public List getProductByProductCode(String strCode) throws Exception{
    	 return baseProductyDAO.getProductByProductCode(strCode);
    }
    
    /**
	 * ����:����������ѯ��Ʒ
	 * @param productname	��Ʒ����
     * @param productcode   ��Ʒ����
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getProductQuery(String productname, String productcode, String producttype, String strUrl, int maxLine) throws Exception{
		
		PagingTool pt = null;
		
		try {
			String sql = "From BaseProduct as t where 1=1" ;
			
			if(productname != null && productname.trim().length() > 0){
				sql += " and t.productname like '%" + productname + "%'";
			}
            if(productcode != null && productcode.trim().length() > 0){
                sql += " and t.productCode='" + productcode + "'";
            }
            if(producttype != null && producttype.trim().length() > 0){
                sql += " and t.producttype='" + producttype + "'";
            }
			
			String strHQL = sql + " order by t.productid";
			String strCountHQL = "select count(*)" + sql ;
			
			pt = CommonPagination.getPagingTool(m_dao, strCountHQL ,strHQL, strUrl, 1, maxLine);
			
		} catch (Exception er) {
			throw new Exception("����������ѯ��Ʒ����:" + er.getMessage());
		}
				
		return pt;
	}
    /**
     * ����: ��ѯ������Ʒ     
	 * @return 
	 * @throws Exception
	 */
    public List searchEntitieAll() throws Exception {
        try{
           
                String strSql = " from BaseProduct ";
                List lsProduct = m_dao.searchEntities(strSql);
                return lsProduct;
            
        }catch(Exception er){
            throw new Exception("������Ʒ��������Ʒ����" + er.getMessage());
        }
       // return null;
    }
}