<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseWharea" %>
<%
	BaseWharea wharea = (BaseWharea)request.getAttribute("whArea");  
	String whAreaId = wharea.getwhAreaId();				//����ID
	String whAreaName = wharea.getwhAreaName();			//������
	String warehousename = wharea.getWarehousename();	//�ֿ���
	String whAreaType = wharea.getwhAreaType();			//��������
	String istask = wharea.getIstask();					//�Ƿ���Ҫ���ɵ�������
%>
<html>
<head>
<title>�޸Ŀ�����Ϣ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript">
<!--
	//�����޸Ŀ�����Ϣ
	function OnOk(){
		var whAreaId   = document.getElementById("whAreaId").value;
		var whAreaName = document.getElementById("whAreaName").value;
		var whAreaType = document.getElementById("whAreaType").value;
		var environment = document.getElementById("environment").value;
		var istask = document.getElementById("istask").checked==true?"Y":"N";
		
		if(whAreaName == null || whAreaName.length <1)
		{
			alert("���������ơ�����Ϊ��!");
			return;
		}
		if(whAreaType == null || whAreaType.length <1)
		{
			alert("���������͡�����Ϊ��!");
			return;
		}
		if(environment == null || environment.length <1)
		{
			alert("���洢����������Ϊ��!");
			return;
		}

		var back_msg = "&whAreaId=" + whAreaId + "&whAreaName=" + whAreaName + "&whAreaType=" + whAreaType  + "&environment=" + environment  + "&istask=" + istask;
		
    	window.returnValue = back_msg;
    	window.close();
	}
	
	function OnLoad(){
	
		var typevalue = "kqlx";	//�ֿ����͵�ֵ
		selectType("<%=whAreaType%>", "whAreaType", typevalue);
		
		selectType("", "environment", "cchj");//�洢����
	}
-->
</script>
</head>

<body onload="OnLoad();">

  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�������Ϣ -&gt; �������� -&gt; �޸Ŀ�����Ϣ</div></td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="150px" class="yx1" align="right"><span class="red">*</span>�������ƣ�</td>
      <td class="xx1">
     	<input type="hidden" id="whAreaId" value="<%=whAreaId%>">
     	<input type="text" id="whAreaName" maxlength="25" size="25" value="<%=whAreaName%>"><font color="red">*</font>
      </td>
    </tr> 
    <tr>
      <td class="yx1" align="right">�����ֿ⣺</td>
      <td class="xx1"><input type="text" id="warehousename" value="<%=warehousename%>" class="input_readonly" readonly></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>�������ͣ�</td>
      <td class="xx1"><select name="whAreaType"><option value=""></option></select> <font color="red">*</font></td>
    </tr>
     <tr>
     <td class="yx1" align="right"><span class="red">*</span>�洢������</td>
     <td class="xx1"><select name="environment"><option value=""></option></select></td>
   </tr>
    
    
    <tr>
     <td class="yx1" align="right">�Ƿ����ɵ�������</td>
     <td class="xx1"><input type="checkbox" name="istask" class="input_checkbox" <%if(istask.equals("Y")){%>checked<%}%>></td>
   </tr>
    <tr>
      <td align="center" colspan="2">
        <input type="button" onclick="OnOk()" name="add" value="&nbsp;&nbsp;&nbsp;����" class="button_add">&nbsp; 
        <input type="button" onClick="window.close()" name="resetBtn" value="�ر�" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
 
</body>
</html>