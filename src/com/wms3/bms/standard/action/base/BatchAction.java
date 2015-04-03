package com.wms3.bms.standard.action.base;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.standard.business.base.IBatchBus;
import com.wms3.bms.standard.business.base.IBatchmeanBus;
import com.wms3.bms.standard.business.base.IBatchruleBus;
import com.wms3.bms.standard.business.base.impl.BatchBusImpl;
import com.wms3.bms.standard.business.base.impl.BatchmeanBusImpl;
import com.wms3.bms.standard.business.base.impl.BatchruleBusImpl;
import com.wms3.bms.standard.entity.base.BaseBatch;
import com.wms3.bms.standard.entity.base.BaseBatchmean;
import com.wms3.bms.standard.entity.base.BaseBatchrule;

/**
 * ����:���ι���
 * @author fangjie
 *
 */
public class BatchAction extends AbstractAction
{
	protected IBatchBus batchBus;
	protected IBatchmeanBus batchBusmean;
	protected IBatchruleBus batchBusrule;
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String id = CommonTools.getStrToGbk(request.getParameter("id"));		//ID
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		batchBus = new BatchBusImpl(dao);
		batchBusmean = new BatchmeanBusImpl(dao);
		batchBusrule = new BatchruleBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //���ι��� ��ѯ�����б�
			{
				strUrl = "/standard/jsp/base/batch/base_batch_left.jsp";
				List ls = batchBus.getBatchList();
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2")) //���ι��� �޸�ʱ��ȡ������Ϣ
			{
				strUrl = "/standard/jsp/base/batch/base_batch_update.jsp";
				BaseBatch batch = batchBus.getBatchById(id);
				request.setAttribute("batch", batch);
					
			}else if(flag != null && flag.equals("3")) //���ι��� ��ѯ���������б�
			{
				strUrl = "/standard/jsp/base/batch/base_batch_mean.jsp";
				List ls = batchBusmean.getBatchmeanByBatchId(id);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("4")) //���ι��� �޸�ʱ��ȡ����������Ϣ
			{
				strUrl = "/standard/jsp/base/batch/base_batch_mean_update.jsp";
				BaseBatchmean batchmean = batchBusmean.getBatchmeanById(id);
				request.setAttribute("batchmean", batchmean);
					
			}else if(flag != null && flag.equals("5")) //���ι��� ��ѯ���ι����б�
			{
				strUrl = "/standard/jsp/base/batch/base_batch_rule.jsp";
				List ls = batchBusrule.getBatchruleByBatchId(id);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("6")) //���ι��� �޸�ʱ��ȡ���ι�����Ϣ
			{
				strUrl = "/standard/jsp/base/batch/base_batch_rule_update.jsp";
				BaseBatchrule batchrule = batchBusrule.getBatchruleById(id);
				request.setAttribute("batchrule", batchrule);
					
			}		
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>���ι���==>���β�ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:��������
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAddBatch(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String batchname = CommonTools.getStrToGbk(request.getParameter("batch_name"));	//��������
		String batchlength = CommonTools.getStrToGbk(request.getParameter("length"));	//������
		String useflag = CommonTools.getStrToGbk(request.getParameter("use_flag"));		//�Ƿ����� Y:�� N.��
		
		String strUserName = (String)httpsession.getAttribute("userName");
		
		batchBus = new BatchBusImpl(dao);
		try
		{
        	//����������Ϣ
			BaseBatch batch = new BaseBatch(batchname, new Integer(batchlength), useflag);		 		
			batchBus.addBatch(batch);
			
			Logger.info("�û�" + strUserName + "���������" + batchname);

			strUrl = "/standard/jsp/base/batch/base_batch_left.jsp";
			List ls = batchBus.getBatchList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>���ι���==>��������ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:������������
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAddBatchmean(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String batchid = CommonTools.getStrToGbk(request.getParameter("batch_id"));	//����ID
		String mean = CommonTools.getStrToGbk(request.getParameter("mean"));			//����
		String startpos = CommonTools.getStrToGbk(request.getParameter("start_pos"));//��ʼλ��
		String endpos = CommonTools.getStrToGbk(request.getParameter("end_pos"));	//����λ��
		
		String strUserName = (String)httpsession.getAttribute("userName");
		
		batchBusmean = new BatchmeanBusImpl(dao);
		try
		{
        	//��������������Ϣ
			BaseBatchmean batchmean = new BaseBatchmean(batchid, mean, new Integer(startpos), new Integer(endpos));		 		
			batchBusmean.addBatchmean(batchmean);
			
			Logger.info("�û�" + strUserName + "���������:" + batchid + "�����壺" + mean);

			strUrl = "/standard/jsp/base/batch/base_batch_mean.jsp";
			List ls = batchBusmean.getBatchmeanByBatchId(batchid);
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>���ι���==>������������ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�������ι���
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAddBatchrule(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String batchid = CommonTools.getStrToGbk(request.getParameter("batch_id"));		//����ID
		String rule_name = CommonTools.getStrToGbk(request.getParameter("rule_name"));	//��������
		String startpos = CommonTools.getStrToGbk(request.getParameter("start_pos"));	//��ʼλ��
		String endpos = CommonTools.getStrToGbk(request.getParameter("end_pos"));		//����λ��
		String ruleinfo = CommonTools.getStrToGbk(request.getParameter("rule_info"));	//����
		
		String strUserName = (String)httpsession.getAttribute("userName");
		
		batchBusrule = new BatchruleBusImpl(dao);
		try
		{
        	//�������ι�����Ϣ
			BaseBatchrule batchrule = new BaseBatchrule(batchid, rule_name, ruleinfo, new Integer(startpos), new Integer(endpos));		 		
			batchBusrule.addBatchrule(batchrule);
			
			Logger.info("�û�" + strUserName + "���������:" + batchid + "�Ĺ���" + rule_name);

			strUrl = "/standard/jsp/base/batch/base_batch_rule.jsp";
			List ls = batchBusrule.getBatchruleByBatchId(batchid);
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>���ι���==>�������ι���ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�޸�����
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecEditBatch(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String batchid = CommonTools.getStrToGbk(request.getParameter("batch_id"));		//����ID
		String batch_name = CommonTools.getStrToGbk(request.getParameter("batch_name"));//��������
		String length = CommonTools.getStrToGbk(request.getParameter("length"));		//����
		String useflag = CommonTools.getStrToGbk(request.getParameter("use_flag"));		//�Ƿ����� Y:�� N.��

		String strUserName = (String)httpsession.getAttribute("userName");
		
		batchBus = new BatchBusImpl(dao);
		try
		{
			if(batchid!=null && !batchid.equals(""))
			{
				BaseBatch batch = batchBus.getBatchById(batchid);
				batch.setUseflag(useflag);	// �Ƿ����� Y:�� N.��
				batch.setBatchname(batch_name);
				if(length!=null){
						try {
					        Integer.parseInt(length);
						} catch (NumberFormatException ex) {
							length="0";
						}
				}
				batch.setBatchlength(Integer.parseInt(length));
				batchBus.updateBatch(batch);
				Logger.info("�û�" + strUserName + "�޸�������" + batchid);
			}
			
			strUrl = "/standard/jsp/base/batch/base_batch_left.jsp";
			List ls = batchBus.getBatchList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>���ι���==>�޸�����ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:ɾ������
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecDeleteBatch(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String id = CommonTools.getStrToGbk(request.getParameter("id"));
		String strUserName = (String)httpsession.getAttribute("userName");
		
		batchBus = new BatchBusImpl(dao);
		try
		{
			//ɾ��
			batchBus.deleteBatch(id);	
			Logger.info("�û�" + strUserName + "ɾ��������" + id);
			
			strUrl = "/standard/jsp/base/batch/base_batch_left.jsp";
			List ls = batchBus.getBatchList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>���ι���==>����ɾ��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:ɾ����������
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecDeleteBatchmean(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String ids = CommonTools.getStrToGbk(request.getParameter("ids"));
		String batchid = CommonTools.getStrToGbk(request.getParameter("batchid"));		//����id
		String strUserName = (String)httpsession.getAttribute("userName");
		
		batchBusmean = new BatchmeanBusImpl(dao);
		try
		{
			if(ids != null && !ids.equals(""))
			{
				String[] id = ids.split(",");
				for(int i=0; i<id.length; i++)
				{
					//ɾ��
					batchBusmean.deleteBatchmean(id[i]);	
					Logger.info("�û�" + strUserName + "ɾ������������" + id[i]);
				}
			}
			
			strUrl = "/standard/jsp/base/batch/base_batch_mean.jsp";
			List ls = batchBusmean.getBatchmeanByBatchId(batchid);
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>���ι���==>��������ɾ��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:ɾ�����ι���
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecDeleteBatchrule(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String ids = CommonTools.getStrToGbk(request.getParameter("ids"));
		String batchid = CommonTools.getStrToGbk(request.getParameter("batchid"));		//����id
		String strUserName = (String)httpsession.getAttribute("userName");
		
		batchBusrule = new BatchruleBusImpl(dao);
		try
		{
			if(ids != null && !ids.equals(""))
			{
				String[] id = ids.split(",");
				for(int i=0; i<id.length; i++)
				{
					//ɾ��
					batchBusrule.deleteBatchrule(id[i]);	
					Logger.info("�û�" + strUserName + "ɾ�������ι���" + id[i]);
				}
			}
			
			strUrl = "/standard/jsp/base/batch/base_batch_rule.jsp";
			List ls = batchBusrule.getBatchruleByBatchId(batchid);
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>���ι���==>���ι���ɾ��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
