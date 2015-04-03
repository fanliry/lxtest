package com.wms3.bms.standard.dao.allot;

import java.util.Hashtable;
import java.util.List;



/**
 * 描述: 出库分配DAO类接口
 * @author yangwm
 * 
 */
public interface IOutboundAllotDao {
	
	/**
	 * 指定产品日期进行分配
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsEvenChannel(Hashtable param) throws Exception;
	
	/**
	 * 按日期先后顺序及巷道均衡进行分配货位
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsFastInFastOutEvenChannel(Hashtable param) throws Exception;
	
	/**
	 * 按日期先后顺序进行分配货位
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List getCsFastInFastOut(Hashtable param) throws Exception;
	
	/**
	 * 人工指定货位
	 * @param param
	 * @return
	 */
	public List getCsAssign(Hashtable param);
}
