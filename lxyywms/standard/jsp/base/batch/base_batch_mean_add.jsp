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
	var batch_id;
	var length;
	
	//��������Ƿ�Ϊ����
	function IsNum(num)
	{ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}

	//����
	function Save(){
		var mean = document.getElementById("mean").value;
		var start_pos = document.getElementById("start_pos").value;
		var end_pos = document.getElementById("end_pos").value;
		
		if(mean == null || mean.length < 1){
			alert("���������ơ�����Ϊ�գ�");
			return;
		}
		if(start_pos == null || start_pos.length < 1 || !IsNum(start_pos)){
			alert("����ʼλ�á�����Ϊ�ջ�ֻ��Ϊ�����ұ���С�ڡ�"+length+"����");
			return;
		}	
		if(end_pos == null || end_pos.length < 1 || !IsNum(end_pos) || end_pos>length){
			alert("������λ�á�����Ϊ�ջ�ֻ��Ϊ�����ұ���С�ڡ�"+length+"��!");
			return;
		}
		start_pos = parseInt(start_pos);
		end_pos = parseInt(end_pos);
		if(start_pos > end_pos){
			alert("����ʼλ�á�����С�ڡ�����λ�á���");
			return;
		}
		
		var backmsg = "&batch_id=" + batch_id + "&mean=" + mean + "&start_pos=" + start_pos + "&end_pos=" + end_pos;
				
		window.returnValue = backmsg;
		window.close();
	}
	//ҳ���½
	function OnLoad(){
		var value = window.dialogArguments;
		var tem = value.split(",");
		batch_id = tem[0];
		length = parseInt(tem[1]);
		document.getElementById("batch_id").value = batch_id;
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
     
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td class="font_006699_bold_12" height="25">��ǰλ�ã�������Ϣ -&gt; ���ι��� -&gt; �����������</td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td class="yx1" align="right">���δ��룺</td>
      <td class="xx1"><input type="text" name="batch_id" size="15" class="input_readonly" readonly></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>�������壺</td>
      <td class="xx1"><input type="text" name="mean" size="15"></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>��ʼλ�ã�</td>
      <td class="xx1"><input type="text" name="start_pos" size="15" maxlength="2"></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>����λ�ã�</td>
      <td class="xx1"><input type="text" name="end_pos" size="15" maxlength="2"></td>
    </tr>
    <tr>
      <td align="center" colspan="2">
        <input onClick="Save()" type="button" name="save" value="����" class="BUTTON_STYLE1">
        <input onClick="window.close()" type="button" name="report" value="�ر�" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
   
</body>
</html>
