<%@ page contentType="text/html; charset=GBK"%>
<%
	String customertype = request.getParameter("customertype");
	if(customertype==null)
	{
		customertype="0101_01";
	}
%>
<html>
<head>
<title>仓储配送管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/js/selectTool.js"></script>
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = "";
	
	//查询
	function queryData()
	{
		var userCode = document.getElementById("strUserCode").value;
		var userName = document.getElementById("strUsername").value;
		var unitCode = document.getElementById("strUnitCode").value;
		
		var linerow  = list.document.getElementById("lineviewrow").value;
		
		condition = "&userCode=" + userCode + "&userName=" + userName + "&unitCode=" + unitCode + "&linerow="+linerow;
		list.location.href = ac + "UserAction" + condition;
	}
	
	function OnOk()
	{
		var backmsg = null;
		var check_ids = list.document.getElementsByName("radio_id");
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
-->
</script>
</head>
<body>
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr><td>
     
	  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
		<tr>
		  <td height="25"><div class="font_001F56_bold_12">当前位置：选择用户</div></td>
		</tr>
	  </table>

	  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
	    <tr>
	      <td width="100px" class="yx1" align="right">用户代码：</td>
	      <td class="yx"><input type="text" name="strUserCode" size="20" maxlength="20"></td>
	      <td width="100px" class="yx1" align="right">用户名：</td>
	      <td class="yx"><input type="text" name="strUsername" size="20" maxlength="20"></td>
	      <td width="100px" class="yx1" align="right">部门编号：</td>
	      <td class="xx1"><input type="text" name="strUnitCode" size="20" maxlength="20"></td>
	    </tr>
	    <tr>
          <td height="30" colspan="6" align="center" valign="bottom">
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
				src="<%=request.getContextPath()%>/standard/jsp/common/common_user_list.jsp"></iframe>
	      </td>
	    </tr> 
 	  </table>
 	
    </td></tr>
  </table>
  
</body>
</html>
