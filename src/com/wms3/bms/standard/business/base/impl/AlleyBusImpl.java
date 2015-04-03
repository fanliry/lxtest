package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.base.IAlleyBus;
import com.wms3.bms.standard.dao.base.IBaseAlleyDao;
import com.wms3.bms.standard.dao.base.impl.BaseAlleyDaoImpl;
import com.wms3.bms.standard.entity.base.BaseAlley;

/**
 * ����: �������ҵ����
 * @author fangjie
 * 
 */
public class AlleyBusImpl implements IAlleyBus {
	
	protected IBaseAlleyDao baseAlleyDAO = null;

	public AlleyBusImpl(EntityDAO dao) {
		this.baseAlleyDAO = new BaseAlleyDaoImpl(dao);
	}
	
	/**
	 * ����:����������ѯ���
	 * @param warehouseid		�ֿ�ID
	 * @param cargoAlleyId		���ID
	 * @param whAreaId			����ID
	 * @return
	 * @throws Exception
	 */
	public List getAlleyQuery(String warehouseid, String cargoAlleyId, String whAreaId) throws Exception {
		
		return baseAlleyDAO.getAlleyQuery(warehouseid, cargoAlleyId, whAreaId);
	}

	/**
	 * ����:�������ID������
	 * @param cargoAlleyId	���ID
	 * @return
	 * @throws Exception
	 */
	public BaseAlley getAlleyById(String cargoAlleyId) throws Exception {
		
		return baseAlleyDAO.getAlleyById(cargoAlleyId);
	}

	/**
	 * ����:�������
	 * @param alley	���
	 * @throws Exception
	 */
	public void addAlley(BaseAlley alley) throws Exception {
		
		String whAreaId = alley.getWhAreaId();			//����ID
		
		// ���ͬ�ֿ�ͬ�����µ�������ID
		String alleyId = baseAlleyDAO.getMaxAlleyId(whAreaId);
		
		alleyId = whAreaId + SeqTool.getCode(Integer.parseInt(alleyId.substring(6, 9)) + 1, 3);
		alley.setCargoAlleyId(alleyId);
		
		baseAlleyDAO.addAlley(alley);
		
	}

	/**
	 * ����:��ȡ��������б�
	 * @return
	 * @throws Exception
	 */
	public List getAlleyList() throws Exception {

		return baseAlleyDAO.getAlleyList();
	}

	/**
	 * ����:�޸����
	 * @param alley	���
	 * @throws Exception
	 */
	public void updateAlley(BaseAlley alley) throws Exception {
		
		baseAlleyDAO.updateAlley(alley);
	}

	/**
	 * ����:ɾ�����
	 * @param id	���ID
	 * @throws Exception
	 */
	public void deleteAlley(String id) throws Exception {
		
		baseAlleyDAO.deleteAlley(id);
	}

	/**
	 * ����:�жϿ������Ƿ������
	 * @param id	����ID
	 * @throws Exception
	 */
	public boolean isWhAreaHasChildNode(String id) throws Exception {
		
		return baseAlleyDAO.isWhAreaHasChildNode(id);
	}

}