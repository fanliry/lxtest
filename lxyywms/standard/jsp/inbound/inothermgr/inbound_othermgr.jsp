<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	String strTime =  StrTools.getCurrDateTime(8); 
	HashMap hsPopedom = null;
	boolean inboundPOQuery = false; //查询
	boolean inboundPOPrint = false; //报表
	boolean inboundPOUploading = false; 
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("inboundPOQuery") != null){
			inboundPOQuery = true;
		}
		if(hsPopedom.get("inboundPOPrint") != null){
			inboundPOPrint = true;
		}
		if(hsPopedom.get("inboundPOUploading") != null){
			inboundPOUploading = true;
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
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/yldSelectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/customs/yld/js/yldselectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lotsearch.js"></script>
<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	var condition = null;
	
	//查询
	function searchData(){
		var potype = document.getElementById("potype").value;			//单据类型
		var customerid = document.getElementById("customer_id").value;	//客户
		var poid = document.getElementById("poid").value;	//PO单号
		var start_time = document.getElementById("createdate_from").value;	//日期开始
		var end_time = document.getElementById("createdate_to").value;		//日期结束
		var linerow = page.document.getElementById("lineviewrow").value;	//每页显示行数	
		
		Loading();
		//"&shift_id=" + shift_id
		condition =  "&poid=" + poid
			 + "&potype=" + potype + "&start_time=" + start_time + "&end_time=" + end_time + "&linerow=" + linerow+"&customerid=" + customerid;
		list.formx1.innerHTML = getLotattCon();
		list.formx1.action = strUrl + "inBoundPoAction&flag=6" + condition;
		list.document.formx1.submit();
	}
	
	function Mupload(){
		
	}
	
	//打印
	function printData(){
		var k = 0;
		var id = "";
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id = check_ids[i].value;
				k++;
			}
		}
		if( k == 0 ){
			alert("请选择一张单据！");
			return;
		}else if(k != 1){
			alert("只能选择一张单据！");
			return;
		}
				
		var actionStr =strUrl + "inBoundPoAction&method=ricoExecPrint&poid="+id;
		var WLeft = Math.ceil((window.screen.width-800)/2);
  		var WTop  = Math.ceil((window.screen.height-600)/2);
		window.open(actionStr,'newwindow','width=1000,height=600,left='+WLeft+',top='+WTop+',scrollbars=yes');	
	}
	
	//页面登录
	function OnLoad(){		
		selectType('', 'potype', 'potype');
		//显示批次属性里配置的查询条件
		//var tableObj = document.getElementById('marginTable');
		//3插入之前的行数, 4是每行显示的td数
		//createLotSearch(tableObj, 'S-ckdmgr', 2, 4); 
	}
	
-->
</script>
</head>

<body onLoad="OnLoad()">
 <input type="hidden" id="condition">
 <input type="hidden" id="lt1">	
 <input type="hidden" id="lt2">
 <input type="hidden" id="lt3">
 <input type="hidden" id="lt4">
 <input type="hidden" id="lt5">
 <input type="hidden" id="lt6">
 <input type="hidden" id="lt7">
 <input type="hidden" id="lt8">
 <input type="hidden" id="lt9">
 <input type="hidden" id="lt10">
 <input type="hidden" id="lt11">
 <input type="hidden" id="lt12">
<div class="con_bk">
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr><td>
      
	  <!-- ======== 当前位置 ======== -->
	  <div class="wz">
		<div id="dqwz" class="f_l">当前位置：<span>入库管理 &gt;&gt; PO外来单管理</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
		  	<%-- <%if(inboundPOUploading){%><li class="tubiao8" style="width:50px;"><a href="#" onclick="Mupload();">上传</a></li><%}%>
		  	<%if(inboundPOPrint){%><li class="tubiao8" style="width:80px;"><a href="#" onclick="printData();">单据打印</a></li><%}%> --%>
			<%if(inboundPOQuery){%><li class="tubiao1"><a href="#" onclick="searchData()">查询</a></li><%}%>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== 查询条件 ======== -->
      <table id="marginTable" width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
	    <tr>
	      <td class="yx1" align="right">日期：</td>
	      <td class="yx">
	      	<input id="createdate_from" type="text" size="13" value="<%=strTime%>" onfocus="calendar();" class="Wdate"> -
	      	<input id="createdate_to" type="text" size="13" value="<%=strTime%>" onfocus="calendar();" class="Wdate">
	      </td>
	      <td class="yx1" width="100px" align="right">单据类型：</td>
	      <td class="yx"><div class="select_search"><select id="potype" style="width:117px;" class="select_search"><option value=""></option></select></div></td>
	      <td class="yx1" align="right">PO单号：</td>
          <td class="yx"><input type="text" id="poid" style="width: 117px;"></td>
	    
	      <td class="yx1"align="right">客户：</td>
	      <td class="xx">
	      	<input type="hidden" name="customer_id"><input type="text" name="customer_name" size="15" readonly>
       		<input type="button" class="button_select" value="…" onClick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp','800','520');">
	      </td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0" >
		  <tr>
            <td valign="bottom" class="title" height="18px">PO单信息 </td>
          </tr>
	      <tr>
	        <td height="180px">
		      <iframe id="list" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inbound/inothermgr/inbound_othermgr_list.jsp"></iframe>
		    </td>
	      </tr>
	      <tr>
	       <td height="25">
	  	     <iframe  id="page" name="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=6&flag=N" 
	  	     	 width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe> 
	       </td>
	     </tr>
	      <tr>
            <td valign="bottom" class="title" height="19px">PO单【<span id="dckdid" style="color: red;font-weight:bold;"></span>】明细信息</td>
          </tr>
	      <tr>
	        <td>
		      <iframe id="detail" width="100%" height="100%" frameborder="0" scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/inbound/inothermgr/inbound_othermgr_detail.jsp"></iframe>
		    </td>
	      </tr>
	    </table>
		
	  </td>
    </tr>
  </table>  	
</div>

	<!-- 页面加载层（start） -->
	<div id="loader_container"
		style="position: absolute; display: none; z-index: 2; filter:alpha(opacity=75);">
		<div id="loader">
			<div align="center">页面正在加载 ...</div>
			<div id="loader_bg">
				<div id="progress"></div>
			</div>
		</div>
	</div>
	<!-- 页面加载层（end） -->
 
</body>
</html>