<%@ page contentType="text/html; charset=GB2312"%>
<%
	//设置重复提交的标志变量，begin为首次加载，finish为已提交
	session.setAttribute("submitFlag","begin");
	String strNumber=(String)request.getAttribute("number");
%>
<html>
	<head>
	<title>部门信息编辑窗口</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
	<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script>
		<!--
		  function inspect()
		  {
		  	var name=document.getElementById("name").value;
		  	if(name!="")
		  	{
		  		if(name.length>15)
				{
				alert("不能超过15个字符！");
				return false;
				}
		  		var actionStr = "BMSService?actionCode=addBranch"
		    			 + "&number=" + document.getElementById("number").value
		        		 + "&name=" + document.getElementById("name").value;
		    	window.returnValue = actionStr;
		    	window.close();
		  	  
		  	}
		  	else
		  	{
		  	  alert("请填写部门名称");
		  	}
		  }
		  //关闭窗体
		  function down()
		  {
		  	window.close();
		  }
		
		-->
</script>
</head>
	<body >
		<form method="post" action="" name="myform">
	 		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	   			<tr>
	    		 <td height="5"></td></tr>
	 		</table>
	 
		 	<table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_ADD">
		   		<tr>
		   			 <td height="25" class="TD_ADD"><div align="right">部门编号：</div></td>
		    		 <td class="TD_ADD"><div align="left"><input type="text" name="number" id="number" value="<%=strNumber%>" disabled></div></td>
		    	 	<td class="TD_ADD"><input onclick="inspect()" type="button" name="add" value="新增" class="BUTTON_STYLE1" ></td>
		  	   </tr>
		  	   <tr>
		     		<td height="25" class="TD_ADD"><div align="right">部门名称：</div></td>
		     		<td class="TD_ADD"><div align="left"><input type="text" name="name" id="name"></div></td>
		    		<td class="TD_ADD"><input onclick="down()" type="button" name="add" value="取消" class="BUTTON_STYLE1"></td>
		   	   </tr>
			</table>
	 	</form>
	</body>
</html>