<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/standard/style/load.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
		// 发货检测
	function sendCheck() 
	{ 
		var warehouse_out_id = document.getElementById("outinvoiceid").value;
		if (warehouse_out_id == null || warehouse_out_id.length < 1) {
			alert("请选择一张单据！");
			return;
		}

		DWREngine.setAsync(false);
		judgmentTool.sendCheck(warehouse_out_id, ShowCheck);
		DWREngine.setAsync(true);
		function ShowCheck(data) { // 显示检测结果
			if (data == "Y") {
				alert("单据【" + warehouse_out_id + "】按开单数量完成，可以发货确认！");
			} else {
				alert("单据【" + warehouse_out_id + "】开单数量与发货数量不等，其中：\r\r" + data);
			}
		}
	}
	// 发货确认
	function sendOk() 
	{ 
		var warehouse_out_id = document.getElementById("outinvoiceid").value;
		if (warehouse_out_id == null || warehouse_out_id.length < 1) {
			alert("请选选择一张单据！");
			return;
		}

		var check_msg = "";
		DWREngine.setAsync(false);
		judgmentTool.sendCheck(warehouse_out_id, ShowCheck);
		DWREngine.setAsync(true);
		function ShowCheck(data) {
			check_msg = data;
		}
		if (check_msg == "Y") {
			msg = "单据【" + warehouse_out_id + "】按开单数量完成，可以发货确认！\r\r";
			msg += "你确定对单据【" + warehouse_out_id + "】发货确认？";
		
			if(confirm(msg)) 
			{
				list.location.href = strUrl + "sendAction&method=ricoExecOkFHQR&out_id=" + warehouse_out_id;
			}
			
			
		} else {
			
			   msg = "单据【" + warehouse_out_id + "】开单数量与发货数量不等，其中：\r\r" + check_msg + "\r\r与开单数据不符的将进行处理！\r\r";
			
	
			//	var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/outbound/outbound_fhqr_adjust.jsp?outId="+warehouse_out_id, 
				//	warehouse_out_id, "dialogWidth=800px; dialogHeight=600px; scroll=no");
				//if(result != null && result.length > 0) 
				//{	
				//}
				msg += "如有剩余数量将直接放入暂存区！\r\r";
				msg += "你确定对单据【" + warehouse_out_id + "】进行发货确认？";
			
				if(confirm(msg)) 
				{
					list.location.href = strUrl + "sendAction&method=ricoExecOkFHQR&out_id=" + warehouse_out_id;
				}
		}

		
	}
	
	//对单据详细进行发货确认
	function sendOneOk() 
	{ 
		var warehouse_out_id = document.getElementById("outinvoiceid").value;
		if (warehouse_out_id == null || warehouse_out_id.length < 1) {
			alert("请选选择一张单据！");
			return;
		}
		
		var ids = "";//单据详细ID
		var check_ids = list.document.getElementsByName("check_id");	
		for(var i=0; i<check_ids.length; i++)
		{
			if(check_ids[i].checked == true){
				ids = check_ids[i].value ;
				break;
			}
		}
		if(ids == ""){
			alert("请选择一条记录");
			return;
		}
		
		var check_msg = "";
		DWREngine.setAsync(false);
		judgmentTool.sendOneCheck(warehouse_out_id, ids, ShowCheck);
		DWREngine.setAsync(true);
		
		function ShowCheck(data) {	
			check_msg = data;
		}
		if (check_msg == "Y") {
			msg = "单据【" + warehouse_out_id + "】的这条单据详细，可以发货确认！\r\r";
			msg += "你确定对单据【" + warehouse_out_id + "】的这条单据详细发货确认？";
		
			if(confirm(msg)) 
			{
				list.location.href = strUrl + "sendAction&method=ricoExecOneOkFHQR&out_id=" + warehouse_out_id + "&out_detail_id=" + ids;
		
			}
			
			
		} else {
			    
			    msg = "单据【" + warehouse_out_id + "】的这条单据详细开单数量与发货数量不等，其中：\r\r" + check_msg + "\r\r与开单数据不符的将进行处理！\r\r";
			
				//var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/outbound/outbound_fhqr_adjust.jsp?outid="+warehouse_out_id + "&outdetailid=" + ids, 
				//	warehouse_out_id, "dialogWidth=800px; dialogHeight=600px; scroll=no");
                
				//if(result != null && result.length > 0) 
				//{}
				msg += "如有剩余数量将直接放入暂存区！\r\r";
				msg += "你确定对单据【" + warehouse_out_id + "】的这条详细进行发货确认？";
				if(confirm(msg)) 
				{
					list.location.href = strUrl + "sendAction&method=ricoExecOneOkFHQR&out_id=" + warehouse_out_id + "&out_detail_id=" + ids;					
				}
			}
	}





	
	function adjust()
	{
		//单据ID
		var outinvoiceid = document.getElementById("outinvoiceid").value;
		if(outinvoiceid == null || outinvoiceid.length < 1){
			alert("【出库单】不能为空！");
			
		}else{
			var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/outbound/fhqr/outbound_fhqr_adjust.jsp?outid="+outinvoiceid, 
						outinvoiceid, "dialogWidth=800px; dialogHeight=600px; scroll=no");
			if(result != null && result.length > 0) 
			{
				
				
			}
		}
	}

	//显示单据详细记录
 	function viewdata()
 	{
 		//单据ID
		var outinvoiceid = document.getElementById("outinvoiceid").value;
		if(outinvoiceid == null || outinvoiceid.length < 1){
			alert("【单据】不能为空！");
			
		}else{
 			
 			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&flag=11";
 			Loading();
 			list.location.href = strUrl + "&invoiceid=" + outinvoiceid;	
		}
	
 	}
 	//显示出库单
 	function viewinvoice()
 	{
 		var warehouseid = document.getElementById("warehouseid").value;
 		
 		if(warehouseid == null || warehouseid.length < 1){
			alert("【仓库】不能为空！");
			
		}else{
 			selectObject(warehouseid, 'outinvoiceid', '13');
		}
 		
 	}
	
	function OnLoad(){	
	
		var back_msg = "<%=request.getAttribute("back_msg") == null ? "" : (String)request.getAttribute("back_msg")%>";
		if (back_msg != "") 
		{
			if(back_msg == "Y")
			{
				alert("确认成功!");
			}else
			{
				alert(back_msg);
			}
		}
		DWREngine.setAsync(false);
		selectObject('', 'warehouseid', '1');
		DWREngine.setAsync(true);
		viewinvoice();//显示出库单
	}
