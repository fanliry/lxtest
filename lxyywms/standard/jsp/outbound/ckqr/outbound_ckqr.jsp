<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/standard/style/load.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
		// �������
	function sendCheck() 
	{ 
		var warehouse_out_id = document.getElementById("outinvoiceid").value;
		if (warehouse_out_id == null || warehouse_out_id.length < 1) {
			alert("��ѡ��һ�ŵ��ݣ�");
			return;
		}

		DWREngine.setAsync(false);
		judgmentTool.sendCheck(warehouse_out_id, ShowCheck);
		DWREngine.setAsync(true);
		function ShowCheck(data) { // ��ʾ�����
			if (data == "Y") {
				alert("���ݡ�" + warehouse_out_id + "��������������ɣ����Է���ȷ�ϣ�");
			} else {
				alert("���ݡ�" + warehouse_out_id + "�����������뷢���������ȣ����У�\r\r" + data);
			}
		}
	}
	// ����ȷ��
	function sendOk() 
	{ 
		var warehouse_out_id = document.getElementById("outinvoiceid").value;
		if (warehouse_out_id == null || warehouse_out_id.length < 1) {
			alert("��ѡѡ��һ�ŵ��ݣ�");
			return;
		}

		var check_msg = "";
		DWREngine.setAsync(false);
		judgmentTool.sendCheck(warehouse_out_id, ShowCheck);
		DWREngine.setAsync(true);
		function ShowCheck(data) {
			check_msg = data;
		}
		if (check_msg == "Y") {
			msg = "���ݡ�" + warehouse_out_id + "��������������ɣ����Է���ȷ�ϣ�\r\r";
			msg += "��ȷ���Ե��ݡ�" + warehouse_out_id + "������ȷ�ϣ�";
		
			if(confirm(msg)) 
			{
				list.location.href = strUrl + "sendAction&method=ricoExecOkFHQR&out_id=" + warehouse_out_id;
			}
			
			
		} else {
			
			   msg = "���ݡ�" + warehouse_out_id + "�����������뷢���������ȣ����У�\r\r" + check_msg + "\r\r�뿪�����ݲ����Ľ����д���\r\r";
			
	
			//	var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/outbound/outbound_fhqr_adjust.jsp?outId="+warehouse_out_id, 
				//	warehouse_out_id, "dialogWidth=800px; dialogHeight=600px; scroll=no");
				//if(result != null && result.length > 0) 
				//{	
				//}
				msg += "����ʣ��������ֱ�ӷ����ݴ�����\r\r";
				msg += "��ȷ���Ե��ݡ�" + warehouse_out_id + "�����з���ȷ�ϣ�";
			
				if(confirm(msg)) 
				{
					list.location.href = strUrl + "sendAction&method=ricoExecOkFHQR&out_id=" + warehouse_out_id;
				}
		}

		
	}
	
	//�Ե�����ϸ���з���ȷ��
	function sendOneOk() 
	{ 
		var warehouse_out_id = document.getElementById("outinvoiceid").value;
		if (warehouse_out_id == null || warehouse_out_id.length < 1) {
			alert("��ѡѡ��һ�ŵ��ݣ�");
			return;
		}
		
		var ids = "";//������ϸID
		var check_ids = list.document.getElementsByName("check_id");	
		for(var i=0; i<check_ids.length; i++)
		{
			if(check_ids[i].checked == true){
				ids = check_ids[i].value ;
				break;
			}
		}
		if(ids == ""){
			alert("��ѡ��һ����¼");
			return;
		}
		
		var check_msg = "";
		DWREngine.setAsync(false);
		judgmentTool.sendOneCheck(warehouse_out_id, ids, ShowCheck);
		DWREngine.setAsync(true);
		
		function ShowCheck(data) {	
			check_msg = data;
		}
		if (check_msg == "Y") {
			msg = "���ݡ�" + warehouse_out_id + "��������������ϸ�����Է���ȷ�ϣ�\r\r";
			msg += "��ȷ���Ե��ݡ�" + warehouse_out_id + "��������������ϸ����ȷ�ϣ�";
		
			if(confirm(msg)) 
			{
				list.location.href = strUrl + "sendAction&method=ricoExecOneOkFHQR&out_id=" + warehouse_out_id + "&out_detail_id=" + ids;
		
			}
			
			
		} else {
			    
			    msg = "���ݡ�" + warehouse_out_id + "��������������ϸ���������뷢���������ȣ����У�\r\r" + check_msg + "\r\r�뿪�����ݲ����Ľ����д���\r\r";
			
				//var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/outbound/outbound_fhqr_adjust.jsp?outid="+warehouse_out_id + "&outdetailid=" + ids, 
				//	warehouse_out_id, "dialogWidth=800px; dialogHeight=600px; scroll=no");
                
				//if(result != null && result.length > 0) 
				//{}
				msg += "����ʣ��������ֱ�ӷ����ݴ�����\r\r";
				msg += "��ȷ���Ե��ݡ�" + warehouse_out_id + "����������ϸ���з���ȷ�ϣ�";
				if(confirm(msg)) 
				{
					list.location.href = strUrl + "sendAction&method=ricoExecOneOkFHQR&out_id=" + warehouse_out_id + "&out_detail_id=" + ids;					
				}
			}
	}





	
	function adjust()
	{
		//����ID
		var outinvoiceid = document.getElementById("outinvoiceid").value;
		if(outinvoiceid == null || outinvoiceid.length < 1){
			alert("�����ⵥ������Ϊ�գ�");
			
		}else{
			var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/outbound/fhqr/outbound_fhqr_adjust.jsp?outid="+outinvoiceid, 
						outinvoiceid, "dialogWidth=800px; dialogHeight=600px; scroll=no");
			if(result != null && result.length > 0) 
			{
				
				
			}
		}
	}

	//��ʾ������ϸ��¼
 	function viewdata()
 	{
 		//����ID
		var outinvoiceid = document.getElementById("outinvoiceid").value;
		if(outinvoiceid == null || outinvoiceid.length < 1){
			alert("�����ݡ�����Ϊ�գ�");
			
		}else{
 			
 			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&flag=11";
 			Loading();
 			list.location.href = strUrl + "&invoiceid=" + outinvoiceid;	
		}
	
 	}
 	//��ʾ���ⵥ
 	function viewinvoice()
 	{
 		var warehouseid = document.getElementById("warehouseid").value;
 		
 		if(warehouseid == null || warehouseid.length < 1){
			alert("���ֿ⡿����Ϊ�գ�");
			
		}else{
 			selectObject(warehouseid, 'outinvoiceid', '13');
		}
 		
 	}
	
	function OnLoad(){	
	
		var back_msg = "<%=request.getAttribute("back_msg") == null ? "" : (String)request.getAttribute("back_msg")%>";
		if (back_msg != "") 
		{
			if(back_msg == "Y")
			{
				alert("ȷ�ϳɹ�!");
			}else
			{
				alert(back_msg);
			}
		}
		DWREngine.setAsync(false);
		selectObject('', 'warehouseid', '1');
		DWREngine.setAsync(true);
		viewinvoice();//��ʾ���ⵥ
	}
