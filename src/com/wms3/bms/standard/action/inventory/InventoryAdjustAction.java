package com.wms3.bms.standard.action.inventory;

import java.util.ArrayList;
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
import com.ricosoft.common.tools.StrTools;
import com.ricosoft.entity.competenceMgr.SystemLogInfo;
import com.wms3.bms.standard.business.SeqTool;
import com.wms3.bms.standard.business.inventory.InventoryAdjustBus;
import com.wms3.bms.standard.business.inventory.impl.InventoryAdjustBusImpl;
import com.wms3.bms.standard.dao.base.impl.BaseProductDaoImpl;
import com.wms3.bms.standard.dao.base.impl.BaseSeqDaoImpl;
import com.wms3.bms.standard.entity.base.BaseProduct;
import com.wms3.bms.standard.entity.base.BaseSeq;
import com.wms3.bms.standard.entity.inventory.InventoryAdjustDetail;
import com.wms3.bms.standard.entity.inventory.InventoryAdjustHeader;
import com.wms3.bms.standard.entity.inventory.InventoryTransaction;
import com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage;



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
		//�ͻ�
		String strCustomer = CommonTools.getStrToGb2312(request.getParameter("customerid"));
		//״̬
		String strStatus = CommonTools.getStrToGb2312(request.getParameter("status"));
		inventoryadjustbus = new InventoryAdjustBusImpl(dao);
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
		if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }

		try
		{	
			/*List ls = null;
			//��ѯ��SQL���
			String strQuerySQL = inventoryadjustbus.getAdjustHeaderSQL(strStatus, strCustomer);
			//��ѯ��ɫ�ܼ�¼����SQL���
			String strCountSQL = inventoryadjustbus.getAdjustHeaderCountSQL(strStatus, strCustomer);
			String strUrl = "/standard/jsp/inventory/adjust/kc_adjustheader_list.jsp";	
			PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, strUrl ,1, maxLine);
			//��ѯ���
			ls = pt.getLsResult();
			request.setAttribute("exResultList", ls);
			request.setAttribute("pagingTool", pt);
			httpsession.setAttribute("paging", pt);	*/

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
		//�ͻ�
		String strCustomer = CommonTools.getStrToGb2312(request.getParameter("customerid"));
		//ԭ�����
		String strReasoncode = CommonTools.getStrToGb2312(request.getParameter("reasoncode"));
		//ԭ��
		String strReasondiscr = CommonTools.getStrToGb2312(request.getParameter("reasondiscr"));
	
		try
		{
			
			InventoryAdjustHeader adjust = new InventoryAdjustHeader();
			//��������
			BaseSeqDaoImpl seqDao = new BaseSeqDaoImpl(dao);
            BaseSeq  seqEn = seqDao.getSeqByType("ADD");
            String strInvoiceNo = SeqTool.getNewID(seqEn.getSeqType(), seqEn.getSeqPrefix(),seqEn.getIcodelength(), dao);
            
			//adjust.setCustomerid(strCustomer);
			adjust.setStatus(strStatus);
			adjust.setReasoncode(strReasoncode);
			adjust.setReasondiscr(strReasondiscr);
			adjust.setAdjustid(strInvoiceNo);
			adjust.setCreatetime(StrTools.getCurrDateTime(5));
			dao.save(adjust);
			
			//��־
			SystemLogInfo sysLog = new SystemLogInfo();
			String strUserCode = request.getSession().getAttribute("userCode").toString();
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
					//adjust=inventoryadjustbus2.getAdjustHeaderToId(strTemp);
					if(adjust!=null && adjust.getStatus().equals("0")){ // ��������Ϊ����״̬
						List lsDetail = inventoryadjustbus2.getAdjustDetailListToId(strTemp);
						List lsinventory = new ArrayList();
						if(lsDetail != null)
						{
							for(int j = 0; j < lsDetail.size(); j++)
							{
								    InventoryAdjustDetail detail = (InventoryAdjustDetail)lsDetail.get(j);
									//���
									InventoryStorage inventory = inventoryadjustbus2.getInventoryInfoToId(detail.getInventoryid());
									if(inventory != null)
									{
										inventory.setStatus("0");//δ����
									}
									lsinventory.add(inventory);
							}
						}
						
						//inventoryadjustbus2.deletejustInvoice(lsDetail, lsinventory, adjust);
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
			//InventoryAdjustHeader adjust = inventoryadjustbus2.getAdjustHeaderToId(strId);
			
			//request.setAttribute("adjust", adjust);
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
		//�ͻ�
		String strCustomer = CommonTools.getStrToGb2312(request.getParameter("customerid"));
		//ԭ�����
		String strReasoncode = CommonTools.getStrToGb2312(request.getParameter("reasoncode"));
		//ԭ��
		String strReasondiscr = CommonTools.getStrToGb2312(request.getParameter("reasondiscr"));
		try
		{
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			InventoryAdjustHeader adjust= null;//inventoryadjustbus2.getAdjustHeaderToId(strId);
			if(adjust != null)
			{
				//adjust.setCustomerid(strCustomer);
				adjust.setReasoncode(strReasoncode);
				adjust.setReasondiscr(strReasondiscr);
				adjust.setAdjusttime(StrTools.getCurrDateTime(5));
				//inventoryadjustbus2.updateAdjustHeader(adjust);
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
	 * ����:���ӵ�������ϸ
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
		//���ID
		String strInventoryid = CommonTools.getStrToGb2312(request.getParameter("inventoryid"));
		//Ŀ������
		String strToQty = CommonTools.getStrToGb2312(request.getParameter("toqty"));
		//Ŀ��ë��
		String strToGrossweight = CommonTools.getStrToGb2312(request.getParameter("togrossweight"));
		//Ŀ�����
		String strToCubic = CommonTools.getStrToGb2312(request.getParameter("tocubic"));
		//Ŀ�꾻��
		String strToNetweight = CommonTools.getStrToGb2312(request.getParameter("tonetweight"));
		//״̬
		String strStatus = CommonTools.getStrToGb2312(request.getParameter("status"));
	
		try
		{
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			InventoryStorage inventory = inventoryadjustbus2.getInventoryInfoToId(strInventoryid);	
			InventoryAdjustHeader adjustx= null ; //inventoryadjustbus2.getAdjustHeaderToId(strId);
			
			if(inventory != null && inventory.getStatus().equals("0"))  //���Ϊδ����״̬
			{
				if(adjustx.getStatus().equals("0")){ //����״̬
					InventoryAdjustDetail adjust = new InventoryAdjustDetail();
					adjust.setAdjustid(strId);
					adjust.setStatus(strStatus);
					//adjust.setCustomerid(inventory.getOwnerId());
					adjust.setProductid(inventory.getProductid());
					adjust.setLotid(inventory.getLotid());
					adjust.setTraycode(inventory.getTraycode());
					adjust.setPnum(inventory.getPnum());
					//adjust.setPvolume(inventory.getPvolume());
					adjust.setPweight(inventory.getPweight());
					adjust.setPnetweight(inventory.getPnetweight());
					adjust.setTonum(Float.parseFloat(strToQty));
					adjust.setCargo_space_id(inventory.getCargoSpaceId());
					adjust.setInventoryid(inventory.getInventoryid());
					
					adjust.setLotatt1(inventory.getLotatt1());
					adjust.setLotatt2(inventory.getLotatt2());
					adjust.setLotatt3(inventory.getLotatt3());
					adjust.setLotatt4(inventory.getLotatt4());
					adjust.setLotatt5(inventory.getLotatt5());
					adjust.setLotatt6(inventory.getLotatt6());
					adjust.setLotatt7(inventory.getLotatt7());
					adjust.setLotatt8(inventory.getLotatt8());
					adjust.setLotatt9(inventory.getLotatt9());
					adjust.setLotatt10(inventory.getLotatt10());
					adjust.setLotatt11(inventory.getLotatt11());
					adjust.setLotatt12(inventory.getLotatt12());
					
					if(strToCubic != null && strToCubic.trim().length() > 0)
					{
						adjust.setTovolume(Float.parseFloat(strToCubic));	
					}
					if(strToGrossweight != null && strToGrossweight.trim().length() > 0)
					{
						adjust.setToweight(Float.parseFloat(strToGrossweight));
					}
					if(strToNetweight != null && strToNetweight.trim().length() > 0)
					{
						adjust.setTonetweight(Float.parseFloat(strToNetweight));
					}
					adjust.setCreatetime(StrTools.getCurrDateTime(5));
					adjust.setAdjustdetailid(strInventoryid);
					dao.save(adjust);	
					
					//���
					inventory.setStatus("3"); //������״̬
					dao.update(inventory);	
					request.setAttribute("back_msg", "Y");
					
					//��־
					SystemLogInfo sysLog = new SystemLogInfo();
					String strUserCode = request.getSession().getAttribute("userCode").toString();
					String strUserName = request.getSession().getAttribute("userName").toString();
					sysLog.setM_strLogCode(strUserCode);
					sysLog.setM_strLogName(strUserName);
					sysLog.setM_strModuleName("������=>������");
					sysLog.setM_strContent("��ӿ���������ϸ�ɹ�");
					sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
					dao.save(sysLog);
				}else{
					request.setAttribute("back_msg", "ֻ�д���״̬�������ӵ�������ϸ");
				}
				
			}else{
				request.setAttribute("back_msg", "ֻ��δ����״̬���ܿ�����");
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
			String strUrl = null;
			InventoryAdjustHeader adjust= null;// inventoryadjustbus2.getAdjustHeaderToId(strId);
			if(strDeleteStr != null && strDeleteStr.length() > 0 && adjust.getStatus().equals("0")) //����״̬
			{
				String [] roleIds = strDeleteStr.split(",");
				for(int i=0; i<roleIds.length; i++)
				{
					//ɾ��
					String strTemp =  roleIds[i];
					//inventoryadjustbus2.deleteAdjustDetail(strTemp);
					/*InventoryAdjustDetail detail = inventoryadjustbus2.getAdjustDetailToId(strTemp);
					InventoryStorage inventory = inventoryadjustbus2.getInventoryInfoToId(detail.getInventoryid());	
					dao.delete(detail);
					inventory.setStatus("0"); //��ԭδ����״̬
					dao.update(inventory);*/
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
			BaseProductDaoImpl skuMgr = new BaseProductDaoImpl(dao);
			InventoryAdjustDetail detail = null;//inventoryadjustbus2.getAdjustDetailToId(strId);
			BaseProduct sku = skuMgr.getProductById(detail.getProductid());
			
			request.setAttribute("adjustdetail", detail);
			request.setAttribute("sku", sku);
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
		String id="";
		//��������ϸID
		String strId = CommonTools.getStrToGb2312(request.getParameter("detailId"));

		//Ŀ������
		String strToQty = CommonTools.getStrToGb2312(request.getParameter("toqty"));
		//Ŀ��ë��
		String strToGrossweight = CommonTools.getStrToGb2312(request.getParameter("togrossweight"));
		//Ŀ�����
		String strToCubic = CommonTools.getStrToGb2312(request.getParameter("tocubic"));
		//Ŀ�꾻��
		String strToNetweight = CommonTools.getStrToGb2312(request.getParameter("tonetweight"));
		try
		{
			InventoryAdjustBusImpl inventoryadjustbus2 = new InventoryAdjustBusImpl(dao);
			InventoryAdjustDetail detail = null;//inventoryadjustbus2.getAdjustDetailToId(strId);
			id = detail.getAdjustid();
			if(detail != null && detail.getStatus().equals("0")) //������ϸ״̬Ϊ����״̬
			{
				detail.setTonum(Float.parseFloat(strToQty));
				if(strToCubic != null && strToCubic.trim().length() > 0)
				{
					detail.setTovolume(Float.parseFloat(strToCubic));	
				}
				if(strToGrossweight != null && strToGrossweight.trim().length() > 0)
				{
					detail.setToweight(Float.parseFloat(strToGrossweight));
				}
				if(strToNetweight != null && strToNetweight.trim().length() > 0)
				{
					detail.setTonetweight(Float.parseFloat(strToNetweight));
				}
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
			String strQuerySQL = inventoryadjustbus2.getAdjustDetailSQL(id);
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
			if(strDeleteStr != null && strDeleteStr.length() > 0)
			{
				String [] roleIds = strDeleteStr.split(",");
				for(int i=0; i<roleIds.length; i++)
				{
					List lsajustDetail = new ArrayList();
					List lsinventory = new ArrayList();
					List lstrans = new ArrayList();
					//ID
					String strTemp =  roleIds[i];
					
					InventoryAdjustHeader adjust= null;//inventoryadjustbus2.getAdjustHeaderToId(strTemp);
					List lsDetail = inventoryadjustbus2.getAdjustDetailListToId(strTemp);
					if(lsDetail != null)
					{
						for(int j = 0; j < lsDetail.size(); j++)
						{
							InventoryAdjustDetail detail = (InventoryAdjustDetail)lsDetail.get(j);
							
							String status = detail.getStatus();
							if(status != null && !status.trim().equals("1"))
							{
								//���ID
								String strId = detail.getInventoryid();
								//Ŀ������
								double toQty = detail.getTonum();
								//Ŀ��ë��
								double toGrossweight = detail.getToweight();
								//Ŀ�꾻��
								double toNetweight = detail.getTonetweight();
								//Ŀ�����
								double toCubic = detail.getTovolume();
								//���
								InventoryStorage inventory = inventoryadjustbus2.getInventoryInfoToId(strId);
								if(inventory != null)
								{
									//���ӿ�潻��
									InventoryTransaction trans = new InventoryTransaction();
									trans.setTransactiontype("����");//����
									trans.setTransstatus("���");//���
									trans.setDoctype("������");//������
									
									trans.setFmcustomerid(inventory.getOwnerId());
									trans.setFmpackid(inventory.getPackid());
									trans.setFmproductid(inventory.getProductid());
									trans.setFmlotid(inventory.getLotid());
									trans.setFmcargo_space_id(inventory.getCargoSpaceId());
									trans.setFmpunit(inventory.getPunit());
									trans.setFmnum(inventory.getPnum());
									trans.setFmvolume(inventory.getPvolume());
									trans.setFmweight(inventory.getPweight());
									trans.setFmnetweight(inventory.getPnetweight());
									trans.setCreatemanid(strUserName);
									
									trans.setTocustomerid(inventory.getOwnerId());
									trans.setToproductid(inventory.getProductid());
									trans.setTolotid(inventory.getLotid());
									trans.setTocargo_space_id(inventory.getCargoSpaceId());
									trans.setTopunit(inventory.getPunit());
									trans.setTonum(toQty);
									trans.setTovolume(toCubic);
									trans.setToweight(toGrossweight);
									trans.setTonetweight(toNetweight);
									
									inventory.setPnum(toQty);
									inventory.setPvolume(toCubic);
									inventory.setPweight(toGrossweight);
									inventory.setPnetweight(toNetweight);
									inventory.setStatus("0");//δ����
						
									trans.setReasoncode(adjust.getReasoncode());
									trans.setReason(adjust.getReasondiscr());
									trans.setTransactiontime(StrTools.getCurrDateTime(5)); 
									
									lstrans.add(trans);
								}
								detail.setAdjusttime(StrTools.getCurrDateTime(5));
								detail.setStatus("���");
								lsajustDetail.add(detail);
								lsinventory.add(inventory);
								
							}
						}
					}
					//�޸ĵ�����״̬��
					adjust.setAdjusttime(StrTools.getCurrDateTime(5));
					adjust.setStatus("1");
					//inventoryadjustbus2.createjustInvoice(lsajustDetail, lsinventory, adjust,lstrans);
            		Logger.info("�û���" + strUserCode + "�������˿������������ݺţ�" + adjust.getAdjustid());
				}
				
				//��־
				SystemLogInfo sysLog = new SystemLogInfo();
				sysLog.setM_strLogCode(strUserCode);
				sysLog.setM_strLogName(strUserName);
				sysLog.setM_strModuleName("������=>��������");
				sysLog.setM_strContent("�������������ɹ�");
				sysLog.setM_strCreateTime(StrTools.getCurrDateTime(2));
				dao.save(sysLog);
				
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
