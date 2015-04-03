package com.wms3.bms.standard.action.Analysis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.standard.business.base.IAlleyBus;
import com.wms3.bms.standard.business.base.impl.AlleyBusImpl;
import com.wms3.bms.standard.dao.base.impl.BaseCargoSpaceDaoImpl;
import com.wms3.bms.standard.entity.base.BaseAlley;
/**
 * ������KPI����
 * @author zhi
 *
 */
public class InAnalysisAction extends AbstractAction
{
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));		//�ֿ�ID
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));			//����id
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		String inout = CommonTools.getStrToGbk(request.getParameter("inout"));
		
		String iFloor = CommonTools.getStrToGbk(request.getParameter("iFloor")); //��
		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from")); //����
		String indate_to = CommonTools.getStrToGbk(request.getParameter("indate_to")); //����
		
		
		try
		{
			if(flag != null && flag.equals("1")) //��λ���ȷ���
			{
				BaseCargoSpaceDaoImpl alleyBus = new BaseCargoSpaceDaoImpl(dao);
				strUrl = "/standard/jsp/inventory/kc_inevenly_analyse_list.jsp";
				List ls = alleyBus.getListSum(warehouseid);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("11")) //��λ���ȷ���
			{
				BaseCargoSpaceDaoImpl alleyBus = new BaseCargoSpaceDaoImpl(dao);
				strUrl = "/standard/jsp/apianalyse/inevenly/kc_inevenly_analyse_list.jsp";
				List ls = alleyBus.getListSum(warehouseid,whAreaId);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2")) //�Ѷ�����ɷ���
			{
				strUrl = "/standard/jsp/inventory/kc_stowMachine_analyse_list.jsp";
				String hql="";
				if(inout!=null && inout.equals("1")){
					hql = "select tcargoAlleyId, count(*) from InoutJob  where status in ('1','2','3') and jobpos='"+inout+"' and onLineType = '1' and warehouseid='"+warehouseid+"'" +
					"  group by tcargoAlleyId order by tcargoAlleyId";
				}else{
					hql = "select scargoAlleyId, count(*) from InoutJob  where status in ('1','2','3') and jobpos='"+inout+"' and onLineType = '1' and warehouseid='"+warehouseid+"'" +
					"  group by scargoAlleyId order by scargoAlleyId";
				}
				List ls = dao.searchEntities(hql);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("3")) //��λƽ�����
			{
				strUrl = "/standard/jsp/inventory/kc_space_analyse_list.jsp";
				BaseCargoSpaceDaoImpl alleyBus = new BaseCargoSpaceDaoImpl(dao);
				String strSQL = alleyBus.getSpaceStatus(iFloor,warehouseid);
				//��÷����������ŵĲ�ѯ���
				String strIClumnSQL = alleyBus.getiColumn(iFloor,warehouseid);	
				//��÷����������еĲ�ѯ���
				String strIPlatoonSQL = alleyBus.getiPlatoon(iFloor,warehouseid);
				
		
				List ls = dao.searchEntities(strSQL);
				HashMap hsRet = new HashMap();
				for(int i=0 ;i < ls.size(); i++){
					Object obj[] = (Object[])ls.get(i);
					hsRet.put((obj[0].toString() +","+obj[1]).toString(),obj[2]);
				}
				List  icolumn= dao.searchEntities(strIClumnSQL);
				List iplatoon = dao.searchEntities(strIPlatoonSQL);
				request.setAttribute("platoon", iplatoon.get(0));
				request.setAttribute("column", icolumn.get(0));
				request.setAttribute("result", hsRet);
					
			}else if(flag != null && flag.equals("4")){
				strUrl = "/standard/jsp/inventory/kc_alley_analyse_list.jsp";
				String hql="";
				if(inout!=null && inout.equals("1")){
					hql = "select tcargoAlleyId, count(*) from InoutJob  where  jobpos='"+inout+"' and onLineType = '1' and createtime >= '"+indate_from+" 00:00:00' and createtime <'"+indate_to+" 24:00:00' and warehouseid='"+warehouseid+"'" +
					"  group by tcargoAlleyId order by tcargoAlleyId";
				}else{
					hql = "select scargoAlleyId, count(*) from InoutJob  where  jobpos='"+inout+"' and onLineType = '1' and createtime >= '"+indate_from+" 00:00:00' and createtime <'"+indate_to+" 24:00:00' and warehouseid='"+warehouseid+"'" +
					"  group by scargoAlleyId order by scargoAlleyId";
				}
				List ls = dao.searchEntities(hql);
				request.setAttribute("exResultList", ls);
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>KPI��������:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}