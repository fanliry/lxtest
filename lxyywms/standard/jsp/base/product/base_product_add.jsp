<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%
	String strTime =  StrTools.getCurrDateTime(8);
%>
<html>
<head>
<title>增加物品信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>	
<script type="text/javascript">

	function OnOk(){

	    var productname = document.getElementById("productname").value;	// 产品标准名，
	    var spec = document.getElementById("spec").value;				// 产品规格，
	    var barcode = "";//document.getElementById("barcode").value;			// 产品条码，
	    var pkspec = document.getElementById("pkspec").value;			// 产品商用名
	    var recunit = document.getElementById("recunit").value;			// 计量单位，
	    //var pttypeid = document.getElementById("pttypeid").value;		// 物品类别
	    var storageareaid = document.getElementById("environment").value;	// 存储环境
	    //var storagespaceid = document.getElementById("cargospace_id").value;// 存储库位
	    var length = document.getElementById("length").value;			// 长
	    var width = document.getElementById("width").value;				// 宽
	    var height = document.getElementById("height").value;			// 高
	    var upperlimit = document.getElementById("upperlimit").value;	// 托盘上限
	    var lowerlimit = document.getElementById("lowerlimit").value;	// 托盘下限
	    var allocationid = "";	// 分配规则ID
	    var lotid = "";				// 批次属性ID
	    var pakageid = "";			// 包装规则
	    var validityterm = document.getElementById("validityterm").value;	// 有效期
	    var validitytype = document.getElementById("validitytype").value;	// 有效期类型名
	    var productCode = document.getElementById("productCode").value;			// 产品编码
	    var remark2 = document.getElementById("remark2").value;			// 备注
	    var remark3 = document.getElementById("remark3").value;			// 备注
	    var remark4 = "";			// 备注
	    var useflag = document.getElementById("useflag").checked?"Y":"N";		// 可用标志
	    var producttype = document.getElementById("producttype").value;
	    
		//物品名
		if(productname == null || productname.length <1)
		{
			alert("【物品名】不能为空!");
			return;
		}
		
		if(strlen(productname) > 100){
			alert("【物品名】过长!");
			return;
		}
		//规格
		if(spec!=null && spec.length>0)
		{
			if(strlen(spec) > 200){
				alert("【规格】过长!");
				return;
			}
		}
		
/* 		//物品条码
		if(barcode!=null && barcode.length>0)
		{
			if(strlen(barcode) > 40){
				alert("【物品条码】过长!");
				return;
			}
		} */
		//长
		if(length != null && length.length > 0 && length != 0)
		{
			if(!isDig(length))
			{
				alert("【长】只能为正浮点数或0！");
				return;
			}
		}
		
		//宽
		if(width != null && width.length > 0 && width != 0)
		{
			if(!isDig(width))
			{
				alert("【宽】只能为正浮点数或0！");
				return;
			}
		}
		
		//高
		if(height != null && height.length > 0 && height != 0)
		{
			if(!isDig(height))
			{
				alert("【高】只能为正浮点数或0！");
				return;
			}
		}
		
		//库存上限
		if(upperlimit != null && upperlimit.length > 0 && upperlimit != 0)
		{
			if(!isDig(upperlimit))
			{
				alert("【库存上限】只能为正浮点数或0！");
				return;
			}
		}
		
		//库存下限
		if(lowerlimit != null && lowerlimit.length > 0 && lowerlimit != 0)
		{
			if(!isDig(lowerlimit))
			{
				alert("【库存下限】只能为正浮点数或0！");
				return;
			}
		}
		
		//有效期
		if(validityterm != null && validityterm.length > 0 && validityterm != 0)
		{
			if(!isDig(validityterm))
			{
				alert("【有效期】只能为正浮点数或0！");
				return;
			}
		}
		
		//物品编码 
		if(productCode==null && productCode.length<1)
		{
			alert("【物品编码】不能为空！");
			return;
		}
		
		//备注2
		if(remark2!=null && remark2.length>0)
		{
			if(strlen(remark2) > 30){
				alert("【备注2】过长!");
				return;
			}
		}
		
		//备注3
		if(remark3!=null && remark3.length>0)
		{
			if(strlen(remark3) > 30){
				alert("【备注3】过长!");
				return;
			}
		}
		
		//备注4
		if(remark4!=null && remark4.length>0)
		{
			if(strlen(remark4) > 30){
				alert("【备注4】过长!");
				return;
			}
		}
		if(producttype != null && producttype.length < 1)
		{
			alert("请选择产品类别");
			return;
		}
		var back_msg = "&productname=" + productname + "&spec=" + spec + "&barcode=" + barcode + "&pkspec=" + pkspec
			 + "&recunit=" + recunit + "&length=" + length + "&width=" + width + "&height="
			 + height+ "&upperlimit=" + upperlimit + "&lowerlimit=" + lowerlimit+ "&storageareaid=" 
			 + storageareaid +"&allocationid=" + allocationid+ "&lotid=" + lotid 
			 + "&pakageid=" + pakageid  + "&validityterm=" + validityterm + "&validitytype=" 
			 + validitytype + "&productCode=" + productCode + "&remark2=" + remark2 + "&remark3=" + remark3 + "&remark4=" + remark4 + "&producttype=" + producttype
			 + "&useflag=" + useflag;		 
		window.returnValue = back_msg;
    	window.close();
	}
	
	function OnLoad(){
		var typevalue = "yxqlx";		//有效期类型的值	
		selectType("", "validitytype", typevalue);
		
		var typevalue = "punit";		//单位类型的值	
		selectType("", "recunit", typevalue);
		selectType("", "producttype", "producttype");
		//DWREngine.setAsync(false);
		//selectObject("", "warehouseid", "1");
		//DWREngine.setAsync(true);
		
		//var warehouseid = document.getElementById("warehouseid").value;
		//selectAreaList("", "whAreaId", warehouseid, "1");
		
		//selectObject("", "pttypeid", "6");		//物品类别
		
		selectType("", "environment", "cchj");//存储环境
	}
	
	//仓库变化时候
	function onWhChange(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");		//库区
		selectRules("", "allocationid", warehouseid,  "2");	//分配规则
	}

