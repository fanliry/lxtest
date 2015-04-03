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
 * 描述：质检管理:抽检和抽检单管理的action
 * @author liuxh
 *
 */
public class QualityAction extends AbstractAction {
	
	protected int maxLine = 20;			//分页显示的行数；
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
			
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
	        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
	        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
	        HttpSession httpsession = request.getSession(false);
			String strWarehouseId = CommonTools.getStrToGbk(request.getParameter("warehouseid"));    //仓库  
	        String strWhAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));          //库区ID
	        String strCargoAreaId = CommonTools.getStrToGbk(request.getParameter("cargoAreaId"));    //货区ID
	        String strPlatoon = CommonTools.getStrToGbk(request.getParameter("platoon"));            //排
	        String strColumn = CommonTools.getStrToGbk(request.getParameter("column"));              //列
	        String strFloor = CommonTools.getStrToGbk(request.getParameter("floor"));                //层
	        String strProductId = CommonTools.getStrToGbk(request.getParameter("productid"));        //产品id
	        String strTrayCode = CommonTools.getStrToGbk(request.getParameter("traycode"));          //托盘条码
	        String strOwnerId = CommonTools.getStrToGbk(request.getParameter("ownerid"));            //货主
	        String strLineRow = CommonTools.getStrToGbk(request.getParameter("linerow"));            //显示行
	        
	        String lotid = CommonTools.getStrToGbk(request.getParameter("lotid"));             		 //批次ID
			String lotatt1 = CommonTools.getStrToGbk(request.getParameter("lotatt1"));				 //批次属性1
			String lotatt2 = CommonTools.getStrToGbk(request.getParameter("lotatt2"));			     //批次属性2
			String lotatt3 = CommonTools.getStrToGbk(request.getParameter("lotatt3"));			     //批次属性3
			String lotatt4 = CommonTools.getStrToGbk(request.getParameter("lotatt4"));			     //批次属性4
			String lotatt5 = CommonTools.getStrToGbk(request.getParameter("lotatt5"));			     //批次属性5
			String lotatt6 = CommonTools.getStrToGbk(request.getParameter("lotatt6"));			     //批次属性6
			String lotatt7 = CommonTools.getStrToGbk(request.getParameter("lotatt7"));			     //批次属性7
			String lotatt8 = CommonTools.getStrToGbk(request.getParameter("lotatt8"));			     //批次属性8
			String lotatt9 = CommonTools.getStrToGbk(request.getParameter("lotatt9"));			     //批次属性9
			String lotatt10 = CommonTools.getStrToGbk(request.getParameter("lotatt10"));		     //批次属性10
			String lotatt11 = CommonTools.getStrToGbk(request.getParameter("lotatt11"));		     //批次属性11
			String lotatt12 = CommonTools.getStrToGbk(request.getParameter("lotatt12"));		     //批次属性12
	        
			IQualityBus iQualityBus = new QualityBusImpl(dao);
			
			if(strLineRow != null && strLineRow.length()>0){
	        	maxLine = Integer.parseInt(strLineRow);
	        }
			try {			
			strUrl = "/standard/jsp/quality/newquality/quality_kc_list.jsp"; 
			
			PagingTool pt = iQualityBus.getKcSelect(strWarehouseId, strWhAreaId, strCargoAreaId, strPlatoon, strColumn, strFloor, strOwnerId, null, null, strProductId, strTrayCode, lotid, lotatt1, lotatt2, lotatt3, lotatt4, lotatt5, 
					                               lotatt6, lotatt7, lotatt8, lotatt9, lotatt10, lotatt11, lotatt12,strUrl, maxLine);
		    List ls = pt.getLsResult();//查询结果
            request.setAttribute("exResultList", ls);
            request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("commpaging", pt);
            request.getRequestDispatcher(strUrl).forward(request, response);
			} catch (Exception er) {
				 Logger.error("质检管理==>抽检==>抽检产品查询失败:" + er.getMessage());
		         request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		return null;
	}
	/**
	 * 功能：保存抽检出库信息：抽检单据，单据明细，作业，作业明细
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdd(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		
		    HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
	        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
	        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
	        String strwarehouseId = CommonTools.getStrToGbk(request.getParameter("warehouseId"));            //仓库
	        String strOwnerId = CommonTools.getStrToGbk(request.getParameter("ownerId"));                 //货主  
	        String strDepartId = CommonTools.getStrToGbk(request.getParameter("departId"));               //部门
	        String strWarehouseManId = CommonTools.getStrToGbk(request.getParameter("warehouseManId"));   //仓管员
	        String strCustomerId = CommonTools.getStrToGbk(request.getParameter("customerId"));           //客户
	        String strCreatTime = CommonTools.getStrToGbk(request.getParameter("creatTime"));             //创建时间
	        String strDetail = CommonTools.getStrToGbk(request.getParameter("detail"));                   //明细
	        String strUserCode = (String)request.getSession().getAttribute("userCode");                   //操作人            
	     
	        int rowLength = StrTools.StrTimes(strDetail,"|");
	        OutboundInvoiceHeader outBoundHeader = new OutboundInvoiceHeader(); //抽检出库单据
	        InoutJob outJob = new InoutJob();//新增作业
            IQualityBus iQualityBus = new QualityBusImpl(dao);
            List<Object[]> delObj = new ArrayList<Object[]>();
         
            try {	
	        //出库单
            outBoundHeader = iQualityBus.addpOutboundInvoiceHeader(strwarehouseId,strDepartId, strCreatTime, 
        		   strUserCode, strCustomerId, strOwnerId,strWarehouseManId);
            for (int i = 0; i < rowLength; i++) {         
            String colums[] = StrTools.GetStrFlag(strDetail,"|",i).split(",");
           // 蒙牛立体库1, 01排01列01层, 物品A, 09-21 13:20:56, null, EA, 10.0, 004001010101, etqgfewqgg, 6
            String  strPnum = colums[6];        //数量
            String strInvetId = colums[8];     //库存ID
            String strCheckNum = colums[9];   //抽检数量
            double checkNum = Double.parseDouble(strCheckNum);
            double Num = Double.parseDouble(strPnum);
			Object[] obj = null;
			
			obj = iQualityBus.addOutInvoiceDelAddOutJob(strUserCode,strInvetId, outBoundHeader.getOutstockid(), outJob.getJobid(), Num, checkNum, strCustomerId,String.valueOf(i));
			delObj.add(obj);
            }
            //保存 单据信息和作业信息
            String strMgr = iQualityBus.addBoundAddBoundDelAddJobAddJobDel(outBoundHeader, delObj);
           
            strUrl = "/standard/jsp/quality/newquality/newquality_list.jsp";
			request.setAttribute("delList" , delObj);
			request.setAttribute("back_msg", strMgr);	
			request.getRequestDispatcher(strUrl).forward(request, response);
			} catch (Exception er) {
				 Logger.error("质检管理==>抽检==>抽检单保存失败:" + er.getMessage());
		         request.getRequestDispatcher("/error.jsp").forward(request, response);
			} 
	  return null;   
	}
	/**
	 * 功能：抽检单确认
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
	        String ids = CommonTools.getStrToGbk(request.getParameter("ids"));            //单据ID
	        String strUserCode = (String)httpsession.getAttribute("userCode");
	        IQualityMgrBus iMgrBus = new QualityMgrBusImpl(dao);
	        try {
	            String meg = iMgrBus.updateCheckBoundUpdateInvent(ids, strUserCode);
	 	        strUrl = "/standard/jsp/quality/qualitymgr/quality_qualitymgr_list.jsp";
	 	        request.setAttribute("back_msg", meg);
	 			request.getRequestDispatcher(strUrl).forward(request, response);
			} catch (Exception er) {
				Logger.error("用户["+strUserCode+"]，质检管理==>抽检单管理==>抽检单确认失败:" + er.getMessage());
	            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
	       
	        
	  return null;   
	}
	/**
	 * 功能：作废抽检单
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
	        String ids = CommonTools.getStrToGbk(request.getParameter("ids"));            //单据ID
	        String strUserCode = (String)httpsession.getAttribute("userCode");
	        IQualityMgrBus iMgrBus = new QualityMgrBusImpl(dao);
	        try {
	            String meg = iMgrBus.deleteCheckBoundUpdateInvent(ids, strUserCode);
	 	        strUrl = "/standard/jsp/quality/qualitymgr/quality_qualitymgr_list.jsp";
	 	        request.setAttribute("back_msg", meg);
	 			request.getRequestDispatcher(strUrl).forward(request, response);
			} catch (Exception er) {
				Logger.error("用户["+strUserCode+"]，质检管理==>抽检单管理==>抽检单作废失败:" + er.getMessage());
	            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
	       
	        
	  return null;   
	}
	/**
	 * 功能:出库单管理->单据打印
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecReportCKD(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String outstockid = CommonTools.getStrToGbk(request.getParameter("outstockid"));	//出库单据编号
		
		IOutBoundBus outboundBus = new OutBoundBusImpl(dao);
		try{
			
			List<BaseLotSet> lsLot = null;	//批次属性集
			HashMap<String, List> hsLot = (HashMap<String, List>)hsSysParam.get("viewLot");	//所有要显示的批次
			if(hsLot != null){
				lsLot = hsLot.get("newckd");		//新建出库单->显示出库单详细	
			}
			OutboundInvoiceHeader outbound = outboundBus.getOutBoundById(outstockid);	//出库单
			List lsDetail = outboundBus.getOutBoundDetailsById(outstockid);				//出库单详细
            
            strUrl = "/standard/jsp/quality/qualitymgr/quality_qualitymgr_print.jsp";
            request.setAttribute("lsLot", lsLot);
            request.setAttribute("invoice", outbound);
            request.setAttribute("invoicedetail", lsDetail); 
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("出库管理==>单据管理==>出库单打印失败:" + er.getMessage());
            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
