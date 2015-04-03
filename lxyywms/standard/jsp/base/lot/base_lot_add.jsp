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
	function Save()
	{
		/*批次*/
		var descr = document.getElementById("descr").value;
		var remark = document.getElementById("remark").value;
		
		/*批次详细*/
		var attname1 = document.getElementById("attname1").value;
		var attcode1 = document.getElementById("attcode1").value;
		var lotatt_flag1 = document.getElementById("lotatt_flag1").value;
		var lottype1 = document.getElementById("lottype1").value;
		var lotsearch1 = document.getElementById("lotsearch1").value;
		var lottypevalue1 = document.getElementById("lottypevalue1").value;

		var attname2 = document.getElementById("attname2").value;
		var attcode2 = document.getElementById("attcode2").value;
		var lotatt_flag2 = document.getElementById("lotatt_flag2").value;
		var lottype2 = document.getElementById("lottype2").value;
		var lotsearch2 = document.getElementById("lotsearch2").value;
		var lottypevalue2 = document.getElementById("lottypevalue2").value;
		
		var attname3 = document.getElementById("attname3").value;
		var attcode3 = document.getElementById("attcode3").value;
		var lotatt_flag3 = document.getElementById("lotatt_flag3").value;
		var lottype3 = document.getElementById("lottype3").value;
		var lotsearch3 = document.getElementById("lotsearch3").value;
		var lottypevalue3 = document.getElementById("lottypevalue3").value;
		
		var attname4 = document.getElementById("attname4").value;
		var attcode4 = document.getElementById("attcode4").value;
		var lotatt_flag4 = document.getElementById("lotatt_flag4").value;
		var lottype4 = document.getElementById("lottype4").value;
		var lotsearch4 = document.getElementById("lotsearch4").value;
		var lottypevalue4 = document.getElementById("lottypevalue4").value;
		
		var attname5 = document.getElementById("attname5").value;
		var attcode5 = document.getElementById("attcode5").value;
		var lotatt_flag5 = document.getElementById("lotatt_flag5").value;
		var lottype5 = document.getElementById("lottype5").value;
		var lotsearch5 = document.getElementById("lotsearch5").value;
		var lottypevalue5 = document.getElementById("lottypevalue5").value;
		
		var attname6 = document.getElementById("attname6").value;
		var attcode6 = document.getElementById("attcode6").value;
		var lotatt_flag6 = document.getElementById("lotatt_flag6").value;
		var lottype6 = document.getElementById("lottype6").value;
		var lotsearch6 = document.getElementById("lotsearch6").value;
		var lottypevalue6 = document.getElementById("lottypevalue6").value;
		
		var attname7 = document.getElementById("attname7").value;
		var attcode7 = document.getElementById("attcode7").value;
		var lotatt_flag7 = document.getElementById("lotatt_flag7").value;
		var lottype7 = document.getElementById("lottype7").value;
		var lotsearch7 = document.getElementById("lotsearch7").value;
		var lottypevalue7 = document.getElementById("lottypevalue7").value;
		
		var attname8 = document.getElementById("attname8").value;
		var attcode8 = document.getElementById("attcode8").value;
		var lotatt_flag8 = document.getElementById("lotatt_flag8").value;
		var lottype8 = document.getElementById("lottype8").value;
		var lotsearch8 = document.getElementById("lotsearch8").value;
		var lottypevalue8 = document.getElementById("lottypevalue8").value;
		
		var attname9 = document.getElementById("attname9").value;
		var attcode9 = document.getElementById("attcode9").value;
		var lotatt_flag9 = document.getElementById("lotatt_flag9").value;
		var lottype9 = document.getElementById("lottype9").value;
		var lotsearch9 = document.getElementById("lotsearch9").value;
		var lottypevalue9 = document.getElementById("lottypevalue9").value;
		
		var attname10 = document.getElementById("attname10").value;
		var attcode10 = document.getElementById("attcode10").value;
		var lotatt_flag10 = document.getElementById("lotatt_flag10").value;
		var lottype10 = document.getElementById("lottype10").value;
		var lotsearch10 = document.getElementById("lotsearch10").value;
		var lottypevalue10 = document.getElementById("lottypevalue10").value;
		
		var attname11 = document.getElementById("attname11").value;
		var attcode11 = document.getElementById("attcode11").value;
		var lotatt_flag11 = document.getElementById("lotatt_flag11").value;
		var lottype11 = document.getElementById("lottype11").value;
		var lotsearch11 = document.getElementById("lotsearch11").value;
		var lottypevalue11 = document.getElementById("lottypevalue11").value;
		
		var attname12 = document.getElementById("attname12").value;
		var attcode12 = document.getElementById("attcode12").value;
		var lotatt_flag12 = document.getElementById("lotatt_flag12").value;
		var lottype12 = document.getElementById("lottype12").value;
		var lotsearch12 = document.getElementById("lotsearch12").value;
		var lottypevalue12 = document.getElementById("lottypevalue12").value;

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
		
		if((lottype1==1 && lotsearch1==2) || (lottype2==1 && lotsearch2==2) || (lottype3==1 && lotsearch3==2)
			 || (lottype3==1 && lotsearch3==2) || (lottype5==1 && lotsearch5==2) || (lottype6==1 && lotsearch6==2)
			 || (lottype7==1 && lotsearch7==2) || (lottype8==1 && lotsearch8==2) || (lottype9==1 && lotsearch9==2)
			 || (lottype10==1 && lotsearch10==2) || (lottype11==1 && lotsearch11==2) || (lottype12==1 && lotsearch12==2)){
		
			alert("【属性格式】是日期的时候，\n【属性查询方式】不能选择模糊查询！");
			return;
		}

		condition = "&descr=" + descr + "&remark=" + remark 
				 
			 + "&attname1=" + attname1 + "&attcode1=" + attcode1 + "&lotatt_flag1=" + lotatt_flag1 + "&lottype1=" + lottype1 
			 + "&lotsearch1=" + lotsearch1 + "&lottypevalue1=" + lottypevalue1
			 
			 + "&attname2=" + attname2 + "&attcode2=" + attcode2 + "&lotatt_flag2=" + lotatt_flag2 + "&lottype2=" + lottype2 
			 + "&lotsearch2=" + lotsearch2 + "&lottypevalue2=" + lottypevalue2
			 
			 + "&attname3=" + attname3 + "&attcode3=" + attcode3 + "&lotatt_flag3=" + lotatt_flag3 + "&lottype3=" + lottype3 
			 + "&lotsearch3=" + lotsearch3 + "&lottypevalue3=" + lottypevalue3
			 
			 + "&attname4=" + attname4 + "&attcode4=" + attcode4 + "&lotatt_flag4=" + lotatt_flag4 + "&lottype4=" + lottype4 
			 + "&lotsearch4=" + lotsearch4 + "&lottypevalue4=" + lottypevalue4
			 
			 + "&attname5=" + attname5 + "&attcode5=" + attcode5 + "&lotatt_flag5=" + lotatt_flag5 + "&lottype5=" + lottype5 
			 + "&lotsearch5=" + lotsearch5 + "&lottypevalue5=" + lottypevalue5
			 
			 + "&attname6=" + attname6 + "&attcode6=" + attcode6 + "&lotatt_flag6=" + lotatt_flag6 + "&lottype6=" + lottype6 
			 + "&lotsearch6=" + lotsearch6 + "&lottypevalue6=" + lottypevalue6
			 
			 + "&attname7=" + attname7 + "&attcode7=" + attcode7 + "&lotatt_flag7=" + lotatt_flag7 + "&lottype7=" + lottype7 
			 + "&lotsearch7=" + lotsearch7 + "&lottypevalue7=" + lottypevalue7
			 
			 + "&attname8=" + attname8 + "&attcode8=" + attcode8 + "&lotatt_flag8=" + lotatt_flag8 + "&lottype8=" + lottype8 
			 + "&lotsearch8=" + lotsearch8 + "&lottypevalue8=" + lottypevalue8
			 
			 + "&attname9=" + attname9 + "&attcode9=" + attcode9 + "&lotatt_flag9=" + lotatt_flag9 + "&lottype9=" + lottype9 
			 + "&lotsearch9=" + lotsearch9 + "&lottypevalue9=" + lottypevalue9
			 
			 + "&attname10=" + attname10 + "&attcode10=" + attcode10 + "&lotatt_flag10=" + lotatt_flag10 + "&lottype10=" 
			 + lottype10 + "&lotsearch10=" + lotsearch10 + "&lottypevalue10=" + lottypevalue10
			 
			 + "&attname11=" + attname11 + "&attcode11=" + attcode11 + "&lotatt_flag11=" + lotatt_flag11 + "&lottype11=" 
			 + lottype11 + "&lotsearch11=" + lotsearch11 + "&lottypevalue11=" + lottypevalue11 
			 
			 + "&attname12=" + attname12 + "&attcode12=" + attcode12 + "&lotatt_flag12=" + lotatt_flag12 + "&lottype12=" 
			 + lottype12 + "&lotsearch12=" + lotsearch12 + "&lottypevalue12=" + lottypevalue12;
	
		window.returnValue = condition;
		window.close();
	}
	
	
	//登陆页面
	function OnLoad(){
		//同步
		DWREngine.setAsync(false);
		selectTypes('2', 'lotatt_flag', 'srkz', 12);
		selectTypes('2', 'lottype', 'sxgs', 12);
		selectTypes('1', 'lotsearch', 'sxcxfs', 12);
		DWREngine.setAsync(true);
		
		document.getElementById("lotatt_flag1").value = "1";
		document.getElementById("lotatt_flag2").value = "1";
		document.getElementById("lotatt_flag3").value = "1";
		
		document.getElementById("lottype1").value = "1";
	}
