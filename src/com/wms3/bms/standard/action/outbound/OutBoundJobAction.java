package com.wms3.bms.standard.action.outbound;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.pagination.PagingTool;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.standard.business.outbound.IOutBoundBus;
import com.wms3.bms.standard.business.outbound.IOutBoundJobBus;
import com.wms3.bms.standard.business.outbound.impl.OutBoundBusImpl;
import com.wms3.bms.standard.business.outbound.impl.OutBoundJobBusImpl;
import com.wms3.bms.standard.dao.job.IJobDao;
import com.wms3.bms.standard.dao.job.impl.JobDaoImpl;
import com.wms3.bms.standard.entity.base.BaseLotSet;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;

/**
 * ����: ������ҵ
 * @author yao
 *
 */
public class OutBoundJobAction extends AbstractAction
{
	protected IOutBoundJobBus outBoundJobBus;
	protected List<BaseLotSet> lsLot;
	protected int maxLine = 6;
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//����
		String Virtualwhid = CommonTools.getStrToGbk(request.getParameter("Virtualwhid"));  //�߼�����id
		String productid = CommonTools.getStrToGbk(request.getParameter("productid"));		//Ʒ��
		String customerid = CommonTools.getStrToGbk(request.getParameter("customerid"));	//�ͻ�
		String jobid = CommonTools.getStrToGbk(request.getParameter("jobid"));				//��ҵ��
		String indate = CommonTools.getStrToGbk(request.getParameter("indate"));			//��ҵ����
		String shiftid = CommonTools.getStrToGbk(request.getParameter("shift_id"));			//��� 
		String status = CommonTools.getStrToGbk(request.getParameter("status"));			//��ҵ״̬
		String taskid = CommonTools.getStrToGbk(request.getParameter("taskid")); 			//��������ID
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));		//��������
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));				//��ʶ
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		
        outBoundJobBus = new OutBoundJobBusImpl(dao);
		try{
		
			if(flag != null && flag.equals("1")){ 		//������ҵ���� ��ѯ��ҵ�б�
			
				strUrl = "/standard/jsp/outbound/job/outbound_job_list.jsp";
				
				PagingTool pt = outBoundJobBus.getOutboundJobs(warehouseid, Virtualwhid, whAreaId, productid, customerid, indate, shiftid, jobid, status, 
						taskid, traycode, strUrl, maxLine);
				
				List ls = pt.getLsResult();
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
					
			}else if(flag != null && flag.equals("2")){	//������ҵ���� ��ѯ��ҵ��ϸ�б�

				strUrl = "/standard/jsp/outbound/job/outbound_job_detail.jsp";
				
				List ls = outBoundJobBus.getJobDetails(jobid);
				request.setAttribute("exResultList", ls);
				
			}else if(flag != null && flag.equals("3")){ 		//������ѯ�б�
			
				strUrl = "/standard/jsp/outbound/query/outbound_search_list.jsp";
				
				PagingTool pt = outBoundJobBus.getOutboundJobs(warehouseid, Virtualwhid, whAreaId, productid, customerid, indate, shiftid, jobid, status, 
						taskid, traycode, strUrl, maxLine);
				
				List ls = pt.getLsResult();
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
					
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("�������==>��ҵ����==>������ҵ��ѯʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�ֶ���ɳ�����ҵ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecFinish(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String jobids = CommonTools.getStrToGbk(request.getParameter("jobids"));		//��ҵID
		String strUserCode = (String)httpsession.getAttribute("userCode");
		
		outBoundJobBus = new OutBoundJobBusImpl(dao);	
		try{
			
			String strBackMsg = outBoundJobBus.finishJobs(jobids, strUserCode);
			
			strUrl = "/standard/jsp/outbound/job/outbound_job_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
			
			Logger.error("�û�["+strUserCode+"]���������==>��ҵ����==>�ֶ���ɳ�����ҵʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}
	/**
	 * ����:�ֶ���ɳ����ݴ���ҵ��
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecFinishToTem(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String jobid = CommonTools.getStrToGbk(request.getParameter("jobids"));		//��ҵID
		String strUserCode = (String)httpsession.getAttribute("userCode");
		
		IOutBoundBus outBoundBus = new OutBoundBusImpl(dao);
		IJobDao jobDao = new JobDaoImpl(dao);
		try{
			List<InoutJobdetail> jobDetail = jobDao.getJobDetailByJobId(jobid);
			InoutJobdetail jobMX = null;
		    if(jobDetail!=null && jobDetail.size()>0){
		    	jobMX = (InoutJobdetail)jobDetail.get(0);
		    }
			String strBackMsg = outBoundBus.updateInventoryAndJob(jobid, jobMX.getJobdetailid(), jobMX.getInventoryid());
			strUrl = "/standard/jsp/outbound/job/outbound_job_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
			
			Logger.error("�û�["+strUserCode+"]���������==>��ҵ����==>�ֶ���ɳ�����ҵʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}
	
	/**
	 * ����:���ϳ�����ҵ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecCancel(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String jobids = CommonTools.getStrToGbk(request.getParameter("jobids"));		//��ҵID
		String strUserCode = (String)httpsession.getAttribute("userCode");
		
		outBoundJobBus = new OutBoundJobBusImpl(dao);	
		try{
			
			String strBackMsg = outBoundJobBus.cancelJobs(jobids, strUserCode);
			
			strUrl = "/standard/jsp/outbound/job/outbound_job_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
			
			Logger.error("�û�["+strUserCode+"]���������==>��ҵ����==>���ϳ�����ҵʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		return null;
	}
	
	/**
	 * ����:�趨���ȼ���
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecUpdPriority(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String jobids = CommonTools.getStrToGbk(request.getParameter("jobids"));		//��ҵIDS
		String priority = CommonTools.getStrToGb2312(request.getParameter("priority"));	//���ȼ�
		String strUserCode = (String)httpsession.getAttribute("userCode");
		
		outBoundJobBus = new OutBoundJobBusImpl(dao);	
		try{
		
			String strBackMsg = outBoundJobBus.updJobStatus(jobids, priority, strUserCode);
			
			strUrl = "/standard/jsp/outbound/job/outbound_job_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
			
		}catch(Exception er){
		
			Logger.error("�û�["+strUserCode+"]���������==>��ҵ����==>�趨���ȼ���ʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�����ѯ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecSearchJob(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//����
		String alleyId = CommonTools.getStrToGbk(request.getParameter("alleyId"));			//���
		String cargospaceid = CommonTools.getStrToGbk(request.getParameter("cargospaceid"));//��λ

		String onlinetype = CommonTools.getStrToGbk(request.getParameter("onlinetype"));	//����ģʽ
		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from"));	//��ҵ����
		String indate_to = CommonTools.getStrToGbk(request.getParameter("indate_to"));		//��ҵ����
		String shiftid = CommonTools.getStrToGbk(request.getParameter("shift_id"));			//��� 
		
		String productid = CommonTools.getStrToGbk(request.getParameter("productid"));		//Ʒ��
		String customerid = CommonTools.getStrToGbk(request.getParameter("customerid"));	//����
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));		//��������
		
/*		String lotid = CommonTools.getStrToGbk(request.getParameter("lotid"));              //����ID
		String lotatt1 = CommonTools.getStrToGbk(request.getParameter("lotatt1"));			//��������1
		String lotatt2 = CommonTools.getStrToGbk(request.getParameter("lotatt2"));			//��������2
		String lotatt3 = CommonTools.getStrToGbk(request.getParameter("lotatt3"));			//��������3
		String lotatt4 = CommonTools.getStrToGbk(request.getParameter("lotatt4"));			//��������4
		String lotatt5 = CommonTools.getStrToGbk(request.getParameter("lotatt5"));			//��������5
		String lotatt6 = CommonTools.getStrToGbk(request.getParameter("lotatt6"));			//��������6
		String lotatt7 = CommonTools.getStrToGbk(request.getParameter("lotatt7"));			//��������7
		String lotatt8 = CommonTools.getStrToGbk(request.getParameter("lotatt8"));			//��������8
		String lotatt9 = CommonTools.getStrToGbk(request.getParameter("lotatt9"));			//��������9
		String lotatt10 = CommonTools.getStrToGbk(request.getParameter("lotatt10"));		//��������10
		String lotatt11 = CommonTools.getStrToGbk(request.getParameter("lotatt11"));		//��������11
		String lotatt12 = CommonTools.getStrToGbk(request.getParameter("lotatt12"));		//��������12
		*/
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		
        outBoundJobBus = new OutBoundJobBusImpl(dao);
		try{
			
			strUrl = "/standard/jsp/outbound/query/outbound_search_list.jsp";
			
			PagingTool pt = outBoundJobBus.getOutboundJobDetails(warehouseid, whAreaId, alleyId, cargospaceid, 
					onlinetype, indate_from, indate_to, shiftid, productid, customerid, traycode, 
					"", "", "", "", "", "", "", "", "", "", "", "", "", strUrl, maxLine);
			
/*			PagingTool pt = outBoundJobBus.getOutboundJobDetails(warehouseid, whAreaId, alleyId, cargospaceid, 
					onlinetype, indate_from, indate_to, shiftid, productid, customerid, traycode, 
					lotid, lotatt1, lotatt2, lotatt3, lotatt4, lotatt5, lotatt6, lotatt7, lotatt8, lotatt9, lotatt10, 
					lotatt11, lotatt12, strUrl, maxLine);*/
			
			List ls = pt.getLsResult();
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);

			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("�������==>�����ѯ==>������ҵ��ѯʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
