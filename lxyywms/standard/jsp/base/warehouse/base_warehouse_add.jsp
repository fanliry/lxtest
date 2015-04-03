<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<title>增加仓库信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/inspectClass.js"></script>
<script type="text/javascript">
<!--
	
	//保存新增仓库信息
	function addData(){
	
		var warehousename = document.getElementById("warehousename").value;
		var whaddress = document.getElementById("whaddress").value;
		var whmgrman = document.getElementById("whmgrman").value;
		var whlinkman = document.getElementById("whlinkman").value;
		var whlinktel = document.getElementById("whlinktel").value;
		var erpcode = document.getElementById("erpcode").value;
		
		if(warehousename == null || warehousename.length <1){
			alert("【仓库名称】不能为空!");
			return false;
		}else{
			if(strlen(warehousename) > 50){
				alert("【仓库名称】过长!");
				return;
			}
		}

		var actionStr = "&warehousename=" + warehousename + "&whaddress=" + whaddress
			 + "&whmgrman=" + whmgrman + "&whlinkman=" + whlinkman + "&whlinktel=" + whlinktel + "&erpcode=" + erpcode;
		
		if(document.getElementById("iscurrent").checked){
			
			inspectClass.checkHaveIscurrent(function(data){
				if(data=="1"){
					var del = confirm("该系统中存在当前仓库！是否要将该仓库设置为当前仓库！");
					if(del){
						window.returnValue = actionStr+"&iscurrent=Y&mark=1";
						window.close();
					}else{
						window.returnValue = actionStr+"&iscurrent=N&mark=0";
						window.close();
					}
				}
				if(data=="0"){
					window.returnValue = actionStr+"&iscurrent=Y";
					window.close();
				}
			});
		}else
		{
			window.returnValue =actionStr+"&iscurrent=N";
			window.close();
		}
	}
	
-->
</script>
</head>

<body>
  <form name="myForm" method="post" action="">
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">当前位置：基本信息 -&gt; 仓库管理 -&gt; 新增仓库信息</div></td>
    </tr>
  </table>
  
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right"><span class="red">*</span>仓库名称：</td>
      <td class="yx"><input type="text" id="warehousename" maxlength="50" size="20"></td>
      <td width="100px" class="yx1" align="right"><div align="right">仓库管理员：</td>
      <td class="xx1"><input type="text" id="whmgrman" maxlength="10"></td> 
    </tr>
    <tr>
      <td class="yx1" align="right">联系人：</td>
      <td class="yx"><input type="text" id="whlinkman" maxlength="10"></td>
      <td class="yx1" align="right"><div align="right">联系电话：</td>
      <td class="xx1"><input type="text" id="whlinktel" maxlength="20"></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><div align="right">仓库地址：</td>
      <td class="xx1" colspan="3"><input type="text" id="whaddress" maxlength="100" size="50"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">当前仓库：</td>
      <td class="yx"><input type="checkbox" id="iscurrent" class="input_checkbox"></td>
      <td class="yx1" align="right"><div align="right">ERP代码：</td>
      <td class="xx1"><input type="text" id="erpcode" maxlength="30"></td>
    </tr>
    <tr>
      <td height="27" colspan="4">
        <div align="center">
          <input type="button" onclick="addData()" id="add" value="&nbsp;&nbsp;&nbsp;保存" class="button_add">&nbsp; 
          <input type="reset" id="resetBtn" value="&nbsp;&nbsp;&nbsp;重置" class="button_reset">
          <input type="button" onClick="window.close()" id="resetBtn" value="关闭" class="BUTTON_STYLE1">
        </div>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>