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
	
	//保存
	function Save(){
	
		/*包装*/
		var pkgdesc = document.getElementById("pkgdesc").value;
		var remark = document.getElementById("remark").value;
		
		/*包装主信息*/
		var qty1 = document.getElementById("qty1").value;
		var descr1 = document.getElementById("descr1").value;
		var in_label1 = document.getElementById("in_label1").checked;
		var out_label1 = document.getElementById("out_label1").checked;
		var loc_usage1 = document.getElementById("loc_usage1").value;
		
		var qty2 = document.getElementById("qty2").value;
		var descr2 = document.getElementById("descr2").value;
		var in_label2 = document.getElementById("in_label2").checked;
		var out_label2 = document.getElementById("out_label2").checked;
		var loc_usage2 = document.getElementById("loc_usage2").value;
		
		var qty3 = document.getElementById("qty3").value;
		var descr3 = document.getElementById("descr3").value;
		var in_label3 = document.getElementById("in_label3").checked;
		var out_label3 = document.getElementById("out_label3").checked;
		var loc_usage3 = document.getElementById("loc_usage3").value;
		
		var qty4 = document.getElementById("qty4").value;
		var descr4 = document.getElementById("descr4").value;
		var in_label4 = document.getElementById("in_label4").checked;
		var out_label4 = document.getElementById("out_label4").checked;
		var loc_usage4 = document.getElementById("loc_usage4").value;
		
		var qty5 = document.getElementById("qty5").value;
		var descr5 = document.getElementById("descr5").value;
		var in_label5 = document.getElementById("in_label5").checked;
		var out_label5 = document.getElementById("out_label5").checked;
		var loc_usage5 = document.getElementById("loc_usage5").value;
		
		/*包装附加信息*/
		var lengthuom1 = document.getElementById("lengthuom1").value;
		var widthuom1 = document.getElementById("widthuom1").value;
		var heightuom1 = document.getElementById("heightuom1").value;
		var cubeuom1 = document.getElementById("cubeuom1").value;
		var weightuom1 = document.getElementById("weightuom1").value;
		
		var lengthuom2 = document.getElementById("lengthuom2").value;
		var widthuom2 = document.getElementById("widthuom2").value;
		var heightuom2 = document.getElementById("heightuom2").value;
		var cubeuom2 = document.getElementById("cubeuom2").value;
		var weightuom2 = document.getElementById("weightuom2").value;
		
		var lengthuom3 = document.getElementById("lengthuom3").value;
		var widthuom3 = document.getElementById("widthuom3").value;
		var heightuom3 = document.getElementById("heightuom3").value;
		var cubeuom3 = document.getElementById("cubeuom3").value;
		var weightuom3 = document.getElementById("weightuom3").value;
		
		var lengthuom4 = document.getElementById("lengthuom4").value;
		var widthuom4 = document.getElementById("widthuom4").value;
		var heightuom4 = document.getElementById("heightuom4").value;
		var cubeuom4 = document.getElementById("cubeuom4").value;
		var weightuom4 = document.getElementById("weightuom4").value;
		var palletwoodlength = document.getElementById("palletwoodlength").value;
		var palletwoodwidth = document.getElementById("palletwoodwidth").value;
		var palletwoodheight = document.getElementById("palletwoodheight").value;
		var palletti = document.getElementById("palletti").value;
		var pallethi = document.getElementById("pallethi").value;
		
		var lengthuom5 = document.getElementById("lengthuom5").value;
		var widthuom5 = document.getElementById("widthuom5").value;
		var heightuom5 = document.getElementById("heightuom5").value;
		var cubeuom5 = document.getElementById("cubeuom5").value;
		var weightuom5 = document.getElementById("weightuom5").value;
		
		/*包装*/
		if(pkgdesc == null || pkgdesc.length < 1)
		{
			alert("【包装描述】不能为空！");
			return;
		}
		if(strlen(pkgdesc) > 100)
		{
			alert("【包装描述】过长！");
			return;
		}
		
		if(remark != null && remark.length > 0)
		{
			if(strlen(remark) > 100)
			{
				alert("【备注】过长！");
				return;
			}
		}
		
		/*包装主信息*/
		if(qty1 != null && qty1.length > 0 && qty1 != 0){

			if(!numChar(qty1)){
				alert("【主单位 数量】只能为正整数或0！");
				return;
			}
		}
		if(descr1 != null && descr1.length > 0){
		
			if(strlen(descr1) > 50){
				alert("【主单位 描述】过长！");
				return;
			}
		}
		
		if(qty2 != null && qty2.length > 0 && qty2 != 0){

			if(!numChar(qty2)){
				alert("【内包装 数量】只能为正整数或0！");
				return;
			}
		}
		if(descr2 != null && descr2.length > 0){
		
			if(strlen(descr2) > 50){
				alert("【内包装 描述】过长！");
				return;
			}
		}
		
		if(qty3 != null && qty3.length > 0 && qty3 != 0){

			if(!numChar(qty3)){
				alert("【箱 数量】只能为正整数或0！");
				return;
			}
		}
		if(descr3 != null && descr3.length > 0){
		
			if(strlen(descr3) > 50){
				alert("【箱 描述】过长！");
				return;
			}
		}

		if(qty4 != null && qty4.length > 0 && qty4 != 0){

			if(!numChar(qty4)){
				alert("【托盘 数量】只能为正整数或0！");
				return;
			}
		}
		if(descr4 != null && descr4.length > 0){
		
			if(strlen(descr4) > 50){
				alert("【托盘 描述】过长！");
				return;
			}
		}

		if(qty5 != null && qty5.length > 0 && qty5 != 0){

			if(!numChar(qty5)){
				alert("【其它 数量】只能为正整数或0！");
				return;
			}
		}
		if(descr5 != null && descr5.length > 0){
		
			if(strlen(descr5) > 50){
				alert("【其它 描述】过长！");
				return;
			}
		}

		/*包装附加信息*/
		if(lengthuom1 != null && lengthuom1.length > 0 && lengthuom1 != 0){
		
			if(!isDig(lengthuom1)){
				alert("【主单位 长】只能为正浮点数或0！");
				return;
			}
		}
		if(widthuom1 != null && widthuom1.length > 0 && widthuom1 != 0){
		
			if(!isDig(widthuom1)){
				alert("【主单位 宽】只能为正浮点数或0！");
				return;
			}
		}
		if(heightuom1 != null && heightuom1.length > 0 && heightuom1 != 0){
		
			if(!isDig(heightuom1)){
				alert("【主单位 高】只能为正浮点数或0！");
				return;
			}
		}
		if(cubeuom1 != null && cubeuom1.length > 0 && cubeuom1 != 0){
		
			if(!isDig(cubeuom1)){
				alert("【主单位 体积】只能为正浮点数或0！");
				return;
			}
		}
		if(weightuom1 != null && weightuom1.length > 0 && weightuom1 != 0){
		
			if(!isDig(weightuom1)){
				alert("【主单位 重量】只能为正浮点数或0！");
				return;
			}
		}
		
		if(lengthuom2 != null && lengthuom2.length > 0 && lengthuom2 != 0){
		
			if(!isDig(lengthuom2)){
				alert("【内包装 长】只能为正浮点数或0！");
				return;
			}
		}
		if(widthuom2 != null && widthuom2.length > 0 && widthuom2 != 0){
		
			if(!isDig(widthuom2)){
				alert("【内包装 宽】只能为正浮点数或0！");
				return;
			}
		}
		if(heightuom2 != null && heightuom2.length > 0 && heightuom2 != 0){
		
			if(!isDig(heightuom2)){
				alert("【内包装 高】只能为正浮点数或0！");
				return;
			}
		}
		if(cubeuom2 != null && cubeuom2.length > 0 && cubeuom2 != 0){
		
			if(!isDig(cubeuom2)){
				alert("【内包装 体积】只能为正浮点数或0！");
				return;
			}
		}
		if(weightuom2 != null && weightuom2.length > 0 && weightuom2 != 0){
		
			if(!isDig(weightuom2)){
				alert("【内包装 重量】只能为正浮点数或0！");
				return;
			}
		}
		
		if(lengthuom3 != null && lengthuom3.length > 0 && lengthuom3 != 0){
		
			if(!isDig(lengthuom3)){
				alert("【箱 长】只能为正浮点数或0！");
				return;
			}
		}
		if(widthuom3 != null && widthuom3.length > 0 && widthuom3 != 0){
		
			if(!isDig(widthuom3)){
				alert("【箱 宽】只能为正浮点数或0！");
				return;
			}
		}
		if(heightuom3 != null && heightuom3.length > 0 && heightuom3 != 0){
		
			if(!isDig(heightuom3)){
				alert("【箱 高】只能为正浮点数或0！");
				return;
			}
		}
		if(cubeuom3 != null && cubeuom3.length > 0 && cubeuom3 != 0){
		
			if(!isDig(cubeuom3)){
				alert("【箱 体积】只能为正浮点数或0！");
				return;
			}
		}
		if(weightuom3 != null && weightuom3.length > 0 && weightuom3 != 0){
		
			if(!isDig(weightuom3)){
				alert("【箱 重量】只能为正浮点数或0！");
				return;
			}
		}

		if(lengthuom4 != null && lengthuom4.length > 0 && lengthuom4 != 0){
		
			if(!isDig(lengthuom4)){
				alert("【托盘 长】只能为正浮点数或0！");
				return;
			}
		}
		if(widthuom4 != null && widthuom4.length > 0 && widthuom4 != 0){
		
			if(!isDig(widthuom4)){
				alert("【托盘 宽】只能为正浮点数或0！");
				return;
			}
		}
		if(heightuom4 != null && heightuom4.length > 0 && heightuom4 != 0){
		
			if(!isDig(heightuom4)){
				alert("【托盘 高】只能为正浮点数或0！");
				return;
			}
		}
		if(cubeuom4 != null && cubeuom4.length > 0 && cubeuom4 != 0){
		
			if(!isDig(cubeuom4)){
				alert("【托盘 体积】只能为正浮点数或0！");
				return;
			}
		}
		if(weightuom4 != null && weightuom4.length > 0 && weightuom4 != 0){
		
			if(!isDig(weightuom4)){
				alert("【托盘 重量】只能为正浮点数或0！");
				return;
			}
		}
		if(palletwoodlength != null && palletwoodlength.length > 0 && palletwoodlength != 0){
		
			if(!isDig(palletwoodlength)){
				alert("【托盘 长2】只能为正浮点数或0！");
				return;
			}
		}
		if(palletwoodwidth != null && palletwoodwidth.length > 0 && palletwoodwidth != 0){
		
			if(!isDig(palletwoodwidth)){
				alert("【托盘 宽2】只能为正浮点数或0！");
				return;
			}
		}
		if(palletwoodheight != null && palletwoodheight.length > 0 && palletwoodheight != 0){
		
			if(!isDig(palletwoodheight)){
				alert("【托盘 高2】只能为正浮点数或0！");
				return;
			}
		}
		if(palletti != null && palletti.length > 0 && palletti != 0){
		
			if(!isDig(palletti)){
				alert("【托盘 TI】只能为正浮点数或0！");
				return;
			}
		}
		if(pallethi != null && pallethi.length > 0 && pallethi != 0){
		
			if(!isDig(pallethi)){
				alert("【托盘 HI】只能为正浮点数或0！");
				return;
			}
		}

		if(lengthuom5 != null && lengthuom5.length > 0 && lengthuom5 != 0){
		
			if(!isDig(lengthuom5)){
				alert("【其它 长】只能为正浮点数或0！");
				return;
			}
		}
		if(widthuom5 != null && widthuom5.length > 0 && widthuom5 != 0){
		
			if(!isDig(widthuom5)){
				alert("【其它 宽】只能为正浮点数或0！");
				return;
			}
		}
		if(heightuom5 != null && heightuom5.length > 0 && heightuom5 != 0){
		
			if(!isDig(heightuom5)){
				alert("【其它 高】只能为正浮点数或0！");
				return;
			}
		}
		if(cubeuom5 != null && cubeuom5.length > 0 && cubeuom5 != 0){
		
			if(!isDig(cubeuom5)){
				alert("【其它 体积】只能为正浮点数或0！");
				return;
			}
		}
		if(weightuom5 != null && weightuom5.length > 0 && weightuom5 != 0){
		
			if(!isDig(weightuom5)){
				alert("【其它 重量】只能为正浮点数或0！");
				return;
			}
		}

		condition = "&pkgdesc=" + pkgdesc + "&remark=" + remark 
				 
				 + "&qty1=" + qty1 + "&descr1=" + descr1 + "&in_label1=" + in_label1 + "&out_label1=" + out_label1 
				 + "&qty2=" + qty2 + "&descr2=" + descr2 + "&in_label2=" + in_label2 + "&out_label2=" + out_label2 
				 + "&qty3=" + qty3 + "&descr3=" + descr3 + "&in_label3=" + in_label3 + "&out_label3=" + out_label3 
				 + "&qty4=" + qty4 + "&descr4=" + descr4 + "&in_label4=" + in_label4 + "&out_label4=" + out_label4 
				 + "&qty5=" + qty5 + "&descr5=" + descr5 + "&in_label5=" + in_label5 + "&out_label5=" + out_label5
				 
				 + "&lengthuom1=" + lengthuom1 + "&widthuom1=" + widthuom1 + "&heightuom1=" + heightuom1 + "&cubeuom1=" + cubeuom1 + "&weightuom1=" + weightuom1
				 + "&lengthuom2=" + lengthuom2 + "&widthuom2=" + widthuom2 + "&heightuom2=" + heightuom2 + "&cubeuom2=" + cubeuom2 + "&weightuom2=" + weightuom2
				 + "&lengthuom3=" + lengthuom3 + "&widthuom3=" + widthuom3 + "&heightuom3=" + heightuom3 + "&cubeuom3=" + cubeuom3 + "&weightuom3=" + weightuom3
				 + "&lengthuom4=" + lengthuom4 + "&widthuom4=" + widthuom4 + "&heightuom4=" + heightuom4 + "&cubeuom4=" + cubeuom4 + "&weightuom4=" + weightuom4
				 + "&palletwoodlength=" + palletwoodlength + "&palletwoodwidth=" + palletwoodwidth + "&palletwoodheight=" + palletwoodheight 
				 + "&palletti=" + palletti + "&pallethi=" + pallethi
				 + "&lengthuom5=" + lengthuom5 + "&widthuom5=" + widthuom5 + "&heightuom5=" + heightuom5 + "&cubeuom5=" + cubeuom5 + "&weightuom5=" + weightuom5
				 + "&loc_usage1=" + loc_usage1 + "&loc_usage2=" + loc_usage2 + "&loc_usage3=" + loc_usage3 + "&loc_usage4=" + loc_usage4 + "&loc_usage5=" + loc_usage5; 
	
		window.returnValue = condition;
		window.close();
	}
	
	//页面登陆
	function OnLoad(){
		
		selectTypes("", 'loc_usage', "kwsy", 5);	//库位使用
	}
