<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail" %>
<%
   
    //���е�������
	OutboundInvoiceDetail exdetilz = (OutboundInvoiceDetail)request.getAttribute("exdetil");

%>
<html>
<head>
<title>�Զ�������ֿ���Ϣ����ϵͳ</title>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
	
</SCRIPT>
</head>

<body>

  <table id="theTable" width="100%" height="100%" border="0" align="center"  cellpadding="0" cellspacing="0" class="TABLE_LIST">
   <thead> 
   
   <tr>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">���ⵥ�ݱ��</div></td>
     
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">״̬</div></td>
      <td class="TD_LIST_TITLE" align="center"><div class="list_title">��Ʒ����</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">Ʒ��</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��λ</div></td>
     
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">����ë��</div></td>
     
     
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">����ë��</div></td>
     
     
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">��������</div></td>
     <td class="TD_LIST_TITLE" align="center"><div class="list_title">����ë��</div></td>
    
   </tr>
<%
	
	
		//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		OutboundInvoiceDetail outDetail = null;//���ⵥ��ϸ  
		
		String outstockdetailid;//���ⵥ��ϸID
      	String outstockid;		//���ⵥ�ݱ��
      	String productCode; //��Ʒ����
      	String productid;		//Ʒ��
      	String packid;          //��װ 
        String eunit;           //��λ 
      	double invoicenum;		//����
	  	double weight;			//����
      	double netweight;		//����
      	
      	
      	double assnum;			//��������
      	double assweight;		//��������
      	double assnetweight;	//���侻��
      	
      	double sendnum;         //��������
   		double snetweight;      //��������
    	double sweight;         //��������

   
        String m_strStatusName;     // ״̬��
        String m_strProductName;    // ��Ʒ
      	String m_strPackName;       // ��װ����
        String m_strUnitName;       // ��λ����
        
        String printdate; //��������
        String lotinfo; //����
      	
    	String traycode;            //��������
	
			outDetail = exdetilz;
			
			 outstockdetailid = outDetail.getOutstockdetailid();//���ⵥ��ϸID
	      	 outstockid = outDetail.getOutstockid();			//���ⵥ�ݱ��
	      	 productCode = outDetail.getM_strProductCode();//��Ʒ����
	      	 productid = outDetail.getProductid();			//Ʒ��
	      	 invoicenum = outDetail.getInvoicenum();		//����
		  	 weight = outDetail.getWeight();				//����
	      	 netweight = outDetail.getNetweight();			//����
	      	 
	      	 packid = outDetail.getPackid();              	/* ��װ */
         	 eunit  = outDetail.getPkgunit();               /* ��λ */
	      	 assnum = outDetail.getAssignnum();				//��������
      	 	 assweight = outDetail.getAssignweight();		//��������
      	 	 assnetweight = outDetail.getAssignnetweight();	//���侻��

 
         	m_strStatusName = outDetail.getM_strStatusName();   // ״̬��
         	m_strProductName = outDetail.getM_strProductName(); // ��Ʒ
         	
         	m_strPackName = outDetail.getM_strPackName();       //��װ����
         	m_strUnitName = outDetail.getM_strUnitName();       //��λ���� 
         	
         	printdate= outDetail.getPrintdate();
         	lotinfo = outDetail.getLotinfo();
         	
         	sendnum = outDetail.getSendnum();				//��������
      	 	sweight = outDetail.getSweight();				//��������
      	 	snetweight = outDetail.getSnetweight();		//��������
      	 	
        	traycode = outDetail.getTraycode();            //��������
 %>
	<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
		     <td class="TD_LIST" align="center"><%=outstockid%></td>
		     <td class="TD_LIST" align="center"><%=m_strStatusName%></td>
		     <td class="TD_LIST" align="center"><%=productCode%></td>
		     <td class="TD_LIST" align="center"><%=m_strProductName%></td>
		     <td class="TD_LIST" align="center"><%=printdate == null ? "" : printdate%></td>
		     
		     <td class="TD_LIST" align="center"><%=traycode == null ? "" : traycode%></td>
		     <td class="TD_LIST" align="center"><%=m_strUnitName == null ? "" : m_strUnitName%></td>
		     
		     <td class="TD_LIST" align="center"><span style="color: blue; "><%=nbf.format(invoicenum)%></span></td>
		     <td class="TD_LIST" align="center"><span style="color: blue; "><%=nbf.format(weight)%></span></td>
		     
		     
		     <td class="TD_LIST" align="center"><span style="color: green; "><%=nbf.format(assnum)%></span></td>
		     <td class="TD_LIST" align="center"><span style="color: green; "><%=nbf.format(assweight)%></span></td>
		     
		     
		     <td class="TD_LIST" align="center"><%=nbf.format(sendnum)%></td>
             <td class="TD_LIST" align="center"><%=nbf.format(sweight)%></td>
             
	</tr>	
	<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
	</tr>   
		<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
	</tr>  
		<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
	</tr>  
		<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
	</tr>  
		<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
	</tr>  
		<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
	</tr>  
		<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>	<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
	</tr>  
		<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
	</tr>  
		<tr onmouseover="this.bgColor='#e1f3ff'" onmouseout="this.bgColor=''">
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
		     <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
             <td class="TD_LIST"></td>
	</tr>  
	 

 
 </table>
</body>
</html>
