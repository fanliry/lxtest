<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseWharea" %>

<%
	BaseWharea wharea = (BaseWharea)request.getAttribute("wharea"); 
	
	String whAreaId = wharea.getwhAreaId();				//����ID
   	String whAreaName = wharea.getwhAreaName();			//������
    String warehousename = wharea.getWarehousename();	//�ֿ���
    String isdefault = wharea.getIsdefault();			//�Ƿ���Ĭ���ջ���  �ǣ�Y  ��N
    if(isdefault != null && isdefault.equalsIgnoreCase("Y")){
		isdefault = "��";
	}else{
		isdefault = "��";
	}
	
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
 <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" align="center"> 
   <tr>
     <td width="36%" valign="top">
     
	 <table width="100%" height="100%" border="0" align="left" cellpadding="0" cellspacing="0" class="table_add">
	   <tr>
		 <td colspan="2" style="height:25px;" class="TD_LIST_TITLE4">[<%=whAreaId%>]<%=whAreaName%></td>
	    </tr>
	   <tr>
	     <td class="yx1" align="right">�������룺</td>
	     <td class="xx1"><input type="text" name="whAreaId" readonly="readonly" class="input_readonly" value="<%=whAreaId%>"></td>
	   </tr>
	   <tr>
	     <td class="yx1" align="right">�������ͣ�</td>
	     <td class="xx1"><input type="text" name="whAreaName" readonly="readonly" class="input_readonly" value="<%=whAreaName%>"></td>
	   </tr>
	   <tr>
	     <td class="yx1" align="right">�����ֿ����ƣ�</td>
	     <td class="xx1"><input type="text" name="warehousename" readonly="readonly" class="input_readonly" value="<%=warehousename%>"></td>
	   </tr>
	   <tr>
	     <td class="yx1" align="right">�Ƿ���Ĭ���ջ�����</td>
	     <td class="xx1"><input type="text" name="isdefault" readonly="readonly" class="input_readonly" value="<%=isdefault%>"></td>
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