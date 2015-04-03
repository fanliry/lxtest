<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ricosoft.business.competenceMgr.UserMgr" %>
<%@ page import="com.ricosoft.entity.competenceMgr.UserInfo" %>
<%@ page import="com.wms3.bms.standard.service.BMSService" %>
<%@ page import="com.ricosoft.common.dao.dataSource.EntityDAO" %>
<html>
<head>
<title>仓储配送管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
	//设置单选框是否选中
	function setSel(i){

		var check_ids = document.getElementsByName("radio_id");
		check_ids[i].checked = true;
		
		//改变背景色
		for(var i=0; i<check_ids.length; i++){
			
			if(check_ids[i].checked){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
-->
</script>
</head>

<body>

  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr><td valign="top" height="100%">
	
 <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE1" width="50"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">用户代码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">用户名</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">部门代码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">是否有效</div></td>
     <td class="TD_LIST_TITLE2"><div class="list_title">备注</div></td>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0)
	{
		EntityDAO dao = BMSService.getm_EntityDAO();
		UserMgr userMgr=new UserMgr(dao);
		UserInfo tb;
		String id,s0,s1,s2,s3,s4,key;
		for(int i=0; i<ls.size(); i++)
		{
			tb = (UserInfo)ls.get(i);
		    id = tb.getM_strUserId();
			s0 = tb.getM_strUserCode();
			s1 = tb.getM_strUsername();
			s2 = tb.getM_strUnitCode();
			s3 = tb.getM_strUsableness();
			s4 = tb.getM_strRemark();
			key=id+"|"+s0+"|"+s1+"|"+s2+"|"+s3+"|"+s4;
			
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="parent.ondbClickDo('<%=key%>');">
     <td class="TD_LIST1" align="center"><input type="radio" name="radio_id" value="<%=key%>" class="input_radio"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=s0==null ? "":s0%></td>
     <td class="TD_LIST" align="center"><%=s1==null ? "":s1%></td>
     <td class="TD_LIST" align="center"><%=s2==null ? "":s2%></td>
     <td class="TD_LIST" align="center"><%=s3==null ? "":s3%></td>
     <td class="TD_LIST2" align="center"><%=s4==null ? "":s4%></td>
   </tr>
<%
      	}
	}
	else{
		session.removeAttribute("commpaging");
	}
%>
   <tr>
     <td height="100%" colspan="6" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
 </table>
<!-- ======== 分页标签 ======== -->
 </td><tr>
 <tr><td>
   <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr><td height="25px">
	  <%@ include file="commpage.jsp"%>
    </td><tr>
  </table>
</body>
</html>
