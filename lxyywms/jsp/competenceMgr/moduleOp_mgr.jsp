
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean upd_moduleOp = false; //修改
	boolean del_moduleOp = false; //删除
	boolean query_moduleOp = false; //查询
	boolean add_submodule = false; //修改
	boolean add_adjacentmodule = true; //删除
	boolean del_module = true; //查询
	
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
    <title>模块管理</title>
    
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
  		//查询
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
  		//删除
		function delData()
		{     
		        var deleteName = getDelCheckName();   
		       
		        if(deleteName == null||deleteName.length <1){
		        	confirm("请选择所要删除的模块?");
		        }  
		        else{
					var del = confirm("确定删除所选模块?")
					if(del){
						myIframe.location.href='<%=request.getContextPath()%>/BMSService?actionCode=moduleOpMgr&method=ricoExecDelete&deleteStr='+deleteName;
						
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
  		//生成树
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
    		var del = confirm("确定删除所选节点")
			if(del){
			
			myIframe.location.href='<%=request.getContextPath()%>/BMSService?actionCode=moduleOpMgr&method=ricoExecDelete&deleteStr='+ItemId+',';
			
			}
    	}
  		//增加新节点
		function addNewNode(flag)
		{
			var tmpParam;
			//当前所选择的节点
			var selectedId = tree.getSelectedItemId();
			if(selectedId == "")
			{
				selectedId = "0";
			}
			var selectLevel = "1";  //节点级别
			
			var parentId = "0";			//父ID
			var parentCode = "root";	//父代码
			var parentName = "根节点";	//父模块名
			if(flag=='1')	//增加相邻节点
			{
				if(selectedId != "0")//不是根结点
				{
					parentId = tree.getParentId(selectedId);
					parentName = tree.getItemText(parentId);
					parentCode = tree.getItemCode(parentId);
					selectLevel=tree.getLevel(selectedId);
				}
				//增加
				tmpParam = openwin(parentId, parentCode, parentName, selectLevel);
				
			}else			//增加子节点
			{
				if(selectedId != "0")//不是根结点
				{
					parentId = selectedId;
					parentName = tree.getItemText(parentId);
					parentCode = tree.getItemCode(parentId);
					var slevel=tree.getLevel(selectedId);
					selectLevel = parseInt(slevel)+1;
				}
				//增加
				tmpParam = openwin(parentId, parentCode, parentName, selectLevel);
				
			}
			if(tmpParam != null)
			{
				window.myIframe.location.href="<%=request.getContextPath()%>/BMSService?actionCode=moduleOpMgr&method=ricoExecAdd&"+tmpParam;
	   			window.close();
			}
		}
		//打开增加模块信息窗口
        function openwin(parentId,parentCode, parentName, Level)
        {
             var   result=showModalDialog('<%=request.getContextPath()%>/jsp/competenceMgr/moduleOp_add.jsp?parentId='+parentId+'&parentCode=' + parentCode
             + '&parentName='+parentName+ '&modOpLevel='+Level,'','dialogWidth=600px;dialogHeight=300px');
             
             return result;
		}
  		
  		
  		
  		
  		
  		
  		
  		
  		

		
		
		//修改
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
  -->
  </script>
  </head>
  
 <body topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0" onload="javascript:viewTree();">
<div class="con_bk">
<table width="100%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr><td height="5">
   <div class="wz">
	  <div id="dqwz" class="f_l">当前位置：<span>系统管理 &gt;&gt; 模块管理</span></div>
      <!-- ======== 增、删、改、查按钮 ======== -->
      <div class="font_001F56_12" align="right">
	     <input onclick="query()" type="button" name="queryBtn" value="&nbsp;&nbsp;&nbsp;查询" class="button_search"<%if(!query_moduleOp){%>disabled<%}%>>
	      <input onclick="delData()" type="button" name="delBtn" value="&nbsp;&nbsp;&nbsp;删除" class="button_delete"<%if(!del_moduleOp){%>disabled<%}%>>
	      <input onclick="updateData()" type="button" name="updateBtn" value="&nbsp;&nbsp;&nbsp;修改" class="button_edit"<%if(!upd_moduleOp){%>disabled<%}%>>     
	  </div>
 </div></td>
  </tr>
  <tr><td height="5"></td></tr>
  <tr>
    <td valign="top" colspan="3">
	<!-- ======== 查询条件开始 ======== -->
	<form id="myform" name="myform" method="post" action="">
	 <table width="99%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
	   <tr>
	     <td class="yx1"><div align="right">模块操作代码：</div></td>
	     <td class="yx"><div align="left">
	    	 <input type="text" name="modOpCode" size="22" maxlength="50">
	     </div></td>
		<td class="yx1"><div align="right">模块操作名：</div></td>
	     <td class="xx1"><div align="left">
	    	 <input type="text" name="modOpName" size="22" maxlength="50">
	     </div></td>
	   </tr>
	   <tr>
	     <td class="y1"><div align="right">父模块代码：</div></td>
	     <td class="x"><div align="left">
	    	 <input type="text" name="pmodOpCode" size="22" maxlength="50">
	     </div></td>
		<td class="y1"><div align="right">父模块名：</div></td>
	     <td><div align="left">
	    	 <input type="text" name="pmodOpName" size="22" maxlength="50">
	     </div></td>
	   </tr>
   
	 </table>
	 </form>
	 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
	   <tr><td height="5"></td></tr>
	 </table>
	 <!-- ======== 查询条件结束 ======== -->
	  <table width="99%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
	   <tr>
	     <td class="query_td1" align="left">
	      <input onclick="addNewNode('2');" type="button" name="addBtn" value="&nbsp;添加子模块" class="button_add"<%if(!add_submodule){%>disabled<%}%>>
	      <input onclick="addNewNode('1');" type="button" name="delBtn" value="&nbsp;添加相邻模块" class="button_delete"<%if(!add_adjacentmodule){%>disabled<%}%>>    
	      <input onclick="deleteNode(tree.getSelectedItemId());" type="button" name="updateBtn" value="&nbsp;删除模块" class="button_edit"<%if(!del_module){%>disabled<%}%>> 
	     </td>
	   </tr>   
 	</table>
	 
	 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
	   <tr><td height="5"></td></tr>
	 </table>
	 
	 
	 <!-- ======== 查询列表显示数据开始 ======== -->
	 <table width="99%" height="70%" border="0" align="center" cellpadding="0" cellspacing="0">
	   <tr>
	   	 <td width="220">
	         <!-- ======== 查询列表1显示数据开始 ======== -->
			 <table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
			   <tr>
			     <td>
			 	   		<div id="treeboxbox_tree" style="width:100%; height:100%;background-color:#EDF2F9; overflow:auto;"></div>
			     </td>
			   </tr>
			 </table>
			 <!-- ======== 查询列表1显示数据结束 ======== -->
	     </td>
	     <td>
	 	   <iframe id="myIframe" src="<%=request.getContextPath()%>/jsp/competenceMgr/moduleOp_list.jsp" frameborder="0" width="100%" height="100%">
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
<script>
		
			var tree = null;
  			function drawFieldTree(xmlValue)
  			{
  				if(tree =='undefined' || tree == null)
  				{
					tree=new dhtmlXTreeObject("treeboxbox_tree","100%","100%",0);
					tree.setImagePath("<%=request.getContextPath()%>/standard/images/");
			
					tree.enableDragAndDrop(1);//允许拖拉
			
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
						tree.clearMap();	/*清空HashMap*/
		    		}
			   	catch(e){
			   	
			   	} 
	     	}
			
			
			
			//selectNodeCode 所选节点代码
			//oldLevel 原来的级别
			//oldParentCode 原来的父节点
			//newLevel 新的级别
			//newParentCode 新父节点			
			function afterDragProcess(selectNodeCode, selectNode, oldLevel, oldParentCode, newLevel, newParentCode)
			{
			  //alert("所选节点代码="+selectNodeCode + "   原来的级别="+ oldLevel +"   原来的父节点="+oldParentCode+"   新的级别="+newLevel+ "新父节点=  "+newParentCode);
			  
			   var parentId = "0";
			   var parentCode = "root";
			   var parentName = "根节点";
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
