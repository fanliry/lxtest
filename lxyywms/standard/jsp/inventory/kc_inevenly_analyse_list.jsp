<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List" %>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE1" align="center" width="50"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">库区/巷道号</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">货位总数</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">已用数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">可用数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">其他</div></td>
   </tr>
<%
	List ls  = (List)request.getAttribute("exResultList");
	if(ls != null){
		Object[] ob = null;
		String s1,s2,s3,s4,s5,xdh;
		int m1=0,m2=0,m3=0,m4=0;
		for(int i=0; i<ls.size(); i++){
			ob = (Object[])ls.get(i);
			
			s1 = ob[0].toString();
			xdh = ob[1].toString();
			s1 = s1+"/"+xdh;
			s2 = ob[2].toString();
			s3 = ob[3].toString();
			s4 = ob[4].toString();
			s5 = ob[5].toString();
			
			m1 += Integer.parseInt(s2);
			m2 += Integer.parseInt(s3);
			m3 += Integer.parseInt(s4);
			m4 += Integer.parseInt(s5);
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST" align="center"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=s1 == null ? "" : s1%></td>
     <td class="TD_LIST" align="center"><%=s2 == null ? "" : s2%></td>
     <td class="TD_LIST" align="center"><%=s3 == null ? "" : s3%></td>
     <td class="TD_LIST" align="center"><%=s4 == null ? "" : s4%></td>
     <td class="TD_LIST" align="center"><%=s5 == null ? "" : s5%></td>
   </tr>
<%
		}
%>
   <tr onmouseover="this.bgColor='#E2E8EA'" onmouseout="this.bgColor=''">
     <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center" style="color: red;">合计</td>
     <td class="TD_LIST" align="center" style="color: red;"><%=m1%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=m2%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=m3%></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=m4%></td>
   </tr>
<%
	}
%>
   <tr>
     <td height="100%" colspan="7" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
 </table>
</div>
</body>
</html>
