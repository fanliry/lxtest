<%@ page contentType="text/html; charset=GB2312"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/table.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/dhtmlXTree.css" rel="stylesheet" type="text/css">
<script  src="<%=request.getContextPath()%>/js/hashMap.js"></script>
<script  src="<%=request.getContextPath()%>/js/dhtmlXCommon.js"></script>
<script  src="<%=request.getContextPath()%>/js/dhtmlXTree.js"></script>	
<script  src="<%=request.getContextPath()%>/js/tools.js"></script>	
 <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/css/dhtmlXTree.css">
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/areaTree.js"></script>

<script>
<!--
	//生成树
	function viewTree()
	{
		//areaTree.getCountryTree(drawTree);
		areaTree.getRegionTree(drawTree);
	}
	function drawTree(data)
	{
		drawDocTree(data);
	}
	
	  function chooseArea()
		{
		  	var areaName=document.getElementById("areaName").value;
		  	if(areaName!="")
		  	{
		    	window.returnValue = areaName;
		    	window.close();
		  	}
		  	else
		  	{
		  	  alert("请选择目的地城市名称");
		  	}
		  }
		  
	function closeWindow()
	{
		window.close();
	}
-->
</script>
</head>
<body onload="javascript:viewTree();">
  <table width="90%" height="85%"  border="1" align="center" cellpadding="0" cellspacing="0" class="MAIN_TABLE">
    <tr>
      <td height="100%" class="TD_LIST">
		<div id="treeboxbox_tree" style="width:100%; height:100%;background-color:#EDF2F9; overflow:auto;"></div>
      </td>
    </tr>
  </table>
  
<script type='text/javascript'>
<!--
		//DWR生成树
		var tree = null;
		function drawDocTree(xmlValue)
		{
			if(tree =='undefined' || tree == null)
			{
				tree=new dhtmlXTreeObject("treeboxbox_tree","100%","100%",0);
				tree.setImagePath("<%=request.getContextPath()%>/images/");
				tree.setOnClickHandler(doOnclick);//添加事件	
			}
			
			xmlValues="<tree id='0'><item text='销售区域' id='1' code='1' im0='magazines_open.gif' im1='magazines_open.gif' im2='magazines_close.gif'><item text='中国' id='country_1' code='1' im0='magazines_open.gif' im1='magazines_open.gif' im2='magazines_close.gif'><item text='湖北省' id='province_1' code='2' im0='books_open.gif' im1='books_open.gif' im2='books_close.gif'></item><item text='浙江省' id='province_2' code='2' im0='books_open.gif' im1='books_open.gif' im2='books_close.gif'></item><item text='江西省' id='province_3' code='2' im0='books_open.gif' im1='books_open.gif' im2='books_close.gif'></item></item><item text='加拿大' id='country_2' code='1' im0='magazines_open.gif' im1='magazines_open.gif' im2='magazines_close.gif'></item></item></tree>";
			tree.loadXMLString(xmlValue);
		}
		
		function myDragHandler(idFrom,idTo){
			return true;
		}
		
	
		//选择节点显示相关信息
		function doOnclick(id)
		{
			var code = tree.getItemCode(id);
			document.getElementById("treeId").value=id;  //点击节点Id
			document.getElementById("parentCode").value=tree.getParentId(id)  //父节点Id
			document.getElementById("code").value=code ;
			document.getElementById("areaName").value=tree.getItemText(id);
		}
	-->
</script>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="13%"><table>
      <tr>
        <td><INPUT type="hidden" id="treeId" name="treeId"/>
            <INPUT type="hidden" id="code" name="code"/>
            <INPUT type="hidden" id="parentCode" name="parentCode"/>
            <input type="hidden" id="areaName" name="areaName">
        </td>
      </tr>
    </table>    
    </td>
    <td width="82%"><input name="button" type="button" id="button" value="确定" class="BUTTON_STYLE1" onclick="chooseArea()">
      <input name="button2" type="button" id="button2" value="取消" class="BUTTON_STYLE1" onclick="closeWindow()"></td>
    <td width="5%">&nbsp;</td>
  </tr>
</table>
</body>
</html>