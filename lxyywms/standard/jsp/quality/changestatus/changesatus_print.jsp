<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.inventory.InventoryQualityResult" %>
<%@ page import="com.wms3.bms.standard.entity.inventory.InventoryQualityResultDetail" %>
<%
InventoryQualityResult result = (InventoryQualityResult)request.getAttribute("result"); 
	//String instockid = 	(String)request.getAttribute("instockid"); 

	 String m_strCheckResultId = null;			/*质检结果单ID*/	
	 String m_strLotNumber=null;  		    /*批号*/
	 String m_strOpManId=null;				/*操作人ID*/
	 String m_strCreateDate=null;   			/*单据日期*/
	 String m_strNum=null;					/*数量*/
	 
	
	if(result != null){
		m_strCheckResultId = result.getM_strCheckResultId();
		m_strOpManId = result.getM_strOpManId();
		m_strCreateDate = result.getM_strCreateDate();
		m_strLotNumber = result.getM_strLotNumber();
		m_strNum = result.getM_strNum();
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
		
		var classname = "com.wms3.bms.standard.action.report.quality.changestatus_print_Excel";
		
		document.tempForm1.action = strUrl + "downFileAction&name=放行记录查询.xls&fileType=excel&classname= " + classname +"&instockid="+"<%=m_strCheckResultId%>";
		
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
 <table width="70%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
	 <td height="40" colspan="6" valign="bottom"><div align="center" class="style2">海&nbsp;正&nbsp;药&nbsp;业&nbsp;(&nbsp;杭&nbsp;州&nbsp;)&nbsp;有&nbsp;限&nbsp;公&nbsp;司</div></td>
   </tr>
   <tr>
	 <td height="20" colspan="6" valign="bottom"><div align="center" class="list_title_tdpr">放行记录查询</div></td>
   </tr>
   <tr>
   	<td colspan="6"><div align="center" id="img"></div></td>
   </tr>
   <tr>
   	<td colspan="6" height="10" ></td>
   </tr>
   <tr>
	 <td height="20"><div align="left"  class="list_title_tdpr">放行单号：<%=m_strCheckResultId==null?"":m_strCheckResultId%></div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr">操作人员：<%=m_strOpManId==null?"":m_strOpManId%></div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr">单据日期：<%=m_strCreateDate==null?"":m_strCreateDate%></div></td>
   
	 <td height="20"><div align="left"  class="list_title_tdpr">批号：<%=m_strLotNumber==null?"":m_strLotNumber%></div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr">数量：<%=m_strNum==null?"":m_strNum%></div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr"></div></td>
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
	if(request.getAttribute("exResultList") != null)
	{
		list = (List)request.getAttribute("exResultList");
	}
 %>
   <tr class="list_title_tr">
   	 <td class="list_title_td">行号</td>
     <td class="list_title_td">品名</td>
     <td class="list_title_td">创建日期</td>
     <td class="list_title_td">物品新状态</td>
     <td class="list_title_td">物品原状态</td>
     <td class="list_title_td">货位号</td>
     <td class="list_title_td">数量</td>
   </tr>
<%
    if(list!=null && !list.isEmpty()){
    
    	//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		InventoryQualityResultDetail detail=null;
        String strDId="";
        String strProductName="";
        String strCreatetime="";
        String strNewProductStatus="";
        String strOldProductStatus="";
        String strClientName="";
        String strCarspaceid="";
        double iProductNum;
        for(int i=0; i<list.size(); i++)
        {
             detail = (InventoryQualityResultDetail)list.get(i); 
             strDId = detail.getM_strCheckResultId();        //状态转换单号
        	 strProductName = detail.getM_strProductName();  //产品名
        	 strCreatetime = detail.getM_strPrintDate();     //创建日期
        	 strNewProductStatus = detail.getM_strNewProductStatusName();	 //新物品状态
        	 strOldProductStatus = detail.getM_strOldProductStatusName();	 //原物品状态
        	 strClientName = detail.getM_strLotNumber();    //批号
        	 strCarspaceid = detail.getM_strCarspaceid();    //货位号
        	 iProductNum = detail.getM_iProductNum();	     //数量	
%>	
		 	
	<tr class="list_normal_tr">
	  <td class="list_normal_td"><%=i+1%></td>
	  <td class="list_normal_td"><%=strProductName==null?"":strProductName%></td>
      <td class="list_normal_td"><%=strCreatetime==null?"":strCreatetime%></td>
	  <td class="list_normal_td"><%=strNewProductStatus==null?"":strNewProductStatus%></td>
	  <td class="list_normal_td"><%=strOldProductStatus==null?"":strOldProductStatus%></td>
      
      <td class="list_normal_td"><%=strCarspaceid==null?"":strCarspaceid%></td>
      <td class="list_normal_td"><%=nbf.format(iProductNum)%></td>
   </tr>
<%
        }
    }
%>	
 </table>
  
<FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm1' target="_self"  style='display:none'></FORM>
</body>
</html>