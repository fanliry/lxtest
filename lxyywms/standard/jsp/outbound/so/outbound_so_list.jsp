<%@page import="com.wms3.bms.standard.entity.outerInterface.SaleForm"%>
<%@page	import="com.wms3.bms.standard.entity.outerInterface.SaleFormDetail"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=GBK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>���ⶩ����Ϣ</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="<%=request.getContextPath()%>/standard/style/style.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	//���õ�ѡ���Ƿ�ѡ��
	function setSel(i){
		var check_ids = document.getElementsByName("check_id");
		check_ids[i].checked = true;
		var strPram = null;
						
		//�ı䱳��ɫ
		for(var i=0; i<check_ids.length; i++){
			
			if(check_ids[i].checked){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
				strPram = check_ids[i].value;
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	    queryDetail(strPram);
	}
	//��ѯ��ϸ
	function queryDetail(id){
		parent.detail.location.href = ac + "SaleFormAction&flag=2&m_strSaleFormId=" + id;
	}
	function OnLoad(){
		parent.page.location.href="<%=request.getContextPath()%>/standard/jsp/page/page.jsp";
	}
	</script>
</head>

<body onLoad="OnLoad()">
	<table width="100%" height="100%" border="0" align="center"
		cellpadding="0" cellspacing="0" class="TABLE_LIST">
		<tr>
			<td class="TD_LIST_TITLE" width="50"><div class="list_title">ѡ��</div></td>
			<td class="TD_LIST_TITLE"><div class="list_title">����</div></td>
			<!-- <td class="TD_LIST_TITLE"><div class="list_title">�Ƿ������</div></td> -->
			<td class="TD_LIST_TITLE"><div class="list_title">������</div></td>
			<!-- <td class="TD_LIST_TITLE"><div class="list_title">�Ƿ���ɳ���</div></td> -->
			<td class="TD_LIST_TITLE"><div class="list_title">�ͻ�</div></td>
			<td class="TD_LIST_TITLE"><div class="list_title">����ʱ��</div></td>
		</tr>
		<%
	List<SaleForm> ls = (List<SaleForm>)request.getAttribute("exResultList");
		if(ls != null && ls.size() > 0) {
			SaleForm soHeader = null;
			String m_strSaleFormId = "";	//���۶�����
			String m_strSaleFormTypeName = "";	//��������
			String m_strCustomer = "";		//�ͻ�
			String m_strDownTime = "";		//��������ʱ��
			String m_strIsOut = "";			//�Ƿ������
			String m_strIsFinish = "";		//�Ƿ���ɳ���
			String m_strIsInvalid = "";		//�Ƿ���Ч

			for(int i=0;i<ls.size();i++){
				soHeader = ls.get(i);
				m_strSaleFormId = soHeader.getM_strSaleFormId();
				m_strSaleFormTypeName = soHeader.getM_strSaleFormTypeName();
				m_strCustomer = soHeader.getM_strCustomerName();
				m_strDownTime = soHeader.getM_strDownTime();
				m_strIsOut = soHeader.getM_strIsOut();
				m_strIsFinish = soHeader.getM_strIsFinish();
				m_strIsInvalid = soHeader.getM_strIsInvalid();
 %>
		<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''"
			onclick="setSel(<%=i%>)">
			<td class="TD_LIST1" align="center"><input type="radio"	name="check_id" value="<%=m_strSaleFormId %>" class="input_checkbox"><%=i+1%></td>
			<td class="TD_LIST" align="center"><%=m_strSaleFormTypeName==null?"":m_strSaleFormTypeName %></td>
			<%-- <td class="TD_LIST" align="center"><%=m_strIsOut==null?"":m_strIsOut %></td> --%>
			<td class="TD_LIST" align="center"><%=m_strSaleFormId==null?"":m_strSaleFormId %></td>
			<%-- <td class="TD_LIST" align="center"><%=m_strIsFinish==null?"":m_strIsFinish %></td> --%>
			<td class="TD_LIST" align="center"><%=m_strCustomer==null?"":m_strCustomer %></td>
			<td class="TD_LIST" align="center"><%=m_strDownTime==null?"":m_strDownTime %></td>
		</tr>
		<%			}
	}
%>
		<tr>
			<td height="100%" colspan="7" class="TD_last_LIST" valign="top">
				<div style="color: red; margin: 5px;">
					<%if(ls != null && ls.size() == 0){%>��������ݣ�<%}%>
				</div>
			</td>
		</tr>

	</table>
</body>
</html>
