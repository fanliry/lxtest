<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean productWarningQuery = false; //查询
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("productWarningQuery") != null){
			productWarningQuery = true;
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
		var name="kc_search";
		
		document.tempForm.action = strUrl + "inventoryAction&flag=2" + condition;
		//document.tempForm.target=name;
		//document.tempForm.attachEvent("onsubmit",function(){ openWindow(name); });
		//document.tempForm.fireEvent("onsubmit");	
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
		var cargoAlleyId = document.getElementById("cargoAlleyId").value;//巷道
		var platoon = document.getElementById("platoon").value;			//排
		var column = document.getElementById("column").value;			//列
		var floor = document.getElementById("floor").value;				//层
		
		var lotnumber = document.getElementById("lotnumber").value;	//批号
		var package_id= document.getElementById("package_id").value;	//品名
		var tray_code = document.getElementById("tray_code").value;		//托盘条码
		
		var productstatus = document.getElementById("productstatus").value;	//物品状态
		var csstatus = document.getElementById("csstatus").value;		//库存状态
		var indate_from = document.getElementById("indate_from").value;	//生产日期
		var indate_to = document.getElementById("indate_to").value;		//生产日期
		if(warehouseid == null || warehouseid.length < 1){
			alert("请先选择【所属仓库】!");
			return;
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
		
		condition = "&warehouseid=" +warehouseid + "&whAreaId=" +whAreaId + "&cargoAlleyId=" +cargoAlleyId + "&platoon=" +platoon + 
			"&column=" +column + "&floor=" +floor + "&lotnumber=" + lotnumber + "&productid=" +package_id + "&tray_code=" +tray_code+ "&productstatus=" +productstatus+ "&status=" +csstatus+ "&indate_from=" +indate_from+ "&indate_to=" +indate_to;
			
		//每页显示行数
		var linerow = page.document.getElementById("lineviewrow").value;
		condition += "&linerow=" + linerow;
		
		list.formx1.action = strUrl + "inventoryAction&flag=1" + condition;
		
		list.document.formx1.submit();
		
		Loading();	
	}
	

	//预警查询
	function prowarn(){
	
		var warehouseid = document.getElementById("warehouseid").value;	//仓库
		var whAreaId = document.getElementById("whAreaId").value;		//库区
		var cargoAlleyId = document.getElementById("cargoAlleyId").value;//巷道
		var platoon = document.getElementById("platoon").value;			//排
		var column = document.getElementById("column").value;			//列
		var floor = document.getElementById("floor").value;				//层
		
		var lotnumber = document.getElementById("lotnumber").value;	//批号
		var package_id= document.getElementById("package_id").value;	//品名
		var tray_code = document.getElementById("tray_code").value;		//托盘条码
		
		var productstatus = document.getElementById("productstatus").value;	//物品状态
		var csstatus = document.getElementById("csstatus").value;		//库存状态
		var indate_from = document.getElementById("indate_from").value;	//生产日期
		var indate_to = document.getElementById("indate_to").value;		//生产日期
		if(warehouseid == null || warehouseid.length < 1){
			alert("请先选择【所属仓库】!");
			return;
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
		
		condition = "&warehouseid=" +warehouseid + "&whAreaId=" +whAreaId + "&cargoAlleyId=" +cargoAlleyId + "&platoon=" +platoon + 
			"&column=" +column + "&floor=" +floor + "&lotnumber=" + lotnumber + "&productid=" +package_id + "&tray_code=" +tray_code+"&productstatus=" +productstatus+ "&status=" +csstatus+ "&indate_from=" +indate_from+ "&indate_to=" +indate_to;
			
		//每页显示行数
		var linerow = page.document.getElementById("lineviewrow").value;
		condition += "&linerow=" + linerow;
		
		list.formx1.action = strUrl + "inventoryAction&flag=11" + condition;
		
		list.document.formx1.submit();
		
		Loading();	
	}
	
	//页面登录
	function OnLoad(){
		//仓库
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");	
		DWREngine.setAsync(true);
		//物品状态
		selectType('', 'productstatus', 'wpzt');
		//物品状态
		selectType('2', 'csstatus', 'hwzt');
		
		//库区
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		prowarn();
	}
	
	//根据仓库获得库区的列表
	function getWhAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
	}
	
	//根据库区获得巷道的列表
	function getAlleyList(){
	
		var whAreaId = document.getElementById("whAreaId").value;
		selectAlleyList("", "cargoAlleyId", whAreaId);
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
		<div id="dqwz" class="f_l">当前位置：<span>库存管理 &gt;&gt; 产品预警</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<li class="tubiao1" ><input onclick="prowarn();" type="button" id="btn1" value="产品预警查询" class="button_search4_out" onmouseover="this.className='button_search4_over'" onmouseout="this.className='button_search4_out'" style="width: 97px;"></li>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== 查询条件 ======== -->
      <table id="marginTable" width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
	    <tr>
	      <td class="yx1" width="100" align="right">仓库：</td>
	      <td class="yx"><select name="warehouseid" onChange="getWhAreaList()" style="width:117px;"><option value=""></option></select></td>
	      <td class="yx1" width="100" align="right">库区：</td>
     	  <td class="yx"><select id="whAreaId" onChange="getAlleyList()" style="width:117px;"><option value=""></option></select></td>
	   	  <td class="yx1" width="100" align="right">巷道：</td>
     	  <td class="yx"><select id="cargoAlleyId" style="width:117px;"><option value="">--请选择--</option></select></td>
     	  <td class="yx1" width="100" align="right">货位：</td>
	   	  <td class="yx">
	   	    <input type="text" id="platoon" size="2">排
	   	    <input type="text" id="column" size="2">列
	   	    <input type="text" id="floor" size="2">层
	   	  </td>
	    </tr>
	    <tr>
	      <td class="yx1" align="right">进场编号：</td>
	      <td class="yx">
	      	<input type="text" name="lotnumber"  size="15">
	      </td>
	      <td class="yx1" align="right">品名：</td>
	      <td class="yx">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name" size="15" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="…" class="button_select">
	      </td>
	      <td class="yx1" align="right">托盘条码：</td>
     	  <td class="yx" colspan = "3"><input name="tray_code" type="text" size="25"></td>

	    </tr>
	    <tr>
	      <td class="yx1" align="right">货位状态：</td>
	      <td class="yx">
	      	<select id="csstatus" style="width:110px;">
                <option value="">--请选择--</option>
              </select>
	      </td>
	      <td class="yx1" align="right">物品状态：</td>
          <td class="yx">
              <select id="productstatus" style="width:110px;">
                <option value="">--请选择--</option>
              </select>
           </td>
	     <td class="yx1" align="right">入库日期：</td>
	     <td class="yx" colspan = "3">
	      	<input id="indate_from" type="text" onfocus="calendar();" class="Wdate" style="width:100px;">-<input id="indate_to" type="text"  onfocus="calendar();" class="Wdate" style="width:100px;">
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
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/inventory/productwarning/product_warning_list.jsp" width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
		  	</td>
		  </tr>
		  <tr>
		    <td height="25">
		      <iframe id="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=120&flag=Y" 
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
