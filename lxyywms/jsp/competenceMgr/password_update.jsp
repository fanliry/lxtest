<%@ page contentType="text/html; charset=GB2312"%>



<html>
<head>
<title>管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/standard/css/table.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/md5.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript">
<!--
	function onload()
	{
		var back_msg = "<%=request.getAttribute("backMsg")==null?"":(String)request.getAttribute("backMsg")%>"
		if(back_msg != ""){
			if(back_msg == "Y"){
				alert("修改密码成功！");
			}
			else{
				alert(back_msg);
			}
		}
	}

	function checkData()
	{
		var p1 = document.getElementById("password1").value;
		var p2 = document.getElementById("password2").value;
		
		var oldpassword = document.getElementById("oldpassword").value;

		if(oldpassword == null || oldpassword.length <1)
		{
			alert("旧密码不能为空!");
			return false;
		}
		if(p1 != null && p1.length >0 && p2 != null && p2.length>0)
		{
			if(p1 != p2)
			{
				alert("两次输入密码不同!");
				return false;
			}
		}
		else 
		{
			alert("新密码不能为空!");
			return false;
		}
		
	}
	
	function saveData()
	{
		var oldpassword = document.getElementById("oldpassword").value;
		//var oldpassword = oldpassword1.calcMD5();
		var password1 = document.getElementById("password1").value;
		//var password1 = password.calcMD5();
		
		window.location.href="<%=request.getContextPath()%>/BMSService?actionCode=UserLoginAction&method=ricoExecUpdatePassword&oldpassword="+oldpassword+
  									"&password1="+password1;
	}
-->
</script>
</head>

<body onload="onload();">
<table width="100%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr><td height="5">
   <div class="wz">
	  <div id="dqwz" class="f_l">当前位置：<span>系统管理 &gt;&gt; 更改口令</span></div>
	  </div>
</td>
   </tr>
  <tr>
    <td valign="top" colspan="3">
	
	
	<table width="100%" height="10"  border="0" cellpadding="0" cellspacing="0">
      <tr><td></td></tr>
    </table>
	
	
	 
	 <!-- ======== 修改密码 ======== -->
	 <form name="myform" method="post">
	   
		<table align="center" border="0" cellpadding="1" cellspacing="1" width="400" class="TABLE_ADD" >
		    <tr class="add_tr">
		    	<td height="30" class="xx1" colspan=2 ><div align="center">修改密码</div></td>
		    </tr>
		    <tr>
			    <td width="40%" class="yx"><div align="right">旧密码：</div></td>
			    <td width="60%" class="xx1">
				<input type="password" name="oldpassword" size="20" maxlength="20">&nbsp;<font color="red">*&nbsp;</font></td>
		    </tr>
			<tr>
			    <td class="yx"><div align="right">新密码：</div></td>
			    <td class="xx1">
				<input type="password" name="password1" size="20" maxlength="20">&nbsp;<font color="red">*&nbsp;</font></td>
			</tr>
			<tr>	
				<td class="yx" ><div align="right">确认新密码：</div></td>
			    <td class="xx1" >
				<input type="password" name="password2" size="20" maxlength="20">&nbsp;<font color="red">*&nbsp;</font></td>
		    </tr>
		    <tr>
			  <td class="xx1" align="center" colspan=2 >
				<div align="center">
		          <input type="button" name="saveBtn" value="修改" class="button_style" onclick="if(checkData()!=false){saveData();}">&nbsp;&nbsp;&nbsp;
		          <input type="reset" name="resetBtn" value="重置" class="button_style">
        </div>
			  </td>
			</tr>
		    <tr>
			  <td  align="center" colspan=2 ><font color="red">注:*为必填内容</font></td>
			</tr>
			
	    </table>													
	    
	 </form>
	 <!-- ======== 修改密码 ======== -->
	 
	
	</td>
    <td width="9" background="<%=request.getContextPath()%>/standard/images/wds_main_03.gif"></td>
  </tr>
</table>
</body>
</html>
