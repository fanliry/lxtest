<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@page import="java.util.HashMap"%>
<%@page import="com.wms3.bms.standard.entity.base.BaseLotSet"%>
<%@page import="com.wms3.bms.standard.entity.inventory.InventoryTransaction"%>
<%@page import="java.lang.reflect.Field"%>
<%
	String list_items = (String)request.getAttribute("list_items");
	int itemscount = 0;
	String[] items = {""};
	if(list_items != null){
		items = list_items.split(",");
		itemscount = items.length;
	}
%>
<%
	HashMap<String, List> hsLot = null;	//所有要显示的批次
	List<BaseLotSet> lsLot = null;  	    //显示的批次属性列表
	if(request.getSession(false).getAttribute("viewLot") != null){
		hsLot = (HashMap)request.getSession(false).getAttribute("viewLot");
		if(hsLot != null){
			lsLot = hsLot.get("kcjy");	//库存盘点显示
		}
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
		alert(window.opener.condition);
		alert(window.opener.getPostCon());
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
	 <td height="40" colspan="<%=12 + itemscount%>" valign="bottom"><div align="center" class="style2">库&nbsp;存&nbsp;交&nbsp;易&nbsp;报&nbsp;表</div></td>
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
   <tr>
     <td class="list_title_td">行号</td>
     <td class="list_title_td">作业ID</td>
     <td class="list_title_td">交易时间</td>
     <td class="list_title_td">FM库区</td>
     <td class="list_title_td">FM货位</td>
     <td class="list_title_td">FM品名规格</td>
     <td class="list_title_td">FM客户</td>
     <td class="list_title_td">FM托盘条码</td>
<%
	int iLine = 0;	//显示的批次属性个数
	BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);			
%>
	 <td class="list_title_td">FM<%=lotSet.getLotname()%></td>
<%
		}
	}
%>

     <td class="list_title_td">FM数量</td>
     <td class="list_title_td">TO库区</td>
     <td class="list_title_td">TO货位</td>
     <td class="list_title_td">TO品名规格</td>
     <td class="list_title_td">TO客户</td>
     <td class="list_title_td">TO托盘条码</td>
<%
//	int iLine = 0;	//显示的批次属性个数
	//BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);			
%>
	 <td class="list_title_td">TO<%=lotSet.getLotname()%></td>
<%
		}
	}
%>
     <td class="list_title_td">TO数量</td>
     <td class="list_title_td">操作人员</td>
     
   </tr>
