<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="com.wms3.bms.standard.service.BMSService"%>
<%@page import="com.ricosoft.common.tools.StrTools"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>无标题文档</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
</head>


<%
	//用户名
	String strUserName = (String)session.getAttribute("userName");
    String strUrl = (String)BMSService.getHsUrl().get("dmmsurl");
%>
<body class="bj_blue" onload="javascript:startClock();">
<script language='JavaScript' charset='gb2312'>
	function startClock()
	{
		var now = new Date();
		var strDate = now.getYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();
		var meg = "&nbsp;&nbsp;<a href='<%=request.getContextPath()%>/jsp/competenceMgr/password_update.jsp' target='conright'>修改密码</a>"
       +"&nbsp;<a onclick='existSys()' href='#'>退出</a>";
		//+ "  "+now.getHours()+":"+now.getMinutes()+":"+now.getSeconds()
		var weeks=now.getDay();  	
		if(weeks == 0){ weeks = "星期天";
		}else if (weeks == 1){    weeks = "星期一";  
		}else if (weeks == 2){    weeks = "星期二";  
		}else if (weeks == 3){    weeks = "星期三";  
		}else if (weeks == 4){    weeks = "星期四";  
		}else if (weeks == 5){    weeks = "星期五";  
		}else {  weeks = "星期六";}
		//+ strDate + " &nbsp;" + weeks + "&nbsp;"
		document.getElementById("itime").innerHTML = "您好：<%=strUserName%> &nbsp;时间：" + strDate + "&nbsp;&nbsp;" + weeks + meg;
		
		//setInterval("startClock();",5000); // 单位是毫秒 5秒
	}
	function existSys(){//退出系统
    	window.top.close();
	}
</script>
  <div id="top" style="position:relative; ">
  <div  id="logo" ></div>
  <div id="apDiv1">
    <div  id="yh" >
      <span id="itime"></span>
     <!--  <p id="itime">当前用户：超级管理员&nbsp;&nbsp;时间：2012-3-30 12：00 星期二</p> -->
    </div>
  </div>

</div>

  
</body>
</html>
