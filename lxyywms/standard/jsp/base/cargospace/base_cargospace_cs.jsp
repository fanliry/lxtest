<%@ page contentType="text/html; charset=GBK"%>
<%
	String cargoSpaceId = (String)request.getAttribute("cargoSpaceId");  
%>
<html>
<head>
<title>自动化立体仓库信息管理系统</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--

	//初始化货位左边列表
	function initLeftPage()
	{
		var strUrl = '<%=request.getContextPath()%>/BMSService?actionCode=baseCargoSpaceAction&method=getCargoSpaceByCargoSpaceId'
				   + '&cargoSpaceId=<%=cargoSpaceId%>';
		
	   	window.csLeftIframe.location.href=strUrl;
	}
	
	//初始化货位右边列表
	function initRightPage()
	{
		var strUrl = '<%=request.getContextPath()%>/BMSService?actionCode=baseCargoSpaceAction&method=getStorageByCargoSpaceId'
				   + '&cargoSpaceId=<%=cargoSpaceId%>';
		
	   	window.csRightIframe.location.href=strUrl;
	}
	
	function onload()
	{
		initLeftPage();
		initRightPage();
	}
-->
</script>
</head>
<body onload="onload()">
<form id="myform" name="myform" method="post" action="">
 <table width="100%" height="100%" border="1" bordercolor="#FFFFFF" align="center" cellpadding="0" cellspacing="0">
   <tr>
     <td width="40%" valign="top">
     	<iframe id="csLeftIframe" name="csLeftIframe" width="100%" height="100%" frameborder="0" scrolling="yes" src="#">
        </iframe>
	 </td>
	 
     <td valign="top">
	   <iframe id="csRightIframe" name="csRightIframe" width="100%" height="100%" frameborder="0" scrolling="yes" src="#">
       </iframe>
	 </td>
   </tr>

 </table>
</form>
</body>
</html>
