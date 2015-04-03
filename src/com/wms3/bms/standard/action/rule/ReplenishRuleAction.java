package com.wms3.bms.standard.action.rule;

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
import com.wms3.bms.standard.business.rule.IReplenishRuleBus;
import com.wms3.bms.standard.business.rule.impl.ReplenishRuleImpl;
import com.wms3.bms.standard.entity.rule.RuleReplenish;

/**
 * ����:�����������
 * @author 
 *
 */
public class ReplenishRuleAction extends AbstractAction
{
	protected IReplenishRuleBus replenishRuleBus;
	protected int maxLine = 20;		//��ҳ��ʾ��������
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String replenishid = CommonTools.getStrToGbk(request.getParameter("replenishid"));	//��������Id
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�����ֿ�ID
		String descr = CommonTools.getStrToGbk(request.getParameter("descr"));				//������������
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        replenishRuleBus = new ReplenishRuleImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //����������� ��ѯ�б�
			{
				strUrl = "/standard/jsp/rule/replenish/rule_replenish_list.jsp";
				
				PagingTool pt = replenishRuleBus.getReplenishRuleQuery(warehouseid, descr, strUrl, maxLine);
				List ls = pt.getLsResult();
				
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
					
			}else if(flag != null && flag.equals("2"))//����������� �޸�ʱ��ȡ��Ϣ
			{
				strUrl = "/standard/jsp/rule/replenish/rule_replenish_update.jsp";
				
				RuleReplenish replenishRule = replenishRuleBus.getReplenishRuleById(replenishid);
				request.setAttribute("replenishRule", replenishRule); 
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("�������==>��������==>���������ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:���Ӳ�������
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
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));		//�����ֿ�ID
		String ruleconfigid = CommonTools.getStrToGb2312(request.getParameter("ruleconfigid"));	//��������ID
		
		String ea_lowerlimit = CommonTools.getStrToGb2312(request.getParameter("ea_lowerlimit"));	//��������
		String ea_uplimit = CommonTools.getStrToGb2312(request.getParameter("ea_uplimit"));			//��������
		String ea_minreplenishqty = CommonTools.getStrToGb2312(request.getParameter("ea_minreplenishqty"));	//������С������
		
		String cs_lowerlimit = CommonTools.getStrToGb2312(request.getParameter("cs_lowerlimit"));	//��������
		String cs_uplimit = CommonTools.getStrToGb2312(request.getParameter("cs_uplimit"));			//��������
		String cs_minreplenishqty = CommonTools.getStrToGb2312(request.getParameter("cs_minreplenishqty"));	//������С������
		
		String ot_lowerlimit = CommonTools.getStrToGb2312(request.getParameter("ot_lowerlimit"));	//���������
		String ot_uplimit = CommonTools.getStrToGb2312(request.getParameter("ot_uplimit"));			//���������
		String ot_minreplenishqty = CommonTools.getStrToGb2312(request.getParameter("ot_minreplenishqty"));	//�������С������
		
		String remark = CommonTools.getStrToGb2312(request.getParameter("remark"));	//��ע
		
