<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseCargospace"%>
<%
	BaseCargospace cargoSpace = (BaseCargospace)request.getAttribute("cargoSpace");  
	
	String cargoSpaceId = cargoSpace.getCargoSpaceId();		// ��λID
	String cargoSpaceName = cargoSpace.getCargoSpaceName();	// ��λ����
	String cargoAlleyName = cargoSpace.getCargoAlleyName();	// �������
	String warehousename = cargoSpace.getWarehousename();	// �ֿ�����
	String whAreaName = cargoSpace.getWhAreaName();			// ��������
	String warehouseid = cargoSpace.getWarehouseid();		// �ֿ�ID
	String cargoAreaId = cargoSpace.getCargoAreaId();		// ����ID
	//String csstatus = cargoSpace.getCsstatus();			// ��λ״̬
	//Integer csplatoon = cargoSpace.getCsplatoon();	// ��λ��
	//Integer cscolumn = cargoSpace.getCscolumn();		// ��λ��
	//Integer csfloor = cargoSpace.getCsfloor();		// ��λ��
	String inlock = cargoSpace.getInlock();				// ����� Y.�� N.��
	String outlock = cargoSpace.getOutlock();			// ������ Y.�� N.��
	String cstype = cargoSpace.getCstype();				// ��λ����
	String storagetype = cargoSpace.getStoragetype();	// �洢����
	String puttype = cargoSpace.getPuttype();			// �ϼ�����
	String picktype = cargoSpace.getPicktype();			// ��ѡ����
	String usagetype = cargoSpace.getUsagetype();   	// ��λʹ��
	String attributetype = cargoSpace.getAttributetype(); // ��λ����
	String demandtype = cargoSpace.getDemandtype();   	// ��ת����
	double length = cargoSpace.getLength();				// ��
	double width = cargoSpace.getWidth();				// ��
	double height = cargoSpace.getHeight();				// ��
	double volume = cargoSpace.getVolume();				// ���
	double xcoordinate = cargoSpace.getXcoordinate();	// x����
	double ycoordinate = cargoSpace.getYcoordinate();	// y����
	double zcoordinate = cargoSpace.getZcoordinate();	// z����
	String environment = cargoSpace.getEnvironment();	// ����
	Integer layernum = cargoSpace.getLayernum();		// ����
	double loadweight = cargoSpace.getLoadweight();		// ����
	Integer palletnum = cargoSpace.getPalletnum();		// �ɷ�������
	
%>
<html>
<head>
<title>��λ�޸�</title>
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
		var cargoSpaceId = document.getElementById("cargoSpaceId").value;	// ��λID
		var cargoAreaId = document.getElementById("cargoAreaId").value;		// ����ID
		var inlock = document.getElementById("inlock").checked?"Y":"N";		// ����� Y.�� N.��
		var outlock = document.getElementById("outlock").checked?"Y":"N";	// ������ Y.�� N.��
		var cstype = document.getElementById("cstype").value;				// ��λ����
		var storagetype = document.getElementById("storagetype").value;		// �洢����
		var puttype = document.getElementById("puttype").value;				// �ϼ�����
		var picktype = document.getElementById("picktype").value;			// ��ѡ����
		var usagetype = document.getElementById("usagetype").value;			// ��λʹ��
		var attributetype = document.getElementById("attributetype").value;	// ��λ����
		var demandtype = document.getElementById("demandtype").value;		// ��ת����
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
		
		var pramArray = [cargoSpaceId, cargoAreaId, inlock, outlock, cstype, storagetype, puttype, picktype, usagetype, attributetype, demandtype,
			length, width, height, volume, xcoordinate, ycoordinate, zcoordinate, environment, layernum, loadweight, palletnum];
		window.returnValue = pramArray;
		window.close();
    	
	}
	//���ݼ���
	function checkData(){
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

		selectType("<%=cstype%>", "cstype", "hwlx");				//��λ����
		selectType("<%=storagetype%>", "storagetype", "cclx");		//�洢����
		selectType("<%=puttype%>", "puttype", "sjlx");				//�ϼ�����
		selectType("<%=picktype%>", "picktype", "jxlx");			//��ѡ����
		selectType("<%=usagetype%>", "usagetype", "kwsy");			//��λʹ��
		selectType("<%=attributetype%>", "attributetype", "kwsx");	//��λ����
		selectType("<%=demandtype%>", "demandtype", "zzxq");		//��ת����
		
		selectAreaList("<%=cargoAreaId%>", "cargoAreaId", "<%=warehouseid%>", "2");	//����
	}
-->
</script>
</head>

