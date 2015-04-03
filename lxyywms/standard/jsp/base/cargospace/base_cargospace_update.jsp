<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.base.BaseCargospace"%>
<%
	BaseCargospace cargoSpace = (BaseCargospace)request.getAttribute("cargoSpace");  
	
	String cargoSpaceId = cargoSpace.getCargoSpaceId();		// 货位ID
	String cargoSpaceName = cargoSpace.getCargoSpaceName();	// 货位名称
	String cargoAlleyName = cargoSpace.getCargoAlleyName();	// 巷道名称
	String warehousename = cargoSpace.getWarehousename();	// 仓库名称
	String whAreaName = cargoSpace.getWhAreaName();			// 库区名称
	String warehouseid = cargoSpace.getWarehouseid();		// 仓库ID
	String cargoAreaId = cargoSpace.getCargoAreaId();		// 货区ID
	//String csstatus = cargoSpace.getCsstatus();			// 货位状态
	//Integer csplatoon = cargoSpace.getCsplatoon();	// 货位排
	//Integer cscolumn = cargoSpace.getCscolumn();		// 货位列
	//Integer csfloor = cargoSpace.getCsfloor();		// 货位层
	String inlock = cargoSpace.getInlock();				// 入库锁 Y.是 N.否
	String outlock = cargoSpace.getOutlock();			// 出库锁 Y.是 N.否
	String cstype = cargoSpace.getCstype();				// 货位类型
	String storagetype = cargoSpace.getStoragetype();	// 存储类型
	String puttype = cargoSpace.getPuttype();			// 上架类型
	String picktype = cargoSpace.getPicktype();			// 拣选类型
	String usagetype = cargoSpace.getUsagetype();   	// 库位使用
	String attributetype = cargoSpace.getAttributetype(); // 库位属性
	String demandtype = cargoSpace.getDemandtype();   	// 周转需求
	double length = cargoSpace.getLength();				// 长
	double width = cargoSpace.getWidth();				// 宽
	double height = cargoSpace.getHeight();				// 高
	double volume = cargoSpace.getVolume();				// 体积
	double xcoordinate = cargoSpace.getXcoordinate();	// x坐标
	double ycoordinate = cargoSpace.getYcoordinate();	// y坐标
	double zcoordinate = cargoSpace.getZcoordinate();	// z坐标
	String environment = cargoSpace.getEnvironment();	// 环境
	Integer layernum = cargoSpace.getLayernum();		// 层数
	double loadweight = cargoSpace.getLoadweight();		// 承重
	Integer palletnum = cargoSpace.getPalletnum();		// 可放托盘数
	
