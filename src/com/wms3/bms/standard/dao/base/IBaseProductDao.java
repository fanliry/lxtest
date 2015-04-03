package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseProduct;

/**
 * ����: ��ƷDAO��ӿ�
 * @author fangjie
 * 
 */
public interface IBaseProductDao {

	/**
	 * ����:���ݲ�ƷID��ò�Ʒ
	 * @param productid	��ƷID
	 * @return
	 * @throws Exception
	 */
	public BaseProduct getProductById(String productid) throws Exception;

	/**
	 * ����:���Ӳ�Ʒ
	 * @param product	��Ʒ
	 * @throws Exception
	 */
	public void addProduct(BaseProduct product) throws Exception;

	/**
	 * ����:�޸Ĳ�Ʒ
	 * @param product	��Ʒ
	 * @throws Exception
	 */
	public void updateProduct(BaseProduct product) throws Exception;

	/**
	 * ����:ɾ����Ʒ
	 * @param id	��ƷID
	 * @throws Exception
	 */
	public void deleteProduct(String id) throws Exception;
    
    /**
     * ����:������Ʒ��������Ʒ�������ж����
     * @author hug 2012-3-14 
     * @param strCode
     * @return
     * @throws Exception
     */
    public List getProductByCode(String strCode) throws Exception;
    
    /**
     * ����:���ݲ�Ʒ��������Ʒ
     * @author hug 2012-3-14 
     * @param strCode
     * @return
     * @throws Exception
     */
    public List getProductByCodeInfo(String strCode) throws Exception;
    
    /**
     * ����:������Ʒ��������Ʒ�������ж����
     * @author hug 2012-3-14 
     * @param strCode
     * @return
     * @throws Exception
     */
    public List getProductByProductCode(String strCode) throws Exception;

	public String getMaxProductId(String typeid)throws Exception;

	public String getMaxProductIdz()throws Exception;

}
