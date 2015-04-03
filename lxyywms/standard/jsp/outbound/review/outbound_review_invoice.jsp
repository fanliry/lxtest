
<%@ page contentType="text/html; charset=GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	//出库单据ID
	String invoiceid = request.getParameter("invoiceid");
%>
<html>
  <head>
    <title>浙江刚玉物流仓库管理系统</title>  
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
	<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />

 <script>
 	//检查数量是否为数字
	function IsNum(num){ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
	//检查数据是否为浮点数
	function IsFloat(ch){
		var re = /^\d+(\.\d+)?$/;
		return re.test(ch);
	}
	function IsRight(print_date){ 
		var str = /^(\d{4})-(\d{2})-(\d{2})$/;
		return(str.test(print_date));
	}
	/*锁定按钮*/
	function LockButton(){	
		document.getElementById("button_add").disabled = true;
		document.getElementById("button_delete").disabled = true;
	}
	/*释放按钮*/
	function UnLockButton(){
		document.getElementById("button_add").disabled = false;
		document.getElementById("button_delete").disabled = false;
	}
	
	//设置复核按钮可用与否
	function setBtn()
	{
		judgmentTool.isOrderChecked('<%=invoiceid%>', 'detail', setButton);
	}
	//设置复核按钮可用与否
	function setButton(str)
	{
		if(str>0)
		{
			document.getElementById("button_add").disabled = true;
			document.getElementById("button_delete").disabled = true;
			document.getElementById("button").disabled = true;
		}
		else
		{
			document.getElementById("button_add").disabled = false;
			document.getElementById("button_delete").disabled = false;
			document.getElementById("button").disabled = false;
		}
	}
	
	
 	//确定复核
	function okReview()
	{
		LockButton();
		//出库单据ID
		var invoiceid = document.getElementById("invoiceid").value;
		//出库单详细ID
		var detailid = document.getElementById("invoicedetailid").value;
		//复核数量
		var invoicenum = document.getElementById("invoicenum").value;
		//未复核数量
		var uninvoicenum = document.getElementById("uninvoicenum").value;

		if(invoiceid == null || invoiceid.length < 1){
			alert("【出库单】不能为空！");
			UnLockButton();
			return;
		}
		
		if(detailid == null || detailid.length < 1){
			alert("【产品】不能为空！");
			UnLockButton();
			return;
		}
		
		if(invoicenum == null || invoicenum.length < 1 || !IsFloat(invoicenum)){
			alert("【数量】不能为空且只能为数字！");
			UnLockButton();
			return;
		}
		if(parseFloat(invoicenum) <= 0){
			alert("【复核数量(" + invoicenum + ")】不能小于0！");
			UnLockButton();
			return;
		}
		if(parseFloat(invoicenum) > parseFloat(uninvoicenum)){
			alert("【复核数量(" + invoicenum + ")】不能大于未复核数量【(" + uninvoicenum + ")】！");
			UnLockButton();
			return;
		}


		//************************************************
		if(confirm("你确定复核？"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=reviewAction&method=invoiceReview&flag=1";

			list.location.href = strUrl + "&invoiceid=" + invoiceid + "&invoicedetailid=" + detailid + "&renum=" + invoicenum;

		}else{
			UnLockButton();
		}
	}
	//取消复核
	function cancelReview()
	{
		LockButton();
		var k = 0;
		var id = "";//出库单详细ID
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++)
		{
			if(check_ids[i].checked)
			{
				id = check_ids[i].value;
				k++;
			}
		}
		if(id == ""){
			alert("请选择要取消的记录！");
			UnLockButton();
			return;
		}
		if( k == 0 ){
			alert("请选择一条记录！");
			UnLockButton();
			return;
		}else if(k != 1){
			alert("只能选择一条记录！");
			UnLockButton();
			return;
		}
		//出库单据ID
		var invoiceid = document.getElementById("invoiceid").value;
		if(invoiceid == null || invoiceid.length < 1){
			alert("【出库单】不能为空！");
			UnLockButton();
			return;
		}
		
		//************************************************
		if(confirm("你确定取消复核？"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=reviewAction&method=invoiceReview&flag=2";
			list.location.href = strUrl + "&invoiceid=" + invoiceid + "&invoicedetailid=" + id;
	
		}else{
			UnLockButton();
		}
	}
	

 function onLoad()
 {
 	var cunstomername = window.dialogArguments;
	document.getElementById("customername").value = cunstomername;
	setBtn();
 }
 

 </script>
</head>
  <body onload="javascript:onLoad();">
  
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：出库管理 -&gt; 复核 -&gt; 按订单复核</div></td>
   </tr>
  </table>
	<FORM id="form1">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
        <tr>
          <td width="15%" align="right" class="yx1"  >出库单号：</td>
          <td width="35%" class="yx" >
          	  <input type="text" name="invoiceid" value="<%=invoiceid%>" class="input_readonly" readonly  style="height:18px;width:100px;"/>
          </td>
          <td width="15%" align="right" class="yx1">收&nbsp;货&nbsp;人：</td>
          <td width="35%" class="xx1">
	          <input type="text" name="customername"  class="input_readonly" readonly  style="height:18px;width:200px;"/>
          </td>
        </tr>
        <tr>
          <td  align="right" class="yx1"  >品&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
          <td  class="yx" >
          	 <input type="text" name="product_name" class="input_readonly"    style="height:18px;width:200px;"/>
          </td>
          <td align="right" class="yx1"><span class="red">*</span>数&nbsp;&nbsp;&nbsp;&nbsp;量：</td>
          <td class="xx1">
          	<input type="text" name="invoicenum" size="16"   style="height:18px;width:100px;"/>
          	<input type="hidden" name="uninvoicenum" size="16"  />
          </td>
        </tr>
        
        <tr>
          <td height="27" colspan="4" align="center" ><input name="button_add" type="button" class="button_add" id="button_add" value="&nbsp;&nbsp;确认复核" onClick="okReview();" />
            <input type="hidden" name="invoicedetailid"/>
            <input name="button_delete" type="button" class="button_delete" id="button_delete" value="&nbsp;&nbsp;取消复核" onclick="cancelReview();" />
            <input name="button" type="reset" class="button_reset" id="button" value="&nbsp;&nbsp;重置" />
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="关闭" onClick="window.close();" />
          </td>
        </tr>
      </table>
      </FORM>
 <table width="98%" height="10"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>


 <!-- ======== 明细列表开始 ======== -->
 <table width="98%" height="400" border="0" align="center" cellpadding="0" cellspacing="0">
   <tr>
	 <td>
	   <iframe id="list" src="<%=request.getContextPath()%>/BMSService?actionCode=reviewAction&flag=3&invoiceid=<%=invoiceid%>" scrolling="auto" frameborder="0" width="100%" height="100%">
	   </iframe>
	 </td>
   </tr>
 </table>
 <!-- ======== 明细列表结束 ========  -->

  </body>
</html>
