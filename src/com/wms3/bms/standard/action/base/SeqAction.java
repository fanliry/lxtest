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
 * 描述:序列号
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
		
		String remark = CommonTools.getStrToGbk(request.getParameter("remark"));	//描述
		String seqId = CommonTools.getStrToGbk(request.getParameter("seqId"));		//序列ID
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		seqBus = new SeqBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")){	 //序列号 查询列表
			
				strUrl = "/standard/jsp/rule/seq/rule_seq_list.jsp";
				List ls = seqBus.getSeqQuery(remark);
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2")){	//序列号 修改时获取信息
			
				strUrl = "/standard/jsp/rule/seq/rule_seq_update.jsp";
				BaseSeq seq = seqBus.getSeqById(seqId);
				request.setAttribute("seq", seq); 
			}	
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("规则管理==>序列号==>序列号查询失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * 功能:修改序列号
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
		
		String seqId = CommonTools.getStrToGbk(request.getParameter("seqId"));			//序列ID
		String seqType = CommonTools.getStrToGbk(request.getParameter("seqType"));		//序列类型
		String seqRemark = CommonTools.getStrToGbk(request.getParameter("seqRemark"));	//描述
		String seqPrefix = CommonTools.getStrToGbk(request.getParameter("seqPrefix"));	//前缀
		String icodelength = CommonTools.getStrToGbk(request.getParameter("icodelength"));	//位数
		String seqValue = CommonTools.getStrToGbk(request.getParameter("seqValue"));	//值
		String strUserName = (String)httpsession.getAttribute("userName");
		
		seqBus = new SeqBusImpl(dao);
		try
		{
			if(seqId != null)
			{
				BaseSeq seq = seqBus.getSeqById(seqId);
				seq.setSeqType(seqType);		//序列类型
				seq.setSeqRemark(seqRemark);	//描述
				seq.setSeqPrefix(seqPrefix);	//前缀
				seq.setIcodelength(Integer.parseInt(icodelength));	//位数
				seq.setSeqValue(seqValue);		//值
				seqBus.updateSeq(seq);
				Logger.info("用户" + strUserName + "修改了序列号" + seqId);
			}
			
			strUrl = "/standard/jsp/rule/seq/rule_seq_list.jsp";
			List ls = seqBus.getSeqQuery("");
			request.setAttribute("exResultList", ls);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("规则管理==>序列号==>修改序列号失败:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}
