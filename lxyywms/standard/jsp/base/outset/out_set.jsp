<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean baseTypeUpdate_Detail = false; //修改

	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("baseTypeUpdate_Detail") != null){
			baseTypeUpdate_Detail = true;
		}
    }
%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = "";
	//修改
	function Update(type)
	{
		var key = null;
		var check_ids = left.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++)
		{
			if(check_ids[i].checked)
			{
				key = check_ids[i].value;
				break;
			}
		}
		if(key == null)
		{
			alert("请先选择一条记录！");
			return;
		}
		if(type == 1)
		{
			var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/outset/out_set_update_s.jsp", key, "dialogWidth=400px;dialogHeight=200px");
			if(result != null && result.length > 1)
			{
				condition = result;
				up.location.href = ac + "OutSetAction&method1=update&flag=1" + condition;
			}
		}
		else
		{
			var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/outset/out_set_update_p.jsp", key, "dialogWidth=400px;dialogHeight=200px");
			if(result != null && result.length > 1)
			{
				condition = result;
				down.location.href = ac + "OutSetAction&method1=update&flag=2" + condition;
			}
		}
	}
	//页面登录
	function OnLoad()
	{
		left.location.href = ac + "OutSetAction&method1=search&flag=3";
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
<div class="con_bk">
 <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td>
     <div class="wz">
		<div id="dqwz" class="f_l">当前位置：<span>基本信息 &gt;&gt; 出库设置</span></div>
	  </div>
     </td>
   </tr>
   <tr>
     <td height="100%"> 
		 <table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		   <tr>
		     <td width="300" rowspan="4">
			 	<iframe id="left" name="left" width="100%" height="100%" frameborder="0" scrolling="yes"
			 		src="<%=request.getContextPath()%>/standard/jsp/base/outset/out_set_left.jsp"></iframe>
			 </td>
			  <td width="5"></td>
		     <td height="20" class="font_006699_bold_12">物品状态</td>
		     <td align="right">
		       <%if(baseTypeUpdate_Detail){%><input type="button" value="修改" class="button" onClick="Update(2)"><%}%>
		     </td>
		   </tr>
		   <tr height="100%">
			 <td width="5"></td>
			 <td colspan="2">
			 	<iframe id="down" name="down" width="100%" height="100%" frameborder="0" scrolling="no"
			 		src="<%=request.getContextPath()%>/standard/jsp/base/outset/out_set_product.jsp"></iframe>
			 </td>
		   </tr>
		 </table>
     </td>
   </tr>
 </table>
   </div>
</body>
</html>
