<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail" %>
<%
	OutboundInvoiceHeader outBound = (OutboundInvoiceHeader)request.getAttribute("invoice"); 
	List<BaseLotSet> lsLot = (List)request.getAttribute("lsLot");  		//��ʾ�����������б�
	
	String outstockid = "";     //���ⵥ�ݱ��
	String formdate = "";    	//��������
	String outtype = "";        //���ⵥ������
	String customer = "";		//�ͻ�
	
	if(outBound != null){
		outstockid = outBound.getOutstockid();
		formdate = outBound.getFormdate();
		outtype = outBound.getM_strOutTypeName();
		customer = outBound.getCustomername();
	}
%>
<html>
<head>
<title>�ʼ쵥��ӡ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
<!--
	
	var HKEY_Root,HKEY_Path,HKEY_Key;
	HKEY_Root="HKEY_CURRENT_USER";
	HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
	
	//������ҳ��ӡ��ҳüҳ��Ϊ��
	function PageSetup_Null()     
	{
		try
		{
			var Wsh=new ActiveXObject("WScript.Shell");
			HKEY_Key="header";
			Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
			HKEY_Key="footer";
			Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
		}
		catch(e){}
	}
	//������ҳ��ӡ��ҳüҳ��ΪĬ��ֵ
	function PageSetup_Default()
	{
	  	try
	    {
			var Wsh=new ActiveXObject("WScript.Shell");
			HKEY_Key="header";
			Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"&w&bҳ00��,&p/&P");
			HKEY_Key="footer";
			Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"&u&b&d");
	    }     
		catch(e){}
	}
-->
</script>
<style media="print">
<!--
	.Noprint{display:none;}<!--�ñ���ʽ�ڴ�ӡʱ���طǴ�ӡ��Ŀ-->
	.PageNext{page-break-after: always;}<!--���Ʒ�ҳ-->
-->
</style> 
<style type="text/css">
<!--
	.style2 {
		font-size: 24px; 
		font-weight: bold; 
		font-family: "����_GB2312";
	}
-->
</style>
</head>

<body>

 <!-- ======== ���ܰ�ť��ʼ ======== -->
 <form id="myform1" name="myform1" method="post">
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0" class="Noprint">
   <tr height="30">
     <td><div align="left">
       <OBJECT id=WebBrowser classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 width=0></OBJECT>
       <input type="button" name="readyBtn" value="��ӡԤ��" class="BUTTON_STYLE1" onclick="PageSetup_Null();document.all.WebBrowser.ExecWB(7,1)">&nbsp;
       <input type="button" name="setBtn" value="ҳ������" class="BUTTON_STYLE1" onclick="document.all.WebBrowser.ExecWB(8,1)">&nbsp;
       <input type="button" name="printBtn" value="��ӡ" class="BUTTON_STYLE1" onclick="PageSetup_Null();javascript:window.print()">&nbsp;
       <input type="button" name="closeBtn" value="�ر�" class="BUTTON_STYLE1" onclick="window.close()">
     </div></td>
   </tr>
 </table>
 </form>
 <!-- ======== ���ܰ�ť���� ======== -->
 
 <!-- ======== ���⿪ʼ ======== -->
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
	 <td height="40" colspan="8" valign="bottom"><div align="center" class="style2">��&nbsp;��&nbsp;��</div></td>
   </tr>
   <tr>
	 <td height="20" colspan="8" valign="top"><div align="center">
	   <hr align="center" width="450" color="#006699" noshade="noshade" size="1">
	 </div></td>
   </tr>
   <tr>
	 <td height="20"><div align="left">���ݺţ�<%=outstockid%></div></td>
	 <td height="20"><div align="left">�������ͣ�<%=outtype==null?"":outtype%></div></td>
	 <td height="20"><div align="left">�������ڣ�<%=formdate==null?"":formdate%></div></td>
	 <td height="20"><div align="left">�ͻ���<%=customer==null?"":customer%></div></td>
   </tr>
 </table>
 <!-- ======== ������� ======== -->

 <table width="100%" height="20"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>
 
 <!-- ======== �����б�ʼ ======== --> 
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="outer_table">
 <%
	List list = null;
	if(request.getAttribute("invoicedetail") != null)
	{
		list = (List)request.getAttribute("invoicedetail");
	}
 %>
   <tr class="list_title_tr">
	  <td class="list_title_td">�к�</td>
	  <td class="list_title_td">Ʒ��</td>
	  <td class="list_title_td">��״̬</td>
	  <td class="list_title_td">��װ</td>
	  <td class="list_title_td">��λ</td>