		String strUserName = (String)httpsession.getAttribute("userName");
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        replenishRuleBus = new ReplenishRuleImpl(dao);
		try
		{
        	//����������Ϣ
			RuleReplenish replenishRule = new RuleReplenish();
			replenishRule.setDescr(descr);					//����
			replenishRule.setWarehouseid(warehouseid);		//�����ֿ�ID
			replenishRule.setRuleconfigid(ruleconfigid);	//��������ID
			replenishRule.setEa_lowerlimit(Double.parseDouble(ea_lowerlimit));		//��������
			replenishRule.setEa_uplimit(Double.parseDouble(ea_uplimit));			//��������
			replenishRule.setEa_minreplenishqty(Double.parseDouble(ea_minreplenishqty));	//������С������
			replenishRule.setCs_lowerlimit(Double.parseDouble(cs_lowerlimit));		//�������
			replenishRule.setCs_uplimit(Double.parseDouble(cs_uplimit));			//�������
			replenishRule.setCs_minreplenishqty(Double.parseDouble(cs_minreplenishqty));	//�����С������
			replenishRule.setOt_lowerlimit(Double.parseDouble(ot_lowerlimit));		//���������
			replenishRule.setOt_uplimit(Double.parseDouble(ot_uplimit));			//���������
			replenishRule.setOt_minreplenishqty(Double.parseDouble(ot_minreplenishqty));	//�������С������
			replenishRule.setRemark(remark);				//��ע

			replenishRuleBus.addReplenishRule(replenishRule);
			
			Logger.info("�û�" + strUserName + "����˲�������" + descr);

			strUrl = "/standard/jsp/rule/replenish/rule_replenish_list.jsp";
			
			PagingTool pt = replenishRuleBus.getReplenishRuleQuery("", "", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("�������==>��������==>���Ӳ�������ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�޸Ĳ�������
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
		
		String replenishid = CommonTools.getStrToGbk(request.getParameter("replenishid"));			//��������Id
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));			//�����ֿ�ID
		String descr = CommonTools.getStrToGb2312(request.getParameter("descr"));					//����
		String ruleconfigid = CommonTools.getStrToGb2312(request.getParameter("ruleconfigid"));		//��������ID
		
		String ea_lowerlimit = CommonTools.getStrToGb2312(request.getParameter("ea_lowerlimit"));	//��������
		String ea_uplimit = CommonTools.getStrToGb2312(request.getParameter("ea_uplimit"));			//��������
		String ea_minreplenishqty = CommonTools.getStrToGb2312(request.getParameter("ea_minreplenishqty"));	//������С������
		
		String cs_lowerlimit = CommonTools.getStrToGb2312(request.getParameter("cs_lowerlimit"));	//��������
		String cs_uplimit = CommonTools.getStrToGb2312(request.getParameter("cs_uplimit"));			//��������
		String cs_minreplenishqty = CommonTools.getStrToGb2312(request.getParameter("cs_minreplenishqty"));	//������С������
		
		String ot_lowerlimit = CommonTools.getStrToGb2312(request.getParameter("ot_lowerlimit"));	//���������
		String ot_uplimit = CommonTools.getStrToGb2312(request.getParameter("ot_uplimit"));			//���������
		String ot_minreplenishqty = CommonTools.getStrToGb2312(request.getParameter("ot_minreplenishqty"));	//�������С������
		
		String remark = CommonTools.getStrToGb2312(request.getParameter("remark"));	//��ע
		
		String strUserName = (String)httpsession.getAttribute("userName");
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        replenishRuleBus = new ReplenishRuleImpl(dao);
		try
		{
			if(replenishid != null && replenishid.length()>0)
			{
				RuleReplenish replenishRule = replenishRuleBus.getReplenishRuleById(replenishid);
				replenishRule.setDescr(descr);					//����
				replenishRule.setWarehouseid(warehouseid);		//�����ֿ�ID
				replenishRule.setRuleconfigid(ruleconfigid);	//��������ID
				replenishRule.setEa_lowerlimit(Double.parseDouble(ea_lowerlimit));		//��������
				replenishRule.setEa_uplimit(Double.parseDouble(ea_uplimit));			//��������
				replenishRule.setEa_minreplenishqty(Double.parseDouble(ea_minreplenishqty));	//������С������
				replenishRule.setCs_lowerlimit(Double.parseDouble(cs_lowerlimit));		//�������
				replenishRule.setCs_uplimit(Double.parseDouble(cs_uplimit));			//�������
				replenishRule.setCs_minreplenishqty(Double.parseDouble(cs_minreplenishqty));	//�����С������
				replenishRule.setOt_lowerlimit(Double.parseDouble(ot_lowerlimit));		//���������
				replenishRule.setOt_uplimit(Double.parseDouble(ot_uplimit));			//���������
				replenishRule.setOt_minreplenishqty(Double.parseDouble(ot_minreplenishqty));	//�������С������
				replenishRule.setRemark(remark);				//��ע
				
				replenishRuleBus.updateReplenishRule(replenishRule);
				Logger.info("�û�" + strUserName + "�޸��˲�������" + replenishid);
			}
			
			strUrl = "/standard/jsp/rule/replenish/rule_replenish_list.jsp";
			
			PagingTool pt = replenishRuleBus.getReplenishRuleQuery("", "", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("�������==>��������==>�޸Ĳ�������ʧ��:" + er.getMessage());
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
	public String ricoExecDelete(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String ids = CommonTools.getStrToGbk(request.getParameter("ids"));
		String strUserName = (String)httpsession.getAttribute("userName");
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));	//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        replenishRuleBus = new ReplenishRuleImpl(dao);
		try
		{
			if(ids != null && !ids.equals(""))
			{
				String[] id = ids.split(",");
				for(int i=0; i<id.length; i++)
				{
					//ɾ��
					replenishRuleBus.deleteReplenishRule(id[i]);	
					Logger.info("�û�" + strUserName + "ɾ���˲�������" + id[i]);
				}
			}
			
			strUrl = "/standard/jsp/rule/replenish/rule_replenish_list.jsp";
			
			PagingTool pt = replenishRuleBus.getReplenishRuleQuery("", "", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("�������==>��������==>ɾ����������ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}