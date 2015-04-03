<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean quality_query = false; //查询
	boolean quality_pass = false; //放行
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("quality_query") != null){
			quality_query = true;
		}
		if(hsPopedom.get("quality_pass") != null){
			quality_pass = true;
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
	function openWindow(name)  
    {  
      window.open('about:blank',name,'height=800, width=1200, top=0, left=0, toolbar=yes, menubar=yes, scrollbars=yes, resizable=yes,location=yes, status=yes');   
    }
	
	//查询
	function searchData(){

		var warehouseid = document.getElementById("warehouseid").value;	//仓库
		var whareaid = document.getElementById("whareaid").value;		//库区
		var lotnumber = document.getElementById("lotnumber").value;     //批号
		var requestid = document.getElementById("requestid").value;	    //申请单id
		var productid = document.getElementById("package_id").value;	    //产品id
		var productstatus = document.getElementById("productstatus").value;		//产品状态
		var productCode = document.getElementById("productCode").value;		//产品编码
		
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("请先选择【所属仓库】!");
			return;
		}
		
		condition = "&warehouseid=" +　warehouseid + "&whareaid=" +　whareaid + "&lotnumber=" +　lotnumber + "&requestid=" +　requestid + 
			"&productid=" +　productid + "&productstatus=" +　productstatus+ "&productCode=" +　productCode ;
		//每页显示行数
		var linerow = page.document.getElementById("lineviewrow").value;
		condition += "&linerow=" + linerow;
		list.formx1.action = strUrl + "inventoryQualityAction&flag=1" + condition;
		list.document.formx1.submit();	
		Loading();	
	}
	
	function openShow(){
		var check_ids = list.document.getElementsByName("check_id");
		var inventoryids="";
		var count = 0;
	    for(var i=0; i<check_ids.length; i++){
		   if(check_ids[i].checked){
				inventoryids += check_ids[i].value;
				count++;
		   }
	    }
	    if (count == 0) {
	    	  alert("请选择需要操作的记录！");
	    	  return;
	    }
	    if (count >1) {
	    	  alert("一次只能选择一个记录！");
	    	  return;
	    }
	    var result = showModalDialog('<%=request.getContextPath()%>/standard/jsp/quality/changestatus/change.jsp',inventoryids,'dialogWidth=400px;dialogHeight=200px');	
	    
	    if(result != null && result.length > 1)
		{
			list.location.href=result;
		}
	}
	
	//页面登录
	function OnLoad(){
		
		//仓库
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");	
		selectType('', 'wpzt', 'wpzt');
		DWREngine.setAsync(true);
		
		//库区
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
	}
	
	//根据仓库获得库区的列表
	function getWhAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
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
		<div id="dqwz" class="f_l">当前位置：<span>质检管理 &gt;&gt; 状态转换</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
		    <%if(quality_pass){%><li class="tubiao2"><a href="#" onclick="openShow()">放行</a></li><%}%>
			<%if(quality_query){%><li class="tubiao1"><a href="#" onclick="searchData()">查询</a></li><%}%>
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
	      <td class="yx"><select name="warehouseid" onChange="getWhAreaList()" style="width:117px;"><option value=""></option></select></td>
	      <td class="yx1" width="100" align="right">库区：</td>
     	  <td class="yx" ><select id="whareaid" style="width:117px;"><option value=""></option></select></td>
	      <td class="y1" width="100" align="right">产品编码：</td>
	   	  <td class="yx">
	   	    <input type="text" name="productCode" size="20">
	   	  </td>
	   	   <td class="yx1" align="right">品名：</td>
	      <td class="yx">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name" size="15" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="…" class="button_select">
	      </td>
	    </tr>
	    <tr>
	      <td class="y1" width="100" align="right">批号：</td>
	   	  <td class="x">
	   	    <input type="text" name="lotnumber" size="20">
	   	  </td>
	      <td class="y1" align="right">入库申请单：</td>
 	      <td class="x"><input name="requestid" type="text" size="15"></td>
	     
     	  <td class="y1" align="right"  width="100">物品状态：</td>
     	  <td class="" colspan="3"><select name="productstatus" id="wpzt"  style="width:117px;">
            <option>---请选择---</option>
          </select></td>
          
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/quality/changestatus/changestatus_list.jsp" 
		  		width="100%" height="100%" scrolling="yes" frameborder="0"></iframe>
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
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm' target="_blank"  style='display:none'></FORM>
</body>
</html>
