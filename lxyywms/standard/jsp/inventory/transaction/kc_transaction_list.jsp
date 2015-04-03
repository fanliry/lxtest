<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@page import="com.wms3.bms.standard.entity.inventory.InventoryTransaction"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.wms3.bms.standard.entity.base.BaseLotSet"%>
<%@page import="java.lang.reflect.Field"%>
<%
	HashMap<String, List> hsLot = null;		//所有要显示的批次
	List<BaseLotSet> lsLot = null;  	    //显示的批次属性列表
	if(request.getSession(false).getAttribute("viewLot") != null){
		hsLot = (HashMap)request.getSession(false).getAttribute("viewLot");
		if(hsLot != null){
			lsLot = hsLot.get("kcjy");	//库存盘点显示
		}
	}
%>
<html>
<head>
<title>欢迎使用自动化立体仓库信息管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
<!--
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
     <td class="TD_LIST_TITLE"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">作业ID</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">交易时间</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">FM库区</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">FM货位</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">FM品名规格</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">FM客户</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">FM托盘条码</div></td>
<%
	int iLine = 0;	//显示的批次属性个数
	BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);			
%>
	 <td class="TD_LIST_TITLE"><div class="list_title">FM<%=lotSet.getLotname()%></div></td>
<%
		}
	}
%>

     <td class="TD_LIST_TITLE"><div class="list_title">FM数量</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">TO库区</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">TO货位</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">TO品名规格</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">TO客户</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">TO托盘条码</div></td>
<%
//	int iLine = 0;	//显示的批次属性个数
	//BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);			
%>
	 <td class="TD_LIST_TITLE"><div class="list_title">TO<%=lotSet.getLotname()%></div></td>
<%
		}
	}
%>
     <td class="TD_LIST_TITLE"><div class="list_title">TO数量</div></td>
     <td class="TD_LIST_TITLE"><div class="list_title">操作人员</div></td>
     
   </tr>
