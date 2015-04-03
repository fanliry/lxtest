<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.rule.RulePutawayDetail"%>
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
	  <td width="60px" class="TD_LIST_TITLE"><div class="list_title">是否有效</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">规则方法</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">目标库区</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">目标库位</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">库位限制</div></td>
	  <td class="TD_LIST_TITLE"><div class="list_title">库位使用</div></td>
	  <td class="TD_LIST_TITLE2"><div class="list_title">存储类型</div></td>
	</tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0){
		
  	 	for(int i = 0; i < ls.size(); i++) {
  	 	
  	 		RulePutawayDetail putawayDetail = (RulePutawayDetail)ls.get(i);  
  	 		
  	 		String putawayid = putawayDetail.getPutawayid(); 				//上架规则ID
  	 	    String putawaydetailid = putawayDetail.getPutawaydetailid(); 	//上架规则详细ID
  	 	    int sort = putawayDetail.getSort();								//优先顺位
  	 	    String enableflag = putawayDetail.getEnableflag();				//有效
  	 	    String ruleconfigname = putawayDetail.getRuleconfigname(); 		//规则配置名称
  	 	    String tozone = putawayDetail.getTozonename();					//目标库区
  	 	    String tolocation = putawayDetail.getTolocationname();			//目标库位
  	 	    String loc_restrict = putawayDetail.getLoc_restrictname();		//库位限制
  	 	    String loc_usage1 = putawayDetail.getLoc_usagename1();			//库位使用
  	 	    String loc_handle1 = putawayDetail.getLoc_handlename1();		//存储类型
  	 	    
  	 	    String param = putawaydetailid + "|" + putawayid;
%>
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="parent.ondbClickDoDetail('<%=putawaydetailid%>');">
	  <td class="TD_LIST1" align="center">
     	<input type="checkbox" name="check_id" value="<%=param%>" class="input_checkbox" onclick="setSel(<%=i%>);" <%if(putawayid.equals("STANDARD")){%>disabled<%}%>></td>
      <td class="TD_LIST" align="center"><%=sort%></td>
	  <td class="TD_LIST" align="center"><%=enableflag == null ? "" : enableflag%></td>
	  <td class="TD_LIST" align="center"><%=ruleconfigname == null ? "" : ruleconfigname%></td>
	  <td class="TD_LIST" align="center"><%=tozone == null ? "" : tozone%></td>
	  <td class="TD_LIST" align="center"><%=tolocation == null ? "" : tolocation%></td>
	  <td class="TD_LIST" align="center"><%=loc_restrict == null ? "" : loc_restrict%></td>
	  <td class="TD_LIST" align="center"><%=loc_usage1 == null ? "" : loc_usage1%></td>
	  <td class="TD_LIST2" align="center"><%=loc_handle1 == null ? "" : loc_handle1%></td>
	</tr>
<%
 		}	
	 }
%>
   <tr>
     <td height="100%" colspan="11" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
 </table>
<FORM action="" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
