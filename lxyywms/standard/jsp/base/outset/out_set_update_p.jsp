<%@ page contentType="text/html; charset=GB2312"%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
<!--
	//����
	function Save()
	{
		var out_type = document.getElementById("out_type").value;;
		var ids = "";
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++)
		{
			if(check_ids[i].checked)
			{
				ids += check_ids[i].value + "|";
			}
		}
		var backmsg = "&out_type=" + out_type + "&ids=" + ids;
		
		window.returnValue = backmsg;
		window.close();
	}
	//ҳ���½
	function OnLoad()
	{
		var getmsg = window.dialogArguments;
		var aem = getmsg.split("|");
		
		document.getElementById("out_type").value = aem[0];
		document.getElementById("out_type_name").value = aem[1];
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
     
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td class="font_006699_bold_12" height="25">��ǰλ�ã�������Ϣ -&gt; �������� -&gt; �޸Ŀ�����Ʒ״̬</td>
   </tr>
 </table>

 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_MGR_TOP">
   <tr>
     <td class="TD_MGR_TOP" colspan="3">
        ��ǰ���ã�<input type="hidden" name="out_type">
       <input type="text" name="out_type_name" class="text" size="20" style="background-color: #EFEFFD" readonly>
     </td>
   </tr>
   <tr>
     <td class="TD_MGR_TOP"><input type="checkbox" name="check_id" value="1" class="input_checkbox">����</td>
     <td class="TD_MGR_TOP"><input type="checkbox" name="check_id" value="2" class="input_checkbox">�ϸ�</td>
     <td class="TD_MGR_TOP"><input type="checkbox" name="check_id" value="3" class="input_checkbox">���ϸ�</td>
   </tr>
   <tr>
    <td class="TD_MGR_TOP" align="center" colspan="6">
      <input onClick="Save()" type="button" name="save" value="����" class="button">
      <input onClick="window.close()" type="button" name="report" value="�ر�" class="button">
    </td>
   </tr>
 </table>
   
</body>
</html>
     