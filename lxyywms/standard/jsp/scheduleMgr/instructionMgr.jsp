<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%
	String strTime = StrTools.getCurrDateTime(8);
	String strWarehouseId = (String) request.getSession(false).getAttribute("warehouseid");
	
	HashMap hsPopedom = null;
	boolean commdScheduleQuery = false; 
	boolean commdScheduleCancel = false; 
	boolean commdScheduleFinish = false;
	boolean commdSchedule = false;

	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("commdScheduleQuery") != null){
			commdScheduleQuery = true;
		}
		if(hsPopedom.get("commdScheduleCancel") != null){
			commdScheduleCancel = true;
		}
		if(hsPopedom.get("commdScheduleFinish") != null){
			commdScheduleFinish = true;
		}
		if(hsPopedom.get("commdSchedule") != null){
			commdSchedule = true;
		}
    }
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>浙江刚玉物流仓库管理系统</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
 	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script> 
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<script>
    var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
    var condition = null;
    var rank;
    var lastRank;
   //检查数量是否为数字
	function IsNum(num)
	{ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
	
	function selectIns()
	{
		var scheduleTaskId = document.getElementById("scheduleTaskId").value;
		var scheduleType = document.getElementById("scheduleType").value;
		var Fplatoon = document.getElementById("Fplatoon").value;
		var Fcolumn = document.getElementById("Fcolumn").value;
		var Ffloor = document.getElementById("Ffloor").value;
		var Tplatoon = document.getElementById("Tplatoon").value;
		var Tcolumn = document.getElementById("Tcolumn").value;
		var Tfloor = document.getElementById("Tfloor").value;
		var whAreaId = document.getElementById("whAreaId").value;
		var alleyId = document.getElementById("alleyId").value;
		var status = document.getElementById("status").value;
		var traycode = document.getElementById("traycode").value;
		var createtime = document.getElementById("createtime").value;
		var maxLine = page.document.getElementById("lineviewrow").value;
		
		if((Fplatoon != null && Fplatoon.length > 0 && !IsNum(Fplatoon)) || (Fcolumn != null && Fcolumn.length > 0 && !IsNum(Fcolumn)) || 
			(Ffloor != null && Ffloor.length > 0 && !IsNum(Ffloor)) || (Tplatoon != null && Tplatoon.length > 0 && !IsNum(Tplatoon)) || 
			(Tcolumn != null && Tcolumn.length > 0 && !IsNum(Tcolumn)) || (Tfloor != null && Tfloor.length > 0 && !IsNum(Tfloor)))
		{
			alert("货位必须为数字");
			return;
		}
		
		condition = "&scheduleTaskId=" + scheduleTaskId + "&scheduleType=" + scheduleType + "&Fplatoon=" + Fplatoon
				 + "&Fcolumn=" + Fcolumn + "&Ffloor=" + Ffloor + "&Tplatoon=" + Tplatoon + "&Tcolumn=" + Tcolumn
				 + "&Tfloor=" + Tfloor + "&whAreaId=" + whAreaId + "&alleyId=" + alleyId + "&status=" + status
				 + "&traycode=" + traycode + "&createtime=" + createtime + "&maxLine=" + maxLine;
		
		var url = strUrl + "ScheduleTaskAction" + condition;
		list.location.href = url;
	}
	
	function change(flag)
	{
		var listObj = list.document.getElementsByName("check_id");
		var check_id = "";
		var method="";
		if(flag == "1")
		{
			method = "ricoFinashExec";
		}else if(flag == "2")
		{
			method = "ricoCancelExec";
		}else if(flag == "3")
		{
			method = "ricoIniExec";
		}
		for(var i = 0; i < listObj.length; i++)
		{
			if(listObj[i].checked)
			{
				check_id += listObj[i].value + ",";
			}
		}
		if(check_id.length < 1)
		{
			alert("请至少选择一条记录");
			return;
		}
		var url = strUrl + "ScheduleTaskAction&method=" + method + "&scheduleTaskIds=" + check_id + condition;
		list.location.href = url;
	}
	
	function OnLoad(){
		//仓库
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");	
		DWREngine.setAsync(true);
		
		//库区
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
	    //作业状态
		selectType('', 'status', 'zyzt');			//作业状态
		//指令类型
		selectType('', 'scheduleType', 'ddtaskstatus');
		
	}
	
	//根据库区获得巷道的列表
	function getAlleyList(){
	
		var whAreaId = document.getElementById("whAreaId").value;
		selectAlleyList("", "alleyId", whAreaId);
	}
	
	</script>
	
	</head>
	
	<body  onload="javascript:OnLoad();">
	<div class="con_bk" >
	<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	   <tr>
	     <td>
		     <div class="wz" >
		      <div id="dqwz" class="f_l">当前位置：<span>调度管理</span> &gt;&gt; <span>指令管理</span></div>
		      <div  class="f_r" id="caozuo">
		        <ul>        	          
		          <%if(commdScheduleQuery){%><li class="tubiao1"><a href="#" onClick="selectIns();">查询</a></li><%}%>
		          <%if(commdScheduleCancel){%><li class="tubiao3"><a href="#" onClick="change('2');">作废</a></li><%}%>
		          <%if(commdScheduleFinish){%><li class="tubiao4"><a href="#" onClick="change('1');">完成</a></li><%}%>
		          <%if(commdSchedule){%><li class="tubiao4" style="width:65px;"><a href="#" onClick="change('3');">初始化</a></li><%}%>
		        </ul>
		      </div>
		    </div>	
		    </td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
			<table id="querycondition" width="99%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
	         <tr>
                
				<td class="yx1"><div align="right">调度任务编号：</div></td>
	            <td class="yx"><input name="scheduleTaskId" type="text" value="" /></td> 
                <td class="yx1" width="100" align="right">调度类型：</td>
                <td class="yx"><div class="select_search"><select id="scheduleType" style="width:117px;">
            	               <option value="">---请选择---</option>
          	                   </select></div>
                </td>
                <td class="yx1"><div align="right">源货位：</div></td>
                <td class="yx">
			   	    <input type="text" id="Fplatoon" size="2">排
			   	    <input type="text" id="Fcolumn" size="2">列
			   	    <input type="text" id="Ffloor" size="2">层
                </td>
                <td class="yx1"><div align="right">目标货位：</div></td>
                <td class="xx1">
			   	    <input type="text" id="Tplatoon" size="2">排
			   	    <input type="text" id="Tcolumn" size="2">列
			   	    <input type="text" id="Tfloor" size="2">层
                </td>
	          </tr>

	          <tr>
	     	 	  <td style="display:none"><select name="warehouseid" onChange="getWhAreaList()" style="width:117px;"><option value=""></option></select></td>
	              <td class="yx1" width="100" align="right">库区：</td>
     	  		  <td class="yx">
     	  		  	<div class="select_search">
	     	  		  	<select id="whAreaId" onChange="getAlleyList()" style="width:117px;"><option value=""></option></select>
     	  		  	</div>
     	  		  </td>
	      		  <td class="yx1" width="100px" align="right">巷道：</td>
	      		  <td class="yx">
	      		  	<div class="select_search">
	      		  		<select id="alleyId" style="width:117px;">
	      		  			<option value="">--请选择--</option>
	      		  		</select>
	      		  	</div>
	      		  </td>
	              <td class="y1"><div align="right">任务状态：</div></td>
	              <td class="yx"><div align="left" class="select_search" style="width:135px;">
	                <select id="status" class="select_search" style="width:135px;" > 
	                  <option value="">--请选择--</option>
	                </select></div>
	              </td>
				  <td class="yx1"><div align="right">托盘条码：</div></td>
	              <td class="xx1"><input name="traycode" type="text" value="" /></td> 
	         </tr>
	            <tr>
	            <td class="yx1" align="right">创建时间：</td>
	     		<td class="yx">
	      			<input id="createtime" type="text" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:100px;">
	      		</td>
	      		<td class="xx1" colspan="6"></td>
	            </tr>
	            </table> 
	            
	 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
	   <tr>
	     <td height="5"></td>
	   </tr>
	 </table>
	 
	     </td>
	   </tr>
	   <tr>
	     <td height="100%" valign="top"> 
	 
	 	 <table width="99%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
		   <tr>
		     <td>
			   <iframe id="list" name="list" width="100%" height="100%"   frameborder="0"  scrolling="yes"
		               src="<%=request.getContextPath()%>/standard/jsp/scheduleMgr/instructionMgr_list.jsp"></iframe>
			 </td>
		   </tr>
		  <tr>
		    <td height="25">
		      <iframe id="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=20&flag=Y" 
		      	width="100%" height="100%" scrolling="no" frameborder="0" ></iframe> 
		    </td>
		  </tr>
		 </table>
	     </td>
	   </tr>
	 </table>
	
	</div>
	
	<!-- 页面加载层（start） -->
	 <div id="loader_container" style="position: absolute; display: none; z-index: 2; filter:alpha(opacity=75);">
	   <div id="loader"><div align="center">页面正在加载 ...</div><div id="loader_bg"><div id="progress"></div></div></div>
	 </div>
	 <!-- 页面加载层（end） -->  
	
	</body>
	</html>
