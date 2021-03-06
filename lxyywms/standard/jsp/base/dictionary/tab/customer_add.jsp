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
<script>
<!--
	function OnOk(){
		var short_name = document.getElementById("short_name").value;
		var full_name = document.getElementById("full_name").value;
		var link_man = document.getElementById("link_man").value;
		var telphone = document.getElementById("telphone").value;
		var fax = document.getElementById("fax").value;
		var address = document.getElementById("address").value;
		var out_id = document.getElementById("out_id").value;
		var is_use = document.getElementById("is_use").checked?"Y":"N";

		if(short_name == null || short_name.length < 1){
			alert("【客户简称】不能为空！");
			return;
	  	}
		var back_msg = "&short_name=" + short_name + "&full_name=" + full_name + "&link_man=" + link_man + "&telphone=" + telphone 
			+ "&fax=" + fax + "&address=" + address + "&out_id=" + out_id + "&is_use=" + is_use;

		window.returnValue = back_msg;
		window.close()
	}
-->
</script>
</head>

<body>

 <table width="98%" height="25" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td width="98%" class="font_006699_bold_12">当前位置：基本信息 -&gt; 字典中心 -&gt; 客户档案 -&gt; 添加客户</td>
   </tr>
 </table>
 
<table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_ADD">
   <tr>
     <td class="TD_ADD" align="right">客户简称：</td>
     <td class="TD_ADD"><input type="text" name="short_name"> <span style="color: red;">*</span></td>
     <td class="TD_ADD" align="right">客户全称：</td>
     <td class="TD_ADD"><input type="text" name="full_name"></td>
   </tr>
   <tr>
     <td class="TD_ADD" align="right">联系人：</td>
     <td class="TD_ADD"><input type="text" name="link_man"></td>
     <td class="TD_ADD" align="right">联系电话：</td>
     <td class="TD_ADD"><input type="text" name="telphone"></td>
   </tr>
   <tr>
     <td class="TD_ADD" align="right">传真：</td>
     <td class="TD_ADD"><input type="text" name="fax"> </td>
     <td class="TD_ADD" align="right">地址：</td>
     <td class="TD_ADD"><input type="text" name="address"></td>
   </tr>
   <tr>
     <td class="TD_ADD" align="right">分销编码：</td>
     <td class="TD_ADD"><input type="text" name="out_id"></td>
     <td class="TD_ADD" align="right">是否启用：</td>
     <td class="TD_ADD"><input type="checkbox" name="is_use" class="input_checkbox" checked></td>
   </tr>
   <tr>
     <td class="TD_ADD" align="center" colspan="4">
       <input type="button" value="保存" class="BUTTON_STYLE1" onClick="OnOk()">
       <input type="button" value="取消" class="BUTTON_STYLE1" onClick="window.close()">
     </td>
   </tr>
 </table>

</body>
</html>
