<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@page import="java.util.HashMap"%>
<%@page import="com.wms3.bms.standard.entity.base.BaseLotSet"%>
<%@page import="com.wms3.bms.standard.entity.inventory.InventoryTransaction"%>
<%@page import="java.lang.reflect.Field"%>
<%
	String list_items = (String)request.getAttribute("list_items");
	int itemscount = 0;
	String[] items = {""};
	if(list_items != null){
		items = list_items.split(",");
		itemscount = items.length;
	}
%>
<%
	HashMap<String, List> hsLot = null;	//����Ҫ��ʾ������
	List<BaseLotSet> lsLot = null;  	    //��ʾ�����������б�
	if(request.getSession(false).getAttribute("viewLot") != null){
		hsLot = (HashMap)request.getSession(false).getAttribute("viewLot");
		if(hsLot != null){
			lsLot = hsLot.get("kcjy");	//����̵���ʾ
		}
	}
%>
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
		alert(window.opener.condition);
		alert(window.opener.getPostCon());
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
	 <td height="40" colspan="<%=12 + itemscount%>" valign="bottom"><div align="center" class="style2">��&nbsp;��&nbsp;��&nbsp;��&nbsp;��&nbsp;��</div></td>
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
   <tr>
     <td class="list_title_td">�к�</td>
     <td class="list_title_td">��ҵID</td>
     <td class="list_title_td">����ʱ��</td>
     <td class="list_title_td">FM����</td>
     <td class="list_title_td">FM��λ</td>
     <td class="list_title_td">FMƷ�����</td>
     <td class="list_title_td">FM�ͻ�</td>
     <td class="list_title_td">FM��������</td>
<%
	int iLine = 0;	//��ʾ���������Ը���
	BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);			
%>
	 <td class="list_title_td">FM<%=lotSet.getLotname()%></td>
<%
		}
	}
%>

     <td class="list_title_td">FM����</td>
     <td class="list_title_td">TO����</td>
     <td class="list_title_td">TO��λ</td>
     <td class="list_title_td">TOƷ�����</td>
     <td class="list_title_td">TO�ͻ�</td>
     <td class="list_title_td">TO��������</td>
<%
//	int iLine = 0;	//��ʾ���������Ը���
	//BaseLotSet lotSet = null;
	if(lsLot != null){
		iLine = lsLot.size();
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);			
%>
	 <td class="list_title_td">TO<%=lotSet.getLotname()%></td>
<%
		}
	}
%>
     <td class="list_title_td">TO����</td>
     <td class="list_title_td">������Ա</td>
     
   </tr>
