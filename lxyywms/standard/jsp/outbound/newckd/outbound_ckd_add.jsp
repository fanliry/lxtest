<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="com.ricosoft.common.tools.StrTools"%>
<%@ page import="java.util.HashMap"%>
<%
	String strTime = StrTools.getCurrDateTime(8);
	String warehouseid = (String) request.getSession(false)
			.getAttribute("warehouseid"); //�ֿ�
%>
<html>
	<head>
		<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="<%=request.getContextPath()%>/standard/style/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/dwr/engine.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/dwr/util.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/standard/js/calendartime.js"></script>
		<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = "";
	var type;
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
	//����
	function getWhAreaVirtualList(id, obj)
	{
	    selectView.getWhAreaVirtualList(id, "001", {callback:function(data){viewSelect1(data,obj);}});
	}
	function viewSelect1(data, obj)
	{
		var gs = list.document.getElementsByName(obj);
		for(var k=0; k<gs.length; k++)
		{
			var g = gs[k];
			while(g.options.length > 0) 
			{
	       		g.remove(g.options.length-1);
	   		}
		   	var op0=new Option("--��ѡ��--");        
		   	g.options.add(op0);
		    op0.value="";
			for(var i = 0; i < data.length; i++)
			{
				var op1=new Option(data[i].strName);        
		    	g.options.add(op1);
		    	op1.value=data[i].strId;
		    	if(data[i].strFlag == "1")
		    	{
		    		op1.selected=true;
		    	}	
			}
		}

	}
	//��ȡ�ͻ�
 	function SelectCustomer(){
		var result = showModalDialog("<%=request.getContextPath()%>/jsp/maintenance/test2/popup.jsp", "", "dialogWidth=900px; dialogHeight=500px; scroll=no");
	   	if(result != null && result.length > 0){
	 		var aem = result.split("|");
	 		document.getElementById("customer_id").value = aem[0];
	 		document.getElementById("customer_name").value = aem[1];
	   	}	   	
	}
	var loattLen = 0;
	//ѡ����Ʒ
	function SelectOut(flag){
		var tb = list.document.getElementById("tb");
		var lastflag = document.getElementById("typeflag").value;
		var result = "";
		if(flag == "order"){//ѡ�񶩵�
		    loattLen = 0;
			result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/outbound/so/outbound_so.jsp", "", 
				"dialogWidth=800px;dialogHeight=600px;"); //������ϸid&��������&�ͻ�id&�ͻ���&��λ&Ʒ��&��λ&���� , ������ϸid&��������&�ͻ�id&�ͻ���&��λ&Ʒ��&��λ&����
			if(result != null && result.length > 0){
				for(var i=tb.rows.length-2; i>0 ; i--){
					tb.deleteRow(i);
				}
				var sotyep="";
				var aem = result.split(",");//������ϸid&��������&�ͻ�id&�ͻ���&��λ&Ʒ��&Ʒ��&��λ&���&����, ������ϸid&��������&�ͻ�id&�ͻ���&��λ&Ʒ��&��λ&����
				for(var i=0; i<aem.length; i++){
				    var aem1 = aem[i].split("&");
				    /* var temp = aem1[0].split("/");
				    if(i==0){
				        sotyep =temp[0];
				        aem1[0]=temp[1];
				    } */
				    if(aem1 != null && aem1.length== 11){//9��& ��10��ֵ
				       document.getElementById("saleno").value = aem1[1];
 				       document.getElementById("customer_id").value = aem1[2];
 				       document.getElementById("customer_name").value = aem1[3];
	 				    //����һ��
	        			newRow = tb.insertRow(tb.rows.length-1);
	        			newRow.setAttribute("onmouseover", function(){this.bgColor='#E2E8EA'});
						newRow.setAttribute("onmouseout", function(){this.bgColor=''});
	        			
	        			//�к�
				   		newCell = newRow.insertCell(newRow.cells.length);
				   		newCell.setAttribute( "className", "TD_LIST");
				   		newCell.setAttribute( "align", "center");
				   		newCell.innerHTML = "<div align='center'><input type='checkbox' name='check_id' class='input_checkbox' value='"+aem1[0]+"' onClick='Change(this)'>"
				    					   +(tb.rows.length-2)+"</div>"; 
				   		//����
				    	newCell = newRow.insertCell(newRow.cells.length);
				    	newCell.setAttribute( "className", "TD_LIST");
				    	newCell.setAttribute( "align", "center");
				    	//newCell.innerHTML = "<div align='center'><input type='hidden' value=''></div>";
			    	    newCell.innerHTML = "<div align='center'><select name='Virtualwhid'><option value=''></option></select></div>";	
				    	//Ʒ��
				    	newCell = newRow.insertCell(newRow.cells.length);
				    	newCell.setAttribute( "className", "TD_LIST");
				    	newCell.setAttribute( "align", "center");
				    	newCell.innerHTML = "<div align='center'>"+aem1[5]+"</div>";
				    	//Ʒ��
				    	newCell = newRow.insertCell(newRow.cells.length);
				    	newCell.setAttribute( "className", "TD_LIST");
				    	newCell.setAttribute( "align", "center");
				    	newCell.innerHTML = "<div align='center'>"+aem1[6]+"</div>";
				    	//���
				    	newCell = newRow.insertCell(newRow.cells.length);
				    	newCell.setAttribute( "className", "TD_LIST");
				    	newCell.setAttribute( "align", "center");
				    	newCell.innerHTML = "<div align='center'>"+aem1[8]+"</div>";
				    	//��λ
				    	newCell = newRow.insertCell(newRow.cells.length);
				    	newCell.setAttribute( "className", "TD_LIST");
				    	newCell.setAttribute( "align", "center");
				    	newCell.innerHTML = "<div align='center'>"+aem1[7]+"</div>";
				    	//����
						newCell = newRow.insertCell(newRow.cells.length);
						newCell.setAttribute( "className", "TD_LIST");
						newCell.setAttribute( "align", "center");
						newCell.innerHTML  = "<div align='center'><input type='text' style='width:100px;text-align: center;' value='"+aem1[9]+"'></div>";

						getWhAreaVirtualList("","Virtualwhid");
				    }
				}
				
				/* var outtype=document.getElementById("outtype");
				var ops=outtype.options;
				for(var i=0;i<ops.length; i++){	
				if(ops[i].value == sotyep){
				   ops[i].selected = "selected";
				   break;
				}} */
				
			}
			document.getElementById("typeflag").value="order";
		}
	}
	function Save()
	{
		var form_type = document.getElementById("outtype").value;				//��������
		//var owner_id = document.getElementById("owner_id").value;			    //����
		var customer_id = document.getElementById("customer_id").value;			//�ͻ�
		//var address = document.getElementById("address").value;				//�ջ���ַ
		var vehicleno = document.getElementById("vehicleno").value;				//���ƺ�
		var vehiclepos =  document.getElementById("vehiclepos").value;			//��λ
		var departid = document.getElementById("departid").value;			    //����
		var sendplatform = document.getElementById("sendplatform").value;	    //������̨
		var flnum = ""; //document.getElementById("flnum").value;			            //��������
		var saleno =  document.getElementById("saleno").value;					//����������
		var formdate =  document.getElementById("formdate").value;		        //��������
		var requestdate = ""; // document.getElementById("requestdate").value;		//Ҫ�󷢻�ʱ��
		var expectdate =  ""; // document.getElementById("expectdate").value;		    //Ԥ�ڷ���ʱ��

		
		if(form_type == null || form_type.length < 1)
		{
			alert("��ѡ�񡾵������͡���");
			return;
		}
		if(customer_id == null || customer_id.length < 1)
		{
			alert("���ͻ�������Ϊ�գ�");
			return;
		}
		if(formdate == null || formdate.length < 1)
		{
			alert("������ʱ�䡿����Ϊ�գ�");
			return;
		}
		if(departid == null || departid.length < 1)
		{
			alert("�����š�����Ϊ�գ�");
			return;
		}
		
		
		// + "&owner_id=" + owner_id  + "&address=" + address
		condition = "&formdate=" + formdate + "&form_type=" + form_type + "&customer_id=" + customer_id
				+ "&vehicleno=" + vehicleno + "&vehiclepos=" + vehiclepos + "&departid=" + departid + "&sendplatform=" + sendplatform 
				+ "&flnum=" + flnum + "&saleno=" + saleno + "&requestdate=" + requestdate + "&expectdate=" + expectdate;
		
		var value = "";
		var line_value = "";
		var detail = "";
		var tb = list.document.getElementById("tb");
		var typeflag = document.getElementById("typeflag").value;
		//ѡ�񶩵�
		if(typeflag == "order"){
			for(var i=1; i<tb.rows.length-1; i++){
				    //���ⶩ����ϸid
					var detailid = tb.rows.item(i).cells.item(0).getElementsByTagName('input')[0].value;
					var num = tb.rows.item(i).cells.item(6).getElementsByTagName('input')[0].value;
					var whare = tb.rows.item(i).cells.item(1).getElementsByTagName('select')[0].value;
					if(whare == null || whare.length < 1)
					{
						alert("��" + i + "�С�����������Ϊ�գ�");
						return;
					}
					detail+=detailid+","+num+","+whare+"|";
			}
		}
		if(detail.length < 1){
			alert("�������ݲ���Ϊ�գ�");
			return;
		}
		detail = detail.substring(0,detail.length-1);
		var action="outBoundAction";
		if(typeflag == "super"){
			action="outBoundAction";
		}

		window.returnValue = strUrl +action+"&method=addRkd&flag="+typeflag + condition+"&detail="+detail;
		window.close();
	}
	
 	//����
	function Report(){
		var id = document.getElementById("out_warehouse_id").value;
		if(id == null || id.length <1){
			alert("���ⵥ�ݺ�Ϊ�գ�");
		}
		else{
			window.open(ac + "MNWarehouseOutQuery&flag=5&id="+id, "report");
		}
	}
	//��ȡ�ж���
	function GetRowObj(obj){
		var i = 0;
		while(obj.tagName.toLowerCase() != "tr"){
			obj = obj.parentNode;
			if(obj.tagName.toLowerCase() == "table")
				return null;
		}
		return obj;
	}
	//��ȡ�����к�
	function GetRowNo(obj){
		var trObj = GetRowObj(obj); 
		var trArr = trObj.parentNode.children;
		for(var i= 0; i<trArr.length; i++){
			if(trObj == trObj.parentNode.children[i]){
				return i;
			}
		}
	}
	//ɾ����
	function DeleteRowOld(){
		var tb = list.document.getElementById("tb");
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=check_ids.length-1; i>= 0; i--){
			if(check_ids[i].checked == true){
				var row1 = GetRowNo(check_ids[i]);
				var row2 = FindRow(tb, row1, tb.rows.item(row1).cells.item(3).innerText);//Ѱ��С����
				var num = parseInt(tb.rows.item(row2).cells.item(6).innerText)-parseInt(tb.rows.item(row1).cells.item(6).innerText);
				if(num == 0){
					tb.deleteRow(row2);
				}
				else{
					tb.rows.item(row2).cells.item(6).innerText = num;
				}
				tb.deleteRow(row1);
			}
		}
		ReSum(tb);//���ºϼ�
		ReNo();//���±��
 	}
 	//ɾ����
	function DeleteRow(){
		var tb = list.document.getElementById("tb");
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=check_ids.length-1; i>= 0; i--){
			if(check_ids[i].checked == true){
				tb.deleteRow(i+1);
			}
		}
 	}
 	//���ºϼ�
 	function ReSum(tb){
 		if(tb.rows.length == 2){
			return;
		}
 		var sum = 0;
 		for(var i=1; i<tb.rows.length-1; i++){
 			if(tb.rows.item(i).cells.item(1).innerText != ""){
 				sum += parseInt(tb.rows.item(i).cells.item(6).innerText);
 			}
 		}
 		if(sum == 0){
 			tb.deleteRow(tb.rows.length-2);
 		}
 		else{
 			tb.rows.item(tb.rows.length-2).cells.item(6).innerText = sum;
 		}
 	}
 	//Ѱ��С����
	function FindRow(tb, row, key){
		if(tb.rows.length == 2){
			return 1;
		}
		else{
			var find = false;
			for(var i=1; i<tb.rows.length-1; i++){
				if(!find && tb.rows.item(i).cells.item(3).innerText == key){//���ҵ���ؼ��֣���Ʒ���ƣ���ͬ����
					find = true;
				}
				else if(find && i > row && tb.rows.item(i).cells.item(3).innerText != tb.rows.item(i-1).cells.item(3).innerText){//�ҵ��ò�ƷС�Ƶ���
					break;
				}
			}
			return i;
		}
	}
 	//���±��
	function ReNo(){
		var tb = list.document.getElementById("tb");
		for(var i=1, k=1; i<tb.rows.length-1; i++){
			if(tb.rows.item(i).cells.item(1).innerText != ""){
				tb.rows.item(i).cells.item(1).innerText = k++;
			}
		}
		if(tb.rows.length <= 2){
			document.getElementById("out_type").disabled = false;
			document.getElementById("on_line_type").disabled = false;
		}
	}
	function Change(type)
	{
		if(type == 1)
		{
			if(document.getElementById("on_line_type").value == "2")
			{
				document.getElementById("order").disabled = true;
				document.getElementById("product").disabled = true;
			}
			else
			{
				document.getElementById("order").disabled = false;
				document.getElementById("product").disabled = false;
			}
			
		}
	}
	
 	function isChange()
 	{
		var outtype = document.getElementById("outtype").value;
			document.getElementById("changename").innerHTML = "��&nbsp;&nbsp;&nbsp;&nbsp;����";
		document.getElementById("moreBtn").onclick = function(){openCustomer("<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=","800","520");};
		if(outtype == "4")
		{
			document.getElementById("changename").innerHTML = "��&nbsp;&nbsp;&nbsp;&nbsp;����";
			document.getElementById("moreBtn").onclick = function(){openCustomer("<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=5","800","520");};
		}
 	}
	//��ʼ��
	function OnLoad()
	{
		selectType('', 'outtype', 'ckdj');
		selectType('', 'vehiclepos', 'carpos');
	}
