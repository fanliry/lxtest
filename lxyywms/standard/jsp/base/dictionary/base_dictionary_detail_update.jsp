<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseType" %>
<%
	BaseType type = (BaseType)request.getAttribute("type");
	String typeid = type.getTypeid();			// ����ID
    String typename = type.getTypename();		// ������
    String typevalue = type.getTypevalue();		// ����ֵ
    String selecttext = type.getSelecttext();	// �����б���ʾ����
    String selectvalue = type.getSelectvalue();	// �����б�ֵ
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
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript">
<!--
    var state = false;
    function isState(data){
		state=data;
	}
	//�ı�����ʧȥ����
	function changetText(){
		 document.getElementById("selectvalue").value = document.getElementById("selecttext").value
		}
	function OnOk(){
    	var typename = document.getElementById("typename").value;
    	var typevalue = document.getElementById("typevalue").value;
		var selecttext = document.getElementById("selecttext").value;
		var selectvalue = document.getElementById("selectvalue").value;
		var selectvalueOld = document.getElementById("selectvalueHide").value;
		
		state = false;// ��ֹ�ڶ����ڽ�����֤ʱ��������ǰ��ֵ ��Ϊstate��ȫ�ֱ���
		DWREngine.setAsync(false);
        selectView.getSelectValueStatus(typevalue, selectvalue,{callback:function(data){isState(data);}});
        DWREngine.setAsync(true);

		if(typename == null || typename.length <1)
		{
			alert("��������������Ϊ��!");
			return false;
		}
		if(selecttext == null || selecttext.length <1)
		{
			alert("���б����ݡ�����Ϊ��!");
			return false;
		}
		if(selectvalue != null && selectvalue == selectvalueOld)
		{
			state = false;
		}
		if(selectvalue == null || selectvalue.length <1 || state == true)
		{
			alert("���б�ֵ������Ϊ��! ���Ѿ����ڸ��б�ֵ");
			return false;
		}
		
		window.returnValue = "&typeId=<%=typeid%>&typename=" + typename + "&typeVlaue=" + typevalue + 
			"&selecttext=" + selecttext + "&selectvalue=" + selectvalue;
		window.close();
	}
	
-->
</script>
</head>

<body>

  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�������Ϣ  -&gt; ���͹��� -&gt; �޸�����ֵ</div></td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right">����ֵ��</td>
      <td class="yx">
      	<input type="text" name="typevalue" maxlength="15" size="15" value="<%=typevalue%>" class="input_readonly" readonly></td>
      <td width="100px" class="yx1" align="right">��������</td>
      <td class="xx1"><input type="text" name="typename" maxlength="15" size="15" value="<%=typename%>" class="input_readonly" readonly></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>�б����ݣ�</td>
      <td class="yx"><input type="text" name="selecttext" maxlength="15" size="15" value="<%=selecttext%>"  onblur="changetText()"></td>
      <td class="yx1" align="right"><span class="red">*</span>�б�ֵ��</td>
      <td class="xx1">
      	<input name="selectvalueHide" type="hidden" value="<%=selectvalue%>"/>
      	<input type="text" name="selectvalue" maxlength="15" size="15" value="<%=selectvalue%>" class="input_readonly" readonly></td>
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