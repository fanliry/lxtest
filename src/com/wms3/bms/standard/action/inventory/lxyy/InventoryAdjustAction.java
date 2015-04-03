package com.wms3.bms.standard.action.inventory.lxyy;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Transaction;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.pagination.CommonPagination;
import com.ricosoft.common.pagination.PagingTool;
import com.ricosoft.common.tools.CommonTools;
import com.ricosoft.common.tools.StrTools;
import com.ricosoft.entity.competenceMgr.SystemLogInfo;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.base.ICargoSpaceBus;
import com.wms3.bms.standard.business.base.IWhAreaBus;
import com.wms3.bms.standard.business.base.impl.CargoSpaceBusImpl;
import com.wms3.bms.standard.business.base.impl.WhAreaBusImpl;
import com.wms3.bms.standard.business.inventory.IInventoryCheckResultBus;
import com.wms3.bms.standard.business.inventory.IInventoryNeedCheckBus;
import com.wms3.bms.standard.business.inventory.InventoryAdjustBus;
import com.wms3.bms.standard.business.inventory.impl.InventoryAdjustBusImpl;
import com.wms3.bms.standard.business.inventory.impl.InventoryCheckResultBusImpl;
import com.wms3.bms.standard.business.inventory.impl.InventoryNeedCheckBusImp;
import com.wms3.bms.standard.constant.StandardConstant;
import com.wms3.bms.standard.entity.base.BaseCargospace;
import com.wms3.bms.standard.entity.base.BaseWharea;
import com.wms3.bms.standard.entity.inventory.InventoryNeedcheck;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryAdjustDetail;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryAdjustHeader;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;
import com.wms3.bms.standard.entity.job.lxyy.InoutJob;
import com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail;



/**
 *  ����:��������
 * @author yao
 *
 */
