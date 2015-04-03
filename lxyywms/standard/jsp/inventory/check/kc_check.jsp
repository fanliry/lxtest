<%@ page contentType="text/html; charset=GBK"%>
<%@page import="com.ricosoft.common.tools.StrTools"%>
<%@ page import="java.util.HashMap" %>
<%
String path = request.getContextPath();
String strTime = StrTools.getCurrDateTime(8);

    HashMap hsPopedom = null;
	boolean kcKccheckQuery = false; 
	boolean kcKccheckAdd = false; 
	boolean kcKccheckDel = false; 
	boolean kcKccheckUpdate = false; 
	boolean kcKccheckComfirm = false; 
	boolean kcKccheckPrint = false; 
	boolean kcKccheckJG = false; 
	boolean kcKccheckQR = false; 
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("kcKccheckQuery") != null){
			kcKccheckQuery = true;
		}
		if(hsPopedom.get("kcKccheckAdd") != null){
			kcKccheckAdd = true;
		}
		if(hsPopedom.get("kcKccheckDel") != null){
			kcKccheckDel = true;
		}
		if(hsPopedom.get("kcKccheckUpdate") != null){
			kcKccheckUpdate = true;
		}
		if(hsPopedom.get("kcKccheckComfirm") != null){
			kcKccheckComfirm = true;
		}
		if(hsPopedom.get("kcKccheckPrint") != null){
			kcKccheckPrint = true;
		}
		if(hsPopedom.get("kcKccheckJG") != null){
			kcKccheckJG = true;
		}
		if(hsPopedom.get("kcKccheckQR") != null){
			kcKccheckQR = true;
		}
    }
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>浙江刚玉物流仓库管理系统</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript">
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	
	//查询
	function queryData(){
	
		var warehouseid = document.getElementById("warehouseid").value;	//仓库
		var whAreaId = document.getElementById("whAreaId").value;		//库区
		var type = document.getElementById("type").value;				//类型
		var status = document.getElementById("status").value;			//状态
		var productid = document.getElementById("package_id").value;		//产品id
		var lotnumber = document.getElementById("lotnumber").value;		//批号
		var linerow = page.document.getElementById("lineviewrow").value;//每页显示行数	
		
		var fmDate = document.getElementById("fmDate").value;//开始时间
		var toDate = document.getElementById("toDate").value;//结束时间
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("请先选择【仓库】!");
			return;
		}
		
		condition = "&warehouseid=" + warehouseid + "&whAreaId=" + whAreaId + "&type=" + type + "&status=" + status + "&productid=" + productid+ "&lotnumber=" + lotnumber + "&linerow=" + linerow+ "&fmDate=" + fmDate+ "&toDate=" + toDate;
		list.location.href = ac + "inventoryCheckAction&flag=1" + condition;
		
		//不知道为什么会有下面这句，有什么用么
