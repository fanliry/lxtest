<%@ page contentType="text/html; charset=GB2312"%>



<html>
<head>
<title>��λ��Ϣ�༭����</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script>
<!--
 function update()
  {
  	var name=document.getElementById("name").value;
  	if(name!="")
  	{ 	
  		if(name.length>15)
		{
			alert("���ܳ���15���ַ���");
			return false;
		}
		if(document.getElementById("remark").value=="0")
		{
			alert("��������Ч����!");
			return false;
		}
	   	var actionStr = "BMSService?actionCode=updWorkshop"
	   			 + "&id=" + document.getElementById("id").value
    			 + "&number=" + document.getElementById("number").value
        		 + "&name=" + document.getElementById("name").value
        		 + "&remark=" +document.getElementById("remark").value;
        		 
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
 <% 
     request.setCharacterEncoding("ISO-8859-1");
  	 String id=request.getParameter("id");
 	 String name=request.getParameter("name");
 	 String number=request.getParameter("number");
 	 String remark=request.getParameter("remark");
 	 name = new String(name.getBytes("ISO-8859-1"),"gb2312");
  %>
<body>
<form method="post" action="" name="myform">
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
      <td class="TD_ADD"><div align="left"><input type="hidden" name="id" id="id" value="<%=id%>"></div></td>
 </table>
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_ADD">
   <tr>
     <td height="25" class="TD_ADD" ><div align="right">��λ���룺</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="number" id="number" value="<%=number%>" disabled></div></td>
     <td class="TD_ADD"><input onclick="update()" type="button" name="add" value="�޸�" class="BUTTON_STYLE1" ></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">��λ����</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="name" id="name" value="<%=name%>"></div></td>
     <td class="TD_ADD"><input onclick="down()" type="button" name="add" value="ȡ��" class="BUTTON_STYLE1"></td>
   </tr>
     <tr>
     <td height="25" class="TD_ADD"><div align="right">��ע��</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="remark" id="remark" value="<%=remark%>"></div></td>
   </tr>
  
 </table>
 </form>
</body>
</html>