
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean sysMgrRoleAdd = false; //���
	boolean sysMgrRoleDelete = false; //ɾ��
	boolean sysMgrRoleUpdate = false; //�޸�
	boolean sysMgrRoleQuery = false; //��ѯ
	boolean sysMgrRoleUserQuery = false; //�鿴��ɫ�û�
	boolean sysMgrRolePermissions = false; //�鿴����Ȩ��
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
    <title>��ɫ����</title>
    
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
  			var roleName  = document.getElementById("roleName").value;

  			myIframe.location.href="<%=request.getContextPath()%>/BMSService?actionCode=roleInfoMgr&roleName="+roleName;	
  			document.getElementById("roleName").value="";			
  		}
  		//����
		function addData()
		{
			var result = showModalDialog("<%=request.getContextPath()%>/jsp/competenceMgr/role_add.jsp",'','dialogWidth=400px;dialogHeight=200px');
		   		
		   	if(result != null && result.length > 1)
		   	{
		   		myIframe.location.href="<%=request.getContextPath()%>/"+result;
		   		
		   		//window.close();
		   	}
		}
		//ɾ��
		function delData()
		{     
		        var deleteName = getDelCheckName();   
		       
		        if(deleteName == null||deleteName.length <1){
		        	confirm("��ѡ����Ҫɾ���Ľ�ɫ?");
		        }  
		        else{
					var del = confirm("ȷ��ɾ����ѡ��ɫ?")
					if(del){
						myIframe.location.href='<%=request.getContextPath()%>/BMSService?actionCode=roleInfoMgr&method=ricoExecDelete&deleteStr='+deleteName;
						
					}
		        }
		}
		//���ѡ���ѡ����ֵ
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
		
		
		
		
		//�޸�
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
			    if(myIframe.myform.elements[i].name!='checkbox_all'&& myIframe.myform.elements[i].type=='checkbox'&& myIframe.myform.elements[i].checked== true)
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
			    if(myIframe.myform.elements[i].name!='checkbox_all'&& myIframe.myform.elements[i].type=='checkbox'&& myIframe.myform.elements[i].checked== true)
			    {
			         value = myIframe.myform.elements[i].id;
		  				break; 
			    }
			}
	  		
	  		return value;
	   }
	   
	   
	   //�鿴��ɫ���û��б�
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
				confirm("ֻ��ѡ��һ����ɫ!");
			}
		}        
		else{
			confirm("ѡ����Ҫ���û��Ľ�ɫ!");
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
				confirm("ֻ��ѡ��һ����ɫ!");
			}
		}        
		else{
			confirm("ѡ����Ҫ��Ȩ�޵Ľ�ɫ!");
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
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>ϵͳ���� &gt;&gt; ��ɫ����</span></div> 
		 <div class="font_001F56_12" align="right">
	      <input onclick="addData()" type="button" name="addBtn" value="&nbsp;&nbsp;&nbsp;���" class="button_add"<%if(!sysMgrRoleAdd){%>disabled<%}%>>
	      <input onclick="delData()" type="button" name="delBtn" value="&nbsp;&nbsp;&nbsp;ɾ��" class="button_delete"<%if(!sysMgrRoleDelete){%>disabled<%}%>>
	      <input onclick="updateData()" type="button" name="updateBtn" value="&nbsp;&nbsp;&nbsp;�޸�" class="button_edit"<%if(!sysMgrRoleUpdate){%>disabled<%}%>>
	      <input onclick="query()" type="button" name="queryBtn" value="&nbsp;&nbsp;&nbsp;��ѯ" class="button_search"<%if(!sysMgrRoleQuery){%>disabled<%}%>>
	  </div> 
		</div>
		</td>
   </tr>
  <tr>
    <td valign="top" colspan="3">	
	<table width="100%" height="15"  border="0" cellpadding="0" cellspacing="0">
      <tr><td height="15"></td></tr>
    </table>
	
	
	<!-- ======== ��ѯ������ʼ ======== -->
	<form id="myform" name="myform" method="post" action="">
	 <table width="99%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
	   <tr>
	     <td class="yx1" width="10%"><div align="right">��ɫ����</div></td>
	     <td class="xx1"><div align="left">
	    	 <input type="text" name="roleName" size="22" maxlength="50">       
	     </div></td>

	   </tr>
	    <tr>
	     <td class="query_td2" height="25" colspan="2"><div align="center">
	       <input type="button" name="" value="�鿴��ɫ�û�" class="button_style" onclick="queryRoleUserList();"<%if(!sysMgrRoleUserQuery){%>disabled<%}%>>
           <input type="button" name="" value="�鿴����Ȩ��" class="button_style" onclick="openPopeodomWin();"<%if(!sysMgrRolePermissions){%>disabled<%}%>>
	     </div></td>
	   </tr>
	   
	 
	 </table>
	 </form>
	 <!-- ======== ��ѯ�������� ======== -->
	 
	 
	 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
	   <tr><td height="15"></td></tr>
	 </table>
	 
	 
	 <!-- ======== ��ѯ�б���ʾ���ݿ�ʼ ======== -->
	 <table width="99%" height="390" border="0" align="center" cellpadding="0" cellspacing="0">
	   <tr>
	     <td>
	 	   <iframe id="myIframe" src="<%=request.getContextPath()%>/jsp/competenceMgr/role_list.jsp" frameborder="0" width="100%" height="100%">
	 	   </iframe>
	     </td>
	   </tr>
	 </table>
	 <!-- ======== ��ѯ�б���ʾ���ݽ��� ======== -->
	 
	 
	 <!-- ======== ��ҳ��ǩ ======== -->
	<iframe id="myIframe1" src="<%=request.getContextPath()%>/jsp/competenceMgr/page.jsp" frameborder="0" width="100%" height="30">
	 	   </iframe>
	
	
	</td>
  </tr>
</table>
</div>
</body>
</html>
