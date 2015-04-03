<%@ page contentType="text/html; charset=GB2312"%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
<!--
	//保存
	function Save()
	{
		var out_type = document.getElementById("out_type").value;;
		var ids = "";
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++)
		{
			if(check_ids[i].checked)
			{
				ids += check_ids[i].value + "|";
			}
		}
		var backmsg = "&out_type=" + out_type + "&ids=" + ids;
		
		window.returnValue = backmsg;
		window.close();
	}
	//页面登陆
	function OnLoad()
	{
		var getmsg = window.dialogArguments;
		var aem = getmsg.split("|");
		
		document.getElementById("out_type").value = aem[0];
		document.getElementById("out_type_name").value = aem[1];
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
     
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td class="font_006699_bold_12" height="25">当前位置：基本信息 -&gt; 出库设置 -&gt; 修改可用物品状态</td>
   </tr>
 </table>

 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_MGR_TOP">
   <tr>
     <td class="TD_MGR_TOP" colspan="3">
        当前设置：<input type="hidden" name="out_type">
       <input type="text" name="out_type_name" class="text" size="20" style="background-color: #EFEFFD" readonly>
     </td>
   </tr>
   <tr>
     <td class="TD_MGR_TOP"><input type="checkbox" name="check_id" value="1" class="input_checkbox">待检</td>
     <td class="TD_MGR_TOP"><input type="checkbox" name="check_id" value="2" class="input_checkbox">合格</td>
     <td class="TD_MGR_TOP"><input type="checkbox" name="check_id" value="3" class="input_checkbox">不合格</td>
   </tr>
   <tr>
    <td class="TD_MGR_TOP" align="center" colspan="6">
      <input onClick="Save()" type="button" name="save" value="保存" class="button">
      <input onClick="window.close()" type="button" name="report" value="关闭" class="button">
    </td>
   </tr>
 </table>
   
</body>
</html>
     