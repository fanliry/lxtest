<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundRequestInvoiceDetail" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundRequestInvoiceHeader" %>
<%
	
	InboundRequestInvoiceHeader invoice = (InboundRequestInvoiceHeader)request.getAttribute("invoice");
	InboundRequestInvoiceDetail invoicedetail = (InboundRequestInvoiceDetail)request.getAttribute("invoicedetail");
	String invoiceid="",requestvoicedetailid="",productName="",productid="",printdate="",lotinfo="",productStatus="";
	String plannum="",bandnum="";
	if(invoiceid!=null && invoicedetail!=null){
	    invoiceid = invoice.getInstockid(); //申请单号
		requestvoicedetailid = invoicedetail.getInstockdetailid(); //申请单明细
		plannum = invoicedetail.getPlannum()+""; //明细计划数量
		bandnum = invoicedetail.getBindingnum()+""; //明细绑定计划
		productName = invoicedetail.getProductName();	//产品名称
		productid = invoicedetail.getProductid();	//产品id
		printdate = invoicedetail.getPrintdate(); //生产日期
		lotinfo = invoicedetail.getLotinfo(); //批号
		productStatus=invoicedetail.getProductStatus();
	}
	
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
		//验证数量是否为托盘的上限
		
		if(isNum()){
			var invoiceid = document.getElementById("invoiceid").value; //申请单号
			var requestvoicedetailid = document.getElementById("requestvoicedetailid").value; //申请单明细
			var plannum = parseFloat(document.getElementById("plannum").value); //明细计划数量
			var bandnum = parseFloat(document.getElementById("bandnum").value); //明细绑定数量
			var productName = document.getElementById("productName").value;	//产品名称
			var productid = document.getElementById("productid").value;	//产品id
			var productStatus =  document.getElementById("productStatus").value;	//产品状态
			
			
			var printdate = document.getElementById("printdate").value; //生产日期
			var lotinfo = document.getElementById("lotinfo").value; //批号
			var traycode = document.getElementById("traycode").value; //托盘条码
			var num = parseFloat(document.getElementById("num").value);	//数量
	
			
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
			if(num == null && num.length < 1)
			{
				alert("【数量】不能为空或不为数字！");
				return;
			}
			if(num > plannum - bandnum)
			{
				alert("超过计划【数量】！");
				return;
			}
			//验证单据状态 看是否为审核或绑定状态
			var msg;
			DWREngine.setAsync(false);
			judgmentTool.isAuditAbind(invoiceid, Show);
			DWREngine.setAsync(true);
			function Show(value){
				msg = value;
			}
			if(msg != "Y"){
				alert(msg);
				return;
			}
			//验证托盘是否可用
			if(traycode != null && traycode.length>1)
			{
				var msg;
				DWREngine.setAsync(false);
				judgmentTool.isTrayCodeUse(traycode, Show);
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
			if(confirm("你确定保存？"))
			{		
				window.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=InBoundRequestAction&flag=RF1&instockid=" + invoiceid+"&requestvoicedetailid="+requestvoicedetailid+"&traycode="+traycode+"&num="+num;
					
			}
		}
		
	}
	function OnCancel() {
		document.all.over.style.display = "none";
		document.all.select.style.display = "none";
	}
	//选择一个申请单
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
			GetInfo();
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
	  var invoiceid = document.getElementById("invoiceid").value;
	  GetInfo();
	
	}
   	//获取没有关闭且没有作废的审核单
	function Getrequestvoiceid(){
   		
		   var warehouseid = "<%=strWarehouseID%>"
		   if(warehouseid==null || warehouseid==""){
		       alert("请重新登陆");
		       return;
		   }
		  
			getTool.Getrequestvoiceid("<%=strWarehouseID%>",Show);
			
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
						
						if(bem[0]==null){
							alert("无数据！");
							return;
						}
						document.getElementById("invoiceid").value = bem[0];
						
						GetInfo();
					}
					
					
				} else {
					alert("未查找到相应的申请单");
				}
			}
	}
	function GetInfo(){
	   var invoiceid = document.getElementById("invoiceid").value;
	   getTool.GetreqdetailByid(invoiceid,Show);
	   function Show(data){
	      if(data!=null && data.length > 0){
	          var aem = data.split(",");
	          if(aem!=null){
	             document.getElementById("requestvoicedetailid").value=aem[0];
		         document.getElementById("plannum").value=aem[1];
		         document.getElementById("bandnum").value=aem[2];
		         document.getElementById("productName").value=aem[3];
		         document.getElementById("printdate").value=aem[4];
		         document.getElementById("lotinfo").value=aem[5];
		         document.getElementById("productid").value=aem[6];
		         document.getElementById("productStatus").value=aem[7];
		         document.getElementById("num").value=aem[1]-aem[2];
		         
		         document.getElementById("requestvoicedetailid").disabled = true;
		         document.getElementById("plannum").disabled = true;
		         document.getElementById("bandnum").disabled = true;
		         document.getElementById("productName").disabled = true;
		         document.getElementById("printdate").disabled = true;
		         document.getElementById("lotinfo").disabled = true;
	          }
	          
	      }else{
	             document.getElementById("requestvoicedetailid").value="";
		         document.getElementById("plannum").value="";
		         document.getElementById("bandnum").value="";
		         document.getElementById("productName").value="";
		         document.getElementById("printdate").value="";
		         document.getElementById("lotinfo").value="";
		         document.getElementById("productid").value="";
		          document.getElementById("productStatus").value="";
		          
		         document.getElementById("requestvoicedetailid").disabled = true;
		         document.getElementById("plannum").disabled = true;
		         document.getElementById("bandnum").disabled = true;
		         document.getElementById("productName").disabled = true;
		         document.getElementById("printdate").disabled = true;
		         document.getElementById("lotinfo").disabled = true;
		         alert("没有找到相关产品信息");
	      }
	      
	   }
	   
	}
	function down(){
	   var invoiceid = document.getElementById("invoiceid").value;
	   var requestvoicedetailid = document.getElementById("requestvoicedetailid").value;
	   getTool.GetreqdetailByDownid(invoiceid,requestvoicedetailid,Show);
	   function Show(data){
	      if(data!=null && data!="" && data.length > 0){
	          var aem = data.split(",");
	          if(aem!=null){
	             document.getElementById("requestvoicedetailid").value=aem[0];
		         document.getElementById("plannum").value=aem[1];
		         document.getElementById("bandnum").value=aem[2];
		         document.getElementById("productName").value=aem[3];
		         document.getElementById("printdate").value=aem[4];
		         document.getElementById("lotinfo").value=aem[5];
		         document.getElementById("productid").value=aem[6];
		          document.getElementById("productStatus").value=aem[7];
		          document.getElementById("num").value=aem[1]-aem[2];
		          
		         document.getElementById("requestvoicedetailid").disabled = true;
		         document.getElementById("plannum").disabled = true;
		         document.getElementById("bandnum").disabled = true;
		         document.getElementById("productName").disabled = true;
		         document.getElementById("printdate").disabled = true;
		         document.getElementById("lotinfo").disabled = true;
	          }
	      }else{
	          alert("没有下条明细");
	      }   
	   }
	}
	function up(){
	   var invoiceid = document.getElementById("invoiceid").value;
	   var requestvoicedetailid = document.getElementById("requestvoicedetailid").value;
	   getTool.GetreqdetailByUpid(invoiceid,requestvoicedetailid,Show);
	   function Show(data){
	      if(data!=null && data!="" && data.length > 0){
	          var aem = data.split(",");
	          if(aem!=null){
	             document.getElementById("requestvoicedetailid").value=aem[0];
		         document.getElementById("plannum").value=aem[1];
		         document.getElementById("bandnum").value=aem[2];
		         document.getElementById("productName").value=aem[3];
		         document.getElementById("printdate").value=aem[4];
		         document.getElementById("lotinfo").value=aem[5];
		         document.getElementById("productid").value=aem[6];
		          document.getElementById("productStatus").value=aem[7];
		          document.getElementById("num").value=aem[1]-aem[2];
		         
		         document.getElementById("requestvoicedetailid").disabled = true;
		         document.getElementById("plannum").disabled = true;
		         document.getElementById("bandnum").disabled = true;
		         document.getElementById("productName").disabled = true;
		         document.getElementById("printdate").disabled = true;
		         document.getElementById("lotinfo").disabled = true;
	          }
	          
	      }else{
	          alert("没有上条明细");
	      }   
	   }
	}
	//检查数量是否为数字
	function IsNum(num){ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
	//检查数据是否为浮点数
	function IsFloat(ch){
		var re = /^\d+(\.\d+)?$/;
		return re.test(ch);
	}
	function IsRight(print_date){ 
		var str = /^(\d{4})-(\d{2})-(\d{2})$/;
		return(str.test(print_date));
	}
	function isNum(){
		//var traycode = document.getElementById("traycode").value ;//托盘条码
		var num = document.getElementById("num").value ;//数量
		var invoiceid = document.getElementById("invoiceid").value;
	    var requestvoicedetailid = document.getElementById("requestvoicedetailid").value;
		if(traycode == null ||  traycode.length < 1)
		{
			alert("【托盘条码】不能为空！");
			return;
		}
		if(num == null || num.length <1 || !IsFloat(num)){
			alert("【数量】不能为空或不为数字！");
			return;
		}else if(num<=0)
		{
			alert("数量需要大于0！");
			return false;
		}
		
		
		var msg;
		DWREngine.setAsync(false);
		judgmentTool.isNum(invoiceid,requestvoicedetailid,num,Show);
		DWREngine.setAsync(true);
		
		function Show(value){
			msg = value;
		}
		if(msg == "Y"){
			return true;
		}
		if(msg != "Y"){
			alert(msg);
			return false;
		}
		//----------------获取某个托盘应存放的数量范围--------------------------
		
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
     <td width="60%" align="center" class="font_006699_bold_12">RF绑定托盘</td>
     <td width="20%" align="right"><a href="<%=request.getContextPath()%>/rf/main.jsp?warehouseid=<%=strWarehouseID%>">返回</a></td>
   </tr>
 </table>
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">申请单号：</td>
     <td class="TD_ADD"><input type="text" name="invoiceid" value="<%=invoiceid!=null?invoiceid:""%>" class="rf_input_long" onchange="disinfo()">
         <input name="moreBtn" type="button" class="button_select" value="…" onclick="Getrequestvoiceid()" />
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">申请单明细：</td>
     <td class="TD_ADD"><input type="hidden" name="requestvoicedetailid" value="<%=requestvoicedetailid!=null?requestvoicedetailid:""%>"  onchange="GetInfo()"><input type="button" name="down" class="BUTTON_STYLE1" value="下条明细" onClick="down();">&nbsp;&nbsp;&nbsp;<input type="button" name="up" class="BUTTON_STYLE1" value="上条明细" onClick="up();">
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">明细计划数量：</td>
     <td class="TD_ADD"><input type="text" name="plannum"  value="<%=plannum!=null?plannum:""%>" class="rf_input_long" maxlength="10" disabled>  <font color="red">*</font>
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">明细绑定数量：</td>
     <td class="TD_ADD"><input type="text" name="bandnum"   value="<%=bandnum!=null?bandnum:""%>" class="rf_input_long" maxlength="10" disabled>  <font color="red">*</font>
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">产品名称：</td>
     <td class="TD_ADD"><input type="text" name="productName" value="<%=productName!=null?productName:""%>" class="rf_input_long" maxlength="10" disabled>  <font color="red">*</font>
     <input type="hidden" name="productid" value="<%=productid!=null?productid:""%>"/>
     <input type="hidden" name="productStatus" value="<%=productStatus!=null?productStatus:""%>"/>
     </td>
   </tr>
   <tr> 
     <td class="TD_ADD" align="right">生产日期：</td>
     <td class="TD_ADD"><input type="text" name="printdate" value="<%=printdate!=null?printdate:""%>" class="rf_input_long" maxlength="10" disabled> <font color="red">*</font></td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">批号：</td>
     <td class="TD_ADD"><input type="text" name="lotinfo" value="<%=lotinfo!=null?lotinfo:""%>" class="rf_input_long" maxlength="10" disabled>  <font color="red">*</font>
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">托盘条码：</td>
     <td class="TD_ADD"><input type="text" name="traycode"  class="rf_input_long" maxlength="10">  <font color="red">*</font>
     </td>
   </tr>
   <tr>  
     <td class="TD_ADD" align="right">数量：</td>
     <td class="TD_ADD"><input type="text" name="num"  class="rf_input_long" maxlength="10" >  <font color="red">*</font>
     </td>
   </tr>
   <tr>
     <td class="TD_ADD" align="center" colspan="2">
       <input type="button" name="add" class="BUTTON_STYLE1" value="绑定" onClick="saveData();">
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
