<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="com.ricosoft.common.pagination.PagingTool" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
<!-- 
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	//��������Ƿ�Ϊ����
	function IsNum(num){ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
	function ToPage(page){
		if(page != 0){
			if(parent["Loading"]){
				parent.Loading();
			}
			window.location.href= ac + "commonPaging&sessionName=inboundPageCancel&page=" + page;
		}
		else{
			var page = document.getElementById("page").value;
			var max_page = document.getElementById("max_page").value;
			if(IsNum(page) && 0 < page && page <= max_page){
				if(parent["Loading"]){
					parent.Loading();
				}
				window.location.href = ac + "commonPaging&sessionName=inboundPageCancel&page=" + page;
			}
			else{
				document.getElementById("page").value="";
				alert("ҳ��Ϊ�����֣����ڷ�Χ�У�");
			}
		}
	}
-->
</script>
<body  id="table_body">

<div >

<%
	int iMaxRow = 50;
	String strMaxRow = "10";
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
	  <table  width="100%" height="25" border="0" cellspacing="0" cellpadding="0" >
         <tr>
              <td width="64%" align="right">
					  <div><span class="fenye">ÿҳ��ʾ
	    <input type="text" name="lineviewrow" id="lineviewrow" value="<%=iMaxRow%>" <%=(strFlag != null && strFlag.equals("N")) ? "readonly":""%>  style="text-align:center;width:25px; height:15px; font-size:12px; font-weight:bold; border:solid 1px #7aaebd;color: #295568;"/>
         ����¼������ <strong><%=iCountRow%></strong> ����¼����ǰ�� <strong><%=iCurrentPage%></strong> ҳ���� <strong><%=iCountPage%></strong> ҳ</span></div></td>
                      <td width="36%"><table width="280" height="25" border="0" align="right" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="44"><div class="fenye"><a href="javascript:ToPage(1)"><img src="<%=request.getContextPath()%>/standard/images/main_54.gif" width="40" height="15" /></a></div></td>
                          <td width="48"><div class="fenye"><a href="javascript:ToPage(<%=(iCurrentPage - 1) > 0 ? (iCurrentPage - 1) : 1%>)"><img src="<%=request.getContextPath()%>/standard/images/main_56.gif" width="45" height="15" /></a></div></td>
                          <td width="48"><div class="fenye"><a href="javascript:ToPage(<%=(iCurrentPage + 1) > iCountPage ? iCountPage : (iCurrentPage + 1)%>)"><img src="<%=request.getContextPath()%>/standard/images/main_58.gif" width="45" height="15" /></a></div></td>
                          <td width="45"><div class="fenye"><a href="javascript:ToPage(<%=iCountPage%>)"><img src="<%=request.getContextPath()%>/standard/images/main_60.gif" width="40" height="15" /></a></div></td>

<%	
	}
	else{
%>
			  <table width="100%" border="0" cellspacing="0" cellpadding="0" >
         <tr>
              <td width="64%" align="right">
					  <div><span class="fenye">ÿҳ��ʾ
	    <input type="text" name="lineviewrow" id="lineviewrow" value="<%=iMaxRow%>" <%=(strFlag != null && strFlag.equals("N")) ? "readonly":""%> style="text-align:center;width:25px; height:15px; font-size:12px; font-weight:bold; border:solid 1px #7aaebd;color: #295568;"/>
         ����¼������ <strong><%=iCountRow%></strong> ����¼����ǰ�� <strong>1</strong> ҳ���� <strong>1</strong> ҳ</span></div></td>
                      <td width="36%"><table width="280" height="25" border="0" align="right" cellpadding="0" cellspacing="0">
                        <tr>
                          <td width="44"><div class="fenye"><a href="#"><img src="<%=request.getContextPath()%>/standard/images/main_54.gif" width="40" height="15" /></a></div></td>
                          <td width="48"><div class="fenye"><a href="#"><img src="<%=request.getContextPath()%>/standard/images/main_56.gif" width="45" height="15" /></a></div></td>
                          <td width="48"><div class="fenye"><a href="#"><img src="<%=request.getContextPath()%>/standard/images/main_58.gif" width="45" height="15" /></a></div></td>
                          <td width="45"><div class="fenye"><a href="#"><img src="<%=request.getContextPath()%>/standard/images/main_60.gif" width="40" height="15" /></a></div></td>
<%	
	}	
%>
                          <td width="31" class="fenye"><div align="center">ת��</div></td>
                          <td width="25"><div class="fenye">
                            <input type="text" name="page" id="page"  style="width:20px; height:12px; font-size:12px; border:solid 1px #7aaebd;"/>
                          </div></td>
                          <td width="17" class="fenye"><div align="center">ҳ</div></td>
                          <td width="32" class="fenye"><input name="button" type="image" id="button" value="�ύ" src="<%=request.getContextPath()%>/standard/images/main_62.gif" onclick="ToPage(0)"/></td>
                        </tr>
                      </table></td>
        </tr>
      </table>  



<input name="max_page" type="hidden" value="<%=iCountPage%>">
                
</div>
</body>
</html>
