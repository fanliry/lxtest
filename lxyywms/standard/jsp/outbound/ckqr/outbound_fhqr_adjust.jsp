<%@ page contentType="text/html; charset=GBK"%>
<%
 	//单据ID
    String strOutBoundId = request.getParameter("outid");

 %>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />


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
		if(k == 0)
		{
			alert("请选择一条或多条记录！一条记录可以修改下移数量；多条则批量下移。");
			return;
		}else{
			detailid = detailid.substring(0, detailid.length-1);
		}
		var msg = "确定下移到";
		var select = document.getElementById("atb").checked;
		//若选择操作为：A客户-> B客户
		var bdetailid = "";	//B客户单据详细ID
		var bvoiceid = "";	//B客户单据ID
		var warehouseid = "";//仓库ID
		var zcwhareaid = ""; //暂存区ID
		if(select)
		{
			//B客户的单据详细ID
			var bcheck_ids = down.list.document.getElementsByName("check_id");	
			for(var i=0; i<bcheck_ids.length; i++)
			{
				if(bcheck_ids[i].checked == true){
					bdetailid = bcheck_ids[i].value ;
					bvoiceid = bcheck_ids[i].id ;
					break;
				}
			}
			if(bdetailid == ""){
				alert("请选择B客户的一条记录！");
				return;
			}
			msg+="B客户中？";
		}else{
			msg+="暂存区中？";
			warehouseid = down.document.getElementById("warehouseid").value;//仓库ID
		 	zcwhareaid = down.document.getElementById("zcwhareaid").value;  //暂存区ID
		 	if(zcwhareaid == ""){
				alert("暂存区不能为空！");
				return;
			}
		}
		if(k == 1){//单条记录下移
			var result = showModalDialog("<%=request.getContextPath()%>/BMSService?actionCode=sendAction&flag=4&jobdetailid="+detailid, '', 
				"dialogWidth=500px;dialogHeight=600px; ");
			if(result != null && result.length > 0) 
			{
				if(select)//A客户->B客户
				{
					up.location.href = strUrl + "sendAction&method=ricoExecMove&detailid="+detailid+"&flag=2&binvoiceid=" + bvoiceid + "&binvoicedetailid="+ bdetailid + result;
				}else{//A客户->暂存区
					up.location.href = strUrl + "sendAction&method=ricoExecMove&detailid="+detailid+"&flag=1&warehouseid=" + warehouseid + "&whAreaId=" + zcwhareaid + result;
				}
			}	
		}else{//多条记录
			if(confirm(msg)) 
			{
				if(select)//A客户->B客户
				{
					up.location.href = strUrl + "sendAction&method=ricoExecBatchMove&detailids="+detailid+"&flag=2&binvoiceid=" + bvoiceid + "&binvoicedetailid="+ bdetailid;
				}else{//A客户->暂存区
					up.location.href = strUrl + "sendAction&method=ricoExecBatchMove&detailids="+detailid+"&flag=1&warehouseid=" + warehouseid + "&whAreaId=" + zcwhareaid;
				}	
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
		var boxcode = document.getElementById("boxcode").value;
		if((traycode == null || traycode.length < 1) && (boxcode == null || boxcode.length < 1)){
			alert("【托盘条码】或【箱条码】不能为空！");
			return;
		}
		up.location.href = strUrl + "sendAction&flag=3&invoiceid=" + outId + "&traycode=" + traycode + "&boxcode=" + boxcode;
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
		       <input type="radio" id="atb" name="radio" class="input_radio" onClick="ChangeOp(2)" >A客户&hArr;B客户
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
     <td class="y1" width="65"><div align="right">箱条码：</div></td>
     <td class="x" width="80"><div align="left"><input type="text" name="boxcode" size="10"></div></td> 
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
       <input name="button" type="image" class="button" height="15" width="70" src="<%=request.getContextPath()%>/standard/images/arrow_down.png" onclick="moveDown();">
       <input type="button" name="button" class="BUTTON_STYLE1" value="关闭" onclick="window.close()">
     </td>
   </tr>
   <tr>
     <td height="50%">
       <iframe id="down" name="down" width="100%" height="100%" frameborder="0" scrolling="no"
  		   		src="<%=request.getContextPath()%>/standard/jsp/outbound/fhqr/outbound_fhqr_adjust_zc.jsp?aoutid=<%=strOutBoundId%>"></iframe>
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
 