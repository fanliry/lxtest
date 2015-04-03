<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BasePackage" %>
<%@ page import="com.wms3.bms.standard.entity.base.BasePackageMastermesg" %>c
<%@ page import="java.text.NumberFormat" %>
<%
	//包装
	BasePackage pk = (BasePackage)request.getAttribute("pk");
	//包装主信息 包装附加信息
	BasePackageMastermesg pkmaster1 = (BasePackageMastermesg)request.getAttribute("pkmaster1");
	BasePackageMastermesg pkmaster2 = (BasePackageMastermesg)request.getAttribute("pkmaster2");
	BasePackageMastermesg pkmaster3 = (BasePackageMastermesg)request.getAttribute("pkmaster3");
	BasePackageMastermesg pkmaster4 = (BasePackageMastermesg)request.getAttribute("pkmaster4");
	BasePackageMastermesg pkmaster5 = (BasePackageMastermesg)request.getAttribute("pkmaster5");
	
	//保留小数2位
	NumberFormat nbf=NumberFormat.getInstance();    
  	nbf.setMinimumFractionDigits(2);
  	nbf.setMaximumFractionDigits(2);
	
	String packid = pk.getPackageid();	/*包装代码*/
	String descr = pk.getPkgdesc();		/*描述*/
	String remark = pk.getRemark();		/*备注*/
	
	String packmasterid1 = pkmaster1.getPackmasterid();	/*包装主信息ID*/
	String qty1 = (int)pkmaster1.getQty()+"";			/*数量*/
	String descr1 = pkmaster1.getPkgunitdesc();			/*描述 默认值:主单位,内包装,箱,托盘,其它*/
	String in_label1 = pkmaster1.getInused();			/*入库启用*/
	String out_label1 = pkmaster1.getOutused();			/*出库启用*/
	String usage1 = pkmaster1.getCsused();				/*库位使用*/
	String lengthuom1 = nbf.format(pkmaster1.getLength()).replaceAll(",", "");	/*长*/
	String widthuom1 = nbf.format(pkmaster1.getWidth()).replaceAll(",", "");	/*宽*/
	String heightuom1 = nbf.format(pkmaster1.getHeight()).replaceAll(",", "");	/*高*/
	String cubeuom1 = nbf.format(pkmaster1.getVolume()).replaceAll(",", "");	/*体积*/
	String weightuom1 = nbf.format(pkmaster1.getWeight()).replaceAll(",", "");	/*重量*/
	
	String packmasterid2 = pkmaster2.getPackmasterid();	/*包装主信息ID*/
	String qty2 = (int)pkmaster2.getQty()+"";			/*数量*/
	String descr2 = pkmaster2.getPkgunitdesc();			/*描述 默认值:主单位,内包装,箱,托盘,其它*/
	String in_label2 = pkmaster2.getInused();			/*入库启用*/
	String out_label2 = pkmaster2.getOutused();			/*出库启用*/
	String usage2 = pkmaster2.getCsused();				/*库位使用*/
	String lengthuom2 = nbf.format(pkmaster2.getLength()).replaceAll(",", "");	/*长*/
	String widthuom2 = nbf.format(pkmaster2.getWidth()).replaceAll(",", "");	/*宽*/
	String heightuom2 = nbf.format(pkmaster2.getHeight()).replaceAll(",", "");	/*高*/
	String cubeuom2 = nbf.format(pkmaster2.getVolume()).replaceAll(",", "");	/*体积*/
	String weightuom2 = nbf.format(pkmaster2.getWeight()).replaceAll(",", "");	/*重量*/
	
	String packmasterid3 = pkmaster3.getPackmasterid();	/*包装主信息ID*/
	String qty3 = (int)pkmaster3.getQty()+"";			/*数量*/
	String descr3 = pkmaster3.getPkgunitdesc();			/*描述 默认值:主单位,内包装,箱,托盘,其它*/
	String in_label3 = pkmaster3.getInused();			/*入库启用*/
	String out_label3 = pkmaster3.getOutused();			/*出库启用*/
	String usage3 = pkmaster3.getCsused();				/*库位使用*/
	String lengthuom3 = nbf.format(pkmaster3.getLength()).replaceAll(",", "");	/*长*/
	String widthuom3 = nbf.format(pkmaster3.getWidth()).replaceAll(",", "");	/*宽*/
	String heightuom3 = nbf.format(pkmaster3.getHeight()).replaceAll(",", "");	/*高*/
	String cubeuom3 = nbf.format(pkmaster3.getVolume()).replaceAll(",", "");	/*体积*/
	String weightuom3 = nbf.format(pkmaster3.getWeight()).replaceAll(",", "");	/*重量*/
	
	String packmasterid4 = pkmaster4.getPackmasterid();	/*包装主信息ID*/
	String qty4 = (int)pkmaster4.getQty()+"";			/*数量*/
	String descr4 = pkmaster4.getPkgunitdesc();			/*描述 默认值:主单位,内包装,箱,托盘,其它*/
	String in_label4 = pkmaster4.getInused();			/*入库启用*/
	String out_label4 = pkmaster4.getOutused();			/*出库启用*/
	String usage4 = pkmaster4.getCsused();				/*库位使用*/
	String lengthuom4 = nbf.format(pkmaster4.getLength()).replaceAll(",", "");	/*长*/
	String widthuom4 = nbf.format(pkmaster4.getWidth()).replaceAll(",", "");	/*宽*/
	String heightuom4 = nbf.format(pkmaster4.getHeight()).replaceAll(",", "");	/*高*/
	String cubeuom4 = nbf.format(pkmaster4.getVolume()).replaceAll(",", "");	/*体积*/
	String weightuom4 = nbf.format(pkmaster4.getWeight()).replaceAll(",", "");	/*重量*/
	String palletwoodlength = nbf.format(pkmaster4.getPalletlength()).replaceAll(",", "");	/*托盘长度*/
	String palletwoodwidth = nbf.format(pkmaster4.getPalletwidth()).replaceAll(",", "");	/*托盘宽度*/
	String palletwoodheight = nbf.format(pkmaster4.getPalletheight()).replaceAll(",", "");	/*托盘高度*/
	String palletti = (int)pkmaster4.getPalletti()+"";							/*TI*/
	String pallethi = (int)pkmaster4.getPallethi()+"";							/*HI*/
	
	String packmasterid5 = pkmaster5.getPackmasterid();	/*包装主信息ID*/
	String qty5 = (int)pkmaster5.getQty()+"";			/*数量*/
	String descr5 = pkmaster5.getPkgunitdesc();			/*描述 默认值:主单位,内包装,箱,托盘,其它*/
	String in_label5 = pkmaster5.getInused();			/*入库启用*/
	String out_label5 = pkmaster5.getOutused();			/*出库启用*/
	String usage5 = pkmaster5.getCsused();				/*库位使用*/
	String lengthuom5 = nbf.format(pkmaster5.getLength()).replaceAll(",", "");	/*长*/
	String widthuom5 = nbf.format(pkmaster5.getWidth()).replaceAll(",", "");	/*宽*/
	String heightuom5 = nbf.format(pkmaster5.getHeight()).replaceAll(",", "");	/*高*/
	String cubeuom5 = nbf.format(pkmaster5.getVolume()).replaceAll(",", "");	/*体积*/
	String weightuom5 = nbf.format(pkmaster5.getWeight()).replaceAll(",", "");	/*重量*/
