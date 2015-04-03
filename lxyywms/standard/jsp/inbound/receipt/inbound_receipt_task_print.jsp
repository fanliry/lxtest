<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundReceiptHeader" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.InboundReceiptTransaction" %>
<%
	HashMap<String, List> hsLot = null;	//����Ҫ��ʾ������
	List<BaseLotSet> lsLot = null;  	//��ʾ�����������б�
	if(request.getSession(false).getAttribute("viewLot") != null){
		hsLot = (HashMap)request.getSession(false).getAttribute("viewLot");
		if(hsLot != null){
			lsLot = hsLot.get("shtrans");//�½��ջ���->��ʾ�ջ�����ϸ	
		}
	}
	

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
	 <td height="20" colspan="2"><div align="center" class="style2">�ջ�����</div></td>
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
 <table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" class="outer_table">
 <%
	List list = null;
	if(request.getAttribute("tasks") != null)
	{
		list = (List)request.getAttribute("tasks");
	}
 %>
   <tr class="list_title_tr">
	  <td width="40" class="list_title_td">���</td>
	  <td  class="list_title_td">��ǩ</td>
	  <td  class="list_title_td">���ٺ�</td>
	  <td  class="list_title_td">״̬</td>
	  <td  class="list_title_td">Ʒ��</td>
	  <td  class="list_title_td">��װ</td>
	  <td  class="list_title_td">��λ</td>
	  <td  class="list_title_td">����</td>
	  <td  class="list_title_td">����</td>
	  <td  class="list_title_td">ë��</td>
	  
	  <%
	int iLine = 0;	//��ʾ���������Ը���
	BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = lsLot.get(k);			
%>
	 <td class="list_title_td"><%=lotSet.getLotname()%></td>
<%
		}
	}
%>   
	  
	  
	</tr>
<%
    if(list!=null && !list.isEmpty()){
    	//����С��2λ
		NumberFormat nbf=NumberFormat.getInstance();      
		nbf.setMinimumFractionDigits(2);
		nbf.setMaximumFractionDigits(2); 
		
		InboundReceiptTransaction receiptTrans = null;//�ջ����׼�¼  
		String transid;		   //���׺�
      	String productid;      //��ƷID
      	String punit;          //��λ
      	double num;            //����
      	double weight;         //����
      	double netweight;      //����   
      	
      	String m_strProductName;// ��Ʒ
      	String m_strPackName;	// ��װ

      	String strStatusName = "�ջ����";	// ״̬��
		for(int i = 0; i < list.size(); i++)
		{
			receiptTrans = (InboundReceiptTransaction)list.get(i);
			transid = receiptTrans.getTransreceiptid();	//���׺�
      	 	productid = receiptTrans.getProductid();    //��ƷID
      	 	punit = receiptTrans.getM_strUnitName();    //��λ
      	 	num = receiptTrans.getRecnum();            	//����
      	 	weight = receiptTrans.getReweight();        //����
      	 	netweight = receiptTrans.getRenetweight();  //����   
      	
      	 	m_strProductName = receiptTrans.getM_strProductName();// ��Ʒ
      	 	m_strPackName = receiptTrans.getM_strPackName();	  // ��װ
            //1:�ջ���ɣ�2:������; 3:�����ϼ�; 4����ȫ�ϼ� 5��ȡ���ջ�
			if(receiptTrans.getTransstatus().equals("1"))
			{
				strStatusName = "�ջ����";
			}else if(receiptTrans.getTransstatus().equals("2")){
			    strStatusName = "������";
			}else if(receiptTrans.getTransstatus().equals("3")){
			    strStatusName = "�����ϼ�";
			}else if(receiptTrans.getTransstatus().equals("4")){
			    strStatusName = "��ȫ�ϼ�";
			}else if(receiptTrans.getTransstatus().equals("5")){
			    strStatusName = "ȡ���ջ�";
			}
%>	
		 	
	<tr class="list_normal_tr">
	  <td class="list_normal_td"><%=i+1%></td>
	  <td class="list_normal_td">
	  	<div align="center"> 
	  		<img src="<%=request.getContextPath()%>/barcode?msg=<%=transid%>&type=code128&fmt=jpeg" height="20px" width=120px />
	  	</div>
	  </td>
	  <td class="list_normal_td"><%=transid%></td>
	  <td class="list_normal_td"><%=strStatusName%></td>
	  <td class="list_normal_td"><%=m_strProductName == null ? "&nbsp;" : m_strProductName%></td>
	  <td class="list_normal_td"><%=m_strPackName == null ? "&nbsp;" : m_strPackName%></td>
	  <td class="list_normal_td"><%=punit == null ? "&nbsp;" : punit%></td>
	  <td class="list_normal_td"><%=nbf.format(num)%></td>
	  <td class="list_normal_td"><%=nbf.format(netweight)%></td>
	  <td class="list_normal_td"><%=nbf.format(weight)%></td>
<%
	if(lsLot != null){
		Field field = null; 
		for(int k = 0; k < iLine; k++){
			lotSet = lsLot.get(k);
			field = receiptTrans.getClass().getDeclaredField(lotSet.getLotid());					
%>
	 		 <td class="list_normal_td"><%=(String)field.get(receiptTrans)%></td>
<%
		}
	}
%>	  	  
	  
	</tr>
<%
        }
    }
%>	

 </table>

 
</body>
</html>