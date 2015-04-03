<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>浙江刚玉物流仓库管理系统</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script>	
	//全选
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
		}
	}
	
	function OnLoad(){
	 var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
	 if(back_msg != ""){
	    alert(back_msg);
	  }
	}

</script>
</head>
<body   onload="javascript:OnLoad();">
 <table id="ty" width="100%" height="100%"   border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr height="10px">
     <td width="50" class="TD_LIST_TITLE"align="center"><div class="list_title">
       <input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">行号
     </div></td>
     <td class="TD_LIST_TITLE" ><div class="list_title">库区</div></td>
     <td class="TD_LIST_TITLE" ><div class="list_title" align="center">货位</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">物品名称</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">入库日期</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">物品状态</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">单位</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">抽检数量</div></td>
   </tr>
   <tr></tr>
 </table>

</body>
</html>
