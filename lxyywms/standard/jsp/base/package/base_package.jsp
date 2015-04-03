<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript">
  <!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = "";
	
	//查询
	function queryData(){
	
		var pkgdesc = document.getElementById("pkgdesc").value;
		var linerow = page.document.getElementById("lineviewrow").value;	//每页显示行数
		condition = "&flag=1" + "&pkgdesc=" + pkgdesc + "&linerow=" + linerow;
		list.location.href = ac + "basePackageAction" + condition;
	}
	
	//增加
	function addData(){
	
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/package/base_package_add.jsp", 
			'', 'dialogWidth=850px;dialogHeight=500px');
	   		
	   	if(result != null && result.length > 1)
	   	{
	   		var linerow = page.document.getElementById("lineviewrow").value;	//每页显示行数
	   		condition = "&method=ricoExecAdd" + result + "&linerow=" + linerow;
	   		list.location.href = ac + "basePackageAction" + condition;
	   	}
	   	condition = "";
	}
	
	//删除信息
	function delData()
	{
		var packids = "";
		var checkbox_ids = window.list.document.getElementsByName("check_id");
		for(var i=0; i<checkbox_ids.length; i++)
		{
			if(checkbox_ids[i].checked)
			{
				var id = checkbox_ids[i].value;
				packids += id + ",";
			}
		}
		if(packids.length <1)
		{
			alert("请选择您要删除的数据项!");
		  	return false;
		}
		else
		{
			condition = "&method=ricoExecDelete" + "&ids="+packids;
			if(confirm("确定删除？"))
			{
				var linerow = page.document.getElementById("lineviewrow").value;	//每页显示行数
				list.location.href = ac + "basePackageAction" + condition + "&linerow=" + linerow;
			}
		}
		return true;
	}
	
	//修改
	function updateData(){
	
		if(updateAble())
		{
			var checkbox_ids = window.list.document.getElementsByName("check_id");
			for(var i=0; i<checkbox_ids.length; i++)
			{
				if(checkbox_ids[i].checked)
				{
					var id = checkbox_ids[i].value;
					condition = "&packageid=" + id;
					break;
				}
			}
			var result = showModalDialog(ac+"basePackageAction&flag=2"+condition,'','dialogWidth=850px;dialogHeight=500px');
		   	if(result != null && result.length > 1)
		   	{
		   		var linerow = page.document.getElementById("lineviewrow").value;	//每页显示行数
		   		condition = "&method=ricoExecEdit" + result + "&linerow=" + linerow;
		   		list.location.href = ac + "basePackageAction" + condition;
		   	}
	   	}
	}
	
	//双击
	function ondbClickDo(strVar){

		condition = "&packageid=" + strVar;

		var result = showModalDialog(ac+"basePackageAction&flag=2"+condition,'','dialogWidth=850px;dialogHeight=500px');
		if(result != null && result.length > 1)
		{
			var linerow = page.document.getElementById("lineviewrow").value;	//每页显示行数
			condition = "&method=ricoExecEdit" + result + "&linerow=" + linerow;
			list.location.href = ac + "basePackageAction" + condition;
		}
	}
	
	//修改数据时调用
	function updateAble(){
	 	var icount = getCheckCount();
		if(icount<1){
			alert("请选择您要修改的数据项!");
		  	return false;
		}
		if(icount>1){
		 	alert("修改时只能选择一行数据!");
		 	return false;
		}
		return true;
	}
	
	//获取复选框个数
	function getCheckCount()   
  	{   
  		var counter = 0;
  		var checkbox_ids = window.list.document.getElementsByName("check_id");
  		for(var i=0; i<checkbox_ids.length; i++)
  		{
	  		if(checkbox_ids[i].checked)
	  		{
	  			counter = counter + 1;
	  		}
  		}
  		return counter;
	}
	
-->
</script>
</head>
<body>

<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== 当前位置 ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">当前位置：<span>规则管理 &gt;&gt; 包装规则</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<li class="tubiao1"><a href="#" onclick="queryData()">查询</a></li>
			<li class="tubiao2"><a href="#" onclick="updateData()">修改</a></li>
			<li class="tubiao3"><a href="#" onclick="delData()">删除</a></li>
			<li class="tubiao4"><a href="#" onclick="addData()">添加</a></li>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== 查询条件 ======== -->
      <table width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
	    <tr>
	      <td class="y1" width="100" align="right">包装描述：</td>
	      <td><input type="text" id="pkgdesc" size="25" maxlength="50"></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/base/package/base_package_list.jsp" 
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
 
</body>
</html>
