<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseCargospace" %>
<%
	BaseCargospace cargoSpace = (BaseCargospace)request.getAttribute("cargoSpace");
	
	String cargoSpaceId = cargoSpace.getCargoSpaceId();			// 货位ID
	String cargoSpaceName = cargoSpace.getCargoSpaceName();		// 货位名称
	String csstatusname = cargoSpace.getCsstatusname();		    // 货位状态名称
	//int csplatoon = cargoSpace.getCsplatoon();				// 货位排
	//int cscolumn = cargoSpace.getCscolumn();					// 货位列
	//int csfloor = cargoSpace.getCsfloor();					// 货位层
	String cstypename = cargoSpace.getCstypename();				// 货位类型名称
	String inlock = cargoSpace.getInlock();						// 入库锁 Y.是 N.否
	String outlock = cargoSpace.getOutlock();					// 出库锁 Y.是 N.否
	String cargoAlleyName = cargoSpace.getCargoAlleyName();		// 巷道名称
	String cargoAreaName = cargoSpace.getCargoAreaName();		// 货区名称
	String warehousename = cargoSpace.getWarehousename();		// 仓库名称
	String whAreaName = cargoSpace.getWhAreaName();				// 库区名称
	String storagetypename = cargoSpace.getStoragetypename();	// 存储类型名称
	String puttypename = cargoSpace.getPuttypename();			// 上架类型名称
	String picktypename = cargoSpace.getPicktypename();			// 拣选类型名称
	double length = cargoSpace.getLength();						// 长
	double width = cargoSpace.getWidth();						// 宽
	double height = cargoSpace.getHeight();						// 高
	double volume = cargoSpace.getVolume();						// 体积
	double xcoordinate = cargoSpace.getXcoordinate();			// x坐标
	double ycoordinate = cargoSpace.getYcoordinate();			// y坐标
	double zcoordinate = cargoSpace.getZcoordinate();			// z坐标
	String environment = cargoSpace.getEnvironment();			// 环境
	Integer layernum = cargoSpace.getLayernum();				// 层数
	double loadweight = cargoSpace.getLoadweight();				// 承重
	Integer palletnum = cargoSpace.getPalletnum();				// 可放托盘数
	
	String istwin = cargoSpace.getIstwin();						// 是否双升货位 Y.是 N.否
	String location = "";										// 位置：1：里面；2：外面
	String twincsname = "";										// 相邻双升货位
	if(istwin!=null && istwin.equals("Y")){
		location = cargoSpace.getLocation().equals("1")?"里":"外";
		twincsname = cargoSpace.getTwincsname();				
	}
	
	String usagetypename = cargoSpace.getUsagetypename();   	// 库位使用名称
	String attributetypename = cargoSpace.getAttributetypename();   // 库位属性名称
	String demandtypename = cargoSpace.getDemandtypename();   	// 周转需求名称
