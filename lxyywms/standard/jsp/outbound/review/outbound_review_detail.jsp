
<%@ page contentType="text/html; charset=GBK"%>
<%
	
	//单据ID
	String invoiceid = request.getParameter("invoiceid");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
		judgmentTool.isOrderChecked('<%=invoiceid%>', 'header', setButton);
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
	
	
	
	
	
 	//确认复核
	function okReview(invoiceid)
	{
		LockButton();
		//作业ID
		var jobid = document.getElementById("jobid").value;
		//作业详细ID
		var jobdetailid = document.getElementById("jobdetailid").value;
		
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
		if(jobid == null || jobid.length < 1){
			alert("【作业编号】不能为空！");
			UnLockButton();
			return;
		}
		if(jobdetailid == null || jobdetailid.length < 1){
			alert("【作业详细编号】不能为空！");
			UnLockButton();
			return;
		}
		
		
		//************************************************
		if(confirm("你确定复核？"))
		{				
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=reviewAction&method=detailReview&flag=1";
			//DWREngine.setAsync(false);
			myIframe.location.href = strUrl + "&invoiceid=" + invoiceid + "&invoicedetailid=" + detailid + "&renum=" + invoicenum + "&jobid=" + jobid + "&jobdetailid=" + jobdetailid;
			
			//DWREngine.setAsync(true);
	
		}else{
			UnLockButton();
		}
	}
	//取消复核
	function cancelReview()
	{
		LockButton();
		var k = 0;
		var id = "";//复核ID
		var check_ids = detail.document.getElementsByName("check_id");
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
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=reviewAction&method=detailReview&flag=2";
			//DWREngine.setAsync(false);
			myIframe.location.href = strUrl + "&invoiceid=" + invoiceid + "&crossid=" + id;
			//var d = new Date();
			//detail.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=reviewAction&flag=5&invoiceid="+invoiceid+"&data="+d.toString();
			//DWREngine.setAsync(true);
	
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
     <td height="25"><div class="font_001F56_bold_12">当前位置：出库管理 -&gt; 复核 -&gt; 按详细复核</div></td>
   </tr>
  </table>
	
 <!-- ======== 作业列表开始 ======== -->
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST2">
   <tr>
	 <td valign="top"  height="305">
	   <iframe id="myIframe" src="<%=request.getContextPath()%>/BMSService?actionCode=reviewAction&flag=4&invoiceid=<%=invoiceid%>" frameborder="0" width="100%" height="100%">
	   </iframe>
	 </td>
   </tr>
 </table>
<table width="98%" height="5"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>	
	<FORM id="form1" name="form1">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
        
        <tr>
          <td  align="right" class="yx1">出库单号：</td>
          <td  class="yx" >
          	 <input type="text" name="invoiceid" value="<%=invoiceid%>" class="input_readonly" readonly  style="height:18px;width:100px;"/>
          </td>
          <td  align="right" class="yx1">收&nbsp;货&nbsp;人：</td>
          <td  class="yx" >
	          <input type="text" name="customername"  class="input_readonly" readonly  style="height:18px;width:200px;"/>         
          </td>
          <td  align="right" class="yx1">作业编号：</td>
          <td  class="xx1" >
          	 <input type="text" name="jobid" class="input_readonly" readonly   style="height:18px;width:100px;"/> 
          </td>
        </tr>
        <tr>
          
          <td  align="right" class="yx1">详细编号：</td>
          <td  class="yx" >
          	 <input type="text" name="jobdetailid" class="input_readonly" readonly   style="height:18px;width:100px;"/> 
          </td>
          <td  align="right" class="yx1">物&nbsp;&nbsp;&nbsp;&nbsp;品：</td>
          <td  class="yx" >
          	 <input type="text" name="product_name" class="input_readonly" readonly   style="height:18px;width:100px;"/> 
          </td>
          <td  align="right" class="yx1"><span class="red">*</span>复核数量：</td>
          <td  class="xx1">
	          	<input type="text" name="invoicenum" size="16"   style="height:18px;width:100px;"/>
          		<input type="hidden" name="uninvoicenum" size="16"  />
          </td>
        </tr>

		<tr>
        	<TD colspan="6" height="2">
        		<input type="hidden" name="invoicedetailid"/>
        	</TD>
        </tr>
        <tr>
          <td height="27" colspan="6" align="center" ><input name="button_add" type="button" class="button_add" id="button_add" value="&nbsp;&nbsp;确认复核" onClick="okReview();" />
            <input name="button_delete" type="button" class="button_delete" id="button_delete" value="&nbsp;&nbsp;取消复核" onclick="cancelReview();" />
            <input name="button" type="reset" class="button_reset" id="button" value="&nbsp;&nbsp;重置" />
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="关闭" onClick="window.close();" />
          </td>
        </tr>
      </table>
      
 </FORM>
	 <!-- ======== 作业详细列表开始 ======== -->
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="TABLE_LIST2">    
	<tr>
         <td valign="top"  height="240" > <!--  如要有横着的滚动条，高度设为185即可 没有是165 -->
					<iframe id="detail" src="<%=request.getContextPath()%>/BMSService?actionCode=reviewAction&flag=5&invoiceid=<%=invoiceid%>"   width="100%" height="100%"   frameborder="0" ></iframe>
         </td>
    </tr>
 </table>
 <!--  ======== 作业详细列表结束 ======== -->
  </body>
</html>
