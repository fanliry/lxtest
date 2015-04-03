<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJob" %>
<%@ page import="com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail" %>
<%
   
    //表中的列排序
	List ls = (List)request.getAttribute("exResultList");
	int len = 0;
	if(ls!=null && ls.size()>0){
	  len = ls.size();
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
		
		var classname = "com.wms3.bms.standard.action.report.inbound.inbound_print_rkQueryExcel";
		
		document.tempForm1.action = strUrl + "downFileAction&name=入库查询.xls&fileType=excel&classname= " + classname + window.opener.condition;
		
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

<body >

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
	 <td height="20" colspan="6" valign="bottom"><div align="center" class="list_title_tdpr">入库查询</div></td>
   </tr>
   <tr>
   	<td colspan="4"><div align="center" id="img"></div></td>
   </tr>
   
  
 </table>
 <!-- ======== 标题结束 ======== -->

 <table width="100%" height="20"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>
 
 <!-- ======== 任务列表开始 ======== -->
<table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="outer_table">
   <tr>
     <td class="list_title_td" align="center" width="50">行号</td>
     <td class="list_title_td" align="center">作业号</td>
     <td class="list_title_td" align="center">作业类型</td>
     <td class="list_title_td" align="center">产品编码</td>
      <td class="list_title_td" align="center">品名</td>
     <td class="list_title_td" align="center">具体批号</td>
     <td class="list_title_td" align="center">生产日期</td>
     <td class="list_title_td" align="center">单位</td>
     <td class="list_title_td" align="center">数量</td>
     <td class="list_title_td" align="center">仓库</td>
    <td class="list_title_td" align="center">巷道</td>
     <td class="list_title_td" align="center">货位</td>
     <td class="list_title_td" align="center">托盘条码</td>
     <td class="list_title_td" align="center">输送机号</td>
     <td class="list_title_td" align="center">生成时间</td>
     <td class="list_title_td" align="center">完成时间</td>
     <td class="list_title_td" align="center">作业来源</td>
   </tr>
<%
	if(ls != null && ls.size()>0){
		
		Object[] obj = null;
		InoutJob job = null;
		InoutJobdetail detail = null;
     	
     	String jobid;      	//作业号
     	String jobtype;    //作业类型 （回流的作业类型为 入库类型的hl值）
     	String productCode;//产品编码
     	String m_strProductName = null;	 //品名
		String lotinfo = null;	         //具体批号
		String printdate = null;	     //生产日期
		String m_strUnitName = null;	//单位
		double jobnum = 0;			    //数量
		String warehouseName;  	//仓库
     	String alley;    		//巷道
     	String cargospace;     	//货位
     	String traycode;     	//托盘条码
     	String snumber;    		//输送号
     	String createtime;  	//生成时间
     	String finishtime; 		//完成时间
     	String invoicetype;//1：按入库收货单来生成的作业，2：按出库单生成的作业，3： 回流直接生成的作业（从暂存到立体库）
     	
     	
		
		for(int i = 0; i < ls.size(); i++){
			
			obj = (Object[])ls.get(i);
			job = (InoutJob)obj[0];
			detail = (InoutJobdetail)obj[1];
			
			jobid = job.getJobid();      			//作业号
     		jobtype = job.getJobtypeName();
			productCode = detail.getProductcode();
			m_strProductName = detail.getM_strProductName();
			lotinfo = detail.getLotinfo();
			printdate = detail.getPrintdate();
			m_strUnitName = detail.getM_strUnitName();
			jobnum = detail.getJobnum();
			
     		warehouseName = job.getWarehousename();	//仓库
     		alley = job.getTcargoAlleyName();		//巷道
     		cargospace = job.getTcargoSpaceName(); 	//货位
     		traycode = job.getTraycode();     		//托盘条码
     		snumber = job.getSnumber();    			//输送号
     		createtime = job.getCreatetime();  		//生成时间
     		finishtime = job.getFinishtime(); 		//完成时间
     		invoicetype = job.getInvoicetypename();
		
%>
	      <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center" width="50px"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=jobid==null?"":jobid%></td>
     <td class="TD_LIST" align="center"><%=jobtype==null?"":jobtype%></td>
     <td class="TD_LIST" align="center"><%=productCode==null?"":productCode%></td>
     <td class="TD_LIST" align="center"><%=m_strProductName==null?"":m_strProductName%></td>
     <td class="TD_LIST" align="center"><%=lotinfo==null?"":lotinfo%></td> 
     <td class="TD_LIST" align="center"><%=printdate==null?"":printdate%></td>
     <td class="TD_LIST" align="center"><%=m_strUnitName==null?"":m_strUnitName%></td>
     <td class="TD_LIST" align="center"><%=jobnum%></td>
     <td class="TD_LIST" align="center"><%=warehouseName==null?"":warehouseName%></td>
     <td class="TD_LIST" align="center"><%=alley==null?"":alley%></td>
     <td class="TD_LIST" align="center"><%=cargospace==null?"":cargospace%></td>
     <td class="TD_LIST" align="center"><%=traycode==null?"":traycode%></td>
     <td class="TD_LIST" align="center"><%=snumber==null?"":snumber%></td>
     <td class="TD_LIST" align="center"><%=createtime==null?"":createtime%></td>
     <td class="TD_LIST" align="center"><%=finishtime==null?"":finishtime%></td>
     <td class="TD_LIST" align="center"><%=invoicetype==null?"":invoicetype%></td>
   </tr>
<%
		}
	}
%>  
 </table>
 
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm1' target="_self"  style='display:none'></FORM>
</body>
</html>