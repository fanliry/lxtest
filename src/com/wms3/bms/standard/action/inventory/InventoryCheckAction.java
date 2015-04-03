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
import com.ricosoft.common.tools.StrTools;
import com.wms3.bms.standard.business.inventory.IInventoryBus;
import com.wms3.bms.standard.business.inventory.IInventoryCheckBus;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckRequest;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult;
import com.wms3.bms.standard.business.inventory.impl.InventoryCheckBusImpl;
import com.wms3.bms.standard.constant.CommonReturn;

/**
 * ����:����̵�
 * @author gui
 *
 */
public class InventoryCheckAction extends AbstractAction {

	protected IInventoryBus inventoryBus;
	protected IInventoryCheckBus inventoryCheckBus;
	protected int maxLine = 20;			//��ҳ��ʾ��������
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));	//�ֿ�ID
		String whAreaId = CommonTools.getStrToGbk(request.getParameter("whAreaId"));		//����ID
		String type = CommonTools.getStrToGbk(request.getParameter("type"));		//����
		String status = CommonTools.getStrToGbk(request.getParameter("status"));	//״̬
		String lotnumber = CommonTools.getStrToGbk(request.getParameter("lotnumber"));		//����
		String productid = CommonTools.getStrToGbk(request.getParameter("productid "));	//״̬
		String requestid = CommonTools.getStrToGbk(request.getParameter("requestid"));	//�̵�����ID
		String taskid = CommonTools.getStrToGbk(request.getParameter("taskid"));	//�̵�����ID
		
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
        String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));		//ÿҳ��ʾ����
     
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        
        inventoryCheckBus = new InventoryCheckBusImpl(dao);
        
		try{
			
			if(flag != null && flag.equals("1")){ //�̵�������Ϣ��ѯ
				
				strUrl = "/standard/jsp/inventory/check/kc_check_list.jsp";
				
				/*PagingTool pt = inventoryCheckBus.getCheckRequests(warehouseid, whAreaId, type, status,lotnumber,productid, strUrl, maxLine);
				
				List ls = pt.getLsResult();
				request.setAttribute("exResultList", ls);
				httpsession.setAttribute("paging", pt);*/
				
			}else if(flag != null && flag.equals("2")){ //�̵�������Ϣ��ѯ
				
				strUrl = "/standard/jsp/inventory/check/kc_check_detail.jsp";
				
				//List ls = inventoryCheckBus.getCheckTasks(requestid);
				//request.setAttribute("exResultList", ls);
				
			}else if(flag != null && flag.equals("3")){ //�޸��̵����󣬲�ѯ�̵�����
				
				strUrl = "/standard/jsp/inventory/check/kc_check_request_update.jsp";
				
				InventoryCheckRequest checkreq = inventoryCheckBus.getCheckReqById(requestid);
				request.setAttribute("checkreq", checkreq); 
				
			}else if(flag != null && flag.equals("4")){ //�����̵����񣬲�ѯ�̵�����
				
				strUrl = "/standard/jsp/inventory/check/kc_check_task.jsp";
				
				InventoryCheckRequest checkreq = inventoryCheckBus.getCheckReqById(requestid);
				request.setAttribute("checkreq", checkreq); 
				
			}else if(flag != null && flag.equals("5")){ //�����̵����񣬲�ѯ����б�
				
				strUrl = "/standard/jsp/inventory/check/kc_check_task_list.jsp";
				
				List ls = inventoryCheckBus.queryStorage(requestid);
				request.setAttribute("exResultList", ls);
				
			}else if(flag != null && flag.equals("6")){ //���, ��ѯ�̵�����
				
				strUrl = "/standard/jsp/inventory/check/kc_check_result_list.jsp";
				
				//List ls = inventoryCheckBus.getCheckTasks(requestid);
				//request.setAttribute("exResultList", ls);
				
			}else if(flag != null && flag.equals("7")){ //���, ��ѯ�̵���
				
				strUrl = "/standard/jsp/inventory/check/kc_check_result_detail.jsp";
				
				List ls = inventoryCheckBus.getCheckResults(taskid);
				request.setAttribute("exResultList", ls);
				
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("������==>����̵�==>��ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�����̵����뵥
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
		
		String type =  CommonTools.getStrToGbk(request.getParameter("type"));					//����
		//String priority = CommonTools.getStrToGbk(request.getParameter("priority"));			//���ȼ�
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));		//�ֿ�
		String wh_area_id = CommonTools.getStrToGbk(request.getParameter("wh_area_id"));		//����
		String cargo_space_id = CommonTools.getStrToGbk(request.getParameter("cargo_space_id"));//��λ
        String lotnumber = CommonTools.getStrToGbk(request.getParameter("lotnumber"));		    //����
        String productid = CommonTools.getStrToGbk(request.getParameter("productid"));			//Ʒ��
        String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));			//��������
       // String boxcode = CommonTools.getStrToGbk(request.getParameter("boxcode"));				//������
        String productcode = CommonTools.getStrToGbk(request.getParameter("productcode"));		//��Ʒ����
        //String starttime = CommonTools.getStrToGbk(request.getParameter("starttime"));			//��ʼʱ��
        //String endtime = CommonTools.getStrToGbk(request.getParameter("endtime"));				//����ʱ��
	    

		String strUserCode = (String)httpsession.getAttribute("userCode");
		inventoryCheckBus = new InventoryCheckBusImpl(dao);
		
		try{
			
			String strBackMsg = "Y";
			String strTime =  StrTools.getCurrDateTime(2); 
			
			InventoryCheckRequest checkReq = new InventoryCheckRequest();
			checkReq.setCounttype(type);		//����
			checkReq.setStatus("1");			//״̬
			//checkReq.setPriority(Integer.parseInt(priority));	//���ȼ�
			checkReq.setWarehouseid(warehouseid);	//�ֿ�
			checkReq.setWh_area_id(wh_area_id);		//����
			checkReq.setCargo_space_id(cargo_space_id);	//��λ
			checkReq.setLotinfo(lotnumber);		//����
			checkReq.setProductid(productid);		//Ʒ��
			checkReq.setTraycode(traycode);			//��������
			//checkReq.setBoxcode(boxcode);			//������
			checkReq.setProductcode(productcode);	//��Ʒ����
			checkReq.setRequesttime(strTime);		//����ʱ��
			checkReq.setCreatemanid(strUserCode);		//������
		//	checkReq.setStarttime(starttime);		//��ʼʱ��
			//checkReq.setEndtime(endtime);			//����ʱ��
	    	
	    	inventoryCheckBus.addCheckReq(checkReq);
	    	
	    	strUrl = "/standard/jsp/inventory/check/kc_check_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){

			Logger.error("�û�["+strUserCode+"]��������==>����̵�==>�����̵�����ʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�޸��̵����뵥
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecEdit(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String type =  CommonTools.getStrToGbk(request.getParameter("type"));					//����
		String warehouseid = CommonTools.getStrToGbk(request.getParameter("warehouseid"));		//�ֿ�
		String wh_area_id = CommonTools.getStrToGbk(request.getParameter("wh_area_id"));		//����
		String cargo_space_id = CommonTools.getStrToGbk(request.getParameter("cargo_space_id"));//��λ
        String lotnumber = CommonTools.getStrToGbk(request.getParameter("lotnumber"));		    //����
        String productid = CommonTools.getStrToGbk(request.getParameter("productid"));			//Ʒ��
        String traycode = CommonTools.getStrToGbk(request.getParameter("traycode"));			//��������
        String productcode = CommonTools.getStrToGbk(request.getParameter("productcode"));		//��Ʒ����
        String requestid = CommonTools.getStrToGbk(request.getParameter("requestid"));		    //���뵥id
        

		String strUserCode = (String)httpsession.getAttribute("userCode");
		inventoryCheckBus = new InventoryCheckBusImpl(dao);
		
		try{
			
			String strBackMsg = "Y";
			
			InventoryCheckRequest checkReq = inventoryCheckBus.getCheckReqById(requestid);
			if(checkReq.getStatus().equals("1")){	
				
				checkReq.setCounttype(type);		//����
				checkReq.setStatus("1");			//״̬
				//checkReq.setWarehouseid(warehouseid);	//�ֿ�
				checkReq.setWh_area_id(wh_area_id);		//����
				checkReq.setCargo_space_id(cargo_space_id);	//��λ
				checkReq.setLotinfo(lotnumber);		//����
				checkReq.setProductid(productid);		//Ʒ��
				checkReq.setTraycode(traycode);			//��������
				checkReq.setProductcode(productcode);	//��Ʒ����
				checkReq.setRequesttime(StrTools.getCurrDateTime(2));		//����ʱ��
				checkReq.setCreatemanid(strUserCode);		//������
		    	
		    	inventoryCheckBus.updateCheckReq(checkReq);
				Logger.info("�û�["+strUserCode+"]��������==>����̵�==>�޸����̵����뵥:" + requestid);
				
			}else{
				strBackMsg = "ֻ���޸ġ��½���״̬�����뵥��";
			}
	    	
	    	strUrl = "/standard/jsp/inventory/check/kc_check_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){

			Logger.error("�û�["+strUserCode+"]��������==>����̵�==>�޸��̵����뵥ʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:ɾ���̵����뵥
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecDelete(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String ids = CommonTools.getStrToGbk(request.getParameter("ids"));
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		inventoryCheckBus = new InventoryCheckBusImpl(dao);
		
		try{
			String strBackMsg = "Y";
			InventoryCheckRequest checkReq = null;
			if(ids != null && !ids.equals("")){
			
				String[] id = ids.split(",");
				for(int i=0; i<id.length; i++){
					
					checkReq = inventoryCheckBus.getCheckReqById(id[i]);
					if(checkReq.getStatus().equals("1")){
						
						inventoryCheckBus.deleteCheckReq(id[i]);	
						Logger.info("�û�["+strUserCode+"]��ɾ�����̵����뵥" + id[i]);
					}else{
						strBackMsg = "�ǡ��½���״̬�����뵥�޷�ɾ����";
					}
				}
			}
			
			strUrl = "/standard/jsp/inventory/check/kc_check_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){
		
			Logger.error("�û�["+strUserCode+"]��������==>����̵�==>ɾ���̵����뵥ʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�����̵�����
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAddTask(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");

		String requestid = CommonTools.getStrToGbk(request.getParameter("requestid"));	//�̵�����ID
		String ids = CommonTools.getStrToGbk(request.getParameter("ids"));	//���ID

		String strUserCode = (String)httpsession.getAttribute("userCode");
		inventoryCheckBus = new InventoryCheckBusImpl(dao);
		
		try{
				
			String strBackMsg = inventoryCheckBus.addCheckTasks(requestid, ids);
			if(strBackMsg.equals("Y")){
				Logger.info("�û�["+strUserCode+"]�����̵����뵥:" + requestid + "�������̵�����");
			}
	    	
	    	strUrl = "/standard/jsp/inventory/check/kc_check_task_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){

			Logger.error("�û�["+strUserCode+"]��������==>����̵�==>���̵����뵥:" + requestid + "�����̵�����ʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�����̵���
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAddResult(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");

		String taskid = CommonTools.getStrToGbk(request.getParameter("taskid"));		//�̵�����ID
		String checknum = CommonTools.getStrToGbk(request.getParameter("checknum"));	//�̵�����

		String strUserCode = (String)httpsession.getAttribute("userCode");
		inventoryCheckBus = new InventoryCheckBusImpl(dao);
		
		try{
				
			CommonReturn cReturn =  inventoryCheckBus.addCheckResult(taskid, checknum,strUserCode);		
			String strBackMsg = cReturn.getRetInfo();
			List lsResult =  cReturn.getLsReturn();
			if(strBackMsg.equals("Y")){
				Logger.info("�û�["+strUserCode+"]�����̵�����:" + taskid + "�������̵������̵�������" + checknum );
			}
	    	
	    	strUrl = "/standard/jsp/inventory/check/kc_check_result_detail.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.setAttribute("exResultList", lsResult);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){

			Logger.error("�û�["+strUserCode+"]��������==>����̵�==>���̵�����:" + taskid + "�����̵���ʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�޸��̵���
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecEditResult(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");

		String checkid = CommonTools.getStrToGbk(request.getParameter("checkid"));		//�̵���ID
		String checknum = CommonTools.getStrToGbk(request.getParameter("checknum"));	//�̵�����
		//String checknetweight = CommonTools.getStrToGbk(request.getParameter("checknetweight"));//�̵�����
		String taskid = "";
		
		String strUserCode = (String)httpsession.getAttribute("userCode");
		inventoryCheckBus = new InventoryCheckBusImpl(dao);
		
		try{
			
			InventoryCheckResult result = inventoryCheckBus.getCheckResultById(checkid);
			taskid = result.getTaskid();
			result.setChecknum(Double.parseDouble(checknum));
			//result.setChecknetweight(Double.parseDouble(checknetweight));
			String strBackMsg = inventoryCheckBus.updateCheckResult(result);
			if(strBackMsg.equals("Y")){
				Logger.info("�û�["+strUserCode+"]�����̵�����:" + taskid + "�޸����̵������̵�������" + checknum);
			}
	    	
	    	strUrl = "/standard/jsp/inventory/check/kc_check_result_detail.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){

			Logger.error("�û�["+strUserCode+"]��������==>����̵�==>���̵�����:" + taskid + "�޸��̵���ʧ��:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�ر�
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecClose(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
	
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");

		String requestid = CommonTools.getStrToGbk(request.getParameter("requestid"));	//�̵�����ID

		String strUserCode = (String)httpsession.getAttribute("userCode");
		inventoryCheckBus = new InventoryCheckBusImpl(dao);
		
		try{
				
			String strBackMsg = inventoryCheckBus.closeCheckTasks(requestid);
			if(strBackMsg.equals("Y")){
				Logger.info("�û�["+strUserCode+"]���ر����̵����뵥:" + requestid);
			}
	    	
	    	strUrl = "/standard/jsp/inventory/check/kc_check_list.jsp";
			request.setAttribute("back_msg", strBackMsg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){

			Logger.error("�û�["+strUserCode+"]��������==>����̵�==>�ر����̵����뵥:" + requestid + "��" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ��ӡ�̵�����
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecPrintTasks(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {

		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");

		String requestid = CommonTools.getStrToGbk(request.getParameter("requestid"));	//�̵�����ID

		String strUserCode = (String)httpsession.getAttribute("userCode");
		inventoryCheckBus = new InventoryCheckBusImpl(dao);
		
		try{
				
			InventoryCheckRequest checkReq = inventoryCheckBus.getCheckReqById(requestid);
			if(checkReq.getCounttype().equals("1")){	//ä��
				strUrl = "/standard/jsp/inventory/check/kc_check_report1.jsp";
			}else{
				strUrl = "/standard/jsp/inventory/check/kc_check_report.jsp";
			}
	    	
			//List ls = inventoryCheckBus.getCheckTasks(requestid);
			//request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er){

			Logger.error("�û�["+strUserCode+"]��������==>����̵�==>��ӡ�̵�����ʧ��(�̵����뵥:" + requestid + ")" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
