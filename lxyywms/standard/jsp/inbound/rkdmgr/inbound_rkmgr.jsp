<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	String strTime =  StrTools.getCurrDateTime(8); 

	HashMap hsPopedom = null;
	boolean inboundInMgrQuery = false;    //查询
	boolean inboundInMgrComfirm = false;     //报表
	boolean inboundInMgrCancel = false;     
	boolean inboundInMgrPrint = false;     
	boolean inboundInMgrUploading = false;     
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("inboundInMgrQuery") != null){
			inboundInMgrQuery = true;
		}
		if(hsPopedom.get("inboundInMgrComfirm") != null){
			inboundInMgrComfirm = true;
		}
		if(hsPopedom.get("inboundInMgrCancel") != null){
			inboundInMgrCancel = true;
		}
		if(hsPopedom.get("inboundInMgrPrint") != null){
			inboundInMgrPrint = true;
		}
		if(hsPopedom.get("inboundInMgrUploading") != null){
			inboundInMgrUploading = true;
		}
	}
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
		var whAreaId = document.getElementById("whAreaId").value;		//库区
		var customer_id = document.getElementById("customer_id").value;	//货主
		var instatus = document.getElementById("instatus").value;		//单据状态
		var intype = document.getElementById("intype").value;			//入库类型
		var indate_from = document.getElementById("indate_from").value;	//日期开始
		var indate_to = document.getElementById("indate_to").value;		//日期结束
		//var shift_id = document.getElementById("shift_id").value;
		var linerow = page.document.getElementById("lineviewrow").value;	//每页显示行数	
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("请先选择【所属仓库】!");
			return;
		}
		
		Loading();
		//"&shift_id=" + shift_id
		condition = "&warehouseid=" +warehouseid + "&whAreaId=" +whAreaId + "&owner_id=" + customer_id + "&instatus=" + instatus
			 + "&intype=" + intype + "&indate_from=" + indate_from + "&indate_to=" + indate_to + "&linerow=" + linerow;
		list.location.href = strUrl + "inBoundAction&method=ricoExecSearchRkd&flag=1" + condition;
	}
	
	//确认 审核 作废
	function updateData(flag){
		
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
		
		var msg = "";
		if(flag == "1"){	//单据审核
		
			if(k != 1){
				alert("只能选择一张单据！");
			}else{
			
				msg = "你确定对所选单据进行审核？";
				if(confirm(msg)){
				
					list.location.href = strUrl + "inBoundAction&method=ricoExecAudit&instockids=" + ids + condition;
					detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/rkdmgr/inbound_rkmgr_detail.jsp";
				}
			}
		}else if(flag == "2"){	//单据确认
		
			if(k != 1){
				alert("只能选择一张单据！");
			}else{
			
				msg = "你确定对所选单据进行确认？";
				if(confirm(msg)){
				
					list.location.href = strUrl + "inBoundAction&method=ricoExecConfirm&instockids=" + ids + condition;
					detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/rkdmgr/inbound_rkmgr_detail.jsp";
				}
			}
		}else if(flag == "3"){	//单据作废
		
			msg = "你确定对所选单据进行作废？单据作废后可对单据相关作业重新生成单据！";
			if(confirm(msg)){
			
				list.location.href = strUrl + "inBoundAction&method=ricoExecCancel&instockids=" + ids + condition;
				detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/rkdmgr/inbound_rkmgr_detail.jsp";
			}
		}else if(flag == "4"){	//单据上传
		
		}else{
		
			alert("没有定义的操作！");
			return;
		}
	}
	
	//作废明细单据
	function updateDetailData(){
		
		var ids = "";
		var check_ids = detail.document.getElementsByName("check_id");
		var k = 0;
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked == true){
				ids += check_ids[i].value + ",";
				k++;
			}
		}
		if(ids == ""){
			alert("请选择一条明细记录！");
			return;
		}else if(k == check_ids.length){
			alert("要作废全部的明细单据，请点击【作废单据】！");
			return;
		}else{
			ids = ids.substring(0, ids.length-1);
		}
		
		var msg = "你确定对所选明细单据进行作废？单据作废后可对单据相关作业重新生成单据！";
		if(confirm(msg)){
		
			detail.location.href = strUrl + "inBoundAction&method=ricoExecCancelDetail&instockdetailids=" + ids;
		}
	}
	
	//打印
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
				
		var actionStr =strUrl + "inBoundAction&method=ricoExecReportRKD&instockid="+id;
	    		
		var WLeft = Math.ceil((window.screen.width-800)/2);
  		var WTop  = Math.ceil((window.screen.height-600)/2);
  			
		window.open(actionStr,'newwindow','width=1000,height=600,left='+WLeft+',top='+WTop+',scrollbars=yes');	
	}
	
	
	function Mupload(){
		
	}
	
	//页面登录
	function OnLoad(){
		
		//仓库
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");	
		DWREngine.setAsync(true);
		
		//库区
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		
		selectType('', 'intype', 'rklx');			//入库类型
		selectType('', 'instatus', 'rkdstatus');	//单据状态
	}
	
	//根据仓库获得库区的列表
	function getWhAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
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
		<div id="dqwz" class="f_l">当前位置：<span>入库管理 &gt;&gt; 入库单管理</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
		  	<%if(inboundInMgrUploading){%> <li class="tubiao8" style="width:50px;"><a href="#" onclick="Mupload();">上传</a></li><%}%>
		  	<%if(inboundInMgrPrint){%> <li class="tubiao8" style="width:80px;"><a href="#" onclick="printData();">单据打印</a></li><%}%>
		  	<!--<li class="tubiao2" style="width:80px;"><a href="#" onclick="updateData(4);">单据上传/a></li>-->
		  	<!--<li class="tubiao3" style="width:80px;"><a href="#" onclick="updateDetailData();">明细作废</a></li>-->
		  	<!-- <li class="tubiao2" style="width:80px;"><a href="#" onclick="updateData(1);">单据审核</a></li> -->
		  	<%if(inboundInMgrCancel){%> <li class="tubiao3" style="width:80px;"><a href="#" onclick="updateData(3);">单据作废</a></li><%}%>
		  	<%if(inboundInMgrComfirm){%> <li class="tubiao2" style="width:80px;"><a href="#" onclick="updateData(2);">单据确认</a></li><%}%>
			<%if(inboundInMgrQuery){%> <li class="tubiao1"><a href="#" onclick="searchData()">查询</a></li><%}%>
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
	      <td class="yx1" width="100" align="right">库区：</td>
     	  <td class="yx"><select id="whAreaId" style="width:110px;"><option value=""></option></select></td>
	      <td class="yx1" align="right" width="100px">货主：</td>
	      <td class="xx1">
	      	<input type="hidden" name="customer_id"><input type="text" name="customer_name" size="15" readonly>
       		<input type="button" class="button_select" value="…" onClick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp','800','520');">
	      </td>
	    </tr>
	    <tr>
	      <td class="y1" width="100" align="right">单据状态：</td>
	      <td class="x"><select id=instatus style="width:110px;"><option value=""></option></select></td>
	      <td class="y1" width="100px" align="right">入库类型：</td>
	      <td class="x"><select id="intype" style="width:110px;"><option value=""></option></select></td>
	      <td class="y1" width="100px" align="right">日期：</td>
	      <td >
	      	<input id="indate_from" type="text" size="13" value="<%=strTime%>" onfocus="calendar();" class="Wdate"> -
	      	<input id="indate_to" type="text" size="13" value="<%=strTime%>" onfocus="calendar();" class="Wdate">
	      </td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0" >
<!-- 		  <tr>
            <td valign="bottom" class="title" height="18px">入库单信息</td>
          </tr> -->
	      <tr>
	        <td height="180px">
		      <iframe id="list" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inbound/rkdmgr/inbound_rkmgr_list.jsp"></iframe>
		    </td>
	      </tr>
	      <tr>
	       <td height="25">
	  	     <iframe  id="page" name="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=6&flag=N" 
	  	     	 width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe> 
	       </td>
	     </tr>
	      <tr>
            <td valign="bottom" class="title" height="19px">入库单明细信息</td>
          </tr>
	      <tr>
	        <td>
		      <iframe id="detail" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inbound/rkdmgr/inbound_rkmgr_detail.jsp"></iframe>
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