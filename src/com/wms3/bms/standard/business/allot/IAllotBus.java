package com.wms3.bms.standard.business.allot;

import com.wms3.bms.standard.entity.base.BaseCargospace;

/**
 * 描述: 分配业务接口类
 * @author yao
 * @since WMS 3.0
 */
public interface IAllotBus {
	/**
	 * 功能：获取一个空货位
	 * @param warehouseid
	 * @param WhAreaId
	 * AlleyId
	 * @return
	 */
    public BaseCargospace getNullSpaceId(String warehouseid, String WhAreaId,String AlleyId);
}
