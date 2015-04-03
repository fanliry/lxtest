
<%@ page contentType="text/html; charset=GBK"%>
<%
	
	//����ID
	String invoiceid = request.getParameter("invoiceid");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
		judgmentTool.isOrderChecked('<%=invoiceid%>', 'header', setButton);
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
	
	
	
	
	
 	//ȷ�ϸ���
	function okReview(invoiceid)
	{
		LockButton();
		//��ҵID
		var jobid = document.getElementById("jobid").value;
		//��ҵ��ϸID
		var jobdetailid = document.getElementById("jobdetailid").value;
		
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
		if(jobid == null || jobid.length < 1){
			alert("����ҵ��š�����Ϊ�գ�");
			UnLockButton();
			return;
		}
		if(jobdetailid == null || jobdetailid.length < 1){
			alert("����ҵ��ϸ��š�����Ϊ�գ�");
			UnLockButton();
			return;
		}
		
		
		//************************************************
		if(confirm("��ȷ�����ˣ�"))
		{				
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=reviewAction&method=detailReview&flag=1";
			//DWREngine.setAsync(false);
			myIframe.location.href = strUrl + "&invoiceid=" + invoiceid + "&invoicedetailid=" + detailid + "&renum=" + invoicenum + "&jobid=" + jobid + "&jobdetailid=" + jobdetailid;
			
			//DWREngine.setAsync(true);
	
		}else{
			UnLockButton();
		}
	}
	//ȡ������
	function cancelReview()
	{
		LockButton();
		var k = 0;
		var id = "";//����ID
		var check_ids = detail.document.getElementsByName("check_id");
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
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=reviewAction&method=detailReview&flag=2";
			//DWREngine.setAsync(false);
			myIframe.location.href = strUrl + "&invoiceid=" + invoiceid + "&crossid=" + id;
			//var d = new Date();
			//detail.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=reviewAction&flag=5&invoiceid="+invoiceid+"&data="+d.toString();
			//DWREngine.setAsync(true);
	
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
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�������� -&gt; ���� -&gt; ����ϸ����</div></td>
   </tr>
  </table>
	
 <!-- ======== ��ҵ�б�ʼ ======== -->
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST2">
   <tr>
	 <td valign="top"  height="305">
	   <iframe id="myIframe" src="<%=request.getContextPath()%>/BMSService?actionCode=reviewAction&flag=4&invoiceid=<%=invoiceid%>" frameborder="0" width="100%" height="100%">
	   </iframe>
	 </td>
   </tr>
 </table>
<table width="98%" height="5"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>	
	<FORM id="form1" name="form1">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
        
        <tr>
          <td  align="right" class="yx1">���ⵥ�ţ�</td>
          <td  class="yx" >
          	 <input type="text" name="invoiceid" value="<%=invoiceid%>" class="input_readonly" readonly  style="height:18px;width:100px;"/>
          </td>
          <td  align="right" class="yx1">��&nbsp;��&nbsp;�ˣ�</td>
          <td  class="yx" >
	          <input type="text" name="customername"  class="input_readonly" readonly  style="height:18px;width:200px;"/>         
          </td>
          <td  align="right" class="yx1">��ҵ��ţ�</td>
          <td  class="xx1" >
          	 <input type="text" name="jobid" class="input_readonly" readonly   style="height:18px;width:100px;"/> 
          </td>
        </tr>
        <tr>
          
          <td  align="right" class="yx1">��ϸ��ţ�</td>
          <td  class="yx" >
          	 <input type="text" name="jobdetailid" class="input_readonly" readonly   style="height:18px;width:100px;"/> 
          </td>
          <td  align="right" class="yx1">��&nbsp;&nbsp;&nbsp;&nbsp;Ʒ��</td>
          <td  class="yx" >
          	 <input type="text" name="product_name" class="input_readonly" readonly   style="height:18px;width:100px;"/> 
          </td>
          <td  align="right" class="yx1"><span class="red">*</span>����������</td>
          <td  class="xx1">
	          	<input type="text" name="invoicenum" size="16"   style="height:18px;width:100px;"/>
          		<input type="hidden" name="uninvoicenum" size="16"  />
          </td>
        </tr>

		<tr>
        	<TD colspan="6" height="2">
        		<input type="hidden" name="invoicedetailid"/>
        	</TD>
        </tr>
        <tr>
          <td height="27" colspan="6" align="center" ><input name="button_add" type="button" class="button_add" id="button_add" value="&nbsp;&nbsp;ȷ�ϸ���" onClick="okReview();" />
            <input name="button_delete" type="button" class="button_delete" id="button_delete" value="&nbsp;&nbsp;ȡ������" onclick="cancelReview();" />
            <input name="button" type="reset" class="button_reset" id="button" value="&nbsp;&nbsp;����" />
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="�ر�" onClick="window.close();" />
          </td>
        </tr>
      </table>
      
 </FORM>
	 <!-- ======== ��ҵ��ϸ�б�ʼ ======== -->
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="TABLE_LIST2">    
	<tr>
         <td valign="top"  height="240" > <!--  ��Ҫ�к��ŵĹ��������߶���Ϊ185���� û����165 -->
					<iframe id="detail" src="<%=request.getContextPath()%>/BMSService?actionCode=reviewAction&flag=5&invoiceid=<%=invoiceid%>"   width="100%" height="100%"   frameborder="0" ></iframe>
         </td>
    </tr>
 </table>
 <!--  ======== ��ҵ��ϸ�б���� ======== -->
  </body>
</html>
