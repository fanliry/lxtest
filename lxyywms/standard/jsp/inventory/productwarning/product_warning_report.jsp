<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryStorage"%>
<%@page import="com.wms3.bms.standard.entity.base.BaseCargospace"%>

<html>
<head>
<title>��ӭʹ���Զ�������ֿ���Ϣ����ϵͳ</title>
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
		
		var classname = "com.wms3.bms.standard.action.report.inventory.InventoryQueryExcel";
		
		
		document.tempForm1.action = strUrl + "downFileAction&name=����ѯ.xls&fileType=excel&classname= " + classname + window.opener.condition;
		
		document.tempForm1.submit();	
		//alert(window.opener.condition);
		//alert(window.opener.getLotattCon());
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
 
<%
	int iLine = 0;	//��ʾ���������Ը���
	
%>

 <!-- ======== ���⿪ʼ ======== -->
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
	 <td height="40" colspan="<%=18 + iLine%>" valign="bottom"><div align="center" class="style2">��&nbsp;��&nbsp;��&nbsp;��</div></td>
   </tr>
   <tr>
	 <td height="20" colspan="8" valign="top"><div align="center">
	   <hr align="center" width="450" color="#006699" noshade="noshade" size="1">
	 </div></td>
   </tr>
 </table>
 <!-- ======== ������� ======== -->

 <table width="100%" height="20"  border="0" cellpadding="0" cellspacing="0">
   <tr><td></td></tr>
 </table>

 <!-- ======== �б�ʼ ======== -->  	
 <table width="98%" border="1" align="center" cellpadding="0" cellspacing="0" class="outer_table">
   <tr class="list_title_tr">
     <td class="list_title_td">�к�</td>
     <td class="list_title_td">����</td>
     <td class="list_title_td">��λ</td>
     <td class="list_title_td">��Ʒ����</td>
     <td class="list_title_td">Ʒ��</td>
     <td class="list_title_td">�������</td>
     <td class="list_title_td">�������</td>
     <td class="list_title_td">����</td>
     <td class="list_title_td">��������</td>
     <td class="list_title_td">���״̬</td>
     <td class="list_title_td">��Ʒ״̬</td>
   </tr>
   
<%

List ls = (List)request.getAttribute("exResultList");
if(ls != null && ls.size() > 0)
{
	Object[] ob = null;
    InventoryStorage storage = null;
    BaseCargospace space = null;
	String whArea;				//����
		String cargoSpace;			//��λ
	 	String product;				//��Ʒ
	 	String lotnumber;		    //����
	 	String instockid;           //��ⵥ
	 	String requestid;           //���뵥
	 	String traycode;        	//��������
	String punit;				//��λ
	double pnum;            	//�������
	String indate;             	//���ʱ��
	String intype;             	//��ⷽʽ 1.���� 2.�ѻ�
    String productcode;         //��Ʒ����
    String printdate;         //��������
    String csstauts;         //���״̬
    String productstatus;     //��Ʒ״̬
	 	for(int i=0; i<ls.size(); i++) 
	 	{
	 	    ob = (Object[])ls.get(i);
	 		storage = (InventoryStorage)ob[0];
	 		space = (BaseCargospace)ob[1];
	 		whArea = storage.getWhAreaName();		//����
	 		cargoSpace = storage.getCargoSpaceName();//��λ
	 		product = storage.getProductName();		//��Ʒ
	 		lotnumber = storage.getLotinfo();		//����
	 		traycode = storage.getTraycode();		//��������
	 		punit = storage.getPunitname();			//��λ
	 		productcode = storage.getProductcode(); //��Ʒ����		
			pnum = storage.getPnum();           //�������
			indate = storage.getIndate();       //���ʱ��
	 		intype = storage.getIntype();		//��ⷽʽ 1.���� 2.�ѻ�
	 		requestid = storage.getRequestid();
	 		instockid = storage.getInstockid();
	 		printdate = storage.getProductdate(); //��������
	 		csstauts = space.getCsstatusname(); 
	 		productstatus = storage.getProductstatus();
	  	 	
%>
   <tr class="list_normal_tr">
     <td class="TD_LIST1" align="center"><%=i+1%></td>
     <td class="TD_LIST" align="center"><%=whArea == null ? "" : whArea%></td>
     <td class="TD_LIST" align="center"><%=cargoSpace == null ? "" : cargoSpace%></td>
     <td class="TD_LIST" align="center"><%=productcode == null ? "" : productcode%></td>
     <td class="TD_LIST" align="center"><%=product == null ? "" : product%></td>
     <td class="TD_LIST" align="center"><%=indate == null ? "" : indate%></td>
     <td class="TD_LIST" align="center"><%=lotnumber == null ? "" : lotnumber%></td>
     <td class="TD_LIST" align="center"><%=pnum == 0.0 ? 0.0 : pnum%></td>
     <td class="TD_LIST" align="center"><%=printdate == null ? "" : printdate%></td>
     <td class="TD_LIST" align="center"><%=csstauts == null ? "" : csstauts%></td>
     <td class="TD_LIST" align="center"><%=productstatus == null ? "" : productstatus%></td>
   </tr>
   
<%
		}
		 }
%>
 
 </table>
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm1' target="_self"  style='display:none'></FORM>
</body>
</html>