<%
	List<InventoryTransaction> ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0)
	{
		InventoryTransaction iTransaction = null;
		String transactiontype;		//交易类型
		String transstatus;			//交易状态
		String doctype;				//单证类型
		String docid;				//单证ID
		String doclineno;			//单证行号
		String fmcargo_space_id;	//FM货位ID
		String fmwh_area_id;		//FM库区ID
		String fmcustomerid;		//FM客户ID
		String fmpackid;			//FM包装ID
		String fmpunit;				//FM计量单位
		String fmproductid;			//FM产品ID
		String tocargo_space_id;	//TO货位ID
		String towh_area_id;		//TO库区ID
		String tocustomerid;		//TO客户ID
		String topackid;		//TO包装ID
		String topunit;			//TO计量单位
		String toproductid;		//TO产品ID
		String totraycode;		//TO托盘条码
		String injobid;			//作业ID
		String injobetailid;		//作业详细ID
		String transactiontime;		//操作时间
		String createmanid;		//操作人
		String inventoryid;		//库存ID
		double fmnum;			//FM库存数量
		double fmvolume;		//FM库存体积
		double fmweight;		//FM库存重量
		double fmnetweight;		//FM库存净重
		double tonetweight;		//TO库存净重
		double toweight;		//TO库存重量
		double tonum;			//TO库存数量
		String reasoncode;		//原因代码
		String reason;			//原因
		double tovolume;		//TO库存体积
		String fmtraycode;		//FM托盘条码
		String warehouseid;		//仓库ID
	    
	    //新增字段
	    String boxcode;            //箱条码
	    String productcode;        //产品条码

  	 	for(int i=0; i<ls.size(); i++) 
  	 	{
  	 		iTransaction = ls.get(i);
  	 		warehouseid = iTransaction.getWarehouseid();				//仓库ID
  	 		transactiontype = iTransaction.getTransactiontype();		//交易类型
  			transstatus = iTransaction.getTransstatus();			    //交易状态
  			doctype = iTransaction.getDoctype();						//单证类型
  			docid = iTransaction.getDocid();							//单证ID
  			doclineno = iTransaction.getDoclineno();					//单证行号
  			fmcargo_space_id = iTransaction.getFmcargo_space_name();		//FM货位名
  			fmwh_area_id = iTransaction.getFmwh_area_name();				//FM库区ID
  			fmcustomerid = iTransaction.getFmcustomername();				//FM客户ID
  			fmpackid = iTransaction.getFmpackid();						//FM包装ID
  			fmpunit = iTransaction.getFmpunit();						//FM计量单位
  			fmnum = iTransaction.getFmnum();							//FM库存数量
  			fmproductid = iTransaction.getFmproductname();				//FM产品ID
 			fmvolume = iTransaction.getFmvolume();						//FM库存体积
  			fmweight = iTransaction.getFmweight();						//FM库存重量
  			tocargo_space_id = iTransaction.getTocargo_space_name();		//TO货位ID
  			towh_area_id = iTransaction.getTowh_area_name();				//TO库区ID
  			tocustomerid = iTransaction.getTocustomername();				//TO客户ID
  			topackid = iTransaction.getTopackid();						//TO包装ID
  			topunit = iTransaction.getTopunit();						//TO计量单位
  			toproductid = iTransaction.getToproductname();				//TO产品ID
  			totraycode = iTransaction.getTotraycode();					//TO托盘条码
  			injobid = iTransaction.getInjobid();						//作业ID
  			injobetailid = iTransaction.getInjobetailid();				//作业详细ID
  			transactiontime = iTransaction.getTransactiontime();		//操作时间
  			createmanid = iTransaction.getCreatemanid();				//操作人
  			inventoryid = iTransaction.getInventoryid();				//库存ID
  			fmnetweight = iTransaction.getFmnetweight();				//FM库存净重
  			tonetweight = iTransaction.getTonetweight();				//TO库存净重
  			toweight = iTransaction.getToweight();						//TO库存重量
  			tonum = iTransaction.getTonum();							//TO库存数量
  			reasoncode = iTransaction.getReasoncode();					//原因代码
  			reason = iTransaction.getReason();							//原因
  			tovolume = iTransaction.getTovolume();						//TO库存体积
  			fmtraycode = iTransaction.getFmtraycode();					//FM托盘条码  
%>
   <tr onmouseover="this.bgColor='#E3F2FF'" onmouseout="this.bgColor=''">
     <td class="TD_LIST" align="center"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=injobid%></td>
     <td class="TD_LIST"><%=transactiontime%></td>
     <td class="TD_LIST" align="center"><%=fmwh_area_id%></td>
     <td class="TD_LIST" align="center"><%=fmcargo_space_id%></td>
     <td class="TD_LIST" align="center"><%=fmproductid%></td>
     <td class="TD_LIST" align="center"><%=fmcustomerid%></td>
     <td class="TD_LIST" align="center"><%=fmtraycode%></td>
<%
	if(lsLot != null){
		Field field = null; 
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);
			field = iTransaction.getClass().getDeclaredField("fm"+lotSet.getLotid());			
%>
	 		 <td class="TD_LIST" align="center"><%=field.get(iTransaction)==null?"":(String)field.get(iTransaction)%></td>
<%
		}
	}
%>  
     <td class="TD_LIST" align="center"><%=fmnum%></td>
     <td class="TD_LIST" align="center"><%=towh_area_id%></td>
     <td class="TD_LIST" align="center"><%=tocargo_space_id%></td>
     <td class="TD_LIST" align="center"><%=toproductid%></td>
     <td class="TD_LIST" align="center"><%=tocustomerid%></td>
     <td class="TD_LIST" align="center"><%=totraycode%></td>
<%
	if(lsLot != null){
		Field field = null; 
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);
			field = iTransaction.getClass().getDeclaredField("to"+lotSet.getLotid());			
%>
	 		 <td class="TD_LIST" align="center"><%=field.get(iTransaction)==null?"":(String)field.get(iTransaction)%></td>
<%
		}
	}
%>
     <td class="TD_LIST" align="center"><%=tonum%></td>
     <td class="TD_LIST" align="center"><%=createmanid%></td>
   </tr>
<%
		}
	}
	else{
		session.removeAttribute("paging");
	}
%>
   <tr>
     <td height="100%" colspan="<%=16 + (iLine+iLine)%>" class="TD_last_LIST" valign="top">
       <div style="color: red; margin:5px;"><%if(ls != null && ls.size() == 0){%>无相关数据！<%}%></div>
     </td>
   </tr>
 </table>
</div>
<FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
</body>
</html>
