<%@ page contentType="text/html; charset=GB2312"%>
<%
	String id = request.getParameter("id"); //调整单ID
	String wharehouseid = request.getParameter("wharehouseid"); 
%>
<html>
<head>
<title>增加</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/sequenceMgr.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>

<script type="text/javascript">
<!--
	//查询
	function queryData(id)
	{	
	    var actionStr = "BMSService?actionCode=inventoryAdjustAction&method=ricoExecDetail&id=" + id;	 
		window.myIframe.location.href = "<%=request.getContextPath()%>/" + actionStr;
	}
	//添加库存信息
	function addData(){
		 var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/inventory/adjust/kc_adjust_header_addkc.jsp?wharehouseid="+"<%=wharehouseid%>",'','dialogWidth=800px;dialogHeight=300px');
			if(result != null && result.length > 1)
		   	{
		 var actionStr = result+"&id="+"<%=id%>";
	     window.myIframe.location.href = "<%=request.getContextPath()%>/" + actionStr;	
		   	}
	}
	//删除
	function delData()
	{
		  var deleteName = getDelCheckName();  
		  var id = document.getElementById("MMId").value;
		   if(deleteName == null||deleteName.length <1){
		        	confirm("请选择所要删除的库存调整单详细");
		    }else{
					var del = confirm("确定删除所选库存调整单详细");
					if(del){
						window.myIframe.location.href = "<%=request.getContextPath()%>/BMSService?actionCode=inventoryAdjustAction&method=ricoExecInDeleteDetail&id=" + id+"&deleteStr="+deleteName;		
					}
		    }
	}
	//获得选择的选择框的值
	function getDelCheckName()
	{
			var strDel = '';
			
			var length = myIframe.myform.elements.length;
		
			for(i=0;i<length;i++){
			    if(myIframe.myform.elements[i].name!='checkbox_all'&& myIframe.myform.elements[i].type=='checkbox'&& myIframe.myform.elements[i].checked== true){
			         strDel = strDel + myIframe.myform.elements[i].value + ',';
			    }
			}
			return strDel;
	}
	//库存查询
	function inventoryData()
	{
			var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/inventory/adjust/kc_inbound_excepton.jsp?wharehouseid="+"<%=wharehouseid%>",'','dialogWidth=800px;dialogHeight=600px');
		   	
		   	if(result != null && result.length > 0)
		   	{	
		   		var temStr = result.split("|");
		   		document.getElementById("inventoryid").value = temStr[0];//异常单id
		   		//根据异常单来获取库位DWR
		   		
		   		
		   		document.getElementById("cargoSpace").value = temStr[1];//异常单id
		   		//根据库位来获取库区，巷道
		   		
		   		
		   		
		   	}
	}
	function callSku(data)
	{
		if(data != null)
		{
			document.getElementById("skulength").value = data.length;
			document.getElementById("skuwidth").value = data.width;
			document.getElementById("skuhigh").value = data.height;
			document.getElementById("skugrossweight").value = data.weight;
			
		}
		
	}
	function sumCubic()
	{
		var toqty = document.getElementById("toqty").value;
		if(toqty == "" || toqty.length <1)  
       	{
       		alert("【目标数量】不能为空!");
       		return false; 
       	}
		if(toqty!=null && toqty.length>0 && !isFloat(toqty,"+","0"))
		{
			alert("【目标数量】必须是正浮点数或零!");
			return false;
		}
		var skulength = document.getElementById("skulength").value;
		var skuwidth = document.getElementById("skuwidth").value;
		var skuhigh = document.getElementById("skuhigh").value;
		var grossweight = document.getElementById("skugrossweight").value;
		var netweight = document.getElementById("skunetweight").value;
		var cublic = (skulength*skuwidth*skuhigh).toFixed(6);
		
		document.getElementById("tocubic").value = (toqty*cublic).toFixed(6);
		document.getElementById("togrossweight").value = (toqty*grossweight).toFixed(6);
		document.getElementById("tonetweight").value = (toqty*netweight).toFixed(6);
	}
	
	
	
	
	

	//新增明细
	function saveDetailData(id)
	{	
		var inventoryid = document.getElementById("inventoryid").value;//异常单ID
		var productid = document.getElementById("package_id").value;//产品ID
		var printDate = document.getElementById("printDate").value;//生产日期
		var traycode = document.getElementById("traycode").value;//托盘条码
		var punit = document.getElementById("punit").value;//单位
		var pnum = document.getElementById("pnum").value;//库存数量
		var lotid = document.getElementById("lotid").value;//批号类型
		var lotinfo = document.getElementById("lotinfo").value;//批号信息
		
		var isSplit = document.getElementById("isSplit").checked ;//是否是整托，默认是整托
		
		 var actionStr = "BMSService?actionCode=inventoryAdjustAction&method=ricoExecAddDetailInboundExce"
				 + "&id="+id
				 + "&inventoryid=" + inventoryid + "&productid=" + productid + "&printDate=" + printDate 
				 + "&traycode=" + traycode+ "&punit=" + punit + "&pnum="
				 + pnum + "&lotid=" + lotid + "&lotinfo=" + lotinfo + "&isSplit=" + isSplit;
			    window.myIframe.location.href = "<%=request.getContextPath()%>/" + actionStr;
	}
	
	//明细数据检验
	function checkDetailData()
	{  
		var inventoryId = Trim(document.getElementById("inventoryid").value);
		//var tonum = Trim(document.getElementById("tonum").value);
		if(inventoryId == "" || inventoryId.length < 1)
		{
			alert("【出库异常】不能为空!");
			return false;
		}
		/*
		if(tonum == null || tonum.length <1)
		{
			alert("【目标数量】不能为空!");
			return false;
		}
		if(tonum!=null && tonum.length>0 && !isFloat(tonum,"+","0"))
		{
			alert("【目标数量】必须是正浮点数或零!");
			return false;
		}
		*/
	}

		//修改
	   function updateData()
	   {
			if(updateAble())
			{		
				var param = getCheckValue();
				var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=inventoryAdjustAction&method=ricoExecQueryDetail&id="+param;
				var result = showModalDialog(strUrl,'','dialogWidth=800px;dialogHeight=400px');
			   	if(result != null && result.length > 1)
			   	{
			   		myIframe.location.href="<%=request.getContextPath()%>/"+result;
			 
			   	}
		   	}
		}
		//修改数据时调用
		function updateAble(){
		 	var icount = getCheckCount();
			if(icount<1){
				alert("请选择您要的数据项!");
			  	return false;
			}
			if(icount>1){
			 	alert("只能选择一行数据!");
			 	return false;
			}
			return true;
		}
		//获取复选框个数
		function getCheckCount()   
	  	{   
	  		var counter = 0;
	  		var length = myIframe.myform.elements.length;
			for(i=0;i<length;i++)
			{
			    if(myIframe.myform.elements[i].name!='checkbox_all'&& myIframe.myform.elements[i].type=='checkbox'&& myIframe.myform.elements[i].checked== true)
			    {
			         counter = counter + 1;
			    }
			}
	  		return counter;
	   }
	   //获取单个复选框的值
	   function getCheckValue()
	   {
	   	    var value = "";
	  		var length = myIframe.myform.elements.length;
		
			for(i=0;i<length;i++)
			{
			    if(myIframe.myform.elements[i].name!='checkbox_all'&& myIframe.myform.elements[i].type=='checkbox'&& myIframe.myform.elements[i].checked== true)
			    {
			         value = myIframe.myform.elements[i].value;
		  				break; 
			   }
			}
	  		return value;
	   }	
	   
	   
	   function Onload(){
		   selectObject('', 'lotid', 'phmc');
	   }
