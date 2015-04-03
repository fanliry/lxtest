<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<title>增加设备信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript">
<!--
	function OnOk(){
		var deviceno = document.getElementById("deviceno").value;		//设备编号
		var devicename = document.getElementById("devicename").value;	//设备名称
		var devicetype = document.getElementById("devicetype").value;	//设备类型
		var warehouseid = document.getElementById("warehouseid").value;	//所属仓库
		var whAreaId = document.getElementById("whAreaId").value;		//所属库区
		var belongto = document.getElementById("belongto").value;		//所属巷道
		
		if(deviceno == null || deviceno.length <1)
		{
			alert("【设备编号】不能为空!");
			return;
		}
		
		if(devicename == null || devicename.length <1)
		{
			alert("【设备名称】不能为空!");
			return;
		}
		
		if(devicetype == null || devicetype.length <1)
		{
			alert("【设备类型】不能为空!");
			return;
		}
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("请选择【所属仓库】!");
			return;
		}
		
		if(whAreaId == null || whAreaId.length < 1){
			alert("请选择【所属库区】!");
			return;
		}

		var back_msg = "&deviceno=" + deviceno + "&devicename=" + devicename + "&devicetype=" + devicetype
			 + "&warehouseid=" + warehouseid + "&whAreaId=" + whAreaId + "&belongto=" + belongto;
		window.returnValue = back_msg;
    	window.close();
	}
	
	function OnLoad(){
		//同步
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");
		DWREngine.setAsync(true);
		
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		
		var typevalue = "sblx";		//设备类型的值	
		selectType("", "devicetype", typevalue);
	}
	
	//根据仓库获得库区的列表
	function getWhAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
	}
	
	//根据库区获得巷道的列表
	function getAlleyList(){
	
		var whAreaId = document.getElementById("whAreaId").value;
		selectAlleyList("", "belongto", whAreaId);
	}
-->
</script>
</head>

<body onload="OnLoad();">
  <form name="myForm" method="post" action="">
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">当前位置：基本信息 -&gt; 设备管理 -&gt; 新增设备信息</div></td>
    </tr>
  </table>
 
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right"><span class="red">*</span>设备编号：</td>
      <td class="xx1"><input type="text" name="deviceno" maxlength="16"></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>设备名称：</td>
      <td class="xx1"><input type="text" name="devicename" size="25" maxlength="25"></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>设备类型：</td>
      <td class="xx1"><select name="devicetype"><option value=""></option></select></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>所属仓库：</td>
      <td class="xx1"><select name="warehouseid" onChange="getWhAreaList();getAlleyList();"><option value=""></option></select></td>
    </tr>    
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>所属库区：</td>
      <td class="xx1"><select name="whAreaId" onChange="getAlleyList()"><option value=""></option></select></td>
    </tr>
    <tr>
      <td class="yx1" align="right">所属巷道：</td>
      <td class="xx1"><select name="belongto"><option value=""></option></select></td>
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