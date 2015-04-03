<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%

    HashMap hsPopedom = null;
	boolean kc_kcmovequery = false; //查询
	boolean kc_kcmovereset = false; //重置
	boolean kc_kcmovezc = false; //移库到暂存
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("kc_kcmovequery") != null){
			kc_kcmovequery = true;
		}
		if(hsPopedom.get("kc_kcmovereset") != null){
			kc_kcmovereset = true;
		}
		if(hsPopedom.get("kc_kcmovezc") != null){
			kc_kcmovezc = true;
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
	
	
	//查询
	function searchData(){
	
		var warehouseid = document.getElementById("warehouseid").value;	//仓库
		var whAreaId = document.getElementById("whAreaId").value;		//库区
		var cargoAlleyId = document.getElementById("cargoAlleyId").value;//巷道
		var platoon = document.getElementById("platoon").value;			//排
		var column = document.getElementById("column").value;			//列
		var floor = document.getElementById("floor").value;				//层
		var package_id= document.getElementById("package_id").value;	//品名
		var tray_code = document.getElementById("tray_code").value;		//托盘条码
		var productcode = document.getElementById("productcode").value;		//产品编码
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("请先选择【所属仓库】!");
			return;
		}
		if(whAreaId == null || whAreaId.length < 1){
			alert("请选择库区！");
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
		
		condition = "&warehouseid=" +　warehouseid + "&whAreaId=" +　whAreaId + "&cargoAlleyId=" +　cargoAlleyId + "&platoon=" +　platoon + 
			"&column=" +　column + "&floor=" +　floor + "&package_id=" +　package_id + "&tray_code=" +　tray_code+ "&productcode=" +　productcode;
			
		//每页显示行数
		var linerow = page.document.getElementById("lineviewrow").value;
		condition += "&linerow=" + linerow;
		list.formx1.action = strUrl + "inventoryMoveAction&flag=1" + condition;
		list.document.formx1.submit();
		Loading();	
	}
	
	function move(){
		    var k = 0;
			var ids = "";
			var check_ids = list.document.getElementsByName("check_id");
			var warehouseid = document.getElementById("warehouseid").value;
			
			if(warehouseid == null || warehouseid.length < 1){
				alert("请先选择【所属仓库】!");
				return;
			}
			
			for(var i=0; i<check_ids.length; i++){
				if(check_ids[i].checked){
					ids += check_ids[i].value+"|";
				}
			}
			if(ids!=null && ids.length > 0){
				ids = ids.substring(0, ids.length-1);//去掉最后面的“|”
			}
			
			if(ids == ""){
				alert("请至少选择一条记录！");
				return;
			}
			
			var WLeft = Math.ceil((window.screen.width-1100)/2);
	  		var WTop  = Math.ceil((window.screen.height-800)/2);
			
			window.open(strUrl+"inventoryMoveAction&flag=2&inventoryid="+ids+"&warehouseid"+warehouseid,'newwindow',"width=800,height=400,left="+WLeft+",top="+WTop+"'");
	
	}
	//重置
	function resetData(formName) 
	{
	    var formObj = document.forms[formName]; 
	    var formEl = formObj.elements; 
	    for (var i=0; i<formEl.length; i++) 
	    {
	        var element = formEl[i]; 
	        if (element.type == 'submit') { continue; } 
	        if (element.type == 'reset') { continue; } 
	        if (element.type == 'button') { continue; } 
	 
	        if (element.type == 'text') { element.value = ''; } 
	        if (element.type == 'hidden') { element.value = ''; } 
	        if (element.type == 'textarea') { element.value = ''; } 
	        if (element.type == 'checkbox') { element.checked = false; } 
	        if (element.type == 'radio') { element.checked = false; } 
	        if (element.type == 'select-multiple') { element.selectedIndex = -1; } 
	        if (element.type == 'select-one') { element.selectedIndex = 0; } 
	    }
	}
	
	//页面登录
	function OnLoad(){
		
		//仓库
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");	
		DWREngine.setAsync(true);
		
		//库区
		var warehouseid = document.getElementById("warehouseid").value;
		
		selectAreaListNotTem('001001', 'whAreaId', warehouseid, "1");// 立体库区
		
		
		
	}
	
	//根据仓库获得库区的列表
	function getWhAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaListNotTem('001001', 'whAreaId', warehouseid, "1");// 立体库区
		//selectAreaList("", "whAreaId", warehouseid, "1");
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
		<div id="dqwz" class="f_l">当前位置：<span>库存管理 &gt;&gt; 库存移动</span></div>
		<div id="caozuo" class="f_r" align="right">
		   <%if(kc_kcmovequery){%><input type="button" style=width:55 class="button_search" value="&nbsp;&nbsp;&nbsp;查询" onclick="searchData()"/><%}%>
		   <%if(kc_kcmovereset){%><input type="reset" style=width:55 class="button_reset" onclick="resetData('myform')" value="&nbsp;&nbsp;&nbsp;重置"/><%}%>
		   <%if(kc_kcmovezc){%><input type="button" style=width:110 class="button_edit" value="&nbsp;&nbsp;&nbsp;移动到暂存" onClick="move()"/><%}%>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== 查询条件 ======== -->
      <form id="myform" name="myform" method="post" action="">	
      <table id="marginTable" width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
	    <tr>
	      <td class="yx1" width="100" align="right">仓库：</td>
	      <td class="yx"><select name="warehouseid" onChange="getWhAreaList()" style="width:117px;"><option value=""></option></select></td>
	      <td class="yx1" width="100" align="right">库区：</td>
     	  <td class="yx"><select id="whAreaId" onChange="getAlleyList()" style="width:117px;"><option value=""></option></select></td>
	   	  <td class="yx1" width="100" align="right">巷道：</td>
     	  <td class="yx"><select id="cargoAlleyId" style="width:117px;"><option value="">--请选择--</option></select></td>
     	  <td class="yx1" width="100" align="right">货位：</td>
	   	  <td class="xx1">
	   	    <input type="text" id="platoon" size="2">排
	   	    <input type="text" id="column" size="2">列
	   	    <input type="text" id="floor" size="2">层
	   	  </td>
	    </tr>
	    <tr>
	     
	      <td class="yx1" align="right">品名：</td>
	      <td class="yx">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name" size="15" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="…" class="button_select">
	      </td>
	      <td class="yx1" align="right">产品编码：</td>
     	  <td class="yx"><input name="productcode" type="text" size="15"></td>
	      <td class="yx1" align="right">托盘条码：</td>
     	  <td class="yx"><input name="tray_code" type="text" size="15"></td>
	   	 
	    </tr>
      </table> 
		</form>
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" name="list" src="<%=request.getContextPath()%>/standard/jsp/inventory/move/kc_move_list.jsp" 
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
