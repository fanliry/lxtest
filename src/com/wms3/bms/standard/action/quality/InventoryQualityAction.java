package com.wms3.bms.standard.action.quality;

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
import com.wms3.bms.standard.business.quality.IQualityBus;
import com.wms3.bms.standard.business.quality.InventoryQualityBus;
import com.wms3.bms.standard.business.quality.impl.InventoryQualityBusImpl;
import com.wms3.bms.standard.business.quality.impl.QualityBusImpl;
import com.wms3.bms.standard.constant.CommonReturn;
import com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail;
import com.wms3.bms.standard.entity.inventory.InventoryQualityResult;
/**
 * ������״̬ת��
 * @author yao
 *
 */
public class InventoryQualityAction extends AbstractAction 
{
	
	protected IQualityBus qualityBus;
	protected int maxLine = 20;			//��ҳ��ʾ��������
	
	@Override
	public String  ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam)throws Exception{
		
		HttpServletRequest request = (HttpServletRequest) hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");

		String strWarehouseId = CommonTools.getStrToGbk(request.getParameter("warehouseid"));//�ֿ�ID	
		String strAreaId = CommonTools.getStrToGbk(request.getParameter("whareaid"));//����
		String strLotNumber = CommonTools.getStrToGbk(request.getParameter("lotnumber"));//����
		String strRequestId = CommonTools.getStrToGbk(request.getParameter("requestid"));//���뵥id
		String strProductId = CommonTools.getStrToGbk(request.getParameter("productid"));//��Ʒid
		String strProductStatus = CommonTools.getStrToGbk(request.getParameter("productstatus"));//��Ʒ״̬
		
		String productCode = CommonTools.getStrToGbk(request.getParameter("productCode"));//��Ʒ����
		String strLineRow = CommonTools.getStrToGbk(request.getParameter("linerow"));//��ʾ����
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));//��ʾ
		
		if (strLineRow!=null && strLineRow.length()>0) {
			maxLine = Integer.parseInt(strLineRow);
		}
		qualityBus = new QualityBusImpl(dao);
		List ls = null;
		try {
		if (flag!=null ) {
			if (flag.equals("1")) {//�ʼ����=��״̬ת��->��ѯ���
				strUrl = "standard/jsp/quality/changestatus/changestatus_list.jsp";
				String strQueryHql = qualityBus.searchInventoryForLotnumber(strWarehouseId, strAreaId, strLotNumber, strRequestId, strProductId, strProductStatus,productCode);	
				int count = dao.searchEntities(strQueryHql).size();
				PagingTool pt = CommonPagination.getPagingTool(dao, count, strQueryHql, strUrl, 1, maxLine);
				ls = pt.getLsResult();				
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);
			}else if(flag.equals("2")){//�ʼ����=��״̬ת��->��ѯ��棨��ⵥ��
                String strHql = qualityBus.searchInventroyForInstock(strWarehouseId, strLotNumber,strRequestId, strProductId,strProductStatus,strAreaId);
				ls = dao.searchEntities(strHql);
                strUrl = "standard/jsp/quality/changestatus/changestatus_list_instock.jsp";		
				request.setAttribute("exResultList", ls);			
			}else if(flag.equals("3")){//�ʼ����=��״̬ת��->��ѯ��棨��ⵥ��ϸ��
				String instockid = CommonTools.getStrToGbk(request.getParameter("instockid"));
                List<InboundDetail> lsdel = qualityBus.searchInStockDel(instockid, strProductId);
                strUrl = "standard/jsp/quality/changestatus/changestatus_list_instockdel.jsp";		
				request.setAttribute("exResultList", lsdel);			
			}else if(flag.equals("4")){//�ʼ����=�����м�¼��ѯ->��ѯ���м�¼
				String strFormDate = CommonTools.getStrToGbk(request.getParameter("fromdate"));//��ʼʱ��
				String strToDate = CommonTools.getStrToGbk(request.getParameter("todate"));//����ʱ��
				
				InventoryQualityBus iBus = new InventoryQualityBusImpl(dao);
				//��ѯ���м�¼��hql
				String strHql = iBus.getQualityHeaderSQL(strLotNumber, strFormDate, strToDate);
				String strCountHql = iBus.getQualityHeaderCountSQL(strLotNumber, strFormDate, strToDate);
				
				strUrl = "standard/jsp/quality/changestatus/changestatusqueryhead.jsp";	
				PagingTool pt = CommonPagination.getPagingTool(dao, strCountHql, strHql, strUrl, 1, maxLine);
				
				ls = pt.getLsResult();				
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);			
			}
		}
		 request.getRequestDispatcher(strUrl).forward(request, response);
		} catch (Exception er) {
			Logger.error("�ʼ����==>״̬ת��==>��ѯ���:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * ���ܣ�������Ʒ״̬���ӷ��м�¼
	 *@author liuxh 2013-3-8
	 *@param hsSysParam
	 *@param hsCurrentParam
	 *@return
	 *@throws Exception
	 */
	public String ricoExecUpdateStatusAndAddRecord(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");  
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		String strUserCode = request.getSession().getAttribute("userCode").toString();
		//String strUserName = request.getSession().getAttribute("userName").toString();
		
		
		String strWarehouseId = CommonTools.getStrToGbk(request.getParameter("warehouseid"));//�ֿ�ID	
		String strAreaId = CommonTools.getStrToGbk(request.getParameter("whareaid"));//����
		String strLotNumber = CommonTools.getStrToGbk(request.getParameter("lotnumber"));//����
		String strRequestId = CommonTools.getStrToGbk(request.getParameter("requestid"));//���뵥id
		String strProductId = CommonTools.getStrToGbk(request.getParameter("productid"));//��Ʒid
		String strProductStatus = CommonTools.getStrToGbk(request.getParameter("productstatus"));//��Ʒ״̬
		String strwpzt = CommonTools.getStrToGbk(request.getParameter("wpzt"));//�µ���Ʒ״̬
		qualityBus = new QualityBusImpl(dao);
		try {
			CommonReturn cReturn = qualityBus.updateStatusAddRecord(strUserCode, strWarehouseId, strAreaId, strLotNumber, strRequestId, strProductId, strProductStatus, strwpzt);
			strUrl = "/standard/jsp/quality/changestatus/changestatus_list.jsp";
			String strQueryHql = qualityBus.searchInventoryForLotnumber(strWarehouseId, strAreaId, strLotNumber, strRequestId, strProductId, strwpzt,null);	
			int count = dao.searchEntities(strQueryHql).size();
			PagingTool pt = CommonPagination.getPagingTool(dao, count, strQueryHql, strUrl, 1, maxLine);
			List ls = pt.getLsResult();				
			request.setAttribute("exResultList", ls);
			request.setAttribute("strMeg", cReturn.getRetInfo());
			httpsession.setAttribute("paging", pt);	
		}catch (Exception er) {
			Logger.error("�ʼ����==>״̬ת��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		request.getRequestDispatcher(strUrl).forward(request, response);
		return null;
	}
	/**
	 * ״̬ת������ϸ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");   
	
		String strUserCode = request.getSession().getAttribute("userCode").toString();
		String strUserName = request.getSession().getAttribute("userName").toString();
		String id = CommonTools.getStrToGbk(request.getParameter("id"));//״̬ת������
		strUrl = "/standard/jsp/quality/changestatus/changestatusquerydetail.jsp";
		try{
			if(id!=null && !id.equals("")){
				InventoryQualityBusImpl qualityBusImpl= new InventoryQualityBusImpl(dao);
				//InventoryQualityResultDetail ss=null;
				List ls= qualityBusImpl.getAdjustDetailListToId(id);
				request.setAttribute("exResultList", ls);
			}
		}catch (Exception er){
			Logger.error("�ʼ����==>״̬ת��:��ѯ״̬ת������ϸʱ���� " + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		request.getRequestDispatcher(strUrl).forward(request, response);
		return null;
	}
	
	/**
	 * ��ӡ
	 * 
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecPrint(Hashtable hsSysParam, Hashtable hsCurrentParam)
			throws Exception {
		HttpServletRequest request = (HttpServletRequest) hsCurrentParam
				.get("request");
		HttpServletResponse response = (HttpServletResponse) hsCurrentParam
				.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO) hsSysParam.get("dao");

		
		
		String invoiceid = CommonTools.getStrToGbk(request.getParameter("invoiceid"));		//���е�ID
		String linerow = CommonTools.getStrToGbk(request
				.getParameter("linerow")); // ÿҳ��ʾ����
		if (linerow != null && linerow.length() > 0) {
			maxLine = Integer.parseInt(linerow);
		}
		try {
			strUrl = "/standard/jsp/quality/changestatus/changesatus_print.jsp";
			
			InventoryQualityBusImpl qualityBusImpl= new InventoryQualityBusImpl(dao);
			
			InventoryQualityResult result= qualityBusImpl.getAdjustListToId(invoiceid);
			List ls = qualityBusImpl.getAdjustDetailListToId(invoiceid);
			
			request.setAttribute("result", result);
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);

		} catch (Exception er) {
			Logger.error("��������==>���м�¼��ѯ==>����:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
		}

		return null;
	}

}
