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
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>
<script type="text/javascript">
<!--
	var condition = "";
	//检验是否为中文字符
	function CheckC(str)
	{
		var ts, tscode;
		for(var i=0; i<str.length; i++) 
		{ 
			ts = str.substring(i); 
			tscode = str.charCodeAt(i); 
			if(tscode >= 19968) 
			{
				return false;
			}
		}
		return true;
	}
	
	//保存
	function Save(){
	
		var lottype = document.getElementById("lottype").value;	//类型
		var remark = document.getElementById("remark").value;	//备注
	
		var lotname1 = document.getElementById("lotname1").value;		//批次属性名称
		var lotid1 = document.getElementById("lotid1").value;			//批次属性ID
		var islot1 = document.getElementById("islot1").value;			//是否显示或者是否分组统计 Y N
		var lotseq1 = document.getElementById("lotseq1").value;			//显示顺序或者分组统计的顺序

		var lotname2 = document.getElementById("lotname2").value;		//批次属性名称
		var lotid2 = document.getElementById("lotid2").value;			//批次属性ID
		var islot2 = document.getElementById("islot2").value;			//是否显示或者是否分组统计 Y N
		var lotseq2 = document.getElementById("lotseq2").value;			//显示顺序或者分组统计的顺序

		var lotname3 = document.getElementById("lotname3").value;		//批次属性名称
		var lotid3 = document.getElementById("lotid3").value;			//批次属性ID
		var islot3 = document.getElementById("islot3").value;			//是否显示或者是否分组统计 Y N
		var lotseq3 = document.getElementById("lotseq3").value;			//显示顺序或者分组统计的顺序
		
		var lotname4 = document.getElementById("lotname4").value;		//批次属性名称
		var lotid4 = document.getElementById("lotid4").value;			//批次属性ID
		var islot4 = document.getElementById("islot4").value;			//是否显示或者是否分组统计 Y N
		var lotseq4 = document.getElementById("lotseq4").value;			//显示顺序或者分组统计的顺序
		
		var lotname5 = document.getElementById("lotname5").value;		//批次属性名称
		var lotid5 = document.getElementById("lotid5").value;			//批次属性ID
		var islot5 = document.getElementById("islot5").value;			//是否显示或者是否分组统计 Y N
		var lotseq5 = document.getElementById("lotseq5").value;			//显示顺序或者分组统计的顺序
		
		var lotname6 = document.getElementById("lotname6").value;		//批次属性名称
		var lotid6 = document.getElementById("lotid6").value;			//批次属性ID
		var islot6 = document.getElementById("islot6").value;			//是否显示或者是否分组统计 Y N
		var lotseq6 = document.getElementById("lotseq6").value;			//显示顺序或者分组统计的顺序
		
		var lotname7 = document.getElementById("lotname7").value;		//批次属性名称
		var lotid7 = document.getElementById("lotid7").value;			//批次属性ID
		var islot7 = document.getElementById("islot7").value;			//是否显示或者是否分组统计 Y N
		var lotseq7 = document.getElementById("lotseq7").value;			//显示顺序或者分组统计的顺序
		
		var lotname8 = document.getElementById("lotname8").value;		//批次属性名称
		var lotid8 = document.getElementById("lotid8").value;			//批次属性ID
		var islot8 = document.getElementById("islot8").value;			//是否显示或者是否分组统计 Y N
		var lotseq8 = document.getElementById("lotseq8").value;			//显示顺序或者分组统计的顺序
		
		var lotname9 = document.getElementById("lotname9").value;		//批次属性名称
		var lotid9 = document.getElementById("lotid9").value;			//批次属性ID
		var islot9 = document.getElementById("islot9").value;			//是否显示或者是否分组统计 Y N
		var lotseq9 = document.getElementById("lotseq9").value;			//显示顺序或者分组统计的顺序
		
		var lotname10 = document.getElementById("lotname10").value;		//批次属性名称
		var lotid10 = document.getElementById("lotid10").value;			//批次属性ID
		var islot10 = document.getElementById("islot10").value;			//是否显示或者是否分组统计 Y N
		var lotseq10 = document.getElementById("lotseq10").value;		//显示顺序或者分组统计的顺序
		
		var lotname11 = document.getElementById("lotname11").value;		//批次属性名称
		var lotid11 = document.getElementById("lotid11").value;			//批次属性ID
		var islot11 = document.getElementById("islot11").value;			//是否显示或者是否分组统计 Y N
		var lotseq11 = document.getElementById("lotseq11").value;		//显示顺序或者分组统计的顺序
		
		var lotname12 = document.getElementById("lotname12").value;		//批次属性名称
		var lotid12 = document.getElementById("lotid12").value;			//批次属性ID
		var islot12 = document.getElementById("islot12").value;			//是否显示或者是否分组统计 Y N
		var lotseq12 = document.getElementById("lotseq12").value;		//显示顺序或者分组统计的顺序
		
		if(lottype == null || lottype.length < 1)
		{
			alert("【类型】不能为空！");
			return;
		}
		if(remark != null && remark.length > 0)
		{
			if(strlen(remark) > 200)
			{
				alert("【备注】过长！");
				return;
			}
		}
		
		if(lotid1 != null && lotid1.length > 0){
			if(!CheckC(lotid1))
			{
				alert("【属性代码1】不能输入中文！");
				return;
			}
			
		}
		if(lotseq1 == null || lotseq1.length < 1){
			alert("【顺序1】不能为空！");
			return;
		}else{
			if(!numChar(lotseq1)){
				alert("【顺序1】只能输入数字！");
				return;
			}
		}
		
		if(lotid2 != null && lotid2.length > 0){
			if(!CheckC(lotid2))
			{
				alert("【属性代码2】不能输入中文！");
				return;
			}
			
		}
		if(lotseq2 == null || lotseq2.length < 1){
			alert("【顺序2】不能为空！");
			return;
		}else{
			if(!numChar(lotseq2)){
				alert("【顺序2】只能输入数字！");
				return;
			}
		}
		
		if(lotid3 != null && lotid3.length > 0){
			if(!CheckC(lotid3))
			{
				alert("【属性代码3】不能输入中文！");
				return;
			}
			
		}
		if(lotseq3 == null || lotseq3.length < 1){
			alert("【顺序3】不能为空！");
			return;
		}else{
			if(!numChar(lotseq3)){
				alert("【顺序3】只能输入数字！");
				return;
			}
		}
		
		if(lotid4 != null && lotid4.length > 0){
			if(!CheckC(lotid4))
			{
				alert("【属性代码4】不能输入中文！");
				return;
			}
			
		}
		if(lotseq4 == null || lotseq4.length < 1){
			alert("【顺序4】不能为空！");
			return;
		}else{
			if(!numChar(lotseq4)){
				alert("【顺序4】只能输入数字！");
				return;
			}
		}
		
		if(lotid5 != null && lotid5.length > 0){
			if(!CheckC(lotid5))
			{
				alert("【属性代码5】不能输入中文！");
				return;
			}
			
		}
		if(lotseq5 == null || lotseq5.length < 1){
			alert("【顺序5】不能为空！");
			return;
		}else{
			if(!numChar(lotseq5)){
				alert("【顺序5】只能输入数字！");
				return;
			}
		}
		
		if(lotid6 != null && lotid6.length > 0){
			if(!CheckC(lotid6))
			{
				alert("【属性代码6】不能输入中文！");
				return;
			}
			
		}
		if(lotseq6 == null || lotseq6.length < 1){
			alert("【顺序6】不能为空！");
			return;
		}else{
			if(!numChar(lotseq6)){
				alert("【顺序6】只能输入数字！");
				return;
			}
		}
		
		if(lotid7 != null && lotid7.length > 0){
			if(!CheckC(lotid7))
			{
				alert("【属性代码7】不能输入中文！");
				return;
			}
			
		}
		if(lotseq7 == null || lotseq7.length < 1){
			alert("【顺序7】不能为空！");
			return;
		}else{
			if(!numChar(lotseq7)){
				alert("【顺序7】只能输入数字！");
				return;
			}
		}
		
		if(lotid8 != null && lotid8.length > 0){
			if(!CheckC(lotid8))
			{
				alert("【属性代码8】不能输入中文！");
				return;
			}
			
		}
		if(lotseq8 == null || lotseq8.length < 1){
			alert("【顺序8】不能为空！");
			return;
		}else{
			if(!numChar(lotseq8)){
				alert("【顺序8】只能输入数字！");
				return;
			}
		}
		
		if(lotid9 != null && lotid9.length > 0){
			if(!CheckC(lotid9))
			{
				alert("【属性代码9】不能输入中文！");
				return;
			}
			
		}
		if(lotseq9 == null || lotseq9.length < 1){
			alert("【顺序9】不能为空！");
			return;
		}else{
			if(!numChar(lotseq9)){
				alert("【顺序9】只能输入数字！");
				return;
			}
		}
		
		if(lotid10 != null && lotid10.length > 0){
			if(!CheckC(lotid10))
			{
				alert("【属性代码10】不能输入中文！");
				return;
			}
			
		}
		if(lotseq10 == null || lotseq10.length < 1){
			alert("【顺序10】不能为空！");
			return;
		}else{
			if(!numChar(lotseq10)){
				alert("【顺序10】只能输入数字！");
				return;
			}
		}
		
		if(lotid11 != null && lotid11.length > 0){
			if(!CheckC(lotid11))
			{
				alert("【属性代码11】不能输入中文！");
				return;
			}
			
		}
		if(lotseq11 == null || lotseq11.length < 1){
			alert("【顺序11】不能为空！");
			return;
		}else{
			if(!numChar(lotseq11)){
				alert("【顺序11】只能输入数字！");
				return;
			}
		}
		
		if(lotid12 != null && lotid12.length > 0){
			if(!CheckC(lotid12))
			{
				alert("【属性代码12】不能输入中文！");
				return;
			}
			
		}
		if(lotseq12 == null || lotseq12.length < 1){
			alert("【顺序12】不能为空！");
			return;
		}else{
			if(!numChar(lotseq12)){
				alert("【顺序12】只能输入数字！");
				return;
			}
		}

		condition = "&lottype=" + lottype + "&remark=" + remark 
			 + "&lotname1=" + lotname1 + "&lotid1=" + lotid1 + "&islot1=" + islot1 + "&lotseq1=" + lotseq1 
			 + "&lotname2=" + lotname2 + "&lotid2=" + lotid2 + "&islot2=" + islot2 + "&lotseq2=" + lotseq2
			 + "&lotname3=" + lotname3 + "&lotid3=" + lotid3 + "&islot3=" + islot3 + "&lotseq3=" + lotseq3 
			 + "&lotname4=" + lotname4 + "&lotid4=" + lotid4 + "&islot4=" + islot4 + "&lotseq4=" + lotseq4 
			 + "&lotname5=" + lotname5 + "&lotid5=" + lotid5 + "&islot5=" + islot5 + "&lotseq5=" + lotseq5
			 + "&lotname6=" + lotname6 + "&lotid6=" + lotid6 + "&islot6=" + islot6 + "&lotseq6=" + lotseq6
			 + "&lotname7=" + lotname7 + "&lotid7=" + lotid7 + "&islot7=" + islot7 + "&lotseq7=" + lotseq7
			 + "&lotname8=" + lotname8 + "&lotid8=" + lotid8 + "&islot8=" + islot8 + "&lotseq8=" + lotseq8
			 + "&lotname9=" + lotname9 + "&lotid9=" + lotid9 + "&islot9=" + islot9 + "&lotseq9=" + lotseq9
			 + "&lotname10=" + lotname10 + "&lotid10=" + lotid10 + "&islot10=" + islot10 + "&lotseq10=" + lotseq10
			 + "&lotname11=" + lotname11 + "&lotid11=" + lotid11 + "&islot11=" + islot11 + "&lotseq11=" + lotseq11
			 + "&lotname12=" + lotname12 + "&lotid12=" + lotid12 + "&islot12=" + islot12 + "&lotseq12=" + lotseq12;
	
		window.returnValue = condition;
		window.close();
	}
	
	//选择批次属性后
	function getLotAtts(obj){
		lotTool.getLotDetails(obj.value, {callback:function(data){
			var tempstr;
			for(var i=0; i<data.length; i++){
				tempstr = "lotname" + (i+1);
				document.getElementById(tempstr).value = data[i].m_strAttname;		//批次属性名称
			}
		}});
		
	}
	
	//登陆页面
	function OnLoad(){
		//同步
		DWREngine.setAsync(false);
		selectType('', 'lottype', 'pclx');	//类型
		selectLot("", "lot");				//批次属性
		DWREngine.setAsync(true);
	}
