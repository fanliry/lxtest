<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader"%>
<%
	String instockid = (String) request.getAttribute("instockid");

	//�����ظ��ύ�ı�־������beginΪ�״μ��أ�finishΪ���ύ
	session.setAttribute("submitFlag","begin");
	String strWarehouseID = (String)session.getAttribute("warehouseid");
	
	if (strWarehouseID == null || strWarehouseID.equals("null")) {
		strWarehouseID = (String) session.getAttribute("warehouseid");
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
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/getTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>


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
    //��������Ƿ�Ϊ������
	function IsFloat(ch){
		var re = /^\d+(\.\d+)?$/;
		return re.test(ch);
	}
	
	//**********************************************************
	//��������
	function saveData()
	{
        var warehouseid = "<%=strWarehouseID%>"
	   if(warehouseid=null || warehouseid==""){
	       alert("�����µ�½");
	       return;
	   }
		var invoiceid = document.getElementById("invoiceid").value; //��ⵥ��
		var productName = document.getElementById("productName").value;	//��Ʒ����
		var productid = document.getElementById("productid").value;	//��Ʒid
		var traycode = document.getElementById("traycode").value; //��������
		var getnum = document.getElementById("getnum").value;	//ʵ������
		
		var platoon = document.getElementById("platoon1").value;//��
		var column = document.getElementById("column1").value;//��
		var floor = document.getElementById("floor1").value;//��
		
		
		if(invoiceid == null || invoiceid.length < 1){
			alert("�����뵥�š�����Ϊ�գ�");
			return;
		}
		if(productName == null || productName.length < 1){
			alert("����Ʒ���ơ�����Ϊ�գ�");
			return;
		}
		if(traycode == null ||  traycode.length < 1)
		{
			alert("���������롿����Ϊ�գ�");
			return;
		}
		if(getnum == null || getnum.length <1 || !IsFloat(getnum)){
			alert("������������Ϊ�գ�");
			return;
		}else if(getnum<=0)
		{
			alert("������Ҫ����0��");
			return false;
		}
		
		//��֤�����Ƿ��Ѿ����ջ� ����Ѿ����ջ����ڽ����ջ� ����ȡ���ջ��Ժ󷽿ɽ����ٴ��ջ�
		if(traycode != null && traycode.length>1)
		{
			var msg;
			DWREngine.setAsync(false);
			judgmentTool.isRecebyTrayCode(invoiceid,traycode, Show);
			DWREngine.setAsync(true);
			function Show(value){
				msg = value;
			}
			if(msg != "Y"){
				alert(msg);
				return;
			}
		}
		if(platoon!=null&&platoon.length>0&&column!=null&&column.length>0&&floor!=null&&floor.length>0){
			
			var msg1;
			DWREngine.setAsync(false);
			judgmentTool.isCarspace("<%=strWarehouseID%>",platoon,column, floor,Show1);
			DWREngine.setAsync(true);
			function Show1(value){
				msg1 = value;
			}
			if(msg1 != "Y"){
				alert(msg1);
				return;
			}else{
			    if(confirm("��ȷ���ջ���"))
				{		
					window.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=InBoundAction&flag=RF1&instockid=" + invoiceid+"&traycode="+traycode+"&num="+getnum+"&platoon="+platoon+"&column="+column+"&floor="+floor;
						
				}
			}
		}else{
			
			alert("����ָ����λ��");
			return false;	
<%-- 					    if(confirm("��ȷ���ջ���"))
						{		
							window.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=InBoundAction&flag=RF1&instockid=" + invoiceid+"&traycode="+traycode+"&num="+getnum+"&platoon="+platoon+"&column="+column+"&floor="+floor;
								
						} --%>
		}
		
		
		//************************************************
		
	}
	function OnCancel() {
		document.all.over.style.display = "none";
		document.all.select.style.display = "none";
	}
	//ѡ��һ����ⵥ
	function OnSelect() {
		var msg = null;
		var check_ids = document.getElementsByName("check_id");
		var invoiceid = parent.document.getElementById("invoiceid");
		invoiceid.focus();
		for (var i=0; i<check_ids.length; i++) {
			if(check_ids[i].checked) {
				msg = check_ids[i].value;
				break;
			}
		}
		if (msg != null) {
			var bem = msg.split(",");
			document.getElementById("invoiceid").value = bem[0];
			
			document.all.over.style.display = "none";
			document.all.select.style.display = "none";
		} else {
			alert("��ѡ��һ����¼��");
		}
	}
	function disinfo(){
	  var warehouseid = "<%=strWarehouseID%>"
	  if(warehouseid=null || warehouseid==""){
		    alert("�����µ�½");
		    return;
	  }
	
	  
	  var traycode = document.getElementById("traycode").value;
	  if(traycode!=null && traycode!=""){
	       GetInfo();
	  }
	}
   	//��ȡû��ȷ����û�����ϵ���ⵥ
	function Getinvoiceid(){
	       var warehouseid = "<%=strWarehouseID%>"
		   if(warehouseid=null || warehouseid==""){
		       alert("�����µ�½");
		       return;
		   }
			getTool.Getinvoiceid("<%=strWarehouseID%>",Show);
			function Show(data) {
				if(data.length > 0) {
					var aem = data.split("|");
					var bem = null;
					if (aem.length > 1) {
						var cem = null;
						var info = "<table id='tb' width='100%' align='center' cellpadding='0' cellspacing='0' class='TABLE_MGR_TOP'>"	
						for (var i=0; i<aem.length; i++) {
							cem = aem[i].split(",");
							info += "<tr>"
								+ "<td class='TD_ADD' align='center' width='25'>"
								+ "<input type='radio' name='check_id' class='input_radio' value='" + aem[i] + "'></td>"
								+ "<td class='TD_ADD' align='center'>" + cem[0] + "</td>"
								+ "<td class='TD_ADD' align='center'>" + cem[1] + "</td>"
								+ "<td class='TD_ADD' align='center'>" + cem[2] + "</td>"
								+ "</tr>"
						}
						info += "<tr><td class='TD_ADD' align='center' colspan='4'>"
							  + "<input type='button' name='save' onClick='OnSelect()' class='BUTTON_STYLE1' value='ȷ��'> "
							  + "<input type='button' name='save' onClick='OnCancel()' class='BUTTON_STYLE1' value='ȡ��'>"
							  + "</td></tr></table>";
						
						document.all.over.style.display = "block";
						document.all.select.style.display = "block";
						document.all.select.innerHTML = info;
						document.all.select.style.top = (document.body.scrollHeight-document.all.tb.clientHeight)/2;
						return;
					} else {
						bem = aem[0].split(",");
					}
					document.getElementById("invoiceid").value = bem[0];
					
				} else {
					alert("δ���ҵ���Ӧ�����뵥");
				}
			}
	}
	function GetInfo(){
	   var traycode = document.getElementById("traycode").value;
	   var invoiceid = document.getElementById("invoiceid").value;
	   getTool.GetdetailByid(invoiceid,traycode,Show);
	   function Show(data){
	      if(data!=null && data.length > 0){
	          var aem = data.split(",");
	          if(aem!=null){
		         document.getElementById("productName").value=aem[0];
		         document.getElementById("printdate").value=aem[1];
		         document.getElementById("lotinfo").value=aem[2];
		         document.getElementById("productid").value=aem[3];
		         document.getElementById("innum").value=aem[4];
		         document.getElementById("getnum").value=aem[4];
		         document.getElementById("unit").value = aem[6];
		         
		         document.getElementById("productName").disabled = true;
		         document.getElementById("printdate").disabled = true;
		         document.getElementById("lotinfo").disabled = true;
		         document.getElementById("innum").disabled = true;
	          }
	          
	      }else{
	             document.getElementById("productName").value="";
		         document.getElementById("printdate").value="";
		         document.getElementById("lotinfo").value="";
		         document.getElementById("productid").value="";
		         document.getElementById("innum").value="";
		         document.getElementById("getnum").value="";
		         document.getElementById("unit").value="";
		         
		         document.getElementById("productName").disabled = true;
		         document.getElementById("printdate").disabled = true;
		         document.getElementById("lotinfo").disabled = true;
		         document.getElementById("innum").disabled = true;
		         alert("û���ҵ���ز�Ʒ��Ϣ");
	      }
	      
	   }
	   
	}
	//������ⵥ�Ż�ȡʣ�������
	function getSurplusnumByinvoiceid(){
			var invoiceid = document.getElementById("invoiceid").value;
			if(invoiceid==null||invoiceid.length < 1){
				document.getElementById("surplusnum").value="";
			}else{
			   
				getTool.getSurplusnumByinvoiceid(invoiceid,Show);
				function Show(data){
					document.getElementById("surplusnum").value=data;
				}
			}
			
		
	}
	//���ݲֿ��ÿ������������б�
	function getPlatoon(){
		
		selectPlatoon( "platoon1");
		
	}
	
	function getColumn(){
		selectColumn( "column1");
		
	}
	
	function getFloor(){
		selectFloor( "floor1");
		
	}
	//�Ƿ�ѡȡָ����λ
	function enableSel(obj){
		var platoon = document.getElementById("platoon1");//��
		var column = document.getElementById("column1");//��
		var floor = document.getElementById("floor1");//��
		
		if(obj.checked){
			platoon.disabled =obj.checked?false:true;
			column.disabled =obj.checked?false:true;
			floor.disabled =obj.checked?false:true;
		}else{
			platoon.value="";
			column.value="";
			floor.value="";
			platoon.disabled =obj.checked?false:true;
			column.disabled =obj.checked?false:true;
			floor.disabled =obj.checked?false:true;
			
			
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
		
		
		selectPlatoon( "platoon1");
		selectColumn( "column1");
		selectFloor( "floor1");
		
		var invoiceid = document.getElementById("invoiceid");
		invoiceid.focus();
	}
-->
</script>
</head>

<body onload="onLoad();">
	<table id="tbb" border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_MGR_TOP">
		<tr>
			<td height="25" bgcolor="#d9e8fc" align="center" colspan="2">
				<table width="100%">
					<tr>
						<td width="20%"></td>
						<td width="60%" align="center" class="font_006699_bold_12">RF�ջ�</td>
						<td width="20%" align="right"><a href="<%=request.getContextPath()%>/rf/main.jsp?warehouseid=<%=strWarehouseID%>">����</a></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">��ⵥ�ţ�</td>
			<td class="TD_ADD"><input type="text" name="invoiceid" onBlur="getSurplusnumByinvoiceid()" value="<%=instockid != null ? instockid : ""%>" class="rf_input_long"> <input name="moreBtn" type="button" class="button_select" value="��" onclick="Getinvoiceid()" /></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">��ⵥʣ��������</td>
			<td class="TD_ADD"><input type="text" name="surplusnum" class="rf_input_long" maxlength="10" disabled> <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">�������룺</td>
			<td class="TD_ADD"><input type="text" name="traycode" class="rf_input_long" maxlength="10" onchange="disinfo()"> <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">��Ʒ���ƣ�</td>
			<td class="TD_ADD"><input type="text" name="productName" class="rf_input_long" maxlength="10" disabled> <font color="red">*</font> <input type="hidden" name="productid" /></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">�������ڣ�</td>
			<td class="TD_ADD"><input type="text" name="printdate" class="rf_input_long" maxlength="10" disabled> <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">���ţ�</td>
			<td class="TD_ADD"><input type="text" name="lotinfo" class="rf_input_long" maxlength="10" disabled> <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">��λ��</td>
			<td class="TD_ADD"><input type="text" name="unit" class="rf_input_long" maxlength="10" disabled> <font color="red">*</font></td>
		</tr>

		<tr style="display:none">
			<td class="TD_ADD" align="right">Ӧ��������</td>
			<td class="TD_ADD"><input type="text" name="innum" class="rf_input_long" maxlength="10" disabled> <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">������</td>
			<td class="TD_ADD"><input type="text" name="getnum" class="rf_input_long" maxlength="10" disabled> <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">�Ƿ�ָ����λ��</td>
			<td class="TD_ADD"><input type="checkbox" name="isCarspace" checked onclick="enableSel(this);"></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">��λ��</td>
			<td class="TD_ADD"><select id="platoon1" style="width: 60px;" >
					<option value=""></option>
			</select> �� <select id="column1" style="width: 60px;" >
					<option value=""></option>
			</select> �� <select id="floor1" style="width: 60px;" >
					<option value=""></option>
			</select> ��</td>
		</tr>
		<tr>
			<td class="TD_ADD" align="center" colspan="2"><input type="button" name="add" class="BUTTON_STYLE1" value="�ջ�" onClick="saveData();"></td>
		</tr>
	</table>
	<div id="over" style="position: absolute; display: none; top: 0px; left: 0px; width: 100%; height: 100%; background-color: #efefef; z-index: 1; filter: alpha(opacity =     70);">
		<iframe style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; z-index: -1;"></iframe>
	</div>
	<div id="select" style="position: absolute; display: none; top: 0px; left: 0px; width: 100%; z-index: 2; background: #ffffff;"></div>
</body>
</html>
