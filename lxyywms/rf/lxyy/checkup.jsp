<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader" %>
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
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/getTool.js"></script>


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
		var traycode = document.getElementById("traycode").value; //��������
		var ClientName = document.getElementById("ClientName").value; //�ջ���
		var productName = document.getElementById("productName").value;	//��Ʒ����
		var jobid = document.getElementById("jobid").value;	//��ҵid
		
		var outnum = document.getElementById("outnum").value;    //��������
		//var invoicenum = document.getElementById("invoicenum").value;	//��������
		var fpnum = document.getElementById("fpnum").value;	//��������

		if(traycode == null ||  traycode.length < 1)
		{
			alert("���������롿����Ϊ�գ�");
			return;
		}
		if(productName == null || productName.length < 1){
			alert("��Ʒ��������Ϊ�գ�");
			return;
		}
		
		//�жϸ���ҵ�Ƿ����
		if(jobid != null && jobid.length>1)
		{
			var msg;
			DWREngine.setAsync(false);
			judgmentTool.CheckJobs(jobid, Show);
			DWREngine.setAsync(true);
			function Show(value){
				msg = value;
			}
			if(msg != "Y"){
				alert(msg);
				return;
			}
		}
		if(fpnum == null || fpnum.length <1 || !IsFloat(fpnum)){
			alert("����������������Ϊ�ջ���ֻ��Ϊ���֣�");
			return;
		}
		//************************************************	
		if(outnum == fpnum){
		      window.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&rfmainView=rfmainView&flag=fh&jobid=" + jobid;	
		}else{
		      if(confirm("�������������������һ�� ����Ĳ�Ʒ�Զ�ת���ݴ���")){
				 window.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&rfmainView=rfmainView&flag=fh&jobid=" + jobid;	
		      }
	     }
	}
	function OnCancel() {
		document.all.over.style.display = "none";
		document.all.select.style.display = "none";
	}
	//ѡ��һ����ⵥ
	function OnSelect() {
		var msg = null;
		var check_ids = document.getElementsByName("check_id");
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
	   getTool.GetCheckUpByTray(traycode,"<%=strWarehouseID%>",Show);
	   function Show(data){
	      if(data!=null && data.length > 0){
	          var aem = data.split(",");
	          if(aem!=null){
		         document.getElementById("ClientName").value=aem[0];
		         document.getElementById("productName").value=aem[1];
		         document.getElementById("printdate").value=aem[2];
		         document.getElementById("lotinfo").value=aem[3];
		         document.getElementById("outnum").value=aem[4];
		         document.getElementById("fpnum").value=aem[5];
		         document.getElementById("jobid").value=aem[6];
	          }
	      }else{
	             document.getElementById("ClientName").value="";
		         document.getElementById("productName").value="";
		         document.getElementById("printdate").value="";
		         document.getElementById("lotinfo").value="";
		         document.getElementById("outnum").value="";
		         document.getElementById("fpnum").value="";
		         document.getElementById("jobid").value="";
		         alert("û���ҵ���ز�Ʒ��Ϣ");
	      }
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
     <td width="60%" align="center" class="font_006699_bold_12">RF���⸴��</td>
     <td width="20%" align="right"><a href="<%=request.getContextPath()%>/rf/main.jsp?warehouseid=<%=strWarehouseID%>">����</a></td>
   </tr>
 </table>
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">�������룺</td>
     <td class="TD_ADD"><input type="text" name="traycode"  class="rf_input_long" maxlength="10"  onchange="disinfo()">  <font color="red">*</font>
        <input type="hidden" name="jobid"/> 
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">�ͻ�����</td>
     <td class="TD_ADD"><input type="text" name="ClientName" class="rf_input_long" maxlength="10" disabled>  <font color="red">*</font>
      <input type="hidden" name="ClientID"/>
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">��Ʒ���ƣ�</td>
     <td class="TD_ADD"><input type="text" name="productName" class="rf_input_long" maxlength="10" disabled>  <font color="red">*</font>
     <input type="hidden" name="productid"/>
     </td>
   </tr>
   <tr> 
     <td class="TD_ADD" align="right">�������ڣ�</td>
     <td class="TD_ADD"><input type="text" name="printdate"class="rf_input_long" maxlength="10" disabled> <font color="red">*</font></td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">������ţ�</td>
     <td class="TD_ADD"><input type="text" name="lotinfo" class="rf_input_long" maxlength="10" disabled>  <font color="red">*</font>
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">����������</td>
     <td class="TD_ADD"><input type="text" name="outnum"  class="rf_input_long" maxlength="10" disabled> <font color="red">*</font>
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">����������</td>
     <td class="TD_ADD"><input type="text" name="fpnum"  class="rf_input_long" maxlength="10" disabled><font color="red">*</font>
     </td>
   </tr>
   <tr>
     <td class="TD_ADD" align="center" colspan="2">
       <input type="button" name="add" class="BUTTON_STYLE1" value="����" onClick="saveData();">
     </td>
    </tr>
 </table>
<div id="over" style="position:absolute; display:none; top:0px; left:0px; width:100%; height:100%; 
  background-color:#efefef; z-index:1; filter:alpha(opacity=70);">
<iframe style="position:absolute; top:0; left:0; width:100%; height:100%; z-index:-1;"></iframe>
</div>
<div id="select" style="position:absolute; display:none; top:0px; left:0px; width:100%; z-index:2; background: #ffffff;"></div>
</body>
</html>
