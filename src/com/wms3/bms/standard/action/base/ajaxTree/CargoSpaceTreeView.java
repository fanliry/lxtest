package com.wms3.bms.standard.action.base.ajaxTree;

import java.util.ArrayList;
import java.util.List;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.base.IAlleyBus;
import com.wms3.bms.standard.business.base.ICargoSpaceBus;
import com.wms3.bms.standard.business.base.IWarehouseBus;
import com.wms3.bms.standard.business.base.IWhAreaBus;
import com.wms3.bms.standard.business.base.impl.AlleyBusImpl;
import com.wms3.bms.standard.business.base.impl.CargoSpaceBusImpl;
import com.wms3.bms.standard.business.base.impl.WarehouseBusImpl;
import com.wms3.bms.standard.business.base.impl.WhAreaBusImpl;
import com.wms3.bms.standard.entity.base.BaseAlley;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BaseWarehouse;
import com.wms3.bms.standard.entity.base.BaseWharea;
import com.wms3.bms.standard.service.BMSService;



/**==========================================
 * ==========      ��������λ��     ===========
 * ==========      ���ߣ�gui     ===========
 * ==========================================
 */
public class CargoSpaceTreeView implements BlueTreeInterFace{
	
	/**
	 * ����:���ݸ��ڵ�id���ػ�λ��
	 * @return
	 */
	public List getTreeElements(String strPid) {
		EntityDAO dao  = BMSService.getm_EntityDAO(); 
		
		List list =  new ArrayList();
		
		boolean pidIsRoot = false; 		//���ڵ��Ƿ�Ϊ���ڵ�
		boolean pidIsWh = false; 		//���ڵ��Ƿ�Ϊ�ֿ�ڵ�
		boolean pidIsWhArea = false; 	//���ڵ��Ƿ�Ϊ�����ڵ�
		boolean pidIsAlley = false; 	//���ڵ��Ƿ�Ϊ����ڵ�
		
		IWarehouseBus warehouseBus = new WarehouseBusImpl(dao);
		IWhAreaBus whAreaBus = new WhAreaBusImpl(dao);
		IAlleyBus alleyBus = new AlleyBusImpl(dao);
		ICargoSpaceBus cargoSpaceBus = new CargoSpaceBusImpl(dao);
		
		try{
			if(strPid.equals("0")){//�жϸ��ڵ��Ƿ�Ϊ���ڵ�
				pidIsRoot = true;
			}else{
				List ls1 = warehouseBus.getWarehouseList();
				for(int i=0; i<ls1.size(); i++){
					BaseWarehouse warehouse= (BaseWarehouse)ls1.get(i); 
					String warehouseId = warehouse.getWarehouseid();
					if(warehouseId.equals(strPid)){//�жϸ��ڵ��Ƿ�Ϊ�ֿ�ڵ�
						pidIsWh = true;
						break;
					}
				}
				if(!pidIsWh){
					List ls2 = whAreaBus.getWhAreaList();
					for(int i=0; i<ls2.size(); i++){
						BaseWharea wharea= (BaseWharea)ls2.get(i);
						String whAreaId = wharea.getwhAreaId();
						if(whAreaId.equals(strPid)){//�жϸ��ڵ��Ƿ�Ϊ�����ڵ�
							pidIsWhArea = true;
							break;
						}
					}
				}
				if(!pidIsWhArea){
					List ls3 = alleyBus.getAlleyList();
					for(int i=0; i<ls3.size(); i++){
						BaseAlley alley= (BaseAlley)ls3.get(i);
						String alleyId = alley.getCargoAlleyId();
						if(alleyId.equals(strPid)){//�жϸ��ڵ��Ƿ�Ϊ����ڵ�
							pidIsAlley = true;
							break;
						}
					}
				}
			}
			
			if(pidIsRoot){//������ڵ�Ϊ���ڵ㣬���ȡ���ڵ��µ����б�
				List ls = warehouseBus.getWarehouseList();
				for(int i=0; i<ls.size(); i++){
					BaseWarehouse warehouse= (BaseWarehouse)ls.get(i); 
					
					String id = warehouse.getWarehouseid();
					String pid = "0";
					String name = warehouse.getWarehousename();
					boolean ischild = whAreaBus.isWhHasChildNode(id);
					String url = BMSService.getm_strServerPath() + "/BMSService?actionCode=baseCargoSpaceAction&method=initWarehouse&warehouseId="+id;
					
					list.add(new BlueTreeData(id, pid, name, url, null, ischild, null, null));
				}
			}
			
			if(pidIsWh){//������ڵ�Ϊ�ֿ�ڵ㣬���ȡ�ֿ�ڵ��µ����б�
				List ls = whAreaBus.getWhAreaQuery(strPid, ""); 
				for(int i=0; i<ls.size(); i++){
					
					BaseWharea wharea= (BaseWharea)ls.get(i);
					String id = wharea.getwhAreaId();
					String pid = strPid;
					String name = wharea.getwhAreaName();
					boolean ischild1 = alleyBus.isWhAreaHasChildNode(id);	//�������Ƿ������������⣩
					
					if(ischild1){//����������������������⣩
						String url = BMSService.getm_strServerPath() +  "/BMSService?actionCode=baseCargoSpaceAction&method=initWhArea&whAreaId="+id;
						list.add(new BlueTreeData(id, pid, name, url, null, ischild1, null, null));
						
					}else{//�����������û�����������⣩
						boolean ischild2 = cargoSpaceBus.isWhAreaHasChildNode(id);	//�������Ƿ��л�λ
						String url = BMSService.getm_strServerPath() +  "/BMSService?actionCode=baseCargoSpaceAction&method=initWhArea&whAreaId="+id;
						list.add(new BlueTreeData(id, pid, name, url, null, ischild2, null, null));
					}
					
				}
		}
			
			if(pidIsWhArea){//������ڵ�Ϊ�����ڵ㣬���ȡ�����ڵ��µ����б�
				List ls1 = alleyBus.getAlleyQuery("", "", strPid); 
				
				if(ls1!=null && ls1.size()>0){	//����������������������⣩
					for(int i=0; i<ls1.size(); i++){
						
						BaseAlley alley= (BaseAlley)ls1.get(i);
						String id = alley.getCargoAlleyId();
						String pid = strPid;
						String name = alley.getCargoAlleyName();
						boolean ischild = cargoSpaceBus.isAlleyHasChildNode(id);
						
						String url = BMSService.getm_strServerPath() +  "/BMSService?actionCode=baseCargoSpaceAction&method=initAlley&cargoAlleyId="+id;
						
						list.add(new BlueTreeData(id, pid, name, url, null, ischild, null, null));
					}
				}else{
					List ls2 = cargoSpaceBus.getCargoSpaceQuery("", "", "", strPid);
					for(int i=0; i<ls2.size(); i++){
						
						BaseCargospace cargoSpace= (BaseCargospace)ls2.get(i); 
						String id = cargoSpace.getCargoSpaceId();
						String pid = strPid;
						String name = cargoSpace.getCargoSpaceName();
						boolean ischild = false;
						String url = BMSService.getm_strServerPath() + "/BMSService?actionCode=baseCargoSpaceAction&flag=1&cargoSpaceId="+id;
						
						list.add(new BlueTreeData(id, pid, name, url, null, ischild, null, null));
					}
					
				}
				
			}
			
			if(pidIsAlley){//������ڵ�Ϊ����ڵ㣬���ȡ����ڵ��µ����б�
				List ls = cargoSpaceBus.getCargoSpaceQuery("", "", strPid, "");
				for(int i=0; i<ls.size(); i++){
					
					BaseCargospace cargoSpace= (BaseCargospace)ls.get(i); 
					String id = cargoSpace.getCargoSpaceId();
					String pid = strPid;
					String name = cargoSpace.getCargoSpaceName();
					boolean ischild = false;
					String url = BMSService.getm_strServerPath() + "/BMSService?actionCode=baseCargoSpaceAction&flag=1&cargoSpaceId="+id;
					
					list.add(new BlueTreeData(id, pid, name, url, null, ischild, null, null));
				}
			}
			
		}catch(Exception er)
		{
			Logger.error("���ݸ��ڵ�id���ػ�λ��ʧ��:" + er.getMessage());
		}
		return list;
	}
		
