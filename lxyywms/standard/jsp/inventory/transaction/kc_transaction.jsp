<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
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
				
		var name="kc_transaction";
		document.tempForm.action = strUrl + "inventoryTransactionAction&method=ricoExecReport" + condition;
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
	
		var fmwarehouseid = document.getElementById("fmwarehouseid").value;	//fm仓库
		var fmwhAreaid = document.getElementById("fmwhAreaId").value;		//fm库区
		var fmplatoon = document.getElementById("fmplatoon").value;	        //fm排
		var fmcolumn = document.getElementById("fmcolumn").value;			//fm列
		var fmfloor = document.getElementById("fmfloor").value;				//fm层
		
		var fmtraycode = document.getElementById("fmtraycode").value;		//fm托盘条码
		var fmcustomerid = document.getElementById("customer_id").value;	//fm客户id
		var fmpackageid = document.getElementById("package_id").value;	
	
		var towarehouseid = document.getElementById("towarehouseid").value;	//to仓库
		var towhAreaid = document.getElementById("towhAreaId").value;		//to库区
		var toplatoon = document.getElementById("toplatoon").value;			//to排
		var tocolumn = document.getElementById("tocolumn").value;			//to列
		var tofloor = document.getElementById("tofloor").value;				//to层
		var totraycode = document.getElementById("totraycode").value;		//to托盘条码
		var tocustomerid = document.getElementById("tocustomer_id").value;	//to客户id
		var topackageid = document.getElementById("topackage_id").value;	//to产品id
		
		var indate_to = document.getElementById("indate_to").value;		//开始时间
		var indate_from = document.getElementById("indate_from").value;		//结束时间
		if(fmwarehouseid == null || fmwarehouseid.length < 1){
			alert("请先选择【所属仓库】!");
			return;
		}
		if(fmplatoon != null && !IsNum(fmplatoon)){
			alert("【FM排】不能为非数字！");
			return;
		}
		if(fmcolumn != null && !IsNum(fmcolumn)){
			alert("【FM列】不能为非数字！");
			return;
		}
		if(fmfloor != null && !IsNum(fmfloor)){
			alert("【FM层】不能为非数字！");
			return;
		}
		
		if(toplatoon != null && !IsNum(toplatoon)){
			alert("【TO排】不能为非数字！");
			return;
		}
		if(tocolumn != null && !IsNum(tocolumn)){
			alert("【TO列】不能为非数字！");
			return;
		}
		if(tofloor != null && !IsNum(tofloor)){
			alert("【TO层】不能为非数字！");	
			return;
		}				
		condition = "&fmwarehouseid=" +　fmwarehouseid + "&fmwhAreaid=" +　fmwhAreaid + "&fmplatoon=" + fmplatoon + "&fmcolumn=" +　fmcolumn+ "&fmfloor=" + fmfloor
			 + "&fmtraycode=" +　fmtraycode + "&fmcustomerid=" + fmcustomerid + "&fmpackageid=" + fmpackageid + "&towarehouseid=" + towarehouseid + "&towhAreaid=" + towhAreaid
			 + "&toplatoon=" +　toplatoon + "&tocolumn=" + tocolumn+ "&tofloor=" + tofloor+ "&totraycode=" + totraycode+ "&tocustomerid=" + tocustomerid+ "&topackageid=" + topackageid
			 + "&indate_to=" +　indate_to + "&indate_from=" +　indate_from; 
		//每页显示行数
		var linerow = page.document.getElementById("lineviewrow").value;
		condition += "&linerow=" + linerow;
		list.formx1.action = strUrl + "inventoryTransactionAction" + condition;
		list.document.formx1.submit();
		
		Loading();	
	}
	
	
	//页面登录
	function OnLoad(){
		
		//仓库
		DWREngine.setAsync(false);
		selectObject("", "fmwarehouseid", "1");	
		selectObject("", "towarehouseid", "1");	
		DWREngine.setAsync(true);
		
		//fm库区
		var fmwarehouseid = document.getElementById("fmwarehouseid").value;
		selectAreaList("", "fmwhAreaId", fmwarehouseid, "1");	
		
		//to库区
		var towarehouseid = document.getElementById("towarehouseid").value;
		selectAreaList("", "towhAreaId", towarehouseid, "1");
	}
	
	//根据仓库获得库区的列表
	function getWhAreaList(id){
	    if(id=="fm"){
	    	var fmwarehouseid = document.getElementById("fmwarehouseid").value;
			selectAreaList("", "fmwhAreaId", fmwarehouseid, "1");
		  }else{
		    var towarehouseid = document.getElementById("towarehouseid").value;
			selectAreaList("", "towhAreaId", towarehouseid, "1");
     	  }
		
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
		<div id="dqwz" class="f_l">当前位置：<span>库存管理 &gt;&gt; 库存交易</span></div>
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
	      <td class="yx1" width="15%" align="right">FM仓库：</td>
	      <td class="yx"><select name="fmwarehouseid" onChange="getWhAreaList('fm')" style="width:117px;"><option value=""></option></select></td>
	      <td class="yx1" width="15%" align="right">FM库区：</td>
     	  <td class="yx"><select id="fmwhAreaId"  style="width:117px;"><option value=""></option></select></td>
     	  <td class="yx1" width="15%" align="right">FM货位：</td>
     	  <td class="xx1">
	   	    <input type="text" id="fmplatoon" size="2">排
	   	    <input type="text" id="fmcolumn" size="2">列
	   	    <input type="text" id="fmfloor" size="2">层
	   	  </td>    	 
	    </tr>
	    <tr>
	      <td class="yx1" width="15%" align="right">FM托盘条码：</td>
     	  <td class="yx">
     	  	<input id="fmtraycode" type="text"/>
		  </td>
		   <td class="yx1" width="15%" align="right"align="right">FM客户：</td>
	      <td class="yx">
	      	<input type="hidden" name="customer_id"><input type="text" name="customer_name" size="15" readonly>
       		<input type="button" class="button_select" value="…" onClick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp','800','520');">
	      </td>
	  	  <td class="yx1" width="15%" align="right">FM品名规格：</td>
	      <td class="xx1">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name" size="15" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="…" class="button_select">
	      </td>
	    </tr>
	     <tr>
	      <td class="yx1" width="15%" align="right">TO仓库：</td>
	      <td class="yx"><select name="towarehouseid" onChange="getWhAreaList()" style="width:117px;"><option value=""></option></select></td>
	      <td class="yx1" width="15%" align="right">TO库区：</td>
     	  <td class="yx"><select id="towhAreaId" onChange="getAlleyList()" style="width:117px;"><option value=""></option></select></td>
     	  <td class="yx1" width="15%" align="right">TO货位：</td>
     	  <td class="xx1">
	   	    <input type="text" id="toplatoon" size="2">排
	   	    <input type="text" id="tocolumn" size="2">列
	   	    <input type="text" id="tofloor" size="2">层
	   	  </td>

	    </tr>
	    <tr>
	      <td class="yx1" width="15%" align="right">TO托盘条码：</td>
     	  <td class="yx">
     	  	<input id="totraycode" type="text"/>
		  </td>
		   <td class="yx1" width="15%" align="right">TO客户：</td>
	      <td class="yx">
	      	<input type="hidden" name="tocustomer_id"><input type="text" name="tocustomer_name" size="15" readonly>
       		<input type="button" class="button_select" value="…" onClick="openCustomer1('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp','800','520','tocustomer_id','tocustomer_name');">
	      </td>
	      <td class="yx1" width="15%" align="right">TO品名规格：</td>
	      <td class="xx1">
	      	<input type="hidden" name="topackage_id"><input type="text" name="topackage_name" size="15" readonly>
            <input onClick="openProduct1('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520','topackage_id','topackage_name');"  type="button" value="…" class="button_select">
	      </td>
	    </tr>
	    <tr> 
	      <td class="y1" width="15%" align="right">时间：</td>
     	  <td class="x">
     	  	<input id="indate_from" type="text" onFocus="calendar()" class="Wdate" style="height:21px;width:100px;">-<input id="indate_to" type="text" onfocus="calendar();" class="Wdate" style="height:21px;width:100px;">
		  </td></tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/inventory/transaction/kc_transaction_list.jsp" 
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
