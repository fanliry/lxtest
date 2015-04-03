<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseDepartment" %>
<html>
<head>
<title>部门信息</title>
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
	
	//修改双击行
	function upd(i){

		var check_ids = document.getElementsByName("check_id");
		var id = check_ids[i].value;
		var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";

		var result = showModalDialog(ac + "baseDepartmentAction&flag=2&id="+id, "", "dialogWidth=550px; dialogHeight=250px; scroll=no");
		if(result != null && result.length > 0){
			location.href = ac + "baseDepartmentAction&method=ricoExecEdit" + result;
		}
	}
-->
</script>
</head>

<body>
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
      <!--td class="TD_LIST_TITLE"><div class="list_title">是否启用</td-->
    </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null){
		BaseDepartment department = null; 
		
		String departmentid;		// 部门编号
	    String shortname;			// 部门简称
	    String departmentname;		// 部门全称
	    String departmenttypename;	// 部门分类名
	    String contact;				// 联系人
	    String phone;				// 联系电话
	    String fax;					// 传真
	    String address;				// 地址
	    //String useflag;				// 是否启用

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
			//useflag = depatment.getUseflag();
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="upd(<%=i%>)">
     <td class="TD_LIST" align="center"><input type="radio" name="check_id" class="input_checkbox" value="<%=departmentid%>"><%=i+1%></td>
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
%>
   <tr>
     <td height="100%" colspan="10" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
   
 </table>
</body>
</html>