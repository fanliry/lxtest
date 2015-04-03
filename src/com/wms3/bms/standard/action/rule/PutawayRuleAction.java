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
import com.wms3.bms.standard.business.rule.IPutawayRuleBus;
import com.wms3.bms.standard.business.rule.impl.PutawayRuleImpl;
import com.wms3.bms.standard.entity.rule.RulePutaway;
import com.wms3.bms.standard.entity.rule.RulePutawayDetail;

/**
 * ����:�ϼܹ������
 * @author 
 *
 */
public class PutawayRuleAction extends AbstractAction
{
	protected IPutawayRuleBus putawayRuleBus;
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String putawayid = CommonTools.getStrToGbk(request.getParameter("putawayid"));		//�ϼܹ���Id
		String putawaydetailid = CommonTools.getStrToGbk(request.getParameter("putawaydetailid"));	//�ϼܹ�����ϸID
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�����ֿ�ID
		String descr = CommonTools.getStrToGbk(request.getParameter("descr"));				//�ϼܹ�������
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));

        putawayRuleBus = new PutawayRuleImpl(dao);
        List ls = null;
		try
		{
			
			if(flag != null && flag.equals("1")) //�ϼܹ������ ��ѯ�б�
			{
				strUrl = "/standard/jsp/rule/putaway/rule_putaway_list.jsp";
				
				ls = putawayRuleBus.getPutawayRuleQuery(warehouseid, descr);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2"))//�ϼܹ������ ��ѯ��ϸ�б�
			{
				strUrl = "/standard/jsp/rule/putaway/rule_putaway_detail.jsp";
				
				ls = putawayRuleBus.getPutawayDetailRuleById(putawayid);
				request.setAttribute("exResultList", ls);
				
			}else if(flag != null && flag.equals("3"))//�ϼܹ������ �޸Ĺ���ʱ��ȡ��Ϣ
			{
				strUrl = "/standard/jsp/rule/putaway/rule_putaway_update.jsp";
				
				RulePutaway putawayRule = putawayRuleBus.getPutawayRuleById(putawayid);
				request.setAttribute("putawayRule", putawayRule); 
				
			}else if(flag != null && flag.equals("4"))//�ϼܹ������ �޸���ϸʱ��ȡ��Ϣ
			{
				strUrl = "/standard/jsp/rule/putaway/rule_putaway_detail_update.jsp";
				
				RulePutawayDetail putawayDetailRule = putawayRuleBus.getPutDetailByDetailId(putawaydetailid);
				request.setAttribute("putawayDetailRule", putawayDetailRule); 
				
				RulePutaway putawayRule = putawayRuleBus.getPutawayRuleById(putawayDetailRule.getPutawayid());
				request.setAttribute("warehouseid", putawayRule.getWarehouseid());
				
			}else if(flag != null && flag.equals("5"))//�ϼܹ������ �޸�����˳λʱ��ȡ��Ϣ
			{
				strUrl = "/standard/jsp/rule/putaway/rule_putaway_sort.jsp";
				
				ls = putawayRuleBus.getPutawayDetailRuleById(putawayid);
				request.setAttribute("exResultList", ls);
				
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("�������==>�ϼܹ���==>�ϼܹ����ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�����ϼܹ���
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
		putawayRuleBus = new PutawayRuleImpl(dao);
		try
		{
        	//�ϼܹ���
			RulePutaway putawayRule = new RulePutaway();
			putawayRule.setDescr(descr);					//����
			putawayRule.setWarehouseid(warehouseid);		//�����ֿ�ID
			putawayRule.setRemark(remark);					//��ע

			putawayRuleBus.addPutawayRule(putawayRule);
			
			Logger.info("�û�:" + strUserName + "������ϼܹ���:" + descr);

			strUrl = "/standard/jsp/rule/putaway/rule_putaway_list.jsp";
			
			List ls = putawayRuleBus.getPutawayRuleQuery("", "");
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("�������==>�ϼܹ���==>�����ϼܹ���ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�޸��ϼܹ���
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
		
		String putawayid = CommonTools.getStrToGbk(request.getParameter("putawayid"));		//�ϼܹ���Id
		String descr = CommonTools.getStrToGb2312(request.getParameter("descr"));			//����
		String remark = CommonTools.getStrToGb2312(request.getParameter("remark"));			//��ע
		
		String strUserName = (String)httpsession.getAttribute("userName");
		putawayRuleBus = new PutawayRuleImpl(dao);
		try
		{
			if(putawayid != null && putawayid.length()>0)
			{
				RulePutaway putawayRule = putawayRuleBus.getPutawayRuleById(putawayid);
				putawayRule.setDescr(descr);				//����
				putawayRule.setRemark(remark);				//��ע
				
				putawayRuleBus.updatePutawayRule(putawayRule);
				Logger.info("�û�:" + strUserName + "�޸����ϼܹ���:" + putawayid);
			}
			
			strUrl = "/standard/jsp/rule/putaway/rule_putaway_list.jsp";
			
			List ls = putawayRuleBus.getPutawayRuleQuery("", "");
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("�������==>�ϼܹ���==>�޸��ϼܹ���ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:ɾ���ϼܹ���
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
		putawayRuleBus = new PutawayRuleImpl(dao);
		try
		{
			if(ids != null && !ids.equals(""))
			{
				String[] id = ids.split(",");
				for(int i=0; i<id.length; i++)
				{	
					putawayRuleBus.deletePutawayRule(id[i]);
					Logger.info("�û�:" + strUserName + "ɾ�����ϼܹ���:" + id[i]);
				}
			}
			
			strUrl = "/standard/jsp/rule/putaway/rule_putaway_list.jsp";
			
			List ls = putawayRuleBus.getPutawayRuleQuery("", "");
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("�������==>�ϼܹ���==>ɾ���ϼܹ���ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�����ϼܹ�����ϸ
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
		
	    String putawayid = CommonTools.getStrToGbk(request.getParameter("putawayid"));  		//�ϼܹ���ID
	    String sort = CommonTools.getStrToGbk(request.getParameter("sort"));					//����˳λ
		String ruleconfigid = CommonTools.getStrToGbk(request.getParameter("ruleconfigid"));	//��������ID
	    String enableflag = CommonTools.getStrToGbk(request.getParameter("enableflag"));		//�Ƿ���Ч
	    
	    String tozone = CommonTools.getStrToGbk(request.getParameter("tozone"));				//Ŀ�����
	    String tolocationid = CommonTools.getStrToGbk(request.getParameter("tolocationid"));	//Ŀ���λ
	    
	    String loc_restrict = CommonTools.getStrToGbk(request.getParameter("loc_restrict"));	//��λ����
	    
	    String loc_usage1 = CommonTools.getStrToGbk(request.getParameter("loc_usage1"));   			//��λʹ��1
	    String loc_usage2 = CommonTools.getStrToGbk(request.getParameter("loc_usage2"));   			//��λʹ��2
	    String loc_usage3 = CommonTools.getStrToGbk(request.getParameter("loc_usage3"));   			//��λʹ��3
	    String loc_usage4 = CommonTools.getStrToGbk(request.getParameter("loc_usage4"));   			//��λʹ��4
	    String loc_usage5 = CommonTools.getStrToGbk(request.getParameter("loc_usage5"));   			//��λʹ��5
	    
	    String loc_handle1 = CommonTools.getStrToGbk(request.getParameter("loc_handle1"));   		//�洢����1
	    String loc_handle2 = CommonTools.getStrToGbk(request.getParameter("loc_handle2"));   		//�洢����2
	    String loc_handle3 = CommonTools.getStrToGbk(request.getParameter("loc_handle3"));   		//�洢����3
	    String loc_handle4 = CommonTools.getStrToGbk(request.getParameter("loc_handle4"));   		//�洢����4
	    String loc_handle5 = CommonTools.getStrToGbk(request.getParameter("loc_handle5"));   		//�洢����5
	    
	    String lotid = CommonTools.getStrToGbk(request.getParameter("lotid"));			//����ID
	    String lotatt1 = CommonTools.getStrToGbk(request.getParameter("lotatt1"));		//��������ֵ1
	    String lotatt2 = CommonTools.getStrToGbk(request.getParameter("lotatt2"));		//��������ֵ2
	    String lotatt3 = CommonTools.getStrToGbk(request.getParameter("lotatt3"));		//��������ֵ3
	    String lotatt4 = CommonTools.getStrToGbk(request.getParameter("lotatt4"));		//��������ֵ4
	    String lotatt5 = CommonTools.getStrToGbk(request.getParameter("lotatt5"));		//��������ֵ5
	    String lotatt6 = CommonTools.getStrToGbk(request.getParameter("lotatt6"));		//��������ֵ6
	    String lotatt7 = CommonTools.getStrToGbk(request.getParameter("lotatt7"));		//��������ֵ7
	    String lotatt8 = CommonTools.getStrToGbk(request.getParameter("lotatt8"));		//��������ֵ8
	    String lotatt9 = CommonTools.getStrToGbk(request.getParameter("lotatt9"));		//��������ֵ9
	    String lotatt10 = CommonTools.getStrToGbk(request.getParameter("lotatt10"));	//��������ֵ10
	    String lotatt11 = CommonTools.getStrToGbk(request.getParameter("lotatt11"));	//��������ֵ11
	    String lotatt12 = CommonTools.getStrToGbk(request.getParameter("lotatt12"));	//��������ֵ12
	    
	    String remark = CommonTools.getStrToGbk(request.getParameter("remark"));    	//��ע
		
		String strUserName = (String)httpsession.getAttribute("userName");
		putawayRuleBus = new PutawayRuleImpl(dao);
		try
		{
        	//�ϼܹ�����ϸ
			RulePutawayDetail putawayDetailRule = new RulePutawayDetail();
			putawayDetailRule.setPutawayid(putawayid) ;  			//�ϼܹ���ID
			putawayDetailRule.setSort(Integer.parseInt(sort));		//����˳λ
			putawayDetailRule.setRuleconfigid(ruleconfigid);		//��������ID
			putawayDetailRule.setEnableflag(enableflag);			//�Ƿ���Ч
		    
			putawayDetailRule.setTozone(tozone);					//Ŀ�����
			putawayDetailRule.setTolocationid(tolocationid);		//Ŀ���λ
		    
			putawayDetailRule.setLoc_restrict(loc_restrict);    	//��λ����
		    
			putawayDetailRule.setLoc_usage1(loc_usage1);   			//��λʹ��1
			putawayDetailRule.setLoc_usage2(loc_usage2);   			//��λʹ��2
			putawayDetailRule.setLoc_usage3(loc_usage3);   			//��λʹ��3
			putawayDetailRule.setLoc_usage4(loc_usage4);   			//��λʹ��4
			putawayDetailRule.setLoc_usage5(loc_usage5);   			//��λʹ��5
		    
			putawayDetailRule.setLoc_handle1(loc_handle1);   		//�洢����1
			putawayDetailRule.setLoc_handle2(loc_handle2);   		//�洢����2
			putawayDetailRule.setLoc_handle3(loc_handle3);   		//�洢����3
			putawayDetailRule.setLoc_handle4(loc_handle4);   		//�洢����4
			putawayDetailRule.setLoc_handle5(loc_handle5);   		//�洢����5

		    putawayDetailRule.setLotid(lotid);			//��������ֵ1	
	    	putawayDetailRule.setLotatt1(lotatt1);		//��������ֵ1
	    	putawayDetailRule.setLotatt2(lotatt2);		//��������ֵ2
	    	putawayDetailRule.setLotatt3(lotatt3);		//��������ֵ3
	    	putawayDetailRule.setLotatt4(lotatt4);		//��������ֵ4
	    	putawayDetailRule.setLotatt5(lotatt5);		//��������ֵ5
	    	putawayDetailRule.setLotatt6(lotatt6);		//��������ֵ6
	    	putawayDetailRule.setLotatt7(lotatt7);		//��������ֵ7
	    	putawayDetailRule.setLotatt8(lotatt8);		//��������ֵ8
	    	putawayDetailRule.setLotatt9(lotatt9);		//��������ֵ9
	    	putawayDetailRule.setLotatt10(lotatt10);	//��������ֵ10
	    	putawayDetailRule.setLotatt11(lotatt11);	//��������ֵ11
	    	putawayDetailRule.setLotatt12(lotatt12);	//��������ֵ12
		    
		    putawayDetailRule.setRemark(remark);    	//��ע

			putawayRuleBus.addPutawayDetailRule(putawayDetailRule);
			
			Logger.info("�û�:" + strUserName + "������ϼܹ���:" + putawayid + "����ϸ");

			strUrl = "/standard/jsp/rule/putaway/rule_putaway_detail.jsp";
			
			List ls = putawayRuleBus.getPutawayDetailRuleById(putawayid);
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("�������==>�ϼܹ���==>�����ϼܹ�����ϸʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�޸��ϼܹ�����ϸ
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
		
	    String putawayid = CommonTools.getStrToGbk(request.getParameter("putawayid"));  		//�ϼܹ���ID
	    String putawaydetailid = CommonTools.getStrToGbk(request.getParameter("putawaydetailid"));	//�ϼܹ�����ϸID
		String ruleconfigid = CommonTools.getStrToGbk(request.getParameter("ruleconfigid"));	//��������ID
	    String enableflag = CommonTools.getStrToGbk(request.getParameter("enableflag"));		//�Ƿ���Ч
	    
	    String tozone = CommonTools.getStrToGbk(request.getParameter("tozone"));				//Ŀ�����
	    String tolocationid = CommonTools.getStrToGbk(request.getParameter("tolocationid"));	//Ŀ���λ
	    
	    String loc_restrict = CommonTools.getStrToGbk(request.getParameter("loc_restrict"));	//��λ����
	    
	    String loc_usage1 = CommonTools.getStrToGbk(request.getParameter("loc_usage1"));   			//��λʹ��1
	    String loc_usage2 = CommonTools.getStrToGbk(request.getParameter("loc_usage2"));   			//��λʹ��2
	    String loc_usage3 = CommonTools.getStrToGbk(request.getParameter("loc_usage3"));   			//��λʹ��3
	    String loc_usage4 = CommonTools.getStrToGbk(request.getParameter("loc_usage4"));   			//��λʹ��4
	    String loc_usage5 = CommonTools.getStrToGbk(request.getParameter("loc_usage5"));   			//��λʹ��5
	    
	    String loc_handle1 = CommonTools.getStrToGbk(request.getParameter("loc_handle1"));   		//�洢����1
	    String loc_handle2 = CommonTools.getStrToGbk(request.getParameter("loc_handle2"));   		//�洢����2
	    String loc_handle3 = CommonTools.getStrToGbk(request.getParameter("loc_handle3"));   		//�洢����3
	    String loc_handle4 = CommonTools.getStrToGbk(request.getParameter("loc_handle4"));   		//�洢����4
	    String loc_handle5 = CommonTools.getStrToGbk(request.getParameter("loc_handle5"));   		//�洢����5
	    
	    String lotid = CommonTools.getStrToGbk(request.getParameter("lotid"));			//����ID
	    String lotatt1 = CommonTools.getStrToGbk(request.getParameter("lotatt1"));		//��������ֵ1
	    String lotatt2 = CommonTools.getStrToGbk(request.getParameter("lotatt2"));		//��������ֵ2
	    String lotatt3 = CommonTools.getStrToGbk(request.getParameter("lotatt3"));		//��������ֵ3
	    String lotatt4 = CommonTools.getStrToGbk(request.getParameter("lotatt4"));		//��������ֵ4
	    String lotatt5 = CommonTools.getStrToGbk(request.getParameter("lotatt5"));		//��������ֵ5
	    String lotatt6 = CommonTools.getStrToGbk(request.getParameter("lotatt6"));		//��������ֵ6
	    String lotatt7 = CommonTools.getStrToGbk(request.getParameter("lotatt7"));		//��������ֵ7
	    String lotatt8 = CommonTools.getStrToGbk(request.getParameter("lotatt8"));		//��������ֵ8
	    String lotatt9 = CommonTools.getStrToGbk(request.getParameter("lotatt9"));		//��������ֵ9
	    String lotatt10 = CommonTools.getStrToGbk(request.getParameter("lotatt10"));	//��������ֵ10
	    String lotatt11 = CommonTools.getStrToGbk(request.getParameter("lotatt11"));	//��������ֵ11
	    String lotatt12 = CommonTools.getStrToGbk(request.getParameter("lotatt12"));	//��������ֵ12
	    
	    String remark = CommonTools.getStrToGbk(request.getParameter("remark"));    	//��ע
		
		String strUserName = (String)httpsession.getAttribute("userName");
		putawayRuleBus = new PutawayRuleImpl(dao);
		try
		{
			RulePutawayDetail putawayDetailRule = putawayRuleBus.getPutDetailByDetailId(putawaydetailid);
			putawayDetailRule.setRuleconfigid(ruleconfigid);		//��������ID
			putawayDetailRule.setEnableflag(enableflag);			//�Ƿ���Ч
		    
			putawayDetailRule.setTozone(tozone);					//Ŀ�����
			putawayDetailRule.setTolocationid(tolocationid);		//Ŀ���λ
		    
			putawayDetailRule.setLoc_restrict(loc_restrict);    	//��λ����
		    
			putawayDetailRule.setLoc_usage1(loc_usage1);   			//��λʹ��1
			putawayDetailRule.setLoc_usage2(loc_usage2);   			//��λʹ��2
			putawayDetailRule.setLoc_usage3(loc_usage3);   			//��λʹ��3
			putawayDetailRule.setLoc_usage4(loc_usage4);   			//��λʹ��4
			putawayDetailRule.setLoc_usage5(loc_usage5);   			//��λʹ��5
		    
			putawayDetailRule.setLoc_handle1(loc_handle1);   		//�洢����1
			putawayDetailRule.setLoc_handle2(loc_handle2);   		//�洢����2
			putawayDetailRule.setLoc_handle3(loc_handle3);   		//�洢����3
			putawayDetailRule.setLoc_handle4(loc_handle4);   		//�洢����4
			putawayDetailRule.setLoc_handle5(loc_handle5);   		//�洢����5
		    
		    putawayDetailRule.setLotid(lotid);			//��������ֵ1	
	    	putawayDetailRule.setLotatt1(lotatt1);		//��������ֵ1
	    	putawayDetailRule.setLotatt2(lotatt2);		//��������ֵ2
	    	putawayDetailRule.setLotatt3(lotatt3);		//��������ֵ3
	    	putawayDetailRule.setLotatt4(lotatt4);		//��������ֵ4
	    	putawayDetailRule.setLotatt5(lotatt5);		//��������ֵ5
	    	putawayDetailRule.setLotatt6(lotatt6);		//��������ֵ6
	    	putawayDetailRule.setLotatt7(lotatt7);		//��������ֵ7
	    	putawayDetailRule.setLotatt8(lotatt8);		//��������ֵ8
	    	putawayDetailRule.setLotatt9(lotatt9);		//��������ֵ9
	    	putawayDetailRule.setLotatt10(lotatt10);	//��������ֵ10
	    	putawayDetailRule.setLotatt11(lotatt11);	//��������ֵ11
	    	putawayDetailRule.setLotatt12(lotatt12);	//��������ֵ12
			
		    putawayDetailRule.setRemark(remark);    	//��ע
		    
		    putawayRuleBus.updatePutawayDetailRule(putawayDetailRule);
			
			Logger.info("�û�:" + strUserName + "�޸����ϼܹ�����ϸ:" + putawaydetailid);

			strUrl = "/standard/jsp/rule/putaway/rule_putaway_detail.jsp";
			
			List ls = putawayRuleBus.getPutawayDetailRuleById(putawayid);
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("�������==>�ϼܹ���==>�޸��ϼܹ�����ϸʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:ɾ���ϼܹ�����ϸ
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
		putawayRuleBus = new PutawayRuleImpl(dao);
		try
		{
			String putawayid = "";	 //�ϼܹ���ID
			if(ids != null && !ids.equals(""))
			{
				String[] id = ids.split(",");
				for(int i=0; i<id.length; i++){
				
					String[] arr = id[i].split("\\|");		//������ϸID +"|" + �ϼܹ���
					putawayid = arr[1];
					
					putawayRuleBus.deletePutawayDetailRule(arr[0]);
					Logger.info("�û�:" + strUserName + "ɾ�����ϼܹ�����ϸ:" + arr[0] + "����������ֵ:" + arr[1]);
				}
			}
			
			strUrl = "/standard/jsp/rule/putaway/rule_putaway_detail.jsp";
			
			List ls = putawayRuleBus.getPutawayDetailRuleById(putawayid);
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("�������==>�ϼܹ���==>ɾ���ϼܹ�����ϸʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�޸��ϼܹ�����ϸ������˳λ
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
		
		String putawayid = CommonTools.getStrToGbk(request.getParameter("putawayid"));  //�ϼܹ���ID
		String detailids = CommonTools.getStrToGbk(request.getParameter("detailids"));	//�ϼܹ�����ϸID
		String sorts = CommonTools.getStrToGbk(request.getParameter("sorts"));			//����˳λ
		String strUserName = (String)httpsession.getAttribute("userName");
		putawayRuleBus = new PutawayRuleImpl(dao);
		try
		{
			putawayRuleBus.updatePutawayDetailRuleSorts(detailids, sorts);
			Logger.info("�û�:" + strUserName + "�޸��ϼܹ���" + putawayid +"������˳λ");

			
			strUrl = "/standard/jsp/rule/putaway/rule_putaway_detail.jsp";
			
			List ls = putawayRuleBus.getPutawayDetailRuleById(putawayid);
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("�������==>�ϼܹ���==>�޸��ϼܹ�����ϸ������˳λʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}