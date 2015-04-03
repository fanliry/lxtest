<%@ page contentType="text/html; charset=GB2312"%>

<%
    request.setCharacterEncoding("ISO-8859-1");
	String id=request.getParameter("id");
	String number=request.getParameter("number");
	String city=request.getParameter("city");
	String typeId=request.getParameter("typeId");
	String fee=request.getParameter("fee");
	String mileage=request.getParameter("mileage");
	
	city= new String(city.getBytes("ISO-8859-1"),"gb2312");
%>


<html>
<head>
<title>集装箱费用信息编辑窗口</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/table.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/js/selectTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script src="<%=request.getContextPath()%>/js/tools.js"></script>

<script>
<!--
	  function update()
	  {
	  	var name=document.getElementById("city").value;
	  	if(name!="")
	  	{
	  		if(name.length>40)
			{
				alert("不能超过40个字符！");
				return false;
			}
			if(document.getElementById("type").value=="")
			{
				alert("请选择型号！");
				return false;
			}
	       var actionStr="rmsService?actionCode=updFee"
		  			 + "&number=" + document.getElementById("number").value 
	        		 + "&mileage="+document.getElementById("mileage").value
	        		 + "&type=" + document.getElementById("type").value
	        		 + "&fee=" + document.getElementById("fee").value
	        		 + "&id=" + document.getElementById("id").value
	        		 + "&city=" + document.getElementById("city").value;
	    	window.returnValue = actionStr;
		    window.close();
	  		
	  	}else
	  	{
	  	  alert("请输入到达城市的名称");
	  	}
	  }
	  //关闭窗体
	  function down()
	  {
	  	window.close();
	  }
	  
	   function onlo()
	  {
	  	 selectObject('<%=typeId%>','type','8');  //规格
	  }
	  
	   function selectCity()
	  {
	  	var strUrl = "<%=request.getContextPath()%>/jsp/dictionary/tab/chooseArea.jsp";
		var  result = showModalDialog(strUrl,'','dialogWidth=250px;dialogHeight=400px');
		if(result != null && result.length > 1){
			document.getElementById("city").value=result;
		}
	  }
-->
</script>

</head>
<body onload="onlo()">

<form method="post" action="" name="myform">
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td class="TD_ADD"><div align="left"><input type="hidden" name="id" id="id" value="<%=id%>"/></div></td>
 </table>
 
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_ADD">
   <tr>
     <td width="30%" height="25" class="TD_ADD"><div align="right">编码：</div></td>
     <td width="57%" class="TD_ADD"><div align="left"><input type="text" name="number" id="number"  value="<%=number%> " disabled/></div></td>
     <td width="13%" class="TD_ADD"><input onclick="update()" type="button" name="add" value="修改" class="BUTTON_STYLE1"></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">到达地：</div></td>
     <td class="TD_ADD">
     <div align="left">
       <input type="text" name="city" id="city" value="<%=city%>">
       <input type="button" value="..." onclick="selectCity()"  class="BUTTON_STYLE1">
     </div>
     </td>
     <td class="TD_ADD"><input onclick="down()" type="button" name="add" value="取消" class="BUTTON_STYLE1"></td>
   </tr>
    <tr>
     <td height="25" class="TD_ADD"><div align="right">集装箱箱型：</div></td>
	     <td class="TD_ADD">
		     <div align="left">
		       <select id="type" name="type" >
                 <option value=""></option>
               </select>
</div>
	     </td>
     <td class="TD_ADD"></td>
   </tr>
    <tr>
     <td height="25" class="TD_ADD"><div align="right">集装箱费：</div></td>
     <td class="TD_ADD"><div align="left">
       <input type="text" name="fee" id="fee"  value="<%=fee%>">

       </div></td>
     <td class="TD_ADD"></td>
   </tr>
    <tr>
     <td height="25" class="TD_ADD"><div align="right">里程：</div></td>
     <td class="TD_ADD"><div align="left">
       <input type="text" name="mileage" id="mileage"  value="<%=mileage%>">
       </div></td>
   </tr> 
     <tr>
     <td height="25" class="TD_ADD"><div align="right"></div></td>
     <td class="TD_ADD"><div align="left">
     </div></td>
   </tr>  </table>
 </form>
</body>
</html>