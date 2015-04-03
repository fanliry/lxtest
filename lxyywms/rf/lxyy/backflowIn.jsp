<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader" %>
<%
	
	String traycode = (String)request.getAttribute("traycode");
	
	
	//设置重复提交的标志变量，begin为首次加载，finish为已提交
	session.setAttribute("submitFlag","begin");
	String strWarehouseID = request.getParameter("warehouseid");
	if(strWarehouseID == null  || strWarehouseID.equals("null")){
		strWarehouseID = (String)session.getAttribute("warehouseid");
	}
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
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>

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

    //检查数据是否为浮点数
	function IsFloat(ch){
		var re = /^\d+(\.\d+)?$/;
		return re.test(ch);
	}
	
	//根据仓库获得库区，货区的列表
	function getPlatoon(){
		
		selectPlatoon( "platoon1");
		
	}
	
	function getColumn(){
		selectColumn( "column1");
		
	}
	
	function getFloor(){
		selectFloor( "floor1");
		
	}
	//是否选取指定货位
	function enableSel(obj){
		var platoon = document.getElementById("platoon1");//排
		var column = document.getElementById("column1");//列
		var floor = document.getElementById("floor1");//层
		
		if(obj.checked){
			platoon.disabled =obj.checked?false:true;
			column.disabled =obj.checked?false:true;
			floor.disabled =obj.checked?false:true;
		}else{
			platoon.value="";
			column.value="";
			floor.value="";
			platoon.disabled =obj.checked?false:true;
			column.disabled =obj.checked?false:true;
			floor.disabled =obj.checked?false:true;
		}
	}
	//**********************************************************
	//保存数据
	function saveData()
	{
		
		var traycode = document.getElementById("traycode").value; //托盘条码
		var warehouseid = document.getElementById("warehouseid").value; //仓库
		var whAreaId = document.getElementById("whAreaId").value; 		//库区
		var Virtualwhid = document.getElementById("Virtualwhid").value; //逻辑库区
		
		var productId = document.getElementById("productid").value;	//产品Id
		
		var productName = document.getElementById("productName").value;	//产品名称
		
		var productDate = document.getElementById("printdate").value;//生产日期
		
		var lotinfo = document.getElementById("lotinfo").value;	//批号
		
		var getnum = document.getElementById("getnum").value;	//实收数量
		
		var sjtraycode = document.getElementById("sjtraycode").value; //上架托盘条码
		
		var inventoryId = document.getElementById("inventoryId").value;
		
		
		var platoon = document.getElementById("platoon1").value;//排
		var column = document.getElementById("column1").value;//列
		var floor = document.getElementById("floor1").value;//层
		
		if(whAreaId == null || whAreaId.length < 1){
			alert("该逻辑库区没有设置所属的【物理库区】！");
			return;
		}else{
			if(whAreaId=="001003"){
				alert("请选择暂存以外的库区");
				return;
			}
		}
		
		if(productName == null || productName.length < 1){
			alert("【产品名称】不能为空！");
			return;
		}
        if(getnum == null || getnum.length <1 || !IsFloat(getnum)){
			alert("【数量】不能为空或者只能为数字！");
			return;
		}else if(getnum<=0)
		{
			alert("数量需要大于0！");
			return false;
		}
		//验证托盘
		if(sjtraycode == null ||  sjtraycode.length < 1)
		{
			alert("【上架托盘条码】不能为空！");
			return;
		}else{
			var msg;
			DWREngine.setAsync(false);
			judgmentTool.isTrayCodeUse(sjtraycode, Show);
			DWREngine.setAsync(true);
			function Show(value){
				msg = value;
			}
			if(msg != "Y"){
				alert(msg);
				return;
			}
		}	
		//************************************************
		if(platoon!=null&&platoon.length>0&&column!=null&&column.length>0&&floor!=null&&floor.length>0){
			
			DWREngine.setAsync(false);
			judgmentTool.iswhAreaId(warehouseid, whAreaId, platoon, column, floor, Show2);
			DWREngine.setAsync(true);
			var msg2;
			
			function Show2(value){
				msg2 = value;
			}
			if(msg2 != "Y"){
				alert(msg2);
				return false;
			}
			
			var msg1;
			DWREngine.setAsync(false);
			judgmentTool.isCarspace("<%=strWarehouseID%>",platoon,column, floor,Show1);
			DWREngine.setAsync(true);
			function Show1(value){
				msg1 = value;
			}
			if(msg1 != "Y"){
				alert(msg1);
				return;
			}else{
			    if(confirm("你确定回流入库吗？"))
				{
					var condition = "&traycode="+traycode+"&sjtraycode="+sjtraycode+"&productId="+productId+"&productDate="+productDate+"&lotinfo="+lotinfo+"&getnum="+getnum+"&inventoryId="+inventoryId+"&platoon="+platoon+"&column="+column+"&floor="+floor+ "&Virtualwhid=" + Virtualwhid;
					window.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=inBoundActionlxyy&rfmainView=rfmainView&flag=HLRKplus" + condition;
				}
			}
		}else{//没有指定货位
		    if(confirm("你确定回流入库吗？"))
			{
				var condition = "&traycode="+traycode+"&sjtraycode="+sjtraycode+"&productId="+productId+"&productDate="+productDate+"&lotinfo="+lotinfo+"&getnum="+getnum+"&inventoryId="+inventoryId+"&platoon="+platoon+"&column="+column+"&floor="+floor+ "&Virtualwhid=" + Virtualwhid;
				window.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=inBoundActionlxyy&rfmainView=rfmainView&flag=HLRKplus" + condition;
			}
		}

	}
	function OnCancel() {
		document.all.over.style.display = "none";
		document.all.select.style.display = "none";
	}
	//选择一个入库单
	function OnSelect() {
		var msg = null;
		var check_ids = document.getElementsByName("check_id");
		
		for (var i=0; i<check_ids.length; i++) {
			if(check_ids[i].checked) {
				msg = check_ids[i].value;
				
				break;
			}
		}
		if (msg != null) {
			var bem = msg.split(",");
			document.getElementById("customerName").value = bem[1];
			document.getElementById("productName").value = bem[2];
			document.getElementById("getnum").value = bem[3];
			document.getElementById("printdate").value = bem[4];
			document.getElementById("lotinfo").value = bem[5];
			document.getElementById("customerId").value = bem[6];
			document.getElementById("productid").value = bem[7];
			document.getElementById("jobId").value = bem[8];
			document.getElementById("jobDetailId").value = bem[9];
			document.getElementById("inventoryId").value = bem[10];
			document.all.over.style.display = "none";
			document.all.select.style.display = "none";
		} else {
			alert("请选择一条记录！");
		}
	}
	function disinfo(){
		
		  var traycode = document.getElementById("traycode").value;
		  document.getElementById("sjtraycode").value = traycode;
		  if(traycode!=null && traycode!=""){
		  	   //GetZCShowInfo(traycode);
		      GetInfo(traycode);
		  }
	}
	
	function GetZCShowInfo(traycode){		
		getTool.GetZCHLInventoryByTraycode(traycode,"<%=strWarehouseID%>",Show);
		function Show(data) {
				if(data.length > 0) {
					var aem = data.split("|");
					var bem = null;
					if (aem.length > 1) {
						var cem = null;
						var info = "<table id='tb' width='100%' align='center' cellpadding='0' cellspacing='0' class='TABLE_MGR_TOP'>"	
						for (var i=0; i<aem.length; i++) {
							cem = aem[i].split(",");
							info += "<tr>"
								+ "<td class='TD_ADD' align='center' width='25'>"
								+ "<input type='radio' name='check_id' class='input_radio' value='" + aem[i] + "'></td>"
								+ "<td class='TD_ADD' align='center'>" + cem[0] + "</td>"
								+ "<td class='TD_ADD' align='center'>" + cem[1] + "</td>"
								+ "<td class='TD_ADD' align='center'>" + cem[2] + "</td>"
								+ "<td class='TD_ADD' align='center'>" + cem[3] + "</td>"
								+ "</tr>"
						}
						info += "<tr><td class='TD_ADD' align='center' colspan='4'>"
							  + "<input type='button' name='save' onClick='OnSelect()' class='BUTTON_STYLE1' value='确定'> "
							  + "<input type='button' name='save' onClick='OnCancel()' class='BUTTON_STYLE1' value='取消'>"
							  + "</td></tr></table>";
						
						document.all.over.style.display = "block";
						document.all.select.style.display = "block";
						document.all.select.innerHTML = info;
						document.all.select.style.top = (document.body.scrollHeight-document.all.tb.clientHeight)/2;
						return;
					} else {
						GetInfo();
					}
					
					
				} else {
					alert("未查找到相应的申请单");
				}
			
		}
	}
   	//获取没有确定及没有作废的入库单
	function Getinvoiceid(){
			getTool.Getinvoiceid("<%=strWarehouseID%>",Show);
			function Show(data) {
				if(data.length > 0) {
					var aem = data.split("|");
					var bem = null;
					if (aem.length > 1) {
						var cem = null;
						var info = "<table id='tb' width='100%' align='center' cellpadding='0' cellspacing='0' class='TABLE_MGR_TOP'>"	
						for (var i=0; i<aem.length; i++) {
							cem = aem[i].split(",");
							info += "<tr>"
								+ "<td class='TD_ADD' align='center' width='25'>"
								+ "<input type='radio' name='check_id' class='input_radio' value='" + aem[i] + "'></td>"
								+ "<td class='TD_ADD' align='center'>" + cem[0] + "</td>"
								+ "<td class='TD_ADD' align='center'>" + cem[1] + "</td>"
								+ "<td class='TD_ADD' align='center'>" + cem[2] + "</td>"
								+ "</tr>"
						}
						info += "<tr><td class='TD_ADD' align='center' colspan='4'>"
							  + "<input type='button' name='save' onClick='OnSelect()' class='BUTTON_STYLE1' value='确定'> "
							  + "<input type='button' name='save' onClick='OnCancel()' class='BUTTON_STYLE1' value='取消'>"
							  + "</td></tr></table>";
						
						document.all.over.style.display = "block";
						document.all.select.style.display = "block";
						document.all.select.innerHTML = info;
						document.all.select.style.top = (document.body.scrollHeight-document.all.tb.clientHeight)/2;
						return;
					} else {
						bem = aem[0].split(",");
					}
					document.getElementById("invoiceid").value = bem[0];
					
				} else {
					alert("未查找到相应的申请单");
				}
			}
	}
	function GetInfo(traycode){
	   var warehouseid = "<%=strWarehouseID%>"
	   if(warehouseid=null || warehouseid==""){
	       alert("请重新登陆");
	       return;
	   }
	   getTool.GetZCHLInventoryByTraycode(traycode,"<%=strWarehouseID%>",Show);
	   function Show(data){
	      if(data!=null && data.length > 0){
	            var aem = data.split(",");
	            if(parseInt(aem[6])==0){ 
		          	document.getElementById("inventoryId").value = aem[0];
					document.getElementById("productid").value = aem[1];
					document.getElementById("productName").value = aem[2];
					document.getElementById("printdate").value = aem[3];
					document.getElementById("lotinfo").value = aem[4];
					document.getElementById("getOriNum").value = aem[5];
					document.getElementById("getnum").value = aem[5];
					
	            }else{
	                document.getElementById("inventoryId").value = "";
					document.getElementById("productid").value = "";
					document.getElementById("productName").value = "";
					document.getElementById("printdate").value = "";
					document.getElementById("lotinfo").value = "";
					document.getElementById("getOriNum").value = "";
					document.getElementById("getnum").value = "";
		            alert("该暂存已分配 不能回流");
	            }
	            
	      }else{
	    	    document.getElementById("inventoryId").value = "";
				document.getElementById("productid").value = "";
				document.getElementById("productName").value = "";
				document.getElementById("printdate").value = "";
				document.getElementById("lotinfo").value = "";
				document.getElementById("getOriNum").value = "";
				document.getElementById("getnum").value = "";
	           alert("没有找到相关产品信息");
	      }
	   }
	}
	
	//获得仓库信息
	function GetWhInfo(){
		   var Virtualwhid = document.getElementById("Virtualwhid").value;
		   getTool.getWhInfo(Virtualwhid,pShow);
		   function pShow(data){
		      if(data!=null && data.length > 0){
		          var aem = data.split("|");
		          if(aem!=null){
			         document.getElementById("warehouseid").value=aem[0];
			         document.getElementById("whAreaId").value=aem[1];
		          }
		          
		      }else{
		             document.getElementById("warehouseid").value="";
			         document.getElementById("whAreaId").value="";
			         alert("没有找到相关库区信息");
		      }
		      
		   }
	}
	
	//页面登陆
	function onLoad()
	{
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		var result = "<%=request.getAttribute("result") == null?"":(String)request.getAttribute("result")%>";
		
		if(result != "")
		{
			if(result == "Y"){
				alert("操作成功！");
			}else{
				alert(result);
			}
		} 
		
		selectAreaList("", "Virtualwhid", "<%=strWarehouseID %>", "3");
		document.getElementById("warehouseid").value="<%=strWarehouseID %>";
		
		selectPlatoon( "platoon1");
		selectColumn( "column1");
		selectFloor( "floor1");
		return;
	}
