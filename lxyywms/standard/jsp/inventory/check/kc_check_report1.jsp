<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckTask"%>
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
 <!-- ======== 标题开始 ======== -->
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
	 <td height="40" colspan="<%=15%>" valign="bottom"><div align="center" class="style2">盘&nbsp;点&nbsp;任&nbsp;务&nbsp;单</div></td>
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
     <td class="list_title_td">任务号条码</td>
     <td class="list_title_td">任务号</td>
     <td class="list_title_td">库区</td>
     <td class="list_title_td">货位</td>
     <td class="list_title_td">状态</td>
     <td class="list_title_td">品名</td>
     <td class="list_title_td">托盘条码</td>
     <td class="list_title_td">产品条码</td>
     <td class="list_title_td">库存数量</td>
     <td class="list_title_td">批号</td>
     <td class="list_title_td">生产日期</td>
   </tr>
   
<%

	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0){
	
		//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
		
		InventoryCheckTask checktask = null;
		String taskid;			//ID
		String status;			//状态
		String cargospace;		//货位
		String wharea;			//库区
		String product;			//产品
		String owner;			//货主
		String tyaycode;		//托盘条码
    	String productcode;     //产品条码
		String prindtate;		//生产日期
		String punit;		    //单位
		String lotinfo;		    //批号
		double num;				//库存数量
		
		for(int i = 0; i < ls.size(); i++){
			checktask = (InventoryCheckTask)ls.get(i);
			
			taskid = checktask.getTaskid();				//ID
			status = checktask.getStatusName();			//状态
			cargospace = checktask.getCargoSpaceName();	//货位
			wharea = checktask.getWhAreaName();			//库区
			product = checktask.getProductName();		//产品
			tyaycode = checktask.getTraycode();			//托盘条码
    		productcode = checktask.getProductcode(); 	//产品条码
			prindtate = checktask.getCreatetime();		//创建时间
			num = checktask.getNum();					//库存数量
			punit = checktask.getPunit(); //单位
			lotinfo = checktask.getLotinfo(); //批号
	  	 	
%>
   <tr class="list_normal_tr">
     <td class="list_normal_td"><%=i+1%></td>
     <td class="list_normal_td"><div align="center"> 
	  		<img src="<%=request.getContextPath()%>/barcode?msg=<%=taskid%>&type=code128&fmt=jpeg" height="20px" width=120px /></div></td>
     <td class="list_normal_td"><%=taskid==null?"":taskid%></td>
     <td class="list_normal_td"><%=wharea==null?"":wharea%></td>
     <td class="list_normal_td"><%=cargospace==null?"":cargospace%></td>
     <td class="list_normal_td"><%=status==null?"":status%></td>
     <td class="list_normal_td"><%=product==null?"":product%></td>
     <td class="list_normal_td"><%=tyaycode==null?"":tyaycode%></td>
     <td class="list_normal_td"><%=productcode==null?"":productcode%></td>
     <td class="list_normal_td"></td>
     <td class="list_normal_td"><%=punit==null?"":punit%></td>
     <td class="list_normal_td"><%=lotinfo==null?"":lotinfo%></td>
   </tr>
   
<%
		}
	}
%>
 </table>
 
</body>
</html>
