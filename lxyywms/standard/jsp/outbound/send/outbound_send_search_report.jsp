<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.wms3.bms.standard.entity.base.BaseLotSet" %>

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
		
		var classname = "com.wms3.bms.standard.action.report.outbound.OutboundSendQueryExcel";
		
		
		document.tempForm1.action = strUrl + "downFileAction&name=������ѯ.xls&fileType=excel&classname= " + classname + window.opener.condition;
		
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
 


 <!-- ======== ���⿪ʼ ======== -->
 <table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
   <tr>
	 <td height="40" colspan="<%=12%>" valign="bottom"><div align="center" class="style2">��&nbsp;��&nbsp;��&nbsp;ѯ&nbsp;��&nbsp;��</div></td>
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
     <td class="list_title_td">���ݱ��</td>
     <td class="list_title_td">�ͻ�</td>
     <td class="list_title_td">���ƺ�</td>
     <td class="list_title_td">��λ</td>
     <td class="list_title_td">Ʒ��</td>
     <td class="list_title_td">��������</td>
     <td class="list_title_td">��λ</td>
     <td class="list_title_td">����</td>
     
     <td class="list_title_td">ë��</td>
     <td class="list_title_td">����</td>
     <td class="list_title_td">���</td>
   </tr>
   
<%

	List ls = (List)request.getAttribute("exResultList");
if(ls != null && ls.size()>0){
	
	//����С��2λ
	NumberFormat nbf=NumberFormat.getInstance();      
	nbf.setMinimumFractionDigits(2);
	nbf.setMaximumFractionDigits(2); 
	
	Object[] obj = null;
	
	String invoiceid = null;	// ���ݱ��
	String customer = null;     // �ͻ�
	String vehicleno = null;	// ���ƺ�
	String vehiclepos = null;	// ��λ
	String product = null;		// ��Ʒ
 	String traycode = null;     // ��������
 	String cargospace = null;   // ��λ
 	String jobid = null;		// ��ҵ��
	double jobnum = 0;			// ����
	double volume = 0;         	// ���
		double weight = 0;         	// ����
	double netweight = 0;		// ����
	
	for(int i = 0; i < ls.size(); i++){
		
		obj = (Object[])ls.get(i);
		invoiceid = obj[0] == null ? "" : obj[0].toString();	// ���ݱ��
		customer = obj[1] == null ? "" : obj[1].toString();		// �ͻ�
		vehicleno = obj[2] == null ? "" : obj[2].toString();	// ���ƺ�
		vehiclepos = obj[3] == null ? "" : obj[3].toString();	// ��λ
		product = obj[4] == null ? "" : obj[4].toString();		// ��Ʒ
		traycode = obj[5] == null ? "" : obj[5].toString();		// ��������
		cargospace = obj[6] == null ? "" : obj[6].toString();	// ��λ
		
		//jobid = obj[7] == null ? "" : obj[7].toString();		// ��ҵ��
		
		jobnum = Double.parseDouble(obj[7].toString().trim());			// ����
		//boxnum = Long.parseLong(obj[9].toString());				// ����
		//boxnum =(Long)obj[9];
		volume = Double.parseDouble(obj[8].toString());      	// ���
			weight = Double.parseDouble(obj[9].toString());     	// ����
		netweight = Double.parseDouble(obj[10].toString());		// ����
		
	  	 	
%>
   <tr class="list_normal_tr">
     <td class="list_normal_td"><%=i+1%></td>
     <td class="list_normal_td"><%=invoiceid%></td>
     <td class="list_normal_td"><%=customer%></td>
     <td class="list_normal_td"><%=vehicleno%></td>
    
     <td class="list_normal_td"><%=vehiclepos%></td>
     <td class="list_normal_td"><%=product%></td>
     <td class="list_normal_td"><%=traycode%></td>
     <td class="list_normal_td"><%=cargospace%></td>
     <td class="list_normal_td"><%=(int)jobnum%></td>
     <td class="list_normal_td"><%=nbf.format(volume)%></td>
     <td class="list_normal_td"><%=nbf.format(weight)%></td>
     <td class="list_normal_td"><%=nbf.format(netweight)%></td>
   </tr>
   
<%
		}
	}
%>
 </table>
 <FORM action="<%=request.getContextPath()%>/BMSService" method='post' name='tempForm1' target="_self"  style='display:none'></FORM>
</body>
</html>
