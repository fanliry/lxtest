<%@ page contentType="text/html; charset=GB2312"%>

<%
	//设置重复提交的标志变量，begin为首次加载，finish为已提交
	session.setAttribute("submitFlag","begin");
	String strNumber=(String)request.getAttribute("number");
%>

<html>
<head>
<title>卸载工编辑信息窗口</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">

<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script>
<!--
	  function onlo()
	  {
		  selectObject('','duty','17'); //班次
	  }
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
				if(document.getElementById("remark").value.length>50)
				{
					alert("备注不能超过50个字符！");
					return false;
				}
		    var actionStr="BMSService?actionCode=addLabout"	
		  			 + "&number=" + document.getElementById("number").value 
	        		 + "&name=" + document.getElementById("name").value
	        		 + "&remark=" + document.getElementById("remark").value
	        		 + "&duty=" //+ document.getElementById("duty").value
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
     <td height="8"></td>
   </tr>
 </table>
 
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_ADD">
   <tr>
     <td width="26%" height="25" class="TD_ADD"><div align="right">编码：</div></td>
     <td width="53%" class="TD_ADD"><div align="left"><input type="text" name="number" id="number" value="<%=strNumber%>" disabled>
     </div></td>
     <td width="21%" class="TD_ADD"><input onClick="inspect()" type="button" name="add" value="确定" class="BUTTON_STYLE1"></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">姓名：</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="name" id="name"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></div></td>
     <td class="TD_ADD"><input onClick="down()" type="button" name="add" value="取消" class="BUTTON_STYLE1"></td>
   </tr>
 <!--   <tr>
     <td height="25" class="TD_ADD"><div align="right">班次：</div></td>
     <td colspan="2" class="TD_ADD"><div align="left">       <select name="duty">
         <option  selected value=""> </option>
         <option value="甲班">甲班</option>
         <option value="乙班">乙班</option>
         <option value="丙班">丙班</option>
       </select>
     </div></td>
   </tr>-->
   <tr>
     <td height="25" class="TD_ADD"><div align="right">启用：</div></td>
    <td colspan="2" class="TD_ADD"><div align="left">
      <select name="play">
        <option value="1">是</option>
        <option selected value="0">否</option>
      </select> 
         </div>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">描述：</div></td>
     <td colspan="2" class="TD_ADD"><div align="left">
     <textarea name="remark" id="remark" onKeyUp="if(checkValue(this.value))execCommand('undo')"></textarea>
     </div></td>
   </tr>
   
 </table>
 
</form>
</body>
</html>