package com.wms3.bms.standard.action.inventory;

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
import com.wms3.bms.standard.business.base.IProductBus;
import com.wms3.bms.standard.business.base.impl.ProductBusImpl;
import com.wms3.bms.standard.business.inventory.IInventoryBus;
import com.wms3.bms.standard.business.inventory.impl.InventoryBusImpl;
import com.wms3.bms.standard.entity.base.BaseLotSet;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;

/**
 * ����:��Ʒ��ѯ
 * @author fanll
 *
 */
public class InventoryAction extends AbstractAction
{
	protected IInventoryBus inventoryBus;
	protected int maxLine = 20;			//��ҳ��ʾ��������
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�ID
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//����ID
		String Virtualwhid = CommonTools.getStrToGbk(request.getParameter("Virtualwhid"));		//�߼�����id
		String cargoAlleyId = CommonTools.getStrToGbk(request.getParameter("cargoAlleyId"));//���ID
		String platoon = CommonTools.getStrToGbk(request.getParameter("platoon"));			//��
		String column = CommonTools.getStrToGbk(request.getParameter("column"));			//��
		String floor = CommonTools.getStrToGbk(request.getParameter("floor"));				//��
		
		String customer_id = CommonTools.getStrToGbk(request.getParameter("customer_id"));	//����ID
		String package_id = CommonTools.getStrToGbk(request.getParameter("package_id"));	//Ʒ��ID
		String tray_code = CommonTools.getStrToGbk(request.getParameter("tray_code"));		//��������
		
		String indate_from = CommonTools.getStrToGbk(request.getParameter("indate_from"));	//�������from
		String indate_to = CommonTools.getStrToGbk(request.getParameter("indate_to"));		//�������to
		
