 <%@ page contentType="text/html; charset=GBK"%>
<%
	String warehouseid = "";
	if(request.getParameter("warehouseid") != null){
		warehouseid = request.getParameter("warehouseid");
	}

	String whAreaId = "";
	if(request.getParameter("whAreaId") != null){
		whAreaId = request.getParameter("whAreaId");
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
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	
	function OnOk(){
		var backmsg = null;
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i< check_ids.length; i++){
		 	if(check_ids[i].checked){
				backmsg = check_ids[i].value;
				break;
			}
		}
		
		if(backmsg==null || backmsg==""){
			alert("请选择一条记录！");
		}else{
			window.returnValue = backmsg;
			window.close();
		}
	} 
	
	//双击
	function ondbClickDo(strVar)
	{
		window.returnValue = strVar;
		window.close();
	}
	
	//查询
	function queryData(){
		var warehouseid = document.getElementById("warehouseid").value;
		var whAreaId = document.getElementById("whAreaId").value;
		var cargoAreaId = document.getElementById("cargoAreaId").value;
		var platoon = document.getElementById("platoon").value;
		var column = document.getElementById("column").value;
		var floor = document.getElementById("floor").value;
		var csstatus = document.getElementById("csstatus").value;
  		var usage = document.getElementById("usage").value;				//库位使用
  		var handling = document.getElementById("handling").value;		//存储类型
		
		if(warehouseid=="" || whAreaId==""){
			alert("请选择仓库和库区！");
			return;
		}

		if(platoon!=null && platoon!=""){
			if(!numChar(platoon) || platoon<=0){
				alert("【排】必须是大于零的数字!");
				return false;
			}
		}
		
		if(column!=null && column!=""){
			if(!numChar(column) || column<=0){
				alert("【列】必须是大于零的数字!");
				return false;
			}
		}
		
		if(floor!=null && floor!=""){
			if(!numChar(floor) || floor<=0){
				alert("【层】必须是大于零的数字!");
				return false;
			}
		}
		
		//每页显示行数
		var linerow = list.document.getElementById("lineviewrow").value;
		
		condition = "&warehouseid=" +　warehouseid + "&whAreaId=" +　whAreaId + "&cargoAreaId=" +　cargoAreaId + "&platoon=" +　platoon 
				+ "&column=" +　column + "&floor=" +　floor + "&csstatus=" +　csstatus + "&usage=" + usage + "&handling=" + handling
			    + "&linerow=" + linerow;
		Loading();
		list.location.href = ac + "baseCargoSpaceAction&flag=3" + condition;
	}
	
	//根据仓库获得库区，货区的列表
	function getAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		selectAreaList("", "cargoAreaId", warehouseid, "2");
	}
	
	//页面登录
	function OnLoad(){	
		//同步
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");
		
		if("<%=warehouseid%>" != ""){
			document.getElementById("warehouseid").value = "<%=warehouseid%>";
		}
		
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		selectAreaList("", "cargoAreaId", warehouseid, "2");
		DWREngine.setAsync(true);
		
		if("<%=whAreaId%>" != ""){
			document.getElementById("whAreaId").value = "<%=whAreaId%>";
		}
		
		selectType("", "csstatus", "estate");	//货位状态
		selectType("", "usage", "kwsy");		//库位使用
		selectType("", "handling", "cclx");		//存储类型
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
 <input type="hidden" name="condition">
 <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td>
     
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">当前位置：选择货位</div></td>
    </tr>
  </table>
 
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td class="yx1" align="right"><div align="right">仓库：</div></td>
	  <td class="yx"><select id="warehouseid" onChange="getAreaList()" style="width:150px;"><option value=""></option></select></td>
	  <td class="yx1" align="right"><div align="right">库区：</div></td>
      <td class="yx"><select name="whAreaId" style="width:150px;"><option value=""></option></select></td>
	  <td class="yx1" align="right">货区：</td>
	  <td class="xx1"><select name="cargoAreaId" style="width:150px;"><option value=""></option></select></td> 
	</tr>
	<tr>
   	  <td class="yx1" align="right">货位：</td>
   	  <td class="yx" style="width:150px;">
   	    <input type="text" name="platoon" size="3" maxlength="2">排
   	    <input type="text" name="column" size="3" maxlength="2">列
   	    <input type="text" name="floor" size="3" maxlength="2">层
   	  </td>
   	  <td class="yx1" align="right">货位状态：</td>
	  <td class="xx1" colspan="3">
	    <select id="csstatus" style="width:150px;"><option value=""></option></select>
	  </td> 
    </tr>	
    <tr>
      <td class="yx1"><div align="right">库位使用：</div></td>
      <td class="yx"><select id="usage" style="width:150px;"><option></option></select></td>
      <td class="yx1"><div align="right">存储类型：</div></td>
      <td class="xx1" colspan="3"><select id="handling" style="width:150px;"><option></option></select></td>
    </tr>   
    <tr>
      <td height="35" colspan="6" align="center" valign="bottom">
        <input type="button" value="&nbsp;&nbsp;&nbsp;查询" class="button_search" onClick="queryData()">
            <input type="button" value="确定" class="BUTTON_STYLE1" onClick="OnOk()">
           	<input type="button" value="关闭" class="BUTTON_STYLE1" onClick="window.close();" />
      </td>
    </tr>
  </table>

    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
      
      	<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/common/common_cargospace_list.jsp"
		  		width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
		  	</td>
		  </tr>
		</table>
		
	  </td>
    </tr>
  </table> 

 <!-- 页面加载层（start） -->
 <div id="loader_container" style="position: absolute; display: none; z-index: 2; filter:alpha(opacity=75);">
   <div id="loader"><div align="center">页面正在加载 ...</div><div id="loader_bg"><div id="progress"></div></div></div>
 </div>
 <!-- 页面加载层（end） -->  
</body>
</html>
