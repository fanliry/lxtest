<%@ page contentType="text/html; charset=GBK"%>
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
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/getTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>

<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	var sp_flag = false;
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
		LockButton();
		
		//托盘条码
		var traycode = document.getElementById("tray_code").value;
		//仓库ID
		var warehouseid = document.getElementById("warehouseid").value;
		//库区ID
		var whAreaId = document.getElementById("whAreaId").value;
		//上架方式
		var putmode = document.getElementById("putmode").value;
		
		
		//if(whAreaId == null || whAreaId.length < 1){
		//	alert("【库区】不能为空！");
		//	UnLockButton();
		//	return;
		//}
		if(putmode == null || putmode.length < 1){
			alert("【上架方式】不能为空！");
			UnLockButton();
			return;
		}
		
		if(tray_code != null && tray_code.length>1 && tray_code.length != 8)
		{
			alert("【托盘条码】不为8位，Y-000000 ！");
			UnLockButton();
			return;
		}
		//验证托盘是否可用
		if(tray_code != null && tray_code.length>1)
		{
			var msg;
			DWREngine.setAsync(false);
			judgmentTool.isTrayCodeUse(tray_code, Show);
			DWREngine.setAsync(true);
			function Show(value){
				msg = value;
			}
			if(msg != "Y"){
				alert(msg);
				UnLockButton();
				return;
			}
		}
		
		
		
		//有中文
		var msg = "";
		var transrows = 0;	//有几行数据
		//表格
		var objtb = list.document.getElementById("puttb");
		for(var i=1; i<objtb.rows.length; i++){
			transrows = transrows + 1;
			//第1列
			var transid = objtb.rows.item(i).cells.item(0).getElementsByTagName("input")[0].value;
			//第3列
			var num = objtb.rows.item(i).cells.item(2).getElementsByTagName("input")[0].value;
			var netweight = objtb.rows.item(i).cells.item(2).getElementsByTagName("input")[1].value;
			var weiht = objtb.rows.item(i).cells.item(2).getElementsByTagName("input")[2].value;
			//第2列
			var lotatt1 = objtb.rows.item(i).cells.item(1).getElementsByTagName("input")[0].value;
			var lotatt2 = objtb.rows.item(i).cells.item(1).getElementsByTagName("input")[1].value;
			var lotatt3 = objtb.rows.item(i).cells.item(1).getElementsByTagName("input")[2].value;
			var lotatt4 = objtb.rows.item(i).cells.item(1).getElementsByTagName("input")[3].value;
			var lotatt5 = objtb.rows.item(i).cells.item(1).getElementsByTagName("input")[4].value;
			var lotatt6 = objtb.rows.item(i).cells.item(1).getElementsByTagName("input")[5].value;
			var lotatt7 = objtb.rows.item(i).cells.item(1).getElementsByTagName("input")[6].value;
			var lotatt8 = objtb.rows.item(i).cells.item(1).getElementsByTagName("input")[7].value;
			var lotatt9 = objtb.rows.item(i).cells.item(1).getElementsByTagName("input")[8].value;
			var lotatt10 = objtb.rows.item(i).cells.item(1).getElementsByTagName("input")[9].value;
			var lotatt11 = objtb.rows.item(i).cells.item(1).getElementsByTagName("input")[10].value;
			var lotatt12 = objtb.rows.item(i).cells.item(1).getElementsByTagName("input")[11].value;
			
			msg = msg + "<input type=hidden name='"+transrows+"transid' value='"+transid+"'>"
					+ "<input type=hidden name='"+transrows+"num' value='"+num+"'>"
					+ "<input type=hidden name='"+transrows+"netweight' value='"+netweight+"'>"
					+ "<input type=hidden name='"+transrows+"weiht' value='"+weiht+"'>"
					+ "<input type=hidden name='"+transrows+"volume' value='0'>"
					+ "<input type=hidden name='"+transrows+"lotatt1' value='"+lotatt1+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt2' value='"+lotatt2+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt3' value='"+lotatt3+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt4' value='"+lotatt4+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt5' value='"+lotatt5+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt6' value='"+lotatt6+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt7' value='"+lotatt7+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt8' value='"+lotatt8+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt9' value='"+lotatt9+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt10' value='"+lotatt10+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt11' value='"+lotatt11+"'>"
					+ "<input type=hidden name='"+transrows+"lotatt12' value='"+lotatt12+"'>";
		}

		//************************************************
		if(transrows > 0){
			if(confirm("你确定是否生成上架任务？"))
			{		
				var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=putawayAction";
	
				//有中文
				msg = msg + "<input type=hidden name='transrows' value='"+transrows+"'>";
				formx1.innerHTML = msg;
				formx1.action = strUrl + "&method=putaway&traycode=" + traycode + "&warehouseid=" + warehouseid + "&whAreaId=" + whAreaId + "&putmode=" + putmode;
				document.formx1.submit();
					
			}else{
				UnLockButton();
			}
		}else{
			alert("请增加需要上架的信息！");
			UnLockButton();
		}
	}
	
	
	
	
	
	
	
	
	
	//增加数据
	function addData()
	{
		var transid = myIframe.document.getElementById("transid").value;//收货记录号
		if(transid == null || transid.length < 1){
			alert("【收货记录】不能为空！");
			return;
		}else{
			var proudctname = myIframe.document.getElementById("product_name").value;//产品
			var packname = myIframe.document.getElementById("packname").value;//包装
			var unitname = myIframe.document.getElementById("eunit").value;//单位

			var knum = myIframe.document.getElementById("num").value;	 			//可上架数量
			var knetweight = myIframe.document.getElementById("netweight").value;	//可上架净重
			var kweight = myIframe.document.getElementById("weight").value;	 		//可上架毛重
			
			var num = myIframe.document.getElementById("renum").value;				//上架数量
			var netweight = myIframe.document.getElementById("renetweight").value;	//上架净重
			var weight = myIframe.document.getElementById("reweight").value;		//上架毛重
			
			//批次类型ID
			var lotid  = myIframe.document.getElementById("lotid").value;		
			var lotatt1  = myIframe.document.getElementById("lotatt1").value; 	//批次属性1
			var lotatt2  = myIframe.document.getElementById("lotatt2").value; 	//批次属性2
			var lotatt3  = myIframe.document.getElementById("lotatt3").value; 	//批次属性3
			var lotatt4  = myIframe.document.getElementById("lotatt4").value; 	//批次属性4
			var lotatt5  = myIframe.document.getElementById("lotatt5").value; 	//批次属性5
			var lotatt6  = myIframe.document.getElementById("lotatt6").value; 	//批次属性6
			var lotatt7  = myIframe.document.getElementById("lotatt7").value; 	//批次属性7
			var lotatt8  = myIframe.document.getElementById("lotatt8").value; 	//批次属性8
			var lotatt9  = myIframe.document.getElementById("lotatt9").value; 	//批次属性9
			var lotatt10  = myIframe.document.getElementById("lotatt10").value; //批次属性10
			var lotatt11  = myIframe.document.getElementById("lotatt11").value; //批次属性11
			var lotatt12  = myIframe.document.getElementById("lotatt12").value; //批次属性12
			
			//检查数量
			if(num == null || num.length < 1 || !IsFloat(num)){
				alert("【上架数量】不能为空且只能为数字！");
				return;
			}
			if(parseFloat(num) > parseFloat(knum)){
				alert("【上架数量(" + num + ")】不能大于收货记录的【可上架数量(" + knum + ")】！");
				return;
			}
			if(netweight != null && netweight.length > 0 && !IsFloat(netweight)){
				alert("【上架净重】只能为数字！");
				return;
			}else if(netweight == null || netweight.length < 1)
			{
				netweight = "0";
			}
			if(parseFloat(netweight) > parseFloat(knetweight)){
				alert("【上架净重(" + netweight + ")】不能大于收货记录的【可上架净重(" + knetweight + ")】！");
				return;
			}
			
			if(weight != null && weight.length > 0 && !IsFloat(weight)){
				alert("【上架毛重】只能为数字！");
				return;
			}else if(weight == null || weight.length < 1)
			{
				weight = "0";
			}
			if(parseFloat(weight) > parseFloat(kweight)){
				alert("【上架毛重(" + weight + ")】不能大于收货记录的【可上架毛重(" + kweight + ")】！");
				return;
			}
			//批次属性值验证
			if(myIframe.checkLotatt()==false){
				return;
			}
			
			//增加到表格中*******************************************
			//增加一行
			var objtb = list.document.getElementById("puttb");
			var newRow = null;	//行
			var newCell = null; //列
			
			//检查收货记录是否有重复
			for(var i=1; i < objtb.rows.length; i++)//表格的标题行不用判断
			{
				var rowtransid = objtb.rows.item(i).cells.item(0).getElementsByTagName("input")[0].value;
				if(transid == rowtransid){
					alert("【收货记录】已存在，请选删除相应收货记录再增加！");
					return;
				}
			}
			
			//增加一行
	        newRow = objtb.insertRow(objtb.rows.length);
	        newRow.setAttribute("onmouseover", function(){this.bgColor='#E2E8EA'});
			newRow.setAttribute("onmouseout", function(){this.bgColor=''});
			
			//跟踪号
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute( "className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+transid+"<input type='hidden' name='key' value='"+transid+"'></div>"; 
			//品名
	        newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+proudctname+"<input type='hidden' name='lot1' value='"+lotatt1+"'>"+
																   "<input type='hidden' name='lot2' value='"+lotatt2+"'>"+
																   "<input type='hidden' name='lot3' value='"+lotatt3+"'>"+
																   "<input type='hidden' name='lot4' value='"+lotatt4+"'>"+
																   "<input type='hidden' name='lot5' value='"+lotatt5+"'>"+
																   "<input type='hidden' name='lot6' value='"+lotatt6+"'>"+
																   "<input type='hidden' name='lot7' value='"+lotatt7+"'>"+
																   "<input type='hidden' name='lot8' value='"+lotatt8+"'>"+
																   "<input type='hidden' name='lot9' value='"+lotatt9+"'>"+
																   "<input type='hidden' name='lot10' value='"+lotatt10+"'>"+
																   "<input type='hidden' name='lot11' value='"+lotatt11+"'>"+
																   "<input type='hidden' name='lot12' value='"+lotatt12+"'></div>";
			
			//数量
			newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST");
			newCell.setAttribute( "align", "center");
			newCell.innerHTML = "<div align='center'>"+num+"<input type='hidden' name='nu' value='"+num+"'>"+
														   "<input type='hidden' name='netwei' value='"+netweight+"'>"+
														   "<input type='hidden' name='wei' value='"+weight+"'></div>";
			
			//删除
	        newCell = newRow.insertCell(newRow.cells.length);
			newCell.setAttribute("className", "TD_LIST2");
			newCell.setAttribute( "align", "center");
			newCell.setAttribute("onclick", function (){DeleteRow(newRow)});
			newCell.innerHTML = "<input type='button' name='details' class='BUTTON_STYLE1' value='删除'>";
		
		}	
	}
	//删除行
	function DeleteRow(obj)
	{
		var tb = list.document.getElementById("puttb");
		if(tb.rows.length > 1)
		{
			tb.deleteRow(obj.rowIndex);
		}
		else
		{
			alert("页面无数据记录！");
		}
 	}
	

	//显示库存信息
	function getInventory(){

		//单据ID
		var invoiceid = document.getElementById("inboundInvoice").value;
		if (invoiceid != null && invoiceid.length > 1) 
		{
			myIframe.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=rfReceiptAction&flag=2&invoiceid=" + invoiceid;
			
			list.location.href = "<%=request.getContextPath()%>/rf/putaway_detail.jsp";
			
		}else{
			alert("请选择收货单！");
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
		selectObject("<%=strWarehouseID%>", "inboundInvoice", "12");
		selectAreaList('', 'whAreaId', '<%=strWarehouseID%>', "1")// 库区
		//上架方式
		selectType('PL', 'putmode', 'putmode');
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
     <td width="60%" align="center" class="font_006699_bold_12">RF上架</td>
     <td width="20%" align="right"><a href="<%=request.getContextPath()%>/rf/main.jsp?warehouseid=<%=strWarehouseID%>">返回</a></td>
   </tr>
 </table>
     </td>
   </tr>
    <tr>  
     <td class="TD_ADD" align="right">收 货 单：</td>
     <td class="TD_ADD"><select id="inboundInvoice" class="rf_select" onchange="getInventory();"><option value="">--请选择单据--</option></select> <font color="red">*</font>
     </td>
   </tr>
   <tr> 
     <td class="TD_ADD" align="right">托盘条码：</td>
     <td class="TD_ADD"><input type="text" id="tray_code" value="" class="rf_input_long" maxlength="10"> </td>
   </tr>
   <tr>
     <td class="TD_ADD" align="right">上架库区：</td>
     <td class="TD_ADD"><input type="hidden" id="warehouseid" value="<%=strWarehouseID%>">
     		<select name="whAreaId"  style="width:162px;" >
	            <option>--请选择--</option>
	           </select> 
     </td>
   </tr>
   <tr>
     <td class="TD_ADD" align="right">上架方式：</td>
     <td class="TD_ADD">
     		<select name="putmode"  style="width:162px;" >
	            <option>--请选择--</option>
	           </select> <font color="red">*</font>
     </td>
   </tr>
   
   <tr>
	 <td class="TD_ADD" colspan="2" valign="top"  height="410">
	   <iframe id="myIframe" src="<%=request.getContextPath()%>/rf/putaway_list.jsp" frameborder="0" width="100%" height="100%" scrolling="no">
	   </iframe>
	 </td>
   </tr>
   <tr>
     <td align="center" height="10" colspan="2"></td>
   </tr>
   
   <tr>
     <td class="TD_ADD" align="center" colspan="2">
       <input name="button_add" type="button" class="button_add" id="button_add" value="&nbsp;&nbsp;增加信息" onClick="addData();"  />
            &nbsp;&nbsp;
       <input type="button" name="add" class="BUTTON_STYLE1" value="保存上架" onClick="saveData();">
     </td>
    </tr>
 </table>
 
  <table width="100%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <TR height="100%">
   	<td height="100%">
   		<iframe id="list" name="list" width="100%" height="100%"   frameborder="0"  scrolling="yes"
	               src="<%=request.getContextPath()%>/rf/putaway_detail.jsp">
   	</td>
   </TR>
 </table>
<FORM action="" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
