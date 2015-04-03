<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundPoHeader" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundPoDetail" %>
<%
	InboundPoHeader po = (InboundPoHeader)request.getAttribute("po"); 
	
	String poid = "";     //出库单据编号
	String formdate = "";    	//单据日期
	String potype = "";        //出库单据类型
	String supplier = "";		//供应商
	
	if(po != null){
		poid = po.getPoid();
		formdate = po.getCreatetime();
		potype = po.getPotypeName();
		supplier = po.getSupplierName();
	}
%>
<html>
<head>
<title>出库单打印</title>
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
	 <td height="40" colspan="8" valign="bottom"><div align="center" class="style2"><%=potype %></div></td>
   </tr>
   <tr>
	 <td height="40" colspan="8"><div align="center" class="style2"><img src="<%=request.getContextPath()%>/barcode?msg=<%=poid%>&type=code128&fmt=jpeg" height="40px" width=220px /><%=poid%></div></td>
   </tr>
   <tr>
	 <td height="20" colspan="8" valign="top"><div align="center">
	   <hr align="center" width="450" color="#006699" noshade="noshade" size="1">
	 </div></td>
   </tr>
   <tr>
	 <td height="20"><div align="left">单据号：<%=poid%></div></td>
	 <td height="20"><div align="left">单据日期：<%=formdate==null?"":formdate%></div></td>
	 <td height="20"><div align="left">供应商：<%=supplier==null?"":supplier%></div></td>
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
	if(request.getAttribute("podetail") != null)
	{
		list = (List)request.getAttribute("podetail");
	}
 %>
   <tr class="list_title_tr">
	 <td class="list_title_td" width="50px">行号</td>
     <td class="list_title_td">状态 </td>
	 <td class="list_title_td">产品</td>
	 <td class="list_title_td">品号</td>
	 <td class="list_title_td">规格</td>
	 <td class="list_title_td">单位</td>
	 <td class="list_title_td">进厂编号</td>
	 <td class="list_title_td">原厂编号</td>
	 <td class="list_title_td">数量</td>
	 <td class="list_title_td">已使用数量</td>
	 <td class="list_title_td">检验单号</td>
   </tr>
<%
	if(list != null && list.size()>0){
		InboundPoDetail poDetail = null;
		String podetailid="";//PO行号
		String polinestatus="";//PO行状态 0-开单
		String productid="";// 产品ID
		String productName="";// 产品名
		String packid="";//包装ID
		String eunit="";// 单位
		String productCode = "";//ERP物料编码
		double ponum=0.0;//总数量
		double useponum=0.0;//已经使用数量
		double nouseponum=0.0;//未使用数量
		double price = 0.0;//单价
		String strvalue="";
		String eunitName = "";
        String spec="";
        
        String lotinfo = "";
        String lotinfo2 = "";
        String checkid = "";

		for(int i=0;i<list.size();i++){
			poDetail = (InboundPoDetail)list.get(i);
			podetailid = poDetail.getPodetailid();
			polinestatus = poDetail.getPolinestatusName();
			productid = poDetail.getProductid();
			productName = poDetail.getM_strProductName();
			eunit = poDetail.getEunit();
			ponum = poDetail.getPonum();
			price = poDetail.getPrice();
			useponum = poDetail.getUseponum();
			eunitName = poDetail.getM_strUnitName();
			productCode = poDetail.getProductCode();
			spec=poDetail.getM_spec();
			lotinfo = poDetail.getLotinfo();
			lotinfo2 = poDetail.getLotinfo2();
			checkid = poDetail.getCheckid();    	 
%>	
		 	
	<tr class="list_normal_tr">
	  <td class="list_normal_td" align="center" width="50px"><%=i+1%></td>
	 <td class="list_normal_td" align="center"><%=polinestatus == null ? "" : polinestatus %></td>
	 <td class="list_normal_td" align="center"><%=productName == null ? "" : productName %></td>
	 <td class="list_normal_td" align="center"><%=productCode == null ? "" : productCode %></td>
	 <td class="list_normal_td" align="center"><%=spec == null ? "" : spec %></td>
	 <td class="list_normal_td" align="center"><%=eunitName == null ? "" : eunitName %></td>
	 <td class="list_normal_td" align="center"><%=lotinfo == null ? "" : lotinfo %></td>
	 <td class="list_normal_td" align="center"><%=lotinfo2 == null ? "" : lotinfo2 %></td>
	 <td class="list_normal_td" align="center"><%=ponum %></td>
	 <td class="list_normal_td" align="center"><%=useponum %></td>
	 <td class="list_normal_td" align="center"><%=checkid == null ? "" : checkid %></td>
   </tr>
<%
        }
    }
%>	
 </table>
</body>
</html>