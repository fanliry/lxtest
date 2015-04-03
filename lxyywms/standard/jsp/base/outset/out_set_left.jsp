<%@ page language="java" pageEncoding="GB2312"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseType"%>

<html>
<head>
<title>自动化立体仓库信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	function Detail(id)
	{
		//parent.up.location.href = ac + "OutSetAction&method1=search&flag=1&out_type=" + id;
		parent.down.location.href = ac + "OutSetAction&method1=search&flag=2&out_type=" + id;
	}
 -->
</script>
</head>
<body>

 <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="MAIN_TABLE">
   <tr>
     <td class="TD_LIST_TITLE" align="center" width="50">选择</td>
     <td class="TD_LIST_TITLE" align="center">单据类型</td>
   </tr>
<%
	List ls = (List)request.getAttribute("list");
	if(ls!=null)
	{
		BaseType ta;
		String s0,s1,key;
		for(int i=0; i<ls.size(); i++)
		{
			ta = (BaseType)ls.get(i);
			s0 = ta.getSelectvalue();
			s1 = ta.getSelecttext();
			key = s0+ "|" + s1;
%>   
   <tr onmouseover="this.bgColor='#E2E8EA'" onmouseout="this.bgColor=''">
     <td class="TD_LIST" align="center"><input type="radio" name="check_id" value="<%=key%>" class="input_radio" onClick="Detail('<%=s0%>')"></td>
     <td class="TD_LIST" align="center"><%=s1 == null ? "" : s1%></td>
   </tr>
<%
		}
	}
%>
   <tr>
     <td height="100%" colspan="2" class="TD_LIST"></td>
   </tr>
 </table>

</body>
</html>