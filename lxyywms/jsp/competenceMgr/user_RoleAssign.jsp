<%@ page contentType="text/html; charset=GB2312"%>

<%@ page import="java.util.List" %>
<%@ page import="com.ricosoft.entity.competenceMgr.UserInfo" %>
<%@ page import="com.ricosoft.entity.competenceMgr.RoleInfo" %>




<html>
<head>
<title>用户角色分配</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
 <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/standard/css/table.css" rel="stylesheet" type="text/css">
</head>

<body>
<script>
<!--
	//保存修改的角色
    function saveAssignRole(userId){
    	var allCheck = getSelectCheckName();    	
		//window.location.href
		var strParam ='BMSService?actionCode=userInfoMgr&method=ricoExecRoleAdd&assignRole='+allCheck+'&userId='+userId;
		window.returnValue=strParam;
		        	
		 window.close();
    }
    
    
     
    function closeW(){
    	window.close();
    }
    
    function selectAll()
	{
	    var state=null;
		var length = document.form2.elements.length;
	
		for(i=0;i<length-1;i++){
		    if(document.form2.elements[i].name=='check_all'&& document.form2.elements[i].type=='checkbox'){
		         state = document.form2.elements[i].checked;
		         break;
		    }
		}
        
		for(i=0;i<length-1;i++){
		     if(document.form2.elements[i].type=='checkbox' && document.form2.elements[i].value=='checkbox'){
		     	   document.form2.elements[i].checked=state;
		     }
		}
	}
	
   function getSelectCheckName(){
		var strSelect = '';
		
		var length = document.form2.elements.length;
	
		for(i=1;i<length-1;i++){
		    if(document.form2.elements[i].name!='check_all'&& document.form2.elements[i].type=='checkbox' && document.form2.elements[i].checked==true && document.form2.elements[i].value=='checkbox'){
		         strSelect = strSelect + document.form2.elements[i].name + ',';
		    }
		}
		
		return strSelect;
	}
  -->
</script>
<%
	UserInfo user = null;
	String strUserId = "";
	String strUserCode = "";	//用户代码
	String strUserName = "";	//用户名
	String strUnitCode = "";	//科室名
	String strUserType = "";	//用户类型
	String strTel = "";			//联系电话
	String strEmail = "";		//Email
	String strHandset = "";		//手机号码
	String strAddress = "";		//联系地址
	String strCreateUser = "";	//创建人
	String strCreateTime = "";		//创建时间
	String strFlag = "";			//是否可用
	String strUserGroup = "";
	if(request.getAttribute("user") != null)
	{
		user = (UserInfo)request.getAttribute("user");
	}
	if(user != null)
	{
		strUserId = user.getM_strUserId();
		strUserCode = user.getM_strUserCode();
		strUserName = user.getM_strUsername();
		strUnitCode = user.getM_strUnitCode();
		strUserType = user.getM_strUserType();
		strTel = user.getM_strTel();
		strEmail = user.getM_strEmail();
		strHandset = user.getM_strHandset();
		strAddress = user.getM_strAddress();
		strCreateUser = user.getM_strCreateUser();
		strCreateTime = user.getM_strCreateTime();
		strFlag = user.getM_strUsableness();
		strUserGroup = user.getM_strUserGroupName();
	}
	if(strTel == null || strTel.equals("null"))
	{
		strTel = "&nbsp;";
	}
	if(strEmail == null || strEmail.equals("null"))
	{
		strEmail = "&nbsp;";
	}
	if(strHandset == null || strHandset.equals("null"))
	{
		strHandset = "&nbsp;";
	}
	if(strAddress == null || strAddress.equals("null"))
	{
		strAddress = "&nbsp;";
	}
	
%>

