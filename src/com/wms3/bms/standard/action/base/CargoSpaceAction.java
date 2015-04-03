package com.wms3.bms.standard.action.base;

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
import com.ricosoft.common.tools.StringUtil;
import com.wms3.bms.standard.business.base.IAlleyBus;
import com.wms3.bms.standard.business.base.ICargoSpaceBus;
import com.wms3.bms.standard.business.base.IWarehouseBus;
import com.wms3.bms.standard.business.base.IWhAreaBus;
import com.wms3.bms.standard.business.base.impl.AlleyBusImpl;
import com.wms3.bms.standard.business.base.impl.CargoSpaceBusImpl;
import com.wms3.bms.standard.business.base.impl.WarehouseBusImpl;
import com.wms3.bms.standard.business.base.impl.WhAreaBusImpl;
import com.wms3.bms.standard.entity.base.BaseAlley;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BaseWarehouse;
import com.wms3.bms.standard.entity.base.BaseWharea;

/**
 * ����:��λ����
 * @author gui
 *
 */
public class CargoSpaceAction extends AbstractAction
{
	protected ICargoSpaceBus cargoSpaceBus;
	protected int maxLine = 20;
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		String cargoSpaceId = CommonTools.getStrToGbk(request.getParameter("cargoSpaceId"));	//��λID
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));	
		
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));				//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		cargoSpaceBus = new CargoSpaceBusImpl(dao);
		try
		{	
			if(flag!=null && flag.equals("1")){	//��ʼ����λҳ��
				
				strUrl = "/standard/jsp/base/cargospace/base_cargospace_cs.jsp";
				request.setAttribute("cargoSpaceId", cargoSpaceId);
				
			}else if(flag!=null && flag.equals("2")){//Ǩ�Ƶ���λ�޸�ҳ��
				
				strUrl = "/standard/jsp/base/cargospace/base_cargospace_update.jsp";
				BaseCargospace cargoSpace = cargoSpaceBus.getCargoSpaceById(cargoSpaceId);
				request.setAttribute("cargoSpace", cargoSpace); 
				
			}else if(flag != null && flag.equals("3")){ //ѡ���λ ��ѯ�б�
			
				String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�
				String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//����
				String cargoAreaId = CommonTools.getStrToGbk(request.getParameter("cargoAreaId"));	//����
				String platoon = CommonTools.getStrToGbk(request.getParameter("platoon"));			//��
				String column = CommonTools.getStrToGbk(request.getParameter("column"));			//��
				String floor = CommonTools.getStrToGbk(request.getParameter("floor"));				//��
				String csstatus = CommonTools.getStrToGbk(request.getParameter("csstatus"));		//��λ״̬
				String usage = CommonTools.getStrToGbk(request.getParameter("usage"));				//��λʹ��
				String handling = CommonTools.getStrToGbk(request.getParameter("handling"));		//�洢����
		  		
				strUrl = "/standard/jsp/common/common_cargospace_list.jsp";
				
				PagingTool pt = cargoSpaceBus.getCsQuery(warehouseid, whAreaId, cargoAreaId, platoon, column, floor, "", "", 
						csstatus, usage, handling, strUrl, maxLine);
				List ls = pt.getLsResult();
				
				request.setAttribute("exResultList", ls);
				request.setAttribute("pagingTool", pt);
				httpsession.setAttribute("commpaging", pt);
					
			}
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>��������==>������ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:��ʼ���ֿ���ʾ����
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String initWarehouse(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		try
		{
			String warehouseId = CommonTools.getStrToGbk(request.getParameter("warehouseId"));
			IWarehouseBus warehouseBus = new WarehouseBusImpl(dao);
			BaseWarehouse warehouse = warehouseBus.getWarehouseById(warehouseId);
			
			strUrl = "/standard/jsp/base/cargospace/base_cargospace_wh.jsp";
			request.setAttribute("warehouse", warehouse);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
		}
		catch(Exception ex)
		{
			Logger.error("��ʼ���ֿ���ʾ����ʧ��:"+ex.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:��ʼ��������ʾ����
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String initWhArea(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		try
		{
			String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));
			IWhAreaBus whAreaBus = new WhAreaBusImpl(dao);
			BaseWharea wharea = whAreaBus.getWhareaById(whAreaId);
			
			strUrl = "/standard/jsp/base/cargospace/base_cargospace_wharea.jsp";
			request.setAttribute("wharea", wharea);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
		}
		catch(Exception ex)
		{
			Logger.error("��ʼ��������ʾ����ʧ��:"+ex.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:��ʼ�������ʾ����
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String initAlley(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		try
		{
			String cargoAlleyId = CommonTools.getStrToGbk(request.getParameter("cargoAlleyId"));
			IAlleyBus alleyBus = new AlleyBusImpl(dao);
			BaseAlley alley = alleyBus.getAlleyById(cargoAlleyId);
			
			strUrl = "/standard/jsp/base/cargospace/base_cargospace_alley.jsp";
			request.setAttribute("alley", alley);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
		}
		catch(Exception ex)
		{
			Logger.error("��ʼ�������ʾ����ʧ��:"+ex.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:��ʾ��λ����
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String getCargoSpaceByCargoSpaceId(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		
		String cargoSpaceId = CommonTools.getStrToGbk(request.getParameter("cargoSpaceId"));
		
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		cargoSpaceBus = new CargoSpaceBusImpl(dao);
		
		try
		{
			BaseCargospace cargoSpace = cargoSpaceBus.getCargoSpaceById(cargoSpaceId);
			
			strUrl = "/standard/jsp/base/cargospace/base_cargospace_cs_left.jsp";
			request.setAttribute("cargoSpace", cargoSpace);
			request.getRequestDispatcher(strUrl).forward(request, response);
		}
		catch(Exception ex)
		{
			Logger.error("��ʾ��λ����ʧ��:"+ex.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:���ݻ�λID��ÿ��
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String getStorageByCargoSpaceId(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		
		String cargoSpaceId = CommonTools.getStrToGb2312(request.getParameter("cargoSpaceId"));
		
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		cargoSpaceBus = new CargoSpaceBusImpl(dao);
		
		try{
			
			List ls = cargoSpaceBus.getStorageBySpaceId(cargoSpaceId);
			
			strUrl = "/standard/jsp/base/cargospace/base_cargospace_cs_right.jsp";
			
			request.setAttribute("result", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception ex)
		{
			Logger.error("���ݻ�λID��ÿ��ʧ��:"+ex.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:��λ��ѯ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String cargoSpaceQuery(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseId = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//����
		String cargoAreaId = CommonTools.getStrToGbk(request.getParameter("cargoAreaId"));	//����
		String csstatus = CommonTools.getStrToGbk(request.getParameter("csstatus"));	//��λ
		String platoon = CommonTools.getStrToGbk(request.getParameter("platoon"));			//��
		String column = CommonTools.getStrToGbk(request.getParameter("column"));			//��
		String floor = CommonTools.getStrToGbk(request.getParameter("floor"));				//��
		String in_lock = CommonTools.getStrToGbk(request.getParameter("in_lock"));
		String out_lock = CommonTools.getStrToGbk(request.getParameter("out_lock"));
		
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        
		cargoSpaceBus = new CargoSpaceBusImpl(dao);
		
		try{
			strUrl = "/standard/jsp/inventory/kwquery/kw_query_list.jsp";
			
			PagingTool pt = cargoSpaceBus.getCsQuery(warehouseId, whAreaId, cargoAreaId, platoon, column, floor, 
					in_lock, out_lock, csstatus, "", "", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			//request.setAttribute("pagingTool", pt);
			
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception ex)
		{
			Logger.error("��λ��ѯʧ��:"+ex.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * ����:��λ��ѯ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String cargoLockSpaceQuery(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseId = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//����
		String cargoAreaId = CommonTools.getStrToGbk(request.getParameter("cargoAreaId"));	//����
		String csstatus = CommonTools.getStrToGbk(request.getParameter("csstatus"));	//��λ
		String platoon = CommonTools.getStrToGbk(request.getParameter("platoon"));			//��
		String column = CommonTools.getStrToGbk(request.getParameter("column"));			//��
		String floor = CommonTools.getStrToGbk(request.getParameter("floor"));				//��
		String in_lock = CommonTools.getStrToGbk(request.getParameter("in_lock"));
		String out_lock = CommonTools.getStrToGbk(request.getParameter("out_lock"));
		
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        
		cargoSpaceBus = new CargoSpaceBusImpl(dao);
		
		try{
			strUrl = "/standard/jsp/base/cargospace/base_cargospace_lock_list.jsp";
			
			PagingTool pt = cargoSpaceBus.getCsQuery(warehouseId, whAreaId, cargoAreaId, platoon, column, floor, 
					in_lock, out_lock, csstatus, "", "", strUrl, maxLine);
			List ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			//request.setAttribute("pagingTool", pt);
			
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception ex)
		{
			Logger.error("��λ��ѯʧ��:"+ex.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * ����:��ջ�λ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String clearCargoSpace(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String cargoSpaceId = CommonTools.getStrToGb2312(request.getParameter("cargoSpaceId"));
		cargoSpaceBus = new CargoSpaceBusImpl(dao);
		
		try{
			String back_msg = "Y";
			
			//one���һ����λ,all������л�λ
			String flag = CommonTools.getStrToGb2312(request.getParameter("flag"));
			if(flag != null && flag.trim().equals("one")){
				cargoSpaceBus.clearOneCargoSpace(cargoSpaceId);
				
			}else if(flag != null && flag.trim().equals("all")){
				cargoSpaceBus.clearAllCargoSpace();
				
			}
			
			request.setAttribute("back_msg", back_msg);
			String url="/standard/jsp/base/cargospace/base_cargospace.jsp";
			
			request.getRequestDispatcher(url).forward(request, response);
			
			
		}catch(Exception ex)
		{
			Logger.error("��ջ�λʧ��:"+ex.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:��λ����
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String cargoSpaceLock(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		String ids = CommonTools.getStrToGbk(request.getParameter("ids"));
		
		cargoSpaceBus = new CargoSpaceBusImpl(dao);
		
		try{
			
			String back_msg = "Y";
			String strFlag = null;
			
			//1:������;2:�������;3:������;4:�������
			if(flag != null && (flag.trim().equals("1") || flag.trim().equals("2"))){
			
				strFlag = "Y";
			}else if(flag != null && (flag.trim().equals("3") || flag.trim().equals("4"))){
			
				strFlag = "N";
			}
			
			String strIds = StringUtil.getStringToInSQL(ids);
			if(strFlag!=null){
				
				//�����������
				cargoSpaceBus.updateCargoSpaceLock(strIds, flag, strFlag);
				
			}else{	//5:��ջ�λ;6:�����λ
				
				if(flag != null && flag.equals("5")){

					//��տ��,���Ļ�λ״̬Ϊ�ջ�λ
					cargoSpaceBus.saveCargoSpace(strIds, "1");
				
				}else if(flag != null && flag.equals("6")){

					//�޸Ŀ��״̬Ϊδ����0���޸Ļ�λΪ����λ
					cargoSpaceBus.saveCargoSpace(strIds, "2");
				}	
			}
			
			PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
			List ls = null;
			if(pt != null){
				ls = CommonPagination.getPagingList(dao, pt);
			}
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			request.setAttribute("back_msg", back_msg);

			strUrl = "/standard/jsp/base/cargospace/base_cargospace_lock_list.jsp";
		    request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception ex)
		{
			Logger.error("��λ����ʧ��:"+ex.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}