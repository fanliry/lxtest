<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%
	//�����ظ��ύ�ı�־������beginΪ�״μ��أ�finishΪ���ύ
	session.setAttribute("submitFlag","begin");
	String strWarehouseID = (String)session.getAttribute("warehouseid");
	
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
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/getTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
<script type="text/javascript">

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

	
   	//ѡ����ⶩ��
	function Getrequestvoiceid(){
   		
		var warehouseid = "<%=strWarehouseID%>"
		if(warehouseid==null || warehouseid==""){
		    alert("�����µ�½");
		    return;
		}
			
		var returnValue = window.showModalDialog("<%=request.getContextPath()%>/rf/lxyy/inother/inbound_othermgr.jsp?whid="+warehouseid,"","dialogWidth=330px;dialogHeight=450px;");
		if(returnValue != null){
			// StrValue = poid +"|"+ podetailid +"|"+ productid +"|"+ productName +"|"+ productCode +"|"+ eunitName +"|"+ lotinfo +"|"+ lotinfo2 +"|"+ ponum;
			var Rearr=returnValue.split("|");
			if(Rearr.length==9){
				
				if("null" != Rearr[0]){
					document.getElementById("poid").value = Rearr[0]; // POid
				}
				if("null" != Rearr[1]){
					document.getElementById("podetailid").value = Rearr[1]; // PO�к�				
				}
				if("null" != Rearr[2]){
					document.getElementById("productid").value = Rearr[2]; // ��ƷID 
				}
				if("null" != Rearr[3]){
					document.getElementById("productName").value = Rearr[3]; // ��Ʒ����
				}
				if("null" != Rearr[4]){
					document.getElementById("productCode").value = Rearr[4]; // ��Ʒ����
				}
				if("null" != Rearr[5]){
					document.getElementById("punit").value = Rearr[5]; // eunitName ��λ
				}
				if("null" != Rearr[6]){
					if(document.getElementById("lotinfo")){
						document.getElementById("lotinfo").value = Rearr[6]; // lotinfo �������
					}
				}
				if("null" != Rearr[7]){
					if(document.getElementById("lotinfo2")){
						document.getElementById("lotinfo2").value = Rearr[7]; // lotinfo2 ԭ�����
					}
				}
				document.getElementById("jobnum").value = Rearr[8]; // ponum ����
			}else{
				alert(Rearr.length);
			}		

		}

	}
	
	//**********************************************************
	//��������
	function saveData()
	{
        var warehouseid = "<%=strWarehouseID %>"
	   if(warehouseid=null || warehouseid==""){
	       alert("�����µ�½");
	       return;
	   }

		var Dstr = "";
		DWREngine.setAsync(false);
		getTool.GetInoutControl(mShow);
		DWREngine.setAsync(true);
		function mShow(value){
			Dstr = value;
		}
        
		if(Dstr == "2"){
			alert("�������÷ǿ����ģʽ!");
			return;
		}
        
		var poid = document.getElementById("poid").value; //poid
		var podetailid = document.getElementById("podetailid").value; //podetailid
		
		var productCode = document.getElementById("productCode").value; //��Ʒ����
		var warehouseidzz = document.getElementById("warehouseid").value;
		var whAreaId = document.getElementById("whAreaId").value; 		//����
		var Virtualwhid = document.getElementById("Virtualwhid").value; //�߼�����
		var intype = document.getElementById("intype").value;			//�������
		var productId = document.getElementById("productId").value; 	//Ʒ��
		var productName = document.getElementById("productName").value; //Ʒ��
		var punit = document.getElementById("punit").value; 			//��λ
		var productstatus = document.getElementById("productstatus").value; //��Ʒ״̬
		var lotinfo = document.getElementById("lotinfo").value;			//�������
		var lotinfo2 = document.getElementById("lotinfo2").value;		//ԭ�����
		var jobnum = document.getElementById("jobnum").value;			//����
		var traycode = document.getElementById("traycode").value; 		//��������
		var snumber = ""; //document.getElementById("snumber").value; 		//���ͺ�
		var printdate = document.getElementById("printdate").value; 	//��������
		var supplier = ""; //document.getElementById("customer_id").value; 	//��Ӧ��
		
		if(productCode == null || productCode.length < 1){
			alert("����Ʒ���롿����Ϊ�գ�");
			return;
		}
		if(whAreaId == null || whAreaId.length < 1){
			alert("���߼�����û�����������ġ������������");
			return;
		}
		if(intype == null || intype.length < 1){
			alert("��������͡�����Ϊ�գ�");
			return;
		}else if(intype == "hl"){
			alert("������ҵ�����ڴ˲������������أ�");
			return;
		}
		if(productName == null || productName.length < 1){
			alert("��Ʒ��������Ϊ�գ�");
			return;
		}
		if(productstatus == null || productstatus.length < 1){
			alert("����Ʒ״̬������Ϊ�գ�");
			return;
		}
		if(lotinfo == null || lotinfo.length < 1){
			alert("��������š�����Ϊ�գ�");
			return;
		}
/* 		if(lotinfo2 == null || lotinfo2.length < 1){
			alert("��ԭ����š�����Ϊ�գ�");
			return;
		} */
/* 		if(printdate == null || printdate.length < 1){
			alert("���������ڡ�����Ϊ�գ�");
			return;
		} */
		if(jobnum == null || jobnum.length <1 || !IsFloat(jobnum)){
			alert("������������Ϊ�գ�");
			return;
		}else if(jobnum<=0)
		{
			alert("������Ҫ����0��");
			return false;
		}
		
/* 		//��֤���ͻ���
		if(snumber == null || snumber.length < 1){
			alert("�����ͺš�����Ϊ�գ�");
			return;
		}else{
			var msg;
			DWREngine.setAsync(false);
			judgmentTool.isSnumberUse(snumber, Show);
			DWREngine.setAsync(true);
			function Show(value){
				msg = value;
			}
			if(msg != "Y"){
				alert(msg);
				return;
			}
		} */
		
		//��֤����
		if(traycode == null ||  traycode.length < 1)
		{
			alert("���������롿����Ϊ�գ�");
			return;
		}else{
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
		
		condition = "&poid=" + poid + "&podetailid=" + podetailid + "&warehouseid=" + warehouseidzz + "&whAreaId=" + whAreaId + "&intype=" + intype 
				+ "&productId=" + productId + "&productName=" + productName + "&punit=" + punit 
				+ "&productstatus=" + productstatus + "&lotinfo=" + lotinfo + "&lotinfo2=" + lotinfo2 + "&jobnum=" + jobnum 
				+ "&snumber=" + snumber	+ "&traycode=" + traycode + "&printdate=" + printdate 
				+"&productCode=" + productCode + "&supplier=" + supplier + "&Virtualwhid=" + Virtualwhid;

	    if(confirm("��ȷ���ջ���"))
		{		
			window.location.href = strUrl + "inBoundJobAction&method=ricoExecCreateRFJob&rfmainView=rfmainView&flag=yflrk"+ condition;
		}

		
	}
	function OnCancel() {
		document.all.over.style.display = "none";
		document.all.select.style.display = "none";
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
		selectType('12', 'intype', 'rklx');			//�������
		selectType('1', 'productstatus', 'wpzt');	//��Ʒ״̬
		document.getElementById("warehouseid").value="<%=strWarehouseID %>";
	}

</script>
</head>

<body onload="onLoad();">
	<table id="tbb" border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_MGR_TOP">
		<tr>
			<td height="25" bgcolor="#d9e8fc" align="center" colspan="2">
				<table width="100%">
					<tr>
						<td width="20%"></td>
						<td width="60%" align="center" class="font_006699_bold_12">RFԭ�������</td>
						<td width="20%" align="right"><a href="<%=request.getContextPath()%>/rf/main.jsp?warehouseid=<%=strWarehouseID%>">����</a></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>  
	     <td class="TD_ADD" align="right">PO�кţ�</td>
	     <td class="TD_ADD">
			<input type="hidden" name="poid" disabled>
			<input type="text" name="podetailid" value="" class="rf_input_long" onchange="disinfo()">
			<input name="moreBtn" type="button" class="button_select" value="��" onclick="Getrequestvoiceid()" /><font color="red"> *</font>
	     </td>
	   </tr>
		<tr>
			<td class="TD_ADD" align="right">�߼�������</td>
     	  	<td class="TD_ADD">
     	  	<input type="hidden" name="warehouseid" disabled>
     	  	<input type="hidden" name="whAreaId" disabled>
     	  	<select id="Virtualwhid" style="width:163px;" onchange="GetWhInfo();" ><option value=""></option></select><font color="red"> *</font>
     	  	</td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">������ͣ�</td>
	      	<td class="TD_ADD"><select id="intype" style="width:163px;" disabled><option value=""></option></select><font color="red"> *</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">��Ʒ���룺</td>
			<td class="TD_ADD"><input type="text" name="productCode" class="rf_input_long" onchange="GetInfo();"><font color="red"> *</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">Ʒ����</td>
			<td class="TD_ADD">
			<input type="hidden" name="productId" disabled>
			<input type="text" name="productName" class="rf_input_long"  disabled><input type="hidden" name="productid" /><font color="red"> *</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">��λ��</td>
			<td class="TD_ADD"><input type="text" name="punit" class="rf_input_long"  disabled><font color="red"> *</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">������ţ�</td>
			<td class="TD_ADD"><input type="text" name="lotinfo" class="rf_input_long" maxlength="10" ><font color="red"> *</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">ԭ����ţ�</td>
			<td class="TD_ADD"><input type="text" name="lotinfo2" class="rf_input_long" maxlength="10" ></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">������</td>
			<td class="TD_ADD"><input type="text" name="jobnum" class="rf_input_long" maxlength="10" ><font color="red"> *</font></td>
		</tr>
		<tr>
		<td class="TD_ADD" align="right">��Ʒ״̬��</td>
          <td class="TD_ADD">
              <select id="productstatus" style="width:163px;">
                <option value="">--��ѡ��--</option>
              </select><font color="red"> *</font>
           </td>
           </tr>
		<tr>
			<td class="TD_ADD" align="right">�������룺</td>
			<td class="TD_ADD"><input type="text" name="traycode" class="rf_input_long" maxlength="10" ><font color="red"> *</font></td>
		</tr>
<!-- 		<tr>
			<td class="TD_ADD" align="right">���ͺţ�</td>
			<td class="TD_ADD"><input type="text" name="snumber" class="rf_input_long" maxlength="10" ><font color="red"> *</font></td>
		</tr> -->
		<tr>
     	  <td class="TD_ADD" align="right">�������ڣ�</td>
	      <td class="TD_ADD">
	      	<input id="printdate" type="text" value="" onfocus="calendar();" class="Wdate" style="width:162px;">
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
