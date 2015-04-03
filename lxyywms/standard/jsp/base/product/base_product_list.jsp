<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseProduct" %>
<html>
<head>
<title>物品信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--

	//全选
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;	
					
			//改变背景色
			if(check_ids[i].checked){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	
	//设置多选框是否选中
	function setSel(i){

		var check_ids = document.getElementsByName("check_id");
		if(check_ids[i].checked){
			check_ids[i].checked = false;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "";
		}else{
			check_ids[i].checked = true;
			check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
		}
		
	}
	
	//修改双击行
	function upd(i){

		var check_ids = document.getElementsByName("check_id");
		var id = check_ids[i].value;
		var ac = "<%=request.getContextPath()%>/BMSService?actionCode=";

		var result = showModalDialog(ac + "baseProductAction&flag=2&id="+id, "", "dialogWidth=750px; dialogHeight=350px; scroll=no");
		if(result != null && result.length > 0){
			var linerow = parent.page.document.getElementById("lineviewrow").value;	//每页显示行数	
			location.href = ac + "baseProductAction&method=ricoExecEdit" + result + "&linerow=" + linerow;
		}
	}
	
	function OnLoad1(){
		parent.page.location.reload();
		var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>";
		if(back_msg != ""){
			alert(back_msg);
		}
	}
-->
</script>
</head>

<body onLoad="OnLoad1()">
  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr>
      <td class="TD_LIST_TITLE1" nowrap width="50px"><div class="list_title">
     	<input type="checkbox" name="check_all" onclick="CheckAll();" class="input_checkbox" value="checkbox">行号</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">产品编码</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">产品标准名</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">产品规格</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">产品条码</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">单位</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">物品类别</div></td>

      <td class="TD_LIST_TITLE" nowrap><div class="list_title">存储库区</div></td>
      <td class="TD_LIST_TITLE" nowrap width="50px"><div class="list_title">有效期</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">有效期类型</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">备注2</div></td>
      <td class="TD_LIST_TITLE2" nowrap><div class="list_title">是否启用</div></td>
    </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null){
		BaseProduct product = null; 
		
		String productid;		//产品ID
		String productCode;			// 产品编码
	    String productname;			// 产品标准码
	    String spec;				// 产品规格
	    String barcode;				// 物品条码
	    String pkspec;				// 产品商用名
	    String recunit;				// 计量单位
	    //String sendunit;			// 缺省发货单位
	    String pttypename;			// 物品类别
	    double length;				// 长
	    double width;				// 宽
	    double height;				// 高
	    double upperlimit;			// 库存上限
	    double lowerlimit;			// 库存下限
	    //double weight;				// 重量
	    //double netweight;			// 净重
	    //double tareweight;			// 皮重
	    //double volume;				// 体积
	    String storageareaname;		// 存储库区
	    String storagespacename;	// 存储货位
	   // String putaway;				// 上架规则
	    String allocation;			// 分配规则
	   // String replenish;			// 补货规则
	    String lotdesc;				// 批次属性描述
        String pkgdesc;				// 包装描述
       // String customername;		// 客户名称
	   // String producedate;			// 生产日期
	    double validityterm;		// 有效期
	    String validitytypename;	// 有效期类型名
	    //String remark1;				// 备注
	    String remark2;				// 备注
	    String remark3;				// 备注
	  //  String remark5;				// 备注
	    String remark4;				// 备注
	    //String remark6;				// 备注
	   // String remark7;				// 备注
	   // String isexcess;            // 是否超量收货
		//String isproductmixed;      // 是否允许产品混放
		//String isbatchmixed;        // 是否允许批次混放
	    String useflag;				// 可用标志
	    String producttype;
	    String producttypename="";

		for(int i=0; i<ls.size(); i++){
			product = (BaseProduct)ls.get(i); 
            
			productid = product.getProductid();	//	产品D
			productCode = product.getProductCode();			// 物品编码
		    productname = product.getProductname();		// 物品名
		    spec = product.getSpec();					// 规格
		    barcode = product.getBarcode();				// 物品条码
		    pkspec = product.getPkspec();				// 包装规格
		    recunit = product.getRecunit();				// 缺省收货单位
		  //  sendunit = product.getSendunit();			// 缺省发货单位
		    //pttypename = product.getPttypename();		// 物品类别
		    length = product.getLength();				// 长
		    width = product.getWidth();					// 宽
		    height = product.getHeight();				// 高
		    upperlimit = product.getUpperlimit();		// 库存上限
		    lowerlimit = product.getLowerlimit();		// 库存下限
		    //weight = product.getWeight();				// 重量
		    //netweight = product.getNetweight();			// 净重
		    //tareweight = product.getTareweight();		// 皮重
		    //volume = product.getVolume();				// 体积
		    storageareaname = product.getStorageareaname();		// 存储库区
		    storagespacename = product.getStoragespacename();	// 存储货位
		   //putaway = product.getPutawayname();			// 上架规则
		    allocation = product.getAllocationname();	// 分配规则
		    //replenish = product.getReplenishname();		// 补货规则
		    lotdesc = product.getLotdesc();						// 批次属性描述
		    pkgdesc = product.getPkgdesc();						// 包装描述
		    //customername = product.getCustomername();			// 客户名称
		    //producedate = product.getProducedate();				// 生产日期
		    validityterm = product.getValidityterm();			// 有效期
		    validitytypename = product.getValiditytypename();	// 有效期类型
		    //remark1 = product.getRemark1();				// 备注
		    remark2 = product.getRemark2();				// 备注
		    remark3 = product.getRemark3();				// 备注
		   // remark5 = product.getRemark4();				// 备注
		    remark4 = product.getRemark5();				// 备注
		    //remark6 = product.getRemark6();				// 备注
		    //remark7 = product.getRemark7();				// 备注
		    //isexcess = product.getIsexcess();			// 是否超量收货
			//isproductmixed = product.getIsproductmixed();  // 是否允许产品混放
			//isbatchmixed = product.getIsbatchmixed();   // 是否允许批次混放
		    useflag = product.getUseflag();				// 可用标志
		    producttype = product.getProducttype();
		    producttypename = product.getProducttypename();

%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="upd(<%=i%>)">
     <td class="TD_LIST" align="center">
     	<input type="checkbox" name="check_id" class="input_checkbox" value="<%=productid%>" onclick="setSel(<%=i%>)"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=productCode == null ? "" : productCode%></td>
     <td class="TD_LIST" align="center"><%=productname == null ? "" : productname%></td>
     <td class="TD_LIST" align="center"><%=spec == null ? "" : spec%></td>
     <td class="TD_LIST" align="center"><%=barcode == null ? "" : barcode%></td>
     <td class="TD_LIST" align="center"><%=recunit == null ? "" : recunit%></td>
     <td class="TD_LIST" align="center"><%=producttypename == null ? "" : producttypename%></td>
     
     <td class="TD_LIST" align="center"><%=storageareaname == null ? "" : storageareaname%></td>
     <td class="TD_LIST" align="center"><%=validityterm%></td>
     <td class="TD_LIST" align="center"><%=validitytypename == null ? "" : validitytypename%></td>
     <td class="TD_LIST" align="center"><%=remark2 == null ? "" : remark2%></td>
     <td class="TD_LIST2" align="center"><%=useflag == null ? "" : useflag.equals("Y")?"是":"否"%></td>
   </tr>
<%
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="39" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
   
 </table>
</body>
</html>