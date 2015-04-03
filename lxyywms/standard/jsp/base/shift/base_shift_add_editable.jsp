<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseShiftconfig" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseShift" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%
	//班次设置信息
	BaseShiftconfig shiftconfig = (BaseShiftconfig)request.getAttribute("shiftconfig");
	String timespace = shiftconfig.getTimespace();	//班次间隔时间
	String type = shiftconfig.getType();			//班次区分
	String inout = shiftconfig.getInout();			//入出库类型
	String op_man1 = shiftconfig.getOpman1();		//甲班负责人
	String onduty1 = shiftconfig.getOnduty1();		//甲班当班人员
	String op_man2 = shiftconfig.getOpman2();		//乙班负责人
	String onduty2 = shiftconfig.getOnduty2();		//乙班当班人员
	String op_man3 = shiftconfig.getOpman3();		//丙班负责人
	String onduty3 = shiftconfig.getOnduty3();		//丙班当班人员
	String op_man4 = shiftconfig.getOpman4();		//丁班负责人
	String onduty4 = shiftconfig.getOnduty4();		//丁班当班人员
	
	SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
	Date lasttime = null;
	
	//最后班次	
	BaseShift lastshift = (BaseShift)request.getAttribute("lastshift");  
	String lastshiftid = "";		//班次id
	String lastshiftname = "";		//班次名
	String lastshifttime = "";		//班次时间
	String lastdaynight = "";		//白班、夜班
	String lastshiftTurn = "";		//甲、乙、丙、丁
	String shifttime = "";
	String daynight = "";
	
	if(lastshift != null){
		lastshiftid = lastshift.getM_strShiftId();
		lastshiftname = lastshift.getM_strShiftName();
		lastshifttime = lastshift.getM_strShiftDate();
		lastdaynight = lastshift.getM_strDayNight();
		lastshiftTurn = lastshift.getM_strThreenum();
		
		lasttime = dfs.parse(lastshifttime);
		
		if(timespace.equals("12")){
	    	if(lastdaynight.equals("白")){
	    		//如果上一班次是白班，日期就一样 
	    		shifttime = lastshifttime;	
	    		daynight = "夜";
	    	}else if(lastdaynight.equals("夜")){
	    		//如果上一班次是夜班，日期就是下一天的
				shifttime = dfs.format(new Date(lasttime.getTime() + 1 * 24 * 60 * 60 * 1000)); 
				daynight = "白";
			}
		} else if(timespace.equals("24")){
			//时间间隔是24的话，加上24小时
			shifttime = dfs.format(new Date(lasttime.getTime() + 1 * 24 * 60 * 60 * 1000)); 
		}
	}
	
	//最后班次 入库
	BaseShift lastshiftin = (BaseShift)request.getAttribute("lastshiftin");  
	String lastshiftidin = "";		//班次id
	String lastshiftnamein = "";	//班次名
	String lastshifttimein = "";	//班次时间
	String lastdaynightin = "";		//白班、夜班
	String lastshiftTurnin = "";	//甲、乙、丙、丁
	String shifttimein = "";
	String daynightin = "";
	
	if(lastshiftin != null){
		lastshiftidin = lastshiftin.getM_strShiftId();
		lastshiftnamein = lastshiftin.getM_strShiftName();
		lastshifttimein = lastshiftin.getM_strShiftDate();
		lastdaynightin = lastshiftin.getM_strDayNight();
		lastshiftTurnin = lastshiftin.getM_strThreenum();

    	lasttime = dfs.parse(lastshifttimein);
    	
	    //如果是12小时班制
	    if(timespace.equals("12")){
	    	if(lastdaynightin.equals("白")){
	    		//如果上一班次是白班，日期就一样 
	    		shifttimein = lastshifttimein;	
	    		daynightin = "夜";
	    	}else if(lastdaynightin.equals("夜")){
	    		//如果上一班次是夜班，日期就是下一天的
				shifttimein = dfs.format(new Date(lasttime.getTime() + 1 * 24 * 60 * 60 * 1000)); 
				daynightin = "白";
			}
		} else if(timespace.equals("24")){
			//时间间隔是24的话，加上24小时
			shifttimein = dfs.format(new Date(lasttime.getTime() + 1 * 24 * 60 * 60 * 1000)); 
		}
	}
	
	//最后班次 出库
	BaseShift lastshiftout = (BaseShift)request.getAttribute("lastshiftout");  
	String lastshiftidout = "";		//班次id
	String lastshiftnameout = "";	//班次名
	String lastshifttimeout = "";	//班次时间
	String lastdaynightout = "";	//白班、夜班
	String lastshiftTurnout = "";	//甲、乙、丙、丁
	String shifttimeout = "";
	String daynightout = "";
	
	if(lastshiftout != null){
		lastshiftidout = lastshiftout.getM_strShiftId();		//班次id
		lastshiftnameout = lastshiftout.getM_strShiftName();	//班次名
		lastshifttimeout = lastshiftout.getM_strShiftDate();	//班次时间
		lastdaynightout = lastshiftout.getM_strDayNight();	//白班、夜班
		lastshiftTurnout = lastshiftout.getM_strThreenum();	//甲、乙、丙、丁
	
    	lasttime = dfs.parse(lastshifttimeout);
    	
	    //如果是12小时班制
	    if(timespace.equals("12")){
	    	if(lastdaynightout.equals("白")){
	    		//如果上一班次是白班，日期就一样 
	    		shifttimeout = lastshifttimeout;	
	    		daynightout = "夜";
	    	}else if(lastdaynightout.equals("夜")){
	    		//如果上一班次是夜班，日期就是下一天的
				shifttimeout = dfs.format(new Date(lasttime.getTime() + 1 * 24 * 60 * 60 * 1000)); 
				daynightout = "白";
			}
		} else if(timespace.equals("24")){
			//时间间隔是24的话，加上24小时
			shifttimeout = dfs.format(new Date(lasttime.getTime() + 1 * 24 * 60 * 60 * 1000)); 
		}
	}
