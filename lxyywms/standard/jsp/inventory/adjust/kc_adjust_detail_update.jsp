<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryAdjustDetail"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseProduct" %>

<html>
<head>
<title>����</title>
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
		String whAreaName = ""; //����
		String cargoSpace = "";//��λ
		String product = "";//��Ʒ��
		String printdate = "";    //��������
		String unit = "";//��λ
		String lotid = "";//��������
		String lotinfo = "";	//������Ϣ
		String traycode = "";	//��������
		double pnum = 0;	//ϵͳ����
		double tonum	= 0;	//Ŀ������
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
	//�޸���ϸ
	function saveDetailData(id)
	{
		 var actionStr = "BMSService?actionCode=inventoryAdjustAction&method=ricoExecUpdateDetail"
				 + "&detailId="+id
				 + "&tonum=" + document.getElementById("tonum").value;	
		  window.returnValue=actionStr;
		  window.close(); 		
	}
	//��ϸ���ݼ���
	function checkDetailData()
	{
		var tonum = Trim(document.getElementById("tonum").value);
		
		if(tonum == null || tonum.length <1)
		{
			alert("��Ŀ������������Ϊ��!");
			return false;
		}
	}
	function sumCubic()
	{
	    var strSkuId = document.getElementById("skuId").value;
		var toqty = document.getElementById("toqty").value;
		if(toqty == "" || toqty.length <1)  
       	{
       		alert("��Ŀ������������Ϊ��!");
       		return false; 
       	}
		if(toqty!=null && toqty.length>0 && !isFloat(toqty,"+","0"))
		{
			alert("��Ŀ��������������������������!");
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
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã������� -&gt; ������ -&gt; ���µ�������ϸ</div></td>
   </tr>
 </table>

 <!-- ======== ��ϸ��Ϣ��ʼ ======== -->
 <form id="myform2" name="myform2" method="post">
 <input type="hidden" id="MMId" name="MMId" value="<%=strId%>">
 <input type="hidden" id="inventoryId" name="inventoryId" value="">
 <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
   <tr>
     <td width="15%" class="yx1"><div align="right">��״̬��</div></td>
     <td width="18%" class="yx"><div align="left">
         <select name="status"  class="input_readonly"  style="width:130px;">
	     		<option value="0" selected="selected">����</option>
	   	</select></div>
     </td>
   <td width="15%" class="yx1"><input type="hidden" name="strId" value="<%=strId%>"><div align="right">������</div></td>
     <td width="18%" class="yx"><div align="left"><input type="text" name="whAreaName" value="<%=whAreaName%>"readonly="readonly" class="input_readonly"></div></td>
     <td width="15%" class="yx1"><div align="right"><font color="red">*&nbsp;</font>��λ��</div></td>
     <td width="19%" class="xx1"><div align="left">
	        <input type="text" name="cargoSpace"value="<%=cargoSpace%> " readonly="readonly" class="input_readonly">
     </div></td>
   </tr>  
   <tr>
    <td class="yx1"><div align="right">��Ʒ��</div></td>
    <td class="yx"><div align="left">
     	<input type="text" name="product" readonly="readonly" value="<%=product%>"class="input_readonly">
      </div></td>
    <td class="yx1"><div align="right">�������ڣ�</div></td>
    <td class="yx"><div align="left">
     	<input type="text" name="printdate" readonly="readonly"value="<%=printdate%>" class="input_readonly">
      </div></td>
     <td class="yx1"><div align="right">��λ��</div></td>
     <td class="xx1"><div align="left"><input type="text" name="unit" value="<%=unit%>" readonly="readonly" class="input_readonly"></div></td>
   </tr>
     <tr>
     <td class="yx1"><div align="right">�������ͣ�</div></td>
     <td class="yx"><div align="left"><select name='lotid' style='width:110px;' readonly="readonly" class="input_readonly"><option value=''>--��ѡ��--</option></select></div></td>
     <td class="yx1"><div align="right">������Ϣ��</div></td>
     <td class="yx"><div align="left"><input type="text" name="lotinfo" value="<%=lotinfo%>"readonly="readonly" class="input_readonly"></div></td>
     <td class="yx1"><div align="right">�������룺</div></td>
     <td class="xx1"><div align="left">
     	<input type="text" name="traycode" value="<%=traycode%>" readonly="readonly" class="input_readonly">
      </div></td>
     </tr>
     <tr>
     <td class="yx1"><div align="right">���������</div></td>
     <td class="xx1"><div align="left">
     	<input type="text" name="pnum" value="<%=pnum%>" readonly="readonly" class="input_readonly">
      </div></td>
     <td class="yx1"><div align="right">Ŀ��������</div></td>
     <td class="xx1" colspan="3"><div align="left">
     <input type="text" name="tonum" value="<%=tonum%>"size="17"></div></td><!-- onblur="sumCubic()" -->
     </tr>
   <tr>
      <td height="27" colspan="6" align="center" >
             <input name="add" type="button" class="button_add" id="add" value="&nbsp;&nbsp;����" onclick="if(checkDetailData()!=false){saveDetailData('<%=strId%>');}"/>
             <input name="button2" type="button" class="BUTTON_STYLE1" id="button2" value="�ر�" onClick="window.close();" />
     </td>
   </tr>
 </table>
 </form>
 <!-- ======== ��ϸ��Ϣ���� ======== -->
 <table width="100%" height="10"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>
</body>
</html>