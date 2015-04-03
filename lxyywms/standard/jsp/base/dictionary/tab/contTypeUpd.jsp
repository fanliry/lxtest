<%@ page contentType="text/html; charset=GB2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<title>集装箱箱型编辑窗口</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>css/table.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/js/tools.js"></script>
<script>
<!--
	  function update()
	  {
	  	var name=document.getElementById("name").value;
	  	if(name!="")
	  	{
	  		if(name.length>15)
			{
				alert("不能超过15个字符！");
				return false;
			}
			if(document.getElementById("remark").value.length>15)
			{
				alert("备注不能超过50个字符！");
				return false;
			}
		   var actionStr="rmsService?actionCode=updContType"
		  			 + "&number=" + document.getElementById("number").value 
	        		 + "&name=" + document.getElementById("name").value
	        		 + "&id=" + document.getElementById("id").value
	        		 + "&remark=" + document.getElementById("remark").value;
	    	window.returnValue = actionStr;
		    window.close();	
	  	}
	  	else
	  	{
	  	  alert("请填写集装箱箱型");
	  	}
	  }
	   //关闭窗口
	  function down()
	  {
	  	window.close();
	  }
-->
</script>
</head>
<%

	request.setCharacterEncoding("ISO-8859-1");
	String id=request.getParameter("id");
	String number=request.getParameter("number");
	String name=request.getParameter("name");
	String remark=request.getParameter("remark");
	
	name=new String(name.getBytes("ISO-8859-1"),"gb2312");
	remark=new String(remark.getBytes("ISO-8859-1"),"gb2312");
%>

<body>
<form method="post" action="" name="myform" >
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td class="TD_ADD"><div align="left"><input type="hidden" name="id" id="id" value="<%=id%>"></div></td>
 </table>
 
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_ADD">
   <tr>
     <td height="25" class="TD_ADD"><div align="right">箱型编号：</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="number" id="number" value="<%=number%>" disabled></div></td>
     <td class="TD_ADD"><input onclick="update()" type="button" name="add" value="修改" class="BUTTON_STYLE1"></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">箱型名称：</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="name" id="name" value="<%=name%>" onKeyUp="if(checkValue(this.value))execCommand('undo')"></div></td>
     <td class="TD_ADD"><input onclick="down()" type="button" name="add" value="取消" class="BUTTON_STYLE1"></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">描述：</div></td>
     <td class="TD_ADD"><div align="left">       <textarea name="remark" id="remark" onKeyUp="if(checkValue(this.value))execCommand('undo')"><%=remark%></textarea>
     </div></td>
     <td class="TD_ADD"></td>
   </tr>
 </table>
 </form>
</body>
</html>