-->
</script>
</head>

<body onload="OnLoad();">

<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== 当前位置 ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">当前位置：<span>出库管理 &gt;&gt; 发货确认</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
          	<li class="tubiao1" style="width:90px;"><a href="#" onclick="sendOk();">发货确认</a></li>
		    <li class="tubiao2" style="width:110px;" ><a href="#" onclick="sendOneOk();">按详细发货确认</a></li>
		    <li class="tubiao2" style="width:90px;" ><a href="#" onclick="adjust();">发货调整</a></li>
		    <li class="tubiao2" style="width:90px;" ><a href="#" onclick="sendCheck();">发货检测</a></li>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== 查询条件 ======== -->
      <table width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center">  
             <tr>
             	<td width="60"  class="y1"><div align="right">仓&nbsp;&nbsp;&nbsp;&nbsp;库：</div></td>
                <td width="150" class="x"><div align="left">
                  <select name="warehouseid"  style="width:100px;" class="input_readonly" onchange="viewinvoice();">
                    <option value="">--请选择--</option>
                  </select>
                </td>
             	<td width="60"  class="y1"><div align="right">出库单号：</div></td>
                <td width="150" class="x"><div align="left">
                  <select name="outinvoiceid"  style="width:120px;" onchange="viewdata();">
                    <option value="">----请选择----</option>
                  </select>
                </td>
				<td width="60" class="y1"><div align="right">车&nbsp;牌&nbsp;号：</div></td>
                <td class="x" width="150">
                	<input type="text" name="vehicleno" class="input_readonly"  readonly   style="height:18px;width:100px;"/>
                </td>
                <td width="60" class="y1"><div align="right">车&nbsp;&nbsp;&nbsp;&nbsp;位：</div></td>
                <td >
                	<input type="text" name="vehiclepos" class="input_readonly"  readonly   style="height:18px;width:100px;"/>
                </td>       
              </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
		 <td valign="top" height="100%">
		 
		 
		  <table width="99%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
		   <tr>
		     <td>
		       <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/outbound/fhqr/outbound_fhqr_list.jsp" 
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
