<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseDepartment"%>
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
      <td class="TD_LIST_TITLE"><div class="list_title">部门编号</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">部门简称</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">部门全称</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">部门分类</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">联系人</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">联系电话</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">传真</div></td>
      <td class="TD_LIST_TITLE2"><div class="list_title">地址</div></td>
    </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0){
	
		BaseDepartment department = null; 
		
		String departmentid;		// 部门编号
	    String shortname;			// 部门简称
	    String departmentname;		// 部门全称
	    String departmenttypename;	// 部门分类名
	    String contact;				// 联系人
	    String phone;				// 联系电话
	    String fax;					// 传真
	    String address;				// 地址
	    
	    String strId = "";
		
		for(int i=0; i<ls.size(); i++){
			department = (BaseDepartment)ls.get(i); 
                        
			departmentid = department.getDepartmentid();		
			shortname = department.getShortname();	
			departmentname = department.getDepartmentname();	
			departmenttypename = department.getDepartmenttypename();		
			contact = department.getContact();
			phone = department.getPhone();
			fax = department.getFax();
			address = department.getAddress();
			
			strId = departmentid + "|"+ departmentname + "|";
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="parent.ondbClickDo('<%=strId%>');">
     <td class="TD_LIST" align="center"><input type="radio" name="check_id" class="input_checkbox" value="<%=strId%>"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=departmentid == null ? "" : departmentid%></td>
     <td class="TD_LIST" align="center"><%=shortname == null ? "" : shortname%></td>
     <td class="TD_LIST" align="center"><%=departmentname == null ? "" : departmentname%></td>
     <td class="TD_LIST" align="center"><%=departmenttypename == null ? "" : departmenttypename%></td>
     <td class="TD_LIST" align="center"><%=contact == null ? "" : contact%></td>
     <td class="TD_LIST" align="center"><%=phone == null ? "" : phone%></td>
     <td class="TD_LIST" align="center"><%=fax == null ? "" : fax%></td>
     <td class="TD_LIST" align="center"><%=address == null ? "" : address%></td>
   </tr>
<%
      	}
	}
	else{
		session.removeAttribute("commpaging");
	}
%>
   <tr>
     <td height="100%" colspan="9" class="TD_last_LIST" valign="top">
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
</body>
</html>