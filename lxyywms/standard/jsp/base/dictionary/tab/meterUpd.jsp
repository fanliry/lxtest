<%@ page contentType="text/html; charset=GB2312"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<title>计量单位编辑窗口</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="<%=basePath%>standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>standard/style/css/table.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
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
	   var actionStr="BMSService?actionCode=updMeter"
	  			 + "&number=" + document.getElementById("number").value 
        		 + "&name=" + document.getElementById("name").value
        		 + "&id=" + document.getElementById("id").value;
    	window.returnValue = actionStr;
	    window.close();	
  	}
  	else
  	{
  	  alert("请填写单位名称");
  	}
  }
   //检查唯一
   function check(){
		  var name=document.getElementById("name").value;
	  	//检查规格名称是否唯一
			inspectClass.checkMeterName(name,function(data){
			if(data=="0"){
			dwr.util.setValue("checkOnly","此单位名已经存在！");
			document.myform.button1.disabled=true;
			}
			if(data=="1"){
			dwr.util.setValue("checkOnly","此单位名还未使用！");
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
<%

    request.setCharacterEncoding("ISO-8859-1");
	String id=request.getParameter("id");
	String number=request.getParameter("number");
	String name=request.getParameter("name");
	
	name=new String(name.getBytes("ISO-8859-1"),"gb2312");
	number=new String(number.getBytes("ISO-8859-1"),"gb2312");
%>

<body>
<form method="post" action="" name="myform">
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
    <td class="TD_ADD"><div align="left"><input type="hidden" name="id" id="id" value="<%=id%>"></div></td>
 </table>
 
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_ADD">
   <tr>
     <td height="25" class="TD_ADD"><div align="right">单位编号：</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="number" id="number" value="<%=number%>" disabled></div></td>
     <td class="TD_ADD"><input onclick="inspect()" type="button" name="button1" value="修改" class="BUTTON_STYLE1"></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">单位名称：</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="name" id="name" value="<%=name%>" onchange="check()"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></div>
		<font color="#FF0000"><div id="checkOnly"> </div></font></td>
     <td class="TD_ADD"><input type="button" name="button2" value="取消" class="BUTTON_STYLE1" onclick="down()"></td>
   </tr>
 </table> 
 </form>
</body>
</html>