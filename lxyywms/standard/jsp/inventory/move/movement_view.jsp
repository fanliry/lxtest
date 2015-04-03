	<%@ page contentType="text/html; charset=GB2312"%>
	<%@ page import="com.ricosoft.common.tools.StrTools" %>
	<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage"%>
	<%@page import="java.util.List" %>
	<%@page import="java.util.ArrayList"%>
	<%
		String strWarehouseId =(String)request.getAttribute("strWarehouseId");
		String ids = (String)request.getAttribute("ids");
		String strTime = StrTools.getCurrDateTime(5);
		
	%>
	<html>
	<head>
	<title>明细</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/tools.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/calendar.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/load.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/selectTool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/winopen.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/standard/js/lot.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/selectView.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/lotTool.js"></script>
	<script type="text/javascript">
	var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
	function viewLot(lotid,lotatt1,lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12){
		
			var tableObj = document.getElementById("tab1");

			//2是查询条件的行数, 4是每行显示的td数
			createLotDetail1(tableObj, lotid, 4, lotatt1, lotatt2,lotatt3,lotatt4,lotatt5,lotatt6,lotatt7,lotatt8,lotatt9,lotatt10,lotatt11,lotatt12);	
		}
	
	
	function saveData(ids)
	{
		
		var strParam = strUrl+"inventoryMoveAction&&method=ricoExecAdd&flag=4" 
        					+ "&ids="+ids      //ID
        					+"&strTime="+"<%=strTime%>"
        					+"&warehouseid="+"<%=strWarehouseId%>"
        					+ "&toWarehouseid="+document.getElementById("warehouseid").value  //移入仓库ID
        					+ "&toWhAreaId="+document.getElementById("whAreaId").value  //移入库区Id
        					+ "&toCargospaceId="+document.getElementById("cargospace_id").value  //移入仓库名称
        					+ "&toCargospaceName="+document.getElementById("cargospace_name").value  //移入货位
        					+ "&reasoncode="+document.getElementById("reasoncode").value  //移库原因
        					+ "&remark="+document.getElementById("remark").value; //备注
        window.location.href=strParam;
		window.close();
	}
	function checkDetailData(qty)
	{
         	
       	//冻结数量
		var qtyHold = document.getElementById("qtyHold").value;
		if(qtyHold > 0)
		{
			alert("请先取消冻结后才能移动!");
       		return false; 
		}
		var toqty = document.getElementById("toqty").value;
		if(toqty == "" || toqty.length <1)  
       	{
       		alert("【移库数量】不能为空！");
       		return false; 
       	}
       	if(toqty!=null && toqty.length>0 && !isFloat(toqty,"+","0"))
		{
			alert("【移库数量】必须是正浮点数!");
			return false;
		}
		if(!parseFloat(toqty) > 0)
       	{
       		alert("【移库数量】不能为零!");
			return false;
       	}
       	if(parseFloat(toqty) > parseFloat(qty))
       	{
       		alert("【移库数量】不能大于【库存数量】!");
			return false;
       	}
       	var toLocId = document.getElementById("locid").value;
		if(toLocId == "" || toLocId.length <1)  
       	{
       		alert("【移库库位】不能为空！");
       		return false; 
       	}
       	//原库位与目标库位不能相同
		var locid = document.getElementById("cargospace_id").value;
		if(locid == toLocId)
       	{
       		alert("【原库位】与【目标库位】不能相同!");
			return false;
       	}
		
		return true;
	}
	
	//根据仓库获取暂存区
	function getZCArea(){
		var warehouseid = document.getElementById("warehouseid").value;
		selectZCAreaList("whAreaId", warehouseid);
	}
	
	
	
	function onload(){
		
		var fromWarehouseid ="<%=strWarehouseId%>" ;
		
			//仓库
		DWREngine.setAsync(false);
		selectObject(fromWarehouseid, "warehouseid", "1");	
		DWREngine.setAsync(true);
		
		//库区
		var warehouseid = document.getElementById("warehouseid").value;
		selectType('', 'reasoncode', 'ykyy');			//类型
		
		selectZCAreaList("whAreaId", warehouseid);
	}
	</script>
