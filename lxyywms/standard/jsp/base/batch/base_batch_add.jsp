<%@ page contentType="text/html; charset=GBK"%>

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
	function IsNum(num)
	{ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
	
	//����
	function OnOk()
	{
		var batch_name = document.getElementById("batch_name").value;
		var length = document.getElementById("length").value;
		var use_flag = document.getElementById("use_flag").checked?"Y":"N";
		
		if(batch_name == null || batch_name.length < 1)
		{
			alert("���������ơ�����Ϊ�գ�");
			return;
		}	
		if(length == null || length.length < 1 || !IsNum(length))
		{
			alert("�����ȡ�����Ϊ�ջ�ֻ��Ϊ����0С��100�����֣�");
			return;
		}	
		var backmsg = "&batch_name=" + batch_name + "&length=" + length + "&use_flag=" + use_flag;
		window.returnValue = backmsg;
		window.close();
	}
-->
</script>
</head>

<body>
  <form name="myForm" method="post" action=""> 
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td class="font_006699_bold_12" height="25">��ǰλ�ã�������Ϣ -&gt; ���ι��� -&gt; ���������Ϣ</td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right"><span class="red">*</span>�������ƣ�</td>
      <td class="xx1"><input type="text" name="batch_name" size="15"></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>���ȣ�</td>
      <td class="xx1"><input type="text" name="length" size="15" maxlength="2"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">���ã�</td>
      <td class="xx1"><input type="checkbox" name="use_flag" class="input_checkbox" checked></td>
    </tr>
    <tr>
      <td align="center" colspan="2">
        <input type="button" onclick="OnOk()" name="add" value="&nbsp;&nbsp;&nbsp;����" class="button_add">&nbsp; 
        <input type="reset" name="resetDetailBtn" value="&nbsp;&nbsp;&nbsp;����" class="button_reset">&nbsp;
        <input type="button" onClick="window.close()" name="resetBtn" value="�ر�" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
  </form>   
  
</body>
</html>
