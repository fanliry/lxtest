package com.wms3.bms.standard.business.base.impl;

import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.base.IWhAreaVirtualBus;
import com.wms3.bms.standard.dao.base.IBaseWhAreaVirtualDao;
import com.wms3.bms.standard.dao.base.impl.BaseWhAreaVirtualDaoImpl;
import com.wms3.bms.standard.entity.base.BaseWhareaVirtual;

/**
 * 
 * ����: ��������ҵ����
 * @author fanll
 * @since WMS 3.0
 */
public class WhAreaVirtualBusImpl implements IWhAreaVirtualBus {
	
	protected IBaseWhAreaVirtualDao baseWhAreaVirtualDAO = null;

	/**
	 * @param dao
	 */
	public WhAreaVirtualBusImpl(EntityDAO dao) {
		this.baseWhAreaVirtualDAO = new BaseWhAreaVirtualDaoImpl(dao);
	}


	/**
	 * ����:����������ѯ����
	 * @param warehouseid		�ֿ�ID
	 * @param whAreaName		������
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaQuery(String warehouseid, String whAreaName) throws Exception {
		
		return baseWhAreaVirtualDAO.getWhAreaQuery(warehouseid, whAreaName);
	}

	/**
	 * ����:���ݿ���ID��ÿ���
	 * @param whAreaId	����ID
	 * @return 
	 * @throws Exception
	 */
	public BaseWhareaVirtual getWhareaById(String whAreaId) throws Exception {
		
		return baseWhAreaVirtualDAO.getWhareaById(whAreaId);
	}

	/**
	 * ����:���ӿ���
	 * @param wharea	����
	 * @throws Exception
	 */
	public void addWhArea(BaseWhareaVirtual wharea) throws Exception {
		
		// ��øòֿ�����е�����������
		String whAreaId = baseWhAreaVirtualDAO.getMaxWhareaNo(wharea.getWarehouseid());
		
		whAreaId = wharea.getWarehouseid() + SeqTool.getCode(Integer.parseInt(whAreaId.substring(3,7)) + 1, 4);
		wharea.setwhAreaId(whAreaId);
		
		baseWhAreaVirtualDAO.addWhArea(wharea);
		
	}
	
	/**
	 * ����:��ȡ���п����б�
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaList() throws Exception {
		
		return baseWhAreaVirtualDAO.getWhAreaList();
	}

	/**
	 * ����:�޸Ŀ���
	 * @param wharea	����
	 * @throws Exception
	 */
	public void updateWhArea(BaseWhareaVirtual wharea) throws Exception {
		
		baseWhAreaVirtualDAO.updateWhArea(wharea);
		
	}

	/**
	 * ����:ɾ������
	 * @param whAreaId	����ID
	 * @throws Exception
	 */
	public void deleteWhArea(String whAreaId) throws Exception {
		
		baseWhAreaVirtualDAO.deleteWhArea(whAreaId);
	}

	/**
	 * ����:�жϲֿ����Ƿ��п���
	 * @param id	�ֿ�ID
	 * @throws Exception
	 */
	public boolean isWhHasChildNode(String id) throws Exception {
		
		return baseWhAreaVirtualDAO.isWhHasChildNode(id);
	}

	/**
	 * ����:ȡ��ָ���ֿ��µĿ���
	 * @param warehouseid	�ֿ�ID
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaByWhid(String warehouseid) throws Exception {
		
		return baseWhAreaVirtualDAO.getWhAreaByWhid(warehouseid);
	}
	/**
	 * ����:ȡ��ָ���ֿ��µĿ���(�����)
	 * @param warehouseid	�ֿ�ID
	 * @return 
	 * @throws Exception
	 */
	public List getWhAreaNotTemByWhid(String warehouseid) throws Exception {
		
		return baseWhAreaVirtualDAO.getWhAreaByWhidNotTem(warehouseid);
	}
    /**
     * ���ݲֿ����ݴ���
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.base.IWhAreaBus#getZCgetWhAreaByWhid(java.lang.String)
     */
    public BaseWhareaVirtual getZCgetWhAreaByWhid(String warehouseid) throws Exception {
        return baseWhAreaVirtualDAO.getZCgetWhAreaByWhid(warehouseid);
    }
}