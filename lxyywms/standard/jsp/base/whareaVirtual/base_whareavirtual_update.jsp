<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseWhareaVirtual" %>
<%
	BaseWhareaVirtual wharea = (BaseWhareaVirtual)request.getAttribute("whArea");  
	String whAreaId = wharea.getwhAreaId();				//库区ID
	String whAreaName = wharea.getwhAreaName();			//库区名
	String warehousename = wharea.getWarehousename();	//仓库名
	String ERPCode = wharea.getERPCode();				//ERP代码
	String ERPAccount = wharea.getERPAccount();
%>
<html>
<head>
<title>修改库区信息</title>
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

	//保存修改库区信息
	function OnOk(){
		var whAreaId   = document.getElementById("whAreaId").value;
		var whAreaName = document.getElementById("whAreaName").value;
		var ERPCode = document.getElementById("ERPCode").value;
		var ERPAccount = document.getElementById("ERPAccount").value;
		
		if(whAreaName == null || whAreaName.length <1)
		{
			alert("【库区名称】不能为空!");
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
		var back_msg = "&whAreaId=" + whAreaId + "&whAreaName=" + whAreaName + "&ERPCode=" + ERPCode + "&ERPAccount=" + ERPAccount;
		
    	window.returnValue = back_msg;
    	window.close();
	}
	
	function OnLoad(){
	
	}

</script>
</head>

<body onload="OnLoad();">

  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">当前位置：基本信息 -&gt; 库区管理 -&gt; 修改库区信息</div></td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="150px" class="yx1" align="right"><span class="red">*</span>库区名称：</td>
      <td class="xx1">
     	<input type="hidden" id="whAreaId" value="<%=whAreaId%>">
     	<input type="text" id="whAreaName" maxlength="25" size="25" value="<%=whAreaName%>"><font color="red">*</font>
      </td>
    </tr> 
   <tr>
     <td width="150px" class="yx1" align="right"><span class="red">*</span>ERP账套：</td>
     <td class="xx1"><input type="text" name="ERPAccount" maxlength="25" size="25" value="<%=ERPAccount%>" ></td>
   </tr> 
   <tr>
     <td width="150px" class="yx1" align="right"><span class="red">*</span>ERP代码：</td>
     <td class="xx1"><input type="text" name="ERPCode" maxlength="25" size="25"  value="<%=ERPCode%>" ></td>
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