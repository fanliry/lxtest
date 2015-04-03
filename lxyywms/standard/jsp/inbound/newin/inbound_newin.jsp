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
	var searchCondition = null;	//建单时候查询条件带过去，（避免画面上的查询条件被修改后，不经过查询就去生成单据）
	function LockButton(){
		document.getElementById("search").disabled = true;
		document.getElementById("detail").disabled = true;
		document.getElementById("add").disabled = true;
	}
	function UnLockButton(){
		document.getElementById("search").disabled = false;
		document.getElementById("detail").disabled = false;
		document.getElementById("add").disabled = false;
	}
	
	//查询
	function searchData()
	{
		var warehouseid = document.getElementById("warehouseid").value;	//仓库
		var whAreaId = document.getElementById("whAreaId").value;		//库区
		var indate = document.getElementById("indate").value;
		var isinvoice = document.getElementById("isinvoice").value;
		var customer_id = document.getElementById("customer_id").value;
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("请先选择【所属仓库】!");
			return;
		}
		
		if(indate == null || indate.length < 1){
			alert("【日期】不能为空！");
			condition = null;
			return;
		}
		
		Loading();
		condition = "&warehouseid=" +　warehouseid + "&whAreaId=" +　whAreaId + "&indate=" + indate + "&isinvoice=" + isinvoice + "&owner_id=" + customer_id;
		searchCondition = warehouseid + "|" + whAreaId + "|" + indate + "|" + isinvoice + "|" + customer_id + "|end" ;
		list.location.href = strUrl + "inBoundAction&flag=1" + condition;
	}
	
	//明细
	function searchDetail(){
		var k = 0;
		var key = "";
		var check_ids = list.document.getElementsByName("check_id");
		var keys = list.document.getElementsByName("detail_key");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				key = keys[i].value;
				k++;
			}
		}
		if(k != 1){
			alert("明细操作必须且只能选择一条记录！");
			return;
		}
		key = encodeURIComponent(encodeURIComponent(key));	//特殊字符,中文的时候
		showModalDialog(strUrl + "inBoundAction&flag=2&key=" + key + condition, "", "dialogWidth=1200px;dialogHeight=500px;scrollbars=auto;");
	}
	
	//生成单据 
	function addInvoice()
	{	
		LockButton();
		var key = "";
		var check_ids = list.document.getElementsByName("check_id");
		var keys = list.document.getElementsByName("detail_key");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				key += keys[i].value + ",";
			}
		}
		if(key == ""){
			alert("请至少选择一条记录！");
			UnLockButton();
			return;
		}
		var warehouseid = document.getElementById("warehouseid").value;	//仓库
		var whAreaId = document.getElementById("whAreaId").value;		//库区
		var indate = document.getElementById("indate").value;
		var isinvoice = document.getElementById("isinvoice").value;
		var customer_id = document.getElementById("customer_id").value;
			
		key =  key.substring(0, key.length-1);
			
		if(confirm("你确定对所选项(日期:"+indate+")生成单据？")){
				
			//有中文
			var msg = "<input type=hidden name='actionCode' value='inBoundAction'>"
					+ "<input type=hidden name='method' value='ricoExecCreate'>"
					+ "<input type=hidden name='searchCondition' value='"+searchCondition+"'>"
					+ "<input type=hidden name='key' value='"+key+"'>";
			list.formx1.innerHTML = msg;
			list.document.formx1.submit(); 	
				
		}else{
			UnLockButton();
		}	
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
		<div id="dqwz" class="f_l">当前位置：<span>入库管理 &gt;&gt; 新建入库单</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<li class="tubiao1"><a href="#" onclick="searchData()" id="search">查询</a></li>
			<li class="tubiao2"><a href="#" onclick="searchDetail()" id="detail">明细</a></li>
			<li class="tubiao4" style="width:83px"><a href="#" onclick="addInvoice()" id="add">生成单据</a></li>
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
	      <td class="x"><select name="warehouseid" onChange="getWhAreaList()" style="width:117px;"><option value=""></option></select></td>
	      <td class="y1" width="100" align="right">库区：</td>
     	  <td class="x"><select id="whAreaId" style="width:117px;"><option value=""></option></select></td>
	      <td class="y1" width="100px" align="right">日期：</td>
	      <td class="x">
	      	<input id="indate" type="text" size="13" value="<%=strTime%>" onfocus="calendar();" class="Wdate"></td>
	      <td class="y1" align="right" width="100px">货主：</td>
	      <td class="x">
	      	<input type="hidden" name="customer_id"><input type="text" name="customer_name" size="15" readonly>
       		<input type="button" class="button_select" value="…" onClick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp','800','520');">
	      </td>
	      <td class="y1" width="100px" align="right">是否开单：</td>
	      <td><select id="isinvoice"><option value="">--请选择--</option><option value="Y">是</option><option value="N">否</option></select></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/inbound/newin/inbound_newin_list.jsp" 
		  		width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
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