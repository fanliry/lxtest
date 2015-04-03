<%@ page contentType="text/html; charset=GBK"%>

<html>
<head>
<title>新增类型信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript">
<!--
    var state = false;
	function saveData(){//保存新增类型信息
	
		var actionStr = "&typename=" + document.getElementById("typename").value
        		+ "&typevalue=" + document.getElementById("typevalue").value;
				
				window.returnValue =actionStr;
				window.close();
	}
	function isState(data){
		state=data;
	}
	function checkData(){//数据检验
		var typename = document.getElementById("typename").value;
		var typevalue = document.getElementById("typevalue").value;
		state = false;// 防止第二次在进行验证时，沿用以前的值 因为state是全局变量
		DWREngine.setAsync(false);
        selectView.getTypeIdStatus(typevalue,{callback:function(data){isState(data);}});
        DWREngine.setAsync(true);
		if(typename == null || typename.length <1)
		{
			alert("【类型名】不能为空!");
			return false;
		}
		if(typevalue == null || typevalue.length <1 || state == true)
		{
			alert("【类型代码】不能为空!  或已存在该类型值");
			return false;
		}
	}
-->
</script>
</head>

<body>

  <form name="myForm" method="post" action="">
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">当前位置：基本信息 -&gt; 类型管理 -&gt; 新增类型</div></td>
    </tr>
  </table>
  
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right"><span class="red">*</span>类型名：</td>
      <td class="yx"><input type="text" name=typename maxlength="15"></td>
      <td width="100px" class="yx1" align="right"><span class="red">*</span>类型代码：</td>
      <td class="xx1"><input type="text" name="typevalue" maxlength="15" size="15"></td>
    </tr>
    <tr>
      <td height="27" colspan="4"><div align="center">
        <input type="button" onclick="saveData()" name="add" value="&nbsp;&nbsp;&nbsp;保存" class="button_add">&nbsp; 
        <input type="reset" name="resetBtn" value="&nbsp;&nbsp;&nbsp;重置" class="button_reset">
        <input type="button" onClick="window.close()" name="resetBtn" value="关闭" class="BUTTON_STYLE1"></div>
      </td>
    </tr>
  </table>
  </form>
  
</body>
</html>