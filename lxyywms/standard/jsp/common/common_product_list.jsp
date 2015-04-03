<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseProduct"%>
<%@ page import="java.util.List"%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
	//设置单选框是否选中
	function setSel(i){

		var check_ids = document.getElementsByName("check_id");
		check_ids[i].checked = true;
		
		//改变背景色
		for(var i=0; i<check_ids.length; i++){
			
			if(check_ids[i].checked){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#99CCFF";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
-->
</script>
</head>

<body >
  <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr><td valign="top" height="100%">
	
  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr>
      <td class="TD_LIST_TITLE1" nowrap><div class="list_title">行号</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">品名</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">规格</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">产品编码</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">包装规格</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">单位</div></td>
      <!--<td class="TD_LIST_TITLE" nowrap><div class="list_title">发货单位</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">物品类别</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">长</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">宽</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">高</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">库存上限</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">库存下限</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">重量</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">净重</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">皮重</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">体积</div></td>-->
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">存储库区</div></td>
      <!--<td class="TD_LIST_TITLE" nowrap><div class="list_title">包装描述</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">存储规则</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">分配规则</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">预配规则</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">补货规则</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">周转规则</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">生产日期</div></td>-->
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">有效期</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">有效期类型</div></td>
      <!--<td class="TD_LIST_TITLE" nowrap><div class="list_title">备注</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">备注2</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">备注3</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">备注4</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">备注5</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">备注6</div></td>
      <td class="TD_LIST_TITLE" nowrap><div class="list_title">备注7</div></td>
      <td class="TD_LIST_TITLE2" nowrap><div class="list_title">是否启用</div></td>-->
    </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0){
	
		BaseProduct product = null; 
		
		String productid;			// 物品ID
		String productCode;			//产品编码
	    String productname;			// 物品名
	    String spec;				// 规格
	    String barcode;				// 物品条码
	    String pkspec;				// 包装规格
	    String recunit;				// 收货单位
	    String sendunit;			// 发货单位
	    String pttypename;			// 物品类别
	    double length;				// 长
	    double width;				// 宽
	    double height;				// 高
	    double upperlimit;			// 库存上限
	    double lowerlimit;			// 库存下限
	    double weight;				// 重量
	    double netweight;			// 净重
	    double tareweight;			// 皮重
	    double volume;				// 体积
	    String storageareaname;		// 存储库区
        String pkgdesc;				// 包装描述
	    String putawayid;		// 存储规则ID
	    String allocationid;		// 分配规则ID
	    String preallocationid;		// 预配规则ID
	    String replenishid;	// 补货规则ID
	    String turnoverruleid;		// 周转规则ID
	    String producedate;			// 生产日期
	    double validityterm;		// 有效期
	    String validitytypename;	// 有效期类型名
	    String remark1;				// 备注
	    String remark2;				// 备注
	    String remark3;				// 备注
	    String remark5;				// 备注
	    String remark4;				// 备注
	    String remark6;				// 备注
	    String remark7;				// 备注
	    String useflag;				// 可用标志
	    
	    String pakageid;		    // 包装ID 
        String lotid;               // 批次ID
        
        String lotdesc;				// 批次属性描述
     	String pakagedesc;			// 包装描述
	    
	    String strId = "";
		
		for(int i=0; i<ls.size(); i++){
			product = (BaseProduct)ls.get(i); 
                        
			productid = product.getProductid();			// 物品ID
			productCode=product.getProductCode();		//产品编码
		    productname = product.getProductname();		// 物品名
		    spec = product.getSpec();					// 规格
		    barcode = product.getBarcode();				// 物品条码
		    pkspec = product.getPkspec();				// 包装规格

		    recunit = product.getRecunit();				// 缺省收货单位
		    sendunit = product.getSendunit();			// 缺省发货单位
		    pttypename = product.getPttypename();		// 物品类别
		    length = product.getLength();				// 长
		    width = product.getWidth();					// 宽
		    height = product.getHeight();				// 高
		    upperlimit = product.getUpperlimit();		// 库存上限
		    lowerlimit = product.getLowerlimit();		// 库存下限
		    weight = product.getWeight();				// 重量
		    netweight = product.getNetweight();			// 净重
		    tareweight = product.getTareweight();		// 皮重
		    volume = product.getVolume();				// 体积
		    storageareaname = product.getStorageareaname();		// 存储库区
		    pkgdesc = product.getPkgdesc();						// 包装描述
		    putawayid = product.getPutawayid();			// 存储规则ID
		    allocationid = product.getAllocationid();	// 分配规则ID
		    replenishid = product.getReplenishid();		// 补货规则ID
		    producedate = product.getProducedate();				// 生产日期
		    validityterm = product.getValidityterm();			// 有效期
		    validitytypename = product.getValiditytypename();	// 有效期类型
		    remark1 = product.getRemark1();				// 备注
		    remark2 = product.getRemark2();				// 备注
		    remark3 = product.getRemark3();				// 备注
		    remark5 = product.getRemark4();				// 备注
		    remark4 = product.getRemark5();				// 备注
		    remark6 = product.getRemark6();				// 备注
		    remark7 = product.getRemark7();				// 备注
		    useflag = product.getUseflag();				// 可用标志
		    
		    pakageid = product.getPakageid();		    // 包装ID 
            lotid = product.getLotid();                 // 批次ID
            
            lotdesc = product.getLotdesc();				// 批次属性描述
     	 	pakagedesc = product.getPkgdesc();			// 包装描述
			
			strId = productid + "|"+ productname + "|" + (pakageid == null? " " : pakageid) + "|" + (pakagedesc == null ? " " : pakagedesc) + "|" + (lotid == null ? " " : lotid) + "|" + (lotdesc == null ? " " : lotdesc) + "|" + recunit + "|"+ (productCode == null ? " " : productCode) + "|"+(spec == null ? " " : spec) + "|";
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''" onclick="setSel(<%=i%>)" ondblclick="parent.ondbClickDo('<%=strId%>');">
     <td class="TD_LIST1" align="center"><input type="radio" name="check_id" value="<%=strId%>" class="input_checkbox"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=productname == null ? "" : productname%></td>
     <td class="TD_LIST" align="center"><%=spec == null ? "" : spec%></td>
     <td class="TD_LIST" align="center"><%=productCode == null ? "" : productCode%></td>
     <td class="TD_LIST" align="center"><%=pkspec == null ? "" : pkspec%></td>
  
     <td class="TD_LIST" align="center"><%=recunit == null ? "" : recunit%></td>
     <!--<td class="TD_LIST" align="center"><%=sendunit == null ? "" : sendunit%></td>
     <td class="TD_LIST" align="center"><%=pttypename == null ? "" : pttypename%></td>
     <td class="TD_LIST" align="center"><%=length%></td>
     <td class="TD_LIST" align="center"><%=width%></td>
     <td class="TD_LIST" align="center"><%=height%></td>
     <td class="TD_LIST" align="center"><%=upperlimit%></td>
     <td class="TD_LIST" align="center"><%=lowerlimit%></td>
     <td class="TD_LIST" align="center"><%=weight%></td>
     <td class="TD_LIST" align="center"><%=netweight%></td>
     <td class="TD_LIST" align="center"><%=tareweight%></td>
     <td class="TD_LIST" align="center"><%=volume%></td>-->
     <td class="TD_LIST" align="center"><%=storageareaname == null ? "" : storageareaname%></td>
     <!--<td class="TD_LIST" align="center"><%=pkgdesc == null ? "" : pkgdesc%></td>
     <td class="TD_LIST" align="center"><%=putawayid == null ? "" : putawayid%></td>
     <td class="TD_LIST" align="center"><%=allocationid == null ? "" : allocationid%></td>
     <td class="TD_LIST" align="center"><%=replenishid == null ? "" : replenishid%></td>
     <td class="TD_LIST" align="center"><%=producedate == null ? "" : producedate%></td>-->
     <td class="TD_LIST" align="center"><%=validityterm%></td>
     <td class="TD_LIST" align="center"><%=validitytypename == null ? "" : validitytypename%></td>
     <!--<td class="TD_LIST" align="center"><%=remark1 == null ? "" : remark1%></td>
     <td class="TD_LIST" align="center"><%=remark2 == null ? "" : remark2%></td>
     <td class="TD_LIST" align="center"><%=remark3 == null ? "" : remark3%></td>
     <td class="TD_LIST" align="center"><%=remark4 == null ? "" : remark4%></td>
     <td class="TD_LIST" align="center"><%=remark5 == null ? "" : remark5%></td>
     <td class="TD_LIST" align="center"><%=remark6 == null ? "" : remark6%></td>
     <td class="TD_LIST" align="center"><%=remark7 == null ? "" : remark7%></td>
     <td class="TD_LIST" align="center"><%=useflag == null ? "" : useflag%></td>-->
   </tr>
<%
      	}
	}
	else{
		session.removeAttribute("commpaging");
	}
%>
   <tr>
     <td height="100%" colspan="18" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
   </table>
 <!-- ======== 分页标签 ======== -->
 </td><tr>
 <tr><td>
   <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr><td height="25px">
	  <%@ include file="commpage.jsp"%>
    </td><tr>
  </table>
</body>
</html>