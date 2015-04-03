package com.wms3.bms.standard.action.outerInterface;

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
import com.wms3.bms.standard.business.outbound.IOutboundSoBus;
import com.wms3.bms.standard.business.outbound.impl.OutboundSoBusImpl;
import com.wms3.bms.standard.entity.outerInterface.SaleForm;

/**
 * ˵�������۵�
 * @author yao
 */
public class SaleFormAction extends AbstractAction {
	protected IOutboundSoBus outBoundSoBus;
	protected int maxLine = 6;
	
	
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		
		String warhouseid = CommonTools.getStrToGbk(request.getParameter("warhouseid"));				//�ֿ�
		String m_strSaleFormId = CommonTools.getStrToGbk(request.getParameter("m_strSaleFormId"));		//���۶�����
		String m_strSaleFormType = CommonTools.getStrToGbk(request.getParameter("m_strSaleFormType"));	//��������
		String m_strCustomerId = CommonTools.getStrToGbk(request.getParameter("m_strCustomerId"));		//�ͻ�ID
		String m_strDownTime = CommonTools.getStrToGbk(request.getParameter("m_strDownTime"));			//��������ʱ��
		String m_strIsOut = CommonTools.getStrToGbk(request.getParameter("m_strIsOut"));				//�Ƿ������
		String m_strIsFinish = CommonTools.getStrToGbk(request.getParameter("m_strIsFinish"));			//�Ƿ���ɳ���
		String m_strIsInvalid = CommonTools.getStrToGbk(request.getParameter("m_strIsInvalid"));		//�Ƿ���Ч
		
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));	
		String linerow = CommonTools.getStrToGbk(request.getParameter("linerow"));			//ÿҳ��ʾ����
        if(linerow != null && linerow.length()>0){
        	maxLine = Integer.parseInt(linerow);
        }
        outBoundSoBus = new OutboundSoBusImpl(dao);
		try {
			if(flag!=null){
				if(flag.equals("1")){//��ö�����Ϣ
					//��תҳ��
					strUrl = "/standard/jsp/outbound/so/outbound_so_list.jsp";
					PagingTool pt = outBoundSoBus.getOutboundSoHeaders(m_strSaleFormId, m_strSaleFormType, m_strCustomerId, m_strDownTime, m_strIsOut, m_strIsFinish, m_strIsInvalid, linerow, maxLine);
					List ls = pt.getLsResult();
					request.setAttribute("exResultList", ls);
					httpsession.setAttribute("paging", pt);
				}else if(flag.equals("2")){
					//��תҳ��
					strUrl = "/standard/jsp/outbound/so/outbound_so_detail.jsp";
					List ls = outBoundSoBus.getOutboundSoDetails(m_strSaleFormId);
					SaleForm header = outBoundSoBus.getOutboundSoHeaders(m_strSaleFormId);
					request.setAttribute("exResultList", ls);
					request.setAttribute("header", header);
				}
				/*
				else if(flag.equals("3")){
					//��תҳ��
					strUrl = "/customs/yld/jsp/base/underbfd/underbfd_so_detail.jsp";
					List ls = outBoundSoBus.getOutboundSoDetails(soid);
					
					OutboundSoHeader header = outBoundSoBus.getOutboundSoHeaders(soid);
					lsLot = AbstractBus.getLotset(hsSysParam, V_CKDDETAIL);
					request.setAttribute("lsLot", lsLot);
					request.setAttribute("exResultList", ls);
					request.setAttribute("header", header);
				}else if(flag.equals("header")){
					//��תҳ��
					strUrl = "/customs/yld/jsp/inbound/gdinvoice/inbound_gd_detail.jsp";
					OutboundSoHeader header = outBoundSoBus.getOutboundSoHeaders(soid);
					if(header!=null&&header.getSotype()!=null){
						header.setSotype(escapeCode(header.getSotype()));
						}
					IProductBus productbus = new ProductBusImpl(dao);
					BaseProduct product = productbus.getProductByErpNo(header.getReserve1());
					lsLot = AbstractBus.getLotset(hsSysParam, V_CKDDETAIL);
					request.setAttribute("lsLot", lsLot);
					request.setAttribute("product", product);
					request.setAttribute("header", header);
				}else if(flag.equals("rf_work")){
					//��תҳ��
					strUrl = "/customs/yld/jsp/rf/outbound_so_detail.jsp";
					List ls = outBoundSoBus.getOutboundSoDetails(soid);
					OutboundSoHeader header = outBoundSoBus.getOutboundSoHeaders(soid);
					lsLot = AbstractBus.getLotset(hsSysParam, V_CKDDETAIL);
					if(header!=null&&header.getSotype()!=null){
					header.setSotype(escapeCode(header.getSotype()));
					}
					request.setAttribute("lsLot", lsLot);
					request.setAttribute("exResultList", ls);
					request.setAttribute("header", header);
				}
				
				*/
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
		} catch (Exception er) {
			Logger.error("�½�����==>���ⶩ����ѯ����:" + er.getMessage());
            request.setAttribute("errormsg", "������Ϣ:" + er.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * �ַ���ת��
	 * @param code ��Ҫת����ַ���
	 * @return
	 */
	public String escapeCode(String oldCode){
		String newCode="";
		      if("1".equals(oldCode)){//һ�㹤��
		    	  newCode="2";
		      }else if("5".equals(oldCode)){
		    	  newCode="2";
		      }else if("7".equals(oldCode)){
		    	  newCode="2";
		      }else if("8".equals(oldCode)){
		    	  newCode="2";
		      }else if("c10".equals(oldCode)){
		    	  newCode="1";
		      }else if("c11".equals(oldCode)){
		    	  newCode="11";
		      }else if("c12".equals(oldCode)){
		    	  newCode="12";
		      } 
		
		return newCode;
	}
}
