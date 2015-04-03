<%@ page contentType="text/html; charset=GB2312"%>
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
	var condition = "";
	
	//保存
	function saveData(){
	
		var descr = document.getElementById("descr").value;
		var warehouseid = document.getElementById("warehouseid").value;
		var ruleconfigid = document.getElementById("ruleconfigid").value;
		var remark = document.getElementById("remark").value;
		
		if(descr == null || descr.length < 1){
		
			alert("【描述】不能为空！");
			return;
		}
		if(strlen(descr) > 200){
		
			alert("【描述】过长！");
			return;
		}

       	if(ruleconfigid == "" || ruleconfigid.length <1){
       	
       		alert("【补货方式】不能为空!");
       		return; 
       	}
       	
       	if(remark != null && remark.length > 0){
		
			if(strlen(remark) > 200){
			
				alert("【备注】过长！");
				return;
			}
		}
       	
       	var ea_lowerlimit = document.getElementById("ea_lowerlimit").value;
        var ea_uplimit    = document.getElementById("ea_uplimit").value;
        var ea_minreplenishqty = document.getElementById("ea_minreplenishqty").value;
        var cs_lowerlimit = document.getElementById("cs_lowerlimit").value;
        var cs_uplimit = document.getElementById("cs_uplimit").value;
        var cs_minreplenishqty = document.getElementById("cs_minreplenishqty").value;
        var ot_lowerlimit = document.getElementById("ot_lowerlimit").value;
        var ot_uplimit = document.getElementById("ot_uplimit").value;
        var ot_minreplenishqty = document.getElementById("ot_minreplenishqty").value;
       	
       	if(ea_lowerlimit != null && ea_lowerlimit.length > 0 && ea_lowerlimit != 0){

			if(!numChar(ea_lowerlimit)){
				alert("【件拣货区下限】只能为正整数或0！");
				return;
			}
		}
		if(ea_uplimit != null && ea_uplimit.length > 0 && ea_uplimit != 0){

			if(!numChar(ea_uplimit)){
				alert("【件拣货区上限】只能为正整数或0！");
				return;
			}
		}
		if(ea_minreplenishqty != null && ea_minreplenishqty.length > 0 && ea_minreplenishqty != 0){

			if(!numChar(ea_minreplenishqty)){
				alert("【件拣货区最小补货数】只能为正整数或0！");
				return;
			}
		}
		
		if(cs_lowerlimit != null && cs_lowerlimit.length > 0 && cs_lowerlimit != 0){

			if(!numChar(cs_lowerlimit)){
				alert("【箱拣货区下限】只能为正整数或0！");
				return;
			}
		}
		if(cs_uplimit != null && cs_uplimit.length > 0 && cs_uplimit != 0){

			if(!numChar(cs_uplimit)){
				alert("【箱拣货区上限】只能为正整数或0！");
				return;
			}
		}
		if(cs_minreplenishqty != null && cs_minreplenishqty.length > 0 && cs_minreplenishqty != 0){

			if(!numChar(cs_minreplenishqty)){
				alert("【箱拣货区最小补货数】只能为正整数或0！");
				return;
			}
		}

		if(ot_lowerlimit != null && ot_lowerlimit.length > 0 && ot_lowerlimit != 0){

			if(!numChar(ot_lowerlimit)){
				alert("【箱/件拣货区下限】只能为正整数或0！");
				return;
			}
		}
		if(ot_uplimit != null && ot_uplimit.length > 0 && ot_uplimit != 0){

			if(!numChar(ot_uplimit)){
				alert("【箱/件拣货区上限】只能为正整数或0！");
				return;
			}
		}		
		if(ot_minreplenishqty != null && ot_minreplenishqty.length > 0 && ot_minreplenishqty != 0){

			if(!numChar(ot_minreplenishqty)){
				alert("【箱/件拣货区最小补货数】只能为正整数或0！");
				return;
			}
		}
	
		condition = "&descr=" + descr + "&warehouseid=" + warehouseid + "&ruleconfigid=" + ruleconfigid + "&remark=" + remark 
				 + "&ea_lowerlimit=" + ea_lowerlimit + "&ea_uplimit=" + ea_uplimit + "&ea_minreplenishqty=" + ea_minreplenishqty
				 + "&cs_lowerlimit=" + cs_lowerlimit + "&cs_uplimit=" + cs_uplimit + "&cs_minreplenishqty=" + cs_minreplenishqty 
				 + "&ot_lowerlimit=" + ot_lowerlimit + "&ot_uplimit=" + ot_uplimit + "&ot_minreplenishqty=" + ot_minreplenishqty;
													
		window.returnValue = condition;
		window.close();
	}
	
	//规则方式变化的时候
	function selectUom()
	{
		var ruleconfigid =  document.getElementById("ruleconfigid").value;
		if(ruleconfigid != "" && ruleconfigid == "003003")  
       	{
       		document.getElementById("eauom").innerHTML= "箱";
       	}else
       	{
       		document.getElementById("eauom").innerHTML= "托";
       	}
	}
	
	//页面登陆
	function OnLoad(){
		selectObject("", "warehouseid", "1");
		selectObject("", "ruleconfigid", "33");
	}
