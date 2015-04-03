<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundRequestInvoiceDetail" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundRequestInvoiceHeader" %>
<%
	
	InboundRequestInvoiceHeader invoice = (InboundRequestInvoiceHeader)request.getAttribute("invoice");
	InboundRequestInvoiceDetail invoicedetail = (InboundRequestInvoiceDetail)request.getAttribute("invoicedetail");
	String invoiceid="",requestvoicedetailid="",productName="",productid="",printdate="",lotinfo="",productStatus="";
	String plannum="",bandnum="";
	if(invoiceid!=null && invoicedetail!=null){
	    invoiceid = invoice.getInstockid(); //���뵥��
		requestvoicedetailid = invoicedetail.getInstockdetailid(); //���뵥��ϸ
		plannum = invoicedetail.getPlannum()+""; //��ϸ�ƻ�����
		bandnum = invoicedetail.getBindingnum()+""; //��ϸ�󶨼ƻ�
		productName = invoicedetail.getProductName();	//��Ʒ����
		productid = invoicedetail.getProductid();	//��Ʒid
		printdate = invoicedetail.getPrintdate(); //��������
		lotinfo = invoicedetail.getLotinfo(); //����
		productStatus=invoicedetail.getProductStatus();
	}
	
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
		//��֤�����Ƿ�Ϊ���̵�����
		
		if(isNum()){
			var invoiceid = document.getElementById("invoiceid").value; //���뵥��
			var requestvoicedetailid = document.getElementById("requestvoicedetailid").value; //���뵥��ϸ
			var plannum = parseFloat(document.getElementById("plannum").value); //��ϸ�ƻ�����
			var bandnum = parseFloat(document.getElementById("bandnum").value); //��ϸ������
			var productName = document.getElementById("productName").value;	//��Ʒ����
			var productid = document.getElementById("productid").value;	//��Ʒid
			var productStatus =  document.getElementById("productStatus").value;	//��Ʒ״̬
			
			
			var printdate = document.getElementById("printdate").value; //��������
			var lotinfo = document.getElementById("lotinfo").value; //����
			var traycode = document.getElementById("traycode").value; //��������
			var num = parseFloat(document.getElementById("num").value);	//����
	
			
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
			if(num == null && num.length < 1)
			{
				alert("������������Ϊ�ջ�Ϊ���֣�");
				return;
			}
			if(num > plannum - bandnum)
			{
				alert("�����ƻ�����������");
				return;
			}
			//��֤����״̬ ���Ƿ�Ϊ��˻��״̬
			var msg;
			DWREngine.setAsync(false);
			judgmentTool.isAuditAbind(invoiceid, Show);
			DWREngine.setAsync(true);
			function Show(value){
				msg = value;
			}
			if(msg != "Y"){
				alert(msg);
				return;
			}
			//��֤�����Ƿ����
			if(traycode != null && traycode.length>1)
			{
				var msg;
				DWREngine.setAsync(false);
				judgmentTool.isTrayCodeUse(traycode, Show);
				DWREngine.setAsync(true);
				function Show(value){
					msg = value;
				}
				if(msg != "Y"){
					alert(msg);
					return;
				}
			}
	
			//************************************************
			if(confirm("��ȷ�����棿"))
			{		
				window.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=InBoundRequestAction&flag=RF1&instockid=" + invoiceid+"&requestvoicedetailid="+requestvoicedetailid+"&traycode="+traycode+"&num="+num;
					
			}
		}
		
	}
	function OnCancel() {
		document.all.over.style.display = "none";
		document.all.select.style.display = "none";
	}
	//ѡ��һ�����뵥
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
			GetInfo();
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
	  var invoiceid = document.getElementById("invoiceid").value;
	  GetInfo();
	
	}
   	//��ȡû�йر���û�����ϵ���˵�
	function Getrequestvoiceid(){
   		
		   var warehouseid = "<%=strWarehouseID%>"
		   if(warehouseid==null || warehouseid==""){
		       alert("�����µ�½");
		       return;
		   }
		  
			getTool.Getrequestvoiceid("<%=strWarehouseID%>",Show);
			
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
						
						if(bem[0]==null){
							alert("�����ݣ�");
							return;
						}
						document.getElementById("invoiceid").value = bem[0];
						
						GetInfo();
					}
					
					
				} else {
					alert("δ���ҵ���Ӧ�����뵥");
				}
			}
	}
	function GetInfo(){
	   var invoiceid = document.getElementById("invoiceid").value;
	   getTool.GetreqdetailByid(invoiceid,Show);
	   function Show(data){
	      if(data!=null && data.length > 0){
	          var aem = data.split(",");
	          if(aem!=null){
	             document.getElementById("requestvoicedetailid").value=aem[0];
		         document.getElementById("plannum").value=aem[1];
		         document.getElementById("bandnum").value=aem[2];
		         document.getElementById("productName").value=aem[3];
		         document.getElementById("printdate").value=aem[4];
		         document.getElementById("lotinfo").value=aem[5];
		         document.getElementById("productid").value=aem[6];
		         document.getElementById("productStatus").value=aem[7];
		         document.getElementById("num").value=aem[1]-aem[2];
		         
		         document.getElementById("requestvoicedetailid").disabled = true;
		         document.getElementById("plannum").disabled = true;
		         document.getElementById("bandnum").disabled = true;
		         document.getElementById("productName").disabled = true;
		         document.getElementById("printdate").disabled = true;
		         document.getElementById("lotinfo").disabled = true;
	          }
	          
	      }else{
	             document.getElementById("requestvoicedetailid").value="";
		         document.getElementById("plannum").value="";
		         document.getElementById("bandnum").value="";
		         document.getElementById("productName").value="";
		         document.getElementById("printdate").value="";
		         document.getElementById("lotinfo").value="";
		         document.getElementById("productid").value="";
		          document.getElementById("productStatus").value="";
		          
		         document.getElementById("requestvoicedetailid").disabled = true;
		         document.getElementById("plannum").disabled = true;
		         document.getElementById("bandnum").disabled = true;
		         document.getElementById("productName").disabled = true;
		         document.getElementById("printdate").disabled = true;
		         document.getElementById("lotinfo").disabled = true;
		         alert("û���ҵ���ز�Ʒ��Ϣ");
	      }
	      
	   }
	   
	}
	function down(){
	   var invoiceid = document.getElementById("invoiceid").value;
	   var requestvoicedetailid = document.getElementById("requestvoicedetailid").value;
	   getTool.GetreqdetailByDownid(invoiceid,requestvoicedetailid,Show);
	   function Show(data){
	      if(data!=null && data!="" && data.length > 0){
	          var aem = data.split(",");
	          if(aem!=null){
	             document.getElementById("requestvoicedetailid").value=aem[0];
		         document.getElementById("plannum").value=aem[1];
		         document.getElementById("bandnum").value=aem[2];
		         document.getElementById("productName").value=aem[3];
		         document.getElementById("printdate").value=aem[4];
		         document.getElementById("lotinfo").value=aem[5];
		         document.getElementById("productid").value=aem[6];
		          document.getElementById("productStatus").value=aem[7];
		          document.getElementById("num").value=aem[1]-aem[2];
		          
		         document.getElementById("requestvoicedetailid").disabled = true;
		         document.getElementById("plannum").disabled = true;
		         document.getElementById("bandnum").disabled = true;
		         document.getElementById("productName").disabled = true;
		         document.getElementById("printdate").disabled = true;
		         document.getElementById("lotinfo").disabled = true;
	          }
	      }else{
	          alert("û��������ϸ");
	      }   
	   }
	}
	function up(){
	   var invoiceid = document.getElementById("invoiceid").value;
	   var requestvoicedetailid = document.getElementById("requestvoicedetailid").value;
	   getTool.GetreqdetailByUpid(invoiceid,requestvoicedetailid,Show);
	   function Show(data){
	      if(data!=null && data!="" && data.length > 0){
	          var aem = data.split(",");
	          if(aem!=null){
	             document.getElementById("requestvoicedetailid").value=aem[0];
		         document.getElementById("plannum").value=aem[1];
		         document.getElementById("bandnum").value=aem[2];
		         document.getElementById("productName").value=aem[3];
		         document.getElementById("printdate").value=aem[4];
		         document.getElementById("lotinfo").value=aem[5];
		         document.getElementById("productid").value=aem[6];
		          document.getElementById("productStatus").value=aem[7];
		          document.getElementById("num").value=aem[1]-aem[2];
		         
		         document.getElementById("requestvoicedetailid").disabled = true;
		         document.getElementById("plannum").disabled = true;
		         document.getElementById("bandnum").disabled = true;
		         document.getElementById("productName").disabled = true;
		         document.getElementById("printdate").disabled = true;
		         document.getElementById("lotinfo").disabled = true;
	          }
	          
	      }else{
	          alert("û��������ϸ");
	      }   
	   }
	}
	//��������Ƿ�Ϊ����
	function IsNum(num){ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
	//��������Ƿ�Ϊ������
	function IsFloat(ch){
		var re = /^\d+(\.\d+)?$/;
		return re.test(ch);
	}
	function IsRight(print_date){ 
		var str = /^(\d{4})-(\d{2})-(\d{2})$/;
		return(str.test(print_date));
	}
	function isNum(){
		//var traycode = document.getElementById("traycode").value ;//��������
		var num = document.getElementById("num").value ;//����
		var invoiceid = document.getElementById("invoiceid").value;
	    var requestvoicedetailid = document.getElementById("requestvoicedetailid").value;
		if(traycode == null ||  traycode.length < 1)
		{
			alert("���������롿����Ϊ�գ�");
			return;
		}
		if(num == null || num.length <1 || !IsFloat(num)){
			alert("������������Ϊ�ջ�Ϊ���֣�");
			return;
		}else if(num<=0)
		{
			alert("������Ҫ����0��");
			return false;
		}
		
		
		var msg;
		DWREngine.setAsync(false);
		judgmentTool.isNum(invoiceid,requestvoicedetailid,num,Show);
		DWREngine.setAsync(true);
		
		function Show(value){
			msg = value;
		}
		if(msg == "Y"){
			return true;
		}
		if(msg != "Y"){
			alert(msg);
			return false;
		}
		//----------------��ȡĳ������Ӧ��ŵ�������Χ--------------------------
		
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
     <td class="TD_ADD" align="right">���뵥�ţ�</td>
     <td class="TD_ADD"><input type="text" name="invoiceid" value="<%=invoiceid!=null?invoiceid:""%>" class="rf_input_long" onchange="disinfo()">
         <input name="moreBtn" type="button" class="button_select" value="��" onclick="Getrequestvoiceid()" />
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">���뵥��ϸ��</td>
     <td class="TD_ADD"><input type="hidden" name="requestvoicedetailid" value="<%=requestvoicedetailid!=null?requestvoicedetailid:""%>"  onchange="GetInfo()"><input type="button" name="down" class="BUTTON_STYLE1" value="������ϸ" onClick="down();">&nbsp;&nbsp;&nbsp;<input type="button" name="up" class="BUTTON_STYLE1" value="������ϸ" onClick="up();">
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">��ϸ�ƻ�������</td>
     <td class="TD_ADD"><input type="text" name="plannum"  value="<%=plannum!=null?plannum:""%>" class="rf_input_long" maxlength="10" disabled>  <font color="red">*</font>
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">��ϸ��������</td>
     <td class="TD_ADD"><input type="text" name="bandnum"   value="<%=bandnum!=null?bandnum:""%>" class="rf_input_long" maxlength="10" disabled>  <font color="red">*</font>
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">��Ʒ���ƣ�</td>
     <td class="TD_ADD"><input type="text" name="productName" value="<%=productName!=null?productName:""%>" class="rf_input_long" maxlength="10" disabled>  <font color="red">*</font>
     <input type="hidden" name="productid" value="<%=productid!=null?productid:""%>"/>
     <input type="hidden" name="productStatus" value="<%=productStatus!=null?productStatus:""%>"/>
     </td>
   </tr>
   <tr> 
     <td class="TD_ADD" align="right">�������ڣ�</td>
     <td class="TD_ADD"><input type="text" name="printdate" value="<%=printdate!=null?printdate:""%>" class="rf_input_long" maxlength="10" disabled> <font color="red">*</font></td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">���ţ�</td>
     <td class="TD_ADD"><input type="text" name="lotinfo" value="<%=lotinfo!=null?lotinfo:""%>" class="rf_input_long" maxlength="10" disabled>  <font color="red">*</font>
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">�������룺</td>
     <td class="TD_ADD"><input type="text" name="traycode"  class="rf_input_long" maxlength="10">  <font color="red">*</font>
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">������</td>
     <td class="TD_ADD"><input type="text" name="num"  class="rf_input_long" maxlength="10" >  <font color="red">*</font>
     </td>
   </tr>
   <tr>
     <td class="TD_ADD" align="center" colspan="2">
       <input type="button" name="add" class="BUTTON_STYLE1" value="��" onClick="saveData();">
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
