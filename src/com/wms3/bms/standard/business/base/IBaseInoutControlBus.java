package com.wms3.bms.standard.business.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseInoutControl;

/**
 * ����: ����⿨��ҵ��ӿ�
 * @author fanll
 * 
 */
public interface IBaseInoutControlBus {


	public void addBaseInoutControl(BaseInoutControl Control) throws Exception;
	public List<BaseInoutControl> getlsInoutControl() throws Exception;
	public BaseInoutControl getInoutControl() throws Exception;
	
	/**
	 * ����:����⿨�أ��������,��������һ������
	 * @param ls
	 * @param Control
	 * @throws Exception
	 */
	public void DelInoutControlAdd(List<BaseInoutControl> ls, BaseInoutControl Control) throws Exception;
	
}
