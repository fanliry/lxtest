<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseAlley" %>

<%
	BaseAlley alley = (BaseAlley)request.getAttribute("alley"); 
	
	String cargoAreaId = alley.getCargoAlleyId();
	String cargoAreaName = alley.getCargoAlleyName();
	String warehouseName = alley.getWarehousename();
	String whAreaName = alley.getWhAreaName();
	String istwin = alley.getIstwin();					//是否双升货位巷道
%>

<html>
<head>
<title>自动化立体仓库信息管理系统</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--

-->
</script>
</head>

<body>
<form id="myform" name="myform" method="post" action="">
<input type="hidden" name="cargoAreaId" value="<%=cargoAreaId%>">
 <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" align="center"> 
   <tr>
     <td width="36%" valign="top">
     
	 <table width="100%" height="100%" border="0" align="left" cellpadding="0" cellspacing="0" class="table_add">
	   <tr>
		 <td colspan="2" style="height:25px;" class="TD_LIST_TITLE4">[<%=cargoAreaId%>]<%=cargoAreaName%></td>
	    </tr>
	   <tr>
	     <td width="120px" class="yx1" align="right">巷道代码：</td>
	     <td class="xx1"><input type="text" name="warehouseNo" readonly="readonly" class="input_readonly" value="<%=cargoAreaId%>"></td>
	   </tr>
	   <tr>
	     <td class="yx1" align="right">巷道名称：</td>
	     <td class="xx1"><input type="text" name="typeCode" readonly="readonly" class="input_readonly" value="<%=cargoAreaName%>"></td>
	   </tr>
	   <tr>
	     <td class="yx1" align="right">所属仓库：</td>
	     <td class="xx1"><input type="text" name="warehouseName" readonly="readonly" class="input_readonly" value="<%=warehouseName%>"></td>
	   </tr>
	   <tr>
	     <td class="yx1" align="right">所属库区：</td>
	     <td class="xx1"><input type="text" name="whAreaName" readonly="readonly" class="input_readonly" value="<%=whAreaName%>"></td>
	   </tr>
	   <tr>
         <td class="yx1" align="right">是否双升货位巷道：</td>
         <td class="xx1"><input type="text" name="istwin" readonly="readonly" class="input_readonly" value="<%=istwin%>"></td>
       </tr>
	   <tr>
		 <td height="100%" colspan="2"></td>
	   </tr>
	 </table>
	 </td>
	 <td width="5px"></td>
     <td valign="top">
	   <iframe id="caRightIframe" name="caRightIframe" width="100%" height="100%" frameborder="0" scrolling="auto" 
	   		src="<%=request.getContextPath()%>/standard/jsp/base/cargospace/base_cargospace_cs_right.jsp">
       </iframe>
	 </td>
   </tr>

 </table>
</form>
</body>
</html>