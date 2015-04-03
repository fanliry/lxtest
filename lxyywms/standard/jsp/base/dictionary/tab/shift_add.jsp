<%@ page contentType="text/html; charset=GB2312"%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/table.css" rel="stylesheet" type="text/css">
<script>
<!--
	//获取用户名
	function GetUser()
	{
		var strUrl='<%=request.getContextPath()%>/jsp/dictionary/tab/chooseUser/popup.jsp';
		var result = showModalDialog(strUrl,'','dialogWidth=700px;dialogHeight=400px');
		
	   	if(result != null && result.length > 0)
	   	{
	   		var tempParam = result;
	 		var temStr = tempParam.split("|");
			//0 用户表id 1用户编码2用户名4部门id5是否可用
	 		document.getElementById("op_man_id").value = temStr[0];
	 		document.getElementById("jobman").value = temStr[2];
		}
	}
	//增加  
	function Save()
	{
		var op_man_id = document.getElementById("op_man_id").value;
		var in_out_type = document.getElementById("in_out_type").value;
			
		if(op_man_id == "" || op_man_id.length < 1)
		{
			alert("【负责人】不能为空！");
			return;
		}
		if(in_out_type == "" || in_out_type.length < 1)
		{
			alert("【出入类型】不能为空！");
			return;
		}
		var backmsg = "&op_man_id=" + op_man_id + "&in_out_type=" + in_out_type;
		
		window.returnValue = backmsg;
		window.close();
	}

-->
</script>
</head>

<body>

 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25" class="font_006699_bold_12">当前操作：添加班次</td>
   </tr>
 </table>
 
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_MGR_TOP">
   <tr>
     <td class="TD_MGR_TOP" align="right">负责人： </td>
     <td class="TD_MGR_TOP">
       <input name="op_man_id" type="hidden"><input name="jobman" type="text" size="16" readonly>
       <input name="moreBtn" type="button" class="button_select" value="…" onClick="GetUser()">
     </td>
   </tr>
   <tr>
     <td class="TD_MGR_TOP" align="right">入出类型： </td>
     <td class="TD_MGR_TOP"><select name="in_out_type"><option value="">--请选择--</option><option value="1">入库</option><option value="2">出库</option></select></td>
   </tr>
   <tr>
     <td height="27" colspan="3" class="TD_MGR_TOP" align="center">
       <input onclick="Save()"type="button" name="button1" value="保存" class="BUTTON_STYLE1">
       <input onclick="window.close()" type="button" name="button1" value="取消" class="BUTTON_STYLE1">
     </td>
   </tr>
 </table>
 
</body>
</html>

