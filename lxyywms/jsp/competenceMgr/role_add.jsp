<%@ page contentType="text/html; charset=GB2312"%>

<html>
<head>
<title>角色增加</title>
<base  target="_self">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/standard/css/table.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
<!--
	//保存
	function saveData()
	{
		var strParam = "BMSService?actionCode=roleInfoMgr&method=ricoExecAdd" 
        					+ "&roleName="+document.getElementById("roleName").value
							+ "&descr=" + document.getElementById("descr").value;   
		  											  
        window.returnValue=strParam;
		window.close();
	}
	function checkData()
	{
		var roleName = document.getElementById("roleName").value;
		if(roleName == "" || roleName.length <1)  
       	{
       		alert("【角色名】不能为空!");
       		return false; 
       	}
		return true;
	}
//
-->
</script>
</head>

<body>
<form method="post"  action="">
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：角色管理 -&gt; 增加角色</div></td>
   </tr>
 </table>
 
 <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">

   <tr>
     <td class="yx1" width="100"><div align="right">角色名：</div></td>
     <td class="xx1"><div align="left"><input type="text" name="roleName" size="30" maxlength="40"> &nbsp;<font color="red">*&nbsp;</font></div></td>
   </tr>
   <tr>
     <td class="yx1" width="100"><div align="right">描述：</div></td>
     <td class="xx1"><div align="left"><textarea cols="62" rows="3" name="descr"></textarea></div></td>
   </tr>
   <tr>
     <td height="27" colspan="2" class="add_td2">
       <div align="center">
         <input type="button" name="saveBtn" value="保存" class="button_style" onclick="if(checkData()!=false){saveData();}">
         <input type="reset" name="resetBtn" value="重置" class="button_style">&nbsp;
         <input type="button" onClick="window.close()" name="resetBtn" value="关闭" class="BUTTON_STYLE">
         </div>
     </td>
   </tr>
 </table>
 
 </form>
</body>
</html>