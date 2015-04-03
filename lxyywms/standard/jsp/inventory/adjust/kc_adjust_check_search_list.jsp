<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="java.util.List" %>
<%@page import="com.wms3.bms.standard.entity.inbound.InboundInvoiceHeader"%>
<%@page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader"%>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckTask"%>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult"%>

<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script>
<!--
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	function Detail(type, id)
	{
		window.parent.detail.location.href = strUrl + "checkQueryAction&flag=2" + "&type=" + type + "&id=" + id;
	}
	//改变背景
    function Change(obj){
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].parentNode.parentNode.style.backgroundColor = "";
		}
		obj.parentNode.parentNode.style.backgroundColor = "#AFEF6F";
	}
  //查询详细
	function queryDetail(id){
		var flag = parent.document.getElementById("inouttype").value
		parent.detail.location.href = strUrl + "InventoryCheckSearchAction&method=ricoExecSearchDel&flag="+flag+"&instockid=" + id;
	}
	
    function OnLoad(){
		parent.page.location.reload();
    }
-->
</script>
</head>
<!-- onload="javascript:OnLoad();" -->
<body onLoad="OnLoad()">

 <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE" ><div class="list_title">选择</div></td>
     <td class="TD_LIST_TITLE" ><div class="list_title">仓库</div></td>
     <td class="TD_LIST_TITLE" ><div class="list_title">申请单号</div></td>
     <td class="TD_LIST_TITLE" ><div class="list_title">任务号</div></td>
     <td class="TD_LIST_TITLE" ><div class="list_title">产品名</div></td>
     <td class="TD_LIST_TITLE" ><div class="list_title">批号</div></td>
     <td class="TD_LIST_TITLE" ><div class="list_title">库存数量</div></td>
     <td class="TD_LIST_TITLE" ><div class="list_title">盘点数量</div></td>
     <td class="TD_LIST_TITLE" ><div class="list_title">操作员</div></td>
     <td class="TD_LIST_TITLE" ><div class="list_title">盘点时间</div></td>
     <td class="TD_LIST_TITLE" ><div class="list_title">状态</div></td>
   </tr>
<%
	List<Object[]> objLs = (List<Object[]> )request.getAttribute("objLs");  //入库盘点单

	if(objLs != null && objLs.size() > 0)
   	{   
		InventoryCheckTask checkTask = null;
		InventoryCheckResult checkResult = null;
		Object[] obj = new Object[2];
   		String strWarehouseId = "";		    // 仓库
   		String strWhareaid = "";//库区
		String strCheckReq = "";			// 盘点申请
		String strCheckTask = "";			// 任务
		String strProductName = "";         //产品名
		String printdate="";//生产日期
		String punit="";//单位
		String lotid;			//批次ID
		String lotinfo;		    //批号
		
		String traycode;		//托盘条码
		String strLotinfo = "";				// 批号
		double strpnum =0.0;                 //库存数量
		double strCheckNum =0.0;            //盘点数量
		String strOpManName = "";			// 操作员名
		String strOpManTime = "";			// 操作时间
		String status = "";					//状态
		
		String inventoryid="";//库存ID
		String checkid = "";//盘点单ID
		
		
		String values;
	
   		for(int i = 0; i < objLs.size(); i++)
   		{
   			obj = (Object[])objLs.get(i);
   			checkResult = (InventoryCheckResult)obj[0];  //盘点结果
   			checkTask = (InventoryCheckTask)obj[1];//盘点任务
   	   		strWarehouseId = checkTask.getWarehouseid();		    // 仓库
   	   		strWhareaid = checkTask.getWh_area_id();				//库区
   	   		strProductName = checkResult.getProductName();         //品名
   	   		printdate = checkResult.getPrintdate();//生产日期
   	   		punit = checkResult.getPunit();//单位
   	   		lotid = checkTask.getLotid();//批号类型
   	   		lotinfo = checkTask.getLotinfo();//批号
   	   		traycode = checkTask.getTraycode();//托盘号
   	   		
   	   		
   			strCheckReq = checkResult.getRequestid();			// 盘点申请
   			strCheckTask = checkResult.getTaskid();			// 任务
   			inventoryid = checkTask.getInventoryid();//库存ID
   			checkid = checkResult.getCheckid();//盘点结果单ID
   			strLotinfo = checkResult.getLotnumber();				// 批号
   			strpnum =checkResult.getNum();                 //库存数量
   			strCheckNum =checkResult.getChecknum();            //盘点数量
   			strOpManName = checkResult.getCreateman();			// 操作员名
   			strOpManTime =checkResult.getChecktime() ;			// 操作时间
   			status = checkResult.getStatusName();
   			values =  strWhareaid+"|"+strCheckNum+"|"+strProductName+"|"+printdate+"|"+punit+"|"+lotid+"|"+lotinfo+"|"+traycode+"|"+strpnum+"|"+inventoryid+"|"+checkid;
			
%>
   <tr onmouseover="this.bgColor='#E2E8EA'" onmouseout="this.bgColor=''">
     <td class="TD_LIST" align="center"><input name="check_id" type="checkbox" class="input_checkbox" value="<%=values%>"></td>
     <td class="TD_LIST" align="center"><%=strWarehouseId == null ? "" : strWarehouseId%></td>
     <td class="TD_LIST" align="center"><%=strCheckReq == null ? "" : strCheckReq%></td>
     <td class="TD_LIST" align="center"><%=strCheckTask == null ? "" : strCheckTask%></td>
     <td class="TD_LIST" align="center"><%=strProductName == null ? "" : strProductName%></td>
     <td class="TD_LIST" align="center"><%=strLotinfo == null ? "" : strLotinfo%></td>
     <td class="TD_LIST" align="center"><%=strpnum%></td>	
     <td class="TD_LIST" align="center"><%=strCheckNum%></td>	
     <td class="TD_LIST" align="center"><%=strOpManName == null ? "" : strOpManName%></td>	
     <td class="TD_LIST" align="center"><%=strOpManTime == null ? "" : strOpManTime%></td>	
     <td class="TD_LIST" align="center"><%=status == null ? "" : status%></td>	
  </tr>
<%
		}
	}		
	else{
		session.removeAttribute("paging");	
	}
%>
   <tr>
      <td height="100%" colspan="7" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%if((objLs != null && objLs.size() == 0) && (objLs!= null && objLs.size() == 0)){%>无相关数据！<%}%></div>
      </td>
    </tr>
 </table>

</body>
</html>
