<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseProducttype" %>
<%
	BaseProducttype producttype = (BaseProducttype)request.getAttribute("producttype");  
	String ptid = producttype.getPtid();		// ��Ʒ�����
    String ptname = producttype.getPtname();	// �����
    String parentid = producttype.getParentid();// ��ID
    Integer ptorder = producttype.getPtorder();	// ˳��
%>
<html>
<head>
<title>�޸���Ʒ�����Ϣ</title>
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
	//������Ʒ�����Ϣ
	function OnOk(){
		var id = document.getElementById("ptid").value;
		var ptname = document.getElementById("ptname").value;
		var parentid = document.getElementById("parentid").value;
		var ptorder = document.getElementById("ptorder").value;
		
		if(ptname == null || ptname.length <1)
		{
			alert("�������������Ϊ��!");
			return;
		}
		
		if(strlen(ptname) > 50){
				alert("�������������!");
				return;
		}
			
		if(ptorder!=null && ptorder.length>0)
		{
			if(!numChar(ptorder)){
				alert("��˳��ֻ���������0������!");
				return;
			}
		}

		var back_msg = "&id=" + id + "&ptname=" + ptname + "&parentid=" + parentid + "&ptorder=" + ptorder;
		window.returnValue = back_msg;
    	window.close();
	}
	
	function OnLoad(){
		selectObject("<%=parentid%>", "parentid", "6");
	}

-->
</script>
</head>

<body onload="OnLoad();">

  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�������Ϣ -&gt; ��Ʒ������ -&gt; �޸���Ʒ�����Ϣ</div></td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td class="yx1" width="100px" align="right">���ID��</td>
      <td class="xx1"><input type="text" name="ptid" maxlength="25" size="25" value="<%=ptid%>" class="input_readonly" readonly></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">�������</td>
      <td class="xx1"><input type="text" name="ptname" maxlength="50" size="40" value="<%=ptname%>"><font color="red">*</font></td>
    </tr> 
    <tr>
      <td class="yx1" align="right">�����</td>
      <td class="xx1"><select name="parentid"><option value=""></option></select></td>
    </tr>
    <tr>
      <td class="yx1" align="right">˳��</td>
      <td class="xx1"><input type="text" name="ptorder" maxlength="4" size="4" value="<%=ptorder==null?"":ptorder%>"></td>
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