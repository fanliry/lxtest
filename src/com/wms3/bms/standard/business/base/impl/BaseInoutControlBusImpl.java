package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.base.IBaseInoutControlBus;
import com.wms3.bms.standard.dao.base.IBaseInoutControlDao;
import com.wms3.bms.standard.dao.base.impl.BaseInoutControlDaoImpl;
import com.wms3.bms.standard.entity.base.BaseInoutControl;

/**
 * 描述: 出入库卡控业务接口
 * @author fanll
 * 
 */
public class BaseInoutControlBusImpl implements IBaseInoutControlBus {
	
	protected IBaseInoutControlDao ControlDao = null;
	protected EntityDAO m_dao = null;
	
	public BaseInoutControlBusImpl(EntityDAO dao) {
		this.ControlDao = new BaseInoutControlDaoImpl(dao);
		this.m_dao = dao;
	}
	
	@Override
	public void addBaseInoutControl(BaseInoutControl Control) throws Exception {
		ControlDao.addBaseInoutControl(Control);
	}
	
	public List<BaseInoutControl> getlsInoutControl() throws Exception{
		List<BaseInoutControl> ls = null;
		ls = ControlDao.getlsInoutControl();
		return ls;	
	}

	public BaseInoutControl getInoutControl() throws Exception{
		List<BaseInoutControl> ls = null;
		BaseInoutControl InoutControl = null;
		ls = ControlDao.getlsInoutControl();
		if(ls != null && ls.size() > 0){
			InoutControl = ls.get(0);
		}
		return InoutControl;	
	}
	
	/**
	 * 功能:出入库卡控，清空数据,并且新增一条数据
	 * @param ls
	 * @param Control
	 * @throws Exception
	 */
	public void DelInoutControlAdd(List<BaseInoutControl> ls, BaseInoutControl Control) throws Exception{
		ControlDao.DelInoutControlAdd(ls, Control);
	}

}