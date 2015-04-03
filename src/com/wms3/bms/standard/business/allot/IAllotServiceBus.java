package com.wms3.bms.standard.business.allot;

import java.util.List;

import com.wms3.bms.standard.business.allot.impl.InAllotRequestBean;
import com.wms3.bms.standard.business.allot.impl.OutAllotRequestBean;


/**
 * 
 * ����: ����ҵ��ӿ���
 * @author yangwm
 * @since WMS 3.0
 */
public interface IAllotServiceBus {
	/**
	 * ���ָ����λ��֤���
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public boolean inVerifyEntry(InAllotRequestBean req) throws Exception;

	/**
	 * ���������
	 * @param productid
	 * @param customid
	 * @return
	 * @throws Exception
	 */
	public List inAllotEntry(String productid, String customid)throws Exception;
	
	/**
	 * ����ָ����λ��֤���
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public List outVerifyEntry(OutAllotRequestBean req) throws Exception;

	/**
	 * ����������
	 * @param productid
	 * @param customid
	 * @return
	 * @throws Exception
	 */
	public List outAllotEntry(String productid, String customid)throws Exception;
}
