<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.lang.reflect.Field" %>
<%@page import="com.wms3.bms.standard.entity.inventory.InventoryNeedcheck"%>

<html>
<head>
<title>需盘点单打印</title>
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
 
 <!-- ======== 标题开始 ======== -->
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
	 <td height="40" colspan="8" valign="bottom"><div align="center" class="style2">需&nbsp;盘&nbsp;点&nbsp;单</div></td>
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
 
 <!-- ======== 任务列表开始 ======== --> 
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="outer_table">
   <tr>
     <td class="list_title_td">行号</td>
     <td class="list_title_td">作业号</td>
     <td class="list_title_td">需盘点类型</td>
     <td class="list_title_td">货位</td>
     <td class="list_title_td">发生时间</td>
     <td class="list_title_td">是否处理</td>
     <td class="list_title_td">托盘条码</td>
     <td class="list_title_td">操作日志</td>
   </tr>	
   <%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size()>0){
	    InventoryNeedcheck iNeedcheck = null;
	    String needcheckid;	//ID
	    String jobid;			//作业号
	    String inouttype;		//入出类型
	    String cargoSpaceName;	//作业货位
	    String createtime;		//生成时间
	    String handleflag;		//处理标识
	    String handlecontent;	//处理内容
	    String traycode;		//托盘条码
	    String taskno;			//任务号
	    String cargoSpaceId;   //货位id
		for(int i = 0; i < ls.size(); i++){
		
			iNeedcheck = (InventoryNeedcheck)ls.get(i);
            needcheckid = iNeedcheck.getNeedcheckid();
            jobid = iNeedcheck.getJobid();
            inouttype = iNeedcheck.getInouttype();
            cargoSpaceName = iNeedcheck.getM_strCargoSpaceName();
            createtime = iNeedcheck.getCreatetime();
            handleflag = iNeedcheck.getHandleflag();
            handlecontent = iNeedcheck.getHandlecontent();
            traycode = iNeedcheck.getTraycode();
            taskno = iNeedcheck.getTaskno();
            cargoSpaceId = iNeedcheck.getCargoSpaceId();
%>
   <tr class="list_normal_tr">	
     <td class="list_normal_td"><%=i+1%></td>
     <td class="list_normal_td"><%=jobid==null?"":jobid%></td>
     <td class="list_normal_td"><%=inouttype==null?"":inouttype%></td>
     <td class="list_normal_td"><%=cargoSpaceName==null?"":cargoSpaceName%></td>
     <td class="list_normal_td"><%=createtime==null?"":createtime%></td>  
     <td class="list_normal_td"><%=handleflag==null?"":handleflag%></td> 
     <td class="list_normal_td"><%=traycode==null?"":traycode%></td>
     <td class="list_normal_td"><%=handlecontent==null?"":handlecontent%></td>
   </tr>
<%
        }
    }
%>	

 </table>

 
</body>
</html>