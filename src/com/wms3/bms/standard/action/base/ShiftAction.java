package com.wms3.bms.standard.action.base;

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
import com.wms3.bms.standard.business.base.IShiftBus;
import com.wms3.bms.standard.business.base.IShiftconfigBus;
import com.wms3.bms.standard.business.base.impl.ShiftBusImpl;
import com.wms3.bms.standard.business.base.impl.ShiftconfigBusImpl;
import com.wms3.bms.standard.entity.base.BaseShift;
import com.wms3.bms.standard.entity.base.BaseShiftconfig;

/**
 * ����:��ι���
 * @author gui
 *
 */
public class ShiftAction extends AbstractAction
{
	protected IShiftBus shiftBus;
	protected IShiftconfigBus shiftconfigBus;
	protected int maxLine = 20;		//��ҳ��ʾ��������
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String op_man_id = CommonTools.getStrToGbk(request.getParameter("op_man_id"));		//������
		String in_out_type = CommonTools.getStrToGbk(request.getParameter("in_out_type"));	//�������
		String id = CommonTools.getStrToGbk(request.getParameter("id"));					//���Id
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		shiftBus = new ShiftBusImpl(dao);
		shiftconfigBus = new ShiftconfigBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //��ι��� ��ѯ�б�
			{
				strUrl = "/standard/jsp/base/shift/base_shift_list.jsp";
				
				PagingTool pt = shiftBus.getShiftQuery(op_man_id, in_out_type, strUrl, maxLine);
				List ls = pt.getLsResult();
				
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
					
			}else if(flag != null && flag.equals("2"))//��ι��� ���ʱ��ȡ��Ϣ
			{
				//ȡ�ð��������Ϣ
				BaseShiftconfig shiftconfig = shiftconfigBus.getShiftconfig();
				request.setAttribute("shiftconfig", shiftconfig); 
				
				//ȡ�������
				BaseShift lastshift = shiftBus.getLastShift("0");	//�����������
				BaseShift lastshiftin = shiftBus.getLastShift("1");	//���
				BaseShift lastshiftout = shiftBus.getLastShift("2");//����
				
				strUrl = "/standard/jsp/base/shift/base_shift_add.jsp";
				//strUrl = "/standard/jsp/base/shift/base_shift_add_editable.jsp";
				request.setAttribute("lastshift", lastshift); 
				request.setAttribute("lastshiftin", lastshiftin); 
				request.setAttribute("lastshiftout", lastshiftout); 

			}else if(flag != null && flag.equals("3"))//��ι��� �޸�ʱ��ȡ��Ϣ
			{
				strUrl = "/standard/jsp/base/shift/base_shift_update.jsp";
				
				BaseShift shift = shiftBus.getShiftById(id);
				request.setAttribute("shift", shift); 
			}	
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>��ι���==>��β�ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:���Ӱ��
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
		
		String daynight = CommonTools.getStrToGbk(request.getParameter("daynight"));		//�װࡢҹ��
		String indate = CommonTools.getStrToGbk(request.getParameter("indate"));			//���ʱ��
		String shiftturn = CommonTools.getStrToGbk(request.getParameter("shift"));			//�ס��ҡ�������
		String op_man_name = CommonTools.getStrToGbk(request.getParameter("op_man_name"));	//������
		String onduty = CommonTools.getStrToGbk(request.getParameter("onduty"));			//������Ա
		String lastShiftId = CommonTools.getStrToGbk(request.getParameter("lastShiftId"));	//�ϰ��ID
		String shiftname = CommonTools.getStrToGbk(request.getParameter("shiftname"));		//�ϰ�����
		String inouttype = CommonTools.getStrToGbk(request.getParameter("inouttype"));		//�������
		
