<%@ page contentType="text/html; charset=GB2312"%>
<%
//设置重复提交的标志变量，begin为首次加载，finish为已提交
	session.setAttribute("submitFlag","begin");
	String strNumber=(String)request.getAttribute("number");
%>
<html>
<head>
<title>信息部信息编辑窗口</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/table.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/js/tools.js"></script>
<script>
<!--
		  function inspect()
		  {
		  //电话号码验证
		 	 var TEL=document.getElementById("phone").value;
	  		var i,j,strTemp;     
			strTemp="0123456789-()# ";     
			for (i=0;i<TEL.length;i++)     
			{     
			j=strTemp.indexOf(TEL.charAt(i));     
			if (j==-1)     
				{         
				alert('您输入的电话号码有误！');
				return false;     
				}     
			}   
		  	var name=document.getElementById("name").value;
		 	 if(name!="")
		  	 {
		  		if(name.length>15)
				{
					alert("不能超过15个字符！");
					return false;
				}
		       var actionStr="rmsService?actionCode=addComm"
		       		     + "&person=" + document.getElementById("person").value
			  			 + "&number=" + document.getElementById("number").value 
		        		 + "&name="+document.getElementById("name").value
		        		 + "&phone=" + document.getElementById("phone").value
		        		 + "&remark=" + document.getElementById("remark").value;
		    	window.returnValue = actionStr;
			    window.close();
		  	  }else{
		  	   alert("请填写信息部门名称");
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
<body>
<form method="post" action="" name="myform">
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="5"></td></tr>
 </table>
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_ADD">
   <tr>
     <td height="25" class="TD_ADD"><div align="right">信息部编号：</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="number" id="number" value="<%=strNumber%>" disabled></div></td>
     <td class="TD_ADD"><input onclick="inspect()" type="button" name="add" value="新增" class="BUTTON_STYLE1" ></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">信息部名称：</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="name" id="name" onKeyUp="if(checkValue(this.value))execCommand('undo')"></div></td>
     <td class="TD_ADD"><input onclick="down()" type="button" name="add" value="取消" class="BUTTON_STYLE1"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">负责人：</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="person" id="person"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></div></td>
     <td class="TD_ADD"></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">电话：</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="phone" id="phone"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></div></td>
     <td class="TD_ADD"></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">备注：</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="remark" id="remark"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></div></td>
     <td class="TD_ADD"></td>
   </tr>
 </table>
 </form>
</body>
</html>