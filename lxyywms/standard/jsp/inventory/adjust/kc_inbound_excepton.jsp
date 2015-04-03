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
	var condition = null;
	
	//查询
	function searchData(){
	
		var warehouseid = document.getElementById("warehouseid").value;	//仓库
		var indate_from = document.getElementById("indate_from").value;	//入库日期开始
		var indate_to = document.getElementById("indate_to").value;		//入库日期结束
		var is_do = document.getElementById("is_do").checked==true?"Y":"N";
		
		condition = "&warehouseid=" +　warehouseid + "&indate_from=" +　indate_from + "&indate_to=" +　indate_to + "&is_do=" +　is_do;
		list.formx1.action = strUrl + "NeedCheckAction&flag=6" + condition;
		list.document.formx1.submit();
		
		Loading();	
	}
	//作废清空货位
	function clearspace(){     
		var ids = "";
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				ids += check_ids[i].value + ",";
			}
		}
		if(ids == ""){
			alert("请选择要处理的记录！");
			return;
		}
		if(confirm("您确定作废（清空货位）所选需盘点记录？")){
			list.location.href = strUrl + "NeedCheckAction&flag=2&ids=" + ids+condition;
		}
	}
	
	function savaDate(){
		 var id = "" ;
		 var k=0;
         var check_ids = list.document.getElementsByName("check_id" );
         for( var i=0; i<check_ids.length; i++){
             if(check_ids[i].checked){
                 id = check_ids[i].value;
                 k++;
                  break;
            }
        }
         if( k == 0 ){
            alert( "请选择一条记录！" );
             return;
        } else if(k != 1){
            alert( "只能选择一条记录！" );
             return;
        }
			window.returnValue=id;
			window.close();		
	}
	//页面登录
	function OnLoad(){
		//仓库
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");	
		DWREngine.setAsync(true);
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
		<div id="dqwz" class="f_l">当前位置：<span>库存管理 -&gt; 库存调整单 -&gt; 调整单明细</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
		    <li class="tubiao1"  style="width:90px"><a href="#" onclick="savaDate()">确定</a></li>
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
	      <td class="yx1" width="100" align="right">仓库：</td>
	      <td class="yx"><select name="warehouseid" style="width:117px;"><option value=""></option></select></td>
	      <td class="yx1" align="right">日期：</td>
	      <td class="yx">
	      	<input id="indate_from" type="text" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:100px;">-<input id="indate_to" type="text" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:100px;">
	      </td>
	      <td class="yx1" width="100" align="right">是否处理：</td>
          <td class="yx"><input type="checkbox" name="is_do" class="input_checkbox"></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/inventory/adjust/kc_inbound_excepton_list.jsp" 
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
