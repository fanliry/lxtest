<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.rule.RuleAllocationDetail"%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
  <!--
	//全选
	function CheckAll(){
		
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		
		for(var i=0; i<check_ids.length; i++){
		
			if(!check_ids[i].disabled){
			
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
	}
	
	//设置多选框是否选中
	function setSel(i){

		var check_ids = document.getElementsByName("check_id");
		
		if(!check_ids[i].disabled){
			if(check_ids[i].checked){
				check_ids[i].checked = false;
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}else{
				check_ids[i].checked = true;
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
		}
	}
  -->
</script>
</head>

<body>
  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
	<tr>
	  <td class="TD_LIST_TITLE1" width="30px"><div class="list_title">
	  	<input type="checkbox" name="check_all" onclick="CheckAll();" class="input_checkbox" value="checkbox"></div></td>
	  <td width="60px" class="TD_LIST_TITLE"><div class="list_title">优先顺位</div></td>
	  <td width="60px" class="TD_LIST_TITLE"><div class="list_title">有效</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">规则方法</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">目标库区</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">目标库位</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">目标巷道</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">计量单位</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">清仓</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">拆零</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">拣货位超量分配</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">自动产生补货任务</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">存储位拣选</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">拆包</div></td>
	  <td class="TD_LIST_TITLE2"><div class="list_title">部分分配</div></td>
	</tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0){
		
  	 	for(int i = 0; i < ls.size(); i++) {
  	 	
  	 		RuleAllocationDetail allocationDetail = (RuleAllocationDetail)ls.get(i);  
  	 		
  	 		String allocationid = allocationDetail.getAllocationid(); 			//分配规则ID
  	 	    String allocationdetailid = allocationDetail.getAllocationdetailid(); 	//分配规则详细ID
  	 	    int sort = allocationDetail.getSort();								//优先顺位
  	 	    String enableflag = allocationDetail.getEnableflag();				//有效
  	 	    String ruleconfigname = allocationDetail.getRuleconfigname(); 		//规则配置名称
  	 	    String tozone = allocationDetail.getTozonename();					//目标库区
  	 	    String tolocation = allocationDetail.getTolocationname();			//目标库位
  	 	    String toalleys = allocationDetail.getToalleys();					//目标巷道，可多条
  	 	    String unit = allocationDetail.getUnitflagname();					//计量单位
  	 	    String clearloc_flag = allocationDetail.getClearloc_flag();			//清仓
  	 	    String apart_flag = allocationDetail.getApart_flag();				//拆零
  	 	    String over_flag = allocationDetail.getOver_flag();					//拣货位超量分配
  	 	    String auto_flag = allocationDetail.getAuto_flag();					//自动产生补货任务
  	 	    String bulkpick_flag = allocationDetail.getBulkpick_flag();			//存储位拣选
  	 	    String part_flag = allocationDetail.getPart_flag();					//拆包
  	 	    String part_allocation_flag = allocationDetail.getPart_allocation_flag();//部分分配
  	 	    
  	 	    String param = allocationdetailid + "|" + allocationid;
%>
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="parent.updateDetailData();">
	  <td class="TD_LIST1" align="center">
     	<input type="checkbox" name="check_id" value="<%=param%>" class="input_checkbox" onclick="setSel(<%=i%>);" <%if(allocationid.equals("STANDARD")){%>disabled<%}%>></td>
      <td class="TD_LIST" align="center"><%=sort%></td>
	  <td class="TD_LIST" align="center"><%=enableflag == null ? "" : enableflag%></td>
	  <td class="TD_LIST" align="center"><%=ruleconfigname == null ? "" : ruleconfigname%></td>
	  <td class="TD_LIST" align="center"><%=tozone == null ? "" : tozone%></td>
	  <td class="TD_LIST" align="center"><%=tolocation == null ? "" : tolocation%></td>
	  <td class="TD_LIST" align="center"><%=toalleys == null ? "" : toalleys%></td>
	  <td class="TD_LIST" align="center"><%=unit == null ? "" : unit%></td>
	  <td class="TD_LIST" align="center"><%=clearloc_flag == null ? "" : clearloc_flag%></td>
	  <td class="TD_LIST" align="center"><%=apart_flag == null ? "" : apart_flag%></td>
	  <td class="TD_LIST" align="center"><%=over_flag == null ? "" : over_flag%></td>
	  <td class="TD_LIST" align="center"><%=auto_flag == null ? "" : auto_flag%></td>
	  <td class="TD_LIST" align="center"><%=bulkpick_flag == null ? "" : bulkpick_flag%></td>
	  <td class="TD_LIST" align="center"><%=part_flag == null ? "" : part_flag%></td>
	  <td class="TD_LIST2" align="center"><%=part_allocation_flag == null ? "" : part_allocation_flag%></td>
	</tr>
<%
 		}	
	 }
%>
   <tr>
     <td height="100%" colspan="15" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
 </table>

</body>
</html>
