 package com.wms3.bms.standard.business.base.impl;

import java.util.HashMap;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.wms3.bms.standard.business.base.ILotBus;
import com.wms3.bms.standard.dao.base.IBaseLotDao;
import com.wms3.bms.standard.dao.base.IBaseLotDetailDao;
import com.wms3.bms.standard.dao.base.impl.BaseLotDaoImpl;
import com.wms3.bms.standard.dao.base.impl.BaseLotDetailDaoImpl;
import com.wms3.bms.standard.entity.base.BaseLot;
import com.wms3.bms.standard.entity.base.BaseLotDetail;


/**
 * 
 * 描述: 批次属性管理业务类
 * @author fangjie
 * @since WMS 3.0
 */
public class LotBusImpl implements ILotBus {
	
	protected IBaseLotDao baseLotDAO = null;
	protected IBaseLotDetailDao baseLotDetailDAO = null;
	protected EntityDAO m_dao = null;

	/**
	 * @param dao
	 */
	public LotBusImpl(EntityDAO dao) {
		this.baseLotDAO = new BaseLotDaoImpl(dao);
		this.baseLotDetailDAO = new BaseLotDetailDaoImpl(dao);
		this.m_dao = dao;
	}


	/**
	 * 功能:根据条件查询批次属性
	 * @param descr	描述
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getLotQuery(String descr, String strUrl, int maxLine) throws Exception {
		
		PagingTool pt = null;
		
		try {
			String sql = "From BaseLot as t where 1=1" ;
			
			if(descr != null && descr.trim().length() > 0){
				sql += " and t.m_strDescr like '%" + descr + "%'";
			}
			
			String strHQL = sql + " order by t.m_strLotid";
			String strCountHQL = "select count(*)" + sql ;
			
			pt = CommonPagination.getPagingTool(m_dao, strCountHQL ,strHQL, strUrl, 1, maxLine);
			
		} catch (Exception er) {
			throw new Exception("根据条件查询批次属性出错:" + er.getMessage());
		}
				
		return pt;
	}

	/**
	 * 功能:增加批次，批次详细信息
	 * @param lot	批次
	 * @param lotDetail1, lotDetail2, lotDetail3, lotDetail4, lotDetail5, lotDetail6, 
	 * @param lotDetail7, lotDetail8, lotDetail9, lotDetail10, lotDetail11, lotDetail12 批次详细信息
	 * @throws Exception
	 */
	public void addLot(BaseLot lot, BaseLotDetail lotDetail1, BaseLotDetail lotDetail2, BaseLotDetail lotDetail3, BaseLotDetail lotDetail4, 
			BaseLotDetail lotDetail5, BaseLotDetail lotDetail6, BaseLotDetail lotDetail7, BaseLotDetail lotDetail8, BaseLotDetail lotDetail9, 
			BaseLotDetail lotDetail10, BaseLotDetail lotDetail11, BaseLotDetail lotDetail12) throws Exception {
		
		baseLotDAO.addLot(lot, lotDetail1, lotDetail2, lotDetail3, lotDetail4, lotDetail5, lotDetail6, 
				lotDetail7, lotDetail8, lotDetail9, lotDetail10, lotDetail11, lotDetail12);
	}

	/**
	 * 功能:根据批次ID获得批次
	 * @param id	批次ID
	 * @return 
	 * @throws Exception
	 */
	public BaseLot getLotById(String lotid) throws Exception {
		
		return baseLotDAO.getLotById(lotid);
	}

	/**
	 * 功能:根据批次ID获得批次详细信息列表
	 * @param id	批次ID
	 * @return 
	 * @throws Exception
	 */
	public List getListByLotId(String lotid) throws Exception {
		
		return baseLotDetailDAO.getListByLotId(lotid);
	}
    /**
     * 根据批次ID获得批次详细信息列表(Map)
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.base.ILotBus#getHashMapByLotId(java.lang.String)
     */
    public HashMap<String, BaseLotDetail> getHashMapByLotId(String lotid) throws Exception {
        HashMap<String, BaseLotDetail> hsLot = new HashMap<String, BaseLotDetail>();
        List ls = baseLotDetailDAO.getListByLotId(lotid);
        if(ls != null){
            BaseLotDetail lotDetail = null;
            for(int i = 0; i < ls.size(); i++){
                lotDetail = (BaseLotDetail)ls.get(i);
                hsLot.put(lotDetail.getM_strAttcode(), lotDetail);
            }
        }
        return hsLot;
    }

	/**
	 * 功能:根据批次明细ID获得批次明细
	 * @param id	批次明细ID
	 * @return 
	 * @throws Exception
	 */
	public BaseLotDetail getLotDetailByDetailId(String lotdetailid) throws Exception {

		return baseLotDetailDAO.getLotDetailByDetailId(lotdetailid);
	}

	/**
	 * 功能:修改批次，批次明细信息
	 * @param lot	批次
	 * @param lotDetail1, lotDetail2, lotDetail3, lotDetail4, lotDetail5, lotDetail6, 
	 * @param lotDetail7, lotDetail8, lotDetail9, lotDetail10, lotDetail11, lotDetail12 批次明细信息
	 * @throws Exception
	 */
	public void updateLot(BaseLot lot, BaseLotDetail lotDetail1, BaseLotDetail lotDetail2, BaseLotDetail lotDetail3, BaseLotDetail lotDetail4, 
			BaseLotDetail lotDetail5, BaseLotDetail lotDetail6, BaseLotDetail lotDetail7, BaseLotDetail lotDetail8, BaseLotDetail lotDetail9, 
			BaseLotDetail lotDetail10, BaseLotDetail lotDetail11, BaseLotDetail lotDetail12) throws Exception {
	
		BaseLot defaultLot = null;
		
		//设置成默认批次属性的时候，要清掉原来的默认批次属性
		if(lot.getM_strIsdefault().equals("Y")){
			defaultLot = baseLotDAO.getDefaultLot();
			if(defaultLot != null){
				defaultLot.setM_strIsdefault("N");
			}
			
		}
		baseLotDAO.updateLot(defaultLot, lot, lotDetail1, lotDetail2, lotDetail3, lotDetail4, lotDetail5, lotDetail6, 
				lotDetail7, lotDetail8, lotDetail9, lotDetail10, lotDetail11, lotDetail12);
	}

	/**
	 * 功能:删除批次，批次明细信息
	 * @param id	批次ID
	 * @return 
	 * @throws Exception
	 */
	public void deleteLot(String id) throws Exception {
		
		baseLotDAO.deleteLot(id);
	}

	/**
	 * 功能:获得所有批次属性
	 * @return 
	 * @throws Exception
	 */
	public List getAllLots() throws Exception {
		
		return baseLotDAO.getAllLots();
	}

	
	
}