<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean sysMgrUserGroupAdd = false; //�޸�
	boolean sysMgrUserGroupDelete = false; //���
	boolean sysMgrUserGroupUpdate = false; //ɾ��
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("sysMgrUserGroupAdd") != null){
			sysMgrUserGroupAdd = true;
		}
		if(hsPopedom.get("sysMgrUserGroupDelete") != null){
			sysMgrUserGroupDelete = true;
		}
		if(hsPopedom.get("sysMgrUserGroupUpdate") != null){
			sysMgrUserGroupUpdate = true;
		}
}
%>
<html>
<head>
  <title>�ִ����͹���ϵͳ</title>
  <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
    <link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/standard/css/dhtmlXTree.css">
	<script  src="<%=request.getContextPath()%>/standard/js/hashMap.js"></script>
	<script  src="<%=request.getContextPath()%>/standard/js/dhtmlXCommon.js"></script>
	<script  src="<%=request.getContextPath()%>/standard/js/dhtmlXTree.js"></script>	
	<script  src="<%=request.getContextPath()%>/standard/js/tools.js"></script>	

    <script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/userGroupTree.js"></script>
  <script type="text/javascript">
  <!--
 	//������
	function viewTree()
	{	
		userGroupTree.getUserGroupTree(drawTree);
	}
	function drawTree(data)
	{
		drawDocTree(data);
	}
	
	//����
	function addNode()
	{
		var selectedId = tree.getSelectedItemId();

		var   result = showModalDialog("<%=request.getContextPath()%>/jsp/competenceMgr/userGroup_add.jsp",'','dialogWidth=500px;dialogHeight=250px');
	   		
	   		if(result != null && result.length > 1)
	   		{
	   			window.location.href="<%=request.getContextPath()%>/"+result;
	   	
	   		} 
	}
	//ɾ��
	function deleteNode()
	{
		var id = tree.getSelectedItemId();
		if(id == "001")
		{
			alert("��ѡ���û���!");
		}else
		{
			var del = confirm("ȷ��Ҫɾ����ѡ�û�����?")
				if(del)
				{
					userGroupTree.deleteUserGroupTree(id, deleteMesg);
				}
		}
	}
	//ɾ���û�����Ϣ
	function deleteMesg(data)
	{
		alert(data);
		viewTree();
	}
	//�޸�
	function updateUserGroup()
	{
		var id = tree.getSelectedItemId();
		if(id=="" || id=="001")
		{
			alert("��ѡ���û���!");
		}else
		{
			var name = tree.getItemText(id);
			var groupText = document.getElementById("textarea").value;
			
			var  result = showModalDialog("<%=request.getContextPath()%>/jsp/competenceMgr/userGroup_update.jsp?id="+id+"&name="+name+"&text="+groupText,'','dialogWidth=500px;dialogHeight=250px');
	   		
	   		if(result != null && result.length > 1)
	   		{
	   			window.location.href="<%=request.getContextPath()%>/"+result;

	   		} 
		}
	}
  -->
  </script>
</head>

