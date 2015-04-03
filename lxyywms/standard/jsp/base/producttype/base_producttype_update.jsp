<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseProducttype" %>
<%
	BaseProducttype producttype = (BaseProducttype)request.getAttribute("producttype");  
	String ptid = producttype.getPtid();		// 物品类别编号
    String ptname = producttype.getPtname();	// 类别名
    String parentid = producttype.getParentid();// 父ID
    Integer ptorder = producttype.getPtorder();	// 顺序
%>
<html>
<head>
<title>修改物品类别信息</title>
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
	//保存物品类别信息
	function OnOk(){
		var id = document.getElementById("ptid").value;
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
			
		if(ptorder!=null && ptorder.length>0)
		{
			if(!numChar(ptorder)){
				alert("【顺序】只能输入大于0的整数!");
				return;
			}
		}

		var back_msg = "&id=" + id + "&ptname=" + ptname + "&parentid=" + parentid + "&ptorder=" + ptorder;
		window.returnValue = back_msg;
    	window.close();
	}
	
	function OnLoad(){
		selectObject("<%=parentid%>", "parentid", "6");
	}

-->
</script>
</head>

<body onload="OnLoad();">

  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">当前位置：基本信息 -&gt; 物品类别管理 -&gt; 修改物品类别信息</div></td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td class="yx1" width="100px" align="right">类别ID：</td>
      <td class="xx1"><input type="text" name="ptid" maxlength="25" size="25" value="<%=ptid%>" class="input_readonly" readonly></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">类别名：</td>
      <td class="xx1"><input type="text" name="ptname" maxlength="50" size="40" value="<%=ptname%>"><font color="red">*</font></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">父类别：</td>
      <td class="xx1"><select name="parentid"><option value=""></option></select></td>
    </tr>
    <tr>
      <td class="yx1" align="right">顺序：</td>
      <td class="xx1"><input type="text" name="ptorder" maxlength="4" size="4" value="<%=ptorder==null?"":ptorder%>"></td>
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