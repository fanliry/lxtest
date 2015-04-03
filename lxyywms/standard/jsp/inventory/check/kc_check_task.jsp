<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckRequest" %>
<%
	InventoryCheckRequest req = (InventoryCheckRequest)request.getAttribute("checkreq");  
	String requestid = req.getRequestid();		//ID
	String type = req.getTypeName();			//类型
	String status = req.getStatus();			//状态
	//String statusname = req.getStatusName();	//状态
	String warehouseid = req.getWarehouseid();	//仓库
	//String whareaid = req.getWh_area_id();		//库区
	String wharea = req.getWhAreaName();		//库区
	//String cargospaceid = req.getCargo_space_id();	//货位
	String cargospace = req.getCargoSpaceName();//货位
	//String productid = req.getProductid();		//产品
	String product = req.getProductName();		//产品
	//String ownerid = req.getCustomerid();		//货主
	String lotnumber = req.getLotinfo();			//批号
	String traycode = req.getTraycode();		//托盘条码
	//String boxcode = req.getBoxcode();         	//箱条码
    String productcode = req.getProductcode(); 	//产品条码
	
%>
<html>
<head>
<title>生成盘点任务</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>
<script type="text/javascript">
<!--

	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	
	//查询
	function queryData(){
	
		condition = "&requestid=<%=requestid%>";
		list.location.href = ac + "inventoryCheckAction&flag=5" + condition;
	}
	
	//生成盘点任务
	function saveData(){
	
		var checkbox_ids = list.document.getElementsByName("check_id");
		var ids = "";
		for(var i=0; i<checkbox_ids.length; i++){
	  		if(checkbox_ids[i].checked){
	  			ids += checkbox_ids[i].value + ",";
	  		}
  		}
  		
  		if(ids == ""){
			alert("请至少选择一条记录!");
		  	return false;
		}
	
		condition = "&requestid=<%=requestid%>&ids=" + ids;
		list.location.href = ac + "inventoryCheckAction&method=ricoExecAddTask" + condition;
	}
	
	//页面登陆
	function OnLoad(){
		
		
	}

-->
</script>
</head>

<body onLoad="OnLoad()">
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：库存管理 -&gt; 库存盘点 -&gt; 生成盘点任务</div></td>
   </tr>
 </table>
 
 <table width="99%">
   <tr>
     <td align="right">
        <input type="button" onClick="queryData()" value="&nbsp;&nbsp;查询&nbsp;&nbsp;" class="BUTTON_STYLE1">
        <input style="width: 105px;" type="button" onclick="saveData()" id="add" value="&nbsp;&nbsp;生成盘点任务" class="button_add" <%if(!status.equals("1")){%> disabled <%}%>>
        <input type="button" onClick="window.close()" id="resetBtn" value="&nbsp;&nbsp;关闭&nbsp;&nbsp;" class="BUTTON_STYLE1">
     </td>
   </tr>
 </table> 
 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td width="100px" class="yx1" align="right">仓库：</td>
     <td class="xx1" colspan="5"><input type="text" id="warehouseid" size="15" value="<%=warehouseid ==null?"":warehouseid%>" readonly class="input_readonly"></td>  
   </tr>
   <tr>
     <td width="100px" class="yx1" align="right">库区：</td>
     <td class="yx"><input type="text" id="wh_area_id" size="15" value="<%=wharea==null?"":wharea%>" readonly class="input_readonly"></td>
     <td width="100px" class="yx1" align="right">货位：</td>
     <td class="yx"><input type="text" id="cargospace_id" size="15" value="<%=cargospace==null?"":cargospace%>" readonly class="input_readonly"></td>
     <td width="100px" class="yx1" align="right">品名：</td>
     <td class="xx1"><input type="text" id="product" size="15" value="<%=product==null?"":product%>" readonly class="input_readonly"></td>
   </tr>
   <tr>
     <td class="y1" align="right">进场编号：</td>
     <td class="x"><input type="text" id="lotnumber" size="15" value="<%=lotnumber==null?"":lotnumber%>" readonly class="input_readonly"></td>  
     <td class="y1" align="right">托盘条码：</td>
     <td class="x"><input type="text" id="traycode" size="15" value="<%=traycode==null?"":traycode%>" readonly class="input_readonly"></td>
     <td class="y1" align="right">产品编码：</td>
     <td><input type="text" id="productcode" size="15" value="<%=productcode==null?"":productcode%>" readonly class="input_readonly"></td>
   </tr>
 </table>
 <table><tr><td height="10"></td></tr></table>
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">  
   <tr>
	 <td height="400px">
	   <iframe id="list" width="100%" height="100%" frameborder="0" scrolling="yes"
	   		src="<%=request.getContextPath()%>/standard/jsp/inventory/check/kc_check_task_list.jsp"></iframe>
	 </td>
   </tr>
 </table>

</body>
</html>