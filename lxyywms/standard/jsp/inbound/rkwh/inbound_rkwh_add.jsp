<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%
	String strTime =  StrTools.getCurrDateTime(8);
	String warehouseid = request.getParameter("warehouseid");
	String whAreaId = request.getParameter("whAreaId");
%>
<html>
<head>
<title>增加入库作业信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript">
<!--
	//增加作业
	function OnOk(){
	
		var warehouseid = document.getElementById("warehouseid").value;	//仓库
		var whAreaId = document.getElementById("whAreaId").value;		//库区
		var alleyId = document.getElementById("alleyId").value;			//巷道
		var cargospaceid = document.getElementById("cargospace_id").value;	//货位id
		var intype = document.getElementById("intype").value;			//入库类型
		var traycode = document.getElementById("traycode").value;		//托盘条码
		var indate = document.getElementById("indate").value;			//作业日期
		//var shift_id = document.getElementById("shift_id").value;		//班次
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("请选择【仓库】!");
			return;
		}
		if(whAreaId == null || whAreaId.length < 1){
			alert("请选择【库区】!");
			return;
		}
		if(cargospaceid == null || cargospaceid.length <1)
		{
			alert("请选择【货位】!");
			return;
		}
		if(intype == null || intype.length <1)
		{
			alert("【入库类型】不能为空!");
			return;
		}
		if(traycode == null || traycode.length <1)
		{
			alert("【托盘条码】不能为空!");
			return;
		}
		
		var tb = list.document.getElementById("tb");
		if(tb.rows.length == 2){
			alert("请添加作业明细信息!");
			return;
		}
		
		//"&shift_id=" + shift_id
		var jobinfo = "&warehouseid=" + warehouseid + "&whAreaId=" + whAreaId + "&alleyId=" + alleyId
			 + "&cargospaceid=" + cargospaceid + "&indate=" + indate + "&intype=" + intype + "&traycode=" + traycode;
		
		var details = list.document.getElementsByName("key");
		var jobdetails = "";
		for(var i=0; i<details.length; i++){
			jobdetails += details[i].value + ","
		}
		var msg = "<input type=hidden name='jobdetails' value='"+jobdetails+"'>"
		var array = new Array();	
		array[0] = jobinfo;				//作业信息
		array[1] = msg;					//作业明细信息
        window.returnValue = array;
		window.close();
		
	}
	
	//增加明细
	function addDetail(){
	
		var productid = document.getElementById("productid").value;			//品名id
		var productname = document.getElementById("product_name").value;	//品名
		var customerid = document.getElementById("customer_id").value;		//货主id
		var customername = document.getElementById("customer_name").value;	//品名
		var packid = document.getElementById("packid").value;				//包装id
		var packname = document.getElementById("packname").value;			//包装
		var objunit = document.getElementById("punit");						//单位
		var punit = objunit.value;											//单位id
		var punitname = objunit.options[objunit.options.selectedIndex].text;//单位
		var lotid = document.getElementById("lotid").value;  				// 批次id
		var lotatt1 = document.getElementById("lotatt1").value;  			// 批次属性1
     	var lotatt2 = document.getElementById("lotatt2").value;  			// 批次属性2
     	var lotatt3 = document.getElementById("lotatt3").value;  			// 批次属性3
     	var lotatt4 = document.getElementById("lotatt4").value;  			// 批次属性4
     	var lotatt5 = document.getElementById("lotatt5").value;  			// 批次属性5
     	var lotatt6 = document.getElementById("lotatt6").value;  			// 批次属性6
     	var lotatt7 = document.getElementById("lotatt7").value;  			// 批次属性7
     	var lotatt8 = document.getElementById("lotatt8").value;  			// 批次属性8
     	var lotatt9 = document.getElementById("lotatt9").value;  			// 批次属性9
     	var lotatt10 = document.getElementById("lotatt10").value;  			// 批次属性10
     	var lotatt11 = document.getElementById("lotatt11").value;  			// 批次属性11
     	var lotatt12 = document.getElementById("lotatt12").value;  			// 批次属性12
     	var jobnum = document.getElementById("jobnum").value;				// 数量
		var volume = document.getElementById("volume").value;         		// 体积
 		var weight = document.getElementById("weight").value;         		// 重量
		var netweight = document.getElementById("netweight").value;			// 净重
		
		if(productid == null || productid.length < 1){
			alert("请选择【品名】!");
			return;
		}
		
		if(jobnum != null && jobnum.length > 0 && jobnum != 0){
			if(!numChar(jobnum)){
				alert("【数量】只能为正整数或0！");
				return;
			}
		}
		
		if(volume != null && volume.length > 0 && volume != 0){
			if(!isDig(volume)){
				alert("【体积】只能为正浮点数或0！");
				return;
			}
		}
		
		if(weight != null && weight.length > 0 && weight != 0){
			if(!isDig(weight)){
				alert("【重量】只能为正浮点数或0！");
				return;
			}
		}
		
		if(netweight != null && netweight.length > 0 && netweight != 0){
			if(!isDig(netweight)){
				alert("【净重】只能为正浮点数或0！");
				return;
			}
		}
		
		//批次属性值验证
		if(checkLotatt()==false){
			return;
		}
		
		var tb = list.document.getElementById("tb");
		var key = productid + "|" + customerid + "|" + packid + "|" + punit + "|" + jobnum + "|" + volume + "|" + weight + "|" + netweight + "|" 
			   + lotatt1 + "|" + lotatt2 + "|" + lotatt3 + "|" + lotatt4 + "|" + lotatt5 + "|" + lotatt6 + "|" + lotatt7 + "|"
			   + lotatt8 + "|" + lotatt9 + "|" + lotatt10 + "|" + lotatt11 + "|" + lotatt12 + "|" + lotid;
		
		
		//增加一行
		var newRow = null;	//行
		newRow = tb.insertRow(tb.rows.length-1);
		newRow.setAttribute("onmouseover", function(){this.bgColor='#e1f3ff'});
		newRow.setAttribute("onmouseout", function(){this.bgColor=''});
   		
		//品名
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = productname;
    	
    	//货主
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = customername;
    	
    	//包装
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = packname;
    	
    	//单位
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = punitname;
    	
    	//批次属性
		newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = lotatt1;
    	
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = lotatt2;
    	
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = lotatt3;
    	
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = lotatt4;
    	
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = lotatt5;
    	
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = lotatt6;
    	
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = lotatt7;
    	
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = lotatt8;
    	
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = lotatt9;
    	
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = lotatt10;
    	
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = lotatt11;
    	
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = lotatt12;
    	
    	// 数量
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = jobnum;

		// 体积
		newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = volume;
    	
    	// 重量
 		newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = weight;
    	
		// 净重
    	newCell = newRow.insertCell(newRow.cells.length);
    	newCell.setAttribute( "className", "TD_LIST");
    	newCell.setAttribute( "align", "center");
    	newCell.innerHTML = netweight;
    	
    	//删除
        newCell = newRow.insertCell(newRow.cells.length);
		newCell.setAttribute("className", "TD_LIST2");
		newCell.setAttribute( "align", "center");
		newCell.innerHTML = "<input type='button' id='details' class='BUTTON_STYLE1' value='删除' onclick='this.parentElement.parentElement.removeNode(true);'>"
			+ "<input type='hidden' name='key' value='" + key +"'>";
	}
	
	function OnLoad(){
	
		//同步
		DWREngine.setAsync(false);
		selectObject("<%=warehouseid%>", "warehouseid", "1");
		DWREngine.setAsync(true);
		
		var warehouseid = document.getElementById("warehouseid").value;
		DWREngine.setAsync(false);
		selectAreaList("<%=whAreaId%>", "whAreaId", warehouseid, "1");
		DWREngine.setAsync(true);
		
		var whAreaId = document.getElementById("whAreaId").value;
		selectAlleyList("", "alleyId", whAreaId);
		
		selectType('', 'intype', 'rklx');	//入库类型
		selectType('', 'punit', 'punit');	//单位
	}
	
	//根据仓库获得库区的列表
	function getWhAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		selectAlleyList("", "alleyId", "");
	}
	
	//根据库区获得巷道的列表
	function getAlleyList(){
	
		var whAreaId = document.getElementById("whAreaId").value;
		selectAlleyList("", "alleyId", whAreaId);
	}