%>
<html>
<head>
<title>修改</title>
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
	function Save()
	{
		/*包装*/
		var packid = document.getElementById("packid").value;
		var descr = document.getElementById("descr").value;
		var remark = document.getElementById("remark").value;
		
		/*包装主信息*/
		var packmasterid1 = document.getElementById("packmasterid1").value;
		var qty1 = document.getElementById("qty1").value;
		var descr1 = document.getElementById("descr1").value;
		var in_label1 = document.getElementById("in_label1").checked;
		var out_label1 = document.getElementById("out_label1").checked;
		var loc_usage1 = document.getElementById("loc_usage1").value;
		
		var packmasterid2 = document.getElementById("packmasterid2").value;
		var qty2 = document.getElementById("qty2").value;
		var descr2 = document.getElementById("descr2").value;
		var in_label2 = document.getElementById("in_label2").checked;
		var out_label2 = document.getElementById("out_label2").checked;
		var loc_usage2 = document.getElementById("loc_usage2").value;
		
		var packmasterid3 = document.getElementById("packmasterid3").value;
		var qty3 = document.getElementById("qty3").value;
		var descr3 = document.getElementById("descr3").value;
		var in_label3 = document.getElementById("in_label3").checked;
		var out_label3 = document.getElementById("out_label3").checked;
		var loc_usage3 = document.getElementById("loc_usage3").value;
		
		var packmasterid4 = document.getElementById("packmasterid4").value;
		var qty4 = document.getElementById("qty4").value;
		var descr4 = document.getElementById("descr4").value;
		var in_label4 = document.getElementById("in_label4").checked;
		var out_label4 = document.getElementById("out_label4").checked;
		var loc_usage4 = document.getElementById("loc_usage4").value;
		
		var packmasterid5 = document.getElementById("packmasterid5").value;
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
		if(descr == null || descr.length < 1)
		{
			alert("【包装描述】不能为空！");
			return;
		}
		if(strlen(descr) > 100)
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
				alert("【主单位 数量】只能为正浮点数或0！");
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
				alert("【内包装 数量】只能为正浮点数或0！");
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
				alert("【箱 数量】只能为正浮点数或0！");
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
				alert("【托盘 数量】只能为正浮点数或0！");
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
				alert("【其它 数量】只能为正浮点数或0！");
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
		
		condition = "&packid=" + packid + "&descr=" + descr + "&remark=" + remark 
				 
				 + "&packmasterid1=" + packmasterid1 + "&qty1=" + qty1 + "&descr1=" + descr1 + "&in_label1=" + in_label1 + "&out_label1=" + out_label1 
				 + "&packmasterid2=" + packmasterid2 + "&qty2=" + qty2 + "&descr2=" + descr2 + "&in_label2=" + in_label2 + "&out_label2=" + out_label2 
				 + "&packmasterid3=" + packmasterid3 + "&qty3=" + qty3 + "&descr3=" + descr3 + "&in_label3=" + in_label3 + "&out_label3=" + out_label3 
				 + "&packmasterid4=" + packmasterid4 + "&qty4=" + qty4 + "&descr4=" + descr4 + "&in_label4=" + in_label4 + "&out_label4=" + out_label4 
				 + "&packmasterid5=" + packmasterid5 + "&qty5=" + qty5 + "&descr5=" + descr5 + "&in_label5=" + in_label5 + "&out_label5=" + out_label5
				 
				 + "&lengthuom1=" + lengthuom1 + "&widthuom1=" + widthuom1 + "&heightuom1=" + heightuom1 + "&cubeuom1=" 
				 + cubeuom1 + "&weightuom1=" + weightuom1
				 
				 + "&lengthuom2=" + lengthuom2 + "&widthuom2=" + widthuom2 + "&heightuom2=" + heightuom2 + "&cubeuom2=" 
				 + cubeuom2 + "&weightuom2=" + weightuom2
				 
				 + "&lengthuom3=" + lengthuom3 + "&widthuom3=" + widthuom3 + "&heightuom3=" + heightuom3 + "&cubeuom3=" 
				 + cubeuom3 + "&weightuom3=" + weightuom3
				 
				 + "&lengthuom4=" + lengthuom4 + "&widthuom4=" + widthuom4 + "&heightuom4=" + heightuom4 + "&cubeuom4=" 
				 + cubeuom4 + "&weightuom4=" + weightuom4
				 + "&palletwoodlength=" + palletwoodlength + "&palletwoodwidth=" + palletwoodwidth + "&palletwoodheight=" + palletwoodheight 
				 + "&palletti=" + palletti + "&pallethi=" + pallethi
				 
				 + "&lengthuom5=" + lengthuom5 + "&widthuom5=" + widthuom5 + "&heightuom5=" + heightuom5 + "&cubeuom5=" 
				 + cubeuom5 + "&weightuom5=" + weightuom5
				 + "&loc_usage1=" + loc_usage1 + "&loc_usage2=" + loc_usage2 + "&loc_usage3=" + loc_usage3 + "&loc_usage4=" + loc_usage4 + "&loc_usage5=" + loc_usage5; 
	
		window.returnValue = condition;
		window.close();
	}
	
	//登陆页面
	function OnLoad(){
	
		//同步
		DWREngine.setAsync(false);
		selectTypes("", 'loc_usage', "kwsy", 5);	//库位使用
		DWREngine.setAsync(true);
		
		document.getElementById("loc_usage1").value = "<%=usage1%>";
		document.getElementById("loc_usage2").value = "<%=usage2%>";
		document.getElementById("loc_usage3").value = "<%=usage3%>";
		document.getElementById("loc_usage4").value = "<%=usage4%>";
		document.getElementById("loc_usage5").value = "<%=usage5%>";
	}

