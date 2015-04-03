<%@page import="com.ricosoft.common.tools.StrTools"%>
<%@ page contentType="text/html; charset=GBK"%>
<%
	String strTime =  StrTools.getCurrDateTime(8); 
	String warehouseid = request.getParameter("warehouseid");
%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css"rel="stylesheet" type="text/css" />
<script type='text/javascript'src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript'src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript'src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript"src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript">	
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = "";
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
	function OnOk() {
		var backmsg = null;
		var check_ids = list.document.getElementsByName("check_id");
		for ( var i = 0; i < check_ids.length; i++) {
			if (check_ids[i].checked) {
				backmsg = check_ids[i].value;
				break;
			}
		}
		if (backmsg == null || backmsg == "") {
			alert("请选择一条订单信息！");
		} else {
		    var ids = "";
		    var reserve2 ="";
		    var sotype ="";
			var check_ids = detail.document.getElementsByName("check_id");//订单详细id&外来单号&客户id&客户名&货位&品名&单位
			//var sotypes   = detail.document.getElementsByName("sotype");//SO 类型
			var checkNums = detail.document.getElementsByName("checkNum");//数量
			var k = 0;
			for(var i=0; i<check_ids.length; i++){
				if(check_ids[i].checked == true){
				    if(checkNums[i].value == null || (checkNums[i].value).length < 1 || !IsFloat(checkNums[i].value)){
						alert("【数量】不能为空 且 只能为数字！");
						return;
					}
					ids += check_ids[i].value + "&"+checkNums[i].value+"&"+ ",";
					reserve2=check_ids[i].alt;
					//sotype =sotypes[i].value;
					k++;
				}
			}
			//alert(sotype);
			if(ids == ""){
				alert("请选择一条入库订单明细！");
				return;
			}else{
				ids = ids.substring(0, ids.length-1);
			}
			if(sotype=="2"){
			 if(reserve2=="Y"){//如果是消耗型材料就把so类型变更为5.线边出库单
				sotype="5";
			 }
			}
			//ids=sotype+"/"+ids;
			window.returnValue =ids; //订单详细id&外来单号&客户id&客户名&货位&品名&单位&数量&批次属性1=值|批次属性2=值|批次属性3=值
    		window.close();
		}
	}
	//双击
	function ondbClickDo(strVar) {
		window.returnValue = strVar;
		window.close();
	}
	//查询
	function queryData() {
		var soid = document.getElementById("soid").value;
		var customerid = document.getElementById("customer_id").value;//客户id
		var createdate = document.getElementById("indate").value;//单据下载时间
		var sotype = document.getElementById("sotype").value;	//订单类型
		//每页显示行数
		var linerow = page.document.getElementById("lineviewrow").value;
		var condition = "&m_strCustomerId="+ customerid + "&m_strDownTime=" + createdate
					  + "&m_strSaleFormId=" + soid + "&m_strSaleFormType=" + sotype + "&linerow=" + linerow;
		list.location.href = ac + "SaleFormAction&flag=1" + condition;
	}
	function onLoad()
	{
		selectType('','sotype','ddlx');		//订单类型
	}
</script>
</head>
<body onload="onLoad();">
	<div class="con_bk">
		<table width="100%" height="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td>
					<table width="98%" align="center" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td height="25"><div class="font_001F56_bold_12">当前位置：选择出库订单</div>
							</td>
						</tr>
					</table>

					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0" class="table_add">
						<tr>
							<td class="yx1" width="100"><div align="right">客户名称：</div></td>
							<td class="yx">	
								<input type="hidden" name="customer_id"><input type="text" name="customer_name" size="15" readonly>
       							<input type="button" class="button_select" value="…" onClick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp','800','520');"></td>
						     <td class="yx1" width="100px" align="right">日期：</td>
	     					 <td class="xx1">
	      						<input id="indate" type="text" size="13" value="<%=strTime%>" onfocus="calendar();" class="Wdate"> 
						</tr>
						<tr>
							<td class="yx1" width="100"><div align="right">销售订单号：</div></td>
							<td class="yx">	
       							<input id="soid" type="text" value="" class=""></td>
						     <td class="yx1" width="100px" align="right">订单类型：</td>
	     					 <td class="xx1">
	      						<select id="sotype"><option>---请选择---</option></select></td>	 
						</tr>
						<tr>
							<td height="30" colspan="4" align="center" valign="bottom"><input type="button" value="查询" class="button_search" onClick="queryData()"> 
								<input type="button" value="确定" class="button_add" onClick="OnOk()"> 
								<input type="button" value="关闭" class="button_delete" onClick="window.close();" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="100%">
					<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td height="18" class="title">出库订单信息</td>
						</tr>
						<tr>
							<td height="180"><iframe id="list" name="list" width="100%"
									height="100%" frameborder="0" scrolling="yes"
									src="<%=request.getContextPath()%>/standard/jsp/outbound/so/outbound_so_list.jsp"></iframe>
							</td>
						</tr>
						<tr>
							<td height="25"><iframe id="page" name="page"
									src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=6&flag=N"
									width="100%" height="100%" scrolling="no" frameborder="0"></iframe>
							</td>
						</tr>
						<tr>
							<td valign="top" class="title" height="18">出库订单明细信息</td>
						</tr>
						<tr>
							<td><iframe id="detail" name="detail"
									width="100%" height="100%" frameborder="0" scrolling="yes"
									src="<%=request.getContextPath()%>/standard/jsp/outbound/so/outbound_so_detail.jsp"></iframe>
							</td>
						</tr>
					</table>
					 <!-- *******************************-->
				</td>
			</tr>
		</table>

	</div>

	<!-- 页面加载层（start） -->
	<div id="loader_container"
		style="position: absolute; display: none; z-index: 2; filter:alpha(opacity=75);">
		<div id="loader">
			<div align="center">页面正在加载 ...</div>
			<div id="loader_bg">
				<div id="progress"></div>
			</div>
		</div>
	</div>
	<!-- 页面加载层（end） -->

</body>
</html>
