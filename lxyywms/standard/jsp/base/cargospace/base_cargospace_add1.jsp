<%@ page contentType="text/html; charset=GBK"%>
<%
	String id = request.getParameter("id");					//巷道ID
	String isAlleytwin = request.getParameter("istwin");	//是否双升货位巷道
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
		document.getElementById("cargoAlleyName").value = "加载中……";
		
		cargoSpaceTree.getParentInfo(strId , '1', 
			function(data){
				document.getElementById("warehouseName").value = data[0];   
				document.getElementById("whAreaName").value = data[1];
		    	document.getElementById("cargoAlleyName").value = data[2];
	    	}
	    )
	}
	//保存货位
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
	//数据检验
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
			alert("【货区起始排】不能为空!");
			return false;
		}else{
			if(!numChar(minPlatoon) || minPlatoon==0){
				alert("【货区起始排】必须是大于0的数字!");
				return false;
			}
		}
		if(maxPlatoon == null || maxPlatoon.length <1)
		{
			alert("【货区终止排】不能为空!");
			return false;
		}else{
			if(!numChar(maxPlatoon) || maxPlatoon==0){
				alert("【货区终止排】必须是大于0的数字!");
				return false;
			}else{
				if(parseInt(minPlatoon)>parseInt(maxPlatoon)){
					alert("【起始排】不能大于【终止排】！");
					return false;
				}else{
					//双升货位巷道(必须4排一起初始化)
					if("<%=isAlleytwin%>" == "Y"){	
						if(parseInt(maxPlatoon)-parseInt(minPlatoon)!=3){
							alert("一个货区有四排货位,请同时初始化！");
							return false;
						}
					
					}else{
						if(parseInt(maxPlatoon)-parseInt(minPlatoon)>1){
							alert("一个货区只有两排货位！");
							return false;
						}
					}
				}
			}
		}
		if(minColumn == null || minColumn.length <1)
		{
			alert("【货区起始列】不能为空!");
			return false;
		}else{
			if(!numChar(minColumn) || minColumn==0){
				alert("【货区起始列】必须是大于0的数字!");
				return false;
			}
		}
		if(maxColumn == null || maxColumn.length <1)
		{
			alert("【货区终止列】不能为空!");
			return false;
		}else{
			if(!numChar(maxColumn) || maxColumn==0){
				alert("【货区终止列】必须是大于0的数字!");
				return false;
			}else{
				if(parseInt(minColumn)>parseInt(maxColumn)){
					alert("【起始列】不能大于【终止列】！");
					return false;
				}
			}
		}
		if(floorNum == null || floorNum.length <1)
		{
			alert("【仓库层数】不能为空!");
			return false;
		}else{
			if(!numChar(floorNum) || floorNum==0){
				alert("【仓库层数】必须是大于0的数字!");
				return false;
			}else{
				if(floorNum<=0){
					alert("【仓库层数】必须大于零!");
					return false;
				}
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
	}
	
	function OnLoad(){

		selectType("1", "cstype", "hwlx");		//货位类型
		selectType("", "storagetype", "cclx");	//存储类型
		
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
      <td class="yx"><input type="text" readonly="readonly" class="input_readonly" name="warehouseName" size="15"></td>
      <td width="100px" class="yx1" align="right">所属库区：</td>
      <td class="xx1"><input type="text" readonly="readonly" class="input_readonly" name="whAreaName" size="15"></td>
    </tr>
    <tr>
      <td class="yx1" align="right">所属巷道：</td>
      <td class="xx1" colspan="3"><input type="text" readonly="readonly" class="input_readonly" name="cargoAlleyName" size="15"></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>巷道排从：</td>
      <td class="yx">
        <input type="text" name="minPlatoon" size="4" maxlength="2">&nbsp;到：<input type="text" name="maxPlatoon" size="4" maxlength="2"></td>
      <td class="yx1" align="right"><span class="red">*</span>巷道列从：</td>
      <td class="xx1">
        <input type="text" name="minColumn" size="4" maxlength="2">&nbsp;到：<input type="text" name="maxColumn" size="4" maxlength="2"></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>仓库层数：</td>
      <td class="xx1" colspan="3"><input type="text" name="floorNum" size="4" maxlength="2"></td>
    </tr>
    <tr>
      <td class="yx1" align="right"><span class="red">*</span>货位类型：</td>
      <td class="yx"><select name="cstype" style="width:100px;"><option value=""></option></select></td>
      <td class="yx1" align="right"><span class="red">*</span>存储类型：</td>
      <td class="xx1"><select name="storagetype" style="width:100px;"><option value=""></option></select></td>
    </tr>
   <tr>
     <td class="yx1" align="right">长：</td>
     <td class="yx"><input type="text" name="length" size="10" maxlength="10" value="0.00"></td>
     <td class="yx1" align="right">宽：</td>
     <td class="xx1"><input type="text" name="width" size="10" maxlength="10" value="0.00"></td>
   </tr>
   <tr>
     <td class="yx1" align="right">高：</td>
     <td class="xx1" colspan="3"><input type="text" name="height" size="10" maxlength="10" value="0.00"></td> 
   </tr>
   <tr>
     <td class="yx1" align="right">体积：</td>
     <td class="yx"><input type="text" name="volume" size="10" maxlength="10" value="0.00"></td> 
     <td class="yx1" align="right">承重：</td>
     <td class="xx1"><input type="text" name="loadweight" size="10" maxlength="10" value="0.00"></td> 
   </tr>
   <tr>
     <td class="yx1" align="right">层数：</td>
     <td class="xx1" colspan="3"><input type="text" name="layernum" size="4" maxlength="1" value="1"></td>
   </tr>
    <tr>
      <td height="27" colspan="4" align="center">
        <input type="button" onclick="if(checkData()!=false){saveData();}" name="add" value="&nbsp;&nbsp;&nbsp;保存" class="button_add">&nbsp; 
        <input type="button" onClick="window.close()" name="resetBtn" value="&nbsp;&nbsp;&nbsp;取消" class="button_reset">
     </td>
    </tr>
  </table>
  </form>
  
</body>
</html>