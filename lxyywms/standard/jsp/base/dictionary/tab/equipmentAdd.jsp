<%@ page contentType="text/html; charset=GB2312"%>
<%
	//�����ظ��ύ�ı�־������beginΪ�״μ��أ�finishΪ���ύ
	session.setAttribute("submitFlag","begin");
	String strNumber=(String)request.getAttribute("number");
%>

<html>
<head>
<title>�豸���ͱ༭����</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/table.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src="<%=request.getContextPath()%>/js/tools.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/js/selectTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/inspectClass.js"></script>

<script>
<!--
	  function inspect()
	  {
	  	var name=document.getElementById("name").value;
	  	if(name!="")
	  	{
	  		if(name.length>15)
			{
				alert("���Ʋ��ܳ���15���ַ���");
				return false;
			}
			if(document.getElementById("remark").value.length>50)
			{
				alert("��ע���ܳ���50���ַ���");
				return false;
			}
		<%--
			inspectClass.checkPackSpace(name,function(data){
			
			if(data=="0")
			{
				dwr.util.setValue("checkOnly","�˹������Ѿ����ڣ�");
				document.myform.button1.disabled=true;
			}
			});
		--%>
		   var actionStr= "rmsService?actionCode=addEqu"
		  			 + "&number=" + document.getElementById("number").value 
	        		 + "&name=" + document.getElementById("name").value
	        		 + "&remark=" + document.getElementById("remark").value;
	    	window.returnValue = actionStr;
		    window.close();	
	  	}
	  	else
	  	{
	  	  alert("����д�豸�������");
	  	}
	  }
	   //�رմ���
	   function down()
	  {
	  	window.close();
	  }
-->
</script>
</head>
<body>
<form method="post" action="" name="myform" >
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="5"></td></tr>
 </table>
 
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_ADD">
   <tr>
     <td height="25" class="TD_ADD"><div align="right">�豸�����ţ�</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="number" id="number" value="<%=strNumber%>" disabled></div></td>
     <td class="TD_ADD"><input onclick="inspect()" type="button" name="button1" value="����" class="BUTTON_STYLE1"></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">�豸������ƣ�</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="name" id="name"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></div><div id="checkOnly"></div></td>
     <td class="TD_ADD"><input onclick="down()" type="button" name="button2" value="ȡ��" class="BUTTON_STYLE1"></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">�豸���˵����</div></td>
     <td class="TD_ADD"><div align="left">
       <textarea name="remark" rows="2" id="remark"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></textarea>
     </div></td>
     <td class="TD_ADD"></td>
   </tr>
 </table>
 </form>
</body>
</html>