package com.wms3.bms.standard.business.allot;

import com.wms3.bms.standard.entity.base.BaseCargospace;

/**
 * ����: ����ҵ��ӿ���
 * @author yao
 * @since WMS 3.0
 */
public interface IAllotBus {
	/**
	 * ���ܣ���ȡһ���ջ�λ
	 * @param warehouseid
	 * @param WhAreaId
	 * AlleyId
	 * @return
	 */
    public BaseCargospace getNullSpaceId(String warehouseid, String WhAreaId,String AlleyId);
}
