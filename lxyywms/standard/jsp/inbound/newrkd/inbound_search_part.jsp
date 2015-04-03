<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	String warehouseid = (String)request.getParameter("warehouseid");
	String jobtype = (String)request.getParameter("jobtype");

	HashMap hsPopedom = null;
	boolean inbound_rkquery = false;    //查询
	boolean inbound_rkreport = false;     //报表
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("inbound_rkquery") != null){
			inbound_rkquery = true;
		}
		if(hsPopedom.get("inbound_rkreport") != null){
			inbound_rkreport = true;
		}
    }
%>
<%
	String strTime =  StrTools.getCurrDateTime(8); 
%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	
	//查询
	function searchData(){
		
		var warehouseid = "<%=warehouseid %>";
		var productid= document.getElementById("package_id").value;	//品名
		var indate = document.getElementById("indate").value;		//入库日期
		var intype = document.getElementById("intype").value;		//入库类型
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("请先选择【所属仓库】!");
			return;
		}
		if(intype == null || intype.length < 1){
			alert("请先选择【入库类型】!");
			return;
		}else if(intype == "hl"){
			alert("回流作业，不生成入库单!");
			return;
		}
		condition = "&warehouseid=" + warehouseid + "&indate=" + indate 
					+ "&productid=" + productid+ "&intype=" + intype;
		
		list.formx1.action = strUrl + "inBoundJobAction&flag=4" + condition;
		list.document.formx1.submit();
		
		Loading();	
	}
	
	//页面登录
	function OnLoad(){
		selectType('<%=jobtype %>', 'intype', 'rklx');			//入库类型
	}

	
	//1.生成单据 2.部分生产单据
	function Ok(){
		var ids = "";
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				ids += check_ids[i].value + ",";
			}
		}
		if(ids == ""){
			alert("请至少选择一条记录！");
			return;
		}
		else if(confirm("你确定对所选记录生成单据？")){
			window.returnValue = ids.substring(0, ids.length-1);
	    	window.close();
    	}
	}

-->
</script>
</head>

<body onLoad="OnLoad()">
 <input type="hidden" id="condition">
 
<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== 当前位置 ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">当前位置：<span>入库管理 &gt;&gt; 部分生成入库单</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<li><input style="width: 50px" onclick="searchData();" type="button" id="btn3" value="查询" class="button_search4_out" onmouseover="this.className='button_search4_over'" onmouseout="this.className='button_search4_out'"></li>
		  	<li><input style="width: 50px;" onclick="Ok();" type="button" id="btn2" value="确定" class="button_edit4_out" onmouseover="this.className='button_edit4_over'" onmouseout="this.className='button_edit4_out'"></li>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== 查询条件 ======== -->
      <table id="marginTable" width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
	    <tr>

     	  <td class="yx1" align="right">入库日期：</td>
	      <td class="yx">
	      	<input id="indate" type="text" value="" onfocus="calendar();" class="Wdate" style="width:100px;">
	      </td>
	      <td class="TD_ADD" align="right">入库类型：</td>
	      	<td class="TD_ADD"><select id="intype" style="width:163px;" ><option value=""></option></select></td>
	      <td class="yx1" align="right">品名：</td>
	      <td class="yx">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name" size="15" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="…" class="button_select">
	      </td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/inbound/newrkd/inbound_search_part_list.jsp" 
		  		width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
		  	</td>
		  </tr>
		</table>
		
	  </td>
    </tr>
  </table>  	
</div>

 <!-- 页面加载层（start） -->
 <div id="loader_container" style="position: absolute; display: none; z-index: 2; filter:alpha(opacity=75);">
   <div id="loader"><div align="center">页面正在加载 ...</div><div id="loader_bg"><div id="progress"></div></div></div>
 </div>
 <!-- 页面加载层（end） -->  
 
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm' target="_blank"  style='display:none'>
 	<input type="hidden" value="">
 </FORM>
</body>
</html>
