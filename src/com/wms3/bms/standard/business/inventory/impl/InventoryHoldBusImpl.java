package com.wms3.bms.standard.business.inventory.impl;

import java.util.ArrayList;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.base.ICargoSpaceBus;
import com.wms3.bms.standard.business.base.impl.CargoSpaceBusImpl;
import com.wms3.bms.standard.business.inventory.IInventoryHoldBus;
import com.wms3.bms.standard.dao.inventory.IInventoryDao;
import com.wms3.bms.standard.dao.inventory.IInventoryHoldDao;
import com.wms3.bms.standard.dao.inventory.impl.InventoryDaoImpl;
import com.wms3.bms.standard.dao.inventory.impl.InventoryHoldDaoImpl;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryHold;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;

/**
 * ����: ��涳��/�ͷ�ҵ����   
 * @author fangjie
 * 
 */
public class InventoryHoldBusImpl implements IInventoryHoldBus {
	
	protected IInventoryHoldDao inventoryHoldDAO = null;
	protected IInventoryDao inventoryDAO = null;
	protected ICargoSpaceBus cargospaceBus = null;
	public InventoryHoldBusImpl(EntityDAO dao) {
		this.inventoryHoldDAO = new InventoryHoldDaoImpl(dao);
		this.inventoryDAO = new InventoryDaoImpl(dao);
		this.cargospaceBus = new CargoSpaceBusImpl(dao);
	}

	/**
	 * ����:����������ѯ�����¼�б�
	 * @param cargospaceid	��λ
	 * @param lotid			��������
	 * @param ownerid		����
	 * @param productid		Ʒ��
	 * @param holdcode		����ԭ��
	 * @param holdby		���᷽��
	 * @param dateon_from	��������
	 * @param dateon_to		��������
	 * @param dateoff_from	�ͷ�����
	 * @param dateoff_to	�ͷ�����
	 * @param maxLine 
	 * @param strUrl 
	 * @return 
	 * @throws Exception
	 */
	public PagingTool getHoldList(String cargospaceid, String productid, String holdcode, String holdby, 
			String dateon_from, String dateon_to, String dateoff_from, String dateoff_to, String strUrl, int maxLine) throws Exception {
	
		PagingTool pt = null;
		
		try {
			StringBuilder strBud = new StringBuilder();
			strBud.append(" from InventoryHold as hold where hold.holdflag='Y' ");
			
			if(cargospaceid != null && cargospaceid.trim().length() > 0){		//��λ
				strBud.append(" and hold.spaceid='").append(cargospaceid).append("'");
			}
			
			
			
			if(productid != null && productid.trim().length() > 0){		//Ʒ��
				strBud.append(" and hold.productid='").append(productid).append("'");
			}
			
			if(holdcode != null && holdcode.trim().length() > 0){		//����ԭ��
				strBud.append(" and hold.holdcode='").append(holdcode).append("'");
			}
			
			if(holdby != null && holdby.trim().length() > 0){		//���᷽��
				strBud.append(" and hold.holdby='").append(holdby).append("'");
			}
			
			//����ʱ��
	        if(dateon_from != null && dateon_from.trim().length()>0){
	        
	            strBud.append(" and hold.dateon>='").append(dateon_from).append("'");
	        }
	        if(dateon_to != null && dateon_to.trim().length()>0){
	            
	            strBud.append(" and hold.dateon<='").append(dateon_to).append(" 99:99:99'");
	        }
	        
	        //�ͷ�ʱ��
	        if(dateoff_from != null && dateoff_from.trim().length()>0){
	        
	            strBud.append(" and hold.dateoff>='").append(dateoff_from).append("'");
	        }
	        if(dateoff_to != null && dateoff_to.trim().length()>0){
	            
	            strBud.append(" and hold.dateoff<='").append(dateoff_to).append(" 99:99:99'");
	        }
	        
			String strHQL = strBud.toString() + " order by hold.dateoff, hold.spaceid";
			
			String strCountHQL = "select count(*)" + strBud.toString();
			
			pt = CommonPagination.getPagingTool(inventoryHoldDAO.getEntityDAO(), strCountHQL ,strHQL, strUrl, 1, maxLine);
			
		} catch (Exception er) {
			throw new Exception("����������ѯ�����¼�б����:" + er.getMessage());
		}
				
		return pt;	
	}

