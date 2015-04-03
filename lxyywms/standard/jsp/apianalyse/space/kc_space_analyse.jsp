<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.HashMap" %>
<%
	String warehouseid = (String)session.getAttribute("warehouseid");//仓库ID

	HashMap hsPopedom = null;
	boolean kpiCaspaceAnalyze = false; //修改

	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("kpiCaspaceAnalyze") != null){
			kpiCaspaceAnalyze = true;
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
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	//货位分析
	function Analyse()
	{
		var whAreaId=document.getElementById("whAreaId").value;
		var iFloor=document.getElementById("iFloor").value;
		
		if(whAreaId == "" || whAreaId.length < 1)
		{
			alert("请选择库区！");
			return;
		}
		if(iFloor == "" || iFloor.length < 1)
		{
			alert("请填写层！");
			return;
		}
		condition = "&iFloor=" + iFloor + "&whAreaId=" + whAreaId;
		list.location.href = ac + "InAnalysisAction&flag=3" + condition;
	}
	function OnLoad(){
		//库区
		selectAreaList("", "whAreaId", "<%=warehouseid%>", "1");
	}
-->
</script>
</head>
<body onLoad="OnLoad()">
 <input type="hidden" id="condition">
 
<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== 当前位置 ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">当前位置：<span>库存管理 -&gt; 货位分析</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<%if(kpiCaspaceAnalyze){%><li class="tubiao1"><a href="#" onclick="Analyse()">分析</a></li><%}%>
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
	      <td class="y1" width="100" align="right">层：</td>
	      <td><input name="iFloor" size="8"></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/apianalyse/space/kc_space_analyse_list.jsp" 
		  		width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
		  	</td>
		  </tr>
		</table>
		
	  </td>
    </tr>
    <tr><td height="5"></td></tr>
    <tr>
     <td valign="top" height="20">
         <table width="600" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		     <td width="100"><div align="right">空货位：</div></td>
		     <td width="50" bgcolor="#EEECFB"></td>
		     <td width="100"><div align="right">入库分配：</div></td>
		     <td width="50"  bgcolor="#FAB5B7"></td>
		     <td width="100"><div align="right">入库需盘点：</div></td>
		     <td width="50"  bgcolor="#9FFA95"></td>
		     <td width="100"><div align="right">其他状态：</div></td>
		     <td width="50"  bgcolor="#F6FCC4"></td>
		  </tr>
		</table>
     </td>
    </tr>
    <tr>
     <td valign="top" height="20">
         <table width="600" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		     <td width="100"><div align="right">满货位：</div></td>
		     <td width="50" bgcolor="#0000CC"></td>
		     <td width="100"><div align="right">出库分配：</div></td>
		     <td width="50"  bgcolor="#FF0000"></td>
		     <td width="100"><div align="right">出库需盘点：</div></td>
		     <td width="50"  bgcolor="#18FF00"></td>
		     <td width="100"><div align="right">货位不存在：</div></td>
		     <td width="50"  bgcolor="#E4FF00"></td>
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
