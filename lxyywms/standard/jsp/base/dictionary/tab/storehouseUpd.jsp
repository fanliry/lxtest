<%@ page contentType="text/html; charset=GB2312"%>


<html>
<head>
<title>�ֹ�Ա�༭����</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script>
<!--
  function inspect()
  {
  	var name=document.getElementById("name").value;
  	var number=document.getElementById("number").value;
  	if(name!="")
  	{
  		if(name.length>15)
			{
				alert("���ܳ���15���ַ���");
				return false;
			}	
  	    var actionStr="BMSService?actionCode=updStore"	
	  			 + "&number=" + document.getElementById("number").value 
        		 + "&name=" + document.getElementById("name").value
        		 + "&use=" + document.getElementById("use").value
        		 + "&id=" + document.getElementById("id").value;
    	window.returnValue = actionStr;
	    window.close();	
  	}
  	else
  	{
  		alert("������ֹ�Ա����");
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

<%
	request.setCharacterEncoding("ISO-8859-1");
	String id=request.getParameter("id");
	String name=request.getParameter("name");
	String number=request.getParameter("number");
	String use=request.getParameter("use");
	
	name=new String(name.getBytes("ISO-8859-1"),"gb2312");
	number=new String(number.getBytes("ISO-8859-1"),"gb2312");
%>
<body>
<form method="post" action=""  name="myform">
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
    <td class="TD_ADD"><div align="left"><input type="hidden" name="id" id="id" value="<%=id%>"></div></td>
 </table>
 
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_ADD">
   <tr>
     <td height="25" class="TD_ADD"><div align="right">�ֹ�Ա��ţ�</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="number" id="number"  value="<%=number%>" disabled></div></td>
     <td class="TD_ADD"><input onclick="inspect()" type="button" name="add" value="�޸�" class="BUTTON_STYLE1"></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">�ֹ�Ա���ƣ�</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="name" id="name"  value="<%=name%>"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></div></td>
     <td class="TD_ADD"><input onclick="down()" type="button" name="add" value="ȡ��" class="BUTTON_STYLE1"></td>
   </tr>
    <tr>
    
     <td height="25" class="TD_ADD"><div align="right">��&nbsp;&nbsp;&nbsp;&nbsp;�ã�</div></td>
    <%
    	if(use.equals("0"))
    	{
    %>
        <td class="TD_ADD"><div align="left">
         <select name="use">
           <option value="1">��</option>
           <option selected value="0">��</option>
         </select>
          </div></td>
          <%
          }
          else
          {
          %>
        <td class="TD_ADD"><div align="left">
         <select name="use">
           <option selected value="1">��</option>
           <option  value="0">��</option>
         </select>
          </div></td>
          <%
          }
          %>
   </tr>
   
 </table>
 
 </form>
</body>
</html>