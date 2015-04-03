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
<!--
	function OnOk(){
		var warehouseid = document.getElementById("warehouseid").value;
		var whAreaName = document.getElementById("whAreaName").value;
		var whAreaType = document.getElementById("whAreaType").value;
		var environment = document.getElementById("environment").value;
		var istask = document.getElementById("istask").checked==true?"Y":"N";
		
		if(whAreaName == null || whAreaName.length <1)
		{
			alert("【库区名称】不能为空!");
			return;
		}
		if(warehouseid == null || warehouseid.length < 1){
			alert("请选择【所属仓库】!");
			return;
		}
		if(whAreaType == null || whAreaType.length <1)
		{
			alert("【库区类型】不能为空!");
			return;
		}
		if(environment == null || environment.length <1)
		{
			alert("【存储环境】不能为空!");
			return;
		}

		var back_msg = "&warehouseid=" + warehouseid + "&whAreaName=" + whAreaName + "&whAreaType=" + whAreaType + "&environment=" + environment  + "&istask=" + istask;
		window.returnValue = back_msg;
    	window.close();
	}
	
	function OnLoad(){
		
		var typevalue = "kqlx";		//库区类型的值	
		selectType("", "whAreaType", typevalue);
		selectObject("", "warehouseid", "1");
		
		selectType("", "environment", "cchj");//存储环境
	}
-->
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
     <td class="yx1" align="right"><span class="red">*</span>所属仓库：</td>
     <td class="xx1"><select name="warehouseid"><option value=""></option></select></td>
   </tr>
   <tr>
     <td class="yx1" align="right"><span class="red">*</span>库区类型：</td>
     <td class="xx1"><select name="whAreaType"><option value=""></option></select></td>
   </tr>
   <tr>
     <td class="yx1" align="right"><span class="red">*</span>存储环境：</td>
     <td class="xx1"><select name="environment"><option value=""></option></select></td>
   </tr>
   <tr>
     <td class="yx1" align="right">是否生成调度任务：</td>
     <td class="xx1"><input type="checkbox" name="istask" class="input_checkbox"></td>
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