</script>
</head>

<body onload="OnLoad();">
  <form name="myForm" method="post" action="">
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">当前位置：基本信息 -&gt; 物品管理 -&gt; 新增物品信息</div></td>
    </tr>
  </table>
 
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right"><span class="red">*</span>产品标准名：</td>
      <td class="yx"><input type="text" id="productname" maxlength="100" size="20"></td>
      <td width="100px" class="yx1" align="right">产品商用名：</td>
      <td class="xx1"><input type="text" id="pkspec" maxlength="32" size="20"></td>
    </tr> 
    <tr>
       <td class="yx1" align="right"><span class="red">*</span>产品类别：</td>
      <td class="yx"><select id="producttype" style="width:110px;">
      	<option value="">---请选择---</option>
      </select></td>
      <td width="100px" class="yx1" align="right">规格：</td>
      <td class="xx1"><input type="text" id="spec" maxlength="200" size="20"></td>
    </tr>
    <tr>
    	 <td class="yx1" align="right"><span class="red">*</span>产品编码：</td>
      <td class="yx"><input type="text" id="productCode" maxlength="30" size="30"></td>  
      
      <td class="yx1" align="right">长：</td>
      <td class="xx1"><input type="text" id="length" maxlength="14" size="14" value="0.0"></td>
    </tr>
    <tr>
	   <td class="yx1" align="right">产品单位：</td>
      <td class="yx"><select id="recunit" style="width:110px;"><option value="">--请选择--</option></select></td>
      <td class="yx1" align="right">宽：</td>
      <td class="xx1"><input type="text" id="width" maxlength="14" size="14" value="0.0"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">存储环境：</td>
      <td class="yx"><select id="environment" style="width:110px;"><option value="">--请选择--</option></select></td>  
      <td class="yx1" align="right">高：</td>
      <td class="xx1"><input type="text" id="height" maxlength="14" size="14" value="0.0"></td>
    </tr>
    <tr>
	   
      <td class="yx1" align="right">托盘上限：</td>
      <td class="yx"><input type="text" id="upperlimit" maxlength="14" size="14" value="0.0"></td>
       <td class="yx1" align="right">托盘下限：</td>
      <td class="xx1"><input type="text" id="lowerlimit" maxlength="14" size="14" value="0.0"></td>
    </tr>
    <tr>
    
     <td class="yx1" align="right">有效期：</td>
      <td class="yx"><input type="text" id="validityterm" maxlength="14" size="14" value="0"></td>
      <td class="yx1" align="right">有效期类型：</td>
      <td class="xx1"><select id="validitytype" style="width:110px;"><option value="">--请选择--</option></select></td>
      
      
    </tr>
    <tr>
       <td class="yx1" align="right">可用标志：</td>
      <td class="yx"><input type="checkbox" id="useflag" checked></td>
       <td class="yx1" align="right">备注2：</td>
      <td class="xx1"><input type="text" id="remark2" maxlength="30" size="30"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">备注3：</td>
      <td class="yx"><input type="text" id="remark3" maxlength="30" size="30"></td>
      <!-- <td width="100px" class="yx1" align="right">产品条码：</td>
      <td class="yx"><input type="text" id="barcode" maxlength="32" size="20"></td> -->
      <td class="xx1" colspan="2"></td>
    </tr>
   
    <tr>
      <td height="27" colspan="4" align="center">
        <input type="button" onclick="OnOk()" id="add" value="&nbsp;&nbsp;&nbsp;保存" class="button_add">&nbsp; 
        <input type="reset" id="resetDetailBtn" value="&nbsp;&nbsp;&nbsp;重置" class="button_reset">&nbsp;
        <input type="button" onClick="window.close()" id="resetBtn" value="关闭" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
  </form>
  
</body>
</html>