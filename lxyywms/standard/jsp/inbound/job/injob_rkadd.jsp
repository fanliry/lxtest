<%@page import="java.util.List"%>
<%@page import="com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail"%>
<%@page import="com.wms3.bms.standard.entity.job.lxyy.InoutJob"%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%
	//�����ظ��ύ�ı�־������beginΪ�״μ��أ�finishΪ���ύ
	session.setAttribute("submitFlag","begin");
	String strWarehouseID = (String)request.getParameter("whid");
	
	if (strWarehouseID == null || strWarehouseID.equals("null")) {
		strWarehouseID = (String) session.getAttribute("warehouseid");
	}
	
	String strTime =  StrTools.getCurrDateTime(8); 
	
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
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript">

	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = "";
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
	
    //��������Ƿ�Ϊ������
	function IsNum(ch){
		var re =  /^[0-9]*$/;
		return re.test(ch);
	}
    
	//**********************************************************
	//��������
	function saveData()
	{
		var warehouseidzz = document.getElementById("warehouseid").value;
		var whAreaId = document.getElementById("whAreaId").value; 		//����
		var Virtualwhid = document.getElementById("Virtualwhid").value; //�߼�����
		var productstatus = document.getElementById("productstatus").value; //��Ʒ״̬
		var productId = document.getElementById("productId").value; 	//Ʒ��
		var productName = document.getElementById("productName").value; //Ʒ��
		var punit = document.getElementById("punit").value; 			//��λ
		var productCode = document.getElementById("productCode").value; //��Ʒ����
		var intype = document.getElementById("intype").value;			//�������
		var lotinfo = document.getElementById("lotinfo").value;			//�������
		var jobnum = document.getElementById("jobnum").value;			//����
		var traycode = document.getElementById("traycode").value; 		//��������
		var printdate = document.getElementById("printdate").value; 	//��������
		var supplier = document.getElementById("customer_id").value; 	//��Ӧ��
		var platoon = document.getElementById("platoon").value;			//��
		var column = document.getElementById("column").value;			//��
		var floor = document.getElementById("floor").value;				//��
		
		if(warehouseidzz == null || warehouseidzz.length < 1){
			alert("���뷵�����µ�¼����");
			return;
		}
		//if(productstatus == null || productstatus.length < 1){
		//	alert("����Ʒ״̬������Ϊ�գ�");
		//	return;
		//}
		if(Virtualwhid == null || Virtualwhid.length < 1){
			alert("���߼�����������Ϊ�գ�");
			return;
		}
		if(lotinfo == null || lotinfo.length < 1){
			alert("��������š�����Ϊ�գ�");
			return;
		}
		if(intype == null || intype.length < 1){
			alert("��������͡�����Ϊ�գ�");
			return;
		}else if(intype == "hl"){
			alert("������ҵ�����ڴ˲������������أ�");
			return;
		}
		/* if(supplier == null || supplier.length < 1){
			alert("����Ӧ�̡�����Ϊ�գ�");
			return;
		} */
		//DWREngine.setAsync(false);
		//judgmentTool.isLotInfoLength(lotid,lotinfo,Show1);
		//DWREngine.setAsync(true);
		//var msg1;
		
		//function Show1(value){
		//	msg1 = value;
		//}
		//if(msg1 != "Y"){
		//	alert(msg1);
		//	return false;
		//}
		if(printdate == null || printdate.length < 1){
			alert("���������ڡ�����Ϊ�գ�");
			return;
		}
		if(jobnum == null || jobnum.length <1 || !IsFloat(jobnum)){
			alert("������������Ϊ�գ�");
			return;
		}else if(jobnum<=0)
		{
			alert("������Ҫ����0��");
			return false;
		}
		
		//��֤����
		if(traycode == null ||  traycode.length < 1)
		{
			alert("���������롿����Ϊ�գ�");
			return;
		}else{
		//	var msg;
		//	DWREngine.setAsync(false);
		//	judgmentTool.isTrayCodeUse(traycode, Show);
		//	DWREngine.setAsync(true);
		//	function Show(value){
		//		msg = value;
		//	}
		//	if(msg != "Y"){
		//		alert(msg);
		//		return;
		//	}
		}
		
		if(platoon != null && !IsNum(platoon)){
			alert("���š�����Ϊ�����֣�");
			return;
		}
		if(column != null && !IsNum(column)){
			alert("���С�����Ϊ�����֣�");
			return;
		}
		if(floor != null && !IsNum(floor)){
			alert("���㡿����Ϊ�����֣�");
			return;
		}
		
		//************************************************
		if(platoon!=null&&platoon.length>0&&column!=null&&column.length>0&&floor!=null&&floor.length>0){
			
			DWREngine.setAsync(false);
			judgmentTool.iswhAreaId(warehouseidzz, whAreaId, platoon, column, floor, Show2);
			DWREngine.setAsync(true);
			var msg2;
			
			function Show2(value){
				msg2 = value;
			}
			if(msg2 != "Y"){
				alert(msg2);
				return false;
			}
			
			var msg1;
			DWREngine.setAsync(false);
			judgmentTool.isCarspace(warehouseidzz,platoon,column, floor,Show1);
			DWREngine.setAsync(true);
			function Show1(value){
				msg1 = value;
			}
			if(msg1 != "Y"){
				alert(msg1);
				return;
			}else{ 
				
				condition = "&productstatus=" + productstatus + "&lotinfo=" + lotinfo + "&jobnum=" + jobnum 
						  + "&warehouseid=" + warehouseidzz + "&whAreaId=" + whAreaId +"&platoon=" + platoon +"&column=" + column +"&floor=" + floor 
						  + "&traycode=" + traycode + "&printdate=" + printdate +"&supplier=" + supplier +"&Virtualwhid=" + Virtualwhid 
						  + "&intype=" + intype + "&productCode=" + productCode + "&productId=" + productId + "&productName=" + productName 
						  + "&punit=" + punit ;

			    if(confirm("��ȷ���޸���"))
				{
			    	window.returnValue = "inBoundJobAction&method=ricoExecRKAdd"+ condition;
			    	window.close();
				}
			}
		}
		
	}
	
	//��ȡ��Ʒ��Ϣ
	function GetInfo(){
	   var productCode = document.getElementById("productCode").value;
	   getTool.getProductInfo(productCode,Show);
	   function Show(data){
	      if(data!=null && data.length > 0){
	          var aem = data.split("|");
	          if(aem!=null){
		         document.getElementById("productId").value=aem[0];
		         document.getElementById("productName").value=aem[1];
		         document.getElementById("punit").value=aem[2];
		         
		         document.getElementById("productName").disabled = true;
		         document.getElementById("punit").disabled = true;
	          }
	          
	      }else{
	             document.getElementById("productId").value="";
		         document.getElementById("productName").value="";
		         document.getElementById("punit").value="";
		         
		         document.getElementById("productName").disabled = true;
		         document.getElementById("punit").disabled = true;
		         alert("û���ҵ���ز�Ʒ��Ϣ");
	      }
	      
	   }
	   
	}
	
	//��òֿ���Ϣ
	function GetWhInfo(){
		   var Virtualwhid = document.getElementById("Virtualwhid").value;
		   getTool.getWhInfo(Virtualwhid,pShow);
		   function pShow(data){
		      if(data!=null && data.length > 0){
		          var aem = data.split("|");
		          if(aem!=null){
			         document.getElementById("warehouseid").value=aem[0];
			         document.getElementById("whAreaId").value=aem[1];
		          }
		          
		      }else{
		             document.getElementById("warehouseid").value="";
			         document.getElementById("whAreaId").value="";
			         alert("û���ҵ���ؿ�����Ϣ");
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
		selectAreaList("", "Virtualwhid", "<%=strWarehouseID %>", "3");
		selectType('9', 'intype', 'rklx');			//�������
		selectType('', 'productstatus', 'wpzt');	//��Ʒ״̬
	}

</script>
</head>

<body onload="onLoad();">
	<table id="tbb" border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_MGR_TOP">
		<tr>
			<td height="25" bgcolor="#d9e8fc" align="center" colspan="2">
				<table width="100%">
    				<tr><td>

					  <!-- ======== ��ǰλ�� ======== -->
					  <div class="wz">
						<div id="dqwz" class="f_l">��ǰλ�ã�<span>������ &gt;&gt; ��ҵ���� &gt;&gt; �������</span></div>
					  </div>
						
					</td></tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">�߼�������</td>
     	  	<td class="TD_ADD">
     	  	<input type="hidden" name="warehouseid" disabled>
     	  	<input type="hidden" name="whAreaId" disabled>
     	  	<select id="Virtualwhid" style="width:163px;" onchange="GetWhInfo();" ><option value=""></option></select>
     	  	</td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">������ͣ�</td>
	      	<td class="TD_ADD"><select id="intype" style="width:163px;" disabled><option value=""></option></select></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">�������룺</td>
			<td class="TD_ADD">
			<input type="text" name="traycode" class="rf_input_long" maxlength="10" > <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">��Ʒ���룺</td>
			<td class="TD_ADD"><input type="text" name="productCode" class="rf_input_long" onchange="GetInfo();" > <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">Ʒ����</td>
			<td class="TD_ADD">
			<input type="hidden" name="productId">
			<input type="text" name="productName" class="rf_input_long" disabled > <font color="red">*</font> <input type="hidden" name="productid" /></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">��λ��</td>
			<td class="TD_ADD"><input type="text" name="punit" class="rf_input_long"  disabled > <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">������ţ�</td>
			<td class="TD_ADD"><input type="text" name="lotinfo" class="rf_input_long" maxlength="10" > <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">������</td>
			<td class="TD_ADD"><input type="text" name="jobnum" class="rf_input_long" maxlength="10" > <font color="red">*</font></td>
		</tr>
		<tr>
		<td class="TD_ADD" align="right">��Ʒ״̬��</td>
          <td class="TD_ADD">
              <select id="productstatus" style="width:163px;" >
                <option value="">--��ѡ��--</option>
              </select>
           </td>
           </tr>
		<tr>
     	  <td class="TD_ADD" align="right">�������ڣ�</td>
	      <td class="TD_ADD">
	      	<input id="printdate" type="text" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:162px;"> <font color="red">*</font>
	      </td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">��Ӧ�̣�</td>
			<td class="TD_ADD">
			<input type="hidden" name="customer_id" ><input type="text" name="customer_name" readonly style="width:137px;" >
       		<input type="button" class="button_select" value="��" onClick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer_rf.jsp','330','500');">
       		 </td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">��λ��</td>
	   	  	<td class="TD_ADD">
	   	    <input type="text" id="platoon" maxlength="2" style="width:38px;" >��
	   	    <input type="text" id="column" maxlength="2" style="width:38px;" >��
	   	    <input type="text" id="floor" maxlength="2" style="width:38px;" >��
	   	     <font color="red">*</font>
	   	  </td>
		</tr>
		<tr>
			<td class="TD_ADD" align="center" colspan="2"><input type="button" name="add" class="BUTTON_STYLE1" value="ȷ��" onClick="saveData();"></td>
		</tr>
	</table>
	<div id="over" style="position: absolute; display: none; top: 0px; left: 0px; width: 100%; height: 100%; background-color: #efefef; z-index: 1; filter: alpha(opacity =     70);">
		<iframe style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; z-index: -1;"></iframe>
	</div>
	<div id="select" style="position: absolute; display: none; top: 0px; left: 0px; width: 100%; z-index: 2; background: #ffffff;"></div>
</body>
</html>