	/**
	 * ����:���ݽڵ�id��ȡ���ڵ���Ϣ
	 * @param strId
	 * @return
	 */
	public List getParentInfo(String strId, String flag) {
		
		EntityDAO dao  = BMSService.getm_EntityDAO(); 
		List list =  new ArrayList();
	
		try{
			// "0"��ʾ�����idΪ����ID
			if(flag.equals("0")){
				IWhAreaBus whAreaBus = new WhAreaBusImpl(dao);
				BaseWharea wharea= whAreaBus.getWhareaById(strId);
				String whAreaName = wharea.getwhAreaName();
				String warehousename = wharea.getWarehousename();
				
				list.add(0, warehousename); 	//0:�ֿ���
				list.add(1, whAreaName); 		//1:������
			}
			
			//"1"��ʾ�����idΪ���ID
			if(flag.equals("1")){
				IAlleyBus alleyBus = new AlleyBusImpl(dao);
				BaseAlley alley= alleyBus.getAlleyById(strId);
				String whAreaName = alley.getWhAreaName();
				String warehousename = alley.getWarehousename();
				String cargoAlleyName = alley.getCargoAlleyName();
				
				list.add(0, warehousename); 	//0:�ֿ���
				list.add(1, whAreaName); 		//1:������
				list.add(2, cargoAlleyName);   	//2:�����
			}
			
		}catch(Exception er)
		{
			Logger.error("���ݽڵ�id��ȡ���ڵ���Ϣʧ��:" + er.getMessage());
		}
		return list;
	}
	
