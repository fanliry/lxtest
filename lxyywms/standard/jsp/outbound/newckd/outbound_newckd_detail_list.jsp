<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>无标题文档</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />

<script>

	var oldobj = null;
	function showDetail(obj,outstockdetailid,productid,productname,printcode,outstockid,eunit,invoicenum,spec)
	{	
		
		if(obj.cells.item(0).getElementsByTagName("input")[0].checked == false)
		{
				obj.style.backgroundColor = "";	
				parent.form1.reset(); //重置
		}else{
					obj.style.backgroundColor = "#99CCFF";
					parent.document.getElementById("invoicedetailid").value = outstockdetailid;//单据详细ID
		    		parent.document.getElementById("invoiceid").value = outstockid;//单据ID
					parent.document.getElementById("productid").value = productid;//产品ID
					parent.document.getElementById("product_name").value = productname;//产品名
		    		parent.document.getElementById("productCode").value = printcode;	//产品编码
		    		parent. selectType(eunit, "eunit", "punit");	//单位		
		
					parent.document.getElementById("invoicenum").value = parseFloat(invoicenum).toFixed(2);
					parent.document.getElementById("spec").value = spec;
					//parent.document.getElementById("netweight").value = parseFloat(netweight).toFixed(2);
					//parent.document.getElementById("volume").value = parseFloat(volume).toFixed(2);
					//parent.document.getElementById("lotinfo").value = lotinfo; //批号
					
		}
		if(oldobj != null){	
			if(oldobj.cells.item(0).getElementsByTagName("input")[0].checked == false)
			{
				oldobj.style.backgroundColor = "";	
			}
		}
		oldobj = obj;
	}
	//全选
	function CheckAll(){
		var state = document.getElementById("check_all").checked;
		var check_ids = document.getElementsByName("check_id");
		for(var i=0; i<check_ids.length; i++){
			check_ids[i].checked = state;
			if(state){
				check_ids[i].parentNode.parentNode.style.backgroundColor = "#33FF33";
			}
			else{
				check_ids[i].parentNode.parentNode.style.backgroundColor = "";
			}
		}
	}
	
	function OnLoad(){
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
</script>

</head>

<body onload="javascript:OnLoad();">
 <div style="width:100%; height:100%; overflow-x:scroll; overflow-y:auto; position:absolute;">
 <table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <tr>
     <td class="TD_LIST_TITLE" align="center" width="50"><div class="list_title"><input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">行号</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">品名</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">产品编码</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">单位</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">出库数量</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">规格</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">逻辑库区</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">批号</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">生产日期</div></td>
     
     
<%
	List ls = (List)request.getAttribute("invoicedetail");
	if(ls != null && ls.size() > 0) 
	{
		//保留小数2位
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		OutboundInvoiceDetail outDetail = null;//出库单详细  
		
		String outstockdetailid;//出库单详细ID
		String outstockid;//出库单ID
		String productId;	//产品id
		String productName;//产品名
		String productCode;//产品编码
		String printDate;//生产日期
		String spec;//规格
		String punit;//单位
		double num;//数量
		double weight;//重量
		
		String VirtualwhName = "";
		String printdate = "";
		String lotinfo = "";
		
        
      	
		for(int i = 0; i < ls.size(); i++)
		{
			outDetail = (OutboundInvoiceDetail)ls.get(i);
			
			outstockdetailid = outDetail.getOutstockdetailid();
			outstockid = outDetail.getOutstockid();
			productId = outDetail.getProductid();
			productName = outDetail.getM_strProductName();
			spec = outDetail.getM_strSpec();
			productCode = outDetail.getM_strProductCode();
			printDate = outDetail.getPrintdate();
			punit =	outDetail.getM_strUnitName();
			num = outDetail.getInvoicenum();
			weight = outDetail.getWeight();
			
			VirtualwhName = outDetail.getVirtualwhname();
			printdate = outDetail.getPrintdate();
			lotinfo = outDetail.getLotinfo();
			 
	      	 
	      	
 %>
	         <tr onmouseover="this.bgColor='#CCFF00'" onmouseout="this.bgColor=''" onclick="showDetail(this,'<%= outstockdetailid%>','<%=productId %>','<%= productName%>','<%=productCode %>','<%= outstockid%>','<%=punit%>','<%=num%>','<%=spec%>')">
		     <td class="TD_LIST" align="center"><input type="checkbox" name="check_id" class="input_checkbox" value="<%=outstockdetailid%>" ><%=i+1%></td>
		     <td class="TD_LIST" align="center"><%=productName%></td>
		     <td class="TD_LIST" align="center"><%=productCode == null ? "" : productCode%></td>
		     
		     <td class="TD_LIST" align="center"><%=punit == null ? "" : punit%></td>
		     
		     <td class="TD_LIST" align="center"><%=nbf.format(num)%></td>
		      <td class="TD_LIST" align="center"><%=spec == null ? "" : spec%></td>
		      <td class="TD_LIST" align="center"><%=VirtualwhName == null ? "" : VirtualwhName%></td>
		       <td class="TD_LIST" align="center"><%=lotinfo == null ? "" : lotinfo%></td>
		        <td class="TD_LIST" align="center"><%=lotinfo == null ? "" : lotinfo%></td>
		          
		   </tr>	       	 
<% 
		}
		
	}
%>

   <tr>
      <td height="100%" colspan="11" valign="top" class="TD_last_LIST">
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "无相关数据！" : ""%></div>
      </td>
    </tr>
  
 </table>
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
 </div>
</body>
</html>
