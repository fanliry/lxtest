package com.wms3.bms.standard.action.base;


import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.standard.entity.base.BranchMeans;


/**
 * ����������Ϣ����
 * @author xiaoguizi
 *
 */
public class CreateCodeAction extends AbstractAction {


	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");

		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		try{			
			//�����û���Ϣ
			//�����
			String strOjbect=CommonTools.getStrToGb2312(request.getParameter("object"));
			String strUrl = CommonTools.getStrToGb2312(request.getParameter("url"));	
			//�����ֶ�
			String strField=CommonTools.getStrToGb2312(request.getParameter("field"));
			BranchMeans bran = new BranchMeans();
			List ls =bran.getNumber(strOjbect,strField,dao);
			//��ñ��������
			String number="";
			if(ls.get(0)!=null)
			{
			 number=ls.get(0).toString();
			}
			//��ʼ������
			if(number=="")
			{
				number="0";
			}
		    int	iNumber=Integer.parseInt(number);
		    String nextNo = getCode(iNumber+1);
			request.setAttribute("number",nextNo);
			request.getRequestDispatcher(strUrl).forward(request, response);
		}
		catch(Exception ex)
		{
			Logger.error("�޸���Ϣʧ��:"+ex.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	/**
	 * ˵�������ݳ�������0�ĸ��� 
	 * @param num
	 * @return
	 */
	public static String getCode(int num){
		String nextNo = "";
		String temNo = String.valueOf(num);
		if(temNo.length() != 3){
			for(int i=0; i<3-temNo.length(); i++){
				nextNo = nextNo + "0";
			}
		}
		nextNo = nextNo + temNo;
		return nextNo;
	}


}
