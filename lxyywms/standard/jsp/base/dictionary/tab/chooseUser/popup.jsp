<%@ page language="java" pageEncoding="GB2312"%>

<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar.js"></script>	
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/table.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
<!--
	function query()
	{
	 	var userCode=document.getElementById('userCode').value;
	 	var userName = document.getElementById('username').value;
	 	var unitCode = document.getElementById('unitCode').value;
	  	window.myiframe.location.href='<%=request.getContextPath()%>/rmsService?actionCode=chooseUser&userCode='+userCode+'&userName='+userName+'&unitCode='+unitCode;
	}	
	function selectCheck()
	{
		var obj = window.myiframe.document.getElementsByName("radio");
		var strPram = null;
		if (obj==null)
		{
			 window.close();
		}
		for(var i=0;i< obj.length;i++)   
		{
		 	if(obj[i].checked)
			{
				strPram = obj[i].value;
			}
		}
		window.returnValue = strPram;
		window.close();
	} 
-->
</script>
</head>

<body>
  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td width="1%" height="27"></td>
      <td width="99%" class="font_006699_bold_12"><DIV class=font_006699_bold_12>��ǰλ�ã�������Ϣ -&gt; ��ι���</DIV></td>
    </tr>
  </table>
  
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr height="22">
      <td>�û����룺
        <input name="userCode" type="text" id="userCode"  size="15"></td>
      <td >�û�����
        <input name="username" type="text" id="username"  size="15"></td>
		 <td>���Ŵ��룺      </td>
	  <td><input name="unitCode" type="text" id="unitCode"  size="15"></td>
      <td><input type="button" name="button" class="BUTTON_STYLE1" value="��ѯ" onClick="query()"></td>
	  <td ><input type="button" name="button" class="BUTTON_STYLE1" value="ȷ��" onclick="selectCheck()"></td>
    </tr>
  </table>
  <table width="98%" height="80%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td>
	  <iframe id="myiframe" name="myiframe" width="100%" height="100%" frameborder="0" 
  	    	   src="<%=request.getContextPath()%>/jsp/dictionary/tab/chooseUser/list.jsp"></iframe>
	  </td>
    </tr>
  </table>
</body>
</html>