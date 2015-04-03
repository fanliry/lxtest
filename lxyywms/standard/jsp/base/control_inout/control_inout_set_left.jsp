<%@ page language="java" pageEncoding="GB2312"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseInoutControl"%>
<% 
	BaseInoutControl InoutControl = (BaseInoutControl)request.getAttribute("InoutControl");
	String con_type = "";
	if(InoutControl != null){
		con_type = InoutControl.getCon_type();
	}

%>
<html>
<head>
<title>自动化立体仓库信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
<!--
	
	function OnLoad(){
		
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			
			if(back_msg == "Y"){
				alert("操作成功！");
			}
			else{
				alert(back_msg);
			}
		}
		
	}
	
 -->
</script>
</head>
<body onload="OnLoad()"> 

 <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="MAIN_TABLE">
   <tr>
     <td class="TD_LIST_TITLE" align="center" width="50">选择</td>
     <td class="TD_LIST_TITLE" align="center">类型</td>
   </tr>
   <tr onmouseover="this.bgColor='#E2E8EA'" onmouseout="this.bgColor=''">
     <td class="TD_LIST" align="center"><input type="radio" name="check_id" <%=con_type.equals("1")? "checked":"" %> value="1" class="input_radio" >1</td>
     <td class="TD_LIST" align="center">只能入库</td>
   </tr>
   <tr onmouseover="this.bgColor='#E2E8EA'" onmouseout="this.bgColor=''">
     <td class="TD_LIST" align="center"><input type="radio" name="check_id" <%=con_type.equals("2")? "checked":"" %> value="2" class="input_radio" >2</td>
     <td class="TD_LIST" align="center">只能出库</td>
   </tr>
      <tr onmouseover="this.bgColor='#E2E8EA'" onmouseout="this.bgColor=''">
     <td class="TD_LIST" align="center"><input type="radio" name="check_id" <%=con_type.equals("3")? "checked":"" %> value="3" class="input_radio" >3</td>
     <td class="TD_LIST" align="center">可出入库</td>
   </tr>
   <tr>
     <td height="100%" colspan="2" class="TD_LIST"></td>
   </tr>
 </table>
<form id="the" action=""></form>
</body>
</html>