	/**
	 * ����:������λ������������������ã�
	 * @param cargoAlleyId, minPlatoonStr
	 * @param maxPlatoonStr, minColumnStr, maxColumnStr, floorNumStr
	 * @param strUserCode, strUserName
	 * @return
	 */
	public String addCargoSpaceTree(String cargoAlleyId, String minPlatoonStr, String maxPlatoonStr, String minColumnStr, 
			String maxColumnStr, String floorNumStr, String cstype, String storagetype, String length, String width, String height, 
			String volume, String layernum, String loadweight, String istwin, String strUserCode, String strUserName)
	{
		String strMesg = "";
		EntityDAO dao = BMSService.getm_EntityDAO();
		ICargoSpaceBus cargoSpaceBus = new CargoSpaceBusImpl(dao);
		
		String currentTime = StrTools.getCurrDateTime(2);
		
		try
		{
			int minPlatoon = Integer.parseInt(minPlatoonStr);
			int maxPlatoon = Integer.parseInt(maxPlatoonStr);
			int minColumn = Integer.parseInt(minColumnStr);
			int maxColumn = Integer.parseInt(maxColumnStr);
			int floorNum = Integer.parseInt(floorNumStr);
			
			String cargoSpaceId = "";   	//��λ���
			String cargoSpaceName = "";   	//��λ����
			int m_iPlatoon = 0;		     	//��λ��
			int m_iColumn = 0;			 	//��λ��
			int m_iFloor = 0;			 	//��λ��
			
			String warehouseid = cargoAlleyId.substring(0, 3);	//�ֿ�ID 3λ
			String whAreaId = cargoAlleyId.substring(0, 6);		//����ID 6λ��ǰ3λ�ǲֿ��ţ�
			String twincsid = "";			//����˫����λID
			
			//���ɻ�λ
			for(int i=(minPlatoon>0 ? minPlatoon : 1); i<=maxPlatoon; i++){
				for(int j=(minColumn>0 ? minColumn : 1); j<=maxColumn; j++){
					//������
					m_iColumn = j;
					//������
					m_iPlatoon = i;
					
					for(int k=1; k<=floorNum; k++){
						//���ɲ�
						m_iFloor = k;
						
						//���û�λ���(�ֿ�3λ������3λ�����в�6λ)
						cargoSpaceId = whAreaId + getCode(m_iPlatoon) + getCode(m_iColumn) + getCode(m_iFloor);
						cargoSpaceName = getCode(m_iPlatoon) + "��" + getCode(m_iColumn) + "��" + getCode(m_iFloor) + "��";
						
						//����û�λ�Ѿ����ڣ��Ͳ�������
						if(cargoSpaceBus.getCargoSpaceById(cargoSpaceId)!=null){
							Logger.info("��λ" + cargoSpaceId + "�Ѿ����ڣ�");
							strMesg = "��λ�Ѿ�����";
							continue;
						}
						
						//����һ����λ
						BaseCargospace cargoSpace = new BaseCargospace();
						
						cargoSpace.setCargoSpaceId(cargoSpaceId);		//��λID
						cargoSpace.setCargoSpaceName(cargoSpaceName);	//��λ����
						cargoSpace.setCsstatus("1");					//��λ״̬ 1���ջ�λ
						cargoSpace.setCsplatoon(m_iPlatoon);			//��λ��
						cargoSpace.setCscolumn(m_iColumn);				//��λ��
						cargoSpace.setCsfloor(m_iFloor);				//��λ��
						cargoSpace.setCstype(cstype); 					//��λ����
						cargoSpace.setInlock("N");						//�����
						cargoSpace.setOutlock("N");						//������
						cargoSpace.setCargoAlleyId(cargoAlleyId);		//���ID
						cargoSpace.setWarehouseid(warehouseid);			//�ֿ�ID
						cargoSpace.setWhAreaId(whAreaId);				//����ID
						cargoSpace.setStoragetype(storagetype);			//�洢����(�洢��λ���У�,�䣬��)
						cargoSpace.setPuttype("1");						//�ϼ�����(������ͣ�1��ֻ�ܷ���һ��2���ɶ�η���)
						cargoSpace.setPicktype("1");					//��ѡ����(�������ͣ�1��ֻ�ܷ���һ�Σ�2���ɶ�η���)
						cargoSpace.setLength(Double.parseDouble(length));	// ��
						cargoSpace.setWidth(Double.parseDouble(width));		// ��
						cargoSpace.setHeight(Double.parseDouble(height));	// ��
						cargoSpace.setVolume(Double.parseDouble(volume));	// ���
						cargoSpace.setLayernum(new Integer(layernum));		// ����
						cargoSpace.setLoadweight(Double.parseDouble(loadweight));	// ����
						cargoSpace.setPalletnum(1);						//�ɷ�������
						cargoSpace.setHaspalletnum(0);					//�ѷ�������
						
						if(istwin.equals("Y")){
							cargoSpace.setIstwin("Y");					//�Ƿ�˫����λ Y.�� N.��
							
							if((i-minPlatoon)==0){	//�������
								cargoSpace.setLocation("1");			//λ�ã�1�����棻2������
								twincsid = whAreaId + getCode(m_iPlatoon+1) + getCode(m_iColumn) + getCode(m_iFloor);
								cargoSpace.setTwincsid(twincsid);		//����˫����λID
							}else if((i-minPlatoon)==1){	//�������
								cargoSpace.setLocation("2");			//λ�ã�1�����棻2������
								twincsid = whAreaId + getCode(m_iPlatoon-1) + getCode(m_iColumn) + getCode(m_iFloor);
								cargoSpace.setTwincsid(twincsid);		//����˫����λID
							}else if((i-minPlatoon)==2){	//�������
								cargoSpace.setLocation("2");			//λ�ã�1�����棻2������
								twincsid = whAreaId + getCode(m_iPlatoon+1) + getCode(m_iColumn) + getCode(m_iFloor);
								cargoSpace.setTwincsid(twincsid);		//����˫����λID
							}else if((i-minPlatoon)==3){	//�������
								cargoSpace.setLocation("1");			//λ�ã�1�����棻2������
								twincsid = whAreaId + getCode(m_iPlatoon-1) + getCode(m_iColumn) + getCode(m_iFloor);
								cargoSpace.setTwincsid(twincsid);		//����˫����λID
							}
							
						}else{
							cargoSpace.setIstwin("N");					//�Ƿ�˫����λ Y.�� N.��
						}
						
						cargoSpace.setCreatetime(currentTime);
						cargoSpace.setCreatemanid(strUserCode);
						cargoSpace.setUpdatetime(currentTime);
						cargoSpace.setUpdatemanid(strUserCode);
						
						cargoSpaceBus.addCargospace(cargoSpace);
						
						Logger.info("�û�" + strUserName + "����˻�λ" + cargoSpaceId);
						
						strMesg = "�����ɹ�";
					}
				}
			}
			
		}catch(Exception er)
		{
			strMesg = "����ʧ��";
			Logger.error(er.getMessage());
		}
		return strMesg;
	}
	
