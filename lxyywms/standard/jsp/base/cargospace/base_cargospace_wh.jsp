<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseWarehouse" %>

<%
	BaseWarehouse warehouse = (BaseWarehouse)request.getAttribute("warehouse"); 
	
	String warehouseId = warehouse.getWarehouseid();		// �ֿ�ID
	String warehouseName = warehouse.getWarehousename();	// �ֿ���
	String whaddress = warehouse.getWhaddress();			// �ֿ��ַ
	String whmgrman = warehouse.getWhmgrman();				// �ֿ����Ա
	String whlinkman = warehouse.getWhlinkman();			// ��ϵ��
	String whlinktel = warehouse.getWhlinktel(); 			// ��ϵ�绰
	
%>

<html>
<head>
<title>�Զ�������ֿ���Ϣ����ϵͳ</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--

-->
</script>
</head>

<body>
<form id="myform" name="myform" method="post" action="">
<input type="hidden" name="warehouseId" value="<%=warehouseId%>">
 <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" align="center"> 
   <tr>
     <td width="36%" valign="top">
     
	 <table width="100%" height="100%" border="0" align="left" cellpadding="0" cellspacing="0" class="table_add">
	   <tr>
		 <td colspan="2" style="height:25px;" class="TD_LIST_TITLE4">[<%=warehouseId%>]<%=warehouseName%></td>
	    </tr>
	   <tr>
	     <td width="100px" class="yx1" align="right">�ֿ���룺</td>
	     <td class="xx1">
	       <input type="text" name="warehouseId" readonly="readonly" class="input_readonly" value="<%=warehouseId%>"></td>
	   </tr>
	   <tr>
	     <td class="yx1" align="right">�ֿ����ƣ�</td>
	     <td class="xx1">
	       <input type="text" name="warehouseName" readonly="readonly" class="input_readonly" value="<%=warehouseName%>"></td>
	   </tr>
	   <tr>
	     <td class="yx1" align="right">�ֿ����Ա��</td>
	     <td class="xx1">
	       <input type="text" name="whmgrman" readonly="readonly" class="input_readonly" value="<%=whmgrman == null ? "" : whmgrman%>"></td>
	   </tr>
	   <tr>
	     <td class="yx1" align="right">��ϵ�ˣ�</td>
	     <td class="xx1">
	       <input type="text" name="whlinkman" readonly="readonly" class="input_readonly" value="<%=whlinkman == null ? "" : whlinkman%>"></td>
	   </tr>
	   <tr>
	     <td class="yx1" align="right">��ϵ�绰��</td>
	     <td class="xx1">
	       <input type="text" name="whlinktel" readonly="readonly" class="input_readonly" value="<%=whlinktel == null ? "" : whlinktel%>"></td>
	   </tr>
	   <tr>
	     <td class="yx1" align="right">�ֿ��ַ��</td>
	     <td class="xx1">
	       <input type="text" name="whaddress" readonly="readonly" class="input_readonly" value="<%=whaddress == null ? "" : whaddress%>"></td>
	   </tr>
	   <tr>
		 <td height="100%" colspan="2"></td>
	   </tr>
	 </table>
	 </td>
	 <td width="5px"></td>
     <td valign="top">
	   <iframe id="whRightIframe" name="whRightIframe" width="100%" height="100%" frameborder="0" scrolling="yes" 
	   		src="<%=request.getContextPath()%>/standard/jsp/base/cargospace/base_cargospace_cs_right.jsp">
       </iframe>
	 </td>
   </tr>

 </table>
</form>
</body>
</html>