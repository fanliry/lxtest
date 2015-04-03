<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%
	HashMap<String, List> hsLot = null;	//所有要显示的批次
	List<BaseLotSet> lsLot = null;  	//显示的批次属性列表
	if(request.getSession(false).getAttribute("viewLot") != null){
		hsLot = (HashMap)request.getSession(false).getAttribute("viewLot");
		if(hsLot != null){
			lsLot = hsLot.get("newckd");//发货确认->显示出库单详细	 
		}
	}

%>
<%
	OutboundInvoiceHeader outinvoice = null;
	if(request.getAttribute("outinvoice") != null)
	{
		outinvoice = (OutboundInvoiceHeader)request.getAttribute("outinvoice");
	}
	String invoiceid = "";	//单据号
	String strVehicleno = "";	//车牌
	String strVehiclepos = "";	//车位
	if(outinvoice != null){
		invoiceid = outinvoice.getOutstockid();		//单据号
		strVehicleno = outinvoice.getVehicleno();	//车牌
        strVehiclepos = outinvoice.getVehiclepos();	//车位
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
	
	parent.document.getElementById("vehicleno").value = '<%=strVehicleno == null ? "" : strVehicleno%>';
	//parent.document.getElementById("vehiclepos").value = '<%=strVehiclepos == null ? "" : strVehiclepos%>';
	
	var oldobj = null;
	function dataonDblClick(obj, id, outid, vehiclepos, vehicleno) 
	{
		//改变背景
		
		obj.style.backgroundColor = "#AFEF6F";
		if(oldobj != null)
		{
			oldobj.style.backgroundColor = "";
		}
		oldobj = obj;
		
		var data = new Date();
		var result = showModalDialog("<%=request.getContextPath()%>/standard/jsp/outbound/fhqr/outbound_fhqr_job.jsp?outdetailid=" + id + "&outboundid=" + outid  + "&vehiclepos=" + vehiclepos + "&vehicleno=" + vehicleno + "&data="+data, 
					outid, "dialogWidth=1000px; dialogHeight=600px; "); 
		 
	}

	
	
	
	function OnLoad(){
		parent.RemoveLoading();

		var back_msg = "<%=request.getAttribute("back_msg")==null?"":request.getAttribute("back_msg")%>";
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

<body onload="javascript:OnLoad();">
<div style="width: 100%; height: 100%;overflow-x:scroll; overflow-y:auto; position:absolute;">
  <table width="150%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr>
      <td class="TD_LIST_TITLE1" width="50"><div class="list_title">行号</div></td>
      <td class="TD_LIST_TITLE" align="center"><div class="list_title">状态</div></td>
      <td class="TD_LIST_TITLE" align="center"><div class="list_title">品名</div></td>
      
      <td class="TD_LIST_TITLE" align="center"><div class="list_title">开单数量</div></td>
      <td class="TD_LIST_TITLE" align="center"><div class="list_title">开单毛重</div></td>
      <td class="TD_LIST_TITLE" align="center"><div class="list_title">开单净重</div></td>
      <td class="TD_LIST_TITLE" align="center"><div class="list_title">出库数量</div></td>
      <td class="TD_LIST_TITLE" align="center"><div class="list_title">出库毛重</div></td>
      <td class="TD_LIST_TITLE" align="center"><div class="list_title">出库净重</div></td>
 <%
	int iLine = 0;	//显示的批次属性个数
	BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = lsLot.get(k);			
%>
	 <td class="TD_LIST_TITLE" align="center"><div class="list_title"><%=lotSet.getLotname()%></div></td>
<%
		}
	}
%>     
     
    </tr>
   
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0) 
	{
		//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2);  
		
		
		OutboundInvoiceDetail invoicedetail = null;
		String strOutId;		//单据号
		String invoicedetailid;	//单据详细ID	
		String linestatus;		//行状态
			
      	String productid;      //物品ID
      	double jobnum;        //作业数量
      	double weight;		  //毛重
      	double netweight;	  //净重
      	
      	double picknum;			//拣货数量
      	double picknetweight;   //拣货净重
        double pickweight;      //拣货重量
     	
		for(int i = 0; i < ls.size(); i++)
		{
			invoicedetail = (OutboundInvoiceDetail)ls.get(i);
		 	strOutId = invoicedetail.getOutstockid();//单据号
			invoicedetailid = invoicedetail.getOutstockdetailid();	//单据详细ID	
		    linestatus = invoicedetail.getM_strStatusName();		//行状态
		
      	 	productid = invoicedetail.getM_strProductName();   //物品

      	 	jobnum = invoicedetail.getInvoicenum();     //作业数量
      	 	weight = invoicedetail.getWeight();			//毛重
      	    netweight = invoicedetail.getNetweight();	//净重
      	    
      	    picknum = invoicedetail.getPicknum();				//拣货数量
      	 	picknetweight = invoicedetail.getPicknetweight();   //拣货净重
         	pickweight = invoicedetail.getPickweight();      	//拣货重量

					
%>
	<tr class="list_normal_tr"  ondblclick="dataonDblClick(this,'<%=invoicedetailid%>','<%=strOutId%>', '<%=strVehiclepos%>', '<%=strVehicleno%>');"  >
      <td class="TD_LIST1" align="center"><input type="radio" name="check_id" id="<%=strOutId%>" value="<%=invoicedetailid%>" class="input_radio" ><%=(i+1)%></td>
      <td class="TD_LIST" align="center"><%=linestatus == null ? "" : linestatus%></td>
      <td class="TD_LIST" align="center"><%=productid == null ? "" : productid%></td>
  
      <td class="TD_LIST" align="center" <%=jobnum != picknum ? "style='background-color:#AABB45' " : ""%>><%=nbf.format(jobnum)%></td>   
      <td class="TD_LIST" align="center"><%=nbf.format(weight)%></td>
      <td class="TD_LIST" align="center"><%=nbf.format(netweight)%></td>
      
      <td class="TD_LIST" align="center" <%=jobnum != picknum ? "style='background-color:#AABB45' " : ""%>><%=nbf.format(picknum)%></td>   
      <td class="TD_LIST" align="center"><%=nbf.format(pickweight)%></td>
      <td class="TD_LIST" align="center"><%=nbf.format(picknetweight)%></td>
 <%
	if(lsLot != null){
		Field field = null; 
		for(int k = 0; k < iLine; k++){
			lotSet = lsLot.get(k);
			field = invoicedetail.getClass().getDeclaredField(lotSet.getLotid());					
%>
	 		 <td class="TD_LIST" align="center"><%=(String)field.get(invoicedetail)%></td>
<%
		}
	}
%>	     
     
    </tr>
<%
      	}
	}

%>
    <tr>
      <td height="100%" colspan="<%=9 + iLine%>" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12;margin:5px;"><%= (ls != null && ls.size() == 0) ? "无相关数据！" : ""%></div>
      </td>
    </tr>

  </table>
</div>
</body>
</html>
