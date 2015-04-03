<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.rule.RuleAllocation" %>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
  <!--
  	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
  	var condition = "";
  	
	//全选
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
			
			//改变背景色
			if(check_ids[i].checked){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	
	//设置多选框是否选中
	function setSel(i){

		var check_ids = document.getElementsByName("check_id");
		if(check_ids[i].checked){
			check_ids[i].checked = false;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "";
		}else{
			check_ids[i].checked = true;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			
			var array = check_ids[i].value.split("|");
			queryDetail(array[0]);
		}
	}
	
	//查询详细
	function queryDetail(id){
	
		condition = "&flag=2" + "&allocationid=" + id;
		parent.detail.location.href = ac + "allocationRuleAction" + condition;
	}
  -->
</script>
</head>

<body>

  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
	<tr>
	  <td class="TD_LIST_TITLE1" width="50px"><div class="list_title">
	  	<input type="checkbox" name="check_all" onclick="CheckAll();" class="input_checkbox" value="checkbox">行号</div></td>
	  <td class="TD_LIST_TITLE" nowrap><div class="list_title">分配规则描述</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">所属仓库</div></td>
	  <td class="TD_LIST_TITLE2" nowrap><div class="list_title">备注</div></td>
	</tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0){
	
	  	for(int i = 0; i < ls.size(); i++){
	  	
	  	 	RuleAllocation allocation = (RuleAllocation)ls.get(i); 

	  	 	    String allocationid = allocation.getAllocationid(); //分配规则ID
	  	 	    String descr = allocation.getDescr();     			//描述
	  	 	    String warehouseid = allocation.getWarehouseid();  //所属仓库ID
  	 	    	String warehousename = allocation.getWarehousename();  //所属仓库名
	  	 		String remark = allocation.getRemark();		// 备注
	  	 		
	  	 		String param = allocationid + "|" + warehouseid;
%>
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="parent.ondbClickDo('<%=allocationid%>');">
	  <td class="TD_LIST1" align="center">
     	<input type="checkbox" name="check_id" value="<%=param%>" class="input_checkbox" onclick="setSel(<%=i%>)"><%=i+1%></td>
      <td class="TD_LIST" align="center"><%=descr == null ? "" : descr%></td>
	  <td class="TD_LIST" align="center"><%=warehousename == null ? "" : warehousename%></td>
	  <td class="TD_LIST2" align="center"><%=remark == null ? "" : remark%></td>
	</tr>
<%
		}
	}
%>
   <tr>
     <td height="100%" colspan="4" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
 </table>

</body>
</html>
