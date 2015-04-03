<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean outboundDjmgrQuery = false; 
	boolean outboundDjmgrComfirm = false; 
	boolean outboundDjmgrCancel = false; 
	boolean outboundDjmgrPrint = false; 
	boolean outboundDjmgrPrintD = false; 
	boolean outboundDjmgrUploading = false; 
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("outboundDjmgrQuery") != null){
			outboundDjmgrQuery = true;
		}
		if(hsPopedom.get("outboundDjmgrComfirm") != null){
			outboundDjmgrComfirm = true;
		}
		if(hsPopedom.get("outboundDjmgrCancel") != null){
			outboundDjmgrCancel = true;
		}
		if(hsPopedom.get("outboundDjmgrPrint") != null){
			outboundDjmgrPrint = true;
		}
		if(hsPopedom.get("outboundDjmgrPrintD") != null){
			outboundDjmgrPrintD = true;
		}
		if(hsPopedom.get("outboundDjmgrUploading") != null){
			outboundDjmgrUploading = true;
		}
    }
%>
<%
	String strTime =  StrTools.getCurrDateTime(8); 
%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	
	//查询
	function searchData(){
	
		var warehouseid = document.getElementById("warehouseid").value;	//仓库
		var outstatus = document.getElementById("outstatus").value;		//单据状态
		var outtype = document.getElementById("outtype").value;			//出库类型
		var customerid = document.getElementById("customer_id").value;	//客户
		var outstockid = document.getElementById("outstockid").value;	//出库单号
		var start_time = document.getElementById("outdate_from").value;	//日期开始
		var end_time = document.getElementById("outdate_to").value;		//日期结束
		//var shift_id = document.getElementById("shift_id").value;
		var linerow = page.document.getElementById("lineviewrow").value;	//每页显示行数	
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("请先选择【所属仓库】!");
			return;
		}
		
		Loading();
		//"&shift_id=" + shift_id
		condition = "&warehouseid=" +warehouseid + "&customerid=" + customerid + "&outstatus=" + outstatus + "&outno=" +outstockid
			 + "&outtype=" + outtype + "&start_time=" + start_time + "&end_time=" + end_time + "&linerow=" + linerow;
		list.location.href = strUrl + "outBoundAction&flag=6" + condition;
	}
	
	//作废
	function updateData(){
		
		var ids = "";
		var check_ids = list.document.getElementsByName("check_id");
		var k = 0;
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked == true){
				ids += check_ids[i].value + ",";
				k++;
			}
		}
		if(ids == ""){
			alert("请选择一张单据！");
			return;
		}else{
			ids = ids.substring(0, ids.length-1);
		}
		
		var msg = "你确定对所选单据进行作废？";
		if(confirm(msg)){
				
			list.location.href = strUrl + "outBoundAction&method=ricoExecCancel&ids=" + ids + condition;
			detail.location.href = "<%=request.getContextPath()%>/standard/jsp/outbound/ckdmgr/outbound_ckmgr_detail.jsp";
		}
	}
	
	//出库产品打印
	function printData(){
		
		var k = 0;
		var id = "";
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id = check_ids[i].value;
				k++;
			}
		}
		if( k == 0 ){
			alert("请选择一张单据！");
			return;
		}else if(k != 1){
			alert("只能选择一张单据！");
			return;
		}
				
		var actionStr =strUrl + "outBoundAction&method=ricoExecReportCKD&outstockid="+id;
	    		
  		
  		var WLeft = Math.ceil((window.screen.width-800)/2);
  		var WTop  = Math.ceil((window.screen.height-600)/2);
  	    window.open(actionStr,"new","dialogLeft='"+WLeft+"';dialogTop='"+WTop+"';dialogWidth=800px;dialogHeight=600px;");
	}
	//出库单据明细打印
	function printMXData(){
		
		var k = 0;
		var id = "";
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id = check_ids[i].value;
				k++;
			}
		}
		if( k == 0 ){
			alert("请选择一张单据！");
			return;
		}else if(k != 1){
			alert("只能选择一张单据！");
			return;
		}
				
		var actionStr =strUrl + "outBoundAction&method=ricoExecReportCKDMX&outstockid="+id;
	    		
  		
  		var WLeft = Math.ceil((window.screen.width-800)/2);
  		var WTop  = Math.ceil((window.screen.height-600)/2);
  	    window.open(actionStr,"new","dialogLeft='"+WLeft+"';dialogTop='"+WTop+"';dialogWidth=800px;dialogHeight=600px;");
	}
	
	
	
	//删除单据详细
	function deleteData()
	{	
		var ids = "";
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++)
		{
			if(check_ids[i].checked)
			{
				ids += check_ids[i].value + ",";
			}
		}
		if(ids == ""){
			alert("请选择要作废的记录！");
			return;
		}
		
		//************************************************
		if(confirm("你确定删除所选记录？"))
		{		
			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&method=deleteRicoExec&flag=1";
			list.location.href = strUrl + "&invoiceids=" + ids;
			tableheight2.location.href = "<%=request.getContextPath()%>/standard/jsp/outbound/newckd/outbound_newckd_dlist.jsp";	
		}
	}
	//更新出库单
 	function updateinvoice()
 	{
 		var k = 0;
		var id = "";
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id = check_ids[i].value;
				k++;
			}
		}
		if( k == 0 ){
			alert("请选择一张单据！");
			return;
		}else if(k != 1){
			alert("只能选择一张单据！");
			return;
		}
 	
 		var result = showModalDialog("<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&flag=3&invoiceid="+id,"","dialogWidth=800px;dialogHeight=250px;");
		if(result != null && result.length > 0)
		{
			list.location.href = result;
		}
 	}
	//页面登录
	function OnLoad(){
		
		//仓库
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");	
		DWREngine.setAsync(true);
		
		selectType('', 'outtype', 'ckdj');
		selectType('', 'outstatus', 'ckdzt');
	}
	
