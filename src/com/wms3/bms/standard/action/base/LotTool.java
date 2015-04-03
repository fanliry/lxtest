package com.wms3.bms.standard.action.base;

import java.util.ArrayList;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.dwr.TreeBean;
import com.ricosoft.common.logger.Logger;
import com.wms3.bms.standard.business.base.ILotBus;
import com.wms3.bms.standard.business.base.impl.LotBusImpl;
import com.wms3.bms.standard.entity.base.BaseLot;
import com.wms3.bms.standard.service.BMSService;

/**
 * 描述:批次属性DWR类
 * @author 
 *
 */
public class LotTool{
	
	protected EntityDAO m_dao = BMSService.getm_EntityDAO();
	
	/**
	 * 功能:获取批次属性的列表
	 */
	public List getAllLots(String id) throws Exception{
		
		List lsResult = new ArrayList();
		List ls = null;
		
		try{
			ILotBus lotBus = new LotBusImpl(m_dao);
			
			//获得所有批次属性
			ls = lotBus.getAllLots();
			
		}catch(Exception ex){
			
			Logger.error("获得所有批次属性失败:" + ex.getMessage());
		}
		
		if(ls != null){
		
			for(int i = 0; i < ls.size(); i++){
			
				BaseLot lot = (BaseLot)ls.get(i);
				String strFlag = "0";
				
				if(id != null && id.trim().length() > 0){
					
					if(lot.getM_strLotid().equals(id)){
						strFlag = "1";
					}
					
				}else{//显示当前仓库
					if(lot.getM_strIsdefault()!=null && lot.getM_strIsdefault().equals("Y")){
						strFlag = "1";
					}
				}
				lsResult.add(new TreeBean(lot.getM_strLotid(), lot.getM_strDescr(), strFlag));
			}
		}
		return lsResult;
	}
	
	/**
	 * 功能:根据批次ID获得批次明细信息列表
	 */
	public List getLotDetails(String id) throws Exception{
		
		List lsResult = new ArrayList();
		List ls = null;
		
		try{
			ILotBus lotBus = new LotBusImpl(m_dao);
			
			//根据批次ID获得批次明细信息列表
			ls = lotBus.getListByLotId(id);
			
		}catch(Exception ex){
			
			Logger.error("根据批次ID获得批次明细信息列表失败:" + ex.getMessage());
		}
		
//		if(ls != null){
//		
//			for(int i = 0; i < ls.size(); i++){
//			
//				BaseLotDetail lotdetail = (BaseLotDetail)ls.get(i);
//				
//				//禁用的不返回
//				if(!lotdetail.getM_strLotatt_flag().equals("2")){
//					
//					String lottypevalue = "";
//					
//					//属性选项项 下拉框的时候
//					if(lotdetail.getM_strLotattvalue().equals("2")){	
//						lottypevalue = lotdetail.getM_strLottypevalue();
//					}
//					
//					lsResult.add(new TreeBean(lotdetail.getM_strAttcode(), lotdetail.getM_strAttname(), lottypevalue));
//				}
//			}
//		}
//		return lsResult;
		
		return ls;
	}

}