<%
	int iLine = 0;	//��ʾ���������Ը���
	BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);			
%>
	 <td class="list_title_td"><%=lotSet.getLotname()%></td>
<%
		}
	}
%>
     <td class="list_title_td">��������</td>
     <td class="list_title_td">����ë��</td>
     <td class="list_title_td">��������</td>
     
     <td class="list_title_td">��������</td>
     <td class="list_title_td">����ë��</td>
     <td class="list_title_td">���侻��</td>
     
     <td class="list_title_td">��������</td>
     <td class="list_title_td">����ë��</td>
     <td class="list_title_td">��������</td>
   </tr>
<%
    if(list!=null && !list.isEmpty()){
    
    	//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		OutboundInvoiceDetail detail = null;	//���ⵥ��ϸ  
		
      	String linestatus;     		// ��״̬��
        String product;    			// ��Ʒ
      	String pack;       			// ��װ����
        String unit;       			// ��λ����
        
      	double invoicenum;		//����
	  	double weight;			//����
      	double netweight;		//����
      	double assnum;			//��������
      	double assweight;		//��������
      	double assnetweight;	//���侻��
		double sendnum;         //��������
   		double snetweight;      //��������
    	double sweight;         //��������
     	
		for(int i = 0; i < list.size(); i++){
			detail = (OutboundInvoiceDetail)list.get(i);
			
	      	outstockid = detail.getOutstockid();			//���ⵥ�ݱ��
	      	linestatus = detail.getM_strStatusName();   	//״̬��
         	product = detail.getM_strProductName(); 		// ��Ʒ
         	pack = detail.getM_strPackName();       		//��װ����
         	unit = detail.getM_strUnitName();       		//��λ����  
         	
	      	invoicenum = detail.getInvoicenum();		//����
		  	weight = detail.getWeight();				//����
	      	netweight = detail.getNetweight();			//����
	      	
	      	assnum = detail.getAssignnum();				//��������
      	 	assweight = detail.getAssignweight();		//��������
      	 	assnetweight = detail.getAssignnetweight();	//���侻��
      	 	
      	 	sendnum = detail.getSendnum();				//��������
      	 	sweight = detail.getSweight();				//��������
      	 	snetweight = detail.getSnetweight();		//��������
	      	 
%>	
		 	
	<tr class="list_normal_tr">
	  <td class="list_normal_td"><%=i+1%></td>
	  <td class="list_normal_td"><%=product==null?"":product%></td>
	  <td class="list_normal_td"><%=linestatus==null?"":linestatus%></td>
	  <td class="list_normal_td"><%=pack==null?"":pack%></td>
	  <td class="list_normal_td"><%=unit==null?"":unit%></td>
<%
	if(lsLot != null){
		Field field = null; 
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);
			field = detail.getClass().getDeclaredField(lotSet.getLotid());			
%>
	 		 <td class="list_normal_td"><%=field.get(detail)==null?"":(String)field.get(detail)%></td>
<%
		}
	}
%>    
     <td class="list_normal_td"><%=nbf.format(invoicenum)%></td>
     <td class="list_normal_td"><%=nbf.format(weight)%></td>
     <td class="list_normal_td"><%=nbf.format(netweight)%></td>
     
     <td class="list_normal_td"><%=nbf.format(assnum)%></td>
     <td class="list_normal_td"><%=nbf.format(assweight)%></td>
     <td class="list_normal_td"><%=nbf.format(assnetweight)%></td>
     
     <td class="list_normal_td"><%=nbf.format(sendnum)%></td>
     <td class="list_normal_td"><%=nbf.format(sweight)%></td>
     <td class="list_normal_td"><%=nbf.format(snetweight)%></td>
   </tr>
<%
        }
    }
%>	

 </table>

 
</body>
</html>