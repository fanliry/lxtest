<%@ page contentType="text/html; charset=GBK"%>
<%
	String strCustomerType = "";//客户类型
	if(request.getParameter("type") != null){
		strCustomerType = request.getParameter("type");
	}
%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript">
  <!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = "";
	
	function OnOk(){
		var backmsg = null;
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i< check_ids.length; i++){
		 	if(check_ids[i].checked){
				backmsg = check_ids[i].value;
				break;
			}
		}
		if(backmsg==null || backmsg==""){
			alert("请选择一条记录！");
		}else{
			window.returnValue = backmsg;
			window.close();
		}
	} 
	
	//双击
	function ondbClickDo(strVar)
	{
		window.returnValue = strVar;
		window.close();
	}
	
	//查询
	function queryData()
	{
		var customername = document.getElementById("customername").value;
		var customertype = document.getElementById("customertype").value;
		
		var condition = "&customername=" + customername + "&customertype=" + customertype;
		
		list.location.href = ac + "baseCustomerAction&flag=4" + condition;
	}
	
	function OnLoad(){
		var typevalue = "khlx";		//部门类型的值	
		selectType('2', "customertype", typevalue);
	}
-->
</script>
</head>

<body onload="OnLoad()"> 
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr><td>
     
	  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
		<tr>
		  <td height="25"><div class="font_001F56_bold_12">当前位置：选择客户</div></td>
		</tr>
	  </table>
    
	  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
	    <tr>
	      <td class="yx1" width="100">
	      <div align="right">客户分类：</div></td>
	      <td class="yx"><select id="customertype" disabled><option value=""></option></select>
	       <input type="hidden" id="customername" >
	      </td>
	     
	    </tr>  
	    <tr>
          <td height="30" colspan="4" align="center" valign="bottom">
			<input type="button" value="&nbsp;&nbsp;&nbsp;查询" class="button_search" onClick="queryData()">
            <input type="button" value="确定" class="BUTTON_STYLE1" onClick="OnOk()">
           	<input type="button" value="关闭" class="BUTTON_STYLE1" onClick="window.close();" />
          </td>
        </tr>
	  </table>

	</td></tr>
	<tr><td height="5"></td></tr>
	<tr><td valign="top" height="100%">
	 
	  <table width="98%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
	    <tr>
	      <td>
	 	    <iframe id="list" name="list" width="100%" height="100%" frameborder="0" scrolling="auto"
				src="<%=request.getContextPath()%>/standard/jsp/common/common_customer_rf_list.jsp" ></iframe>
	      </td>
	    </tr> 
 	  </table>
 	
    </td></tr>
  </table>
 
</body>
</html>
