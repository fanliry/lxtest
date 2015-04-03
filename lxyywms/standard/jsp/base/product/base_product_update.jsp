<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseProduct" %>
<%
	BaseProduct product = (BaseProduct)request.getAttribute("product");  
	String productid = product.getProductid();		// 物品id
	//String productCode = product.getProductCode();	//物品编码
    String productname = product.getProductname();	// 物品名
    String spec = product.getSpec();				// 规格
    String barcode = product.getBarcode();			// 物品条码
    String pkspec = product.getPkspec();			// 包装规格
    String recunit = product.getRecunit();			// 收货单位
    String sendunit = product.getSendunit();		// 发货单位
    double length = product.getLength();			// 长
    double width = product.getWidth();				// 宽
    double height = product.getHeight();			// 高
    double upperlimit = product.getUpperlimit();	// 库存上限
    double lowerlimit = product.getLowerlimit();	// 库存下限
    double weight = product.getWeight();			// 重量
    double netweight = product.getNetweight();		// 净重
    double tareweight = product.getTareweight();	// 皮重
    double volume = product.getVolume();			// 体积
    String pttypeid = product.getPttypeid();		// 物品类别ID
    String storageareaid = product.getStorageareaid();		// 存储库区ID
    String storagespaceid = product.getStoragespaceid();	// 存储货位ID
    String storagespacename = product.getStoragespacename();	// 存储货位
    String putawayid = product.getPutawayid();		// 上架规则ID
    String allocationid = product.getAllocationid();// 分配规则ID
    String replenishid = product.getReplenishid();	// 补货规则ID
	String lotid = product.getLotid();				// 批次ID
    String pakageid = product.getPakageid();		// 包装ID
    String customerid = product.getCustomerid();     // 客户ID
    String customername = product.getCustomername(); // 客户
    String producedate = product.getProducedate();	// 生产日期
    double validityterm = product.getValidityterm();// 有效期
    String validitytype = product.getValiditytype();// 有效期类型
    String productCode = product.getProductCode();			// 产品编码
    String remark2 = product.getRemark2();			// 备注
    String remark3 = product.getRemark3();			// 备注
    String remark5 = product.getRemark4();			// 备注
    String remark4 = product.getRemark5();			// 备注
    String remark6 = product.getRemark6();			// 备注
    String remark7 = product.getRemark7();			// 备注
    String isexcess = product.getIsexcess();		// 是否超量收货
	String isproductmixed = product.getIsproductmixed();  // 是否允许产品混放
	String isbatchmixed = product.getIsbatchmixed();// 是否允许批次混放
    String useflag = product.getUseflag();			// 可用标志
    String pkgdesc = product.getPkgdesc();			// 包装描述
    String lotdesc = product.getLotdesc();			// 批次描述
    String warehouseid = "";
    String producttype = product.getProducttype();
   // if(storageareaid!=null && !storageareaid.equals("")){
    //	warehouseid = storageareaid.substring(0,3);
   // }
    
