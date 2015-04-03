<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%@ page import="java.text.NumberFormat" %>
<%
  String detailid = (String) request.getParameter("detailid");
  String Virtualwhid = (String) request.getParameter("Virtualwhid");
  String productid = (String) request.getParameter("productid");
  String lotinfo = (String) request.getParameter("lotinfo");
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
	    var key = "";
		var check_ids = list.document.getElementsByName("check_id");
		var checkNums = list.document.getElementsByName("checkNum");//数量
		var kcNums = list.document.getElementsByName("kcNum");//库存数量
		for(var i=0; i<check_ids.length; i++)
		{
			if(check_ids[i].checked)
			{
				if(checkNums[i].value == null || (checkNums[i].value).length < 1 || !IsFloat(checkNums[i].value) || !IsFloat(kcNums[i].value)){
						alert("【数量】不能为空 且 只能为数字！");
						return;
				}
				if(parseFloat(kcNums[i].value) < parseFloat(checkNums[i].value)){
					alert("分配数量不能大于库存数量！");
					return;
				}
				key += check_ids[i].value + "|"+checkNums[i].value+ ",";
			}
		}
		if(key == "")
		{
			alert("请先选择一条记录！");
		}else{
		    key = key.substring(0, key.length-1);
		    window.returnValue =key; //库存id&分配数量,库存id&分配数量
    		window.close();
		}
	}
	function IsNum(num)
	{ 
		var reNum=/^\d*$/; 
		return(reNum.test(num)); 
	}
  	function OnLoad()
	{	
		list.location.href = strUrl + "outBoundAction&flag=lotinfopickzc&detailid=<%=detailid%>&Virtualwhid=<%=Virtualwhid%>&productid=<%=productid%>&lotinfo=<%=lotinfo%>";
	}
 </script>
</head>
  <body onload="javascript:OnLoad();">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td>  
   <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td><div class="font_001F56_bold_12">当前位置：按批号拣货出库 -&gt; 暂存列表</div></td>
     <td align="right" >
            <input name="button_delete" type="button" class="button_add" id="button_delete" value="&nbsp;&nbsp;确定" onclick="assignData();" />
            <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="关闭" onClick="window.close();" />
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
	   <iframe id="list" src="<%=request.getContextPath()%>/standard/jsp/outbound/lotinfopick/zc_kc_list.jsp" scrolling="auto" frameborder="0" width="100%" height="100%">  
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
