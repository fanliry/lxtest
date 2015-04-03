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
 * ���ܣ���ѯ���̵���Ϣ
 * @param needcheckid      ���̵�id
 * @param jobid            ��ҵ��
 * @param inouttype        �������
 * @param cargoSpaceId     ��ҵ��λ
 * @param createtimeform   ����ʱ��from
 * @param createtimeto     ����ʱ��to
 * @param handleflag       �����ʶ
 * @param traycode         ��������
 * @param taskno           �����
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
			 throw new  Exception("������̵���Ϣ��SQL������InventoryNeedCheckBusImp.getInventNeedCheck��"+e.getMessage());
	  }
	   return strSql;
	 }
/**
 * ���ܣ����¿�棬��λ״�壬���̵�״̬			                                                
 * @param ids
 * @param user
 * @param flag 1.����2.����
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
		//������̵��¼
		iNeedcheck = needDao.getINeedcheckInfo(needcheckId[i]);
		//��û�λ��Ϣ
		cargospace = cargoSpaceDao.getCargoSpaceById(iNeedcheck.getCargoSpaceId());
		//��ÿ����Ϣ
		iStoragesls = iInventoryDao.getInventoryByJobIdAndTrayCode(iNeedcheck.getJobid(),iNeedcheck.getTraycode());
		//���Ŀ��״̬��δ����
		if (iStoragesls!=null&&iStoragesls.size()>0) {
			for (int j = 0; j < iStoragesls.size(); j++) {
				iStorage = iStoragesls.get(j);
				iStorage.setStatus("0");
				list.add(iStorage);
			}		
		}else {
			return meg = "������ҵID,���������ȡ���ʧ�ܣ�";
		}
		//���Ļ�λ״̬��1���棨����λ����2���ϣ��ջ�λ��
		if (flag.equals("1")) {//���棺��λ״̬������λ����棺δ���䣻�����־���Ѿ�����
			cargospace.setCsstatus("2");
			iNeedcheck.setHandlecontent(user+"����λ"+cargospace.getCargoSpaceName()+"����Ϊ����λ"+StrTools.getCurrDateTime(2));
		}
	    if (flag.equals("2")) {//���ϣ���λ״̬���ջ�λ����棺δ���䣻�����־���Ѿ�����
	    	cargospace.setCsstatus("1");
	    	iNeedcheck.setHandlecontent(user+"����λ"+cargospace.getCargoSpaceName()+"����Ϊ�ջ�λ"+StrTools.getCurrDateTime(2));
		}
	    //�������̵㴦���־���Ѿ�����Y
	    iNeedcheck.setHandleflag("Y");//�����־���Ѿ�����
	    //������¡�
	    if (updateAll(iNeedcheck, cargospace, list)) {
	    	return meg ="Y";
		}else {
			return meg = "����ʧ��!";
		}
	}		
	} catch (Exception e) {
		throw new  Exception("���¿�棬��λ״�壬���̵�״̬��InventoryNeedCheckBusImp.updateInventAndCargoSpace��"+e.getMessage());
	}
	return meg;
}
/**
 * ����:������µĿ�档��λ״̬�Լ����̵��¼
 * @param iNeedcheck ���̵��¼
 * @param cargospace ��λ
 * @param iStoragesls ��漯��
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
		Logger.error("���¿�棬��λ�����InventoryNeedCheckBusImp.updateAll"+he.getMessage());
		he.printStackTrace();
		return false;
	}finally
	{
		dao.closeSession(session);
		Logger.error("���̵㱣��ɹ������̵��¼ID��"+iNeedcheck.getNeedcheckid()+"�̵��λ��"+cargospace.getCargoSpaceName());
	} 
	
	return true;
}
public InventoryNeedcheck getINeedcheckInfo(String needCheckId) throws Exception{
	return needDao.getINeedcheckInfo(needCheckId);
}

/**
 * ���ܣ����ݿ�λID��ȡ�쳣��
 * @param needCheckId
 * @return
 * @throws Exception
 */
public InventoryNeedcheck getINeedcheckInfoByCargospaceId(String cargospaceId)
	throws Exception{
	return needDao.getINeedcheckInfoByCargospaceId(cargospaceId);
}
}
