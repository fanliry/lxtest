<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/rico-tag" prefix="rico" %>
<html>
<head>
<title>货位管理-左边树</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
<!--
	.img1{
		border:0;
	}
	.wenzi{
		font-size:12px;
		color:#006699;
	}
	A.a1:visited {
		font-size:12px;
		color:#006699;
		text-decoration:none;
	}
	A.a1:hover {
		font-size:12px;
		color:#0030FF;
		text-decoration:none;
	}
	A.a1:link {
		font-size:12px;
		color:#006699;
		text-decoration:none;
	}
-->
</style>

<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/cargoSpaceTree.js"></script>
<script>
<!--
	
-->
</script>
</head>

<body>
  <table width="100%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td height="100%">
		<div id="treeboxbox_tree" style="width:100%; height:100%; background-color:#EDF2F9; overflow:auto; border: 1px solid #99CCFF;">
		
		<rico:tree 
					target="rightIframe"
					js="/standard/js/ajaxTree.js"
					rootId="0"
					blueClass="com.wms3.bms.standard.action.base.ajaxTree.CargoSpaceTreeView"
					imageURL="/standard/images/"
					linkClass="a1"
					styleClass="wenzi"
					imgClass="img1"
					isUseDataURL="true"
					/>
		
		</div>
		
		<input id="hiddenId" name="hiddenId" type="hidden">
		
		<input id="hiddenLevel" name="hiddenLevel" type="hidden">
 		<input id="hiddenName" name="hiddenName" type="hidden">
 		
 		<input id="hiddenNo" name="hiddenNo" type="hidden">
 		<input id="hiddenParentName" name="hiddenParentName" type="hidden">
 		<input id="hiddenPParentName" name="hiddenPParentName" type="hidden">
      </td>
    </tr>
  </table>
</body>
</html>