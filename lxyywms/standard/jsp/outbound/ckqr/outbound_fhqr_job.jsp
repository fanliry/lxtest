
<%@ page contentType="text/html; charset=GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	//request.setCharacterEncoding("iso-8859-1"); 
	//����ID
	String invoiceid = request.getParameter("outboundid");
	//������ϸID
	String invoicedetailid = request.getParameter("outdetailid");
	//��λ
	String vehiclepos = request.getParameter("vehiclepos");
	if(vehiclepos != null){
		vehiclepos = new String(vehiclepos.getBytes("ISO-8859-1"),"gbk");
	}
	//����
	String vehicleno = request.getParameter("vehicleno");
	if(vehicleno != null){
		vehicleno = new String(vehicleno.getBytes("ISO-8859-1"),"gbk");
	}
%>
<html>
  <head>
    <title>�㽭���������ֿ����ϵͳ</title>  
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
	<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
</head>
  <body >
  
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�������� -&gt; ����ȷ�� -&gt; ��ҵ��Ϣ</div></td>
   </tr>
  </table>
	<FORM id="form1">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
        <tr>  
          <td align="right" width="60" class="y1">��&nbsp;��&nbsp;�ţ�</td>
          <td class="x" width="150">
          	<input type="text" name="invoiceid" value="<%=invoiceid%>" class="input_readonly" readonly  style="height:18px;width:100px;"/> </td>
          <td align="right" width="60" class="y1">��&nbsp;&nbsp;&nbsp;&nbsp;�ƣ�</td>
          <td  class="x" width="150"><input type="text" name="invoiceid" value="<%=vehicleno == null ? "" : vehicleno%>" class="input_readonly" readonly  style="height:18px;width:100px;"/></td>
          <td align="right" width="60" class="y1">��&nbsp;&nbsp;&nbsp;&nbsp;λ��</td>
          <td  class="x" width="150"><input type="text" name="invoiceid" value="<%=vehiclepos == null ? "" : vehiclepos%>" class="input_readonly" readonly  style="height:18px;width:100px;"/></td>

          <td  align="right" >
          	<input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="�ر�" onClick="window.close();" />
          </td>
        </tr>
      </table>
      </FORM>
 <table width="98%" height="10"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>
 <!-- ======== ��ϸ�б�ʼ ======== -->
 <table width="98%" height="485" border="0" align="center" cellpadding="0" cellspacing="0">
   <tr>
	 <td>
	   <iframe id="list" src="<%=request.getContextPath()%>/BMSService?actionCode=sendAction&flag=2&invoiceid=<%=invoiceid%>&invoicedetailid=<%=invoicedetailid%>" scrolling="auto" frameborder="0" width="100%" height="100%">
	   </iframe>
	 </td>
   </tr>
 </table>
 <!-- ======== ��ϸ�б���� ======== -->

  </body>
</html>
