<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.util.HashMap" %>
<%
    String strTime =  StrTools.getCurrDateTime(8); 
	HashMap hsPopedom = null;
	boolean outboundNewsqdQuery = false;    
	boolean outboundNewsqdAdd = false;     
	boolean outboundNewsqdAddSo = false; 
	boolean outboundNewsqdProduct = false; 
	
	if(request.getSession(false).getAttribute("hsPopedom") != null){
		hsPopedom = (HashMap)request.getSession(false).getAttribute("hsPopedom");
	}
	if(hsPopedom != null){
		if(hsPopedom.get("outboundNewsqdQuery") != null){
			outboundNewsqdQuery = true;
		}
		if(hsPopedom.get("outboundNewsqdAdd") != null){
			outboundNewsqdAdd = true;
		}
		if(hsPopedom.get("outboundNewsqdAddSo") != null){
			outboundNewsqdAddSo = true;
		}
		if(hsPopedom.get("outboundNewsqdProduct") != null){
			outboundNewsqdProduct = true;
		}
    }
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>浙江刚玉物流仓库管理系统</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />

<script>
var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";
 	var condition = null;
 	//增加出库单
 	function addinvoice()
 	{
 		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/outbound/newckd/outbound_newckd_add.jsp","","dialogWidth=800px;dialogHeight=250px;");
		if(result != null && result.length > 0)
		{
			list.location.href = result;
			detail.location.href = "<%=request.getContextPath()%>/standard/jsp/outbound/newckd/outbound_newckd_dlist.jsp";
		}		
 	}
 	//按订单增加出库单
 	function ddaddinvoice()
 	{
 		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/outbound/newckd/outbound_ckd_add.jsp","","dialogWidth=850px;dialogHeight=600px;");
		if(result != null && result.length > 0)
		{
		    //每页显示行数
		    var linerow = page.document.getElementById("lineviewrow").value;
			list.location.href = result+"&linerow="+linerow;
			detail.location.href = "<%=request.getContextPath()%>/standard/jsp/outbound/newckd/outbound_newckd_dlist.jsp";
		}
 	}
 	//更新出库单
 	function updateinvoice()
 	{
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
 	
 		var result = showModalDialog("<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&flag=3&invoiceid="+id,"","dialogWidth=800px;dialogHeight=250px;");
		if(result != null && result.length > 0)
		{
			list.location.href = result;
		}
		queryinvoice();
 	}
 	//增加出库单明细
 	function addinvoicedetail()
 	{
 		var k = 0;
		var id = "";
		var ownerid = "";
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id = check_ids[i].value;
				ownerid = check_ids[i].alt;
				k++;
			}
		}
		if( k == 0 ){
			alert("请选择一张出库单据！");
			return;
		}else if(k != 1){
			alert("只能选择一张出库单据！");
			return;
		}
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/outbound/newckd/outbound_newckd_detail.jsp?invoiceid="+id,"new","dialogWidth=800px;dialogHeight=600px;");
		if(result != null && result.length > 0)
		{
			
		}
		list.queryDetail(id);
 	}
 	

 	
	//分配
 	function assign()
 	{
 		var k = 0;
		var id = "";
		var check_ids = tableheight2.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			if(check_ids[i].checked){
				id = check_ids[i].value;
				k++;
				break;
			}
		}
		if( k == 0 ){
			alert("请选择一条出库单明细！");
			return;
		}else if(k != 1){
			alert("只能选择一条出库单明细！");
			return;
		}	    		
		var WLeft = Math.ceil((window.screen.width-1100)/2);
  		var WTop  = Math.ceil((window.screen.height-800)/2);
  			
		showModalDialog("<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&flag=5&invoicedetailid="+id,'newwindow',"dialogWidth=1000px;dialogHeight=600px;");
 	}
 	
 	//查询
 	function queryinvoice()
 	{
 		//单据状态
 		var outstatus = document.getElementById("outstatus").value;;
 		//仓库ID
		var warehouseid = document.getElementById("warehouseid").value;
		//类型
		var outtype = document.getElementById("outtype").value;
		//客户
		var customer_id = document.getElementById("customer_id").value;
		//开始日期
		var start_time = document.getElementById("start_time").value;
		//结束日期
		var end_time = document.getElementById("end_time").value;
		//出库单号
		var outstockid = document.getElementById("outstockid").value;
	
		
		//每页显示行数
		var linerow = page.document.getElementById("lineviewrow").value;
		
 		var condition = "&linerow=" + linerow + "&outstatus=" + outstatus + "&outtype=" + outtype + "&warehouseid=" + warehouseid + "&customer_id=" + customer_id + 
					    "&start_time=" + start_time + "&end_time=" + end_time + "&outno=" + outstockid;
		
 		var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=outBoundAction&flag=1";
 		
 		Loading();
 		
 		list.location.href = strUrl + condition;
 		detail.location.href = "<%=request.getContextPath()%>/standard/jsp/outbound/newckd/outbound_newckd_dlist.jsp";	
 	}
 
 
 
 	function OnLoad()
	{
		selectObject('', 'warehouseid', '1');
		selectType('', 'outtype', 'ckdj');
		selectType('0', 'outstatus', 'ckdzt');
	}
