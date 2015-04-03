<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean inbound_scrkdmgrconfirm = false;    //确定
	boolean inbound_scrkdmgrinvalid = false;     //作废
	boolean inbound_scrkdmgrprint = false; //打印入库单
	boolean inbound_scrkdmgrprintreceive = false;     //打印收货单
	boolean inbound_scrkdmgrquery = false; //查询
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("inbound_scrkdmgrconfirm") != null){
			inbound_scrkdmgrconfirm = true;
		}
		if(hsPopedom.get("inbound_scrkdmgrinvalid") != null){
			inbound_scrkdmgrinvalid = true;
		}
		if(hsPopedom.get("inbound_scrkdmgrprint") != null){
			inbound_scrkdmgrprint = true;
		}
		if(hsPopedom.get("inbound_scrkdmgrprintreceive") != null){
			inbound_scrkdmgrprintreceive = true;
		}
		if(hsPopedom.get("inbound_scrkdmgrquery") != null){
			inbound_scrkdmgrquery = true;
		}
    }
%>
<%
	String strTime =  StrTools.getCurrDateTime(8); 
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
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/getTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />

<script>
<!--自适应高度的div-->
 	var condition = null;
    //作废入库单
    function deleteinvoice(){
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
 	    var msg;
		DWREngine.setAsync(false);
		judgmentTool.isDelete(id, Show);
		DWREngine.setAsync(true);
		function Show(value){
			msg = value;
		}
		if(msg != "Y"){
			alert(msg);
			return;
		}
		
 	    var warehouseid = document.getElementById("warehouseid").value;//仓库ID
 	    var inrequeststockid = document.getElementById("inrequeststockid").value;//申请单据号
		var instatus = document.getElementById("instatus").value;//单据状态
		var productName = document.getElementById("productName").value;//产品名称
		var lotinfo = document.getElementById("lotinfo").value;//批号
		var invoicetype = document.getElementById("invoicetype").value;//入库类型
		
		var starttime = document.getElementById("start_time").value;//开始日期
		var endtime = document.getElementById("end_time").value;//结束日期
		
 		var condition = "&warehouseid=" + warehouseid + "&inrequeststockid=" + inrequeststockid +"&instatus=" + instatus + "&productName=" + productName + "&lotinfo=" + lotinfo+ "&starttime=" + starttime + "&endtime=" + endtime+ "&invoicetype=" + invoicetype;
 		
		list.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=InBoundAction&flag=4&instockid="+id;
		tableheight2.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/newin/lxyy/inbound_scnewrkdmgrde_list.jsp";
 	}
 	//确认入库单
 	function confirminvoice(){
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
 	    var msg;
		DWREngine.setAsync(false);
		judgmentTool.isRKDYZ(id, Show);
		DWREngine.setAsync(true);
		function Show(value){
			msg = value;
		}
		if(msg != "Y"){
			alert(msg);
			return;
		}
		
		var msg1;
		DWREngine.setAsync(false);
		judgmentTool.isRKDconfirm(id, Show1);
		DWREngine.setAsync(true);
		function Show1(value){
			msg1 = value;
			
		}
		if(msg1 != "Y"){
			if(confirm(msg1)){
			}else{
			    return;
			}	
		}
 	    var warehouseid = document.getElementById("warehouseid").value;//仓库ID
 	    var inrequeststockid = document.getElementById("inrequeststockid").value;//申请单据号
		var instatus = document.getElementById("instatus").value;//单据状态
		var productName = document.getElementById("productName").value;//产品名称
		var lotinfo = document.getElementById("lotinfo").value;//批号
		var invoicetype = document.getElementById("invoicetype").value;//入库类型
		
		var starttime = document.getElementById("start_time").value;//开始日期
		var endtime = document.getElementById("end_time").value;//结束日期
 		var condition = "&warehouseid=" + warehouseid + "&inrequeststockid=" + inrequeststockid +"&instatus=" + instatus + "&productName=" + productName + "&lotinfo=" + lotinfo+ "&starttime=" + starttime + "&endtime=" + endtime+ "&invoicetype=" + invoicetype;
 		
		list.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=InBoundAction&flag=9&instockid="+id+condition;
		tableheight2.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/newin/lxyy/inbound_scnewrkdmgrde_list.jsp";
 	}
 	//打印入库单
	function printinvoice(){
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
		var WLeft = Math.ceil((window.screen.width-800)/2);
  		var WTop  = Math.ceil((window.screen.height-600)/2);
  	   window.open("<%=request.getContextPath()%>/BMSService?actionCode=InBoundAction&flag=5&instockid="+id,"new","dialogLeft='"+WLeft+"';dialogTop='"+WTop+"';dialogWidth=800px;dialogHeight=600px;");
	}
	//打印收货单
	function printreceiverecord(){
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
		
 	    var WLeft = Math.ceil((window.screen.width-800)/2);
  		var WTop  = Math.ceil((window.screen.height-600)/2);	
  	    window.open("<%=request.getContextPath()%>/BMSService?actionCode=InBoundAction&flag=6&instockid="+id,"new","dialogLeft='"+WLeft+"';dialogTop='"+WTop+"';dialogWidth=800px;dialogHeight=600px;");
	}
 	
 	//查询
 	function queryinvoice()
 	{
 		
 		
		var warehouseid = document.getElementById("warehouseid").value;//仓库ID
		var inrequeststockid = document.getElementById("inrequeststockid").value;//申请单据号
		var instatus = document.getElementById("instatus").value;//单据状态
		var productName = document.getElementById("productName").value;//产品名称
		var lotinfo = document.getElementById("lotinfo").value;//批号
		var invoicetype = document.getElementById("invoicetype").value;//入库类型
		
		var starttime = document.getElementById("start_time").value;//开始日期
		var endtime = document.getElementById("end_time").value;//结束日期
		
 		var condition = "&warehouseid=" + warehouseid + "&inrequeststockid=" + inrequeststockid + "&instatus=" + instatus + "&productName=" + productName + "&lotinfo=" + lotinfo + "&starttime=" + starttime + "&endtime=" + endtime+ "&invoicetype=" + invoicetype;
 		var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=InBoundAction&flag=2"+condition;
 		Loading();
 		list.location.href = strUrl + condition;
		tableheight2.location.href = "<%=request.getContextPath()%>/standard/jsp/inbound/newin/lxyy/inbound_scnewrkdmgrde_list.jsp";	
 	}
 
 	function OnLoad()
	{
		selectObject('', 'warehouseid', '1');
		selectType('1', 'instatus', 'rkdstatus');
		selectType('9', 'invoicetype', 'rkdj');
	}
