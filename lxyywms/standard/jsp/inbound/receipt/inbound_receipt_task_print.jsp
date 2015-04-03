<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundReceiptHeader" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction" %>
<%
	HashMap<String, List> hsLot = null;	//所有要显示的批次
	List<BaseLotSet> lsLot = null;  	//显示的批次属性列表
	if(request.getSession(false).getAttribute("viewLot") != null){
		hsLot = (HashMap)request.getSession(false).getAttribute("viewLot");
		if(hsLot != null){
			lsLot = hsLot.get("shtrans");//新建收货单->显示收货单详细	
		}
	}
	

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
	 <td height="20" colspan="2"><div align="center" class="style2">收货任务</div></td>
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
 <table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" class="outer_table">
 <%
	List list = null;
	if(request.getAttribute("tasks") != null)
	{
		list = (List)request.getAttribute("tasks");
	}
 %>
   <tr class="list_title_tr">
	  <td width="40" class="list_title_td">序号</td>
	  <td  class="list_title_td">标签</td>
	  <td  class="list_title_td">跟踪号</td>
	  <td  class="list_title_td">状态</td>
	  <td  class="list_title_td">品名</td>
	  <td  class="list_title_td">包装</td>
	  <td  class="list_title_td">单位</td>
	  <td  class="list_title_td">数量</td>
	  <td  class="list_title_td">净重</td>
	  <td  class="list_title_td">毛重</td>
	  
	  <%
	int iLine = 0;	//显示的批次属性个数
	BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = lsLot.get(k);			
%>
	 <td class="list_title_td"><%=lotSet.getLotname()%></td>
<%
		}
	}
%>   
	  
	  
	</tr>
<%
    if(list!=null && !list.isEmpty()){
    	//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
		
		InboundReceiptTransaction receiptTrans = null;//收货交易记录  
		String transid;		   //交易号
      	String productid;      //物品ID
      	String punit;          //单位
      	double num;            //数量
      	double weight;         //重量
      	double netweight;      //净重   
      	
      	String m_strProductName;// 产品
      	String m_strPackName;	// 包装

      	String strStatusName = "收货完成";	// 状态名
		for(int i = 0; i < list.size(); i++)
		{
			receiptTrans = (InboundReceiptTransaction)list.get(i);
			transid = receiptTrans.getTransreceiptid();	//交易号
      	 	productid = receiptTrans.getProductid();    //物品ID
      	 	punit = receiptTrans.getM_strUnitName();    //单位
      	 	num = receiptTrans.getRecnum();            	//数量
      	 	weight = receiptTrans.getReweight();        //重量
      	 	netweight = receiptTrans.getRenetweight();  //净重   
      	
      	 	m_strProductName = receiptTrans.getM_strProductName();// 产品
      	 	m_strPackName = receiptTrans.getM_strPackName();	  // 包装
            //1:收货完成；2:已码盘; 3:部分上架; 4：完全上架 5：取消收货
			if(receiptTrans.getTransstatus().equals("1"))
			{
				strStatusName = "收货完成";
			}else if(receiptTrans.getTransstatus().equals("2")){
			    strStatusName = "已码盘";
			}else if(receiptTrans.getTransstatus().equals("3")){
			    strStatusName = "部分上架";
			}else if(receiptTrans.getTransstatus().equals("4")){
			    strStatusName = "完全上架";
			}else if(receiptTrans.getTransstatus().equals("5")){
			    strStatusName = "取消收货";
			}
%>	
		 	
	<tr class="list_normal_tr">
	  <td class="list_normal_td"><%=i+1%></td>
	  <td class="list_normal_td">
	  	<div align="center"> 
	  		<img src="<%=request.getContextPath()%>/barcode?msg=<%=transid%>&type=code128&fmt=jpeg" height="20px" width=120px />
	  	</div>
	  </td>
	  <td class="list_normal_td"><%=transid%></td>
	  <td class="list_normal_td"><%=strStatusName%></td>
	  <td class="list_normal_td"><%=m_strProductName == null ? "&nbsp;" : m_strProductName%></td>
	  <td class="list_normal_td"><%=m_strPackName == null ? "&nbsp;" : m_strPackName%></td>
	  <td class="list_normal_td"><%=punit == null ? "&nbsp;" : punit%></td>
	  <td class="list_normal_td"><%=nbf.format(num)%></td>
	  <td class="list_normal_td"><%=nbf.format(netweight)%></td>
	  <td class="list_normal_td"><%=nbf.format(weight)%></td>
<%
	if(lsLot != null){
		Field field = null; 
		for(int k = 0; k < iLine; k++){
			lotSet = lsLot.get(k);
			field = receiptTrans.getClass().getDeclaredField(lotSet.getLotid());					
%>
	 		 <td class="list_normal_td"><%=(String)field.get(receiptTrans)%></td>
<%
		}
	}
%>	  	  
	  
	</tr>
<%
        }
    }
%>	

 </table>

 
</body>
</html>