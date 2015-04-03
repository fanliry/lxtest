<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.HashMap" %>
<%
	String warehouseid = (String)session.getAttribute("warehouseid");//仓库ID

	HashMap hsPopedom = null;
	boolean kpiMachineAnalyze = false; //修改

	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("kpiMachineAnalyze") != null){
			kpiMachineAnalyze = true;
		}
    }
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
<script type='text/javascript'src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	function Search(){
		var inout = document.getElementById("inout").value;
		var whAreaId = document.getElementById("whAreaId").value;
		if(whAreaId == null || whAreaId.length <1)
		{
			alert("【库区】不能为空!");
			return false;
		}
		//验证库区是否为立体库区(自动化)
		if(whAreaId != null && whAreaId.length >1)
		{
			var msg;
			DWREngine.setAsync(false);
			judgmentTool.isAutoWharea(whAreaId, Show);
			DWREngine.setAsync(true);
			function Show(value){
				msg = value;
			}
			if(msg != "Y"){
				alert(msg);
				return;
			}else{
			    condition = "&inout=" + inout+"&whAreaId=" + whAreaId;
				list.location.href = ac + "InAnalysisAction&flag=2" + condition;
			}
		}else{
			alert("【库区】不能为空!");
			return false;
		}	
	}
	function OnLoad(){
		//库区
		selectAreaList("", "whAreaId", "<%=warehouseid%>", "1");
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
		<div id="dqwz" class="f_l">当前位置：<span>KPI分析 -&gt; 堆垛机负荷分析</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<%if(kpiMachineAnalyze){%><li class="tubiao1"><a href="#" onclick="Search()">分析</a></li><%}%>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== 查询条件 ======== -->
      <table id="marginTable" width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center">
        <tr>
	      <td class="y1" width="100" align="right">库区：</td>
	      <td class="x"><div class="select_search"><select id="whAreaId" style="width:117px;"><option value=""></option></select></div></td>
	      <td class="y1" width="100" align="right">入出库：</td>
	      <td><div class="select_search"><select name="inout"  style="width:117px;"><option value="1">入库</option><option value="2">出库</option></select></div></td>
	    </tr> 
      </table> 
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/BMSService?actionCode=InAnalysisAction&flag=2&inout=1&warehouseid=001" 
		  		width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
		  	</td>
		  </tr>
		</table>
	  </td>
    </tr>
    <tr><td align="center"><strong><Font color="red">查询10个堆垛机当前没有执行完的入出库作业数量</Font></strong></td></tr>
  </table>  	
</div>
</html>
