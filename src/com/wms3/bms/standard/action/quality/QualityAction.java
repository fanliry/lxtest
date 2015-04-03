package com.wms3.bms.standard.action.quality;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.outbound.IOutBoundBus;
import com.wms3.bms.standard.business.outbound.impl.OutBoundBusImpl;
import com.wms3.bms.standard.business.quality.IQualityBus;
import com.wms3.bms.standard.business.quality.IQualityMgrBus;
import com.wms3.bms.standard.business.quality.impl.QualityBusImpl;
import com.wms3.bms.standard.business.quality.impl.QualityMgrBusImpl;
import com.wms3.bms.standard.entity.base.BaseLotSet;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;
/**
 * �������ʼ����:���ͳ�쵥�����action
 * @author liuxh
 *
 */
public class QualityAction extends AbstractAction {
	
	protected int maxLine = 20;			//��ҳ��ʾ��������
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
			
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
	        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
	        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
	        HttpSession httpsession = request.getSession(false);
			String strWarehouseId = CommonTools.getStrToGbk(request.getParameter("warehouseid"));    //�ֿ�  
	        String strWhAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));          //����ID
	        String strCargoAreaId = CommonTools.getStrToGbk(request.getParameter("cargoAreaId"));    //����ID
	        String strPlatoon = CommonTools.getStrToGbk(request.getParameter("platoon"));            //��
	        String strColumn = CommonTools.getStrToGbk(request.getParameter("column"));              //��
	        String strFloor = CommonTools.getStrToGbk(request.getParameter("floor"));                //��
	        String strProductId = CommonTools.getStrToGbk(request.getParameter("productid"));        //��Ʒid
	        String strTrayCode = CommonTools.getStrToGbk(request.getParameter("traycode"));          //��������
	        String strOwnerId = CommonTools.getStrToGbk(request.getParameter("ownerid"));            //����
	        String strLineRow = CommonTools.getStrToGbk(request.getParameter("linerow"));            //��ʾ��
	        
	        String lotid = CommonTools.getStrToGbk(request.getParameter("lotid"));             		 //����ID
			String lotatt1 = CommonTools.getStrToGbk(request.getParameter("lotatt1"));				 //��������1
			String lotatt2 = CommonTools.getStrToGbk(request.getParameter("lotatt2"));			     //��������2
			String lotatt3 = CommonTools.getStrToGbk(request.getParameter("lotatt3"));			     //��������3
			String lotatt4 = CommonTools.getStrToGbk(request.getParameter("lotatt4"));			     //��������4
			String lotatt5 = CommonTools.getStrToGbk(request.getParameter("lotatt5"));			     //��������5
			String lotatt6 = CommonTools.getStrToGbk(request.getParameter("lotatt6"));			     //��������6
			String lotatt7 = CommonTools.getStrToGbk(request.getParameter("lotatt7"));			     //��������7
			String lotatt8 = CommonTools.getStrToGbk(request.getParameter("lotatt8"));			     //��������8
			String lotatt9 = CommonTools.getStrToGbk(request.getParameter("lotatt9"));			     //��������9
			String lotatt10 = CommonTools.getStrToGbk(request.getParameter("lotatt10"));		     //��������10
			String lotatt11 = CommonTools.getStrToGbk(request.getParameter("lotatt11"));		     //��������11
			String lotatt12 = CommonTools.getStrToGbk(request.getParameter("lotatt12"));		     //��������12
	        
			IQualityBus iQualityBus = new QualityBusImpl(dao);
			
			if(strLineRow != null && strLineRow.length()>0){
	        	maxLine = Integer.parseInt(strLineRow);
	        }
			try {			
			strUrl = "/standard/jsp/quality/newquality/quality_kc_list.jsp"; 
			
			PagingTool pt = iQualityBus.getKcSelect(strWarehouseId, strWhAreaId, strCargoAreaId, strPlatoon, strColumn, strFloor, strOwnerId, null, null, strProductId, strTrayCode, lotid, lotatt1, lotatt2, lotatt3, lotatt4, lotatt5, 
					                               lotatt6, lotatt7, lotatt8, lotatt9, lotatt10, lotatt11, lotatt12,strUrl, maxLine);
		    List ls = pt.getLsResult();//��ѯ���
            request.setAttribute("exResultList", ls);
            request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("commpaging", pt);
            request.getRequestDispatcher(strUrl).forward(request, response);
			} catch (Exception er) {
				 Logger.error("�ʼ����==>���==>����Ʒ��ѯʧ��:" + er.getMessage());
		         request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		return null;
	}
	/**
	 * ���ܣ������������Ϣ����쵥�ݣ�������ϸ����ҵ����ҵ��ϸ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdd(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		
		    HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
	        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
	        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
	        String strwarehouseId = CommonTools.getStrToGbk(request.getParameter("warehouseId"));            //�ֿ�
	        String strOwnerId = CommonTools.getStrToGbk(request.getParameter("ownerId"));                 //����  
	        String strDepartId = CommonTools.getStrToGbk(request.getParameter("departId"));               //����
	        String strWarehouseManId = CommonTools.getStrToGbk(request.getParameter("warehouseManId"));   //�ֹ�Ա
	        String strCustomerId = CommonTools.getStrToGbk(request.getParameter("customerId"));           //�ͻ�
	        String strCreatTime = CommonTools.getStrToGbk(request.getParameter("creatTime"));             //����ʱ��
	        String strDetail = CommonTools.getStrToGbk(request.getParameter("detail"));                   //��ϸ
	        String strUserCode = (String)request.getSession().getAttribute("userCode");                   //������            
	     
	        int rowLength = StrTools.StrTimes(strDetail,"|");
	        OutboundInvoiceHeader outBoundHeader = new OutboundInvoiceHeader(); //�����ⵥ��
	        InoutJob outJob = new InoutJob();//������ҵ
            IQualityBus iQualityBus = new QualityBusImpl(dao);
            List<Object[]> delObj = new ArrayList<Object[]>();
         
            try {	
	        //���ⵥ
            outBoundHeader = iQualityBus.addpOutboundInvoiceHeader(strwarehouseId,strDepartId, strCreatTime, 
        		   strUserCode, strCustomerId, strOwnerId,strWarehouseManId);
            for (int i = 0; i < rowLength; i++) {         
            String colums[] = StrTools.GetStrFlag(strDetail,"|",i).split(",");
           // ��ţ�����1, 01��01��01��, ��ƷA, 09-21 13:20:56, null, EA, 10.0, 004001010101, etqgfewqgg, 6
            String  strPnum = colums[6];        //����
            String strInvetId = colums[8];     //���ID
            String strCheckNum = colums[9];   //�������
            double checkNum = Double.parseDouble(strCheckNum);
            double Num = Double.parseDouble(strPnum);
			Object[] obj = null;
			
			obj = iQualityBus.addOutInvoiceDelAddOutJob(strUserCode,strInvetId, outBoundHeader.getOutstockid(), outJob.getJobid(), Num, checkNum, strCustomerId,String.valueOf(i));
			delObj.add(obj);
            }
            //���� ������Ϣ����ҵ��Ϣ
            String strMgr = iQualityBus.addBoundAddBoundDelAddJobAddJobDel(outBoundHeader, delObj);
           
            strUrl = "/standard/jsp/quality/newquality/newquality_list.jsp";
			request.setAttribute("delList" , delObj);
			request.setAttribute("back_msg", strMgr);	
			request.getRequestDispatcher(strUrl).forward(request, response);
			} catch (Exception er) {
				 Logger.error("�ʼ����==>���==>��쵥����ʧ��:" + er.getMessage());
		         request.getRequestDispatcher("/error.jsp").forward(request, response);
			} 
	  return null;   
	}
	/**
	 * ���ܣ���쵥ȷ��
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecUpdate(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		
		    HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
	        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
	        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
	        HttpSession httpsession = request.getSession(false);
	        String ids = CommonTools.getStrToGbk(request.getParameter("ids"));            //����ID
	        String strUserCode = (String)httpsession.getAttribute("userCode");
	        IQualityMgrBus iMgrBus = new QualityMgrBusImpl(dao);
	        try {
	            String meg = iMgrBus.updateCheckBoundUpdateInvent(ids, strUserCode);
	 	        strUrl = "/standard/jsp/quality/qualitymgr/quality_qualitymgr_list.jsp";
	 	        request.setAttribute("back_msg", meg);
	 			request.getRequestDispatcher(strUrl).forward(request, response);
			} catch (Exception er) {
				Logger.error("�û�["+strUserCode+"]���ʼ����==>��쵥����==>��쵥ȷ��ʧ��:" + er.getMessage());
	            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
	       
	        
	  return null;   
	}
	/**
	 * ���ܣ����ϳ�쵥
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecDelete(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		
		    HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
	        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
	        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
	        HttpSession httpsession = request.getSession(false);
	        String ids = CommonTools.getStrToGbk(request.getParameter("ids"));            //����ID
	        String strUserCode = (String)httpsession.getAttribute("userCode");
	        IQualityMgrBus iMgrBus = new QualityMgrBusImpl(dao);
	        try {
	            String meg = iMgrBus.deleteCheckBoundUpdateInvent(ids, strUserCode);
	 	        strUrl = "/standard/jsp/quality/qualitymgr/quality_qualitymgr_list.jsp";
	 	        request.setAttribute("back_msg", meg);
	 			request.getRequestDispatcher(strUrl).forward(request, response);
			} catch (Exception er) {
				Logger.error("�û�["+strUserCode+"]���ʼ����==>��쵥����==>��쵥����ʧ��:" + er.getMessage());
	            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
	       
	        
	  return null;   
	}
	/**
	 * ����:���ⵥ����->���ݴ�ӡ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecReportCKD(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String outstockid = CommonTools.getStrToGbk(request.getParameter("outstockid"));	//���ⵥ�ݱ��
		
		IOutBoundBus outboundBus = new OutBoundBusImpl(dao);
		try{
			
			List<BaseLotSet> lsLot = null;	//�������Լ�
			HashMap<String, List> hsLot = (HashMap<String, List>)hsSysParam.get("viewLot");	//����Ҫ��ʾ������
			if(hsLot != null){
				lsLot = hsLot.get("newckd");		//�½����ⵥ->��ʾ���ⵥ��ϸ	
			}
			OutboundInvoiceHeader outbound = outboundBus.getOutBoundById(outstockid);	//���ⵥ
			List lsDetail = outboundBus.getOutBoundDetailsById(outstockid);				//���ⵥ��ϸ
            
            strUrl = "/standard/jsp/quality/qualitymgr/quality_qualitymgr_print.jsp";
            request.setAttribute("lsLot", lsLot);
            request.setAttribute("invoice", outbound);
            request.setAttribute("invoicedetail", lsDetail); 
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("�������==>���ݹ���==>���ⵥ��ӡʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