%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/getTool.js"></script>
<script>
<!--
	//获取用户名
	function GetUser()
	{
		var result = showModalDialog('<%=request.getContextPath()%>/standard/jsp/common/common_user.jsp','','dialogWidth=700px;dialogHeight=440px');
		if(result != null && result.length > 0){
	 		var tem = result.split("|");
	 		document.getElementById("op_man_id").value = tem[0];
	 		document.getElementById("op_man_name").value = tem[2];
	 		}
	}
	
	//获取多个用户名
	function GetUsers(){
	     var result = showModalDialog('<%=request.getContextPath()%>/standard/jsp/common/common_users.jsp','','dialogWidth=700px;dialogHeight=440px');
	     var str = "";
	     if(result != null && result.length > 0){
	 		var tem = result.split("|");
	 		if(tem != null){
	 		 for(var i=0;i<tem.length;i++){
	 		   str += tem[i]+",";
	 		 }
	 		}
	 	}
	 	 if(str != null){
		   str = str.substring(0, str.length-1);
		 }
		 if(document.getElementById("onduty").value == ""){
	 	 	document.getElementById("onduty").value += str;
	 	 }else{
	 	 	document.getElementById("onduty").value += "," + str;
	 	 }
	}
	
	//当班次发生改变时执行的操作
	function shiftOnchage(){
		
		//取得对应班次的负责人和当班人员
		setOpman(document.getElementById("shift").value);
	}
	
	//当入出库类型发生改变时执行的操作
	function inoutOnchange(){
	
	    var in_out_type = document.getElementById("in_out_type").value;
	    var obj = document.getElementById("shift");
	    var obj1 = document.getElementsByName("daynight"); 
	    
	    if(in_out_type != null && in_out_type == '1'){ //入库类型的班次列表
	    	
	    	//最后班次
	    	document.getElementById("lastShiftId").value = "<%=lastshiftidin%>";	
	    	document.getElementById("lastShiftName").value = "<%=lastshiftnamein%>";
	    	
	    	//白/夜
			if("<%=daynightin%>" == "白"){
				obj1[0].checked = true;
			}else if("<%=daynightin%>" == "夜"){
				obj1[1].checked = true;
			}
	    	
	    	//班次日期
	    	document.getElementById("indate").value = "<%=shifttimein%>";
	    	
	    	//设置班次的默认值(最后一个班次的下一班次)
			for(i=0; i<obj.options.length; i++){
				if(obj.options[i].value == "<%=lastshiftTurnin%>"){   
					if(i == obj.options.length-1){
						obj.options[1].selected = true;
					} else {  
						obj.options[i+1].selected = true;
					}
					break;
				}
			}
			
			//设置对应班次的负责人和当班人员
			setOpman(document.getElementById("shift").value);
	    	
	    }else if(in_out_type != null && in_out_type == '2'){ //出库类型的班次列表
	    
	    	//最后班次
	    	document.getElementById("lastShiftId").value = "<%=lastshiftidout%>";
	    	document.getElementById("lastShiftName").value = "<%=lastshiftnameout%>";
	    	
	    	//白/夜
			if("<%=daynightout%>" == "白"){
				obj1[0].checked = true;
			}else if("<%=daynightout%>" == "夜"){
				obj1[1].checked = true;
			}
	    	
	    	//班次日期
	    	document.getElementById("indate").value = "<%=shifttimeout%>";
	    	
	    	//设置班次的默认值(最后一个班次的下一班次)
			for(i=0; i<obj.options.length; i++){
				if(obj.options[i].value == "<%=lastshiftTurnout%>"){   
					if(i == obj.options.length-1){
						obj.options[1].selected = true;
					} else {  
						obj.options[i+1].selected = true;
					}
					break;
				}
			}
			
			//设置对应班次的负责人和当班人员
			setOpman(document.getElementById("shift").value);
	    	
	    }else{
	    	//最后班次
	    	document.getElementById("lastShiftId").value = "";
	    	document.getElementById("lastShiftName").value = "";
	    	
	    	//白/夜
	    	
	    	//班次日期
	    	document.getElementById("indate").value = "";
	    	
	    	//班次
	    	obj.options[0].selected = true;
	    	
	    	//设置对应班次的负责人和当班人员
			setOpman(document.getElementById("shift").value);
	    	
	    }
	}
	
	//设置对应班次的负责人和当班人员（班次设置里设置好的值）
	function setOpman(lastshiftTurn){
	
		var opman = "";
		var onduty = "";
		
		if(lastshiftTurn=="甲"){
			opman = "<%=op_man1%>";
	 		onduty = "<%=onduty1%>";
	 		
		}else if(lastshiftTurn=="乙"){
			opman = "<%=op_man2%>";
	 		onduty = "<%=onduty2%>";
	 		
		}else if(lastshiftTurn=="丙"){
			opman = "<%=op_man3%>";
	 		onduty = "<%=onduty3%>";
	 		
		}else if(lastshiftTurn=="丁"){
			opman = "<%=op_man4%>";
	 		onduty = "<%=onduty4%>";
		}
		
		document.getElementById("op_man_name").value = opman;
	 	document.getElementById("onduty").value = onduty;
	}
	
		
	//增加  
	function onSave()
	{
		var lastShiftId = document.getElementById("lastShiftId").value;
		var op_man_name = document.getElementById("op_man_name").value;
		var shift = document.getElementById("shift").value;
		var indate = document.getElementById("indate").value;
		var onduty = document.getElementById("onduty").value;
		var daynight = "";
		var shiftname = indate;
		var in_out_type = 0;
		
		//入出库类型
		if("<%=inout%>"=="Y"){
			in_out_type = document.getElementById("in_out_type").value;			
			if(in_out_type == "" || in_out_type.length < 1)
			{
				alert("【入出库类型】不能为空！");
				return;
			}
		}
		
		//白班 夜班
		if("<%=timespace%>"==12){
			var objRadio = document.getElementsByName("daynight"); 
			if(objRadio[0].checked){
				daynight = objRadio[0].value;
			}else if(objRadio[1].checked){
				daynight = objRadio[1].value;
			}
			
			if(daynight == "" || daynight.length < 1)
			{
				alert("【白/夜】不能为空！");
				return;
			}
			shiftname += daynight;
		}

		if(indate == "" || indate.length < 1)
		{
			alert("【班次日期】不能为空！");
			return;
		}
		
		if(shift == "" || shift.length < 1)
		{
			alert("【班次】不能为空！");
			return;
		}
		shiftname += shift;
		
		if(op_man_name == "" || op_man_name.length < 1)
		{
			alert("【负责人】不能为空！");
			return;
		}

		if(onduty == "" || onduty.length < 1)
		{
			alert("【当班人员】不能为空！");
			return;
		}
		
		var backmsg = "&lastShiftId=" + lastShiftId + "&daynight=" + daynight + "&indate=" + indate + "&shift=" + shift 
			+ "&op_man_name=" + op_man_name + "&onduty=" + onduty + "&shiftname=" + shiftname + "&inouttype=" + in_out_type;
		
		window.returnValue = backmsg;
		window.close();
	}
	
	function onLoad(){
	
		var obj = document.getElementById("shift");
	    var obj1 = document.getElementsByName("daynight"); 
		var shiftTurn = "<%=lastshiftTurn%>";
		var inouttype = "<%=inout%>";
		var daynight = "<%=daynight%>";
		
		//不区分入出库时候设置默认值
		if(inouttype == "N"){
			//最后班次
	    	document.getElementById("lastShiftId").value = "<%=lastshiftid%>";	
	    	document.getElementById("lastShiftName").value = "<%=lastshiftname%>";
	    	
	    	//白/夜
			if(daynight == "白"){
				obj1[0].checked = true;
			}else if("<%=daynight%>" == "夜"){
				obj1[1].checked = true;
			}
	    	
	    	//班次日期
			document.getElementById("indate").value = "<%=shifttime%>";
	    	
	    	//班次
	    	if(shiftTurn == ""){
				obj.options[0].selected = true;
			}else{
				for(i=0; i<obj.options.length; i++){
					if(obj.options[i].value == shiftTurn){   
						if(i == obj.options.length-1){
							obj.options[1].selected = true;
						} else {  
							obj.options[i+1].selected = true;
						}
						break;
					}
				}
			}
			
			//取得对应班次的负责人和当班人员
			setOpman(document.getElementById("shift").value);
		}
	}