<body topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0"  onload="javascript:viewTree();">
<div class="con_bk">
<table width="100%" height="100%"align="center" border="0" cellspacing="0" cellpadding="0">
  <tr><td height="5px";>
      <div class="wz">
		<div id="dqwz" class="f_l">��ǰλ�ã�<span>������Ϣ &gt;&gt; �û������</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<%if(sysMgrUserGroupUpdate){%><li class="tubiao1"><a href="#" onclick="updateUserGroup()">�޸�</a></li><%}%>
			<%if(sysMgrUserGroupDelete){%><li class="tubiao1"><a href="#" onclick="deleteNode()">ɾ��</a></li><%}%>
			<%if(sysMgrUserGroupAdd){%><li class="tubiao1"><a href="#" onclick="addNode()">���</a></li><%}%>
		  </ul>
		</div>
	  </div>
   </td></tr>  
   <tr><td height="5"></td></tr>
   <tr><td>
 	<!-- ======== ��ѯ������ʼ ======== -->
	<form id="myform" name="myform" method="post" action="">
	 <table width="100%" height="98%" border="0" align="center" bordercolor="#FFFFFF" cellpadding="0" cellspacing="0">
	   <tr>
	     <td width="220">
	         <!-- ======== ��ѯ�б�1��ʾ���ݿ�ʼ ======== -->
			 <table width="92%" height="100%" border="1" align="center" cellpadding="0" cellspacing="0" class="outer_table">
			   <tr>
			     <td class="outer_td" valign="top">
			 	   		<div id="treeboxbox_tree" style="width:100%; height:100%;background-color:#EDF2F9; overflow:auto;"></div>
			     </td>
			   </tr>
			 </table>
			 <!-- ======== ��ѯ�б�1��ʾ���ݽ��� ======== -->
	     </td>
	     <td width="2">
	     </td>
	     <td width="995">
	         <!-- ======== ��ѯ�б�2��ʾ���ݿ�ʼ ======== -->
			 <table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="outer_table">
			   <tr>
			     <td class="outer_td" align="left" valign="top">

				         <table class="table_add1" width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				           <tr>
				             <td class="yx1" width="12%" height="20" align="right">����룺</td>
				             <td class="xx1" width="88%"><input name="groupCode" type="text" size="27" readonly></td>
				           </tr>
						   <tr>
				             <td class="yx1" width="12%" height="20"><div align="right">������</div></td>
				             <td class="xx1" width="88%"><input name="groupName" type="text" size="27" readonly></td>
				           </tr>
						   <tr>
				             <td class="yx1" width="12%" height="30"><div align="right">��˵����</div></td>
				             <td class="xx1" width="88%"><textarea name="textarea" cols="2" rows="3" readonly="readonly" style="width:550px;height:40px"></textarea></td>
				           </tr>
						   <tr>
				             <td class="yx1" width="12%" height="20"><div align="right">�������룺</div></td>
				             <td class="xx1" width="88%"><input name="code" type="text" size="27" readonly></td>
				           </tr>
						   <tr>
				             <td class="yx1" width="12%" height="20"><div align="right">�ϼ����룺</div></td>
				             <td class="xx1" width="88%"><input name="parentCode" type="text" size="27" readonly></td>
				           </tr>
				           </table>
			     </td>
			   </tr>
			 </table>
			 <!-- ======== ��ѯ�б�2��ʾ���ݽ��� ======== -->
	 </table>
	 </form>
	 <!-- ======== ��ѯ�������� ======== -->	
 	</td></tr>
	</table>
	</div>	
<script>
  			var tree = null;
  			function drawDocTree(xmlValue)
  			{
  				
  				if(tree =='undefined' || tree == null)
  				{
  					
					tree=new dhtmlXTreeObject("treeboxbox_tree","100%","100%",0);
					tree.setImagePath("<%=request.getContextPath()%>/standard/images/");
					
					tree.setDragHandler(myDragHandler);
  					tree.setOnClickHandler(tonclick);//�����ʾ�¼�
  				
  				}
  					deleteType();
  				
					tree.loadXMLString(xmlValue);
  				
  			}
			function myDragHandler(idFrom,idTo)
			{
				return true;
			}

			function deleteType()
     	 	{
     	          var selNode = tree._globalIdStorageFind("0");
		 		  var Nodes=selNode.childNodes;			
		 		 var  ndel = selNode.childsCount;	
		 				 
				 try{
			
						 for(var i=ndel-1; i >=0 ; i--){
					   	 var tmpNode = Nodes[i];
					   	 
							tree.deleteItem(tmpNode.id,true);
						}
						tree.clearMap();	/*���HashMap*/
		    		}
			   	catch(e){
			   	
			   	} 
	     	}
			function tonclick(id)
			{
				var name =tree.getItemText(id);
				var parentCode = tree.getParentId(id);
				if(parentCode == "0")
				{
					parentCode = "";
				}
				var groupCode = "001";
				if(id != "001")
				{
					groupCode = id.substring(3,6);
				}
				 
				document.getElementById("groupCode").value=id;
				document.getElementById("code").value=groupCode;
				document.getElementById("groupName").value=name;
				document.getElementById("parentCode").value=parentCode;
				
				userGroupTree.getUserGroupText(id, groupText);
			}
			function groupText(data)
			{
				if(data == null)
				{
					data = "";
				}
				document.getElementById("textarea").value=data;
			}
	</script>
</body>
</html>
