<%@ page contentType="text/html; charset=GBK"%>
<%
	//������뵥��id
	String instockid = request.getParameter("instockid");
%>
<html>
  <head>
    <title>�㽭���������ֿ����ϵͳ</title>  
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
	<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>
	<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<script type="text/javascript">
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
	function deleteRecord(){
	    var k = 0;
		var id = "";
		var instockid = document.getElementById("instockid").value;//������뵥�ݺ�
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id = check_ids[i].value;
				k++;
			}
		}
		if( k == 0 ){
			alert("��ѡ��һ���ջ���¼��");
			return;
		}else if(k != 1){
			alert("ֻ��ѡ��һ���ջ���¼��");
			return;
		}
		    var msg;
			DWREngine.setAsync(false);
			judgmentTool.isReceipt(id, Show);
			DWREngine.setAsync(true);
			function Show(value){
				msg = value;
			}
			if(msg != "Y"){
				alert(msg);
				return;
			}
		list.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=InBoundAction&flag=8&instockid="+instockid+"&receiverecordid="+id;
	}
 </script>
</head>
  <body>
  
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã������� -&gt; ���������� -&gt;��ⵥ���� -&gt; �ջ���Ϣ��ʾ</div></td>
   </tr>
  </table>
	<FORM id="form1">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
        <tr>
          <td height="27" colspan="6" align="center" >
            <input name="button_delete" type="button" class="button_delete" id="button_delete" value="&nbsp;&nbsp;ȡ���ջ�" onclick="deleteRecord();" />
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="�ر�" onClick="window.close();" />
            <input type="hidden" name="instockid" value="<%=instockid!=null?instockid:""%>"/>
          </td>
        </tr>
      </table>
      </FORM>
 <table width="98%" height="10"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>


 <!-- ======== ��ϸ�б�ʼ ======== -->
 <table width="98%" height="270" border="0" align="center" cellpadding="0" cellspacing="0">
   <tr>
	 <td>
	   <iframe id="list" src="<%=request.getContextPath()%>/BMSService?actionCode=InBoundAction&flag=7&instockid=<%=instockid%>" scrolling="auto" frameborder="0" width="100%" height="300px">
	   </iframe>
	 </td>
   </tr>
 </table>
 <!-- ======== ��ϸ�б���� ======== -->
  </body>
</html>
