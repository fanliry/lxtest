
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String strTime =  StrTools.getCurrDateTime(8); 
	OutboundInvoiceHeader outBound = null; 
	OutboundInvoiceDetail outdetail = null; 
	if(request.getAttribute("invoicedetail") != null){
		outdetail = (OutboundInvoiceDetail)request.getAttribute("invoicedetail"); 
	}
	if(request.getAttribute("invoice") != null){
		outBound = (OutboundInvoiceHeader)request.getAttribute("invoice"); 
	}
	//����С��2λ
	NumberFormat nbf=NumberFormat.getInstance();      
	nbf.setMinimumFractionDigits(2);
	nbf.setMaximumFractionDigits(2);  
	
	String warehouseid = "";	//�ֿ�ID
	String outstockdetailid = "";//���ⵥ��ϸID
    String outstockid = "";		//���ⵥ�ݺ�
        
    String productid = "";		//��Ʒ
    String packid = "";			//��װ
    String pkgunit = "";		//��λ
    String productname = "";	//��Ʒ����
    String packname = "";		//��װ����
    String pkgunitname = "";	//��λ����
    //String lotinfo = "";	   //����
    
      
    double invoicenum = 0;		//��������
    double netweight = 0;		//��������
    double weight = 0;			//��������
    double volume = 0;			//�������
    
    double assignnum = 0;		//��������
    double assignnetweight = 0; //���侻��
    double assignweight = 0;    //��������
    double assignvolume = 0;    //�������  
    
    double noassignnum = 0; //δ��������
      
      
    String customid = "";			//�ͻ�id  
    String ownerid = "";			//����id 
    String customname = "";			//�ͻ�����
    String ownername = "";			//��������
    
    String outtype = "";
    
    
         
	if(outdetail != null)
	{
	   outstockdetailid = outdetail.getOutstockdetailid();	//���ⵥ��ϸID
       outstockid = outdetail.getOutstockid();				//���ⵥ�ݺ�
       
       productid = outdetail.getProductid();		//��Ʒ
       packid = outdetail.getPackid();				//��װ
       pkgunit = outdetail.getPkgunit();			//��λ
       productname = outdetail.getM_strProductName();//��Ʒ����
       packname = outdetail.getM_strPackName();		//��װ����
       pkgunitname = outdetail.getM_strUnitName();	//��λ���� 
       //lotinfo = outdetail.getLotinfo(); //����
       strTime = outdetail.getPrintdate(); //��������
       
       invoicenum = outdetail.getInvoicenum();		//��������
       netweight = outdetail.getNetweight();		//����
       weight = outdetail.getWeight();				//����
       volume = outdetail.getVolume();				//���
       
       assignnum = outdetail.getAssignnum();				 //��������
       assignnetweight = outdetail.getAssignnetweight();     //���侻��
       assignweight = outdetail.getAssignweight();           //��������
       assignvolume = outdetail.getAssignvolume();           //�������
       noassignnum = invoicenum-assignnum;
       
	}
	if(outBound != null){
		warehouseid = outBound.getWarehouseid();//�ֿ�ID
		customid = outBound.getCustomerid();	//�ͻ�id  
        ownerid = outBound.getOwnerid();		//����id
        customname = outBound.getCustomername();//�ͻ�����
        ownername = outBound.getOwnername();	//��������
        outtype= outBound.getOuttype();
	}