	/**
	 * ����:����������ѯ����¼���б�
	 * @param ownerid		����
	 * @param productid		Ʒ��
	 * @param cargospaceid	��λ
	 * @param lotid			��������
	 * @param traycode		��������
	 * @param boxcode		������
	 * @param productcode	��Ʒ����
	 * @return 
	 * @throws Exception
	 */
	public List getStorageList(String productid, String cargospaceid, 
			String traycode, String boxcode, String productcode) throws Exception {
		
		List ls = null;
		
		try {
			StringBuilder strBud = new StringBuilder();
			strBud.append(" from InventoryStorage as storage where storage.cargoSpaceId in (select cargoSpaceId from BaseCargospace as cargospace where cargospace.csstatus ='1' or cargospace.csstatus ='2') ");	////״̬  0:δ���䣬1���ѷ��� 3:���� 4:��Ʒ������5���̵�
			
			
			if(productid != null && productid.trim().length() > 0){		//Ʒ��
				strBud.append(" and storage.productid='").append(productid).append("'");
			}
			
			if(cargospaceid != null && cargospaceid.trim().length() > 0){		//��λ
				strBud.append(" and storage.cargoSpaceId='").append(cargospaceid).append("'");
			}
			
			
			if(traycode != null && traycode.trim().length() > 0){		//��������
				strBud.append(" and storage.traycode='").append(traycode).append("'");
			}
			
			if(boxcode != null && boxcode.trim().length() > 0){		//������
				strBud.append(" and storage.boxcode='").append(boxcode).append("'");
			}
			
			if(productcode != null && productcode.trim().length() > 0){		//��Ʒ����
				strBud.append(" and storage.productcode='").append(productcode).append("'");
			}
			
			strBud.append(" order by storage.cargoSpaceId");
			
			ls = inventoryHoldDAO.querySQL(strBud.toString());
			
		} catch (Exception er) {
			throw new Exception("����������ѯ����¼���б����:" + er.getMessage());
		}
				
		return ls;	
	}

