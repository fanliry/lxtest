<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<title>增加巷道信息</title>
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
		var whAreaId = document.getElementById("whAreaId").value;
		var cargoAlleyName = document.getElementById("cargoAlleyName").value;
		var istwin = document.getElementById("istwin").checked==true?"Y":"N";
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("请选择【所属仓库】!");
			return;
		}
		if(whAreaId == null || whAreaId.length < 1){
			alert("请选择【所属库区】!");
			return;
		}
		if(cargoAlleyName == null || cargoAlleyName.length <1)
		{
			alert("【巷道名称】不能为空!");
			return;
		}
		selectView.isWhAreaTypeRight(whAreaId, {callback:function(data){
			if(data){
				var back_msg = "&warehouseid=" + warehouseid + "&whAreaId=" + whAreaId + "&cargoAlleyName=" + cargoAlleyName + "&istwin=" + istwin;
				window.returnValue = back_msg;
		    	window.close();
	    	}else{
	    		alert("所选库区不是立体库区的类型！\n请重新选择【所属库区】!");
	    	}
		}});
	}
	
	function OnLoad(){
		//同步
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");
		DWREngine.setAsync(true);
		
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
	}
	
	//根据仓库获得库区的列表
	function getWhAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
	}
-->
</script>
</head>

<body onload="OnLoad();">

  <form name="myForm" method="post" action="">
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">当前位置：基本信息 -&gt; 巷道管理 -&gt; 新增巷道信息</div></td>
    </tr>
  </table>
  
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
     <td width="120px" class="yx1" align="right"><span class="red">*</span>所属仓库：</td>
     <td class="xx1"><select name="warehouseid" onChange="getWhAreaList()"><option value=""></option></select></td>
   </tr>    
   <tr>
     <td class="yx1" align="right"><span class="red">*</span>所属库区：</td>
     <td class="xx1"><select name="whAreaId"><option value=""></option></select></td>
   </tr> 
   <tr>
     <td class="yx1" align="right"><span class="red">*</span>巷道名称：</td>
     <td class="xx1"><input type="text" name="cargoAlleyName"></td>
   </tr>
   <tr>
      <td class="yx1" align="right">是否双升货位巷道：</td>
      <td class="xx1"><input type="checkbox" name="istwin" class="input_checkbox"></td>
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