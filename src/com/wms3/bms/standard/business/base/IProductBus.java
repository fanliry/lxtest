package com.wms3.bms.standard.business.base;

import java.util.List;

import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.entity.base.BaseProduct;

/**
 * ����: ��Ʒ����ҵ��ӿ�
 * @author fangjie
 * 
 */
public interface IProductBus {

	/**
	 * ����:������ƷID�����Ʒ
	 * @param productid	��ƷID
	 * @return
	 * @throws Exception
	 */
	public BaseProduct getProductById(String productid) throws Exception;
	
	/**
	 * ����:������Ʒ
	 * @param product	��Ʒ
	 * @throws Exception
	 */
	public void addProduct(BaseProduct product) throws Exception;

	/**
	 * ����:�޸���Ʒ
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
    
	/**
	 * ����:����������ѯ��Ʒ
	 * @param productname	��Ʒ����
     * @param productcode   ��Ʒ����
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getProductQuery(String productname, String productcode, String producttype,String strUrl, int maxLine) throws Exception;
    /**
     * ����: ��ѯ������Ʒ     
	 * @return 
	 * @throws Exception
	 */
    public List searchEntitieAll() throws Exception;
}
