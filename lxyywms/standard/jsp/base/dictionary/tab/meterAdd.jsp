<%@ page contentType="text/html; charset=GB2312"%>

<%
	//�����ظ��ύ�ı�־������beginΪ�״μ��أ�finishΪ���ύ
	session.setAttribute("submitFlag","begin");
	String strNumber=(String)request.getAttribute("number");
%>

<html>
<head>
<title>������λ�༭����</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
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
				alert("���ܳ���15���ַ���");
				return false;
			}
	   var actionStr="BMSService?actionCode=addMeter"
	  			 + "&number=" + document.getElementById("number").value 
        		 + "&name=" + document.getElementById("name").value
    	window.returnValue = actionStr;
	    window.close();	
  	}
  	else
  	{
  	  alert("����д��λ����");
  	}
  }
  //���Ψһ
   function check(){
		  var name=document.getElementById("name").value;
	  	//����������Ƿ�Ψһ
			inspectClass.checkMeterName(name,function(data){
			if(data=="0"){
			dwr.util.setValue("checkOnly","�˵�λ���Ѿ����ڣ�");
			document.myform.button1.disabled=true;
			}
			if(data=="1"){
			dwr.util.setValue("checkOnly","�˵�λ����δʹ�ã�");
			document.myform.button1.disabled=false;
			}
 		 });
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
<form method="post" action="" name="myform">
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="5"></td></tr>
 </table>
 
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_ADD">
   <tr>
     <td height="25" class="TD_ADD"><div align="right">��λ��ţ�</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="number" id="number" value="<%=strNumber%>" disabled></div></td>
     <td class="TD_ADD"><input onclick="inspect()" type="button" name="button1" value="����" class="BUTTON_STYLE1"></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">��λ���ƣ�</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="name" id="name" onchange="check()"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></div>
		<font color="#FF0000"><div id="checkOnly"> </div></font></td>
     <td class="TD_ADD"><input onclick="down()" type="button" name="button2" value="ȡ��" class="BUTTON_STYLE1"></td>
   </tr>
   
 </table>
 
 </form>
</body>
</html>