-->
</script>
</head>

<body onLoad="OnLoad();">

 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：包装 -&gt; 修改包装</div></td>
   </tr>
 </table>
 
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td class="yx1" width="100px" align="right"><span class="red">*</span>包装描述：</td>
     <td class="xx1"><input type="hidden" id="packid" value="<%=packid%>"><input type="text" id="descr" size="50" maxlength="100" value="<%=descr%>"></td>
   </tr>
   <tr>
     <td class="y2" align="right">备注：</td>
     <td><textarea id="remark" cols="83" rows="3"><%=remark==null?"":remark%></textarea></td>
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
     <td class="yx" align="center">主单位<input type="hidden" id="packmasterid1" value="<%=packmasterid1%>"></td>
     <td class="yx" align="center"><input type="text" id="qty1" size="15" maxlength="10" value="<%=qty1%>" readonly></td>
     <td class="yx" align="center"><input type="text" id="descr1" size="15" maxlength="20" value="<%=descr1%>" value="件"></td>
     <td class="yx" align="center"><input type="checkbox" id="in_label1" class="input_checkbox" <%if(in_label1.equals("Y")){%>checked<%}%>></td>
     <td class="yx" align="center"><input type="checkbox" id="out_label1" class="input_checkbox" <%if(out_label1.equals("Y")){%>checked<%}%>></td>
     <td class="xx1" align="center"><select id="loc_usage1"><option>----- 请选择 ----</option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center">内包装<input type="hidden" id="packmasterid2" value="<%=packmasterid2%>"></td>
     <td class="yx" align="center"><input type="text" id="qty2" size="15" maxlength="10"  value="<%=qty2%>"></td>
     <td class="yx" align="center"><input type="text" id="descr2" size="15" maxlength="20" value="<%=descr2%>"></td>
     <td class="yx" align="center"><input type="checkbox" id="in_label2" class="input_checkbox" <%if(in_label2.equals("Y")){%>checked<%}%>></td>
     <td class="yx" align="center"><input type="checkbox" id="out_label2" class="input_checkbox" <%if(out_label2.equals("Y")){%>checked<%}%>></td>
     <td class="xx1" align="center"><select id="loc_usage2"><option>----- 请选择 ----</option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center">箱<input type="hidden" id="packmasterid3" value="<%=packmasterid3%>"></td>
     <td class="yx" align="center"><input type="text" id="qty3" size="15" maxlength="10" value="<%=qty3%>"></td>
     <td class="yx" align="center"><input type="text" id="descr3" size="15" maxlength="20" value="<%=descr3%>"></td>
     <td class="yx" align="center"><input type="checkbox" id="in_label3" class="input_checkbox" <%if(in_label3.equals("Y")){%>checked<%}%>></td>
     <td class="yx" align="center"><input type="checkbox" id="out_label3" class="input_checkbox" <%if(out_label3.equals("Y")){%>checked<%}%>></td>
  	 <td class="xx1" align="center"><select id="loc_usage3"><option>----- 请选择 ----</option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center">托盘<input type="hidden" id="packmasterid4"  value="<%=packmasterid4%>"></td>
     <td class="yx" align="center"><input type="text" id="qty4" size="15" maxlength="10"  value="<%=qty4%>"></td>
     <td class="yx" align="center"><input type="text" id="descr4" size="15" maxlength="20" value="<%=descr4%>"></td>
     <td class="yx" align="center"><input type="checkbox" id="in_label4" class="input_checkbox" <%if(in_label4.equals("Y")){%>checked<%}%>></td>
     <td class="yx" align="center"><input type="checkbox" id="out_label4" class="input_checkbox" <%if(out_label4.equals("Y")){%>checked<%}%>></td>
   	 <td class="xx1" align="center"><select id="loc_usage4"><option>----- 请选择 ----</option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center">其它<input type="hidden" id="packmasterid5"  value="<%=packmasterid5%>"></td>
     <td class="yx" align="center"><input type="text" id="qty5" size="15" maxlength="10"  value="<%=qty5%>"></td>
     <td class="yx" align="center"><input type="text" id="descr5" size="15" maxlength="20" value="<%=descr5%>"></td>
     <td class="yx" align="center"><input type="checkbox" id="in_label5" class="input_checkbox" <%if(in_label5.equals("Y")){%>checked<%}%>></td>
     <td class="yx" align="center"><input type="checkbox" id="out_label5" class="input_checkbox" <%if(out_label5.equals("Y")){%>checked<%}%>></td>
   	 <td class="xx1" align="center"><select id="loc_usage5"><option>----- 请选择 ----</option></select></td>
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
     <td class="yx" align="center"><input type="text" id="lengthuom1" size="15" maxlength="10" value="<%=lengthuom1%>"></td>
     <td class="yx" align="center"><input type="text" id="widthuom1" size="15" maxlength="10" value="<%=widthuom1%>"></td>
     <td class="yx" align="center"><input type="text" id="heightuom1" size="15" maxlength="10" value="<%=heightuom1%>"></td>
     <td class="yx" align="center"><input type="text" id="cubeuom1"  size="15" maxlength="10" value="<%=cubeuom1%>"></td>
     <td class="yx" align="center"><input type="text" id="weightuom1" size="15" maxlength="10" value="<%=weightuom1%>"></td>
     <td class="xx1" align="center">&nbsp;</td>
   </tr>
   <tr>
     <td class="yx" align="center">内包装</td>
     <td class="yx" align="center"><input type="text" id="lengthuom2" size="15" maxlength="10" value="<%=lengthuom2%>"></td>
     <td class="yx" align="center"><input type="text" id="widthuom2" size="15" maxlength="10" value="<%=widthuom2%>"></td>
     <td class="yx" align="center"><input type="text" id="heightuom2" size="15" maxlength="10" value="<%=heightuom2%>"></td>
     <td class="yx" align="center"><input type="text" id="cubeuom2"  size="15" maxlength="10" value="<%=cubeuom2%>"></td>
     <td class="yx" align="center"><input type="text" id="weightuom2" size="15" maxlength="10" value="<%=weightuom2%>"></td>
     <td class="xx1" align="center">&nbsp;</td>
   </tr>
   <tr>
     <td class="yx" align="center">箱</td>
     <td class="yx" align="center"><input type="text" id="lengthuom3" size="15" maxlength="10" value="<%=lengthuom3%>"></td>
     <td class="yx" align="center"><input type="text" id="widthuom3" size="15" maxlength="10" value="<%=widthuom3%>"></td>
     <td class="yx" align="center"><input type="text" id="heightuom3" size="15" maxlength="10" value="<%=heightuom3%>"></td>
     <td class="yx" align="center"><input type="text" id="cubeuom3"  size="15" maxlength="10" value="<%=cubeuom3%>"></td>
     <td class="yx" align="center"><input type="text" id="weightuom3" size="15" maxlength="10" value="<%=weightuom3%>"></td>
     <td class="xx1" align="center">&nbsp;</td>
   </tr>
   <tr>
     <td class="x" align="center">托盘</td>
     <td class="yx" align="center"><input type="text" id="lengthuom4" size="15" maxlength="10" value="<%=lengthuom4%>"></td>
     <td class="yx" align="center"><input type="text" id="widthuom4" size="15" maxlength="10" value="<%=widthuom4%>"></td>
     <td class="yx" align="center"><input type="text" id="heightuom4" size="15" maxlength="10" value="<%=heightuom4%>"></td>
     <td class="yx" align="center"><input type="text" id="cubeuom4"  size="15" maxlength="10" value="<%=cubeuom4%>"></td>
     <td class="yx" align="center"><input type="text" id="weightuom4" size="15" maxlength="10" value="<%=weightuom4%>"></td>
     <td class="xx1" align="center"><input type="text" id="palletti" size="5" maxlength="6" value="<%=palletti%>">*<input type="text" id="pallethi" size="5" maxlength="6" value="<%=pallethi%>"></td>
   </tr>
   <tr>
     <td class="yx" align="center">&nbsp;</td>
     <td class="yx" align="center"><input type="text" id="palletwoodlength" size="15" maxlength="10" value="<%=palletwoodlength%>"></td>
     <td class="yx" align="center"><input type="text" id="palletwoodwidth" size="15" maxlength="10" value="<%=palletwoodwidth%>"></td>
     <td class="yx" align="center"><input type="text" id="palletwoodheight" size="15" maxlength="10" value="<%=palletwoodheight%>"></td>
     <td class="yx" align="center">&nbsp;</td>
     <td class="yx" align="center">&nbsp;</td>
     <td class="xx1" align="center">&nbsp;</td>
   </tr>
   <tr>
     <td class="x" align="center">其它</td>
     <td class="x" align="center"><input type="text" id="lengthuom5" size="15" maxlength="10" value="<%=lengthuom5%>"></td>
     <td class="x" align="center"><input type="text" id="widthuom5" size="15" maxlength="10" value="<%=widthuom5%>"></td>
     <td class="x" align="center"><input type="text" id="heightuom5" size="15" maxlength="10" value="<%=heightuom5%>"></td>
     <td class="x" align="center"><input type="text" id="cubeuom5"  size="15" maxlength="10" value="<%=cubeuom5%>"></td>
     <td class="x" align="center"><input type="text" id="weightuom5" size="15" maxlength="10" value="<%=weightuom5%>"></td>
     <td align="center"></td>
   </tr>
 </table>
 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">  
   <tr>
     <td height="27" align="center">
         <input type="button" name="saveBtn" value="&nbsp;&nbsp;&nbsp;保存" class="button_add" onclick="Save()">
         <input type="button" name="cancelBtn" value="关闭" class="BUTTON_STYLE1" onClick="window.close()">
     </td>
   </tr>
 </table>
 
</body>
</html>