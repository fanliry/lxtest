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
	//������
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
		  	  alert("��ѡ��Ŀ�ĵس�������");
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
		//DWR������
		var tree = null;
		function drawDocTree(xmlValue)
		{
			if(tree =='undefined' || tree == null)
			{
				tree=new dhtmlXTreeObject("treeboxbox_tree","100%","100%",0);
				tree.setImagePath("<%=request.getContextPath()%>/images/");
				tree.setOnClickHandler(doOnclick);//����¼�	
			}
			
			xmlValues="<tree id='0'><item text='��������' id='1' code='1' im0='magazines_open.gif' im1='magazines_open.gif' im2='magazines_close.gif'><item text='�й�' id='country_1' code='1' im0='magazines_open.gif' im1='magazines_open.gif' im2='magazines_close.gif'><item text='����ʡ' id='province_1' code='2' im0='books_open.gif' im1='books_open.gif' im2='books_close.gif'></item><item text='�㽭ʡ' id='province_2' code='2' im0='books_open.gif' im1='books_open.gif' im2='books_close.gif'></item><item text='����ʡ' id='province_3' code='2' im0='books_open.gif' im1='books_open.gif' im2='books_close.gif'></item></item><item text='���ô�' id='country_2' code='1' im0='magazines_open.gif' im1='magazines_open.gif' im2='magazines_close.gif'></item></item></tree>";
			tree.loadXMLString(xmlValue);
		}
		
		function myDragHandler(idFrom,idTo){
			return true;
		}
		
	
		//ѡ��ڵ���ʾ�����Ϣ
		function doOnclick(id)
		{
			var code = tree.getItemCode(id);
			document.getElementById("treeId").value=id;  //����ڵ�Id
			document.getElementById("parentCode").value=tree.getParentId(id)  //���ڵ�Id
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
    <td width="82%"><input name="button" type="button" id="button" value="ȷ��" class="BUTTON_STYLE1" onclick="chooseArea()">
      <input name="button2" type="button" id="button2" value="ȡ��" class="BUTTON_STYLE1" onclick="closeWindow()"></td>
    <td width="5%">&nbsp;</td>
  </tr>
</table>
</body>
</html>