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
 * 描述: 物品管理业务类
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
	 * 功能:根据物品ID获得物品
	 * @param productid	物品ID
	 * @return
	 * @throws Exception
	 */
	public BaseProduct getProductById(String productid) throws Exception {
		return baseProductyDAO.getProductById(productid);
	}

	/**
	 * 功能:增加物品
	 * @param product	物品
	 * @throws Exception
	 */
	public void addProduct(BaseProduct product) throws Exception {
        //String typeid = product.getPttypeid();		//物品类别ID
		
		// 获得同类别下的最大物品ID
		String no = baseProductyDAO.getMaxProductIdz();
		
		String productid = SeqTool.getCode(Integer.parseInt(no) + 1, 7);
		product.setProductid(productid);
		baseProductyDAO.addProduct(product);
	}

	/**
	 * 功能:修改物品
	 * @param product	物品
	 * @throws Exception
	 */
	public void updateProduct(BaseProduct product) throws Exception {
		baseProductyDAO.updateProduct(product);
	}

	/**
	 * 功能:删除物品
	 * @param id	物品ID
	 * @throws Exception
	 */
	public void deleteProduct(String id) throws Exception {
		baseProductyDAO.deleteProduct(id);
	}
	
    /**
     * 功能:根据物品条码获得物品（可能有多个）
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.base.IProductBus#getProductByCode(java.lang.String)
     */
    public List getProductByCode(String strCode) throws Exception {
        
        return baseProductyDAO.getProductByCode(strCode);
    }
    
    /**
     * 功能:根据产品编码获得物品
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.base.IProductBus#getProductByCodeInfo(String)
     */
    public List getProductByCodeInfo(String strCode) throws Exception {
        
        return baseProductyDAO.getProductByCodeInfo(strCode);
    }
    
    /**
     * 功能:根据物品条码获得物品（可能有多个）
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.base.IProductBus#getProductByCode(java.lang.String)
     */
    public List getProductByProductCode(String strCode) throws Exception{
    	 return baseProductyDAO.getProductByProductCode(strCode);
    }
    
    /**
	 * 功能:根据条件查询物品
	 * @param productname	物品名称
     * @param productcode   产品编码
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
			throw new Exception("根据条件查询物品出错:" + er.getMessage());
		}
				
		return pt;
	}
    /**
     * 功能: 查询所有物品     
	 * @return 
	 * @throws Exception
	 */
    public List searchEntitieAll() throws Exception {
        try{
           
                String strSql = " from BaseProduct ";
                List lsProduct = m_dao.searchEntities(strSql);
                return lsProduct;
            
        }catch(Exception er){
            throw new Exception("根据物品条码获得物品出错：" + er.getMessage());
        }
       // return null;
    }
}