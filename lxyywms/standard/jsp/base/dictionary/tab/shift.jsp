<%@ page contentType="text/html; charset=GB2312"%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/table.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/load.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/load.js"></script>
<script>
<!--
	var ac = "<%=request.getContextPath()%>/rmsService?actionCode=";
	var condition = null;
    //获取用户名
	function GetUser(){
		var result = showModalDialog('<%=request.getContextPath()%>/jsp/dictionary/tab/chooseUser/popup.jsp','','dialogWidth=700px;dialogHeight=400px');
		if(result != null && result.length > 0){
	 		var tem = result.split("|");
	 		document.getElementById("op_man_id").value = tem[0];
	 		document.getElementById("op_man_name").value = tem[2];
		}
	}
	//增加  
	function Add(){
		var result = showModalDialog("<%=request.getContextPath()%>/jsp/dictionary/tab/shift_add.jsp",'','dialogWidth=300px;dialogHeight=200px');
		if(result != null && result.length > 1){
			list.location.href = ac + "addtbjobnumber" + result;
		}
	}
	//查询
	function Search(){
		var op_man_id = document.getElementById('op_man_id').value;
		var in_out_type = document.getElementById('in_out_type').value;
		
		condition = "&op_man_id=" + op_man_id + "&in_out_type=" + in_out_type;
		
		list.location.href = ac + "ShiftAction&method=search&flag=11" + condition;	
	}
	//删除
	function Delete(){     
		var ids = "";
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				ids += check_ids[i].value + ",";
			}
		}
		if(ids == ""){
			alert("请选择要删除的记录！");
			return;
		}
		if(confirm("您确定删除所选记录？")){
			list.location.href = ac + "ShiftAction&method=delete&ids=" + ids.substring(0, ids.length);
		}
	}
	//修改
	function Update(){
		var id = "";
		var k = 0;
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id = check_ids[i].value;
				k++;
			}
		}
		if(k != 1){
			alert("修改时只能选择且必须选择一条记录！");
			return;
		}
		var result = showModalDialog(ac + "ShiftAction&method=search&flag=7&shift_id=" + id,'','dialogWidth=300px;dialogHeight=200px');
		if(result != null && result.length > 1){
			list.location.href = ac + "ShiftAction&method=update&flag=1" + result;
		}		
	}
-->
</script>
</head>

<body>

 <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td>

 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25" class="font_006699_bold_12">当前位置：基本信息 -&gt; 字典中心 -&gt; 班次管理</td>
   </tr>
 </table>
 
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_MGR_TOP">
   <tr>
     <td class="TD_MGR_TOP" width="100" align="right">负责人： </td>
     <td class="TD_MGR_TOP" width="150">
       <input name="op_man_id" type="hidden"><input name="op_man_name" type="text" size="16" value="" readonly>
       <input name="moreBtn" type="button" class="button_select" value="…" onClick="GetUser()">
     </td>
     <td class="TD_MGR_TOP" width="100" align="right">入出类型：</td>
     <td class="TD_MGR_TOP">
       <select name="in_out_type"><option value="">--请选择--</option>
         <option value="1">入库</option><option value="2">出库</option>
       </select>
     </td>
   </tr>
   <tr>
     <td colspan="4" class="TD_MGR_TOP" align="right">
       <input type="button" value="添加" class="BUTTON_STYLE1" onclick="Add()">
       <input type="button" value="查询" class="BUTTON_STYLE1" onclick="Search()">
       <input type="button" value="修改" class="BUTTON_STYLE1" onclick="Update()">
       <input type="button" value="删除" class="BUTTON_STYLE1" onclick="Delete()">
     </td>
   </tr>
 </table>
 
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="5"></td>
   </tr>
 </table>
 
     </td>
   </tr>
   <tr>
     <td height="100%"> 
     
 <table width="98%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
   <tr>
     <td valign="top">
       <iframe id="list" name="list" width="100%" height="100%" frameborder="0" scrolling="no"
  		   		src="<%=request.getContextPath()%>/jsp/dictionary/tab/shift_list.jsp"></iframe>
     </td>
   </tr>
   <tr>
     <td height="25">
       <iframe id="page" name="page" width="100%" height="100%" frameborder="0" scrolling="no"
  		   		src="<%=request.getContextPath()%>/jsp/page/page2.jsp"></iframe>
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

