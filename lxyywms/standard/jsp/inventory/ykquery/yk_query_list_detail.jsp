<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryMovementHeader" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryMovementDetail" %>
<%@page import="java.util.HashMap"%>
<%@page import="com.wms3.bms.standard.entity.base.BaseLotSet"%>
<%@page import="java.lang.reflect.Field"%>
<%@page import="java.util.ArrayList"%>
<%
   
    //表中的列排序
	List ls = (List)request.getAttribute("exResultList");
	int len = 0;
	if(ls!=null && ls.size()>0){
	  len = ls.size();
	}
%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/sort.js"></script> 
<script type="text/javascript">
<!--
	function OnLoad(){
		parent.RemoveLoading();
		parent.page.location.reload();
		
		//表中的列排序
		var iflag = parseInt("<%=len%>")+1;
		if(iflag == "0"){
		}else{
		   new tableSort('theTable',1,2,iflag,'TD_LIST_TITLE','TD_LIST_TITLE','TD_LIST_TITLE');
		}
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
  <table width="100%" id="theTable" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
   	
     <td class="TD_LIST_TITLE"><div class="list_title">行号</div></td>
    
     <td class="TD_LIST_TITLE"><div class="list_title">产品编码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">生产日期</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">批号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">托盘号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">单位</div></td>
     
     
     
     <td class="TD_LIST_TITLE"><div class="list_title">FM库区</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">FM货位</div></td>
  
     <td class="TD_LIST_TITLE"><div class="list_title">TO库区</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">TO货位</div></td>
     
     
   </tr>
<%
	
	if(ls != null && ls.size() > 0){
		InventoryMovementDetail iDetail = null;
		String strWarehouseName = null;	//仓库名称
		String strProductCode = null; 	//产品编号
		String strProductName = null; 	//品名
		String strProductDate = null;	//生产日期
		String strLotNum = null;	//批号
		String strTrayNum = null;	//托盘号
		String strPunit = null; //单位
		String strCreateTime = null;	//制单时间
		String strFromWhare = null;	//FM库区
		String strFromCargospace = null; //FM货位
		
		String strToWhare = null;	//to货区
		String strToCargospace = null; //to货位
		String strCreateMan = null; 	//制单人
		
		
		for(int i = 0; i< ls.size(); i++){
			iDetail = (InventoryMovementDetail)ls.get(i);
			
			strProductCode = iDetail.getProductid();
			strProductName = iDetail.getProductName();
			strProductDate = iDetail.getProductDate();
			strLotNum = iDetail.getLotNum();
			strTrayNum = iDetail.getTrayCode();
			strPunit = iDetail.getMeter();
			
			
			strFromWhare = iDetail.getFromAreName();
			
			strFromCargospace = iDetail.getFromCargospace();
			strToWhare = iDetail.getToAreName();
			strToCargospace = iDetail.getToCargospace();
			

	
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST" align="center"><%=i+1%></td>
     <td class="TD_LIST"><%=strProductCode%></td>
     <td class="TD_LIST" align="center"><%=strProductName%></td>
     <td class="TD_LIST" align="center"><%=strProductDate%></td>
     <td class="TD_LIST" align="center"><%=strLotNum%></td>
     <td class="TD_LIST" align="center"><%=strTrayNum%></td>
     <td class="TD_LIST" align="center"><%=strPunit%></td>
     <td class="TD_LIST" align="center"><%=strFromWhare%></td>
     <td class="TD_LIST" align="center"><%=strFromCargospace%></td>
     <td class="TD_LIST" align="center"><%=strToWhare%></td>
     <td class="TD_LIST" align="center"><%=strToCargospace%></td>
   </tr>
<%
		}
		
	}
	else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"></div>
     </td>
   </tr>
 </table>
</div>
<FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
