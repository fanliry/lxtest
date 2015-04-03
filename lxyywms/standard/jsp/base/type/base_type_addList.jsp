<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.CommonTools"%>
<%
    String typevalue = CommonTools.getStrToGb2312(request.getParameter("typevalue"));
	String typename = CommonTools.getStrToGb2312(request.getParameter("typename"));
%>
<html>
<head>
<title>新增类型信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript">
<!--
    var state = false;
    //保存新增类型值信息
	function saveData(){
	
		var typevalue = document.getElementById("typevalue").value;
		var typename = document.getElementById("typename").value;
		var selecttext = document.getElementById("selecttext").value;
		var selectvalue = document.getElementById("selectvalue").value;
		
		var typeid = typevalue + "_" + selectvalue;
		
		window.returnValue = "&typeid=" + typeid + "&typevalue=" + typevalue + "&typename=" + typename
			 + "&selecttext=" + selecttext + "&selectvalue=" + selectvalue;
		window.close();
	}
	
	function isState(data){
		state=data;
	}
	
	//数据检验
	function checkData(){
	
		var typevalue = document.getElementById("typevalue").value;
		var selecttext = document.getElementById("selecttext").value;
		var selectvalue = document.getElementById("selectvalue").value;
		
		var str = typevalue + "_" + selectvalue;
        DWREngine.setAsync(false);
        selectView.getTypeIdStatus(str,{callback:function(data){isState(data);}});
        DWREngine.setAsync(true);
        
		if(selecttext == null || selecttext.length <1)
		{
			alert("【列表内容】不能为空!");
			return false;
		}
		if(selectvalue == null || selectvalue.length <1 || state == true)
		{
			alert("【列表值】不能为空!  或已存在该类型值");
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
      <td height="25"><div class="font_001F56_bold_12">当前位置：基本信息 -&gt; 类型管理 -&gt; 新增类型值</div></td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
  	<tr>
      <td width="100px" class="yx1" align="right">类型值：</td>
      <td class="yx"><input type="text" id="typevalue" maxlength="15" value="<%=typevalue%>" class="input_readonly" readonly></td>
      <td width="100px" class="yx1" align="right">类型名：</td>
      <td class="xx1"><input type="text" id="typename" maxlength="15" size="15" value="<%=typename%>" class="input_readonly" readonly></td>
    </tr>
    <tr>
      <td width="100px" class="yx1" align="right"><span class="red">*</span>列表内容：</td>
      <td class="yx"><input type="text" id="selecttext" maxlength="15"></td>
      <td width="100px" class="yx1" align="right"><span class="red">*</span>列表值：</td>
      <td class="xx1"><input type="text" id="selectvalue" maxlength="15" size="15"></td>
    </tr>
    <tr>
      <td height="27" colspan="4" class="TD_ADD"><div align="center">
        <input type="button" onclick="if(checkData()!=false){saveData();}" name="add" value="&nbsp;&nbsp;&nbsp;保存" class="button_add">&nbsp; 
        <input type="reset" name="resetBtn" value="&nbsp;&nbsp;&nbsp;重置" class="button_reset">
        <input type="button" onClick="window.close()" name="resetBtn" value="关闭" class="BUTTON_STYLE1"></div>
      </td>
    </tr>
  </table>
  </form>
  
</body>
</html>