	/**
	 * ����:��ȡ����˫����λID
	 * @param istwin		�Ƿ�˫����λ
	 * @param twinlocation	λ�ã�1�����棻2������
	 * @param cargoSpaceId	����ID
	 * @param floor 		��
	 * @param column 		��
	 * @param platoon 		��
	 * @return
	 */
	private String getTwincsid(String istwin, String twinlocation, String cargoSpaceId, int platoon, int column, int floor) {
		
		String twincsid = "";
		int twinplatoon;
		
		if(istwin.equals("Y") && (twinlocation.equals("1") || twinlocation.equals("2"))){
			
			if(twinlocation.equals("1")){	//���Ż�λ
				
				
			}else{	//���Ż�λ
				
			}
			
		}
		return twincsid;
	}

	/**
	 * ����:������λ����������������������ã�
	 * @param whAreaId, Platoon, Column, floor
	 * @param cstype, storagetype, puttype, picktype, usagetype, attributetype, demandtype
	 * @param length, width, height, volume, xcoordinate, ycoordinate, zcoordinate
	 * @param environment, layernum, loadweight, palletnum, isproductmixed, isbatchmixed
	 * @param strUserCode, strUserName
	 * @return
	 */
	public String addCargoSpaceNode(String whAreaId, String Platoon, String Column, String floor, 
			String cstype, String storagetype, String puttype, String picktype, String usagetype, String attributetype, String demandtype, 
			String length, String width, String height, String volume, String xcoordinate, String ycoordinate, String zcoordinate, 
			String environment, String layernum, String loadweight, String palletnum, String strUserCode, String strUserName)
	{
		String strMesg = "";
		EntityDAO dao = BMSService.getm_EntityDAO();
		ICargoSpaceBus cargoSpaceBus = new CargoSpaceBusImpl(dao);
		
		String currentTime = StrTools.getCurrDateTime(2);
		
		try
		{
			String warehouseid = whAreaId.substring(0, 3);	//�ֿ�ID 3λ

			//���û�λ���(�ֿ�3λ������3λ�����в�6λ)
			String cargoSpaceId = whAreaId + getCode(Integer.parseInt(Platoon)) + getCode(Integer.parseInt(Column)) + getCode(Integer.parseInt(floor));
			//��λ����
			String cargoSpaceName = getCode(Integer.parseInt(Platoon)) + "��" + 
				getCode(Integer.parseInt(Column)) + "��" + getCode(Integer.parseInt(floor)) + "��";
			
			//�жϸû�λ�Ƿ��Ѿ�����
			if(cargoSpaceBus.getCargoSpaceById(cargoSpaceId) != null){
				strMesg = "����ʧ�ܣ���λ" + cargoSpaceId + "�Ѿ����ڣ�";
			}else{
				  
				//����һ����λ
				BaseCargospace cargoSpace = new BaseCargospace();
				
				cargoSpace.setCargoSpaceId(cargoSpaceId);		//��λID
				cargoSpace.setCargoSpaceName(cargoSpaceName);	//��λ����
				cargoSpace.setCsstatus("1");					//��λ״̬ 1���ջ�λ
				cargoSpace.setCsplatoon(new Integer(Platoon));	//��λ��
				cargoSpace.setCscolumn(new Integer(Column));	//��λ��
				cargoSpace.setCsfloor(new Integer(floor));		//��λ��
				cargoSpace.setCstype(cstype); 					//��λ����
				cargoSpace.setInlock("N");						//�����
				cargoSpace.setOutlock("N");						//������
				cargoSpace.setWarehouseid(warehouseid);			//�ֿ�ID
				cargoSpace.setWhAreaId(whAreaId);				//����ID
				cargoSpace.setStoragetype(storagetype);			//�洢����(�洢��λ���У�,�䣬��)
				cargoSpace.setPuttype(puttype);					//�ϼ�����(������ͣ�1��ֻ�ܷ���һ��2���ɶ�η���)
				cargoSpace.setPicktype(picktype);				//��ѡ����(�������ͣ�1��ֻ�ܷ���һ�Σ�2���ɶ�η���)
				cargoSpace.setLength(Double.parseDouble(length));	// ��
				cargoSpace.setWidth(Double.parseDouble(width));		// ��
				cargoSpace.setHeight(Double.parseDouble(height));	// ��
				cargoSpace.setVolume(Double.parseDouble(volume));	// ���
				cargoSpace.setXcoordinate(Double.parseDouble(xcoordinate));	// x����
				cargoSpace.setYcoordinate(Double.parseDouble(ycoordinate));	// y����
				cargoSpace.setZcoordinate(Double.parseDouble(zcoordinate));	// z����
				cargoSpace.setEnvironment(environment);			// ����
				cargoSpace.setLayernum(new Integer(layernum));	// ����
				cargoSpace.setLoadweight(Double.parseDouble(loadweight));	// ����
				cargoSpace.setPalletnum(new Integer(palletnum));//�ɷ�������
				cargoSpace.setHaspalletnum(0);					//�ѷ�������
				
				cargoSpace.setUsagetype(usagetype) ;   			// ��λʹ��
				cargoSpace.setAttributetype(attributetype);   	// ��λ����
				cargoSpace.setDemandtype(demandtype);   		// ��ת����
				
				cargoSpace.setCreatetime(currentTime);
				cargoSpace.setCreatemanid(strUserCode);
				cargoSpace.setUpdatetime(currentTime);
				cargoSpace.setUpdatemanid(strUserCode);
				
				cargoSpaceBus.addCargospace(cargoSpace);
				
				Logger.info("�û�" + strUserName + "����˻�λ" + cargoSpaceId);
				strMesg = "�����ɹ�";
			}

			
		}catch(Exception er)
		{
			strMesg = "����ʧ��";
			Logger.error(er.getMessage());
		}
		return strMesg;
	}
	
