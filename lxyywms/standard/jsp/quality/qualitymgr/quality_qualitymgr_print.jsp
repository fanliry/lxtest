<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail" %>
<%
	OutboundInvoiceHeader outBound = (OutboundInvoiceHeader)request.getAttribute("invoice"); 
	List<BaseLotSet> lsLot = (List)request.getAttribute("lsLot");  		//显示的批次属性列表
	
	String outstockid = "";     //出库单据编号
	String formdate = "";    	//单据日期
	String outtype = "";        //出库单据类型
	String customer = "";		//客户
	
	if(outBound != null){
		outstockid = outBound.getOutstockid();
		formdate = outBound.getFormdate();
		outtype = outBound.getM_strOutTypeName();
		customer = outBound.getCustomername();
	}
%>
<html>
<head>
<title>质检单打印</title>
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
	 <td height="40" colspan="8" valign="bottom"><div align="center" class="style2">质&nbsp;检&nbsp;单</div></td>
   </tr>
   <tr>
	 <td height="20" colspan="8" valign="top"><div align="center">
	   <hr align="center" width="450" color="#006699" noshade="noshade" size="1">
	 </div></td>
   </tr>
   <tr>
	 <td height="20"><div align="left">单据号：<%=outstockid%></div></td>
	 <td height="20"><div align="left">单据类型：<%=outtype==null?"":outtype%></div></td>
	 <td height="20"><div align="left">单据日期：<%=formdate==null?"":formdate%></div></td>
	 <td height="20"><div align="left">客户：<%=customer==null?"":customer%></div></td>
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
	  <td class="list_title_td">行状态</td>
	  <td class="list_title_td">包装</td>
	  <td class="list_title_td">单位</td>
<%
	int iLine = 0;	//显示的批次属性个数
	BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);			
%>
	 <td class="list_title_td"><%=lotSet.getLotname()%></td>
<%
		}
	}
%>
     <td class="list_title_td">开单数量</td>
     <td class="list_title_td">开单毛重</td>
     <td class="list_title_td">开单净重</td>
     
     <td class="list_title_td">分配数量</td>
     <td class="list_title_td">分配毛重</td>
     <td class="list_title_td">分配净重</td>
     
     <td class="list_title_td">发货数量</td>
     <td class="list_title_td">发货毛重</td>
     <td class="list_title_td">发货净重</td>
   </tr>
<%
    if(list!=null && !list.isEmpty()){
    
    	//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		OutboundInvoiceDetail detail = null;	//出库单详细  
		
      	String linestatus;     		// 行状态名
        String product;    			// 产品
      	String pack;       			// 包装名称
        String unit;       			// 单位名称
        
      	double invoicenum;		//数量
	  	double weight;			//重量
      	double netweight;		//净重
      	double assnum;			//分配数量
      	double assweight;		//分配重量
      	double assnetweight;	//分配净重
		double sendnum;         //发货数量
   		double snetweight;      //发货重重
    	double sweight;         //发货净量
     	
		for(int i = 0; i < list.size(); i++){
			detail = (OutboundInvoiceDetail)list.get(i);
			
	      	outstockid = detail.getOutstockid();			//出库单据编号
	      	linestatus = detail.getM_strStatusName();   	//状态名
         	product = detail.getM_strProductName(); 		// 产品
         	pack = detail.getM_strPackName();       		//包装名称
         	unit = detail.getM_strUnitName();       		//单位名称  
         	
	      	invoicenum = detail.getInvoicenum();		//数量
		  	weight = detail.getWeight();				//重量
	      	netweight = detail.getNetweight();			//净重
	      	
	      	assnum = detail.getAssignnum();				//分配数量
      	 	assweight = detail.getAssignweight();		//分配重量
      	 	assnetweight = detail.getAssignnetweight();	//分配净重
      	 	
      	 	sendnum = detail.getSendnum();				//发货数量
      	 	sweight = detail.getSweight();				//发货重量
      	 	snetweight = detail.getSnetweight();		//发货净重
	      	 
%>	
		 	
	<tr class="list_normal_tr">
	  <td class="list_normal_td"><%=i+1%></td>
	  <td class="list_normal_td"><%=product==null?"":product%></td>
	  <td class="list_normal_td"><%=linestatus==null?"":linestatus%></td>
	  <td class="list_normal_td"><%=pack==null?"":pack%></td>
	  <td class="list_normal_td"><%=unit==null?"":unit%></td>
<%
	if(lsLot != null){
		Field field = null; 
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);
			field = detail.getClass().getDeclaredField(lotSet.getLotid());			
%>
	 		 <td class="list_normal_td"><%=field.get(detail)==null?"":(String)field.get(detail)%></td>
<%
		}
	}
%>    
     <td class="list_normal_td"><%=nbf.format(invoicenum)%></td>
     <td class="list_normal_td"><%=nbf.format(weight)%></td>
     <td class="list_normal_td"><%=nbf.format(netweight)%></td>
     
     <td class="list_normal_td"><%=nbf.format(assnum)%></td>
     <td class="list_normal_td"><%=nbf.format(assweight)%></td>
     <td class="list_normal_td"><%=nbf.format(assnetweight)%></td>
     
     <td class="list_normal_td"><%=nbf.format(sendnum)%></td>
     <td class="list_normal_td"><%=nbf.format(sweight)%></td>
     <td class="list_normal_td"><%=nbf.format(snetweight)%></td>
   </tr>
<%
        }
    }
%>	

 </table>

 
</body>
</html>