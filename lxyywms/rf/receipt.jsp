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
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/getTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>

<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	var sp_flag = false;
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
		//�ջ���ID
		var invoiceid = myIframe.document.getElementById("invoiceid").value;
		//�ջ�����ϸID
		var detailid = myIframe.document.getElementById("detailid").value;	
		
		var knum = myIframe.document.getElementById("num").value;	 			//���ջ�����
		var knetweight = myIframe.document.getElementById("netweight").value;	//���ջ�����
		var kweight = myIframe.document.getElementById("weight").value;	 		//���ջ�ë��
			
		var num = myIframe.document.getElementById("renum").value;				//�ջ�����
		var netweight = myIframe.document.getElementById("renetweight").value;	//�ջ�����
		var weight = myIframe.document.getElementById("reweight").value;		//�ջ�ë��
			
		//��������ID
		var lotid  = myIframe.document.getElementById("lotid").value;		
		var lotatt1  = myIframe.document.getElementById("lotatt1").value; 	//��������1
		var lotatt2  = myIframe.document.getElementById("lotatt2").value; 	//��������2
		var lotatt3  = myIframe.document.getElementById("lotatt3").value; 	//��������3
		var lotatt4  = myIframe.document.getElementById("lotatt4").value; 	//��������4
		var lotatt5  = myIframe.document.getElementById("lotatt5").value; 	//��������5
		var lotatt6  = myIframe.document.getElementById("lotatt6").value; 	//��������6
		var lotatt7  = myIframe.document.getElementById("lotatt7").value; 	//��������7
		var lotatt8  = myIframe.document.getElementById("lotatt8").value; 	//��������8
		var lotatt9  = myIframe.document.getElementById("lotatt9").value; 	//��������9
		var lotatt10  = myIframe.document.getElementById("lotatt10").value; //��������10
		var lotatt11  = myIframe.document.getElementById("lotatt11").value; //��������11
		var lotatt12  = myIframe.document.getElementById("lotatt12").value; //��������12
		
		if(invoiceid == null || invoiceid.length < 1){
			alert("���ջ���������Ϊ�գ�");
			UnLockButton();
			return;
		}
		if(detailid == null || detailid.length < 1){
			alert("���ջ�����ϸ������Ϊ�գ�");
			UnLockButton();
			return;
		}
		
			//�������
			if(num == null || num.length < 1 || !IsFloat(num)){
				alert("���ջ�����������Ϊ����ֻ��Ϊ���֣�");
				UnLockButton();
				return;
			}
			if(parseFloat(num) > parseFloat(knum)){
				alert("���ջ�����(" + num + ")�����ܴ����ջ�����ϸ�ġ����ջ�����(" + knum + ")����");
				UnLockButton();
				return;
			}
			if(parseFloat(num) <= 0){
				alert("���ջ�����(" + num + ")��Ҫ����0��");
				UnLockButton();
				return;
			}
			if(netweight != null && netweight.length > 0 && !IsFloat(netweight)){
				alert("���ջ����ء�ֻ��Ϊ���֣�");
				UnLockButton();
				return;
			}else if(netweight == null || netweight.length < 1)
			{
				netweight = "0";
			}
			if(parseFloat(netweight) > parseFloat(knetweight)){
				alert("���ջ�����(" + netweight + ")�����ܴ����ջ�����ϸ�ġ����ջ�����(" + knetweight + ")����");
				UnLockButton();
				return;
			}
			
			if(weight != null && weight.length > 0 && !IsFloat(weight)){
				alert("���ջ�ë�ء�ֻ��Ϊ���֣�");
				UnLockButton();
				return;
			}else if(weight == null || weight.length < 1)
			{
				weight = "0";
			}
			if(parseFloat(weight) > parseFloat(kweight)){
				alert("���ջ�ë��(" + weight + ")�����ܴ����ջ�����ϸ�ġ����ջ�ë��(" + kweight + ")����");
				UnLockButton();
				return;
			}
			//��������ֵ��֤
			if(myIframe.checkLotatt()==false){
				return;
			}

		var details = "&invoicedetailid=" + detailid + "&num=" + num + "&weight=" + weight + "&netweight=" + netweight + "&volume=0";	
		
		if(confirm("����ջ�����Ϊ"+num+",��ȷ���Ƿ��ջ���"))
		{
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=rfReceiptAction";
			//������
			var msg = "<input type=hidden name='lotatt1' value='"+lotatt1+"'>"
						+ "<input type=hidden name='lotatt2' value='"+lotatt2+"'>"
						+ "<input type=hidden name='lotatt3' value='"+lotatt3+"'>"
						+ "<input type=hidden name='lotatt4' value='"+lotatt4+"'>"
						+ "<input type=hidden name='lotatt5' value='"+lotatt5+"'>"
						+ "<input type=hidden name='lotatt6' value='"+lotatt6+"'>"
						+ "<input type=hidden name='lotatt7' value='"+lotatt7+"'>"
						+ "<input type=hidden name='lotatt8' value='"+lotatt8+"'>"
						+ "<input type=hidden name='lotatt9' value='"+lotatt9+"'>"
						+ "<input type=hidden name='lotatt10' value='"+lotatt10+"'>"
						+ "<input type=hidden name='lotatt11' value='"+lotatt11+"'>"
						+ "<input type=hidden name='lotatt12' value='"+lotatt12+"'>";
			formx1.innerHTML = msg;
			formx1.action = strUrl + "&method=receipt&invoiceid="+ invoiceid + "&lotid=" + lotid + details;
			document.formx1.submit();
				
		}else{
			UnLockButton();
		}
	}
	
	//��ȡ�ջ�����ϸ
	function getInvoiceDetail()
	{
		//����ID
		var invoiceid = document.getElementById("inboundInvoice").value;
		if (invoiceid != null && invoiceid.length > 1) 
		{
			myIframe.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=rfReceiptAction&flag=1&invoiceid=" + invoiceid;
		}else{
			alert("��ѡ���ջ�����");
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
		selectObject("<%=strWarehouseID%>", "inboundInvoice", "11");
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
     <td width="60%" align="center" class="font_006699_bold_12">RF�ջ�</td>
     <td width="20%" align="right"><a href="<%=request.getContextPath()%>/rf/main.jsp?warehouseid=<%=strWarehouseID%>">����</a></td>
   </tr>
 </table>
     </td>
   </tr>
    <tr>  
     <td class="TD_ADD" align="right">�� �� ����</td>
     <td class="TD_ADD"><select id="inboundInvoice" class="rf_select" onchange="getInvoiceDetail();"><option value="">--��ѡ�񵥾�--</option></select> <font color="red">*</font>
     </td>
   </tr>

   <tr>
	 <td class="TD_ADD" colspan="2" valign="top"  height="410">
	   <iframe id="myIframe" src="<%=request.getContextPath()%>/rf/receipt_list.jsp" frameborder="0" width="100%" height="100%" scrolling="no">
	   </iframe>
	 </td>
   </tr>
   <tr>
     <td align="center" height="10" colspan="2"></td>
   </tr>
   <tr>
     <td class="TD_ADD" align="center" colspan="2">
       <input type="button" name="add" class="BUTTON_STYLE1" value="�ջ�" onClick="saveData();">
     </td>
    </tr>
 </table>
 <FORM action="" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
