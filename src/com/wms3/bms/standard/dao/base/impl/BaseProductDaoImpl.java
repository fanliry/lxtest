package com.wms3.bms.standard.dao.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.dao.base.IBaseProductDao;
import com.wms3.bms.standard.entity.base.BaseProduct;

/**
 * ����: ��ƷDAO��
 * @author fangjie 
 * 
 */
public class BaseProductDaoImpl implements IBaseProductDao {

	/** ���ݿ������DAO���� */
    protected EntityDAO m_dao;
    
	public BaseProductDaoImpl(EntityDAO dao) {
		this.m_dao = dao;
	}
	
	/**
	 * ����:������ƷID�����Ʒ
	 * @param id	��ƷID
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
	 * ����:������Ʒ
	 * @param product	��Ʒ
	 * @throws Exception
	 */
	public void addProduct(BaseProduct product) throws Exception {
		
		m_dao.save(product);
	}

	/**
	 * ����:�޸���Ʒ
	 * @param product	��Ʒ
	 * @throws Exception
	 */
	public void updateProduct(BaseProduct product) throws Exception {
		m_dao.update(product);
	}

	/**
	 * ����:ɾ����Ʒ
	 * @param id	��ƷID
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
     * ����:������Ʒ��������Ʒ�������ж����
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
            throw new Exception("������Ʒ��������Ʒ����" + er.getMessage());
        }
        return null;
    }
    
    /**
     * ����:���ݲ�Ʒ��������Ʒ
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
            throw new Exception("���ݲ�Ʒ��������Ʒ����" + er.getMessage());
        }
        return null;
    }
    
    /**
     * ����:������Ʒ��������Ʒ�������ж����
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
             throw new Exception("������Ʒ��������Ʒ����" + er.getMessage());
         }
         return null;
    }
    /**
     * ����:������Ʒ�������Ʒ���ID
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
		
			throw new Exception("������["+typeid+"]������һ����Ʒ����ʱ��ʧ��:"+er.getMessage());
		}
		return maxNo;
	}
    /**
     * ����:��������ƷId
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
		
			throw new Exception("��û�������ƷIdʱ��ʧ��:"+er.getMessage());
		}
		return maxNo;
	}
}
