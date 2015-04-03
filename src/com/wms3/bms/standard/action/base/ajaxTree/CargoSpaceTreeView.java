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
 * ==========      描述：货位树     ===========
 * ==========      作者：gui     ===========
 * ==========================================
 */
public class CargoSpaceTreeView implements BlueTreeInterFace{
	
	/**
	 * 功能:根据父节点id加载货位树
	 * @return
	 */
	public List getTreeElements(String strPid) {
		EntityDAO dao  = BMSService.getm_EntityDAO(); 
		
		List list =  new ArrayList();
		
		boolean pidIsRoot = false; 		//父节点是否为根节点
		boolean pidIsWh = false; 		//父节点是否为仓库节点
		boolean pidIsWhArea = false; 	//父节点是否为库区节点
		boolean pidIsAlley = false; 	//父节点是否为巷道节点
		
		IWarehouseBus warehouseBus = new WarehouseBusImpl(dao);
		IWhAreaBus whAreaBus = new WhAreaBusImpl(dao);
		IAlleyBus alleyBus = new AlleyBusImpl(dao);
		ICargoSpaceBus cargoSpaceBus = new CargoSpaceBusImpl(dao);
		
		try{
			if(strPid.equals("0")){//判断父节点是否为根节点
				pidIsRoot = true;
			}else{
				List ls1 = warehouseBus.getWarehouseList();
				for(int i=0; i<ls1.size(); i++){
					BaseWarehouse warehouse= (BaseWarehouse)ls1.get(i); 
					String warehouseId = warehouse.getWarehouseid();
					if(warehouseId.equals(strPid)){//判断父节点是否为仓库节点
						pidIsWh = true;
						break;
					}
				}
				if(!pidIsWh){
					List ls2 = whAreaBus.getWhAreaList();
					for(int i=0; i<ls2.size(); i++){
						BaseWharea wharea= (BaseWharea)ls2.get(i);
						String whAreaId = wharea.getwhAreaId();
						if(whAreaId.equals(strPid)){//判断父节点是否为库区节点
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
						if(alleyId.equals(strPid)){//判断父节点是否为巷道节点
							pidIsAlley = true;
							break;
						}
					}
				}
			}
			
			if(pidIsRoot){//如果父节点为根节点，则读取根节点下的子列表
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
			
			if(pidIsWh){//如果父节点为仓库节点，则读取仓库节点下的子列表
				List ls = whAreaBus.getWhAreaQuery(strPid, ""); 
				for(int i=0; i<ls.size(); i++){
					
					BaseWharea wharea= (BaseWharea)ls.get(i);
					String id = wharea.getwhAreaId();
					String pid = strPid;
					String name = wharea.getwhAreaName();
					boolean ischild1 = alleyBus.isWhAreaHasChildNode(id);	//库区下是否有巷道（立体库）
					
					if(ischild1){//如果库区下面有巷道（立体库）
						String url = BMSService.getm_strServerPath() +  "/BMSService?actionCode=baseCargoSpaceAction&method=initWhArea&whAreaId="+id;
						list.add(new BlueTreeData(id, pid, name, url, null, ischild1, null, null));
						
					}else{//如果库区下面没有巷道（立体库）
						boolean ischild2 = cargoSpaceBus.isWhAreaHasChildNode(id);	//库区下是否有货位
						String url = BMSService.getm_strServerPath() +  "/BMSService?actionCode=baseCargoSpaceAction&method=initWhArea&whAreaId="+id;
						list.add(new BlueTreeData(id, pid, name, url, null, ischild2, null, null));
					}
					
				}
		}
			
			if(pidIsWhArea){//如果父节点为库区节点，则读取库区节点下的子列表
				List ls1 = alleyBus.getAlleyQuery("", "", strPid); 
				
				if(ls1!=null && ls1.size()>0){	//如果库区下面有巷道（立体库）
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
			
			if(pidIsAlley){//如果父节点为巷道节点，则读取巷道节点下的子列表
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
			Logger.error("根据父节点id加载货位树失败:" + er.getMessage());
		}
		return list;
	}
		
	/**
	 * 功能:根据节点id获取父节点信息
	 * @param strId
	 * @return
	 */
	public List getParentInfo(String strId, String flag) {
		
		EntityDAO dao  = BMSService.getm_EntityDAO(); 
		List list =  new ArrayList();
	
		try{
			// "0"表示传入的id为库区ID
			if(flag.equals("0")){
				IWhAreaBus whAreaBus = new WhAreaBusImpl(dao);
				BaseWharea wharea= whAreaBus.getWhareaById(strId);
				String whAreaName = wharea.getwhAreaName();
				String warehousename = wharea.getWarehousename();
				
				list.add(0, warehousename); 	//0:仓库名
				list.add(1, whAreaName); 		//1:库区名
			}
			
			//"1"表示传入的id为巷道ID
			if(flag.equals("1")){
				IAlleyBus alleyBus = new AlleyBusImpl(dao);
				BaseAlley alley= alleyBus.getAlleyById(strId);
				String whAreaName = alley.getWhAreaName();
				String warehousename = alley.getWarehousename();
				String cargoAlleyName = alley.getCargoAlleyName();
				
				list.add(0, warehousename); 	//0:仓库名
				list.add(1, whAreaName); 		//1:库区名
				list.add(2, cargoAlleyName);   	//2:巷道名
			}
			
		}catch(Exception er)
		{
			Logger.error("根据节点id获取父节点信息失败:" + er.getMessage());
		}
		return list;
	}
	
	/**
	 * 功能:新增货位（立体库区批量生成用）
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
			
			String cargoSpaceId = "";   	//货位编号
			String cargoSpaceName = "";   	//货位名称
			int m_iPlatoon = 0;		     	//货位排
			int m_iColumn = 0;			 	//货位列
			int m_iFloor = 0;			 	//货位层
			
			String warehouseid = cargoAlleyId.substring(0, 3);	//仓库ID 3位
			String whAreaId = cargoAlleyId.substring(0, 6);		//库区ID 6位（前3位是仓库编号）
			String twincsid = "";			//相邻双升货位ID
			
			//生成货位
			for(int i=(minPlatoon>0 ? minPlatoon : 1); i<=maxPlatoon; i++){
				for(int j=(minColumn>0 ? minColumn : 1); j<=maxColumn; j++){
					//生成列
					m_iColumn = j;
					//生成排
					m_iPlatoon = i;
					
					for(int k=1; k<=floorNum; k++){
						//生成层
						m_iFloor = k;
						
						//设置货位编号(仓库3位，库区3位，排列层6位)
						cargoSpaceId = whAreaId + getCode(m_iPlatoon) + getCode(m_iColumn) + getCode(m_iFloor);
						cargoSpaceName = getCode(m_iPlatoon) + "排" + getCode(m_iColumn) + "列" + getCode(m_iFloor) + "层";
						
						//如果该货位已经存在，就不再生成
						if(cargoSpaceBus.getCargoSpaceById(cargoSpaceId)!=null){
							Logger.info("货位" + cargoSpaceId + "已经存在！");
							strMesg = "货位已经存在";
							continue;
						}
						
						//新增一个货位
						BaseCargospace cargoSpace = new BaseCargospace();
						
						cargoSpace.setCargoSpaceId(cargoSpaceId);		//货位ID
						cargoSpace.setCargoSpaceName(cargoSpaceName);	//货位名称
						cargoSpace.setCsstatus("1");					//货位状态 1：空货位
						cargoSpace.setCsplatoon(m_iPlatoon);			//货位排
						cargoSpace.setCscolumn(m_iColumn);				//货位列
						cargoSpace.setCsfloor(m_iFloor);				//货位层
						cargoSpace.setCstype(cstype); 					//货位类型
						cargoSpace.setInlock("N");						//入库锁
						cargoSpace.setOutlock("N");						//出库锁
						cargoSpace.setCargoAlleyId(cargoAlleyId);		//巷道ID
						cargoSpace.setWarehouseid(warehouseid);			//仓库ID
						cargoSpace.setWhAreaId(whAreaId);				//库区ID
						cargoSpace.setStoragetype(storagetype);			//存储类型(存储单位：托，,箱，件)
						cargoSpace.setPuttype("1");						//上架类型(入库类型：1：只能分配一次2：可多次分配)
						cargoSpace.setPicktype("1");					//拣选类型(出库类型：1：只能分配一次；2：可多次分配)
						cargoSpace.setLength(Double.parseDouble(length));	// 长
						cargoSpace.setWidth(Double.parseDouble(width));		// 宽
						cargoSpace.setHeight(Double.parseDouble(height));	// 高
						cargoSpace.setVolume(Double.parseDouble(volume));	// 体积
						cargoSpace.setLayernum(new Integer(layernum));		// 层数
						cargoSpace.setLoadweight(Double.parseDouble(loadweight));	// 承重
						cargoSpace.setPalletnum(1);						//可放托盘数
						cargoSpace.setHaspalletnum(0);					//已放托盘数
						
						if(istwin.equals("Y")){
							cargoSpace.setIstwin("Y");					//是否双升货位 Y.是 N.否
							
							if((i-minPlatoon)==0){	//巷道左里
								cargoSpace.setLocation("1");			//位置：1：里面；2：外面
								twincsid = whAreaId + getCode(m_iPlatoon+1) + getCode(m_iColumn) + getCode(m_iFloor);
								cargoSpace.setTwincsid(twincsid);		//相邻双升货位ID
							}else if((i-minPlatoon)==1){	//巷道左外
								cargoSpace.setLocation("2");			//位置：1：里面；2：外面
								twincsid = whAreaId + getCode(m_iPlatoon-1) + getCode(m_iColumn) + getCode(m_iFloor);
								cargoSpace.setTwincsid(twincsid);		//相邻双升货位ID
							}else if((i-minPlatoon)==2){	//巷道右外
								cargoSpace.setLocation("2");			//位置：1：里面；2：外面
								twincsid = whAreaId + getCode(m_iPlatoon+1) + getCode(m_iColumn) + getCode(m_iFloor);
								cargoSpace.setTwincsid(twincsid);		//相邻双升货位ID
							}else if((i-minPlatoon)==3){	//巷道右里
								cargoSpace.setLocation("1");			//位置：1：里面；2：外面
								twincsid = whAreaId + getCode(m_iPlatoon-1) + getCode(m_iColumn) + getCode(m_iFloor);
								cargoSpace.setTwincsid(twincsid);		//相邻双升货位ID
							}
							
						}else{
							cargoSpace.setIstwin("N");					//是否双升货位 Y.是 N.否
						}
						
						cargoSpace.setCreatetime(currentTime);
						cargoSpace.setCreatemanid(strUserCode);
						cargoSpace.setUpdatetime(currentTime);
						cargoSpace.setUpdatemanid(strUserCode);
						
						cargoSpaceBus.addCargospace(cargoSpace);
						
						Logger.info("用户" + strUserName + "添加了货位" + cargoSpaceId);
						
						strMesg = "新增成功";
					}
				}
			}
			
		}catch(Exception er)
		{
			strMesg = "新增失败";
			Logger.error(er.getMessage());
		}
		return strMesg;
	}
	
	/**
	 * 功能:获取相邻双升货位ID
	 * @param istwin		是否双升货位
	 * @param twinlocation	位置：1：里面；2：外面
	 * @param cargoSpaceId	库区ID
	 * @param floor 		层
	 * @param column 		列
	 * @param platoon 		排
	 * @return
	 */
	private String getTwincsid(String istwin, String twinlocation, String cargoSpaceId, int platoon, int column, int floor) {
		
		String twincsid = "";
		int twinplatoon;
		
		if(istwin.equals("Y") && (twinlocation.equals("1") || twinlocation.equals("2"))){
			
			if(twinlocation.equals("1")){	//里排货位
				
				
			}else{	//外排货位
				
			}
			
		}
		return twincsid;
	}

	/**
	 * 功能:新增货位（非立体库区，单个生成用）
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
			String warehouseid = whAreaId.substring(0, 3);	//仓库ID 3位

			//设置货位编号(仓库3位，库区3位，排列层6位)
			String cargoSpaceId = whAreaId + getCode(Integer.parseInt(Platoon)) + getCode(Integer.parseInt(Column)) + getCode(Integer.parseInt(floor));
			//货位名称
			String cargoSpaceName = getCode(Integer.parseInt(Platoon)) + "排" + 
				getCode(Integer.parseInt(Column)) + "列" + getCode(Integer.parseInt(floor)) + "层";
			
			//判断该货位是否已经存在
			if(cargoSpaceBus.getCargoSpaceById(cargoSpaceId) != null){
				strMesg = "新增失败，货位" + cargoSpaceId + "已经存在！";
			}else{
				  
				//新增一个货位
				BaseCargospace cargoSpace = new BaseCargospace();
				
				cargoSpace.setCargoSpaceId(cargoSpaceId);		//货位ID
				cargoSpace.setCargoSpaceName(cargoSpaceName);	//货位名称
				cargoSpace.setCsstatus("1");					//货位状态 1：空货位
				cargoSpace.setCsplatoon(new Integer(Platoon));	//货位排
				cargoSpace.setCscolumn(new Integer(Column));	//货位列
				cargoSpace.setCsfloor(new Integer(floor));		//货位层
				cargoSpace.setCstype(cstype); 					//货位类型
				cargoSpace.setInlock("N");						//入库锁
				cargoSpace.setOutlock("N");						//出库锁
				cargoSpace.setWarehouseid(warehouseid);			//仓库ID
				cargoSpace.setWhAreaId(whAreaId);				//库区ID
				cargoSpace.setStoragetype(storagetype);			//存储类型(存储单位：托，,箱，件)
				cargoSpace.setPuttype(puttype);					//上架类型(入库类型：1：只能分配一次2：可多次分配)
				cargoSpace.setPicktype(picktype);				//拣选类型(出库类型：1：只能分配一次；2：可多次分配)
				cargoSpace.setLength(Double.parseDouble(length));	// 长
				cargoSpace.setWidth(Double.parseDouble(width));		// 宽
				cargoSpace.setHeight(Double.parseDouble(height));	// 高
				cargoSpace.setVolume(Double.parseDouble(volume));	// 体积
				cargoSpace.setXcoordinate(Double.parseDouble(xcoordinate));	// x坐标
				cargoSpace.setYcoordinate(Double.parseDouble(ycoordinate));	// y坐标
				cargoSpace.setZcoordinate(Double.parseDouble(zcoordinate));	// z坐标
				cargoSpace.setEnvironment(environment);			// 环境
				cargoSpace.setLayernum(new Integer(layernum));	// 层数
				cargoSpace.setLoadweight(Double.parseDouble(loadweight));	// 承重
				cargoSpace.setPalletnum(new Integer(palletnum));//可放托盘数
				cargoSpace.setHaspalletnum(0);					//已放托盘数
				
				cargoSpace.setUsagetype(usagetype) ;   			// 库位使用
				cargoSpace.setAttributetype(attributetype);   	// 库位属性
				cargoSpace.setDemandtype(demandtype);   		// 周转需求
				
				cargoSpace.setCreatetime(currentTime);
				cargoSpace.setCreatemanid(strUserCode);
				cargoSpace.setUpdatetime(currentTime);
				cargoSpace.setUpdatemanid(strUserCode);
				
				cargoSpaceBus.addCargospace(cargoSpace);
				
				Logger.info("用户" + strUserName + "添加了货位" + cargoSpaceId);
				strMesg = "新增成功";
			}

			
		}catch(Exception er)
		{
			strMesg = "新增失败";
			Logger.error(er.getMessage());
		}
		return strMesg;
	}
	
	/**
	 * 功能:修改货位
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
				cargoSpace.setCargoAreaId(cargoAreaId);		//货区ID
			}
			
			cargoSpace.setInlock(inlock);				//入库锁 Y.是 N.否
			cargoSpace.setOutlock(outlock);				//出库锁 Y.是 N.否
			cargoSpace.setCstype(cstype);				//货位类型
			cargoSpace.setStoragetype(storagetype);		//存储类型
			cargoSpace.setPuttype(puttype);				//上架类型
			cargoSpace.setPicktype(picktype);			//拣选类型
			cargoSpace.setLength(Double.parseDouble(length));	//长
			cargoSpace.setWidth(Double.parseDouble(width));		//宽
			cargoSpace.setHeight(Double.parseDouble(height));	//高
			cargoSpace.setVolume(Double.parseDouble(volume));	//体积
			cargoSpace.setXcoordinate(Double.parseDouble(xcoordinate));	//x坐标
			cargoSpace.setYcoordinate(Double.parseDouble(ycoordinate));	//y坐标
			cargoSpace.setZcoordinate(Double.parseDouble(zcoordinate));	//z坐标
			cargoSpace.setEnvironment(environment);				//环境
			cargoSpace.setLayernum(new Integer(layernum));		//层数
			cargoSpace.setLoadweight(Double.parseDouble(loadweight));	//承重
			cargoSpace.setPalletnum(new Integer(palletnum));	//可放托盘数
			
			cargoSpace.setUsagetype(usagetype) ;   			// 库位使用
			cargoSpace.setAttributetype(attributetype);   	// 库位属性
			cargoSpace.setDemandtype(demandtype);   		// 周转需求
			
			cargoSpace.setUpdatetime(currentTime);				//最后修改时间
			cargoSpace.setUpdatemanid(strUserCode);				//最后修改人
			
			cargoSpaceBus.updateCargospace(cargoSpace);
			
			Logger.info("用户" + strUserName + "修改了货位" + cargoSpaceId);
			strMesg = "修改成功";

			
		}catch(Exception er)
		{
			strMesg = "修改失败";
			Logger.error(er.getMessage());
		}
		return strMesg;
	}

	/**
	 * 功能:删除货位
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
			Logger.info("用户" + strUserName + "删除了货位" + strId);
			strMesg = "删除成功!";
			
		}catch(Exception er)
		{
			strMesg = "删除失败!";
			Logger.error(er.getMessage());
		}
		return strMesg;
	}
	
	/**
	 * 功能:删除整个巷道的货位
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
			Logger.info("用户" + strUserName + "删除了巷道：" + strId + "下所有货位");
			strMesg = "删除成功!";
		}catch(Exception er)
		{
			strMesg = "删除失败!";
			Logger.error(er.getMessage());
		}
		return strMesg;
	}
	
	/**
	 * 功能:处理货位编码
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
