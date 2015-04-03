<%@ page contentType="text/html; charset=GB2312"%>

<%
//设置重复提交的标志变量，begin为首次加载，finish为已提交
	session.setAttribute("submitFlag","begin");
	String strNumber=(String)request.getAttribute("number");
%>

<html>
<head>
<title>物品规格编辑窗口</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/table.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src="<%=request.getContextPath()%>/js/tools.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/js/selectTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/inspectClass.js"></script>

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
			if(document.getElementById("remark").value.length>15)
			{
				alert("不能超过15个字符！");
				return false;
			}
			
	   var actionStr="rmsService?actionCode=addPack"	
	  			 + "&number=" + document.getElementById("number").value 
        		 + "&name=" + document.getElementById("name").value
        		 + "&remark=" + document.getElementById("remark").value;
    	window.returnValue = actionStr;
	    window.close();	
  	}
  	else
  	{
  	  alert("请填写包装规格名称");
  	}
  }
  function check()
  {
	  var name=document.getElementById("name").value;
	  	//检查规格名称是否唯一
		inspectClass.checkPackSpec(name,function(data){
		if(data=="0"){
			dwr.util.setValue("checkOnly","此规格名已经存在！");
			document.myform.button1.disabled=true;
		}
		if(data=="1"){
			dwr.util.setValue("checkOnly","此规格名还未使用！");
			document.myform.button1.disabled=false;
			}
 	 });
  }
   //关闭窗口
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
     <td height="25" class="TD_ADD"><div align="right">编码：</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="number" id="number" value="<%=strNumber%>" disabled></div></td>
     <td class="TD_ADD"><input onclick="inspect()" type="button" name="button1" value="新增" class="BUTTON_STYLE1"></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">包装规格：</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="name" id="name" onchange="check()"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></div>
     <font color="#FF0000"><div id="checkOnly"></div></font></td>
     <td class="TD_ADD"><input onclick="down()" type="button" name="button2" value="取消" class="BUTTON_STYLE1" ></td>
   </tr>
    <tr>
     <td height="25" class="TD_ADD"><div align="right">描述：</div></td>
     <td class="TD_ADD"><div align="left">       <textarea name="remark" id="remark"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></textarea>
     </div></td>
     <td class="TD_ADD"></td>
   </tr>  
 </table>
 </form>
</body>
</html>