<form method="post" action="">
  <table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：系统设置 -&gt; 用户管理 -&gt; 用户角色分配</div></td>
   </tr>
 </table>
 
 
 <table width="90%"  border="0" align="center" cellpadding="0" cellspacing="0"  class="table_add">
   <tr>
     <td height="25" class="yx1"><div align="right">用户代码：</div></td>
     <td class="yx"><div align="left"><input name="userCode" type="text" size="20" readonly="true" value="<%=strUserCode%>" class="input_readonly"></div></td>
     <td class="yx1"><div align="right">用户名：</div></td>
     <td class="xx1"><div align="left"><input name="userName" type="text"  size="20" readonly="true" value="<%=strUserName%>" class="input_readonly">
     </div></td>
   </tr>
     <tr>
     <td height="25" class="yx1"><div align="right">手机号码：</div></td>
     <td class="yx"><div align="left"><input name="handset" type="text"   size="20" readonly="true" value="<%=strHandset%>" class="input_readonly"></div></td>
     <td class="yx1"><div align="right">联系电话：</div></td>
     <td class="xx1"><div align="left"><input name="tel" type="text"   size="20" readonly="true" value="<%=strTel%>" class="input_readonly"></div></td>
   </tr>
   
      <tr>
     <td height="25" class="yx1"><div align="right">用户组：</div></td>
     <td class="yx"><div align="left"><input name="createUser" type="text" size="20" readonly="true" value="<%=strUserGroup == null ? "" : strUserGroup%>" class="input_readonly"></div></td>
     <td class="yx1"><div align="right">Email：</div></td>
     <td class="xx1"><div align="left"><input name="Email" type="text"   size="20" readonly="true" value="<%=strEmail%>" class="input_readonly"></div></td>
   </tr>
      <tr>
     <td height="25" class="y2"><div align="right">联系地址：</div></td>
     <td class="x"><div align="left"><input name="address" type="text" size="20" readonly="true" value="<%=strAddress%>" class="input_readonly"></div></td>
     <td class="y2"><div align="right">可用：</div></td>
     <td><div align="left">
     <%
     	if(strFlag != null && strFlag.trim().equals("1"))
     	{
     %>
     		<input name="iflag" id="iflag" type="checkbox" checked="checked" disabled="true" class="input_readonly"> 
     <%
     	}else
     	{
     %>
     		<input name="iflag" id="iflag" type="checkbox"  disabled="true" class="input_readonly"> 
     <%
     	}
     %>
      
     </div></td>
   </tr>
   
 </table>
 
</form>
<form name="form2" method="post" action="">
   <table width="80%" height="20"  border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
	  <td><div align="left" class="font_001F56_12">可以对用户的下列角色进行分配:</div></td>
    </tr>
  </table>
 <table width="80%" height="50%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
 		<tr class="TD_LIST_TITLE">
	     
	     <td class="TD_LIST_TITLE" width="15%"><div class="list_title">
	       <input type="checkbox" name="check_all" value="checkbox" onclick="selectAll();">
	                            全选 
	     </div></td>
	     <td class="TD_LIST_TITLE" width="40%"><div class="list_title">角色名</div></td>
	     <td class="TD_LIST_TITLE" width="45%"><div class="list_title">备注</div></td>   
	   </tr>
	        <%
      
      List lsRole = null; 
      List lsUserRole = null;
      if(request.getAttribute("lsRole") != null)
      {
      	lsRole = (List)request.getAttribute("lsRole");
      }
      if(request.getAttribute("lsUserRole") != null)
      {
      	lsUserRole = (List)request.getAttribute("lsUserRole");
      }
      
		if(lsRole != null && !lsRole.isEmpty()) {
       
    	for(int i = 0; i < lsRole.size(); i++) { 
			    RoleInfo role = (RoleInfo)lsRole.get(i);   
			    
				String strRoleId = role.getM_strRoleId();
	 			String strRoleName = role.getM_strRoleName();
	 			String strRemark = role.getM_strRemark();
	 			String bCheck = "";
	 			if(strRoleName == null || strRoleName.trim().length()<1)
	 			{
	 				strRoleName = "&nbsp;";
	 			}
	 			if(strRemark == null || strRemark.trim().length()<1)
	 			{
	 				strRemark = "&nbsp;";
	 			}
	 			
	 			if(lsUserRole != null)
	 			{
		 			for(int j=0; j<lsUserRole.size(); j++)
		 			{
		 				if(strRoleId.trim().equals((String)lsUserRole.get(j))){
		 					bCheck = "checked";
		 					break;
		 				}
		 			}	
	 			}
	 		
		%>
	   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
		 <td class="TD_LIST1"><div align="center">
		   <input type="checkbox" name="<%=strRoleId%>" value="checkbox"  <%=bCheck%>>
		 </div></td>
		 <td class="TD_LIST1"><div align="center"><%=strRoleName%></div></td>
		 <td class="TD_LIST1"><div align="center"><%=strRemark%></div></td>
	
	   </tr>
	        <%		}	

			} 
		%>
	  <tr height="100%">
	  <td height="100%" colspan="8" class="TD_last_LIST" valign="top">
	    <div style="color: red; margin:5px;"><%if(lsUserRole != null && lsUserRole.size() == 0){%>无相关数据！<%}%></div>
	  </td>
	</tr>
	   
	 </table>

  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="40"><div align="center">   
	  <input type="button" name="Submit2" value="确定" onClick="saveAssignRole('<%=strUserId%>');" class="button_style">
        &nbsp;
      <input type="button" name="Submit" value="关闭" onClick="closeW();" class="button_style">
	   </div></td>
   </tr>
  </table>
  </form>
</body>
</html>