%>
<html>
  <head>
    <title>�㽭���������ֿ����ϵͳ</title>  
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
	<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/standard/style/load.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendartime.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lotsearch.js"></script>
	<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />

 <script>
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
		
		document.getElementById("button_search").disabled = true;
		//document.getElementById("button_edit").disabled = true;
		document.getElementById("button_delete").disabled = true;
	}
	/*�ͷŰ�ť*/
	function UnLockButton(){
		
		document.getElementById("button_search").disabled = false;
		//document.getElementById("button_edit").disabled = false;
		document.getElementById("button_delete").disabled = false;
	}
 	//�ֶ�����Ĳ�ѯ����
	function queryData()
	{
		inum.innerHTML = "0";
		//����ID
		var invoiceid = document.getElementById("invoiceid").value;
		//���ⵥ��ϸID
		var invoicedetailid = document.getElementById("invoicedetailid").value;
		//�ͻ����ջ��ˣ�
		var customerid = document.getElementById("customer_id").value;
		//����
		var ownerid = "";
		//�ֿ�ID
		var warehouseid = document.getElementById("warehouseid").value;
		//Ʒ��ID
		var productid = document.getElementById("product_id").value;	
		//����ID
		var whAreaId = document.getElementById("whAreaId").value;
		
		//���ID
		var alleyid = document.getElementById("cargoAlleyId").value;
		//��������
		var traycode = document.getElementById("traycode").value;
		
		//��λ
		var unit = '<%=pkgunit%>';
		
		if(whAreaId == null || whAreaId.length < 1){
			alert("������������Ϊ�գ�");
			return;
		}
		
		var condition = "&invoiceid=" + invoiceid + "&invoicedetailid=" + invoicedetailid + "&customerid=" + customerid + "&ownerid=" + ownerid + "&warehouseid=" + warehouseid + "&productid=" + productid +"&whAreaId=" + whAreaId + "&alleyid=" + alleyid + "&traycode=" + traycode+ "&unit=" + unit;
		var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=allocationAction&flag=1";	
		list.formx1.action = strUrl + condition;
		list.document.formx1.submit();	
	}
	function IsNum(num)
	{ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
	function saveData()
	{
		LockButton();
		//����ID
		var invoiceid = document.getElementById("invoiceid").value;
		//������ϸID
		var invoicedetailid = document.getElementById("invoicedetailid").value;
		if(invoiceid == null || invoiceid.length < 1){
			alert("�����ݡ�����Ϊ�գ�");
			UnLockButton();
			return;
		}
		if(invoicedetailid == null || invoicedetailid.length < 1){
			alert("��������ϸ������Ϊ�գ�");
			UnLockButton();
			return;
		}
		var result = "";
		var check_ids = list.document.getElementsByName("check_id");
		var fpnums = list.document.getElementsByName("fpnum");
		var nums = list.document.getElementsByName("num");
		var noz = "<%=noassignnum%>";
		var floor = document.getElementById("floor").value;
		var tsjh = document.getElementById("tsjh").value;
		var outtype = document.getElementById("outtype").value;
		var temp = 0;
		if(floor == null || floor.length < 1){
			alert("��¥�㡿����Ϊ�գ�");
			UnLockButton();
			return;
		}
		if(outtype == 4){ //ҵ������Ҫ������
		  	if(!document.getElementById("tsjh").disabled && (tsjh == null || tsjh.length < 1))
			{
				alert("��������������");
				return false;
			}
		}
		
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				temp = temp + parseFloat(fpnums[i].value);
			        if(fpnums[i].value == null ||  !IsFloat(fpnums[i].value))
					{
						alert("��"+(i+1)+"��,��������Ϊ�ջ�ֻ��Ϊ���֣�");
						UnLockButton();
						return false;
					}else if(parseFloat(fpnums[i].value)<0)
					{
						alert("��"+(i+1)+"��,��������С��1��");
						UnLockButton();
						return false;
					}
					else if(parseFloat(fpnums[i].value) > parseFloat(nums[i].value+1))
					{
						alert("��"+(i+1)+"��,�������ܴ���"+nums[i].value+"��");
						UnLockButton();
						return false;
					}
					else if(temp > parseFloat(noz))
					{
						alert("����"+noz+"��");
						UnLockButton();
						return false;
					}
			    var va = check_ids[i].value+","+fpnums[i].value+"]";
				result = result + va+ "|";	
			}
		}

		var condition = "&outboundid="+ invoiceid + "&detaiid=" + invoicedetailid + "&result=" + result + "&floor=" + floor + "&tsjh=" + tsjh;
		//************************************************
		if(confirm("��ȷ���������ļ�¼��"))
		{
			b.innerHTML = parseFloat(document.getElementById("b").innerText) + temp;        //�����ѷ�������
			synum.innerHTML = parseFloat(document.getElementById("synum").innerText) - temp;    //����δ��������
			
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=allocationAction&method=allotRicoExec";
			list.formx1.action = strUrl + condition;
			
			list.document.formx1.submit();	
			UnLockButton();	
			//queryData();
			
		}else{
			UnLockButton();
		}
	}
	//�Զ�����
	function updateData()
	{
		LockButton();
		//����ID
		var invoiceid = document.getElementById("invoiceid").value;
		//������ϸID
		var invoicedetailid = document.getElementById("invoicedetailid").value;
		
		if(invoiceid == null || invoiceid.length < 1){
			alert("�����ݡ�����Ϊ�գ�");
			UnLockButton();
			return;
		}
		
		if(invoicedetailid == null || invoicedetailid.length < 1){
			alert("��������ϸ������Ϊ�գ�");
			UnLockButton();
			return;
		}
		
		var result = "";
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				result = result + check_ids[i].value + "|";		
			}
		}
		if(result.length > 0)
		{
			result = result.substring(0, result.length-1);	
		}
		var condition = "&outboundid="+ invoiceid + "&detaiid=" + invoicedetailid + "&result=" + result ;
		
		//************************************************
		if(confirm("��ȷ���������ļ�¼��"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=allocationAction&method=outoAllotRicoExec";
			list.location.href = strUrl + condition;

				
		}else{
			UnLockButton();
		}
	}
	function viewTrans(packid,eunit,lotid,lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12)
	{
		selectinoutUnit(eunit, 'eunit', packid, '1');	//��λ		
		setLotValueReadonly(lotid, lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12);//����
					
	}
	//���ݿ������������б�
	function getAlleyList(){
	
		var whAreaId = document.getElementById("whAreaId").value;
		selectAlleyList("", "cargoAlleyId", whAreaId);
	}
	function getInput(){
		var floor = document.getElementById("floor").value;
		document.getElementById("tsjh").value = "";
		if(floor == "2")
		{
			document.getElementById("tsjh").disabled = false;
		}else
		{
			document.getElementById("tsjh").disabled = true;
		}
	}
  	function OnLoad()
	{	
		UnLockButton();
		selectObject('<%=warehouseid%>', 'warehouseid', '1');  //�ֿ�
		selectAreaList('001001', 'whAreaId', '<%=warehouseid%>', "1");// �������
		selectType('<%=pkgunit%>', "eunit", "punit");
		//����
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
		if(back_msg != "")
		{
			if(back_msg == "Y"){	
				alert("�����ɹ���");
			}else{
				alert(back_msg);
			}
		}
	}
 </script>
