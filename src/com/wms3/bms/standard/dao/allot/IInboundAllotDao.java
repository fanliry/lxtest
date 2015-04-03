package com.wms3.bms.standard.dao.allot;

import java.util.Hashtable;
import java.util.List;

/**
 * 描述: 入库分配DAO类接口
 * @author yangwm
 * 
 */
public interface IInboundAllotDao {

	
	/**
	 * 从[目标库区]中查找合适的库位
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsFromArea(Hashtable param) throws Exception;
	
	/**
	 * 上架到[目标库位]
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsFromTarget(Hashtable param) throws Exception;
	
	/**
	 * 上架到sku所指定的[上架库位]
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsFromSkuTarget(Hashtable param) throws Exception;
	
	/**
	 * 从sku所指定的[上架库区]中查找合适的库位
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsFromSkuArea(Hashtable param) throws Exception;
	
	/**
	 * 使用[件拣货库位]
	 * @param param
	 * @return
	 */
	public List getCsFromPieceCs(Hashtable param);
	
	
	/**
	 *  使用[箱拣货库位]
	 * @param param
	 * @return
	 */
	public List getCsFromBoxCs(Hashtable param);
	
	/**
	 * 使用[件/箱拣货库位]
	 * @param param
	 * @return
	 */
	public List getCsFromPieceBoxCs(Hashtable param);
	
	/**
	 * 如果批次属性为指定[批次属性]，则上架到[目标库位]
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsFromBatchToTarget(Hashtable param) throws Exception;
	
	/**
	 * 如果批次属性为指定[批次属性]，则上[目标库区]中查找合适的库位
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsFromBatchToArea(Hashtable param) throws Exception;
	
	/**
	 * 查找同名称产品相邻空库位
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsFromSameProductId(Hashtable param) throws Exception;
	
	/**
	 * 查找同批次产品相邻空库位
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsFromSameBatch(Hashtable param) throws Exception;
	
	/**
	 * 存储类型来分配货位
	 * @param param
	 * @return
	 */
	public List getCsFromStoragetype(Hashtable param);
		
	/**
	 * 按产品和日期在各巷道数量均匀分布到每个巷道
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsEvenProductDate(Hashtable param) throws Exception;
	
	/**
	 * 每个巷道逐个分配，除非巷道锁定
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsAlleyByAlley(Hashtable param) throws Exception;
	
	/**
	 * 巷道递增分配
	 * @param param
	 * @return
	 */
	public List getCsIncrease(Hashtable param);
}
