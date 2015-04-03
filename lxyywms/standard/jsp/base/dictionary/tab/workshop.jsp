<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.base.Workshop"%>
<%@ page import="com.ricosoft.common.pagination.PagingTool"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript">
<!--
	//获得选择的选择框的值
	function getDelCheckName()
	{
			var strDel = '';
			var length = document.myform.elements.length;
		
			for(i=0;i<length;i++){
			    if(document.myform.elements[i].name!='check_all'&& document.myform.elements[i].type=='checkbox'&& document.myform.elements[i].checked== true){
			         strDel = strDel + document.myform.elements[i].value + ',';
			    }
			}
		return strDel;
	}
	//全选
	function selectAll()
	{
	    var state=null;
		var length = document.myform.elements.length;
		for(i=0;i<length;i++){
		    if(document.myform.elements[i].name=='check_all'&& document.myform.elements[i].type=='checkbox'){
		         state = document.myform.elements[i].checked;
		         
		         break;
		    }
		}
		for(i=0;i<length;i++){
		     if(document.myform.elements[i].type=='checkbox'){
		     	   document.myform.elements[i].checked=state;
		     }
		}
	}
	//分页跳转
	 function toPagingRow(pageCount)
	{
		 var page;
		 page = 0;
		 page = document.getElementById("pageId").value;	 
		
		 if( 0< page && page <= pageCount&& numChar(page))
		{
			window.location.href= '<%=request.getContextPath()%>/BMSService?actionCode=commonPaging&page='+page;
		}
	}


	//增加  object 表对象 field 表中编码字段  url 跳转页面
	function add()
	{
		var result = showModalDialog("<%=request.getContextPath()%>/BMSService?actionCode=createCode&url=/standard/jsp/base/dictionary/tab/workshopAdd.jsp&object=Workshop&field=m_workshopCode",'','dialogWidth=300px;dialogHeight=200px');
	   		
	   	if(result != null && result.length > 1)
	   	{
	   		window.location.href="<%=request.getContextPath()%>/"+result;
	   		window.close();
	   	}
	}
	//查询
	function search()
	{
	    document.myform.action="<%=request.getContextPath()%>/BMSService?actionCode=workshop";	
	    myform.submit();
	}
	//删除
	function deleteCheck()
	{     
		var deleteName = getDelCheckName();   
		if(deleteName == null||deleteName.length <1){
		alert("请选择所要删除的信息");
		 }else{
			var del = confirm("确定删除所选信息")
			if(del){
			window.location.href='<%=request.getContextPath()%>/BMSService?actionCode=delete&deleteStr='+deleteName+'&object=Workshop&id=m_workshopid';
			 }
		 }
	}
	//修改
	function updateUser(param){
	   	var strUrl = "<%=request.getContextPath()%>/standard/jsp/base/dictionary/tab/workshopUpd.jsp";
	   	strUrl = strUrl +"?" + param;
	   	var  result = showModalDialog(strUrl,'','dialogWidth=300px;dialogHeight=200px');
 		if(result != null && result.length > 1)
 		{
 			window.location.href="<%=request.getContextPath()%>/"+result;
			window.close();
		}		
	 }
-->
</script>
</head>