<body onload="OnLoad()">
<form name="myForm" method="post" action="">
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">��ǰλ�ã�������Ϣ -&gt; �ֿ���� -&gt; �޸Ļ�λ��Ϣ</div></td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right">��λID��</td>
      <td class="yx"><input type="text" readonly class="input_readonly" name="cargoSpaceId" value="<%=cargoSpaceId%>"></td>
      <td width="100px" class="yx1" align="right">��λ���ƣ�</td>
      <td class="xx1"><input type="text" readonly class="input_readonly" name="cargoSpaceName" value="<%=cargoSpaceName%>"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">�����ֿ⣺</td>
      <td class="yx" ><input type="text" readonly class="input_readonly" name="warehousename" value="<%=warehousename%>"></td>
      <td class="yx1" align="right">����������</td>
      <td class="xx1"><input type="text" readonly class="input_readonly" name="whAreaName" value="<%=whAreaName%>"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">���������</td>
      <td class="yx"><input type="text" readonly class="input_readonly" name="cargoAlleyName" value="<%=cargoAlleyName==null?"":cargoAlleyName%>"></td>
      <td class="yx1" align="right">����������</td>
      <td class="xx1"><select name="cargoAreaId"><option value=""></option></select></td>
    </tr>
    <tr>
      <td class="yx1" align="right">�������</td>
      <td class="yx"><input type="checkbox" name="inlock" <%if(inlock.equals("Y")){%>checked<%}%>></td>
      <td class="yx1" align="right">��������</td>
      <td class="xx1"><input type="checkbox" name="outlock" <%if(outlock.equals("Y")){%>checked<%}%>></td>
    </tr>
    <tr>
      <td class="yx1" align="right">��λ���ͣ�</td>
      <td class="yx"><select name="cstype" style="width:110px;"><option value=""></option></select></td>
      <td class="yx1" align="right">�洢���ͣ�</td>
      <td class="xx1"><select name="storagetype" style="width:110px;"><option value=""></option></select></td>
    </tr>
    <tr>
      <td class="yx1" align="right">�ϼ����ͣ�</td>
      <td class="yx"><select name="puttype" style="width:110px;"><option value=""></option></select></td>
      <td class="yx1" align="right">��ѡ���ͣ�</td>
      <td class="xx1"><select name="picktype" style="width:110px;"><option value=""></option></select></td>
    </tr>    
    <tr>
      <td class="yx1" align="right">��λʹ�ã�</td>
      <td class="yx"><select name="usagetype" style="width:110px;"><option value=""></option></select></td>
      <td class="yx1" align="right">��λ���ԣ�</td>
      <td class="xx1"><select name="attributetype" style="width:110px;"><option value=""></option></select></td>
    </tr>
    <tr>
      <td class="yx1" align="right">��ת����</td>
      <td class="xx1" colspan="3"><select name="demandtype" style="width:110px;"><option value=""></option></select></td>
    </tr>
    <tr>
      <td class="yx1" align="right">����</td>
      <td class="yx"><input type="text" name="length" size="10" maxlength="10" value="<%=length%>"></td>
      <td class="yx1" align="right">x���꣺</td>
      <td class="xx1"><input type="text" name="xcoordinate" size="10" maxlength="10" value="<%=xcoordinate%>"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">��</td>
      <td class="yx"><input type="text" name="width" size="10" maxlength="10" value="<%=width%>"></td>
      <td class="yx1" align="right">y���꣺</td>
      <td class="xx1"><input type="text" name="ycoordinate" size="10" maxlength="10" value="<%=ycoordinate%>"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">�ߣ�</td>
      <td class="yx"><input type="text" name="height" size="10" maxlength="10" value="<%=height%>"></td> 
      <td class="yx1" align="right">z���꣺</td>
      <td class="xx1"><input type="text" name="zcoordinate" size="10" maxlength="10" value="<%=zcoordinate%>"></td> 
    </tr>
    <tr>
      <td class="yx1" align="right">�����</td>
      <td class="yx"><input type="text" name="volume" size="10" maxlength="10" value="<%=volume%>"></td> 
      <td class="yx1" align="right">������</td>
      <td class="xx1"><input type="text" name="layernum" size="6" maxlength="1" value="<%=layernum==null?0:layernum%>"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">���أ�</td>
      <td class="yx"><input type="text" name="loadweight" size="10" maxlength="10" value="<%=loadweight%>"></td> 
      <td class="yx1" align="right">�ɷ���������</td>
      <td class="xx1"><input type="text" name="palletnum" size="6" maxlength="1" value="<%=palletnum==null?0:palletnum%>"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">������</td>
      <td class="xx1" colspan="3"><input type="text" name="environment" size="25" maxlength="25" value="<%=environment==null?"":palletnum%>"></td>
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