<%@ page contentType="text/html; charset=GB2312"%>

<% 
     request.setCharacterEncoding("ISO-8859-1");
  	 String id=request.getParameter("id");
  	 String strMNJobName=request.getParameter("strMNJobName");
  	 String strJobmanid=request.getParameter("strJobmanid");
  	 String strIscurrent=request.getParameter("strIscurrent");
  	 String strInOutType=request.getParameter("strInOutType");
  	 
  	 strMNJobName=new String(strMNJobName.getBytes("ISO-8859-1"),"gb2312");
  	 strIscurrent=new String(strIscurrent.getBytes("ISO-8859-1"),"gb2312");
  	 
  	 
%>

<html>
<head>
<title>部门信息编辑窗口</title>

<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/table.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/js/tools.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/js/selectTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/js/calendar.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/inspectClass.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript">

<!--
	//修改
	  function update()
	  {
	  
	  		if(document.getElementById("jobman").value=="")
	  		{
	  			alert("请选择当班负责人！");
	  			return false;
	  		}
	  		
	  		var actionStr = "rmsService?actionCode=udptbjobnumber"
				+ "&id=" + document.getElementById("id").value  //上一班次
	  			//当前班次  
	    		+ "&jobman=" + document.getElementById("jobman").value        		 //负责人
	        	+ "&inouttype=" + document.getElementById("inouttype").value;		 //出入类型
	  		
	  		if(document.getElementById("iscurrent").checked==true)
	  		{
	  			inspectClass.checkJob(<%=strInOutType%>,function(data){
	  			if(data=="1")
	  			{
	  			 var del = confirm("该系统中存在当前班次！是否要将该班次设置为当前班次！");
					if(del){
	    				window.returnValue = actionStr+"&iscurrent=Y&mark=1";
	    				window.close();
					}else{
	    				window.returnValue = actionStr+"&iscurrent=N&mark=0";
	    				window.close();
					}
	  			}if(data=="0"){
	    				window.returnValue = actionStr+"&iscurrent=Y";
	    				window.close();
	  				}
	  			});
	  			}else{
						window.returnValue = actionStr+"&iscurrent=N";
	    				window.close();
				}
	  		}

	  
	   function down()
	  {
	  	window.close();
	  }
	  function onlo()
	  {
	  	 selectObject('<%=strJobmanid%>','jobman','16');
	 	 selectType('<%=strInOutType%>','inouttype','bcrcnx');
	 	 
	  }
-->
</script>
</head>

<body onload="onlo()"> 
<form method="post"  action="" name="myform">
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td class="TD_ADD"><div align="left"><input type="hidden" name="id" id="id" value="<%=id%>"></div></td></tr>
 </table>
 
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_ADD">
   <tr>
     <td height="25" class="TD_ADD" ><div align="right">班次名称：</div></td>
     <td class="TD_ADD"><div align="left"><input type="text" name="jobName" id="jobName" value="<%=strMNJobName%>" disabled>
     </div></td>
     <td class="TD_ADD"><input onclick="update()" type="button" name="add" value="修改" class="BUTTON_STYLE1" ></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD" ><div align="right">当班负责人：</div></td>
     <td class="TD_ADD"><div align="left">     
      <select name="jobman" id="jobman">
       </select>
     </div></td>
     <td class="TD_ADD"><input onclick="down()" type="button" name="add" value="取消" class="BUTTON_STYLE1"></td>
   </tr>
   <tr>
     <td height="25" class="TD_ADD"><div align="right">班次入出类型：</div></td>
     <td class="TD_ADD"><div align="left">       
     <select name="inouttype" id="inouttype">
       <option value=""></option>
       </select>
     </div></td>
 <td class="TD_ADD">&nbsp;</td>
   </tr> 
   <tr>
     <td height="25" class="TD_ADD" ><div align="right">是否当前班次：</div></td>
     <td class="TD_ADD"><div align="left">      
      <input name="iscurrent" type="checkbox" id="iscurrent" value="" <%if(strIscurrent.equals("Y")){%>checked<%}%> class="input_checkbox">
     </div></td>
     <td class="TD_ADD">&nbsp;</td>
   </tr> 
 </table>
</form>
</body>
</html>