<%@ page contentType="text/html; charset=GB2312"%>
<%
    //�����ظ��ύ�ı�־������beginΪ�״μ��أ�finishΪ���ύ
	session.setAttribute("submitFlag","begin");
	String strNumber=(String)request.getAttribute("number");
%>

<html>
<head>
<title>ʡ����Ϣ�༭����</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/table.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tools.js"></script>	
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/js/selectTool.js"></script>
<script type="text/javascript">

<!--
	  	function inspect()
	  	{
	  		var name=document.getElementById("name").value;
	  		if(name!=""){	
	  			if(name.length>15){
					alert("���ܳ���15���ַ���");
					return false;
				}	
				if(document.getElementById("name").value.length>50){
					alert("��ע���ܳ���15���ַ���");
					return false;
				}	
		    var actionStr="rmsService?actionCode=addProvince"	
		  			 + "&number=" + document.getElementById("number").value 
	        		 + "&name=" + document.getElementById("name").value
	        		 + "&remark=" + document.getElementById("remark").value;
	    	window.returnValue = actionStr;
		    window.close();	
	  	}else{
	  	  alert("����дʡ�ݵ�����");
	  	}
	  }
	   //�رմ���
	   function down(){
	  	window.close();
	  } 
  	//����ʡ��
	   function onlo(){
		selectType('','name','province');
		
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
     <td height="25" class="TD_ADD"><div align="right">ʡ�ݱ�ţ�</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="number" id="number" value="<%=strNumber%>" disabled></div></td>
     <td class="TD_ADD"><input onclick="inspect()" type="button" name="add" value="����" class="BUTTON_STYLE1"></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">ʡ�����ƣ�</div></td>
     <td width="30%" class="TD_MGR_TOP"><div align="left">
     <select name="name" id="name"  onKeyUp="if(checkValue(this.value))execCommand('undo')">
     	<option value=""></option>
     </select></div></td>
     <td class="TD_ADD"><input onclick="down()" type="button" name="add" value="ȡ��" class="BUTTON_STYLE1"></td>
   </tr>
    <tr>
     <td height="25" class="TD_ADD"><div align="right">��ע��</div></td>
     <td class="TD_ADD"><div align="left">       <textarea name="remark" id="remark"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></textarea>
     </div></td>
     <td class="TD_ADD"></td>
   </tr>
 </table>
 
 </form>
</body>
</html>