-->
</script>
</head>

<body onload="OnLoad();">

  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">当前位置：入库管理 -&gt; 入库维护 -&gt; 新增作业信息</div></td>
    </tr>
  </table>
  
  <!---------- 作业信息 ------------>
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right"><span class="red">*</span>仓库：</td>
      <td class="yx"><select id="warehouseid" onChange="getWhAreaList()" style="width:117px;"><option value=""></option></select></td>
      <td width="100px" class="yx1" align="right"><span class="red">*</span>库区：</td>
      <td class="yx"><select id="whAreaId" onChange="getAlleyList()" style="width:117px;"><option value=""></option></select></td>
      <td width="100px" class="yx1" align="right">巷道：</td>
	  <td class="yx"><select id="alleyId" style="width:117px;"><option value="">--请选择--</option></select></td>
	  <td width="100px" class="yx1" align="right"><span class="red">*</span>货位：</td>
	  <td class="xx1">
		<input type="hidden" id="cargospace_id"><input type="text" id="cargospace_name" size="14" readonly>
       	<input onClick="openCargospace('<%=request.getContextPath()%>/standard/jsp/common/common_cargospace.jsp?warehouseid=<%=warehouseid%>&whAreaId='+document.getElementById('whAreaId').value,'850','550');" 
       		type="button" value="…" class="button_select">
      </td>
   </tr>    
   <tr>
     <td class="y1" align="right">作业日期：</td>
	 <td class="x"><input id="indate" type="text" size="15" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:117px;"></td>
     <td class="y1" width="100px" align="right"><span class="red">*</span>入库类型：</td>
	 <td class="x"><select id="intype" style="width:117px;"><option value=""></option></select></td>
     <td class="y1" align="right"><span class="red">*</span>托盘条码：</td>
	 <td colspan="3"><input id="traycode" type="text" size="15"></td>
   </tr> 
   </table>
   
   <!---------- 作业详细信息 ------------>
   <form name="myForm" method="post" action="">
   <table width="98%" border="0" align="center" cellpadding="0" cellspacing="5">
   <tr><td align="right">
        <input type="button" onclick="addDetail()" value="&nbsp;&nbsp;&nbsp;增加明细" class="button_add">
	    <input type="reset" id="resetDetailBtn" value="&nbsp;&nbsp;&nbsp;重置明细" class="button_reset">&nbsp;&nbsp;&nbsp;
      	<input type="button" onclick="OnOk()" name="add" value="&nbsp;&nbsp;&nbsp;增加作业" class="button_add"> 
       	<input type="button" onClick="window.close()" name="resetBtn" value="关闭" class="BUTTON_STYLE1">
   </td></tr>
   <tr><td class="title">作业明细信息</td></tr>
   <tr><td>
     <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
     <tr>
       <td width="100px" class="yx1" align="right"><span class="red">*</span>品名：</td>
       <td class="yx">
         <input type="hidden" name="productid"><input type="text" name="product_name" size="15" readonly class="input_readonly">
         <input onclick="openProductPacke('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520',
         	'productid','product_name', 'packid', 'packname', '1','punit', 'lotid');"  type="button" value="…" class="button_select">
       </td>
       <td width="100px" class="yx1" align="right">货主：</td>
       <td class="yx">
         <input id="customer_id" type="hidden"><input id="customer_name" type="text" size="15" readonly class="input_readonly">
         <input onClick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp','700','450');" 
        	type="button" value="…" class="button_select">
       </td>
       <td width="100px" class="yx1" align="right">包装：</td>
       <td class="yx"><input type="hidden" id="packid"/><input type="text" id="packname" class="input_readonly" readonly size="15" /></td>
       <td class="yx1" align="right">单位：</td>
	   <td class="xx1"><select id="punit" style="width:117px;" ><option>--请选择--</option></select></td>
     </tr>    
     <tr>
       <td class="yx1" align="right">数量：</td>
	   <td class="yx"><input id="jobnum" type="text" size="15" value="0"></td>
	   <td class="yx1" align="right">体积：</td>
	   <td class="yx"><input id="volume" type="text" size="15" value="0.0"></td>
       <td class="yx1" align="right">毛重：</td>
	   <td class="yx"><input id="weight" type="text" size="15" value="0.0"></td>
       <td class="yx1" align="right">净重：</td>
	   <td class="xx1"><input id="netweight" type="text" size="15" value="0.0"></td>
     </tr> 
     <tr>
     </tr> 
     <tr><td height="5" colspan="6"><input type="hidden" name="lotid"/></td></tr>
     <tr>
      <td align="right" class="yx1_top"><div id="lotatt001" align="right">批次属性1：</div></td>
      <td class="yx_top"><div id="lotvalue001" align="left">
      	<input type="text" name="lotatt1" size="16" /></div>
      </td>
      <td align="right" class="yx1_top"><div id="lotatt002" align="right">批次属性2：</div></td>
      <td class="yx_top"><div id="lotvalue002" align="left">
      	<input type="text" name="lotatt2" size="16"/></div>  
       </td>
      <td align="right" class="yx1_top"><div id="lotatt003" align="right">批次属性3：</div></td>
      <td class="yx_top" ><div id="lotvalue003" align="left">
      	<input type="text" name="lotatt3" size="16"/></div>
      </td>
      <td align="right" class="yx1_top"><div id="lotatt004" align="right">批次属性4：</div></td>
      <td class="xx1_top"><div id="lotvalue004" align="left">
      	<input type="text" name="lotatt4" size="16"  /></div>
      </td>
    </tr>
    <tr>
      <td align="right" class="yx1"><div id="lotatt005" align="right">批次属性5：</div></td>
      <td class="yx"><div id="lotvalue005" align="left">
      	<input type="text" name="lotatt5" size="16"/></div>  
       </td>
      <td align="right" class="yx1"><div id="lotatt006" align="right">批次属性6：</div></td>
      <td class="yx" ><div id="lotvalue006" align="left">
      	<input type="text" name="lotatt6" size="16"/></div>
      </td>
      <td align="right" class="yx1"><div id="lotatt007" align="right">批次属性7：</div></td>
      <td class="yx"><div id="lotvalue007" align="left">
      	<input type="text" name="lotatt7" size="16"  /></div>
      </td>
      <td align="right" class="yx1"><div id="lotatt008" align="right">批次属性8：</div></td>
      <td class="xx1"><div id="lotvalue008" align="left">
      	<input type="text" name="lotatt8" size="16"/></div> 
       </td>
    </tr>
    <tr>
      <td align="right" class="y1"><div id="lotatt009" align="right">批次属性9：</div></td>
      <td class="x" ><div id="lotvalue009" align="left">
      	<input type="text" name="lotatt9" size="16"/></div>
      </td>
      <td align="right" class="y1"><div id="lotatt010" align="right">批次属性10：</div></td>
      <td class="x"><div id="lotvalue010" align="left">
      	<input type="text" name="lotatt10" size="16"  /></div>
      </td>
      <td align="right" class="y1"><div id="lotatt011" align="right">批次属性11：</div></td>
      <td class="x"><div id="lotvalue011" align="left">
      	<input type="text" name="lotatt11" size="16"/></div>  
       </td>
      <td align="right" class="y1"><div id="lotatt012" align="right">批次属性12：</div></td>
      <td><div id="lotvalue012" align="left">
      	<input type="text" name="lotatt12" size="16"/></div>
      </td>
    </tr>
   </table>
   </td></tr>
   <tr><td>

	 <!-- ======== 明细列表开始 ======== -->
	 <table width="100%" height="270" border="0" align="center" cellpadding="0" cellspacing="0">
	   <tr>
	      <td align="right">
	      	
	      </td>
	    </tr>
	    <tr>
		  <td>
		    <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/inbound/rkwh/inbound_rkwh_add_list.jsp" 
		   		scrolling="auto" frameborder="0" width="100%" height="100%">
		    </iframe>
		  </td>
	   </tr>
	 </table>
	 <!-- ======== 明细列表结束 ======== -->
   </td></tr>
</table>  
</form>
   
</body>
</html>