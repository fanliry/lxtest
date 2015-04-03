<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean inboundRkdQuery = false;    //查询
	boolean inboundRkdCreate = false;     //报表
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("inboundRkdQuery") != null){
			inboundRkdQuery = true;
		}
		if(hsPopedom.get("inboundRkdCreate") != null){
			inboundRkdCreate = true;
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
		
		var startTime = document.getElementById("start_time").value;		//入库日期
		var endTime = document.getElementById("end_time").value;		//入库日期
		var productdate = document.getElementById("productdate").value;		//产品生产日期
		
		
		var productid= document.getElementById("package_id").value;	//品名
		var traycode = document.getElementById("tray_code").value;		//托盘条码
		var lotinfo = document.getElementById("lotinfo").value;		//批号
		
		var isopen = document.getElementById("isopen").value;
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("请先选择【所属仓库】!");
			return;
		}
		
		condition = "&warehouseid=" + warehouseid + "&whAreaId=" + whAreaId + "&cargoAlleyId=" + cargoAlleyId + "&cargospaceid=" + cargospaceid
			 + "&startTime=" + startTime+ "&endTime=" + endTime+ "&productdate=" + productdate + "&productid=" + productid + "&traycode=" + traycode + "&lotinfo=" + lotinfo + "&isopen=" + isopen;
	
		list.formx1.action = strUrl + "inBoundJobAction&method=ricoExecSearchJobCollection" + condition;
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
	
	function Mupload(){
		
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
	
	//1.生成单据 2.部分生产单据
	function Add(type){
		
		var alts = "";
		var keys = "";
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				keys += check_ids[i].value + ",";
				alts += check_ids[i].alt + ",";
			}
		}
		if(type == 1){//1.生成单据 
			if(keys == ""){
				alert("请至少选择一条记录！");
				return;
			}
			
			if(confirm("你确定对所选项生成单据？")){
				//alert(keys);
				list.formx1.action = strUrl + "inBoundJobAction&method=ricoExecCreate" + "&flag=1" + condition;
				list.document.getElementById("keys").value = keys;
				list.document.getElementById("condition").value = condition;
				list.document.formx1.submit();
			}
			else{
				return;
			}
			
		}else {//2.部分生产单据
			var warehouseid = document.getElementById("warehouseid").value;	//仓库
			alts = alts.substring(0, alts.length-1);
			var inss = alts.split(",");
			if(inss.length != 1){
				alert("部分生成单据，只能选择一条");
				return;
			}
			
			var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/inbound/newrkd/inbound_search_part.jsp?warehouseid="+warehouseid+"&jobtype="+alts, "", 
				"dialogWidth=1100px;dialogHeight=500px;scroll=no");
			if(result != null && result.length > 0){
				//alert(result);
				list.location.href = strUrl + "inBoundJobAction&method=ricoExecCreatePart&warehouseid="+warehouseid+"&keys="+result+"&jobtype="+alts;
			}
		}
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
		<div id="dqwz" class="f_l">当前位置：<span>入库管理 &gt;&gt; 新建入库单</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<%if(inboundRkdQuery){%> <li><input style="width:50px" onclick="searchData();" type="button" id="btn8" value="查询" class="button_search4_out" onmouseover="this.className='button_search4_over'" onmouseout="this.className='button_search4_out'"></li><%}%>
		  	<%if(inboundRkdCreate){%> <li><input style="width: 88px;" onclick="Add(1);" type="button" id="btn1" value="生成入库单" class="button_edit4_out" onmouseover="this.className='button_edit4_over'" onmouseout="this.className='button_edit4_out'"></li><%}%>
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
	      <td class="yx"><select name="warehouseid" onChange="getWhAreaList()" style="width:117px;" ><option value=""></option></select></td>
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
     	  <td class="yx1" align="right">入库日期：</td>
	      <td class="yx">
	      	<input name="start_time" type="text" size="13" value="<%=strTime%>" onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
                  -<input name="end_time" type="text" size="13" value="<%=strTime%>" onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" /> 
	      </td>
	       <td class="yx1" align="right">托盘条码：</td>
     	  <td class="yx"><input name="tray_code" type="text" size="15"></td>
	      <td class="yx1" align="right">品名：</td>
	      <td class="yx">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name" size="15" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="…" class="button_select">
	      </td>
	   	  <td class="yx1" align="right">进场编号：</td>
     	  <td class="xx1"><input name="lotinfo" type="text" size="15"></td>
	    </tr>
	    <tr>
	     <td class="yx1" align="right">生产日期：</td>
	      <td class="yx">
	      	<input name="productdate" type="text" size="13"  onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
	      </td>
     	  <td class="yx1" align="right">是否开单：</td>
	      <td class="yx" colspan="5">
	      	<select name="isopen" style="width:117px;">
	      		<option value="">---请选择---</option>
	      		<option value="Y">已开单</option>
	      		<option value="N" selected>未开单</option>
	      	</select>
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
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/inbound/newrkd/inbound_search_list.jsp" 
		  		width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
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
 
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm' target="_blank"  style='display:none'>
 	<input type="hidden" value="" id="keys">
 </FORM>
</body>
</html>
