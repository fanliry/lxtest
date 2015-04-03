<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean baseBarcodeNew = false;    //����������
	boolean baseBarcodeAll = false;    //ȫ������
	boolean baseBarcodeUse = false; //��������
	boolean baseBarcodeUsed = false;  //��������
	boolean baseBarcodeFUsed = false; //�����ʹ��
	boolean baseBarcodePrint = false;  //��ӡ����
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("baseBarcodeNew") != null){
			baseBarcodeNew = true;
		}
		if(hsPopedom.get("baseBarcodeAll") != null){
			baseBarcodeAll = true;
		}
		if(hsPopedom.get("baseBarcodeUse") != null){
			baseBarcodeUse = true;
		}
		if(hsPopedom.get("baseBarcodeUsed") != null){
			baseBarcodeUsed = true;
		}
		if(hsPopedom.get("baseBarcodeFUsed") != null){
			baseBarcodeFUsed = true;
		}
		if(hsPopedom.get("baseBarcodePrint") != null){
			baseBarcodePrint = true;
		}
    }
%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/inspectClass.js"></script>
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;

	//��������Ƿ�Ϊ����
	function IsNum(num)
	{ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	} 
	
	//��ѯ
	function Search(type)
	{

		if(type == 1){
			condition = "";
			
		}else if(type == 2){
			condition = "&is_used=N"
			
		}else{
			condition = "&is_used=Y"
		}
		
		var linerow = parent.page.document.getElementById("lineviewrow").value;		//ÿҳ��ʾ����
		window.parent.list.location.href = ac + "baseBarcodeAction" + condition + "&linerow=" + linerow;
	}
	
	//����������
	function addData()
	{
		var head = document.getElementById("head").value;
		var start = document.getElementById("start").value;
		var amount = document.getElementById("amount").value;
		
		
		if(head == null || head.length < 1)
		{
			alert("������ͷ������Ϊ�գ�");
			return;
		}
		if(start == null || start.length < 1 || !IsNum(start) || parseInt(start) < 1 || parseInt(start) > 99999)
		{
			alert("����ʼ������Ϊ����ֻ��Ϊ0~100000������֣�");
			return;
		}
		if(amount == null || amount.length < 1 || !IsNum(amount) || parseInt(amount) < 0 || (parseInt(amount) + parseInt(start)) > 99999)
		{
			alert("������������Ϊ����ֻ��Ϊ����0�������ң���ʼ+����<100000����");
			return;
		}

		var linerow = parent.page.document.getElementById("lineviewrow").value;		//ÿҳ��ʾ����
		condition = "&head=" + head + "&start=" + start + "&amount=" + amount + "&linerow=" + linerow;
		
		window.parent.list.location.href = ac + "baseBarcodeAction&method=ricoExecAdd" + condition;
	}
	
	// �޸�������
	function updateData()
	{
		var ids = "";
		var check_ids = parent.list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++)
		{
			if(check_ids[i].checked)
			{
				ids += check_ids[i].value + ",";
			}
		}
		if(ids == "")
		{
			alert("������ѡ��һ����¼��");
			return;
		}
		
		var linerow = parent.page.document.getElementById("lineviewrow").value;		//ÿҳ��ʾ����
		condition = "&ids=" + ids + "&linerow=" + linerow;
		
		window.parent.list.location.href = ac + "baseBarcodeAction&method=ricoExecEdit" + condition;
	}
	
	//��ӡ����
	function PrintCode()
	{
		var ids = "";
		var check_ids = parent.list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++)
		{
			if(check_ids[i].checked)
			{
				ids += check_ids[i].value + ",";
			}
		}
		if(ids == ""){
			alert("������ѡ��һ����¼��");
			return;
		}
		updateData();
		ids = ids.substring(0, ids.length-1);
		
		//showModalDialog("<%=request.getContextPath()%>/jsp/maintenance/tray_code_print.jsp", ids, "dialogWidth=600px;dialogHeight=400px");
		window.open("<%=request.getContextPath()%>/standard/jsp/base/barcode/base_barcode_print.jsp?ids="+ids, '', 
			"Width=600,Height=500; scrollbars=yes, resizable=yes");
	}
-->
</script>
</head>

<body>
  <table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr><td height="100%" valign="top">
 
  <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
    <tr>
      <td style="height:25px;" class="TD_LIST_TITLE4" align="center" colspan="2">����������</td>
    </tr>
    <tr>
      <td class="yx1" align="right">����ͷ��</td>
      <td class="xx1"><input name="head" size="10" maxlength="7">
    </tr>
    <tr>
      <td class="yx1" align="right">��ʼ��</td>
      <td class="xx1"><input name="start" size="10" maxlength="7">
    </tr>
    <tr>
      <td class="yx1" align="right">������</td>
      <td class="xx1"><input name="amount" size="10">
    </tr>
    <%if(baseBarcodeNew){%>
    <tr>
      <td colspan="2" align="center">
        <input onclick="addData()" type="button" name="button1" value="����������" style="width: 100px" class="BUTTON_STYLE1">
      </td>
    </tr>
     <%}%>
  </table>

  <br>
 
  <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
  
    <tr>
      <td style="height:25px;" class="TD_LIST_TITLE4" align="center">��ѯ</td>
    </tr>
    <%if(baseBarcodeAll){%>
    <tr>
      <td class="xx1" align="center">
        <input onclick="Search(1)" type="button" name="button2" value="ȫ������" style="width: 100px" class="BUTTON_STYLE1">
      </td>
    </tr>
    <%}%>
    <%if(baseBarcodeUse){%>
    <tr>
      <td class="xx1" align="center">
        <input onclick="Search(2)" type="button" name="button2" value="��������" style="width: 100px" class="BUTTON_STYLE1">
      </td>
    </tr>
    <%}%>
    <%if(baseBarcodeUsed){%>
    <tr>
      <td align="center">
        <input onclick="Search(3)" type="button" name="button3" value="��������" style="width: 100px" class="BUTTON_STYLE1">
      </td>
    </tr>
    <%}%>
  </table>
 
  <br> 
 
  <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
    <tr>
      <td style="height:25px;" class="TD_LIST_TITLE4" align="center">����</td>
    </tr>
    <%if(baseBarcodeFUsed){%>
    <tr>
      <td class="xx1" align="center">
        <input onclick="updateData()" type="button" name="button4" value="�����ʹ��" style="width: 100px" class="BUTTON_STYLE1">
      </td>
    </tr>
    <%}%>
    <%if(baseBarcodePrint){%>
    <tr>
      <td align="center">
        <input onclick="PrintCode()" type="button" name="button4" value="��ӡ����" style="width: 100px" class="BUTTON_STYLE1">
      </td>
    </tr>
    <%}%>
  </table>
 
     </td>
   </tr>
 </table>
 
</body>
</html>
