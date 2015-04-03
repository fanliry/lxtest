<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet"%>
<%
	BaseLotSet lotset1 = (BaseLotSet)request.getAttribute("lotset1");
	BaseLotSet lotset2 = (BaseLotSet)request.getAttribute("lotset2");
	BaseLotSet lotset3 = (BaseLotSet)request.getAttribute("lotset3");
	BaseLotSet lotset4 = (BaseLotSet)request.getAttribute("lotset4");
	BaseLotSet lotset5 = (BaseLotSet)request.getAttribute("lotset5");
	BaseLotSet lotset6 = (BaseLotSet)request.getAttribute("lotset6");
	BaseLotSet lotset7 = (BaseLotSet)request.getAttribute("lotset7");
	BaseLotSet lotset8 = (BaseLotSet)request.getAttribute("lotset8");
	BaseLotSet lotset9 = (BaseLotSet)request.getAttribute("lotset9");
	BaseLotSet lotset10 = (BaseLotSet)request.getAttribute("lotset10");
	BaseLotSet lotset11 = (BaseLotSet)request.getAttribute("lotset11");
	BaseLotSet lotset12 = (BaseLotSet)request.getAttribute("lotset12");
	
	String lottype = lotset1.getLottype();
	String lottypename = lotset1.getLottypename();
	String remark = lotset1.getRemark();
	
	String lotattid1 = lotset1.getLotattid();	//ID
	String lotid1 = lotset1.getLotid();			//批次属性ID
	String lotname1 = lotset1.getLotname();		//批次属性名称
	String islot1 = lotset1.getIslot();			//是否显示或者是否分组统计 Y N
	int lotseq1 = lotset1.getLotseq();			//显示顺序或者分组统计的顺序
	
	String lotattid2 = lotset2.getLotattid();	//ID
	String lotid2 = lotset2.getLotid();			//批次属性ID
	String lotname2 = lotset2.getLotname();		//批次属性名称
	String islot2 = lotset2.getIslot();			//是否显示或者是否分组统计 Y N
	int lotseq2 = lotset2.getLotseq();			//显示顺序或者分组统计的顺序
	
	String lotattid3 = lotset3.getLotattid();	//ID
	String lotid3 = lotset3.getLotid();			//批次属性ID
	String lotname3 = lotset3.getLotname();		//批次属性名称
	String islot3 = lotset3.getIslot();			//是否显示或者是否分组统计 Y N
	int lotseq3 = lotset3.getLotseq();			//显示顺序或者分组统计的顺序
	
	String lotattid4 = lotset4.getLotattid();	//ID
	String lotid4 = lotset4.getLotid();			//批次属性ID
	String lotname4 = lotset4.getLotname();		//批次属性名称
	String islot4 = lotset4.getIslot();			//是否显示或者是否分组统计 Y N
	int lotseq4 = lotset4.getLotseq();			//显示顺序或者分组统计的顺序
	
	String lotattid5 = lotset5.getLotattid();	//ID
	String lotid5 = lotset5.getLotid();			//批次属性ID
	String lotname5 = lotset5.getLotname();		//批次属性名称
	String islot5 = lotset5.getIslot();			//是否显示或者是否分组统计 Y N
	int lotseq5 = lotset5.getLotseq();			//显示顺序或者分组统计的顺序
	
	String lotattid6 = lotset6.getLotattid();	//ID
	String lotid6 = lotset6.getLotid();			//批次属性ID
	String lotname6 = lotset6.getLotname();		//批次属性名称
	String islot6 = lotset6.getIslot();			//是否显示或者是否分组统计 Y N
	int lotseq6 = lotset6.getLotseq();			//显示顺序或者分组统计的顺序
	
	String lotattid7 = lotset7.getLotattid();	//ID
	String lotid7 = lotset7.getLotid();			//批次属性ID
	String lotname7 = lotset7.getLotname();		//批次属性名称
	String islot7 = lotset7.getIslot();			//是否显示或者是否分组统计 Y N
	int lotseq7 = lotset7.getLotseq();			//显示顺序或者分组统计的顺序
	
	String lotattid8 = lotset8.getLotattid();	//ID
	String lotid8 = lotset8.getLotid();			//批次属性ID
	String lotname8 = lotset8.getLotname();		//批次属性名称
	String islot8 = lotset8.getIslot();			//是否显示或者是否分组统计 Y N
	int lotseq8 = lotset8.getLotseq();			//显示顺序或者分组统计的顺序
	
	String lotattid9 = lotset9.getLotattid();	//ID
	String lotid9 = lotset9.getLotid();			//批次属性ID
	String lotname9 = lotset9.getLotname();		//批次属性名称
	String islot9 = lotset9.getIslot();			//是否显示或者是否分组统计 Y N
	int lotseq9 = lotset9.getLotseq();			//显示顺序或者分组统计的顺序
	
	String lotattid10 = lotset10.getLotattid();	//ID
	String lotid10 = lotset10.getLotid();		//批次属性ID
	String lotname10 = lotset10.getLotname();	//批次属性名称
	String islot10 = lotset10.getIslot();		//是否显示或者是否分组统计 Y N
	int lotseq10 = lotset10.getLotseq();		//显示顺序或者分组统计的顺序
	
	String lotattid11 = lotset11.getLotattid();	//ID
	String lotid11 = lotset11.getLotid();		//批次属性ID
	String lotname11 = lotset11.getLotname();	//批次属性名称
	String islot11 = lotset11.getIslot();		//是否显示或者是否分组统计 Y N
	int lotseq11 = lotset11.getLotseq();		//显示顺序或者分组统计的顺序
	
	String lotattid12 = lotset12.getLotattid();	//ID
	String lotid12 = lotset12.getLotid();		//批次属性ID
	String lotname12 = lotset12.getLotname();	//批次属性名称
	String islot12 = lotset12.getIslot();		//是否显示或者是否分组统计 Y N
	int lotseq12 = lotset12.getLotseq();		//显示顺序或者分组统计的顺序
