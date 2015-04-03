<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction" %>
<%@ page import="java.text.NumberFormat" %>

<%
	InboundReceiptTransaction trans = null;
	
	String strTransId = "";	//�ϼܼ�¼ID

	String productid = "";  //��ƷID
	String m_strProductName = ""; // ��Ʒ	
	String ownername = "";	//����
	double recnum = 0;				//�ջ�����
    double reweight = 0;			//�ջ�����
    double renetweight = 0;			//�ջ�����
    if(request.getAttribute("trans")!=null)
	{
		trans = (InboundReceiptTransaction)request.getAttribute("trans");
	}
	if(trans != null)
	{
		strTransId = trans.getTransreceiptid();	//�ϼܼ�¼ID
	 	productid = trans.getProductid();  		//��ƷID
	 	m_strProductName = trans.getM_strProductName(); // ��Ʒ	
	 	ownername = trans.getOwnername();		//����
	 	recnum = trans.getRecnum();				//�ջ�����
     	reweight = trans.getReweight();			//�ջ�����
     	renetweight = trans.getRenetweight();	//�ջ�����
	}
	//����С��2λ
	NumberFormat nbf=NumberFormat.getInstance();      
	nbf.setMinimumFractionDigits(2);
	nbf.setMaximumFractionDigits(2);  

%>

<html>
<head>
<title>�ջ���ǩ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/standard/style/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
	#nav{
		width: 80mm;
		height:40mm;
		border:1px solid #000000;
	}
-->
</style>

<style media="print">
<!--
	.Noprint{display:none;}<!--�ñ���ʽ�ڴ�ӡʱ���طǴ�ӡ��Ŀ-->
	.PageNext{page-break-after: always;}<!--���Ʒ�ҳ-->
-->
</style> 

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
</head>

<body>
 <form method="post" action="">
<!-- ======== ���ܰ�ť��ʼ ======== -->
 <div style="width: 80mm;" align="center">
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
 </div>
 <!-- ======== ���ܰ�ť���� ======== -->
 
 <!-- ======== ��ǩ���ݿ�ʼ ======== -->
 <div id="nav" align="center">
   <table width="95%"align="center" border="0" cellspacing="0" cellpadding="0">
     <tr>
	   <td height="5px"></td>
	 </tr>
	 <tr >
	   <td align="left"><img id="codeImg" src="<%=request.getContextPath()%>/barcode?msg=<%=strTransId%>&type=code128&fmt=jpeg" height="80px" width="280px"/></td>
	 </tr>
   </table>
   <table width="280px" align="center" border="0" cellspacing="0" cellpadding="0" class="font_001F56_12">
     <tr>
	   <td colspan="2">��Ʒ���룺<img id="codeImg" src="<%=request.getContextPath()%>/barcode?msg=<%=productid%>&type=code128&fmt=jpeg" height="30px" width="200px"/></td>
	 </tr>
	 <tr height="18px">
	   <td width="120">Ʒ&nbsp;&nbsp;&nbsp;&nbsp;����<%=m_strProductName%></td><td>��&nbsp;&nbsp;&nbsp;&nbsp;����<%=nbf.format(recnum)%></td>
	 </tr>
	 <tr height="18px">
	   <td>ë&nbsp;&nbsp;&nbsp;&nbsp;�أ�<%=nbf.format(reweight)%></td><td>��&nbsp;&nbsp;&nbsp;&nbsp;�أ�<%=nbf.format(renetweight)%></td>
	 </tr>
	  <tr height="18px">
	   <td colspan="2">��&nbsp;&nbsp;&nbsp;&nbsp;����<%=ownername%></td>
	 </tr>
   </table>
 </div>
 <!-- ======== ��ǩ���ݽ��� ======== -->
 </form>
</body>
</html>