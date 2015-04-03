<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader" %>
<%
	
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

	//检查数据是否为浮点数
	function IsFloat(ch){
		var re = /^\d+(\.\d+)?$/;
		return re.test(ch);
	}
	//**********************************************************
	//保存数据
	function saveData()
	{
		var traycode = document.getElementById("traycode").value; //托盘条码
		var ClientName = document.getElementById("ClientName").value; //收货人
		var productName = document.getElementById("productName").value;	//产品名称
		var jobid = document.getElementById("jobid").value;	//作业id
		
		var outnum = document.getElementById("outnum").value;    //出库数量
		//var invoicenum = document.getElementById("invoicenum").value;	//开单数量
		var fpnum = document.getElementById("fpnum").value;	//分配数量

		if(traycode == null ||  traycode.length < 1)
		{
			alert("【托盘条码】不能为空！");
			return;
		}
		if(productName == null || productName.length < 1){
			alert("【品名】不能为空！");
			return;
		}
		
		//判断该作业是否完成
		if(jobid != null && jobid.length>1)
		{
			var msg;
			DWREngine.setAsync(false);
			judgmentTool.CheckJobs(jobid, Show);
			DWREngine.setAsync(true);
			function Show(value){
				msg = value;
			}
			if(msg != "Y"){
				alert(msg);
				return;
			}
		}
		if(fpnum == null || fpnum.length <1 || !IsFloat(fpnum)){
			alert("【分配数量】不能为空或者只能为数字！");
			return;
		}
		//************************************************	
		if(outnum == fpnum){
		      window.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&rfmainView=rfmainView&flag=fh&jobid=" + jobid;	
		}else{
		      if(confirm("出库数量与分配数量不一致 多余的产品自动转入暂存区")){
				 window.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&rfmainView=rfmainView&flag=fh&jobid=" + jobid;	
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
	   getTool.GetCheckUpByTray(traycode,"<%=strWarehouseID%>",Show);
	   function Show(data){
	      if(data!=null && data.length > 0){
	          var aem = data.split(",");
	          if(aem!=null){
		         document.getElementById("ClientName").value=aem[0];
		         document.getElementById("productName").value=aem[1];
		         document.getElementById("printdate").value=aem[2];
		         document.getElementById("lotinfo").value=aem[3];
		         document.getElementById("outnum").value=aem[4];
		         document.getElementById("fpnum").value=aem[5];
		         document.getElementById("jobid").value=aem[6];
	          }
	      }else{
	             document.getElementById("ClientName").value="";
		         document.getElementById("productName").value="";
		         document.getElementById("printdate").value="";
		         document.getElementById("lotinfo").value="";
		         document.getElementById("outnum").value="";
		         document.getElementById("fpnum").value="";
		         document.getElementById("jobid").value="";
		         alert("没有找到相关产品信息");
	      }
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
     <td width="60%" align="center" class="font_006699_bold_12">RF出库复核</td>
     <td width="20%" align="right"><a href="<%=request.getContextPath()%>/rf/main.jsp?warehouseid=<%=strWarehouseID%>">返回</a></td>
   </tr>
 </table>
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">托盘条码：</td>
     <td class="TD_ADD"><input type="text" name="traycode"  class="rf_input_long" maxlength="10"  onchange="disinfo()">  <font color="red">*</font>
        <input type="hidden" name="jobid"/> 
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">客户名：</td>
     <td class="TD_ADD"><input type="text" name="ClientName" class="rf_input_long" maxlength="10" disabled>  <font color="red">*</font>
      <input type="hidden" name="ClientID"/>
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">产品名称：</td>
     <td class="TD_ADD"><input type="text" name="productName" class="rf_input_long" maxlength="10" disabled>  <font color="red">*</font>
     <input type="hidden" name="productid"/>
     </td>
   </tr>
   <tr> 
     <td class="TD_ADD" align="right">生产日期：</td>
     <td class="TD_ADD"><input type="text" name="printdate"class="rf_input_long" maxlength="10" disabled> <font color="red">*</font></td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">进场编号：</td>
     <td class="TD_ADD"><input type="text" name="lotinfo" class="rf_input_long" maxlength="10" disabled>  <font color="red">*</font>
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">出库数量：</td>
     <td class="TD_ADD"><input type="text" name="outnum"  class="rf_input_long" maxlength="10" disabled> <font color="red">*</font>
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">分配数量：</td>
     <td class="TD_ADD"><input type="text" name="fpnum"  class="rf_input_long" maxlength="10" disabled><font color="red">*</font>
     </td>
   </tr>
   <tr>
     <td class="TD_ADD" align="center" colspan="2">
       <input type="button" name="add" class="BUTTON_STYLE1" value="复核" onClick="saveData();">
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