%>
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
	
		var remark = document.getElementById("remark").value;	//备注
		
		var lotattid1 = document.getElementById("lotattid1").value;		//ID
		var lotname1 = document.getElementById("lotname1").value;		//批次属性名称
		var lotid1 = document.getElementById("lotid1").value;			//批次属性ID
		var islot1 = document.getElementById("islot1").value;			//是否显示或者是否分组统计 Y N
		var lotseq1 = document.getElementById("lotseq1").value;			//显示顺序或者分组统计的顺序
		
		var lotattid2 = document.getElementById("lotattid2").value;		//ID
		var lotname2 = document.getElementById("lotname2").value;		//批次属性名称
		var lotid2 = document.getElementById("lotid2").value;			//批次属性ID
		var islot2 = document.getElementById("islot2").value;			//是否显示或者是否分组统计 Y N
		var lotseq2 = document.getElementById("lotseq2").value;			//显示顺序或者分组统计的顺序
		
		var lotattid3 = document.getElementById("lotattid3").value;		//ID
		var lotname3 = document.getElementById("lotname3").value;		//批次属性名称
		var lotid3 = document.getElementById("lotid3").value;			//批次属性ID
		var islot3 = document.getElementById("islot3").value;			//是否显示或者是否分组统计 Y N
		var lotseq3 = document.getElementById("lotseq3").value;			//显示顺序或者分组统计的顺序
		
		var lotattid4 = document.getElementById("lotattid4").value;		//ID
		var lotname4 = document.getElementById("lotname4").value;		//批次属性名称
		var lotid4 = document.getElementById("lotid4").value;			//批次属性ID
		var islot4 = document.getElementById("islot4").value;			//是否显示或者是否分组统计 Y N
		var lotseq4 = document.getElementById("lotseq4").value;			//显示顺序或者分组统计的顺序
		
		var lotattid5 = document.getElementById("lotattid5").value;		//ID
		var lotname5 = document.getElementById("lotname5").value;		//批次属性名称
		var lotid5 = document.getElementById("lotid5").value;			//批次属性ID
		var islot5 = document.getElementById("islot5").value;			//是否显示或者是否分组统计 Y N
		var lotseq5 = document.getElementById("lotseq5").value;			//显示顺序或者分组统计的顺序
		
		var lotattid6 = document.getElementById("lotattid6").value;		//ID
		var lotname6 = document.getElementById("lotname6").value;		//批次属性名称
		var lotid6 = document.getElementById("lotid6").value;			//批次属性ID
		var islot6 = document.getElementById("islot6").value;			//是否显示或者是否分组统计 Y N
		var lotseq6 = document.getElementById("lotseq6").value;			//显示顺序或者分组统计的顺序
		
		var lotattid7 = document.getElementById("lotattid7").value;		//ID
		var lotname7 = document.getElementById("lotname7").value;		//批次属性名称
		var lotid7 = document.getElementById("lotid7").value;			//批次属性ID
		var islot7 = document.getElementById("islot7").value;			//是否显示或者是否分组统计 Y N
		var lotseq7 = document.getElementById("lotseq7").value;			//显示顺序或者分组统计的顺序
		
		var lotattid8 = document.getElementById("lotattid8").value;		//ID
		var lotname8 = document.getElementById("lotname8").value;		//批次属性名称
		var lotid8 = document.getElementById("lotid8").value;			//批次属性ID
		var islot8 = document.getElementById("islot8").value;			//是否显示或者是否分组统计 Y N
		var lotseq8 = document.getElementById("lotseq8").value;			//显示顺序或者分组统计的顺序
		
		var lotattid9 = document.getElementById("lotattid9").value;		//ID
		var lotname9 = document.getElementById("lotname9").value;		//批次属性名称
		var lotid9 = document.getElementById("lotid9").value;			//批次属性ID
		var islot9 = document.getElementById("islot9").value;			//是否显示或者是否分组统计 Y N
		var lotseq9 = document.getElementById("lotseq9").value;			//显示顺序或者分组统计的顺序
		
		var lotattid10 = document.getElementById("lotattid10").value;	//ID
		var lotname10 = document.getElementById("lotname10").value;		//批次属性名称
		var lotid10 = document.getElementById("lotid10").value;			//批次属性ID
		var islot10 = document.getElementById("islot10").value;			//是否显示或者是否分组统计 Y N
		var lotseq10 = document.getElementById("lotseq10").value;		//显示顺序或者分组统计的顺序
		
		var lotattid11 = document.getElementById("lotattid11").value;	//ID
		var lotname11 = document.getElementById("lotname11").value;		//批次属性名称
		var lotid11 = document.getElementById("lotid11").value;			//批次属性ID
		var islot11 = document.getElementById("islot11").value;			//是否显示或者是否分组统计 Y N
		var lotseq11 = document.getElementById("lotseq11").value;		//显示顺序或者分组统计的顺序
		
		var lotattid12 = document.getElementById("lotattid12").value;	//ID
		var lotname12 = document.getElementById("lotname12").value;		//批次属性名称
		var lotid12 = document.getElementById("lotid12").value;			//批次属性ID
		var islot12 = document.getElementById("islot12").value;			//是否显示或者是否分组统计 Y N
		var lotseq12 = document.getElementById("lotseq12").value;		//显示顺序或者分组统计的顺序
		
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

		condition = "&lottype=<%=lottype%>&remark=" + remark 
			 + "&lotattid1=" + lotattid1 + "&lotname1=" + lotname1 + "&lotid1=" + lotid1 + "&islot1=" + islot1 + "&lotseq1=" + lotseq1 
			 + "&lotattid2=" + lotattid2 + "&lotname2=" + lotname2 + "&lotid2=" + lotid2 + "&islot2=" + islot2 + "&lotseq2=" + lotseq2
			 + "&lotattid3=" + lotattid3 + "&lotname3=" + lotname3 + "&lotid3=" + lotid3 + "&islot3=" + islot3 + "&lotseq3=" + lotseq3 
			 + "&lotattid4=" + lotattid4 + "&lotname4=" + lotname4 + "&lotid4=" + lotid4 + "&islot4=" + islot4 + "&lotseq4=" + lotseq4 
			 + "&lotattid5=" + lotattid5 + "&lotname5=" + lotname5 + "&lotid5=" + lotid5 + "&islot5=" + islot5 + "&lotseq5=" + lotseq5
			 + "&lotattid6=" + lotattid6 + "&lotname6=" + lotname6 + "&lotid6=" + lotid6 + "&islot6=" + islot6 + "&lotseq6=" + lotseq6
			 + "&lotattid7=" + lotattid7 + "&lotname7=" + lotname7 + "&lotid7=" + lotid7 + "&islot7=" + islot7 + "&lotseq7=" + lotseq7
			 + "&lotattid8=" + lotattid8 + "&lotname8=" + lotname8 + "&lotid8=" + lotid8 + "&islot8=" + islot8 + "&lotseq8=" + lotseq8
			 + "&lotattid9=" + lotattid9 + "&lotname9=" + lotname9 + "&lotid9=" + lotid9 + "&islot9=" + islot9 + "&lotseq9=" + lotseq9
			 + "&lotattid10=" + lotattid10 + "&lotname10=" + lotname10 + "&lotid10=" + lotid10 + "&islot10=" + islot10 + "&lotseq10=" + lotseq10
			 + "&lotattid11=" + lotattid11 + "&lotname11=" + lotname11 + "&lotid11=" + lotid11 + "&islot11=" + islot11 + "&lotseq11=" + lotseq11
			 + "&lotattid12=" + lotattid12 + "&lotname12=" + lotname12 + "&lotid12=" + lotid12 + "&islot12=" + islot12 + "&lotseq12=" + lotseq12;
	
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
		selectLot("", "lot");				//批次属性
		DWREngine.setAsync(true);
		
		document.getElementById("remark").value = '<%=remark==null?"":remark%>';
		
		document.getElementById("lotattid1").value = '<%=lotattid1%>';
		document.getElementById("lotname1").value = '<%=lotname1%>';
		document.getElementById("lotid1").value = '<%=lotid1%>';
		document.getElementById("islot1").value = "<%=islot1%>";
		document.getElementById("lotseq1").value = "<%=lotseq1%>";
		
		document.getElementById("lotattid2").value = '<%=lotattid2%>';
		document.getElementById("lotname2").value = '<%=lotname2%>';
		document.getElementById("lotid2").value = '<%=lotid2%>';
		document.getElementById("islot2").value = "<%=islot2%>";
		document.getElementById("lotseq2").value = "<%=lotseq2%>";
		
		document.getElementById("lotattid3").value = '<%=lotattid3%>';
		document.getElementById("lotname3").value = '<%=lotname3%>';
		document.getElementById("lotid3").value = '<%=lotid3%>';
		document.getElementById("islot3").value = "<%=islot3%>";
		document.getElementById("lotseq3").value = "<%=lotseq3%>";
		
		document.getElementById("lotattid4").value = '<%=lotattid4%>';
		document.getElementById("lotname4").value = '<%=lotname4%>';
		document.getElementById("lotid4").value = '<%=lotid4%>';
		document.getElementById("islot4").value = "<%=islot4%>";
		document.getElementById("lotseq4").value = "<%=lotseq4%>";
		
		document.getElementById("lotattid5").value = '<%=lotattid5%>';
		document.getElementById("lotname5").value = '<%=lotname5%>';
		document.getElementById("lotid5").value = '<%=lotid5%>';
		document.getElementById("islot5").value = "<%=islot5%>";
		document.getElementById("lotseq5").value = "<%=lotseq5%>";
		
		document.getElementById("lotattid6").value = '<%=lotattid6%>';
		document.getElementById("lotname6").value = '<%=lotname6%>';
		document.getElementById("lotid6").value = '<%=lotid6%>';
		document.getElementById("islot6").value = "<%=islot6%>";
		document.getElementById("lotseq6").value = "<%=lotseq6%>";
		
		document.getElementById("lotattid7").value = '<%=lotattid7%>';
		document.getElementById("lotname7").value = '<%=lotname7%>';
		document.getElementById("lotid7").value = '<%=lotid7%>';
		document.getElementById("islot7").value = "<%=islot7%>";
		document.getElementById("lotseq7").value = "<%=lotseq7%>";
		
		document.getElementById("lotattid8").value = '<%=lotattid8%>';
		document.getElementById("lotname8").value = '<%=lotname8%>';
		document.getElementById("lotid8").value = '<%=lotid8%>';
		document.getElementById("islot8").value = "<%=islot8%>";
		document.getElementById("lotseq8").value = "<%=lotseq8%>";
		
		document.getElementById("lotattid9").value = '<%=lotattid9%>';
		document.getElementById("lotname9").value = '<%=lotname9%>';
		document.getElementById("lotid9").value = '<%=lotid9%>';
		document.getElementById("islot9").value = "<%=islot9%>";
		document.getElementById("lotseq9").value = "<%=lotseq9%>";
		
		document.getElementById("lotattid10").value = '<%=lotattid10%>';
		document.getElementById("lotname10").value = '<%=lotname10%>';
		document.getElementById("lotid10").value = '<%=lotid10%>';
		document.getElementById("islot10").value = "<%=islot10%>";
		document.getElementById("lotseq10").value = "<%=lotseq10%>";
		
		document.getElementById("lotattid11").value = '<%=lotattid11%>';
		document.getElementById("lotname11").value = '<%=lotname11%>';
		document.getElementById("lotid11").value = '<%=lotid11%>';
		document.getElementById("islot11").value = "<%=islot11%>";
		document.getElementById("lotseq11").value = "<%=lotseq11%>";
		
		document.getElementById("lotattid12").value = '<%=lotattid12%>';
		document.getElementById("lotname12").value = '<%=lotname12%>';
		document.getElementById("lotid12").value = '<%=lotid12%>';
		document.getElementById("islot12").value = "<%=islot12%>";
		document.getElementById("lotseq12").value = "<%=lotseq12%>";
	}
