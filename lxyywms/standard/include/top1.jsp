<%@ page language="java" pageEncoding="GBK"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>无标题文档</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
</head>


<%
	//用户名
	String strUserName = (String)session.getAttribute("userName");
%>
<body class="bj_blue" onload="javascript:startClock();">
<script language='JavaScript' charset='gb2312'>
	function startClock()
	{
		var now = new Date();
		var strDate = now.getYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();
		//+ "  "+now.getHours()+":"+now.getMinutes()+":"+now.getSeconds()
		var weeks=now.getDay();  	
		if(weeks == 0){ weeks = "星期天";
		}else if (weeks == 1){    weeks = "星期一";  
		}else if (weeks == 2){    weeks = "星期二";  
		}else if (weeks == 3){    weeks = "星期三";  
		}else if (weeks == 4){    weeks = "星期四";  
		}else if (weeks == 5){    weeks = "星期五";  
		}else {  weeks = "星期六";}
		document.getElementById("itime").innerHTML = "您好：<%=strUserName%>&nbsp;时间：" + strDate + "&nbsp;" + weeks+ "&nbsp;&nbsp;<a href='<%=request.getContextPath()%>/jsp/competenceMgr/password_update.jsp' target='conright'>修改密码</a>"+"&nbsp;&nbsp;<a href='<%=request.getContextPath()%>/standard/include/exit.jsp' >退出</a>";
		
		//setInterval("startClock();",5000); // 单位是毫秒 5秒
	}
</script>
  <div id="top" style="position:relative; ">
  <div  id="logo" ></div>
  <div id="apDiv1">
    <div  id="yh" >
    	<p id="itime"></p>
    </div>
    
  </div>
  
</div>
  
  
</body>
</html>
