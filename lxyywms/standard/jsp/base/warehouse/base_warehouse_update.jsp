<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseWarehouse" %>
<%
	BaseWarehouse wh = (BaseWarehouse)request.getAttribute("Warehouse"); 
	String warehouseid = wh.getWarehouseid();		// 仓库ID
    String warehousename = wh.getWarehousename();	// 仓库名
    String whaddress = wh.getWhaddress();			// 仓库地址
    String whmgrman = wh.getWhmgrman();				// 仓库管理员
    String whlinkman = wh.getWhlinkman();			// 联系人
    String whlinktel = wh.getWhlinktel();			// 联系电话
    String iscurrent = wh.getIscurrent();			// 是否当前仓库
    String erpcode = wh.getErpcode();				// 对应ERP的代码
%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript">
<!--
	function OnOk(){
	
    	var warehouseid = document.getElementById("warehouseid").value;
    	var warehousename = document.getElementById("warehousename").value;
		var whaddress = document.getElementById("whaddress").value;
		var whmgrman = document.getElementById("whmgrman").value;
		var whlinkman = document.getElementById("whlinkman").value;
		var whlinktel = document.getElementById("whlinktel").value;
		var iscurrent = document.getElementById("iscurrent").checked?"Y":"N";
		var erpcode = document.getElementById("erpcode").value;
				 
		if(warehousename == null || warehousename.length < 1){
			alert("【仓库名称】不能为空！");
			return;
		}else{
			if(strlen(warehousename) > 50){
				alert("【仓库名称】过长!");
				return;
			}
		}
		
		var back_msg = "&warehouseid=" + warehouseid + "&warehousename=" + warehousename + "&whaddress=" + whaddress
			  + "&whmgrman=" + whmgrman + "&whlinkman=" + whlinkman + "&whlinktel=" + whlinktel + "&iscurrent=" + iscurrent + "&erpcode=" + erpcode;
		
		window.returnValue = back_msg;
		window.close();
	}

-->
</script>
</head>

<body>

  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">当前位置：基本信息 -&gt; 仓库管理 -&gt; 修改仓库信息</div></td>
    </tr>
  </table>
 
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right"><span class="red">*</span>仓库名称：</td>
      <td class="yx"><input type="hidden" id="warehouseid" value="<%=warehouseid%>">
     	<input type="text" id="warehousename" value="<%=warehousename%>" maxlength="25" size="20"></td>
      <td width="100px" class="yx1" align="right">仓库管理员：</td>
      <td class="xx1"><input type="text" id="whmgrman" value="<%=whmgrman%>" maxlength="10"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">联系人：</td>
      <td class="yx"><input type="text" id="whlinkman" value="<%=whlinkman%>" maxlength="10"></td>
      <td class="yx1" align="right">联系电话：</td>
      <td class="xx1"><input type="text" id="whlinktel" value="<%=whlinktel%>" maxlength="20"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">仓库地址：</td>
      <td class="xx1" colspan="3"><input type="text" id="whaddress" value="<%=whaddress%>" maxlength="100" size="50"></td>
    </tr>
    <tr>
   	  <td class="yx1" align="right">当前仓库：</td>
      <td class="yx"><input type="checkbox" id="iscurrent" <%if(iscurrent.equals("Y")){%>checked<%}%> class="input_checkbox"></td>
      <td class="yx1" align="right"><div align="right">ERP代码：</td>
      <td class="xx1"><input type="text" id="erpcode" maxlength="30" value="<%=erpcode%>"></td>
    </tr>
    <tr>
      <td colspan="4" align="center">
        <input type="button" onclick="OnOk()" value="&nbsp;&nbsp;&nbsp;保存" class="button_edit">
        <input type="button" onClick="window.close()" name="resetBtn" value="关闭" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
 
</body>
</html>