-->
</script>
</head>

<body onLoad="OnLoad();">
<form name="myForm" method="post" action="">
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：批次设置 -&gt; 修改批次设置</div></td>
   </tr>
 </table>
 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td class="yx1" width="100px" align="right">类型：</td>
     <td class="yx"><input type="text" id="lottype" value="<%=lottypename%>" readonly class="input_readonly"></td>
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
     <td class="yx" align="center"><input type="hidden" id="lotattid1" value=""><input type="text" id="lotname1" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid1" size="15" maxlength="15" value="lotatt1" disabled></td>
     <td class="yx" align="center"><select id="islot1" style="width:100px;"><option value="Y">是</option><option value="N">否</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq1" size="15" maxlength="2" value=""></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotattid2" value=""><input type="text" id="lotname2" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid2" size="15" maxlength="15" value="lotatt2" disabled></td>
     <td class="yx" align="center"><select id="islot2" style="width:100px;"><option value="Y">是</option><option value="N">否</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq2" size="15" maxlength="2" value=""></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotattid3" value=""><input type="text" id="lotname3" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid3" size="15" maxlength="15" value="lotatt3" disabled></td>
     <td class="yx" align="center"><select id="islot3" style="width:100px;"><option value="Y">是</option><option value="N">否</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq3" size="15" maxlength="2" value=""></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotattid4" value=""><input type="text" id="lotname4" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid4" size="15" maxlength="15" value="lotatt4" disabled></td>
     <td class="yx" align="center"><select id="islot4" style="width:100px;"><option value="Y">是</option><option value="N">否</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq4" size="15" maxlength="2" value=""></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotattid5" value=""><input type="text" id="lotname5" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid5" size="15" maxlength="15" value="lotatt5" disabled></td>
     <td class="yx" align="center"><select id="islot5" style="width:100px;"><option value="Y">是</option><option value="N">否</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq5" size="15" maxlength="2" value=""></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotattid6" value=""><input type="text" id="lotname6" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid6" size="15" maxlength="15" value="lotatt6" disabled></td>
     <td class="yx" align="center"><select id="islot6" style="width:100px;"><option value="Y">是</option><option value="N">否</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq6" size="15" maxlength="2" value=""></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotattid7" value=""><input type="text" id="lotname7" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid7" size="15" maxlength="15" value="lotatt7" disabled></td>
     <td class="yx" align="center"><select id="islot7" style="width:100px;"><option value="Y">是</option><option value="N">否</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq7" size="15" maxlength="2" value=""></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotattid8" value=""><input type="text" id="lotname8" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid8" size="15" maxlength="15" value="lotatt8" disabled></td>
     <td class="yx" align="center"><select id="islot8" style="width:100px;"><option value="Y">是</option><option value="N">否</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq8" size="15" maxlength="2" value=""></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotattid9" value=""><input type="text" id="lotname9" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid9" size="15" maxlength="15" value="lotatt9" disabled></td>
     <td class="yx" align="center"><select id="islot9" style="width:100px;"><option value="Y">是</option><option value="N">否</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq9" size="15" maxlength="2" value=""></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotattid10" value=""><input type="text" id="lotname10" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid10" size="15" maxlength="15" value="lotatt10" disabled></td>
     <td class="yx" align="center"><select id="islot10" style="width:100px;"><option value="Y">是</option><option value="N">否</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq10" size="15" maxlength="2" value=""></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotattid11" value=""><input type="text" id="lotname11" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid11" size="15" maxlength="15" value="lotatt11" disabled></td>
     <td class="yx" align="center"><select id="islot11" style="width:100px;"><option value="Y">是</option><option value="N">否</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq11" size="15" maxlength="2" value=""></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotattid12" value=""><input type="text" id="lotname12" size="15" maxlength="15" value=""></td>
     <td class="yx" align="center"><input type="text" id="lotid12" size="15" maxlength="15" value="lotatt12" disabled></td>
     <td class="yx" align="center"><select id="islot12" style="width:100px;"><option value="Y">是</option><option value="N">否</option></select></td>
     <td class="xx1" align="center"><input type="text" id="lotseq12" size="15" maxlength="2" value=""></td>
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