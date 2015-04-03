<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLot"%>
<%@ page import="java.util.List"%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
	//设置单选框是否选中
	function setSel(i){

		var check_ids = document.getElementsByName("check_id");
		check_ids[i].checked = true;
		
		//改变背景色
		for(var i=0; i<check_ids.length; i++){
			
			if(check_ids[i].checked){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
-->
</script>
</head>

<body >
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr><td valign="top" height="100%">
	
 <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE1" width="50"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">批次属性描述</div></td>
     <td class="TD_LIST_TITLE2"><div class="list_title">备注</div></td>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0)
	{
		BaseLot lot = null;
		String lotid = "";			// 批次属性ID
		String descr;				// 批次属性描述
		String remark = "";			// 备注
		
		String strId = "";
		
		for(int i=0; i<ls.size(); i++)
		{
			lot = (BaseLot)ls.get(i);
			
			lotid = lot.getM_strLotid();
			descr = lot.getM_strDescr();
			remark = lot.getM_strRemark();
			
			strId = lotid + "|"+ descr + "|";
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="parent.ondbClickDo('<%=strId%>');">
     <td class="TD_LIST1" align="center"><input type="radio" name="check_id" value="<%=strId%>" class="input_checkbox"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=descr==null ? "":descr%></td>
     <td class="TD_LIST2" align="center"><%=remark==null ? "":remark%></td>
   </tr>
<%
      	}
	}
	else{
		session.removeAttribute("commpaging");
	}
%>
   <tr>
     <td height="100%" colspan="4" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
   </table>
 <!-- ======== 分页标签 ======== -->
 </td><tr>
 <tr><td>
   <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	 <tr><td height="25px">
	   <%@ include file="commpage.jsp"%>
     </td><tr>
   </table>
 </td><tr>
</table>

</body>
</html>