	/**
	 * ����:�޸Ļ�λ
	 * @param cargoSpaceId, cargoAreaId, inlock, outlock
	 * @param cstype, storagetype, puttype, picktype, usagetype, attributetype, demandtype
	 * @param length, width, height, volume, xcoordinate, ycoordinate, zcoordinate
	 * @param environment, layernum, loadweight, palletnum, isproductmixed, isbatchmixed
	 * @param strUserCode, strUserName
	 * @return
	 */
	public String updateCargoSpaceNode(String cargoSpaceId, String cargoAreaId, String inlock, String outlock, 
			String cstype, String storagetype, String puttype, String picktype, String usagetype, String attributetype, String demandtype,
			String length, String width, String height, String volume, String xcoordinate, String ycoordinate, String zcoordinate, 
			String environment, String layernum, String loadweight, String palletnum, String strUserCode, String strUserName)
	{
		String strMesg = "";
		EntityDAO dao = BMSService.getm_EntityDAO();
		ICargoSpaceBus cargoSpaceBus = new CargoSpaceBusImpl(dao);
		
		String currentTime = StrTools.getCurrDateTime(2);
		
		try
		{
			BaseCargospace cargoSpace = cargoSpaceBus.getCargoSpaceById(cargoSpaceId);
			if(cargoAreaId!=null && !cargoAreaId.equals("")){
				cargoSpace.setCargoAreaId(cargoAreaId);		//����ID
			}
			
			cargoSpace.setInlock(inlock);				//����� Y.�� N.��
			cargoSpace.setOutlock(outlock);				//������ Y.�� N.��
			cargoSpace.setCstype(cstype);				//��λ����
			cargoSpace.setStoragetype(storagetype);		//�洢����
			cargoSpace.setPuttype(puttype);				//�ϼ�����
			cargoSpace.setPicktype(picktype);			//��ѡ����
			cargoSpace.setLength(Double.parseDouble(length));	//��
			cargoSpace.setWidth(Double.parseDouble(width));		//��
			cargoSpace.setHeight(Double.parseDouble(height));	//��
			cargoSpace.setVolume(Double.parseDouble(volume));	//���
			cargoSpace.setXcoordinate(Double.parseDouble(xcoordinate));	//x����
			cargoSpace.setYcoordinate(Double.parseDouble(ycoordinate));	//y����
			cargoSpace.setZcoordinate(Double.parseDouble(zcoordinate));	//z����
			cargoSpace.setEnvironment(environment);				//����
			cargoSpace.setLayernum(new Integer(layernum));		//����
			cargoSpace.setLoadweight(Double.parseDouble(loadweight));	//����
			cargoSpace.setPalletnum(new Integer(palletnum));	//�ɷ�������
			
			cargoSpace.setUsagetype(usagetype) ;   			// ��λʹ��
			cargoSpace.setAttributetype(attributetype);   	// ��λ����
			cargoSpace.setDemandtype(demandtype);   		// ��ת����
			
			cargoSpace.setUpdatetime(currentTime);				//����޸�ʱ��
			cargoSpace.setUpdatemanid(strUserCode);				//����޸���
			
			cargoSpaceBus.updateCargospace(cargoSpace);
			
			Logger.info("�û�" + strUserName + "�޸��˻�λ" + cargoSpaceId);
			strMesg = "�޸ĳɹ�";

			
		}catch(Exception er)
		{
			strMesg = "�޸�ʧ��";
			Logger.error(er.getMessage());
		}
		return strMesg;
	}