-->
</script>
</head>

<body onload="onLoad()">

  <form name="myForm" method="post" action="">
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">当前位置：基本信息 -&gt; 班次管理 -&gt; 新增班次信息</div></td>
    </tr>
  </table>
 
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <%if(inout.equals("Y")) {%>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>入出类型：</td>
      <td class="xx1">
     	<select name="in_out_type" style="width:130px" onchange="inoutOnchange();">
	      <option value="">--请选择--</option>
	      <option value="1">入库</option>
	      <option value="2">出库</option>
	    </select>
	  </td>
    </tr>
    <%}%>
    <tr>
      <td class="yx1" align="right" width="100px">最后班次：</td>
      <td class="xx1">
        <input name="lastShiftId" type="hidden" size="20" value="">
     	<input name="lastShiftName" type="text" size="20" value="" readonly class="input_readonly"></td>
    </tr> 
    <%if(timespace.equals("12")) {%>
    <tr id="trTime">
      <td class="yx1" align="right"><span class="red">*</span>白/夜：</td>
      <td class="xx1">
	    <input type="radio" name="daynight" value="白"/>白
	    &nbsp;<input type="radio" name="daynight" value="夜" />夜
	  </td>
    </tr>
    <%}%>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>班次日期：</td>
      <td class="xx1"><input name="indate" type="text" size="20" onfocus="calendar();" class="Wdate" class="input_readonly" readonly value=""></td>
    </tr>   
    <tr>
	  <td class="yx1" align="right"><span class="red">*</span>班次：</td>
      <td class="xx1">
     	<select name="shift" style="width:130px" onChange="shiftOnchage()">
     	  <option value="">--请选择--</option>
     	  <option value="甲">甲</option>
     	  <option value="乙">乙</option>
     	  <%if(Integer.parseInt(type)>2) {%><option value="丙">丙</option><%}%>
     	  <%if(Integer.parseInt(type)>3) {%><option value="丁">丁</option><%}%>
     	</select>
      </td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>负责人：</td>
      <td class="xx1">
        <input name="op_man_id" type="hidden"><input name="op_man_name" type="text" size="16" readonly>
        <input name="moreBtn" type="button" class="button_select" value="…" onClick="GetUser()">
      </td>
    </tr>
	<tr>
	  <td class="yx1" align="right"><span class="red">*</span>当班人员： </td>
	  <td class="xx1" align="left">
	  	<textarea rows="2" style="width:300px;height:40px" id="onduty"></textarea>
	  	<input name="moreBtn" type="button" class="button_select" value="…" onClick="GetUsers()"></td>   
	</tr>	
    <tr>
      <td height="27" colspan="2" align="center">
        <input onClick="onSave()"type="button" name="button1" value="保存" class="BUTTON_STYLE1">
        <input type="reset" name="resetDetailBtn" value="&nbsp;&nbsp;&nbsp;重置" class="button_reset">&nbsp;
        <input type="button" onClick="window.close()" name="resetBtn" value="关闭" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
  </form>
   
</body>
</html>

