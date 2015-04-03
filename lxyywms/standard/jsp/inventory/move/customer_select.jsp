<%@ page contentType="text/html; charset=GB2312"%>
<%
	String customertype = request.getParameter("customertype");
	if(customertype==null)
	{
		customertype="0101_01";
	}
%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
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
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/wdsService?actionCode=";
	var condition = "";
	
	//查询
	function queryData()
	{
		var descr_c = document.getElementById("descr_c").value;
		var descr_e = document.getElementById("descr_e").value;
		var customertype = document.getElementById("customertype").value;
		
		condition = "&flag=2" + "&descr_c=" + descr_c + "&descr_e=" + descr_e + "&customertype=" + customertype;
		myIframe.location.href = ac + "CustomerAction" + condition;
	}
	//确定选择
	function selectCheck()
	{
		 var radio_ids = window.myIframe.document.getElementsByName("radio_id");
		 var strPram = null;
		 if (radio_ids==null)
		 {
			  window.close();
		 }
		 for(var i=0; i<radio_ids.length; i++)   
		 {
		 	 if(radio_ids[i].checked)
			 {
			  	strPram = radio_ids[i].value;
			 }
		 }
		 window.returnValue = strPram;
		 window.close();
	 } 
	//页面加载
	function OnLoad()
	{
		selectType('<%=customertype%>', 'customertype', '0101');
	}
-->
</script>
</head>

<body onLoad="OnLoad();" topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0">

 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div id="dqwz" class="f_l">当前位置：<span>选择客户</span> </div></td>
   </tr>
 </table>

 <!-- ======== 客户查询条件开始 ======== -->
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="query_table">
   <tr class="query_tr">
     <td class="yx1" width="75"><div align="right">客户类型：</div></td>
     <td class="yx"><div align="left"><select name="customertype"><option></option></select></div></td>
     <td class="yx1" width="75"><div align="right">中文描述：</div></td>
     <td class="yx"><div align="left"><input type="text" name="descr_c" size="25" maxlength="50"></div></td>
     <td class="yx1" width="75"><div align="right">英文描述：</div></td>
     <td class="yx"><div align="left"><input type="text" name="descr_e" size="25" maxlength="15"></div></td>
   </tr>
   <tr class="query_tr">
     <td class="yx" height="27" colspan="6"><div align="center">
       <input onclick="queryData()" type="button" name="queryBtn" value="查询" class="button_search">&nbsp;&nbsp;
       <input onclick="selectCheck()" type="button" name="saveBtn" value="确定" class="BUTTON_STYLE1">
     </div></td>
   </tr>
 </table>
 <!-- ======== 客户查询条件结束 ======== -->


 <table width="100%" height="5"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>


 <!-- ======== 查询列表显示数据开始 ======== -->
 <table width="98%" height="280" border="1" align="center" cellpadding="0" cellspacing="0" class="outer_table">
   <tr>
     <td class="outer_td">
 	   <iframe id="myIframe" src="<%=request.getContextPath()%>/jsp/selectPages/customer_list.jsp" frameborder="0" width="100%" height="100%">
 	   </iframe>
     </td>
   </tr>
 </table>
 <!-- ======== 查询列表显示数据结束 ======== -->

</body>
</html>