%>
<html>
<head>
<title>修改物品信息</title>
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
<!--
	//保存物品信息
	function OnOk(){
		
		var productid = document.getElementById("productid").value;	// 产品id，
	    var productname = document.getElementById("productname").value;	// 产品标准名，
	    var spec = document.getElementById("spec").value;				// 产品规格，
	    var barcode = "";//document.getElementById("barcode").value;			// 产品批号，
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
	    //var allocationid = document.getElementById("allocationid").value;	// 分配规则ID
	    //var lotid = document.getElementById("lotid").value;				// 批次属性ID
	    //var pakageid = document.getElementById("package_id").value;			// 包装规则
	    var validityterm = document.getElementById("validityterm").value;	// 有效期
	    var validitytype = document.getElementById("validitytype").value;	// 有效期类型名
	    var productCode = document.getElementById("productCode").value;			// 备注
	    var remark2 = document.getElementById("remark2").value;			// 备注
	    var remark3 = document.getElementById("remark3").value;			// 备注
	    var remark4 = "";//document.getElementById("remark4").value;			// 备注
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
		
		//备注1
		if(productCode==null && productCode.length<1)
		{
			alert("【产品编码】不能为空！");
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
		
/* 		//备注4
		if(remark4!=null && remark4.length>0)
		{
			if(strlen(remark4) > 30){
				alert("【备注4】过长!");
				return;
			}
		} */
		if(producttype != null && producttype.length < 1)
		{
			alert("请选择产品类别");
			return;
		}
		var back_msg ="&id=" + productid +  "&productname=" + productname + "&spec=" + spec + "&barcode=" + barcode + "&pkspec=" + pkspec
			 + "&recunit=" + recunit + "&length=" + length + "&width=" + width + "&height="
			 + height+ "&upperlimit=" + upperlimit + "&lowerlimit=" + lowerlimit+ "&storageareaid=" 
			 + storageareaid + "&validityterm=" + validityterm + "&validitytype=" 
			 + validitytype + "&productCode=" + productCode + "&remark2=" + remark2 + "&remark3=" + remark3 + "&remark4=" + remark4 + "&producttype=" + producttype
			 + "&useflag=" + useflag;		 
		window.returnValue = back_msg;
 		window.close();
	}
	
	function OnLoad(){
		var typevalue = "yxqlx";		//有效期类型的值	
		selectType("<%=validitytype%>", "validitytype", typevalue);
		
		var typevalue = "punit";		//单位类型的值	
		selectType("<%=recunit%>", "recunit", typevalue);
		//selectType("<%=sendunit%>", "sendunit", typevalue);
		selectType("<%=storageareaid%>", "environment", "cchj");//存储环境
		selectType("<%=producttype%>", "producttype", "producttype");
		//DWREngine.setAsync(false);
		//selectObject("<%=warehouseid%>", "warehouseid", "1");
		//DWREngine.setAsync(true);
		
		//var warehouseid = document.getElementById("warehouseid").value;
		//selectAreaList("<%=storageareaid%>", "whAreaId", warehouseid, "1");
		
		//selectObject("<%=pttypeid%>", "pttypeid", "6");
		//selectLot("<%=lotid%>", "lotid");		//批次属性
		
		//selectRules("<%=putawayid%>", "putawayid", warehouseid, "1");	//上架规则
		//selectRules("<%=allocationid%>", "allocationid", warehouseid,  "2");	//分配规则
		//selectRules("<%=replenishid%>", "replenishid", warehouseid, "3");	//补货规则
	}
	
	//仓库变化时候
	function onWhChange(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");		//库区
		
		//selectRules("", "putawayid", warehouseid, "1");	//上架规则
		selectRules("", "allocationid", warehouseid,  "2");	//分配规则
		//selectRules("", "replenishid", warehouseid, "3");	//补货规则
	}

-->
</script>
</head>

<body onload="OnLoad();">

  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">当前位置：基本信息 -&gt; 物品管理 -&gt; 修改物品信息</div></td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
   <tr>
      <td width="100px" class="yx1" align="right"><span class="red">*</span>产品标准名：</td>
      <td class="yx"><input type="hidden" id="productid" value="<%=productid%>"><input type="text" id="productname" maxlength="100" size="20" value="<%=productname%>"></td>
      <td width="100px" class="yx1" align="right">产品商用名：</td>
      <td class="xx1"><input type="text" id="pkspec" maxlength="32" size="20" value="<%=pkspec%>"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>产品类别：</td>
      <td class="yx" ><select id="producttype" style="width:110px;">
      	<option value="">---请选择---</option>
      </select></td>
      <td width="100px" class="yx1" align="right">规格：</td>
      <td class="xx1"><input type="text" id="spec" maxlength="200" size="20" value="<%=spec%>"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">计量单位：</td>
      <td class="yx"><select id="recunit" style="width:110px;"><option value="">--请选择--</option></select></td>
      <td class="yx1" align="right">长：</td>
      <td class="xx1"><input type="text" id="length" maxlength="14" size="14" value="<%=length%>"></td>
    </tr>
    <tr>
	  
	  <td class="yx1" align="right"><span class="red">*</span>产品编码：</td>
      <td class="yx"><input type="text" id="productCode" maxlength="30" size="30" value="<%=productCode%>" disabled></td>
	   
      <td class="yx1" align="right">宽：</td>
      <td class="xx1"><input type="text" id="width" maxlength="14" size="14" value="<%=width%>"></td>
    </tr>
    <tr>
       <td class="yx1" align="right">存储环境：</td>
      <td class="yx"><select id="environment" style="width:110px;"><option value="">--请选择--</option></select></td>
      <td class="yx1" align="right">高：</td>
      <td class="xx1"><input type="text" id="height" maxlength="14" size="14" value="<%=height%>"></td>
    </tr>
    <tr>
	     
      <td class="yx1" align="right">托盘上限：</td>
      <td class="yx"><input type="text" id="upperlimit" maxlength="14" size="14" value="<%=upperlimit%>"></td>
       <td class="yx1" align="right">托盘下限：</td>
      <td class="xx1"><input type="text" id="lowerlimit" maxlength="14" size="14" value="<%=lowerlimit%>"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">有效期类型：</td>
      <td class="yx"><select id="validitytype" style="width:110px;"><option value="<%=validitytype%>">--请选择--</option></select></td>
      <td class="yx1" align="right">可用标志：</td>
      <td class="xx1"><input type="checkbox" id="useflag" <%if(useflag!=null && useflag.equals("Y")){%> checked <%}%>></td>
      
    </tr>
    <tr>
     
      
      <td class="yx1" align="right">有效期：</td>
      <td class="yx"><input type="text" id="validityterm" maxlength="14" size="14" value="<%=validityterm%>"></td>
      <td class="yx1" align="right">备注2：</td>
      <td class="xx1"><input type="text" id="remark2" maxlength="30" size="30" value="<%=remark2%>"></td>
    </tr>
    
    <tr>
      
      <td class="yx1" align="right">备注3：</td>
      <td class="yx" ><input type="text" id="remark3" maxlength="30" size="30" value="<%=remark3%>"></td>
       <td class="xx1" colspan="2"></td>
      
    </tr>
    
<%--     <tr>
      
      <td width="100px" class="yx1" align="right">产品条码：</td>
      <td class="yx"><input type="text" id="barcode" maxlength="40" size="20" value="<%=barcode%>"></td> 
      <td class="xx1" colspan="2"></td>
      
    </tr> --%>
    
    
    <tr>
      <td class="TD_MGR_TOP" align="center" colspan="4">
        <input type="button" onclick="OnOk()" name="add" value="&nbsp;&nbsp;&nbsp;保存" class="button_add">&nbsp; 
        <input type="button" onClick="window.close()" name="resetBtn" value="关闭" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
 
</body>
</html>