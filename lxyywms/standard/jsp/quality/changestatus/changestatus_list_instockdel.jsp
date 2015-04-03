<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<%@page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail"%>
<html>
<head>
<title>自动化立体仓库信息管理系统</title>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">

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
     <td class="TD_LIST_TITLE"><div class="list_title">入库单</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">产品名称</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">托盘条码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">批号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">入库数量</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">实收数量</div></td>

   </tr>
   </thead>  
   <tbody> 
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size()>0){ 
		
    	String instockid;  	    //入库单
    	String product;       	//产品名称
    	String traycode;        //托盘条码
    	String lotnumber;        //批号
        double innum;			    //入库数量
        double getnum;			    //实收数量
     	
     	//小计
		double pnum_sum = 0;	// 数量
		InboundDetail indDetail = null;
		for(int i = 0; i < ls.size(); i++){
			
			indDetail = (InboundDetail)ls.get(i);
			
			instockid = indDetail.getInstockid() == null ? "":indDetail.getInstockid();      //入库单
		    product = indDetail.getProductName() == null ? "" : indDetail.getProductName();       	//产品名称
	    	traycode = indDetail.getTraycode() == null ? "" : indDetail.getTraycode();        //托盘条码
	    	lotnumber = indDetail.getLotinfo() == null ? "" : indDetail.getLotinfo();;        //托盘条码
	        innum = indDetail.getInnum();			    //入库数量
	        getnum = indDetail.getGetnum();			    //实收数量
     		
     		//小计
			pnum_sum += getnum;			// 数量
		
%>
   <tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''" ondblclick="queryDetail('<%=i%>')">
     <td class="TD_LIST1" align="center" width="50px"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=instockid%></td>
     <td class="TD_LIST" align="center"><%=product%></td>
     <td class="TD_LIST" align="center"><%=traycode%></td>
     <td class="TD_LIST" align="center"><%=lotnumber%></td>   
     <td class="TD_LIST" align="center"><%=innum%></td>
     <td class="TD_LIST" align="center"><%=getnum%></td>
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
     <td class="TD_LIST" align="center" style="color: red;"><%=(int)pnum_sum%></td>
   </tr>
<%
	}
%>  
   <tr>
     <td height="100%" colspan="7" class="TD_last_LIST" valign="top">
     	<div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
   </tbody> 
 </table>
</div>
<FORM action="" method='post' name='formx1' style='display:none'></FORM>
</body>
</html>