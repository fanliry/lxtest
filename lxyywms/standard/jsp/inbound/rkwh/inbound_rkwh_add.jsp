<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%
	String strTime =  StrTools.getCurrDateTime(8);
	String warehouseid = request.getParameter("warehouseid");
	String whAreaId = request.getParameter("whAreaId");
%>
<html>
<head>
<title>���������ҵ��Ϣ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript">
<!--
	//������ҵ
	function OnOk(){
	
		var warehouseid = document.getElementById("warehouseid").value;	//�ֿ�
		var whAreaId = document.getElementById("whAreaId").value;		//����
		var alleyId = document.getElementById("alleyId").value;			//���
		var cargospaceid = document.getElementById("cargospace_id").value;	//��λid
		var intype = document.getElementById("intype").value;			//�������
		var traycode = document.getElementById("traycode").value;		//��������
		var indate = document.getElementById("indate").value;			//��ҵ����
		//var shift_id = document.getElementById("shift_id").value;		//���
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("��ѡ�񡾲ֿ⡿!");
			return;
		}
		if(whAreaId == null || whAreaId.length < 1){
			alert("��ѡ�񡾿�����!");
			return;
		}
		if(cargospaceid == null || cargospaceid.length <1)
		{
			alert("��ѡ�񡾻�λ��!");
			return;
		}
		if(intype == null || intype.length <1)
		{
			alert("��������͡�����Ϊ��!");
			return;
		}
		if(traycode == null || traycode.length <1)
		{
			alert("���������롿����Ϊ��!");
			return;
		}
		
		var tb = list.document.getElementById("tb");
		if(tb.rows.length == 2){
			alert("�������ҵ��ϸ��Ϣ!");
			return;
		}
		
		//"&shift_id=" + shift_id
		var jobinfo = "&warehouseid=" + warehouseid + "&whAreaId=" + whAreaId + "&alleyId=" + alleyId
			 + "&cargospaceid=" + cargospaceid + "&indate=" + indate + "&intype=" + intype + "&traycode=" + traycode;
		
		var details = list.document.getElementsByName("key");
		var jobdetails = "";
		for(var i=0; i<details.length; i++){
			jobdetails += details[i].value + ","
		}
		var msg = "<input type=hidden name='jobdetails' value='"+jobdetails+"'>"
		var array = new Array();	
		array[0] = jobinfo;				//��ҵ��Ϣ
		array[1] = msg;					//��ҵ��ϸ��Ϣ
        window.returnValue = array;
		window.close();
		
	}
	
	//������ϸ
	function addDetail(){
	
		var productid = document.getElementById("productid").value;			//Ʒ��id
		var productname = document.getElementById("product_name").value;	//Ʒ��
		var customerid = document.getElementById("customer_id").value;		//����id
		var customername = document.getElementById("customer_name").value;	//Ʒ��
		var packid = document.getElementById("packid").value;				//��װid
		var packname = document.getElementById("packname").value;			//��װ
		var objunit = document.getElementById("punit");						//��λ
		var punit = objunit.value;											//��λid
		var punitname = objunit.options[objunit.options.selectedIndex].text;//��λ
		var lotid = document.getElementById("lotid").value;  				// ����id
		var lotatt1 = document.getElementById("lotatt1").value;  			// ��������1
     	var lotatt2 = document.getElementById("lotatt2").value;  			// ��������2
     	var lotatt3 = document.getElementById("lotatt3").value;  			// ��������3
     	var lotatt4 = document.getElementById("lotatt4").value;  			// ��������4
     	var lotatt5 = document.getElementById("lotatt5").value;  			// ��������5
     	var lotatt6 = document.getElementById("lotatt6").value;  			// ��������6
     	var lotatt7 = document.getElementById("lotatt7").value;  			// ��������7
     	var lotatt8 = document.getElementById("lotatt8").value;  			// ��������8
     	var lotatt9 = document.getElementById("lotatt9").value;  			// ��������9
     	var lotatt10 = document.getElementById("lotatt10").value;  			// ��������10
     	var lotatt11 = document.getElementById("lotatt11").value;  			// ��������11
     	var lotatt12 = document.getElementById("lotatt12").value;  			// ��������12
     	var jobnum = document.getElementById("jobnum").value;				// ����
		var volume = document.getElementById("volume").value;         		// ���
 		var weight = document.getElementById("weight").value;         		// ����
		var netweight = document.getElementById("netweight").value;			// ����
		
		if(productid == null || productid.length < 1){
			alert("��ѡ��Ʒ����!");
			return;
		}
		
		if(jobnum != null && jobnum.length > 0 && jobnum != 0){
			if(!numChar(jobnum)){
				alert("��������ֻ��Ϊ��������0��");
				return;
			}
		}
		
		if(volume != null && volume.length > 0 && volume != 0){
			if(!isDig(volume)){
				alert("�������ֻ��Ϊ����������0��");
				return;
			}
		}
		
		if(weight != null && weight.length > 0 && weight != 0){
			if(!isDig(weight)){
				alert("��������ֻ��Ϊ����������0��");
				return;
			}
		}
		
		if(netweight != null && netweight.length > 0 && netweight != 0){
			if(!isDig(netweight)){
				alert("�����ء�ֻ��Ϊ����������0��");
				return;
			}
		}
		
		//��������ֵ��֤
		if(checkLotatt()==false){
			return;
		}
		
		var tb = list.document.getElementById("tb");
		var key = productid + "|" + customerid + "|" + packid + "|" + punit + "|" + jobnum + "|" + volume + "|" + weight + "|" + netweight + "|" 
			   + lotatt1 + "|" + lotatt2 + "|" + lotatt3 + "|" + lotatt4 + "|" + lotatt5 + "|" + lotatt6 + "|" + lotatt7 + "|"
			   + lotatt8 + "|" + lotatt9 + "|" + lotatt10 + "|" + lotatt11 + "|" + lotatt12 + "|" + lotid;
		
		
		//����һ��
		var newRow = null;	//��
		newRow = tb.insertRow(tb.rows.length-1);
		newRow.setAttribute("onmouseover", function(){this.bgColor='#e1f3ff'});
		newRow.setAttribute("onmouseout", function(){this.bgColor=''});
   		
		//Ʒ��
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = productname;
    	
    	//����
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = customername;
    	
    	//��װ
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = packname;
    	
    	//��λ
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = punitname;
    	
    	//��������
		newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = lotatt1;
    	
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = lotatt2;
    	
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = lotatt3;
    	
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = lotatt4;
    	
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = lotatt5;
    	
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = lotatt6;
    	
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = lotatt7;
    	
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = lotatt8;
    	
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = lotatt9;
    	
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = lotatt10;
    	
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = lotatt11;
    	
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = lotatt12;
    	
    	// ����
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = jobnum;

		// ���
		newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = volume;
    	
    	// ����
 		newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = weight;
    	
		// ����
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = netweight;
    	
    	//ɾ��
        newCell = newRow.insertCell(newRow.cells.length);
		newCell.setAttribute("className", "TD_LIST2");
		newCell.setAttribute( "align", "center");
		newCell.innerHTML = "<input type='button' id='details' class='BUTTON_STYLE1' value='ɾ��' onclick='this.parentElement.parentElement.removeNode(true);'>"
			+ "<input type='hidden' name='key' value='" + key +"'>";
	}
	
	function OnLoad(){
	
		//ͬ��
		DWREngine.setAsync(false);
		selectObject("<%=warehouseid%>", "warehouseid", "1");
		DWREngine.setAsync(true);
		
		var warehouseid = document.getElementById("warehouseid").value;
		DWREngine.setAsync(false);
		selectAreaList("<%=whAreaId%>", "whAreaId", warehouseid, "1");
		DWREngine.setAsync(true);
		
		var whAreaId = document.getElementById("whAreaId").value;
		selectAlleyList("", "alleyId", whAreaId);
		
		selectType('', 'intype', 'rklx');	//�������
		selectType('', 'punit', 'punit');	//��λ
	}
	
	//���ݲֿ��ÿ������б�
	function getWhAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		selectAlleyList("", "alleyId", "");
	}
	
	//���ݿ������������б�
	function getAlleyList(){
	
		var whAreaId = document.getElementById("whAreaId").value;
		selectAlleyList("", "alleyId", whAreaId);
	}

