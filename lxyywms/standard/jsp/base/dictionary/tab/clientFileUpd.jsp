<%@ page contentType="text/html; charset=GB2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>�ͻ�������Ϣ�༭����</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>css/table.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/js/tools.js"></script>
<script>
<!--
	  function inspect()
	  {
	  	var name=document.getElementById("clientName").value;
	  	if(name!="")
	  	{
	  		if(name.length>15)
			{
				alert("���ܳ���15���ַ���");
				return false;
			}
		     var actionStr = "rmsService?actionCode=updFile"
	        		 + "&ratePayingNumber="+document.getElementById("ratePayingNumber").value
	        		 + "&accountNumber=" + document.getElementById("accountNumber").value
	    			 + "&clientNumber=" + document.getElementById("clientNumber").value
	        		 + "&clientName=" + document.getElementById("clientName").value
	        		 + "&fullName=" + document.getElementById("fullName").value
	        		 + "&phone=" + document.getElementById("phone").value
	        		 + "&fax=" + document.getElementById("fax").value
	        		 + "&id=" + document.getElementById("id").value	 
	        		 + "&reveal=" + document.getElementById("reveal").value
	        		 + "&linkman=" + document.getElementById("linkman").value
	        		 + "&address=" +    document.getElementById("address").value
	        		 + "&userNumber=" + document.getElementById("userNumber").value
	        		 + "&accountBrank=" + document.getElementById("accountBrank").value
	        		 + "&criterionSort=" + document.getElementById("criterionSort").value;
	    	window.returnValue = actionStr;
	    	window.close();
	  	}
	  	else
	  	{
	  	  alert("����д�û����");
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
			String clientNumber=request.getParameter("clientNumber");
			String userNumber=request.getParameter("userNumber");
			String clientName=request.getParameter("clientName");
			String criterionSort=request.getParameter("criterionSort");
			String fullName=request.getParameter("fullName");
			String linkman=request.getParameter("linkman");
			String phone=request.getParameter("phone");
			String fax=request.getParameter("fax");
			String address=request.getParameter("address");
			String accountBrank=request.getParameter("accountBrank");
			String accountNumber=request.getParameter("accountNumber");
			String ratePayingNumber=(request.getParameter("ratePayingNumber"));
			String reveal=request.getParameter("reveal");
			
			clientNumber=new String(clientNumber.getBytes("ISO-8859-1"),"gb2312");
			clientName=new String(clientName.getBytes("ISO-8859-1"),"gb2312");
			criterionSort=new String(criterionSort.getBytes("ISO-8859-1"),"gb2312");
			fullName=new String(fullName.getBytes("ISO-8859-1"),"gb2312");
			linkman=new String(linkman.getBytes("ISO-8859-1"),"gb2312");
			fax=new String(fax.getBytes("ISO-8859-1"),"gb2312");
			address=new String(address.getBytes("ISO-8859-1"),"gb2312");
			accountBrank=new String(accountBrank.getBytes("ISO-8859-1"),"gb2312");

%>
<body>
<form method="post" action="" name="myform">
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
    <td width="50%" class="TD_ADD"><div align="left"><input type="hidden" name="id" id="id" value="<%=id%>">
   </tr>
 </table>
 
<table width="46%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_ADD">
   <tr>
     <td width="26%" height="23" class="TD_ADD"><div align="right">�ͻ���ţ�</div></td>
     <td width="50%" class="TD_ADD"><div align="left"><input type="text" name="clientNumber" id="clientNumber" value="<%=clientNumber%>" disabled>
     </div></td>
     <td width="24%" class="TD_ADD"><input onclick="inspect()" type="button" name="add" value="�޸�" class="BUTTON_STYLE1" ></td>
   </tr>
   <tr>
     <td height="23" class="TD_ADD"><div align="right">�ͻ���ƣ�</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="clientName" id="clientName" value="<%=clientName%>">
     </div></td>
     <td class="TD_ADD"><input onclick="down()" type="button" name="add" value="ȡ��" class="BUTTON_STYLE1"></td>
   </tr>
   <tr>
     <td height="18" class="TD_ADD"><div align="right">�ͻ�ȫ�ƣ�</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="fullName" id="fullName" value="<%=fullName%>">
     </div></td>
     <td class="TD_ADD"></td>
   </tr>
   <tr>
     <td height="18" class="TD_ADD"><div align="right">��ϵ�ˣ�</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="linkman" id="linkman" value="<%=linkman%>">
     </div></td>
     <td class="TD_ADD"></td>
   </tr>
   <tr>
     <td height="18" class="TD_ADD"><div align="right">��ϵ�绰��</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="phone" id="phone" value="<%=phone%>">
     </div></td>
     <td class="TD_ADD"></td>
   </tr>
   <tr>
     <td height="18" class="TD_ADD"><div align="right">���棺</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="fax" id="fax" value="<%=fax%>">
     </div></td>
     <td class="TD_ADD"></td>
   </tr>
   <tr>
     <td height="18" class="TD_ADD"><div align="right">��ַ��</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="address" id="address" value="<%=address%>">
     </div></td>
     <td class="TD_ADD"></td>
   </tr>
   <tr>
     <td height="18" class="TD_ADD"><div align="right">�������У�</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="accountBrank" id="accountBrank" value="<%=accountBrank%>">
     </div></td>
     <td class="TD_ADD"></td>
   </tr>
   <tr>
     <td height="18" class="TD_ADD"><div align="right">�����˺ţ�</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="accountNumber" id="accountNumber" value="<%=accountNumber%>">
     </div></td>
     <td class="TD_ADD"></td>
   </tr>
    <tr>
     <td height="18" class="TD_ADD"><div align="right">��˰�ţ�</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="ratePayingNumber" id="ratePayingNumber" value="<%=ratePayingNumber%>">
     </div></td>
     <td class="TD_ADD"></td>
   </tr>
    <tr>
     <td height="18" class="TD_ADD"><div align="right">���ѱ��룺</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="userNumber" id="userNumber" value="<%=userNumber%>">
     </div></td>
     <td class="TD_ADD"></td>
   </tr>
    <tr>
     <td height="18" class="TD_ADD"><div align="right">��׼���ࣺ</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="criterionSort" id="criterionSort" value="<%=criterionSort%>">
     </div></td>
     <td class="TD_ADD"></td>
   </tr>
    <tr>
     <td height="18" class="TD_ADD"><div align="right">�Ƿ����ã�</div></td>
     <td class="TD_ADD"><div align="left">
     <%if(reveal.equals("0")){%>
       <select name="reveal" id="reveal">
         <option value="1">��</option>
         <option value="0" selected>��</option>
       </select>
       <%}else{%>
       <select name="reveal" id="reveal">
         <option value="1" selected>��</option>
         <option value="0">��</option>
       </select>
       <%}%></div></td>
     <td class="TD_ADD"></td>
   </tr>
 </table>
</form>
</body>
</html>
