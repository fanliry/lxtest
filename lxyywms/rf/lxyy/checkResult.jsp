<%@page import="com.ricosoft.common.tools.StrTools"%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckTask"%>
<%
	
	String instockid = (String)request.getAttribute("instockid");
    String strTime =  StrTools.getCurrDateTime(8);
	//�����ظ��ύ�ı�־������beginΪ�״μ��أ�finishΪ���ύ
	session.setAttribute("submitFlag","begin");
	String strWarehouseID = request.getParameter("warehouseid");
	if(strWarehouseID == null  || strWarehouseID.equals("null")){
		strWarehouseID = (String)session.getAttribute("warehouseid");
	}
%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
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
	/*������ť*/
	function LockButton(){	
		document.getElementById("traycode").readOnly = true;
	}
	/*�ͷŰ�ť*/
	function UnLockButton(){	
		document.getElementById("traycode").readOnly = false;
	}
	//**********************************************************
	//�޸��̵���
	function updateData()
	{
		var taskid = myIframe.document.getElementById("taskid").value; //����id
		var resultid = myIframe.document.getElementById("resultid").value; //�̵���
		var getnum = myIframe.document.getElementById("getnum").value;	//�̵�����
		if(getnum == "")
		{
			alert("������������Ϊ�գ�");
			return;
		}
		//************************************************	
		myIframe.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=RFCheckAction&method=ricoExecUpdateCheckResult&taskid=" + taskid + "&checknum=" + getnum +"&resultid="+resultid;			
	}
	function saveData()
	{
		var taskid = myIframe.document.getElementById("taskid").value; //����id
		var getnum = myIframe.document.getElementById("getnum").value;	//�̵�����
		if(getnum == null && getnum.length < 1)
		{
			alert("������������Ϊ�գ�");
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
		{alert("�������̵㵥�ţ�")}
	}
	 --%>
	//������������س� ��ȡ��Ϣ
	function  tray_onkeypress()   
	{   
  		GetInfo();     
    }

    //��ȡ��Ϣ�̵�������Ϣ
	function GetInfo(){
	   var checkid = document.getElementById("checkid").value;
	   var traycode = document.getElementById("traycode").value;
	   if(checkid == ""){
       alert("�̵㵥����Ϊ��!");
       return;
	   }
	   if(traycode == ""){
           alert("�������벻��Ϊ�գ�");
           return;
	   }
	   myIframe.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=RFCheckAction" + "&checkid=" +checkid +"&traycode=" + traycode;
    }
	//����ʱ���
	function getChecks(){
		var invoicedate = document.getElementById("invoicedate").value;
		var timeandwhid = invoicedate+"|"+"<%=strWarehouseID%>";
		selectObject(timeandwhid,"checkid","14");
	}
	//ҳ���½
	function onLoad()
	{	
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != "")
		{
			if(back_msg == "Y"){
				alert("�����ɹ���");
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
     <td width="60%" align="center" class="font_006699_bold_12">�̵���¼��</td>
     <td width="20%" align="right"><a href="<%=request.getContextPath()%>/rf/main.jsp?warehouseid=<%=strWarehouseID%>">����</a></td>
   </tr>
 </table>
     </td>
   </tr>
   <tr>
      <td class="TD_ADD" align="right"><div align="right">�Ƶ����ڣ�</div></td>
      <td class="TD_ADD"  ><div align="left">
       <input name="invoicedate" id="invoicedate" type="text" size="13" value="<%=strTime%>" onfocus="calendar();"  onpropertychange="getChecks();"class="Wdate" style="height:18px;width:138px;" />
    </div></td>
    </tr>
   <tr>  
     <td class="TD_ADD" align="right">�̵㵥�ţ�</td>
     <td class="TD_ADD"><select id="checkid" class="rf_select" style="width: 138px"><option value="">--��ѡ�񵥾�--</option></select> <font color="red">*</font><input type="hidden" name="taskId" value=""/>
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">�������룺</td>
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
       <input type="button" name="add" class="BUTTON_STYLE1" value="�������" onClick="saveData();">
       <input type="button" name="update" class="BUTTON_STYLE1" value="�޸Ľ��" onClick="updateData();">
     </td>
    </tr>  
 </table>
 <%--<FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm' target="_blank"  style='display:none'></FORM>
--%></body>
</html>
