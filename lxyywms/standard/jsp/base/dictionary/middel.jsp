<%@ page contentType = "text/html;charset=gb2312"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>欢迎使用自动化立体仓库信息管理系统</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<style> 
.navPoint { 
COLOR: white; CURSOR: hand; FONT-FAMILY: Webdings; FONT-SIZE: 9pt 
} 
</style> 
<script type="text/javascript">
<!--
	function switchSysBar(){ 
	var locate=location.href.replace('<%=request.getContextPath()%>/standard/jsp/base/dictionary/middel.jsp','');
	
	var ssrc=document.getElementById("img1").src.replace(locate,'');
	
	if (ssrc=="<%=request.getContextPath()%>/standard/images/main_55.gif")
	{ 
	document.all("img1").src="<%=request.getContextPath()%>/standard/images/main_55_1.gif";
	document.all("frmTitle").style.display="none" 
	} 
	else
	{ 
	document.all("img1").src="<%=request.getContextPath()%>/standard/images/main_55.gif";
	document.all("frmTitle").style.display="";
	} 
	} 
-->
</script>

</head>

<body style="overflow:hidden">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
  <tr>
    <td width="171" id=frmTitle noWrap name="fmTitle" align="center" valign="top"><table width="171" height="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
      <tr>
        <td  bgcolor="#6699CC" style="width:6px;">&nbsp;</td>
        <td width="165">
		<iframe name="I1" height="100%" width="165" src="<%=request.getContextPath()%>/BMSService?actionCode=mapQuery" border="0" frameborder="0" >
		</iframe></td>
      </tr>
    </table>		</td>
    <td width="6"  style="width:6px;"valign="middle" bgcolor="#6699CC"  onclick='switchSysBar()'>
		<SPAN class=navPoint id=switchPoint title=关闭/打开左栏>
				<img src="<%=request.getContextPath()%>/standard/images/main_55.gif" name="img1" width=6 height=40 id=img1>
		</SPAN>
	</td>
    <td width="100%" align="center" valign="top">
	<iframe name="main" height="100%" width="100%" border="0" frameborder="0" src="<%=request.getContextPath()%>/BMSService?actionCode=branchMeans"/>
	 </td>
  </tr>
</table>
</body>
</html>
