<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLot"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotDetail"%>
<%
	//批次属性
	BaseLot lot = (BaseLot)request.getAttribute("lot");
	
	//批次属性明细
	BaseLotDetail lotDetail1 = (BaseLotDetail)request.getAttribute("lotDetail1");
	BaseLotDetail lotDetail2 = (BaseLotDetail)request.getAttribute("lotDetail2");
	BaseLotDetail lotDetail3 = (BaseLotDetail)request.getAttribute("lotDetail3");
	
	BaseLotDetail lotDetail4 = (BaseLotDetail)request.getAttribute("lotDetail4");
	BaseLotDetail lotDetail5 = (BaseLotDetail)request.getAttribute("lotDetail5");
	BaseLotDetail lotDetail6 = (BaseLotDetail)request.getAttribute("lotDetail6");
	
	BaseLotDetail lotDetail7 = (BaseLotDetail)request.getAttribute("lotDetail7");
	BaseLotDetail lotDetail8 = (BaseLotDetail)request.getAttribute("lotDetail8");
	BaseLotDetail lotDetail9 = (BaseLotDetail)request.getAttribute("lotDetail9");
	
	BaseLotDetail lotDetail10 = (BaseLotDetail)request.getAttribute("lotDetail10");
	BaseLotDetail lotDetail11 = (BaseLotDetail)request.getAttribute("lotDetail11");
	BaseLotDetail lotDetail12 = (BaseLotDetail)request.getAttribute("lotDetail12");
	
	String lotid = lot.getM_strLotid();			/*批次属性代码*/
	String descr = lot.getM_strDescr();			/*描述*/
    String isdefault = lot.getM_strIsdefault();	/*是否默认批次属性*/
	String remark = lot.getM_strRemark();		/*备注*/
	
	String lotdetailid1 = lotDetail1.getM_strLotdetailid();		/*批次详细ID*/
	String attname1 = lotDetail1.getM_strAttname();				/*属性名称*/
	String attcode1 = lotDetail1.getM_strAttcode();				/*属性代码*/
	String lotatt_flag1 = lotDetail1.getM_strLotatt_flag();		/*输入控制*/
	String lottype1 = lotDetail1.getM_strLottype();				/*属性格式*/
	String lotsearch1 = lotDetail1.getM_strLotsearch();		/*属性选项项*/
	String lottypevalue1 = lotDetail1.getM_strLottypevalue();	/*下拉用的类型代码*/
	
	String lotdetailid2 = lotDetail2.getM_strLotdetailid();		/*批次详细ID*/
	String attname2 = lotDetail2.getM_strAttname();				/*属性名称*/
	String attcode2 = lotDetail2.getM_strAttcode();				/*属性代码*/
	String lotatt_flag2 = lotDetail2.getM_strLotatt_flag();		/*输入控制*/
	String lottype2 = lotDetail2.getM_strLottype();				/*属性格式*/
	String lotsearch2 = lotDetail2.getM_strLotsearch();		/*属性选项项*/
	String lottypevalue2 = lotDetail2.getM_strLottypevalue();	/*下拉用的类型代码*/
	
	String lotdetailid3 = lotDetail3.getM_strLotdetailid();		/*批次详细ID*/
	String attname3 = lotDetail3.getM_strAttname();				/*属性名称*/
	String attcode3 = lotDetail3.getM_strAttcode();				/*属性代码*/
	String lotatt_flag3 = lotDetail3.getM_strLotatt_flag();		/*输入控制*/
	String lottype3 = lotDetail3.getM_strLottype();				/*属性格式*/
	String lotsearch3 = lotDetail3.getM_strLotsearch();		/*属性选项项*/
	String lottypevalue3 = lotDetail3.getM_strLottypevalue();	/*下拉用的类型代码*/
	
	String lotdetailid4 = lotDetail4.getM_strLotdetailid();		/*批次详细ID*/
	String attname4 = lotDetail4.getM_strAttname();				/*属性名称*/
	String attcode4 = lotDetail4.getM_strAttcode();				/*属性代码*/
	String lotatt_flag4 = lotDetail4.getM_strLotatt_flag();		/*输入控制*/
	String lottype4 = lotDetail4.getM_strLottype();				/*属性格式*/
	String lotsearch4 = lotDetail4.getM_strLotsearch();		/*属性选项项*/
	String lottypevalue4 = lotDetail4.getM_strLottypevalue();	/*下拉用的类型代码*/
	
	String lotdetailid5 = lotDetail5.getM_strLotdetailid();		/*批次详细ID*/
	String attname5 = lotDetail5.getM_strAttname();				/*属性名称*/
	String attcode5 = lotDetail5.getM_strAttcode();				/*属性代码*/
	String lotatt_flag5 = lotDetail5.getM_strLotatt_flag();		/*输入控制*/
	String lottype5 = lotDetail5.getM_strLottype();				/*属性格式*/
	String lotsearch5 = lotDetail5.getM_strLotsearch();		/*属性选项项*/
	String lottypevalue5 = lotDetail5.getM_strLottypevalue();	/*下拉用的类型代码*/
	
	String lotdetailid6 = lotDetail6.getM_strLotdetailid();		/*批次详细ID*/
	String attname6 = lotDetail6.getM_strAttname();				/*属性名称*/
	String attcode6 = lotDetail6.getM_strAttcode();				/*属性代码*/
	String lotatt_flag6 = lotDetail6.getM_strLotatt_flag();		/*输入控制*/
	String lottype6 = lotDetail6.getM_strLottype();				/*属性格式*/
	String lotsearch6 = lotDetail6.getM_strLotsearch();		/*属性选项项*/
	String lottypevalue6 = lotDetail6.getM_strLottypevalue();	/*下拉用的类型代码*/
	
	String lotdetailid7 = lotDetail7.getM_strLotdetailid();		/*批次详细ID*/
	String attname7 = lotDetail7.getM_strAttname();				/*属性名称*/
	String attcode7 = lotDetail7.getM_strAttcode();				/*属性代码*/
	String lotatt_flag7 = lotDetail7.getM_strLotatt_flag();		/*输入控制*/
	String lottype7 = lotDetail7.getM_strLottype();				/*属性格式*/
	String lotsearch7 = lotDetail7.getM_strLotsearch();		/*属性选项项*/
	String lottypevalue7 = lotDetail7.getM_strLottypevalue();	/*下拉用的类型代码*/
	
	String lotdetailid8 = lotDetail8.getM_strLotdetailid();		/*批次详细ID*/
	String attname8 = lotDetail8.getM_strAttname();				/*属性名称*/
	String attcode8 = lotDetail8.getM_strAttcode();				/*属性代码*/
	String lotatt_flag8 = lotDetail8.getM_strLotatt_flag();		/*输入控制*/
	String lottype8 = lotDetail8.getM_strLottype();				/*属性格式*/
	String lotsearch8 = lotDetail8.getM_strLotsearch();		/*属性选项项*/
	String lottypevalue8 = lotDetail8.getM_strLottypevalue();	/*下拉用的类型代码*/
	
	String lotdetailid9 = lotDetail9.getM_strLotdetailid();		/*批次详细ID*/
	String attname9 = lotDetail9.getM_strAttname();				/*属性名称*/
	String attcode9 = lotDetail9.getM_strAttcode();				/*属性代码*/
	String lotatt_flag9 = lotDetail9.getM_strLotatt_flag();		/*输入控制*/
	String lottype9 = lotDetail9.getM_strLottype();				/*属性格式*/
	String lotsearch9 = lotDetail9.getM_strLotsearch();		/*属性选项项*/
	String lottypevalue9 = lotDetail9.getM_strLottypevalue();	/*下拉用的类型代码*/
	
	String lotdetailid10 = lotDetail10.getM_strLotdetailid();	/*批次详细ID*/
	String attname10 = lotDetail10.getM_strAttname();			/*属性名称*/
	String attcode10 = lotDetail10.getM_strAttcode();			/*属性代码*/
	String lotatt_flag10 = lotDetail10.getM_strLotatt_flag();	/*输入控制*/
	String lottype10 = lotDetail10.getM_strLottype();			/*属性格式*/
	String lotsearch10 = lotDetail10.getM_strLotsearch();	/*属性选项项*/
	String lottypevalue10 = lotDetail10.getM_strLottypevalue();	/*下拉用的类型代码*/
	
	String lotdetailid11 = lotDetail11.getM_strLotdetailid();	/*批次详细ID*/
	String attname11 = lotDetail11.getM_strAttname();			/*属性名称*/
	String attcode11 = lotDetail11.getM_strAttcode();			/*属性代码*/
	String lotatt_flag11 = lotDetail11.getM_strLotatt_flag();	/*输入控制*/
	String lottype11 = lotDetail11.getM_strLottype();			/*属性格式*/
	String lotsearch11 = lotDetail11.getM_strLotsearch();	/*属性选项项*/
	String lottypevalue11 = lotDetail11.getM_strLottypevalue();	/*下拉用的类型代码*/
	
	String lotdetailid12 = lotDetail12.getM_strLotdetailid();	/*批次详细ID*/
	String attname12 = lotDetail12.getM_strAttname();			/*属性名称*/
	String attcode12 = lotDetail12.getM_strAttcode();			/*属性代码*/
	String lotatt_flag12 = lotDetail12.getM_strLotatt_flag();	/*输入控制*/
	String lottype12 = lotDetail12.getM_strLottype();			/*属性格式*/
	String lotsearch12 = lotDetail12.getM_strLotsearch();	/*属性选项项*/
	String lottypevalue12 = lotDetail12.getM_strLottypevalue();	/*下拉用的类型代码*/
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
		/*批次*/
		var descr = document.getElementById("descr").value;
		var isdefault = document.getElementById("isdefault").checked?"Y":"N";
		var remark = document.getElementById("remark").value;
		
		/*批次详细*/
		var lotdetailid1 = document.getElementById("lotdetailid1").value;
		var attname1 = document.getElementById("attname1").value;
		var attcode1 = document.getElementById("attcode1").value;
		var lotatt_flag1 = document.getElementById("lotatt_flag1").value;
		var lottype1 = document.getElementById("lottype1").value;
		var lotsearch1 = document.getElementById("lotsearch1").value;
		var lottypevalue1 = document.getElementById("lottypevalue1").value;

		var lotdetailid2 = document.getElementById("lotdetailid2").value;
		var attname2 = document.getElementById("attname2").value;
		var attcode2 = document.getElementById("attcode2").value;
		var lotatt_flag2 = document.getElementById("lotatt_flag2").value;
		var lottype2 = document.getElementById("lottype2").value;
		var lotsearch2 = document.getElementById("lotsearch2").value;
		var lottypevalue2 = document.getElementById("lottypevalue2").value;
		
		var lotdetailid3 = document.getElementById("lotdetailid3").value;
		var attname3 = document.getElementById("attname3").value;
		var attcode3 = document.getElementById("attcode3").value;
		var lotatt_flag3 = document.getElementById("lotatt_flag3").value;
		var lottype3 = document.getElementById("lottype3").value;
		var lotsearch3 = document.getElementById("lotsearch3").value;
		var lottypevalue3 = document.getElementById("lottypevalue3").value;
		
		var lotdetailid4 = document.getElementById("lotdetailid4").value;
		var attname4 = document.getElementById("attname4").value;
		var attcode4 = document.getElementById("attcode4").value;
		var lotatt_flag4 = document.getElementById("lotatt_flag4").value;
		var lottype4 = document.getElementById("lottype4").value;
		var lotsearch4 = document.getElementById("lotsearch4").value;
		var lottypevalue4 = document.getElementById("lottypevalue4").value;
		
		var lotdetailid5 = document.getElementById("lotdetailid5").value;
		var attname5 = document.getElementById("attname5").value;
		var attcode5 = document.getElementById("attcode5").value;
		var lotatt_flag5 = document.getElementById("lotatt_flag5").value;
		var lottype5 = document.getElementById("lottype5").value;
		var lotsearch5 = document.getElementById("lotsearch5").value;
		var lottypevalue5 = document.getElementById("lottypevalue5").value;
		
		var lotdetailid6 = document.getElementById("lotdetailid6").value;
		var attname6 = document.getElementById("attname6").value;
		var attcode6 = document.getElementById("attcode6").value;
		var lotatt_flag6 = document.getElementById("lotatt_flag6").value;
		var lottype6 = document.getElementById("lottype6").value;
		var lotsearch6 = document.getElementById("lotsearch6").value;
		var lottypevalue6 = document.getElementById("lottypevalue6").value;
		
		var lotdetailid7 = document.getElementById("lotdetailid7").value;
		var attname7 = document.getElementById("attname7").value;
		var attcode7 = document.getElementById("attcode7").value;
		var lotatt_flag7 = document.getElementById("lotatt_flag7").value;
		var lottype7 = document.getElementById("lottype7").value;
		var lotsearch7 = document.getElementById("lotsearch7").value;
		var lottypevalue7 = document.getElementById("lottypevalue7").value;
		
		var lotdetailid8 = document.getElementById("lotdetailid8").value;
		var attname8 = document.getElementById("attname8").value;
		var attcode8 = document.getElementById("attcode8").value;
		var lotatt_flag8 = document.getElementById("lotatt_flag8").value;
		var lottype8 = document.getElementById("lottype8").value;
		var lotsearch8 = document.getElementById("lotsearch8").value;
		var lottypevalue8 = document.getElementById("lottypevalue8").value;
		
		var lotdetailid9 = document.getElementById("lotdetailid9").value;
		var attname9 = document.getElementById("attname9").value;
		var attcode9 = document.getElementById("attcode9").value;
		var lotatt_flag9 = document.getElementById("lotatt_flag9").value;
		var lottype9 = document.getElementById("lottype9").value;
		var lotsearch9 = document.getElementById("lotsearch9").value;
		var lottypevalue9 = document.getElementById("lottypevalue9").value;
		
		var lotdetailid10 = document.getElementById("lotdetailid10").value;
		var attname10 = document.getElementById("attname10").value;
		var attcode10 = document.getElementById("attcode10").value;
		var lotatt_flag10 = document.getElementById("lotatt_flag10").value;
		var lottype10 = document.getElementById("lottype10").value;
		var lotsearch10 = document.getElementById("lotsearch10").value;
		var lottypevalue10 = document.getElementById("lottypevalue10").value;
		
		var lotdetailid11 = document.getElementById("lotdetailid11").value;
		var attname11 = document.getElementById("attname11").value;
		var attcode11 = document.getElementById("attcode11").value;
		var lotatt_flag11 = document.getElementById("lotatt_flag11").value;
		var lottype11 = document.getElementById("lottype11").value;
		var lotsearch11 = document.getElementById("lotsearch11").value;
		var lottypevalue11 = document.getElementById("lottypevalue11").value;
		
		var lotdetailid12 = document.getElementById("lotdetailid12").value;
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

		condition = "&lotid=<%=lotid%>" + "&descr=" + descr + "&isdefault=" + isdefault + "&remark=" + remark 
				 
				 + "&lotdetailid1=" + lotdetailid1 + "&attname1=" + attname1 + "&attcode1=" + attcode1 + "&lotatt_flag1=" + lotatt_flag1 
				 + "&lottype1=" + lottype1 + "&lotsearch1=" + lotsearch1 + "&lottypevalue1=" + lottypevalue1
				 
				 + "&lotdetailid2=" + lotdetailid2 + "&attname2=" + attname2 + "&attcode2=" + attcode2 + "&lotatt_flag2=" + lotatt_flag2 
				 + "&lottype2=" + lottype2 + "&lotsearch2=" + lotsearch2 + "&lottypevalue2=" + lottypevalue2
				 
				 + "&lotdetailid3=" + lotdetailid3 + "&attname3=" + attname3 + "&attcode3=" + attcode3 + "&lotatt_flag3=" + lotatt_flag3 
				 + "&lottype3=" + lottype3 + "&lotsearch3=" + lotsearch3 + "&lottypevalue3=" + lottypevalue3
				 
				 + "&lotdetailid4=" + lotdetailid4 + "&attname4=" + attname4 + "&attcode4=" + attcode4 + "&lotatt_flag4=" + lotatt_flag4 
				 + "&lottype4=" + lottype4 + "&lotsearch4=" + lotsearch4 + "&lottypevalue4=" + lottypevalue4
				 
				 + "&lotdetailid5=" + lotdetailid5 + "&attname5=" + attname5 + "&attcode5=" + attcode5 + "&lotatt_flag5=" + lotatt_flag5 
				 + "&lottype5=" + lottype5 + "&lotsearch5=" + lotsearch5 + "&lottypevalue5=" + lottypevalue5
				 
				 + "&lotdetailid6=" + lotdetailid6 + "&attname6=" + attname6 + "&attcode6=" + attcode6 + "&lotatt_flag6=" + lotatt_flag6 
				 + "&lottype6=" + lottype6 + "&lotsearch6=" + lotsearch6 + "&lottypevalue6=" + lottypevalue6
				 
				 + "&lotdetailid7=" + lotdetailid7 + "&attname7=" + attname7 + "&attcode7=" + attcode7 + "&lotatt_flag7=" + lotatt_flag7 
				 + "&lottype7=" + lottype7 + "&lotsearch7=" + lotsearch7 + "&lottypevalue7=" + lottypevalue7
				 
				 + "&lotdetailid8=" + lotdetailid8 + "&attname8=" + attname8 + "&attcode8=" + attcode8 + "&lotatt_flag8=" + lotatt_flag8 
				 + "&lottype8=" + lottype8 + "&lotsearch8=" + lotsearch8 + "&lottypevalue8=" + lottypevalue8
				 
				 + "&lotdetailid9=" + lotdetailid9 + "&attname9=" + attname9 + "&attcode9=" + attcode9 + "&lotatt_flag9=" + lotatt_flag9 
				 + "&lottype9=" + lottype9 + "&lotsearch9=" + lotsearch9 + "&lottypevalue9=" + lottypevalue9
				 
				 + "&lotdetailid10=" + lotdetailid10 + "&attname10=" + attname10 + "&attcode10=" + attcode10 + "&lotatt_flag10=" + lotatt_flag10 
				 + "&lottype10=" + lottype10 + "&lotsearch10=" + lotsearch10 + "&lottypevalue10=" + lottypevalue10
				 
				 + "&lotdetailid11=" + lotdetailid11 + "&attname11=" + attname11 + "&attcode11=" + attcode11 + "&lotatt_flag11=" + lotatt_flag11 
				 + "&lottype11=" + lottype11 + "&lotsearch11=" + lotsearch11 + "&lottypevalue11=" + lottypevalue11
				  
				 + "&lotdetailid12=" + lotdetailid12  + "&attname12=" + attname12 + "&attcode12=" + attcode12 + "&lotatt_flag12=" + lotatt_flag12 
				 + "&lottype12=" + lottype12 + "&lotsearch12=" + lotsearch12 + "&lottypevalue12=" + lottypevalue12
				 ;
	
		window.returnValue = condition;
		window.close();
	}
	
	//登陆页面
	function OnLoad()
	{
		//同步
		DWREngine.setAsync(false);
		selectTypes('', 'lotatt_flag', 'srkz', 12);
		selectTypes('', 'lottype', 'sxgs', 12);
		selectTypes('', 'lotsearch', 'sxcxfs', 12);
		DWREngine.setAsync(true);
		
		document.getElementById("descr").value = '<%=descr==null?"":descr%>';
		document.getElementById("remark").value = '<%=remark==null?"":remark%>';
		
		document.getElementById("lotdetailid1").value = '<%=lotdetailid1%>';
		document.getElementById("attname1").value = '<%=attname1==null?"":attname1%>';
		document.getElementById("attcode1").value = '<%=attcode1==null?"":attcode1%>';
		document.getElementById("lotatt_flag1").value = "<%=lotatt_flag1%>";
		document.getElementById("lottype1").value = "<%=lottype1%>";
		document.getElementById("lotsearch1").value = "<%=lotsearch1%>";
		
		document.getElementById("lotdetailid2").value = '<%=lotdetailid2%>';
		document.getElementById("attname2").value = '<%=attname2==null?"":attname2%>';
		document.getElementById("attcode2").value = '<%=attcode2==null?"":attcode2%>';
		document.getElementById("lotatt_flag2").value = "<%=lotatt_flag2%>";
		document.getElementById("lottype2").value = "<%=lottype2%>";
		document.getElementById("lotsearch2").value = "<%=lotsearch2%>";
		
		document.getElementById("lotdetailid3").value = '<%=lotdetailid3%>';
		document.getElementById("attname3").value = '<%=attname3==null?"":attname3%>';
		document.getElementById("attcode3").value = '<%=attcode3==null?"":attcode3%>';
		document.getElementById("lotatt_flag3").value = "<%=lotatt_flag3%>";
		document.getElementById("lottype3").value = "<%=lottype3%>";
		document.getElementById("lotsearch3").value = "<%=lotsearch3%>";
		
		document.getElementById("lotdetailid4").value = '<%=lotdetailid4%>';
		document.getElementById("attname4").value = '<%=attname4==null?"":attname4%>';
		document.getElementById("attcode4").value = '<%=attcode4==null?"":attcode4%>';
		document.getElementById("lotatt_flag4").value = "<%=lotatt_flag4%>";
		document.getElementById("lottype4").value = "<%=lottype4%>";
		document.getElementById("lotsearch4").value = "<%=lotsearch4%>";
		
		document.getElementById("lotdetailid5").value = '<%=lotdetailid5%>';
		document.getElementById("attname5").value = '<%=attname5==null?"":attname5%>';
		document.getElementById("attcode5").value = '<%=attcode5==null?"":attcode5%>';
		document.getElementById("lotatt_flag5").value = "<%=lotatt_flag5%>";
		document.getElementById("lottype5").value = "<%=lottype5%>";
		document.getElementById("lotsearch5").value = "<%=lotsearch5%>";
		
		document.getElementById("lotdetailid6").value = '<%=lotdetailid6%>';
		document.getElementById("attname6").value = '<%=attname6==null?"":attname6%>';
		document.getElementById("attcode6").value = '<%=attcode6==null?"":attcode6%>';
		document.getElementById("lotatt_flag6").value = "<%=lotatt_flag6%>";
		document.getElementById("lottype6").value = "<%=lottype6%>";
		document.getElementById("lotsearch6").value = "<%=lotsearch6%>";
		
		document.getElementById("lotdetailid7").value = '<%=lotdetailid7%>';
		document.getElementById("attname7").value = '<%=attname7==null?"":attname7%>';
		document.getElementById("attcode7").value = '<%=attcode7==null?"":attcode7%>';
		document.getElementById("lotatt_flag7").value = "<%=lotatt_flag7%>";
		document.getElementById("lottype7").value = "<%=lottype7%>";
		document.getElementById("lotsearch7").value = "<%=lotsearch7%>";
		
		document.getElementById("lotdetailid8").value = '<%=lotdetailid8%>';
		document.getElementById("attname8").value = '<%=attname8==null?"":attname8%>';
		document.getElementById("attcode8").value = '<%=attcode8==null?"":attcode8%>';
		document.getElementById("lotatt_flag8").value = "<%=lotatt_flag8%>";
		document.getElementById("lottype8").value = "<%=lottype8%>";
		document.getElementById("lotsearch8").value = "<%=lotsearch8%>";
		
		document.getElementById("lotdetailid9").value = '<%=lotdetailid9%>';
		document.getElementById("attname9").value = '<%=attname9==null?"":attname9%>';
		document.getElementById("attcode9").value = '<%=attcode9==null?"":attcode9%>';
		document.getElementById("lotatt_flag9").value = "<%=lotatt_flag9%>";
		document.getElementById("lottype9").value = "<%=lottype9%>";
		document.getElementById("lotsearch9").value = "<%=lotsearch9%>";
		
		document.getElementById("lotdetailid10").value = '<%=lotdetailid10%>';
		document.getElementById("attname10").value = '<%=attname10==null?"":attname10%>';
		document.getElementById("attcode10").value = '<%=attcode10==null?"":attcode10%>';
		document.getElementById("lotatt_flag10").value = "<%=lotatt_flag10%>";
		document.getElementById("lottype10").value = "<%=lottype10%>";
		document.getElementById("lotsearch10").value = "<%=lotsearch10%>";
		
		document.getElementById("lotdetailid11").value = '<%=lotdetailid11%>';
		document.getElementById("attname11").value = '<%=attname11==null?"":attname11%>';
		document.getElementById("attcode11").value = '<%=attcode11==null?"":attcode11%>';
		document.getElementById("lotatt_flag11").value = "<%=lotatt_flag11%>";
		document.getElementById("lottype11").value = "<%=lottype11%>";
		document.getElementById("lotsearch11").value = "<%=lotsearch11%>";
		
		document.getElementById("lotdetailid12").value = '<%=lotdetailid12%>';
		document.getElementById("attname12").value = '<%=attname12==null?"":attname12%>';
		document.getElementById("attcode12").value = '<%=attcode12==null?"":attcode12%>';
		document.getElementById("lotatt_flag12").value = "<%=lotatt_flag12%>";
		document.getElementById("lottype12").value = "<%=lottype12%>";
		document.getElementById("lotsearch12").value = "<%=lotsearch12%>";
	}
