<%@ page contentType="text/html; charset=GB2312"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<title>物品规格编辑窗口</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>css/table.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src="<%=request.getContextPath()%>/js/tools.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/js/selectTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/inspectClass.js"></script>

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
			if(document.getElementById("name").value.length>50)
			{
				alert("备注不能超过50个字符！");
				return false;
			}
	   var actionStr="rmsService?actionCode=updGoods"	
	  			 + "&number=" + document.getElementById("number").value 
        		 + "&name=" + document.getElementById("name").value
        		 + "&id=" + document.getElementById("id").value
        		 + "&remark=" + document.getElementById("remark").value;
    	window.returnValue = actionStr;
	    window.close();	
  	}
  	else
  	{
  	  alert("请填写物品规格名称");
  	}
  }
   //检查唯一
   function check(){
		  var name=document.getElementById("name").value;
	  	//检查规格名称是否唯一
			inspectClass.checkGoodsSpec(name,function(data){
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
<%

    request.setCharacterEncoding("ISO-8859-1");
	String id=request.getParameter("id");
	String name=request.getParameter("name");
	String number=request.getParameter("number");
	String remark=request.getParameter("remark");

	number=new String(number.getBytes("ISO-8859-1"),"gb2312");	
	name=new String(name.getBytes("ISO-8859-1"),"gb2312");
	remark=new String(remark.getBytes("ISO-8859-1"),"gb2312");
%>
<body>
<form method="post" action="" name="myform">
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td class="TD_ADD"><div align="left"><input type="text" name="id" id="id" value="<%=id%>"></div></td>
 </table>
 
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_ADD">
   <tr>
     <td height="25" class="TD_ADD"><div align="right">编码：</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="number" id="number" value="<%=number%>" disabled></div></td>
     <td class="TD_ADD"><input onclick="update()" type="button" name="button1" value="修改" class="BUTTON_STYLE1"></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">包装规格：</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="name" id="name" value="<%=name%>" onchange="check()"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></div> 
      <font color="#FF0000"><div id="checkOnly"> </div></font></td>
     <td class="TD_ADD"><input onclick="down()" type="button" name="button2" value="取消" class="BUTTON_STYLE1"></td>
   </tr>
    <tr>
     <td height="25" class="TD_ADD"><div align="right">描述：</div></td>
     <td class="TD_ADD"><div align="left">       <textarea name="remark" id="remark"  onKeyUp="if(checkValue(this.value))execCommand('undo')"><%=remark%></textarea>
     </div></td>
     <td class="TD_ADD"></td>
   </tr>  
 </table>
 </form>
</body>
</html>