<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundRequestInvoiceHeader" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.BindingRecord" %>
<%
	InboundRequestInvoiceHeader inBound = (InboundRequestInvoiceHeader)request.getAttribute("invoice"); 
	
	String departname = "";         //生产车间
	String warehousename = "";    	//仓库
	String invoicetypename = "";    //入库类型
	
	String createmanname = "";     //制单人
	String invoicedate = "";      //制单日期
	String instockid = "";        //单据编号
	
	if(inBound != null){
		departname = inBound.getDepartname();
		warehousename = inBound.getWarehousename();
		invoicetypename = inBound.getInvoicetypename();
		
		createmanname = inBound.getCreatemanname();
		invoicedate = inBound.getInvoicedate();
		instockid = inBound.getInstockid();
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
	//打开Excel报表
	function openexcel()
	{
		
		
		var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
		
		var classname = "com.wms3.bms.standard.action.report.otherinbound.inbound_print_cpbdExcel";
		
		document.tempForm1.action = strUrl + "downFileAction&name=其他入库绑定单.xls&fileType=excel&classname= " + classname +"&instockid="+"<%=instockid%>";
		
		document.tempForm1.submit();
		
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
		color: #000000;
	}
	.style2 {
		font-weight: bold; 
		color: #000000;
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
	 <td height="20" colspan="6" valign="bottom"><div align="center" class="list_title_tdpr">其他入库申请绑定记录</div></td>
   </tr>
   <tr>
	 <td height="10" colspan="6"></td>
   </tr>
   <tr>
	 <td height="20"><div align="left"  class="list_title_tdpr">生产车间：<%=departname==null?"":departname%></div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr">收货仓库：<%=warehousename==null?"":warehousename%></div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr">入库类型：<%=invoicetypename==null?"":invoicetypename%></div></td>
   </tr>
   <tr>
	 <td height="20"><div align="left"  class="list_title_tdpr">制单人：<%=createmanname==null?"":createmanname%></div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr">制单日期：<%=invoicedate==null?"":invoicedate%></div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr">单据编号：<%=instockid==null?"":instockid%></div></td>
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
	if(request.getAttribute("lsrecord") != null)
	{
		list = (List)request.getAttribute("lsrecord");
	}
 %>
   <tr class="list_title_tr">
     <td class="list_title_td">产品编码</td>
     <td class="list_title_td">品名</td>
     <td class="list_title_td">生产日期</td>
     <td class="list_title_td">批号</td>
     <td class="list_title_td">单位</td>
     <td class="list_title_td">数量</td>
     <td class="list_title_td">托盘条码</td>
     <td class="list_title_td">绑定人</td>
   </tr>
<%
    if(list!=null && !list.isEmpty()){
    
    	//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		BindingRecord detail = null;	//入库申请单详细  
        
      	String productid;		
		String productCode;//产品编码
	  	String productName;		//品名
      	String printdate;		//生产日期
      	String lotinfo;			//批号
      	String unitName;		//单位
      	double num;	            //数量
		String traycode;        //托盘条码
		String bandmanname;     //绑定人
     	
		for(int i = 0; i < list.size(); i++){
			detail = (BindingRecord)list.get(i);
			
	      	productid = detail.getProductid();		
	      	productCode = detail.getProductCode();
	      	productName = detail.getProductName();   	
         	printdate = detail.getPrintdate(); 		 
         	lotinfo = detail.getLotinfo();       		
         	unitName = detail.getM_strUnitName();       		
	      	num = detail.getNum();		
		  	traycode = detail.getTraycode();		
		  	bandmanname = detail.getBandmanname();	
%>	
		 	
	<tr class="list_normal_tr">
	  <td class="list_normal_td"><%=productCode==null?"":productCode%></td>
	  <td class="list_normal_td"><%=productName==null?"":productName%></td>
      <td class="list_normal_td"><%=printdate==null?"":printdate%></td>
	  <td class="list_normal_td"><%=lotinfo==null?"":lotinfo%></td>
	  <td class="list_normal_td"><%=unitName==null?"":unitName%></td>
      <td class="list_normal_td"><%=nbf.format(num)%></td>
      <td class="list_normal_td"><%=traycode==null?"":traycode%></td>
      <td class="list_normal_td"><%=bandmanname==null?"":bandmanname%></td>
   </tr>
<%
        }
    }
%>	
 </table>
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm1' target="_self"  style='display:none'></FORM>
</body>
</html>