<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryMovementHeader" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryMovementDetail" %>
<%@page import="java.util.HashMap"%>
<%@page import="com.wms3.bms.standard.entity.base.BaseLotSet"%>
<%@page import="java.lang.reflect.Field"%>
<%@page import="java.util.ArrayList"%>
<%
	String list_items = (String)request.getAttribute("list_items");
	int itemscount = 0;
	String[] items = {""};
	if(list_items != null){
		items = list_items.split(",");
		itemscount = items.length;
	}
	
	
	
	
	InventoryMovementHeader result = (InventoryMovementHeader)request.getAttribute("result"); 
	//String instockid = 	(String)request.getAttribute("instockid"); 

	 String movementId = null;
		String strWarehouseId=null;//仓库ID
		String strWarehouseName = null;	//仓库名称
		
		String strCreateTime = null;	//制单时间
		
		String strCreateManid = null; 	//制单人
	 	String strCreteMan = null;
	
	if(result != null){
		movementId = result.getMovementid();
		strWarehouseId = result.getWarehouseid();
		strWarehouseName = result.getWarehouseName();
		strCreateTime = result.getCreateTime();
		strCreateManid = result.getCreateManid();
		strCreteMan = result.getCreateMan();
	}
%>

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
		
		var classname = "com.wms3.bms.standard.action.report.inventory.InventoryYKQueryExcel";
		
		
		document.tempForm1.action = strUrl + "downFileAction&name=移库查询.xls&fileType=excel&classname= " + classname + "&movementId="+"<%=movementId %>";
		
		document.tempForm1.submit();
		//alert(window.opener.condition);
		//alert(window.opener.getPostCon());
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

 <!-- ======== 标题开始 ======== -->
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
 	<tr>
	 <td height="40" colspan="6" valign="bottom"><div align="center" class="style2">海&nbsp;正&nbsp;药&nbsp;业&nbsp;(&nbsp;杭&nbsp;州&nbsp;)&nbsp;有&nbsp;限&nbsp;公&nbsp;司</div></td>
   </tr>
   <tr>
	 <td height="40" colspan="<%=12 + itemscount%>" valign="bottom"><div align="center" class="style2">库&nbsp;存&nbsp;移&nbsp;库&nbsp;报&nbsp;表</div></td>
   </tr>
    <tr>
   	<td colspan="6" height="10" ></td>
   </tr>
   <tr>
	 <td height="20" colspan="8" valign="top"><div align="center">
	   <hr align="center" width="450" color="#006699" noshade="noshade" size="1">
	 </div></td>
   </tr>
   <tr>
	 <td height="20"><div align="left"  class="list_title_tdpr">仓库名称：<%=strWarehouseName==null?"":strWarehouseName%></div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr">制单时间：<%=strCreateTime==null?"":strCreateTime%></div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr">单据编码：<%=movementId==null?"":movementId%></div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr">制单人：<%=strCreteMan==null?"":strCreteMan%></div></td>
	 
   </tr>
   
 </table>
 <!-- ======== 标题结束 ======== -->

 <table width="100%" height="20"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>

 <!-- ======== 列表开始 ======== -->  	
  <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="outer_table">
   <tr>
   	
     <td class="TD_LIST_TITLE"><div class="list_title">行号</div></td>
   
    
     <td class="TD_LIST_TITLE"><div class="list_title">产品编码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">生产日期</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">批号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">托盘号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">单位</div></td>
     
     
    
     <td class="TD_LIST_TITLE"><div class="list_title">FM库区</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">FM货位</div></td>
  
     <td class="TD_LIST_TITLE"><div class="list_title">TO库区</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">TO货位</div></td>
     
     
   </tr>
   <%
	List ls = (List)request.getAttribute("exResultList");
   if(ls != null && ls.size() > 0){
		InventoryMovementDetail iDetail = null;
		
		String strProductCode = null; 	//产品编号
		String strProductName = null; 	//品名
		String strProductDate = null;	//生产日期
		String strLotNum = null;	//批号
		String strTrayNum = null;	//托盘号
		String strPunit = null; //单位
		
		String strFromWhare = null;	//FM库区
		String strFromCargospace = null; //FM货位
		
		String strToWhare = null;	//to货区
		String strToCargospace = null; //to货位
		
		
		
		for(int i = 0; i< ls.size(); i++){
			iDetail = (InventoryMovementDetail)ls.get(i);
			
			strProductCode = iDetail.getProductid();
			strProductName = iDetail.getProductName();
			strProductDate = iDetail.getProductDate();
			strLotNum = iDetail.getLotNum();
			strTrayNum = iDetail.getTrayCode();
			strPunit = iDetail.getMeter();
			
			
			strFromWhare = iDetail.getFromAreName();
			
			strFromCargospace = iDetail.getFromCargospace();
			strToWhare = iDetail.getToAreName();
			strToCargospace = iDetail.getToCargospace();
			
	
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
   	
     <td class="TD_LIST" align="center"><%=i+1%></td>
    
     <td class="TD_LIST"><%=strProductCode%></td>
     <td class="TD_LIST" align="center"><%=strProductName%></td>
     
     <td class="TD_LIST" align="center"><%=strProductDate%></td>
     <td class="TD_LIST" align="center"><%=strLotNum%></td>
     <td class="TD_LIST" align="center"><%=strTrayNum%></td>
     <td class="TD_LIST" align="center"><%=strPunit%></td>
     
     <td class="TD_LIST" align="center"><%=strFromWhare%></td>
     <td class="TD_LIST" align="center"><%=strFromCargospace%></td>
     <td class="TD_LIST" align="center"><%=strToWhare%></td>
     <td class="TD_LIST" align="center"><%=strToCargospace%></td>
    
    
   </tr>
<%
		}
		
	}
	else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"></div>
     </td>
   </tr>

 </table>
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm1' target="_self"  style='display:none'></FORM>
</body>
</html>
