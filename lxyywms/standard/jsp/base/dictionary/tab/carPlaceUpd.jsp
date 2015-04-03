<%@ page contentType="text/html; charset=GB2312"%>



<html>
<head>
<title>车位信息编辑窗口</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/table.css" rel="stylesheet" type="text/css">
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
		if(document.getElementById("row").value=="0")
		{
			alert("请输入有效数据!");
			return false;
		}
		if(document.getElementById("line").value=="0")
		{
			alert("请输入有效数据!");
			return false;
		}
		if(document.getElementById("laye").value=="0")
		{
			alert("请输入有效数据!");
			return false;
		}
	   	var actionStr = "rmsService?actionCode=updPlace"
	   			 + "&id=" + document.getElementById("id").value
    			 + "&number=" + document.getElementById("number").value
        		 + "&name=" + document.getElementById("name").value
        		 + "&line=" +document.getElementById("line").value
        		 + "&row=" +document.getElementById("row").value
        		 + "&laye=" +document.getElementById("laye").value;
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
 <% 
     request.setCharacterEncoding("ISO-8859-1");
  	 String id=request.getParameter("id");
 	 String name=request.getParameter("name");
 	 String number=request.getParameter("number");
 	 String row=request.getParameter("row");
 	 String line=request.getParameter("line");
 	 String laye=request.getParameter("laye");
 	 name = new String(name.getBytes("ISO-8859-1"),"gb2312");
  %>
<body>
<form method="post" action="" name="myform">
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
      <td class="TD_ADD"><div align="left"><input type="hidden" name="id" id="id" value="<%=id%>"></div></td>
 </table>
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_ADD">
   <tr>
     <td height="25" class="TD_ADD" ><div align="right">车位编码：</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="number" id="number" value="<%=number%>" disabled></div></td>
     <td class="TD_ADD"><input onclick="update()" type="button" name="add" value="修改" class="BUTTON_STYLE1" ></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">车位名：</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="name" id="name" value="<%=name%>"></div></td>
     <td class="TD_ADD"><input onclick="down()" type="button" name="add" value="取消" class="BUTTON_STYLE1"></td>
   </tr>
     <tr>
     <td height="25" class="TD_ADD"><div align="right">排：</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="row" id="row" value="<%=row%>"></div></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">列：</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="line" id="line" value="<%=line%>"></div></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">层：</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="laye" id="laye" value="<%=laye%>"></div></td>
   </tr>
 </table>
 </form>
</body>
</html>