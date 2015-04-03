<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseWharea" %>

<%
	BaseWharea wharea = (BaseWharea)request.getAttribute("wharea"); 
	
	String whAreaId = wharea.getwhAreaId();				//库区ID
   	String whAreaName = wharea.getwhAreaName();			//库区名
    String warehousename = wharea.getWarehousename();	//仓库名
    String isdefault = wharea.getIsdefault();			//是否是默认收货区  是：Y  否：N
    if(isdefault != null && isdefault.equalsIgnoreCase("Y")){
		isdefault = "是";
	}else{
		isdefault = "否";
	}
	
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
 <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" align="center"> 
   <tr>
     <td width="36%" valign="top">
     
	 <table width="100%" height="100%" border="0" align="left" cellpadding="0" cellspacing="0" class="table_add">
	   <tr>
		 <td colspan="2" style="height:25px;" class="TD_LIST_TITLE4">[<%=whAreaId%>]<%=whAreaName%></td>
	    </tr>
	   <tr>
	     <td class="yx1" align="right">库区代码：</td>
	     <td class="xx1"><input type="text" name="whAreaId" readonly="readonly" class="input_readonly" value="<%=whAreaId%>"></td>
	   </tr>
	   <tr>
	     <td class="yx1" align="right">库区类型：</td>
	     <td class="xx1"><input type="text" name="whAreaName" readonly="readonly" class="input_readonly" value="<%=whAreaName%>"></td>
	   </tr>
	   <tr>
	     <td class="yx1" align="right">所属仓库名称：</td>
	     <td class="xx1"><input type="text" name="warehousename" readonly="readonly" class="input_readonly" value="<%=warehousename%>"></td>
	   </tr>
	   <tr>
	     <td class="yx1" align="right">是否是默认收货区：</td>
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