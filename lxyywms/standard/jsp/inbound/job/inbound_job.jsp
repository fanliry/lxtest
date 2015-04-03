<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean inboundJobAdd = false;    
	boolean inboundJobMainten = false;    
	boolean inboundJobQuery = false;
	boolean inboundJobCancel = false;     
	boolean inboundJobFinish = false; 
	boolean inboundJobComfirm = false; 
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("inboundJobAdd") != null){
			inboundJobAdd = true;
		}
		if(hsPopedom.get("inboundJobMainten") != null){
			inboundJobMainten = true;
		}
		if(hsPopedom.get("inboundJobQuery") != null){
			inboundJobQuery = true;
		}
		if(hsPopedom.get("inboundJobCancel") != null){
			inboundJobCancel = true;
		}
		if(hsPopedom.get("inboundJobFinish") != null){
			inboundJobFinish = true;
		}
		if(hsPopedom.get("inboundJobComfirm") != null){
			inboundJobComfirm = true;
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
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	var searchCondition = null;
	
	/*锁定按钮*/
	function LockButton(){
		document.getElementById("btn1").disabled = true;
		document.getElementById("btn2").disabled = true;
		//document.getElementById("btn3").disabled = true;
		document.getElementById("btn4").disabled = true;
		document.getElementById("btn5").disabled = true;
	}
	/*释放按钮*/
	function UnLockButton(){
		document.getElementById("btn1").disabled = false;
		document.getElementById("btn2").disabled = false;
		//document.getElementById("btn3").disabled = false;
		document.getElementById("btn4").disabled = false;
		document.getElementById("btn5").disabled = false;
	}
	
	function Mupload(){
		
	}
	
	//查询
	function searchData(){
	
		var warehouseid = document.getElementById("warehouseid").value;	//仓库
		var whAreaId = document.getElementById("whAreaId").value;		//库区
		var Virtualwhid = document.getElementById("Virtualwhid").value;		//逻辑库区id
		var indate = document.getElementById("indate").value;			//作业日期
		var jobid = document.getElementById("jobid").value;				//作业号
		var instockid = document.getElementById("instockid").value;	    //入库单号
		var status = document.getElementById("status").value;			//作业状态
		var traycode = document.getElementById("traycode").value;		//托盘条码
		var isback = document.getElementById("isback").value;			//是否回流
		var productid = document.getElementById("package_id").value;	//品名
		var lotinfo = document.getElementById("lotinfo").value;		    //批号信息
		var taskid = document.getElementById("taskid").value;		    //调度任务ID
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("请先选择【所属仓库】!");
			return;
		}
		
		Loading();
		condition = "&warehouseid=" +warehouseid + "&whAreaId=" +whAreaId + "&indate=" + indate + "&jobid=" + jobid 
			 + "&instockid=" + instockid + "&status=" + status + "&traycode=" + traycode + "&isback=" + isback 
			 + "&productid=" + productid + "&lotinfo=" + lotinfo + "&taskid=" + taskid + "&Virtualwhid=" + Virtualwhid;
		searchCondition = condition;
		list.location.href = strUrl + "inBoundJobAction&flag=1" + condition;
		
		detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/job/inbound_job_detail.jsp";
	}
	
	//1.手动完成 2.作废
	function updateData(flag){
		LockButton();
	    var warehouseid = document.getElementById("warehouseid").value;	//仓库
		var whAreaId = document.getElementById("whAreaId").value;		//库区
		var indate = document.getElementById("indate").value;			//作业日期
		var jobid = document.getElementById("jobid").value;				//作业号
		var instockid = document.getElementById("instockid").value;	    //入库单号
		var status = document.getElementById("status").value;			//作业状态
		var traycode = document.getElementById("traycode").value;		//托盘条码
		var isback = document.getElementById("isback").value;			//是否回流
		var productid = document.getElementById("package_id").value;	//品名
		var lotinfo = document.getElementById("lotinfo").value;		    //批号信息
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("请先选择【所属仓库】!");
			return;
		}
		
		condition = "&warehouseid=" +warehouseid + "&whAreaId=" +whAreaId + "&indate=" + indate + "&jobid=" + jobid 
			 + "&instockid=" + instockid + "&status=" + status + "&traycode=" + traycode + "&isback=" + isback 
			 + "&productid=" + productid + "&lotinfo=" + lotinfo;
		
		var k = 0;
		var id = "";
		var check_ids = list.document.getElementsByName("check_id");
		var onetraycodels = list.document.getElementsByName("traycode");
		var onetraycode = "";
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id = check_ids[i].value;
				onetraycode = onetraycodels[i].value;
				k++;
			}
		}
		if( k == 0 ){
			alert("请选择一条作业！");
			UnLockButton();
			return;
		}else if(k != 1){
		    alert("只能选择一条作业！");
		    UnLockButton();
			return;
		}
		if(flag == 4){	//手动完成
		
		    var msg;
			DWREngine.setAsync(false);
			judgmentTool.finishJobs(id,Show);
			DWREngine.setAsync(true);
			function Show(value){
				msg = value;
			}
			if(msg != "Y"){
				alert(msg);
				UnLockButton();
				return;
			}
			list.location.href = strUrl + "inBoundJobAction&method=ricoExecFinish&jobids=" + id+condition;
			detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/job/inbound_job_detail.jsp";
			
		}else if(flag == 5){ //作业初始化
			var msg;
			DWREngine.setAsync(false);
			judgmentTool.initializeJobs(id,Show);
			DWREngine.setAsync(true);
			function Show(value){
				msg = value;
			}
			if(msg != "Y"){
				alert(msg);
				UnLockButton();
				return;
			}
			
			list.location.href = strUrl + "inBoundJobAction&method=ricoExecinitialize&jobids=" + id+condition;
			detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/job/inbound_job_detail.jsp";
		}else if(flag == 6){ //移库到暂存区（只有入库单未执行作业）
			var msg;
			DWREngine.setAsync(false);
			judgmentTool.WaitJobs(id,Show);
			DWREngine.setAsync(true);
			function Show(value){
				msg = value;
			}
			if(msg != "Y"){
				alert(msg);
				UnLockButton();
				return;
			}
			list.location.href = strUrl + "inBoundJobAction&flag=3&jobid=" + id + condition;
			detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/job/inbound_job_detail.jsp";
		}else if(flag == 7){ //作废作业
			var msg;
			DWREngine.setAsync(false);
			judgmentTool.cancelJobs(id,Show);
			DWREngine.setAsync(true);
			function Show(value){
				msg = value;
			}
			if(msg != "Y"){
				alert(msg);
				UnLockButton();
				return;
			}
			
			list.location.href = strUrl + "inBoundJobAction&method=ricoExecCancel&jobids=" + id+condition;
			detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/job/inbound_job_detail.jsp";
		}else if(flag == 8){ //入库维护
			if(onetraycode != null && onetraycode.length > 0)
			{
				var returnValue = window.showModalDialog("<%=request.getContextPath()%>/BMSService?actionCode=InjobSafeguardAction&flag=1&isWho=pc&jobid="+id+"&traycode="+onetraycode,"","dialogWidth=800px;dialogHeight=600px;");
				if(returnValue != null){
					list.location.href = strUrl + returnValue;
				}else{
					UnLockButton();
				}
				//list.location.href = strUrl + "inBoundJobAction&flag=1" + searchCondition;
			}
			detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/job/inbound_job_detail.jsp";
		}
	}
	
	//入库增加
	function rkadd(){
		var whid = document.getElementById("warehouseid").value;	//仓库
		var returnValue = window.showModalDialog("<%=request.getContextPath()%>/standard/jsp/inbound/job/injob_rkadd.jsp?whid="+whid,"","dialogWidth=800px;dialogHeight=600px;");
		if(returnValue != null){
			list.location.href = strUrl + returnValue;
		}else{
			UnLockButton();
		}
		
		detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/job/inbound_job_detail.jsp";
	}
	
	//页面登录
	function OnLoad(){
		
		//仓库
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");	
		//库区
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		selectAreaList("", "Virtualwhid", warehouseid, "3");
		
		selectType('2', 'status', 'zyzt');			//作业状态
		DWREngine.setAsync(true);
		searchData();
		
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
		<div id="dqwz" class="f_l">当前位置：<span>入库管理 &gt;&gt; 作业管理</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
		    <%if(inboundJobComfirm){%><li class="tubiao4" ><input onclick="updateData(6)" type="button" id="btn5" value="移库到暂存" class="button_edit4_out" onmouseover="this.className='button_edit4_over'" onmouseout="this.className='button_edit4_out'" style="width: 85px;"></li><%}%>
		  	<%if(inboundJobFinish){%><li class="tubiao2" ><input onclick="updateData(4);" type="button" id="btn4" value="手动完成" class="button_edit4_out" onmouseover="this.className='button_edit4_over'" onmouseout="this.className='button_edit4_out'" style="width: 72px;"></li><%}%>
		    <!--<%if(inboundJobFinish){%><li class="tubiao3" ><input onclick="updateData(5)" type="button" id="btn3" value="入库作业初始化" class="button_edit4_out" onmouseover="this.className='button_edit4_over'" onmouseout="this.className='button_edit4_out'" style="width: 110px;"></li><%}%>-->
		  	<%if(inboundJobCancel){%><li class="tubiao2" ><input onclick="updateData(7)" type="button" id="btn2" value="作废作业" class="button_edit4_out" onmouseover="this.className='button_edit4_over'" onmouseout="this.className='button_edit4_out'" style="width: 75px;"></li><%}%>
			<%if(inboundJobQuery){%><li class="tubiao1" ><input onclick="searchData();" type="button" id="btn1" value="查询" class="button_search4_out" onmouseover="this.className='button_search4_over'" onmouseout="this.className='button_search4_out'" style="width: 50px;"></li><%}%>
		  	<%if(inboundJobMainten){%><li class="tubiao2" ><input onclick="updateData(8);" type="button" id="btn4" value="入库维护" class="button_edit4_out" onmouseover="this.className='button_edit4_over'" onmouseout="this.className='button_edit4_out'" style="width: 72px;"></li><%}%>
		  	<%if(inboundJobAdd){%><li class="tubiao2" ><input onclick="rkadd();" type="button" id="btn4" value="入库增加" class="button_edit4_out" onmouseover="this.className='button_edit4_over'" onmouseout="this.className='button_edit4_out'" style="width: 72px;"></li><%}%>
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
     	  <td class="yx"><select id="whAreaId" style="width:117px;"><option value=""></option></select></td>
     	  <td class="yx1" align="right">逻辑库区：</td>
     	  	<td class="yx">
     	  	<select id="Virtualwhid" style="width:117px;"><option value=""></option></select>
     	  	</td>
	      <td class="yx1" align="right">作业号：</td>
	      <td class="xx1"><input type="text" id="jobid" size="15" ></td>
	    </tr>
        <tr>
	      <td class="yx1" align="right">入库单号：</td>
	      <td class="yx"><input type="text" name="instockid"   style="height:18px;width:110px;"/></td>
	      <td class="yx1" align="right">作业状态：</td>
	      <td class="yx"><select id="status" style="width:117px;"><option value=""></option></select></td>
	      <td class="yx1" align="right">托盘条码：</td>
	      <td class="yx"><input name="traycode" type="text" size="15"></td>
	      <td class="yx1" align="right">是否回流：</td>
	      <td class="xx1">
         	<select id="isback" style="width:117px;"><option value="">--请选择--</option><option value="Y">回流</option><option value="N">非回流</option></select>
       	  </td>
	    </tr>
	    <tr>
	      <td class="yx1" align="right">品名：</td>
	      <td class="yx">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name" size="15" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  
            	type="button" value="…" class="button_select">
	      </td>
	      <td class="yx1" width="100" align="right">进厂编号：</td>
	      <td class="yx"><input type="text" name="lotinfo" style="height:18px;width:110px;"/></td>
		  <td class="yx1" align="right">日期：</td>
	      <td class="yx"><input id="indate" type="text" size="15" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:117px;"></td>
	      <td class="yx1" width="100" align="right">调度号：</td>
	      <td class="yx"><input type="text" name="taskid" style="height:18px;width:110px;"/></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0" >
<!-- 		  <tr> -->
<!--             <td valign="bottom" class="title" height="18px">作业信息</td> -->
<!--           </tr> -->
	      <tr>
	        <td height="180px">
		      <iframe id="list" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inbound/job/inbound_job_list.jsp"></iframe>
		    </td>
	      </tr>
	      <tr>
	       <td height="25">
	  	     <iframe  id="page" name="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=6&flag=N" 
	  	     	 width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe> 
	       </td>
	     </tr>
	      <tr>
            <td valign="bottom" class="title" height="19px">
          	  作业【<span id="jobidzo" style="color: red;font-weight:bold;"></span>】详细信息
            </td>
          </tr>
	      <tr>
	        <td>
		      <iframe id="detail" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inbound/job/inbound_job_detail.jsp"></iframe>
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