-->
</script>
</head>

<body onLoad="OnLoad();">
<form name="myForm" method="post" action="">
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：包装 -&gt; 增加包装</div></td>
   </tr>
 </table>
 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td class="yx1" width="100px" align="right"><span class="red">*</span>包装描述：</td>
     <td class="xx1"><input type="text" id="pkgdesc" size="50" maxlength="100"></td>
   </tr>
   <tr>
     <td class="y2" align="right">备注：</td>
     <td><textarea id="remark" cols="83" rows="3"></textarea></td>
   </tr>
 </table>
 
 <table><tr><td height="10"></td></tr></table> 
 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">  
   <tr>
     <td class="yx1" align="center" width="80px"></td>
     <td class="yx1" align="center">数量</td>
     <td class="yx1" align="center">描述</td>
     <td class="yx1" align="center">入库启用</td>
     <td class="yx1" align="center">出库启用</td>
     <td class="x1" align="center">库位使用</td>
   </tr>
   <tr>
     <td class="yx" align="center">主单位</td>
     <td class="yx" align="center"><input type="text" id="qty1" size="15" maxlength="10" value="1" readonly></td>
     <td class="yx" align="center"><input type="text" id="descr1" size="15" maxlength="20" value="件"></td>
     <td class="yx" align="center"><input type="checkbox" id="in_label1" class="input_checkbox"></td>
     <td class="yx" align="center"><input type="checkbox" id="out_label1" class="input_checkbox"></td>
     <td class="xx1" align="center"><select id="loc_usage1"><option>----- 请选择 ----</option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center">内包装</td>
     <td class="yx" align="center"><input type="text" id="qty2" size="15" maxlength="10" value="0"></td>
     <td class="yx" align="center"><input type="text" id="descr2" size="15" maxlength="20" value="中包"></td>
     <td class="yx" align="center"><input type="checkbox" id="in_label2" class="input_checkbox"></td>
     <td class="yx" align="center"><input type="checkbox" id="out_label2" class="input_checkbox"></td>
     <td class="xx1" align="center"><select id="loc_usage2"><option>----- 请选择 ----</option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center">箱</td>
     <td class="yx" align="center"><input type="text" id="qty3" size="15" maxlength="10" value="0"></td>
     <td class="yx" align="center"><input type="text" id="descr3" size="15" maxlength="20" value="箱"></td>
     <td class="yx" align="center"><input type="checkbox" id="in_label3" class="input_checkbox"></td>
     <td class="yx" align="center"><input type="checkbox" id="out_label3" class="input_checkbox"></td>
     <td class="xx1" align="center"><select id="loc_usage3"><option>----- 请选择 ----</option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center">托盘</td>
     <td class="yx" align="center"><input type="text" id="qty4" size="15" maxlength="10" value="0"></td>
     <td class="yx" align="center"><input type="text" id="descr4" size="15" maxlength="20" value="托盘"></td>
     <td class="yx" align="center"><input type="checkbox" id="in_label4" class="input_checkbox"></td>
     <td class="yx" align="center"><input type="checkbox" id="out_label4" class="input_checkbox"></td>
     <td class="xx1" align="center"><select id="loc_usage4"><option>----- 请选择 ----</option></select></td>
   </tr>
   <tr>
     <td class="x" align="center">其它</td>
     <td class="x" align="center"><input type="text" id="qty5" size="15" maxlength="10" value="0"></td>
     <td class="x" align="center"><input type="text" id="descr5" size="15" maxlength="20" value="OT"></td>
     <td class="x" align="center"><input type="checkbox" id="in_label5" class="input_checkbox"></td>
     <td class="x" align="center"><input type="checkbox" id="out_label5" class="input_checkbox"></td>
     <td align="center"><select id="loc_usage5"><option>----- 请选择 ----</option></select></td>
   </tr>
 </table>
 
 <table><tr><td height="10"></td></tr></table> 
 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td class="yx1" align="center" width="80px"></td>
     <td class="yx1" align="center">长</td>
     <td class="yx1" align="center">宽</td>
     <td class="yx1" align="center">高</td>
     <td class="yx1" align="center">体积</td>
     <td class="yx1" align="center">重量</td>
     <td class="x1" align="center">TI*HI</td>
   </tr>
   <tr>
     <td class="yx" align="center">主单位</td>
     <td class="yx" align="center"><input type="text" id="lengthuom1" size="15" maxlength="10" value="0"></td>
     <td class="yx" align="center"><input type="text" id="widthuom1" size="15" maxlength="10" value="0"></td>
     <td class="yx" align="center"><input type="text" id="heightuom1" size="15" maxlength="10" value="0"></td>
     <td class="yx" align="center"><input type="text" id="cubeuom1"  size="15" maxlength="10" value="0"></td>
     <td class="yx" align="center"><input type="text" id="weightuom1" size="15" maxlength="10" value="0"></td>
     <td class="xx1" align="center">&nbsp;</td>
   </tr>
   <tr>
     <td class="yx" align="center">内包装</td>
     <td class="yx" align="center"><input type="text" id="lengthuom2" size="15" maxlength="10" value="0"></td>
     <td class="yx" align="center"><input type="text" id="widthuom2" size="15" maxlength="10" value="0"></td>
     <td class="yx" align="center"><input type="text" id="heightuom2" size="15" maxlength="10" value="0"></td>
     <td class="yx" align="center"><input type="text" id="cubeuom2"  size="15" maxlength="10" value="0"></td>
     <td class="yx" align="center"><input type="text" id="weightuom2" size="15" maxlength="10" value="0"></td>
     <td class="xx1" align="center">&nbsp;</td>
   </tr>
   <tr>
     <td class="yx" align="center">箱</td>
     <td class="yx" align="center"><input type="text" id="lengthuom3" size="15" maxlength="10" value="0"></td>
     <td class="yx" align="center"><input type="text" id="widthuom3" size="15" maxlength="10" value="0"></td>
     <td class="yx" align="center"><input type="text" id="heightuom3" size="15" maxlength="10" value="0"></td>
     <td class="yx" align="center"><input type="text" id="cubeuom3"  size="15" maxlength="10" value="0"></td>
     <td class="yx" align="center"><input type="text" id="weightuom3" size="15" maxlength="10" value="0"></td>
     <td class="xx1" align="center">&nbsp;</td>
   </tr>
   <tr>
     <td class="x" align="center">托盘</td>
     <td class="yx" align="center"><input type="text" id="lengthuom4" size="15" maxlength="10" value="0"></td>
     <td class="yx" align="center"><input type="text" id="widthuom4" size="15" maxlength="10" value="0"></td>
     <td class="yx" align="center"><input type="text" id="heightuom4" size="15" maxlength="10" value="0"></td>
     <td class="yx" align="center"><input type="text" id="cubeuom4"  size="15" maxlength="10" value="0"></td>
     <td class="yx" align="center"><input type="text" id="weightuom4" size="15" maxlength="10" value="0"></td>
     <td class="xx1" align="center"><input type="text" id="palletti" size="5" maxlength="6" value="0">*<input type="text" id="pallethi" size="5" maxlength="6" value="0"></td>
   </tr>
   <tr>
     <td class="yx" align="center">&nbsp;</td>
     <td class="yx" align="center"><input type="text" id="palletwoodlength" size="15" maxlength="10" value="0"></td>
     <td class="yx" align="center"><input type="text" id="palletwoodwidth" size="15" maxlength="10" value="0"></td>
     <td class="yx" align="center"><input type="text" id="palletwoodheight" size="15" maxlength="10" value="0"></td>
     <td class="yx" align="center">&nbsp;</td>
     <td class="yx" align="center">&nbsp;</td>
     <td class="xx1" align="center">&nbsp;</td>
   </tr>
   <tr>
     <td class="x" align="center">其它</td>
     <td class="x" align="center"><input type="text" id="lengthuom5" size="15" maxlength="10" value="0"></td>
     <td class="x" align="center"><input type="text" id="widthuom5" size="15" maxlength="10" value="0"></td>
     <td class="x" align="center"><input type="text" id="heightuom5" size="15" maxlength="10" value="0"></td>
     <td class="x" align="center"><input type="text" id="cubeuom5"  size="15" maxlength="10" value="0"></td>
     <td class="x" align="center"><input type="text" id="weightuom5" size="15" maxlength="10" value="0"></td>
     <td align="center"></td>
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