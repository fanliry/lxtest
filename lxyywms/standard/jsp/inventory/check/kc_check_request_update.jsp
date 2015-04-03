<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckRequest" %>
<%
	InventoryCheckRequest req = (InventoryCheckRequest)request.getAttribute("checkreq");  
	String requestid = req.getRequestid();		//ID
	String type = req.getCounttype();			//类型
	String lotnumber = req.getLotinfo();		//批号
	String warehouseid = req.getWarehouseid();	//仓库
	String cargospaceid = req.getCargo_space_id();	//货位
	String cargospacename = req.getCargoSpaceName();//货位
	String whareaid = req.getWh_area_id();		//库区
	String productid = req.getProductid();		//产品
	String productname = req.getProductName();	//产品
	String traycode = req.getTraycode();		//托盘条码
%>
<html>
<head>
<title>修改盘点请求单</title>
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
	//保存
	function saveData(){
	
		var type =  document.getElementById("type").value;					//类型
		var wh_area_id = document.getElementById("wh_area_id").value;		//库区
	    var cargo_space_id = document.getElementById("cargospace_id").value;//货位
	    var lotnumber = document.getElementById("lotnumber").value;		    //批号
	    var productid = document.getElementById("package_id").value;		//品名
	    var traycode = document.getElementById("traycode").value;			//托盘条码
	    var requestid = document.getElementById("requestid").value;			//申请单id
		var condition = "&type=" + type + "&wh_area_id=" + wh_area_id
		    + "&cargo_space_id=" + cargo_space_id
			+ "&lotnumber=" + lotnumber + "&productid=" + productid 
			+ "&traycode=" + traycode + "&requestid=" + requestid;
	    window.returnValue = condition;
		window.close();
	}
	
	//库区改变的时候
	function OnWhareaChange(){
		
		//清空货位
		document.getElementById('cargospace_id').value = "";
		document.getElementById('cargospace_name').value = "";
	}
	
	//页面登陆
	function OnLoad(){
	
		DWREngine.setAsync(false);
		selectType('<%=type%>', 'type', 'pdlx');			//类型
		selectAreaList("<%=whareaid%>", "wh_area_id", "<%=warehouseid%>", "1");	//库区
		DWREngine.setAsync(true);
	}

-->
</script>
</head>

<body onLoad="OnLoad()">
<form id="myForm" method="post" action="">

  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：库存管理 -&gt; 库存盘点 -&gt; 修改盘点申请</div></td>
   </tr>
 </table>
 
 <table><tr><td height="10"></td></tr></table> 
 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td width="100px" class="yx1" align="right">类型：<input type="hidden" id="requestid" size="15" value="<%=requestid%>"></td>
     <td class="yx"><select id="type" style="width:115px;"><option value=""></option></select></td>
	 <td class="yx1" align="right">批号：</td>
	 <td class="xx1"><input type="text" id="lotnumber" size="15" value="<%=lotnumber%>" style="ime-mode:disabled"></td>
   <tr>
   	 <td class="yx1" align="right">库区：</td>
     <td class="yx"><select id="wh_area_id" style="width:115px;" onchange="OnWhareaChange()"><option></option></select></td>
     <td class="yx1" align="right">货位：</td>
     <td class="xx1">
     	<input type="hidden" id="cargospace_id" value="<%=cargospaceid==null?"":cargospaceid%>">
     	<input type="text" id="cargospace_name" size="15" readonly value="<%=cargospacename==null?"":cargospacename%>">
       	<input onClick="openCargospace('<%=request.getContextPath()%>/standard/jsp/common/common_cargospace.jsp?warehouseid=<%=warehouseid%>&whAreaId='+document.getElementById('wh_area_id').value,'850','550');" 
       		type="button" value="…" class="button_select">
     </td>
   </tr>
   <tr>
   	 <td class="y1" align="right">托盘条码：</td>
	 <td class="x"><input type="text" id="traycode" size="15" maxlength="50" value="<%=traycode%>" style="ime-mode:disabled"></td>
	 <td class="y1" align="right">品名：</td>
	 <td>
	   <input type="hidden" id="package_id" value="<%=productid==null?"":productid%>">
	   <input type="text" id="package_name" size="15" readonly value="<%=productname==null?"":productname%>">
       <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="…" class="button_select">
	 </td>
   </tr>
 </table>
 
 <table><tr><td height="10"></td></tr></table>  
 <table><tr><td height="10"></td></tr></table>
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">  
   <tr>
     <td height="27" align="center">
        <input type="button" onclick="saveData()" id="add" value="&nbsp;&nbsp;&nbsp;保存" class="button_add">&nbsp; 
        <input type="reset" id="resetBtn" value="&nbsp;&nbsp;&nbsp;重置" class="button_reset">&nbsp;
        <input type="button" onClick="window.close()" id="resetBtn" value="关闭" class="BUTTON_STYLE1">
     </td>
   </tr>
 </table>
 </form>

</body>
</html>