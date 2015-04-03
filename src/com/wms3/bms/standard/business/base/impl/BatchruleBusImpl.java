package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.base.IBatchruleBus;
import com.wms3.bms.standard.dao.base.IBaseBatchruleDao;
import com.wms3.bms.standard.dao.base.impl.BaseBatchruleDaoImpl;
import com.wms3.bms.standard.entity.base.BaseBatchrule;

/**
 * 
 * ����: ���ι������ҵ����
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
	 * ����:��ȡ�������ι����б�
	 * @return 
	 * @throws Exception
	 */
	public List getBatchruleList() throws Exception {
		
		return baseBatchruleDAO.getBatchruleList();
	}

	/**
	 * ����:�������ι���ID������ι���
	 * @param id	���ι���ID
	 * @return 
	 * @throws Exception
	 */
	public BaseBatchrule getBatchruleById(String id) throws Exception {
		
		return baseBatchruleDAO.getBatchruleById(id);
	}

	/**
	 * ����:��������ID������ι���
	 * @param id	����ID
	 * @return 
	 * @throws Exception
	 */
	public List getBatchruleByBatchId(String id) throws Exception {
		
		return baseBatchruleDAO.getBatchruleByBatchId(id);
	}
	
	/**
	 * ����:�������ι���
	 * @param batchrule	���ι���
	 * @throws Exception
	 */
	public void addBatchrule(BaseBatchrule batchrule) throws Exception {
		
		baseBatchruleDAO.addBatchrule(batchrule);
		
	}

	/**
	 * ����:�޸����ι���
	 * @param batchrule	���ι���
	 * @throws Exception
	 */
	public void updateBatchrule(BaseBatchrule batchrule) throws Exception {
		
		baseBatchruleDAO.updateBatchrule(batchrule);
		
	}

	/**
	 * ����:ɾ�����ι���
	 * @param id	���ι���ID
	 * @throws Exception
	 */
	public void deleteBatchrule(String id) throws Exception {
		
		baseBatchruleDAO.deleteBatchrule(id);
	}

}