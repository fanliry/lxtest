<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@page import="com.wms3.bms.standard.entity.inbound.InboundInvoiceHeader"%>
<%@page import="com.wms3.bms.standard.entity.outbound.OutboundInvoiceHeader"%>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckTask"%>
<%@page import="com.wms3.bms.standard.entity.inventory.lxyy.InventoryCheckResult"%>

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
		
		var classname = "com.wms3.bms.standard.action.report.inventory.CheckInventoryQueryExcel";
		
		
		document.tempForm1.action = strUrl + "downFileAction&name=�̵��ѯ.xls&fileType=excel&classname= " + classname + window.opener.condition;
		
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
	 <td height="40" colspan="<%=18 + iLine%>" valign="bottom"><div align="center" class="style2">��&nbsp;��&nbsp;��&nbsp;��&nbsp;��&nbsp;��</div></td>
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
     <td class="list_title_td" >�ֿ�</td>
     <td class="list_title_td" >���뵥��</td>
     <td class="list_title_td" >�����</td>
     <td class="list_title_td" >��Ʒ��</td>
     <td class="list_title_td" >����</td>
     <td class="list_title_td" >�������</td>
     <td class="list_title_td" >�̵�����</td>
     <td class="list_title_td" >����Ա</td>
     <td class="list_title_td" >�̵�ʱ��</td>
   </tr>
   
<%
	List<Object[]> objLs = (List<Object[]> )request.getAttribute("objLs");  //����̵㵥

	if(objLs != null && objLs.size() > 0)
   	{   
		InventoryCheckTask checkTask = null;
		InventoryCheckResult checkResult = null;
		Object[] obj = new Object[2];
   		String strWarehouseId = "";		    // �ֿ�
		String strCheckReq = "";			// �̵�����
		String strCheckTask = "";			// ����
		String strProductName = "";         //��Ʒ��
		String strLotinfo = "";				// ����
		double strpnum =0.0;                 //�������
		double strCheckNum =0.0;            //�̵�����
		String strOpManName = "";			// ����Ա��
		String strOpManTime = "";			// ����ʱ��
	
   		for(int i = 0; i < objLs.size(); i++)
   		{
   			obj = (Object[])objLs.get(i);
   			checkResult = (InventoryCheckResult)obj[0];  //�̵���
   			checkTask = (InventoryCheckTask)obj[1];//�̵�����
   	   		strWarehouseId = checkTask.getWarehouseid();		    // �ֿ�
   			strCheckReq = checkResult.getRequestid();			// �̵�����
   			strCheckTask = checkResult.getTaskid();			// ����
   			strProductName = checkResult.getProductName();         //��Ʒ��
   			strLotinfo = checkResult.getLotnumber();				// ����
   			strpnum =checkResult.getNum();                 //�������
   			strCheckNum =checkResult.getChecknum();            //�̵�����
   			strOpManName = checkResult.getCreateman();			// ����Ա��
   			strOpManTime =checkResult.getChecktime() ;			// ����ʱ��
	  	 	
%>
   <tr  class="list_normal_tr">
     <td class="TD_LIST" align="center"><%=strWarehouseId == null ? "" : strWarehouseId%></td>
     <td class="TD_LIST" align="center"><%=strCheckReq == null ? "" : strCheckReq%></td>
     <td class="TD_LIST" align="center"><%=strCheckTask == null ? "" : strCheckTask%></td>
     <td class="TD_LIST" align="center"><%=strProductName == null ? "" : strProductName%></td>
     <td class="TD_LIST" align="center"><%=strLotinfo == null ? "" : strLotinfo%></td>
     <td class="TD_LIST" align="center"><%=strpnum%></td>	
     <td class="TD_LIST" align="center"><%=strCheckNum%></td>	
     <td class="TD_LIST" align="center"><%=strOpManName == null ? "" : strOpManName%></td>	
     <td class="TD_LIST" align="center"><%=strOpManTime == null ? "" : strOpManTime%></td>	
  </tr>
<%
		}
		 }
%>
 
 </table>
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm1' target="_self"  style='display:none'></FORM>
</body>
</html>