-->
</script>
</head>

<body onLoad="OnLoad();">
<form name="myForm" method="post" action="">
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：批次属性 -&gt; 增加批次属性</div></td>
   </tr>
 </table>
 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td class="yx1" width="100px" align="right"><span class="red">*</span>批次属性描述：</td>
     <td class="xx1"><input type="text" id="descr" size="30" maxlength="200"></td>
   </tr>
   <tr>
     <td class="y2" align="right">备注：</td>
     <td><textarea id="remark" cols="83" rows="3"></textarea></td>
   </tr>
 </table>
 
 <table><tr><td height="10"></td></tr></table> 
 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td class="yx1" align="center">属性名称</td>
     <td class="yx1" align="center">属性代码</td>
     <td class="yx1" align="center">输入控制</td>
     <td class="yx1" align="center">属性格式</td>
     <td class="yx1" align="center">属性格式值</td>
     <td class="x1" align="center">属性查询方式</td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="attname1" size="20" maxlength="20" value="生产日期" style="background-color: #EDDFFD;color: #001F56;border: 1px solid #99CCFF;" readonly></td>
     <td class="yx" align="center"><input type="text" id="attcode1" size="15" maxlength="20" value="lotatt1" disabled></td>
     <td class="yx" align="center"><select id="lotatt_flag1"  style="width:120px;"><option></option></select></td>
     <td class="yx" align="center"><select id="lottype1"  style="background-color: #EDDFFD;width:120px;" disabled><option></option></select></td>
     <td class="yx" align="center"><input type="text" id="lottypevalue1" size="15" maxlength="20" value=""></td>
     <td class="xx1" align="center"><select id="lotsearch1" style="width:120px;"><option></option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="attname2" size="20" maxlength="20" value="批次属性2"></td>
     <td class="yx" align="center"><input type="text" id="attcode2" size="15" maxlength="20" value="lotatt2" disabled></td>
     <td class="yx" align="center"><select id="lotatt_flag2"  style="width:120px;"><option></option></select></td>
     <td class="yx" align="center"><select id="lottype2"  style="width:120px;"><option></option></select></td>
     <td class="yx" align="center"><input type="text" id="lottypevalue2" size="15" maxlength="20" value=""></td>
     <td class="xx1" align="center"><select id="lotsearch2" style="width:120px;"><option></option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="attname3" size="20" maxlength="20" value="批次属性3"></td>
     <td class="yx" align="center"><input type="text" id="attcode3" size="15" maxlength="20" value="lotatt3" disabled></td>
     <td class="yx" align="center"><select id="lotatt_flag3"  style="width:120px;"><option></option></select></td>
     <td class="yx" align="center"><select id="lottype3"  style="width:120px;"><option></option></select></td>
     <td class="yx" align="center"><input type="text" id="lottypevalue3" size="15" maxlength="20" value=""></td>
     <td class="xx1" align="center"><select id="lotsearch3" style="width:120px;"><option></option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="attname4" size="20" maxlength="20" value="批次属性4"></td>
     <td class="yx" align="center"><input type="text" id="attcode4" size="15" maxlength="20" value="lotatt4" disabled></td>
     <td class="yx" align="center"><select id="lotatt_flag4" style="width:120px;"><option></option></select></td>
     <td class="yx" align="center"><select id="lottype4" style="width:120px;"><option></option></select></td>
     <td class="yx" align="center"><input type="text" id="lottypevalue4" size="15" maxlength="20" value=""></td>
     <td class="xx1" align="center"><select id="lotsearch4" style="width:120px;"><option></option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="attname5" size="20" maxlength="20" value="批次属性5"></td>
     <td class="yx" align="center"><input type="text" id="attcode5" size="15" maxlength="20" value="lotatt5" disabled></td>
     <td class="yx" align="center"><select id="lotatt_flag5" style="width:120px;"><option></option></select></td>
     <td class="yx" align="center"><select id="lottype5" style="width:120px;"><option></option></select></td>
     <td class="yx" align="center"><input type="text" id="lottypevalue5" size="15" maxlength="20" value=""></td>
     <td class="xx1" align="center"><select id="lotsearch5" style="width:120px;"><option></option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="attname6" size="20" maxlength="20" value="批次属性6"></td>
     <td class="yx" align="center"><input type="text" id="attcode6" size="15" maxlength="20" value="lotatt6" disabled></td>
     <td class="yx" align="center"><select id="lotatt_flag6" style="width:120px;"><option></option></select></td>
     <td class="yx" align="center"><select id="lottype6" style="width:120px;"><option></option></select></td>
     <td class="yx" align="center"><input type="text" id="lottypevalue6" size="15" maxlength="20" value=""></td>
     <td class="xx1" align="center"><select id="lotsearch6" style="width:120px;"><option></option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="attname7" size="20" maxlength="20" value="批次属性7"></td>
     <td class="yx" align="center"><input type="text" id="attcode7" size="15" maxlength="20" value="lotatt7" disabled></td>
     <td class="yx" align="center"><select id="lotatt_flag7" style="width:120px;"><option></option></select></td>
     <td class="yx" align="center"><select id="lottype7" style="width:120px;"><option></option></select></td>
     <td class="yx" align="center"><input type="text" id="lottypevalue7" size="15" maxlength="20" value=""></td>
     <td class="xx1" align="center"><select id="lotsearch7" style="width:120px;"><option></option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="attname8" size="20" maxlength="20" value="批次属性8"></td>
     <td class="yx" align="center"><input type="text" id="attcode8" size="15" maxlength="20" value="lotatt8" disabled></td>
     <td class="yx" align="center"><select id="lotatt_flag8" style="width:120px;"><option></option></select></td>
     <td class="yx" align="center"><select id="lottype8" style="width:120px;"><option></option></select></td>
     <td class="yx" align="center"><input type="text" id="lottypevalue8" size="15" maxlength="20" value=""></td>
     <td class="xx1" align="center"><select id="lotsearch8" style="width:120px;"><option></option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="attname9" size="20" maxlength="20" value="批次属性9"></td>
     <td class="yx" align="center"><input type="text" id="attcode9" size="15" maxlength="20" value="lotatt9" disabled></td>
     <td class="yx" align="center"><select id="lotatt_flag9" style="width:120px;"><option></option></select></td>
     <td class="yx" align="center"><select id="lottype9" style="width:120px;"><option></option></select></td>
     <td class="yx" align="center"><input type="text" id="lottypevalue9" size="15" maxlength="20" value=""></td>
     <td class="xx1" align="center"><select id="lotsearch9" style="width:120px;"><option></option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="attname10" size="20" maxlength="20" value="批次属性10"></td>
     <td class="yx" align="center"><input type="text" id="attcode10" size="15" maxlength="20" value="lotatt10" disabled></td>
     <td class="yx" align="center"><select id="lotatt_flag10" style="width:120px;"><option></option></select></td>
     <td class="yx" align="center"><select id="lottype10" style="width:120px;"><option></option></select></td>
     <td class="yx" align="center"><input type="text" id="lottypevalue10" size="15" maxlength="20" value=""></td>
     <td class="xx1" align="center"><select id="lotsearch10" style="width:120px;"><option></option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="text" id="attname11" size="20" maxlength="20" value="批次属性11"></td>
     <td class="yx" align="center"><input type="text" id="attcode11" size="15" maxlength="20" value="lotatt11" disabled></td>
     <td class="yx" align="center"><select id="lotatt_flag11" style="width:120px;"><option></option></select></td>
     <td class="yx" align="center"><select id="lottype11" style="width:120px;"><option></option></select></td>
     <td class="yx" align="center"><input type="text" id="lottypevalue11" size="15" maxlength="20" value=""></td>
     <td class="xx1" align="center"><select id="lotsearch11" style="width:120px;"><option></option></select></td>
   </tr>
   <tr>
     <td class="x" align="center"><input type="text" id="attname12" size="20" maxlength="20" value="批次属性12"></td>
     <td class="x" align="center"><input type="text" id="attcode12" size="15" maxlength="20" value="lotatt12" disabled></td>
     <td class="x" align="center"><select id="lotatt_flag12" style="width:120px;"><option></option></select></td>
     <td class="x" align="center"><select id="lottype12" style="width:120px;"><option></option></select></td>
     <td class="x" align="center"><input type="text" id="lottypevalue12" size="15" maxlength="20" value=""></td>
     <td align="center"><select id="lotsearch12" style="width:120px;"><option></option></select></td>
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