%>
<html>
<head>
<title>货位管理-货位信息</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
  <table width="100%" height="100%" border="0" align="left" cellpadding="0" cellspacing="0" class="table_add">
   <tr>
	 <td colspan="2" style="height:25px;" class="TD_LIST_TITLE4">[<%=cargoSpaceId%>]<%=cargoSpaceName%></td>
    </tr>
   <!--tr>
     <td class="TD_ADD"><div align="right">货位代码：</td>
     <td class="TD_ADD">
     <input type="text" name="warehouseNo" readonly="readonly" class="input_readonly" value="<%=cargoSpaceId%>"></td>
   </tr>
   <tr>
     <td class="TD_ADD"><div align="right">货位名称：</td>
     <td class="TD_ADD">
     <input type="text" name="typeCode" readonly="readonly" class="input_readonly" value="<%=cargoSpaceName%>"></td>
   </tr-->
   <tr>
     <td width="120px" class="yx1" align="right">所属仓库：</td>
     <td class="xx1"><input type="text" name="warehousename" readonly="readonly" class="input_readonly" value="<%=warehousename%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">所属库区：</td>
     <td class="xx1"><input type="text" name="whAreaName" readonly="readonly" class="input_readonly" value="<%=whAreaName%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">所属巷道：</td>
     <td class="xx1"><input type="text" name="cargoAlleyName" readonly="readonly" class="input_readonly" value="<%=cargoAlleyName==null?"":cargoAlleyName%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">所属货区：</td>
     <td class="xx1"><input type="text" name="cargoAreaName" readonly="readonly" class="input_readonly" value="<%=cargoAreaName==null?"":cargoAreaName%>"></td>
   </tr>
   <!--tr>
     <td class="TD_ADD"><div align="right">排：</td>
     <td class="TD_ADD">
     <input type="text" name="mgrMan" readonly="readonly" class="input_readonly" value="<%=cargoAreaName%>"></td>
   </tr>
   <tr>
     <td class="TD_ADD"><div align="right">列：</td>
     <td class="TD_ADD">
     <input type="text" name="mgrMan" readonly="readonly" class="input_readonly" value="<%=cargoAreaName%>"></td>
   </tr>
   <tr>
     <td class="TD_ADD"><div align="right">层：</td>
     <td class="TD_ADD">
     <input type="text" name="mgrMan" readonly="readonly" class="input_readonly" value="<%=cargoAreaName%>"></td>
   </tr-->
   <tr>
     <td class="yx1" align="right">货位状态：</td>
     <td class="xx1"><input type="text" name="csstatus" readonly="readonly" class="input_readonly"  value="<%=csstatusname%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">货位类型：</td>
     <td class="xx1"><input type="text" name="cstype" readonly="readonly" class="input_readonly" value="<%=cstypename==null?"":cstypename%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">存储类型：</td>
     <td class="xx1"><input type="text" name="storagetype" readonly="readonly" class="input_readonly" value="<%=storagetypename==null?"":storagetypename%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">上架类型：</td>
     <td class="xx1"><input type="text" name="puttype" readonly="readonly" class="input_readonly" value="<%=puttypename==null?"":puttypename%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">拣选类型：</td>
     <td class="xx1"><input type="text" name="picktype" readonly="readonly" class="input_readonly" value="<%=picktypename==null?"":picktypename%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right"><div align="right">长：</td>
     <td class="xx1"><input type="text" name="length" readonly="readonly" class="input_readonly" value="<%=length==0?"":length%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">宽：</td>
     <td class="xx1"><input type="text" name="width" readonly="readonly" class="input_readonly" value="<%=width==0?"":width%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">高：</td>
     <td class="xx1"><input type="text" name="height" readonly="readonly" class="input_readonly" value="<%=height==0?"":height%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">体积：</td>
     <td class="xx1"><input type="text" name="volume" readonly="readonly" class="input_readonly" value="<%=volume==0?"":volume%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">x坐标：</td>
     <td class="xx1"><input type="text" name="xcoordinate" readonly="readonly" class="input_readonly" value="<%=xcoordinate==0?"":xcoordinate%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">y坐标：</td>
     <td class="xx1"><input type="text" name="ycoordinate" readonly="readonly" class="input_readonly" value="<%=ycoordinate==0?"":ycoordinate%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">z坐标：</td>
     <td class="xx1"><input type="text" name="zcoordinate" readonly="readonly" class="input_readonly" value="<%=zcoordinate==0?"":zcoordinate%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">环境：</td>
     <td class="xx1"><input type="text" name="environment" readonly="readonly" class="input_readonly" value="<%=environment==null?"":environment%>"></td>
   </tr><tr>
     <td class="yx1"><div align="right">层数：</td>
     <td class="xx1"><input type="text" name="layernum" readonly="readonly" class="input_readonly" value="<%=layernum==null?"":layernum%>"></td>
   </tr>
   <tr>
     <td class="yx1"><div align="right">承重：</td>
     <td class="xx1"><input type="text" name="loadweight" readonly="readonly" class="input_readonly" value="<%=loadweight==0?"":loadweight%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">可放托盘数：</td>
     <td class="xx1"><input type="text" name="palletnum" readonly="readonly" class="input_readonly" value="<%=palletnum==null?"":palletnum%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">入库锁：</td>
     <td class="xx1"><input type="text" name="inlock" readonly="readonly" class="input_readonly" value="<%=inlock==null?"":inlock%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">出库锁：</td>
     <td class="xx1"><input type="text" name="outlock" readonly="readonly" class="input_readonly" value="<%=outlock==null?"":outlock%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">是否双升货位：</td>
     <td class="xx1"><input type="text" name="istwin" readonly="readonly" class="input_readonly" value="<%=istwin==null?"":istwin%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">位置(双升货位)：</td>
     <td class="xx1"><input type="text" name="location" readonly="readonly" class="input_readonly" value="<%=location==null?"":location%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">相邻双升货位：</td>
     <td class="xx1"><input type="text" name="twincsname" readonly="readonly" class="input_readonly" value="<%=twincsname==null?"":twincsname%>"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">库位属性：</td>
     <td class="xx1"><input type="text" name="attributetypename" readonly class="input_readonly" value="<%=attributetypename==null?"":attributetypename%>"></td>
   </tr>
   <tr>
   	 <td height="100%"> </td>
   </tr>
 </table>
</body>
</html>