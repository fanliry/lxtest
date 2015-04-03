<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<title>增加</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript">
<!--
	function saveData()
	{
		var descr =  document.getElementById("descr").value;
		var warehouseid = document.getElementById("warehouseid").value;
		var remark = document.getElementById("remark").value;
		
		if(descr == null || descr.length < 1){
		
			alert("【描述】不能为空！");
			return;
		}
		if(strlen(descr) > 200){
		
			alert("【描述】过长！");
			return;
		}

       	if(remark != null && remark.length > 0){
		
			if(strlen(remark) > 200){
			
				alert("【备注】过长！");
				return;
			}
		}
		
		condition = "&descr=" + descr + "&warehouseid=" + warehouseid + "&remark=" + remark;
		window.returnValue = condition;
		window.close();
	}
	
	//页面登陆
	function OnLoad(){
		selectObject("", "warehouseid", "1");
	}
-->
</script>
</head>

<body onLoad="OnLoad();">
<form name="myForm" method="post" action="">
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：业务规则 -&gt; 分配规则 -&gt; 新增分配规则</div></td>
   </tr>
 </table>

 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
   <tr>
     <td class="yx1" width="100px" align="right"><span class="red">*</span>描述：</td>
     <td class="xx1"><input type="text" id="descr" size="30" maxlength="200"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">所属仓库：</td>
     <td class="xx1"><select name="warehouseid"><option value=""></option></select></td>
   </tr>
   <tr>
     <td class="yx1" align="right">备注：</td>
     <td class="xx1"><textarea id="remark" cols="83" rows="3"></textarea></td>
   </tr>
   <tr>
     <td height="27" colspan="2" align="center">
       <input type="button" onclick="saveData()" name="add" value="&nbsp;&nbsp;&nbsp;保存" class="button_add">&nbsp; 
       <input type="reset" name="resetDetailBtn" value="&nbsp;&nbsp;&nbsp;重置" class="button_reset">&nbsp;
       <input type="button" onClick="window.close()" name="resetBtn" value="关闭" class="BUTTON_STYLE1">
     </td>
   </tr>
 </table>
 </form>

</body>
</html>