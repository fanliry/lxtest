<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ricosoft.entity.competenceMgr.UserInfo" %>


<html>
<head>
  <title>管理系统</title>
  <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
  <link href="<%=request.getContextPath()%>/standard/css/table.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/userGroupTree.js"></script>
  <script type="text/javascript">
  <!--
	//全选
	function CheckAll(){
		var state = document.getElementById("checkbox_all").checked;
		var check_ids = document.getElementsByName("checkbox_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;		
			//改变背景色
			if(check_ids[i].checked){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	function updateFlag(id)
	{
		var flag = "N";
		if(document.getElementById(id).checked == true)
        {
        	   flag = "Y";
        }
       
		userGroupTree.updateUserFlag(id, flag);
	}
	//设置多选框是否选中
	function setSel(i){

		var check_ids = document.getElementsByName("checkbox_id");
		if(check_ids[i].checked){
			check_ids[i].checked = false;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "";
		}else{
			check_ids[i].checked = true;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
		}
	}
  -->
  </script>
</head>

<body onload="javascript:parent.myIframe1.location.reload();">
<%
	List ls = null;
	if(request.getAttribute("exResultList") != null)
	{
		ls = (List)request.getAttribute("exResultList");
	}
	
%>
<form id="myform" name="myform" method="post" action="">
   <table width="99%" height="100%"   border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
	<tr>
	  <td width="10%" class="TD_LIST_TITLE"><div class="list_title">
	    <input type="checkbox" name="checkbox_all" value="checkbox" class="input_checkbox" onclick="CheckAll()">全选
	 </div> </td>
	  <td width="10%" class="TD_LIST_TITLE"><div class="list_title">行号</div></td>
	  <td width="25%" class="TD_LIST_TITLE"><div class="list_title">用户名</div></td>
	  <td class="TD_LIST_TITLE" width="25%"><div class="list_title">用户代码</div></td>
      <td class="TD_LIST_TITLE" width="20%"><div class="list_title">用户组</div></td>
      <td class="TD_LIST_TITLE" width="10%"><div class="list_title">是否可用</div></td>
	</tr>
	  <%
	  	if(ls != null && !ls.isEmpty())
	  	{
	  	 	for(int i = 0; i < ls.size(); i++) 
	  	 	{
	  	 		UserInfo user = (UserInfo)ls.get(i);   
	  	 	    String strId = user.getM_strUserId(); 		//代码
	  	 	    String strUserName = user.getM_strUsername();
	  	 	    String strUserCode = user.getM_strUserCode();
	  	 	    String strUserGroup = user.getM_strUserGroup();
	  	 	    String strUsableness = user.getM_strUsableness();
	  	 	    
	  	 	    String strTel= user.getM_strTel();
	 			String strEmail= user.getM_strEmail();
	 			String strHandset= user.getM_strHandset();
	 			String strAddress= user.getM_strAddress();
	 			String strPassword = user.getM_strPassword();
	  		
	  			String strUserGroupName = user.getM_strUserGroupName();
	  			String strParam = "userId="+strId+
                        	   "&userCode="+strUserCode+
                        	   "&password="+strPassword+
                        	   "&userName="+strUserName+
                        	   "&tel="+(strTel == null ? "" : strTel)+
                        	   "&email="+(strEmail == null ? "" : strEmail)+
                        	   "&handset="+(strHandset == null ? "" : strHandset)+
                        	   "&address="+(strAddress == null ? "" : strHandset)+
                        	   "&usableness=" + (strUsableness == null ? "" : strUsableness)+
                        	   "&userGroup="+(strUserGroup == null ? "" : strUserGroup);
	  	%>
	  		<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" >
			  <td class="TD_LIST1" align="center">
			    <input type="checkbox" name="checkbox_id" value="<%=strId%>" class="input_checkbox" id="<%=strParam%>"onclick="setSel(<%=i%>)">
			  </td>
			  <td class="TD_LIST1"><%=i+1%></td>
			  <td class="TD_LIST1"><%=strUserName == null ? "" : strUserName%></td>
			  <td class="TD_LIST1"><%=strUserCode == null ? "" : strUserCode%></td>
			  <td class="TD_LIST1"><%=strUserGroupName == null ? "" : strUserGroupName%></td>
			  <td class="TD_LIST1" align="center"><input name="<%=strId%>" type="checkbox" <%=strUsableness.equals("Y") ? "checked" : ""%> onclick="updateFlag('<%=strId%>');"></td>
			</tr> 
	  	<%
	  	 	}
	  	 }else
	  	 {
	  	 	session.removeAttribute("paging");
	  	 }
	  %>
	

		<!-- ==== 最后一行（空行），必须有 ==== -->
	  <tr height="100%">
	  <td height="100%" colspan="8" class="TD_last_LIST" valign="top">
	    <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
	  </td>
	</tr>
  </table>
</form>

</body>
</html>
