<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage" %>
<%@ page import="java.text.DecimalFormat" %>
<%
	//����С��2λ
	//NumberFormat nbf=NumberFormat.getNumberInstance();     
	//nbf.setMinimumFractionDigits(2);
	//nbf.setMaximumFractionDigits(2); 
	
	DecimalFormat nbf =new DecimalFormat("#0.00"); 
	
	InventoryStorage storage = null;
	if(request.getAttribute("storage") != null){
		storage = (InventoryStorage)request.getAttribute("storage");
	}
	
	String strStorageid = null;		// ���id
	String strTrayCode = null;		// ����
	String strProductName = null;	// Ʒ��
	
	double fPNum =0;					// ����
	double fGrossWeight = 0;		// ë��
	double fNetWeight = 0;			// ����
	
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
<title>�Զ�������ֿ���Ϣ����ϵͳ</title>
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

	//ȷ��
	function Ok(detailid){
		var num = document.getElementById("num").value;				//����
		var weight = document.getElementById("fGrossWeight").value;	//ë��
		var netweight = document.getElementById("fNetWeight").value;//����
		var backmsg = "&num=" + num + "&weight=" + weight + "&netweight=" + netweight + "&volume=0";
		window.returnValue = backmsg;
		window.close();
	}
	function OnLoad(lotid,lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12)
	{
		var tableObj = document.getElementById("tab1");
		// 1��ÿ����ʾ��td��
		createLotDetail1(tableObj, lotid, 1, lotatt1, lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12);
	}	
-->
</script>
</head>

<body>

 <table width="98%" height="27" align="center" border="0" cellpadding="0" cellspacing="0">
   <tr>
     <td class="font_006699_bold_12">��ǰ���������ݵ���</td>
   </tr>
 </table>
     
 <table id="tab1" width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr> 
     <td class="TD_ADD"><div align="right">���̣�</div></td>
     <td class="TD_ADD"><input type="text" name="traycode" value="<%=strTrayCode == null ? "" : strTrayCode%>" style="width:150px;background-color: #EDDFFD;" readonly></td>
   </tr>
   <tr>
     <td class="TD_ADD"><div align="right">Ʒ����</div></td>
     <td class="TD_ADD"><input type="text" name="packaging_name" value="<%=strProductName == null ? "" : strProductName%>" style="background-color: #EDDFFD;width:150px;" readonly></td>
   </tr>
   </table>
   <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td class="yx1" width="100"><div align="right">������</div></td>
     <td class="xx1">
     	<input type="text" name="num" size="24" value="<%=nbf.format(fPNum)%>" style="width:100px;" >
     </td>
   </tr>
   <tr>
     <td class="yx1"><div align="right">ë�أ�</div></td>
     <td class="xx1"><input type="text" name="fGrossWeight" size="24" value="<%=nbf.format(fGrossWeight)%>" style="width:100px;" ></td>
   </tr>
   <tr>
     <td class="yx1"><div align="right">���أ�</div></td>
     <td class="xx1"><input type="text" name="fNetWeight" size="24" value="<%=nbf.format(fNetWeight)%>" style="width:100px;" ></td>
   </tr>
   <tr>
     <td  align="center" colspan="2">
       <input type="button" name="save" class="button" onClick="Ok('<%=strStorageid%>')" value="ȷ��">
       <input type="button" name="cancel" class="button" onClick="window.close()" value="ȡ��">
     </td>
   </tr>
 </table>

</body>
</html>