	/**
	 * ����:���ɶ����¼
	 * @param ids			���ids
	 * @param holdcode		����ԭ��
	 * @param holdby		���᷽��
	 * @param dateoff		�ͷ�����
	 * @param holdreason 	ԭ������
	 * @param strUserCode 	�û�����
	 * @return 
	 * @throws Exception
	 */
	public String addHold(String ids, String holdcode, String holdby, String dateoff, String holdreason, String strUserCode) throws Exception {
		
		String strBackMsg = "Y";
		List<InventoryStorage> lsstorage = new ArrayList<InventoryStorage>();
		List<InventoryHold> lshold = new ArrayList<InventoryHold>();
		
		try{
			
			InventoryStorage storage = null;
			InventoryHold hold = null;
			String strTime =  StrTools.getCurrDateTime(2); 
			
			String[] id = ids.split(",");
			for(int i=0; i<id.length; i++){
				
				storage = inventoryDAO.getInventoryById(id[i]);
				hold = new InventoryHold();
				hold.setStatus("1");				//״̬(Ĭ��1,ֱ�Ӷ���)
				hold.setHoldflag("Y");				//�Ƿ񶳽� Y-��,N-��
				hold.setHoldby(holdby);				//���᷽�� 1.ֱ�Ӷ��� 2.�������ϸ�Ʒ��
				hold.setHoldcode(holdcode);        	//����ԭ�� 1.��Ʒ���� 2.��Ʒ��
				hold.setHoldreason(holdreason);     //ԭ������
				hold.setInventoryid(id[i]);     	//���ID
				hold.setOwnerid(storage.getOwnerId());		//����ID
				hold.setProductid(storage.getProductid());  //��ƷID
				hold.setLotid(storage.getLotid());          //���κ�
				hold.setSpaceid(storage.getCargoSpaceId());	//��λID
				hold.setTraycode(storage.getTraycode());	//��������
				hold.setBoxcode(storage.getBoxcode());      //������
				hold.setProductcode(storage.getProductcode());  //��Ʒ����
				hold.setQtyonhold(storage.getPnum());       //��������
				hold.setNetweightonhold(storage.getPnetweight()); //��������
				hold.setDateon(strTime);          	//����ʱ��
				hold.setWhoon(strUserCode);         //���������
				hold.setDateoff(dateoff);         	//�ͷ�ʱ��
				
				lshold.add(hold);
				
				BaseCargospace baseCargospace = cargospaceBus.getCargoSpaceById(storage.getCargoSpaceId()) ;
				//�жϿ��״̬�ǲ���δ����
				if(baseCargospace.getCsstatus().equals("1")||baseCargospace.getCsstatus().equals("2")){
					if(storage.getHoldnum()>0 || storage.getHoldnetweight()>0){
						strBackMsg = "��λ���Ѵ��ڶ����������Ƕ��������������ٶ��ᣡ";
						break;
					}else{
						storage.setHoldnum(storage.getPnum());
						storage.setHoldnetweight(storage.getPnetweight());
						lsstorage.add(storage);
					}
					
				}else{
					strBackMsg = "��λ״̬���ǡ��ջ�λ��������λ�����������ڷ���ÿ�棬�����²�ѯ�������ɶ����¼��";
					break;
				}
			}
			
			if(strBackMsg.equals("Y")){
				inventoryHoldDAO.addHold(lshold, lsstorage);
				Logger.info("�û�["+strUserCode+"]���Կ��:��" + ids + "�������˶����¼");
			}
			
		}catch(Exception er){
        	Logger.error("�Կ��:" + ids + "���ɶ����¼ʱ����:" + er.getMessage());
            throw new Exception("�Կ��:" + ids + "���ɶ����¼ʱ����:" + er.getMessage());
        }
		return strBackMsg; 
	}

	/**
	 * ����:�ͷŶ����¼
	 * @param ids			ids(����ID+"|"+���ID)
	 * @param strUserCode 	�û�����
	 * @return 
	 * @throws Exception
	 */
	public String updateHold(String ids, String strUserCode) throws Exception {
		
		String strBackMsg = "Y";
		List<InventoryStorage> lsstorage = new ArrayList<InventoryStorage>();
		List<InventoryHold> lshold = new ArrayList<InventoryHold>();
		
		try{
			
			InventoryStorage storage = null;
			InventoryHold hold = null;
			String strTime =  StrTools.getCurrDateTime(2); 
			
			String[] tempid = ids.split(",");
			String[] id;
			
			for(int i=0; i<tempid.length; i++){
				
				id = tempid[i].split("\\|");
				//�ͷ�
				hold = inventoryHoldDAO.getHoldById(id[0]);
				if(hold.getHoldflag().equals("N")){
					strBackMsg = "�����¼�Ѿ����ͷţ������²�ѯ�����ͷţ�";
					break;
				}
				hold.setHoldflag("N");				//�Ƿ񶳽� Y-��,N-��
				hold.setWhoon(strUserCode);         //���������
				hold.setDateoff(strTime);         	//�ͷ�ʱ��
				lshold.add(hold);
				
				//��տ�涳�������Ͷ�������
				storage = inventoryDAO.getInventoryById(id[1]);
				storage.setHoldnum(0);
				storage.setHoldnetweight(0);
				lsstorage.add(storage);
				
			}
			
			if(strBackMsg.equals("Y")){
				inventoryHoldDAO.updateHold(lshold, lsstorage);
				Logger.info("�û�["+strUserCode+"]���ͷ��˶����¼|���:��" + ids + "��");
			}
			
		}catch(Exception er){
        	Logger.error("�Զ����¼|���:" + ids + "�ͷ�ʱ����:" + er.getMessage());
            throw new Exception("�Զ����¼|���:" + ids + "�ͷ�ʱ����:" + er.getMessage());
        }
		return strBackMsg; 
	}
	
}