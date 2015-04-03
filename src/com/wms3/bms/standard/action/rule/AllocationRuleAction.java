package com.wms3.bms.standard.action.rule;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.standard.business.rule.IAllocationRuleBus;
import com.wms3.bms.standard.business.rule.impl.AllocationRuleImpl;
import com.wms3.bms.standard.entity.rule.RuleAllocation;
import com.wms3.bms.standard.entity.rule.RuleAllocationDetail;

/**
 * ����:����������
 * @author 
 *
 */
public class AllocationRuleAction extends AbstractAction
{
	protected IAllocationRuleBus allocationRuleBus;
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String allocationid = CommonTools.getStrToGbk(request.getParameter("allocationid"));		//�������Id
		String allocationdetailid = CommonTools.getStrToGbk(request.getParameter("allocationdetailid"));	//���������ϸID
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�����ֿ�ID
		String descr = CommonTools.getStrToGbk(request.getParameter("descr"));				//�����������
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));

        allocationRuleBus = new AllocationRuleImpl(dao);
        List ls = null;
		try
		{
			
			if(flag != null && flag.equals("1")) //���������� ��ѯ�б�
			{
				strUrl = "/standard/jsp/rule/allocation/rule_allocation_list.jsp";
				
				ls = allocationRuleBus.getAllocationRuleQuery(warehouseid, descr);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2"))//���������� ��ѯ��ϸ�б�
			{
				strUrl = "/standard/jsp/rule/allocation/rule_allocation_detail.jsp";
				
				ls = allocationRuleBus.getAllocationDetailRuleById(allocationid);
				request.setAttribute("exResultList", ls);
				
			}else if(flag != null && flag.equals("3"))//���������� �޸Ĺ���ʱ��ȡ��Ϣ
			{
				strUrl = "/standard/jsp/rule/allocation/rule_allocation_update.jsp";
				
				RuleAllocation allocationRule = allocationRuleBus.getAllocationRuleById(allocationid);
				request.setAttribute("allocationRule", allocationRule); 
				
			}else if(flag != null && flag.equals("4"))//���������� �޸���ϸʱ��ȡ��Ϣ
			{
				strUrl = "/standard/jsp/rule/allocation/rule_allocation_detail_update.jsp";
				
				RuleAllocationDetail allocationDetailRule = allocationRuleBus.getPutDetailByDetailId(allocationdetailid);
				request.setAttribute("allocationDetailRule", allocationDetailRule); 
				
				RuleAllocation allocationRule = allocationRuleBus.getAllocationRuleById(allocationid);
				request.setAttribute("warehouseid", allocationRule.getWarehouseid());
				
			}else if(flag != null && flag.equals("5"))//���������� �޸�����˳λʱ��ȡ��Ϣ
			{
				strUrl = "/standard/jsp/rule/allocation/rule_allocation_sort.jsp";
				
				ls = allocationRuleBus.getAllocationDetailRuleById(allocationid);
				request.setAttribute("exResultList", ls);
				
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("�������==>�������==>��������ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:���ӷ������
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdd(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String descr = CommonTools.getStrToGb2312(request.getParameter("descr"));	//����
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�����ֿ�ID
		String remark = CommonTools.getStrToGb2312(request.getParameter("remark"));	//��ע
		
		String strUserName = (String)httpsession.getAttribute("userName");
		allocationRuleBus = new AllocationRuleImpl(dao);
		try
		{
        	//�������
			RuleAllocation allocationRule = new RuleAllocation();
			allocationRule.setDescr(descr);					//����
			allocationRule.setWarehouseid(warehouseid);		//�����ֿ�ID
			allocationRule.setRemark(remark);				//��ע

			allocationRuleBus.addAllocationRule(allocationRule);
			
			Logger.info("�û�:" + strUserName + "����˷������:" + descr);

			strUrl = "/standard/jsp/rule/allocation/rule_allocation_list.jsp";
			
			List ls = allocationRuleBus.getAllocationRuleQuery("", "");
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("�������==>�������==>���ӷ������ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�޸ķ������
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecEdit(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String allocationid = CommonTools.getStrToGbk(request.getParameter("allocationid"));		//�������Id
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�����ֿ�ID
		String descr = CommonTools.getStrToGb2312(request.getParameter("descr"));			//����
		String remark = CommonTools.getStrToGb2312(request.getParameter("remark"));			//��ע
		
		String strUserName = (String)httpsession.getAttribute("userName");
		allocationRuleBus = new AllocationRuleImpl(dao);
		try
		{
			if(allocationid != null && allocationid.length()>0)
			{
				RuleAllocation allocationRule = allocationRuleBus.getAllocationRuleById(allocationid);
				allocationRule.setDescr(descr);				//����
				allocationRule.setWarehouseid(warehouseid);	//�����ֿ�ID
				allocationRule.setRemark(remark);			//��ע
				
				allocationRuleBus.updateAllocationRule(allocationRule);
				Logger.info("�û�:" + strUserName + "�޸��˷������:" + allocationid);
			}
			
			strUrl = "/standard/jsp/rule/allocation/rule_allocation_list.jsp";
			
			List ls = allocationRuleBus.getAllocationRuleQuery("", "");
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("�������==>�������==>�޸ķ������ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:ɾ���������
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecDelete(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String ids = CommonTools.getStrToGbk(request.getParameter("ids"));
		String strUserName = (String)httpsession.getAttribute("userName");
		allocationRuleBus = new AllocationRuleImpl(dao);
		try
		{
			if(ids != null && !ids.equals(""))
			{
				String[] id = ids.split(",");
				for(int i=0; i<id.length; i++)
				{
					allocationRuleBus.deleteAllocationRule(id[i]);
					Logger.info("�û�:" + strUserName + "ɾ���˷������:" + id[i] + "�Լ���ϸ");
				}
			}
			
			strUrl = "/standard/jsp/rule/allocation/rule_allocation_list.jsp";
			
			List ls = allocationRuleBus.getAllocationRuleQuery("", "");
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("�������==>�������==>ɾ���������ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:���ӷ��������ϸ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAddDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
	    String allocationid = CommonTools.getStrToGbk(request.getParameter("allocationid"));  	//�������ID
	    String sort = CommonTools.getStrToGbk(request.getParameter("sort"));					//����˳λ
		String ruleconfigid = CommonTools.getStrToGbk(request.getParameter("ruleconfigid"));	//��������ID
	    String enableflag = CommonTools.getStrToGbk(request.getParameter("enableflag"));		//�Ƿ���Ч
	    
	    String clearloc_flag = CommonTools.getStrToGbk(request.getParameter("clearloc_flag"));  //�Ƿ����
	    String apart_flag = CommonTools.getStrToGbk(request.getParameter("apart_flag"));		//�Ƿ����
	    String over_flag = CommonTools.getStrToGbk(request.getParameter("over_flag"));    		//�Ƿ���λ��������
	    String auto_flag = CommonTools.getStrToGbk(request.getParameter("auto_flag"));    		//�Ƿ��Զ�������������
	    String bulkpick_flag = CommonTools.getStrToGbk(request.getParameter("bulkpick_flag"));  //�Ƿ�洢λ��ѡ
	    String part_flag = CommonTools.getStrToGbk(request.getParameter("part_flag"));    		//�Ƿ���
	    String unit_flag = CommonTools.getStrToGbk(request.getParameter("unit_flag"));    		//������λ
	    
	    String tozone = CommonTools.getStrToGbk(request.getParameter("tozone"));				//Ŀ�����
	    String tolocationid = CommonTools.getStrToGbk(request.getParameter("tolocationid"));	//Ŀ���λ
	    String toalleys = CommonTools.getStrToGbk(request.getParameter("toalleys"));			//Ŀ��������ɶ���
	    String part_allocation_flag = CommonTools.getStrToGbk(request.getParameter("part_allocation_flag"));//�Ƿ������ַ��� N-��,Y-��
	    
	    String remark = CommonTools.getStrToGbk(request.getParameter("remark"));    	//��ע
		
		String strUserName = (String)httpsession.getAttribute("userName");
		allocationRuleBus = new AllocationRuleImpl(dao);
		try
		{
        	//���������ϸ
			RuleAllocationDetail allocationDetailRule = new RuleAllocationDetail();
			
			allocationDetailRule.setAllocationid(allocationid) ;  	//�������ID
			allocationDetailRule.setSort(Integer.parseInt(sort));	//����˳λ
			allocationDetailRule.setRuleconfigid(ruleconfigid);		//��������ID
			allocationDetailRule.setEnableflag(enableflag);			//�Ƿ���Ч
		    
			allocationDetailRule.setClearloc_flag(clearloc_flag);	//�Ƿ����
			allocationDetailRule.setApart_flag(apart_flag);			//�Ƿ����
			allocationDetailRule.setOver_flag(over_flag);			//�Ƿ���λ��������
			allocationDetailRule.setAuto_flag(auto_flag);			//�Ƿ��Զ�������������
			allocationDetailRule.setBulkpick_flag(bulkpick_flag);	//�Ƿ�洢λ��ѡ
			allocationDetailRule.setPart_flag(part_flag);			//�Ƿ���
			allocationDetailRule.setUnit_flag(unit_flag);			//������λ
			
			allocationDetailRule.setTozone(tozone);					//Ŀ�����
			allocationDetailRule.setTolocationid(tolocationid);		//Ŀ���λ
			allocationDetailRule.setToalleys(toalleys);				//Ŀ��������ɶ���
		    allocationDetailRule.setPart_allocation_flag(part_allocation_flag);//�Ƿ������ַ��� N-��,Y-��
		    
		    allocationDetailRule.setRemark(remark);    				//��ע

			allocationRuleBus.addAllocationDetailRule(allocationDetailRule);
			
			Logger.info("�û�:" + strUserName + "����˷������:" + allocationid + "����ϸ");

			strUrl = "/standard/jsp/rule/allocation/rule_allocation_detail.jsp";
			
			List ls = allocationRuleBus.getAllocationDetailRuleById(allocationid);
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("�������==>�������==>���ӷ��������ϸʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�޸ķ��������ϸ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecEditDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
	    String allocationid = CommonTools.getStrToGbk(request.getParameter("allocationid"));  		//�������ID
	    String allocationdetailid = CommonTools.getStrToGbk(request.getParameter("allocationdetailid"));	//���������ϸID
		String ruleconfigid = CommonTools.getStrToGbk(request.getParameter("ruleconfigid"));	//��������ID
	    String enableflag = CommonTools.getStrToGbk(request.getParameter("enableflag"));		//�Ƿ���Ч
	    
	    String clearloc_flag = CommonTools.getStrToGbk(request.getParameter("clearloc_flag"));  //�Ƿ����
	    String apart_flag = CommonTools.getStrToGbk(request.getParameter("apart_flag"));		//�Ƿ����
	    String over_flag = CommonTools.getStrToGbk(request.getParameter("over_flag"));    		//�Ƿ���λ��������
	    String auto_flag = CommonTools.getStrToGbk(request.getParameter("auto_flag"));    		//�Ƿ��Զ�������������
	    String bulkpick_flag = CommonTools.getStrToGbk(request.getParameter("bulkpick_flag"));  //�Ƿ�洢λ��ѡ
	    String part_flag = CommonTools.getStrToGbk(request.getParameter("part_flag"));    		//�Ƿ���
	    String unit_flag = CommonTools.getStrToGbk(request.getParameter("unit_flag"));    		//������λ
	    
	    String tozone = CommonTools.getStrToGbk(request.getParameter("tozone"));				//Ŀ�����
	    String tolocationid = CommonTools.getStrToGbk(request.getParameter("tolocationid"));	//Ŀ���λ
	    String toalleys = CommonTools.getStrToGbk(request.getParameter("toalleys"));			//Ŀ��������ɶ���
	    String part_allocation_flag = CommonTools.getStrToGbk(request.getParameter("part_allocation_flag"));//�Ƿ������ַ��� N-��,Y-��
	    
	    String remark = CommonTools.getStrToGbk(request.getParameter("remark"));    	//��ע
		
		String strUserName = (String)httpsession.getAttribute("userName");
		allocationRuleBus = new AllocationRuleImpl(dao);
		try
		{
			RuleAllocationDetail allocationDetailRule = allocationRuleBus.getPutDetailByDetailId(allocationdetailid);
			allocationDetailRule.setRuleconfigid(ruleconfigid);		//��������ID
			allocationDetailRule.setEnableflag(enableflag);			//�Ƿ���Ч
		    
			allocationDetailRule.setClearloc_flag(clearloc_flag);	//�Ƿ����
			allocationDetailRule.setApart_flag(apart_flag);			//�Ƿ����
			allocationDetailRule.setOver_flag(over_flag);			//�Ƿ���λ��������
			allocationDetailRule.setAuto_flag(auto_flag);			//�Ƿ��Զ�������������
			allocationDetailRule.setBulkpick_flag(bulkpick_flag);	//�Ƿ�洢λ��ѡ
			allocationDetailRule.setPart_flag(part_flag);			//�Ƿ���
			allocationDetailRule.setUnit_flag(unit_flag);			//������λ
			
			allocationDetailRule.setTozone(tozone);					//Ŀ�����
			allocationDetailRule.setTolocationid(tolocationid);		//Ŀ���λ
			allocationDetailRule.setToalleys(toalleys);				//Ŀ��������ɶ���
		    allocationDetailRule.setPart_allocation_flag(part_allocation_flag);//�Ƿ������ַ��� N-��,Y-��
			
		    allocationDetailRule.setRemark(remark);    				//��ע
		    
		    allocationRuleBus.updateAllocationDetailRule(allocationDetailRule);
			
			Logger.info("�û�:" + strUserName + "�޸��˷��������ϸ:" + allocationdetailid);

			strUrl = "/standard/jsp/rule/allocation/rule_allocation_detail.jsp";
			
			List ls = allocationRuleBus.getAllocationDetailRuleById(allocationid);
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("�������==>�������==>�޸ķ��������ϸʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:ɾ�����������ϸ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecDeleteDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String ids = CommonTools.getStrToGbk(request.getParameter("ids"));
		String strUserName = (String)httpsession.getAttribute("userName");
		allocationRuleBus = new AllocationRuleImpl(dao);
		try
		{
			String allocationid = "";	 //�������ID
			if(ids != null && !ids.equals(""))
			{
				String[] id = ids.split(",");
				for(int i=0; i<id.length; i++){
				
					String[] arr = id[i].split("\\|");		//������ϸID +"|" + �������ID 
					allocationid = arr[1];
					
					allocationRuleBus.deleteAllocationDetailRule(arr[0]);
					Logger.info("�û�:" + strUserName + "ɾ���˷��������ϸ:" + arr[0]);
				}
			}
			
			strUrl = "/standard/jsp/rule/allocation/rule_allocation_detail.jsp";
			
			List ls = allocationRuleBus.getAllocationDetailRuleById(allocationid);
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("�������==>�������==>ɾ�����������ϸʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�޸ķ��������ϸ������˳λ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecEditSorts(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String allocationid = CommonTools.getStrToGbk(request.getParameter("allocationid"));  //�������ID
		String detailids = CommonTools.getStrToGbk(request.getParameter("detailids"));	//���������ϸID
		String sorts = CommonTools.getStrToGbk(request.getParameter("sorts"));			//����˳λ
		String strUserName = (String)httpsession.getAttribute("userName");
		allocationRuleBus = new AllocationRuleImpl(dao);
		try
		{
			allocationRuleBus.updateAllocationDetailRuleSorts(detailids, sorts);
			Logger.info("�û�:" + strUserName + "�޸ķ������" + allocationid +"������˳λ");

			
			strUrl = "/standard/jsp/rule/allocation/rule_allocation_detail.jsp";
			
			List ls = allocationRuleBus.getAllocationDetailRuleById(allocationid);
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("�������==>�������==>�޸ķ��������ϸ������˳λʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}