<%
	List<InventoryTransaction> ls = (List)request.getAttribute("exResultList");
	if(ls != null && ls.size() > 0)
	{
		InventoryTransaction iTransaction = null;
		String transactiontype;		//��������
		String transstatus;			//����״̬
		String doctype;				//��֤����
		String docid;				//��֤ID
		String doclineno;			//��֤�к�
		String fmcargo_space_id;	//FM��λID
		String fmwh_area_id;		//FM����ID
		String fmcustomerid;		//FM�ͻ�ID
		String fmpackid;			//FM��װID
		String fmpunit;				//FM������λ
		String fmproductid;			//FM��ƷID
		String tocargo_space_id;	//TO��λID
		String towh_area_id;		//TO����ID
		String tocustomerid;		//TO�ͻ�ID
		String topackid;		//TO��װID
		String topunit;			//TO������λ
		String toproductid;		//TO��ƷID
		String totraycode;		//TO��������
		String injobid;			//��ҵID
		String injobetailid;		//��ҵ��ϸID
		String transactiontime;		//����ʱ��
		String createmanid;		//������
		String inventoryid;		//���ID
		double fmnum;			//FM�������
		double fmvolume;		//FM������
		double fmweight;		//FM�������
		double fmnetweight;		//FM��澻��
		double tonetweight;		//TO��澻��
		double toweight;		//TO�������
		double tonum;			//TO�������
		String reasoncode;		//ԭ�����
		String reason;			//ԭ��
		double tovolume;		//TO������
		String fmtraycode;		//FM��������
		String warehouseid;		//�ֿ�ID
	    
	    //�����ֶ�
	    String boxcode;            //������
	    String productcode;        //��Ʒ����

  	 	for(int i=0; i<ls.size(); i++) 
  	 	{
  	 		iTransaction = ls.get(i);
  	 		warehouseid = iTransaction.getWarehouseid();				//�ֿ�ID
  	 		transactiontype = iTransaction.getTransactiontype();		//��������
  			transstatus = iTransaction.getTransstatus();			    //����״̬
  			doctype = iTransaction.getDoctype();						//��֤����
  			docid = iTransaction.getDocid();							//��֤ID
  			doclineno = iTransaction.getDoclineno();					//��֤�к�
  			fmcargo_space_id = iTransaction.getFmcargo_space_name();		//FM��λ��
  			fmwh_area_id = iTransaction.getFmwh_area_name();				//FM����ID
  			fmcustomerid = iTransaction.getFmcustomername();				//FM�ͻ�ID
  			fmpackid = iTransaction.getFmpackid();						//FM��װID
  			fmpunit = iTransaction.getFmpunit();						//FM������λ
  			fmnum = iTransaction.getFmnum();							//FM�������
  			fmproductid = iTransaction.getFmproductname();				//FM��ƷID
 			fmvolume = iTransaction.getFmvolume();						//FM������
  			fmweight = iTransaction.getFmweight();						//FM�������
  			tocargo_space_id = iTransaction.getTocargo_space_name();		//TO��λID
  			towh_area_id = iTransaction.getTowh_area_name();				//TO����ID
  			tocustomerid = iTransaction.getTocustomername();				//TO�ͻ�ID
  			topackid = iTransaction.getTopackid();						//TO��װID
  			topunit = iTransaction.getTopunit();						//TO������λ
  			toproductid = iTransaction.getToproductname();				//TO��ƷID
  			totraycode = iTransaction.getTotraycode();					//TO��������
  			injobid = iTransaction.getInjobid();						//��ҵID
  			injobetailid = iTransaction.getInjobetailid();				//��ҵ��ϸID
  			transactiontime = iTransaction.getTransactiontime();		//����ʱ��
  			createmanid = iTransaction.getCreatemanid();				//������
  			inventoryid = iTransaction.getInventoryid();				//���ID
  			fmnetweight = iTransaction.getFmnetweight();				//FM��澻��
  			tonetweight = iTransaction.getTonetweight();				//TO��澻��
  			toweight = iTransaction.getToweight();						//TO�������
  			tonum = iTransaction.getTonum();							//TO�������
  			reasoncode = iTransaction.getReasoncode();					//ԭ�����
  			reason = iTransaction.getReason();							//ԭ��
  			tovolume = iTransaction.getTovolume();						//TO������
  			fmtraycode = iTransaction.getFmtraycode();					//FM��������  
%>
   <tr class="list_normal_tr">
     <td class="list_normal_td"><%=i+1%></td>
     <td class="list_normal_td"><%=injobid%></td>
     <td class="list_normal_td"><%=transactiontime%></td>
     <td class="list_normal_td"><%=fmwh_area_id%></td>
     <td class="list_normal_td"><%=fmcargo_space_id%></td>
     <td class="list_normal_td"><%=fmproductid%></td>
     <td class="list_normal_td"><%=fmcustomerid%></td>
     <td class="list_normal_td"><%=fmtraycode%></td>
<%
	if(lsLot != null){
		Field field = null; 
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);
			field = iTransaction.getClass().getDeclaredField("fm"+lotSet.getLotid());			
%>
	 		 <td class="list_normal_td"><%=field.get(iTransaction)==null?"":(String)field.get(iTransaction)%></td>
<%
		}
	}
%>  
     <td class="list_normal_td"><%=fmnum%></td>
     <td class="list_normal_td"><%=towh_area_id%></td>
     <td class="list_normal_td"><%=tocargo_space_id%></td>
     <td class="list_normal_td"><%=toproductid%></td>
     <td class="list_normal_td"><%=tocustomerid%></td>
     <td class="list_normal_td"><%=totraycode%></td>
<%
	if(lsLot != null){
		Field field = null; 
		for(int k = 0; k < iLine; k++){
			lotSet = (BaseLotSet)lsLot.get(k);
			field = iTransaction.getClass().getDeclaredField("to"+lotSet.getLotid());			
%>
	 		 <td class="list_normal_td"><%=field.get(iTransaction)==null?"":(String)field.get(iTransaction)%></td>
<%
		}
	}
%>
     <td class="list_normal_td"><%=tonum%></td>
     <td class="list_normal_td"><%=createmanid%></td>
   </tr>
<%
		}
	}
%>

 </table>
</body>
</html>
