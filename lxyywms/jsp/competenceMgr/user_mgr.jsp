
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean sysMgrUserUpdate = false; //修改
	boolean sysMgrUserAdd = false; //添加
	boolean sysMgrUserDelete = false; //删除
	boolean sysMgrUserQuery = false; //查询
	boolean sysMgrUserRoleQuery = false; //查看分配角色
	boolean sysMgrUserPermissions = false; //查看分配权限
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("sysMgrUserUpdate") != null){
			sysMgrUserUpdate = true;
		}
		if(hsPopedom.get("sysMgrUserAdd") != null){
			sysMgrUserAdd = true;
		}
		if(hsPopedom.get("sysMgrUserDelete") != null){
			sysMgrUserDelete = true;
		}
		if(hsPopedom.get("sysMgrUserQuery") != null){
			sysMgrUserQuery = true;
		}
		if(hsPopedom.get("sysMgrUserRoleQuery") != null){
			sysMgrUserRoleQuery = true;
		}
		if(hsPopedom.get("sysMgrUserPermissions") != null){
			sysMgrUserPermissions = true;
		}
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>用户管理</title>
    
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
  			var userName  = document.getElementById("userName").value;
  			var userCode  = document.getElementById("userCode").value;
  			var flag  = document.getElementById("flag").value;
  			myIframe.location.href="<%=request.getContextPath()%>/BMSService?actionCode=userInfoMgr&userName="+userName+
  									"&userCode=" + userCode + "&flag=" + flag;	
  			document.getElementById("userName").value="";
  			document.getElementById("userCode").value="";
  			document.getElementById("flag").value="";			
  		}
  		//增加
		function addData()
		{
			var result = showModalDialog("<%=request.getContextPath()%>/jsp/competenceMgr/user_add.jsp",'','dialogWidth=600px;dialogHeight=300px');
		   		
		   	if(result != null && result.length > 1)
		   	{
		   		myIframe.location.href="<%=request.getContextPath()%>/"+result;
		   		
		   		window.close();
		   	}
		}
		//删除
		function delData()
		{     
		        var deleteName = getDelCheckName();   
		       
		        if(deleteName == null||deleteName.length <1){
		        	confirm("请选择所要删除的用户?");
		        }  
		        else{
					var del = confirm("确定删除所选用户?")
					if(del){
						myIframe.location.href='<%=request.getContextPath()%>/BMSService?actionCode=userInfoMgr&method=ricoExecDelete&deleteStr='+deleteName;
						
					}
		        }
		}
		//获得选择的选择框的值
		function getDelCheckName()
		{
			var strDel = '';
			
			var length = myIframe.myform.elements.length;
		
			for(i=0;i<length;i++){
			    if(myIframe.myform.elements[i].name=='checkbox_id'&& myIframe.myform.elements[i].type=='checkbox'&& myIframe.myform.elements[i].checked== true){
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
				var param1 = getCheckValue1();
				if(param1 != "root")
				{
					var strUrl = "<%=request.getContextPath()%>/jsp/competenceMgr/user_update.jsp";
					var param = getCheckValue();
					
					strUrl = strUrl +"?"+ param;
					
					var result = showModalDialog(strUrl,'','dialogWidth=600px;dialogHeight=300px');
				   	if(result != null && result.length > 1)
				   	{
				   		myIframe.location.href="<%=request.getContextPath()%>/"+result;
				   		window.close();
				   	}
				}else
				{
					alert("不能修改超级用户!");
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
			    if(myIframe.myform.elements[i].name=='checkbox_id'&& myIframe.myform.elements[i].type=='checkbox'&& myIframe.myform.elements[i].checked== true)
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
			    if(myIframe.myform.elements[i].name=='checkbox_id'&& myIframe.myform.elements[i].type=='checkbox'&& myIframe.myform.elements[i].checked== true)
			    {
			         value = myIframe.myform.elements[i].id;
		  				break; 
			    }
			}
	  		
	  		return value;
	   }
	   //获取单个复选框的值
	   function getCheckValue1()
	   {
	   	 var value = "";
	  		var length = myIframe.myform.elements.length;
		
			for(i=0;i<length;i++)
			{
			    if(myIframe.myform.elements[i].name=='checkbox_id'&& myIframe.myform.elements[i].type=='checkbox'&& myIframe.myform.elements[i].checked== true)
			    {
			         value = myIframe.myform.elements[i].value;
		  				break; 
			    }
			}
	  		
	  		return value;
	   }
	   
	   
	   
	     //查看用户的角色列表
	function queryRoleUserList()
	{  
		var checkRole =  getDelCheckName();

		if(checkRole != null && checkRole.length > 0)
		{
			var strRoleIdLeng = checkRole.split(",");

			if( strRoleIdLeng.length == 2)
			{
				var userId = strRoleIdLeng[0];
				if(userId != "root")
				{
						var result = showModalDialog('<%=request.getContextPath()%>/BMSService?actionCode=userInfoMgr&method=ricoExecRole&userId='+userId,'','dialogWidth=500px;dialogHeight=410px');	
							if(result != null && result.length > 1)
			           		 {
								myIframe.location.href="<%=request.getContextPath()%>/"+result;
								
							}
				}else
				{
					alert("不能操作超级用户!");
				}

			}else
			{
				confirm("只能选择一个用户!");
			}
		}        
		else{
			confirm("选择需要查角色的用户!");
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
				var userId = strRoleIdLeng[0];
				
				if(userId != "root")
				{
					var result = showModalDialog('<%=request.getContextPath()%>/BMSService?actionCode=userInfoMgr&method=ricoExecModule&userId='+userId,'','dialogWidth=500px;dialogHeight=480px');	
					if(result != null && result.length > 1)
	 				{
	 					myIframe.location.href="<%=request.getContextPath()%>/"+result;
						
					}
				}else
				{
					alert("不能操作超级用户!");
				}
			}else
			{
				confirm("只能选择一个用户!");
			}
		}        
		else{
			confirm("选择需要查权限的用户!");
		}
	}
	   
  -->
  </script>
  </head>
  
<body >
<div class="con_bk">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr><!-- ======== 当前位置 ======== --><td height="5">
	  <div class="wz">
		<div id="dqwz" class="f_l">当前位置：<span>系统管理 &gt;&gt; 用户管理</span></div>  
      <!-- ======== 增、删、改、查按钮 ======== -->
      <div align="right">
	      <input onclick="addData()" type="button" name="addBtn" value="&nbsp;&nbsp;&nbsp;添加" class="button_add"<%if(!sysMgrUserAdd){%>disabled<%}%>>
	      <input onclick="delData()" type="button" name="delBtn" value="&nbsp;&nbsp;&nbsp;删除" class="button_delete"<%if(!sysMgrUserDelete){%>disabled<%}%>>
	      <input onclick="updateData()" type="button" name="updateBtn" value="&nbsp;&nbsp;&nbsp;修改" class="button_edit"<%if(!sysMgrUserUpdate){%>disabled<%}%>>
	      <input onclick="query()" type="button" name="queryBtn" value="&nbsp;&nbsp;&nbsp;查询" class="button_search"<%if(!sysMgrUserQuery){%>disabled<%}%>>
	  </div>
	  </div>
   </td>
  </tr>
  <tr><td height="5"></td></tr>
  <tr>
    <td valign="top">		
	<!-- ======== 查询条件开始 ======== -->
	<form id="myform" name="myform" method="post" action="">
	 <table width="99%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
	   <tr>
	     <td class="yx1" height="5"><div align="right">用户名：</div></td>
	     <td class="yx"><div align="left">
	    	 <input type="text" name="userName" size="22" maxlength="50">
	       
	     </div></td>
	     <td class="yx1" ><div align="right">用户代码：</div></td>
	     <td class="yx"><div align="left">
	    	 <input type="text" name="userCode" size="22" maxlength="50">
	       
	     </div></td>
	     <td class="yx1" ><div align="right">是否可用：</div></td>
	     <td class="xx1"><div align="left">
	    	 <select name="flag">
       		<option value="" selected>请选择</option> 
     		 <option value="Y" >是</option>
             <option value="N" >否</option>         	 
      </select> 
     </div></td>
     </tr>	
      <tr>
	     <td height="25" colspan="6"><div align="center">
	       <input type="button" name="" value="查看分配角色" class="button_style" onclick="queryRoleUserList();"<%if(!sysMgrUserRoleQuery){%>disabled<%}%>>
           <input type="button" name="" value="查看分配权限" class="button_style" onclick="openPopeodomWin();"<%if(!sysMgrUserPermissions){%>disabled<%}%>>
	     </div></td>
	   </tr>   	 
	 </table>
	
	  
	 </form>
	 <!-- ======== 查询条件结束 ======== -->	 	 
	  <tr><td height="15"></td></tr>
	<tr><td> 
	 <!-- ======== 查询列表显示数据开始 ======== -->
	 <table width="100%" height="400" border="0" align="center" cellpadding="0" cellspacing="0">
	   <tr>
	     <td height="180">
	 	   <iframe id="myIframe" src="<%=request.getContextPath()%>/jsp/competenceMgr/user_list.jsp" frameborder="0" width="100%" height="100%">
	 	   </iframe>
	     </td>
	   </tr>
	 </table>
	 </td></tr>
	 <!-- ======== 查询列表显示数据结束 ======== -->
	 
	 
	 <!-- ======== 分页标签 ======== -->
	 <tr><td>
	<iframe id="myIframe1" src="<%=request.getContextPath()%>/jsp/competenceMgr/page.jsp" frameborder="0" width="100%" height="30">
	  </iframe>
	</td></tr>
	

</table>
</div>
</body>
</html>
