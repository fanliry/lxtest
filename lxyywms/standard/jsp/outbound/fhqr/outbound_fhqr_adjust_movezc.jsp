<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage" %>
<%@ page import="java.text.DecimalFormat" %>
<%
	//保留小数2位
	//NumberFormat nbf=NumberFormat.getNumberInstance();     
	//nbf.setMinimumFractionDigits(2);
	//nbf.setMaximumFractionDigits(2); 
	
	DecimalFormat nbf =new DecimalFormat("#0.00"); 
	
	InventoryStorage storage = null;
	if(request.getAttribute("storage") != null){
		storage = (InventoryStorage)request.getAttribute("storage");
	}
	
	String strStorageid = null;		// 库存id
	String strTrayCode = null;		// 托盘
	String strProductName = null;	// 品名
	
	double fPNum =0;					// 数量
	double fGrossWeight = 0;		// 毛重
	double fNetWeight = 0;			// 净重
	
	if(storage != null){
		strStorageid = storage.getInventoryid();
		strTrayCode = storage.getTraycode();
		strProductName = storage.getProductName();
		fPNum = storage.getPnum();
		fGrossWeight = storage.getPweight();
		fNetWeight = storage.getPnetweight();
	}
%>
<html>
<head>
<title>自动化立体仓库信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>

<script type="text/javascript">
<!--

	//确定
	function Ok(detailid){
		var num = document.getElementById("num").value;				//数量
		var weight = document.getElementById("fGrossWeight").value;	//毛重
		var netweight = document.getElementById("fNetWeight").value;//净重
		var backmsg = "&num=" + num + "&weight=" + weight + "&netweight=" + netweight + "&volume=0";
		window.returnValue = backmsg;
		window.close();
	}
	function OnLoad(lotid,lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12)
	{
		var tableObj = document.getElementById("tab1");
		// 1是每行显示的td数
		createLotDetail1(tableObj, lotid, 1, lotatt1, lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12);
	}	
-->
</script>
</head>

<body>

 <table width="98%" height="27" align="center" border="0" cellpadding="0" cellspacing="0">
   <tr>
     <td class="font_006699_bold_12">当前操作：数据调整</td>
   </tr>
 </table>
     
 <table id="tab1" width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr> 
     <td class="TD_ADD"><div align="right">托盘：</div></td>
     <td class="TD_ADD"><input type="text" name="traycode" value="<%=strTrayCode == null ? "" : strTrayCode%>" style="width:150px;background-color: #EDDFFD;" readonly></td>
   </tr>
   <tr>
     <td class="TD_ADD"><div align="right">品名：</div></td>
     <td class="TD_ADD"><input type="text" name="packaging_name" value="<%=strProductName == null ? "" : strProductName%>" style="background-color: #EDDFFD;width:150px;" readonly></td>
   </tr>
   </table>
   <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td class="yx1" width="100"><div align="right">数量：</div></td>
     <td class="xx1">
     	<input type="text" name="num" size="24" value="<%=nbf.format(fPNum)%>" style="width:100px;" >
     </td>
   </tr>
   <tr>
     <td class="yx1"><div align="right">毛重：</div></td>
     <td class="xx1"><input type="text" name="fGrossWeight" size="24" value="<%=nbf.format(fGrossWeight)%>" style="width:100px;" ></td>
   </tr>
   <tr>
     <td class="yx1"><div align="right">净重：</div></td>
     <td class="xx1"><input type="text" name="fNetWeight" size="24" value="<%=nbf.format(fNetWeight)%>" style="width:100px;" ></td>
   </tr>
   <tr>
     <td  align="center" colspan="2">
       <input type="button" name="save" class="button" onClick="Ok('<%=strStorageid%>')" value="确定">
       <input type="button" name="cancel" class="button" onClick="window.close()" value="取消">
     </td>
   </tr>
 </table>

</body>
</html>