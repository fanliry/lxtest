<%@ page contentType="text/html; charset=GBK"%>

<html>

<head>
<title>�Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
<script type="text/javascript">

	//ҳ���¼
	function OnLoad()
	{
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != "")
		{
			if(back_msg == "Y"){
				alert("�����ɹ���");
			}else{
				alert(back_msg);
			}
		}
	}
</script>
</head>

<body onload="OnLoad()">
<div style="width: 100%; height: 100%; overflow:auto;">
 <table width="100%" border="0" align="center" id="puttb" cellpadding="0" cellspacing="0" class="MAIN_TABLE">
   <tr>
   	 <td class="TD_LIST_TITLE" align="center" >���ٺ�</td>
     <td class="TD_LIST_TITLE" align="center" >Ʒ��</td>
     <td class="TD_LIST_TITLE" align="center" >����</td>
     <td class="TD_LIST_TITLE" align="center" >����</td>
  </tr>

 </table>
</div>

</body>
</html>