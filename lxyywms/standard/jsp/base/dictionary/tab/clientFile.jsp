<%@ page contentType="text/html; charset=GB2312"%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/load.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = "";
	//增加
	function Add(){
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/dictionary/tab/customer_add.jsp", "", "dialogWidth=600px; dialogHeight=300px; scroll=no");
		if(result != null && result.length > 1){
			list.location.href = ac + "customerMgrAction&methoder=add&flag=1" + result;
		}
	}
	//删除
	function Delete(){
		var ids = "";
		var check_is = list.document.getElementsByName("check_id");
		for(var i=0; i<check_is.length; i++){
			if(check_is[i].checked){
				ids += check_is[i].value + ",";
			}
		}
		if(ids == ""){
			alert("删除时至少选择一条记录！");
			return;
		}
		condition = "&ids=" + ids;
		if(confirm("你确定删除所选记录？")){
			list.location.href = ac + "customerMgrAction&methoder=delete&flag=1" + condition;
		}
	}
	//修改
	function Update(){
		var id = null;
		var k = 0;
		var check_is = list.document.getElementsByName("check_id");
		for(var i=0; i<check_is.length; i++){
			if(check_is[i].checked){
				id = check_is[i].value;
				k++;
			}
		}
		if(k != 1){
			alert("修改时只能且必须选择一条记录！");
			return;
		}
		
		var result = showModalDialog(ac + "customerMgrAction&methoder=search&flag=6&customer_id=" + id , "", "dialogWidth=600px; dialogHeight=300px; scroll=no");
		if(result != null && result.length > 0){
			list.location.href = ac + "customerMgrAction&methoder=update&flag=1" + result;
		}
	}
	//查询
	function Search(){
		var short_name = document.getElementById("short_name").value;
		var out_id = document.getElementById("out_id").value;
		
		condition = "&short_name=" + short_name + "&out_id=" + out_id;
		
		Loading();
		
		list.location.href = ac + "customerMgrAction&methoder=search&flag=5" + condition;
	}
-->
</script>
</head>

<body>

 <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td>
     
 <table width="98%" height="25" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td width="98%" class="font_006699_bold_12">当前位置：基本信息 -&gt; 字典中心 -&gt; 客户档案</td>
   </tr>
 </table>
 
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_MGR_TOP">
   <tr>
     <td class="TD_MGR_TOP" align="right" width="100">客户简称：</td>
     <td class="TD_MGR_TOP" width="150"><input type="text" name="short_name" size="15"></td>
     <td class="TD_MGR_TOP" align="right" width="100">双汇编号：</td>
     <td class="TD_MGR_TOP"><input type="text" name="out_id" size="15"></td>
   </tr>
   <tr>
     <td class="TD_MGR_TOP" align="right" colspan="6">
       <input type="button" value="新增" class="BUTTON_STYLE1" onclick="Add()">
       <!-- 
       <input type="button" value="删除" class="BUTTON_STYLE1" onclick="Delete()">
       -->
       <input type="button" value="修改" class="BUTTON_STYLE1" onclick="Update()">
       <input type="button" value="查询" class="BUTTON_STYLE1" onclick="Search()">
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
     <td height="100%">
       <iframe id="list" name="list" width="100%" height="100%" frameborder="0" scrolling="no"
  		   		src="<%=request.getContextPath()%>/standard/jsp/base/dictionary/tab/customer_list.jsp"></iframe>
     </td>
   </tr>
   <tr>
     <td height="25">
       <iframe id="page" name="page" width="100%" height="100%" frameborder="0" scrolling="no"
  		   		src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=30&flag=N"></iframe>
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