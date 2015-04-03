<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage"%>
<%@page import="com.wms3.bms.standard.entity.base.BaseCargospace"%>

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
		
		var classname = "com.wms3.bms.standard.action.report.inventory.InventoryQueryExcel";
		
		
		document.tempForm1.action = strUrl + "downFileAction&name=库存查询.xls&fileType=excel&classname= " + classname + window.opener.condition;
		
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
	 <td height="40" colspan="<%=18 + iLine%>" valign="bottom"><div align="center" class="style2">库&nbsp;存&nbsp;报&nbsp;表</div></td>
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
     <td class="list_title_td">库区</td>
     <td class="list_title_td">货位</td>
     <td class="list_title_td">产品编码</td>
     <td class="list_title_td">品名</td>
     <td class="list_title_td">入库日期</td>
     <td class="list_title_td">进场编号</td>
     <td class="list_title_td">数量</td>
     <td class="list_title_td">生产日期</td>
     <td class="list_title_td">库存状态</td>
     <td class="list_title_td">物品状态</td>
   </tr>
   
<%

List ls = (List)request.getAttribute("exResultList");
if(ls != null && ls.size() > 0)
{
	Object[] ob = null;
    InventoryStorage storage = null;
    BaseCargospace space = null;
	String whArea;				//库区
		String cargoSpace;			//货位
	 	String product;				//产品
	 	String lotnumber;		    //批号
	 	String instockid;           //入库单
	 	String requestid;           //申请单
	 	String traycode;        	//托盘条码
	String punit;				//单位
	double pnum;            	//库存数量
	String indate;             	//入库时间
	String intype;             	//入库方式 1.联机 2.脱机
    String productcode;         //产品编码
    String printdate;         //生产日期
    String csstauts;         //库存状态
    String productstatus;     //物品状态
	 	for(int i=0; i<ls.size(); i++) 
	 	{
	 	    ob = (Object[])ls.get(i);
	 		storage = (InventoryStorage)ob[0];
	 		space = (BaseCargospace)ob[1];
	 		whArea = storage.getWhAreaName();		//库区
	 		cargoSpace = storage.getCargoSpaceName();//货位
	 		product = storage.getProductName();		//产品
	 		lotnumber = storage.getLotinfo();		//批号
	 		traycode = storage.getTraycode();		//托盘条码
	 		punit = storage.getPunitname();			//单位
	 		productcode = storage.getProductcode(); //产品编码		
			pnum = storage.getPnum();           //库存数量
			indate = storage.getIndate();       //入库时间
	 		intype = storage.getIntype();		//入库方式 1.联机 2.脱机
	 		requestid = storage.getRequestid();
	 		instockid = storage.getInstockid();
	 		printdate = storage.getProductdate(); //生产日期
	 		csstauts = space.getCsstatusname(); 
	 		productstatus = storage.getProductstatus();
	  	 	
%>
   <tr class="list_normal_tr">
     <td class="TD_LIST1" align="center"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=whArea == null ? "" : whArea%></td>
     <td class="TD_LIST" align="center"><%=cargoSpace == null ? "" : cargoSpace%></td>
     <td class="TD_LIST" align="center"><%=productcode == null ? "" : productcode%></td>
     <td class="TD_LIST" align="center"><%=product == null ? "" : product%></td>
     <td class="TD_LIST" align="center"><%=indate == null ? "" : indate%></td>
     <td class="TD_LIST" align="center"><%=lotnumber == null ? "" : lotnumber%></td>
     <td class="TD_LIST" align="center"><%=pnum == 0.0 ? 0.0 : pnum%></td>
     <td class="TD_LIST" align="center"><%=printdate == null ? "" : printdate%></td>
     <td class="TD_LIST" align="center"><%=csstauts == null ? "" : csstauts%></td>
     <td class="TD_LIST" align="center"><%=productstatus == null ? "" : productstatus%></td>
   </tr>
   
<%
		}
		 }
%>
 
 </table>
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm1' target="_self"  style='display:none'></FORM>
</body>
</html>
