<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="com.ricosoft.entity.competenceMgr.UserInfo" %>


<html>
<head>
<title>用户权限分配</title>
 <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/standard/css/table.css" rel="stylesheet" type="text/css">
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/standard/css/dhtmlXTree.css">
<script  src="<%=request.getContextPath()%>/standard/js/hashMap.js"></script>
<script  src="<%=request.getContextPath()%>/standard/js/dhtmlXCommon.js"></script>
<script  src="<%=request.getContextPath()%>/standard/js/dhtmlXTree.js"></script>	
<script  src="<%=request.getContextPath()%>/standard/js/tools.js"></script>	
</head>

<body>
<script>
<!--
	
	//保存修改的权限
    function saveAssignPopedom(userId){
    	var allCheck = tree.getAllChecked()+","+tree.getAllPartiallyChecked();
    	
    	//alert('allCheck='+allCheck);
    	
		allCheck = allCheck+',';
		
		var strParam ='BMSService?actionCode=userInfoMgr&method=ricoExecModuleAdd&assignPopedom='+allCheck
							+'&userId='+userId;
	
		window.returnValue=strParam;
		        	
		 window.close();
    }
        
    function closeW(){
    	window.close();
    }
  -->
</script>
<%
        String mgrLoad="";
		
		UserInfo user = null;  
  		String strUserId  ="";
  		String strUserCode ="";
  		String strUserName = "";
  		String strUnitCode = "";
  		String strTel = "";
  		String strEmail = "";
  		String strHandset= "";
  		String strAddress= "";
  		String strUserType= ""; 
  		String strCreateTime="";
  		String strCreateUser="";
 		String strFlag = "";
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
		strTel = user.getM_strTel();
		strEmail = user.getM_strEmail();
		strHandset = user.getM_strHandset();
		strAddress = user.getM_strAddress();
		strUserType = user.getM_strUserType();
		strCreateTime = user.getM_strCreateTime();
		strCreateUser = user.getM_strCreateUser();
		strFlag = user.getM_strUsableness();
		strUserGroup = user.getM_strUserGroupName();
	}

   	if(strTel == null || strTel.trim().equals("null"))
   {
   			strTel = "&nbsp;";
   } 
   	if(strHandset == null  ||strHandset.trim().equals("null"))
   {
   			strHandset = "&nbsp;";
   } 
   	if(strEmail == null || strEmail.trim().equals("null"))
   {
   			strEmail = "&nbsp;";
   } 
   	if(strAddress == null || strAddress.trim().equals("null"))
   {
   			strAddress = "&nbsp;";
   } 
  
   	if(request.getAttribute("modTree") != null)
   	{
   		mgrLoad = (String)request.getAttribute("modTree");
   	}	
   //已有权限
   String strHasPopedom = null;
   if(request.getAttribute("popedom") != null)
   {
   		strHasPopedom = (String)request.getAttribute("popedom");
   }
 	
  %>
<form method="post" action="">
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td width="2%" height="27"></td>
     <td width="98%" class="font_001F56_bold_12">当前位置：系统管理 -&gt; 用户组管理 -&gt; 用户权限分配</td>
   </tr>
 </table>
 
 <table width="90%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
     <tr>
     <td height="25" class="yx1"><div align="right">用户代码：</div></td>
     <td class="yx"><div align="left"><input name="userCode" type="text" size="20" readonly="true" class="input_readonly" value="<%=strUserCode%>"></div></td>
     <td class="yx1"><div align="right">用户名：</div></td>
     <td class="xx1"><div align="left"><input name="userName" type="text"  size="20" readonly="true" class="input_readonly" value="<%=strUserName%>">
     </div></td>
   </tr>
     <tr>
     <td height="25" class="yx1"><div align="right">手机号码：</div></td>
     <td class="yx"><div align="left"><input name="handset" type="text"   size="20" readonly="true" class="input_readonly" value="<%=strHandset%>"></div></td>
     <td class="yx1"><div align="right">联系电话：</div></td>
     <td class="xx1"><div align="left"><input name="tel" type="text"   size="20" readonly="true" class="input_readonly" value="<%=strTel%>"></div></td>
   </tr>
   
      <tr>
     <td height="25" class="yx1"><div align="right">用户组：</div></td>
     <td class="yx"><div align="left"><input name="createUser" type="text" size="20" readonly="true" class="input_readonly" value="<%=strUserGroup == null ? "" : strUserGroup%>"></div></td>
     <td class="yx1"><div align="right">Email：</div></td>
     <td class="xx1"><div align="left"><input name="Email" type="text"   size="20" readonly="true" class="input_readonly" value="<%=strEmail%>"></div></td>
   </tr>
      <tr>
     <td height="25" class="y2"><div align="right">联系地址：</div></td>
     <td class="x"><div align="left"><input name="address" type="text" size="20" readonly="true" class="input_readonly" value="<%=strAddress%>"></div></td>
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
 
  <table width="250" height="20"  border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
	  <td><div align="left" class="font_001F56_bold_12">可以对用户的下列权限进行分配: </div></td>
    </tr>
  </table>
  
  <table width="250" height="200"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
	<tr>
	  <td valign="top" class="TD_STYLE2">
	  	<div id="treeboxbox_tree" style="width:250; height:218;background-color:#EDF2F9; overflow:auto;"></div>
	  </td>
    </tr>
  </table>  

  <table width="60%" align="center"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="40"><div align="center">
	    <input type="button" name="button2" value="确定" onClick="saveAssignPopedom('<%=strUserId%>');" class="button_style">
            &nbsp;&nbsp;&nbsp; 
            <input type="button" name="Submit" value="关闭" onClick="closeW();" class="button_style">
	   
	   </div></td>
   </tr>
  </table>
  <script>
			tree=new dhtmlXTreeObject("treeboxbox_tree","100%","100%",0);
			tree.setImagePath("<%=request.getContextPath()%>/standard/images/");
			tree.enableCheckBoxes(1);
			tree.enableThreeStateCheckboxes(true);
			//tree.loadXMLString(loadStr);
			tree.loadXMLString("<%=mgrLoad%>");
			tree.setHasedChecked("<%=strHasPopedom%>")
	</script>
</body>
</html>