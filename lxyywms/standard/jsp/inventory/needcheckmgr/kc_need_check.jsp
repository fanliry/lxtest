<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
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
	var outinvoiceid = "";
	var condition = null;
	
	//检查数量是否为数字
	function IsNum(num){ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
	
	//查询
	function searchData(){

		var check_type = document.getElementById("check_type").value;//盘点类型
		var start_time = document.getElementById("dateoff_from").value;//开始时间
		var end_time = document.getElementById("dateoff_to").value;  //结束时间
		var is_do = document.getElementById("is_do").checked==true?"Y":"N"; //是否处理
		condition = "&inouttype=" +　check_type + "&createtimeform=" +　start_time
			 + "&createtimeto=" + end_time + "&handleflag=" +　is_do;
		//每页显示行数
		var linerow = page.document.getElementById("lineviewrow").value;
		condition += "&linerow=" + linerow;					
		list.location.href = strUrl + "inventotyNeedCheckAction" + condition;	
		Loading();	
	}
	
   //处理保存和删除
	function updateData(N){
	
		var checkbox_ids = list.document.getElementsByName("check_id");
		var ids = "";
		for(var i=0; i<checkbox_ids.length; i++){
	  		if(checkbox_ids[i].checked){
	  			ids += checkbox_ids[i].value + ",";
	  		}
  		} 		
  		if(ids == ""){
			alert("请至少选择一条冻结信息!");
		  	return false;
		}
		if(N == "1"){//保存
		  if(confirm("确认设为满货位？")){				
			 list.location.href = strUrl + "inventotyNeedCheckAction&method=ricoExecUpdate&flag=1&ids=" + ids;			
		    }
		}
		if(N == "2"){//作废
		   if(confirm("确认设为空货位？")){				
			  list.location.href = strUrl + "inventotyNeedCheckAction&method=ricoExecUpdate&flag=2&ids=" + ids;			
			}
		}	
	}
	//打印
	function printData(){
      if(condition == null){
        alert("请先查询！");
        return;
          }
  	 window.open(strUrl+ "inventotyNeedCheckAction&method=ricoExecPrint"+condition,'newwindow','width=1200,height=600,left='+WLeft+',top='+WTop+',scrollbars=yes');	
		}
	//页面登录
	function OnLoad(){

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
		<div id="dqwz" class="f_l">当前位置：<span>库存管理 &gt;&gt; 需盘点管理</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
		    <li class="tubiao2"><a href="#" onClick="printData()">打印</a></li>
		    <li class="tubiao3"><a href="#" onClick="updateData(2)">作废</a></li>
		    <li class="tubiao4"><a href="#" onClick="updateData(1)">保存</a></li>
			<li class="tubiao1"><a href="#" onclick="searchData()">查询</a></li>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== 查询条件 ======== -->
      <table id="marginTable" width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
	    <tr>
	      <td class="y1" align="right">需盘点类型：</td>
     	  <td class="x"><select id="check_type" style="width:100px;"><option value="">--请选择--</option><option value="1">入库</option><option value="2">出库</option></select></td>
		  <td class="y1" align="right">时间：</td>
     	  <td class="x" style="width:230px;">
     	  	<input id="dateoff_from" type="text" onFocus="calendar()" class="Wdate" style="height:21px;width:100px;" value='<%=strTime%>'>-<input id="dateoff_to" type="text" onfocus="calendar();" class="Wdate" style="height:21px;width:100px;" value='<%=strTime%>'>
		  </td>
		  <td class="y1" align="right">是否处理：</td>
	      <td class="x" style="width:230px;">
	        <input type="checkbox" name="is_do"></td>
	         <td class="x" style="width:230px;">
	      </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/inventory/needcheckmgr/kc_need_check_list.jsp" 
		  		width="100%" height="100%" scrolling="auto" frameborder="0"></iframe>
		  	</td>
		  </tr>
		  <tr>
		    <td height="25">
		      <iframe id="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=20&flag=Y" 
		      	width="100%" height="100%" scrolling="no" frameborder="0" ></iframe> 
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
</body>
</html>
