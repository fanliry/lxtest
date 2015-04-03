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
import com.wms3.bms.standard.business.base.ICartonBus;
import com.wms3.bms.standard.business.base.impl.CartonBusImpl;
import com.wms3.bms.standard.entity.base.BaseCarton;

/**
 * ����:��ת��
 * @author gui
 *
 */
public class CartonAction extends AbstractAction
{
	protected ICartonBus cartonBus;
	protected int maxLine = 20;		//��ҳ��ʾ��������
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String cartonid = CommonTools.getStrToGbk(request.getParameter("cartonid"));	//װ�����
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        cartonBus = new CartonBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //��ѯ�б�
			{
				strUrl = "/standard/jsp/base/carton/base_carton_list.jsp";
				
				PagingTool pt = cartonBus.getCartonQuery(cartonid, strUrl, maxLine);
				List ls = pt.getLsResult();
				
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
					
			}else if(flag != null && flag.equals("2"))//�޸�ʱ��ȡ��Ϣ
			{
				strUrl = "/standard/jsp/base/carton/base_carton_update.jsp";
				
				BaseCarton carton = cartonBus.getCartonById(cartonid);
				request.setAttribute("carton", carton); 
				
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>��ת��==>��ת���ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:������ת��
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
		
		String cartontype = CommonTools.getStrToGbk(request.getParameter("cartontype"));		//װ������
		String descr = CommonTools.getStrToGbk(request.getParameter("descr"));					//װ������
		String boxleng = CommonTools.getStrToGbk(request.getParameter("boxleng"));				//��
		String boxwidth = CommonTools.getStrToGbk(request.getParameter("boxwidth"));			//��
		String boxheight = CommonTools.getStrToGbk(request.getParameter("boxheight"));			//��
		String maxcube = CommonTools.getStrToGbk(request.getParameter("maxcube"));				//������
		String maxweight = CommonTools.getStrToGbk(request.getParameter("maxweight"));			//�������
		String maxcount = CommonTools.getStrToGbk(request.getParameter("maxcount"));			//�������
		String selfweight = CommonTools.getStrToGbk(request.getParameter("selfweight"));		//����
		String cartonpercent = CommonTools.getStrToGbk(request.getParameter("cartonpercent"));	//װ��ٷֱ�
		String remark = CommonTools.getStrToGbk(request.getParameter("remark"));				//��ע
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		cartonBus = new CartonBusImpl(dao);
		try
		{
        	//������ת����Ϣ
			BaseCarton carton = new BaseCarton();
			carton.setCartontype(cartontype);	// װ������
			carton.setDescr(descr);				// װ������
			carton.setBoxleng(Double.parseDouble(boxleng));		// ��
			carton.setBoxwidth(Double.parseDouble(boxwidth));	// ��
			carton.setBoxheight(Double.parseDouble(boxheight));	// ��
			carton.setMaxcube(Double.parseDouble(maxcube));		// ������
			carton.setMaxweight(Double.parseDouble(maxweight));	// �������
			carton.setMaxcount(Double.parseDouble(maxcount));	// �������
			carton.setSelfweight(Double.parseDouble(selfweight));		// ����
			carton.setCartonpercent(Double.parseDouble(cartonpercent));	// װ��ٷֱ�
			carton.setRemark(remark);			// ��ע
			cartonBus.addCarton(carton);
			
			Logger.info("�û�" + strUserName + "�������ת��" + descr);

	        strUrl = "/standard/jsp/base/carton/base_carton_list.jsp";
	        PagingTool pt = cartonBus.getCartonQuery("", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>��ת�����==>������ת��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�޸���ת��
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
		
		String cartonid = CommonTools.getStrToGbk(request.getParameter("cartonid"));						//��ת��Id
		String cartontype = CommonTools.getStrToGbk(request.getParameter("cartontype"));		//װ������
		String descr = CommonTools.getStrToGbk(request.getParameter("descr"));					//װ������
		String boxleng = CommonTools.getStrToGbk(request.getParameter("boxleng"));				//��
		String boxwidth = CommonTools.getStrToGbk(request.getParameter("boxwidth"));			//��
		String boxheight = CommonTools.getStrToGbk(request.getParameter("boxheight"));			//��
		String maxcube = CommonTools.getStrToGbk(request.getParameter("maxcube"));				//������
		String maxweight = CommonTools.getStrToGbk(request.getParameter("maxweight"));			//�������
		String maxcount = CommonTools.getStrToGbk(request.getParameter("maxcount"));			//�������
		String selfweight = CommonTools.getStrToGbk(request.getParameter("selfweight"));		//����
		String cartonpercent = CommonTools.getStrToGbk(request.getParameter("cartonpercent"));	//װ��ٷֱ�
		String remark = CommonTools.getStrToGbk(request.getParameter("remark"));				//��ע
		
		String strUserName = (String)httpsession.getAttribute("userName");
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		cartonBus = new CartonBusImpl(dao);
		try
		{
			if(cartonid != null && cartonid.length()>0)
			{
				BaseCarton carton = cartonBus.getCartonById(cartonid);
				carton.setCartontype(cartontype);	// װ������
				carton.setDescr(descr);				// װ������
				carton.setBoxleng(Double.parseDouble(boxleng));		// ��
				carton.setBoxwidth(Double.parseDouble(boxwidth));	// ��
				carton.setBoxheight(Double.parseDouble(boxheight));	// ��
				carton.setMaxcube(Double.parseDouble(maxcube));		// ������
				carton.setMaxweight(Double.parseDouble(maxweight));	// �������
				carton.setMaxcount(Double.parseDouble(maxcount));	// �������
				carton.setSelfweight(Double.parseDouble(selfweight));		// ����
				carton.setCartonpercent(Double.parseDouble(cartonpercent));	// װ��ٷֱ�
				carton.setRemark(remark);			// ��ע
				cartonBus.updateCarton(carton);
				Logger.info("�û�" + strUserName + "�޸�����ת��" + cartonid);
			}
			
			strUrl = "/standard/jsp/base/carton/base_carton_list.jsp";
			PagingTool pt = cartonBus.getCartonQuery("", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>��ת�����==>�޸���ת��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:ɾ����ת��
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
		cartonBus = new CartonBusImpl(dao);
		try
		{
			if(ids != null && !ids.equals(""))
			{
				String[] id = ids.split(",");
				for(int i=0; i<id.length; i++)
				{
					//ɾ��
					cartonBus.deleteCarton(id[i]);	
					Logger.info("�û�" + strUserName + "ɾ������ת��" + id[i]);
				}
			}
			
			strUrl = "/standard/jsp/base/carton/base_carton_list.jsp";
			PagingTool pt = cartonBus.getCartonQuery("", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>��ת�����==>��ת��ɾ��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
}