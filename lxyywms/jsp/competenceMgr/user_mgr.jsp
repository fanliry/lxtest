
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean sysMgrUserUpdate = false; //�޸�
	boolean sysMgrUserAdd = false; //���
	boolean sysMgrUserDelete = false; //ɾ��
	boolean sysMgrUserQuery = false; //��ѯ
	boolean sysMgrUserRoleQuery = false; //�鿴�����ɫ
	boolean sysMgrUserPermissions = false; //�鿴����Ȩ��
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
    <title>�û�����</title>
    
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
  		//��ѯ
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
  		//����
		function addData()
		{
			var result = showModalDialog("<%=request.getContextPath()%>/jsp/competenceMgr/user_add.jsp",'','dialogWidth=600px;dialogHeight=300px');
		   		
		   	if(result != null && result.length > 1)
		   	{
		   		myIframe.location.href="<%=request.getContextPath()%>/"+result;
		   		
		   		window.close();
		   	}
		}
		//ɾ��
		function delData()
		{     
		        var deleteName = getDelCheckName();   
		       
		        if(deleteName == null||deleteName.length <1){
		        	confirm("��ѡ����Ҫɾ�����û�?");
		        }  
		        else{
					var del = confirm("ȷ��ɾ����ѡ�û�?")
					if(del){
						myIframe.location.href='<%=request.getContextPath()%>/BMSService?actionCode=userInfoMgr&method=ricoExecDelete&deleteStr='+deleteName;
						
					}
		        }
		}
		//���ѡ���ѡ����ֵ
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

		
		//�޸�
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
					alert("�����޸ĳ����û�!");
				}
		
		   	}
		}
		//�޸�����ʱ����
		function updateAble(){
		 	var icount = getCheckCount();
			if(icount<1){
				alert("��ѡ����Ҫ�޸ĵ�������!");
			  	return false;
			}
			if(icount>1){
			 	alert("�޸�ʱֻ��ѡ��һ������!");
			 	return false;
			}
			return true;
		}
		//��ȡ��ѡ�����
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
	   //��ȡ������ѡ���ֵ
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
	   //��ȡ������ѡ���ֵ
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
	   
	   
	   
	     //�鿴�û��Ľ�ɫ�б�
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
					alert("���ܲ��������û�!");
				}

			}else
			{
				confirm("ֻ��ѡ��һ���û�!");
			}
		}        
		else{
			confirm("ѡ����Ҫ���ɫ���û�!");
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
					alert("���ܲ��������û�!");
				}
			}else
			{
				confirm("ֻ��ѡ��һ���û�!");
			}
		}        
		else{
			confirm("ѡ����Ҫ��Ȩ�޵��û�!");
		}
	}
	   
  -->
  </script>
  </head>
  
<body >
<div class="con_bk">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr><!-- ======== ��ǰλ�� ======== --><td height="5">
	  <div class="wz">
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>ϵͳ���� &gt;&gt; �û�����</span></div>  
      <!-- ======== ����ɾ���ġ��鰴ť ======== -->
      <div align="right">
	      <input onclick="addData()" type="button" name="addBtn" value="&nbsp;&nbsp;&nbsp;���" class="button_add"<%if(!sysMgrUserAdd){%>disabled<%}%>>
	      <input onclick="delData()" type="button" name="delBtn" value="&nbsp;&nbsp;&nbsp;ɾ��" class="button_delete"<%if(!sysMgrUserDelete){%>disabled<%}%>>
	      <input onclick="updateData()" type="button" name="updateBtn" value="&nbsp;&nbsp;&nbsp;�޸�" class="button_edit"<%if(!sysMgrUserUpdate){%>disabled<%}%>>
	      <input onclick="query()" type="button" name="queryBtn" value="&nbsp;&nbsp;&nbsp;��ѯ" class="button_search"<%if(!sysMgrUserQuery){%>disabled<%}%>>
	  </div>
	  </div>
   </td>
  </tr>
  <tr><td height="5"></td></tr>
  <tr>
    <td valign="top">		
	<!-- ======== ��ѯ������ʼ ======== -->
	<form id="myform" name="myform" method="post" action="">
	 <table width="99%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
	   <tr>
	     <td class="yx1" height="5"><div align="right">�û�����</div></td>
	     <td class="yx"><div align="left">
	    	 <input type="text" name="userName" size="22" maxlength="50">
	       
	     </div></td>
	     <td class="yx1" ><div align="right">�û����룺</div></td>
	     <td class="yx"><div align="left">
	    	 <input type="text" name="userCode" size="22" maxlength="50">
	       
	     </div></td>
	     <td class="yx1" ><div align="right">�Ƿ���ã�</div></td>
	     <td class="xx1"><div align="left">
	    	 <select name="flag">
       		<option value="" selected>��ѡ��</option> 
     		 <option value="Y" >��</option>
             <option value="N" >��</option>         	 
      </select> 
     </div></td>
     </tr>	
      <tr>
	     <td height="25" colspan="6"><div align="center">
	       <input type="button" name="" value="�鿴�����ɫ" class="button_style" onclick="queryRoleUserList();"<%if(!sysMgrUserRoleQuery){%>disabled<%}%>>
           <input type="button" name="" value="�鿴����Ȩ��" class="button_style" onclick="openPopeodomWin();"<%if(!sysMgrUserPermissions){%>disabled<%}%>>
	     </div></td>
	   </tr>   	 
	 </table>
	
	  
	 </form>
	 <!-- ======== ��ѯ�������� ======== -->	 	 
	  <tr><td height="15"></td></tr>
	<tr><td> 
	 <!-- ======== ��ѯ�б���ʾ���ݿ�ʼ ======== -->
	 <table width="100%" height="400" border="0" align="center" cellpadding="0" cellspacing="0">
	   <tr>
	     <td height="180">
	 	   <iframe id="myIframe" src="<%=request.getContextPath()%>/jsp/competenceMgr/user_list.jsp" frameborder="0" width="100%" height="100%">
	 	   </iframe>
	     </td>
	   </tr>
	 </table>
	 </td></tr>
	 <!-- ======== ��ѯ�б���ʾ���ݽ��� ======== -->
	 
	 
	 <!-- ======== ��ҳ��ǩ ======== -->
	 <tr><td>
	<iframe id="myIframe1" src="<%=request.getContextPath()%>/jsp/competenceMgr/page.jsp" frameborder="0" width="100%" height="30">
	  </iframe>
	</td></tr>
	

</table>
</div>
</body>
</html>
