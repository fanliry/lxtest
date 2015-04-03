<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail" %>
<%
	InboundHeader inBound = (InboundHeader)request.getAttribute("invoice"); 
	
	String instockid = "";      //入库单据编号
	String invoicedate = "";    //单据日期
	String intype = "";         //入库类型
	String ownername = "";		//货主
	if(inBound != null){
		instockid = inBound.getInstockid();
		invoicedate = inBound.getInvoicedate();
		intype = inBound.getInvoicetypename();
		ownername = "";
	}
%>
<html>
<head>
<title>入库单打印</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
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
	 <td height="40" colspan="8" valign="bottom"><div align="center" class="style2">入&nbsp;库&nbsp;单</div></td>
   </tr>
   <tr>
	 <td height="20" colspan="8" valign="top"><div align="center">
	   <hr align="center" width="450" color="#006699" noshade="noshade" size="1">
	 </div></td>
   </tr>
   <tr>
	 <td height="20"><div align="left">单据号：<%=instockid%></div></td>
	 <td height="20"><div align="left">单据类型：<%=intype==null?"":intype%></div></td>
	 <td height="20"><div align="left">单据日期：<%=invoicedate==null?"":invoicedate%></div></td>
	 <td height="20"><div align="left">货主：<%=ownername==null?"":ownername%></div></td>
   </tr>
 </table>
 <!-- ======== 标题结束 ======== -->

 <table width="100%" height="20"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>
 
 <!-- ======== 任务列表开始 ======== --> 
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="outer_table">
 <%
	List list = null;
	if(request.getAttribute("invoicedetail") != null)
	{
		list = (List)request.getAttribute("invoicedetail");
	}
 %>
   <tr class="list_title_tr">
	  <td class="list_title_td">行号</td>
	  <td class="list_title_td">品名</td>
	  <td class="list_title_td">单位</td>
	  <td class="list_title_td">生产日期</td>
	  <td class="list_title_td">产品状态</td>
      <td class="list_title_td">数量</td>
   </tr>
<%
    if(list!=null && !list.isEmpty()){
    
    	//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		InboundDetail detail = null;
		String instockdetailid = null;//入库单详细ID
		String product = null;		// 品名
		String eunit = null;		// 单位
		double  invoicenum = 0;		// 数量
		
		String printdate = "";
		String statusName = "";

     	
		for(int i = 0; i < list.size(); i++){
			detail = (InboundDetail)list.get(i);
			
			instockdetailid = detail.getInstockdetailid();	// 入库单详细ID
			product = detail.getProductName();			// 品名
			eunit = detail.getM_strUnitName();				// 单位
			invoicenum = detail.getInnum();	// 数量
			printdate = detail.getPrintdate();
			statusName = detail.getProductStatusName();
	      	 
%>	
		 	
	<tr class="list_normal_tr">
	  <td class="list_normal_td"><%=i+1%></td>
	  <td class="list_normal_td"><%=product==null?"":product%></td>
	  <td class="list_normal_td"><%=eunit==null?"":eunit%></td>
	  <td class="list_normal_td"><%=printdate==null?"":printdate%></td>
	  <td class="list_normal_td"><%=statusName==null?"":statusName%></td>
     <td class="list_normal_td"><%=nbf.format(invoicenum)%></td>
   </tr>
<%
        }
    }
%>	

 </table>
</body>
</html>