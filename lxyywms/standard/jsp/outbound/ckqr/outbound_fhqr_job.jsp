
<%@ page contentType="text/html; charset=GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	//request.setCharacterEncoding("iso-8859-1"); 
	//单据ID
	String invoiceid = request.getParameter("outboundid");
	//单据详细ID
	String invoicedetailid = request.getParameter("outdetailid");
	//车位
	String vehiclepos = request.getParameter("vehiclepos");
	if(vehiclepos != null){
		vehiclepos = new String(vehiclepos.getBytes("ISO-8859-1"),"gbk");
	}
	//车牌
	String vehicleno = request.getParameter("vehicleno");
	if(vehicleno != null){
		vehicleno = new String(vehicleno.getBytes("ISO-8859-1"),"gbk");
	}
%>
<html>
  <head>
    <title>浙江刚玉物流仓库管理系统</title>  
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
	<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
</head>
  <body >
  
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：出库管理 -&gt; 发货确认 -&gt; 作业信息</div></td>
   </tr>
  </table>
	<FORM id="form1">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
        <tr>  
          <td align="right" width="60" class="y1">单&nbsp;据&nbsp;号：</td>
          <td class="x" width="150">
          	<input type="text" name="invoiceid" value="<%=invoiceid%>" class="input_readonly" readonly  style="height:18px;width:100px;"/> </td>
          <td align="right" width="60" class="y1">车&nbsp;&nbsp;&nbsp;&nbsp;牌：</td>
          <td  class="x" width="150"><input type="text" name="invoiceid" value="<%=vehicleno == null ? "" : vehicleno%>" class="input_readonly" readonly  style="height:18px;width:100px;"/></td>
          <td align="right" width="60" class="y1">车&nbsp;&nbsp;&nbsp;&nbsp;位：</td>
          <td  class="x" width="150"><input type="text" name="invoiceid" value="<%=vehiclepos == null ? "" : vehiclepos%>" class="input_readonly" readonly  style="height:18px;width:100px;"/></td>

          <td  align="right" >
          	<input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="关闭" onClick="window.close();" />
          </td>
        </tr>
      </table>
      </FORM>
 <table width="98%" height="10"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>
 <!-- ======== 明细列表开始 ======== -->
 <table width="98%" height="485" border="0" align="center" cellpadding="0" cellspacing="0">
   <tr>
	 <td>
	   <iframe id="list" src="<%=request.getContextPath()%>/BMSService?actionCode=sendAction&flag=2&invoiceid=<%=invoiceid%>&invoicedetailid=<%=invoicedetailid%>" scrolling="auto" frameborder="0" width="100%" height="100%">
	   </iframe>
	 </td>
   </tr>
 </table>
 <!-- ======== 明细列表结束 ======== -->

  </body>
</html>
