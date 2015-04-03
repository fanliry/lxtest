
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>
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
		document.getElementById("button_cancel").disabled = true;
	}
	/*释放按钮*/
	function UnLockButton(){
		document.getElementById("button_cancel").disabled = false;
		document.getElementById("button_add").disabled = false;
	}
 	//上架
	function saveData(invoiceid)
	{
		LockButton();
		//作业ID
		var jobid = document.getElementById("jobid").value;
		//作业状态
		var status = document.getElementById("status").value;
		//库区
		var whareaId = document.getElementById("whareaId").value;
		//货位
		var spacename = document.getElementById("spacename").value;	
		//货位ID
		var newspaceid = document.getElementById("spaceid").value;	
		
		if(jobid == null || jobid.length < 1){
			alert("【作业编号】不能为空！");
			UnLockButton();
			return;
		}
		
		if(status == null || status.length < 1){
			alert("【作业状态】不能为空！");
			UnLockButton();
			return;
		}
		if(status != "1"){
			alert("【作业状态】不为未执行状态！");
			UnLockButton();
			return;
		}
		
		//************************************************
		if(confirm("上架货位为"+spacename+",你确定是否上架？"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=putawayAction";

			myIframe.formx1.action = strUrl + "&method=putaway&invoiceid="+ invoiceid + "&jobid=" + jobid + "&newspaceid=" + newspaceid;
			myIframe.document.formx1.submit();
			
			document.form1.reset();//重置		
			detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/putaway/inbound_putaway_execute_jobdetail.jsp";
			
		}else{
			UnLockButton();
		}
	}
	
	//取消上架
	function cancelPutaway(invoiceid)
	{
		//作业ID
		var jobid = document.getElementById("jobid").value;
		
		//************************************************
		if(confirm("作业："+jobid+",你确定取消上架？"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=putawayAction";

			myIframe.formx1.action = strUrl + "&method=cancelPutaway&invoiceid="+ invoiceid + "&jobid=" + jobid;
			myIframe.document.formx1.submit();
			
			document.form1.reset();//重置		
			detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/putaway/inbound_putaway_execute_jobdetail.jsp";
			
		}else{
			UnLockButton();
		}
	}

	

</script>
  </head>
  
  <body >
  
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：入库管理 -&gt; 上架 -&gt; 执行上架</div></td>
   </tr>
  </table>
	
 <!-- ======== 作业列表开始 ======== -->
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST2">
   <tr>
	 <td valign="top"  height="170">
	   <iframe id="myIframe" src="<%=request.getContextPath()%>/BMSService?actionCode=putawayAction&flag=3&invoiceid=<%=invoiceid%>" frameborder="0" width="100%" height="100%">
	   </iframe>
	 </td>
   </tr>
 </table>
 <!-- ======== 作业列表结束 ======== -->
 <table width="98%" height="5"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>
 
 <!-- ======== 作业详细列表开始 ======== -->
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="TABLE_LIST2">    
	<tr>
         <td valign="top"  height="185" > <!-- 如要有横着的滚动条，高度设为185即可 没有是165 -->
					<iframe id="detail" src="<%=request.getContextPath()%>/standard/jsp/inbound/putaway/inbound_putaway_execute_jobdetail.jsp"   width="100%" height="100%"   frameborder="0" ></iframe>
         </td>
    </tr>
 </table>
 <!-- ======== 作业详细列表结束 ======== -->
 
<table width="98%" height="10"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>	
	
	<FORM id="form1" name="form1">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add" >
        
        <tr>
          <td width="13%" align="right" class="yx1">作业编号：</td>
          <td width="18%" class="yx" >
          	 <input type="text" name="jobid" class="input_readonly" readonly   style="height:18px;width:100px;"/> 
          	 <input type="hidden" name="status" >
          </td>
          <td width="15%" align="right" class="yx1">上架库区：</td>
          <td width="20%" class="yx">
	          <input type="text" name="whareaname" class="input_readonly"  readonly  style="height:18px;width:100px;"/>
	          <input type="hidden" name="whareaId" >
          </td>
          <td width="15%" align="right" class="yx1">上架货位：</td>
          <td width="19%" class="xx1">
	          <input type="text" name="spacename" class="input_readonly"  readonly  style="height:18px;width:100px;"/>
	          <input type="hidden" name="spaceid" >
          </td>
        </tr>

		<tr>
        	<TD colspan="6" height="10">
        		<input type="hidden" name="invoiceid" value="<%=invoiceid%>" class="input_readonly" readonly  style="height:18px;width:100px;"/>
        	</TD>
        </tr>
        
        
        
        <tr>
          <td height="27" colspan="6" align="center" ><input name="button_add" type="button" class="button_add" id="button_add" value="&nbsp;&nbsp;执行上架" onClick="saveData('<%=invoiceid%>');"  />
            <input name="button" type="reset" class="button_reset" id="button" value="&nbsp;&nbsp;重置" />&nbsp;&nbsp;&nbsp;&nbsp;
            <input name="button_cancel" type="button" class="button_add" id="button_cancel" value="&nbsp;&nbsp;取消上架" onClick="cancelPutaway('<%=invoiceid%>');"  />
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="关闭" onClick="window.close();" />
          </td>
        </tr>
      </table>
      
 </FORM>

  </body>
</html>
