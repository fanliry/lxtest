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
		
		//��������
		var traycode = document.getElementById("tray_code").value;
		//�ֿ�ID
		var warehouseid = document.getElementById("warehouseid").value;
		//����ID
		var whAreaId = document.getElementById("whAreaId").value;
		//�ϼܷ�ʽ
		var putmode = document.getElementById("putmode").value;
		
		
		//if(whAreaId == null || whAreaId.length < 1){
		//	alert("������������Ϊ�գ�");
		//	UnLockButton();
		//	return;
		//}
		if(putmode == null || putmode.length < 1){
			alert("���ϼܷ�ʽ������Ϊ�գ�");
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
		
		
		
		//������
		var msg = "";
		var transrows = 0;	//�м�������
		//���
		var objtb = list.document.getElementById("puttb");
		for(var i=1; i<objtb.rows.length; i++){
			transrows = transrows + 1;
			//��1��
			var transid = objtb.rows.item(i).cells.item(0).getElementsByTagName("input")[0].value;
			//��3��
			var num = objtb.rows.item(i).cells.item(2).getElementsByTagName("input")[0].value;
			var netweight = objtb.rows.item(i).cells.item(2).getElementsByTagName("input")[1].value;
			var weiht = objtb.rows.item(i).cells.item(2).getElementsByTagName("input")[2].value;
			//��2��
			var lotatt1 = objtb.rows.item(i).cells.item(1).getElementsByTagName("input")[0].value;
			var lotatt2 = objtb.rows.item(i).cells.item(1).getElementsByTagName("input")[1].value;
			var lotatt3 = objtb.rows.item(i).cells.item(1).getElementsByTagName("input")[2].value;
			var lotatt4 = objtb.rows.item(i).cells.item(1).getElementsByTagName("input")[3].value;
			var lotatt5 = objtb.rows.item(i).cells.item(1).getElementsByTagName("input")[4].value;
			var lotatt6 = objtb.rows.item(i).cells.item(1).getElementsByTagName("input")[5].value;
			var lotatt7 = objtb.rows.item(i).cells.item(1).getElementsByTagName("input")[6].value;
			var lotatt8 = objtb.rows.item(i).cells.item(1).getElementsByTagName("input")[7].value;
			var lotatt9 = objtb.rows.item(i).cells.item(1).getElementsByTagName("input")[8].value;
			var lotatt10 = objtb.rows.item(i).cells.item(1).getElementsByTagName("input")[9].value;
			var lotatt11 = objtb.rows.item(i).cells.item(1).getElementsByTagName("input")[10].value;
			var lotatt12 = objtb.rows.item(i).cells.item(1).getElementsByTagName("input")[11].value;
			
			msg = msg + "<input type=hidden name='"+transrows+"transid' value='"+transid+"'>"
					+ "<input type=hidden name='"+transrows+"num' value='"+num+"'>"
					+ "<input type=hidden name='"+transrows+"netweight' value='"+netweight+"'>"
					+ "<input type=hidden name='"+transrows+"weiht' value='"+weiht+"'>"
					+ "<input type=hidden name='"+transrows+"volume' value='0'>"
					+ "<input type=hidden name='"+transrows+"lotatt1' value='"+lotatt1+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt2' value='"+lotatt2+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt3' value='"+lotatt3+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt4' value='"+lotatt4+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt5' value='"+lotatt5+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt6' value='"+lotatt6+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt7' value='"+lotatt7+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt8' value='"+lotatt8+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt9' value='"+lotatt9+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt10' value='"+lotatt10+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt11' value='"+lotatt11+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt12' value='"+lotatt12+"'>";
		}

		//************************************************
		if(transrows > 0){
			if(confirm("��ȷ���Ƿ������ϼ�����"))
			{		
				var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=putawayAction";
	
				//������
				msg = msg + "<input type=hidden name='transrows' value='"+transrows+"'>";
				formx1.innerHTML = msg;
				formx1.action = strUrl + "&method=putaway&traycode=" + traycode + "&warehouseid=" + warehouseid + "&whAreaId=" + whAreaId + "&putmode=" + putmode;
				document.formx1.submit();
					
			}else{
				UnLockButton();
			}
		}else{
			alert("��������Ҫ�ϼܵ���Ϣ��");
			UnLockButton();
		}
	}
	
	
	
	
	
	
	
	
	
	//��������
	function addData()
	{
		var transid = myIframe.document.getElementById("transid").value;//�ջ���¼��
		if(transid == null || transid.length < 1){
			alert("���ջ���¼������Ϊ�գ�");
			return;
		}else{
			var proudctname = myIframe.document.getElementById("product_name").value;//��Ʒ
			var packname = myIframe.document.getElementById("packname").value;//��װ
			var unitname = myIframe.document.getElementById("eunit").value;//��λ

			var knum = myIframe.document.getElementById("num").value;	 			//���ϼ�����
			var knetweight = myIframe.document.getElementById("netweight").value;	//���ϼܾ���
			var kweight = myIframe.document.getElementById("weight").value;	 		//���ϼ�ë��
			
			var num = myIframe.document.getElementById("renum").value;				//�ϼ�����
			var netweight = myIframe.document.getElementById("renetweight").value;	//�ϼܾ���
			var weight = myIframe.document.getElementById("reweight").value;		//�ϼ�ë��
			
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
			
			//�������
			if(num == null || num.length < 1 || !IsFloat(num)){
				alert("���ϼ�����������Ϊ����ֻ��Ϊ���֣�");
				return;
			}
			if(parseFloat(num) > parseFloat(knum)){
				alert("���ϼ�����(" + num + ")�����ܴ����ջ���¼�ġ����ϼ�����(" + knum + ")����");
				return;
			}
			if(netweight != null && netweight.length > 0 && !IsFloat(netweight)){
				alert("���ϼܾ��ء�ֻ��Ϊ���֣�");
				return;
			}else if(netweight == null || netweight.length < 1)
			{
				netweight = "0";
			}
			if(parseFloat(netweight) > parseFloat(knetweight)){
				alert("���ϼܾ���(" + netweight + ")�����ܴ����ջ���¼�ġ����ϼܾ���(" + knetweight + ")����");
				return;
			}
			
			if(weight != null && weight.length > 0 && !IsFloat(weight)){
				alert("���ϼ�ë�ء�ֻ��Ϊ���֣�");
				return;
			}else if(weight == null || weight.length < 1)
			{
				weight = "0";
			}
			if(parseFloat(weight) > parseFloat(kweight)){
				alert("���ϼ�ë��(" + weight + ")�����ܴ����ջ���¼�ġ����ϼ�ë��(" + kweight + ")����");
				return;
			}
			//��������ֵ��֤
			if(myIframe.checkLotatt()==false){
				return;
			}
			
			//���ӵ������*******************************************
			//����һ��
			var objtb = list.document.getElementById("puttb");
			var newRow = null;	//��
			var newCell = null; //��
			
			//����ջ���¼�Ƿ����ظ�
			for(var i=1; i < objtb.rows.length; i++)//���ı����в����ж�
			{
				var rowtransid = objtb.rows.item(i).cells.item(0).getElementsByTagName("input")[0].value;
				if(transid == rowtransid){
					alert("���ջ���¼���Ѵ��ڣ���ѡɾ����Ӧ�ջ���¼�����ӣ�");
					return;
				}
			}
			
			//����һ��
	        newRow = objtb.insertRow(objtb.rows.length);
	        newRow.setAttribute("onmouseover", function(){this.bgColor='#E2E8EA'});
			newRow.setAttribute("onmouseout", function(){this.bgColor=''});
			
			//���ٺ�
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute( "className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+transid+"<input type='hidden' name='key' value='"+transid+"'></div>"; 
			//Ʒ��
	        newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+proudctname+"<input type='hidden' name='lot1' value='"+lotatt1+"'>"+
																   "<input type='hidden' name='lot2' value='"+lotatt2+"'>"+
																   "<input type='hidden' name='lot3' value='"+lotatt3+"'>"+
																   "<input type='hidden' name='lot4' value='"+lotatt4+"'>"+
																   "<input type='hidden' name='lot5' value='"+lotatt5+"'>"+
																   "<input type='hidden' name='lot6' value='"+lotatt6+"'>"+
																   "<input type='hidden' name='lot7' value='"+lotatt7+"'>"+
																   "<input type='hidden' name='lot8' value='"+lotatt8+"'>"+
																   "<input type='hidden' name='lot9' value='"+lotatt9+"'>"+
																   "<input type='hidden' name='lot10' value='"+lotatt10+"'>"+
																   "<input type='hidden' name='lot11' value='"+lotatt11+"'>"+
																   "<input type='hidden' name='lot12' value='"+lotatt12+"'></div>";
			
			//����
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+num+"<input type='hidden' name='nu' value='"+num+"'>"+
														   "<input type='hidden' name='netwei' value='"+netweight+"'>"+
														   "<input type='hidden' name='wei' value='"+weight+"'></div>";
			
			//ɾ��
	        newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST2");
			newCell.setAttribute( "align", "center");
			newCell.setAttribute("onclick", function (){DeleteRow(newRow)});
			newCell.innerHTML = "<input type='button' name='details' class='BUTTON_STYLE1' value='ɾ��'>";
		
		}	
	}
	//ɾ����
	function DeleteRow(obj)
	{
		var tb = list.document.getElementById("puttb");
		if(tb.rows.length > 1)
		{
			tb.deleteRow(obj.rowIndex);
		}
		else
		{
			alert("ҳ�������ݼ�¼��");
		}
 	}
	

	//��ʾ�����Ϣ
	function getInventory(){

		//����ID
		var invoiceid = document.getElementById("inboundInvoice").value;
		if (invoiceid != null && invoiceid.length > 1) 
		{
			myIframe.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=rfReceiptAction&flag=2&invoiceid=" + invoiceid;
			
			list.location.href = "<%=request.getContextPath()%>/rf/putaway_detail.jsp";
			
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
		selectObject("<%=strWarehouseID%>", "inboundInvoice", "12");
		selectAreaList('', 'whAreaId', '<%=strWarehouseID%>', "1")// ����
		//�ϼܷ�ʽ
		selectType('PL', 'putmode', 'putmode');
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
     <td width="60%" align="center" class="font_006699_bold_12">RF�ϼ�</td>
     <td width="20%" align="right"><a href="<%=request.getContextPath()%>/rf/main.jsp?warehouseid=<%=strWarehouseID%>">����</a></td>
   </tr>
 </table>
     </td>
   </tr>
    <tr>  
     <td class="TD_ADD" align="right">�� �� ����</td>
     <td class="TD_ADD"><select id="inboundInvoice" class="rf_select" onchange="getInventory();"><option value="">--��ѡ�񵥾�--</option></select> <font color="red">*</font>
     </td>
   </tr>
   <tr> 
     <td class="TD_ADD" align="right">�������룺</td>
     <td class="TD_ADD"><input type="text" id="tray_code" value="" class="rf_input_long" maxlength="10"> </td>
   </tr>
   <tr>
     <td class="TD_ADD" align="right">�ϼܿ�����</td>
     <td class="TD_ADD"><input type="hidden" id="warehouseid" value="<%=strWarehouseID%>">
     		<select name="whAreaId"  style="width:162px;" >
	            <option>--��ѡ��--</option>
	           </select> 
     </td>
   </tr>
   <tr>
     <td class="TD_ADD" align="right">�ϼܷ�ʽ��</td>
     <td class="TD_ADD">
     		<select name="putmode"  style="width:162px;" >
	            <option>--��ѡ��--</option>
	           </select> <font color="red">*</font>
     </td>
   </tr>
   
   <tr>
	 <td class="TD_ADD" colspan="2" valign="top"  height="410">
	   <iframe id="myIframe" src="<%=request.getContextPath()%>/rf/putaway_list.jsp" frameborder="0" width="100%" height="100%" scrolling="no">
	   </iframe>
	 </td>
   </tr>
   <tr>
     <td align="center" height="10" colspan="2"></td>
   </tr>
   
   <tr>
     <td class="TD_ADD" align="center" colspan="2">
       <input name="button_add" type="button" class="button_add" id="button_add" value="&nbsp;&nbsp;������Ϣ" onClick="addData();"  />
            &nbsp;&nbsp;
       <input type="button" name="add" class="BUTTON_STYLE1" value="�����ϼ�" onClick="saveData();">
     </td>
    </tr>
 </table>
 
  <table width="100%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <TR height="100%">
   	<td height="100%">
   		<iframe id="list" name="list" width="100%" height="100%"   frameborder="0"  scrolling="yes"
	               src="<%=request.getContextPath()%>/rf/putaway_detail.jsp">
   	</td>
   </TR>
 </table>
<FORM action="" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
