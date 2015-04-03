<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<title>增加物品类别信息</title>
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
		var ptname = document.getElementById("ptname").value;
		var parentid = document.getElementById("parentid").value;
		var ptorder = document.getElementById("ptorder").value;
		
		if(ptname == null || ptname.length <1)
		{
			alert("【类别名】不能为空!");
			return;
		}
		
		if(strlen(ptname) > 50){
				alert("【类别名】过长!");
				return;
		}
		
		if(ptorder == null || ptorder.length <1)
		{
			alert("【顺序】不能为空!");
			return;
		}
			
		if(ptorder!=null && ptorder.length>0){
			if(!numChar(ptorder)){
				alert("【顺序】只能输入大于0的整数!");
				return;
			}
		}

		var back_msg = "&ptname=" + ptname + "&parentid=" + parentid + "&ptorder=" + ptorder;
		window.returnValue = back_msg;
    	window.close();
	}
	
	function OnLoad(){
		selectObject("", "parentid", "6");
	}
-->
</script>
</head>

<body onload="OnLoad();">
 <form name="myForm" method="post" action="">
 </table>
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">当前位置：基本信息 -&gt; 物品类别管理 -&gt; 新增物品类别信息</div></td>
    </tr>
  </table>
 
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td class="yx1" width="100px" align="right"><span class="red">*</span>类别名：</td>
      <td class="xx1"><input type="text" name="ptname" maxlength="50" size="40"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">父类别：</td>
      <td class="xx1"><select name="parentid"><option value=""></option></select></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>顺序：</td>
      <td class="xx1"><input type="text" name="ptorder" maxlength="4" size="4"></td>
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