<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.*" %>
<%@ page import="com.ricosoft.common.pagination.PagingTool" %>
<%
    request.setCharacterEncoding("GB2312"); 
%>
<html>
<head>
  <title>�ִ����͹���ϵͳ</title>
  <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
 <link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
  <link href="<%=request.getContextPath()%>/standard/css/table.css" rel="stylesheet" type="text/css">
  </head>
<script type="text/javascript">
<!-- 
	//��ҳ��ת
	function toPagingRow(pageCount)
	{
		var page;
		page = 0;
		page = document.getElementById("pageId").value;
		  
		if( 0< page && page <= pageCount && numChar(page))
		{
			parent.myIframe.location.href= '<%=request.getContextPath()%>/BMSService?actionCode=commonPaging&page='+page;
			//window.location.reload();
		}
	} 
	//�����Ƿ�Ϊ����
	function numChar(str){
		var bVal = true;
	
		for(var i=0; i<str.length; i++){
			var ls = str.charCodeAt(i);
			
			if(ls>57 || ls < 48)
			{
				 bVal = false;
    	         break;
			}	
		}	
		return bVal;
	}
	function toPaging(url)
	{
		parent.myIframe.location.href=url;
		//window.location.reload();
	}
-->
</script>

<body id="table_body">
<table width="750" align="right"  border="0" cellspacing="0" cellpadding="0">
<tr>
<td width="750" height="25" class="font_001F56_12">

<%
	PagingTool pt = null; 
	int iCountRow = 0;
	int iMaxRow = 15;
	int iCurrentPage = 1;
	int iCountPage = 1;
	if(request.getSession(false).getAttribute("paging") != null)
	{
		pt = (PagingTool)request.getSession(false).getAttribute("paging"); 
	}
	if(pt != null)
	{
		iCountRow = pt.getM_iCountRow();
		iMaxRow = pt.getM_iMaxRow();
		iCurrentPage = pt.getM_iCurrentPage();
		iCountPage = pt.getM_iCountPage();
	}
	if(iCountPage > 1)
	{
%>
       <!-- ÿҳ��ʾ  ����¼������ 0 ����¼����ǰ�� 1 ҳ���� 1 ҳ     ת��   ҳ   -->
       <!-- ����[0]��  ÿҳ[15]��  ��ǰ��[1]ҳ  ��[1]ҳ  [��ҳ][��һҳ][��һҳ][ĩҳ] ������ҳ   -->
       <table width="100%" border="0" cellspacing="0" cellpadding="0" >
       <tr><td width="64%" align="right">
		<div><span class="fenye">
		       ÿҳ��ʾ<strong><%=iMaxRow%></strong>����¼&nbsp;&nbsp;����<strong><%=iCountRow%></strong>����¼&nbsp;&nbsp;��ǰ��<strong><%=iCurrentPage%></strong>ҳ&nbsp;&nbsp;��<strong><%=iCountPage%></strong>ҳ&nbsp;&nbsp;
		  </span></div></td>
		  <td width="36%">
		  <table width="280" height="25" border="0" align="right" cellpadding="0" cellspacing="0">
		  <tr><td width="44"><div class="fenye">
		  <A href="javascript:toPaging('<%=request.getContextPath()%>/BMSService?actionCode=commonPaging&page=1');"><img src="<%=request.getContextPath()%>/standard/images/main_54.gif" width="40" height="15" /></A>
		  </div></td><td width="48"><div class="fenye">
		  <A href="javascript:toPaging('<%=request.getContextPath()%>/BMSService?actionCode=commonPaging&page=<%= (iCurrentPage - 1) > 0 ? (iCurrentPage - 1) : 1%>');"><img src="<%=request.getContextPath()%>/standard/images/main_56.gif" width="45" height="15" /></A>
		  </div></td><td width="48"><div class="fenye">
		  <A href="javascript:toPaging('<%=request.getContextPath()%>/BMSService?actionCode=commonPaging&page=<%= (iCurrentPage + 1) > iCountPage ? iCountPage : (iCurrentPage + 1)%>');"><img src="<%=request.getContextPath()%>/standard/images/main_58.gif" width="45" height="15" /></A> 
		  </div></td><td width="45"><div class="fenye">
		  <A href="javascript:toPaging('<%=request.getContextPath()%>/BMSService?actionCode=commonPaging&page=<%=iCountPage%>');"><img src="<%=request.getContextPath()%>/standard/images/main_60.gif" width="40" height="15" /></A>
		   </div></td><td width="31" class="fenye"><div align="center">
		ת��</div></td><td width="25"><div class="fenye">
		   <input name="pageId" type=text size=1 ></div></td>
		   <td width="17" class="fenye"><div align="center">ҳ</div></td>
		 <td width="32">
		  <input name="button" type="image" id="button" value="�ύ" src="<%=request.getContextPath()%>/standard/images/main_62.gif" onclick="toPagingRow(<%=iCountPage%>)"/>
		 </td>
		 </tr>	
		 </table>
		 </td>	
		</tr></table>
<%	
	}else
	{
%>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" >
       <tr><td width="64%" align="right">
		<div><span class="fenye">
		       ÿҳ��ʾ<strong><%=iMaxRow%></strong>����¼&nbsp;&nbsp;����<strong><%=iCountRow%></strong>����¼&nbsp;&nbsp;��ǰ��<strong><%=iCurrentPage%></strong>ҳ&nbsp;&nbsp;��<strong><%=iCountPage%></strong>ҳ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  </span></div></td>
		  <td width="36%">
		  <table width="280" height="25" border="0" align="right" cellpadding="0" cellspacing="0">
		  <tr><td width="44"><div class="fenye">
		  <A href="#"><img src="<%=request.getContextPath()%>/standard/images/main_54.gif" width="40" height="15" /></A>
		  </div></td><td width="48"><div class="fenye">
		  <A href="#"><img src="<%=request.getContextPath()%>/standard/images/main_56.gif" width="45" height="15" /></A>
		  </div></td><td width="48"><div class="fenye">
		  <A href="#"><img src="<%=request.getContextPath()%>/standard/images/main_58.gif" width="45" height="15" /></A> 
		  </div></td><td width="45"><div class="fenye">
		  <A href="#"><img src="<%=request.getContextPath()%>/standard/images/main_60.gif" width="40" height="15" /></A>
		   </div></td><td width="31" class="fenye"><div align="center">
		ת��</div></td><td width="25"><div class="fenye">
		   <input name="pageId" type=text style="width:20px; height:12px; font-size:12px; border:solid 1px #7aaebd;"></div></td>
		   <td width="17" class="fenye"><div align="center">ҳ</div></td>
		 <td width="32" class="fenye">
		  <input name="button" type="image" id="button" value="�ύ" src="<%=request.getContextPath()%>/standard/images/main_62.gif"/>
		 </td>
		 </tr>	
		 </table>
		 </td>	
		</tr></table>
<%	
	}	
%>
</td>
</tr>
</table>
</body>
</html>