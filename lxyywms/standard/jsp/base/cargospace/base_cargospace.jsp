<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%
	String strUserCode = (String)request.getSession(false).getAttribute("userCode");
	String strUserName = (String)request.getSession(false).getAttribute("UserName");
	
	HashMap hsPopedom = null;
	boolean baseCargospaceAdd = false;    //添加
	boolean baseCargospaceDelete = false; //删除
	boolean baseCargospaceUpdate = false; //修改
	boolean baseCargospaceClear = false;  //清空货位
	boolean baseCargospaceClearAll = false;  //清空所有货位
	boolean baseCargospaceStoreManage = false;  //仓库管理
	boolean baseCargospaceAlleyManage = false;  //巷道管理
	boolean baseCargospaceLock = false;  //货位锁定
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("baseCargospaceAdd") != null){
			baseCargospaceAdd = true;
		}
		if(hsPopedom.get("baseCargospaceDelete") != null){
			baseCargospaceDelete = true;
		}
		if(hsPopedom.get("baseCargospaceUpdate") != null){
			baseCargospaceUpdate = true;
		}
		if(hsPopedom.get("baseCargospaceClear") != null){
			baseCargospaceClear = true;
		}
		if(hsPopedom.get("baseCargospaceClearAll") != null){
			baseCargospaceClearAll = true;
		}
		if(hsPopedom.get("baseCargospaceStoreManage") != null){
			baseCargospaceStoreManage = true;
		}
		if(hsPopedom.get("baseCargospaceAlleyManage") != null){
			baseCargospaceAlleyManage = true;
		}
		if(hsPopedom.get("baseCargospaceLock") != null){
			baseCargospaceLock = true;
		}
    }
%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
<!--
	table{
		table-layout:fixed;
	}
	td{
		word-break:keep-all;
		white-space:nowrap;
		overflow:hidden;
		text-overflow:ellipsis;
	} 
-->
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>	
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/cargoSpaceTree.js"></script>
<script type="text/javascript">
<!--
	var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
	
	//----增加----
	function addInfo()
	{
		var hiddenId = window.leftIframe.document.getElementById("hiddenId").value;
		var hiddenLevel = window.leftIframe.document.getElementById("hiddenLevel").value;
		
		if(hiddenLevel!=null && hiddenLevel=="3" && hiddenId.length==9){//增加巷道下面的货位，可以批量，立体库用
			var strUrl='<%=request.getContextPath()%>/standard/jsp/base/cargospace/base_cargospace_add1.jsp';
			var isAlleytwin = window.rightIframe.document.getElementById("istwin").value;	//是否双升货位巷道
			strUrl = strUrl + "?id=" + hiddenId + "&istwin=" + isAlleytwin;
			
			var result = showModalDialog(strUrl,'','dialogWidth=500px;dialogHeight=350px');
	   		if(result!=null && result.length>0)
	   		{
	   			cargoSpaceTree.addCargoSpaceTree(result[0], result[1], result[2], result[3], result[4], result[5], result[6], result[7], 
	   				result[8], result[9], result[10], result[11], result[12], result[13], result[14],
	   				"<%=strUserCode%>", "<%=strUserName%>", returnMesg);
	   		}
		}else if(hiddenLevel!=null && hiddenLevel=="2"){//单个增加货区下面的货位，立体库以外用
			//只有所选库区不是立体库类型时可以增加
			selectView.isWhAreaTypeRight(hiddenId, {callback:function(data){
				if(data){
					alert("请选择对应的巷道！");
					
		    	}else{
		    		var strUrl='<%=request.getContextPath()%>/standard/jsp/base/cargospace/base_cargospace_add2.jsp';
					strUrl = strUrl + '?id=' + hiddenId;
			
					var result = showModalDialog(strUrl,'','dialogWidth=500px;dialogHeight=400px');
					
			   		if(result!=null)
			   		{
			   			cargoSpaceTree.addCargoSpaceNode(result[0], result[1], result[2], result[3], result[4], result[5], result[6], result[7], 
			   				result[8], result[9], result[10], result[11], result[12], result[13], result[14], result[15], result[16], result[17],
			   				result[18], result[19], result[20], result[21], "<%=strUserCode%>", "<%=strUserName%>", returnMesg);
			   		}
		    	}
			}});

		}else{
			alert("请选择对应的库区或巷道！");
		}
		
	}
	//----删除----
	function deleteCheckValue()
	{
		var strId = window.leftIframe.document.getElementById("hiddenId").value;
	 	var hiddenLevel = window.leftIframe.document.getElementById("hiddenLevel").value;
	 	
	 	//删除所选货位
		if(strId.length==12){
			var del = confirm("确定删除所选货位");
			if(del){
				cargoSpaceTree.deleteCargoSpaceTree(strId, "<%=strUserName%>", returnMesg);
			}
		}
		//删除所选巷道的所有货位
		else if(hiddenLevel!=null && hiddenLevel=="3" && strId.length==9){
			var del = confirm("确定删除所选巷道的所有货位");
			if(del){
				cargoSpaceTree.deleteCsTreeByAlleyId(strId, "<%=strUserName%>", returnMesg);
			}
		}
		
		else{
			alert("请选择所要删除的货位");
		}
	}
	
	//修改
	function updateData(){
	
		var hiddenId = window.leftIframe.document.getElementById("hiddenId").value;
		if(hiddenId.length==12){
			
    		var strUrl='<%=request.getContextPath()%>/standard/jsp/base/cargospace/base_cargospace_update.jsp';
			strUrl = strUrl + '?id=' + hiddenId;
	
			var result = showModalDialog('<%=request.getContextPath()%>/BMSService?actionCode=baseCargoSpaceAction&flag=2&cargoSpaceId='+hiddenId,
				'','dialogWidth=500px;dialogHeight=400px');
			
	   		if(result!=null && result.length > 0)
	   		{
	   			cargoSpaceTree.updateCargoSpaceNode(result[0], result[1], result[2], result[3], result[4], result[5], result[6], result[7], 
	   				result[8], result[9], result[10], result[11], result[12], result[13], result[14], result[15], result[16], result[17],
	   				result[18], result[19], result[20], result[21], "<%=strUserCode%>", "<%=strUserName%>", 
	   				function(data){alert(data);window.rightIframe.location.reload();});
	   		}
		}else{
			alert("请选择所要修改的货位！");
		}
	}
	
	//----新增或删除的返回信息----
	function returnMesg(data)
	{
		window.leftIframe.location.reload();
	}

	//----清空所有货位----
	function clearCs(){
	
		var del = confirm("确定清空所有货位信息");
		if(del){
			var strUrl = ac + "baseCargoSpaceAction&method=clearCargoSpace&flag=all";
			window.location.href = strUrl;
		}
   	}
	
	//----清空单个货位----
	function clearOnwCs(){
	
		var hiddenId = window.leftIframe.document.getElementById("hiddenId").value;
		if(hiddenId.length==12){
	  		var del = confirm("确定清空所选择的货位信息");
			if(del){
				var strUrl = ac + "baseCargoSpaceAction&method=clearCargoSpace&flag=one&cargoSpaceId=" + hiddenId;
				window.location.href = strUrl;
			}
		 }else{
		  	alert("请选择所要清空的货位");
		 }
	}
	   
	 //------货位锁定-----------  
	 function cargoSpaceLock(){
	 	showModalDialog("<%=request.getContextPath()%>/standard/jsp/base/cargospace/base_cargospace_lock.jsp","","dialogWidth=900px;dialogHeight=600px;")
	 }  
	   
	//----转到仓库管理----
	function goWarehouseMgr(){
		var strUrl = '<%=request.getContextPath()%>/standard/jsp/base/warehouse/base_warehouse.jsp';
		window.location.href = strUrl;
	}
	
	//----转到巷道管理----
	function goCargoSpaceMgr(){
		var strUrl = '<%=request.getContextPath()%>/standard/jsp/base/alley/base_alley.jsp';
		window.location.href = strUrl;
	}
	
	function OnLoad(){

		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>"
		if(back_msg != ""){
			if(back_msg == "Y"){
				alert("操作成功！");
			}else{
				alert(back_msg);
			}
		}
	}

