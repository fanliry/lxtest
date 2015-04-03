<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
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
	parent.document.getElementById("vehiclepos").value = '<%=strVehiclepos == null ? "" : strVehiclepos%>';
	
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
 <table width="100%" height="100%" border="0"  align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE" align="center" width="50"><div class="list_title">行号</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">状态</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">批号</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">生产日期</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">单位</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">开单数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">分配数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">发货数量</div></td>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0) 
	{
		//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		OutboundInvoiceDetail outDetail = null;//出库单详细  
		
		String outstockdetailid;//出库单详细ID
      	String outstockid;		//出库单据编号
      	String productid;		//品名
      	String isFH = null;	//是否复合
      	String packid;          //包装 
        String eunit;           //单位 
      	double invoicenum;		//数量
      	double assnum;			//分配数量
      	double sendnum;         //发货数量

   
        String m_strStatusName;     // 状态名
        String m_strProductName;    // 产品
      	String m_strPackName;       // 包装名称
        String m_strUnitName;       // 单位名称
        String printdate; //生产日期
        String lotinfo; //批号
      	
		for(int i = 0; i < ls.size(); i++)
		{
			outDetail = (OutboundInvoiceDetail)ls.get(i);
			
			 outstockdetailid = outDetail.getOutstockdetailid();//出库单详细ID
	      	 outstockid = outDetail.getOutstockid();			//出库单据编号
	      	 
	      	 productid = outDetail.getProductid();			//品名
	      	 invoicenum = outDetail.getInvoicenum();		//数量
	      	 
	      	 packid = outDetail.getPackid();              	/* 包装 */
         	 eunit  = outDetail.getPkgunit();               /* 单位 */
	      	 assnum = outDetail.getAssignnum();				//分配数量

 
         	m_strStatusName = outDetail.getM_strStatusName();   // 状态名
         	m_strProductName = outDetail.getM_strProductName(); // 产品
         	
         	m_strPackName = outDetail.getM_strPackName();       //包装名称
         	m_strUnitName = outDetail.getM_strUnitName();       //单位名称 
         	
         	printdate= outDetail.getPrintdate();
         	lotinfo = outDetail.getLotinfo();
         	
         	sendnum = outDetail.getSendnum();				//发货数量
 %>
	         <tr class="list_normal_tr"  ondblclick="dataonDblClick(this,'<%=outstockdetailid%>','<%=outstockid%>', '<%=strVehiclepos%>', '<%=strVehicleno%>');">
		     <td class="TD_LIST" align="center"><input type="radio" name="check_id" id="<%=outstockid%>" value="<%=outstockdetailid%>" class="input_radio" ><%=(i+1)%></td>
		     <td class="TD_LIST" align="center"><%=m_strStatusName%></td>
		     <td class="TD_LIST" align="center"><%=m_strProductName%></td>
		     <td class="TD_LIST" align="center"><%=m_strProductName%></td>
		     <td class="TD_LIST" align="center"><%=lotinfo == null ? "" : lotinfo%></td>
		     <td class="TD_LIST" align="center"><%=printdate == null ? "" : printdate%></td>
		     <td class="TD_LIST" align="center"><%=m_strUnitName == null ? "" : m_strUnitName%></td>
		     <td class="TD_LIST" align="center"><span style="color: blue; "><%=nbf.format(invoicenum)%></span></td>
		     <td class="TD_LIST" align="center"><span style="color: green; "><%=nbf.format(assnum)%></span></td>
		     <td class="TD_LIST" align="center"><%=nbf.format(sendnum)%></td>
		   </tr>	       	 
<% 	 
		}
		
	}
%>
   <tr>
      <td height="100%" colspan="7" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "无相关数据！" : ""%></div>
      </td>
    </tr>
 </table>
</body>
</html>
