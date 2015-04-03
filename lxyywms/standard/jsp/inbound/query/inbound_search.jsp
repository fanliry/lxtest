<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean inboundRkQuery = false;    
	boolean inboundRkReport = false;     
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("inboundRkQuery") != null){
			inboundRkQuery = true;
		}
		if(hsPopedom.get("inboundRkReport") != null){
			inboundRkReport = true;
		}
    }
%>
<%
	String strTime =  StrTools.getCurrDateTime(8); 
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
	var condition = null;
	
	//查询
	function searchData(){
	
		var warehouseid = document.getElementById("warehouseid").value;	//仓库
		var whAreaId = document.getElementById("whAreaId").value;		//库区
		var cargoAlleyId = document.getElementById("cargoAlleyId").value;//巷道
		var cargospaceid = document.getElementById("cargospace_id").value;	//货位id
		
		var onlinetype = document.getElementById("onlinetype").value;	//入库模式
		var indate_from = document.getElementById("indate_from").value;	//入库日期开始
		var indate_to = document.getElementById("indate_to").value;		//入库日期结束
		var isback = document.getElementById("isback").value;			//是否回流
		var invoicetype = "";//document.getElementById("invoicetype").value;	//作业来源
		
		var instockid = document.getElementById("instockid").value;	//入库单号
		var productid= document.getElementById("package_id").value;	//品名
		var traycode = document.getElementById("tray_code").value;		//托盘条码
		var lotinfo = document.getElementById("lotinfo").value;		//批号
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("请先选择【所属仓库】!");
			return;
		}
		
		condition = "&warehouseid=" + warehouseid + "&whAreaId=" + whAreaId + "&cargoAlleyId=" + cargoAlleyId + "&cargospaceid=" + cargospaceid
			 + "&onlinetype=" + onlinetype + "&indate_from=" + indate_from + "&indate_to=" + indate_to + "&isback=" + isback + "&invoicetype=" + invoicetype
			 + "&instockid=" + instockid + "&productid=" + productid + "&traycode=" + traycode + "&lotinfo=" + lotinfo;
	
		list.formx1.action = strUrl + "inBoundJobAction&method=ricoExecSearchJob" + condition;
		list.document.formx1.submit();
		
		Loading();	
	}
	
	//页面登录
	function OnLoad(){
		
		//仓库
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");	
		//库区
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		//selectType('1', 'invoicetype', 'zyly');		//作业来源
		DWREngine.setAsync(true);
		searchData();
	}
	
	//根据仓库获得库区的列表
	function getWhAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
	}
	//打印
	function report(){
		if(condition == null){
			alert("请先查询！");
			return;
		}
		
		document.tempForm.action = strUrl + "inBoundJobAction&method=ricoExecPrint" + condition;
		
		document.tempForm.submit();	
	}
	
	function Mupload(){
		
	}
	
	//1.生成单据 2.部分生产单据
	function Add(type){
		if(type == 1){
			var alts = "";
			var keys = "";
			var check_ids = list.document.getElementsByName("check_id");
			for(var i=0; i<check_ids.length; i++){
				if(check_ids[i].checked){
					alts += check_ids[i].alt + ",";
					keys += check_ids[i].value + ",";
				}
			}
			if(alts == ""){
				alert("请至少选择一条记录！");
				return;
			}
						
			condition =  alts.substring(0, alts.length-1);
			var tmp = "";
			var flagbl= "";
			var theArray = condition.split(",");
			if(theArray.length>1){
				for(var j=0; j < theArray.length; j++){
					var theArrayson = theArray[j].split("|");
					if(j == 0){
						tmp = theArrayson[1];
					}
					if(tmp != theArrayson[1]){
						flagbl = theArrayson[0];
						break;
					}
				}
			}
			
			if(flagbl.length>0){
				alert("所选第["+flagbl+"]行与其他行，类型不相同，不能生成一条入库单!");
				return;
			}
			
			if(confirm("你确定对所选项生成单据？")){
				//alert(keys);
				list.formx1.action = strUrl + "inBoundJobAction&method=ricoExecCreate&keys=" + keys;
				list.document.formx1.submit();
			}
			else{
			}
		}
<%-- 		else {
			var result = showModalDialog("<%=request.getContextPath()%>/jsp/report/shift_in_part_add.jsp", "", 
				"dialogWidth=1100px;dialogHeight=500px;scroll=no");
			if(result != null && result.length > 0){
				list.location.href = ac + "ShiftInStaAction&method=add&flag=4&keys=" + result;
			}
		} --%>
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
		<div id="dqwz" class="f_l">当前位置：<span>入库管理 &gt;&gt; 入库查询</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<%if(inboundRkQuery){%> <li><input style="width:50px" onclick="searchData();" type="button" id="btn8" value="查询" class="button_search4_out" onmouseover="this.className='button_search4_over'" onmouseout="this.className='button_search4_out'"></li><%}%>
		  	<!-- <li><input style="width: 135px;" onclick="Add(2);" type="button" id="btn1" value="部分生成生产入库单" class="button_edit4_out" onmouseover="this.className='button_edit4_over'" onmouseout="this.className='button_edit4_out'"></li>
		  	<li><input style="width: 88px;" onclick="Add(1);" type="button" id="btn1" value="生成入库单" class="button_edit4_out" onmouseover="this.className='button_edit4_over'" onmouseout="this.className='button_edit4_out'"></li> -->
		  	<%if(inboundRkReport){%><li><input style="width:50px;" onclick="report();" type="button" id="btn1" value="报表" class="button_search4_out" onmouseover="this.className='button_search4_over'" onmouseout="this.className='button_search4_out'"></li><%}%>
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
     	  <td class="yx1" width="100px" align="right">货位：</td>
	      <td class="xx1">
		    <input type="hidden" id="cargospace_id"><input type="text" id="cargospace_name" size="14" readonly>
       	    <input onClick="openCargospace('<%=request.getContextPath()%>/standard/jsp/common/common_cargospace.jsp?warehouseid='+document.getElementById('warehouseid').value+'&whAreaId='+document.getElementById('whAreaId').value,'850','550');" 
       			type="button" value="…" class="button_select">
          </td>
	    </tr>
	    <tr>
	      <td class="yx1" align="right">入库模式：</td>
     	  <td class="yx">
     	  	<select id="onlinetype" style="width:117px;"><option value="">--请选择--</option><option value="1">联机</option><option value="2">脱机</option></select>
     	  </td>
     	  <td class="yx1" align="right">入库日期：</td>
	      <td class="yx">
	      	<input id="indate_from" type="text" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:100px;">-<input id="indate_to" type="text" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:100px;">
	      </td>
	      <td class="yx1" align="right">是否回流：</td>
	      <td class="yx">
         	<select id="isback" style="width:117px;"><option value="">--请选择--</option><option value="Y">回流</option><option value="N">非回流</option></select>
       	  </td>
	       <td class="yx1" align="right">托盘条码：</td>
     	  <td class="yx"><input name="tray_code" type="text" size="15"></td>
	    </tr>
	    <tr>
	      <td class="yx1" align="right">入库单号：</td>
	      <td class="yx"><input type="text" name="instockid"   style="height:18px;width:110px;"/></td>
	      <td class="yx1" align="right">品名：</td>
	      <td class="yx">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name" size="15" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="…" class="button_select">
	      </td>
	      <td class="yx1" align="right">托盘条码：</td>
     	  <td class="yx"><input name="tray_code" type="text" size="15"></td>
	   	  <td class="yx1" align="right">批号：</td>
     	  <td class="yx"><input name="lotinfo" type="text" size="15"></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/inbound/query/inbound_search_list.jsp" 
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