// 		list.location.href = "<%=request.getContextPath()%>/standard/jsp/inventory/check/kc_check_list.jsp";
	}
	
	//添加
	function addData(){
		var warehouseid = document.getElementById("warehouseid").value;	//仓库
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/inventory/check/kc_check_request_add.jsp?warehouseid="+warehouseid,'',
			'dialogWidth=600px;dialogHeight=250px');
	   	if(result != null){
	   		//list.formx1.innerHTML = result[1];
			list.formx1.action = ac + "inventoryCheckAction&method=ricoExecAdd" + result;
			list.document.formx1.submit();
			list.location.href = "<%=request.getContextPath()%>/standard/jsp/inventory/check/kc_check_detail.jsp";
	   	}
	}
	
	//删除
	function delData(){
		var checkbox_ids = list.document.getElementsByName("check_id");
		var ids = "";
		for(var i=0; i<checkbox_ids.length; i++){
	  		if(checkbox_ids[i].checked){
	  			ids += checkbox_ids[i].value + ",";
	  		}
  		}
  		
  		if(ids == ""){
			alert("请至少选择一条盘点请求信息!");
		  	return false;
		}
	
		list.location.href = ac + "inventoryCheckAction&method=ricoExecDelete&ids=" + ids;
		detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inventory/check/kc_check_detail.jsp";
	}
	//完成盘点单
	function finish(){
		var checkbox_ids = list.document.getElementsByName("check_id");
		var ids = "";
		for(var i=0; i<checkbox_ids.length; i++){
	  		if(checkbox_ids[i].checked){
	  			ids += checkbox_ids[i].value + ",";
	  		}
  		}
  		
  		if(ids == ""){
			alert("请至少选择一条盘点请求信息!");
		  	return false;
		}
	
		list.location.href = ac + "inventoryCheckAction&method=ricoExecFinish&ids=" + ids;
		detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inventory/check/kc_check_detail.jsp";
	}
	
	//修改
	function updateData(){
	
		var counter = 0;
		var checkbox_ids = list.document.getElementsByName("check_id");
		var id = "";
		for(var i=0; i<checkbox_ids.length; i++){
	  		if(checkbox_ids[i].checked){
	  			counter = counter + 1;
	  			id = checkbox_ids[i].value;
	  		}
  		}
		if(counter!=1){
			alert("请选择一条盘点请求信息!");
		  	return false;
		}
		
		var result = showModalDialog(ac + "inventoryCheckAction&flag=3&requestid=" + id,'','dialogWidth=600px;dialogHeight=250px');
		   	if(result != null){
			list.formx1.action = ac + "inventoryCheckAction&method=ricoExecEdit" + result;
// 			list.document.formx1.submit();
// 	   		detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inventory/check/kc_check_detail.jsp";
	   	}
	}
	
	//盘点任务
	function addTask(){
	
		var counter = 0;
		var checkbox_ids = list.document.getElementsByName("check_id");
		var id = "";
		for(var i=0; i<checkbox_ids.length; i++){
	  		if(checkbox_ids[i].checked){
	  			counter = counter + 1;
	  			id = checkbox_ids[i].value;
	  		}
  		}
		if(counter!=1){
			alert("请选择一条盘点请求信息!");
		  	return false;
		}
		
		showModalDialog(ac + "inventoryCheckAction&flag=4&requestid="+id,'','dialogWidth=1000px;dialogHeight=600px');
	
	}
	
	//结果
	function addResult(){
	
		var counter = 0;
		var checkbox_ids = list.document.getElementsByName("check_id");
		var id = "";
		for(var i=0; i<checkbox_ids.length; i++){
	  		if(checkbox_ids[i].checked){
	  			counter = counter + 1;
	  			id = checkbox_ids[i].value;
	  		}
  		}
		if(counter!=1){
			alert("请选择一条盘点请求信息!");
		  	return false;
		}
		
		showModalDialog("<%=request.getContextPath()%>/standard/jsp/inventory/check/kc_check_result.jsp?requestid="+id,
			'','dialogWidth=1000px;dialogHeight=550px');
	
	} 
	
	//关闭
	function closeData(){
	
		var counter = 0;
		var checkbox_ids = list.document.getElementsByName("check_id");
		var id = "";
		for(var i=0; i<checkbox_ids.length; i++){
	  		if(checkbox_ids[i].checked){
	  			counter = counter + 1;
	  			id = checkbox_ids[i].value;
	  		}
  		}
		if(counter!=1){
			alert("请选择一条盘点请求信息!");
		  	return false;
		}
		
		list.location.href = ac + "inventoryCheckAction&method=ricoExecClose&requestid=" + id;
		detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inventory/check/kc_check_detail.jsp";
	} 
	
	//打印盘点任务
	function printTask(){
		
		var counter = 0;
		var checkbox_ids = list.document.getElementsByName("check_id");
		var id = "";
		for(var i=0; i<checkbox_ids.length; i++){
	  		if(checkbox_ids[i].checked){
	  			counter = counter + 1;
	  			id = checkbox_ids[i].value;
	  		}
  		}
		if(counter!=1){
			alert("请选择一条盘点请求信息!");
		  	return false;
		}
		
		window.open(ac + "inventoryCheckAction&method=ricoExecPrintTasks&requestid="+id,'','dialogWidth=1300px;dialogHeight=600px');
	} 
	
	//页面加载
	function OnLoad(){
	
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");				//仓库
		DWREngine.setAsync(true);
		
		var warehouseid = document.getElementById("warehouseid").value;	
		selectAreaList("", "whAreaId", warehouseid, "1");	//库区
		
		selectType('', 'type', 'pdlx');			//类型
		selectType('', 'status', 'pdzt');		//状态
	
	} 
	
	//根据仓库获得库区的列表
	function getWhAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
	}

