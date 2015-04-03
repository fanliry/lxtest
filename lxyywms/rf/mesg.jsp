<%@ page contentType="text/html; charset=GB2312"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>重新登录</title>
</head>
<body>
<script language="javascript">
	alert("会话超时，请重新登录！");
	top.window.location.href = "<%=request.getContextPath()%>/rf/login.jsp";
</script>
</body>
</html>

