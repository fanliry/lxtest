package com.wms3.bms.standard.action.quality;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

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
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;
import com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader;
import com.wms3.bms.standard.entity.quality.Release;
/**
 * 描述：质检管理
 * @author fanll
 *
 */
public class QualityNewAction extends AbstractAction {
	
	protected int maxLine = 20;			//分页显示的行数；
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
			
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
	        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
	        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
	        HttpSession httpsession = request.getSession(false);
	        
			String productId = CommonTools.getStrToGbk(request.getParameter("productId"));    //品名
	        String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));        //批号
	        String Qualityid = CommonTools.getStrToGbk(request.getParameter("Qualityid"));    //质检单号
	        String createtime = CommonTools.getStrToGbk(request.getParameter("createtime"));  //时间
	        String strLineRow = CommonTools.getStrToGbk(request.getParameter("linerow"));            //显示行

	        IQualityBus iQualityBus = new QualityBusImpl(dao);
			if(strLineRow != null && strLineRow.length()>0){
	        	maxLine = Integer.parseInt(strLineRow);
	        }
			try {
			strUrl = "/standard/jsp/quality/push/pushsearch_list.jsp"; 
			PagingTool pt = iQualityBus.getLsRelease(productId, lotinfo, Qualityid, createtime, strLineRow, maxLine);
			List ls = pt.getLsResult();//查询结果
            request.setAttribute("exResultList", ls);
            request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("commpaging", pt);
            request.getRequestDispatcher(strUrl).forward(request, response);
			} catch (Exception er) {
				 Logger.error("质检管理==>放行记录查询:" + er.getMessage());
		         request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		return null;
	}

	/**
	 * 功能：放行
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecPush(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		
		    HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
	        HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
	        EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
	        HttpSession httpsession = request.getSession(false);
	        
	        String productId = CommonTools.getStrToGbk(request.getParameter("productId"));    //品名
	        String lotinfo = CommonTools.getStrToGbk(request.getParameter("lotinfo"));    	  //批号
	        String Qualityid = CommonTools.getStrToGbk(request.getParameter("Qualityid"));    //质检单号
	        
	        String strUserCode = (String)httpsession.getAttribute("userCode");
	        
	        try {

	        	String strBackMsg = "Y";
	        	
	        	String sql1	= "UPDATE InoutJobdetail jobD SET  jobD.productStatus = '2' WHERE jobD.lotinfo = '"+lotinfo+"' and jobD.productid = '"+productId+"' ";
	        	String sql2	= "UPDATE InventoryStorage sto SET sto.productstatus = '2' WHERE sto.lotinfo = '"+lotinfo+"' and sto.productid = '"+productId+"' ";
	        	
	        	Release release = new Release();
	        	release.setProductid(productId);
	        	release.setLotinfo(lotinfo);
	        	release.setQualityid(Qualityid);
	        	release.setCreatetime(StrTools.getCurrDateTime(2));
	        	release.setOpManId(strUserCode);
	        	
	        	int jobD = InoutJobdetail.getJobDLotsum(productId, lotinfo, dao);
	        	int sto  = InventoryStorage.getJobDLotsum(productId, lotinfo, dao);
	        	if(jobD + sto > 0){ 

		            StatelessSession session = null; //大批量操作时使用
		            Transaction tx = null;
		            Connection conn = null;
		            
	        		try {

	                    conn = dao.getConnection();//从连接池获得连接
	                    session = dao.getSessionFactory().openStatelessSession(conn);
	                    tx = session.beginTransaction();
	                    
	        			//增加放行记录
	                    session.insert(release);

	                    session.createQuery(sql1).executeUpdate();
	                    session.createQuery(sql2).executeUpdate();
	                    
	                    tx.commit();   
	                }catch(Exception er){
	                    if(tx != null){
	                        tx.rollback();
	                    }
	                    throw new  Exception("放行出错："+er.getMessage());
	                }finally{
	                    if(session != null)
	                    {
	                        session.close();
	                    }
	                    if(conn != null){
	                        conn.close(); //关闭连接
	                    }
	                }    
		        	
	        	}else{
	        		strBackMsg = "放行失败,不存在该批号的产品！！";
	        	}
	        	
	 	        strUrl = "/standard/jsp/quality/push/pushchange.jsp";
	 	        request.setAttribute("back_msg", strBackMsg);
	 			request.getRequestDispatcher(strUrl).forward(request, response);
			} catch (Exception er) {
				Logger.error("用户["+strUserCode+"]，质检管理==>放行失败:" + er.getMessage());
	            request.setAttribute("errormsg", "错误信息:" + er.getMessage());
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
	       
	        
	  return null;   
	}

}
