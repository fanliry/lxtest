<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseAlley" %>
<%
	BaseAlley alley = (BaseAlley)request.getAttribute("alley");  
	String cargoAlleyId = alley.getCargoAlleyId();			//巷道ID
	String cargoAlleyName = alley.getCargoAlleyName();		//巷道名
	String whAreaName = alley.getWhAreaName();				//库区名
	String warehousename = alley.getWarehousename();		//仓库名
	String inlock = alley.getInlock();						//入库锁
	String outlock = alley.getOutlock();					//出库锁
	String istwin = alley.getIstwin().equals("Y")?"是":"否";	//是否双升货位巷道
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
		var cargoAlleyId = document.getElementById("cargoAlleyId").value;		//巷道ID
		var cargoAlleyName = document.getElementById("cargoAlleyName").value;	//巷道名
		var inlock = document.getElementById("inlock").checked==true?"Y":"N";	//入库锁
		var outlock = document.getElementById("outlock").checked==true?"Y":"N";	//出库锁
		var istwin = document.getElementById("istwin").checked==true?"Y":"N";	//是否双升货位巷道
		
		if(cargoAlleyName == null || cargoAlleyName.length <1)
		{
			alert("【巷道名称】不能为空!");
			return;
		}

		var back_msg = "&cargoAlleyId=" + cargoAlleyId + "&cargoAlleyName=" + cargoAlleyName + "&inlock=" + inlock + "&outlock=" + outlock + "&istwin=" + istwin;
		
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
      <td width="120px" class="yx1" align="right">所属仓库：</td>
      <td class="xx1"><input type="text" name="warehousename" value="<%=warehousename%>" class="input_readonly" readonly></td>
    </tr>
    <tr>
      <td class="yx1" align="right">所属库区：</td>
      <td class="xx1"><input type="text" name="whAreaName" value="<%=whAreaName%>" class="input_readonly" readonly></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>巷道名称：</td>
      <td class="xx1">
        <input type="hidden" name="cargoAlleyId" value="<%=cargoAlleyId%>">
        <input type="text" name="cargoAlleyName" value="<%=cargoAlleyName%>">
      </td>
    </tr>
    <tr>
      <td class="yx1" align="right">入库锁：</td>
      <td class="xx1"><input type="checkbox" name="inlock" class="input_checkbox" <%if(inlock.equals("Y")){%>checked<%}%>></td>
    </tr>
    <tr>
      <td class="yx1" align="right">出库锁：</td>
      <td class="xx1"><input type="checkbox" name="outlock" class="input_checkbox" <%if(outlock.equals("Y")){%>checked<%}%>></td>
    </tr>
    <tr>
      <td class="yx1" align="right">是否双升货位巷道：</td>
      <td class="xx1"><input type="text" name="istwin" class="input_readonly" readonly value="<%=istwin%>"></td>
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