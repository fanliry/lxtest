<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@page import="java.lang.reflect.Field"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.wms3.bms.standard.entity.base.BaseCargospace"%>

<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	
	//查询详细
	function queryDetail(i){
		//var stokeys = document.getElementsByName("check_id");
		showModalDialog(strUrl + "inventoryQualityAction&flag=2" + i, "", "dialogWidth=1200px;dialogHeight=500px;");
	}
	
	function Change(obj)
	{
		if(obj.checked){
			obj.parentNode.parentNode.style.backgroundColor = "#99CCFF";
		}
		else{
			obj.parentNode.parentNode.style.backgroundColor = "";
		}
	}
	
	function OnLoad(){
		parent.RemoveLoading();
		parent.page.location.reload();
	}
-->
</script>
</head>

<body onLoad="OnLoad()">
<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
  <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE1" width="30"><div class="list_title">选择</div></td>
     <td class="TD_LIST_TITLE1" width="50"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">仓库</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库区</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">申请单号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">批号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">产品编码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">物品状态</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">数量</div></td>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0)
	{
	    Object[] ob = null;
	    String warehouseid;	//仓库ID
        String warehouseName;       //仓库
        String whAreaID;	//库区ID
		String whArea;				//库区
		String productID;	//产品ID
		String productCode;//产品编码
 	 	String productName;				//产品名
 	 	String requestid;		    //申请单号
 	 	String lotInfo;        	//批号
		
		String productStatus;	    //产品状态
		String productStatusName;//产品状态名
		double pnum;            	//库存数量
	    String value;
  	 	for(int i=0; i<ls.size(); i++) 
  	 	{ 	 	  
 	 	    ob = (Object[])ls.get(i);
 	 	    warehouseid = ob[0] == null ? "" : ob[0].toString(); //仓库ID
 	 	    warehouseName = ob[1] == null ? "" : ob[1].toString(); //仓库
 	 	    whAreaID = ob[2] == null ? "" : ob[2].toString();//库区id
 	 	    whArea = ob[3] == null ? "" : ob[3].toString();			//库区
 	 	    requestid = ob[4] == null ? "" : ob[4].toString();		//申请单
 	 	    lotInfo = ob[5] == null ? "" : ob[5].toString();		//批号
 	 	    
 	 	    productID = ob[6] == null ? "" : ob[6].toString();	//产品ID
 	 	    productCode = ob[7] == null ? "" : ob[7].toString();		//产品
 	 	  	productName = ob[8] == null ? "" : ob[8].toString();		//产品
 	 	    
  	 		productStatus = ob[9] == null ? "" : ob[9].toString();		//产品状态	
  	 		productStatusName= ob[10] == null ? "" : ob[10].toString();		//产品状态名	
  	 		pnum = ob[11] == null ? 0.0 : Double.parseDouble(ob[11].toString());		//库存数量
  	 		
  	 		
  	 		//仓库id|库区id|申请单id|批号|产品id|产品状态
  	 		value = "&warehouseid=" + warehouseid + "&whareaid=" + whAreaID +"&requestid="+ requestid  +"&lotnumber=" + lotInfo 
  	 		+"&productid=" + productID  +"&productstatus=" + productStatus;
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" ondblclick="queryDetail('<%=value%>')">
     <td class="TD_LIST" align="center"><input onClick="Change(this)" type="checkbox" name="check_id" class="input_checkbox" value="<%=value%>"/></td>
     <td class="TD_LIST1" align="center"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=warehouseName%></td>
     <td class="TD_LIST" align="center"><%=whArea%></td>
     <td class="TD_LIST" align="center"><%=requestid%></td>
     <td class="TD_LIST" align="center"><%=lotInfo%></td>
     <td class="TD_LIST" align="center"><%=productCode%></td>
     <td class="TD_LIST" align="center"><%=productName%></td>
     <td class="TD_LIST" align="center"><%=productStatusName%></td>
     <td class="TD_LIST" align="center"><%=pnum%></td>
   </tr>
<%
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="<%=9%>" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
 </table>
</div>
<FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
