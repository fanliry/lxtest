<%@ page contentType="text/html; charset=GB2312"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<title>��Ϣ����Ϣ�༭����</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>css/table.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/js/tools.js"></script>
<script>
<!--
	  function update()
	  {
	  	//�绰������֤
		var TEL=document.getElementById("phone").value;
	  	var i,j,strTemp;     
		strTemp="0123456789-()# ";     
		for (i=0;i<TEL.length;i++)     
		{     
			j=strTemp.indexOf(TEL.charAt(i));     
			if (j==-1)     
			{         
				alert('������ĵ绰��������');
				return false;     
			}     
		}   
	  	var name=document.getElementById("name").value;
	  	if(name!="")
	  	{
	  		if(name.length>15)
			{
				alert("���ܳ���15���ַ���");
				return false;
			}
	       var actionStr="rmsService?actionCode=updComm"
	       		     + "&person=" + document.getElementById("person").value
		  			 + "&number=" + document.getElementById("number").value 
	        		 + "&name="+document.getElementById("name").value
	        		 + "&id=" + document.getElementById("id").value
	        		 + "&phone=" + document.getElementById("phone").value
	        		 + "&remark=" + document.getElementById("remark").value;
	    	window.returnValue = actionStr;
		    window.close();
	  	}else
	  	{
	  	  alert("����д��Ϣ��������");
	  	}
	  }
	  function down()
	  {
	  	window.close();
	  }
-->
</script>
<%
	    request.setCharacterEncoding("ISO-8859-1");
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String number=request.getParameter("number");
		String person=request.getParameter("person");
		String phone=request.getParameter("phone");
		String remark=request.getParameter("remark"); 
		remark=new String(remark.getBytes("ISO-8859-1"),"gb2312");
		name=new String(name.getBytes("ISO-8859-1"),"gb2312");
		person=new String(person.getBytes("ISO-8859-1"),"gb2312");
%>
</head>
<body>
	<form method="post" action="" name="myform">
		 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
		   <tr>
		     <td class="TD_ADD"><div align="left"><input type="hidden" name="id" id="id" value="<%=id%>"></div></td>
		 </table>
		 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_ADD">
		   <tr>
		     <td height="25" class="TD_ADD"><div align="right">��Ϣ����ţ�</div></td>
		     <td class="TD_ADD"><div align="left"><input type="text" name="number" id="number" value="<%=number%>" disabled></div></td>
		     <td class="TD_ADD"><input onclick="update()" type="button" name="add" value="�޸�" class="BUTTON_STYLE1" ></td>
		   </tr>
		   <tr>
		     <td height="25" class="TD_ADD"><div align="right">��Ϣ�����ƣ�</div></td>
		     <td class="TD_ADD"><div align="left"><input type="text" name="name" id="name" value="<%=name%>"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></div></td>
		     <td class="TD_ADD"><input onclick="down()" type="button" name="add" value="ȡ��" class="BUTTON_STYLE1"></td>
		   </tr>
		   <tr>
		     <td height="25" class="TD_ADD"><div align="right">�����ˣ�</div></td>
		     <td class="TD_ADD"><div align="left"><input type="text" name="person" id="person" value="<%=person%>"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></div></td>
		     <td class="TD_ADD"></td>
		   </tr>
		   <tr>
		     <td height="25" class="TD_ADD"><div align="right">�绰��</div></td>
		     <td class="TD_ADD"><div align="left"><input type="text" name="phone" id="phone" value="<%=phone%>"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></div></td>
		     <td class="TD_ADD"></td>
		   </tr>
		   <tr>
		     <td height="25" class="TD_ADD"><div align="right">��ע��</div></td>
		     <td class="TD_ADD"><div align="left"><input type="text" name="remark" id="remark" value="<%=remark%> "  onKeyUp="if(checkValue(this.value))execCommand('undo')"></div></td>
		     <td class="TD_ADD"></td>
		   </tr>
		 </table>
	 </form>
</body>
</html>