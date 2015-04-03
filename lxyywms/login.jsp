<%@ page language="java" pageEncoding="GBK"%>
<%@page import="com.ricosoft.common.tools.StrTools"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<title>浙江刚玉自动化立体仓储管理系统</title>
<link href="<%=request.getContextPath()%>/standard/style/basic.css" rel="stylesheet" type="text/css" />
<script language='JavaScript' charset='gb2312'> 
function openPostWindow(url,name, username, password) 
	{   
	var tempForm = document.createElement("form"); 
	tempForm.id="tempForm1"; 
	tempForm.method="post"; 
	tempForm.action=url; 
	tempForm.target=name; 
	var hideInput0 = document.createElement("input"); 
	hideInput0.type="hidden";
	hideInput0.name= "actionCode";
	hideInput0.value= "UserLoginAction"; 
	tempForm.appendChild(hideInput0); 
	
	var hideInput1 = document.createElement("input"); 
	hideInput1.type="hidden";
	hideInput1.name= "username";
	hideInput1.value= username; 
	tempForm.appendChild(hideInput1); 
	var hideInput2 = document.createElement("input"); 
	hideInput2.type="hidden"; 
	hideInput2.name= "password";
	hideInput2.value= password; 
	tempForm.appendChild(hideInput2); 
	tempForm.attachEvent("onsubmit",function(){ openWindow(name); }); 
	document.body.appendChild(tempForm); 
	tempForm.fireEvent("onsubmit"); 
	tempForm.submit(); 
	document.body.removeChild(tempForm); 
	} 
	function openWindow(name) 
	{ 
		window.open('about:blank',name,'fullscreen=yes, toolbar=no, menubar=no, scrollbars=no, resizable=yes, location=no, status=no'); 
		window.opener = null;
		window.open('','_self');
		window.close();
	} 
	function userLogin()
	{
		var password2 = document.getElementById("password").value;
	    //var password2 = password1.calcMD5();
		//document.getElementById("password").value =password2;
		
		var username = document.getElementById("username").value;
		//var warehouse_id = document.getElementById("warehouse_id").value;
		if(username == null || username.length < 1){
			alert("【用户】不能为空！");
			return false;
		}
		if(password2 == null || password2.length < 1){
			alert("【密码】不能为空！");
			return false;
		}
		//if(warehouse_id == null || warehouse_id.length < 1){
		//	alert("【仓库】不能为空！");
		//	return;
		//}
		
		var name1 = "newwindow"+(new Date().getTime().toString(36));
		//var strUrl = "<%=request.getContextPath()%>/BMSService";
		var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=UserLoginAction&username=" + username + "&password=" + password2;
		
		/*window.open(strUrl, "newwindow"+(new Date().getTime().toString(36)), "fullscreen=yes, toolbar=no, menubar=no, scrollbars=no, resizable=yes, location=no, status=no");
		window.opener = null;
		window.open('','_self');
		window.close();*/
		
		window.location.href=strUrl;
		//openPostWindow(strUrl,name1, username, password2);
		
		
		//改为POST提交
		//	var subUrl = "<%=request.getContextPath()%>/BMSService";
		//	document.write("<form action='"+subUrl+"' method='post' name='formx1' style='display:none'>"); 
		//	document.write("<input type=hidden name='actionCode' value='UserLoginAction'>"); 
		//	document.write("<input type=hidden name='username'   value='"+username+"'>");
		//	document.write("<input type=hidden name='password'   value='"+password2+"'>");
			//document.write("<input type=hidden name='warehouseid'   value='"+warehouse_id+"'>");
		//	document.write("</form>"); 
		//	document.formx1.submit(); 
	}
	function  login_onkeypress()   
	{   
  		if(event.keyCode==13)
  		{   
  			userLogin();   
  		}   
  }

	//表单重置
	function resetBtn()
	{
		document.getElementById("password").value="";
	    document.getElementById("username").value="";
　　 }
//页面登录
	function OnLoad()
	{
		//parent.RemoveLoading();	
		var back_msg = "<%=request.getParameter("back_msg")==null?"":new String(((String)request.getParameter("back_msg")).getBytes("ISO-8859-1"), "UTF-8")%>";
		if(back_msg != "")
		{
			if(back_msg == "Y"){
			
			}else{
				alert(back_msg);
			}
		}
	}
</script>
</head>
<body class="login_body"  onload="OnLoad();">
<div class="login_bg">

<div class="input_name">
<div style="margin-top:2px;" > <input id="username" type="text"  class="input_st" value="root"/></div>
<div style="margin-top:10px;"> <input id="password" type="password"  class="input_st" value="123456" onkeypress="login_onkeypress();"/></div>
</div>
<div style="width:146px;margin-top:10px;padding:15px 0 0 287px;"> 
<input name="登录" type="image" value="登录" src="<%=request.getContextPath()%>/standard/images/btn_login.jpg" onclick="userLogin();" /> 
<input name="取消" type="image" value="取消" src="<%=request.getContextPath()%>/standard/images/btn_quxiao.jpg" onclick="resetBtn();"  /></div>

<div style="text-align:center; margin-top:140px;">
<p>浙江刚玉物流科技有限公司  版权所有  Copyright (c) All Rights Reserved  </p>
<p> 联系电话：0571-88223936 </p>
<p> 地址：杭州市西湖区东部软件园2号楼2409室 </p>
</div>
</div>
</body>
</html>