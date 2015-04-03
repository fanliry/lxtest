
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean sysMgrRoleAdd = false; //添加
	boolean sysMgrRoleDelete = false; //删除
	boolean sysMgrRoleUpdate = false; //修改
	boolean sysMgrRoleQuery = false; //查询
	boolean sysMgrRoleUserQuery = false; //查看角色用户
	boolean sysMgrRolePermissions = false; //查看分配权限
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("sysMgrRoleAdd") != null){
			sysMgrRoleAdd = true;
		}
		if(hsPopedom.get("sysMgrRoleDelete") != null){
			sysMgrRoleDelete = true;
		}
		if(hsPopedom.get("sysMgrRoleUpdate") != null){
			sysMgrRoleUpdate = true;
		}
		if(hsPopedom.get("sysMgrRoleQuery") != null){
			sysMgrRoleQuery = true;
		}
		if(hsPopedom.get("sysMgrRoleUserQuery") != null){
			sysMgrRoleUserQuery = true;
		}
		if(hsPopedom.get("sysMgrRolePermissions") != null){
			sysMgrRolePermissions = true;
		}
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>角色管理</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/standard/css/table.css" rel="stylesheet" type="text/css">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
  
  <script type="text/javascript">
  <!--
  		//查询
  		function query()
  		{
  			var roleName  = document.getElementById("roleName").value;

  			myIframe.location.href="<%=request.getContextPath()%>/BMSService?actionCode=roleInfoMgr&roleName="+roleName;	
  			document.getElementById("roleName").value="";			
  		}
  		//增加
		function addData()
		{
			var result = showModalDialog("<%=request.getContextPath()%>/jsp/competenceMgr/role_add.jsp",'','dialogWidth=400px;dialogHeight=200px');
		   		
		   	if(result != null && result.length > 1)
		   	{
		   		myIframe.location.href="<%=request.getContextPath()%>/"+result;
		   		
		   		//window.close();
		   	}
		}
		//删除
		function delData()
		{     
		        var deleteName = getDelCheckName();   
		       
		        if(deleteName == null||deleteName.length <1){
		        	confirm("请选择所要删除的角色?");
		        }  
		        else{
					var del = confirm("确定删除所选角色?")
					if(del){
						myIframe.location.href='<%=request.getContextPath()%>/BMSService?actionCode=roleInfoMgr&method=ricoExecDelete&deleteStr='+deleteName;
						
					}
		        }
		}
		//获得选择的选择框的值
		function getDelCheckName()
		{
			var strDel = '';
			
			var length = myIframe.myform.elements.length;
		
			for(i=0;i<length;i++){
			    if(myIframe.myform.elements[i].name!='checkbox_all'&& myIframe.myform.elements[i].type=='checkbox'&& myIframe.myform.elements[i].checked== true){
			         strDel = strDel + myIframe.myform.elements[i].value + ',';
			    }
			}
			return strDel;
		}
		
		
		
		
		//修改
	   function updateData()
		{
			if(updateAble())
			{
				var strUrl = "<%=request.getContextPath()%>/jsp/competenceMgr/role_update.jsp";
				var param = getCheckValue();
				strUrl = strUrl +"?"+ param;
				
				var result = showModalDialog(strUrl,'','dialogWidth=400px;dialogHeight=200px');
			   	if(result != null && result.length > 1)
			   	{
			   		myIframe.location.href="<%=request.getContextPath()%>/"+result;
			   		window.close();
			   	}
		   	}
		}
		//修改数据时调用
		function updateAble(){
		 	var icount = getCheckCount();
			if(icount<1){
				alert("请选择您要修改的数据项!");
			  	return false;
			}
			if(icount>1){
			 	alert("修改时只能选择一行数据!");
			 	return false;
			}
			return true;
		}
		//获取复选框个数
		function getCheckCount()   
	  	{   
	  		var counter = 0;

	  		var length = myIframe.myform.elements.length;
		
			for(i=0;i<length;i++)
			{
			    if(myIframe.myform.elements[i].name!='checkbox_all'&& myIframe.myform.elements[i].type=='checkbox'&& myIframe.myform.elements[i].checked== true)
			    {
			         counter = counter + 1;
			    }
			}
	  		
	  		return counter;
	   }
	   //获取单个复选框的值
	   function getCheckValue()
	   {
	   	 var value = "";
	  		var length = myIframe.myform.elements.length;
		
			for(i=0;i<length;i++)
			{
			    if(myIframe.myform.elements[i].name!='checkbox_all'&& myIframe.myform.elements[i].type=='checkbox'&& myIframe.myform.elements[i].checked== true)
			    {
			         value = myIframe.myform.elements[i].id;
		  				break; 
			    }
			}
	  		
	  		return value;
	   }
	   
	   
	   //查看角色的用户列表
	function queryRoleUserList()
	{  
		var checkRole =  getDelCheckName();

		if(checkRole != null && checkRole.length > 0)
		{
			var strRoleIdLeng = checkRole.split(",");

			if( strRoleIdLeng.length == 2)
			{
				var roleId = strRoleIdLeng[0];
				
				showModalDialog('<%=request.getContextPath()%>/BMSService?actionCode=roleInfoMgr&method=ricoExecUser&roleId='+roleId,'','dialogWidth=800px;dialogHeight=300px');
					
				
			}else
			{
				confirm("只能选择一个角色!");
			}
		}        
		else{
			confirm("选择需要查用户的角色!");
		}
	}
	function openPopeodomWin()
	{
		var checkRole =  getDelCheckName();

		if(checkRole != null && checkRole.length > 0)
		{
			var strRoleIdLeng = checkRole.split(",");

			if( strRoleIdLeng.length == 2)
			{
				var roleId = strRoleIdLeng[0];
				
				var result = showModalDialog('<%=request.getContextPath()%>/BMSService?actionCode=roleInfoMgr&method=ricoExecModule&roleId='+roleId,'','dialogWidth=430px;dialogHeight=410px');	
				if(result != null && result.length > 1)
 				{
 					myIframe.location.href="<%=request.getContextPath()%>/"+result;
					
				}
			}else
			{
				confirm("只能选择一个角色!");
			}
		}        
		else{
			confirm("选择需要查权限的角色!");
		}
	}
  -->
  </script>
  </head>
  
 <body topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0" >
 <div class="con_bk">
