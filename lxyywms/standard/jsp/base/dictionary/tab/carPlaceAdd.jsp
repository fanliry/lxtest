<%@ page contentType="text/html; charset=GB2312"%>

<%
	//�����ظ��ύ�ı�־������beginΪ�״μ��أ�finishΪ���ύ
	session.setAttribute("submitFlag","begin");
	String strNumber=(String)request.getAttribute("number");
%>
<html>
<head>
<title>��λ��Ϣ�༭����</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/table.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/js/tools.js"></script>
<script>
<!--
  function inspect()
  {
  	var name=document.getElementById("name").value;
  	if(name!="")
  	{ 	
  		if(name.length>15)
		{
			alert("���ܳ���15���ַ���");
			return false;
		}
		if(document.getElementById("row").value=="0")
		{
			alert("��������Ч����!");
			return false;
		}
		if(document.getElementById("line").value=="0")
		{
			alert("��������Ч����!");
			return false;
		}
		if(document.getElementById("laye").value=="0")
		{
			alert("��������Ч����!");
			return false;
		}
	   	var actionStr = "rmsService?actionCode=addPlace"
    			 + "&number=" + document.getElementById("number").value
        		 + "&name=" + document.getElementById("name").value
        		 + "&line=" +document.getElementById("line").value
        		 + "&row=" +document.getElementById("row").value
        		 + "&laye=" +document.getElementById("laye").value;
    	window.returnValue = actionStr;
    	window.close();	
  	}
  	else
  	{
  	  alert("���λ����");
  	}
  }
   function down()
  {
  	window.close();
  }
-->
</script>
</head>
<body>
<form method="post" action="" name="myform">
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="5"></td></tr>
 </table>
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_ADD">
   <tr>
     <td height="25" class="TD_ADD"><div align="right">��λ���룺</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="number" id="number" value="<%=strNumber%>" disabled></div></td>
     <td class="TD_ADD"><input onclick="inspect()" type="button" name="add" value="����" class="BUTTON_STYLE1" ></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">��λ����</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="name" id="name"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></div></td>
     <td class="TD_ADD"><input onclick="down()" type="button" name="add" value="ȡ��" class="BUTTON_STYLE1"></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">�ţ�</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="row" id="row"></div></td>

   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">�У�</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="line" id="line"></div></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">�㣺</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="laye" id="laye"></div></td>
   </tr>
 </table>
 </form>
</body>
</html>