%>
<html>
<head>
<title>货位修改</title>
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
	//获取所属货区、仓库信息
	function getParentInfo(strId){
	
		document.getElementById("warehouseName").value = "加载中……";   
		document.getElementById("whAreaName").value = "加载中……";
		
		cargoSpaceTree.getParentInfo(strId , '0', 
			function(data){
				document.getElementById("warehouseName").value = data[0];   
				document.getElementById("whAreaName").value = data[1];
	    	}
	    )
	}
	//保存货位
	function saveData(){ 	
		var cargoSpaceId = document.getElementById("cargoSpaceId").value;	// 货位ID
		var cargoAreaId = document.getElementById("cargoAreaId").value;		// 货区ID
		var inlock = document.getElementById("inlock").checked?"Y":"N";		// 入库锁 Y.是 N.否
		var outlock = document.getElementById("outlock").checked?"Y":"N";	// 出库锁 Y.是 N.否
		var cstype = document.getElementById("cstype").value;				// 货位类型
		var storagetype = document.getElementById("storagetype").value;		// 存储类型
		var puttype = document.getElementById("puttype").value;				// 上架类型
		var picktype = document.getElementById("picktype").value;			// 拣选类型
		var usagetype = document.getElementById("usagetype").value;			// 库位使用
		var attributetype = document.getElementById("attributetype").value;	// 库位属性
		var demandtype = document.getElementById("demandtype").value;		// 周转需求
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
	//数据检验
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
		
		//长
		if(length != null )
		{
			if(!isDig(length) || length<0){
				alert("【长】必须是大于零的数字!");
				return false;
			}
		}
		
		//宽
		if(width != null )
		{
			if(!isDig(width) || width<0){
				alert("【宽】必须是大于零的数字!");
				return false;
			}
		}
		
		//高
		if(height != null )
		{
			if(!isDig(height) || height<0){
				alert("【高】必须是大于零的数字!");
				return false;
			}
		}
		
		//体积
		if(volume != null )
		{
			if(!isDig(volume) || volume<0){
				alert("【体积】必须是大于零的数字!");
				return false;
			}
		}
		
		//x坐标
		if(xcoordinate != null )
		{
			if(!isDig(xcoordinate) || xcoordinate<0){
				alert("【x坐标】必须是大于零的数字!");
				return false;
			}
		}
		
		//y坐标
		if(ycoordinate != null )
		{
			if(!isDig(ycoordinate) || ycoordinate<0){
				alert("【y坐标】必须是大于零的数字!");
				return false;
			}
		}
		
		//z坐标
		if(zcoordinate != null )
		{
			if(!isDig(zcoordinate) || zcoordinate<0){
				alert("【z坐标】必须是大于零的数字!");
				return false;
			}
		}
		
		//承重
		if(loadweight != null )
		{
			if(!isDig(loadweight) || loadweight<0){
				alert("【承重】必须是大于零的数字!");
				return false;
			}
		}
		
		//层数
		if(layernum != null )
		{
			if(!numChar(layernum) || layernum<0){
				alert("【层数】必须是大于零的数字!");
				return false;
			}
		}
		
		//可放托盘数
		if(palletnum != null )
		{
			if(!numChar(palletnum) || palletnum<0){
				alert("【可放托盘数】必须是大于零的数字!");
				return false;
			}
		}
	}
	
	function OnLoad(){

		selectType("<%=cstype%>", "cstype", "hwlx");				//货位类型
		selectType("<%=storagetype%>", "storagetype", "cclx");		//存储类型
		selectType("<%=puttype%>", "puttype", "sjlx");				//上架类型
		selectType("<%=picktype%>", "picktype", "jxlx");			//拣选类型
		selectType("<%=usagetype%>", "usagetype", "kwsy");			//库位使用
		selectType("<%=attributetype%>", "attributetype", "kwsx");	//库位属性
		selectType("<%=demandtype%>", "demandtype", "zzxq");		//周转需求
		
		selectAreaList("<%=cargoAreaId%>", "cargoAreaId", "<%=warehouseid%>", "2");	//货区
	}
-->
</script>
</head>

