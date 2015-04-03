<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.List"%>
<%
	
	String instockid = (String)request.getAttribute("instockid");
	
	//设置重复提交的标志变量，begin为首次加载，finish为已提交
	session.setAttribute("submitFlag","begin");
	String strWarehouseID = request.getParameter("warehouseid");
	if(strWarehouseID == null  || strWarehouseID.equals("null")){
		strWarehouseID = (String)session.getAttribute("warehouseid");
	}
%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/standard/style/css/table.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/judgmentTool.js"></script>
<script type='text/javascript' src="<%=request.getContextPath()%>/dwr/interface/getTool.js"></script>


<script type="text/javascript">
<!--
//页面登陆
function onLoad()
{
	var back_msg = "<%=request.getAttribute("back_msg")==null?"":(String)request.getAttribute("back_msg")%>"
	if(back_msg != "")
	{
		if(back_msg == "Y"){
			alert("操作成功！");
		}else{
			alert(back_msg);
		}
	}
}
-->
</script>
</head>

<body onload="onLoad();">
<div style="width: 100%; height: 100%;   overflow:auto;">
<table width="100%" border="0" align="center" id="tb" cellpadding="0" cellspacing="0" class="MAIN_TABLE">
   <tr>  
     <td class="TD_LIST_TITLE" align="center" >盘点数量</td>  
     <td class="TD_LIST_TITLE" align="center" >盘点人</td>
     <td class="TD_LIST_TITLE" align="center" >盘点时间</td>
   </tr>
 <%  List ls = (List)request.getAttribute("exResultList");
	 if(ls != null && ls.size()>0){
			
			//保留小数2位
			NumberFormat nbf=NumberFormat.getInstance();      
			nbf.setMinimumFractionDigits(2);
			nbf.setMaximumFractionDigits(2); 
		
			InventoryCheckResult checkresult = null;
			String resultId ;			//ID
			String product;			//产品
			String lotnumber;	    //批号
			String traycode;		//托盘条码
			double num;				//库存数量
			double checknum;		//盘点数量
			String checktime;		//盘点时间
			String createman;		//操作人
			
			for(int i = 0; i < ls.size(); i++){
			
				checkresult = (InventoryCheckResult)ls.get(i);
				resultId = checkresult.getCheckid();			//ID
				product = checkresult.getProductName();		//产品
				lotnumber = checkresult.getLotnumber();	    //批号
	 		    traycode = checkresult.getTraycode(); 		//托盘条码
				num = checkresult.getNum();					//库存数量
				checknum = checkresult.getChecknum();		//盘点数量
				checktime = checkresult.getChecktime();		//盘点时间
				createman = checkresult.getCreateman();		//操作人
 %>
    <tr onmouseover="this.bgColor='#E2E8EA'" onmouseout="this.bgColor=''" >
    <td class="TD_LIST" align="center"><%=(int)checknum%></td>
    <td class="TD_LIST" align="center"><%=checktime==null?"":checktime%></td>
    <td class="TD_LIST" align="center"><%=createman==null?"":createman%></td>
   </tr>
<%
		}
	}
%> 
 </table>
 </div>
 <div id="info" style="position:absolute; display:none; background-color:#efefff; left:200px; top:200px; z-index: 2;"></div>
</body>
</html>
