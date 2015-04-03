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
 * 描述: 库存冻结/释放业务类   
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
	 * 功能:根据条件查询冻结记录列表
	 * @param cargospaceid	货位
	 * @param lotid			批次属性
	 * @param ownerid		货主
	 * @param productid		品名
	 * @param holdcode		冻结原因
	 * @param holdby		冻结方法
	 * @param dateon_from	冻结日期
	 * @param dateon_to		冻结日期
	 * @param dateoff_from	释放日期
	 * @param dateoff_to	释放日期
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
			
			if(cargospaceid != null && cargospaceid.trim().length() > 0){		//货位
				strBud.append(" and hold.spaceid='").append(cargospaceid).append("'");
			}
			
			
			
			if(productid != null && productid.trim().length() > 0){		//品名
				strBud.append(" and hold.productid='").append(productid).append("'");
			}
			
			if(holdcode != null && holdcode.trim().length() > 0){		//冻结原因
				strBud.append(" and hold.holdcode='").append(holdcode).append("'");
			}
			
			if(holdby != null && holdby.trim().length() > 0){		//冻结方法
				strBud.append(" and hold.holdby='").append(holdby).append("'");
			}
			
			//冻结时间
	        if(dateon_from != null && dateon_from.trim().length()>0){
	        
	            strBud.append(" and hold.dateon>='").append(dateon_from).append("'");
	        }
	        if(dateon_to != null && dateon_to.trim().length()>0){
	            
	            strBud.append(" and hold.dateon<='").append(dateon_to).append(" 99:99:99'");
	        }
	        
	        //释放时间
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
			throw new Exception("根据条件查询冻结记录列表出错:" + er.getMessage());
		}
				
		return pt;	
	}

	/**
	 * 功能:根据条件查询库存记录的列表
	 * @param ownerid		货主
	 * @param productid		品名
	 * @param cargospaceid	货位
	 * @param lotid			批次属性
	 * @param traycode		托盘条码
	 * @param boxcode		箱条码
	 * @param productcode	产品条码
	 * @return 
	 * @throws Exception
	 */
	public List getStorageList(String productid, String cargospaceid, 
			String traycode, String boxcode, String productcode) throws Exception {
		
		List ls = null;
		
		try {
			StringBuilder strBud = new StringBuilder();
			strBud.append(" from InventoryStorage as storage where storage.cargoSpaceId in (select cargoSpaceId from BaseCargospace as cargospace where cargospace.csstatus ='1' or cargospace.csstatus ='2') ");	////状态  0:未分配，1：已分配 3:调整 4:货品调整；5：盘点
			
			
			if(productid != null && productid.trim().length() > 0){		//品名
				strBud.append(" and storage.productid='").append(productid).append("'");
			}
			
			if(cargospaceid != null && cargospaceid.trim().length() > 0){		//货位
				strBud.append(" and storage.cargoSpaceId='").append(cargospaceid).append("'");
			}
			
			
			if(traycode != null && traycode.trim().length() > 0){		//托盘条码
				strBud.append(" and storage.traycode='").append(traycode).append("'");
			}
			
			if(boxcode != null && boxcode.trim().length() > 0){		//箱条码
				strBud.append(" and storage.boxcode='").append(boxcode).append("'");
			}
			
			if(productcode != null && productcode.trim().length() > 0){		//产品条码
				strBud.append(" and storage.productcode='").append(productcode).append("'");
			}
			
			strBud.append(" order by storage.cargoSpaceId");
			
			ls = inventoryHoldDAO.querySQL(strBud.toString());
			
		} catch (Exception er) {
			throw new Exception("根据条件查询库存记录的列表出错:" + er.getMessage());
		}
				
		return ls;	
	}

	/**
	 * 功能:生成冻结记录
	 * @param ids			库存ids
	 * @param holdcode		冻结原因
	 * @param holdby		冻结方法
	 * @param dateoff		释放日期
	 * @param holdreason 	原因描述
	 * @param strUserCode 	用户代码
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
				hold.setStatus("1");				//状态(默认1,直接冻结)
				hold.setHoldflag("Y");				//是否冻结 Y-是,N-否
				hold.setHoldby(holdby);				//冻结方法 1.直接冻结 2.移至不合格品区
				hold.setHoldcode(holdcode);        	//冻结原因 1.产品过期 2.产品损坏
				hold.setHoldreason(holdreason);     //原因描述
				hold.setInventoryid(id[i]);     	//库存ID
				hold.setOwnerid(storage.getOwnerId());		//货主ID
				hold.setProductid(storage.getProductid());  //产品ID
				hold.setLotid(storage.getLotid());          //批次号
				hold.setSpaceid(storage.getCargoSpaceId());	//货位ID
				hold.setTraycode(storage.getTraycode());	//托盘条码
				hold.setBoxcode(storage.getBoxcode());      //箱条码
				hold.setProductcode(storage.getProductcode());  //产品条码
				hold.setQtyonhold(storage.getPnum());       //冻结数量
				hold.setNetweightonhold(storage.getPnetweight()); //冻结重量
				hold.setDateon(strTime);          	//冻结时间
				hold.setWhoon(strUserCode);         //冻结操作人
				hold.setDateoff(dateoff);         	//释放时间
				
				lshold.add(hold);
				
				BaseCargospace baseCargospace = cargospaceBus.getCargoSpaceById(storage.getCargoSpaceId()) ;
				//判断库存状态是不是未分配
				if(baseCargospace.getCsstatus().equals("1")||baseCargospace.getCsstatus().equals("2")){
					if(storage.getHoldnum()>0 || storage.getHoldnetweight()>0){
						strBackMsg = "库位里已存在冻结数量或是冻结重量，不能再冻结！";
						break;
					}else{
						storage.setHoldnum(storage.getPnum());
						storage.setHoldnetweight(storage.getPnetweight());
						lsstorage.add(storage);
					}
					
				}else{
					strBackMsg = "库位状态不是【空货位】或【满货位】，可能正在分配该库存，请重新查询后再生成冻结记录！";
					break;
				}
			}
			
			if(strBackMsg.equals("Y")){
				inventoryHoldDAO.addHold(lshold, lsstorage);
				Logger.info("用户["+strUserCode+"]，对库存:【" + ids + "】生成了冻结记录");
			}
			
		}catch(Exception er){
        	Logger.error("对库存:" + ids + "生成冻结记录时出错:" + er.getMessage());
            throw new Exception("对库存:" + ids + "生成冻结记录时出错:" + er.getMessage());
        }
		return strBackMsg; 
	}

	/**
	 * 功能:释放冻结记录
	 * @param ids			ids(冻结ID+"|"+库存ID)
	 * @param strUserCode 	用户代码
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
				//释放
				hold = inventoryHoldDAO.getHoldById(id[0]);
				if(hold.getHoldflag().equals("N")){
					strBackMsg = "冻结记录已经被释放，请重新查询后再释放！";
					break;
				}
				hold.setHoldflag("N");				//是否冻结 Y-是,N-否
				hold.setWhoon(strUserCode);         //冻结操作人
				hold.setDateoff(strTime);         	//释放时间
				lshold.add(hold);
				
				//清空库存冻结数量和冻结重量
				storage = inventoryDAO.getInventoryById(id[1]);
				storage.setHoldnum(0);
				storage.setHoldnetweight(0);
				lsstorage.add(storage);
				
			}
			
			if(strBackMsg.equals("Y")){
				inventoryHoldDAO.updateHold(lshold, lsstorage);
				Logger.info("用户["+strUserCode+"]，释放了冻结记录|库存:【" + ids + "】");
			}
			
		}catch(Exception er){
        	Logger.error("对冻结记录|库存:" + ids + "释放时出错:" + er.getMessage());
            throw new Exception("对冻结记录|库存:" + ids + "释放时出错:" + er.getMessage());
        }
		return strBackMsg; 
	}
	
}