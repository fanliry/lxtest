<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.util.HashMap" %>
<%

    HashMap hsPopedom = null;
	boolean kc_movequerybutton = false; //查询
	boolean kc_movequeryreport = false; //报表
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("kc_movequerybutton") != null){
			kc_movequerybutton = true;
		}
		if(hsPopedom.get("kc_movequeryreport") != null){
			kc_movequeryreport = true;
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
	//打印
	function report(){
		
		//var movementid = list.document.getElementById("fmwarehouseid").value;
		if(condition == null){
			alert("请先查询！");
			return;
		}
		
		var k = 0;
		var id = " ";
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id = check_ids[i].value;
				k++;
			}
		}
		if( k == 0 ){
			alert("请选择一张单据！");
			return;
		}else if(k != 1){
			alert("只能选择一张单据！");
			return;
		}
		//document.tempForm.action = strUrl + "inventoryQualityAction&method=ricoExecPrint&invoiceid="+id;
		//document.tempForm.submit();	
  		
		//var actionStr =strUrl + "inventoryMoveAction&method=ricoExecPrint";
	    		
		//var WLeft = Math.ceil((window.screen.width-800)/2);
  		//var WTop  = Math.ceil((window.screen.height-600)/2);
  			
		//window.open(actionStr,'newwindow','width=1200,height=600,left='+WLeft+',top='+WTop+',scrollbars=yes');
		
		//var name="kc_query";
		document.tempForm.action = strUrl + "inventoryMoveAction&method=ricoExecPrint&invoiceid="+id;
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
	
		var fmwarehouseid = document.getElementById("fmwarehouseid").value;	//fm仓库
		var fmwhAreaid = document.getElementById("fmwhAreaId").value;		//fm库区
		var fmplatoon = document.getElementById("fmplatoon").value;	        //fm排
		var fmcolumn = document.getElementById("fmcolumn").value;			//fm列
		var fmfloor = document.getElementById("fmfloor").value;				//fm层
		
		
	
		var towarehouseid = document.getElementById("towarehouseid").value;	//to仓库
		var towhAreaid = document.getElementById("towhAreaId").value;		//to库区
		var toplatoon = document.getElementById("toplatoon").value;			//to排
		var tocolumn = document.getElementById("tocolumn").value;			//to列
		var tofloor = document.getElementById("tofloor").value;				//to层
		
		
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
			 +  "&towarehouseid=" + towarehouseid + "&towhAreaid=" + towhAreaid+ "&toplatoon=" +　toplatoon + "&tocolumn=" + tocolumn+ "&tofloor=" + tofloor
			 + "&indate_to=" +　indate_to + "&indate_from=" +　indate_from; 
		//每页显示行数
		var linerow = page.document.getElementById("lineviewrow").value;
		condition += "&linerow=" + linerow;
		list.formx1.action = strUrl + "inventoryMoveAction&method=ricoExecQuery&flag=1" + condition;
		list.document.formx1.submit();
	
		Loading();
 		
		detail.location.href = "<%=request.getContextPath()%>/standard/jsp/inventory/ykquery/yk_query_list_detail.jsp";
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
		<div id="dqwz" class="f_l">当前位置：<span>库存管理 &gt;&gt; 移库查询</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
		    <%if(kc_movequeryreport){%><li class="tubiao2"><a href="#" onClick="report()">报表</a></li><%}%>
			<%if(kc_movequerybutton){%><li class="tubiao1"><a href="#" onclick="searchData()">查询</a></li><%}%>
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
	      <td class="yx1" width="15%" align="right">TO仓库：</td>
	      <td class="yx"><select name="towarehouseid" onChange="getWhAreaList()" style="width:117px;"><option value=""></option></select></td>
	      <td class="yx1" width="15%" align="right">TO库区：</td>
     	  <td class="yx"><select id="towhAreaId" style="width:117px;"><option value=""></option></select></td>
     	  <td class="yx1" width="15%" align="right">TO货位：</td>
     	  <td class="xx1">
	   	    <input type="text" id="toplatoon" size="2">排
	   	    <input type="text" id="tocolumn" size="2">列
	   	    <input type="text" id="tofloor" size="2">层
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
<!-- 		  <tr>
            <td valign="bottom" class="title" height="18px">移库单信息</td>
          </tr> -->
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/inventory/ykquery/yk_query_list.jsp" 
		  		width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
		  	</td>
		  </tr>
		  <tr>
		    <td height="25">
		      <iframe id="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=20&flag=Y" 
		      	width="100%" height="100%" scrolling="no" frameborder="0" ></iframe> 
		    </td>
		  </tr>
		  
		  <tr>
            <td valign="bottom" class="title" height="19px">移库单详细信息</td>
          </tr>
	      <tr>
	        <td>
		      <iframe id="detail" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inventory/ykquery/yk_query_list_detail.jsp"></iframe>
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
