<%@ page contentType="text/html; charset=GBK"%>
<%
	String id = request.getParameter("id");					//���ID
	String isAlleytwin = request.getParameter("istwin");	//�Ƿ�˫����λ���
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
		document.getElementById("cargoAlleyName").value = "�����С���";
		
		cargoSpaceTree.getParentInfo(strId , '1', 
			function(data){
				document.getElementById("warehouseName").value = data[0];   
				document.getElementById("whAreaName").value = data[1];
		    	document.getElementById("cargoAlleyName").value = data[2];
	    	}
	    )
	}
	//�����λ
	function saveData(){ 	
		var cargoAlleyId = '<%=id%>';
        var minPlatoon = document.getElementById("minPlatoon").value;
		var maxPlatoon = document.getElementById("maxPlatoon").value;
		var minColumn = document.getElementById("minColumn").value;
		var maxColumn = document.getElementById("maxColumn").value;
		var floorNum = document.getElementById("floorNum").value;
		var cstype = document.getElementById("cstype").value;
		var storagetype = document.getElementById("storagetype").value;
		var length = document.getElementById("length").value=="" ? 0 : document.getElementById("length").value;
		var width = document.getElementById("width").value=="" ? 0 : document.getElementById("width").value;
		var height = document.getElementById("height").value=="" ? 0 : document.getElementById("height").value;
		var volume = document.getElementById("volume").value=="" ? 0 : document.getElementById("volume").value;
		var layernum = document.getElementById("layernum").value=="" ? 0 : document.getElementById("layernum").value;
		var loadweight = document.getElementById("loadweight").value=="" ? 0 : document.getElementById("loadweight").value;
		
		var pramArray = [cargoAlleyId, minPlatoon, maxPlatoon, minColumn, maxColumn, floorNum, cstype, storagetype, 
			length, width, height, volume, layernum, loadweight, "<%=isAlleytwin%>"];
		window.returnValue = pramArray;
		window.close();
    	
	}
	//���ݼ���
	function checkData(){
		var minPlatoon = Trim(document.getElementById("minPlatoon").value);
		var maxPlatoon = Trim(document.getElementById("maxPlatoon").value);
		var minColumn = Trim(document.getElementById("minColumn").value);
		var maxColumn = Trim(document.getElementById("maxColumn").value);
		var floorNum = Trim(document.getElementById("floorNum").value);
		var cstype = document.getElementById("cstype").value;
		var storagetype = document.getElementById("storagetype").value;
		var length = Trim(document.getElementById("length").value);
		var width = Trim(document.getElementById("width").value);
		var height = Trim(document.getElementById("height").value);
		var volume = Trim(document.getElementById("volume").value);
		var loadweight = Trim(document.getElementById("loadweight").value);
		var layernum = Trim(document.getElementById("layernum").value);
		
		if(minPlatoon == null || minPlatoon.length <1)
		{
			alert("��������ʼ�š�����Ϊ��!");
			return false;
		}else{
			if(!numChar(minPlatoon) || minPlatoon==0){
				alert("��������ʼ�š������Ǵ���0������!");
				return false;
			}
		}
		if(maxPlatoon == null || maxPlatoon.length <1)
		{
			alert("��������ֹ�š�����Ϊ��!");
			return false;
		}else{
			if(!numChar(maxPlatoon) || maxPlatoon==0){
				alert("��������ֹ�š������Ǵ���0������!");
				return false;
			}else{
				if(parseInt(minPlatoon)>parseInt(maxPlatoon)){
					alert("����ʼ�š����ܴ��ڡ���ֹ�š���");
					return false;
				}else{
					//˫����λ���(����4��һ���ʼ��)
					if("<%=isAlleytwin%>" == "Y"){	
						if(parseInt(maxPlatoon)-parseInt(minPlatoon)!=3){
							alert("һ�����������Ż�λ,��ͬʱ��ʼ����");
							return false;
						}
					
					}else{
						if(parseInt(maxPlatoon)-parseInt(minPlatoon)>1){
							alert("һ������ֻ�����Ż�λ��");
							return false;
						}
					}
				}
			}
		}
		if(minColumn == null || minColumn.length <1)
		{
			alert("��������ʼ�С�����Ϊ��!");
			return false;
		}else{
			if(!numChar(minColumn) || minColumn==0){
				alert("��������ʼ�С������Ǵ���0������!");
				return false;
			}
		}
		if(maxColumn == null || maxColumn.length <1)
		{
			alert("��������ֹ�С�����Ϊ��!");
			return false;
		}else{
			if(!numChar(maxColumn) || maxColumn==0){
				alert("��������ֹ�С������Ǵ���0������!");
				return false;
			}else{
				if(parseInt(minColumn)>parseInt(maxColumn)){
					alert("����ʼ�С����ܴ��ڡ���ֹ�С���");
					return false;
				}
			}
		}
		if(floorNum == null || floorNum.length <1)
		{
			alert("���ֿ����������Ϊ��!");
			return false;
		}else{
			if(!numChar(floorNum) || floorNum==0){
				alert("���ֿ�����������Ǵ���0������!");
				return false;
			}else{
				if(floorNum<=0){
					alert("���ֿ���������������!");
					return false;
				}
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
	}
	
	function OnLoad(){

		selectType("1", "cstype", "hwlx");		//��λ����
		selectType("", "storagetype", "cclx");	//�洢����
		
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
      <td class="yx"><input type="text" readonly="readonly" class="input_readonly" name="warehouseName" size="15"></td>
      <td width="100px" class="yx1" align="right">����������</td>
      <td class="xx1"><input type="text" readonly="readonly" class="input_readonly" name="whAreaName" size="15"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">���������</td>
      <td class="xx1" colspan="3"><input type="text" readonly="readonly" class="input_readonly" name="cargoAlleyName" size="15"></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>����Ŵӣ�</td>
      <td class="yx">
        <input type="text" name="minPlatoon" size="4" maxlength="2">&nbsp;����<input type="text" name="maxPlatoon" size="4" maxlength="2"></td>
      <td class="yx1" align="right"><span class="red">*</span>����дӣ�</td>
      <td class="xx1">
        <input type="text" name="minColumn" size="4" maxlength="2">&nbsp;����<input type="text" name="maxColumn" size="4" maxlength="2"></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>�ֿ������</td>
      <td class="xx1" colspan="3"><input type="text" name="floorNum" size="4" maxlength="2"></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>��λ���ͣ�</td>
      <td class="yx"><select name="cstype" style="width:100px;"><option value=""></option></select></td>
      <td class="yx1" align="right"><span class="red">*</span>�洢���ͣ�</td>
      <td class="xx1"><select name="storagetype" style="width:100px;"><option value=""></option></select></td>
    </tr>
   <tr>
     <td class="yx1" align="right">����</td>
     <td class="yx"><input type="text" name="length" size="10" maxlength="10" value="0.00"></td>
     <td class="yx1" align="right">��</td>
     <td class="xx1"><input type="text" name="width" size="10" maxlength="10" value="0.00"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">�ߣ�</td>
     <td class="xx1" colspan="3"><input type="text" name="height" size="10" maxlength="10" value="0.00"></td> 
   </tr>
   <tr>
     <td class="yx1" align="right">�����</td>
     <td class="yx"><input type="text" name="volume" size="10" maxlength="10" value="0.00"></td> 
     <td class="yx1" align="right">���أ�</td>
     <td class="xx1"><input type="text" name="loadweight" size="10" maxlength="10" value="0.00"></td> 
   </tr>
   <tr>
     <td class="yx1" align="right">������</td>
     <td class="xx1" colspan="3"><input type="text" name="layernum" size="4" maxlength="1" value="1"></td>
   </tr>
    <tr>
      <td height="27" colspan="4" align="center">
        <input type="button" onclick="if(checkData()!=false){saveData();}" name="add" value="&nbsp;&nbsp;&nbsp;����" class="button_add">&nbsp; 
        <input type="button" onClick="window.close()" name="resetBtn" value="&nbsp;&nbsp;&nbsp;ȡ��" class="button_reset">
     </td>
    </tr>
  </table>
  </form>
  
</body>
</html>