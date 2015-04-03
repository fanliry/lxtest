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
		var whAreaId = document.getElementById("whAreaId").value;		//库区
		var alleyId = document.getElementById("alleyId").value;			//巷道
		var indate = document.getElementById("indate").value;			//作业日期
		var jobid = document.getElementById("jobid").value;				//作业号
		var invoicetype = document.getElementById("invoicetype").value;	//作业来源
		var package_id = document.getElementById("package_id").value;	//品名
		var traycode = document.getElementById("traycode").value;		//托盘条码
		var isback = document.getElementById("isback").value;			//是否回流
		//var shift_id = document.getElementById("shift_id").value;		//班次
		var linerow = page.document.getElementById("lineviewrow").value;//每页显示行数	
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("请先选择【所属仓库】!");
			return;
		}
		
		Loading();
		//"&shift_id=" + shift_id
		condition = "&warehouseid=" +　warehouseid + "&whAreaId=" +　whAreaId + "&alleyId=" + alleyId + "&indate=" + indate
			 + "&jobid=" + jobid + "&invoicetype=" + invoicetype + "&package_id=" + package_id + "&traycode=" + traycode
			 + "&isback=" + isback + "&linerow=" + linerow;
		list.location.href = strUrl + "inBoundRKWHAction&flag=1" + condition;
		detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/rkwh/inbound_rkwh_detail.jsp";
	}
	
	//增加作业
	function addData(){
	
		var warehouseid = document.getElementById("warehouseid").value;	//仓库
		var whAreaId = document.getElementById("whAreaId").value;		//库区
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/inbound/rkwh/inbound_rkwh_add.jsp?warehouseid="+warehouseid+"&whAreaId="+whAreaId,
			"","dialogWidth=950px;dialogHeight=600px;");
			
		if(result != null && result.length > 0){
			
			list.formx1.innerHTML = result[1];
			list.formx1.action = strUrl + "inBoundRKWHAction&method=ricoExecAdd" + result[0];
			list.document.formx1.submit();
		}
	}
	
	//修改
	function updateData(){
		
		var ids = "";
		var check_ids = list.document.getElementsByName("check_id");
		var k = 0;
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked == true){
				ids = check_ids[i].value;
				k++;
			}
		}
		if(k != 1){
			alert("请选择一条记录！");
		}else{
			
			var result = showModalDialog(strUrl + "inBoundRKWHAction&flag=3&jobid=" + ids, "", "dialogWidth=400px; dialogHeight=200px; scroll=no");
			if(result != null && result.length > 0){
				list.location.href = strUrl + "inBoundRKWHAction&method=ricoExecUpdate" + result;
				detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/rkwh/inbound_rkwh_detail.jsp";
			}
		}
	}
	
	//修改明细
	function updateDetailData(){
		
		var ids = "";
		var check_ids = detail.document.getElementsByName("check_id");
		var k = 0;
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked == true){
				ids = check_ids[i].value;
				k++;
			}
		}
		if(k != 1){
			alert("请选择一条记录！");
		}else{
			
			var result = showModalDialog(strUrl + "inBoundRKWHAction&flag=4&jobdetailid=" + ids, "", "dialogWidth=800px; dialogHeight=500px; scroll=no");
			if(result != null && result.length > 0){
			
				//detail.location.href = strUrl + "inBoundRKWHAction&method=ricoExecUpdateDetail" + result;
				detail.formx1.innerHTML = result;
				detail.formx1.action = strUrl + "inBoundRKWHAction&method=ricoExecUpdateDetail&jobdetailid=" + ids;
				detail.document.formx1.submit();
			}
		}
	}
	
	//作废作业
	function deleteData(){
		
		var ids = "";
		var check_ids = list.document.getElementsByName("check_id");
		var k = 0;
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked == true){
				ids = check_ids[i].value;
				k++;
			}
		}
		if(k != 1){
			alert("请选择一条记录！");
		}else{
			
			var msg = "你确定对所选的作业进行作废？";
			if(confirm(msg)){
			
				list.location.href = strUrl + "inBoundRKWHAction&method=ricoExecCancel&jobid=" + ids;
				detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/rkwh/inbound_rkwh_detail.jsp";
			}
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
		
		selectType('1', 'invoicetype', 'zyly');		//作业来源
	}
	
	//根据仓库获得库区的列表
	function getWhAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		selectAlleyList("", "alleyId", "");
	}
	
	//根据库区获得巷道的列表
	function getAlleyList(){
	
		var whAreaId = document.getElementById("whAreaId").value;
		selectAlleyList("", "alleyId", whAreaId);
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
		<div id="dqwz" class="f_l">当前位置：<span>入库管理 &gt;&gt; 入库维护</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
		  	<li class="tubiao3"><a href="#" onclick="deleteData()">作废</a></li>
		  	<li class="tubiao2" style="width:80px;"><a href="#" onclick="updateDetailData();">修改明细</a></li>
		  	<li class="tubiao2"><a href="#" onclick="updateData();">修改</a></li>
		  	<li class="tubiao4"><a href="#" onclick="addData();">添加</a></li>
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
	      <td class="yx1" width="100" align="right">仓库：</td>
	      <td class="yx"><select name="warehouseid" onChange="getWhAreaList()" style="width:117px;"><option value=""></option></select></td>
	      <td class="yx1" width="100" align="right">库区：</td>
     	  <td class="yx"><select id="whAreaId" style="width:117px;" onChange="getAlleyList()"><option value=""></option></select></td>
	      <td class="yx1" width="100px" align="right">巷道：</td>
	      <td class="xx1"><select id="alleyId" style="width:117px;"><option value="">--请选择--</option></select></td>
	    </tr>
        <tr>
	      <td class="yx1" align="right">入库日期：</td>
	      <td class="yx"><input id="indate" type="text" size="15" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:117px;"></td>
	      <td class="yx1" align="right">作业号：</td>
	      <td class="yx"><input type="text" id="jobid" size="15" ></td>
	      <td class="yx1" align="right">作业来源：</td>
	      <td class="xx1"><select id=invoicetype><option value=""></option></select></td>
	    </tr>
	    <tr>
	      <td class="y1" align="right">品名：</td>
	      <td class="x">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name" size="15" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  
            	type="button" value="…" class="button_select">
	      </td>
	      <td class="y1" align="right">托盘条码：</td>
	      <td class="x"><input name="traycode" type="text" size="15"></td>
	      <td class="y1" align="right">是否回流：</td>
	      <td>
         	<select id="isback" style="width:117px;"><option value="">--请选择--</option><option value="Y">回流</option><option value="N">非回流</option></select>
       	  </td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0" >
		  <tr>
            <td valign="bottom" class="title" height="18px">作业信息</td>
          </tr>
	      <tr>
	        <td height="180px">
		      <iframe id="list" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inbound/rkwh/inbound_rkwh_list.jsp"></iframe>
		    </td>
	      </tr>
	      <tr>
	       <td height="25">
	  	     <iframe  id="page" name="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=6&flag=N" 
	  	     	 width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe> 
	       </td>
	     </tr>
	      <tr>
            <td valign="bottom" class="title" height="19px">作业详细信息</td>
          </tr>
	      <tr>
	        <td>
		      <iframe id="detail" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inbound/rkwh/inbound_rkwh_detail.jsp"></iframe>
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