-->
</script>
</head>

<body onload="onLoad();">
	<table id="tbb" border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_MGR_TOP">
		<tr>
			<td height="25" bgcolor="#d9e8fc" align="center" colspan="2">
				<table width="100%">
					<tr>
						<td width="20%"></td>
						<td width="60%" align="center" class="font_006699_bold_12">RF回流人库</td>
						<td width="20%" align="right"><a href="<%=request.getContextPath()%>/rf/main.jsp?warehouseid=<%=strWarehouseID%>">返回</a></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">逻辑库区：</td>
     	  	<td class="TD_ADD">
     	  	<input type="hidden" name="warehouseid" disabled>
     	  	<input type="hidden" name="whAreaId" disabled>
     	  	<select id="Virtualwhid" style="width:163px;" onchange="GetWhInfo();" ><option value=""></option></select>
     	  	</td>
		</tr>
		<td class="TD_ADD" align="right">托盘条码：</td>
		<td class="TD_ADD"><input type="text" name="traycode" class="rf_input_long" maxlength="10" onchange="disinfo()"> <font color="red">*</font> <input type="hidden" name="jobId" /> <input type="hidden" name="jobDetailId" /> <input type="hidden" name="inventoryId" /></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">产品名称：</td>
			<td class="TD_ADD"><input type="text" name="productName" class="rf_input_long" maxlength="10" disabled> <font color="red">*</font> <input type="hidden" name="productid" /></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">生产日期：</td>
			<td class="TD_ADD"><input type="text" name="printdate" class="rf_input_long" maxlength="10" disabled> <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">进场编号：</td>
			<td class="TD_ADD"><input type="text" name="lotinfo" class="rf_input_long" maxlength="10" disabled> <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">回流数量：</td>
			<td class="TD_ADD"><input type="text" name="getOriNum" class="rf_input_long" maxlength="10" disabled> <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">实际回流数量：</td>
			<td class="TD_ADD"><input type="text" name="getnum" class="rf_input_long" maxlength="10"> <font color="red">*</font></td>
		</tr>
		<tr>
	     <td class="TD_ADD" align="right">上架托盘条码：</td>
	     <td class="TD_ADD"><input type="text" id="sjtraycode"  class="rf_input_long" maxlength="10"> <font color="red">*</font>  
	     </td>
	   </tr>
		<tr>
			<td class="TD_ADD" align="right">是否指定货位：</td>
			<td class="TD_ADD"><input type="checkbox" name="isCarspace" checked onclick="enableSel(this);"></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">货位：</td>
			<td class="TD_ADD"><select id="platoon1" style="width: 60px;">
					<option value=""></option>
			</select> 排 <select id="column1" style="width: 60px;">
					<option value=""></option>
			</select> 列 <select id="floor1" style="width: 60px;">
					<option value=""></option>
			</select> 层</td>
		</tr>
		<tr>
			<td class="TD_ADD" align="center" colspan="2"><input type="button" name="add" class="BUTTON_STYLE1" value="回流入库" onClick="saveData();"></td>
		</tr>
	</table>
<span style="color:Red">注：若不指定货位，系统将按默认要求随机指定一个空货位</span>
<div id="over" style="position:absolute; display:none; top:0px; left:0px; width:100%; height:100%; 
  background-color:#efefef; z-index:1; filter:alpha(opacity=70);">
<iframe style="position:absolute; top:0; left:0; width:100%; height:100%; z-index:-1;"></iframe>
</div>
<div id="select" style="position:absolute; display:none; top:0px; left:0px; width:100%; z-index:2; background: #ffffff;"></div>
</body>
</html>
