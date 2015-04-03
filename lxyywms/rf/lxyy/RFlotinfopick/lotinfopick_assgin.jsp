<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.text.NumberFormat" %>
<%
	
	//设置重复提交的标志变量，begin为首次加载，finish为已提交
	session.setAttribute("submitFlag","begin");
	String strWarehouseID = request.getParameter("warehouseid");
	if(strWarehouseID == null  || strWarehouseID.equals("null")){
		strWarehouseID = (String)session.getAttribute("warehouseid");
	}
%>
<html>
  <head>
    <title>浙江刚玉物流仓库管理系统</title>  
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
	<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/standard/style/load.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendartime.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lotsearch.js"></script>
	<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
 <script>
    var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
 	//检查数量是否为数字
	function IsNum(num){ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
	//检查数据是否为浮点数
	function IsFloat(ch){
		var re = /^\d+(\.\d+)?$/;
		return re.test(ch);
	}
	function IsRight(print_date){ 
		var str = /^(\d{4})-(\d{2})-(\d{2})$/;
		return(str.test(print_date));
	}
 	//分配
	function assignData()
	{
	    var key = null;
		var check_ids = list.document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++)
		{
			if(check_ids[i].checked)
			{
				key = check_ids[i].value;
				break;
			}
			
		}
		if(key == null)
		{
			alert("请先选择一条记录！");
			return;
		}else{
		    var info =  key.split("|");
		    var detailid = info[0];
		    var Virtualwhid = info[1];
		    var productid = info[2];
		    var lotinfo = info[3];
		    var result = showModalDialog("<%=request.getContextPath()%>/rf/lxyy/RFlotinfopick/zc_kc.jsp?detailid="+detailid+"&Virtualwhid="+Virtualwhid+"&productid="+productid+"&lotinfo="+lotinfo, "","dialogWidth=350px;dialogHeight=500px;");
			if(result != null && result.length > 0){ ////库存id|分配数量,库存id|分配数量
			  list.location.href = strUrl + "outBoundAction&method=pickZcProduct&detailid="+detailid+"&result="+result;
			}
		}
	}
 	
	function IsNum(num)
	{ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
  	function OnLoad()
	{	
		list.location.href = strUrl + "outBoundAction&flag=lotinfopick&isRF=rf";
	}
 </script>
</head>
  <body onload="javascript:OnLoad();">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td>  
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td class="TD_LIST_TITLE"><div class="list_title">RF按批号拣货出库(<a href="<%=request.getContextPath()%>/rf/main.jsp?warehouseid=<%=strWarehouseID%>">返回</a>)</div></td>
   </tr>
      	<tr>
			<td class="TD_ADD" align="center" >
			<input type="button" name="add" class="BUTTON_STYLE1" value="分配" onClick="assignData();">
			</td>
		</tr>
  </table>
</td>
   </tr>
   <tr>
   	 <td height="10">
   	 </td>
   </tr>
   <tr>
     <td height="100%"> 
  <!-- ======== 库存列表开始 ======== -->
 <table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
   <tr>
	 <td>
	   <iframe id="list" src="<%=request.getContextPath()%>/rf/lxyy/RFlotinfopick/lotinfopick_assgin_list.jsp" scrolling="auto" frameborder="0" width="100%" height="100%">  
	   </iframe>
	 </td>
   </tr>
 </table>
 <!-- ======== 库存列表结束 ======== -->
     </td>
   </tr>
 </table>
  </body>
</html>
