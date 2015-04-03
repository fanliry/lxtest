<%@page import="java.util.List"%>
<%@page import="com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail"%>
<%@page import="com.wms3.bms.standard.entity.job.lxyy.InoutJob"%>
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
	
	String traycode = "";
	String jobid = "";
	String productid = "";
	String productname = "";
	String punit = "";
	String inLot = "";
	double num = 0;
	String productstatus = "";
	String productdate = "";
	String customerid = "";
	String customername = "";
	String jobtype = "";
	
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() >= 1)
	{
		Object[] obj = (Object[])ls.get(0);
		InoutJob job = (InoutJob)obj[0];
		InoutJobdetail jobD = (InoutJobdetail)obj[1];
		
		traycode = job.getTraycode();
		jobid = job.getJobid();
		productid = jobD.getProductcode();
		productname = jobD.getM_strProductName();
		punit = jobD.getM_strUnitName();
		inLot = jobD.getLotinfo();
		num = jobD.getJobnum();
		productstatus = jobD.getProductStatus();
		productdate = jobD.getPrintdate();
		customerid = jobD.getCustomerid();
		customername = jobD.getM_strCustomerName();
		jobtype = job.getJobtype();
		
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
        
		var productstatus = document.getElementById("productstatus").value; //物品状态
		var lotinfo = document.getElementById("lotinfo").value;			//具体批号
		var jobid = document.getElementById("jobid").value; 	
		var jobnum = document.getElementById("jobnum").value;			//数量
		var traycode = document.getElementById("traycode").value; 		//托盘条码
		var printdate = document.getElementById("printdate").value; 	//生产日期
		var customerid = document.getElementById("customer_id").value; 	//生产日期
		var jobtype = document.getElementById("jobtype").value; 	//作业类型
		
		//if(productstatus == null || productstatus.length < 1){
		//	alert("【物品状态】不能为空！");
		//	return;
		//}
		if(jobid == null || jobid.length < 1){
			alert("【未找到】作业号！");
			return;
		}
		if(jobtype == null || jobtype.length < 1){
			alert("【未找到】作业类型！");
			return;
		}else{
			if(jobtype==hl){
				alert("回流的作业不能维护！");
				return;
			}
		}
		if(lotinfo == null || lotinfo.length < 1){
			alert("【具体批号】不能为空！");
			return;
		}
		/* if(customerid == null || customerid.length < 1){
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
		
		condition = "&productstatus=" + productstatus + "&lotinfo=" + lotinfo + "&jobnum=" + jobnum + "&jobid=" + jobid 
				+ "&traycode=" + traycode + "&printdate=" + printdate +"&customerid=" + customerid;

	    if(confirm("你确定修改吗？"))
		{		
			window.location.href = strUrl + "InjobSafeguardAction&method=ricoExecEditJob&rfmainView=rfmainView&flag=rf"+ condition;
		}

		
	}
	
	function getInfo()
	{
		var traycode = document.getElementById("traycode").value
		if(traycode == null || traycode.length < 1)
		{
			alert("请输入托盘条码");
			return;
		}
		window.location.href = strUrl + "InjobSafeguardAction&flag=1&isWho=rf&traycode=" + traycode;
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
		selectType('<%=productstatus == null ? "":productstatus %>', 'productstatus', 'wpzt');	//物品状态
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
						<td width="60%" align="center" class="font_006699_bold_12">RF入库维护</td>
						<td width="20%" align="right"><a href="<%=request.getContextPath()%>/rf/main.jsp?warehouseid=<%=strWarehouseID%>">返回</a></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">托盘条码：</td>
			<td class="TD_ADD">
			<input type="hidden" name="jobid" value="<%=jobid %>">
			<input type="text" name="traycode" class="rf_input_long" maxlength="10" onchange="getInfo()" value="<%=traycode == null ? "":traycode %>"> <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">产品编码：</td>
			<td class="TD_ADD"><input type="text" name="productCode" class="rf_input_long" disabled value="<%=productid == null ? "":productid %>"> <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">品名：</td>
			<td class="TD_ADD">
			<input type="hidden" name="productId" disabled>
			<input type="text" name="productName" class="rf_input_long" disabled value="<%=productname == null ? "":productname %>"> <font color="red">*</font> <input type="hidden" name="productid" /></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">单位：</td>
			<td class="TD_ADD">
			<input type="hidden" name="jobtype" value="<%=jobtype %>">
			<input type="text" name="punit" class="rf_input_long"  disabled value="<%=punit == null ? "":punit %>"> <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">进场编号：</td>
			<td class="TD_ADD"><input type="text" name="lotinfo" class="rf_input_long" maxlength="10" value="<%=inLot == null ? "":inLot %>"> <font color="red">*</font></td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">数量：</td>
			<td class="TD_ADD"><input type="text" name="jobnum" class="rf_input_long" maxlength="10" value="<%=num %>"> <font color="red">*</font></td>
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
	      	<input id="printdate" type="text" onfocus="calendar();" class="Wdate" style="width:162px;" value="<%=productdate == null ? "":productdate %>"> <font color="red">*</font>
	      </td>
		</tr>
		<tr>
			<td class="TD_ADD" align="right">供应商：</td>
			<td class="TD_ADD">
			<input type="hidden" name="customer_id" value="<%=customerid == null ? "":customerid %>"><input type="text" name="customer_name" readonly style="width:137px;" value="<%=customername == null ? "":customername %>">
       		<input type="button" class="button_select" value="…" onClick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer_rf.jsp','330','500');">
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
