
<%@ page contentType="text/html; charset=GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	//���ⵥ��ID
	String invoiceid = request.getParameter("invoiceid");
%>
<html>
  <head>
    <title>�㽭���������ֿ����ϵͳ</title>  
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
	<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
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
		document.getElementById("button_add").disabled = true;
		document.getElementById("button_delete").disabled = true;
	}
	/*�ͷŰ�ť*/
	function UnLockButton(){
		document.getElementById("button_add").disabled = false;
		document.getElementById("button_delete").disabled = false;
	}
	
	//���ø��˰�ť�������
	function setBtn()
	{
		judgmentTool.isOrderChecked('<%=invoiceid%>', 'detail', setButton);
	}
	//���ø��˰�ť�������
	function setButton(str)
	{
		if(str>0)
		{
			document.getElementById("button_add").disabled = true;
			document.getElementById("button_delete").disabled = true;
			document.getElementById("button").disabled = true;
		}
		else
		{
			document.getElementById("button_add").disabled = false;
			document.getElementById("button_delete").disabled = false;
			document.getElementById("button").disabled = false;
		}
	}
	
	
 	//ȷ������
	function okReview()
	{
		LockButton();
		//���ⵥ��ID
		var invoiceid = document.getElementById("invoiceid").value;
		//���ⵥ��ϸID
		var detailid = document.getElementById("invoicedetailid").value;
		//��������
		var invoicenum = document.getElementById("invoicenum").value;
		//δ��������
		var uninvoicenum = document.getElementById("uninvoicenum").value;

		if(invoiceid == null || invoiceid.length < 1){
			alert("�����ⵥ������Ϊ�գ�");
			UnLockButton();
			return;
		}
		
		if(detailid == null || detailid.length < 1){
			alert("����Ʒ������Ϊ�գ�");
			UnLockButton();
			return;
		}
		
		if(invoicenum == null || invoicenum.length < 1 || !IsFloat(invoicenum)){
			alert("������������Ϊ����ֻ��Ϊ���֣�");
			UnLockButton();
			return;
		}
		if(parseFloat(invoicenum) <= 0){
			alert("����������(" + invoicenum + ")������С��0��");
			UnLockButton();
			return;
		}
		if(parseFloat(invoicenum) > parseFloat(uninvoicenum)){
			alert("����������(" + invoicenum + ")�����ܴ���δ����������(" + uninvoicenum + ")����");
			UnLockButton();
			return;
		}


		//************************************************
		if(confirm("��ȷ�����ˣ�"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=reviewAction&method=invoiceReview&flag=1";

			list.location.href = strUrl + "&invoiceid=" + invoiceid + "&invoicedetailid=" + detailid + "&renum=" + invoicenum;

		}else{
			UnLockButton();
		}
	}
	//ȡ������
	function cancelReview()
	{
		LockButton();
		var k = 0;
		var id = "";//���ⵥ��ϸID
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++)
		{
			if(check_ids[i].checked)
			{
				id = check_ids[i].value;
				k++;
			}
		}
		if(id == ""){
			alert("��ѡ��Ҫȡ���ļ�¼��");
			UnLockButton();
			return;
		}
		if( k == 0 ){
			alert("��ѡ��һ����¼��");
			UnLockButton();
			return;
		}else if(k != 1){
			alert("ֻ��ѡ��һ����¼��");
			UnLockButton();
			return;
		}
		//���ⵥ��ID
		var invoiceid = document.getElementById("invoiceid").value;
		if(invoiceid == null || invoiceid.length < 1){
			alert("�����ⵥ������Ϊ�գ�");
			UnLockButton();
			return;
		}
		
		//************************************************
		if(confirm("��ȷ��ȡ�����ˣ�"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=reviewAction&method=invoiceReview&flag=2";
			list.location.href = strUrl + "&invoiceid=" + invoiceid + "&invoicedetailid=" + id;
	
		}else{
			UnLockButton();
		}
	}
	

 function onLoad()
 {
 	var cunstomername = window.dialogArguments;
	document.getElementById("customername").value = cunstomername;
	setBtn();
 }
 

 </script>
</head>
  <body onload="javascript:onLoad();">
  
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�������� -&gt; ���� -&gt; ����������</div></td>
   </tr>
  </table>
	<FORM id="form1">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
        <tr>
          <td width="15%" align="right" class="yx1"  >���ⵥ�ţ�</td>
          <td width="35%" class="yx" >
          	  <input type="text" name="invoiceid" value="<%=invoiceid%>" class="input_readonly" readonly  style="height:18px;width:100px;"/>
          </td>
          <td width="15%" align="right" class="yx1">��&nbsp;��&nbsp;�ˣ�</td>
          <td width="35%" class="xx1">
	          <input type="text" name="customername"  class="input_readonly" readonly  style="height:18px;width:200px;"/>
          </td>
        </tr>
        <tr>
          <td  align="right" class="yx1"  >Ʒ&nbsp;&nbsp;&nbsp;&nbsp;����</td>
          <td  class="yx" >
          	 <input type="text" name="product_name" class="input_readonly"    style="height:18px;width:200px;"/>
          </td>
          <td align="right" class="yx1"><span class="red">*</span>��&nbsp;&nbsp;&nbsp;&nbsp;����</td>
          <td class="xx1">
          	<input type="text" name="invoicenum" size="16"   style="height:18px;width:100px;"/>
          	<input type="hidden" name="uninvoicenum" size="16"  />
          </td>
        </tr>
        
        <tr>
          <td height="27" colspan="4" align="center" ><input name="button_add" type="button" class="button_add" id="button_add" value="&nbsp;&nbsp;ȷ�ϸ���" onClick="okReview();" />
            <input type="hidden" name="invoicedetailid"/>
            <input name="button_delete" type="button" class="button_delete" id="button_delete" value="&nbsp;&nbsp;ȡ������" onclick="cancelReview();" />
            <input name="button" type="reset" class="button_reset" id="button" value="&nbsp;&nbsp;����" />
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="�ر�" onClick="window.close();" />
          </td>
        </tr>
      </table>
      </FORM>
 <table width="98%" height="10"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>


 <!-- ======== ��ϸ�б�ʼ ======== -->
 <table width="98%" height="400" border="0" align="center" cellpadding="0" cellspacing="0">
   <tr>
	 <td>
	   <iframe id="list" src="<%=request.getContextPath()%>/BMSService?actionCode=reviewAction&flag=3&invoiceid=<%=invoiceid%>" scrolling="auto" frameborder="0" width="100%" height="100%">
	   </iframe>
	 </td>
   </tr>
 </table>
 <!-- ======== ��ϸ�б���� ========  -->

  </body>
</html>
