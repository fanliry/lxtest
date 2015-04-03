<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseWhareaVirtual" %>
<%
	BaseWhareaVirtual wharea = (BaseWhareaVirtual)request.getAttribute("whArea");  
	String whAreaId = wharea.getwhAreaId();				//����ID
	String whAreaName = wharea.getwhAreaName();			//������
	String warehousename = wharea.getWarehousename();	//�ֿ���
	String ERPCode = wharea.getERPCode();				//ERP����
	String ERPAccount = wharea.getERPAccount();
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

	//�����޸Ŀ�����Ϣ
	function OnOk(){
		var whAreaId   = document.getElementById("whAreaId").value;
		var whAreaName = document.getElementById("whAreaName").value;
		var ERPCode = document.getElementById("ERPCode").value;
		var ERPAccount = document.getElementById("ERPAccount").value;
		
		if(whAreaName == null || whAreaName.length <1)
		{
			alert("���������ơ�����Ϊ��!");
			return;
		}
		if(ERPAccount == null || ERPAccount.length < 1){
			alert("��ѡ��ERP���ס�!");
			return;
		}
		if(ERPCode == null || ERPCode.length < 1){
			alert("��ѡ��ERP���롿!");
			return;
		}
		var back_msg = "&whAreaId=" + whAreaId + "&whAreaName=" + whAreaName + "&ERPCode=" + ERPCode + "&ERPAccount=" + ERPAccount;
		
    	window.returnValue = back_msg;
    	window.close();
	}
	
	function OnLoad(){
	
	}

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
     <td width="150px" class="yx1" align="right"><span class="red">*</span>ERP���ף�</td>
     <td class="xx1"><input type="text" name="ERPAccount" maxlength="25" size="25" value="<%=ERPAccount%>" ></td>
   </tr> 
   <tr>
     <td width="150px" class="yx1" align="right"><span class="red">*</span>ERP���룺</td>
     <td class="xx1"><input type="text" name="ERPCode" maxlength="25" size="25"  value="<%=ERPCode%>" ></td>
   </tr> 
    <tr>
      <td class="yx1" align="right">�����ֿ⣺</td>
      <td class="xx1"><input type="text" id="warehousename" value="<%=warehousename%>" class="input_readonly" readonly></td>
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