-->
</script>
</head>
<body onload="javascript:queryData('<%=id%>');javascript:Onload();">
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：库存管理 -&gt; 库存调整单 -&gt; 调整单明细</div></td>
   </tr>
 </table>

 <!-- ======== 明细信息开始 ======== -->
 <form id="myform2" name="myform2" method="post">
 <input type="hidden" id="MMId" name="MMId" value="<%=id%>">
 <input type="hidden" id="inventoryId" name="inventoryId" value="">
 <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
   <tr>
     <td width="15%" class="yx1"><div align="right"><font color="red">*&nbsp;</font>入库异常货位：<input type="hidden" name="inventoryid" ></div></td>
     <td width="15%" class="yx">
     		<div align="left">
     		<input type="hidden" name="wharehouseid" value="<%=wharehouseid%>">
	        <input type="hidden" name="inventoryid" size="15" readonly="readonly" class="input_readonly">
	        <input type="text" name="cargoSpace" size="15" readonly="readonly" class="input_readonly">
            <input onclick="inventoryData();" type="button" name="moreSku" value="…" class="button_select">
     		</div>
     </td>
     
     
     <td width="10%" class="yx1"><div align="right">品名：</div></td>
	  <td width="17%" class="yx">
	  <div align="left">
	      	<input type="hidden" name="package_id">
	      	<input type="text" name="package_name" size="15" readonly>
            <input onClick="openProductIdNameAndPunit('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  
            	type="button" value="…" class="button_select">
       </div>
	  </td>
     
     <td width="12%" class="yx1"><div align="right">产品生产日期：</div></td>
     <td width="15%" class="yx"><div align="left"><input id="printDate" type="text" size="15" onfocus="calendar();" class="Wdate" style="width:117px;"></div></td>
   
     
   </tr>
   
   <tr>
        
        <td width="10%" class="yx1"><div align="right">托盘条码：</div></td>
        <td width="15%" class="yx" ><div align="left"><input type="text" name="traycode"  ></div></td>
        
        <td width="10%" class="yx1"><div align="right">单位：</div></td>
        <td width="15%" class="yx"><div align="left"><input type="text" name="punit" ></div></td>
   		
   		<td width="10%" class="yx1"><div align="right">库存数量：</div></td>
        <td width="15%" class="yx"><div align="left"><input type="text" name="pnum" ></div></td>
        
        
   
   </tr>
  
  <tr>
        <td width="10%" class="yx1"><div align="right">批号类型：</div></td>
        <td width="15%" class="yx"><div align="left"><select name='lotid' style='width:110px;'><option value=''>--请选择--</option></select></div></td>
        
        <td width="10%" class="yx1"><div align="right">批号信息：</div></td>
        <td width="15%" class="yx"><div align="left"><input type="text" name="lotinfo" ></div></td>
        
        <td width="10%" class="yx1"><div align="right">是否是整托</div></td>
        <td width="15%" class="yx" ><div align="left"><input type="checkbox" checked="checked" name="isSplit" ></div></td>
   </tr>
 
     
   <tr>
     <td height="27" colspan="6">
       <div align="center">
         <input type="button" name="saveDetailBtn" value="增加明细" class="BUTTON_STYLE1" onclick="if(checkDetailData()!=false){saveDetailData('<%=id%>');}">&nbsp;
         <input type="button" name="delDetailBtn" value="删除明细" class="BUTTON_STYLE1" onclick="delData()">&nbsp;
        <!--  <input type="button" name="updateDetailBtn" value="修改明细" class="BUTTON_STYLE1" onclick="updateData();">&nbsp; -->
         <input type="button" name="updateDetailBtn" value="查询" class="BUTTON_STYLE1" onclick="queryData('<%=id%>');">&nbsp;
         <input type="button" name="resetDetailBtn" value="关闭" class="BUTTON_STYLE1" onclick="javascript:window.close();"></div>
     </td>
   </tr>
 </table>
 </form>
 <!-- ======== 明细信息结束 ======== -->
 
 
 <table width="100%" height="10"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>


 <!-- ======== 明细列表开始 ======== -->
 <table width="98%" height="400" border="0" align="center" cellpadding="0" cellspacing="0" >
   <tr>
	 <td>
	   <iframe id="myIframe" src="<%=request.getContextPath()%>/standard/jsp/inventory/adjust/kc_adjustdetail_list.jsp" frameborder="0" width="100%" height="100%"  scrolling="yes">
	   </iframe>
	 </td>
   </tr>
 </table>
 <!-- ======== 明细列表结束 ======== -->
 
 
</body>
</html>