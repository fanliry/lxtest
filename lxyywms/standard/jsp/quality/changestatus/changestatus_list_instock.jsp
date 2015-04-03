<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<html>
<head>
<title>自动化立体仓库信息管理系统</title>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">

	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	
	//查询详细
	function queryDetail(ids){
		showModalDialog(strUrl + "inventoryQualityAction&flag=3&instockid=" + ids, "", "dialogWidth=1200px;dialogHeight=400px;");
	}
	
	function OnLoad(){
	}
</SCRIPT>
</head>

<body onLoad="OnLoad()">

<div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;"> 
 <table id="theTable" width="100%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   <tr>
     <td class="TD_LIST_TITLE1" width="50px"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">仓库</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库区</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">入库单</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">产品名称</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">产品规格</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">产品状态</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">库存数量</div></td>
   </tr>
   </thead>  
   <tbody> 
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size()>0){ 
		
		String warehouseid;      //仓库ID
		String wharea;      	//库区
    	String instockid;  	    //入库单
    	String product;       	//产品
    	String productstandard; //产品规格
    	String productstatus;        //产品状态
    	double pnum;            //数量
    	String productid;
    	String key;
     	//小计
		double pnum_sum = 0;	// 数量
		Object[] ob = null;
		for(int i = 0; i < ls.size(); i++){
			
			ob = (Object[])ls.get(i);
			
			warehouseid = ob[0] == null ? "":ob[0].toString();      //仓库ID
			wharea = ob[1] == null ? "":ob[1].toString();      	//库区
	    	instockid = ob[2] == null ? "":ob[2].toString();  	    //入库单
	    	product = ob[3] == null ? "":ob[3].toString();       	//产品
	    	productstandard = ob[4] == null ? "":ob[4].toString(); //产品规格
	        productstatus = ob[5] == null ? "":ob[5].toString();        //产品状态
	    	pnum = ob[6] == null ? 0.0:Double.parseDouble(ob[6].toString());            //数量
	    	productid = ob[7] == null ? "":ob[7].toString();       	//产品
     		key = instockid +"&productid="+ productid;
     		//小计
			pnum_sum += pnum;			// 数量
		
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" ondblclick="queryDetail('<%=key%>')">
     <td class="TD_LIST1" align="center" width="50px"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=warehouseid%></td>
     <td class="TD_LIST" align="center"><%=wharea%></td>
     <td class="TD_LIST" align="center"><%=instockid%></td>
     <td class="TD_LIST" align="center"><%=product%></td>   
     <td class="TD_LIST" align="center"><%=productstandard%></td>
     <td class="TD_LIST" align="center"><%=productstatus%></td>
     <td class="TD_LIST" align="center"><%=(int)pnum%></td>
   </tr>
<%
		}
%>
	<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
     <td class="TD_LIST1" align="center"></td>
     <td class="TD_LIST1" align="center"></td>
     <td class="TD_LIST" align="center" style="color: red;">单页小计</td>
     <td class="TD_LIST1" align="center"></td>
     <td class="TD_LIST1" align="center"></td>  
	 <td class="TD_LIST" align="center"></td>
	 <td class="TD_LIST" align="center"></td>
     <td class="TD_LIST" align="center" style="color: red;"><%=(int)pnum_sum%></td>
   </tr>
<%
	}
%>  
   <tr>
     <td height="100%" colspan="8" class="TD_last_LIST" valign="top">
     	<div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
   </tbody> 
 </table>
</div>
<FORM action="" method='post' name='formx1' style='display:none'></FORM>
</body>
</html>