
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
	}
	/*�ͷŰ�ť*/
	function UnLockButton(){
		
		document.getElementById("button_add").disabled = false;
	}
 	//ȡ���ջ�
	function saveData(invoiceid)
	{
		LockButton();
		var k = 0;
		var id = "";
		var check_ids = myIframe.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id = check_ids[i].value;
				k++;
			}
		}
		if( k == 0 ){
			alert("��ѡ���ջ�������!");
			return;
		}else if(k != 1){
			alert("һ��ֻ��ѡ��һ�У�");
			return;
		}

		if(confirm("��ȷ��ȡ���ջ���"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=receiptAction";
			myIframe.location.href = strUrl + "&method=cancelreceipt&invoiceid="+ invoiceid + "&transid=" + id;
			
		}else{
			UnLockButton();
		}
	}
	

	

</script>
  </head>
  
  <body>
  
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã������� -&gt; �ջ� -&gt; ȡ���ջ�</div></td>
   </tr>
  </table>
	
 <!-- ======== �ջ���¼�б�ʼ ======== -->
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST2">
   <tr>
	 <td valign="top"  height="305">
	   <iframe id="myIframe" src="<%=request.getContextPath()%>/BMSService?actionCode=receiptAction&flag=5&invoiceid=<%=invoiceid%>" scrolling="auto" frameborder="0" width="100%" height="100%">
	   </iframe>
	 </td>
   </tr>
 </table>
 <!-- ======== �ջ���¼�б���� ======== -->
 <table width="98%" height="5"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>
 
<table width="98%" height="10"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>	
	
	<FORM id="form1" name="form1">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
        <tr>
          <td height="27" colspan="6" align="center" ><input name="button_add" type="button" class="button_add" id="button_add" value="&nbsp;&nbsp;ȡ���ջ�" onClick="saveData('<%=invoiceid%>');"  />

            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="�ر�" onClick="window.close();" />
          </td>
        </tr>
      </table>
      
 </FORM>

  </body>
</html>
