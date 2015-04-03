<%@ page contentType="text/html; charset=GB2312"%>
<%

    request.setCharacterEncoding("ISO-8859-1");
    String id=request.getParameter("id");
	String number=request.getParameter("number");
	String name=request.getParameter("name");
	String remark=request.getParameter("remark");
	String duty=request.getParameter("duty");
	String play=request.getParameter("play");
	name= new String(name.getBytes("ISO-8859-1"),"gb2312");
	remark=new String(remark.getBytes("ISO-8859-1"),"gb2312");
	duty=new String(duty.getBytes("ISo-8859-1"),"gb2312");

%>

<html>
<head>
<title>卸载工编辑信息窗口</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">

<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script>
<!--
	function onlo()
	{
		selectObject('<%=duty%>','duty','17'); //班次
	}
	  function inspect()
	  {
	  	var name=document.getElementById("name").value;
	  	if(name!="")
	  	{
  			if(document.getElementById("remark").value.length>50)
			{
				alert("备注不能超过50个字符！");
				return false;
			}if(name.length>15)
			{
				alert("不能超过15个字符！");
				return false;
			}
	    var actionStr="BMSService?actionCode=updLabout"	
	  			 + "&number=" + document.getElementById("number").value 
        		 + "&name=" + document.getElementById("name").value
        		 + "&remark=" + document.getElementById("remark").value
        		 + "&duty="// + document.getElementById("duty").value
        		 + "&id=" + document.getElementById("id").value
        		 + "&play=" + document.getElementById("play").value;
    	window.returnValue = actionStr;
	    window.close();	
	  	}
	  	else
	  	{
	  	  alert("请填写员工姓名");
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

<body onload="">
<form method="post" action="" name="myform">
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td class="TD_ADD"><div align="left"><input type="hidden" name="id" size="27" id="id" value="<%=id%>"></div></td>
   </tr>
 </table>
 
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_ADD">
   <tr>
     <td width="30%" height="25" class="TD_ADD"><div align="right">编码：</div></td>
     <td width="47%" class="TD_ADD"><div align="left">
         <input type="text" name="number" size="27" id="number" value="<%=number%>" disabled>
     </div></td>
     <td width="15%" class="TD_ADD"><input onClick="inspect()" type="button" name="add" value="确定" class="BUTTON_STYLE1"></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">姓名：</div></td>
     <td class="TD_ADD"><div align="left">
         <input type="text" name="name" size="27" id="name" value="<%=name%>">
     </div></td>
     <td class="TD_ADD"><input onClick="down()" type="button" name="add" value="取消" class="BUTTON_STYLE1"></td>
   </tr>
 <!--   <tr>
     <td height="25" class="TD_ADD"><div align="right">班次：</div></td>
     <td colspan="2" class="TD_ADD"><div align="left">
       <select name="duty" id="duty">
         <option value=""></option>
       </select>
</div></td>
   </tr>-->
   <tr>
     <td height="25" class="TD_ADD"><div align="right">启用：</div></td>
     <td colspan="2" class="TD_ADD"><div align="left">
     <%if(play.equals("0")){%>
       <select name="play">
         <option value="1">是</option>
         <option selected value="0">否</option>
       </select>
        <%}else{%>
       <select name="play">
         <option selected  value="1">是</option>
         <option value="0">否</option>
       </select>
       <%}%>
</div>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">描述：</div></td>
     
     <td colspan="2" class="TD_ADD"><div align="left"> 
	<textarea name="remark" id="remark"><%=remark%></textarea>
	</div></td>
     <td width="8%" class="TD_ADD"><div align="left">
          </div></td>

   </tr>
 </table>
</form>
</body>
</html>