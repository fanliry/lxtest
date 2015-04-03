<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%
	HashMap<String, List> hsLot = null;	//����Ҫ��ʾ������
	List<BaseLotSet> lsLot = null;  	//��ʾ�����������б�
	if(request.getSession(false).getAttribute("viewLot") != null){
		hsLot = (HashMap)request.getSession(false).getAttribute("viewLot");
		if(hsLot != null){
			lsLot = hsLot.get("newckd");//����ȷ��->��ʾ���ⵥ��ϸ	 
		}
	}

%>
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
	//parent.document.getElementById("vehiclepos").value = '<%=strVehiclepos == null ? "" : strVehiclepos%>';
	
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
<div style="width: 100%; height: 100%;overflow-x:scroll; overflow-y:auto; position:absolute;">
  <table width="150%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="TABLE_LIST">
    <tr>
      <td class="TD_LIST_TITLE1" width="50"><div class="list_title">�к�</div></td>
      <td class="TD_LIST_TITLE" align="center"><div class="list_title">״̬</div></td>
      <td class="TD_LIST_TITLE" align="center"><div class="list_title">Ʒ��</div></td>
      
      <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
      <td class="TD_LIST_TITLE" align="center"><div class="list_title">����ë��</div></td>
      <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
      <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
      <td class="TD_LIST_TITLE" align="center"><div class="list_title">����ë��</div></td>
      <td class="TD_LIST_TITLE" align="center"><div class="list_title">���⾻��</div></td>
 <%
	int iLine = 0;	//��ʾ���������Ը���
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
		//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2);  
		
		
		OutboundInvoiceDetail invoicedetail = null;
		String strOutId;		//���ݺ�
		String invoicedetailid;	//������ϸID	
		String linestatus;		//��״̬
			
      	String productid;      //��ƷID
      	double jobnum;        //��ҵ����
      	double weight;		  //ë��
      	double netweight;	  //����
      	
      	double picknum;			//�������
      	double picknetweight;   //�������
        double pickweight;      //�������
     	
		for(int i = 0; i < ls.size(); i++)
		{
			invoicedetail = (OutboundInvoiceDetail)ls.get(i);
		 	strOutId = invoicedetail.getOutstockid();//���ݺ�
			invoicedetailid = invoicedetail.getOutstockdetailid();	//������ϸID	
		    linestatus = invoicedetail.getM_strStatusName();		//��״̬
		
      	 	productid = invoicedetail.getM_strProductName();   //��Ʒ

      	 	jobnum = invoicedetail.getInvoicenum();     //��ҵ����
      	 	weight = invoicedetail.getWeight();			//ë��
      	    netweight = invoicedetail.getNetweight();	//����
      	    
      	    picknum = invoicedetail.getPicknum();				//�������
      	 	picknetweight = invoicedetail.getPicknetweight();   //�������
         	pickweight = invoicedetail.getPickweight();      	//�������

					
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
        <div style="color: red; font-size:12;margin:5px;"><%= (ls != null && ls.size() == 0) ? "��������ݣ�" : ""%></div>
      </td>
    </tr>

  </table>
</div>
</body>
</html>
