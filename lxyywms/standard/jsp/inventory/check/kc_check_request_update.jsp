<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckRequest" %>
<%
	InventoryCheckRequest req = (InventoryCheckRequest)request.getAttribute("checkreq");  
	String requestid = req.getRequestid();		//ID
	String type = req.getCounttype();			//����
	String lotnumber = req.getLotinfo();		//����
	String warehouseid = req.getWarehouseid();	//�ֿ�
	String cargospaceid = req.getCargo_space_id();	//��λ
	String cargospacename = req.getCargoSpaceName();//��λ
	String whareaid = req.getWh_area_id();		//����
	String productid = req.getProductid();		//��Ʒ
	String productname = req.getProductName();	//��Ʒ
	String traycode = req.getTraycode();		//��������
%>
<html>
<head>
<title>�޸��̵�����</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>
<script type="text/javascript">
<!--
	//����
	function saveData(){
	
		var type =  document.getElementById("type").value;					//����
		var wh_area_id = document.getElementById("wh_area_id").value;		//����
	    var cargo_space_id = document.getElementById("cargospace_id").value;//��λ
	    var lotnumber = document.getElementById("lotnumber").value;		    //����
	    var productid = document.getElementById("package_id").value;		//Ʒ��
	    var traycode = document.getElementById("traycode").value;			//��������
	    var requestid = document.getElementById("requestid").value;			//���뵥id
		var condition = "&type=" + type + "&wh_area_id=" + wh_area_id
		    + "&cargo_space_id=" + cargo_space_id
			+ "&lotnumber=" + lotnumber + "&productid=" + productid 
			+ "&traycode=" + traycode + "&requestid=" + requestid;
	    window.returnValue = condition;
		window.close();
	}
	
	//�����ı��ʱ��
	function OnWhareaChange(){
		
		//��ջ�λ
		document.getElementById('cargospace_id').value = "";
		document.getElementById('cargospace_name').value = "";
	}
	
	//ҳ���½
	function OnLoad(){
	
		DWREngine.setAsync(false);
		selectType('<%=type%>', 'type', 'pdlx');			//����
		selectAreaList("<%=whareaid%>", "wh_area_id", "<%=warehouseid%>", "1");	//����
		DWREngine.setAsync(true);
	}

-->
</script>
</head>

<body onLoad="OnLoad()">
<form id="myForm" method="post" action="">

  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
     <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã������� -&gt; ����̵� -&gt; �޸��̵�����</div></td>
   </tr>
 </table>
 
 <table><tr><td height="10"></td></tr></table> 
 
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table1">
   <tr>
     <td width="100px" class="yx1" align="right">���ͣ�<input type="hidden" id="requestid" size="15" value="<%=requestid%>"></td>
     <td class="yx"><select id="type" style="width:115px;"><option value=""></option></select></td>
	 <td class="yx1" align="right">���ţ�</td>
	 <td class="xx1"><input type="text" id="lotnumber" size="15" value="<%=lotnumber%>" style="ime-mode:disabled"></td>
   <tr>
   	 <td class="yx1" align="right">������</td>
     <td class="yx"><select id="wh_area_id" style="width:115px;" onchange="OnWhareaChange()"><option></option></select></td>
     <td class="yx1" align="right">��λ��</td>
     <td class="xx1">
     	<input type="hidden" id="cargospace_id" value="<%=cargospaceid==null?"":cargospaceid%>">
     	<input type="text" id="cargospace_name" size="15" readonly value="<%=cargospacename==null?"":cargospacename%>">
       	<input onClick="openCargospace('<%=request.getContextPath()%>/standard/jsp/common/common_cargospace.jsp?warehouseid=<%=warehouseid%>&whAreaId='+document.getElementById('wh_area_id').value,'850','550');" 
       		type="button" value="��" class="button_select">
     </td>
   </tr>
   <tr>
   	 <td class="y1" align="right">�������룺</td>
	 <td class="x"><input type="text" id="traycode" size="15" maxlength="50" value="<%=traycode%>" style="ime-mode:disabled"></td>
	 <td class="y1" align="right">Ʒ����</td>
	 <td>
	   <input type="hidden" id="package_id" value="<%=productid==null?"":productid%>">
	   <input type="text" id="package_name" size="15" readonly value="<%=productname==null?"":productname%>">
       <input onClick="openProduct('<%=request.getContextPath()%>/standard/jsp/common/common_product.jsp','800','520');"  type="button" value="��" class="button_select">
	 </td>
   </tr>
 </table>
 
 <table><tr><td height="10"></td></tr></table>  
 <table><tr><td height="10"></td></tr></table>
 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">  
   <tr>
     <td height="27" align="center">
        <input type="button" onclick="saveData()" id="add" value="&nbsp;&nbsp;&nbsp;����" class="button_add">&nbsp; 
        <input type="reset" id="resetBtn" value="&nbsp;&nbsp;&nbsp;����" class="button_reset">&nbsp;
        <input type="button" onClick="window.close()" id="resetBtn" value="�ر�" class="BUTTON_STYLE1">
     </td>
   </tr>
 </table>
 </form>

</body>
</html>