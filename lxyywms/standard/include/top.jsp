<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="com.wms3.bms.standard.service.BMSService"%>
<%@page import="com.ricosoft.common.tools.StrTools"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>�ޱ����ĵ�</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
</head>


<%
	//�û���
	String strUserName = (String)session.getAttribute("userName");
    String strUrl = (String)BMSService.getHsUrl().get("dmmsurl");
%>
<body class="bj_blue" onload="javascript:startClock();">
<script language='JavaScript' charset='gb2312'>
	function startClock()
	{
		var now = new Date();
		var strDate = now.getYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();
		var meg = "&nbsp;&nbsp;<a href='<%=request.getContextPath()%>/jsp/competenceMgr/password_update.jsp' target='conright'>�޸�����</a>"
       +"&nbsp;<a onclick='existSys()' href='#'>�˳�</a>";
		//+ "  "+now.getHours()+":"+now.getMinutes()+":"+now.getSeconds()
		var weeks=now.getDay();  	
		if(weeks == 0){ weeks = "������";
		}else if (weeks == 1){    weeks = "����һ";  
		}else if (weeks == 2){    weeks = "���ڶ�";  
		}else if (weeks == 3){    weeks = "������";  
		}else if (weeks == 4){    weeks = "������";  
		}else if (weeks == 5){    weeks = "������";  
		}else {  weeks = "������";}
		//+ strDate + " &nbsp;" + weeks + "&nbsp;"
		document.getElementById("itime").innerHTML = "���ã�<%=strUserName%> &nbsp;ʱ�䣺" + strDate + "&nbsp;&nbsp;" + weeks + meg;
		
		//setInterval("startClock();",5000); // ��λ�Ǻ��� 5��
	}
	function existSys(){//�˳�ϵͳ
    	window.top.close();
	}
</script>
  <div id="top" style="position:relative; ">
  <div  id="logo" ></div>
  <div id="apDiv1">
    <div  id="yh" >
      <span id="itime"></span>
     <!--  <p id="itime">��ǰ�û�����������Ա&nbsp;&nbsp;ʱ�䣺2012-3-30 12��00 ���ڶ�</p> -->
    </div>
  </div>

</div>

  
</body>
</html>
