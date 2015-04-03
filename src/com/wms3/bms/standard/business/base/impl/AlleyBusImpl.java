package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.base.IAlleyBus;
import com.wms3.bms.standard.dao.base.IBaseAlleyDao;
import com.wms3.bms.standard.dao.base.impl.BaseAlleyDaoImpl;
import com.wms3.bms.standard.entity.base.BaseAlley;

/**
 * 描述: 巷道管理业务类
 * @author fangjie
 * 
 */
public class AlleyBusImpl implements IAlleyBus {
	
	protected IBaseAlleyDao baseAlleyDAO = null;

	public AlleyBusImpl(EntityDAO dao) {
		this.baseAlleyDAO = new BaseAlleyDaoImpl(dao);
	}
	
	/**
	 * 功能:根据条件查询巷道
	 * @param warehouseid		仓库ID
	 * @param cargoAlleyId		巷道ID
	 * @param whAreaId			库区ID
	 * @return
	 * @throws Exception
	 */
	public List getAlleyQuery(String warehouseid, String cargoAlleyId, String whAreaId) throws Exception {
		
		return baseAlleyDAO.getAlleyQuery(warehouseid, cargoAlleyId, whAreaId);
	}

	/**
	 * 功能:根据巷道ID获得巷道
	 * @param cargoAlleyId	巷道ID
	 * @return
	 * @throws Exception
	 */
	public BaseAlley getAlleyById(String cargoAlleyId) throws Exception {
		
		return baseAlleyDAO.getAlleyById(cargoAlleyId);
	}

	/**
	 * 功能:增加巷道
	 * @param alley	巷道
	 * @throws Exception
	 */
	public void addAlley(BaseAlley alley) throws Exception {
		
		String whAreaId = alley.getWhAreaId();			//库区ID
		
		// 获得同仓库同库区下的最大巷道ID
		String alleyId = baseAlleyDAO.getMaxAlleyId(whAreaId);
		
		alleyId = whAreaId + SeqTool.getCode(Integer.parseInt(alleyId.substring(6, 9)) + 1, 3);
		alley.setCargoAlleyId(alleyId);
		
		baseAlleyDAO.addAlley(alley);
		
	}

	/**
	 * 功能:获取所有巷道列表
	 * @return
	 * @throws Exception
	 */
	public List getAlleyList() throws Exception {

		return baseAlleyDAO.getAlleyList();
	}

	/**
	 * 功能:修改巷道
	 * @param alley	巷道
	 * @throws Exception
	 */
	public void updateAlley(BaseAlley alley) throws Exception {
		
		baseAlleyDAO.updateAlley(alley);
	}

	/**
	 * 功能:删除巷道
	 * @param id	巷道ID
	 * @throws Exception
	 */
	public void deleteAlley(String id) throws Exception {
		
		baseAlleyDAO.deleteAlley(id);
	}

	/**
	 * 功能:判断库区下是否有巷道
	 * @param id	库区ID
	 * @throws Exception
	 */
	public boolean isWhAreaHasChildNode(String id) throws Exception {
		
		return baseAlleyDAO.isWhAreaHasChildNode(id);
	}

}