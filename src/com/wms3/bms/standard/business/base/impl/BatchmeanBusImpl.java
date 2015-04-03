package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.base.IBatchmeanBus;
import com.wms3.bms.standard.dao.base.IBaseBatchmeanDao;
import com.wms3.bms.standard.dao.base.impl.BaseBatchmeanDaoImpl;
import com.wms3.bms.standard.entity.base.BaseBatchmean;

/**
 * 
 * ����: �����������ҵ����
 * @author fangjie
 * @since WMS 3.0
 */
public class BatchmeanBusImpl implements IBatchmeanBus {
	
	protected IBaseBatchmeanDao baseBatchmeanDAO = null;

	/**
	 * @param dao
	 */
	public BatchmeanBusImpl(EntityDAO dao) {
		this.baseBatchmeanDAO = new BaseBatchmeanDaoImpl(dao);
	}

	/**
	 * ����:��ȡ�������������б�
	 * @return 
	 * @throws Exception
	 */
	public List getBatchmeanList() throws Exception {
		
		return baseBatchmeanDAO.getBatchmeanList();
	}

	/**
	 * ����:������������ID�����������
	 * @param id	��������ID
	 * @return 
	 * @throws Exception
	 */
	public BaseBatchmean getBatchmeanById(String id) throws Exception {
		
		return baseBatchmeanDAO.getBatchmeanById(id);
	}

	/**
	 * ����:��������ID�����������
	 * @param id	����ID
	 * @return 
	 * @throws Exception
	 */
	public List getBatchmeanByBatchId(String id) throws Exception {
		
		return baseBatchmeanDAO.getBatchmeanByBatchId(id);
	}
	
	/**
	 * ����:������������
	 * @param batchmean	��������
	 * @throws Exception
	 */
	public void addBatchmean(BaseBatchmean batchmean) throws Exception {
		
		baseBatchmeanDAO.addBatchmean(batchmean);
		
	}

	/**
	 * ����:�޸���������
	 * @param batchmean	��������
	 * @throws Exception
	 */
	public void updateBatchmean(BaseBatchmean batchmean) throws Exception {
		
		baseBatchmeanDAO.updateBatchmean(batchmean);
		
	}

	/**
	 * ����:ɾ����������
	 * @param id	��������ID
	 * @throws Exception
	 */
	public void deleteBatchmean(String id) throws Exception {
		
		baseBatchmeanDAO.deleteBatchmean(id);
	}

}