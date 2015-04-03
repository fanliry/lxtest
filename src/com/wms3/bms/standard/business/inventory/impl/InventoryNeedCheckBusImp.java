package com.wms3.bms.standard.business.inventory.impl;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.inventory.IInventoryNeedCheckBus;
import com.wms3.bms.standard.dao.base.IBaseCargoSpaceDao;
import com.wms3.bms.standard.dao.base.impl.BaseCargoSpaceDaoImpl;
import com.wms3.bms.standard.dao.inventory.IInventoryDao;
import com.wms3.bms.standard.dao.inventory.IInventoryNeedCheckDao;
import com.wms3.bms.standard.dao.inventory.impl.InventoryDaoImpl;
import com.wms3.bms.standard.dao.inventory.impl.InventoryNeedCheckDaoImpl;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.inventory.InventoryNeedcheck;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;

public class InventoryNeedCheckBusImp implements IInventoryNeedCheckBus {
	protected EntityDAO dao = null;
	protected IInventoryNeedCheckDao needDao;
	protected IBaseCargoSpaceDao cargoSpaceDao;
	protected IInventoryDao iInventoryDao;
	public InventoryNeedCheckBusImp(EntityDAO dao){
		this.dao = dao;	  
		this.needDao = new InventoryNeedCheckDaoImpl(dao);
		this.cargoSpaceDao = new BaseCargoSpaceDaoImpl(dao);
		this.iInventoryDao = new InventoryDaoImpl(dao);
	}
/**
 * 功能：查询需盘点信息
 * @param needcheckid      需盘点id
 * @param jobid            作业号
 * @param inouttype        入出类型
 * @param cargoSpaceId     作业货位
 * @param createtimeform   生成时间from
 * @param createtimeto     生成时间to
 * @param handleflag       处理标识
 * @param traycode         托盘条码
 * @param taskno           任务号
 * @return
 * @throws Exception
 */
public String[] getInventNeedCheck(String needcheckid,
			String jobid, String inouttype, String cargoSpaceId,
			String createtimeform, String createtimeto, String handleflag,
			String traycode, String taskno) throws Exception {
	String[] strSql = new String[2];	
	StringBuilder strBud = new StringBuilder();
	try {		
		strBud.append("From InventoryNeedcheck where 1=1");
		if (needcheckid != null && needcheckid.trim().length() > 0) {
		    strBud.append(" and needcheckid='"+needcheckid+"'");
		}
		if (jobid != null && jobid.trim().length() > 0) {
			strBud.append(" and jobid='"+jobid+"'");
		}
		if (inouttype !=null && inouttype.trim().length() > 0) {
			strBud.append(" and inouttype='"+inouttype+"'");
		}
		if (cargoSpaceId !=null && cargoSpaceId.trim().length() > 0) {
			strBud.append(" and cargoSpaceId='"+cargoSpaceId+"'");
		}
		if (createtimeform !=null && createtimeform.trim().length() > 0) {
			strBud.append(" and createtime>='"+createtimeform+"'");
		}
		if (createtimeto !=null && createtimeto.trim().length() > 0) {
			strBud.append(" and createtime<='"+createtimeto+"'");
		}
		if (handleflag !=null && handleflag.trim().length() > 0) {
			strBud.append(" and handleflag='"+handleflag+"'");
		}
		if (traycode !=null && traycode.trim().length() > 0) {
			strBud.append(" and traycode='"+traycode+"'");
		}
        if (taskno !=null && taskno.trim().length() > 0) {
			strBud.append(" and taskno='"+taskno+"'");
		}  
        strSql[0] = strBud.toString();
        strSql[1] = "select count(*) "+strBud.toString();
	 } catch (Exception e) {
			 throw new  Exception("获得需盘点信息的SQL语句出错，InventoryNeedCheckBusImp.getInventNeedCheck："+e.getMessage());
	  }
	   return strSql;
	 }
/**
 * 功能：更新库存，货位状体，需盘点状态			                                                
 * @param ids
 * @param user
 * @param flag 1.保存2.作废
 * @return
 * @throws Exception
 */
public String updateInventAndCargoSpace(String ids, String flag, String user)
		throws Exception {
	String meg = "N";
	try {
	String[] needcheckId = ids.split(",");
	for (int i = 0; i < needcheckId.length; i++) {
		InventoryNeedcheck iNeedcheck = null;
		BaseCargospace cargospace = null;
		List<InventoryStorage> iStoragesls = null;
		ArrayList<InventoryStorage> list = new ArrayList<InventoryStorage>();
		InventoryStorage iStorage = null;
		//获得需盘点记录
		iNeedcheck = needDao.getINeedcheckInfo(needcheckId[i]);
		//获得货位信息
		cargospace = cargoSpaceDao.getCargoSpaceById(iNeedcheck.getCargoSpaceId());
		//获得库存信息
		iStoragesls = iInventoryDao.getInventoryByJobIdAndTrayCode(iNeedcheck.getJobid(),iNeedcheck.getTraycode());
		//更改库存状态：未分配
		if (iStoragesls!=null&&iStoragesls.size()>0) {
			for (int j = 0; j < iStoragesls.size(); j++) {
				iStorage = iStoragesls.get(j);
				iStorage.setStatus("0");
				list.add(iStorage);
			}		
		}else {
			return meg = "根据作业ID,托盘条码获取库存失败！";
		}
		//更改货位状态：1保存（满货位），2作废（空货位）
		if (flag.equals("1")) {//保存：货位状态：满货位；库存：未分配；处理标志：已经处理
			cargospace.setCsstatus("2");
			iNeedcheck.setHandlecontent(user+"将货位"+cargospace.getCargoSpaceName()+"保存为满货位"+StrTools.getCurrDateTime(2));
		}
	    if (flag.equals("2")) {//作废：货位状态：空货位；库存：未分配；处理标志：已经处理
	    	cargospace.setCsstatus("1");
	    	iNeedcheck.setHandlecontent(user+"将货位"+cargospace.getCargoSpaceName()+"保存为空货位"+StrTools.getCurrDateTime(2));
		}
	    //更改需盘点处理标志：已经处理：Y
	    iNeedcheck.setHandleflag("Y");//处理标志：已经处理
	    //保存更新。
	    if (updateAll(iNeedcheck, cargospace, list)) {
	    	return meg ="Y";
		}else {
			return meg = "操作失败!";
		}
	}		
	} catch (Exception e) {
		throw new  Exception("更新库存，货位状体，需盘点状态，InventoryNeedCheckBusImp.updateInventAndCargoSpace："+e.getMessage());
	}
	return meg;
}
/**
 * 功能:保存更新的库存。货位状态以及需盘点记录
 * @param iNeedcheck 需盘点记录
 * @param cargospace 货位
 * @param iStoragesls 库存集合
 * @return
 */
private boolean updateAll(InventoryNeedcheck iNeedcheck,BaseCargospace cargospace,List<InventoryStorage> iStoragesls) {
	InventoryStorage iStorage = null;
	Session session = dao.getSession();
	try {
		Transaction tx = session.beginTransaction();
		if (iStoragesls!=null&&iStoragesls.size()>0) {
			for (int i = 0; i < iStoragesls.size(); i++) {
				iStorage = iStoragesls.get(i);
				session.update(iStorage);
			}
		}
		session.update(iNeedcheck);
		session.save(cargospace);
		tx.commit();
	} catch (HibernateException he) {		
		Logger.error("更新库存，货位，出差！InventoryNeedCheckBusImp.updateAll"+he.getMessage());
		he.printStackTrace();
		return false;
	}finally
	{
		dao.closeSession(session);
		Logger.error("需盘点保存成功！需盘点记录ID："+iNeedcheck.getNeedcheckid()+"盘点货位："+cargospace.getCargoSpaceName());
	} 
	
	return true;
}
public InventoryNeedcheck getINeedcheckInfo(String needCheckId) throws Exception{
	return needDao.getINeedcheckInfo(needCheckId);
}

/**
 * 功能：根据库位ID获取异常单
 * @param needCheckId
 * @return
 * @throws Exception
 */
public InventoryNeedcheck getINeedcheckInfoByCargospaceId(String cargospaceId)
	throws Exception{
	return needDao.getINeedcheckInfoByCargospaceId(cargospaceId);
}
}
