<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
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
		var outtype = "7";			//出库类型 :抽检出库
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
		condition = "&warehouseid=" +　warehouseid + "&customerid=" + customerid + "&outno=" +　outstockid
			 + "&outtype=" + outtype + "&start_time=" + start_time + "&end_time=" + end_time + "&linerow=" + linerow;	
        
		list.location.href = strUrl + "outBoundAction&flag=13" + condition;
	}
	//单据确认
	function saveData(){
		
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
		
		var msg = "你确定单据抽检完成？";
		if(confirm(msg)){
		
			list.location.href = strUrl + "qualityAction&method=ricoExecUpdate&ids=" + ids + condition;
			detail.location.href = "<%=request.getContextPath()%>/standard/jsp/quality/qualitymgr/quality_qualitymgr_detail.jsp";
		}
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
		
			list.location.href = strUrl + "qualityAction&method=ricoExecDelete&ids=" + ids + condition;
			detail.location.href = "<%=request.getContextPath()%>/standard/jsp/quality/qualitymgr/quality_qualitymgr_detail.jsp";
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
				
		var actionStr =strUrl + "qualityAction&method=ricoExecReportCKD&outstockid="+id;
	    		
		var WLeft = Math.ceil((window.screen.width-800)/2);
  		var WTop  = Math.ceil((window.screen.height-600)/2);
  			
		window.open(actionStr,'newwindow','width=1200,height=600,left='+WLeft+',top='+WTop+',scrollbars=yes');	
	}
	
	//页面登录
	function OnLoad(){
		
		//仓库
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");	
		DWREngine.setAsync(true);
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
		<div id="dqwz" class="f_l">当前位置：<span>质检管理 &gt;&gt; 抽检单管理</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
		  	<li class="tubiao2" style="width:80px;"><a href="#" onclick="printData();">单据打印</a></li>
		  	<!--<li class="tubiao2" style="width:80px;"><a href="#" onclick="updateData(4);">单据上传/a></li>-->
		  	<li class="tubiao3" style="width:80px;"><a href="#" onclick="updateData();">单据作废</a></li>
		  	<li class="tubiao2" style="width:80px;"><a href="#" onclick="saveData();">单据确认</a></li>
			<li class="tubiao1"><a href="#" onclick="searchData()">查询</a></li>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== 查询条件 ======== -->
      <table width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
	    <tr>
	      <td class="y1" width="100" align="right">仓库：</td>
	      <td class="x"><select name="warehouseid" onChange="getWhAreaList()" style="width:110px;"><option value=""></option></select></td>
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
		  <tr>
            <td valign="bottom" class="title" height="18px">抽检单信息</td>
          </tr>
	      <tr>
	        <td height="180px">
		      <iframe id="list" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/quality/qualitymgr/quality_qualitymgr_list.jsp"></iframe>
		    </td>
	      </tr>
	      <tr>
	       <td height="25">
	  	     <iframe  id="page" name="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=6&flag=N" 
	  	     	 width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe> 
	       </td>
	     </tr>
	      <tr>
            <td valign="bottom" class="title" height="19px">抽检单明细信息</td>
          </tr>
	      <tr>
	        <td>
		      <iframe id="detail" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/quality/qualitymgr/quality_qualitymgr_detail.jsp"></iframe>
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