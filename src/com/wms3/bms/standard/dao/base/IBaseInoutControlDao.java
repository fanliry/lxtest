package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseInoutControl;

/**
 * ����: ����⿨��DAO��ӿ�
 * @author fanll
 * 
 */
public interface IBaseInoutControlDao {


	public void addBaseInoutControl(BaseInoutControl Control) throws Exception;
	public List<BaseInoutControl> getlsInoutControl() throws Exception;
	/**
	 * ����:����⿨�أ��������,��������һ������
	 * @param ls
	 * @param Control
	 * @throws Exception
	 */
	public void DelInoutControlAdd(List<BaseInoutControl> ls, BaseInoutControl Control) throws Exception;
}
