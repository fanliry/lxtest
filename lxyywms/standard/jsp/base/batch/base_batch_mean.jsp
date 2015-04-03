<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseBatchmean"%>

<html>
<head>
<title>自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
	//全选
    function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var checkbox_ids = document.getElementsByName("check_id");
		
		for(var i=0; i<checkbox_ids.length; i++){
			checkbox_ids[i].checked = state;
			
			//改变背景色
			if(checkbox_ids[i].checked){
				checkbox_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				checkbox_ids[i].parentNode.parentNode.style.backgroundColor = "";
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
		}
		
	}
-->
</script>
</head>
<body>

  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr>
      <td class="TD_LIST_TITLE1" width="50"><div class="list_title">
        <input type="checkbox" name="check_all" class="input_checkbox" onClick="CheckAll()">行号</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">批次意义</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">开始位置</div></td>
      <td class="TD_LIST_TITLE2"><div class="list_title">结束位置</div></td>
    </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls!=null)
	{
		BaseBatchmean batchmean = null;
		String batchmeanid;	//批次意义ID
		//String batchid;	//批次ID
		String mean;		//意义
		Integer startpos;	//开始位置
		Integer endpos;		//结束位置
		
		for(int i=0; i<ls.size(); i++)
		{
			batchmean = (BaseBatchmean)ls.get(i);
			batchmeanid = batchmean.getBatchmeanid();
			mean = batchmean.getMean();
			startpos = batchmean.getStartpos();
			endpos = batchmean.getEndpos();
%>   
   <tr onmouseover="this.bgColor='#E2E8EA'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)">
     <td class="TD_LIST1" align="center"><input type="checkbox" name="check_id" value="<%=batchmeanid%>" onclick="setSel(<%=i%>)"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=mean==null?"":mean%></td>
     <td class="TD_LIST" align="center"><%=startpos%></td>
     <td class="TD_LIST2" align="center"><%=endpos%></td>
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