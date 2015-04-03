<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<title>增加库区信息</title>
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

	function OnOk(){
		var warehouseid = document.getElementById("warehouseid").value;
		var whAreaName = document.getElementById("whAreaName").value;
		var ERPCode = document.getElementById("ERPCode").value;
		var ERPAccount = document.getElementById("ERPAccount").value;
		
		
		if(whAreaName == null || whAreaName.length <1)
		{
			alert("【库区名称】不能为空!");
			return;
		}
		if(warehouseid == null || warehouseid.length < 1){
			alert("请选择【所属仓库】!");
			return;
		}
		if(ERPAccount == null || ERPAccount.length < 1){
			alert("请选择【ERP账套】!");
			return;
		}
		if(ERPCode == null || ERPCode.length < 1){
			alert("请选择【ERP代码】!");
			return;
		}
		var back_msg = "&warehouseid=" + warehouseid + "&whAreaName=" + whAreaName + "&ERPCode=" + ERPCode + "&ERPAccount=" + ERPAccount;
		window.returnValue = back_msg;
    	window.close();
	}
	
	function OnLoad(){
		selectObject("", "warehouseid", "1");
	}

</script>
</head>

<body onload="OnLoad();">

  <form name="myForm" method="post" action="">
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">当前位置：基本信息 -&gt; 库区管理 -&gt; 新增库区信息</div></td>
    </tr>
  </table>
  
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
     <td width="150px" class="yx1" align="right"><span class="red">*</span>库区名称：</td>
     <td class="xx1"><input type="text" name="whAreaName" maxlength="25" size="25"></td>
   </tr>
   <tr>
     <td width="150px" class="yx1" align="right"><span class="red">*</span>ERP账套：</td>
     <td class="xx1"><input type="text" name="ERPAccount" maxlength="25" size="25"></td>
   </tr> 
   <tr>
     <td width="150px" class="yx1" align="right"><span class="red">*</span>ERP代码：</td>
     <td class="xx1"><input type="text" name="ERPCode" maxlength="25" size="25"></td>
   </tr> 
   <tr>
     <td class="yx1" align="right"><span class="red">*</span>所属仓库：</td>
     <td class="xx1"><select name="warehouseid"><option value=""></option></select></td>
   </tr>
   <tr>
     <td height="27" colspan="2" align="center">
       <input type="button" onclick="OnOk()" name="add" value="&nbsp;&nbsp;&nbsp;保存" class="button_add">&nbsp; 
       <input type="reset" name="resetDetailBtn" value="&nbsp;&nbsp;&nbsp;重置" class="button_reset">&nbsp;
       <input type="button" onClick="window.close()" name="resetBtn" value="关闭" class="BUTTON_STYLE1">
     </td>
   </tr>
 </table>
 </form>
 
</body>
</html>