<%@ page contentType="text/html; charset=GBK"%>
<%
 	//A客户单据ID
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
 //显示暂存区
 function viewstorage()
 {
 	var warehouseid = document.getElementById("warehouseid").value;	
 	if(warehouseid == null || warehouseid.length < 1){
		alert("【仓库】不能为空！");	
	}else{
		selectZCAreaList('zcwhareaid', warehouseid);//暂存区
		
 			var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=sendAction&flag=1";
 			Loading();
 			list.location.href = strUrl + "&warehouseid=" + warehouseid;	
	}	
 }
 function OnLoad()
 {	
	DWREngine.setAsync(false);
	selectObject('', 'warehouseid', '1');
	DWREngine.setAsync(true);
	viewstorage();//显示暂存区
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
		   	 <td class="x" width="50"><span id="n_remind" class="title" style="color: red; ">暂存区:</span></td>
		     <td class="y1" width="70"><div align="right">仓&nbsp;&nbsp;&nbsp;&nbsp;库：</div></td>
		     <td class="x" width="80"><div align="left">
		     	<select name="warehouseid"  style="width:100px;" onchange="viewstorage();">
                    <option value="">--请选择--</option>
                  </select>
		     </div></td> 
		     <td class="y1" width="70"><div align="right">暂&nbsp;存&nbsp;区：</div></td>
		     <td class="x" width="80"><div align="left">
		     	<select name="zcwhareaid"  style="width:100px;">
                    <option value="">--请选择--</option>
                  </select>
		     </div></td>
		     <td  height="27" colspan="2"><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;
		     </div></td> 
		   </tr>
		 </table>	
   	</td>
   </tr>
   <tr>
     <td height="100%" >
       <iframe id="list" name="list" width="100%" height="100%" frameborder="0" scrolling="no"
         src="<%=request.getContextPath()%>/standard/jsp/outbound/fhqr/outbound_fhqr_adjust_zc_list.jsp"></iframe>
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
 