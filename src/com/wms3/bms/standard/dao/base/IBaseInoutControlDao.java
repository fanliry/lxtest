package com.wms3.bms.standard.dao.base;

import java.util.List;

import com.wms3.bms.standard.entity.base.BaseInoutControl;

/**
 * 描述: 出入库卡控DAO类接口
 * @author fanll
 * 
 */
public interface IBaseInoutControlDao {


	public void addBaseInoutControl(BaseInoutControl Control) throws Exception;
	public List<BaseInoutControl> getlsInoutControl() throws Exception;
	/**
	 * 功能:出入库卡控，清空数据,并且新增一条数据
	 * @param ls
	 * @param Control
	 * @throws Exception
	 */
	public void DelInoutControlAdd(List<BaseInoutControl> ls, BaseInoutControl Control) throws Exception;
}
