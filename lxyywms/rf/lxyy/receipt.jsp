<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader"%>
<%
	String instockid = (String) request.getAttribute("instockid");

	//设置重复提交的标志变量，begin为首次加载，finish为已提交
	session.setAttribute("submitFlag","begin");
	String strWarehouseID = (String)session.getAttribute("warehouseid");
	
	if (strWarehouseID == null || strWarehouseID.equals("null")) {
		strWarehouseID = (String) session.getAttribute("warehouseid");
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
	
	//**********************************************************
	//保存数据
	function saveData()
	{
        var warehouseid = "<%=strWarehouseID%>"
	   if(warehouseid=null || warehouseid==""){
	       alert("请重新登陆");
	       return;
	   }
		var invoiceid = document.getElementById("invoiceid").value; //入库单号
		var productName = document.getElementById("productName").value;	//产品名称
		var productid = document.getElementById("productid").value;	//产品id
		var traycode = document.getElementById("traycode").value; //托盘条码
		var getnum = document.getElementById("getnum").value;	//实收数量
		
		var platoon = document.getElementById("platoon1").value;//排
		var column = document.getElementById("column1").value;//列
		var floor = document.getElementById("floor1").value;//层
		
		
		if(invoiceid == null || invoiceid.length < 1){
			alert("【申请单号】不能为空！");
			return;
		}
		if(productName == null || productName.length < 1){
			alert("【产品名称】不能为空！");
			return;
		}
		if(traycode == null ||  traycode.length < 1)
		{
			alert("【托盘条码】不能为空！");
			return;
		}
		if(getnum == null || getnum.length <1 || !IsFloat(getnum)){
			alert("【数量】不能为空！");
			return;
		}else if(getnum<=0)
		{
			alert("数量需要大于0！");
			return false;
		}
		
		//验证托盘是否已经被收货 如果已经被收货则不在进行收货 除非取消收货以后方可进行再次收货
		if(traycode != null && traycode.length>1)
		{
			var msg;
			DWREngine.setAsync(false);
			judgmentTool.isRecebyTrayCode(invoiceid,traycode, Show);
			DWREngine.setAsync(true);
			function Show(value){
				msg = value;
			}
			if(msg != "Y"){
				alert(msg);
				return;
			}
		}
		if(platoon!=null&&platoon.length>0&&column!=null&&column.length>0&&floor!=null&&floor.length>0){
			
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
			    if(confirm("你确定收货吗？"))
				{		
					window.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=InBoundAction&flag=RF1&instockid=" + invoiceid+"&traycode="+traycode+"&num="+getnum+"&platoon="+platoon+"&column="+column+"&floor="+floor;
						
				}
			}
		}else{
			
			alert("建议指定货位！");
			return false;	
<%-- 					    if(confirm("你确定收货吗？"))
						{		
							window.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=InBoundAction&flag=RF1&instockid=" + invoiceid+"&traycode="+traycode+"&num="+getnum+"&platoon="+platoon+"&column="+column+"&floor="+floor;
								
						} --%>
		}
		
		
		//************************************************
		
	}
	function OnCancel() {
		document.all.over.style.display = "none";
		document.all.select.style.display = "none";
	}
	//选择一个入库单
	function OnSelect() {
		var msg = null;
		var check_ids = document.getElementsByName("check_id");
		var invoiceid = parent.document.getElementById("invoiceid");
		invoiceid.focus();
		for (var i=0; i<check_ids.length; i++) {
			if(check_ids[i].checked) {
				msg = check_ids[i].value;
				break;
			}
		}
		if (msg != null) {
			var bem = msg.split(",");
			document.getElementById("invoiceid").value = bem[0];
			
			document.all.over.style.display = "none";
			document.all.select.style.display = "none";
		} else {
			alert("请选择一条记录！");
		}
	}
	function disinfo(){
	  var warehouseid = "<%=strWarehouseID%>"
	  if(warehouseid=null || warehouseid==""){
		    alert("请重新登陆");
		    return;
	  }
	
	  
	  var traycode = document.getElementById("traycode").value;
	  if(traycode!=null && traycode!=""){
	       GetInfo();
	  }
	}
   	//获取没有确定及没有作废的入库单
	function Getinvoiceid(){
	       var warehouseid = "<%=strWarehouseID%>"
		   if(warehouseid=null || warehouseid==""){
		       alert("请重新登陆");
		       return;
		   }
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
	function GetInfo(){
	   var traycode = document.getElementById("traycode").value;
	   var invoiceid = document.getElementById("invoiceid").value;
	   getTool.GetdetailByid(invoiceid,traycode,Show);
	   function Show(data){
	      if(data!=null && data.length > 0){
	          var aem = data.split(",");
	          if(aem!=null){
		         document.getElementById("productName").value=aem[0];
		         document.getElementById("printdate").value=aem[1];
		         document.getElementById("lotinfo").value=aem[2];
		         document.getElementById("productid").value=aem[3];
		         document.getElementById("innum").value=aem[4];
		         document.getElementById("getnum").value=aem[4];
		         document.getElementById("unit").value = aem[6];
		         
		         document.getElementById("productName").disabled = true;
		         document.getElementById("printdate").disabled = true;
		         document.getElementById("lotinfo").disabled = true;
		         document.getElementById("innum").disabled = true;
	          }
	          
	      }else{
	             document.getElementById("productName").value="";
		         document.getElementById("printdate").value="";
		         document.getElementById("lotinfo").value="";
		         document.getElementById("productid").value="";
		         document.getElementById("innum").value="";
		         document.getElementById("getnum").value="";
		         document.getElementById("unit").value="";
		         
		         document.getElementById("productName").disabled = true;
		         document.getElementById("printdate").disabled = true;
		         document.getElementById("lotinfo").disabled = true;
		         document.getElementById("innum").disabled = true;
		         alert("没有找到相关产品信息");
	      }
	      
	   }
	   
	}
	//根据入库单号获取剩余的数量
	function getSurplusnumByinvoiceid(){
			var invoiceid = document.getElementById("invoiceid").value;
			if(invoiceid==null||invoiceid.length < 1){
				document.getElementById("surplusnum").value="";
			}else{
			   
				getTool.getSurplusnumByinvoiceid(invoiceid,Show);
				function Show(data){
					document.getElementById("surplusnum").value=data;
				}
			}
			
		
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
	
	//页面登陆
	function onLoad()
	{
	
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>"
		if(back_msg != "")
		{
			if(back_msg == "Y"){
				alert("操作成功！");
			}else{
				alert(back_msg);
			}
		}
		
		
		selectPlatoon( "platoon1");
		selectColumn( "column1");
		selectFloor( "floor1");
		
		var invoiceid = document.getElementById("invoiceid");
		invoiceid.focus();
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
						<td width="60%" align="center" class="font_006699_bold_12">RF收货</td>
						<td width="20%" align="right"><a href="<%=request.getContextPath()%>/rf/main.jsp?warehouseid=<%=strWarehouseID%>">返回</a></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">入库单号：</td>
			<td class="TD_ADD"><input type="text" name="invoiceid" onBlur="getSurplusnumByinvoiceid()" value="<%=instockid != null ? instockid : ""%>" class="rf_input_long"> <input name="moreBtn" type="button" class="button_select" value="…" onclick="Getinvoiceid()" /></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">入库单剩余数量：</td>
			<td class="TD_ADD"><input type="text" name="surplusnum" class="rf_input_long" maxlength="10" disabled> <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">托盘条码：</td>
			<td class="TD_ADD"><input type="text" name="traycode" class="rf_input_long" maxlength="10" onchange="disinfo()"> <font color="red">*</font></td>
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
			<td class="TD_ADD" align="right">批号：</td>
			<td class="TD_ADD"><input type="text" name="lotinfo" class="rf_input_long" maxlength="10" disabled> <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">单位：</td>
			<td class="TD_ADD"><input type="text" name="unit" class="rf_input_long" maxlength="10" disabled> <font color="red">*</font></td>
		</tr>

		<tr style="display:none">
			<td class="TD_ADD" align="right">应收数量：</td>
			<td class="TD_ADD"><input type="text" name="innum" class="rf_input_long" maxlength="10" disabled> <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">数量：</td>
			<td class="TD_ADD"><input type="text" name="getnum" class="rf_input_long" maxlength="10" disabled> <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">是否指定货位：</td>
			<td class="TD_ADD"><input type="checkbox" name="isCarspace" checked onclick="enableSel(this);"></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">货位：</td>
			<td class="TD_ADD"><select id="platoon1" style="width: 60px;" >
					<option value=""></option>
			</select> 排 <select id="column1" style="width: 60px;" >
					<option value=""></option>
			</select> 列 <select id="floor1" style="width: 60px;" >
					<option value=""></option>
			</select> 层</td>
		</tr>
		<tr>
			<td class="TD_ADD" align="center" colspan="2"><input type="button" name="add" class="BUTTON_STYLE1" value="收货" onClick="saveData();"></td>
		</tr>
	</table>
	<div id="over" style="position: absolute; display: none; top: 0px; left: 0px; width: 100%; height: 100%; background-color: #efefef; z-index: 1; filter: alpha(opacity =     70);">
		<iframe style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; z-index: -1;"></iframe>
	</div>
	<div id="select" style="position: absolute; display: none; top: 0px; left: 0px; width: 100%; z-index: 2; background: #ffffff;"></div>
</body>
</html>
