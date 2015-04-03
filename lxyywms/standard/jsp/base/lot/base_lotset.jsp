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
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript">
  <!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = "";
	
	//查询
	function queryData(){

		left.location.href = ac + "baseLotSetAction&flag=1";
	}
	
	//增加
	function addData(){
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/lot/base_lotset_add.jsp",'',
			'dialogWidth=650px;dialogHeight=500px');
	   	if(result != null && result.length > 1){
	   		left.location.href= ac + "baseLotSetAction&method=ricoExecAdd" + result;
	   		right.location.href = "<%=request.getContextPath()%>/standard/jsp/base/lot/base_lotset_detail.jsp";
	   	}
	}
	
	//修改
	function updateData(){
	
		var id = getChkValue();
		if(id == ""){
			alert("请选择一条记录！");
			return;
		}
		
		var result = showModalDialog(ac + "baseLotSetAction&flag=3&lottype="+id, "", "dialogWidth=650px;dialogHeight=500px;");
		if(result != null && result.length > 0){
			left.location.href = ac + "baseLotSetAction&method=ricoExecEdit" + result;
			right.location.href = "<%=request.getContextPath()%>/standard/jsp/base/lot/base_lotset_detail.jsp";
		}
	}
	
	//删除
	function delData(){
	
		var id = getChkValue();
		if(id == ""){
			alert("请选择一条记录！");
			return;
		}
		if(confirm("确认要删除该记录？")){
			left.location.href = ac + "baseLotSetAction&method=ricoExecDelete&lottype=" + id;
			right.location.href = "<%=request.getContextPath()%>/standard/jsp/base/lot/base_lotset_detail.jsp";
		}
	}
	
	//返回选中值
	function getChkValue(){
		var id = "";
		var check_ids = left.document.getElementsByName("check_id");
		
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id = check_ids[i].value;
			}
		}
		return(id);
	}

	//页面加载
	function OnLoad(){
		queryData();
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
		<div id="dqwz" class="f_l">当前位置：<span>基本信息 &gt;&gt; 批次设置</span></div>
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
    <tr>
      <td valign="top" height="100%">
		
		<table width="99%" height="100%" border="0" align="center" cellpadding="5" cellspacing="0">
		  <tr>
		    <td width="40%">
	          <iframe id="left" src="<%=request.getContextPath()%>/standard/jsp/base/lot/base_lotset_list.jsp" 
		  		width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
		  	</td>
		  	<td width="60%">
	          <iframe id="right" src="<%=request.getContextPath()%>/standard/jsp/base/lot/base_lotset_detail.jsp" 
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