-->
</script>
</head>

<body onload="OnLoad();">

<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== ��ǰλ�� ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������� &gt;&gt; ����ȷ��</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
          	<li class="tubiao1" style="width:90px;"><a href="#" onclick="sendOk();">����ȷ��</a></li>
		    <li class="tubiao2" style="width:110px;" ><a href="#" onclick="sendOneOk();">����ϸ����ȷ��</a></li>
		    <li class="tubiao2" style="width:90px;" ><a href="#" onclick="adjust();">��������</a></li>
		    <li class="tubiao2" style="width:90px;" ><a href="#" onclick="sendCheck();">�������</a></li>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== ��ѯ���� ======== -->
      <table width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center">  
             <tr>
             	<td width="60"  class="y1"><div align="right">��&nbsp;&nbsp;&nbsp;&nbsp;�⣺</div></td>
                <td width="150" class="x"><div align="left">
                  <select name="warehouseid"  style="width:100px;" class="input_readonly" onchange="viewinvoice();">
                    <option value="">--��ѡ��--</option>
                  </select>
                </td>
             	<td width="60"  class="y1"><div align="right">���ⵥ�ţ�</div></td>
                <td width="150" class="x"><div align="left">
                  <select name="outinvoiceid"  style="width:120px;" onchange="viewdata();">
                    <option value="">----��ѡ��----</option>
                  </select>
                </td>
				<td width="60" class="y1"><div align="right">��&nbsp;��&nbsp;�ţ�</div></td>
                <td class="x" width="150">
                	<input type="text" name="vehicleno" class="input_readonly"  readonly   style="height:18px;width:100px;"/>
                </td>
                <td width="60" class="y1"><div align="right">��&nbsp;&nbsp;&nbsp;&nbsp;λ��</div></td>
                <td >
                	<input type="text" name="vehiclepos" class="input_readonly"  readonly   style="height:18px;width:100px;"/>
                </td>       
              </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
		 <td valign="top" height="100%">
		 
		 
		  <table width="99%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
		   <tr>
		     <td>
		       <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/outbound/fhqr/outbound_fhqr_list.jsp" 
		  			width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
			 </td>
		   </tr>
		 </table>

	  </td>
    </tr>
  </table>  	

</div>

<!-- ҳ����ز㣨start�� -->
<div id="loader_container" style="position: absolute; display: none; z-index: 2; filter:alpha(opacity=75);">
	<div id="loader"><div align="center">ҳ�����ڼ��� ...</div><div id="loader_bg"><div id="progress"></div></div></div>
</div>
<!-- ҳ����ز㣨end�� -->  

</body>
</html>