		String total_items = CommonTools.getStrToGbk(request.getParameter("total_items"));  //ͳ����Ŀ
        String lotnumber = CommonTools.getStrToGbk(request.getParameter("lotnumber"));      //����
        String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));      //�������
        String productid = CommonTools.getStrToGbk(request.getParameter("productid"));      //��Ʒid
        //, , , , 
        String status = CommonTools.getStrToGbk(request.getParameter("status"));            //��λ״̬
        String kcstatus = CommonTools.getStrToGbk(request.getParameter("kcstatus"));            //��λ״̬
        String requestid = CommonTools.getStrToGbk(request.getParameter("requestid"));      //���뵥��
        String instockid = CommonTools.getStrToGbk(request.getParameter("instockid"));      //��ⵥ��
        String productstatus = CommonTools.getStrToGbk(request.getParameter("productstatus")); //��Ʒ״̬
        String productcode = CommonTools.getStrToGbk(request.getParameter("productcode"));     //��Ʒ����
        String producttype = CommonTools.getStrToGbk(request.getParameter("producttype"));     //��Ʒ���
       
		
		//String inventoryid = CommonTools.getStrToGb2312(request.getParameter("inventoryid"));   //���ID
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        
		inventoryBus = new InventoryBusImpl(dao);
		try
		{
			List ls = null;
			String strMsql = new String();
			String[] strSqls = new String[2];
			
			if(flag != null && (flag.equals("1") || flag.equals("2") || flag.equals("11") || flag.equals("12") || flag.equals("21"))){ //����ѯ

				if(!flag.equals("12") && !flag.equals("21")){
					strSqls = inventoryBus.getInventoryQuerynew(warehouseid, whAreaId,Virtualwhid, lotinfo, producttype, cargoAlleyId, platoon, 
							column, floor, lotnumber, productid, indate_from, indate_to, tray_code, status, requestid, instockid, productstatus, productcode);
				}else{
					strMsql = inventoryBus.getInventoryWpQuerynew(warehouseid, whAreaId, Virtualwhid, lotinfo, productid, indate_from, indate_to, productstatus);
				}

				if(flag.equals("1")){	//����ѯ ��ѯ�б�
					strUrl = "/standard/jsp/inventory/search/kc_search_list.jsp";
					PagingTool pt = CommonPagination.getPagingTool(dao, strSqls[1],strSqls[0] , strUrl, 1, maxLine);
					ls = pt.getLsResult();
					request.setAttribute("exResultList", ls);
					httpsession.setAttribute("paging", pt);
					
				}
				else if(flag.equals("12")){	//��Ʒ��ѯ ��ѯ�б�
					strUrl = "/standard/jsp/inventory/search/wp_search_list.jsp";
					
/*						strSqls = inventoryBus.getInventoryWpQuerynew(warehouseid, whAreaId, Virtualwhid, lotinfo, productid, indate_from, indate_to, productstatus);
						
						PagingTool pt = CommonPagination.getPagingTool(dao, strSqls[1],strSqls[0] , strUrl, 1, maxLine);
						ls = pt.getLsResult();*/
					ls = dao.searchEntities(strMsql);
					request.setAttribute("exResultList", ls);
					//httpsession.setAttribute("paging", pt);
					
				}
				else if(flag.equals("11")){	//����ѯ  ��Ʒ����Ԥ��
					strUrl = "/standard/jsp/inventory/productwarning/product_warning_list.jsp";
					List ls2 = null;
					IProductBus productBus = new ProductBusImpl(dao);
					ls2 = productBus.searchEntitieAll();
					request.setAttribute("exResultList2", ls2);
					
					PagingTool pt = CommonPagination.getPagingTool(dao, strSqls[1],strSqls[0] , strUrl, 1, maxLine);
					ls = pt.getLsResult();
					request.setAttribute("exResultList", ls);
					httpsession.setAttribute("paging", pt);
					
				}else if(flag.equals("2")){	//������ ����ѯ ����
					
					strUrl = "/standard/jsp/inventory/search/kc_search_report.jsp";
					
					//PagingTool pt = CommonPagination.getPagingTool(dao, strSqls[1],strSqls[0] , strUrl, 1, maxLine);
					ls = dao.searchEntities(strSqls[0]);
					request.setAttribute("exResultList", ls);
					//httpsession.setAttribute("paging", pt);
				}else if(flag.equals("21")){	//������ ��Ʒ��ѯ ����
					
					strUrl = "/standard/jsp/inventory/search/wp_search_report.jsp";
					
					//PagingTool pt = CommonPagination.getPagingTool(dao, strSqls[1],strSqls[0] , strUrl, 1, maxLine);
					ls = dao.searchEntities(strMsql);
					request.setAttribute("exResultList", ls);
					//httpsession.setAttribute("paging", pt);
				}
				
				}else if(flag != null && (flag.equals("3") || flag.equals("4"))){ //������ ���ͳ��
				
				strSqls = inventoryBus.getInventoryTotalQuery(warehouseid, whAreaId, customer_id, package_id, indate_from, indate_to,total_items);
				
				//ȡ��ҳ���б�Ҫ��ʾ����Ŀ��
				String list_items = "";
				if(total_items.indexOf("sto.productid") > 0){	//Ʒ��
					list_items += "Ʒ��,";
	            }
	            if(total_items.indexOf("sto.ownerId") > 0){		//����
	            	list_items += "����,";
	            }
	            
			/*	if(hsLot != null){
					lsLot = hsLot.get("kcstatistic");	//���ͳ��
				}
				*/
				BaseLotSet lotSet = null;
//				for(int i=0; i<lsLot.size(); i++){
//					lotSet = (BaseLotSet)lsLot.get(i);
//					if(total_items.indexOf(lotSet.getLotid()) > 0){
//						list_items += lotSet.getLotname() + ",";
//					}
//				}
				
				if(flag.equals("3")){	//������ ���ͳ�� ��ѯ�б�
					
					strUrl = "/standard/jsp/inventory/statistic/kc_statistic_list.jsp";
					
					PagingTool pt = CommonPagination.getPagingTool(dao, strSqls[0], strSqls[1], strUrl, 1, maxLine);
					ls = pt.getLsResult();
					
					request.setAttribute("list_items", list_items);
					request.setAttribute("exResultList", ls);
					httpsession.setAttribute("paging", pt);
					
				}else if(flag.equals("4")){	//������ ����ѯ ����
					
					strUrl = "/standard/jsp/inventory/statistic/kc_statistic_report.jsp";
					
					ls = dao.searchEntities(strSqls[1]);
					request.setAttribute("list_items", list_items);
					request.setAttribute("exResultList", ls);
				}
			}else if (flag!=null && flag.equals("5")) {
				strUrl = "/standard/jsp/inventory/adjust/kc_search_list.jsp";
				strSqls = inventoryBus.getInventoryQuerynew(warehouseid, whAreaId, cargoAlleyId, platoon, 
						column, floor, lotnumber, productid, indate_from, indate_to, tray_code, status, requestid, instockid, productstatus, productcode,kcstatus);
				
				PagingTool pt = CommonPagination.getPagingTool(dao, strSqls[1],strSqls[0] , strUrl, 1, maxLine);
				ls = pt.getLsResult();
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������==>����ѯ==>����ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
