<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>

<html>
<head>
<title>增加库存调整单</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>
<script type="text/javascript">
<!--
	//保存
	function saveData()
	{
	
	   var strwarehouseid = document.getElementById("warehouseid").value;
	   var strstatus = document.getElementById("status").value;
	   var stradjtype = document.getElementById("adjtype").value;
	   var strreasoncode = document.getElementById("reasoncode").value;
	   var strreasondiscr = document.getElementById("reasondiscr").value;
	   if(strwarehouseid == null ||  strwarehouseid.length<1)
		{
			alert("【仓库】不能为空");
			return;
		}
		if(stradjtype == null || stradjtype.length<1)
		{
			alert("【调整类型】不能为空");
			return;
		}
		if(strreasoncode == null || strreasoncode.length<1)
		{
			alert("【调整原因】不能为空");
			return;
		}
		
		var strParam = "BMSService?actionCode=inventoryAdjustAction&method=ricoExecAdd" 
        					+ "&warehouseid="+strwarehouseid
        					+ "&status="+strstatus
        					+ "&adjusttype="+stradjtype
        					+ "&reasoncode="+strreasoncode
							+ "&reasondiscr=" + strreasondiscr;   
        window.returnValue=strParam;
		window.close();
	}
	function checkData()
	{
		return true;
	}
	function basCustomer(obj, url, obj1)
    {
		var strUrl=url + '/jsp/selectPages/customer_select.jsp';
		var result = showModalDialog(strUrl,'','dialogWidth=650px;dialogHeight=440px');	
		if(result != null && result.length > 0)
		{
			 var tempParam = result;
	
			 var temStr = tempParam.split("|");
			 		
			 document.getElementById(obj).value = temStr[0];
			 if(obj1)
			 {
			 	document.getElementById(obj1).value = temStr[1];
			 }
		}
    }
	//页面登录
	function OnLoad(){
		
		//仓库
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");	
		DWREngine.setAsync(true);
		selectType("", 'adjtype', 'tzlx');
		selectType("", 'reasoncode', 'tzyy');
	}
-->
</script>
</head>
<%
	String strTime = StrTools.getCurrDateTime(5);
%>
<body onLoad="OnLoad()">

 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：库存管理 -&gt;库存调整 -&gt; 增加库存调整单</div></td>
   </tr>
 </table>
 <form>
 <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
   <tr>
     <td class="yx1" width="100" align="right">仓库：</td>
     <td class="yx"><select name="warehouseid" style="width:117px;"><option value=""></option></select></td>
	 <td class="yx1"><div align="right">状态：</div></td>
     <td class="xx1"><div align="left">
     <select name="status" class="input_readonly" style="width:130px;">
	         <option value="0" selected="selected">创建</option>
	 </select>
     </div></td>
   </tr>
   <tr>
	      <td class="yx1" align="right">调整类型：</td>
	      <td class="yx">
	      	<select id="adjtype" name="adjtype" style="width:117px;">
                <option value="">--请选择--</option>
              </select>
	      </td>
	      <td class="yx1" align="right">调整原因：</td>
	      <td class="xx1">
	      	<select id="reasoncode" style="width:117px;">
                <option value="">--请选择--</option>
              </select>
	      </td>

   </tr>
   <tr>
     <td class="yx1"><div align="right">原因描述：</div></td>
     <td class="xx1" colspan="3"><div align="left">
     		<input type="text" size="40" name="reasondiscr">    
     </div></td>
   </tr>
   <tr>
     <td height="27" colspan="4" align="center" ><input name="add" type="button" class="button_add" id="add" value="&nbsp;&nbsp;保存" onClick="saveData();"/>
            <input name="button" type="reset" class="button_reset" id="button" value="&nbsp;&nbsp;重置" />
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="关闭" onClick="window.close();" />
     </td>
   </tr>
 </table>
 </form>
</body>
</html>