<body>
<form name="myform" method="post" action="">
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td width="2%" height="27"></td>
     <td width="98%" class="font_006699_bold_12"><DIV class=font_006699_bold_12>
    当前位置：基本信息 -&gt; 字典中心 -&gt; 车间资料</DIV></td>
   </tr>
 </table>
 
 <table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_MGR_TOP">
   <tr>
     <td width="16%" class="TD_MGR_TOP"><div align="right">车间编号：</div></td>
     <td width="19%" class="TD_MGR_TOP"><div align="left">
       <input type="text" name="number" id="number"  onKeyUp="if(checkValue(this.value))execCommand('undo')">
     </div></td>
     <td width="11%" class="TD_MGR_TOP"><div align="right">车间名：</div></td>
     <td colspan="2" class="TD_MGR_TOP"><div align="left">
       <input type="text" name="name" id="name"  onKeyUp="if(checkValue(this.value))execCommand('undo')">
     </div>       <div align="left">      </div></td>
    </tr>
   <tr>
     <td height="27" colspan="5" class="TD_MGR_TOP"><div align="center">
       <input onclick="add()" type="button" name="button1" value="新增" class="BUTTON_STYLE1">&nbsp;
       <input onclick="deleteCheck()" type="button" name="button2" value="删除" class="BUTTON_STYLE1">&nbsp;
       <input onclick="search()" type="button" name="button4" value="查询" class="BUTTON_STYLE1"></div>
     </td>
   </tr>
 </table>
 
  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="20"></td>
   </tr>
 </table>
 
 <table width="98%" height="60%"  border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE"><div align="center">
      <input width="10%" type="checkbox" name="check_all" value="checkbox" onclick="selectAll();"  class="input_checkbox"></div>
    </td>
     <td class="TD_LIST_TITLE"><div align="center">车间编号</div></td>
     <td class="TD_LIST_TITLE"><div align="center">车间名</div></td>
     <td class="TD_LIST_TITLE"><div align="center">备注</div></td>
	 <td class="TD_LIST_TITLE"><div align="center">操作</div></td>
   </tr>
  		 <%
  	    List ls = (List)request.getAttribute("exResultList");
		if(ls!=null)
		{
	    	for(int i = 0; i < ls.size(); i++) 
	    	{
	    	Workshop lab = (Workshop)ls.get(i);
	    	String strWorkshopId = lab.getM_workshopid();
	    	String strWorkshopCode = lab.getM_workshopCode();
	    	String strWorkshopName  = lab.getM_workshopName();
	    	String strRemark   = lab.getM_remark();
	    	
	    	if(strWorkshopId == null||strWorkshopId.trim().length() < 1)
	 				 {strWorkshopId = "";}
	 		if(strWorkshopCode == null||strWorkshopCode.trim().length() < 1)
	 				 {strWorkshopCode = "";}
	 	 	if(strWorkshopName == null||strWorkshopName.trim().length() < 1)
	 				 {strWorkshopName = "";}
	 		if(strRemark == null||strRemark.trim().length() < 1)
	 				 {strRemark = "";}
	    	String param="id="+strWorkshopId+"&number="+strWorkshopCode+"&name="+strWorkshopName+"&remark="+strRemark;	
   %>	
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST" width="10%"><div align="center">
      <input name="roleId" type="checkbox" value="<%=strWorkshopId%>" class="input_checkbox"  class="input_checkbox">
     </div></td>
     <td class="TD_LIST"><div align="center"><%=strWorkshopCode%></div></td>
     <td class="TD_LIST"><div align="center"><%=strWorkshopName%></div></td>
	 <td class="TD_LIST"><div align="center"><%=strRemark%></div></td>
	  <td class="TD_LIST"><div align="center"><input type="button" name="button3" value="修改" class="BUTTON_STYLE1" onclick="updateUser('<%=param%>');"></div></td>
   </tr>
   <%
   }
   }
   %>
   <tr>
     <td height="100%" colspan="7" class="TD_LIST"></td>
   </tr>
 </table>
</form>
		<table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="98%" height="30" class="font_006699_12">
					<%PagingTool pt = null;
						int iCountRow = 0;
						int iMaxRow = 10;
						int iCurrentPage = 1;
						int iCountPage = 1;
						if (request.getAttribute("pagingTool") != null) {
							pt = (PagingTool) request.getAttribute("pagingTool");
						}
						if (pt != null) 
						{
							iCountRow = pt.getM_iCountRow();
							iMaxRow = pt.getM_iMaxRow();
							iCurrentPage = pt.getM_iCurrentPage();
							iCountPage = pt.getM_iCountPage();
						}
						if (iCountPage > 1) {%><div align="right">
						总数[
						<%=iCountRow%>
						]条 每页[10]条 当前第[
						<%=iCurrentPage%>
						]页 共[
						<%=iCountPage%>
						]页 【 <A href="<%=request.getContextPath()%>/BMSService?actionCode=commonPaging&page=1">首页</A>】【<A href="<%=request.getContextPath()%>/BMSService?actionCode=commonPaging&page=<%= (iCurrentPage - 1) > 0 ? (iCurrentPage - 1) : 1%>">上一页</A>】【<A
							href="<%=request.getContextPath()%>/BMSService?actionCode=commonPaging&page=<%= (iCurrentPage + 1) > iCountPage ? iCountPage : (iCurrentPage + 1)%>">下一页</A>】 【<A
							href="<%=request.getContextPath()%>/BMSService?actionCode=commonPaging&page=<%=iCountPage%>">末页</A>】跳到第
						<input name="pageId" type=text class=input1 size=1>
						页&nbsp;&nbsp;
						<input type=image src="<%=request.getContextPath()%>/standard/images/go.bmp" width=18 height=18 border=0 onclick="toPagingRow(<%=iCountPage%>);">
						&nbsp;&nbsp;
					</div>
						<%} else {%>
					<div align="right">
						总数[
						<%=iCountRow%>
						]条 每页[10]条 当前第[1]页 共[1]页【首页】【上一页】【下一页】【末页】 跳到第
						<input name=page type=text class=input1 size=1>
						页&nbsp;&nbsp;
						<input type=image src="<%=request.getContextPath()%>/standard/images/go.bmp" width=18 height=18 border=0>
						&nbsp;&nbsp;
					</div>
					<%}%>
				</td>
			</tr>
		</table>
	</body>
</html>
