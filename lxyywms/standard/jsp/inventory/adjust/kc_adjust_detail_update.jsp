<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryAdjustDetail"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseProduct" %>

<html>
<head>
<title>更新</title>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<%
		InventoryAdjustDetail detail = null;
		BaseProduct sku = null;
		if(request.getAttribute("adjustdetail") != null)
		{
			detail = (InventoryAdjustDetail)request.getAttribute("adjustdetail"); 
		}
		String strId = "";//ID
		String whAreaName = ""; //库区
		String cargoSpace = "";//货位
		String product = "";//产品名
		String printdate = "";    //生产日期
		String unit = "";//单位
		String lotid = "";//批号类型
		String lotinfo = "";	//批号信息
		String traycode = "";	//托盘条码
		double pnum = 0;	//系统数量
		double tonum	= 0;	//目标数量
		if(detail != null)
		{
			strId = detail.getAdjustdetailid();
			whAreaName = detail.getWh_area_id();
			cargoSpace = detail.getCargo_space_id();
			product = detail.getTproductname();
			printdate = detail.getTproductname();
			unit = detail.getTpunit();
			lotid = detail.getTlotid();
			lotinfo = detail.getTlotinfo();
			
			traycode = detail.getTtraycode();
			pnum = detail.getSyspnum();
			tonum = detail.getRealitypnum();		
		}
		
%>
<script type="text/javascript">
<!--
	//修改明细
	function saveDetailData(id)
	{
		 var actionStr = "BMSService?actionCode=inventoryAdjustAction&method=ricoExecUpdateDetail"
				 + "&detailId="+id
				 + "&tonum=" + document.getElementById("tonum").value;	
		  window.returnValue=actionStr;
		  window.close(); 		
	}
	//明细数据检验
	function checkDetailData()
	{
		var tonum = Trim(document.getElementById("tonum").value);
		
		if(tonum == null || tonum.length <1)
		{
			alert("【目标数量】不能为空!");
			return false;
		}
	}
	function sumCubic()
	{
	    var strSkuId = document.getElementById("skuId").value;
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
		sequenceMgr.getSkuId(strSkuId,callSku);
		
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
function Onload(){
		   selectObject('<%=lotid%>', 'lotid', 'phmc');
	   }
-->
</script>
</head>

<body onload="javascript:Onload();">
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">当前位置：库存管理 -&gt; 库存调整 -&gt; 更新调整单明细</div></td>
   </tr>
 </table>

 <!-- ======== 明细信息开始 ======== -->
 <form id="myform2" name="myform2" method="post">
 <input type="hidden" id="MMId" name="MMId" value="<%=strId%>">
 <input type="hidden" id="inventoryId" name="inventoryId" value="">
 <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
   <tr>
     <td width="15%" class="yx1"><div align="right">行状态：</div></td>
     <td width="18%" class="yx"><div align="left">
         <select name="status"  class="input_readonly"  style="width:130px;">
	     		<option value="0" selected="selected">创建</option>
	   	</select></div>
     </td>
   <td width="15%" class="yx1"><input type="hidden" name="strId" value="<%=strId%>"><div align="right">库区：</div></td>
     <td width="18%" class="yx"><div align="left"><input type="text" name="whAreaName" value="<%=whAreaName%>"readonly="readonly" class="input_readonly"></div></td>
     <td width="15%" class="yx1"><div align="right"><font color="red">*&nbsp;</font>库位：</div></td>
     <td width="19%" class="xx1"><div align="left">
	        <input type="text" name="cargoSpace"value="<%=cargoSpace%> " readonly="readonly" class="input_readonly">
     </div></td>
   </tr>  
   <tr>
    <td class="yx1"><div align="right">产品：</div></td>
    <td class="yx"><div align="left">
     	<input type="text" name="product" readonly="readonly" value="<%=product%>"class="input_readonly">
      </div></td>
    <td class="yx1"><div align="right">生产日期：</div></td>
    <td class="yx"><div align="left">
     	<input type="text" name="printdate" readonly="readonly"value="<%=printdate%>" class="input_readonly">
      </div></td>
     <td class="yx1"><div align="right">单位：</div></td>
     <td class="xx1"><div align="left"><input type="text" name="unit" value="<%=unit%>" readonly="readonly" class="input_readonly"></div></td>
   </tr>
     <tr>
     <td class="yx1"><div align="right">批号类型：</div></td>
     <td class="yx"><div align="left"><select name='lotid' style='width:110px;' readonly="readonly" class="input_readonly"><option value=''>--请选择--</option></select></div></td>
     <td class="yx1"><div align="right">批号信息：</div></td>
     <td class="yx"><div align="left"><input type="text" name="lotinfo" value="<%=lotinfo%>"readonly="readonly" class="input_readonly"></div></td>
     <td class="yx1"><div align="right">托盘条码：</div></td>
     <td class="xx1"><div align="left">
     	<input type="text" name="traycode" value="<%=traycode%>" readonly="readonly" class="input_readonly">
      </div></td>
     </tr>
     <tr>
     <td class="yx1"><div align="right">库存数量：</div></td>
     <td class="xx1"><div align="left">
     	<input type="text" name="pnum" value="<%=pnum%>" readonly="readonly" class="input_readonly">
      </div></td>
     <td class="yx1"><div align="right">目标数量：</div></td>
     <td class="xx1" colspan="3"><div align="left">
     <input type="text" name="tonum" value="<%=tonum%>"size="17"></div></td><!-- onblur="sumCubic()" -->
     </tr>
   <tr>
      <td height="27" colspan="6" align="center" >
             <input name="add" type="button" class="button_add" id="add" value="&nbsp;&nbsp;保存" onclick="if(checkDetailData()!=false){saveDetailData('<%=strId%>');}"/>
             <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="关闭" onClick="window.close();" />
     </td>
   </tr>
 </table>
 </form>
 <!-- ======== 明细信息结束 ======== -->
 <table width="100%" height="10"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>
</body>
</html>