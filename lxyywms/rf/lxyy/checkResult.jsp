<%@page import="com.ricosoft.common.tools.StrTools"%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckTask"%>
<%
	
	String instockid = (String)request.getAttribute("instockid");
    String strTime =  StrTools.getCurrDateTime(8);
	//设置重复提交的标志变量，begin为首次加载，finish为已提交
	session.setAttribute("submitFlag","begin");
	String strWarehouseID = request.getParameter("warehouseid");
	if(strWarehouseID == null  || strWarehouseID.equals("null")){
		strWarehouseID = (String)session.getAttribute("warehouseid");
	}
%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<script type="text/javascript">

	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	/*锁定按钮*/
	function LockButton(){	
		document.getElementById("traycode").readOnly = true;
	}
	/*释放按钮*/
	function UnLockButton(){	
		document.getElementById("traycode").readOnly = false;
	}
	//**********************************************************
	//修改盘点结果
	function updateData()
	{
		var taskid = myIframe.document.getElementById("taskid").value; //任务id
		var resultid = myIframe.document.getElementById("resultid").value; //盘点结果
		var getnum = myIframe.document.getElementById("getnum").value;	//盘点数量
		if(getnum == "")
		{
			alert("【数量】不能为空！");
			return;
		}
		//************************************************	
		myIframe.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=RFCheckAction&method=ricoExecUpdateCheckResult&taskid=" + taskid + "&checknum=" + getnum +"&resultid="+resultid;			
	}
	function saveData()
	{
		var taskid = myIframe.document.getElementById("taskid").value; //任务id
		var getnum = myIframe.document.getElementById("getnum").value;	//盘点数量
		if(getnum == null && getnum.length < 1)
		{
			alert("【数量】不能为空！");
			return;
		}
			
		//************************************************	
		myIframe.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=RFCheckAction&method=ricoExecAddCheckResult&taskid=" + taskid + "&checknum=" + getnum;			
	}
<%--
	function checkid_onkeypress() {   
  		if(event.keyCode==13)
  		{   
  			disinfo();   
  		}   
    }
	function disinfo(){
	  
	  var checkid = document.getElementById("checkid").value;
	  if(checkid != null && checkid.length>1)
		{
		 var strMeg="";
		 DWREngine.setAsync(false);
		 judgmentTool.isCheckRequest(checkid, Show);
		 DWREngine.setAsync(true);
		 function Show(value){
			 strMeg = value;
 
		 }
		 UnLockButton();
		 if(strMeg != "Y" ){
			 alert(strMeg); 
			 document.getElementById("checkid").value="";	 
			 document.getElementById("checkid").focus();
			 LockButton();
		 }	 
		}else
		{alert("请输入盘点单号！")}
	}
	 --%>
	//输入托盘条码回车 获取信息
	function  tray_onkeypress()   
	{   
  		GetInfo();     
    }

    //获取信息盘点任务信息
	function GetInfo(){
	   var checkid = document.getElementById("checkid").value;
	   var traycode = document.getElementById("traycode").value;
	   if(checkid == ""){
       alert("盘点单不能为空!");
       return;
	   }
	   if(traycode == ""){
           alert("托盘条码不能为空！");
           return;
	   }
	   myIframe.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=RFCheckAction" + "&checkid=" +checkid +"&traycode=" + traycode;
    }
	//更改时间后
	function getChecks(){
		var invoicedate = document.getElementById("invoicedate").value;
		var timeandwhid = invoicedate+"|"+"<%=strWarehouseID%>";
		selectObject(timeandwhid,"checkid","14");
	}
	//页面登陆
	function onLoad()
	{	
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != "")
		{
			if(back_msg == "Y"){
				alert("操作成功！");
			}else{
				alert(back_msg);
			}
		}
		var invoicedate = document.getElementById("invoicedate").value;
		var timeandwhid = invoicedate+"|"+"<%=strWarehouseID%>";
		//alert(timeandwhid);
		selectObject(timeandwhid, "checkid", "14"); 
	}

</script>
</head>

<body onload="onLoad();">
 <table id="tbb"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_MGR_TOP">
   <tr>
     <td height="25" bgcolor="#d9e8fc" align="center" colspan="2">
 <table width="100%">
   <tr>
     <td width="20%"></td>
     <td width="60%" align="center" class="font_006699_bold_12">盘点结果录入</td>
     <td width="20%" align="right"><a href="<%=request.getContextPath()%>/rf/main.jsp?warehouseid=<%=strWarehouseID%>">返回</a></td>
   </tr>
 </table>
     </td>
   </tr>
   <tr>
      <td class="TD_ADD" align="right"><div align="right">制单日期：</div></td>
      <td class="TD_ADD"  ><div align="left">
       <input name="invoicedate" id="invoicedate" type="text" size="13" value="<%=strTime%>" onfocus="calendar();"  onpropertychange="getChecks();"class="Wdate" style="height:18px;width:138px;" />
    </div></td>
    </tr>
   <tr>  
     <td class="TD_ADD" align="right">盘点单号：</td>
     <td class="TD_ADD"><select id="checkid" class="rf_select" style="width: 138px"><option value="">--请选择单据--</option></select> <font color="red">*</font><input type="hidden" name="taskId" value=""/>
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">托盘条码：</td>
     <td class="TD_ADD"><input type="text" id="traycode"  class="rf_input_short" maxlength="10"   onchange="tray_onkeypress()">  <font color="red">*</font>
     </td>
   </tr>
   <tr>
	 <td class="TD_ADD" colspan="2" valign="top"  height="130">
	   <iframe id="myIframe" src="<%=request.getContextPath()%>/rf/fyhz/checkResult_task.jsp" frameborder="0" width="100%" height="100%" scrolling="no">
	   </iframe>
	 </td>
   </tr>
   <tr>
     <td align="center" colspan="2">
       <input type="button" name="add" class="BUTTON_STYLE1" value="新增结果" onClick="saveData();">
       <input type="button" name="update" class="BUTTON_STYLE1" value="修改结果" onClick="updateData();">
     </td>
    </tr>  
 </table>
 <%--<FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm' target="_blank"  style='display:none'></FORM>
--%></body>
</html>
