<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseProduct" %>
<%
	BaseProduct product = (BaseProduct)request.getAttribute("product");  
	String productid = product.getProductid();		// ��Ʒid
	//String productCode = product.getProductCode();	//��Ʒ����
    String productname = product.getProductname();	// ��Ʒ��
    String spec = product.getSpec();				// ���
    String barcode = product.getBarcode();			// ��Ʒ����
    String pkspec = product.getPkspec();			// ��װ���
    String recunit = product.getRecunit();			// �ջ���λ
    String sendunit = product.getSendunit();		// ������λ
    double length = product.getLength();			// ��
    double width = product.getWidth();				// ��
    double height = product.getHeight();			// ��
    double upperlimit = product.getUpperlimit();	// �������
    double lowerlimit = product.getLowerlimit();	// �������
    double weight = product.getWeight();			// ����
    double netweight = product.getNetweight();		// ����
    double tareweight = product.getTareweight();	// Ƥ��
    double volume = product.getVolume();			// ���
    String pttypeid = product.getPttypeid();		// ��Ʒ���ID
    String storageareaid = product.getStorageareaid();		// �洢����ID
    String storagespaceid = product.getStoragespaceid();	// �洢��λID
    String storagespacename = product.getStoragespacename();	// �洢��λ
    String putawayid = product.getPutawayid();		// �ϼܹ���ID
    String allocationid = product.getAllocationid();// �������ID
    String replenishid = product.getReplenishid();	// ��������ID
	String lotid = product.getLotid();				// ����ID
    String pakageid = product.getPakageid();		// ��װID
    String customerid = product.getCustomerid();     // �ͻ�ID
    String customername = product.getCustomername(); // �ͻ�
    String producedate = product.getProducedate();	// ��������
    double validityterm = product.getValidityterm();// ��Ч��
    String validitytype = product.getValiditytype();// ��Ч������
    String productCode = product.getProductCode();			// ��Ʒ����
    String remark2 = product.getRemark2();			// ��ע
    String remark3 = product.getRemark3();			// ��ע
    String remark5 = product.getRemark4();			// ��ע
    String remark4 = product.getRemark5();			// ��ע
    String remark6 = product.getRemark6();			// ��ע
    String remark7 = product.getRemark7();			// ��ע
    String isexcess = product.getIsexcess();		// �Ƿ����ջ�
	String isproductmixed = product.getIsproductmixed();  // �Ƿ������Ʒ���
	String isbatchmixed = product.getIsbatchmixed();// �Ƿ��������λ��
    String useflag = product.getUseflag();			// ���ñ�־
    String pkgdesc = product.getPkgdesc();			// ��װ����
    String lotdesc = product.getLotdesc();			// ��������
    String warehouseid = "";
    String producttype = product.getProducttype();
   // if(storageareaid!=null && !storageareaid.equals("")){
    //	warehouseid = storageareaid.substring(0,3);
   // }
    
