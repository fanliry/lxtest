<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools"%>
<%@ page import="java.util.HashMap"%>
<%
	HashMap hsPopedom = null;
	boolean inbound_scnewsqdproduct = false;    //ѡ���Ʒ
	boolean inbound_scnewsqdsave = false;     //����
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("inbound_scnewsqdproduct") != null){
			inbound_scnewsqdproduct = true;
		}
		if(hsPopedom.get("inbound_scnewsqdsave") != null){
			inbound_scnewsqdsave = true;
		}
    }
%>
<%
	String strTime =  StrTools.getCurrDateTime(8); 
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>�㽭���������ֿ����ϵͳ</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />

<script>
<!--����Ӧ�߶ȵ�div-->
 	var condition = null;
    function Select()
	{
		var result,values;
		var tableObj = list.document.getElementById("tb");
		result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp",'','dialogWidth=800px;dialogHeight=520px');
		
		
	    if(result != null && result.length > 0)
        {
        	result = result.split(",");
        	for(var i = 0; i < result.length; i++)
        	{
        	    
        		var cs = result[i].split("|");
        		//����һ��
        		var newRowObj = tableObj.insertRow(tableObj.rows.length-1); 
        		
        		//ѡ���
        		var newCheck=newRowObj.insertCell(newRowObj.cells.length);
			   	newCheck.setAttribute( "className", "TD_LIST");
			   	//��Ʒ����
			    var newProductCode=newRowObj.insertCell(newRowObj.cells.length);
			    newProductCode.setAttribute( "className", "TD_LIST"); 
			   	//��Ʒ����
			    var newProductname=newRowObj.insertCell(newRowObj.cells.length);
			    newProductname.setAttribute( "className", "TD_LIST"); 
			    //��������
			    var newPrintdate=newRowObj.insertCell(newRowObj.cells.length);
			    newPrintdate.setAttribute( "className", "TD_LIST");
			    //��λ
			    var newPunit=newRowObj.insertCell(newRowObj.cells.length);
			    newPunit.setAttribute( "className", "TD_LIST");
			    //�ƻ�����
			    var newPnum=newRowObj.insertCell(newRowObj.cells.length);
			    newPnum.setAttribute( "className", "TD_LIST");
			    //��������
			    var newLotId=newRowObj.insertCell(newRowObj.cells.length);
			    newLotId.setAttribute( "className", "TD_LIST");
			    //��������
			    var newLotInfo=newRowObj.insertCell(newRowObj.cells.length);
			    newLotInfo.setAttribute( "className", "TD_LIST");
				 //��Ʒ����
			    var productStatus=newRowObj.insertCell(newRowObj.cells.length);
			    productStatus.setAttribute( "className", "TD_LIST");
			    	 
			    	             //��Ʒid        ��Ʒ��           ��λ     
			    var checkvaleid = cs[0] + "," + cs[1] + "," + cs[6];	 
			    	 
			   	 
			    newCheck.innerHTML= "<div align='center'><input type='checkbox' class='input_checkbox' name='check_id' value='"+checkvaleid+"'></div>";
			    newProductCode.innerHTML  = "<div align='center'>"+cs[7]+"</div>";
			    newProductname.innerHTML  = "<div align='center'>"+cs[1]+"</div>"; 
			    newPrintdate.innerHTML  = "<div align='center'><input name='invoicedate' type='text' size='13' value='<%=strTime%>' onfocus='calendar()'  class='Wdate' style='height:18px;width:100px;'/></div>";
			    newPunit.innerHTML = "<div align='center'>"+cs[6]+"</div>";
			    newPnum.innerHTML  = "<div align='center'><input type='text'></div>";
				newLotId.innerHTML    = "<div align='center'><select name='lotinfo' id='lotinfo"+tableObj.rows.length+"'  style='width:110px;'><option value=''>--��ѡ��--</option></select></div>";
				newLotInfo.innerHTML      = "<div align='center'><input type='text'></div>";
				productStatus.innerHTML    = "<div align='center'><select name='productStaatus' id='productStaatus"+tableObj.rows.length+"'  style='width:110px;' disabled><option value=''></option></select></div>";
				list.selectObject111('', 'lotinfo'+tableObj.rows.length, 'phmc');
				list.selectObject111('1', 'productStaatus'+tableObj.rows.length, 'wplb');
        	}
        }
	}
	function IsNum(num)
	{ 
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
	function add()
	{
		//��ȡ���뵥�����Ϣ
		var result = "";
		var i=-1;
		var j=-1;
		var rows = list.document.getElementById("tb").rows;
		var lineValue;
		var cellValue;
		//��ѭ��,���ڶ���һ�У����Լ�1	
		for (i = 1; i < rows.length-1; i++)
		{
			        lineValue = "";
			        cellValue = rows.item(i).cells.item(0).getElementsByTagName('INPUT')[0].value;
			        
					//��������
					printdate = rows.item(i).cells.item(3).getElementsByTagName('input')[0].value;
					if(printdate == null || printdate.length <1)
					{
						alert("�ڡ�"+i+"�����������ڲ���Ϊ�գ�");
						return false;
					}
					else if(printdate<1)
					{
						alert("�ڡ�"+i+"�����������ڲ���Ϊ�գ�");
						return false;
					}
					//�ƻ�����
					plannum = rows.item(i).cells.item(5).getElementsByTagName('input')[0].value;
					if(plannum == null || plannum.length <1 || !IsFloat(plannum))
					{
						alert("�ڡ�"+i+"���мƻ���������Ϊ�ջ�ֻ��Ϊ���֣�");
						return false;
					}
					else if(plannum<=0)
					{
						alert("�ڡ�"+i+"���мƻ�������Ҫ����0��");
						return false;
					}
					//��������
					lotid = rows.item(i).cells.item(6).getElementsByTagName('select')[0].value;
					if(lotid == null || lotid.length <1)
					{
						alert("�ڡ�"+i+"�����������Ͳ���Ϊ�գ�");
						return false;
					}
					//��������
					lotinfo = list.tb.rows.item(i).cells.item(7).getElementsByTagName('input')[0].value;
					if(lotinfo == null || lotinfo.length <1)
					{
						alert("�ڡ�"+i+"���о������Ų���Ϊ�գ�");
						return false;
					}
					DWREngine.setAsync(false);
					judgmentTool.isLotInfoLength(lotid,lotinfo,Show1);
					DWREngine.setAsync(true);
					var msg1;
					
					function Show1(value){
						msg1 = value;
					}
					if(msg1 != "Y"){
						alert(msg1);
						return false;
					}
					
					//��Ʒ״̬
					producStatus = rows.item(i).cells.item(8).getElementsByTagName('select')[0].value;
					if(producStatus == null || producStatus.length <1)
					{
						alert("�ڡ�"+i+"������Ʒ״̬����Ϊ�գ�");
						return false;
					}
					//��Ʒid   ��Ʒ��     ��λ     ��������    �ƻ�����   ��������  ������Ϣ
			        lineValue = cellValue+ "," + printdate+ ","+ plannum+ ","+ lotid+ ","+ lotinfo+ ","+producStatus+ ",";
			        
			        result = result + lineValue+ ",|";
			       
		}
		var warehouseid = document.getElementById("warehouseid").value; //�ֿ�
		var invoicetype = document.getElementById("invoicetype").value; //�������
		var invoicedate = document.getElementById("invoicedate").value; //��������
		var department = document.getElementById("department").value;   //��������
		var remark = document.getElementById("remark").value; //��ע
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("���ֿ⡿����Ϊ�գ�");
			return;
		}
		if(invoicetype == null || invoicetype.length < 1){
			alert("��������͡�����Ϊ�գ�");
			return;
		}
		if(invoicedate == null || invoicedate.length < 1){
			alert("���������ڡ�����Ϊ�գ�");
			return;
		}
		if(department == null || department.length < 1){
			alert("���������䡿����Ϊ�գ�");
			return;
		}
		var url = "&warehouseid="+warehouseid+
				   "&invoicetype="+invoicetype+
				   "&invoicedate="+invoicedate+
				   "&department="+department+
				   "&remark="+remark+
				   "&detail="+result;
		if(result.length > 0)
		{
			list.location.href="<%=request.getContextPath()%>/BMSService?actionCode=InBoundRequestAction&method=ricoExecCreate" + url;
		}
		else
		{
			alert("��ѡ�����!");
		}
	}
 	function OnLoad()
	{
		selectObject('', 'warehouseid', '1');
		selectType('9', 'invoicetype', 'rkdj');
		selectObject('001', 'department', 'workshop');
	}
