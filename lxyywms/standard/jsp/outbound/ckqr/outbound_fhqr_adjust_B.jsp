<%@ page contentType="text/html; charset=GBK"%>
<%
 	//单据ID
    String strOutBoundId = request.getParameter("aoutid");

 %>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
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
	
	//****************************************************
	
    
    //************************************************************************************
    
    
    
    //显示B客户单据详细记录
 	function viewdata()
 	{
 		//B单据ID
		var outinvoiceid = document.getElementById("outinvoiceid").value;
		if(outinvoiceid == null || outinvoiceid.length < 1){
			alert("【单据】不能为空！");
			
		}else{
 			
 			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&flag=12";
 			Loading();
 			list.location.href = strUrl + "&invoiceid=" + outinvoiceid;	
		}
	
 	}
 	//显示B客户出库单
 	function viewinvoice(aoutid)
 	{
 		var warehouseid = document.getElementById("warehouseid").value;	
 		if(warehouseid == null || warehouseid.length < 1){
			alert("【仓库】不能为空！");
			
		}else{
 			getBoutInvoiceId(warehouseid, aoutid, 'outinvoiceid');
		}	
 	}
	function OnLoad(){	
		DWREngine.setAsync(false);
		selectObject('', 'warehouseid', '1');
		DWREngine.setAsync(true);
		viewinvoice('<%=strOutBoundId%>');//显示B客户出库单
	}
-->
</script>
</head>

<body onload="OnLoad();">
 <input type="hidden" name="warehouse_out_id">
 <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
   <tr>
     <td height="100%"> 
 <table width="100%" height="100%" align="center" border="0" cellpadding="0" cellspacing="0">
   <tr height="20">
   	<td width="10%">
   		 <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
		   <tr >
		   	 <td class="x" width="50"><span id="n_remind" class="title" style="color: red; ">B客户:</span></td>
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
                <td class="x">
                	<input type="text" name="vehicleno" class="input_readonly"  readonly   style="height:18px;width:100px;"/>
                </td>      
              </tr>
		 </table>	
   	</td>
   </tr>
   <tr>
     <td height="100%" colspan="2">
       <iframe id="list" name="list" width="100%" height="100%" frameborder="0" scrolling="no"
         src="<%=request.getContextPath()%>/standard/jsp/outbound/fhqr/outbound_fhqr_adjust_B_list.jsp"></iframe>
     </td>
   </tr>
 </table>
     </td>
   </tr>
 </table>    
<!-- 页面加载层（start） -->
<div id="loader_container" style="position: absolute; display: none; z-index: 2; filter:alpha(opacity=75);">
	<div id="loader"><div align="center">页面正在加载 ...</div><div id="loader_bg"><div id="progress"></div></div></div>
</div>
<!-- 页面加载层（end） -->
</body>
</html>
 