</head>


	<body onload="javascript:onload();">
	<table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
	   <tr>
	     <td height="25"><div class="font_001F56_bold_12">当前位置：库存管理 -&gt; 库区移动 -&gt; 库区移动明细</div></td>
	   </tr>
   </table>	
   <table width="98%" height="10"  border="0" cellpadding="0" cellspacing="0">
	   <tr><td></td></tr>
   </table>
	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     
     <td class="TD_LIST_TITLE"><div class="list_title">移出仓库</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">移出库区</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">移出货位</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">制单日期</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">托盘条码</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">单位</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">批号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">创单人</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">生产日期</div></td>
   </tr>
	<%  
		String userCode = (String)request.getSession(false).getAttribute("userCode");
		String userName = (String)request.getSession(false).getAttribute("userName");
		
		
		
		InventoryStorage invent = null; 
		List<InventoryStorage> lsIstorage = null;
		if(request.getAttribute("inventory") != null)
		{
			lsIstorage = (List<InventoryStorage>)request.getAttribute("inventory");  
		}
		
		String strId = "";//ID
		String strCustomerId = "";    //客户
		String  strWareId  = "";      //仓库ID
		String  strWarehouse = ""; //仓库名称
		String strWhAreId = "";	//库区Id
		String  strWhAreName  = "";   //库区
		String strSkuId = "";         //产品id
	    String strSkuName = "";       //产品中文描述 
	    String strProductDate = "";		//产品生产日期
		String strLotnum = "";        //批号
		String strLocId = ""; //库位ID
		String strLocName = "";         //库位
		String strTraceId = "";       //托盘条码
		int qty = 0;                  //库存数量
		int qtyHold = 0;              //冻结数量
		int qtyAllocated = 0;         //分配数量
		double cubic = 0.0;           //体积
	    double grossweight = 0.0;     //毛重
	    String strPackUnit = "";      //单位
	    int qtyUsable = 0;            //可用数量  
	
		if(lsIstorage != null)
		{
			for(int i=0;i<lsIstorage.size();i++){
			invent = lsIstorage.get(i);
			
			strId = invent.getInventoryid();             //库存ID
			strWareId = invent.getWarehouseid();         //仓库ID
			strWarehouse =invent.getWarehouseName();	//仓库名称
			strWhAreId = invent.getWhAreaId();//库区id
			strWhAreName  = invent.getWhAreaName();      //库区
			strCustomerId = invent.getOwnerId();         //客户
			strSkuId = invent.getProductid();            //产品id
			strSkuName = invent.getProductName();        //产品中文描述 
			strProductDate = invent.getIndate();	//产品生产日期
		   
			strLotnum = invent.getLotinfo();              //批号
			strLocId = invent.getCargoSpaceId();//库位ID
			strLocName = invent.getCargoSpaceName();       //库位
			strTraceId = invent.getTraycode();           //托盘条码
			qty = (int)invent.getPnum();                 //库存数量
			qtyHold = (int)invent.getHoldnum();          //冻结数量
			qtyAllocated = (int)invent.getAssignnum();   //分配数量
			cubic = invent.getPvolume();                 //体积
		    grossweight = invent.getPweight();           //毛重
		    strPackUnit = invent.getPunitname();         //主单位
		    qtyUsable = qty - qtyAllocated - qtyHold;;   //可用数量  
	%>
	<tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST" align="center"><%=strWarehouse==null?"":strWarehouse%></td>
     <td class="TD_LIST" align="center"><%=strWhAreName==null?"":strWhAreName%></td>
     <td class="TD_LIST" align="center"><%=strLocName==null?"":strLocName%></td>
     <td class="TD_LIST" align="center"><%=strTime==null?"":strTime%></td>
     <td class="TD_LIST" align="center"><%=strTraceId==null?"":strTraceId%></td>
     <td class="TD_LIST" align="center"><%=strPackUnit==null?"":strPackUnit%></td>
     <td class="TD_LIST" align="center"><%=strLotnum==null?"":strLotnum%></td>
     <td class="TD_LIST" align="center"><%=userName==null?"":userName%></td>
     <td class="TD_LIST" align="center"><%=strProductDate==null?"":strProductDate%></td>
   </tr>
   <%
		}
			
	}else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(lsIstorage != null && lsIstorage.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
</table>
	
	 <!-- ======== 明细信息开始 ======== -->
	
	 <!-- ======== 明细信息结束 ======== -->
	<table width="98%" border="0" height="10"  align="center" cellpadding="0" class="x3"> 
    <tr><td></td></tr>
    </table>  
	 <!-- ======== 明细信息开始 ======== -->
     
	 <form id="myform1" name="myform1" method="post">
	 <table width="98%" border="0" align="center" cellpadding="0" class="table_add">
	  <!--   <tr>
     		<td height="15" colspan="6" class="yx"><input type="hidden" name="lotid"/></td>
   	   </tr>-->
	   <tr>
	   	<td width="100" align="right" class="yx1" >移入仓库：</td>
	     <td  class="yx">
	     	<select name="warehouseid" onChange="getZCArea()" style="width:117px;" disabled="disabled"><option value=""></option></select>
	     </td>
	     <td width="100" align="right" class="yx1">移入货区：</td>
	     <td class="yx">
		    <select id="whAreaId" style="width:117px;" disabled="disabled"><option value=""></option></select>
          </td>
           <td width="100" align="right" class="yx1" >移入库位：</td>
	     <td  class="xx1">
	     	<input type="hidden" id="cargospace_id"><input type="text" id="cargospace_name" size="20" readonly  style="height:18px;width:100px";>
       	    <input onClick="openCargospace('<%=request.getContextPath()%>/standard/jsp/inventory/move/kc_cargospace.jsp?warehouseid=<%=strWareId %>','850','550');" 
       			type="button" value="…" class="button_select">
	     </td>
	    
	   </tr>
	   <tr >
	    
	     <td width="100" align="right" class="yx1">移库原因：</td>
	     <td class="yx">
			<select name="reasoncode">
		         <option value="">--请选择--</option>
		    </select>
		</td>
		<td width="100" align="right" class="yx1">移库时间：</td>
	     <td class="xx1"><input type="text" name="moveTime" size="20" maxlength="12" value="<%=strTime == null ? "" : strTime%>" readonly class="input_readonly"></td>
	     
	     <td width="100" align="right" class="yx1">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
	     <td class="xx1"><input type="text" name="remark" size="20" maxlength="12"></td>
	   </tr>

	 <tr>
        	<TD colspan="6" height="10" class="yx">
        		<input type="hidden" name="invoicedetailid" />
        	</TD>
     </tr>
	 <tr>
	     <td height="27" colspan="8" align="center" >
	        <input name="button_add" type="button" class="button_add" id="button_add" value="&nbsp;&nbsp;移动" onClick="saveData('<%=ids%>')"/><!-- onClick="if(checkDetailData('<%=qty%>')!=false){saveData('<%=strId%>');}" -->
            <input name="button" type="reset" class="button_reset" id="button" value="&nbsp;&nbsp;重置" />
         </td>
    </tr>
	 </table>	
	 </form>
	
	 <!-- ========  ======== -->

	</body>
	</html>