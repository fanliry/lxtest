<%@ page contentType="text/html; charset=GB2312"%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
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
			alert("���ͻ���ơ�����Ϊ�գ�");
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
     <td width="98%" class="font_006699_bold_12">��ǰλ�ã�������Ϣ -&gt; �ֵ����� -&gt; �ͻ����� -&gt; ���ӿͻ�</td>
   </tr>
 </table>
 
<table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_ADD">
   <tr>
     <td class="TD_ADD" align="right">�ͻ���ƣ�</td>
     <td class="TD_ADD"><input type="text" name="short_name"> <span style="color: red;">*</span></td>
     <td class="TD_ADD" align="right">�ͻ�ȫ�ƣ�</td>
     <td class="TD_ADD"><input type="text" name="full_name"></td>
   </tr>
   <tr>
     <td class="TD_ADD" align="right">��ϵ�ˣ�</td>
     <td class="TD_ADD"><input type="text" name="link_man"></td>
     <td class="TD_ADD" align="right">��ϵ�绰��</td>
     <td class="TD_ADD"><input type="text" name="telphone"></td>
   </tr>
   <tr>
     <td class="TD_ADD" align="right">���棺</td>
     <td class="TD_ADD"><input type="text" name="fax"> </td>
     <td class="TD_ADD" align="right">��ַ��</td>
     <td class="TD_ADD"><input type="text" name="address"></td>
   </tr>
   <tr>
     <td class="TD_ADD" align="right">�������룺</td>
     <td class="TD_ADD"><input type="text" name="out_id"></td>
     <td class="TD_ADD" align="right">�Ƿ����ã�</td>
     <td class="TD_ADD"><input type="checkbox" name="is_use" class="input_checkbox" checked></td>
   </tr>
   <tr>
     <td class="TD_ADD" align="center" colspan="4">
       <input type="button" value="����" class="BUTTON_STYLE1" onClick="OnOk()">
       <input type="button" value="ȡ��" class="BUTTON_STYLE1" onClick="window.close()">
     </td>
   </tr>
 </table>

</body>
</html>