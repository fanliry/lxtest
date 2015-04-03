package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.base.IBatchruleBus;
import com.wms3.bms.standard.dao.base.IBaseBatchruleDao;
import com.wms3.bms.standard.dao.base.impl.BaseBatchruleDaoImpl;
import com.wms3.bms.standard.entity.base.BaseBatchrule;

/**
 * 
 * 描述: 批次规则管理业务类
 * @author fangjie
 * @since WMS 3.0
 */
public class BatchruleBusImpl implements IBatchruleBus {
	
	protected IBaseBatchruleDao baseBatchruleDAO = null;

	/**
	 * @param dao
	 */
	public BatchruleBusImpl(EntityDAO dao) {
		this.baseBatchruleDAO = new BaseBatchruleDaoImpl(dao);
	}

	/**
	 * 功能:获取所有批次规则列表
	 * @return 
	 * @throws Exception
	 */
	public List getBatchruleList() throws Exception {
		
		return baseBatchruleDAO.getBatchruleList();
	}

	/**
	 * 功能:根据批次规则ID获得批次规则
	 * @param id	批次规则ID
	 * @return 
	 * @throws Exception
	 */
	public BaseBatchrule getBatchruleById(String id) throws Exception {
		
		return baseBatchruleDAO.getBatchruleById(id);
	}

	/**
	 * 功能:根据批次ID获得批次规则
	 * @param id	批次ID
	 * @return 
	 * @throws Exception
	 */
	public List getBatchruleByBatchId(String id) throws Exception {
		
		return baseBatchruleDAO.getBatchruleByBatchId(id);
	}
	
	/**
	 * 功能:增加批次规则
	 * @param batchrule	批次规则
	 * @throws Exception
	 */
	public void addBatchrule(BaseBatchrule batchrule) throws Exception {
		
		baseBatchruleDAO.addBatchrule(batchrule);
		
	}

	/**
	 * 功能:修改批次规则
	 * @param batchrule	批次规则
	 * @throws Exception
	 */
	public void updateBatchrule(BaseBatchrule batchrule) throws Exception {
		
		baseBatchruleDAO.updateBatchrule(batchrule);
		
	}

	/**
	 * 功能:删除批次规则
	 * @param id	批次规则ID
	 * @throws Exception
	 */
	public void deleteBatchrule(String id) throws Exception {
		
		baseBatchruleDAO.deleteBatchrule(id);
	}

}