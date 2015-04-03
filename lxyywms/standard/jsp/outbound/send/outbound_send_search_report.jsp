<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>

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
		
		var classname = "com.wms3.bms.standard.action.report.outbound.OutboundSendQueryExcel";
		
		
		document.tempForm1.action = strUrl + "downFileAction&name=发货查询.xls&fileType=excel&classname= " + classname + window.opener.condition;
		
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
 


 <!-- ======== 标题开始 ======== -->
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
	 <td height="40" colspan="<%=12%>" valign="bottom"><div align="center" class="style2">发&nbsp;货&nbsp;查&nbsp;询&nbsp;报&nbsp;表</div></td>
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
     <td class="list_title_td">行号</td>
     <td class="list_title_td">单据编号</td>
     <td class="list_title_td">客户</td>
     <td class="list_title_td">车牌号</td>
     <td class="list_title_td">车位</td>
     <td class="list_title_td">品名</td>
     <td class="list_title_td">托盘条码</td>
     <td class="list_title_td">货位</td>
     <td class="list_title_td">数量</td>
     
     <td class="list_title_td">毛重</td>
     <td class="list_title_td">净重</td>
     <td class="list_title_td">体积</td>
   </tr>
   
<%

	List ls = (List)request.getAttribute("exResultList");
if(ls != null && ls.size()>0){
	
	//保留小数2位
	NumberFormat nbf=NumberFormat.getInstance();      
	nbf.setMinimumFractionDigits(2);
	nbf.setMaximumFractionDigits(2); 
	
	Object[] obj = null;
	
	String invoiceid = null;	// 单据编号
	String customer = null;     // 客户
	String vehicleno = null;	// 车牌号
	String vehiclepos = null;	// 车位
	String product = null;		// 产品
 	String traycode = null;     // 托盘条码
 	String cargospace = null;   // 货位
 	String jobid = null;		// 作业号
	double jobnum = 0;			// 数量
	double volume = 0;         	// 体积
		double weight = 0;         	// 重量
	double netweight = 0;		// 净重
	
	for(int i = 0; i < ls.size(); i++){
		
		obj = (Object[])ls.get(i);
		invoiceid = obj[0] == null ? "" : obj[0].toString();	// 单据编号
		customer = obj[1] == null ? "" : obj[1].toString();		// 客户
		vehicleno = obj[2] == null ? "" : obj[2].toString();	// 车牌号
		vehiclepos = obj[3] == null ? "" : obj[3].toString();	// 车位
		product = obj[4] == null ? "" : obj[4].toString();		// 产品
		traycode = obj[5] == null ? "" : obj[5].toString();		// 托盘条码
		cargospace = obj[6] == null ? "" : obj[6].toString();	// 货位
		
		//jobid = obj[7] == null ? "" : obj[7].toString();		// 作业号
		
		jobnum = Double.parseDouble(obj[7].toString().trim());			// 数量
		//boxnum = Long.parseLong(obj[9].toString());				// 箱数
		//boxnum =(Long)obj[9];
		volume = Double.parseDouble(obj[8].toString());      	// 体积
			weight = Double.parseDouble(obj[9].toString());     	// 重量
		netweight = Double.parseDouble(obj[10].toString());		// 净重
		
	  	 	
%>
   <tr class="list_normal_tr">
     <td class="list_normal_td"><%=i+1%></td>
     <td class="list_normal_td"><%=invoiceid%></td>
     <td class="list_normal_td"><%=customer%></td>
     <td class="list_normal_td"><%=vehicleno%></td>
    
     <td class="list_normal_td"><%=vehiclepos%></td>
     <td class="list_normal_td"><%=product%></td>
     <td class="list_normal_td"><%=traycode%></td>
     <td class="list_normal_td"><%=cargospace%></td>
     <td class="list_normal_td"><%=(int)jobnum%></td>
     <td class="list_normal_td"><%=nbf.format(volume)%></td>
     <td class="list_normal_td"><%=nbf.format(weight)%></td>
     <td class="list_normal_td"><%=nbf.format(netweight)%></td>
   </tr>
   
<%
		}
	}
%>
 </table>
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm1' target="_self"  style='display:none'></FORM>
</body>
</html>
