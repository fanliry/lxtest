<%@ page contentType="text/html; charset=GB2312"%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
	//��������Ƿ�Ϊ����
	function IsNum(num){ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	} 
	//ȷ��
	function Ok(){
		var priority = document.getElementById("priority").value;
		
		if(priority == null || priority.length < 1 || !IsNum(priority) || priority > 10 || priority < 1){
			alert("�����ȼ�������Ϊ����ֻ��Ϊ1-10�����֣�");
			return null;
		}
		window.returnValue = priority;
		window.close();
	}
-->
</script>
</head>

<body>

 <table width="98%" height="25" align="center" border="0" cellpadding="0" cellspacing="0">
   <tr>
     <td class="font_006699_bold_12">��ǰλ�ã���ҵ���� -&gt; �趨���ȼ�</td>
   </tr>
 </table>
 <p>
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td class="yx1"><div align="right"><span class="red">*</span>���ȼ���</div></td>
     <td class="xx1"><input type="text" name="priority" size="15"></td>
   </tr>
   <tr>
     <td align="center" colspan="2">
       <input type="button" class="BUTTON_STYLE1" value="ȷ��" onClick="Ok()">
       <input type="button" class="BUTTON_STYLE1" value="ȡ��" onClick="window.close()">
     </td>
   </tr>
 </table>
 <p>
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
   <tr>
     <td style="color: red;font-size:12px" height="25">��ʾ��Ϣ�����ȼ�ֻ��Ϊ1-10�����֣�����ԽС���ȼ�Խ�ߡ�</td>
   </tr>
 </table>
 
</body>
</html>