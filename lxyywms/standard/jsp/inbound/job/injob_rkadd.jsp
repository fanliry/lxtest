<%@page import="java.util.List"%>
<%@page import="com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail"%>
<%@page import="com.wms3.bms.standard.entity.job.lxyy.InoutJob"%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%
	//设置重复提交的标志变量，begin为首次加载，finish为已提交
	session.setAttribute("submitFlag","begin");
	String strWarehouseID = (String)request.getParameter("whid");
	
	if (strWarehouseID == null || strWarehouseID.equals("null")) {
		strWarehouseID = (String) session.getAttribute("warehouseid");
	}
	
	String strTime =  StrTools.getCurrDateTime(8); 
	
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
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript">

	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = "";
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
	
    //检查数据是否为浮点数
	function IsNum(ch){
		var re =  /^[0-9]*$/;
		return re.test(ch);
	}
    
	//**********************************************************
	//保存数据
	function saveData()
	{
		var warehouseidzz = document.getElementById("warehouseid").value;
		var whAreaId = document.getElementById("whAreaId").value; 		//库区
		var Virtualwhid = document.getElementById("Virtualwhid").value; //逻辑库区
		var productstatus = document.getElementById("productstatus").value; //物品状态
		var productId = document.getElementById("productId").value; 	//品号
		var productName = document.getElementById("productName").value; //品名
		var punit = document.getElementById("punit").value; 			//单位
		var productCode = document.getElementById("productCode").value; //产品编码
		var intype = document.getElementById("intype").value;			//入库类型
		var lotinfo = document.getElementById("lotinfo").value;			//进厂编号
		var jobnum = document.getElementById("jobnum").value;			//数量
		var traycode = document.getElementById("traycode").value; 		//托盘条码
		var printdate = document.getElementById("printdate").value; 	//生产日期
		var supplier = document.getElementById("customer_id").value; 	//供应商
		var platoon = document.getElementById("platoon").value;			//排
		var column = document.getElementById("column").value;			//列
		var floor = document.getElementById("floor").value;				//层
		
		if(warehouseidzz == null || warehouseidzz.length < 1){
			alert("【请返回重新登录】！");
			return;
		}
		//if(productstatus == null || productstatus.length < 1){
		//	alert("【物品状态】不能为空！");
		//	return;
		//}
		if(Virtualwhid == null || Virtualwhid.length < 1){
			alert("【逻辑库区】不能为空！");
			return;
		}
		if(lotinfo == null || lotinfo.length < 1){
			alert("【进厂编号】不能为空！");
			return;
		}
		if(intype == null || intype.length < 1){
			alert("【入库类型】不能为空！");
			return;
		}else if(intype == "hl"){
			alert("回流作业，不在此操作，请点击返回！");
			return;
		}
		/* if(supplier == null || supplier.length < 1){
			alert("【供应商】不能为空！");
			return;
		} */
		//DWREngine.setAsync(false);
		//judgmentTool.isLotInfoLength(lotid,lotinfo,Show1);
		//DWREngine.setAsync(true);
		//var msg1;
		
		//function Show1(value){
		//	msg1 = value;
		//}
		//if(msg1 != "Y"){
		//	alert(msg1);
		//	return false;
		//}
		if(printdate == null || printdate.length < 1){
			alert("【生产日期】不能为空！");
			return;
		}
		if(jobnum == null || jobnum.length <1 || !IsFloat(jobnum)){
			alert("【数量】不能为空！");
			return;
		}else if(jobnum<=0)
		{
			alert("数量需要大于0！");
			return false;
		}
		
		//验证托盘
		if(traycode == null ||  traycode.length < 1)
		{
			alert("【托盘条码】不能为空！");
			return;
		}else{
		//	var msg;
		//	DWREngine.setAsync(false);
		//	judgmentTool.isTrayCodeUse(traycode, Show);
		//	DWREngine.setAsync(true);
		//	function Show(value){
		//		msg = value;
		//	}
		//	if(msg != "Y"){
		//		alert(msg);
		//		return;
		//	}
		}
		
		if(platoon != null && !IsNum(platoon)){
			alert("【排】不能为非数字！");
			return;
		}
		if(column != null && !IsNum(column)){
			alert("【列】不能为非数字！");
			return;
		}
		if(floor != null && !IsNum(floor)){
			alert("【层】不能为非数字！");
			return;
		}
		
		//************************************************
		if(platoon!=null&&platoon.length>0&&column!=null&&column.length>0&&floor!=null&&floor.length>0){
			
			DWREngine.setAsync(false);
			judgmentTool.iswhAreaId(warehouseidzz, whAreaId, platoon, column, floor, Show2);
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
			judgmentTool.isCarspace(warehouseidzz,platoon,column, floor,Show1);
			DWREngine.setAsync(true);
			function Show1(value){
				msg1 = value;
			}
			if(msg1 != "Y"){
				alert(msg1);
				return;
			}else{ 
				
				condition = "&productstatus=" + productstatus + "&lotinfo=" + lotinfo + "&jobnum=" + jobnum 
						  + "&warehouseid=" + warehouseidzz + "&whAreaId=" + whAreaId +"&platoon=" + platoon +"&column=" + column +"&floor=" + floor 
						  + "&traycode=" + traycode + "&printdate=" + printdate +"&supplier=" + supplier +"&Virtualwhid=" + Virtualwhid 
						  + "&intype=" + intype + "&productCode=" + productCode + "&productId=" + productId + "&productName=" + productName 
						  + "&punit=" + punit ;

			    if(confirm("你确定修改吗？"))
				{
			    	window.returnValue = "inBoundJobAction&method=ricoExecRKAdd"+ condition;
			    	window.close();
				}
			}
		}
		
	}
	
	//获取产品信息
	function GetInfo(){
	   var productCode = document.getElementById("productCode").value;
	   getTool.getProductInfo(productCode,Show);
	   function Show(data){
	      if(data!=null && data.length > 0){
	          var aem = data.split("|");
	          if(aem!=null){
		         document.getElementById("productId").value=aem[0];
		         document.getElementById("productName").value=aem[1];
		         document.getElementById("punit").value=aem[2];
		         
		         document.getElementById("productName").disabled = true;
		         document.getElementById("punit").disabled = true;
	          }
	          
	      }else{
	             document.getElementById("productId").value="";
		         document.getElementById("productName").value="";
		         document.getElementById("punit").value="";
		         
		         document.getElementById("productName").disabled = true;
		         document.getElementById("punit").disabled = true;
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
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>"
		if(back_msg != "")
		{
			if(back_msg == "Y"){
				alert("操作成功！");
			}else{
				alert(back_msg);
			}
		}
		selectAreaList("", "Virtualwhid", "<%=strWarehouseID %>", "3");
		selectType('9', 'intype', 'rklx');			//入库类型
		selectType('', 'productstatus', 'wpzt');	//物品状态
	}

</script>
</head>

<body onload="onLoad();">
	<table id="tbb" border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_MGR_TOP">
		<tr>
			<td height="25" bgcolor="#d9e8fc" align="center" colspan="2">
				<table width="100%">
    				<tr><td>

					  <!-- ======== 当前位置 ======== -->
					  <div class="wz">
						<div id="dqwz" class="f_l">当前位置：<span>入库管理 &gt;&gt; 作业管理 &gt;&gt; 入库增加</span></div>
					  </div>
						
					</td></tr>
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
		<tr>
			<td class="TD_ADD" align="right">入库类型：</td>
	      	<td class="TD_ADD"><select id="intype" style="width:163px;" disabled><option value=""></option></select></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">托盘条码：</td>
			<td class="TD_ADD">
			<input type="text" name="traycode" class="rf_input_long" maxlength="10" > <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">产品编码：</td>
			<td class="TD_ADD"><input type="text" name="productCode" class="rf_input_long" onchange="GetInfo();" > <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">品名：</td>
			<td class="TD_ADD">
			<input type="hidden" name="productId">
			<input type="text" name="productName" class="rf_input_long" disabled > <font color="red">*</font> <input type="hidden" name="productid" /></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">单位：</td>
			<td class="TD_ADD"><input type="text" name="punit" class="rf_input_long"  disabled > <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">进场编号：</td>
			<td class="TD_ADD"><input type="text" name="lotinfo" class="rf_input_long" maxlength="10" > <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">数量：</td>
			<td class="TD_ADD"><input type="text" name="jobnum" class="rf_input_long" maxlength="10" > <font color="red">*</font></td>
		</tr>
		<tr>
		<td class="TD_ADD" align="right">物品状态：</td>
          <td class="TD_ADD">
              <select id="productstatus" style="width:163px;" >
                <option value="">--请选择--</option>
              </select>
           </td>
           </tr>
		<tr>
     	  <td class="TD_ADD" align="right">生产日期：</td>
	      <td class="TD_ADD">
	      	<input id="printdate" type="text" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:162px;"> <font color="red">*</font>
	      </td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">供应商：</td>
			<td class="TD_ADD">
			<input type="hidden" name="customer_id" ><input type="text" name="customer_name" readonly style="width:137px;" >
       		<input type="button" class="button_select" value="…" onClick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer_rf.jsp','330','500');">
       		 </td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">货位：</td>
	   	  	<td class="TD_ADD">
	   	    <input type="text" id="platoon" maxlength="2" style="width:38px;" >排
	   	    <input type="text" id="column" maxlength="2" style="width:38px;" >列
	   	    <input type="text" id="floor" maxlength="2" style="width:38px;" >层
	   	     <font color="red">*</font>
	   	  </td>
		</tr>
		<tr>
			<td class="TD_ADD" align="center" colspan="2"><input type="button" name="add" class="BUTTON_STYLE1" value="确认" onClick="saveData();"></td>
		</tr>
	</table>
	<div id="over" style="position: absolute; display: none; top: 0px; left: 0px; width: 100%; height: 100%; background-color: #efefef; z-index: 1; filter: alpha(opacity =     70);">
		<iframe style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; z-index: -1;"></iframe>
	</div>
	<div id="select" style="position: absolute; display: none; top: 0px; left: 0px; width: 100%; z-index: 2; background: #ffffff;"></div>
</body>
</html>
