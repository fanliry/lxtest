<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BasePackage"%>
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
		}
		
	}
-->
</script>
</head>

<body onload="javascript:parent.page.location.reload();">

  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr>
      <td class="TD_LIST_TITLE1" width="100px"><div class="list_title">
        <input type="checkbox" name="check_all" onclick="CheckAll();" class="input_checkbox" value="checkbox">行号</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">包装代码</div></td>
      <td class="TD_LIST_TITLE"><div class="list_title">包装描述</div></td>
      <td class="TD_LIST_TITLE2"><div class="list_title">备注</div></td>
    </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0)
	{
		BasePackage pk = null;
		String packageid = "";		// 包装ID
		String pkgdesc = "";		// 包装描述
		String remark = "";			// 备注
		
		for(int i=0; i<ls.size(); i++)
		{
			pk = (BasePackage)ls.get(i);
			packageid = pk.getPackageid();	// 包装ID
			pkgdesc = pk.getPkgdesc();		// 包装描述
			remark = pk.getRemark();		// 备注
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="parent.ondbClickDo('<%=packageid%>');">
     <td class="TD_LIST1" align="center">
     	<input type="checkbox" name="check_id" value="<%=packageid%>" class="input_checkbox" onclick="setSel(<%=i%>)"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=packageid%></td>
     <td class="TD_LIST" align="center"><%=pkgdesc==null ? "":pkgdesc%></td>
     <td class="TD_LIST2" align="center"><%=remark==null ? "":remark%></td>
   </tr>
<%
		}
	}
	else{
		session.removeAttribute("paging");
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
