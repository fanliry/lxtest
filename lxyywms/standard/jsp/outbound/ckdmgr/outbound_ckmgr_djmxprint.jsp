<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader" %>
<%@ page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceDetail" %>
<%@page import="com.wms3.bms.standard.entity.job.lxyy.InoutJob"%>
<%@page import="com.wms3.bms.standard.entity.job.lxyy.InoutJobdetail"%>
<%
	OutboundInvoiceHeader outBound = (OutboundInvoiceHeader)request.getAttribute("invoice"); 
	
	
	String warehousename = "";    	//�ֿ�
	String customername = "";         //�ջ��ͻ�
	String outTypeName = "";    //��������
	
	String createmanname = "";     //�Ƶ���
	String invoicedate = "";      //�Ƶ�����
	String instockid = "";        //���ݱ��
	
	if(outBound != null){
		warehousename = outBound.getWarehousename();
		customername = outBound.getCustomername();
		outTypeName = outBound.getM_strOutTypeName();
		createmanname = outBound.getM_strOpManName();
		invoicedate = outBound.getFormdate();
		instockid = outBound.getOutstockid();
	}
%>
<html>
<head>
<title>��ⵥ��ӡ</title>
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
	//��Excel����
	function openexcel()
	{
		
		
		var strUrl = "<%=request.getContextPath()%>/BMSService?actionCode=";
		
		var classname = "com.wms3.bms.standard.action.report.outbound.outbound_print_djmxExcel";
		
		document.tempForm1.action = strUrl + "downFileAction&name=���ⵥ����ϸ.xls&fileType=excel&classname= " + classname +"&outstockid="+"<%=instockid%>";
		
		document.tempForm1.submit();
		
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
		color: #000000;
	}
	.style2 {
		font-weight: bold; 
		color: #000000;
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
       <input type="button" name="readyBtn" value="excel����" class="BUTTON_STYLE1" onclick="openexcel();">&nbsp;
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
	 <td height="40" colspan="6" valign="bottom"><div align="center" class="style2">ԣ&nbsp;��&nbsp;ҩ&nbsp;ҵ&nbsp;��&nbsp;��&nbsp;��&nbsp;˾</div></td>
   </tr>
   <tr>
	 <td height="20" colspan="6" valign="bottom"><div align="center" class="list_title_tdpr">��Ʒ���ⵥ</div></td>
   </tr>
   <tr>
	 <td height="10" colspan="6"></td>
   </tr>
   <tr>
	 <td height="20"><div align="left"  class="list_title_tdpr">�ջ��ֿ⣺<%=warehousename==null?"":warehousename%></div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr">�ջ���λ��<%=customername==null?"":customername%></div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr">�������ͣ�<%=outTypeName==null?"":outTypeName%></div></td>
   </tr>
   <tr>
	 <td height="20"><div align="left"  class="list_title_tdpr">�Ƶ��ˣ�<%=createmanname==null?"":createmanname%></div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr">�Ƶ����ڣ�<%=invoicedate==null?"":invoicedate%></div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr">���ݱ�ţ�<%=instockid==null?"":instockid%></div></td>
   </tr>
 </table>
 <!-- ======== ������� ======== -->

 <table width="100%" height="20"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>
 
 <!-- ======== �����б���ʼ ======== --> 
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="outer_table">
 <%
	List list = null;
	if(request.getAttribute("invoicedetail") != null)
	{
		list = (List)request.getAttribute("invoicedetail");
	}
 %>
   <tr class="list_title_tr">
     <td class="list_title_td">��Ʒ����</td>
     <td class="list_title_td">Ʒ��</td>
     <td class="list_title_td">��������</td>
     <td class="list_title_td">����</td>
     <td class="list_title_td">���̺�</td>
     <td class="list_title_td">��λ</td>
     <td class="list_title_td">ʵ������</td>
     <td class="list_title_td">��ע</td>
   </tr>
<%
    if(list!=null && !list.isEmpty()){
    
    	//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		InoutJob job = null;	//��ҵ  
        InoutJobdetail jobDetail = null;//��ҵ��ϸ
        
        String productid;//��ƷId
      	String productCode;		//��Ʒ����
	  	String productName;		//Ʒ��
      	String printdate;		//��������
      	String lotinfo;			//����
      	String traycode;//���̺�
      	String unitName;		//��λ
      	double plannum;	        //��������
      	double sendnum;	        //��������
		String remark=null;          //��ע
     	
		for(int i = 0; i < list.size(); i++){
			Object[] obj = (Object[])list.get(i);
			
			
			job = (InoutJob)obj[0];
			jobDetail =(InoutJobdetail)obj[1];
			
	      	productid = jobDetail.getProductid();	
	      	productCode = jobDetail.getProductcode();
	      	productName = jobDetail.getM_strProductName();   	
         	printdate = jobDetail.getPrintdate(); 		 
         	lotinfo = jobDetail.getLotinfo();       		
         	unitName = jobDetail.getM_strUnitName();       		
	      	plannum = jobDetail.getJobnum();	
	      	traycode = job.getTraycode();
		  	sendnum = jobDetail.getAssignnum();
		  	
%>	
		 	
	<tr class="list_normal_tr">
	  <td class="list_normal_td"><%=productCode==null?"":productCode%></td>
	  <td class="list_normal_td"><%=productName==null?"":productName%></td>
      <td class="list_normal_td"><%=printdate==null?"":printdate%></td>
	  <td class="list_normal_td"><%=lotinfo==null?"":lotinfo%></td>
	  <td class="list_normal_td"><%=traycode==null?"":traycode%></td>
	  <td class="list_normal_td"><%=unitName==null?"":unitName%></td>
      
      <td class="list_normal_td"><%=nbf.format(sendnum)%></td>
      <td class="list_normal_td"><%=remark==null?"":remark%></td>
   </tr>
<%
        }
    }
%>	
 </table>
  <!-- ======== �����β ======== -->
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
	 <td height="10" colspan="4"></td>
   </tr>
   <tr>
	 <td height="20"><div align="left"  class="list_title_tdpr">�����ˣ�</div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr">�������ڣ�</div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr">�����ˣ�</div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr">�������ڣ�</div></td>
   </tr>

 </table>
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm1' target="_self"  style='display:none'></FORM>
</body>
</html>