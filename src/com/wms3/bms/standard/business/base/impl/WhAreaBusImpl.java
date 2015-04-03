package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.base.IWhAreaBus;
import com.wms3.bms.standard.dao.base.IBaseWhAreaDao;
import com.wms3.bms.standard.dao.base.impl.BaseWhAreaDaoImpl;
import com.wms3.bms.standard.entity.base.BaseWharea;

/**
 * 
 * ����: ��������ҵ����
 * @author fangjie
 * @since WMS 3.0
 */
public class WhAreaBusImpl implements IWhAreaBus {
	
	protected IBaseWhAreaDao baseWhAreaDAO = null;

	/**
	 * @param dao
	 */
	public WhAreaBusImpl(EntityDAO dao) {
		this.baseWhAreaDAO = new BaseWhAreaDaoImpl(dao);
	}


	/**
	 * ����:����������ѯ����
	 * @param warehouseid		�ֿ�ID
	 * @param whAreaName		������
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaQuery(String warehouseid, String whAreaName) throws Exception {
		
		return baseWhAreaDAO.getWhAreaQuery(warehouseid, whAreaName);
	}

	/**
	 * ����:���ݿ���ID��ÿ���
	 * @param whAreaId	����ID
	 * @return 
	 * @throws Exception
	 */
	public BaseWharea getWhareaById(String whAreaId) throws Exception {
		
		return baseWhAreaDAO.getWhareaById(whAreaId);
	}

	/**
	 * ����:���ӿ���
	 * @param wharea	����
	 * @throws Exception
	 */
	public void addWhArea(BaseWharea wharea) throws Exception {
		
		// ��øòֿ�����е�����������
		String whAreaId = baseWhAreaDAO.getMaxWhareaNo(wharea.getWarehouseid());
		
		whAreaId = wharea.getWarehouseid() + SeqTool.getCode(Integer.parseInt(whAreaId.substring(3,6)) + 1, 3);
		wharea.setwhAreaId(whAreaId);
		
		baseWhAreaDAO.addWhArea(wharea);
		
	}
	
	/**
	 * ����:��ȡ���п����б�
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaList() throws Exception {
		
		return baseWhAreaDAO.getWhAreaList();
	}

	/**
	 * ����:�޸Ŀ���
	 * @param wharea	����
	 * @throws Exception
	 */
	public void updateWhArea(BaseWharea wharea) throws Exception {
		
		baseWhAreaDAO.updateWhArea(wharea);
		
	}

	/**
	 * ����:ɾ������
	 * @param whAreaId	����ID
	 * @throws Exception
	 */
	public void deleteWhArea(String whAreaId) throws Exception {
		
		baseWhAreaDAO.deleteWhArea(whAreaId);
	}

	/**
	 * ����:�жϲֿ����Ƿ��п���
	 * @param id	�ֿ�ID
	 * @throws Exception
	 */
	public boolean isWhHasChildNode(String id) throws Exception {
		
		return baseWhAreaDAO.isWhHasChildNode(id);
	}

	/**
	 * ����:ȡ��ָ���ֿ��µĿ���
	 * @param warehouseid	�ֿ�ID
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaByWhid(String warehouseid) throws Exception {
		
		return baseWhAreaDAO.getWhAreaByWhid(warehouseid);
	}
	/**
	 * ����:ȡ��ָ���ֿ��µĿ���(�����)
	 * @param warehouseid	�ֿ�ID
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaNotTemByWhid(String warehouseid) throws Exception {
		
		return baseWhAreaDAO.getWhAreaByWhidNotTem(warehouseid);
	}
    /**
     * ���ݲֿ����ݴ���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.base.IWhAreaBus#getZCgetWhAreaByWhid(java.lang.String)
     */
    public BaseWharea getZCgetWhAreaByWhid(String warehouseid,String whAreaId) throws Exception {
        return baseWhAreaDAO.getZCgetWhAreaByWhid(warehouseid,whAreaId);
    }
}