<%
	List<InventoryTransaction> ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0)
	{
		InventoryTransaction iTransaction = null;
		String transactiontype;		//交易类型
		String transstatus;			//交易状态
		String doctype;				//单证类型
		String docid;				//单证ID
		String doclineno;			//单证行号
		String fmcargo_space_id;	//FM货位ID
		String fmwh_area_id;		//FM库区ID
		String fmcustomerid;		//FM客户ID
		String fmpackid;			//FM包装ID
		String fmpunit;				//FM计量单位
		String fmproductid;			//FM产品ID
		String tocargo_space_id;	//TO货位ID
		String towh_area_id;		//TO库区ID
		String tocustomerid;		//TO客户ID
		String topackid;		//TO包装ID
		String topunit;			//TO计量单位
		String toproductid;		//TO产品ID
		String totraycode;		//TO托盘条码
		String injobid;			//作业ID
		String injobetailid;		//作业详细ID
		String transactiontime;		//操作时间
		String createmanid;		//操作人
		String inventoryid;		//库存ID
		double fmnum;			//FM库存数量
		double fmvolume;		//FM库存体积
		double fmweight;		//FM库存重量
		double fmnetweight;		//FM库存净重
		double tonetweight;		//TO库存净重
		double toweight;		//TO库存重量
		double tonum;			//TO库存数量
		String reasoncode;		//原因代码
		String reason;			//原因
		double tovolume;		//TO库存体积
		String fmtraycode;		//FM托盘条码
		String warehouseid;		//仓库ID
	    
	    //新增字段
	    String boxcode;            //箱条码
	    String productcode;        //产品条码

  	 	for(int i=0; i<ls.size(); i++) 
  	 	{
  	 		iTransaction = ls.get(i);
  	 		warehouseid = iTransaction.getWarehouseid();				//仓库ID
  	 		transactiontype = iTransaction.getTransactiontype();		//交易类型
  			transstatus = iTransaction.getTransstatus();			    //交易状态
  			doctype = iTransaction.getDoctype();						//单证类型
  			docid = iTransaction.getDocid();							//单证ID
  			doclineno = iTransaction.getDoclineno();					//单证行号
  			fmcargo_space_id = iTransaction.getFmcargo_space_name();		//FM货位名
  			fmwh_area_id = iTransaction.getFmwh_area_name();				//FM库区ID
  			fmcustomerid = iTransaction.getFmcustomername();				//FM客户ID
  			fmpackid = iTransaction.getFmpackid();						//FM包装ID
  			fmpunit = iTransaction.getFmpunit();						//FM计量单位
  			fmnum = iTransaction.getFmnum();							//FM库存数量
  			fmproductid = iTransaction.getFmproductname();				//FM产品ID
 			fmvolume = iTransaction.getFmvolume();						//FM库存体积
  			fmweight = iTransaction.getFmweight();						//FM库存重量
  			tocargo_space_id = iTransaction.getTocargo_space_name();		//TO货位ID
  			towh_area_id = iTransaction.getTowh_area_name();				//TO库区ID
  			tocustomerid = iTransaction.getTocustomername();				//TO客户ID
  			topackid = iTransaction.getTopackid();						//TO包装ID
  			topunit = iTransaction.getTopunit();						//TO计量单位
  			toproductid = iTransaction.getToproductname();				//TO产品ID
  			totraycode = iTransaction.getTotraycode();					//TO托盘条码
  			injobid = iTransaction.getInjobid();						//作业ID
  			injobetailid = iTransaction.getInjobetailid();				//作业详细ID
  			transactiontime = iTransaction.getTransactiontime();		//操作时间
  			createmanid = iTransaction.getCreatemanid();				//操作人
  			inventoryid = iTransaction.getInventoryid();				//库存ID
  			fmnetweight = iTransaction.getFmnetweight();				//FM库存净重
  			tonetweight = iTransaction.getTonetweight();				//TO库存净重
  			toweight = iTransaction.getToweight();						//TO库存重量
  			tonum = iTransaction.getTonum();							//TO库存数量
  			reasoncode = iTransaction.getReasoncode();					//原因代码
  			reason = iTransaction.getReason();							//原因
  			tovolume = iTransaction.getTovolume();						//TO库存体积
  			fmtraycode = iTransaction.getFmtraycode();					//FM托盘条码  
%>
   <tr class="list_normal_tr">
     <td class="list_normal_td"><%=i+1%></td>
     <td class="list_normal_td"><%=injobid%></td>
     <td class="list_normal_td"><%=transactiontime%></td>
     <td class="list_normal_td"><%=fmwh_area_id%></td>
     <td class="list_normal_td"><%=fmcargo_space_id%></td>
     <td class="list_normal_td"><%=fmproductid%></td>
     <td class="list_normal_td"><%=fmcustomerid%></td>
     <td class="list_normal_td"><%=fmtraycode%></td>
<%
	if(lsLot != null){
		Field field = null; 
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);
			field = iTransaction.getClass().getDeclaredField("fm"+lotSet.getLotid());			
%>
	 		 <td class="list_normal_td"><%=field.get(iTransaction)==null?"":(String)field.get(iTransaction)%></td>
<%
		}
	}
%>  
     <td class="list_normal_td"><%=fmnum%></td>
     <td class="list_normal_td"><%=towh_area_id%></td>
     <td class="list_normal_td"><%=tocargo_space_id%></td>
     <td class="list_normal_td"><%=toproductid%></td>
     <td class="list_normal_td"><%=tocustomerid%></td>
     <td class="list_normal_td"><%=totraycode%></td>
<%
	if(lsLot != null){
		Field field = null; 
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);
			field = iTransaction.getClass().getDeclaredField("to"+lotSet.getLotid());			
%>
	 		 <td class="list_normal_td"><%=field.get(iTransaction)==null?"":(String)field.get(iTransaction)%></td>
<%
		}
	}
%>
     <td class="list_normal_td"><%=tonum%></td>
     <td class="list_normal_td"><%=createmanid%></td>
   </tr>
<%
		}
	}
%>

 </table>
</body>
</html>
