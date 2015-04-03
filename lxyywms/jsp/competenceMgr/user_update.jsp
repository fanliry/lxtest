<%@ page contentType="text/html; charset=GB2312"%>

<html>
<head>
<title>用户修改</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/standard/css/table.css" rel="stylesheet" type="text/css">
	<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/userGroupTree.js"></script>
	<SCRIPT type = "text/javascript"  language=JavaScript src="<%=request.getContextPath()%>/standard/js/md5.js"></SCRIPT>
<script type="text/javascript">
<!--
	//保存
	function saveData(id)
	{
		  	
        	var password1 = document.getElementById("password").value;
        	var password2;
        	if(password1 !=undefined && password1.length>0)
        	{
        	    	//password2=password1.calcMD5();//加密保存
        	    	password2=password1;//加密保存
        	    	var flag = "N";
        	    	
        	    	if(document.getElementById("iflag").checked == true)
        	    	{
        	    		flag = "Y";
        	    	}
        	    	
        	    	var strParam = "BMSService?actionCode=userInfoMgr&method=ricoExecUpdate"
        	    					+ "&userId=" + id 
        							+ "&userCode=" + document.getElementById("userCode").value
        							+ "&userName=" + document.getElementById("userName").value 
        							+ "&password=" + password2 
        							+ "&tel=" + document.getElementById("tel").value 
          							+ "&email=" + document.getElementById("Email").value 
          							+ "&handset=" + document.getElementById("handset").value
          							+ "&address=" + document.getElementById("address").value
	          						+ "&flag="+flag
	          						+ "&userGroup="+document.getElementById("userGroup").value;
									     	   	
		        	window.returnValue=strParam;
		        	
		        	window.close();
		        	    	
        	}else
        	{
        	    	alert("密码不能为空!");
        	}  
	}
	function checkData()
	{
		var p1 = document.getElementById("password").value.trim();
		var p2 = document.getElementById("password2").value.trim();
		
		var userCode = document.getElementById("userCode").value.trim();
		var userName = document.getElementById("userName").value.trim();

		if(userCode == null || userCode.length <1)
		{
			alert("用户代码不能为空!");
			return false;
		}
		if(userName == null || userName.length <1)
		{
			alert("用户名不能为空!");
			return false;
		}
		
		if(p1 != null && p2 != null)
		{
			if(p1 != p2)
			{
				alert("两次输入密码不同!");
				return false;
			}
		}else 
		{	
			alert("密码不能为空!");
			return false;
		}
		return true;
	}
	  String.prototype.trim = function()
	{
		return this.replace(/^\s*|\s*$/g,"");
	}
	function getGroup(id)
	{
		userGroupTree.getUserGroupList(id, viewGroup);
	}
	function viewGroup(data)
	{
		
		var g =document.getElementById("userGroup");
		while(g.options.length > 0) 
		{
	       	g.remove(g.options.length-1);
	    }
	    var op0=new Option("--请选择--");        
	    g.options.add(op0);
	    op0.value="";
		for(var i = 0; i < data.length; i++)
		{
			//alert(data[i].strId + '  |  '+data[i].strName);
			var op1=new Option(data[i].strName);        
	    	g.options.add(op1);
	    	op1.value=data[i].strId;
	    	if(data[i].strFlag == "1")
			{
				op1.selected=true;
			}
	    	
		}
	}

