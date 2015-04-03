<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%
	//设置重复提交的标志变量，begin为首次加载，finish为已提交
	session.setAttribute("submitFlag","begin");
	String strWarehouseID = (String)session.getAttribute("warehouseid");
	
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
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/getTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
<script type="text/javascript">

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
       var warehouseid = "<%=strWarehouseID %>"
	   if(warehouseid=null || warehouseid==""){
	       window.location.href = "<%=request.getContextPath()%>/rf/mesg.jsp";
	       return;
	   }
      	
		var Dstr = "";
		DWREngine.setAsync(false);
		getTool.GetInoutControl(mShow);
		DWREngine.setAsync(true);
		function mShow(value){
			Dstr = value;
		}
        
		if(Dstr == "2"){
			alert("卡控设置非可入库模式!");
			return;
		}
        
		var productCode = document.getElementById("productCode").value; //产品编码
		var warehouseidzz = document.getElementById("warehouseid").value;
		var whAreaId = document.getElementById("whAreaId").value; 		//库区
		var Virtualwhid = document.getElementById("Virtualwhid").value; //逻辑库区
		var intype = document.getElementById("intype").value;			//入库类型
		var productId = document.getElementById("productId").value; 	//品号
		var productName = document.getElementById("productName").value; //品名
		var punit = document.getElementById("punit").value; 			//单位
		var productstatus = document.getElementById("productstatus").value; //物品状态
		var lotinfo = document.getElementById("lotinfo").value;			//进厂编号
		var lotinfo2 = document.getElementById("lotinfo2").value;		//原厂编号
		var jobnum = document.getElementById("jobnum").value;			//数量
		var traycode = document.getElementById("traycode").value; 		//托盘条码
		var snumber = document.getElementById("snumber").value; 		//输送号
		var printdate = document.getElementById("printdate").value; 	//生产日期
		
		if(productCode == null || productCode.length < 1){
			alert("【产品编码】不能为空！");
			return;
		}
		if(whAreaId == null || whAreaId.length < 1){
			alert("该逻辑库区没有设置所属的【物理库区】！");
			return;
		}
		if(intype == null || intype.length < 1){
			alert("【入库类型】不能为空！");
			return;
		}else if(intype == "hl"){
			alert("回流作业，不在此操作，请点击返回！");
			return;
		}
		if(productName == null || productName.length < 1){
			alert("【品名】不能为空！");
			return;
		}
		if(productstatus == null || productstatus.length < 1){
			alert("【物品状态】不能为空！");
			return;
		}
		if(lotinfo == null || lotinfo.length < 1){
			alert("【进厂编号】不能为空！");
			return;
		}
/* 		if(lotinfo2 == null || lotinfo2.length < 1){
			alert("【原厂编号】不能为空！");
			return;
		}
		if(printdate == null || printdate.length < 1){
			alert("【生产日期】不能为空！");
			return;
		} */
		if(jobnum == null || jobnum.length <1 || !IsFloat(jobnum)){
			alert("【数量】不能为空！");
			return;
		}else if(jobnum<=0)
		{
			alert("数量需要大于0！");
			return false;
		}
		
		//验证输送机号
		if(snumber == null || snumber.length < 1){
			alert("【输送号】不能为空！");
			return;
		}else{
			var msg;
			DWREngine.setAsync(false);
			judgmentTool.isSnumberUse(snumber, Show);
			DWREngine.setAsync(true);
			function Show(value){
				msg = value;
			}
			if(msg != "Y"){
				alert(msg);
				return;
			}
		}
		
		//验证托盘
		if(traycode == null ||  traycode.length < 1)
		{
			alert("【托盘条码】不能为空！");
			return;
		}else{
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
		
		condition = "&warehouseid=" + warehouseidzz + "&whAreaId=" + whAreaId + "&intype=" + intype 
				+ "&productId=" + productId + "&productName=" + productName + "&punit=" + punit 
				+ "&productstatus=" + productstatus + "&lotinfo=" + lotinfo + "&lotinfo2=" + lotinfo2 + "&jobnum=" + jobnum 
				+ "&snumber=" + snumber	+ "&traycode=" + traycode + "&printdate=" + printdate 
				+"&productCode=" + productCode + "&Virtualwhid=" + Virtualwhid;

	    if(confirm("你确定入库吗？"))
		{		
			window.location.href = strUrl + "inBoundJobAction&method=ricoExecCreateCGJob&flag=cprk&rf=cprk&rfmainView=rfmainView"+ condition;
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
	       GetInfo();
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
		//selectObject("","snumber","workshop");
		selectAreaList("", "Virtualwhid", "<%=strWarehouseID %>", "3");
		selectType('9', 'intype', 'rklx');			//入库类型
		selectType('1', 'productstatus', 'wpzt');	//物品状态
		document.getElementById("warehouseid").value="<%=strWarehouseID %>";
	}

</script>
</head>

<body onload="onLoad();">
	<table id="tbb" border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_MGR_TOP">
		<tr>
			<td height="25" bgcolor="#d9e8fc" align="center" colspan="2">
				<table width="100%">
					<tr>
						<td width="20%"></td>
						<td width="60%" align="center" class="font_006699_bold_12">RF成品入库</td>
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
     	  	<select id="Virtualwhid" style="width:163px;" onchange="GetWhInfo();" ><option value=""></option></select><font color="red"> *</font>
     	  	</td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">入库类型：</td>
	      	<td class="TD_ADD"><select id="intype" style="width:163px;" disabled><option value=""></option></select><font color="red"> *</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">产品编码：</td>
			<td class="TD_ADD"><input type="text" name="productCode" class="rf_input_long" onchange="GetInfo();"><font color="red"> *</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">品名：</td>
			<td class="TD_ADD">
			<input type="hidden" name="productId" disabled>
			<input type="text" name="productName" class="rf_input_long"  disabled><input type="hidden" name="productid" /><font color="red"> *</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">单位：</td>
			<td class="TD_ADD"><input type="text" name="punit" class="rf_input_long"  disabled><font color="red"> *</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">进厂编号：</td>
			<td class="TD_ADD"><input type="text" name="lotinfo" class="rf_input_long" maxlength="10" ><font color="red"> *</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">原厂编号：</td>
			<td class="TD_ADD"><input type="text" name="lotinfo2" class="rf_input_long" maxlength="10" ></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">数量：</td>
			<td class="TD_ADD"><input type="text" name="jobnum" class="rf_input_long" maxlength="10" ><font color="red"> *</font></td>
		</tr>
		<tr>
		<td class="TD_ADD" align="right">物品状态：</td>
          <td class="TD_ADD">
              <select id="productstatus" style="width:163px;">
                <option value="">--请选择--</option>
              </select><font color="red"> *</font>
           </td>
           </tr>
        <tr>
			<td class="TD_ADD" align="right">输送号：</td>
			<td class="TD_ADD">
			  <input type="text" name="snumber" class="rf_input_long" maxlength="10" ><font color="red"> *</font>
			  <!--<select id="snumber" style="width:163px;"><option value="">--请选择--</option></select><font color="red"> *</font>-->
			</td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">托盘条码：</td>
			<td class="TD_ADD"><input type="text" name="traycode" class="rf_input_long" maxlength="10" ><font color="red"> *</font></td>
		</tr>
		<tr>
     	  <td class="TD_ADD" align="right">生产日期：</td>
	      <td class="TD_ADD">
	      	<input id="printdate" type="text" value="" onfocus="calendar();" class="Wdate" style="width:162px;">
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