<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseBatch"%>
<%
	BaseBatch batch = (BaseBatch)request.getAttribute("batch");
	String batch_id = batch.getBatchid();
	String batch_name = batch.getBatchname();
	Integer length = batch.getBatchlength();
	String use_flag = batch.getUseflag();
%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!-- 
	//����
	function Save()
	{
		var batch_id = document.getElementById("batch_id").value;
		var batch_name = document.getElementById("batch_name").value;
		var length = document.getElementById("length").value;
		var use_flag = document.getElementById("use_flag").checked?"Y":"N";
		
		var backmsg = "&batch_id=" + batch_id + "&batch_name=" + batch_name+ "&length=" + length+ "&use_flag=" + use_flag;
		
		window.returnValue = backmsg;
		window.close();
	}
-->
</script>
</head>

<body>
     
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td class="font_006699_bold_12" height="25">��ǰλ�ã�������Ϣ -&gt; ���ι��� -&gt; �޸�������Ϣ</td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td class="yx1" align="right">���δ��룺</td>
       <td class="xx1"><input type="text" name="batch_id" size="15" value="<%=batch_id%>" class="input_readonly" readonly></td>
    </tr>
    <tr>
      <td class="yx1" align="right">�������ƣ�</td>
      <td class="xx1"><input type="text" name="batch_name" size="15" value="<%=batch_name%>"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">���ȣ�</td>
      <td class="xx1"><input type="text" name="length" size="15" value="<%=length%>"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">���ã�</td>
      <td class="xx1"><input type="checkbox" name="use_flag" class="checkbox" <%if(use_flag.equals("Y")){%>checked<%}%>></td>
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
     