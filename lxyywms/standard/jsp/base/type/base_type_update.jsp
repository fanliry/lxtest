<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.CommonTools"%>
<%
    String typevalue = CommonTools.getStrToGb2312(request.getParameter("typevalue"));
	String typename = CommonTools.getStrToGb2312(request.getParameter("typename"));
%>
<html>
<head>
<title>�޸�������Ϣ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript">
<!--
	function OnOk(){
    	var typevalue = document.getElementById("typevalue").value;
		var typename = document.getElementById("typename").value;

		if(typename == null || typename.length <1)
		{
			alert("��������������Ϊ��!");
			return false;
		}
		var back_msg = "&typename=" + typename + "&typevalue=" + typevalue;
		
		window.returnValue = back_msg;
		window.close();
	}
	
-->
</script>
</head>

<body>

  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�������Ϣ  -&gt; ���͹��� -&gt; �޸�����</div></td>
    </tr>
  </table>
  
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right">����ֵ��</td>
      <td class="yx"><input type="text" id="typevalue" maxlength="15" value="<%=typevalue%>" class="input_readonly" readonly></td>
      <td width="100px" class="yx1" align="right"><span class="red">*</span>��������</td>
      <td class="xx1"><input type="text" id="typename" maxlength="15" size="15" value="<%=typename%>"></td>
    </tr>
    <tr>
      <td colspan="4" align="center">
        <input type="button" onclick="OnOk()" value="&nbsp;&nbsp;&nbsp;����" class="button_edit">
        <input type="button" onClick="window.close()" name="resetBtn" value="�ر�" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
 
</body>
</html>