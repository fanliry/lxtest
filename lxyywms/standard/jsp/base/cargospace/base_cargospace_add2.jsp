<%@ page contentType="text/html; charset=GBK"%>
<%
	String id = request.getParameter("id");		//����ID
%>
<html>
<head>
<title>��λ�Զ�����</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/tools.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/cargoSpaceTree.js"></script>
<script type="text/javascript">
<!--
	//��ȡ�����������ֿ���Ϣ
	function getParentInfo(strId){
	
		document.getElementById("warehouseName").value = "�����С���";   
		document.getElementById("whAreaName").value = "�����С���";
		
		cargoSpaceTree.getParentInfo(strId , '0', 
			function(data){
				document.getElementById("warehouseName").value = data[0];   
				document.getElementById("whAreaName").value = data[1];
	    	}
	    )
	}
	//�����λ
	function saveData(){ 	
		var whAreaId = '<%=id%>';
		var intPlatoon = document.getElementById("intPlatoon").value;
		var intColumn = document.getElementById("intColumn").value;
		var intfloor = document.getElementById("intfloor").value;	
		var cstype = document.getElementById("cstype").value;
		var storagetype = document.getElementById("storagetype").value;
		var puttype = "";
		var picktype = "";
		var usagetype = "";			//��λʹ��
		var attributetype = document.getElementById("attributetype").value;	//��λ����
		var demandtype = "";		//��ת����
		var length = document.getElementById("length").value=="" ? 0 : document.getElementById("length").value;
		var width = document.getElementById("width").value=="" ? 0 : document.getElementById("width").value;
		var height = document.getElementById("height").value=="" ? 0 : document.getElementById("height").value;
		var volume = document.getElementById("volume").value=="" ? 0 : document.getElementById("volume").value;
		var xcoordinate = document.getElementById("xcoordinate").value=="" ? 0 : document.getElementById("xcoordinate").value;
		var ycoordinate = document.getElementById("ycoordinate").value=="" ? 0 : document.getElementById("ycoordinate").value;
		var zcoordinate = document.getElementById("zcoordinate").value=="" ? 0 : document.getElementById("zcoordinate").value;
		var environment = document.getElementById("environment").value;
		var layernum = document.getElementById("layernum").value=="" ? 0 : document.getElementById("layernum").value;
		var loadweight = document.getElementById("loadweight").value=="" ? 0 : document.getElementById("loadweight").value;
		var palletnum = document.getElementById("palletnum").value=="" ? 0 : document.getElementById("palletnum").value;
		var pramArray = [whAreaId, intPlatoon, intColumn, intfloor, cstype, storagetype, puttype, picktype, usagetype, attributetype, demandtype,
			length, width, height, volume, xcoordinate, ycoordinate, zcoordinate, environment, layernum, loadweight, palletnum];
		window.returnValue = pramArray;
		window.close();
    	
	}
	//���ݼ���
	function checkData(){
		var intPlatoon = Trim(document.getElementById("intPlatoon").value);
		var intColumn = Trim(document.getElementById("intColumn").value);
		var intfloor = Trim(document.getElementById("intfloor").value);
		var cstype = document.getElementById("cstype").value;
		var storagetype = document.getElementById("storagetype").value;
		var length = Trim(document.getElementById("length").value);
		var width = Trim(document.getElementById("width").value);
		var height = Trim(document.getElementById("height").value);
		var volume = Trim(document.getElementById("volume").value);
		var xcoordinate = Trim(document.getElementById("xcoordinate").value);
		var ycoordinate = Trim(document.getElementById("ycoordinate").value);
		var zcoordinate = Trim(document.getElementById("zcoordinate").value);
		var loadweight = Trim(document.getElementById("loadweight").value);
		var layernum = Trim(document.getElementById("layernum").value);
		var palletnum = Trim(document.getElementById("palletnum").value);
		
		//��
		if(intPlatoon == null || intPlatoon.length <1)
		{
			alert("���š�����Ϊ��!");
			return false;
		}else{
			if(!numChar(intPlatoon) || intPlatoon<=0){
				alert("���š������Ǵ����������!");
				return false;
			}
		}
		
		//��
		if(intColumn == null || intColumn.length <1)
		{
			alert("���С�����Ϊ��!");
			return false;
		}else{
			if(!numChar(intColumn) || intColumn<=0){
				alert("���С������Ǵ����������!");
				return false;
			}
		}
		
		//��
		if(intfloor == null || intfloor.length <1)
		{
			alert("���㡿����Ϊ��!");
			return false;
		}else{
			if(!numChar(intfloor) || intfloor<=0){
				alert("���㡿�����Ǵ����������!");
				return false;
			}
		}
		
		//��λ����
		if(cstype == null || cstype.length <1)
		{
			alert("����λ���͡�����Ϊ��!");
			return false;
		}
		
		//�洢����
		if(storagetype == null || storagetype.length <1)
		{
			alert("���洢���͡�����Ϊ��!");
			return false;
		}
		
		//��
		if(length != null )
		{
			if(!isDig(length) || length<0){
				alert("�����������Ǵ����������!");
				return false;
			}
		}
		
		//��
		if(width != null )
		{
			if(!isDig(width) || width<0){
				alert("���������Ǵ����������!");
				return false;
			}
		}
		
		//��
		if(height != null )
		{
			if(!isDig(height) || height<0){
				alert("���ߡ������Ǵ����������!");
				return false;
			}
		}
		
		//���
		if(volume != null )
		{
			if(!isDig(volume) || volume<0){
				alert("������������Ǵ����������!");
				return false;
			}
		}
		
		//x����
		if(xcoordinate != null )
		{
			if(!isDig(xcoordinate) || xcoordinate<0){
				alert("��x���꡿�����Ǵ����������!");
				return false;
			}
		}
		
		//y����
		if(ycoordinate != null )
		{
			if(!isDig(ycoordinate) || ycoordinate<0){
				alert("��y���꡿�����Ǵ����������!");
				return false;
			}
		}
		
		//z����
		if(zcoordinate != null )
		{
			if(!isDig(zcoordinate) || zcoordinate<0){
				alert("��z���꡿�����Ǵ����������!");
				return false;
			}
		}
		
		//����
		if(loadweight != null )
		{
			if(!isDig(loadweight) || loadweight<0){
				alert("�����ء������Ǵ����������!");
				return false;
			}
		}
		
		//����
		if(layernum != null )
		{
			if(!numChar(layernum) || layernum<0){
				alert("�������������Ǵ����������!");
				return false;
			}
		}
		
		//�ɷ�������
		if(palletnum != null )
		{
			if(!numChar(palletnum) || palletnum<0){
				alert("���ɷ��������������Ǵ����������!");
				return false;
			}
		}
	}
	
	function OnLoad(){

		selectType("", "cstype", "hwlx");			//��λ����
		selectType("", "storagetype", "cclx");		//�洢����
		selectType("", "attributetype", "kwsx");	//��λ����
	
		//��ȡ�����������ֿ���Ϣ
		getParentInfo('<%=id%>');
	}