public class InventoryAdjustAction extends AbstractAction
{
	protected InventoryAdjustBus inventoryadjustbus;
	protected int maxLine = 5;			//��ҳ��ʾ��������
	/**
	 * ��ѯ��������
	 */
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		//״̬
		String strStatus = CommonTools.getStrToGb2312(request.getParameter("status"));
		//�ֿ�id
		String strWarehouseid = CommonTools.getStrToGb2312(request.getParameter("warehouseid"));
		//��������
		String strAdjusttype = CommonTools.getStrToGb2312(request.getParameter("adjusttype"));
		//����ԭ��
		String strReasoncode = CommonTools.getStrToGb2312(request.getParameter("reasoncode"));
		inventoryadjustbus = new InventoryAdjustBusImpl(dao);
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
		if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }

		try
		{	
			List ls = null;
			//��ѯ��SQL���
			String strQuerySQL = inventoryadjustbus.getAdjustHeaderSQL(strWarehouseid,strStatus,strAdjusttype,strReasoncode);
			//��ѯ��ɫ�ܼ�¼����SQL���
			String strCountSQL = "select count(ah) "+strQuerySQL;
			String strUrl = "/standard/jsp/inventory/adjust/kc_adjustheader_list.jsp";	
			PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, strUrl ,1, maxLine);
			//��ѯ���
			ls = pt.getLsResult();
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);	

			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������==>������==>����������ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * ����:����������ϸ��ѯ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		
		//id
		String strId = CommonTools.getStrToGb2312(request.getParameter("id"));
		InventoryAdjustBusImpl inventoryadjustbus1 = new InventoryAdjustBusImpl(dao);
		
		try
		{	
			List ls = null;
			//��ѯ��SQL���
			String strQuerySQL = inventoryadjustbus1.getAdjustDetailSQL(strId);
			//��ѯ��ɫ�ܼ�¼����SQL���
			String strCountSQL = inventoryadjustbus1.getAdjustDetailCountSQL(strId);

			String strUrl = "/standard/jsp/inventory/adjust/kc_adjustdetail_list.jsp";
			
			PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, strUrl ,1, maxLine);
			//��ѯ���
			ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("inboundPage1", pt);	

			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������==>������==>����������ϸ��ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * ����:���ӿ�������
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdd(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		
		//״̬
		String strStatus = CommonTools.getStrToGb2312(request.getParameter("status"));
		//�ֿ�id
		String strWarehouseid = CommonTools.getStrToGb2312(request.getParameter("warehouseid"));
		//��������
		String strAdjusttype = CommonTools.getStrToGb2312(request.getParameter("adjusttype"));
		//����ԭ��
		String strReasoncode = CommonTools.getStrToGb2312(request.getParameter("reasoncode"));
		//ԭ��
		String strReasondiscr = CommonTools.getStrToGb2312(request.getParameter("reasondiscr"));
		//�û�
		String strUserCode = request.getSession().getAttribute("userCode").toString();
	
		try
		{
			
			InventoryAdjustHeader adjust = new InventoryAdjustHeader();
			//��������
            String strInvoiceNo = SeqTool.getID("TZ", dao);     
			adjust.setWarehouseid(strWarehouseid);
			adjust.setStatus(strStatus);
			adjust.setAdjusttype(strAdjusttype);
			adjust.setReasoncode(strReasoncode);
			adjust.setReasondiscr(strReasondiscr);
			adjust.setAdjustid(strInvoiceNo);
			adjust.setCreatemanid(strUserCode);
			adjust.setCreatetime(StrTools.getCurrDateTime(5));
			dao.save(adjust);
			
			//��־
			SystemLogInfo sysLog = new SystemLogInfo();
			String strUserName = request.getSession().getAttribute("userName").toString();
			sysLog.setM_strLogCode(strUserCode);
			sysLog.setM_strLogName(strUserName);
			sysLog.setM_strModuleName("������=>������");
			sysLog.setM_strContent("��ӿ��������ɹ�");
			sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
			dao.save(sysLog);
		
			//ˢ�� 
			PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
			List ls = null;
			String strUrl = null;
			if(pt != null)
			{
				//�����ܼ�¼��
				int rows = pt.getM_iCountRow()+1;
				pt.setPagingParameter(rows, 1, pt.getM_iMaxRow());
				ls = CommonPagination.getPagingList(dao, pt);
				strUrl = pt.getM_strUrl();
			}
			if(strUrl == null)
			{
				strUrl = "/standard/jsp/inventory/adjust/kc_adjustheader_list.jsp";
			}
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������==>������==>������������ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * ����:ɾ����������
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecDelete(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		try
		{

			//����(����ö��ŷָ�)
			String strDeleteStr = CommonTools.getStrToGb2312(request.getParameter("deleteStr"));
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			ICargoSpaceBus csBus = new CargoSpaceBusImpl(dao);
			PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
			List ls = null;
			String strUrl = null;
			if(strDeleteStr != null && strDeleteStr.length() > 0)
			{
				String [] roleIds = strDeleteStr.split(",");
				InventoryAdjustHeader adjust=null;
				for(int i=0; i<roleIds.length; i++)
				{
					String strTemp =  roleIds[i];
					//ɾ��
					adjust=inventoryadjustbus2.getAdjustHeaderToId(strTemp);
					if(adjust!=null && adjust.getStatus().equals("0")){ // ��������Ϊ����״̬
						List<InventoryAdjustDetail> lsDetail = inventoryadjustbus2.getAdjustDetailListToId(strTemp);
						List<InventoryStorage> lsinventory = new ArrayList<InventoryStorage>();
						List<BaseCargospace> lscargospace = new ArrayList<BaseCargospace>();
						if(lsDetail != null)
						{
							for(int j = 0; j < lsDetail.size(); j++)
							{
								    InventoryAdjustDetail detail = (InventoryAdjustDetail)lsDetail.get(j);
								    if (detail.getType().equals("2")) {
										BaseCargospace cargospace = csBus.getCargoSpaceById(detail.getCargo_space_id());
										cargospace.setCsstatus("1");
										lscargospace.add(cargospace);
										//dao.update(cargospace);
									}
									//���
									InventoryStorage inventory = inventoryadjustbus2.getInventoryInfoToId(detail.getInventoryid());
									if(inventory != null)
									{
										inventory.setStatus("0");//δ����
									}
									lsinventory.add(inventory);
							}
						}
						
						inventoryadjustbus2.deletejustInvoice(lscargospace,lsDetail, lsinventory, adjust);
						//��־
						SystemLogInfo sysLog = new SystemLogInfo();
						String strUserCode = request.getSession().getAttribute("userCode").toString();
						String strUserName = request.getSession().getAttribute("userName").toString();
						sysLog.setM_strLogCode(strUserCode);
						sysLog.setM_strLogName(strUserName);
						sysLog.setM_strModuleName("������=>��������");
						sysLog.setM_strContent("ɾ�����������ɹ�:����Ϊ"+strTemp);
						sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
						dao.save(sysLog);				
						request.setAttribute("back_msg", "Y");
					}else{
						request.setAttribute("back_msg", "���������Ǵ���״̬");
					}	
				}

				if(pt != null)
				{
					//�����ܼ�¼��  
					int rows = pt.getM_iCountRow()-roleIds.length;
					pt.setPagingParameter(rows, 1, pt.getM_iMaxRow());
					ls = CommonPagination.getPagingList(dao, pt);
					strUrl = pt.getM_strUrl();
				}
			}
			if(strUrl == null)
			{
				strUrl = "/standard/jsp/inventory/adjust/kc_adjustheader_list.jsp";
			}
			
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
	
		}catch(Exception er)
		{
			Logger.error("������==>������==>��������ɾ��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * ����:����ID��ÿ�������
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecQuery(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String strId = CommonTools.getStrToGb2312(request.getParameter("id"));
		try
		{
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			InventoryAdjustHeader adjust = inventoryadjustbus2.getAdjustHeaderToId(strId);
			if (adjust.getStatus()!=null&&adjust.getStatus().trim().equals("0")) {
				request.setAttribute("adjust", adjust);
			}else {
				request.setAttribute("back_msg", "ֻ�д���״̬�������ӵ�������ϸ");
			}
			request.setAttribute("adjust", adjust);
			request.getRequestDispatcher("/standard/jsp/inventory/adjust/kc_adjust_header_update.jsp").forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������==>������==>����ID��ÿ�������ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}	
		return null;
	}
	/**
	 * ����:���¿�������
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecUpdate(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
  
		//ID
		String strId = CommonTools.getStrToGb2312(request.getParameter("id"));
		//ԭ�����
		String strReasoncode = CommonTools.getStrToGb2312(request.getParameter("reasoncode"));
		//ԭ��
		String strReasondiscr = CommonTools.getStrToGb2312(request.getParameter("reasondiscr"));
		try
		{
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			InventoryAdjustHeader adjust= inventoryadjustbus2.getAdjustHeaderToId(strId);
			if(adjust != null)
			{
				adjust.setReasoncode(strReasoncode);
				adjust.setReasondiscr(strReasondiscr);
				adjust.setAdjusttime(StrTools.getCurrDateTime(5));
				inventoryadjustbus2.updateAdjustHeader(adjust);
			}
			
			//��־
			SystemLogInfo sysLog = new SystemLogInfo();
			String strUserCode = request.getSession().getAttribute("userCode").toString();
			String strUserName = request.getSession().getAttribute("userName").toString();
			sysLog.setM_strLogCode(strUserCode);
			sysLog.setM_strLogName(strUserName);
			sysLog.setM_strModuleName("������=>��������");
			sysLog.setM_strContent("�޸Ŀ��������ɹ�");
			sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
			dao.save(sysLog);
			
			//ˢ�� 
			PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
			List ls = null;
			String strUrl = null;
			if(pt != null)
			{
				ls = CommonPagination.getPagingList(dao, pt);
				
				strUrl = pt.getM_strUrl();
			}
			if(strUrl == null)
			{
				strUrl = "/standard/jsp/inventory/adjust/kc_adjustheader_list.jsp";
			}
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������==>������==>���������޸�ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}

	/**
	 * ����:����ѯ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecInventoryQuery(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		
		//�ͻ�
		String strCustomerId = CommonTools.getStrToGb2312(request.getParameter("customerid"));
		//��Ʒ
		String strPackageId = CommonTools.getStrToGb2312(request.getParameter("packageid"));
		//��������
		String strLotid = CommonTools.getStrToGb2312(request.getParameter("lotid"));
		//��������
		String strTraycode = CommonTools.getStrToGb2312(request.getParameter("tray_code"));
		//��λ
		String strLocid = CommonTools.getStrToGb2312(request.getParameter("locid"));
		InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
		
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
		if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
		try
		{
			List ls = null;
			//��ѯ��SQL���
			String strQuerySQL = inventoryadjustbus2.getInventoryToSQL(strCustomerId,strPackageId,strLotid,strTraycode,strLocid);
			//��ѯ��ɫ�ܼ�¼����SQL���
			String strCountSQL = inventoryadjustbus2.getInventoryToCountSQL(strCustomerId,strPackageId,strLotid,strTraycode,strLocid);

			String strUrl = "/standard/jsp/inventory/adjust/kc_list_detail.jsp";
			PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, strUrl ,1, maxLine);
			//��ѯ���
			ls = pt.getLsResult();
			
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);	

			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������==>���ᡢ�ͷ�==>����ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}

		return null;
		
	}
	//****��������ϸ*******************************************************
	
	
	/**
	 * ����:�����̵��������ϸ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAddCheckDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);  
		//������ID
		String strId = CommonTools.getStrToGb2312(request.getParameter("id"));
		
		//�̵���
		String checkresult = CommonTools.getStrToGb2312(request.getParameter("checkresult"));
		
		//���ID
		String strInventoryid = CommonTools.getStrToGb2312(request.getParameter("inventoryid"));
		
		//�̵���ID
		String checkid = CommonTools.getStrToGb2312(request.getParameter("checkid"));
		
		//����޸ĵı�ע
		String strflag = CommonTools.getStrToGb2312(request.getParameter("flag"));
		//״̬
		//String strStatus = CommonTools.getStrToGb2312(request.getParameter("status"));
	
		try
		{
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			InventoryStorage inventory = inventoryadjustbus2.getInventoryInfoToId(strInventoryid);	//��ÿ��
			InventoryAdjustHeader adjustx= inventoryadjustbus2.getAdjustHeaderToId(strId);//��ȡ������
			ICargoSpaceBus csBus = new CargoSpaceBusImpl(dao);
			IWhAreaBus whareaBus = new WhAreaBusImpl(dao);
			IInventoryCheckResultBus checkResultBus = new InventoryCheckResultBusImpl(dao);
			BaseWharea wharea = null;
			BaseCargospace cargospace = null;
			BaseCargospace updatecs = null; //���»�λ�ı�ʶ�滻��
			InventoryCheckResult checkResult = null;//�̵�����
			
			List<InventoryAdjustDetail > adjustDetail = null;
			if(adjustx!=null&&adjustx.getStatus().equals("0")){//������Ϊ����״̬ricoExecAddDetailOutboundExce
				if(inventory!=null&&inventory.getStatus().equals("2")){//���Ϊ�����״̬
					adjustDetail = inventoryadjustbus2.getAdjustDetailListToId(adjustx.getAdjustid());
					if(!isCargoSpace(inventory.getCargoSpaceId(),adjustDetail)){
						cargospace = csBus.getCargoSpaceById(inventory.getCargoSpaceId());//��û�λ		
						InventoryAdjustDetail adjustDel = new InventoryAdjustDetail();
						adjustDel.setAdjustid(strId);//��������
						adjustDel.setStatus("1");//������״̬
						adjustDel.setWarehouseid(inventory.getWarehouseid());//�ֿ�
						adjustDel.setWh_area_id(inventory.getWhAreaId());//����
						adjustDel.setTlotinfo(inventory.getLotinfo());//����
						adjustDel.setTproductid(inventory.getProductid());//��Ʒid
						adjustDel.setTlotid(inventory.getLotid());//����id
						adjustDel.setTtraycode(inventory.getTraycode());//��������
						adjustDel.setSyspnum(inventory.getPnum());//�������
						adjustDel.setRealitypnum(Float.parseFloat(checkresult));//Ŀ������
						adjustDel.setCargo_space_id(inventory.getCargoSpaceId());//��λid
						adjustDel.setInventoryid(inventory.getInventoryid());//���id
						adjustDel.setCreatetime(StrTools.getCurrDateTime(5));//����ʱ��
						adjustDel.setAdjustdetailid(SeqTool.getDetailId(strId, "2"));
						adjustDel.setWarehouseid(inventory.getWarehouseid());//�ֿ�id
						adjustDel.setTprintdate(inventory.getProductdate());
						adjustDel.setType("1");//��ʾ��������
						//dao.save(adjustDel);
						
						//���ÿ��
						inventory.setStatus("3"); //������״̬״̬  0:δ���䣬1���ѷ��䣬2:������� 3:���� 4:��Ʒ������5���̵�       (�ݴ���״̬)
						//dao.update(inventory);						
						
						//���Ŀ�λ״̬
						wharea = whareaBus.getWhareaById(cargospace.getWhAreaId());//��ÿ���
						if (wharea.getwhAreaType()!=null && !wharea.getwhAreaType().equals("9")) {
							cargospace.setCsstatus("2");//��λ�������������ݴ���ʱ�����û�λ״̬Ϊ����λ״̬
							updatecs = cargospace;
						}
						//�����̵�����״̬Ϊ����
						
						checkResult = checkResultBus.getCheckResultById(checkid);
						checkResult.setStatus("2");// 1.�½���2.������3.���
						
						
						
						//��־
						SystemLogInfo sysLog = new SystemLogInfo();
						String strUserCode = request.getSession().getAttribute("userCode").toString();
						String strUserName = request.getSession().getAttribute("userName").toString();
						sysLog.setM_strLogCode(strUserCode);
						sysLog.setM_strLogName(strUserName);
						sysLog.setM_strModuleName("������=>������");
						sysLog.setM_strContent("��ӿ���������ϸ�ɹ�");
						sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
						//�ύ�����ݿ� 
						inventoryadjustbus2.addinvenUpdatecarsoAddAdjustDel(inventory, updatecs, adjustDel,checkResult,sysLog);
						
						request.setAttribute("back_msg", "Y");
					}else{
						request.setAttribute("back_msg", "��ϸ���Ѿ��иû�λ�������ٴ���ӣ�");
					}
				}else{
					request.setAttribute("back_msg", "���Ϊ�����״̬���������ϸ��");
				}
			}else{
				request.setAttribute("back_msg", "��������"+adjustx.getAdjustid()+"��Ϊ����״̬���������ϸ��");
			}
			
			//��ѯ��SQL���
			String strQuerySQL = inventoryadjustbus2.getAdjustDetailSQL(strId);
			strUrl = "/standard/jsp/inventory/adjust/kc_adjustdetail_list.jsp";
			List ls=dao.searchEntities(strQuerySQL);
			//��ѯ���
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������==>������==>�̵����������ϸ����ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
		
	/**
	 * ����:���ӿ���������ϸ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAddDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);  
		//������ID
		String strId = CommonTools.getStrToGb2312(request.getParameter("id"));
		//�ֿ�
		String strWarehouseId= CommonTools.getStrToGb2312(request.getParameter("warehouseid"));
		//����
		String strWhAreaid = CommonTools.getStrToGb2312(request.getParameter("whareaid"));
		//��λ
		String strCargospaceid = CommonTools.getStrToGb2312(request.getParameter("cargospaceid"));
		//��Ʒid
		String strPackage= CommonTools.getStrToGb2312(request.getParameter("packageid"));
	   //��������
		String strPrintdate = CommonTools.getStrToGb2312(request.getParameter("printdate"));
	    //��λ
		String strPunt = CommonTools.getStrToGb2312(request.getParameter("punit"));
	   //��������
		String strLotid = CommonTools.getStrToGb2312(request.getParameter("lotid"));
	   //������Ϣ
		String strLotinfo = CommonTools.getStrToGb2312(request.getParameter("lotinfo"));
	   //��������
		String strTraycode = CommonTools.getStrToGb2312(request.getParameter("traycode"));
	   //Ŀ����������
		String pnum = CommonTools.getStrToGb2312(request.getParameter("pnum"));
		//���ID
		String strInventoryid = CommonTools.getStrToGb2312(request.getParameter("inventoryid"));
		//����޸ĵı�ע
		String strflag = CommonTools.getStrToGb2312(request.getParameter("flag"));
		//״̬
		//String strStatus = CommonTools.getStrToGb2312(request.getParameter("status"));
	
		try
		{
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			InventoryStorage inventory = inventoryadjustbus2.getInventoryInfoToId(strInventoryid);	//��ÿ��
			InventoryAdjustHeader adjustx= inventoryadjustbus2.getAdjustHeaderToId(strId);
			ICargoSpaceBus csBus = new CargoSpaceBusImpl(dao);
			IWhAreaBus whareaBus = new WhAreaBusImpl(dao);
			BaseWharea wharea = null;
			BaseCargospace cargospace = null;
			BaseCargospace updatecs = null; //���»�λ�ı�ʶ�滻��
			List<InventoryAdjustDetail >adjustDetail = null;
			if(inventory != null && inventory.getStatus().equals("0"))  //�п�棬�ҿ��Ϊδ����״̬
			{
		        adjustDetail = inventoryadjustbus2.getAdjustDetailListToId(adjustx.getAdjustid());				
			     if(!isCargoSpace(inventory.getCargoSpaceId(),adjustDetail) || inventory.getCargoSpaceId().substring(3, 6).equals(StandardConstant.zcidkzl)){
				    cargospace = csBus.getCargoSpaceById(inventory.getCargoSpaceId());//��û�λ		
					if(adjustx.getStatus().equals("0")){ //����״̬�� 1.�����ϸ2�����Ŀ��״̬������״̬����3.��λ״̬������״̬���ݴ�ȥ���⣩
						InventoryAdjustDetail adjustDel = new InventoryAdjustDetail();
						adjustDel.setAdjustid(strId);//��������
						adjustDel.setStatus("1");//������״̬
						adjustDel.setWarehouseid(inventory.getWarehouseid());//�ֿ�
						adjustDel.setWh_area_id(inventory.getWhAreaId());//����
						adjustDel.setTlotinfo(strLotinfo);//����
						adjustDel.setTproductid(strPackage);//��Ʒid
						adjustDel.setTpunit(strPunt);//��λ
						adjustDel.setTlotid(strLotid);//����id
						adjustDel.setTtraycode(strTraycode);//��������
						adjustDel.setSyspnum(inventory.getPnum());//�������
						adjustDel.setRealitypnum(Double.parseDouble(pnum));//Ŀ������
						adjustDel.setCargo_space_id(inventory.getCargoSpaceId());//��λid
						adjustDel.setInventoryid(inventory.getInventoryid());//���id
						adjustDel.setCreatetime(StrTools.getCurrDateTime(5));//����ʱ��
						adjustDel.setAdjustdetailid(SeqTool.getDetailId(strId, "2"));
						adjustDel.setWarehouseid(inventory.getWarehouseid());//�ֿ�id
						adjustDel.setTprintdate(strPrintdate);
						
						adjustDel.setType("1");//��ʾ��������
						//dao.save(adjustDel);
						
						//���ÿ��
						inventory.setStatus("3"); //������״̬״̬  0:δ���䣬1���ѷ��䣬2:������� 3:���� 4:��Ʒ������5���̵�       (�ݴ���״̬)
						//dao.update(inventory);						
						
						//���Ŀ�λ״̬
						wharea = whareaBus.getWhareaById(cargospace.getWhAreaId());//��ÿ���
						/*if (wharea.getwhAreaType()!=null && !wharea.getwhAreaType().equals("9")) {
							cargospace.setCsstatus("2");//��λ�������������ݴ���ʱ�����û�λ״̬Ϊ����״̬
							updatecs = cargospace;
						}else{
							
						}*/
						
						//��־
						SystemLogInfo sysLog = new SystemLogInfo();
						String strUserCode = request.getSession().getAttribute("userCode").toString();
						String strUserName = request.getSession().getAttribute("userName").toString();
						sysLog.setM_strLogCode(strUserCode);
						sysLog.setM_strLogName(strUserName);
						sysLog.setM_strModuleName("������=>������");
						sysLog.setM_strContent("��ӿ���������ϸ�ɹ�");
						sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
						//�ύ�����ݿ� 
						inventoryadjustbus2.addinvenUpdatecarsoAddAdjustDel1(inventory, updatecs, adjustDel,sysLog);
						
						request.setAttribute("back_msg", "Y");
					}else{
						request.setAttribute("back_msg", "ֻ�д���״̬�������ӵ�������ϸ");
					}
				}else {
					request.setAttribute("back_msg", "��ϸ���Ѿ��иû�λ�������ٴ���ӣ�");
				}
			}else{//û�п�����
				if (strflag!=null && strflag.equals("3")) {
							adjustDetail = inventoryadjustbus2.getAdjustDetailListToId(adjustx.getAdjustid());
							if (!isCargoSpace(strCargospaceid, adjustDetail)) {
							cargospace = csBus.getCargoSpaceById(strCargospaceid);//��û�λ
							if (cargospace.getCsstatus()!=null && cargospace.getCsstatus().equals("1")) {
								InventoryAdjustDetail adjustDetail1 = new InventoryAdjustDetail();
								adjustDetail1.setAdjustdetailid(SeqTool.getDetailId(strId, "1"));
								adjustDetail1.setAdjustid(strId);//������id
								adjustDetail1.setTlotid(strLotid);//��������
								adjustDetail1.setTlotinfo(strLotinfo);//������Ϣ
								adjustDetail1.setTprintdate(strPrintdate);//��������
								adjustDetail1.setTproductid(strPackage);//��Ʒ
								adjustDetail1.setRealitypnum(Double.valueOf(pnum));
								adjustDetail1.setTpunit(strPunt);//��λ
								adjustDetail1.setTtraycode(strTraycode);//��������
								adjustDetail1.setWarehouseid(strWarehouseId);//�ֿ�
								adjustDetail1.setWh_area_id(strWhAreaid);//����
								adjustDetail1.setCargo_space_id(strCargospaceid);//��λ
								adjustDetail1.setStatus("1");
								adjustDetail1.setType("2");//��ʾ�������
								//dao.save(adjustDetail);
		
								//���Ŀ�λ״̬
								wharea = whareaBus.getWhareaById(cargospace.getWhAreaId());//��ÿ���
								if (wharea.getwhAreaType()!=null && !wharea.getwhAreaType().equals("9")) {
									cargospace.setCsstatus("2");//��λ�������������ݴ���ʱ�����û�λ״̬Ϊ����״̬
									updatecs = cargospace;
								}
								//dao.update(cargospace);
								
								//��־
								SystemLogInfo sysLog = new SystemLogInfo();
								String strUserCode = request.getSession().getAttribute("userCode").toString();
								String strUserName = request.getSession().getAttribute("userName").toString();
								sysLog.setM_strLogCode(strUserCode);
								sysLog.setM_strLogName(strUserName);
								sysLog.setM_strModuleName("������=>������");
								sysLog.setM_strContent("��ӿ���������ϸ�ɹ�");
								sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
								//dao.save(sysLog);
								//�ύ�����ݿ�
								inventoryadjustbus2.addinvenUpdatecarsoAddAdjustDel1(inventory,updatecs, adjustDetail1,sysLog);		
								
								request.setAttribute("back_msg", "Y");					
							
							}else {
								request.setAttribute("back_msg", "ֻ��δ����״̬�Ļ�λ���������Ϣ��");
							}		
						}else {
							request.setAttribute("back_msg", "��ϸ���Ѿ�����˶Ըû�λ�ĵ����������ٴ���ӣ�");
						}
					}else {
					        request.setAttribute("back_msg", "ֻ�п��״̬Ϊδ����״̬ʱ�����ɽ�����ӵ�����");
				    }
				
			}
		
			//��ѯ��SQL���
			String strQuerySQL = inventoryadjustbus2.getAdjustDetailSQL(strId);
			strUrl = "/standard/jsp/inventory/adjust/kc_adjustdetail_list.jsp";
			List ls=dao.searchEntities(strQuerySQL);
			//��ѯ���
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������==>������==>����������ϸ����ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:���ӳ����쳣��������ϸ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAddDetailOutboundExce(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);  
		//������ID
		String strId = CommonTools.getStrToGb2312(request.getParameter("id"));
		//�쳣��ID
		String strInventoryid = CommonTools.getStrToGb2312(request.getParameter("inventoryid"));
	
		try
		{
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			//��ȡ�����쳣��
			InventoryNeedcheck inventoryNC = inventoryadjustbus2.getInventoryNeedcheckInfoById(strInventoryid);
			
			//��ȡ������ͷ
			InventoryAdjustHeader adjustx= inventoryadjustbus2.getAdjustHeaderToId(strId);
			
			//��ȡ��棬�����쳣���Ļ�λ
			InventoryStorage inventoryStorage = inventoryadjustbus2.getInventoryStorageInfoByCargoSpaceId(inventoryNC.getCargoSpaceId());
			
			ICargoSpaceBus csBus = new CargoSpaceBusImpl(dao);
			IWhAreaBus whareaBus = new WhAreaBusImpl(dao);
			BaseWharea wharea = null;
			BaseCargospace cargospace = null;
			BaseCargospace updatecs = null; //���»�λ�ı�ʶ�滻��
			List<InventoryAdjustDetail > adjustDetail = null;
			if(inventoryNC != null &&inventoryNC.getStatus().equals("0")&&inventoryNC.getInouttype().equals("2")){//ֻ���½�״̬�����ǳ����쳣�ĳ����쳣�������ӵ�����ϸ
				if(adjustx.getStatus().equals("0")){// ֻ�д���״̬�ĵ��������������ϸ 0������״̬ 1�����״̬ 3�����״̬
					adjustDetail = inventoryadjustbus2.getAdjustDetailListToId(adjustx.getAdjustid());	//��ȡ����������
					if(inventoryStorage != null&&inventoryStorage.getStatus().equals("0")){//���Ϊδ����״̬
					if(!isCargoSpace(inventoryStorage.getCargoSpaceId(),adjustDetail)){//�жϸû�λ�Ƿ��Ѿ�����
						
							//�޸ĳ����쳣��
							inventoryNC.setStatus("1");
							inventoryNC.setHandleflag("Y");
							//inventoryNC.setHandletime(StrTools.getCurrDateTime(5));
							
							//���ӵ�������ϸ
							InventoryAdjustDetail adjustDel = new InventoryAdjustDetail();
							adjustDel.setAdjustid(strId);//��������
							adjustDel.setStatus("1");//��������ϸ״̬ ��1��δ���� 2���ѵ�����
							adjustDel.setInventoryid(inventoryNC.getNeedcheckid());//�����쳣��ID
							adjustDel.setWarehouseid(inventoryNC.getWarehouseid());//�ֿ�
							adjustDel.setWh_area_id(inventoryStorage.getWhAreaId());//����
							adjustDel.setTlotinfo(inventoryStorage.getLotinfo());//����
							adjustDel.setTlotid(inventoryStorage.getLotid());//����id
							adjustDel.setTproductid(inventoryStorage.getProductid());//��Ʒid
							adjustDel.setCargo_space_id(inventoryNC.getCargoSpaceId());//��λ
							adjustDel.setTtraycode(inventoryStorage.getTraycode());//��������
							adjustDel.setTpunit(inventoryStorage.getPunit());//��λ
							
							adjustDel.setSyspnum(inventoryStorage.getPnum());//�������
							adjustDel.setRealitypnum(0);//Ŀ������
							adjustDel.setCreatetime(StrTools.getCurrDateTime(5));//����ʱ��
							adjustDel.setAdjustdetailid(SeqTool.getDetailId(strId, "2"));
							adjustDel.setTprintdate(inventoryStorage.getProductdate());
							adjustDel.setType("1");//��ʾ��������
							
							//�޸Ŀ��״̬Ϊ����
							inventoryStorage.setStatus("3");//0:δ���䣬1���ѷ��䣬2:������� 3:���� 4:��Ʒ������5���̵�       (�ݴ���״̬)
							
							
							
							//��־
							SystemLogInfo sysLog = new SystemLogInfo();
							String strUserCode = request.getSession().getAttribute("userCode").toString();
							String strUserName = request.getSession().getAttribute("userName").toString();
							sysLog.setM_strLogCode(strUserCode);
							sysLog.setM_strLogName(strUserName);
							sysLog.setM_strModuleName("������=>������");
							sysLog.setM_strContent("��ӿ���������ϸ�ɹ�");
							sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
							//�ύ�����ݿ� 
							inventoryadjustbus2.addinvenUpdatecarsoAddAdjustDeloutboundExce(inventoryStorage,inventoryNC,adjustDel,sysLog);
							request.setAttribute("back_msg", "Y");
						
					}else{
						request.setAttribute("back_msg", "��ϸ���Ѿ��иû�λ�������ٴ���ӣ�");
					}
					}else{
						request.setAttribute("back_msg", "û�п�λ��"+inventoryNC.getCargoSpaceId()+"����Ӧ�Ŀ����߿��Ϊδ����״̬�ſɴ�����������ϸ");
					}
				}else{
						request.setAttribute("back_msg", "ֻ�е������ڴ���״̬�������ӵ�������ϸ");
				}
			}else{
				request.setAttribute("back_msg", "�������쳣�������쳣��ֻ���ڴ���״̬��Ϊ�����쳣ʱ�������ӵ�������ϸ");
			}
			//��ѯ��SQL���
			String strQuerySQL = inventoryadjustbus2.getAdjustDetailSQL(strId);
			strUrl = "/standard/jsp/inventory/adjust/kc_adjustdetail_list.jsp";
			List ls=dao.searchEntities(strQuerySQL);
			//��ѯ���
			request.setAttribute("inventoryNCId", strInventoryid);
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������==>������==>����������ϸ����ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:��������쳣��������ϸ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAddDetailInboundExce(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);  
		//������ID
		String strId = CommonTools.getStrToGb2312(request.getParameter("id"));
		//�쳣��ID
		String strInventoryid = CommonTools.getStrToGb2312(request.getParameter("inventoryid"));
		//��ƷID
		String productid = CommonTools.getStrToGb2312(request.getParameter("productid"));
		//��������
		String printDate = CommonTools.getStrToGb2312(request.getParameter("printDate"));
		//��������
		String traycode = CommonTools.getStrToGb2312(request.getParameter("traycode"));
		//��λ
		String punit = CommonTools.getStrToGb2312(request.getParameter("punit"));
		//�������
		String pnum = CommonTools.getStrToGb2312(request.getParameter("pnum"));
		//��������
		String lotid = CommonTools.getStrToGb2312(request.getParameter("lotid"));
		//������Ϣ
		String lotinfo = CommonTools.getStrToGb2312(request.getParameter("lotinfo"));
		//�Ƿ�������
		String isSplit = CommonTools.getStrToGb2312(request.getParameter("isSplit"));
	
		try
		{
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			//��ȡ����쳣��
			InventoryNeedcheck inventoryNC = inventoryadjustbus2.getInventoryNeedcheckInfoById(strInventoryid);
			//�ֿ�ID
			String warehouseid = inventoryNC.getWarehouseid();
			//��λID
			String cargospaceid = inventoryNC.getCargoSpaceId();
		
			//��ȡ��λ���󣬸�����ID
			ICargoSpaceBus csBus = new CargoSpaceBusImpl(dao);
			BaseCargospace cargospace = csBus.getCargoSpaceById(cargospaceid);
			
			//��ȡ�������󣬸��ݿ�λid�Ͳֿ�id
			IWhAreaBus whareaBus = new WhAreaBusImpl(dao);
			String whareaid = csBus.getWhAreaIdByWarehouseidAndCargospaceId(warehouseid, cargospaceid);
//			BaseWharea wharea = whareaBus.getWhareaById(whareaid);
			
//			//��ȡ������󣬸���
//			IAlleyBus alleybus = new AlleyBusImpl(dao);
//			String alleyid = csBus.getAlleyIdByWarehouseidAndCargospaceId(inventoryNC.getWarehouseid(), inventoryNC.getCargoSpaceId());
//			BaseAlley alley = alleybus.getAlleyById(alleyid);
			List<InventoryAdjustDetail > adjustDetail = null;

			//��ȡ������ͷ
			InventoryAdjustHeader adjustx= inventoryadjustbus2.getAdjustHeaderToId(strId);
			if(inventoryNC != null &&inventoryNC.getStatus().equals("0")&&inventoryNC.getInouttype().equals("1")){//ֻ���½�״̬����������쳣������쳣�������ӵ�����ϸ
				if(adjustx.getStatus().equals("0")){// ֻ�д���״̬�ĵ��������������ϸ 0������״̬ 1�����״̬ 3�����״̬
					adjustDetail = inventoryadjustbus2.getAdjustDetailListToId(adjustx.getAdjustid());	//��ȡ����������
					if(!isCargoSpace(cargospaceid,adjustDetail)){//�жϸû�λ�Ƿ��Ѿ���������ϸ
						//�޸ĳ����쳣��
						inventoryNC.setStatus("1");// 0--�½�  1--���ڵ��� 2--�������
						inventoryNC.setHandleflag("Y");//�����ʶ Y,N
						
						//���ӵ�������ϸ
						InventoryAdjustDetail adjustDel = new InventoryAdjustDetail();
						adjustDel.setAdjustid(strId);//��������
						adjustDel.setStatus("1");//��������ϸ״̬ ��1��δ���� 2���ѵ�����
						adjustDel.setInventoryid(strInventoryid);//����쳣��ID
						adjustDel.setWarehouseid(warehouseid);//�ֿ�
						adjustDel.setWh_area_id(whareaid);//����
						adjustDel.setCargo_space_id(cargospaceid);//��λ
						adjustDel.setTlotid(lotid);//����id
						adjustDel.setTlotinfo(lotinfo);//����
						adjustDel.setTpunit(punit);//��λ
						adjustDel.setTproductid(productid);//��Ʒid
						
						adjustDel.setTtraycode(traycode);//��������
						adjustDel.setSyspnum(0);//�������
						adjustDel.setRealitypnum(Double.parseDouble(pnum));//Ŀ������
						adjustDel.setCreatetime(StrTools.getCurrDateTime(5));//����ʱ��
						adjustDel.setAdjustdetailid(SeqTool.getDetailId(strId, "2"));
						adjustDel.setTprintdate(printDate);//��Ʒ��������
						adjustDel.setType("1");//��ʾ��������
						
						
						//��־
						SystemLogInfo sysLog = new SystemLogInfo();
						String strUserCode = request.getSession().getAttribute("userCode").toString();
						String strUserName = request.getSession().getAttribute("userName").toString();
						sysLog.setM_strLogCode(strUserCode);
						sysLog.setM_strLogName(strUserName);
						sysLog.setM_strModuleName("������=>������");
						sysLog.setM_strContent("��ӿ���������ϸ�ɹ�");
						sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
						
						//�ύ�����ݿ� 
						inventoryadjustbus2.addinvenUpdatecarsoAddAdjustDeloutboundExce(inventoryNC,adjustDel,sysLog);
						request.setAttribute("back_msg", "Y");
						
					}else{
						request.setAttribute("back_msg", "��ϸ���Ѿ��иû�λ�������ٴ���ӣ�");
					}
					
				}else{
					request.setAttribute("back_msg", "ֻ�е������ڴ���״̬�������ӵ�������ϸ");
				}
			}else{
				request.setAttribute("back_msg", "������쳣�������쳣��ֻ���ڴ���״̬��Ϊ����쳣ʱ�������ӵ�������ϸ");
			}

			//��ѯ��SQL���
			String strQuerySQL = inventoryadjustbus2.getAdjustDetailSQL(strId);
			strUrl = "/standard/jsp/inventory/adjust/kc_adjustdetail_list.jsp";
			List ls=dao.searchEntities(strQuerySQL);
			//��ѯ���
			request.setAttribute("inventoryNCId", strInventoryid);
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������==>������==>����������ϸ����ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:ɾ���̵��������ϸ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecCheckDeleteDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		try
		{

			//������ID
			String strId = CommonTools.getStrToGb2312(request.getParameter("id"));
			//����(����ö��ŷָ�)
			String strDeleteStr = CommonTools.getStrToGb2312(request.getParameter("deleteStr"));
			
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			IInventoryCheckResultBus checkResultBus = new InventoryCheckResultBusImpl(dao);
			String strUrl = null;
			InventoryAdjustHeader adjust= inventoryadjustbus2.getAdjustHeaderToId(strId);
			if(strDeleteStr != null && strDeleteStr.length() > 0 && adjust.getStatus().equals("0")) //����״̬
			{
				String [] roleIds = strDeleteStr.split(",");
				for(int i=0; i<roleIds.length; i++)
				{
					//ɾ��
					String strTemp =  roleIds[i];
					//inventoryadjustbus2.deleteAdjustDetail(strTemp);
					InventoryAdjustDetail detail = inventoryadjustbus2.getAdjustDetailToId(strTemp);
					if(detail.getType()!=null && detail.getType().equals("1")){
						InventoryStorage inventory = inventoryadjustbus2.getInventoryInfoToId(detail.getInventoryid());
						inventory.setStatus("2"); //��ԭδ�����״̬ 0:δ���䣬1���ѷ��䣬2:������� 3:���� 4:��Ʒ������5���̵�       (�ݴ���״̬)
						InventoryCheckResult checkResult = checkResultBus.getCheckResultByInventoryId(detail.getInventoryid());
						checkResult.setStatus("1");// 1.�½���2.������3.���
						
						
						if(inventory!=null){
							dao.update(inventory);
						}
						if(checkResult!=null){
							dao.update(checkResult);
						}
						if(detail!=null){
							dao.delete(detail);
						}
						
					}else {
						ICargoSpaceBus csBus = new CargoSpaceBusImpl(dao);
						BaseCargospace cargospace = csBus.getCargoSpaceById(detail.getCargo_space_id());
						cargospace.setCsstatus("1");
						dao.update(cargospace);
						dao.delete(detail);
					}

				}
				//��־
				SystemLogInfo sysLog = new SystemLogInfo();
				String strUserCode = request.getSession().getAttribute("userCode").toString();
				String strUserName = request.getSession().getAttribute("userName").toString();
				sysLog.setM_strLogCode(strUserCode);
				sysLog.setM_strLogName(strUserName);
				sysLog.setM_strModuleName("������=>������");
				sysLog.setM_strContent("ɾ������������ϸ�ɹ�");
				sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
				dao.save(sysLog);
				request.setAttribute("back_msg", "Y");
				
			}else{
				request.setAttribute("back_msg", "ֻ�д���״̬����ɾ��");
			}
			//��ѯ��SQL���
			String strQuerySQL = inventoryadjustbus2.getAdjustDetailSQL(strId);
			strUrl = "/standard/jsp/inventory/adjust/kc_adjustdetail_list.jsp";
			List ls=dao.searchEntities(strQuerySQL);
			//��ѯ���
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
	
		}catch(Exception er)
		{
			Logger.error("������==>������==>����������ϸɾ��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:ɾ������������ϸ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecDeleteDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		try
		{

			//������ID
			String strId = CommonTools.getStrToGb2312(request.getParameter("id"));
			//����(����ö��ŷָ�)
			String strDeleteStr = CommonTools.getStrToGb2312(request.getParameter("deleteStr"));
			
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			IInventoryCheckResultBus checkResultBus = new InventoryCheckResultBusImpl(dao);
			String strUrl = null;
			InventoryAdjustHeader adjust= inventoryadjustbus2.getAdjustHeaderToId(strId);
			if(strDeleteStr != null && strDeleteStr.length() > 0 && adjust.getStatus().equals("0")) //����״̬
			{
				String [] roleIds = strDeleteStr.split(",");
				for(int i=0; i<roleIds.length; i++)
				{
					//ɾ��
					String strTemp =  roleIds[i];
					//inventoryadjustbus2.deleteAdjustDetail(strTemp);
					InventoryAdjustDetail detail = inventoryadjustbus2.getAdjustDetailToId(strTemp);
					if(detail.getType()!=null && detail.getType().equals("1")){
						//�޸Ŀ��
						InventoryStorage inventory = inventoryadjustbus2.getInventoryInfoToId(detail.getInventoryid());
						inventory.setStatus("0"); //��ԭδ�����״̬ 0:δ���䣬1���ѷ��䣬2:������� 3:���� 4:��Ʒ������5���̵�       (�ݴ���״̬)
						
						if(inventory!=null){
							dao.update(inventory);
						}
						
						if(detail!=null){
							dao.delete(detail);
						}
						
					}else {
						
						ICargoSpaceBus csBus = new CargoSpaceBusImpl(dao);
						BaseCargospace cargospace = csBus.getCargoSpaceById(detail.getCargo_space_id());
						cargospace.setCsstatus("1");
						dao.update(cargospace);
						dao.delete(detail);
					}

				}
				//��־
				SystemLogInfo sysLog = new SystemLogInfo();
				String strUserCode = request.getSession().getAttribute("userCode").toString();
				String strUserName = request.getSession().getAttribute("userName").toString();
				sysLog.setM_strLogCode(strUserCode);
				sysLog.setM_strLogName(strUserName);
				sysLog.setM_strModuleName("������=>������");
				sysLog.setM_strContent("ɾ������������ϸ�ɹ�");
				sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
				dao.save(sysLog);
				request.setAttribute("back_msg", "Y");
				
			}else{
				request.setAttribute("back_msg", "ֻ�д���״̬����ɾ��");
			}
			//��ѯ��SQL���
			String strQuerySQL = inventoryadjustbus2.getAdjustDetailSQL(strId);
			strUrl = "/standard/jsp/inventory/adjust/kc_adjustdetail_list.jsp";
			List ls=dao.searchEntities(strQuerySQL);
			//��ѯ���
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
	
		}catch(Exception er)
		{
			Logger.error("������==>������==>����������ϸɾ��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:ɾ������������ϸ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecInDeleteDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		IInventoryNeedCheckBus iinventoryNCBus = new InventoryNeedCheckBusImp(dao);
		try
		{

			//������ID
			String strId = CommonTools.getStrToGb2312(request.getParameter("id"));
			//����(����ö��ŷָ�)
			String strDeleteStr = CommonTools.getStrToGb2312(request.getParameter("deleteStr"));
			
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			
			String strUrl = null;
			InventoryAdjustHeader adjust= inventoryadjustbus2.getAdjustHeaderToId(strId);
			if(strDeleteStr != null && strDeleteStr.length() > 0 && adjust.getStatus().equals("0")) //����״̬
			{
				String [] roleIds = strDeleteStr.split(",");
				for(int i=0; i<roleIds.length; i++)
				{
					//ɾ��
					String strTemp =  roleIds[i];
					//inventoryadjustbus2.deleteAdjustDetail(strTemp);
					InventoryAdjustDetail detail = inventoryadjustbus2.getAdjustDetailToId(strTemp);
					
					//��ȡ��λ
					String cargospaceid = detail.getCargo_space_id();
					//���ݻ�λ��ȡ����쳣��
					InventoryNeedcheck inNeedcheck = iinventoryNCBus.getINeedcheckInfoByCargospaceId(cargospaceid);
					if(inNeedcheck!=null){//�Ƿ���N,Y��
						inNeedcheck.setHandleflag("N");
						inNeedcheck.setStatus("0");//״̬0--�½�1---����2--���
					}
					
					
					if(detail!=null && detail.getStatus().equals("1")){
						//ɾ������쳣��������ϸ
						if(detail!=null){
							dao.delete(detail);
						}
						//�޸�����쳣�����Ƿ���N��״̬0
						if(inNeedcheck!=null){
							dao.update(inNeedcheck);
						}
						
						
					}else{
						request.setAttribute("back_msg", "ֻ������쳣��������ϸΪδ����״̬����ɾ��");
					}

				}
				//��־
				SystemLogInfo sysLog = new SystemLogInfo();
				String strUserCode = request.getSession().getAttribute("userCode").toString();
				String strUserName = request.getSession().getAttribute("userName").toString();
				sysLog.setM_strLogCode(strUserCode);
				sysLog.setM_strLogName(strUserName);
				sysLog.setM_strModuleName("������=>������");
				sysLog.setM_strContent("ɾ������쳣��������ϸ�ɹ�");
				sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
				dao.save(sysLog);
				request.setAttribute("back_msg", "Y");
				
			}else{
				request.setAttribute("back_msg", "ֻ�е�����Ϊ����״̬����ɾ��");
			}
			//��ѯ��SQL���
			String strQuerySQL = inventoryadjustbus2.getAdjustDetailSQL(strId);
			strUrl = "/standard/jsp/inventory/adjust/kc_adjustdetail_list.jsp";
			List ls=dao.searchEntities(strQuerySQL);
			//��ѯ���
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
	
		}catch(Exception er)
		{
			Logger.error("������==>������==>����쳣��������ϸɾ��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:ɾ�������������ϸ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecOutDeleteDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		IInventoryNeedCheckBus iinventoryNCBus = new InventoryNeedCheckBusImp(dao);
		try
		{

			//������ID
			String strId = CommonTools.getStrToGb2312(request.getParameter("id"));
			//����(����ö��ŷָ�)
			String strDeleteStr = CommonTools.getStrToGb2312(request.getParameter("deleteStr"));
			
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			
			String strUrl = null;
			InventoryAdjustHeader adjust= inventoryadjustbus2.getAdjustHeaderToId(strId);
			if(strDeleteStr != null && strDeleteStr.length() > 0 && adjust.getStatus().equals("0")) //����״̬
			{
				String [] roleIds = strDeleteStr.split(",");
				for(int i=0; i<roleIds.length; i++)
				{
					//ɾ��
					String strTemp =  roleIds[i];
					//inventoryadjustbus2.deleteAdjustDetail(strTemp);
					InventoryAdjustDetail detail = inventoryadjustbus2.getAdjustDetailToId(strTemp);
					
						//���ݿ�λ�ҿ��
						InventoryStorage inventory = inventoryadjustbus2.getInventoryStorageInfoByCargoSpaceId(detail.getCargo_space_id());
						inventory.setStatus("0"); //��ԭδ�����״̬0:δ���䣬1���ѷ��䣬2:������� 3:���� 4:��Ʒ������5���̵�       (�ݴ���״̬)
						
						//��ȡ��λ
						String cargospaceid = detail.getCargo_space_id();
						//���ݻ�λ��ȡ����쳣��
						InventoryNeedcheck inNeedcheck = iinventoryNCBus.getINeedcheckInfoByCargospaceId(cargospaceid);
						if(inNeedcheck!=null){//�Ƿ���N,Y��
							inNeedcheck.setHandleflag("N");
							inNeedcheck.setStatus("0");//״̬0--�½�1---����2--���
						}
						
						//�޸ĳ����쳣��
						if(inNeedcheck!=null){
							dao.update(inNeedcheck);
						}
						//�޸Ŀ��״̬
						if(inventory!=null){
							dao.update(inventory);
						}
						//ɾ����ϸ
						if(detail!=null){
							dao.delete(detail);
						}
						
					

				}
				//��־
				SystemLogInfo sysLog = new SystemLogInfo();
				String strUserCode = request.getSession().getAttribute("userCode").toString();
				String strUserName = request.getSession().getAttribute("userName").toString();
				sysLog.setM_strLogCode(strUserCode);
				sysLog.setM_strLogName(strUserName);
				sysLog.setM_strModuleName("������=>������");
				sysLog.setM_strContent("ɾ�������������ϸ�ɹ�");
				sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
				dao.save(sysLog);
				request.setAttribute("back_msg", "Y");
				
			}else{
				request.setAttribute("back_msg", "ֻ�д���״̬����ɾ��");
			}
			//��ѯ��SQL���
			String strQuerySQL = inventoryadjustbus2.getAdjustDetailSQL(strId);
			strUrl = "/standard/jsp/inventory/adjust/kc_adjustdetail_list.jsp";
			List ls=dao.searchEntities(strQuerySQL);
			//��ѯ���
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
	
		}catch(Exception er)
		{
			Logger.error("������==>������==>����������ϸɾ��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	
	
	
	
	
	
	
	/**
	 * ����:����ID��ÿ���������ϸ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecQueryDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String strId = CommonTools.getStrToGb2312(request.getParameter("id"));
		try
		{
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			//BaseProductDaoImpl skuMgr = new BaseProductDaoImpl(dao);
			InventoryAdjustDetail detail = inventoryadjustbus2.getAdjustDetailToId(strId);
			//BaseProduct sku = skuMgr.getProductById(detail.getTproductid());	
			request.setAttribute("adjustdetail", detail);
			//request.setAttribute("sku", sku);
			strUrl = "/standard/jsp/inventory/adjust/kc_adjust_detail_update.jsp";
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������==>��������==>����ID��ÿ���������ϸʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}	
		return null;
	}
	/**
	 * ����:�޸Ŀ���������ϸ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecUpdateDetail(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		//��������ϸID
		String strId = CommonTools.getStrToGb2312(request.getParameter("detailId"));

		//Ŀ������
		String strToQty = CommonTools.getStrToGb2312(request.getParameter("tonum"));
		try
		{
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			InventoryAdjustDetail detail = inventoryadjustbus2.getAdjustDetailToId(strId);
			InventoryAdjustHeader header = inventoryadjustbus2.getAdjustHeaderToId(detail.getAdjustid());
			if(header.getStatus() != null && header.getStatus().equals("0")) //������ϸ״̬Ϊ����״̬
			{   
				detail.setRealitypnum(Double.valueOf(strToQty));	     
				detail.setCreatetime(StrTools.getCurrDateTime(5));
				dao.update(detail);
				request.setAttribute("back_msg", "Y");
			}else{
				request.setAttribute("back_msg", "ֻ�д���״̬�����޸�");
			}
			
			//��־
			SystemLogInfo sysLog = new SystemLogInfo();
			String strUserCode = request.getSession().getAttribute("userCode").toString();
			String strUserName = request.getSession().getAttribute("userName").toString();
			sysLog.setM_strLogCode(strUserCode);
			sysLog.setM_strLogName(strUserName);
			sysLog.setM_strModuleName("������=>������");
			sysLog.setM_strContent("�޸Ŀ���������ϸ�ɹ�");
			sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
			dao.save(sysLog);
			
			//��ѯ��SQL���
			String strQuerySQL = inventoryadjustbus2.getAdjustDetailSQL(strId);
			strUrl = "/standard/jsp/inventory/adjust/kc_adjustdetail_list.jsp";
			List ls=dao.searchEntities(strQuerySQL);
			//��ѯ���
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������==>������==>����������ϸ�޸�ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * ��������
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdjust(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);	
		String strDeleteStr = CommonTools.getStrToGb2312(request.getParameter("deleteStr"));
		try
		{
			PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
			List ls = null;
			String strUrl = null;
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			String strUserCode = request.getSession().getAttribute("userCode").toString();
			String strUserName = request.getSession().getAttribute("userName").toString();
			String backmeg = "";//������Ϣ
			if(strDeleteStr != null && strDeleteStr.length() > 0)
			{
				String [] roleIds = strDeleteStr.split(",");
				for(int i=0; i<roleIds.length; i++)
				{
					List<InventoryAdjustDetail> lsajustDetail = new ArrayList<InventoryAdjustDetail>();
					List<InventoryStorage> lsinventory = new ArrayList<InventoryStorage>();//�޸Ŀ��
					List<InventoryStorage> addkcls = new ArrayList<InventoryStorage>();//������漯��
					List<InoutJob> jobls = new ArrayList<InoutJob>();//��ҵ���ϣ�����ɣ�
					List<InoutJobdetail> jobdells = new ArrayList<InoutJobdetail>();//��ҵ��ϸ
					List<BaseCargospace> cargospacesls = new ArrayList<BaseCargospace>();	
					Object[] obj = null;
					//ID
					String strTemp =  roleIds[i];				
					InventoryAdjustHeader adjust= inventoryadjustbus2.getAdjustHeaderToId(strTemp);
					List lsDetail = inventoryadjustbus2.getAdjustDetailListToId(strTemp);
				if (adjust.getStatus()!=null && adjust.getStatus().trim().equals("1")) {//ֻ����˲��ܵ���
					if(lsDetail != null && lsDetail.size()>0)
					{
						for(int j = 0; j < lsDetail.size(); j++)
						{
							InventoryAdjustDetail detail = (InventoryAdjustDetail)lsDetail.get(j);	
							String status = detail.getStatus();
							if(status != null && status.trim().equals("1"))//δ���
							{
								String type = detail.getType();
								InoutJob jobObj = null;
								if (type!=null && type.trim().equals("2")) {//������棨����쳣�ã�
									obj = inventoryadjustbus2.addinvenForAdjust(detail);
									jobObj = (InoutJob)obj[0];
									jobls.add(jobObj);//�����ҵ
									jobdells.add((InoutJobdetail)obj[1]);//�����ҵ��ϸ
									addkcls.add((InventoryStorage)obj[2]);//��ӿ��
									cargospacesls.add((BaseCargospace)obj[3]);//��ӿ�λ
									//detail.setStatus("2");//���
									//lsajustDetail.add(detail);
								}else {//��������������̵㳣�ã�
									obj = inventoryadjustbus2.updateinvenForAdjust(detail);
									jobObj = (InoutJob)obj[0];
									jobls.add(jobObj);//�����ҵ
									jobdells.add((InoutJobdetail)obj[1]);//�����ҵ��ϸ
									lsinventory.add((InventoryStorage)obj[2]);//��ӿ��
									cargospacesls.add((BaseCargospace)obj[3]);
								}
								//���µ�������״̬������ʱ��
								detail.setJobid(jobObj.getJobid());
								detail.setStatus("2");//�ѵ���
								detail.setAdjusttime(StrTools.getCurrDateTime(5));//����ʱ��
								lsajustDetail.add(detail);						
							}
						}
						//�޸ĵ�����״̬��
						adjust.setAdjusttime(StrTools.getCurrDateTime(5));
						adjust.setStatus("3");//���״̬
						adjust.setAuditmanid(strUserCode);
						//��־
						SystemLogInfo sysLog = new SystemLogInfo();
						sysLog.setM_strLogCode(strUserCode);
						sysLog.setM_strLogName(strUserName);
						sysLog.setM_strModuleName("������=>��������");
						sysLog.setM_strContent("�������������ɹ�");
						sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
						//ִ�е������ύ�����ݿ�
						inventoryadjustbus2.createjustInvoice(adjust,lsajustDetail,lsinventory,addkcls,jobls,jobdells,cargospacesls,sysLog);
		        		Logger.info("�û���" + strUserCode + "�������˿��������ɹ������ݺţ�" + adjust.getAdjustid());
						
					}else {
						request.setAttribute("back_msg", "������:"+adjust.getAdjustid()+"û�пɵ�������Ϣ��");
					}
					
				  }else {
					backmeg+= strTemp+",";
					request.setAttribute("back_msg", "������:"+backmeg+"�ǲ������״̬�����ܽ��е�����");
				}

				    
			 }
								
				if(pt != null)
				{
					//�����ܼ�¼��  
					int rows = pt.getM_iCountRow();
					pt.setPagingParameter(rows, 1, pt.getM_iMaxRow());
					
					ls = CommonPagination.getPagingList(dao, pt);
					
					strUrl = pt.getM_strUrl();
				}
			}
			if(strUrl == null)
			{
				strUrl = "/jsp/inventory/inventoryAdjustBatch_list_header.jsp";
			}
			
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);	
			
		}catch(Exception er)
		{
			Logger.error("������==>������==>��������ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	
	/**
	 * �������������쳣������
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdjustOutboundExce(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);	
		String strDeleteStr = CommonTools.getStrToGb2312(request.getParameter("deleteStr"));
		try
		{
			PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
			List ls = null;
			String strUrl = null;
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			ICargoSpaceBus cargoSpaceBus = new CargoSpaceBusImpl(dao);
		
			String strUserCode = request.getSession().getAttribute("userCode").toString();
			String strUserName = request.getSession().getAttribute("userName").toString();
			String backmeg = "";//������Ϣ
			if(strDeleteStr != null && strDeleteStr.length() > 0)
			{
				String [] roleIds = strDeleteStr.split(",");
				for(int i=0; i<roleIds.length; i++)
				{
					List<InventoryAdjustDetail> lsajustDetail = new ArrayList<InventoryAdjustDetail>();
					List<InventoryStorage> lsinventory = new ArrayList<InventoryStorage>();//ɾ�����
//					List<InventoryStorage> addkcls = new ArrayList<InventoryStorage>();//������漯��
//					List<InoutJob> jobls = new ArrayList<InoutJob>();//��ҵ���ϣ�����ɣ�
//					List<InoutJobdetail> jobdells = new ArrayList<InoutJobdetail>();//��ҵ��ϸ
					List<BaseCargospace> cargospacesls = new ArrayList<BaseCargospace>();	//�޸Ļ�λ״̬
					List<InventoryNeedcheck> lsinventoryNeedcheck = new ArrayList<InventoryNeedcheck>();	//�޸��쳣��״̬
					
					Object[] obj = null;
					//ID
					String strTemp =  roleIds[i];	
					
					InventoryAdjustHeader adjust= inventoryadjustbus2.getAdjustHeaderToId(strTemp);//��ȡ������ͷ
					List lsDetail = inventoryadjustbus2.getAdjustDetailListToId(strTemp);//��ȡ��������ϸ
				if (adjust.getStatus()!=null && adjust.getStatus().trim().equals("1")) {//ֻ����˲��ܵ���
					if(lsDetail != null && lsDetail.size()>0)
					{
						for(int j = 0; j < lsDetail.size(); j++)
						{
							InventoryAdjustDetail detail = (InventoryAdjustDetail)lsDetail.get(j);	
							String status = detail.getStatus();
							
							if(status != null && status.trim().equals("1"))//δ���
							{
								//������Ӧ�Ĵ��
								InventoryStorage inventoryStorage = inventoryadjustbus2.getInventoryStorageInfoByCargoSpaceId(detail.getCargo_space_id());
								lsinventory.add(inventoryStorage);
								//������Ӧ�Ļ�λ
								BaseCargospace baseCargospace = cargoSpaceBus.getCargoSpaceById(detail.getCargo_space_id());
								baseCargospace.setCsstatus("1");//�޸�Ϊ�ջ�λ
								cargospacesls.add(baseCargospace);
								
								//���µ�������״̬������ʱ��
								//detail.setJobid(jobObj.getJobid());
								detail.setStatus("2");//�ѵ���
								detail.setAdjusttime(StrTools.getCurrDateTime(5));//����ʱ��
								lsajustDetail.add(detail);		
								
								//�����쳣��
								//��ȡ�쳣��
								InventoryNeedcheck inventoryNeedcheck = inventoryadjustbus2.getInventoryNeedcheckInfoById(detail.getInventoryid());
								if(inventoryNeedcheck!=null){
									inventoryNeedcheck.setStatus("2");//����Ϊ���״̬
									lsinventoryNeedcheck.add(inventoryNeedcheck);
								}
								
								
								
							}
						}
						//�޸ĵ�����״̬��
						adjust.setAdjusttime(StrTools.getCurrDateTime(5));
						adjust.setStatus("3");//���״̬
						adjust.setAuditmanid(strUserCode);
						//��־
						SystemLogInfo sysLog = new SystemLogInfo();
						sysLog.setM_strLogCode(strUserCode);
						sysLog.setM_strLogName(strUserName);
						sysLog.setM_strModuleName("������=>��������");
						sysLog.setM_strContent("�������������ɹ�");
						sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
						//ִ�е������ύ�����ݿ�
						inventoryadjustbus2.createjustInvoice(adjust,lsajustDetail,lsinventory,cargospacesls,lsinventoryNeedcheck,sysLog);
						
		        		Logger.info("�û���" + strUserCode + "�������˿��������ɹ������ݺţ�" + adjust.getAdjustid());
						
					}else {
						request.setAttribute("back_msg", "������:"+adjust.getAdjustid()+"û�пɵ�������Ϣ��");
					}
					
				  }else {
					backmeg+= strTemp+",";
					request.setAttribute("back_msg", "������:"+backmeg+"�ǲ������״̬�����ܽ��е�����");
				}

				    
			 }
								
				if(pt != null)
				{
					//�����ܼ�¼��  
					int rows = pt.getM_iCountRow();
					pt.setPagingParameter(rows, 1, pt.getM_iMaxRow());
					
					ls = CommonPagination.getPagingList(dao, pt);
					
					strUrl = pt.getM_strUrl();
				}
			}
			if(strUrl == null)
			{
				strUrl = "/jsp/inventory/inventoryAdjustBatch_list_header.jsp";
			}
			
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);	
			
		}catch(Exception er)
		{
			Logger.error("������==>������==>��������ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	
	
	
	/**
	 * ������������쳣������
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdjustInboundExce(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);	
		String strDeleteStr = CommonTools.getStrToGb2312(request.getParameter("deleteStr"));
		try
		{
			PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
			List ls = null;
			String strUrl = null;
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			ICargoSpaceBus cargoSpaceBus = new CargoSpaceBusImpl(dao);
		
			String strUserCode = request.getSession().getAttribute("userCode").toString();
			String strUserName = request.getSession().getAttribute("userName").toString();
			String backmeg = "";//������Ϣ
			if(strDeleteStr != null && strDeleteStr.length() > 0)
			{
				String [] roleIds = strDeleteStr.split(",");
				for(int i=0; i<roleIds.length; i++)
				{
					List<InventoryAdjustDetail> lsajustDetail = new ArrayList<InventoryAdjustDetail>();
					List<InventoryStorage> lsinventory = new ArrayList<InventoryStorage>();//���ӿ��
//					List<InventoryStorage> addkcls = new ArrayList<InventoryStorage>();//������漯��
//					List<InoutJob> jobls = new ArrayList<InoutJob>();//��ҵ���ϣ�����ɣ�
//					List<InoutJobdetail> jobdells = new ArrayList<InoutJobdetail>();//��ҵ��ϸ
					List<BaseCargospace> cargospacesls = new ArrayList<BaseCargospace>();	//�޸Ļ�λ״̬
					List<InventoryNeedcheck> lsinventoryNeedcheck = new ArrayList<InventoryNeedcheck>();	//�޸��쳣��״̬
					
					Object[] obj = null;
					//ID
					String strTemp =  roleIds[i];	
					
					InventoryAdjustHeader adjust= inventoryadjustbus2.getAdjustHeaderToId(strTemp);//��ȡ������ͷ
					List lsDetail = inventoryadjustbus2.getAdjustDetailListToId(strTemp);//��ȡ��������ϸ
				if (adjust.getStatus()!=null && adjust.getStatus().trim().equals("1")) {//ֻ����˲��ܵ���
					if(lsDetail != null && lsDetail.size()>0)
					{
						for(int j = 0; j < lsDetail.size(); j++)
						{
							InventoryAdjustDetail detail = (InventoryAdjustDetail)lsDetail.get(j);	
							String status = detail.getStatus();
							
							if(status != null && status.trim().equals("1"))//δ���
							{
								//������Ӧ�Ĵ��
								InventoryStorage inventoryS = new InventoryStorage();
								inventoryS.setCargoSpaceId(detail.getCargo_space_id());
								//inventoryS.setCargoAlleyId(job.getTcargoAlleyId());
								inventoryS.setWhAreaId(detail.getWh_area_id());
								inventoryS.setWarehouseid(detail.getWarehouseid());
								inventoryS.setProductid(detail.getTproductid());
								inventoryS.setProductdate(detail.getTprintdate());
								inventoryS.setIndate(StrTools.getCurrDateTime(2));
								inventoryS.setLotid(detail.getTlotid());
								inventoryS.setLotinfo(detail.getTlotinfo());
								//inventoryS.setPackid(jobdetail.getPackid());
								inventoryS.setIntype("2"); //�ѻ�
								inventoryS.setPunit(detail.getTpunit());
								inventoryS.setProductstatus("1");//��Ʒ״̬
							//	inventoryS.setIsplit("1"); //����
								inventoryS.setPnum(detail.getRealitypnum());//�������
//								inventoryS.setInjobid(job.getJobid());
								inventoryS.setTraycode(detail.getTtraycode());
								//inventoryS.setRequestid(job.getBoundrequeststockid()); //���뵥
								//inventoryS.setInstockid(job.getBoundstockid());
								inventoryS.setProductcode(detail.getTproductcode());
								inventoryS.setStatus("0"); //δ����״̬
								
								
								lsinventory.add(inventoryS);
								
								
								//������Ӧ�Ļ�λ
								BaseCargospace baseCargospace = cargoSpaceBus.getCargoSpaceById(detail.getCargo_space_id());
								baseCargospace.setCsstatus("2");//�޸�Ϊ����λ
								cargospacesls.add(baseCargospace);
								
								//���µ����������״̬������ʱ��
								//detail.setJobid(jobObj.getJobid());
								detail.setStatus("2");//�ѵ���
								detail.setAdjusttime(StrTools.getCurrDateTime(5));//����ʱ��
								lsajustDetail.add(detail);		
								
								//�����쳣��
								//��ȡ�쳣��
								InventoryNeedcheck inventoryNeedcheck = inventoryadjustbus2.getInventoryNeedcheckInfoById(detail.getInventoryid());
								if(inventoryNeedcheck!=null){
									inventoryNeedcheck.setStatus("2");//����Ϊ���״̬
									lsinventoryNeedcheck.add(inventoryNeedcheck);
								}
							}
						}
						//�޸ĵ�����״̬��
						adjust.setAdjusttime(StrTools.getCurrDateTime(5));
						adjust.setStatus("3");//���״̬
						adjust.setAuditmanid(strUserCode);
						//��־
						SystemLogInfo sysLog = new SystemLogInfo();
						sysLog.setM_strLogCode(strUserCode);
						sysLog.setM_strLogName(strUserName);
						sysLog.setM_strModuleName("������=>��������");
						sysLog.setM_strContent("�������������ɹ�");
						sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
						//ִ�е������ύ�����ݿ�
						inventoryadjustbus2.createjustInvoice1(adjust,lsajustDetail,lsinventory,cargospacesls,lsinventoryNeedcheck,sysLog);
						
		        		Logger.info("�û���" + strUserCode + "�������˿��������ɹ������ݺţ�" + adjust.getAdjustid());
						
					}else {
						request.setAttribute("back_msg", "������:"+adjust.getAdjustid()+"û�пɵ�������Ϣ��");
					}
					
				  }else {
					backmeg+= strTemp+",";
					request.setAttribute("back_msg", "������:"+backmeg+"�ǲ������״̬�����ܽ��е�����");
				}

				    
			 }
								
				if(pt != null)
				{
					//�����ܼ�¼��  
					int rows = pt.getM_iCountRow();
					pt.setPagingParameter(rows, 1, pt.getM_iMaxRow());
					
					ls = CommonPagination.getPagingList(dao, pt);
					
					strUrl = pt.getM_strUrl();
				}
			}
			if(strUrl == null)
			{
				strUrl = "/jsp/inventory/inventoryAdjustBatch_list_header.jsp";
			}
			
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);	
			
		}catch(Exception er)
		{
			Logger.error("������==>������==>��������ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ���������̵������
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdjustCheckExce(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);	
		String strDeleteStr = CommonTools.getStrToGb2312(request.getParameter("deleteStr"));
		try
		{
			PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
			List ls = null;
			String strUrl = null;
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			ICargoSpaceBus cargoSpaceBus = new CargoSpaceBusImpl(dao);
			IInventoryCheckResultBus checkResultBus = new InventoryCheckResultBusImpl(dao);
		
			String strUserCode = request.getSession().getAttribute("userCode").toString();
			String strUserName = request.getSession().getAttribute("userName").toString();
			String backmeg = "";//������Ϣ
			if(strDeleteStr != null && strDeleteStr.length() > 0)
			{
				String [] roleIds = strDeleteStr.split(",");
				for(int i=0; i<roleIds.length; i++)
				{
					List<InventoryAdjustDetail> lsajustDetail = new ArrayList<InventoryAdjustDetail>();
					List<InventoryStorage> lsinventory = new ArrayList<InventoryStorage>();//�޸Ŀ��
					List<BaseCargospace> cargospacesls = new ArrayList<BaseCargospace>();	//�޸Ļ�λ״̬
					List<InventoryCheckResult> lscheckResult = new ArrayList<InventoryCheckResult>();	//�޸��̵�����״̬
					
					Object[] obj = null;
					//ID
					String strTemp =  roleIds[i];	
					
					InventoryAdjustHeader adjust= inventoryadjustbus2.getAdjustHeaderToId(strTemp);//��ȡ������ͷ
					List lsDetail = inventoryadjustbus2.getAdjustDetailListToId(strTemp);//��ȡ��������ϸ
				if (adjust.getStatus()!=null && adjust.getStatus().trim().equals("1")) {//ֻ����˲��ܵ���
					if(lsDetail != null && lsDetail.size()>0)
					{
						for(int j = 0; j < lsDetail.size(); j++)
						{
							InventoryAdjustDetail detail = (InventoryAdjustDetail)lsDetail.get(j);	
							String status = detail.getStatus();
							
							if(status != null && status.trim().equals("1"))//δ���
							{
								//������Ӧ�Ĵ��
								InventoryStorage inventoryS = inventoryadjustbus2.getInventoryInfoToId(detail.getInventoryid());
								inventoryS.setCargoSpaceId(detail.getCargo_space_id());
								//inventoryS.setCargoAlleyId(job.getTcargoAlleyId());
								inventoryS.setWhAreaId(detail.getWh_area_id());
								inventoryS.setWarehouseid(detail.getWarehouseid());
								inventoryS.setProductid(detail.getTproductid());
								inventoryS.setProductdate(detail.getTprintdate());
								inventoryS.setIndate(StrTools.getCurrDateTime(2));
								inventoryS.setLotid(detail.getTlotid());
								inventoryS.setLotinfo(detail.getTlotinfo());
								//inventoryS.setPackid(jobdetail.getPackid());
								inventoryS.setIntype("2"); //�ѻ�
								inventoryS.setPunit(detail.getTpunit());
								inventoryS.setProductstatus("1");//��Ʒ״̬
							//	inventoryS.setIsplit("1"); //����
								inventoryS.setPnum(detail.getRealitypnum());//�������
//								inventoryS.setInjobid(job.getJobid());
								inventoryS.setTraycode(detail.getTtraycode());
								//inventoryS.setRequestid(job.getBoundrequeststockid()); //���뵥
								//inventoryS.setInstockid(job.getBoundstockid());
								//inventoryS.setProductcode(detail.get);
								inventoryS.setStatus("0"); //δ����״̬
								
								
								lsinventory.add(inventoryS);
								
								//�̵�����
								InventoryCheckResult checkResult = checkResultBus.getCheckResultByInventoryId(detail.getInventoryid());
								checkResult.setStatus("3");// 1.�½���2.������3.���
								lscheckResult.add(checkResult);
								
								//������Ӧ�Ļ�λ
								BaseCargospace baseCargospace = cargoSpaceBus.getCargoSpaceById(detail.getCargo_space_id());
								baseCargospace.setCsstatus("2");//�޸�Ϊ����λ
								cargospacesls.add(baseCargospace);
								
								//���µ�������״̬������ʱ��
								//detail.setJobid(jobObj.getJobid());
								detail.setStatus("2");//�ѵ���
								detail.setAdjusttime(StrTools.getCurrDateTime(5));//����ʱ��
								lsajustDetail.add(detail);		
								
								//�����쳣��
								//��ȡ�쳣��
//								InventoryNeedcheck inventoryNeedcheck = inventoryadjustbus2.getInventoryNeedcheckInfoById(detail.getInventoryid());
//								if(inventoryNeedcheck!=null){
//									inventoryNeedcheck.setStatus("2");//����Ϊ���״̬
//									lsinventoryNeedcheck.add(inventoryNeedcheck);
//								}
								
								
								
							}
						}
						//�޸ĵ�����״̬��
						adjust.setAdjusttime(StrTools.getCurrDateTime(5));
						adjust.setStatus("3");//���״̬
						adjust.setAuditmanid(strUserCode);
						//��־
						SystemLogInfo sysLog = new SystemLogInfo();
						sysLog.setM_strLogCode(strUserCode);
						sysLog.setM_strLogName(strUserName);
						sysLog.setM_strModuleName("������=>��������");
						sysLog.setM_strContent("�������������ɹ�");
						sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
						//ִ�е������ύ�����ݿ�
						inventoryadjustbus2.createjustInvoice2(adjust,lsajustDetail,lsinventory,cargospacesls,lscheckResult,sysLog);
						
		        		Logger.info("�û���" + strUserCode + "�������˿��������ɹ������ݺţ�" + adjust.getAdjustid());
						
					}else {
						request.setAttribute("back_msg", "������:"+adjust.getAdjustid()+"û�пɵ�������Ϣ��");
					}
					
				  }else {
					backmeg+= strTemp+",";
					request.setAttribute("back_msg", "������:"+backmeg+"�ǲ������״̬�����ܽ��е�����");
				}

				    
			 }
								
				if(pt != null)
				{
					//�����ܼ�¼��  
					int rows = pt.getM_iCountRow();
					pt.setPagingParameter(rows, 1, pt.getM_iMaxRow());
					
					ls = CommonPagination.getPagingList(dao, pt);
					
					strUrl = pt.getM_strUrl();
				}
			}
			if(strUrl == null)
			{
				strUrl = "/jsp/inventory/inventoryAdjustBatch_list_header.jsp";
			}
			
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);	
			
		}catch(Exception er)
		{
			Logger.error("������==>������==>��������ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * ����:�����������
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAuditAjustHeader(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		String strUserCode = request.getSession().getAttribute("userCode").toString();
		String strUserName = request.getSession().getAttribute("userName").toString();
		try{
		//����(����ö��ŷָ�)
		String strDeleteStr = CommonTools.getStrToGb2312(request.getParameter("deleteStr"));
		InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
		ICargoSpaceBus csBus = new CargoSpaceBusImpl(dao);
		PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
		List ls = null;
		String strUrl = null;
		if(strDeleteStr != null && strDeleteStr.length() > 0)
		{
			String [] roleIds = strDeleteStr.split(",");
			InventoryAdjustHeader adjust=null;
			List<InventoryAdjustDetail> adjustDetailLs = null;
			for(int i=0; i<roleIds.length; i++)
			{
				String strTemp =  roleIds[i];
				//��õ�����
				adjust=inventoryadjustbus2.getAdjustHeaderToId(strTemp);
			   //��õ�������ϸ
				adjustDetailLs = inventoryadjustbus2.getAdjustDetailListToId(adjust.getAdjustid());
				if(adjust!=null && adjust.getStatus().equals("0")){ // ��������Ϊ����״̬
					if (adjustDetailLs!=null && adjustDetailLs.size()>0) {
						adjust.setAuditdate(StrTools.getCurrDateTime(5));
						adjust.setStatus("1");
						adjust.setAuditmanid(strUserCode);
						dao.update(adjust);
						
						//��־
						SystemLogInfo sysLog = new SystemLogInfo();
						sysLog.setM_strLogCode(strUserCode);
						sysLog.setM_strLogName(strUserName);
						sysLog.setM_strModuleName("������=>��������");
						sysLog.setM_strContent("��˿��������ɹ�:����Ϊ"+strTemp);
						sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
						dao.save(sysLog);				
						request.setAttribute("back_msg", "Y");
					}else {
						request.setAttribute("back_msg", "�ÿ�������û����ϸ��������ˣ�");
					}
					
				}else{
					request.setAttribute("back_msg", "���������Ǵ���״̬");
				}	
			}

			if(pt != null)
			{
				//�����ܼ�¼��  
				int rows = pt.getM_iCountRow()-roleIds.length;
				pt.setPagingParameter(rows, 1, pt.getM_iMaxRow());
				ls = CommonPagination.getPagingList(dao, pt);
				strUrl = pt.getM_strUrl();
			}
		}
		if(strUrl == null)
		{
			strUrl = "/standard/jsp/inventory/adjust/kc_adjustheader_list.jsp";
		}
		
		request.setAttribute("exResultList", ls);
		request.setAttribute("pagingTool", pt);
		httpsession.setAttribute("paging", pt);
		
		request.getRequestDispatcher(strUrl).forward(request, response);

		}catch(Exception er)
		{
			Logger.error("������==>������==>�����������ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * ���ܣ��̵��������ϸ���Ƿ����Ҫ�����Ļ�λ
	 * @param cs Ҫ�����Ļ�λ
	 * @param csLs  ������ϸ����
	 * @return
	 */
	boolean isCargoSpace(String cs,List<InventoryAdjustDetail> csLs) {
		boolean isHavaCs = false;
		if (csLs!=null && csLs.size()>0) {
			for (InventoryAdjustDetail del : csLs) {
				if (del.getCargo_space_id().equals(cs)) {
					isHavaCs = true;
					break;
				}
			}
		}
		return isHavaCs;
	}
	
	
	
	
	
	
	/**
	 * �������������Ϣ
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdjustInventoryinfoExce(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);	
		String strDeleteStr = CommonTools.getStrToGb2312(request.getParameter("deleteStr"));
		try
		{
			PagingTool pt = (PagingTool)httpsession.getAttribute("paging");
			List ls = null;
			String strUrl = null;
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			ICargoSpaceBus cargoSpaceBus = new CargoSpaceBusImpl(dao);
		
			String strUserCode = request.getSession().getAttribute("userCode").toString();
			String strUserName = request.getSession().getAttribute("userName").toString();
			String backmeg = "";//������Ϣ
			if(strDeleteStr != null && strDeleteStr.length() > 0)
			{
				String [] roleIds = strDeleteStr.split(",");
				for(int i=0; i<roleIds.length; i++)
				{
					List<InventoryAdjustDetail> lsajustDetail = new ArrayList<InventoryAdjustDetail>();
					List<InventoryStorage> lsinventory = new ArrayList<InventoryStorage>();//�޸Ŀ��
					List<BaseCargospace> cargospacesls = new ArrayList<BaseCargospace>();	//�޸Ļ�λ״̬
					//List<InventoryNeedcheck> lsinventoryNeedcheck = new ArrayList<InventoryNeedcheck>();	//�޸��쳣��״̬
					
					Object[] obj = null;
					//ID
					String strTemp =  roleIds[i];	
					
					InventoryAdjustHeader adjust= inventoryadjustbus2.getAdjustHeaderToId(strTemp);//��ȡ������ͷ
					List lsDetail = inventoryadjustbus2.getAdjustDetailListToId(strTemp);//��ȡ��������ϸ
				   if (adjust.getStatus()!=null && adjust.getStatus().trim().equals("1")) {//ֻ����˲��ܵ���
					if(lsDetail != null && lsDetail.size()>0)
					{
						for(int j = 0; j < lsDetail.size(); j++)
						{
							InventoryAdjustDetail detail = (InventoryAdjustDetail)lsDetail.get(j);	
							String status = detail.getStatus();
							
							if(status != null && status.trim().equals("1"))//δ���
							{
								//������Ӧ�Ĵ��
								InventoryStorage inventoryS = inventoryadjustbus2.getInventoryInfoToId(detail.getInventoryid());
								if(inventoryS!=null){//�޸Ŀ��
									inventoryS.setCargoSpaceId(detail.getCargo_space_id());
									//inventoryS.setCargoAlleyId(job.getTcargoAlleyId());
									inventoryS.setWhAreaId(detail.getWh_area_id());
									inventoryS.setWarehouseid(detail.getWarehouseid());
									inventoryS.setProductid(detail.getTproductid());
									inventoryS.setProductdate(detail.getTprintdate());
									inventoryS.setIndate(StrTools.getCurrDateTime(2));
									inventoryS.setLotid(detail.getTlotid());
									inventoryS.setLotinfo(detail.getTlotinfo());
									//inventoryS.setPackid(jobdetail.getPackid());
									inventoryS.setIntype("2"); //�ѻ�
									inventoryS.setPunit(detail.getTpunit());
									inventoryS.setProductstatus("1");//��Ʒ״̬
								//	inventoryS.setIsplit("1"); //����
									inventoryS.setPnum(detail.getRealitypnum());//�������
//									inventoryS.setInjobid(job.getJobid());
									inventoryS.setTraycode(detail.getTtraycode());
									//inventoryS.setRequestid(job.getBoundrequeststockid()); //���뵥
									//inventoryS.setInstockid(job.getBoundstockid());
									//inventoryS.setProductcode(detail.get);
									inventoryS.setStatus("0"); //δ����״̬
									lsinventory.add(inventoryS);
									
								}else{//��ӿ��
									//---------------��ӿ��-----------------------
									InventoryStorage inventoryS1 = new InventoryStorage();
									inventoryS1.setCargoSpaceId(detail.getCargo_space_id());
									//inventoryS1.setCargoAlleyId(job.getTcargoAlleyId());
									inventoryS1.setWhAreaId(detail.getWh_area_id());
									inventoryS1.setWarehouseid(detail.getWarehouseid());
									inventoryS1.setProductid(detail.getTproductid());
									inventoryS1.setProductdate(detail.getTprintdate());
									inventoryS1.setIndate(StrTools.getCurrDateTime(2));
									inventoryS1.setLotid(detail.getTlotid());
									inventoryS1.setLotinfo(detail.getTlotinfo());
									//inventoryS1.setPackid(jobdetail.getPackid());
									inventoryS1.setIntype("2"); //�ѻ�
									inventoryS1.setPunit(detail.getTpunit());
									inventoryS1.setProductcode(detail.getTproductcode());//��Ʒ����
									inventoryS1.setProductstatus("1");//��Ʒ״̬
									inventoryS1.setPnum(detail.getRealitypnum());//�������
									inventoryS1.setTraycode(detail.getTtraycode());
									inventoryS1.setStatus("0"); //δ����״̬
									lsinventory.add(inventoryS1);
								}
								//������Ӧ�Ļ�λ
								BaseCargospace baseCargospace = cargoSpaceBus.getCargoSpaceById(detail.getCargo_space_id());
								String ss="";
								if(adjust.getWarehouseid().equals("001")){
									ss = StandardConstant.zcidkzl;
								}
								if(baseCargospace.getWhAreaId().equals(adjust.getWarehouseid()+ss)){ //Ϊ�ݴ��� ����Ҫ���Ļ�λ״̬
								}else{
									if(detail.getRealitypnum()<=0){
										baseCargospace.setCsstatus("1");
									}else{
										baseCargospace.setCsstatus("2");
									}
									cargospacesls.add(baseCargospace);
								}
								
								
								//���µ�������״̬������ʱ��
								//detail.setJobid(jobObj.getJobid());
								detail.setStatus("2");//�ѵ���
								detail.setAdjusttime(StrTools.getCurrDateTime(5));//����ʱ��
								lsajustDetail.add(detail);		
								
								//�����쳣��
								//��ȡ�쳣��
//								InventoryNeedcheck inventoryNeedcheck = inventoryadjustbus2.getInventoryNeedcheckInfoById(detail.getInventoryid());
//								if(inventoryNeedcheck!=null){
//									inventoryNeedcheck.setStatus("2");//����Ϊ���״̬
//									lsinventoryNeedcheck.add(inventoryNeedcheck);
//								}
								
								
								
							}
						}
						//�޸ĵ�����״̬��
						adjust.setAdjusttime(StrTools.getCurrDateTime(5));
						adjust.setStatus("3");//���״̬
						adjust.setAuditmanid(strUserCode);
						//��־
						SystemLogInfo sysLog = new SystemLogInfo();
						sysLog.setM_strLogCode(strUserCode);
						sysLog.setM_strLogName(strUserName);
						sysLog.setM_strModuleName("������=>��������");
						sysLog.setM_strContent("�������������ɹ�");
						sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
						//ִ�е������ύ�����ݿ�
						inventoryadjustbus2.createjustInvoice3(adjust,lsajustDetail,lsinventory,cargospacesls,sysLog);
						
		        		Logger.info("�û���" + strUserCode + "�������˿��������ɹ������ݺţ�" + adjust.getAdjustid());
						
					}else {
						request.setAttribute("back_msg", "������:"+adjust.getAdjustid()+"û�пɵ�������Ϣ��");
					}
					
				  }else {
					backmeg+= strTemp+",";
					request.setAttribute("back_msg", "������:"+backmeg+"�ǲ������״̬�����ܽ��е�����");
				}

				    
			 }
								
				if(pt != null)
				{
					//�����ܼ�¼��  
					int rows = pt.getM_iCountRow();
					pt.setPagingParameter(rows, 1, pt.getM_iMaxRow());
					
					ls = CommonPagination.getPagingList(dao, pt);
					
					strUrl = pt.getM_strUrl();
				}
			}
			if(strUrl == null)
			{
				strUrl = "/jsp/inventory/inventoryAdjustBatch_list_header.jsp";
			}
			
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);
			request.getRequestDispatcher(strUrl).forward(request, response);	
			
		}catch(Exception er)
		{
			Logger.error("������==>������==>��������ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
}
