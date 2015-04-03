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
 * 描述：字典中心action
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
		String flag= CommonTools.getStrToGbk(request.getParameter("flag"));	//货位ID
		String typeVlaue= CommonTools.getStrToGbk(request.getParameter("typeVlaue"));//类型值
		String typename = CommonTools.getStrToGbk(request.getParameter("typename"));
		String typeId= CommonTools.getStrToGbk(request.getParameter("typeId"));//类型值
		String strUserName = (String)httpsession.getAttribute("userName");
		String selecttext = CommonTools.getStrToGbk(request.getParameter("selecttext"));	//下拉列表显示内容
	    String selectvalue = CommonTools.getStrToGbk(request.getParameter("selectvalue"));
	    iTypeBus = new TypeBusImpl(dao);
	    String meg = "基本信息==>字典中心==>执行DictionaryAction出错:";
	    try {
	    	if (flag!=null&&flag.equals("1")) {//查询字典
	    		meg += "（查询字典_>flag=1）";
				strUrl = "/standard/jsp/base/dictionary/base_dictionary_list.jsp";
				List<BaseType> ls = iTypeBus.getBaseType();
				request.setAttribute("exResultList", ls);		
			}
            if (flag!=null&&flag.equals("2")) {//查询类型
            	meg += "（查询类型_>flag=2）";
				strUrl = "/standard/jsp/base/dictionary/base_dictionary_detail.jsp";
				List ls = iTypeBus.getTypeList(typeVlaue);
				request.setAttribute("exResultList", ls);		
			}
            if (flag!=null && flag.equals("3")) {//根据类型id获得类型对象
            	meg += "（根据类型id获得类型对象_>flag=3）";
            	strUrl ="/standard/jsp/base/dictionary/base_dictionary_detail_update.jsp";
				BaseType bType = iTypeBus.getTypeById(typeId);
				request.setAttribute("type", bType);
			}
            if (flag!=null && flag.equals("4")) {//保存更新
            	meg += "（保存更新_>flag=4）";
            	BaseType type = iTypeBus.getTypeById(typeId);
				type.setSelecttext(selecttext);
				type.setSelectvalue(selectvalue);
				
				iTypeBus.updateType(type);
				Logger.info("用户" + strUserName + "修改了类型的下拉列表:" + typeId);
				
				strUrl = "/standard/jsp/base/dictionary/base_dictionary_detail.jsp";	
				List ls = iTypeBus.getTypeList(typeVlaue);
				request.setAttribute("exResultList", ls);
			}
            if (flag != null && flag.equals("5")){ 	//增加类型列表
            	meg += "（增加类型列表_>flag=5）";
				BaseType type = new BaseType();	
				type.setTypeid(typeId);
				type.setTypevalue(typeVlaue);
				type.setTypename(typename);
				type.setSelecttext(selecttext);
				type.setSelectvalue(selectvalue);
				type.setTypelevel("");	//"Y"表示系统
				
				iTypeBus.addType(type);
				Logger.info("用户" + strUserName + "添加了类型:" + typename + "的下拉列表：" + selecttext);
				
				strUrl = "/standard/jsp/base/dictionary/base_dictionary_detail.jsp";	
				List ls = iTypeBus.getTypeList(typeVlaue);
				request.setAttribute("exResultList", ls);
				
			}
            if(flag != null && flag.equals("6")){ 	//删除类型下拉列表
            	meg += "（删除类型下拉列表_>flag=6）";
				BaseType type = iTypeBus.getTypeById(typeId);
				iTypeBus.deleteType(type);
				Logger.info("用户" + strUserName + "删除了类型的下拉列表:" + typeId);
				
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
