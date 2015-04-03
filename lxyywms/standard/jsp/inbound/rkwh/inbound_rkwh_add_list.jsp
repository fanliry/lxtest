<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>作业详细列表</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script>


</script>

<body>
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
 <table id="tb" width="140%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   <tr>
     <td class="TD_LIST_TITLE1"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">货主</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">包装</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">单位</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">批次属性1</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">批次属性2</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">批次属性3</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">批次属性4</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">批次属性5</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">批次属性6</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">批次属性7</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">批次属性8</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">批次属性9</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">批次属性10</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">批次属性11</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">批次属性12</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">数量</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">体积</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">毛重</div></td>
	 <td class="TD_LIST_TITLE"><div class="list_title">净重</div></td>
	 <td class="TD_LIST_TITLE2"><div class="list_title">操作</div></td>
   </tr>
   </thead>  
   <tbody> 
   <tr>
     <td height="100%" colspan="<%=21%>" class="TD_last_LIST"></td>
   </tr>
   </tbody> 
 </table>
</div>
</body>
</html>