	/**
	 * ����:ɾ����λ
	 * @param strId
	 * @param strUserName
	 * @return
	 */
	public String deleteCargoSpaceTree(String strId, String strUserName)
	{
		String strMesg = "";
		EntityDAO dao = BMSService.getm_EntityDAO();
		ICargoSpaceBus cargoSpaceBus = new CargoSpaceBusImpl(dao);

		try
		{
			cargoSpaceBus.deletCargospaceById(strId);
			Logger.info("�û�" + strUserName + "ɾ���˻�λ" + strId);
			strMesg = "ɾ���ɹ�!";
			
		}catch(Exception er)
		{
			strMesg = "ɾ��ʧ��!";
			Logger.error(er.getMessage());
		}
		return strMesg;
	}
	
	/**
	 * ����:ɾ����������Ļ�λ
	 * @param strId
	 * @param strUserName
	 * @return
	 */
	public String deleteCsTreeByAlleyId(String strId, String strUserName)
	{
		String strMesg = "";
		EntityDAO dao = BMSService.getm_EntityDAO();
		ICargoSpaceBus cargoSpaceBus = new CargoSpaceBusImpl(dao);
		
		try
		{
			cargoSpaceBus.deletCargospaceByAlleyId(strId);
			Logger.info("�û�" + strUserName + "ɾ���������" + strId + "�����л�λ");
			strMesg = "ɾ���ɹ�!";
		}catch(Exception er)
		{
			strMesg = "ɾ��ʧ��!";
			Logger.error(er.getMessage());
		}
		return strMesg;
	}
	
	/**
	 * ����:�����λ����
	 * @param num
	 * @return
	 */
	private String getCode(int num){
		String nextNo = "";
		String temNo = String.valueOf(num);
		if(temNo.length() != 2){
			for(int i=0; i<2-temNo.length(); i++){
				nextNo = nextNo + "0";
			}
		}
		nextNo = nextNo + temNo;
		return nextNo;
	}
}
