<%@page import="java.util.List"%>
<%@page import="com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail"%>
<%@page import="com.wms3.bms.standard.entity.job.lxyy.InoutJob"%>
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
	
	String traycode = "";
	String jobid = "";
	String productid = "";
	String productname = "";
	String punit = "";
	String inLot = "";
	double num = 0;
	String productstatus = "";
	String productdate = "";
	String customerid = "";
	String customername = "";
	String jobtype = "";
	
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() >= 1)
	{
		Object[] obj = (Object[])ls.get(0);
		InoutJob job = (InoutJob)obj[0];
		InoutJobdetail jobD = (InoutJobdetail)obj[1];
		
		traycode = job.getTraycode();
		jobid = job.getJobid();
		productid = jobD.getProductcode();
		productname = jobD.getM_strProductName();
		punit = jobD.getM_strUnitName();
		inLot = jobD.getLotinfo();
		num = jobD.getJobnum();
		productstatus = jobD.getProductStatus();
		productdate = jobD.getPrintdate();
		customerid = jobD.getCustomerid();
		customername = jobD.getM_strCustomerName();
		jobtype = job.getJobtype();
		
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
	
	//**********************************************************
	//��������
	function saveData()
	{
        
		var productstatus = document.getElementById("productstatus").value; //��Ʒ״̬
		var lotinfo = document.getElementById("lotinfo").value;			//��������
		var jobid = document.getElementById("jobid").value; 	
		var jobnum = document.getElementById("jobnum").value;			//����
		var traycode = document.getElementById("traycode").value; 		//��������
		var printdate = document.getElementById("printdate").value; 	//��������
		var customerid = document.getElementById("customer_id").value; 	//��������
		var jobtype = document.getElementById("jobtype").value; 	//��ҵ����
		
		//if(productstatus == null || productstatus.length < 1){
		//	alert("����Ʒ״̬������Ϊ�գ�");
		//	return;
		//}
		if(jobid == null || jobid.length < 1){
			alert("��δ�ҵ�����ҵ�ţ�");
			return;
		}
		if(jobtype == null || jobtype.length < 1){
			alert("��δ�ҵ�����ҵ���ͣ�");
			return;
		}else{
			if(jobtype==hl){
				alert("��������ҵ����ά����");
				return;
			}
		}
		if(lotinfo == null || lotinfo.length < 1){
			alert("���������š�����Ϊ�գ�");
			return;
		}
		/* if(customerid == null || customerid.length < 1){
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
		
		condition = "&productstatus=" + productstatus + "&lotinfo=" + lotinfo + "&jobnum=" + jobnum + "&jobid=" + jobid 
				+ "&traycode=" + traycode + "&printdate=" + printdate +"&customerid=" + customerid;

	    if(confirm("��ȷ���޸���"))
		{		
			window.location.href = strUrl + "InjobSafeguardAction&method=ricoExecEditJob&rfmainView=rfmainView&flag=rf"+ condition;
		}

		
	}
	
	function getInfo()
	{
		var traycode = document.getElementById("traycode").value
		if(traycode == null || traycode.length < 1)
		{
			alert("��������������");
			return;
		}
		window.location.href = strUrl + "InjobSafeguardAction&flag=1&isWho=rf&traycode=" + traycode;
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
		selectType('<%=productstatus == null ? "":productstatus %>', 'productstatus', 'wpzt');	//��Ʒ״̬
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
						<td width="60%" align="center" class="font_006699_bold_12">RF���ά��</td>
						<td width="20%" align="right"><a href="<%=request.getContextPath()%>/rf/main.jsp?warehouseid=<%=strWarehouseID%>">����</a></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">�������룺</td>
			<td class="TD_ADD">
			<input type="hidden" name="jobid" value="<%=jobid %>">
			<input type="text" name="traycode" class="rf_input_long" maxlength="10" onchange="getInfo()" value="<%=traycode == null ? "":traycode %>"> <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">��Ʒ���룺</td>
			<td class="TD_ADD"><input type="text" name="productCode" class="rf_input_long" disabled value="<%=productid == null ? "":productid %>"> <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">Ʒ����</td>
			<td class="TD_ADD">
			<input type="hidden" name="productId" disabled>
			<input type="text" name="productName" class="rf_input_long" disabled value="<%=productname == null ? "":productname %>"> <font color="red">*</font> <input type="hidden" name="productid" /></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">��λ��</td>
			<td class="TD_ADD">
			<input type="hidden" name="jobtype" value="<%=jobtype %>">
			<input type="text" name="punit" class="rf_input_long"  disabled value="<%=punit == null ? "":punit %>"> <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">������ţ�</td>
			<td class="TD_ADD"><input type="text" name="lotinfo" class="rf_input_long" maxlength="10" value="<%=inLot == null ? "":inLot %>"> <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">������</td>
			<td class="TD_ADD"><input type="text" name="jobnum" class="rf_input_long" maxlength="10" value="<%=num %>"> <font color="red">*</font></td>
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
	      	<input id="printdate" type="text" onfocus="calendar();" class="Wdate" style="width:162px;" value="<%=productdate == null ? "":productdate %>"> <font color="red">*</font>
	      </td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">��Ӧ�̣�</td>
			<td class="TD_ADD">
			<input type="hidden" name="customer_id" value="<%=customerid == null ? "":customerid %>"><input type="text" name="customer_name" readonly style="width:137px;" value="<%=customername == null ? "":customername %>">
       		<input type="button" class="button_select" value="��" onClick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer_rf.jsp','330','500');">
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
