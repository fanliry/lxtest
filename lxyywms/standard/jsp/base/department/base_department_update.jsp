<%@ page contentType="text/html; charset=GBK"%>>
<%@ page import="com.wms3.bms.standard.entity.base.BaseDepartment" %>
<%
	BaseDepartment department = (BaseDepartment)request.getAttribute("department");  
	String departmentid = department.getDepartmentid();		// ���ű��
    String shortname = department.getShortname();			// ���ż��
    String departmentname = department.getDepartmentname();	// ����ȫ��
    String departmenttype = department.getDepartmenttype();	// ���ŷ���
    String contact = department.getContact();				// ��ϵ��
    String phone = department.getPhone();					// ��ϵ�绰
    String fax = department.getFax();						// ����
    String address = department.getAddress();				// ��ַ
%>
<html>
<head>
<title>�޸Ĳ�����Ϣ</title>
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
	//���沿����Ϣ
	function OnOk(){
		var id = document.getElementById("id").value;
		var shortname = document.getElementById("shortname").value;
		var departmentname = document.getElementById("departmentname").value;
		var departmenttype = document.getElementById("departmenttype").value;
		var contact = document.getElementById("contact").value;
		var phone = document.getElementById("phone").value;
		var fax = document.getElementById("fax").value;
		var address = document.getElementById("address").value;
		
		if(departmentname == null || departmentname.length <1)
		{
			alert("������ȫ�ơ�����Ϊ��!");
			return;
		}
		
		if(phone!=null && phone.length>0)
		{
			if(strlen(phone) > 20){
				alert("����ϵ�绰������!");
				return;
			}
		}
		
		if(fax!=null && fax.length>0)
		{
			if(strlen(fax) > 20){
				alert("�����桿����!");
				return;
			}
		}

		var back_msg = "&id=" + id + "&shortname=" + shortname + "&departmentname=" + departmentname + "&departmenttype=" + departmenttype 
			+ "&contact=" + contact + "&phone=" + phone + "&fax=" + fax + "&address=" + address;
		
    	window.returnValue = back_msg;
    	window.close();
	}
	
	function OnLoad(){
	
		var typevalue = "bmlx";	//�������͵�ֵ
		selectType("<%=departmenttype%>", "departmenttype", typevalue);
	}

-->
</script>
</head>

<body onload="OnLoad();">

  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�������Ϣ -&gt; ���Ź��� -&gt; �޸Ĳ�����Ϣ</div></td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right">���ż�ƣ�</td>
      <td class="xx1">
     	<input type="hidden" id="id" value="<%=departmentid%>">
     	<input type="text" name="shortname" maxlength="25" size="25" value="<%=shortname%>"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>����ȫ�ƣ�</td>
      <td class="xx1">
     	<input type="text" name="departmentname" maxlength="50" size="50" value="<%=departmentname%>"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">���ŷ��ࣺ</td>
      <td class="xx1"><select name="departmenttype"><option value=""></option></select></td>
    </tr>
    <tr>
      <td class="yx1" align="right">��ϵ�ˣ�</td>
      <td class="xx1"><input type="text" name="contact" maxlength="10" size="10" value="<%=contact%>"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">��ϵ�绰��</td>
      <td class="xx1"><input type="text" name="phone" maxlength="20" size="20" value="<%=phone%>"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">���棺</td>
      <td class="xx1"><input type="text" name="fax" maxlength="20" size="20" value="<%=fax%>"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">��ַ��</td>
      <td class="xx1"><input type="text" name="address" maxlength="50" size="50" value="<%=address%>"></td>
    </tr>
    <tr>
      <td height="27" align="center" colspan="2">
        <input type="button" onclick="OnOk()" name="add" value="&nbsp;&nbsp;&nbsp;����" class="button_add">&nbsp; 
        <input type="button" onClick="window.close()" name="resetBtn" value="�ر�" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
 
</body>
</html>