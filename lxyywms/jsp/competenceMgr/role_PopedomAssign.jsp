<%@ page contentType = "text/html;charset=gb2312"%>
<%@ page import="com.ricosoft.entity.competenceMgr.RoleInfo" %>



<html>
<head>
<title>��ɫȨ�޷���</title>

  <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/standard/css/table.css" rel="stylesheet" type="text/css">
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/standard/css/dhtmlXTree.css">
<script  src="<%=request.getContextPath()%>/standard/js/hashMap.js"></script>
<script  src="<%=request.getContextPath()%>/standard/js/dhtmlXCommon.js"></script>
<script  src="<%=request.getContextPath()%>/standard/js/dhtmlXTree.js"></script>	
<script  src="<%=request.getContextPath()%>/standard/js/tools.js"></script>	
</head>

<body>
<script>
<!--
    
	//�����޸ĵ�Ȩ��
    function saveAssignPopedom(roleId, roleName){
    	var allCheck = tree.getAllChecked()+","+tree.getAllPartiallyChecked();
		allCheck = allCheck+',';
		//window.location.href
		var strParam ='BMSService?actionCode=roleInfoMgr&method=ricoExecModuleAdd&assignPopedom='+allCheck
								+'&roleId='+roleId;
		window.returnValue=strParam;
		        	
		 window.close();
    }
     
    function closeW(){
    	window.close();
    }
  -->
</script>
   <%
        String mgrLoad="";

  		String strRoleName = "";
  		String strRoleId = "";
   		//����Ȩ��
   		 String strHasPopedom = "";
   		 RoleInfo role = null;   
   		 
  		 request.setCharacterEncoding("ISO-8859-1"); 
 
    
   if(request.getAttribute("modTree")!= null )
   {
      	mgrLoad = (String)request.getAttribute("modTree");	   			  	    		
   }
     if(request.getAttribute("popedom")!= null )
   {
      	strHasPopedom = (String)request.getAttribute("popedom");	   			  	    		
   }
    if(request.getAttribute("role")!= null )
   {
      	role = (RoleInfo)request.getAttribute("role");	   			  	    		
   }
   if(role != null)
   {
   		strRoleName = role.getM_strRoleName();
   		strRoleId = role.getM_strRoleId();
   }
 
  %>
<form method="post" action="">
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
    <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�ϵͳ���� -&gt; ��ɫ���� -&gt; ��ɫȨ�޷���</div></td>
   </tr>
 </table>
 
 <table width="400"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
   <tr>
     <td height="25" class="y2"><div align="right">��ɫ����</div></td>
     <td class="add_td2"><div align="left"><input type="text" name="roleName" class="input_readonly" readonly="true" value="<%=strRoleName%>"></div></td>
    
   </tr>
 </table>
 
</form>
 
  <table width="60%" height="20"  border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
	  <td><div align="center" class="font_001F56_bold_12">���ԶԽ�ɫ������Ȩ�޽��з���:</div></td>
    </tr>
  </table>
  
  <table width="250" height="200"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
	<tr>
	  <td valign="top" class="TD_STYLE2">
	  
	  	<div id="treeboxbox_tree" style="width:250; height:218;background-color:#EDF2F9; overflow:auto;"></div>
	  </td>
    </tr>
  </table>  

  <table width="80%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="40"><div align="center">
	  
	    <input type="button" name="Submit2" value="ȷ��" onClick="saveAssignPopedom('<%=strRoleId%>','<%=strRoleName%>');" class="button_style">
	   &nbsp; 
	   <input type="button" name="cancer" value="�ر�" class="button_style" onclick="closeW();"></div></td> 
   </tr>
  </table>
  	<script>
		tree=new dhtmlXTreeObject("treeboxbox_tree","100%","100%",0);
		tree.setImagePath("<%=request.getContextPath()%>/standard/images/");
		tree.enableCheckBoxes(1);
		tree.enableThreeStateCheckboxes(true);
		tree.loadXMLString("<%=mgrLoad%>");
		tree.setHasedChecked("<%=strHasPopedom%>")
	</script>
</body>
</html>