-->
</script>
</head>

<body onload="OnLoad();">

  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã������� -&gt; ���ά�� -&gt; ������ҵ��Ϣ</div></td>
    </tr>
  </table>
  
  <!---------- ��ҵ��Ϣ ------------>
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right"><span class="red">*</span>�ֿ⣺</td>
      <td class="yx"><select id="warehouseid" onChange="getWhAreaList()" style="width:117px;"><option value=""></option></select></td>
      <td width="100px" class="yx1" align="right"><span class="red">*</span>������</td>
      <td class="yx"><select id="whAreaId" onChange="getAlleyList()" style="width:117px;"><option value=""></option></select></td>
      <td width="100px" class="yx1" align="right">�����</td>
	  <td class="yx"><select id="alleyId" style="width:117px;"><option value="">--��ѡ��--</option></select></td>
	  <td width="100px" class="yx1" align="right"><span class="red">*</span>��λ��</td>
	  <td class="xx1">
		<input type="hidden" id="cargospace_id"><input type="text" id="cargospace_name" size="14" readonly>
       	<input onClick="openCargospace('<%=request.getContextPath()%>/standard/jsp/common/common_cargospace.jsp?warehouseid=<%=warehouseid%>&whAreaId='+document.getElementById('whAreaId').value,'850','550');" 
       		type="button" value="��" class="button_select">
      </td>
   </tr>    
   <tr>
     <td class="y1" align="right">��ҵ���ڣ�</td>
	 <td class="x"><input id="indate" type="text" size="15" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:117px;"></td>
     <td class="y1" width="100px" align="right"><span class="red">*</span>������ͣ�</td>
	 <td class="x"><select id="intype" style="width:117px;"><option value=""></option></select></td>
     <td class="y1" align="right"><span class="red">*</span>�������룺</td>
	 <td colspan="3"><input id="traycode" type="text" size="15"></td>
   </tr> 
   </table>
   
   <!---------- ��ҵ��ϸ��Ϣ ------------>
   <form name="myForm" method="post" action="">
   <table width="98%" border="0" align="center" cellpadding="0" cellspacing="5">
   <tr><td align="right">
        <input type="button" onclick="addDetail()" value="&nbsp;&nbsp;&nbsp;������ϸ" class="button_add">
	    <input type="reset" id="resetDetailBtn" value="&nbsp;&nbsp;&nbsp;������ϸ" class="button_reset">&nbsp;&nbsp;&nbsp;
      	<input type="button" onclick="OnOk()" name="add" value="&nbsp;&nbsp;&nbsp;������ҵ" class="button_add"> 
       	<input type="button" onClick="window.close()" name="resetBtn" value="�ر�" class="BUTTON_STYLE1">
   </td></tr>
   <tr><td class="title">��ҵ��ϸ��Ϣ</td></tr>
   <tr><td>
     <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
     <tr>
       <td width="100px" class="yx1" align="right"><span class="red">*</span>Ʒ����</td>
       <td class="yx">
         <input type="hidden" name="productid"><input type="text" name="product_name" size="15" readonly class="input_readonly">
         <input onclick="openProductPacke('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520',
         	'productid','product_name', 'packid', 'packname', '1','punit', 'lotid');"  type="button" value="��" class="button_select">
       </td>
       <td width="100px" class="yx1" align="right">������</td>
       <td class="yx">
         <input id="customer_id" type="hidden"><input id="customer_name" type="text" size="15" readonly class="input_readonly">
         <input onClick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp','700','450');" 
        	type="button" value="��" class="button_select">
       </td>
       <td width="100px" class="yx1" align="right">��װ��</td>
       <td class="yx"><input type="hidden" id="packid"/><input type="text" id="packname" class="input_readonly" readonly size="15" /></td>
       <td class="yx1" align="right">��λ��</td>
	   <td class="xx1"><select id="punit" style="width:117px;" ><option>--��ѡ��--</option></select></td>
     </tr>    
     <tr>
       <td class="yx1" align="right">������</td>
	   <td class="yx"><input id="jobnum" type="text" size="15" value="0"></td>
	   <td class="yx1" align="right">�����</td>
	   <td class="yx"><input id="volume" type="text" size="15" value="0.0"></td>
       <td class="yx1" align="right">ë�أ�</td>
	   <td class="yx"><input id="weight" type="text" size="15" value="0.0"></td>
       <td class="yx1" align="right">���أ�</td>
	   <td class="xx1"><input id="netweight" type="text" size="15" value="0.0"></td>
     </tr> 
     <tr>
     </tr> 
     <tr><td height="5" colspan="6"><input type="hidden" name="lotid"/></td></tr>
     <tr>
      <td align="right" class="yx1_top"><div id="lotatt001" align="right">��������1��</div></td>
      <td class="yx_top"><div id="lotvalue001" align="left">
      	<input type="text" name="lotatt1" size="16" /></div>
      </td>
      <td align="right" class="yx1_top"><div id="lotatt002" align="right">��������2��</div></td>
      <td class="yx_top"><div id="lotvalue002" align="left">
      	<input type="text" name="lotatt2" size="16"/></div>  
       </td>
      <td align="right" class="yx1_top"><div id="lotatt003" align="right">��������3��</div></td>
      <td class="yx_top" ><div id="lotvalue003" align="left">
      	<input type="text" name="lotatt3" size="16"/></div>
      </td>
      <td align="right" class="yx1_top"><div id="lotatt004" align="right">��������4��</div></td>
      <td class="xx1_top"><div id="lotvalue004" align="left">
      	<input type="text" name="lotatt4" size="16"  /></div>
      </td>
    </tr>
    <tr>
      <td align="right" class="yx1"><div id="lotatt005" align="right">��������5��</div></td>
      <td class="yx"><div id="lotvalue005" align="left">
      	<input type="text" name="lotatt5" size="16"/></div>  
       </td>
      <td align="right" class="yx1"><div id="lotatt006" align="right">��������6��</div></td>
      <td class="yx" ><div id="lotvalue006" align="left">
      	<input type="text" name="lotatt6" size="16"/></div>
      </td>
      <td align="right" class="yx1"><div id="lotatt007" align="right">��������7��</div></td>
      <td class="yx"><div id="lotvalue007" align="left">
      	<input type="text" name="lotatt7" size="16"  /></div>
      </td>
      <td align="right" class="yx1"><div id="lotatt008" align="right">��������8��</div></td>
      <td class="xx1"><div id="lotvalue008" align="left">
      	<input type="text" name="lotatt8" size="16"/></div> 
       </td>
    </tr>
    <tr>
      <td align="right" class="y1"><div id="lotatt009" align="right">��������9��</div></td>
      <td class="x" ><div id="lotvalue009" align="left">
      	<input type="text" name="lotatt9" size="16"/></div>
      </td>
      <td align="right" class="y1"><div id="lotatt010" align="right">��������10��</div></td>
      <td class="x"><div id="lotvalue010" align="left">
      	<input type="text" name="lotatt10" size="16"  /></div>
      </td>
      <td align="right" class="y1"><div id="lotatt011" align="right">��������11��</div></td>
      <td class="x"><div id="lotvalue011" align="left">
      	<input type="text" name="lotatt11" size="16"/></div>  
       </td>
      <td align="right" class="y1"><div id="lotatt012" align="right">��������12��</div></td>
      <td><div id="lotvalue012" align="left">
      	<input type="text" name="lotatt12" size="16"/></div>
      </td>
    </tr>
   </table>
   </td></tr>
   <tr><td>

	 <!-- ======== ��ϸ�б�ʼ ======== -->
	 <table width="100%" height="270" border="0" align="center" cellpadding="0" cellspacing="0">
	   <tr>
	      <td align="right">
	      	
	      </td>
	    </tr>
	    <tr>
		  <td>
		    <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/inbound/rkwh/inbound_rkwh_add_list.jsp" 
		   		scrolling="auto" frameborder="0" width="100%" height="100%">
		    </iframe>
		  </td>
	   </tr>
	 </table>
	 <!-- ======== ��ϸ�б���� ======== -->
   </td></tr>
</table>  
</form>
   
</body>
</html>