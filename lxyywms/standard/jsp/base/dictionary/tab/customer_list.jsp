<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.base.ClientFile"%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
<!--
	//全选
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
			if(state){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#EFEFEF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	//改变背景
    function Change(obj){
		if(obj.checked){
			obj.parentNode.parentNode.style.backgroundColor = "#EFEFEF";
		}
		else{
			obj.parentNode.parentNode.style.backgroundColor = "";
		}
	}
	//页面登录
	function OnLoad(){
		parent.RemoveLoading();
		parent.page.location.reload();
		
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>"
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

<body onLoad="OnLoad()">
<div style="width: 100%; height: 100%; overflow:auto;">
 <table width="100%" height="100%" align="center" border="0" cellpadding="0" cellspacing="0" class="MAIN_TABLE">
   <tr>
     <td class="TD_LIST_TITLE" align="center" width="25"><input type="checkbox" name="check_all" onclick="CheckAll()" class="input_checkbox"></td>
     <td class="TD_LIST_TITLE" align="center" width="50">行号</td>
     <td class="TD_LIST_TITLE" align="center">客户简称</td> 
     <td class="TD_LIST_TITLE" align="center">全称</td>
	 <td class="TD_LIST_TITLE" align="center">联系人</td>
	 <td class="TD_LIST_TITLE" align="center">联系电话</td>     
     <td class="TD_LIST_TITLE" align="center">传真</td> 
     <td class="TD_LIST_TITLE" align="center">地址</td>
     <td class="TD_LIST_TITLE" align="center">分销代码</td>
     <td class="TD_LIST_TITLE" align="center">是否启用</td>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0){
		ClientFile cus = null;
		String s0,s1,s2,s3,s4,s5,s6,s7,s8;
		for(int i = 0; i < ls.size(); i++){
			cus = (ClientFile)ls.get(i);
	    	
	    	s0 = cus.getM_clientFileId();	/*主键*/
			s1 = cus.getM_clientName();		/*客户简称*/
			s2 = cus.getM_fullName();		/*全称*/
			s3 = cus.getM_linkman();			/*联系人*/
			s4 = cus.getM_phone();			/*联系电话*/
			s5 = cus.getM_fax();				/*传真*/
			s6 = cus.getM_address();			/*地址*/
			s7 = cus.getM_strOutId();
			s8 = cus.getM_reveal();

%>	
   <tr onmouseover="this.bgColor='#E2E8EA'" onmouseout="this.bgColor=''">
     <td class="TD_LIST" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="<%=s0%>" onClick="Change(this)"></td>
     <td class="TD_LIST" align="center"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=s1==null?"":s1%></td>
     <td class="TD_LIST" align="center"><%=s2==null?"":s2%></td>
     <td class="TD_LIST" align="center"><%=s3==null?"":s3%></td>
     <td class="TD_LIST" align="center"><%=s4==null?"":s4%></td>
     <td class="TD_LIST" align="center"><%=s5==null?"":s5%></td>
     <td class="TD_LIST" align="center"><%=s6==null?"":s6%></td>
     <td class="TD_LIST" align="center"><%=s7==null?"":s7%></td>
     <td class="TD_LIST" align="center"><%=s8==null?"":s8%></td>
<%
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="10" class="TD_LIST" valign="top">
       <div style="color: red;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
 </table>
</div>
</body>
</html>