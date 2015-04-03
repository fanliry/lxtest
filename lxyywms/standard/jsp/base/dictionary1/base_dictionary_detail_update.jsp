<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseType" %>
<%
	BaseType type = (BaseType)request.getAttribute("type");
	String typeid = type.getTypeid();			// 类型ID
    String typename = type.getTypename();		// 类型名
    String typevalue = type.getTypevalue();		// 类型值
    String selecttext = type.getSelecttext();	// 下拉列表显示内容
    String selectvalue = type.getSelectvalue();	// 下拉列表值
%>
<html>
<head>
<title>修改类型信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript">
<!--
    var state = false;
    function isState(data){
		state=data;
	}
	//文本内容失去焦点
	function changetText(){
		 document.getElementById("selectvalue").value = document.getElementById("selecttext").value
		}
	function OnOk(){
    	var typename = document.getElementById("typename").value;
    	var typevalue = document.getElementById("typevalue").value;
		var selecttext = document.getElementById("selecttext").value;
		var selectvalue = document.getElementById("selectvalue").value;
		var selectvalueOld = document.getElementById("selectvalueHide").value;
		
		state = false;// 防止第二次在进行验证时，沿用以前的值 因为state是全局变量
		DWREngine.setAsync(false);
        selectView.getSelectValueStatus(typevalue, selectvalue,{callback:function(data){isState(data);}});
        DWREngine.setAsync(true);

		if(typename == null || typename.length <1)
		{
			alert("【类型名】不能为空!");
			return false;
		}
		if(selecttext == null || selecttext.length <1)
		{
			alert("【列表内容】不能为空!");
			return false;
		}
		if(selectvalue != null && selectvalue == selectvalueOld)
		{
			state = false;
		}
		if(selectvalue == null || selectvalue.length <1 || state == true)
		{
			alert("【列表值】不能为空! 或已经存在该列表值");
			return false;
		}
		
		window.returnValue = "&typeId=<%=typeid%>&typename=" + typename + "&typeVlaue=" + typevalue + 
			"&selecttext=" + selecttext + "&selectvalue=" + selectvalue;
		window.close();
	}
	
-->
</script>
</head>

<body>

  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">当前位置：基本信息  -&gt; 类型管理 -&gt; 修改类型值</div></td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right">类型值：</td>
      <td class="yx">
      	<input type="text" name="typevalue" maxlength="15" size="15" value="<%=typevalue%>" class="input_readonly" readonly></td>
      <td width="100px" class="yx1" align="right">类型名：</td>
      <td class="xx1"><input type="text" name="typename" maxlength="15" size="15" value="<%=typename%>" class="input_readonly" readonly></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>列表内容：</td>
      <td class="yx"><input type="text" name="selecttext" maxlength="15" size="15" value="<%=selecttext%>"  onblur="changetText()"></td>
      <td class="yx1" align="right"><span class="red">*</span>列表值：</td>
      <td class="xx1">
      	<input name="selectvalueHide" type="hidden" value="<%=selectvalue%>"/>
      	<input type="text" name="selectvalue" maxlength="15" size="15" value="<%=selectvalue%>" class="input_readonly" readonly></td>
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