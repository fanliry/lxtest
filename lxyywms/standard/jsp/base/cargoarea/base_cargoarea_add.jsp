<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<title>���ӻ�����Ϣ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript">
<!--
	function OnOk(){
		var warehouseid = document.getElementById("warehouseid").value;
		var cargoAreaName = document.getElementById("cargoAreaName").value;
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("��ѡ�������ֿ⡿!");
			return;
		}
		if(cargoAreaName == null || cargoAreaName.length <1)
		{
			alert("���������ơ�����Ϊ��!");
			return;
		}

		var back_msg = "&warehouseid=" + warehouseid + "&cargoAreaName=" + cargoAreaName;
		window.returnValue = back_msg;
    	window.close();
	}
	
	function OnLoad(){
		selectObject("", "warehouseid", "1");
	}
-->
</script>
</head>

<body onload="OnLoad();">

  <form name="myForm" method="post" action="">
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�������Ϣ -&gt; �������� -&gt; ����������Ϣ</div></td>
    </tr>
  </table>
  
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
     <td width="100px" class="yx1" align="right"><span class="red">*</span>�������ƣ�</td>
     <td class="xx1"><div align="left"><input type="text" name="cargoAreaName" maxlength="25" size="25"></td>
   </tr> 
   <tr>
     <td class="yx1" align="right"><span class="red">*</span>�����ֿ⣺</td>
     <td class="xx1"><select name="warehouseid"><option value=""></option></select></td>
   </tr>
   <tr>
     <td height="27" colspan="2" align="center">
       <input type="button" onclick="OnOk()" name="add" value="&nbsp;&nbsp;&nbsp;����" class="button_add">&nbsp; 
       <input type="reset" name="resetDetailBtn" value="&nbsp;&nbsp;&nbsp;����" class="button_reset">&nbsp;
       <input type="button" onClick="window.close()" name="resetBtn" value="�ر�" class="BUTTON_STYLE1">
     </td>
   </tr>
 </table>
 </form>
 
</body>
</html>