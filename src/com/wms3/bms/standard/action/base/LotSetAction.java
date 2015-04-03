package com.wms3.bms.standard.action.base;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ricosoft.action.AbstractAction;
import com.ricosoft.common.dao.dataSource.EntityDAO;
import com.ricosoft.common.logger.Logger;
import com.ricosoft.common.tools.CommonTools;
import com.wms3.bms.standard.business.base.ILotSetBus;
import com.wms3.bms.standard.business.base.impl.LotSetBusImpl;
import com.wms3.bms.standard.entity.base.BaseLotSet;

/**
 * ����:�����������ù���
 * @author gui
 *
 */
public class LotSetAction extends AbstractAction
{
	protected ILotSetBus lotsetBus;
	
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String lottype = CommonTools.getStrToGb2312(request.getParameter("lottype"));	//����
		String flag = CommonTools.getStrToGbk(request.getParameter("flag"));
		
		lotsetBus = new LotSetBusImpl(dao);
		try
		{
			if(flag != null && flag.equals("1")) //�����������ù��� ��ѯ�б�
			{
				strUrl = "/standard/jsp/base/lot/base_lotset_list.jsp";
				
				List ls = lotsetBus.getLotsetType();
				
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("2")) //�����������ù��� ��ѯ��ϸ�б�
			{
				strUrl = "/standard/jsp/base/lot/base_lotset_detail.jsp";
				
				List ls = lotsetBus.getLotsetByType(lottype);
				
				request.setAttribute("exResultList", ls);
					
			}else if(flag != null && flag.equals("3"))//�����������ù��� �޸�ʱ��ȡ��Ϣ
			{
				strUrl = "/standard/jsp/base/lot/base_lotset_update.jsp";
				
				//������ϸ��Ϣ
				List ls = lotsetBus.getLotsetByType(lottype);
				
				request.setAttribute("lotset1", ls.get(0));
				request.setAttribute("lotset2", ls.get(1));
				request.setAttribute("lotset3", ls.get(2));
				request.setAttribute("lotset4", ls.get(3));
				
				request.setAttribute("lotset5", ls.get(4));
				request.setAttribute("lotset6", ls.get(5));
				request.setAttribute("lotset7", ls.get(6));
				request.setAttribute("lotset8", ls.get(7));
				
				request.setAttribute("lotset9", ls.get(8));
				request.setAttribute("lotset10", ls.get(9));
				request.setAttribute("lotset11", ls.get(10));
				request.setAttribute("lotset12", ls.get(11));
				
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>�����������ù���==>�����������ò�ѯʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:����������������
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecAdd(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String lottype = CommonTools.getStrToGb2312(request.getParameter("lottype"));	//����
		String remark = CommonTools.getStrToGb2312(request.getParameter("remark"));		//��ע
		
		String lotname1 = CommonTools.getStrToGb2312(request.getParameter("lotname1"));	//������������
		String lotid1 = CommonTools.getStrToGb2312(request.getParameter("lotid1"));		//��������ID
		String islot1 = CommonTools.getStrToGb2312(request.getParameter("islot1"));		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		String lotseq1 = CommonTools.getStrToGb2312(request.getParameter("lotseq1"));	//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		String lotname2 = CommonTools.getStrToGb2312(request.getParameter("lotname2"));	//������������
		String lotid2 = CommonTools.getStrToGb2312(request.getParameter("lotid2"));		//��������ID
		String islot2 = CommonTools.getStrToGb2312(request.getParameter("islot2"));		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		String lotseq2 = CommonTools.getStrToGb2312(request.getParameter("lotseq2"));	//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		String lotname3 = CommonTools.getStrToGb2312(request.getParameter("lotname3"));	//������������
		String lotid3 = CommonTools.getStrToGb2312(request.getParameter("lotid3"));		//��������ID
		String islot3 = CommonTools.getStrToGb2312(request.getParameter("islot3"));		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		String lotseq3 = CommonTools.getStrToGb2312(request.getParameter("lotseq3"));	//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		String lotname4 = CommonTools.getStrToGb2312(request.getParameter("lotname4"));	//������������
		String lotid4 = CommonTools.getStrToGb2312(request.getParameter("lotid4"));		//��������ID
		String islot4 = CommonTools.getStrToGb2312(request.getParameter("islot4"));		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		String lotseq4 = CommonTools.getStrToGb2312(request.getParameter("lotseq4"));	//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		String lotname5 = CommonTools.getStrToGb2312(request.getParameter("lotname5"));	//������������
		String lotid5 = CommonTools.getStrToGb2312(request.getParameter("lotid5"));		//��������ID
		String islot5 = CommonTools.getStrToGb2312(request.getParameter("islot5"));		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		String lotseq5 = CommonTools.getStrToGb2312(request.getParameter("lotseq5"));	//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		String lotname6 = CommonTools.getStrToGb2312(request.getParameter("lotname6"));	//������������
		String lotid6 = CommonTools.getStrToGb2312(request.getParameter("lotid6"));		//��������ID
		String islot6 = CommonTools.getStrToGb2312(request.getParameter("islot6"));		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		String lotseq6 = CommonTools.getStrToGb2312(request.getParameter("lotseq6"));	//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		String lotname7 = CommonTools.getStrToGb2312(request.getParameter("lotname7"));	//������������
		String lotid7 = CommonTools.getStrToGb2312(request.getParameter("lotid7"));		//��������ID
		String islot7 = CommonTools.getStrToGb2312(request.getParameter("islot7"));		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		String lotseq7 = CommonTools.getStrToGb2312(request.getParameter("lotseq7"));	//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		String lotname8 = CommonTools.getStrToGb2312(request.getParameter("lotname8"));	//������������
		String lotid8 = CommonTools.getStrToGb2312(request.getParameter("lotid8"));		//��������ID
		String islot8 = CommonTools.getStrToGb2312(request.getParameter("islot8"));		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		String lotseq8 = CommonTools.getStrToGb2312(request.getParameter("lotseq8"));	//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		String lotname9 = CommonTools.getStrToGb2312(request.getParameter("lotname9"));	//������������
		String lotid9 = CommonTools.getStrToGb2312(request.getParameter("lotid9"));		//��������ID
		String islot9 = CommonTools.getStrToGb2312(request.getParameter("islot9"));		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		String lotseq9 = CommonTools.getStrToGb2312(request.getParameter("lotseq9"));	//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		String lotname10 = CommonTools.getStrToGb2312(request.getParameter("lotname10"));	//������������
		String lotid10 = CommonTools.getStrToGb2312(request.getParameter("lotid10"));		//��������ID
		String islot10 = CommonTools.getStrToGb2312(request.getParameter("islot10"));		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		String lotseq10 = CommonTools.getStrToGb2312(request.getParameter("lotseq10"));		//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		String lotname11 = CommonTools.getStrToGb2312(request.getParameter("lotname11"));	//������������
		String lotid11 = CommonTools.getStrToGb2312(request.getParameter("lotid11"));		//��������ID
		String islot11 = CommonTools.getStrToGb2312(request.getParameter("islot11"));		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		String lotseq11 = CommonTools.getStrToGb2312(request.getParameter("lotseq11"));		//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		String lotname12 = CommonTools.getStrToGb2312(request.getParameter("lotname12"));	//������������
		String lotid12 = CommonTools.getStrToGb2312(request.getParameter("lotid12"));		//��������ID
		String islot12 = CommonTools.getStrToGb2312(request.getParameter("islot12"));		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		String lotseq12 = CommonTools.getStrToGb2312(request.getParameter("lotseq12"));		//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		String strUserName = (String)httpsession.getAttribute("userName");

		lotsetBus = new LotSetBusImpl(dao);
		try
		{
			String back_msg = "Y";
			//�жϸ����������Ƿ��Ѿ�����
			if(lotsetBus.isLotsetExist(lottype)){
				
				back_msg = "������͵����������Ѿ������ˣ�";
			}else{
				
				BaseLotSet lotset1 = new BaseLotSet();
				lotset1.setLottype(lottype);	//����
				lotset1.setRemark(remark);		//��ע
				lotset1.setLotname(lotname1);	//������������
				lotset1.setLotid(lotid1);		//��������ID
				lotset1.setIslot(islot1);		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
				lotset1.setLotseq(Integer.parseInt(lotseq1));		//��ʾ˳����߷���ͳ�Ƶ�˳��
	
				BaseLotSet lotset2 = new BaseLotSet();
				lotset2.setLottype(lottype);	//����
				lotset2.setRemark(remark);		//��ע
				lotset2.setLotname(lotname2);	//������������
				lotset2.setLotid(lotid2);		//��������ID
				lotset2.setIslot(islot2);		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
				lotset2.setLotseq(Integer.parseInt(lotseq2));		//��ʾ˳����߷���ͳ�Ƶ�˳��
				
				BaseLotSet lotset3 = new BaseLotSet();
				lotset3.setLottype(lottype);	//����
				lotset3.setRemark(remark);		//��ע
				lotset3.setLotname(lotname3);	//������������
				lotset3.setLotid(lotid3);		//��������ID
				lotset3.setIslot(islot3);		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
				lotset3.setLotseq(Integer.parseInt(lotseq3));		//��ʾ˳����߷���ͳ�Ƶ�˳��
				
				BaseLotSet lotset4 = new BaseLotSet();
				lotset4.setLottype(lottype);	//����
				lotset4.setRemark(remark);		//��ע
				lotset4.setLotname(lotname4);	//������������
				lotset4.setLotid(lotid4);		//��������ID
				lotset4.setIslot(islot4);		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
				lotset4.setLotseq(Integer.parseInt(lotseq4));		//��ʾ˳����߷���ͳ�Ƶ�˳��
				
				BaseLotSet lotset5 = new BaseLotSet();
				lotset5.setLottype(lottype);	//����
				lotset5.setRemark(remark);		//��ע
				lotset5.setLotname(lotname5);	//������������
				lotset5.setLotid(lotid5);		//��������ID
				lotset5.setIslot(islot5);		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
				lotset5.setLotseq(Integer.parseInt(lotseq5));		//��ʾ˳����߷���ͳ�Ƶ�˳��
				
				BaseLotSet lotset6 = new BaseLotSet();
				lotset6.setLottype(lottype);	//����
				lotset6.setRemark(remark);		//��ע
				lotset6.setLotname(lotname6);	//������������
				lotset6.setLotid(lotid6);		//��������ID
				lotset6.setIslot(islot6);		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
				lotset6.setLotseq(Integer.parseInt(lotseq6));		//��ʾ˳����߷���ͳ�Ƶ�˳��
				
				BaseLotSet lotset7 = new BaseLotSet();
				lotset7.setLottype(lottype);	//����
				lotset7.setRemark(remark);		//��ע
				lotset7.setLotname(lotname7);	//������������
				lotset7.setLotid(lotid7);		//��������ID
				lotset7.setIslot(islot7);		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
				lotset7.setLotseq(Integer.parseInt(lotseq7));		//��ʾ˳����߷���ͳ�Ƶ�˳��
				
				BaseLotSet lotset8 = new BaseLotSet();
				lotset8.setLottype(lottype);	//����
				lotset8.setRemark(remark);		//��ע
				lotset8.setLotname(lotname8);	//������������
				lotset8.setLotid(lotid8);		//��������ID
				lotset8.setIslot(islot8);		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
				lotset8.setLotseq(Integer.parseInt(lotseq8));		//��ʾ˳����߷���ͳ�Ƶ�˳��
				
				BaseLotSet lotset9 = new BaseLotSet();
				lotset9.setLottype(lottype);	//����
				lotset9.setRemark(remark);		//��ע
				lotset9.setLotname(lotname9);	//������������
				lotset9.setLotid(lotid9);		//��������ID
				lotset9.setIslot(islot9);		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
				lotset9.setLotseq(Integer.parseInt(lotseq9));		//��ʾ˳����߷���ͳ�Ƶ�˳��
				
				BaseLotSet lotset10 = new BaseLotSet();
				lotset10.setLottype(lottype);	//����
				lotset10.setRemark(remark);		//��ע
				lotset10.setLotname(lotname10);	//������������
				lotset10.setLotid(lotid10);		//��������ID
				lotset10.setIslot(islot10);		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
				lotset10.setLotseq(Integer.parseInt(lotseq10));		//��ʾ˳����߷���ͳ�Ƶ�˳��
				
				BaseLotSet lotset11 = new BaseLotSet();
				lotset11.setLottype(lottype);	//����
				lotset11.setRemark(remark);		//��ע
				lotset11.setLotname(lotname11);	//������������
				lotset11.setLotid(lotid11);		//��������ID
				lotset11.setIslot(islot11);		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
				lotset11.setLotseq(Integer.parseInt(lotseq11));		//��ʾ˳����߷���ͳ�Ƶ�˳��
				
				BaseLotSet lotset12 = new BaseLotSet();
				lotset12.setLottype(lottype);	//����
				lotset12.setRemark(remark);		//��ע
				lotset12.setLotname(lotname12);	//������������
				lotset12.setLotid(lotid12);		//��������ID
				lotset12.setIslot(islot12);		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
				lotset12.setLotseq(Integer.parseInt(lotseq12));		//��ʾ˳����߷���ͳ�Ƶ�˳��
				
				lotsetBus.addLotset(lotset1, lotset2, lotset3, lotset4, lotset5, lotset6, lotset7, lotset8, lotset9, lotset10, lotset11, lotset12);
				
				Logger.info("�û�" + strUserName + "����������������ã�����" + lottype);
                
                //������ʾ������
                HashMap<String, List> hsLot = lotsetBus.getViewLot();//��ʾ����������  
                hsSysParam.put("viewLot", hsLot);
                httpsession.setAttribute("viewLot",hsLot );     //��ʾ������
                
			}
			strUrl = "/standard/jsp/base/lot/base_lotset_list.jsp";
			
			List ls = lotsetBus.getLotsetType();
			
			request.setAttribute("exResultList", ls);
			request.setAttribute("back_msg", back_msg);
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>�����������ù���==>����������������ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:�޸�������������
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
		
		String lottype = CommonTools.getStrToGb2312(request.getParameter("lottype"));		//����
		String remark = CommonTools.getStrToGb2312(request.getParameter("remark"));			//��ע
		
		String lotattid1 = CommonTools.getStrToGb2312(request.getParameter("lotattid1"));	//ID
		String lotname1 = CommonTools.getStrToGb2312(request.getParameter("lotname1"));		//������������
		String lotid1 = CommonTools.getStrToGb2312(request.getParameter("lotid1"));			//��������ID
		String islot1 = CommonTools.getStrToGb2312(request.getParameter("islot1"));			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		String lotseq1 = CommonTools.getStrToGb2312(request.getParameter("lotseq1"));		//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		String lotattid2 = CommonTools.getStrToGb2312(request.getParameter("lotattid2"));	//ID
		String lotname2 = CommonTools.getStrToGb2312(request.getParameter("lotname2"));		//������������
		String lotid2 = CommonTools.getStrToGb2312(request.getParameter("lotid2"));			//��������ID
		String islot2 = CommonTools.getStrToGb2312(request.getParameter("islot2"));			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		String lotseq2 = CommonTools.getStrToGb2312(request.getParameter("lotseq2"));		//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		String lotattid3 = CommonTools.getStrToGb2312(request.getParameter("lotattid3"));	//ID
		String lotname3 = CommonTools.getStrToGb2312(request.getParameter("lotname3"));		//������������
		String lotid3 = CommonTools.getStrToGb2312(request.getParameter("lotid3"));			//��������ID
		String islot3 = CommonTools.getStrToGb2312(request.getParameter("islot3"));			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		String lotseq3 = CommonTools.getStrToGb2312(request.getParameter("lotseq3"));		//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		String lotattid4 = CommonTools.getStrToGb2312(request.getParameter("lotattid4"));	//ID
		String lotname4 = CommonTools.getStrToGb2312(request.getParameter("lotname4"));		//������������
		String lotid4 = CommonTools.getStrToGb2312(request.getParameter("lotid4"));			//��������ID
		String islot4 = CommonTools.getStrToGb2312(request.getParameter("islot4"));			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		String lotseq4 = CommonTools.getStrToGb2312(request.getParameter("lotseq4"));		//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		String lotattid5 = CommonTools.getStrToGb2312(request.getParameter("lotattid5"));	//ID
		String lotname5 = CommonTools.getStrToGb2312(request.getParameter("lotname5"));		//������������
		String lotid5 = CommonTools.getStrToGb2312(request.getParameter("lotid5"));			//��������ID
		String islot5 = CommonTools.getStrToGb2312(request.getParameter("islot5"));			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		String lotseq5 = CommonTools.getStrToGb2312(request.getParameter("lotseq5"));		//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		String lotattid6 = CommonTools.getStrToGb2312(request.getParameter("lotattid6"));	//ID
		String lotname6 = CommonTools.getStrToGb2312(request.getParameter("lotname6"));		//������������
		String lotid6 = CommonTools.getStrToGb2312(request.getParameter("lotid6"));			//��������ID
		String islot6 = CommonTools.getStrToGb2312(request.getParameter("islot6"));			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		String lotseq6 = CommonTools.getStrToGb2312(request.getParameter("lotseq6"));		//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		String lotattid7 = CommonTools.getStrToGb2312(request.getParameter("lotattid7"));	//ID
		String lotname7 = CommonTools.getStrToGb2312(request.getParameter("lotname7"));		//������������
		String lotid7 = CommonTools.getStrToGb2312(request.getParameter("lotid7"));			//��������ID
		String islot7 = CommonTools.getStrToGb2312(request.getParameter("islot7"));			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		String lotseq7 = CommonTools.getStrToGb2312(request.getParameter("lotseq7"));		//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		String lotattid8 = CommonTools.getStrToGb2312(request.getParameter("lotattid8"));	//ID
		String lotname8 = CommonTools.getStrToGb2312(request.getParameter("lotname8"));		//������������
		String lotid8 = CommonTools.getStrToGb2312(request.getParameter("lotid8"));			//��������ID
		String islot8 = CommonTools.getStrToGb2312(request.getParameter("islot8"));			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		String lotseq8 = CommonTools.getStrToGb2312(request.getParameter("lotseq8"));		//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		String lotattid9 = CommonTools.getStrToGb2312(request.getParameter("lotattid9"));	//ID
		String lotname9 = CommonTools.getStrToGb2312(request.getParameter("lotname9"));		//������������
		String lotid9 = CommonTools.getStrToGb2312(request.getParameter("lotid9"));			//��������ID
		String islot9 = CommonTools.getStrToGb2312(request.getParameter("islot9"));			//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		String lotseq9 = CommonTools.getStrToGb2312(request.getParameter("lotseq9"));		//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		String lotattid10 = CommonTools.getStrToGb2312(request.getParameter("lotattid10"));	//ID
		String lotname10 = CommonTools.getStrToGb2312(request.getParameter("lotname10"));	//������������
		String lotid10 = CommonTools.getStrToGb2312(request.getParameter("lotid10"));		//��������ID
		String islot10 = CommonTools.getStrToGb2312(request.getParameter("islot10"));		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		String lotseq10 = CommonTools.getStrToGb2312(request.getParameter("lotseq10"));		//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		String lotattid11 = CommonTools.getStrToGb2312(request.getParameter("lotattid11"));	//ID
		String lotname11 = CommonTools.getStrToGb2312(request.getParameter("lotname11"));	//������������
		String lotid11 = CommonTools.getStrToGb2312(request.getParameter("lotid11"));		//��������ID
		String islot11 = CommonTools.getStrToGb2312(request.getParameter("islot11"));		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		String lotseq11 = CommonTools.getStrToGb2312(request.getParameter("lotseq11"));		//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		String lotattid12 = CommonTools.getStrToGb2312(request.getParameter("lotattid12"));	//ID
		String lotname12 = CommonTools.getStrToGb2312(request.getParameter("lotname12"));	//������������
		String lotid12 = CommonTools.getStrToGb2312(request.getParameter("lotid12"));		//��������ID
		String islot12 = CommonTools.getStrToGb2312(request.getParameter("islot12"));		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
		String lotseq12 = CommonTools.getStrToGb2312(request.getParameter("lotseq12"));		//��ʾ˳����߷���ͳ�Ƶ�˳��
		
		String strUserName = (String)httpsession.getAttribute("userName");

		lotsetBus = new LotSetBusImpl(dao);
		try
		{
			BaseLotSet lotset1 = lotsetBus.getLotsetById(lotattid1);
			lotset1.setLottype(lottype);	//����
			lotset1.setRemark(remark);		//��ע
			lotset1.setLotname(lotname1);	//������������
			lotset1.setLotid(lotid1);		//��������ID
			lotset1.setIslot(islot1);		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
			lotset1.setLotseq(Integer.parseInt(lotseq1));		//��ʾ˳����߷���ͳ�Ƶ�˳��

			BaseLotSet lotset2 = lotsetBus.getLotsetById(lotattid2);
			lotset2.setLottype(lottype);	//����
			lotset2.setRemark(remark);		//��ע
			lotset2.setLotname(lotname2);	//������������
			lotset2.setLotid(lotid2);		//��������ID
			lotset2.setIslot(islot2);		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
			lotset2.setLotseq(Integer.parseInt(lotseq2));		//��ʾ˳����߷���ͳ�Ƶ�˳��
			
			BaseLotSet lotset3 = lotsetBus.getLotsetById(lotattid3);
			lotset3.setLottype(lottype);	//����
			lotset3.setRemark(remark);		//��ע
			lotset3.setLotname(lotname3);	//������������
			lotset3.setLotid(lotid3);		//��������ID
			lotset3.setIslot(islot3);		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
			lotset3.setLotseq(Integer.parseInt(lotseq3));		//��ʾ˳����߷���ͳ�Ƶ�˳��
			
			BaseLotSet lotset4 = lotsetBus.getLotsetById(lotattid4);
			lotset4.setLottype(lottype);	//����
			lotset4.setRemark(remark);		//��ע
			lotset4.setLotname(lotname4);	//������������
			lotset4.setLotid(lotid4);		//��������ID
			lotset4.setIslot(islot4);		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
			lotset4.setLotseq(Integer.parseInt(lotseq4));		//��ʾ˳����߷���ͳ�Ƶ�˳��
			
			BaseLotSet lotset5 = lotsetBus.getLotsetById(lotattid5);
			lotset5.setLottype(lottype);	//����
			lotset5.setRemark(remark);		//��ע
			lotset5.setLotname(lotname5);	//������������
			lotset5.setLotid(lotid5);		//��������ID
			lotset5.setIslot(islot5);		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
			lotset5.setLotseq(Integer.parseInt(lotseq5));		//��ʾ˳����߷���ͳ�Ƶ�˳��
			
			BaseLotSet lotset6 = lotsetBus.getLotsetById(lotattid6);
			lotset6.setLottype(lottype);	//����
			lotset6.setRemark(remark);		//��ע
			lotset6.setLotname(lotname6);	//������������
			lotset6.setLotid(lotid6);		//��������ID
			lotset6.setIslot(islot6);		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
			lotset6.setLotseq(Integer.parseInt(lotseq6));		//��ʾ˳����߷���ͳ�Ƶ�˳��
			
			BaseLotSet lotset7 = lotsetBus.getLotsetById(lotattid7);
			lotset7.setLottype(lottype);	//����
			lotset7.setRemark(remark);		//��ע
			lotset7.setLotname(lotname7);	//������������
			lotset7.setLotid(lotid7);		//��������ID
			lotset7.setIslot(islot7);		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
			lotset7.setLotseq(Integer.parseInt(lotseq7));		//��ʾ˳����߷���ͳ�Ƶ�˳��
			
			BaseLotSet lotset8 = lotsetBus.getLotsetById(lotattid8);
			lotset8.setLottype(lottype);	//����
			lotset8.setRemark(remark);		//��ע
			lotset8.setLotname(lotname8);	//������������
			lotset8.setLotid(lotid8);		//��������ID
			lotset8.setIslot(islot8);		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
			lotset8.setLotseq(Integer.parseInt(lotseq8));		//��ʾ˳����߷���ͳ�Ƶ�˳��
			
			BaseLotSet lotset9 = lotsetBus.getLotsetById(lotattid9);
			lotset9.setLottype(lottype);	//����
			lotset9.setRemark(remark);		//��ע
			lotset9.setLotname(lotname9);	//������������
			lotset9.setLotid(lotid9);		//��������ID
			lotset9.setIslot(islot9);		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
			lotset9.setLotseq(Integer.parseInt(lotseq9));		//��ʾ˳����߷���ͳ�Ƶ�˳��
			
			BaseLotSet lotset10 = lotsetBus.getLotsetById(lotattid10);
			lotset10.setLottype(lottype);	//����
			lotset10.setRemark(remark);		//��ע
			lotset10.setLotname(lotname10);	//������������
			lotset10.setLotid(lotid10);		//��������ID
			lotset10.setIslot(islot10);		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
			lotset10.setLotseq(Integer.parseInt(lotseq10));		//��ʾ˳����߷���ͳ�Ƶ�˳��
			
			BaseLotSet lotset11 = lotsetBus.getLotsetById(lotattid11);
			lotset11.setLottype(lottype);	//����
			lotset11.setRemark(remark);		//��ע
			lotset11.setLotname(lotname11);	//������������
			lotset11.setLotid(lotid11);		//��������ID
			lotset11.setIslot(islot11);		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
			lotset11.setLotseq(Integer.parseInt(lotseq11));		//��ʾ˳����߷���ͳ�Ƶ�˳��
			
			BaseLotSet lotset12 = lotsetBus.getLotsetById(lotattid12);
			lotset12.setLottype(lottype);	//����
			lotset12.setRemark(remark);		//��ע
			lotset12.setLotname(lotname12);	//������������
			lotset12.setLotid(lotid12);		//��������ID
			lotset12.setIslot(islot12);		//�Ƿ���ʾ�����Ƿ����ͳ�� Y N
			lotset12.setLotseq(Integer.parseInt(lotseq12));		//��ʾ˳����߷���ͳ�Ƶ�˳��
			
			lotsetBus.updateLotset(lotset1, lotset2, lotset3, lotset4, lotset5, lotset6, lotset7, lotset8, lotset9, lotset10, lotset11, lotset12);
			
			Logger.info("�û�" + strUserName + "�޸��������������ã�����" + lottype);
            
            //������ʾ������
            HashMap<String, List> hsLot = lotsetBus.getViewLot();//��ʾ����������  
            hsSysParam.put("viewLot", hsLot);
            httpsession.setAttribute("viewLot",hsLot );     //��ʾ������
            

			strUrl = "/standard/jsp/base/lot/base_lotset_list.jsp";
			
			List ls = lotsetBus.getLotsetType();
			
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>�����������ù���==>�޸�������������ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
	
	/**
	 * ����:ɾ��������������
	 * @param hsSysParam
	 * @param hsCurrentParam
	 * @return
	 * @throws Exception
	 */
	public String ricoExecDelete(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception 
	{
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession(false);
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String lottype = CommonTools.getStrToGb2312(request.getParameter("lottype"));	//����
		String strUserName = (String)httpsession.getAttribute("userName");

		lotsetBus = new LotSetBusImpl(dao);
		try
		{
			//ɾ��
			lotsetBus.deleteLotset(lottype);	
			Logger.info("�û�" + strUserName + "ɾ�������������裬���ͣ�" + lottype);
            
            //������ʾ������
            HashMap<String, List> hsLot = lotsetBus.getViewLot();//��ʾ����������  
            hsSysParam.put("viewLot", hsLot);
            httpsession.setAttribute("viewLot",hsLot );     //��ʾ������
			
			strUrl = "/standard/jsp/base/lot/base_lotset_list.jsp";
			
			List ls = lotsetBus.getLotsetType();
			
			request.setAttribute("exResultList", ls);
			
			request.getRequestDispatcher(strUrl).forward(request, response);
			
		}catch(Exception er)
		{
			Logger.error("������Ϣ==>�����������ù���==>������������ɾ��ʧ��:" + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		return null;
	}
}