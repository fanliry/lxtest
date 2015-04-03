<%@ page contentType="text/html; charset=GBK"%>
<%
	String id = request.getParameter("id");		//库区ID
%>
<html>
<head>
<title>货位自动生成</title>
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
		var whAreaId = '<%=id%>';
		var intPlatoon = document.getElementById("intPlatoon").value;
		var intColumn = document.getElementById("intColumn").value;
		var intfloor = document.getElementById("intfloor").value;	
		var cstype = document.getElementById("cstype").value;
		var storagetype = document.getElementById("storagetype").value;
		var puttype = "";
		var picktype = "";
		var usagetype = "";			//库位使用
		var attributetype = document.getElementById("attributetype").value;	//库位属性
		var demandtype = "";		//周转需求
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
	//数据检验
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
		
		//排
		if(intPlatoon == null || intPlatoon.length <1)
		{
			alert("【排】不能为空!");
			return false;
		}else{
			if(!numChar(intPlatoon) || intPlatoon<=0){
				alert("【排】必须是大于零的数字!");
				return false;
			}
		}
		
		//列
		if(intColumn == null || intColumn.length <1)
		{
			alert("【列】不能为空!");
			return false;
		}else{
			if(!numChar(intColumn) || intColumn<=0){
				alert("【列】必须是大于零的数字!");
				return false;
			}
		}
		
		//层
		if(intfloor == null || intfloor.length <1)
		{
			alert("【层】不能为空!");
			return false;
		}else{
			if(!numChar(intfloor) || intfloor<=0){
				alert("【层】必须是大于零的数字!");
				return false;
			}
		}
		
		//货位类型
		if(cstype == null || cstype.length <1)
		{
			alert("【货位类型】不能为空!");
			return false;
		}
		
		//存储类型
		if(storagetype == null || storagetype.length <1)
		{
			alert("【存储类型】不能为空!");
			return false;
		}
		
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

		selectType("", "cstype", "hwlx");			//货位类型
		selectType("", "storagetype", "cclx");		//存储类型
		selectType("", "attributetype", "kwsx");	//库位属性
	
		//获取所属货区、仓库信息
		getParentInfo('<%=id%>');
	}
-->
</script>
</head>

<body onload="OnLoad()">
<form name="myForm" method="post" action="">
  <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25"><div class="font_001F56_bold_12">当前位置：基本信息 -&gt; 仓库管理 -&gt; 新增货位信息</div></td>
    </tr>
  </table>

  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_add">
    <tr>
      <td width="100px" class="yx1" align="right">所属仓库：</td>
      <td class="yx"><input type="text" readonly="readonly" class="input_readonly" name="warehouseName" size="16"></td>
      <td width="100px" class="yx1" align="right">所属库区：</td>
      <td class="xx1"><input type="text" readonly="readonly" class="input_readonly" name="whAreaName" size="16"></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>排：</td>
      <td class="yx"><input type="text" name="intPlatoon" size="4" maxlength="2"></td>
      <td class="yx1" align="right"><span class="red">*</span>列：</td>
      <td class="xx1"><input type="text" name="intColumn" size="4" maxlength="2"></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>层：</td>
      <td class="xx1" colspan="3"><input type="text" name="intfloor" size="4" maxlength="2"></td> 
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>货位类型：</td>
      <td class="yx"><select name="cstype" style="width:110px;"><option value=""></option></select></td>
      <td class="yx1" align="right"><span class="red">*</span>存储类型：</td>
      <td class="xx1"><select name="storagetype" style="width:110px;"><option value=""></option></select></td>
    </tr>
    <tr>
      <td class="yx1" align="right"  colspan="3">库位属性：</td>
      <td class="xx1"><select name="attributetype" style="width:110px;"><option value=""></option></select></td>
    </tr>
   <tr>
     <td class="yx1" align="right">长：</td>
     <td class="yx"><input type="text" name="length" size="10" maxlength="10" value="0.00"></td>
	 <td class="yx1" align="right">x坐标：</td>
     <td class="xx1"><input type="text" name="xcoordinate" size="10" maxlength="10" value="0.00"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">宽：</td>
     <td class="yx"><input type="text" name="width" size="10" maxlength="10" value="0.00"></td>
     <td class="yx1" align="right">y坐标：</td>
     <td class="xx1"><input type="text" name="ycoordinate" size="10" maxlength="10" value="0.00"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">高：</td>
     <td class="yx"><input type="text" name="height" size="10" maxlength="10" value="0.00"></td> 
     <td class="yx1" align="right">z坐标：</td>
     <td class="xx1"><input type="text" name="zcoordinate" size="10" maxlength="10" value="0.00"></td> 
   </tr>
   <tr>
     <td class="yx1" align="right">体积：</td>
     <td class="yx"><input type="text" name="volume" size="10" maxlength="10" value="0.00"></td> 
     <td class="yx1" align="right">层数：</td>
     <td class="xx1"><input type="text" name="layernum" size="4" maxlength="1" value="1"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">承重：</td>
     <td class="yx"><input type="text" name="loadweight" size="10" maxlength="10" value="0.00"></td> 
     <td class="yx1" align="right">可放托盘数：</td>
     <td class="xx1"><input type="text" name="palletnum" size="4" maxlength="1" value="1"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">环境：</td>
     <td class="xx1" colspan="3"><input type="text" name="environment" size="30" maxlength="25" value=""></td>
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