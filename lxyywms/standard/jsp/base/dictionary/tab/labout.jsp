<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.base.Labout"%>
<%@ page import="com.ricosoft.common.pagination.PagingTool"%>


<%String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="<%=basePath%>standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>standard/style/css/table.css" rel="stylesheet" type="text/css">

<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
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
			var result = showModalDialog("<%=request.getContextPath()%>/BMSService?actionCode=createCode&url=/standard/jsp/base/dictionary/tab/laboutAdd.jsp&object=Labout&field=m_laboutNumber",'','dialogWidth=400px;dialogHeight=200px');
		   		
		   	if(result != null && result.length > 1)
		   	{
		   		window.location.href="<%=basePath%>"+result;
		   		window.close();
		   	}
		}
		//查询
		function search()
		{
		
		    	document.myform.action="<%=basePath%>/BMSService?actionCode=labout";	
		    	myform.submit();
		   
		}
		//删除
		function deleteCheck()
		{     
		        var deleteName = getDelCheckName();   
		        
		        if(deleteName == null||deleteName.length <1){
		        	alert("请选择所要删除的信息");
		        }  
		        else{
					var del = confirm("确定删除所选信息")
					if(del){
					window.location.href='<%=request.getContextPath()%>/BMSService?actionCode=delete&deleteStr='+deleteName+'&object=Labout&id=m_laboutId';
					}
		        }
		}
		//修改
		function update()
		{
			var checkRole =  getDelCheckName();
			if(checkRole != null && checkRole.length > 0)
			{
				var strRoleIdLeng = checkRole.split(",");
				if( strRoleIdLeng.length == 2)
				{
	 					alert("信息!");
				}else
				{
					alert("只能选择一条信息!");
				}
			}        
			else{
				alert("请选择你要修改的信息");
			}
		}
		 function updateUser(param){
		   		var strUrl = "<%=request.getContextPath()%>/standard/jsp/base/dictionary/tab/laboutUpd.jsp";
		   		strUrl = strUrl +"?" + param;	
		   		var  result = showModalDialog(strUrl,'','dialogWidth=400px;dialogHeight=200px');
	 			if(result != null && result.length > 1)
	 			{
	 				window.location.href="<%=request.getContextPath()%>/"+result;
					window.close();
				}		
		   }
		 function onlo()
		{
			selectObject('','duty','17'); //班次
		}
-->
</script>
	</head>
	<body onload="">
		<form name="myform" method="post" action="">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="2%" height="27"></td>
					<td width="98%" class="font_006699_bold_12">
						当前位置：基本信息 -&gt; 字典中心 -&gt; 装卸工字典</td>
				</tr>
			</table>
			<table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_MGR_TOP">
				<tr>
					<td width="24%" class="TD_MGR_TOP"><div align="right">卸载工号：</div></td>
					<td width="29%" class="TD_MGR_TOP"><div align="left"><input type="text" name="number" id="number"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></div></td>
					<td width="17%" class="TD_MGR_TOP"><div align="right">名称：</div></td>
					<td width="30%" class="TD_MGR_TOP"><div align="left"><input type="text" name="name" id="name"  onKeyUp="if(checkValue(this.value))execCommand('undo')"></div></td>
				</tr>
				<tr>
					<td width="24%" class="TD_MGR_TOP"><div align="right">是否可用：</div></td>
					<td width="29%" class="TD_MGR_TOP"><div align="left"><select name="marker"><option value="">---请选择---</option><option value="1">可用</option><option value="0">不可用</option></select></div></td>
				<!-- 	<td width="17%" class="TD_MGR_TOP"><div align="right">班次：</div></td>
    				 <td width="30%" class="TD_MGR_TOP"><div align="left">
     				   <select name="duty" id="duty"> 
         				  <option value=""> </option>
        				</select>
     				</div></td>-->
				</tr>
				<tr>
					<td height="27" colspan="4" class="TD_MGR_TOP">
						<div align="center"><input onclick="add()" type="button" name="button1" value="新增" class="BUTTON_STYLE1">
							&nbsp;
							<input onclick="deleteCheck()" type="button" name="button2" value="删除" class="BUTTON_STYLE1">
							&nbsp;
							<input onclick="search()" type="button" name="button4" value="查询" class="BUTTON_STYLE1">
						</div>
					</td>
				</tr>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="20"></td>
				</tr>
			</table>
			<table width="98%" height="60%" border="1" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
				<tr>
					<td width="10%" class="TD_LIST_TITLE"><div align="center"><input type="checkbox" name="check_all" value="checkbox" onclick="selectAll();"class="input_checkbox">全选</div></td>
					<td class="TD_LIST_TITLE"><div align="center">装卸工号</div></td>
					<td class="TD_LIST_TITLE"><div align="center">名称</div></td>
				<!-- 	<td class="TD_LIST_TITLE"><div align="center">班次</div></td>-->
					<td class="TD_LIST_TITLE"><div align="center">是否启用</div></td>
					<td class="TD_LIST_TITLE"><div align="center">描述</div></td>
					<td class="TD_LIST_TITLE"><div align="center">操作</div></td></tr>
				<%List ls = (List) request.getAttribute("exResultList");
			if (ls != null) {
				for (int i = 0; i < ls.size(); i++) {
					Labout lab = (Labout) ls.get(i);
					String strLabId = lab.getM_laboutId();
					String strLabNumber = lab.getM_laboutNumber();
					String strLabName = lab.getM_laboutName();
					String strLabMarker = lab.getM_marker();
					String strLabDuty = lab.getM_duty();
					String strLabDescribe = lab.getM_describe();
		    	if(strLabId == null||strLabId.trim().length() < 1){
				 		strLabId = " ";}
				if(strLabNumber == null||strLabNumber.trim().length() < 1){
				 		strLabNumber = " ";}
				if(strLabName == null||strLabName.trim().length() < 1){
				 		strLabName = " ";}
				if(strLabMarker == null||strLabMarker.trim().length() < 1){
				 		strLabMarker = " ";}
				if(strLabDuty == null||strLabDuty.trim().length() < 1){
			 	  		strLabDuty = " ";}
			 	if(strLabDescribe == null||strLabDescribe.trim().length() < 1){
			 			strLabDescribe = " ";}

				String param="id="+strLabId+"&name="+strLabName+"&number="+strLabNumber+"&remark="+strLabDescribe+"&duty="+strLabDuty+"&play="+strLabMarker;
					%>
				<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
				  <td class="TD_LIST"><div align="center"><input name="roleId" type="checkbox" value="<%=strLabId%>"  class="input_checkbox"></div></td>
				  <td class="TD_LIST"><div align="center"><%=strLabNumber%></div></td>
				  <td class="TD_LIST"><div align="center"><%=strLabName%></div></td>
				  
				<!--   <td class="TD_LIST"><div align="center"><%=strLabDuty%></div></td>-->
				  <td class="TD_LIST"><div align="center"><%if(strLabMarker.equals("1")){%>可用<%}else{%>不可用<%}%></div></td>
				  <td class="TD_LIST"><div align="center"><%=strLabDescribe%></div></td>
				  <td class="TD_LIST"><div align="center"><input type="button" name="button3" value="修改" class="BUTTON_STYLE1" onclick="updateUser('<%=param%>');"></div></td>
     			</tr>
				<%}
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
			if (pt != null) {
				iCountRow = pt.getM_iCountRow();
				iMaxRow = pt.getM_iMaxRow();
				iCurrentPage = pt.getM_iCurrentPage();
				iCountPage = pt.getM_iCountPage();
			}
			if (iCountPage > 1) {%>
					<div align="right">
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