</script>

</head>

<body onload="javascript:OnLoad();">
	<div class="con_bk">
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<div class="wz">
						<div id="dqwz" class="f_l">
							��ǰλ�ã�<span>������</span> &gt;&gt;<span>����������</span> &gt;&gt; <span>�½����뵥</span>
						</div>
						<div class="f_r" id="caozuo">
							<ul>
								<%if(inbound_scnewsqdsave){%><li class="tubiao1"><a href="#" onclick="add();">����</a></li>
								<%}%>
								<%if(inbound_scnewsqdproduct){%><li class="tubiao4" style="width:90px"><a href="#"
									onclick="Select();">ѡ���Ʒ</a></li>
								<%}%>
							</ul>
						</div>
					</div>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="5"></td>
						</tr>
					</table>
					<table id="querycondition" width="99%" border="0" align="center" cellpadding="0"
						cellspacing="0" class="table1">
						<tr>
							<td width="80" class="yx1"><div align="right">�ջ��ֿ⣺</div></td>
							<td width="150" class="yx"><div align="left">
									<select id="warehouseid" style="width:100px;">
										<option value="">--��ѡ��--</option>
									</select>
								</div></td>
							<td width="80" class="yx1" align="right"><div align="right">������ͣ�</div></td>
							<td width="150" class="yx"><select id="invoicetype" style="width:100px;" disabled>
									<option value="">--��ѡ��--</option>
							</select></td>
							<td class="yx1" width="60"><div align="right">�Ƶ����ڣ�</div></td>
							<td class="xx1"><div align="left">
									<input name="invoicedate" type="text" size="13" value="<%=strTime%>" onfocus="calendar();"
										class="Wdate" style="height:18px;width:100px;" /></td>
						</tr>
						<tr>
							<td width="80" class="y1"><div align="right">�������䣺</div></td>
							<td width="150" class="x"><div align="left">
									<select id="department" style="width:100px;">
										<option value="">--��ѡ��--</option>
									</select></td>
							<td width="80" class="y1"><div align="right">��ע��</div></td>
							<td width="200" class="x"><input type="text" name="remark"
								style="height:18px;width:100px;" /></td>
							<td></td>
							<td></td>
						</tr>
					</table>

					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="5"></td>
						</tr>
					</table>

				</td>
			</tr>
			<tr>
				<td height="100%">
					<!-- ***** ������ҵ��Ϣ����ҵ��ϸ*****-->
					<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
<!-- 						<tr> -->
<!-- 							<td height="10" class="title">��Ʒ��Ϣ</td> -->
<!-- 						</tr> -->
						<tr>
							<td height="180"><iframe id="list" name="list" width="100%" height="100%"
									frameborder="0" scrolling="yes"
									src="<%=request.getContextPath()%>/standard/jsp/inbound/scnew/inbound_scnewsqd_list.jsp"></iframe>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<!-- ҳ����ز㣨start�� -->
	<div id="loader_container"
		style="position: absolute; display: none; z-index: 2; filter:alpha(opacity=75);">
		<div id="loader">
			<div align="center">ҳ�����ڼ��� ...</div>
			<div id="loader_bg">
				<div id="progress"></div>
			</div>
		</div>
	</div>
	<!-- ҳ����ز㣨end�� -->

</body>
</html>
