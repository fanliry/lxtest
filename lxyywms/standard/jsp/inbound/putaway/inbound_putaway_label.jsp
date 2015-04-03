<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction" %>
<%@ page import="java.text.NumberFormat" %>

<%
	InboundReceiptTransaction trans = null;
	
	String strTransId = "";	//上架记录ID

	String productid = "";  //物品ID
	String m_strProductName = ""; // 产品	
	String ownername = "";	//货主
	double recnum = 0;				//收货数量
    double reweight = 0;			//收货重量
    double renetweight = 0;			//收货净重
    if(request.getAttribute("trans")!=null)
	{
		trans = (InboundReceiptTransaction)request.getAttribute("trans");
	}
	if(trans != null)
	{
		strTransId = trans.getTransreceiptid();	//上架记录ID
	 	productid = trans.getProductid();  		//物品ID
	 	m_strProductName = trans.getM_strProductName(); // 产品	
	 	ownername = trans.getOwnername();		//货主
	 	recnum = trans.getRecnum();				//收货数量
     	reweight = trans.getReweight();			//收货重量
     	renetweight = trans.getRenetweight();	//收货净重
	}
	//保留小数2位
	NumberFormat nbf=NumberFormat.getInstance();      
	nbf.setMinimumFractionDigits(2);
	nbf.setMaximumFractionDigits(2);  

%>

<html>
<head>
<title>收货标签</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
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
-->
</script>
</head>

<body>
 <form method="post" action="">
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
   <table width="95%"align="center" border="0" cellspacing="0" cellpadding="0">
     <tr>
	   <td height="5px"></td>
	 </tr>
	 <tr >
	   <td align="left"><img id="codeImg" src="<%=request.getContextPath()%>/barcode?msg=<%=strTransId%>&type=code128&fmt=jpeg" height="80px" width="280px"/></td>
	 </tr>
   </table>
   <table width="280px" align="center" border="0" cellspacing="0" cellpadding="0" class="font_001F56_12">
     <tr>
	   <td colspan="2">产品条码：<img id="codeImg" src="<%=request.getContextPath()%>/barcode?msg=<%=productid%>&type=code128&fmt=jpeg" height="30px" width="200px"/></td>
	 </tr>
	 <tr height="18px">
	   <td width="120">品&nbsp;&nbsp;&nbsp;&nbsp;名：<%=m_strProductName%></td><td>数&nbsp;&nbsp;&nbsp;&nbsp;量：<%=nbf.format(recnum)%></td>
	 </tr>
	 <tr height="18px">
	   <td>毛&nbsp;&nbsp;&nbsp;&nbsp;重：<%=nbf.format(reweight)%></td><td>净&nbsp;&nbsp;&nbsp;&nbsp;重：<%=nbf.format(renetweight)%></td>
	 </tr>
	  <tr height="18px">
	   <td colspan="2">货&nbsp;&nbsp;&nbsp;&nbsp;主：<%=ownername%></td>
	 </tr>
   </table>
 </div>
 <!-- ======== 标签内容结束 ======== -->
 </form>
</body>
</html>