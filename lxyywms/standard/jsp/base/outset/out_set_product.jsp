<%@ page language="java" pageEncoding="GB2312"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.base.HisProduct"%>

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
	//全选
    function CheckAll()
	{
		var state = document.getElementById("checkbox_all").checked;
		var checkbox_ids = document.getElementsByName("checkbox_id");
		
		for(var i=0; i<checkbox_ids.length; i++)
		{
			checkbox_ids[i].checked = state;
		}
	}
-->
</script>
</head>
<body>

 <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="MAIN_TABLE">
   <tr>
     <td class="TD_LIST_TITLE" align="center" width="50">行号</td>
     <td class="TD_LIST_TITLE" align="center">物品状态</td>
   </tr>
<%
	List ls = (List)request.getAttribute("list");
	if(ls!=null)
	{
		HisProduct ta;
		String s1;
		for(int i=0; i<ls.size(); i++)
		{
			ta = (HisProduct)ls.get(i);
			s1 = ta.getM_strTypeValeName();

%>   
   <tr onmouseover="this.bgColor='#E2E8EA'" onmouseout="this.bgColor=''">
     <td class="TD_LIST" align="center"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=s1%></td>
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