</head>
  <body onload="javascript:OnLoad();">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td>
       
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><input type="hidden" id="outtype" value="<%=outtype%>"><div class="font_001F56_bold_12">��ǰλ�ã�������� -&gt; �½����ⵥ -&gt; ����</div></td>
   </tr>
  </table>
	<FORM id="form1">
  
	  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
			<tr>
				<td width="10%" align="right" class="yx1">�� &nbsp;&nbsp;&nbsp;&nbsp;�⣺</td>
				<td class="yx">
				<select id="warehouseid" style="width:100px;" class="input_readonly">
						<option value="">--��ѡ��--</option>
				</select>
				</td>
				<td align="right" class="yx1">���ⵥ�ţ�</td>
				<td class="yx"><input type="text" name="invoiceid" value="<%=outstockid%>" class="input_readonly" readonly style="height:18px;width:100px;" /> <input type="hidden" name="invoicedetailid" value="<%=outstockdetailid%>" /></td>
				<td align="right" class="yx1">��&nbsp;&nbsp;����</td>
				<td class="yx"><input type="text" name="customer_name" value="<%=customname == null ? "" : customname%>" readonly="readonly" class="input_readonly" style="height:18px;width:100px;" /> <input type="hidden" name="customer_id" value="<%=customid%>" /></td>
				<td align="right" class="yx1">�� &nbsp;&nbsp;&nbsp;&nbsp;λ��</td>
				<td class="yx"><select name="eunit" id="eunit" style="width:100px;" class="input_readonly" disabled="disabled">
						<option>--��ѡ��--</option>
				</select></td>
			</tr>
			<tr>
				<td width="10%" align="right" class="yx1">Ʒ &nbsp;&nbsp;&nbsp;&nbsp;����</td>
				<td class="yx"><input type="text" name="product_name" value="<%=productname%>" class="input_readonly" readonly style="height:18px;width:100px;" /> <input type="hidden" name="product_id" value="<%=productid%>" class="input_readonly" style="height:18px;width:100px;" /></td>

				<td align="right" class="yx1"><span class="red">*</span>��&nbsp;&nbsp;&nbsp;&nbsp;����</td>
				<td class="yx"><select id="whAreaId" style="width:100px;" onChange="getAlleyList()">
						<option value="">--��ѡ��--</option>
				</select></td>
				<td align="right" class="yx1">�� &nbsp;&nbsp;&nbsp;&nbsp;����</td>
				<td class="yx"><select id="cargoAlleyId" style="width:100px;"><option value="">--��ѡ��--</option></select></td>
				<td align="right" class="yx1">�������룺</td>
				<td class="yx" colspan="5"><input type="text" name="traycode" size="16" value="" style="height:18px;width:100px;" /></td>
			</tr>
			<tr>
				<td width="10%" align="right" class="yx1">��ҵ¥�㣺</td>
				<td class="yx"><select id="floor" onchange="getInput()">
		     		<option value="">---��ѡ��---</option>
		     		<option value="1" selected>1¥</option>
		     		<option value="2">2¥</option>
		     	</select></td>
				<td align="right" class="yx1">���䣺</td>
				<td class="yx" colspan="5"><select id="tsjh" disabled>
		     		<option value="">---��ѡ��---</option>
		     		<option value="1">һ����</option>
		     		<option value="2">������</option>
		     		<option value="3">������</option>
		     		<option value="4">�ĳ���</option>
		     		<option value="5">�峵��</option>
		     	</select></td>
			</tr>
       </table>
       
       <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
        <tr height="25">
           <td valign="bottom">
     			<span style="color: blue;font-size:12px;font-weight:bold; ">��������:</span><span id="a" style="color: blue;font-size:12px;font-weight:bold; "><%=nbf.format(invoicenum)%> </span>
     			<span style="color: green;font-size:12px;font-weight:bold; ">�ѷ���������</span><span id="b" style="color:green;font-size:12px;font-weight:bold;  "><%=nbf.format(assignnum)%> </span>
     			<span style="color: #A6A600;font-size:12px;font-weight:bold; ">δ��������:</span><span id="synum" style="color: #A6A600;font-size:12px;font-weight:bold;  "><%=nbf.format(noassignnum)%></span>
     			<span style="color: red;font-size:12px;font-weight:bold; ">��ǰ��ѡ:</span><span id="inum" style="color: red;font-size:12px;font-weight:bold; ">0</span>
          </td>
			<td align="right" >
            <input name="button_search" type="button" class="button_search" id="button_search" value="&nbsp;&nbsp;��ѯ" onClick="queryData();" />
            <input name="button_delete" type="button" class="button_add" id="button_delete" value="&nbsp;&nbsp;ȷ��" onclick="saveData();" />
            <!-- <input name="button_edit" type="button" class="button_add" id="button_edit" value="&nbsp;&nbsp;�Զ�����" onclick="updateData();" /> -->
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="�ر�" onClick="window.close();" />
          </td>
        </tr>
      </table>
      </FORM>

</td>
   </tr>
   <tr>
   	 <td height="10">
   	 </td>
   </tr>
   <tr>
     <td height="100%"> 
       
  <!-- ======== ����б�ʼ ======== -->
 <table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
   <tr>
	 <td>
	   <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/outbound/assgin/outbound_assgin_list.jsp" scrolling="auto" frameborder="0" width="100%" height="100%">  
	   </iframe>
	 </td>
   </tr>
 </table>
 <!-- ======== ����б���� ======== -->
  
     </td>
   </tr>
 </table>



  </body>
</html>