<table width="100%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5">
    <div class="wz">
		<div id="dqwz" class="f_l">当前位置：<span>系统管理 &gt;&gt; 角色管理</span></div> 
		 <div class="font_001F56_12" align="right">
	      <input onclick="addData()" type="button" name="addBtn" value="&nbsp;&nbsp;&nbsp;添加" class="button_add"<%if(!sysMgrRoleAdd){%>disabled<%}%>>
	      <input onclick="delData()" type="button" name="delBtn" value="&nbsp;&nbsp;&nbsp;删除" class="button_delete"<%if(!sysMgrRoleDelete){%>disabled<%}%>>
	      <input onclick="updateData()" type="button" name="updateBtn" value="&nbsp;&nbsp;&nbsp;修改" class="button_edit"<%if(!sysMgrRoleUpdate){%>disabled<%}%>>
	      <input onclick="query()" type="button" name="queryBtn" value="&nbsp;&nbsp;&nbsp;查询" class="button_search"<%if(!sysMgrRoleQuery){%>disabled<%}%>>
	  </div> 
		</div>
		</td>
   </tr>
  <tr>
    <td valign="top" colspan="3">	
	<table width="100%" height="15"  border="0" cellpadding="0" cellspacing="0">
      <tr><td height="15"></td></tr>
    </table>
	
	
	<!-- ======== 查询条件开始 ======== -->
	<form id="myform" name="myform" method="post" action="">
	 <table width="99%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
	   <tr>
	     <td class="yx1" width="10%"><div align="right">角色名：</div></td>
	     <td class="xx1"><div align="left">
	    	 <input type="text" name="roleName" size="22" maxlength="50">       
	     </div></td>

	   </tr>
	    <tr>
	     <td class="query_td2" height="25" colspan="2"><div align="center">
	       <input type="button" name="" value="查看角色用户" class="button_style" onclick="queryRoleUserList();"<%if(!sysMgrRoleUserQuery){%>disabled<%}%>>
           <input type="button" name="" value="查看分配权限" class="button_style" onclick="openPopeodomWin();"<%if(!sysMgrRolePermissions){%>disabled<%}%>>
	     </div></td>
	   </tr>
	   
	 
	 </table>
	 </form>
	 <!-- ======== 查询条件结束 ======== -->
	 
	 
	 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
	   <tr><td height="15"></td></tr>
	 </table>
	 
	 
	 <!-- ======== 查询列表显示数据开始 ======== -->
	 <table width="99%" height="390" border="0" align="center" cellpadding="0" cellspacing="0">
	   <tr>
	     <td>
	 	   <iframe id="myIframe" src="<%=request.getContextPath()%>/jsp/competenceMgr/role_list.jsp" frameborder="0" width="100%" height="100%">
	 	   </iframe>
	     </td>
	   </tr>
	 </table>
	 <!-- ======== 查询列表显示数据结束 ======== -->
	 
	 
	 <!-- ======== 分页标签 ======== -->
	<iframe id="myIframe1" src="<%=request.getContextPath()%>/jsp/competenceMgr/page.jsp" frameborder="0" width="100%" height="30">
	 	   </iframe>
	
	
	</td>
  </tr>
</table>
</div>
</body>
</html>
