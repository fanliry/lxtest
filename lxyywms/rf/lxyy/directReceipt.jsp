<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader" %>
<%
	String traycode = (String)request.getAttribute("traycode");
	String strWarehouseID = request.getParameter("warehouseid");
%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/getTool.js"></script>


<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	/*锁定按钮*/
	function LockButton(){
		
		document.getElementById("add").disabled = true;
	}
	/*释放按钮*/
	function UnLockButton(){
		
		document.getElementById("add").disabled = false;
	}

	
	//**********************************************************
	//保存数据
	function saveData()
	{
		
		var traycode = document.getElementById("traycode").value; //托盘条码
		var productName = document.getElementById("productName").value;	//产品名称
		var getnum = document.getElementById("getnum").value;	//实收数量
		var jobId = document.getElementById("jobId").value;
		var jobDetailId = document.getElementById("jobDetailId").value;
		var inventoryId = document.getElementById("inventoryId").value;
	
		if(productName == null || productName.length < 1){
			alert("【产品名称】不能为空！");
			return;
		}
		if(traycode == null ||  traycode.length < 1)
		{
			alert("【托盘条码】不能为空！");
			return;
		}
		if(getnum == null && getnum.length < 1)
		{
			alert("【数量】不能为空！");
			return;
		}
		//************************************************
		if(confirm("你确定直接出货吗？"))
		{	var condition = "&jobId="+jobId+"&jobDetailId="+jobDetailId+"&inventoryId="+inventoryId;
			window.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=inBoundActionlxyy&rfmainView=rfmainView&flag=ZJCK" + condition;
		}
	}
	function OnCancel() {
		document.all.over.style.display = "none";
		document.all.select.style.display = "none";
	}
	
	function disinfo(){
	  var warehouseid = "<%=strWarehouseID%>"
	  if(warehouseid=null || warehouseid==""){
		    alert("请重新登陆");
		    return;
	  }
	  
	  var traycode = document.getElementById("traycode").value;
	  if(traycode!=null && traycode!=""){
	  	  GetZCShowInfo(traycode);
	  }
	}
	
	function GetZCShowInfo(traycode){
		getTool.GetZCJobDetailByTraycode(traycode,"<%=strWarehouseID%>",Show);
			function Show(data) {
				if(data.length > 0) {
					var aem = data.split("|");
					var bem = null;					
						bem = aem[0].split(",");
						document.getElementById("customerName").value = bem[1];
						document.getElementById("productName").value = bem[2];
						document.getElementById("printdate").value = bem[4];
						document.getElementById("lotinfo").value = bem[5];
						document.getElementById("getnum").value = bem[3];
						
						document.getElementById("jobId").value = bem[8];
						document.getElementById("jobDetailId").value = bem[9];
						document.getElementById("inventoryId").value = bem[10];
						document.getElementById("customerId").value = bem[6];
						document.getElementById("productid").value = bem[7];
						
				} else {
					alert("未查找到相应的出库作业");
				}
				
		   }
	}
	//页面登陆
	function onLoad()
	{
		var result = "<%=request.getAttribute("result") == null?"":(String)request.getAttribute("result")%>";
		if(result != "")
		{
			if(result == "Y"){
				alert("操作成功！");
			}else{
				alert(result);
			}
		}
	}
-->
</script>
</head>

<body onload="onLoad();">
 <table id="tbb"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_MGR_TOP">
   <tr>
     <td height="25" bgcolor="#d9e8fc" align="center" colspan="2">
 <table width="100%">
   <tr>
     <td width="20%"></td>
     <td width="60%" align="center" class="font_006699_bold_12">RF直接出库</td>
     <td width="20%" align="right"><a href="<%=request.getContextPath()%>/rf/main.jsp?warehouseid=<%=strWarehouseID%>">返回</a></td>
   </tr>
 </table>
     </td>
   </tr>
     <td class="TD_ADD" align="right">托盘条码：</td>
     <td class="TD_ADD"><input type="text" id="traycode"  class="rf_input_long" maxlength="10"  onchange="disinfo()">  <font color="red">*</font>
     <input type="hidden" id="jobId"/>
     <input type="hidden" id="jobDetailId"/>
     <input type="hidden" id="inventoryId"/>
     </td>
   </tr>
    <tr>  
     <td class="TD_ADD" align="right">客 &nbsp;&nbsp;&nbsp;户：</td>
     <td class="TD_ADD"><input type="text" id="customerName"  class="rf_input_long" maxlength="10" disabled> <font color="red">*</font>
     <input type="hidden" id="customerId"/>
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">产品名称：</td>
     <td class="TD_ADD"><input type="text" id="productName" class="rf_input_long" maxlength="10" disabled>  <font color="red">*</font>
     <input type="hidden" id="productid"/>
     </td>
   </tr>
   <tr> 
     <td class="TD_ADD" align="right">生产日期：</td>
     <td class="TD_ADD"><input type="text" id="printdate" class="rf_input_long" maxlength="10" disabled> <font color="red">*</font></td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">进场编码：</td>
     <td class="TD_ADD"><input type="text" id="lotinfo" class="rf_input_long" maxlength="10" disabled>  <font color="red">*</font>
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">数量：</td>
     <td class="TD_ADD"><input type="text" id="getnum"  class="rf_input_long" maxlength="10" disabled>  <font color="red">*</font>
     </td>
   </tr>
   <tr>
     <td class="TD_ADD" align="center" colspan="2">
       <input type="button" name="add" class="BUTTON_STYLE1" value="直接出库" onClick="saveData();">
     </td>
    </tr>
 </table>
<div id="over" style="position:absolute; display:none; top:0px; left:0px; width:100%; height:100%; 
  background-color:#efefef; z-index:1; filter:alpha(opacity=70);">
<iframe style="position:absolute; top:0; left:0; width:100%; height:100%; z-index:-1;"></iframe>
</div>
<div id="select" style="position:absolute; display:none; top:0px; left:0px; width:100%; z-index:2; background: #ffffff;"></div>
</body>
</html>