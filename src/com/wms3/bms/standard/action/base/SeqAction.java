package com.wms3.bms.standard.action.base;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.standard.business.base.ISeqBus;
import com.wms3.bms.standard.business.base.impl.SeqBusImpl;
import com.wms3.bms.standard.entity.base.BaseSeq;
/**
 * ����:���к�
 * @author gui
 *
 */
public class SeqAction extends AbstractAction
{
	protected ISeqBus seqBus;
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String remark = CommonTools.getStrToGbk(request.getParameter("remark"));	//����
		String seqId = CommonTools.getStrToGbk(request.getParameter("seqId"));		//����ID
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		seqBus = new SeqBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")){	 //���к� ��ѯ�б�
			
				strUrl = "/standard/jsp/rule/seq/rule_seq_list.jsp";
				List ls = seqBus.getSeqQuery(remark);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2")){	//���к� �޸�ʱ��ȡ��Ϣ
			
				strUrl = "/standard/jsp/rule/seq/rule_seq_update.jsp";
				BaseSeq seq = seqBus.getSeqById(seqId);
				request.setAttribute("seq", seq); 
			}	
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("�������==>���к�==>���кŲ�ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�޸����к�
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecEdit(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String seqId = CommonTools.getStrToGbk(request.getParameter("seqId"));			//����ID
		String seqType = CommonTools.getStrToGbk(request.getParameter("seqType"));		//��������
		String seqRemark = CommonTools.getStrToGbk(request.getParameter("seqRemark"));	//����
		String seqPrefix = CommonTools.getStrToGbk(request.getParameter("seqPrefix"));	//ǰ׺
		String icodelength = CommonTools.getStrToGbk(request.getParameter("icodelength"));	//λ��
		String seqValue = CommonTools.getStrToGbk(request.getParameter("seqValue"));	//ֵ
		String strUserName = (String)httpsession.getAttribute("userName");
		
		seqBus = new SeqBusImpl(dao);
		try
		{
			if(seqId != null)
			{
				BaseSeq seq = seqBus.getSeqById(seqId);
				seq.setSeqType(seqType);		//��������
				seq.setSeqRemark(seqRemark);	//����
				seq.setSeqPrefix(seqPrefix);	//ǰ׺
				seq.setIcodelength(Integer.parseInt(icodelength));	//λ��
				seq.setSeqValue(seqValue);		//ֵ
				seqBus.updateSeq(seq);
				Logger.info("�û�" + strUserName + "�޸������к�" + seqId);
			}
			
			strUrl = "/standard/jsp/rule/seq/rule_seq_list.jsp";
			List ls = seqBus.getSeqQuery("");
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("�������==>���к�==>�޸����к�ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
