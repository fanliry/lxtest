<%@ page language="java" pageEncoding="GBK"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>�ޱ����ĵ�</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
</head>


<%
	//�û���
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
		if(weeks == 0){ weeks = "������";
		}else if (weeks == 1){    weeks = "����һ";  
		}else if (weeks == 2){    weeks = "���ڶ�";  
		}else if (weeks == 3){    weeks = "������";  
		}else if (weeks == 4){    weeks = "������";  
		}else if (weeks == 5){    weeks = "������";  
		}else {  weeks = "������";}
		document.getElementById("itime").innerHTML = "���ã�<%=strUserName%>&nbsp;ʱ�䣺" + strDate + "&nbsp;" + weeks+ "&nbsp;&nbsp;<a href='<%=request.getContextPath()%>/jsp/competenceMgr/password_update.jsp' target='conright'>�޸�����</a>"+"&nbsp;&nbsp;<a href='<%=request.getContextPath()%>/standard/include/exit.jsp' >�˳�</a>";
		
		//setInterval("startClock();",5000); // ��λ�Ǻ��� 5��
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
