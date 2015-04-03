<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@page import="com.wms3.bms.standard.entity.inbound.InboundInvoiceHeader"%>
<%@page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader"%>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckTask"%>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult"%>

<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
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
	
	//打开Excel报表
	function openexcel()
	{
		
		var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
		
		var classname = "com.wms3.bms.standard.action.report.inventory.CheckInventoryQueryExcel";
		
		
		document.tempForm1.action = strUrl + "downFileAction&name=盘点查询.xls&fileType=excel&classname= " + classname + window.opener.condition;
		
		document.tempForm1.submit();	
		//alert(window.opener.condition);
		//alert(window.opener.getLotattCon());
	}
	
-->
</script>
<style media="print">
<!--
	.Noprint{display:none;}<!--用本样式在打印时隐藏非打印项目-->
	.PageNext{page-break-after: always;}<!--控制分页-->
-->
</style> 
<style type="text/css">
<!--
	.style2 {
		font-size: 24px; 
		font-weight: bold; 
		font-family: "楷体_GB2312";
	}
-->
</style>
</head>

<body>


 <!-- ======== 功能按钮开始 ======== -->
 <form id="myform1" name="myform1" method="post">
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0" class="Noprint">
   <tr height="30">
     <td><div align="left">
       <OBJECT id=WebBrowser classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 width=0></OBJECT>
       <input type="button" name="readyBtn" value="excel报表" class="BUTTON_STYLE1" onclick="openexcel();">&nbsp;
       <input type="button" name="readyBtn" value="打印预览" class="BUTTON_STYLE1" onclick="PageSetup_Null();document.all.WebBrowser.ExecWB(7,1)">&nbsp;
       <input type="button" name="setBtn" value="页面设置" class="BUTTON_STYLE1" onclick="document.all.WebBrowser.ExecWB(8,1)">&nbsp;
       <input type="button" name="printBtn" value="打印" class="BUTTON_STYLE1" onclick="PageSetup_Null();javascript:window.print()">&nbsp;
       <input type="button" name="closeBtn" value="关闭" class="BUTTON_STYLE1" onclick="window.close()">
     </div></td>
   </tr>
 </table>
 </form>
 <!-- ======== 功能按钮结束 ======== -->
 
<%
	int iLine = 0;	//显示的批次属性个数
	
%>

 <!-- ======== 标题开始 ======== -->
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
	 <td height="40" colspan="<%=18 + iLine%>" valign="bottom"><div align="center" class="style2">盘&nbsp;点&nbsp;结&nbsp;果&nbsp;报&nbsp;表</div></td>
   </tr>
   <tr>
	 <td height="20" colspan="8" valign="top"><div align="center">
	   <hr align="center" width="450" color="#006699" noshade="noshade" size="1">
	 </div></td>
   </tr>
 </table>
 <!-- ======== 标题结束 ======== -->

 <table width="100%" height="20"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>

 <!-- ======== 列表开始 ======== -->  	
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="outer_table">
   <tr class="list_title_tr">
     <td class="list_title_td" >仓库</td>
     <td class="list_title_td" >申请单号</td>
     <td class="list_title_td" >任务号</td>
     <td class="list_title_td" >产品名</td>
     <td class="list_title_td" >批号</td>
     <td class="list_title_td" >库存数量</td>
     <td class="list_title_td" >盘点数量</td>
     <td class="list_title_td" >操作员</td>
     <td class="list_title_td" >盘点时间</td>
   </tr>
   
<%
	List<Object[]> objLs = (List<Object[]> )request.getAttribute("objLs");  //入库盘点单

	if(objLs != null && objLs.size() > 0)
   	{   
		InventoryCheckTask checkTask = null;
		InventoryCheckResult checkResult = null;
		Object[] obj = new Object[2];
   		String strWarehouseId = "";		    // 仓库
		String strCheckReq = "";			// 盘点申请
		String strCheckTask = "";			// 任务
		String strProductName = "";         //产品名
		String strLotinfo = "";				// 批号
		double strpnum =0.0;                 //库存数量
		double strCheckNum =0.0;            //盘点数量
		String strOpManName = "";			// 操作员名
		String strOpManTime = "";			// 操作时间
	
   		for(int i = 0; i < objLs.size(); i++)
   		{
   			obj = (Object[])objLs.get(i);
   			checkResult = (InventoryCheckResult)obj[0];  //盘点结果
   			checkTask = (InventoryCheckTask)obj[1];//盘点任务
   	   		strWarehouseId = checkTask.getWarehouseid();		    // 仓库
   			strCheckReq = checkResult.getRequestid();			// 盘点申请
   			strCheckTask = checkResult.getTaskid();			// 任务
   			strProductName = checkResult.getProductName();         //产品名
   			strLotinfo = checkResult.getLotnumber();				// 批号
   			strpnum =checkResult.getNum();                 //库存数量
   			strCheckNum =checkResult.getChecknum();            //盘点数量
   			strOpManName = checkResult.getCreateman();			// 操作员名
   			strOpManTime =checkResult.getChecktime() ;			// 操作时间
	  	 	
%>
   <tr  class="list_normal_tr">
     <td class="TD_LIST" align="center"><%=strWarehouseId == null ? "" : strWarehouseId%></td>
     <td class="TD_LIST" align="center"><%=strCheckReq == null ? "" : strCheckReq%></td>
     <td class="TD_LIST" align="center"><%=strCheckTask == null ? "" : strCheckTask%></td>
     <td class="TD_LIST" align="center"><%=strProductName == null ? "" : strProductName%></td>
     <td class="TD_LIST" align="center"><%=strLotinfo == null ? "" : strLotinfo%></td>
     <td class="TD_LIST" align="center"><%=strpnum%></td>	
     <td class="TD_LIST" align="center"><%=strCheckNum%></td>	
     <td class="TD_LIST" align="center"><%=strOpManName == null ? "" : strOpManName%></td>	
     <td class="TD_LIST" align="center"><%=strOpManTime == null ? "" : strOpManTime%></td>	
  </tr>
<%
		}
		 }
%>
 
 </table>
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm1' target="_self"  style='display:none'></FORM>
</body>
</html>