</script>

</head>

<body  onload="javascript:OnLoad();">
<div class="con_bk" >
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td>
     
	     <div class="wz" >
	      <div id="dqwz" class="f_l">当前位置：<span>出库管理</span> &gt;&gt; <span>新建出库单</span></div>
	      <div  class="f_r" id="caozuo">
	        <ul>
	          <%if(outboundNewsqdAddSo){%><li class="tubiao4" style="width:88px;"><a href="#" onclick="ddaddinvoice();">按订单添加</a></li><%}%>
	          <%if(outboundNewsqdProduct){%><li class="tubiao2" style="width:90px;"><a href="#" onclick="addinvoicedetail();">出库产品</a></li><%}%>
	          <%if(outboundNewsqdAdd){%><li class="tubiao4"><a href="#" onclick="addinvoice();">添加</a></li><%}%>
	          <%if(outboundNewsqdQuery){%><li class="tubiao1"><a href="#" onclick="queryinvoice();">查询</a></li><%}%>
	        </ul>
	      </div>
	    </div>
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="5"></td>
   </tr>
 </table>			
		<table id="querycondition" width="99%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
              <tr>
                <td width="80"  class="yx1"><div align="right">仓&nbsp;&nbsp;&nbsp;&nbsp;库：</div></td>
                <td width="150" class="yx"><div align="left">
                  <select id="warehouseid" style="width:100px;">
                    <option value="">--请选择--</option>
                  </select></div>
                </td>
                <td width="80" class="yx1" align="right"><div align="right">单据状态：</div></td>
                <td width="150"class="yx">
                  <select id="outstatus" style="width:100px;">
                    <option value="">--请选择--</option>
                  </select>
                </td>
                <td class="yx1" width="60"><div align="right">出库类型：</div></td>
                <td class="xx1"  ><div align="left">
                  <select id="outtype" style="width:100px;">
                    <option value="">--请选择--</option>
                  </select></div>
                </td>
             </tr>
             <tr>
             	<td width="80"  class="y1"><div align="right">收&nbsp;货&nbsp;人：</div></td>
                <td width="150" class="x"><div align="left">
                  	<input type="text" name="customer_name"  readonly="readonly"  style="height:18px;width:100px;"/>
                  	<input type="hidden" name="customer_id" />
                  	<input name="moreBtn" type="button" class="button_select" value="…" onclick="openCustomer('<%=request.getContextPath()%>/standard/jsp/common/common_customer.jsp','800','520');" />
                </div>
                </td>
                <td width="80" class="y1"><div align="right">出库单号：</div></td>
                <td width="200" class="x" >
                	<input type="text" name="outstockid"   style="height:18px;width:100px;"/>
                </td>
                <td width="60" class="y1"><div align="right">日&nbsp;&nbsp;&nbsp;&nbsp;期：</div></td>
                <td>
                	
                  	<input name="start_time" type="text" size="13" value="<%=strTime%>" onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" />
                  -<input name="end_time" type="text" size="13" value="<%=strTime%>" onfocus="calendar();"  class="Wdate" style="height:18px;width:100px;" /> 
               
                  </td>     
              </tr>
              
            </table> 
            
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="5"></td>
   </tr>
 </table>
 
     </td>
   </tr>
   <tr>
     <td height="100%"> 
     
  <!-- ***** 调度作业信息及作业详细*****-->
 	 <table width="99%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
<!-- 						<tr> -->
<!-- 							<td height="18" class="title">出库单基本信息</td> -->
<!-- 						</tr> -->
		<tr>
	     <td height="180">
		   <iframe id="list" name="list" width="100%" height="100%"   frameborder="0"  scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/outbound/newckd/outbound_newckd_list.jsp"></iframe>
		 </td>
	   </tr>
	   <tr>
	     <td height="25">
	  	  <iframe  id="page" name="page" src="<%=request.getContextPath()%>/standard/jsp/page/page.jsp?linerow=6&flag=N"   width="100%" height="100%" scrolling="no"  frameborder="0" ></iframe> 
	     </td>
	   </tr>
	   <tr >
            <td valign="top" class="title" height="18" >
              		出库单【<span id="dckdid" style="color: red;font-weight:bold;"></span>】明细信息
            </td>
        </tr>
	   <tr>
	     <td>
		   <iframe id="detail" width="100%" height="100%"   frameborder="0"  scrolling="yes"
	               src="<%=request.getContextPath()%>/standard/jsp/outbound/newckd/outbound_newckd_dlist.jsp"></iframe>
		 </td>
	   </tr>
	 </table>
 <!-- *******************************-->
 
 
 
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
