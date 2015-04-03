<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseCargospace" %>
<%
	BaseCargospace cargoSpace = (BaseCargospace)request.getAttribute("cargoSpace");
	
	String cargoSpaceId = cargoSpace.getCargoSpaceId();			// ��λID
	String cargoSpaceName = cargoSpace.getCargoSpaceName();		// ��λ����
	String csstatusname = cargoSpace.getCsstatusname();		    // ��λ״̬����
	//int csplatoon = cargoSpace.getCsplatoon();				// ��λ��
	//int cscolumn = cargoSpace.getCscolumn();					// ��λ��
	//int csfloor = cargoSpace.getCsfloor();					// ��λ��
	String cstypename = cargoSpace.getCstypename();				// ��λ��������
	String inlock = cargoSpace.getInlock();						// ����� Y.�� N.��
	String outlock = cargoSpace.getOutlock();					// ������ Y.�� N.��
	String cargoAlleyName = cargoSpace.getCargoAlleyName();		// �������
	String cargoAreaName = cargoSpace.getCargoAreaName();		// ��������
	String warehousename = cargoSpace.getWarehousename();		// �ֿ�����
	String whAreaName = cargoSpace.getWhAreaName();				// ��������
	String storagetypename = cargoSpace.getStoragetypename();	// �洢��������
	String puttypename = cargoSpace.getPuttypename();			// �ϼ���������
	String picktypename = cargoSpace.getPicktypename();			// ��ѡ��������
	double length = cargoSpace.getLength();						// ��
	double width = cargoSpace.getWidth();						// ��
	double height = cargoSpace.getHeight();						// ��
	double volume = cargoSpace.getVolume();						// ���
	double xcoordinate = cargoSpace.getXcoordinate();			// x����
	double ycoordinate = cargoSpace.getYcoordinate();			// y����
	double zcoordinate = cargoSpace.getZcoordinate();			// z����
	String environment = cargoSpace.getEnvironment();			// ����
	Integer layernum = cargoSpace.getLayernum();				// ����
	double loadweight = cargoSpace.getLoadweight();				// ����
	Integer palletnum = cargoSpace.getPalletnum();				// �ɷ�������
	
	String istwin = cargoSpace.getIstwin();						// �Ƿ�˫����λ Y.�� N.��
	String location = "";										// λ�ã�1�����棻2������
	String twincsname = "";										// ����˫����λ
	if(istwin!=null && istwin.equals("Y")){
		location = cargoSpace.getLocation().equals("1")?"��":"��";
		twincsname = cargoSpace.getTwincsname();				
	}
	
	String usagetypename = cargoSpace.getUsagetypename();   	// ��λʹ������
	String attributetypename = cargoSpace.getAttributetypename();   // ��λ��������
	String demandtypename = cargoSpace.getDemandtypename();   	// ��ת��������
