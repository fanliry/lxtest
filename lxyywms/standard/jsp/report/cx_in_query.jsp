<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
	HashMap hsPopedom = null;
	boolean reportInjobQuery = false; //查询
	boolean reportInjobReport = false; //报表
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("reportInjobQuery") != null){
			reportInjobQuery = true;
		}
		if(hsPopedom.get("reportInjobReport") != null){
			reportInjobReport = true;
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
	var outinvoiceid = "";
	var condition = null;
	
	//检查数量是否为数字
	function IsNum(num){ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
	//报表
	function report(){
		if(condition == null){
			alert("请先查询！");
			return;
		}
		var name="kc_search";
		document.tempForm.action = strUrl + "CXInOutJobAction&flag=3" + condition;
		document.tempForm.submit();
	}
	
	function openWindow(name)  
    {  
      window.open('about:blank',name,'height=800, width=1200, top=0, left=0, toolbar=yes, menubar=yes, scrollbars=yes, resizable=yes,location=yes, status=yes');   
    }
		//查询
	function searchData(){
	
		var warehouseid = document.getElementById("warehouseid").value;	//仓库
		var whAreaId = document.getElementById("whAreaId").value;		//库区
		var cargoAlleyId = document.getElementById("cargoAlleyId").value;//巷道
		
		var indate_from = document.getElementById("indate_from").value;	//入库日期开始
		var indate_to = document.getElementById("indate_to").value;		//入库日期结束
		var invoicetype = document.getElementById("invoicetype").value;	//作业来源
		
		//var owner_id = document.getElementById("owner_id").value;	//货主
		//var customer_id = document.getElementById("customer_id").value;	//客户
		var package_id= document.getElementById("package_id").value;	//品名
		var tray_code = document.getElementById("tray_code").value;		//托盘条码
		
		var boundstockid = document.getElementById("boundstockid").value;	//单据号
		var intype = document.getElementById("intype").value;	    //单据类型
		
		
		var lotid = document.getElementById("lotid").value//批号
		
		if(warehouseid == null || warehouseid.length < 1){
			alert("请先选择【所属仓库】!");
			return;
		}
		
		condition = "&warehouseid=" + warehouseid + "&whAreaId=" + whAreaId + "&cargoAlleyId=" + cargoAlleyId
			 + "&boundstockid=" + boundstockid + "&indate_from=" + indate_from + "&indate_to=" + indate_to + "&intype=" + intype + "&invoicetype=" + invoicetype
			 + "&package_id=" + package_id + "&tray_code=" + tray_code+ "&groupinfo=" + groupinfo+ "&lotid=" + lotid;
		
		
		//批次属性值，如果是日期范围的，格式如下：2012-08-30|2012-08-31
		
		
		//每页显示行数
		var linerow = page.document.getElementById("lineviewrow").value;
		condition += "&linerow=" + linerow;
		
		
		list.formx1.action = "<%=request.getContextPath()%>/BMSService?actionCode=CXInOutJobAction&flag=1" + condition;
		
		list.document.formx1.submit();
		
		Loading();	
	}
	
	//获得用post方法提交的参数，包括要统计的项目，批次属性的查询条件
	function getPostCon(){
	
		//统计条件
		var total_items = "";
		if(document.getElementById("chk_product").checked){
			total_items += ", sto.productid";
		}
		if(document.getElementById("chk_owner").checked){
			total_items += ", sto.ownerId";
		}
		var lotatt;
		for(var i=1; i<13; i++){
			lotatt = document.getElementById("chk_lotatt" + i);
			if(lotatt!=null && lotatt.checked){
				total_items += ", sto.lotatt" + i;
			}
		}

		var lotid = document.getElementById("lotid").value
		//批次属性值，如果是日期范围的，格式如下：2012-08-30|2012-08-31
		
		//属性  0：表示禁用的，1：表示精确或糊涂查询的 2：表示日期要取范围的
		
		//有中文
		var msg = "<input type=hidden name='total_items' value='"+total_items+"'>";
					
		return msg;
	}
	
	//页面登录
	function OnLoad(){
		
		//仓库
		DWREngine.setAsync(false);
		selectObject("", "warehouseid", "1");	
		DWREngine.setAsync(true);
		selectType('', 'intype', 'rkdj');
		selectType('', 'invoicetype', 'zyly');		//作业来源
		
		//库区
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
		
		
	}
	
	//根据仓库获得库区的列表
	function getWhAreaList(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");
	}
	
	//选择批次后，显示该批次的明细属性
	function viewLot(){
	
		var tableObj = document.getElementById("marginTable");
		var lotid = document.getElementById("lotid").value;
		
		//3是查询条件的行数, 4是每行显示的td数
		createLotDetail(tableObj, lotid, 4, 4);
		
	}
    //统计分组条件
	var groupinfo = "", info = ""; // groupinfo为 --customerid|ownerId,1|2,客户|货主--
	function More()
	{
		var result = showModalDialog(strUrl + "CXInOutJobAction&method=ricoExecGroup&flag=1", "", "dialogWidth=500px;dialogHeight=400px");
		groupinfo = result;
		if(result != null && result.length > 0)
		{
				var aem = result.split(",");	//解析不同内容
				var bem = aem[2].split("|"); // 属性名将相同内容划分为单元
				info = "";
				for(var i=0; i<bem.length; i++)
				{
					info += bem[i]+"/";
				}
				p_remind.innerHTML = '&nbsp;&nbsp;&nbsp;<input onclick="More()" type="button" name="button" value="进入>>" class="BUTTON_STYLE1">'+info.substring(0, info.length-1);
		}else{
				p_remind.innerHTML = '&nbsp;&nbsp;&nbsp;<input onclick="More()" type="button" name="button" value="进入>>" class="BUTTON_STYLE1">&nbsp';
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
		<div id="dqwz" class="f_l">当前位置：<span>查询统计 &gt;&gt; 入库流水查询</span></div>
		<div id="caozuo" class="f_r">
		  <ul>
			<%if(reportInjobReport){%><li class="tubiao1"><a href="#" onclick="report()">报表</a></li><%}%>
			<%if(reportInjobQuery){%><li class="tubiao1"><a href="#" onclick="searchData()">查询</a></li><%}%>
		  </ul>
		</div>
	  </div>
		
	</td></tr>
	<tr><td height="5"></td></tr>
    <tr><td>
      
      <!-- ======== 查询条件 ======== -->
      <table id="marginTable" width="99%"  border="0" cellpadding="0" cellspacing="0" class="table1" align="center"> 
        <tr>
	   	  <td class="yx1" align="right">统计分组条件：</td>
	      <td class="yx" colspan="7">
	         <div id="p_remind" style="color: red; ">&nbsp;&nbsp;&nbsp;<input onclick="More()" type="button" name="button" value="进入>>" class="BUTTON_STYLE1"></div>
		  </td>
 	 	</tr>
	    <tr>
	      <td class="yx1" width="100" align="right">仓库：</td>
	      <td class="yx"><select name="warehouseid" onChange="getWhAreaList()" style="width:117px;"><option value=""></option></select></td>
	      <td class="yx1" width="100" align="right">库区：</td>
     	  <td class="yx"><select id="whAreaId" onChange="getAlleyList()" style="width:117px;"><option value=""></option></select></td>
	   	  <td class="yx1" width="100" align="right">巷道：</td>
     	  <td class="yx"><select id="cargoAlleyId" style="width:117px;"><option value="">--请选择--</option></select></td>
     	  <td class="yx1" width="100px" align="right">单号：</td>
		  <td class="yx"><input name="boundstockid" type="text" size="15"></td>
	    </tr>
	    <tr>
	      
          <td class="yx1" align="right">托盘条码：</td>
     	  <td class="yx"><input name="tray_code" type="text" size="15"></td>
     	  <td class="yx1" align="right">入库日期：</td>
	      <td class="yx">
	      	<input id="indate_from" type="text" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:100px;">-<input id="indate_to" type="text" value="<%=strTime%>" onfocus="calendar();" class="Wdate" style="width:100px;">
	      </td>
	      <td class="yx1" width="100" align="right">单据类型：</td>
          <td class="yx"><select name="intype" id="intype"  style="width:117px;">
            <option>---请选择---</option>
          </select></td>
	      <td class="yx1" align="right">作业来源：</td>
	      <td class="xx1"><select id=invoicetype  style="width:117px;"><option value=""></option></select></td>
	    </tr>
	    <tr>
	      
	      <td class="yx1" align="right">品名：</td>
	      <td class="yx">
	      	<input type="hidden" name="package_id"><input type="text" name="package_name" size="15" readonly>
            <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="…" class="button_select">
	      </td>
	     
	   	  <td class="yx1" align="right">批号：</td>
     	 <td class="yx"><input name="lotid" type="text" size="15"></td>
	    </tr>
      </table> 
		
    </td></tr>
	<tr><td height="5"></td></tr>
    <tr>
      <td valign="top" height="100%">
		  
		<table width="99%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td>
	          <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/report/cx_in_query_list.jsp" 
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
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm' target="_blank"  style='display:none'></FORM>
</body>
</html>
