<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage" %>

<html>
<head>
<title>货位管理-物品列表</title>
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
<!--
	table{
		table-layout:fixed;
	}
	td{
		word-break:keep-all;
		white-space:nowrap;
		overflow:hidden;
		text-overflow:ellipsis;
	} 
-->
</style>
</head>
<body>
<%
	List list = null;
	String isInit = "no";//标识货位是否已经被初始化，默认位没有
	if(request.getAttribute("result") != null)
	{
		list = (List)request.getAttribute("result");
	}
%>
<form name="myform" method="post">
  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr>
      <td class="TD_LIST_TITLE1" width="40%"><div align="center">品名规格</div></td>
      <td class="TD_LIST_TITLE" width="25%"><div align="center">批次信息</div></td>
      <td class="TD_LIST_TITLE" width="15%"><div align="center">数量</div></td>
      <td class="TD_LIST_TITLE2" width="20%"><div align="center">单位</div></td>
    </tr>
   <%
    if(list!=null && !list.isEmpty()){
        for(int i=0; i<list.size(); i++){
        
        	InventoryStorage storage = (InventoryStorage)list.get(i); 
        	
			String productName = storage.getProductName();	//产品名称 品名规格
			String lot = storage.getLotinfo(); 	//批次信息
			int pnum = (int)storage.getPnum(); //数量
            String unit = storage.getPunitname(); //单位
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center"><%=productName==null ? "&nbsp;":productName%></td>
     <td class="TD_LIST" align="center"><%=lot==null ? "&nbsp;":lot%></td>
     <td class="TD_LIST" align="center"><%=pnum%></td>
     <td class="TD_LIST2" align="center"><%=unit==null ? "&nbsp;":unit%></td>
   </tr>
<%
        }
    }
%>
   <tr>
     <td height="100%" colspan="4" class="TD_ADD"></td>
   </tr>
 </table>
<input type="hidden" name="isInit" value="">
</form>
</body>
</html>