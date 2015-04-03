<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean basewhareavirtualAdd = false;    //添加
	boolean basewhareavirtualDelete = false; //删除
	boolean basewhareavirtualUpdate = false; //修改
	boolean basewhareavirtualQuery = false;  //查询
	boolean basewhareavirtualSetDef = false;  //设置隶属于物理库区
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("basewhareavirtualAdd") != null){
			basewhareavirtualAdd = true;
		}
		if(hsPopedom.get("basewhareavirtualDelete") != null){
			basewhareavirtualDelete = true;
		}
		if(hsPopedom.get("basewhareavirtualUpdate") != null){
			basewhareavirtualUpdate = true;
		}
		if(hsPopedom.get("basewhareavirtualSetDef") != null){
			basewhareavirtualSetDef = true;
		}
		if(hsPopedom.get("basewhareavirtualQuery") != null){
			basewhareavirtualQuery = true;
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
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/whareaVirtual/base_whareavirtual_add.jsp",'','dialogWidth=500px;dialogHeight=250px');
	   	if(result != null && result.length > 1){
	   		list.location.href = ac + "baseWhareaVirtualAction&method=ricoExecAdd" + result;
	   	}
	}
	
	//查询
	function searchData(){
		var warehouseid = document.getElementById("warehouseid").value;
		var whAreaName = document.getElementById("whAreaName").value;
		
		var condition = "&warehouseid=" + warehouseid + "&whAreaName=" + whAreaName;
		
		list.location.href = ac + "baseWhareaVirtualAction&flag=1" + condition;
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
		
		var result = showModalDialog(ac + "baseWhareaVirtualAction&flag=2&whAreaId="+id, "", "dialogWidth=500px; dialogHeight=250px; scroll=no");
		if(result != null && result.length > 0){
			list.location.href = ac + "baseWhareaVirtualAction&method=ricoExecEdit" + result;
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
			list.location.href = ac + "baseWhareaVirtualAction&method=ricoExecDelete&id=" + id;
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
			list.location.href = ac + "baseWhareaVirtualAction&method=ricoExecSetDefault&whAreaId=" + id;
		}
	}
	
	//设置隶属于库区
	function setBelongTo(){
	    var id = getChkValue();
		if(id == ""){
			alert("请选择一条记录！");
			return;
		}
		var result = showModalDialog(ac + "baseWhareaVirtualAction&flag=3&whAreaId="+id, "", "dialogWidth=500px; dialogHeight=250px; scroll=no");
		if(result != null && result.length > 0){
			list.location.href = ac + "baseWhareaVirtualAction&method=ricoExecSetBelongTo" + result;
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
		<div id="dqwz" class="f_l">当前位置：<span>基本信息 &gt;&gt; 虚拟库区管理</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<%if(basewhareavirtualQuery){%><li class="tubiao1"><a href="#" onclick="searchData()">查询</a></li><%}%>
			<%if(basewhareavirtualUpdate){%><li class="tubiao2"><a href="#" onclick="updateData()">修改</a></li><%}%>
			<%if(basewhareavirtualDelete){%><li class="tubiao3"><a href="#" onclick="deleteData()">删除</a></li><%}%>
			<%if(basewhareavirtualAdd){%><li class="tubiao4"><a href="#" onclick="addData()">添加</a></li><%}%>
			<%if(basewhareavirtualSetDef){%><li class="tubiao2" style="width:137px;"><a href="#" onclick="setBelongTo()">设置隶属于物理库区</a></li><%}%>
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
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/base/whareaVirtual/base_whareavirtual_list.jsp"
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
