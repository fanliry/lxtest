package com.wms3.bms.standard.action.inventory;

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
import com.wms3.bms.standard.business.inventory.IInventoryHoldBus;
import com.wms3.bms.standard.business.inventory.impl.InventoryHoldBusImpl;

/**
 * ����:��涳��/�ͷ�
 * @author gui
 *
 */
public class InventoryHoldAction extends AbstractAction {

	protected IInventoryHoldBus inventoryHoldBus;
	protected int maxLine = 20;			//��ҳ��ʾ��������
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String cargospaceid = CommonTools.getStrToGbk(request.getParameter("cargospaceid"));//��λID
		
		String productid = CommonTools.getStrToGbk(request.getParameter("productid"));		//Ʒ��ID
		String holdcode = CommonTools.getStrToGbk(request.getParameter("holdcode"));		//����ԭ��
		String holdby = CommonTools.getStrToGbk(request.getParameter("holdby"));			//���᷽��
		String dateon_from = CommonTools.getStrToGbk(request.getParameter("dateon_from"));	//��������
		String dateon_to = CommonTools.getStrToGbk(request.getParameter("dateon_to"));		//��������
		String dateoff_from = CommonTools.getStrToGbk(request.getParameter("dateoff_from"));//�ͷ�����
		String dateoff_to = CommonTools.getStrToGbk(request.getParameter("dateoff_to"));	//�ͷ�����
		String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));		//��������
		String boxcode = CommonTools.getStrToGbk(request.getParameter("boxcode"));			//������
		String productcode = CommonTools.getStrToGbk(request.getParameter("productcode"));	//��Ʒ����
		
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//ÿҳ��ʾ����
     
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        
        inventoryHoldBus = new InventoryHoldBusImpl(dao);
		try{
			
			if(flag != null && flag.equals("1")){ //��涳����Ϣ��ѯ
				
				strUrl = "/standard/jsp/inventory/hold/kc_hold_list.jsp";
				
				PagingTool pt = inventoryHoldBus.getHoldList(cargospaceid, productid, 
						holdcode, holdby, dateon_from, dateon_to, dateoff_from, dateoff_to, strUrl, maxLine);
				
				List ls = pt.getLsResult();
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
				
			}else if(flag != null && flag.equals("2")){ //��ѯ�����
				
				strUrl = "/standard/jsp/inventory/hold/kc_inhold_add_list.jsp";
				
				List ls = inventoryHoldBus.getStorageList(productid, cargospaceid, traycode, boxcode, productcode);
				request.setAttribute("exResultList", ls);
			}else if(flag != null && flag.equals("3")){ //��ѯ������
				
				strUrl = "/standard/jsp/inventory/hold/kc_outhold_add_list.jsp";
				
				List ls = inventoryHoldBus.getStorageList(productid, cargospaceid, traycode, boxcode, productcode);
				request.setAttribute("exResultList", ls);
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("������==>����/�ͷ�==>��ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			
		}
		return null;
	}
	
	/**
	 * ����:���ɶ����¼
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdd(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");

		String holdcode = CommonTools.getStrToGbk(request.getParameter("holdcode"));		//����ԭ��
		String holdby = CommonTools.getStrToGbk(request.getParameter("holdby"));			//���᷽��
		String dateoff = CommonTools.getStrToGbk(request.getParameter("dateoff"));			//�ͷ�����
		String holdreason = CommonTools.getStrToGbk(request.getParameter("holdreason"));	//ԭ������
		String ids = CommonTools.getStrToGbk(request.getParameter("ids"));					//���IDS

		String strUserCode = (String)httpsession.getAttribute("userCode");
		inventoryHoldBus = new InventoryHoldBusImpl(dao);
		try{

			String strBackMsg = inventoryHoldBus.addHold(ids, holdcode, holdby, dateoff, holdreason, strUserCode);
	    	
	    	strUrl = "/standard/jsp/inventory/hold/kc_hold_add_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){

			Logger.error("�û�["+strUserCode+"]��������==>����/�ͷ�==>�Կ��:" + ids + "���ɶ����¼ʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�ͷŶ����¼
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecUpdate(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");

		String ids = CommonTools.getStrToGbk(request.getParameter("ids"));					//���IDS

		String strUserCode = (String)httpsession.getAttribute("userCode");
		inventoryHoldBus = new InventoryHoldBusImpl(dao);
		try{
			
			String strBackMsg = inventoryHoldBus.updateHold(ids, strUserCode);
	    	
	    	strUrl = "/standard/jsp/inventory/hold/kc_hold_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){

			Logger.error("�û�["+strUserCode+"]��������==>����/�ͷ�==>�Կ��:" + ids + "���ɶ����¼ʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
}
