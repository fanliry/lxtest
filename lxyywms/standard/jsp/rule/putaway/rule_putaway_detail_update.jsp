<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.rule.RulePutawayDetail" %>
<%
	RulePutawayDetail putawayDetail = (RulePutawayDetail)request.getAttribute("putawayDetailRule");
	String warehouseid = (String)request.getAttribute("warehouseid");
	
	String putawaydetailid = putawayDetail.getPutawaydetailid();//上架规则详细ID
	String putawayid = putawayDetail.getPutawayid();  			//上架规则ID
	int sort = putawayDetail.getSort();							//优先顺位
	String enableflag = putawayDetail.getEnableflag();			//是否激活
	String ruleconfigid = putawayDetail.getRuleconfigid(); 		//规则配置ID
	String tozone = putawayDetail.getTozone();					//目标库区
	String tolocationid = putawayDetail.getTolocationid();		//目标库位
	String tolocationname = putawayDetail.getTolocationname();	//目标库位
				
	String loc_restrict = putawayDetail.getLoc_restrict();		//库位限制
		
	String loc_usage1 = putawayDetail.getLoc_usage1();			//库位使用
	String loc_usage2 = putawayDetail.getLoc_usage2();
	String loc_usage3 = putawayDetail.getLoc_usage3();
	String loc_usage4 = putawayDetail.getLoc_usage4();
	String loc_usage5 = putawayDetail.getLoc_usage5();
		
	String loc_handle1 = putawayDetail.getLoc_handle1();		//存储类型
	String loc_handle2 = putawayDetail.getLoc_handle2();
	String loc_handle3 = putawayDetail.getLoc_handle3();
	String loc_handle4 = putawayDetail.getLoc_handle4();
	String loc_handle5 = putawayDetail.getLoc_handle5();

	String remark = putawayDetail.getRemark();					//备注
	
	//批次属性值
	String lotid = putawayDetail.getLotid();
	String lotatt1 = putawayDetail.getLotatt1();			
	String lotatt2 = putawayDetail.getLotatt2();
	String lotatt3 = putawayDetail.getLotatt3();
	String lotatt4 = putawayDetail.getLotatt4();
	String lotatt5 = putawayDetail.getLotatt5();
	String lotatt6 = putawayDetail.getLotatt6(); 
	String lotatt7 = putawayDetail.getLotatt7();
	String lotatt8 = putawayDetail.getLotatt8();
	String lotatt9 = putawayDetail.getLotatt9();
	String lotatt10 = putawayDetail.getLotatt10();
	String lotatt11 = putawayDetail.getLotatt11();
	String lotatt12 = putawayDetail.getLotatt12();
			  
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
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>
<script type="text/javascript">
<!--
	//保存
	function saveDetailData()
	{
		var ruleconfigid = document.getElementById("ruleconfigid").value;		//规则方法
		var enableflag = document.getElementById("enableflag").checked?"Y":"N";	//是否激活
		
		var tozone = document.getElementById("tozone").value;				//目标库区
        var tolocationid = document.getElementById("cargospace_id").value;	//目标库位
        
        var loc_restrict = document.getElementById("loc_restrict").value;	//库位限制1
        
        var loc_usage1 = document.getElementById("loc_usage1").value;	//库位使用
        var loc_usage2 = document.getElementById("loc_usage2").value;
        var loc_usage3 = document.getElementById("loc_usage3").value;
        var loc_usage4 = document.getElementById("loc_usage4").value;
        var loc_usage5 = document.getElementById("loc_usage5").value;
        					
        var loc_handle1 = document.getElementById("loc_handle1").value;	//存储类型
        var loc_handle2 = document.getElementById("loc_handle2").value;
        var loc_handle3 = document.getElementById("loc_handle3").value;
        var loc_handle4 = document.getElementById("loc_handle4").value;
        var loc_handle5 = document.getElementById("loc_handle5").value;
        
        var lotid = document.getElementById("lotid").value;			
		var lotatt1 = document.getElementById("lotatt1").value;	//批次
		var lotatt2 = document.getElementById("lotatt2").value;
		var lotatt3 = document.getElementById("lotatt3").value;
		var lotatt4 = document.getElementById("lotatt4").value;
		var lotatt5 = document.getElementById("lotatt5").value;
		var lotatt6 = document.getElementById("lotatt6").value;
		var lotatt7 = document.getElementById("lotatt7").value;
		var lotatt8 = document.getElementById("lotatt8").value;
		var lotatt9 = document.getElementById("lotatt9").value;
		var lotatt10 = document.getElementById("lotatt10").value;
		var lotatt11 = document.getElementById("lotatt11").value;
		var lotatt12 = document.getElementById("lotatt12").value;
		
		var remark = document.getElementById("remark").value;	//备注
       	
		if(ruleconfigid == "" || ruleconfigid.length <1)  
       	{
       		alert("【规则方法】不能为空!");
       		return; 
       	}
       	
       	if(lotatt4 != null && lotatt4.length > 0){
		
			if(strlen(lotatt4) > 50){
				alert("【批次4】过长！");
				return;
			}
		}
		if(lotatt5 != null && lotatt5.length > 0){
		
			if(strlen(lotatt5) > 50){
				alert("【批次5】过长！");
				return;
			}
		}
		if(lotatt6 != null && lotatt6.length > 0){
		
			if(strlen(lotatt6) > 50){
				alert("【批次6】过长！");
				return;
			}
		}
		if(lotatt7 != null && lotatt7.length > 0){
		
			if(strlen(lotatt7) > 50){
				alert("【批次7】过长！");
				return;
			}
		}
		if(lotatt8 != null && lotatt8.length > 0){
		
			if(strlen(lotatt8) > 50){
				alert("【批次8】过长！");
				return;
			}
		}
		if(lotatt9 != null && lotatt9.length > 0){
		
			if(strlen(lotatt9) > 50){
				alert("【批次9】过长！");
				return;
			}
		}
		if(lotatt10 != null && lotatt10.length > 0){
		
			if(strlen(lotatt10) > 50){
				alert("【批次10】过长！");
				return;
			}
		}
		if(lotatt11 != null && lotatt11.length > 0){
		
			if(strlen(lotatt11) > 50){
				alert("【批次11】过长！");
				return;
			}
		}
		if(lotatt12 != null && lotatt12.length > 0){
		
			if(strlen(lotatt12) > 50){
				alert("【批次12】过长！");
				return;
			}
		}
		
		if(remark != null && remark.length > 0){
		
			if(strlen(remark) > 200){
				alert("【备注】过长！");
				return;
			}
		}
		
		var condition = "&putawayid=<%=putawayid%>&putawaydetailid=<%=putawaydetailid%>"
		    + "&ruleconfigid=" + ruleconfigid + "&enableflag=" + enableflag + "&remark=" + remark
			+ "&tozone=" + tozone + "&tolocationid=" + tolocationid
			//库位限制
			+ "&loc_restrict=" + loc_restrict
			//库位使用
			+ "&loc_usage1=" + loc_usage1 + "&loc_usage2=" + loc_usage2 + "&loc_usage3=" + loc_usage3 
			+ "&loc_usage4=" + loc_usage4 + "&loc_usage5=" + loc_usage5
			//存储类型
        	+ "&loc_handle1=" + loc_handle1 + "&loc_handle2=" + loc_handle2 + "&loc_handle3=" + loc_handle3 
			+ "&loc_handle4=" + loc_handle4 + "&loc_handle5=" + loc_handle5			
        	//批次	
			//+ "&lotid=" + lotid + "&lotatt1=" + lotatt1 + "&lotatt2=" + lotatt2 + "&lotatt3=" + lotatt3 + "&lotatt4=" + lotatt4
		    //+ "&lotatt5=" + lotatt5 + "&lotatt6=" + lotatt6 + "&lotatt7=" + lotatt7 + "&lotatt8=" + lotatt8 
		    //+ "&lotatt9=" + lotatt9 + "&lotatt10=" + lotatt10 + "&lotatt11=" + lotatt11 + "&lotatt12=" + lotatt12;
									
	    var msg = "<input type=hidden name='lotid' value='"+lotid+"'>"
				+ "<input type=hidden name='lotatt1' value='"+lotatt1+"'>"
				+ "<input type=hidden name='lotatt2' value='"+lotatt2+"'>"
				+ "<input type=hidden name='lotatt3' value='"+lotatt3+"'>"
				+ "<input type=hidden name='lotatt4' value='"+lotatt4+"'>"
				+ "<input type=hidden name='lotatt5' value='"+lotatt5+"'>"
				+ "<input type=hidden name='lotatt6' value='"+lotatt6+"'>"
				+ "<input type=hidden name='lotatt7' value='"+lotatt7+"'>"
				+ "<input type=hidden name='lotatt8' value='"+lotatt8+"'>"
				+ "<input type=hidden name='lotatt9' value='"+lotatt9+"'>"
				+ "<input type=hidden name='lotatt10' value='"+lotatt10+"'>"
				+ "<input type=hidden name='lotatt11' value='"+lotatt11+"'>"
				+ "<input type=hidden name='lotatt12' value='"+lotatt12+"'>";
		
		var array = new Array();	
		array[0] = condition;
		array[1] = msg;
        window.returnValue = array;
		window.close();
	}
	
	//选择批次属性后
	function getLotAtts(obj){

		lotTool.getLotDetails(obj.value, {callback:function(data){
			if(data !=null){
				var tempstr;
				for(var i=0; i<data.length; i++){
					tempstr = "lotname" + (i+1);
					document.getElementById(tempstr).innerHTML = data[i].m_strAttname + "：";		//批次属性名称
					if(data[i].m_strLotatt_flag == '2'){	//禁用
						document.getElementById("lotatt" + (i+1)).disabled = true;
						document.getElementById("lotatt" + (i+1)).value = "";
					}else{
						document.getElementById("lotatt" + (i+1)).disabled = false;
					}
				}
			}}});
	}
	
	//页面登陆
	function OnLoad(){
	
		//同步
		DWREngine.setAsync(false);
		selectObject("<%=ruleconfigid%>", "ruleconfigid", "31");			//规则方法
		selectAreaList("<%=tozone%>", "tozone", "<%=warehouseid%>", "1");	//目标库区
		
		selectType("<%=loc_restrict%>", "loc_restrict", "kwxz");	//库位限制
		selectTypes("", "loc_usage", "kwsy", 5);					//库位使用
		selectTypes("", "loc_handle", "cclx", 5);					//存储类型
		selectLot("<%=lotid%>", "lotid");							//批次属性
		DWREngine.setAsync(true);
		
		//库位使用
		document.getElementById("loc_usage1").value = "<%=loc_usage1%>";
		document.getElementById("loc_usage2").value = "<%=loc_usage2%>";
		document.getElementById("loc_usage3").value = "<%=loc_usage3%>";
		document.getElementById("loc_usage4").value = "<%=loc_usage4%>";
		document.getElementById("loc_usage5").value = "<%=loc_usage5%>";
		
		//存储类型
		document.getElementById("loc_handle1").value = "<%=loc_handle1%>";
		document.getElementById("loc_handle2").value = "<%=loc_handle2%>";
		document.getElementById("loc_handle3").value = "<%=loc_handle3%>";
		document.getElementById("loc_handle4").value = "<%=loc_handle4%>";
		document.getElementById("loc_handle5").value = "<%=loc_handle5%>";
		
		getLotAtts(document.getElementById("lotid"));
	}
