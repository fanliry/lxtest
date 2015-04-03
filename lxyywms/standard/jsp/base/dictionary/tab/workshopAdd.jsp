<%@ page contentType="text/html; charset=GB2312"%>

<%
	//设置重复提交的标志变量，begin为首次加载，finish为已提交
	session.setAttribute("submitFlag","begin");
	String strNumber=(String)request.getAttribute("number");
%>
<html>
<head>
<title>车位信息编辑窗口</title>
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
		if(document.getElementById("remark").value=="0")
		{
			alert("请输入有效数据!");
			return false;
		}
	   	var actionStr = "BMSService?actionCode=addWorkshop"
    			 + "&number=" + document.getElementById("number").value
        		 + "&name=" + document.getElementById("name").value
        		 + "&remark=" +document.getElementById("remark").value;
    	window.returnValue = actionStr;
    	window.close();	
  	}
  	else
  	{
  	  alert("请填车位名称");
  	}
  }
   function down()
  {
  	window.close();
  }
-->
</script>
</head>
<body>
<form method="post" action="" name="myform">
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="5"></td></tr>
 </table>
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_ADD">
   <tr>
     <td height="25" class="TD_ADD"><div align="right">车位编码：</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="number" id="number" value="<%=strNumber%>" disabled></div></td>
     <td class="TD_ADD"><input onclick="inspect()" type="button" name="add" value="新增" class="BUTTON_STYLE1" ></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">车位名：</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="name" id="name"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></div></td>
     <td class="TD_ADD"><input onclick="down()" type="button" name="add" value="取消" class="BUTTON_STYLE1"></td>
   </tr>

   <tr>
     <td height="25" class="TD_ADD"><div align="right">备注：</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="remark" id="remark"></div></td>
   </tr>
 </table>
 </form>
</body>
</html>