<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.ricosoft.common.tools.StrTools" %>
<%
	String strTime =  StrTools.getCurrDateTime(8);
%>
<html>
<head>
<title>������Ʒ��Ϣ</title>
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

	    var productname = document.getElementById("productname").value;	// ��Ʒ��׼����
	    var spec = document.getElementById("spec").value;				// ��Ʒ���
	    var barcode = "";//document.getElementById("barcode").value;			// ��Ʒ���룬
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
	    var allocationid = "";	// �������ID
	    var lotid = "";				// ��������ID
	    var pakageid = "";			// ��װ����
	    var validityterm = document.getElementById("validityterm").value;	// ��Ч��
	    var validitytype = document.getElementById("validitytype").value;	// ��Ч��������
	    var productCode = document.getElementById("productCode").value;			// ��Ʒ����
	    var remark2 = document.getElementById("remark2").value;			// ��ע
	    var remark3 = document.getElementById("remark3").value;			// ��ע
	    var remark4 = "";			// ��ע
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
		
		//��Ʒ���� 
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
		
		//��ע4
		if(remark4!=null && remark4.length>0)
		{
			if(strlen(remark4) > 30){
				alert("����ע4������!");
				return;
			}
		}
		if(producttype != null && producttype.length < 1)
		{
			alert("��ѡ���Ʒ���");
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
		var typevalue = "yxqlx";		//��Ч�����͵�ֵ	
		selectType("", "validitytype", typevalue);
		
		var typevalue = "punit";		//��λ���͵�ֵ	
		selectType("", "recunit", typevalue);
		selectType("", "producttype", "producttype");
		//DWREngine.setAsync(false);
		//selectObject("", "warehouseid", "1");
		//DWREngine.setAsync(true);
		
		//var warehouseid = document.getElementById("warehouseid").value;
		//selectAreaList("", "whAreaId", warehouseid, "1");
		
		//selectObject("", "pttypeid", "6");		//��Ʒ���
		
		selectType("", "environment", "cchj");//�洢����
	}
	
	//�ֿ�仯ʱ��
	function onWhChange(){
	
		var warehouseid = document.getElementById("warehouseid").value;
		selectAreaList("", "whAreaId", warehouseid, "1");		//����
		selectRules("", "allocationid", warehouseid,  "2");	//�������
	}

</script>
</head>

<body onload="OnLoad();">
  <form name="myForm" method="post" action="">
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�������Ϣ -&gt; ��Ʒ���� -&gt; ������Ʒ��Ϣ</div></td>
    </tr>
  </table>
 
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right"><span class="red">*</span>��Ʒ��׼����</td>
      <td class="yx"><input type="text" id="productname" maxlength="100" size="20"></td>
      <td width="100px" class="yx1" align="right">��Ʒ��������</td>
      <td class="xx1"><input type="text" id="pkspec" maxlength="32" size="20"></td>
    </tr> 
    <tr>
       <td class="yx1" align="right"><span class="red">*</span>��Ʒ���</td>
      <td class="yx"><select id="producttype" style="width:110px;">
      	<option value="">---��ѡ��---</option>
      </select></td>
      <td width="100px" class="yx1" align="right">���</td>
      <td class="xx1"><input type="text" id="spec" maxlength="200" size="20"></td>
    </tr>
    <tr>
    	 <td class="yx1" align="right"><span class="red">*</span>��Ʒ���룺</td>
      <td class="yx"><input type="text" id="productCode" maxlength="30" size="30"></td>  
      
      <td class="yx1" align="right">����</td>
      <td class="xx1"><input type="text" id="length" maxlength="14" size="14" value="0.0"></td>
    </tr>
    <tr>
	   <td class="yx1" align="right">��Ʒ��λ��</td>
      <td class="yx"><select id="recunit" style="width:110px;"><option value="">--��ѡ��--</option></select></td>
      <td class="yx1" align="right">��</td>
      <td class="xx1"><input type="text" id="width" maxlength="14" size="14" value="0.0"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">�洢������</td>
      <td class="yx"><select id="environment" style="width:110px;"><option value="">--��ѡ��--</option></select></td>  
      <td class="yx1" align="right">�ߣ�</td>
      <td class="xx1"><input type="text" id="height" maxlength="14" size="14" value="0.0"></td>
    </tr>
    <tr>
	   
      <td class="yx1" align="right">�������ޣ�</td>
      <td class="yx"><input type="text" id="upperlimit" maxlength="14" size="14" value="0.0"></td>
       <td class="yx1" align="right">�������ޣ�</td>
      <td class="xx1"><input type="text" id="lowerlimit" maxlength="14" size="14" value="0.0"></td>
    </tr>
    <tr>
    
     <td class="yx1" align="right">��Ч�ڣ�</td>
      <td class="yx"><input type="text" id="validityterm" maxlength="14" size="14" value="0"></td>
      <td class="yx1" align="right">��Ч�����ͣ�</td>
      <td class="xx1"><select id="validitytype" style="width:110px;"><option value="">--��ѡ��--</option></select></td>
      
      
    </tr>
    <tr>
       <td class="yx1" align="right">���ñ�־��</td>
      <td class="yx"><input type="checkbox" id="useflag" checked></td>
       <td class="yx1" align="right">��ע2��</td>
      <td class="xx1"><input type="text" id="remark2" maxlength="30" size="30"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">��ע3��</td>
      <td class="yx"><input type="text" id="remark3" maxlength="30" size="30"></td>
      <!-- <td width="100px" class="yx1" align="right">��Ʒ���룺</td>
      <td class="yx"><input type="text" id="barcode" maxlength="32" size="20"></td> -->
      <td class="xx1" colspan="2"></td>
    </tr>
   
    <tr>
      <td height="27" colspan="4" align="center">
        <input type="button" onclick="OnOk()" id="add" value="&nbsp;&nbsp;&nbsp;����" class="button_add">&nbsp; 
        <input type="reset" id="resetDetailBtn" value="&nbsp;&nbsp;&nbsp;����" class="button_reset">&nbsp;
        <input type="button" onClick="window.close()" id="resetBtn" value="�ر�" class="BUTTON_STYLE1">
      </td>
    </tr>
  </table>
  </form>
  
</body>
</html>