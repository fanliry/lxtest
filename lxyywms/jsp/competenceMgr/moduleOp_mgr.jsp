
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean upd_moduleOp = false; //�޸�
	boolean del_moduleOp = false; //ɾ��
	boolean query_moduleOp = false; //��ѯ
	boolean add_submodule = false; //�޸�
	boolean add_adjacentmodule = true; //ɾ��
	boolean del_module = true; //��ѯ
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("upd_moduleOp") != null){
			upd_moduleOp = true;
		}
		if(hsPopedom.get("del_moduleOp") != null){
			del_moduleOp = true;
		}
		if(hsPopedom.get("query_moduleOp") != null){
			query_moduleOp = true;
		}
		if(hsPopedom.get("add_submodule") != null){
			add_submodule = true;
		}
		if(hsPopedom.get("add_adjacentmodule") != null){
			add_adjacentmodule = true;
		}
		if(hsPopedom.get("del_module") != null){
			del_module = true;
		}
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>ģ�����</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/standard/css/table.css" rel="stylesheet" type="text/css">
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/css/dhtmlXTree.css">
<script  src="<%=request.getContextPath()%>/standard/js/hashMap.js"></script>
<script  src="<%=request.getContextPath()%>/standard/js/dhtmlXCommon.js"></script>
<script  src="<%=request.getContextPath()%>/standard/js/dhtmlXTree.js"></script>	
<script  src="<%=request.getContextPath()%>/standard/js/tools.js"></script>	
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
	<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/menuTree.js"></script>

  <script type="text/javascript">
  <!--
  		//��ѯ
  		function query()
  		{
  			var modOpCode  = document.getElementById("modOpCode").value;
  			var modOpName  = document.getElementById("modOpName").value;
  			var pmodOpCode  = document.getElementById("pmodOpCode").value;
  			var pmodOpName  = document.getElementById("pmodOpName").value;
  			myIframe.location.href="<%=request.getContextPath()%>/BMSService?actionCode=moduleOpMgr&modOpCode="+modOpCode+
  													"&modOpName=" + modOpName +
  													"&pmodOpCode=" + pmodOpCode +
  													"&pmodOpName=" + pmodOpName;	
  			document.getElementById("modOpCode").value="";
  			document.getElementById("modOpName").value="";
  			document.getElementById("pmodOpCode").value="";
  			document.getElementById("pmodOpName").value="";			
  		}
  		//ɾ��
		function delData()
		{     
		        var deleteName = getDelCheckName();   
		       
		        if(deleteName == null||deleteName.length <1){
		        	confirm("��ѡ����Ҫɾ����ģ��?");
		        }  
		        else{
					var del = confirm("ȷ��ɾ����ѡģ��?")
					if(del){
						myIframe.location.href='<%=request.getContextPath()%>/BMSService?actionCode=moduleOpMgr&method=ricoExecDelete&deleteStr='+deleteName;
						
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
  		//������
		function viewTree()
		{
			menuTree.getModuleTree(drawTree);
		}
		function drawTree(data)
		{
			drawFieldTree(data);
		}
		 function deleteNode(ItemId)
		 {
    		var del = confirm("ȷ��ɾ����ѡ�ڵ�")
			if(del){
			
			myIframe.location.href='<%=request.getContextPath()%>/BMSService?actionCode=moduleOpMgr&method=ricoExecDelete&deleteStr='+ItemId+',';
			
			}
    	}
  		//�����½ڵ�
		function addNewNode(flag)
		{
			var tmpParam;
			//��ǰ��ѡ��Ľڵ�
			var selectedId = tree.getSelectedItemId();
			if(selectedId == "")
			{
				selectedId = "0";
			}
			var selectLevel = "1";  //�ڵ㼶��
			
			var parentId = "0";			//��ID
			var parentCode = "root";	//������
			var parentName = "���ڵ�";	//��ģ����
			if(flag=='1')	//�������ڽڵ�
			{
				if(selectedId != "0")//���Ǹ����
				{
					parentId = tree.getParentId(selectedId);
					parentName = tree.getItemText(parentId);
					parentCode = tree.getItemCode(parentId);
					selectLevel=tree.getLevel(selectedId);
				}
				//����
				tmpParam = openwin(parentId, parentCode, parentName, selectLevel);
				
			}else			//�����ӽڵ�
			{
				if(selectedId != "0")//���Ǹ����
				{
					parentId = selectedId;
					parentName = tree.getItemText(parentId);
					parentCode = tree.getItemCode(parentId);
					var slevel=tree.getLevel(selectedId);
					selectLevel = parseInt(slevel)+1;
				}
				//����
				tmpParam = openwin(parentId, parentCode, parentName, selectLevel);
				
			}
			if(tmpParam != null)
			{
				window.myIframe.location.href="<%=request.getContextPath()%>/BMSService?actionCode=moduleOpMgr&method=ricoExecAdd&"+tmpParam;
	   			window.close();
			}
		}
		//������ģ����Ϣ����
        function openwin(parentId,parentCode, parentName, Level)
        {
             var   result=showModalDialog('<%=request.getContextPath()%>/jsp/competenceMgr/moduleOp_add.jsp?parentId='+parentId+'&parentCode=' + parentCode
             + '&parentName='+parentName+ '&modOpLevel='+Level,'','dialogWidth=600px;dialogHeight=300px');
             
             return result;
		}
  		
  		
  		
  		
  		
  		
  		
  		
  		

		
		
		//�޸�
	   function updateData()
		{
			if(updateAble())
			{
				var strUrl = "<%=request.getContextPath()%>/jsp/competenceMgr/moduleOp_update.jsp";
				var param = getCheckValue();
				strUrl = strUrl +"?"+ param;
				
				var result = showModalDialog(strUrl,'','dialogWidth=600px;dialogHeight=300px');
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
  -->
  </script>
  </head>
  
 <body topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0" onload="javascript:viewTree();">
<div class="con_bk">
<table width="100%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr><td height="5">
   <div class="wz">
	  <div id="dqwz" class="f_l">��ǰλ�ã�<span>ϵͳ���� &gt;&gt; ģ�����</span></div>
      <!-- ======== ����ɾ���ġ��鰴ť ======== -->
      <div class="font_001F56_12" align="right">
	     <input onclick="query()" type="button" name="queryBtn" value="&nbsp;&nbsp;&nbsp;��ѯ" class="button_search"<%if(!query_moduleOp){%>disabled<%}%>>
	      <input onclick="delData()" type="button" name="delBtn" value="&nbsp;&nbsp;&nbsp;ɾ��" class="button_delete"<%if(!del_moduleOp){%>disabled<%}%>>
	      <input onclick="updateData()" type="button" name="updateBtn" value="&nbsp;&nbsp;&nbsp;�޸�" class="button_edit"<%if(!upd_moduleOp){%>disabled<%}%>>     
	  </div>
 </div></td>
  </tr>
  <tr><td height="5"></td></tr>
  <tr>
    <td valign="top" colspan="3">
	<!-- ======== ��ѯ������ʼ ======== -->
	<form id="myform" name="myform" method="post" action="">
	 <table width="99%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
	   <tr>
	     <td class="yx1"><div align="right">ģ��������룺</div></td>
	     <td class="yx"><div align="left">
	    	 <input type="text" name="modOpCode" size="22" maxlength="50">
	     </div></td>
		<td class="yx1"><div align="right">ģ���������</div></td>
	     <td class="xx1"><div align="left">
	    	 <input type="text" name="modOpName" size="22" maxlength="50">
	     </div></td>
	   </tr>
	   <tr>
	     <td class="y1"><div align="right">��ģ����룺</div></td>
	     <td class="x"><div align="left">
	    	 <input type="text" name="pmodOpCode" size="22" maxlength="50">
	     </div></td>
		<td class="y1"><div align="right">��ģ������</div></td>
	     <td><div align="left">
	    	 <input type="text" name="pmodOpName" size="22" maxlength="50">
	     </div></td>
	   </tr>
   
	 </table>
	 </form>
	 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
	   <tr><td height="5"></td></tr>
	 </table>
	 <!-- ======== ��ѯ�������� ======== -->
	  <table width="99%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
	   <tr>
	     <td class="query_td1" align="left">
	      <input onclick="addNewNode('2');" type="button" name="addBtn" value="&nbsp;�����ģ��" class="button_add"<%if(!add_submodule){%>disabled<%}%>>
	      <input onclick="addNewNode('1');" type="button" name="delBtn" value="&nbsp;�������ģ��" class="button_delete"<%if(!add_adjacentmodule){%>disabled<%}%>>    
	      <input onclick="deleteNode(tree.getSelectedItemId());" type="button" name="updateBtn" value="&nbsp;ɾ��ģ��" class="button_edit"<%if(!del_module){%>disabled<%}%>> 
	     </td>
	   </tr>   
 	</table>
	 
	 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
	   <tr><td height="5"></td></tr>
	 </table>
	 
	 
	 <!-- ======== ��ѯ�б���ʾ���ݿ�ʼ ======== -->
	 <table width="99%" height="70%" border="0" align="center" cellpadding="0" cellspacing="0">
	   <tr>
	   	 <td width="220">
	         <!-- ======== ��ѯ�б�1��ʾ���ݿ�ʼ ======== -->
			 <table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
			   <tr>
			     <td>
			 	   		<div id="treeboxbox_tree" style="width:100%; height:100%;background-color:#EDF2F9; overflow:auto;"></div>
			     </td>
			   </tr>
			 </table>
			 <!-- ======== ��ѯ�б�1��ʾ���ݽ��� ======== -->
	     </td>
	     <td>
	 	   <iframe id="myIframe" src="<%=request.getContextPath()%>/jsp/competenceMgr/moduleOp_list.jsp" frameborder="0" width="100%" height="100%">
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
<script>
		
			var tree = null;
  			function drawFieldTree(xmlValue)
  			{
  				if(tree =='undefined' || tree == null)
  				{
					tree=new dhtmlXTreeObject("treeboxbox_tree","100%","100%",0);
					tree.setImagePath("<%=request.getContextPath()%>/standard/images/");
			
					tree.enableDragAndDrop(1);//��������
			
					tree.setDragHandler(myDragHandler);
					
					
				}
				deleteType();
				tree.loadXMLString(xmlValue);
			}
			function myDragHandler(idFrom,idTo){
				//if we return false then drag&drop be aborted
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
			
			
			
			//selectNodeCode ��ѡ�ڵ����
			//oldLevel ԭ���ļ���
			//oldParentCode ԭ���ĸ��ڵ�
			//newLevel �µļ���
			//newParentCode �¸��ڵ�			
			function afterDragProcess(selectNodeCode, selectNode, oldLevel, oldParentCode, newLevel, newParentCode)
			{
			  //alert("��ѡ�ڵ����="+selectNodeCode + "   ԭ���ļ���="+ oldLevel +"   ԭ���ĸ��ڵ�="+oldParentCode+"   �µļ���="+newLevel+ "�¸��ڵ�=  "+newParentCode);
			  
			   var parentId = "0";
			   var parentCode = "root";
			   var parentName = "���ڵ�";
			   if(newParentCode != "0")
			   {
			   		parentId = newParentCode;
			   		parentName = tree.getItemText(parentId);
					parentCode = tree.getItemCode(parentId);
			   }
				
			  
			   myIframe.location.href='<%=request.getContextPath()%>/BMSService?actionCode=moduleOpMgr&method=ricoExecLevl'
			   						+'&modOpId='+selectNodeCode+'&parentId='+parentId+'&parentCode='+parentCode+'&parentName='+parentName+'&lvl='+newLevel;
			}

	</script>
 
 
</body>
</html>