-->
</script>
</head>

<body onload="OnLoad();">
<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
    
      <!-- ======== 当前位置 ======== -->
      <form id="myform" name="myform" method="post" action="<%=request.getContextPath()%>/rmsService?actionCode=warehouseAction&method=query">
	  <div class="wz">
		<div id="dqwz" class="f_l">当前位置：<span>基本信息 &gt;&gt; 货位管理</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
		    <!-- <%if(baseCargospaceLock){%><li class="tubiao2" style="width:84px;"><a href="#" onclick="cargoSpaceLock()">货位锁定</a></li><%}%> -->
		    <%if(baseCargospaceAlleyManage){%><li class="tubiao2" style="width:84px;"><a href="#" onclick="goCargoSpaceMgr()">巷道管理</a></li><%}%>
		    <%if(baseCargospaceStoreManage){%><li class="tubiao2" style="width:84px;"><a href="#" onclick="goWarehouseMgr()">仓库管理</a></li><%}%>
		    <!--<%if(baseCargospaceClearAll){%><li class="tubiao2" style="width:105px;"><a href="#" onclick="clearCs()">清空所有货位</a></li><%}%>-->
		    <!--<%if(baseCargospaceClear){%><li class="tubiao2" style="width:84px;"><a href="#" onclick="clearOnwCs()">清空货位</a></li><%}%>-->
			<%if(baseCargospaceUpdate){%><li class="tubiao2"><a href="#" onclick="updateData()">修改</a></li><%}%>
			<!--<%if(baseCargospaceDelete){%><li class="tubiao3"><a href="#" onclick="deleteCheckValue()">删除</a></li><%}%>-->
			<%if(baseCargospaceAdd){%><li class="tubiao4"><a href="#" onclick="addInfo()">添加</a></li><%}%>
		  </ul>
		</div>
	  </div>
	  </form>
	</td></tr>
    <tr><td height="100%" valign="top"> 

	  <table width="100%" height="100%"  border="0" align="center" cellpadding="5" cellspacing="0">
	 	<tr><td width="25%" valign="top">
	   		  
	      <form id="myform1" name="myform1" method="post" action="">
		  <table width="100%" height="100%"  border="0" bordercolor="#FFFFFF" cellpadding="0" cellspacing="0">
			<tr><td height="100%">
  		   	  <iframe id="leftIframe" name="leftIframe" width="100%" height="100%" frameborder="0" 
				src="<%=request.getContextPath()%>/standard/jsp/base/cargospace//base_cargospace_left.jsp"></iframe>
         	</td></tr>
      	  </table>
	      </form>
	    </td>
	 
	    <td valign="top">
	      <form id="myform2" name="myform2" method="post" action="">
	   	  <iframe id="rightIframe" name="rightIframe" width="100%" height="100%" frameborder="0" scrolling="no" src="#"></iframe>
	      </form>
	    </td></tr>
      </table>

    </td></tr>
  </table>
</div>

</body>
</html>
