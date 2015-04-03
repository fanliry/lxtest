<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseCargoarea" %>
<%
	BaseCargoarea cargoArea = (BaseCargoarea)request.getAttribute("cargoArea");  
	String cargoAreaId = cargoArea.getCargoAreaId();		//货区ID
	String cargoAreaName = cargoArea.getCargoAreaName();	//货区名
	String warehousename = cargoArea.getWarehousename();	//仓库名
%>
<html>
<head>
<title>修改货区信息</title>
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
	//保存修改货区信息
	function OnOk(){
		var cargoAreaId   = document.getElementById("cargoAreaId").value;
		var cargoAreaName = document.getElementById("cargoAreaName").value;
		
		if(cargoAreaName == null || cargoAreaName.length <1)
		{
			alert("【货区名称】不能为空!");
			return;
		}

		var back_msg = "&cargoAreaId=" + cargoAreaId + "&cargoAreaName=" + cargoAreaName;
		
    	window.returnValue = back_msg;
    	window.close();
	}
	

-->
</script>
</head>

<body>

  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">当前位置：基本信息 -&gt; 货区管理 -&gt; 修改货区信息</div></td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right"><span class="red">*</span>货区名称：</td>
      <td class="xx1">
     	<input type="hidden" id="cargoAreaId" value="<%=cargoAreaId%>">
     	<input type="text" id="cargoAreaName" maxlength="25" size="25" value="<%=cargoAreaName%>">
      </td>
    </tr> 
    <tr>
      <td class="yx1" align="right">所属仓库：</td>
      <td class="xx1"><input type="text" id="warehousename" value="<%=warehousename%>" class="input_readonly" readonly></td>
    </tr>
   
    <tr>
      <td align="center" colspan="2">
        <input type="button" onclick="OnOk()" name="add" value="&nbsp;&nbsp;&nbsp;保存" class="button_add">&nbsp; 
        <input type="button" onClick="window.close()" name="resetBtn" value="关闭" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
 
</body>
</html>