-->
</script>
</head>

<body onLoad="OnLoad();">

 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：批次属性 -&gt; 修改批次属性</div></td>
   </tr>
 </table>
 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td class="yx1" width="100px" align="right"><span class="red">*</span>批次属性描述：</td>
     <td class="yx" width="250px"><input type="text" id="descr" size="30" maxlength="200"></td>
     <td class="yx1" width="100px" align="right">默认批次属性：</td>
     <td class="xx1"><input type="checkbox" id="isdefault" class="input_checkbox" <%if(isdefault.equals("Y")){%>checked<%}%>></td>
   </tr>
   <tr>
     <td class="y2" align="right">备注：</td>
     <td colspan="3"><textarea id="remark" cols="83" rows="3"></textarea></td>
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
     <td class="yx" align="center"><input type="hidden" id="lotdetailid1"><input type="text" id="attname1" size="20" style="background-color: #EDDFFD;color: #001F56;border: 1px solid #99CCFF;" readonly  maxlength="20"></td>
     <td class="yx" align="center"><input type="text" id="attcode1" size="15" maxlength="20" value="lotatt1" disabled></td>
     <td class="yx" align="center"><select id="lotatt_flag1"><option></option></select></td>
     <td class="yx" align="center"><select id="lottype1" style="background-color: #EDDFFD;width:120px;"  disabled><option></option></select></td>
     <td class="yx" align="center"><input type="text" id="lottypevalue1" size="15" maxlength="20" value="<%=lottypevalue1%>"></td>
     <td class="xx1" align="center"><select id="lotsearch1"><option></option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotdetailid2"><input type="text" id="attname2" size="20" maxlength="20"></td>
     <td class="yx" align="center"><input type="text" id="attcode2" size="15" maxlength="20" value="lotatt2" disabled></td>
     <td class="yx" align="center"><select id="lotatt_flag2"><option></option></select></td>
     <td class="yx" align="center"><select id="lottype2"><option></option></select></td>
     <td class="yx" align="center"><input type="text" id="lottypevalue2" size="15" maxlength="20" value="<%=lottypevalue2%>"></td>
     <td class="xx1" align="center"><select id="lotsearch2"><option></option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotdetailid3"><input type="text" id="attname3" size="20" maxlength="20"></td>
     <td class="yx" align="center"><input type="text" id="attcode3" size="15" maxlength="20" value="lotatt3" disabled></td>
     <td class="yx" align="center"><select id="lotatt_flag3" ><option></option></select></td>
     <td class="yx" align="center"><select id="lottype3"><option></option></select></td>
     <td class="yx" align="center"><input type="text" id="lottypevalue3" size="15" maxlength="20" value="<%=lottypevalue3%>"></td>
     <td class="xx1" align="center"><select id="lotsearch3"><option></option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotdetailid4"><input type="text" id="attname4" size="20" maxlength="20" ></td>
     <td class="yx" align="center"><input type="text" id="attcode4" size="15" maxlength="20" value="lotatt4" disabled></td>
     <td class="yx" align="center"><select id="lotatt_flag4" ><option></option></select></td>
     <td class="yx" align="center"><select id="lottype4" ><option></option></select></td>
     <td class="yx" align="center"><input type="text" id="lottypevalue4" size="15" maxlength="20" value="<%=lottypevalue4%>"></td>
     <td class="xx1" align="center"><select id="lotsearch4" ><option></option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotdetailid5"><input type="text" id="attname5" size="20" maxlength="20"></td>
     <td class="yx" align="center"><input type="text" id="attcode5" size="15" maxlength="20" value="lotatt5" disabled></td>
     <td class="yx" align="center"><select id="lotatt_flag5"><option></option></select></td>
     <td class="yx" align="center"><select id="lottype5"><option></option></select></td>
     <td class="yx" align="center"><input type="text" id="lottypevalue5" size="15" maxlength="20" value="<%=lottypevalue5%>"></td>
     <td class="xx1" align="center"><select id="lotsearch5"><option></option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotdetailid6"><input type="text" id="attname6" size="20" maxlength="20"></td>
     <td class="yx" align="center"><input type="text" id="attcode6" size="15" maxlength="20" value="lotatt6" disabled></td>
     <td class="yx" align="center"><select id="lotatt_flag6"><option></option></select></td>
     <td class="yx" align="center"><select id="lottype6"><option></option></select></td>
     <td class="yx" align="center"><input type="text" id="lottypevalue6" size="15" maxlength="20" value="<%=lottypevalue6%>"></td>
     <td class="xx1" align="center"><select id="lotsearch6"><option></option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotdetailid7"><input type="text" id="attname7" size="20" maxlength="20"></td>
     <td class="yx" align="center"><input type="text" id="attcode7" size="15" maxlength="20" value="lotatt7" disabled></td>
     <td class="yx" align="center"><select id="lotatt_flag7"><option></option></select></td>
     <td class="yx" align="center"><select id="lottype7"><option></option></select></td>
     <td class="yx" align="center"><input type="text" id="lottypevalue7" size="15" maxlength="20" value="<%=lottypevalue7%>"></td>
     <td class="xx1" align="center"><select id="lotsearch7"><option></option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotdetailid8"><input type="text" id="attname8" size="20" maxlength="20"></td>
     <td class="yx" align="center"><input type="text" id="attcode8" size="15" maxlength="20" value="lotatt8" disabled></td>
     <td class="yx" align="center"><select id="lotatt_flag8"><option></option></select></td>
     <td class="yx" align="center"><select id="lottype8"><option></option></select></td>
     <td class="yx" align="center"><input type="text" id="lottypevalue8" size="15" maxlength="20" value="<%=lottypevalue8%>"></td>
     <td class="xx1" align="center"><select id="lotsearch8"><option></option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotdetailid9"><input type="text" id="attname9" size="20" maxlength="20"></td>
     <td class="yx" align="center"><input type="text" id="attcode9" size="15" maxlength="20" value="lotatt9" disabled></td>
     <td class="yx" align="center"><select id="lotatt_flag9"><option></option></select></td>
     <td class="yx" align="center"><select id="lottype9"><option></option></select></td>
     <td class="yx" align="center"><input type="text" id="lottypevalue9" size="15" maxlength="20" value="<%=lottypevalue9%>"></td>
     <td class="xx1" align="center"><select id="lotsearch9"><option></option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotdetailid10"><input type="text" id="attname10" size="20" maxlength="20"></td>
     <td class="yx" align="center"><input type="text" id="attcode10" size="15" maxlength="20" value="lotatt10" disabled></td>
     <td class="yx" align="center"><select id="lotatt_flag10"><option></option></select></td>
     <td class="yx" align="center"><select id="lottype10"><option></option></select></td>
     <td class="yx" align="center"><input type="text" id="lottypevalue10" size="15" maxlength="20" value="<%=lottypevalue10%>"></td>
     <td class="xx1" align="center"><select id="lotsearch10"><option></option></select></td>
   </tr>
   <tr>
     <td class="yx" align="center"><input type="hidden" id="lotdetailid11"><input type="text" id="attname11" size="20" maxlength="20"></td>
     <td class="yx" align="center"><input type="text" id="attcode11" size="15" maxlength="20" value="lotatt11" disabled></td>
     <td class="yx" align="center"><select id="lotatt_flag11"><option></option></select></td>
     <td class="yx" align="center"><select id="lottype11"><option></option></select></td>
     <td class="yx" align="center"><input type="text" id="lottypevalue11" size="15" maxlength="20" value="<%=lottypevalue11%>"></td>
     <td class="xx1" align="center"><select id="lotsearch11"><option></option></select></td>
   </tr>
   <tr>
     <td class="x" align="center"><input type="hidden" id="lotdetailid12"><input type="text" id="attname12" size="20" maxlength="20"></td>
     <td class="x" align="center"><input type="text" id="attcode12" size="15" maxlength="20" value="lotatt12" disabled></td>
     <td class="x" align="center"><select id="lotatt_flag12"><option></option></select></td>
     <td class="x" align="center"><select id="lottype12"><option></option></select></td>
     <td class="x" align="center"><input type="text" id="lottypevalue12" size="15" maxlength="20" value="<%=lottypevalue12%>"></td>
     <td align="center"><select id="lotsearch12"><option></option></td>
   </tr>
 </table>
  
 <table><tr><td height="10"></td></tr></table> 
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