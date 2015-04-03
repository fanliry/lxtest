<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean baseBarcodeNew = false;    //生成新条码
	boolean baseBarcodeAll = false;    //全部条码
	boolean baseBarcodeUse = false; //可用条码
	boolean baseBarcodeUsed = false;  //已用条码
	boolean baseBarcodeFUsed = false; //标记已使用
	boolean baseBarcodePrint = false;  //打印条码
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("baseBarcodeNew") != null){
			baseBarcodeNew = true;
		}
		if(hsPopedom.get("baseBarcodeAll") != null){
			baseBarcodeAll = true;
		}
		if(hsPopedom.get("baseBarcodeUse") != null){
			baseBarcodeUse = true;
		}
		if(hsPopedom.get("baseBarcodeUsed") != null){
			baseBarcodeUsed = true;
		}
		if(hsPopedom.get("baseBarcodeFUsed") != null){
			baseBarcodeFUsed = true;
		}
		if(hsPopedom.get("baseBarcodePrint") != null){
			baseBarcodePrint = true;
		}
    }
%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/inspectClass.js"></script>
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;

	//检查数量是否为数字
	function IsNum(num)
	{ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	} 
	
	//查询
	function Search(type)
	{

		if(type == 1){
			condition = "";
			
		}else if(type == 2){
			condition = "&is_used=N"
			
		}else{
			condition = "&is_used=Y"
		}
		
		var linerow = parent.page.document.getElementById("lineviewrow").value;		//每页显示行数
		window.parent.list.location.href = ac + "baseBarcodeAction" + condition + "&linerow=" + linerow;
	}
	
	//增加新条码
	function addData()
	{
		var head = document.getElementById("head").value;
		var start = document.getElementById("start").value;
		var amount = document.getElementById("amount").value;
		
		
		if(head == null || head.length < 1)
		{
			alert("【条码头】不能为空！");
			return;
		}
		if(start == null || start.length < 1 || !IsNum(start) || parseInt(start) < 1 || parseInt(start) > 99999)
		{
			alert("【起始】不能为空且只能为0~100000间的数字！");
			return;
		}
		if(amount == null || amount.length < 1 || !IsNum(amount) || parseInt(amount) < 0 || (parseInt(amount) + parseInt(start)) > 99999)
		{
			alert("【数量】不能为空且只能为大于0的数字且（起始+数量<100000）！");
			return;
		}

		var linerow = parent.page.document.getElementById("lineviewrow").value;		//每页显示行数
		condition = "&head=" + head + "&start=" + start + "&amount=" + amount + "&linerow=" + linerow;
		
		window.parent.list.location.href = ac + "baseBarcodeAction&method=ricoExecAdd" + condition;
	}
	
	// 修改条形码
	function updateData()
	{
		var ids = "";
		var check_ids = parent.list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++)
		{
			if(check_ids[i].checked)
			{
				ids += check_ids[i].value + ",";
			}
		}
		if(ids == "")
		{
			alert("请至少选择一条记录！");
			return;
		}
		
		var linerow = parent.page.document.getElementById("lineviewrow").value;		//每页显示行数
		condition = "&ids=" + ids + "&linerow=" + linerow;
		
		window.parent.list.location.href = ac + "baseBarcodeAction&method=ricoExecEdit" + condition;
	}
	
	//打印条码
	function PrintCode()
	{
		var ids = "";
		var check_ids = parent.list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++)
		{
			if(check_ids[i].checked)
			{
				ids += check_ids[i].value + ",";
			}
		}
		if(ids == ""){
			alert("请至少选择一条记录！");
			return;
		}
		updateData();
		ids = ids.substring(0, ids.length-1);
		
		//showModalDialog("<%=request.getContextPath()%>/jsp/maintenance/tray_code_print.jsp", ids, "dialogWidth=600px;dialogHeight=400px");
		window.open("<%=request.getContextPath()%>/standard/jsp/base/barcode/base_barcode_print.jsp?ids="+ids, '', 
			"Width=600,Height=500; scrollbars=yes, resizable=yes");
	}
-->
</script>
</head>

<body>
  <table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr><td height="100%" valign="top">
 
  <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
    <tr>
      <td style="height:25px;" class="TD_LIST_TITLE4" align="center" colspan="2">新条码生成</td>
    </tr>
    <tr>
      <td class="yx1" align="right">条码头：</td>
      <td class="xx1"><input name="head" size="10" maxlength="7">
    </tr>
    <tr>
      <td class="yx1" align="right">起始：</td>
      <td class="xx1"><input name="start" size="10" maxlength="7">
    </tr>
    <tr>
      <td class="yx1" align="right">数量：</td>
      <td class="xx1"><input name="amount" size="10">
    </tr>
    <%if(baseBarcodeNew){%>
    <tr>
      <td colspan="2" align="center">
        <input onclick="addData()" type="button" name="button1" value="生成新条码" style="width: 100px" class="BUTTON_STYLE1">
      </td>
    </tr>
     <%}%>
  </table>

  <br>
 
  <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
  
    <tr>
      <td style="height:25px;" class="TD_LIST_TITLE4" align="center">查询</td>
    </tr>
    <%if(baseBarcodeAll){%>
    <tr>
      <td class="xx1" align="center">
        <input onclick="Search(1)" type="button" name="button2" value="全部条码" style="width: 100px" class="BUTTON_STYLE1">
      </td>
    </tr>
    <%}%>
    <%if(baseBarcodeUse){%>
    <tr>
      <td class="xx1" align="center">
        <input onclick="Search(2)" type="button" name="button2" value="可用条码" style="width: 100px" class="BUTTON_STYLE1">
      </td>
    </tr>
    <%}%>
    <%if(baseBarcodeUsed){%>
    <tr>
      <td align="center">
        <input onclick="Search(3)" type="button" name="button3" value="已用条码" style="width: 100px" class="BUTTON_STYLE1">
      </td>
    </tr>
    <%}%>
  </table>
 
  <br> 
 
  <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
    <tr>
      <td style="height:25px;" class="TD_LIST_TITLE4" align="center">处理</td>
    </tr>
    <%if(baseBarcodeFUsed){%>
    <tr>
      <td class="xx1" align="center">
        <input onclick="updateData()" type="button" name="button4" value="标记已使用" style="width: 100px" class="BUTTON_STYLE1">
      </td>
    </tr>
    <%}%>
    <%if(baseBarcodePrint){%>
    <tr>
      <td align="center">
        <input onclick="PrintCode()" type="button" name="button4" value="打印条码" style="width: 100px" class="BUTTON_STYLE1">
      </td>
    </tr>
    <%}%>
  </table>
 
     </td>
   </tr>
 </table>
 
</body>
</html>