-->
</script>
</head>

<body onLoad="OnLoad()">

<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== 当前位置 ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">当前位置：<span>出库管理 &gt;&gt; 出库单管理</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
		  <%if(outboundDjmgrUploading){%><li class="tubiao8" style="width:50px;"><a href="#" onclick="Mupload();">上传</a></li><%}%>
		  	<%if(outboundDjmgrPrintD){%><li class="tubiao2" style="width:110px;"><a href="#" onclick="printMXData();">单据明细打印</a></li><%}%>
		  	<%if(outboundDjmgrPrint){%><li class="tubiao2" style="width:110px;"><a href="#" onclick="printData();">出库产品打印</a></li><%}%>
		  	<!--<li class="tubiao2" style="width:80px;"><a href="#" onclick="updateData(4);">单据上传/a></li>-->
		  	<%if(outboundDjmgrCancel){%><li class="tubiao3" style="width:80px;"><a href="#" onclick="updateData();">单据作废</a></li><%}%>
		  	<!--<li class="tubiao3"><a href="#" onclick="deleteData();">删除</a></li>-->
	        <%if(outboundDjmgrComfirm){%><li class="tubiao2"><a href="#" onclick="updateinvoice();">修改</a></li><%}%>
			<%if(outboundDjmgrQuery){%><li class="tubiao1"><a href="#" onclick="searchData()">查询</a></li><%}%>
			
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== 查询条件 ======== -->
      <table width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
	    <tr>
	      <td class="yx1" width="100" align="right">仓库：</td>
	      <td class="yx"><select name="warehouseid" onChange="getWhAreaList()" style="width:110px;"><option value=""></option></select></td>
	      <td class="yx1" width="100" align="right">单据状态：</td>
	      <td class="yx"><select id="outstatus" style="width:110px;"><option value=""></option></select></td>
	      <td class="yx1" width="100px" align="right">出库类型：</td>
	      <td class="xx1"><select id="outtype" style="width:110px;"><option value=""></option></select></td>
	    </tr>
	    <tr>
	      <td class="y1" align="right">客户：</td>
	      <td class="x">
	      	<input type="hidden" name="customer_id"><input type="text" name="customer_name" size="15" readonly>
       		<input type="button" class="button_select" value="…" onClick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp','800','520');">
	      </td>
	      <td class="y1" align="right">出库单号：</td>
          <td class="x"><input type="text" id="outstockid"></td>
	      <td class="y1" align="right">日期：</td>
	      <td >
	      	<input id="outdate_from" type="text" size="13" value="<%=strTime%>" onfocus="calendar();" class="Wdate"> -
	      	<input id="outdate_to" type="text" size="13" value="<%=strTime%>" onfocus="calendar();" class="Wdate">
	      </td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0" >
<!-- 		  <tr> -->
<!--             <td valign="bottom" class="title" height="18px">出库单基本信息</td> -->
<!--           </tr> -->
	      <tr>
	        <td height="180px">
		      <iframe id="list" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/outbound/ckdmgr/outbound_ckmgr_list.jsp"></iframe>
		    </td>
	      </tr>
	      <tr>
	       <td height="25">
	  	     <iframe  id="page" name="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=6&flag=N" 
	  	     	 width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe> 
	       </td>
	     </tr>
	      <tr>
            <td valign="bottom" class="title" height="19px">出库单【<span id="dckdid" style="color: red;font-weight:bold;"></span>】明细信息</td>
          </tr>
	      <tr>
	        <td>
		      <iframe id="detail" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/outbound/ckdmgr/outbound_ckmgr_detail.jsp"></iframe>
		    </td>
	      </tr>
	    </table>
		
	  </td>
    </tr>
  </table>  	
</div>

<!-- 页面加载层（start） -->
 <div id="loader_container" style="position: absolute; display: none; z-index: 2; filter:alpha(opacity=75);">
   <div id="loader"><div align="center">页面正在加载 ...</div><div id="loader_bg"><div id="progress"></div></div></div>
 </div>
 <!-- 页面加载层（end） -->  
 
</body>
</html>