-->
</script>
	</head>

	<body onload="OnLoad()">

		<input type="hidden" name="out_warehouse_id" value=""/>
		<div class="con_bk">
			<table width="100%" height="100%" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td>
						<table width="98%" align="center" border="0" cellspacing="0"
							cellpadding="0">
							<tr>
								<td class="font_006699_bold_12" height="25">
									��ǰλ�ã�������� -&gt; �½����ⵥ -&gt; ���
								</td>
							</tr>
						</table>

						<table width="98%" border="0" align="center" cellpadding="0"
							cellspacing="0" class="table1">
							<tr>
								<td style="height: 2px"></td>
							</tr>
							<tr>
								<td>
									<input onClick="SelectOut('order')" type="button" name="order"
										value="ѡ�񶩵�" class="BUTTON_STYLE1">
								</td>
								<td align="right">
									<input type="button" value="����" id="save" class="BUTTON_STYLE1"
										onClick="Save()">
									<input type="button" value="ɾ����" id="del" class="BUTTON_STYLE1"
										onClick="DeleteRow()">
									<input type="button" value="�ر�" class="BUTTON_STYLE1"
										id="button2" onClick="window.close();" />
								</td>
							</tr>
							<tr>
								<td style="height: 2px"></td>
							</tr>
						</table>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="5"></td>
							</tr>
						</table>
						<table width="98%" border="0" align="center" cellpadding="0"
							cellspacing="0" class="table1">
							<tr>
								<td width="13%" align="right" class="yx1">
									�������ڣ�
								</td>
								<td width="18%" class="yx">
									<input type='hidden' id='typeflag' value=''>
									<!-- order cs product space -->
									<input name="formdate" id="formdate" type="text" size="13"
										value="<%=strTime%>" onfocus="calendar();" class="Wdate"
										style="height: 18px; width: 135px; " />
								</td>
								<td width="15%" align="right" class="yx1">
									<span class="red">*</span>�������ͣ�
								</td>
								<td width="20%" class="yx">
									<div class="select_search">
										<select id="outtype" style="width: 135px;" onchange="isChange();">
											<option>
												---��ѡ��---
											</option>
										</select>
									</div>
								</td>
								<td width="15%" align="right" class="yx1">
									<span class="red">*</span>����״̬��
								</td>
								<td width="19%" class="xx1">
									<div class="select_search">
										<select id="instatus" disabled style="width: 135px;">
											<option selected="selected">
												����
											</option>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<td align="right" class="yx1">
									�������ţ�
								</td>
								<td class="yx">
									<input type="text" name="saleno" id="saleno" size="16"
										style="height: 18px; width: 135px;" readonly="readonly" class="input_readonly" />
								</td>
								<td align="right" class="yx1">
									<span class="red">*</span><span id="changename">��&nbsp;&nbsp;&nbsp;&nbsp;����</span>
								</td>
								<td class="yx">
									<input type="text" name="customer_name" id="customer_name"
										readonly="readonly" class="input_readonly"
										style="height: 18px; width: 111px;" />
									<input type="hidden" id="customer_id" />
									<input name="moreBtn" type="button" class="button_select"
										value="��"
										onclick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp?type=','800','520');" />
								</td>
								<td align="right" class="yx1">
									<span class="red">*</span>��&nbsp;&nbsp;&nbsp;&nbsp;�ţ�
								</td>
								<td class="xx1">
									<input type="text" id="departname" readonly="readonly"
										class="input_readonly" style="height: 18px; width: 111px;" />
									<input type="hidden" id="departid" />
									<input name="moreBtn" type="button" class="button_select"
										value="��"
										onclick="openDepartment('<%=request.getContextPath()%>/standard/jsp/common/common_department.jsp','800','520','departid','departname');" />
								</td>
							</tr>
							<tr>
								<td align="right" class="yx1">
									��&nbsp;&nbsp;&nbsp;&nbsp;�ƣ�
								</td>
								<td class="yx">
									<input type="text" id="vehicleno"
										style="height: 18px; width: 135px;" />
								</td>
								<td align="right" class="yx1">
									��&nbsp;&nbsp;&nbsp;&nbsp;λ��
								</td>
								<td class="yx">
									<div class="select_search">
										<select id="vehiclepos" style="width: 135px;">
											<option value="">
												--��ѡ��--
											</option>
										</select>
									</div>
								</td>
								<td align="right" class="yx1">
									������̨��
								</td>
								<td class="xx1">
									<input type="text" name="sendplatform" id="sendplatform"
										style="height: 18px; width: 135px;" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td height="5"></td>
				</tr>
				<tr>
					<td valign="top" height="100%">
						<table width="98%" height="100%" border="0" align="center"
							cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<iframe id="list" name="list" width="100%" height="100%"
										frameborder="0" scrolling="auto"
										src="<%=request.getContextPath()%>/standard/jsp/outbound/newckd/outbound_ckd_add_list.jsp"></iframe>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