		String strUserName = (String)httpsession.getAttribute("userName");
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		shiftBus = new ShiftBusImpl(dao);
		try
		{
        	//���������Ϣ
			shiftname = shiftname.replaceAll("-", "");
			BaseShift shift = new BaseShift(indate, daynight, shiftturn, shiftname, inouttype, op_man_name, 
				"N", "N", "", "N", "", "N", "", "Y", lastShiftId, onduty);
			String back_msg = shiftBus.addShift(shift);
			if(back_msg.equals("")){
				Logger.info("�û�" + strUserName + "����˰��" + shiftname);
			}
			
	        strUrl = "/standard/jsp/base/shift/base_shift_list.jsp";
	        PagingTool pt = shiftBus.getShiftQuery("", "", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("back_msg", back_msg);
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>��ι���==>���Ӱ��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�޸İ��
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
		
		String id = CommonTools.getStrToGbk(request.getParameter("id"));					//���Id
		String op_man_name = CommonTools.getStrToGbk(request.getParameter("op_man_name"));	//������
		String onduty = CommonTools.getStrToGbk(request.getParameter("onduty"));			//������Ա
		
		String strUserName = (String)httpsession.getAttribute("userName");
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		shiftBus = new ShiftBusImpl(dao);
		try
		{
			if(id != null && id.length()>0)
			{
				BaseShift shift = shiftBus.getShiftById(id);
				shift.setM_strOp_Man_Id(op_man_name);
				shift.setM_strOndutyMen(onduty);	
				shiftBus.updateShift(shift);
				Logger.info("�û���" + strUserName + "�޸��˰������" + shift.getM_strShiftName() + "����������ͣ�" + shift.getM_strIn_Out_Type());
			}
			
	        strUrl = "/standard/jsp/base/shift/base_shift_list.jsp";
	        PagingTool pt = shiftBus.getShiftQuery("", "", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>��ι���==>�޸İ��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:ɾ�����
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
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		shiftBus = new ShiftBusImpl(dao);
		try
		{
			if(ids != null && !ids.equals(""))
			{
				String[] id = ids.split(",");
				for(int i=0; i<id.length; i++)
				{
					//ɾ��
					shiftBus.deleteShift(id[i]);	
					Logger.info("�û�" + strUserName + "ɾ���˰��" + id[i]);
				}
			}
			
			strUrl = "/standard/jsp/base/shift/base_shift_list.jsp";
			PagingTool pt = shiftBus.getShiftQuery("", "", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>��ι���==>���ɾ��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�޸İ���趨��Ϣ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecEditSet(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String shift_timespace = CommonTools.getStrToGbk(request.getParameter("shift_timespace"));	//��εļ��ʱ��
		String shift_type = CommonTools.getStrToGbk(request.getParameter("shift_type"));			//�������
		String shift_inout = CommonTools.getStrToGbk(request.getParameter("shift_inout"));			//��ε��������
		String op_man1 = CommonTools.getStrToGbk(request.getParameter("op_man1"));					//�װฺ����
		String onduty1 = CommonTools.getStrToGbk(request.getParameter("onduty1"));					//�װ൱����Ա
		String op_man2 = CommonTools.getStrToGbk(request.getParameter("op_man2"));					//�Ұฺ����
		String onduty2 = CommonTools.getStrToGbk(request.getParameter("onduty2"));					//�Ұ൱����Ա
		String op_man3 = CommonTools.getStrToGbk(request.getParameter("op_man3"));					//���ฺ����
		String onduty3 = CommonTools.getStrToGbk(request.getParameter("onduty3"));					//���൱����Ա
		String op_man4 = CommonTools.getStrToGbk(request.getParameter("op_man4"));					//���ฺ����
		String onduty4 = CommonTools.getStrToGbk(request.getParameter("onduty4"));					//���൱����Ա
		
		String strUserName = (String)httpsession.getAttribute("userName");
		
		shiftconfigBus = new ShiftconfigBusImpl(dao);
		try
		{
			BaseShiftconfig shiftconfig = shiftconfigBus.getShiftconfig();
			if(shiftconfig == null){
				
				shiftconfig = new BaseShiftconfig(shift_timespace, shift_type, shift_inout, 
						op_man1, onduty1, op_man2, onduty2, op_man3, onduty3, op_man4, onduty4, "", "");
				shiftconfigBus.addShiftconfig(shiftconfig);
				
				Logger.info("�û�" + strUserName + "�����˰�����ã���μ��ʱ�䣺" + shift_timespace + "��������֣�" + shift_type 
						 + "����ε�������ͣ�" + shift_inout + "���װฺ���ˣ�" + op_man1 + "���װ൱����Ա��" + onduty1
						 + "���Ұฺ���ˣ�" + op_man2 + "���Ұ൱����Ա��" + onduty2 + "�����ฺ���ˣ�" + op_man3 + "�����൱����Ա��" + onduty3
						 + "�����ฺ���ˣ�" + op_man4 + "�����൱����Ա��" + onduty4);
				
			}else{
				shiftconfig.setTimespace(shift_timespace);
				shiftconfig.setType(shift_type);
				shiftconfig.setInout(shift_inout);
				shiftconfig.setOpman1(op_man1);
				shiftconfig.setOnduty1(onduty1);
				shiftconfig.setOpman2(op_man2);
				shiftconfig.setOnduty2(onduty2);
				shiftconfig.setOpman3(op_man3);
				shiftconfig.setOnduty3(onduty3);
				shiftconfig.setOpman4(op_man4);
				shiftconfig.setOnduty4(onduty4);
				shiftconfigBus.updateShiftconfig(shiftconfig);
				
				Logger.info("�û�" + strUserName + "�޸��˰�����ã���μ��ʱ�䣺" + shift_timespace + "��������֣�" + shift_type 
						 + "����ε�������ͣ�" + shift_inout + "���װฺ���ˣ�" + op_man1 + "���װ൱����Ա��" + onduty1
						 + "���Ұฺ���ˣ�" + op_man2 + "���Ұ൱����Ա��" + onduty2 + "�����ฺ���ˣ�" + op_man3 + "�����൱����Ա��" + onduty3
						 + "�����ฺ���ˣ�" + op_man4 + "�����൱����Ա��" + onduty4);
			}
			
			
			strUrl = "/standard/jsp/base/shift/base_shiftconfig.jsp";
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>��ι���==>�޸İ������ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
}