-->
</script>
</head>

<body onLoad="OnLoad();">
<form name="myForm" method="post" action="">
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：批次设置 -&gt; 增加批次设置</div></td>
   </tr>
 </table>
 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td class="yx1" width="100px" align="right"><span class="red">*</span>类型：</td>
     <td class="yx"><select id="lottype"><option value=""></option></select></td>
     <td class="yx1" width="100px" align="right">批次属性：</td>
     <td class="xx1"><select id="lot" onchange="getLotAtts(this)"><option value=""></option></select></td>
   </tr>
   <tr>
     <td class="y2" align="right">备注：</td>
     <td colspan="3"><textarea id="remark" cols="70" rows="3"></textarea></td>
   </tr>
 </table>
 
 <table><tr><td height="10"></td></tr></table> 
 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td class="yx1" align="center">属性名称</td>
     <td class="yx1" align="center">属性代码</td>
     <td class="yx1" align="center">是否使用</td>
     <td class="x1" align="center">顺序</td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="lotname1" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid1" size="15" maxlength="15" value="lotatt1" disabled></td>
     <td class="yx" align="center"><select id="islot1" style="width:100px;"><option value="Y">是</option><option value="N">否</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq1" size="15" maxlength="2" value="1"></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="lotname2" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid2" size="15" maxlength="15" value="lotatt2" disabled></td>
     <td class="yx" align="center"><select id="islot2" style="width:100px;"><option value="Y">是</option><option value="N">否</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq2" size="15" maxlength="2" value="2"></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="lotname3" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid3" size="15" maxlength="15" value="lotatt3" disabled></td>
     <td class="yx" align="center"><select id="islot3" style="width:100px;"><option value="Y">是</option><option value="N">否</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq3" size="15" maxlength="2" value="3"></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="lotname4" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid4" size="15" maxlength="15" value="lotatt4" disabled></td>
     <td class="yx" align="center"><select id="islot4" style="width:100px;"><option value="Y">是</option><option value="N">否</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq4" size="15" maxlength="2" value="4"></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="lotname5" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid5" size="15" maxlength="15" value="lotatt5" disabled></td>
     <td class="yx" align="center"><select id="islot5" style="width:100px;"><option value="Y">是</option><option value="N">否</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq5" size="15" maxlength="2" value="5"></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="lotname6" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid6" size="15" maxlength="15" value="lotatt6" disabled></td>
     <td class="yx" align="center"><select id="islot6" style="width:100px;"><option value="Y">是</option><option value="N">否</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq6" size="15" maxlength="2" value="6"></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="lotname7" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid7" size="15" maxlength="15" value="lotatt7" disabled></td>
     <td class="yx" align="center"><select id="islot7" style="width:100px;"><option value="Y">是</option><option value="N">否</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq7" size="15" maxlength="2" value="7"></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="lotname8" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid8" size="15" maxlength="15" value="lotatt8" disabled></td>
     <td class="yx" align="center"><select id="islot8" style="width:100px;"><option value="Y">是</option><option value="N">否</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq8" size="15" maxlength="2" value="8"></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="lotname9" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid9" size="15" maxlength="15" value="lotatt9" disabled></td>
     <td class="yx" align="center"><select id="islot9" style="width:100px;"><option value="Y">是</option><option value="N">否</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq9" size="15" maxlength="2" value="9"></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="lotname10" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid10" size="15" maxlength="15" value="lotatt10" disabled></td>
     <td class="yx" align="center"><select id="islot10" style="width:100px;"><option value="Y">是</option><option value="N">否</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq10" size="15" maxlength="2" value="10"></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="lotname11" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid11" size="15" maxlength="15" value="lotatt11" disabled></td>
     <td class="yx" align="center"><select id="islot11" style="width:100px;"><option value="Y">是</option><option value="N">否</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq11" size="15" maxlength="2" value="11"></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="lotname12" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid12" size="15" maxlength="15" value="lotatt12" disabled></td>
     <td class="yx" align="center"><select id="islot12" style="width:100px;"><option value="Y">是</option><option value="N">否</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq12" size="15" maxlength="2" value="12"></td>
   </tr>
 </table>
 
 <table><tr><td height="10"></td></tr></table> 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">  
   <tr>
     <td height="27" align="center">
        <input type="button" onclick="Save()" id="add" value="&nbsp;&nbsp;&nbsp;保存" class="button_add">&nbsp; 
        <input type="reset" id="resetDetailBtn" value="&nbsp;&nbsp;&nbsp;重置" class="button_reset">&nbsp;
        <input type="button" onClick="window.close()" id="resetBtn" value="关闭" class="BUTTON_STYLE1">
     </td>
   </tr>
 </table>
 
 </form>
</body>
</html>