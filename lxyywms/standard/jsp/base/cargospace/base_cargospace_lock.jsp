 <%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%

	HashMap hsPopedom = null;
	boolean instoreQuery_query = true;   //查询

	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("instoreQuery_query") != null){
			instoreQuery_query = true;
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
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	
	function Update(flag){
		var ids = "";
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				ids += check_ids[i].value + ",";
			}
		}
		if(ids == ""){
			alert("请选择一条记录！");
		}
		else{
			//get提交长度为2056,超过这个长度用post提交
			//list.location.href = ac + "baseCargoSpaceAction&method=cargoSpaceLock&flag=" + flag + "&ids=" + ids.substring(0, ids.length-1);
			
			//改为POST提交
			var subUrl = ac + "baseCargoSpaceAction&method=cargoSpaceLock&flag=" + flag;
			list.document.write("<form action='"+subUrl+"' method='post' name='formx1' style='display:none'>"); 
			list.document.write("<input type=hidden name='ids' value='"+ids.substring(0, ids.length-1)+"'>"); 
			list.document.write("</form>"); 
			list.document.formx1.submit(); 
		}
	}
	
	//查询
	function Search(){
		var warehouseid = document.getElementById("warehouseid").value;
		var whAreaId = document.getElementById("whAreaId").value;
		var cargoAreaId = document.getElementById("cargoAreaId").value;
		var platoon = document.getElementById("platoon").value;
		var column = document.getElementById("column").value;
		var floor = document.getElementById("floor").value;
		var in_lock = document.getElementById("in_lock").value;
		var out_lock = document.getElementById("out_lock").value;
		
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
				+ "&column=" +　column + "&floor=" +　floor + "&in_lock=" +　in_lock + "&out_lock=" +　out_lock + "&linerow=" + linerow;
		Loading();
		list.location.href = ac + "baseCargoSpaceAction&method=cargoSpaceQuery" + condition;
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
		DWREngine.setAsync(true);
		
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		selectAreaList("", "cargoAreaId", warehouseid, "2");
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
      <td height="25"><div class="font_001F56_bold_12">当前位置：基本信息 -&gt; 货位锁定管理</div></td>
    </tr>
  </table>
 
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td class="yx1" width="100px" align="right"><div align="right">仓库：</div></td>
	  <td class="yx"><select id="warehouseid" onChange="getAreaList()" style="width:135px;"><option value=""></option></select></td>
	  <td class="yx1" width="100px" align="right"><div align="right">库区：</div></td>
      <td class="yx" width="150px"><select name="whAreaId" style="width:100px;"><option value=""></option></select></td>
	  <td class="yx1" width="100px" align="right">货区：</td>
	  <td class="xx1" width="150px"><select name="cargoAreaId" style="width:100px;"><option value=""></option></select></td> 
	</tr>
	<tr>
   	  <td class="yx1" align="right">货位：</td>
   	  <td class="yx">
   	    <input type="text" name="platoon" size="3" maxlength="2">排
   	    <input type="text" name="column" size="3" maxlength="2">列
   	    <input type="text" name="floor" size="3" maxlength="2">层
   	  </td>
   	  <td class="yx1" align="right">入库锁：</td>
	  <td class="yx">
	    <select name="in_lock" style="width:100px;"><option value="">--请选择--</option><option value="Y">已锁</option><option value="N">未锁</option></select>
	  </td> 
	  <td class="yx1" align="right">出库锁：</td>
	  <td class="xx1">
        <select name="out_lock" style="width:100px;"><option value="">--请选择--</option><option value="Y">已锁</option><option value="N">未锁</option></select>
      </td> 
    </tr>	 
    <tr>
      <td height="35" colspan="6" align="center" valign="bottom">
        <input type="button" value="&nbsp;&nbsp;&nbsp;查询" class="button_search" onClick="Search()" <%if(instoreQuery_query==false){%>disabled<%}%>>
        <input type="button" value="&nbsp;&nbsp;入库加锁" class="button_edit" onClick="Update(1)">
        <input type="button" value="&nbsp;&nbsp;出库加锁" class="button_edit" onClick="Update(2)">
        <input type="button" value="&nbsp;&nbsp;入库解锁" class="button_edit" onClick="Update(3)">
        <input type="button" value="&nbsp;&nbsp;出库解锁" class="button_edit" onClick="Update(4)">
        <input type="button" value="&nbsp;&nbsp;清空货位" class="button_edit" onClick="Update(5)">
        <input type="button" value="&nbsp;&nbsp;保存货位" class="button_edit" onClick="Update(6)">
        <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="关闭" onClick="window.close();" />
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
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/base/cargospace/base_cargospace_lock_list.jsp"
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
