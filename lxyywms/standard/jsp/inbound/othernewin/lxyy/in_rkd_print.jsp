<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundHeader" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundDetail" %>
<%
	InboundHeader inBound = (InboundHeader)request.getAttribute("invoice"); 
	
	String deliveryunits = "";         //������λ
	String warehousename = "";    	//�ֿ�
	String invoicetypename = "";    //�������
	
	String createmanname = "";     //�Ƶ���
	String invoicedate = "";      //�Ƶ�����
	String instockid = "";        //���ݱ��
	
	if(inBound != null){
		deliveryunits = inBound.getDeliveryunits();
		warehousename = inBound.getWarehousename();
		invoicetypename = inBound.getInvoicetypename();
		
		createmanname = inBound.getCreatemanname();
		invoicedate = inBound.getInvoicedate();
		instockid = inBound.getInstockid();
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
		
		var classname = "com.wms3.bms.standard.action.report.otherinbound.inbound_print_rkdExcel";
		
		document.tempForm1.action = strUrl + "downFileAction&name=������ⵥ.xls&fileType=excel&classname= " + classname +"&instockid="+"<%=instockid%>";
		
		document.tempForm1.submit();
		
	}
	
	function OnLoad(){
		var code = document.getElementById("code").value;
		
		if(code == null || code.length < 1){
			alert("�����뵥���ݣ�");
		}else{
			var info = "";
			var width =200; //document.getElementById("width").value;
			var height =120;// document.getElementById("height").value;
			var aem = code.split(",");
			for(var i=0; i<aem.length; i++){
		 		//info += "<img src=\"<%=request.getContextPath()%>/barcode?msg=" + aem[i] 
				//	+ "&type=code128&fmt=jpeg\" height='" + height + "px' width=' " + width + "px' />";
					
				if (info == "") {
		 		info += "<img src=\"<%=request.getContextPath()%>/barcode?msg=" + aem[i] 
					+ "&type=code128&fmt=jpeg\" height='" + height + "px' width='" + width + "px' />";
				} else {
				info += "<br><img src=\"<%=request.getContextPath()%>/barcode?msg=" + aem[i] 
					+ "&type=code128&fmt=jpeg\" height='" + height + "px' width='" + width + "px' />";
					}	
					
					
			}
			img.innerHTML = info;
		
		}
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

<body onLoad="OnLoad()">

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
       <input type="hidden" name="code" value="<%=instockid==null?"":instockid %>" >
     </div></td>
   </tr>
 </table>
 </form>
 <!-- ======== ���ܰ�ť���� ======== -->
 
 <!-- ======== ���⿪ʼ ======== -->
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
	 <td height="40" colspan="6" valign="bottom"><div align="center" class="style2">��&nbsp;��&nbsp;ҩ&nbsp;ҵ&nbsp;(&nbsp;��&nbsp;��&nbsp;)&nbsp;��&nbsp;��&nbsp;��&nbsp;˾</div></td>
   </tr>
   <tr>
	 <td height="20" colspan="6" valign="bottom"><div align="center" class="list_title_tdpr">������ⵥ</div></td>
   </tr>
   <tr>
   	<td colspan="6"><div align="center" id="img"></div></td>
   </tr>
   <tr>
	 <td height="10" colspan="6"></td>
   </tr>
   <tr>
	 <td height="20"><div align="left"  class="list_title_tdpr">�ջ���λ��<%=deliveryunits==null?"":deliveryunits%></div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr">�ջ��ֿ⣺<%=warehousename==null?"":warehousename%></div></td>
	 <td height="20"><div align="left"  class="list_title_tdpr">������ͣ�<%=invoicetypename==null?"":invoicetypename%></div></td>
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
 
 <!-- ======== �����б�ʼ ======== --> 
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="outer_table">
   <tr>
     <td class="list_title_td" align="center" width="50">�к�</td>
     <td class="list_title_td" align="center">��Ʒ����</td>
     <td class="list_title_td" align="center">�󶨼�¼id</td>
     <td class="list_title_td" align="center">��λ</td>
     <td class="list_title_td" align="center">��������</td>
     <td class="list_title_td" align="center">�������</td>
     <td class="list_title_td" align="center">ʵ������</td>
     <td class="list_title_td" align="center">��������</td>
     <td class="list_title_td" align="center">��������</td>
     <td class="list_title_td" align="center">״̬</td>
     <td class="list_title_td" align="center">�ջ���</td>
     <td class="list_title_td" align="center">�ջ�ʱ��</td>
   </tr>
<%
	List ls = (List)request.getAttribute("invoicedetail");
	if(ls != null && ls.size() > 0) 
	{
		//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
	
		InboundDetail inDetail = null;//��ⵥ��ϸ 
		String instockdetailid;	//��ⵥ��ϸid
      	String inid;		   //��ⵥ��
      	String productid;		//Ʒ��id
      	String printdate;		//��������
	  	double innum;			//�������
      	double getnum;		    //ʵ������
      	String lotinfo;         //������Ϣ
      	String productName;     //��Ʒ����
      	String traycode;        //��������
      	String statusname;          //״̬
      	String obtainmanname;          //�ջ���
      	String obtaintime;          //�ջ�ʱ��
      	String bandrecordid;          //�󶨼�¼id
        String m_strUnitName="ƿ"; //��λ
      	
		for(int i = 0; i < ls.size(); i++)
		{
			 inDetail = (InboundDetail)ls.get(i);
			 instockdetailid = inDetail.getInstockdetailid();	
			 inid = inDetail.getInstockid();	
			 productid = inDetail.getProductid();	
			 printdate = inDetail.getPrintdate();	
			 innum = inDetail.getInnum();	
			 getnum = inDetail.getGetnum();	
			 lotinfo = inDetail.getLotinfo();	
			 productName = inDetail.getProductName();	
			 traycode = inDetail.getTraycode();
			 statusname = inDetail.getStatusname();
			 obtainmanname = inDetail.getObtainmanname();
			 obtaintime = inDetail.getObtaintime();
			 bandrecordid = inDetail.getBandrecordid();
 %>
	         <tr class="list_normal_tr">
		     <td class="list_normal_td" align="center"><%=i+1%></td>
		     <td class="list_normal_td" align="center"><%=productName == null ? "" : productName%></td>
		     <td class="list_normal_td" align="center"><%=bandrecordid == null ? "" : bandrecordid%></td>
		     <td class="list_normal_td" align="center"><%=m_strUnitName == null ? "" : m_strUnitName%></td>
		     <td class="list_normal_td" align="center"><%=printdate == null ? "" : printdate%></td>
		     <td class="list_normal_td" align="center"><%=innum%></td>
		     <td class="list_normal_td" align="center"><%=getnum%></td>  
		     <td class="list_normal_td" align="center"><%=lotinfo == null ? "" : lotinfo%></td> 
		     
		     <td class="list_normal_td" align="center"><%=traycode == null ? "" : traycode%></td> 
		     <td class="list_normal_td" align="center"><%=statusname == null ? "" : statusname%></td> 
		     <td class="list_normal_td" align="center"><%=obtainmanname == null ? "" : obtainmanname%></td> 
		     <td class="list_normal_td" align="center"><%=obtaintime == null ? "" : obtaintime%></td> 
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