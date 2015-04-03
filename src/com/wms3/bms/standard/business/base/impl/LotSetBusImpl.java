package com.wms3.bms.standard.business.base.impl;

import java.util.HashMap;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.wms3.bms.standard.business.base.ILotSetBus;
import com.wms3.bms.standard.dao.base.IBaseLotSetDao;
import com.wms3.bms.standard.dao.base.impl.BaseLotSetDaoImpl;
import com.wms3.bms.standard.entity.base.BaseLotSet;


/**
 * 
 * 描述: 批次属性设置管理
 * @author hug 2012-3-12
 * @since WMS 3.0
 */
public class LotSetBusImpl implements ILotSetBus{
    protected IBaseLotSetDao baseLotSetDAO = null;
    
    public LotSetBusImpl(EntityDAO dao){
        this.baseLotSetDAO = new BaseLotSetDaoImpl(dao);
    }

	/**
	 * 功能:查询批次属性设置不同类型
	 * @return 
	 * @throws Exception
	 */
	public List getLotsetType() throws Exception {
		
		return baseLotSetDAO.getLotsetType();
	}
	
	/**
	 * 功能:增加批次属性设置
	 * @param lotset1, lotset2, lotset3, lotset4, lotset5, lotset6, 
	 * @param lotset7, lotset8, lotset9, lotset10, lotset11, lotset12
	 * @throws Exception
	 */
	public void addLotset(BaseLotSet lotset1, BaseLotSet lotset2, BaseLotSet lotset3, BaseLotSet lotset4, BaseLotSet lotset5, BaseLotSet lotset6, 
			BaseLotSet lotset7, BaseLotSet lotset8, BaseLotSet lotset9, BaseLotSet lotset10, BaseLotSet lotset11, BaseLotSet lotset12) throws Exception {
		
		baseLotSetDAO.addLotset(lotset1, lotset2, lotset3, lotset4, lotset5, lotset6, lotset7, lotset8, lotset9, lotset10, lotset11, lotset12);
	}

	/**
	 * 功能:根据类型查询批次属性设置的列表
	 * @param lottype	类型
	 * @return 
	 * @throws Exception
	 */
	public List getLotsetByType(String lottype) throws Exception {

		return baseLotSetDAO.getLotsetByType(lottype);
	}

	/**
	 * 功能:删除批次属性设置
	 * @param lottype	类型
	 * @return 
	 * @throws Exception
	 */
	public void deleteLotset(String lottype) throws Exception {
		
		baseLotSetDAO.deleteLotset(lottype);
	}
	
	/**
	 * 功能:根据Id查询批次属性设置
	 * @param lotattid	Id
	 * @return 
	 * @throws Exception
	 */
	public BaseLotSet getLotsetById(String lotattid) throws Exception {
		
		return baseLotSetDAO.getLotsetById(lotattid);
	}

	/**
	 * 功能:修改批次属性设置
	 * @param lotset1, lotset2, lotset3, lotset4, lotset5, lotset6, 
	 * @param lotset7, lotset8, lotset9, lotset10, lotset11, lotset12
	 * @throws Exception
	 */
	public void updateLotset(BaseLotSet lotset1, BaseLotSet lotset2, BaseLotSet lotset3, BaseLotSet lotset4, BaseLotSet lotset5, BaseLotSet lotset6, 
			BaseLotSet lotset7, BaseLotSet lotset8, BaseLotSet lotset9, BaseLotSet lotset10, BaseLotSet lotset11, BaseLotSet lotset12) throws Exception {
		
		baseLotSetDAO.updateLotset(lotset1, lotset2, lotset3, lotset4, lotset5, lotset6, lotset7, lotset8, lotset9, lotset10, lotset11, lotset12);
	}

	/**
	 * 功能:判断该类型是否存在
	 * @param lottype	类型
	 * @throws Exception
	 */
	public boolean isLotsetExist(String lottype) throws Exception {
		
		return baseLotSetDAO.isLotsetExist(lottype);
	}
    
    /**
     * 根据批次类型获得可显示的批次属性，并按设置的顺序排序
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.base.ILotSetBus#getViewLot(java.lang.String)
     */
    public List<BaseLotSet> getViewLot(String strLotType) throws Exception {
        return baseLotSetDAO.getViewLot(strLotType);
    }
    /**
     * 统计所有要显示的批次类型
     * (non-Javadoc)
     * @see com.wms3.bms.standard.business.base.ILotSetBus#getViewLot()
     */
    public HashMap<String, List> getViewLot() throws Exception {
        HashMap<String, List> hsLot = new HashMap<String, List>();
        try
        {
            //统计要显示的批次类型
            String strLotType = null;
            List<String> lsType = baseLotSetDAO.getGroupLotType();
            for(int i = 0; i < lsType.size(); i++){
                strLotType = lsType.get(i);
                if(strLotType != null){
                    List<BaseLotSet> lsLot = baseLotSetDAO.getViewLot(strLotType);
                    hsLot.put(strLotType, lsLot);
                }
            }   
        }catch(Exception er){
            throw new Exception("获取所有显示的批次出错：" + er.getMessage());
        }
        return hsLot;
    }

}