<body onload="OnLoad()">
<form name="myForm" method="post" action="">
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">当前位置：基本信息 -&gt; 仓库管理 -&gt; 修改货位信息</div></td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right">货位ID：</td>
      <td class="yx"><input type="text" readonly class="input_readonly" name="cargoSpaceId" value="<%=cargoSpaceId%>"></td>
      <td width="100px" class="yx1" align="right">货位名称：</td>
      <td class="xx1"><input type="text" readonly class="input_readonly" name="cargoSpaceName" value="<%=cargoSpaceName%>"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">所属仓库：</td>
      <td class="yx" ><input type="text" readonly class="input_readonly" name="warehousename" value="<%=warehousename%>"></td>
      <td class="yx1" align="right">所属库区：</td>
      <td class="xx1"><input type="text" readonly class="input_readonly" name="whAreaName" value="<%=whAreaName%>"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">所属巷道：</td>
      <td class="yx"><input type="text" readonly class="input_readonly" name="cargoAlleyName" value="<%=cargoAlleyName==null?"":cargoAlleyName%>"></td>
      <td class="yx1" align="right">所属货区：</td>
      <td class="xx1"><select name="cargoAreaId"><option value=""></option></select></td>
    </tr>
    <tr>
      <td class="yx1" align="right">入库锁：</td>
      <td class="yx"><input type="checkbox" name="inlock" <%if(inlock.equals("Y")){%>checked<%}%>></td>
      <td class="yx1" align="right">出库锁：</td>
      <td class="xx1"><input type="checkbox" name="outlock" <%if(outlock.equals("Y")){%>checked<%}%>></td>
    </tr>
    <tr>
      <td class="yx1" align="right">货位类型：</td>
      <td class="yx"><select name="cstype" style="width:110px;"><option value=""></option></select></td>
      <td class="yx1" align="right">存储类型：</td>
      <td class="xx1"><select name="storagetype" style="width:110px;"><option value=""></option></select></td>
    </tr>
    <tr>
      <td class="yx1" align="right">上架类型：</td>
      <td class="yx"><select name="puttype" style="width:110px;"><option value=""></option></select></td>
      <td class="yx1" align="right">拣选类型：</td>
      <td class="xx1"><select name="picktype" style="width:110px;"><option value=""></option></select></td>
    </tr>    
    <tr>
      <td class="yx1" align="right">库位使用：</td>
      <td class="yx"><select name="usagetype" style="width:110px;"><option value=""></option></select></td>
      <td class="yx1" align="right">库位属性：</td>
      <td class="xx1"><select name="attributetype" style="width:110px;"><option value=""></option></select></td>
    </tr>
    <tr>
      <td class="yx1" align="right">周转需求：</td>
      <td class="xx1" colspan="3"><select name="demandtype" style="width:110px;"><option value=""></option></select></td>
    </tr>
    <tr>
      <td class="yx1" align="right">长：</td>
      <td class="yx"><input type="text" name="length" size="10" maxlength="10" value="<%=length%>"></td>
      <td class="yx1" align="right">x坐标：</td>
      <td class="xx1"><input type="text" name="xcoordinate" size="10" maxlength="10" value="<%=xcoordinate%>"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">宽：</td>
      <td class="yx"><input type="text" name="width" size="10" maxlength="10" value="<%=width%>"></td>
      <td class="yx1" align="right">y坐标：</td>
      <td class="xx1"><input type="text" name="ycoordinate" size="10" maxlength="10" value="<%=ycoordinate%>"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">高：</td>
      <td class="yx"><input type="text" name="height" size="10" maxlength="10" value="<%=height%>"></td> 
      <td class="yx1" align="right">z坐标：</td>
      <td class="xx1"><input type="text" name="zcoordinate" size="10" maxlength="10" value="<%=zcoordinate%>"></td> 
    </tr>
    <tr>
      <td class="yx1" align="right">体积：</td>
      <td class="yx"><input type="text" name="volume" size="10" maxlength="10" value="<%=volume%>"></td> 
      <td class="yx1" align="right">层数：</td>
      <td class="xx1"><input type="text" name="layernum" size="6" maxlength="1" value="<%=layernum==null?0:layernum%>"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">承重：</td>
      <td class="yx"><input type="text" name="loadweight" size="10" maxlength="10" value="<%=loadweight%>"></td> 
      <td class="yx1" align="right">可放托盘数：</td>
      <td class="xx1"><input type="text" name="palletnum" size="6" maxlength="1" value="<%=palletnum==null?0:palletnum%>"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">环境：</td>
      <td class="xx1" colspan="3"><input type="text" name="environment" size="25" maxlength="25" value="<%=environment==null?"":palletnum%>"></td>
    </tr>
    <tr>
      <td height="27" colspan="6" align="center">
        <input type="button" onclick="if(checkData()!=false){saveData();}" name="add" value="&nbsp;&nbsp;&nbsp;保存" class="button_add">&nbsp; 
        <input type="button" onClick="window.close()" name="resetBtn" value="&nbsp;&nbsp;&nbsp;取消" class="button_reset">
      </td>
    </tr>
  </table>
</form>
</body>
</html>