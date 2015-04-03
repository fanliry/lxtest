<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.lang.reflect.Field" %>
<html>
<head>
<title>自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
	//改变背景
    function Change(obj){
		if(obj.checked){
			obj.parentNode.parentNode.style.backgroundColor = "#EFEFEF";
		}
		else{
			obj.parentNode.parentNode.style.backgroundColor = "";
		}
	}
	//全选
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
		}
	}
	//页面登录
	function OnLoad(){
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			if(back_msg == "Y"){
				alert("操作成功！");
			}
			else{
				alert(back_msg);
			}
		}
		
	}
-->
</script>
</head>
<body onload="OnLoad()">
 <table id="tb" width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
	 <td  class="TD_LIST_TITLE1" width="50"><div class="list_title">
       <input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">行号
     </div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">库区</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">品号</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">规格</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">单位</div></td>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title">数量</div></td>
   </tr>
   <tr>
     <td height="100%" colspan="<%=5 %>" valign="top" class="TD_last_LIST">
       <div style="color: red; font-size:12; margin:5px;"></div>
     </td>
   </tr>
 </table>
</body>
</html>