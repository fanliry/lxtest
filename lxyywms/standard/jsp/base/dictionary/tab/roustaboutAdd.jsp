<%@ page contentType="text/html; charset=GB2312"%>


<%
	//设置重复提交的标志变量，begin为首次加载，finish为已提交
	session.setAttribute("submitFlag","begin");
	String strNumber=(String)request.getAttribute("number");
%>

<html>
<head>
<title>码盘工信息编辑窗口</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/table.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/js/tools.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/js/selectTool.js"></script>
<script>
<!--
	function onlo()
	{
		selectObject('','duty','17'); //班次
	}
	function inspect()
    {
  	var name=document.getElementById("name").value;
  	var pwd=document.getElementById("pwd").value;
  	if(name!=""&&pwd!="")
  	{	
  		if(name.length>15)
		{
				alert("不能超过15个字符！");
				return false;
		}	
		if(document.getElementById("remark").value.length>50)
		{
				alert("备注不能超过50个字符！");
				return false;
		}	
	    var actionStr="rmsService?actionCode=addRoust"	
	  			 + "&number=" + document.getElementById("number").value 
        		 + "&name=" + document.getElementById("name").value
        		 + "&remark=" + document.getElementById("remark").value
        		 + "&pwd=" + document.getElementById("pwd").value
        		 + "&duty=";// + document.getElementById("duty").value;
    	window.returnValue = actionStr;
	    window.close();	
  	}
  	else
  	{
  	  alert("请输入完整信息！");
  	}
  }
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
     <td height="5"></td></tr>
 </table>
 
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_ADD">
   <tr>
     <td height="25" class="TD_ADD"><div align="right">编号：</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="number" id="number" value="<%=strNumber%>" disabled></div></td>
     <td class="TD_ADD"><input onclick="inspect()" type="button" name="add" value="新增" class="BUTTON_STYLE1"></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">名称：</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="name" id="name"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></div></td>
     <td class="TD_ADD"><input onclick="down()" type="button" name="add" value="取消" class="BUTTON_STYLE1"></td>
   </tr>
    <tr>
     <td height="25" class="TD_ADD"><div align="right">密码：</div></td>
     <td class="TD_ADD"><div align="left"><input type="password" name="pwd" id="pwd"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></div></td>
     <td class="TD_ADD"></td>
   </tr>
<!--     <tr>
     <td height="25" class="TD_ADD"><div align="right" >班次：</div></td>
     <td class="TD_ADD"><div align="left">
       <select name="duty" id="duty">
         <option value=""> </option>
       </select>
     </div></td>
     <td class="TD_ADD"></td>
   </tr>-->
   <tr>
     <td height="25" class="TD_ADD"><div align="right">描述：</div></td>
    <td class="TD_ADD"> 
         <textarea name="remark" id="remark"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></textarea>
   </tr>
   
 </table>
 
</form>
</body>
</html>