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
 * ����:��������DWR��
 * @author 
 *
 */
public class LotTool{
	
	protected EntityDAO m_dao = BMSService.getm_EntityDAO();
	
	/**
	 * ����:��ȡ�������Ե��б�
	 */
	public List getAllLots(String id) throws Exception{
		
		List lsResult = new ArrayList();
		List ls = null;
		
		try{
			ILotBus lotBus = new LotBusImpl(m_dao);
			
			//���������������
			ls = lotBus.getAllLots();
			
		}catch(Exception ex){
			
			Logger.error("���������������ʧ��:" + ex.getMessage());
		}
		
		if(ls != null){
		
			for(int i = 0; i < ls.size(); i++){
			
				BaseLot lot = (BaseLot)ls.get(i);
				String strFlag = "0";
				
				if(id != null && id.trim().length() > 0){
					
					if(lot.getM_strLotid().equals(id)){
						strFlag = "1";
					}
					
				}else{//��ʾ��ǰ�ֿ�
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
	 * ����:��������ID���������ϸ��Ϣ�б�
	 */
	public List getLotDetails(String id) throws Exception{
		
		List lsResult = new ArrayList();
		List ls = null;
		
		try{
			ILotBus lotBus = new LotBusImpl(m_dao);
			
			//��������ID���������ϸ��Ϣ�б�
			ls = lotBus.getListByLotId(id);
			
		}catch(Exception ex){
			
			Logger.error("��������ID���������ϸ��Ϣ�б�ʧ��:" + ex.getMessage());
		}
		
//		if(ls != null){
//		
//			for(int i = 0; i < ls.size(); i++){
//			
//				BaseLotDetail lotdetail = (BaseLotDetail)ls.get(i);
//				
//				//���õĲ�����
//				if(!lotdetail.getM_strLotatt_flag().equals("2")){
//					
//					String lottypevalue = "";
//					
//					//����ѡ���� �������ʱ��
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
