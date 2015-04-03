<%@ page contentType="text/html; charset=GB2312"%>

<html>
<head>
<title>更新用户组</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/standard/css/table.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
<!--
	//保存
	function saveData(id)
	{
		var strParam = "BMSService?actionCode=userGroupMgr&method=ricoExecUpdate" 
							+ "&id="+id
        					+ "&groupName="+document.getElementById("groupName").value
							+ "&groupText=" + document.getElementById("groupText").value;       
        window.returnValue=strParam;
		window.close();
	}
	function checkData()
	{
       	var roleName = document.getElementById("groupName").value;
		
		if(roleName == null || roleName.length <1)
		{
			alert("【组名】不能为空!");
			return false;
		}
		if(roleName.length >100)
		{
			alert("【组名】太长!");
			return false;
		}
		return true;
	}

-->
</script>
</head>
<%
  String strId = "";
  String strName = "";
  String strText = "";
  request.setCharacterEncoding("ISO-8859-1"); 

   if(request.getParameter("id")!=null)
   {    		
      	strId = (String)request.getParameter("id");
    		
   }  
   if(request.getParameter("name")!=null)
   {
      strName = (String)request.getParameter("name");
      strName = new String(strName.getBytes("ISO-8859-1"),"gb2312");   	 		
   }
    if(request.getParameter("text")!=null)
    {
      	strText = (String)request.getParameter("text");
      	strText = new String(strText.getBytes("ISO-8859-1"),"gb2312");       		
   }

   
   %>   
<body>
<form method="post" action="">
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：用户组管理 -&gt; 更新用户组</div></td>
   </tr>
 </table>
 
 <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">

   <tr>
     <td class="yx1" width="30%"><div align="right">组名：</div></td>
     <td class="xx1" width="70%"><div align="left"><input type="text" name="groupName" size="40" value="<%=strName == null ? "" : strName%>" maxlength="40"> &nbsp;<font color="red">*&nbsp;</font></div></td>
   </tr>
   <tr>
     <td class="yx1"><div align="right">组说明：</div></td>
     <td class="xx1"><div align="left"><textarea cols="62" rows="3" name="groupText"><%=strText == null ? "" : strText%></textarea></div></td>
   </tr>
   <tr>
     <td height="27" colspan="2" class="add_td2">
       <div align="center">
         <input type="button" name="saveBtn" value="保存" class="button_style" onclick="if(checkData()!=false){saveData('<%=strId%>');}">
         <input type="reset" name="resetBtn" value="重置" class="button_style">&nbsp;
         <input type="button" onClick="window.close()" name="resetBtn" value="关闭" class="BUTTON_STYLE">
         </div>
     </td>
   </tr>
 </table>
 
 </form>
</body>
</html>