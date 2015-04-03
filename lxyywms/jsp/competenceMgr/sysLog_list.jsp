<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ricosoft.entity.competenceMgr.SystemLogInfo" %>



<html>
<head>
  <title>仓储配送管理系统</title>
  <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
  <link href="<%=request.getContextPath()%>/standard/css/table.css" rel="stylesheet" type="text/css">
  <script type="text/javascript">
  <!--
   //全选
    function selectAll()
	{
		var state=null;
		var length = document.myform.elements.length;
		for(i=0;i<length;i++){
			if( document.myform.elements[i].type=='checkbox' && document.myform.elements[i].name=='checkbox_all'){
				 state = document.myform.elements[i].checked;
				 break;
			}
		}
		for(i=0;i<length;i++){
			 if( document.myform.elements[i].type=='checkbox' && document.myform.elements[i].name=='checkbox_id'){
				   document.myform.elements[i].checked=state;
			 }
		}
	}
	
  -->
  </script>
</head>

<body onload="javascript:parent.myIframe1.location.reload();">
<%
	List ls = null;
	if(request.getAttribute("exResultList") != null)
	{
		ls = (List)request.getAttribute("exResultList");
	}
	
%>
<form id="myform" name="myform" method="post" action="">
  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
	<tr>
	  <td width="10%" class="TD_LIST_TITLE"><div class="list_title">行号</div></td>
	  <td width="20%" class="TD_LIST_TITLE"><div class="list_title">操作员</div></td>
	  <td width="30%" class="TD_LIST_TITLE"><div class="list_title">内容</div></td>
	  <td width="20%" class="TD_LIST_TITLE"><div class="list_title">模块名</div></td>
	  <td width="20%" class="TD_LIST_TITLE"><div class="list_title">时间</div></td>

	</tr>
	  <%
	  	if(ls != null && !ls.isEmpty())
	  	{
	  	 	for(int i = 0; i < ls.size(); i++) 
	  	 	{
	  	 		SystemLogInfo log = (SystemLogInfo)ls.get(i);  
	  	 	    String strId = log.getM_strLogId(); 		//ID
	  	 	    String strLogName = log.getM_strLogName();
	  	 	    String strContent = log.getM_strContent();
	  	 	    String strModuleName = log.getM_strModuleName();
	  	 	    String strTime = log.getM_strCreateTime();
	  		
	  	%>
	  		<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" align="center">

			  <td class="TD_LIST1"><%=i+1%></td>
			  <td class="TD_LIST1"><%=strLogName == null ? "" : strLogName%></td>
			  <td class="TD_LIST1"><%=strContent == null ? "" : strContent%></td>
			  <td class="TD_LIST1"><%=strModuleName == null ? "" : strModuleName%></td>
			  <td class="TD_LIST1"><%=strTime == null ? "" : strTime%></td>
			</tr>
	  	<%
	  	 	}
	  	 }else
	  	 {
	  	 	session.removeAttribute("paging");
	  	 }
	  %>
	

		<!-- ==== 最后一行（空行），必须有 ==== -->
	  <tr height="100%">
	  <td height="100%" class="TD_last_LIST" valign="top">
	    <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
	  </td>
	</tr>
  </table>
</form>

</body>
</html>
