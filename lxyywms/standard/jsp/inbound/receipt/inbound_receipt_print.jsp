<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundReceiptHeader" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundReceiptDetail" %>
<%
	InboundReceiptHeader inBound = null; 
	if(request.getAttribute("invoice") != null){
		inBound = (InboundReceiptHeader)request.getAttribute("invoice");
	}
	String strInvoiceId = "";	//�ջ���ID
	String ownername = "";		//����
	if(inBound != null){
		strInvoiceId = inBound.getReinvoiceid();
		ownername = inBound.getOwnername();
	}
%>
<html>
<head>
<title>��ӡ�ϼ�����</title>
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
	 <td height="20" colspan="2"><div align="center" class="style2">�ջ���</div></td>
   </tr>
   <tr>
	 <td height="10" colspan="2" valign="top"><div align="center">
	   <hr align="center" width="450" color="#006699" noshade="noshade" size="1">
	 </div></td>
   </tr>
   <tr>
	 <td height="20"><div align="left" class="style2"><img src="<%=request.getContextPath()%>/barcode?msg=<%=strInvoiceId%>&type=code128&fmt=jpeg" height="20px" width=120px /><%=strInvoiceId%></div></td>
	 <td height="20"><div align="left" class="style2">������<%=ownername%></div></td>
   </tr>
 </table>
 <!-- ======== ������� ======== -->

 <table width="100%" height="10"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>
 
 <!-- ======== �����б�ʼ ======== --> 
 <table width="910" border="1" align="center" cellpadding="0" cellspacing="0" class="outer_table">
 <%
	List list = null;
	if(request.getAttribute("invoicedetail") != null)
	{
		list = (List)request.getAttribute("invoicedetail");
	}
 %>
   <tr class="list_title_tr">
	  <td width="40" class="list_title_td">���</td>
	  <td width="120" class="list_title_td">��Ʒ</td>
	  <td width="80" class="list_title_td">��״̬</td>
	  <td width="100" class="list_title_td">�д���</td>
	  <td width="90" class="list_title_td">��������</td>
	  <td width="120" class="list_title_td">��װ</td>
	  <td width="60" class="list_title_td">��λ</td>
	  <td width="80" class="list_title_td">Ԥ�ڵ�������</td>
	  <td width="80" class="list_title_td">Ԥ�ڵ�������</td>
	  <td width="70" class="list_title_td">�ջ�����</td>
	  <td width="70" class="list_title_td">�ջ�����</td>
	</tr>
<%
    if(list!=null && !list.isEmpty()){
    	//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
		
		InboundReceiptDetail inDetail = null;//�ջ�����ϸ  
		String instockdetailid;	//�ջ�����ϸID
      	String productid;		//Ʒ�����
      	double invoicenum;		//��С��λ����
	  	double weight;			//����
      	double netweight;		//����

      	double recnum;			//��С��λ�������ջ���
      	double reweight;		//�ջ�����
      	double renetweight;		//�ջ�����


      	String m_strProductName;    // ��Ʒ
      	String m_strPackName;       //��װ����
        String m_strUnitName;       //��λ����

      	String linestatus;      //������״̬0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�
		for(int i = 0; i < list.size(); i++)
		{
			inDetail = (InboundReceiptDetail)list.get(i);
			 instockdetailid = inDetail.getReincoicedetailid();	//��ⵥ��ϸID
	      	 productid = inDetail.getProductid();	//Ʒ�����
	      	 invoicenum = inDetail.getInvoicenum();	//��С��λ����
		  	 weight = inDetail.getWeight();			//����
	      	 netweight = inDetail.getNetweight();	//����
	      	 m_strUnitName = inDetail.getM_strUnitName();	//��λ��
	      	 m_strProductName = inDetail.getM_strProductName();    // ��Ʒ
	    
	      	 recnum = inDetail.getRecnum();				//��С��λ�������ջ���
	      	 reweight = inDetail.getReweight();			//�ջ�����
	      	 renetweight = inDetail.getRenetweight();	//�ջ�����
			 m_strPackName = inDetail.getM_strPackName();//��װ����

	      	 linestatus = inDetail.getM_strStatusName();//������״̬0-����1-�����ջ� 2-��ȫ�ջ� 3-������ 4-�����ϼ�5-��ȫ�ϼ�
	      	 
%>	
		 	
	<tr class="list_normal_tr">
	  <td class="list_normal_td"><%=i+1%></td>
	  <td class="list_normal_td">
	  	<div align="center"> 
	  		<img src="<%=request.getContextPath()%>/barcode?msg=<%=instockdetailid%>&type=code128&fmt=jpeg" height="20px" width=120px />
	  	</div>
	  </td>
	  <td class="list_normal_td"><%=linestatus%></td>
	  <td class="list_normal_td"><%=instockdetailid%></td>
	  <td class="list_normal_td"><%=m_strProductName == null ? "&nbsp;" : m_strProductName%></td>
	  <td class="list_normal_td"><%=m_strPackName == null ? "&nbsp;" : m_strPackName%></td>
	  <td class="list_normal_td"><%=m_strUnitName == null ? "&nbsp;" : m_strUnitName%></td>
	  <td class="list_normal_td"><%=nbf.format(invoicenum)%></td>
	  <td class="list_normal_td"><%=nbf.format(netweight)%></td>
	  <td class="list_normal_td"><%=nbf.format(recnum)%></td>
	  <td class="list_normal_td"><%=nbf.format(renetweight)%></td>
	</tr>
<%
        }
    }
%>	

 </table>

 
</body>
</html>