%>
<html>
<head>
<title>��λ����-��λ��Ϣ</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
  <table width="100%" height="100%" border="0" align="left" cellpadding="0" cellspacing="0" class="table_add">
   <tr>
	 <td colspan="2" style="height:25px;" class="TD_LIST_TITLE4">[<%=cargoSpaceId%>]<%=cargoSpaceName%></td>
    </tr>
   <!--tr>
     <td class="TD_ADD"><div align="right">��λ���룺</td>
     <td class="TD_ADD">
     <input type="text" name="warehouseNo" readonly="readonly" class="input_readonly" value="<%=cargoSpaceId%>"></td>
   </tr>
   <tr>
     <td class="TD_ADD"><div align="right">��λ���ƣ�</td>
     <td class="TD_ADD">
     <input type="text" name="typeCode" readonly="readonly" class="input_readonly" value="<%=cargoSpaceName%>"></td>
   </tr-->
   <tr>
     <td width="120px" class="yx1" align="right">�����ֿ⣺</td>
     <td class="xx1"><input type="text" name="warehousename" readonly="readonly" class="input_readonly" value="<%=warehousename%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">����������</td>
     <td class="xx1"><input type="text" name="whAreaName" readonly="readonly" class="input_readonly" value="<%=whAreaName%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">���������</td>
     <td class="xx1"><input type="text" name="cargoAlleyName" readonly="readonly" class="input_readonly" value="<%=cargoAlleyName==null?"":cargoAlleyName%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">����������</td>
     <td class="xx1"><input type="text" name="cargoAreaName" readonly="readonly" class="input_readonly" value="<%=cargoAreaName==null?"":cargoAreaName%>"></td>
   </tr>
   <!--tr>
     <td class="TD_ADD"><div align="right">�ţ�</td>
     <td class="TD_ADD">
     <input type="text" name="mgrMan" readonly="readonly" class="input_readonly" value="<%=cargoAreaName%>"></td>
   </tr>
   <tr>
     <td class="TD_ADD"><div align="right">�У�</td>
     <td class="TD_ADD">
     <input type="text" name="mgrMan" readonly="readonly" class="input_readonly" value="<%=cargoAreaName%>"></td>
   </tr>
   <tr>
     <td class="TD_ADD"><div align="right">�㣺</td>
     <td class="TD_ADD">
     <input type="text" name="mgrMan" readonly="readonly" class="input_readonly" value="<%=cargoAreaName%>"></td>
   </tr-->
   <tr>
     <td class="yx1" align="right">��λ״̬��</td>
     <td class="xx1"><input type="text" name="csstatus" readonly="readonly" class="input_readonly"  value="<%=csstatusname%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">��λ���ͣ�</td>
     <td class="xx1"><input type="text" name="cstype" readonly="readonly" class="input_readonly" value="<%=cstypename==null?"":cstypename%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">�洢���ͣ�</td>
     <td class="xx1"><input type="text" name="storagetype" readonly="readonly" class="input_readonly" value="<%=storagetypename==null?"":storagetypename%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">�ϼ����ͣ�</td>
     <td class="xx1"><input type="text" name="puttype" readonly="readonly" class="input_readonly" value="<%=puttypename==null?"":puttypename%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">��ѡ���ͣ�</td>
     <td class="xx1"><input type="text" name="picktype" readonly="readonly" class="input_readonly" value="<%=picktypename==null?"":picktypename%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right"><div align="right">����</td>
     <td class="xx1"><input type="text" name="length" readonly="readonly" class="input_readonly" value="<%=length==0?"":length%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">��</td>
     <td class="xx1"><input type="text" name="width" readonly="readonly" class="input_readonly" value="<%=width==0?"":width%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">�ߣ�</td>
     <td class="xx1"><input type="text" name="height" readonly="readonly" class="input_readonly" value="<%=height==0?"":height%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">�����</td>
     <td class="xx1"><input type="text" name="volume" readonly="readonly" class="input_readonly" value="<%=volume==0?"":volume%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">x���꣺</td>
     <td class="xx1"><input type="text" name="xcoordinate" readonly="readonly" class="input_readonly" value="<%=xcoordinate==0?"":xcoordinate%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">y���꣺</td>
     <td class="xx1"><input type="text" name="ycoordinate" readonly="readonly" class="input_readonly" value="<%=ycoordinate==0?"":ycoordinate%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">z���꣺</td>
     <td class="xx1"><input type="text" name="zcoordinate" readonly="readonly" class="input_readonly" value="<%=zcoordinate==0?"":zcoordinate%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">������</td>
     <td class="xx1"><input type="text" name="environment" readonly="readonly" class="input_readonly" value="<%=environment==null?"":environment%>"></td>
   </tr><tr>
     <td class="yx1"><div align="right">������</td>
     <td class="xx1"><input type="text" name="layernum" readonly="readonly" class="input_readonly" value="<%=layernum==null?"":layernum%>"></td>
   </tr>
   <tr>
     <td class="yx1"><div align="right">���أ�</td>
     <td class="xx1"><input type="text" name="loadweight" readonly="readonly" class="input_readonly" value="<%=loadweight==0?"":loadweight%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">�ɷ���������</td>
     <td class="xx1"><input type="text" name="palletnum" readonly="readonly" class="input_readonly" value="<%=palletnum==null?"":palletnum%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">�������</td>
     <td class="xx1"><input type="text" name="inlock" readonly="readonly" class="input_readonly" value="<%=inlock==null?"":inlock%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">��������</td>
     <td class="xx1"><input type="text" name="outlock" readonly="readonly" class="input_readonly" value="<%=outlock==null?"":outlock%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">�Ƿ�˫����λ��</td>
     <td class="xx1"><input type="text" name="istwin" readonly="readonly" class="input_readonly" value="<%=istwin==null?"":istwin%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">λ��(˫����λ)��</td>
     <td class="xx1"><input type="text" name="location" readonly="readonly" class="input_readonly" value="<%=location==null?"":location%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">����˫����λ��</td>
     <td class="xx1"><input type="text" name="twincsname" readonly="readonly" class="input_readonly" value="<%=twincsname==null?"":twincsname%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">��λ���ԣ�</td>
     <td class="xx1"><input type="text" name="attributetypename" readonly class="input_readonly" value="<%=attributetypename==null?"":attributetypename%>"></td>
   </tr>
   <tr>
   	 <td height="100%"> </td>
   </tr>
 </table>
</body>
</html>