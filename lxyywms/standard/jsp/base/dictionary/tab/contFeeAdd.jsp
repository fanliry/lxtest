<%@ page contentType="text/html; charset=GB2312"%>

<%
	//�����ظ��ύ�ı�־������beginΪ�״μ��أ�finishΪ���ύ
	session.setAttribute("submitFlag","begin");
	String strNumber=(String)request.getAttribute("number");
%>
<html>
<head>
<title>��װ�������Ϣ�༭����</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/table.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/js/selectTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script src="<%=request.getContextPath()%>/js/tools.js"></script>
<script>
<!--
	
	function inspect()
	{
	  	var name=document.getElementById("city").value;
	  	if(name!="")
	  	{
	  		if(name.length>15)
			{
				alert("���ܳ���15���ַ���");
				return false;
			}
			if(document.getElementById("type").value=="")
			{
				alert("��ѡ���ͺţ�");
				return false;
			}
	       var actionStr="rmsService?actionCode=addFee"
		  			 + "&number=" + document.getElementById("number").value 
	        		 + "&mileage="+document.getElementById("mileage").value
	        		 + "&type=" + document.getElementById("type").value
	        		 + "&fee=" + document.getElementById("fee").value
	        		 + "&city=" + document.getElementById("city").value;
	        		 
	    	window.returnValue = actionStr;
		    window.close();
	  	}else
	  	{
	  	  alert("�����뵽����е�����");
	  	}
	  }
	  function down()
	  {
	  	window.close();
	  }
	  
	  function onlo()
	  {
	  	 selectObject('','type','8');//���
	  }
	    function selectCity()
	  {
	  	var strUrl = "<%=request.getContextPath()%>/jsp/dictionary/tab/chooseArea.jsp";
		var  result = showModalDialog(strUrl,'','dialogWidth=150px;dialogHeight=400px');
		if(result != null && result.length > 1){
			document.getElementById("city").value=result;
		}
	  }
-->
</script>
</head>

<body onload="onlo()">
<form method="post" action="" name="myform">
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="5"></td></tr>
 </table>
 
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_ADD">
   <tr>
     <td height="25" class="TD_ADD"><div align="right">���룺</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="number" id="number" value="<%=strNumber%>" disabled></div></td>
     <td class="TD_ADD"><input onclick="inspect()" type="button" name="add" value="����" class="BUTTON_STYLE1"></td>
   </tr>
    <tr>
        <td height="25" class="TD_ADD"><div align="right">����أ�</div></td>
	     <td class="TD_ADD">
		     <div align="left"><input type="text" name="city" id="city" value=""><input type="button" value="..." onclick="selectCity()"  class="BUTTON_STYLE1"></div>
	     </td>
		 <td class="TD_ADD"><input onclick="down()" type="button" name="add" value="ȡ��" class="BUTTON_STYLE1"></td>
		
   </tr>
    <tr>
     <td height="25" class="TD_ADD"><div align="right">��װ�����ͣ�</div></td>
     <td class="TD_ADD"><div align="left">
       <select id="type" name="type">
         <option value=""></option>
       </select>
     </div></td>
     <td class="TD_ADD"></td>
   </tr>
    <tr>
     <td height="25" class="TD_ADD"><div align="right">��װ��ѣ�</div></td>
     <td class="TD_ADD"><div align="left">
       <input type="text" name="fee" id="fee">
     </div></td>
     <td class="TD_ADD"></td>
   </tr>
    <tr>
     <td height="25" class="TD_ADD"><div align="right">��̣�</div></td>
     <td class="TD_ADD"><div align="left">
       <input type="text" name="mileage" id="mileage">
     </div></td>
     <td class="TD_ADD"></td>
   </tr>
 </table>
</form>
</body>
</html>