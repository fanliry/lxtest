package com.wms3.bms.standard.action.rf;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.CommonTools;
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.dao.base.IBaseCargoSpaceDao;
import com.wms3.bms.standard.dao.base.impl.BaseCargoSpaceDaoImpl;
import com.wms3.bms.standard.dao.base.impl.BaseSeqDaoImpl;
import com.wms3.bms.standard.dao.inventory.impl.InventoryDaoImpl;
import com.wms3.bms.standard.dao.job.IJobDao;
import com.wms3.bms.standard.dao.job.impl.JobDaoImpl;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BaseSeq;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.schedule.ScheduleTask;

public class InjobSafeguardAction extends AbstractAction {

	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		
		
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
	    HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
	    EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
	    HttpSession httpsession = request.getSession(false);
	    
	    String traycode =CommonTools.getStrToGbk(request.getParameter("traycode"));
	    String jobid =CommonTools.getStrToGbk(request.getParameter("jobid"));
	    String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
	    String isWho = CommonTools.getStrToGbk(request.getParameter("isWho"));
	    try {
	    	if("1".equals(flag))
		    {
				if("rf".equals(isWho)){//rf���ά��
					strUrl = "/rf/lxyy/injob_safeguard.jsp";
				}else if("pc".equals(isWho)){//������->��ҵ����->���ά��
					strUrl = "/standard/jsp/inbound/job/injob_safeguard_pc.jsp";
				}
	    		
				String hql = "From InoutJob job, InoutJobdetail jobdetail where job.jobid = jobdetail.jobid " ;
				
				if(jobid != null && jobid.length() > 0){
					hql += " and job.jobid = '"+jobid+"' ";
					
				}
				if(traycode != null && traycode.length() > 0){
					hql += " and job.traycode = '"+traycode+"' ";
				}
				
				
				hql += " and job.status in ('1','2','3','4','8') and jobdetail.isinvoice<>'Y' and job.inOutType='1' ";
				hql += " order by job.createtime desc";
		    	
		    	List resultls = dao.searchEntities(hql);
		    	request.setAttribute("exResultList", resultls);
		    }
	    	
			request.getRequestDispatcher(strUrl).forward(request, response);
		} catch (Exception er) {
			Logger.error("���ά��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	    
	    
		return null;
	}
	/**
	 * ����:���������޸������ҵ
	 * 
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecEditJob(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {

		HttpServletRequest request = (HttpServletRequest) hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse) hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO) hsSysParam.get("dao");

		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode")); // ��������
		String jobid = CommonTools.getStrToGbk(request.getParameter("jobid")); // ��ҵ��
		String productstatus = CommonTools.getStrToGbk(request.getParameter("productstatus")); // ��Ʒ״̬
		String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo")); 	// ����(�������)
		String printdate = CommonTools.getStrToGbk(request.getParameter("printdate")); 	// ��������
		double jobnum = Double.parseDouble(request.getParameter("jobnum")); 		// ����
		String customerid = CommonTools.getStrToGbk(request.getParameter("customerid")); 	// ��Ӧ��
		
		String strUserCode = (String) httpsession.getAttribute("userCode");
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		IJobDao ijobdao = null;
		
		try {
			String strBackMsg = "Y";
			if("rf".equals(flag)){//rf���ά��
				strUrl = "/rf/lxyy/injob_safeguard.jsp";
			}else if("pc".equals(flag)){//������->��ҵ����->���ά��
				strUrl = "/standard/jsp/inbound/job/inbound_job_list.jsp";
			}
			
			String hql = "From InoutJob job, InoutJobdetail jobdetail where job.jobid = jobdetail.jobid " ;
			
			if(jobid != null && jobid.length() > 0){
				hql += " and job.jobid = '"+jobid+"' ";
				
			}
			if(traycode != null && traycode.length() > 0){
				hql += " and job.traycode = '"+traycode+"' ";
			}
			
			
			hql += " and job.status in ('1','2','3','4','8') and jobdetail.isinvoice<>'Y' and job.inOutType='1' ";
			hql += " order by job.createtime desc";
			
			List resultls = dao.searchEntities(hql);
	    	if(resultls != null)
	    	{
	    		List<InventoryStorage> inv = null;
	    		Object[] obj = (Object[])resultls.get(0);
	    		InoutJob job = (InoutJob)obj[0];
	    		InoutJobdetail jobD = (InoutJobdetail)obj[1];
	    		
	    		jobD.setProductStatus(productstatus);
	    		jobD.setLotinfo(lotinfo);
	    		jobD.setPrintdate(printdate);
	    		jobD.setJobnum(jobnum);
	    		jobD.setCustomerid(customerid);
	    		if("4".equals(job.getStatus()))
	    		{
	    			InventoryDaoImpl invDao = new InventoryDaoImpl(dao);
	    			inv = invDao.getInventoryByJobIdAndTrayCode(job.getJobid(), traycode);
	    			if(inv != null && inv.size() > 0)
	    			{
	    				for(int i = 0; i < inv.size(); i++)
	    				{
	    					InventoryStorage inven = (InventoryStorage)inv.get(i);
	    					inven.setProductstatus(productstatus);
	    					inven.setProductdate(printdate);
	    					inven.setPnum(jobnum);
	    					inven.setLotinfo(lotinfo);
	    					inven.setSupplier(customerid);
	    				}
	    			}
	    		}
	        	
	        	Session session = dao.getSession();
	    		try {
	    			Transaction tx = session.beginTransaction();
	    			session.update(jobD);
	    			if(inv != null && inv.size() > 0)
	    			{
	    				for(int i = 0; i < inv.size(); i++)
	    				{
	    					session.save(inv.get(i));
	    				}
	    			}
	    			tx.commit();	
	    		} catch (HibernateException he) {
	    			strBackMsg = "����ʧ��!";
	    			Logger.error("������ⵥ����ϸ���޸���ҵ��_��������ʧ��"+he.getMessage());
	    		}
	    		finally
	    		{
	        		dao.closeSession(session);
	        	}
				Logger.error("�û�[" + strUserCode + "]�����ά��:jobid=[" +job.getJobid() +"]");
	    	}
			
			
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {
			Logger.error("�û�[" + strUserCode + "]��������==>RF������ҵʧ��:" + er.getMessage());
			request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

}
