<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundReceiptHeader" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundReceiptDetail" %>
<%
	InboundReceiptHeader inBound = null; 
	if(request.getAttribute("invoice") != null){
		inBound = (InboundReceiptHeader)request.getAttribute("invoice");
	}
	String strInvoiceId = "";	//收货单ID
	String ownername = "";		//货主
	if(inBound != null){
		strInvoiceId = inBound.getReinvoiceid();
		ownername = inBound.getOwnername();
	}
%>
<html>
<head>
<title>打印上架任务</title>
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
	 <td height="20" colspan="2"><div align="center" class="style2">收货单</div></td>
   </tr>
   <tr>
	 <td height="10" colspan="2" valign="top"><div align="center">
	   <hr align="center" width="450" color="#006699" noshade="noshade" size="1">
	 </div></td>
   </tr>
   <tr>
	 <td height="20"><div align="left" class="style2"><img src="<%=request.getContextPath()%>/barcode?msg=<%=strInvoiceId%>&type=code128&fmt=jpeg" height="20px" width=120px /><%=strInvoiceId%></div></td>
	 <td height="20"><div align="left" class="style2">货主：<%=ownername%></div></td>
   </tr>
 </table>
 <!-- ======== 标题结束 ======== -->

 <table width="100%" height="10"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>
 
 <!-- ======== 任务列表开始 ======== --> 
 <table width="910" border="1" align="center" cellpadding="0" cellspacing="0" class="outer_table">
 <%
	List list = null;
	if(request.getAttribute("invoicedetail") != null)
	{
		list = (List)request.getAttribute("invoicedetail");
	}
 %>
   <tr class="list_title_tr">
	  <td width="40" class="list_title_td">序号</td>
	  <td width="120" class="list_title_td">产品</td>
	  <td width="80" class="list_title_td">行状态</td>
	  <td width="100" class="list_title_td">行代码</td>
	  <td width="90" class="list_title_td">中文描述</td>
	  <td width="120" class="list_title_td">包装</td>
	  <td width="60" class="list_title_td">单位</td>
	  <td width="80" class="list_title_td">预期到货数量</td>
	  <td width="80" class="list_title_td">预期到货净重</td>
	  <td width="70" class="list_title_td">收货数量</td>
	  <td width="70" class="list_title_td">收货净重</td>
	</tr>
<%
    if(list!=null && !list.isEmpty()){
    	//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
		
		InboundReceiptDetail inDetail = null;//收货单详细  
		String instockdetailid;	//收货单详细ID
      	String productid;		//品名规格
      	double invoicenum;		//最小单位数量
	  	double weight;			//重量
      	double netweight;		//净重

      	double recnum;			//最小单位数量（收货）
      	double reweight;		//收货重量
      	double renetweight;		//收货净重


      	String m_strProductName;    // 产品
      	String m_strPackName;       //包装名称
        String m_strUnitName;       //单位名称

      	String linestatus;      //单据行状态0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架
		for(int i = 0; i < list.size(); i++)
		{
			inDetail = (InboundReceiptDetail)list.get(i);
			 instockdetailid = inDetail.getReincoicedetailid();	//入库单详细ID
	      	 productid = inDetail.getProductid();	//品名规格
	      	 invoicenum = inDetail.getInvoicenum();	//最小单位数量
		  	 weight = inDetail.getWeight();			//重量
	      	 netweight = inDetail.getNetweight();	//净重
	      	 m_strUnitName = inDetail.getM_strUnitName();	//单位名
	      	 m_strProductName = inDetail.getM_strProductName();    // 产品
	    
	      	 recnum = inDetail.getRecnum();				//最小单位数量（收货）
	      	 reweight = inDetail.getReweight();			//收货重量
	      	 renetweight = inDetail.getRenetweight();	//收货净重
			 m_strPackName = inDetail.getM_strPackName();//包装名称

	      	 linestatus = inDetail.getM_strStatusName();//单据行状态0-开单1-部分收货 2-完全收货 3-已码盘 4-部分上架5-完全上架
	      	 
%>	
		 	
	<tr class="list_normal_tr">
	  <td class="list_normal_td"><%=i+1%></td>
	  <td class="list_normal_td">
	  	<div align="center"> 
	  		<img src="<%=request.getContextPath()%>/barcode?msg=<%=instockdetailid%>&type=code128&fmt=jpeg" height="20px" width=120px />
	  	</div>
	  </td>
	  <td class="list_normal_td"><%=linestatus%></td>
	  <td class="list_normal_td"><%=instockdetailid%></td>
	  <td class="list_normal_td"><%=m_strProductName == null ? "&nbsp;" : m_strProductName%></td>
	  <td class="list_normal_td"><%=m_strPackName == null ? "&nbsp;" : m_strPackName%></td>
	  <td class="list_normal_td"><%=m_strUnitName == null ? "&nbsp;" : m_strUnitName%></td>
	  <td class="list_normal_td"><%=nbf.format(invoicenum)%></td>
	  <td class="list_normal_td"><%=nbf.format(netweight)%></td>
	  <td class="list_normal_td"><%=nbf.format(recnum)%></td>
	  <td class="list_normal_td"><%=nbf.format(renetweight)%></td>
	</tr>
<%
        }
    }
%>	

 </table>

 
</body>
</html>