-->
</script>
</head>
<%
  String strUserId  ="";
  String strUserCode ="";
  String strPassword ="";
  String strUserName = ""; 
  String strTel = "";
  String strEmail = "";
  String strHandset= "";
  String strAddress= "";
  String strUserGroup = "";
  String strUsableness = "";

  request.setCharacterEncoding("ISO-8859-1"); 
 
    if(request.getParameter("userId")!=null){
      	strUserId = (String)request.getParameter("userId");		     		
   }

   if(request.getParameter("userCode") != null){
      	strUserCode = (String)request.getParameter("userCode");
   }      		
     		
      		if(request.getParameter("password") != null){
      			strPassword = (String)request.getParameter("password");
      		}      		
 
         		
      		if(request.getParameter("userName") != null){
      			strUserName = (String)request.getParameter("userName");
      		}  
      		strUserName = new String(strUserName.getBytes("ISO-8859-1"),"gb2312");   	    		
  
      		if(request.getParameter("tel") != null){
      			strTel = (String)request.getParameter("tel");
      		}     
  		
      		
      		if(request.getParameter("email") != null){
      			strEmail = (String)request.getParameter("email");
      		}   		

   
      		if(request.getParameter("handset") != null){
      			strHandset = (String)request.getParameter("handset");
      		}      		

      		if(request.getParameter("address") != null){
      			strAddress = (String)request.getParameter("address");
      		}   
      		strAddress = new String(strAddress.getBytes("ISO-8859-1"),"gb2312");   		
 
   
   if(request.getParameter("userGroup") != null)
   {
      	strUserGroup = (String)request.getParameter("userGroup");
    } 
     if(request.getParameter("usableness") != null)
   {
      	strUsableness = (String)request.getParameter("usableness");
    } 
    if(strUsableness == null)
    {
    	strUsableness = "";
    }
  
 %>
<body onload="getGroup('<%=strUserGroup%>');">
<form method="post" action="">
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：用户管理 -&gt; 修改用户</div></td>
   </tr>
 </table>
 
 <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">

   <tr>
     <td class="yx1" width="20%"><div align="right">用户代码：</div></td>
     <td class="yx" width="30%"><div align="left"><input type="text" name="userCode" size="20" value="<%=strUserCode == null ? "" : strUserCode%>" maxlength="40"> &nbsp;<font color="red">*&nbsp;</font></div></td>
  	 <td class="yx1" width="20%"><div align="right">用户名：</div></td>
     <td class="xx1" width="30%"><div align="left"><input type="text" name="userName" size="20" value="<%=strUserName == null ? "" : strUserName%>" maxlength="40"> &nbsp;<font color="red">*&nbsp;</font></div></td>
   </tr>
   <tr>
     <td class="yx1"><div align="right">用户密码：</div></td>
     <td class="yx"><div align="left"><input type="password" name="password" size="21" value="" maxlength="40"> &nbsp;<font color="red">*&nbsp;</font></div></td>
  	 <td class="yx1"><div align="right">确认密码：</div></td>
     <td class="xx1"><div align="left"><input type="password" name="password2" size="21" value="" maxlength="40"> &nbsp;<font color="red">*&nbsp;</font></div></td>
   </tr>
   <tr>
     <td class="yx1"><div align="right">用户组：</div></td>
     <td class="yx"><div align="left">
     	<select name="userGroup">
              <option value="">    </option>
              
                  	 
      </select> 
     </div></td>
  	 <td class="yx1"><div align="right">可用：</div></td>
     <td class="xx1"><div align="left"> 
     <input name="iflag" id="iflag" type="checkbox" <%=strUsableness.equals("Y") ? "checked" : ""%>> </div></td>
   </tr>
   <tr>
     <td class="yx1"><div align="right">手机号码：</div></td>
     <td class="yx"><div align="left"><input type="text" name="handset" value="<%=strHandset == null ? "" : strHandset%>" size="20" maxlength="40"></div></td>
  	 <td class="yx1"><div align="right">联系电话：</div></td>
     <td class="xx1"><div align="left"><input type="text" name="tel" value="<%=strTel == null ? "" : strTel%>" size="20" maxlength="40"></div></td>
   </tr>
   <tr>
     <td class="yx1"><div align="right">Email：</div></td>
     <td class="yx"><div align="left"><input type="text" name="Email" size="20" value="<%=strEmail == null ? "" : strEmail%>" maxlength="40"> </div></td>
  	 <td class="yx1"><div align="right">联系地址：</div></td>
     <td class="xx1"><div align="left"><input type="text" name="address" size="20" value="<%=strAddress == null ? "" : strAddress%>" maxlength="40"> </div></td>
   </tr>
   <tr>
     <td height="27" colspan="4" class="add_td2">
       <div align="center">
         <input type="button" name="saveBtn" value="保存" class="button_style" onclick="if(checkData()!=false){saveData('<%=strUserId%>');}">
         <input type="reset" name="resetBtn" value="重置" class="button_style">&nbsp;
         <input type="button" onClick="window.close()" name="resetBtn" value="关闭" class="BUTTON_STYLE"></div>
     </td>
   </tr>
 </table>
 
 </form>
</body>
</html>