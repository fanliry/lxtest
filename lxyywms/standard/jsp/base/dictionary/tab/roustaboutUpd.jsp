<%@ page contentType="text/html; charset=GB2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%

	request.setCharacterEncoding("ISO-8859-1");
	String id=request.getParameter("id");
	String name=request.getParameter("name");
	String number=request.getParameter("number");
	String pwd=request.getParameter("pwd");
	String duty=request.getParameter("duty");
	String remark=request.getParameter("remark");
	
	remark=new String(remark.getBytes("ISO-8859-1"),"gb2312");
	name=new String(name.getBytes("ISO-8859-1"),"gb2312");
	pwd=new String(pwd.getBytes("ISO-8859-1"),"gb2312");
	duty=new String(duty.getBytes("ISO-8859-1"),"gb2312");	
%>

<html>
<head>
<title>���̹���Ϣ�༭����</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>css/table.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/js/tools.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/js/selectTool.js"></script>
<script>
<!--
	function onlo()
	{
		selectObject('<%=duty%>','duty','17'); //���
	}
  function inspect()
  {
  	var name=document.getElementById("name").value;
  	var pwd=document.getElementById("pwd").value;
  	if(name!=""&&pwd!="")
  	{	
    	if(name.length>15)
		{
				alert("���ܳ���15���ַ���");
				return false;
		}	
		if(document.getElementById("remark").value.length>15)
		{
				alert("���ܳ���15���ַ���");
				return false;
		}	
	    var actionStr="rmsService?actionCode=updRoust"
	  			 + "&number=" + document.getElementById("number").value  
        		 + "&remark=" + document.getElementById("remark").value
        		 + "&duty=" //+ document.getElementById("duty").value
        		 + "&name=" + document.getElementById("name").value
        		 + "&pwd=" + document.getElementById("pwd").value
        		 + "&id=" + document.getElementById("id").value;
    	window.returnValue = actionStr;
	    window.close();	
  	}
  	else
  	{
  	  alert("������������Ϣ��");
  	}
  }
  
  function down()
  {
  	window.close();
  }
-->
</script>
</head>


<body onload="">
<form method="post" action="" name="myform">
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
    <td class="TD_ADD"><div align="left"><input type="hidden" name="id" id="id" value="<%=id%>"></div></td>
 </table>
 
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_ADD">
   <tr>
     <td width="15%" height="25" class="TD_ADD"><div align="right">��ţ�</div></td>
     <td width="66%" class="TD_ADD"><div align="left"><input type="text" name="number" id="number" value="<%=number%>" disabled></div></td>
     <td width="19%" class="TD_ADD"><input onclick="inspect()" type="button" name="add" value="�޸�" class="BUTTON_STYLE1"></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">���ƣ�</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="name" id="name" value="<%=name%>"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></div></td>
     <td class="TD_ADD"><input onclick="down()" type="button" name="add" value="ȡ��" class="BUTTON_STYLE1"></td>
   </tr>
    <tr>
     <td height="25" class="TD_ADD"><div align="right">���룺</div></td>
     <td class="TD_ADD"><div align="left"><input type="password" name="pwd" id="pwd" value="<%=pwd%>"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></div></td>
     <td class="TD_ADD"></td>
   </tr>
   <!--  <tr>
     <td height="25" class="TD_ADD"><div align="right">��Σ�</div></td>
     <td class="TD_ADD"><div align="left">
       <select name="duty">
         <option  value=""><%=duty%></option>
       </select>
</div></td>
     <td class="TD_ADD"></td>
   </tr>-->
   <tr>
     <td height="25" class="TD_ADD"><div align="right">������</div></td>
    <td class="TD_ADD">
         <textarea name="remark" id="remark"  onKeyUp="if(checkValue(this.value))execCommand('undo')"><%=remark%></textarea>
   </tr>
   
 </table>
 
</form>
</body>
</html>