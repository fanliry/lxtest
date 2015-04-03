<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
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

	//设置单选框是否选中
	function setSel(i){
		var check_ids = document.getElementsByName("check_id");
		check_ids[i].checked = true;
		parent.right.location.href= ac + "baseLotSetAction&flag=2&lottype=" + check_ids[i].value;
		
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
	
	//页面加载
	function OnLoad(){
		
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg!="" && back_msg!="Y"){
			alert(back_msg);
		}
	}
-->
</script>
</head>

<body onload="OnLoad()">
 <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE1" width="50"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">类型</div></td>
     <td class="TD_LIST_TITLE2"><div class="list_title">备注</div></td>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0)
	{
		Object[] ob = null;
		String lottype;			//类型
		String lottypename;		//类型名
		String remark;			//备注
		
		for(int i=0; i<ls.size(); i++)
		{
			ob = (Object[])ls.get(i);
			
			lottype = ob[0] == null ? "" : ob[0].toString();
			lottypename = ob[1] == null ? "" : ob[1].toString();
			remark = ob[2] == null ? "" : ob[2].toString();
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>);" ondblclick="parent.updateData();">
     <td class="TD_LIST1" align="center"><input type="radio" name="check_id" class="input_checkbox" value="<%=lottype%>"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=lottypename%></td>
     <td class="TD_LIST2" align="center"><%=remark%></td>
   </tr>
<%
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="5" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
 </table>

</body>
</html>
