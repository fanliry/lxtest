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
<title>�ޱ����ĵ�</title>
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
				parent.form1.reset(); //����
		}else{
					obj.style.backgroundColor = "#99CCFF";
					parent.document.getElementById("invoicedetailid").value = outstockdetailid;//������ϸID
		    		parent.document.getElementById("invoiceid").value = outstockid;//����ID
					parent.document.getElementById("productid").value = productid;//��ƷID
					parent.document.getElementById("product_name").value = productname;//��Ʒ��
		    		parent.document.getElementById("productCode").value = printcode;	//��Ʒ����
		    		parent. selectType(eunit, "eunit", "punit");	//��λ		
		
					parent.document.getElementById("invoicenum").value = parseFloat(invoicenum).toFixed(2);
					parent.document.getElementById("spec").value = spec;
					//parent.document.getElementById("netweight").value = parseFloat(netweight).toFixed(2);
					//parent.document.getElementById("volume").value = parseFloat(volume).toFixed(2);
					//parent.document.getElementById("lotinfo").value = lotinfo; //����
					
		}
		if(oldobj != null){	
			if(oldobj.cells.item(0).getElementsByTagName("input")[0].checked == false)
			{
				oldobj.style.backgroundColor = "";	
			}
		}
		oldobj = obj;
	}
	//ȫѡ
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
				alert("�����ɹ���");
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
     <td class="TD_LIST_TITLE" align="center" width="50"><div class="list_title"><input type="checkbox" name="check_all" onClick="CheckAll();" class="input_checkbox">�к�</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��Ʒ����</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��λ</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">���</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">�߼�����</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     
     
<%
	List ls = (List)request.getAttribute("invoicedetail");
	if(ls != null && ls.size() > 0) 
	{
		//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		OutboundInvoiceDetail outDetail = null;//���ⵥ��ϸ  
		
		String outstockdetailid;//���ⵥ��ϸID
		String outstockid;//���ⵥID
		String productId;	//��Ʒid
		String productName;//��Ʒ��
		String productCode;//��Ʒ����
		String printDate;//��������
		String spec;//���
		String punit;//��λ
		double num;//����
		double weight;//����
		
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
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "��������ݣ�" : ""%></div>
      </td>
    </tr>
  
 </table>
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='formx1'  style='display:none'></FORM>
 </div>
</body>
</html>