-->
</script>
</head>

<body onLoad="OnLoad();">
<form name="myForm" method="post" action="">
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：业务规则 -&gt; 补货规则 -&gt; 增加补货规则</div></td>
   </tr>
 </table>

 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td class="yx1" width="100px" align="right"><span class="red">*</span>描述：</td>
     <td class="xx1" colspan="3"><input type="text" id="descr" size="30" maxlength="200"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">所属仓库：</td>
     <td class="yx"><select name="warehouseid"><option value=""></option></select></td>
     <td class="yx1" align="right"><span class="red">*</span>补货方式：</td>
     <td class="xx1"><select id="ruleconfigid" onchange="selectUom();"><option value=""></option></select></td>
   </tr>
   <tr>
     <td class="y1" width="100px" align="right">备注：</td>
     <td colspan="3"><input type="text" id="remark" size="50" maxlength="200"></td>
   </tr>
 </table>
 
 <table><tr><td height="10"></td></tr></table> 

 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td class="yx1" align="center">货区</td>
     <td class="yx1" align="center">下限</td>
     <td class="yx1" align="center">上限</td>
     <td class="x1" align="center">最小补货数量</td>  
  </tr>
   <tr>
     <td class="yx" align="center">件拣货区：</td>
     <td class="yx" align="center"><input type="text" name="ea_lowerlimit" size="15" maxlength="10" value="0">件</td>
     <td class="yx" align="center"><input type="text" name="ea_uplimit" size="15" maxlength="10" value="0">件</td>
     <td class="xx1" align="center"><input type="text" name="ea_minreplenishqty" size="15" maxlength="10" value="0"><label id="eauom">托</label></td>
  </tr>
  <tr>
     <td class="yx" align="center">箱拣货区：</td>
     <td class="yx" align="center"><input type="text" name="cs_lowerlimit" size="15" maxlength="10" value="0">箱</td>
     <td class="yx" align="center"><input type="text" name="cs_uplimit" size="15" maxlength="10" value="0">箱</td>
     <td class="xx1" align="center"><input type="text" name="cs_minreplenishqty" size="15" maxlength="10" value="0">托</td>
  </tr>
  <tr>
     <td class="x" align="center">箱/件拣货区：</td>
     <td class="x" align="center"><input type="text" name="ot_lowerlimit" size="15" maxlength="10" value="0">件</td>
     <td class="x" align="center"><input type="text" name="ot_uplimit" size="15" maxlength="10" value="0">件</td>
     <td align="center"><input type="text" name="ot_minreplenishqty" size="15" maxlength="10" value="0">托</td>
  </tr>
 </table> 

 <table><tr><td height="10"></td></tr></table> 
 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">  
   <tr>
     <td height="27" align="center">
        <input type="button" onclick="saveData()" id="add" value="&nbsp;&nbsp;&nbsp;保存" class="button_add">&nbsp; 
        <input type="reset" id="resetDetailBtn" value="&nbsp;&nbsp;&nbsp;重置" class="button_reset">&nbsp;
        <input type="button" onClick="window.close()" id="resetBtn" value="关闭" class="BUTTON_STYLE1">
     </td>
   </tr>
 </table>

</form> 
</body>
</html>