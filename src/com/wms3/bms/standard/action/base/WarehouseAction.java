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
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.base.IWarehouseBus;
import com.wms3.bms.standard.business.base.impl.WarehouseBusImpl;
import com.wms3.bms.standard.entity.base.BaseWarehouse;

/**
 * ����:�ֿ����
 * @author gui
 *
 */
public class WarehouseAction extends AbstractAction
{
    protected IWarehouseBus warehouseBus; 
    
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));		// �ֿ�ID
		String warehousename = CommonTools.getStrToGbk(request.getParameter("warehousename"));	// �ֿ���
		String warehousetype = CommonTools.getStrToGbk(request.getParameter("warehousetype"));	// �ֿ�����
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
        warehouseBus = new WarehouseBusImpl(dao);
        
		try
		{
			if(flag != null && flag.equals("1")) //�ֿ���� ��ѯ�б�
			{
				strUrl = "/standard/jsp/base/warehouse/base_warehouse_list.jsp";
				List ls = warehouseBus.getWarehouseQuery(warehousename, warehousetype);
					
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2"))//�ֿ���� �޸�ʱ��ȡ��Ϣ
			{
				strUrl = "/standard/jsp/base/warehouse/base_warehouse_update.jsp";
				BaseWarehouse wh = warehouseBus.getWarehouseById(warehouseid);
				
				request.setAttribute("Warehouse", wh); 
				
			}	
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>�ֿ����==>�ֿ��ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

	/**
	 * ����:���Ӳֿ�
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
		
		String warehousename = CommonTools.getStrToGbk(request.getParameter("warehousename"));	// �ֿ���
		String whaddress = CommonTools.getStrToGbk(request.getParameter("whaddress"));			// �ֿ��ַ
		String whmgrman = CommonTools.getStrToGbk(request.getParameter("whmgrman"));			// �ֿ����Ա
		String whlinkman = CommonTools.getStrToGbk(request.getParameter("whlinkman"));			// ��ϵ��
		String whlinktel = CommonTools.getStrToGbk(request.getParameter("whlinktel"));			// ��ϵ�绰
		String iscurrent = CommonTools.getStrToGbk(request.getParameter("iscurrent"));			// �Ƿ�ǰ�ֿ�
		String erpcode = CommonTools.getStrToGbk(request.getParameter("erpcode"));				// ��ӦERP�Ĵ���
		String mark = CommonTools.getStrToGb2312(request.getParameter("mark"));
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);
		
        warehouseBus = new WarehouseBusImpl(dao);
		try{
			
        	if(mark.equals("1")){
        		//���òֿⲻ�ǵ�ǰ�ֿ�
        		warehouseBus.updateIsCurrent();
        	}
        	
        	//�����ֿ���Ϣ
			BaseWarehouse warehouse = new BaseWarehouse();
			warehouse.setWarehousename(warehousename);		// �ֿ���
			warehouse.setWhaddress(whaddress);				// �ֿ��ַ
			warehouse.setWhmgrman(whmgrman);				// �ֿ����Ա
			warehouse.setWhlinkman(whlinkman);				// ��ϵ��
			warehouse.setWhlinktel(whlinktel);				// ��ϵ�绰
			warehouse.setIscurrent(iscurrent);				// �Ƿ�ǰ�ֿ�
			warehouse.setErpcode(erpcode);					// ��ӦERP�Ĵ���
			warehouse.setUpdatetime(currentTime);			// ����޸�ʱ��
			warehouse.setUpdatemanid(strUserCode);			// ����޸���
            warehouseBus.addWarehouse(warehouse);
			
			Logger.info("�û�" + strUserName + "����˲ֿ�" + warehousename);
			
			//���»�ȡ�ֿ��б�
	        strUrl = "/standard/jsp/base/warehouse/base_warehouse_list.jsp";
	        List ls = warehouseBus.getWarehouseList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>�ֿ����==>���Ӳֿ�ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�޸Ĳֿ�
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
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));		// �ֿ�ID
		String warehousename = CommonTools.getStrToGbk(request.getParameter("warehousename"));	// �ֿ���
		String whaddress = CommonTools.getStrToGbk(request.getParameter("whaddress"));			// �ֿ��ַ
		String whmgrman = CommonTools.getStrToGbk(request.getParameter("whmgrman"));			// �ֿ����Ա
		String whlinkman = CommonTools.getStrToGbk(request.getParameter("whlinkman"));			// ��ϵ��
		String whlinktel = CommonTools.getStrToGbk(request.getParameter("whlinktel"));			// ��ϵ�绰
		String iscurrent = CommonTools.getStrToGbk(request.getParameter("iscurrent"));			// �Ƿ�ǰ�ֿ�
		String erpcode = CommonTools.getStrToGbk(request.getParameter("erpcode"));				// ��ӦERP�Ĵ���
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		String strUserName = (String)httpsession.getAttribute("userName");
		String currentTime = StrTools.getCurrDateTime(2);

        warehouseBus = new WarehouseBusImpl(dao);
		try
		{
			if(warehouseid != null && !warehouseid.equals(""))
			{
				if(iscurrent.equals("Y")){
	        		//���òֿⲻ�ǵ�ǰ�ֿ�
	        		warehouseBus.updateIsCurrent();
	        	}
				
				BaseWarehouse wh = warehouseBus.getWarehouseById(warehouseid);
				wh.setWarehousename(warehousename);		// �ֿ���
				wh.setWhaddress(whaddress);				// �ֿ��ַ
				wh.setWhmgrman(whmgrman);				// �ֿ����Ա
				wh.setWhlinkman(whlinkman);				// ��ϵ��
				wh.setWhlinktel(whlinktel);				// ��ϵ�绰
				wh.setIscurrent(iscurrent);				// �Ƿ�ǰ�ֿ�
				wh.setErpcode(erpcode);					// ��ӦERP�Ĵ���
				wh.setUpdatetime(currentTime);			// ����޸�ʱ��
				wh.setUpdatemanid(strUserCode);			// ����޸���
				
                warehouseBus.updateWarehouse(wh);
				Logger.info("�û�" + strUserName + "�޸��˲ֿ�" + warehouseid);
			}
			
			//���»�ȡ�ֿ��б�
	        strUrl = "/standard/jsp/base/warehouse/base_warehouse_list.jsp";
	        List ls = warehouseBus.getWarehouseList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>�ֿ����==>�޸Ĳֿ�ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:ɾ���ֿ�
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
		
		String id = CommonTools.getStrToGbk(request.getParameter("id"));
		String strUserName = (String)httpsession.getAttribute("userName");

        warehouseBus = new WarehouseBusImpl(dao);
		try
		{
			//ɾ��
            warehouseBus.deleteWarehouse(id);	
			Logger.info("�û�" + strUserName + "ɾ���˲ֿ�" + id);
			
			//���»�ȡ�ֿ��б�
	        strUrl = "/standard/jsp/base/warehouse/base_warehouse_list.jsp";
	        List ls = warehouseBus.getWarehouseList();
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>�ֿ����==>�ֿ�ɾ��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
