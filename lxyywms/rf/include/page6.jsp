<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="com.ricosoft.common.pagination.PagingTool" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>欢迎使用自动化立体仓库信息管理系统</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
<!-- 
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	//检查数量是否为数字
	function IsNum(num){ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
	function ToPage(page){
		if(page != 0){
			if(parent["Loading"]){
				parent.Loading();
			}
			window.location.href= ac + "commonPaging&sessionName=inboundPage6&page=" + page;
		}
		else{
			var page = document.getElementById("page").value;
			var max_page = document.getElementById("max_page").value;
			if(IsNum(page) && 0 < page && page <= max_page){
				if(parent["Loading"]){
					parent.Loading();
				}
				window.location.href = ac + "commonPaging&sessionName=inboundPage6&page=" + page;
			}
			else{
				document.getElementById("page").value="";
				alert("页号为非数字，或不在范围中！");
			}
		}
	}
-->
</script>
<body  id="table_body">

<div >

<%
	int iMaxRow = 50;
	String strMaxRow = "1";
	String strFlag = "N";
	if(strMaxRow != null){
		iMaxRow = Integer.parseInt(strMaxRow);
	}
	int iCountRow = 0;
	int iCurrentPage = 1;
	int iCountPage = 1;
	PagingTool pt = (PagingTool)request.getAttribute("pagingTool");
	if(pt != null){
		iCountRow = pt.getM_iCountRow();
		iMaxRow = pt.getM_iMaxRow();
		iCurrentPage = pt.getM_iCurrentPage();
		iCountPage = pt.getM_iCountPage();
	}
	if(iCountPage > 1){
%>
	  <table width="100%" height="25" border="0" cellspacing="0" cellpadding="0" >
                          <td ><div class="fenye"><strong><%=iCurrentPage%>/<%=iCountPage%></strong>页</div></td>
                          <td ><div class="fenye"><a href="javascript:ToPage(1)"><img src="<%=request.getContextPath()%>/standard/images/main_54.gif" width="40" height="15" /></a></div></td>
                          <td ><div class="fenye"><a href="javascript:ToPage(<%=(iCurrentPage - 1) > 0 ? (iCurrentPage - 1) : 1%>)"><img src="<%=request.getContextPath()%>/standard/images/main_56.gif" width="45" height="15" /></a></div></td>
                          <td ><div class="fenye"><a href="javascript:ToPage(<%=(iCurrentPage + 1) > iCountPage ? iCountPage : (iCurrentPage + 1)%>)"><img src="<%=request.getContextPath()%>/standard/images/main_58.gif" width="45" height="15" /></a></div></td>
                          <td ><div class="fenye"><a href="javascript:ToPage(<%=iCountPage%>)"><img src="<%=request.getContextPath()%>/standard/images/main_60.gif" width="40" height="15" /></a></div></td>

<%	
	}
	else{
%>
			  <table width="100%" height="25" border="0" cellspacing="0" cellpadding="0" >
         
                        <tr>
                          <td ><div class="fenye"><strong>1/1</strong>页</div></td>
                          <td ><div class="fenye"><a href="#"><img src="<%=request.getContextPath()%>/standard/images/main_54.gif" width="40" height="15" /></a></div></td>
                          <td ><div class="fenye"><a href="#"><img src="<%=request.getContextPath()%>/standard/images/main_56.gif" width="45" height="15" /></a></div></td>
                          <td ><div class="fenye"><a href="#"><img src="<%=request.getContextPath()%>/standard/images/main_58.gif" width="45" height="15" /></a></div></td>
                          <td ><div class="fenye"><a href="#"><img src="<%=request.getContextPath()%>/standard/images/main_60.gif" width="40" height="15" /></a></div></td>
<%	
	}	
%> 
                  
        </tr>
      </table>  



<input name="max_page" type="hidden" value="<%=iCountPage%>">
                
</div>
</body>
</html>
