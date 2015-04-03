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
import com.wms3.bms.standard.business.base.ITypeBus;
import com.wms3.bms.standard.business.base.impl.TypeBusImpl;
import com.wms3.bms.standard.entity.base.BaseType;
/**
 * �������ֵ�����action
 * @author liuxh
 *
 */
public class DictionaryAction extends AbstractAction {
    protected ITypeBus iTypeBus;
	@Override
	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		HttpSession httpsession = request.getSession(false);
		String flag= CommonTools.getStrToGbk(request.getParameter("flag"));	//��λID
		String typeVlaue= CommonTools.getStrToGbk(request.getParameter("typeVlaue"));//����ֵ
		String typename = CommonTools.getStrToGbk(request.getParameter("typename"));
		String typeId= CommonTools.getStrToGbk(request.getParameter("typeId"));//����ֵ
		String strUserName = (String)httpsession.getAttribute("userName");
		String selecttext = CommonTools.getStrToGbk(request.getParameter("selecttext"));	//�����б���ʾ����
	    String selectvalue = CommonTools.getStrToGbk(request.getParameter("selectvalue"));
	    iTypeBus = new TypeBusImpl(dao);
	    String meg = "������Ϣ==>�ֵ�����==>ִ��DictionaryAction����:";
	    try {
	    	if (flag!=null&&flag.equals("1")) {//��ѯ�ֵ�
	    		meg += "����ѯ�ֵ�_>flag=1��";
				strUrl = "/standard/jsp/base/dictionary/base_dictionary_list.jsp";
				List<BaseType> ls = iTypeBus.getBaseType();
				request.setAttribute("exResultList", ls);		
			}
            if (flag!=null&&flag.equals("2")) {//��ѯ����
            	meg += "����ѯ����_>flag=2��";
				strUrl = "/standard/jsp/base/dictionary/base_dictionary_detail.jsp";
				List ls = iTypeBus.getTypeList(typeVlaue);
				request.setAttribute("exResultList", ls);		
			}
            if (flag!=null && flag.equals("3")) {//��������id������Ͷ���
            	meg += "����������id������Ͷ���_>flag=3��";
            	strUrl ="/standard/jsp/base/dictionary/base_dictionary_detail_update.jsp";
				BaseType bType = iTypeBus.getTypeById(typeId);
				request.setAttribute("type", bType);
			}
            if (flag!=null && flag.equals("4")) {//�������
            	meg += "���������_>flag=4��";
            	BaseType type = iTypeBus.getTypeById(typeId);
				type.setSelecttext(selecttext);
				type.setSelectvalue(selectvalue);
				
				iTypeBus.updateType(type);
				Logger.info("�û�" + strUserName + "�޸������͵������б�:" + typeId);
				
				strUrl = "/standard/jsp/base/dictionary/base_dictionary_detail.jsp";	
				List ls = iTypeBus.getTypeList(typeVlaue);
				request.setAttribute("exResultList", ls);
			}
            if (flag != null && flag.equals("5")){ 	//���������б�
            	meg += "�����������б�_>flag=5��";
				BaseType type = new BaseType();	
				type.setTypeid(typeId);
				type.setTypevalue(typeVlaue);
				type.setTypename(typename);
				type.setSelecttext(selecttext);
				type.setSelectvalue(selectvalue);
				type.setTypelevel("");	//"Y"��ʾϵͳ
				
				iTypeBus.addType(type);
				Logger.info("�û�" + strUserName + "���������:" + typename + "�������б�" + selecttext);
				
				strUrl = "/standard/jsp/base/dictionary/base_dictionary_detail.jsp";	
				List ls = iTypeBus.getTypeList(typeVlaue);
				request.setAttribute("exResultList", ls);
				
			}
            if(flag != null && flag.equals("6")){ 	//ɾ�����������б�
            	meg += "��ɾ�����������б�_>flag=6��";
				BaseType type = iTypeBus.getTypeById(typeId);
				iTypeBus.deleteType(type);
				Logger.info("�û�" + strUserName + "ɾ�������͵������б�:" + typeId);
				
				strUrl = "/standard/jsp/base/dictionary/base_dictionary_detail.jsp";	
				String[] temp = typeId.split("_");
				List ls = iTypeBus.getTypeList(temp[0]);
				request.setAttribute("exResultList", ls);
				
			}
	    	request.getRequestDispatcher(strUrl).forward(request, response);
		} catch (Exception er) {
			Logger.error(meg + er.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	
		return null;
	}

}
