package com.wms3.bms.standard.business.allot;

import java.util.List;

import com.wms3.bms.standard.business.allot.impl.InAllotRequestBean;
import com.wms3.bms.standard.business.allot.impl.OutAllotRequestBean;


/**
 * 
 * 描述: 分配业务接口类
 * @author yangwm
 * @since WMS 3.0
 */
public interface IAllotServiceBus {
	/**
	 * 入库指定货位验证入口
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public boolean inVerifyEntry(InAllotRequestBean req) throws Exception;

	/**
	 * 入库分配入口
	 * @param productid
	 * @param customid
	 * @return
	 * @throws Exception
	 */
	public List inAllotEntry(String productid, String customid)throws Exception;
	
	/**
	 * 出库指定货位验证入口
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public List outVerifyEntry(OutAllotRequestBean req) throws Exception;

	/**
	 * 出库分配入口
	 * @param productid
	 * @param customid
	 * @return
	 * @throws Exception
	 */
	public List outAllotEntry(String productid, String customid)throws Exception;
}
