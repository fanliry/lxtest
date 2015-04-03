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
		var productName = document.getElementById("productName").value; //品名
		var productCode = document.getElementById("productCode").value; //产品编码
		var num = document.getElementById("num").value; //托盘条码
		var traycode = document.getElementById("traycode").value; //托盘条码
		
		
		if(traycode == null ||  traycode.length < 1)
		{
			alert("【托盘条码】不能为空！");
			return;
		}
		
		if(confirm("你确定直接出货吗？"))
		{	var condition = "&traycode="+traycode+"&customerId="+customerId+"&productId="+productId+"&productDate="+productDate+"&lotinfo="+lotinfo+"&getnum="+getnum+"&jobId="+jobId+"&jobDetailId="+jobDetailId+"&inventoryId="+inventoryId;
			window.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=InBoundAction&rfmainView=rfmainView&flag=ZJCK" + condition;
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
	  //var traycode = document.getElementById("traycode").value;
	  if(traycode!=null && traycode!=""){
	  		
	  	   //GetZCShowInfo(traycode);
	       GetInfo();
	  }
	}
	
	function GetZCShowInfo(traycode){
		
		getTool.GetJobInfoByTraycode(traycode,Show);
		
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
						
						//bem = aem[0].split(",");
						//document.getElementById("traycode").value = bem[0];
						
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
	function GetInfo(){
		
	   var traycode = document.getElementById("traycode").value;
	   getTool.GetJobInfoByTraycode(traycode,Show);
	   function Show(data){
		   
	      if(data!=null && data.length > 0){
	          var aem = data.split(",");
	          
	          if(aem!=null){
		         document.getElementById("productName").value=aem[0];
		         document.getElementById("lotinfo").value=aem[1];
		         document.getElementById("productCode").value=aem[2];
		         document.getElementById("num").value=aem[3];
		         document.getElementById("warehouseName").value=aem[4];
		         document.getElementById("scargoWhareaName").value=aem[5]; 
		      //   document.getElementById("productName").disabled = true;
	          }
	          
	      }else{
	             document.getElementById("productName").value="";
		         document.getElementById("lotinfo").value="";
		         document.getElementById("productCode").value="";
		         document.getElementById("num").value="";
		         document.getElementById("warehouseName").value="";
		         document.getElementById("scargoWhareaName").value="";
		         alert("没有找到相关产品信息");
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
     <td width="60%" align="center" class="font_006699_bold_12">RF查询</td>
     <td width="20%" align="right"><a href="<%=request.getContextPath()%>/rf/main.jsp?warehouseid=<%=strWarehouseID%>">返回</a></td>
   </tr>
 </table>
     </td>
   </tr>
     <td class="TD_ADD" align="right">托盘条码：</td>
     <td class="TD_ADD"><input type="text" name="traycode"  class="rf_input_long" maxlength="10"  onchange="disinfo()">  <font color="red">*</font>
     
     
     </td>
   </tr>
    <tr>  
     <td class="TD_ADD" align="right">品 &nbsp;&nbsp;&nbsp;名：</td>
     <td class="TD_ADD"><input type="text" name="productName"  class="rf_input_long" maxlength="10" disabled> <font color="red">*</font>
     
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">产品编码：</td>
     <td class="TD_ADD"><input type="text" name="productCode" class="rf_input_long" maxlength="10" disabled>  <font color="red">*</font>
    
     </td>
   </tr>
   <tr> 
     <td class="TD_ADD" align="right">数 &nbsp;&nbsp;&nbsp;量：</td>
     <td class="TD_ADD"><input type="text" name="num" class="rf_input_long" maxlength="10" disabled> <font color="red">*</font></td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">批 &nbsp;&nbsp;&nbsp;号：</td>
     <td class="TD_ADD"><input type="text" name="lotinfo" class="rf_input_long" maxlength="10" disabled>  <font color="red">*</font>
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">仓 &nbsp;&nbsp;&nbsp;库：</td>
     <td class="TD_ADD"><input type="text" name="warehouseName"  class="rf_input_long" maxlength="10">  <font color="red">*</font>
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">库 &nbsp;&nbsp;&nbsp;区：</td>
     <td class="TD_ADD"><input type="text" name="scargoWhareaName"  class="rf_input_long" maxlength="10">  <font color="red">*</font>
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
