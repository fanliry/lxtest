<%@ page contentType="text/html; charset=GB2312"%>

<%
	//�����ظ��ύ�ı�־������beginΪ�״μ��أ�finishΪ���ύ
	session.setAttribute("submitFlag","begin");
	String strNumber=(String)request.getAttribute("number");
%>

<html>
<head>
<title>ж�ع��༭��Ϣ����</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">

<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script>
<!--
	  function onlo()
	  {
		  selectObject('','duty','17'); //���
	  }
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
				if(document.getElementById("remark").value.length>50)
				{
					alert("��ע���ܳ���50���ַ���");
					return false;
				}
		    var actionStr="BMSService?actionCode=addLabout"	
		  			 + "&number=" + document.getElementById("number").value 
	        		 + "&name=" + document.getElementById("name").value
	        		 + "&remark=" + document.getElementById("remark").value
	        		 + "&duty=" //+ document.getElementById("duty").value
	        		 + "&play=" + document.getElementById("play").value;
	    	window.returnValue = actionStr;
		    window.close();	
	  	}
	  	else
	  	{
	  	  alert("����дԱ������");
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

<body onload="">
<form method="post" action="" name="myform">
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="8"></td>
   </tr>
 </table>
 
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_ADD">
   <tr>
     <td width="26%" height="25" class="TD_ADD"><div align="right">���룺</div></td>
     <td width="53%" class="TD_ADD"><div align="left"><input type="text" name="number" id="number" value="<%=strNumber%>" disabled>
     </div></td>
     <td width="21%" class="TD_ADD"><input onClick="inspect()" type="button" name="add" value="ȷ��" class="BUTTON_STYLE1"></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">������</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="name" id="name"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></div></td>
     <td class="TD_ADD"><input onClick="down()" type="button" name="add" value="ȡ��" class="BUTTON_STYLE1"></td>
   </tr>
 <!--   <tr>
     <td height="25" class="TD_ADD"><div align="right">��Σ�</div></td>
     <td colspan="2" class="TD_ADD"><div align="left">       <select name="duty">
         <option  selected value=""> </option>
         <option value="�װ�">�װ�</option>
         <option value="�Ұ�">�Ұ�</option>
         <option value="����">����</option>
       </select>
     </div></td>
   </tr>-->
   <tr>
     <td height="25" class="TD_ADD"><div align="right">���ã�</div></td>
    <td colspan="2" class="TD_ADD"><div align="left">
      <select name="play">
        <option value="1">��</option>
        <option selected value="0">��</option>
      </select> 
         </div>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">������</div></td>
     <td colspan="2" class="TD_ADD"><div align="left">
     <textarea name="remark" id="remark" onKeyUp="if(checkValue(this.value))execCommand('undo')"></textarea>
     </div></td>
   </tr>
   
 </table>
 
</form>
</body>
</html>