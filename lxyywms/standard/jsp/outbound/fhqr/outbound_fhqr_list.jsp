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
	String invoiceid = "";	//���ݺ�
	String strVehicleno = "";	//����
	String strVehiclepos = "";	//��λ
	if(outinvoice != null){
		invoiceid = outinvoice.getOutstockid();		//���ݺ�
		strVehicleno = outinvoice.getVehicleno();	//����
        strVehiclepos = outinvoice.getVehiclepos();	//��λ
	}
%>
<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
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
		//�ı䱳��
		
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
				alert("�����ɹ���");
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
     <td class="TD_LIST_TITLE" align="center" width="50"><div class="list_title">�к�</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">״̬</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">����</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��λ</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
   </tr>
<%
	List ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0) 
	{
		//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		OutboundInvoiceDetail outDetail = null;//���ⵥ��ϸ  
		
		String outstockdetailid;//���ⵥ��ϸID
      	String outstockid;		//���ⵥ�ݱ��
      	String productid;		//Ʒ��
      	String isFH = null;	//�Ƿ񸴺�
      	String packid;          //��װ 
        String eunit;           //��λ 
      	double invoicenum;		//����
      	double assnum;			//��������
      	double sendnum;         //��������

   
        String m_strStatusName;     // ״̬��
        String m_strProductName;    // ��Ʒ
      	String m_strPackName;       // ��װ����
        String m_strUnitName;       // ��λ����
        String printdate; //��������
        String lotinfo; //����
      	
		for(int i = 0; i < ls.size(); i++)
		{
			outDetail = (OutboundInvoiceDetail)ls.get(i);
			
			 outstockdetailid = outDetail.getOutstockdetailid();//���ⵥ��ϸID
	      	 outstockid = outDetail.getOutstockid();			//���ⵥ�ݱ��
	      	 
	      	 productid = outDetail.getProductid();			//Ʒ��
	      	 invoicenum = outDetail.getInvoicenum();		//����
	      	 
	      	 packid = outDetail.getPackid();              	/* ��װ */
         	 eunit  = outDetail.getPkgunit();               /* ��λ */
	      	 assnum = outDetail.getAssignnum();				//��������

 
         	m_strStatusName = outDetail.getM_strStatusName();   // ״̬��
         	m_strProductName = outDetail.getM_strProductName(); // ��Ʒ
         	
         	m_strPackName = outDetail.getM_strPackName();       //��װ����
         	m_strUnitName = outDetail.getM_strUnitName();       //��λ���� 
         	
         	printdate= outDetail.getPrintdate();
         	lotinfo = outDetail.getLotinfo();
         	
         	sendnum = outDetail.getSendnum();				//��������
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
        <div style="color: red; font-size:12; margin:5px;"><%= (ls != null && ls.size() == 0) ? "��������ݣ�" : ""%></div>
      </td>
    </tr>
 </table>
</body>
</html>