</script>

</head>

<body  onload="javascript:OnLoad();">
<div class="con_bk" >
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== 当前位置 ======== -->
     
	     <div class="wz" >
	      <div id="dqwz" class="f_l">当前位置：<span>库存管理 &gt;&gt; 库存盘点</span></div>
	      <div  class="f_r" id="caozuo">
	        <ul>
	         <%if(kcKccheckQR){%><li class="tubiao2" style="width:50px;"><a href="#" onclick="finish();">确认</a></li><%}%>
	         <%if(kcKccheckJG){%><li class="tubiao2" style="width:50px;"><a href="#" onclick="addResult();">结果</a></li><%}%>
	         <%if(kcKccheckPrint){%><li class="tubiao2" style="width:100px;"><a href="#" onclick="printTask();">打印盘点任务</a></li><%}%>
	         <%if(kcKccheckComfirm){%><li class="tubiao2" style="width:80px;"><a href="#" onclick="addTask();">盘点任务</a></li>	<%}%>         
	         <%if(kcKccheckUpdate){%><li class="tubiao2" style="width:50px;"><a href="#" onclick="updateData();">修改</a></li><%}%>
	         <%if(kcKccheckDel){%><li class="tubiao3" style="width:50px;"><a href="#" onclick="delData();">删除</a></li><%}%>
	         <%if(kcKccheckAdd){%><li class="tubiao4" style="width:50px;"><a href="#" onclick="addData();">添加</a></li><%}%>
	         <%if(kcKccheckQuery){%><li class="tubiao1" style="width:50px;"><a href="#" onclick="queryData();">查询</a></li><%}%>
	        </ul>
	      </div>
	    </div>
	    
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== 查询条件 ======== -->
      <table id="marginTable" width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
        <tr>
          <td class="yx1" width="100" align="right">仓库：</td>
	      <td class="yx"><select name="warehouseid" onChange="getWhAreaList()"><option value=""></option></select></td>
	      <td class="yx1" width="100" align="right">库区：</td>
     	  <td class="xx1"><select id="whAreaId"><option value=""></option></select></td>
     	  <td class="y1" align="right" width="100">时间：</td> 
     	  <td class="xx1" colspan="3"><input id="fmDate" type="text" size="15" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:117px;"/>-<input id="toDate" type="text" size="15" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:117px;"/></td>
         
	    </tr>
	    <tr>
          <td class="y1" width="100" align="right">进场编码：</td>
	      <td class="x"><input name="lotnumber" type="text" size="20"></td>
	      <td class="y1" width="100" align="right">产品：</td>
	      <td class="x"><div align="left">
	       	<input type="hidden" name="package_id"><input type="text" name="package_name" class="input_readonly"/>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="…" class="button_select">
	     </div></td>
	      <td class="y1" width="100" align="right">类型：</td>
	      <td class="x"><select id="type"><option value=""></option></select></td>
	      <td class="y1" width="100" align="right">状态：</td>
	      <td><select id="status"><option value=""></option></select></td>
	    </tr>
	  </table> 
       
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
      
 	 <table width="99%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
	   <tr>
	     <td height="180">
		   <iframe id="list" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inventory/check/kc_check_list.jsp"></iframe>
		 </td>
	   </tr>
	   <tr>
	     <td height="25">
	  	     <iframe  id="page" name="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=6&flag=N" 
	  	     	 width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe> 
	       </td>
	   </tr>
	    <tr>
         <td valign="top" class="title" height="18" >盘点任务信息</td>
        </tr>
	   <tr>
	     <td>
		   <iframe id="detail" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inventory/check/kc_check_detail.jsp"></iframe>
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
