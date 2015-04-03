package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseProductDao;
import com.wms3.bms.standard.entity.base.BaseProduct;

/**
 * 描述: 物品DAO类
 * @author fangjie 
 * 
 */
public class BaseProductDaoImpl implements IBaseProductDao {

	/** 数据库基本的DAO操作 */
    protected EntityDAO m_dao;
    
	public BaseProductDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}
	
	/**
	 * 功能:根据物品ID获得物品
	 * @param id	物品ID
	 * @return
	 * @throws Exception
	 */
	public BaseProduct getProductById(String id) throws Exception {
		
		if(id!=null && !id.equals(""))
		{
			String strSql = " from BaseProduct as t where t.productid='" + id + "'";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && ls.size() > 0){
				return (BaseProduct)ls.get(0);
			}
		}
		return null;
	}

	/**
	 * 功能:增加物品
	 * @param product	物品
	 * @throws Exception
	 */
	public void addProduct(BaseProduct product) throws Exception {
		
		m_dao.save(product);
	}

	/**
	 * 功能:修改物品
	 * @param product	物品
	 * @throws Exception
	 */
	public void updateProduct(BaseProduct product) throws Exception {
		m_dao.update(product);
	}

	/**
	 * 功能:删除物品
	 * @param id	物品ID
	 * @throws Exception
	 */
	public void deleteProduct(String id) throws Exception {
		if(id != null && !id.equals(""))
		{
			String strSql  = " delete BaseProduct as t where t.productid='" + id + "'";
			m_dao.deleteSql(strSql);
		}
	}
	
    /**
     * 功能:根据物品条码获得物品（可能有多个）
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.base.IBaseProductDao#getProductByCode(java.lang.String)
     */
    public List getProductByCode(String strCode) throws Exception {
        try{
            if(strCode != null){
                String strSql = "select bp.productid, bp.productname from BaseProduct as bp where bp.barcode='" + strCode + "'";
                List lsProduct = m_dao.searchEntities(strSql);
                return lsProduct;
            }
        }catch(Exception er){
            throw new Exception("根据物品条码获得物品出错：" + er.getMessage());
        }
        return null;
    }
    
    /**
     * 功能:根据产品编码获得物品
     * (non-Javadoc)
     * @see com.wms3.bms.standard.dao.base.IBaseProductDao#getProductByCodeInfo(String);
     */
    public List getProductByCodeInfo(String strCode) throws Exception {
        try{
            if(strCode != null){
                String strSql = "select bp.productid, bp.productname, bp.recunit, bp.spec, bp.barcode from BaseProduct as bp where bp.productCode='" + strCode + "'";
                List lsProduct = m_dao.searchEntities(strSql);
                return lsProduct;
            }
        }catch(Exception er){
            throw new Exception("根据产品编码获得物品出错：" + er.getMessage());
        }
        return null;
    }
    
    /**
     * 功能:根据物品编码获得物品（可能有多个）
     * @author hug 2012-3-14 
     * @param strCode
     * @return
     * @throws Exception
     */
    public List getProductByProductCode(String strCode) throws Exception{
    	 try{
             if(strCode != null){
                 String strSql = "select bp.productid, bp.productname from BaseProduct as bp where bp.productCode='" + strCode + "'";
                 List lsProduct = m_dao.searchEntities(strSql);
                 return lsProduct;
             }
         }catch(Exception er){
             throw new Exception("根据物品条码获得物品出错：" + er.getMessage());
         }
         return null;
    }
    /**
     * 功能:根据物品类别获得物品最大ID
     * @param typeid
     * @return
     * @throws Exception
     */
	public String getMaxProductId(String typeid) throws Exception {
		
		String maxNo = "0000";
		try{
		
			String strSql = "from BaseProduct as t where t.pttypeid='" + typeid +"' order by t.productid desc";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && !ls.isEmpty()){
			
				BaseProduct product = (BaseProduct)ls.get(0);
				maxNo = product.getProductid().substring(3, 7);
			}
		}catch(Exception er){
		
			throw new Exception("获得类别["+typeid+"]的最大的一个物品编码时候失败:"+er.getMessage());
		}
		return maxNo;
	}
    /**
     * 功能:获得最大物品Id
     * @return
     * @throws Exception
     */
	public String getMaxProductIdz() throws Exception {
		
		String maxNo = "0000";
		try{
		
			String strSql = "from BaseProduct as t order by t.productid desc";
			List ls = m_dao.searchEntities(strSql);
			if(ls != null && !ls.isEmpty()){
			
				BaseProduct product = (BaseProduct)ls.get(0);
				maxNo = product.getProductid().toString();
			}
		}catch(Exception er){
		
			throw new Exception("获得获得最大物品Id时候失败:"+er.getMessage());
		}
		return maxNo;
	}
}
