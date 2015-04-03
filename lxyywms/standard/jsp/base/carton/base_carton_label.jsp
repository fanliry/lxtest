<%@ page contentType="text/html; charset=GB2312"%>
<%
   
	String CartonId = request.getParameter("CartonId");
	String[] strIds = CartonId.split(",");
	CartonId = strIds[0];
%>
<html>
<head>
<title>装箱标签</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
<!--
	#nav{
		width: 80mm;
		height:40mm;
		border:1px solid #000000;
	}
-->
</style>

<style media="print">
<!--
	.Noprint{display:none;}<!--用本样式在打印时隐藏非打印项目-->
	.PageNext{page-break-after: always;}<!--控制分页-->
-->
</style> 

<script type="text/javascript">
<!--

	var HKEY_Root,HKEY_Path,HKEY_Key;
	HKEY_Root="HKEY_CURRENT_USER";
	HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
	
	//设置网页打印的页眉页脚为空
	function PageSetup_Null()     
	{
		try
		{
			var Wsh=new ActiveXObject("WScript.Shell");
			HKEY_Key="header";
			Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
			HKEY_Key="footer";
			Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
		}
		catch(e){}
	}
	//设置网页打印的页眉页脚为默认值
	function PageSetup_Default()
	{
	  	try
	    {
			var Wsh=new ActiveXObject("WScript.Shell");
			HKEY_Key="header";
			Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"&w&b页00码,&p/&P");
			HKEY_Key="footer";
			Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"&u&b&d");
	    }     
		catch(e){}
	}
	
	//毫米转换成像素
	function mmToDPI(width, height)
	{
		var dpi = window.screen.deviceXDPI;
		var _width = dpi * (width / 25.4);
		var _height = dpi * (height / 25.4);
		return {width: _width.toFixed(), height: _height.toFixed()};
	}
	//像素转换成毫米
	function dpiToMM(width, height)
	{
		var dpi = window.screen.deviceXDPI;
		var _width = (width / dpi) * 25.4;
		var _height = (height / dpi) * 25.4;
		return {width: _width.toFixed(), height: _height.toFixed()};
	}

	// 毫米转换成像素(a4)
	//var result = mmToDPI(80,40);
	//var _width = result.width;
	//var _height = result.height;
	
	//alert ( result.width + " * " + result.height );
-->
</script>
</head>

<body>

 <!-- ======== 功能按钮开始 ======== -->
 <div style="width: 80mm;" align="center">
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0" class="Noprint">
   <tr height="30">
     <td><div align="left">
       <OBJECT id=WebBrowser classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 width=0></OBJECT>
       <input type="button" name="readyBtn" value="打印预览" class="BUTTON_STYLE1" onclick="PageSetup_Null();document.all.WebBrowser.ExecWB(7,1)">&nbsp;
       <input type="button" name="setBtn" value="页面设置" class="BUTTON_STYLE1" onclick="document.all.WebBrowser.ExecWB(8,1)">&nbsp;
       <input type="button" name="printBtn" value="打印" class="BUTTON_STYLE1" onclick="PageSetup_Null();javascript:window.print()">&nbsp;
       <input type="button" name="closeBtn" value="关闭" class="BUTTON_STYLE1" onclick="window.close()">
     </div></td>
   </tr>
 </table>
 </div>
 <!-- ======== 功能按钮结束 ======== -->
 
 <!-- ======== 标签内容开始 ======== -->
 <div id="nav" align="center">
   <table width="95%" height="95%" align="center" border="0" cellspacing="0" cellpadding="0">
     <tr height="10">
	   <td></td>
	 </tr>
	 <tr height="40">
	   <td align="center"><img id="codeImg" src="<%=request.getContextPath()%>/barcode?msg=<%=CartonId%>&type=code128&fmt=jpeg" height="120px" width="280px"/></td>
	 </tr>
   </table>
 </div>
 <!-- ======== 标签内容结束 ======== -->
 
</body>
</html>