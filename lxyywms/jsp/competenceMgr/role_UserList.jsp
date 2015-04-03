<%@ page contentType = "text/html;charset=gb2312"%>
<%@ page import = "java.util.*"%>
<%@ page import="com.ricosoft.entity.competenceMgr.UserInfo" %>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>查看角色的用户</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/standard/css/table.css" rel="stylesheet" type="text/css">
<script  src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
  <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">	
<style type="text/css">

</style></head>

<body>
 

<table width="100%" height="78"  border="0" align="center" cellpadding="0" cellspacing="1">
  <tr>
    <td align="right" valign="top" > 
      <table width="100%" height="70" border="0">
        <tr> 
          <td align="right" valign="top" bordercolor="#FFFFFF"> 
            <form action="" method="post" name="form2">
            
            <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
		   	<tr>
		     <td class="TD_LIST_TITLE" width="11%"><div class="list_title">用户代码</div></td>
		     <td class="TD_LIST_TITLE" width="9%"><div class="list_title">用户名</div></td>
		     <td class="TD_LIST_TITLE" width="11%"><div class="list_title">用户电话</div></td>
		     <td class="TD_LIST_TITLE" width="39%"><div class="list_title">用户地址</div></td>
		     <td class="TD_LIST_TITLE" width="17%"><div class="list_title">创建时间</div></td>
		     <td class="x1" width="13%"><div class="list_title">用户组</div></td>
		   	</tr>
            
                <%
	List lsRoleUser = (List)request.getAttribute("userls");	
	if(lsRoleUser != null && !lsRoleUser.isEmpty()) {
	
	
    	for(int i = 0; i < lsRoleUser.size(); i++) {
			UserInfo userInfo = (UserInfo)lsRoleUser.get(i);  
			
			String strUserCode= userInfo.getM_strUserCode();
			String strUserName = userInfo.getM_strUsername();
			String strTel= userInfo.getM_strTel();
			String strAddress = userInfo.getM_strAddress();
			String strCreateTime = userInfo.getM_strCreateTime();
			String strUserGroup  = userInfo.getM_strUserGroupName();
			
			if(strUserName == null || strUserName.trim().length()<0)
			{
				strUserName = "&nbsp;";
			}
			if(strTel == null || strTel.trim().length()<0)
			{
				strTel = "&nbsp;";
			}
			if(strAddress == null || strAddress.trim().length()<0)
			{
				strAddress = "&nbsp;";
			}
			if(strCreateTime == null || strCreateTime.trim().length()<0)
			{
				strCreateTime = "&nbsp;";
			}
	
			
			
%>
                <tr align="center"> 
                  <td class="TD_LIST1"><%=strUserCode%></td>
                  <td class="TD_LIST1"><%=strUserName%></td>
                  <td class="TD_LIST1"><%=strTel%></td>
                  <td class="TD_LIST1"><%=strAddress%></td>
                  <td class="TD_LIST1"><%=strCreateTime%></td>
                  <td class="TD_LIST1"><%=strUserGroup == null ? "" : strUserGroup%></td>
                </tr>
                <%		}	%>
               
                <%
			} 
			%>
     <tr height="100%">
	  <td height="100%" colspan="8" class="TD_last_LIST" valign="top">
	    <div style="color: red; margin:5px;"><%if(lsRoleUser != null && lsRoleUser.size() == 0){%>无相关数据！<%}%></div>
	  </td>
	  </tr>
              </table>
            </form>            
          </td>
        </tr>
      </table></td>
  </tr>
</table>

</body>
</html>
