<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%
	String strTime =  StrTools.getCurrDateTime(8); 
%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	function Search(){
		var inout = document.getElementById("inout").value;
		var warehouseid = document.getElementById("warehouseid").value;
		var indate_from = document.getElementById("indate_from").value;
		var indate_to = document.getElementById("indate_to").value;
		
		if(warehouseid == null || warehouseid.length <1)
		{
			alert("【仓库】不能为空!");
			return false;
		}
		if(indate_from == null || indate_from.length <1)
		{
			alert("【起初日期】不能为空!");
			return false;
		}
		if(indate_to == null || indate_to.length <1)
		{
			alert("【结束日期】不能为空!");
			return false;
		}
		condition = "&inout=" +　inout+"&warehouseid=" +　warehouseid+"&indate_from=" +　indate_from+"&indate_to=" +　indate_to;
		
		list.location.href = ac + "InAnalysisAction&flag=4" + condition;
	}
	function OnLoad(){
		//仓库
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");	
		DWREngine.setAsync(true);
	}
-->
</script>
</head>
<body  onLoad="OnLoad()">
 <input type="hidden" id="condition">
 
<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== 当前位置 ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">当前位置：<span>KPI分析 -&gt; 巷道均匀分析</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<li class="tubiao1"><a href="#" onclick="Search()">分析</a></li>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== 查询条件 ======== -->
      <table id="marginTable" width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center">
        <tr>
	      <td class="yx1" width="100" align="right">仓库：</td>
	      <td class="yx"><select name="warehouseid" onChange="getWhAreaList()" style="width:117px;"><option value=""></option></select></td>
	      <td class="yx1" width="100" align="right">入出库：</td>
	      <td class="yx"><select name="inout"  style="width:117px;"><option value="1">入库</option><option value="2">出库</option></select></td>
	      <td class="yx1" width="100" align="right">日期：</td>
     	  <td class="yx">
     	  	<input id="indate_from" type="text" onFocus="calendar()"  value="<%=strTime%>" class="Wdate" style="height:21px;width:100px;">-<input id="indate_to" type="text" onfocus="calendar();"  value="<%=strTime%>" class="Wdate" style="height:21px;width:100px;">
		  </td>
	    </tr> 
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/inventory/kc_alley_analyse_list.jsp" 
		  		width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
		  	</td>
		  </tr>
		</table>
	  </td>
    </tr>
    <tr><td align="center"><strong><Font color="red">查询10个堆垛机某天或者某几天执行的入出库作业数量</Font></strong></td></tr>
  </table>  	
</div>
</html>