</script>

</head>

<body  onload="javascript:OnLoad();">
<div class="con_bk" >
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td>
	     <div class="wz" >
	      <div id="dqwz" class="f_l">当前位置：<span>入库管理</span> &gt;&gt; <span>生产入库管理</span> &gt;&gt; <span>入库单管理</span></div>
	      <div  class="f_r" id="caozuo">
	        <ul>
	          <%if(inbound_scrkdmgrquery){%><li class="tubiao1"><a href="#" onclick="queryinvoice();">查询</a></li><%}%>
	          <%if(inbound_scrkdmgrprintreceive){%><li class="tubiao2" style="width:100px"><a href="#" onclick="printreceiverecord();">打印收货单</a></li><%}%>
	          <%if(inbound_scrkdmgrprint){%><li class="tubiao2" style="width:100px"><a href="#" onclick="printinvoice();">打印入库单</a></li><%}%>
	          <%if(inbound_scrkdmgrinvalid){%><li class="tubiao3"><a href="#" onclick="deleteinvoice();">作废</a></li><%}%>
	          <%if(inbound_scrkdmgrconfirm){%><li class="tubiao2" style="width:50px"><a href="#" onclick="confirminvoice();">确认</a></li><%}%>
	        </ul>
	      </div>
	    </div>
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="5"></td>
   </tr>
 </table>			
 <table id="querycondition" width="99%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
              <tr>
                <td width="80"  class="yx1"><div align="right">收货仓库：</div></td>
                <td width="150" class="yx"><div align="left">
                  <select id="warehouseid" style="width:100px;">
                    <option value="">--请选择--</option>
                  </select></div>
                </td>
                <td width="80" class="yx1" align="right"><div align="right">申请单号：</div></td>
                <td width="150"class="yx">
                  <input type="text" name="inrequeststockid"  style="height:18px;width:100px;"/>
                </td>
                <td class="yx1" width="80"><div align="right">日期：</div></td>
                <td class="xx1"  ><div align="left">
                  <input name="start_time" type="text" size="13" value="<%=strTime%>" onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
                  -<input name="end_time" type="text" size="13" value="<%=strTime%>" onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" /> 
                </td>
             </tr>
             <tr>
             	<td width="80"  class="yx1"><div align="right">单据状态：</div></td>
                <td width="150" class="yx"><div align="left">
                  	<select id="instatus" style="width:100px;">
                       <option value="">--请选择--</option>
                    </select>
                </td>
                <td width="80" class="yx1"><div align="right">产品名称：</div></td>
                <td width="150" class="yx" >
                	<input type="text" name="productName"   style="height:18px;width:100px;"/>
                </td>  
                <td width="80" class="yx1"><div align="right">批号：</div></td>
                <td width="150" class="yx" >
                	<input type="text" name="lotinfo"   style="height:18px;width:100px;"/>
                </td>   
              </tr> 
              <tr>
             	<td width="80" class="yx1" align="right"><div align="right">入库类型：</div></td>
                <td width="150"class="yx">
                  <select id="invoicetype" style="width:100px;" disabled>
                    <option value="">--请选择--</option>
                  </select>
                </td>
                <td></td>
                <td></td>  
                <td></td>
                <td></td>   
              </tr> 
</table> 
            
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="5"></td>
   </tr>
 </table>
 
     </td>
   </tr>
   <tr>
     <td height="100%"> 
     
  <!-- ***** 调度作业信息及作业详细*****-->
 	 <table width="99%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
<!-- 	   <tr> -->
<!--            <td height="18" class="title">入库单信息 -->
<!--                 </td> -->
<!--        </tr> -->
	   <tr>
	     <td height="180">
		   <iframe id="list" name="list" width="100%" height="100%"   frameborder="0"  scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inbound/newin/lxyy/inbound_scnewrkdmgr_list.jsp"></iframe>
		 </td>
	   </tr>
	   <tr>
	     <td height="25">
	  	  <iframe  id="page" name="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=6&flag=N"   width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe> 
	     </td>
	   </tr>
	   <tr >
            <td valign="top" class="title" height="18" >
              		入库单【<span id="rkid" style="color: red;font-weight:bold;"></span>】明细信息
            </td>
        </tr>
	   <tr>
	     <td>
		   <iframe id="tableheight2" name="tableheight2" width="100%" height="100%"   frameborder="0"  scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inbound/newin/lxyy/inbound_scnewrkdmgrde_list.jsp"></iframe>
		 </td>
	   </tr>
	 </table>
 <!-- *******************************-->
 
 
 
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
