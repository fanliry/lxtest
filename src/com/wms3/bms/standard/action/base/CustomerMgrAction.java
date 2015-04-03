package com.wms3.bms.standard.action.base;



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
import com.wms3.bms.standard.entity.base.ClientFile;
/**
 * 
 * @author zhi
 *
 */
public class CustomerMgrAction extends AbstractAction{

	public String ricoExec(Hashtable hsSysParam, Hashtable hsCurrentParam) throws Exception {
		HttpServletRequest request = (HttpServletRequest)hsCurrentParam.get("request");
		HttpServletResponse response = (HttpServletResponse)hsCurrentParam.get("response");
		HttpSession httpsession = request.getSession();
		EntityDAO dao = (EntityDAO)hsSysParam.get("dao");
		
		String strUserCode = (String)request.getSession().getAttribute("userCode");
		
		String back_msg = "Y";
		//��������
		String method = CommonTools.getStrToGb2312(request.getParameter("methoder"));
		
		String flag = CommonTools.getStrToGb2312(request.getParameter("flag"));
		
		
		String ids = CommonTools.getStrToGb2312(request.getParameter("ids"));
		String customer_id = CommonTools.getStrToGb2312(request.getParameter("customer_id"));
		String short_name = CommonTools.getStrToGb2312(request.getParameter("short_name"));
		String full_name = CommonTools.getStrToGb2312(request.getParameter("full_name"));
		String telphone = CommonTools.getStrToGb2312(request.getParameter("telphone"));
		String fax = CommonTools.getStrToGb2312(request.getParameter("fax"));
		String out_id = CommonTools.getStrToGb2312(request.getParameter("out_id"));
		String is_use = CommonTools.getStrToGb2312(request.getParameter("is_use"));
		String link_man = CommonTools.getStrToGb2312(request.getParameter("link_man"));
		String address = CommonTools.getStrToGb2312(request.getParameter("address"));
		try{
			ClientFile cus = new ClientFile();
			if(method.trim().equals("add")){
				if(flag.equals("1")){
					strUrl = "/standard/jsp/base/dictionary/tab/customer_list.jsp";
					
					cus = new ClientFile();
					cus.setM_clientName(short_name);
					cus.setM_fullName(full_name);
					cus.setM_phone(telphone);
					cus.setM_fax(fax);
					cus.setM_linkman(link_man);
					cus.setM_strOutId(out_id);
					cus.setM_reveal(is_use);
					cus.setM_address(address);
					dao.save(cus);
					
					request.setAttribute("back_msg", back_msg);
				}
			}
			else if(method.trim().equals("delete")){
				if(flag.equals("1")){
					
					strUrl = "/standard/jsp/base/dictionary/tab/customer_list.jsp";
					
					String[] id = ids.split(",");
					for(int i=0; i<id.length; i++){
						cus = new ClientFile().getInfoById(dao, id[i]);
						if(cus != null){
							dao.delete(cus);
						}
					}
					request.setAttribute("back_msg", back_msg);
				}
			}
			else if(method.trim().equals("update")){
				if(flag.equals("1")){
					strUrl = "/standard/jsp/base/dictionary/tab/customer_list.jsp";
					
					cus = new ClientFile().getInfoById(dao, customer_id);
					cus.setM_clientName(short_name);
					cus.setM_fullName(full_name);
					cus.setM_phone(telphone);
					cus.setM_fax(fax);
					cus.setM_linkman(link_man);
					cus.setM_strOutId(out_id);
					cus.setM_reveal(is_use);
					cus.setM_address(address);
					dao.update(cus);
					
					request.setAttribute("back_msg", back_msg);
				}
			}
			else if(method.trim().equals("search")){
				if(flag.equals("1")){
					strUrl = "/jsp/report/customer_search.jsp";
					String hql = cus.getQueryHQL(short_name, link_man, address, null);
					List ls = dao.searchEntities(hql);
					request.setAttribute("list", ls);
				}
				else if(flag.equals("2")){/*��ѯͳ�� �ͻ����� ��ѯ*/
					strUrl = "/jsp/report/customer_list.jsp";
					String strCountSQL = cus.getCountHQL(short_name, link_man, address, null);
					String strQuerySQL = cus.getQueryHQL(short_name, link_man, address, null);
					PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, strUrl ,1, 30);
					//��ѯ���
					List ls = pt.getLsResult();
					
					request.setAttribute("exResultList", ls);
					request.setAttribute("pagingTool", pt);
					httpsession.setAttribute("paging", pt);
				}
				else if(flag.equals("3")){/*��ѯͳ�� �ͻ����� ����*/
					strUrl = "/jsp/report/customer_report.jsp";
					
					String strQuerySQL = cus.getQueryHQL(short_name, link_man, address, null);
					List ls = dao.searchEntities(strQuerySQL);
					request.setAttribute("exResultList", ls);
				}
				else if(flag.equals("4")){/*ѡ��ͻ�*/
					strUrl = "/jsp/maintenance/test2/custoer_list.jsp";
					
					String strCountSQL = cus.getCountHQL(short_name, link_man, address, null);
					String strQuerySQL = cus.getQueryHQL(short_name, link_man, address, null);
					PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, strUrl ,1, 30);
					//��ѯ���
					List ls = pt.getLsResult();
					
					request.setAttribute("exResultList", ls);
					request.setAttribute("pagingTool", pt);
					httpsession.setAttribute("paging", pt);
				}
				else if(flag.equals("5")){//������Ϣ �ͻ����� ��ѯ
					strUrl = "/standard/jsp/base/dictionary/tab/customer_list.jsp";
					
					String strCountSQL = cus.getCountHQL(short_name, link_man, address, out_id);
					String strQuerySQL = cus.getQueryHQL(short_name, link_man, address, out_id);
					PagingTool pt = CommonPagination.getPagingTool(dao, strCountSQL, strQuerySQL, strUrl ,1, 30);
					//��ѯ���
					List ls = pt.getLsResult();
					
					request.setAttribute("exResultList", ls);
					request.setAttribute("pagingTool", pt);
					httpsession.setAttribute("paging", pt);
				}
				else if(flag.equals("6")){//������Ϣ �ͻ����� �޸Ĳ�ѯ
					strUrl = "/standard/jsp/base/dictionary/tab/customer_update.jsp";
					
					cus = cus.getInfoById(dao, customer_id);
					
					request.setAttribute("Customer", cus);
				}
				else{
					Logger.error("δ�����method=" + method + "�е�flag=" + flag + "������");
				}
			}
			else{
				Logger.error("δ�����method=" + method + "������");
			}
			request.getRequestDispatcher(strUrl).forward(request, response);
		}
		catch(Exception ex){
			Logger.error("�ͻ�����ʧ�ܣ�" + ex.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		finally{
			Logger.info("�û�" + strUserCode + "ִ�У�method=" + method + "��flag=" + flag + "������");
		}
		return null;			
	}
}
