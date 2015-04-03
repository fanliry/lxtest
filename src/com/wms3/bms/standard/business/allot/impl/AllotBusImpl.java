package com.wms3.bms.standard.business.allot.impl;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.business.allot.IAllotBus;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.service.BMSService;

public class AllotBusImpl implements IAllotBus{
    public static byte[] rk_assgin_lock = new byte[0]; 
	protected EntityDAO m_dao;
	public AllotBusImpl(EntityDAO dao) {
		m_dao = dao;
	}
	/**
	 * 功能：获取一个空货位
	 * @param warehouseid
	 * @param WhAreaId
	 * @param AlleyId
	 * @return
	 */
    public BaseCargospace getNullSpaceId(String warehouseid,String WhAreaId ,String AlleyId)
    {
    	BaseCargospace space = null;
        EntityDAO dao = BMSService.getm_EntityDAO();
        try
        {
        	synchronized (rk_assgin_lock) {
        		String strSql = " select bc from BaseCargospace bc,BaseAlley al where bc.cscolumn!=1 and bc.cargoAlleyId = al.cargoAlleyId and al.inlock='N' and bc.csstatus='1' and bc.whAreaId='"+WhAreaId+"'"+" and bc.warehouseid='"+warehouseid+"'";
        		//String strSql = " select bc from BaseCargospace bc,BaseAlley al where bc.cargoAlleyId = al.cargoAlleyId and al.inlock='N' and bc.csstatus='1' and bc.whAreaId='"+WhAreaId+"'"+" and bc.warehouseid='"+warehouseid+"'";
                if(AlleyId!=null && AlleyId.length()>0){
                	strSql += " and bc.cargoAlleyId='"+AlleyId+"'";
                }
                strSql += " order by bc.csplatoon,bc.csfloor,bc.cscolumn ";  //先放一排，能后放一层，列从1-79
                List lsList = dao.searchEntities(strSql,0,1);
                if(lsList!=null && lsList.size()>0){
                	if(lsList.get(0)!=null){
                		space=(BaseCargospace)lsList.get(0);
                		return space;
                	}
                }
			}
        }catch(Exception er)
        {
            Logger.error("根据货位获取货位信息失败:" + er.getMessage());
        }
        return null;
    }
}
