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
import com.wms3.bms.standard.business.inventory.IInventoryTransactionBus;
import com.wms3.bms.standard.business.inventory.impl.InventoryTransactionBusImpl;

/**
 * ����:��潻��
 * @author liuxh
 *
 */
public class InventoryTransactionAction extends AbstractAction
{
	protected IInventoryTransactionBus iTransactionBus;
	protected int maxLine = 20;			//��ҳ��ʾ��������
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		//from
		String fmwarehouseid = CommonTools.getStrToGbk(request.getParameter("fmwarehouseid"));	//�ֿ�ID
		String fmwhAreaId = CommonTools.getStrToGbk(request.getParameter("fmwhAreaid"));		//����ID
		String fmplatoon = CommonTools.getStrToGbk(request.getParameter("fmplatoon"));			//��
		String fmcolumn = CommonTools.getStrToGbk(request.getParameter("fmcolumn"));			//��
		String fmfloor = CommonTools.getStrToGbk(request.getParameter("fmfloor"));				//��
		
		String fmCustomerId = CommonTools.getStrToGbk(request.getParameter("fmcustomerid"));	//�ͻ�ID
		String fmPackageId = CommonTools.getStrToGbk(request.getParameter("fmpackageId"));	//Ʒ��ID
		String fmTrayCode = CommonTools.getStrToGbk(request.getParameter("fmtrayCode"));		//��������
		//to
		String towhAreaId = CommonTools.getStrToGbk(request.getParameter("towhAreaid"));		//����ID
		String toplatoon = CommonTools.getStrToGbk(request.getParameter("toplatoon"));			//��
		String tocolumn = CommonTools.getStrToGbk(request.getParameter("tocolumn"));			//��
		String tofloor = CommonTools.getStrToGbk(request.getParameter("tofloor"));				//��
		
		String toCustomerId = CommonTools.getStrToGbk(request.getParameter("tocustomerId"));	//�ͻ�ID
		String toPackageId = CommonTools.getStrToGbk(request.getParameter("topackageId"));	//Ʒ��ID
		String toTrayCode = CommonTools.getStrToGbk(request.getParameter("totrayCode"));		//��������
		
		String date_from = CommonTools.getStrToGbk(request.getParameter("indate_from"));	//����from
		String date_to = CommonTools.getStrToGbk(request.getParameter("indate_to"));		//����to
        
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//ÿҳ��ʾ���� 
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        iTransactionBus = new InventoryTransactionBusImpl(dao);
        List lsTransactions = null;
        PagingTool pt = null;
		try
		{
			strUrl = "/standard/jsp/inventory/ykquery/kc_query_list.jsp";
			pt = iTransactionBus.getTransactionsLs(fmwarehouseid, fmwhAreaId, fmplatoon, fmcolumn, fmfloor,
						fmCustomerId, fmPackageId, fmTrayCode, towhAreaId, toplatoon, tocolumn, tofloor, toCustomerId, 
						toPackageId, toTrayCode, date_from, date_to,strUrl,1,maxLine);		
			lsTransactions = pt.getLsResult();
			request.setAttribute("exResultList", lsTransactions);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������==>��潻��==>��ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * ���ܣ���ӡ����
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecReport(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		//from
		String fmwarehouseid = CommonTools.getStrToGbk(request.getParameter("fmwarehouseid"));	//�ֿ�ID
		String fmwhAreaId = CommonTools.getStrToGbk(request.getParameter("fmwhAreaid"));		//����ID
		String fmplatoon = CommonTools.getStrToGbk(request.getParameter("fmplatoon"));			//��
		String fmcolumn = CommonTools.getStrToGbk(request.getParameter("fmcolumn"));			//��
		String fmfloor = CommonTools.getStrToGbk(request.getParameter("fmfloor"));				//��
		
		String fmCustomerId = CommonTools.getStrToGbk(request.getParameter("fmcustomerid"));	//�ͻ�ID
		String fmPackageId = CommonTools.getStrToGbk(request.getParameter("fmpackageId"));	//Ʒ��ID
		String fmTrayCode = CommonTools.getStrToGbk(request.getParameter("fmtrayCode"));		//��������
		//to
		String towhAreaId = CommonTools.getStrToGbk(request.getParameter("towhAreaid"));		//����ID
		String toplatoon = CommonTools.getStrToGbk(request.getParameter("toplatoon"));			//��
		String tocolumn = CommonTools.getStrToGbk(request.getParameter("tocolumn"));			//��
		String tofloor = CommonTools.getStrToGbk(request.getParameter("tofloor"));				//��
		
		String toCustomerId = CommonTools.getStrToGbk(request.getParameter("tocustomerId"));	//�ͻ�ID
		String toPackageId = CommonTools.getStrToGbk(request.getParameter("topackageId"));	//Ʒ��ID
		String toTrayCode = CommonTools.getStrToGbk(request.getParameter("totrayCode"));		//��������
		
		String date_from = CommonTools.getStrToGbk(request.getParameter("indate_from"));	//����from
		String date_to = CommonTools.getStrToGbk(request.getParameter("indate_to"));		//����to
        
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//ÿҳ��ʾ���� 
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        iTransactionBus = new InventoryTransactionBusImpl(dao);
        List lsTransactions = null;
        PagingTool pt = null;
		try
		{
			strUrl = "/standard/jsp/inventory/transaction/kc_transaction_report.jsp";
			pt = iTransactionBus.getTransactionsLs(fmwarehouseid, fmwhAreaId, fmplatoon, fmcolumn, fmfloor,
						fmCustomerId, fmPackageId, fmTrayCode, towhAreaId, toplatoon, tocolumn, tofloor, toCustomerId, 
						toPackageId, toTrayCode, date_from, date_to,strUrl,1,maxLine);		
			lsTransactions = pt.getLsResult();
			request.setAttribute("exResultList", lsTransactions);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������==>��潻��==>��ӡ����:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
