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
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/carton/base_carton_add.jsp",'',
			'dialogWidth=500px;dialogHeight=350px');
	   	if(result != null && result.length > 1){
	   		var linerow = page.document.getElementById("lineviewrow").value;	//每页显示行数	
	   		list.location.href = ac + "baseCartonAction&method=ricoExecAdd" + result + "&linerow=" + linerow;
	   	}
	}
	
	//查询
	function searchData()
	{
		var cartonid = document.getElementById("cartonid").value;
		var linerow = page.document.getElementById("lineviewrow").value;	//每页显示行数	
		var condition = "&cartonid=" + cartonid + "&linerow=" + linerow;
		
		list.location.href = ac + "baseCartonAction&flag=1" + condition;
	}
	
	//返回选中值
	function getChkValue(){
		var id = "";
		var check_ids = list.document.getElementsByName("check_id");
		
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id += check_ids[i].value + ",";
			}
		}
		
		return(id);
	}
	
	//修改
	function updateData(){
		var k = 0;
		var id = "";
		var check_ids = list.document.getElementsByName("check_id");
		
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				k++;
				id = check_ids[i].value
			}
		}
		if(k != 1){
			alert("请选择一条记录！");
			return;
		}
		
		var result = showModalDialog(ac + "baseCartonAction&flag=2&cartonid="+id, "", "dialogWidth=500px; dialogHeight=350px; scroll=no");
		if(result != null && result.length > 0){
			var linerow = page.document.getElementById("lineviewrow").value;	//每页显示行数	
			list.location.href = ac + "baseCartonAction&method=ricoExecEdit" + result + "&linerow=" + linerow;
		}
	}
	
	//删除
	function deleteData(){
		var ids = getChkValue();
		if(ids == ""){
			alert("请至少选择一条记录！");
			return;
		}
		if(confirm("确认要删除该记录？")){
			var linerow = page.document.getElementById("lineviewrow").value;	//每页显示行数	
			list.location.href = ac + "baseCartonAction&method=ricoExecDelete&ids=" + ids.substring(0, ids.length-1) + "&linerow=" + linerow;
		}
	}
	
	//打印标签
	function printData(){
	
		var k = 0;
		var id = "";
		var check_ids = list.document.getElementsByName("check_id");
		
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				k++;
				id = check_ids[i].value
			}
		}
		if(k != 1){
			alert("请选择一条记录！");
			return;
		}
		
		var strUrl = "<%=request.getContextPath()%>/standard/jsp/base/carton/base_carton_label.jsp?CartonId="+id;
		showModalDialog(strUrl,'','dialogWidth=350px;dialogHeight=240px');
		
		//var result = showModalDialog(strUrl,'','dialogWidth=350px;dialogHeight=240px');
		//if(result != null && result.length > 1){
		
		//  	myIframe.location.href="<%=request.getContextPath()%>/"+result;
		//   	window.close();
		//}
	}
	
	function OnLoad(){

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
		<div id="dqwz" class="f_l">当前位置：<span>基本信息 &gt;&gt; 周转箱</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<li class="tubiao1"><a href="#" onclick="searchData()">查询</a></li>
			<li class="tubiao2" style="width:85px;"><a href="#" onclick="printData()">打印标签</a></li>
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
      <table width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center">
	    <tr>
	      <td class="y1" width="100"><div align="right">装箱代码：</div></td>
	      <td><input type="text" id="cartonid" size="25"></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/base/carton/base_carton_list.jsp" 
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
