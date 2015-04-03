
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
	}
	/*释放按钮*/
	function UnLockButton(){
		
		document.getElementById("button_add").disabled = false;
	}
 	//取消收货
	function saveData(invoiceid)
	{
		LockButton();
		var k = 0;
		var id = "";
		var check_ids = myIframe.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id = check_ids[i].value;
				k++;
			}
		}
		if( k == 0 ){
			alert("请选择收货交易行!");
			return;
		}else if(k != 1){
			alert("一次只能选择一行！");
			return;
		}

		if(confirm("你确定取消收货？"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=receiptAction";
			myIframe.location.href = strUrl + "&method=cancelreceipt&invoiceid="+ invoiceid + "&transid=" + id;
			
		}else{
			UnLockButton();
		}
	}
	

	

</script>
  </head>
  
  <body>
  
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：入库管理 -&gt; 收货 -&gt; 取消收货</div></td>
   </tr>
  </table>
	
 <!-- ======== 收货记录列表开始 ======== -->
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST2">
   <tr>
	 <td valign="top"  height="305">
	   <iframe id="myIframe" src="<%=request.getContextPath()%>/BMSService?actionCode=receiptAction&flag=5&invoiceid=<%=invoiceid%>" scrolling="auto" frameborder="0" width="100%" height="100%">
	   </iframe>
	 </td>
   </tr>
 </table>
 <!-- ======== 收货记录列表结束 ======== -->
 <table width="98%" height="5"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>
 
<table width="98%" height="10"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>	
	
	<FORM id="form1" name="form1">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
        <tr>
          <td height="27" colspan="6" align="center" ><input name="button_add" type="button" class="button_add" id="button_add" value="&nbsp;&nbsp;取消收货" onClick="saveData('<%=invoiceid%>');"  />

            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="关闭" onClick="window.close();" />
          </td>
        </tr>
      </table>
      
 </FORM>

  </body>
</html>
