
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean sysMgrSysLogQuery = false; //查询
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("sysMgrSysLogQuery") != null){
			sysMgrSysLogQuery = true;
		}
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>日志管理</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/standard/css/table.css" rel="stylesheet" type="text/css">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
   
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
  <script type="text/javascript">
  <!--
  		//查询
  		function query()
  		{
  			var logName  = document.getElementById("logName").value;
  			var moduleName  = document.getElementById("moduleName").value;
  			var startTime  = document.getElementById("startTime").value;
  			var endTime  = document.getElementById("endTime").value;

  			myIframe.location.href="<%=request.getContextPath()%>/BMSService?actionCode=sysLogMgr&logName="+logName
  									+ "&=moduleName" + moduleName + "&startTime="  + startTime + "&endTime=" + endTime;	
  			document.getElementById("logName").value="";	
  			document.getElementById("moduleName").value="";
  			document.getElementById("startTime").value="";
  			document.getElementById("endTime").value="";		
  		}
  	
  -->
  </script>
  </head>
  
 <body topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0" >
 <div class="con_bk">
<table width="100%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr> <td height="5"><div class="wz">
		<div id="dqwz" class="f_l">当前位置：<span>系统管理 &gt;&gt; 日志管理</span></div>
		  <div class="font_001F56_12" align="right">
	       <!-- ======== 增、删、改、查按钮 ======== -->
	       <input onclick="query()" type="button" name="queryBtn" value="&nbsp;&nbsp;&nbsp;查询" class="button_search"<%if(!sysMgrSysLogQuery){%>disabled<%}%>>
	      </div>
		</div></td>
  </tr>
  <tr><td height="5"></td></tr>
  <tr>
    <td valign="top">	
	
	<!-- ======== 查询条件开始 ======== -->
	<form id="myform" name="myform" method="post" action="">
	 <table width="99%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
	   <tr>
	     <td class="yx1" ><div align="right">操作员名：</div></td>
	     <td class="yx"><div align="left">
	    	 <input type="text" name="logName" size="22" maxlength="50">
	     </div></td>
	     <td class="yx1" ><div align="right">模块名称：</div></td>
	     <td class="xx1"><div align="left">
	    	 <input type="text" name="moduleName" size="22" maxlength="50">
	     </div></td>
	   </tr>
		 <tr>
	     <td class="y1" ><div align="right">开始时间：</div></td>
	     <td class="x"><div align="left">
	    	 <input type="text" name="startTime" size="22" maxlength="50"  class="Wdate" readonly="readonly" onclick="calendar()">
	     </div></td>
	     <td class="y1" ><div align="right">结束时间：</div></td>
	     <td ><div align="left">
	    	 <input type="text" name="endTime" size="22" maxlength="50"  class="Wdate"  readonly="readonly" onclick="calendar()">
	     </div></td>
	   </tr>


	 </table>
	 </form>
	 <!-- ======== 查询条件结束 ======== -->
	 
	 
	 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
	   <tr><td height="15"></td></tr>
	 </table>
	 
	 
	 <!-- ======== 查询列表显示数据开始 ======== -->
	 <table width="99%" height="345" border="0" align="center" cellpadding="0" cellspacing="0">
	   <tr>
	     <td>
	 	   <iframe id="myIframe" src="<%=request.getContextPath()%>/jsp/competenceMgr/sysLog_list.jsp" frameborder="0" width="100%" height="100%">
	 	   </iframe>
	     </td>
	   </tr>
	 </table>
	 <!-- ======== 查询列表显示数据结束 ======== -->
	 
	 
	 <!-- ======== 分页标签 ======== -->
	<iframe id="myIframe1" src="<%=request.getContextPath()%>/jsp/competenceMgr/page.jsp" frameborder="0" width="100%" height="30">
	 	   </iframe>
	
	
	</td>
  </tr>
</table>
</div>
</body>
</html>
