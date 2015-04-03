<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%
	HashMap<String, List> hsLot = null;	//所有要显示的批次
	List<BaseLotSet> lsLot = null;  	//显示的批次属性列表
	int iLine = 0;	//显示的批次属性个数
	if(request.getSession(false).getAttribute("viewLot") != null){
		hsLot = (HashMap)request.getSession(false).getAttribute("viewLot");
		if(hsLot != null){
			lsLot = hsLot.get("kcstatistic");//库存统计项目
			iLine = lsLot.size();
		}
	}

%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
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
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var outinvoiceid = "";
	var condition = null;
	
	//检查数量是否为数字
	function IsNum(num){ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
	//报表
	function report(){
		if(condition == null){
			alert("请先查询！");
			return;
		}
				
		var name="kc_statistic";
		document.tempForm.innerHTML = getPostCon();
		document.tempForm.action = strUrl + "inventoryAction&flag=4" + condition;
		document.tempForm.target=name;
		document.tempForm.attachEvent("onsubmit",function(){ openWindow(name); });
		document.tempForm.fireEvent("onsubmit");	
		document.tempForm.submit();	
	}
	
	function openWindow(name)  
    {  
      window.open('about:blank',name,'height=800, width=1200, top=0, left=0, toolbar=yes, menubar=yes, scrollbars=yes, resizable=yes,location=yes, status=yes');   
    }
	
	//查询
	function searchData(){
	
		var warehouseid = document.getElementById("warehouseid").value;	//仓库
		var whAreaId = document.getElementById("whAreaId").value;		//库区
		var customer_id = document.getElementById("customer_id").value;	//货主
		var package_id = document.getElementById("package_id").value;	//品名
		var indate_from = document.getElementById("indate_from").value;	//入库日期from
		var indate_to = document.getElementById("indate_to").value;		//入库日期to
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("请先选择【所属仓库】!");
			return;
		}
						
		condition = "&warehouseid=" +　warehouseid + "&whAreaId=" +　whAreaId + "&customer_id=" + customer_id + "&package_id=" +　package_id
			 + "&indate_from=" +　indate_from + "&indate_to=" +　indate_to;
			 
		//每页显示行数
		var linerow = page.document.getElementById("lineviewrow").value;
		condition += "&linerow=" + linerow;
					
		list.formx1.innerHTML = getPostCon();
		list.formx1.action = strUrl + "inventoryAction&flag=3" + condition;
		list.document.formx1.submit();
		
		Loading();	
	}
	
	//获得用post方法提交的参数，包括要统计的项目，批次属性的查询条件
	function getPostCon(){
	
		//统计条件
		var total_items = "";
		if(document.getElementById("chk_product").checked){
			total_items += ", sto.productid";
		}
		if(document.getElementById("chk_owner").checked){
			total_items += ", sto.ownerId";
		}
		var lotatt;
		for(var i=1; i<13; i++){
			lotatt = document.getElementById("chk_lotatt" + i);
			if(lotatt!=null && lotatt.checked){
				total_items += ", sto.lotatt" + i;
			}
		}

		var lotid = document.getElementById("lotid").value
		//批次属性值，如果是日期范围的，格式如下：2012-08-30|2012-08-31
		var lotatt1 = ""; //批次属性1
		var lotatt2 = ""; //批次属性2
		var lotatt3 = ""; //批次属性3
		var lotatt4 = ""; //批次属性4
		var lotatt5 = ""; //批次属性5
		var lotatt6 = ""; //批次属性6
		var lotatt7 = ""; //批次属性7
		var lotatt8 = ""; //批次属性8
		var lotatt9 = ""; //批次属性9
		var lotatt10 = "";//批次属性10
		var lotatt11 = "";//批次属性11
		var lotatt12 = "";//批次属性12
		//属性  0：表示禁用的，1：表示精确或糊涂查询的 2：表示日期要取范围的
		var lt1 = document.getElementById("lt1").value; //批次1的属性
		if(lt1 != null && lt1.length >0){
			if(lt1 == "0"){
				lotatt1 = "";
			}else if(lt1 == "2"){
				lotatt1 = document.getElementById("lotatt1_from").value + "|" + document.getElementById("lotatt1_to").value;
			}else{
				lotatt1 = document.getElementById("lotatt1").value;
			}	
		}else{ //没有值
			lotatt1 = "";
		}
		
		var lt2 = document.getElementById("lt2").value; //批次2的属性
		if(lt2 != null && lt2.length >0){
			if(lt2 == "0"){
				lotatt2 = "";
			}else if(lt2 == "2"){
				lotatt2 = document.getElementById("lotatt2_from").value + "|" + document.getElementById("lotatt2_to").value;
			}else{
				lotatt2 = document.getElementById("lotatt2").value;
			}	
		}else{ //没有值
			lotatt2 = "";
		}
		
		var lt3 = document.getElementById("lt3").value; //批次3的属性
		if(lt3 != null && lt3.length >0){
			if(lt3 == "0"){
				lotatt3 = "";
			}else if(lt3 == "2"){
				lotatt3 = document.getElementById("lotatt3_from").value + "|" + document.getElementById("lotatt3_to").value;
			}else{
				lotatt3 = document.getElementById("lotatt3").value;
			}	
		}else{ //没有值
			lotatt3 = "";
		}
		
		var lt4 = document.getElementById("lt4").value; //批次4的属性
		if(lt4 != null && lt4.length >0){
			if(lt4 == "0"){
				lotatt4 = "";
			}else if(lt4 == "2"){
				lotatt4 = document.getElementById("lotatt4_from").value + "|" + document.getElementById("lotatt4_to").value;
			}else{
				lotatt4 = document.getElementById("lotatt4").value;
			}	
		}else{ //没有值
			lotatt4 = "";
		}
		
		var lt5 = document.getElementById("lt5").value; //批次5的属性
		if(lt5 != null && lt5.length >0){
			if(lt5 == "0"){
				lotatt5 = "";
			}else if(lt5 == "2"){
				lotatt5 = document.getElementById("lotatt5_from").value + "|" + document.getElementById("lotatt5_to").value;
			}else{
				lotatt5 = document.getElementById("lotatt5").value;
			}	
		}else{ //没有值
			lotatt5 = "";
		}
		
		var lt6 = document.getElementById("lt6").value; //批次6的属性
		if(lt6 != null && lt6.length >0){
			if(lt6 == "0"){
				lotatt6 = "";
			}else if(lt6 == "2"){
				lotatt6 = document.getElementById("lotatt6_from").value + "|" + document.getElementById("lotatt6_to").value;
			}else{
				lotatt6 = document.getElementById("lotatt6").value;
			}	
		}else{ //没有值
			lotatt6 = "";
		}
		
		var lt7 = document.getElementById("lt7").value; //批次7的属性
		if(lt7 != null && lt7.length >0){
			if(lt7 == "0"){
				lotatt7 = "";
			}else if(lt7 == "2"){
				lotatt7 = document.getElementById("lotatt7_from").value + "|" + document.getElementById("lotatt7_to").value;
			}else{
				lotatt7 = document.getElementById("lotatt7").value;
			}	
		}else{ //没有值
			lotatt7 = "";
		}
		
		var lt8 = document.getElementById("lt8").value; //批次8的属性
		if(lt8 != null && lt8.length >0){
			if(lt8 == "0"){
				lotatt8 = "";
			}else if(lt8 == "2"){
				lotatt8 = document.getElementById("lotatt8_from").value + "|" + document.getElementById("lotatt8_to").value;
			}else{
				lotatt8 = document.getElementById("lotatt8").value;
			}	
		}else{ //没有值
			lotatt8 = "";
		}
		
		var lt9 = document.getElementById("lt9").value; //批次9的属性
		if(lt9 != null && lt9.length >0){
			if(lt9 == "0"){
				lotatt9 = "";
			}else if(lt9 == "2"){
				lotatt9 = document.getElementById("lotatt9_from").value + "|" + document.getElementById("lotatt9_to").value;
			}else{
				lotatt9 = document.getElementById("lotatt9").value;
			}	
		}else{ //没有值
			lotatt9 = "";
		}
		
		var lt10 = document.getElementById("lt10").value; //批次10的属性
		if(lt10 != null && lt10.length >0){
			if(lt10 == "0"){
				lotatt10 = "";
			}else if(lt10 == "2"){
				lotatt10 = document.getElementById("lotatt10_from").value + "|" + document.getElementById("lotatt10_to").value;
			}else{
				lotatt10 = document.getElementById("lotatt10").value;
			}	
		}else{ //没有值
			lotatt10 = "";
		}
		
		var lt11 = document.getElementById("lt11").value; //批次11的属性
		if(lt11 != null && lt11.length >0){
			if(lt11 == "0"){
				lotatt11 = "";
			}else if(lt11 == "2"){
				lotatt11 = document.getElementById("lotatt11_from").value + "|" + document.getElementById("lotatt11_to").value;
			}else{
				lotatt11 = document.getElementById("lotatt11").value;
			}	
		}else{ //没有值
			lotatt11 = "";
		}
		
		var lt12 = document.getElementById("lt12").value; //批次12的属性
		if(lt12 != null && lt12.length >0){
			if(lt12 == "0"){
				lotatt12 = "";
			}else if(lt12 == "2"){
				lotatt12 = document.getElementById("lotatt12_from").value + "|" + document.getElementById("lotatt12_to").value;
			}else{
				lotatt12 = document.getElementById("lotatt12").value;
			}	
		}else{ //没有值
			lotatt12 = "";
		}

		//有中文
		var msg = "<input type=hidden name='lotatt1' value='"+lotatt1+"'>"
					+ "<input type=hidden name='lotatt2' value='"+lotatt2+"'>"
					+ "<input type=hidden name='lotatt3' value='"+lotatt3+"'>"
					+ "<input type=hidden name='lotatt4' value='"+lotatt4+"'>"
					+ "<input type=hidden name='lotatt5' value='"+lotatt5+"'>"
					+ "<input type=hidden name='lotatt6' value='"+lotatt6+"'>"
					+ "<input type=hidden name='lotatt7' value='"+lotatt7+"'>"
					+ "<input type=hidden name='lotatt8' value='"+lotatt8+"'>"
					+ "<input type=hidden name='lotatt9' value='"+lotatt9+"'>"
					+ "<input type=hidden name='lotatt10' value='"+lotatt10+"'>"
					+ "<input type=hidden name='lotatt11' value='"+lotatt11+"'>"
					+ "<input type=hidden name='lotatt12' value='"+lotatt12+"'>"
					+ "<input type=hidden name='lotid' value='"+lotid+"'>"
					+ "<input type=hidden name='total_items' value='"+total_items+"'>";
					
		return msg;
	}
	
	//页面登录
	function OnLoad(){
		
		//仓库
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");	
		DWREngine.setAsync(true);
		
		//库区
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		
		//批次属性
		selectLot("", "lotid");
	}
	
	//根据仓库获得库区的列表
	function getWhAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
	}
	
	//选择批次后，显示该批次的明细属性
	function viewLot(){
	
		var tableObj = document.getElementById("marginTable");
		var lotid = document.getElementById("lotid").value;
		
		//2是查询条件的行数, 4是每行显示的td数
		createLotDetail(tableObj, lotid, 3, 4);
		
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
 <input type="hidden" id="condition">
 
<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== 当前位置 ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">当前位置：<span>库存管理 &gt;&gt; 库存统计</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
		    <li class="tubiao2"><a href="#" onClick="report()">报表</a></li>
			<li class="tubiao1"><a href="#" onclick="searchData()">查询</a></li>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== 查询条件 ======== -->
      <table id="marginTable" width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
        <tr>
	   	  <td class="yx1" align="right">统计条件：</td>
	      <td class="yx" colspan="7">
	        <input type="checkbox" id="chk_product">品名
	        <input type="checkbox" id="chk_owner">货主
<%
	BaseLotSet lotSet = null;
	if(lsLot != null){
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);			
%>
	 		<input type="checkbox" id="chk_<%=lotSet.getLotid()%>"><%=lotSet.getLotname()%>
<%
		}
	}
