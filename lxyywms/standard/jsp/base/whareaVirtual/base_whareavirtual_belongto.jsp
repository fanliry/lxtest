<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseWhareaVirtual" %>
<%
    String warehouseid = (String)session.getAttribute("warehouseid");//�ֿ�ID
    BaseWhareaVirtual wharea = (BaseWhareaVirtual)request.getAttribute("whArea");  
	String whAreaId = wharea.getwhAreaId();				//����ID
	String whAreaName = wharea.getwhAreaName();			//������
	String warehousename = wharea.getWarehousename();	//�ֿ���
	warehouseid = wharea.getWarehouseid();
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
		var whAreaIdBe = document.getElementById("whAreaIdBe").value;
		if(whAreaName == null || whAreaName.length <1)
		{
			alert("���������ơ�����Ϊ��!");
			return;
		}
		if(whAreaIdBe == null || whAreaIdBe.length <1)
		{
			alert("�������ڿ���������Ϊ��!");
			return;
		}
		var back_msg = "&whAreaId=" + whAreaId + "&whAreaIdBelongTo=" + whAreaIdBe;
		
    	window.returnValue = back_msg;
    	window.close();
	}
	function OnLoad(){
		//����
		selectAreaList("", "whAreaIdBe", "<%=warehouseid%>", "1");
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
     	<input type="text" id="whAreaName" maxlength="25" size="25" class="input_readonly" readonly value="<%=whAreaName%>">
      </td>
    </tr> 
    <tr>
      <td class="yx1" align="right">�����ֿ⣺</td>
      <td class="xx1"><input type="text" id="warehousename" value="<%=warehousename%>" class="input_readonly" readonly size="25"></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>�����ڿ�����</td>
      <td class="xx1"><div class="select"><select id="whAreaIdBe" style="width:160px"><option value=""></option></select></div></td>
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