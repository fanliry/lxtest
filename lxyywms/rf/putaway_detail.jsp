<%@ page contentType="text/html; charset=GBK"%>

<html>

<head>
<title>自动化立体仓库信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
<script type="text/javascript">

	//页面登录
	function OnLoad()
	{
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != "")
		{
			if(back_msg == "Y"){
				alert("操作成功！");
			}else{
				alert(back_msg);
			}
		}
	}
</script>
</head>

<body onload="OnLoad()">
<div style="width: 100%; height: 100%; overflow:auto;">
 <table width="100%" border="0" align="center" id="puttb" cellpadding="0" cellspacing="0" class="MAIN_TABLE">
   <tr>
   	 <td class="TD_LIST_TITLE" align="center" >跟踪号</td>
     <td class="TD_LIST_TITLE" align="center" >品名</td>
     <td class="TD_LIST_TITLE" align="center" >数量</td>
     <td class="TD_LIST_TITLE" align="center" >操作</td>
  </tr>

 </table>
</div>

</body>
</html>