<%@ page contentType="text/html; charset=GBK"%>
<%
 	//单据ID
    String strOutBoundId = request.getParameter("outid");
    String warehouseid = request.getParameter("warehouseid");

 %>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>

<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";	
	var outId="";//A客户单据ID
	function ChangeOp(flag)
    {
    	if (outId == null || outId.length < 1) {
			alert("A客户单据为空，请选择一张单据！");
			return;
		}
    	//A客户单据ID
    	if(flag == "1")
    	{
    		down.location.href = "<%=request.getContextPath()%>/standard/jsp/outbound/fhqr/outbound_fhqr_adjust_zc.jsp?aoutid="+outId;
    	}else if(flag == "2"){
    		down.location.href = "<%=request.getContextPath()%>/standard/jsp/outbound/fhqr/outbound_fhqr_adjust_B.jsp?aoutid="+outId;
    	}
    }

	//下移
	function moveDown()
	{
		//1: 出入库分配作业详细ID
		var detailid = "";
		var k=0;
		var check_ids = up.document.getElementsByName("check_id");
		if(check_ids.length == 0)
		{
			alert("A客户单据无记录，无需调整!");
			return;
		}else
		{
			for(i=0; i<check_ids.length; i++)
			{
				if(check_ids[i].checked)
				{
					detailid = detailid + check_ids[i].value + ",";
					k+=1;
				}
			}
		}
		if(k != 1)
		{
			alert("请选择一条下移。");
			return;
		}else{
			detailid = detailid.substring(0, detailid.length-1);
		}
		var msg = "确定下移到";
		var warehouseid = "";//仓库ID
		var zcwhareaid = ""; //暂存区ID
		if(k == 1){//单条记录下移
			var result = showModalDialog("<%=request.getContextPath()%>/BMSService?actionCode=sendAction&flag=4&jobdetailid="+detailid, '', 
				"dialogWidth=500px;dialogHeight=300px; ");
			var traycode = document.getElementById("traycode").value;
			if(result != null && result.length > 0) 
			{   
			    DWREngine.setAsync(false);
				up.location.href = strUrl + "sendAction&method=ricoExecMove&detailid="+detailid+"&flag=1&warehouseid=" + warehouseid + "&whAreaId=" + zcwhareaid + result+"&invoiceid=" + outId + "&traycode=" + traycode;
				DWREngine.setAsync(true);
				Search();
				down.location.href = "<%=request.getContextPath()%>/standard/jsp/outbound/fhqr/outbound_fhqr_adjust_zc.jsp?aoutid="+outId + "&warehouseid=<%=warehouseid%>";
				
			}	
		}	
	}
	//关闭触发事件
	window.onbeforeunload  =  function()   
    { 
    	window.returnValue = document.getElementById("warehouse_out_id").value;
    }
    
    //根据托盘条码或箱条码查询
    function Search()
    {
    	if (outId == null || outId.length < 1) {
			alert("A客户单据为空，请选择一张单据！");
			return;
		}
		var traycode = document.getElementById("traycode").value;
		//var boxcode = document.getElementById("boxcode").value;
		var boxcode = "";
		
		up.location.href = strUrl + "sendAction&flag=3&invoiceid=" + outId + "&traycode=" + traycode + "&boxcode=" + boxcode;
    }
	//****************************************************
	function OnLoad() 
	{
		outId = window.dialogArguments;
		document.getElementById("warehouse_out_id").value = outId;
		var back_msg = "<%=request.getAttribute("back_msg") == null ? "" : (String)request.getAttribute("back_msg")%>";
		if (back_msg != "") 
		{
			if(back_msg == "Y")
			{
				alert("操作成功!");
			}else
			{
				alert(back_msg);
			}
		}
		
	}

-->
</script>
</head>

<body onLoad="OnLoad()">
 <input type="hidden" name="warehouse_out_id">
 
 
 <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
   <tr>
     <td>
     
 <table width="98%" height="27" align="center" border="0" cellpadding="0" cellspacing="0">
   <tr>
     <td class="font_006699_bold_12">当前位置：出库管理 -&gt; 发货确认 -&gt; 调整</td>
     <td align="right" width="280">
     	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
		   <tr>
		     <td class="y1" align="right" width="70">操作选择：</td>
		     <td  align="left" width="200">
		       <input type="radio" id="atz" name="radio" class="input_radio" onClick="ChangeOp(1)" checked>A客户&hArr;暂存区
		       <!-- <input type="radio" id="atb" name="radio" class="input_radio" onClick="ChangeOp(2)" >A客户&hArr;B客户 -->
		     </td>
		   </tr>
		 </table>
     </td>
   </tr>
 </table>
  <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr >
     <td class="y1" width="70"><div align="right">托盘条码：</div></td>
     <td class="x" width="80"><div align="left"><input name="traycode" type="text" size="10"></div></td>
     <td  height="27" colspan="4"><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;
       <input onclick="Search()" type="button" name="queryBtn" value="查询" class="button_style">&nbsp;&nbsp;
     </div></td> 
   </tr>
 </table>
     </td>
   </tr>
   <tr>
     <td height="100%"> 
       
  <table width="98%" height="100%" align="center" border="0" cellpadding="0" cellspacing="0">
   <tr>
     <td width="84%" height="50%">

 <table width="100%" height="100%" align="center" border="0" cellpadding="0" cellspacing="0">
   <tr>
     <td height="50%">
       <iframe id="up" name="up" width="100%" height="100%" frameborder="0" scrolling="no"
  		   		src="<%=request.getContextPath()%>/standard/jsp/outbound/fhqr/outbound_fhqr_adjust_jobdetail.jsp"></iframe>
     </td>
   </tr>
   <tr>
     <td align="center">
       <input name="button1" type="image" class="button" height="15" width="70" src="<%=request.getContextPath()%>/standard/images/arrow_down.png" onclick="moveDown();">
       <input type="button" name="button" class="BUTTON_STYLE1" value="关闭" onclick="window.close()">
     </td>
   </tr>
   <tr>
     <td height="50%">
       <iframe id="down" name="down" width="100%" height="100%" frameborder="0" scrolling="no"
  		   		src="<%=request.getContextPath()%>/standard/jsp/outbound/fhqr/outbound_fhqr_adjust_zc.jsp?aoutid=<%=strOutBoundId%>&warehouseid=<%=warehouseid%>"></iframe>
     </td>
   </tr>
 </table>
     </td>
   </tr>
 </table>    

     </td>
   </tr>
 </table>    
 
</body>
</html>
 