%> 
		  </td>
 	 	</tr>
	    <tr>
	      <td class="yx1" width="100" align="right">仓库：</td>
	      <td class="yx"><select name="warehouseid" onChange="getWhAreaList()" style="width:117px;"><option value=""></option></select></td>
	      <td class="yx1" width="100" align="right">库区：</td>
     	  <td class="yx"><select id="whAreaId" onChange="getAlleyList()" style="width:117px;"><option value=""></option></select></td>
     	  <td class="yx1" width="100" align="right">货主：</td>
	      <td class="yx">
	      	<input type="hidden" name="customer_id"><input type="text" name="customer_name" size="15" readonly>
       		<input type="button" class="button_select" value="…" onClick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp','800','520');">
	      </td>
	      <td class="yx1" width="100" align="right">品名：</td>
	      <td class="xx1">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name" size="15" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="…" class="button_select">
	      </td>
	    </tr>
	    <tr>
	      <td class="yx1" width="100" align="right">入库日期：</td>
     	  <td class="yx">
     	  	<input id="indate_from" type="text" onFocus="calendar()" class="Wdate" style="height:21px;width:100px;">-<input id="indate_to" type="text" onfocus="calendar();" class="Wdate" style="height:21px;width:100px;">
		  </td>
	   	  <td class="yx1" align="right">批次属性：</td>
     	  <td class="xx1" colspan="5"><select id="lotid" onchange="viewLot();"><option value=""></option></select>
     	  	<input type="hidden" name="lt1">	
     	    <input type="hidden" name="lt2">
     	    <input type="hidden" name="lt3">
     	    <input type="hidden" name="lt4">
     	    <input type="hidden" name="lt5">
     	    <input type="hidden" name="lt6">
     	    <input type="hidden" name="lt7">
     	    <input type="hidden" name="lt8">
     	    <input type="hidden" name="lt9">
     	    <input type="hidden" name="lt10">
     	    <input type="hidden" name="lt11">
     	    <input type="hidden" name="lt12">
     	  </td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/inventory/statistic/kc_statistic_list.jsp" 
		  		width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
		  	</td>
		  </tr>
		  <tr>
		    <td height="25">
		      <iframe id="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=20&flag=Y" 
		      	width="100%" height="100%" scrolling="no" frameborder="0" ></iframe> 
		    </td>
		  </tr>
		</table>
		
	  </td>
    </tr>
  </table>  	
</div>

 <!-- 页面加载层（start） -->
 <div id="loader_container" style="position: absolute; display: none; z-index: 2; filter:alpha(opacity=75);">
   <div id="loader"><div align="center">页面正在加载 ...</div><div id="loader_bg"><div id="progress"></div></div></div>
 </div>
 <!-- 页面加载层（end） -->  
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm' target="_blank"  style='display:none'></FORM>
</body>
</html>