%>
<html>
<head>
<title>�޸���Ʒ��Ϣ</title>
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
	//������Ʒ��Ϣ
	function OnOk(){
		
		var productid = document.getElementById("productid").value;	// ��Ʒid��
	    var productname = document.getElementById("productname").value;	// ��Ʒ��׼����
	    var spec = document.getElementById("spec").value;				// ��Ʒ���
	    var barcode = "";//document.getElementById("barcode").value;			// ��Ʒ���ţ�
	    var pkspec = document.getElementById("pkspec").value;			// ��Ʒ������
	    var recunit = document.getElementById("recunit").value;			// ������λ��
	    //var pttypeid = document.getElementById("pttypeid").value;		// ��Ʒ���
	    var storageareaid = document.getElementById("environment").value;	// �洢����
	    //var storagespaceid = document.getElementById("cargospace_id").value;// �洢��λ
	    var length = document.getElementById("length").value;			// ��
	    var width = document.getElementById("width").value;				// ��
	    var height = document.getElementById("height").value;			// ��
	    var upperlimit = document.getElementById("upperlimit").value;	// ��������
	    var lowerlimit = document.getElementById("lowerlimit").value;	// ��������
	    //var allocationid = document.getElementById("allocationid").value;	// �������ID
	    //var lotid = document.getElementById("lotid").value;				// ��������ID
	    //var pakageid = document.getElementById("package_id").value;			// ��װ����
	    var validityterm = document.getElementById("validityterm").value;	// ��Ч��
	    var validitytype = document.getElementById("validitytype").value;	// ��Ч��������
	    var productCode = document.getElementById("productCode").value;			// ��ע
	    var remark2 = document.getElementById("remark2").value;			// ��ע
	    var remark3 = document.getElementById("remark3").value;			// ��ע
	    var remark4 = "";//document.getElementById("remark4").value;			// ��ע
	    var useflag = document.getElementById("useflag").checked?"Y":"N";		// ���ñ�־
	    var producttype = document.getElementById("producttype").value;
	    
		//��Ʒ��
		if(productname == null || productname.length <1)
		{
			alert("����Ʒ��������Ϊ��!");
			return;
		}
		
		if(strlen(productname) > 100){
			alert("����Ʒ��������!");
			return;
		}
		//���
		if(spec!=null && spec.length>0)
		{
			if(strlen(spec) > 200){
				alert("����񡿹���!");
				return;
			}
		}
		
/* 		//��Ʒ����
		if(barcode!=null && barcode.length>0)
		{
			if(strlen(barcode) > 40){
				alert("����Ʒ���롿����!");
				return;
			}
		} */
		//��
		if(length != null && length.length > 0 && length != 0)
		{
			if(!isDig(length))
			{
				alert("������ֻ��Ϊ����������0��");
				return;
			}
		}
		
		//��
		if(width != null && width.length > 0 && width != 0)
		{
			if(!isDig(width))
			{
				alert("����ֻ��Ϊ����������0��");
				return;
			}
		}
		
		//��
		if(height != null && height.length > 0 && height != 0)
		{
			if(!isDig(height))
			{
				alert("���ߡ�ֻ��Ϊ����������0��");
				return;
			}
		}
		
		//�������
		if(upperlimit != null && upperlimit.length > 0 && upperlimit != 0)
		{
			if(!isDig(upperlimit))
			{
				alert("��������ޡ�ֻ��Ϊ����������0��");
				return;
			}
		}
		
		//�������
		if(lowerlimit != null && lowerlimit.length > 0 && lowerlimit != 0)
		{
			if(!isDig(lowerlimit))
			{
				alert("��������ޡ�ֻ��Ϊ����������0��");
				return;
			}
		}
		
		//��Ч��
		if(validityterm != null && validityterm.length > 0 && validityterm != 0)
		{
			if(!isDig(validityterm))
			{
				alert("����Ч�ڡ�ֻ��Ϊ����������0��");
				return;
			}
		}
		
		//��ע1
		if(productCode==null && productCode.length<1)
		{
			alert("����Ʒ���롿����Ϊ�գ�");
			return;
		}
		
		//��ע2
		if(remark2!=null && remark2.length>0)
		{
			if(strlen(remark2) > 30){
				alert("����ע2������!");
				return;
			}
		}
		
		//��ע3
		if(remark3!=null && remark3.length>0)
		{
			if(strlen(remark3) > 30){
				alert("����ע3������!");
				return;
			}
		}
		
/* 		//��ע4
		if(remark4!=null && remark4.length>0)
		{
			if(strlen(remark4) > 30){
				alert("����ע4������!");
				return;
			}
		} */
		if(producttype != null && producttype.length < 1)
		{
			alert("��ѡ���Ʒ���");
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
		var typevalue = "yxqlx";		//��Ч�����͵�ֵ	
		selectType("<%=validitytype%>", "validitytype", typevalue);
		
		var typevalue = "punit";		//��λ���͵�ֵ	
		selectType("<%=recunit%>", "recunit", typevalue);
		//selectType("<%=sendunit%>", "sendunit", typevalue);
		selectType("<%=storageareaid%>", "environment", "cchj");//�洢����
		selectType("<%=producttype%>", "producttype", "producttype");
		//DWREngine.setAsync(false);
		//selectObject("<%=warehouseid%>", "warehouseid", "1");
		//DWREngine.setAsync(true);
		
		//var warehouseid = document.getElementById("warehouseid").value;
		//selectAreaList("<%=storageareaid%>", "whAreaId", warehouseid, "1");
		
		//selectObject("<%=pttypeid%>", "pttypeid", "6");
		//selectLot("<%=lotid%>", "lotid");		//��������
		
		//selectRules("<%=putawayid%>", "putawayid", warehouseid, "1");	//�ϼܹ���
		//selectRules("<%=allocationid%>", "allocationid", warehouseid,  "2");	//�������
		//selectRules("<%=replenishid%>", "replenishid", warehouseid, "3");	//��������
	}
	
	//�ֿ�仯ʱ��
	function onWhChange(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");		//����
		
		//selectRules("", "putawayid", warehouseid, "1");	//�ϼܹ���
		selectRules("", "allocationid", warehouseid,  "2");	//�������
		//selectRules("", "replenishid", warehouseid, "3");	//��������
	}

-->
</script>
</head>

<body onload="OnLoad();">

  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�������Ϣ -&gt; ��Ʒ���� -&gt; �޸���Ʒ��Ϣ</div></td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
   <tr>
      <td width="100px" class="yx1" align="right"><span class="red">*</span>��Ʒ��׼����</td>
      <td class="yx"><input type="hidden" id="productid" value="<%=productid%>"><input type="text" id="productname" maxlength="100" size="20" value="<%=productname%>"></td>
      <td width="100px" class="yx1" align="right">��Ʒ��������</td>
      <td class="xx1"><input type="text" id="pkspec" maxlength="32" size="20" value="<%=pkspec%>"></td>
    </tr> 
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>��Ʒ���</td>
      <td class="yx" ><select id="producttype" style="width:110px;">
      	<option value="">---��ѡ��---</option>
      </select></td>
      <td width="100px" class="yx1" align="right">���</td>
      <td class="xx1"><input type="text" id="spec" maxlength="200" size="20" value="<%=spec%>"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">������λ��</td>
      <td class="yx"><select id="recunit" style="width:110px;"><option value="">--��ѡ��--</option></select></td>
      <td class="yx1" align="right">����</td>
      <td class="xx1"><input type="text" id="length" maxlength="14" size="14" value="<%=length%>"></td>
    </tr>
    <tr>
	  
	  <td class="yx1" align="right"><span class="red">*</span>��Ʒ���룺</td>
      <td class="yx"><input type="text" id="productCode" maxlength="30" size="30" value="<%=productCode%>" disabled></td>
	   
      <td class="yx1" align="right">��</td>
      <td class="xx1"><input type="text" id="width" maxlength="14" size="14" value="<%=width%>"></td>
    </tr>
    <tr>
       <td class="yx1" align="right">�洢������</td>
      <td class="yx"><select id="environment" style="width:110px;"><option value="">--��ѡ��--</option></select></td>
      <td class="yx1" align="right">�ߣ�</td>
      <td class="xx1"><input type="text" id="height" maxlength="14" size="14" value="<%=height%>"></td>
    </tr>
    <tr>
	     
      <td class="yx1" align="right">�������ޣ�</td>
      <td class="yx"><input type="text" id="upperlimit" maxlength="14" size="14" value="<%=upperlimit%>"></td>
       <td class="yx1" align="right">�������ޣ�</td>
      <td class="xx1"><input type="text" id="lowerlimit" maxlength="14" size="14" value="<%=lowerlimit%>"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">��Ч�����ͣ�</td>
      <td class="yx"><select id="validitytype" style="width:110px;"><option value="<%=validitytype%>">--��ѡ��--</option></select></td>
      <td class="yx1" align="right">���ñ�־��</td>
      <td class="xx1"><input type="checkbox" id="useflag" <%if(useflag!=null && useflag.equals("Y")){%> checked <%}%>></td>
      
    </tr>
    <tr>
     
      
      <td class="yx1" align="right">��Ч�ڣ�</td>
      <td class="yx"><input type="text" id="validityterm" maxlength="14" size="14" value="<%=validityterm%>"></td>
      <td class="yx1" align="right">��ע2��</td>
      <td class="xx1"><input type="text" id="remark2" maxlength="30" size="30" value="<%=remark2%>"></td>
    </tr>
    
    <tr>
      
      <td class="yx1" align="right">��ע3��</td>
      <td class="yx" ><input type="text" id="remark3" maxlength="30" size="30" value="<%=remark3%>"></td>
       <td class="xx1" colspan="2"></td>
      
    </tr>
    
<%--     <tr>
      
      <td width="100px" class="yx1" align="right">��Ʒ���룺</td>
      <td class="yx"><input type="text" id="barcode" maxlength="40" size="20" value="<%=barcode%>"></td> 
      <td class="xx1" colspan="2"></td>
      
    </tr> --%>
    
    
    <tr>
      <td class="TD_MGR_TOP" align="center" colspan="4">
        <input type="button" onclick="OnOk()" name="add" value="&nbsp;&nbsp;&nbsp;����" class="button_add">&nbsp; 
        <input type="button" onClick="window.close()" name="resetBtn" value="�ر�" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
 
</body>
</html>