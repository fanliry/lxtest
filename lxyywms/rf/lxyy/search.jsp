<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader" %>
<%
	
	String traycode = (String)request.getAttribute("traycode");
	
	
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

	
	//**********************************************************
	//��������
	function saveData()
	{
		
		var traycode = document.getElementById("traycode").value; //��������
		var productName = document.getElementById("productName").value; //Ʒ��
		var productCode = document.getElementById("productCode").value; //��Ʒ����
		var num = document.getElementById("num").value; //��������
		var traycode = document.getElementById("traycode").value; //��������
		
		
		if(traycode == null ||  traycode.length < 1)
		{
			alert("���������롿����Ϊ�գ�");
			return;
		}
		
		if(confirm("��ȷ��ֱ�ӳ�����"))
		{	var condition = "&traycode="+traycode+"&customerId="+customerId+"&productId="+productId+"&productDate="+productDate+"&lotinfo="+lotinfo+"&getnum="+getnum+"&jobId="+jobId+"&jobDetailId="+jobDetailId+"&inventoryId="+inventoryId;
			window.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=InBoundAction&rfmainView=rfmainView&flag=ZJCK" + condition;
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
			document.getElementById("customerName").value = bem[1];
			document.getElementById("productName").value = bem[2];
			document.getElementById("getnum").value = bem[3];
			document.getElementById("printdate").value = bem[4];
			document.getElementById("lotinfo").value = bem[5];
			document.getElementById("customerId").value = bem[6];
			document.getElementById("productid").value = bem[7];
			document.getElementById("jobId").value = bem[8];
			document.getElementById("jobDetailId").value = bem[9];
			document.getElementById("inventoryId").value = bem[10];
			document.all.over.style.display = "none";
			document.all.select.style.display = "none";
		} else {
			alert("��ѡ��һ����¼��");
		}
	}
	function disinfo(){
	  //var traycode = document.getElementById("traycode").value;
	  if(traycode!=null && traycode!=""){
	  		
	  	   //GetZCShowInfo(traycode);
	       GetInfo();
	  }
	}
	
	function GetZCShowInfo(traycode){
		
		getTool.GetJobInfoByTraycode(traycode,Show);
		
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
								+ "<td class='TD_ADD' align='center'>" + cem[3] + "</td>"
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
						
						//bem = aem[0].split(",");
						//document.getElementById("traycode").value = bem[0];
						
						GetInfo();
					}
					
					
				} else {
					alert("δ���ҵ���Ӧ�����뵥");
				}
			
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
	   getTool.GetJobInfoByTraycode(traycode,Show);
	   function Show(data){
		   
	      if(data!=null && data.length > 0){
	          var aem = data.split(",");
	          
	          if(aem!=null){
		         document.getElementById("productName").value=aem[0];
		         document.getElementById("lotinfo").value=aem[1];
		         document.getElementById("productCode").value=aem[2];
		         document.getElementById("num").value=aem[3];
		         document.getElementById("warehouseName").value=aem[4];
		         document.getElementById("scargoWhareaName").value=aem[5]; 
		      //   document.getElementById("productName").disabled = true;
	          }
	          
	      }else{
	             document.getElementById("productName").value="";
		         document.getElementById("lotinfo").value="";
		         document.getElementById("productCode").value="";
		         document.getElementById("num").value="";
		         document.getElementById("warehouseName").value="";
		         document.getElementById("scargoWhareaName").value="";
		         alert("û���ҵ���ز�Ʒ��Ϣ");
	      }
	      
	   }
	   
	}
	//ҳ���½
	function onLoad()
	{
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		var result = "<%=request.getAttribute("result") == null?"":(String)request.getAttribute("result")%>";
		
		if(result != "")
		{
			if(result == "Y"){
				alert("�����ɹ���");
			}else{
				alert(result);
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
     <td width="60%" align="center" class="font_006699_bold_12">RF��ѯ</td>
     <td width="20%" align="right"><a href="<%=request.getContextPath()%>/rf/main.jsp?warehouseid=<%=strWarehouseID%>">����</a></td>
   </tr>
 </table>
     </td>
   </tr>
     <td class="TD_ADD" align="right">�������룺</td>
     <td class="TD_ADD"><input type="text" name="traycode"  class="rf_input_long" maxlength="10"  onchange="disinfo()">  <font color="red">*</font>
     
     
     </td>
   </tr>
    <tr>  
     <td class="TD_ADD" align="right">Ʒ &nbsp;&nbsp;&nbsp;����</td>
     <td class="TD_ADD"><input type="text" name="productName"  class="rf_input_long" maxlength="10" disabled> <font color="red">*</font>
     
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">��Ʒ���룺</td>
     <td class="TD_ADD"><input type="text" name="productCode" class="rf_input_long" maxlength="10" disabled>  <font color="red">*</font>
    
     </td>
   </tr>
   <tr> 
     <td class="TD_ADD" align="right">�� &nbsp;&nbsp;&nbsp;����</td>
     <td class="TD_ADD"><input type="text" name="num" class="rf_input_long" maxlength="10" disabled> <font color="red">*</font></td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">�� &nbsp;&nbsp;&nbsp;�ţ�</td>
     <td class="TD_ADD"><input type="text" name="lotinfo" class="rf_input_long" maxlength="10" disabled>  <font color="red">*</font>
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">�� &nbsp;&nbsp;&nbsp;�⣺</td>
     <td class="TD_ADD"><input type="text" name="warehouseName"  class="rf_input_long" maxlength="10">  <font color="red">*</font>
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">�� &nbsp;&nbsp;&nbsp;����</td>
     <td class="TD_ADD"><input type="text" name="scargoWhareaName"  class="rf_input_long" maxlength="10">  <font color="red">*</font>
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
