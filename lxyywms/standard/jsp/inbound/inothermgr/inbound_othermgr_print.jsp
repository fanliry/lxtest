<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundPoHeader" %>
<%@ page import="com.wms3.bms.standard.entity.inbound.lxyy.InboundPoDetail" %>
<%
	InboundPoHeader po = (InboundPoHeader)request.getAttribute("po"); 
	
	String poid = "";     //���ⵥ�ݱ��
	String formdate = "";    	//��������
	String potype = "";        //���ⵥ������
	String supplier = "";		//��Ӧ��
	
	if(po != null){
		poid = po.getPoid();
		formdate = po.getCreatetime();
		potype = po.getPotypeName();
		supplier = po.getSupplierName();
	}
%>
<html>
<head>
<title>���ⵥ��ӡ</title>
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
	 <td height="40" colspan="8" valign="bottom"><div align="center" class="style2"><%=potype %></div></td>
   </tr>
   <tr>
	 <td height="40" colspan="8"><div align="center" class="style2"><img src="<%=request.getContextPath()%>/barcode?msg=<%=poid%>&type=code128&fmt=jpeg" height="40px" width=220px /><%=poid%></div></td>
   </tr>
   <tr>
	 <td height="20" colspan="8" valign="top"><div align="center">
	   <hr align="center" width="450" color="#006699" noshade="noshade" size="1">
	 </div></td>
   </tr>
   <tr>
	 <td height="20"><div align="left">���ݺţ�<%=poid%></div></td>
	 <td height="20"><div align="left">�������ڣ�<%=formdate==null?"":formdate%></div></td>
	 <td height="20"><div align="left">��Ӧ�̣�<%=supplier==null?"":supplier%></div></td>
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
	if(request.getAttribute("podetail") != null)
	{
		list = (List)request.getAttribute("podetail");
	}
 %>
   <tr class="list_title_tr">
	 <td class="list_title_td" width="50px">�к�</td>
     <td class="list_title_td">״̬ </td>
	 <td class="list_title_td">��Ʒ</td>
	 <td class="list_title_td">Ʒ��</td>
	 <td class="list_title_td">���</td>
	 <td class="list_title_td">��λ</td>
	 <td class="list_title_td">�������</td>
	 <td class="list_title_td">ԭ�����</td>
	 <td class="list_title_td">����</td>
	 <td class="list_title_td">��ʹ������</td>
	 <td class="list_title_td">���鵥��</td>
   </tr>
<%
	if(list != null && list.size()>0){
		InboundPoDetail poDetail = null;
		String podetailid="";//PO�к�
		String polinestatus="";//PO��״̬ 0-����
		String productid="";// ��ƷID
		String productName="";// ��Ʒ��
		String packid="";//��װID
		String eunit="";// ��λ
		String productCode = "";//ERP���ϱ���
		double ponum=0.0;//������
		double useponum=0.0;//�Ѿ�ʹ������
		double nouseponum=0.0;//δʹ������
		double price = 0.0;//����
		String strvalue="";
		String eunitName = "";
        String spec="";
        
        String lotinfo = "";
        String lotinfo2 = "";
        String checkid = "";

		for(int i=0;i<list.size();i++){
			poDetail = (InboundPoDetail)list.get(i);
			podetailid = poDetail.getPodetailid();
			polinestatus = poDetail.getPolinestatusName();
			productid = poDetail.getProductid();
			productName = poDetail.getM_strProductName();
			eunit = poDetail.getEunit();
			ponum = poDetail.getPonum();
			price = poDetail.getPrice();
			useponum = poDetail.getUseponum();
			eunitName = poDetail.getM_strUnitName();
			productCode = poDetail.getProductCode();
			spec=poDetail.getM_spec();
			lotinfo = poDetail.getLotinfo();
			lotinfo2 = poDetail.getLotinfo2();
			checkid = poDetail.getCheckid();    	 
%>	
		 	
	<tr class="list_normal_tr">
	  <td class="list_normal_td" align="center" width="50px"><%=i+1%></td>
	 <td class="list_normal_td" align="center"><%=polinestatus == null ? "" : polinestatus %></td>
	 <td class="list_normal_td" align="center"><%=productName == null ? "" : productName %></td>
	 <td class="list_normal_td" align="center"><%=productCode == null ? "" : productCode %></td>
	 <td class="list_normal_td" align="center"><%=spec == null ? "" : spec %></td>
	 <td class="list_normal_td" align="center"><%=eunitName == null ? "" : eunitName %></td>
	 <td class="list_normal_td" align="center"><%=lotinfo == null ? "" : lotinfo %></td>
	 <td class="list_normal_td" align="center"><%=lotinfo2 == null ? "" : lotinfo2 %></td>
	 <td class="list_normal_td" align="center"><%=ponum %></td>
	 <td class="list_normal_td" align="center"><%=useponum %></td>
	 <td class="list_normal_td" align="center"><%=checkid == null ? "" : checkid %></td>
   </tr>
<%
        }
    }
%>	
 </table>
</body>
</html>