<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<title>�����豸��Ϣ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript">
<!--
	function OnOk(){
		var deviceno = document.getElementById("deviceno").value;		//�豸���
		var devicename = document.getElementById("devicename").value;	//�豸����
		var devicetype = document.getElementById("devicetype").value;	//�豸����
		var warehouseid = document.getElementById("warehouseid").value;	//�����ֿ�
		var whAreaId = document.getElementById("whAreaId").value;		//��������
		var belongto = document.getElementById("belongto").value;		//�������
		
		if(deviceno == null || deviceno.length <1)
		{
			alert("���豸��š�����Ϊ��!");
			return;
		}
		
		if(devicename == null || devicename.length <1)
		{
			alert("���豸���ơ�����Ϊ��!");
			return;
		}
		
		if(devicetype == null || devicetype.length <1)
		{
			alert("���豸���͡�����Ϊ��!");
			return;
		}
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("��ѡ�������ֿ⡿!");
			return;
		}
		
		if(whAreaId == null || whAreaId.length < 1){
			alert("��ѡ������������!");
			return;
		}

		var back_msg = "&deviceno=" + deviceno + "&devicename=" + devicename + "&devicetype=" + devicetype
			 + "&warehouseid=" + warehouseid + "&whAreaId=" + whAreaId + "&belongto=" + belongto;
		window.returnValue = back_msg;
    	window.close();
	}
	
	function OnLoad(){
		//ͬ��
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");
		DWREngine.setAsync(true);
		
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		
		var typevalue = "sblx";		//�豸���͵�ֵ	
		selectType("", "devicetype", typevalue);
	}
	
	//���ݲֿ��ÿ������б�
	function getWhAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
	}
	
	//���ݿ������������б�
	function getAlleyList(){
	
		var whAreaId = document.getElementById("whAreaId").value;
		selectAlleyList("", "belongto", whAreaId);
	}
-->
</script>
</head>

<body onload="OnLoad();">
  <form name="myForm" method="post" action="">
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�������Ϣ -&gt; �豸���� -&gt; �����豸��Ϣ</div></td>
    </tr>
  </table>
 
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right"><span class="red">*</span>�豸��ţ�</td>
      <td class="xx1"><input type="text" name="deviceno" maxlength="16"></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>�豸���ƣ�</td>
      <td class="xx1"><input type="text" name="devicename" size="25" maxlength="25"></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>�豸���ͣ�</td>
      <td class="xx1"><select name="devicetype"><option value=""></option></select></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>�����ֿ⣺</td>
      <td class="xx1"><select name="warehouseid" onChange="getWhAreaList();getAlleyList();"><option value=""></option></select></td>
    </tr>    
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>����������</td>
      <td class="xx1"><select name="whAreaId" onChange="getAlleyList()"><option value=""></option></select></td>
    </tr>
    <tr>
      <td class="yx1" align="right">���������</td>
      <td class="xx1"><select name="belongto"><option value=""></option></select></td>
    </tr>
    <tr>
      <td height="27" colspan="2" align="center">
        <input type="button" onclick="OnOk()" name="add" value="&nbsp;&nbsp;&nbsp;����" class="button_add">&nbsp; 
        <input type="reset" name="resetDetailBtn" value="&nbsp;&nbsp;&nbsp;����" class="button_reset">&nbsp;
        <input type="button" onClick="window.close()" name="resetBtn" value="�ر�" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
  </form>
  
</body>
</html>