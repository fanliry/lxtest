<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
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
	
-->
</script>
</head>

<body>

 <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE1" width="50"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">属性名称</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">属性代码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">是否使用</div></td>
     <td class="TD_LIST_TITLE2"><div class="list_title">顺序</div></td>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0)
	{
		BaseLotSet lotset = null;
		String lotid;			//批次属性ID
		String lotname;			//批次属性名称
		String islot;			//是否显示或者是否分组统计 Y N
		int lotseq;				//显示顺序或者分组统计的顺序
		
		for(int i=0; i<ls.size(); i++)
		{
			lotset = (BaseLotSet)ls.get(i);
			lotname = lotset.getLotname();
			lotid = lotset.getLotid();
			islot = lotset.getIslot();
			lotseq = lotset.getLotseq();
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=lotname == null ? "" : lotname%></td>
     <td class="TD_LIST" align="center"><%=lotid == null ? "" : lotid%></td>
     <td class="TD_LIST" align="center"><%=islot == null ? "" : islot%></td>
     <td class="TD_LIST2" align="center"><%=lotseq%></td>
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
