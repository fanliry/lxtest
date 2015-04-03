<%@ page contentType="text/html; charset=GBK"%>
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
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/department/base_department_add.jsp",'',
			'dialogWidth=550px;dialogHeight=250px');
			
	   	if(result != null && result.length > 1){
	   		list.location.href = ac + "baseDepartmentAction&method=ricoExecAdd" + result;
	   	}
	}
	
	//查询
	function searchData()
	{
		var departmentname = document.getElementById("departmentname").value;
		var departmenttype = document.getElementById("departmenttype").value;
		
		var condition = "&departmentname=" + departmentname + "&departmenttype=" + departmenttype;
		
		list.location.href = ac + "baseDepartmentAction&flag=1" + condition;
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
	
	//修改
	function updateData(){
		var id = getChkValue();
		if(id == ""){
			alert("请选择一条记录！");
			return;
		}
		
		var result = showModalDialog(ac + "baseDepartmentAction&flag=2&id="+id, "", "dialogWidth=550px; dialogHeight=250px; scroll=no");
		if(result != null && result.length > 0){
			list.location.href = ac + "baseDepartmentAction&method=ricoExecEdit" + result;
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
			list.location.href = ac + "baseDepartmentAction&method=ricoExecDelete&id=" + id;
		}
	}
	
	function OnLoad(){
		var typevalue = "bmlx";		//部门类型的值	
		selectType("", "departmenttype", typevalue);
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
		<div id="dqwz" class="f_l">当前位置：<span>基本信息 &gt;&gt; 部门管理</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<li class="tubiao1"><a href="#" onclick="searchData()">查询</a></li>
			<li class="tubiao2"><a href="#" onclick="updateData()">修改</a></li>
			<li class="tubiao3"><a href="#" onclick="deleteData()">删除</a></li>
			<li class="tubiao4"><a href="#" onclick="addData()">添加</a></li>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== 查询条件 ======== -->
      <table width="99%" border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
	    <tr>
	      <td class="y1" width="100" ><div align="right">部门名称：</div></td>
	      <td class="x" width="250"><input type="text" id="departmentname" size="25"></td>
	      <td class="y1" width="100"><div align="right">部门分类：</div></td>
	      <td><select id="departmenttype"><option value=""></option></select></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/base/department/base_department_list.jsp"
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