-->
</script>
</head>

<body onLoad="OnLoad()">

  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：规则管理 -&gt; 上架规则 -&gt; 修改上架规则详细</div></td>
   </tr>
 </table>

<table><tr><td height="10"></td></tr></table> 
 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td width="100px" class="yx1" align="right">优先顺位：</td>
     <td class="yx"><input type="text" id="sort" size="2" maxlength="2" class="input_readonly" readonly value="<%=sort%>">
       <input type="checkbox" id="enableflag" class="input_checkbox" <%if(enableflag.equals("Y")){%>checked<%}%>>有效</td>
     <td class="yx1" align="right"><span class="red">*</span>规则方法：</td>
     <td class="xx1"><select id="ruleconfigid"><option></option></select></td>
   </tr>
   <tr>
   	 <td class="y1" align="right">目标库区：</td>
     <td class="x"><select id="tozone" style="width:130px;"><option></option></select></td>
     <td class="y1" align="right">目标库位：</td>
     <td>
       	<input type="hidden" id="cargospace_id" value="<%=tolocationid==null?"":tolocationid%>">
     	<input type="text" id="cargospace_name" size="14" readonly value="<%=tolocationname==null?"":tolocationname%>">
       	<input onClick="openCargospace('<%=request.getContextPath()%>/standard/jsp/common/common_cargospace.jsp?warehouseid=<%=warehouseid%>&whAreaId='+document.getElementById('tozone').value,'850','550');" 
       		type="button" value="…" class="button_select">
     </td>
   </tr>
 </table>
 <table><tr><td height="10"></td></tr></table>  
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td width="100px" class="y1" align="right">库位限制：</td>
     <td colspan="5"><select id="loc_restrict"><option></option></select></td>
   </tr>
 </table>
 <table><tr><td height="10"></td></tr></table>  
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td width="100px" class="yx1" align="right">库位使用：</td>
     <td class="yx"><select id="loc_usage1" style="width:130px;"><option></option></select></td>
     <td class="yx"><select id="loc_usage2" style="width:130px;"><option></option></select></td>
     <td class="yx"><select id="loc_usage3" style="width:130px;"><option></option></select></td>
     <td class="yx"><select id="loc_usage4" style="width:130px;"><option></option></select></td>
     <td class="xx1"><select id="loc_usage5" style="width:130px;"><option></option></select></td>
   </tr>
   <tr>
     <td class="y1" align="right">存储类型：</td>
     <td class="x"><select id="loc_handle1" style="width:130px;"><option></option></select></td>
     <td class="x"><select id="loc_handle2" style="width:130px;"><option></option></select></td>
     <td class="x"><select id="loc_handle3" style="width:130px;"><option></option></select></td>
     <td class="x"><select id="loc_handle4" style="width:130px;"><option></option></select></td>
     <td><select id="loc_handle5" style="width:130px;"><option></option></select></td>
   </tr>
 </table>
 <table><tr><td height="10"></td></tr></table>  
 <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td class="yx1" width="100px" align="right">批次属性：</td>
     <td class="xx1" colspan="5"><select id="lotid" onchange="getLotAtts(this)"><option value=""></option></select></td>
   </tr>
   <tr>
     <td class="yx1" width="100px" align="right" id="lotname1">批次属性1：</td>
     <td class="yx"><input type="text" id="lotatt1" size="20" maxlength="50" value="<%=lotatt1==null?"":lotatt1%>"></td>
     <td class="yx1" width="100px" align="right" id="lotname2">批次属性2：</td>
     <td class="yx"><input type="text" id="lotatt2" size="20" maxlength="50" value="<%=lotatt2==null?"":lotatt2%>"></td>
     <td class="yx1" width="100px" align="right" id="lotname3">批次属性3：</td>
     <td class="xx1"><input type="text" id="lotatt3" size="20" maxlength="50" value="<%=lotatt3==null?"":lotatt3%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right" id="lotname4">批次属性4：</td>
     <td class="yx"><input type="text" id="lotatt4" size="20" maxlength="50" value="<%=lotatt4==null?"":lotatt4%>"></td>
     <td class="yx1" align="right" id="lotname5">批次属性5：</td>
     <td class="yx"><input type="text" id="lotatt5" size="20" maxlength="50" value="<%=lotatt5==null?"":lotatt5%>"></td>
     <td class="yx1" align="right" id="lotname6">批次属性6：</td>
     <td class="xx1"><input type="text" id="lotatt6" size="20" maxlength="50" value="<%=lotatt6==null?"":lotatt6%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right" id="lotname7">批次属性7：</td>
     <td class="yx"><input type="text" id="lotatt7" size="20" maxlength="50" value="<%=lotatt7==null?"":lotatt7%>"></td>
     <td class="yx1" align="right" id="lotname8">批次属性8：</td>
     <td class="yx"><input type="text" id="lotatt8" size="20" maxlength="50" value="<%=lotatt8==null?"":lotatt8%>"></td>
     <td class="yx1" align="right" id="lotname9">批次属性9：</td>
     <td class="xx1"><input type="text" id="lotatt9" size="20" maxlength="50" value="<%=lotatt9==null?"":lotatt9%>"></td>
   </tr>
   <tr>
     <td class="y1" align="right" id="lotname10">批次属性10：</td>
     <td class="x"><input type="text" id="lotatt10" size="20" maxlength="50" value="<%=lotatt10==null?"":lotatt10%>"></td>
     <td class="y1" align="right" id="lotname11">批次属性11：</td>
     <td class="x"><input type="text" id="lotatt11" size="20" maxlength="50" value="<%=lotatt11==null?"":lotatt11%>"></td>
     <td class="y1" align="right" id="lotname12">批次属性12：</td>
     <td><input type="text" id="lotatt12" size="20" maxlength="50" value="<%=lotatt12==null?"":lotatt12%>"></td>
   </tr>
 </table>
 <table><tr><td height="10"></td></tr></table> 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td width="100px" class="yx1" align="right">备注：</td>
     <td class="xx1" colspan="5"><textarea id="remark" cols="83" rows="3"><%=remark==null?"":remark%></textarea></td>
   </tr>
 </table>
 <table><tr><td height="10"></td></tr></table>
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">  
   <tr>
     <td height="27" align="center">
        <input type="button" onclick="saveDetailData()" value="&nbsp;&nbsp;&nbsp;保存明细" class="button_add">&nbsp; 
        <input type="button" onClick="window.close()" id="resetBtn" value="关闭" class="BUTTON_STYLE1">
     </td>
   </tr>
 </table>
 
</body>
</html>