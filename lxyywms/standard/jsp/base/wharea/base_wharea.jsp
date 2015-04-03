<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean baseWhareaAdd = false;    //添加
	boolean baseWhareaDelete = false; //删除
	boolean baseWhareaUpdate = false; //修改
	boolean baseWhareaQuery = false;  //查询
	boolean baseWhareaBelongTo = false;  //设置隶属于库区
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("baseWhareaAdd") != null){
			baseWhareaAdd = true;
		}
		if(hsPopedom.get("baseWhareaDelete") != null){
			baseWhareaDelete = true;
		}
		if(hsPopedom.get("baseWhareaUpdate") != null){
			baseWhareaUpdate = true;
		}
		if(hsPopedom.get("baseWhareaQuery") != null){
			baseWhareaQuery = true;
		}
		if(hsPopedom.get("baseWhareaBelongTo") != null){
			baseWhareaBelongTo = true;
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
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	
	//增加
	function addData(){
		var time = new Date(); 
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/wharea/base_wharea_add.jsp",'','dialogWidth=500px;dialogHeight=250px');
	   	if(result != null && result.length > 1){
	   		list.location.href = ac + "baseWhareaAction&method=ricoExecAdd" + result;
	   	}
	}
	
	//查询
	function searchData(){
		var warehouseid = document.getElementById("warehouseid").value;
		var whAreaName = document.getElementById("whAreaName").value;
		
		var condition = "&warehouseid=" + warehouseid + "&whAreaName=" + whAreaName;
		
		list.location.href = ac + "baseWhareaAction&flag=1" + condition;
	}
	
	//返回选中值
	function getChkValue(){
		var id = "";
		var check_ids = list.document.getElementsByName("check_id");
		
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id = check_ids[i].value;
			}
		}
		
		return(id);
	}
	
	//返回库区类型
	function getChkValue1(){
		var whAreaType = "";
		var check_ids = list.document.getElementsByName("check_id");
		
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				whAreaType = check_ids[i].alt;
			}
		}
		return(whAreaType);
	}
	
	//修改
	function updateData(){
		var id = getChkValue();
		if(id == ""){
			alert("请选择一条记录！");
			return;
		}
		
		var result = showModalDialog(ac + "baseWhareaAction&flag=2&whAreaId="+id, "", "dialogWidth=500px; dialogHeight=250px; scroll=no");
		if(result != null && result.length > 0){
			list.location.href = ac + "baseWhareaAction&method=ricoExecEdit" + result;
		}
	}
	
	//删除
	function deleteData(){
		var id = getChkValue();
		if(id == ""){
			alert("请选择一条记录！");
			return;
		}
		if(confirm("确认要删除该记录？")){
			list.location.href = ac + "baseWhareaAction&method=ricoExecDelete&id=" + id;
		}
	}
	
	//设置为默认收货区
	function setDefault(){
		var id = getChkValue();
		if(id == ""){
			alert("请选择一条记录！");
			return;
		}
		if(confirm("一个仓库只能设置一个默认收货区。\t\n确认要设置该库区为默认收货区？")){
			list.location.href = ac + "baseWhareaAction&method=ricoExecSetDefault&whAreaId=" + id;
		}
	}
	
	//设置隶属于库区
	function setBelongTo(){
	    var id = getChkValue();
		if(id == ""){
			alert("请选择一条记录！");
			return;
		}
		var whAreaType = getChkValue1();
		if(whAreaType!=9){
			alert("库区类型不是暂存区！");
			return;
		}
		var result = showModalDialog(ac + "baseWhareaAction&flag=3&whAreaId="+id, "", "dialogWidth=500px; dialogHeight=250px; scroll=no");
		if(result != null && result.length > 0){
			list.location.href = ac + "baseWhareaAction&method=ricoExecSetBelongTo" + result;
		}
	}
	
	function OnLoad(){
		selectObject("", "warehouseid", "1");
	}
-->
</script>
</head>

<body onload="OnLoad()"> 
<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== 当前位置 ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">当前位置：<span>基本信息 &gt;&gt; 物理库区管理</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<%if(baseWhareaQuery){%><li class="tubiao1"><a href="#" onclick="searchData()">查询</a></li><%}%>
			<%if(baseWhareaUpdate){%><li class="tubiao2"><a href="#" onclick="updateData()">修改</a></li><%}%>
			<%if(baseWhareaDelete){%><li class="tubiao3"><a href="#" onclick="deleteData()">删除</a></li><%}%>
			<%if(baseWhareaAdd){%><li class="tubiao4"><a href="#" onclick="addData()">添加</a></li><%}%>
			<%if(baseWhareaBelongTo){%><li class="tubiao2" style="width:110px;"><a href="#" onclick="setBelongTo()">设置隶属于库区</a></li><%}%>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== 查询条件 ======== -->
      <table width="99%" border="0" cellpadding="0" cellspacing="0" class="table1" align="center">
	    <tr>
	      <td class="y1" width="100" ><div align="right">所属仓库：</div></td>
	      <td class="x" width="250"><select id="warehouseid"><option value=""></option></select></td>
	      <td class="y1" width="100"><div align="right">库区名称：</div></td>
	      <td><input type="text" id="whAreaName" size="15"></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/base/wharea/base_wharea_list.jsp"
		  		width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
		  	</td>
		  </tr>
		</table>
		
	  </td>
    </tr>
  </table>  	
</div>

</body>
</html>