-->
</script>
</head>

<body onload="OnLoad()">
<form name="myForm" method="post" action="">
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�������Ϣ -&gt; �ֿ���� -&gt; ������λ��Ϣ</div></td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right">�����ֿ⣺</td>
      <td class="yx"><input type="text" readonly="readonly" class="input_readonly" name="warehouseName" size="16"></td>
      <td width="100px" class="yx1" align="right">����������</td>
      <td class="xx1"><input type="text" readonly="readonly" class="input_readonly" name="whAreaName" size="16"></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>�ţ�</td>
      <td class="yx"><input type="text" name="intPlatoon" size="4" maxlength="2"></td>
      <td class="yx1" align="right"><span class="red">*</span>�У�</td>
      <td class="xx1"><input type="text" name="intColumn" size="4" maxlength="2"></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>�㣺</td>
      <td class="xx1" colspan="3"><input type="text" name="intfloor" size="4" maxlength="2"></td> 
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>��λ���ͣ�</td>
      <td class="yx"><select name="cstype" style="width:110px;"><option value=""></option></select></td>
      <td class="yx1" align="right"><span class="red">*</span>�洢���ͣ�</td>
      <td class="xx1"><select name="storagetype" style="width:110px;"><option value=""></option></select></td>
    </tr>
    <tr>
      <td class="yx1" align="right"  colspan="3">��λ���ԣ�</td>
      <td class="xx1"><select name="attributetype" style="width:110px;"><option value=""></option></select></td>
    </tr>
   <tr>
     <td class="yx1" align="right">����</td>
     <td class="yx"><input type="text" name="length" size="10" maxlength="10" value="0.00"></td>
	 <td class="yx1" align="right">x���꣺</td>
     <td class="xx1"><input type="text" name="xcoordinate" size="10" maxlength="10" value="0.00"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">��</td>
     <td class="yx"><input type="text" name="width" size="10" maxlength="10" value="0.00"></td>
     <td class="yx1" align="right">y���꣺</td>
     <td class="xx1"><input type="text" name="ycoordinate" size="10" maxlength="10" value="0.00"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">�ߣ�</td>
     <td class="yx"><input type="text" name="height" size="10" maxlength="10" value="0.00"></td> 
     <td class="yx1" align="right">z���꣺</td>
     <td class="xx1"><input type="text" name="zcoordinate" size="10" maxlength="10" value="0.00"></td> 
   </tr>
   <tr>
     <td class="yx1" align="right">�����</td>
     <td class="yx"><input type="text" name="volume" size="10" maxlength="10" value="0.00"></td> 
     <td class="yx1" align="right">������</td>
     <td class="xx1"><input type="text" name="layernum" size="4" maxlength="1" value="1"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">���أ�</td>
     <td class="yx"><input type="text" name="loadweight" size="10" maxlength="10" value="0.00"></td> 
     <td class="yx1" align="right">�ɷ���������</td>
     <td class="xx1"><input type="text" name="palletnum" size="4" maxlength="1" value="1"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">������</td>
     <td class="xx1" colspan="3"><input type="text" name="environment" size="30" maxlength="25" value=""></td>
   </tr>
   <tr>
     <td height="27" colspan="6" align="center">
       <input type="button" onclick="if(checkData()!=false){saveData();}" name="add" value="&nbsp;&nbsp;&nbsp;����" class="button_add">&nbsp; 
       <input type="button" onClick="window.close()" name="resetBtn" value="&nbsp;&nbsp;&nbsp;ȡ��" class="button_reset">
     </td>
   </tr>
  </table>
 
</form>
</body>
</html>