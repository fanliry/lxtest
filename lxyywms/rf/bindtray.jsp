<%@ page contentType="text/html; charset=GBK"%>
<%
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
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>


<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	/*������ť*/
	function LockButton(){
		
		document.getElementById("add").disabled = true;
	}
	/*�ͷŰ�ť*/
	function UnLockButton(){
		
		document.getElementById("add").disabled = false;
	}

	
	//**********************************************************
	//��������
	function saveData()
	{
		LockButton();
		//��ҵID
		var jobid = document.getElementById("jobid").value;
		//��������
		var tray_code = document.getElementById("tray_code").value;	

		
		if(jobid == null || jobid.length < 1){
			alert("����ҵ��š�����Ϊ�գ�");
			UnLockButton();
			return;
		}

		if(tray_code == null || tray_code.length < 1){
			alert("���������롿����Ϊ�գ�");
			UnLockButton();
			return;
		}
		if(tray_code != null && tray_code.length>1 && tray_code.length != 8)
		{
			alert("���������롿��Ϊ8λ��Y-000000 ��");
			UnLockButton();
			return;
		}
			
		//��֤�����Ƿ����
		if(tray_code != null && tray_code.length>1)
		{
			var msg;
			DWREngine.setAsync(false);
			judgmentTool.isTrayCodeUse(tray_code, Show);
			DWREngine.setAsync(true);
			function Show(value){
				msg = value;
			}
			if(msg != "Y"){
				alert(msg);
				UnLockButton();
				return;
			}
		}

		//************************************************
		if(confirm("��ȷ�����棿"))
		{		
			window.location.href = strUrl + "rfReceiptAction&method=bindTray&jobid=" + jobid + "&traycode=" + tray_code;
				
		}else{
			UnLockButton();
		}
	}

	//ҳ���½
	function onLoad()
	{
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>"
		if(back_msg != "")
		{
			if(back_msg == "Y"){
				alert("�����ɹ���");
			}else{
				alert(back_msg);
			}
		}
	}
-->
</script>
</head>

<body onload="onLoad();">
 <table id="tbb"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_MGR_TOP">
   <tr>
     <td height="25" bgcolor="#d9e8fc" align="center" colspan="2">
 <table width="100%">
   <tr>
     <td width="20%"></td>
     <td width="60%" align="center" class="font_006699_bold_12">RF������</td>
     <td width="20%" align="right"><a href="<%=request.getContextPath()%>/rf/main.jsp?warehouseid=<%=strWarehouseID%>">����</a></td>
   </tr>
 </table>
     </td>
   </tr>
    <tr>  
     <td class="TD_ADD" align="right">��ҵ��ţ�</td>
     <td class="TD_ADD"><input type="text" name="jobid"  class="rf_input_long" maxlength="10">  <font color="red">*</font>
     </td>
   </tr>
   <tr> 
     <td class="TD_ADD" align="right">�������룺</td>
     <td class="TD_ADD"><input type="text" name="tray_code"  class="rf_input_long" maxlength="10"> <font color="red">*</font></td>
   </tr>
   <tr>
     <td class="TD_ADD" align="center" colspan="2">
       <input type="button" name="add" class="BUTTON_STYLE1" value="��" onClick="saveData();">
     </td>
    </tr>
 </table>
 

</body>
</html>
